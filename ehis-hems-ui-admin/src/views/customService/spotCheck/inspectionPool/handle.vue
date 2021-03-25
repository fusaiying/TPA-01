<template>
  <div class="app-container">
    <!--    客户基本信息 -->
    <div id="#anchor-1" class="personInfo_class" style="margin-top: 5px;">
      <personInfo :routerParams="params" ref="personForm" :policyNo="this.params.policyNo"
                  :policyItemNo="this.params.policyItemNo"/>
    </div>

    <!--    受理信息 -->
    <div id="#anchor-21" v-if="this.params.businessType=='01'" class="personInfo_class" style="margin-top: 5px;">
      <demandAcceptInfo :routerParams="params" :acceptInfo="allList.acceptInfo" :isDisabled="isDisabled"/>
    </div>
    <div id="#anchor-22" v-if="this.params.businessType=='03'" class="personInfo_class" style="margin-top: 5px;">
      <!--投诉-->
      <complaintAcceptInfo ref="complaintAcceptInfo" :routerParams="params" :acceptInfo="allList.acceptInfo"
                           :isAcceptInfo="isAcceptInfo"/>
    </div>
    <!--    流转信息-->
    <div id="#anchor-3" class="personInfo_class" style="margin-top: 5px;">
      <flowLogInfo :routerParams="params" :acceptInfo="allList.acceptInfo" :disabled="true"/>
    </div>
    <!--    附件信息-->
    <div id="#anchor-4" class="personInfo_class" style="margin-top: 5px;">
      <attachmentList :routerParams="params" :attachmentInfoData="allList.attachmentInfoData"/>
    </div>
    <!--    处理信息-->
    <!--    信息需求-->
    <div id="#anchor-51" v-if="this.params.businessType=='01'" style="margin-top: 5px;" class="personInfo_class">
      <infohandle :routerParams="params" :acceptInfo="allList.acceptInfo" :isDisabled="isDisabled"/>
    </div>
    <!--    投诉投诉处理-->
    <div id="#anchor-52" v-if="this.params.businessType=='03'" style="margin-top: 5px;" class="personInfo_class">
      <complaintHandle :formData="allList.form" ref="complaintHandle" :routerParams="params" :form="allList.form" :isAcceptInfo="isAcceptInfo"/>
    </div>
    <!--    质检处理-->
    <!--    信息需求-->
    <!--   <div id="#anchor-61" v-if="this.params.businessType=='01'"  style="margin-top: 5px;" class="personInfo_class">
         <inspectionProcessInfo :routerParams="params" :inspection="allList.inspection" ref="inspectionProcessInfo"
                                @selectHandleStatus="searchHandleStatus" :isDisabled="disableFlag" @getHandleInfoList="searchHandleList"/>
       </div>-->
    <!--    投诉-->
    <div id="#anchor-62" style="margin-top: 5px;" class="personInfo_class">
      <complaintProcessInfo :businessType="params.businessType" :routerParams="params" :acceptInfo="allList.acceptInfo"
                            ref="complaintProcessInfo" :node="params.node"
                            @changeShowStatus="changeShowStatus" :isDisabled="disableFlag"
                            @changeAppealFlag="changeAppealFlag"/>
    </div>
    <div class="personInfo_class" style="margin-top: 10px;margin-bottom: 50px">
      <el-row :gutter=20 style="float: right">
        <el-button :disabled="showCase || params.status=='show'" style="margin-right: 20px" type="primary"
                   @click="submit1('03')" v-if="this.params.businessType=='01' && this.params.node!='mistake'"
                   size="mini">结案
        </el-button>
        <el-button :disabled="showReview || params.status=='show'" style="margin-right: 20px" type="primary"
                   @click="submit1('01')" v-if="this.params.businessType=='01' && this.params.node!='mistake'"
                   size="mini">案件复核
        </el-button>
        <el-button :disabled="showCase || params.status=='show'" style="margin-right: 20px" type="primary"
                   @click="submit1('03')" v-if="this.params.businessType=='03' && this.params.node!='mistake'"
                   size="mini">结案
        </el-button>
        <el-button :disabled="showReview || params.status=='show'" style="margin-right: 20px" type="primary"
                   @click="submit1('01')" v-if="this.params.businessType=='03' && this.params.node!='mistake'"
                   size="mini">退回修改
        </el-button>
        <el-button v-if="this.params.node=='mistake'" style="margin-right: 20px" type="primary" @click="submit1('02')"
                   :disabled="params.status=='show'" size="mini">提交 <!--  差错提交      -->
        </el-button>
        <el-button v-if="this.params.node=='mistake'" style="margin-right: 20px" type="primary" @click="closeHandle"
                   size="mini">关闭
        </el-button>
      </el-row>
    </div>
  </div>
