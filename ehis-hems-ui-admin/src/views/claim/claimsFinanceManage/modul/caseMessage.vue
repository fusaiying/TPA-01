<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>案件信息列表</span>
          <span style="float: right;">
            <el-button type="primary" size="mini" @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          :cell-style="changeCellStyle"
          style=" width: 100%;">
          <el-table-column align="center" prop="rptNo" label="报案号" show-overflow-tooltip/>
          <el-table-column align="center" prop="treatmentDate" label="就诊日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="name" label="姓名" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
          <el-table-column align="center" prop="billAmount" label="账单金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.billAmount}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.billAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="discountedAmount" label="折后金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.discountedAmount}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.discountedAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="advancePayment" label="先期给付" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.advancePayment}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.advancePayment}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="copay" label="自付额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.copay}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.copay}}</span>
            </template>
          </el-table-column>
          <el-table-column key="77" v-if="querys.status === 'public'" align="center" prop="payAmount" label="理赔金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.payAmount}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.payAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column key="78" v-if="querys.status === 'publicForeign'" align="center" prop="payAmount" label="理赔金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.payAmount}} CNY</span>
              <span v-else>{{scope.row.payAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column key="79" v-if="querys.status === 'public'" align="center" prop="debtAmount" label="追讨金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.debtAmount}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.debtAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column key="80" v-if="querys.status === 'publicForeign'" align="center" prop="debtAmount" label="追讨金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.debtAmount}} CNY</span>
              <span v-else>{{scope.row.debtAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column key="1" v-if="querys.status === 'publicForeign'" align="center" prop="exchangeRate"
                           label="汇率" show-overflow-tooltip>
            <template slot-scope="scope">
              <span >{{getNewData(scope.row.exchangeRate)}}</span>
            </template>
          </el-table-column>
          <el-table-column key="2" v-if="querys.status === 'publicForeign'" align="center" prop="payAmountForeign"
                           label="外币支付金额" width="110" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.payAmountForeign}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.payAmountForeign}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="borrowAmount" label="借款金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.caseStatus!='98'">{{scope.row.borrowAmount}} {{scope.row.currency}}</span>
              <span v-else>{{scope.row.borrowAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="caseStatus" label="案件状态" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(claim_statusOptions, scope.row.caseStatus)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="payStatus" label="支付状态" show-overflow-tooltip>
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
        <el-form ref="baseForm" :rules="baseFormRule" :model="baseForm" style="padding-bottom: 30px;margin-top:60px;"
                 label-width="200px"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="支付币种：" prop="currency">
                <span class="size">{{ baseForm.currency }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="支付总金额：" prop="payAmount">
                <span class="size">{{ baseForm.payAmount }} {{baseForm.currency}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="理赔总金额：" prop="calAmount">
                <span class="size">{{ baseForm.calAmount }} {{baseForm.currency}}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='public'" :span="8">
              <el-form-item label="开户行：" prop="bank">
                <span class="size">{{ baseForm.bank }}</span>
              </el-form-item>
            </el-col>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <el-form-item label="外币支付总金额：" prop="foreignPayAmount">
                <span class="size">{{ baseForm.foreignPayAmount }} {{baseForm.currency}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="账户名：" prop="bankName">
                <span class="size">{{ baseForm.bankName }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="账户号：" prop="bankNumber">
                <span class="size">{{ baseForm.bankNumber }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <el-form-item label="开户行：" prop="bank">
                <span class="size">{{ baseForm.bank }}</span>
              </el-form-item>
            </el-col>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <el-form-item label="交易编码：" prop="transactionCode">
                <span class="size">422000</span>
              </el-form-item>
            </el-col>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <el-form-item label="国际收支填报人：" prop="internationalCompletedBy">
                <el-input v-model="baseForm.internationalCompletedBy" class="item-width" maxlength="20" clearable
                          size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <el-form-item label="国际收支填报人电话：" prop="internationalCompletedPhone">
                <el-input v-model="baseForm.internationalCompletedPhone" class="item-width" maxlength="12" clearable
                          size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <el-form-item label="交易附言：" prop="transactionPostscript">
                <el-input v-model="baseForm.transactionPostscript" class="item-width" maxlength="1000" clearable
                          size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <div style="float: right;margin-top: 50px;margin-bottom: 10px">
            <!--<el-button v-if="querys.status==='publicForeign'" type="primary" size="mini" @click="aab">保存</el-button>-->
            <el-button type="primary" :disabled="isConfirmPayShow" size="mini" @click="confirmPay">确认支付</el-button>
            <el-button type="primary" :disabled="isBorrowShow" size="mini" @click="caseBorrow">案件借款
            </el-button>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {startPay, confirmPayment, rollback, borrowingCase, startForeignPay} from '@/api/claim/corporatePay'
  import {getUserInfo} from '@/api/claim/standingBookSearch'

  let dictss = [{dictType: 'case_pay_status'}, {dictType: 'claim_status'},]
  export default {
    data() {
      return {
        isConfirmPayShow: false,
        isBorrowShow: false,
        organCode: '',
        querys: {},
        tableData: [],
        baseFormRule: {
          internationalCompletedBy: {required: true, message: '国际收支填报人不能为空', trigger: 'blur'},
          internationalCompletedPhone: {required: true, message: '国际收支填报人电话不能为空', trigger: 'blur'},
          transactionPostscript: {required: true, message: '交易附言不能为空', trigger: 'blur'},
        },
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
          transactionCode: '',//交易编码
          internationalCompletedBy: '',// 国际收支填报人
          internationalCompletedPhone: '',// 国际收支填报人电话
          transactionPostscript: '',// 交易附言
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
              if (res.data.payFlag == 'false') {
                this.isConfirmPayShow = true
              } else {
                this.isConfirmPayShow = false
              }
              if (res.data.borrowFlag == 'false') {
                this.isBorrowShow = true
              } else {
                this.isBorrowShow = false
              }
            }
          })
        } else if (this.querys.status === 'publicForeign') {//对公外币支付
          startForeignPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
              this.baseForm.transactionCode = '422000'
              if (res.data.payFlag == 'false') {
                this.isConfirmPayShow = true
              } else {
                this.isConfirmPayShow = false
              }
              if (res.data.borrowFlag == 'false') {
                this.isBorrowShow = true
              } else {
                this.isBorrowShow = false
              }
            }
          })
        }
      }
      getUserInfo().then(res => {
        if (res != null && res.code === 200) {
          this.organCode = res.data.organCode
        }
      })
    },
    methods: {
      search() {
        if (this.querys.status === 'public') {//对公支付
          startPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
              if (res.data.payFlag == 'false') {
                this.isConfirmPayShow = true
              } else {
                this.isConfirmPayShow = false
              }
              if (res.data.borrowFlag == 'false') {
                this.isBorrowShow = true
              } else {
                this.isBorrowShow = false
              }
            }
          })
        } else if (this.querys.status === 'publicForeign') {//对公外币支付
          startForeignPay(this.querys.batchNo).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.data.caseInfoList
              this.baseForm = res.data.payment
              this.baseForm.transactionCode = '422000'
              if (res.data.payFlag == 'false') {
                this.isConfirmPayShow = true
              } else {
                this.isConfirmPayShow = false
              }
              if (res.data.borrowFlag == 'false') {
                this.isBorrowShow = true
              } else {
                this.isBorrowShow = false
              }
            }
          })
        }
      },
      confirmPay() {//确认支付
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            if (this.baseForm.claimFlag === '02' && this.baseForm.payAmount != this.querys.batchTotal) {
              this.$confirm(`支付总金额与批次总金额不符，请核实!`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let data = {
                  batchNo: this.querys.batchNo,
                  organCode: this.organCode,
                  caseInfoList: this.tableData,
                  payment: this.baseForm
                }
                confirmPayment(data).then(res => {
                  if (res != null && res.data === 1) {
                    this.$message({
                      message: '提交成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                    this.$store.dispatch("tagsView/delView", this.$route);
                    this.$router.go(-1)
                  } else if (res != null && res.data === 2) {
                    return this.$message.warning(
                      "此批次存在回退案件，请结案后进行支付！"
                    )
                  } else if (res != null && res.data === 3) {
                    return this.$message.warning(
                      "批次下案件币种不统一，请核实！"
                    )
                  }
                  this.search()
                })

              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消！'
                })
              })
            } else {
              let data = {
                batchNo: this.querys.batchNo,
                organCode: this.organCode,
                caseInfoList: this.tableData,
                payment: this.baseForm
              }
              confirmPayment(data).then(res => {
                if (res != null && res.data === 1) {
                  this.$message({
                    message: '提交成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$store.dispatch("tagsView/delView", this.$route);
                  this.$router.go(-1)
                } else if (res != null && res.data === 2) {
                  return this.$message.warning(
                    "此批次存在回退案件，请结案后进行支付！"
                  )
                } else if (res != null && res.data === 3) {
                  return this.$message.warning(
                    "批次下案件币种不统一，请核实！"
                  )
                }
                this.search()
              })
            }
          }
        })
      },
      backspace(row) {//回退
        if (row.caseProp == '02') {
          return this.$message.warning(
            " 核心案件不允许进行回退，请核实！"
          )
        } else {
          if ((row.caseStatus != '08' && row.caseStatus != '99') || row.payStatus === '02' || row.payStatus === '03') {
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
              this.search()
            })
          }
        }

      },
      caseBorrow() {//案件借款
        if (this.baseForm.claimFlag === '01') {//非全陪
          //按钮置灰
          return this.$message.warning(
            "批次医院为非全赔医院，不可借款！"
          )
        } else {
          if (this.baseForm.payAmount != this.querys.batchTotal) {
            this.$confirm(`支付总金额与批次总金额不符，请核实!`, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              let data = {
                batchNo: this.querys.batchNo,
                organCode: this.organCode,
                caseInfoList: this.tableData,
                payment: this.baseForm
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
                } else if (res != null && res.data === 4) {
                  return this.$message.warning(
                    "不存在可以借款的案件！"
                  )
                }
                this.search()
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消！'
              })
            })
          } else {
            let data = {
              batchNo: this.querys.batchNo,
              caseInfoList: this.tableData,
              payment: this.baseForm
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
              this.search()
            })

          }
        }


      },
      listExport() {
        let data = {
          batchNo: this.querys.batchNo
        }
        if (this.querys.status === 'public') {
          this.isListExport = true
          this.download('finance/pay/export', data, `caseMessage_${new Date().getTime()}.xlsx`).catch(res => {
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        } else if (this.querys.status === 'publicForeign') {
          this.isListExport = true
          this.download('finance/pay/exportForeign', data, `caseMessageForeign_${new Date().getTime()}.xlsx`).catch(res => {
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }
      },
      changeCellStyle(rows, column, rowIndex, columnIndex) {
        if (this.querys.status === 'public') {
          /*if (){//当系统日期-该批次的交单日期>=网络医院维护的该医院的付款期限，该笔批次在对公支付查询页面飘红显示
            return 'color: red'  // 修改的样式
          } else{
            return ''
          }*/
        } else if (this.querys.status === 'publicForeign') {
          if (rows.row.flag === 'N') {
            return 'color: red'  // 修改的样式
          } else {
            return ''
          }
        }
      },
      getNewData(value) {
        if (value=='' || value==null || value==undefined){
          value=0
        }
        let tempVal = parseFloat(value).toFixed(2)
        return tempVal
      }
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
    width: 170px;
    text-align: right;
  }
</style>
