<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="125px">
      <el-row :gutter="10" class="mb8">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-data mr5"></i><span>产品包详情信息</span>
          </div>
          <el-form :model="form" ref="form" :rules="rules" :inline="true" label-width="135px">
            <el-row :gutter="10" class="mb8">
              <el-col :span="8">
                <el-form-item label="产品包编码" prop="productno">
                  <el-input v-model="form.productno"
                            placeholder="请输入产品包编码"
                            clearable
                  />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="产品包名称" prop="productname">
                  <el-input v-model="form.productname"
                            placeholder="请输入产品包名称"
                            clearable
                  />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="对外产品包名称" prop="exterproductname">
                  <el-input v-model="form.exterproductname"
                            placeholder="请输入对外产品包名称"
                            clearable
                  />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="10" class="mb8">
              <el-col :span="8">
                <el-form-item label="售价(单位:元)" prop="normalprice">
                  <el-input v-model="form.normalprice" placeholder="请输入售价(单位:元)" clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="税价(单位:元)" prop="taxprice">
                  <el-input
                    v-model="form.taxprice"
                    placeholder="税价(单位:元)"
                    clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="是否推送电子凭证" prop="productclass">
                  <el-select v-model="form.productclass" placeholder="请输入是否推送电子凭证"
                             clearable
                             @change="changeProductSubClass">
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
            <el-row :gutter="10" class="mb8">
              <el-col :span="8">
                <el-form-item label="使用人标记" prop="userTag">
                  <el-input v-model="form.userTag" placeholder="请输入使用人标记"
                            clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="最多使用人数" prop="maxPersonNo">
                  <el-input
                    v-model="form.maxPersonNo"
                    placeholder="请输入最多使用人数"
                    clearable />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="是否可更换使用人" prop="isreplaceuser">
                  <el-select v-model="form.isreplaceuser" placeholder="请输入是否可更换使用人"
                             clearable>
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
            <el-row :gutter="10" class="mb8">
              <el-col :span="8">
                <el-form-item label="销售起期" prop="salstartdate">
                  <el-date-picker
                    v-model="form.salstartdate"
                    type="date"
                    clearable
                    value-format="yyyy-MM-dd"
                    placeholder="选择销售起期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="销售止期" prop="salenddate">
                  <el-date-picker
                    v-model="form.salenddate"
                    type="date"
                    clearable
                    value-format="yyyy-MM-dd"
                    placeholder="选择销售止期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </el-row>

      <el-row :gutter="10" class="mb8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <i class="el-icon-office-building mr5"></i><span>服务机构</span>
          </div>
          <el-table :data="serviceOrgList" highlight-current-row
                    @current-change="handleCurrentChange">
            <el-table-column type="index" width="50" align="center">
            </el-table-column>
            <el-table-column label="机构类型" align="center" prop="serivcecomtype" width="180">
              <template slot-scope="scope">
                <el-select v-model="scope.row.serivcecomtype"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                           @change="changeServiceComNo(scope.row)"
                >
                  <el-option
                    v-for="dict in serivceComTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{getServiceComTypeName(scope.row.serivcecomtype)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="机构名称" align="center" prop="serivcecomno" width="200">
              <template slot-scope="scope">
                <el-select v-model="scope.row.serivcecomno"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                           @change="getResultTypeName(scope.row,scope.row.serivcecomno)"
                >
                  <el-option
                    v-for="dict in serivceComNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show" v-if="">{{scope.row.serivcecomnoname}}</span>
              </template>
            </el-table-column>
            <el-table-column label="供应商成本" align="center" prop="servicecomcost" width="100">
              <template slot-scope="scope">
                <el-input v-model="scope.row.servicecomcost"
                          v-show="scope.row.show"
                          clearable
                          size="mini"
                />
                <span v-show="!scope.row.show">{{scope.row.servicecomcost}}</span>
              </template>
            </el-table-column>
            <el-table-column label="币种" align="center" prop="currency" width="200">
              <template slot-scope="scope">
                <el-select v-model="scope.row.currency"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                >
                  <el-option
                    v-for="dict in productCurrencyOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{scope.row.currency}}</span>
              </template>
            </el-table-column>
            <el-table-column label="代收付金额" align="center" prop="payamount" width="100">
              <template slot-scope="scope">
                <el-input v-model="scope.row.payamount"
                          v-show="scope.row.show"
                          clearable
                          size="mini"
                />
                <span v-show="!scope.row.show">{{scope.row.payamount}}</span>
              </template>
            </el-table-column>
            <el-table-column label="供应商结算方式" align="center" prop="settlementtype" width="200">
              <template slot-scope="scope">
                <el-select v-model="scope.row.settlementtype"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                >
                  <el-option
                    v-for="dict in settlementTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{scope.row.settlementtype}}</span>
              </template>
            </el-table-column>
            <el-table-column label="服务收入金额" align="center" prop="serviceincomeamount" width="100">
              <template slot-scope="scope">
                <el-input v-model="scope.row.serviceincomeamount"
                          v-show="scope.row.show"
                          clearable
                          size="mini"
                />
                <span v-show="!scope.row.show">{{scope.row.serviceincomeamount}}</span>
              </template>
            </el-table-column>
            <el-table-column label="机构状态" align="center" prop="invaild" width="150">
              <template slot-scope="scope">
                <el-select v-model="scope.row.invaild"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                >
                  <el-option
                    v-for="dict in invaildOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{scope.row.invaild}}</span>
              </template>
            </el-table-column>
            <el-table-column label="备注" align="center" prop="remark" width="300">
              <template slot-scope="scope">
                <el-input v-model="scope.row.remark"
                          v-show="scope.row.show"
                          clearable
                          size="mini"
                ></el-input>
                <span v-show="!scope.row.show">{{scope.row.remark}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120" class-name="small-padding fixed-width" fixed="right">
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
                  @click="deleteServiceOrgListOneRow(scope.$index)"
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
        </el-card>
      </el-row>

      <el-row :gutter="10" class="mb8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <i class="el-icon-share mr5"></i><span>服务网点</span>
          </div>

          <el-table :data="serviceNetWorkList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="50" align="center"/>
            <el-table-column label="网点编码" align="center" prop="websitecode"/>
            <el-table-column label="网点名称" align="center" prop="websitename"/>
          </el-table>

          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
          />
          <el-form>
            <el-form-item style="text-align: right;margin-top:10px;">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click=""
                v-hasPermi="['system:definition:add']"
              >保存
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-row>

      <el-row :gutter="10" class="mb8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <i class="el-icon-location mr5"></i><span>服务销售区域</span>
          </div>

          <el-table :data="serviceCityList" @selection-change="handleSelectionChange">
            <el-table-column type="index" width="50" align="center">
            </el-table-column>
            <el-table-column label="省" align="center" prop="salprovince">
              <template slot-scope="scope">
                <el-select v-model="scope.row.salprovince"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                           @change="changeCity(scope.row)"
                >
                  <el-option
                    v-for="dict in provinceOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{scope.row.salprovincename}}</span>
              </template>
            </el-table-column>
            <el-table-column label="市" align="center" prop="salcity">
              <template slot-scope="scope">
                <el-select v-model="scope.row.salcity"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                           @change="changeDistrict(scope.row)"
                >
                  <el-option
                    v-for="dict in cityOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{scope.row.salcityname}}</span>
              </template>
            </el-table-column>
            <el-table-column label="地区" align="center" prop="salarea">
              <template slot-scope="scope">
                <el-select v-model="scope.row.salarea"
                           v-show="scope.row.show"
                           clearable
                           size="mini"
                           @change="changeDistrictName(scope.row)"
                >
                  <el-option
                    v-for="dict in districtOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
                <span v-show="!scope.row.show">{{scope.row.salareaname}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width" fixed="right">
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
                  @click="deleteServiceCityListOneRow(scope.$index)"
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
                @click="addServiceCityListOneRow"
                v-hasPermi="['system:definition:add']"
              >新增
              </el-button>
            </el-col>
          </el-row>

          <!--<el-form>-->
          <!--<el-form-item style="text-align: right;margin-top:10px;">-->
          <!--<el-button-->
          <!--type="primary"-->
          <!--icon="el-icon-s-claim"-->
          <!--size="mini"-->
          <!--@click=""-->
          <!--v-hasPermi="['system:definition:add']"-->
          <!--&gt;保存</el-button>-->
          <!--</el-form-item>-->
          <!--</el-form>-->
          <el-row :gutter="10" class="mb8" style="text-align: right;">
            <el-button
              type="primary"
              icon="el-icon-s-claim"
              size="mini"
              @click=""
              v-hasPermi="['system:definition:edit']"
            >保存
            </el-button>
          </el-row>
          <el-row :gutter="10" class="mb8" style="text-align: right;">
            <el-button
              type="primary"
              icon="el-icon-s-claim"
              size="mini"
              @click=""
              v-hasPermi="['system:definition:add']"
            >提交审核
            </el-button>
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              @click=""
              v-hasPermi="['system:definition:add']"
            >服务产品条款
            </el-button>
            <el-button
              icon="el-icon-close"
              size="mini"
              @click=""
              v-hasPermi="['system:definition:remove']"
            >返 回
            </el-button>
          </el-row>
          <!--          <el-row :gutter="10" class="mb8" style="text-align: right;">
                      <el-button
                        type="primary"
                        icon="el-icon-view"
                        size="mini"
                        @click=""
                        v-hasPermi="['system:definition:edit']"
                      >服务产品条款</el-button>
                      <el-button
                        icon="el-icon-close"
                        size="mini"
                        @click=""
                        v-hasPermi="['system:definition:remove']"
                      >返 回</el-button>
                      <el-button
                        type="primary"
                        icon="el-icon-remove-outline"
                        size="mini"
                        @click=""
                        v-hasPermi="['system:definition:add']"
                      >产品下线</el-button>
                    </el-row>-->
        </el-card>
      </el-row>
    </el-form>

  </div>
</template>

<script>
  import {listProduct, getServiceType, getProductClass, getProductSubClass} from "@/api/product/definition";
  import {
    getServiceComNo,
    getServiceNetWorkList,
    getProvinceAll,
    getCityByProvince,
    getDistrictByCity
  } from "@/api/product/info";
  import {selectDictLabel} from "@/utils/sinoutils";
  import ElCol from "element-ui/packages/col/src/col";

  export default {
    components: {ElCol},
    name: "ProductInfo",
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
        serviceOrgList: [],
        serviceNetWorkList: [],
        serviceCityList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //卡单属性数据字典
        cardTypeOptions: [],
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
        auditform: {
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
          invaild: undefined,
          remark: undefined
        },
        form: {
          isreplaceuser: undefined,
          cardtype: undefined,
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
          pageSize: 10
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
          // 用户给
          userTag: [
            {required: true, message: "使用人标记不能为空", trigger: "blur"}
          ],
          // 最多使用人数
          maxPersonNo: [
            {required: true, message: "最多使用人数不能为空", trigger: "blur"}
          ],
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
    created() {
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
      this.getDicts("sys_card_type").then(response => {
        this.cardTypeOptions = response.data;
      });
    },
    methods: {
      /** 查询用户列表 */

      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.page = 1;
        this.getList();
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
      /** 服务机构类型改变触发方法 */
      changeServiceComNo(row) {
        row.serivcecomno = undefined;
        getServiceComNo(row).then(response => {
            this.serivceComNoOptions = response.data;
          }
        );
      },
      /** 省份改变触发方法 */
      changeCity(row) {
        row.salcity = undefined;
        row.salarea = undefined;
        getProvinceAll().then(response => {
            row.salprovincename = selectDictLabel(response.data, row.salprovince);
          }
        );
        getCityByProvince(row).then(response => {
            this.cityOptions = response.data;
          }
        );
      },
      /** 市改变触发方法 */
      changeDistrict(row) {
        row.salarea = undefined;
        getCityByProvince(row).then(response => {
            row.salcityname = selectDictLabel(response.data, row.salcity);
          }
        );
        getDistrictByCity(row).then(response => {
            this.districtOptions = response.data;
          }
        );
      },

      /** 地区改变触发方法 */
      changeDistrictName(row) {
        getDistrictByCity(row).then(response => {
            row.salareaname = selectDictLabel(response.data, row.salarea);
          }
        );
      },

      //给表格内部下拉value转化为name
      getServiceComTypeName(serviceComTypeValue) {
        return selectDictLabel(this.serivceComTypeOptions, serviceComTypeValue);
      },
      getResultTypeName(row, serviceComValue) {
        getServiceComNo(row).then(response => {
            row.serivcecomnoname = selectDictLabel(response.data, serviceComValue);
          }
        );
      },
      //给销售地区下拉value转化为name
      getPlaceName(placeValue) {
        return selectDictLabel(this.serivceComTypeOptions, serviceComTypeValue);
      },

      // 单选框选中数据
      handleCurrentChange(val) {
        this.loading = true;
        val.pageNum = this.queryParams.pageNum;
        val.pageSize = this.queryParams.pageSize;
        getServiceNetWorkList(val).then(response => {
            this.serviceNetWorkList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.productid);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },

      //ServiceOrgList添加一行
      addServiceOrgListOneRow() {
        let row = {
          serivcecomtype: null,
          serivcecomno: null,
          servicecomcost: null,
          currency: null,
          payamount: null,
          settlementtype: null,
          serviceincomeamount: null,
          invaild: null,
          remark: null,
          show: true
        };
        this.serviceOrgList.push(row);
      },

      /** ServiceOrgList删除一行 */
      deleteServiceOrgListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.serviceOrgList.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

      //ServiceCityList添加一行
      addServiceCityListOneRow() {
        let row = {
          salprovince: null,
          salcity: null,
          salarea: null,
          show: true
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
          this.serviceCityList.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

      /** 保存按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.productid != null) {
              updateInformation(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                }
              });
            } else {
              addInformation(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                }
              });
            }
          }
        });
      },
      /** 关闭按钮操作 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: "/product/definition", query: {t: Date.now()}})
      }
    }
  };
</script>

