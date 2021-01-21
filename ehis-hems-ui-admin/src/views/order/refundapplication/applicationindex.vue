<template>
  <div class="app-container">
    <el-row class="mb8">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
        <el-col :span="8">
          <el-form-item label="客户姓名">
            <el-input
              v-model="queryParams.customerName"
              placeholder="请输入客户姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别">
            <el-select v-model="form.sex"
                       clearable
                       placeholder="请选择客户性别">
              <el-option v-for="dict in sexOptions"
                         :key="dict.dictValue"
                         :label="dict.dictValue+'-'+dict.dictLabel"
                         :value="dict.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型">
            <el-select v-model="form.idType"
                       clearable
                       placeholder="请选择证件类型">
              <el-option v-for="dict in idTypeOptions"
                         :key="dict.dictValue"
                         :label="dict.dictValue+'-'+dict.dictLabel"
                         :value="dict.dictValue">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码">
            <el-input
              v-model="queryParams.idNo"
              placeholder="请输入证件号码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号">
            <el-input
              v-model="queryParams.telNo"
              placeholder="请输入手机号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客户类型">
            <el-select v-model="form.customerType"
                       clearable
                       placeholder="请选择客户类型">
              <el-option v-for="dict in customerTypeOptions"
                         :key="dict.dictValue"
                         :label="dict.dictValue+'-'+dict.dictLabel"
                         :value="dict.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="订单号">
            <el-input
              v-model="queryParams.orderNo"
              placeholder="请输入订单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务卡号">
            <el-input
              v-model="queryParams.cardNo"
              placeholder="请输入服务卡号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团单号">
            <el-input
              v-model="queryParams.groupNo"
              placeholder="请输入团单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-row :gutter="10" class="mb8">
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-row>

      </el-form>
    </el-row>

    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <!-- 卡片标题 -->
        <div slot="header" class="clearfix">
          <i class="el-icon-tickets mr5"></i><span>订单列表</span>
        </div>

        <!-- 操作按钮 -->
        <el-row :gutter="10" class="mb8">
          <el-button
            type="danger"
            size="mini"
            :disabled="multiple"
            icon="el-icon-edit"
            @click="publishClick"
            v-hasPermi="['system:definition:add']">
            退费申请
          </el-button>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <!-- 表格 -->
        <el-table v-loading="loading" :data="informationList1" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="45" align="center"/>
          <el-table-column label="团单号" align="center" prop="iVip"/>
          <el-table-column label="订单名称" align="center" prop="hVip"/>
          <el-table-column label="个单号" align="center" width="80px" prop="purName"/>
          <el-table-column label="服务卡号" align="center" width="120px" prop="purMobile"/>
          <el-table-column label="客户健康号" align="center" width="120px" prop="idNo"/>
          <el-table-column label="客户姓名" align="center" width="120px" prop="contNo"/>
          <el-table-column label="手机号" align="center" prop="payType"/>
          <el-table-column label="性别" align="center" prop="userName"/>
          <el-table-column label="出生日期" align="center" width="120px" prop="userMobile"/>
          <el-table-column label="证件类型" align="center" width="120px" prop="userIdno"/>
          <el-table-column label="证件号码" align="center" width="120px" prop="productPackageNo"/>
          <el-table-column label="操作" align="center" width="115">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="publishClick"
                v-hasPermi="['system:information:edit']"
              >退费申请
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
  import {listCustomer} from "@/api/query/customer";

  export default {
    name: "Query",
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
        // 性别字典
        sexOptions: [],
        //证件类型字典
        idTypeOptions: [],
        //客户类型
        customerTypeOptions: [],
        // 产品定义表格数据


        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
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
        informationList1: []
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_user_sex").then(response => {
        this.sexOptions = response.data;
      });
      this.getDicts("id_type").then(response => {
        this.idTypeOptions = response.data;
      });
      this.getDicts("customer_type").then(response => {
        this.customerTypeOptions = response.data;
      });

    },
    methods: {
      /** 查询产品定义列表 */
      getList() {
        this.loading = true;
        listCustomer(this.queryParams).then(response => {
          this.informationList1 = response.rows;
          this.total = response.total;
          this.loading = false;
        })
      },

      publishClick() {
        this.$router.push({
          path: '/order/refundapplication/application/0'
        });
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
