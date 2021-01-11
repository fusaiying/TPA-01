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
          <el-form-item label="团体订单号">
            <el-input
              v-model="queryParams.grouporderno"
              placeholder="请输入团体订单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="申请日期">
            <el-date-picker clearable
                            v-model="queryParams.applicationStartDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择申请日期起期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第一行 end -->

      <!-- 第二行 start -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合作伙伴编码">
            <el-input
              v-model="queryParams.partnerCode"
              placeholder="请输入合作伙伴编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合作伙伴名称">
            <el-input
              v-model="queryParams.partnerName"
              placeholder="请输入合作伙伴名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="渠道属性">
            <el-select v-model="queryParams.channelAttribute" placeholder="请选择渠道属性" clearable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in channelAttribute"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- end 第二行 -->

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- card1 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <!-- 卡片标题 -->
        <div slot="header" class="clearfix">
          <i class="el-icon-tickets mr5"></i><span>待收费账单</span>
        </div>
        <!-- 操作按钮 -->
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              :disabled="single"
              v-hasPermi="['order:personal:add']"
            >收费申请
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearchEnter" @queryTable="getList"></right-toolbar>
        </el-row>
        <!-- 表格 -->
        <el-table v-loading="loading" :data="bill" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="团体申请书编号" align="center" prop="groupAppNo" show-overflow-tooltip/>
          <el-table-column label="团体订单号" align="center" prop="groupOrderNo" show-overflow-tooltip/>
          <el-table-column label="申请日期" align="center" prop="appDate" show-overflow-tooltip/>
          <el-table-column label="签发日期" align="center" prop="signDate" show-overflow-tooltip/>
          <el-table-column label="合作伙伴名称" align="center" prop="partnerName" show-overflow-tooltip/>
          <el-table-column label="渠道属性" align="center" prop="channelAttribute" show-overflow-tooltip/>
          <el-table-column label="收费方式" align="center" prop="chargeWay" show-overflow-tooltip/>
          <el-table-column label="应收费用" align="center" prop="shouldReceive" show-overflow-tooltip/>
          <el-table-column label="实收费用" align="center" prop="realReceive" show-overflow-tooltip/>
          <el-table-column label="银行账号名" align="center" prop="bankName" show-overflow-tooltip/>
          <el-table-column label="银行账号" align="center" prop="bankAccount" show-overflow-tooltip/>
        </el-table>
