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
            <span class="info_span to_right">出单公司：</span><span class="info_span">{{ baseInfo.companyName }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">被保险人：</span><span class="info_span">{{ (baseInfo.name) }}</span>
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
            <span class="info_span to_right">出险日期：</span><span class="info_span">{{ (baseInfo.accDate) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">交单来源：</span>
            <span class="info_span">{{ getDeliverySourceName(baseInfo.source) }}  </span>
          </el-col>
       </el-row>
      </form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
        <span>历史协谈信息</span>
      </div>

      <form  v-for="(item,index) in HistoryData">
        <el-row style="margin: 20px 10px;">
          <el-col :span="8">
            <span class="info_span to_right">协谈序号：</span><span class="info_span">{{ item.discId }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">协谈处理时间 ：</span><span class="info_span">{{ (item.createTime) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">协谈结论：</span><span class="info_span">{{ getConclusionName(item.conclusion) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">协谈人：</span><span class="info_span">{{ (item.createBy) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">协谈意见：</span><span class="info_span">{{ (item.conclusionView) }}</span>
          </el-col>
        </el-row>
      </form>
    </el-card>
      <!-- 历史协谈信息 end -->



      <!-- 本次协谈处理 start -->
      <el-card v-if="handleView" class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
        <span>本次协谈处理</span>
        <span style="float: right;">
          <el-button  v-if="dealBtn" type="primary" size="mini" @click="dealFun(rptNo,clussionForm)">确认</el-button>
        </span>
      </div>

      <el-form ref="clussionForm" :model="clussionForm" style="padding-bottom: 30px;margin-left: 2%" label-width="130px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="审核转出意见：" prop="bussinessStatus">
              <span class="info_span"> {{ baseInfo.disView }} </span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row >
          <el-col :span="8">
            <el-form-item label="协谈处理结论：" prop="bussinessStatus">
              <el-select v-model="clussionForm.conclusion" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="item in clusionSelect" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row >
          <el-col :span="8">
            <el-form-item label="协谈意见：" :style="{width:'100%'}" :class="['long-input']" prop="remark" maxlength="2000" >
              <el-input maxlength="2000" type="textarea" :rows="3"  v-model="clussionForm.conclusionView"  clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!-- 本次协谈处理 end -->

    </el-card>
  </div>
</template>
<script>

  import { baseInfo ,historyDisInfo, updateDiss} from '@/api/negotiation/api'


  export default {
    data() {
      return {

        dealBtn :true,
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
          conclusion:'',
          conclusionView:'',
        },
        rptNo:'',
        handleView:false,
        HistoryData :[],
        clusionSelect:[],
        deliverySource:[],
        claimStatusSelect:[],
        gender:[],

      }
    },

     mounted() {
       this.rptNo = this.$route.query.rptNo ;
       if (this.$route.query.type != 'show') {
         this.handleView  = true;
        // this.getPendingData();
       }

       this.getDicts("handleconclusion").then(response => {
         this.clusionSelect = response.data;
       });

       //交单来源
       this.getDicts("delivery_source").then(response => {
         this.deliverySource = response.data;
       });
       //案件状态 claim_status
       this.getDicts("claim_status").then(response => {
         this.claimStatusSelect = response.data;
       });
       //性别
       this.getDicts("sex").then(response => {
         this.gender = response.data;
       });

       if('' != this.rptNo) {
         this.getBaseInfo();
         this.getHistoryDisInfo();
       }
    },
      methods: {
        dealFun(rptNo,conclusionForm){
          let vm = this;
          this.$confirm('确认提交本次协谈处理?', "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function () {
            const param = {};
            param.rptNo = rptNo;
            param.conclusion = conclusionForm.conclusion;
            param.conclusionView = conclusionForm.conclusionView;
            param.isHistory = 'Y';
            updateDiss(param).then(res => {
              if(res.code == '200') {
                vm.dealBtn = false;
                vm.$message({
                  message: '处理成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
              }
            });
          }).then(() => {
          }).catch(function (error) {
            console.log(error)
          });
        },
        getBaseInfo(){
          baseInfo(this.rptNo).then(res => {
            if(res.code == '200' && res.data) {
              this.baseInfo = res.data;
            }
          });
        },
        getHistoryDisInfo(){
          historyDisInfo(this.rptNo).then(res => {
            if(res.code == '200' && res.rows) {
              this.HistoryData = res.rows;
            }
          });
        },
        getConclusionName(value) {
          return this.selectDictLabel(this.clusionSelect,value)
        },
        getGenderName(value) {
          return this.selectDictLabel(this.gender, value)
        },
        getDeliverySourceName(value) {
          return this.selectDictLabel(this.deliverySource,value)
        },
        getClaimStatusName(value) {
          return this.selectDictLabel(this.claimStatusSelect, value)
        },
        goBack(){
          this.$router.go(-1);
        }
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
