<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i><span>产品基础信息</span>
        </div>
        <el-form :model="form" ref="form"  :inline="true" label-width="125px">
          <el-row :gutter="10" class="mb8">
            <el-col :span="8">
              <el-form-item label="产品编码" prop="productno">
                <el-input v-model="form.productno" placeholder="请输入产品编码"
                          clearable
                          :disabled="true"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
            <el-col :span="8">
              <el-form-item label="产品名称" prop="productname">
                <el-input v-model="form.productname" placeholder="请输入产品名称"
                          clearable
                          :disabled="true"
                />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="对外产品名称" prop="exterproductname">
                <el-input v-model="form.exterproductname" placeholder="请输入对外产品名称"
                          clearable
                          :disabled="true"
                />
              </el-form-item>
            </el-col>
          <el-col :span="8">
            <el-form-item label="产品类型" prop="producttype">
              <el-select v-model="form.producttype" placeholder="请选择产品类型"
                         clearable
                         :disabled="true"
              >
                <el-option
                  v-for="dict in productTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
            <el-col :span="8">
              <el-form-item label="服务类型" prop="servicetype">
                <el-select v-model="form.servicetype" placeholder="请选择服务类型"
                           clearable
                           :disabled="true">
<!--                           @change="changeProductClass"-->
                  <el-option
                    v-for="dict in serviceTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="产品类别" prop="productclass">
                <el-select v-model="form.productclass" placeholder="请输入产品类别"
                           clearable
                           :disabled="true">
<!--                           @change="changeProductSubClass"-->
                  <el-option
                    v-for="dict in productClassOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          <el-col :span="8">
            <el-form-item label="产品细类" prop="productsubclass">
              <el-select
                v-model="form.productsubclass"
                placeholder="请输入产品细类"
                clearable
                :disabled="true"
              >
                <el-option
                  v-for="dict in productSubClassOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="产品售价" prop="normalprice">-->
<!--              <el-input v-model="form.normalprice" placeholder="请输入产品销售价格(单位:元)"-->
<!--                        clearable-->
<!--                        :disabled="true"-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="币种" prop="currency">-->
<!--              <el-select-->
<!--                v-model="form.currency"-->
<!--                placeholder="请输入币种"-->
<!--                clearable-->
<!--                :disabled="true"-->
<!--              >-->
<!--                <el-option-->
<!--                  v-for="dict in productCurrencyOptions"-->
<!--                  :key="dict.dictValue"-->
<!--                  :label="dict.dictValue+' - '+dict.dictLabel"-->
<!--                  :value="dict.dictValue"-->
<!--                />-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          </el-row>
          <el-row :gutter="10" class="mb8">
<!--          <el-col :span="8">-->
<!--            <el-form-item label="税价" prop="taxprice">-->
<!--              <el-input v-model="form.taxprice" placeholder="请输入税价(单位:元)"-->
<!--                        clearable-->
<!--                        :disabled="true"-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="服务限期" prop="servicelimit">
              <el-input v-model="form.servicelimit" placeholder="请输入服务限期"
                        clearable
                        :disabled="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="限期单位" prop="servicelimitflag">
              <el-select
                v-model="form.servicelimitflag"
                placeholder="请输入限期单位"
                clearable
                :disabled="true"
              >
                <el-option
                  v-for="dict in servicelimitflagOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
            <el-col :span="8">
              <el-form-item label="退费期/天" prop="refunddate">
                <el-input v-model="form.refunddate" placeholder="请输入产品退费期/天"
                          clearable
                          :disabled="true"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
