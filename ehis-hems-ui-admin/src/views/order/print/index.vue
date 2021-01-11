<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearchEnter" label-width="125px">
      <!-- start 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="申请书编号">
            <el-input
              v-model="queryParams.personalorderno"
              placeholder="请输入申请书编号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="申请日期起期">
            <el-date-picker clearable
                            v-model="queryParams.applicationStartDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择申请日期起期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="订单号">
            <el-input
              v-model="queryParams.orderNo"
              placeholder="请输入订单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第一行 end -->

      <!-- 第二行 start -->
      <el-row>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="客户类型">
            <el-select v-model="queryParams.customerType" placeholder="请选择客户类型"
                       clearable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in customerType"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="业务员">
            <el-select v-model="queryParams.businessName" placeholder="请选择业务员"
                       clearable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in businessName"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="购买人姓名">
            <el-input
              v-model="queryParams.purname"
              placeholder="请输入购买人姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- end 第二行 -->

      <!-- start 第三行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="联系人">
            <el-input
              v-model="queryParams.concatName"
              placeholder="请输入联系人"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="手机号">
            <el-input
              v-model="queryParams.concatPhone"
              placeholder="请输入手机号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

      </el-row>
      <!-- 第三行 end -->

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <!-- 卡片标题 -->
        <div slot="header" class="clearfix">
          <i class="el-icon-tickets mr5"></i><span>合同列表</span>
        </div>
        <!-- 操作按钮 -->
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="print"
              v-hasPermi="['order:personal:edit']"
            >合同打印
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearchEnter" @queryTable="getList"></right-toolbar>
        </el-row>
        <!-- 表格 -->
        <el-table v-loading="loading" :data="personalList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="申请书编号" align="center" prop="personalorderno" show-overflow-tooltip/>
          <el-table-column label="订单名称" align="center" prop="orderName" show-overflow-tooltip/>
          <el-table-column label="订单号" align="center" prop="orderNo" show-overflow-tooltip/>
          <el-table-column label="客户类型" align="center" prop="customerType" show-overflow-tooltip/>
          <el-table-column label="购买人姓名" align="center" prop="purname" show-overflow-tooltip/>
          <el-table-column label="联系人" align="center" prop="concatName" show-overflow-tooltip/>
          <el-table-column label="手机号" align="center" prop="concatPhone" show-overflow-tooltip/>
          <el-table-column label="业务员" align="center" prop="salesmanno" show-overflow-tooltip/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="print(scope.row)"
                v-hasPermi="['order:personal:edit']"
              >合同打印
              </el-button>
            </template>
          </el-table-column>

        </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
      </el-card>
    </el-row>

  </div>
</template>

<script>
  import {listPersonal, getPersonal, delPersonal, addPersonal, updatePersonal} from "@/api/order/personal";

  export default {
    name: "Print",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件 - 录入
        showSearchEnter: true,
        // 总条数
        total: 0,
        // 个人订单表格数据
        personalList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          personalorderno: null,
          applicationdate: null,
          purname: null,
          salesmanno: null,
          orderstatus: null,
          makedate: null,
        },
        // 表单参数
        form: {},
        // 页面状态 下拉框字典
        orderstatus: [],
        customerType: [],
        businessName: [],
      };
    },
    created() {
      this.getList();
      this.getDicts("order_orderstatus").then(response => {
        this.orderstatus = response.data;
      });
    },
    methods: {
      /** 查询个人订单列表 */
      getList() {
        this.loading = true;
        listPersonal(this.queryParams).then(response => {
          this.personalList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          personalorderno: null,
          servicecardno: null,
          salesmanno: null,
          applicationdate: null,
          orderstartdate: null,
          orderenddate: null,
          advancepay: null,
          issendcard: null,
          purname: null,
          pursex: null,
          idtype: null,
          idno: null,
          birthday: null,
          phone: null,
          mobile: null,
          email: null,
          postcode: null,
          chargemethod: null,
          bankaccountname: null,
          bankname: null,
          bankbranch: null,
          bankaccount: null,
          bankaddress: null,
          billpattern: null,
          ordermark: null,
          orderfrom: null,
          orderstatus: "0",
          actualcurrency: null,
          actualmoney: null,
          payintegral: null,
          paycurrency: null,
          paydate: null,
          accdate: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null,
          contno: null,
          riskcode: null,
          supplier: null,
          conttel: null,
          signdate: null,
          chargemethod2: null,
          worktime: null,
          relatedcode: null,
          taxpayertype: null,
          invoicetype: null,
          collectionflag: null,
          billnumber: null,
          channelno1: null,
          saleplatform: null,
          balanceflag: null,
          printtimes: null,
          customerno: null,
          commission: null,
          servicecharge: null,
          enddate: null,
          remark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.personalorderno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      print() {
        this.$message.error('该订单下的产品无电子凭证信息');
      },

    }
  };
</script>
