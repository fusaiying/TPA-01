<template>
  <el-card class="box-card" style="margin-top: 10px;" id="insureed">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;被保人信息</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;被保人信息</i>
        <span style="float: right;">
         <el-button v-if="status==='edit' && node==='accept'" type="primary" :disabled="!collapsed" size="mini"
                    @click="policySearch">查询</el-button>
         <el-button v-if="status==='edit' && node==='accept'" type="primary" :disabled="!collapsed" size="mini"
                    @click="saveHandle">保存</el-button>
      </span>
      </div>

    </div>
    <el-form v-show="collapsed" ref="baseForm" :rules="tableFormRules"
             :disabled="(node === 'accept' && status === 'show')||node==='input' || node==='calculateReview' || node==='sport' "
             :model="baseForm"
             style="padding-bottom: 30px;" label-width="150px" size="mini" class="baseInfo_class">
      <el-row>
        <el-col :span="8">
          <el-form-item label="客户号：" prop="insuredNo">
            <el-input v-model="baseForm.insuredNo" class="item-width" clearable
                      size="mini" disabled/>
            <!--            <span class="size">{{ baseForm.insuredNo }}</span>-->
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="姓名：" prop="name">
            <span class="size">{{ baseForm.name }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item disabled label="证件类型：" prop="idType">
            <span class="size">{{ selectDictLabel(card_typeOptions, baseForm.idType) }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="证件号码：" prop="idNo">
            <span class="size">{{ baseForm.idNo }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：" prop="sex">
            <span class="size">{{ selectDictLabel(rgtSexOptions, baseForm.sex) }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：" prop="birthday">
            <span class="size">{{ baseForm.birthday }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="职业：" prop="occupation">
            <el-select v-model="baseForm.occupation" filterable :disabled="disableFlag" class="item-width" clearable
                       placeholder="请选择">
              <el-option v-for="option in occupationOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="国籍：" prop="nationality"
                        :rules="tableFormRules.nationality">
            <el-select v-model="baseForm.nationality" :disabled="disableFlag" class="item-width" clearable
                       placeholder="请选择">
              <el-option v-for="option in nativeplaceOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="证件有效日期：" prop="dateRange">
            <el-date-picker
              :disabled="baseForm.checked === true || (disableFlag)"
              v-model="baseForm.dateRange"
              style="width: 215px;"
              type="daterange"
              range-separator="~"
              start-placeholder="开始日期"
              end-placeholder="结束日期"/>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item style="padding-left: 62px;" label-width="0" prop="checked">
            <el-checkbox v-model="baseForm.checked" @change="rulesFlag">长期</el-checkbox><!--9999-12-31-->
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="手机号码：" prop="mobile">
            <el-input v-model="baseForm.mobile" class="item-width" clearable
                      size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱：" prop="email">
            <el-input v-model="baseForm.email" class="item-width" clearable
                      size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话：" prop="phone">
            <el-input v-model="baseForm.phone" class="item-width" clearable
                      size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="详细地址：">
            <el-cascader
              v-model="region"
              :options="regions"
              class="item-width"
              placeholder="请选择"
              @change="handleChange"
              clearable/>
            <el-input v-model="baseForm.address" :readonly="disableFlag" show-word-limit maxlength="400"
                      style="width:61%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-table
          ref="table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          tooltip-effect="dark"
          style="width: 100%;"
          :row-class-name="tableRowClassName">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table
                :ref="'table'+props.$index"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                :data="props.row.minData"
                size="small"
                v-loading="minLoading"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">
                <el-table-column align="center" prop="riskCode" label="险种编码" show-overflow-tooltip/>
                <el-table-column align="center" prop="riskName" label="险种名称" show-overflow-tooltip/>
                <el-table-column label="责任编码" align="center" prop="dutyCode">
                  <!--                      <template slot-scope="scope">
                                          <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                                        </template>-->
                </el-table-column>
                <el-table-column label="责任名称" align="center" prop="dutyName">
                  <!--                      <template slot-scope="scope">
                                          <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                                        </template>-->
                </el-table-column>
                <el-table-column label="责任明细编码" align="center" prop="dutyDetailCode">
                  <!--                      <template slot-scope="scope">
                                          <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                                        </template>-->
                </el-table-column>
                <el-table-column label="责任明细名称" align="center" prop="dutyDetailName">
                  <!--                      <template slot-scope="scope">
                                          <P class="duty-p" v-for="item in scope.row.getDutyList" :key="item.getDutyCode"
                                             :title="item.getDutyName">{{ item.getDutyName }}</P>
                                        </template>-->
                </el-table-column>
                <el-table-column label="申请原因" align="center" prop="applyReason">
                  <!--                      <template slot-scope="scope">
                                          <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">
                                            {{ item.getDutyKind === null ? '' : getDuty_kind(item.getDutyKind.trim()) }}</P>
                                        </template>-->
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyItemNo" label="分单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="name" label="被保人" show-overflow-tooltip/>
          <el-table-column align="center" prop="appName" label="投保人" show-overflow-tooltip/>
          <el-table-column align="center" label="有效日期" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row">{{ scope.row.validStartDate }}~{{ scope.row.validEndDate }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="policyRiskType" label="保单险类" :formatter="getPolicyRiskType" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyStatus" label="保单状态" :formatter="getPolicy_status"
                           show-overflow-tooltip/>
          <el-table-column align="center" prop="specialAgreement" label="特约信息" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyType" label="保单类型" :formatter="getPolicy_type"
                           show-overflow-tooltip/>
          <el-table-column align="center" prop="ssFlag" label="社保标记" :formatter="getSocialinsurance1"
                           show-overflow-tooltip/>
          <!--          <el-table-column label="操作" width="120" align="center">
                      <template slot-scope="scope">
                        <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="preview(scope.row)">预览</el-button>
                        <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="download(scope.row)">下载
                        </el-button>
                      </template>
                    </el-table-column>-->
        </el-table>
      </el-row>
    </el-form>
    <!-- 保单信息查询模态框 -->
    <insured-modal :value="dialogPolicy" :dictList="dictList" :node="node" @infoShow="insuredInfoShow"
                   @getPropData="getPropData" :fixInfo="fixInfo"
                   @lightShow="lightListDeal" @closeDialogVisable="closeDialogVisable"/>
  </el-card>
</template>
<script>
  import insuredModal from '../modul/insured'
  import {validEmail, validPhone} from '@/utils/validate'
  import Bus from '../../bus/bus'
  import {getAddress} from "@/api/baseInfo/medicalManage";


  import {addInsuredAndPolicy} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'rgtSex'}, {dictType: 'card_type'}, {dictType: 'insurance_type'}, {dictType: 'policy_status'}, {dictType: 'policy_type'}, {dictType: 'socialinsurance1'}]
  export default {
    components: {
      insuredModal
    },
    mixins: [],
    props: {
      fixInfo: {
        type: Object,
        default: function () {
          return {}
        }
      },
      status: String,
      node: String,
      sonInsuredData: {
        type: Object,
        default: function () {
          return {}
        }
      },
    },
    watch: {
      fixInfo: function (newVal) {
        this.copyFixInfo = newVal
      },
      sonInsuredData: {
        handler: function (newVal) {
          this.init()
        },
        deep: true,

      },
      /*      sonInsuredData: function (newVal){
              if (newVal!==null && newVal!==undefined){
                this.baseForm=newVal.claimCaseInsured
                this.tableData=newVal.policyInfominData
                this.region.push(this.baseForm.province)
                this.region.push(this.baseForm.city)
                this.region.push(this.baseForm.district)
              }
            }*/
    },

    data() {
      const validateAmount = (rule, value, callback) => {
        if (value !== '' && value !== null && value !== undefined) {
          if (isNaN(parseFloat(value)) || parseFloat(value) < 0) {
            callback(new Error('录入金额有误，请重新确认'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      let validateEmail = (rule, value, callback) => {
        if (value) {
          if (!validEmail(value)) {
            callback(new Error('邮箱格式不合法，请检查'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      let checkPhone = (rule, value, callback) => {
        if (value) {
          let str = value.replace(/\s/g, "")
          if (!validPhone(str)) {
            callback(new Error('手机号码格式不合法，请检查'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      let checkValid = (rule, value, callback) => {
        if (value) {
          let str = value.replace(/\s/g, "")
          if (!validPhone(str)) {
            if (!/\d{3}-\d{8}|\d{4}-\d{7,8}/.test(str)) {
              callback(new Error('电话号码格式不合法，请检查'))
            } else {
              callback()
            }
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkDateRange = (rule, value, callback) => {
        if(this.baseForm.checked){
          callback()
        }
        else{
          if(this.baseForm.dateRange!=null && this.baseForm.dateRange.length==2){

            if(this.baseForm.dateRange[0] !=null && this.baseForm.dateRange[0]!='' && this.baseForm.dateRange[1]!=null && this.baseForm.dateRange[1]!=''){
              callback()
            }
            else {

              callback(new Error('证件有效期不能为空!'))
            }
          }
          else {

            callback(new Error('证件有效期不能为空!'))
          }
        }
      }
      let checkValid2 = (rule, value, callback) => {
        /* if (value) {
           let str = value.replace(/(^\s*)|(\s*$)/g, "")
           if (str.indexOf(" ") !== -1) {
             callback(new Error('输入格式不正确'))
           } else {
             callback()
           }
         } else {
           callback(new Error('不能为空！'))
         }*/
      }

      return {
        hasInsuredId:false,
        isInsuredSave: false,
        copyFixInfo: {},
        minLoading: false,
        collapsed: true,
        dialogPolicy: false,
        showFlag: false,
        searchForm: {},
        tableFormRules: {
          insuredNo: {required: true, message: '客户号不能为空!', trigger: 'blur'},
          name: {required: true, message: '姓名不能为空!', trigger: 'blur'},
          idType: {required: true, message: '证件类型不能为空!', trigger: 'blur'},
          idNo: {required: true, message: '证件号码不能为空!', trigger: 'blur'},
          sex: {required: true, message: '性别不能为空!', trigger: 'blur'},
          birthday: {required: true, message: '出生日期不能为空!', trigger: 'blur'},
         // dateRange: {required: true,validator: checkDateRange,  trigger: ['blur','change']},

          email: {validator: validateEmail, trigger: 'blur'},
          phone: {validator: checkValid, trigger: 'blur'},
          mobile: {validator: checkPhone, trigger: 'blur'},

          /* isReceipt: {required: true, message: '不能为空!', trigger: 'change'},
           nationality: {required: true, message: '不能为空!', trigger: 'change'},
           receivedDate: {required: true, message: '不能为空!', trigger: 'change'},
           billCount: {required: false, message: '不能为空!', trigger: 'blur'},
           billAmount: {validator: validateAmount, required: false, trigger: ['blur', 'change']},
           trackingNumber: {required: true, validator: checkValid2, trigger: 'change'}*/
        },
        labelType: {
          '01': 'VIP',
          '02': '爱投诉',
          '03': '高风险'
        },
        labelList: [],
        disableFlag: false,
        loading: false,
        dialogTableVis: false,
        diaForm: {
          tableData: []
        },
        regions: [], // 省市区下拉选项
        region: [], // 省市区下拉选项
        baseForm: {
          insuredNo: undefined,
          name: undefined,
          idType: undefined,
          idNo: undefined,
          sex: undefined,
          birthday: undefined,
          occupation: undefined,
          nationality: undefined,
          dateRange: [], //
          idStartDate: '',
          idEndDate: undefined,
          mobile: undefined,
          province: undefined,
          city: undefined,
          district: undefined,
          email: undefined,
          phone: undefined,
          address:undefined,
          checked: false, //
        },

        tableData: [],
        insuredAndPolicy: [],
        lightList: [],
        saveform: {
          tableData: []
        },
        dictList: [],
        rgtSexOptions: [],
        card_typeOptions: [],
        insurance_typeOptions: [],
        policy_statusOptions: [],
        policy_typeOptions: [],
        socialinsurance1Options: [],
        occupationOptions: [{dictLabel: '老师', dictValue: '01'}, {dictLabel: '销售', dictValue: '02'}],
        nativeplaceOptions: [{dictLabel: '中国', dictValue: '01'}, {dictLabel: '韩国', dictValue: '02'}],
      }
    },
    computed: {
      sumCount() {
        let count = 0
        this.diaForm.tableData.map(item => {
          item.acount ? count += item.acount : 0
        })
        count = count.toFixed(2)
        return count
      }

    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType === 'card_type'
      }).dictDate
      this.rgtSexOptions = this.dictList.find(item => {
        return item.dictType === 'rgtSex'
      }).dictDate
      this.insurance_typeOptions = this.dictList.find(item => {
        return item.dictType === 'insurance_type'
      }).dictDate
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType === 'policy_status'
      }).dictDate
      this.policy_typeOptions = this.dictList.find(item => {
        return item.dictType === 'policy_type'
      }).dictDate
      this.socialinsurance1Options = this.dictList.find(item => {
        return item.dictType === 'socialinsurance1'
      }).dictDate


      /*      if (this.node === 'report') {
              this.tableFormRules.dateRange = {required: false, message: '证件有效期不能为空!', trigger: ['blur', 'change']}
            } else {
              this.tableFormRules.dateRange = {required: true, message: '证件有效期不能为空!', trigger: ['blur', 'change']}
            }*/
      this.getAddressData()
    },
    created() {

    },
    methods: {
      init() {
        this.getAddressData()
        const subFormSearch = JSON.parse(JSON.stringify(this.sonInsuredData))

        let baseFormData = subFormSearch.claimCaseInsured
//this.baseForm.checked=false
        this.$set(this.baseForm, 'checked', false)
        this.baseForm.insuredNo = baseFormData.insuredNo
        this.baseForm.name = baseFormData.name
        this.baseForm.idType = baseFormData.idType
        this.baseForm.idNo = baseFormData.idNo
        this.baseForm.sex = baseFormData.sex
        this.baseForm.birthday = baseFormData.birthday
        this.baseForm.occupation = baseFormData.occupation
        this.baseForm.nationality = baseFormData.nationality
        this.baseForm.idStartDate = baseFormData.idStartDate
        this.baseForm.idEndDate = baseFormData.idEndDate
        this.baseForm.mobile = baseFormData.mobile
        this.baseForm.province = baseFormData.province
        this.baseForm.city = baseFormData.city
        this.baseForm.district = baseFormData.district
        this.baseForm.phone = baseFormData.phone
        this.baseForm.email = baseFormData.email
        this.baseForm.address = baseFormData.address
        this.baseForm.dateRange = []


        /* this.baseForm.dateRange[0] =  this.baseForm.idStartDate
      this.baseForm.dateRange[1] =  this.baseForm.idStartDate*/

        //this.baseForm.idEndDate = '9999-12-31'
        if (this.baseForm.idEndDate == '9999-12-31') {
          this.$set(this.baseForm, 'checked', true)
          this.baseForm.dateRange = []
        } else {
          this.$set(this.baseForm, 'checked', false)
          this.baseForm.dateRange[0] = this.baseForm.idStartDate
          this.baseForm.dateRange[1] = this.baseForm.idEndDate
        }


        this.region = []
        this.region[0] = this.baseForm.province
        this.region[1] = this.baseForm.city
        this.region[2] = this.baseForm.district
        this.tableData = subFormSearch.policyInfominData

        /*    this.$nextTick(() =>{

            })*/

        //别删  通过是否有id来判断是否保存过
        if (this.baseForm.rptNo!==null && this.baseForm.rptNo!=='' ){
          this.hasInsuredId=true
          this.$emit('emitSaveFlag')
        }
      },
      getPropData(val) {
        //调用保存的接口


        const subFormSearch = JSON.parse(JSON.stringify(val))
        let baseFormData = subFormSearch.caseInsuredData


        this.baseForm.insuredNo=baseFormData.insuredNo
        this.baseForm.name=baseFormData.name
        this.baseForm.idType=baseFormData.idType
        this.baseForm.idNo=baseFormData.idNo
        this.baseForm.sex=baseFormData.sex
        this.baseForm.birthday=baseFormData.birthday
        this.baseForm.occupation=baseFormData.occupation
        this.baseForm.nationality=baseFormData.nationality
        this.baseForm.idStartDate=baseFormData.idStartDate
        this.baseForm.idEndDate=baseFormData.idEndDate
        this.baseForm.mobile=baseFormData.mobile
        this.baseForm.province=baseFormData.province
        this.baseForm.city=baseFormData.city
        this.baseForm.district=baseFormData.district
        this.baseForm.phone=baseFormData.phone
        this.baseForm.email=baseFormData.email
        this.baseForm.address=baseFormData.address
        this.baseForm.dateRange = []

        if (this.baseForm.idEndDate =='9999-12-31') {
          this.$set(this.baseForm, 'checked', true)
          this.baseForm.dateRange = []
        }
        else {
          this.$set(this.baseForm, 'checked', false)

          if(baseFormData.idStartDate!=null && baseFormData.idStartDate!=''){

            this.baseForm.dateRange[0] =  baseFormData.idStartDate

          }
          else {

            this.baseForm.dateRange[0] = undefined
          }
          if(baseFormData.idEndDate!=null && baseFormData.idEndDate!=''){
            this.baseForm.dateRange[1] =  this.baseForm.idEndDate
          }
          else {
            this.baseForm.dateRange[1] = undefined
          }
        }


        this.region = []
        this.region[0] = this.baseForm.province
        this.region[1] = this.baseForm.city
        this.region[2] = this.baseForm.district


        /* this.baseForm.region=this.region*/
        /*  this.baseForm.region[1]=subFormSearch.caseInsuredData.city
          this.baseForm.region[2]=subFormSearch.caseInsuredData.district*/

        this.tableData = subFormSearch.policyInfoData
        this.isInsuredSave = true
        this.$emit('emitSaveFlag')

      },

      closeDialogVisable() {
        this.dialogPolicy = false
      },


      tableRowClassName({row, rowIndex}) {
        if (this.lightList.includes(row.contNo)) {
          return 'success-row'
        }
      },
      policySearch() {

        this.dialogPolicy = true
      },

      rulesFlag() {


        if (this.baseForm.checked) {
          this.baseForm.dateRange = []
          /*this.baseForm.idEndDate = '9999-12-31'*/
          this.$set(this.baseForm,'idEndDate','9999-12-31')
          this.baseForm.idStartDate=''
          /* this.baseForm.dateRange[0] = ''
           this.baseForm.dateRange[1] = '9999-12-31'*/
        } else {
          this.baseForm.dateRange = []
          this.baseForm.idEndDate = ''
          this.baseForm.idStartDate=''
        }



        /* if (this.node === 'report') {
           if (value === true) {
             this.tableFormRules.dateRange = {required: false, message: '不能为空!', trigger: ['blur', 'change']}
             this.baseForm.dateRange = null
           } else {
             this.tableFormRules.dateRange = {required: false, message: '不能为空!', trigger: ['blur', 'change']}
           }
         } else {
           if (value === true) {
             this.tableFormRules.dateRange = {required: false, message: '不能为空!', trigger: ['blur', 'change']}
             this.baseForm.dateRange = null
          /!*   this.$refs.baseForm.validate((valid) => {
             })*!/
           } else {
             this.tableFormRules.dateRange = {required: true, message: '不能为空!', trigger: ['blur', 'change']}
           }
         }*/
      },


      handleChange() {
        this.baseForm.province = this.region[0]
        this.baseForm.city = this.region[1]
        this.baseForm.district = this.region[2]
      },
      preview() {
      },
      download(row) {
        // const url = 'https://m-health.mypicc.com.cn/toDownload?contNo=' + row.contNo + '&pdfFlag=download&ip=10.130.129.21'
        // window.open(url)
        // 生产
        const requestData = {
          policyNo: row.contNo,
          privateKey: 'piccandthirdpart'
        }
        des3Encode(requestData).then(res => {
          if (res.status === '1') {
            const url = 'http://e-shop.picchealth.com/pdf/checkPDF?contno=' + res.data + '&type=d'
            window.open(url)
          }
        })
      },
      //保存
      saveHandle() {
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            let policyNoList = []

            if (this.tableData.length > 0) {
              this.tableData.forEach(item => {
                policyNoList.push(item.policyNo)
              })
            }
            const subFormSearch = JSON.parse(JSON.stringify(this.baseForm))
            subFormSearch.province = this.region[0]
            subFormSearch.city = this.region[1]
            subFormSearch.district = this.region[2]
            subFormSearch.rptNo = this.copyFixInfo.rptNo
            if(this.baseForm.dateRange.length>=2){
              this.baseForm.idStartDate= this.baseForm.dateRange[0]
              this.baseForm.idEndDate  =this.baseForm.dateRange[1]
            }
            subFormSearch.idStartDate = this.baseForm.idStartDate
            subFormSearch.idEndDate = this.baseForm.idEndDate
            let insuredInfoData = {
              policyNos: this.tableData,
              claimCaseInsured: subFormSearch
            }
            addInsuredAndPolicy(insuredInfoData).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.isInsuredSave = true
                this.$emit('emitSaveFlag')
              } else {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
          } else {
            this.$message.warning('必录项未必录!')
          }


        })

      },
      insuredInfoSave() {

      },
      insuredInfoShow(data) {

      },
      lightListDeal(data) {
        this.lightList = data
      },
      dealData(data) {
        if (this.lightList.length > 0) {
          for (let i = 0; i < data.length; i++) {
            if (this.lightList.includes(data[i].contNo)) {
              const item = data[i]
              data.splice(i, 1);
              data.unshift(item)
            }
          }
        }
      },

      getPolicy_status(row, col) {
        return this.selectDictLabel(this.policy_statusOptions, row.policyStatus)
      },
      getPolicyRiskType(row, col) {
        return this.selectDictLabel(this.insurance_typeOptions, row.policyRiskType)
      },
      getPolicy_type(row, col) {
        return this.selectDictLabel(this.policy_typeOptions, row.policyType)
      },
      getSocialinsurance1(row, col) {
        return this.selectDictLabel(this.socialinsurance1Options, row.ssFlag)
      },
      // 地址下拉选
      getAddressInfo() {

        /*this.regions = this.getAddressByFront()*/
      },
      getAddressData() {
        getAddress().then(response => {
          this.regions = response
        }).catch(error => {
        })
      },

      restSumCount() {
        this.$refs.diaForm.resetFields()
        // this.diaForm.tableData = []
      },
      setForm() {
        if (this.saveform.tableData.length) {
          this.diaForm = JSON.parse(JSON.stringify(this.saveform))
        }
      },
      openDia() {
        this.dialogTableVis = true
      },
      closeHandle() {
        // this.$refs.diaForm.resetFields()
        // this.diaForm.tableData = []
        this.dialogTableVis = false
      },
      addHandel() {
        // 表单字段具体要根据后端定义进行修改
        const field = {
          editFlag: 'add',
          info: ''
        }
        this.diaForm.tableData.push(field)
        setTimeout(() => {
          this.$refs.table.setCurrentRow(field)
        }, 10)
      },
      caclHandle() {
        this.$refs.diaForm.validate((valid) => {
          if (valid) {
            this.saveform = {...this.diaForm}
            let account = 0
            this.diaForm.tableData.map(item => {
              account += item.acount
            })
            this.$set(this.baseForm, 'billAmount', account)
            this.dialogTableVis = false
          } else {
            return false
          }
        })
      },
      getDuty_kind(value) {
        return this.selectDictLabel(this.get_duty_kindOptions, value)
      },
      getLabel_type(value) {
        return this.selectDictLabel(this.label_typeOptions, value)
      },
      //校验
      validataForm(){
        let flag
        let savefalg
          let list = this.$parent.getPayeeInfoData()
         let obj =list.find(item =>{
          return item.relationIns==1
        })
        //领款人中与被保人关系有本人
        if(obj!=null && obj!=''){
          flag=true
        }
        else {
          flag=false
        }
        if(flag){
          if(this.baseForm.nationality && this.baseForm.occupation){
            savefalg=true
          }
          else {
            this.$set(this.tableFormRules,'nationality',{required: true, message: '国籍不能为空!', trigger: ['change','blur']})
            this.$set(this.tableFormRules,'occupation',{required: true, message: '职业不能为空!', trigger: ['change','blur']})
            savefalg=false
          }
        }
        else {
          savefalg=true
        }
        return savefalg
      }
    }
  }
</script>>
<style scoped>
  .item-width {
    width: 215px;
  }

  .duty-p {
    width: 170px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .el-table ::v-deep .warning-row {
    background: oldlace;
  }

  .el-table ::v-deep .success-row {
    background: rgba(135, 206, 250, .8);
  }

  ::v-deep .el-dialog__body {
    padding: 5px 20px 0px 20px !important;
  }

  ::v-deep .el-table__row {
    height: 35px;
    line-height: 35px;
  }

  ::v-deep .el-table td {
    padding: 0px 0px;
  }

  ::v-deep .el-dialog__footer {
    padding: 0px 20px 20px 0px;
  }

  .acount-class ::v-deep .el-form-item {
    margin-bottom: 0;
  }

  [v-cloak] {

    　　display: none

  }

</style>
