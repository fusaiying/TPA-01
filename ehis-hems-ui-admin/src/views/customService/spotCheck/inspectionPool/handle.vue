<template>
  <div class="app-container">
<!--    客户基本信息 -->
    <div id="#anchor-1" class="personInfo_class" style="margin-top: 5px;">
      <personInfo ref="personForm" :policyNo="this.params.policyNo" :policyItemNo="this.params.policyItemNo" />
    </div>

<!--    受理信息 -->
    <div id="#anchor-21" v-if="this.params.businessType=='01'" class="personInfo_class" style="margin-top: 5px;">
      <demandAcceptInfo :acceptInfo="allList.acceptInfo" :isDisabled="isDisabled" />
    </div>
    <div id="#anchor-22" v-if="this.params.businessType=='03'" class="personInfo_class" style="margin-top: 5px;">
      <complaintAcceptInfo :acceptInfo="allList.acceptInfo" :isDisabled="isDisabled"/>
    </div>
<!--    流转信息-->
<!--    <div id="#anchor-3" class="personInfo_class" style="margin-top: 5px;">-->
<!--      <complaintAcceptInfo :acceptInfo="acceptInfo" :isDisabled="isDisabled"/>-->
<!--    </div>-->
<!--    附件信息-->
    <div id="#anchor-4" class="personInfo_class" style="margin-top: 5px;">
      <attachmentList :attachmentInfoData="allList.attachmentInfoData"/>
    </div>
<!--    处理信息-->
  <!--    信息需求-->
    <div id="#anchor-51" v-if="this.params.businessType=='01'"  style="margin-top: 5px;" class="personInfo_class">
      <infohandle :acceptInfo="allList.acceptInfo"  />
    </div>
  <!--    投诉-->
    <div id="#anchor-52"  v-if="this.params.businessType=='03'"  style="margin-top: 5px;" class="personInfo_class">
      <complaintHandle :form="allList.form"  />
    </div>
<!--    质检处理-->
  <!--    信息需求-->
    <div id="#anchor-61" v-if="this.params.businessType=='01'"  style="margin-top: 5px;" class="personInfo_class">
      <inspectionProcessInfo :inspection="allList.inspection" ref="inspectionProcessInfo" v-model="handleInfoList"/>
    </div>
  <!--    投诉-->
    <div id="#anchor-62" v-if="this.params.businessType=='03'"  style="margin-top: 5px;" class="personInfo_class">
      <complaintProcessInfo :acceptInfo="allList.acceptInfo" ref="complaintProcessInfo" v-model="handleInfoList"/>
    </div>
    <div class="personInfo_class" style="margin-top: 5px;">
      <el-row :gutter=20 style="float: right"  >
        <el-button style="margin-right: 20px" type="primary" @click="submit()" v-if="this.params.businessType=='01'" size="mini">结案</el-button>
        <el-button style="margin-right: 20px" type="primary" @click="submit()" v-if="this.params.businessType=='01'" size="mini">案件复核</el-button>
        <el-button style="margin-right: 20px" type="primary" @click="submit()" v-if="this.params.businessType=='03'" size="mini">结案</el-button>
        <el-button style="margin-right: 20px" type="primary" @click="submit()" v-if="this.params.businessType=='03'" size="mini">退回修改</el-button>
      </el-row>
    </div>
  </div>