<!--            <el-col :span="8">-->
<!--              <el-form-item label="服务成本" prop="servicecost">-->
<!--                <el-input v-model="form.servicecost" placeholder="请输入服务成本"-->
<!--                          clearable-->
<!--                          :disabled="true"-->
<!--                />-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="销售起期" prop="salstartdate">-->
<!--                <el-date-picker size="small"-->
<!--                                v-model="form.salstartdate"-->
<!--                                type="date"-->
<!--                                clearable-->
<!--                                :disabled="true"-->
<!--                                value-format="yyyy-MM-dd"-->
<!--                                placeholder="选择销售起期">-->
<!--                </el-date-picker>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="销售止期" prop="salenddate">-->
<!--                <el-date-picker size="small"-->
<!--                                v-model="form.salenddate"-->
<!--                                type="date"-->
<!--                                clearable-->
<!--                                :disabled="true"-->
<!--                                value-format="yyyy-MM-dd"-->
<!--                                placeholder="选择销售止期">-->
<!--                </el-date-picker>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
          <el-col :span="8">
            <el-form-item label="适用性别" prop="applysex">
              <el-select v-model="form.applysex" placeholder="请选择适用性别"
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
          <el-col :span="8">
            <el-form-item label="最小适用年龄" prop="agemin">
              <el-input v-model="form.agemin" placeholder="请输入最小适用年龄"
                        clearable
                        :disabled="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="最大适用年龄" prop="agemax">
              <el-input v-model="form.agemax" placeholder="请输入最大适用年"
                        clearable
                        :disabled="true"
              />
            </el-form-item>
          </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
          <el-col :span="8">
            <el-form-item label="扣减方式" prop="discounttype">
              <el-select v-model="form.discounttype" placeholder="请选择扣减方式"
                         clearable
                         :disabled="true"
              >
                <el-option
                  v-for="dict in discountTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="税率" prop="taxrate">-->
<!--              <el-input v-model="form.taxrate" placeholder="请输入产品税率"-->
<!--                        clearable-->
<!--                        :disabled="true"-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="服务实施类型" prop="serviceimpletype">-->
<!--                <el-select v-model="form.serviceimpletype" placeholder="请选择服务实施类型"-->
<!--                           clearable-->
<!--                           :disabled="true"-->
<!--                >-->
<!--                  <el-option-->
<!--                    v-for="dict in serviceImpleTypeOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue+' - '+dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  />-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="是否主动外呼" prop="callout">-->
<!--                <el-switch-->
<!--                  v-model="form.callout"-->
<!--                  active-value="01"-->
<!--                  inactive-value="02"-->
<!--                  active-text="是"-->
<!--                  inactive-text="否"-->
<!--                  :disabled="true"-->
<!--                ></el-switch>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
            <el-col :span="8">
              <el-form-item label="是否本人使用" prop="usebyoneself">
                <el-switch
                  v-model="form.usebyoneself"
                  active-value="01"
                  inactive-value="02"
                  active-text="是"
                  inactive-text="否"
                  :disabled="true"
                ></el-switch>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="允许取消预约" prop="allowedtocancel">
                <el-switch
                  v-model="form.allowedtocancel"
                  active-value="01"
                  inactive-value="02"
                  active-text="是"
                  inactive-text="否"
                  :disabled="true"
                ></el-switch>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
<!--            <el-col :span="8">-->
<!--              <el-form-item label="每月收入摊销标识" prop="amortizationflag">-->
<!--                <el-switch-->
<!--                  v-model="form.amortizationflag"-->
<!--                  active-value="Y"-->
<!--                  inactive-value="N"-->
<!--                  active-text="是"-->
<!--                  inactive-text="否"-->
<!--                  :disabled="true"-->
<!--                ></el-switch>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
            <el-col :span="8">
                <el-form-item label="提供在线服务" prop="isprovideonline">
                  <el-switch
                    v-model="form.isprovideonline"
                    active-value="Y"
                    inactive-value="N"
                    active-text="是"
                    inactive-text="否"
                    :disabled="true"
                  ></el-switch>
                </el-form-item>
              </el-col>
            <el-col :span="8">
              <el-form-item label="是否主动提供服务" prop="isprovideservice">
                <el-switch
                  v-model="form.isprovideservice"
                  active-value="Y"
                  inactive-value="N"
                  active-text="是"
                  inactive-text="否"
                  :disabled="true"
                ></el-switch>
              </el-form-item>
            </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="是否为标准产品" prop="isstandardproduct">-->
