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
            style="color:#409EFF;font-size:12px">申请来源：{{ selectDictLabel(delivery_sourceOption, fixInfo.source)  }}　</span>
        </span>
        <el-form style="float: right; padding: 3px 0">
          <span>
            <el-button type="primary" v-if="querys.node==='calculateReview'" size="mini"
                       @click="openAppealInfo">申述信息</el-button>
            <el-button type="primary" size="mini" @click="">影像查看</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview'" size="mini"
                       @click="openHistoryClaim">历史理赔</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview'" size="mini" @click="">保障查看</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview'" size="mini" @click="openHistorySurvey">调查(?)</el-button>
            <el-button type="primary" v-if="querys.node==='calculateReview'" size="mini" @click="openHistoryDiscussion">协谈(2)</el-button>
            <el-button type="primary" v-if="querys.node==='accept'" size="mini"
                       @click="selectHistoricalProblem">问题件</el-button>
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
        <insured-com :sonInsuredData="sonInsuredData" :node="querys.node" :status="querys.status" ref="insuredForm"
                     :fixInfo="fixInfo" @emitSaveFlag="changeSaveFlag"/>
      </div>
      <!-- 申请人信息 -->
      <div id="#anchor-12" class="batchInfo_class" style="margin-top: 10px;">
        <applicant-com :sonRegisterData="sonRegisterData" :node="querys.node" :status="querys.status"
                       ref="applicantInfoForm" :applicantData="applicantData" :fixInfo="fixInfo"
                       @getApplicantData="getApplicantData"/>
      </div>
      <!-- 领款人信息 -->
      <div id="#anchor-11" class="batchInfo_class" style="margin-top: 10px;">
        <payeeInfo :sonPayeeInfoData="sonPayeeInfoData" ref="payeeInfoForm" :baseInfo="batchInfo" :fixInfo="fixInfo"
                   @getApplicantData="getApplicantData"
                   @refresh-item="refreshList" :applicantData="applicantData" :node="querys.node"
                   :status="querys.status"/>
      </div>
      <!-- 受理信息 -->
      <div id="#anchor-13" class="batchInfo_class" style="margin-top: 10px;">
        <acceptInfo :sonAcceptInfoData="sonAcceptInfoData" ref="acceptInfoForm" :claimtype="querys.claimtype"
                    :baseInfo="batchInfo" :isSave="isSave"
                    :node="querys.node" :status="querys.status" :fixInfo="fixInfo"/>
      </div>
      <!-- 账单明细 -->
      <div v-if="querys.node==='input' || querys.node==='calculateReview'" id="#anchor-15" class="batchInfo_class"
           style="margin-top: 10px;">
        <billing-details ref="billingInfoForm" :sonBillingInfoData="sonBillingInfoData" :claimtype="querys.claimtype"
                         :fixInfo="fixInfo"
                         :node="querys.node" :status="querys.status" @refresh-item="refreshList"/>
      </div>
      <!-- 案件理算 -->
      <div v-if="querys.node==='calculateReview'" id="#anchor-18" class="batchInfo_class" style="margin-top: 10px;">
        <case-calculate :sonCalculateData="sonCalculateData" :fixInfo="fixInfo"/>
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
      <div v-if="querys.node==='calculateReview'" class="batchInfo_class" style="margin-top: 10px;">
        <pay-conclusion :fixInfo="fixInfo"/>
      </div>
      <!--赔付结论-->
      <div v-if="querys.node==='calculateReview' || querys.node==='sport'" id="#anchor-17" class="batchInfo_class" style="margin-top: 10px;">
        <discussion :fixInfo="fixInfo" :node="querys.node"/>
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
    <history-claim :value="historyClaimDialog" :fixInfo="fixInfo" @closeHistoryClaimDialog="closeHistoryClaimDialog"/>
    <!-- 历史协谈 -->
    <history-discussion :value="historyDiscussionDialog" :fixInfo="fixInfo"
                        @closeHistoryDiscussionDialog="closeHistoryDiscussionDialog"/>
    <!-- 历史调查 -->
    <history-survey :value="historySurveyDialog" :fixInfo="fixInfo"
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
  import mixinAnchor from "../../claimsHandle/common/mixins/mixinAnchor";
  import {mapGetters} from 'vuex'

  import historyProblemCase from './modul/problemCase' //历史问题件
  import removeCase from './modul/removeCase' //撤件
  import appealInfo from './modul/appealInfo' //申述信息
  import historyClaim from './modul/historyClaim' //历史理赔
  import historyDiscussion from './modul/historyDiscussion' //历史协谈
  import historySurvey from './modul/historySurvey' //历史调查

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
    adjustRemarkList
  } from '@/api/claim/handleCom'

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
      discussion
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
    data() {
      return {
        sonInsuredData: {
          claimCaseInsured: '',
          policyInfominData: []
        },
        sonRegisterData: {},
        sonPayeeInfoData: [],
        sonBillingInfoData: [],
        sonAcceptInfoData: {},
        sonProblemData: [],
        sonCalculateData: [],
        historicalProblemData: [],
        historicalProblemDialog: false,
        removeDialog: false,
        appealDialog: false,
        historyClaimDialog: false,
        historyDiscussionDialog: false,
        historySurveyDialog: false,
        isSave: false,
        btnArr: [],
        fixInfo: {
          batchNo: undefined,
          filingNo: undefined,
          rptNo: undefined,
          source: undefined,
        },
        batchInfo: {},//批次信息
        querys: {},
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
        reportData: [],
        dictList: [],
        delivery_sourceOption: [],
        applicantData: {}

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
        getCase(this.querys).then(res => {
          if (res != null && res.code === 200) {
            this.fixInfo = res.data
          }
        }).catch(res => {
        })
        getBatch(this.querys.batchNo).then(res => {
          if (res != null && res.code === 200) {
            this.batchInfo = res.data
            this.querys.claimType=res.data.claimtype
          }
        }).catch(res => {
        })
        selectHistoricalProblem(this.querys.rptNo).then(res => {
          if (res != null && res.code === 200) {
            this.historicalProblemData = res.rows
          }
        })
        getInsured(this.querys.rptNo).then(res => {

          if (res != null && res.code === 200) {
            if (res.data.claimCaseInsured != null && res.data.claimCaseInsured !== '') {
              this.sonInsuredData.claimCaseInsured = res.data.claimCaseInsured
              this.isSave = true
            }
            if (res.data.policyInfominData != null && res.data.policyInfominData.length > 0) {
              this.sonInsuredData.policyInfominData = res.data.policyInfominData
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

        if (this.querys.claimType==='01'){
          let data={
            rptNo:this.querys.rptNo
          }
          getHospital(data).then(res => {
            if (res != null && res.code === 200) {
              this.sonPayeeInfoData = res.rows
            }
          })
        }else {
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
      } else if (this.querys.node === 'calculateReview') {
        this.btnArr = this.calculateArr
      }

    },

    methods: {
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
              /*this.$router.replace('/login')*/
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
          if (this.querys.claimType==='01'){
            let data={
              rptNo:this.querys.rptNo
            }
            getHospital(data).then(res => {
              if (res != null && res.code === 200) {
                this.sonPayeeInfoData = res.data
              }
            })
          }else {
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
        if ((isInsuredSave || hasInsuredId) && (isApplicantSave || hasApplicantId) && (isAcceptInfoSave || hasAcceptId)) {
          let data = {
            rptNo: this.querys.rptNo
          }
          editCaseAndRecordInfoSuspend(data).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '提交成功！',
                type: 'success',
                center: true,
                showClose: true
              })
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
              "请保存申请信息！"
            )
          }
        }

      },
      goBack() {
        this.$router.go(-1)
      },
      openAppealInfo() {
        this.appealDialog = true
      },
      openHistoryClaim() {
        this.historyClaimDialog = true
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
      closeHistoryDiscussionDialog() {
        this.historyDiscussionDialog = false
      },
      closeHistorySurveyDialog() {
        this.historySurveyDialog = false
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

  .hideSidebar .nav-bar {
    width: calc(100% - 94px) !important;
    transition: width 0.28s !important;
  }

  .top-instance {
    top: 84px;
  }

  .nav-bar /deep/ .el-tabs--border-card > .el-tabs__content {
    padding: 0
  }

  .el-tabs /deep/ .el-tabs__item {
    width: auto;
    font-size: 12px;
    padding: 0 12px;
  }

  .nav-bar /deep/ .el-card__body,
  .top-card /deep/ .el-card__body {
    padding: 10px 20px;
  }

  .basic-box /deep/ .el-form-item__label {
    font-size: 12px;
  }

  .el-col-7 /deep/ .el-form-item {
    margin-bottom: 10px;
  }

  .el-col-12 /deep/ .el-form-item {
    margin-bottom: 4px;
  }

  .el-col-7 /deep/ .el-form-item__label,
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
    margin-top: 10px !important;
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
