<template>
  <div class="app-container">
    <!-- 查询表单 start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 险种名称 -->
          <el-form-item label="险种名称">
            <el-select v-model="queryParams.riskname"
                       placeholder="请选择险种名称"
                       clearable
                       multiple
                       collapse-tags
                       filterable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in riskname"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 客户等级 -->
          <el-form-item label="客户等级">
            <el-select v-model="queryParams.customerlevel"
                       placeholder="请选择客户等级"
                       clearable
                       multiple
                       collapse-tags
                       filterable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in customerlevel"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 客户标签 -->
          <el-form-item label="客户标签">
            <el-select v-model="queryParams.customerlabel"
                       placeholder="请选择客户等级"
                       clearable
                       multiple
                       collapse-tags
                       filterable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in customerlabel"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 分配产品 -->
          <el-form-item label="分配产品">
            <el-select v-model="queryParams.distriproduct"
                       placeholder="请选择分配产品"
                       clearable
                       multiple
                       collapse-tags
                       filterable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in distriproduct"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
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
        <span>查询结果</span>
      </div>
      <!-- 新增规则、批量发布按钮 -->
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增规则</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" icon="el-icon-edit" size="mini" :disabled="multiple" @click="handlePublish">批量发布
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <!-- 供应商列表 表格 -->
      <el-table v-loading="loading" :data="providerList" @selection-change="handleSelectionChange">

        <el-table-column type="selection" width="55" align="center"></el-table-column>

        <el-table-column label="规则ID" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.servcomno}}</template>
        </el-table-column>

        <el-table-column label="规则条件" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.chname}}</template>
        </el-table-column>

        <el-table-column label="分配产品" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.enname}}</template>
        </el-table-column>

        <el-table-column label="有效期" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.servcomtype}}</template>
        </el-table-column>

        <el-table-column label="添加日期" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.medicallevel}}</template>
        </el-table-column>

        <el-table-column label="状态" show-overflow-tooltip align="center">
          <template slot-scope="scope">{{scope.row.province}}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑规则</el-button>
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
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="125px">
        <el-row>
          <el-col :span="12">
            <!-- 险种名称 -->
            <el-form-item label="险种名称">
              <el-select v-model="form.riskname"
                         placeholder="请选择险种名称"
                         clearable
                         multiple
                         collapse-tags
                         filterable
                         @keyup.enter.native="handleQuery">
                <el-option
                  v-for="dict in riskname"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' + dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- 客户等级 -->
            <el-form-item label="客户等级">
              <el-select v-model="form.customerlevel"
                         placeholder="请选择客户等级"
                         clearable
                         multiple
                         collapse-tags
                         filterable
                         @keyup.enter.native="handleQuery">
                <el-option
                  v-for="dict in customerlevel"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' + dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <!-- 客户标签 -->
            <el-form-item label="客户标签">
              <el-select v-model="form.customerlabel"
                         placeholder="请选择客户等级"
                         clearable
                         multiple
                         collapse-tags
                         filterable
                         @keyup.enter.native="handleQuery">
                <el-option
                  v-for="dict in customerlabel"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' + dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- 分配产品 -->
            <el-form-item label="分配产品">
              <el-select v-model="form.distriproduct"
                         placeholder="请选择分配产品"
                         clearable
                         multiple
                         collapse-tags
                         filterable
                         @keyup.enter.native="handleQuery">
                <el-option
                  v-for="dict in distriproduct"
                  :key="dict.productno"
                  :label="dict.productno + ' - ' + dict.productname"
                  :value="dict.productno"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="有效期">
              <el-date-picker
                v-model="form.period"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
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
  import {getProductDict} from "@/api/benefit/ruleconfig";

  export default {
    name: "RuleConfig",
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
        // 显示搜索条件 - 基础
        showSearch: true,
        // 总条数
        total: 0,
        // 供应商管理表格数据
        providerList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,

        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          riskname: '',
          customerlevel: '',
          customerlabel: '',
          distriproduct: '',
        },
        // 弹框表单
        form: {
          riskname: '',
          customerlevel: '',
          customerlabel: '',
          distriproduct: '',
          period: '',
        },
        // rules校验
        rules: {},
        // 险种名称
        riskname: [],
        // 客户等级
        customerlevel: [],
        // 客户标签
        customerlabel: [],
        // 分配产品
        distriproduct: [],
      };
    },
    created() {
      // 组件一加载就查询所有
      this.getList();
      // 客户等级 下拉
      this.getDicts("benefit_customerlevel").then(response => {
        this.customerlevel = response.data;
      });
      // 客户标签 下拉
      this.getDicts("benefit_customerlabel").then(response => {
        this.customerlabel = response.data;
      });
      // 分配产品 下拉
      getProductDict().then(response => {
        this.distriproduct = response.data;
      })
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
      /** 新增规则按钮 */
      handleAdd() {
        this.open = true;
        this.title = "新增规则";
      },
      /** 批量发布 */
      handlePublish() {
        this.msgSuccess("批量发布")
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.open = true;
        this.title = "编辑规则";
      },
      /** 弹窗确定按钮 */
      submitForm() {
        this.open = false;
      },
      /** 弹窗取消按钮 */
      cancel() {
        this.open = false;
        // this.reset();
      }
    }
  };
</script>
