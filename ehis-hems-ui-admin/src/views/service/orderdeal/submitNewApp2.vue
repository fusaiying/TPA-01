<template>
  <div class="app-container">
    <el-steps :active="active" finish-status="success" align-center :space="1000">
      <el-step title="选择申请客户"></el-step>
      <el-step title="提交申请请求"></el-step>
    </el-steps>

    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">
      <el-row class="mt10">
        <!-- 第一个card -->
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-document mr5"></i>
            <span>客户信息</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="客户姓名">
                <el-input v-model="form.name" placeholder="请输入客户姓名" clearable :disabled="true"/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="客户性别">
                <el-select v-model="form.sex" placeholder="请输入客户性别"
                           clearable
                           :disabled="true"
                >
                  <el-option
                    v-for="dict in sexOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="出生日期">
                <el-date-picker clearable
                                v-model="form.birthday"
                                type="date"
                                value-format="yyyy-MM-dd"
                                :disabled="true"
                                placeholder="请输入出生日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系电话">
                <el-input v-model="form.phone" placeholder="请输入联系电话" clearable :disabled="true"/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件类型">
                <el-select v-model="form.idtype" placeholder="请输入证件类型"
                           clearable
                           :disabled="true"
                >
                  <el-option
                    v-for="dict in idTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件号码">
                <el-input v-model="form.idcardno" placeholder="请输入证件号码" clearable :disabled="true"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>

    <el-row class="mt10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-document mr5"></i>
          <span>服务产品信息</span>
        </div>
      <!-- 表格区域 -->
        <el-table :data="productList">
          <el-table-column label="产品名称" align="center" prop="productname"/>
          <el-table-column label="服务名称" align="center" prop="servicename"/>
          <el-table-column label="总次数" align="center" prop="upperlimit"/>
          <el-table-column label="剩余次数" align="center" prop="balance"/>
          <el-table-column label="取消次数" align="center" prop=""/>
          <el-table-column label="服务说明" align="center" prop=""/>
          <el-table-column label="有效期至" align="center" prop="enddate"/>
          <el-table-column label="限制标记" align="center" prop="limitunit"/>
        </el-table>
        <el-row class="mt10">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <!--<i class="el-icon-document mr5"></i>-->
              <span>产品描述</span>
            </div>
            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="mb10">
              <editor v-model="productForm.remark" :min-height="192" :contenteditable="true"/>
            </el-col>
          </el-card>
        </el-row>
      </el-card>
    </el-row>

    <el-row class="mt10">
      <!-- 第二个card -->
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-grid mr5"></i>
          <span>既往使用人列表</span>
        </div>

        <!-- 使用人列表 表格 -->
        <el-table :data="userCustomerList">
          <el-table-column type="index" label="序号" align="center"/>
          <el-table-column label="使用人姓名" align="center" prop="user"/>
          <el-table-column label="使用人性别" align="center" prop="usersex"/>
          <el-table-column label="出生日期" align="center" prop="userbirthday"/>
          <el-table-column label="证件类型" align="center" prop="useridtype"/>
          <el-table-column label="证件号码" align="center" prop="useridcode"/>
          <el-table-column label="手机号码" align="center" prop="usermobile"/>
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :limit.sync="queryParams.pageSize"
          @pagination="queryUserCustomerList"
        />
      </el-card>
    </el-row>

    <el-form ref="userform" :model="userform" :rules="rules" :inline="true" label-width="125px">
      <el-row class="mt10">
        <!-- 第三个card -->
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-custom mr5"></i>
            <span>新建或更新使用人信息</span>
          </div>

          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人姓名" prop="user">
                <el-input v-model="userform.user" placeholder="请输入使用人姓名" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件类型" prop="useridtype">
                <el-select v-model="userform.useridtype" placeholder="请输入证件类型" clearable>
                  <el-option
                    v-for="dict in idTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件号码" prop="useridcode">
                <el-input v-model="userform.useridcode" placeholder="请输入证件号码" clearable/>
              </el-form-item>
            </el-col>

          </el-row>
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人性别" prop="usersex">
                <el-select v-model="userform.usersex" placeholder="请选择使用人性别" clearable>
                  <el-option
                    v-for="dict in sexOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="出生日期" prop="userbirthday">
                <el-date-picker clearable
                                v-model="userform.userbirthday"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择出生日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="手机号码" prop="usermobile">
                <el-input v-model="userform.usermobile" placeholder="请输入手机号码" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="预约供应商" prop="supplier">
                <el-select v-model="orderform.supplier" placeholder="请选择预约供应商"
                           @change="changeServiceWeb(orderform.supplier)"
                           clearable>
                  <el-option
                    v-for="dict in supplierOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="预约网点" prop="supplieroutlets">
                <el-select v-model="orderform.supplieroutlets" placeholder="请选择预约网点" clearable>
                  <el-option
                    v-for="dict in supplierOutletsOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="预约医生" prop="supplierdoctor">
                <el-select v-model="orderform.supplierdoctor" placeholder="请选择预约医生" clearable>
                  <el-option
                    v-for="dict in supplierDoctorOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="预约渠道" prop="ordersource">
                <el-select v-model="orderform.ordersource" placeholder="请选择预约渠道" clearable>
                  <el-option
                    v-for="dict in orderSourceOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="预约时间" prop="appointmenttime">
                <el-date-picker clearable
                                v-model="orderform.appointmenttime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择预约时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <el-row class="mt10">
        <!-- 第四个card -->
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-edit mr5"></i>
            <span>新建联系人/监护人</span>
          </div>
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="姓名">
                <el-input v-model="userform.contacts" placeholder="请输入姓名" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件类型">
                <el-select v-model="userform.contactsidtype" placeholder="请输入证件类型" clearable>
                  <el-option
                    v-for="dict in idTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件号码">
                <el-input v-model="userform.contactsidcode" placeholder="请输入证件号码" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="手机号码">
                <el-input v-model="userform.contactsmobile" placeholder="请输入手机号码" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
    <!-- 按钮区域 -->
    <el-row class="mt10">
      <el-card class="box-card" shadow="hover">
        <el-row style="text-align: right;">
          <el-col>
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="submitForm"
            >提交
            </el-button>
            <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">上一步</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>

  </div>
