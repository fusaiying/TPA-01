<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="豁免"
    v-bind="$attrs"
    width="80%"
    @open="openHandle">
    <el-form v-loading="loading" ref="baseForm" :model="baseForm" size="mini">
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="baseForm.tableData"
        size="small"
        ref="tableData"
        highlight-current-row
        tooltip-effect="dark"
        @row-click="handleSelectionChange">
<!--        <el-table-column type="selection" width="55" align="center"/>-->
        <el-table-column align="center" property="checked">
          <template slot-scope="scope">
            <el-checkbox v-model="scope.row.checked">{{&nbsp;}}</el-checkbox>
          </template>
        </el-table-column>
        <el-table-column property="contno" width="130" label="保单号" align="center"/>
        <el-table-column property="riskcode" label="险种代码" align="center"/>
        <el-table-column property="riskname" width="160" label="险种名称" align="center"/>
        <el-table-column property="paystartdate" label="保费豁免开始日期" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="scope.row.editFlag" label-width="0px"
              :prop="'tableData.' + scope.$index + '.paystartdate'"
              :rules="{ required: true, message: '请选择', trigger: 'change' }">
              <!-- <el-input v-model="scope.row.date" style="width: 90px" placeholder="请输入" size="mini"/> -->
              <el-date-picker
                style="width: 140px"
                v-model="scope.row.paystartdate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <span v-else>{{scope.row.paystartdate}}</span>
          </template>
        </el-table-column>
        <el-table-column property="payenddate" label="保费豁免结束日期" align="center" width="160"/>
        <el-table-column property="freepried" label="豁免期数" align="center"/>
        <el-table-column :formatter="getPaymentmode" property="payintv" label="缴费间隔" align="center"/>
        <el-table-column property="status" :formatter="getstatus" label="豁免状态" align="center"/>
        <el-table-column property="renewal" label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="editHandle(scope.$index)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
    <el-form ref="basicForm" :model="basicForm">
      <el-row style="margin-top: 20px;">
        <el-col :span="24">
          <el-form-item label="备注：" prop="remarks" label-width="90px"
              :rules="{ required: remarkFlag, message: '请输入', trigger: 'blur' }">
            <el-input maxlength="500" :rows="3" show-word-limit v-model="basicForm.remarks" type="textarea" placeholder="请输入" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="mini"  type="primary" @click="confirmHandle('01')" :disabled="node == 'complex' || node == 'spotCheck' || node == 'correct'">确认豁免</el-button>
      <el-button size="mini" @click="confirmHandle('02')" :disabled="node == 'complex' || node == 'spotCheck' || node == 'correct'">取消豁免</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {queryExemptionDetail, updateExemptionDetail , queryExemptionCount,queryPolicyExemptionDetail} from '@/api/claim/handleDeal'
