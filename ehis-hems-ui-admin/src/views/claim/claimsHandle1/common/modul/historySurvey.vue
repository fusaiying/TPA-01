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
      <form>
        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">调查序号：</span><span class="info_span">{{ surveyInfo.contractNo }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">调查类型 ：</span><span class="info_span">{{ (surveyInfo.servcomNo) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">调查状态：</span><span class="info_span">{{ surveyInfo.contractName }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">调查原因：</span><span class="info_span">{{ (surveyInfo.contractType) }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">交查人：</span><span class="info_span">{{ (surveyInfo.contracttermType) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">交查时间：</span><span class="info_span">{{ (surveyInfo.contractsort) }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
        <el-col :span="8">
            <span class="info_span to_right">调查项目：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">调查定性：：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">调查定性依据：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">事实与依据：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">结论：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">证明材料及件数：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">主管补充：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
              <span class="info_span to_right">是否有异地调查：</span><span class="info_span">{{ (surveyInfo.bussinessStatus) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">是否扫描：</span><span class="info_span">{{ surveyInfo.remark }}</span>
            </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">查讫时间：</span><span class="info_span">{{ (surveyInfo.bussinessStatus) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">调查机构：</span><span class="info_span">{{ surveyInfo.remark }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">调查人：</span><span class="info_span">{{ surveyInfo.remark }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">任务退回理由：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">结果退回理由：</span><span class="info_span">{{ surveyInfo.contractadvance }}</span>
          </el-col>
        </el-row>
      </form>

    </el-card>
    </el-dialog>
</template>
<script>

  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
    },
    data() {
      return {
        dialogVisable: false,
        surveyInfo: {

        },

      }
    },

    mounted() {
      this.getDetail();
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      getDetail(){

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
  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>
