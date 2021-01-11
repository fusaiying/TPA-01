<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务项目编码">
              <el-input
                v-model="queryParams.projectcode"
                placeholder="请输入服务项目编码"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务项目名称">
              <el-input
                v-model="queryParams.projectname"
                placeholder="请输入服务项目名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务项目分类">
              <el-select v-model="queryParams.projecttype"
                         clearable
                         placeholder="请选择服务项目分类">
                <el-option
                  v-for="dict in projectTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-row>

    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-edit-outline mr5"></i>
          <span>服务项目列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['product:project:add']"
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
              v-hasPermi="['product:project:edit']"
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
              v-hasPermi="['product:project:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="服务项目编码" align="center" prop="projectcode" show-overflow-tooltip/>
          <el-table-column label="服务项目名称" align="center" prop="projectname" show-overflow-tooltip/>
          <el-table-column label="服务项目分类" align="center" prop="projecttype" show-overflow-tooltip/>
          <el-table-column label="创建日期" align="center" prop="makedate" show-overflow-tooltip/>
          <el-table-column label="创建时间" align="center" prop="maketime" show-overflow-tooltip/>
          <el-table-column label="创建人" align="center" prop="operator" show-overflow-tooltip/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['product:project:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['product:project:remove']"
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

  </div>
</template>

<script>
  import {
    listProjectInfo,
    delProjectInfo
  } from "@/api/product/project";

  export default {
    name: "ProjectList",
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
        // 产品项目信息表格数据
        projectList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 服务项目分类数据字典
        projectTypeOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectcode: null,
          projectname: null,
          projecttype: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.getList();
      //服务项目分类下拉列表
      this.getDicts("product_service_imple_type").then(response => {
        this.projectTypeOptions = response.data;
      });
    },
    methods: {
      /** 查询服务项目信息列表 */
      getList() {
        this.loading = true;
        listProjectInfo(this.queryParams).then(response => {
          this.projectList = response.rows;
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
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.projectcode);
        this.single = selection.length !== 1;
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.$router.push({path: '/product/project/add'});
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const projectcodes = row.projectcode || this.ids[0];
        this.$router.push('/product/project/edit/' + projectcodes);
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const projectcodes = row.projectcode || this.ids;
        this.$confirm('是否确认删除产品包信息编号为"' + projectcodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delProjectInfo(projectcodes);
        }).then(() => {
          if(this.projectList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('product/project/export', {
          ...this.queryParams
        }, `product_projectinfo.xlsx`)
      }
    }
  };
</script>
