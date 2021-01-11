<template>
  <div>
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column v-if="status==='01'" key="0" label="" width="65">
        <template scope="scope">
          <el-radio v-model="radio" :label="scope.row.id" @change.native="getBaseform(scope.row)">{{ &nbsp; }}</el-radio>
        </template>
      </el-table-column>
      <el-table-column key="1" align="center" prop="claimNo" :width="180" label="赔案号">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="detailHandle(scope.row,'show')">{{ scope.row.claimNo }}</el-button>
        </template>
      </el-table-column>
      <el-table-column key="2" :formatter="getApplicationsource" align="center" prop="applicationSource" label="申请来源" show-overflow-tooltip/>
      <el-table-column key="3" align="center" prop="insuredName" label="被保人姓名" show-overflow-tooltip/>
      <el-table-column key="4" align="center" prop="idcardNo" :width="220" label="证件号码"/>
      <!--<el-table-column align="center" prop="description" label="申请类别" :formatter="getapplicationcategorye"  show-overflow-tooltip></el-table-column>-->
      <el-table-column key="5" :formatter="formatApplicaReason" align="center" prop="description" label="申请类别" show-overflow-tooltip/>
      <el-table-column key="6" align="center" prop="eventDate" label="出险日期" show-overflow-tooltip/>
      <el-table-column key="7" align="center" prop="applicationDate" label="受理日期" show-overflow-tooltip/>
      <el-table-column key="8" align="center" prop="closedate" label="结案日期" show-overflow-tooltip/>
      <el-table-column v-if="status !== '01'" key="9" align="center" prop="approvalStatus" label="状态" show-overflow-tooltip/>
    </el-table>
  </div>
</template>

<script>
import { getMater } from '@/api/approval.js'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['apply_sourcetype', 'queue_claim_status', 'application_category'],
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    baseForm: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String
  },
  data() {
    return {
      showFlag: false,
      radio: ''
      // baseForm: baseForm
    }
  },
  watch: {
    tableData: {
      immediate: true,
      handler: function() {
        this.clearRadio()
      }
    },
    radio() {
      this.$emit('resetTable')
    }
  },
  methods: {
    getBaseform(row) {
      this.radio = row.id
      const params = {}
      params.claimno = row.claimNo || ''
      getMater(params).then(res => {
        if (res.status === '1') {
          this.$emit('getBaseform', [{ res: res.data, claimno: row.claimNo }])
          this.openTable()
        }
      })
    },
    // 申请类别
    formatApplicaReason(row) {
      let reason = ''
      if (row.description) {
        row.description.split(',').map((item, i) => {
          this.dict.label.application_category[item] ? reason += `|${this.dict.label.application_category[item]}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    openTable() {
      this.$emit('openTable')
    },
    clearRadio() {
      this.radio = ''
      this.$emit('resetTable')
    },
    // 申请来源
    getApplicationsource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationSource]
    },
    // 案件状态
    getClaimstatus(row, col) {
      return this.dict.label.queue_claim_status[row.status]
    },
    // 申请类别
    getapplicationcategorye(row, col) {
      return this.dict.label.application_category[row.description]
    },
    detailHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimNo),
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
      //     // claimno: row.claimNo,
      //     // status,
      //     // node: 'review'
      //   }
      // })
    }
  }
}
</script>
