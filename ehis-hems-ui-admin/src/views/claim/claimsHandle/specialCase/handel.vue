<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>案件报备工作池</span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable :table-data="pendingTableData" :status="activeName" @getList1="getList1"/>
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
import { getSpecialHandlList } from '@/api/claim/specialCase'
export default {
  components: {
    claimsTable
  },
  data() {
    return {
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
      }
    }
  },
  created() {
    this.initData()
  },
  methods: {
    initData() {
      this.getList1()
    },
    // 获取待处理和已处理列表
    getList1() {
      this.getSpecialHandleList('01')
      this.getSpecialHandleList('03')
    },
    getSpecialHandleList(tabNo) {
      const param = {
        tabNo: tabNo,
        pageno: tabNo === '01' ? this.pendPageInfo.page : this.completePageInfo.page,
        pagesize: tabNo === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      }
      getSpecialHandlList(param).then(response => {
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
            this.$message({ mssage: '查询已处理结果错误！', type: 'error' })
          }
        }
      })
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getSpecialHandleList(this.activeName)
      } else {
        this.completePageInfo.pageSize = val
        this.getSpecialHandleList(this.activeName)
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getSpecialHandleList(this.activeName)
      } else {
        this.completePageInfo.page = val
        this.getSpecialHandleList(this.activeName)
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
