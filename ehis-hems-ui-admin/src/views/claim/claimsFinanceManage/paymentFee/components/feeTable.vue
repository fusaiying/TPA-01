<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    v-loading="dataSearchLoad"
    @selection-change="handleSelectionChange"
    style="width: 100%;">

    <el-table-column v-if="status == '03'" type="selection" width="50" align="center"/>
    <el-table-column align="center" prop="settleTaskNo" label="任务号" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="sumPayAmount" label="任务总金额CNY" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="createTime" label="建立日期" show-overflow-tooltip>
      <template slot-scope="scope">
        <span >{{ scope.row.createTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="settleStatus" label="付款状态" :formatter="getStatusName" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="settleEndDate" label="结算日期" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="colDate" label="核销日期" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="scope.row.settleStatus != '02'" size="small" type="text" @click="viewDetail(scope.row,'show')">查看</el-button>
        <el-button v-if="scope.row.settleStatus == '02'" size="small" type="text" @click="viewDetail(scope.row,'confirm')">确认</el-button>
        <el-button v-if="scope.row.settleStatus != '03'" size="small" type="text" @click="delHandle(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

  import moment from 'moment'

export default {
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String,

  },
  watch: {
    tableData:function(newValue) {
      console.log("*****333333333333*******************")
      console.log("********333333****************")
      console.log(newValue)
      console.log("**********33333333**************")
      console.log("**********3333333**************")
      this.dataSearchLoad = false;
    },
  },
  data() {
    return {
      dataSearchLoad: true,
      detailDialog : true,
      settleTaskNos:'',
      detailInfo:{
        row:'',
        type:'',
      },
      settleStatus:[],
    }
  },
  mounted(){
    //结算状态
    this.getDicts("tpa_settle_status").then(response => {
      this.settleStatus = response.data;
    });
  },
  methods: {
    //删除
    delHandle(row) {
    },
    //查看
    viewDetail(row,type){
      this.detailInfo.row = row;
      this.detailInfo.type = type;
      this.$emit('openDetail',this.detailInfo);
      this.detailDialog = true;
    }
    ,getStatusName(row,col){
      return this.selectDictLabel(this.settleStatus, row.settleStatus)
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.settleTaskNos = (selection.map(item => item.settleTaskNo)); //rptNo
      // this.single = selection.length != 1;
      // this.multiple = !selection.length;
    },
  }
}
</script>
