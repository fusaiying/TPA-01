<template>
  <div class="app-container">
    <div>
      <el-steps :active="active" finish-status="success" align-center :space="1000">
        <el-step title="服务申请"></el-step>
        <el-step title="服务分诊"></el-step>
        <!--        <el-step title="服务预约"></el-step>-->
        <!--        <el-step title="服务陪诊"></el-step>-->
        <!--        <el-step title="次日就诊"></el-step>-->
        <el-step title="服务实施"></el-step>
        <el-step title="服务回访"></el-step>
        <el-step title="疑难处理"></el-step>
      </el-steps>
    </div>

    <!-- 表单区域 -->
    <el-form ref="form" :model="form" :inline="true" label-width="125px">
      <!-- 第一个卡片 -->
      <el-row class="mb10 mt20">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-promotion mr5"></i>
            <span>服务信息</span>
          </div>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="来源">
                <el-select v-model="form.ordersource"
                           clearable placeholder="请选择预约来源" disabled>
                  <el-option
                    v-for="dict in ordersourceOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="申请服务">
                <el-input
                  v-model="form.servicename"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人姓名">
                <el-input
                  v-model="form.user"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人性别">
                <el-select v-model="form.usersex"
                           clearable placeholder="请选择使用人性别" disabled>
                  <el-option
                    v-for="dict in usersex"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人证件类型">
                <el-select v-model="form.useridtype"
                           clearable placeholder="请选择证件类型" disabled>
                  <el-option
                    v-for="dict in useridtype"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人证件号码">
                <el-input
                  v-model="form.useridcode"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="使用人手机号码">
                <el-input
                  v-model="form.usermobile"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="使用人出生日期">
                <el-date-picker clearable
                                v-model="form.userbirthday"
                                type="date"
                                disabled
                                value-format="yyyy-MM-dd"
                                placeholder="">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系人姓名">
                <el-input
                  v-model="form.contacts"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系人证件类型">
                <el-select v-model="form.contactsidtype"
                           clearable placeholder="请选择证件类型" disabled>
                  <el-option
                    v-for="dict in useridtype"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系人证件号码">
                <el-input
                  v-model="form.contactsidcode"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系人手机号码">
                <el-input
                  v-model="form.contactsmobile"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="预约供应商">
                <el-input
                  v-model="form.supplier"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商网点">
                <el-input
                  v-model="form.supplieroutlets"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="期望就诊时间">
                <el-date-picker clearable
                                v-model="form.appointmenttime"
                                type="date"
                                disabled
                                value-format="yyyy-MM-dd"
                                placeholder="">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>

    <!-- 分诊表单 -->
    <el-form ref="trialForm" :model="trialForm" :rules="trialRules" label-width="125px" :disabled="trialDisabled">
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-edit-outline mr5"></i>
            <span>分诊信息</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="分诊方式" prop="assigntype">
                <el-select v-model="trialForm.assigntype"
                           clearable placeholder="请选择分诊方式">
                  <el-option
                    v-for="dict in assigntype"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="分诊状态" prop="status">
                <el-select v-model="trialForm.status"
                           clearable placeholder="请选择状态">
                  <el-option
                    v-for="dict in status"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="分诊说明">
                <el-input type="textarea" v-model="trialForm.alternatefield1"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>

    <!-- 服务实施Form -->
    <el-form ref="serviceForm" :model="serviceForm" :rules="serviceRules" label-width="125px"
             :disabled="serviceDisabled">
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover" v-if="showFlag == '02'">
          <div slot="header" class="clearfix">
            <i class="el-icon-data-line mr5"></i>
            <span>服务实施结构信息</span>
          </div>
          <el-row>
            <el-col>
              <el-form-item label="实施内容" prop="resultcontent">
                <el-input
                  v-model="serviceForm.resultcontent"
                  placeholder=""
                  type="textarea"
                  clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="实施状态" prop="status">
                <el-select v-model="serviceForm.status"
                           clearable placeholder="请选择实施状态">
                  <el-option
                    v-for="dict in status"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>

    <el-row style="text-align: right;" class="mt10 mb10">
      <el-col>
        <el-button
          type="danger"
          icon="el-icon-close"
          size="mini"
          @click="submit"
        >撤销申请
        </el-button>
        <el-button icon="el-icon-arrow-right" size="mini" @click="nextStep">下一步</el-button>
        <el-button icon="el-icon-d-arrow-left" size="mini" @click="backFun">返回</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {getServiceInfo} from "@/api/service/queryDeal";
  import {queryProjectData} from "@/api/product/project";
  import {getProvider} from "@/api/provider/provider";
  import {selectComwebsitebyCode} from "@/api/provider/provider";
  import {addTrialInfo, addServiceInfo, updateServiceInfo} from "@/api/service/queryDeal";
  import {updateTrialInfo} from "@/api/service/queryDeal";

  export default {
    name: "serviceQueryDeal",
    inject: ['reload'],
    data() {
      return {
        // multiple: true,
        active: 0,
        form: {
          ordersource: '',
          servicename: '',
          user: '',
          usersex: '',
          supplier: '',
          supplieroutlets: '',
        },
        // 分诊表单
        trialForm: {
          assigntype: '02',
          status: '',
          alternatefield1: '',
        },
        // 服务实施表单
        serviceForm: {
          resultcontent: '',
          status: '',
        },
        // 服务实施规则
        trialRules: {
          // 分诊方式
          assigntype: [
            {required: true, message: '分诊方式必填', trigger: 'change'},
          ],
          // 分诊状态
          status: [
            {required: true, message: '分诊状态必填', trigger: 'change'},
          ],
        },
        // 服务实施规则
        serviceRules: {
          // 实施内容
          resultcontent: [
            {required: true, message: '实施内容必填', trigger: 'blur'},
          ],
          // 实施状态
          status: [
            {required: true, message: '实施状态必填', trigger: 'change'},
          ],
        },
        // 来源 下拉框
        ordersourceOptions: [],
        // 使用人性别
        usersex: [],
        // 使用人证件类型
        useridtype: [],
        // 分诊方式 下拉
        assigntype: [],
        // 分诊状态 下拉
        status: [],
        // 显示信息
        showFlag: '',
        // 工单编号
        ordercode: '',
        // 分诊表单隐藏
        trialDisabled: false,
        // 实施表单隐藏
        serviceDisabled: false,
      }
    },
    created() {
      // const index = this.$route.params && this.$route.params.index;
      // ============  2020-11-17新增  =====================
      // 来源 下拉框
      this.getDicts("service_ordersource").then(response => {
        this.ordersourceOptions = response.data;
      });
      // 性别 下拉框
      this.getDicts("sys_user_sex").then(response => {
        this.usersex = response.data;
      });
      // 证件类型 下拉框
      this.getDicts("id_type").then(response => {
        this.useridtype = response.data;
      });
      // 分诊方式 下拉框
      this.getDicts("service_assigntype").then(response => {
        this.assigntype = response.data;
      });
      // 分诊状态 下拉框
      this.getDicts("service_status").then(response => {
        this.status = response.data;
      });
      // 获取工单编号
      const applicationcode = this.$route.params.index;
      this.ordercode = applicationcode;
      getServiceInfo(applicationcode).then(response => {
        // 来源
        this.form.ordersource = response.serviceInfoVo.hmpServiceOrder.ordersource;
        // 申请服务
        var projectcode = response.serviceInfoVo.hmpServiceOrder.servicecode;
        if (projectcode != null) {
          // 根据服务编码查询服务名称
          queryProjectData(projectcode).then(response => {
            if (response.data.length != 0) {
              this.form.servicename = response.data[0].projectname;
            }
          });
        }
        // 使用人姓名
        this.form.user = response.serviceInfoVo.hmpServiceOrderApplication.user;
        // 性别
        this.form.usersex = response.serviceInfoVo.hmpServiceOrderApplication.usersex;
        // 证件类型
        this.form.useridtype = response.serviceInfoVo.hmpServiceOrderApplication.useridtype;
        // 证件号码
        this.form.useridcode = response.serviceInfoVo.hmpServiceOrderApplication.useridcode;
        // 手机号码
        this.form.usermobile = response.serviceInfoVo.hmpServiceOrderApplication.usermobile;
        // 出生日期
        this.form.userbirthday = response.serviceInfoVo.hmpServiceOrderApplication.userbirthday;
        // 联系人姓名
        this.form.contacts = response.serviceInfoVo.hmpServiceOrderApplication.contacts;
        // 联系人证件类型
        this.form.contactsidtype = response.serviceInfoVo.hmpServiceOrderApplication.contactsidtype;
        // 联系人证件号码
        this.form.contactsidcode = response.serviceInfoVo.hmpServiceOrderApplication.contactsidcode;
        // 联系人手机号
        this.form.contactsmobile = response.serviceInfoVo.hmpServiceOrderApplication.contactsmobile;
        // 供应商
        var supplier = response.serviceInfoVo.hmpServiceOrder.supplier;
        // 根据供应商编码获取供应商名称
        getProvider(supplier).then(response => {
          this.form.supplier = response.data.chname;
        })
        // 就诊时间
        this.form.appointmenttime = response.serviceInfoVo.hmpServiceOrder.appointmenttime;
        // 供应商网点
        var supplieroutlets = response.serviceInfoVo.hmpServiceOrder.supplieroutlets;
        if (supplieroutlets != null) {
          selectComwebsitebyCode(supplieroutlets).then(response => {
            this.form.supplieroutlets = response.hmpComWebSite.websitename;
          })
        }
        // 分诊方式 分诊状态 备注
        if (response.serviceInfoVo.hmpServiceOrderAssign != null) {
          this.trialForm.assigntype = response.serviceInfoVo.hmpServiceOrderAssign.assigntype;
          this.trialForm.status = response.serviceInfoVo.hmpServiceOrderAssign.status;
          this.trialForm.alternatefield1 = response.serviceInfoVo.hmpServiceOrderAssign.alternatefield1;
        }
        // 实施内容 实施状态
        if (response.serviceInfoVo.hmpServiceOrderResult != null) {
          this.serviceForm.resultcontent = response.serviceInfoVo.hmpServiceOrderResult.resultcontent;
          this.serviceForm.status = response.serviceInfoVo.hmpServiceOrderResult.status;
        }
        // 获取工单状态
        var orderstatus = response.serviceInfoVo.hmpServiceOrder.orderstatus;
        // 已经申请 此时应只展示分诊信息
        if (orderstatus == "01") {
          this.showFlag = '01';
          this.active = 1;
        }
        // 已分诊 此时应只展示服务实施信息
        if (orderstatus == "02") {
          this.showFlag = '02';
          this.active = 2;
          this.trialDisabled = true;
        }
        // 已实施 此时定位到回访
        if (orderstatus == '03') {
          this.showFlag = "02";
          this.active = 3;
          this.trialDisabled = true;
          this.serviceDisabled = true;
        }

        if (orderstatus == '04') {
          this.showFlag = '03';
        }
      });
    },
    methods: {

      submit() {
        this.form = {};
        this.$message({
          message: '撤销成功',
          type: 'success'
        });
      },
      backFun() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/service/serviceImp/queryDeal'});
      },
      preStep() {
        if (this.active-- < 0)
          this.active = 7;
      },
      // 服务分诊方法
      method1() {
        // 刷新该标签
        this.reload();
        // 弹框显示
        if (this.trialForm.status == '01') {
          this.msgSuccess("分诊处理中");
        } else {
          this.msgSuccess("分诊处理完成");
        }
      },
      method2() {
        // 刷新该标签
        this.reload();
        // 弹框显示
        if (this.serviceForm.status == '01') {
          this.msgSuccess("实施处理中");
        } else {
          this.msgSuccess("实施处理完成");
        }
      },
      // 点击下一步
      nextStep() {
        // if (this.active++ > 7)
        //   this.active = 0;
        // if (this.active != 0) {
        //   this.multiple = false;
        // }
        // ++ 操作
        if (this.active == 1) {
          // 工单编码
          this.trialForm.ordercode = this.ordercode;
          this.$refs["trialForm"].validate(valid => {
            if (valid) {
              // 根据工单编码查询服务工单
              getServiceInfo(this.ordercode).then(response => {
                // 如果查询不到 那么新增
                if (response.serviceInfoVo.hmpServiceOrderAssign == null) {
                  // 添加分诊信息
                  addTrialInfo(this.trialForm).then(response => {
                  });
                  this.active = 2;
                  setTimeout(this.method1, 1000);
                } else { // 查询的到 那么修改
                  // 修改分诊信息
                  updateTrialInfo(this.trialForm).then(response => {
                  });
                  this.active = 2;
                  setTimeout(this.method1, 1000);
                }
              });
            }
          });
          // 滚动到顶部
          document.documentElement.scrollTop = 0;
          return;
        }
        if (this.active == 2) {
          // 工单编码
          this.serviceForm.ordercode = this.ordercode;
          this.$refs["serviceForm"].validate(valid => {
            if (valid) {
              // 根据工单查询服务实施结果信息
              getServiceInfo(this.ordercode).then(response => {
                // 如果实施结果信息为空 那么新增
                if (response.serviceInfoVo.hmpServiceOrderResult == null) {
                  // 添加服务实施结构信息
                  addServiceInfo(this.serviceForm).then(response => {
                  });
                  this.active = 3;
                  setTimeout(this.method2, 1000);
                } else { // 如果实施结果信息不为空 那么修改
                  // 修改服务实施结果信息
                  updateServiceInfo(this.serviceForm).then(response => {
                  });
                  this.active = 3;
                  setTimeout(this.method2, 1000);
                }
              });
            }
          });
          // 滚动到顶部
          document.documentElement.scrollTop = 0;
          return;
        }
        if (this.active == '3') {
          this.active = 4;
          // 滚动到顶部
          document.documentElement.scrollTop = 0;
          return;
        }
        if (this.active == 4) {
          this.active = 5;
          // 滚动到顶部
          document.documentElement.scrollTop = 0;
          return;
        }
      },
    },

  }
</script>

<style scoped>

</style>
