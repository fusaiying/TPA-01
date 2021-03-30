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
              <el-select filterable v-model="formSearch.companyCode" clearable class="item-width" placeholder="请选择" @change="companyChange" >
                <el-option v-for="option in companySelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
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
            <el-form-item label="结算类型：" prop="settlementType">
              <el-select v-model="formSearch.settlementType" clearable class="item-width" placeholder="请选择">
                <el-option v-for="option in settlementTypeSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="险种：" prop="riskCode"> <!-- riskCodeSelect -->
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
          <el-button size="mini" type="primary"  @click="openImportDialog">清单导入</el-button>
          <el-button size="mini" type="primary"  @click="openDialog">发起结算</el-button>
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
      <feeTable  :table-data="initTableData" :status="status"  @openDetail="openDetail" @initData="initData"/>
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
    <fee-detail :value="detailDialog" :fixInfo="fixInfo" @closeDetailDialog="closeDetailDialog" @openImportDialog="openImportDialog"/>

    <!--导入对话框 -->
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
        <!--<div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport"/>
          是否更新已经存在的用户数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>-->
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="closeImportDialog">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {getToken} from "@/utils/auth"
  import feeTable from '../components/feeTable'
  import {companyList,riskList, listInfo } from '@/api/tpaFee/api'
  import feeDetail from "../components/feeDetail";
  import moment from "moment";
  export default {
    components: {
      feeDetail,
      feeTable,
     /* importTable,*/
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
          url:  process.env.VUE_APP_BASE_API + "/finance/tpaTask/importInitiate"
        },

        fixInfo:{},
        btnSearch:false,
        importDialog:false,
        detailDialog:false,
        status:'01',
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
        companySelect:[],
        settlementTypeSelect:[],
        riskCodeSelect:[],
        preStart:'',
        preEnd:'',
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
      resetForm() {
        this.$refs.formSearch.resetFields()
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
          if(dif > 3 && this.preStart !== createTimeStrt && this.preEnd !== createTimeEnd ) {
            this.preStart = createTimeStrt ;
            this.preEnd = createTimeEnd ;
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
        let creationStartDate = '';
        let creationEndDate = '';
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
        params.pageStatus = '01';
        this.searchLoad = true;

        if(!this.btnSearch) {
          params.pageType = '01';
          let startDate  = moment().subtract('month', 1).format('YYYY-MM-DD') + ' ' + '00:00:00'
          let endDate =  moment(new Date().getTime()).format('YYYY-MM-DD') + ' ' + '23:59:59'
          params.creationStartDate = startDate;
          params.creationEndDate = endDate;
        }
        listInfo(params).then(res => {
          if (res.code == '200') {
            this.total = res.total;
            this.initTableData = res.rows;
          }
          this.searchLoad = false
        });
      },
      //详情弹框 start
      openDetail(info){
        this.fixInfo = {
          rowData:info.row,
          type:info.type,
        };
        this.detailDialog = true
      },
      openDialog(){
        if(this.formSearch.companyCode == ''
          || this.formSearch.settleEndDate ==  ''
          || this.formSearch.settlementType ==  '') {
          this.$message.warning('请录入出单公司、结算方式、结算止期后发起结算！');
          return false;
        }
        this.fixInfo = {
          rowData:{
            settleTaskNo:'',
            settlementType:this.formSearch.settlementType,
            riskCode:this.formSearch.riskCode,
            settleEndDate:this.formSearch.settleEndDate,
            companyCode:this.formSearch.companyCode,
          },
          type:'launch',
        },
        this.detailDialog = true;
      },
      closeDetailDialog() {
        this.detailDialog = false
      },
      //详情弹框 end
      //导入弹框 start
      openImportDialog(){
        this.importDialog = true;
      },
      closeImportDialog() {
        this.$refs.upload.clearFiles()
        this.upload.isUploading = false;
        this.importDialog = false
      },
      //导入弹框 end
      /** 提交上传文件 */
      submitFileForm() {
        this.$refs.upload.submit();
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
