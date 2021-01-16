<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="initData"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="formSearch.eventdate"
                style="width: 230px;"
                type="daterange"
                range-separator="~"
                value-format="yyyy-MM-dd"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="applicationdate">
              <el-date-picker
                v-model="formSearch.applicationdate"
                style="width: 230px;"
                type="daterange"
                range-separator="~"
                value-format="yyyy-MM-dd"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoad"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="initData"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable :table-data="pendingTableData" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="03">
          <claimsTable :table-data="completedTableData" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <el-pagination
        :total="activeName==='01'?pendingTotal: completedTotal"
        :current-page="activeName==='01'?pendPageInfo.page:completePageInfo.page"
        :page-size="activeName==='01'?pendPageInfo.pageSize:completePageInfo.pageSize"
        :page-sizes="[5,10,20,30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"/>
    </el-card>
  </div>
</template>
<script>
import claimsTable from './components/claimsTable'
import { selectRestartCase } from '@/api/claim/reopenCase'
export default {
  components: {
    claimsTable
  },
  data() {
    return {
      formSearch: {
        name: '',
        eventdate: [],
        applicationdate: []
      },
      changeSerchData: {},
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
      searchLoad: false
    }
  },
  created() {
    this.initData()
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
    initData() {
      this.changeSerchData.name = this.formSearch.name
      this.changeSerchData.eventdate = this.formSearch.eventdate
      this.changeSerchData.applicationdate = this.formSearch.applicationdate
      this.pendPageInfo.page = 1
      this.pendPageInfo.pageSize = 10
      this.completePageInfo.page = 1
      this.completePageInfo.pageSize = 10
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      this.searchHandle('01')
      this.searchHandle('03')
    },
    searchHandle(tabNo) {
      this.searchLoad = true
      const params = {
        name: this.changeSerchData.name,
        eventDateStart: this.changeSerchData.eventdate ? this.changeSerchData.eventdate[0] : '',
        eventDateEnd: this.changeSerchData.eventdate ? this.changeSerchData.eventdate[1] : '',
        // eventDate: this.formSearch.eventdate,
        startApplicationDate: this.changeSerchData.applicationdate ? this.changeSerchData.applicationdate[0] : '',
        endApplicationDate: this.changeSerchData.applicationdate ? this.changeSerchData.applicationdate[1] : '',
        tabNo: tabNo,
        pageno: tabNo === '01' ? this.pendPageInfo.page : this.completePageInfo.page,
        pagesize: tabNo === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      }
      selectRestartCase(params).then(response => {
        if (response.status === '1') {
          if (tabNo === '01') {
            this.pendingTotal = response.data.totalnum
            this.pendingTableData = response.data.list
          } else if (tabNo === '03') {
            this.completedTotal = response.data.totalnum
            this.completedTableData = response.data.list
          }
        } else {
          if (tabNo === '01') {
            this.$message({ message: '查询待处理结果错误！', type: 'error' })
          } else {
            this.$message({ message: '查询已处理结果错误！', type: 'error' })
          }
        }
        this.searchLoad = false
      })
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.searchHandle(this.activeName)
      } else {
        this.completePageInfo.pageSize = val
        this.searchHandle(this.activeName)
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.searchHandle(this.activeName)
      } else {
        this.completePageInfo.page = val
        this.searchHandle(this.activeName)
      }
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
