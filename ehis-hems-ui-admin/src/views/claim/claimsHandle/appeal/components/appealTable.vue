<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" prop="discType" label="报案号" show-overflow-tooltip/>
    <el-table-column width="200" align="center" prop="caseStatus"  label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="organCode" label="证件号码" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="理赔类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="承保机构" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="赔付金额" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="审核人" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="申诉状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="操作日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="操作人" show-overflow-tooltip/>
    <el-table-column align="center"  v-if="status === '03'" prop="monitoringTime" label="修正理赔号" show-overflow-tooltip/>-->
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
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
  },
  data() {
    return {
    }
  },
  methods: {
  }
}
</script>
