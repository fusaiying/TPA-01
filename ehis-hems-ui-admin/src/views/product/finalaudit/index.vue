<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
          <el-col :span="8">
            <el-form-item label="产品编码" prop="productno">
              <el-input
                v-model="queryParams.productno"
                placeholder="请输入产品编码"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称" prop="productname">
              <el-input
                v-model="queryParams.productname"
                placeholder="请输入产品名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务类型" prop="servicetype">
              <el-select
                v-model="queryParams.servicetype"
                placeholder="服务类型"
                clearable
                @change="changeProductClass"
              >
                <el-option
                  v-for="dict in serviceTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品类别" prop="productclass">
              <el-select
                v-model="queryParams.productclass"
                placeholder="产品类别"
                clearable
                @change="changeProductSubClass"
              >
                <el-option
                  v-for="dict in productClassOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品细类" prop="productsubclass">
              <el-select
                v-model="queryParams.productsubclass"
                placeholder="产品细类"
                clearable
              >
                <el-option
                  v-for="dict in productSubClassOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="产品销售价格" prop="productprice">-->
<!--              <el-input-->
<!--                v-model="queryParams.productprice"-->
<!--                placeholder="请输入产品销售价格(单位:元)"-->
<!--                clearable-->
<!--                @keyup.enter.native="handleQuery"-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="允许取消预约" prop="allowedtocancel">
              <el-select
                v-model="queryParams.allowedtocancel"
                placeholder="允许取消预约"
                clearable
              >
                <el-option
                  v-for="dict in allowedToCancelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-form>
    </el-row>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-col>
        </el-row>
    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-check mr5"></i><span>产品复核列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              :disabled="single"
              @click="openFinalAudit"
              v-hasPermi="['system:audit:openFinalAudit']"
            >财务复核
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="产品编码" align="center" prop="productno" width="120"/>
          <el-table-column label="产品名称" align="center" prop="productname"  width="120"/>
          <el-table-column label="服务类型" align="center" prop="servicetype" />
          <el-table-column label="产品类别" align="center" prop="productclass" />
          <el-table-column label="产品细类" align="center" prop="productsubclass"/>
          <el-table-column label="有效状态" align="center" prop="productstate" />
<!--          <el-table-column label="产品销售价格" align="center" prop="normalprice" />-->
          <el-table-column label="允许取消预约" align="center" prop="allowedtocancel" />
          <el-table-column label="服务限期" align="center" prop="servicelimit" />
          <el-table-column label="限期单位" align="center" prop="servicelimitflag" />
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
  import {listProduct, openView, getServiceType, getProductClass, getProductSubClass} from "@/api/product/definition";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Product",
    //components: {Treeselect},
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
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 用户表格数据
        productList: null,
        // 弹出层标题
        //title: "",
        // 部门树选项
        //deptOptions: undefined,
        // 是否显示弹出层
        //open: false,
        // 部门名称
        //deptName: undefined,
        // 默认密码
        //initPassword: undefined,
        // 日期范围
        //dateRange: [],
        // 服务类型数据字典
        serviceTypeOptions: [],
        // 产品类别字典
        productClassOptions: [],
        // 产品细类字典
        productSubClassOptions: [],
        // 允许取消预约字典
        allowedToCancelOptions: [],
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          productno: undefined,
          productname: undefined,
          servicetype: undefined,
          productclass: undefined,
          productsubclass: undefined,
          // productprice: undefined,
          allowedtocancel: undefined
        }
      };
    },
    created() {
      this.getList();
      getServiceType().then(response => {
        this.serviceTypeOptions = response.data;
        }
      );
      this.getDicts("product_yesflag").then(response => {
        this.allowedToCancelOptions = response.data;
      });
    },
    methods: {
      /** 查询用户列表 */
      getList() {
        this.loading = true;
        this.queryParams.productstate = "03";
        listProduct(this.queryParams).then(response => {
            this.productList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.page = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        //this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 服务类型改变触发方法 */
      changeProductClass(){
        this.queryParams.productclass = undefined;
        this.queryParams.productsubclass = undefined;
        getProductClass(this.queryParams).then(response => {
            this.productClassOptions = response.data;
          }
        );
        getProductSubClass(this.queryParams).then(response => {
            this.productSubClassOptions = response.data;
          }
        );
      },
      /** 产品类型改变触发方法 */
      changeProductSubClass(){
        this.queryParams.productsubclass = undefined;
        getProductSubClass(this.queryParams).then(response => {
            this.productSubClassOptions = response.data;
          }
        );
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.productno);
        this.single = selection.length!==1;
        this.multiple = !selection.length;
      },
      /** 财务复核按钮操作 */
      openFinalAudit(row) {
        const productno = row.productno || this.ids;
        this.$router.push({path: '/product/audition/info/'+productno});
      }
    }
  };
</script>
