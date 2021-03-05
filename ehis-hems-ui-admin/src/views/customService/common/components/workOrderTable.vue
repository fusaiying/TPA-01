<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    @expand-change="getMinData"
    style="width: 100%;">
    <el-table-column type="selection" align="center" content="全选"/>
    <el-table-column prop="rptno" label="工单号" align="center"/>
    <el-table-column prop="rptno" label="受理渠道" align="center"/>
    <el-table-column prop="rptno" label="服务项目" align="center"/>
    <el-table-column prop="rptno" label="保单号" align="center"/>
    <el-table-column prop="rptno" label="分单号" align="center"/>
    <el-table-column prop="rptno" label="险种代码" align="center"/>
    <el-table-column prop="rptno" label="被保人" align="center"/>
    <el-table-column prop="rptno" label="投保人" align="center"/>
    <el-table-column align="center" prop="updateTime" min-width="120" label="受理时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="updateTime" min-width="120" label="修改时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="rptno" label="受理人" align="center"/>
    <el-table-column prop="rptno" label="处理人" align="center"/>
    <el-table-column prop="status" label="VIP标识" align="center"/><!--status待确认-->
    <el-table-column prop="updateTime" label="优先级" align="center"/>
    <el-table-column prop="updateBy" label="出单机构" align="center"/>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='01'" size="small" type="text" @click="editPresenting(scope.row,'get')">获取
        </el-button>
        <el-button v-if="status==='01'" size="small" type="text" @click="editPresenting(scope.row,'edit')">修改
        </el-button>
        <el-button v-if="status==='01'" size="small" type="text" @click="editPresenting(scope.row,'cancel')">取消
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {getMinData} from '@/api/claim/presentingReview'
import {encrypt} from "@/utils/rsaEncrypt"


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
      loading: true
    }
  },
  mounted() {

  },
  methods: {
    // 处理跳转
    editPresenting(row, status) {
      let data = encodeURI(
        JSON.stringify({
          batchno: row.batchno, //批次号
          status,//新增or查看
          claimtype: row.claimtype//理赔类型
        })
      )
      this.$router.push({
        path: '/claims-handle/presenting-edit',
        query: {
          data
        }
      })
    },
    //条码打印
    codePrint() {

    },
    getMinData(row, expandedRows) {
      this.loading = true
      //判断只有展开是做请求
      if (expandedRows.length > 0) {
        getMinData(row.batchno).then(res => {
          this.tableData.forEach((temp, index) => {
            if (temp.batchno === row.batchno) {
              this.tableData[index].minData = res.rows
            }
          })
          this.loading = false
        })
      }
    }
  }
}
</script>
<style scoped>

.el-table /deep/ .el-table__expanded-cell {
  padding: 10px;
}
</style>

