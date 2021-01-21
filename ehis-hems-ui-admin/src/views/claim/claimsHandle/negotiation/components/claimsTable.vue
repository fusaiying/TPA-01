<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column width="200" align="center" prop="orderno" label="客服/工单号" show-overflow-tooltip/>
    <el-table-column :formatter="getMode" align="center" prop="negotiationtype" label="协谈类型" show-overflow-tooltip/>
    <el-table-column width="160" align="center" prop="claimno" label="赔案号" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button width="160" size="small" type="text" @click="detailHandle(scope.row,'show')">{{ scope.row.claimno }}</el-button>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="insuredidno" label="证件号码" show-overflow-tooltip/>
    <el-table-column align="center" prop="startdate" label="发起日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="claimcreatedate" label="受理日期" show-overflow-tooltip/>
    <el-table-column v-if="status === '03'" align="center" prop="dualdays" label="处理时效" show-overflow-tooltip/>
    <el-table-column :formatter="getSourceType" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column :formatter="getStatus" align="center" prop="status" label="赔案状态" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="status === '01' && scope.row.negotiationmode == '02'" size="small" type="text" @click="editHandle(scope.row,'handle')">协谈</el-button>
        <el-button v-else size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { changeDate } from '@/utils/commenMethods.js'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['negotiation_mode', 'queue_claim_status', 'apply_sourcetype', 'negotiation_type'],
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
    }
  },
  methods: {
    changeDate: changeDate,
    getMode(row) {
      return this.dict.label.negotiation_type[row.negotiationtype]
    },
    getStatus(row) {
      return this.dict.label.queue_claim_status[row.status]
    },
    getSourceType(row) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    // 处理跳转
    editHandle(row, status) {
      this.$emit('editHandle', { orderno: row.orderno, claimno: row.claimno, node: status, negotiationno: row.negotiationno })
    },
    detailHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          node: 'review'
        })
      )
      const newpage = this.$router.resolve({
        name: 'casedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // claimno: row.claimno,
      //     // status,
      //     // node: 'review'
      //   }
      // })
    }
  }
}
</script>
