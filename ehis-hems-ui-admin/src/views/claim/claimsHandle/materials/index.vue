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
                @keyup.native.enter="searchHandle"
              />
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
            <el-form-item label="受理日期：" prop="applicationdate">
              <el-date-picker
                style="width: 220px;"
                v-model="formSearch.applicationdate"
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
    </el-card>
    <el-card style="margin-top:10px">
      <div slot="header" class="clearfix">
        <span>补材工作池</span>
      </div>
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
import { getList } from '@/api/claim/fillings'
/*import { get } from '@/api/dictDetail'*/
export default {
  dicts: ['claim_status','apply_sourcetype','queue_claim_status'],
  components: {
    claimsTable
  },
  data() {
    return {
      formSearch: {
        claimno: '',
        applicationsource: '',
        daterange: [],
        eventdate: '',
        insuredname: '',
        idcardno: '',
        applicationdate: [],
        status: ''
      },
      queryForm: {
        applicationdate: []
      },
      claimstate: [],
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
      searchLoading: false
    }
  },
  created(){
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
      this.pendPageInfo.page = 1,
      this.pendPageInfo.pagesize = 10,
      this.completePageInfo.page = 1,
      this.completePageInfo.pagesize = 10,
      this.queryForm.claimno = this.formSearch.claimno
      this.queryForm.applicationsource = this.formSearch.applicationsource
      this.queryForm.eventdate = this.formSearch.eventdate
      this.queryForm.insuredname = this.formSearch.insuredname
      this.queryForm.applicationdate = this.formSearch.applicationdate
      this.queryForm.idcardno = this.formSearch.idcardno
      this.queryForm.status = this.formSearch.status
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      //获取待处理和已处理列表
      this.getPublicpool("01")
      this.getPublicpool("03")
    },
    resetForm(){
      this.$refs.searchForm.resetFields()
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getPublicpool("01")
      } else {
        this.completePageInfo.pageSize = val
        this.getPublicpool("03")
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getPublicpool("01")
      } else {
        this.completePageInfo.page = val
        this.getPublicpool("03")
      }
    },
    getPublicpool(tabNo){
      let params = {
        claimno: this.queryForm.claimno, //赔案号
        applicationsource: this.queryForm.applicationsource, //申请来源
        eventdate:this.queryForm.eventdate,//出险日期
        insuredname : this.queryForm.insuredname,//被保人姓名
        acceptstartdate: (this.queryForm.applicationdate!==undefined&&this.queryForm.applicationdate!==null&&this.queryForm.applicationdate.length===2) ? this.queryForm.applicationdate[0] : "", //提调日期开始时间
        acceptenddate: (this.queryForm.applicationdate!==undefined&&this.queryForm.applicationdate!==null&&this.queryForm.applicationdate.length===2) ? this.queryForm.applicationdate[1] : "", //提调日期结束时间
        idcardno : this.queryForm.idcardno,
        status : this.queryForm.status,
        pageno: tabNo==='01'?this.pendPageInfo.page:this.completePageInfo.page,
        pagesize: tabNo==='01'?this.pendPageInfo.pageSize:this.completePageInfo.pageSize,
        tabno: tabNo, //类型
      };
      this.loading = true
      this.searchLoading = true
      getList(params).then(response => {
        if (response.data) {
          if (response.data.errCode !== '' || response.data.errCode !== undefined) {
            if(tabNo === '01') {
              this.$message({message: '查询待处理结果错误！', type: 'error'});
            }else {
              this.$message({message: '查询已处理结果错误！', type: 'error'});
            }
          }
        } else {
          if(tabNo === '01'){
            this.pendingTotal = response.total;
            this.pendingTableData = response.list;
          } else if (tabNo === '03') {
            this.completedTotal = response.total;
            this.completedTableData = response.list;
          }
        }
        this.searchLoading = false
      })
    },
    choceActiveName() {
      this.getPublicpool(this.activeName)
    },
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
