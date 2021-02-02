<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :before-close="dialogClosed"
      :close-on-click-modal="false"
      title="提调信息"
      width="80%"
      @open="open">
      <el-card class="box-card" shadow="never" style="margin-top: -20px;" id="toneinfoClass">
        <el-tabs v-model="activeName" @tab-click="tabClick">
          <el-tab-pane label="关联调查" name="01">
            <el-table
              v-loading="loading1"
              :data="tableData"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="small"
              highlight-current-row
              tooltip-effect="dark">
              <!--            <el-table-column align="center" type="selection" label="序号"/>-->
              <el-table-column align="center" property="taskNo" label="调查号"/>
              <el-table-column align="center" property="claimNo" label="赔案号"/>
              <el-table-column :formatter="displaySurveyType" align="center" property="surveyType" label="调查类型"/>
              <el-table-column :formatter="displaySurveyReason" align="center" property="initiateReasons" label="提调原因"/>
              <el-table-column align="center" property="sinitiateDate" label="提调日期"></el-table-column>
              <el-table-column align="center" property="initiator" label="提调人"/>
              <el-table-column :formatter="displaySurveyStatus" align="center" property="currentStatus" label="调查状态"/>
              <!--<el-table-column align="center" property="conclusionNature" label="结论性质"/>-->
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="既往调查" name="03" style="margin-left:20px">
            <!-- 如果最终字段大致相同可遍历渲染 -->
            <el-table
              v-loading="loading2"
              :data="tableData2"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="small"
              highlight-current-row
              tooltip-effect="dark">
              <el-table-column width="55">
                <template slot-scope="scope">
                  <el-radio v-model="radio" :label="scope.row.id"
                            @change.native="handleCurrentChange(scope.row, scope.$index)">{{ &nbsp; }}
                  </el-radio>
                  <!--                <el-checkbox v-model="scope.row.checked"/>-->
                </template>
              </el-table-column>
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-row>
                    <el-col :span="8" class="col-style">
                      <span class="labelClass">
                        <span style="float: right">出险地区：</span>
                      </span>
                      <span class="dataClass">{{scope.row.orrencearea}}</span>
                    </el-col>
                    <el-col :span="8" class="col-style">
                      <span class="labelClass">
                        <span style="float: right">调查地区：</span>
                      </span>
                      <span class="dataClass">{{''}}</span>
                    </el-col>
                    <el-col :span="8" class="col-style">
                      <span class="labelClass">
                        <span style="float: right">提调依据：</span>
                      </span>
                      <span class="dataClass">{{promotionbasisHand(scope.row.investigatebasis)}}</span>
                    </el-col>
                    <el-col :span="8" class="col-style">
                      <span class="labelClass">
                        <span style="float: right">调查要求：</span>
                      </span>
                      <span class="dataClass">{{scope.row.initiatedemand}}</span>
                    </el-col>
                    <el-col :span="8" class="col-style">
                      <span class="labelClass">
                        <span style="float: right">结案时间：</span>
                      </span>
                      <span class="dataClass">{{changeDate(scope.row.closedate)}}</span>
                    </el-col>
                    <el-col :span="8" class="col-style">
                      <span class="labelClass">
                        <span style="float: right">调查结论：</span>
                      </span>
                      <span class="dataClass">{{scope.row.conclusion}}</span>
                    </el-col>
                  </el-row>
                </template>
              </el-table-column>
              <el-table-column align="center" property="taskno" label="调查号"/>
              <el-table-column align="center" property="claimno" label="赔案号"/>
              <el-table-column :formatter="displaySurveyType1" align="center" property="surveytype" label="调查类型"/>
              <el-table-column :formatter="displaySurveyReason1" align="center" property="initiatereasons"
                               label="提调原因"/>
              <el-table-column align="center" property="sinitiatedate" label="提调日期">
                <template slot-scope="scope">
                  <span>{{scope.row.sinitiatedate}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" property="initiator" label="提调人"/>
              <el-table-column :formatter="displaySurveyStatus1" align="center" property="currentstatus" label="调查状态"/>
              <el-table-column :formatter="getCasecharacter" align="center" property="casecharacter" label="结论性质"/>
              <el-table-column align="center" label="操作" fixed="right" v-if="node == 'firstTrial'">
                <template slot-scope="scope">
                  <el-button type="text" size="mini" @click="returnMethods(scope.row,scope.$index)">回退</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <el-pagination
          :total="activeName==='01'?total01:total02"
          :current-page="activeName==='01'? page01 : page02"
          :page-sizes="[5, 10]"
          :page-size="activeName==='01'?pageSize01:pageSize02"
          style="margin-top: 8px; text-align: right;"
          layout="sizes, prev, pager, next"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
      </el-card>
      <el-card class="box-card" style="margin-top: 20px;" shadow="never">
        <div slot="header" class="clearfix">
          <span>提调信息</span>
          <span style="float: right; padding: 3px 0">
            <el-button :loading="serverLoading" type="primary" size="mini" @click="surveyHandle">发起调查</el-button>
            <el-button type="primary" size="mini" @click="resetForm">重置</el-button>
          </span>
        </div>
        <el-form ref="baseForm" :rules="rules" :model="baseForm" label-width="120px" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="赔案号：" prop="claimno">
                <span>{{ baseForm.claimno }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="调查次数：" prop="investigateTime">
                <span>{{ baseForm.investigateTime ? baseForm.investigateTime : 0 }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="提调人：" prop="initiator;">
                <span>{{ baseForm.initiator }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="调查方式：" prop="surveyMode">
                <el-select v-model="baseForm.surveyMode" class="item-width" placeholder="请选择">
                  <el-option v-for="option in surveyModes" :disabled="option.value === '02'" :key="option.id"
                             :label="option.label" :value="option.value"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="调查类型：" prop="surveyType">
                <el-select v-model="baseForm.surveyType" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="option in surveyTypes" :key="option.id" :label="option.label"
                             :value="option.value"/>
                </el-select>
              </el-form-item>
            </el-col>
            <!-- <el-col :span="8">
              <el-form-item label="提调时效：" prop="type">
                <el-select v-model="baseForm.type" class="item-width" placeholder="请选择">
                  <el-option label="一" value="1"/>
                  <el-option label="二" value="2"/>
                </el-select>
              </el-form-item>
            </el-col> -->
            <el-col :span="8">
              <el-form-item label="提调原因：" prop="initiateReasons">
                <!--              <el-select v-model="baseForm.initiateReasons" class="item-width" placeholder="请选择">-->
                <!--                <el-option v-for="option in initiateReasonsArr" :key="option.id" :label="option.label" :value="option.value"/>-->
                <!--              </el-select>-->
                <el-cascader v-model="baseForm.initiateReasons" :options="initiateReasonsArr" class="item-width"
                             placeholder="请选择"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="涉案金额：" prop="investigationAmount">
                <el-input v-model="baseForm.investigationAmount" class="item-width"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="出险地区：" prop="address">
                <el-cascader
                  v-model="baseForm.address"
                  :options="address"
                  :props="{ checkStrictly: true }"
                  class="item-width"
                  clearable></el-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="提调依据：" prop="investigateBasis">
                <el-select v-model="baseForm.investigateBasis" class="item-width" placeholder="请选择">
                  <el-option v-for="option in promotionbasisOptions" :key="option.id" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="疑点描述：" prop="doubtfulDescription">
                <el-input v-model="baseForm.doubtfulDescription" type="textarea" :autosize="{ minRows: 3, maxRows: 10}"
                          maxlength="200" show-word-limit/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="调查要求：" prop="initiateDemand">
                <el-input v-model="baseForm.initiateDemand" type="textarea" :autosize="{ minRows: 3, maxRows: 10}"
                          maxlength="1000" show-word-limit/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
      <el-card v-if="activeName==='03' && radio !== null" class="box-card" style="margin-top: 20px;" shadow="never">
        <!--    <el-card class="box-card" style="margin-top: 20px;" shadow="never">-->
        <div slot="header" class="clearfix">
          <span>调查信息</span>
        </div>
        <div v-if="type==='point'">
          <el-form :model="tableForm" style="margin-top: 20px" label-width="80px" size="mini">
            <el-table
              :data="paginInvestData"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="mini"
              highlight-current-row
              tooltip-effect="dark">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-table :data="props.row.surveyPointList" :show-header="false" :span-method="arraySpanMethod" border
                            size="mini">
                    <el-table-column prop="hospitalname" label="医院"/>
                    <el-table-column width="260" prop="hospital">
                      <template slot-scope="scope">
                        <el-form-item label="是否调查">
                          <el-radio :disabled="true" v-model="scope.row.isInvestigate" label="01">是</el-radio>
                          <el-radio :disabled="true" v-model="scope.row.isInvestigate" label="02">否</el-radio>
                        </el-form-item>
                      </template>
                    </el-table-column>
                    <el-table-column>
                      <template slot-scope="scope">
                        <el-row>
                          <el-col v-if="scope.row.isInvestigate === '01'" :span="12">
                            <el-form-item label="是否异常">
                              <el-radio :disabled="true" v-model="scope.row.isAbnormal" label="01" size="mini">是
                              </el-radio>
                              <el-radio :disabled="true" v-model="scope.row.isAbnormal" label="02" size="mini">否
                              </el-radio>
                            </el-form-item>
                          </el-col>
                          <el-col v-if="scope.row.isInvestigate === '01'" :span="12">
                            <el-form-item label="打卡照片">
                              <el-button type="text" size="mini" @click="downloadAttachment(scope.row.annexList)">
                                打卡照片({{ scope.row.annexList.length }})
                              </el-button>
                            </el-form-item>
                          </el-col>
                          <el-col v-else :span="12">
                            <el-form-item label="备注信息">
                              <span>{{ scope.row.causeOf }}</span>
                            </el-form-item>
                          </el-col>
                        </el-row>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column :formatter="getCasePointType" prop="casePointType" label="案件点类型"/>
              <el-table-column prop="investigationRequirements" label="调查要求"/>
            </el-table>
          </el-form>
          <el-pagination
            :total="total03"
            :current-page="page03"
            :page-sizes="[10, 20, 30]"
            :page-size="pageSize03"
            style="margin-top: 8px; text-align: right;"
            layout="sizes, prev, pager, next"
            @size-change="investSizeChange"
            @current-change="investPageChange"
          />
        </div>
        <!-- 按项目 -->
        <div v-else>
          <el-form :model="proForm">
            <el-card shadow="never">
              <el-form-item>
                <div class="flex_box">
                  <p class="flex-title">医疗机构排查：</p>
                  <div class="flex-content">
                    <el-checkbox-group v-model="proForm.org">
                      <el-checkbox v-for="item in organList" :label="item" :key="item" style="width: 160px;">{{ item
                        }}
                      </el-checkbox>
                    </el-checkbox-group>
                  </div>
                </div>
              </el-form-item>
            </el-card>
            <el-card shadow="never" style="margin-top: 10px;">
              <el-form-item>
                <div class="flex_box">
                  <p class="flex-title">社保排查：</p>
                  <div class="flex-content">
                    <el-checkbox-group v-model="proForm.social">
                      <el-checkbox v-for="item in socialList" :label="item" :key="item" style="width: 160px;">{{ item
                        }}
                      </el-checkbox>
                    </el-checkbox-group>
                  </div>
                </div>
              </el-form-item>
            </el-card>
            <el-card shadow="never" style="margin-top: 10px;">
              <el-form-item>
                <div class="flex_box">
                  <p class="flex-title">走访排查：</p>
                  <div class="flex-content">
                    <el-checkbox-group v-model="proForm.visit">
                      <el-checkbox v-for="item in visitList" :label="item" :key="item" style="width: 160px;">{{ item
                        }}
                      </el-checkbox>
                    </el-checkbox-group>
                  </div>
                </div>
              </el-form-item>
            </el-card>
            <el-card shadow="never" style="margin-top: 10px;">
              <el-form-item>
                <div class="flex_box">
                  <p class="flex-title">其他：</p>
                  <div class="flex-content">
                    <el-checkbox-group v-model="proForm.other">
                      <el-checkbox v-for="item in otherList" :label="item" :key="item" style="width: 160px;">{{ item
                        }}
                      </el-checkbox>
                    </el-checkbox-group>
                  </div>
                </div>
              </el-form-item>
            </el-card>
          </el-form>
        </div>
        <div style="margin-top: 20px;">调查报告：</div>
        <div class="doc_list" style="">
          <p v-for="(item, index) in investigationReport" :key="index">
            <el-button class="del_padding" type="text" @click="downloadAttachment([item])">
              <i class="el-icon-link"/>
              {{ item.name }}
            </el-button>
          </p>
        </div>
        <div style="margin-top: 20px;">
          <span style="vertical-align: top;">结论描述：</span>
          <el-input v-model="conclusionDescription" style="width:  90%;" disabled :autosize="{ minRows: 3, maxRows: 10}"
                    type="textarea">备注信息
          </el-input>
        </div>
      </el-card>
    </el-dialog>
    <el-dialog
      title="调查回退"
      :visible.sync="returnModal"
      width="450px"
      :append-to-body="true"
      :show-close="false"
      :close-on-click-modal="false"
    >
      <el-form :model="ruleForm" :rules="ruleslist" ref="returnForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="回退原因" prop="rejectReason">
          <el-select v-model="ruleForm.rejectReason" style="width:260px" class="item-width" placeholder="请选择">
            <el-option v-for="option in reject_reasonOptions" :key="option.dictValue" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="回退类型" prop="rejectType">
          <el-select v-model="ruleForm.rejectType" style="width:260px" class="item-width" placeholder="请选择">
            <el-option v-for="option in reject_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="回退描述" prop="rejectDescription">
          <el-input
            type="textarea"
            style="width:260px"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入内容"
            clearable
            maxlength="100"
            show-word-limit
            v-model="ruleForm.rejectDescription">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="confireReturnag" type="primary" size="mini">确认</el-button>
          <el-button type="primary" size="mini" @click="returnModal = false">取消</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
  let that
  import {get} from '@/api/dictDetail'
  import {
    relationSurvey,
    surveyInfo,
    investigateCount,
    investigateTask,
    claimDownloadFile,
    getFinalamount,
    getHospitalArea
  } from '@/api/toneInfo/toneinfo.js'
  import {toClient, paginData} from '@/utils/commenMethods.js'
  import {downloadData} from '@/utils/index.js'
  import {changeDate} from '@/utils/commenMethods.js'
  import {investFallback} from '@/api/claim/handleDeal.js'

  import getAddressByFront from '../mixins/address'

  export default {
    /*dicts: ['casecharacter', 'surve_types', 'initiate_reasons', 'investigate_status', 'second_initiate_reasons', 'casePoint_type',
     'promotionbasis','reject_reason','reject_type'
    ],*/
    inject: ['getCountInvestigaeNumber'],
    mixins: [getAddressByFront],
    props: {
      dictList: Array,
      value: {
        type: Boolean,
        default: false
      },
      node: {
        type: String,
        default: ''
      },
      type: {
        type: String,
        default: 'point'
      },
      claimno: {
        type: String,
        default: '111'
      }
    },
    filters: {},
    data() {
      const checkMoney = (rule, value, callback) => {
        const str = /^([1-9]\d*(\.\d{1,2})?|([0](\.([0][1-9]|[1-9]\d{0,1}))))$/
        if (value === null || value === '' || value === undefined) {
          callback(new Error('涉案金额不可为空'))
        } else if (!str.test(value)) {
          callback(new Error('允许录入大于0的数，最多两位小数'))
        } else {
          callback()
        }
      }
      const checkRequirements = (rule, value, callback) => {
        if (value.length > 1000) {
          callback(new Error('最多录入1000字符'))
        } else {
          callback()
        }
      }
      return {
        activeName: '01',
        total01: 0,
        total02: 0,
        total03: 0,
        page01: 1,
        page02: 1,
        page03: 1,
        pageSize01: 5,
        pageSize02: 5,
        pageSize03: 10,
        tableData: [],
        tableData2: [],
        investData: [],
        tableForm: {},
        returnModal: false, // 回退确认框
        ruleForm: {
          rejectDescription: ''
        },
        ruleslist: {
          rejectReason: [
            {required: true, message: '请选择回退原因', trigger: 'blur'},
          ],
          rejectType: [
            {required: true, message: '请选择回退类型', trigger: 'blur'},
          ],
          rejectDescription: [
            {required: true, message: '请输入回退描述', trigger: 'blur'},
          ],
        },
        returnMethodsFrom: {},
        returnMethodsIndex: 0,
        baseForm: {
          claimno: '',
          initiator: '',
          surveyMode: '',
          surveyType: '',
          initiateReasons: '',
          investigationAmount: '',
          doubtfulDescription: '',
          initiateDemand: '',
          address: [],
          investigateBasis: ''
        },
        surveyModes: [],
        surveyTypes: [],
        initiateReasonsArr: [],
        proForm: {
          org: [],
          social: [],
          visit: [],
          other: []
        },
        organList: ['居住地附件医院排查', '工作地附件医院排查', '户籍地附件医院排查', '出险地附件医院排查', '出生医院排查', '本次就诊医院排查', '体检机构排查'],
        socialList: ['社保报销记录排查', '社保住院报销机构排查', '社保门诊报销机构排查', '社保药店报销机构排查'],
        visitList: ['面访被保险人/家属', '走访事故地点', '走访居住地', '走访户籍地', '走访工作单位', '走访公检法等机关单位', '走访鉴定机构', '走访材料出具机构', '走访事故处理机构', '走访街道办或村委会'],
        otherList: ['同业排查', '延伸排查', '其他'],
        mark: '备注信息',
        loading1: false,
        loading2: false,
        rules: {
          surveyMode: [{required: true, message: '必录', trigger: 'change'}],
          surveyType: [{required: true, message: '必录', trigger: 'change'}],
          initiateReasons: [{required: true, message: '必录', trigger: 'change'}],
          // doubtfulDescription: [{ required: true, message: '必录', trigger: 'blur' }],
          investigationAmount: [{required: true, validator: checkMoney, trigger: 'blur'}],
          initiateDemand: [{validator: checkRequirements, trigger: 'change'}],
          address: [{required: true, message: '必录', trigger: 'change'}],
          investigateBasis: [{required: true, message: '必录', trigger: 'change'}],
        },
        choose: [],
        investigationReport: [],
        row: {},
        childReason: [],
        radio: null,
        conclusionDescription: '',
        serverLoading: false,
        address: [],

        casecharacterOptions: [],
        surve_typesOptions: [],
        initiate_reasonsOptions: [],
        investigate_statusOptions: [],
        second_initiate_reasonsOptions: [],
        casePoint_typeOptions: [],
        promotionbasisOptions: [],
        reject_reasonOptions: [],
        reject_typeOptions: []
      }
    },
    computed: {
      dialogVisable: {
        set() {
        },
        get() {
          return this.value
        }
      },
      paginInvestData: function () {
        return paginData(this.investData, this.page03, this.pageSize03)
      }
    },
    mounted() {
      if (this.dictList!=null && this.dictList.length!=0) {
        this.casecharacterOptions = this.dictList.find(item => {
          return item.dictType == 'casecharacter'
        }).dictDate
        this.surve_typesOptions = this.dictList.find(item => {
          return item.dictType == 'surve_types'
        }).dictDate
        this.initiate_reasonsOptions = this.dictList.find(item => {
          return item.dictType == 'initiate_reasons'
        }).dictDate
        this.investigate_statusOptions = this.dictList.find(item => {
          return item.dictType == 'investigate_status'
        }).dictDate
        this.second_initiate_reasonsOptions = this.dictList.find(item => {
          return item.dictType == 'second_initiate_reasons'
        }).dictDate
        this.casePoint_typeOptions = this.dictList.find(item => {
          return item.dictType == 'casePoint_type'
        }).dictDate
        this.promotionbasisOptions = this.dictList.find(item => {
          return item.dictType == 'promotionbasis'
        }).dictDate
        this.reject_reasonOptions = this.dictList.find(item => {
          return item.dictType == 'reject_reason'
        }).dictDate
        this.reject_typeOptions = this.dictList.find(item => {
          return item.dictType == 'reject_type'
        }).dictDate
      }
    },
    methods: {
      changeDate(data) {
        return changeDate(data)
      },
      promotionbasisHand(data) {
        /*return that.dict.label.promotionbasis[data]*/
        return this.selectDictLabel(this.promotionbasisOptions, data)
      },
      open() {
        this.getRelationSurvey()
        this.getInvestigateCount()
        this.getFinalamount()
        const initiator = toClient()
        this.$set(this.baseForm, 'initiator', initiator)
        this.$set(this.baseForm, 'claimno', this.claimno)
        this.getSurveyModes()// 调查方式
        this.getSurveyTypes()// 调查类型
        this.getSurveyReasons()// 提调原因
        // this.queryHospitalArea()// 出险地区
      },
      // 查询出险地区
      // queryHospitalArea() {
      //   getHospitalArea(this.claimno).then(res => {
      //       if (res.data !== null) {
      //         this.$set(this.baseForm, 'address', [res.data.occurrenceprovince, res.data.occurrencecity, res.data.occurrencedistrict])
      //       }
      //   })
      // },
      getCasePointType(row) {
        /*return this.dict.label.casePoint_type[row.casePointType]*/
        return this.selectDictLabel(this.casePoint_typeOptions, row.casePointType)
      },
      returnMethods(row, index) {
        this.returnModal = true
        this.returnMethodsFrom = row
        this.returnMethodsIndex = index
      },
      confireReturnag() {
        this.$refs.returnForm.validate((valid) => {
          if (valid) {
            let params = {}
            params.investNo = this.returnMethodsFrom.taskno
            params.initiateNo = this.returnMethodsFrom.initiateno
            params.rejectType = this.ruleForm.rejectType
            params.rejectReason = this.ruleForm.rejectReason
            params.rejectDescription = this.ruleForm.rejectDescription
            investFallback(params).then(res => {
              if (res.status == '1') {
                this.$message.success('回退成功！')
                this.returnModal = false
              } else {
                this.$message({message: res.data.errMsg, type: 'error'})
                this.returnModal = false
              }
            })
          } else {
            return false
          }
        })
      },
      handleCurrentChange(currentRow, index) {
        this.tableData2.forEach(item => {
          if (item.taskNo !== currentRow.taskNo) {
            item.checked = false
          }
          this.choose = currentRow
        })
        this.getSurveyInfo()
      },
      // 获取涉案金额
      getFinalamount() {
        getFinalamount(this.claimno).then(res => {
          if (res.status === '1') {
            this.$set(this.baseForm, 'investigationAmount', res.data)
          }
        })
      },
      getChildrenSelection() {
        this.initiateReasonsArr.forEach(item => {
          if (item.value === '01') {
            item.children = this.childReason
          }
        })
        this.initiateReasonsArr = [...this.initiateReasonsArr]
      },
      getSurveyModes() {
        const dictName = 'surveymode'
        get(dictName).then((response) => {
          this.surveyModes = response.content
        })
      },
      getSurveyTypes() {
        const dictName = 'surve_types'
        this.getDicts(dictName).then((response) => {
          this.surveyTypes = response.content
        })
      },
      getSurveyChildReason() {
        const dictName = 'second_initiate_reasons'
        this.getDicts(dictName).then((response) => {
          this.childReason = response.content
          this.getChildrenSelection()
        })
      },
      getSurveyReasons() {
        const dictName = 'initiate_reasons'
        this.getDicts(dictName).then((response) => {
          this.initiateReasonsArr = response.content
          this.getSurveyChildReason()// 二级原因
        })
      },
      tabClick() {
        this.activeName === '01' ? this.tableData = [] : this.tableData2 = []
        this.activeName === '01' ? this.page01 = 1 : this.page02 = 1
        this.activeName === '01' ? this.pageSize01 = 5 : this.pageSize02 = 5
        this.getRelationSurvey()
      },
      displaySurveyType(row, col) {
        /* return this.dict.label.surve_types[row.surveyType]*/
        return this.selectDictLabel(this.surve_typesOptions, row.surveyType)
      },
      displaySurveyType1(row, col) {
        /*return this.dict.label.surve_types[row.surveytype]*/
        return this.selectDictLabel(this.surve_typesOptions, row.surveytype)
      },
      displaySurveyReason(row, col) {
        const dictName = 'second_initiate_reasons'
        const reasonArr = row.initiateReasons.split(',')
        if (reasonArr.length <= 1) {
          /* return this.dict.label.initiate_reasons[row.initiateReasons]*/
          return this.selectDictLabel(this.initiate_reasonsOptions, row.initiateReasons)
        } else {
          /* let reason1 = reasonArr[0] ? this.dict.label.initiate_reasons[reasonArr[0]] : ''
           let reason2 = reasonArr[1] ? '/' + this.dict.label.second_initiate_reasons[reasonArr[1]] : ''*/
          let reason1 = reasonArr[0] ? this.selectDictLabel(this.initiate_reasonsOptions, reasonArr[0]) : ''
          let reason2 = reasonArr[1] ? '/' + this.selectDictLabel(this.second_initiate_reasonsOptions, reasonArr[1]) : ''
          return reason1 + reason2
        }
      },
      displaySurveyReason1(row, col) {
        const dictName = 'second_initiate_reasons'
        const reasonArr = row.initiatereasons.split(',')
        if (reasonArr.length <= 1) {
          /* return this.dict.label.initiate_reasons[row.initiatereasons]*/
          return this.selectDictLabel(this.initiate_reasonsOptions, row.initiatereasons)
        } else {
          /*let reason1 = reasonArr[0] ? this.dict.label.initiate_reasons[reasonArr[0]] : ''
          let reason2 = reasonArr[1] ? '/' + this.dict.label.second_initiate_reasons[reasonArr[1]] : ''*/
          let reason1 = reasonArr[0] ? this.selectDictLabel(this.initiate_reasonsOptions, reasonArr[0]) : ''
          let reason2 = reasonArr[1] ? '/' + this.selectDictLabel(this.second_initiate_reasonsOptions, reasonArr[1]) : ''
          return reason1 + reason2
        }
      },
      displaySurveyStatus(row, col) {
        /* return this.dict.label.investigate_status[row.currentStatus]*/
        return this.selectDictLabel(this.investigate_statusOptions, row.currentStatus)
      },
      displaySurveyStatus1(row, col) {
        /*return this.dict.label.investigate_status[row.currentstatus]*/
        return this.selectDictLabel(this.investigate_statusOptions, row.currentstatus)
      },
      getCasecharacter(row, col) {
        /*return this.dict.label.casecharacter[row.casecharacter]*/
        return this.selectDictLabel(this.casecharacterOptions, row.casecharacter)
      },
      // 关联调查/既往调查 查询
      getRelationSurvey() {
        const params = {
          claimNo: this.claimno,
          tabNo: this.activeName,
          pageNo: this.activeName === '01' ? this.page01 : this.page02,
          pageSize: this.activeName === '01' ? this.pageSize01 : this.pageSize02
        }
        if (this.activeName === '01') {
          this.loading1 = true
        } else {
          this.loading2 = true
        }
        relationSurvey(params).then(res => {
          if (res.status === '1') {
            if (this.activeName === '01') {
              this.tableData = res.data.list
              this.total01 = res.data.total
            } else {
              res.data.list.forEach(item => {
                item.checked = false
              })
              this.tableData2 = res.data.list
              this.tableData2.forEach((item, ind) => {
                item.id = ind
              })
              this.total02 = res.data.total
            }
          } else {
            if (this.activeName === '01') {
              this.$message({message: '关联调查结果错误！', type: 'error'})
            } else {
              this.$message({message: '既往调查结果错误！', type: 'error'})
            }
          }
        }).finally(res => {
          this.loading1 = false
          this.loading2 = false
        })
      },
      // 调查信息  案件点
      getSurveyInfo() {
        if (!this.choose) {
          return
        }
        const params = {
          taskNo: this.choose.taskno,
          surveyMode: '01'
        }
        surveyInfo(params).then(res => {
          if (res.status === '1' && res.data.data !== null) {
            this.investData = res.data.data.casePointList
            this.total03 = res.data.data.casePointList.length
            this.investigationReport = res.data.data.investigationReport
            this.conclusionDescription = res.data.data.conclusionDescription
          }
        })
      },
      // 发起调查次数
      getInvestigateCount() {
        investigateCount(this.claimno).then(res => {
          this.$set(this.baseForm, 'investigateTime', res.data)
        })
      },
      surveyHandle() {
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            const params = JSON.parse(JSON.stringify(this.baseForm))
            params.province = params.address.length ? params.address[0] : ''
            params.city = params.address.length ? params.address[1] : ''
            params.district = params.address.length ? params.address[2] : ''
            delete params.address
            params.initiateReasons = params.initiateReasons.join(',')
            this.serverLoading = true
            investigateTask(params).then(res => {
              if (res.status === '1') {
                this.getRelationSurvey()
                this.getInvestigateCount()
                this.getCountInvestigaeNumber()
                this.$message.success('发起调查成功！')
                window.location.reload()
                this.$emit('updateStatus', 'show')
              } else {
                if (res.data.errCode === 'SL001' || res.data.errCode === 'SL002' || res.data.errCode === 'SL003') {
                  this.$message.error(res.data.errMsg)
                } else {
                  this.$message.error('发起调查失败！')
                }
              }
              this.serverLoading = false
            })
          } else {
            return false
          }
        })
      },
      dialogClosed() {
        this.$refs.baseForm.resetFields()
        this.$emit('input', false)
      },
      resetForm() {
        this.$refs.baseForm.resetFields()
        this.$set(this.baseForm, 'claimno', this.claimno)
      },
      saveHandle() {
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            alert('submit!')
          } else {
            return false
          }
        })
      },
      arraySpanMethod({row, column, rowIndex, columnIndex}) {
        if (rowIndex === 1) {
          if (columnIndex === 2) {
            return [1, 2]
          }
        }
      },
      sizeChange(val) {
        this.activeName === '01' ? this.pageSize01 = val : this.pageSize02 = val
        this.getRelationSurvey()
      },
      pageChange(val) {
        this.activeName === '01' ? this.page01 = val : this.page02 = val
        this.getRelationSurvey()
      },
      setCurrentRow() {
      },
      // handleSelectionChange(sel) {
      //     this.tableData2.forEach(item => {
      //         if (item.taskNo !== sel.taskNo) {
      //             item.checked = false
      //         }
      //         this.choose = sel
      //         this.getSurveyInfo()
      //     })
      // },
      investSizeChange(val) {
        this.pageSize03 = val
      },
      investPageChange(val) {
        this.page03 = val
      },
      downloadAttachment(fileList) {
        fileList.forEach(item => {
          const pushObj = {
            filePath: item.url,
            fileName: item.name
          }
          claimDownloadFile(pushObj).then(res => {
            downloadData(res, item.name)
          })
        })
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }

  .el-table ::v-deep .el-table__expanded-cell {
    padding: 20px 0;
  }

  .expand_span {
    font-size: 12px;
  }

  .doc_list {
    color: #999;
  }

  .flex_box {
    display: flex;
  }

  .flex-title {
    flex: 1;
    margin-top: 0;
    padding-right: 20px;
    text-align: right;
    color: #555;
  }

  .flex-content {
    flex: 7;
  }

  .del_padding {
    padding-top: 0;
    padding-bottom: 0;
  }

  .col-style {
    display: flex;
  }

  .labelClass {
    display: inline-block;
    width: 30%;
    padding: 5px;
  }

  .dataClass {
    display: inline-block;
    flex: 1;
    padding: 5px;
  }

  #toneinfoClass ::v-deep .el-tabs--top .el-tabs__item.is-top:last-child {
    margin-left: 50px;
  }
</style>
