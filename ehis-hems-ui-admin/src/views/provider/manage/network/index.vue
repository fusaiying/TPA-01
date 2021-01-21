<template>
  <div class="app-container">
    <!-- 供应商网络 start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearchNet" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商编码 -->
          <el-form-item label="供应商编码">
            <el-input
              v-model="queryParams.servcomno"
              placeholder="请输入供应商编码"
              clearable
              @keyup.enter.native="handleQuery"></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商名称 -->
          <el-form-item label="供应商名称">
            <el-input
              v-model="queryParams.chname"
              placeholder="请输入供应商名称"
              clearable
              @keyup.enter.native="handleQuery"></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="所在地区">
            <el-cascader clearable
                         :options="areaSelectData"
                         filterable
                         @keyup.enter.native="handleQuery"
                         v-model="queryParams.selectedOptions" placeholder="请选择所在地区"/>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商等级 -->
          <el-form-item label="供应商等级">
            <el-select v-model="queryParams.medicallevel"
                       filterable
                       placeholder="请选择供应商等级"
                       clearable>
              <el-option
                v-for="dict in medicalLevelOptions"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商类型 -->
          <el-form-item label="供应商类型">
            <el-select v-model="queryParams.contracttype"
                       placeholder="请选择供应商类型"
                       filterable
                       clearable>
              <el-option
                v-for="dict in medicaltypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">

        </el-col>
      </el-row>

      <el-row>
        <el-col>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <i class="el-icon-s-grid mr5"></i>
        <span>供应商列表</span>
      </div>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            :disabled="single"
            @click="netManage"
            v-hasPermi="['provider:provider:add']"
          >网点管理
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="netExport"
            v-hasPermi="['provider:provider:edit']"
          >网点导入
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearchNet" @queryTable="getList"></right-toolbar>
      </el-row>

      <!-- table start -->
      <el-table v-loading="loading" :data="providerList" @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55" align="center">
        </el-table-column>

        <el-table-column
          label="供应商编码" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.hmpServOpera['servcomno'] }}</template>
        </el-table-column>

        <el-table-column
          label="供应商中文名称" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.hmpServOpera['chname'] }}</template>
        </el-table-column>

        <el-table-column
          label="供应商英文名称" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.hmpServOpera['enname'] }}</template>
        </el-table-column>

        <el-table-column
          label="供应商类型" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.hmpServOpera['servcomtype'] }}</template>
        </el-table-column>

        <el-table-column
          label="所在地区" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.hmpServOpera['district']}}</template>
        </el-table-column>

        <el-table-column
          label="网点数量" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.netnum}}</template>
        </el-table-column>

        <el-table-column
          label="操作" fixed="right" align="center" width="180px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-setting"
              @click="netManage1(scope.row)"
              v-hasPermi="['provider:provider:edit']"
            >网点管理
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-paperclip"
              @click="netExport1(scope.row)"
              v-hasPermi="['provider:provider:remove']"
            >网点导入
            </el-button>
          </template>
        </el-table-column>

      </el-table>
      <!-- end table -->

      <!-- 分页 -->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"/>
    </el-card>
    <!-- end 供应商网络 -->
  </div>
</template>

<script>
  import {listProviderNetwork} from "@/api/provider/provider";
  import {regionData} from "element-china-area-data";

  export default {
    name: "NetProvider",
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
        // 显示搜索条件 - 网络
        showSearchNet: true,
        // 总条数
        total: 0,
        // 供应商管理1表格数据
        providerList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,

        // 查询参数 - 基础信息维护                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          servcomno: null,
          chname: null,
          enname: null,
          servcomtype: null,
          medicallevel: null,
        },
        // 查询参数 - 供应商网络
        queryParamsNet: {},
        // 产品子项 下拉框
        childProduct: [],
        // 表单参数
        form: {},
        // 机构类型字典 下拉
        medicaltypeOptions: [],
        // 机构等级字典 下拉
        medicalLevelOptions: [],
        // 表单校验
        rules: {
          servcomtype: [
            {required: true, message: "机构类型不能为空", trigger: "blur"}
          ],
          chname: [
            {required: true, message: "服务机构中文名称不能为空", trigger: "blur"}
          ],
          contact: [
            {required: true, message: "联系方式不能为空", trigger: "blur"}
          ],
          detailedaddress: [
            {required: true, message: "详细地址不能为空", trigger: "blur"}
          ],
        },
        // 所在地区
        areaSelectData: regionData,
      };
    },
    created() {
      this.getList()
      // 供应商类型
      this.getDicts("provider_servcomtype").then(response => {
        this.medicaltypeOptions = response.data;
      });
      // 供应商等级
      this.getDicts("provider_medicallevel").then(response => {
        this.medicalLevelOptions = response.data;
      });
    },
    methods: {
      /** 查询供应商网络列表 */
      getList() {
        this.loading = true;
        // 点击查询后，如果查询表单地区选项不为空的话，将会按照地区进行查询
        if (this.queryParams.selectedOptions != undefined) {
          this.queryParams.district = this.queryParams.selectedOptions[2];
          this.queryParams.selectedOptions = '';
        }
        listProviderNetwork(this.queryParams).then(response => {
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
        this.queryParams = {pageNum: 1, pageSize: 10,};
        this.handleQuery();
      },
      /** 点击多选框 */
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.hmpServOpera['servcomno']);
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 网点管理按钮 */
      netManage() {
        const servcomno = this.ids[0];
        this.$router.push({path: '/provider/manage/network/manage/' + servcomno});
      },
      /** 表格中网点管理按钮 */
      netManage1(row) {
        const servcomno = row.hmpServOpera['servcomno'];
        this.$router.push({path: '/provider/manage/network/manage/' + servcomno});
      },
      /** 网点导入按钮 */
      netExport() {
        const servcomno = this.ids[0];
        this.$router.push({path: '/provider/manage/network/import/' + servcomno});
      },
      /** 网点导入 */
      netExport1(row) {
        const servcomno = row.hmpServOpera['servcomno'];
        this.$router.push({path: '/provider/manage/network/import/' + servcomno});
      },
    }
  };
</script>
