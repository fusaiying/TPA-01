<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品包编码" prop="productpackageno">
              <el-input
                v-model="queryParams.productpackageno"
                placeholder="请输入产品包编码"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品包名称" prop="productpackagename">
              <el-input
                v-model="queryParams.productpackagename"
                placeholder="请输入产品包名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否推送电子凭证" prop="sendcontract" label-width="140px">
              <el-select
                v-model="queryParams.sendcontract"
                placeholder="请选择是否推送凭证"
                clearable
              >
                <el-option
                  v-for="dict in yesFlagOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="标准价格" prop="normalprice">
              <el-input
                v-model="queryParams.normalprice"
                placeholder="请输入标准价格"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="卡单属性" prop="cardtype">
              <el-select
                v-model="queryParams.cardtype"
                placeholder="请选择卡单属性"
                clearable
              >
                <el-option
                  v-for="dict in cardTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品包状态" prop="productstate2">
              <el-select
                v-model="queryParams.productstate2"
                placeholder="请选择产品包状态"
                clearable
              >
                <el-option
                  v-for="dict in productstate2Options"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
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
          <i class="el-icon-edit-outline mr5"></i><span>产品包列表</span>
        </div>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['product:productpacketinfo:add']"
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
              v-hasPermi="['product:productpacketinfo:edit']"
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
              v-hasPermi="['product:productpacketinfo:remove']"
            >删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['product:productpacketinfo:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    <el-table v-loading="loading" :data="productpacketinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center"/>
      <el-table-column label="产品包编码" align="center" prop="productpackageno" width="120"/>
      <el-table-column label="产品包名称" align="center" prop="productpackagename" width="120"/>
      <el-table-column label="标准价格" align="center" prop="normalprice"/>
      <el-table-column label="税价" align="center" prop="taxprice"/>
      <el-table-column label="是否推送电子凭证" align="center" prop="sendcontract"/>
      <el-table-column label="销售起期" align="center" prop="salstartdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salstartdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="销售止期" align="center" prop="salenddate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salenddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="卡单属性" align="center" prop="cardtype"/>
      <el-table-column label="产品包状态" align="center" prop="productstate2"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:productpacketinfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:productpacketinfo:remove']"
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
    <!-- 添加或修改产品包信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="产品包名称" prop="productpackagename">
          <el-input
            v-model="form.productpackagename"
            placeholder="请输入产品包名称"
            size="mini"
            style="width: 260px"
          />
        </el-form-item>
        <el-form-item label="对外产品包名字" prop="exterpackagename">
          <el-input
            v-model="form.exterpackagename"
            placeholder="请输入对外产品包名字"
            size="mini"
            style="width: 260px"
          />
        </el-form-item>
        <el-form-item label="标准价格" prop="normalprice">
          <el-input
            v-model="form.normalprice"
            placeholder="请输入标准价格"
            size="mini"
            style="width: 260px"
          />
        </el-form-item>
        <el-form-item label="税价" prop="taxprice">
          <el-input
            v-model="form.taxprice"
            placeholder="请输入税价"
            size="mini"
            style="width: 260px"
          />
        </el-form-item>
        <el-form-item label="是否推送电子凭证" prop="sendcontract">
          <el-select
            v-model="form.sendcontract"
            placeholder="请输入是否推送电子凭证"
            size="mini"
            style="width: 260px"
          >
            <el-option
              v-for="dict in yesFlagOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="卡单属性" prop="cardtype">
          <el-select
            v-model="form.cardtype"
            placeholder="请选择卡单属性"
            size="mini"
            style="width: 260px"
          >
            <el-option
              v-for="dict in cardTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="使用人标记" prop="userflag">
          <el-input
            v-model="form.userflag"
            placeholder="请输入使用人标记"
            size="mini"
            style="width: 260px"
          />
        </el-form-item>
        <el-form-item label="最多使用人数" prop="maxusernum">
          <el-input
            v-model="form.maxusernum"
            placeholder="请输入最多使用人数"
            size="mini"
            style="width: 260px"
          />
        </el-form-item>
        <el-form-item label="是否可更换使用人" prop="isreplaceuser">
          <el-select
            v-model="form.isreplaceuser"
            placeholder="请输入是否可更换使用人"
            size="mini"
            style="width: 260px"
          >
          <el-option
            v-for="dict in yesFlagOptions"
            :key="dict.dictValue"
            :label="dict.dictValue+' - '+dict.dictLabel"
            :value="dict.dictValue"
          />
          </el-select>
        </el-form-item>
        <el-form-item label="销售起期" prop="salstartdate">
          <el-date-picker clearable size="mini" style="width: 260px"
                          v-model="form.salstartdate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择销售起期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="销售止期" prop="salenddate">
          <el-date-picker clearable size="mini" style="width: 260px"
                          v-model="form.salenddate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择销售止期">
          </el-date-picker>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="submitForm">确 定</el-button>
        <el-button size="mini" @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    addProductpacketinfo,
    delProductpacketinfo,
    getProductpacketinfo,
    listProductpacketinfo,
    updateProductpacketinfo
  } from "@/api/product/productpacketinfo";

  export default {
    name: "Productpacketinfo",
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
        // 产品包信息表格数据
        productpacketinfoList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 卡单属性数据字典
        cardTypeOptions: [],
        //系统是否数据字典
        sysYesNoOptions: [],
        // 产品是否字典
        yesFlagOptions: [],
        // 产品状态数据字典
        productstate2Options:[],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          productpackagename: null,
          normalprice: null,
          taxprice: null,
          sendcontract: null,
          salstartdate: null,
          salenddate: null,
          cardtype: null,
          prooftype: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null,
          userflag: null,
          productstate2: null,
          issendsms: null,
          maxusernum: null,
          isreplaceuser: null,
          exterpackagename: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          productpackagename: [
            {required: true, message: "产品包名称不能为空", trigger: "blur"}
          ],
          exterpackagename: [
            {required: true, message: "对外产品包名字不能为空", trigger: "blur"}
          ],
          normalprice: [
            {required: true, message: "标准价格不能为空", trigger: "blur"}
          ],
          salstartdate: [
            {required: true, message: "产品包销售起期不能为空", trigger: "blur"}
          ],
          userflag: [
            {required: true, message: "使用人标记不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      //卡单属性下拉列表
      this.getDicts("sys_card_type").then(response => {
        this.cardTypeOptions = response.data;
      });
      //产品是否数据字典
      this.getDicts("product_yesflag").then(response => {
        this.yesFlagOptions = response.data;
      });
      //产品状态数据字典
      this.getDicts("product_state").then(response => {
        this.productstate2Options = response.data;
      });
    },
    methods: {
      /** 查询产品包信息列表 */
      getList() {
        this.loading = true;
        listProductpacketinfo(this.queryParams).then(response => {
          this.productpacketinfoList = response.rows;
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
          productpackageno: null,
          productpackagename: null,
          normalprice: null,
          taxprice: null,
          sendcontract: null,
          salstartdate: null,
          salenddate: null,
          cardtype: null,
          prooftype: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null,
          userflag: null,
          productstate2: null,
          issendsms: null,
          maxusernum: null,
          isreplaceuser: null,
          exterpackagename: null
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
        this.ids = selection.map(item => item.productpackageno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        // this.reset();
        // this.open = true;
        // this.title = "添加产品包信息";
        this.$router.push({path: '/product/productpacketinfo/info/0'});
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const productpackageno = row.productpackageno || this.ids
        getProductpacketinfo(productpackageno).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品包信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.productpackageno != null) {
              updateProductpacketinfo(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addProductpacketinfo(this.form).then(response => {
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
        const productpackagenos = row.productpackageno || this.ids;
        this.$confirm('是否确认删除产品包信息编号为"' + productpackagenos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delProductpacketinfo(productpackagenos);
        }).then(() => {
          if(this.productpacketinfoList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('product/productpacketinfo/export', {
          ...this.queryParams
        }, `product_productpacketinfo.xlsx`)
      }
    }
  };
</script>
