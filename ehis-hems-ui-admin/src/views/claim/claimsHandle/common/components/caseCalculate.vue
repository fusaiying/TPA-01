<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;案件理算</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;案件理算</i>
        <span style="float: right;">
        <el-button v-if="!isOpen" type="primary" :disabled="!collapsed" size="mini" @click="openAll">一键展开</el-button>
        <el-button v-else type="primary" :disabled="!collapsed" size="mini" @click="closeAll">一键收起</el-button>
        <el-button v-if="node==='calculateReview'" type="primary" :disabled="!collapsed" size="mini"
                   @click="calculate">赔付计算</el-button>
        <el-button v-if="node==='calculateReview'" type="primary" :disabled="!collapsed" size="mini"
                   @click="save">保存</el-button>
      </span>
      </div>
    </div>
    <el-form ref="caseForm" :rules="caseRules" :model="caseForm" size="small" v-show="collapsed">
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :cell-style="cellStyle"
        v-loading="caseLoading"
        :data="caseForm.caseData"
        size="small"
        highlight-current-row
        @expand-change="getMinData"
        :expand-row-keys="expands"
        :row-key="getRowKeys"
        @row-click="rowClick"
        show-summary
        :summary-method="getSum"
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="scopes">
            <el-table :data="scopes.row.minData"
                      :header-cell-style="{color:'black',background:'#f8f8ff'}"
                      highlight-current-row
                      size="small"
                      v-loading="loading"
                      tooltip-effect="dark"
                      @row-click="rowMinClick"
                      style="width: 100%;">
              <el-table-column align="center" width="90" prop="feeItemName" label="费用项" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyName" width="200" label="赔付责任" show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-form-item v-if="!scope.row.isEdit" style="display: inline-flex !important;"
                                :prop="'caseData.'+scopes.$index+'.minData.' + scope.$index + '.riskCode'"
                                :rules="caseRules.riskCode">
                    <el-select v-model="scope.row.riskCode" filterable style="width: 95px"
                               clearable size="mini"
                               @change="getSelectSData($event,scope.row,scope.$index)"
                               placeholder="请选择">
                      <el-option v-for="option in scope.row.selectFData" :key="option.policyNo"
                                 :label="option.policyNo+'-'+option.riskName"
                                 :value="option.riskCode"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item v-if="!scope.row.isEdit" style="display: inline-flex !important;"
                                :prop="'caseData.'+scopes.$index+'.minData.' + scope.$index + '.dutyDetailCode'"
                                :rules="caseRules.dutyDetailCode">
                    <el-select v-model="scope.row.dutyDetailCode" filterable style="width: 95px"
                               clearable size="mini"
                               @change="changeMinData($event,scope.row,scope.$index)"
                               placeholder="请选择">
                      <el-option v-for="option in scope.row.selectSData" :key="option.dutyDetailCode"
                                 :label="option.dutyDetailName"
                                 :value="option.dutyDetailCode"/>
                    </el-select>
                  </el-form-item>
                  <span class="size" v-if="scope.row.isEdit">{{scope.row.policyNo}}-{{scope.row.riskName}} | {{scope.row.dutyDetailName}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="billDetailAmount" label="费用项金额" width="90" show-overflow-tooltip/>
              <el-table-column align="center" prop="hosDiscountAmount" label="折扣金额" show-overflow-tooltip/>
              <el-table-column align="center" prop="selfAmount" label="自费金额" show-overflow-tooltip/>
              <el-table-column align="center" prop="partSelfAmount" label="部分自费" show-overflow-tooltip/>
              <el-table-column align="center" prop="advancePayment" label="先期给付" show-overflow-tooltip/>
              <el-table-column align="center" prop="unableAmount" label="不合理金额" width="90" show-overflow-tooltip/>
              <el-table-column align="center" prop="deduUsed" label="免赔额" show-overflow-tooltip/>
              <el-table-column align="center" prop="payRate" label="赔付比例%" width="90" show-overflow-tooltip/>
              <el-table-column align="center" prop="calAmount" label="理算金额" show-overflow-tooltip/>
              <el-table-column align="center" prop="refusedAmount" label="拒赔金额" show-overflow-tooltip/>
              <el-table-column align="center" prop="billDetailCopay" label="自付额" show-overflow-tooltip/>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" width="140" prop="billNo" label="账单号/发票号" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.billNo }} | {{ scope.row.invoiceNo }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="treatmentStartDate" label="就诊日期" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.treatmentStartDate | changeDate }} ~ {{ scope.row.treatmentEndDate | changeDate }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="billAmount" label="账单金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="hosDiscountAmount" label="折扣金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="unableAmount" label="不合理金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="deduUsed" label="免赔额" show-overflow-tooltip/><!---->
        <el-table-column align="center" prop="calAmount" label="理算金额" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link style="font-size: 11px" type="primary" @click="openAdjustmentDialog(scope.$index)">{{
              scope.row.calAmount }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="payAmount" label="赔付金额" width="110" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.isEdit" style="display: inline-flex !important;"
                          :prop="'caseData.' + scope.$index + '.payAmount'"
                          :rules="caseRules.payAmount">
              <el-input v-model="scope.row.payAmount" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span class="size" v-if="scope.row.isEdit">{{scope.row.payAmount}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="refusedAmount" label="拒赔金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="remark" label="备注" width="110" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link style="font-size: 11px" v-if="scope.row.remark" type="primary"
                     @click="changeRemark(scope.$index,scope.row.remark)">{{ scope.row.remark }}
              </el-link>
            <el-link style="font-size: 11px" type="primary" v-else @click="changeRemark(scope.$index)">请点击输入备注</el-link>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="payConclusion" label="赔付结论" width="110" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.isEdit" style="display: inline-flex !important;"
                          :prop="'caseData.' + scope.$index + '.payConclusion'"
                          :rules="caseRules.payConclusion">
              <el-select v-model="scope.row.payConclusion" filterable style="width: 95px" clearable size="mini"
                         placeholder="请选择">
                <el-option v-for="option in conclusionOptions" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
            <span class="size" v-if="scope.row.isEdit">{{selectDictLabel(conclusionOptions,scope.row.payConclusion)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="copay" label="自付额" show-overflow-tooltip/>
      </el-table>
    </el-form>

    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="备注明细"
      width="40%" height="35%">
      <div style="height: 200px">
        <el-form ref="remarkForm" :model="remarkForm" size="mini" :rules="remarkRule">
          <el-row>
            <el-col :span="8">
              <el-form-item prop="pulloutDescribe" style="position:relative;width: 550px">
                <el-input type="textarea" :rows="7" v-model="remarkForm.remark"
                          class="item-widths" clearable
                          size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <span style="float: right;">
          <el-button type="primary" size="mini" @click="saveRemark">确认</el-button>
          <el-button size="mini" @click="dialogVisable=false">返回</el-button>
        </span>
        </el-form>
      </div>
    </el-dialog>
    <!-- 理算明细 -->
    <adjustment-detail :fixInfo="fixInfo" :value="adjustmentDialog" @closeAdjustmentDialog="closeAdjustmentDialog"/>
  </el-card>
</template>
<script>
  import {getAddress} from '@/api/supplierManager/supplier'
  import {
    infoList,
    itemList,
    detailsList,
    responsibilityDetailsList,
    billDetailsSave,
    insurancePolicyList,
    calculate,
    calSummary
  } from '@/api/claim/handleCom'
  import adjustmentDetail from '../modul/adjustmentDetail'
  import moment from 'moment'

  let dictss = [{dictType: 'application_reason'}, {dictType: 'preAuthFlag'}, {dictType: 'priority_reason'},
    {dictType: 'accident_status'}, {dictType: 'current_state'}, {dictType: 'conclusion'},]
  export default {
    components: {
      adjustmentDetail
    },
    mixins: [],
    props: {
      status: String,
      fixInfo: Object,
      node: String,
      sonCalculateData: Array,
    },
    watch: {
      sonCalculateData: function (newValue) {
        if (newValue != null && newValue.length > 0) {
          newValue.forEach(item => {
            item.isEdit = true
          })
          this.caseForm.caseData = newValue
        }
      },
      fixInfo: function (newVal) {
        this.getCalSummary()
      },
    },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      },

    },

    data() {
      const checkPayAmount = (rule, value, callback) => {
        const index = rule.field.replace('caseData.', '').replace('.payAmount', '')
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (value) {
          if (value < 0) {
            callback(new Error("最小允许录入0"));
          } else if (!regx.test(value)) {
            callback(new Error("请保留两位小数"));
          } else if (value>(parseFloat(this.caseForm.caseData[index].billAmount)-parseFloat(this.caseForm.caseData[index].hosDiscountAmount))){
            callback(new Error("录入金额有误"));
          }else {
            this.caseForm.caseData[index].refusedAmount=parseFloat(this.caseForm.caseData[index].billAmount)-parseFloat(this.caseForm.caseData[index].hosDiscountAmount)-parseFloat(this.caseForm.caseData[index].payAmount)
            callback();
          }
        } else {
          callback(new Error("赔付金额不能为空"));
        }
      }
      const checkRemark = (rule, value, callback) => {
        const index = rule.field.replace('caseData.', '').replace('.remark', '')
        if (value) {
          callback();
        } else {
          if (this.caseForm.caseData[index].payAmount==0 && this.caseForm.caseData[index].calAmount==0){
            callback();
          }else if (this.caseForm.caseData[index].payAmount!=this.caseForm.caseData[index].calAmount){
            callback(new Error("请录入备注"));
          }else {
            callback();
          }
        }
      }
      return {
        isSum: false,
        calSummaryData: {},
        loading: true,
        dialogVisable: false,
        index: undefined,
        expands: [],
        caseForm: {
          caseData: []
        },
        caseRules: {
          payAmount: [{validator: checkPayAmount,required: true,trigger: ['blur','change']}],//赔付金额
          remark: [{validator: checkRemark,required: true,trigger: ['blur']}],//赔付金额
          payConclusion: [{required: true, message: '请选择赔付结论', trigger: 'blur'}],//赔付结论
          riskCode: [{required: true, message: '请选择', trigger: 'blur,change'}],//
          dutyDetailCode: [{required: true, message: '请选择', trigger: 'blur,change'}],//
        },
        remarkForm: {
          remark: ''
        },
        remarkRule: {},
        collapsed: false,
        isOpen: false,
        adjustmentDialog: false,
        dictList: [],
        caseLoading: false,
        application_reasonOptions: [],
        preAuthFlagOptions: [],
        priority_reasonOptions: [],
        accident_statusOptions: [],
        current_stateOptions: [],
        conclusionOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.preAuthFlagOptions = this.dictList.find(item => {
        return item.dictType === 'preAuthFlag'
      }).dictDate
      this.application_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'application_reason'
      }).dictDate
      this.priority_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'priority_reason'
      }).dictDate
      this.accident_statusOptions = this.dictList.find(item => {
        return item.dictType === 'accident_status'
      }).dictDate
      this.current_stateOptions = this.dictList.find(item => {
        return item.dictType === 'current_state'
      }).dictDate
      this.conclusionOptions = this.dictList.find(item => {
        return item.dictType === 'conclusion'
      }).dictDate


    },

    methods: {
      openAdjustmentDialog(index) {
        this.adjustmentDialog = true
      },
      closeAdjustmentDialog() {
        this.adjustmentDialog = false
      },
      checkedAllFun() {

      },
      calculate(){
        calculate(this.fixInfo.rptNo,).then(res=>{
          if (res!=null && res.code===200){
            this.$message({
              message: '理算成功！',
              type: 'success',
              center: true,
              showClose: true
            })
          }
          detailsList(data).then(res => {
            if (res != null && res.code === 200 && res.rows.length > 0) {
              this.caseForm.caseData = res.rows
              this.caseForm.caseData.forEach(item => {
                item.isEdit = true
                if (item.minData.length > 0) {
                  item.minData.forEach(option => {
                    option.isEdit = true
                    option.selectFData = []
                    option.selectSData = []
                  })
                }
              })
            }
          })
          this.getCalSummary()
        }).catch(res=>{
          this.$message({
            message: '理算失败!',
            type: 'error',
            center: true,
            showClose: true
          })
        })
        let data = {
          rptNo: this.fixInfo.rptNo,
        }
      },
      save() {
        this.$refs.caseForm.validate((valid) => {
          if (valid) {
           let flag=true
           let flag2=true
            this.caseForm.caseData.forEach(item=>{
               if (item.payAmount!=item.calAmount && (item.remark!=null && item.remark!='' && item.remark!=undefined )){
                 flag=false
              }
              if (item.payConclusion==null || item.payConclusion=='' || item.payConclusion==undefined ){
                flag2=false
              }
            })
            if (flag){
              if (flag2){
                let item = {
                  billDetailList: this.caseForm.caseData
                }
                billDetailsSave(item).then(res => {
                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                  }
                  let data = {
                    rptNo: this.fixInfo.rptNo,
                  }
                  detailsList(data).then(res => {
                    if (res != null && res.code === 200 && res.rows.length > 0) {
                      res.rows.forEach(item => {
                        item.isEdit = true
                        if (item.minData.length > 0) {
                          item.minData.forEach(option => {
                            option.isEdit = true
                          })
                        }
                      })
                      this.caseForm.caseData = res.rows
                    }
                  }).catch(res => {
                  })
                  this.getCalSummary()
                }).catch(res => {
                  this.$message({
                    message: '保存失败!',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                })
              }else {
                return this.$message.warning(
                  "存在未录入的赔付结论！"
                )
              }
            }else {
              return this.$message.warning(
                "赔付金额与理算金额不相等时备注必录！"
              )
            }

          }
        })
      },
      getMinData(row, expandedRows) {
        this.loading = true
        //判断只有展开是做请求
        let data = {
          rptNo: this.fixInfo.rptNo,
          billId: row.billId
        }
        if (expandedRows.length > 0) {
          itemList(data).then(res => {
            if (res != null && res.code === 200 && res.rows.length > 0) {
              res.rows.forEach(item => {
                item.isEdit = true
                item.selectFData = []
                item.selectSData = []
              })
              this.caseForm.caseData.forEach((temp, index) => {
                if (temp.billId === row.billId) {
                  this.caseForm.caseData[index].minData = res.rows
                }
              })
            }
            this.loading = false
          })
        }
      },
      changeRemark(index, remark) {
        if (this.node === 'calculateReview') {
          this.index = index
          this.dialogVisable = true
          this.remarkForm.remark = remark
        }
      },
      changeDialogVisable() {
        this.dialogVisable = false
      },
      saveRemark() {
        this.caseForm.caseData[this.index].remark = this.remarkForm.remark.trim()
        this.dialogVisable = false
      },
      openAll() {
        this.loading = true
        let data = {
          rptNo: this.fixInfo.rptNo,
        }
        detailsList(data).then(res => {
          if (res != null && res.code === 200 && res.rows.length > 0) {
            this.caseForm.caseData = res.rows
            this.caseForm.caseData.forEach(item => {
              item.isEdit = true
              if (item.minData.length > 0) {
                item.minData.forEach(option => {
                  option.isEdit = true
                  option.selectFData = []
                  option.selectSData = []
                })
              }
            })
          }
          this.loading = false
        }).catch(res => {
          this.loading = false
        })
        this.isOpen = true
        let ids = this.caseForm.caseData.map(item => item.billId)
        this.expands = ids
      },
      closeAll() {
        this.isOpen = false
        this.expands = []
      },
      getRowKeys(row) {
        return row.billId
      },
      rowClick(row, column, event) {
        row.isEdit = false
      },
      rowMinClick(row, column, event) {
        if (this.node === 'calculateReview') {
          let data = {
            rptNo: this.fixInfo.rptNo
          }
          insurancePolicyList(data).then(res => {
            if (res != null && res.code === 200) {
              row.selectFData = res.rows
            }
          })
          let item = {
            riskCode: row.riskCode,
            feeItemCode: row.feeItemCode
          }
          responsibilityDetailsList(item).then(res => {
            if (res != null && res.code === 200) {
              row.selectSData = res.rows
            }
          }).catch(res => {
          })
          row.isEdit = false
        }
      },
      getSum(param) {
        const sums = []
        if (this.calSummaryData!==null && this.calSummaryData!==undefined && this.calSummaryData.sumBillAmount){
          this.isSum=true
          const {columns, data} = param;
          columns.forEach((column, index) => {
            if (index === 1) {
              sums[index] = '汇总信息';
              return;
            }
            switch (column.property) {
              case "billAmount":
                sums[index] = this.calSummaryData.sumBillAmount;// 账单金额
                break;
              case "hosDiscountAmount":
                sums[index] = this.calSummaryData.sumHosDiscountAmount;//折扣金额
                break;
              case "unableAmount":
                sums[index] = this.calSummaryData.sumUnableAmount;//不合理金额
                break;
              case "deduUsed":
                sums[index] = this.calSummaryData.sumDeduUsed;//免赔额
                break;
              case "calAmount":
                sums[index] =this.calSummaryData.calAmount;//理算金额
                break;
              case "payAmount":
                sums[index] = this.calSummaryData.sumPayAmount;//赔付金额
                break;
              case "refusedAmount":
                sums[index] = this.calSummaryData.sumRefusedAmount;//拒赔金额
                break;
              default:
                break;
            }
          });
        }
        return sums;
      },
      getSelectSData(riskCode, row, index) {
        let fData = {};
        fData = row.selectFData.find((item) => {
          return item.riskCode === riskCode;//筛选出匹配数据
        });
        this.caseForm.caseData[index].minData.policyNo = fData.policyNo
        this.caseForm.caseData[index].minData.riskName = fData.riskName
        let data = {
          riskCode: riskCode,
          feeItemCode: row.feeItemCode
        }
        responsibilityDetailsList(data).then(res => {
          if (res != null && res.code === 200) {
            row.selectSData = res.rows
          }
        }).catch(res => {
        })

      },
      changeMinData(dutyDetailCode, row, index) {
        let sData = {};
        sData = row.selectSData.find((item) => {
          return item.dutyDetailCode === dutyDetailCode;//筛选出匹配数据
        });
        this.caseForm.caseData[index].minData.dutyDetailCode = sData.dutyDetailCode
        this.caseForm.caseData[index].minData.dutyDetailName = sData.dutyDetailName
        this.caseForm.caseData[index].minData.dutyCode = sData.dutyCode
        this.caseForm.caseData[index].minData.planCode = sData.planCode
      },
      getCalSummary() {
        let data = {
          rptNo: this.fixInfo.rptNo
        }
        calSummary(data).then(res => {
          if (res != null && res.code === 200) {
            this.calSummaryData = res.data
          }
        })
      },
      cellStyle({row, column, rowIndex, columnIndex}) {
        if (rowIndex===0 && this.isSum){
          // 改变合计行样式
          const s_table = document.getElementsByClassName('el-table__footer-wrapper')[0]
          const child_tr = s_table.getElementsByTagName('tr')[0]
          child_tr.childNodes.forEach(item => {
            if (item.cellIndex === 0 || item.cellIndex === 1 || item.cellIndex === 2) {
              item.setAttribute('style', 'background: #dbad85')
            }
          })
        }
      },
      getDataCase(){
        let data = {
          rptNo: this.fixInfo.rptNo,
        }
        detailsList(data).then(res => {
          if (res != null && res.code === 200) {
            this.caseForm.caseData = res.rows
            this.caseForm.caseData.forEach(item => {
              item.isEdit = true
              if (item.minData.length > 0) {
                item.minData.forEach(option => {
                  option.isEdit = true
                  option.selectFData = []
                  option.selectSData = []
                })
              }
            })
          }
        }).catch(res => {
        })
      }
    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }

  .el-form ::v-deep .el-form-item__label {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .read-only.el-cascader ::v-deep .el-input.is-disabled .el-input__inner {
    border: none;
    color: #333;
    padding: 0;
    background: transparent;
    width: 100%;
  }

  .read-only.el-cascader ::v-deep .el-icon-arrow-down:before {
    display: none;
  }

  .long-select {
    width: 100%;
  }

</style>
