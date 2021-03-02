<template>
  <div class="app-container">
    <!-- 查询条件 start -->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>交接案件提取</span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="任务号：" prop="settleTaskNo">
              <el-input v-model="formSearch.settleTaskNo" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="结算止期：" prop="settleEndDate">
              <el-date-picker  v-model="formSearch.settleEndDate"  style="width:220px;"  size="mini"
                               type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="建立日期：" prop="createTimeArr">
              <el-date-picker  v-model="formSearch.createTimeArr"  style="width:220px;"  size="mini"
                       start-placeholder="开始日期" end-placeholder="结束日期"
                       type="daterange" value-format="yyyy-MM-dd" placeholder="选择日期" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="付款状态：" prop="settleStatus">
              <el-select v-model="formSearch.settleStatus" clearable class="item-width" placeholder="请选择">
                <el-option v-for="option in settleStatus" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="批次总金额：" prop="batchTotal">
              <el-input v-model="formSearch.batchTotal" class="item-width" clearable size="mini" placeholder="请输入"
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
        <span>交接任务列表</span>
      </div>

      <!--交接任务列表 start -->
      <feeTable  :table-data="initTableData" :status="status" @checkBoxVue="checkBoxVue"  @openDetail="openDetail"/>
      <!--交接任务列表 end-->

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
        <el-button style="float: right; margin-right: 10px;" type="primary" size="mini" @click="dealFun">核销
        </el-button>
      </div>

      <!--收款明细 start-->
      <gatheringTable :table-data="gatherTableData" @radioVue="radioVue"/>
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


  import {companyList} from '@/api/tpaFee/api'
  import {listInfo, collectionInfoList, updateSettleEndStatus, updateConfirm} from '@/api/paymentFee/api'

  import feeDetail from "../components/feeDetail"
  import feeTable from '../components/feeTable'
  import gatheringTable from '../components/gatheringTable'
  import moment from "moment";

  export default {
    components: {
      feeDetail,
      feeTable,
      gatheringTable,
    },
    data() {
      return {
        btnSearch:false,
        fixInfo:{},
        detailDialog:false,
        status:'03',
        searchLoad:false,
        tableData: [],
        formSearch: {
          settleTaskNo: '',
          settleEndDate: '',
          createTimeArr: '',
          settleStatus: '',
          batchTotal: '',
          companyCode: '',
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
        //出单公司
        companySelect:[],
        //结算状态
        settleStatus:[],
        // 复选框任务号
        taskNoList:[],
        //收款明显单选框 实收ID
        collectionId:''
      }
    },
    mounted(){
      //结算状态
      this.getDicts("tpa_settle_status").then(response => {
        this.settleStatus = response.data;
      });
    },
    created() {
      this.getCompanyList();
      this.initData();
      this.initGatherData();
    },
    watch: {

    },
    computed: {
    },
    methods: {
      radioVue(value){
        this.collectionId = value;
      },
      checkBoxVue(checkVue){
        this.taskNoList = checkVue;
      },
      dealFun(){
        if(this.taskNoList.length === 0) {
          this.$message({ type: 'info',  message: '请选择需要进行核销的任务!'});
          return false;
        }
        if(this.collectionId === '') {
          this.$message({ type: 'info',  message: '请选择收款明细!'});
          return false;
        }
        //updateSettleEndStatus
        let settleTaskNo  = this.taskNoList;
        this.$confirm('确定核销', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          updateSettleEndStatus(settleTaskNo).then(response => {
            if(response.code == '200') {
              this.initData();
              this.initGatherData();
              this.$message({
                type: 'success',
                message: '核销成功!'
              });
            } else {
              this.$message({
                type: 'info',
                message: '核销失败'
              });
            }
          }).catch(error => {
            console.log(error);
          })
        }).catch(() => {
        })
      },
      initGatherData(){
        const params = {};
        params.pageNum = this.gatherPageInfo.page;
        params.pageSize = this.gatherPageInfo.pageSize;
        params.companyCode = this.formSearch.companyCode;
        collectionInfoList(params).then(res => {
          if (res.code == '200') {
            this.gatherTotal = res.total;
            this.gatherTableData = res.rows;
          }
        });
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      searchHandle() {
        this.btnSearch = true;
        this.pendPageInfo.page = 1;
        this.pendPageInfo.pageSize = 10;
        this.total = 0;
        this.initData();
        if(this.formSearch.companyCode != '') {
          this.initGatherData();
        }
      },
      // 查询处理中
      initData() {
        let createTimeStrt = '';
        let createTimeEnd = '';
        let createTimeArr = this.formSearch.createTimeArr;
        if('' != createTimeArr) {
          createTimeStrt = createTimeArr[0];
          createTimeEnd = createTimeArr[1];
          let entime = moment(createTimeStrt)
          let letime = moment(createTimeEnd)
          let dif = letime.diff(entime, 'months')
          if(dif > 3) {
            // 时间跨度太长，是否确认
            this.$confirm('时间跨度太长，是否确认', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              this.getData();
            }).catch(() => {
            })
          } else {
            this.getData();
          }
        } else {
          this.getData();
        }
      },
      getData(){
        let createTimeStrt = '';
        let createTimeEnd = '';
        let createTimeArr = this.formSearch.createTimeArr;
        if('' != createTimeArr) {
          createTimeStrt = createTimeArr[0] + " 00:00:00";
          createTimeEnd = createTimeArr[1];
        }
        const params = {};
        params.pageNum = this.pendPageInfo.page;
        params.pageSize = this.pendPageInfo.pageSize;

        params.settleTaskNo = this.formSearch.settleTaskNo;
        params.settleEndDate = this.formSearch.settleEndDate;
        params.startDate = createTimeStrt;
        params.endDate = createTimeEnd;
        params.settleStatus = this.formSearch.settleStatus;
        params.batchTotal = this.formSearch.batchTotal;
        params.companyCode = this.formSearch.companyCode;

        if(this.btnSearch) {
          params.pageType = '03';
        } else {
          params.settleStatus = '02';
        }
        listInfo(params).then(res => {
          if (res.code == '200') {
            this.total = res.total;
            this.initTableData = res.rows;
          }
        });
      },
      closeDetailDialog() {
        this.detailDialog = false
      },
      openDetail(info){
        this.fixInfo = {
          rowData:info.row,
          type:info.type,
        };
        this.detailDialog = true
      },
      openDialog(){
        this.detailDialog = true;
      },
      //出单公司
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

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