</template>
<script>
  import personInfo from '../../common/moduel/personInfo'; //客户基本信息
  import demandAcceptInfo from "../../common/moduel/demandAcceptInfo";//信息需求
  import complaintAcceptInfo from "../../common/moduel/complaintAcceptInfo";//投诉
  import flowLogList from "../../common/moduel/attachmentList";//流转记录列表
  import attachmentList from "../../common/moduel/attachmentList"; //附件列表
  import complaintHandle from "../../common/moduel/complaintHandle";//投诉处理
  import infohandle from "../../common/moduel/infohandle";//服务处理
  import complaintProcessInfo from "../../common/moduel/complaintProcessInfo";//投诉处理信息
  import inspectionProcessInfo from "../../common/moduel/inspectionProcessInfo";//质检处理信息
  import flowLogInfo from "../../common/moduel/flowLogInfo";
  import {
    getAcceptInfoByTypeOrId,
    getAttachmentListById,
    getComplaintHandleInfo,
    getHandleInfoList,
    insertItem,
    insertItem2,
    selectHandleStatus,
    insetQualityHandleInfo,
  } from '@/api/customService/spotCheck';

  import {mapGetters} from 'vuex'


  let dictss = [{dictType: 'delivery_source'},]

  export default {
    components: {
      personInfo,
      demandAcceptInfo,
      complaintAcceptInfo,
      attachmentList,
      complaintProcessInfo,
      complaintHandle,
      inspectionProcessInfo,
      infohandle,
      flowLogInfo,
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
        showCase: false,
        showReview: false,
        historicalProblemData: [],
        historicalProblemDialog: false,
        removeDialog: false,
        isSave: false,
        btnArr: [],
        //定义子页面对象
        itemList: [],
        businessType: '',
        appealFlag: '',
        appealReason: '',
        workOrderNo: '',
        status: '',
        disableFlag: true,
        score: '',
        acceptInfoForm: {},
        attachmentInfoForm: {},
        formForm: {},
        allList: {
          flowLogData: [],
          attachmentInfoData: [],
          acceptInfo: {},
          form: {
            level1: '',
            level2: '',
            pieceworkFlag: '',
            complaintStatus: '',
            faseReason: '',
            repeatedComplaint: '',
            reason1: '',
            reason2: '',
            reason3: '',
            complaintLink: '',
            complaintQuestion: '',
            outsideState: '',
            riskType: '',
            marketChannel: '',
            complaintCategory: '',
            customerFeedback: '',
            rootImprovement: '',
            actPromptly: '',
            rootDepartment: '',
            actionCause: '',
            treatmentResult: '',
          },
          inspection: {
            appeal: [
              {
                appealName: '是否申诉',
                appealFlag: '',
                appealReason: '',
              }
            ],
            items: [
              {
                itemKey: '是否时效内响应客户',
                value: '',
                remark: '',
              },
              {
                itemKey: '是否符合短信结案规则',
                value: '',
                remark: '',
              },
              {
                itemKey: '是否电话通知客户',
                value: '',
                remark: '',
              },
              {
                itemKey: '是否满足客户诉求',
                value: '',
                remark: '',
              },
              {
                itemKey: '是否及时升级投诉',
                value: '',
                remark: '',
              },
              {
                itemKey: '是否规范记录',
                value: '',
                remark: '',
              },
              {
                itemKey: '是否有其他错误',
                value: '',
                remark: '',
              },
            ]
          },
          complaintProcess: {
            appeal: [
              {
                appealName: '是否申诉',
                appealFlag: '',
                appealReason: '',
              }
            ],
            score: '',
            items: [
              {
                itemType: '时效性',
                itemKey: '投诉件录入时效',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '时效性',
                itemKey: '响应时间',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '时效性',
                itemKey: '根因改善闭环时效',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '受理渠道',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '投诉分类',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '监管计件',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '监管撤诉状态',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '投诉原因',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '客户反馈',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '投诉损失',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '投诉是否成立',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '投诉根因部门',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '根因改善',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '致诉根因',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '处理结果',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '附件完整性',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '征求处理意见',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '处理意见',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '准确性',
                itemKey: '行协调解或外部鉴定状态',
                value: '',
                itemRemark: '',
              },
              {
                itemType: '真实性',
                itemKey: '案件真实性',
                value: '',
                itemRemark: '',
              },
            ]
          }
        },
        complaintProcessList: [],
        inspectionList: [],
        //接收的参数对象
        params: {},
        isDisabled: true,
        isAcceptInfo: true,
        navFlag: true,
        reportData: [],
        dictList: [],
        delivery_sourceOption: [],
        applicantData: {},
      }

    },
    created() {
      if (this.$route.query) {

        this.params = JSON.parse(decodeURI(this.$route.query.data))
        //获取受理信息
        let query = {
          workOrderNo: this.params.workOrderNo,
          businessType: this.params.businessType,
        }
        getAcceptInfoByTypeOrId(query).then(res => {
          if (res != null && res.code === 200) {
            console.info(res.data);
            this.allList.acceptInfo = res.data;
          }
        }).catch(res => {
        })
        //获取附件列表信息
        getAttachmentListById(query).then(res => {
          if (res != null && res.code === 200) {
            console.info(res.data);
            this.allList.attachmentInfoData = res.data;
          }
        }).catch(res => {
        })
        getComplaintHandleInfo(query).then(res => {
          if (res !== null && res.code === 200) {
            this.allList.form = res.data;
          }
        }).catch(res => {
        });
        this.searchHandleStatus();
        this.searchHandleList();


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
      searchHandleStatus() {
        /*selectHandleStatus(query).then(res =>{
          if (res !== null && res.code === 200) {
            this.status = res.data;
          }
        })*/
      },
      searchHandleList() {
        /* getHandleInfoList(query).then(res => {
           if(query.businessType === '01'){
             if (res !== null && res.code === 200) {
               this.inspectionList = res.rows;
               console.log(this.inspectionList,'+++++++++5555555');
             }
           }else if(query.businessType === '03'){
             if (res !== null && res.code === 200) {
               this.complaintProcessList = res.rows;
               console.log(this.allList.complaintProcess)
             }
           }

         }).catch( res => {
         });*/
      },
      submit1(value) {
        let data = this.$refs.complaintProcessInfo.getPlaintAllData()
        let item = {
          inspectionId: this.params.inspectionId,//质检号
          workOrderNo: this.params.workOrderNo,
          score: data.score,//评分
          appealFlag: data.appealFlag,//是否申诉
          appealReason: data.appealReason,//申诉理由
          status: value,//01待确认02已确认03结案04无
          businessType: this.params.businessType,
          items: data.items
        }

        if (value=='02'){
          item.inspectionHandlerId=this.params.inspectionHandlerId
        }
        if (value=='02' && this.params.businessType=='03') {
          //受理信息
          item.complaintAcceptVo = this.$refs.complaintAcceptInfo.acceptForm;
          //投诉处理信息
          item.complaintDealVo = this.$refs.complaintHandle.form;
        }
        let flag=true
        item.items.forEach(option => {
          if (option.itemValue=='01' && (option.itemRemark=='' || option.itemRemark==null || option.itemRemark==undefined)){
            flag=false
          }
        })
        if (flag){
          if (item.appealFlag=='01' && (item.appealReason=='' || item.appealReason==null || item.appealReason==undefined)){
            return this.$message.warning(
              "请录入申诉理由！"
            )
          }else {
           if (this.$refs.complaintProcessInfo.checkForm()){
             if (value=='02' && this.params.businessType=='03' ){
               if (this.$refs.complaintAcceptInfo.checkForm() && this.$refs.complaintHandle.checkForm()){
                 insetQualityHandleInfo(item).then(res => {
                   if (res != null && res.code == '200') {
                     this.$message({
                       message: '提交成功！',
                       type: 'success',
                       center: true,
                       showClose: true
                     })
                     this.closeHandle()
                   }
                 })
               }else {
                 return this.$message.warning(
                   "请录入必要信息！"
                 )
               }
             }else {
               insetQualityHandleInfo(item).then(res => {
                 if (res != null && res.code == '200') {
                   this.$message({
                     message: '提交成功！',
                     type: 'success',
                     center: true,
                     showClose: true
                   })
                   this.closeHandle()
                 }
               })
             }

           }
          }
        }else {
          return this.$message.warning(
            "当投诉质检处理的是否存在差错为'是'时，质检说明不能为空，请检查！"
          )
        }

      },
      changeSaveFlag() {
        this.isSave = true
      },

      goBack() {
        this.$router.go(-1)
      },
      changeShowStatus(value) {
        if (value == '1') {
          this.showCase = true
          this.showReview = false
        } else if (value == '2') {
          this.showCase = false
          this.showReview = true
        }

      },
      changeAppealFlag(value) {
        if (value == '01') {
          this.isAcceptInfo = false
        } else if (value == '02') {
          this.isAcceptInfo = true
        }
      },
      closeHandle(value) {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.go(-1)
      }

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

  //.nav-bar /deep/ .el-tabs--border-card > .el-tabs__content {
  //  padding: 0
  //}
  //
  //.el-tabs /deep/ .el-tabs__item {
  //  width: auto;
  //  font-size: 12px;
  //  padding: 0 12px;
  //}
  //
  //.nav-bar /deep/ .el-card__body,
  //.top-card /deep/ .el-card__body {
  //  padding: 10px 20px;
  //}
  //
  //.basic-box /deep/ .el-form-item__label {
  //  font-size: 12px;
  //}
  //
  //.el-col-7 /deep/ .el-form-item {
  //  margin-bottom: 10px;
  //}
  //
  //.el-col-12 /deep/ .el-form-item {
  //  margin-bottom: 4px;
  //}
  //
  //.el-col-7 /deep/ .el-form-item__label,
  //.form-item-span {
  //  font-size: 12px;
  //}
  //
  //.card-title {
  //  margin-top: 0;
  //}
  //
  //.opened-adapt {
  //  width: 97.2%;
  //  // width: calc(100% - 233px);
  //  transition: .5s;
  //}
  //
  //.close-adapt {
  //  width: 97.2%;
  //  // width: calc(100% - 78px);
  //  transition: .5s;
  //}
  //
  //.startjump {
  //  margin-top: 90px;
  //}
  //
  //.navFalgClass {
  //  margin-top: 10px !important;
  //}
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
