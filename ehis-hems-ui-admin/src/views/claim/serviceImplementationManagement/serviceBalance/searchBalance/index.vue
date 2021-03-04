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
              <el-select v-model="queryParams.serviceCode" class="item-width" size="mini" placeholder="请选择" clearable filterable>
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
            <el-form-item label="客户姓名：" prop="serialNo">
              <el-input v-model="queryParams.custName" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="保单号：" prop="serialNo">
              <el-input v-model="queryParams.policyNo" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="分单号：" prop="serialNo">
              <el-input v-model="queryParams.policyItemNo" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="success" icon="el-icon-search" @click="handleQuery">查 询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetQuery">重 置</el-button>
        </div>
      </el-form>
    </el-card>

    <!-- 结算明细列表 START-->
    <balance-detail ref="balanceDetailTable" :queryParams="queryDetailParams" :dictList="dictList"/>
    <!-- 结算明细列表 END-->

  </div>
</template>

<script>
import balanceDetail from './../components/balanceDetail'
import {getAllBaseSupplierInfo, getContractServerList} from "@/api/contractManage/contractManagement";

let dictss = [{dictType: 'balance_status'},{dictType: 'sex'}]
export default {
  components: {
    balanceDetail
  },
  data() {
    return {
      //查询参数
      queryParams: {
        //是否展示
        isShow: true,
        //是否结算
        isSearche: true,
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
        //状态
        bussinessStatus: null,
        //客户姓名
        custName: null,
        //保单号
        policyNo: null,
        //分单号
        policyItemNo: null
      },
      //明细查询参数
      queryDetailParams: {
        //是否展示
        isShow: false,
        //是否结算
        isSearche: true
      },
      //是否显示
      isShow: false,
      //字典数组
      dictList: [],
      //供应商
      supplierOptions: [],
      //供应商服务项目—all
      serviceData: [],
      //供应商服务项目
      serviceOptions: [],
      //结算业务状态
      balanceStatusOptions: []
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.isShow = true;
      this.queryParams.pageNum = 1;
      this.queryDetailParams = JSON.parse(JSON.stringify(this.queryParams));
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
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
        //状态
        bussinessStatus: null,
        //客户姓名
        custName: null,
        //保单号
        policyNo: null,
        //分单号
        policyItemNo: null
      };
      this.resetForm("queryForm");
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
