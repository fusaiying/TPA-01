<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">赔付明细</span>
          <span v-show="!activeNames.length"
                style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span v-if="node==='complex'||node==='spotCheck'||node==='correct'"
              style="position: absolute;right: 40px;top: 0;">
          <el-button :disabled="status==='show' || disabledExemptModul" type="primary" size="mini"
                     @click="openExemptModul">豁免({{exemptNum}})</el-button>
        </span>
        <el-form
          :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false"
          ref="baseForm" :rules="tableFormRules" :model="baseForm" size="mini">
          <span v-if="!(node==='complex'||node==='spotCheck'||node==='correct')"
                style="position: absolute;right: 40px;top: 0;">
            <el-button type="primary" size="mini" :disabled="disabledExemptModul" @click="openExemptModul">豁免({{exemptNum}})</el-button>
            <el-button type="primary" size="mini" @click="payCalculation">赔付计算</el-button>
            <el-button type="primary" size="mini" @click="savePayConclusion">保存</el-button>
          </span>
          <el-row>
            <el-table
              ref="table"
              :summary-method="getSummaries"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="Array.isArray(baseForm.tableData)?baseForm.tableData:[]"
              size="small"
              show-summary
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-table
                    ref="table"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="props.row.casebillslist"
                    size="small"
                    highlight-current-row
                    tooltip-effect="dark"
                    style="width: 100%;">
                    <el-table-column type="expand">
                      <template slot-scope="props">
                        <el-table
                          ref="table"
                          :header-cell-style="{color:'black',background:'#f8f8ff'}"
                          :data="props.row.casebillsdetaillist"
                          size="small"
                          highlight-current-row
                          tooltip-effect="dark"
                          style="width: 100%;">
                          <el-table-column align="center" prop="feetiem" label="费用项名称" show-overflow-tooltip/>
                          <el-table-column align="center" prop="amount" label="费用金额">
                            <template slot-scope="scope">
                              <span>{{scope.row.amount|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="paymentratio" label="赔付比例">
                            <template slot-scope="scope">
                              <span>{{scope.row.paymentratio|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="overallpayment" label="统筹支付">
                            <template slot-scope="scope">
                              <span>{{scope.row.overallpayment|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="thirdpartypayment" label="第三方支付">
                            <template slot-scope="scope">
                              <span>{{scope.row.thirdpartypayment|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="paybycategory" label="比例自付">
                            <template slot-scope="scope">
                              <span>{{scope.row.paybycategory|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="selfpayment" label="自费金额">
                            <template slot-scope="scope">
                              <span>{{scope.row.selfpayment|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="unreasonableamount" label="不合理费用">
                            <template slot-scope="scope">
                              <span>{{scope.row.unreasonableamount|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="unreasonableexplain" label="不合理原因"
                                           show-overflow-tooltip/>
                          <el-table-column align="center" prop="deductible" label="免赔额">
                            <template slot-scope="scope">
                              <span>{{scope.row.deductible|numFilter}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column align="center" prop="payableamount" label="赔付金额">
                            <template slot-scope="scope">
                              <span>{{scope.row.payableamount|numFilter}}</span>
                            </template>
                          </el-table-column>
                        </el-table>
                      </template>
                    </el-table-column>
                    <!--                    <el-table-column align="center" prop="invoiceno" label="发票号" show-overflow-tooltip/>-->
                    <el-table-column align="center" prop="invoiceno" label="发票号" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <span v-if="scope.row.pastClaimNo === ''">{{scope.row.invoiceno}}</span>
                        <el-tooltip v-else class="item" effect="dark" :content="'重复理赔，既往赔案号' + scope.row.pastClaimNo"
                                    placement="top-start">
                          <span style="color: red;">
                            <span>{{scope.row.invoiceno}}</span>
                            <span class="el-icon-warning"></span>
                          </span>
                        </el-tooltip>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="invoicetype" :formatter="getinvoicetype" label="发票类型"
                                     show-overflow-tooltip/>
                    <el-table-column align="center" prop="hospitalname" label="医院名称" show-overflow-tooltip/>
                    <el-table-column label="就诊区间" align="center" width="160">
                      <template slot-scope="scope">
                        <el-form-item>
                          <span style="font-size: 12px;">{{scope.row.visitdate}}至{{scope.row.dischargedate}}</span>
                        </el-form-item>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="paymentratio" label="赔付比例">
                      <template slot-scope="scope">
                        <span>{{scope.row.paymentratio|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="amount" label="发票总金额">
                      <template slot-scope="scope">
                        <span>{{scope.row.amount|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="overallpayment" label="统筹支付">
                      <template slot-scope="scope">
                        <span>{{scope.row.overallpayment|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="thirdpartypayment" label="第三方支付">
                      <template slot-scope="scope">
                        <span>{{scope.row.thirdpartypayment|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="paybycategory" label="比例自付">
                      <template slot-scope="scope">
                        <span>{{scope.row.paybycategory|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="selfpayment" label="自费金额">
                      <template slot-scope="scope">
                        <span>{{scope.row.selfpayment|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="unreasonableamount" label="不合理费用">
                      <template slot-scope="scope">
                        <span>{{scope.row.unreasonableamount|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="unreasonableexplain" label="不合理原因" show-overflow-tooltip/>
                    <el-table-column align="center" prop="deductible" label="免赔金额">
                      <template slot-scope="scope">
                        <span>{{scope.row.deductible|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="deductibleamount" label="抵扣垫付金额">
                      <template slot-scope="scope">
                        <span>{{scope.row.deductibleamount|numFilter}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="payableamount" label="赔付金额">
                      <template slot-scope="scope">
                        <span>{{scope.row.payableamount|numFilter}}</span>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column align="center" width="160" prop="policyno" label="保单号" style="position:relative">
                <template slot-scope="scope">
                  <span v-if="!scope.row.isAlreadyExempt">{{scope.row.policyno}}</span>
                  <el-tooltip v-else class="item" effect="dark" content="已豁免" placement="top-start">
                    <span style="color: red;">
                      <span>{{scope.row.policyno}}</span>
                      <span class="el-icon-warning"></span>
                    </span>
                  </el-tooltip>
                  <!-- <span v-if="scope.row.isAlreadyExempt" style="position:absolute;top:10px;right:8px;color:red">已豁免</span> -->
                </template>
              </el-table-column>
              <el-table-column align="center" width="100" prop="coveragename" label="险种名称"/>
              <el-table-column align="center" width="100" prop="getdutyname" label="责任名称"/>
              <el-table-column align="center" prop="paymentratio" label="赔付比例">
                <template slot-scope="scope">
                  <span>{{scope.row.paymentratio|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="amount" label="发票总金额">
                <template slot-scope="scope">
                  <span>{{scope.row.amount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="overallpayment" label="统筹支付">
                <template slot-scope="scope">
                  <span>{{scope.row.overallpayment|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="thirdpartypayment" label="第三方给付">
                <template slot-scope="scope">
                  <span>{{scope.row.thirdpartypayment|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="paybycategory" label="比例自付">
                <template slot-scope="scope">
                  <span>{{scope.row.paybycategory|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="selfpayment" label="自费金额">
                <template slot-scope="scope">
                  <span>{{scope.row.selfpayment|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="unreasonableamount" label="不合理费用">
                <template slot-scope="scope">
                  <span>{{scope.row.unreasonableamount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column label="不合理原因" width="100" prop="unreasonableexplain" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.unreasonableexplain'"
                                :rules="{ required: scope.row.unreasonableamount==='0.000'?false:true, message: '不能为空', trigger: ['blur', 'change'] }">
                    <el-input :disabled="scope.row.removeflag==='Y'" v-model="scope.row.unreasonableexplain"
                              style="width: 90px" placeholder="请输入" size="mini"/>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="deductible" label="免赔金额">
                <template slot-scope="scope">
                  <span>{{scope.row.deductible|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="deductibleamount" label="抵扣垫付金额" width="120">
                <template slot-scope="scope">
                  <span>{{scope.row.deductibleamount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="payableamount" label="理算金额">
                <template slot-scope="scope">
                  <span>{{scope.row.payableamount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="finalamount" label="实赔金额" width="160">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.finalamount'"
                                :rules="{ required: true, message: '不能为空', trigger: ['blur', 'change'] }">
                    <el-input-number v-model="scope.row.finalamount" :min="0" :precision="2" :step="0.01"
                                     style="width: 150px" class="item_width" autocomplete="off"
                                     :disabled="scope.row.disabledFinalamount" placeholder="请输入" clearable/>
                  </el-form-item>
                  <!-- <span>{{scope.row.finalamount|numFilter}}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="赔付结论" width="100" prop="conclusion" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.conclusion'" :rules="tableFormRules.conclusion">
                    <el-select :disabled="scope.row.removeflag==='Y'" v-model="scope.row.conclusion"
                               style="width: 90px;" placeholder="请选择"
                               @change="changeRe(scope.row.conclusion,scope.$index)" clearable>
                      <el-option v-for="item in scope.row.conclusionList" :label="item.dictLabel"
                                 :value="item.dictValue"
                                 :key="item.value"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="结论依据" width="100" prop="conclusionreason" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.conclusionreason'"
                                :rules="tableFormRules.conclusionreason">
                    <el-select :disabled="scope.row.removeflag==='Y' || scope.row.disabledCon == true"
                               v-model="scope.row.conclusionreason" style="width: 90px;" placeholder="请选择" clearable
                               @change="loadAll">
                      <el-option v-for="item in scope.row.reList" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.value"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="description" label="赔付说明" show-overflow-tooltip/>
              <el-table-column label="赔付描述" width="200" prop="explanation" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.explanation'"
                                :rules="tableFormRules.explanation">
                    <el-autocomplete
                      :disabled="scope.row.removeflag==='Y'"
                      v-model="scope.row.explanation"
                      :rows="1"
                      width="100px"
                      :fetch-suggestions="querySearch"
                      type="textarea"
                      show-word-limit
                      placeholder="请输入内容"
                      :maxlength="200"
                      @click.native="detRiskAndDuty(scope.row)"
                    >
                    </el-autocomplete>
                    <!-- <el-input v-model="scope.row.explanation" style="width: 90px" placeholder="请输入" size="mini" clearable/> -->
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" fixed="right" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <el-button type="text" v-if="scope.row.removeflag==='N'"
                               @click="delGetDutyResult(scope.row, scope.$index, 'Y')">移 除
                    </el-button>
                    <el-button type="text" v-if="scope.row.removeflag==='Y'"
                               @click="delGetDutyResult(scope.row, scope.$index, 'N')">恢 复
                    </el-button>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <exempt-modul v-model="exemptVis" :dictList="dictList" :node="node" :claimno='claimno'></exempt-modul>
  </el-card>
</template>
<script>
  import exemptModul from '../modul/exempt'
  import {
    delGetDutyResult,
    payCalculation,
    savePayConclusion,
    queryPolicyExemptionDetail,
    getTempalte,
    getContents,
    queryAccordWithExemption,
    queryExemptionCount,
    deleteExemptionDetail
  } from '@/api/claim/handleDeal'
  import {judgeBnf} from '@/api/claim/register'
  import Bus from '../../bus/bus'

  export default {
    //dicts: ['conclusion', 'conclusionreason', 'invoicetype', 'conclusionreason2', 'conclusionreason3', 'conclusionreason4', 'conclusionreason5'],
    components: {exemptModul},
    props: {
      dictList: Array,
      status: String,
      node: String,
      claimno: String,
      baseForm: {
        type: Object,
        default: () => {
          return {
            tableData: []
          }
        }
      }
    },
    filters: {
      numFilter(value) {
        let realVal = ''
        if (!isNaN(value) && value !== '' && value !== null) {
          // 截取当前数据到小数点后两位
          realVal = parseFloat(value).toFixed(2)
        } else {
          realVal = ''
        }
        return realVal
      }
    },
    inject: ['getPayDetail'],
    data() {
      return {
        submitFlag: false,
        calcFlag: false,
        exemptVis: false,
        activeNames: ['1'],
        showFlag: false,
        disabledCon: false,
        disabledExemptModul: false,
        exemptNum: 0,
        tableFormRules: {
          // unreasonableexplain: { required: true, message: '不能为空!', trigger: 'blur' },
          conclusion: {required: true, message: '不能为空!', trigger: 'blur'},
          conclusionreason: {required: true, message: '不能为空!', trigger: 'blur'},
          explanation: {required: true, message: '不能为空!', trigger: 'blur'}
        },
        expList: [],
        insurancecode: '',
        dutycode: '',
        conclusion: '',
        conclusionreason: '',
        count: [],
        conclusionOptions: [],
        conclusionreasonOptions: [],
        invoicetypeOptions: [],
        conclusionreason2Options: [],
        conclusionreason3Options: [],
        conclusionreason4Options: [],
        conclusionreason5Options: [],
      }
    }
    ,
    mounted() {
      this.loadAll()
      this.queryAccordWithExemption()
      this.queryExemptionCount()
      Bus.$on('updateExemptNum', data => {
        this.exemptNum = data
      })
      if (this.dictList != null && this.dictList.length != 0) {
        this.conclusionOptions = this.dictList.find(item => {
          return item.dictType == 'conclusion'
        }).dictDate
        this.conclusionreasonOptions = this.dictList.find(item => {
          return item.dictType == 'conclusionreason'
        }).dictDate
        this.invoicetypeOptions = this.dictList.find(item => {
          return item.dictType == 'invoicetype'
        }).dictDate
        this.conclusionreason2Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason2'
        }).dictDate
        this.conclusionreason3Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason3'
        }).dictDate
        this.conclusionreason4Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason4'
        }).dictDate
        this.conclusionreason5Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason5'
        }).dictDate
      }
    },
    methods: {
      openExemptModul() {
        this.exemptVis = true
      },
      detRiskAndDuty(row) {
        this.insurancecode = row.coveragecode
        this.dutycode = row.dutycode
        this.conclusion = row.conclusion
        this.conclusionreason = row.conclusionreason
        this.loadAll()
      },
      // 查询豁免是否可点击
      queryAccordWithExemption() {
        queryAccordWithExemption(this.claimno).then(res => {
          if (res.status == '1') {
            console.log(typeof (res.data), 4444444)
            if (res.data == 0) {
              this.disabledExemptModul = true
            } else {
              this.disabledExemptModul = false
            }
          } else {
            this.$message.error('查询豁免错误！')
          }
        })
      },
      //查询豁免条数
      queryExemptionCount() {
        queryPolicyExemptionDetail(this.claimno).then(res => {
          if (res.status == '1') {
            this.exemptNum = res.data
          } else {
            this.$message.error('查询豁免条数错误！')
          }
        })
      },
      deleteExemptionDetail() {
        deleteExemptionDetail(this.claimno).then(res => {
          if (res.status == '1') {

          } else {
            this.$message.error('更新豁免信息错误！')
          }
        }).then(() => {
          this.queryExemptionCount()
        })
      },
      loadAll() {
        // let type = '01'
        // getTempalte(type).then(res =>{
        //   if (res.status == '1') {
        //     this.expList = res.data || []
        //   } else {
        //     this.$message.error('查询模版消息错误！')
        //   }
        // })
        this.expList = []
        const params = {
          templatetype: '01',
          supplementarymode: '',
          insurancecode: this.insurancecode ? this.insurancecode : '',
          dutycode: this.dutycode ? this.dutycode : '',
          circulationvalue1: this.conclusion ? this.conclusion : '',
          circulationvalue2: this.conclusionreason ? this.conclusionreason : ''
        }
        getContents(params).then(res => {
          if (res!=null && res.status == '1') {
            res.data.forEach(item => {
              this.expList.push({value: item})
            })
            // this.expList = res.data || []
          }
        })
      },
      savePayConclusion() {
        let flag = this.baseForm.tableData.every((item) => {
          return item.removeflag == 'Y'
        })
        if (flag) {
          this.$message.warning('赔付明细中至少保留一条未移除状态数据！')
          return false
        }
        // if(this.calcFlag){
        let checkIdx = []
        this.baseForm.tableData.map((item, i) => {
          if (item.removeflag === 'N') {
            checkIdx.push({index: i, conclusion: item.conclusion})
          } else {

          }
        })
        let checkFlag = []
        checkIdx.map((item, index) => {
          this.$refs.baseForm.validateField(['tableData.' + item.index + '.conclusion',
            'tableData.' + item.index + '.unreasonableexplain',
            'tableData.' + item.index + '.explanation', 'tableData.' + item.index + '.conclusionreason'], empty => {
            if (empty) {
              checkFlag.push(0)
            }
          })
        })
        if (!checkFlag.includes(0)) {
          // let dutycode= this.baseForm.tableData[0].dutycode
          this.baseForm.tableData.map((item) => {
            item.claimno = this.claimno
            // item.dutycode = dutycode
            return item
          })
          savePayConclusion(this.baseForm.tableData).then(res => {
            if (res.status === '1') {
              this.$message({message: '操作成功！', type: 'success'})
              this.submitFlag = true
              this.getPayDetail(this.claimno)
              this.baseForm.tableData.map((item) => {
                this.count.push(item.finalamount)
              })
              var sum = 0
              for (var i = 0; i < this.count.length; i++) {
                sum = sum + this.count[i]
              }
              Bus.$emit('finalamountMethods', sum)
              sum = 0
              this.count = []
              this.queryAccordWithExemption()
              this.deleteExemptionDetail()
            } else {
              this.$message({message: res.data.errMsg, type: 'error'})
            }
          })
        }
        // } else {
        //   this.$message.warning('请先进行赔付计算！')
        // }
      },
      createFilter(queryString) {
        return (expList) => {
          return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        }
      },
      querySearch(queryString, cb) {
        let expList = this.expList
        let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
        cb(results ? results : [])
      },
      payCalculation() {
        judgeBnf(this.claimno).then(response => {
          if (response.status === '1') {
            if (response.data !== '') {
              this.$message({message: response.data, type: 'warning'})
            } else {
              payCalculation(this.claimno).then(res => {
                if (res.status === '1') {
                  this.$message({message: '操作成功！', type: 'success'})
                  this.submitFlag = false
                  // this.calcFlag = true
                  this.getPayDetail(this.claimno)
                  this.$emit("getPayInfo")
                  this.$emit("getPayeeInfo")
                } else {
                  if (res.data.errCode == 'CL008') {
                    this.$message({message: res.data.errMsg, type: 'warning'})
                  } else {
                    this.$message({message: res.data.errMsg, type: 'error'})
                  }
                }
              })
            }
          }
        })
      },
      changeRe(value, index) {
        if (value == 3) {
          this.baseForm.tableData[index].finalamount = 0
          this.baseForm.tableData[index].disabledFinalamount = true
        } else {
          this.baseForm.tableData[index].disabledFinalamount = false
          this.baseForm.tableData[index].finalamount = this.baseForm.tableData[index].payableamount
        }
        this.baseForm.tableData[index].reList = []
        if (value == '1') {
          this.baseForm.tableData[index].reList = this.conclusionreasonOptions
          // this.payInfoRules.conclusionreason.required = true
          this.baseForm.tableData[index].disabledCon = false
          this.tableFormRules.conclusionreason.required = true
        } else if (value == '2') {
          this.baseForm.tableData[index].reList = this.conclusionreasonOptions
          // this.tableFormRules.conclusionreason.required = true
          this.baseForm.tableData[index].disabledCon = false
        } else if (value == '3') {
          this.baseForm.tableData[index].reList = this.conclusionreason2Options
          // this.tableFormRules.conclusionreason.required = true
          this.baseForm.tableData[index].disabledCon = false
        } else if (value == '4') {
          this.baseForm.tableData[index].reList = this.conclusionreason3Options
          // this.tableFormRules.conclusionreason.required = true
          this.baseForm.tableData[index].disabledCon = false
        } else if (value == '5') {
          this.baseForm.tableData[index].reList = this.conclusionreason4Options
          // this.tableFormRules.conclusionreason.required = true
          this.baseForm.tableData[index].disabledCon = false
        } else {
          // this.$refs.baseForm.clearValidate(['tableData.'+index+'.conclusionreason'])
          this.baseForm.tableData[index].reList = this.conclusionreason5Options
          this.baseForm.tableData[index].disabledCon = false
          // this.baseForm.tableData[index].conclusionreason = ''
        }
      },
      // 获取发票类型码表
      getinvoicetype(row, col) {
        return this.dict.label.invoicetype[row.invoicetype]
      },
      delGetDutyResult(row, idx, removeflag) {
        let params = {
          claimno: this.claimno,
          incidentno: row.incidentno || '',
          policyno: row.policyno || '',
          getdutycode: row.getdutycode || '',
          dutycode: row.dutycode || '',
          removeflag
        }
        delGetDutyResult(params).then(res => {
          if (res.status === '1') {
            this.$refs.baseForm.clearValidate(['tableData.' + idx + '.conclusion',
              'tableData.' + idx + '.unreasonableexplain',
              'tableData.' + idx + '.explanation', 'tableData.' + idx + '.conclusionreason'])
            // this.calcFlag = false
            this.$message({message: '操作成功！', type: 'success'})
            this.getPayDetail(this.claimno)
          } else {
            this.$message({message: res.data.errMsg, type: 'error'})
          }
        })
      },
      getSummaries(param) {
        const {columns, data} = param
        const sums = []
        columns.forEach((column, index) => {
          if (index === 1) {
            sums[index] = '汇总信息'
            return
          }
          if (index === 16 || index === 17 || index === 18 || index === 19) {
            sums[index] = ''
            return
          }
          const values = data.map(item => Number(item[column.property]))
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
          } else {
            sums[index] = ''
          }
        })
        return sums
      }
    }
  }
</script>
<style scoped>
  .item-width {
    width: 200px;
  }

  .el-collapse {
    border: none;
  }

  .el-tabs /deep/ .el-tabs__item {
    width: 10%;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 20px;
  }

  .el-collapse /deep/ .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-weight: 360;
    font-size: 14px;
  }
</style>
