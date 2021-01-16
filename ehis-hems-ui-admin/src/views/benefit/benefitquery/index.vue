<template>
  <div class="app-container">
    <!-- 查询表单  start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 客户姓名 -->
          <el-form-item label="客户姓名">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入客户姓名"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 手机号码 -->
          <el-form-item label="手机号码">
            <el-input
              v-model="queryParams.mobilephone"
              placeholder="请输入手机号码"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 证件号码 -->
          <el-form-item label="证件号码">
            <el-input
              v-model="queryParams.idcardno"
              placeholder="请输入证件号码"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 保单号码 -->
          <el-form-item label="保单号码">
            <el-input
              v-model="queryParams.contno"
              placeholder="请输入保单号码"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 保险名称 -->
          <el-form-item label="保险名称">
            <el-input
              v-model="queryParams.riskname"
              placeholder="请输入保险名称"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 服务产品名称 -->
          <el-form-item label="服务产品名称">
            <el-input
              v-model="queryParams.productname"
              placeholder="请输入服务产品名称"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row>
        <el-col>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <!-- end 查询表单 -->
    <!-- card start -->
    <el-card class="box-card" shadow="hover">
      <!-- card title -->
      <div slot="header" class="clearfix">
        <i class="el-icon-s-grid mr5"></i>
        <span>供应商基础信息列表</span>
      </div>
      <!-- 新增、修改、删除、机构附件按钮 -->
      <el-row :gutter="10" class="mb8">
        <!--        <el-col :span="1.5">-->
        <!--          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>-->
        <!--        </el-col>-->
        <!--        <el-col :span="1.5">-->
        <!--          <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改-->
        <!--          </el-button>-->
        <!--        </el-col>-->
        <!--        <el-col :span="1.5">-->
        <!--          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除-->
        <!--          </el-button>-->
        <!--        </el-col>-->
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <!-- 供应商列表 表格 -->
      <!--      <el-table v-loading="loading" :data="providerList" @selection-change="handleSelectionChange" row-key="customerid" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">-->
      <el-table v-loading="loading" :data="providerList" @selection-change="handleSelectionChange" style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table :data="props.row.prodInfo" style="width: 100%;">
              <el-table-column type="expand">
                <template slot-scope="property">
                  <el-table :data="property.row.serviceOrder" style="width: 100%;" row-key="ordercode">
                    <el-table-column type="selection" width="55" align="center"></el-table-column>
                    <el-table-column label="预约工单编号">
                      <template slot-scope="property">{{property.row.ordercode}}</template>
                    </el-table-column>
                    <el-table-column label="预约渠道">
                      <template slot-scope="property">{{property.row.ordersource}}</template>
                    </el-table-column>
                    <el-table-column label="预约时间">
                      <template slot-scope="property">{{property.row.appointmenttime}}</template>
                    </el-table-column>
                    <el-table-column label="预约供应商">
                      <template slot-scope="property">{{property.row.supplier}}</template>
                    </el-table-column>
                    <el-table-column label="使用人">
                      <template slot-scope="property">{{property.row.user}}</template>
                    </el-table-column>
                    <el-table-column label="实施日期">
                      <template slot-scope="property">{{property.row.time}}</template>
                    </el-table-column>
                    <el-table-column label="实施结果">
                      <template slot-scope="property">{{property.row.resultcontent}}</template>
                    </el-table-column>
                    <el-table-column label="工单状态">
                      <template slot-scope="property">{{property.row.status}}</template>
                    </el-table-column>


                  </el-table>
                </template>
              </el-table-column>

              <el-table-column type="selection" width="55" align="center"></el-table-column>
              <el-table-column label="服务产品编码">
                <template slot-scope="props">{{props.row.productcode}}</template>
              </el-table-column>
              <el-table-column label="服务产品名称">
                <template slot-scope="props">{{props.row.productname}}</template>
              </el-table-column>
              <el-table-column label="服务产品来源">
                <template slot-scope="props">{{props.row.productcode}}</template>
              </el-table-column>
              <el-table-column label="服务项目编码">
                <template slot-scope="props">{{props.row.productname}}</template>
              </el-table-column>
              <el-table-column label="服务项目名称">
                <template slot-scope="props">{{props.row.productcode}}</template>
              </el-table-column>
              <el-table-column label="服务有效期">
                <template slot-scope="props">{{props.row.productname}}</template>
              </el-table-column>
              <el-table-column label="服务剩余次数">
                <template slot-scope="props">{{props.row.productcode}}</template>
              </el-table-column>
              <el-table-column label="服务消费详情">
                <template slot-scope="props">{{props.row.productname}}</template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>

        <el-table-column type="selection" width="55" align="center"></el-table-column>

        <el-table-column label="客户ID" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.customerid}}</template>
        </el-table-column>

        <el-table-column label="客户姓名" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>

        <el-table-column label="手机号码" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.mobilephone}}</template>
        </el-table-column>

        <el-table-column label="保单号码" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.contno}}</template>
        </el-table-column>

        <el-table-column label="保单有效期" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.period}}</template>
        </el-table-column>

        <el-table-column label="保险名称" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.riskname}}</template>
        </el-table-column>

        <el-table-column label="服务产品数量" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.productnum}}</template>
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleAdd(scope.row)">添加服务</el-button>
          </template>
        </el-table-column>

      </el-table>

      <!-- 分页 -->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"/>
    </el-card>
    <!-- end card -->


    <!-- 新增规则弹窗 start -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="125px">
        <el-row>
          <el-col :span="24">
            <!-- 分配产品 -->
            <el-form-item label="分配产品">
              <el-select v-model="form.productname"
                         placeholder="请选择险种名称"
                         clearable
                         multiple
                         collapse-tags
                         filterable
                         @keyup.enter.native="handleQuery">
                <el-option
                  v-for="dict in productname"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' + dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="生效日期">
              <el-date-picker
                v-model="form.effectDate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- end 新增规则弹窗 -->
  </div>
