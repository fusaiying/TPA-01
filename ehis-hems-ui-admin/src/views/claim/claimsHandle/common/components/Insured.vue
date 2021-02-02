
<template>
  <el-card class="box-card" style="margin-top: 10px;" id="insureed">
    <div slot="header" class="clearfix">
      <span>被保人信息</span>
      <span style="float: right;" >
        <el-button :disabled="disableFlag || acceptFlag" :loading="loading" type="primary" size="mini" @click="saveHandle">保存</el-button>
      </span>
    </div>
    <el-form ref="baseForm" :rules="tableFormRules" :disabled="node == 'accept' && status == 'show'" :model="baseForm" style="padding-bottom: 30px;" label-width="170px" size="mini" class="baseInfo_class">
      <el-row>
        <el-col :span="8">
          <el-form-item label="客户号：" prop="claimAmount">
            <el-input v-model="baseForm.insuredNo" readonly class="item-width" clearable size="mini" placeholder="请输入">
              <!-- <el-button slot="append" icon="el-icon-search"></el-button> -->
            </el-input>
            <!-- <el-button
              style="margin-left: 5px; position: absolute;"
              type="primary"
              icon="el-icon-search"
              :disabled="disableFlag || acceptFlag"
              circle
              @click="policySearch"/> -->
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="姓名：" prop="insuredName">
            <el-input v-model="baseForm.insuredName" readonly class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：" prop="idType" >
            <el-select v-model="baseForm.idType" disabled class="item-width" placeholder="请选择" >
              <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="证件号码：" prop="idNo">
            <el-input v-model="baseForm.idNo" readonly class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：" prop="sex">
            <el-select v-model="baseForm.sex" disabled class="item-width" placeholder="请选择">
              <el-option v-for="option in sexOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：" prop="birthDate">
            <el-date-picker
              v-model="baseForm.birthdate"
              readonly
              style="width: 215px;"
              type="date"
              placeholder="选择日期"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="职业：" prop="occupation">
            <el-select v-model="baseForm.occupation" filterable :disabled="disableFlag || acceptFlag" class="item-width" placeholder="请选择">
              <el-option v-for="option in payee_occupationOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="国籍：" prop="nationality"
          :rules="tableFormRules.nationality">
            <el-select v-model="baseForm.nationality" :disabled="disableFlag || acceptFlag" class="item-width" placeholder="请选择">
              <el-option v-for="option in nativeplaceOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="证件有效起止日期：" prop="dateRange">
            <el-date-picker
              :disabled="baseForm.checked === true || (disableFlag || acceptFlag)"
              v-model="baseForm.dateRange"
              style="width: 215px;"
              type="daterange"
              range-separator="~"
              start-placeholder="开始日期"
              end-placeholder="结束日期"/>
              <!-- <el-date-picker
              v-model="baseForm.dateRange"
              :disabled="baseForm.checked === true || (disableFlag || acceptFlag)"
              type="datetimerange"
              align="right"
              style="width: 215px;"
              format="yyyy-MM-dd"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              > -->
