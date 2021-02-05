<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    v-loading="dataSearchLoad"
    style="width: 100%;">

    <el-table-column align="center" prop="settleTaskNo" label="任务号" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyCode" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="settlementType" label="结算类型" :formatter="getSettlementName" show-overflow-tooltip/>
    <el-table-column align="center" prop="serviceSettleAmount" label="结算总金额CNY" show-overflow-tooltip/>
    <el-table-column align="center" prop="createTime" label="建立日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="settleEndDate" label="结算日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="settleStatus" label="结算状态" :formatter="getStatusName" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="status != '02'" size="small" type="text" @click="viewDetail(scope.row,'show')">查看</el-button>
        <el-button v-if="status == '02'" size="small" type="text" @click="viewDetail(scope.row,'confirm')">确认</el-button>
        <el-button v-if="status != '03'" size="small" type="text" @click="delHandle(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>


export default {
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String,
    loading:Boolean,
  },
  watch: {
    status: function (newValue) {
      console.log("************************")
      console.log("************************")
      console.log(newValue)
      console.log("************************")
      console.log("************************")

      this.dataType = newValue;
    },
    loading: function (newVal){
      console.log("********444444444****************")
      console.log(newVal)
      console.log(newVal)
      console.log(newVal)
      console.log(newVal)
      console.log(newVal)
      console.log("*******4444444444444444444*****************")
    },
    tableData:function(newValue) {
      this.dataSearchLoad = false;
    }
  },
  data() {
    return {
      dataSearchLoad:true,
      dataType:'',
      detailDialog : true,
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
    //结算类型
    this.getDicts("accounttype").then(response => {
      this.settlementTypeSelect = response.data;
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
    // detailHandle(row, status) {
    //   let data = encodeURI(
    //     JSON.stringify({
    //       claimno: 'xxx',
    //       node: 'review'
    //     })
    //   )
    //   const newpage = this.$router.resolve({
    //     name: 'casedetail',
    //     params:{},
    //     query:{ data }
    //   })
    //   window.open(newpage.href, '_blank');
    // }
    ,getStatusName(row,col){
      return this.selectDictLabel(this.settleStatus, row.settleStatus)
    }
    ,getSettlementName(row,col){
      return this.selectDictLabel(this.settlementTypeSelect, row.settlementType)
    }
  }

}
</script>
