<template>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" width="140" prop="insurancecode" label="产品编码" show-overflow-tooltip/>
      <el-table-column align="center" prop="insurancename" label="产品名称" show-overflow-tooltip/>
      <el-table-column align="center" prop="mainflag" label="产品性质" show-overflow-tooltip/>
      <el-table-column align="center" prop="insurancetype" label="产品分类" show-overflow-tooltip/>
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
      <el-table-column align="center" prop="status" label="规则状态" show-overflow-tooltip/>
      <el-table-column  prop="backReason" align="center" label="结论说明" show-overflow-tooltip/>
      <el-table-column v-if="status === '03'" prop="backReason" align="center" label="操作人" show-overflow-tooltip/>
      <el-table-column align="center" label="操作" width="140">
        <template slot-scope="scope">
          <el-button v-if="status==='03'" size="mini" type="text" @click="linkDetail(scope.row, 'show')">详情</el-button>
          <el-button v-if="status==='01'" size="mini" type="text" @click="linkRuleDefin(scope.row)">处理</el-button>
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
    },
    status: {
      type: String,
      default: function() {
        return ''
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
    // 处理跳转
    linkDetail(row, node) {
      this.$router.push({
        path: 'rule-info',
        query: {
          node: node,
          insurancecode: row.insurancecode
        }
      })
    },
    linkRuleDefin(row) {
      this.$router.push({
        path: 'rule-defin',
        query: { insurancecode: row.insurancecode }
      })
    }
  }
}
</script>
<style scoped>
  .status-color{
    color: #E6A23C;
  }
</style>
