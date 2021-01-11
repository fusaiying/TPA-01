
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>申请人信息</span>
      <span style="float: right;" >
         <el-button type="primary" size="mini" :disabled="disableFlag || acceptFlag" @click="openBeneficDia">受益人信息</el-button>
        <el-button :disabled="disableFlag || acceptFlag" type="primary" :loading="saveHandleLoading" size="mini" @click="saveHandle">保存</el-button>
      </span>
    </div>
    <el-form ref="baseForm" :rules="baseFormRule" :disabled="disableFlag || acceptFlag" :model="baseForm" style="padding-bottom: 30px;" label-width="136px" label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="与被保人关系：" prop="relationship">
            <el-select v-model="baseForm.relationship" class="item-width" placeholder="请选择" @change="changeInfo">
              <el-option v-for="option in relation_ship_applyOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="申请来源：" prop="source">
            <el-select :disabled="true" v-model="source" class="item-width" placeholder="请选择">
              <el-option v-for="option in apply_sourcetypeOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="申请人姓名：" prop="reportName">
            <el-input v-model="baseForm.reportName" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：" prop="cardType">
            <el-select v-model="baseForm.cardType" class="item-width" placeholder="请选择">
              <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码：" prop="idCard">
            <el-input v-model="baseForm.idCard" class="item-width" clearable size="mini" placeholder="请输入" @input="getGender('baseForm')"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：" prop="gender">
            <el-select v-model="baseForm.gender" class="item-width" placeholder="请选择">
              <el-option v-for="option in sexOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话：" prop="phone">
            <el-input v-model="baseForm.phone" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机：" prop="mobile">
            <el-input v-model="baseForm.mobile" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱：" prop="email">
            <el-input v-model="baseForm.email" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地址：" prop="email">
            <el-input v-model="baseForm.address" style="width: 60%" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-row>
      <el-table
        ref="table"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column :formatter="getCNCollect" width="120" label="领款方式" prop="payType" align="center"/>
        <el-table-column :formatter="getCNRelation" width="120" label="与被保人关系" prop="relation" align="center"/>
        <el-table-column width="120" label="领款人" prop="payee" align="center"/>
        <el-table-column width="120" label="手机" prop="mobile" align="center"/>
