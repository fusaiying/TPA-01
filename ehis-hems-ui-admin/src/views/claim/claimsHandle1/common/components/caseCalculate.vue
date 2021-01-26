<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;案件理算</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;案件理算</i>
        <span style="float: right;">
        <el-button v-if="!isOpen" type="primary" :disabled="!collapsed" size="mini" @click="openAll">一键展开</el-button>
        <el-button v-else type="primary" :disabled="!collapsed" size="mini" @click="closeAll">一键收起</el-button>
        <el-button type="primary" :disabled="!collapsed" size="mini" @click="">赔付计算</el-button>
        <el-button type="primary" :disabled="!collapsed" size="mini" @click="save">保存</el-button>
      </span>
      </div>
    </div>
    <el-form ref="caseForm" :rules="caseRules" :model="caseForm" size="small" v-if="collapsed">
      <el-table
        v-loading="caseLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
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
                      <el-option v-for="option in selectFData" :key="option.policyNo"
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
                      <el-option v-for="option in selectSData" :key="option.dutyDetailCode"
                                 :label="option.dutyDetailName"
                                 :value="option.dutyDetailCode"/>
                    </el-select>
                  </el-form-item>
                  <span class="size" v-if="scope.row.isEdit">{{scope.row.policyNo}}-{{scope.row.riskName}}|{{scope.row.dutyDetailName}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="dutyDetailCode" label="费用项金额" width="90" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyDetailName" label="折扣金额" show-overflow-tooltip/>
              <el-table-column align="center" prop="applyReason" label="自费金额" show-overflow-tooltip/>
              <el-table-column align="center" prop="applyReason" label="部分自费" show-overflow-tooltip/>
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
        <el-table-column align="center" width="140" prop="billNo" label="账单号/发票号" show-overflow-tooltip/>
        <el-table-column align="center" prop="treatmentStartDate" label="就诊日期" show-overflow-tooltip>
          <!-- <template slot-scope="scope">
             <span>{{ scope.row.treatmentStartDate | changeDate }}~{{ scope.row.treatmentEndDate | changeDate }}</span>
           </template>-->
        </el-table-column>
        <el-table-column align="center" prop="billAmount" label="账单金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="hosDiscountAmount" label="折扣金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="unableAmount" label="不合理金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="ensureRegion" label="免赔额" show-overflow-tooltip/><!---->
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
            <span class="size" v-if="scope.row.isEdit">{{scope.row.payConclusion}}</span>
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
    addAccept,
    infoList,
    itemList,
    detailsList,
    responsibilityDetailsList,
    billDetailsSave
  } from '@/api/claim/handleCom'
  import adjustmentDetail from '../modul/adjustmentDetail'
  import Logo from "../../../../../layout/components/Sidebar/Logo"; //理算明细
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
      sonCalculateSelectData: Array,
    },
    watch: {
      sonCalculateData: function (newValue) {
        if (newValue != null && newValue.length > 0) {
          newValue.forEach(item => {
            item.isEdit = true
          })
         /* this.caseForm.caseData = newValue*/
        }
      },
      sonCalculateSelectData: function (newValue) {
        if (newValue != null && newValue.length > 0) {
         /* this.selectFData = newValue*/
        }
      }
    },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      },
      tableData: {
        handler: function (newName, oldName) {
          this.$nextTick(() => {
            // 改变合计行样式
            const s_table = document.getElementsByClassName('el-table__footer-wrapper')[0]
            // console.log(s_table)
            s_table.setAttribute('style', 'border: 1px solid #1A936F')
            const child_tr = s_table.getElementsByTagName('tr')[0]
            // console.log(child_tr)
            child_tr.childNodes.forEach(item => {
              item.setAttribute('style', 'border: 1px solid #1A936F')
            })
          })
        },
        immediate: true,
        deep: true
      }

    },
    data() {
      return {
        loading: true,
        dialogVisable: false,
        index: undefined,
        expands: [],
        selectFData: [
          {
            riskCode: 'C000101',
            policyNo: '01',
            riskName: '啊啊啊啊',
          }, {
            riskCode: 'C000102',
            policyNo: '02',
            riskName: '嘻嘻嘻嘻',
          }
        ],
        selectSData: [],
        caseForm: {
          caseData: [{
            isEdit: true,
            id: '1',
            remark: 'aa',
            billAmount: '哈哈哈',
            calAmount: '125',
            minData: [{
              feeItemName: '00001',
              feeItemCode:'ADDCN001',
              policyNo: '001',
              riskName: '啊啊啊啊',
              isEdit: true,
              riskCode: '001',
              dutyDetailCode: '001',
              dutyDetailName: '无敌'
            }],
          }, {
            isEdit: true,
            id: '2',
            remark: '',
            minData: [{
              feeItemCode:'ADDCN003',
              policyNo: '002',
              riskName: '啊啊啊啊',
              feeItemName: '00001',
              isEdit: true,
              riskCode: '002',
              dutyDetailName: '无敌',
              dutyDetailCode: '001'
            }],
          }]
        },
        caseRules: {
          payAmount: [{required: true, message: '赔付金额不能为空', trigger: 'blur'}],//赔付金额
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
      save() {
        this.$refs.caseForm.validate((valid) => {
          if (valid) {
            billDetailsSave(this.caseForm.caseData).then(res => {
              if(res!=null && res.code===200){
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
            }).catch(res=>{
              this.$message({
                message: '保存失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            })
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
        this.index = index
        this.dialogVisable = true
        this.remarkForm.remark = remark
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
          this.loading = false
        }).catch(res => {
          this.loading = false
        })
        this.isOpen = true
        let ids = this.caseForm.caseData.map(item => item.id)
        this.expands = ids
      },
      closeAll() {
        this.isOpen = false
        this.expands = []
      },
      getRowKeys(row) {
        return row.id
      },
      rowClick(row, column, event) {
        row.isEdit = false
      },
      rowMinClick(row, column, event) {
        row.isEdit = false
      },
      getSum(param) {
        const {columns, data} = param;
        const sums = [];

        columns.forEach((column, index) => {
          if (index === 1) {
            sums[index] = '汇总信息';
            return;
          }
          switch (column.property) {
            case "ensureRegion":
              sums[index] = (100).toFixed(2) + '元';
              break;
            case "payAmount":
              sums[index] = 100 + '元';
              break;
            default:
              break;
          }
        });
        return sums;
      },
      getSelectSData(riskCode,row,index) {
        let fData = {};
        fData = this.selectFData.find((item)=>{
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
            this.$set(this,'selectSData',res.rows)
          }
        }).catch(res => {
        })

      },
      changeMinData(dutyDetailCode, row, index) {
        let sData = {};
        sData = this.selectSData.find((item)=>{
          return item.dutyDetailCode === dutyDetailCode;//筛选出匹配数据
        });
        this.caseForm.caseData[index].minData.dutyDetailCode = sData.dutyDetailCode
        this.caseForm.caseData[index].minData.dutyDetailName = sData.dutyDetailName
        this.caseForm.caseData[index].minData.dutyCode = sData.dutyCode
        this.caseForm.caseData[index].minData.planCode = sData.planCode
      }


      /*  columnStyle({ row, column, rowIndex, columnIndex }) {
          if ((columnIndex ===1||columnIndex ===2 || columnIndex ===0)&& (rowIndex===1 || rowIndex===2)) {
            return 'background:red;'
          }
        },*/
    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }

  .el-form /deep/ .el-form-item__label {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .read-only.el-cascader /deep/ .el-input.is-disabled .el-input__inner {
    border: none;
    color: #333;
    padding: 0;
    background: transparent;
    width: 100%;
  }

  .read-only.el-cascader /deep/ .el-icon-arrow-down:before {
    display: none;
  }

  .long-select {
    width: 100%;
  }

</style>
