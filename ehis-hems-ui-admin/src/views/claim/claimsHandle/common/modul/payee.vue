<template>
  <el-dialog
    :visible.sync="dialogVis"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="申请人信息"
    width="76%">
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" property="date" label="领款方式" width="150"/>
      <el-table-column align="center" property="name" label="与被保人关系" width="200"/>
      <el-table-column align="center" property="address" label="领款人"/>
      <el-table-column align="center" property="address" label="分配比例(%)"/>
      <el-table-column align="center" property="address" label="金额"/>
    </el-table>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="dialogClosed">关 闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    value: Boolean,
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    } // 上个页面带过来的值
  },
  data() {
    return {}
  },
  computed: {
    dialogVis: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
    }
  }
}
</script>
<style scoped>
  .el-row /deep/ .el-col-8 {
    height: 40px;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  span {
    color: #555;
    line-height: 2.5;
  }
</style>
