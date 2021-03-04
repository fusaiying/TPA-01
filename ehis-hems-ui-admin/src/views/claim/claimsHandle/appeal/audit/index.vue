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
            <el-form-item label="证件号码：" prop="idNo">
              <el-input v-model="formSearch.idNo" class="item-width" clearable size="mini" placeholder="请输入"
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
            <el-form-item label="操作日期：" prop="operateDate">
              <el-date-picker
                v-model="formSearch.operateDate"
                style="width:220px;"
                size="mini"
                type="daterange"
                value-format="yyyy-MM-dd"
                start-placeholder="开始日期" end-placeholder="结束日期"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="审核人：" prop="updateBy">
              <el-input v-model="formSearch.updateBy" class="item-width" clearable size="mini" placeholder="请输入"
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

    <!-- 申诉工作池  start  -->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>申诉工作池</span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <appealTable :claimTypes="claimTypes" :deliverySource="deliverySource"  :table-data="pendingTableData" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane  :label="`已处理(${completedTotal})`" name="03">
          <appealTable :claimTypes="claimTypes" :deliverySource="deliverySource" :table-data="completedTableData" :status="activeName"/>
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

import { PendingData,processedData } from '@/api/negotiation/api'
import { claimInfoList } from '@/api/appeal/api'

import moment from "moment";

let dictss = [{dictType: 'delivery_source'},{dictType: 'claimType'} , {dictType: 'claim_status'},{dictType: 'case_pay_status'}]

export default {
  dicts: ['delivery_source'],
  components: {
    appealTable,
  },
  data() {
    return {
      formSearch: {
        rptNo: '',
        source: '',
        idNo:'',
        name: '',
        operateDate: '',
        updateBy: '',
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
      deliverySource:[],
      claimStatus:[],
      dictList:[],
      claimTypes:[],
      searchBtn:false,
    }
  },
  async mounted(){

    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    //交单来源
    this.deliverySource = this.dictList.find(item => {
      return item.dictType === 'delivery_source'
    }).dictDate

    // 理赔类型
    this.claimTypes = this.dictList.find(item => {
      return item.dictType === 'claimType'
    }).dictDate

    // 案件状态
    this.claimStatus = this.dictList.find(item => {
      return item.dictType === 'claim_status'
    }).dictDate

  },
  created() {
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
      // this.searchBtn = true;
      // this.pendPageInfo.pageNum = 1;
      // this.pendPageInfo.pageSize = 10;
      // this.completePageInfo.pageNum = 1;
      // this.completePageInfo.pageSize = 10;
      // this.pendingTotal = 0;
      // this.completedTotal = 0;
      // this.getPendingData();
      // this.getProcessedData();
    },
    // 查询处理中
    getPendingData() {
      this.searchLoad = true;
      let startTime = "";
      let endTime = "";
      let operateDate = this.formSearch.operateDate;
      if('' != operateDate) {
        startTime = operateDate[0];
        endTime = operateDate[1];
      }
      const params = {};
      params.pageNum = this.pendPageInfo.page;
      params.pageSize = this.pendPageInfo.pageSize;
      params.rptNo = this.formSearch.rptNo;
      params.source = this.formSearch.source;
      params.idNo = this.formSearch.idNo;
      params.name = this.formSearch.name;
      params.createStartTime = startTime;
      params.createEndTime = endTime;
      params.updateBy = this.formSearch.updateBy;

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
      let startTime = "";
      let endTime = "";
      let operateDate = this.formSearch.operateDate;
      if('' != operateDate) {
        startTime = operateDate[0];
        endTime = operateDate[1];
      }
      const params = {};
      params.pageNum = this.completePageInfo.page;
      params.pageSize = this.completePageInfo.pageSize;
      params.rptNo = this.formSearch.rptNo;
      params.source = this.formSearch.source;
      params.idNo = this.formSearch.idNo;
      params.name = this.formSearch.name;
      params.createStartTime = startTime;
      params.createEndTime = endTime;
      params.updateBy = this.formSearch.updateBy;
      processedData(params).then(res => {
        if (res.code == '200') {
          this.completedTotal = res.total;
          this.completedTableData = res.rows;
        }
      })
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
