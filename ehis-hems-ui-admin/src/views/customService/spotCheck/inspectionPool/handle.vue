<template>
  <div class="app-container">
<!--    客户基本信息 -->
    <div id="#anchor-1" class="personInfo_class" style="margin-top: 5px;">
      <personInfo ref="personForm" :policyNo="this.params.policyNo" :policyItemNo="this.params.policyItemNo" />
    </div>

<!--    受理信息 -->
    <div id="#anchor-21" v-if="this.params.businessType=='01'" class="personInfo_class" style="margin-top: 5px;">
      <demandAcceptInfo :acceptInfo="acceptInfo" :isDisabled="isDisabled" />
    </div>
    <div id="#anchor-22" v-if="this.params.businessType=='03'" class="personInfo_class" style="margin-top: 5px;">
      <complaintAcceptInfo :acceptInfo="acceptInfo" :isDisabled="isDisabled"/>
    </div>
<!--    流转信息-->
<!--    <div id="#anchor-3" class="personInfo_class" style="margin-top: 5px;">-->
<!--      <complaintAcceptInfo :acceptInfo="acceptInfo" :isDisabled="isDisabled"/>-->
<!--    </div>-->
<!--    附件信息-->
    <div id="#anchor-4" class="personInfo_class" style="margin-top: 5px;">
      <attachmentList :attachmentInfoData="attachmentInfoData"/>
    </div>
<!--    处理信息-->
<!--    信息需求-->
    <div id="#anchor-51">

    </div>
<!--    投诉-->
    <div id="#anchor-52">

    </div>
<!--    质检处理-->
<!--    信息需求-->
    <div id="#anchor-61">

    </div>
<!--    投诉-->
    <div id="#anchor-62">

    </div>
  </div>
</template>
<script>
import personInfo from '@/views/customService/common/moduel/personInfo'; //客户基本信息
import demandAcceptInfo from "@/views/customService/common/moduel/demandAcceptInfo";//信息需求
import complaintAcceptInfo from "@/views/customService/common/moduel/complaintAcceptInfo";//投诉
import flowLogList from "@/views/customService/common/moduel/attachmentList";//流转记录列表
import attachmentList from "@/views/customService/common/moduel/attachmentList";//附件列表
import {
  getAcceptInfoByTypeOrId,
  getAttachmentListById
} from '@/api/customService/spotCheck'

import {mapGetters} from 'vuex'

let dictss = [{dictType: 'delivery_source'},]

export default {
  components: {
    personInfo,
    demandAcceptInfo,
    complaintAcceptInfo,
    attachmentList,
  },
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
      //定义子页面对象
      acceptInfo: {},
      attachmentInfoData: [],
      //接收的参数对象
      params: {},
      isDisabled:true,
      navFlag: true,
      reportData: [],
      dictList: [],
      delivery_sourceOption: [],
      applicantData: {}
    }

  },
  created() {
    if (this.$route.query) {
      this.params = JSON.parse(decodeURI(this.$route.query.data))
      console.info(this.params);
      //获取受理信息
      let query = {
        workOrderNo: this.params.workOrderNo,
        businessType: this.params.businessType,
      }
      getAcceptInfoByTypeOrId(query).then(res => {
        if (res != null && res.code === 200) {
          console.info(res.data);
          this.acceptInfo=res.data;
        }
      }).catch(res => {
      })
      //获取附件列表信息
      getAttachmentListById(query).then(res => {
        if (res != null && res.code === 200) {
          console.info(res.data);
          this.attachmentInfoData=res.data;
        }
      }).catch(res => {
      })
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
      // this.querys = JSON.parse(decodeURI(this.$route.query.data))
      // getCase(this.querys).then(res => {
      //   if (res != null && res.code === 200) {
      //     this.fixInfo = res.data
      //   }
      // }).catch(res => {
      // })
      // getBatch(this.querys.batchNo).then(res => {
      //   if (res != null && res.code === 200) {
      //     this.batchInfo = res.data
      //   }
      // }).catch(res => {
      // })
      // getListRemark(this.querys.rptNo).then(res => {
      //   if (res != null && res.code === 200) {
      //     this.reportData = res.data
      //   }
      // }).catch(res => {
      // })
      // selectHistoricalProblem(this.querys.rptNo).then(res => {
      //   if (res!=null && res.code===200){
      //     this.historicalProblemData=res.rows
      //   }
      // })
    }
  },

  methods: {
    changeSaveFlag() {
      this.isSave = true
    },
    acceptOver(){
      //this.$refs.headerChild.属性insuredForm applicantCom acceptInfo

      //被保人信息是否保存，否则阻断提示：“请保存被保人信息。”，同时字段框红高亮显示；
      //申请人信息是否保存，否则阻断提示：“请保存申请人信息。”，同时字段框红高亮显示；
      //申请信息是否保存，否则阻断提示：“请保存申请信息”，同时字段框红高亮显示；
      //若选择的该被保人的保单不存在TPA保单也不存在健康险保单时，阻断提示：“该被保人不存在保单信息，请撤件”；
      // let isInsuredSave = this.$refs.insuredForm.isInsuredSave
      // let isApplicantSave=this.$refs.applicantInfoForm.isApplicantSave
      // let isAcceptInfoSave=this.$refs.acceptInfoForm.isAcceptInfoSave
      // if (isInsuredSave && isApplicantSave && isAcceptInfoSave){
      //   let data = {
      //     rptNo:this.querys.rptNo
      //   }
      //   editCaseAndRecordInfoSuspend(data).then(res=>{
      //     if(res!=null && res.code===200){
      //       this.$message({
      //         message: '提交成功！',
      //         type: 'success',
      //         center: true,
      //         showClose: true
      //       })
      //       this.changeDialogVisable()
      //     }
      //   }).catch(res=>{
      //     this.$message({
      //       message: '提交失败!',
      //       type: 'error',
      //       center: true,
      //       showClose: true
      //     })
      //   })
      // }else {
      //   if (!isInsuredSave){
      //     return this.$message.warning(
      //       "请保存被保人信息！"
      //     )
      //   }else if (!isApplicantSave){
      //     return this.$message.warning(
      //       "请保存申请人信息！"
      //     )
      //   }else if (!isAcceptInfoSave){
      //     return this.$message.warning(
      //       "请保存申请信息！"
      //     )
      //   }
      // }

    },
    goBack() {
      this.$router.go(-1)
    },
    // selectHistoricalProblem() {
    //   this.historicalProblemDialog = true
    // },
    // closeHistoricalProblem() {
    //   this.historicalProblemDialog = false
    // },
    // openRemoveDialog() {
    //   this.removeDialog = true
    // },
    // closeRemoveDialog() {
    //   this.removeDialog = false
    // },

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
