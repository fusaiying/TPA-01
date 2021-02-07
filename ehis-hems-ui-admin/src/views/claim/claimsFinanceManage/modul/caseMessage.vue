<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>案件信息列表</span>
          <span style="float: right;">
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="rptNo" label="报案号" show-overflow-tooltip/>
          <el-table-column align="center" prop="treatmentDate" label="就诊日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="name" label="姓名" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
          <el-table-column align="center" prop="billAmount" label="账单金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.billAmount}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="discountedAmount" label="折后金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.discountedAmount}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="advancePayment" label="先期给付" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.advancePayment}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="copay" label="自付额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.copay}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="payAmount" label="理赔金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.payAmount}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="debtAmount" label="追讨金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.debtAmount}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="exchangeRate" label="汇率" show-overflow-tooltip/>
          <el-table-column align="center" prop="payAmountForeign" label="外币支付金额" width="110" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.payAmountForeign}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="borrowAmount" label="借款金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.borrowAmount}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="caseStatus" label="案件状态" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(claim_statusOptions, scope.row.caseStatus)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="companyName" label="payStatus" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(case_pay_statusOptions, scope.row.payStatus)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="backspace(scope.row)">回退</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-form ref="caseForm" style="padding-bottom: 30px;margin-top:60px;" label-width="150px"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">支付币种：</span><span class="info_span">{{ baseForm.currency }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">支付总金额：</span><span class="info_span">{{ baseForm.payAmount }} {{baseForm.currency}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">理赔总金额：</span><span class="info_span">{{ baseForm.calAmount }} {{baseForm.currency}}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='public'" :span="8">
              <span class="info_span_col to_right">开户行：</span><span class="info_span">{{ baseForm.bank }}</span>
            </el-col>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <span class="info_span_col to_right">外币支付总金额：</span><span class="info_span">{{ baseForm.foreignPayAmount }} {{baseForm.currency}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">账户名：</span><span class="info_span">{{ baseForm.bankName }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">账户号：</span><span class="info_span">{{ baseForm.bankNumber }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <span class="info_span_col to_right">开户行：</span><span class="info_span">{{ baseForm.bank }}</span>
            </el-col>
          </el-row>

          <div style="float: right;margin-top: 50px;margin-bottom: 10px">
            <el-button v-if="querys.status==='publicForeign'" type="primary" size="mini" @click="">保存</el-button>
            <el-button type="primary" size="mini" @click="confirmPay">确认支付</el-button>
            <el-button type="primary" :disabled="baseForm.claimFlag==='01'" size="mini" @click="caseBorrow">案件借款
            </el-button>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {startPay, confirmPayment, rollback, borrowingCase, startForeignPay} from '@/api/claim/corporatePay'

  let dictss = [{dictType: 'case_pay_status'}, {dictType: 'claim_status'},]
  export default {
    data() {
      return {
        querys: {},
        tableData: [],
        baseForm: {
          batchNo: undefined,
          currency: undefined,
          payAmount: undefined,// 支付总金额
          calAmount: undefined,//理赔总金额
          bank: undefined,//开户行
          bankName: undefined,//账户名
          bankNumber: undefined,//账户号
          claimFlag: undefined,//是否仅结算理赔责任
          foreignPayAmount: undefined,//外币支付总金额
        },
        isListExport: false,
        dictList: [],
        claim_statusOptions: [],
        case_pay_statusOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.case_pay_statusOptions = this.dictList.find(item => {
        return item.dictType === 'case_pay_status'
      }).dictDate
      this.claim_statusOptions = this.dictList.find(item => {
        return item.dictType === 'claim_status'
      }).dictDate
      if (this.$route.query) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        if (this.querys.status === 'public') {//对公支付
          startPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
            }
          })
        } else if (this.querys.status === 'publicForeign') {//对公外币支付
          startForeignPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
            }
          })
        }
      }
    },
    methods: {
      search() {
        if (this.querys.status === 'public') {//对公支付
          startPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
            }
          })
        } else if (this.querys.status === 'publicForeign') {//对公外币支付
          startForeignPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
            }
          })
        }
      },
      confirmPay() {//确认支付
        if (this.baseForm.claimFlag === '02' && this.baseForm.payAmount !== this.querys.batchTotal) {
          return this.$message.warning(
            "支付总金额与批次总金额不符，请核实！"
          )
        } else {
          let data={
            batchNo: this.querys.batchNo,
            caseInfoList:this.tableData,
            payment:this.baseForm
          }
          confirmPayment(data).then(res => {
            if (res != null && res.data === 1) {
              this.$message({
                message: '提交成功！',
                type: 'success',
                center: true,
                showClose: true
              })
            } else if (res != null && res.data === 2) {
              return this.$message.warning(
                "此批次存在回退案件，请结案后进行支付！"
              )
            } else if (res != null && res.data === 3) {
              return this.$message.warning(
                "批次下案件币种不统一，请核实！"
              )
            }
          })
          this.search()
        }


      },
      backspace(row) {//回退
        if (row.caseStatus === '97' || row.caseStatus === '98' || row.payStatus === '02' || row.payStatus === '03') {
          return this.$message.warning(
            " 当前案件不允许进行回退，请核实！"
          )
        } else {
          rollback(row.rptNo).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '回退成功！',
                type: 'success',
                center: true,
                showClose: true
              })
            } else {
              this.$message({
                message: '回退失败',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })
          this.search()
        }
      },
      caseBorrow() {//案件借款
        if (this.baseForm.claimFlag === '01') {//非全陪
          //按钮置灰
        } else {
          if (this.baseForm.payAmount !== this.querys.batchTotal) {
            return this.$message.warning(
              "支付总金额与批次总金额不符，请核实！"
            )
          } else {
            let data = {
              batchNo: this.querys.batchNo,
              caseInfoList:this.tableData,
              payment:this.baseForm
            }
            borrowingCase(data).then(res => {
              if (res != null && res.data === 1) {
                this.$message({
                  message: '提交成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
              } else if (res != null && res.data === 2) {
                return this.$message.warning(
                  "此批次存在回退案件，请结案后进行支付！"
                )
              } else if (res != null && res.data === 3) {
                return this.$message.warning(
                  "批次下案件币种不统一，请核实！"
                )
              }
            })
            this.search()
          }
        }


      },
      listExport() {
        if (this.querys.status === 'public') {
          this.isListExport = true
          this.download('finance/pay/export', {
            ...query
          }, `FYX_${new Date().getTime()}.xlsx`).catch(res => {
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        } else if (this.querys.status === 'publicForeign') {
          this.isListExport = true
          this.download('finance/pay/exportForeign', {
            ...query
          }, `FYX_${new Date().getTime()}.xlsx`).catch(res => {
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

  ::v-deep.info_span_col {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #606266;
    line-height: 40px;
    padding: 0 12px 0 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }

  ::v-deep.to_right {
    width: 130px;
    text-align: right;
  }
</style>
