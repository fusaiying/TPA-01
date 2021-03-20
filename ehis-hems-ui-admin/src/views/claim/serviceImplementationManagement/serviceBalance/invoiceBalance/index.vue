<!-- 发票核销查询 -->
<template>
  <div class="app-container">
    <!-- 发票核销列表 START -->
    <el-card class="box-card">
      <el-form :model="queryParams" ref="queryForm" label-width="130px" label-position="right" size="mini">
        <!-- 第一行 -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="supplierCode">
              <el-select v-model="queryParams.supplierCode" class="item-width" size="mini" placeholder="请选择" clearable filterable>
                <el-option v-for="item in supplierOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="地区代码：" prop="areaCode">
              <el-input class="item-width" clearable v-model="queryParams.areaCode" size="mini" placeholder="H+区域代码6位" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="公司段：" prop="manageCom">
              <el-input class="item-width" clearable v-model="queryParams.manageCom" size="mini" placeholder="地区代码+4位" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="支付流水号：" prop="payNo">
              <el-input class="item-width" clearable v-model="queryParams.payNo" size="mini" placeholder="请输入" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="任务号：" prop="taskNo">
              <el-input class="item-width" clearable v-model="queryParams.taskNo" size="mini" placeholder="请输入" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="支付时间：" prop="payDate">
              <el-date-picker
                class="item-width"
                v-model="queryParams.payDate"
                type="date"
                :picker-options="dateOptions"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="状态：" prop="invoiceStatus">
              <el-select v-model="queryParams.invoiceStatus" class="item-width" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in invoiceStatusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <div style="text-align: right; margin-right: 10px;">
            <el-button size="mini" type="success" icon="el-icon-search" @click="handleQuery">查 询</el-button>
            <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetQuery">重 置</el-button>
          </div>
        </el-row>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>发票核销列表（{{ total }}）</span>
        </div>
        <!-- 表格区域 START -->
        <el-table
          :data="invoiceWriteList"
          ref="invoiceWriteTable"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          highlight-current-row
          @row-click="handelDetail"
          :default-sort = "{prop: 'invoiceStatus', order: 'ascending'}"
        >
          <el-table-column prop="taskNo" label="任务号" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="areaCode" label="地区代码" align="center" show-overflow-tooltip />
          <el-table-column prop="manageCom" label="公司段" align="center" show-overflow-tooltip />
          <el-table-column prop="payNo" label="支付流水号" align="center" show-overflow-tooltip />
          <el-table-column prop="payDate" label="支付时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.payDate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="payAmount" label="支付金额" align="center" show-overflow-tooltip/>
          <el-table-column sortable prop="invoiceStatus" label="状态" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(invoiceStatusOptions, scope.row.invoiceStatus)}}</span>
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
    <!-- 发票核销列表 END -->

    <!-- 发票信息列表 START -->
    <balance-invoice-detail ref="balanceInvoiceTable" :params="invoice" :dictList="dictList"/>
    <!-- 发票信息列表 END -->
  </div>
</template>

<script>
import moment from 'moment';
import balanceInvoiceDetail from './../components/balanceInvoiceDetail'

import {listBalance2,listBalance2Default} from "@/api/claim/serviceBalance";
import {getAllBaseSupplierInfo} from '@/api/contractManage/contractManagement';
import {atLeastOne2} from "@/utils/commenMethods";

let dictss = [{dictType: 'invoice_status'},{dictType: 'balance_invoice_type2'},{dictType: 'invoice_auth'}]
export default {
  name: "invoiceBalance",
  watch: {

  },
  components: {
    balanceInvoiceDetail
  },
  data() {
    return {
      isDefault: true,
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 发票核销列表数据
      invoiceWriteList: [],
      // 查询参数
      queryParams: {
        params: {
          queryType:"invoiceBalance",
        },
        //数据状态
        status: "Y",
        //页码
        pageNum: 1,
        //条数
        pageSize: 10,
        //供应商
        supplierCode: null,
        //地区代码
        areaCode: null,
        //公司段
        manageCom: null,
        //支付流水号
        payNo: null,
        //任务号
        taskNo: null,
        //支付时间
        payDate: null,
        //状态
        invoiceStatus: null
      },
      //发票信息
      invoice: {
        //是否显示
        isShow: false,
        //是否核销
        isWrite: false,
        //是否为添加
        isAdd: false,
        //任务号
        taskNo: null,
        //支付金额
        payAmount: null
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
      //状态
      invoiceStatusOptions: [],
    };
  },
  created() {
    this.getListDefault();

  },
  async mounted() {
    //初始化供应商
    this.allBaseSupplierInfo();

    //初始化字典
    await this.getDictsList(dictss).then(res => {
      this.dictList = res.data
    })
    this.invoiceStatusOptions = this.dictList.find(item => {
      return item.dictType === 'invoice_status'
    }).dictDate
  },
  methods: {
    /** 供应商 */
    allBaseSupplierInfo (){
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
      })
    },
    /** 设置核销状态 */
    setInvoiceStatus(taskNo) {
      const row = this.invoiceWriteList.find(item => {
        return item.taskNo === taskNo
      });
      if (row) {
        row.invoiceStatus = "Y";
      }
    },
    /** 查询服务结算列表 */
    getList() {
      //默认查询
      if(this.isDefault){
        this.getListDefault()
      }
      else {
        this.loading = true;
        listBalance2(this.queryParams).then(res => {
          if (res != null && res.code === 200) {
            this.invoiceWriteList = res.rows;
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
      }




    },
    /** 查询服务结算列表 默认*/
    getListDefault() {
      this.loading = true;
      listBalance2Default(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.invoiceWriteList = res.rows;
          this.total = res.total;
          this.loading = false;
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
      this.resetInvoice();
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.isDefault=true
      this.resetInvoice();
      this.resetForm("queryForm");
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10;
      this.getListDefault();
    },
    /** 重置发票信息 */
    resetInvoice() {
      this.invoice = {
        //是否显示
        isShow: false,
        //是否核销
        isWrite: false,
        //是否为添加
        isAdd: false,
        //任务号
        taskNo: null
      };
    },
    /** 明细操作 */
    handelDetail(row) {
      this.resetInvoice();
      this.invoice.isShow = true;
      //先票后付-正常流程是不需要发票核销
      //有票
      if (row.balanceInvoiceType == "01") {
        //确认前（01-待确认，03-待确认（退回））可修改，其他时候只能查看
        if (row.bussinessStatus == "01" || row.bussinessStatus == "03") {
          this.invoice.isWrite = true;
        }
      } else {//无票
        //未核销可添加
        if (row.invoiceStatus == "N") {
          this.invoice.isAdd = true;
          this.invoice.isWrite = true;
        }
      }
      //确认前（04-已审核（待核销），05-核销 可发票核销
      if (row.bussinessStatus == "04" || row.bussinessStatus == "05") {
        //未核销可添加
        if (row.invoiceStatus == "N") {
          this.invoice.isWrite = true;
          this.invoice.isWrite = true;
        }
      }
      this.invoice.taskNo = row.taskNo;
      //支付金额
      this.invoice.payAmount = row.payAmount;
    }

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
