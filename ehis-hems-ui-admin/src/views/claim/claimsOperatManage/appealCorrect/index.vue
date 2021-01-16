<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
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
              <el-select v-model="formSearch.applicationsource" class="item-width" placeholder="请选择">
                <el-option v-for="(item,index) in applicationsourceArr"
                :key="index"
                :label="item.label"
                :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="formSearch.eventdate"
                type="daterange"
                class="item-width"
                value-format="yyyy-MM-dd"
                range-separator="~"
                 start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idNo">
              <el-input v-model="formSearch.idNo" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请日期：" prop="applyDate">
              <el-date-picker
                v-model="formSearch.applyDate"
                class="item-width"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="审核人：" prop="auditor">
              <el-input v-model="formSearch.auditor" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoading"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <div class="tab-title">
        <span>申诉工作池</span>
      </div>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName" @tab-click="choseActive">
          <el-tab-pane :label="`待处理(${pendingTableDataLength})`" name="0">
            <claimsTable v-loading="tableLoading" :table-data="pendingTableData" @getData="getData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${completedTableDataLength})`" name="1">
            <claimsTable v-loading="tableLoading" :table-data="completedTableData" :status="activeName"/>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <el-pagination
          :total="activeName==='0'?total:finishTotal"
          :current-page="activeName==='0'?page:finishPage"
          :page-sizes="pageSizes"
          :page-size="activeName==='0'?pageSize:finishPageSize"
          style="margin-top: 8px; text-align: right;"
          layout="sizes, prev, pager, next"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
      </div>
    </el-card>
  </div>
</template>
<script>
import claimsTable from './components/claimsTable'
import { atLeastOne } from '@/utils/commenMethods.js'
import { getList, complaintCorrectionHandle } from '@/api/appealCorrect/index.js'
import moment from 'moment'
export default {
  components:{
    claimsTable
  },
  dicts : ['apply_sourcetype'],
  data() {
    return {
      pendingTableDataLength: 0,
      caseDate: [moment().subtract(6, 'days').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')],
      completedTableDataLength: 0,
      tableData: [],
      page: 1,
      total: 0,
      dialogTableVisible: false,
      finishTotal: 0,
      pageSize: 10,
      finishPage: 1,
      finishPageSize: 10,
      pageSizes: [5, 10,20,30,50,100],
      activeName: '0',
      formSearch: {
        claimno: '',
        applicationsource: '',
        eventdate: [],
        name: '',
        applyDate: [moment().subtract(30, 'days').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')],
        auditor: '',
        pageno: '',
        pagesize: '',
        idNo: ''
      },
      applicationsourceArr: [],
      completedTableData: [
      ],
      pendingTableData: [],
      tableLoading: false,
      changeSerchData: {
        applyDate: []
      },
      searchLoading: false
    }
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.total === 0 && newVal.finishTotal > 0) {
        this.activeName = '1'
      } else {
        this.activeName = '0'
      }
    }
  },
  computed: {
    totalChange() {
      const {total, finishTotal} = this
      return {total, finishTotal}
    }
  },
  mounted() {
    // this.getData()
    this.getData('0')
    this.getData('1')
    this.getApplicationsource()
  },
  methods: {
    sizeChange(val) {
      if (this.activeName == '0') {
        this.pageSize = val
        this.page = 1
      } else {
        this.finishPageSize = val
        this.finishPage = 1
      }
      this.getData(this.activeName)
    },
    pageChange(val) {
      if (this.activeName == '0') {
        this.page = val
      } else {
        this.finishPage = val
      }
      this.getData(this.activeName)
    },
    // 搜素
    searchHandle() {
      this.changeSerchData.claimno = this.formSearch.claimno
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.eventdate = this.formSearch.eventdate
      this.changeSerchData.name = this.formSearch.name
      this.changeSerchData.applyDate = this.formSearch.applyDate
      this.changeSerchData.auditor = this.formSearch.auditor
      this.changeSerchData.idNo = this.formSearch.idNo
      this.pageSize = 10
      this.finishPageSize = 10
      this.page = 1
      this.finishPage = 1
      // const checkResult = atLeastOne(this.formSearch)
      // if (!checkResult) {
      //   this.$message({ message: '请至少录入一项查询条件!', type: 'warning'})
      //   return false
      // }
      this.getData('0')
      this.getData('1')
    },
    // 查询申诉纠错列表
    getData(no) {
      // 处理参数
      this.searchLoading = true
      const params = JSON.parse(JSON.stringify(this.changeSerchData))
      params.pageno = this.activeName == '0' ? this.page : this.finishPage
      params.pagesize = this.activeName == '0' ? this.pageSize : this.finishPageSize
      params.isDeal = no
      delete params.eventdate
      delete params.applyDate
      params.eventsdate = (this.changeSerchData.eventdate? this.changeSerchData.eventdate[0] : '') || ''
      params.eventedate =(this.changeSerchData.eventdate? this.changeSerchData.eventdate[1] : '')|| ''
      if (this.changeSerchData.applyDate!== null && this.changeSerchData.applyDate.length>0) {
        params.applicationsdate = this.changeSerchData.applyDate? this.changeSerchData.applyDate[0] : ''
        params.applicationedate  = this.changeSerchData.applyDate? this.changeSerchData.applyDate[1] : ''
      } else {
        params.applicationsdate = this.formSearch.applyDate? this.formSearch.applyDate[0] : ''
        params.applicationedate  = this.formSearch.applyDate? this.formSearch.applyDate[1] : ''
      }
      params.idNo = this.changeSerchData.idNo || ''
      let _this = this
      getList(params).then(res => {
        if (res.status == '1') {
          _this.tableData = []
          _this.tableData =  (res.data && res.data.list) || []
          if (no === '0') {
            _this.pendingTableData = _this.tableData
            this.pendingTableDataLength = res.data.total || 0 || 0
            this.total = res.data.total || 0
          } else {
            _this.completedTableData = _this.tableData
            this.completedTableDataLength = res.data.total || 0
            this.finishTotal = res.data.total || 0
          }
          this.totalCount = res.data.total
        } else {
          this.$message.error('查询异常！')
        }
        this.searchLoading = false
      })
    },
    // 重置表单
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    // 切换tab
    choseActive() {
      // this.getData(this.activeName)
    },
    // 获取申请来源
    getApplicationsource(row, col) {
      return this.applicationsourceArr = this.dict.apply_sourcetype || []
    },
  }
}
</script>
<style scoped>
  .item-width {
    width: 220px;
  }
</style>