</template>

<script>
  import {getServiceCom, getServiceWeb, getCustomerInfo, getCustomerServiceInfo, saveOrderInfo, getUserCustomerList} from "@/api/service/submitNewApp";
  import Editor from "@/components/Editor/index";

  export default {
    name: "submitNewApp",
    components: {Editor},
    data() {
      return {
        active: 1,
        total: 0,
        loading: false,
        customerid: null,
        productcode: null,
        form: {
          name:null,
          sex:null,
          birthday:null,
          phone:null,
          idtype:null,
          idcardno:null
        },
        userform: {
          user:null,
          useridtype:null,
          useridcode:null,
          usersex:null,
          userbirthday:null,
          usermobile:null,
          contacts:null,
          contactsidtype:null,
          contactsidcode:null,
          contactsmobile:null
        },
        queryParams: {
          pageNum: 1,
          pageSize: 5
        },
        productForm: {
          remark:null
        },
        orderform:{
          supplier:null,
          supplieroutlets:null,
          supplierdoctor:null,
          ordersource:null,
          appointmenttime:null
        },
        //服务产品列表
        productList: [],
        //使用人列表
        userCustomerList: [],
        //证件字典
        idTypeOptions: [],
        //性别字典
        sexOptions: [],
        //供应商字典
        supplierOptions: [],
        //网点字典
        supplierOutletsOptions: [],
        //医生字典
        supplierDoctorOptions: [],
        //预约渠道字典
        orderSourceOptions: [],
        rules: {
          user: [
            {required: true, message: "使用人类型不能为空", trigger: "blur"}
          ],
          useridtype: [
            {required: true, message: "证件类型不能为空", trigger: "change"}
          ],
          useridcode: [
            {required: true, message: "证件号码不能为空", trigger: "blur"}
          ],
          usersex: [
            {required: true, message: "使用人性别不能为空", trigger: "change"}
          ],
          userbirthday: [
            {required: true, message: "出生日期不能为空", trigger: "blur"}
          ],
          usermobile: [
            {required: true, message: "手机号码不能为空", trigger: "blur"}
          ]
        }
      }
    },
    created() {
      const code = this.$route.params && this.$route.params.code;
      this.customerid = code.split("_")[0];
      this.productcode = code.split("_")[1];

      //获取客户信息
      getCustomerInfo(this.customerid).then(response => {
        if (response.code === 200) {
          this.form = response.data;
        }else {
          this.msgError(response.msg);
        }
      });
      //获取客户服务信息
      getCustomerServiceInfo(this.customerid,this.productcode).then(response => {
        if (response.code === 200) {
          this.productList.push(response.data);
        }else {
          this.msgError(response.msg);
        }
      });
      //获取既往受益人
      this.queryUserCustomerList();

      //供应商
      getServiceCom(this.productcode).then(response => {
          this.supplierOptions = response.data;
      });
      // 证件类型
      this.getDicts("id_type").then(response => {
        this.idTypeOptions = response.data;
      });
      // 客户性别
      this.getDicts("sys_user_sex").then(response => {
        this.sexOptions = response.data;
      });
      // 预约渠道
      this.getDicts("service_ordersource").then(response => {
        this.orderSourceOptions = response.data;
      });
    },
    methods: {

      queryUserCustomerList(){
        this.queryParams.customerid = this.customerid;
        this.queryParams.productcode = this.productcode;
        getUserCustomerList(this.queryParams).then(response => {
          this.userCustomerList = response.rows;
          this.total = response.total;
          });
      },

      changeServiceWeb(serviceCom){
        this.orderform.supplieroutlets = null;
        getServiceWeb(serviceCom).then(response => {
            this.supplierOutletsOptions = response.data;
          }
        );
      },

      //提交申请
      submitForm() {
        this.$refs["userform"].validate(valid => {
          if (valid) {
            this.$confirm('是否确认提交，提交后不可修改？', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(() => {
              saveOrderInfo(this.customerid,this.productcode,this.userform,this.orderform).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("保存成功");
                  this.open = true;
                  this.title = '短信提醒服务';
                }else {
                  this.msgError(response.msg);
                }
              });
            }).catch(function () {
            });
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
        });
      },
      back() {
        this.$router.push({path: '/service/orderdeal/newApp1'});
      },
    },
  }
</script>

<style scoped>

</style>
