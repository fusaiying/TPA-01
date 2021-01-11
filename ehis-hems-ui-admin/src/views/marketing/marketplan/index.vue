<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="营销方案编码" prop="programno">
            <el-input
              v-model="queryParams.programno"
              placeholder="请输入营销方案编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="营销方案名称" prop="programname">
            <el-input
              v-model="queryParams.programname"
              placeholder="请输入营销方案名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="合作伙伴" prop="channelno">
            <el-input
              v-model="queryParams.channelno"
              placeholder="请输入合作伙伴"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="销售平台" prop="saleplatform">
            <el-input
              v-model="queryParams.saleplatform"
              placeholder="请输入销售平台"
              clearable
              @keyup.enter.native="handleQuery"/>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="有效状态" prop="productstate">
            <el-select
              v-model="queryParams.productstate"
              placeholder="请输入有效状态"
              clearable>
              <el-option
                v-for="dict in productStates"
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
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i><span>营销方案列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['marketing:marketplan:handleAdd']"
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
              v-hasPermi="['marketing:marketplan:edit']"
            >修改
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['marketing:marketplan:remove']"
            >删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['marketing:marketplan:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    <el-table v-loading="loading" :data="marketplanList" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="营销方案编码" align="center" prop="programno" width="100"/>
      <el-table-column label="营销方案名称" align="center" prop="programname" width="100"/>
      <el-table-column label="优惠方式" align="center" prop="preferentialway"/>
      <el-table-column label="合作伙伴" align="center" prop="channelno"/>
      <el-table-column label="销售平台" align="center" prop="saleplatform"/>
      <el-table-column label="佣金方式" align="center" prop="commissiontype"/>
      <el-table-column label="佣金" align="center" prop="commission"/>
      <el-table-column label="手续费" align="center" prop="servicefee"/>
      <el-table-column label="销售起期" align="center" prop="salstartdatetime" width="80">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salstartdatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="销售止期" align="center" prop="salenddatetime" width="80">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salenddatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效状态" align="center" prop="productstate"/>
      <el-table-column label="备用字段1" align="center" prop="alternatefield1"/>
      <el-table-column label="备用字段2" align="center" prop="alternatefield2"/>
      <el-table-column label="操作员" align="center" prop="operator"/>
      <el-table-column label="入机日期" align="center" prop="makedatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.makedatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入机时间" align="center" prop="maketime"/>
      <el-table-column label="最后一次操作员" align="center" prop="modifyoperator"/>
      <el-table-column label="最后一次修改日期" align="center" prop="modifydatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifydatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后一次修改时间" align="center" prop="modifytime"/>
      <el-table-column label="手续费方式" align="center" prop="feetype"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180" fixed ="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['marketing:marketplan:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['marketing:marketplan:remove']"
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

    <!-- 添加或修改marketplan对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="营销方案名称" prop="programname">
          <el-input v-model="form.programname" placeholder="请输入营销方案名称"/>
        </el-form-item>
        <el-form-item label="优惠方式" prop="preferentialway">
          <el-input v-model="form.preferentialway" placeholder="请输入优惠方式"/>
        </el-form-item>
        <el-form-item label="合作伙伴" prop="channelno">
          <el-input v-model="form.channelno" placeholder="请输入合作伙伴"/>
        </el-form-item>
        <el-form-item label="销售平台" prop="saleplatform">
          <el-input v-model="form.saleplatform" placeholder="请输入销售平台"/>
        </el-form-item>
        <el-form-item label="佣金方式" prop="commissiontype">
          <el-select v-model="form.commissiontype" placeholder="请选择佣金方式">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="佣金" prop="commission">
          <el-input v-model="form.commission" placeholder="请输入佣金"/>
        </el-form-item>
        <el-form-item label="手续费" prop="servicefee">
          <el-input v-model="form.servicefee" placeholder="请输入手续费"/>
        </el-form-item>
        <el-form-item label="销售起期" prop="salstartdatetime">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.salstartdatetime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择销售起期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="销售止期" prop="salenddatetime">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.salenddatetime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择销售止期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="有效状态" prop="productstate">
          <el-input v-model="form.productstate" placeholder="请输入有效状态"/>
        </el-form-item>
        <el-form-item label="备用字段1" prop="alternatefield1">
          <el-input v-model="form.alternatefield1" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="备用字段2" prop="alternatefield2">
          <el-input v-model="form.alternatefield2" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="操作员" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入操作员"/>
        </el-form-item>
        <el-form-item label="入机日期" prop="makedatetime">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.makedatetime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择入机日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="入机时间" prop="maketime">
          <el-input v-model="form.maketime" placeholder="请输入入机时间"/>
        </el-form-item>
        <el-form-item label="最后一次操作员" prop="modifyoperator">
          <el-input v-model="form.modifyoperator" placeholder="请输入最后一次操作员"/>
        </el-form-item>
        <el-form-item label="最后一次修改日期" prop="modifydatetime">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.modifydatetime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择最后一次修改日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后一次修改时间" prop="modifytime">
          <el-input v-model="form.modifytime" placeholder="请输入最后一次修改时间"/>
        </el-form-item>
        <el-form-item label="手续费方式" prop="feetype">
          <el-select v-model="form.feetype" placeholder="请选择手续费方式">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
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
  import {
    listMarketplan,
    getMarketplan,
    delMarketplan,
    addMarketplan,
    updateMarketplan,
    openView
  } from "@/api/marketing/marketplan";

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
        // marketplan表格数据
        marketplanList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          programno: null,
          programname: null,
          preferentialway: null,
          channelno: null,
          saleplatform: null,
          commissiontype: null,
          commission: null,
          servicefee: null,
          salstartdatetime: null,
          salenddatetime: null,
          productstate: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedatetime: null,
          maketime: null,
          modifyoperator: null,
          modifydatetime: null,
          modifytime: null,
          feetype: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          programname: [
            {required: true, message: "营销方案名称不能为空", trigger: "blur"}
          ],
          preferentialway: [
            {required: true, message: "优惠方式不能为空", trigger: "blur"}
          ],
          channelno: [
            {required: true, message: "合作伙伴不能为空", trigger: "blur"}
          ],
          servicefee: [
            {required: true, message: "手续费不能为空", trigger: "blur"}
          ],
          salstartdatetime: [
            {required: true, message: "销售起期不能为空", trigger: "blur"}
          ],
          productstate: [
            {required: true, message: "有效状态不能为空", trigger: "blur"}
          ],
          operator: [
            {required: true, message: "操作员不能为空", trigger: "blur"}
          ],
          makedatetime: [
            {required: true, message: "入机日期不能为空", trigger: "blur"}
          ],
          maketime: [
            {required: true, message: "入机时间不能为空", trigger: "blur"}
          ],
          modifydatetime: [
            {required: true, message: "最后一次修改日期不能为空", trigger: "blur"}
          ],
          modifytime: [
            {required: true, message: "最后一次修改时间不能为空", trigger: "blur"}
          ],
        },
        productStates:[]
      };
    },
    created() {
      this.getList();
      this.getDicts("market_product_state").then(response => {
        this.productStates = response.data;
      });
    },
    methods: {
      /** 查询marketplan列表 */
      getList() {
        this.loading = true;
        listMarketplan(this.queryParams).then(response => {
          this.marketplanList = response.rows;
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
          programno: null,
          programname: null,
          preferentialway: null,
          channelno: null,
          saleplatform: null,
          commissiontype: null,
          commission: null,
          servicefee: null,
          salstartdatetime: null,
          salenddatetime: null,
          productstate: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedatetime: null,
          maketime: null,
          modifyoperator: null,
          modifydatetime: null,
          modifytime: null,
          feetype: null
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
        this.ids = selection.map(item => item.programno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.$router.push({path: '/marketing/marketPlanAdd/addPlan'});
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const programno = row.programno || this.ids;
        console.log(programno);
        this.$router.push({path: '/marketing/marketPlanAdd/updatePlan/'+programno});
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.programno != null) {
              updateMarketplan(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addMarketplan(this.form).then(response => {
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
        const programnos = row.programno || this.ids;
        this.$confirm('是否确认删除marketplan编号为"' + programnos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delMarketplan(programnos);
        }).then(() => {
          if(this.marketplanList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('marketing/marketplan/export', {
          ...this.queryParams
        }, `marketing_marketplan.xlsx`)
      }
    }
  };
</script>
