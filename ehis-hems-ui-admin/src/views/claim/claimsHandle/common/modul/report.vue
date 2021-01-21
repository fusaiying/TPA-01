<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="报案信息"
    width="76%"
    @open="registerInfo">
    <el-card>
      <div slot="header" class="clearfix">
        <span>未关联</span>
      </div>
      <el-table
        v-loading="loading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        @selection-change="handleSelectionChange">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="props.row.policyShowList"
              size="small"
              highlight-current-row
              tooltip-effect="dark">
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
                    <el-table-column label="给付责任名称" width="160" align="center">
                      <template slot-scope="scope">
                        <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyName }}</P>
                      </template>
                    </el-table-column>
                    <el-table-column label="给付责任类型" width="130" align="center">
                      <template slot-scope="scope">
                        <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyKind }}</P>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column align="center" property="contNo" label="保单号" show-overflow-tooltip/>
              <el-table-column align="center" property="riskInfo" label="险种名称" show-overflow-tooltip/>
              <el-table-column align="center" property="effectiveDate" label="有效日期" width="200" show-overflow-tooltip/>
              <el-table-column :formatter="getCNStatus" align="center" property="status" label="保单状态" show-overflow-tooltip/>
              <el-table-column align="center" property="special" label="特约信息" show-overflow-tooltip/>
              <el-table-column align="center" prop="isRecuperation" label="是否复效" show-overflow-tooltip>
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
          </template>
        </el-table-column>
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column property="register" width="116" label="报案号"  align="center"/>
        <el-table-column property="applicationReason" :formatter="getCNReasonType" label="申请类别" align="center"/>
        <el-table-column property="outpatientClaimAmount" label="门诊索赔金额" align="center"/>
        <el-table-column property="hospitalizationClaimAmount" label="住院索赔金额" align="center"/>
        <el-table-column property="currentState" :formatter="getCNCurrentStatus" label="事故者现状" align="center"/>
        <el-table-column property="insuredName" label="被保人" align="center"/>
        <el-table-column property="idNo" label="证件号码" width="124" align="center"/>
        <el-table-column property="registrant" label="报案人" align="center"/>
        <el-table-column property="registerDate" label="报案日期" align="center"/>
      </el-table>
    </el-card>
    <el-card style="margin-top: 15px;margin-bottom: 15px">
      <div slot="header" class="clearfix">
        <span>已关联</span>
      </div>
      <el-table
        v-loading="loading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="relationTableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="props.row.policyShowList"
              size="small"
              highlight-current-row
              tooltip-effect="dark">
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
                    <el-table-column label="给付责任名称" width="160" align="center">
                      <template slot-scope="scope">
                        <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyName }}</P>
                      </template>
                    </el-table-column>
                    <el-table-column label="给付责任类型" width="130" align="center">
                      <template slot-scope="scope">
                        <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyKind }}</P>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column align="center" property="contNo" label="保单号" show-overflow-tooltip/>
              <el-table-column align="center" property="riskInfo" label="险种名称" show-overflow-tooltip/>
              <el-table-column align="center" property="effectiveDate" label="有效日期" width="200" show-overflow-tooltip/>
              <el-table-column :formatter="getCNStatus" align="center" property="status" label="保单状态" show-overflow-tooltip/>
              <el-table-column align="center" property="special" label="特约信息" show-overflow-tooltip/>
              <el-table-column align="center" prop="isRecuperation" label="是否复效" show-overflow-tooltip>
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
          </template>
        </el-table-column>
        <el-table-column property="register" width="116" label="报案号"  align="center"/>
        <el-table-column property="applicationReason" :formatter="getCNReasonType" label="申请类别" align="center"/>
        <el-table-column property="outpatientClaimAmount" label="门诊索赔金额" align="center"/>
        <el-table-column property="hospitalizationClaimAmount" label="住院索赔金额" align="center"/>
        <el-table-column property="currentState" :formatter="getCNCurrentStatus" label="事故者现状" align="center"/>
        <el-table-column property="insuredName" label="被保人" align="center"/>
        <el-table-column property="idNo" label="证件号码" width="124" align="center"/>
        <el-table-column property="registrant" label="报案人" align="center"/>
        <el-table-column property="registerDate" label="报案日期" align="center"/>
      </el-table>
    </el-card>
    <div v-if="flag==='edit'" style="margin-top: 30px; text-align: right;">
      <el-button size="mini" type="primary" @click="relationHandle">关 联</el-button>
      <el-button size="mini" type="primary" @click="dialogClosed">返 回</el-button>
    </div>
  </el-dialog>
