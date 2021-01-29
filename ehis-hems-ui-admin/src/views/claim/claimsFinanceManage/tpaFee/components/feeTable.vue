<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">

    <el-table-column align="center" prop="companyName" label="任务号" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="organCode" label="结算类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="结算总金额CNY" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="建立日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="结算日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="结算状态" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="viewDetail(scope.row)">查看</el-button>
        <el-button size="small" type="text" @click="delHandle(scope.row)">处理</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { changeDate } from '@/utils/commenMethods.js'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String,

  },
  data() {
    return {
      detailDialog : true,
      detailInfo:{
        row:'',
        type:'',
      },
    }
  },
  methods: {
    //删除
    delHandle(row) {

    },
    //查看
    viewDetail(row){
      this.detailInfo.row = row;
      this.detailInfo.type = 'edit';
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
  }
}
</script>
