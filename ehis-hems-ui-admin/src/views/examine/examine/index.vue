<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="产品包名称" prop="productpackagename">
            <el-input
              v-model="queryParams.productpackagename"
              placeholder="请输入产品包名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="售价" prop="normalprice">
            <el-input
              v-model="queryParams.normalprice"
              placeholder="请输入售价"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="是否推送电子凭证" prop="sendcontract" label-width="140px">
            <el-select
              v-model="queryParams.sendcontract"
              placeholder="请选择是否推送凭证"
              clearable
            >
              <el-option
                v-for="dict in sysYesNoOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="卡单属性" prop="cardtype">
            <el-select
              v-model="queryParams.cardtype"
              placeholder="请选择卡单属性"
              clearable>
              <el-option
                v-for="dict in cardTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="产品包状态" prop="productstate2">
            <el-select
              v-model="queryParams.productstate2"
              placeholder="请选择产品包状态"
              clearable
            >
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
      <!-- 第三行 -->
      <el-row>
        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>

    <!-- 卡片、表格 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-edit-outline mr5"></i><span>审核列表</span>
        </div>
        <el-row :gutter="10" class="mb20">
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="maintain"
              v-hasPermi="['examine:examine:maintain']"
            >产品包维护
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="examineList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="产品包编码" align="center" prop="productpackageno"/>
          <el-table-column label="产品包名称" align="center" prop="productpackagename"/>
          <el-table-column label="售价" align="center" prop="normalprice"/>
          <el-table-column label="是否推送电子凭证" align="center" prop="sendcontract"/>
          <el-table-column label="卡单属性" align="center" prop="cardtype"/>
          <el-table-column label="产品包状态" align="center" prop="productstate2"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['examine:examine:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['examine:examine:remove']"
              >删除
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

    <!-- 添加或修改产品包审核对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="125px">
        <el-form-item label="产品包名称" prop="productpackagename">
          <el-input
            v-model="form.productpackagename"
            placeholder="请输入产品包名称"
          />
        </el-form-item>
        <el-form-item label="售价" prop="normalprice">
          <el-input
            v-model="form.normalprice"
            placeholder="请输入售价"
          />
        </el-form-item>
        <el-form-item label="是否推送电子凭证" prop="sendcontract">
          <el-select
            v-model="form.sendcontract"
            placeholder="请选择是否推送电子凭证"
          >
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="卡单属性" prop="cardtype">
          <el-select
            v-model="form.cardtype"
            placeholder="请选择卡单属性"
          >
            <el-option
              v-for="dict in cardTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品包状态" prop="productstate2">
          <el-select
            v-model="form.productstate2"
            placeholder="请选择产品包状态"
          >
            <el-option
              v-for="dict in productstate2Options"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="primary" @click="submitForm">确 定</el-button>
        <el-button size="mini" @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {listExamine, getExamine, delExamine, addExamine, updateExamine} from "@/api/examine/examine";

  export default {
    name: "Examine",
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
        // 产品包审核表格数据
        examineList: [],
        // YN是否字典
        sysYesNoOptions: [],
        // 卡单属性数据字典
        cardTypeOptions: [],
        // 产品状态数据字典
        productstate2Options: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          productpackagename: null,
          normalprice: null,
          sendcontract: null,
          cardtype: null,
          productstate2: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          productpackagename: [
            {required: true, message: "产品包名称不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      //产品是否字典
      this.getDicts("product_yesflag").then(response => {
        this.sysYesNoOptions = response.data;
      });
      //卡单属性数据字典
      this.getDicts("sys_card_type").then(response => {
        this.cardTypeOptions = response.data;
      });
      //产品状态数据字典
      this.getDicts("product_state").then(response => {
        this.productstate2Options = response.data;
      });
    },
    methods: {
      /** 查询产品包审核列表 */
      getList() {
        this.loading = true;
        listExamine(this.queryParams).then(response => {
          this.examineList = response.rows;
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
          productpackageno: null,
          productpackagename: null,
          normalprice: null,
          taxprice: null,
          sendcontract: null,
          salstartdate: null,
          salenddate: null,
          cardtype: null,
          prooftype: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null,
          userflag: null,
          productstate2: null,
          issendsms: null,
          maxusernum: null,
          isreplaceuser: null,
          exterpackagename: null
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
        this.ids = selection.map(item => item.productpackageno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加产品包审核";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const productpackageno = row.productpackageno || this.ids
        getExamine(productpackageno).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品包审核";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.productpackageno != null) {
              updateExamine(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addExamine(this.form).then(response => {
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
        const productpackagenos = row.productpackageno || this.ids;
        this.$confirm('是否确认删除产品包审核编号为"' + productpackagenos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delExamine(productpackagenos);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 产品包维护 */
      maintain(row) {
        const servcomno = row.servcomno || this.ids[0];
        this.$router.push({path: '/examine/examine/maintain/' + servcomno});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('examine/examine/export', {
          ...this.queryParams
        }, `examine_examine.xlsx`)
      }
    }
  };
</script>
