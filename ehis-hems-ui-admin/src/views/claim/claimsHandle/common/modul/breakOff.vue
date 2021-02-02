<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="解约"
    width="86%"
    @open="openHandle">
    <el-card class="box-card" style="margin-top: -10px;" shadow="never">
      <el-table
        ref="table"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="policyInfo"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;"
        @selection-change="handleSelectionChange">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="props.row.riskinfoList"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column align="center" prop="insurancecode" label="险种编码" show-overflow-tooltip/>
              <el-table-column align="center" prop="insurancename" label="险种名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="insuredamount" label="保额/份数" show-overflow-tooltip/>
              <el-table-column align="center" prop="totalpremium" label="期交保费" show-overflow-tooltip/>
              <el-table-column align="center" prop="effectivedate" label="生效日期" show-overflow-tooltip/>
              <el-table-column align="center" prop="enddate" label="交至日期" show-overflow-tooltip/>
              <el-table-column align="center" prop="status" label="险种状态" show-overflow-tooltip>
                <template slot-scope="scop">
                  {{/*dict.label.policy_status[scop.row.status]*/this.selectDictLabel(this.policy_statusOptions,scop.row.status)}}
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
        <el-table-column align="center" prop="insuredName" label="被保人姓名" show-overflow-tooltip/>
        <el-table-column align="center" prop="insuredCardID" label="证件号码" show-overflow-tooltip/>
        <el-table-column align="center" prop="customerNo" label="客户号" show-overflow-tooltip/>
        <el-table-column align="center" prop="effectiveDate" label="生效日期" width="160" show-overflow-tooltip/>
        <el-table-column align="center" prop="endDate" label="交至日期" width="160" show-overflow-tooltip/>
        <el-table-column align="center" prop="paymentInterval" label="交费间隔" show-overflow-tooltip/>
        <el-table-column align="center" prop="prem" label="期交保费" show-overflow-tooltip/>
        <el-table-column align="center" prop="policyStatus" label="保单状态" show-overflow-tooltip>
          <template slot-scope="scop">
            {{/*dict.label.policy_status[scop.row.policyStatus]*/ this.selectDictLabel(this.policy_statusOptions,scop.row.policyStatus)}}
          </template>
        </el-table-column>
      </el-table>
      <div style="float: right;">
        <el-pagination
          layout="total, prev, pager, next"
          :total="total"
          @current-change="handleCurrentChange">
        </el-pagination>
      </div>
      <el-form ref="diaForm" style="margin-top: 20px;" :rules="tableFormRules" :model="diaForm" label-width="110px" size="mini">
        <el-form-item label="推送方式：" prop="pushtype">
          <el-checkbox-group v-model="diaForm.pushtype">
            <el-checkbox v-for="item in supplementmodeOptions" :label="item.dictValue" :key="item.dictValue">{{item.dictLabel}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="解约说明：" prop="description">
          <el-input
            type="textarea"
            :rows="3"
            placeholder="请输入内容"
            v-model="diaForm.description">
          </el-input>
          <!-- <el-autocomplete
            v-model="diaForm.description"
            :rows="3"
            :fetch-suggestions="querySearch"
            type="textarea"
            style="width: 100%;"
            placeholder="请输入内容">
          </el-autocomplete> -->
        </el-form-item>
        <div style="float: right; margin-bottom: 10px;">
          <el-button type="primary" size="mini" @click="queryPolicyinfoSend">发起解约</el-button>
          <el-button size="mini" @click="dialogClosed">返 回</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card v-if="breakInfo" class="box-card" style="margin-top: 10px;" shadow="never">
      <div slot="header" class="clearfix">
        <span>工单反馈</span>
      </div>
      <el-form label-width="130px" size="mini">
        <el-form-item label="解约说明及结论：" prop="pushtype">
          <span>{{breakInfo}}</span>
        </el-form-item>
        <!-- <el-form-item label="解约结论：" prop="pushtype">
          <span>同意</span>
        </el-form-item>
        <el-form-item label="解约说明：" prop="description">
          <span>同意解约</span>
        </el-form-item> -->
      </el-form>
    </el-card>
  </el-dialog>
</template>

<script>
import {queryPolicyinfo, queryPolicyinfoSend, queryNegotiationResults,getTempalte} from '@/api/claim/handleDeal'
export default {
  //dicts: ['supplementmode', 'policy_status'],
  props: {
    dictList:Array,
    value: Boolean,
    status: String,
    node: String,
    claimno: String,
  },
  data() {
    return {
      total: 0,
      pageno: 1,
      expList: [],
      pagesize: 10,
      diaForm: {
        pushtype: ['04'],
        supplementmode: []
      },
      multipleSelection: [],
      breakInfo: '',
      policyInfo: [],
      tableFormRules: {
        description: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        pushtype:  { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
      },
      supplementmodeOptions: [],
      policy_statusOptions: []
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  mounted() {
    this.loadAll()
    if (this.dictList!=null && this.dictList.length!=0) {
      this.supplementmodeOptions = this.dictList.find(item => {
        return item.dictType == 'supplementmode'
      }).dictDate
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
    }
  },
  methods: {
    querySearch(queryString, cb) {
        let expList = this.expList
        let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
        cb(results)
    },
    createFilter(queryString) {
        return (expList) => {
            return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        }
    },
    loadAll() {
      let type = '01'
      getTempalte(type).then(res =>{
        if (res.status == '1') {
          this.expList = res.data || []
        } else {
          this.$message.error('查询模版消息错误！')
        }
      })
    },
    dialogClosed() {
      this.$emit('input', false)
    },
    openHandle(){
      this.queryPolicyinfo()
      this.queryNegotiationResults()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleCurrentChange(val) {
      this.pageno = val
      this.queryPolicyinfo()
    },
    queryPolicyinfo() {
      let params = {
        pageno: this.pageno,
        pagesize: this.pagesize,
        insuredno: '',
        claimno: this.claimno
      }
      queryPolicyinfo(params).then(res => {
        if (res.status === '1') {
          this.total = res.data.total
          this.policyInfo = res.data.list
        } else {
          this.$message({ message: res.data.errMsg?res.data.errMsg:'查询保单列表错误！', type: 'error' })
        }
      })
    },
    queryNegotiationResults(){
      let params = {
        claimno: this.claimno,
        message: this.diaForm.description
      }
      queryNegotiationResults(this.claimno).then(res => {
        if (res.status === '1') {
          if(res.data){
            this.breakInfo = res.data
          }
        } else {
            this.$message({ message: res.data.errMsg?res.data.errMsg: '查询工单解约错误！', type: 'error' })
        }
      })
    },
    queryPolicyinfoSend(){
      if(this.multipleSelection.length){
        this.$refs.diaForm.validate((valid) => {
          if(valid){
            let params = {
              terminationflag: '04', // 目前只有工单
              claimno: this.claimno,
              message: this.diaForm.description+'/'
            }
            this.multipleSelection.map(item => {
              params.message += '保单号：'+item.policyNo + '/'
            })
            params.message.slice(-1)
            if(this.diaForm.pushtype.includes('04')){
              queryPolicyinfoSend(params).then(res => {
                if (res.status === '1') {
                  this.$message({ message: '操作成功！', type: 'success' })
                  // this.queryNegotiationResults()
                  // this.$set(this.diaForm, 'supplementmode', [])
                  this.dialogClosed()
                  this.$router.go(-1)
                } else {
                  this.$message({ message: res.data.errMsg?res.data.errMsg: '操作失败！', type: 'error' })
                }
              })
            } else {
              this.$message({ message: '推送方式请选择工单！', type: 'warning' })
            }
          } else {
            return false
          }
        })
      } else {
        this.$message({ message: '请至少选择一条保单信息！', type: 'warning' })
      }
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.expand_span {
  font-size: 12px;
}
</style>
