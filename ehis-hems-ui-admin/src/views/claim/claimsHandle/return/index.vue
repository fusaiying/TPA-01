<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form style="padding-bottom: 30px;" ref="searchForm" :model="formSearch" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input class="item-width" clearable v-model="formSearch.claimno" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="formSearch.applicationsource" style="width:220px;" clearable size="mini" placeholder="请选择">
                <el-option v-for="option in dict.apply_sourcetype"  :key="option.value" :label="option.label" :value="option.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                style="width: 200px;"
                v-model="formSearch.eventdate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredname">
              <el-input class="item-width" clearable v-model="formSearch.insuredname" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="daterange">
              <el-date-picker
                style="width: 220px;"
                v-model="formSearch.daterange"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input class="item-width" clearable v-model="formSearch.idcardno" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLaod"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card style="margin-top:10px">
      <div slot="header" class="clearfix">
        <span>退件工作池</span>
      </div>
      <div style="position: relative;">
        <el-button size="mini" type="primary" style="position: absolute; right: 20px; top: 0;z-index:99999999999999999999999999" @click.native="toCase">结案退件</el-button>
        <el-tabs v-model="activeName" @tab-click="choceActiveName" >
          <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
            <claimsTable :table-data="pendingTableData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${completedTotal})`" name="03">
            <claimsTable :table-data="completedTableData" :status="activeName"/>
          </el-tab-pane>
        </el-tabs>
      </div>
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
import {getList} from '@/api/return/returnProcessing.js'
export default {
  dicts: ['queue_claim_status','apply_sourcetype'],
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
      changeSerchData: {},
      searchLaod: false,
    }
  },
  mounted() {
    // this.getList(this.activeName)
    // this.getList('03')
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
    searchHandle(){
      this.changeSerchData.claimno = this.formSearch.claimno
      this.changeSerchData.idcardno = this.formSearch.idcardno
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.eventdate = this.formSearch.eventdate
      this.changeSerchData.insuredname = this.formSearch.insuredname
      this.changeSerchData.daterange = this.formSearch.daterange
      this.pendPageInfo.page = 1
      this.completePageInfo.page = 1
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      this.getList('01')
      this.getList('03')
    },
    resetForm(){
      this.$refs.searchForm.resetFields()
    },
    getList(no) {
      this.searchLaod = true
      let params = {}
      params.applicationsource = this.changeSerchData.applicationsource	|| ''
      params.acceptstartdate = this.changeSerchData.daterange ? this.changeSerchData.daterange[0] : ''
      params.acceptenddate = this.changeSerchData.daterange ? this.changeSerchData.daterange[1] : ''
      params.claimno = this.changeSerchData.claimno || ''
      params.eventdate = this.changeSerchData.eventdate || ''
      params.insuredname = this.changeSerchData.insuredname || ''
      params.idcardno = this.changeSerchData.idcardno || ''
      params.pageno = no === '01' ? this.pendPageInfo.page : this.completePageInfo.page
      params.pagesize = no === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      params.tabno = no
      params.flag = '01'
      console.log(params)
      getList(params).then(res =>{
        if (res.data) {
          if (res.data && res.data.errCode !== undefined || res.data && res.data.errCode !== '' ) {
            if(no === '01') {
              this.$message({message: '查询待处理结果错误！', type: 'error'});
            }else {
              this.$message({message: '查询已处理结果错误！', type: 'error'});
            }
          }
        } else {
          if(no === '01'){
            this.pendingTotal = res.total;
            this.pendingTableData = res.list;
          } else if (no === '03') {
            this.completedTotal = res.total;
            this.completedTableData = res.list;
          }
        }
        this.searchLaod = false
      })
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getList('01')
      } else {
        this.completePageInfo.pageSize = val
        this.getList('03')
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getList('01')
      } else {
        this.completePageInfo.page = val
        this.getList('03')
      }
    },
    // 切换tab页
    choceActiveName() {
      this.getList(this.activeName)
    },
    // 跳转至结案退件页面
    toCase() {
      this.$router.push({
        path :'/claims-handle/close-case',
        query: {
          status,
          showSelect: true
        }
      })
    },
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
