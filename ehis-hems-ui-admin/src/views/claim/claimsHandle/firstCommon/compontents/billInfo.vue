<template>
  <el-card style="margin:10px 0">
   <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">账单信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button type="primary" @click="relationEventInvoice" size="mini">关联事件</el-button>
          <!-- <el-button type="primary" size="mini">发票修改</el-button> -->
        </span>
        <el-table
          :data="invoicelistInfo"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          ref="billtable"
          highlight-current-row
          @selection-change="handleSelectChange"
          tooltip-effect="dark">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table
                :data="props.row.invoicedetail"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark">
                <el-table-column property="feetiem" label="费用项名称" align="center"></el-table-column>
                <el-table-column property="amount" label="总金额" align="center">
                  <template slot-scope="scope">
                    <span>{{scope.row.amount|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column property="paycategoryamount" label="比例自付" align="center">
                  <template slot-scope="scope">
                    <span>{{scope.row.paycategoryamount|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column property="ownamount" label="自费金额" align="center">
                  <template slot-scope="scope">
                    <span>{{scope.row.ownamount|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column property="overallpayment" label="统筹支付" align="center">
                  <template slot-scope="scope">
                    <span>{{scope.row.overallpayment|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column property="thirdpartypayment" label="第三方支付" align="center">
                  <template slot-scope="scope">
                    <span>{{scope.row.thirdpartypayment|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column property="unreasonableamount" label="不合理金额" align="center">
                  <template slot-scope="scope">
                    <el-input-number size="mini" v-model="scope.row.unreasonableamount" :min="0" :precision="2" :step="0.01" style="width: 130px"  class="item_width" autocomplete="off"  placeholder="请输入" clearable />
                    <!-- <span>{{scope.row.unreasonableamount|numFilter}}</span> -->
                  </template>
                </el-table-column>
                <el-table-column property="unreasonableexplain" label="不合理原因" align="center">
                  <template slot-scope="scope">
                    <el-input type="textarea"   show-word-limit size="mini" maxlength="500" :autosize="{ minRows: 1, maxRows: 4}" placeholder="请输入内容" v-model="scope.row.unreasonableexplain"></el-input>
                  </template>
                </el-table-column>
                <el-table-column label="操作" align="center" width="120">
                  <template slot-scope="props">
                    <el-button type="text" size="mini" @click="saveInvoiceInfo(props.row,props.$index)">保存</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <!-- <el-table-column align="center" width="50">
            <template slot-scope="scope">
              <el-radio :disabled="selIncidentno!==scope.row.incidentno" v-model="selRadio" :label="scope.$index" @change="val => invoiceSelHandle(scope.row)"></el-radio>
            </template>
          </el-table-column> -->
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column type="index" align="center" label="序号"></el-table-column>
          <el-table-column align="center" width="140" prop="invoiceno" label="发票号" show-overflow-tooltip>
            <template slot-scope="scope" style="display: flex;justify-content: space-between">
              <span v-if="scope.row.pastClaimNo === ''">{{scope.row.invoiceno}}</span>
              <el-tooltip v-else class="item" effect="dark" :content="'重复理赔，既往赔案号' + scope.row.pastClaimNo" placement="top-start">
                <span style="color: red;">
                  <span>{{scope.row.invoiceno}}</span>
                  <span class="el-icon-warning"></span>
                </span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column property="invoicetype" label="发票类型" :formatter="getInvoicetype" align="center"></el-table-column>
          <el-table-column property="hospitalname" label="医院名称" align="center"></el-table-column>
          <el-table-column property="visitdate" label="就诊区间" align="center">
            <template slot-scope="scope">
              {{scope.row.visitdate}} ~ {{scope.row.dischargedate}}
            </template>
          </el-table-column>
          <el-table-column property="zhenduan" label="诊断" align="center">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" :content="scope.row.zhenduan" placement="top-start">
                <p style="width:80px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">{{scope.row.zhenduan}}</p>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column property="amount" label="总金额" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.amount|numFilter}}</span>
            </template>
          </el-table-column>
          <el-table-column property="overallpayment" label="统筹支付" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.overallpayment|numFilter}}</span>
            </template>
          </el-table-column>
          <el-table-column property="thirdpartypayment" label="第三方支付" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.thirdpartypayment|numFilter}}</span>
            </template>
          </el-table-column>
          <el-table-column property="paybycategory" label="比例自付" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.paybycategory|numFilter}}</span>
            </template>
          </el-table-column>
          <el-table-column property="ownamount" label="自费金额" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.ownamount|numFilter}}</span>
            </template>
          </el-table-column>
          <el-table-column property="unreasonableamount" width="160" label="不合理金额" align="center">
            <template slot-scope="scope">
              <!-- <el-input-number size="mini" v-model="scope.row.unreasonableamount" :min="0" :precision="2" :step="0.01" style="width: 150px"  class="item_width" autocomplete="off"  placeholder="请输入" clearable /> -->
              <span>{{scope.row.unreasonableamount|numFilter}}</span>
            </template>
          </el-table-column>
          <!-- <el-table-column property="unreasonableexplain" width="160"  label="不合理原因" align="center">
            <template slot-scope="scope">
              <el-input type="textarea" size="mini" :autosize="{ minRows: 1, maxRows: 4}" placeholder="请输入内容" v-model="scope.row.unreasonableexplain"></el-input>
            </template>
          </el-table-column> -->
          <el-table-column label="操作" align="center" width="100">
            <template slot-scope="props">
              <p><el-button type="text" size="mini" @click="seerr">影像查看</el-button></p>
              <p><el-button type="text" size="mini" @click="updateInvoice(props.row,props.$index)">发票修改</el-button></p>
            </template>
          </el-table-column>
        </el-table>
      </el-collapse-item>
   </el-collapse>
   <!-- <el-button id="btn" @click="initBox">打开弹窗</el-button>
    <el-card v-show="boxShow" class="box-card" id="box" style="position: absolute">
      <div slot="header" class="clearfix" id="tittle">
        <span>卡片名称</span>
        <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
      </div>
      <div v-for="o in 4" :key="o" class="text item">
        {{'列表内容 ' + o }}
      </div>
    </el-card> -->
  </el-card>
