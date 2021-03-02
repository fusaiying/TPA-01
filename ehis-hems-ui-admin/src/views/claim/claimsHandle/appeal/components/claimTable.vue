<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    v-loading="loading"
    style="width: 100%;">
    <el-table-column align="center" prop="receiptCompanyName" label="报案号" show-overflow-tooltip/>
    <el-table-column align="center" prop="receiptAmount" label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="receiptDate" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="remark" label="证件号码" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="理赔类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="承保机构" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="赔付金额" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="审核人" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="结案日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="操作" show-overflow-tooltip/>
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
  },
  watch: {
    tableData:function(newValue) {
      this.loading = false;
    },
  },
  data() {
    return {
      loading:true,
      radio:false,
    }
  },
  methods: {
    // getCurrentRow(row){//获取当前行的数据
    //   this.$emit('radioVue',row.collectionId);
    // }
  }
}
</script>
