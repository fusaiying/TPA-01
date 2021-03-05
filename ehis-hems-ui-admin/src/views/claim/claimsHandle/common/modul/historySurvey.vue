<template>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="80%" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>历史调查信息</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
      </div>
      <form  v-for="(item,index) in HistoryData">
        <div class="div_b" style="margin-bottom: 20px">
          <el-row style="margin: 0px 10px;">
            <el-col :span="8">
              <span class="info_span to_right">调查序号：</span><span class="info_span">{{ (index +1)}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">调查类型 ：</span><span class="info_span">{{ getdispatchTypeName(item.invDetailType) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">调查状态：</span><span class="info_span">{{ item.invDetailStatus }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">调查原因：</span><span class="info_span">{{ getInitiateReasonName(item.invDetailCause) }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="8">
              <span class="info_span to_right">交查人：</span><span class="info_span">{{ (item.invDetailPeople) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">交查时间：</span><span class="info_span">{{ (item.invDetailTime) }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">调查项目：</span><span class="info_span">{{ item.invDetailItem }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">调查定性：：</span><span class="info_span">{{ item.invDetailNature }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">调查定性依据：</span><span class="info_span">{{ item.invDetailGist }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">事实与依据：</span><span class="info_span">{{ item.invDetailFace }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">结论：</span><span class="info_span">{{ item.conclusion }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">证明材料及件数：</span><span class="info_span">{{ item.invDetailNum }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">主管补充：</span><span class="info_span">{{ item.invDetailReplenish }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="8">
                <span class="info_span to_right">是否有异地调查：</span><span class="info_span">{{ (item.invDetailSurvey) }}</span>
              </el-col>
              <el-col :span="8">
                <span class="info_span to_right">是否扫描：</span><span class="info_span">{{ item.invDetailScan }}</span>
              </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="8">
              <span class="info_span to_right">查讫时间：</span><span class="info_span">{{ (item.invDetailAfterTime) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">调查机构：</span><span class="info_span">{{ getInquiryOrgName(item.invDetailOrgan) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">调查人：</span><span class="info_span">{{ getInitiateOrgName(item.invDetailAfterPeople) }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">任务退回理由：</span><span class="info_span">{{ item.invDetailArgument }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 0px 10px;">
            <el-col :span="24">
              <span class="info_span to_right">结果退回理由：</span><span class="info_span">{{ item.invDetailResultArgument }}</span>
            </el-col>
          </el-row>
        </div>
      </form>

    </el-card>
    </el-dialog>
</template>
<script>

let dictss = [{dictType: 'dispatch_type'}, {dictType: 'initiate_reasons'}, {dictType: 'inquiry_org'}, {dictType: 'initiate_org'}]
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      preSurveyHistoryData : {
        type: Array,
        default: []
      },
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
      preSurveyHistoryData: function (newValue) {
        this.HistoryData = newValue;
      },
    },
    data() {
      return {
        //提调类型
        dispatchTypes:[],
        //提调原因
        initiateReasons:[],
        //调查机构
        inquiryOrg:[],
        //提调机构
        initiateOrg:[],
        dialogVisable: false,
        HistoryData :[],
        surveyInfo: {

        },

      }
    },
    async  mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
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

    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      getdispatchTypeName(value) {
        return this.selectDictLabel(this.dispatchTypes,value)
      },
      getInitiateReasonName(value) {
        return this.selectDictLabel(this.initiateReasons,value)
      },
      getInquiryOrgName(value) {
        return this.selectDictLabel(this.inquiryOrg,value)
      },
      getInitiateOrgName(value) {
        return this.selectDictLabel(this.initiateOrg,value)
      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeHistorySurveyDialog')
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
  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
  .div_b{border:1px solid #E6EBF5}
</style>
