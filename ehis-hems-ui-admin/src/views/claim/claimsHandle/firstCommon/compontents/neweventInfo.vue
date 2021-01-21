
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">事件信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" type="primary" size="mini" @click="invoiceHandle">发票补录</el-button>
        </span>
        <el-form style="padding-bottom: 30px;"  :rules="eventRules" ref="tableForm" :model="eventForm" size="mini">
          <el-row>
            <el-table
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="eventInfo"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;"
              @selection-change="handleSelectChange">
              <el-table-column type="selection" width="50" align="center"/>
              <el-table-column label="事件号" prop="incidentno" min-width="140" align="center">
                <template slot-scope="scope">
                  <el-form-item prop="incidentno">
                    <span style="font-size:12px">{{scope.row.incidentno}}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="申请类别" prop="categorycode" min-width="180" align="center">
                <template slot-scope="scope">
                  <el-form-item v-if="!scope.row.id" :prop="'eventInfo.' + scope.$index + '.categorycode'" :rules="eventRules.categorycode">
                    <el-select style="width:160px" v-model="scope.row.categorycode" multiple collapse-tags class="item-width"  @change="changeTumorcode" placeholder="请选择">
                      <el-option v-for="item in apply_typeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item v-else>
                    <span style="font-size:12px">{{ formateAppli(scope.row.categorycode) }}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="出险日期" prop="eventdate" align="center" width="100">
                <template slot-scope="scope">
                  <el-form-item prop="eventdate">
                    <span style="font-size:12px">{{scope.row.eventdate}}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="就诊区间" align="center" width="235">
                <template slot-scope="scope">
                  <el-form-item prop="visitdate">
                    <span style="font-size:12px">{{ scope.row.visitdate|formatApprai}}<span v-if="scope.row.visitdate !== ''">至</span>{{ scope.row.dischargedate }}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="出险类型" prop="incidenttype" min-width="120" align="center">
                <template slot-scope="scope">
                  <el-form-item v-if="!scope.row.id" prop="grouptype"  :rules="tableFormRules.grouptype">
                    <el-select v-model="scope.row.incidenttype" style="width:100px" class="item-width" placeholder="请选择">
                      <el-option v-for="item in incidenttypeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item v-else>
                    <span style="font-size:12px">{{ scope.row.incidenttype == null? '疾病' : /*dict.label.incidenttype[scope.row.incidenttype]*/this.selectDictLabel(this.incidenttypeOptions,scope.row.incidenttype)}}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="医院" prop="hospitalname" min-width="220" align="center">
                <template slot-scope="scope">
                  <el-form-item v-if="!scope.row.id"  :prop="'eventInfo.' + scope.$index + '.hospitalcode'" :rules="eventRules.hospitalcode">
                    <el-select
                      class="item-width"
                      v-model="scope.row.hospitalcode"
                      :remote-method="remoteHospitals"
                      @change="changeHospital"
                      filterable
                      remote
                      reserve-keyword
                      placeholder="请输入医院关键字"
                      clearable>
                      <el-option v-for="option in hospitals" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                  <el-form-item v-else>
                    <p style="font-size:12px">{{scope.row.hospitalname}}</p>
                    <!-- <p> {{scope.row.hospitallevel==='其他其他'?'其他':scope.row.hospitallevel}} </p> -->
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="诊断" width="140" prop="maindiagnosisname" align="center">
                <template slot-scope="scope">
                  <el-form-item v-if="!scope.row.id" prop="maindiagnosis"  :rules="tableFormRules.grouptype">
                    <el-select v-model="scope.row.maindiagnosis"
                      reserve-keyword
                      filterable
                      :remote-method="remoteICD"
                      remote
                      clearable
                      style="width:100px"
                      class="item-width"
                      placeholder="请选择">
                      <el-option v-for="(item, i) in ICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
                    </el-select>
                    <el-select v-model="scope.row.secondarydiagnosis"
                      :remote-method="secondarydiagnosisICD"
                      filterable
                      remote
                      reserve-keyword
                      style="width:100px;margin:5px 0"
                      clearable
                      class="item-width"
                      placeholder="请选择">
                      <el-option v-for="(item, i) in secondarydiagnosisICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
                    </el-select>
                    <el-select v-model="scope.row.otherdiagnosis"
                      ref="otherdia"
                      :remote-method="otherdiagnosisICD"
                      filterable
                      multiple
                      remote
                      reserve-keyword
                      style="width:100px"
                      clearable
                      class="item-width"
                      placeholder="请选择"
                      @change="otherdiaChange">
                      <el-option v-for="(item, i) in otherdiagnosisICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item v-else>
                    <el-popover
                      :ref="`popover-${scope.$index}`"
                      placement="right"
                      width="460"
                      trigger="hover">
                      <el-table :data="[scope.row]"
                        :header-cell-style="{color:'black',background:'#f8f8ff'}"
                        size="small">
                        <el-table-column property="maindiagnosisname" align="center" label="第一诊断"></el-table-column>
                        <el-table-column property="secondarydiagnosisname" align="center" label="第二诊断"></el-table-column>
                        <el-table-column property="otherdiagnosisname" align="center" label="其他诊断"></el-table-column>
                      </el-table>
                      <el-button slot="reference" type="text" size="mini">{{ scope.row.maindiagnosisname }}</el-button>
                    </el-popover>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="140" align="center">
                <template slot-scope="scope">
                  <el-form-item prop="lis">
                    <el-button v-if="scope.row.id" :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 0)">编辑</el-button>
                    <el-button v-if="!scope.row.id" :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="newSaveEventInfo(scope.row,scope.$index, 0)">保存</el-button>
                    <el-button :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="newEventDelete(scope.row,scope.$index)">删除</el-button>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
            <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(0)"> + 添加</el-button>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import {getInDangerInfo, saveDangerInfo, saveEventInfo, deleteDangerInfo, saveEventRelationBills, getFirstMedicalInstitution, getLikeICD10,getICD10ByCode, getHosptailByCode,
   getInfoByName,getaccident, getProofNameByCode, deleteEvent,getProofNameBySubCode , newEventDelete,newSaveEventInfo
} from '@/api/claim/handleDeal'
import moment from 'moment'
import {encrypt} from "@/utils/rsaEncrypt"
import { getHospitalLike } from '@/api/claim/input'
import getAddressByFront from "../../common/mixins/address"
import bus from '../../bus/bus'
let incidentnolist = []
export default {
  mixins:[getAddressByFront],
  inject: ['getEventInfoList'],
  //dicts: ['apply_type', 'incidenttype','payee_occupation' ,'diseasetype', 'risk_code', 'risk_level', 'disability_code', 'deformity_code', 'operation_code', 'accident_type','invoicetype','tumormorphologicalcode','occupationname', 'Identificationresults'],
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: {
      type: String,
      default: ''
    },
    eventInfo: {
      type: Array,
      default: function() {
        return [];
      }
    },
    insuredInfo: Object,
    accidentdate: String,
  },
  filters: {
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '') {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  data() {
    return {
      activeNames: ['1'],
      saveEventLoading: false,
      selRadio: false,
      inciSelRadio: null,
      title: '新增事件信息',
      eventForm: {},
      eventRules: {
        categorycode: [{ required: true, message: '不能为空!', trigger: 'change' }],
        hospitalcode: [{ required: true, message: '不能为空!', trigger: 'change' }],
      },
      operatList: [
        {label: '一', value: '1'},
        {label: '二', value: '2'},
      ],
      hospitals: [],
      tumormorphologicalcodeArr: [],
      routerList: [],
      visible: false,
      flag: false,
      ICDArr: [],
      otherdiagnosisICDArr:[],
      secondarydiagnosisICDArr: [],
      stateFrom: {},
      // diagnForm: {},
      timeChange: {
        disabledDate(time){
          return time.getTime() > Date.now()  //选择时间范围 ||
        }
      },
      tableData: [{ policyNo: 'QWE23423645', diagnosis: '上呼吸道', days: 5, subTable: [{ policyNo: 'QWE23423645', subTable: [{ policyNo: 'QWE23423645' }] }] }],
      // 模态框数据
      showFlag: false,
      dialogVis: false,
      addEventDia: false,
      addForm: {
        daterange: null,
        address: null,
        categorycode: null,
        otherdiagnosis: null
      },
      address: [],
      baseForm: {
        casediseaselist:[],
        casedisabilitylist: [],
        casedeformitylist: [],
        caseoperationlist: [],
        caseaccidentlist: []
      },
      transDiaVis: false,
      tumorcodeFlag: false,
      transForm: {},
      transParams: {},
      tableFormRules: {
        diseasetype: { required: true, message: '不能为空!', trigger: 'change' }, // 疾病类型
        diseasecode: { required: true, message: '不能为空!', trigger: 'change' }, // 疾病名称
        risklevel: { required: false, message: '不能为空!', trigger: 'change' }, // 风险等级
        identificationresults: { required: false, message: '不能为空!', trigger: 'change' }, // 认定结论
        judgmentbasis: { required: false, message: '不能为空!', trigger: 'change' }, //日期
        diagnosisdate: { required: true, message: '不能为空!', trigger: 'change' }, //日期
        disabilitycode: { required: true, message: '不能为空!', trigger: 'change' }, // 伤残名称
        disabilitylevel: { required: true, message: '不能为空!', trigger: 'change' }, // 伤残等级
        appraisaldate: { required: true, message: '不能为空!', trigger: 'change' }, // 日期
        paymentratio: { required: true, message: '不能为空!', trigger: 'change' }, // 比例
        operationcode: { required: true, message: '不能为空!', trigger: 'change' }, // 手术名称
        operationlevel: { required: true, message: '不能为空!', trigger: 'change' }, // 手术等级
        accidentcode: { required: true, message: '不能为空!', trigger: 'change' }, // 意外名称
        occupationname: { required: true, message: '不能为空!', trigger: 'change' }, // 出险职业名称
        occupationcategory: { required: true, message: '不能为空!', trigger: 'change' }, // 出险职业类别
      },
      addFormRules: {
        categorycode: { required: true, message: '不能为空!', trigger: 'change' },
        eventdate: { required: true, message: '不能为空!', trigger: 'change' },
        address: { required: true, message: '不能为空!', trigger: 'change' },
        daterange: { required: true, message: '不能为空!', trigger: 'change' },
        incidenttype: { required: true, message: '不能为空!', trigger: 'change' },
        incident: { required: true, message: '不能为空!', trigger: 'change' },
        hospitalcode: { required: true, message: '不能为空!', trigger: 'change' },
        maindiagnosis: { required: true, message: '不能为空!', trigger: 'change' },
        tumorcode: { required: false, message: '申请类别为重大疾病时，肿瘤形态学必录!', trigger: 'change' },
      },
      selIncidentno: null,
      selInvoiceno: null,
      stateListArr: [],
      reList: [],
      disabilityName: {
        grade: '',
        ratio: null
      }, //伤残等级
      disabilitylevelArr: {
        grade: '',
        ratio: ''
      }, // 残疾等级
      operationListArr: {
        grade: 0,
        ratio: 0
      }, // 手术等级
      occupationnameListArr: {
        grade: 0,
        ratio: 0
      }, // 意外等级
      listBill: {},
      disease01:[],
      restList: [],
      incidentnolist: [], // 事件号列表
      //中症
      "disease02":[],

      // 轻症
      "disease03":[],

      //少儿特疾
      "disease04":[],
      //伤残
      "disease05":[],



      //残疾
      "disease06":[],


      //手术
      "disease07":[],


      //意外
      "disease08":[],
      queryType: '', // 类型
      querysubtype: '',//疾病类型
      queryname: '', // 名称
      querycode: '', // code
     /* 'apply_type', 'incidenttype','payee_occupation' ,'diseasetype', 'risk_code', 'risk_level',
      'disability_code', 'deformity_code', 'operation_code', 'accident_type','invoicetype','tumormorphologicalcode','occupationname', 'Identificationresults'*/
      apply_typeOptions: [],
      incidenttypeOptions: [],
      payee_occupationOptions: [],
      diseasetypeOptions: [],
      risk_codeOptions: [],
      risk_levelOptions: [],
      disability_codeOptions: [],
      deformity_codeOptions: [],
      operation_codeOptions: [],
      accident_typeOptions: [],
      invoicetypeOptions: [],
      tumormorphologicalcodeOptions: [],
      occupationnameOptions: [],
      IdentificationresultsOptions: []
    }
  },
  mounted() {
    this.gettumormorphologicalcodeArr()

    if (this.dictList != null && this.dictList.length != 0) {
      this.apply_typeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_type'
      }).dictDate
      this.incidenttypeOptions = this.dictList.find(item => {
        return item.dictType == 'incidenttype'
      }).dictDate
      this.payee_occupationOptions = this.dictList.find(item => {
        return item.dictType == 'payee_occupation'
      }).dictDate
      this.diseasetypeOptions = this.dictList.find(item => {
        return item.dictType == 'diseasetyp'
      }).dictDate
      this.risk_codeOptions = this.dictList.find(item => {
        return item.dictType == 'risk_code'
      }).dictDate
      this.risk_levelOptions = this.dictList.find(item => {
        return item.dictType == 'risk_level'
      }).dictDate
      this.disability_codeOptions = this.dictList.find(item => {
        return item.dictType == 'disability_code'
      }).dictDate
      this.deformity_codeOptions = this.dictList.find(item => {
        return item.dictType == 'deformity_code'
      }).dictDate
      this.operation_codeOptions = this.dictList.find(item => {
        return item.dictType == 'operation_code'
      }).dictDate
      this.accident_typeOptions = this.dictList.find(item => {
        return item.dictType == 'accident_type'
      }).dictDate
      this.invoicetypeOptions = this.dictList.find(item => {
        return item.dictType == 'invoicetype'
      }).dictDate
      this.tumormorphologicalcodeOptions = this.dictList.find(item => {
        return item.dictType == 'tumormorphologicalcode'
      }).dictDate
      this.occupationnameOptions = this.dictList.find(item => {
        return item.dictType == 'occupationname'
      }).dictDate
      this.IdentificationresultsOptions = this.dictList.find(item => {
        return item.dictType == 'Identificationresults'
      }).dictDate
    }
  },
  methods: {
    newEventDelete(row,index) {
      if (row.incidentno == '') {
        this.eventInfo.splice(index,1)
        this.eventInfo = [...this.eventInfo]
      } else {
        let params = {}
        params.claimno = this.claimno
        params.incidentno = row.incidentno
        newEventDelete(params).then(res =>{
          if (res.status == '1') {
            this.$message.success('删除成功！')
            this.eventInfo.splice(index,1)
            this.eventInfo = [...this.eventInfo]
          } else {
            this.$message({ message: res.data.errMsg, type: 'error' })
          }
        })
      }
    },
    newSaveEventInfo(row,index) {
      if (row.categorycode && row.categorycode.length == 0) {
        this.$message.warning('请先填写申请类别！')
        return false
      }
      if (row.hospitalcode == '' || row.hospitalcode == undefined || row.hospitalcode == null) {
        this.$message.warning('请先填写医院信息！')
        return false
      }
      if (row.maindiagnosis == '' || row.maindiagnosis == undefined || row.maindiagnosis == null) {
        this.$message.warning('请先填写主要诊断！')
        return false
      }
      let params = {}
      params.claimno  = this.claimno
      params.incidentno = row.incidentno ? row.incidentno : ''
      params.categorycode = row.categorycode
      params.eventdate = row.eventdate
      params.visitdate = row.visitdate
      params.dischargedate = row.dischargedate
      params.incidenttype = row.incidenttype
      params.incident = row.incident
      params.hospitalcode = row.hospitalcode
      params.departmentname = row.departmentname
      params.occurrenceprovince = row.occurrenceprovince
      params.occurrencecity = row.occurrencecity
      params.occurrencedistrict = row.occurrencedistrict
      params.maindiagnosis = row.maindiagnosis
      params.secondarydiagnosis = row.secondarydiagnosis
      params.otherdiagnosis = row.otherdiagnosis ? (row.otherdiagnosis).indexOf(',') !== -1 ? [row.otherdiagnosis] : row.otherdiagnosis : []
      params.tumorcode = row.tumorcode
      newSaveEventInfo(params).then(res =>{
        if (res.status == '1') {
         this.$message.success('保存成功！')
         this.$emit('getEventInfo')
        } else {
          this.$message({message: res.data.errMsg,type:'error'})
        }
      })
    },
    otherdiaChange(val){
      this.$refs.otherdia.query=''
    },
    openedModel() {
      this.baseForm.casediseaselist = [...this.baseForm.casediseaselist]
    },
    changeType(value,index) {
      if (value == '0') {
        this.querysubtype = '4'
      } else if (value == '1') {
        this.querysubtype = '3'
      } else if (value == '2') {
        this.querysubtype = '2'
      } else {
        this.querysubtype = '1'
      }
    },
    // 残疾名称
    remotejibinMethods(query) {
      if (query !== '') {
        this.stateListArr  = []
        const requestData = {
          type: '1',
          subtype: this.querysubtype,
          name: query,
          code: this.querycode,
        }
        getInfoByName(requestData).then(response => {
          if (response.status == '0') {
            if (response.data.errMsg == '请求参数不合法') {
              this.$message({message:'请先选择疾病类型！'})
              return false
            }
          } else {
            this.stateListArr = response.data
          }
        }).catch(error => {
          this.$message.error('查询异常')
        })
      }
    },
    changeName(row,index) {
      this.getProofNameByCode('1',row.diseasecode,this.querysubtype,index)
    },
    focusName(row,index) {
      this.stateListArr = []
    },
    // 根据名称查依据
    getProofNameByCode(type,code,subtype,index) {
      let data = {
        type: type,
        subtype: subtype,
        // name: query,
        code: code
      }
      getProofNameByCode(data).then(res =>{
        this.$set(this.baseForm.casediseaselist[index],'reList',res.data)
        this.baseForm.casediseaselist[index].reList = [...this.baseForm.casediseaselist[index].reList]
        this.$forceUpdate()
      })
    },
    formateAppli: function (val){
      let str = ''
      if(val){
        let arrVal = val.split(',')
        arrVal.map(item => {
          if(/*this.dict.label.apply_type[item]*/this.selectDictLabel(this.apply_typeOptions,item)){
            /*str += '｜'+this.dict.label.apply_type[item]*/
            str += '｜'+this.selectDictLabel(this.apply_typeOptions,item)
          }
        })
        return str.slice(1)
      } else {
        return val
      }
    },
    // 伤残名称
    remoteDisabilityMethods(query) {
      this.disease08 = []
      if (query !== '') {
        const requestData = {
          type: '2',
          subtype: '0',
          name: query,
          code: this.querycode,
        }
        getInfoByName(requestData).then(response => {
          this.disease08 = response.data
        }).catch(error => {
          this.$message.error('查询异常')
        })
      }
    },
    // 残疾名称
    remotecanjiMethods(query) {
      this.disease06 = []
      if (query !== '') {
        const requestData = {
          type: '3',
          subtype: '0',
          name: query,
          code: this.querycode,
        }
        getInfoByName(requestData).then(response => {
          this.disease06 = response.data
        }).catch(error => {
          this.$message.error('查询异常')
        })
      }
    },
    // 手术名称
    remoteshoushuMethods(query) {
      this.disease07 = []
        if (query !== '') {
          const requestData = {
            type: '4',
            subtype: '0',
            name: query,
            code: this.querycode,
          }
          getInfoByName(requestData).then(response => {
            this.disease07 = response.data
          }).catch(error => {
            this.$message.error('查询异常')
          })
        }
    },
    // 意外名称
    remoteyiwaiMethods(query) {
      this.disease02 = []
        if (query !== '') {
          const requestData = {
            type: '5',
            subtype: '0',
            name: query,
            code: this.querycode,
          }
          getInfoByName(requestData).then(response => {
            this.disease02 = response.data
          }).catch(error => {
            this.$message.error('查询异常')
          })
        }
    },
    // 根据code查等级
    getaccident (code,type,subtype,index) {
      let data = {
        type: type,
        subtype: subtype,
        // name: query,
        code: code
      }
      getaccident(data).then(res =>{
        if (type == '2') {
          this.$set(this.baseForm.casedisabilitylist[index], 'disabilitylevel', res.data.grade)
          this.$set(this.baseForm.casedisabilitylist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.casedisabilitylist[index].disabilitylevel = res.data.grade
          // this.baseForm.casedisabilitylist[index].paymentratio = res.data.ratio
        }
        if (type == '3') {
          this.$set(this.baseForm.casedeformitylist[index], 'disabilitylevel', res.data.grade)
          this.$set(this.baseForm.casedeformitylist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.casedeformitylist[index].disabilitylevel = res.data.grade
          // this.baseForm.casedeformitylist[index].paymentratio = res.data.ratio
        }
        if (type == '4') {
          this.$set(this.baseForm.caseoperationlist[index], 'operationlevel', res.data.grade)
          this.$set(this.baseForm.caseoperationlist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.caseoperationlist[index].operationlevel = res.data.grade
          // this.baseForm.caseoperationlist[index].paymentratio = res.data.ratio
        }
      })
    },
    inputdisabilitylevel(index,value) {
      this.baseForm.caseoperationlist[index].operationlevel = value
      this.$set(this.baseForm.caseoperationlist[index],'operationlevel',value)
      this.baseForm.caseoperationlist = [...this.baseForm.caseoperationlist]
      this.$forceUpdate()
    },
    inputdisabilitylevelFlag(index,value) {
      this.baseForm.casedisabilitylist[index].disabilitylevel = value
      this.$set(this.baseForm.casedisabilitylist[index],'disabilitylevel',value)
      this.baseForm.casedisabilitylist = [...this.baseForm.casedisabilitylist]
      this.$forceUpdate()
    },
    changeDisability(value,index) {
     this.getaccident(value,'2','0',index)
    },
    focusDisability(row,index) {
      this.disease08 = []
    },
    changeCasede(value,index) {
     this.getaccident(value,'3','0',index)
    },
    focusCasede(row,index) {
      this.disease06 = []
    },
    changeOperationName(value,index) {
     this.getaccident(value,'4','0',index)
    },
    focusOperationName(row,index) {
      this.disease07 = []
    },
    focusAccidentcode(row,indx) {
      this.disease02 = []
    },
    openDia(){
      if(this.incidentnolist.length===0){
        return this.$message.warning('至少选择一条事件信息！')
      }
      this.dialogVis=true
      this.getInDangerInfo()
    },
    //出险信息信息
    getInDangerInfo(){
      let {claimno} = this
      incidentnolist = []
      this.incidentnolist.map(item => {
        incidentnolist.push(item.incidentno)
      })
      getInDangerInfo({claimno, incidentnolist}).then(response => {
        if (response.status == "1") {
          for(let key in this.baseForm){
            response.data[key]=response.data[key]===null?[]:response.data[key]
            if(key==='caseaccidentlist'){
              response.data.casediseaselist.map((item, i) => {
                item.editFlag = false
                response.data.casediseaselist[i].judgmentbasis = response.data.casediseaselist[i].judgmentbasis.split(',')
                // type,code,subtype
                let params = {
                  '0': '4',
                  '1':'3',
                  '2':'2',
                  '3':'1'
                }
                let subtype = params[response.data.casediseaselist[i].diseasetype]
                response.data.casediseaselist[i].judgmentbasis.map(list=> {
                  this.reList = []
                  let subcode = response.data.casediseaselist[i].judgmentbasis.join(',')
                  let requetdata = {
                    type: '1',
                    subtype: subtype,
                    code: response.data.casediseaselist[i].diseasecode,
                    subcode: subcode
                  }
                  getProofNameBySubCode(requetdata).then(res =>{
                    // this.reList = res.data
                    let arr = res.data
                    var list = []
                    arr.map((item,index) =>{
                      response.data.casediseaselist[i].judgmentbasisName
                      ?response.data.casediseaselist[i].judgmentbasisName
                      :response.data.casediseaselist[i].judgmentbasisName=''
                      response.data.casediseaselist[i].judgmentbasisName += ',' + item.proofname
                      list.push(item.proofname)
                    })
                    response.data.casediseaselist[i].judgmentbasis = response.data.casediseaselist[i].judgmentbasis
                    response.data.casediseaselist[i].judgmentbasisName = response.data.casediseaselist[i].judgmentbasisName.slice(1)
                    response.data.casediseaselist[i].judgmentbasisNameList = list
                  })
                  response.data.casediseaselist = [...response.data.casediseaselist]
                })
              })
            }
          }
          this.baseForm = response.data
        } else {
          this.$message({message: '查询出险信息错误！', type: 'error'});
        }
      })
    },
    handleSelectChange(val){
      let list = []
      val.map(item =>{
        list.push(item.incidentno)
      })
      this.incidentnolist = val
      if (val.length !== 0) {
        bus.$emit('updateaccidentInfo',list)
      } else {
        bus.$emit('updateaccidentdata',{casediseaselist: [],casedisabilitylist: [],casedeformitylist: [],caseoperationlist:[],caseaccidentlist:[]})
      }
      // bus.$emit('updateaccidentInfo',list)
      bus.$emit('getEventNo',list)
      bus.$emit('updateInvoiceInfo',list)
    },
    remoteHospitals(query) {
      this.hospitals = []
      if (query !== '') {
        const requestData = {
          hospitalName: query,
          oldHospitals: []
        }
        getHospitalLike(requestData).then(response => {
          this.hospitals = response.data
        }).catch(error => {
          this.loading = false
          this.$message.error('查询医院列表异常')
        })
      }
    },
    remoteICD(query) {
      this.ICDArr = []
      if (query !== '') {
        getLikeICD10(query).then(response => {
          this.ICDArr = response.data
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    secondarydiagnosisICD(query) {
      this.secondarydiagnosisICDArr = []
      if (query !== '') {
        getLikeICD10(query).then(response => {
          this.secondarydiagnosisICDArr = response.data
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    otherdiagnosisICD(query) {
      this.otherdiagnosisICDArr = []
      if (query !== '') {
        getLikeICD10(query).then(response => {
          this.otherdiagnosisICDArr = response.data
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    changeHospital(value) {
      this.getHospitallevel(value)
    },
    getHospitallevel(value) {
      getFirstMedicalInstitution(value).then(res =>{
        if(res.data.hospitalGradeName==='其他'&&res.data.hospitalLevelName==='其他'){
          this.addForm.hospitallevel = res.data.hospitalGradeName
          // this.$set(this.addForm,'address', [res.data.zoneProvinceCode,res.data.zoneCityCode,res.data.zoneCountyCode])
        } else {
          this.addForm.hospitallevel = res.data.hospitalGradeName + res.data.hospitalLevelName
          // this.$set(this.addForm,'address', [res.data.zoneProvinceCode,res.data.zoneCityCode,res.data.zoneCountyCode])
        }
      })
    },
    invoiceHandle(row, status) {
      if(this.incidentnolist.length!==1){
        this.$message({message: '请选择一条事件记录！', type: 'warning'})
      } else {
        let data = encodeURI(
          JSON.stringify({
            claimNo: encrypt(this.claimno),
            incidentno: this.incidentnolist[0].incidentno ,
            invoiceno: '',
            node: 'input',
            status: 'edit',
          })
        )
        const newpage = this.$router.resolve({
          name: 'newinputdetail',
          params:{},
          query:{
            data
          }
        })
        window.open(newpage.href, '_blank');
      }
    },
    openTransDia(row){
      this.transDiaVis = true
      this.transParams = row
    },
    transDiaClose(){
      this.$refs.transForm.resetFields()
      this.transDiaVis = false
    },
    transformHandle() {
      this.$refs.transForm.validate(valid => {
        if(valid){
          let params = {
            claimno: this.claimno,
            currentincidentno: this.transParams.incidentno,
            incidentno: this.transForm.incidentno,
            invoiceno: this.transParams.invoiceno
          }
          saveEventRelationBills(params).then(response => {
            if (response.status === '1') {
              this.$message({ message: '操作成功！', type: 'success' })
              this.transDiaClose()
              this.$emit('relationEvent')
            } else {
              this.$message({ message: '操作失败！', type: 'error' })
            }
          })
        } else {
          return false
        }
      })
    },
    openPop() {
      this.visible = true
    },
    // 获取地址信息
    getAddress() {
      this.address = this.getAddressByFront()
    },
    otherdiagnosisICDByCode(query,index) {
      if (query !== '' && query!== null) {
        getICD10ByCode(query).then(response => {
          if (index == '1') {
            if(response.data){
              // this.ICDArr = []
              this.ICDArr.push(response.data)
            }
          } else if (index == '2') {
            if(response.data){
              // this.secondarydiagnosisICDArr = []
              this.secondarydiagnosisICDArr.push(response.data)
            }
          } else {
            if(response.data){
              // this.otherdiagnosisICDArr = []
              this.otherdiagnosisICDArr.push(response.data)
            }
          }
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    otherdiagnosisHostpByCode(query) {
      if (query !== '') {
        let code =  query
        getHosptailByCode(code).then(response => {
          this.hospitals = []
          this.hospitals.push(response.data)
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    openHandle(){
      if(!this.address.length){
        this.getAddress()
      }
    },
    getTumormorphologicalcode(row,col) {
      /*return this.dict.label.tumormorphologicalcode[row.tumorcode]*/
      return this.selectDictLabel(this.tumormorphologicalcodeOptions, row.tumorcode)
    },
    gettumormorphologicalcodeArr() {
      /*this.tumormorphologicalcodeArr = this.dict.tumormorphologicalcode || []*/
      this.tumormorphologicalcodeArr = this.tumormorphologicalcodeOptions || []
    },
    // 查询依据bycode
    getProofNameBySubCode() {},
    // 模态框方法
    editHandle(idx, row, no) {
      if(no === 0){
        this.eventInfo[idx].id = ''
        this.$set(this.eventInfo[idx],'incidenttype',row.incidenttype == null ? '1' : row.incidenttype)
        this.eventInfo[idx].categorycode = row.categorycode.split(',')
        this.eventInfo[idx].otherdiagnosis = row.otherdiagnosis.split(',')
        this.ICDArr = row.icdarr
        this.secondarydiagnosisICDArr = row.secondarydiagnosisICDArr
        this.otherdiagnosisICDArr = row.otherdiagnosisICDArr
        this.otherdiagnosisHostpByCode(row.hospitalcode)
        if (row.visitdate == '' || row.dischargedate == '') {
          this.$set(this.eventInfo[idx], 'daterange', [])
        } else {
          this.$set(this.eventInfo[idx], 'daterange', [row.visitdate, row.dischargedate])
        }
      } else if (no === 1) {
        this.baseForm.casediseaselist[idx].editFlag = true
        let params = {
          '0': '4',
          '1':'3',
          '2':'2',
          '3':'1'
        }
        let subtype = params[row.diseasetype]
        let item = this.baseForm.casediseaselist[idx]
        let data = {
          type: '1',
          subtype: subtype,
          name: '',
          code: item.diseasecode,
        }
        let subcode = item.judgmentbasis.join(',')
        let requetdata = {
          type: '1',
          subtype: subtype,
          code: item.diseasecode,
          subcode: subcode
        }
        this.$nextTick(() => {
          this.baseForm.casediseaselist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.stateListArr.push(res.data)
          this.stateListArr.map((item) =>{
            return item.value = item.code
          })
        })
        getProofNameBySubCode(requetdata).then(res =>{
          row.reList = res.data
          row.reList = row.reList.map((item) =>{
            item.name = item.proofname
            item.value = item.proofnameId
            return item
          })
        })
      } else if (no === 2) {
        let item = this.baseForm.casedisabilitylist[idx]
        let data = {
          type: '2',
          subtype: '0',
          name: '',
          code: item.disabilitycode,
        }
        this.$nextTick(() => {
          this.baseForm.casedisabilitylist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease08.push(res.data)
          this.disease08.map((item) =>{
            return item.value = item.code
          })
        })
      } else if (no === 3) {
        let item = this.baseForm.caseoperationlist[idx]
        let data = {
          type: '4',
          subtype: '0',
          name: '',
          code: item.operationcode,
        }
        this.$nextTick(() => {
          this.baseForm.caseoperationlist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease07.push(res.data)
          this.disease07.map((item) =>{
            return item.value = item.code
          })
        })
      } else if (no== 5) {
        let item = this.baseForm.casedeformitylist[idx]
        let data = {
          type: '3',
          subtype: '0',
          name: '',
          code: item.disabilitycode,
        }
        this.$nextTick(() => {
          this.baseForm.casedeformitylist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease06.push(res.data)
          this.disease06.map((item) =>{
            return item.value = item.code
          })
        })
      } else {
        let item = this.baseForm.caseaccidentlist[idx]
        let data = {
          type: '5',
          subtype: '0',
          name: '',
          code: item.accidentcode,
        }
        this.$nextTick(() => {
          this.baseForm.caseaccidentlist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease02.push(res.data)
          this.disease02.map((item) =>{
            return item.value = item.code
          })
        })
      }
    },
    delEventHandle(row){
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let params = {
          claimno: this.claimno,
          incidentno: row.incidentno
        }
        deleteEvent(params).then(res => {
          if (res.status === "1") {
            this.getEventInfoList()
            this.$message({ message: '删除成功！', type: 'success' })
          } else {
            this.$message({message: res.data.errMsg? res.data.errMsg:'删除失败！', type: 'error'});
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    addHandel(idx) {
      if (idx === 0) {
        const field = {
          incidentno: '',
          categorycode: '',
          eventdate:  this.accidentdate,
          incidenttype: '1'
        }
        this.eventInfo.push(field)
        setTimeout(() => {
          this.$refs.table.setCurrentRow(field)
        }, 10)
      } else if (idx === 1) {
        this.baseForm.casediseaselist.push(field)
        setTimeout(() => { this.$refs.table1.setCurrentRow(field) }, 10)
      } else if (idx === 2) {
        this.baseForm.casedisabilitylist.push(field)
        setTimeout(() => { this.$refs.table2.setCurrentRow(field) }, 10)
      } else if (idx === 3) {
        this.baseForm.caseoperationlist.push(field)
        setTimeout(() => { this.$refs.table3.setCurrentRow(field) }, 10)
      }  else if (idx === 4) {
        this.baseForm.caseaccidentlist.push(field)
        setTimeout(() => { this.$refs.table4.setCurrentRow(field) }, 10)
      } else {
        this.baseForm.casedeformitylist.push(field)
        setTimeout(() => { this.$refs.table5.setCurrentRow(field) }, 10)
      }
    },
    delHandle(idx, no, row) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(!row.creator) {
          if (no === 1) {
            this.baseForm.casediseaselist.splice(idx, 1)
          } else if (no === 2) {
            this.baseForm.casedisabilitylist.splice(idx, 1)
          } else if (no === 3) {
            this.baseForm.caseoperationlist.splice(idx, 1)
          } else if (no === 4){
            this.baseForm.caseaccidentlist.splice(idx, 1)
          } else {
            this.baseForm.casedeformitylist.splice(idx, 1)
          }
        } else {
          let params = {
            claimno: this.claimno,
            incidentno: incidentnolist,
          }
          if (no === 1) {
            params.key = row.diseasetype
            params.diseasecode = row.diseasecode
            params.type = '01'
          } else if (no === 2) {
            params.key = row.disabilitycode
            params.type = '02'
          } else if (no === 3) {
            params.key = row.operationcode
            params.type = '04'
          } else if (no === 4){
            params.key = row.accidentcode
            params.type = '05'
          } else {
            params.key = row.disabilitycode
            params.type = '03'
          }
          deleteDangerInfo(params).then(res => {
            if (res.status === "1") {
              this.$message({ message: '操作成功！', type: 'success' })
              this.getInDangerInfo()
            } else {
              this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: 'error'});
            }
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    allSaveHandle(idx) {
      if (idx === 1) {
        this.$refs.baseForm.validate(valid => {
          if (valid) {
            let params = this.baseForm
            let keys = Object.keys(params)
            keys.map(item => {
              params[item].map((list, i)=> {
                params[item][i].incidentno = incidentnolist
                params[item][i].claimno = this.claimno
              })
            })
            params.claimno = this.claimno
            saveDangerInfo(params).then(res => {
              if (res.status === "1") {
                this.$message({message: res.data.errMsg? res.data.errMsg:'操作成功！', type: 'success'})
                this.getInDangerInfo()
                this.$emit('relationEvent')
                this.riskDiaClosed()
              } else {
                this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: 'error'})
              }
            }).finally(res => {
              delete params.claimno
            })
          } else {
            setTimeout(()=>{
              let errDoms= document.getElementsByClassName("is-error")
              errDoms[0].querySelector('input')?
              errDoms[0].querySelector('input').focus():
              errDoms[0].querySelector('select').focus()
            },500)
            return false
          }
        })
      }
    },
    changeTumorcode(value) {
      // let flag = value.some((item) =>{
      //   return item == '04'
      // })
      // if (flag) {
      //   this.tumorcodeFlag = true
      //   this.addFormRules.tumorcode.required = true
      // } else {
      //    this.tumorcodeFlag = false
      //    this.addFormRules.tumorcode.required = false
      // }
    },
    saveEvent() {
      this.$refs.addForm.validate(valid => {
        if(valid){
          let params = {...this.addForm}
          if(params.daterange && params.daterange.length){
            params.visitdate = params.daterange[0]
            params.dischargedate = params.daterange[1]
          }
          if(params.address.length){
            params.occurrenceprovince = params.address[0]
            params.occurrencecity = params.address[1]
            params.occurrencedistrict = params.address[2]
          }
          if(this.title==="新增事件信息"){
            params.incidentno = null
          }
          params.claimno = this.claimno
          if(!params.otherdiagnosis){
            params.otherdiagnosis=[]
          }
          delete params.daterange
          delete params.address
          this.saveEventLoading=true
          saveEventInfo(params).then(res => {
            if (res.status === "1") {
              this.$message({message: '操作成功！', type: 'success'})
              this.addForm.daterange=[]
              this.eventDiaClosed()
              this.$emit('relationEvent')
            } else {
              this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: 'error'})
            }
          }).finally(() => {
            this.saveEventLoading=false
          })
        } else {
          return
        }
      })
    },
    selHandle(row){
      this.selIncidentno = row.incidentno
      this.incidentnolist.push(row)
    },
    invoiceSelHandle(row){
      this.listBill = row
      let list = []
      list.push(row)
      this.flag = list.length ? true : false
      this.selInvoiceno = row.invoiceno
    },
    riskDiaClosed() {
      this.$refs.baseForm.resetFields()
      this.baseForm.casediseaselist = [],
      this.baseForm.casedisabilitylist = [],
      this.baseForm.casedeformitylist = [],
      this.baseForm.caseoperationlist = [],
      this.baseForm.caseaccidentlist = []
      incidentnolist.length = 0
      this.dialogVis = false
    },
    eventDiaClosed(){
      this.$refs.addForm.resetFields()
      this.addEventDia = false
    },
    // 发票类型
    getInvoicetype(row,col) {
      /*return this.dict.label.invoicetype[row.invoicetype]*/
      return this.selectDictLabel(this.invoicetypeOptions, row.invoicetype)
    },
  }
}
</script>
<style scoped>
.item-width {
  width: 180px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10%;
}
.el-table /deep/ .el-table__expanded-cell {
  padding: 20px;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
.el-radio /deep/ .el-radio__label {
  display: none;
}
.list_span {
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.overEslap_class{
  color: #555;
  font-size: 12px;
  width:50%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.incident_class {
  color: #555;
  font-size: 12px;
  width:100%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.el-select-dropdown__item {
  max-width: 800px;
  height: auto;
  white-space: pre-wrap;
  word-break: break-word;
}
/* /deep/ .el-select__tags-text {
  min-width: 30px;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
} */
</style>
<style>
.el-select-dropdown el-popper is-multiple{
  max-width: 800px;
}
</style>
