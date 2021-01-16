<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-data mr5"></i><span>产品基础信息</span>
        </div>
        <el-form :model="form" ref="form" :rules="rules" :inline="true" label-width="125px">
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
                />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="对外产品名称" prop="exterproductname">
                <el-input v-model="form.exterproductname" placeholder="请输入对外产品名称"
                          clearable
                />
              </el-form-item>
            </el-col>
          <el-col :span="8">
            <el-form-item label="产品类型" prop="producttype">
              <el-select v-model="form.producttype" placeholder="请选择产品类型"
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
          </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
            <el-col :span="8">
              <el-form-item label="服务类型" prop="servicetype">
                <el-select v-model="form.servicetype" placeholder="请选择服务类型"
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
            </el-col>
            <el-col :span="8">
              <el-form-item label="产品类别" prop="productclass">
                <el-select v-model="form.productclass" placeholder="请输入产品类别"
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
            </el-col>
          <el-col :span="8">
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
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="产品售价" prop="normalprice">-->
<!--              <el-input v-model="form.normalprice" placeholder="请输入产品销售价格(单位:元)"-->
<!--                        clearable-->
<!--                        @change="getTaxPrice(form.normalprice)"-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="币种" prop="currency">-->
<!--              <el-select-->
<!--                v-model="form.currency"-->
<!--                placeholder="请输入币种"-->
<!--                clearable-->
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
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="服务限期" prop="servicelimit">
              <el-input v-model="form.servicelimit" placeholder="请输入服务限期"
                        clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
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
          </el-col>
            <el-col :span="8">
              <el-form-item label="退费期/天" prop="refunddate">
                <el-input v-model="form.refunddate" placeholder="请输入产品退费期/天"
                          clearable
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
          <el-col :span="8">
            <el-form-item label="适用性别" prop="applysex">
              <el-select v-model="form.applysex" placeholder="请选择适用性别"
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
          </el-col>
          <el-col :span="8">
            <el-form-item label="最小适用年龄" prop="agemin">
              <el-input v-model="form.agemin" placeholder="请输入最小适用年龄"
                        clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="最大适用年龄" prop="agemax">
              <el-input v-model="form.agemax" placeholder="请输入最大适用年"
                        clearable
              />
            </el-form-item>
          </el-col>
          </el-row>
          <el-row :gutter="10" class="mb8">
          <el-col :span="8">
            <el-form-item label="扣减方式" prop="discounttype">
              <el-select v-model="form.discounttype" placeholder="请选择扣减方式"
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
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="税率" prop="taxrate">-->
<!--              <el-input v-model="form.taxrate" placeholder="请输入产品税率"-->
<!--                        clearable-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="服务实施类型" prop="serviceimpletype">-->
<!--                <el-select v-model="form.serviceimpletype" placeholder="请选择服务实施类型"-->
<!--                           clearable-->
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

      <el-table :data="projectList" highlight-current-row @current-change="serviceProjectListCurrentChange">
        <el-table-column type="index" width="50" align="center"/>
        <el-table-column label="服务项目编码" align="center" prop="projectcode"/>
        <el-table-column label="服务项目名称" align="center" prop="projectname"/>
        <el-table-column label="服务项目分类" align="center" prop="projecttype"/>
      </el-table>
      <pagination
        v-show="projectTotal>0"
        :total="projectTotal"
        :page.sync="projectParams.pageNum"
        :limit.sync="projectParams.pageSize"
        :pageSizes="[5,10,15,20]"
        :autoScroll="false"
        @pagination="getProjectList"
      />
    </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-office-building mr5"></i><span>服务机构</span>
        </div>
        <el-table :data="serviceOrgList" highlight-current-row
                  @current-change="serviceOrgListCurrentChange">
          <el-table-column type="index" width="50" align="center">
          </el-table-column>
          <el-table-column label="机构编码" align="center" prop="servcomno"/>
          <el-table-column label="机构名称" align="center" prop="chname" />
          <el-table-column label="机构类型" align="center" prop="servcomtype"/>
          <el-table-column label="机构等级" align="center" prop="medicallevel"/>
        </el-table>
        <pagination
          v-show="orgTotal>0"
          :total="orgTotal"
          :page.sync="orgParams.pageNum"
          :limit.sync="orgParams.pageSize"
          :pageSizes="[5,10,15,20]"
          :autoScroll="false"
          @pagination="getServiceOrgListByProduct"
        />
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
         <i class="el-icon-share mr5"></i><span>服务网点</span>
        </div>

        <el-table :data="serviceNetWorkList" v-loading="netLoading" highlight-current-row @current-change="serviceNetWorkListCurrentChange">
          <el-table-column type="index" width="50" align="center"/>
          <el-table-column label="网点编码" align="center" prop="websitecode" />
          <el-table-column label="网点名称" align="center" prop="websitename" />
        </el-table>

        <el-form>
          <el-form-item style="text-align: right;margin-top:20px;">
            <el-button
              type="primary"
              icon="el-icon-s-claim"
              size="mini"
              @click="handleUpdate"
              v-hasPermi="['system:definition:edit']"
            >服务修改</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-row>

    <!-- 新增产品图文说明 富文本编辑器 -->
    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-view mr5"></i>
          <span>产品图文说明</span>
        </div>
        <editor v-model="form.alternatefield2" :min-height="192"/>
        <el-row :gutter="10" class="mt20" style="text-align: right;">
          <el-button
            type="primary"
            icon="el-icon-s-claim"
            size="mini"
            @click="productInfoUpdate"
            v-hasPermi="['system:definition:edit']"
          >保存</el-button>
        </el-row>
        <el-row :gutter="10" class="mt20" style="text-align: right;">
          <el-button
            type="primary"
            icon="el-icon-s-claim"
            size="mini"
            @click="submitAudit"
            v-hasPermi="['system:definition:add']"
          >提交审核</el-button>
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
  </div>
