<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="rptNo">
              <el-input v-model="formSearch.rptNo" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交单来源：" prop="source">
              <el-select v-model="formSearch.source" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in deliverySource" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="证件号码：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="操作日期：" prop="effectiveSDate">
              <el-date-picker
                v-model="formSearch.effectiveSDate"
                style="width:220px;"
                size="mini"
                type="daterange"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="审核人：" prop="createBy">
              <el-input v-model="formSearch.createBy" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button  size="mini" type="success" icon="el-icon-search" @click="searchHandle" >查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>

    <!--案件工作池 start-->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>案件工作池</span>
      </div>
      <claimTable :table-data="claimTableData"/>
      <pagination
        v-show="claimTotal>0"
        :total="claimTotal"
        :page.sync="claimPageInfo.page"
        :limit.sync="claimPageInfo.pageSize"
        @pagination="initGatherData"
      />
    </el-card>
    <!--案件工作池 end -->

    <!-- 申诉工作池  start  -->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>申诉工作池</span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <appealTable  :deliverySource="deliverySource"  :table-data="pendingTableData" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane  :label="`已处理(${completedTotal})`" name="03">
          <appealTable  :deliverySource="deliverySource" :table-data="completedTableData" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <pagination
        v-if="activeName==='01'"
        v-show="pendingTotal>0"
        :total="pendingTotal"
        :page.sync="pendPageInfo.pageNum"
        :limit.sync="pendPageInfo.pageSize"
        @pagination="handleClick"
      />
      <pagination
        v-if="activeName==='03'"
        v-show="completedTotal>0"
        :total="completedTotal"
        :page.sync="completePageInfo.pageNum"
        :limit.sync="completePageInfo.pageSize"
        @pagination="handleClick"
      />
    </el-card>
    <!-- 申诉工作池  end  -->
  </div>
</template>

<script>
import appealTable from '../components/appealTable'
import claimTable from '../components/claimTable'

import { PendingData,processedData } from '@/api/negotiation/api'
import { collectionInfoList } from '@/api/paymentFee/api'

import moment from "moment";
export default {
  dicts: ['delivery_source'],
  components: {
    appealTable,
    claimTable,
  },
  data() {
    return {
      claimTableData: [],
      claimTotal: 0,
      claimPageInfo: {
        page: 1,
        pageSize: 10
      },
      tableData: [],
      formSearch: {
        rptNo: '',
        source: '',
        name: '',
        discType: '',
        createBy: '',
      },
      activeName: '01',
      pendingTableData: [],
      completedTableData: [],
      pendingTotal: 0,
      completedTotal: 0,
      pendPageInfo: {
        pageNum: 1,
        pageSize: 10
      },
      completePageInfo: {
        pageNum: 1,
        pageSize: 10
      },
      dialogVisible: false,
      orderno: '',
      negotiationno: '',
      changeSerchData: {},
      deliverySource:[],
      searchBtn:false,
    }
  },
  mounted(){
    //交单来源
    this.getDicts("delivery_source").then(response => {
      this.deliverySource = response.data;
    });
  },
  created() {
    this.initGatherData();
    this.getPendingData();
    this.getProcessedData();
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
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {
      this.searchBtn = true;
      this.pendPageInfo.pageNum = 1;
      this.pendPageInfo.pageSize = 10;
      this.completePageInfo.pageNum = 1;
      this.completePageInfo.pageSize = 10;
      this.pendingTotal = 0;
      this.completedTotal = 0;
      this.getPendingData();
      this.getProcessedData();
    },
    // 查询处理中
    getPendingData() {
      this.searchLoad = true;
      let rptNo = this.formSearch.rptNo ;
      let source = this.formSearch.source ;
      let name = this.formSearch.name ;
      let discType = this.formSearch.discType ;
      let createBy = this.formSearch.createBy ;

      const params = {};
      params.rptNo = rptNo;
      params.source = source;
      params.name = name;
      params.discType = discType;
      params.createBy = createBy;
      params.pageNum = this.pendPageInfo.pageNum;
      params.pageSize = this.pendPageInfo.pageSize;
      PendingData(params).then(res => {
        if (res.code == '200') {
          this.pendingTotal = res.total;
          this.pendingTableData = res.rows;
        }
        this.searchLoad = false
      });
      this.searchLoad = false
    },
    // 查询已处理
    getProcessedData() {
      let rptNo = this.formSearch.rptNo ;
      let source = this.formSearch.source ;
      let name = this.formSearch.name ;
      let discType = this.formSearch.discType ;
      let createBy = this.formSearch.createBy ;

      const params = {};
      params.rptNo = rptNo;
      params.source = source;
      params.name = name;
      params.discType = discType;
      params.createBy = createBy;
      params.pageNum = this.completePageInfo.pageNum;
      params.pageSize = this.completePageInfo.pageSize;

      let startTime = '';
      let endTime = '';
      // if (!this.searchBtn) {
      //   startTime = moment().subtract('month', 1).format('YYYY-MM-DD') + ' ' + '00:00:00'
      //   endTime = moment(new Date().getTime()).format('YYYY-MM-DD') + ' ' + '23:59:59'
      // }
      params.createStartTime = startTime;
      params.createEndTime = endTime;
      processedData(params).then(res => {
        if (res.code == '200') {
          this.completedTotal = res.total;
          this.completedTableData = res.rows;
        }
      })
    },
    initGatherData(){
      const params = {};
      params.pageNum = this.claimPageInfo.page;
      params.pageSize = this.claimPageInfo.pageSize;
      params.companyCode = this.formSearch.companyCode;
      collectionInfoList(params).then(res => {
        if (res.code == '200') {
          this.claimTotal = res.total;
          this.claimTableData = res.rows;
        }
      });
    },
    handleClick() {
      if (this.activeName === '01') {
        this.getPendingData()
      } else {
        this.getProcessedData()
      }
    },
    handleClose() {
      this.dialogVisible = false
    },
    refreshTable() {
      this.searchHandle()
    },
    goBack() {
      this.$router.go(-1)
    },
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
