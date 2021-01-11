<template>
  <el-card class="box-card">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item name="1">
        <template slot="title">
          <span style="font-size:16px;color:black">客户信息查询</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
        </template>
        <el-card class="box-card">
          <el-form ref="searchForm" :model="searchForm" label-width="120px" size="mini">
            <el-row>
              <el-col :span="8">
                <el-form-item label="保单号："
                  prop="policyNo"
                  :rules="{ required: false, message: '请输入', trigger: 'blur' }">
                  <el-input v-model="searchForm.policyNo" class="item-width" autocomplete="off" clearable
                  @change="policyChange"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item
                  label="被保人姓名："
                  prop="name"
                >
                  <el-input
                    v-model="searchForm.name"
                    class="item-width"
                    autocomplete="off"
                    clearable=""
                    @change="nameChange"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item
                  label="证件号码："
                  prop="idNo"
                  >
                  <el-input
                    v-model="searchForm.idNo"
                    class="item-width"
                    autocomplete="off"
                    clearable
                    @change="idChange"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item
                  label="客户号："
                  prop="insuredNo"
                >
                <el-input v-model="searchForm.insuredNo" class="item-width" autocomplete="off" clearable
                  @change="policyChange"/>
                </el-form-item>
              </el-col>
              <!-- <el-col :span="8">
                <el-form-item
                  label="出生日期："
                  prop="birthday"
                  :rules="{ required: birRequired, message: '请输入', trigger: 'change' }">
                  <el-date-picker
                    v-model="searchForm.birthday"
                    style="width: 200px;"
                    type="date"
                    placeholder="选择日期"
                    value-format="yyyy-MM-dd"
                    clearable/>
                </el-form-item>
              </el-col> -->
              <el-col :span="16" style="text-align: right;">
                <span style="padding-right: 20px;">
                  <el-button
                    :loading="searchLoading"
                    size="mini"
                    type="success"
                    icon="el-icon-search"
                    :disabled="disableFlag || acceptFlag"
                    @click="searchHandle"
                  >查询</el-button>
                  <el-button :loading="confirmLoading" :disabled="disableFlag || acceptFlag" size="mini" type="primary"  @click="confirmHandle">确 定</el-button>
                </span>
              </el-col>
            </el-row>
            <div style="text-align: right; margin-right: 10px;"/>
          </el-form>
          <el-divider></el-divider>
          <el-table
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="tableData"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            @selection-change="handleSelectionChange">
            <el-table-column v-if="node !== 'report'" type="selection"/>
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-table
                  ref="table"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  :data="props.row.riskList"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  style="width: 100%;">
                  <el-table-column align="center" prop="riskName" label="险种名称" show-overflow-tooltip/>
                  <el-table-column align="center" prop="dutyName" label="责任名称" show-overflow-tooltip/>
                  <el-table-column align="center" prop="amount" label="责任保额" show-overflow-tooltip/>
                  <el-table-column align="center" prop="surplusAmount" label="剩余保额" show-overflow-tooltip/>
                  <el-table-column align="center" prop="getLimit" label="责任免赔额" show-overflow-tooltip/>
                  <el-table-column align="center" prop="surplusGetLimit" label="剩余免赔额" show-overflow-tooltip/>
                  <el-table-column label="给付责任编码" width="130" align="center">
                    <template slot-scope="scope">
                      <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                    </template>
                  </el-table-column>
                  <el-table-column label="给付责任名称" width="180" align="center">
                    <template slot-scope="scope">
                      <P class="duty-p" v-for="item in scope.row.getDutyList" :title="item.getDutyName" :key="item.getDutyCode">{{ item.getDutyName }}</P>
                    </template>
                  </el-table-column>
                  <el-table-column label="给付责任类型" width="130" align="center">
                    <template slot-scope="scope">
                      <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyKind===null?'': this.selectDictLabel(this.get_duty_kindOptions, item.getDutyKind.trim())}}</P>
                    </template>
                  </el-table-column>
                </el-table>
              </template>
            </el-table-column>
            <el-table-column align="center" property="insuredName" label="被保人姓名" show-overflow-tooltip/>
            <el-table-column align="center" property="contNo" label="保单号" show-overflow-tooltip/>
            <el-table-column align="center" property="riskInfo" label="险种名称" show-overflow-tooltip/>
            <el-table-column align="center" property="effectiveDate" label="有效日期" width="200" show-overflow-tooltip/>
            <el-table-column :formatter="getCNStatus" align="center" property="status" label="保单状态" show-overflow-tooltip/>
            <el-table-column align="center" property="special" label="特约信息" show-overflow-tooltip/>
            <el-table-column align="center" property="isRecuperation" label="是否复效" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-popover
                  v-if="scope.row.isRecuperation === '是'"
                  placement="right"
                  width="400"
                  trigger="click">
                  <el-table
                    :data="[scope.row]"
                    :header-cell-style="{color:'#555', fontWeight: '400'}"
                    size="mini">
                    <el-table-column property="policyNo" label="复效日期"/>
                    <el-table-column property="name" label="失效日期"/>
                    <el-table-column property="address" label="复效健康告知"/>
                  </el-table>
                  <el-button slot="reference" type="text" size="mini">
                    {{ scope.row.isRecuperation }}
                    <i style="color: #E6A23C;font-size: 14px;" class="el-icon-warning-outline"/>
                  </el-button>
                </el-popover>
                <span v-if="scope.row.isRecuperation === '否'">{{ scope.row.isRecuperation }}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" property="isRenewal" label="是否续保" show-overflow-tooltip/>
            <el-table-column align="center" property="socialInsurance" label="社保投保" show-overflow-tooltip/>
          </el-table>
        </el-card>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import { getCoreInfo } from '@/api/claim/register'
