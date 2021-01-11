<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form style="padding-bottom: 30px;" ref="searchForm" :model="formSearch" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimNo">
              <el-input class="item-width" clearable v-model="formSearch.claimNo" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select class="item-width" v-model="formSearch.applicationsource" placeholder="请选择">
                <el-option v-for="item in dict.apply_sourcetype" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventDate">
              <el-date-picker
                style="width: 220px;"
                v-model="formSearch.eventDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input class="item-width" clearable v-model="formSearch.insuredName" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardNo">
              <el-input class="item-width" clearable v-model="formSearch.idcardNo" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="daterange">
              <el-date-picker
                style="width: 220px;"
                v-model="formSearch.daterange"
                value-format="yyyy-MM-dd"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>
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
      <el-tabs v-model="activeName" @tab-click="choseActive">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable :table-data="pendingTableData" :status="activeName" @searchHandle="searchHandle"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="02">
          <claimsTable :table-data="completedTableData" :status="activeName"/>
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
  </div>
</template>
<script>
import claimsTable from './components/claimsTable'
import {list} from '@/api/examine.js'
export default {
  dicts: ['apply_sourcetype', 'apply_type'],
  components: {
    claimsTable
  },
  data() {
    return {
      formSearch: {
        daterange: [],
        claimNo: '',
        applicationsource: '',
        eventDate: '',
        insuredName: '',
        idcardNo: ''
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
      changeSerchData: {
        daterange:[]
      },
      completePageInfo: {
        page: 1,
        pageSize: 10
      },
      baseForm: {
        tableData: []
      },
      searchLoading: false
    }
  },
  mounted() {
    this.getList('01')
    this.getList('02')
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.pendingTotal === 0 && newVal.completedTotal > 0) {
        this.activeName = '02'
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
    searchHandle(){
      this.changeSerchData.claimNo = this.formSearch.claimNo
      this.changeSerchData.idcardNo = this.formSearch.idcardNo
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.eventDate = this.formSearch.eventDate
      this.changeSerchData.insuredName = this.formSearch.insuredName
      this.changeSerchData.daterange = this.formSearch.daterange
      // this.getList(this.activeName)
      this.pendPageInfo.page = 1
      this.completePageInfo.page = 1
      this.pendPageInfo.pageSize = 10
      this.completePageInfo.pageSize = 10
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      this.getList('01')
      this.getList('02')
    },
    resetForm(){
      this.$refs.searchForm.resetFields()
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getList(this.activeName)
      } else {
        this.completePageInfo.pageSize = val
        this.getList(this.activeName)
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getList(this.activeName)
      } else {
        this.completePageInfo.page = val
        this.getList(this.activeName)
      }
    },
    getList(no) {
      let params = {}
      params.pageno = no === '01'?  this.pendPageInfo.page : this.completePageInfo.page
      params.pagesize = no === '01'? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      params.tabno = no
      params.acceptStartDate = this.changeSerchData.daterange? this.changeSerchData.daterange[0] : ''
      params.acceptendDate = this.changeSerchData.daterange? this.changeSerchData.daterange[1] : ''
      params.applicationSource = this.changeSerchData.applicationsource || ''
      params.claimNo = this.changeSerchData.claimNo || ''
      params.eventDate = this.changeSerchData.eventDate || ''
      params.idcardNo = this.changeSerchData.idcardNo || ''
      params.insuredName = this.changeSerchData.insuredName || ''
      this.searchLoading = true
      list(params).then(res => {
        let data = (res && res.list) || []
        if(no === '01'){
          this.pendingTableData = data
          this.pendingTotal = res.total || 0
        }else{
          this.completedTableData = data
          this.completedTotal = res.total || 0
        }
        this.searchLoading = false
      }
      )
    },
    choseActive() {
      this.getList(this.activeName)
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .row-box {
    padding-top: 20px;
    border-bottom: 1px solid #e6ebf5;
    margin-bottom: 20px;
    line-height: 50px;
  }
</style>
