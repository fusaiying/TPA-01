<template>
  <div class="app-container">
    <!-- 基础信息维护 start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 供应商编码 -->
          <el-form-item label="供应商编码">
            <el-input
              v-model="queryParams.servcomno"
              placeholder="请输入供应商编码"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 供应商名称 -->
          <el-form-item label="供应商名称">
            <el-input
              v-model="queryParams.chname"
              placeholder="请输入供应商名称"
              clearable
              @keyup.enter.native="handleQuery"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 供应商等级 -->
          <el-form-item label="供应商等级">
            <el-select v-model="queryParams.medicallevel"
                       placeholder="请选择供应商等级"
                       clearable
                       filterable
                       @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in medicalLevelOptions"
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
          <!-- 供应商类型 -->
          <el-form-item label="供应商类型">
            <el-select v-model="queryParams.servcomtype"
                       @keyup.enter.native="handleQuery"
                       clearable
                       filterable
                       placeholder="请选择供应商类型">
              <el-option
                v-for="dict in medicaltypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="所在地区">
            <el-cascader clearable
                         :options="areaSelectData"
                         filterable
                         @keyup.enter.native="handleQuery"
                         v-model="queryParams.selectedOptions" placeholder="请选择所在地区"/>
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

    <!-- card start -->
    <el-card class="box-card" shadow="hover">
      <!-- card title -->
      <div slot="header" class="clearfix">
        <i class="el-icon-s-grid mr5"></i>
        <span>供应商基础信息列表</span>
      </div>
      <!-- 新增、修改、删除、机构附件按钮 -->
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-col>
        <!-- 导出功能 -->
        <!--      <el-col :span="1.5">-->
        <!--        <el-button-->
        <!--          type="warning"-->
        <!--          icon="el-icon-download"-->
        <!--          size="mini"-->
        <!--          @click="handleExport"-->
        <!--          v-hasPermi="['provider:provider:export']"-->
        <!--        >导出-->
        <!--        </el-button>-->
        <!--      </el-col>-->
        <el-col :span="1.5">
          <el-button icon="el-icon-upload" size="mini" @click="toImport" type="info" :disabled="single">供应商附件
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <!-- 供应商列表 表格 -->
      <el-table v-loading="loading" :data="providerList" @selection-change="handleSelectionChange">

        <el-table-column type="selection" width="55" align="center"></el-table-column>

        <el-table-column label="供应商编码" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.servcomno}}</template>
        </el-table-column>

        <el-table-column label="供应商中文名称" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.chname}}</template>
        </el-table-column>

        <el-table-column label="供应商英文名称" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.enname}}</template>
        </el-table-column>

        <el-table-column label="供应商类型" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.servcomtype}}</template>
        </el-table-column>

        <el-table-column label="供应商等级" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.medicallevel}}</template>
        </el-table-column>

        <el-table-column label="省" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.province}}</template>
        </el-table-column>

        <el-table-column label="市" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.city}}</template>
        </el-table-column>

        <el-table-column label="区县" show-overflow-tooltip>
          <template slot-scope="scope">{{scope.row.district}}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
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
  </div>
</template>

<script>
  import {listProvider, delProvider, addProvider, updateProvider} from "@/api/provider/provider";
  import {regionData} from "element-china-area-data";

  export default {
    name: "Provider",
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

        // 查询参数 - 基础信息维护
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          selectedOptions: null,
        },
        // 表单参数
        form: {},
        // 机构类型字典 下拉
        medicaltypeOptions: [],
        // 机构等级字典 下拉
        medicalLevelOptions: [],
        // 地区区域
        areaSelectData: regionData,
      };
    },
    created() {
      // 组件一加载就查询所有
      this.getList();
      // 供应商类型 下拉
      this.getDicts("provider_servcomtype").then(response => {
        this.medicaltypeOptions = response.data;
      });
      // 供应商等级 下拉
      this.getDicts("provider_medicallevel").then(response => {
        this.medicalLevelOptions = response.data;
      });
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
      /** 新增按钮操作 */
      handleAdd() {
        this.$router.push({path: '/provider/manage/info/add'});
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const servcomno = row.servcomno || this.ids[0];
        this.$router.push('/provider/manage/info/edit/' + servcomno);
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const servcomnos = row.servcomno || this.ids;
        // 编号换行
        var servcomStr = '<br/>';
        for (var i = 0; i < servcomnos.length; i++) {
          servcomStr += servcomnos[i] + "<br/>";
        }
        this.$confirm('是否确认删除供应商编号为"' + servcomStr + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
          dangerouslyUseHTMLString: true,
        }).then(function () {
          return delProvider(servcomnos);
        }).then(() => {
          if (this.providerList.length == 1 && this.queryParams.pageNum != 1) {
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      // handleExport() {
      //   this.download('provider/provider/export', {
      //     ...this.queryParams
      //   }, `provider_info.xlsx`)
      // },
      /** 机构附件上传 */
      toImport(row) {
        const servcomno = row.servcomno || this.ids[0];
        this.$router.push({path: '/provider/manage/info/attachment/' + servcomno});
      },
    }
  };
</script>