</template>

<script>
  import {listProvider} from "@/api/provider/provider";

  export default {
    name: "RightsQuery",
    data() {
      return {
        // 遮罩层
        loading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件 - 基础
        showSearch: true,
        // 总条数
        total: 0,
        // 供应商管理表格数据
        providerList: [
          {
            customerid: 1,
            name: '张三',
            mobilephone: '15832955845',
            contno: '111110',
            period: '2020/11/11-2020/12/12',
            riskname: '金太阳。',
            productnum: '12',
            prodInfo: [
              {
                productcode: '1111110',
                productname: '产品名称1',
                servicesource: '产品来源',
                servicecode: '服务项目编码',
                projectname: '服务项目名称',
                period: '2020/11/11-2020/12/12',
                balance: '剩余10',
                detail: '消费详情',
              },
              {
                productcode: '232323',
                productname: '产品名称2',
                servicesource: '产品来源',
                servicecode: '服务项目编码',
                projectname: '服务项目名称',
                period: '2020/11/11-2020/12/12',
                balance: '剩余10',
                detail: '消费详情',
              },
              {
                productcode: '1111111',
                productname: '产品名称3',
                servicesource: '产品来源',
                servicecode: '服务项目编码',
                projectname: '服务项目名称',
                period: '2020/11/11-2020/12/12',
                balance: '剩余10',
                detail: '消费详情',
              },

            ],
          },
          {
            customerid: 2,
            name: '李四',
            mobilephone: '15832955845',
            contno: '111110',
            period: '2020/11/11-2020/12/12',
            riskname: '金太阳。',
            productnum: '12',
            prodInfo: [
              {
                productcode: '1111111',
                productname: '产品名称3',
                servicesource: '产品来源',
                servicecode: '服务项目编码',
                projectname: '服务项目名称',
                period: '2020/11/11-2020/12/12',
                balance: '剩余10',
                detail: '消费详情',
                serviceOrder: [
                  {
                    ordercode: '000000001',
                    ordersource: '微信预约',
                    appointmenttime: '2020-11-19',
                    supplier: '预约供应商11',
                    user: '使用人',
                    time: '2020-11-20',
                    resultcontent: '实施结果',
                    status: '实施状态',
                  }
                ],
              },

            ],
          },
        ],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,

        // 查询参数 - 基础信息维护
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: '',
          mobilephone: '',
          idcardno: '',
          contno: '',
          riskname: '',
          productname: '',
        },
        // 弹框表单参数
        form: {},
        // 弹框校验
        rules: {},
        // 分配产品下拉
        productname: [],
      };
    },
    created() {
      // 组件一加载就查询所有
      // this.getList();
      // 供应商类型 下拉
      this.getDicts("provider_servcomtype").then(response => {
        this.medicaltypeOptions = response.data;
      });
      // 供应商等级 下拉
      this.getDicts("provider_medicallevel").then(response => {
        this.medicalLevelOptions = response.data;
      });
    },
    methods: {
      /** 查询供应商 */
      getList() {
        this.loading = true;
        // 点击查询后，如果查询表单地区选项不为空的话，将会按照地区进行查询
        if (this.queryParams.selectedOptions != undefined) {
          this.queryParams.district = this.queryParams.selectedOptions[2];
          this.queryParams.selectedOptions = '';
        }
        listProvider(this.queryParams).then(response => {
          this.providerList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        // 将查询表单重置
        this.queryParams = {pageNum: 1, pageSize: 10,};
        this.resetForm('queryParams');
        // 重新查询
        this.handleQuery();
      },
      /** 点击复选框 */
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.servcomno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.open = true;
        this.title = "新增规则";
      },
      submitForm() {
        this.open = false;
      },
      cancel() {
        this.open = false;
      },
    }
  };
</script>

<style>

  body .el-table th.gutter {
    display: table-cell !important
  }

  .el-table__expanded-cell {
    border-bottom: 0px;
    border-right: 0px;
    padding: 0px 0px 0px 47px;
  }
</style>
