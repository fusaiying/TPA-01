<template>
  <div class="app-container">

    <el-form :model="form" ref="form" :rules="rules" :inline="true" v-show="showSearch" label-width="125px">
      <el-card class="box-card" style="margin-bottom: 10px">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i><span>产品基本信息</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="Return">返回</el-button>
        </div>
        <el-form-item label="产品编码" prop="productno">
          <el-input
            v-model="form.productno"
            placeholder="请输入产品编码"
            clearable
          />
        </el-form-item>
        <el-form-item label="产品名称" prop="productname">
          <el-input
            v-model="form.productname"
            placeholder="请输入产品名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="对外产品名称" prop="exterproductname">
          <el-input
            v-model="form.exterproductname"
            placeholder="请输入对外产品名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="产品类型" prop="producttype">
          <el-select
            v-model="form.producttype"
            placeholder="请选择产品类型"
            clearable
          >
            <el-option
              v-for="dict in productTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="服务类型" prop="servicetype">
          <el-select
            v-model="form.servicetype"
            placeholder="请选择服务类型"
            clearable
            @change="changeProductClass"
          >
            <el-option
              v-for="dict in serviceTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品类别" prop="productclass">
          <el-select
            v-model="form.productclass"
            placeholder="请输入产品类别"
            clearable
            @change="changeProductSubClass"
          >
            <el-option
              v-for="dict in productClassOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品细类" prop="productsubclass">
          <el-select
            v-model="form.productsubclass"
            placeholder="请输入产品细类"
            clearable
          >
            <el-option
              v-for="dict in productSubClassOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品销售价格" prop="normalprice">
          <el-input
            v-model="form.normalprice"
            placeholder="请输入产品销售价格(单位:元)"
            clearable
          />
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select
            v-model="form.currency"
            placeholder="请输入币种"
            clearable
          >
            <el-option
              v-for="dict in productCurrencyOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="税价" prop="taxprice">
          <el-input
            v-model="form.taxprice"
            placeholder="请输入税价(单位:元)"
            clearable
          />
        </el-form-item>
        <el-form-item label="服务限期" prop="servicelimit">
          <el-input
            v-model="form.servicelimit"
            placeholder="请输入服务限期"
            clearable
          />
        </el-form-item>
        <el-form-item label="限期单位" prop="servicelimitflag">
          <el-select
            v-model="form.servicelimitflag"
            placeholder="请输入限期单位"
            clearable
          >
            <el-option
              v-for="dict in servicelimitflagOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="有效状态" prop="productstate">
          <el-select
            v-model="form.productstate"
            placeholder="请输入有效状态"
            clearable
          >
            <el-option
              v-for="dict in productStateOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否主动外呼" prop="callout">
          <el-select
            v-model="form.callout"
            placeholder="是否主动外呼"
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
        <el-form-item label="是否本人使用" prop="usebyoneself">
          <el-select
            v-model="form.usebyoneself"
            placeholder="是否本人使用"
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
        <el-form-item label="服务成本" prop="servicecost">
          <el-input v-model="form.servicecost" placeholder="请输入服务成本"
                    clearable
          />
        </el-form-item>
        <el-form-item label="允许取消预约" prop="allowedtocancel">
          <el-select
            v-model="form.allowedtocancel"
            placeholder="请输入是否允许取消预约"
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
        <el-form-item label="销售起期" prop="salstartdate">
          <el-date-picker clearable
                          v-model="form.salstartdate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择销售起期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="销售止期" prop="salenddate">
          <el-date-picker clearable
                          v-model="form.salenddate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择销售止期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="适用性别" prop="applysex">
          <el-select
            v-model="form.applysex"
            placeholder="请选择适用性别"
            clearable
          >
            <el-option
              v-for="dict in sexOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="最小适用年龄" prop="agemin">
          <el-input
            v-model="form.agemin"
            placeholder="请输入最小适用年龄"
            clearable
          />
        </el-form-item>
        <el-form-item label="最大适用年龄" prop="agemax">
          <el-input
            v-model="form.agemax"
            placeholder="请输入最大适用年"
            clearable
          />
        </el-form-item>
        <el-form-item label="扣减方式" prop="discounttype">
          <el-select
            v-model="form.discounttype"
            placeholder="请选择扣减方式"
            clearable
          >
            <el-option
              v-for="dict in discountTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="税率" prop="taxrate">
          <el-input
            v-model="form.taxrate"
            placeholder="请输入产品税率"
            clearable
          />
        </el-form-item>
        <el-form-item label="退费期/天" prop="refunddate">
          <el-input
            v-model="form.refunddate"
            placeholder="请输入产品退费期/天"
            clearable
          />
        </el-form-item>
        <el-form-item label="每月收入摊销标识" prop="amortizationflag">
          <el-select
            v-model="form.amortizationflag"
            placeholder="请输入每月收入摊销标识"
            clearable
          >
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="服务实施类型" prop="serviceimpletype">
          <el-select
            v-model="form.serviceimpletype"
            placeholder="请选择服务实施类型"
            clearable
          >
            <el-option
              v-for="dict in serviceImpleTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否主动提供服务" prop="isprovideservice">
          <el-select
            v-model="form.isprovideservice"
            placeholder="请输入是否主动提供服务"
            clearable
          >
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否提供在线服务" prop="isprovideonline">
          <el-select
            v-model="form.isprovideonline"
            placeholder="请输入是否提供在线服务"
            clearable
          >
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否为标准产品" prop="isstandardproduct">
          <el-select
            v-model="form.isstandardproduct"
            placeholder="请输入是否为标准产品"
            clearable
          >
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue+' - '+dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
      </el-card>

      <el-card class="box-card" style="margin-bottom: 10px">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-shop mr5"></i><span>供应商服务信息</span>
        </div>

        <div>
          <span style="font-family: 微软雅黑;font-weight: 600">服务机构</span>
        </div>
        <el-table :data="informationList1">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="机构类型" align="center" prop="serivcecomtype"/>
          <el-table-column label="机构名称" align="center" prop="serivcecomno"/>
          <el-table-column label="供应商成本" align="center" prop="servicecomcost"/>
          <el-table-column label="币种" align="center" prop="currency"/>
          <el-table-column label="代收付金额" align="center" prop="payamount"/>
          <el-table-column label="供应商结算方式" align="center" prop="settlementtype"/>
          <el-table-column label="服务收入金额" align="center" prop="serviceincomeamount"/>
          <el-table-column label="机构状态" align="center" prop="invaild"/>
          <el-table-column label="备注" align="center" prop="allowedtocancel"/>
        </el-table>
        <pagination
          v-show=true

        />
        <div>
          <span style="font-family: 微软雅黑;font-weight: 600">服务网点</span>

        </div>
        <el-table width="500px" :data="webSiteList">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="网点编码" align="center" prop="websitecode"/>
          <el-table-column label="网点名称" align="center" prop="websitename"/>
        </el-table>
        <pagination
          v-show=true
        />
      </el-card>
      <el-card class="box-card" style="margin-bottom: 10px">
        <div slot="header" class="clearfix">
          <i class="el-icon-location mr5"></i><span>服务销售区域选择</span>
        </div>
        <el-table width="500px" :data="cityList">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="省" align="center" prop="salprovince"/>
          <el-table-column label="市" align="center" prop="salcity"/>
          <el-table-column label="地区" align="center" prop="salarea"/>
        </el-table>

          <el-row style="text-align: right;" >
            <el-col class="mt10 mb10">
              <el-button type="cyan" @click="handleQuery">服务产品条款
              </el-button>
              <el-button type="cyan" @click="Return">返回
              </el-button>
            </el-col>
          </el-row>

      </el-card>
      <el-card class="box-card" style="margin-bottom: 10px">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-check mr5"></i><span>产品审核</span>
        </div>
        <el-row>
          <el-form-item label="审核结果" prop="">
            <el-select v-model="form.isprovideonline" placeholder="请选择..."
                       clearable
            >
              <el-option
                v-for="dict in sysYesNoOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-row>
        <el-form-item label="审核意见" prop="">
          <el-input v-model="form.refunddate"
                    type="textarea"
                    clearable
                    style="width: 500px;"
          />
        </el-form-item>
        <el-row style="text-align: right">
          <el-button type="cyan"
                     @click="proUp">产品上线
          </el-button>
          <el-button type="cyan" @click="examine">重新审核
          </el-button>
        </el-row>
      </el-card>
    </el-form>
  </div>
