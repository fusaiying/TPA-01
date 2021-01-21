<template>
  <el-dialog
    v-loading="tableLoading"
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    :fullscreen="true"
    @open="openHandle">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>抽检历史</span>
      </div>
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <el-table-column width="160" property="rulecode" label="抽检规则"/>
        <el-table-column width="160" property="claimno" label="赔案号"/>
        <el-table-column property="name" label="被保人姓名"/>
        <el-table-column :formatter="formatApplicationreason" property="applicationReason" label="申请类别"/>
        <el-table-column property="eventdate" label="出险日期"/>
        <el-table-column property="applicationdate" label="受理日期"/>
        <el-table-column property="operatedate" label="结案日期">
          <template slot-scope="scope">
            <span>{{scope.row.operatedate|changeDate}}</span>
          </template>
        </el-table-column>
        <el-table-column :formatter="formatConclusion" property="conclusion" label="赔付结论"/>
        <el-table-column property="payableamount" label="赔付金额">
          <template slot-scope="scope">
            <span>{{scope.row.payableamount ? scope.row.payableamount.substring(0, scope.row.payableamount.length - 1) : ''}}</span>
          </template>
        </el-table-column>
        <el-table-column :formatter="changeStatus" property="status" label="抽检状态"/>
        <el-table-column :formatter="formatFinalsamplingConclusions" property="checkConclusion" label="抽检结论"/>
      </el-table>
      <el-pagination
        :total="total"
        :current-page="pageInfo.page"
        :page-size="pageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </el-card>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="dialogClosed">关 闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { changeDate } from '@/utils/commenMethods.js'
import { checkHistory } from '@/api/closedCaseSampl/closedCaseSampl'
export default {
  dicts: ['samplingstatus', 'applicationreason', 'conclusion', 'conclusions'],
  props: {
    value: Boolean
  },
  filters: {
    changeDate: changeDate
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageInfo: {
        currentPage: 1,
        pageSize: 10
      },
      tableLoading: false
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  mounted() {
    // this.getHistoryData()
  },
  methods: {
    openHandle() {
        this.getHistoryData()
    },
    // 申请原因
    formatApplicationreason(row) {
        return this.dict.label.applicationreason[row.applicationReason]
    },
    // 赔付结论
    formatConclusion(row) {
        return this.dict.label.conclusion[row.conclusion]
    },
    // 抽检结论
    formatFinalsamplingConclusions(row) {
        return row.status === '2' ? this.dict.label.conclusions[row.checkConclusion] : ''
    },
    getHistoryData() {
      const params = JSON.parse(JSON.stringify(this.pageInfo))
      checkHistory(params).then(res => {
        if (res.status === '1') {
          this.tableData = res.data.list
          this.total = res.data.total
          this.tableLoading = false
        } else {
          this.tableLoading = false
        }
      })
    },
    changeStatus(row) {
      return this.dict.label.samplingstatus[row.status]
    },
    dialogClosed() {
      this.$emit('input', false)
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    handleSizeChange(val) {
      this.pageInfo.pageSize = val
      this.getHistoryData()
    },
    handleCurrentChange(val) {
      this.pageInfo.currentPage = val
      this.getHistoryData()
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
</style>
