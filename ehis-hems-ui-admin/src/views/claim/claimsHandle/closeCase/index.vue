<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="formSearch.claimno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="formSearch.applicationsource" clearable class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="item in dict.apply_sourcetype" :key="item.id" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="formSearch.eventdate"
                style="width: 220px;"
                type="date"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredname">
              <el-input v-model="formSearch.insuredname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="formSearch.idcardno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="daterange">
              <el-date-picker
                v-model="formSearch.daterange"
                style="width: 220px;"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询结果</span>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" width="160px" prop="claimno" label="赔案号" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button size="small" type="text" @click="detailHandle(scope.row,'show')">{{scope.row.claimno}}</el-button>
          </template>
        </el-table-column>
        <el-table-column :formatter="getSurveySource" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
        <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
        <el-table-column align="center" prop="idcardno" label="证件号码" show-overflow-tooltip/>
        <el-table-column :formatter="formatApplicaReason" align="center" prop="applicationreason" label="申请类别" show-overflow-tooltip/>
        <el-table-column align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
        <el-table-column align="center" prop="applicationdate" label="受理日期" show-overflow-tooltip/>
        <el-table-column :formatter="getCasestatus" align="center" prop="status" label="赔案状态" show-overflow-tooltip/>
        <el-table-column align="center" prop="finalamount" label="赔付金额" show-overflow-tooltip></el-table-column>
        <el-table-column align="center" prop="closedate" label="结案日期" show-overflow-tooltip/>
        <!--        <el-table-column :formatter="getRefund" align="center" prop="status" label="退件状态" show-overflow-tooltip/>-->
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="text" @click="editHandle(scope.row,'handle')">退 件</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <el-pagination
        :total="totalCount"
        :current-page="pageInfo.page"
        :page-size="pageInfo.pageSize"
        :page-sizes="[5,10, 20, 30, 50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"/>
    </el-card>
  </div>
</template>
<script>
import claimsTable from './components/claimsTable'
import {encrypt} from "@/utils/rsaEncrypt"
import { getList } from '@/api/return/returnProcessing.js'
export default {
  dicts: ['applicationsource', 'casestatus', 'apply_sourcetype', 'application_category', 'queue_claim_status', 'refund'],
  components: {
    claimsTable
  },
  data() {
    return {
      formSearch: {
        daterange: [],
        claimno: '',
        applicationsource: '',
        eventdate: '',
        insuredname: '',
        idcardno: ''
      },
      activeName: '01',
      tableData: [],
      totalCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      changeSerchData: {}
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    searchHandle() {
      this.changeSerchData.daterange = this.formSearch.daterange
      this.changeSerchData.claimno = this.formSearch.claimno
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.eventdate = this.formSearch.eventdate
      this.changeSerchData.insuredname = this.formSearch.insuredname
      this.changeSerchData.idcardno = this.formSearch.idcardno
      this.pageInfo.page = 1
      this.pageInfo.pageSize = 10
      this.getList()
    },
    getRefund(row) {
      return this.dict.label.refund[row.status]
    },
    // 处理跳转
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          status,
          showSelect: false,
          flag: '02',
          claimno: encrypt(row.claimno),
          refundno: row.refundno,
          caseclosedreturnflag: row.returnflag,
          showEle: true
        })
      )
      const newpage = this.$router.resolve({
        name: 'returndetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/return-edit',
      //   query: {
      //    data
      //   }
      // })
    },
    formatApplicaReason(row) {
      let reason = ''
      if (row.applicationreason) {
        row.applicationreason.split(',').map((item, i) => {
          this.dict.label.application_category[item] ? reason += `、${this.dict.label.application_category[item]}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    sizeChange(val) {
      this.pageInfo.pageSize = val
      this.getList()
    },
    pageChange(val) {
      this.pageInfo.page = val
      this.getList()
    },
    getList() {
      const params = {}
      params.applicationsource = this.changeSerchData.applicationsource	|| ''
      params.acceptenddate = this.changeSerchData.daterange ? this.changeSerchData.daterange[1] : ''
      params.acceptstartdate = this.changeSerchData.daterange ? this.changeSerchData.daterange[0] : ''
      params.claimno = this.changeSerchData.claimno || ''
      params.eventdate = this.changeSerchData.eventdate || ''
      params.insuredname = this.changeSerchData.insuredname || ''
      params.idcardno = this.changeSerchData.idcardno || ''
      params.pageno = this.pageInfo.page
      params.pagesize = this.pageInfo.pageSize
      // params.tabno = no
      params.flag = '02'
      getList(params).then(res => {
        this.tableData = (res && res.list) || []
        this.totalCount = res.total
      })
    },
    // 获取调查来源名称
    getSurveySource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    // 获取案件状态
    getCasestatus(row, col) {
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
      //     // claimno: row.claimNo,
      //     // status,
      //     // node: 'review'
      //   }
      // })
    },
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
