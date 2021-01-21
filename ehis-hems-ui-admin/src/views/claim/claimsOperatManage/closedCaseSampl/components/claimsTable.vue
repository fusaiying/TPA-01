<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" prop="taskno" label="险种名称" show-overflow-tooltip/>
    <el-table-column align="center" prop="source" label="给付责任" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="赔付金额" show-overflow-tooltip/>
    <el-table-column align="center" prop="tasktype" label="赔付结论" show-overflow-tooltip/>
    <el-table-column align="center" prop="currentstatus" label="结论说明" show-overflow-tooltip/>
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
    status: String
  },
  data() {
    return {
    }
  },
  methods: {
    // 处理跳转
    editHandle(row, status) {
      this.$router.push({
        path: '/claims-handle/com-handle',
        query: {
          status,
          node: 'spotCheck'
        }
      })
    }
  }
}
</script>
