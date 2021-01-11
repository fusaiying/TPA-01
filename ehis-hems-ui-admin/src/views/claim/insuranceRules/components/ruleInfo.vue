<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>产品福利规则</span>
        <span style="float: right;">
          <!--          <el-button v-if="node === 'ruleReview'" type="primary" size="mini" @click="reviewHandle">提交</el-button>-->
          <el-button v-if="node === 'maintain'" :loading="resetLoading" :disabled="resetFlag" type="primary" size="mini"
                     @click="ruleReset">规则重定</el-button>
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <div style="margin-top: 0px">
        <span style="size: 2px">产品详情</span>
      </div>
      <el-divider/>
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
      <div class="tab-title">
        <span>计划信息</span>
        <span
          v-if="$route.query.node !== 'show' && $route.query.node !== 'maintain' && $route.query.node !== 'ruleReview'"
          style="float: right;">
          <el-button type="primary" size="mini" @click="saveHandle">保存</el-button>
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
            <span>{{ scope.row.isexempt === 'Y' ? '是' : scope.row.isexempt === 'N' ? '否' : '' }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="type" label="操作" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button
              :disabled="$route.query.node === 'show' || $route.query.node === 'maintain' || $route.query.node === 'ruleReview'"
              type="text" size="small" @click="editHandle(scope.row)">编辑
            </el-button>
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
    <el-card class="card" style="margin-top: 10px;">
      <div style="margin-top: 0px">
        <span style="size: 2px">福利规则</span>
      </div>
      <el-divider/>
      <el-table
        v-loading="ruleLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="ruleTableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-row>
              <el-col v-if="props.row.eventtype" :span="8">
                <span class="expand_span to_right">出险类型：</span><span class="expand_span">{{ getRiskType(props.row.eventtype) }}</span>
              </el-col>
              <el-col v-if="props.row.visittype" :span="8">
                <span class="expand_span to_right">发票类型：</span><span class="expand_span">{{ getVisittype(props.row.visittype) }}</span>
              </el-col>
              <el-col v-if="props.row.waitingperiod" :span="8">
                <span class="expand_span to_right">等待期：</span><span class="expand_span">{{ getWaitingPeriod(props.row.waitingperiod) }}</span>
              </el-col>
              <el-col v-if="props.row.observationperiod" :span="8">
                <span class="expand_span to_right">观察期：</span><span class="expand_span">{{ getObservation(props.row.observationperiod) }}</span>
              </el-col>
              <el-col v-if="props.row.age" :span="8">
                <span class="expand_span to_right">年龄区间：</span><span class="expand_span">{{ props.row.age }}</span>
              </el-col>
              <el-col v-if="props.row.useofinsurance" :span="8">
                <span class="expand_span to_right">医保使用：</span><span class="expand_span">{{ getUseOfMedicalInsurance(props.row.useofinsurance) }}</span>
              </el-col>
              <el-col v-if="props.row.level" :span="8">
                <span class="expand_span to_right">医院等级：</span><span class="expand_span">{{ props.row.level }}</span>
              </el-col>
              <el-col v-if="props.row.attribute" :span="8">
                <span class="expand_span to_right">医院性质：</span><span
                class="expand_span">{{ props.row.attribute }}</span>
              </el-col>
              <el-col v-if="props.row.nature" :span="8">
                <span class="expand_span to_right">医院社保性质：</span><span class="expand_span">{{ props.row.nature }}</span>
              </el-col>
              <el-col v-if="props.row.gears" :span="8">
                <span class="expand_span to_right">档位：</span><span
                class="expand_span">{{ getGears(props.row.gears) }}</span>
              </el-col>
              <el-col v-if="props.row.isShare" :span="8">
                <span class="expand_span to_right">是否共享：</span><span class="expand_span">{{ props.row.isShare }}</span>
              </el-col>
              <el-col v-if="props.row.grade" :span="8">
                <span class="expand_span to_right">医院级别：</span><span class="expand_span">{{ props.row.grade}}</span>
              </el-col>
              <el-col v-if="props.row.cumulativeperiod" :span="8">
                <span class="expand_span to_right">累计周期：</span><span
                class="expand_span">{{ props.row.cumulativeperiod}}</span>
              </el-col>
              <el-col v-if="props.row.compensationscope" :span="8">
                <span class="expand_span to_right">赔付范围：</span><span class="expand_span">{{ props.row.compensationscope}}</span>
              </el-col>
              <el-col v-if="props.row.address" :span="8" style="display: flex">
                <span class="expand_span to_right">医院省市：</span>
                <el-tooltip class="item" effect="dark" :content="props.row.address" placement="top-start">
                  <span class="expand_span"
                        style="display:inline-block;flex:1;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">{{ props.row.address}}</span>
                </el-tooltip>
              </el-col>
              <el-col v-if="props.row.hospitalname" :span="8" style="display: flex">
                <span class="expand_span to_right">特殊医院：</span>
                <el-tooltip class="item" effect="dark" :content="props.row.hospitalname" placement="top-start">
                  <span class="expand_span"
                        style="display:inline-block;flex:1;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">{{ props.row.hospitalname}}</span>
                </el-tooltip>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="ruleobjectname" label="对象名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="ruleinfo" label="规则信息" show-overflow-tooltip/>
        <el-table-column align="center" prop="ruleexplain" label="规则描述" show-overflow-tooltip/>
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
    </el-card>
    <el-card v-if="node === 'ruleReview'" class="box-card" style="margin-top: 10px;">
      <div style="margin-top: 0px">
        <span style="size: 2px">审核结论</span>
        <span style="float: right;">
       <el-button type="primary" size="mini"
                  @click="reviewHandle">审核完毕</el-button>
        </span>
      </div>
      <el-divider/>
      <el-form ref="reviewForm" :model="reviewForm" label-width="100px" class="demo-ruleForm" size="mini">
        <el-form-item
          :rules="{ required: true, message: '审核结论不能为空',trigger: 'change'}"
          label="审核结论"
          prop="conclusion">
          <el-select v-model="reviewForm.conclusion" placeholder="请选择审核结论">
            <el-option label="通过" value="1"/>
            <el-option label="退回" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item
          label="结论说明"
          prop="explanation">
          <span>
            <el-input v-model="reviewForm.explanation" maxlength="1000" class="small-input" show-word-limit
                      type="textarea"/>
          </span>
        </el-form-item>
      </el-form>
    </el-card>
    <el-dialog
      :visible.sync="dialogVisible"
      :modal="modal"
      title="提示"
      width="30%">
      <el-form ref="reForm" :rules="reFormRules" :model="reForm" label-width="100px" class="demo-ruleForm" size="mini"
               @submit.native.prevent>
        <el-form-item
          label="重定说明"
          prop="explanation">
          <el-input
            v-model="reForm.explanation"
            placeholder="请输入重定原因"
            clearable
            maxlength="100"
            show-word-limit
            @change="changeExplanation"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button :loading="resetLoading" type="primary" @click="confirmHandle">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  let that
  import {
    getInsuranceInfo,
    getRules,
    updateDutyList,
    updatePublicRiskConclusion,
    updatePublicRiskReturn,
    getExemption
  } from '@/api/insuranceRules/ruleDefinition.js/'
  import moment from 'moment'
  import {get} from "@/api/dictDetail";
  import getAddressByFront from '../../claimsHandle/common/mixins/address.js'

  export default {
    mixins: [getAddressByFront],
    dicts: ['insurance_status', 'mainflag', 'insurance_category', 'ins_insurancetype', 'observation', 'waiting_period', 'incidenttype', 'cycle_unit', 'unitofelement', 'exemption_type',
      'invoicetype', 'risk_type', 'useOfMedicalInsurance', 'visit_type', 'hospital_nature', 'hospital_level', 'hospital_attribute', 'gears', 'hospital_grade'],
    filters: {
      changeDate: function (value) {
        return moment(value).format('YYYY-MM-DD')
      },
    },
    data() {
      const checkExplanation = (rules, value, callback) => {
        if (!value) {
          callback(new Error('重定结论不可为空'))
        } else {
          if (value.length > 100) {
            callback(new Error('字数限制为100'))
          } else {
            callback()
          }
        }
      }
      return {
        insuranceInfo: [],
        resTableData: [],
        ruleTableData: [],
        resTotalCount: 0,
        ruleTotalCount: 0,
        ruleLoading: false,
        resPageInfo: {
          page: 1,
          pageSize: 10
        },
        rulPageInfo: {
          page: 1,
          pageSize: 10
        },
        reviewForm: {},
        node: '',
        dutyParams: {},
        dutyBeforeHandle: [],
        dialogVisible: false,
        modal: false,
        reForm: {
          explanation: ''
        },
        dutyLoading: false,
        reFormRules: {
          explanation: [{validator: checkExplanation, trigger: 'change'}]
        },
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
        resetFlag: false,
        natures: [],
        levels: [],
        attrs: [],
        grades: [],
        reviewLoading: false,
        resetLoading: false,
        compensationscope: '',
        address: [],
        activeName: 'first',
        exemptionLoading: false,
        exemptionTable: [],
        exemptionCount: 0,
        exemptionInfo: {
          page: 1,
          pageSize: 10
        },
        insurance_statusOptions: [],
        mainflagOptions: [],
        insurance_categoryOptions: [],
        ins_insurancetypeOptions: [],
        observationOptions: [],
        waiting_periodOptions: [],
        incidenttypeOptions: [],
        cycle_unitOptions: [],
        unitofelementOptions: [],
        exemption_typeOptions: [],
        invoicetypeOptions: [],
        risk_typeOptions: [],
        useOfMedicalInsuranceOptions: [],
        visit_typeOptions: [],
        hospital_natureOptions: [],
        hospital_levelOptions: [],
        hospital_attributeOptions: [],
        gearsOptions: [],
        hospital_gradeOptions: [],
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
      if (this.$route.query) {
        this.node = this.$route.query.node
      }
      this.getDictHandle()
      this.address = this.getAddressByFront()
      this.getRuleelement()
      this.getUnitofelement()
      // this.getInsuranceInfoData()
      this.getDicts("insurance_status").then(response => {
        this.insurance_statusOptions = response.data;
      });
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
      this.getDicts("unitofelement").then(response => {
        this.unitofelementOptions = response.data;
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
      this.getDicts("invoicetype").then(response => {
        this.invoicetypeOptions = response.data;//
      });
      this.getDicts("risk_type").then(response => {
        this.risk_typeOptions = response.data;//
      });
      this.getDicts("hospital_nature").then(response => {
        this.hospital_natureOptions = response.data;//
      });
      this.getDicts("hospital_level").then(response => {
        this.hospital_levelOptions = response.data;//
      });
      this.getDicts("hospital_attribute").then(response => {
        this.hospital_attributeOptions = response.data;//
      });
      this.getDicts("hospital_grade").then(response => {
        this.hospital_gradeOptions = response.data;//
      });
    },
    methods: {
      exemptionType(row) {
        //return this.dict.label.exemption_type[row.exempttype]
        return this.selectDictLabel(this.exemption_typeOptions, row.exempttype);
      },
      getRuleelement() {
        get('ruleelement').then(res => {
          this.ruleElementList = res.content
        })
      },
      getUnitofelement() {
        get('unitofelement').then(res => {
          this.unitofList = res.content
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
      // 查询医院相关码表
      getDictHandle() {
        let natures = this.getNatures()
        let levels = this.getLevels()
        let attrs = this.getAttrs()
        let grades = this.getGrades()
        let compensationscope = this.getCompensationScope()
        let all = Promise.all([natures, levels, attrs, grades, compensationscope]).then(res => {
          this.natures = this.fixHos(res[0])
          this.levels = this.fixHos(res[1])
          this.attrs = this.fixHos(res[2])
          this.grades = this.fixHos(res[3])
          this.compensationscope = this.fixHos(res[4])
          this.getInsuranceInfoData()
          this.getRules()
        })
      },
      // 处理医院码表数据
      fixHos(data) {
        let map = new Map()
        data.forEach(item => {
          map.set(item.value, item.label)
        })
        return map
      },
      getNatures() {
        return new Promise(function (resolve, reject) {
          get('hospital_nature').then(response => {
            resolve(response.content)
          })
        })
      },
      getLevels() {
        return new Promise(function (resolve, reject) {
          get('hospital_level').then(response => {
            resolve(response.content)
          })
        })
      },
      getAttrs() {
        return new Promise(function (resolve, reject) {
          get('hospital_attribute').then(response => {
            resolve(response.content)
          })
        })
      },
      getGrades() {
        return new Promise(function (resolve, reject) {
          get('hospital_grade').then(response => {
            resolve(response.content)
          })
        })
      },
      getCompensationScope() {
        return new Promise(function (resolve, reject) {
          get('compensation_scope').then(response => {
            resolve(response.content)
          })
        })
      },
      changeExplanation() {
        this.reForm.explanation = this.reForm.explanation.replace(/\ +/g, '')
        this.reForm.explanation = this.reForm.explanation.replace(/[\r\n]/g, '')
      },
      getInsuranceInfoData() {
        this.dutyLoading = true
        this.ruleLoading = true
        this.resTableData = []
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
            this.resTotalCount = item.getDutyList.length
          })
          this.dutyLoading = false
        }).catch(res => {
          this.dutyLoading = false
        })
        // this.getRules()
      },
      getRules() {
        const params = {
          insurancecode: this.$route.query.insurancecode,
          pageno: this.rulPageInfo.page,
          pagesize: this.rulPageInfo.pageSize
        }
        getRules(params).then(res => {
          this.ruleTableData = []
          const _list = res.data.list
          this.ruleTotalCount = res.data.total
          if (!_list) {
            this.ruleLoading = false
            return
          }
          _list.forEach((item, index) => {
            const outter = {}
            outter.cumulativeperiod = item.cumulativeperiod ? item.cumulativeperiod : ''
            if (outter.cumulativeperiod) {
              //item.unitofperiod ? outter.cumulativeperiod += '|' + this.dict.label.cycle_unit[item.unitofperiod] : outter.cumulativeperiod += ''
              item.unitofperiod ? outter.cumulativeperiod += '|' + this.selectDictLabel(this.cycle_unitOptions, item.unitofperiod) : outter.cumulativeperiod += ''
            }
            item.rulesRelationList.forEach(val => {
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
              const ruleinfo = ruleEle + '|' + item.elementvalue + uniEle
              outter.ruleinfo = ruleinfo
              let ruleObjectName = ''
              // 名称
              if (val.feeitemname) {
                ruleObjectName ? ruleObjectName += '|' + val.feeitemname : ruleObjectName += val.feeitemname
              } else if (val.getdutyname) {
                ruleObjectName ? ruleObjectName += '|' + val.getdutyname : ruleObjectName += val.getdutyname
              } else if (val.dutyname) {
                ruleObjectName ? ruleObjectName += '|' + val.dutyname : ruleObjectName += val.dutyname
              }
              outter.ruleobjectname ? outter.ruleobjectname += '|' + ruleObjectName : outter.ruleobjectname = ruleObjectName
            })
            // 这里将某几种类型的数据做一个拼接
            item.rulesConstraintList.forEach(val => {
              this.chooseList.forEach(cho => {
                if (val.constrainttype === cho.value) {
                  if (cho.value === '08' && val.constrainttype === '08') {
                    if (outter[cho.field]) {
                      outter[cho.field].includes(this.attrs.get(val.value1)) ? outter[cho.field] += '' : outter[cho.field] += '|' + this.attrs.get(val.value1)
                    } else {
                      outter[cho.field] = this.attrs.get(val.value1)
                    }
                  } else if (cho.value === '09' && val.constrainttype === '09') {
                    if (outter[cho.field]) {
                      outter[cho.field].includes(this.levels.get(val.value1)) ? outter[cho.field] += '' : outter[cho.field] += '|' + this.levels.get(val.value1)
                    } else {
                      outter[cho.field] = this.levels.get(val.value1)
                    }
                  } else if (cho.value === '10' && val.constrainttype === '10') {
                    if (outter[cho.field]) {
                      outter[cho.field].includes(this.natures.get(val.value1)) ? outter[cho.field] += '' : outter[cho.field] += '|' + this.natures.get(val.value1)
                    } else {
                      outter[cho.field] = this.natures.get(val.value1)
                    }
                  } else if (cho.value === '12' && val.constrainttype === '12') {
                    if (outter[cho.field]) {
                      outter[cho.field].includes(this.grades.get(val.value1)) ? outter[cho.field] += '' : outter[cho.field] += '|' + this.grades.get(val.value1)
                    } else {
                      outter[cho.field] = this.grades.get(val.value1)
                    }
                  } else if (cho.value === '05' && val.constrainttype === '05') {
                    outter[cho.field] = val.value1 + '-' + val.value2
                  } else if (cho.value === '06' && val.constrainttype === '06') {
                    outter[cho.field] = val.value1 ? this.compensationscope.get(val.value1) : ''
                  } else {
                    outter[cho.field] = val.value1
                  }
                }
              })
            })
            // 特殊医院
            let hosStr = ''
            item.rulesHospitalList.forEach((nitem, ind) => {
              if (ind === 0) {
                hosStr += nitem.hospitalname
              } else {
                hosStr += '|' + nitem.hospitalname
              }
            })
            outter.hospitalname = hosStr
            // 医院省市
            let address = ''
            item.rulesAddressList.forEach((nitem, ind) => {
              if (ind === 0) {
                address += this.getAddress(nitem.province) + this.getAddress(nitem.city)
              } else {
                address += '|' + this.getAddress(nitem.province) + this.getAddress(nitem.city)
              }
            })
            // 是否共享
            if (item.rulesRelationList.length > 1) {
              outter.isShare = '是'
            } else {
              outter.isShare = '否'
            }
            outter.address = address
            outter.ruleexplain = item.ruleexplain
            outter.ruleelement = item.ruleelement
            this.ruleTableData.push(outter)
          })
          this.ruleLoading = false
        }).catch(res => {
          this.ruleLoading = false
        })
      },
      // 获取医院省市
      getAddress(data) {
        let address = ''
        let index = this.address.findIndex(item => item.value === data)
        if (index !== -1) {
          return address = this.address[index].label
        } else {
          this.address.forEach(item => {
            let nindex = item.children.findIndex(val => val.value === data)
            if (nindex !== -1) return address = item.children[nindex].label
          })
        }
        return address
      },
      // 保存对责任信息的修改
      saveHandle() {
        if (this.pagingData.length) {
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
              for (const i in val) {
                if (val.dutycode === item) {
                  dutyObject.dutycode = val.dutycode
                  dutyObject.dutyname = val.dutyname
                  dutyObject.getDutyList.push(val)
                }
              }
            })
            outterList.push(dutyObject)
          })
          this.dutyParams.dutyVOList = outterList
          updateDutyList(this.dutyParams).then(res => {
            if (res.status === '1') {
              this.getInsuranceInfoData()
              this.$message({
                message: '保存成功',
                type: 'success',
                showClose: true,
                center: true
              })
            } else {
              this.$message({
                message: '保存失败',
                type: 'error',
                showClose: true,
                center: true
              })
            }
          })
          this.resTableData.forEach(item => {
            item.editFlag = false
          })
        }
      },
      confirmHandle() {
        this.$refs.reForm.validate((valid) => {
          if (valid) {
            this.dialogVisible = false
            const params = {
              explanation: this.reForm.explanation,
              insurancecode: this.$route.query.insurancecode,
              status: this.$route.query.status
            }
            this.resetLoading = true
            updatePublicRiskReturn(params).then(res => {
              if (res.status === '1') {
                this.$message({
                  message: '重定成功',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.resetFlag = true
                this.$router.go(-1)
              } else if (res.data === '存在险种已同步至理赔系统，请刷新后重试') {
                this.$message({message: res.data, type: 'error', center: true, showClose: true})
              } else {
                this.$message({
                  message: '重定失败',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
              this.resetLoading = false
            })
          } else {
            return false
          }
        })
      },
      editHandle(row) {
        row.editFlag = !row.editFlag
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
        this.getRules()
      },
      rulePageChange(val) {
        this.rulPageInfo.page = val
        this.getRules()
      },
      reviewHandle() {
        this.$refs.reviewForm.validate((valid) => {
          if (valid) {
            const params = {
              conclusion: this.reviewForm.conclusion,
              explanation: this.reviewForm.explanation,
              insurancecode: this.insuranceInfo.insurancecode
            }
            this.reviewLoading = true
            updatePublicRiskConclusion(params).then(res => {
              if (res.status === '1') {
                this.$message({
                  message: '审核完毕',
                  type: 'success',
                  showClose: true,
                  center: true
                })
                this.$router.go(-1)
              } else {
                this.$message({
                  message: '审核失败',
                  type: 'error',
                  showClose: true,
                  center: true
                })
              }
              this.reviewLoading = false
            })
          } else {
            return false
          }
        })
      },
      ruleReset() {
        this.dialogVisible = true
      },
      goBack() {
        this.$router.go(-1)
      },
      changeTab() {
        if (this.activeName === 'second') {
          this.exemptionInfo.page = 1
          this.exemptionInfo.pageSize = 10
          this.getExemptionHandle()
        } else {
          this.rulPageInfo.page = 1
          this.rulPageInfo.pageSize = 10
          this.getRules()
        }
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
              let rulename = ''
              item.exemptionRelationVO.forEach((val, ind) => {
                if (val.getdutycode !== '') {
                  rulename += ind === 0 ? val.getdutyname : '/' + val.getdutyname
                } else {
                  rulename += ind === 0 ? val.dutyname : '/' + val.dutyname
                }
              })
              let eachData = {
                rulename: rulename,
                rulecode: rulename,
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
      getGears: function (value) {
        //return that.dict.label.gears[value]
        return this.selectDictLabel(this.gearsOptions, value);
      },
      getUseOfMedicalInsurance(value) {
        //return that.dict.label.useOfMedicalInsurance[value]
        return this.selectDictLabel(this.gearsOptions, value);
      },
      getRiskType(value) {
        //return that.dict.label.incidenttype[value]
        return this.selectDictLabel(this.incidenttypeOptions, value);
      },
      getVisittype(value) {
        //return that.dict.label.visit_type[value]
        return this.selectDictLabel(this.visit_typeOptions, value);
      },
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
      getObservation(value) {
        //return that.dict.label.observation[value]
        return this.selectDictLabel(this.observationOptions, value);
      },
      getWaitingPeriod(value) {
        //return that.dict.label.waiting_period[value]
        return this.selectDictLabel(this.waiting_periodOptions, value);
      }

    }
  }
</script>

<style scoped>
  .small-input {
    width: 80%;
  }

  .to_right {
    width: 100px;
    text-align: right;
  }
</style>