<!--              <el-switch-->
<!--                v-model="form.isstandardproduct"-->
<!--                active-value="Y"-->
<!--                inactive-value="N"-->
<!--                active-text="是"-->
<!--                inactive-text="否"-->
<!--                :disabled="true"-->
<!--              ></el-switch>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          </el-row>
        </el-form>
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-location mr5"></i><span>服务项目</span>
        </div>

        <el-table :data="projectList" >
          <el-table-column type="index" width="50" align="center"/>
          <el-table-column label="服务项目编码" align="center" prop="projectcode"/>
          <el-table-column label="服务项目名称" align="center" prop="projectname"/>
          <el-table-column label="服务项目分类" align="center" prop="projecttype"/>
        </el-table>
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-office-building mr5"></i><span>服务机构</span>
        </div>
        <el-table :data="serviceOrgList" >
          <el-table-column type="index" width="50" align="center">
          </el-table-column>
          <el-table-column label="机构编码" align="center" prop="servcomno"/>
          <el-table-column label="机构名称" align="center" prop="chname" />
          <el-table-column label="机构类型" align="center" prop="servcomtype"/>
          <el-table-column label="机构等级" align="center" prop="medicallevel"/>
        </el-table>
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-share mr5"></i><span>服务网点</span>
        </div>

        <el-table :data="serviceNetWorkList" >
          <el-table-column type="index" width="50" align="center"/>
          <el-table-column label="网点编码" align="center" prop="websitecode" />
          <el-table-column label="网点名称" align="center" prop="websitename" />
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          :pageSizes="[5,10,15,20]"
          :autoScroll="false"
        />
      </el-card>
    </el-row>

    <!-- 新增产品图文说明 富文本编辑器 -->
    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-view mr5"></i>
          <span>产品图文说明</span>
        </div>
        <editor v-model="form.alternatefield2" :min-height="192" :disabled="true"/>
        <el-row :gutter="10" class="mb8" style="text-align: right;margin-top:10px;">
          <el-button
            type="primary"
            icon="el-icon-view"
            size="mini"
            @click="openProductAttachment"
            v-hasPermi="['system:definition:add']"
          >服务产品条款</el-button>
          <el-button
            icon="el-icon-close"
            size="mini"
            @click="close"
            v-hasPermi="['system:definition:remove']"
          >返 回</el-button>
        </el-row>
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-check mr5"></i><span>产品审核</span>
        </div>
        <el-form :model="auditform" ref="auditform"  :inline="true" v-show="showSearch" label-width="125px">
          <el-row :gutter="10" class="mb8">
            <el-form-item label="审核结果" prop="auditresult">
              <el-select
                v-model="auditform.auditresult"
                placeholder="请选择审核结果"
                clearable
                :disabled="true"
              >
                <el-option
                  v-for="dict in auditResultsOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-row>
          <el-row :gutter="10" class="mb8">
            <el-form-item label="审核意见" prop="reason">
              <el-input type="textarea"
                        v-model="auditform.reason"
                        placeholder="请输入审核意见"
                        style="width: 500px"
                        :disabled="true"
              />
            </el-form-item>
          </el-row>
        </el-form>
        <el-row :gutter="10" class="mb8" style="text-align: right;">
          <el-button
            type="primary"
            icon="el-icon-check"
            size="mini"
            @click="submitPublish"
            v-hasPermi="['product:definition:online']"
          >产品上线</el-button>
          <el-button
            type="primary"
            icon="el-icon-view"
            size="mini"
            @click="reAuditTrack"
            v-hasPermi="['product:definition:reaudit']"
          >重新审核</el-button>
        </el-row>
      </el-card>
    </el-row>


  </div>
</template>