</template>
<script>
import personInfo from '@/views/customService/common/moduel/personInfo'; //客户基本信息
import demandAcceptInfo from "@/views/customService/common/moduel/demandAcceptInfo";//信息需求
import complaintAcceptInfo from "@/views/customService/common/moduel/complaintAcceptInfo";//投诉
import flowLogList from "@/views/customService/common/moduel/attachmentList";//流转记录列表
import attachmentList from "@/views/customService/common/moduel/attachmentList"; //附件列表
import complaintHandle from "@/views/customService/common/moduel/complaintHandle";//投诉处理
import infohandle from "@/views/customService/common/moduel/infohandle";//服务处理
import complaintProcessInfo from "@/views/customService/common/moduel/complaintProcessInfo";//投诉处理信息
import inspectionProcessInfo from "@/views/customService/common/moduel/inspectionProcessInfo";//质检处理信息
import {
  getAcceptInfoByTypeOrId,
  getAttachmentListById,
  getComplaintHandleInfo,
  getHandleInfoList,
  insertItem,
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
      itemList:[],
      businessType:'',
      appealFlag: '',
      appealReason : '',
      workOrderNo: '',
      score: '',
      allList:{
        attachmentInfoData: [],
        acceptInfo:{},
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
        inspection:{
          appeal :[
            {
              appealName: '是否申诉',
              appealFlag : '',
              appealReason: '',
            }
          ],
          items:[
            {
              itemKey:'是否时效内响应客户',
              value:'',
              remark:'',
            },
            {
              itemKey:'是否符合短信结案规则',
              value:'',
              remark:'',
            },
            {
              itemKey:'是否电话通知客户',
              value:'',
              remark:'',
            },
            {
              itemKey:'是否满足客户诉求',
              value:'',
              remark:'',
            },
            {
              itemKey:'是否及时升级投诉',
              value:'',
              remark:'',
            },
            {
              itemKey:'是否规范记录',
              value:'',
              remark:'',
            },
            {
              itemKey:'是否有其他错误',
              value:'',
              remark:'',
            },
          ]
        },
        complaintProcess: {
          appeal:[
            {
              appealName : '是否申诉',
              appealFlag: '',
              appealReason: '',
            }
          ],
          score:'',
          items: [
            {
              itemType:'时效性',
              itemKey:'投诉件录入时效',
              value:'',
              itemRemark: '',
            },
            {
              itemType:'时效性',
              itemKey: '响应时间',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'时效性',
              itemKey: '根因改善闭环时效',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '受理渠道',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '投诉分类',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '监管计件',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '监管撤诉状态',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '投诉原因',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '客户反馈',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '投诉损失',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '投诉是否成立',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '投诉根因部门',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '根因改善',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '致诉根因',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '处理结果',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '附件完整性',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '征求处理意见',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '处理意见',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'准确性',
              itemKey: '行协调解或外部鉴定状态',
              value: '',
              itemRemark: '',
            },
            {
              itemType:'真实性',
              itemKey: '案件真实性',
              value: '',
              itemRemark: '',
            },
          ]
        }
      },
      handleInfoList: [],
      //接收的参数对象
      params: {},
      isDisabled:true,
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
          this.allList.acceptInfo=res.data;
        }
      }).catch(res => {
      })
      //获取附件列表信息
      getAttachmentListById(query).then(res => {
        if (res != null && res.code === 200) {
          console.info(res.data);
          this.allList.attachmentInfoData=res.data;
        }
      }).catch(res => {
      })
      getComplaintHandleInfo(query).then(res => {
        if (res !== null && res.code === 200) {
          this.allList.form = res.data;
        }
      }).catch( res => {
      });

      getHandleInfoList(query).then(res => {
          if (res !== null && res.code === 200) {
            this.handleInfoList = res.rows;

        }
      }).catch( res => {
      });

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
    submit(){
      const value1=[];//判断是否申诉
      const value2=[];
      let data1={};
      let data2={};
      console.log(data2,"????????????????")
      //判断类型为信息需求时是否申诉
      if(this.params.businessType=='01'){
        data1 = this.$refs.inspectionProcessInfo.inspection;
        for(var i=0;i<data1.appeal.length;i++ ){
            value1[i]=data1.appeal[i].appealFlag;
        }
        console.log(value1,"59451")
        //获取信息需求所有的项目，值，说明
        this.allList.inspection.items=[]
        for (let i = 0; i < data1.items.length; i++) {
          this.allList.inspection.items.push({
            itemKey: data1.items[i].itemKey,
            value: data1.items[i].value,
            itemRemark: data1.items[i].itemRemark,
          });
        }
        //获取信息需求是否申诉
        this.allList.inspection.appeal=[]
        for (let i = 0; i < data1.appeal.length; i++) {
          this.allList.inspection.appeal.push({
            appealName: data1.appeal[i].appealName,
            appealFlag: data1.appeal[i].appealFlag,
            appealReason: data1.appeal[i].appealReason,
          });
        }
        //判断类型为投诉时是否申诉
      }else if(this.params.businessType=='03'){
        data2=this.$refs.complaintProcessInfo.complaintProcess;
        for(var i=0;i<data2.appeal.length;i++ ){
          value2[i]=data2.appeal[i].appealFlag;
        }
        console.log(value2,"59451")
        //获取投诉的所有分类，项目，值和说明
        this.allList.complaintProcess.items=[]
        for (let i = 0; i < data2.items.length; i++) {
          this.allList.complaintProcess.items.push({
            itemType: data2.items[i].itemType,
            itemKey: data2.items[i].itemKey,
            value: data2.items[i].value,
            itemRemark: data2.items[i].itemRemark,
          });
        }
        //获取投诉处理的是否申诉
        this.allList.complaintProcess.appeal=[]
        for (let i = 0; i < data2.appeal.length; i++) {
          this.allList.complaintProcess.appeal.push({
            appealName: data2.appeal[i].appealName,
            appealFlag: data2.appeal[i].appealFlag,
            appealReason: data2.appeal[i].appealReason,
          });
        }
      }
      //如果是否存在差错存在是，那么上面的card可修改，并保存
      if(value1.includes('01')){
        const save={
            itemList: this.allList,
            businessType: this.allList.acceptInfo.businessType
        }
        insertItem(save).then(response => {
          if (response.code === 200) {
            this.$message.success('保存成功!');
          }
        })
        //如果是否存在差错全是否，那么只保存此card信息
      }else{
        //提取数据并给参数赋值
        let save={};
          //当类型为01时数据保存
          if(this.allList.acceptInfo.businessType==='01'){
            save={
              itemList: this.allList.inspection.items,
              appealFlag: data1.appeal[0].appealFlag,
              appealReason: data1.appeal[0].appealReason,
              businessType: this.allList.acceptInfo.businessType,
              workOrderNo: this.allList.acceptInfo.workOrderNo,
            }
            //当类型为03时数据保存
          }else if(this.allList.acceptInfo.businessType==='03'){
            save={
              itemList: this.allList.complaintProcess.items,
              appealFlag: data2.appeal[0].appealFlag,
              appealReason: data2.appeal[0].appealReason,
              score: data2.score,
              businessType: this.allList.acceptInfo.businessType,
              workOrderNo: this.allList.acceptInfo.workOrderNo,
            }
          }
        console.log(save,"+++++++++++++????")
        //保存数据
        insertItem(save).then(response => {
          if (response.code === 200) {
            this.$message.success('保存成功!');
          }
        })
      }


    },
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
