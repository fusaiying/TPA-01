<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="客户姓名">
            <el-input
              v-model="queryParams.productno"
              placeholder="客户姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客户手机号">
            <el-input
              v-model="queryParams.productno"
              placeholder="客户手机号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客户证件号">
            <el-input
              v-model="queryParams.productno"
              placeholder="客户证件号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="客户类型">
            <el-select>
              <el-option>客户类型</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="保单号">
            <el-input
              v-model="queryParams.productno"
              placeholder="保单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="保险名称">
            <el-input
              v-model="queryParams.productno"
              placeholder="保险名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="8">
          <el-form-item label="增值服务代码">
            <el-input
              v-model="queryParams.productno"
              placeholder="增值服务代码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品包编码">
            <el-select>
              <el-option>产品包编码</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品子项编码">
            <el-select>
              <el-option>产品子项编码</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="服务卡号">
            <el-input
              v-model="queryParams.productno"
              placeholder="服务卡号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团体客户">
            <el-select >
              <el-option>团体客户</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="合作伙伴">
            <el-select>
              <el-option>合作伙伴</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="订单号">
            <el-input
              v-model="queryParams.productno"
              placeholder="订单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="订单录入时间">
            <el-input
              v-model="queryParams.productno"
              placeholder="订单录入时间"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="订单状态">
            <el-input
              v-model="queryParams.productno"
              placeholder="订单状态"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="订单生效时间">
            <el-input
              v-model="queryParams.productno"
              placeholder="订单生效时间"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>


      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-col>
      </el-row>

    </el-form>



    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <i class="el-icon-s-grid mr5"></i><span>客户信息列表</span>
      </div>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <template>
            <el-button
              type="warning"
              size="mini"
              :disabled="multiple"
              icon="el-icon-edit"
              @click="publishClick"
              v-hasPermi="['system:definition:add']">
              详细信息
            </el-button>
          </template>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
      <el-table v-loading="loading" :data="informationList1" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="45" align="center"/>
        <el-table-column label="公司VIP等级" align="center" prop="iVip"/>
        <el-table-column label="HMC VIP等级" align="center" prop="hVip"/>
        <el-table-column label="购买人" align="center" width="80px" prop="purName"/>
        <el-table-column label="购买人手机号" align="center" width="120px" prop="purMobile"/>
        <el-table-column label="购买人身份证号" align="center" width="120px" prop="idNo"/>
        <el-table-column label="保单号" align="center" width="120px" prop="contNo"/>
        <el-table-column label="结算方式" align="center" prop="payType"/>
        <el-table-column label="使用人姓名" align="center" prop="userName"/>
        <el-table-column label="使用人手机号" align="center" width="120px" prop="userMobile"/>
        <el-table-column label="使用人身份证号" align="center" width="120px" prop="userIdno"/>
        <el-table-column label="产品包编码" align="center" width="120px" prop="productPackageNo"/>
        <el-table-column label="产品包名称" align="center" width="120px" prop="productPackageName"/>
        <el-table-column label="产品子项编码" align="center" width="120px" prop="productNo"/>
        <el-table-column label="产品子项名称" align="center" width="120px" prop="productName"/>
        <el-table-column label="服务起期" align="center" width="120px" prop="salstartDate"/>
        <el-table-column label="服务止期" align="center" width="120px" prop="salendDate"/>
        <el-table-column label="服务总次数" align="center" prop="times"/>
        <el-table-column label="已使用次数" align="center" prop="serviceTimes"/>
        <el-table-column label="剩余次数" align="center" prop="surplusTimes"/>
        <el-table-column label="服务卡号" align="center" prop="serviceCardNo"/>
        <el-table-column label="操作" align="center" width="115" class-name="mini-padding fixed-width" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="publishClick"
              v-hasPermi="['system:information:edit']"
            >详细信息
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
          path: '/query/customerInfo/detailInfo/0'
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
