<!-- 服务结算明细 -->
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
              <el-select v-model="queryParams.serviceCode" class="item-width" size="mini" placeholder="请选择" clearable filterable @change="setSupplierServiceName">
                <el-option v-for="item in serviceOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
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
        </el-row>

        <!-- 第二行 -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户姓名：" prop="custName">
              <el-input v-model="queryParams.custName" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="queryParams.policyNo" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyItemNo">
              <el-input v-model="queryParams.policyItemNo" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="success" icon="el-icon-search" @click="handleQuery">查 询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetQuery">重 置</el-button>
        </div>
      </el-form>

    <el-divider/>
    <div>
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>服务费结算列表（{{ total }}）</span>
        <span  style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">
              <el-button type="primary" size="mini" @click.native.stop="listExport">清单导出</el-button>
          </span>
      </div>
      <!-- 表格区域 START -->
      <el-table
        ref="balanceDetailTable"
        :data="balanceList"
        v-loading="loading"
        size="mini"
        tooltip-effect="dark"
        class="receive_table"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
      >
        <el-table-column type="index" label="序号" align="center" show-overflow-tooltip width="50px"/>
        <el-table-column  key="taskNo" prop="taskNo" label="任务号" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="orderCode" prop="orderCode" label="工单编号" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="riskCode" prop="riskName" label="险种" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="policyNo" prop="policyNo" label="保单号" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="policyItemNo" prop="policyItemNo" label="分单号" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="serviceCode" prop="supplierServiceName" label="供应商项目名称" width="150%" align="center" show-overflow-tooltip>
<!--          <template slot-scope="scope">
            <span>{{selectDictLabel(serviceData, scope.row.serviceCode)}}</span>
          </template>-->
        </el-table-column>
        <el-table-column  key="appntName" prop="appntName" label="投保人" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="custName" prop="custName" label="客户姓名" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="sex" prop="sex" label="性别" width="150%" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{selectDictLabel(sexOptions, scope.row.sex)}}</span>
          </template>
        </el-table-column>
        <el-table-column  key="idNo" prop="idNo" label="证件号码" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="serviceDate" prop="serviceDate" label="服务日期" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="remark" prop="remark" label="备注" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="amount" prop="amount" label="服务费" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="actualAmount" prop="actualAmount" label="实际服务费用" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="reason" prop="reason" label="编辑原因" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column  key="bussinessStatus" prop="bussinessStatus" label="状态" width="150%" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{selectDictLabel(balanceStatusOptions, scope.row.bussinessStatus)}}</span>
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
    <!-- 结算明细列表 END-->
    </el-card>

  </div>
</template>

<script>
import balanceDetail from './../components/balanceDetail'
import {getAllBaseSupplierInfo, getContractServerList} from "@/api/contractManage/contractManagement";
import {listBalanceDetail_1, listBalanceDetail_2,listBalanceDetail_3} from "@/api/claim/serviceBalance";
import {atLeastOne2} from "@/utils/commenMethods";
import moment from "moment";

let dictss = [{dictType: 'balance_status'},{dictType: 'sys_user_sex'}]
export default {
  components: {
    balanceDetail
  },
  data() {
    return {
      // 是否默认30天查询
      isDefault: true,
      //总条数
      total: 0,
      // 遮罩层
      loading: false,
      //明细列表
      balanceList: [],
      //查询参数
      queryParams: {
        // 状态
        status: "Y",
        // 页数
        pageNum: 1,
        // 分页数
        pageSize: 10,
        //结算方式-按case
        settleType: "01",
        //供应商
        supplierCode: null,
        //供应商服务项目
        serviceCode: null,
        //供应商服务项目名称
        supplierServiceName: null,
        //状态
        bussinessStatus: null,
        //客户姓名
        custName: null,
        //保单号
        policyNo: null,
        //分单号
        policyItemNo: null
      },
      //字典数组
      dictList: [],
      //供应商
      supplierOptions: [],
      //供应商服务项目—all
      serviceData: [],
      //供应商服务项目
      serviceOptions: [],
      //结算业务状态
      balanceStatusOptions: [],
      node:true,
      sexOptions: []
    };
  },
  created() {

  },
  async mounted() {
    //初始化
    this.initData();

    await this.getDictsList(dictss).then(res => {
      this.dictList = res.data
    });
    this.balanceStatusOptions = this.dictList.find(item => {
      return item.dictType === 'balance_status'
    }).dictDate;
    this.sexOptions = this.dictList.find(item => {
      return item.dictType === 'sys_user_sex'
    }).dictDate;
  },
  methods: {
    //给供应商服务项目名称赋值
    setSupplierServiceName(){
      let arr=this.serviceOptions.filter(item=>{
        return item.dictValue==this.queryParams.serviceCode
      })
      this.queryParams.supplierServiceName= arr[0].dictLabel
    },

    //清单导出
    listExport(){
      listBalanceDetail_2(this.queryParams).then(res => {
        if (res != null && res.rows.length>0){
          this.download('order/balanceDetail/export2', {
            ...this.queryParams}, `invoice_${new Date().getTime()}.xlsx`).catch(res=>{
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }else {
          return this.$message.warning(
            "没有查询到能导出的数据！"
          )
        }
      }).catch(res => {
      })

    },

    /** 查询列表 */
    getList() {
      this.loading = true;
      //无查询参数查询默认30天
      if (this.isDefault) {
        //结算清单
        listBalanceDetail_3(this.queryParams).then(res => {
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
      }
      else {
        //结算清单
        listBalanceDetail_2(this.queryParams).then(res => {
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
      this.getList()
    },
    /** 供应商事件 */
    changeSupplier() {
      this.queryParams.serviceCode = "";
      this.serviceOptions = this.serviceData.filter(item => {
        return item.supplierCode === this.queryParams.supplierCode;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10;
      let query = JSON.parse(JSON.stringify(this.queryParams))
      const flag = atLeastOne2(query,["status","pageNum","pageSize",'settleType']);
      if(flag){
        this.isDefault=false
      }
      else{
        this.isDefault=true
      }
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.isDefault=true
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10;
      this.resetForm("queryForm");
      this.queryParams.supplierServiceName=null;
    this.getList()
    },
  }
};
</script>

<style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .item-width {
    width: 220px;
  }
</style>
