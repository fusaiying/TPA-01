<template>
  <div class="app-container" style="margin-bottom: 20px;">
    <el-backtop :bottom="60"/>
    <div v-if="navFlag === true"
         :class="{'opened-adapt': sidebar.opened, 'close-adapt': !sidebar.opened, 'top-instance': this.$store.state.settings.tagsView }"
         class="nav-bar">
      <div class="nav-flex-box ">
        <div v-for="(item, i) in btnArr" :key="i" :name="item.name"
             class="flex-item-btn"
             :class="{'active':activeNav===item.name}"
             @click="goAnchor(item.name)">
          <span>{{ item.label }}</span>
        </div>
      </div>
      <el-card class="top-card">
        <span style="margin-right: 10px">
          <span style="color:#409EFF; font-size: 12px;">报案号：{{ fixInfo.rptNo }}　</span>
        </span>
        <span style="margin-right: 10px">
          <span style="color:#409EFF;font-size:12px">批次号：{{ fixInfo.batchNo }}　</span>
        </span>
        <!--<span style="margin-right: 10px">
          <span style="color:#409EFF;font-size:12px">归档号：{{ fixInfo.filingNo }}　</span>
        </span>-->
        <span style="margin-right: 10px">
          <span
            style="color:#409eff;font-size:12px">申请来源：{{ selectDictLabel(delivery_sourceOption, fixInfo.source)  }}　</span>
        </span>
        <el-form style="float: right; padding: 3px 0">
          <span>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openAppealInfo">申述信息</el-button>
            <el-button type="primary" size="mini" @click="">影像查看</el-button>
            <el-button type="primary" v-if="(querys.node==='calculateReview' || querys.node==='sport') && showHistoryClaim" size="mini"
                       @click="openHistoryClaim">历史理赔</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openGuaranteee">保障查看</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openHistorySurvey">调查({{historySurCount}})</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openHistoryDiscussion">协谈({{historyDisCount}})</el-button>
            <el-button type="primary" v-if="querys.node==='accept'" size="mini"
                       @click="selectHistoricalProblem">问题件({{historicalProblemDataTotal}})</el-button>
            <el-button type="primary" v-if="querys.node==='accept' || querys.node==='calculateReview'"
                       :disabled="querys.status==='show'" size="mini" @click="openRemoveDialog">撤件</el-button>
            <el-button type="primary" v-if="querys.node==='accept'" :disabled="querys.status==='show'" size="mini"
                       @click="acceptOver">受理完毕</el-button>
            <el-button type="primary" v-if="querys.node==='input'" :disabled="querys.status==='show'" size="mini"
                       @click="changeBillStatus">录入完毕</el-button>
            <el-button size="mini" @click="goBack">返回</el-button>
          </span>
        </el-form>
      </el-card>
    </div>
    <div v-if="navFlag === false"
         :class="{'opened-adapt1': sidebar.opened, 'close-adapt1': !sidebar.opened, 'top-instance1': this.$store.state.settings.tagsView }"
         class="nav-bar1">
      <div class="nav-flex-box ">
        <div v-for="(item, i) in btnArr" :key="i" :name="item.name"
             class="flex-item-btn"
             :class="{'active':activeNav===item.name}"
             @click="goAnchor(item.name)">
          <span>{{ item.label }}</span>
        </div>
      </div>
      <el-card class="top-card">
        <span style="margin-right: 10px">
          <span style="color:#409EFF; font-size: 12px;">报案号：{{ fixInfo.rptNo }}　</span>
        </span>
        <span style="margin-right: 10px">
          <span style="color:#409EFF;font-size:12px">批次号：{{ fixInfo.batchNo }}　</span>
        </span>
        <!--<span style="margin-right: 10px">
          <span style="color:#409EFF;font-size:12px">归档号：{{ fixInfo.filingNo }}　</span>
        </span>-->
        <span style="margin-right: 10px">
          <span
            style="color:#409eff;font-size:12px">申请来源：{{ selectDictLabel(delivery_sourceOption, fixInfo.source)  }}　</span>
        </span>
        <el-form style="float: right; padding: 3px 0">
          <span>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openAppealInfo">申述信息</el-button>
            <el-button type="primary" size="mini" @click="">影像查看</el-button>
            <el-button type="primary" v-if="(querys.node==='calculateReview' || querys.node==='sport') && showHistoryClaim" size="mini"
                       @click="openHistoryClaim">历史理赔</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openGuaranteee">保障查看</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openHistorySurvey">调查({{historySurCount}})</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview' || querys.node==='sport'" size="mini"
                       @click="openHistoryDiscussion">协谈({{historyDisCount}})</el-button>
            <el-button type="primary" v-if="querys.node==='accept'" size="mini"
                       @click="selectHistoricalProblem">问题件({{historicalProblemDataTotal}})</el-button>
            <el-button type="primary" v-if="querys.node==='accept' || querys.node==='calculateReview'"
                       :disabled="querys.status==='show'" size="mini" @click="openRemoveDialog">撤件</el-button>
            <el-button type="primary" v-if="querys.node==='accept'" :disabled="querys.status==='show'" size="mini"
                       @click="acceptOver">受理完毕</el-button>
            <el-button type="primary" v-if="querys.node==='input'" :disabled="querys.status==='show'" size="mini"
                       @click="changeBillStatus">录入完毕</el-button>
            <el-button size="mini" @click="goBack">返回</el-button>
          </span>
        </el-form>
      </el-card>
    </div>
    <div id="start_jump" class="startjump" :class="{'navFalgClass': navFlag === false}">
      <!-- 批次信息 -->
      <div id="#anchor-21" class="batchInfo_class" style="margin-top: 10px;">
        <batchInfo ref="batchInfo" :baseInfo="batchInfo"/>
      </div>
      <!-- 被保人信息 -->
      <div id="#anchor-2" class="batchInfo_class" style="margin-top: 10px;">
        <insured-com :sonInsuredData="sonInsuredData" :node="querys.node" :status="querys.status"
                     ref="insuredForm" @getInsuredData="getInsuredData" @getPropData="getPropData"
                     :batchInfo="batchInfo"
                     :fixInfo="fixInfo" @emitSaveFlag="changeSaveFlag" @getPayeeDatas="getPayeeDatas"/>
      </div>
      <!-- 申请人信息 -->
      <div id="#anchor-12" class="batchInfo_class" style="margin-top: 10px;">
        <applicant-com :sonRegisterData="sonRegisterData" :node="querys.node" :status="querys.status"
                       ref="applicantInfoForm" :applicantData="applicantData" :fixInfo="fixInfo"
                       :caseInsuredData="caseInsuredData"
                       @getApplicantData="getApplicantData" :baseInfo="batchInfo" :isSave="isSave"/>
      </div>
      <!-- 领款人信息 -->
      <div id="#anchor-11" class="batchInfo_class" style="margin-top: 10px;">
        <payeeInfo :sonPayeeInfoData="sonPayeeInfoData" ref="payeeInfoForm" :baseInfo="batchInfo" :fixInfo="fixInfo"
                   @getApplicantData="getApplicantData" @getInsuredData="getInsuredData"
                   :insuredFormData="insuredFormData"
                   @refresh-item="refreshList" :applicantData="applicantData" :node="querys.node"
                   :status="querys.status"/>
      </div>
      <!-- 受理信息 -->
      <div id="#anchor-13" class="batchInfo_class" style="margin-top: 10px;">
        <acceptInfo :sonAcceptInfoData="sonAcceptInfoData" ref="acceptInfoForm" :claimtype="querys.claimType"
                    :baseInfo="batchInfo" :isSave="isSave" @refresh-item="refreshList"
                    :node="querys.node" :status="querys.status" :fixInfo="fixInfo"/>
      </div>
      <!-- 账单明细 -->
      <div v-if="querys.node==='input' || querys.node==='calculateReview' || querys.node==='sport'" id="#anchor-15"
           class="batchInfo_class"
           style="margin-top: 10px;">
        <billing-details ref="billingInfoForm" :batchData="batchInfo" :acceptData="sonAcceptInfoData"
                         :sonBillingInfoData="sonBillingInfoData" :claimtype="querys.claimType"
                         :fixInfo="fixInfo"
                         :node="querys.node" :status="querys.status" @refresh-item="refreshList"/>
      </div>
      <!-- 案件理算 -->
      <div v-if="querys.node==='calculateReview' || querys.node==='sport'" id="#anchor-18" class="batchInfo_class"
           style="margin-top: 10px;">
        <case-calculate ref="caseCalculate" :sonCalculateData="sonCalculateData" :fixInfo="fixInfo"
                        @refresh-item="refreshList" :status="querys.status"
                        :node="querys.node"/>
      </div>
      <!--赔案备注-->
      <div id="#anchor-16" class="batchInfo_class" style="margin-top: 10px;">
        <reportNotes :reportDatas="reportData" :fixInfo="fixInfo" :node="querys.node" :status="querys.status"
                     :batchInfo="batchInfo"/>
      </div>
      <!--问题件-->
      <div v-if="querys.node==='accept'" id="#anchor-14" class="batchInfo_class" style="margin-top: 10px;">
        <problemCase :sonProblemData="sonProblemData" :fixInfo="fixInfo" :node="querys.node" :status="querys.status"/>
      </div>
      <!--赔付结论-->
      <div v-if="(querys.node==='calculateReview' || querys.node==='sport') && fixInfo.isAppeal==='02'"
           class="batchInfo_class" style="margin-top: 10px;">
        <pay-conclusion :fixInfo="fixInfo"/>
      </div>
      <!--赔付结论-->
      <div v-if="querys.node==='calculateReview' || querys.node==='sport'" id="#anchor-17" class="batchInfo_class"
           style="margin-top: 10px;">
        <discussion ref="discussion" :status="querys.status" :insuredData="insuredData"
                    :policySelectData="policySelectData" :fixInfo="fixInfo" :node="querys.node"/>
      </div>
    </div>
    <!-- 历史问题件模态框 -->
    <history-problem-case :fixInfo="fixInfo" :historicalProblemData="historicalProblemData"
                          :value="historicalProblemDialog" @closeHistoricalProblem="closeHistoricalProblem"/>
    <!-- 撤件模态框 -->
    <remove-case :value="removeDialog" :node="querys.node" @closeRemoveDialog="closeRemoveDialog" :fixInfo="fixInfo"/>
    <!-- 申述信息 -->
    <appeal-info :value="appealDialog" :fixInfo="fixInfo" @closeAppealDialog="closeAppealDialog"/>
    <!-- 历史理赔 -->
    <history-claim :value="historyClaimDialog" :insuredNo="insuredNo" :fixInfo="fixInfo"
                   @closeHistoryClaimDialog="closeHistoryClaimDialog" :querys="querys" />

    <!-- 保障查看 -->
    <guarantee :value="guaranteeDialog" :insuredNo="insuredNo" :fixInfo="fixInfo"
                   @closeGuaranteeDialog="closeGuaranteeDialog"/>
    <!-- 历史协谈 -->
    <history-discussion :preHistoryData="preHistoryData" :value="historyDiscussionDialog" :fixInfo="fixInfo"
                        @closeHistoryDiscussionDialog="closeHistoryDiscussionDialog"/>
    <!-- 历史调查 -->
    <history-survey :preSurveyHistoryData="preSurveyHistoryData" :value="historySurveyDialog" :fixInfo="fixInfo"
                    @closeHistorySurveyDialog="closeHistorySurveyDialog"/>
  </div>
