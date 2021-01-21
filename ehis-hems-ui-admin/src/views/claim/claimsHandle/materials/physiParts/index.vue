<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
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
              <el-select v-model="formSearch.applicationsource" style="width:200px;" clearable size="mini" placeholder="请选择">
                <el-option v-for="option in dict.apply_sourcetype"  :key="option.value" :label="option.label" :value="option.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                style="width: 220px;"
                v-model="formSearch.eventdate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredname">
              <el-input class="item-width" clearable v-model="formSearch.insuredname" size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input class="item-width" clearable v-model="formSearch.idcardno" size="mini" placeholder="请输入"
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
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案状态：" prop="status">
              <el-select v-model="formSearch.status" style="width:200px;" clearable size="mini" placeholder="请选择">
                <el-option v-for="option in dict.queue_claim_status"  :key="option.value" :label="option.label" :value="option.value" />
              </el-select>
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
<!--      <el-tabs v-model="activeName" @tab-click="choceActiveName">-->
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
import {getCommonList} from '@/api/claim/fillings.js'
import { get } from '@/api/dictDetail'
export default {
  components: {
    claimsTable
  },
  dicts: ['queue_claim_status','apply_sourcetype'],
  data() {
    return {
      formSearch: {
        daterange: [],
        claimno: '',
        applicationsource: '',
        eventdate: '',
        insuredname: '',
        idcardno: '',
        status: ''
      },
      activeName: '01',
      claimstate: [], // 赔案状态数组
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
      searchLoading: false
    }
  },
  mounted() {
    this.getClaimStatusData()
    this.getList('01')
    this.getList('03')
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
      this.changeSerchData.status = this.formSearch.status
      this.changeSerchData.claimno = this.formSearch.claimno
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.eventdate = this.formSearch.eventdate
      this.changeSerchData.insuredname = this.formSearch.insuredname
      this.changeSerchData.idcardno = this.formSearch.idcardno
      this.changeSerchData.daterange = this.formSearch.daterange
      this.pendPageInfo.page = 1
      this.completePageInfo.page = 1
      this.pendPageInfo.pageSize = 10
      this.completePageInfo.pageSize = 10
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
    // 获取列表
    getList(tabNo) {
      let params = {
        claimno: this.changeSerchData.claimno || '', //赔案号
        applicationsource: this.changeSerchData.applicationsource || '', //申请来源
        eventdate:this.changeSerchData.eventdate || '',//出险日期
        insuredname : this.changeSerchData.insuredname || '',//被保人姓名
        acceptstartdate: (this.changeSerchData.daterange!==undefined&&this.changeSerchData.daterange!==null&&this.changeSerchData.daterange.length===2) ? this.changeSerchData.daterange[0] : "", //提调日期开始时间
        acceptenddate: (this.changeSerchData.daterange!==undefined&&this.changeSerchData.daterange!==null&&this.changeSerchData.daterange.length===2) ? this.changeSerchData.daterange[1] : "", //提调日期结束时间
        idcardno : this.changeSerchData.idcardno || '',
        status : this.changeSerchData.status || '',
        pageno: tabNo==='01'?this.pendPageInfo.page:this.completePageInfo.page || 1,
        pagesize: tabNo==='01'?this.pendPageInfo.pageSize:this.completePageInfo.pageSize || 10,
        tabno: tabNo, //类型
      }
      this.searchLoading = true
      getCommonList(params).then(res =>{
        if (res.data) {
          if (res.data.errCode !== '' || res.data.errCode !== undefined) {
            if(tabNo === '01') {
              this.$message({message: '查询待处理结果错误！', type: 'error'});
            }else {
              this.$message({message: '查询已处理结果错误！', type: 'error'});
            }
          }
        } else {
          if(tabNo === '01'){
            this.pendingTableData = res.list || []
            // this.pendingTotal = res.list && res.list.length || 0
            this.pendingTotal = res.total
          } else if (tabNo === '03') {
            this.completedTableData = res.list || []
            // this.completedTotal = res.list && res.list.length || 0
            this.completedTotal = res.total
          }
        }
        this.searchLoading = false
      })
    },
    // 获取赔案状态
    getClaimStatusData(){
      const dictName = 'claim_status'
      get(dictName).then(response => {
        this.claimstate = response.content || []
      })
    },
    // 切换标签
    choceActiveName() {
      this.getList(this.activeName)
    },
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
