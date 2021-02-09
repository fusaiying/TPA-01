<template>
  <div class="app-container">
    <!-- 查询条件 start -->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="formSearch" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="任务号：" prop="settleTaskNo">
              <el-input v-model="formSearch.settleTaskNo" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司：" prop="companyCode">
              <el-select v-model="formSearch.companyCode" clearable class="item-width" placeholder="请选择">
                <el-option v-for="option in companySelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="建立日期：" prop="createTimeArr">
              <el-date-picker  v-model="formSearch.createTimeArr"  style="width:220px;"  size="mini"
                type="daterange" value-format="yyyy-MM-dd" placeholder="选择日期" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="结算类型：" prop="settlementType">
              <el-select v-model="formSearch.settlementType" clearable class="item-width" placeholder="请选择">
                <el-option v-for="option in settlementTypeSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="险种：" prop="riskCode">
              <el-select v-model="formSearch.riskCode" clearable class="item-width" placeholder="请选择">
                <el-option v-for="option in riskCodeSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="结算止期：" prop="settleEndDate">
              <el-date-picker  v-model="formSearch.settleEndDate"  style="width:220px;"  size="mini"
                               type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
            </el-form-item>
          </el-col>

        </el-row>
        <div style="text-align: right; margin-right: 10px;">
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

    <!--查看 -->
    <fee-detail :value="detailDialog" :fixInfo="fixInfo" @closeDetailDialog="closeDetailDialog"/>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>收款明细</span>
      </div>

      <!--收款明细 start-->
      <gatheringTable :table-data="gatherTableData" />
      <!--收款明细 end -->

      <pagination
        v-show="gatherTotal>0"
        :total="gatherTotal"
        :page.sync="gatherPageInfo.page"
        :limit.sync="gatherPageInfo.pageSize"
        @pagination="initGatherData"
      />

    </el-card>
  </div>
</template>

<script>
  import feeTable from '../components/feeTable'
  import gatheringTable from '../components/gatheringTable'

  import {companyList,riskList, listInfo } from '@/api/tpaFee/api'
  import feeDetail from "../components/feeDetail";
  export default {
    components: {
      feeDetail,
      feeTable,
      gatheringTable,
    },
    data() {
      return {

        fixInfo:{},
        detailDialog:false,
        status:'03',
        searchLoad:false,
        tableData: [],
        formSearch: {
          settleTaskNo: '',
          companyCode: '',
          createTimeArr: '',
          settlementType: '',
          riskCode:'',
          settleEndDate: '',
        },
        initTableData: [],
        total: 0,
        pendPageInfo: {
          page: 1,
          pageSize: 10
        },
        gatherTableData: [],
        gatherTotal: 0,
        gatherPageInfo: {
          page: 1,
          pageSize: 10
        },
        companySelect:[],
        settlementTypeSelect:[],
        riskCodeSelect:[],
      }
    },
    mounted(){
      //结算类型
      this.getDicts("accounttype").then(response => {
        this.settlementTypeSelect = response.data;
      });
    },
    created() {
      this.getRiskList('');
      this.getCompanyList();
      this.initData();
    },
    watch: {

    },
    computed: {
    },
    methods: {
      initGatherData(){

      },
      resetForm() {
        this.$refs.formSearch.resetFields()
      },
      searchHandle() {
        this.pendPageInfo.page = 1;
        this.pendPageInfo.pageSize = 10;
        this.total = 0;
        this.initData();
      },
      // 查询处理中
      initData() {

        let creationStartDate = '';
        let creationEndDate = '';
        /*
        settleTaskNo: '',
          companyCode: '',
          createTimeArr: '',
          settlementType: '',
          riskCode:'',
          settleDateArr: '',
        * */
        let createTimeArr = this.formSearch.createTimeArr;
        if('' != createTimeArr) {
          creationStartDate = createTimeArr[0];
          creationEndDate = createTimeArr[1];
        }
        const params = {};
        params.pageNum = this.pendPageInfo.page;
        params.pageSize = this.pendPageInfo.pageSize;
        params.settleTaskNo = this.formSearch.settleTaskNo;
        params.companyCode = this.formSearch.companyCode;
        params.creationStartDate = creationStartDate;
        params.creationEndDate = creationEndDate;
        params.settlementType = this.formSearch.settlementType;
        params.riskCode = this.formSearch.riskCode;
        params.settleEndDate = this.formSearch.settleEndDate;
        params.pageStatus = '03';

        this.searchLoad = true;
        listInfo(params).then(res => {
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
      getCompanyList(){
        const query ={
          pageNum:1,
          pageSize:10000,
          status:'Y',
          xtype:'companyList',
        };
        companyList(query).then(response => {
          if(response.rows) {
            for(let i=0; i<response.rows.length; i++) {
              let obj= new Object();
              obj.dictLabel = response.rows[i].companyname;
              obj.dictValue = response.rows[i].companycode.toString();
              this.companySelect.push(obj);
            }
          }
        }).catch(error => {
          console.log(error);
        })
      },

      getRiskList(companycode){
        this.formSearch.riskCode = '';
        this.riskCodeSelect = [];
        const query ={
          pageNum:1,
          pageSize:10000,
          status:'Y',
          xtype:'riskList',
          companycode:companycode,
        };
        riskList(query).then(response => {
          if(response.rows) {
            for(let i=0; i<response.rows.length; i++) {
              let obj= new Object();
              obj.dictLabel = response.rows[i].riskName;
              obj.dictValue = response.rows[i].riskCode.toString();
              this.riskCodeSelect.push(obj);
            }
          }
        }).catch(error => {
          console.log(error);
        })
      },
      companyChange(value){
        this.getRiskList(value);
      }

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