</template>
<script>
  import batchInfo from '../common/components/batchInfo'//批次信息
  import insuredCom from '../common/components/insured'//被保人信息
  import applicantCom from '../common/components/applicant'//申请人信息
  import payeeInfo from '../common/components/payeeInfo'//领款人信息
  import acceptInfo from '../common/components/acceptInfo'//受理信息
  import reportNotes from '../common/components/reportNotes'//赔案备注
  import problemCase from '../common/components/problemCase'//问题件
  import billingDetails from '../common/components/billingDetails'//账单明细
  import caseCalculate from '../common/components/caseCalculate'//案件理算
  import payConclusion from '../common/components/payConclusion'//申诉案件陪付结论
  import discussion from '../common/components/discussion'//陪付结论
  import mixinAnchor from "../common/mixins/mixinAnchor";
  import {mapGetters} from 'vuex'

  import historyProblemCase from './modul/problemCase' //历史问题件
  import removeCase from './modul/removeCase' //撤件
  import appealInfo from './modul/appealInfo' //申述信息
  import historyClaim from './modul/historyClaim' //历史理赔
  import historyDiscussion from './modul/historyDiscussion' //历史协谈
  import historySurvey from './modul/historySurvey' //历史调查

  import guarantee from './modul/guarantee' //保障查看
  import {
    getCase,
    getBatch,
    selectHistoricalProblem,
    getProblemRptNo,
    editCaseAndRecordInfoSuspend,
    getRegister,
    getInsured,
    listRemarkRptNo,
    getHospital,
    getAccept,
    getRemarkRptNo,
    getBillList,
    changeBillStatus,
    infoList,
    insurancePolicyList,
    checkThePayment,
    setRptNo,
    adjustRemarkList, addInsuredAndPolicy
  } from '@/api/claim/handleCom'


  import {historyDisInfo, historySurInfo} from '@/api/negotiation/api'
  import elementIcons from "../../../components/icons/element-icons"; // 历史协谈数据


  let dictss = [{dictType: 'delivery_source'},]
  export default {
    components: {
      batchInfo,
      insuredCom,
      applicantCom,
      payeeInfo,
      acceptInfo,
      reportNotes,
      problemCase,
      historyProblemCase,
      removeCase,
      billingDetails,
      payConclusion,
      caseCalculate,
      historyDiscussion,
      historyClaim,
      historySurvey,
      appealInfo,
      discussion,
      guarantee
    },
    mixins: [mixinAnchor],
    computed: {
      disabled() {
        return this.status === 'show'
      },
      ...mapGetters([
        'sidebar'
      ])
    },
    watch:{
      $route (to, from) {
        this.$router.go(0)
      }
    },
    data() {
      return {
        historySurCount: 0,// 历史调查个数
        historyDisCount: 0,// 历史协谈个数
        caseInsuredData: {},
        sonInsuredData: {
          claimCaseInsured: '',
          policyInfominData: []
        },
        policySelectData: [],
        sonRegisterData: {},
        sonPayeeInfoData: [],
        sonBillingInfoData: [],
        sonAcceptInfoData: {},
        sonProblemData: [],
        sonCalculateData: [],
        historicalProblemData: [],
        historicalProblemDataTotal: '0',
        problemStatus: 0,
        historicalProblemDialog: false,
        removeDialog: false,
        appealDialog: false,
        historyClaimDialog: false,
        guaranteeDialog: false,
        historyDiscussionDialog: false,
        historySurveyDialog: false,
        isSave: false,
        isNavBar: true,
        btnArr: [],
        fixInfo: {
          batchNo: undefined,
          filingNo: undefined,
          rptNo: undefined,
          source: undefined,
        },
        insuredNo: undefined,
        insuredData: undefined,
        batchInfo: {},//批次信息
        querys: {},
        showHistoryClaim: true,
        navFlag: true,
        // 受理锚点
        acceptArr: [
          {label: '批次信息', name: '#anchor-21'},
          {label: '被保人信息', name: '#anchor-2'},
          {label: '申请人信息', name: '#anchor-12'},
          {label: '领款人信息', name: '#anchor-11'},
          {label: '受理信息', name: '#anchor-13'},
          {label: '赔案备注', name: '#anchor-16'},
          {label: '问题件', name: '#anchor-14'},
        ],
        // 录入锚点
        inputArr: [
          {label: '批次信息', name: '#anchor-21'},
          {label: '被保人信息', name: '#anchor-2'},
          {label: '申请人信息', name: '#anchor-12'},
          {label: '领款人信息', name: '#anchor-11'},
          {label: '受理信息', name: '#anchor-13'},
          {label: '账单明细', name: '#anchor-15'},
          {label: '赔案备注', name: '#anchor-16'},
          //{label: '问题件', name: '#anchor-14'},
        ],
        // 理算锚点
        calculateArr: [
          {label: '批次信息', name: '#anchor-21'},
          {label: '被保人信息', name: '#anchor-2'},
          {label: '申请人信息', name: '#anchor-12'},
          {label: '领款人信息', name: '#anchor-11'},
          {label: '受理信息', name: '#anchor-13'},
          {label: '账单明细', name: '#anchor-15'},
          {label: '案件理算', name: '#anchor-18'},
          {label: '赔案备注', name: '#anchor-16'},
          {label: '赔付结论', name: '#anchor-17'},
          //{label: '问题件', name: '#anchor-14'},
        ],
        reportData: {},
        dictList: [],
        delivery_sourceOption: [],
        applicantData: {},
        insuredFormData: {},
        preHistoryData: [], // 协谈
        preSurveyHistoryData: [], // 调查
      }

    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.delivery_sourceOption = this.dictList.find(item => {
        return item.dictType === 'delivery_source'
      }).dictDate

      if (this.$route.query) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        if (this.querys.flag=='01'){
          this.showHistoryClaim=false
          this.navFlag=false
        }
        getCase(this.querys).then(res => {
          if (res != null && res.code === 200) {
            this.fixInfo = res.data
          }
        }).catch(res => {
        })
        await getBatch(this.querys.batchNo).then(res => {
          if (res != null && res.code === 200) {
            this.batchInfo = res.data
            this.querys.claimType = res.data.claimtype
          }
        }).catch(res => {
        })
        let item = {
          rptNo: this.querys.rptNo
        }
        selectHistoricalProblem(item).then(res => {
          if (res != null && res.code === 200) {
            this.historicalProblemData = res.data.claimCaseProblems
            if (res.data.problemStatus === 'Y') {
              this.historicalProblemDataTotal = '?'
            } else {
              if (res.data.total != null) {
                this.historicalProblemDataTotal = res.data.total
              }
            }
          }
        })
        getInsured(this.querys.rptNo).then(res => {

          if (res != null && res.code === 200) {
            this.insuredFormData = {
              caseInsuredData: undefined,
              policyInfoData: undefined,
            }
            if (res.data.claimCaseInsured != null && res.data.claimCaseInsured !== '') {
              this.sonInsuredData.claimCaseInsured = res.data.claimCaseInsured
              this.insuredNo = this.sonInsuredData.claimCaseInsured.insuredNo;
              this.insuredData = res.data.claimCaseInsured
              this.insuredFormData.caseInsuredData = res.data.claimCaseInsured
              this.isSave = true
            }
            if (res.data.policyInfominData != null && res.data.policyInfominData.length > 0) {
              this.sonInsuredData.policyInfominData = res.data.policyInfominData;
              this.policySelectData = res.data.policyInfominData;
              this.insuredFormData.policyInfoData = res.data.policyInfominData;
            }
          }
        })
        getRegister(this.querys.rptNo).then(res => {
          if (res != null && res.code === 200) {
            this.sonRegisterData = res.data
          }
        })
        getAccept(this.querys.rptNo).then(res => {
          if (res != null && res.code === 200) {
            this.sonAcceptInfoData = res.data
          }
        })
        getProblemRptNo(this.querys.rptNo).then(res => {
          if (res != null && res.code === 200) {
            this.sonProblemData = res.data
          }
        })

        if (this.querys.claimType === '01') {
          let data = {
            rptNo: this.querys.rptNo
          }
          getHospital(data).then(res => {
            if (res != null && res.code === 200) {
              res.data.forEach(item => {
                let option = {
                  payMode: '1',
                  payeeName: item.accountName,
                  accAttribute: item.accAttribute,
                  payeeBank: item.bankName,
                  accNo: item.accountNo,
                }
                this.sonPayeeInfoData.push(option)
              })
            }
          })
        } else {
          listRemarkRptNo(this.querys.rptNo).then(res => {
            if (res != null && res.code === 200) {
              this.sonPayeeInfoData = res.data
            }
          })
        }
        let data = {
          rptNo: this.querys.rptNo
        }
        if (this.querys.node === 'calculateReview') {
          adjustRemarkList(data).then(res => {
            if (res != null && res.code === 200) {
              this.reportData = res.data
            }
          })
        } else {
          getRemarkRptNo(this.querys.rptNo).then(res => {
            if (res != null && res.code === 200) {
              this.reportData = res.data
            }
          })
        }
        getBillList(data).then(res => {
          if (res != null && res.code === 200) {
            this.sonBillingInfoData = res.rows
          }
        })
        infoList(data).then(res => {
          if (res != null && res.code === 200) {
            this.sonCalculateData = res.rows
          }
        })
      }

      if (this.querys.node === 'accept') {
        this.btnArr = this.acceptArr
      } else if (this.querys.node === 'input') {
        this.btnArr = this.inputArr
      } else if (this.querys.node === 'calculateReview' || this.querys.node === 'sport') {
        this.btnArr = this.calculateArr
      }
      this.getHistoryDisInfo();
      this.getHistorySurInfo();
    },

    methods: {
      // 历史协谈数据
      getHistoryDisInfo() {
        if (this.querys.rptNo == '') {
          return;
        }
        historyDisInfo(this.querys.rptNo).then(res => {
          if (res.code == '200') {
            this.historyDisCount = res.total;
            this.preHistoryData = res.rows;
          }
        });
      },
      // 历史调查数据
      getHistorySurInfo() {
        if (this.querys.rptNo == '') {
          return;
        }
        let param = {};
        param.rptNo = this.querys.rptNo;
        historySurInfo(param).then(res => {
          if (res.code == '200' && res.rows) {
            this.historySurCount = res.total;
            this.preSurveyHistoryData = res.rows;
          }
        });
      },
      //获取领款人信息数据
      getPayeeInfoData() {
        return this.$refs.payeeInfoForm.tableData
      },
      //录入完毕
      changeBillStatus() {
        let isBillInfoSave = this.$refs.billingInfoForm.isBillInfoSave
        if (isBillInfoSave) {
          let data = {
            rptNo: this.querys.rptNo
          }
          changeBillStatus(data).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '提交成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.$store.dispatch("tagsView/delView", this.$route);
              this.$router.go(-1)
            }
          }).catch(res => {
            this.$message({
              message: '提交失败!',
              type: 'error',
              center: true,
              showClose: true
            })
          })
        } else {
          return this.$message.warning(
            "请保存账单明细信息！"
          )
        }
      },
      refreshList(item) {
        if (item === 'payeeInfo') {
          if (this.querys.claimType === '01') {
            let data = {
              rptNo: this.querys.rptNo
            }
            getHospital(data).then(res => {
              if (res != null && res.code === 200) {
                this.sonPayeeInfoData = res.data
              }
            })
          } else {
            listRemarkRptNo(this.querys.rptNo).then(res => {
              if (res != null && res.code === 200) {
                this.sonPayeeInfoData = res.data
              }
            })
          }
        } else if (item === 'bill') {
          let data = {
            rptNo: this.querys.rptNo
          }
          getBillList(data).then(res => {
            if (res != null && res.code === 200) {
              this.sonBillingInfoData = res.rows
            }
          })
        } else if (item === 'calculate') {
          this.$refs.caseCalculate.getDataCase()
        } else if (item === 'discussion') {
          this.$refs.discussion.getCalInfo()
        } else if (item === 'discussions') {
          this.$refs.discussion.getAcceptInfo()
        }
      },
      changeSaveFlag() {
        this.isSave = true
      },
      getApplicantData() {
        this.applicantData = this.$refs.insuredForm.baseForm
      },
      getApplicantDatas() {
        let applicantData = this.$refs.insuredForm.baseForm
        return applicantData
      },
      acceptOver() {
        //this.$refs.headerChild.属性insuredForm applicantCom acceptInfo

        //被保人信息是否保存，否则阻断提示：“请保存被保人信息。”，同时字段框红高亮显示；
        //申请人信息是否保存，否则阻断提示：“请保存申请人信息。”，同时字段框红高亮显示；
        //申请信息是否保存，否则阻断提示：“请保存申请信息”，同时字段框红高亮显示；
        //若选择的该被保人的保单不存在TPA保单也不存在健康险保单时，阻断提示：“该被保人不存在保单信息，请撤件”；
        let isInsuredSave = this.$refs.insuredForm.isInsuredSave
        let hasInsuredId = this.$refs.insuredForm.hasInsuredId
        let isApplicantSave = this.$refs.applicantInfoForm.isApplicantSave
        let hasApplicantId = this.$refs.applicantInfoForm.hasApplicantId
        let isAcceptInfoSave = this.$refs.acceptInfoForm.isAcceptInfoSave
        let hasAcceptId = this.$refs.acceptInfoForm.hasAcceptId
        let flag = this.$refs.insuredForm.validataForm()
        //理赔类型事后 false  直结true
        let saveflag = true
        if (this.batchInfo.claimtype === '02') {
          saveflag = false
        } else {
          saveflag = true
        }

        //
        if (saveflag) {
          if ((isInsuredSave || hasInsuredId) && (isApplicantSave || hasApplicantId) && (isAcceptInfoSave || hasAcceptId) && this.$refs.payeeInfoForm.tableData.length > 0) {
            if (this.$refs.insuredForm.tableData != null && this.$refs.insuredForm.tableData.length > 0) {
              let data = {
                claimCase: {rptNo: this.querys.rptNo},
                insuredNo: this.$refs.insuredForm.baseForm.insuredNo,//被保人客户号
                name: this.$refs.insuredForm.baseForm.name//被保人姓名
              }
              editCaseAndRecordInfoSuspend(data).then(res => {
                if (res != null && res.code === 200) {
                  if (res.data.caseStypeFind === '01') {
                    this.$message({
                      message: '提交成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                    this.$store.dispatch("tagsView/delView", this.$route);
                    this.$router.go(-1)
                  } else if (res.data.caseStypeFind === '02') {
                    this.$confirm(`此被保人只有健康险保单，确认后将提交至健康险?`, '提示', {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'warning'
                    }).then(() => {
                      let data = {
                        claimCase: {rptNo: this.querys.rptNo},
                        insuredNo: this.$refs.insuredForm.baseForm.insuredNo,//被保人客户号
                        name: this.$refs.insuredForm.baseForm.name,//被保人姓名
                        caseStypeFind: '02'
                      }
                      editCaseAndRecordInfoSuspend(data).then(response => {
                        if (res != null && response.code === 200) {
                          this.$message({
                            message: '提交成功！',
                            type: 'success',
                            center: true,
                            showClose: true
                          })
                          this.$store.dispatch("tagsView/delView", this.$route);
                          this.$router.go(-1)
                        }
                      })
                    }).catch(() => {
                      this.$message({
                        type: 'info',
                        message: '已取消！'
                      })
                    })
                  } else if (res.data.caseStypeFind === '03') {
                    return this.$message.warning(
                      "该被保人不存在保单信息，请撤件！"
                    )
                  }

                }
              }).catch(res => {

              })
            } else {
              return this.$message.warning(
                "该被保人不存在保单信息，请撤件！"
              )
            }
          } else {
            if (!(isInsuredSave || hasInsuredId)) {
              return this.$message.warning(
                "请保存被保人信息！"
              )
            } else if (!(isApplicantSave || hasApplicantId)) {
              return this.$message.warning(
                "请保存申请人信息！"
              )
            } else if (!(isAcceptInfoSave || hasAcceptId)) {
              return this.$message.warning(
                "请保存受理信息！"
              )
            } else if (this.$refs.payeeInfoForm.tableData.length <= 0) {
              return this.$message.warning(
                "请保存领款人信息！"
              )
            }
          }

        } else {
          let option = {
            rptNo: this.querys.rptNo
          }
          if (flag) {

            //获取被保人信息数据   保存被保人信息
            let insuredData = this.$refs.insuredForm.baseForm
            let tableData = this.$refs.insuredForm.tableData
            const subFormSearch = JSON.parse(JSON.stringify(insuredData))
            subFormSearch.rptNo = this.querys.rptNo


            let insuredInfoData = {
              policyNos: tableData,
              claimCaseInsured: subFormSearch
            }


            if ((isInsuredSave || hasInsuredId) && (isApplicantSave || hasApplicantId) && (isAcceptInfoSave || hasAcceptId) && this.$refs.payeeInfoForm.tableData.length > 0) {
              addInsuredAndPolicy(insuredInfoData)
              if (this.$refs.insuredForm.tableData != null && this.$refs.insuredForm.tableData.length > 0) {
                checkThePayment(option).then(res => {
                  if (res != null && res.code === 200) {
                    if (res.data == 1) {
                      let data = {
                        claimCase: {rptNo: this.querys.rptNo},
                        insuredNo: this.$refs.insuredForm.baseForm.insuredNo,//被保人客户号
                        name: this.$refs.insuredForm.baseForm.name//被保人姓名
                      }
                      editCaseAndRecordInfoSuspend(data).then(res => {
                        if (res != null && res.code === 200) {
                          if (res.data.caseStypeFind === '01') {
                            this.$message({
                              message: '提交成功！',
                              type: 'success',
                              center: true,
                              showClose: true
                            })

                            let option = {
                              rptNo: this.fixInfo.rptNo,
                              batchNo: this.fixInfo.batchNo,
                              idType: this.$refs.insuredForm.baseForm.idType,
                              name: this.$refs.insuredForm.baseForm.name,
                              idNo: this.$refs.insuredForm.baseForm.idNo,
                            }
                            setRptNo(option).then(res => {
                            }).catch(res => {
                            })
                            this.$store.dispatch("tagsView/delView", this.$route);
                            this.$router.go(-1)
                          } else if (res.data.caseStypeFind === '02') {
                            this.$confirm(`此被保人只有健康险保单，确认后将提交至健康险?`, '提示', {
                              confirmButtonText: '确定',
                              cancelButtonText: '取消',
                              type: 'warning'
                            }).then(() => {
                              let data = {
                                claimCase: {rptNo: this.querys.rptNo},
                                insuredNo: this.$refs.insuredForm.baseForm.insuredNo,//被保人客户号
                                name: this.$refs.insuredForm.baseForm.name,//被保人姓名
                                caseStypeFind: '02'
                              }
                              editCaseAndRecordInfoSuspend(data).then(response => {
                                if (res != null && response.code === 200) {
                                  this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    center: true,
                                    showClose: true
                                  })
                                  this.$store.dispatch("tagsView/delView", this.$route);
                                  this.$router.go(-1)
                                }
                              })
                            }).catch(() => {
                              this.$message({
                                type: 'info',
                                message: '已取消！'
                              })
                            })
                          } else if (res.data.caseStypeFind === '03') {
                            return this.$message.warning(
                              "该被保人不存在保单信息，请撤件！"
                            )
                          }
                        }
                      })

                    } else {
                      return this.$message.warning(
                        "领款人分配比例有误，请检查！"
                      )
                    }
                  }
                }).catch(res => {
                })
              } else {
                return this.$message.warning(
                  "该被保人不存在保单信息，请撤件！"
                )
              }
            } else {
              if (!(isInsuredSave || hasInsuredId)) {
                return this.$message.warning(
                  "请保存被保人信息！"
                )
              } else if (!(isApplicantSave || hasApplicantId)) {
                return this.$message.warning(
                  "请保存申请人信息！"
                )
              } else if (!(isAcceptInfoSave || hasAcceptId)) {
                return this.$message.warning(
                  "请保存受理信息！"
                )
              } else if (this.$refs.payeeInfoForm.tableData.length <= 0) {
                return this.$message.warning(
                  "请保存领款人信息！"
                )
              }
            }
          } else {
            return this.$message.warning(
              "领款人信息中与被保人关系存在本人时，被保人信息的职业和国籍必录！"
            )
          }


        }
      },
      goBack() {
        this.$store.dispatch("tagsView/delView", this.$route);
        if (this.querys.flag=='01'){
          window.close();
        }else {
          this.$router.go(-1)
        }
      },
      openAppealInfo() {
        this.appealDialog = true
      },
      openHistoryClaim() {
        this.historyClaimDialog = true
      },
      openGuaranteee() {
        this.guaranteeDialog = true
      },
      openHistorySurvey() {
        this.historySurveyDialog = true
      },
      openHistoryDiscussion() {
        this.historyDiscussionDialog = true
      },
      selectHistoricalProblem() {
        this.historicalProblemDialog = true
      },
      closeHistoricalProblem() {
        this.historicalProblemDialog = false
      },
      openRemoveDialog() {
        this.removeDialog = true
      },
      closeRemoveDialog() {
        this.removeDialog = false
      },
      closeAppealDialog() {
        this.appealDialog = false
      },
      closeHistoryClaimDialog() {
        this.historyClaimDialog = false
      },
      closeGuaranteeDialog() {
        this.guaranteeDialog= false
      },
      closeHistoryDiscussionDialog() {
        this.historyDiscussionDialog = false
      },
      closeHistorySurveyDialog() {
        this.historySurveyDialog = false
      },
      getInsuredData() {
        this.insuredFormData = {
          caseInsuredData: this.$refs.insuredForm.baseForm,
          policyInfoData: this.$refs.insuredForm.tableData,
        }
      },
      getPropData(val) {
        this.caseInsuredData = val
        this.caseInsuredData.claimType = this.batchInfo.claimtype
      },
      getPayeeDatas() {
        if (this.querys.claimType === '02') {
          listRemarkRptNo(this.querys.rptNo).then(res => {
            if (res != null && res.code === 200) {
              this.sonPayeeInfoData = res.data
            }
          })
        }
      },
    }
  }