<!--        <el-table-column width="150" label="银行地区" prop="bankAddress" align="center">-->
<!--          <template slot-scope="scope">-->
<!--            <el-cascader-->
<!--              v-model="scope.row.bankAddress"-->
<!--              :options="newRegions"-->
<!--              size="mini"-->
<!--              disabled-->
<!--              placeholder=""-->
<!--              style="width: 150px;"-->
<!--              class="read-only"/>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column width="120"  label="银行名称" prop="bankName" align="center">
        </el-table-column>
        <el-table-column width="120" label="账号/支付宝号" prop="bankNo" align="center"/>
        <el-table-column width="120" label="分配比例(%)" prop="getRate" align="center"/>
        <!-- *** ⚠️以下字段当申请人和被保人一致时不展示-->
        <el-table-column width="120" :formatter="getCNIdType" label="证件类型" prop="cardType" align="center"/>
        <el-table-column width="120" label="证件号码" prop="idCard" align="center"/>
        <el-table-column width="120" label="证件有效期" prop="dateRange" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.validSign === true">长期</span>
            <span v-else>{{scope.row.dateRange | dateFilter}}</span>
          </template>
        </el-table-column>
        <el-table-column width="120" :formatter="getCNNative" label="国籍" prop="nation" align="center"/>
        <el-table-column width="150" :formatter="getCNOccupation" label="职业" prop="occupation" align="center"/>
        <el-table-column width="150" label="地区" prop="address" align="center">
          <template slot-scope="scope">
            <el-cascader
              v-model="scope.row.address"
              :options="regions"
              size="mini"
              disabled
              placeholder=""
              style="width: 150px;"
              class="read-only"/>
          </template>
        </el-table-column>
        <el-table-column width="120" label="详细地址" prop="detailAddress" align="center"/>
        <el-table-column fixed="right" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-button :disabled="disableFlag || acceptFlag" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button>
            <el-button :disabled="disableFlag || acceptFlag" style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button :disabled="disableFlag || acceptFlag" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel"> + 添加</el-button>
    </el-row>
   <el-dialog :visible.sync="dialogFormVisible"  title="领款信息" width="96%" append-to-body :close-on-click-modal="false">
      <el-form :model="addForm" :rules="tableFormRules" ref="addForm" label-width="140px" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="领款方式：" prop="payType">
              <el-select v-model="addForm.payType" class="item-width" placeholder="请选择" @change="changePayType">
                <el-option v-for="option in collectedmodeOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="与被保人关系：" prop="relation">
              <el-select v-model="addForm.relation" class="item-width" placeholder="请选择" @change="changePayInfo">
                <el-option v-for="option in relation_ship_applyOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="领款人：" prop="payee">
              <el-input v-model="addForm.payee" :disabled="showFlag" class="item-width" clearable autocomplete="off"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="手机号码：" prop="mobile">
              <el-input v-model="addForm.mobile" class="item-width" clearable autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="国籍：" prop="nation">
              <el-select v-model="addForm.nation" class="item-width" placeholder="请选择">
                <el-option v-for="option in nativeplaceOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="证件类型：" prop="cardType">
              <el-select v-model="addForm.cardType" class="item-width" placeholder="请选择">
                <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
        <!-- </el-row>
        <el-row> -->
          <el-col :span="8">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="证件号码：" prop="idCard">
              <el-input v-model="addForm.idCard" class="item-width" clearable maxlength="20" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="性别：" prop="sex">
              <el-select v-model="addForm.sex" class="item-width" placeholder="请选择">
                <el-option v-for="option in sexOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="证件有效起止日期：" prop="dateRange">
              <el-date-picker
                :disabled="addForm.validSign === true"
                v-model="addForm.dateRange"
                style="width: 220px;"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="1">
            <el-form-item  v-if="addForm.relation!== '00' && addForm.relation!== ''"  style="padding-left: 55px;" label-width="0" prop="validSign">
              <el-checkbox :disabled="addForm.dateRange !==null" v-model="addForm.validSign" @change="rulesFlag">长期</el-checkbox>
            </el-form-item>
          </el-col>
        <!-- </el-row>
        <el-row> -->
          <el-col  :span="8"  :class="{'positionClass': (addForm.relation!== '00' && addForm.relation!== '')}">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="职业：" prop="occupation">
              <el-select v-model="addForm.occupation" filterable  class="item-width" placeholder="请选择" >
                <el-option v-for="option in payee_occupationOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8" :class="{'positionsass':addForm.payType!=='01' && addForm.relation== '00', 'positionClass2': (addForm.payType!=='01' && addForm.payType !== '' && addForm.relation !== '00' &&  addForm.relation !== '')}">
            <el-form-item  v-if="addForm.payType!=='01'" label="账号/支付宝号：" prop="bankNo">
              <el-input v-model="addForm.bankNo"  clearable  class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item  v-if="addForm.payType!=='01'" title="确认账号/支付宝号" label="确认账号/支付宝号：" prop="repeatBankNo">
              <el-input v-model="addForm.repeatBankNo" class="item-width" @paste.native.capture.prevent="handlePaste" clearable autocomplete="off"/>
            </el-form-item>
          </el-col>
        <!-- </el-row>
        <el-row> -->
          <el-col  :span="8">
            <el-form-item v-if="addForm.payType!=='01'" title="银行名称" label="银行名称：" prop="bank">
              <el-select v-model="addForm.bank"
                         class="item-width"
                         :remote-method="remoteBanks"
                         :loading="loading"
                         filterable
                         allow-create
                         remote
                         reserve-keyword
                         placeholder="请输入银行关键字"
                         clearable
                         @change="handleChange">
                <el-option v-for="option in bankList" :key="option.id" :label="`${option.value}-${option.label}`" :value="option.value" />
              </el-select>
            </el-form-item>
          </el-col>
