<template>
  <div class="app-container">
    <el-form :model="form" ref="form" :rules="rules" :inline="true" v-show="showSearch" label-width="125px">
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-data mr5"></i><span>营销方案基本信息</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="Return123">返回</el-button>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="营销方案编码" prop="productno">
                <el-input v-model="form.productno" placeholder="请输入营销方案编码"
                          clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="营销方案名称" :required="true" prop="productname">
                <el-input v-model="form.productname" placeholder="请输入营销方案名称"
                          clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="优惠方式" :required="true" prop="exterproductname">
                <el-select v-model="form.exterproductname" placeholder="请选择优惠方式"
                           clearable>
                  <el-option
                    v-for="dict in preferentialTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="合作伙伴" :required="true" prop="producttype">
                <el-select v-model="form.producttype" placeholder="请选择合作伙伴"
                           clearable>
                  <el-option
                    v-for="dict in productTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="销售平台" prop="productno">
                <el-select v-model="form.servicetype" placeholder="请选择销售平台"
                           clearable
                           @change="changeProductClass">
                  <el-option
                    v-for="dict in serviceTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="佣金方式" prop="productclass">
                <el-select v-model="form.productclass" placeholder="请选择佣金方式"
                           clearable
                           @change="changeProductSubClass">
                  <el-option
                    v-for="dict in commissionTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="佣金" prop="productsubclass">
                <el-input v-model="form.productname" placeholder="请输入佣金"
                          clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="手续费方式" prop="normalprice">
                <el-input v-model="form.normalprice" placeholder="请输入手续费方式"
                          clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="手续费" :required="true" prop="currency">
                <el-input v-model="form.normalprice" placeholder="请输入手续费"
                          clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="销售起期" prop="taxprice">
                <el-date-picker
                  v-model="value1"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="销售止期" prop="productno">
                <el-date-picker
                  v-model="value2"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="客户类型" prop="productno">
                <el-select
                  v-model="form.servicelimitflag"
                  placeholder="客户类型"
                  clearable>
                  <el-option
                    v-for="dict in servicelimitflagOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-goods mr5"></i><span>优惠列表</span>
          </div>

          <el-table>
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="份数" align="center" prop="productid"/>
            <el-table-column label="满减价格" align="center" prop="productno"/>
            <el-table-column label="优惠值" align="center" prop="productname"/>
          </el-table>

          <pagination
            v-show=true
          />

        </el-card>
      </el-row>

      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-operation mr5"></i><span>产品包选择列表</span>
          </div>
          <el-table width="500px">
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="产品包名称" align="center" prop="productid"/>
            <el-table-column label="产品包售价" align="center" prop="productno"/>
          </el-table>
          <pagination
            v-show=true
          />

        </el-card>
      </el-row>

      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-tickets mr5"></i><span>审核结果</span>
          </div>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="审核结果" prop="">
                <el-select v-model="form.isprovideonline" placeholder="请选择..."
                           clearable>
                  <el-option
                    v-for="dict in sysYesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="审核意见" prop="">
                <el-input v-model="form.refunddate"
                          clearable
                          type="textarea"
                          :rows="1"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row  style="text-align: right;">
            <el-form-item>
              <el-button type="cyan" size="mini" @click="review">审核完毕
              </el-button>
              <el-button type="cyan" size="mini" @click="up">上线
              </el-button>
              <el-button type="cyan" size="mini" @click="down">下线
              </el-button>
              <el-button type="cyan" size="mini" @click="Return123">返回
              </el-button>

            </el-form-item>
          </el-row>


        </el-card>
      </el-row>
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
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },

        },
        value1: '',
        value2: '',
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
        // 优惠方式数据字典
        preferentialTypeOptions: [],
        // 佣金方式数据字典
        commissionTypeOptions: [],
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
      // 得到佣金方式数据字典
      this.getDicts("market_commission_type").then(response => {
        this.commissionTypeOptions = response.data;
      });
      // 得到优惠方式数据字典
      this.getDicts("market_tialway_type").then(response => {
        this.preferentialTypeOptions = response.data;
      });
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
      review() {
        this.msgSuccess("审核完毕！");
      },
      up() {
        this.msgSuccess("上线成功");
      },
      down() {
        this.msgSuccess("下线成功");
      },
      Return123() {
        this.$router.push({
          path: '/marketing/planreview/index/0'
        })
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