import Bus from '../../bus/bus'
export default {
  //dicts: ['paymentmode','exemptionstatus'],
  props: {
    dictList:Array,
    value: Boolean,
    claimno: String,
    node: String
  },
  data() {
    return {
      multipleSelection: [],
      loading: false,
      basicForm: {
        remarks: null
      },
      baseForm: {
        tableData: []
      },
      chooseList: {},
      remarkFlag: true,
      exemptNum: 0,
      /*'paymentmode','exemptionstatus'*/
      paymentmodeOptions: [],
      exemptionstatusOptions: []
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
    if (this.dictList != null && this.dictList.length != 0) {
      this.paymentmodeOptions = this.dictList.find(item => {
        return item.dictType == 'paymentmode'
      }).dictDate
      this.exemptionstatusOptions = this.dictList.find(item => {
        return item.dictType == 'exemptionstatus'
      }).dictDate
    }
  },
  watch: {
      chooseList: function(newVal, oldVal) {
          // this.baseForm.tableData.forEach(item => {
          //     let flag = tableList.findIndex(val => item.grpcontno === val.grpcontno)
          //     if (flag == '-1' && item.checked) {
          //         tableList.push(item)
          //     }
          // })
          // tableList.forEach(item => {
          //     this.baseForm.tableData.forEach(val => {
          //         if (item.grpcontno === val.grpcontno) {
          //             val.checked = item.checked
          //         }
          //     })
          // })
          // console.log(newVal)
          // newVal.forEach(item => {
              this.baseForm.tableData.forEach((val, ind) => {
                  if (newVal.contno === val.contno) {
                      this.baseForm.tableData[ind].checked = newVal.checked
                  }
              })
          // })
      }
  },
  methods: {
    handleSelectionChange(val) {
        this.chooseList = {}
        val.checked = !val.checked
        this.chooseList = val
        // this.multipleSelection.push(val)
    },
    // 缴费间隔码表
    getPaymentmode(value) {
        /*return this.dict.label.paymentmode[value.payintv]*/
      return this.selectDictLabel(this.paymentmodeOptions,value.payintv);
    },
    getstatus(row) {
     /* return this.dict.label.exemptionstatus[row.status]*/
      return this.selectDictLabel(this.exemptionstatusOptions,row.status);
    },
    openHandle(){
      // 请求列表数据
      this.loading = true
      this.queryExemptionDetail()
    },
    queryExemptionDetail(){
      queryExemptionDetail(this.claimno).then(res => {
        if (res.status === '1') {
          res.data.forEach(item => {
              item.checked = false
          })
          this.baseForm.tableData = res.data
        } else {
          this.$message({ message: res.data.errMsg?res.data.errMsg:'查询列表错误！', type: 'error' })
        }
      }).finally(() => {
        this.loading = false
      })
    },
    dialogClosed() {
      this.$refs.baseForm.resetFields()
      this.$refs.basicForm.resetFields()
      this.$emit('input', false)
    },
    confirmHandle(status) {
      if (status === '01') {
          this.remarkFlag = true
      } else {
          this.remarkFlag = false
      }
      this.$nextTick(function() {
          if(!this.baseForm.tableData.some(item => item.checked)){
              return this.$message({ message: '请至少选择一条数据！', type: 'warning' })
          } else {
              this.$refs.baseForm.validate((valid0)=> {
                  this.$refs.basicForm.validate((valid) => {
                      if (valid&&valid0) {
                          this.multipleSelection = []
                          this.baseForm.tableData.forEach(item => {
                              if (item.checked) {
                                  this.multipleSelection.push(item)
                              }
                          })
                          let subFlag = this.multipleSelection.some(item => item.checked && item.status === 'temp')
                          if (status === '01' && subFlag) {
                              this.$message.warning('选择的保单已经确认豁免，请重新选择！')
                              return
                          }
                          let cancelFlag = this.multipleSelection.some(item => item.checked && !item.status)
                          if (status === '02' && cancelFlag) {
                              this.$message.warning('选择的保单还未确认豁免，请重新选择！！')
                              return
                          }
                          let params = {
                              claimno: this.claimno,
                              status,
                              remarks: this.basicForm.remarks,
                              exemptionDetailVOList: this.multipleSelection
                          }
                          updateExemptionDetail(params).then(res => {
                              if (res.status === '1') {
                                  this.queryExemptionDetail()
                                  queryPolicyExemptionDetail(this.claimno).then(res =>{
                                    if (res.status == '1') {
                                      this.exemptNum = res.data
                                    } else {
                                      this.$message.error('查询豁免条数错误！')
                                    }
                                  }).then(() =>{
                                    Bus.$emit('updateExemptNum',this.exemptNum)
                                  })
                                  this.$message({ message: '操作成功！', type: 'success' })
                              } else {
                                  this.$message({ message: res.data.errMsg?res.data.errMsg : '查询列表错误！', type: 'error' })
                              }
                          })
                      } else {
                          return false;
                      }
                  })
              })
          }
      })
    },
    editHandle(i){
      this.baseForm.tableData[i].editFlag = true
    }
  }
}
</script>