</script>
<style lang='scss' scoped>
  .active {
    color: #67c23a;
    background: #fff;
  }

  .nav-flex-box {
    display: flex;
    width: 100%;
    height: 40px;
    line-height: 40px;
    color: #409eff;
    justify-content: center;
    background: #f8f8f8;
    z-index: 99999;
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

  .nav-bar {
    position: fixed;
    top: 0;
    right: 20px;
    z-index: 9;
    width: calc(100% - 240px) !important;
    transition: width 0.28s !important;
  }

  .nav-bar1 {
    position: fixed;
    top: 0;
    right: 20px;
    z-index: 9;
    width: calc(100% - 40px) !important;
  }

  .hideSidebar .nav-bar {
    width: calc(100% - 94px) !important;
    transition: width 0.28s !important;
  }

  .top-instance {
    top: 84px;
  }

  .top-instance1 {
    top: 10px;
  }

  .nav-bar ::v-deep .el-tabs--border-card > .el-tabs__content {
    padding: 0
  }

  .el-tabs ::v-deep .el-tabs__item {
    width: auto;
    font-size: 12px;
    padding: 0 12px;
  }

  .nav-bar ::v-deep .el-card__body,
  .top-card ::v-deep .el-card__body {
    padding: 10px 20px;
  }

  .basic-box ::v-deep .el-form-item__label {
    font-size: 12px;
  }

  .el-col-7 ::v-deep .el-form-item {
    margin-bottom: 10px;
  }

  .el-col-12 ::v-deep .el-form-item {
    margin-bottom: 4px;
  }

  .el-col-7 ::v-deep .el-form-item__label,
  .form-item-span {
    font-size: 12px;
  }

  .card-title {
    margin-top: 0;
  }

  .opened-adapt {
    width: 97.2%;
    // width: calc(100% - 233px);
    transition: .5s;
  }

  .close-adapt {
    width: 97.2%;
    // width: calc(100% - 78px);
    transition: .5s;
  }

  .startjump {
    margin-top: 90px;
  }

  .navFalgClass {
    margin-top: 100px !important;
  }
</style>
<style>
  .batchInfo_class .el-tag--small {
    margin-right: 10px !important;
  }

  .el-autocomplete-suggestion li {
    width: 100%;
    white-space: pre-wrap;
    word-break: break-word;
    height: auto;
    line-height: 18px;
    padding: 0 5px;
  }

  .el-collapse-item__wrap {
    border: none !important;
  }

  .el-collapse {
    border: none;
  }
</style>