</template>

<script>
  import {listProduct, getServiceType, getProductClass, getProductSubClass} from "@/api/product/definition";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Product",
    //components: {Treeselect},
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
        // 用户表格数据
        productList: null,
        // 弹出层标题
        //title: "",
        // 部门树选项
        //deptOptions: undefined,
        // 是否显示弹出层
        //open: false,
        // 部门名称
        //deptName: undefined,
        // 默认密码
        //initPassword: undefined,
        // 日期范围
        //dateRange: [],
        // 产品类型数据字典
        productTypeOptions: [],
        // 服务类型数据字典
        serviceTypeOptions: [],
        // 产品类别字典
        productClassOptions: [],
        // 产品细类字典
        productSubClassOptions: [],
        // 产品币种字典
        productCurrencyOptions: [],
        // 时间单位字典
        servicelimitflagOptions: [],
        // 产品状态字典
        productStateOptions: [],
        // 产品是否字典
        yesFlagOptions: [],
        // 性别字典
        sexOptions: [],
        // 扣减字典
        discountTypeOptions: [],
        // YN是否字典
        sysYesNoOptions: [],
        // 服务实施类型字典
        serviceImpleTypeOptions: [],
        cityList: [{
          salprovince: '001-北京市',
          salcity: 'C001-北京市',
          salarea: 'D0002-西城区'
        }],
        webSiteList: [{
          websitecode: '01-医疗机构',
          websitename: 'H010011658-招商信诺'
        }],
        // 列表
        informationList1: [{
          serivcecomtype: '01-医疗机构',
          serivcecomno: 'H010011658-招商信诺',
          servicecomcost: '3000',
          currency: 'CNY-人民币',
          payamount: '3000',
          settlementtype: '01-按基础数据',
          serviceincomeamount: '2300',
          invaild: 'Y-有效'
        }],
        // 表单参数
        form: {
          productno: undefined,
          productname: undefined,
          exterproductname: undefined,
          producttype: undefined,
          servicetype: undefined,
          productclass: undefined,
          productsubclass: undefined,
          normalprice: undefined,
          currency: undefined,
          taxprice: undefined,
          servicelimit: undefined,
          servicelimitflag: undefined,
          productstate: undefined,
          callout: undefined,
          usebyoneself: undefined,
          servicecost: undefined,
          allowedtocancel: undefined,
          salstartdate: undefined,
          salenddate: undefined,
          applysex: undefined,
          agemin: undefined,
          agemax: undefined,
          discounttype: undefined,
          taxrate: undefined,
          refunddate: undefined,
          amortizationflag: undefined,
          serviceimpletype: undefined,
          isprovideservice: undefined,
          isprovideonline: undefined,
          isstandardproduct: undefined
        },
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          productcode: undefined,
          productname: undefined,
          servicetype: undefined,
          productclass: undefined,
          productsubclass: undefined,
          productprice: undefined,
          allowedtocancel: undefined
        },
        // 表单校验
        rules: {
          productname: [
            {required: true, message: "产品名称不能为空", trigger: "blur"}
          ],
          exterproductname: [
            {required: true, message: "对外产品名称能为空", trigger: "blur"}
          ],
          producttype: [
            {required: true, message: "产品类型不能为空", trigger: "blur"}
          ],
          servicetype: [
            {required: true, message: "服务类型不能为空", trigger: "blur"}
          ],
          productclass: [
            {required: true, message: "产品类别不能为空", trigger: "blur"}
          ],
          productsubclass: [
            {required: true, message: "产品细类不能为空", trigger: "blur"}
          ],
          normalprice: [
            {required: true, message: "产品销售价格不能为空", trigger: "blur"}
          ],
          taxprice: [
            {required: true, message: "税价不能为空", trigger: "blur"}
          ],
//          servicelimit: [
//            {required: true, message: "服务限期不能为空", trigger: "blur"}
//          ],
          servicelimitflag: [
            {required: true, message: "限期单位不能为空", trigger: "blur"}
          ],
          salstartdate: [
            {required: true, message: "销售起期不能为空", trigger: "blur"}
          ],
//          taxrate: [
//            {required: true, message: "税率不能为空", trigger: "blur"}
//          ],
//          refunddate: [
//            {required: true, message: "退费期/天不能为空", trigger: "blur"}
//          ],
          isprovideonline: [
            {required: true, message: "是否提供在线服务不能为空", trigger: "blur"}
          ],
          taxrate: [
            {required: true, message: "税率必须为最多0-1之间的三位小数的纯数字！", trigger: "blur"},
            {
              pattern: '^0+(.[0-9]{0,3})?$',
              message: "请输入正确的税率",
              trigger: "blur"
            }
          ],
          refunddate: [
            {required: true, message: "退费期/天必须为正整数", trigger: "blur"},
            {
              pattern: '^[0-9]*$',
              message: "请输入正确的退费期/天",
              trigger: "blur"
            }
          ],
          servicelimit: [
            {required: true, message: "服务限期必须为正整数", trigger: "blur"},
            {
              pattern: '^[0-9]*$',
              message: "请输入正确的服务限期",
              trigger: "blur"
            }
          ],
          agemin: [
            {required: true, message: "适用年龄必须为正整数", trigger: "blur"},
            {
              pattern: '^[0-9]*$',
              message: "请输入正确的适用年龄",
              trigger: "blur"
            }
          ],
          agemax: [
            {required: true, message: "适用年龄必须为正整数", trigger: "blur"},
            {
              pattern: '^[0-9]*$',
              message: "请输入正确的适用年龄",
              trigger: "blur"
            }
          ]
        }
      };
    },
    watch: {},
    created() {
      //this.getList();
      getServiceType().then(response => {
          this.serviceTypeOptions = response.data;
        }
      );
      this.getDicts("product_type").then(response => {
        this.productTypeOptions = response.data;
      });
      this.getDicts("product_currency").then(response => {
        this.productCurrencyOptions = response.data;
      });
      this.getDicts("time_unit").then(response => {
        this.servicelimitflagOptions = response.data;
      });
      this.getDicts("product_state").then(response => {
        this.productStateOptions = response.data;
      });
      this.getDicts("product_yesflag").then(response => {
        this.yesFlagOptions = response.data;
      });
      this.getDicts("sys_user_sex").then(response => {
        this.sexOptions = response.data;
      });
      this.getDicts("product_discount_type").then(response => {
        this.discountTypeOptions = response.data;
      });
      this.getDicts("sys_yes_no").then(response => {
        this.sysYesNoOptions = response.data;
      });
      this.getDicts("product_service_imple_type").then(response => {
        this.serviceImpleTypeOptions = response.data;
      });
    },
    methods: {
      /** 查询用户列表 */
      getList() {
        this.loading = true;
        listProduct(this.queryParams).then(response => {
            this.productList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      /** 删除按钮操作 */
      proUp(row) {
        // this.$confirm('是否确认删除名称为"' + row.deptName + '"的数据项?', "警告", {
        //   confirmButtonText: "确定",
        //   cancelButtonText: "取消",
        //   type: "warning"
        // }).then(function () {
        //   return delDept(row.deptId);
        // }.then(() => {
        this.getList();
        this.msgSuccess("上线成功");
      },
      examine() {
        this.msgSuccess("审核成功");
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          productid: null,
          productno: null,
          productname: null,
          producttype: null,
          servicetype: null,
          costprice: null,
          normalprice: null,
          taxprice: null,
          servicelimit: null,
          servicelimitflag: null,
          callout: null,
          sendcontract: null,
          usebyoneself: null,
          allowedtocancel: null,
          salstartdate: null,
          salenddate: null,
          applysex: null,
          agemin: null,
          agemax: null,
          discounttype: null,
          cardtype: null,
          prooftype: null,
          productstate: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null,
          productcode: null,
          usertype: null,
          taxrate: null,
          refunddate: null,
          servicecost: null,
          productclass: null,
          productsubclass: null,
          exterproductname: null,
          amortizationflag: null,
          currency: null,
          isprovideservice: null,
          isprovideonline: null,
          isstandardproduct: null,
          serviceimpletype: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.page = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        //this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 服务类型改变触发方法 */
      changeProductClass() {
        this.form.productclass = undefined;
        this.form.productsubclass = undefined;
        getProductClass(this.form).then(response => {
            this.productClassOptions = response.data;
          }
        );
        getProductSubClass(this.form).then(response => {
            this.productSubClassOptions = response.data;
          }
        );
      },
      /** 产品类型改变触发方法 */
      changeProductSubClass() {
        this.form.productsubclass = undefined;
        getProductSubClass(this.form).then(response => {
            this.productSubClassOptions = response.data;
          }
        );
      },
      /** 状态维护按钮操作 */
      audit() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.productid);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      Return() {
        this.$router.push({
          path: '/product/publishandmaintain/publishindex/0'
        });
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "定义产品";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const productid = row.productid || this.ids;
        getInformation(productid).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品定义";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.productid != null) {
              updateInformation(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addInformation(this.form).then(response => {
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
        const productids = row.productid || this.ids;
        this.$confirm('是否确认删除产品定义编号为"' + productids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delInformation(productids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/information/export', {
          ...this.queryParams
        }, `system_information.xlsx`)
      }
    }
  };
</script>
