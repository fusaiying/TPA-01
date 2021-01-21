<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="问卷编码" prop="productpackagename">
            <el-input
              v-model="queryParams.productpackagename"
              placeholder="请输入问卷编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="问卷名称" prop="normalprice">
            <el-input
              v-model="queryParams.normalprice"
              placeholder="请输入问卷名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="应用的产品" prop="sendcontract">
            <el-select v-model="queryParams.sendcontract" placeholder="请选择应用产品" clearable>
              <el-option label="请选择数据词典" value=""/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="应用的服务" prop="sendcontract">
            <el-select v-model="queryParams.sendcontract" placeholder="请选择应用服务" clearable>
              <el-option label="请选择数据词典" value=""/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="所在地区" prop="sendcontract">
            <el-cascader :options="areaSelectData"
                         @change="handleChange" class="full-width"
                         v-model="selectedOptions" placeholder="请选择您所在的城市"/>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="服务商编码" prop="cardtype">
            <el-input placeholder="服务商编码" v-model="queryParams.productpackagename">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="供应商编码" prop="cardtype">
            <el-input placeholder="供应商编码" v-model="queryParams.productpackagename">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="问卷状态" prop="sendcontract">
            <el-select v-model="queryParams.sendcontract" placeholder="请选择应用产品" clearable>
              <el-option label="请选择数据词典" value=""/>
            </el-select>
          </el-form-item>
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


    <el-row class="mt10 mb10">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i>
          <span>问卷列表</span>
        </div>
        <el-row class="mb10">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['provider:contract:add']"
            >新增
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="examineList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="问卷编码" align="center" prop="productpackageno"/>
          <el-table-column label="问卷名称" align="center" prop="productpackagename"/>
          <el-table-column label="问卷描述" align="center" prop="normalprice"/>
          <el-table-column label="状态" align="center" prop="sendcontract"/>
          <el-table-column label="发布日期" align="center" prop="cardtype"/>
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
        <el-row style="text-align: right;">
          <el-col class="mt10 mb10">
            <el-button
              type="info"
              icon="el-icon-zoom-in"
              size="mini"
              @click="releaseFun"
              :disabled="single"
              v-hasPermi="['provider:provider:add']"
            >发布
            </el-button>
            <el-button
              type="danger"
              icon="el-icon-edit-outline"
              size="mini"
              @click="disabledFun"
              :disabled="single"
              v-hasPermi="['provider:provider:add']"
            >禁用
            </el-button>
            <el-button
              type="info"
              icon="el-icon-edit-outline"
              size="mini"
              @click="startFun"
              :disabled="single"
              v-hasPermi="['provider:provider:add']"
            >启用
            </el-button>
            <el-button
              type="danger"
              icon="el-icon-edit-outline"
              size="mini"
              @click="copyFun"
              :disabled="single"
              v-hasPermi="['provider:provider:add']"
            >复制
            </el-button>
            <el-button
              type="info"
              icon="el-icon-edit-outline"
              size="mini"
              @click="updateFun"
              :disabled="single"
              v-hasPermi="['provider:provider:add']"
            >修改
            </el-button>
            <el-button
              type="info"
              icon="el-icon-edit-outline"
              size="mini"
              @click="delFun"
              :disabled="single"
              v-hasPermi="['provider:provider:add']"
            >删除
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>

    <!-- 添加或修改产品包审核对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="dialogForm" :rules="rules" :inline="true" label-width="125px">
        <el-row>
          <el-col :span="11">
            <el-form-item label="问卷编码" prop="productpackagecode">
              <el-input
                v-model="dialogForm.productpackagecode"
                placeholder="请输入问卷编码"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="问卷名称" prop="productpackageName">
              <el-input
                v-model="dialogForm.productpackageName"
                placeholder="请输入问卷名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="问卷描述" prop="normalprice1">
              <el-input type="textarea" v-model="form.detailedaddress"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="应用的产品" prop="sendcontract">
              <el-select v-model="queryParams.sendcontract" placeholder="请选择应用产品" clearable>
                <el-option label="请选择数据词典" value=""/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="应用的服务" prop="sendcontract">
              <el-select v-model="queryParams.sendcontract" placeholder="请选择应用服务" clearable>
                <el-option label="请选择数据词典" value=""/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="服务商编码" prop="cardtype">
              <el-input placeholder="服务商编码" v-model="queryParams.productpackagename">
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="所在地区" prop="sendcontract">
              <el-cascader :options="areaSelectData"
                           @change="handleChange" class="full-width"
                           v-model="selectedOptions" placeholder="请选择您所在的城市"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="供应商编码" prop="cardtype">
              <el-input placeholder="供应商编码" v-model="queryParams.productpackagename">
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" size="mini">编辑问题</el-button>
        <el-button @click="saveAsDraft" size="mini">保存为草稿</el-button>
        <el-button
          type="danger"
          icon="el-icon-edit-outline"
          size="mini"
          @click="saveAndPut"
          v-hasPermi="['provider:provider:add']"
        >保存并发布
        </el-button>
        <el-button
          type="info"
          icon="el-icon-edit-outline"
          size="mini"
          @click="cancel"
          v-hasPermi="['provider:provider:add']"
        >取消
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
  .linkage {
    display: flex;

    div {
      margin-right: 5px;
    }
  }
