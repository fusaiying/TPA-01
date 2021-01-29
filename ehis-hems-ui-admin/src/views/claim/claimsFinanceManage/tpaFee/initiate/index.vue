<template>
  <div class="app-container">
    <!-- 查询条件 start -->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="任务号：" prop="rptNo">
              <el-input v-model="formSearch.rptNo" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司：" prop="source">
              <el-select v-model="formSearch.source" clearable class="item-width" placeholder="请选择">
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="建立日期：" prop="effectiveSDate">
              <el-date-picker  v-model="formSearch.effectiveSDate"  style="width:220px;"  size="mini"
                type="daterange" value-format="yyyy-MM-dd" placeholder="选择日期" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="结算类型：" prop="discType">
              <el-select v-model="formSearch.discType" clearable class="item-width" placeholder="请选择">
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="险种：" prop="discType">
              <el-select v-model="formSearch.discType" clearable class="item-width" placeholder="请选择">
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="结算日期：" prop="effectiveSDate">
              <el-date-picker  v-model="formSearch.effectiveSDate"  style="width:220px;"  size="mini"
                               type="daterange" value-format="yyyy-MM-dd" placeholder="选择日期" />
            </el-form-item>
          </el-col>

        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="primary"  @click="openDialog">清单导入</el-button>
          <el-button size="mini" type="primary"  @click="resetForm">发起结算</el-button>
          <el-button :loading="searchLoad" size="mini" type="success" icon="el-icon-search" @click="searchHandle" >查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <!-- 查询条件 end -->

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务费结算列表</span>
      </div>

      <!--服务费结算列表 start -->
      <feeTable  :table-data="initTableData" :status="status"  @openDetail="openDetail"/>
      <!--服务费结算列表 end-->

      <!--分页组件-->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="pendPageInfo.page"
        :limit.sync="pendPageInfo.pageSize"
        @pagination="initData"
      />

    </el-card>

    <!--详情 -->
    <fee-detail :value="detailDialog" :fixInfo="fixInfo" @closeDetailDialog="closeDetailDialog"/>

  </div>
</template>

<script>
  import feeTable from '../components/feeTable'
  import { PendingData,processedData } from '@/api/negotiation/api'
  import feeDetail from "../components/feeDetail";
  export default {
    components: {
      feeDetail,
      feeTable,
    },
    data() {
      return {

        fixInfo:{},
        detailDialog:false,
        status:'01',
        searchLoad:false,
        tableData: [],
        formSearch: {
          rptNo: '',
          source: '',
          name: '',
          discType: '',
          createBy: '',
        },
        initTableData: [],
        total: 0,
        pendPageInfo: {
          page: 1,
          pageSize: 10
        },
      }
    },
    mounted(){
      //协谈类型
      // this.getDicts("negotiation_type").then(response => {
      //   this.negotiationTypes = response.data;
      // });
    },
    created() {
      this.initData();
    },
    watch: {

    },
    computed: {
    },
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      searchHandle() {
        this.pendPageInfo.page = 1;
        this.pendPageInfo.pageSize = 10;
        this.total = 0;
        this.initData();
      },
      // 查询处理中
      initData() {

        this.searchLoad = true;
        const params = {};
        params.pageNum = this.pendPageInfo.page;
        params.pageSize = this.pendPageInfo.pageSize;

        PendingData(params).then(res => {
          if (res.code == '200') {
            this.total = res.total;
            this.initTableData = res.rows;
          }
          this.searchLoad = false
        });
        this.searchLoad = false
      },
      closeDetailDialog() {
        this.detailDialog = false
      },
      openDetail(info){
        console.log("info *****************")
        console.log(info)
        console.log("info *****************")
        this.fixInfo = info;
        this.detailDialog = true
      },
      openDialog(){
        this.detailDialog = true;
      },

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
