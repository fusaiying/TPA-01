<template>

    <el-card class="box-card" style="margin-top: 10px;">
      <div style="position: relative">
        <div slot="header" class="clearfix">
          <div style="width: 100%;cursor: pointer;">
            <span id="span1" :class="[isActiveSpan1?'span-tab is-active':'span-tab']" @click="activeFun('span1')">赔付结论</span>
            <span id="span2" v-if="node=='calculateReview'" :class="[isActiveSpan2?'span-tab is-active':'span-tab']" @click="activeFun('span2')">协谈</span>
            <span id="span3" v-if="node=='calculateReview'" :class="[isActiveSpan3?'span-tab is-active':'span-tab']" @click="activeFun('span3')">调查</span>
            <div style="float: right;" v-if="status==='edit' && (node==='sport' || node==='calculateReview') ">
              <el-button v-if="isButtonShow" type="primary" @click="updateCalInfo" size="mini" >保存 </el-button>
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
                 label-position="right" size="mini"  :disabled="status === 'show' && (node==='sport' || node==='calculateReview')">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">账单金额：</span> <span class="info_span">{{ conclusionInfo.sumBillAmount }} {{ conclusionInfo.sumBillAmount == ''  ? '' : billCurency }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">折扣金额：</span> <span class="info_span money_class">{{ conclusionInfo.sumHosDiscountAmount }} {{ conclusionInfo.sumHosDiscountAmount == ''  ? '' : billCurency}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">赔付金额：</span> <span class="info_span money_class">{{ conclusionInfo.calAmount }} {{ conclusionInfo.calAmount == '' || conclusionInfo.calAmount == null  ? '' : 'CNY' }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">拒赔金额 ：</span> <span class="info_span money_class">{{ conclusionInfo.refusedAmount }} {{ conclusionInfo.refusedAmount == '' || conclusionInfo.refusedAmount == null ? '' : 'CNY' }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">追讨金额：</span> <span class="info_span money_class">{{ conclusionInfo.debtAmount}} {{ conclusionInfo.debtAmount == '' || conclusionInfo.debtAmount == null ? '' : 'CNY' }}</span>
            </el-col>
            <el-col :span="8" v-if="appealCase">
              <span class="info_span_col to_right">本次支付差额：</span> <span class="info_span money_class">{{ conclusionInfo.paymentDifference }} {{ conclusionInfo.paymentDifference == '' || conclusionInfo.paymentDifference == null  ? '' : billCurency }}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="账单币种：" prop="billCurrency">
                <el-select  size="mini" v-model="conclusionForm.billCurrency" class= "el-select item-width el-select--mini" placeholder="请选择" @change="billCurrencyChange">
                  <el-option  v-for="dict in currencys" :key="dict.dictValue"  :label="dict.dictValue+' - '+dict.dictLabel"  :value="dict.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">汇率：</span> <span class="info_span money_class">{{ conclusionInfo.exchangeRate}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">外币给付金额：</span> <span class="info_span money_class">{{ conclusionInfo.payAmountForeign}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="赔付结论：" prop="payConclusion">
                <el-select disabled="disabled"  size="mini" v-model="conclusionForm.payConclusion" class= "el-select item-width el-select--mini" placeholder="请选择" @change="payConclusionChange">
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
        <el-form v-if="isActiveSpan2" ref="discussionForm"  :disabled="status === 'show'  && (node==='sport' || node==='calculateReview')"
                 :inline="true" :model="discussionForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px"  :rules="disRules"  label-position="right" size="mini">
          <el-row>
            <el-col :span="20" :xs="24">
                <el-form-item label="协谈类型：" prop="discType">
                  <el-select v-model="discussionForm.discType" class="item-width" size="mini" placeholder="请选择" @change="validSubType">
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
                <el-input  style="min-width: 700px" col="2" type="textarea" row="4" maxlength="1000" v-model="discussionForm.disView" clearable size="mini"  placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

          <!--保存 start-->
          <el-row >
            <el-col :span="6" :offset="12">
              <el-button :disabled="dissSave" type="primary" size="mini" @click="discussionSave">确认</el-button>
            </el-col>
          </el-row>
          <!--保存 end-->

        </el-form>
        <!-- 协谈 end -->

        <!-- 调查 start -->
        <el-form v-if="isActiveSpan3"  :disabled="status === 'show'  && (node==='sport' || node==='calculateReview')"
                 ref="surveyForm" :model="surveyForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" :rules="surRules"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">调查任务号：</span><span class="info_span_col">{{ surveyInfo.invNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">被保人：</span><span class="info_span_col">{{ surveyInfo.name}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">性别：</span><span class="info_span_col">{{selectDictLabel(rgtSexs,surveyInfo.sex)}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">证件类型：</span><span class="info_span_col">{{selectDictLabel(card_types,surveyInfo.idType)}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">证件号码：</span><span class="info_span_col">{{ surveyInfo.idNo}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">出险日期：</span><span class="info_span_col">{{ surveyInfo.accDate}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">事故地点：</span><span class="info_span_col">{{ surveyInfo.accProvince  }}  {{ surveyInfo.accCity  }}  {{ surveyInfo.accDistrict  }}</span>
            </el-col>
            <el-col :span="16">
              <span class="info_span_col to_right">事故经过：</span><span class="info_span_col_left el-col-18">{{ surveyInfo.accDescribe}}</span>
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
                  <el-option v-for="item in inquiryOrg" :key="item.dictValue"  :label="item.dictLabel + ' - ' +item.dictValue" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="提调机构：" prop="organCode" key="organCode1da">
                <el-select v-model="surveyForm.organCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in initiateOrg" :key="item.dictValue"  :label="item.dictLabel + ' - ' +item.dictValue" :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="保单号：" prop="policyNo"  key="policyNo1df">
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
                <el-input maxlength="1000" v-model="surveyForm.invView" :class="['long-input']" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!--保存 start-->
          <el-row >
            <el-col :span="6" :offset="20">
              <el-button type="primary" size="mini" @click="inQuireSaveFun" >确定</el-button>
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
          investigationBaseInfo,
          discussionBaseInfo,
          acceptInfo,
          checkBillAndPolicyDate,
          exchangeRate,
  } from '@/api/handel/common/api'
  import {editCaseCheckBack, editCaseCheck} from '@/api/claim/sportCheck'

  let dictss = [{dictType: 'rejected_reasons'}, {dictType: 'conclusion'}, {dictType: 'claim_currency'}, {dictType: 'negotiation_type'},
    {dictType: 'negotiation_sub_type'}, {dictType: 'dispatch_type'}, {dictType: 'initiate_reasons'},{dictType: 'inquiry_org'},{dictType: 'initiate_org'},
    {dictType: 'rgtSex'},{dictType: 'card_type'}]

  export default {
    components: {
    },
    props: {
      status: String,
      value: {
        type: Boolean,
        default: false
      },
      policySelectData: {
        type: Array,
        default: function () {
          return []
        }
      },
      insuredData:Object,
      fixInfo:Object,
      node:String,

    },
    watch: {
      insuredData: function (newValue) {
        this.surveyInfo = newValue;
      },
      policySelectData: function (newValue) {
        this.policyNos = newValue;
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue;
        this.rptNo = this.fixInfoData.rptNo;
        this.batchNo = this.fixInfoData.batchNo;
        if(this.rptNo != '') {
          this.getCalInfo();
          this.getDiscussionInfo();
          this.getInvestigationBaseInfo();
        }
      },
      value: function (newValue) {
      },
    },
    data() {
      const checkRefusedReason = (rule, value, callback) => {
        if(this.conclusionInfo.refusedAmount > 0) {
          if (!value) {
            callback(new Error("拒赔原因必填"));// XXXXXXXXXXXX
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      const checkDiscSubType = (rule, value, callback) => {
        let discTypeValue = this.discussionForm.discType;
        if(discTypeValue === '04' || discTypeValue === '08' || discTypeValue === '09') {
          if (!value) {
            callback(new Error("协谈细类必填"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      return {
        billCurency:'',
        appealCase:false,
        dictList: [],
        rgtSexs:[],
        card_types: [],
        inQuireConfirmBtn:false,
        policyNos :[],
        dissSave:false,
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
          prePayStatus:'', // 原案件支付状态

        },
        conclusionForm:{
          billCurrency:'',
          payConclusion:'',
          refusedReason:'',
          remark:'Min[上限，Σ费用项（合理金额-免赔额）*赔付比例]',
          claimCheck:'Min[上限，Σ费用项（合理金额-免赔额）*赔付比例]',
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
          discSubType: {trigger: ['change'], required: false, validator: checkDiscSubType,},
        },
        //协谈信息 end

        //调查信息 start
        surveyInfo:{
          invNo:'', // 调查任务号
          name : '', // 被保人
          sex:'', //性别
          idType:'', // 证件类型
          idNo:'', // 证件号码
          accDate:'', // 出险日期
          accProvince:'',
          accCity:'',
          accDistrict:'',
          accAddress:'',
          invType:'', // 提调类型
          invReason:'', // 提调原因
          invOrganCode:'', // 调查机构
          organCode:'', // 提调机构
          policyNo:'',
          invView:'',
          accDescribe:'',
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
        negotiationSubTypes:[],
        //所有协谈细类
        negotiationSubAllTypes:[]

      }
    },

    created() {
      this.getAcceptInfo();
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.rejectedReasons = this.dictList.find(item => {
        return item.dictType === 'rejected_reasons'
      }).dictDate
      this.conclusionSelect = this.dictList.find(item => {
        return item.dictType === 'conclusion'
      }).dictDate
      this.currencys = this.dictList.find(item => {
        return item.dictType === 'claim_currency'
      }).dictDate
      this.negotiationTypes = this.dictList.find(item => {
        return item.dictType === 'negotiation_type'
      }).dictDate
      this.negotiationSubAllTypes = this.dictList.find(item => {
        return item.dictType === 'negotiation_sub_type'
      }).dictDate
      this.dispatchTypes = this.dictList.find(item => {
        return item.dictType === 'dispatch_type'
      }).dictDate
      this.initiateReasons = this.dictList.find(item => {
        return item.dictType === 'initiate_reasons'
      }).dictDate
      this.inquiryOrg = this.dictList.find(item => {
        return item.dictType === 'inquiry_org'
      }).dictDate
      this.initiateOrg = this.dictList.find(item => {
        return item.dictType === 'initiate_org'
      }).dictDate
      this.rgtSexs = this.dictList.find(item => {
        return item.dictType === 'rgtSex'
      }).dictDate
      this.card_types = this.dictList.find(item => {
        return item.dictType === 'card_type'
      }).dictDate
    },
    computed: {
    },
    methods: {
      goHistory(){
        this.$router.go(-1);
      },
      // 账单币种改变后，变更汇率和外币给付金额
      billCurrencyChange(value){
        if(value != '' && this.rptNo != '') {
          this.conclusionInfo.exchangeRate = '';
          this.conclusionInfo.payAmountForeign = '';
          const params = {
            rptNo : this.rptNo,
            billCurrency:value,
          };
          exchangeRate(params).then(res => {
            if(res.code == '200' && res.data) {
              let result = res.data;
              if (this.conclusionForm.billCurrency=='CNY'){
                this.conclusionInfo.exchangeRate=''
                this.conclusionInfo.payAmountForeign=''
              }else {
                if(result.payAmountForeign != null) {
                  this.conclusionInfo.payAmountForeign = result.payAmountForeign;
                }
                if(result.exchangeRate != null) {
                  this.conclusionInfo.exchangeRate = parseFloat(result.exchangeRate).toFixed(2);;

                }
              }
            }
          });
        }
      },
      validSubType(value){
        this.negotiationSubTypes = [];
        this.discussionForm.discSubType = '';
        for(let i=0 ; i<this.negotiationSubAllTypes.length; i++) {
          if(this.negotiationSubAllTypes[i].listClass === value) {
            this.negotiationSubTypes.push(this.negotiationSubAllTypes[i])
          }
        }
      },
      payConclusionChange(value){
        if(value != '05'){
          this.conclusionForm.refusedReason = '';
        }
      },
      getAcceptInfo(){
        if(this.rptNo == '') {
          return false;
        }
          this.surveyInfo.accProvince = '';
          this.surveyInfo.accCity  = '';
          this.surveyInfo.accDistrict  = '';
          this.surveyInfo.accDescribe =  '';
          this.surveyInfo.accDate =  '';
        acceptInfo(this.rptNo).then(res => {
          if(res.code == '200' && res.data) {
            let data = res.data;
            if(null != data.claimCaseAccept) {
              data = data.claimCaseAccept ;
              if(data.accProvinceName != '') {
                this.surveyInfo.accProvince = data.accProvinceName;
              }
              if(data.accCityName != '') {
                this.surveyInfo.accCity  = data.accCityName;
              }
              if(data.accDistrictName != '') {
                this.surveyInfo.accDistrict  = data.accDistrictName;
              }
              if(data.accDescribe != '') {
                this.surveyInfo.accDescribe =  data.accDescribe;
              }
              if(data.accDate != '') {
                this.surveyInfo.accDate =  data.accDate;
              }

            }
          }
        });
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
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
                this.goHistory();
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
                debtAmount:this.conclusionInfo.debtAmount
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
                  this.$message({
                    message: '保存失败！',
                    type: 'error',
                    center: true,
                    showClose: true
                  });
                }
              });
            } else {
            }
          })
      },

      // 赔付结论  审核完毕
      examineSave(){
        if(this.rptNo == '') {
          return false;
        }
        if(this.appealCase) {
          if(this.conclusionInfo.prePayStatus === '05') {
            this.$message.warning('该案件存在退票，支付成功后才可申诉！')
            return false;
          }
        }
          //
        if(this.conclusionInfo.payConclusion == '' || this.conclusionInfo.payConclusion == null){
          this.$message.warning('请先保存再进行审核！')
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
          payAmount:this.conclusionInfo.calAmount,
          refusedAmount:this.conclusionInfo.refusedAmount,
          debtAmount:this.conclusionInfo.debtAmount,
          paymentDifference:this.conclusionInfo.paymentDifference,
        };
        checkBillAndPolicyDate(this.rptNo).then(res=>{
          if (res!=null && res.code==200){
            if (res.data==0){
              // 审核完毕
              finishClaimCase(params).then(res => {
                if (res.code == '200') {
                  this.$message({
                    message: '审核成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  });
                  this.goHistory();
                } else {
                  this.$message({
                    message: '审核失败！',
                    type: 'error',
                    center: true,
                    showClose: true
                  });
                }
              });
            }else {
              this.$confirm(`就诊日期不在保单有效期范围内，请确认是否继续?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                // 审核完毕
                finishClaimCase(params).then(res => {
                  if (res.code == '200') {
                    this.$message({
                      message: '审核成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    });
                    this.goHistory();
                  } else {
                    this.$message({
                      message: '审核失败！',
                      type: 'error',
                      center: true,
                      showClose: true
                    });
                  }
                });
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消！'
                })
              })

            }
          }
        })


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

        this.$confirm(`是否退回受理?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          backToClaimCase(params).then(res => {
            if (res.code == '200') {
              this.$message({
                message: '退回成功！',
                type: 'success',
                center: true,
                showClose: true
              });
              this.goHistory();
            } else {
              this.$message({
                message: '退回失败！',
                type: 'error',
                center: true,
                showClose: true
              });
            }
          });
        }).catch(() => {
        })
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
              if (res.code == '200') {
                this.getInvestigationBaseInfo();
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
        if(!this.inQuireConfirmBtn) {
          this.$message.info('请先保存再进行提调！')
          return false;
        }
        const params = {};
        params.rptNo = this.rptNo;

        inQuireConfirm(params).then(res => {
          if (res.code == '200') {
            this.$message({
              message: '提调成功！',
              type: 'success',
              center: true,
              showClose: true
            });
            this.goHistory();
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
        //  test rpt :  96JGH0X0000000041
        calInfo(this.rptNo).then(res => {
          if(res.code == '200' && res.data) {
            this.conclusionInfo = res.data;
            if(null != this.conclusionInfo.exchangeRate && '' != this.conclusionInfo.exchangeRate) {
              this.conclusionInfo.exchangeRate = parseFloat(this.conclusionInfo.exchangeRate).toFixed(2);
            }

            if(this.conclusionInfo.billCurrency != '' && this.conclusionInfo.billCurrency != null) {
              this.conclusionForm.billCurrency = this.conclusionInfo.billCurrency; // 账单币种
              this.billCurency =  this.conclusionInfo.billCurrency; // 账单币种
              if (this.conclusionForm.billCurrency=='CNY'){
                this.conclusionInfo.exchangeRate=''
                this.conclusionInfo.payAmountForeign=''
              }
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
            if(res.data.isAppeal == '02') {
              this.appealCase = true;
            }
          }
        });
      },
      getInvestigationBaseInfo() {
        investigationBaseInfo(this.rptNo).then(res => {
          if(res.code == '200' && res.data) {
            this.surveyInfo = res.data;
            if(this.surveyInfo.invType != '' && this.surveyInfo.invType != null) {
              this.surveyForm.invType = this.surveyInfo.invType;
              this.inQuireConfirmBtn = true;
            }
            if(this.surveyInfo.invReason != '' && this.surveyInfo.invReason != null) {
              this.surveyForm.invReason = this.surveyInfo.invReason;
            }
            if(this.surveyInfo.invOrganCode != '' && this.surveyInfo.invOrganCode != null) {
              this.surveyForm.invOrganCode = this.surveyInfo.invOrganCode;
            }
            if(this.surveyInfo.organCode != '' && this.surveyInfo.organCode != null) {
              this.surveyForm.organCode = this.surveyInfo.organCode;
            }
            if(this.surveyInfo.policyNo != '' && this.surveyInfo.policyNo != null) {
              this.surveyForm.policyNo = this.surveyInfo.policyNo;
            }
            if(this.surveyInfo.invView != '' && this.surveyInfo.invView != null) {
              this.surveyForm.invView = this.surveyInfo.invView;
            }
          }else {
            this.getAcceptInfo();
          }
        });
      },
      getDiscussionInfo() {
        discussionBaseInfo(this.rptNo).then(res => {
          if(res.code == '200' && res.data && res.data.isHistory != 'Y') {
            this.dissSave = true;
            if(res.data.discType != '' && res.data.discType != null) {
              this.discussionForm.discType = res.data.discType;
              this.validSubType(res.data.discType)
              if(res.data.discSubType != '' && res.data.discSubType != null) {
                this.discussionForm.discSubType = res.data.discSubType;
              }
            }
            if(res.data.disView != '' && res.data.disView != null) {
              this.discussionForm.disView = res.data.disView;
            }
          }
        });
      },
      formReset(fromName){
     //   this.$refs[fromName].resetFields()
        this.surveyForm.invType = '';
        this.surveyForm.invReason = '';
        this.surveyForm.invOrganCode = '';
        this.surveyForm.organCode = ' ';
        this.surveyForm.policyNo = ' ';
        this.surveyForm.invView = '';
        this.surveyForm.organCode = '';
        this.surveyForm.policyNo = '';
        this.$refs['surveyForm'].clearValidate();





      },
      activeFun(id){
        switch(id) {
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

        if(this.appealCase) {
          if(this.conclusionInfo.prePayStatus === '05') {
            this.$message.warning('该案件存在退票，支付成功后才可申诉！')
            return false;
          }
        }

        editCaseCheck(data).then(res => {
          if (res != null && res.code === 200) {
            this.$message({
              message: '抽检成功！',
              type: 'success',
              center: true,
              showClose: true
            });
            this.$store.dispatch("tagsView/delView", this.$route);
            this.$router.go(-1)
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

  ::v-deep.info_span_col_left {
    text-align: left;
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
