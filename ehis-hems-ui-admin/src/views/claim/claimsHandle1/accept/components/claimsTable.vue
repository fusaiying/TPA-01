<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column sortable key="1" align="center" prop="claimno" min-width="150" label="批次号" show-overflow-tooltip/>
    <el-table-column key="2" align="center" min-width="100" prop="applicationsource" label="报案号" show-overflow-tooltip/>
    <el-table-column key="3" align="center" prop="name" label="是否直结" min-width="120" show-overflow-tooltip/>
    <el-table-column key="4" align="center" prop="idcardno" min-width="160" label="案件状态" show-overflow-tooltip/>
    <el-table-column v-if="status==='01'" key="5" align="center" prop="eventdate" label="停留时长" min-width="120" show-overflow-tooltip/>
    <el-table-column key="6" align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'edit')">处理</el-button>
        <el-button v-if="status==='02' || status==='03' " size="small" type="text" @click="editHandle(scope.row,'show')">
          查看
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

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
      }
    },
    mounted() {

    },
    methods: {
      // 处理跳转
      editHandle(row, status) {
        let data = encodeURI(
          JSON.stringify({
            claimno: encrypt(row.claimno),
            status,
            applySource: row.applicationsource,
            node: 'accept',
            styleFlag: 'list',
          })
        )
        this.$router.push({
          path: '/claims-handle/com-handle',
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
