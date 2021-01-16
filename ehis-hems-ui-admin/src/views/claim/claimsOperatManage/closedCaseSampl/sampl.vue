<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :rules="rules" :model="searchForm" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" clearable size="mini" class="item-width"  placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="姓名：" prop="name">
              <el-input v-model="searchForm.name" clearable size="mini" class="item-width"  placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="身份证：" prop="idcardno">
              <el-input v-model="searchForm.idcardno" clearable size="mini" class="item-width" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="抽检结论：" prop="checkConclusion">
              <el-select v-model="searchForm.checkConclusion" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.conclusions" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="searchForm.applicationsource" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.apply_sourcetype" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="不通过类型：" prop="rejectreason">
              <el-select v-model="searchForm.rejectreason" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.examine_reject" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="结案日期：" prop="closedate">
              <el-date-picker
                v-model="searchForm.closedate"
                value-format="yyyy-MM-dd"
                type="daterange"
                size="mini"
                style="width:240px"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="抽检时间：" prop="sampldate">
              <el-date-picker
                v-model="searchForm.sampldate"
                value-format="yyyy-MM-dd"
                type="daterange"
                size="mini"
                style="width:240px"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
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
          <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <div slot="header" class="clearfix">
        <span>抽检工作池</span>
        <span style="float: right;" >
          <el-button :loading="exportLoading" size="mini" type="warning" @click="exportInventoryFile" icon="el-icon-plus">清单下载</el-button>
        </span>
      </div>
      <el-tabs v-model="activeName" @tab-click="getData">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <samplTable :table-data="pendingTableData" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="03">
          <samplTable :table-data="completedTableData" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <el-pagination
        :total="activeName==='01'?pendingTotal: completedTotal"
        :current-page="activeName==='01'?pendPageInfo.currentPage:completePageInfo.currentPage"
        :page-size="activeName==='01'?pendPageInfo.pageSize:completePageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"
      />
    </el-card>
  </div>
</template>
<script>
import samplTable from './components/samplTable'
import { downloadFile } from "@/utils/index";
import { getCheckPendingList, getCheckCompleteList,exportPendingFile,exportCompleteFile } from '@/api/closedCaseSampl/check'
export default {
  dicts: ['apply_sourcetype', 'conclusion', 'examine_reject', 'conclusions'],
  components: {
    samplTable
  },
  data() {
    return {
      activeName: '01',
      pendingTableData: [],
      completedTableData: [],
      pendingTotal: 0,
      completedTotal: 0,
      pendPageInfo: {
        currentPage: 1,
        pageSize: 10
      },
      completePageInfo: {
        currentPage: 1,
        pageSize: 10
      },
      exportLoading:false,
      rules: {},
      searchForm: {
        claimno: '',
        name: '',
        idcardno: '',
        checkConclusion: '',
        applicationsource: '',
        rejectreason: '',
        closedate: [],
        sampldate: []
      },
      searchLoading: false
    }
  },
  mounted() {
    this.searchHandle()
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.pendingTotal === 0 && newVal.completedTotal > 0) {
        this.activeName = '03'
      } else {
        this.activeName = '01'
      }
    },
  },
  computed: {
    totalChange() {
      const {pendingTotal, completedTotal} = this
      return {pendingTotal, completedTotal}
    }
  },
  methods: {
    getData() {
      // if (this.activeName === '01') {
      //   this.getPendingData(true)
      // } else {
      //   this.getCompletedData(true)
      // }
    },
    getParams() {
      const params = JSON.parse(JSON.stringify(this.searchForm))
      // params.pageno = this.activeName === '01' ? this.pendPageInfo.currentPage : this.completePageInfo.currentPage
      // params.pagesize = this.activeName === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      params.beginCreateDate = params.sampldate ? params.sampldate[0] : ''
      params.endCreateDate = params.sampldate ? params.sampldate[1] : ''
      params.beginOperateDate = params.closedate ? params.closedate[0] : ''
      params.endOperateDate = params.closedate ? params.closedate[1] : ''
      delete params.sampldate
      delete params.closedate
      return params
    },
    getPendingData(flag) {
      // const params = JSON.parse(JSON.stringify(this.pendPageInfo))
      // getCheckPendingList(params).then(res => {
      //   if (res.status === '1') {
      //     this.pendingTableData = res.data.list
      //     this.pendingTotal = res.data.total
      //   }
      // })
      this.searchLoading = true
      let params
      if (flag) {
        params = this.getParams()
        this.searchParams = JSON.parse(JSON.stringify(params))
      } else {
        params = this.searchParams
      }
      params.pageno = this.activeName === '01' ? this.pendPageInfo.currentPage : this.completePageInfo.currentPage
      params.pagesize = this.activeName === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      getCheckPendingList(params).then(res => {
        if (res.status === '1') {
          this.pendingTableData = res.data.list
          this.pendingTotal = res.data.total
        } else {
          this.$message.error('查询失败！')
        }
      }).finally(res => {
        this.searchLoading = false
      })
    },
    getCompletedData(flag) {
      // const params = JSON.parse(JSON.stringify(this.completePageInfo))
      this.searchLoading = true
      let params
      if (flag) {
        params = this.getParams()
        this.searchParams = JSON.parse(JSON.stringify(params))
      } else {
        params = this.searchParams
      }
      params.pageno = this.activeName === '01' ? this.pendPageInfo.currentPage : this.completePageInfo.currentPage
      params.pagesize = this.activeName === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      getCheckCompleteList(params).then(res => {
        if (res.status === '1') {
          this.completedTableData = res.data.list
          this.completedTotal = res.data.total
        }
      }).finally(res => {
        this.searchLoading = false
      })
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getPendingData(false)
      } else {
        this.completePageInfo.pageSize = val
        this.getCompletedData(false)
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.currentPage = val
        this.getPendingData(false)
      } else {
        this.completePageInfo.currentPage = val
        this.getCompletedData(false)
      }
    },
    //下载
    exportInventoryFile(){
        this.exportLoading = true
        if (this.activeName === '01'){
            exportPendingFile()
                .then(response => {
                    downloadFile(response, '待处理清单', 'xlsx')
                })
                .catch(error => {
                    this.$message({message: '待处理清单下载异常！', type: 'error'});
                }).finally(() => {
                this.exportLoading = false
            })
        }
        else {
            exportCompleteFile()
                .then(response => {
                    downloadFile(response, '已处理清单', 'xlsx')
                })
                .catch(error => {
                    this.$message({message: '已处理清单下载异常！', type: 'error'});
                }).finally(() => {
                this.exportLoading = false
            })
        }
    },
    searchHandle() {
      this.searchLoading = true
      this.completePageInfo.currentPage = 1
      this.completePageInfo.pageSize = 10
      this.pendPageInfo.currentPage = 1
      this.pendPageInfo.pageSize = 10
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      this.getPendingData(true)
      this.getCompletedData(true)
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
