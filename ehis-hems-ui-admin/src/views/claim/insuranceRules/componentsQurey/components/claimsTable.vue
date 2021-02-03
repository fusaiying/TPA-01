<template>
  <el-table
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    :data="tableData"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style=" width: 100%;">
    <el-table-column align="center" width="140" prop="riskCode" label="产品编码" show-overflow-tooltip/>
    <el-table-column align="center" prop="riskName" label="产品名称" show-overflow-tooltip/>
    <el-table-column align="center" prop="riskNature" label="产品性质" show-overflow-tooltip/>
    <el-table-column align="center" prop="riskClass" label="产品分类" show-overflow-tooltip/>
    <el-table-column align="center" :formatter="getRiskStatus" prop="riskStatus" label="状态" show-overflow-tooltip/>
    <el-table-column  prop="examineConclusion" align="center" :formatter="getConclusion" label="当前结论" show-overflow-tooltip/>
    <el-table-column prop="synchronizeTime" label="同步日期" align="center" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ scope.row.synchronizeTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="updateTime" label="操作日期" align="center" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ scope.row.updateTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column  prop="updateBy" align="center" label="操作人" show-overflow-tooltip/>
    <el-table-column align="center" label="操作" width="140">
      <template slot-scope="scope">
        <el-button  size="mini" type="text" @click="linkDetail(scope.row, 'show')">详情</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import moment from 'moment'
  let dictss = [{dictType: 'product_status'},{dictType: 'approvalconclusion'}]
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
        dictList:[],
        product_statusOptions:[],
        approvalconclusionOptions:[],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate
      this.approvalconclusionOptions = this.dictList.find(item => {
        return item.dictType === 'approvalconclusion'
      }).dictDate
    },
    methods: {

      linkDetail(row, node) {
        this.$router.push({
          path: 'rule-info',
          query: {
            node: node,
            riskCode: row.riskCode
          }
        })
      },
      getRiskStatus(row){
        return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      },
      getConclusion(row){
        return this.selectDictLabel(this.approvalconclusionOptions, row.examineConclusion)
      }
    }
  }
</script>
<style scoped>
  .status-color{
    color: #E6A23C;
  }
</style>
