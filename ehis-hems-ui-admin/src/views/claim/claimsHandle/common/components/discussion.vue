<template>

    <el-card class="box-card" style="margin-top: 10px;">
      <div style="position: relative">
        <div slot="header" class="clearfix">
          <div style="width: 100%;cursor: pointer;">
            <span id="span1" :class="[isActiveSpan1?'span-tab is-active':'span-tab']" @click="activeFun">赔付结论</span>
            <span id="span2" :class="[isActiveSpan2?'span-tab is-active':'span-tab']" @click="activeFun">协谈</span>
            <span id="span3" :class="[isActiveSpan3?'span-tab is-active':'span-tab']" @click="activeFun">调查</span>
            <div style="float: right;">
              <el-button v-if="isButtonShow && conSave" type="primary" @click="updateCalInfo" size="mini" >保存 </el-button>
              <el-button v-if="isButtonShow && node!=='sport'" type="primary" @click="examineSave" size="mini">审核完毕
              </el-button>
              <el-button v-if="node!=='sport'" size="mini" type="primary" @click="backClaimCase">退回受理</el-button>

              <el-button v-if="isButtonShow && node==='sport'" type="primary" @click="caseCheckOver" size="mini">抽检完毕
              </el-button>
              <el-button v-if="node==='sport'" type="primary" @click="backToCalculate" size="mini">退回审核</el-button>
            </div>
          </div>
        </div>
        <el-divider/>

        <!-- 赔付结论 start -->
        <el-form v-if="isActiveSpan1" ref="conclusionForm" :model="conclusionForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" :rules="conRules"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">账单金额：</span><span class="info_span">{{ conclusionInfo.sumBillAmount }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">折扣金额：</span><span class="info_span money_class">{{ conclusionInfo.sumHosDiscountAmount }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">赔付金额：</span><span class="info_span money_class">{{ conclusionInfo.payAmount }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">拒赔金额 ：</span><span class="info_span money_class">{{ conclusionInfo.refusedAmount }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">追讨金额：</span><span class="info_span money_class">{{ conclusionInfo.debtAmount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">本次支付差额：</span><span class="info_span money_class">{{ conclusionInfo.paymentDifference }}</span>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="8">
            <!--  <span class="info_span_col to_right">账单币种：</span>-->
              <el-form-item label="账单币种：" prop="billCurrency">
                <el-select  size="mini" v-model="conclusionForm.billCurrency" class= "el-select item-width el-select--mini" placeholder="请选择">
                  <el-option  v-for="dict in currencys" :key="dict.dictValue"  :label="dict.dictValue+' - '+dict.dictLabel"  :value="dict.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">汇率：</span><span class="info_span money_class">{{ conclusionInfo.exchangeRate}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">外币给付金额：</span><span class="info_span money_class"></span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="赔付结论：" prop="payConclusion">
                <el-select  size="mini" v-model="conclusionForm.payConclusion" class= "el-select item-width el-select--mini" placeholder="请选择" @change="payConclusionChange">
                  <el-option v-for="dict in conclusionSelect" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="拒赔原因：" prop="refusedReason">
              <el-select  size="mini" v-model="conclusionForm.refusedReason" class= "el-select item-width el-select--mini" placeholder="请选择">
                <el-option v-for="dict in rejectedReasons" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="客户备注：" prop="remark">
                <el-input  style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                          v-model="conclusionForm.remark" class="item-width" clearable size="mini"
                          placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="核赔依据：" prop="claimCheck">
                <el-input style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                          v-model="conclusionForm.claimCheck" class="item-width" clearable size="mini"
                          placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
        <!-- 赔付结论 end -->

        <!-- 协谈 start -->
        <el-form v-if="isActiveSpan2" ref="discussionForm" :inline="true" :model="discussionForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px"  :rules="disRules"  label-position="right" size="mini">
          <el-row>
            <el-col :span="20" :xs="24">
                <el-form-item label="协谈类型：" prop="discType">
                  <el-select v-model="discussionForm.discType" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="item in negotiationTypes" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
                  </el-select>
                </el-form-item>

              <el-form-item  prop="discSubType">
                <el-select v-model="discussionForm.discSubType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in negotiationSubTypes" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="转出意见：" prop="disView">
                <el-input style="min-width: 700px" col="2" type="textarea" row="4" maxlength="1000" v-model="discussionForm.disView" clearable size="mini"  placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

          <!--保存 start-->
          <el-row >
            <el-col :span="6" :offset="12">
              <el-button type="primary" size="mini" @click="discussionSave">保存</el-button>
            </el-col>
          </el-row>
          <!--保存 end-->

        </el-form>
        <!-- 协谈 end -->

        <!-- 调查 start -->
        <el-form v-if="isActiveSpan3" ref="surveyForm" :model="surveyForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" :rules="surRules"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">调查任务号：</span><span class="info_span_col">{{ surveyInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">被保人：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">性别：</span><span class="info_span_col">{{ surveyInfo.contractName }}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">证件类型：</span><span class="info_span_col">{{ surveyInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">证件号码：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">出险日期：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">事故地点：</span><span class="info_span_col">{{ surveyInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">事故经过：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="提调类型：" prop="invType">
                <el-select v-model="surveyForm.invType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in dispatchTypes" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="提调原因：" prop="invReason">
                <el-select v-model="surveyForm.invReason" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in initiateReasons" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="调查机构：" prop="invOrganCode">
                <el-select v-model="surveyForm.invOrganCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in inquiryOrg" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="提调机构：" prop="organCode">
                <el-select v-model="surveyForm.organCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in initiateOrg" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="保单号：" prop="policyNo">
                <el-select v-model="surveyForm.policyNo" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="dict in policyNos"
                             :key="dict.policyNo"
                             :label="dict.policyNo"
                             :value="dict.policyNo"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="23">
              <el-form-item label="提调事项：" :style="{width:'100%'}" prop="invView">
                <el-input v-model="surveyForm.invView" :class="['long-input']" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!--保存 start-->
          <el-row >
            <el-col :span="6" :offset="20">
              <el-button type="primary" size="mini" @click="inQuireSaveFun" >保存</el-button>
              <el-button type="primary" size="mini"  @click="inQuireConfirmFun">确认提调</el-button>
              <el-button type="primary" size="mini"  @click="formReset('surveyForm')">重置</el-button>
            </el-col>
          </el-row>
          <!--保存 end-->

        </el-form>
        <!-- 调查 end -->
      </div>
    </el-card>

</template>

<script>

  let that = this;
  import { addDiscussion,
          updateCal,
          finishClaimCase ,
          backToClaimCase,
          inQuireConfirm,
          inQuireSave,
          calInfo,
          verifyProReset,
          verifyCurrency,
          addRecoveryInfo,
  } from '@/api/handel/common/api'
  import {editCaseCheckBack, editCaseCheck} from '@/api/claim/sportCheck'

  export default {
    components: {
    },
    props: {
      value: {
        type: Boolean,
        default: false
      },
      policySelectData: {
        type: Object,
        default: function () {
          return {}
        }
      },
      fixInfo:Object,
      node:String,

    },
    watch: {
      policySelectData: function (newValue) {
        this.policyNos = newValue;
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue;
        this.rptNo = this.fixInfoData.rptNo;
        this.batchNo = this.fixInfoData.batchNo;
        if(this.rptNo != '') {
          this.getCalInfo()
        }
      },
      value: function (newValue) {
      },
    },
    data() {
      const checkRefusedReason = (rule, value, callback) => {
        if(this.conclusionForm.payConclusion != '' && '05' == this.conclusionForm.payConclusion) {
          if (!value) {
            callback(new Error("拒赔原因必填"));// XXXXXXXXXXXX
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      return {
        policyNos :[],
        conSave:false,
        rptNo:'',
        batchNo:'',
        fixInfoData:'',
        isButtonShow:true,
        //赔付结论信息 start
        conclusionInfo:{
          sumBillAmount:'', // 账单金额
          sumHosDiscountAmount:'',// 折扣金额
          payAmount:'', // 赔付金额
          refusedAmount:'', //拒赔金额
          debtAmount:'', // 追讨金额
          paymentDifference:'',// 本次支付差额
          exchangeRate:'', // 汇率
                          //外币给付金额
          isAppeal:'', //是否是申诉案件
          insuredNo:'', // 被保人客户号
          payConclusion:'', //赔付结论

        },
        conclusionForm:{
          billCurrency:'',
          payConclusion:'',
          refusedReason:'',
          remark:'',
          claimCheck:'',
        },
        //赔付结论信息 end
        conRules: {
          billCurrency: {trigger: ['change'], required: true, message: '账单币种必填'},
          payConclusion: {trigger: ['change'], required: true, message: '赔付结论必填'},
          refusedReason: {trigger: ['change'], required: false, validator: checkRefusedReason},
          remark: {trigger: ['change'], required: true, message: '客户备注必填'},
          claimCheck: {trigger: ['change'], required: true, message: '核赔依据必填'},

        },

        //协谈信息 start
        discussionForm:{
          discType:'',
          disView:'',
          discSubType:'',
        },
        disRules: {
          discType: {trigger: ['change'], required: true, message: '协谈类型必填'},
          disView: {trigger: ['change'], required: true, message: '转出意见必填'},
          discSubType: {trigger: ['change'], required: true, message: '协谈细类必填'},
        },
        //协谈信息 end

        //调查信息 start
        surveyInfo:{

        },
        surveyForm:{
          invType:'',
          invReason:'',
          invOrganCode:'',
          organCode:'',
          policyNo:'',
          invView:'',
        },
        surRules: {
          invType: {trigger: ['change'], required: true, message: '提调类型必填'},
          invReason: {trigger: ['change'], required: true, message: '提调原因必填'},
          invOrganCode: {trigger: ['change'], required: true, message: '调查机构必填'},
          organCode: {trigger: ['change'], required: true, message: '提调机构必填'},
          policyNo: {trigger: ['change'], required: true, message: '保单号必填'},
          invView: {trigger: ['change'], required: true, message: '提调事项必填'},
        },

        //tab 切换
        loading:false,
        isActiveSpan1:true,
        isActiveSpan2:false,
        isActiveSpan3:false,

        //协谈类型
        negotiationTypes:[],
        //提调类型
        dispatchTypes:[],
        //提调原因
        initiateReasons:[],
        //调查机构
        inquiryOrg:[],
        //提调机构
        initiateOrg:[],
        //币种
        currencys :[],
        //赔付结论
        conclusionSelect:[],
        //拒赔原因
        rejectedReasons :[],
        //协谈细类
        negotiationSubTypes:[]

      }
    },

    created() {
    },
    mounted() {
      this.getDicts("rejected_reasons").then(response => {
        this.rejectedReasons = response.data;
      });
      //赔付结论 conclusion
      this.getDicts("conclusion").then(response => {
        this.conclusionSelect = response.data;
      });
      this.getDicts("claim_currency").then(response => {
        this.currencys = response.data;
      });
      //协谈类型
      this.getDicts("negotiation_type").then(response => {
        this.negotiationTypes = response.data;
      });
      //协谈细类
      this.getDicts("negotiation_sub_type").then(response => {
        this.negotiationSubTypes = response.data;
      });
      //提调类型
      this.getDicts("dispatch_type").then(response => {
        this.dispatchTypes = response.data;
      });
      //提调原因
      this.getDicts("initiate_reasons").then(response => {
        this.initiateReasons = response.data;
      });
      //调查机构
      this.getDicts("inquiry_org").then(response => {
        this.inquiryOrg = response.data;
      });
      //提调机构
      this.getDicts("initiate_org").then(response => {
        this.initiateOrg = response.data;
      });
    },
    computed: {
    },
    methods: {
      payConclusionChange(value){
        if(value != '05'){
          this.conclusionForm.refusedReason = '';
        }
      },
      discussionSave(){

        this.$refs.discussionForm.validate((valid) => {
          if (valid) {
            if(this.rptNo == '') {
              return false;
            }

            const params = {
              rptNo : this.rptNo,
              discType:this.discussionForm.discType,
              disView:this.discussionForm.disView,
              discSubType:this.discussionForm.discSubType,
            };

            addDiscussion(params).then(res => {
              console.log(res);
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
              } else {
                this.$message({
                  message: '保存成功！',
                  type: 'error',
                  center: true,
                  showClose: true
                });
              }
            });
            }
          })
      },

      // 赔付结论保存
      updateCalInfo(){
          this.conSave = false
          this.$refs.conclusionForm.validate((valid) => {
            if (valid) {
              if(this.rptNo == '') {
                return false;
              }
              const params = {
                rptNo : this.rptNo,
                billCurrency:this.conclusionForm.billCurrency,
                payConclusion:this.conclusionForm.payConclusion,
                refusedReason:this.conclusionForm.refusedReason,
                remark:this.conclusionForm.remark,
                claimCheck:this.conclusionForm.claimCheck,
                // calAmount:'0',
                // payAmount:this.conclusionInfo.payAmount,
                // refusedAmount:this.conclusionInfo.refusedAmount,
                // debtAmount:this.conclusionInfo.debtAmount,
              };

              updateCal(params).then(res => {
                if (res.code == '200') {
                  this.getCalInfo();
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  });
                } else {
                  this.conSave = true
                  this.$message({
                    message: '保存失败！',
                    type: 'error',
                    center: true,
                    showClose: true
                  });
                }
              });
            } else {
              this.conSave = true
            }
          })
      },

      // 赔付结论  审核完毕
      examineSave(){
        if(this.rptNo == '') {
          return false;
        }

        if(this.conclusionInfo.payConclusion == ''){
          this.$message.info('请先保存再进行审核！')
          return false;
        }
        //币种一致
        const param = {
          rptNo : this.rptNo,
          batchNo: this.batchNo,
          billCurrency: this.conclusionForm.billCurrency,
        };
        verifyCurrency(param).then(res => {
          let vm = this;
          if(res.code == '200' && res.msg != '操作成功') {
            this.$confirm(res.msg, "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "info"
            }).then(function () {
              vm.verifyProReset();
            }).then(() => {
            }).catch(function (error) {
              console.log(error)
            });
          } else if (res.code == '200' ){
            vm.verifyProReset();
          }
        });
      },

      verifyProReset(){
        const params = {
          rptNo : this.rptNo,
          batchNo: this.batchNo,
          claimCheck:this.conclusionForm.claimCheck,
          remark:this.conclusionForm.remark,
        };

        verifyProReset(params).then(res => {
          let vm = this;
          if(res.code == '200' && res.msg != '操作成功') {
            this.$confirm(res.msg, "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "info"
            }).then(function () {
              vm.addRecoveryInfo();
            }).then(() => {
            }).catch(function (error) {
              console.log(error)
            });
          } else if (res.code == '200' ){
            vm.addRecoveryInfo();
          }
        });
      },
      addRecoveryInfo(){
        if(this.conclusionInfo.debtAmount > 0) {
          const params = {
            rptNo : this.rptNo,
            insuredNo: this.conclusionInfo.insuredNo,
            debtAmount:this.conclusionInfo.debtAmount,
          };
          addRecoveryInfo(params).then(res => {
            console.log(res);
          });
        }
        const params = {
          rptNo : this.rptNo,
          payConclusion: this.conclusionInfo.payConclusion,
          isAppeal: this.conclusionInfo.isAppeal,
        };
        // 审核完毕
        finishClaimCase(params).then(res => {
          if (res.code == '200') {
            this.$message({
              message: '审核成功！',
              type: 'success',
              center: true,
              showClose: true
            });
          } else {
            this.$message({
              message: '审核失败！',
              type: 'error',
              center: true,
              showClose: true
            });
          }
        });
      },
      // 赔付结论  退回受理
      backClaimCase(){
        if(this.rptNo == '') {
          return false;
        }
        const params = {};
        params.rptNo = this.rptNo;
        params.claimCheck = this.conclusionForm.claimCheck;
        params.remark = this.conclusionForm.remark;

        backToClaimCase(params).then(res => {
          console.log(res);
          if (res.code == '200') {
            this.$message({
              message: '退回成功！',
              type: 'success',
              center: true,
              showClose: true
            });
          } else {
            this.$message({
              message: '退回失败！',
              type: 'error',
              center: true,
              showClose: true
            });
          }
        });
      },
      //调查保存
      inQuireSaveFun(){
        this.$refs.surveyForm.validate((valid) => {
          if (valid) {
            if(this.rptNo == '') {
              return false;
            }
            const params = this.surveyForm;
            params.rptNo = this.rptNo;

            inQuireSave(params).then(res => {
              console.log(res);
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
              } else {
                this.$message({
                  message: '保存失败！',
                  type: 'error',
                  center: true,
                  showClose: true
                });
              }
            });
          }
        })
      },
      // 调查 确认提调
      inQuireConfirmFun(){
        if(this.rptNo == '') {
          return false;
        }
        const params = {};
        params.rptNo = this.rptNo;

        inQuireConfirm(params).then(res => {
          console.log(res);
          if (res.code == '200') {
            this.$message({
              message: '提调成功！',
              type: 'success',
              center: true,
              showClose: true
            });
          } else {
            this.$message({
              message: '提调失败！',
              type: 'error',
              center: true,
              showClose: true
            });
          }
        });
      },
      getCalInfo() {
        calInfo(this.rptNo).then(res => {
          if(res.code == '200' && res.data) {
            this.conclusionInfo = res.data;
            if(this.conclusionInfo.payConclusion == '') {
              this.conSave = true;
            }
            if(this.conclusionInfo.billCurrency != '' && this.conclusionInfo.billCurrency != null) {
              this.conclusionForm.billCurrency = this.conclusionInfo.billCurrency; // 账单币种
            }
            if(this.conclusionInfo.payConclusion != '' && this.conclusionInfo.payConclusion != null) {
              this.conclusionForm.payConclusion = this.conclusionInfo.payConclusion; // 赔付结论
            }
            if(this.conclusionInfo.refusedReason != '' && this.conclusionInfo.refusedReason != null) {
              this.conclusionForm.refusedReason = this.conclusionInfo.refusedReason; // 拒赔原因
            }
            if(this.conclusionInfo.remark != '' && this.conclusionInfo.remark != null) {
              this.conclusionForm.remark = this.conclusionInfo.remark; // 客户备注
            }
            if(this.conclusionInfo.claimCheck != '' && this.conclusionInfo.claimCheck != null) {
              this.conclusionForm.claimCheck = this.conclusionInfo.claimCheck; // 核赔依据
            }
            // console.log("res.data")
            // console.log(res.data)
            // console.log("res.data")
          }
        });
      },
      formReset(fromName){
        this.$refs[fromName].resetFields()
      },
      activeFun(obj){

        let id = obj.id;
        switch(obj.toElement.id) {
          case 'span1':
            this.isButtonShow=true;
            this.isActiveSpan1 = true;
            this.isActiveSpan2 = false;
            this.isActiveSpan3 = false;
            break;
          case 'span2':
            this.isButtonShow=false;
            this.isActiveSpan1 = false;
            this.isActiveSpan2 = true;
            this.isActiveSpan3 = false;
            break;
          case 'span3':
            this.isButtonShow=false;
            this.isActiveSpan1 = false;
            this.isActiveSpan2 = false;
            this.isActiveSpan3 = true;
            break;
          default :
        }

      },
      //抽检完毕
      caseCheckOver() {
        let data = {
          rptNo: this.fixInfo.rptNo
        }
        editCaseCheck(data).then(res => {
          if (res != null && res.code === 200) {
            this.$message({
              message: '抽检成功！',
              type: 'success',
              center: true,
              showClose: true
            });
          }
        }).catch(res => {
          this.$message({
            message: '抽检失败！',
            type: 'error',
            center: true,
            showClose: true
          });
        })
      },
      //退回审核
      backToCalculate() {
        this.$confirm(`是否退回审核?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let data = {
            rptNo: this.fixInfo.rptNo
          }
          editCaseCheckBack(data).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '退回审核成功！',
                type: 'success',
                center: true,
                showClose: true
              });
              this.$router.push({path: '/claims-handle/review'});
            }else {
              this.$message({
                message: '退回审核失败！',
                type: 'error',
                center: true,
                showClose: true
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      }
    }

  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
  .long-input ::v-deep .el-form-item__content {
    width: calc(100% - 150px);
  }

  .money_class{
    color: #2CC38E
  }
  ::v-deep.el-tabs__item.is-disabled {
    color: #303133;
    cursor:default
  }
  ::v-deep.el-tabs__item.is-active {
    color: #409EFF;
  }
  .span-tab {
    padding-right: 3%;
  }
  ::v-deep.span-tab:hover {
    color: #1890ff;
    cursor: pointer;
  }
  ::v-deep.span-tab {
    height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 40px;
    display: inline-block;
    list-style: none;
    font-size: 14px;
    font-weight: 500;
    color: #303133;
    position: relative;
  }
  ::v-deep.span-tab.is-active {
    color: #1890ff;
  }

  ::v-deep.span-tab1 {
    padding: 0 20px;
    height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 40px;
    display: inline-block;
    list-style: none;
    font-size: 14px;
    font-weight: 500;
    color: #303133;
    position: relative;
  }

  ::v-deep.el-divider--horizontal {
    display: block;
    height: 1px;
    width: 100%;
    margin: 0 0;
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
