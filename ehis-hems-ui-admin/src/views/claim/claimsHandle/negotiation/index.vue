<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="formSearch.claimno" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="formSearch.applicationsource" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.apply_sourcetype" :key="item.id" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="accidentdate">
              <el-date-picker
                v-model="formSearch.accidentdate"
                type="date"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredname">
              <el-input v-model="formSearch.insuredname" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="insuredidno">
              <el-input v-model="formSearch.insuredidno" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="claimCreatedate">
              <el-date-picker
                v-model="formSearch.claimCreatedate"
                class="item-width"
                type="daterange"
                range-separator="~ "
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="协谈类型：" prop="negotiationtype">
              <el-select v-model="formSearch.negotiationtype" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.negotiation_type" :key="item.id" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="赔案状态：" prop="status">
              <el-select v-model="formSearch.status" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.queue_claim_status" :key="item.id" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="username">
              <el-input v-model="formSearch.username" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoad"
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
        <span>协谈工作池</span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable :table-data="pendingTableData" :claimno="claimno" :node="node" :status="activeName" @editHandle="editHandle"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="03">
          <claimsTable :table-data="completedTableData" :claimno="claimno" :node="node" :status="activeName" @editHandle="editHandle"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <el-pagination
        :total="activeName==='01'?pendingTotal: completedTotal"
        :current-page="activeName==='01'?pendPageInfo.page:completePageInfo.page"
        :page-size="activeName==='01'?pendPageInfo.pageSize:completePageInfo.pageSize"
        :page-sizes="[5,10, 20, 30, 50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"/>
    </el-card>
    <ClaimsDialog
      :dialog-visible="dialogVisible"
      :orderno="orderno"
      :claimno="claimno"
      :node="node"
      :negotiationno="negotiationno"
      @refreshTable="refreshTable"
      @handleClose="handleClose"/>
  </div>
</template>

<script>
import claimsTable from './components/claimsTable'
import ClaimsDialog from './components/claimsDialog'
import { queryNegotiationinfo01, queryNegotiationinfo02, updateNegotiation } from '@/api/claim/negotation.js'
export default {
  dicts: ['negotiation_mode', 'queue_claim_status', 'apply_sourcetype', 'negotiation_type'],
  name: 'Negotiation',
  components: {
    claimsTable,
    ClaimsDialog
  },
  data() {
    return {
      tableData: [],
      formSearch: {
        negotiationtype: '', // 协谈类型
        claimno: '', // 赔案号
        insuredname: '', // 被保人姓名
        insuredidno: '', // 证件号码
        username: '', // 操作人
        applicationsource: '', // 申请来源
        status: '', // 赔案状态
        accidentdate: '', // 出现日期
        claimCreatedate: ['', '']// 受理日期
      },
      activeName: '01',
      pendingTableData: [],
      completedTableData: [],
      pendingTotal: 0,
      completedTotal: 0,
      pendPageInfo: {
        page: 1,
        pageSize: 10
      },
      completePageInfo: {
        page: 1,
        pageSize: 10
      },
      dialogVisible: false,
      orderno: '',
      claimno: '',
      node: '',
      negotiationno: '',
      changeSerchData: {}
    }
  },
  created() {
    // this.getDataList1()
    // this.getDataList2()
    this.searchHandle()
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.pendingTotal === 0 && newVal.completedTotal > 0) {
        this.activeName = '03'
      } else {
        this.activeName = '01'
      }
    }
  },
  computed: {
    totalChange() {
      const {pendingTotal, completedTotal} = this
      return {pendingTotal, completedTotal}
    }
  },
  methods: {
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {
      this.changeSerchData.negotiationtype = this.formSearch.negotiationtype
      this.changeSerchData.claimno = this.formSearch.claimno
      this.changeSerchData.insuredname = this.formSearch.insuredname
      this.changeSerchData.insuredidno = this.formSearch.insuredidno
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.username = this.formSearch.username
      this.changeSerchData.status = this.formSearch.status
      this.changeSerchData.accidentdate = this.formSearch.accidentdate
      this.changeSerchData.claimCreatedate = this.formSearch.claimCreatedate
      this.pendPageInfo.page = 1
      this.pendPageInfo.pageSize = 10
      this.completePageInfo.page = 1
      this.completePageInfo.pageSize = 10
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      this.getDataList1()
      this.getDataList2()
    },
    // 查询处理中
    getDataList1() {
      this.searchLoad = true
      const params = JSON.parse(JSON.stringify(this.changeSerchData))
      params.pageno = this.pendPageInfo.page
      params.pagesize = this.pendPageInfo.pageSize
      params.claimstartdate = params.claimCreatedate? params.claimCreatedate[0] : ''
      params.claimenddate = params.claimCreatedate? params.claimCreatedate[1] : ''
      delete params.claimcreatedate
      queryNegotiationinfo01(params).then(res => {
        if (res.status === '1') {
          this.pendingTotal = res.data.total
          this.pendingTableData = res.data.list
        }
        this.searchLoad = false
      })
    },
    // 查询已处理
    getDataList2() {
      const params = JSON.parse(JSON.stringify(this.changeSerchData))
      params.pageno = this.completePageInfo.page
      params.pagesize = this.completePageInfo.pageSize
      params.claimstartdate = params.claimCreatedate? params.claimCreatedate[0] : ''
      params.claimenddate = params.claimCreatedate? params.claimCreatedate[1] : ''
      delete params.claimcreatedate
      queryNegotiationinfo02(params).then(res => {
        if (res.status === '1') {
          this.completedTotal = res.data.total
          this.completedTableData = res.data.list
        }
      })
    },
    handleClick() {
      if (this.activeName === '01') {
        this.getDataList1()
      } else {
        this.getDataList2()
      }
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getDataList1()
      } else {
        this.completePageInfo.pageSize = val
        this.getDataList2()
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getDataList1()
      } else {
        this.completePageInfo.page = val
        this.getDataList2()
      }
    },
    editHandle(data) {
      this.orderno = data.orderno
      this.claimno = data.claimno
      this.node = data.node
      this.negotiationno = data.negotiationno
      this.dialogVisible = true
    },
    handleClose() {
      this.dialogVisible = false
    },
    refreshTable() {
      this.searchHandle()
    },
    goBack() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
