<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input v-model="formSearch.insuredName" @keyup.native.enter="searchHandle" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idCard">
              <el-input v-model="formSearch.idCard" @keyup.native.enter="searchHandle" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报案日期：" prop="dateRange">
              <el-date-picker
                v-model="formSearch.dateRange"
                style="width: 240px;"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            :loading="searchHandleLoading"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <div class="tab-title">
        <span>报案工作池</span>
        <span style="float: right; padding-right: 10px;">
          <!--          <el-button size="mini" type="primary" @click="renovate">刷新</el-button>-->
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="addHandle">新增报案</el-button>
        </span>
      </div>
      <span>已导入({{ total }})</span>
      <claimsTable :table-data="tableData" :total="total"/>
      <el-pagination
        :total="total"
        :current-page="pageInfo.page"
        :page-size="pageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30, 50, 100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"/>
    </el-card>
  </div>
</template>
<script>
import claimsTable from './components/claimsTable'
import { getRegisterList } from '@/api/claim/register'
import moment from 'moment'
export default {
  components: {
    claimsTable
  },
  data() {
    return {
      formSearch: {
        insuredName: '',
        idCard: '',
        dateRange: [moment().subtract(29, 'days').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')]
      },
      tableData: [],
      total: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      // 点击查询按钮后查询条件
      realSearch: {
        insuredName: '',
        idNo: '',
        startDate: '',
        endDate: '',
        dateRange: [moment().subtract(29, 'days').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')]
      },
      searchHandleLoading: false,
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    // 查询
    searchHandle() {
      this.pageInfo.page = 1
      this.pageInfo.pageSize = 10
      this.realSearch.insuredName = this.formSearch.insuredName
      this.realSearch.dateRange = this.formSearch.dateRange
      this.realSearch.idNo = this.formSearch.idCard
      this.getList()
    },
    // 重置
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    // 刷新
    renovate() {
      this.getList()
    },
    addHandle() {
      let data = encodeURI(
        JSON.stringify({
          status: 'handle',
          node: 'report',
          styleFlag: 'report',
        })
      )
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // status: 'handle',
      //     // node: 'report'
      //   }
      // })
      const newpage = this.$router.resolve({
        name: 'reportdetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
    },
    sizeChange(val) {
      this.pageInfo.pageSize = val
      this.getList()
    },
    pageChange(val) {
      this.pageInfo.page = val
      this.getList()
    },
    // 获取报案列表数据
    getList() {
      this.searchHandleLoading = true
      const requestData = {
        insuredName: this.realSearch.insuredName,
        idNo: this.realSearch.idNo,
        // startDate: this.realSearch.startDate,
        // endDate:this.realSearch.endDate,
        pageSize: this.pageInfo.pageSize,
        pageNo: this.pageInfo.page
      }
      if (this.realSearch.dateRange !== null && this.realSearch.dateRange.length>0) {
        requestData.startDate = this.realSearch.dateRange[0]
        requestData.endDate = this.realSearch.dateRange[1]
      } else {
        requestData.startDate = this.formSearch.dateRange ? this.formSearch.dateRange[0] : ''
        requestData.endDate = this.formSearch.dateRange? this.formSearch.dateRange[1] : ''
      }
      getRegisterList(requestData).then(res => {
        if (res.status === '1') {
          this.tableData = res.data.list
          this.total = res.data.total
          this.searchHandleLoading = false
        } else {
          this.$message({ message: '查询报案列表数据失败！', type: 'error' })
          this.searchHandleLoading = false
        }
      })
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
