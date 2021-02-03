<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>产品福利规则</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="submitReview">提交审核</el-button>
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
        <el-divider/>
      </div>
      <div slot="header" class="clearfix">
        <span>产品详情</span>
      </div>
      <el-row v-loading="dutyLoading" style="margin: 0 40px;padding-bottom: 20px">
        <el-col :span="8">
          <span class="info_span to_right">产品编码：</span><span class="info_span">{{ insuranceInfo.riskCode }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品名称：</span><span class="info_span">{{ insuranceInfo.riskName }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品性质：</span><span
          class="info_span">{{ insuranceInfo.riskNature }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品类别：</span><span class="info_span">{{ insuranceInfo.riskType }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品分类：</span><span class="info_span">{{ insuranceInfo.riskClass }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">等待期：</span><span class="info_span" v-if="insuranceInfo.waitingPeriod">{{ insuranceInfo.waitingPeriod +'天'}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">出单公司：</span><span class="info_span">{{ insuranceInfo.company }}</span>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card" style="margin: 20px 0;">
      <div class="tab-title">
        <span>计划信息</span>
      </div>
      <el-table
        v-loading="dutyLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="pagingData"
        size="small"
        highlight-current-row
        @expand-change="getMinData"
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-table :data="scope.row.minData"
                      :header-cell-style="{color:'black',background:'#f8f8ff'}"
                      highlight-current-row
                      size="small"
                      v-loading="minLoading"
                      tooltip-effect="dark"
                      style="width: 100%;">
              <el-table-column align="center" width="140" prop="dutyCode" label="责任编码" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyName" label="责任名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyDetailCode" label="责任明细编码" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyDetailName" label="责任明细名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="applyReason" label="申请原因" show-overflow-tooltip/>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" width="140" prop="planCode" label="保障计划编码" show-overflow-tooltip/>
        <el-table-column align="center" prop="planName" label="保障计划名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="ensureRegion" label="保障区域" show-overflow-tooltip/>
        <el-table-column align="center" prop="planDescription" label="计划描述" show-overflow-tooltip/>
      </el-table>
    </el-card>
    <div style="display: flex">
      <el-row style="width: 100%;">
        <el-col style="width: 28%;margin-right: 1%">
          <el-card class="collapse-card">
            <el-collapse v-model="activeNames">
              <el-collapse-item title="涵盖区域选择" name="2">
                <el-checkbox v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                <el-tree
                  ref="areaTree"
                  :data="address"
                  :props="defaultProps"
                  :default-expanded-keys="defaultAddress"
                  :load="loadNode"
                  @check="areaTreeCheck"
                  style="margin-top: 10px;"
                  lazy
                  show-checkbox
                  node-key="value"/>
              </el-collapse-item>
              <el-collapse-item title="医院属性选择" name="3">
                <el-tree
                  ref="hospitaltree"
                  :data="rulTreeData"
                  :props="defaultProps"
                  :default-expand-all="isExpand"
                  style="margin-top: 10px;"
                  show-checkbox
                  node-key="id"/>
              </el-collapse-item>
              <el-collapse-item title="特殊医院选择" name="4">
                <div>
                  <el-select
                    v-model="specialHos"
                    multiple
                    filterable
                    remote
                    reserve-keyword
                    placeholder="请选择医院"
                    :remote-method="remoteMethod"
                    :loading="specialHosLoading"
                    style="margin-top: 20px;margin-left:5%;width: 68%"
                    size="mini">
                    <el-option
                      v-for="(item, ind) in remoteAddress"
                      :key="ind"
                      :label="item.chname1"
                      :value="{value: item.providerCode, label: item.chname1}">
                    </el-option>
                  </el-select>
                  <el-button type="primary" size="mini"
                             style="width: 56px;margin-top: 20px;float: right;margin-left: 15px" @click="addSpecialHos">
                    添加
                  </el-button>
                </div>
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
                  ref="welfareTree"
                  :data="welfareList"
                  :props="defaultProps"
                  :default-expand-all="isExpand"
                  style="margin-top: 10px;"
                  show-checkbox
                  node-key="value"/>
              </el-collapse-item>
            </el-collapse>
          </el-card>
        </el-col>
        <el-col style="width: 71%">
          <el-tabs v-model="activeName" type="border-card">
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
                    <el-form-item label="区间单位：" prop="intervalUnit">
                      <el-select v-model="ruleForm.intervalUnit" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in intervalUnitOptions" :key="item.dictValue" :label="item.dictLabel"
                                   :value="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="性别：" prop="sex">
                      <el-select v-model="ruleForm.sex" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in sexOptions" :label="item.dictLabel" :value="item.dictValue"
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
                    <el-form-item label="规则要素：" prop="ruleElement" :rules="rules.checkRequired">
                      <el-select v-model="ruleForm.ruleElement" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in ruleelementOptions" :key="item.dictValue" :label="item.dictLabel"
                                   :value="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="要素值：" prop="elementValue" :rules="rules.elementValue">
                      <el-input v-model.number="ruleForm.elementValue" class="item-width" clearable size="mini"
                                placeholder="请输入"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="要素单位：" prop="elementUnit" :rules="rules.checkRequired">
                      <el-select v-model="ruleForm.elementUnit" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in unitofelementOptions" :key="item.dictValue" :label="item.dictLabel"
                                   :value="item.dictValue"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="累计方式：" prop="cumulativeMode">
                      <el-select v-model="ruleForm.cumulativeMode" class="item-width" size="mini" placeholder="请选择"
                                 clearable>
                        <el-option v-for="item in special_cumulativemodeOptions" :label="item.dictLabel"
                                   :value="item.dictValue"
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
                      prop="ruleDescription" :rules="rules.checkRequired">
                      <el-input v-model="ruleForm.ruleDescription" maxlength="500" style="width: 98%;" clearable
                                size="mini"
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
            </el-tab-pane>
          </el-tabs>
          <el-tabs v-model="list1" type="border-card" style="margin-top: 10px">
            <el-tab-pane label="规则列表" name="first">
              <div style="height: 40px;margin: 10px 10px; float: right">
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
                <el-table-column align="center" width="140" label="福利对象" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{getPlan(scope.row,scope.$index)}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" label="规则信息" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{getRuleInfo(scope.row,scope.$index)}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" label="规则条件" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{getConditional(scope.row,scope.$index,'1')}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" label="规则描述" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{scope.row.ruleDescription}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="createBy" label="涵盖区域" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{getConditional(scope.row,scope.$index,'2')}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="createBy" label="医院属性" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{getConditional(scope.row,scope.$index,'3')}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="createBy" label="特殊医院" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{getConditional(scope.row,scope.$index,'4')}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="createBy" label="操作">
                  <template slot-scope="scope">
                    <el-button size="mini" type="text" style="color: #f00;" @click="deleteAll(scope.row)">删除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <!--分页组件-->
              <pagination
                v-show="ruleTotalCount>0"
                :total="ruleTotalCount"
                :page.sync="rulPageInfo.pageNum"
                :limit.sync="rulPageInfo.pageSize"
                @pagination="listRule"
              />
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
  import claimsTable from './components/claimsTable'
  import {
    getProduct,
    listPlan,
    listDetail,
    listRule,
    getWelfare,
    deleteRule,
    addRule,
    submitReview,
    checkSave,
    getListNew
  } from '@/api/insuranceRules/ruleDefin'
  import jwt from 'jsonwebtoken'

  let dictss = [{dictType: 'waiting_period'}, {dictType: 'isShare'}, {dictType: 'sex'}, {dictType: 'ruleelement'},
    {dictType: 'special_cumulativemode'}, {dictType: 'intervalUnit'}, {dictType: 'unitofelement'},]
  import {getAddress} from '@/api/supplierManager/supplier'

  export default {
    components: {
      claimsTable
    },
    filters: {},
    data() {
      const integerRange = (rule, value, callback) => {
        const str = /^[0-9]*$/
        if (this.ruleForm.minage && !str.test(this.ruleForm.minage) || this.ruleForm.maxage && !str.test(this.ruleForm.maxage)) {
          callback(new Error('允许录入不小于0的整数'))
        } else if (parseInt(this.ruleForm.minage) < 0 || parseInt(this.ruleForm.maxage) < 0) {
          callback(new Error('允许录入不小于0的整数'))
        } else if (parseInt(this.ruleForm.minage) >= parseInt(this.ruleForm.maxage)) {
          callback(new Error('第一位应小于第二位'))
        } else if ((this.ruleForm.minage != null && this.ruleForm.minage !== '') && (this.ruleForm.maxage == null || this.ruleForm.maxage === '')) {
          callback(new Error('请输入第二位'))
        } else if ((this.ruleForm.minage == null || this.ruleForm.minage === '') && (this.ruleForm.maxage != null && this.ruleForm.maxage !== '')) {
          callback(new Error('请输入第一位'))
        } else {
          callback()
        }
      }
      const checkIntervalUnit = (rule, value, callback) => {
        if ((this.ruleForm.minage != null && this.ruleForm.minage !== '') || (this.ruleForm.maxage != null && this.ruleForm.maxage !== '')) {
          if (!value) {
            callback(new Error('区间值非空，请录入区间单位'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkRequired = (rule, value, callback) => {
        /*        if ((this.ruleForm.minage != null && this.ruleForm.minage !== '') || (this.ruleForm.maxage != null && this.ruleForm.maxage !== '')
                  || (this.ruleForm.intervalUnit != null && this.ruleForm.intervalUnit !== '') || (this.ruleForm.sex != null && this.ruleForm.sex !== '')
                  || (this.ruleForm.waitingperiod != null && this.ruleForm.waitingperiod !== '')) {

                }*/
        if (!value) {
          callback('设置规则时,不允许为空!')
        } else {
          callback()
        }
      }

      const positiveInteger = (rule, value, callback) => {
        if (!value) {
          if (value!==0){
            callback(new Error('要素值非空，请录入要素值'))
          }else {
            callback()
          }
        } else {
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
      }

      return {
        loading: false,
        minLoading: false,
        activeNames: '',
        resTableData: [],
        ruleTableData: [],
        resTotalCount: 0,
        ruleTotalCount: 0,
        rules: {
          age: [{validator: integerRange, trigger: 'change'}],
          intervalUnit: [{validator: checkIntervalUnit, trigger: 'blur'}],
          checkRequired: [{validator: checkRequired,required: true, trigger: 'blur'}],
          elementValue: [{validator: positiveInteger,required: true, trigger: 'blur'}],
        },

        rulPageInfo: {
          pageNum: 1,
          pageSize: 10
        },
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        rulTreeData: [
          {
            id: 1,
            label: '医院属性',
            children: [{
              id: '081',
              label: '公立',
              attr: 'attribute',
              code: '1'
            }, {
              id: '082',
              label: '私立',
              attr: 'attribute',
              code: '2'
            }]
          },
          {
            id: 2,
            label: '医院级别',
            children: [{
              id: '121',
              label: 'I_一级',
              attr: 'grade',
              code: '1'
            }, {
              id: '122',
              label: 'IA_一级甲等',
              attr: 'grade',
              code: '2'
            }]
          },
          {
            id: 3,
            label: '部门类别',
            children: [{
              id: '091',
              label: '国际部',
              attr: 'department',
              code: '1'
            }, {
              id: '092',
              label: '特殊部',
              attr: 'department',
              code: '2'
            }, {
              id: '0999',
              label: '其他',
              attr: 'department',
              code: '99'
            }]
          }, {
            id: 4,
            label: '医院类别',
            children: [{
              id: '098',
              label: '一级',
              attr: 'category',
              code: '1'
            }, {
              id: '092',
              label: '四类',
              attr: 'category',
              code: '2'
            }, {
              id: '099',
              label: '其他',
              attr: 'category',
              code: '99'
            }]
          }, {
            id: 6,
            label: '网络类',
            children: [{
              id: '056',
              label: '是',
              attr: 'network',
              code: '1'
            }, {
              id: '057',
              label: '否',
              attr: 'network',
              code: '2'
            }]
          },{
            id: 7,
            label: '是否直付',
            children: [{
              id: '058',
              label: '是',
              attr: 'isPay',
              code: '1'
            }, {
              id: '059',
              label: '否',
              attr: 'isPay',
              code: '2'
            }]
          },
        ],
        ruleForm: {
          minage: undefined,
          maxage: undefined,
          intervalUnit: undefined,//区间单位
          sex: undefined,//性别
          waitingperiod: undefined,//等待期内外
          ruleElement: undefined,//规则要素
          elementValue: undefined,//要素值
          elementUnit: undefined,//要素单位
          cumulativeMode: '1',//累计方式
          isShare: 'N',//是否共享
          ruleDescription: undefined,//规则描述
        },
        insuranceInfo: {
          riskCode: undefined,
          riskStatus: undefined,
          riskName: undefined,
          riskNature: undefined,
          riskType: undefined,
          riskClass: undefined,
          companyName: undefined,
          waitingPeriod: undefined,
          company: undefined,
        },
        dutyBeforeHandle: [],
        dutyParams: {},
        ruleElementList: [],
        unitofList: [],
        welfareList: [],//福利对象
        // feeItemChooseList: [],
        ruleLoading: false,
        dutyLoading: false,
        ruletypelist: [
          {code: 'dutycode', value: '02'},
          {code: 'getdutycode', value: '03'},
          // { code: 'feeitemcode', value: '04' }
        ],
        pagingData: [],
        isExpand: true,
        hosLevel: [], // 医院等级
        hosGrade: [], // 医院级别
        hosAttr: [], // 医院属性
        hosNature: [], // 医院社保属性
        saveLoading: false,
        saveDutyLoading: false,
        commitLoading: false,
        address: [{
          'value': '01',
          'label': '亚洲',
          'children': [{
            'value': '02',
            'label': '中国',
            'children': undefined
          }]
        }],
        remoteAddress: [],//特殊医院列表
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
        rulePagingData: [],
        rulePagingDatas: [],
        exemptList: [],
        waiting_periodOptions: [],
        unitofelementOptions: [],
        isShareOptions: [],
        ruleelementOptions: [],
        special_cumulativemodeOptions: [],
        sexOptions: [],
        dictList: [],
        intervalUnitOptions: [],
        rulePagingDatasrulePagingDatas: []
      }
    },
    computed: {},
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.waiting_periodOptions = this.dictList.find(item => {
        return item.dictType === 'waiting_period'
      }).dictDate
      this.isShareOptions = this.dictList.find(item => {
        return item.dictType === 'isShare'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType === 'sex'
      }).dictDate
      this.ruleelementOptions = this.dictList.find(item => {
        return item.dictType === 'ruleelement'
      }).dictDate
      this.special_cumulativemodeOptions = this.dictList.find(item => {
        return item.dictType === 'special_cumulativemode'
      }).dictDate
      this.intervalUnitOptions = this.dictList.find(item => {
        return item.dictType === 'intervalUnit'
      }).dictDate
      this.unitofelementOptions = this.dictList.find(item => {
        return item.dictType === 'unitofelement'
      }).dictDate

      this.getAddress()
      if (this.$route.query.riskCode) {
        this.getProduct()
        this.listPlan()
        this.listRule()
        this.getWelfare()
      }
    }
    ,
    methods: {
      getAddress() {
        getAddress().then(response => {
          this.address[0].children[0].children = response
        }).catch(error => {
          console.log(error)
        })
      }
      ,
      // 查询产品详情
      getProduct() {
        getProduct(this.$route.query.riskCode).then(res => {
          if (res != null && res.code === 200) {
            this.insuranceInfo = res.data
          }
        }).catch(res => {
        })
      }
      ,
      //查询计划信息
      listPlan() {
        let query = {
          riskCode: this.$route.query.riskCode
        }
        listPlan(query).then(res => {
          if (res != null && res.code === 200) {
            this.pagingData = res.rows
            if (this.pagingData.length !== 0) {
              this.pagingData.forEach(item => {
                item.minData = []
              })
            }
          }
        }).catch(res => {
        })
      }
      ,
      //查询福利对象
      getWelfare() {
        getWelfare(this.$route.query.riskCode).then(res => {
          if (res != null && res.code === 200) {
            this.welfareList = res.rows
          }
        }).catch(res => {

        })
      }
      ,
      //查询福利规则
      listRule() {
        let querys = {
          pageNum: this.rulPageInfo.pageNum,
          pageSize: this.rulPageInfo.pageSize,
          riskCode: this.$route.query.riskCode
        }
        listRule(querys).then(res => {
          if (res != null && res.code === 200) {
            this.rulePagingData = res.rows
            this.ruleTotalCount = res.total
          }
        }).catch(res => {
        })
      }
      ,
      // 查询特殊医院
      remoteMethod(query) {

        if (query !== '' && query!=null) {//调用特殊医院查询接口
          let data ={
            chname1:query
          }
          getListNew(data).then(res => {
            this.remoteAddress = res.rows
          })
        }
      }
      ,
      // 添加特殊医院
      addSpecialHos() {
        this.specialHosList = this.specialHosList.concat(this.specialHos)
        this.specialHos = []
        this.remoteAddress = []
      }
      ,
      // 删除特殊医院
      delSpecialHos(ind) {
        this.specialHosList.splice(ind, 1)
      }
      ,
      // 加载省市
      loadNode(node, resolve) {
        // todo 设置末级节点
        if (node.level === 0) {
          return resolve(node.data);
        }
        if (node.level === 1) {
          /*if (node.label === '北京市' || node.label === '天津市' || node.label === '重庆市' || node.label === '上海市'
            || node.label === '香港特别行政区' || node.label === '澳门特别行政区' || node.label === '台湾省') {
            return resolve([]);
          }*/
          return resolve(node.data.children);
        }
        if (node.level === 2) {
          return resolve(node.data.children);
        }
        if (node.level === 3) {
          return resolve(node.data.children);
        }
        if (node.level === 4) {
          return resolve(node.data.children);
        }
        return resolve([])
      }
      ,
      getInsuranceInfoData() {

      }
      ,
      // 全选省市
      handleCheckAllChange() {
        if (this.checkAll) {
          this.$refs.areaTree.setCheckedNodes(this.address)
        } else {
          this.$refs.areaTree.setCheckedKeys([])
        }
      }
      ,
      getParams(dutyruleno) {

      }
      ,
      saveHandle: function () {
        //半选
        const welfareList = this.$refs.welfareTree.getHalfCheckedNodes()// 医院属性
        let num = 0
        welfareList.forEach(item => {
          if (!item.planCode && !item.dutyCode && !item.dutyDetailCode) {
            num++
          }
        })
        //全选
        const welfareList2 = this.getSimpleCheckedNodes(this.$refs.welfareTree.store)
        welfareList2.forEach(item => {
          if (!item.planCode && !item.dutyCode && !item.dutyDetailCode) {
            num++
          }
        })
        if (this.ruleForm.isShare === 'Y' && num >= 2) {//需要判断福利对象是否有多个 不同计划是不能共享的  *先判断是否为共享
          return this.$message.warning(
            "不同计划规则是不能共享的！"
          )
        } else if (welfareList2.length <= 0) {
          return this.$message.warning(
            "最少选择一个计划！"
          )
        }else {
          this.$refs.ruleForm.validate((valid) => {
            if (valid) {
              //条件列表
              let constraintList = []
              //pushConstraint
              //区间条件10
              if (this.ruleForm.minage != null && this.ruleForm.minage !== '') {
                let constraint = this.pushConstraint('10', this.ruleForm.minage, this.ruleForm.maxage, this.ruleForm.intervalUnit)
                constraintList.push(constraint)
              }
              //性别条件11
              if (this.ruleForm.sex != null && this.ruleForm.sex !== '') {
                let constraint = this.pushConstraint('11', this.ruleForm.sex, undefined, undefined)
                constraintList.push(constraint)
              }
              //等待期内/外12
              if (this.ruleForm.waitingperiod != null && this.ruleForm.waitingperiod !== '') {
                let constraint = this.pushConstraint('12', this.ruleForm.waitingperiod, undefined, undefined)
                constraintList.push(constraint)
              }
              //地区00
              let areaList = this.getSimpleCheckedNodes(this.$refs.areaTree.store)
              areaList.forEach(item => {
                let constraint = this.pushConstraint('00', item.value, item.label, undefined)
                constraintList.push(constraint)
              })

              //医院属性
              let hospitalList = this.$refs.hospitaltree.getCheckedNodes(true)
              let networkNum = 0
              let network = undefined
              let isPayNum = 0
              let isPay = undefined
              hospitalList.forEach(item => {
                //医院属性31
                if (item.attr === 'attribute') {
                  let constraint = this.pushConstraint('31', item.code, item.label, undefined)
                  constraintList.push(constraint)
                }
                //医院级别32
                if (item.attr === 'grade') {
                  let constraint = this.pushConstraint('32', item.code, item.label, undefined)
                  constraintList.push(constraint)
                }
                //部门类别33
                if (item.attr === 'department') {
                  let constraint = this.pushConstraint('33', item.code, item.label, undefined)
                  constraintList.push(constraint)
                }
                //医院类别34
                if (item.attr === 'category') {
                  let constraint = this.pushConstraint('34', item.code, item.label, undefined)
                  constraintList.push(constraint)
                }
                //网络内/外35
                if (item.attr === 'network') {
                  networkNum++
                  if (networkNum === 1) {
                    network = this.pushConstraint('35', item.code, item.label, undefined)
                  }
                }
                //是否直付36
                if (item.attr === 'isPay') {
                  isPayNum++
                  if (isPayNum === 1) {
                    isPay = this.pushConstraint('36', item.code, item.label, undefined)
                  }
                }
              })
              if (networkNum === 1) {
                constraintList.push(network)
              }
              if (isPayNum === 1) {
                constraintList.push(isPay)
              }

              //特殊医院30   item.code待定
              this.specialHosList.forEach(item=>{
                let constraint = this.pushConstraint('30', item.value, item.label, undefined)
                constraintList.push(constraint)
              })
              //关联对象列表
              let relationList = [] //pushRelation
              let relations = this.getSimpleCheckedNodes(this.$refs.welfareTree.store)
              relations.forEach(item => {
                //费用项
                if (item.planCode && item.dutyCode && item.dutyDetailCode) {
                  let relation = this.pushRelation(item.planCode, item.dutyCode, item.dutyDetailCode, item.value)
                  relationList.push(relation)
                }
                //责任明细
                if (item.planCode && item.dutyCode && !item.dutyDetailCode) {
                  let relation = this.pushRelation(item.planCode, item.dutyCode, item.value, undefined)
                  relationList.push(relation)
                }
                //责任明细
                if (item.planCode && !item.dutyCode && !item.dutyDetailCode) {
                  let relation = this.pushRelation(item.planCode, item.value, undefined, undefined)
                  relationList.push(relation)
                }
                //计划
                if (!item.planCode && !item.dutyCode && !item.dutyDetailCode) {
                  let relation = this.pushRelation(item.value, undefined, undefined, undefined)
                  relationList.push(relation)
                }
              })
              //福利规则新增
              let rule = {
                riskCode: this.insuranceInfo.riskCode,
                ruleElement: this.ruleForm.ruleElement,//规则要素
                elementValue: this.ruleForm.elementValue,//要素值
                elementUnit: this.ruleForm.elementUnit,//要素单位
                cumulativeMode: this.ruleForm.cumulativeMode,//累计方式
                isShare: this.ruleForm.isShare,//是否共享
                ruleDescription: this.ruleForm.ruleDescription,//规则描述
                constraintList: constraintList,//条件列表
                relationInfoList: relationList,//关联对象列表
              }
              addRule(rule).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    showClose: true,
                    center: true
                  })
                  this.listRule()
                }
              }).catch(res => {
                return this.$message.warning(
                  "保存失败！"
                )
              })
            }
          })
        }

      }
      ,
      resetForm() {
        this.$refs.ruleForm.resetFields()
        this.ruleForm.minage = ''
        this.ruleForm.maxage = ''
        this.ruleForm.cumulativeMode = ''
        this.ruleForm.isShare = ''
        this.checkAll=false
        this.resetTree()
      }
      ,
      resetTree() {
        this.$nextTick(() => {
          this.$refs.welfareTree.setCheckedKeys([])
          this.$refs.areaTree.setCheckedKeys([])
          this.$refs.hospitaltree.setCheckedKeys([])
        });
        this.welfareList.forEach(item=>{
          let node = this.$refs.welfareTree.getNode(item.value).parent;
          node.childNodes.forEach((item) => {
            node.checked = false;
            if (item.childNodes.length > 0) {
              item.childNodes.forEach((element) => {
                element.checked=false
                if (element.childNodes.length > 0) {
                  element.childNodes.forEach((element) => {
                    element.checked=false
                    if (element.childNodes.length > 0) {
                      element.childNodes.forEach((element) => {
                        element.checked=false
                      })
                    }
                  })
                }
              })
            }
          })
        })
        this.rulTreeData.forEach(item=>{
          let node = this.$refs.hospitaltree.getNode(item.id).parent;
          node.childNodes.forEach((item) => {
            node.checked = false;
            if (item.childNodes.length > 0) {
              item.childNodes.forEach((element) => {
                element.checked = false;
              })
            }
          })
        })

        this.specialHosList = []
      }
      ,
      /*      renderContent(h, {node, data, store}) {
              return (
                <el-col style='width: 100%'>
                <el-tooltip effect='dark' content={node.label} placement='top'>
                <span class='custom-tree-node'>
                <span>{node.label}</span>
                </span>
                </el-tooltip>
                </el-col>)
            },*/

      handleSelectionChange(val) {
        this.rulePagingDatasrulePagingDatas = val
      }
      ,
      deleteAll(row) {
        let ruleNos = []
        if (this.rulePagingDatasrulePagingDatas.length > 0) {
          for (let i = 0; i < this.rulePagingDatasrulePagingDatas.length; i++) {
            ruleNos.push(this.rulePagingDatasrulePagingDatas[i].ruleNo)
          }
        } else if (row.ruleNo) {
          ruleNos.push(row.ruleNo)
        }
        if (ruleNos.length > 0) {
          this.$confirm('确认要删除当前规则?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            deleteRule(ruleNos).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '删除成功',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                //调用查询方法
                this.listRule()
              }
            }).catch(res => {
              this.$message({
                message: '删除失败',
                type: 'error',
                center: true,
                showClose: true
              })
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          })
        } else {
          return this.$message.warning(
            "请选择要删除的数据！"
          )
        }

      },
      submitReview() {
        checkSave(this.insuranceInfo.riskCode).then(res=>{
          if (res.data===1){//通过
            let data = {
              riskCode: this.insuranceInfo.riskCode,
              riskStatus: '03'
            }
            submitReview(data).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '提交成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.$router.go(-1)
              }
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '提交失败！'
              })
            })
          }else if(res.data===0){//不通过
            return this.$message.warning(
              "每个计划都应包含一条只有涵盖区域的且规则要素为保障区域100%的记录！"
            )
          }
        }).catch(res=>{

        })
      }
      ,
      getSimpleCheckedNodes(data) {

        const checkedNodes = [];
        const traverse = function (node) {
          const childNodes = node.root ? node.root.childNodes : node.childNodes;
          childNodes.forEach(child => {
            if (child.checked) {
              checkedNodes.push(child.data);
            }
            if (child.indeterminate) {
              traverse(child);
            }
          });
        };
        traverse(data)
        return checkedNodes;
      }
      ,
      goBack() {
        this.$router.go(-1)
      },
      getMinData(row, expandedRows) {
        this.minLoading = true
        //判断只有展开是做请求
        if (expandedRows.length > 0) {
          let data = {
            planCode: row.planCode
          }
          listDetail(data).then(res => {
            if (res != null && res.code === 200) {
              this.pagingData.forEach((temp, index) => {
                if (temp.planCode === row.planCode) {
                  this.pagingData[index].minData = res.rows
                }
              })
            }
          }).finally(() => {
            this.minLoading = false
          })
        }
      }
      ,

      pushConstraint(constraintType, value1, value2, constraintUnit) {
        //单个条件
        let constraint = {
          constraintType: constraintType,//条件类型
          value1: value1,//条件值1
          value2: value2,//条件值2
          constraintUnit: constraintUnit,//条件单位
        }
        return constraint
      }
      ,
      pushRelation(planCode, dutyCode, dutyDetailCode, feeitemCode) {
        //单个条件
        let relation = {
          planCode: planCode,//计划编码
          dutyCode: dutyCode,//责任编码
          dutyDetailCode: dutyDetailCode,//责任明细编码
          feeitemCode: feeitemCode,//费用项编码
        }
        return relation
      }
      ,
      getPlan(row, index) {
        if (row.relationInfoList.length > 0) {
          let data = row.relationInfoList[0]
          let plan=''
          row.relationInfoList.forEach(data=>{
            if (plan!==''){
              plan = plan + '|'
            }
            if ((data.feeitemName == null || data.planName === '') && (data.dutyDetailName !== null && data.dutyDetailName !== '')) {
              plan=plan+ data.planName + '>' + data.dutyName + ':' + data.dutyDetailName
            } else if ((data.dutyDetailName == null || data.dutyDetailName === '') && (data.dutyName !== null && data.dutyName !== '')) {
              plan= plan+ data.planName + '>' + data.dutyName
            } else if ((data.dutyName == null || data.dutyName === '') && (data.planName !== null && data.planName !== '')) {
              plan= plan+ data.planName
            } else {
              plan= plan+ data.planName + '>' + data.dutyName + ':' + data.dutyDetailName + '>' + data.feeitemName
            }
          })
          return plan

        }
      }
      ,
      getRuleInfo(row, index) {
        return this.selectDictLabel(this.ruleelementOptions, row.ruleElement) +'|'+ row.elementValue +'|'+ this.selectDictLabel(this.unitofelementOptions, row.elementUnit)
      }
      ,
      getConditional(row, index, status) {
        let data = row.constraintList
        let constraint = ''
        let area = ''
        let hospital = ''
        let hospitalProperty = ''
        let hospitalGrade = ''
        let departmentClass = ''
        let hospitalClass = ''
        let network = ''
        let isPay=''
        let specialHospitals=''
        data.forEach(item => {
          //规则条件
          if (item.constraintType === '10' || item.constraintType === '11' || item.constraintType === '12') {
            if (item.constraintType === '10') {
              constraint = item.value1 + '-' + item.value2 + this.selectDictLabel(this.intervalUnitOptions, item.constraintUnit)
            }
            if (item.constraintType === '11') {
              if (constraint !== '') {
                constraint = constraint + '|' + this.selectDictLabel(this.sexOptions, item.value1)
              } else {
                constraint = constraint + this.selectDictLabel(this.sexOptions, item.value1)
              }
            }
            if (item.constraintType === '12') {
              if (constraint !== '') {
                constraint = constraint + '|' + this.selectDictLabel(this.waiting_periodOptions, item.value1)
              } else {
                constraint = constraint + this.selectDictLabel(this.waiting_periodOptions, item.value1)
              }
            }
          }
          //涵盖区域
          if (item.constraintType === '00') {
            if (area === '') {
              area = item.value2
            } else {
              area = area + '|' + item.value2
            }
          }
          //医院属性
          if (item.constraintType === '31' || item.constraintType === '32' || item.constraintType === '33' || item.constraintType === '34' || item.constraintType === '35'|| item.constraintType === '36') {
            if (item.constraintType === '31') {
              if (hospitalProperty === '') {
                hospitalProperty = '医院属性：' + item.value2
              } else {
                hospitalProperty = hospitalProperty + ',' + item.value2
              }
            }
            if (item.constraintType === '32') {
              if (hospitalGrade === '') {
                hospitalGrade = '医院级别：' + item.value2
              } else {
                hospitalGrade = hospitalGrade + ',' + item.value2
              }
            }
            if (item.constraintType === '33') {
              if (departmentClass === '') {
                departmentClass = '部门类别：' + item.value2
              } else {
                departmentClass = departmentClass + ',' + item.value2
              }
            }
            if (item.constraintType === '34') {
              if (hospitalClass === '') {
                hospitalClass = '医院类别：' + item.value2
              } else {
                hospitalClass = hospitalClass + ',' + item.value2
              }
            }
            if (item.constraintType === '35') {
              if (network === '') {
                network = '网络内：' + item.value2
              } else {
                network = network + ',' + item.value2
              }
            }
            if (item.constraintType === '36') {
              if (isPay === '') {
                isPay = '是否直结：' + item.value2
              } else {
                isPay = isPay + ',' + item.value2
              }
            }
          }
          //特殊医院
          if (item.constraintType === '30'){
            if (specialHospitals!==''){
              specialHospitals = specialHospitals + '|' + item.value2
            }else {
              specialHospitals = item.value2
            }
          }
        })

        if (hospitalProperty !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + hospitalProperty
          } else {
            hospital = hospitalProperty
          }

        }
        if (hospitalGrade !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + hospitalGrade
          } else {
            hospital = hospitalGrade
          }

        }
        if (departmentClass !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + departmentClass
          } else {
            hospital = departmentClass
          }
        }
        if (hospitalClass !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + hospitalClass
          } else {
            hospital = hospitalClass
          }
        }
        if (network !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + network
          } else {
            hospital = network
          }
        }
        if (isPay !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + isPay
          } else {
            hospital = isPay
          }
        }


        if (status === '1') {
          return constraint
        } else if (status === '2') {
          return area
        } else if (status === '3') {
          return hospital
        } else if (status === '4') {
          return specialHospitals //特殊医院
        }
      },
      areaTreeCheck(){
        const areaTreeList = this.$refs.areaTree.getHalfCheckedNodes()
        const areaTreeList2 = this.$refs.areaTree.getCheckedNodes()
        console.log(areaTreeList);
        if(areaTreeList.length<=0 && areaTreeList2.length<=0){
          this.checkAll=false
        }
        let checks=0
        this.address.forEach(item=>{
          let node = this.$refs.areaTree.getNode(item.value);
          console.log(node);
          if (node.checked===false){
            checks++
          }
        })

        if (areaTreeList.length<=0 && checks===0){
          this.checkAll=true
        }
      }

    }
  }
</script>
<style scoped>
  .item-width {
    width: 160px;
  }

  .el-tree ::v-deep .el-tree-node__content {
    height: 30px;
  }

  .check_box {
    margin-top: 16px;
  }

  .collapse-card.el-card ::v-deep .el-card__body {
    padding: 0;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    background-color: #f8f8f8;
    padding: 0 10px;
  }

  .el-collapse ::v-deep .el-collapse-item__wrap {
    padding: 0 10px;
  }

  .el-checkbox {
    margin-left: 20px;
  }

  .el-checkbox-group {
    margin-left: 20px;
  }

  .el-checkbox-group ::v-deep .el-checkbox {
    display: block;
  }

  .to_right {
    width: 100px;
    text-align: right;
  }

  ::v-deep .custom-tree-node {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }

  ::v-deep .custom-tree-node span {
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

  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
</style>
