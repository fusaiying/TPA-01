<!-- 发起结算 -->
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" label-width="130px" label-position="right" size="mini" :rules="rules">
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
            <el-select v-model="queryParams.serviceCode" class="item-width" size="mini" placeholder="请先选择供应商" clearable filterable>
              <el-option v-for="item in serviceOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="结算方式：" prop="settleType">
            <el-select v-model="queryParams.settleType" class="item-width" size="mini" placeholder="请先选择供应商" @change="changeSettleType">
              <el-option v-for="item in clearingFormOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue" />
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
          <el-form-item label="是否有发票：" prop="balanceInvoiceType">
            <el-select v-model="queryParams.balanceInvoiceType" class="item-width" size="mini" placeholder="请选择" >
              <el-option v-for="item in balanceInvoiceTypeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <div style="text-align: right; margin-right: 10px;">
        <el-button size="mini" type="success" icon="el-icon-search" @click="handleQuery">查 询</el-button>
        <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetQuery">重 置</el-button>
        <el-button size="mini" type="primary" icon="el-icon-edit" :disabled="genLoading" @click="handleGen">生 成</el-button>
        <el-button size="mini" type="primary" icon="el-icon-download" @click="handleDown">下 载</el-button>
      </div>
    </el-form>

    <el-divider/>

    <!-- 表格区域 START-->
    <div v-if="queryParams.settleType">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>结算明细列表（{{ listTitle }}）</span>
      </div>
      <el-table
        :data="balanceList"
        v-loading="loading"
        size="mini"
        tooltip-effect="dark"
        class="receive_table"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        style="width: 100%;"
      >
        <el-table-column type="index" label="序号" align="center" show-overflow-tooltip width="50px"/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isPrice" key="supplierCode" prop="supplierCode" label="供应商名称" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="orderCode" prop="orderCode" label="工单编号" min-width="150%" align="center" show-overflow-tooltip/>

        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="settlementTime" prop="settlementTime" label="结算时间区间" min-width="150%" align="center" show-overflow-tooltip/>


        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont" key="riskCode" prop="riskName" label="险种" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="policyNo" prop="policyNo" label="保单号" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="policyItemNo" prop="policyItemNo" label="分单号" width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isPrice" key="serviceCode" prop="supplierServiceName" label="供应商项目名称" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="appntName" prop="appntName" label="投保人" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="custName" prop="custName" label="客户姓名" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="sex" prop="sex" label="性别" min-width="150%" align="center" :formatter="getSex" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="idNo" prop="idNo" label="证件号码" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="serviceDate" prop="serviceDate" label="服务日期" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCont" key="insuredNum" prop="insuredNum" label="承保人数" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCont" key="unitPrice" prop="unitPrice" label="客单价" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont" key="remark" prop="remark" label="备注" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCont" key="allAmount" prop="allAmount" :formatter="getAllAmout" label="总费用" min-width="150%" align="center" show-overflow-tooltip/>

        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="amount" prop="amount" label="服务费" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="actualAmount" prop="actualAmount" label="实际服务费用" min-width="150%" align="center" show-overflow-tooltip/>
        <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="reason" prop="reason" label="编辑原因" min-width="150%" align="center" show-overflow-tooltip/>
      </el-table>
      <!--分页组件-->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList()"
      />
    </div>
    <!-- 表格区域 END-->
  </div>
</template>

<script>
import {
  getAllBaseSupplierInfo,
  getContractServerList
} from '@/api/contractManage/contractManagement';
import {listBalanceDetail, addBalance} from "@/api/claim/serviceBalance";

