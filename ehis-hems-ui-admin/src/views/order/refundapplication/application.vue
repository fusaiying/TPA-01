<template>
  <div class="app-container">
    <el-form :model="form" ref="form" :rules="rules" :inline="true" v-show="showSearch" label-width="125px">
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-tickets mr5"></i><span>订单基本信息</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="Return">返回</el-button>
          </div>
          <el-row>
            <el-col :span="8">
              <el-form-item label="客户健康号" prop="healthId">
                <el-input v-model="form.healthId" placeholder="客户健康号"
                          disabled="true"
                          clearable
                />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="客户姓名" prop="productno">
                <el-input v-model="form.userName" placeholder="客户姓名"
                          disabled="true"
                          clearable
                />
              </el-form-item>
            </el-col>
            <el-form-item label="性别" prop="productno">
              <el-select v-model="form.userSex"
                         disabled="true"
                         clearable
                         placeholder="性别" >
                <el-option v-for="dict in customerTypeOptions"
                           :key="dict.dictValue"
                           :label="dict.dictValue+'-'+dict.dictLabel"
                           :value="dict.dictValue" >
                </el-option>
              </el-select>
            </el-form-item>

            <el-col :span="8">
              <el-form-item label="出生日期" prop="productno">
                <el-date-picker clearable
                                v-model="form.userBirthday"
                                clearable
                                disabled="true"
                                value-format="yyyy-MM-dd"
                                placeholder="出生日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件类型" prop="productno">
                <el-select v-model="form.idType" placeholder="证件类型"
                           disabled="true"
                           clearable
                           @change="changeProductClass">
                  <el-option
                    v-for="dict in serviceTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-form-item label="证件号码" prop="productno">
              <el-input v-model="form.userIdno" placeholder="证件号码"
                        disabled="true"
                        clearable/>
            </el-form-item>

            <el-col :span="8">
              <el-form-item label="订单号" prop="productno">
                <el-input v-model="form.personalOrderno" placeholder="订单号"
                          disabled="true"
                          clearable />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="服务卡号" prop="productno">
                <el-input v-model="form.serviceCardNo" placeholder="服务卡号"
                          disabled="true"
                          clearable />
              </el-form-item>
            </el-col>
            <el-form-item label="手机号" prop="productno">
              <el-input v-model="form.userMobile" placeholder="手机号"
                        disabled="true"
                        clearable/>
            </el-form-item>
          </el-row>
        </el-card>
      </el-row>

      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-order mr5"></i><span>已有服务产品</span>
          </div>
          <el-table :data="informationList1">
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="产品包编码" align="center" prop="productPackageNo"/>
            <el-table-column label="服务产品编码" align="center" prop="productno"/>
            <el-table-column label="服务起期" align="center" prop="salstartDate"/>
            <el-table-column label="服务止期" align="center" prop="salendDate"/>
            <el-table-column label="服务类型" align="center" prop="salType"/>
            <el-table-column label="是否本人使用" align="center" prop="self"/>
            <el-table-column label="允许取消预约" align="center" prop="cancel"/>
            <el-table-column label="待服务次数" align="center" prop="surplusTimes"/>
          </el-table>
          <pagination
            v-show=true
          />
        </el-card>
      </el-row>

      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-data mr5"></i><span>退费信息</span>
          </div>
          <el-row>
            <el-col :span="8">
              <el-form-item label="退款金额" prop="productno">
                <el-input v-model="form.money" placeholder="退款金额"
                          disabled="true"
                          clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="银行账户名" :required="true" prop="bankAccountName">
                <el-input v-model="form.bankAccountName" placeholder="请输入银行账户名" clearable />
              </el-form-item>
            </el-col>

            <el-form-item label="银行账号" :required="true" prop="bankAccount">
              <el-input v-model="form.bankAccount" placeholder="请输入银行账号" clearable />
            </el-form-item>
            <el-col :span="8">
              <el-form-item label="银行名称" :required="true" prop="bankName">
                <el-select v-model="form.bankName" placeholder="请选择银行名称名称" clearable>
                  <el-option
                    v-for="dict in banknames"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-form-item label="银行区域" :required="true" prop="bankArea">
              <el-select v-model="form.bankArea" placeholder="请选择银行区域" clearable>
                <el-option
                  v-for="dict in bankcitys"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-row>
          <el-row style="text-align: right;">
            <el-button type="success" size="mini" @click="confirm">
              确认申请
            </el-button>
            <el-button type="success" size="mini" @click="Return">
              返回
            </el-button>
          </el-row>

        </el-card>
      </el-row>

    </el-form>
  </div>
</template>

<script>
  import {listProduct, getServiceType, getProductClass, getProductSubClass} from "@/api/product/definition";
  import {listCustomer} from "@/api/query/customer";
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
        // 列表
        informationList1: [{
          productPackageNo: '3432212121',
          productno: '434343343',
          salstartDate: '1993-06-10',
          salendDate: '2016-05-02',
          salType: '12',
          self: '是',
          cancel: '是',
          surplusTimes: '23'
        }],
        //银行名称字典
        banknames: [],
        //银行区域字典
        bankcitys: [],
        // 表单参数
        form: {
          healthId: "123",
          userName: "张三",
          idType: "身份证",
          userIdno: "4569831993279232",
          userBirthday: "1988-06-03",
          personalOrderno: "32564856221",
          serviceCardNo: "45302456",
          userMobile: "13568953322",
          userSex: "男",
          money: "322.0",
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
        rules: {
          bankAccountName: [
            {required: true, message: '银行账户名不能为空', trigger: 'blur'},
          ],
          bankName: [
            {required: true, message: '银行名称不能为空', trigger: 'change'},
          ],
          bankAccount: [
            {required: true, message: '银行账号不能为空', trigger: 'blur'},
          ],
          bankArea: [
            {required: true, message: '银行区域不能为空', trigger: 'change'},
          ],

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
      this.getDicts("bank_name").then(response => {
        this.banknames = response.data;
      });
      this.getDicts("bank_city").then(response => {
        this.bankcitys = response.data;
      });
    },
    methods: {
      confirm() {
        this.msgSuccess("申请成功");
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
          path: '/order/refundapplication/applicationindex/0'
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
