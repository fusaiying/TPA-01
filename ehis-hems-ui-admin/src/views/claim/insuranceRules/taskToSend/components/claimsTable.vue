<template>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style=" width: 100%;"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center"/>
      <el-table-column align="center" width="140" prop="insurancecode" label="产品编码" show-overflow-tooltip/>
      <el-table-column align="center" prop="insurancename" label="产品名称" show-overflow-tooltip/>
      <el-table-column align="center" prop="mainflag" label="产品性质" show-overflow-tooltip/>
      <el-table-column align="center" prop="insurancetype" label="产品分类" show-overflow-tooltip/>
      <el-table-column align="center" prop="status" label="状态" show-overflow-tooltip/>
      <el-table-column  prop="backReason" align="center" label="当前结论" show-overflow-tooltip/>
      <el-table-column prop="startdate" label="同步日期" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.startdate | changeDate}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="enddate" label="操作日期" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.enddate | changeDate}}</span>
        </template>
      </el-table-column>
      <el-table-column  prop="backReason" align="center" label="操作人" show-overflow-tooltip/>
      <el-table-column align="center" label="操作" width="140">
        <template slot-scope="scope">
          <el-button  size="mini" type="text" @click="sendOne">改派</el-button>
        </template>
      </el-table-column>
    </el-table>
</template>

<script>
import moment from 'moment'
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
    }
  },
  data() {
    return {
    }
  },
  mounted() {

  },
  methods: {
    handleSelectionChange(){

    },
    sendOne(){

    }
  }
}
</script>
<style scoped>
  .status-color{
    color: #E6A23C;
  }
</style>
