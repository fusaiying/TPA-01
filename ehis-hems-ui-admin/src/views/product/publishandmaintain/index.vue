<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
        <el-col :span="8">
          <el-form-item label="产品编码">
            <el-input
              v-model="queryParams.productno"
              placeholder="产品编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品名称">
            <el-input
              v-model="queryParams.productname"
              placeholder="产品名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务类型" prop="servicetype">
            <el-select v-model="queryParams.servicetype"
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
            <el-select v-model="queryParams.productclass"
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
            <el-select v-model="queryParams.productsubclass"
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
<!--        <el-col :span="8">-->
<!--          <el-form-item label="销售价格">-->
<!--            <el-input-->
<!--              v-model="queryParams.normalprice"-->
<!--              placeholder="销售价格"-->
<!--              clearable-->
<!--              @keyup.enter.native="handleQuery"-->
<!--            />-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :span="8">
          <el-form-item label="允许取消预约" prop="allowedtocancel">
            <el-select v-model="queryParams.allowedtocancel"
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
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-col>
    </el-row>

    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-upload mr5"></i><span>产品发布列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="publishClick"
              v-hasPermi="['system:definition:pushlish']">产品发布
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" align="center"/>
      <el-table-column label="产品编号" align="center" prop="productno"/>
      <el-table-column label="产品名称" align="center" prop="productname"/>
      <el-table-column label="服务类型" align="center" prop="servicetype"/>
      <el-table-column label="产品类别" align="center" prop="productclass"/>
      <el-table-column label="产品细类" align="center" prop="productsubclass"/>
      <el-table-column label="有效状态" align="center" prop="productstate"/>
<!--      <el-table-column label="产品销售价格" align="center" prop="normalprice"/>-->
      <el-table-column label="允许取消预约" align="center" prop="allowedtocancel"/>
      <el-table-column label="服务限期" align="center" prop="servicelimit"/>
      <el-table-column label="限期单位" align="center" prop="servicelimitflag">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salstartdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="115" class-name="mini-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="publishClick(scope.row)"
            v-hasPermi="['product:information:publish']"
          >产品发布
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
  import {
    listProduct,
    getServiceType,
    getProductClass,
    getProductSubClass
  } from "@/api/product/definition";


  export default {
    name: "Product",
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
        // 产品定义表格数据
        informationList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 服务类型数据字典
        serviceTypeOptions: [],
        // 产品类别字典
        productClassOptions: [],
        // 产品细类字典
        productSubClassOptions: [],
        // 允许取消预约字典
        allowedToCancelOptions: [],
        // 查询参数
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
        },
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },
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
      /** 查询产品定义列表 */
      getList() {
        this.loading = true;
        this.queryParams.productstate = "06";
        listProduct(this.queryParams).then(response => {
          this.informationList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
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
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 产品发布按钮操作 */
      publishClick(row) {
        const productno = row.productno || this.ids;
        this.$router.push({path: '/product/publishandmaintain/info/'+productno});
      }
    }
  };

</script>
