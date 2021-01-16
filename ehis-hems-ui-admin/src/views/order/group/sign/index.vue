<template>
  <div class="app-container">
    <!-- 团体订单签发 start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearchSign" label-width="125px">
      <!-- start 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="申请书编号" prop="personalorderno">
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
          <el-form-item label="申请日期止期">
            <el-date-picker clearable
                            v-model="queryParams.applicationEndDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择申请日期止期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第一行 end -->

      <!-- start 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="团体客户编码" prop="purname">
            <el-input
              v-model="queryParams.purname"
              placeholder="请输入团体客户编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="团体客户名称" prop="salesmanno">
            <el-input
              v-model="queryParams.salesmanno"
              placeholder="请输入团体客户名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第二行 end -->

      <!-- 第三行 start -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="联系人" prop="concatName">
            <el-input
              v-model="queryParams.concatName"
              placeholder="请输入联系人"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="联系电话" prop="concatPhone">
            <el-input
              v-model="queryParams.concatPhone"
              placeholder="请输入联系电话"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="状态" prop="orderstatus">
            <el-select v-model="queryParams.orderstatus" placeholder="请选择合约类型" clearable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in orderstatus"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- end 第三行 -->

      <!-- 第三行 start -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="录入日期起期">
            <el-date-picker clearable
                            v-model="queryParams.enterStartDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择录入日期起期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="录入日期止期">
            <el-date-picker clearable
                            v-model="queryParams.enterEndDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择录入日期止期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第三行 end -->

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <!-- 卡片标题 -->
        <div slot="header" class="clearfix">
          <i class="el-icon-tickets mr5"></i><span>订单签发列表</span>
        </div>
        <!-- 操作按钮 -->
        <el-row class="mb8">
          <el-button type="primary" icon="el-icon-s-promotion" :disabled="single" size="mini" @click="applicationSign">
            申请书签发
          </el-button>
          <right-toolbar :showSearch.sync="showSearchSign" @queryTable="getList"></right-toolbar>
        </el-row>
        <!-- 表格 -->
        <el-table v-loading="loading" :data="personalList" @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55" align="center">
          </el-table-column>

          <el-table-column label="申请书编号" align="center" prop="personalorderno" show-overflow-tooltip/>
          <el-table-column label="订单名称" align="center" prop="purname" show-overflow-tooltip/>
          <el-table-column label="团体客户编码" align="center" prop="salesmanno" show-overflow-tooltip/>
          <el-table-column label="团体客户名称" align="center" prop="orderstatus" show-overflow-tooltip/>
          <el-table-column label="联系人" align="center" prop="operator" show-overflow-tooltip/>
          <el-table-column label="联系人姓名" align="center" prop="operator"/>
          <el-table-column label="申请日期" align="center" prop="applicationdate">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.applicationdate, '{y}-{m}-{d}') }}</span>
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
    <!-- end 个人订单签发 -->
  </div>
</template>

<script>
  import {listPersonal, getPersonal, delPersonal, addPersonal, updatePersonal} from "@/api/order/personal";

  export default {
    name: "Personal",
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
        // 显示搜索条件 - 签发
        showSearchSign: true,
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
      /** 新增按钮操作 */
      handleAdd() {
        this.$router.push({path: '/order/personal/enter/add'});
        // this.reset();
        // this.open = true;
        // this.title = "添加个人订单";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        alert('该功能待开发。。。');
        // this.reset();
        // const personalorderno = row.personalorderno || this.ids
        // getPersonal(personalorderno).then(response => {
        //   this.form = response.data;
        //   this.open = true;
        //   this.title = "修改个人订单";
        // });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.appno != null) {
              updatePersonal(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addPersonal(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                }
              });
            }
          }
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
        }, `order_group.xlsx`)
      },
      /** 申请书签发 */
      applicationSign(row) {
        const personalorderno = row.personalorderno || this.ids[0];
        this.$router.push({path: '/order/group/sign/' + personalorderno});
      },
    }
  };
</script>
