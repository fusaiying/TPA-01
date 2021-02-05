<template>
  <div>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column prop="riskCode" label="产品编码" align="center" show-overflow-tooltip/>
      <el-table-column prop="riskName" label="产品名称" align="center" show-overflow-tooltip />
      <el-table-column align="center" prop="riskNature" label="产品性质" show-overflow-tooltip/>
      <el-table-column align="center" prop="riskClass" label="产品分类" show-overflow-tooltip/>
      <el-table-column prop="synchronizeTime" label="同步日期" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.synchronizeTime|changeDate }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="updateTime" label="操作日期" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime|changeDate }}</span>
        </template>
      </el-table-column>
      <el-table-column  prop="riskStatus" label="规则状态" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{selectDictLabel(product_statusOptions, scope.row.riskStatus)}}</span>
        </template>
      </el-table-column>
      <el-table-column  prop="conclusionExplanation" align="center" label="结论说明" show-overflow-tooltip/>
      <el-table-column  prop="updateBy" align="center" label="操作人" show-overflow-tooltip/>
      <el-table-column align="center" label="操作" width="140">
        <template slot-scope="scope">
          <el-button v-if="status==='01'" size="mini" type="text" @click="revieweHandle(scope.row, 'ruleReview')">处理</el-button>
          <el-button v-else size="mini" type="text" @click="showHandle(scope.row,'show')">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import moment from 'moment'
  let dictss = [{dictType: 'product_status'}]
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
        dictList:[],
        product_statusOptions:[],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate
    },
    methods: {
      revieweHandle(row, node) {
        this.$router.push({
          path: 'rule-info',
          query: {
            node: node,
            riskCode: row.riskCode
          }
        })
      },
      showHandle(row, node) {
        this.$router.push({
          path: 'rule-info',
          query: {
            node: node,
            riskCode: row.riskCode
          }
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
