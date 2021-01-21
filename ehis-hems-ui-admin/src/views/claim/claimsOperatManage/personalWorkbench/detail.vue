<template>
  <div class="app-container">
    <el-card class="box-card">
      <div>
        <div class="tab-title">
          <span>案件流量详情</span>
          <span style="float: right;">
            <el-button size="mini" type="primary" @click="goBack">返回</el-button>
          </span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="createdate" label="受理日期" show-overflow-tooltip/>
          <el-table-column align="center" width="140" prop="accepttotal" label="受理总数" show-overflow-tooltip/>
          <el-table-column align="center" width="140" prop="enternum" label="录入" show-overflow-tooltip/>
          <el-table-column align="center" prop="firstverifynum" label="初审" show-overflow-tooltip/>
          <el-table-column align="center" prop="verifynum" label="审核" show-overflow-tooltip/>
          <el-table-column align="center" prop="repeatverifynum" label="复核" show-overflow-tooltip/>
          <el-table-column align="center" prop="spotchecknum" label="抽检" show-overflow-tooltip/>
          <el-table-column align="center" prop="approvalnum" label="审批" show-overflow-tooltip/>
          <el-table-column :cell-style="changeCellStyle" align="center" prop="caseclosenum" label="已结案" show-overflow-tooltip/>
        </el-table>
      </div>
    </el-card>
  </div>
</template>
<script>
import { getDetailList } from '@/api/personalWork/index.js'
export default {
  data() {
    return {
      tableData: []
    }
  },
  mounted() {
    this.getlist()
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    getlist() {
      const params = {
        applicationetime: this.$route.query.applicationetime || '',
        applicationstime: this.$route.query.applicationstime || '',
        // creator: this.$route.creator || '',
        // group: this.$route.group || '',
        groupname: this.$route.query.groupname || '',
        grouptype: this.$route.query.grouptype || '',
        pageno: 1,
        pagesize: 10
      }
      getDetailList(params).then(res => {
        if (res.status == '1') {
          this.tableData = res.data
        }
      })
    },
    changeCellStyle() {
      return {
        color: 'rgba(254,254,252,1)'
      }
    }
  }
}
</script>
