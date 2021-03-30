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
    <el-table-column align="center" prop="companyCode" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="settlementType" label="结算类型" :formatter="getSettlementName" show-overflow-tooltip/>
    <el-table-column align="center" prop="serviceSettleAmount" label="结算总金额CNY" show-overflow-tooltip/>
    <el-table-column align="center" prop="createTime" label="建立日期" show-overflow-tooltip>
      <template slot-scope="scope">
        <span >{{ scope.row.createTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="settleEndDate" label="结算日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="settleStatus" label="结算状态" :formatter="getStatusName" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="status != '02'" size="small" type="text" @click="viewDetail(scope.row,'show')">查看</el-button>
        <el-button v-if="status == '02'" size="small" type="text" @click="viewDetail(scope.row,'confirm')">确认</el-button>
        <el-button v-if="status != '03'" size="small" type="text" @click="delFun(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>


import moment from "moment";

import {updateTask} from '@/api/tpaFee/api'

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
    loading:Boolean,
  },
  watch: {
    status: function (newValue) {
      this.dataType = newValue;
    },
    loading: function (newVal){
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
      settlementTypeSelect:[],
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
    delFun(row) {
      // 02-待结算  该任务为待结算状态，不允许进行删除操作，请核实
      // 03-已结算  该任务为已结算状态，不允许进行删除操作，请核实
      if(row.settleStatus === '02') {
        this.$message.warning('该任务为待结算状态，不允许进行删除操作，请核实');
        return false;
      }
      if(row.settleStatus === '03') {
        this.$message.warning('该任务为已结算状态，不允许进行删除操作，请核实');
        return false;
      }
      const params = {};
      params.settleTaskNo =  row.settleTaskNo;
      this.$confirm('确定删除', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        updateTask(params).then(response => {
          if(response.code == '200') {
            this.$emit('initData');
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          } else {
            this.$message({
              type: 'info',
              message: '删除失败'
            });
          }

        }).catch(error => {
          console.log(error);
        })
      }).catch(() => {
      })
    },
    //查看
    viewDetail(row,type){
      // this.detailInfo.row = row;
      // this.detailInfo.type = type;
      // this.$emit('openDetail',this.detailInfo);
      // this.detailDialog = true;
      this.detailInfo.row = row;
      this.detailInfo.type = type;
      this.$emit('openDetail',this.detailInfo);
      this.detailDialog = true;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.settleTaskNos = (selection.map(item => item.settleTaskNo));
      this.$emit('checkBoxVue',this.settleTaskNos);
    }
    ,getStatusName(row,col){
      return this.selectDictLabel(this.settleStatus, row.settleStatus)
    }
    ,getSettlementName(row,col){
      return this.selectDictLabel(this.settlementTypeSelect, row.settlementType)
    }
  }

}
</script>
