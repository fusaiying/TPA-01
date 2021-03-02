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
        <div class="div_b" style="margin-bottom: 20px">
          <el-row style="margin: 10px 10px;">
            <el-col :span="8">
              <span class="info_span to_right">协谈序号：</span><span class="info_span">{{ (index +1) * 2 -1 }}-下发</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈处理时间 ：</span><span class="info_span">{{ item.createTime | changeDate }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈类型：</span><span class="info_span">{{ getNegotiationTypesName(item.discType) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈人：</span><span class="info_span">{{ (item.createBy) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">转出意见：</span><span class="info_span">{{ (item.disView) }}</span>
            </el-col>
          </el-row>

          <el-row style="margin: 10px 10px;" v-if="item.conclusion != ''">
            <el-col :span="8">
              <span class="info_span to_right">协谈序号：</span><span class="info_span">{{ (index +1) * 2 }}-回调</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈处理时间 ：</span><span class="info_span">{{ item.updateTime | changeDate }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈结论：</span><span class="info_span">{{ getConclusionName(item.conclusion) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈人：</span><span class="info_span">{{ (item.updateBy) }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span to_right">协谈意见：</span><span class="info_span">{{ (item.conclusionView) }}</span>
            </el-col>
          </el-row>
        </div>
      </form>
    </el-card>
  </el-dialog>
</template>
<script>

  import moment from 'moment'
  import {  historyDisInfo } from '@/api/negotiation/api'

  export default {
    filters: {
      changeDate: function(value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo: Object,
      preHistoryData : {
        type: Array,
        default: []
      },
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue;
        if(this.dialogVisable) {
         // this.getHistoryDisInfo();
        }
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue;
        this.rptNo = this.fixInfoData.rptNo;
      },
      preHistoryData: function (newValue) {
        this.HistoryData = newValue;
      },
    },
    data() {
      return {
        dialogVisable:false,
        HistoryData :[],
        fixInfoData : '',
        rptNo :'',
        clusionSelect:[],
        negotiationTypes:[],
      }
    },

    mounted() {
      this.getDicts("handleconclusion").then(response => {
        this.clusionSelect = response.data;
      });
      //协谈类型
      this.getDicts("negotiation_type").then(response => {
        this.negotiationTypes = response.data;
      });
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
      getNegotiationTypesName(value) {
        return this.selectDictLabel(this.negotiationTypes,value)
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
  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
  .div_b{border:1px solid #E6EBF5}
</style>
