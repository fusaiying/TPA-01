<template>
  <div class="app-container">
    <!-- 网络医生 start -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearchDoc" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商编码 -->
          <el-form-item label="供应商编码">
            <el-input
              v-model="queryParams.servcomno"
              placeholder="请输入供应商编码"
              clearable></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商名称 -->
          <el-form-item label="供应商名称">
            <el-input
              v-model="queryParams.chname"
              placeholder="请输入供应商名称"
              clearable></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 供应商类型 -->
          <el-form-item label="供应商类型">
            <el-select v-model="queryParams.servcomtype"
                       clearable
                       filterable
                       placeholder="请选择供应商机构">
              <el-option
                v-for="dict in medicaltypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="所在地区">
            <el-cascader clearable
                         :options="areaSelectData"
                         filterable
                         @keyup.enter.native="handleQuery"
                         v-model="queryParams.selectedOptions" placeholder="请选择所在地区"/>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 网点编码 -->
          <el-form-item label="网点编码">
            <el-input
              v-model="queryParams.servcomno"
              placeholder="请输入网点编码"
              clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 网点名称 -->
          <el-form-item label="网点名称">
            <el-input
              v-model="queryParams.chname"
              placeholder="请输入网点名称"
              clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <!-- 网络医生 -->
          <el-form-item label="网络医生">
            <el-input
              v-model="queryParams.netDoc"
              placeholder="请输入网络医生"
              clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>



    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <i class="el-icon-s-grid mr5"></i>
        <span>供应商基本信息</span>
      </div>

      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            :disabled="single"
            @click="doctorManage"
            v-hasPermi="['provider:provider:add']"
          >医生管理
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            icon="el-icon-edit"
            :disabled="single"
            size="mini"
            @click="doctorExport"
            v-hasPermi="['provider:provider:edit']"
          >医生导入
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearchDoc" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="providerList" @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55" align="center">
        </el-table-column>

        <el-table-column
          label="供应商编码" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.servcomno }}</template>
        </el-table-column>
        <el-table-column
          label="供应商中文名称" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.chname }}</template>
        </el-table-column>

        <el-table-column
          label="供应商类型" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.servcomtype }}</template>
        </el-table-column>

        <el-table-column
          label="网点编码" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.netCode }}</template>
        </el-table-column>

        <el-table-column
          label="网点名称" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.netName }}</template>
        </el-table-column>

        <el-table-column
          label="所在地区" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.district}}</template>
        </el-table-column>

        <el-table-column
          label="医生数量" show-overflow-tooltip>
          <template slot-scope="scope">{{ scope.row.docCount }}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" fixed="right" width="180px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-setting"
              @click="doctorManage(scope.row)"
              v-hasPermi="['provider:provider:edit']"
            >医生管理
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-paperclip"
              @click="doctorExport(scope.row)"
              v-hasPermi="['provider:provider:remove']"
            >医生导入
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


    <!-- end 医生网络 -->
  </div>
</template>

<script>
  import {listProvider, getProvider, delProvider, addProvider, updateProvider} from "@/api/provider/provider";
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
        // 显示搜索条件 - 医生
        showSearchDoc: true,

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
            {required: true, message: "供应商类型不能为空", trigger: "blur"}
          ],
          chname: [
            {required: true, message: "供应商中文名称不能为空", trigger: "blur"}
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
      this.getDicts("provider_servcomtype").then(response => {
        this.medicaltypeOptions = response.data;
      });
      this.getDicts("sys_medicalLevel").then(response => {
        this.medicalLevelOptions = response.data;
      });
    },
    methods: {
      /** 查询供应商管理1列表 */
      getList() {
        this.loading = true;
        // 保证前台不保存。。。
        this.queryParams.selectedOptions = undefined;
        listProvider(this.queryParams).then(response => {
          this.providerList = response.rows;
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
          servcomno: null,
          chname: null,
          enname: null,
          servcomtype: null,
          servcomattribute: null,
          medicaltype: null,
          medicallevel: null,
          department: null,
          constate: null,
          payway: null,
          paytype: null,
          qualifiedmdc: null,
          contact: null,
          postcode: null,
          expensive: null,
          instructions: null,
          trafficroute: null,
          continent: null,
          country: null,
          province: null,
          city: null,
          district: null,
          website: null,
          detailedaddress: null,
          remark: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifieddate: null,
          modifiedtime: null
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
        // this.resetForm("queryForm");
        this.queryParams = {pageNum: 1, pageSize: 10,},
          this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.servcomno);
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.$router.push({path: '/provider/add'});
        // this.reset();
        // this.open = true;
        // this.title = "添加-服务机构基本信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        // this.reset();
        // const servcomno = row.servcomno || this.ids;
        // getProvider(servcomno).then(response => {
        //   this.form = response.data;
        //   this.open = true;
        //   this.title = "修改-服务机构基本信息";
        // });
        const servcomno = row.servcomno || this.ids[0];
        this.$router.push('/provider/edit/' + servcomno);
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.servcomno != null) {
              updateProvider(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addProvider(this.form).then(response => {
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
        const servcomnos = row.servcomno || this.ids;
        this.$confirm('是否确认删除供应商编号为"' + servcomnos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delProvider(servcomnos);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('provider/provider/export', {
          ...this.queryParams
        }, `provider_provider.xlsx`)
      },
      // 医生管理按钮
      doctorManage(row) {
        const servcomno = row.servcomno || this.ids[0];
        this.$router.push({path: '/provider/manage/doctor/manage/' + servcomno});
      },
      // 医生导入
      doctorExport(row) {
        const servcomno = row.servcomno || this.ids[0];
        console.log(servcomno);
        this.$router.push({path: '/provider/manage/network/import/' + servcomno});
      },
    }
  };
</script>
