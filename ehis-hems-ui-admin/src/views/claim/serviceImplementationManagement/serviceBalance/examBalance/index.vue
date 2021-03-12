<!-- 结算审核查询 -->
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
              <el-select v-model="queryParams.serviceCode" class="item-width" size="mini" placeholder="请选择" clearable filterable>
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
        </div>
        <!-- 表格区域 START -->
        <el-table
          :data="balanceList"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          highlight-current-row
          :default-sort = "{prop: 'taskNo', order: 'descending'}"
        >
          <el-table-column sortable prop="taskNo" label="任务号" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="supplierCode" label="供应商名称" width="150%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(supplierOptions, scope.row.supplierCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="serviceCode" label="供应商项目名称" width="150%" align="center" show-overflow-tooltip>
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
          <el-table-column prop="serviceAmount" label="服务总金额" align="center" show-overflow-tooltip/>
          <el-table-column prop="bussinessStatus" label="状态" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(balanceStatusOptions, scope.row.bussinessStatus)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" fixed="right" class-name="small-padding fixed-width" width="120px">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-download" style="padding: 0px;" @click="handleExport(scope.row)">下载</el-button>
              <el-button size="mini" type="text" icon="el-icon-circle-check" style="padding: 0px;" @click="handleExam(scope.row)">审核</el-button>
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
import {listBalance2} from "@/api/claim/serviceBalance";
import {
  getAllBaseSupplierInfo,
  getContractServerList
} from '@/api/contractManage/contractManagement';

let dictss = [{dictType: 'currency'}, {dictType: 'clearing_form'},{dictType: 'closing_balance_status'}]
export default {
  name: "examBalance",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 服务结算表格数据
      balanceList: [],
      // 查询参数
      queryParams: {
        params: {
          queryType:"examBalance",
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
        //结算方式
        settleType: null,
        //结算止期
        endDate: null,
        //状态
        bussinessStatus: null
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
      //供应商服务项目-all
      serviceData: [],
      //供应商服务项目
      serviceOptions: [],
      //币种
      currencyOptions: [],
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
    this.clearingFormOptions = this.dictList.find(item => {
      return item.dictType === 'clearing_form'
    }).dictDate

    this.balanceStatusOptions = this.dictList.find(item => {
      return item.dictType === 'closing_balance_status'
    }).dictDate
  },
  methods: {
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
      this.loading = true;
      listBalance2(this.queryParams).then(res => {
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
      });;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
    },
    /** 清单审核 */
    handleExam(row) {
      this.$router.push({
        path: '/implementation/service-balance/balance-exam-edit',
        query: {
          taskNo: row.taskNo,
          supplierCode: row.supplierCode,
          settleType: row.settleType
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
      this.download('order/balanceDetail/export', params,"balance_" + row.taskNo + '_' + new Date().getTime() + '.xlsx');
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
