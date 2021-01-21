<template>
  <div class="app-container">
    <!-- 基础信息维护 start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">

      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 服务类型 -->
          <el-form-item label="服务类型">
            <el-input
              v-model="queryParams.serviceType"
              placeholder="请输入服务类型"
              clearable
              @keyup.enter.native="handleQuery"></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 服务名称 -->
          <el-form-item label="服务名称">
            <el-input
              v-model="queryParams.serviceName"
              placeholder="请输入服务名称"
              clearable
              @keyup.enter.native="handleQuery"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row>
        <el-col>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-home mr5"></i><span>服务流程列表</span>
        </div>

        <!-- 按钮 -->
        <el-row :gutter="10" class="mb10">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['provider:provider:add']"
              :disabled="single"
            >服务流程配置
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button icon="el-icon-upload" size="mini" @click="queueConfigure" type="info" :disabled="single"
                       v-hasPermi="['provider:provider:export']"
            >服务队列配置
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="multiple"
              @click="handleUpdate"
              v-hasPermi="['provider:provider:edit']"
            >发布
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['provider:provider:remove']"
            >禁用
            </el-button>
          </el-col>

          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <!-- 服务流程列表 -->
        <el-table v-loading="loading" :data="serviceList" @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55" align="center">
          </el-table-column>

          <el-table-column
            label="序号">
            <template slot-scope="scope">{{ scope.row.index }}</template>
          </el-table-column>

          <el-table-column
            label="服务代码">
            <template slot-scope="scope">{{ scope.row.serviceNo }}</template>
          </el-table-column>

          <el-table-column
            label="服务名称">
            <template slot-scope="scope">{{ scope.row.serviceName }}</template>
          </el-table-column>

          <el-table-column
            label="服务类别">
            <template slot-scope="scope">{{ scope.row.serviceType }}</template>
          </el-table-column>

          <el-table-column
            label="是否配置服务流程">
            <template slot-scope="scope">{{ scope.row.configProgress }}</template>
          </el-table-column>

          <el-table-column
            label="是否配置服务队列">
            <template slot-scope="scope">{{ scope.row.configQueue}}</template>
          </el-table-column>

          <el-table-column
            label="当前状态">
            <template slot-scope="scope">{{ scope.row.currentStatus}}</template>
          </el-table-column>

          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-check"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['provider:provider:edit']"
              >发布
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-close"
                @click="handleDelete(scope.row)"
                v-hasPermi="['provider:provider:remove']"
              >禁用
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
  export default {
    name: "index",
    data() {
      return {
        // 查询参数 - 基础信息维护
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          serviceType: null,
          serviceName: null,
        },
        // 显示搜索条件 - 基础
        showSearch: true,
        //加载转圈
        loading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 表格数据总数
        total: 3,
        // 表格数据
        serviceList: [
          {
            index: '1',
            serviceNo: 'CK001',
            serviceName: '齿科服务',
            serviceType: '齿科服务',
            configProgress: '是',
            configQueue: '是',
            currentStatus: '发布',
          },
          {
            index: '2',
            serviceNo: 'CK002',
            serviceName: '齿科服务',
            serviceType: '齿科服务',
            configProgress: '是',
            configQueue: '是',
            currentStatus: '发布',
          },
          {
            index: '3',
            serviceNo: 'CK003',
            serviceName: '齿科服务',
            serviceType: '齿科服务',
            configProgress: '是',
            configQueue: '是',
            currentStatus: '发布',
          },
        ],
      }
    },
    methods: {
      // 查询
      handleQuery() {
      },
      // 重置
      resetQuery() {
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.index);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      // 查询所有数据
      getList() {

      },
      // 点击 服务流程规则配置
      handleAdd(row) {
        const index = row.index || this.ids[0];
        this.$router.push('/service/configure/process/' + index);
      },
      handleUpdate(row) {
        const index = row.index || this.ids;
        this.$confirm('是否确认发布序号为"' + index + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          // return delProvider(servcomnos);
        }).then(() => {
          // this.getList();
          if (index.length == 1) {
            this.serviceList[index - 1].currentStatus = '发布';
          } else {
            for (var i = 0; i < index.length; i++) {
              this.serviceList[index[i] - 1].currentStatus = '发布';
            }
          }
          this.msgSuccess("发布成功");
        }).catch(function () {
        });
      },
      handleDelete(row) {
        const index = row.index || this.ids;
        this.$confirm('是否确认禁用序号为"' + index + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          // return delProvider(servcomnos);
        }).then(() => {
          // this.getList();
          if (index.length == 1) {
            this.serviceList[index - 1].currentStatus = '禁用';
          } else {
            for (var i = 0; i < index.length; i++) {
              this.serviceList[index[i] - 1].currentStatus = '禁用';
            }
          }
          this.msgSuccess("禁用成功");
        }).catch(function () {
        });
      },
      // 服务队列配置
      queueConfigure(row) {
        const index = row.index || this.ids[0];
        this.$router.push('/service/configure/queue/' + index);
      },

    },
  }
</script>

<style scoped>

</style>
