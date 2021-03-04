<template>
  <el-table
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    :data="tableData"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" width="140" prop="riskCode" label="产品编码" show-overflow-tooltip/>
    <el-table-column align="center" prop="riskName" label="产品名称" show-overflow-tooltip/>
    <el-table-column align="center" prop="riskNature" label="产品性质" show-overflow-tooltip/>
    <el-table-column align="center" prop="riskClass" label="产品分类" show-overflow-tooltip/>
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
    <el-table-column align="center" :formatter="getRiskStatus" prop="riskStatus" label="规则状态" show-overflow-tooltip/>
    <el-table-column  prop="conclusionExplanation" align="center" label="结论说明" show-overflow-tooltip/>
    <el-table-column v-if="status === '02'" prop="updateBy" align="center" label="操作人" show-overflow-tooltip/>
    <el-table-column align="center" label="操作" width="140">
      <template slot-scope="scope">
        <el-button v-if="status==='02'" size="mini" type="text" @click="linkDetail(scope.row, 'show')">详情</el-button>
        <el-button v-if="scope.row.riskStatus==='04'" size="mini" type="text" @click="resetInfo(scope.row,scope.$index)">重设</el-button>
        <el-button v-if="status==='01'" size="mini" type="text" @click="linkRuleDefin(scope.row)">处理</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>


<script>
  import moment from 'moment'
  import {resetInfo} from '@/api/insuranceRules/ruleDefin'
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
        product_statusOptions:[],
        dictList:[],
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
      // 处理跳转
      linkDetail(row, node) {
        this.$router.push({
          path: 'rule-info',
          query: {
            node: node,
            riskCode: row.riskCode
          }
        })
      },
      linkRuleDefin(row) {
        this.$router.push({
          path: 'rule-defin',
          query: { riskCode: row.riskCode }
        })
      },
      getRiskStatus(row){
        return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      },
      resetInfo(row,index){
        this.$confirm(`是否确定重设?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          resetInfo(row.riskCode).then(res=>{
            if (res!=null && res.code===200){
              this.$message({
                message: '重设成功！',
                type: 'success',
                center: true,
                showClose: true
              })
            }
            this.$emit("refresh-item", 200)
          }).catch(res=>{
            this.$message.error(
              "重设失败!"
            );
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
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
