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
          <i class="el-icon-s-data mr5"></i><span>产品定义列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:definition:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['system:definition:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['system:definition:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['system:definition:export']"
            >导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              :disabled="single"
              @click="openProductAttachment"
              v-hasPermi="['system:definition:openProductAttachment']"
            >服务产品条款
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
          <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width" fixed="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:information:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:information:remove']"
              >删除</el-button>
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
  import {listProduct, openView, getServiceType,  getProductClass, getProductSubClass, delProductInfo} from "@/api/product/definition";
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
        this.queryParams.productstate = "01";
        listProduct(this.queryParams).then(response => {
            this.productList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      /*// 表单重置
      reset() {
        this.form = {
          productno: null,
          productname: null,
          producttype: null,
          servicetype: null,
          costprice: null,
          normalprice: null,
          taxprice: null,
          servicelimit: null,
          servicelimitflag: null,
          callout: null,
          sendcontract: null,
          usebyoneself: null,
          allowedtocancel: null,
          salstartdate: null,
          salenddate: null,
          applysex: null,
          agemin: null,
          agemax: null,
          discounttype: null,
          cardtype: null,
          prooftype: null,
          productstate: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null,
          productcode: null,
          usertype: null,
          taxrate: null,
          refunddate: null,
          servicecost: null,
          productclass: null,
          productsubclass: null,
          exterproductname: null,
          amortizationflag: null,
          currency: null,
          isprovideservice: null,
          isprovideonline: null,
          isstandardproduct: null,
          serviceimpletype: null
        };
        this.resetForm("form");
      },*/
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
      /** 状态维护按钮操作 */
      audit() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.productno);
        this.single = selection.length!==1;
        this.multiple = !selection.length;
      },
      /** 新增按钮操作 */
      handleAdd() {
//        this.reset();
//        this.open = true;
//        this.title = "定义产品";
        this.$router.push({path: '/product/definition/info/0'});
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const productno = row.productno || this.ids;
        this.$router.push({path: '/product/definition/info/'+productno});
      },
      /** 服务产品条款 */
      openProductAttachment(row) {
        const productno = row.productno || this.ids;
        this.$router.push({path: '/product/definition/attachment/'+productno});
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const productnos = row.productno || this.ids;
        this.$confirm('是否确认删除产品定义编号为"' + productnos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProductInfo(productnos);
        }).then(() => {
          if(this.productList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.queryParams.productstate = "01";
        this.download('system/definition/export', {
          ...this.queryParams
        }, `产品信息列表.xlsx`)
      }
    }
  };
</script>
