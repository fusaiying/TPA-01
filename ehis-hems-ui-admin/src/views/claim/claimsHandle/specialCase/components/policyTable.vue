<template>
  <el-table
      size="small"
      highlight-current-row
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-row>
            <el-col :span="6">
              <span class="expand_span">文档未列出需展示字段：{{props.row.code}}</span>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="goDetail(scope.row,'handle')">{{scope.row.policyNo}}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="source" label="被保险人姓名" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="name" label="险种名称" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="tasktype" label="保单状态" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="currentstatus" label="有效日期" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="chname" label="是否续保" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="surveytype" label="特约信息" show-overflow-tooltip></el-table-column>
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
    }
  },
  data() {
    return {
    }
  },
  methods: {
    goDetail(){
      this.$router.push({
        path :'/policy/policy-info',
        query:{}
      })
    }
  }
};
</script>
