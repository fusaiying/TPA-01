<template>
  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
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
          <el-form-item label="所属集团" prop="groupInfo">
          <el-input
            v-model="queryParams.groupInfo"
            placeholder="请输入所属集团"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 机构类型 -->
          <el-form-item label="类型">
            <el-select v-model="queryParams.alternatefield2"
                       clearable
                       placeholder="请选择类型">
              <el-option
                v-for="dict in srviceTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
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
          <i class="el-icon-s-tools mr5"></i><span>平台维护清单</span>
        </div>
        <el-row :gutter="10" class="mt10 mb10">
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
            >维护
            </el-button>
          </el-col>
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="danger"-->
          <!--          icon="el-icon-delete"-->
          <!--          size="mini"-->
          <!--          :disabled="multiple"-->
          <!--          @click="handleDelete"-->
          <!--          v-hasPermi="['marketing:marketplan:remove']"-->
          <!--        >删除-->
          <!--        </el-button>-->
          <!--      </el-col>-->
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="warning"-->
          <!--          icon="el-icon-download"-->
          <!--          size="mini"-->
          <!--          @click="handleExport"-->
          <!--          v-hasPermi="['marketing:marketplan:export']"-->
          <!--        >导出-->
          <!--        </el-button>-->
          <!--      </el-col>-->
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    <el-table v-loading="loading" :data="marketplanList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编码" prop="programno"/>
      <el-table-column label="名称" prop="programname"/>
      <el-table-column label="渠道属性" prop="channelno"/>
      <el-table-column label="所属集团" prop="groupinfo"/>
      <el-table-column label="详细地址" prop="detailAddress"/>
      <el-table-column label="类型" prop="commissiontype"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180" fixed ="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['marketing:marketplan:edit']"
          >维护
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
    listMarketplan,
    getMarketplan,
    delMarketplan,
    addMarketplan,
    updateMarketplan,
    openView
  } from "@/api/marketing/marketplan";
    export default {
        name: "index",
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
            // 销售平台下拉
            alternatefield2:[],
            //服务机构类型数据字典
            srviceTypeOptions:[],
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
            }
          };
        },
      created() {
        this.getList();
        // 服务机构类型数据字典
        this.getDicts("service_com_type").then(response => {
          this.srviceTypeOptions = response.data;
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
          this.$router.push({path: '/marketing/maintain/add'});
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
          const programno = row.programno || this.ids[0];
          this.$router.push('/marketing/maintain/edit/' + programno);
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
    }
</script>

<style scoped>

</style>
