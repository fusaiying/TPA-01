<template>
  <div>
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" prop="taskno" width="140" label="赔案号" show-overflow-tooltip/>
      <el-table-column align="center" prop="source" label="操作类型" show-overflow-tooltip/>
      <el-table-column align="center" prop="source" label="申请来源" show-overflow-tooltip/>
      <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
      <el-table-column align="center" prop="name" label="证件号码" show-overflow-tooltip/>
      <el-table-column align="center" prop="name" label="申请类别" show-overflow-tooltip/>
      <el-table-column align="center" prop="currentstatus" label="出险日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="chname" label="受理日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="surveytype" label="赔案状态" show-overflow-tooltip/>
      <el-table-column align="center" prop="initiatedate" label="赔付金额" show-overflow-tooltip/>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="editHandle(scope.row,'show')">处 理</el-button>
        </template>
      </el-table-column>
    </el-table>
    <policy-modul v-model="policyVis" :node="node" :policy-info="policyInfo"/>
  </div>
</template>

<script>
import policyModul from '../../common/modul/policyHandle'
export default {
  components: { policyModul },
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
      policyVis: false,
      policyInfo: {},
      node: 'policy'
    }
  },
  methods: {
    editHandle(row, status) {
      this.policyInfo = row // 具体数据按照接口传
      this.policyVis = true
    }
  }
}
</script>
