<template>
  <div class="app-container">
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i><span>营销方案基本信息</span>
        </div>
        <el-form :model="queryParams" :rules="rules" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
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
              <el-form-item label="营销方案名称" :required="true" prop="programname">
                <el-input
                  v-model="queryParams.programname"
                  placeholder="请输入营销方案名称"
                  clearable
                  @keyup.enter.native="handleQuery"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="优惠方式" :required="true" prop="preferentialway">
                <el-select
                  v-model="queryParams.preferentialway"
                  placeholder="请输入优惠方式"
                  clearable>
                  <el-option
                    v-for="dict in tialwayOptions"
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
              <el-form-item label="合作伙伴" prop="channelno">
                <template>
                  <el-select
                    v-model="value"
                    clearable
                    placeholder="请选择合作伙伴">
                    <el-option
                      v-for="dict in options"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value">
                    </el-option>
                  </el-select>
                </template>

              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="销售平台" prop="saleplatform">
                <el-input
                  v-model="queryParams.saleplatform"
                  placeholder="请输入销售平台"
                  clearable
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="佣金方式" :required="true" prop="commissiontype">
                <el-select
                  v-model="queryParams.commissiontype"
                  placeholder="请选择佣金方式"
                  clearable>
                  <el-option
                    v-for="dict in commissionOptions"
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
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="佣金" :required="true" prop="commission">
                <el-input
                  v-model="queryParams.commission"
                  placeholder="请输入佣金"
                  clearable
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="手续费" :required="true" prop="servicefee">
                <el-input
                  v-model="queryParams.servicefee"
                  placeholder="请输入手续费"
                  clearable
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="手续费方式" :required="true" prop="feetype">
                <el-select
                  v-model="queryParams.feetype"
                  placeholder="请选择手续费方式"
                  clearable>
                  <el-option
                    v-for="dict in feeTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第四行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="销售起期" :required="true" prop="salstartdatetime">
                <el-date-picker clearable
                                v-model="queryParams.salstartdatetime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择销售起期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="销售止期" prop="salenddatetime">
                <el-date-picker clearable
                                v-model="queryParams.salenddatetime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择销售止期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="有效状态" prop="productstate">
                <el-input
                  v-model="queryParams.productstate"
                  placeholder="请输入有效状态"
                  clearable
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['marketing:marketplan:add']"
            >新增
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <!-- 优惠列表信息 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-goods mr5"></i><span>优惠列表</span>
        </div>
        <el-row class="mb8">
          <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
            <el-upload
              ref="upload"
              :limit="1"
              accept=".xlsx, .xls"
              :headers="upload.headers"
              :action="upload.url + '?updateSupport=' + upload.updateSupport"
              :disabled="upload.isUploading"
              :on-progress="handleFileUploadProgress"
              :on-success="handleFileSuccess"
              :auto-upload="false"
              drag>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或
                <em>点击上传</em>
              </div>
              <div class="el-upload__tip" slot="tip">
                <!--          <el-checkbox v-model="upload.updateSupport"/>-->
                <!--          是否更新已经存在的用户数据-->
                <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
              </div>
              <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
            </el-upload>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="submitFileForm">确 定</el-button>
              <el-button @click="upload.open = false">取 消</el-button>
            </div>
          </el-dialog>
          <el-button class="filter-item" size="mini" type="cyan" icon="el-icon-upload" @click="handleImport">上传文件
          </el-button>
          <el-button class="filter-item" size="mini" type="primary" icon="el-icon-s-claim"
                     @click="savereduceinfo">保存
          </el-button>
          <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handledeleteService">删除
          </el-button>
        </el-row>
        <!-- 优惠列表信息table -->
        <el-row>
          <el-form v-show="showSearch" label-width="68px">
            <el-table :data="reducetableData" border highlight-current-row size="small"
                      class="el-tb-edit"
                      @current-change="handleSelectionChange">
              <el-table-column type="selection" width="55" align="center"/>
              <el-table-column prop="copies" align="center" header-align="center" label="份数" min-width="2"
                               show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-input size="mini" v-model="scope.row.copies" placeholder="请输入内容"
                            @change="handleEdit(scope.$index, scope.row)"></el-input>
                  <span>{{scope.row.copies}}</span>
                </template>
              </el-table-column>
              <el-table-column prop="reductionprice" align="center" header-align="center" label="满减价格" min-width="2"
                               show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-input size="mini" v-model="scope.row.reductionprice" placeholder="请输入内容"
                            @change="handleEdit(scope.$index, scope.row)"></el-input>
                  <span>{{scope.row.reductionprice}}</span>
                </template>
              </el-table-column>
              <el-table-column prop="preferential" align="center" header-align="center" label="优惠值" min-width="2"
                               show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-input size="mini" v-model="scope.row.preferential" placeholder="请输入内容"
                            @change="handleEdit(scope.$index, scope.row)"></el-input>
                  <span>{{scope.row.preferential}}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"
                               fixed="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-s-claim"
                    v-show="scope.row.show"
                    @click="scope.row.show=false"
                    v-hasPermi="['marketing:marketplan:add']"
                  >保存
                  </el-button>
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    v-show="!scope.row.show"
                    @click="scope.row.show=true"
                    v-hasPermi="['marketing:marketplan:edit']"
                  >编辑
                  </el-button>
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="handledeleteService(scope.row)"
                    v-hasPermi="['marketing:marketplan:remove']"
                  >删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form>
          <el-row :gutter="10" class="mb8">
            <el-col :span="24">
              <el-button
                style="width: 100%; margin-top: 3px;"
                icon="el-icon-plus"
                size="mini"
                @click="addRowHandle"
                v-hasPermi="['system:definition:add']"
              >新增
              </el-button>
            </el-col>
          </el-row>
        </el-row>
      </el-card>
    </el-row>

    <!-- 产品包信息 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-tickets mr5"></i><span>产品包信息</span>
        </div>
        <el-row class="mb8">
          <el-button class="filter-item" size="mini" type="primary" icon="el-icon-s-claim" @click="savereduceinfo">保存
          </el-button>
          <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handledeleteService">
            删除
          </el-button>

        </el-row>
        <el-row>
          <el-form v-show="showSearch" label-width="68px">
            <el-table :data="puducttableData" border highlight-current-row size="small"
                      class="el-tb-edit">
              <el-table-column type="index" width="50" align="center">
              </el-table-column>
              <el-table-column label="产品包名称" align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.productpagename"
                            v-show="scope.row.show"
                            clearable
                            size="mini"
                  ></el-input>
                  <span v-show="!scope.row.show">{{scope.row.productpagename}}</span>
                </template>
              </el-table-column>
              <el-table-column label="产品包价格" align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.productpagerice"
                            v-show="scope.row.show"
                            clearable
                            size="mini"
                  ></el-input>
                  <span v-show="!scope.row.show">{{scope.row.productpagerice}}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"
                               fixed="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-s-claim"
                    v-show="scope.row.show"
                    @click="scope.row.show=false"
                    v-hasPermi="['system:information:remove']"
                  >保存
                  </el-button>
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    v-show="!scope.row.show"
                    @click="scope.row.show=true"
                    v-hasPermi="['system:information:remove']"
                  >编辑
                  </el-button>
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="deleteServiceOrgListOneRow(scope.row)"
                    v-hasPermi="['system:information:remove']"
                  >删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-row :gutter="10" class="mb8">
              <el-col :span="24">
                <el-button
                  style="width: 100%; margin-top: 3px;"
                  icon="el-icon-plus"
                  size="mini"
                  @click="addServiceOrgListOneRow"
                  v-hasPermi="['system:definition:add']"
                >新增
                </el-button>
              </el-col>
            </el-row>
          </el-form>
        </el-row>
      </el-card>
    </el-row>

  </div>

