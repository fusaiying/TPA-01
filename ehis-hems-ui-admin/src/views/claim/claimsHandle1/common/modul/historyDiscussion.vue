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
        <span>历史协谈信息</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
      </div>
      <form>
        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">协谈序号：</span><span class="info_span">{{ surveyInfo.contractNo }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">协谈处理时间 ：</span><span class="info_span">{{ (surveyInfo.servcomNo) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">协谈结论：</span><span class="info_span">{{ surveyInfo.contractName }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">协谈人：</span><span class="info_span">{{ (surveyInfo.contractType) }}</span>
          </el-col>
        </el-row>

        <el-row  style="margin: 0 40px;">
          <el-col :span="8">
            <span class="info_span to_right">协谈意见：</span><span class="info_span">{{ (surveyInfo.contracttermType) }}</span>
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
        dialogVisable:false,
        surveyInfo: {

        },
      }
    },

    mounted() {
      this.getDetail();
    },
    methods: {
      getDetail(){

      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeHistoryDiscussionDialog')
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
