<template>
  <div id="newfirsttaial" class="newfirsttaial">
    <el-backtop :bottom="60"/>
    <el-button
      style="{
          height: 20px;
          width: 120px;
          background-color: #8deeee;
          box-shadow: 0 0 6px rgba(0,0,0, .12);
          text-align: center;
          line-height: 20px;
          color: black;
          font-size: 15px;
          position: fixed;
          top: 230px;
          right: 40px;
          z-index: 999
        }"
      size="small"
      id="imagemove"
      @click="imageView"
    >
      影 像 预 览
    </el-button>
    <div
      :class="{'opened-adapt': sidebar.opened, 'close-adapt': !sidebar.opened, 'top-instance': this.$store.state.settings.tagsView }"
      class="nav-bar">
      <el-card class="top-card-heard">
        <span>
          <span style="color:#409EFF;font-size:12px">赔案号：{{ claimno }}</span>
          <el-button style="cursor: default;" type="text" size="small"> 申请来源：{{ /* dict.label.apply_sourcetype[fixInfo.applicationsource] */getApply_sourcetypeOptions(fixInfo.applicationsource) }}</el-button>
        </span>
        <span>
          <el-dropdown :hide-on-click="false" size="mini" style="margin: 0 20px;">
            <span class="el-dropdown-link" style="font-size: 12px; color: #1890ff;cursor: pointer;">
              异常信息:
              <span v-if="fixInfo.exceptioninfo.length" style="color:#F56C6C;">有
              <i class="el-icon-arrow-down el-icon--right"/>
              </span>
              <span v-if="fixInfo.exceptioninfo.length == 0" style="font-size: 12px; color: #67C23A;">无</span>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="(item, i) in fixInfo.exceptioninfo" :key="i">{{ item }}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
        <el-tooltip class="item" effect="dark" placement="top-start">
          <div slot="content">当前岗位处理时效：{{fixInfo.stationtime}}天<br/>案件处理时效:{{fixInfo.handletime}}天<br/>受理时效：{{fixInfo.accepttime}}天
          </div>
          <el-button style="cursor: default;" type="text" size="small">时效:{{ fixInfo.stationtime }}天</el-button>
        </el-tooltip>
        <el-form :disabled="disabled" style="float: right; padding: 3px 0">
          <span>
            <el-button type="primary" size="mini" @click="openFillis">补材({{fillsCount}})</el-button>
            <el-button type="primary" size="mini" @click="openTone">提调({{investigaeNumber}})</el-button>
            <el-button type="primary" size="mini" @click="openNegotiat">协谈({{negotiationCount}})</el-button>
            <el-button :disabled="!reportCount" type="primary" size="mini" @click="openReporInfo('show')">报案({{reportCount}})</el-button>
            <el-button v-if="suspendFlag==='N'" type="primary" size="mini"
                       @click="openHandle('0','悬挂说明')">悬挂</el-button>
            <el-button type="primary" size="mini" @click="openHandle('1','不予受理话术模版')">不予受理</el-button>
            <el-button type="primary" size="mini" @click="firstTrialReturn">初审回退</el-button>
            <el-button type="primary" size="mini" @click="firstTrialOver">初审完毕</el-button>
          </span>
        </el-form>
      </el-card>
    </div>
    <el-tabs type="card" class="card-top" :stretch="true" v-model="activetab" style="padding:0 10px">
      <el-tab-pane label="案件信息" name="1">
        <div class="nav-flex-box">
          <div v-for="(item, i) in btnArr" :key="i" :name="item.name"
               class="flex-item-btn"
               :class="{'active':activeNav===item.name}"
               @click="goAnchor(item.name)">
            <span>{{ item.label }}</span>
          </div>
        </div>
        <div id="start_jump" class="startjump" :class="{'navFalgClass': navFlag == false}">
          <div id="#anchor-1">
            <baseinfo v-if="isFlag" :applicationInfo="applicationInfo" :dictList="dictList"
                      :status="rejectReturnFlag?'show':status" :isAppeal="isAppeal" :claimno="claimno" :node="node"
                      :finalamount="finalamount" :payee-info="{data: payeeInfo}" :insured-info="insuredInfo"
                      :insuredInfo="insuredInfo"></baseinfo>
          </div>
          <div id="#anchor-2">
            <new-policy-info v-if="isFlag" :activetab="activetab" :dictList="dictList" :policy-info="policyInfo"
                             :status="rejectReturnFlag?'show':status" :node="node"></new-policy-info>
          </div>
          <div id="#anchor-3">
            <new-past-info :status="status" :dictList="dictList" :node="node" :claimno="claimno"
                           :past-info="pastInfo"></new-past-info>
          </div>
          <div id="#anchor-4">
            <new-risk-info :status="rejectReturnFlag?'show':status" :node="node"
                           :applysourcetype="fixInfo.applicationsource" :danger-info="dangerInfo" :claimno="claimno"/>
          </div>
          <div id="#anchor-5">
            <materialInfo-com :claimno="claimno" :dictList="dictList" :acceptFlag="acceptFlag"
                              @getMateriaList="getMateriaList" :status="rejectReturnFlag?'show':status" :node="node"
                              :material-info="materialInfo" :tab-labels="tabLabels" :image-info="imageInfo"
                              :email="applicationInfo.applicantmail" @changeList="changeList"
                              @imageUpdate="imageUpdate"/>
          </div>
          <div id="#anchor-6">
            <new-report-notes :acceptFlag="acceptFlag" :dictList="dictList" :status="rejectReturnFlag?'show':status"
                              :node="node" @caseRemarkList="caseRemarkList" @getCaseRemark="getCaseRemark"
                              :flag="disableFlag" :case-remark="caseRemark"
                              :claimno="node==='report'?registerNo:claimno"></new-report-notes>
          </div>
          <div id="#anchor-7">
            <new-case-label :node="node" :status="status" :claimNo="claimno" @getPublicList="getPublicList"
                            :caselabelInfo="caselabelInfo"></new-case-label>
          </div>
        </div>
        <div id="#anchor-8">
          <new-loag :status="status" :dictList="dictList" :node="node" :timelineInfo="timelineInfo"></new-loag>
        </div>
      </el-tab-pane>
      <el-tab-pane label="就诊信息" name="2">
        <div class="startjump_next">
          <div id="#anchor-10">
            <new-event-info :dictList="dictList" :status="rejectReturnFlag?'show':status" @getEventInfo="getEventInfo"
                            :accidentdate="applicationInfo.accidentdate" :node="node" :event-info="eventInfo"
                            :claimno="claimno" :insuredInfo="insuredInfo"
                            @relationEvent="relationEvent"></new-event-info>
          </div>
          <div id="#anchor-11">
            <new-disease-info :dictList="dictList" :status="rejectReturnFlag?'show':status"
                              :accidentdate="applicationInfo.accidentdate" :accidentInfoFrom="accidentInfoFrom"
                              :node="node" :event-info="eventInfo" :claimno="claimno" :insuredInfo="insuredInfo"
                              @relationEvent="relationEvent"></new-disease-info>
          </div>
          <div>
            <new-bill-info :dictList="dictList" :status="rejectReturnFlag?'show':status"
                           @getInvoicelistInfo="getInvoicelistInfo" :accidentdate="applicationInfo.accidentdate"
                           :node="node" :event-info="eventInfo" :claimno="claimno" :insuredInfo="insuredInfo"
                           @relationEvent="relationEvent" :invoicelistInfo="invoicelistInfo"></new-bill-info>
          </div>
          <div id="#anchor-12">
            <new-loag :status="status" :dictList="dictList" :node="node" :timelineInfo="timelineInfo"></new-loag>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="赔付理算" name="3">
        <div class="startjump_next">
          <div id="#anchor-13">
            <new-policy-info :dictList="dictList" :activetab="activetab"
                             :istpolicyListDisablsed="istpolicyListDisablsed" :policy-info="policyInfo"
                             :status="rejectReturnFlag?'show':status" :claimno="claimno" :node="node"></new-policy-info>
          </div>
          <div style="margin:10px 0"
               v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node === 'physical'"
               id="#anchor-14">
            <new-compen-info :dictList="dictList" ref="newCompenInfo" :istpolicyListDisablsed="istpolicyListDisablsed"
                             :status="rejectReturnFlag?'show':status" :node="node" :claimno="claimno"
                             @getPayInfo="getPayInfo" @getPayeeInfo="getPayeeInfo" :base-form="{tableData: payDetail}"/>
          </div>
          <div id="#anchor-15">
            <payconclusion :dictList="dictList" :payInfo="payInfo" :conclusionreasonList="conclusionreasonList"
                           :payeeInfo="payeeInfo" :node="node" @getPayInfo="getPayInfo" :claimno="claimno"
                           :finalamount="finalamount" :payee-infos="{data: payeeInfo}" :insured-info="insuredInfo"
                           :insuredInfo="insuredInfo"></payconclusion>
          </div>
          <div id="#anchor-23">
            <confire-payee :dictList="dictList" :status="rejectReturnFlag?' show':status" :isAppeal="isAppeal"
                           :claimno="claimno" :node="node" :finalamount="finalamount" :payee-info="{data: payeeInfo}"
                           :insured-info="insuredInfo"></confire-payee>
          </div>
          <div id="#anchor-16">
            <new-loag :dictList="dictList" :status="status" :node="node" :timelineInfo="timelineInfo"></new-loag>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="通知消息" name="4">
        <div class="startjump_next">
          <div style="min-height:500px" id="#anchor-17"></div>
          <div id="#anchor-18">
            <new-loag :dictList="dictList" :status="status" :node="node" :timelineInfo="timelineInfo"></new-loag>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- 模态框 -->
    <trial-repor-modul :dictList="dictList" v-model="trialReporVis" :claimno="claimno"/>
    <report-modul :dictList="dictList" v-model="reportVis" :flag="reportType" :claimno="claimno" :insured-no="insuredNo"
                  @reportCountsUpdate="reportCountsUpdate"/>
    <file-list-modul :dictList="dictList" v-model="fileListVis" :claimno="claimno"/>
    <fills-modul :dictList="dictList" ref="minimize" v-model="fillisVis" @toMinimize="toMinimize"
                 @updateStatus="updateStatus" @changeList="changeList" @imageUpdate="imageUpdate"
                 @getFileCount="getFileCount" :imageLoad="imageLoad" :tab-labels="tabLabels" :status="status"
                 :node="node" :image-info="imageInfo" :fillsCount="fillsCount" :email="applicationInfo.applicantmail"
                 :claimno="claimno" type="01"/>
    <negotiat-modul :dictList="dictList" v-model="negotiatVis" @updateStatus="updateStatus" :claimno="claimno"
                    :node="node"/>
    <handle-modul :dictList="dictList" v-model="handleVis" :diaTitle="diaTitle" :placeholder="placeholder"
                  :claimno="claimno" @changeButtonDisabled="changeButtonDisabled"/>
    <case-split-modul :dictList="dictList" v-model="splitVis" :claimno="claimno"/>
    <!-- <policy-info-modul v-model="policyVis" /> -->
    <break-off-modul :dictList="dictList" v-model="breakVis" :claimno="claimno"/>
    <tone-info-modul :dictList="dictList" v-model="toneVis" @updateStatus="updateStatus" :node="node"
                     :claimno="claimno"/>
    <div v-show="iframeShow" id="printall" ref="printall">
      <div style="margin-bottom: 20px">
        <barcode :value="claimno" :options="barcode_option" tag="svg"/>
      </div>
    </div>
    <iframe v-show="iframeShow" id="print-iframe"/>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="300px"
      :append-to-body="true"
      :close-on-click-modal="false"
    >
      <span>{{exemptionTitle}}</span>
      <span slot="footer" class="dialog-footer" v-if="exemptionTitleFlag == true">
        <el-button @click="confireExemption" type="primary" size="mini">确认豁免</el-button>
        <el-button @click="calseExemption" type="primary" size="mini">取消豁免</el-button>
        <el-button type="primary" size="mini" @click="dialogVisible = false">取消</el-button>
      </span>
      <span slot="footer" class="dialog-footer" v-if="exemptionTitleFlag == false">
        <el-button type="primary" @click="dialogVisible = false" size="mini">是</el-button>
        <el-button type="primary" @click="calseExemption2" size="mini">否</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="提示"
      :visible.sync="saveconsdialogVisible"
      width="270px"
      :append-to-body="true"
      :close-on-click-modal="false"
    >
      <span>结案权限不足，案件将继续流转！</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="confireSaveConculsion" type="primary" size="mini">确认</el-button>
        <el-button type="primary" size="mini" @click="saveconsdialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="初审回退确认"
      :visible.sync="firstTrialReturnMedal"
      width="450px"
      :append-to-body="true"
      :show-close="false"
      :close-on-click-modal="false"
    >
      <el-form :model="ruleForm" :rules="rules" ref="returnForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="回退原因" prop="conclusionContent">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入内容"
            clearable
            maxlength="100"
            show-word-limit
            v-model="ruleForm.conclusionContent">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="confireReturnag" type="primary" size="mini">确认</el-button>
        <el-button type="primary" size="mini" @click="scallReturn">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import Bus from '../bus/bus'
  import {mapGetters} from 'vuex'

  import mixinAnchor from './mixins/mixinAnchor'
  import {decrypt, encrypt} from "@/utils/rsaEncrypt"
  import moment from 'moment'
  import materialInfoCom from '../common/components/materialInfo'
  // 模态框
  import reportModul from '../common/modul/report'
  import trialReporModul from '../common/modul/trialRepor'
  import fileListModul from '../common/modul/fileList'
  import fillsModul from '../common/modul/fills'
  import negotiatModul from '../common/modul/negotiation'
  import handleModul from '../common/modul/handle'
  import caseSplitModul from '../common/modul/caseSplit'

  import toneInfoModul from '../common/modul/toneInfo'
  import breakOffModul from '../common/modul/breakOff'
  import baseinfo from './compontents/baseInfo'
  //import newPayeeInfo from './compontents/newpayeeInfo'
  import newPolicyInfo from './compontents/newpolicyInfo'
  import newPastInfo from './compontents/newpastInfo'
  import newRiskInfo from './compontents/newriskInfo'

  import newReportNotes from './compontents/newreportNotes'
  import newCaseLabel from './compontents/caselabel'
  import newEventInfo from './compontents/neweventInfo'
  import newDiseaseInfo from './compontents/newdisease'
  import newBillInfo from './compontents/billInfo'
  import newCompenInfo from './compontents/newcompenInfo'
  import newLoag from './compontents/newLoag'
  import payconclusion from './compontents/payconclusion'
  import errorNotice from './compontents/errorNotice'
  import confirePayee from './compontents/newconfirePayee'
  // api
  import {
    getApplicantInfo,
    getInsuredInfo,
    getPayInfo,
    savePayInfo,
    getPayeeInfo,
    getDangerInfo,
    getEventInfoList,
    getCaseRemark,
    relationPolicy,
    getPayDetail,
    getMateriaList,
    casesuspend,
    saveConclusion,
    caseSchedule,
    getImageInfo,
    pastSurvey,
    pastNegotiation,
    updateCheck,
    materialPendingQuery,
    basicInfo,
    saveConclusionCheck,
    getCaseLastNodeInfo,
    isSuspend,
    getFileCount,
    getPastClaimsList,
    getTempalte,
    registerInfo,
    returnInformationQuery,
    getLastOptlog,
    getGroupUserList,
    pastSupplementaryList,
    getNegotiationInfo,
    getContents,
    getCheckOptLog,
    checkExemption,
    saveExemption,
    checkCaseDeal,
    judgeReopen,
    blockCheck,
    judgeStatus,
    countInvestigaeNumber,
    isAppealCorrection,
    downloadPdf,
    queryWaitExemptionCount,
    queryAccordWithExemption,
    queryPolicyExemptionDetail,
    queryExemptionStartDate,
    getInvoicelistInfo,
    getCasePublicList,
    getEventInfo,
    newEventDelete,
    newAccidentInfo,
    getNewpolicyInfo,
    getProofNameBySubCode,
    processCheck,
    caseRemarkList,
    caseProcessLog,
    calculationPayAmount
  } from '@/api/claim/handleDeal'
  import {
    getRegisterInfo,
    registerConfirm,
    getClaimInfo,
    acceptConfirm,
    getCounts,
    imageDeal,
    judgeClaim,
    judgeBnf,
    judgeRegister,
    getReceipt,
    getCustomerLabel
  } from '@/api/claim/register'
  import {getBillInfos, updateMaterial} from '@/api/claim/input'
  import {downloadFile} from "@/utils/index";
  /*  import { get } from '@/api/dictDetail'*/
  let dictss = [{dictType: 'channel_source'}, {dictType: 'relation_ship_apply'}, {dictType: 'applicant_idtype'}, {dictType: 'apply_type'},
    {dictType: 'applicant_sex'}, {dictType: 'payment_conclusion'}, {dictType: 'apply_sourcetype'}, {dictType: 'tasknode'},
    {dictType: 'examine_conclusion'}, {dictType: 'nativeplace'}, {dictType: 'payee_occupation'}, {dictType: 'casestep'},
    {dictType: 'examine_reject'}, {dictType: 'conclusionreason'}, {dictType: 'err_type'}, {dictType: 'accommodation_type'},
    {dictType: 'modify_type'}, {dictType: 'invest_err_type'}, {dictType: 'conclusionreason2'}, {dictType: 'conclusionreason3'},
    {dictType: 'conclusionreason4'}, {dictType: 'conclusionreason5'}, {dictType: 'sex'}, {dictType: 'apply_type'},
    {dictType: 'material_type'}, {dictType: 'label_type'}, {dictType: 'conclusion'},

    {dictType: 'collectedmode'}, {dictType: 'policy_status'}, {dictType: 'get_duty_kind'}, {dictType: 'queue_claim_status'}, {dictType: 'case_type'},
    {dictType: 'incidenttype'}, {dictType: 'relationtoinsured'}, {dictType: 'negotiation_type'}, {dictType: 'negotiation_status'},
    {dictType: 'surve_types'}, {dictType: 'task_type'}, {dictType: 'survereason'}, {dictType: 'investigate_status'},
    {dictType: 'survey_source'}, {dictType: 'initiate_reasons'}, {dictType: 'second_initiate_reasons'}, {dictType: 'casecharacter'},
    {dictType: 'refund_type'}, {dictType: 'is_refund'}, {dictType: 'remarks_type'}, {dictType: 'diseasetype'},
    {dictType: 'risk_code'}, {dictType: 'risk_level'}, {dictType: 'disability_code'}, {dictType: 'deformity_code'},
    {dictType: 'operation_code'}, {dictType: 'accident_type'}, {dictType: 'invoicetype'}, {dictType: 'tumormorphologicalcode'},
    {dictType: 'occupationname'}, {dictType: 'Identificationresults'}, {dictType: 'card_type'},
    {dictType: 'apply_way'}, {dictType: 'collect_way'}, {dictType: 'payee_insuredrelationship'}, {dictType: 'bnf_occupationbnf_grade'},
    {dictType: 'bnf_grade'}, {dictType: 'application_category'}, {dictType: 'current_state'},
    {dictType: 'is_cremation'}, {dictType: 'relation_ship_register'}, {dictType: 'physicalpartsconclusion'}, {dictType: 'image_type'}, {dictType: 'supplementmode'},
    {dictType: 'status_supple'}, {dictType: 'supplementaryconclusion'}, {dictType: 'negotiation_mode'}, {dictType: 'acceptreason'},
    {dictType: 'casePoint_type'}, {dictType: 'promotionbasis'}, {dictType: 'reject_reason'}, {dictType: 'reject_type'},
    {dictType: 'paymentmode'}, {dictType: 'exemptionstatus'}, {dictType: 'bnf_occupation'},
  ]
  export default {
    components: {
      materialInfoCom,
      // 模态框
      reportModul,
      trialReporModul,
      fileListModul,
      negotiatModul,
      handleModul,
      caseSplitModul,

      toneInfoModul,
      fillsModul,
      breakOffModul,
      baseinfo,
      //newPayeeInfo,
      newPolicyInfo,
      newPastInfo,
      newRiskInfo,

      newReportNotes,
      newCaseLabel,
      newEventInfo,
      newDiseaseInfo,
      newBillInfo,
      newCompenInfo,
      newLoag,
      payconclusion,
      errorNotice,
      confirePayee,
    },
    provide() {
      return {
        getCaseRemark: this.getCaseRemark,
        getMateriaList: this.getMateriaList,
        getPayDetail: this.getPayDetail,
        getDangerInfo: this.getDangerInfo,
        isSuspend: this.isSuspend,
        getImageInfo: this.getImageInfo,
        getEventInfoList: this.getEventInfoList,
        getPayeeInfo: this.getPayeeInfo,
        getCountInvestigaeNumber: this.getCountInvestigaeNumber
      }
    },
    mixins: [mixinAnchor],
    /*    dicts: [
          'channel_source', 'relation_ship_apply', 'applicant_idtype', 'apply_type',
          'applicant_sex', 'payment_conclusion', 'apply_sourcetype', 'tasknode', 'examine_conclusion',
          'nativeplace', 'payee_occupation', 'casestep', 'examine_reject', 'conclusionreason',
          'err_type', 'accommodation_type', 'modify_type', 'invest_err_type', 'conclusionreason2','conclusionreason3',
          'conclusionreason4',
          'conclusionreason5','sex', 'apply_type','material_type','label_type','conclusion'
        ],*/
    computed: {
      disabled() {
        return this.status === 'show'
      },
      ...mapGetters([
        'sidebar'
      ])
    },
    filters: {
      numFilter(value) {
        let realVal = ''
        if (!isNaN(value) && value !== '') {
          // 截取当前数据到小数点后两位
          realVal = parseFloat(value).toFixed(2)
        } else {
          realVal = ''
        }
        return realVal
      },
      formatApprai(val) {
        if (val) {
          return moment(val).format('YYYY-MM-DD')
        }
      },
    },
    data() {
      // 校验金额（数字 最多三位小数）
      const isAmount = (rule, value, callback) => {
        if (value) {
          // const regx = /^(\d+|\d+\.\d{1,3})$/   //不能为负
          const regx = this.isAppeal ? /^-?\d+(\.\d{1,3})?$/ : /^(\d+|\d+\.\d{1,3})$/
          if (!regx.test(value)) {
            callback(new Error('无效输入'))
          } else {
            if (this.payInfo.conclusion === '4' || this.payInfo.conclusion === '5') {
              callback()
            } else {
              if (this.payInfo.explanation) {
                if (Number(this.payInfo.payableamount) < Number(value)) {
                  callback(new Error('实赔金额不能大于理算金额！'))
                } else {
                  callback()
                }
              } else {
                callback()
              }
            }
          }
        } else {
          if (value == 0) {
            callback()
          } else {
            callback(new Error('实赔金额必填!'))
          }
        }
      }
      return {
        isFlag: false,
        rules: {
          conclusionContent: [
            {required: true, message: '请输入回退原因', trigger: 'blur'},
          ],
        },
        table: {
          name: '张三',
          sex: '男',
          idNo: '89898989898989898',
          date: '2020-09-09',
          orangeList: [
            {
              name: '身份证',
              num: '2份'
            },
            {
              name: '原件',
              num: '7份'
            }
          ]
        },
        activetab: '1',
        labelType: {
          '01': 'VIP',
          '02': '爱投诉',
          '03': '高风险'
        },
        conclusionreasonList: [], // 结论依据
        showHeader: false,
        navFlag: true,
        watchForm: {
          applicationInfo: {},
          insuredInfo: {},
          payInfo: {},
          insuredBaseInfo: {},
          eventInfo: []
        },
        caselabelInfo: {
          data: []
        }, // 案件标签数据
        invoicelistInfo: [], // 账单信息数据
        disabledClass: false,
        htmlTitle: '受理回执信息',
        disabledCon: false,
        submitLoading: false,
        rejectReturnFlag: false,
        reportCount: 0, // 关联报案条数
        negotiationCount: 0, // 协谈条数
        fillsCount: '0', // 补材条数
        currentData: moment(new Date()).format('YYYY-MM-DD HH:mm'),
        restaurants: [],
        errDoms: [],
        disabledFinalamount: false,
        activeName: 'anchor-0',
        reportVis: false,
        trialReporVis: false,
        pdfAcceptFlag: false,
        reportType: '',
        fileListVis: false,
        fillisVis: false,
        negotiatVis: false,
        ruleForm: {
          conclusionContent: '',
        },
        diaTitle: '',
        handleVis: false,
        supplementaryno: '', // 补财号
        splitVis: false,
        policyVis: false,
        breakVis: false,
        toneVis: false,
        dialogVisible: false,
        saveconsdialogVisible: false,
        spotCheckFlag: false, // 结案抽检工作池
        conclusionDisable: false,
        disabledConclusion: false, // 不通过时是否禁用
        resultForm: {},
        tempScrollId: '',
        basicInfo: {},
        status: '',
        node: '',
        relationCounts: 0,
        btnArr: [],
        acceptLoading: false,
        reportConfirmLoading: false,
        // 初审锚点
        firstTrialArr: [
          {label: '基本信息', name: '#anchor-1'},
          {label: '保单信息', name: '#anchor-2'},
          {label: '既往信息', name: '#anchor-3'},
          {label: '风控查询', name: '#anchor-4'},
          {label: '材料信息', name: '#anchor-5'},
          {label: '赔案备注', name: '#anchor-6'},
          {label: '案件标签', name: '#anchor-7'},
        ],
        nodeStatus: {
          'accept': 'C001',
          'classificat': 'C002',
          'input': 'C003',
          'firstTrial': 'C004',
          'review': 'C005',
          'complex': 'C006',
          'spotCheck': 'C007',
          'correct': 'C008',
        },
        // 初审页面数据
        claimno: '', // 赔案号
        pdfLoading: false,
        applicationInfo: {}, // 基本信息数据
        insuredInfo: {}, // 被保人信息
        finalamount: null, // 实赔金额
        payInfo: {
          conclusionreason: null,
          conclusion: null,
          explanation: null
        }, // 赔付信息
        expList: [],
        payeeInfo: [], // 领款人信息
        policyInfo: [], // 保单信息
        dangerInfo: {}, // 出险信息
        eventInfo: [], // 事件信息
        caseRemark: {
          data: []
        }, // 赔案备注信息
        exemptionTitle: '',
        exemptionTitleFlag: true,
        incidentnolist: [], // 事件号列表
        fixInfo: {
          exceptioninfo: []
          // stationtime: null,
          // exceptioninfo: []
        },
        accidentInfoFrom: {}, // 出险信息
        placeholder: '', // 暂停和不予受理时的默认提示
        pastInfo: {
          negotiation: [],
          survey: [],
          claim: [],
          advance: []
        }, // 既往信息
        materialInfo: {
          data: [],
          typeList: []
        }, // 材料信息
        // 补充物理件
        supplementaryFrom: {},
        supplementaryArr: [],
        payDetail: {}, // 赔付明细
        // 赔付信息规则
        noAllList: [], // 不齐全资料列表
        payInfoRules: {
          finalamount: [{validator: isAmount, trigger: 'blur', required: true}], // 实赔金额
          conclusion: [{trigger: 'change', required: true, message: '赔付结论必填'}], // 赔付结论
          conclusionreason: {trigger: 'change', required: true, message: '赔付依据必填'}, // 赔付依据
          explanation: [{trigger: ['blur', 'change'], required: true, message: '赔付说明必填'}] // 赔付说明
        },
        // 报案页面数据
        acceptFlag: false, // 受理未确认标识
        registerNo: '', // 报案号
        registerSource: '', // 报案来源
        registerStatus: '', // 报案状态
        insuredSave: false, // 被保人信息保存标志
        reportSave: false, // 报案人信息保存标志
        applySave: false, // 申请信息保存标志
        applicantSave: false, // 申请人信息保存标志
        insuredNo: '',
        insuredName: '',
        insuredBaseInfo: null, // 被保人信息
        billsInfo: [], // 发票信息
        // 时间轴信息
        timelineInfo: [],
        loadTimeline: false,
        isrequired: false,
        errortypeRequired: false,
        suspendFlag: false,
        fillMount: 0, // 暂时存储案件层金额
        // 影像文件
        imageInfo: {
          originallist: {
            list: [],
            previewList: []
          },
          supplementlist: {
            list: [],
            previewList: []
          },
          surveylist: {
            list: [],
            previewList: []
          },
          supplementpartlist: {
            list: [],
            previewList: []
          }
        },
        hasSelectList: [], // 匹配的保单
        conclusionTypeData: [],
        tabLabels: {
          originallist: [],
          supplementlist: [],
          supplementpartlist: [],
          surveylist: []
        },
        invoiceList: [], //
        imageLoad: false,
        iframeShow: false,
        barCodeList: [],
        barcode_option: {
          displayValue: true, // 是否默认显示条形码数据
          background: '#fff', // 条形码背景颜色
          valid: function (valid) {
          },
          width: '1px', // 单个条形码的宽度
          height: '40px',
          fontSize: '12px' // 字体大小
        },
        caseLastNodeInfo: {},
        disableFlag: false,
        operationGroupUserDOList: [], // 审核人列表
        investigaeNumber: 0, // 提调数量
        querys: null,
        sopckDisabled: false,
        pdfMessage: {},
        pdfInfo: {
          applicantReasons: []
        },
        isAppeal: false, // 是否申诉纠错
        calseExemptionFrom: {},
        timeInfo: {},
        dictList: [],
        istpolicyListDisablsed: false,
        firstTrialReturnMedal: false,
        channel_sourceOptions: [],
        relation_ship_applyOptions: [],
        applicant_idtypeOptions: [],
        apply_typeOptions: [],
        applicant_sexOptions: [],
        payment_conclusionOptions: [],
        apply_sourcetypeOptions: [],
        tasknodeOptions: [],
        examine_conclusionOptions: [],
        nativeplaceOptions: [],
        payee_occupationOptions: [],
        casestepOptions: [],
        examine_rejectOptions: [],
        conclusionreasonOptions: [],
        err_typeOptions: [],
        accommodation_typeOptions: [],
        modify_typeOptions: [],
        invest_err_typeOptions: [],
        conclusionreason2Options: [],
        conclusionreason3Options: [],
        conclusionreason4Options: [],
        conclusionreason5Options: [],
        sexOptions: [],
        material_typeOptions: [],
        label_typeOptions: [],
        conclusionOptions: []
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.channel_sourceOptions = this.dictList.find(item => {
        return item.dictType == 'channel_source'
      }).dictDate
      this.relation_ship_applyOptions = this.dictList.find(item => {
        return item.dictType == 'relation_ship_apply'
      }).dictDate
      this.applicant_idtypeOptions = this.dictList.find(item => {
        return item.dictType == 'applicant_idtype'
      }).dictDate
      this.apply_typeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_type'
      }).dictDate
      this.applicant_sexOptions = this.dictList.find(item => {
        return item.dictType == 'applicant_sex'
      }).dictDate
      this.payment_conclusionOptions = this.dictList.find(item => {
        return item.dictType == 'payment_conclusion'
      }).dictDate
      this.apply_sourcetypeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_sourcetype'
      }).dictDate
      this.tasknodeOptions = this.dictList.find(item => {
        return item.dictType == 'tasknode'
      }).dictDate
      this.examine_conclusionOptions = this.dictList.find(item => {
        return item.dictType == 'examine_conclusion'
      }).dictDate
      this.nativeplaceOptions = this.dictList.find(item => {
        return item.dictType == 'nativeplace'
      }).dictDate
      this.payee_occupationOptions = this.dictList.find(item => {
        return item.dictType == 'payee_occupation'
      }).dictDate
      this.casestepOptions = this.dictList.find(item => {
        return item.dictType == 'casestep'
      }).dictDate
      this.examine_rejectOptions = this.dictList.find(item => {
        return item.dictType == 'examine_reject'
      }).dictDate
      this.conclusionreasonOptions = this.dictList.find(item => {
        return item.dictType == 'conclusionreason'
      }).dictDate
      this.err_typeOptions = this.dictList.find(item => {
        return item.dictType == 'err_type'
      }).dictDate
      this.accommodation_typeOptions = this.dictList.find(item => {
        return item.dictType == 'accommodation_type'
      }).dictDate
      this.modify_typeOptions = this.dictList.find(item => {
        return item.dictType == 'modify_type'
      }).dictDate
      this.invest_err_typeOptions = this.dictList.find(item => {
        return item.dictType == 'invest_err_type'
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
      this.sexOptions = this.dictList.find(item => {
        return item.dictType == 'sex'
      }).dictDate
      this.material_typeOptions = this.dictList.find(item => {
        return item.dictType == 'material_type'
      }).dictDate
      this.label_typeOptions = this.dictList.find(item => {
        return item.dictType == 'label_type'
      }).dictDate
      this.conclusionOptions = this.dictList.find(item => {
        return item.dictType == 'conclusion'
      }).dictDate
      this.isFlag = true
      if (this.$route.query) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        this.status = this.querys.status
        this.node = this.querys.node
        this.spotCheckFlag = this.querys.spotCheckFlag ? true : false
        this.claimno = this.querys.claimno ? decrypt(this.querys.claimno) : ''
        this.sopckDisabled = this.querys.sopckDisabled
        // this.sopckDisabled = this.sopckDisabled ? this.sopckDisabled : this.sopckDisabled === undefined ? true : this.sopckDisabled
        this.supplementaryno = this.querys.supplementaryno
        this.insuredName = this.querys.insuredName
        // this.claimno = this.$route.query.claimno
        // this.node = this.$route.query.node
        // this.claimno = this.$route.query.claimno
        // this.supplementaryno = this.$route.query.supplementaryno
        // this.insuredName = this.$route.query.insuredName
        this.btnArr = this.node === 'report' ? this.reportButtonArr
          : this.node === 'accept' ? this.acceptArr
            : this.node === 'input' ? this.inputArr : this.firstTrialArr
        if (this.node === 'physical') {
          this.btnArr = this.physicalArr
        }
        if (this.claimno) {
          this.getpdfMessage()
          this.disabledClass = true
        }
        // 初审及后续菜单初始化页面
        if (this.node === 'firstTrial' || this.node === 'review' || this.node === 'complex' || this.node === 'correct' || this.node === 'spotCheck' || this.node === 'materials' || this.node === 'physical') {
          this.initExamine()
          if (this.node === 'physical') {
            this.getMaterialPendingQuery() // 补充物理件
          }
        }
        if (this.node === 'input') {
          this.getCaseLastNodeInfo()
        }
        this.registerNo = this.querys.registerNo ? decrypt(this.querys.registerNo) : ''
        this.registerSource = this.querys.registerSource
        this.registerStatus = this.querys.registerStatus
        // this.registerNo = this.$route.query.registerNo
        // this.registerSource = this.$route.query.registerSource
        // this.registerStatus = this.$route.query.registerStatus
        if (this.node === 'report') {
          if (this.status === 'edit') {
            this.getRegisterDetailInfo()
            this.getCaseRemark()
            this.getMateriaList()
            this.getImageInfo('1', 1)
          }
          this.getFileCount()
        }
        if (this.node === 'accept' || this.node === 'input') {
          this.fixInfo.applicationsource = this.querys.applySource
          // this.fixInfo.applicationsource = this.$route.query.applySource
          if (this.status !== 'handle') {
            this.getClaimDetailInfo()
            this.getCaseRemark()
            this.getMateriaList()
            this.caseSchedule()
            this.caseRemarkList()
            if (this.claimno !== '' && this.claimno !== null) {
              this.getbasicInfo() // 顶部固定展示内容接口
            }
            //影像件接口
            this.getFileCount()
            this.getImageInfo('1', 1)
            this.getImageInfo('2', 1)
            this.getImageInfo('3', 1)
            this.getImageInfo('4', 1)
          } else {
            this.getFileCount()
          }
          if (this.claimno) {
            this.getInsuredInfo()
          }
        }
        if (this.node === 'input') {
          this.getBills()
          this.getLastOptlog()
        }
        this.imageDeal()
        // this.input_filter()
        if (this.claimno !== null && this.claimno !== "" && this.claimno !== undefined) {
          this.updateMaterialList()
        }
        if (this.node == 'report' || this.node == 'accept') {
          this.navFlag = this.querys.styleFlag == 'report' ? false : true
        }
        let casedetailFlag = this.querys.casedetailFlag ? false : true
        Bus.$on('updateFlag', data => {
          this.navFlag = data
        })
        Bus.$on('updatePhysicalStatus', data => {
          this.status = data
        })
        Bus.$on('changeList1', () => {
          this.changeList(4)
        })
        Bus.$on('updateaccidentInfo', data => {
          this.incidentnolist = data
          this.getAccidentInfo()
        })
        Bus.$on('updateInvoiceInfo', data => {
          this.incidentnolist = data
          this.getInvoicelistInfo()
        })
        Bus.$on('finalamountMethods', data => {
          this.fillMount = data
          this.payInfo.finalamount = (data)
        })
        if (this.claimno && casedetailFlag == true && (this.node == 'accept' || this.node == 'classificat' || this.node == 'input' || this.node == 'firstTrial' || this.node == 'review' || this.node == 'complex' || this.node == 'spotCheck' || this.node == 'correct')) {
          this.checkCaseDeal()
        }
        if (this.node == 'physical') {
          this.judePhysicalStatus()
        }
        if (this.registerNo && this.node == 'report') {
          this.judgeStatusReport()
        }
        // 判断是否申诉纠错案件
        this.isAppealCorrection(this.claimno)
      }
    },
    updated() {
    },
    methods: {

      getApply_sourcetypeOptions(data) {
        return this.selectDictLabel(this.apply_sourcetypeOptions, data)
      },
      initExamine() {
        this.getbasicInfo() // 顶部固定展示内容接口
        if (this.status === 'handle') {
          this.isSuspend() // 是否暂停
          this.getCaseLastNodeInfo() // 是否退回
        } else {
          this.getLastOptlog()
        }
        this.getInvoicelistInfo()
        this.getConclusionTypeData() // 赔付结论
        this.registerInfo()
        this.getPublicList()
        this.getNegotiationInfo()// 获取协谈列表条数
        this.getCountInvestigaeNumber()// 获取提调列表条数
        this.pastSupplementaryList()// 获取补材列表条数
        this.getApplicantInfo() // 获取申请人信息
        this.getInsuredInfo() // 获取被保人信息
        this.getPayInfo() // 获取赔付信息
        this.getPayeeInfo() // 获取领款人信息
        // this.relationPolicy() // 保单信息
        this.getNewpolicyInfo() // 保单信息
        this.getDangerInfo() // 风险信息
        // this.getAccidentInfo() // 出险信息查询
        // this.getEventInfoList() // 事件信息
        this.getEventInfo() // 新版事件信息
        // this.getCaseRemark() // 赔案备注信息
        this.getMateriaList() // 材料信息
        this.getPayDetail() // 赔付明细
        this.caseSchedule() // 查询操作轨迹信息
        this.caseRemarkList() //
        // this.getImageInfo() // 影像文件
        this.getImageInfo('1', 1)
        this.getImageInfo('2', 1)
        this.getImageInfo('3', 1)
        this.getImageInfo('4', 1)
        this.getFileCount()
        if (this.node === 'firstTrial') {
          this.getGroupUserList()
        }
      },
      // 查询赔付模板
      conclusionreasonChange() {
        this.loadAll()
      },
      // 判断当前节点的状态
      checkCaseDeal() {
        let params = {
          node: this.nodeStatus[this.node],
          claimno: this.claimno
        }
        checkCaseDeal(params).then(res => {
          if (res.data == true) {
            this.status = 'handle'
          } else {
            this.status = 'show'
            this.disableFlag = true
            this.acceptFlag = true
          }
        }).then(() => {
          if (this.querys.serchNode == true) {
            this.status = 'show'
            this.disableFlag = true
            this.acceptFlag = true
          }
        })
      },
      // 节点为物理件时判断状态
      judePhysicalStatus() {
        let params = {
          supplementaryno: this.supplementaryno,
          sign: '02'
        }
        let _this = this
        blockCheck(params).then(res => {
          if (res.data == true) {
            _this.status = 'handle'
          } else {
            _this.status = 'show'
          }
        })
      },
      scallReturn() {
        this.firstTrialReturnMedal = false
        this.ruleForm.conclusionContent = ''
      },
      getNewpolicyInfo() {
        getNewpolicyInfo(this.claimno).then(res => {
          if (res.status == '1') {
            this.policyInfo = res.data.tpolicyList || []
            this.policyInfo.map((item) => {
              if (item.ismatch == 'Y') {
                this.hasSelectList.push(item.ismatch)
              }
            })
            // this.$nextTick(()=>{
            //   this.policyInfo.forEach(row => {
            //     if(this.hasSelectList.indexOf(row.ismatch) >= 0){
            //       this.$refs.policytable.toggleRowSelection(row,true);
            //     }
            //   })
            // })
            this.istpolicyListDisablsed = res.data.save
          } else {
            this.$message.error('查询保单信息错误！')
          }
        })
      },
      getAccidentInfo() {
        let params = {}
        params.claimno = this.claimno
        params.incidentnolist = this.incidentnolist  //['E202010230001328','E202010230001329']
        newAccidentInfo(params).then(res => {
          if (res.status == '1') {
            if (res.data.casediseaselist) {
              res.data.casediseaselist.map((item, i) => {
                res.data.casediseaselist[i].judgmentbasis = res.data.casediseaselist[i].judgmentbasis.split(',')
                let params = {
                  '0': '4',
                  '1': '3',
                  '2': '2',
                  '3': '1',
                  '4': '5'
                }
                let subtype = params[res.data.casediseaselist[i].diseasetype]
                res.data.casediseaselist[i].judgmentbasis.map(list => {
                  this.reList = []
                  let subcode = res.data.casediseaselist[i].judgmentbasis.join(',')
                  let requetdata = {
                    type: '1',
                    subtype: subtype,
                    code: res.data.casediseaselist[i].diseasecode,
                    subcode: subcode
                  }
                  getProofNameBySubCode(requetdata).then(response => {
                    // this.reList = res.data
                    let arr = response.data
                    var list = []
                    arr.map((item, index) => {
                      res.data.casediseaselist[i].judgmentbasisName
                        ? res.data.casediseaselist[i].judgmentbasisName
                        : res.data.casediseaselist[i].judgmentbasisName = ''
                      res.data.casediseaselist[i].judgmentbasisName += ',' + item.proofname
                      list.push(item.proofname)
                    })
                    res.data.casediseaselist[i].judgmentbasis = res.data.casediseaselist[i].judgmentbasis
                    res.data.casediseaselist[i].judgmentbasisName = res.data.casediseaselist[i].judgmentbasisName.slice(1)
                    res.data.casediseaselist[i].judgmentbasisNameList = list
                  })
                  res.data.casediseaselist = [...res.data.casediseaselist]
                })
              })
            }
            this.accidentInfoFrom = res.data
          } else {
            this.$message.error('查询出险信息错误！')
          }
        })
      },
      // 实现自动生成生日，性别，年龄
      go(value, length, index) {
        let iden = value;
        let sex = null;
        let birth = null;
        let myDate = new Date();
        let month = myDate.getMonth() + 1;
        let day = myDate.getDate();
        let age = 0;
        if (length === 18) {
          age = myDate.getFullYear() - iden.substring(6, 10) - 1;
          sex = iden.substring(16, 17);
          birth = iden.substring(6, 10) + "-" + iden.substring(10, 12) + "-" + iden.substring(12, 14);
          if (iden.substring(10, 12) < month || iden.substring(10, 12) == month && iden.substring(12, 14) <= day) age++;
        }
        if (length === 15) {
          age = myDate.getFullYear() - iden.substring(6, 8) - 1901;
          sex = iden.substring(13, 14);
          birth = "19" + iden.substring(6, 8) + "-" + iden.substring(8, 10) + "-" + iden.substring(10, 12);
          if (iden.substring(8, 10) < month || iden.substring(8, 10) == month && iden.substring(10, 12) <= day) age++;
        }
        if (sex % 2 === 0)
          sex = '1';
        else
          sex = '0';
        // this.beneficiaryForm.tableData[index].sex = sex
        // this.beneficiaryForm.tableData[index].birthday = birth
      },
      caseRemarkList() {
        const type = this.node === 'report' ? '01' : '02'
        let node = ''
        node = this.nodeStatus[this.node]
        const params = {relationno: this.node === 'report' ? this.registerNo : this.claimno, type, node}
        caseRemarkList(params).then(response => {
          if (response.data) {
            this.caseRemark = response
            this.caseRemark.data.map((item) => {
              item.editFlag = false
            })
          } else {
            this.$message.error('查询赔案备注列表错误！')
          }
        })
      },
      // 判断报案节点状态
      judgeStatusReport() {
        judgeStatus(this.registerNo).then(res => {
          if (res.data == true) {
            this.status = 'handle'
            this.disableFlag = false
            this.acceptFlag = false
          } else {
            this.status = 'show'
            this.disableFlag = true
            this.acceptFlag = true
          }
        })
      },
      imageView() {
        let data = encodeURI(
          JSON.stringify({
            claimno: encrypt(this.claimno),
            node: this.node,
            status: this.rejectReturnFlag ? 'show' : status,
            imageLoad: this.imageLoad,
            // tabLabels: this.tabLabels,
            acceptFlag: this.acceptFlag,
            // imageInfo: this.imageInfo,
            disableFlag: this.disableFlag,
          })
        )
        const newpage = this.$router.resolve({
          name: 'imageshows',
          params: {},
          query: {data}
        })
        window.open(newpage.href, '_blank');
      },
      // 不予受理后修改状态
      changeButtonDisabled() {
        this.status = 'show'
      },
      firstTrialOver() {
        this.resultForm.conclusion = '01'
        this.confireReturn()
      },
      confireReturnag() {
        if (this.ruleForm.conclusionContent !== '' && this.ruleForm.conclusionContent !== null) {
          this.ruleForm.conclusionContent = this.ruleForm.conclusionContent + '  '
          this.ruleForm.conclusionContent = this.ruleForm.conclusionContent.replace(/\s+/g, "");
        }
        this.$refs.returnForm.validate((valid) => {
          if (valid) {
            this.confireReturn()
          } else {
            return false
          }
        })
      },
      firstTrialReturn() {
        this.firstTrialReturnMedal = true
        this.resultForm.conclusion = '02'
      },
      confireReturn() {
        //  this.$refs.resultForm.validate((valid) => {
        //   if (valid) {
        judgeBnf(this.claimno).then(response => {
          if (response.status === '1') {
            this.firstTrialReturnMedal = false
            if (response.data !== '' && this.resultForm.conclusion !== '02') {
              this.$message({message: response.data, type: 'warning'})
            } else {
              const params = {
                claimno: this.claimno,
                conclusion: this.resultForm.conclusion,
                explanation: this.resultForm.conclusion == '02' ? this.ruleForm.conclusionContent : '',
                // ...this.resultForm,
                tasknode: this.mapNode(this.node)
              }
              if (this.caseLastNodeInfo.conclusion === '02') {
                params.isreturn = 'Y'
                params.status = this.caseLastNodeInfo.status
                params.reviewer = this.caseLastNodeInfo.creator
                /*let nextCaseNode = this.dict.label.casestep[this.caseLastNodeInfo.tasknode]*/
                let nextCaseNode = this.selectDictLabel(this.casestepOptions, this.caseLastNodeInfo.tasknode)
                params.nexttasknode = this.mapNode(nextCaseNode)
              }
              if (this.querys.conclusionType) {
                this.submitLoading = true
                params.detailno = this.querys.detailno
                updateCheck(params).then(res => {
                  if (res.status === '1') {
                    this.$message({message: '操作成功！', type: 'success'})
                    // setTimeout(() =>{
                    //   this.goBack()
                    // },2000)
                  } else {
                    if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
                      this.$message({message: res.data.errMsg, type: 'error'})
                    } else {
                      this.$message({message: res.data.errMsg, type: 'warning'})
                    }
                  }
                }).finally(() => {
                  this.submitLoading = false
                  this.sopckDisabled = true
                })
              } else {
                if (this.resultForm.conclusion !== '02') {
                  this.saveConclusionCheck(params)
                } else {
                  this.saveConclusion(params)
                }
              }
            }
          }
        })
        // } else {
        //   return false
        // }
        // })
      },
      beforeunloadHandler(e) {
        e = e || window.event
        if (e) {
          e.returnValue = '关闭提示'
        }
        return '关闭提示'
      },
      // 实赔金额校验
      concluChange(value) {
        this.payInfo.conclusionreason = ''
        if (value == '3') {
          this.payInfo.finalamount = '0'
          this.disabledFinalamount = true
        } else {
          this.disabledFinalamount = false
          if (this.fillMount == 0) {
            this.payInfo.finalamount = this.payInfo.finalamount
          } else {
            this.payInfo.finalamount = (this.fillMount)
          }
        }
        this.conclusionreasonList = []
        this.$refs.payInfoForm.validateField('finalamount')
        if (value == '1') {
          this.conclusionreasonList = this.conclusionreasonOptions
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '2') {
          this.conclusionreasonList = this.conclusionreasonOptions
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '3') {
          this.conclusionreasonList = this.conclusionreason2Options
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '4') {
          this.conclusionreasonList = this.conclusionreason3Options
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '5') {
          this.conclusionreasonList = this.conclusionreason4Options
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else {
          this.conclusionreasonList = this.conclusionreason5Options
          this.disabledCon = false
          this.payInfoRules.conclusionreason.required = true
        }
      },
      // 获取案件标签列表
      getPublicList() {
        let params = {}
        params.claimNo = this.claimno
        getCasePublicList(params).then(res => {
          if (res.status == '1') {
            this.caselabelInfo.data = res.data == '未查询出数据' ? [] : res.data
            this.caselabelInfo.data.map((item, index) => {
              item.id = index + 1
            })
          } else {
            this.$message.error('查询案件标签列表异常！')
          }
        })
      },
      // 获取审核人列表
      getGroupUserList() {
        let params = 'claim_examine'
        // let params = this.mapNode(this.node)
        getGroupUserList(params).then(res => {
          if (res.status === '1') {
            this.operationGroupUserDOList = res.data
          } else {
            this.$message({message: res.data.errMsg, type: 'error'})
          }
        })
      },
      focusSerch() {
        this.loadAll()
      },
      getInvoicelistInfo() {
        let params = {}
        params.claimno = this.claimno
        params.incidentnolist = this.incidentnolist ? this.incidentnolist : []
        getInvoicelistInfo(params).then(res => {
          if (res != null && res.status == '1') {
            this.invoicelistInfo = res.data || []
            this.invoicelistInfo.map((item) => {
              item.zhenduan = (item.firstdiagnosisname == null ? '' : '【' + item.firstdiagnosisname + '】') + (item.seconddiagnosisname ? '【' + item.seconddiagnosisname + '】' : '') + (item.thirddiagnosisname ? '【' + item.thirddiagnosisname + '】' : '')
            })
            Bus.$emit('updatebilldata', this.invoicelistInfo)
          } else {
            this.$message.error('查询账单信息异常！')
          }
        })
      },
      // 获取报案列表数量
      registerInfo() {
        registerInfo(this.claimno).then(res => {
          if (res.status === '1') {
            this.reportCount = res.data ? res.data.length : 0
          }
        })
      },
      // 获取补材列表数量
      pastSupplementaryList() {
        let params = {
          relationno: this.claimno,
          type: '01'
        }
        pastSupplementaryList(params).then(res => {
          if (res.status === '1') {
            if (res.data && res.data.length) {
              let flag = res.data.find(f => {
                return f.status === '01'
              })
              if (flag) {
                this.fillsCount = '?'
              } else {
                this.fillsCount = res.data.length.toString()
              }
            }
          }
        })
      },
      // 事件信息查询
      getEventInfo() {
        getEventInfo(this.claimno).then(res => {
          if (res != null && res.status == '1') {
            var arr = []
            arr = res.data || []
            arr.forEach((item, index) => {
              item.id = index + 1
              return item
            })
            this.eventInfo = arr || []
          } else {
            this.$message.error('查询事件信息错误！')
          }
        })
      },
      // 获取补材列表数量
      getNegotiationInfo() {
        getNegotiationInfo(this.claimno).then(res => {
          if (res.status === '1') {
            if (res.data) {
              let flag = res.data.list.some(f => {
                return f.status === '01'
              })
              if (flag) {
                this.negotiationCount = '?'
              } else {
                this.negotiationCount = res.data.list.length
              }
            }
          }
        })
      },
      // 获取提调数量
      getCountInvestigaeNumber() {
        countInvestigaeNumber(this.claimno).then(res => {
          if (res.status == '1') {
            if (res.data.ralationInvastigation) {
              this.investigaeNumber = '?'
            } else {
              this.investigaeNumber = res.data.completeCount
            }
          }
          this.$forceUpdate()
        })
      },
      confireSaveConculsion() {
      },
      // 查看处理结论接口
      getLastOptlog() {
        // if (this.node === 'spotCheck' && this.sopckDisabled === undefined) {
        //   return
        // }
        let params = {
          claimno: this.claimno,
          node: this.mapNode(this.node),
          detailno: this.querys.detailno
        }
        if (this.sopckDisabled) {
          getCheckOptLog(params).then(res => {
            if (res.status === '1') {
              if (res.data) {
                let row = res.data
                this.$set(this.resultForm, 'conclusion', row.conclusion)
                this.$set(this.resultForm, 'reviewer', row.reviewer)
                this.$set(this.resultForm, 'rejectreason', row.rejectreason)
                this.$set(this.resultForm, 'errortype', row.errortype)
                this.$set(this.resultForm, 'explanation', row.explanation)
              }
            } else {
              this.$message({message: '请求出错！', type: 'error'})
            }
          })
        } else if (this.sopckDisabled === undefined) {
          delete params.detailno
          getLastOptlog(params).then(res => {
            if (res.status === '1') {
              if (res.data) {
                let row = res.data
                this.$set(this.resultForm, 'conclusion', row.conclusion)
                this.$set(this.resultForm, 'reviewer', row.reviewer)
                this.$set(this.resultForm, 'rejectreason', row.rejectreason)
                this.$set(this.resultForm, 'errortype', row.errortype)
                this.$set(this.resultForm, 'explanation', row.explanation)
              }
            } else {
              this.$message({message: '请求出错！', type: 'error'})
            }
          })
        }
      },
      getCaseLastNodeInfo() {
        let params = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        getCaseLastNodeInfo(params).then(res => {
          if (res.status === '1') {
            this.caseLastNodeInfo = res.data
          } else {
            this.$message({message: '请求出错！', type: 'error'})
          }
        })
      },
      isSuspend() {
        isSuspend(this.claimno).then(res => {
          if (res.status === '1') {
            this.suspendFlag = res.data
          } else {
            this.$message({message: '请求出错！', type: 'error'})
          }
        })
      },
      // 初始化调用返回顶部固定位置的展示信息
      getbasicInfo() {
        const params = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        basicInfo(params).then(response => {
          if (response.status === '1') {
            this.fixInfo = response.data
            this.fixInfo.stationtime = response.data.stationtime
            this.fixInfo.applicationsource = this.fixInfo.applicationsource == null ? '1' : this.fixInfo.applicationsource
            this.fixInfo.exceptioninfo = response.data.exceptioninfo == null ? [] : response.data.exceptioninfo
            this.$forceUpdate()
          } else {
            this.$message({message: '请求出错！', type: 'error'})
          }
        })
      },
      formateAppli: function (val) {
        let str = ''
        if (val) {
          let arrVal = val.split(',')
          arrVal.map(item => {
            /*this.dict.label.apply_type[item]*/
            if (this.selectDictLabel(this.apply_typeOptions, item)) {
              /*this.dict.label.apply_type[item]*/
              str += '｜' + this.selectDictLabel(this.apply_typeOptions, item)
            }
          })
          return str.slice(1)
        } else {
          return val
        }
      },
      getpdfMessage() {
        getReceipt(this.claimno).then(response => {
          if (response.status === '1') {
            this.pdfInfo = response.data
            // this.$refs['pdf'].claimno = this.claimno
            // this.$refs['pdf'].pdfInfo = response.data
            let arr = response.data.materials.filter((item) => {
              return item.number > 0
            })
            this.pdfInfo.materials = arr
          }
        })
      },
      getPdfs() {
        this.pdfLoading = true
        this.disabledClass = true
        downloadPdf(this.claimno).then(res => {
          downloadFile(res, '受理回执信息', 'pdf')
          this.pdfLoading = false
          this.disabledClass = false
        })
      },
      // 查询申请人信息
      getApplicantInfo() {
        getApplicantInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.applicationInfo = response.data
          } else {
            this.$message({message: '查询申请人信息错误！', type: 'error'})
          }
        })
      },
      // 查询被保人信息
      getInsuredInfo() {
        getInsuredInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.insuredInfo = response.data
            this.pastSurvey(this.insuredInfo.customerno)
            this.pastNegotiation(this.insuredInfo.customerno)
            this.getPastClaimsList(this.insuredInfo.customerno)
            // nsuredInfo.customerno
          } else {
            this.$message({message: '查询被保人信息错误！', type: 'error'})
          }
        })
      },
      // 既往调查
      pastSurvey(insuredno) {
        let data = {
          insuredno: insuredno || '',
          pageno: 1,
          pagesize: 100,
          ispage: false
        }
        pastSurvey(data).then(response => {
          if (response.status === '1') {
            this.pastInfo.survey = response.data && response.data.list || []
          } else {
            this.$message({message: '查询既往调查错误！', type: 'error'})
          }
        })
      },
      // 既往协谈
      pastNegotiation(insuredno) {
        pastNegotiation(insuredno).then(response => {
          if (response.status === '1') {
            this.pastInfo.negotiation = response.data
          } else {
            this.$message({message: '查询既往协谈错误！', type: 'error'})
          }
        })
      },
      // 既往赔案
      getPastClaimsList(insuredno) {
        getPastClaimsList(insuredno).then(response => {
          if (response.status === '1') {
            this.pastInfo.claim = response.data
          } else {
            this.$message({message: '查询既往赔案错误！', type: 'error'})
          }
        })
      },
      // 查询赔付信息
      getPayInfo() {
        getPayInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.payInfo = response.data
            this.conclusionreasonList = []
            if (this.payInfo.conclusion == '1') {
              this.conclusionreasonList = this.conclusionreasonOptions
            } else if (this.payInfo.conclusion == '2') {
              this.conclusionreasonList = this.conclusionreasonOptions
            } else if (this.payInfo.conclusion == '3') {
              this.conclusionreasonList = this.conclusionreason2Options
            } else if (this.payInfo.conclusion == '4') {
              this.conclusionreasonList = this.conclusionreason3Options
            } else if (this.payInfo.conclusion == '5') {
              this.conclusionreasonList = this.conclusionreason4Options
            } else {
              this.conclusionreasonList = this.conclusionreason5Options
            }
            if (response.data.finalamount != '' && response.data.finalamount != null && response.data.finalamount != undefined) {
              this.payInfo.finalamount = parseFloat(response.data.finalamount).toFixed(2)
            }
            this.finalamount = response.data.finalamount
            this.loadAll()
          } else {
            this.$message({message: '查询赔付信息错误！', type: 'error'})
          }
        })
      },
      // 保存赔付信息
      savePayInfo() {
        if (this.payInfo.explanation !== '' && this.payInfo.explanation !== null) {
          this.payInfo.explanation = this.payInfo.explanation.replace(/\s+/g, "");
          if (this.payInfo.explanation == '') {
            this.$message.warning('请填写赔付说明信息！')
            return false
          }
        }
        this.$refs['payInfoForm'].validate(valid => {
          if (valid) {
            const {conclusion, finalamount, conclusionreason, explanation, applicationreason} = this.payInfo
            const params = {
              claimno: this.claimno,
              conclusion,
              finalamount,
              conclusionreason,
              explanation,
              applicationreason
            }
            savePayInfo(params).then(response => {
              if (response.status === '1') {
                this.getPayInfo()
                if (this.payeeInfo.length) {
                  this.payeeInfo.map((item, i) => {
                    if (item.payoutratio) {
                      let params = {
                        claimno: this.claimno,
                        payoutratio: item.payoutratio
                      }
                      calculationPayAmount(params).then(res => {
                        if (res.status === "1") {
                          if (res.data === 0) {
                            // this.amountFlag=true
                          }
                          this.$set(this.payeeInfo[i], 'payamount', res.data)
                        } else {
                          // this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: "error"})
                        }
                      })
                    } else {
                      this.$set(this.payeeInfo[i], 'payamount', null)
                    }
                  })
                }
                this.$message({message: '操作成功！', type: 'success'})
              } else {
                this.$message({message: response.data.errMsg, type: 'error'})
              }
            })
          }
        })
      },
      // 查询领款人信息
      getPayeeInfo() {
        getPayeeInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.payeeInfo = response.data
          } else {
            this.$message({message: '查询领款人信息错误！', type: 'error'})
          }
        })
      },
      // 查询保单信息
      relationPolicy() {
        relationPolicy(this.claimno).then(response => {
          if (response.status === '1') {
            this.policyInfo = response.data || []
          } else {
            this.$message({message: '查询保单信息错误！', type: 'error'})
          }
        })
      },
      // 查询风险信息
      getDangerInfo() {
        getDangerInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.dangerInfo = response.data
          } else {
            this.$message({message: '查询风险信息错误！', type: 'error'})
          }
        })
      },
      // 查询事件信息
      getEventInfoList() {
        getEventInfoList(this.claimno).then(response => {
          if (response.status === '1') {
            var arr = []
            arr = response.data || []
            arr.forEach((item, index) => {
              item.id = index + 1
              item.casediseaselist.forEach((ele) => {
                ele.judgmentbasisnameList = ele.judgmentbasisname == null ? '' : ele.judgmentbasisname.indexOf("$") == -1 ? [ele.judgmentbasisname] : ele.judgmentbasisname.split('$')
                return ele
              })
              return item
            })
            this.eventInfo = arr || []
          } else {
            this.$message({message: '查询事件信息错误！', type: 'error'})
          }
        })
      },
      /**
       * 事件信息保存后需更新材料信息列表
       */
      relationEvent() {
        // this.getEventInfoList()
        this.getMateriaList()
      },
      // 修改页面的编辑与只读状态
      updateStatus(status) {
        this.status = status
      },
      // 赔案备注信息
      getCaseRemark() {
        // type 报案 ‘01’， 赔案：‘02’
        const type = this.node === 'report' ? '01' : '02'
        let node = ''
        node = this.nodeStatus[this.node]
        const params = {relationno: this.node === 'report' ? this.registerNo : this.claimno, type, node}
        getCaseRemark(params).then(response => {
          if (response.status === '1') {
            // if(response.data){
            //   this.caseRemark = response
            //   this.caseRemark.data.map((item) =>{
            //     item.editFlag = false
            //   })
            // }
          } else {
            this.$message({message: '查询赔案备注信息错误！', type: 'error'})
          }
        })
      },
      // 获取材料信息
      getMateriaList() {
        let params = this.claimno ? this.claimno : this.registerNo
        getMateriaList(params).then(response => {
          if (response.status === '1') {
            this.materialInfo.data = response.data
          } else {
            this.$message({message: '查询材料信息错误！', type: 'error'})
          }
        })
      },
      getConclusionTypeData() {
        this.conclusionTypeData = this.conclusionOptions
      },
      // 获取赔付明细
      getPayDetail() {
        getPayDetail(this.claimno).then(response => {
          if (response.status === '1') {
            if (response.data) {
              let from = response.data.casegetdutylist
              let arr = this.conclusionTypeData
              let isExemptArr = []
              let exemptArr = []
              isExemptArr = arr.filter((item) => {
                return item.value == '6' || item.value == '7'
              })
              exemptArr = arr.filter((item) => {
                return item.value !== '6' && item.value !== '7'
              })
              response.data.casegetdutylist.map((item, i) => {
                if (item.isExempt == true) {
                  item.conclusionList = isExemptArr
                } else {
                  item.conclusionList = exemptArr
                }
                if (item.conclusion == '1') {
                  item.reList = this.conclusionreasonOptions
                  item.disabledCon = false
                  item.disabledFinalamount = false
                } else if (item.conclusion == '2') {
                  item.reList = this.conclusionreasonOptions
                  item.disabledFinalamount = false
                  item.disabledCon = false
                } else if (item.conclusion == '3') {
                  item.reList = this.conclusionreason2Options
                  item.disabledFinalamount = true
                  item.disabledCon = false
                } else if (item.conclusion == '4') {
                  item.reList = this.conclusionreason3Options
                  item.disabledFinalamount = false
                  item.disabledCon = false
                } else if (item.conclusion == '5') {
                  item.reList = this.conclusionreason4Options
                  item.disabledFinalamount = false
                  item.disabledCon = false
                } else {
                  item.reList = this.conclusionreason5Options
                  item.disabledFinalamount = false
                  item.disabledCon = false
                }
                return item
              })
              this.payDetail = response.data.casegetdutylist
            }
          } else {
            this.$message({message: '查询赔付明细错误！', type: 'error'})
          }
        })
      },
      // 查询补充物理件
      getMaterialPendingQuery() {
        const params = {}
        params.claimno = this.claimno || ''
        params.supplementaryno = this.supplementaryno || ''
        materialPendingQuery(params).then(res => {
          if (res.data) {
            if (res.data.errCode !== '' || res.data.errCode !== undefined) {
              this.$message({message: '查询物理件错误！', type: 'error'});
            }
          } else {
            this.supplementaryFrom = {
              tableData: res || []
            }
            this.noAllList = []
            res.length ? res.map((item) => {
              let ele = {}
              ele.materialtype = item.materialtype
              ele.materialname = item.materialname
              this.noAllList.push(ele)
            }) : []
          }
        })
      },
      // 时间轴
      caseSchedule() {
        this.loadTimeline = true
        caseProcessLog(this.claimno).then(response => {
          if (response.status === '1') {
            this.timelineInfo = response.data
          } else {
            this.$message({message: '查询操作轨迹错误！', type: 'error'})
          }
        }).finally(() => {
          this.loadTimeline = false
        })
      },
      getFileCount() {
        let temp = [
          {label: "全部", name: '00', total: 0},
          {label: "其他证明材料", name: '9', total: 0},
          {label: "理赔申请书", name: '0', total: 0},
          {label: "身份证", name: '1', total: 0},
          {label: "关系证明", name: '2', total: 0},
          {label: "银行卡", name: '3', total: 0},
          {label: "医疗票据", name: '4', total: 0},
          {label: "病历", name: '5', total: 0},
          {label: "费用清单", name: '6', total: 0},
          {label: "检查报告", name: '7', total: 0},
          {label: "保险事故证明", name: '8', total: 0}
        ]
        let businessNo = ''
        if (this.node === 'report') {
          businessNo = this.registerNo
        } else {
          businessNo = this.claimno
        }
        getFileCount(businessNo).then(res => {
          if (res.status === '1') {
            if (res.data) {
              this.tabLabels = res.data
            } else {
              for (let key in this.tabLabels) {
                this.tabLabels[key] = temp
              }
            }
          } else {
            for (let key in this.tabLabels) {
              this.tabLabels[key] = temp
            }
          }
        })
      },
      changeList(type) {
        if (type === '1') {
          this.imageInfo.originallist.list = []
          this.imageInfo.originallist.previewList = []
        } else if (type === '2') {
          this.imageInfo.supplementlist.list = []
          this.imageInfo.supplementlist.previewList = []
        } else if (type === '3') {
          this.imageInfo.surveylist.list = []
          this.imageInfo.surveylist.previewList = []
        } else if (type === '4') {
          this.imageInfo.supplementpartlist.list = []
          this.imageInfo.supplementpartlist.previewList = []
        }
      },
      // 影像文件
      getImageInfo(type, pageno, subtype = null, change = false) {
        let businessNo = ''
        if (this.node === 'report') {
          businessNo = this.registerNo
        } else {
          businessNo = this.claimno
        }
        if (businessNo !== null && businessNo !== '' && businessNo !== undefined) {
          this.imageLoad = true
          let params = {
            claimno: businessNo,
            type,
            subtype,
            pageno,
            pagesize: 2000
          }
          getImageInfo(params).then(response => {
            if (response != null && response.status === '1') {
              // 切换标签或上传触发
              if (change) {
                this.changeList(type)
              }
              if (params.type === '1') {
                if (response.data) {
                  response.data.list.map((item, i) => {
                    this.imageInfo.originallist.list.push(item)
                    this.imageInfo.originallist.previewList.push('data:image/jpeg;base64,' + item.src)
                  })
                }
              } else if (params.type === '2') {
                if (response.data) {
                  response.data.list.map((item, i) => {
                    this.imageInfo.supplementlist.previewList.push('data:image/jpeg;base64,' + item.src)
                    this.imageInfo.supplementlist.list.push(item)
                  })
                }
              } else if (params.type === '3') {
                if (response.data) {
                  response.data.list.map((item, i) => {
                    this.imageInfo.surveylist.previewList.push('data:image/jpeg;base64,' + item.src)
                    this.imageInfo.surveylist.list.push(item)
                  })
                }
              } else if (params.type === '4') {
                if (response.data) {
                  response.data.list.map((item, i) => {
                    this.imageInfo.supplementpartlist.previewList.push('data:image/jpeg;base64,' + item.src)
                    this.imageInfo.supplementpartlist.list.push(item)
                    this.imageInfo.supplementpartlist.previewList = [...this.imageInfo.supplementpartlist.previewList]
                    this.imageInfo.supplementpartlist.list = [...this.imageInfo.supplementpartlist.list]
                  })
                }
              }
            } else {
              this.$message({message: '查询影像文件错误！', type: 'error'})
            }
          }).finally(() => {
            this.imageLoad = false
          })
        }
      },
      querySearch(queryString, cb) {
        let expList = this.expList
        let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
        cb(results ? results : [])
      },
      createFilter(queryString) {
        return (expList) => {
          return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        }
      },
      loadAll() {
        this.expList = []
        const params = {
          templatetype: '01',
          supplementarymode: '',
          circulationvalue1: this.payInfo.conclusion,
          circulationvalue2: this.payInfo.conclusionreason
        }
        getContents(params).then(res => {
          if (res != null && res.status == '1') {
            res.data.forEach(item => {
              this.expList.push({value: item})
            })
          }
        })
      },
      invoiceHandle() {
        const newpage = this.$router.resolve({
          name: 'invoiceHandle',
          params: {},
          query: {
            node: 'input',
            claimNo: encrypt(this.claimno),
            insuredInfo: JSON.stringify(this.insuredBaseInfo),
            insuredName: this.insuredName
          }
        })
        window.open(newpage.href, '_blank');
      },
      openReporInfo(type) {
        this.reportType = type
        if (this.node === 'accept' || this.node === 'input') {
          this.reportVis = true
        } else {
          this.trialReporVis = true
        }
      },
      openFileList() {
        this.fileListVis = true
      },
      openFillis() {
        this.fillisVis = true
      },
      openNegotiat() {
        this.negotiatVis = true
      },
      openHandle(title, placeholder) {
        this.diaTitle = title
        this.handleVis = true
        this.placeholder = placeholder
      },
      openSplit() {
        this.splitVis = true
      },
      openBreakOff() {
        this.breakVis = true
      },
      openTone() {
        this.toneVis = true
      },
      conclusionChange(val) {
        if (this.node === 'firstTrial') {
          this.$set(this.resultForm, 'reviewer', '')
        }
        // sopckDisabled
        if (val === '03') {
          this.disabledConclusion = false
          this.status = 'show'
          this.rejectReturnFlag = true
        } else if (val === '01') {
          this.disabledConclusion = false
          this.status = 'handle'
          this.rejectReturnFlag = false
        } else {
          this.disabledConclusion = true
          this.status = 'handle'
          this.rejectReturnFlag = false
        }
        if (this.sopckDisabled === false) {
          this.status = 'show'
        }
        this.$refs.resultForm.clearValidate(['rejectreason', 'errortype'])
        val === '01' ? this.isrequired = false : this.isrequired = true
      },
      rejectreasonChange(val) {
        this.$refs.resultForm.clearValidate(['rejectreason', 'errortype'])
        if (this.resultForm.errortype) {
          this.$set(this.resultForm, 'errortype', '')
        }
      },
      confireExemption() {
        saveExemption(this.claimno).then(res => {
          if (res.status == '1') {
            this.saveConclusion(this.calseExemptionFrom)
            this.dialogVisible = false
          } else {
            this.$message({message: res.data.errMsg ? res.data.errMsg : '豁免失败！', type: 'error'})
            this.dialogVisible = false
          }
        })
      },
      calseExemption() {
        this.saveConclusion(this.calseExemptionFrom)
        this.dialogVisible = false
      },
      calseExemption2() {
        this.saveConclusion(this.calseExemptionFrom)
        this.dialogVisible = false
      },
      saveConclusionCheck(params) {
        let query = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        this.calseExemptionFrom = params
        this.submitLoading = true
        saveConclusionCheck(query).then(res => {
          if (res.status === '1') {
            if (res.data.istips == true) {
              this.$message({message: res.data.msg, type: 'error'})
              return false
            } else {
              if (res.data.msg == '') {
              } else {
                this.$message({message: res.data.msg, type: 'warning'})
              }
              if (this.node == 'firstTrial' || this.node == 'review' || this.node == 'spotCheck' || this.node == 'complex' || this.node == 'correct') {
                queryAccordWithExemption(this.claimno).then(res => {
                  if (res.status == '1') {
                    if (res.data == 0) {
                      this.saveConclusion(params)
                    } else {
                      queryWaitExemptionCount(this.claimno).then(res => {
                        if (res.status == '1') {
                          if (res.data == 0) {
                            this.saveConclusion(params)
                          } else {
                            queryPolicyExemptionDetail(this.claimno).then(res => {
                              if (res.status == '1') {
                                if (res.data == 0) {
                                  queryExemptionStartDate(this.claimno).then(res => {
                                    if (res.status == '1') {
                                      this.dialogVisible = true
                                      this.exemptionTitle = '触发自动豁免规则，确认后将按照豁免责任进行自动豁免'
                                      this.exemptionTitleFlag = true
                                    } else {
                                      this.$message({
                                        message: res.data.errMsg ? res.data.errMsg : '获取豁免开始日期失败！',
                                        type: 'error'
                                      })
                                    }
                                  })
                                } else {
                                  this.dialogVisible = true
                                  this.exemptionTitleFlag = false
                                  this.exemptionTitle = '存在未豁免的责任信息，是否进行处理？'
                                }
                              } else {
                                this.$message({
                                  message: res.data.errMsg ? res.data.errMsg : '查询已豁免条数失败！',
                                  type: 'error'
                                })
                              }
                            })
                          }
                        } else {
                          this.$message({message: res.data.errMsg ? res.data.errMsg : '查询待豁免条数失败！', type: 'error'})
                        }
                      })
                    }
                  } else {
                    this.$message({message: res.data.errMsg ? res.data.errMsg : '豁免失败！', type: 'error'})
                  }
                })
              } else {
                this.saveConclusion(params)
              }
            }
          } else {
            this.$message({message: '操作失败！', type: 'error'})
          }
        }).finally(() => {
          this.submitLoading = false
        })
      },
      saveConclusion(params) {
        this.submitLoading = true
        processCheck(params).then(res => {
          if (res.status == '1') {
            params.node = res.data.node
            params.personalpool = res.data.personalpool
            saveConclusion(params).then(res => {
              if (res.status === '1') {
                this.$message({message: '操作成功！', type: 'success'})
                this.status = 'show'
                this.acceptFlag = true
                this.disableFlag = true
                this.sopckDisabled = true
              } else {
                if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
                  this.$message({message: res.data.errMsg ? res.data.errMsg : '操作失败！', type: 'error'})
                } else {
                  this.$message({message: res.data.errMsg ? res.data.errMsg : '操作失败！', type: 'warning'})
                }
              }
            }).finally(() => {
              this.submitLoading = false
            })
            // if (res.data.personalpool == true) {
            //   // this.saveconsdialogVisible = true
            // } else {
            //  this.confireSaveConculsion(this.processCheckData)
            // }
          } else {
            this.$message({message: res.data.errMsg, type: 'warning'})
          }
        })
      },
      submitHandle() {
        // if((this.node==='firstTrial'|| this.node==='review')&&(!this.$refs.compenInfo.submitFlag||this.caseLastNodeInfo.conclusion!=='02'||this.resultForm.conclusion==='02')){
        // this.$message({ message: '请先进行赔付计算并保存！', type: 'warning' })
        // } else {
        this.$refs.resultForm.validate((valid) => {
          if (valid) {
            judgeBnf(this.claimno).then(response => {
              if (response.status === '1') {
                if (response.data !== '' && this.resultForm.conclusion !== '02') {
                  this.$message({message: response.data, type: 'warning'})
                } else {
                  const params = {
                    claimno: this.claimno,
                    ...this.resultForm,
                    tasknode: this.mapNode(this.node)
                  }
                  if (this.caseLastNodeInfo.conclusion === '02') {
                    params.isreturn = 'Y'
                    params.status = this.caseLastNodeInfo.status
                    params.reviewer = this.caseLastNodeInfo.creator
                    /* let nextCaseNode = this.dict.label.casestep[this.caseLastNodeInfo.tasknode]*/
                    let nextCaseNode = this.selectDictLabel(this.casestepOptions, this.caseLastNodeInfo.tasknode)
                    params.nexttasknode = this.mapNode(nextCaseNode)
                  }
                  if (this.querys.conclusionType) {
                    this.submitLoading = true
                    params.detailno = this.querys.detailno
                    updateCheck(params).then(res => {
                      if (res.status === '1') {
                        this.$message({message: '操作成功！', type: 'success'})
                        // setTimeout(() =>{
                        //   this.goBack()
                        // },2000)
                      } else {
                        if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
                          this.$message({message: res.data.errMsg, type: 'error'})
                        } else {
                          this.$message({message: res.data.errMsg, type: 'warning'})
                        }
                      }
                    }).finally(() => {
                      this.submitLoading = false
                      this.sopckDisabled = true
                    })
                  } else {
                    if (this.resultForm.conclusion !== '02') {
                      this.saveConclusionCheck(params)
                    } else {
                      this.saveConclusion(params)
                    }
                  }
                }
              }
            })
          } else {
            return false
          }
        })
        // }
      },
      goBack() {
        window.close()
        // this.$router.go(-1)
      },
      registerNoUpdate(data) {
        this.registerNo = data
        this.getRegisterDetailInfo()
      },
      registerSourceUpdate(data) {
        this.registerSource = data
      },
      applySourceUpdate(data) {
        this.fixInfo.applicationsource = data
      },
      insuredNoUpdate(data) {
        this.insuredNo = data
      },
      insuredInfoUpdate(data) {
        this.insuredBaseInfo = data
        if (this.node === 'report') {
          if (this.$refs['reporterCom'].baseForm.relationship === '00') {
            this.$refs['reporterCom'].baseForm.reportName = this.insuredBaseInfo.insuredName
            this.$refs['reporterCom'].baseForm.cardType = this.insuredBaseInfo.idType
            this.$refs['reporterCom'].baseForm.idCard = this.insuredBaseInfo.idNo
            this.$refs['reporterCom'].baseForm.gender = this.insuredBaseInfo.sex
            if (this.insuredBaseInfo.phone !== null && this.insuredBaseInfo.phone !== '') {
              this.$refs['reporterCom'].baseForm.phone = this.insuredBaseInfo.phone
            }
            if (this.insuredBaseInfo.mobiles[0] !== null && this.insuredBaseInfo.mobiles[0] !== '') {
              this.$refs['reporterCom'].baseForm.mobile = this.insuredBaseInfo.mobiles[0]
            }
            if (this.insuredBaseInfo.email !== null && this.insuredBaseInfo.email !== '') {
              this.$refs['reporterCom'].baseForm.email = this.insuredBaseInfo.email
            }
          }
        } else {
          if (this.$refs['applicant'].baseForm.relationship === '00') {
            this.$refs['applicant'].baseForm.reportName = this.insuredBaseInfo.insuredName
            this.$refs['applicant'].baseForm.cardType = this.insuredBaseInfo.idType
            this.$refs['applicant'].baseForm.idCard = this.insuredBaseInfo.idNo
            this.$refs['applicant'].baseForm.gender = this.insuredBaseInfo.sex
            if (this.insuredBaseInfo.phone !== null && this.insuredBaseInfo.phone !== '') {
              this.$refs['applicant'].baseForm.phone = this.insuredBaseInfo.phone
            }
            if (this.insuredBaseInfo.mobiles[0] !== null && this.insuredBaseInfo.mobiles[0] !== '') {
              this.$refs['applicant'].baseForm.mobile = this.insuredBaseInfo.mobiles[0]
            }
            if (this.insuredBaseInfo.email !== null && this.insuredBaseInfo.email !== '') {
              this.$refs['applicant'].baseForm.email = this.insuredBaseInfo.email
            }
            // this.$refs['applicant'].baseForm.phone = this.insuredBaseInfo.phone
            // this.$refs['applicant'].baseForm.mobile = this.insuredBaseInfo.mobiles[0]
            // this.$refs['applicant'].baseForm.email = this.insuredBaseInfo.email
            if (this.$refs['applicant'].tableData.length > 0) {
              const data = this.$refs['applicant'].tableData
              for (let i = 0; i < data.length; i++) {
                if (data[i].relation === '00') {
                  data[i].payee = this.insuredBaseInfo.insuredName
                  data[i].cardType = this.insuredBaseInfo.idType
                  data[i].idCard = this.insuredBaseInfo.idNo
                  data[i].sex = this.insuredBaseInfo.sex
                  if (this.insuredBaseInfo.mobiles[0] !== null && this.insuredBaseInfo.mobiles[0] !== '') {
                    data[i].mobile = this.insuredBaseInfo.mobiles[0]
                  }
                  // data[i].mobile = this.insuredBaseInfo.mobiles[0]
                  data[i].validSign = this.insuredBaseInfo.checked
                  data[i].dateRange = this.insuredBaseInfo.dateRange
                  data[i].nation = this.insuredBaseInfo.nationality
                  data[i].occupation = this.insuredBaseInfo.occupation
                  data[i].address = this.insuredBaseInfo.area
                  data[i].detailAddress = this.insuredBaseInfo.address
                }
              }
            }
          }
        }
      },
      insuredSaveUpdate(data) {
        this.insuredSave = data
      },
      reportSaveUpdate(data) {
        this.reportSave = data
      },
      reportCountsUpdate(data) {
        this.getRelationReports(data)
      },
      applySaveUpdate(data) {
        this.applySave = data
      },
      renovateList(data) {
        this.getMateriaList()
      },
      // 获取报案详情数据
      getRegisterDetailInfo() {
        getRegisterInfo(this.registerNo).then(res => {
          if (res.status === '1') {
            if (res.data.insuredNo !== null && res.data.insuredNo !== '') {
              this.insuredNo = res.data.insuredNo
              this.insuredBaseInfo = res.data.insuredInfo
              this.$refs['insuredCom'].baseForm = res.data.insuredInfo
              this.$refs['insuredCom'].tableData = res.data.policyShowList
              this.$refs['insuredCom'].baseForm.phones = []
              this.getLabel(res.data.insuredNo)
              if (res.data.insuredInfo.checked === true) {
                this.$refs['insuredCom'].tableFormRules.certificate = {
                  required: false,
                  message: '不能为空!',
                  trigger: 'change'
                }
              }
              if (res.data.insuredInfo.mobiles.length > 0) {
                res.data.insuredInfo.mobiles.forEach(item => this.$refs['insuredCom'].baseForm.phones.push({value: item}))
              } else {
                this.$refs['insuredCom'].baseForm.phones = [{value: ''}]
              }
              this.insuredSave = true
            }
            if (res.data.report.relationship != null) {
              this.$refs['reporterCom'].baseForm = res.data.report
              this.reportSave = true
            }
            if (res.data.apply.reason !== null && res.data.apply.reason.length > 0) {
              this.$refs['applyCom'].baseForm = res.data.apply
              this.applySave = true
            }
            if (this.registerStatus === '01') {
              this.$refs['insuredCom'].disableFlag = true
              this.$refs['reporterCom'].disableFlag = true
              this.$refs['applyCom'].disableFlag = true
              this.disableFlag = true
            }
          } else {
            this.$message({message: '查询报案详情数据失败！', type: 'error'})
          }
        })
      },
      // 获取受理详情数据
      getClaimDetailInfo() {
        getClaimInfo(this.claimno).then(res => {
          if (res.status === '1') {
            this.insuredNo = res.data.insuredNo
            this.insuredBaseInfo = res.data.insuredInfo
            this.$refs['insuredCom'].baseForm = res.data.insuredInfo
            this.$refs['insuredCom'].lightList = res.data.pitchList
            this.$refs['insuredCom'].tableData = res.data.policyShowList
            this.$refs['insuredCom'].dealData(this.$refs['insuredCom'].tableData)
            this.$refs['insuredCom'].baseForm.phones = []
            this.getLabel(res.data.insuredNo)
            this.getRelationReports(this.claimno)
            if (res.data.insuredInfo.checked === true) {
              this.$refs['insuredCom'].tableFormRules.dateRange = {required: false, message: '不能为空!', trigger: 'change'}
            }
            if (res.data.insuredInfo.mobiles.length > 0) {
              res.data.insuredInfo.mobiles.forEach(item => this.$refs['insuredCom'].baseForm.phones.push({value: item}))
            } else {
              this.$refs['insuredCom'].baseForm.phones = [{value: ''}]
            }
            this.insuredSave = true
            if (res.data.report.relationship != null) {
              this.$refs['applicant'].baseForm = res.data.report
              this.$refs['applicant'].tableData = res.data.payeeInfoList
              this.applicantSave = true
            }
            if (res.data.apply.reason !== null && res.data.apply.reason.length > 0) {
              this.$refs['applyCom'].baseForm = res.data.apply
              this.applySave = true
            }
            if (this.status === 'show') {
              this.$refs['insuredCom'].disableFlag = true
              this.$refs['applicant'].disableFlag = true
              this.$refs['applyCom'].disableFlag = true
              this.disableFlag = true
              this.acceptFlag = true
            }
          } else {
            this.$message({message: '查询赔案详情数据失败！', type: 'error'})
          }
        })
      },
      getRelationReports(value) {
        getCounts(value).then(res => {
          if (res.status === '1') {
            this.relationCounts = res.data
          }
        })
      },
      // 报案确认
      reportConfirm() {
        this.reportConfirmLoading = true
        if (this.insuredSave && this.reportSave && this.applySave) {
          registerConfirm(this.registerNo).then(res => {
            if (res.status === '1') {
              this.$message({message: '报案确认成功！', type: 'success'})
              this.disableFlag = true
              this.acceptFlag = true
              this.$refs['reporterCom'].disableFlag = true
              this.reportConfirmLoading = false
              // this.$router.push('report')
            } else {
              this.$message({message: '报案确认失败！', type: 'error'})
              this.reportConfirmLoading = false
            }
          })
        } else {
          if (!this.insuredSave) {
            this.$message({message: '请保存被保人信息！', type: 'warning'})
            this.reportConfirmLoading = false
            return
          }
          if (!this.reportSave) {
            this.$message({message: '请保存报案人信息！', type: 'warning'})
            this.reportConfirmLoading = false
            return
          }
          if (!this.applySave) {
            this.$message({message: '请保存报案信息！', type: 'warning'})
            this.reportConfirmLoading = false
            return
          }
        }
      },
      // 受理确认
      acceptConfirm() {
        this.acceptLoading = true
        if (this.insuredSave && this.applicantSave && this.applySave) {
          const requestData = {
            insuredNo: this.insuredNo,
            claimNo: this.claimno
          }
          judgeBnf(this.claimno).then(response => {
            if (response.status === '1') {
              if (response.data !== '') {
                this.$message({message: response.data, type: 'warning'})
                this.acceptLoading = false
              } else {
                judgeClaim(requestData).then(res => {
                  if (res.status === '1') {
                    if (res.data.flag) {
                      this.$confirm(res.data.tips, '温馨提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                      }).then(() => {
                        judgeRegister(this.claimno).then(res => {
                          if (res.status === '1') {
                            if (res.data) {
                              this.$confirm('存在尚未关联的报案信息，是否继续操作', '温馨提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                              }).then(() => {
                                this.acceptDeal()
                              }).catch(error => {
                                this.acceptLoading = false
                              })
                            } else {
                              this.acceptDeal()
                            }
                          }
                        })
                      }).catch(error => {
                        this.acceptLoading = false
                      })
                    } else {
                      judgeRegister(this.claimno).then(res => {
                        if (res.status === '1') {
                          if (res.data) {
                            this.$confirm('存在尚未关联的报案信息，是否继续操作', '温馨提示', {
                              confirmButtonText: '确定',
                              cancelButtonText: '取消',
                              type: 'warning'
                            }).then(() => {
                              this.acceptDeal()
                            }).catch(error => {
                              this.acceptLoading = falsethis.acceptLoading = false
                            })
                          } else {
                            this.acceptDeal()
                          }
                        }
                      })
                    }
                  } else {
                    this.$message({message: '校验异常！', type: 'error'})
                    this.acceptLoading = false
                  }
                })
              }
            }
          })
        } else {
          if (!this.insuredSave) {
            this.$message({message: '请保存被保人信息！', type: 'warning'})
            this.acceptLoading = false
            return
          }
          if (!this.applicantSave) {
            this.$message({message: '请保存申请人信息！', type: 'warning'})
            this.acceptLoading = false
            return
          }
          if (!this.applySave) {
            this.$message({message: '请保存申请信息！', type: 'warning'})
            this.acceptLoading = false
            return
          }
        }
      },
      // 受理完成
      acceptDeal() {
        this.acceptLoading = true
        acceptConfirm(this.claimno).then(res => {
          if (res.status === '1') {
            this.$message({message: '受理确认成功！', type: 'success'})
            this.getpdfMessage()
            this.acceptFlag = true
            this.acceptLoading = false
            // this.$router.push('accept')
          } else {
            this.$message({message: '受理确认失败！', type: 'error'})
            this.acceptLoading = false
          }
        })
      },
      // 录入完成
      inputConfirm() {
        const requestData = {
          claimno: this.claimno,
          conclusion: this.resultForm.result,
          explanation: this.resultForm.desc,
          tasknode: 'claim_input'
        }

        this.submitLoading = true
        saveConclusion(requestData).then(res => {
          if (res.status === '1') {
            this.$message({message: '录入确认成功！', type: 'success'})
            this.$router.push('input')
          } else {
            this.$message({message: '录入确认失败！', type: 'error'})
          }
        }).finally(() => {
          this.submitLoading = false
        })
      },
      claimNoUpdate(data) {
        this.claimno = data
        this.getClaimDetailInfo()
        this.getbasicInfo()
      },
      applicantSaveUpdate(data) {
        this.applicantSave = data
        // this.getClaimDetailInfo()
      },
      batchPrinting() {
        const printIframe = document.getElementById('print-iframe')
        const print = printIframe.contentWindow
        const doc = print.document
        doc.write(this.$refs.printall.innerHTML)
        doc.close()
        print.focus()
        print.print()
      },
      getBills() {
        getBillInfos(this.claimno).then(res => {
          if (res.status === '1') {
            this.billsInfo = res.data
          } else {
            this.$message({message: '账单信息查询失败！', type: 'error'})
          }
        })
      },
      imageUpdate(data) {
        if ((this.claimno !== null && this.claimno !== "" && this.claimno !== undefined) || (this.registerNo !== null && this.registerNo !== "" && this.registerNo !== undefined)) {
          this.imageInfo.originallist.list = []
          this.imageInfo.originallist.previewList = []
          this.imageInfo.supplementlist.list = []
          this.imageInfo.supplementlist.previewList = []
          this.imageInfo.surveylist.list = []
          this.imageInfo.surveylist.previewList = []
          this.imageInfo.supplementpartlist.list = []
          this.imageInfo.supplementpartlist.previewList = []
          this.getImageInfo('1', 1, null, true)
          if (this.node !== 'report') {
            this.getImageInfo('2', 1, null, true)
            this.getImageInfo('3', 1, null, true)
            this.getImageInfo('4', 1, null, true)
          }
          this.getFileCount()
          if (this.node !== 'report') {
            this.updateMaterialList()
          }
          this.$refs.imagecom.initImages()
        }
      },
      updateMaterialList() {
        if (this.node === 'accept') return
        updateMaterial(this.claimno).then(res => {
          if (res.status === '1') {
            this.getMateriaList()
          }
        })
      },
      imageDeal() {
        if ((this.claimno !== null && this.claimno !== "" && this.claimno !== undefined) || (this.registerNo !== null && this.registerNo !== "" && this.registerNo !== undefined)) {
          let businessNo = ''
          if (this.node === 'report') {
            businessNo = this.registerNo
          } else {
            businessNo = this.claimno
          }
          imageDeal(businessNo).then(res => {
            if (res.status === '1') {
            }
          })
        }
      },
      getLabel(data) {
        getCustomerLabel(data).then(res => {
          if (res.status === '1') {
            this.$refs['insuredCom'].labelList = res.data
            this.$refs['insuredCom'].showFlag = true
          }
        })
      },
      isAppealCorrection(claimno) {
        isAppealCorrection(claimno).then(res => {
          if (res.status === '1') {
            // this.isAppeal = res.data
          }
        })
      },
      // 最小化
      toMinimize() {
        let minimize = this.$refs.minimize.$el
        let wrapper = document.getElementsByClassName('v-modal')[0]
        setTimeout(() => {
          minimize.style.transform = 'scale(0.05, 0.05)'
          minimize.style.transformOrigin = 'left bottom'
          minimize.style.transition = 'transform 0.5s'
          // minimize.style.zIndex = '999'
          wrapper.style.transform = 'scale(0.05, 0.05)'
          wrapper.style.transformOrigin = 'left bottom'
          wrapper.style.transition = 'transform 0.5s'
          wrapper.style.zIndex = '3000'
          wrapper.onclick = this.toBigmize
        }, 0)
      },
      // 最大化
      toBigmize() {
        let minimize = this.$refs.minimize.$el
        let wrapper = document.getElementsByClassName('v-modal')[0]
        setTimeout(() => {
          // minimize.style.zIndex = '2001'
          minimize.style.transform = 'scale(1, 1)'
          minimize.style.transformOrigin = 'left bottom'
          minimize.style.transition = 'transform 0.5s'
          wrapper.style.transform = 'scale(1, 1)'
          wrapper.style.transformOrigin = 'left bottom'
          wrapper.style.transition = 'transform 0.5s'
          wrapper.style.zIndex = '2000'
          wrapper.onclick = null
        }, 0)
      }
    }
  }
