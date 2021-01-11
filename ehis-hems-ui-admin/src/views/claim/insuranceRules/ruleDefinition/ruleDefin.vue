<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>险种详情</span>
      </div>
      <el-row v-loading="dutyLoading" style="margin: 0 40px;padding-bottom: 20px">
        <el-col :span="8">
          <span class="info_span to_right">产品编码：</span><span class="info_span">{{ insuranceInfo.insurancecode }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品名称：</span><span class="info_span">{{ insuranceInfo.insurancename }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品性质：</span><span
          class="info_span">{{getMainflag(insuranceInfo.mainflag) }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品类别：</span><span class="info_span">{{ getInsuranceCategory(insuranceInfo.category) }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品分类：</span><span class="info_span">{{ getInsInsurancetype(insuranceInfo.insurancetype) }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">等待期：</span><span class="info_span">{{ getInsInsurancetype(insuranceInfo.insurancetype) }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">出单公司：</span><span class="info_span">{{ getInsInsurancetype(insuranceInfo.insurancetype) }}</span>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card" style="margin: 20px 0;">
      <div class="tab-title">
        <span>责任信息</span>
        <span style="float: right;">
          <el-button :loading="saveDutyLoading" type="primary" size="mini" @click="dutySaveHandle">保存</el-button>
        </span>
      </div>
      <el-table
        v-loading="dutyLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="pagingData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" width="140" prop="dutycode" label="责任编码" show-overflow-tooltip/>
        <el-table-column align="center" prop="dutyname" label="责任名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="getdutycode" label="给付责任编码" show-overflow-tooltip/>
        <el-table-column align="center" prop="getdutyname" label="给付责任名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="getdutycategory" label="给付责任类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="claimexplain" label="赔付说明" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input v-if="scope.row.editFlag" v-model="scope.row.claimexplain" size="small" maxlength="1000"
                      placeholder="请输入内容"/>
            <span v-else>{{ scope.row.claimexplain }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="isexempt" label="是否豁免" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-if="scope.row.editFlag" v-model="scope.row.isexempt" style="width:150" size="mini"
                       placeholder="请选择" clearable>
              <el-option label="是" value="Y"/>
              <el-option label="否" value="N"/>
            </el-select>
            <span v-else>{{ scope.row.isexempt === 'Y' ? '是' : scope.row.isexempt === 'N' ? '否' : '' }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="type" label="操作" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="dutyEditHandle(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <el-pagination
        :total="resTotalCount"
        :current-page="resPageInfo.page"
        :page-size="resPageInfo.pageSize"
        :page-sizes="[5,10,20,30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="sizes, prev, pager, next"
        @size-change="resSizeChange"
        @current-change="resPageChange"
      />
    </el-card>
    <div style="display: flex; margin-bottom: 20px;">
      <el-row :gutter="20" style="width: 100%;">
        <el-col :span="6">
          <el-card class="collapse-card">
            <el-collapse v-model="activeNames">
              <el-collapse-item v-if="activeName === 'first'" title="涵盖区域选择" name="2">
                <el-checkbox v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                <el-tree
                  ref="areaTree"
                  :data="address"
                  :props="defaultProps"
                  :render-content="renderContent"
                  :default-expanded-keys="defaultAddress"
                  :load="loadNode"
                  style="margin-top: 10px;"
                  lazy
                  show-checkbox
                  node-key="value"/>
              </el-collapse-item>
              <el-collapse-item v-if="activeName === 'first'" title="医院属性选择" name="3">
                <el-tree
                  ref="hospitaltree"
                  :data="rulTreeData"
                  :props="defaultProps"
                  :default-expand-all="isExpand"
                  :render-content="renderContent"
                  style="margin-top: 10px;"
                  show-checkbox
                  node-key="id"/>
              </el-collapse-item>
              <el-collapse-item v-if="activeName === 'first'" title="特殊医院选择" name="4">
                <div>
                  <el-button type="primary" size="mini"
                             style="width: 56px;margin-top: 10px;float: right;margin-left: 15px" @click="addSpecialHos">
                    添加
                  </el-button>
                </div>
                <el-select
                  v-model="specialHos"
                  multiple
                  filterable
                  remote
                  reserve-keyword
                  placeholder="请选择医院"
                  :remote-method="remoteMethod"
                  :loading="specialHosLoading"
                  style="margin-top: 20px;margin-left:5%;width: 90%"
                  size="mini">
                  <el-option
                    v-for="(item, ind) in remoteAddress"
                    :key="ind"
                    :label="item"
                    :value="{value: ind, label: item}">
                  </el-option>
                </el-select>
                <div style="width: 100%">
                  <div v-for="(item, ind) in specialHosList">
                    <span
                      style="display: inline-block;width: 80%;margin-top: 10px;margin-left: 5%">{{item.label}}</span>
                    <span style="display: inline-block;width: 10%;margin-top: 10px">
                      <el-button type="text" size="mini" style="color: red" @click="delSpecialHos(ind)">删除</el-button>
                    </span>
                  </div>
                </div>
              </el-collapse-item>
              <el-collapse-item title="福利对象" name="1">
                <el-tree
                  ref="dutyTree"
                  :data="dutyChooseList"
                  :props="defaultProps"
                  :default-expand-all="isExpand"
                  :render-content="renderContent"
                  :check-strictly="true"
                  style="margin-top: 10px;"
                  show-checkbox
                  node-key="id"/>
              </el-collapse-item>
            </el-collapse>
          </el-card>
        </el-col>
        <el-col :span="18">
          <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
            <el-tab-pane label="规则设置" name="first">
              <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" label-position="right"
                       style="margin-top: 20px" size="mini">
                <el-row>
                  <el-col :span="8">
                    <el-form-item label="区间：" prop="age">
                      <el-input v-model="ruleForm.minage" clearable style="width: 74px;" size="mini"/>
                      -
                      <el-input v-model="ruleForm.maxage" clearable style="width: 74px;"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="区间单位：" prop="useofinsurance">
                      <el-select v-model="ruleForm.unitofelement" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in unitofelementOptions" :key="item.dictValue" :label="item.dictLabel"
                                   :value="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="性别：" prop="isShare">
                      <el-select v-model="ruleForm.isShare" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in isShareOptions" :label="item.dictLabel" :value="item.dictValue"
                                   :key="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="等待期内/外：" prop="waitingperiod">
                      <el-select v-model="ruleForm.waitingperiod" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in waiting_periodOptions" :label="item.dictLabel" :value="item.dictValue"
                                   :key="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-divider/>
                  <el-col :span="8">
                    <el-form-item label="规则要素：" prop="ruleelement">
                      <el-select v-model="ruleForm.ruleelement" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in ruleelementOptions" :key="item.dictValue" :label="item.dictLabel"
                                   :value="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="要素值：" prop="elementvalue">
                      <el-input v-model.number="ruleForm.elementvalue" class="item-width" clearable size="mini"
                                placeholder="请输入"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="要素单位：" prop="unitofelement">
                      <el-select v-model="ruleForm.unitofelement" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in unitofelementOptions" :key="item.dictValue" :label="item.dictLabel"
                                   :value="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="累计方式：" prop="unitofperiod">
                      <el-select v-model="ruleForm.unitofperiod" class="item-width" size="mini" placeholder="请选择"
                                 clearable>
                        <el-option v-for="item in cycle_unitOptions" :label="item.dictLabel" :value="item.dictValue"
                                   :key="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="是否共享：" prop="isShare">
                      <el-select v-model="ruleForm.isShare" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in isShareOptions" :label="item.dictLabel" :value="item.dictValue"
                                   :key="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item
                      label="规则描述："
                      prop="ruleexplain">
                      <el-input v-model="ruleForm.ruleexplain" style="width: 98%;" clearable size="mini"
                                placeholder="请输入"/>
                    </el-form-item>
                  </el-col>
                </el-row>
                <div style="text-align: right; margin-right: 10px;">
                  <el-button
                    :loading="saveLoading"
                    size="mini"
                    type="success"
                    @click="saveHandle"
                  >保存规则
                  </el-button>
                  <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置表单</el-button>
                </div>
              </el-form>
              <el-tabs v-model="list1" style="margin-top: 50px">
                <el-tab-pane label="规则列表" name="first">
                  <div style="height: 40px;margin: 10px 10px;">
                    <el-button type="danger" :loading="commitLoading" size="mini" @click="deleteAll">删除规则</el-button>
                  </div>
                  <el-table
                    v-loading="ruleLoading"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="rulePagingData"
                    size="small"
                    highlight-current-row
                    tooltip-effect="dark"
                    style="width: 100%;"
                    @selection-change="handleSelectionChange">
                    <el-table-column type="selection" align="center"/>
                    <el-table-column align="center" width="140" prop="name" label="福利对象" show-overflow-tooltip/>
                    <el-table-column align="center" prop="ruleinfo" label="规则信息" show-overflow-tooltip/>
                    <el-table-column align="center" prop="quota" label="规则条件" show-overflow-tooltip/>
                    <el-table-column align="center" prop="quotaexplanation" label="规则描述" show-overflow-tooltip/>
                    <el-table-column align="center" prop="quotaexplanation" label="涵盖区域" show-overflow-tooltip/>
                    <el-table-column align="center" prop="quotaexplanation" label="医院属性" show-overflow-tooltip/>
                    <el-table-column align="center" prop="quotaexplanation" label="特殊医院" show-overflow-tooltip/>
                    <el-table-column align="center" label="操作">
                      <template slot-scope="scope">
                        <el-button size="mini" type="text" style="color: #f00;" @click="delHandle(scope.row)">删除
                        </el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                  <!--分页组件-->
                  <el-pagination
                    :total="ruleTotalCount"
                    :current-page="rulPageInfo.page"
                    :page-size="rulPageInfo.pageSize"
                    :page-sizes="[5,10,20,30,50,100]"
                    style="margin-top: 8px; text-align: right;"
                    layout="sizes, prev, pager, next"
                    @size-change="ruleSizeChange"
                    @current-change="rulePageChange"
                  />
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import Cookies from "js-cookie";//  npm install js-cookie --save

  let that
  import moment from 'moment'
  import XLSX from 'xlsx'
  import claimsTable from './components/claimsTable'
  import {get} from '@/api/dictDetail';
  import getAddressByFront from '../../claimsHandle/common/mixins/address.js'
  import {
    getInsuranceInfo,
    updateDutyList,
    getAllFeeitem,
    insertRule,
    updateRiskSubmit,
    getRules,
    deleteRule,
    updateRule,
    getMedicalInstitutionByName,
    getExemption,
    addExemption,
    deleteExemption,
    getExemptionInfoByName,
    getLikeICD10
  } from '@/api/insuranceRules/ruleDefinition.js'
  import jwt from 'jsonwebtoken'

  export default {
    dicts: ['incidenttype', 'visit_type', 'observation', 'waiting_period',
      'compensation_scope', 'useOfMedicalInsurance', 'isShare', 'ruleelement', 'unitofelement',
      'mainflag', 'insurance_category', 'ins_insurancetype', 'cycle_unit', 'gears',
      'exemption_type'],
    components: {
      claimsTable
    },
    mixins: [getAddressByFront],
    filters: {},
    data() {
      const positiveInteger = (rule, value, callback) => {
        if (!Number.isInteger(value)) {
          callback(new Error('允许录入大于等于0的整数'))
        } else {
          if (value < 0) {
            callback(new Error('允许录入大于等于0的整数'))
          } else {
            callback()
          }
        }
      }
      const integerRange = (rule, value, callback) => {
        const str = /^[0-9]*$/
        if (this.ruleForm.minage && !str.test(this.ruleForm.minage) || this.ruleForm.maxage && !str.test(this.ruleForm.maxage)) {
          callback(new Error('允许录入0~200之间的整数'))
        } else if (parseInt(this.ruleForm.minage) < 0 || parseInt(this.ruleForm.minage) > 200 || parseInt(this.ruleForm.maxage) < 0 || parseInt(this.ruleForm.maxage) > 200) {
          callback(new Error('允许录入0~200之间的整数'))
        } else if (parseInt(this.ruleForm.minage) >= parseInt(this.ruleForm.maxage)) {
          callback(new Error('第一位应小于第二位'))
        } else {
          callback()
        }
      }
      const ruleexplainSize = (rule, value, callback) => {
        if (!value) {
          callback('不允许为空')
        } else {
          if (value.length >= 1000) {
            callback('字数小于1000')
          } else {
            callback()
          }
        }
      }
      const checkCumulativeperiod = (rule, value, callback) => {
        if (value && isNaN(parseInt(value))) {
          callback(new Error('请输入数字类型'))
        }
        if (value.length > 20) {
          callback(new Error('累计周期小于20位'))
        } else if (parseInt(value) < 0) {
          callback(new Error('累计周期大于0'))
        } else {
          callback()
        }
      }
      const checkUnitofperiod = (rule, value, callback) => {
        if (this.ruleForm.cumulativeperiod && !value) {
          callback(new Error('如果累计周期不为空，则单位不可为空'))
        } else {
          callback()
        }
      }
      return {
        activeNames: '',
        resTableData: [],
        ruleTableData: [],
        resTotalCount: 0,
        ruleTotalCount: 0,
        rules: {
          age: [{validator: integerRange, trigger: 'change'}],
          elementvalue: [{validator: positiveInteger, required: true, trigger: 'blur'}],
          ruleelement: {required: true, message: '请选择规则要素', trigger: 'change'},
          unitofelement: [{required: true, message: '请输入要素单位', trigger: 'change'}],
          date: [{required: true, message: '请输入有效期限', trigger: 'blur'}],
          ruleexplain: [{validator: ruleexplainSize, required: true, trigger: 'change'}],
          cumulativeperiod: [{validator: checkCumulativeperiod, trigger: 'change'}],
          unitofperiod: [{validator: checkUnitofperiod, message: '若累计周期不为空，单位也不可为空', trigger: 'blur'}]
        },
        resPageInfo: {
          page: 1,
          pageSize: 10
        },
        rulPageInfo: {
          page: 1,
          pageSize: 10
        },
        exemptionInfo: {
          page: 1,
          pageSize: 10
        },
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        rulTreeData: [
          {
            id: 1,
            label: '医院属性名称',
            children: [{
              id: '081',
              label: '公立',
              attr: 'attribute',
              code: '1'
            }, {
              id: '082',
              label: '民营',
              attr: 'attribute',
              code: '2'
            }, {
              id: '083',
              label: '合资',
              attr: 'attribute',
              code: '3'
            }, {
              id: '084',
              label: '外资',
              attr: 'attribute',
              code: '4'
            }, {
              id: '0899',
              label: '其他',
              attr: 'attribute',
              code: '99'
            }]
          },
          {
            id: 2,
            label: '公立医院级别',
            children: [{
              id: '121',
              label: '甲等',
              attr: 'grade',
              code: '1'
            }, {
              id: '122',
              label: '乙等',
              attr: 'grade',
              code: '2'
            }, {
              id: '123',
              label: '丙等',
              attr: 'grade',
              code: '3'
            }, {
              id: '124',
              label: '特等',
              attr: 'grade',
              code: '4'
            }, {
              id: '125',
              label: '其他',
              attr: 'grade',
              code: '5'
            }]
          },
          {
            id: 3,
            label: '公立医院等级',
            children: [{
              id: '091',
              label: '一级',
              attr: 'level',
              code: '1'
            }, {
              id: '092',
              label: '二级',
              attr: 'level',
              code: '2'
            }, {
              id: '093',
              label: '三级',
              attr: 'level',
              code: '3'
            }, {
              id: '0999',
              label: '其他',
              attr: 'level',
              code: '99'
            }]
          },
        ],
        ruleForm: {
          isShare: 'N',
          eventtype: '',
          visittype: '',
          waitingperiod: '',
          observationperiod: '',
          minage: '',
          maxage: '',
          unitofperiod: '',
          compensationscope: '',
          cumulativeperiod: '',
          useofinsurance: '',
          ruleelement: '',
          elementvalue: '',
          unitofelement: '',
          date: '',
          ruleexplain: '',
          dutyruleno: '',
          gears: ''
        },
        insuranceInfo: [],
        dutyBeforeHandle: [],
        dutyParams: {},
        chooseList: [
          {label: '出险类型', field: 'eventtype', value: '01'},
          {label: '就诊类型', field: 'visittype', value: '02'},
          {label: '等待期', field: 'waitingperiod', value: '03'},
          {label: '观察期', field: 'observationperiod', value: '04'},
          {label: '年龄区间', field: 'age', value: '05'},
          {label: '赔付范围', field: 'compensationscope', value: '06'},
          {label: '医保使用', field: 'useofinsurance', value: '07'},
          {label: '医院属性', field: 'attribute', value: '08'},
          {label: '医院等级', field: 'level', value: '09'},
          {label: '医院社保性质', field: 'nature', value: '10'},
          {label: '挡位', field: 'gears', value: '11'},
          {label: '医院级别', field: 'grade', value: '12'}
        ],
        ruleElementList: [],
        unitofList: [],
        dutyChooseList: [],
        getDutyChooseList: [],
        // feeItemChooseList: [],
        ruleLoading: false,
        dutyLoading: false,
        ruletypelist: [
          {code: 'dutycode', value: '02'},
          {code: 'getdutycode', value: '03'},
          // { code: 'feeitemcode', value: '04' }
        ],
        isExpand: true,
        hosLevel: [], // 医院等级
        hosGrade: [], // 医院级别
        hosAttr: [], // 医院属性
        hosNature: [], // 医院社保属性
        saveLoading: false,
        saveDutyLoading: false,
        commitLoading: false,
        address: [],
        remoteAddress: [],
        specialHos: [],
        specialHosList: [],
        specialHosLoading: false,
        defaultAddress: [],
        checkAll: false,
        activeName: 'first',
        list1: 'first',
        list2: 'first',
        exemptionForm: {
          exempttype: '',
          exemptname: [] || '',
          exemptratio: '',
          description: '',
          exemptruleno: ''
        },
        exemptionFormRules: {},
        exemptionTable: [],
        exemptionLoading: false,
        exemptionCount: 0,
        exemptList: [],
        incidenttypeOptions: [],
        visit_typeOptions: [],
        observationOptions: [],
        useOfMedicalInsuranceOptions: [],
        waiting_periodOptions: [],
        isShareOptions: [],
        ruleelementOptions: [],
        unitofelementOptions: [],
        compensation_scopeOptions: [],
        gearsOptions: [],
        cycle_unitOptions: [],
        exemption_typeOptions: [],
        mainflagOptions: [],
        ins_insurancetypeOptions: [],
        insurance_categoryOptions: [],
      }
    },
    computed: {
      // 分页数据处理
      pagingData: function () {
        return this.resTableData.slice((this.resPageInfo.page - 1) * this.resPageInfo.pageSize, this.resPageInfo.page * this.resPageInfo.pageSize)
      },
      rulePagingData: function () {
        return this.ruleTableData.slice((this.rulPageInfo.page - 1) * this.rulPageInfo.pageSize, this.rulPageInfo.page * this.rulPageInfo.pageSize)
      }
    },
    beforeCreate() {
      that = this

    },

    mounted() {
      this.getInsuranceInfoData()
      this.getRulesData()
      this.getHosEveryTypeData()
      this.address = this.getAddressByFront()
      this.getRuleelement()
      this.getUnitofelement()
      this.getDicts("incidenttype").then(response => {
        this.incidenttypeOptions = response.data;
      });
      this.getDicts("visit_type").then(response => {
        this.visit_typeOptions = response.data;
      });
      this.getDicts("observation").then(response => {
        this.observationOptions = response.data;
      });
      this.getDicts("useOfMedicalInsurance").then(response => {
        this.useOfMedicalInsuranceOptions = response.data;
      });
      this.getDicts("waiting_period").then(response => {
        this.waiting_periodOptions = response.data;
      });
      this.getDicts("isShare").then(response => {
        this.isShareOptions = response.data;
      });
      this.getDicts("ruleelement").then(response => {
        this.ruleelementOptions = response.data;
      });
      this.getDicts("unitofelement").then(response => {
        this.unitofelementOptions = response.data;
      });
      this.getDicts("compensation_scope").then(response => {
        this.compensation_scopeOptions = response.data;
      });
      this.getDicts("gears").then(response => {
        this.gearsOptions = response.data;
      });
      this.getDicts("cycle_unit").then(response => {
        this.cycle_unitOptions = response.data;
      });
      this.getDicts("exemption_type").then(response => {
        this.exemption_typeOptions = response.data;
      });
      this.getDicts("mainflag").then(response => {
        this.mainflagOptions = response.data;
      });
      this.getDicts("insurance_category").then(response => {
        this.insurance_categoryOptions = response.data;
      });
      this.getDicts("ins_insurancetype").then(response => {
        this.ins_insurancetypeOptions = response.data;
      });
    },
    methods: {
      getMainflag(value) {
        //return that.dict.label.mainflag[value]
        return this.selectDictLabel(this.mainflagOptions, value);
      },
      getInsuranceCategory(value) {
        //return that.dict.label.insurance_category[value]
        return this.selectDictLabel(this.insurance_categoryOptions, value);
      },
      getInsInsurancetype(value) {
        //return that.dict.label.ins_insurancetype[value]
        return this.selectDictLabel(this.ins_insurancetypeOptions, value);
      },
      handleClick(val) {
        if (this.activeName === 'second') {
          this.exemptionInfo.page = 1
          this.exemptionInfo.pageSize = 10
          this.resetForm()
          this.getExemptionHandle()
        } else {
          this.rulPageInfo.page = 1
          this.rulPageInfo.pageSize = 10
          this.getRulesData()
          this.resetExemptionForm()
        }
      },
      // 查询特殊医院
      remoteMethod(query) {
        if (query !== '') {
          getMedicalInstitutionByName(query).then(res => {
            this.remoteAddress = res.data
          })
        }
      },
      // 添加特殊医院
      addSpecialHos() {
        this.specialHosList = this.specialHosList.concat(this.specialHos)
        this.specialHos = []
        this.remoteAddress = []
      },
      getHospital(query) {
        if (query !== '') {
          getMedicalInstitutionByName(query).then(res => {
            return res.data
          })
        }
      },
      // 导入医院
      importExcel(file) {
        if (!/\.(xls|xlsx)$/.test(file.name.toLowerCase())) {
          this.$message({message: '上传文件格式不正确，请上传xls或者xlsx格式！', type: 'warning'})
          return false
        }
        const fileReader = new FileReader()
        let hosList = []
        fileReader.onload = (ev) => {
          const data = ev.target.result
          const workbook = XLSX.read(data, {
            type: 'array'
          })
          let sheets = workbook.Sheets
          let all = Object.values(sheets)
          all.forEach(item => {
            let everyList = Object.values(item)
            everyList.forEach((item, ind) => {
              if (item.v && ind !== 1) {
                let ob = this.getHospital(item.v)
                // hosList.push(item.v)
              }
            })
          })
        }
        fileReader.readAsArrayBuffer(file.raw)
      },
      // 删除特殊医院
      delSpecialHos(ind) {
        this.specialHosList.splice(ind, 1)
      },
      // 加载省市
      loadNode(node, resolve) {
        // todo 设置末级节点
        if (node.level === 0) {
          return resolve(node.data);
        }
        if (node.level === 1) {
          if (node.label === '北京市' || node.label === '天津市' || node.label === '重庆市' || node.label === '上海市'
            || node.label === '香港特别行政区' || node.label === '澳门特别行政区' || node.label === '台湾省') {
            return resolve([]);
          }
          return resolve(node.data.children);
        }
        return resolve([])
      },
      // 获取等级\级别\属性
      getHosEveryTypeData() {
        // 1-属性,2-级别,3-等级,4-社保性质
        this.rulTreeData.forEach(item => {
          switch (item.id) {
            case 1 :
              this.hosAttr = item.children
              break
            case 2 :
              this.hosGrade = item.children
              break
            case 3 :
              this.hosLevel = item.children
              break
            case 4 :
              this.hosNature = item.children
              break
          }
        })
      },
      // 过滤医院等级
      getHosLevel(value) {
        let label = ''
        this.hosLevel.forEach(item => {
          if (value && item.code === value) {
            label = '|' + item.label
          }
        })
        return label
      },
      // 过滤医院级别
      getHosGrade(value) {
        let label = ''
        this.hosGrade.forEach(item => {
          if (value && item.code === value) {
            label = '|' + item.label
          }
        })
        return label
      },
      // 过滤医院属性
      getHosAttr(value) {
        let label = ''
        this.hosAttr.forEach(item => {
          if (value && item.code === value) {
            label = '|' + item.label
          }
        })
        return label
      },
      // 过滤医院社保属性
      getHosNature(value) {
        let label = ''
        this.hosNature.forEach(item => {
          if (value && item.code === value) {
            label = '|' + item.label
          }
        })
        return label
      },
      // 过滤规则要素
      ruleEleFilter(data) {
        this.ruleElementList.forEach(item => {
          if (data === item.value) {
            return item.label
          }
        })
      },
      unitofFilter(data) {
        this.unitofList.forEach(item => {
          if (data === item.value) {
            return item.label
          }
        })
      },
      getRuleelement() {
        get('ruleelement').then(res => {
          this.ruleElementList = res.data
        })
      },
      getUnitofelement() {
        get('unitofelement').then(res => {
          this.unitofList = res.data
        })
      },
      getRulesData() {
        this.ruleTableData = []
        this.ruleLoading = true
        const params = {
          insurancecode: this.$route.query.insurancecode,
          pageno: 1,
          pagesize: 100
        }
        const gears = this.dict.label.gears
        getRules(params).then(res => {
          if (res.status === '1') {
            const opList = res.data.list
            opList.forEach(item => {
              let ruleEle = ''
              let uniEle = ''
              // 规则要素
              this.ruleElementList.forEach(ival => {
                if (item.ruleelement === ival.value) {
                  ruleEle = ival.label
                }
              })
              // 要素单位
              this.unitofList.forEach(ival => {
                if (item.unitofelement === ival.value) {
                  uniEle = ival.label
                }
              })
              // 周期单位
              const uni = this.dict.label.cycle_unit[item.unitofperiod]
              // cumulativeperiod + '|' + unitofperiod + '|' + compensationscope + '|' + ruleexplain
              const pushObject = {
                ruleinfo: '',
                validtime: '',
                quotaexplanation: item.ruleexplain,
                dutyruleno: item.dutyruleno,
                quota: '',
                name: ''
              }
              let compensationscope = ''
              pushObject.validtime += item.startdate ? moment(item.startdate).format('YYYY-MM-DD') + '至' : '至'
              pushObject.validtime += item.enddate ? moment(item.enddate).format('YYYY-MM-DD') : ''
              item.rulesConstraintList.forEach((citem, ind) => {
                switch (citem.constrainttype) {
                  case '01':
                    pushObject.quota += citem.value1 === '1' ? '疾病' : '意外';
                    break
                  case '02':
                    pushObject.quota += citem.value1 === '01' ? '|门诊' : '|住院';
                    break
                  case '03':
                    pushObject.quota += citem.value1 === 'Y' ? '|等待期内' : '|等待期外';
                    break
                  case '04':
                    pushObject.quota += citem.value1 === 'Y' ? '|观察期内' : '|观察期外';
                    break
                  case '05':
                    pushObject.quota += '|' + citem.value1 + '-' + citem.value2;
                    break
                  // case '06': pushObject.quota += citem.value1 === '01' ? '|保额|' + item.cumulativeperiod : '|已缴保费|' + item.cumulativeperiod; break
                  case '06':
                    compensationscope = citem.value1 === '01' ? '保额' : '已缴保费';
                    break
                  case '07':
                    pushObject.quota += citem.value1 === 'Y' ? '|已使用医保' : '|未使用医保';
                    break
                  case '08':
                    pushObject.quota += this.getHosAttr(citem.value1);
                    break
                  case '09':
                    pushObject.quota += this.getHosLevel(citem.value1);
                    break
                  case '10':
                    pushObject.quota += this.getHosNature(citem.value1);
                    break
                  case '11':
                    pushObject.quota += '|' + gears[citem.value1];
                    break
                  case '12':
                    pushObject.quota += this.getHosGrade(citem.value1);
                    break
                }
              })
              if (pushObject.quota[0] === '|') {
                pushObject.quota = pushObject.quota.slice(1)
              }
              // pushObject.ruleinfo = ruleEle + '|' + item.elementvalue + '|' + uniEle + '|' + item.cumulativeperiod + '|' + uni + '|' + compensationscope + '|' + item.ruleexplain
              pushObject.ruleinfo = ruleEle + '|' + item.elementvalue + '|' + uniEle
              item.cumulativeperiod ? pushObject.ruleinfo += '|' + item.cumulativeperiod : pushObject.ruleinfo += ''
              uni ? pushObject.ruleinfo += '|' + uni : pushObject.ruleinfo += ''
              compensationscope ? pushObject.ruleinfo += '|' + compensationscope : pushObject.ruleinfo += ''
              // 待修改（拼接规则列表信息）
              item.rulesRelationList.forEach((item, ind) => {
                // if (item.feeitemname) {
                //   pushObject.name ? pushObject.name += '|' + item.feeitemname : pushObject.name += item.feeitemname
                // } else
                if (item.getdutyname) {
                  pushObject.name ? pushObject.name += '|' + item.getdutyname : pushObject.name += item.getdutyname
                } else if (item.dutyname) {
                  pushObject.name ? pushObject.name += '|' + item.dutyname : pushObject.name += item.dutyname
                }
              })
              this.ruleTableData.push(pushObject)
            })
            this.ruleTotalCount = this.ruleTableData.length
          }
          this.ruleLoading = false
        }).catch(res => {
          this.ruleLoading = false
        })
      },
      getInsuranceInfoData() {
        this.dutyLoading = true
        this.resTableData = []
        // 待修改 获取责任信息列表
        getInsuranceInfo(this.$route.query.insurancecode).then(res => {
          this.insuranceInfo = res.data
          res.data.dutyList.forEach((item, index) => {
            item.getDutyList.forEach((val, ind) => {
              const obj = {}
              obj.dutycode = item.dutycode
              obj.dutyname = item.dutyname
              obj.editFlag = false
              for (const key in val) {
                obj[key] = val[key]
              }
              this.resTableData.push(obj)
            })
          })
          this.resTotalCount = this.resTableData.length
          // 获取责任选择、给付责任选择码表 待修改
          res.data.dutyList.forEach((item, ind) => {
            if (this.dutyChooseList.findIndex(val => val.value === item.dutycode) === -1) {
              const pushObject = {label: item.dutyname, value: item.dutycode, id: ind, type: 'duty'}
              pushObject.children = []
              item.getDutyList.forEach((gVal, key) => {
                pushObject.children.push({
                  label: gVal.getdutyname,
                  value: gVal.getdutycode,
                  id: gVal.getdutycode + gVal.dutycode,
                  type: 'getduty',
                  dutycode: gVal.dutycode
                })
              })
              this.dutyChooseList.push(pushObject)
            }
          })
          this.dutyLoading = false
        }).catch(res => {
          this.dutyLoading = false
        })
        // 获取费用项选择列表
        // getAllFeeitem().then(res => {
        //   res.data.forEach((item, ind) => {
        //     const pushObject = { label: item.feeitemname, value: item.feeitemcode, id: ind }
        //     this.feeItemChooseList.push(pushObject)
        //   })
        // })
      },
      // 提交审核
      commitToAudit() {
        const flag = this.resTableData.some(item => {
          return !item.isexempt
        })
        if (this.insuranceInfo.insurancetype !== '1' && flag) {
          this.$confirm('当前险种为非医疗险，请确认是否选择给付责任的豁免信息?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$confirm('是否确认提交审核?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.commitLoading = true
              updateRiskSubmit({insurancecode: this.$route.query.insurancecode}).then(res => {
                if (res.status === '1') {
                  this.$message({
                    message: '提交成功',
                    center: true,
                    showClose: true,
                    type: 'success'
                  })
                  this.$router.go(-1)
                } else if (res.data === '该险种已发起审核，请勿重复提交') {
                  this.$message({message: res.data, type: 'error', center: true, showClose: true})
                } else {
                  this.$message({
                    message: '提交失败',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                }
                this.commitLoading = false
              })
            }).catch(() => {
              return
            })
          }).catch(() => {
            return
          })
        } else {
          this.$confirm('是否确认提交审核?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.commitLoading = true
            updateRiskSubmit({insurancecode: this.$route.query.insurancecode}).then(res => {
              if (res.status === '1') {
                this.$message({
                  message: '提交成功',
                  center: true,
                  showClose: true,
                  type: 'success'
                })
                this.$router.go(-1)
              } else if (res.data === '该险种已发起审核，请勿重复提交') {
                this.$message({message: res.data, type: 'error', center: true, showClose: true})
              } else {
                this.$message({
                  message: '提交失败',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
            this.commitLoading = false
          }).catch(() => {
            return
          })
        }
      },
      // 责任信息编辑
      dutyEditHandle(row) {
        row.editFlag = !row.editFlag
        // this.dutyBeforeHandle = []
        this.dutyBeforeHandle.push(row)
      },
      resSizeChange(val) {
        this.resPageInfo.pageSize = val
      },
      resPageChange(val) {
        this.resPageInfo.page = val
      },
      ruleSizeChange(val) {
        this.rulPageInfo.pageSize = val
      },
      rulePageChange(val) {
        this.rulPageInfo.page = val
      },
      goBack() {
        this.$router.go(-1)
      },
      editHandle(row, index) {
        this.resetForm()
        const params = {
          insurancecode: this.$route.query.insurancecode,
          pageno: this.rulPageInfo.page,
          pagesize: this.rulPageInfo.pageSize
        }
        getRules(params).then(res => {
          const editData = res.data.list[index]
          this.ruleForm.dutyruleno = editData.dutyruleno
          this.ruleForm.ruleelement = editData.ruleelement
          this.ruleForm.elementvalue = editData.elementvalue
          this.ruleForm.ruleexplain = editData.ruleexplain
          this.ruleForm.unitofelement = editData.unitofelement
          this.ruleForm.cumulativeperiod = editData.cumulativeperiod
          this.ruleForm.unitofperiod = editData.unitofperiod
          this.ruleForm.date = [editData.startdate, editData.enddate]
          const setList = []
          this.resetTree()
          editData.rulesConstraintList.forEach(item => {
            this.chooseList.forEach(val => {
              if (item.constrainttype === val.value) {
                this.ruleForm[val.field] = item.value1
                if (val.field === 'age') {
                  this.ruleForm.minage = item.value1
                  this.ruleForm.maxage = item.value2
                }
                // 反写医院信息
                if (val.value === '08' || val.value === '09' || val.value === '10' || val.value === '12') {
                  this.rulTreeData.forEach(hosVal => {
                    hosVal.children.forEach(cVal => {
                      if (cVal.id === val.value + item.value1) {
                        setList.push(cVal)
                      }
                    })
                  })
                }
              }
            })
          })
          // 反写relationList  待修改
          const feeArr = []
          const dutyArr = []
          if (editData.rulesRelationList.length > 1) {
            this.ruleForm.isShare = 'Y'
          }
          editData.rulesRelationList.forEach(item => {
            // if (item.feeitemcode) {
            //   this.feeItemChooseList.forEach(val => {
            //     if (val.value === item.feeitemcode) feeArr.push(val)
            //   })
            // } else
            if (item.getdutycode) {
              this.dutyChooseList.forEach(val => {
                val.children.forEach(nVal => {
                  if (nVal.id === item.getdutycode + item.dutycode) {
                    dutyArr.push(item.getdutycode + item.dutycode)
                  }
                })
              })
            } else if (item.dutycode && !item.getdutycode) {
              this.dutyChooseList.forEach(val => {
                if (val.value === item.dutycode) dutyArr.push(val.id)
              })
            }
          })
          // 反显省市区
          const cityList = []
          this.defaultAddress = []
          editData.rulesAddressList.forEach(item => {
            this.defaultAddress.push(item.province)
            if (item.province === '' && item.city) {
              this.defaultAddress.push(item.city)
            }
            cityList.push(item.city)
          })
          this.$refs.areaTree.setCheckedKeys(cityList)
          // 反显医院
          editData.rulesHospitalList.forEach(item => {
            this.specialHosList.push({label: item.hospitalname, value: item.hospitalno})
          })
          // this.$refs.feeTree.setCheckedNodes(feeArr)
          this.$refs.dutyTree.setCheckedKeys(dutyArr)
          this.$refs.hospitaltree.setCheckedNodes(setList)
        })
      },
      // 全选省市
      handleCheckAllChange() {
        if (this.checkAll) {
          this.$refs.areaTree.setCheckedNodes(this.address)
        } else {
          this.$refs.areaTree.setCheckedKeys([])
        }
      },
      delHandle(row) {
        this.$confirm('确认要删除当前规则?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteRule(row.dutyruleno).then(res => {
            if (res.status === '1') {
              this.$message({
                message: '删除成功',
                type: 'success',
                center: true,
                showClose: true
              })
              this.resetForm()
              this.getRulesData()
            } else {
              this.$message({
                message: '删除失败',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      dutySaveHandle() {
        const outterList = []
        const duty = this.dutyBeforeHandle// 通过编辑获取到的责任信息
        const inspectList = []// 获取到的责任编号的数组
        duty.forEach(item => {
          if (inspectList.findIndex(val => val === item.dutycode) === -1) {
            inspectList.push(item.dutycode)
          }
        })
        inspectList.forEach(item => { // 遍历责任编号数组
          const dutyObject = {
            cumulativemode: '',
            cumulativeperiod: '',
            deductible: '',
            dutycode: '',
            dutyname: '',
            getDutyList: [],
            insurancecode: '',
            paymentratio: '',
            ruleelement: '',
            unitofwaitingperiod: '',
            waitingperiod: ''
          }
          dutyObject.insurancecode = this.$route.query.insurancecode
          duty.forEach(val => { // 遍历获取的编辑后信息
            // eslint-disable-next-line no-unused-vars
            // for (const i in val) {
            if (val.dutycode === item) {
              dutyObject.dutycode = val.dutycode
              dutyObject.dutyname = val.dutyname
              dutyObject.getDutyList.push(val)
            }
            // }
          })
          outterList.push(dutyObject)
        })
        this.dutyParams.dutyVOList = outterList
        this.saveDutyLoading = true
        updateDutyList(this.dutyParams).then(res => {
          if (res.status === '1') {
            this.getInsuranceInfoData()
            this.saveLoading = false
            this.dutyBeforeHandle = []
            this.$message({
              message: '保存成功',
              type: 'success',
              showClose: true,
              center: true
            })
          } else {
            this.saveLoading = false
            this.$message({
              message: '保存失败',
              type: 'error',
              showClose: true,
              center: true
            })
          }
        }).finally(() => {
          this.saveDutyLoading = false
        })
        this.resTableData.forEach(item => {
          item.editFlag = false
        })
      },
      getParams(dutyruleno) {
        const hosattrList = this.$refs.hospitaltree.getCheckedNodes(true)// 医院属性
        const params = {
          dutyruleno: dutyruleno,
          elementvalue: this.ruleForm.elementvalue,
          enddate: this.ruleForm.date ? this.ruleForm.date[1] : '',
          insurancecode: this.insuranceInfo.insurancecode,
          cumulativeperiod: this.ruleForm.cumulativeperiod,
          isShare: this.ruleForm.isShare,
          ruleelement: this.ruleForm.ruleelement,
          ruleexplain: this.ruleForm.ruleexplain,
          rulesConstraintList: [],
          rulesRelationList: [],
          rulesHospitalList: [],
          rulesAddressList: [],
          ruletype: '',
          startdate: this.ruleForm.date ? this.ruleForm.date[0] : '',
          unitofelement: this.ruleForm.unitofelement,
          unitofperiod: this.ruleForm.unitofperiod
        }
        // 医院参数
        // params.rulesHospitalList
        this.chooseList.forEach(item => {
          const constrain = {
            constraintno: '',
            constrainttype: item.value,
            createdate: '',
            creator: '',
            dutyruleno: '',
            managecom: '',
            operatedate: '',
            operator: '',
            value1: '',
            value2: ''
          }
          for (const i in this.ruleForm) {
            if (i === item.field && item.field !== 'age') {
              constrain.value2 = ''
              constrain.value1 = this.ruleForm[i]
            } else if (item.field === 'age') {
              constrain.value1 = this.ruleForm.minage
              constrain.value2 = this.ruleForm.maxage
            }
          }
          if (item.field !== 'level' && item.field !== 'attribute' && item.field !== 'nature' && item.field !== 'grade' && constrain.value1 !== '') {
            params.rulesConstraintList.push(constrain)
          }
          // 处理医院相关选项数据
          hosattrList.forEach(hosItem => {
            const hos = {
              constraintno: '',
              constrainttype: item.value,
              createdate: '',
              creator: '',
              dutyruleno: '',
              managecom: '',
              operatedate: '',
              operator: '',
              value1: '',
              value2: ''
            }
            // 医院待修改
            if (hosItem.attr === item.field) {
              hos.value1 = hosItem.code ? hosItem.code : ''
              hos.constrainttype = item.value
              params.rulesConstraintList.push(hos)
            }
          })
        })
        // 特殊医院
        this.specialHosList.forEach(item => {
          const pushData = {
            hospitalno: item.value,
            hospitalname: item.label
          }
          params.rulesHospitalList.push(pushData)
        })
        // 医院省市 getCheckedNodes
        let provinceChoose = this.$refs.areaTree.getHalfCheckedNodes()
        let cityChoose = this.$refs.areaTree.getCheckedNodes()
        cityChoose.forEach((cItem, ind) => {
          const pushData = {
            province: '',
            city: cItem.value
          }
          provinceChoose.forEach((item, ind) => {
            if (item.children.findIndex(a => a.value === cItem.value) !== -1) {
              pushData.province = item.value
            }
          })
          params.rulesAddressList.push(pushData)
        })
        // 获取三个选择项写入请求参数  待修改
        const dutyChoose = []
        const getDutyChoose = []
        this.$refs.dutyTree.getCheckedNodes().forEach(item => {
          // 码表
          if (item.type === 'duty') {
            dutyChoose.push(item)
          } else if (item.type === 'getduty') {
            getDutyChoose.push(item)
          }
        })
        // const allChoose = { 'dutycode': dutyChoose, 'getdutycode': getDutyChoose, 'feeitemcode': feeChoose }
        const allChoose = {'dutycode': dutyChoose, 'getdutycode': getDutyChoose}
        for (const i in allChoose) {
          if (allChoose[i].length) {
            this.ruletypelist.forEach(iVal => {
              if (i === iVal.code) params.ruletype = iVal.value
            })
          }
          allChoose[i].forEach(item => {
            if (item.value) {
              const relation = {
                createdate: '',
                creator: '',
                dutycode: '',
                dutyruleno: '',
                feeitemcode: '',
                getdutycode: '',
                insurancecode: '',
                managecom: '',
                operatedate: '',
                operator: '',
                relationno: ''
              }
              relation[i] = item.value
              if (i === 'getdutycode') {
                relation.dutycode = item.dutycode
              }
              relation.insurancecode = this.insuranceInfo.insurancecode
              params.rulesRelationList.push(relation)
            }
          })
        }
        return params
      },
      saveHandle() {
        // 待修改
        this.$refs.ruleForm.validate((valid) => {
          if (valid) {
            // const dutyChoose = this.$refs.dutyTree.getCheckedNodes
            const dutyChoose = []
            const getDutyChoose = []
            this.$refs.dutyTree.getCheckedNodes().forEach(item => {
              // 码表
              if (item.type === 'duty') {
                dutyChoose.push(item)
              } else if (item.type === 'getduty') {
                getDutyChoose.push(item)
              }
            })
            // const feeChoose = this.$refs.feeTree.getCheckedNodes(true)
            // if ((dutyChoose.length && getDutyChoose.length || dutyChoose.length && feeChoose.length || feeChoose.length && getDutyChoose.length) && this.ruleForm.isShare === 'Y') {
            if ((dutyChoose.length && getDutyChoose.length) && this.ruleForm.isShare === 'Y') {
              // this.$message({ message: '是否共享选择为是，责任、给付责任、费用项不可跨层级选择', type: 'warning', showClose: true, center: true })
              this.$message({message: '是否共享选择为是，责任、给付责任不可跨层级选择', type: 'warning', showClose: true, center: true})
              // } else if (!dutyChoose.length && !getDutyChoose.length && !feeChoose.length) {
            } else if (!dutyChoose.length && !getDutyChoose.length) {
              // this.$message({ message: '责任、给付责任、费用项不可全部为空', type: 'warning', showClose: true, center: true })
              this.$message({message: '责任、给付责任不可全部为空', type: 'warning', showClose: true, center: true})
            } else {
              this.saveLoading = true
              const params = this.getParams(this.ruleForm.dutyruleno)
              if (params.dutyruleno !== '') {
                updateRule(params).then(res => {
                  if (res.status === '1') {
                    this.$message({message: '保存成功', type: 'success', showClose: true, center: true})
                    this.saveLoading = false
                    this.resetForm()
                    this.getRulesData()
                  } else {
                    this.$message({message: '保存失败', type: 'error', showClose: true, center: true})
                    this.saveLoading = false
                  }
                }).catch(() => {
                  this.saveLoading = false
                })
              } else {
                insertRule(params).then(res => {
                  if (res.status === '1') {
                    this.$message({message: '保存成功', type: 'success', showClose: true, center: true})
                    this.saveLoading = false
                    this.resetForm()
                    this.getRulesData()
                  } else {
                    this.$message({message: '保存失败', type: 'error', showClose: true, center: true})
                    this.saveLoading = false
                  }
                }).catch(() => {
                  this.saveLoading = false
                })
              }
            }
          } else {
            this.$message({
              message: '规则要素、要素值、要素单位、规则描述信息必录，如果累计周期不为空，则单位不可为空',
              type: 'warning',
              showClose: true,
              center: true
            })
          }
        })
      },
      resetForm() {
        this.$refs.ruleForm.resetFields()
        this.ruleForm.dutyruleno = ''
        this.ruleForm.minage = ''
        this.ruleForm.maxage = ''
        this.resetTree()
      },
      resetExemptionForm() {
        this.$refs.exemptionForm.resetFields()
        this.$set(this.exemptionForm, 'exemptruleno', '')
        this.resetExemptionTree()
      },
      resetExemptionTree() {
        this.$refs.dutyTree.setCheckedKeys([])
      },
      resetTree() {
        this.$refs.dutyTree.setCheckedKeys([])
        // this.$refs.feeTree.setCheckedKeys([], true)
        this.$refs.hospitaltree.setCheckedKeys([])
        this.$refs.areaTree.setCheckedKeys([])
        this.specialHosList = []
      },
      renderContent(h, {node, data, store}) {
        return (
          <el-col style='width: 100%'>
          <el-tooltip effect='dark' content={node.label} placement='top'>
          <span class='custom-tree-node'>
          <span>{node.label}</span>
          </span>
          </el-tooltip>
          </el-col>)
      },
      // 查询免责
      getExemptionHandle() {
        this.exemptionTable = []
        const params = {
          insurancecode: this.$route.query.insurancecode,
          pageno: this.exemptionInfo.page,
          pagesize: this.exemptionInfo.pageSize
        }
        this.exemptionLoading = true
        getExemption(params).then(res => {
          this.exemptList = []
          if (res.status === '1') {
            res.data.list.forEach(item => {
              let str = ''
              let exempttypeList = []
              item.itemDOS.forEach((nVal, nInd) => {
                str += nInd === 0 ? nVal.exemptname : '/' + nVal.exemptname
                exempttypeList.push({name: nVal.exemptname, value: nVal.exemptcode})
              })
              let dutycode = []
              let getdutycode = []
              let rulename = ''
              item.exemptionRelationVO.forEach((val, ind) => {
                if (val.getdutycode !== '') {
                  rulename += ind === 0 ? val.getdutyname : '/' + val.getdutyname
                  getdutycode.push(val.getdutycode + val.dutycode)
                } else {
                  rulename += ind === 0 ? val.dutyname : '/' + val.dutyname
                  dutycode.push(ind)
                }
              })
              let eachData = {
                rulename: rulename,
                rulecode: rulename,
                dutycode: dutycode,
                getdutycode: getdutycode,
                exemptruleno: item.exemptruleno,
                exempttype: item.exempttype,
                exemptratio: item.exemptratio,
                isShare: 'Y',
                exemptname: str,
                exemptcode: item.exempttype === '01' ? str : exempttypeList,
                description: item.description
              }
              this.exemptionTable.push(eachData)
            })
          }
        }).finally(res => {
          this.exemptionLoading = false
        })
      },
      addExemptionHandle() {
        // exemptionForm
        this.$refs.exemptionForm.validate((valid) => {
          let params = this.getExemptionParams()
          addExemption(params).then(res => {
            if (res.status === '1') {
              this.$message.success('保存成功！')
              this.resetExemptionForm()
              this.getExemptionHandle()
            } else {
              this.$message.error('保存失败！')
            }
          })
        })
      },
      getExemptionParams() {
        const params = JSON.parse(JSON.stringify(this.exemptionForm))
        params.exemptionRelationVOS = []
        params.itemDOS = []
        if (params.exempttype !== '01') {
          params.exemptname.forEach(item => {
            let data = {
              exemptname: item.name,
              exemptcode: item.value
            }
            params.itemDOS.push(data)
          })
          delete params.exemptname
        } else {
          let data = {
            exemptname: params.exemptname,
            exemptcode: params.exemptname
          }
          params.itemDOS.push(data)
        }
        params.insurancecode = this.$route.query.insurancecode
        params.isShare = 'Y'
        const dutyChoose = []
        const getDutyChoose = []
        this.$refs.dutyTree.getCheckedNodes().forEach(item => {
          // 码表
          if (item.type === 'duty') {
            dutyChoose.push(item)
          } else if (item.type === 'getduty') {
            getDutyChoose.push(item)
          }
        })
        const allChoose = {'dutycode': dutyChoose, 'getdutycode': getDutyChoose}
        for (const i in allChoose) {
          if (allChoose[i].length) {
            this.ruletypelist.forEach(iVal => {
              if (i === iVal.code) params.ruletype = iVal.value
            })
          }
          allChoose[i].forEach(item => {
            if (item.value) {
              const relation = {
                dutycode: '',
                feeitemcode: '',
                getdutycode: '',
                insurancecode: '',
              }
              relation[i] = item.value
              if (i === 'getdutycode') {
                relation.dutycode = item.dutycode
              }
              relation.insurancecode = this.insuranceInfo.insurancecode
              params.exemptionRelationVOS.push(relation)
            }
          })
        }
        return params
      },
      remoteExemptMethod(query) {
        if (query !== '') {
          if (this.exemptionForm.exempttype === '03') {
            getLikeICD10(query).then(res => {
              this.exemptList = res.data
            })
          } else if (this.exemptionForm.exempttype === '02') {
            const params = {
              type: '1',
              subtype: '',
              name: query,
              code: '',
              subcode: ''
            }
            getExemptionInfoByName(params).then(res => {
              this.exemptList = res.data
            })
          }
        }
      },
      exemptionType(row) {
        return this.dict.label.exemption_type[row.exempttype]
      },
      exemptioneditHandle(row) {
        if (row.dutycode.length > 0) {
          this.$refs.dutyTree.setCheckedKeys(row.dutycode)
        } else {
          this.$refs.dutyTree.setCheckedKeys(row.getdutycode, true)
        }
        this.$set(this.exemptionForm, 'rulecode', row.rulecode)
        this.$set(this.exemptionForm, 'exemptruleno', row.exemptruleno)
        this.$set(this.exemptionForm, 'exempttype', row.exempttype)
        this.$set(this.exemptionForm, 'exemptratio', row.exemptratio)
        this.$set(this.exemptionForm, 'description', row.description)
        this.$set(this.exemptionForm, 'exemptname', row.exemptcode)
        this.exemptList = JSON.parse(JSON.stringify(row.exemptcode))
      },
      exemptionDelHandle(row) {
        deleteExemption(row.exemptruleno).then(res => {
          if (res.status === '1') {
            this.$message.success('删除成功！')
            this.getExemptionHandle()
          } else {
            this.$message.success('删除失败！')
          }
        })
      },
      exemptionPageChange(val) {
        this.exemptionInfo.page = val
        this.getExemptionHandle()
      },
      exemptionSizeChange(val) {
        this.exemptionInfo.pageSize = val
        this.getExemptionHandle()
      },
      handleSelectionChange() {

      },
      deleteAll(){

      }
    }
  }
</script>
<style scoped>
  .item-width {
    width: 160px;
  }

  .el-tree /deep/ .el-tree-node__content {
    height: 30px;
  }

  .check_box {
    margin-top: 16px;
  }

  .collapse-card.el-card /deep/ .el-card__body {
    padding: 0;
  }

  .el-collapse /deep/ .el-collapse-item__header {
    background-color: #f8f8f8;
    padding: 0 10px;
  }

  .el-collapse /deep/ .el-collapse-item__wrap {
    padding: 0 10px;
  }

  .el-checkbox {
    margin-left: 20px;
  }

  .el-checkbox-group {
    margin-left: 20px;
  }

  .el-checkbox-group /deep/ .el-checkbox {
    display: block;
  }

  .to_right {
    width: 100px;
    text-align: right;
  }

  /deep/ .custom-tree-node {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }

  /deep/ .custom-tree-node span {
    flex: 1;
    display: inline-block;
    width: 80%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
</style>
<style>
  .el-tooltip__popper {
    max-width: 400px;
  }
</style>
