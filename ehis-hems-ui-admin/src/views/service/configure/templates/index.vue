<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <!-- 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <!-- 服务类型 -->
          <el-form-item label="模板名称">
            <el-input
              v-model="queryParams.templateName"
              placeholder="请输入模板名称"
              clearable
              @keyup.enter.native="handleQuery"></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="服务节点">
            <el-select v-model="queryParams.serviceNode"
                       clearable
                       placeholder="请选择服务节点">
              <el-option
                v-for="dict in serviceNode"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="发送对象">
            <el-select v-model="queryParams.sendObj"
                       clearable
                       placeholder="请选择发送对象">
              <el-option
                v-for="dict in sendObj"
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
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="服务类别">
            <el-select v-model="queryParams.serviceType"
                       clearable
                       placeholder="请选择服务类别">
              <el-option
                v-for="dict in serviceType"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="是否有效">
            <el-select v-model="queryParams.effective"
                       clearable
                       placeholder="请选择是否有效">
              <el-option
                v-for="dict in effective"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
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


    <!-- 卡片、表格 -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-message mr5"></i><span>短信及邮件配置列表</span>
        </div>



        <el-row :gutter="10" class="mb10">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['provider:provider:add']"
            >新增
            </el-button>
          </el-col>
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="success"-->
          <!--          icon="el-icon-edit"-->
          <!--          size="mini"-->
          <!--          :disabled="single"-->
          <!--          @click="handleUpdate"-->
          <!--          v-hasPermi="['provider:provider:edit']"-->
          <!--        >修改-->
          <!--        </el-button>-->
          <!--      </el-col>-->
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <!-- 服务流程列表 -->
        <el-table v-loading="loading" :data="serviceList">
          <el-table-column
            label="序号">
            <template slot-scope="scope">{{ scope.row.index }}</template>
          </el-table-column>

          <el-table-column
            label="模板名称">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['provider:provider:edit']"
              >{{ scope.row.templateName }}
              </el-button>
            </template>
          </el-table-column>

          <el-table-column
            label="服务节点">
            <template slot-scope="scope">{{ scope.row.serviceNode }}</template>
          </el-table-column>

          <el-table-column
            label="发送对象">
            <template slot-scope="scope">{{ scope.row.sendObj }}</template>
          </el-table-column>

          <el-table-column
            label="serviceType">
            <template slot-scope="scope">{{ scope.row.serviceType }}</template>
          </el-table-column>

          <el-table-column
            label="是否有效">
            <template slot-scope="scope">{{ scope.row.effective}}</template>
          </el-table-column>

          <el-table-column
            label="发送内容">
            <template slot-scope="scope">{{ scope.row.sendContent}}</template>
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


    <!-- 添加或修改供应商管理对话框-->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="模板名称" prop="templateName">
              <el-input v-model="form.templateName" placeholder="请输入模板名称" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="自动填充内容">
              <el-select v-model="form.filledContent"
                         clearable
                         placeholder="">
                <el-option
                  v-for="dict in filledContent"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="服务节点" prop="serviceNode">
              <el-select v-model="form.serviceNode"
                         clearable
                         placeholder="请选择服务节点">
                <el-option
                  v-for="dict in serviceNode"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发送对象" prop="sendObj">
              <el-select v-model="form.sendObj"
                         clearable
                         placeholder="请选择发送对象">
                <el-option
                  v-for="dict in sendObj"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

          <el-col :span="12">
            <el-form-item label="服务类型" prop="serviceType">
              <el-select v-model="form.serviceType"
                         clearable
                         placeholder="请选择产品名称">
                <el-option
                  v-for="dict in serviceType"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品名称">
              <el-input v-model="form.productName" placeholder="请输入产品名称" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="预约来源">
              <el-input v-model="form.source" placeholder="请输入预约来源" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否有效" prop="effective">
              <el-select v-model="form.effective"
                         clearable
                         placeholder="请选择是否有效">
                <el-option
                  v-for="dict in effective"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="发送内容" prop="sendContent">
              <el-input type="textarea" v-model="form.sendContent"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <!-- 添加或修改按钮提示框中确定和取消 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="submitForm">确 定</el-button>
        <el-button @click="cancel" size="mini">取 消</el-button>
      </div>
    </el-dialog>


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
        total: 1,

        // 表格数据
        serviceList: [
          {
            index: '1',
            templateName: '齿科服务模板',
            serviceNode: '提交',
            sendObj: 'PICC陪诊人员',
            serviceType: '齿科服务',
            effective: '无效',
            sendContent: 'PICC陪诊管家电话',
          },
        ],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 弹框form
        form: {},
        // 弹框rules
        rules: {
          templateName: [
              {required: true, message: "模板名称不能为空", trigger: "blur"}
          ],
          serviceNode: [
              {required: true, message: "服务节点不能为空", trigger: "change"}
          ],
          sendObj: [
              {required: true, message: "发送对象不能为空", trigger: "change"}
          ],
          serviceType: [
              {required: true, message: "服务类型不能为空", trigger: "change"}
          ],
          effective: [
              {required: true, message: "是否有效不能为空", trigger: "change"}
          ],
          sendContent: [
              {required: true, message: "发送内容不能为空", trigger: "blur"}
          ],

        },
        // 下拉框
        filledContent: [],
        serviceNode: [],
        sendObj: [],
        serviceType: [],
        effective: [],

      }
    },
    methods: {
      // 查询
      handleQuery() {
      },
      // 重置
      resetQuery() {
      },
      getList() {
      },
      handleAdd() {
        this.open = true;
        this.title = "新增短信/邮件模板维护";
      },
      handleUpdate() {
        this.open = true;
        this.title = "查看短信/邮件模板";
        this.form = this.serviceList[0];
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.index);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      // 弹框函数
      submitForm() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form = {};
            this.resetForm('form');
            this.open = false;
            this.$message({
              showClose: true,
              message: '保存成功',
              type: 'success'
            });
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          };
        });
      },
      cancel() {
        this.open = false;
        this.form = {};
        this.resetForm("form");
      },
    },
  }
</script>

<style scoped>

</style>
