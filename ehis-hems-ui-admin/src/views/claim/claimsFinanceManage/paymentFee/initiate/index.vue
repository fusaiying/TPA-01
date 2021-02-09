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
                   type="date" value-format="yyyy-MM-dd" placeholder="结算止期" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="建立日期：" prop="createTimeArr">
              <el-date-picker  v-model="formSearch.createTimeArr"  style="width:220px;"  size="mini"
                start-placeholder="开始日期" end-placeholder="结束日期"
                type="daterange" value-format="yyyy-MM-dd" placeholder="建立日期" />
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
          <el-button size="mini" type="primary"  @click="openImportDialog">清单导入</el-button>
          <el-button size="mini" type="primary"  @click="openDialog">发起任务</el-button>
          <el-button :loading="searchLoad" size="mini" type="success" icon="el-icon-search" @click="searchHandle" >查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <!-- 查询条件 end -->

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>交接任务列表({{total}})</span>
      </div>

      <!--交接任务列表 start -->
      <feeTable   :table-data="initTableData" :status="status"  @initData="initData" @openDetail="openDetail"/>
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

    <!--详情 -->
    <fee-detail :value="detailDialog" :fixInfo="fixInfo" @closeDetailDialog="closeDetailDialog" @openImportDialog="openImportDialog"/>

    <!--导入对话框 start -->
    <el-dialog :title="upload.title" :visible.sync="importDialog"
               :close-on-click-modal="true"
               :before-close="closeImportDialog"
               width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="closeImportDialog">取 消</el-button>
      </div>
    </el-dialog>
    <!--导入对话框 end -->

  </div>
</template>

<script>

  import moment from 'moment'
  import {companyList} from '@/api/tpaFee/api'
  import {deleteFinanceInfo, listInfo} from '@/api/paymentFee/api'
  import feeDetail from "../components/feeDetail";
  import feeTable from '../components/feeTable'
  import {getToken} from "@/utils/auth";
  export default {
    components: {
      feeDetail,
      feeTable,
    },
    data() {
      return {
        // 清单导入参数
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url:''// process.env.VUE_APP_BASE_API + "/system/user/importData"
        },
        fixInfo:{
          rowData:{},
          type:'',
        },
        importDialog:false,
        btnSearch:false,
        detailDialog:false,
        status:'01',
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
        //出单公司
        companySelect:[],
        //结算状态
        settleStatus:[],

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
        this.btnSearch = true;
        this.pendPageInfo.page = 1;
        this.pendPageInfo.pageSize = 10;
        this.total = 0;
        this.initData();
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

        if(!this.btnSearch) {
          params.pageType = '01';
          let startDate  = moment().subtract('month', 1).format('YYYY-MM-DD') + ' ' + '00:00:00'
          let endDate =  moment(new Date().getTime()).format('YYYY-MM-DD') + ' ' + '23:59:59'
          params.startDate = startDate;
          params.endDate = endDate;
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

        if(this.formSearch.settleEndDate == '' ) {
          this.$message.info('请录入结算止期！');
          return false;
        }
        if(this.formSearch.companyCode == ''){
          this.$message.info('请选择出单公司！');
          return false;
        }

        this.fixInfo = {
          rowData:{
            settleTaskNo:'',
            settleEndDate:this.formSearch.settleEndDate,
            companyCode:this.formSearch.companyCode,
          },
          type:'launch',
        },
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
      //导入弹框 start
      openImportDialog(){
        this.importDialog = true;
      },
      closeImportDialog() {
        this.importDialog = false
      },
      //导入弹框 end
      /** 提交上传文件 */
      submitFileForm() {
        this.$refs.upload.submit();
      },
      /** 下载模板操作 */
      importTemplate() {
        // this.download('provider/provider/importTemplate', {
        //   ...this.queryParams
        // }, `数据模板_${new Date().getTime()}.xlsx`)
      },
      /** 文件上传中处理 */
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      /** 文件上传成功处理 */
      handleFileSuccess(response, file, fileList) {
        // this.upload.open = false;
        // this.upload.isUploading = false;
        // this.$refs.upload.clearFiles();
        // this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        // this.getList();
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
