<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <!--    <el-table-column align="center" prop="claimno" label="赔案号" show-overflow-tooltip/>-->
    <el-table-column align="center" prop="claimno" :width="180" label="赔案号" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="detailHandle(scope.row,'show')">{{ scope.row.claimno }}</el-button>
      </template>
    </el-table-column>
    <el-table-column :formatter="getapplicationsource" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="idcardno" label="证件号码" show-overflow-tooltip/>
    <el-table-column :formatter="formatApplicaReason" align="center" prop="description" label="申请类别" show-overflow-tooltip/>
    <el-table-column align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="applicationdate" label="受理日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="datalist" label="资料清单" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-tooltip class="item" effect="dark" :content="scope.row.materialnames" placement="top-start">
          <el-button size="small" type="text">{{ scope.row.datalist }}</el-button>
        </el-tooltip>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="fillingsdescription" label="补材描述" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button size="small" type="text">{{ scope.row.fillingsdescription }}</el-button>
      </template>
    </el-table-column>
    <el-table-column :formatter="getqueueclaimstatus" align="center" prop="status" label="赔案状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="createdate" label="发起时间" show-overflow-tooltip/>
    <el-table-column align="center" prop="fillingsdays" label="补材天数" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="status === '01'" size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
        <el-button v-else size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['apply_sourcetype', 'application_category', 'queue_claim_status'],
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
          status,
          node: 'physical',
          claimno: encrypt(row.claimno),
          supplementaryno: row.supplementaryno
        })
      )
      const newpage = this.$router.resolve({
        name: 'physicaldetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // status,
      //     // node: 'physical',
      //     // claimno: row.claimno,
      //     // supplementaryno: row.supplementaryno
      //   }
      // })
    },
    // 申请来源
    getapplicationsource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
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
    // 赔案状态
    getqueueclaimstatus(row, col) {
      return this.dict.label.queue_claim_status[row.status]
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
<style>
.el-tooltip__popper{
  max-width: 50%;
}
</style>