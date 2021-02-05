<template>
  <el-table
    :data="gatherTable"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">

    <el-table-column align="center" prop="companyName" label="账户名" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="收款金额CNY" show-overflow-tooltip/>
    <el-table-column align="center" prop="organCode" label="收款日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="收款备注" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="操作人" show-overflow-tooltip/>
  </el-table>
</template>

<script>
import { changeDate } from '@/utils/commenMethods.js'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  props: {
    gatherTable: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String,

  },
  data() {
    return {
      gatherTableData: [],
      gatherTotal: 0,
      gatherPageInfo: {
        page: 1,
        pageSize: 10
      },
      // detailInfo:{
      //   row:'',
      //   type:'',
      // },
    }
  },
  methods: {
    //删除
    delHandle(row) {

    },
    //查看
    viewDetail(row){
      // this.detailInfo.row = row;
      // this.detailInfo.type = 'edit';
     // this.$emit('openDetail',this.detailInfo);
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
