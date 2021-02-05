<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column sortable key="1" align="center" prop="batchNo" min-width="150" label="批次号" show-overflow-tooltip/>
    <el-table-column key="2" align="center" min-width="100" prop="rptNo" label="报案号" show-overflow-tooltip/>
    <el-table-column key="3" align="center" prop="claimType" label="是否直结" min-width="120" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(sys_yes_noOptions, scope.row.claimType)}}</span>
      </template>
    </el-table-column>
    <el-table-column key="4" align="center" prop="caseStatus" min-width="160" label="案件状态" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(claim_statusOptions, scope.row.caseStatus)}}</span>
      </template>
    </el-table-column>
    <el-table-column v-if="status==='01'" key="5" align="center" prop="stayTime" label="停留时长" min-width="120"
                     show-overflow-tooltip/>
    <el-table-column key="6" align="center" label="操作" min-width="94" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'edit')">处理</el-button>
        <el-button v-if="status==='02' || status==='03' " size="small" type="text"
                   @click="editHandle(scope.row,'show')">
          查看
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  let dictss = [{dictType: 'claim_status'},{dictType: 'sys_yes_no'},]
  export default {
    props: {
      tableData: {
        type: Array,
        default: function () {
          return []
        }
      },
      status: String,
    },
    data() {
      return {
        dictList: [],
        claim_statusOptions: [],
        sys_yes_noOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claim_statusOptions = this.dictList.find(item => {
        return item.dictType === 'claim_status'
      }).dictDate
      this.sys_yes_noOptions = this.dictList.find(item => {
        return item.dictType === 'sys_yes_no'
      }).dictDate

    },
    methods: {
      // 处理跳转
      editHandle(row, status) {
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
<style scoped>
  .mycell .el-checkbox__input {
    display: none;
  }
</style>