</template>
<script>
import {getInDangerInfo, saveDangerInfo, saveEventInfo, deleteDangerInfo, saveEventRelationBills, getFirstMedicalInstitution, getLikeICD10,getICD10ByCode, getHosptailByCode, getInfoByName,getaccident, getProofNameByCode,
  deleteEvent,getProofNameBySubCode,saveInvoiceInfo,relationEventInvoice,saveInvoiceDetailInfo
} from '@/api/claim/handleDeal'
import moment from 'moment'
import Bus from '../../bus/bus'
import {encrypt} from "@/utils/rsaEncrypt"
import { getHospitalLike } from '@/api/claim/input'
import getAddressByFront from "../../common/mixins/address"
/*import { list } from '../../../../api/examine'*/
let incidentnolist = []
export default {
  mixins:[getAddressByFront],
  inject: ['getEventInfoList'],
 // dicts: ['apply_type', 'incidenttype','payee_occupation' ,'diseasetype', 'risk_code', 'risk_level', 'disability_code', 'deformity_code', 'operation_code', 'accident_type','invoicetype','tumormorphologicalcode','occupationname', 'Identificationresults'],
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
    invoicelistInfo: {
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
      incidentnolist: [],
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
      incidentnoList: [],
      hasSelectList: [], // 默认选中
      boxShow: false,

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
    Bus.$on('getEventNo', data =>{
      this.incidentnoList = data
    })
    Bus.$on('updatebilldata', data =>{
      this.invoicelistInfo = data
      this.invoicelistInfo.map((item) =>{
        if (item.ischecked == true) {
          this.hasSelectList.push(item.ischecked)
        }
      })
      this.$nextTick(()=>{
        this.invoicelistInfo.forEach(row => {
          if(this.hasSelectList.indexOf(row.ischecked) >= 0){
            this.$refs.billtable.toggleRowSelection(row,true);
          }
        })
      })
    })

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
  updated () {
  },
  methods: {
    updateInvoice(row,index) {
      let data = encodeURI(
        JSON.stringify({
          claimNo: encrypt(this.claimno),
          // incidentno: this.incidentnoList[0],
          invoiceno: row.invoiceno,
          node: 'input',
          status: 'edit',
          pageid: row.pageid
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
    },
    relationEventInvoice() {
      if (this.incidentnoList.length>1) {
        this.$message.warning("不能选择多个事件！")
        return false
      }
      if (this.incidentnoList == 0) {
        this.$message.warning("请先选择要关联的事件！")
        return false
      }
      if (this.invoicelist && this.invoicelist.length == 0) {
        this.$message.warning("请先选择要关联的发票！")
        return false
      }
      let params = {}
      let relation = []
      this.invoicelist ? this.invoicelist.map(item =>{
        let from  = {}
        from.invoiceno = item.invoiceno
        from.currentincidentno = item.currentincidentno
        relation.push(from)
      }) : []
      params.claimno = this.claimno
      params.incidentno = this.incidentnoList[0]
      params.relation = relation
      relationEventInvoice(params).then(res =>{
        if (res.status == '1') {
          this.$message.success('关联成功！')
        } else {
          this.$message({ message: res.data.errMsg, type: 'error' })
        }
      })
    },
    seerr() {
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
    handleSelectChange(val) {
      let list = []
      val.map(item =>{
        let from = {}
        from.invoiceno = item.invoiceno
        from.currentincidentno = item.incidentno
        list.push(from)
      })
      this.invoicelist = list
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
    formatApplicaReason(row) {
      let reason = ''
      if (row.categorycode) {
        row.categorycode.split(',').map((item, i) => {
          /*this.dict.label.apply_type[item] ? reason += `｜${this.dict.label.apply_type[item]}` : reason = ''*/
          this.selectDictLabel(this.apply_typeOptions,item)? reason += `｜${this.selectDictLabel(this.apply_typeOptions,item)}` : reason = ''
        })
      }
      return reason.slice(1)
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
    saveInvoiceInfo(row,index) {
      if (row.unreasonableamount !== '' || row.unreasonableamount !== undefined || row.unreasonableamount !== null) {
        if (row.unreasonableexplain == '' || row.unreasonableexplain == null || row.unreasonableexplain == undefined) {
          this.$message.warning('请录入不合理原因！')
          return false
        }
      }
      if ((Number(row.overallpayment) + Number(row.thirdpartypayment) + Number(row.unreasonableamount)) > (Number(row.amount))) {
        this.$message({ message: '统筹+第三方+不合理金额总和不能大于总金额', type: 'warning' })
        return false
      }
      let params = {}
      params.claimno = this.claimno
      params.invoiceno = row.invoiceno
      params.detailedno = row.detailedno
      params.unreasonableamount = row.unreasonableamount
      params.unreasonableexplain = row.unreasonableexplain
      saveInvoiceDetailInfo(params).then(res =>{
        if (res.status == '1') {
          this.$message.success('发票保存成功！')
          this.$emit('getInvoicelistInfo')
        } else {
          this.$message.error("更新发票信息错误！")
        }
      })
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
    see(pic) {
       /*图片对象*/
        var imgs = document.getElementsByTagName("img");
        /*前景div*/
        var light   = document.getElementById('light')  || document.createElement("div");
        /*背景div*/
        var bg      = document.getElementById('bg')     || document.createElement("div");
        /*图片放大*/
        var s_pic   = document.getElementById('s_pic')  || document.createElement("img");
        /*css对象*/
        var css     = document.createElement("style");
        /*css样式*/
        var csstext = '\
        .pic_bg{\
            position: absolute;\
            margin:0 auto; \
            top: 0%;\
            left: 0%;\
            right: 0%;\
            width: 100%;\
            padding-bottom: 1000%;\
            background-color: black;\
            z-index:1001;\
            opacity:.80;\
            filter: alpha(opacity=80);\
            overflow:scroll;\
        }\
        .pic_div {\
            margin-bottom: auto;\
            position: fixed;\
            left:30%;\
            top:20%;\
            width: 100%;\
            padding-top:25px;\
            margin-left:-250px;\
            margin-top:-100px;\
            z-index:1002;\
        }';
        /*收集页面所有图片对象*/
        for(var i=0; i<imgs.length;i++){
            imgs[i].setAttribute("onclick", "picHook(this)" );
        }
        css.type = "text/css";

        /*关闭图像*/
        if( !pic ){
            bg.style.display = light.style.display = "none";
        }

        /*ie兼容*/
        if(css.styleSheet){
            css.styleSheet.cssText = csstext;
        }else{
            css.appendChild(document.createTextNode(csstext));
        }

        s_pic.setAttribute("id", "s_pic");
        s_pic.setAttribute("src", pic.src);
        s_pic.setAttribute("width","70%");
        s_pic.setAttribute("height","65%");
        s_pic.setAttribute("margin","0 auto");
        s_pic.style.display = 'block';
        light.setAttribute("id", "light");
        light.setAttribute("class", "pic_div");
        light.style.display = 'block';
        light.appendChild(s_pic);
        light.setAttribute("onclick", "picHook()");
        bg.setAttribute("id", "bg");
        bg.setAttribute("class", "pic_bg");
        bg.setAttribute("onclick", "picHook()");
        bg.style.display = light.style.display;
        document.getElementsByTagName("head")[0].appendChild(css);
        document.body.appendChild(bg);
        document.body.appendChild(light);
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
    // handleSelectChange(val){
    //   this.restList = []
    //   this.accidentReList = []
    //   this.incidentnolist=[]
    //   if(val.length){
    //     this.incidentnolist = val
    //     this.selIncidentno = val[0].incidentno
    //     this.routerList = val
    //     val.map((item) =>{
    //       this.restList.push(item.categorycode)
    //       this.accidentReList.push(item.incidenttype)
    //     })
    //   }
    // },
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
        let invoiceInfo = {}
        let invoiceInfoFrom = {}
        if (this.listBill) {
          invoiceInfoFrom = this.listBill
        }
        invoiceInfo.invoiceNo = invoiceInfoFrom.invoiceno
        invoiceInfo.invoiceType = invoiceInfoFrom.invoicetype
        invoiceInfo.socialSecuritySettlement = invoiceInfoFrom.socialecuritysettlement
        invoiceInfo.amount = invoiceInfoFrom.amount
        invoiceInfo.overallPayment = invoiceInfoFrom.overallpayment
        invoiceInfo.thirdPartyPayment = invoiceInfoFrom.thirdpartypayment
        if (invoiceInfoFrom.visitdate == '' || invoiceInfoFrom.dischargedate == '') {
          invoiceInfo.dateRange  = []
        } else {
          invoiceInfo.dateRange = [invoiceInfoFrom.visitdate,invoiceInfoFrom.dischargedate]
        }
        invoiceInfo.overallPaymentShare = invoiceInfoFrom.overallpaymentshare
        invoiceInfo.cashPayment = invoiceInfoFrom.cashpayment
        invoiceInfo.accountPayment = invoiceInfoFrom.accountpayment
        invoiceInfo.thirdPartyPaymentShare = invoiceInfoFrom.thirdpartypaymentshare
        invoiceInfo.payByCategory = invoiceInfoFrom.paybycategory
        invoiceInfo.selfPayment = invoiceInfoFrom.selfpayment
        invoiceInfo.patient = invoiceInfoFrom.patient
        invoiceInfo.hospital = invoiceInfoFrom.hospitalcode
        invoiceInfo.hospitalname = invoiceInfoFrom.hospitalname
        invoiceInfo.incidentno = invoiceInfoFrom.incidentno
        // let data = {
        //   node: 'input',
        //   status: 'edit',
        //   incidentNo: this.flag == true ? '' : this.selIncidentno,
        //   claimNo: encrypt(this.claimno),
        //   insuredInfo: this.insuredInfo,
        //   invoiceInfo: this.flag == true ? invoiceInfo : ''
        // }
        const newpage = this.$router.resolve({
          name: 'invoiceHandle',
          params:{},
          query:{
            node: 'input',
            status: 'edit',
            incidentNo: this.selIncidentno,
            claimNo: encrypt(this.claimno),
            // insuredInfo: JSON.stringify(this.insuredInfo) ,
            invoiceInfo: this.flag == true ? JSON.stringify(invoiceInfo) : ''
          }
        })
        window.open(newpage.href, '_blank');
        // this.$router.push({
        //   path: '/claims-handle/classificat',
        //   query: {
        //     node: 'input',
        //     status: 'edit',
        //     incidentNo: this.flag == true ? '' : this.selIncidentno,
        //     claimNo: encrypt(this.claimno),
        //     insuredInfo: this.insuredInfo,
        //     invoiceInfo: this.flag == true ? invoiceInfo : ''
        //   }
        // })
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
              this.ICDArr = []
              this.ICDArr.push(response.data)
            }
          } else if (index == '2') {
            if(response.data){
              this.secondarydiagnosisICDArr = []
              this.secondarydiagnosisICDArr.push(response.data)
            }
          } else {
            if(response.data){
              this.otherdiagnosisICDArr = []
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
     /* return this.dict.label.tumormorphologicalcode[row.tumorcode]*/
      return this.selectDictLabel(this.tumormorphologicalcodeOptions, row.tumorcode)
    },
    gettumormorphologicalcodeArr() {
      this.tumormorphologicalcodeArr = this.tumormorphologicalcodeOptions || []
    },
    // 查询依据bycode
    getProofNameBySubCode() {},
    // 模态框方法
    editHandle(idx, row, no) {
      if(no === 0){
        this.title = '编辑事件信息'
        this.otherdiagnosisICDByCode(row.maindiagnosis,'1')
        this.otherdiagnosisICDByCode(row.secondarydiagnosis,'2')
        if(row.otherdiagnosis){
          let otherdiagnos = row.otherdiagnosis.split(',')
          if(otherdiagnos.length){
            otherdiagnos.map(item => {
              this.otherdiagnosisICDByCode(item,'3')
            })
          }
        }
        this.otherdiagnosisHostpByCode(row.hospitalcode)
        this.gettumormorphologicalcodeArr()
        this.$nextTick(() => {
          if(!row.areacode.length){
            row.address=null
          }
          this.addForm = {...row}
          if(this.addForm.hospitallevel=='其他其他'){
            this.$set(this.addForm, 'hospitallevel', '其他')
          }
          if(row.otherdiagnosis){
            this.addForm.otherdiagnosis = row.otherdiagnosis.split(',')
          }
          this.addForm.categorycode = row.categorycode.split(',')
          if (row.visitdate == '' || row.dischargedate == '') {
            this.$set(this.addForm, 'daterange', [])
          } else {
            this.$set(this.addForm, 'daterange', [row.visitdate, row.dischargedate])
          }
          this.addForm.address = row.areacode.length?[row.areacode[0], row.areacode[1], row.areacode[2]]: []
        })
        this.addEventDia = true
        this.$nextTick(()=>{
          this.$refs.addForm.clearValidate()
        })
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
      const field = {
        editFlag: true,
        editFlagT: true,
      }
      if (idx === 0) {
        this.title = '新增事件信息'
        this.addForm = {}
        this.addEventDia = true
        this.$nextTick(()=>{
          this.$refs.addForm.clearValidate()
        })
        this.addForm.eventdate = this.accidentdate
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
          // let flag = this.addForm.categorycode.some((item) =>{
          //   return item == '04'
          // })
          // if (flag && this.addForm.tumorcode == null) {
          //   this.$message({message:'肿瘤形态学名称必填！', type: 'error'})
          //   return false
          // }
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
    initBox() {
      this.boxShow = true
      let oBox = document.getElementById('box')
      // oBox.style.left = document.documentElement.clientWidth / 2 - oBox.offsetWidth / 2 + 'px'
      // oBox.style.top = document.documentElement.clientHeight / 2 - oBox.offsetHeight + 'px'
      this.drag('box')
    },
    drag(id){
      //获取所拖拽的元素
      let ele = document.getElementById(id);
      ele.onmousedown = function(evt){
        let e = evt || window.event;
        let target = e.target || e.srcElement;
        if(target.className === 'el-card__header'){
          let disX = e.offsetX;
          let disY = e.offsetY;
          document.onmousemove = function(evt){
            let e = evt || window.event;
            ele.style.left = e.pageX - disX + 'px';
            ele.style.top = e.pageY - disY + 'px';
          }
          document.onmouseup = function(){
            document.onmousemove = null;
          }
        }
      }
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 220px;
}
.el-collapse {
  border: none;
}
.el-tabs ::v-deep .el-tabs__item {
  width: 10%;
}
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.el-collapse ::v-deep .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
.el-radio ::v-deep .el-radio__label {
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
/* ::v-deep .el-select__tags-text {
  min-width: 30px;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
} */
#dragBox{
  width: 600px;
  height: 600px;
  background: red;
  z-index: 9999;
}
</style>
<style>
.el-select-dropdown el-popper is-multiple{
  max-width: 800px;
}
</style>
