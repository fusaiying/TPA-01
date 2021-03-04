<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    v-loading="gatherSearchLoad"
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column label="" width="40">
      <template slot-scope="scope">
        <el-radio :label="scope.$index" v-model="radio" @change.native="getCurrentRow(scope.row)" style="color: #fff;padding-left: 10px; margin-right: -25px;"></el-radio>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="receiptAccName" label="账户名" show-overflow-tooltip/>
    <el-table-column align="center" prop="receiptAmount" label="收款金额CNY" show-overflow-tooltip/>
    <el-table-column align="center" prop="receiptDate" label="收款日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="remark" label="收款备注" show-overflow-tooltip/>
    <el-table-column align="center" prop="updateBy" label="操作人" show-overflow-tooltip/>
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
  watch:{
    tableData:function(newValue) {
      this.gatherSearchLoad = false;
    }
  },
  data() {
    return {
      gatherSearchLoad:true,
    }
  },
  methods: {
    //删除
    delHandle(row) {

    },
    getCurrentRow(row){//获取当前行的数据
      this.$emit('radioVue',row.collectionId);
    }
  }
}
</script>