<!--            </el-date-picker>-->
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item style="padding-left: 72px;"  label-width="0" prop="checked">
            <el-checkbox v-model="baseForm.checked" @change="rulesFlag" :disabled="disableFlag || acceptFlag || (baseForm.dateRange !==null)">长期</el-checkbox>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          v-for="(phone, index) in baseForm.phones"
          :span="8"
          :key="phone.key"
          style="position: relative;">
          <!-- <el-form-item label="手机号码：" prop="mobie">
            <el-input class="item-width" clearable v-model="baseForm.mobie" size="mini" placeholder="请输入"/>
          </el-form-item> -->
          <el-form-item
            :label="index===0?'手机号码':'手机号码' + index"
            :prop="'phones.' + index + '.value'"
            :rules="tableFormRules.checkMobie">
            <!-- :rules="{
              required: true, message: '域名不能为空', trigger: 'blur'
            }" -->
            <!-- <el-input class="item-width" clearable v-model="baseForm.mobie" size="mini" placeholder="请输入"/> -->
            <el-input v-model="phone.value" :readonly="disableFlag || acceptFlag" class="item-width" size="mini" clearable placeholder="请输入"/>
          </el-form-item>
          <el-button
            v-if="index===0 && node!=='report'"
            style="position: absolute; top: 0; right: 8px;"
            size="mini"
            type="primary"
            icon="el-icon-plus"
            circle
            @click="addPhone"/>
          <el-button
            v-if="index!==0"
            style="position: absolute; top: 0; right: 8px;"
            size="mini"
            type="primary"
            icon="el-icon-minus"
            circle
            @click="removePhone(phone)"/>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话：" prop="phone">
            <el-input v-model="baseForm.phone" :readonly="disableFlag || acceptFlag" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱：" prop="email">
            <el-input v-model="baseForm.email"  :readonly="disableFlag || acceptFlag" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="地址：" prop="area">
            <el-cascader
              v-model="baseForm.area"
              :options="regions"
              class="item-width"
              clearable
              filterable
              :disabled="disableFlag || acceptFlag"
              @change="handleChange"/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="详细地址：" prop="address">
            <el-input v-model="baseForm.address" :readonly="disableFlag || acceptFlag" show-word-limit maxlength="100" style="width: 98%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col v-if="showFlag && node !== 'report'" :span="24" style="margin-bottom: 10px;">
          <el-form-item label="被保人标签：">
            <el-tag
              v-for="item in labelList"
              :key="item.labelno"
              :type="item.labeltype==='01'?'success':
                        item.labeltype==='02'?'danger':'warning'"
              size="small"
            >
             <el-tooltip class="item" effect="dark" :content="item.labelcontent" placement="top-start">
                <span>{{getLabel_type(item.labeltype) }}</span>
             </el-tooltip>
            </el-tag>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="node!=='report'" label="是否收件：" prop="isReceipt">
            <el-radio-group v-model="baseForm.isReceipt" :disabled="(disableFlag || acceptFlag) ||  node == 'input'">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="baseForm.isReceipt === '01'">
        <el-col :span="8">
          <el-form-item label="收件日期：" prop="receivedDate">
            <el-date-picker
              v-model="baseForm.receivedDate"
              :disabled="node === 'input'"
              style="width: 215px;"
              type="date"
              placeholder="选择日期"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="发票张数：" prop="billCount">
            <!-- <el-input v-model="baseForm.billCount" class="item-width" clearable size="mini" placeholder="请输入"/> -->
            <el-input-number v-model="baseForm.billCount" :disabled="node === 'input'" :min="0" :step="1" step-strictly class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="发票总额：" prop="billAmount">
            <el-input v-model.number="baseForm.billAmount" :disabled="node === 'input'" class="item-width" clearable size="mini" placeholder="请输入">
              <el-button slot="append" @click="openDia" :disabled="node =='input'">
              <svg class="svg-icon" style="width: 20px; height: 20px; margin-top: 6px;">
                <use xlink:href="#icon-cacl" />
              </svg>
            </el-button>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="baseForm.isReceipt === '01'">
        <el-col :span="8">
          <el-form-item label="快递单号：" prop="trackingNumber">
            <el-input v-model="baseForm.trackingNumber" :disabled="node === 'input'" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="结算单/第三方原件张数：" prop="otherQuantity">
            <el-input-number v-model="baseForm.otherQuantity" :disabled="node === 'input'" :min="0" :step="1" step-strictly class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="baseForm.isReceipt === '01'">
        <el-col :span="16">
          <el-form-item label="备注：" prop="remarks">
            <el-input v-model="baseForm.remarks" :disabled="node === 'input'" type="textarea" :autosize="{ minRows: 3, maxRows: 4}" maxlength="500" show-word-limit style="width: 96%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-dialog
      :visible.sync="dialogTableVis"
      :before-close="closeHandle"
      :close-on-click-modal="false"
      width="30%"
      title="发票金额"
      class="acount-class"
      append-to-body
      @open="setForm">
      <el-form ref="diaForm" :model="diaForm" label-width="0">
        <div ref="diaTable" style="max-height: 370px; overflow-y: scroll;" >
          <el-table ref="table" :data="diaForm.tableData">
            <el-table-column type="index" label="序号" min-width="100px" align="center"/>
            <el-table-column label="发票金额" prop="acount" min-width="200px" align="center">
              <template slot-scope="scope">
                <el-form-item
                  v-if="scope.row.editFlag"
                  :prop="'tableData.' + scope.$index + '.acount'"
                  :rules=" { required: true, message: '不能为空'}">
                  <el-input-number v-model="scope.row.acount" :min="0" :precision="2" :step="0.1" style="width: 180px" placeholder="请输入" size="mini"/>
                </el-form-item>
                <span v-else>{{ scope.row.count }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" min-width="60px" align="center">
              <template slot-scope="scope">
                <el-button style="margin:8px 0px;" type="text" size="mini" @click="delate(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel"> + 添加</el-button>
      </el-form>
      <p>发票总金额：{{ sumCount }}</p>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="dialogTableVis = false">返 回</el-button>
        <el-button size="mini" @click="restSumCount">清空</el-button>
        <el-button type="primary" size="mini" @click="caclHandle">确 定</el-button>
      </div>
    </el-dialog>
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
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="props.row.riskList"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column align="center" prop="riskName" label="险种名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyName" label="责任名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="amount" label="责任保额" show-overflow-tooltip/>
              <el-table-column align="center" prop="surplusAmount" label="剩余保额" show-overflow-tooltip/>
              <el-table-column align="center" prop="getLimit" label="责任免赔额" show-overflow-tooltip/>
              <el-table-column align="center" prop="surplusGetLimit" label="剩余免赔额" show-overflow-tooltip/>
              <el-table-column label="给付责任编码" width="130" align="center">
                <template slot-scope="scope">
                  <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                </template>
              </el-table-column>
              <el-table-column label="给付责任名称" width="180" align="center">
                <template slot-scope="scope">
                  <P class="duty-p" v-for="item in scope.row.getDutyList" :key="item.getDutyCode" :title="item.getDutyName">{{item.getDutyName}}</P>
                </template>
              </el-table-column>
              <el-table-column label="给付责任类型" width="130" align="center">
                <template slot-scope="scope">
                  <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyKind===null?'':getDuty_kind(item.getDutyKind.trim())}}</P>
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" property="contNo" label="保单号" show-overflow-tooltip/>
        <el-table-column align="center" property="riskInfo" label="险种名称" show-overflow-tooltip/>
        <el-table-column align="center" property="effectiveDate" label="有效日期" width="200" show-overflow-tooltip/>
        <el-table-column :formatter="getCNStatus" align="center" property="status" label="保单状态" show-overflow-tooltip/>
        <el-table-column align="center" property="special" label="特约信息" show-overflow-tooltip/>
        <el-table-column align="center" prop="isRecuperation" label="是否复效" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-popover
              v-if="scope.row.isRecuperation === '是'"
              placement="right"
              width="400"
              trigger="click">
              <el-table
                :data="[scope.row]"
                :header-cell-style="{color:'#555', fontWeight: '400'}"
                size="mini">
                <el-table-column property="policyNo" label="复效日期"/>
                <el-table-column property="name" label="失效日期"/>
                <el-table-column property="address" label="复效健康告知"/>
              </el-table>
              <el-button slot="reference" type="text" size="mini">
                {{ scope.row.isRecuperation }}
                <i style="color: #E6A23C;font-size: 14px;" class="el-icon-warning-outline"/>
              </el-button>
            </el-popover>
            <span v-if="scope.row.isRecuperation === '否'">{{ scope.row.isRecuperation }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" property="isRenewal" label="是否续保" show-overflow-tooltip/>
        <el-table-column align="center" property="socialInsurance" label="社保投保" show-overflow-tooltip/>
        <el-table-column label="操作" width="120" align="center">
          <template slot-scope="scope">
            <!-- <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="preview(scope.row)">预览</el-button> -->
            <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="download(scope.row)">下载</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <!-- 保单信息查询模态框 -->
    <insured-modal v-model="dialogPolicy" :dictList="dictList" :node="node" @infoShow="insuredInfoShow" @lightShow="lightListDeal"/>
  </el-card>
</template>
<script>
import insuredModal from '../modul/insured'
import { getAddress, insuredInfoSave, judgeClaim,des3Encode,judgeCustomer } from '@/api/claim/register'
import getAddressByFront from '../mixins/address'
import { validEmail, validPhone } from '@/utils/validate'
import Bus from '../../bus/bus'
export default {
  components: {
    insuredModal
  },
  mixins: [getAddressByFront],
  props: {
    dictList: Array,
    status: String,
    node: String,
    registerNo: String,
    claimno: String,
    acceptFlag: {
      type: Boolean,
      default: false
    }
  },
  //dicts: ['card_type', 'sex', 'policy_status', 'nativeplace', 'payee_occupation', 'get_duty_kind','label_type'],
  data() {
    const validateAmount = (rule, value, callback) => {
      if (value !== '' && value !== null && value !== undefined) {
        if (isNaN(parseFloat(value)) || parseFloat(value) < 0) {
          callback(new Error('录入金额有误，请重新确认'))
        } else {
          callback()
        }
      }else{
        callback()
      }
    }
    let validateEmail = (rule, value, callback) => {
      if(value !== '' && value !== null) {
      if(!validEmail(value)){
        callback(new Error('输入格式不正确'))
      } else {
        callback()
      }
      }else{
        callback()
      }
    }
    let checkPhone = (rule, value, callback) => {
        if(value !== '' && value !== null) {
          let str = value.replace(/\s/g, "")
          if (!validPhone(str)) {
            callback(new Error('输入格式不正确'))
          } else {
            callback()
          }
        }else{
          callback()
        }
    }
    let checkValid = (rule, value, callback) => {
        if(value) {
          let str = value.replace(/\s/g, "")
          if (!validPhone(str)) {
            if(!/\d{3}-\d{8}|\d{4}-\d{7,8}/.test(str)){
              callback(new Error('输入格式不正确'))
            } else {
              callback()
            }
          } else {
            callback()
          }
        }else{
          callback()
        }
    }
    let checkValid2 = (rule, value, callback) => {
      if(value) {
        let str = value.replace(/(^\s*)|(\s*$)/g, "")
        if (str.indexOf(" ") !== -1) {
          callback(new Error('输入格式不正确'))
        } else {
          callback()
        }
      }else{
        callback(new Error('不能为空！'))
      }
    }
    return {
      dialogPolicy: false,
      showFlag: false,
      searchForm: {},
      tableFormRules: {
        email: { validator: validateEmail, trigger: 'blur' },
        phone: { validator: checkValid, trigger: 'blur' },
        checkMobie: { validator: checkPhone, trigger: 'blur' },
        dateRange: { required: false, message: '不能为空!', trigger: 'change' },
        isReceipt: {required: true, message: '不能为空!', trigger: 'change' },
        nationality: { required: true, message: '不能为空!', trigger: 'change' },
        receivedDate: { required: true, message: '不能为空!', trigger: 'change' },
        billCount: { required: false, message: '不能为空!', trigger: 'blur' },
        billAmount: { validator: validateAmount, required: false, trigger: ['blur', 'change'] },
        trackingNumber: { required: true,validator: checkValid2, trigger: 'change' }
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
      baseForm: {
        nation: '1',
        validDate: '',
        phone: '',
        email: '',
        checked: false,
        isReceipt: '01',
        phones: [{
          value: ''
        }]
      },
      tableData: [],
      insuredAndPolicy: [],
      lightList: [],
      saveform: {
        tableData: []
      },
      card_typeOptions: [],
      sexOptions: [],
      policy_statusOptions: [],
      nativeplaceOptions: [],
      payee_occupationOptions: [],
      get_duty_kindOptions: [],
      label_typeOptions: [],
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
  mounted() {

    if(this.node === 'report') {
      this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
    } else {
      this.tableFormRules.dateRange = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
    }
    this.getAddressInfo()
    //dicts: ['card_type', 'sex', 'policy_status', 'nativeplace', 'payee_occupation', 'get_duty_kind','label_type'],
    if (this.dictList!=null && this.dictList.length!=0) {
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType == 'card_type'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType == 'sex'
      }).dictDate
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
      this.nativeplaceOptions = this.dictList.find(item => {
        return item.dictType == 'nativeplace'
      }).dictDate
      this.payee_occupationOptions = this.dictList.find(item => {
        return item.dictType == 'payee_occupation'
      }).dictDate
      this.get_duty_kindOptions = this.dictList.find(item => {
        return item.dictType == 'get_duty_kind'
      }).dictDate
      this.label_typeOptions = this.dictList.find(item => {
        return item.dictType == 'label_type'
      }).dictDate
    }
  },
  created() {
    Bus.$on('insuredInfoShow',data =>{
      this.baseForm = data[0].insuredForm
      if((this.baseForm.dateRange === null) && this.node !== 'report') {
        this.tableFormRules.dateRange = { required: true, message: '不能为空!', trigger: ['blur', 'change']}
      } else {
        this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: ['blur', 'change']}
      }
      if (data[0].insuredForm.contectNumber !== null) {
        this.baseForm.phones = [{ value: data[0].insuredForm.contectNumber }]
      } else {
        this.baseForm.phones = [{ value: '' }]
      }
      this.baseForm.checked = false
      this.tableData = []
      this.insuredAndPolicy = data
      data.forEach(item => this.tableData.push(item.policyShow))
      this.dealData(this.tableData)
    })
    Bus.$on('lightListDeal',data =>{
      this.lightList = data
    })

  },
  methods: {
    tableRowClassName({row, rowIndex}) {
      if(this.lightList.includes(row.contNo)) {
        return 'success-row'
      }
    },
    policySearch() {
      this.dialogPolicy = true
    },
    initForm() {
      this.$nextTick(function() {
        this.$set(this.baseForm, 'insuredName', this.informationOfTheInsured.insuredName)
        this.$set(this.baseForm, 'sex', this.informationOfTheInsured.sex)
        this.$set(this.baseForm, 'idType', this.informationOfTheInsured.cardType)
        this.$set(this.baseForm, 'idNo', this.informationOfTheInsured.idNo)
        this.$set(this.baseForm, 'birthdate', this.informationOfTheInsured.birthday)
        // this.$set(this.baseForm, 'checked', this.informationOfTheInsured.validSign)
        this.$set(this.baseForm, 'checked', false)
        // if (this.baseForm.checked) {
        //   // this.baseFormRules.dateRange =  { required: false, message: '不能为空!', trigger: 'change' }
        //   this.baseFormRules.startDate =  { required: false, message: '不能为空!', trigger: 'change' }
        //   this.baseFormRules.endDate =  { required: false, message: '不能为空!', trigger: 'change' }
        // } else {
        if (this.baseForm.idType === '0') {
          this.baseFormRules.startDate =  { required: true, message: '不能为空!', trigger: 'change' }
          this.baseFormRules.endDate =  { required: true, message: '不能为空!', trigger: 'change' }
        } else {
          this.baseFormRules.startDate =  { required: false, message: '不能为空!', trigger: 'change' }
          this.baseFormRules.endDate =  { required: false, message: '不能为空!', trigger: 'change' }
        }
        // }
        if (this.informationOfTheInsured.dateRange) {
          // this.$set(this.baseForm, 'dateRange', this.informationOfTheInsured.dateRange)
          this.$set(this.baseForm, 'startDate', this.informationOfTheInsured.dateRange[0])
          this.$set(this.baseForm, 'endDate', this.informationOfTheInsured.dateRange[1])
        }
      })
    },
    rulesFlag(value) {
      if (this.node == 'report') {
        if (value == true) {
          this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
          this.baseForm.dateRange = null
        } else {
          this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
        }
      } else {
        if (value == true) {
          this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
          this.baseForm.dateRange = null
          this.$refs.baseForm.validate((valid) => {})
        } else {
          this.tableFormRules.dateRange = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
        }
      }
    },
    addPhone() {
      this.baseForm.phones.push({
        value: '',
        key: Date.now()
      })
    },
    removePhone(item) {
      var index = this.baseForm.phones.indexOf(item)
      if (index !== -1) {
        this.baseForm.phones.splice(index, 1)
      }
    },
    handleChange() {},
    preview() {},
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
    saveHandle() {
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          if (this.baseForm.insuredNo === undefined || this.baseForm.insuredNo === null || this.baseForm.insuredNo === '') {
            this.$message({ message: '被保人信息为空，请选择！', type: 'warning' })
            return false
          }
          this.loading = true
          if(this.node !== 'accept') {
            judgeCustomer(this.baseForm).then(res => {
              if(res.status === '1') {
                if(res.data) {
                  this.$confirm('当前理赔系统已存在当前被保人相似客户，请确认！', '温馨提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }).then(() => {
                    this.insuredInfoSave()
                  }).catch(error => {
                  })
                }else{
                  this.insuredInfoSave()
                }
              }
            }).finally(() => {
              this.loading = false
            })
          } else {
            const requestData = {
              insuredNo:this.baseForm.insuredNo,
              claimNo:this.claimno === undefined? '': this.claimno
            }
            judgeClaim(requestData).then(res => {
              if(res.status === '1') {
                if(res.data.flag) {
                  this.$confirm(res.data.tips, '温馨提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }).then(() => {
                    judgeCustomer(this.baseForm).then(res => {
                      if(res.status === '1') {
                        if(res.data) {
                          this.$confirm('当前理赔系统已存在当前被保人相似客户，请确认！', '温馨提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                          }).then(() => {
                            this.insuredInfoSave()
                          }).catch(error => {
                          })
                        }else{
                          this.insuredInfoSave()
                        }
                      }
                    })
                  }).catch(error => {
                  })
                } else {
                  judgeCustomer(this.baseForm).then(res => {
                    if(res.status === '1') {
                      if(res.data) {
                        this.$confirm('当前理赔系统已存在当前被保人相似客户，请确认！', '温馨提示', {
                          confirmButtonText: '确定',
                          cancelButtonText: '取消',
                          type: 'warning'
                        }).then(() => {
                          this.insuredInfoSave()
                        }).catch(error => {
                        })
                      }else{
                        this.insuredInfoSave()
                      }
                    }
                  })
                }
              } else {
                this.$message({ message: '校验异常！', type: 'error' })
              }
            }).finally(() => {
              this.loading = false
            })
          }
        } else {
          return false
        }
      })
    },
    insuredInfoSave() {
      this.loading = true
      let flag
      if (this.node === 'report') {
        flag = '01'
      } else if (this.node === 'accept') {
        flag = '02'
      } else {
        flag = '03'
      }
      let businessNo
      if (this.node === 'report') {
        businessNo = this.registerNo
      } else {
        businessNo = this.claimno
      }
      const phones = []
      this.baseForm.phones.forEach(item => phones.push(item.value))
      const requestData = {
        flag: flag,
        relationNo: businessNo,
        insuredInfo: this.baseForm,
        pitchList: this.lightList,
        insuredAndPolicy: this.insuredAndPolicy
      }
      requestData.insuredInfo.mobiles = phones
      insuredInfoSave(requestData).then(res => {
        if (res.status === '1') {
          if (flag === '01') {
            this.$emit('registerNoUpdate', res.data.businessNo)
            this.$emit('registerSourceUpdate', res.data.source)
          } else {
            this.$emit('claimNoUpdate', res.data.businessNo)
            this.$emit('applySourceUpdate', res.data.source)
          }
          this.$emit('insuredNoUpdate', this.baseForm.insuredNo)
          this.$emit('insuredInfoUpdate', requestData.insuredInfo)
          this.$emit('insuredSaveUpdate', true)
          this.$emit('pastSurvey',this.baseForm.insuredNo)
          this.$emit('pastNegotiation',this.baseForm.insuredNo)
          this.$emit('getPastClaimsList',this.baseForm.insuredNo)
          this.$emit('updateReportFlag',true)
          this.$message({ message: '被保人信息保存成功！', type: 'success' })
        } else {
          this.$message({ message: '被保人落库失败！', type: 'error' })
          this.loading = false
        }
      }).finally(() => {
        this.loading = false
      })
    },
    insuredInfoShow(data) {
      this.baseForm = data[0].insuredForm
      if(this.baseForm.dateRange === null) {
        this.tableFormRules.dateRange = { required: true, message: '不能为空!', trigger: ['blur', 'change']}
      }
      if (data[0].insuredForm.contectNumber !== null) {
        this.baseForm.phones = [{ value: data[0].insuredForm.contectNumber }]
      } else {
        this.baseForm.phones = [{ value: '' }]
      }
      this.baseForm.checked = false
      this.tableData = []
      this.insuredAndPolicy = data
      data.forEach(item => this.tableData.push(item.policyShow))
      this.dealData(this.tableData)
    },
    lightListDeal(data) {
      this.lightList = data
    },
    dealData(data) {
      if(this.lightList.length > 0) {
        for(let i=0;i<data.length;i++) {
          if(this.lightList.includes(data[i].contNo)) {
            const item = data[i]
            data.splice(i, 1);
            data.unshift(item)
          }
        }
      }
    },
    getCNStatus(row, col) {
      //return this.dict.label.policy_status[row.status]
      return this.selectDictLabel(this.policy_statusOptions, row.status)
    },
    // 地址下拉选
    getAddressInfo() {
      // getAddress().then(response => {
      //   if (response.status === '1') {
      //     this.regions = response.data
      //   } else {
      //     this.$message({ message: '地址下拉数据初始化失败！', type: 'error' })
      //   }
      // }).catch(error => {
      // })
      this.regions = this.getAddressByFront()
    },
    delate(index,row){
      this.diaForm.tableData.splice(index, 1)
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
      setTimeout(() => { this.$refs.table.setCurrentRow(field) }, 10)
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
    getDuty_kind(value){
      return this.selectDictLabel(this.get_duty_kindOptions, value)
    },
    getLabel_type(value){
      return this.selectDictLabel(this.label_typeOptions, value)
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 215px;
}
.duty-p {
  width: 170px;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.el-table ::v-deep .warning-row {
  background: oldlace;
}

.el-table ::v-deep .success-row {
  background: rgba(135, 206, 250, .8);
}
::v-deep .el-dialog__body {
  padding: 5px 20px 0px 20px!important;
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

[v-cloak]{

  　　display:none

}
</style>
<style>
.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}
.el-tooltip__popper{
  max-width: 50%;
}
::v-deep .el-date-range-picker__editors-wrap :nth-child(2n) {
  display: none;
}