</style>
<script>
  import {listExamine, getExamine, delExamine, addExamine, updateExamine} from "@/api/examine/examine";
  import city from './area.json'
  import {regionData, CodeToText, TextToCode} from 'element-china-area-data';

  export default {
    name: "Examine",
    data() {
      return {
        dialogForm: {},
        areaSelectData: regionData, // options绑定的数据就是引入的 provinceAndCityData
        selectedOptions: [], // 地区选择参数，['省区域码', '市区域码']
        // 整个省市县数据
        city: city,
        // 被选中的市数据
        cityList: [],
        // 被选中的县数据
        areaList: [],
        selectProvince: {},
        selectCity: {},
        selectArea: {},
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
        // 产品包审核表格数据
        examineList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 5,
          productpackagename: null,
          normalprice: null,
          sendcontract: null,
          cardtype: null,
          productstate2: null,
        },
        tableData: [
          {
            netCode: 'D000002121',
            netName: '金鱼姬NO34',
            netLinkMan: '金鱼姬NO34',
            netLinkPhone: '15566936655',
            netLinkMain: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'D000002122',
            netName: '金鱼姬NO23',
            netLinkMan: '金鱼姬NO34',
            netLinkPhone: '15566936655',
            netLinkMain: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'D000002123',
            netName: '金鱼姬NO54',
            netLinkMan: '金鱼姬NO54',
            netLinkPhone: '15566936655',
            netLinkMain: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'D000002124',
            netName: '金鱼姬NO43',
            netLinkMan: '金鱼姬NO23',
            netLinkPhone: '15566936655',
            netLinkMain: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'D000002125',
            netName: '金鱼姬NO43',
            netLinkMan: '金鱼姬NO23',
            netLinkPhone: '15566936655',
            netLinkMain: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
        ],
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          productpackageName: [
            {required: true, message: "问卷名称不能为空", trigger: "blur"}
          ],
          productpackagecode: [
            {required: true, message: "问卷编码不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      // 编辑格式化地址
      handleChange() {
        var self = this;
        var provinceCode = self.selectedOptions[0];
        var cityCode = self.selectedOptions[1];
        // CodeToText属性是区域码，属性值是汉字 CodeToText['110000']输出北京市
        self.infoForm.province = CodeToText[provinceCode];
        self.infoForm.city = CodeToText[cityCode];
        console.log("选择的省市：", self.infoForm.province, self.infoForm.city);
      },
      // 获取到省市数据，进行格式化
      formatCity() {
        var self = this;
        console.log(self.infoForm.province, self.infoForm.city);
        // TextToCode属性是汉字，属性值是区域码 TextToCode['北京市'].code输出110000
        // 省份
        var provinceCode = TextToCode[self.infoForm.province].code;
        // 城市
        var cityCode = TextToCode[self.infoForm.province][self.infoForm.city].code;
        self.selectedOptions = [provinceCode, cityCode];
      },
      getCity() {
        var self = this;
        let data = localStorage.getItem('kimid');
        self.$api.getcustomers(data).then(res => {
          self.infoForm.province = res.data.province,//省
            self.infoForm.city = res.data.city;
          self.formatCity();
        })
      },
      // 省份 市 县联动
      selectProvinceFun(event) {
        console.log(event)
        if (event) {
          this.cityList = event.children
        } else {
          this.cityList = []
        }
        this.areaList = []
        this.$emit('getLawyerListInfo', [event.label, 'province'])
      },
      selectCityFun(event) {
        console.log(event)
        if (event) {
          this.areaList = event.children
        } else {
          this.areaList = []
        }
        this.$emit('getLawyerListInfo', [event.label, 'city'])
      },
      selectAreaFun(event) {
        console.log(event)
        this.$emit('getLawyerListInfo', [event.label, 'area'])
      },
      /** 查询产品包审核列表 */
      getList() {
        this.loading = true;
        listExamine(this.queryParams).then(response => {
          this.examineList = response.rows;
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
        this.reset();
        this.open = true;
        this.title = "新建公共问题";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const productpackageno = row.productpackageno || this.ids
        getExamine(productpackageno).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品包审核";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.open = false;
            this.$message({
              message: '编辑成功',
              type: 'success'
            });
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const productpackagenos = row.productpackageno || this.ids;
        this.$confirm('是否确认删除产品名称为"' + productpackagenos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delExamine(productpackagenos);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 产品包维护 */
      maintain(row) {
        const servcomno = row.servcomno || this.ids[0];
        this.$router.push({path: '/examine/examine/maintain/' + servcomno});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('examine/examine/export', {
          ...this.queryParams
        }, `examine_examine.xlsx`)
      },
      releaseFun() {
        this.$message({
          message: '发布成功',
          type: 'success'
        });
      },
      disabledFun() {
        this.$message({
          message: '禁用成功',
          type: 'success'
        });
      },
      startFun() {
        this.$message({
          message: '启用成功',
          type: 'success'
        });
      },
      copyFun() {
        this.$message({
          message: '复制成功',
          type: 'success'
        });
      },
      updateFun() {
        this.$message({
          message: '更新成功',
          type: 'success'
        });
      },
      delFun() {
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      },
      saveAsDraft() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.open = false;
            this.$message({
              message: '保存为草稿成功',
              type: 'success'
            });
          }
        });
      },
      saveAndPut() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.open = false;
            this.$message({
              message: '已保存并发布',
              type: 'success'
            });
          }
        });
      },
    }
  };
</script>