</script>
<style scoped>
  .newfirsttaial /deep/ .el-tabs__item .is-top {
    border-right: 1px solid black;
  }

  .active {
    color: #67c23a;
    background: #fff;
  }

  .newfirsttaial {
    /* position: relative; */
    margin: 0 20px 20px 20px;
  }

  /*  .nav-flex-box {
      display: flex;
      width: 96%;
      position: fixed;
      height: 40px;
      line-height: 40px;
      color: #409eff;
      justify-content: center;
      background: #f8f8f8;
      z-index: 999;
      margin-top: 93px;
    }*/
  .nav-flex-box {
    display: flex;
    position: fixed;
    height: 40px;
    line-height: 40px;
    color: #409eff;
    justify-content: center;
    background: #f8f8f8;
    margin-top: 93px;
    right: 30px;
    z-index: 999;
    width: calc(100% - 260px) !important;
    transition: width 0.28s !important;
  }

  .hideSidebar .nav-flex-box {
    width: calc(100% - 114px) !important;
    transition: width 0.25s !important;
  }

  .flex-item-btn {
    flex: 1;
    font-size: 12px;
    padding: 0 3px;
    border: 1px solid #eee;
    border-right: none;
    margin: 0 auto;
    cursor: pointer;
  }

  .flex-item-btn:last-child {
    border-right: 1px solid #eee;
  }

  .flex-item-btn > span {
    display: inline-block;
    width: 100%;
    text-align: center;
  }

  /* .card-top /deep/ .el-tabs__header {
     position: fixed;
     z-index: 999;
     width: 96%;
   }
   .el-card /deep/ .el-card__body{
     padding:10px;
   }
   .top-card-heard{
     position: fixed;
     z-index: 999;
     width: 96%;
     margin-top:40px;
     margin-left: 10px;
   }*/
  .card-top /deep/ .el-tabs__header {
    position: fixed;
    right: 30px;
    z-index: 9;
    width: calc(100% - 260px) !important;
    transition: width 0.28s !important;
  }

  .hideSidebar .card-top /deep/ .el-tabs__header {
    width: calc(100% - 114px) !important;
    transition: width 0.25s !important;
  }

  .el-card /deep/ .el-card__body {
    padding: 10px;
  }

  .top-card-heard {
    position: fixed;
    right: 30px;
    z-index: 9;
    margin-top: 40px;
    margin-left: 10px;
    width: calc(100% - 260px) !important;
    transition: width 0.28s !important;
  }

  .hideSidebar .top-card-heard {
    width: calc(100% - 114px) !important;
    transition: width 0.25s !important;
  }

  .startjump {
    padding-top: 142px;
  }

  .startjump_next {
    margin-top: 102px;
  }

  .imageView {
    position: fixed;
    bottom: 50px;
    right: 40px;
    width: 30px;
    height: 30px;
    z-index: 99999;
  }

  /deep/ .el-tabs__nav-scroll {
    background: #f6f6f6;
  }
</style>
<style>
  .newfirsttaial .el-tabs__item is-top {
    width: 25% !important;
  }

  .newfirsttaial .el-tabs__content {
    /* min-height: 1000px !important; */
    width: 100%;
    padding: 0 !important;
  }
</style>