<!--        <pagination-->
<!--          v-show="total>0"-->
<!--          :total="total"-->
<!--          :page.sync="queryParams.pageNum"-->
<!--          :limit.sync="queryParams.pageSize"-->
<!--          @pagination="getList"-->
<!--        />-->
      </el-card>
    </el-row>


    <!-- card2 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-marketing mr5"></i><span>财务匹配</span>
        </div>
        <el-table v-loading="loading" :data="matching" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="团体申请书编号" align="center" prop="groupAppNo" show-overflow-tooltip/>
          <el-table-column label="团体订单号" align="center" prop="groupOrderNo" show-overflow-tooltip/>
          <el-table-column label="申请日期" align="center" prop="appDate" show-overflow-tooltip/>
          <el-table-column label="签发日期" align="center" prop="signDate" show-overflow-tooltip/>
          <el-table-column label="合作伙伴名称" align="center" prop="partnerName" show-overflow-tooltip/>
          <el-table-column label="渠道属性" align="center" prop="channelAttribute" show-overflow-tooltip/>
          <el-table-column label="收费方式" align="center" prop="chargeWay" show-overflow-tooltip/>
          <el-table-column label="应收费用" align="center" prop="shouldReceive" show-overflow-tooltip/>
          <el-table-column label="实收费用" align="center" prop="realReceive" show-overflow-tooltip/>
          <el-table-column label="银行账号名" align="center" prop="bankName" show-overflow-tooltip/>
          <el-table-column label="银行账号" align="center" prop="bankAccount" show-overflow-tooltip/>
        </el-table>
      </el-card>
    </el-row>

    <!-- card3 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i><span>财务收费数据</span>
        </div>
        <el-table v-loading="loading" :data="chargeData" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="客户开户行" align="center" prop="personalorderno" show-overflow-tooltip/>
          <el-table-column label="客户账号" align="center" prop="purname" show-overflow-tooltip/>
          <el-table-column label="客户账户名" align="center" prop="salesmanno" show-overflow-tooltip/>
          <el-table-column label="收款账户" align="center" prop="orderstatus" show-overflow-tooltip/>
          <el-table-column label="交易金额" align="center" prop="operator" show-overflow-tooltip/>
          <el-table-column label="交易时间" align="center" prop="applicationdate" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.applicationdate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-row>


    <el-row class="mt20 mb10" style="text-align: right;">
      <el-col class="mt10 mb10">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="clear"
          v-hasPermi="['provider:provider:add']"
        >清空匹配池
        </el-button>
        <el-button
          type="primary"
          icon="el-icon-s-claim"
          size="mini"
          @click="submitForm"
          v-hasPermi="['provider:provider:add']"
        >确认匹配
        </el-button>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import {listPersonal, getPersonal, delPersonal, addPersonal, updatePersonal} from "@/api/order/personal";

  export default {
    name: "GroupCharge",
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
        // 代收费 账单
        bill: [
          {
            groupAppNo: 'HG2018000619',
            groupOrderNo: 'HG2018000619',
            appDate: '2018-05-23',
            signDate: '2018-05-23',
            partnerName: '合作伙伴0401123',
            channelAttribute: '16000921',
            chargeWay: '02 - 银行转账',
            shouldReceive: '2000',
            realReceive: '2000',
            bankName: 'shl222',
            bankAccount: '79874646546',
          },
          {
            groupAppNo: 'HG2018000595',
            groupOrderNo: 'HG2018000595',
            appDate: '2018-05-23',
            signDate: '2018-05-23',
            partnerName: '合作伙伴0401123',
            channelAttribute: '16000921',
            chargeWay: '02 - 银行转账',
            shouldReceive: '5000',
            realReceive: '5000',
            bankName: 'shl222',
            bankAccount: '79874646546',
          },
          {
            groupAppNo: 'HG2018000565',
            groupOrderNo: 'HG2018000565',
            appDate: '2018-05-23',
            signDate: '2018-05-23',
            partnerName: '合作伙伴04111111',
            channelAttribute: '16000921',
            chargeWay: '02 - 银行转账',
            shouldReceive: '1000',
            realReceive: '1000',
            bankName: 'shl111',
            bankAccount: '798746464234',
          },
          ],
        // 财务匹配
        matching: [],
        // 收费数据
        chargeData: [],

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
        // 渠道属性 下拉框字典
        channelAttribute: [],
        // 表单校验
        rules: {
          orderstartdate: [
            {required: true, message: "录入员不能为空", trigger: "blur"}
          ],
          chargemethod: [
            {required: true, message: "录入员不能为空", trigger: "blur"}
          ],
          operator: [
            {required: true, message: "录入员不能为空", trigger: "blur"}
          ],
          makedate: [
            {required: true, message: "录入员不能为空", trigger: "blur"}
          ],
          maketime: [
            {required: true, message: "录入员不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.loading = false;
      // this.getList();
      // this.getDicts("order_orderstatus").then(response => {
      //   this.orderstatus = response.data;
      // });
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
        this.ids = selection.map(item => item.groupAppNo);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 收费申请操作 */
      handleAdd() {
        this.matching = [{
          groupAppNo: 'HG2018000619',
          groupOrderNo: 'HG2018000619',
          appDate: '2018-05-23',
          signDate: '2018-05-23',
          partnerName: '合作伙伴0401123',
          channelAttribute: '16000921',
          chargeWay: '02 - 银行转账',
          shouldReceive: '2000',
          realReceive: '2000',
          bankName: 'shl222',
          bankAccount: '79874646546',
        }];
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const personalorderno = row.personalorderno || this.ids[0];
        this.$router.push('/order/group/enter/edit/' + personalorderno);
      },
      clear() {
        this.matching = [];
      },
      /** 提交按钮 */
      submitForm() {
        this.$message({
          message: '匹配成功',
          type: 'success'
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        alert('该功能待开发。。。');
        // const appnos = row.appno || this.ids;
        // this.$confirm('是否确认删除个人订单编号为"' + appnos + '"的数据项?', "警告", {
        //   confirmButtonText: "确定",
        //   cancelButtonText: "取消",
        //   type: "warning"
        // }).then(function () {
        //   return delPersonal(appnos);
        // }).then(() => {
        //   this.getList();
        //   this.msgSuccess("删除成功");
        // }).catch(function () {
        // });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('order/personal/export', {
          ...this.queryParams
        }, `order_personal.xlsx`)
      },
      /** 申请书订正 */
      applicationCorrect() {
        alert('该功能待开发。。。');
      },
      /** 申请书签发 */
      applicationSign() {
        alert('该功能待开发。。。');
      },
    }
  };
</script>
