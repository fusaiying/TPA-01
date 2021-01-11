<template>
  <div>
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column width="160" align="center" prop="claimno" label="赔案号" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button width="160" size="small" type="text" @click="detailHandle(scope.row,'show')">{{ scope.row.claimno }}</el-button>
        </template>
      </el-table-column>
      <el-table-column :formatter="getApplicationsource" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
      <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
      <el-table-column :formatter="formatApplicaReason" align="center" prop="description" label="申请类别" show-overflow-tooltip/>
      <el-table-column align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="applicationdate" label="受理日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="payableamount" label="赔付金额" show-overflow-tooltip/>
      <el-table-column :formatter="getSpecialStatus" align="center" prop="status" label="案件状态" show-overflow-tooltip/>
      <el-table-column :formatter="getCaseState" align="center" prop="ruletype" label="报备类型" show-overflow-tooltip/>
      <el-table-column align="center" prop="createdate" label="报备时间" show-overflow-tooltip/>
      <el-table-column align="center" label="操作" width="80">
        <template slot-scope="scope">
          <el-button v-if="status==='01'" style="color: #409EFF;" size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
          <el-button v-if="status==='03'" style="color: #409EFF;" size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <excess-modul ref="excessmodul" v-model="dialogVisable" :show-flag="showFlag" :claim-no="claimNo" :specialreport-no="specialreportNo" v-on="$listeners"/>
  </div>
</template>

<script>
import excessModul from '../components/excessModul'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['apply_sourcetype', 'claim_status', 'case_state', 'application_category', 'special_rules'],
  components: {
    excessModul
  },
  inheritAttrs: false,
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
      dialogVisable: false,
      showFlag: false,
      claimNo: '',
      specialreportNo: ''
    }
  },
  methods: {
    // 获得申请来源
    getApplicationsource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    // 获得案件状态
    getSpecialStatus(row, col) {
      return this.dict.label.claim_status[row.status]
    },
    getCaseState(row) {
      return this.dict.label.special_rules[row.ruletype]
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
    // 不同意二级原因
    // 处理跳转
    editHandle(row, status) {
      if (status === 'show') {
        this.showFlag = true
      }
      this.dialogVisable = true
      this.claimNo = row.claimno
      this.specialreportNo = row.specialreportno
    },
    detailHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          node: 'review'
        })
      )
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // claimno: row.claimno,
      //     // status,
      //     // node: 'review'
      //   }
      // })
      const newpage = this.$router.resolve({
        name: 'casedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
    }
  }
}
</script>