</template>

<script>
/*import {registerInfo} from '@/api/claim/handleDeal'*/
import { getNotRelationReport, claimRelatedReport, getRelationReport } from '@/api/claim/register'
import codeMap from "../../common/mixins/codeMap"
export default {
  mixins: [codeMap],
  props: {
    dictList:Array,
    value: Boolean,
    node: String,
    claimno: String,
    insuredNo: String,
    flag: {
      type: String,
      default: ''
    }
  },
 // dicts: ['policy_status', 'application_category', 'current_state','apply_type'],
  data() {
    return {
      loading: false,
      searchForm: {},
      tableData: [],
      relationTableData: [],
      multipleSelection: [],
      subTable: [{ policyNo: 'ERF23423645', insuredName: '小米', renewal: '是' }],
      policyCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      policy_statusOptions: [],
      application_categoryOptions: [],
      current_stateOptions: [],
      apply_typeOptions: []
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
    if (this.dictList!=null && this.dictList.length!=0) {
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
      this.application_categoryOptions = this.dictList.find(item => {
        return item.dictType == 'application_category'
      }).dictDate
      this.current_stateOptions = this.dictList.find(item => {
        return item.dictType == 'current_state'
      }).dictDate
      this.apply_typeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_type'
      }).dictDate
    }
  },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
      this.$emit('reportCountsUpdate',this.claimno)
    },
    registerInfo(){
      if(this.insuredNo !== '' && this.insuredNo != null) {
        this.loading = true
        getNotRelationReport(this.insuredNo).then(response => {
            if (response.status === '1') {
              this.tableData = response.data
              this.getRelationReport()
            } else {
              this.$message({ message: '查询报案信息失败！', type: 'error' })
            }
          }).finally(() => {
            this.loading = false
          })
      }
    },
    getRelationReport() {
      this.loading = true
      const requestData = {
        claimNo:this.claimno,
        insuredNo:this.insuredNo
      }
      getRelationReport(requestData).then(response => {
        if (response.status === '1') {
          this.relationTableData = response.data
        }
      }).finally(() => {
        this.loading = false
      })
    },
    relationHandle() {
      if(this.multipleSelection.length > 0) {
        const dataList = []
        this.multipleSelection.forEach(item => dataList.push(item.register))
        const requestData = {
          claimNo:this.claimno,
          registerNoList:dataList
        }
        claimRelatedReport(requestData).then(response => {
          if (response.status === '1') {
            if(response.data) {
              this.$message({ message: '存在出险日期不在保单有效日期内的报案记录，请核实！', type: 'warning' })
            }else{
              this.$message({ message: '关联报案成功！', type: 'success' })
              this.registerInfo()
            }
          } else {
            this.$message({ message: '关联报案失败！', type: 'error' })
          }
        })
      }else{
        this.$message({ message: '请先选择案件！', type: 'warning' })
      }
    },
    getCNStatus(row, col) {
      /*return this.dict.label.policy_status[row.status]*/
      return this.selectDictLabel(this.policy_statusOptions,row.status)
    },
    getCNReasonType(row, col) {
      if (row.applicationReason != null) {
        if (row.applicationReason.length > 2) {
          const reasonArray = row.applicationReason.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              /*reason = this.dict.label.application_category[reasonArray[i]]*/
              reason = this.selectDictLabel(this.application_categoryOptions,reasonArray[i])
            } else {
              /*reason = reason + '|' + this.dict.label.application_category[reasonArray[i]]*/
              reason = reason + '|' + this.selectDictLabel(this.application_categoryOptions,reasonArray[i])
            }
          }
          return reason
        } else {
          /*return this.dict.label.application_category[row.applicationReason]*/
          return this.selectDictLabel(this.application_categoryOptions,row.applicationReason)
        }
      }
    },
    getCNCurrentStatus(row, col) {
      /*return this.dict.label.current_state[row.currentState]*/
      return this.selectDictLabel(this.current_stateOptions,row.currentState)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.el-table /deep/ .el-table__expanded-cell {
  padding: 20px;
}
.expand_span {
  font-size: 12px;
}
</style>
