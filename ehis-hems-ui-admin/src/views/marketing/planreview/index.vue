<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="营销方案编码">
            <el-input
              v-model="queryParams.productno"
              placeholder="请输入营销方案编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="营销方案名称">
            <el-input
              v-model="queryParams.productName"
              placeholder="请输入营销方案名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="合作伙伴">
            <el-select
              placeholder="请选择合作伙伴"
              clearable>
              <el-option
                v-for="dict in productTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="销售平台">
            <el-input
              v-model="queryParams.productPlatform"
              placeholder="请输入销售平台"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="有效状态">
            <el-select
              v-model="queryParams.productstate2"
              clearable
              placeholder="请选择有效状态">
              <el-option
                v-for="dict in productstate2Options"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>


    <!-- 卡片 表格-->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-check mr5"></i><span>方案审核列表</span>
        </div>
        <el-row :gutter="10" class="mb20">
          <el-col :span="1.5">
            <template>
              <el-button
                type="success"
                size="mini"
                @click="publishClick"
                :disabled="single"
                icon="el-icon-edit"
                v-hasPermi="['system:definition:add']">
                营销方案维护
              </el-button>
            </template>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" align="center"/>
      <el-table-column label="营销方案编码" align="center" prop="programno"/>
      <el-table-column label="营销方案名称" align="center" prop="programname"/>
      <el-table-column label="合作伙伴" align="center" prop="preferentialway"/>
      <el-table-column label="销售平台" align="center" prop="saleplatform"/>
      <el-table-column label="有效状态" align="center" prop="productstate"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180" fixed ="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="publishClick(scope.row)"
            v-hasPermi="['system:definition:add']"
          >营销方案维护
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
  import {listMarketplan} from "@/api/marketing/marketplan";

  export default {
    name: "Marketplan",
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
        // 产品状态数据字典
        productstate2Options:[],
        // 银行名称数据字典
        bankNameOptions:[],
        // 产品类型数据字典
        productTypeOptions:[],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10
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
      // 产品类型数据字典
      this.getDicts("product_type").then(response => {
        this.productTypeOptions = response.data;
      });
      // 产品状态数据字典
      this.getDicts("product_state").then(response => {
        this.productstate2Options = response.data;
      });
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
        listMarketplan(this.queryParams).then(response => {
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
      /** 修改 营销方案维护 按钮操作*/
      publishClick(row) {
        const programno = row.programno || this.ids[0];
        this.$router.push({path: '/marketing/planreview/planreview/0'});
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
  }

</script>

<style scoped>

</style>
