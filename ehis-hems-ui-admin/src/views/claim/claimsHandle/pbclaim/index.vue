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
            <el-form-item label="问题件类型：" prop="problemType">
              <el-select v-model="formSearch.problemType" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in problemTypes" :key="item.dictValue"  :label="item.dictLabel" :value="item.dictValue"/>
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
          <el-button :loading="searchLoad" size="mini" type="success" icon="el-icon-search" @click="searchHandle">查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重置</el-button>
          <!--<el-button size="mini" type="primary" icon="el-icon-refresh" @click="viewDetail">详情</el-button>-->
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>问题件工作池</span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable :claimStatusSelect="claimStatusSelect" :deliverySource="deliverySource" :problemTypes="problemTypes" :table-data="pendingTableData"  :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${ completedTotal })`" name="03">
          <claimsTable :claimStatusSelect="claimStatusSelect" :deliverySource="deliverySource" :problemTypes="problemTypes" :table-data="completedTableData" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
<!--      <pagination
        v-show="activeName==='01'?pendingTotal >0: completedTotal>0"
        :total="activeName==='01'?pendingTotal: completedTotal"
        :page.sync="activeName==='01'?pendPageInfo.pageNum:completePageInfo.pageNum"
        :limit.sync="activeName==='01'?pendPageInfo.pageSize:completePageInfo.pageSize"/>-->
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
  import { PendingData, processedData } from '@/api/pbclaim/api'

export default {
  // dicts: [ 'apply_sourcetype', 'negotiation_type','problem_shipment_type'],
  components: {
    claimsTable,
  },
  data() {
    return {
      searchBtn : false,
      searchLoad : false,
     // tableData: [],
      formSearch: {
        rptNo: '',
        source: '',
        name: '',
        problemType: '',
        updateBy: '',
        createBy:'',
      },

      activeName: '01',
      pendingTableData: [],
      completedTableData: [],

      pendingTotal: 0,
      completedTotal: 0,

      pendPageInfo: {
        pageNum:1,
        pageSize: 10
      },

      completePageInfo: {
        pageNum:1,
        pageSize: 10
      },

      problemTypes:[],
      deliverySource:[],
      claimStatusSelect:[],
    }
  },
  mounted(){
    //问题件类型
    this.getDicts("problem_shipment_type").then(response => {
      this.problemTypes = response.data;
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
    this.getAllData();
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
      let rptNo = this.formSearch.rptNo;
      let source = this.formSearch.source;
      let name = this.formSearch.name;
      let problemType = this.formSearch.problemType;
      let createBy = this.formSearch.createBy;

      this.searchLoad = true;
      const pendParams = {};
      pendParams.pageNum = this.pendPageInfo.pageNum;
      pendParams.pageSize = this.pendPageInfo.pageSize;
      pendParams.rptNo = rptNo;
      pendParams.source = source;
      pendParams.name = name;
      pendParams.problemType = problemType;
      pendParams.createBy = createBy;

      PendingData(pendParams).then(res => {
        if (res.code == '200') {
          this.pendingTotal = res.total;
          this.pendingTableData = res.rows;
        }
        this.searchLoad = false
      });
    },
    getProcessedData() {

      let rptNo = this.formSearch.rptNo;
      let source = this.formSearch.source;
      let name = this.formSearch.name;
      let problemType = this.formSearch.problemType;
      let createBy = this.formSearch.createBy;

      let dBefore1  = '';
      let dNow1 = '';
      if(!this.searchBtn) {
        let currentDate = new  Date();
        dNow1   = (this.dateformat('yyyy-MM-dd',currentDate))  +" 23:59:59";
        currentDate.setMonth(currentDate.getMonth() - 1);
        dBefore1 = this.dateformat('yyyy-MM-dd',currentDate);
      }


      const processeParams = {};
      processeParams.pageNum = this.completePageInfo.pageNum;
      processeParams.pageSize = this.completePageInfo.pageSize;
      processeParams.rptNo = rptNo;
      processeParams.source = source;
      processeParams.name = name;
      processeParams.problemType = problemType;
      processeParams.createBy = createBy;
      processeParams.dBefore1 = dBefore1;
      processeParams.dNow1 = dNow1;

      processedData(processeParams).then(res => {
        if (res.code == '200') {
          this.completedTotal = res.total;
          this.completedTableData = res.rows;
        }
      })
    },
    getAllData(){
      this.getPendingData();
      this.getProcessedData();
    },

    handleClick() {
      if (this.activeName === '01') {
        this.getPendingData()
      } else {
        this.getProcessedData()
      }
    },
    // sizeChange(val) {
    //   if (this.activeName === '01') {
    //     this.pendPageInfo.pageSize = val
    //     this.getPendingData()
    //   } else {
    //     this.completePageInfo.pageSize = val
    //     this.getProcessedData()
    //   }
    // },
    // pageChange(val) {
    //   if(val == undefined || val == null) {
    //     val = 1;
    //   }
    //   if (this.activeName === '01') {
    //     this.pendPageInfo.pageNum = val;
    //     this.getPendingData()
    //   } else {
    //     this.completePageInfo.pageNum = val;
    //     this.getProcessedData()
    //   }
    // },

    refreshTable() {
      this.searchHandle()
    },
    goBack() {
      this.$router.go(-1)
    },
    viewDetail(){
      this.$router.push({
        path: '/claims-handle/pbClaimDetail',
        query: {}

      })
    },
    dateformat(fmt,date) {
      let o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
      };
      if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
      }
      for(let k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
      }
      return fmt;
    },
  }
}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
