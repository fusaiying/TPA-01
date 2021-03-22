<!-- 服务结算查询 -->
<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form :model="queryParams" ref="queryForm" label-width="130px" label-position="right" size="mini">
        <!-- 第一行 -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="supplierCode">
              <el-select v-model="queryParams.supplierCode" class="item-width" size="mini" placeholder="请选择" @change="changeSupplier" clearable filterable>
                <el-option v-for="item in supplierOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="供应商项目名称：" prop="serviceCode">
              <el-select v-model="queryParams.serviceCode" class="item-width" size="mini" placeholder="请选择" clearable @change="setSupplierServiceName" filterable>
                <el-option v-for="item in serviceOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="结算方式：" prop="settleType">
              <el-select v-model="queryParams.settleType" class="item-width" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in clearingFormOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="结算止期：" prop="endDate">
              <el-date-picker
                class="item-width"
                v-model="queryParams.endDate"
                type="date"
                :picker-options="dateOptions"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus">
              <el-select v-model="queryParams.bussinessStatus" class="item-width" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in balanceStatusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <div style="text-align: right; margin-right: 10px;">
              <el-button size="mini" type="success" icon="el-icon-search" @click="handleQuery">查 询</el-button>
              <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetQuery">重 置</el-button>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>服务费结算列表（{{ total }}）</span>
          <span style="float: right;">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">发起结算</el-button>
            <el-upload
              class="upload-demo"
              :headers="upload.headers"
              :action="upload.url"
              :disabled="upload.isUploading"
              :show-file-list="false"
              :on-success="uploadSuccess"
              :before-upload="beforeUpload"
              :on-progress="uploadProgress"
              accept=".xls,.xlsx"
              title="提示：仅允许导入文件名为任务号，且“xls”或“xlsx”格式文件！"
              style="display: initial; margin: 0px 10px;"
            >
              <el-button size="mini" type="primary">清单导入</el-button>
            </el-upload>
          </span>
        </div>
        <!-- 表格区域 START -->
        <el-table
          :data="balanceList"
          v-loading="loading"
          size="mini"
          :cell-style="changeCellStyle"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          highlight-current-row
          :default-sort = "{prop: 'taskNo', order: 'descending'}"
        >
          <el-table-column sortable prop="taskNo" label="任务号" width="120%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link style="font-size: 11px" type="primary" @click="handelDetail(scope.row)">{{scope.row.taskNo}}</el-link>
            </template>
          </el-table-column>
          <el-table-column prop="supplierCode" label="供应商名称" width="120%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(supplierOptions, scope.row.supplierCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="serviceCode" label="供应商项目名称" width="120%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(serviceData, scope.row.serviceCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="settleType" label="结算方式" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(clearingFormOptions, scope.row.settleType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="settleCurrency" label="结算币种" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(currencyOptions, scope.row.settleCurrency)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="endDate" label="结算止期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="balanceInvoiceType" label="发票类型" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(balanceInvoiceTypeOptions, scope.row.balanceInvoiceType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="serviceAmount" label="服务费总金额" width="120%" align="center" show-overflow-tooltip/>
          <el-table-column prop="bussinessStatus" label="状态" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(balanceStatusOptions, scope.row.bussinessStatus)}}{{handleRemark(scope.row.remark)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" fixed="right" class-name="small-padding fixed-width" width="180px">
            <template slot-scope="scope">
              <!-- 01-待确认，03-待确认（退回） -->
              <el-button v-if="scope.row.bussinessStatus==='01' || scope.row.bussinessStatus==='03'" size="mini" type="text" icon="el-icon-download" style="padding: 0px;" @click="handleExport(scope.row)">下载</el-button>
              <!-- 01-待确认，03-待确认（退回） -->
              <el-button v-if="scope.row.bussinessStatus==='01' || scope.row.bussinessStatus==='03'" size="mini" type="text" icon="el-icon-check" style="padding: 0px;" @click="handleConfirm(scope.row)">确认</el-button>
              <!-- 01-待确认，03-待确认（退回） -->
              <el-button v-if="scope.row.bussinessStatus==='01' || scope.row.bussinessStatus==='03'" size="mini" type="text" icon="el-icon-delete" style="padding: 0px;" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 表格区域 END -->
        <!--分页组件-->
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import moment from 'moment';
import { atLeastOne2 } from '@/utils/commenMethods.js';
import {listBalance2, updateBalance} from "@/api/claim/serviceBalance";
import {
  getAllBaseSupplierInfo,
  getContractServerList
} from '@/api/contractManage/contractManagement';
import {getToken} from "@/utils/auth";

let dictss = [{dictType: 'currency'},{dictType: 'balance_invoice_type'}, {dictType: 'clearing_form'}, {dictType: 'balance_status_settlement'}]
export default {
  name: "startBbalance",
  watch: {

  },
  data() {
    return {
      // 遮罩层
      loading: false,
      // 是否默认一个月查询
      isDefault: true,
      // 总条数
      total: 0,
      // 服务结算表格数据
      balanceList: [],
      // 查询参数
      queryParams: {
        params: {
          queryType:"startBalance",
        },
        //数据状态
        status: "Y",
        //页码
        pageNum: 1,
        //条数
        pageSize: 10,
        //供应商
        supplierCode: null,
        //供应商服务项目
        serviceCode: null,
        //供应商项目名称
        supplierServiceName:null,
        //结算方式
        settleType: null,
        //结算止期
        endDate: null,
        //状态
        bussinessStatus: null
      },
      //上传文件
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/order/balanceDetail/importData"
      },
      //日期限制
      dateOptions: {
        disabledDate(time) {
          return time >= Date.now();
        }
      },
      //字典
      dictList: [],
      //供应商
      supplierOptions: [],
      //供应商服务项目—all
      serviceData: [],
      //供应商服务项目
      serviceOptions: [],
      //币种
      currencyOptions: [],
      //发票类型
      balanceInvoiceTypeOptions: [],
      //结算方式
      clearingFormOptions: [],
      //结算业务状态
      balanceStatusOptions: []
    };
  },
  created() {
    this.getList();

  },
  async mounted() {
    //初始化
    this.initData();
    //初始化字典
    await this.getDictsList(dictss).then(res => {
      this.dictList = res.data
    })
    this.currencyOptions = this.dictList.find(item => {
      return item.dictType === 'currency'
    }).dictDate
    this.balanceInvoiceTypeOptions = this.dictList.find(item => {
      return item.dictType === 'balance_invoice_type'
    }).dictDate
    this.clearingFormOptions = this.dictList.find(item => {
      return item.dictType === 'clearing_form'
    }).dictDate
    this.balanceStatusOptions = this.dictList.find(item => {
      return item.dictType === 'balance_status_settlement'
    }).dictDate
  },
  methods: {
    //给供应商服务项目名称赋值
    setSupplierServiceName(){
      if(this.queryParams.serviceCode!=null && this.queryParams.serviceCode!='') {
        let arr = this.serviceOptions.filter(item => {
          return item.dictValue == this.queryParams.serviceCode
        })
        this.queryParams.supplierServiceName = arr[0].dictLabel
      }
      else {
        this.queryParams.supplierServiceName=null
      }
    },

    initData (){
      /** 供应商 */
      const query ={
        status:'Y'
      };
      getAllBaseSupplierInfo(query).then(res => {
        if(res.data != null) {
          for(let i=0; i<res.data.length; i++) {
            let obj= new Object();
            obj.dictLabel = res.data[i].chname + " - " + res.data[i].enname;
            obj.dictValue = res.data[i].serialNo;
            this.supplierOptions.push(obj);
          }
        }
      }).catch(error => {
        console.log(error);
      });
      /** 供应商项目 */
      getContractServerList(query).then(res => {
        if (res.rows != null) {
          for(let i=0; i<res.rows.length; i++) {
            let obj= new Object();
            obj.dictLabel = res.rows[i].supplierServiceName;
            obj.dictValue = res.rows[i].serialNo;
            obj.settleType = res.rows[i].settleType;
            obj.supplierCode = res.rows[i].supplierCode;
            this.serviceData.push(obj);
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    /** 供应商事件 */
    changeSupplier() {
      this.queryParams.serviceCode = "";
      this.serviceOptions = this.serviceData.filter(item => {
        return item.supplierCode === this.queryParams.supplierCode;
      });
    },
    /** 查询服务结算列表 */
    getList() {
      let query=JSON.parse(JSON.stringify(this.queryParams));
      if (this.isDefault) {
          query.beginTime = moment().subtract(30,"days").format('YYYY-MM-DD');
          query.endTime = moment().format('YYYY-MM-DD');

      } else {
        query = this.queryParams;
      }
      this.loading = true;
      listBalance2(query).then(res => {
        if (res != null && res.code === 200) {
          this.balanceList = res.rows;
          this.total = res.total;
          this.loading = false;
          if (res.rows.length < 1) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {
        this.loading = false
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      let query = JSON.parse(JSON.stringify(this.queryParams))
      const flag = atLeastOne2(query,["params","status","pageNum","pageSize"]);
      if(flag){
        this.isDefault = false
      }
      else {
        this.isDefault = true
      }
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.isDefault = true;
      this.resetForm("queryForm");
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10
      this.queryParams.supplierServiceName=null
      this.getList();
    },
    //处理备注
    handleRemark(remark) {
      if (remark) {
        return "(" + remark + ")";
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push({
        path: '/implementation/service-balance/balance-start-edit'
      });
    },
    /** 明细操作 */
    handelDetail(row) {
      this.$router.push({
        path: '/implementation/service-balance/balance-detail',
        query: {
          taskNo: row.taskNo,
          supplierCode: row.supplierCode,
          settleType: row.settleType,
          bussinessStatus: row.bussinessStatus
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除任务号为"' + row.taskNo + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        const params = {
          params: {type: "DEL"},
          taskNo: row.taskNo
        };
        updateBalance(params).then(res => {
          if (res != null && res.code === 200) {
            this.msgSuccess("删除成功！")
            this.handleQuery();
          }
        }).catch(res => {
          this.msgError('删除失败！')
        });
      }).catch(() => {
        this.msgInfo("已取消！")
      });
    },
    /** 清单确认 */
    handleConfirm(row) {
      if (row.isImport !== "Y") {
        this.$message.warning('请先导入清单！')
        return;
      }
      this.$router.push({
        path: '/implementation/service-balance/balance-start-confirm',
        query: {
          taskNo: row.taskNo,
          supplierCode: row.supplierCode,
          settleType: row.settleType,
          balanceInvoiceType: row.balanceInvoiceType,
          serviceAmount:row.serviceAmount
        }
      });
    },
    /** 清单下载 */
    handleExport(row) {
      const params = {
        taskNo: row.taskNo,
        settleType: row.settleType,
        status: "Y"
      };
      this.download('order/balanceDetail/export', params, "balance_" + row.taskNo + '_' + new Date().getTime() + '.xlsx');
    },
    /** 导入成功后事件 */
    uploadSuccess(response, file, fileList){
      this.upload.isUploading = false;
      if (response != null && response.code === 200) {
        this.getList();
        this.msgSuccess('导入成功！');
      } else {
        this.$message({
          message: response.msg,
          type: 'warning',
          showClose: true,
          duration: 5000
        })
      }
    },
    /** 上传前 */
    beforeUpload(file) {
      const fileName = file.name.substring(file.name.lastIndexOf('.')+1);
      //excel/jpg/png/pdf
      if (fileName === "xls" || fileName === "xlsx") {
        return true;
      } else {
        this.$message.warning('只能上传excel文件！');
        return false;
      }
    },
    /** 上传中 */
    uploadProgress() {
      this.upload.isUploading = true;
    },
    //退回状态行飘红
    changeCellStyle (rows, column, rowIndex, columnIndex) {
      if(rows.row.bussinessStatus === "03"){
        return 'color: red'  // 修改的样式
      }else{
        return ''
      }
    },

  }
};
</script>

<style scoped>
  /*自定义样式*/
  .receive_table {
    width: 98%;
    padding: 0px;
    margin-left: 1%;
    margin-right: 1%;
  }

  .item-width {
    width: 220px;
  }
</style>
