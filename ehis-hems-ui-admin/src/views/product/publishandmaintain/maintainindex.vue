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
        <el-col :span="8">
          <el-form-item label="销售价格">
            <el-input
              v-model="queryParams.normalprice"
              placeholder="销售价格"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="  允许取消预约" prop="allowedtocancel">
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
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-col>
    </el-row>

    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-tools mr5"></i><span>状态维护列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-edit"
              :disabled="multiple"
              @click="publishClick"
              v-hasPermi="['system:definition:add']">状态维护
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" align="center"/>
      <el-table-column label="序号" align="center" prop="productid"/>
      <el-table-column label="产品编号" align="center" prop="productno"/>
      <el-table-column label="产品名称" align="center" prop="productname"/>
      <el-table-column label="服务类型" align="center" prop="servicetype"/>
      <el-table-column label="产品类别" align="center" prop="productclass"/>
      <el-table-column label="产品细类" align="center" prop="productsubclass"/>
      <el-table-column label="有效状态" align="center" prop="productstate"/>
      <el-table-column label="产品销售价格" align="center" prop="normalprice"/>
      <el-table-column label="允许取消预约" align="center" prop="allowedtocancel"/>
      <el-table-column label="服务限期" align="center" prop="servicelimit"/>
      <el-table-column label="限期单位" align="center" prop="servicelimitflag">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salstartdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="115" class-name="mini-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="publishClick"
            v-hasPermi="['system:information:edit']"
          >状态维护
          </el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column label="操作" align="center" class-name="mini-padding fixed-width"> &ndash;&gt;-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-button-->
      <!--            size="mini"-->
      <!--            type="text"-->
      <!--            icon="el-icon-edit"-->
      <!--            @click="handleUpdate(scope.row)"-->
      <!--            v-hasPermi="['system:information:edit']"-->
      <!--          >修改-->
      <!--          </el-button>-->
      <!--          <el-button-->
      <!--            size="mini"-->
      <!--            type="text"-->
      <!--            icon="el-icon-delete"-->
      <!--            @click="handleDelete(scope.row)"-->
      <!--            v-hasPermi="['system:information:remove']"-->
      <!--          >删除-->
      <!--          </el-button>-->
      <!--        </template>-->
      <!--        &ndash;&gt;-->
      <!--      </el-table-column>-->
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
    openView,
    getServiceType,
    getProductClass,
    getProductSubClass,
    getInformation,
    delInformation,
    addInformation,
    updateInformation
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
          productcode: undefined,
          productname: undefined,
          servicetype: undefined,
          productclass: undefined,
          productsubclass: undefined,
          productprice: undefined,
          allowedtocancel: undefined
        },
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 表单校验
        rules: {
          productno: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          productname: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          producttype: [
            {required: true, message: "产品ID不能为空", trigger: "change"}
          ],
          servicetype: [
            {required: true, message: "产品ID不能为空", trigger: "change"}
          ],
          servicelimit: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          servicelimitflag: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          salstartdate: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          productstate: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          operator: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          makedate: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          maketime: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          modifydate: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
          modifytime: [
            {required: true, message: "产品ID不能为空", trigger: "blur"}
          ],
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
      /** 查询产品定义列表 */
      getList() {
        this.loading = true;
        listProduct(this.queryParams).then(response => {
          this.informationList = response.rows;
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
          productid: null,
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
      },
      publishClick() {
        this.$router.push({path: '/product/publishandmaintain/maintain/0'});
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
      /** 服务类型改变触发方法 */
      changeProductClass() {
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
      changeProductSubClass() {
        this.queryParams.productsubclass = undefined;
        getProductSubClass(this.queryParams).then(response => {
            this.productSubClassOptions = response.data;
          }
        );
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.productid)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加产品定义";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const productid = row.productid || this.ids
        getInformation(productid).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品定义";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.productid != null) {
              updateInformation(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addInformation(this.form).then(response => {
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
        const productids = row.productid || this.ids;
        this.$confirm('是否确认删除产品定义编号为"' + productids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delInformation(productids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/information/export', {
          ...this.queryParams
        }, `system_information.xlsx`)
      }
    }
  };

</script>
