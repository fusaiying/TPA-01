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
  </el-dialog>
</template>
<script>

  import {  historyDisInfo } from '@/api/negotiation/api'

  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo: Object,
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue;
        if(this.dialogVisable) {
          this.getHistoryDisInfo();
        }
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue;
        this.rptNo = this.fixInfoData.rptNo;
      },
    },
    data() {
      return {
        dialogVisable:false,
        HistoryData :[],
        fixInfoData : '',
        rptNo :'',
        clusionSelect:[],
      }
    },

    mounted() {

    },
    created: function() {

    },
    methods: {
      getHistoryDisInfo(){
        historyDisInfo(this.rptNo).then(res => {
          if(res.code == '200') {
            this.HistoryData = res.rows;
          }
        });
      },
      getConclusionName(value) {
        return this.selectDictLabel(this.clusionSelect,value)
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
