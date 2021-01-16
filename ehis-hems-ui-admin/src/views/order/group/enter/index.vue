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
          <el-form-item label="申请日期止期">
            <el-date-picker clearable
                            v-model="queryParams.applicationEndDate"
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
          <el-form-item label="团体客户编码">
            <el-input
              v-model="queryParams.purname"
              placeholder="请输入团体客户编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="团体客户名称">
            <el-input
              v-model="queryParams.salesmanno"
              placeholder="请输入团体客户名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="联系人">
            <el-input
              v-model="queryParams.salesmanno"
              placeholder="请输入联系人"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="联系人电话">
            <el-input
              v-model="queryParams.salesmanno"
              placeholder="请输入联系人电话"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="状态">
            <el-select v-model="queryParams.orderstatus" placeholder="请选择状态" clearable
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
      <!-- end 第二行 -->

      <!-- start 第三行 -->
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
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <!-- 卡片、表格 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-tickets mr5"></i><span>团体申请书列表</span>
        </div>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['order:personal:add']"
            >新增
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['order:personal:edit']"
            >修改
            </el-button>
          </el-col>
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="danger"-->
          <!--          icon="el-icon-delete"-->
          <!--          size="mini"-->
          <!--          :disabled="multiple"-->
          <!--          @click="handleDelete"-->
          <!--          v-hasPermi="['order:personal:remove']"-->
          <!--        >删除-->
          <!--        </el-button>-->
          <!--      </el-col>-->
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="warning"-->
          <!--          icon="el-icon-download"-->
          <!--          size="mini"-->
          <!--          @click="handleExport"-->
          <!--          v-hasPermi="['order:personal:export']"-->
          <!--        >导出-->
          <!--        </el-button>-->
          <!--      </el-col>-->
          <right-toolbar :showSearch.sync="showSearchEnter" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="personalList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="申请书编号" align="center" prop="personalorderno" show-overflow-tooltip/>
          <el-table-column label="购买人姓名" align="center" prop="purname" show-overflow-tooltip/>
          <el-table-column label="业务员" align="center" prop="salesmanno" show-overflow-tooltip/>
          <el-table-column label="状态" align="center" prop="orderstatus" show-overflow-tooltip/>
          <el-table-column label="录入员" align="center" prop="operator" show-overflow-tooltip/>

          <el-table-column label="申请日期" align="center" prop="applicationdate" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.applicationdate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['order:personal:edit']"
              >修改
              </el-button>
              <!--          <el-button-->
              <!--            size="mini"-->
              <!--            type="text"-->
              <!--            icon="el-icon-delete"-->
              <!--            @click="handleDelete(scope.row)"-->
              <!--            v-hasPermi="['order:personal:remove']"-->
              <!--          >删除-->
              <!--          </el-button>-->
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

    <!-- 添加或修改个人订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="录入员" prop="servicecardno">
          <el-input v-model="form.servicecardno" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="业务员" prop="salesmanno">
          <el-input v-model="form.salesmanno" placeholder="请输入业务员"/>
        </el-form-item>
        <el-form-item label="申请日期" prop="applicationdate">
          <el-date-picker clearable
                          v-model="form.applicationdate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择申请日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请日期" prop="orderstartdate">
          <el-date-picker clearable
                          v-model="form.orderstartdate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择申请日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请日期" prop="orderenddate">
          <el-date-picker clearable
                          v-model="form.orderenddate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择申请日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请日期" prop="advancepay">
          <el-input v-model="form.advancepay" placeholder="请输入申请日期"/>
        </el-form-item>
        <el-form-item label="申请日期" prop="issendcard">
          <el-input v-model="form.issendcard" placeholder="请输入申请日期"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="purname">
          <el-input v-model="form.purname" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="pursex">
          <el-select v-model="form.pursex" placeholder="请选择购买人姓名">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="idtype">
          <el-select v-model="form.idtype" placeholder="请选择购买人姓名">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="idno">
          <el-input v-model="form.idno" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="birthday">
          <el-date-picker clearable
                          v-model="form.birthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择购买人姓名">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="email">
          <el-input v-model="form.email" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="postcode">
          <el-input v-model="form.postcode" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="chargemethod">
          <el-input v-model="form.chargemethod" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="bankaccountname">
          <el-input v-model="form.bankaccountname" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="bankname">
          <el-input v-model="form.bankname" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="bankbranch">
          <el-input v-model="form.bankbranch" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="bankaccount">
          <el-input v-model="form.bankaccount" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="bankaddress">
          <el-input v-model="form.bankaddress" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="billpattern">
          <el-input v-model="form.billpattern" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="ordermark">
          <el-input v-model="form.ordermark" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="购买人姓名" prop="orderfrom">
          <el-input v-model="form.orderfrom" placeholder="请输入购买人姓名"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.orderstatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="状态" prop="actualcurrency">
          <el-input v-model="form.actualcurrency" placeholder="请输入状态"/>
        </el-form-item>
        <el-form-item label="状态" prop="actualmoney">
          <el-input v-model="form.actualmoney" placeholder="请输入状态"/>
        </el-form-item>
        <el-form-item label="状态" prop="payintegral">
          <el-input v-model="form.payintegral" placeholder="请输入状态"/>
        </el-form-item>
        <el-form-item label="状态" prop="paycurrency">
          <el-input v-model="form.paycurrency" placeholder="请输入状态"/>
        </el-form-item>
        <el-form-item label="状态" prop="paydate">
          <el-date-picker clearable
                          v-model="form.paydate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择状态">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="accdate">
          <el-date-picker clearable
                          v-model="form.accdate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择状态">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="alternatefield1">
          <el-input v-model="form.alternatefield1" placeholder="请输入状态"/>
        </el-form-item>
        <el-form-item label="状态" prop="alternatefield2">
          <el-input v-model="form.alternatefield2" placeholder="请输入状态"/>
        </el-form-item>
        <el-form-item label="录入员" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="makedate">
          <el-date-picker clearable
                          v-model="form.makedate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择录入员">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入员" prop="maketime">
          <el-input v-model="form.maketime" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="modifyoperator">
          <el-input v-model="form.modifyoperator" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="modifydate">
          <el-date-picker clearable
                          v-model="form.modifydate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择录入员">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入员" prop="modifytime">
          <el-input v-model="form.modifytime" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="contno">
          <el-input v-model="form.contno" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="riskcode">
          <el-input v-model="form.riskcode" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="supplier">
          <el-input v-model="form.supplier" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="conttel">
          <el-input v-model="form.conttel" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="signdate">
          <el-date-picker clearable
                          v-model="form.signdate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择录入员">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入员" prop="chargemethod2">
          <el-input v-model="form.chargemethod2" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="worktime">
          <el-input v-model="form.worktime" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="relatedcode">
          <el-input v-model="form.relatedcode" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="taxpayertype">
          <el-select v-model="form.taxpayertype" placeholder="请选择录入员">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="录入员" prop="invoicetype">
          <el-select v-model="form.invoicetype" placeholder="请选择录入员">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="录入员" prop="collectionflag">
          <el-input v-model="form.collectionflag" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="billnumber">
          <el-input v-model="form.billnumber" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="channelno1">
          <el-input v-model="form.channelno1" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="saleplatform">
          <el-input v-model="form.saleplatform" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="balanceflag">
          <el-input v-model="form.balanceflag" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="printtimes">
          <el-input v-model="form.printtimes" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="customerno">
          <el-input v-model="form.customerno" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="commission">
          <el-input v-model="form.commission" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="servicecharge">
          <el-input v-model="form.servicecharge" placeholder="请输入录入员"/>
        </el-form-item>
        <el-form-item label="录入员" prop="enddate">
          <el-date-picker clearable
                          v-model="form.enddate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择录入员">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入员" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入录入员"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

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
        this.$router.push({path: '/order/group/enter/add'});
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const personalorderno = row.personalorderno || this.ids[0];
        this.$router.push('/order/group/enter/edit/' + personalorderno);
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