<!--          <el-col v-if="addForm.payType!=='01'" :span="8">-->
<!--            <el-form-item label="银行地区：" prop="bankAddress">-->
<!--              <el-cascader-->
<!--                v-model="addForm.bankAddress"-->
<!--                :options="newRegions"-->
<!--                class="item-width"-->
<!--                clearable/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col v-if="addForm.payType!=='01'" :span="8">-->
<!--            <el-form-item label="分支机构：" prop="subBank">-->
<!--              <el-input-->
<!--                v-model="addForm.subBank"-->
<!--                class="item-width"-->
<!--                clearable/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
        <!-- </el-row>
        <el-row> -->
          <el-col :span="8">
            <el-form-item label="分配比例(%)：" prop="getRate" :class="{'positionClass2': (addForm.payType=='01' && addForm.relation!== '00' && addForm.relation!== '')}">
              <el-input-number v-model="addForm.getRate" :min="1" :max="100" :precision="0" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col v-if="addForm.relation!== '00' && addForm.relation!== ''" :span="8">
            <el-form-item label="地址：" prop="address">
              <el-cascader
                v-model="addForm.address"
                :options="regions"
                class="item-width"/>
            </el-form-item>
          </el-col>
        <!-- </el-row>
        <el-row> -->
          <el-col v-if="addForm.relation!== '00' && addForm.relation!== ''" :span="15">
            <el-form-item label="详细地址：" prop="detailAddress">
              <el-input v-model="addForm.detailAddress" clearable size="mini" maxlength="200" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="dialogFormVisible = false">返 回</el-button>
        <el-button size="mini" type="primary" @click="dialogFormConfirm">保 存</el-button>
      </div>
    </el-dialog>
    <beneficiary-mdul v-model="beneficVis" :node="node" :dictList="dictList" :insured-base-info="insuredBaseInfo" :claimno="claimno"></beneficiary-mdul>
  </el-card>