import Bus from '../../bus/bus'
import { atLeastOne } from '@/utils/commenMethods.js'
export default {
  props: {
    dictList:Array,
    value: Boolean,
    node: String,
    sportFlag: Boolean,
    disableFlag: Boolean,
    acceptFlag: Boolean,
  },
  //dicts: ['policy_status','get_duty_kind'],
  // inject: ['insuredInfoShow','lightListDeal'],
  data() {
    return {
      searchForm: {
        name: '',
        idNo: '',
        policyNo: '',
        // birthday: '',
        customerNo: '',
        insuredNo: ''
      },
      activeNames: ['1'],
      searchLoading: false,
      confirmLoading: false,
      nameRequired: true,
      idRequired: true,
      birRequired: false,
      tableData: [],
      multipleSelection: [],
      queryPolicyDataPaging: {
        currentPage: 1,
        totalPages: 0,
        pageSize: 10,
        powerData: []
      },
      flag: false, // 判断查询出来的所有保单信息是不是都是一个人的
      insuredNo: '', // 通过
      lightList: [],
      policy_statusOptions: [],
      get_duty_kindOptions: [],
    }
  },
  mounted() {
    Bus.$on('sportFlag',data =>{
      this.activeNames = data
    })
    if (this.dictList!=null && this.dictList.length!=0) {
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
      this.get_duty_kindOptions = this.dictList.find(item => {
        return item.dictType == 'get_duty_kind'
      }).dictDate
    }
  },
  created() {
    if (this.sportFlag == false) {
      this.activeNames = ['1']
    } else {
      this.activeNames = []
    }
  },
  methods: {
    nameChange(val) {
      if (val) {
        this.idRequired = false
        this.birRequired = true
        this.$refs.searchForm.clearValidate('idNo')
      } else {
        if(this.searchForm.policyNo){
          this.idRequired = false
          this.nameRequired = false
          this.birRequired = false
          this.$refs.searchForm.clearValidate('birthday')
          this.$refs.searchForm.clearValidate('name')
          this.$refs.searchForm.clearValidate('idNo')
        }
        this.idRequired = true
        this.birRequired = false
      }
    },
    handleChange(val) {
    },
    idChange(val) {
      if (val) {
        this.nameRequired = false
        this.birRequired = false
        this.$refs.searchForm.clearValidate('name')
        this.$refs.searchForm.clearValidate('birthday')
      } else {
        this.nameRequired = true
        this.birRequired = true
      }
    },
    policyChange(val){
      if(val) {
        this.idRequired = false
        this.nameRequired = false
        this.birRequired = false
        this.$refs.searchForm.clearValidate('birthday')
        this.$refs.searchForm.clearValidate('name')
        this.$refs.searchForm.clearValidate('idNo')
      } else {
        this.idRequired = true
        this.nameRequired = true
      }
    },
    dialogClosed() {
      this.$refs.searchForm.resetFields()
      this.$emit('input', false)
    },
    confirmHandle() {

      if (this.node !== 'report') {
        if (this.multipleSelection.length === 0) {
          this.$message.warning('请至少选择一条记录！')
          return
        }
        this.checkMulInfo(this.multipleSelection)
        this.lightList = []
        this.multipleSelection.forEach(item => this.lightList.push(item.contNo))
        if (this.flag) {
          this.$message.warning('存在多个被保人的信息，请核实！')
          return
        }
      } else {
        if (this.flag) {
          this.$message.warning('存在多个被保人的信息，请核实！')
          return
        }
      }
      if (this.insuredNo !== '') {
        this.confirmLoading = true
        const requestData = {
          insuredNo: this.insuredNo
        }
        getCoreInfo(requestData).then(res => {
          if (res.status === '1') {
            this.$emit('controlList',false)
            Bus.$emit('updateFlag',true)
            setTimeout(() =>{
              Bus.$emit('lightListDeal',this.lightList)
              Bus.$emit('insuredInfoShow',res.data)
            },500)
            this.activeNames = []
            this.$refs.searchForm.resetFields()
            this.tableData = []
          } else {
            this.$message({ message: '查询核心失败！', type: 'error' })
          }
        }).finally(() => {
          this.confirmLoading = false
        })
      } else {
        this.$message.warning('请查询出对应的信息！')
        return
      }
    },
    // 查询出的保单数据进行分页处理
    handleQueryPolicyDataChange(ind, pag) {
      const start = (ind - 1) * pag
      const end = (ind - 1) * pag + pag
      this.tableData = this.queryPolicyDataPaging.powerData.slice(start, end)
      this.queryPolicyDataPaging.totalPages = this.queryPolicyDataPaging.powerData.length
    }, handleQueryPolicySizeChange(val) {
      this.queryPolicyDataPaging.pageSize = val
      const num = this.queryPolicyDataPaging.pageSize
      this.handleQueryPolicyDataChange(1, num)
    }, handleQueryPolicyCurrentChange(val) {
      this.queryPolicyDataPaging.currentPage = val
      const curre = this.queryPolicyDataPaging.currentPage
      const num = this.queryPolicyDataPaging.pageSize
      this.handleQueryPolicyDataChange(curre, num)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    searchHandle() {
      const flag = atLeastOne(this.searchForm)
      if (!flag) {
        this.$message.warning('必须录入一项查询条件！')
        return
      }
      if (this.searchForm.name !== '') {
        if (this.searchForm.policyNo == '' && this.searchForm.insuredNo == '' && this.searchForm.idNo == '') {
          this.$message.warning('被保人姓名需与其他条件组合查询！')
          return
        }
      }
      this.$refs.searchForm.validate((valid) => {
      if (valid) {
        this.searchLoading = true
        getCoreInfo(this.searchForm).then(res => {
          if (res.status === '1') {
            this.tableData = []
            this.queryPolicyDataPaging.powerData = []
            if (res.data.length !== 0) {
              res.data.forEach(item => this.queryPolicyDataPaging.powerData.push(item.policyShow))
              const num = this.queryPolicyDataPaging.pageSize
              this.handleQueryPolicyDataChange(1, num)
              this.checkInfo(res.data)
            } else {
              this.$message({ message: '未查询到信息！', type: 'warning' })
            }
          } else {
            this.$message({ message: '查询核心失败！', type: 'error' })
          }
        }).finally(() => {
          this.searchLoading = false
        })
      } else {
        return false
      }
    })
    },
    checkInfo(data) {
      const set = new Set()
      data.forEach(item => set.add(item.insuredNo))
      if (set.size > 1) {
        this.flag = true
        this.insuredNo = ''
      } else {
        this.flag = false
        for (const val of set.values()) {
          this.insuredNo = val
        }
      }
    },
    checkMulInfo(data) {
      const set = new Set()
      data.forEach(item => set.add(item.insuredNo))
      if (set.size > 1) {
        this.flag = true
        this.insuredNo = ''
      } else {
        this.flag = false
        for (const val of set.values()) {
          this.insuredNo = val
        }
      }
    },
    getCNStatus(row, col) {
      //return this.dict.label.policy_status[row.status]
      return this.selectDictLabel(this.policy_statusOptions, row.status)
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.duty-p {
  width: 170px;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.el-collapse {
  border: none;
}

.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