<script>
  import { getServiceType, getProductClass, getProductSubClass} from "@/api/product/definition";
  import {getServiceComNo, getProvinceAll, getCityByProvince, getDistrictByCity, queryServiceOrgAndNetWork, getProductInfoById, queryServiceCity, getProductExamineResult, productPublish, productReAuditTrack} from "@/api/product/info";
  import { selectDictLabel } from "@/utils/sinoutils";
  import Editor from "../../../components/Editor/index";

  export default {
    //components: {ElCol},
    name: "ProductAudit",
    components: {Editor},
    //components: {Treeselect},
    data() {
      return {
        // 遮罩层
        loading: false,
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
        // 审核轨迹
        auditTotal: 0,
        // 获取的产品编码
        productno:"",
        //产品编码是否可以编辑
        disableFlag:false,
        //页面输入框是否变化标记
        modifyNum:0,
        // 用户表格数据
        projectList: [],
        serviceOrgList: [],
        serviceNetWorkList: [],
        serviceCityList: [],
        //服务机构选中的单行
        serviceOrgOneRow:null,
        //服务机构选中的行
        serviceOrgSelectList:[],
        //网点选中的行
        serviceNetWorkSelectList:[],
        //审核轨迹
        auditList:[],
        // 弹出层标题
        title: "产品审核轨迹信息",
        // 是否显示弹出层
        open: false,
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
        // 服务机构类型数据字典
        serivceComTypeOptions: [],
        // 服务机构数据字典
        serivceComNoOptions: [],
        // 服务机构数据字典辅助显示表格内部内容
        serviceComNoOnTableOptions: [],
        // 供应商结算方式数据字典
        settlementTypeOptions: [],
        // 机构状态数据字典
        invaildOptions: [],
        // 省份数据字典
        provinceOptions: [],
        // 城市数据字典
        cityOptions: [],
        // 地区数据字典
        districtOptions: [],
        // 审核结果数据字典
        auditResultsOptions: [],
        // 表单参数
        auditform:{
          auditresult: undefined,
          reason: undefined
        },
        serviceOrg: {
          serivcecomtype: undefined,
          serivcecomno: undefined,
          servicecomcost: undefined,
          currency: undefined,
          payamount: undefined,
          settlementtype: undefined,
          serviceincomeamount: undefined,
          invaild: "Y",
          remark: undefined
        },
        form: {
          productno: undefined,
          productname: undefined,
          exterproductname: undefined,
          producttype: undefined,
          servicetype: undefined,
          productclass: undefined,
          productsubclass: undefined,
          // normalprice: undefined,
          // currency: undefined,
          // taxprice: undefined,
          servicelimit: undefined,
          servicelimitflag: undefined,
          productstate: undefined,
          // callout: "02",
          usebyoneself: "01",
          // servicecost: undefined,
          allowedtocancel: "01",
          // salstartdate: undefined,
          // salenddate: undefined,
          applysex: undefined,
          agemin: undefined,
          agemax: undefined,
          discounttype: undefined,
          // taxrate: undefined,
          refunddate: undefined,
          // amortizationflag: "Y",
          // serviceimpletype: undefined,
          isprovideservice: "Y",
          isprovideonline: "Y",
          // isstandardproduct: "Y"
        },
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 5
        },
        // 查询参数
        auditQueryParams: {
          pageNum: 1,
          pageSize: 10
        },
      };
    },
    created() {
      const productno = this.$route.params && this.$route.params.productno;
      this.productno = productno;
      this.getProductInfoById(productno);
      getServiceType().then(response => {
          this.serviceTypeOptions = response.data;
        }
      );
      getProvinceAll().then(response => {
          this.provinceOptions = response.data;
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
      this.getDicts("service_com_type").then(response => {
        this.serivceComTypeOptions = response.data;
      });
      this.getDicts("supset_tlement_type").then(response => {
        this.settlementTypeOptions = response.data;
      });
      this.getDicts("service_com_invaild").then(response => {
        this.invaildOptions = response.data;
      });
      this.getDicts("audit_conclusion").then(response => {
        this.auditResultsOptions = response.data;
      });
    },
    methods: {
      /** 查询产品详情 */
      getProductInfoById(productno){
        if(productno == "0"){
          return;
        }
        getProductInfoById(productno).then(response => {
          this.form = response.data;
          this.form.params = undefined;
          getProductClass(this.form).then(response => {
              this.productClassOptions = response.data;
            }
          );
          getProductSubClass(this.form).then(response => {
              this.productSubClassOptions = response.data;
            }
          );
          queryServiceOrgAndNetWork(this.form.productno).then(response => {
            if (response.code === 200) {
              this.projectList = response.serviceProject;
              this.serviceOrgList = response.serviceComs;
              this.serviceNetWorkList = response.serviceNetWorks;
              this.total = 1;
              this.loading = false;
              this.modifyNum = 0;
            }
          });

        });
        this.disableFlag = true;
        getProductExamineResult(productno).then(Response => {
            this.auditform = Response.examineresult;
          }
        );
      },


      /** 服务产品条款 */
      openProductAttachment() {
        if(this.productno == undefined || this.productno == null || this.productno == "" || this.productno == "0"){
          this.msgError("请先保存产品基本信息！");
          return;
        }
        this.$router.push({path: '/product/audition/attachment/'+this.form.productno});
      },


      /** 返回按钮操作 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({ path: "/product/publishandmaintain", query: { t: Date.now()}})
      },


    /** 产品上线按钮  */
    submitPublish(){
      // let productstate = this.form.productstate
        productPublish(this.form).then(response => {
          if (response.code === 200) {
            this.msgSuccess("上线成功!")
          }else{
            this.msgError(response.msg);
          }
        });
    },

    /** 重新审核 */
    reAuditTrack() {
      let productstate = this.form.productstate;
      productReAuditTrack(this.form).then(response => {
        if (response.code === 200) {
          this.msgSuccess("重新审核已提交!")
        } else {
          this.msgError(response.msg);
        }
      });
    },
    },

  };
</script>