let dictss = [{dictType: 'clearing_form'},{dictType: 'balance_invoice_type'},{dictType: 'sys_user_sex'}];
export default {
  name: "balanceAdd",
  data() {
    return {
      // 遮罩层
      loading: false,
      //生成任务
      balanceInfo: null,
      //生成加载
      genLoading: false,
      //结算方式变量
      settleTypeData: {
        //按case
        isCase: "01",
        //按人头
        isCont: "02",
        //按一口价
        isPrice: "03"
      },
      // 查询参数
      queryParams: {
        // 页数
        pageNum: 1,
        // 分页数
        pageSize: 10,
        //供应商
        supplierCode: null,
        //供应商服务项目
        serviceCode: null,
        //供应商服务项目名称
        serviceName:'',
        //结算方式
        settleType: null,
        //结算止期
        endDate: null,
        //发票类型
        balanceInvoiceType: "01"
      },
      //查询校验
      rules: {
        supplierCode: {trigger: ['change'], required: true, message: '供应商必填'},
        settleType: {trigger: ['change'], required: true, message: '结算方式必填'},
        balanceInvoiceType: {trigger: ['blur'], required: true, message: '发票类型必填'}
      },
      //总条数
      total: 0,
      //明细列表标题
      listTitle: null,
      //明细列表
      balanceList: [],
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
      //结算方式数据
      clearingFormData: [],
      //结算方式
      clearingFormOptions: [],
      //发票类型
      balanceInvoiceTypeOptions: [],
      //性别码表
      cs_sexOptions: []
    };
  },
  created() {

  },
  async mounted() {
    //初始化
    this.initData();
    //初始化字典
    await this.getDictsList(dictss).then(res => {
      this.dictList = res.data
    });
    this.clearingFormData = this.dictList.find(item => {
      return item.dictType === 'clearing_form'
    }).dictDate;
    this.balanceInvoiceTypeOptions = this.dictList.find(item => {
      return item.dictType === 'balance_invoice_type'
    }).dictDate;
    this.cs_sexOptions = this.dictList.find(item => {
      return item.dictType === 'sys_user_sex'
    }).dictDate;

  },
  methods: {
    //反显总费用
    getAllAmout(row){
      return  parseFloat(row.insuredNum)*parseFloat(row.unitPrice)
    },
    //反显性别
    getSex(row){
      return this.selectDictLabel(this.cs_sexOptions, row.sex);
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

      //如果只有一条，则默认选中
      if (this.serviceOptions.length == 1) {
        this.queryParams.serviceCode = this.serviceOptions[0].serialNo;
      }

      //结算方式
      this.queryParams.settleType = "";
      this.clearingFormOptions = [];
      this.clearingFormData.forEach(item => {
        if (this.serviceOptions.findIndex(item2 => item2.settleType === item.dictValue) > -1) {
          let obj= new Object();
          obj.dictLabel = item.dictLabel;
          obj.dictValue = item.dictValue;
          this.clearingFormOptions.push(obj);
        }
      });
      //如果只显示一条，则默认选中
      if (this.clearingFormOptions.length == 1) {
        this.queryParams.settleType = this.clearingFormOptions[0].dictValue;
        this.changeSettleType();
      }
    },
    /** 结算方式事件 */
    changeSettleType() {
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 10;
      this.total = 0;
      this.listTitle = null;
      this.balanceList = [];
      //按case
      if (this.queryParams.settleType == this.settleTypeData.isCase) {
        this.listTitle = "按case";
        //按人头
      } else if (this.queryParams.settleType == this.settleTypeData.isCont) {
        this.listTitle = "按人头";
        //按一口价
      } else if (this.queryParams.settleType == this.settleTypeData.isPrice) {
        this.listTitle = "按一口价";
      }
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      console.log(this.queryParams.serviceCode)
      if(this.queryParams.serviceCode!=undefined && this.queryParams.serviceCode!=null && this.queryParams.serviceCode!=''){
        let arr=this.serviceOptions.filter(item =>{
          return item.dictValue==this.queryParams.serviceCode
        })
        console.log(arr)

        this.queryParams.serviceName=arr[0].dictLabel
        console.log(this.queryParams.serviceName)
      }
      listBalanceDetail(this.queryParams).then(res => {
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
      this.$refs.queryForm.validate((valid) =>{
        if (valid){
          this.balanceInfo = null;
          this.queryParams.pageNum = 1;
          this.queryParams.pageSize = 10;
          this.getList();
        }
      })
    },
    /** 重置按钮操作 */
    resetQuery() {
      // 将查询表单重置
      this.$refs.queryForm.resetFields()
      this.queryParams.balanceInvoiceType = "01";
    },
    /** 生成 */
    handleGen() {
      if (this.balanceList.length == 0) {
        this.$message.warning('请先查询！');
        return;
      }
      this.$refs.queryForm.validate((valid) =>{
        if (valid){
          this.genLoading = true;
          addBalance(this.queryParams).then(res => {
            if (res != null && res.code === 200) {
              this.balanceInfo = res.data;
              this.genLoading = false;
              this.msgInfo('生成成功，请下载！');
            }
          }).catch(error => {
            this.genLoading = false;
          });
        }
      })
    },
    /** 下载 */
    handleDown() {
      if (this.balanceInfo == null) {
        this.$message.warning('请先生成！');
        return;
      }
      const params = {
        taskNo: this.balanceInfo.taskNo,
        settleType: this.balanceInfo.settleType,
        status: "Y"
      };
      this.download('order/balanceDetail/export', params, "balance_" + this.balanceInfo.taskNo + '_' + new Date().getTime() + '.xlsx');
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
