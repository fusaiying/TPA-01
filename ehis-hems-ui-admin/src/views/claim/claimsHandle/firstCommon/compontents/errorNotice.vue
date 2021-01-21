<template>
  <el-card>
    <div slot="header" class="clearfix">
      <span>错误提示</span>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="操作"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="错误一级"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="错误二级">
      </el-table-column>
      <el-table-column
        prop="address"
        label="错误描述">
      </el-table-column>
      <el-table-column
        prop="address"
        label="备注">
      </el-table-column>
    </el-table>
  </el-card>
</template>
<script>
export default {
  data () {
    return {
      tableData: []
    }
  }
}
</script>
<style scoped>

</style>