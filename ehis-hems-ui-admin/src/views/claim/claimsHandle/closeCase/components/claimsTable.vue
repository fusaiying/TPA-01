<template>
  <el-table
    size="small"
    highlight-current-row
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" prop="source" label="赔案号" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="name" label="申请来源" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="tasktype" label="被保人姓名" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="tasktype" label="申请原因" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="currentstatus" label="出险日期" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="currentstatus" label="受理日期" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="currentstatus" label="案件状态" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="currentstatus" label="退件清单" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" prop="currentstatus" label="结案日期" show-overflow-tooltip></el-table-column>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button  v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
        <el-button  v-if="status==='03'" size="small" type="text" @click="editHandle(scope.row,'show')">详情</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  props: {
    tableData: {
      type: Array,
        default:function () {
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
    //处理跳转
    editHandle(row, status) {
      // this.$router.push({
      //   path :'/claims-handle/return-edit',
      //   query: {
      //     status
      //   }
      // })
      const newpage = this.$router.resolve({
        name: 'returndetail',
        params:{},
        query:{ status }
      })
      window.open(newpage.href, '_blank');
    }
  }
};
</script>
