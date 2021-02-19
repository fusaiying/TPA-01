<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" prop="problemType" :formatter="getProblemTypeName"  label="问题件类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="caseStatus" :formatter="getClaimStatusName" label="案件状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="rptNo" label="报案号" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button width="160" size="small" type="text" @click="viewHandle(scope.row,'show')">{{ scope.row.rptNo }}</el-button>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center"  v-if="status === '01'" prop="stopTime" label="停留时长" show-overflow-tooltip/>
    <el-table-column align="center" prop="source"  :formatter="getDeliverySourceName" label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="提交用户" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="status === '01' " size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
        <el-button v-else size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

export default {
  dicts: ['negotiation_type'],
  props: {
    status:String,

    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    problemTypes: {
      type: Array,
      default: function() {
        return []
      }
    },

    claimStatusSelect: {
      type: Array,
      default: function() {
        return []
      }
    },

    deliverySource: {
      type: Array,
      default: function() {
        return []
      }
    },
  },
  data() {
    return {
    }
  },
  methods: {
    getProblemTypeName(row,col) {
      return this.selectDictLabel(this.problemTypes, row.problemType)
    },
    getDeliverySourceName(row,col) {
      return this.selectDictLabel(this.deliverySource, row.source)
    },
    getClaimStatusName(row,col) {
      return this.selectDictLabel(this.claimStatusSelect, row.caseStatus)
    },

    // 处理跳转
    editHandle(row, type) {
      console.log(row)
      this.$router.push({
        path: '/claims-handle/pbClaimDetail',
        query: {type:type,rptNo: row.rptNo,problemId:row.problemId}

      })
    },
    // 处理跳转
    viewHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          batchNo: row.batchNo,
          claimType: row.claimType,
          rptNo: row.rptNo,
          status,
          node: 'accept',
          styleFlag: 'list',
        })
      )
      this.$router.push({
        path: '/claims-handle/accept-process',
        query: {
          data
        }
      })
    },
  }
}
</script>
