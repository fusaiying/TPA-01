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
        <span style="margin-right: 10px">
          <span style="color:#409EFF;font-size:12px">归档号：{{ fixInfo.filingNo }}　</span>
        </span>
        <span style="margin-right: 10px">
          <span
            style="color:#409EFF;font-size:12px">申请来源：{{ selectDictLabel(delivery_sourceOption, fixInfo.source)  }}　</span>
        </span>
        <el-form style="float: right; padding: 3px 0">
          <span>
            <el-button type="primary" size="mini" @click="">影像查看</el-button>
            <el-button type="primary" size="mini" @click="selectHistoricalProblem">问题件</el-button>
            <el-button type="primary" size="mini" @click="openRemoveDialog">撤件</el-button>
            <el-button type="primary" size="mini" @click="">受理完毕</el-button>
            <el-button size="mini" @click="goBack">返回</el-button>
          </span>
        </el-form>
      </el-card>
    </div>
    <div id="start_jump" class="startjump" :class="{'navFalgClass': navFlag === false}">
      <!-- 批次信息 -->
      <div id="#anchor-21" class="batchInfo_class" style="margin-top: 10px;">
        <batchInfo :baseInfo="batchInfo"/>
      </div>
      <!-- 被保人信息 -->
      <div id="#anchor-2" class="batchInfo_class" style="margin-top: 10px;">
        <insured-com :fixInfo="fixInfo" ref="insuredForm" @emitSaveFlag="changeSaveFlag"/>
      </div>
      <!-- 申请人信息 -->
      <div id="#anchor-12" class="batchInfo_class" style="margin-top: 10px;">
        <applicant-com :applicantData="applicantData" :fixInfo="fixInfo" @getApplicantData="getApplicantData"/>
      </div>
      <!-- 领款人信息 -->
      <div id="#anchor-11" class="batchInfo_class" style="margin-top: 10px;">
        <payeeInfo :baseInfo="batchInfo" :fixInfo="fixInfo" @getApplicantData="getApplicantData"
                   :applicantData="applicantData"/>
      </div>
      <!-- 受理信息 -->
      <div id="#anchor-13" class="batchInfo_class" style="margin-top: 10px;">
        <acceptInfo ref="acceptInfo" :claimtype="this.querys.claimtype" :baseInfo="batchInfo" :status="isSave"/>
      </div>
      <!--赔案备注-->
      <div id="#anchor-16" class="batchInfo_class" style="margin-top: 10px;">
        <reportNotes :reportData="reportData"/>
      </div>
      <!--问题件-->
      <div id="#anchor-14" class="batchInfo_class" style="margin-top: 10px;">
        <problemCase :fixInfo="fixInfo"/>
      </div>
    </div>
    <!-- 历史问题件模态框 -->
    <history-problem-case :historicalProblemData="historicalProblemData" :value="historicalProblemDialog" @closeHistoricalProblem="closeHistoricalProblem"/>
    <!-- 撤件模态框 -->
    <remove-case :value="removeDialog" @closeRemoveDialog="closeRemoveDialog" :fixInfo="fixInfo"/>
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
  import mixinAnchor from "../../claimsHandle/common/mixins/mixinAnchor";
  import {mapGetters} from 'vuex'

  import historyProblemCase from './modul/problemCase' //历史问题件
  import removeCase from './modul/removeCase' //撤件

  import {getCase, getBatch, selectHistoricalProblem, getListRemark} from '@/api/claim/handleCom'

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
      removeCase
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
        historicalProblemData: [],
        historicalProblemDialog: false,
        removeDialog: false,
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
      this.btnArr = this.acceptArr
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
          }
        }).catch(res => {
        })
        getListRemark(this.querys.rptNo).then(res => {
          if (res != null && res.code === 200) {
            this.reportData = res.data
          }
        }).catch(res => {
        })
        selectHistoricalProblem(this.querys.rptNo).then(res => {
          if (res!=null && res.code===200){
            this.historicalProblemData=res.rows
          }
        })
      }
    },

    methods: {
      changeSaveFlag() {
        this.isSave = true
      },
      getApplicantData() {
        this.applicantData = this.$refs.insuredForm.baseForm
      },
      goBack() {
        this.$router.go(-1)
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
  .abatchInfo_class .el-tag--small {
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