</template>
<script>
import { getAddress, reportSave, getCategoryBank,getHeadBank,getProvince,getCity, getBankName } from '@/api/claim/register'
import { getBankLike }  from '@/api/claim/input'
import getAddressByFront from '../mixins/address'
import moment from 'moment'
import { validEmail, validPhone } from '@/utils/validate'
import beneficiaryMdul from '../modul/beneficiary'
export default {
  mixins: [getAddressByFront],
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: String,
    insuredNo: String,
    insuredSave: Boolean,
    insuredBaseInfo: Object,
    acceptFlag: {
      type: Boolean,
      default: false
    },
    source: String
  },
  components: {
    beneficiaryMdul
  },
  //dicts: ['relation_ship_apply', 'card_type', 'sex', 'apply_way', 'collect_way', 'collectedmode', 'apply_sourcetype', 'nativeplace', 'payee_occupation'],
  filters: {
    dateFilter(value)
    {
      if(value !== null && value.length != 0) {
        return moment(value[0]).format("YYYY-MM-DD") + '~' + moment(value[1]).format("YYYY-MM-DD")
      }
    }
  },
  data() {
    const _self = this;
    const validateEmail = (rule, value, callback) => {
      if (value) {
        let str = value.replace(/\s/g, "")
        if(!validEmail(str)){
          callback(new Error('输入格式不正确'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('不能为空'))
      } else {
        if(value !== '' && value !== null) {
          const str = value.replace(/\s/g, '')
          if (!validPhone(str)) {
            callback(new Error('输入格式不正确'))
          } else {
            callback()
          }
        }else{
          callback()
        }
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
    return {
      beneficVis: false,
      dialogFormVisible: false,
      disableFlag: false,
      showFlag: false,
      tableData: [],
      addForm: {
        payee: '',
        cardType: '',
        idCard: '',
        sex: '',
        mobile: '',
        validSign: false,
        dateRange: [],
        nation: '',
        occupation: '',
        bank: []
      },
      saveHandleLoading: false,
      baseForm: {
        reportName: '',
        cardType: '',
        idCard: '',
        gender: '',
        phone: '',
        mobile: '',
        email: '',
        address: ''
      },
      baseFormRule: {
        relationship: [{ required: true, message: '不能为空!', trigger: 'change' }],
        applyWay: [{ required: true, message: '不能为空!', trigger: 'change' }],
        cardType: [{ required: true, message: '不能为空!', trigger: 'change' }],
        gender: [{ required: true, message: '不能为空!', trigger: 'change' }],
        insuredName: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        reportName: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        idCard: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        email: { validator: validateEmail, trigger: 'blur' },
        phone: { validator: checkValid, trigger: 'blur' },
        mobile: { required: true, validator: checkPhone, trigger: 'blur' }
      },
      tableFormRules: {
        payType: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        relation: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        payee: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        getRate: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        mobile: { required: true, validator: checkPhone, trigger: ['blur', 'change'] },
        sex: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        cardType: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        idCard: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        dateRange: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        nation: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        address: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        detailAddress: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        bankAddress: { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
        bank: { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
        bankNo: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        repeatBankNo: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        occupation: { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
      },
      regions: [],
      newRegions: [],
      bankRegions: [],
      bankList: [],
      loading: false,
      index: 99,
      rowIndex: '',
      rowStatus: '',
      relation_ship_applyOptions:[],
      card_typeOptions:[],
      sexOptions:[],
      apply_wayOptions:[],
      collect_wayOptions:[],
      apply_sourcetypeOptions:[],
      collectedmodeOptions:[],
      nativeplaceOptions:[],
      payee_occupationOptions:[],
    }
  },
  mounted() {
    this.getAddressInfo()
    // this.getBankAddress()
    if (this.dictList!=null && this.dictList.length!=0){
    if (this.dictList!=null && this.dictList.length!=0) {
      this.relation_ship_applyOptions = this.dictList.find(item => {
        return item.dictType == 'relation_ship_apply'
      }).dictDate
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType == 'card_type'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType == 'sex'
      }).dictDate
      this.apply_wayOptions = this.dictList.find(item => {
        return item.dictType == 'apply_way'
      }).dictDate
      this.collect_wayOptions = this.dictList.find(item => {
        return item.dictType == 'collect_way'
      }).dictDate
      this.apply_sourcetypeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_sourcetype'
      }).dictDate
      this.collectedmodeOptions = this.dictList.find(item => {
        return item.dictType == 'collectedmode'
      }).dictDate
      this.nativeplaceOptions = this.dictList.find(item => {
        return item.dictType == 'nativeplace'
      }).dictDate
      this.payee_occupationOptions = this.dictList.find(item => {
        return item.dictType == 'payee_occupation'
      }).dictDate
    }
    }
  },

  methods: {
    openBeneficDia(){
      this.beneficVis=true
    },
    saveHandle() {
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          this.saveHandleLoading = true
          if (this.insuredSave) {
            // if(this.tableData !== null && this.tableData.length > 0) {
              let rate = 0;
              for(let i = 0;i< this.tableData.length;i++) {
                  rate += parseInt(this.tableData[i].getRate)
              }
              // if(rate !== 100) {
              //   this.$message({ message: '所有领款人的分配比例之和应等于100%！', type: 'warning' })
              //   this.saveHandleLoading = false
              // } else  {
                const requestData = {
                  flag: '02',
                  insuredNo: this.insuredNo,
                  businessNo: this.claimno,
                  report: this.baseForm,
                  payeeInfoList: this.tableData
                }
                reportSave(requestData).then(res => {
                  if (res.status === '1') {
                    this.$emit('applicantSaveUpdate', true)
                    this.$message({ message: '申请人信息保存成功！', type: 'success' })
                    this.saveHandleLoading = false
                  } else {
                    this.$message({ message: '数据存储失败！', type: 'error' })
                    this.saveHandleLoading = false
                  }
                })
              // }
            // } else {
            //   this.$message({ message: '请先录入领款人信息！', type: 'warning' })
            //   this.saveHandleLoading = false
            // }
          } else {
            this.$message({ message: '请先保存被保人信息！', type: 'warning' })
            this.saveHandleLoading = false
          }
        } else {
          return false
          this.saveHandleLoading = false
        }
      })
    },
    addHandel() {
      this.rowStatus = 'new'
      this.showFlag = false
      this.dialogFormVisible = true
      this.addForm = {
        payType: '',
        payee: '',
        relation: '',
        cardType: '',
        idCard: '',
        sex: '',
        mobile: '',
        validSign: false,
        dateRange: [],
        nation: '',
        occupation: '',
        bankNo:'',
        getRate: 100,
        bank: '',
        address: [],
        bankAddress: [],
        detailAddress: '',
        repeatBankNo: '',
        bankName: ''
      }
      this.bankList = []
    },
    delHandle(index, row) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.tableData.splice(index, 1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    editHandle(index, row) {
      console.log(row)
      this.rowStatus = 'edit'
      this.rowIndex = index
      const data =this.deepClone(row)
      if(row.validSign) {
        this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: 'change' }
      }
      this.bankList = []
      if(data.bank !== '' && data.bank !== null) {
        this.bankList.push({label:data.bankName,value: data.bank})
      }
      this.addForm = data
      if(data.payType === '04' || data.payType === '05') {
        this.tableFormRules.bank = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
      }else {
        this.tableFormRules.bank = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      }
      // this.getCategoryBankList(row.bankAddress)
      this.dialogFormVisible = true
    },
    handlePaste() {
      console.log('禁止粘贴！！！！')
    },
    // handleBankChange(value) {
    //   this.addForm.bank = ''
    //   this.getCategoryBankList(value)
    // },
    // handleFocus() {
    //   if(this.bankRegions === null || this.bankRegions === []) {
    //     this.$message({ message: '请先选择银行地区！', type: 'warning' })
    //     return false
    //   }
    //   if(this.bankList === null || this.bankList === []) {
    //     this.$message({ message: '该地区无此银行，请重新选择银行地区！', type: 'warning' })
    //     return false
    //   }
    // },
    remoteBanks(query) {
      if (query !== '') {
        this.loading = true
        getBankLike(query).then(response => {
          this.bankList = response.data
          this.loading = false
        }).catch(error => {
          this.loading = false
          this.$message.error('查询银行列表异常')
        })
      }
    },
    handleChange(data) {
      if(data !== '' && data !== null) {
        let obj = {};
        obj = this.bankList.find((item)=>{//这里的userList就是上面遍历的数据源
          return item.value === data;//筛选出匹配数据
        });
        if(obj === undefined) {
          this.addForm.bankName = data
        }else{
          this.addForm.bankName = obj.label
        }
      }else {
        this.addForm.bankName = ''
      }
    },
    // 地址下拉选
    getAddressInfo() {
      this.regions = this.getAddressByFront()
      this.newRegions = this.getAddressByFront()
      this.newRegions.forEach((item) =>{
        item.children.map((item) =>{
          delete item.children
        })
      })
    },
    dialogFormConfirm() {
      this.$refs.addForm.validate((valid) => {
        if(valid) {
          if(this.tableData.length > 0) {
            for(let i = 0;i<this.tableData.length;i++) {
              if(this.rowStatus === 'edit') {
                if(this.rowIndex === i) {
                  continue
                }
              }
              if(this.addForm.payee === this.tableData[i].payee) {
                this.$message({ message: '不能再次录入相同的领款人！', type: 'warning' })
                return  false
              }
            }
          }
          if(this.addForm.payType !== '01') {
            if(this.addForm.bankNo !== this.addForm.repeatBankNo) {
              this.$message({ message: '账号与确认账号不一致！', type: 'warning' })
              return false
            }
          }
          this.dialogFormVisible = false
          const data = this.addForm
          if (this.rowStatus === 'new') {
            this.tableData.push(data)
          } else {
            this.tableData[this.rowIndex] = data
          }
        }else {
          return false
        }

      })
    },
    // 申请人选择被保人关系为本人时，自动带入被保人信息
    changeInfo(value) {
      if (value === '00') {
        this.baseForm.reportName = this.insuredBaseInfo.insuredName
        this.baseForm.cardType = this.insuredBaseInfo.idType
        this.baseForm.idCard = this.insuredBaseInfo.idNo
        this.baseForm.gender = this.insuredBaseInfo.sex
        this.baseForm.phone = this.insuredBaseInfo.phone
        this.baseForm.mobile = this.insuredBaseInfo.mobiles[0]
        this.baseForm.email = this.insuredBaseInfo.email
        this.baseForm.address = this.insuredBaseInfo.address
      } else {
        this.baseForm.reportName = ''
        this.baseForm.cardType = ''
        this.baseForm.idCard = ''
        this.baseForm.gender = ''
        this.baseForm.phone = ''
        this.baseForm.mobile = ''
        this.baseForm.email = ''
        this.baseForm.address = ''
      }
    },
    changePayInfo(value) {
      if (value === '00') {
        this.addForm.payee = this.insuredBaseInfo.insuredName
        this.showFlag = true
        this.addForm.cardType = this.insuredBaseInfo.idType
        this.addForm.idCard = this.insuredBaseInfo.idNo
        this.addForm.sex = this.insuredBaseInfo.sex
        this.addForm.mobile = this.insuredBaseInfo.mobiles[0]
        this.addForm.validSign = this.insuredBaseInfo.checked
        this.addForm.dateRange = this.insuredBaseInfo.dateRange
        this.addForm.nation = this.insuredBaseInfo.nationality
        this.addForm.occupation = this.insuredBaseInfo.occupation
        this.addForm.address = this.insuredBaseInfo.area
        this.addForm.detailAddress = this.insuredBaseInfo.address
      }else {
        this.addForm.payee = ''
        this.showFlag = false
        this.addForm.cardType = ''
        this.addForm.idCard = ''
        this.addForm.sex = ''
        this.addForm.mobile = ''
        this.addForm.validSign = false
        this.addForm.dateRange = null
        this.addForm.nation = ''
        this.addForm.occupation = ''
        this.addForm.address = ''
        this.addForm.detailAddress = ''
      }
    },
    changePayType(value) {
      // 支付宝
      if(value === '06') {
          this.tableFormRules.bankAddress =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
          this.tableFormRules.bank =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      }else {
        this.tableFormRules.bankAddress =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
        this.tableFormRules.bank =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      }
      if(value === '04' || value === '05') {
        this.tableFormRules.bank = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
      }
    },
    getCNCollect(row) {
      //return this.dict.label.collectedmode[row.payType]
      return this.selectDictLabel(this.collectedmodeOptions, row.payType)
    },
    getCNRelation(row) {
      //return this.dict.label.relation_ship_apply[row.relation]
      return this.selectDictLabel(this.relation_ship_applyOptions, row.relation)
    },
    getCNNative(row) {
      //return this.dict.label.nativeplace[row.nation]
      return this.selectDictLabel(this.nativeplaceOptions, row.nation)
    },
    getCNOccupation(row) {
      //return this.dict.label.payee_occupation[row.occupation]
      return this.selectDictLabel(this.payee_occupationOptions, row.occupation)
    },
    getCNIdType(row) {
      //return this.dict.label.card_type[row.cardType]
      return this.selectDictLabel(this.card_typeOptions, row.cardType)
    },
    // //获取银行地址
    // getBankAddress() {
    //   getAddress().then(response => {
    //     if (response.status === '1') {
    //       this.bankRegions = response.data
    //     } else {
    //       this.$message({ message: '地址下拉数据初始化失败！', type: 'error' })
    //     }
    //   }).catch(error => {
    //   })
    // },
    // getCategoryBankList(data) {
    //   if(data !== null && data.length > 0) {
    //     const params = {
    //       proCode: data[1],
    //       cityCode: data[2],
    //       headBank:data[0]
    //     }
    //     getCategoryBank(params).then(res => {
    //       this.bankList = res.data
    //     })
    //   }
    // },
    rulesFlag(value) {
      if(value) {
        this.tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: 'change' }
        this.addForm.dateRange = null
        this.$refs.addForm.clearValidate('dateRange')
      }else {
        this.tableFormRules.dateRange = { required: true, message: '不能为空!', trigger: 'change' }
      }
    },
    getGender() {
      if (this.baseForm.cardType !== '0') {
        return
      }
      if (parseInt(this.baseForm.idCard.substr(16, 1)) % 2 === 1) {
        this.baseForm.gender = '0'
      } else {
        this.baseForm.gender = '1'
      }
    },
    deepClone(val) {
      var obj = JSON.stringify(val)
      return JSON.parse(obj)
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 220px;
}
.positionsass {
  position: none !important;
}
.positionsass2 {
  position: none !important;
}
.positionClass {
  position: absolute;
  left: 0;
  top: 95px;
}
.positionClass2 {
  position: absolute;
  left: 33.3%;
  top: 95px;
}
.el-form /deep/ .el-form-item__label {
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.read-only.el-cascader /deep/ .el-input.is-disabled .el-input__inner{
  border: none;
  color: #333;
  padding: 0;
  background: transparent;
  width: 100%;
}
.read-only.el-cascader /deep/ .el-icon-arrow-down:before {
  display: none;
}
.long-select{
  width: 100%;
}
</style>
