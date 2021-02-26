<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>案件基本信息</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <form>
        <el-row style="margin: 0 40px;padding-bottom: 20px">
          <el-col :span="8">
            <span class="info_span to_right">报案号：</span><span class="info_span">{{ baseInfo.rptNo }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">案件状态：</span><span class="info_span">{{ getClaimStatusName(baseInfo.caseStatus) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">被保险人：</span><span class="info_span">{{ baseInfo.name }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">证件号码：</span><span class="info_span">{{ (baseInfo.idNo) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">年龄：</span><span class="info_span">{{ (baseInfo.age) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">性别：</span><span class="info_span">{{ getGenderName(baseInfo.sex) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">交单来源：</span><span class="info_span">{{ getDeliverySourceName(baseInfo.source) }}</span>
          </el-col>
       </el-row>
      </form>
    </el-card>

    <el-card v-if="HistoryData.length > 0" class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
        <span>历史问题附件信息</span>
      </div>

      <form  v-for="(item,index) in HistoryData">
        <el-row style="margin: 20px 10px;">
          <el-col :span="8">
            <span class="info_span to_right">问题件类型：</span><span class="info_span">{{ getProblemTypeName(item.problemType) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">处理时间 ：</span><span class="info_span">{{ index == 0 ? item.createTime : item.updateTime }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">处理人：</span><span class="info_span">{{ index == 0 ?  item.createBy : item.updateBy }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">转出意见：</span><span class="info_span">{{ (item.problemView) }}</span>
          </el-col>
<!--          <el-col :span="8">
            <span class="info_span to_right">处理意见：</span><span class="info_span">{{ (item.conclusionView) }}</span>
          </el-col>-->
        </el-row>
      </form>
    </el-card>
      <!-- 历史问题附件信息 end -->


      <!-- 本次问题件处理 start -->
      <el-card  class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
        <span>本次问题件处理</span>
        <span style="float: right;">
          <el-button v-if="handleView" type="primary" size="mini" @click="dealFun(rptNo,clussionForm.conclusionView)">确认</el-button>
        </span>
      </div>

      <el-form ref="clussionForm" :model="clussionForm" style="padding-bottom: 0px;margin-left: 2%" label-width="130px" :rules="rules">
        <el-row>
          <el-col :span="8">
            <el-form-item label="问题件类型 ：" prop="bussinessStatus">
              <span class="info_span">{{getProblemTypeName(dealInfo.problemType)}}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row >
          <el-col :span="8">
            <el-form-item label="转出意见 ：" prop="bussinessStatus">
              <span class="info_span">{{dealInfo.problemView}}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row >
          <el-col :span="8">
            <el-form-item label="处理意见：" :style="{width:'100%'}" :class="['long-input']" prop="conclusionView" maxlength="2000" >
              <el-input maxlength="2000" type="textarea" :rows="3"  v-model="clussionForm.conclusionView"  clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!-- 本次问题件处理 end -->

    </el-card>
  </div>
</template>
<script>

  import { baseInfo,historicalProblem,PendingData,updateProblem } from '@/api/pbclaim/api'

  export default {
    data() {
      return {
        baseInfo: {
          rptNo:'',
          caseStatus: '',
          name:'',
          idNo:'',
          age:'',
          sex:'',
          source:'',
        },
        clussionForm :{
          clussion:'',
          conclusionView:'',
        },
        handleView :false,
        rptNo:'',
        problemId:'',
        HistoryData :[],

        dealInfo: {
          problemType:'',
          problemView: '',
        },
        problemTypes:[],
        deliverySource:[],
        claimStatusSelect:[],
        gender:[],

        rules: {
          conclusionView: {trigger: ['blur'], required: true, message: '处理意见必填'}
        },
      }
    },

     mounted() {

      //性别
       this.getDicts("rgtSex").then(response => {
         this.gender = response.data;
       });
       //问题件类型
       this.getDicts("problem_shipment_type").then(response => {
         this.problemTypes = response.data;
       });
       //交单来源
       this.getDicts("delivery_source").then(response => {
         this.deliverySource = response.data;
       });
       //案件状态 claim_status
       this.getDicts("claim_status").then(response => {
         this.claimStatusSelect = response.data;
       });

       this.rptNo = this.$route.query.rptNo ;
       this.problemId = this.$route.query.problemId ;
       if (this.$route.query.type != 'show') {
          this.handleView  = true;
          this.getPendingData();
       }

       if('' !=this.rptNo) {
         this.getBaseInfo();
         this.getHistoricalProblem();
       }
      // this.getDicts("cooperator").then(response => {
      //   this.cooperativeUnitSelects = response.data;
      // });
      //
      // if (this.flag == '01') {
      // }
    },
    methods: {
      getBaseInfo(){
        const param = {};
        param.rptNo = this.rptNo;
        baseInfo(param).then(res => {
          if(res.code == '200' && res.rows.length >0) {
            this.baseInfo = res.rows[0];
          }
        });
      },
      getHistoricalProblem(){
        const param = {};
        param.rptNo = this.rptNo;
        historicalProblem(param).then(res => {
          if(res.code == '200' && res.data.claimCaseProblems.length >0) {
            this.HistoryData = res.data.claimCaseProblems;
            if(!this.handleView) {
                let  vdata = this.HistoryData[this.HistoryData.length - 1];
                this.dealInfo.problemType = vdata.problemType;
                this.dealInfo.problemView = vdata.problemView;
                this.clussionForm.conclusionView = vdata.conclusionView;
            }
          }
        });
      },
      getPendingData(){
        const param = {};
        param.rptNo = this.rptNo;
        param.pageNum = 1;
        param.pageSize = 1;
        PendingData(param).then(res => {
          if(res.code == '200' && res.rows.length >0) {
            this.dealInfo = res.rows[0];
          }
        });
      },
      dealFun(rptNo,conclusionView){
        this.$refs.clussionForm.validate((valid) => {
          if (valid) {
            let vm = this;
            this.$confirm('确认处理本次问题件?', "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function () {
              const param = {};
              param.rptNo = rptNo;
              param.problemId = vm.problemId;
              param.conclusionView = conclusionView;
              param.isHistory = 'Y';
              updateProblem(param).then(res => {
                if(res.code == '200') {
                  vm.handleView = false;
                  vm.$message({
                    message: '处理成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  });
                }
              });
            }).then(() => {
              //this.msgSuccess(text + "成功");
            }).catch(function (error) {
              console.log(error)
            });
          }
        })
      },
      goBack(){
        this.$router.go(-1)
      },
      getProblemTypeName(value) {
        return this.selectDictLabel(this.problemTypes,value)
      },
      getDeliverySourceName(value) {
        return this.selectDictLabel(this.deliverySource,value)
      },
      getClaimStatusName(value) {
        return this.selectDictLabel(this.claimStatusSelect, value)
      },
      getGenderName(value) {
        return this.selectDictLabel(this.gender, value)
      },
    }
  }
</script>

<style scoped>
  .small-input {
    width: 80%;
  }

  .to_right {
    width: 120px;
    text-align: right;
  }
  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>