</template>

<script>
  import {
    listMarketplan,
    addMarketplan,
    updateMarketplan
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
        //多选框初始化
        checked: null,
        // 表单参数
        form: {
          commissionOptions: "01-按百分比"
        },
        // 表单校验
        rules: {
          programname: [
            {required: true, message: "营销方案名称不能为空", trigger: "blur"}
          ],
          preferentialway: [
            {required: true, message: "优惠方式不能为空", trigger: "blur"}
          ],
          // channelno: [
          //   {required: true, message: "合作伙伴不能为空", trigger: "blur"}
          // ],
          servicefee: [
            {required: true, message: "手续费不能为空", trigger: "blur"},
            {
              pattern: '^0+(.[0-9]{0,2})?$',
              message: "手续费只能录入小数，请确认！",
              trigger: "blur"
            }

          ],
          salstartdatetime: [
            {required: true, message: "销售起期不能为空", trigger: "blur"}
          ],
          productstate: [
            {required: true, message: "有效状态不能为空", trigger: "blur"}
          ],
          commission: [
            {required: true, message: "佣金不能为空", trigger: "blur"},
            {
              pattern: '^0+(.[0-9]{0,2})?$',
              message: "佣金只能录入小数，请确认！",
              trigger: "blur"
            }
          ],
          commissiontype: [
            {required: true, message: "佣金方式不能为空", trigger: "blur"}
          ],
          feetype: [
            {required: true, message: "手续费方式不能为空", trigger: "blur"}
          ],
        },
        // 文件上传用户导入参数
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
          // 设置上传的请求头部
          // headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/system/user/importData"
        },
        options: [{
          value: '1',
          label: '渠道1'
        }, {
          value: '2',
          label: '渠道2'
        }, {
          value: '3',
          label: '渠道3'
        }, {
          value: '4',
          label: '渠道4'
        }],
        value: '第三方',
        //优惠列表信息
        reducetableData: [],
        //产品包信息
        puducttableData: [],
        //手续费方式字典
        feeTypeOptions: [],
        //佣金方式字典
        commissionOptions: [],
        //优惠方式字典
        tialwayOptions: [],
        // 财务信息
        concatInfo: [],


      };
    },
    created() {
      this.getList();
      this.getDicts("market_fee_type").then(response => {
        this.feeTypeOptions = response.data;
      });
      this.getDicts("market_commission_type").then(response => {
        this.commissionOptions = response.data;
      });
      this.getDicts("market_tialway_type").then(response => {
        this.tialwayOptions = response.data;
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
      /** 新增按钮操作 */
      handleAdd() {

        this.$refs["queryForm"].validate(valid => {
          if (valid) {
            addMarketplan(this.queryParams).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
              }
            });
          }
        });
        this.reset();
      },

      // 添加一列数据
      addRowHandle() {
        const field = {
          copies: '',
          reductionprice: '',
          preferential: ''
        };
        this.reducetableData.push(field);
      },
      // 添加一列数据
      addpuductRowHandle() {
        const field = {
          productpagename: null,
          productpagerice: null,
          show: true
        };
        this.puducttableData.push(field);
      },
      //选择框录入
      handleSelectionChange(row) {
        this.ids = selection.map(item => item.programno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "机构附件导入";
        this.upload.open = true;
      },
      // 提交上传文件
      submitFileForm() {
        // this.$refs.upload.submit();
        this.$message({
          message: '机构附件上传成功',
          type: 'success'
        });
        this.upload.open = false;
      },
      /** 下载模板操作 */
      importTemplate() {
        this.download('system/user/importTemplate', {
          ...this.queryParams
        }, `comAttachment_${new Date().getTime()}.xlsx`)
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        // this.getList();
      },

      /** 删除一行 */
      handledeleteService(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.reducetableData.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 删除一行 */
      handledelete(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.puducttableData.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

      /** 优惠信息保存 */
      savereduceinfo() {
        this.$message({
          message: '数据保存成功',
          type: 'success'
        });
      },

      //ServiceOrgList添加一行
      addServiceOrgListOneRow() {
        const row = {
          productpagename: null,
          productpagerice: null,
          show: true
        };
        this.puducttableData.push(row);
      },


      /** ServiceOrgList删除一行 */
      deleteServiceOrgListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.puducttableData.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },


    },

  };

</script>
