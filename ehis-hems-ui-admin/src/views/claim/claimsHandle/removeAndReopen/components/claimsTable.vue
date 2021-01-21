<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8f8'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" prop="claimno" width="140px" label="赔案号" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="detailHandle(scope.row,'show')">{{ scope.row.claimno }}</el-button>
      </template>
    </el-table-column>
    <el-table-column :formatter="getApplicationsource" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column :formatter="formatApplicaReason" align="center" prop="description" label="申请类别" show-overflow-tooltip/>
    <el-table-column align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="applicationdate" label="受理日期" show-overflow-tooltip/>
    <el-table-column :formatter="formatRejectReason" align="center" prop="rejectreason" label="撤件原因" show-overflow-tooltip/>
    <el-table-column align="center" prop="createdate" label="撤件日期" show-overflow-tooltip/>
    <el-table-column v-if="status==='03'" align="center" prop="relationclaimno" label="新赔案号" show-overflow-tooltip/>
    <el-table-column v-if="status==='01'" align="center" label="操作">
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['apply_sourcetype', 'application_category', 'acceptreason'],
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
    // 处理跳转
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          node: 'reopen',
          flag: '1',
          claimno: encrypt(row.claimno)
        })
      )
      // this.$router.push({
      //   path: '/claims-handle/upload',
      //   query: {
      //     data
      //   }
      // })
      const newpage = this.$router.resolve({
        name: 'removecasedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
    },
    // 撤件原因
    formatRejectReason(row) {
      return this.dict.label.acceptreason[row.rejectreason]
    },
    // 获得申请来源
    getApplicationsource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    formatApplicaReason(row) {
      let reason = ''
      if (row.description) {
        row.description.split(',').map((item, i) => {
          this.dict.label.application_category[item] ? reason += `|${this.dict.label.application_category[item]}` : reason = ''
        })
      }
      return reason.slice(1)
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
      //   // path: '/job-manage/handle',
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