</template>

<script>
  import {listProduct, getServiceType, getProductClass, getProductSubClass} from "@/api/product/definition";
  import {getServiceComNo, getServiceNetWorkList, getProvinceAll, getCityByProvince, getDistrictByCity, saveProduct, queryServiceOrgAndNetWork, getProductInfoById, saveServiceCity, queryServiceCity, productAudit} from "@/api/product/info";
  import {listProjectInfo, getServiceOrgList} from "@/api/product/project";
  import { selectDictLabel } from "@/utils/sinoutils";
  import Editor from "../../../components/Editor/index";

  export default {
    //components: {ElCol},
    name: "ProductInfo",
    components: {Editor},
    //components: {Treeselect},
    data() {
      return {
        // 遮罩层
        projectLoading: false,
        orgLoading: false,
        netLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        NetTotal: 0,
        projectTotal: 0,
        orgTotal: 0,
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
        // serviceCityList: [],
        //服务项目选中的单行
        serviceProjectOneRow: null,
        //服务项目选中的行
        serviceProjectSelectList: [],
        //服务机构选中的单行
        serviceOrgOneRow:null,
        //服务机构选中的行
        serviceOrgSelectList:[],
        //网点选中的行
        serviceNetWorkSelectList:[],
        // 弹出层标题
        title: "",
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
          auditResults: undefined,
          auditOpinion: undefined
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
          servicecost: undefined,
          allowedtocancel: "01",
          salstartdate: undefined,
          salenddate: undefined,
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
          alternatefield2: undefined
        },
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 服务网点查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 5
        },
        // 服务项目查询参数
        projectParams: {
          pageNum: 1,
          pageSize: 5,
          projectcode: null,
          projectname: null,
          projecttype: null
        },
        // 服务机构查询参数
        orgParams: {
          pageNum: 1,
          pageSize: 5
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
          // normalprice: [
          //   {required: true, message: "产品销售价格不能为空", trigger: "blur"}
          // ],
          // taxprice: [
          //   {required: true, message: "税价不能为空", trigger: "blur"}
          // ],
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
//           isprovideonline: [
//             {required: true, message: "是否提供在线服务不能为空", trigger: "blur"}
//           ],
          // taxrate: [
          //   {required: true, message: "税率必须为最多0-1之间的三位小数的纯数字！", trigger: "blur"},
          //   {
          //     pattern: '^0+(.[0-9]{0,3})?$',
          //     message: "请输入正确的税率",
          //     trigger: "blur"
          //   }
          // ],
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
      // this.getDicts("product_currency").then(response => {
      //   this.productCurrencyOptions = response.data;
      // });
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
    watch:{
      form:{
        handler(){
          this.modifyNum++;
        },
        deep:true
      }
    },
    methods: {
      /** 查询产品详情 */
      getProductInfoById(productno){
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
              // response.serviceComs.forEach(item => {
              //   item.params = undefined;
              //   item.show = false;
              //
              //   getServiceComNo(item).then(response => {
              //     item.serivcecomnoname = selectDictLabel(response.data,item.serivcecomno);
              //     }
              //   );
              //   getServiceComNo(item).then(response => {
              //       this.serivceComNoOptions = response.data;
              //     }
              //   );
              // });
              this.projectList = response.serviceProject;
              this.serviceOrgList = response.serviceComs;
              this.serviceNetWorkList = response.serviceNetWorks;
              this.NetTotal = 1;
              this.netLoading = false;
              this.modifyNum = 0;
            }
          });
          // queryServiceCity(this.form.productno).then(response => {
          //   if (response.code === 200) {
          //     //this.serviceOrgList = response.serviceComs;
          //     response.salComs.forEach(item => {
          //       item.params = undefined;
          //       item.show = false;
          //       getProvinceAll().then(response => {
          //         item.salprovincename = selectDictLabel(response.data,item.salprovince);
          //         }
          //       );
          //       getCityByProvince(item).then(response => {
          //           this.cityOptions = response.data;
          //         }
          //       );
          //       getCityByProvince(item).then(response => {
          //         item.salcityname = selectDictLabel(response.data,item.salcity);
          //         }
          //       );
          //       getDistrictByCity(item).then(response => {
          //           this.districtOptions = response.data;
          //         }
          //       );
          //       getDistrictByCity(item).then(response => {
          //         item.salareaname = selectDictLabel(response.data,item.salarea);
          //         }
          //       );
          //
          //     });
          //     //console.log(response.salComs);
          //     this.serviceCityList = response.salComs;
          //   }
          // });
        });
        this.disableFlag = true;
      },

      /** 查询服务项目列表 */
      getProjectList(){
        this.projectLoading = true;
        listProjectInfo(this.projectParams).then(response => {
          this.projectList = response.rows;
          this.projectTotal = response.total;
          this.projectLoading = false;
        })
      },

      /** 税价获取 */
      // getTaxPrice(normalprice){
      //   if(normalprice>0){
      //     this.form.normalprice=Math.round((normalprice*100))/100;//税价
      //     this.form.taxprice=Math.round(((normalprice/(1+0.06)*0.06)*100))/100;//税价
      //   }
      // },

      /** 服务类型改变触发方法 */
      changeProductClass(){
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
      changeProductSubClass(){
        this.form.productsubclass = undefined;
        getProductSubClass(this.form).then(response => {
            this.productSubClassOptions = response.data;
          }
        );
      },
      /** 服务机构类型改变触发方法 */
      changeServiceComNo(row){
        row.serivcecomno = undefined;
        getServiceComNo(row).then(response => {
            this.serivceComNoOptions = response.data;
          }
        );
      },

      /** 省份改变触发方法 */
      changeCity(row){
        row.salcity = undefined;
        row.salarea = undefined;
        getProvinceAll().then(response => {
            row.salprovincename = selectDictLabel(response.data,row.salprovince);
          }
        );
        getCityByProvince(row).then(response => {
            this.cityOptions = response.data;
          }
        );
      },
      /** 市改变触发方法 */
      changeDistrict(row){
        row.salarea = undefined;
        getCityByProvince(row).then(response => {
            row.salcityname = selectDictLabel(response.data,row.salcity);
          }
        );
        getDistrictByCity(row).then(response => {
            this.districtOptions = response.data;
          }
        );
      },
      /** 地区改变触发方法 */
      changeDistrictName(row){
        getDistrictByCity(row).then(response => {
            row.salareaname = selectDictLabel(response.data,row.salarea);
          }
        );
      },

      /** 服务修改按钮操作 */
      handleUpdate() {
        this.$router.push({path: '/product/definition/subInfo/'+this.form.productno});
      },

      /** 保存按钮操作 */
      productInfoUpdate() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            // console.log(this.form);
            if( this.form.agemin>this.form.agemax){
              this.msgError("请确定适用年龄输入正确！");
            }else{
              this.modifyNum = 0;
              console.log("校验结束")
              saveProduct(this.form).then(response => {
                if (response.code === 200) {
                  this.disableFlag = true;
                  this.form = response.data;
                  this.productno = this.form.productno;
                  this.form.params = undefined;
                  this.modifyNum = 0;

                  this.msgSuccess("保存成功");
                }else{
                  this.msgError(response.msg);
                }
              });
            }
          }
        })
      },

      //给表格内部下拉value转化为name
      getServiceComTypeName(serviceComTypeValue){
        return selectDictLabel(this.serivceComTypeOptions,serviceComTypeValue);
      },
      getCurrencyName(currency){
        return selectDictLabel(this.productCurrencyOptions,currency);
      },
      getSettlementTypeName(settlementtype){
        return selectDictLabel(this.settlementTypeOptions,settlementtype);
      },
      getResultTypeName(row,serviceComValue){
        getServiceComNo(row).then(response => {
          row.serivcecomnoname = selectDictLabel(response.data,serviceComValue);
          }
        );
      },

      //给销售地区下拉value转化为name
      getPlaceName(placeValue){
        return selectDictLabel(this.serivceComTypeOptions,serviceComTypeValue);
      },

      //服务项目选中数据
      serviceProjectListCurrentChange(val){
        console.log(val);
        val.params = undefined;
        this.serviceProjectSelectList.length = 0;
        this.serviceProjectSelectList.push(val);
        this.serviceProjectOneRow = val;
        this.orgLoading = true;
        this.serviceProjectOneRow.pageNum = this.projectParams.pageNum;
        this.serviceProjectOneRow.pageSize = this.projectParams.pageSize;
        getServiceOrgList(this.serviceProjectOneRow).then(response => {
          this.serviceOrgList = response.rows;
          this.orgTotal = response.total;
          this.orgLoading = false;
        })
      },

      // 服务机构分页方法
      getServiceOrgListByProduct(){
        let val = this.serviceProjectOneRow;
        this.orgLoading = true;
        val.pageNum = this.orgParams.pageNum;
        val.pageSize = this.orgParams.pageSize;
        getServiceOrgList(val).then(response => {
          this.serviceOrgList = response.rows;
          this.orgTotal = response.total;
          this.orgLoading = false;
        })
      },

      // 服务机构选中数据
      serviceOrgListCurrentChange(val) {
        this.serviceOrgSelectList.length = 0;
        this.serviceOrgSelectList.push(val);
        this.serviceOrgOneRow = val;
        this.netLoading = true;
        this.serviceOrgOneRow.pageNum = this.queryParams.pageNum;
        this.serviceOrgOneRow.pageSize = this.queryParams.pageSize;
        getServiceNetWorkList(this.serviceOrgOneRow).then(response => {
            this.serviceNetWorkList = response.rows;
            this.NetTotal = response.total;
            this.netLoading = false;
          }
        );
      },

      //获取网点数据
      getServiceNetWorkListByServiceOrg(){
        let val = this.serviceOrgOneRow;
        this.netLoading = true;
        val.pageNum = this.queryParams.pageNum;
        val.pageSize = this.queryParams.pageSize;
        getServiceNetWorkList(val).then(response => {
            this.serviceNetWorkList = response.rows;
            this.NetTotal = response.total;
            this.netLoading = false;
          }
        );
      },

      // 网点单选中数据
      serviceNetWorkListCurrentChange(val) {
        this.serviceNetWorkSelectList.length = 0;
        this.serviceNetWorkSelectList.push(val);
      },

      //ServiceOrgList添加一行
      addServiceOrgListOneRow(){
        if(this.serviceOrgList.length>0){
          this.msgError("只能添加一行数据");
        }else{
          let row = {
            serivcecomtype: null,
            serivcecomno: null,
            servicecomcost: null,
            currency: null,
            payamount: null,
            settlementtype: null,
            serviceincomeamount: null,
            invaild: "Y",
            remark: null,
            show:true
          };
          this.serviceOrgList.push(row);
        }
      },

      /** ServiceOrgList删除一行 */
      deleteServiceOrgListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.serviceOrgList.splice(index,1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },

      //ServiceCityList添加一行
      addServiceCityListOneRow(){
        if(this.productno  == undefined || this.productno == null || this.productno == "" || this.productno == "0"){
          this.msgError("请先添加产品基本信息，并保存！");
          return;
        }
        if(this.modifyNum > 0){
          this.msgError("产品信息有变化，请先保存产品基本信息！");
          return;
        }
        let row = {
          salprovince: null,
          salcity: null,
          salarea: null,
          show:true
        };
        this.serviceCityList.push(row);
      },

      /** ServiceCityList删除一行 */
      deleteServiceCityListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.serviceCityList.splice(index,1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },

      /** 服务产品条款 */
      openProductAttachment() {
        if(this.productno == undefined || this.productno == null || this.productno == "" || this.productno == "0"){
          this.msgError("请先保存产品基本信息！");
          return;
        }
        this.$router.push({path: '/product/definition/attachment/'+this.form.productno});
      },

      /** 服务网点save */
      serviceNetWorkSave(row) {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.projectList.length===0){
              this.msgError("请选择一个服务项目！");
              return false;
            }
            if(this.serviceOrgList.length===0 ){
              this.msgError("请至少录入一条供应商信息！");
              return false;
            }
            console.log("4");
            console.log(this.form);
            // if(this.form.salstartdate!==''&& this.form.salstartdate>this.form.salenddate){
            //   this.msgError("销售止期需大于销售起期！");
            // // }else if((this.form.normalprice===undefined || this.form.normalprice===null || this.form.normalprice===" " ||this.form.normalprice<=0)){
            // //   this.msgError("请输入正确的产品售价！");
            // }else
            console.log(this.form.agemin);
            console.log(this.form.agemax);
            if( this.form.agemin>this.form.agemax){
                this.msgError("请确定适用年龄输入正确！");
            }else{
                console.log("5");
                // if(this.serviceOrgSelectList.length >0){
                //   let te1= this.serviceOrgSelectList[0].servicecomcost;
                //   if(te1==="" || te1===undefined){
                //     this.msgError("请录入供应商成本后保存！");
                //     return false;
                //   }
                //   // // let normalPrice = this.form.normalprice;//产品销售价格
                //   // let te3= this.serviceOrgSelectList[0].payamount;
                //   // if(te3==="" || te3===undefined){
                //   //   this.serviceOrgSelectList[0].serviceincomeamount=Math.round((normalPrice));
                //   // }else{
                //   //   this.serviceOrgSelectList[0].serviceincomeamount=Math.round((normalPrice-te3));
                //   // }
                // }

                this.modifyNum = 0;
                console.log("校验结束")
                saveProduct(this.form,this.serviceProjectSelectList,this.serviceOrgSelectList,this.serviceNetWorkSelectList).then(response => {
                  if (response.code === 200) {
                    this.disableFlag = true;
                    this.form = response.data;
                    this.productno = this.form.productno;
                    this.form.params = undefined;
                    this.modifyNum = 0;
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
                        response.serviceComs.forEach(item => {
                          item.params = undefined;
                          item.show = false;
                          getServiceComNo(item).then(response => {
                              this.serivceComNoOptions = response.data;
                            }
                          );
                          getServiceComNo(item).then(response => {
                              item.serivcecomnoname = selectDictLabel(response.data,item.serivcecomno);
                            }
                          );
                        });
                        this.serviceOrgList = response.serviceComs;
                        this.serviceNetWorkList = response.serviceNetWorks;
                        this.queryParams.pageNum = 1;
                        this.NetTotal = 1;
                        this.netLoading = false;
                        this.modifyNum = 0;
                      }
                    });
                    this.netLoading = false;
                    this.msgSuccess("保存成功");
                  }else{
                    this.msgError(response.msg);
                  }
                });
            }
          }
        });
      },

      /** 服务地区save */
      serviceCitySave(row) {
        if(undefined != this.serviceCityList[0]) {
          let province = this.serviceCityList[0].province;
          if (province == "") {
            this.msgError("请确认销售区域的省已经选择！");
            return false;
          }
        }
        if(this.form.productno == undefined){
          this.msgError("请先保存产品基本信息！");
          return false;
        }
        if(this.modifyNum > 0){
          this.msgError("产品信息有变化，请先保存产品基本信息！");
          return;
        }
        this.$refs["form"].validate(valid => {
          if (!valid) {
            return false;
          }
        });
        if(this.serviceOrgList.length == 0 ){
          this.msgError("请至少选择一条服务机构！");
          return false;
        }
        let te=this.serviceOrgList[0].serivcecomno;
        if(te=="" || te==undefined){
          this.msgError("请选择机构名称后保存！");
          return false;
        }
        saveServiceCity(this.form,this.serviceCityList).then(response => {
          if (response.code === 200) {
            queryServiceCity(this.form.productno).then(response => {
              if (response.code === 200) {
                //this.serviceOrgList = response.serviceComs;
                response.salComs.forEach(item => {
                  item.params = undefined;
                  item.show = false;
                  getProvinceAll().then(response => {
                      item.salprovincename = selectDictLabel(response.data,item.salprovince);
                    }
                  );
                  getCityByProvince(item).then(response => {
                      this.cityOptions = response.data;
                    }
                  );
                  getCityByProvince(item).then(response => {
                      item.salcityname = selectDictLabel(response.data,item.salcity);
                    }
                  );
                  getDistrictByCity(item).then(response => {
                      this.districtOptions = response.data;
                    }
                  );
                  getDistrictByCity(item).then(response => {
                      item.salareaname = selectDictLabel(response.data,item.salarea);
                    }
                  );

                });
                this.serviceCityList = response.salComs;
                this.modifyNum = 0;
              }
            });
            this.msgSuccess("保存成功");
          }else{
            this.msgError(response.msg);
          }
        });
      },

      /** 提交审核 */
      submitAudit(){
        if(this.form.productno == undefined){
          this.msgError("请先保存产品基本信息！");
          return false;
        }
        if(this.modifyNum > 0){
          this.msgError("产品信息有变化，请先保存产品基本信息！");
          return;
        }
        this.$refs["form"].validate(valid => {
          if (!valid) {
            return false;
          }
        });
        // if(this.form.salstartdate!==''&& this.form.salstartdate>this.form.salenddate){
        //   this.msgError("销售止期需大于销售起期！");
        // }else if((this.form.normalprice===undefined || this.form.normalprice===null || this.form.normalprice===" " ||this.form.normalprice<=0)){
        //   this.msgError("请输入正确的产品售价！");
        // }else
        if( this.form.agemin>this.form.agemax){
          this.msgError("请确定适用年龄输入正确！");
        }else{
          productAudit(this.form).then(response => {
              if (response.code === 200) {
                this.form = response.data;
                this.msgSuccess("提交审核成功！");
              }else{
                this.msgError(response.msg);
              }
            }
          );
        }
      },

      /** 关闭按钮操作 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({ path: "/product/definition", query: { t: Date.now()}})
      }
    }
  };
</script>

