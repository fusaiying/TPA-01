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
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="协谈类型：" prop="discType">
              <el-select v-model="formSearch.discType" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in negotiationTypes" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="提交用户：" prop="createBy">
              <el-input v-model="formSearch.createBy" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button  size="mini" type="success" icon="el-icon-search" @click="searchHandle" >查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重置</el-button>
<!--          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="viewDetail">详情</el-button>-->
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>协谈工作池</span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable :negotiationTypes="negotiationTypes" :claimStatusSelect="claimStatusSelect" :deliverySource="deliverySource"  :table-data="pendingTableData" :claimno="claimno" :node="node" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane  :label="`已处理(${completedTotal})`" name="03">
          <claimsTable :negotiationTypes="negotiationTypes" :claimStatusSelect="claimStatusSelect" :deliverySource="deliverySource" :table-data="completedTableData" :claimno="claimno" :node="node" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
<!--      <el-pagination
        v-show="activeName==='01'?pendingTotal >0: completedTotal>0"
        :total="activeName==='01'?pendingTotal: completedTotal"
        :current-page="activeName==='01'?pendPageInfo.page:completePageInfo.page"
        :page-size="activeName==='01'?pendPageInfo.pageSize:completePageInfo.pageSize"
        :page-sizes="[5,10, 20, 30, 50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"/>-->
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
  </div>
</template>

<script>
import claimsTable from './components/claimsTable'
import { PendingData,processedData } from '@/api/negotiation/api'
export default {
  dicts: ['negotiation_mode', 'queue_claim_status', 'apply_sourcetype', 'negotiation_type'],
  name: 'Negotiation',
  components: {
    claimsTable,
  },
  data() {
    return {
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
      claimno: '',
      node: '',
      negotiationno: '',
      changeSerchData: {},
      deliverySource:[],
      claimStatusSelect:[],
      negotiationTypes:[],
    }
  },
  mounted(){
    //协谈类型
    this.getDicts("negotiation_type").then(response => {
      this.negotiationTypes = response.data;
    });
    //交单来源
    this.getDicts("delivery_source").then(response => {
      this.deliverySource = response.data;
    });
    //案件状态 claim_status
    this.getDicts("claim_status").then(response => {
      this.claimStatusSelect = response.data;
    });
  },
  created() {
    // this.getPendingData()
    // this.getProcessedData()
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
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {
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

      const params = {};// JSON.parse(JSON.stringify(this.changeSerchData))
      params.rptNo = rptNo;
      params.source = source;
      params.name = name;
      params.discType = discType;
      params.createBy = createBy;
      params.pageno = this.pendPageInfo.pageNum;
      params.pagesize = this.pendPageInfo.pageSize;
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

      const params = {

      };// JSON.parse(JSON.stringify(this.changeSerchData))
      params.rptNo = rptNo;
      params.source = source;
      params.name = name;
      params.discType = discType;
      params.createBy = createBy;
      params.pageno = this.completePageInfo.pageNum;
      params.pagesize = this.completePageInfo.pageSize;
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
    viewDetail(){
      this.$router.push({
        path: '/claims-handle/nagotDetail',
        query: {}

      })
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
