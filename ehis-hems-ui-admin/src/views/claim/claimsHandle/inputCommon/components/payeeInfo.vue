<template>
  <div>
    <el-table
      :data="addFormTable"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      style="width: 95%;margin-bottom: 20px"
      highlight-current-row
      tooltip-effect="dark"
      @row-click="rowClick">
      <el-table-column :formatter="getRelation" prop="relation" align="center" label="与被保人关系"/>
      <el-table-column prop="payee" align="center" label="领款人"/>
      <el-table-column prop="idCard" align="center" label="证件号码"/>
      <el-table-column prop="getRate" align="center" label="分配比例(%)"/>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button :disabled="status === 'show'" size="mini" type="text" @click="delHandle(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="height: 350px;overflow-y: scroll">
      <el-form ref="addForm" :rules="addFormRules" :disabled="status === 'show'" :model="addForm" size="mini" label-width="160px">
        <el-row>
          <el-col :span="13">
            <el-form-item label="领款方式：" prop="payType">
              <el-select v-model="addForm.payType" class="item-width" placeholder="请选择" @change="changePayType">
                <el-option :disabled="disabledFlag && (option.value === '04' || option.value === '05')" v-for="option in collectedmodeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="与被保人关系：" prop="relation">
              <el-select v-model="addForm.relation" class="item-width" placeholder="请选择" @change="changePayInfo">
                <el-option v-for="option in relation_ship_applyOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="领款人：" prop="payee">
              <el-input v-model="addForm.payee" :disabled="showFlag" class="item-width" clearable autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="手机号码：" prop="mobile">
              <el-input v-model="addForm.mobile" class="item-width" clearable autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item v-if="addForm.relation!== '00' && addForm.relation!== ''" label="国籍：" prop="nation">
              <el-select v-model="addForm.nation" class="item-width" placeholder="请选择">
                <el-option v-for="option in nativeplaceOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="证件类型：" prop="cardType">
              <el-select v-model="addForm.cardType" class="item-width" placeholder="请选择">
                <el-option v-for="option in card_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="证件号码：" prop="idCard">
              <el-input v-model="addForm.idCard" class="item-width" clearable maxlength="20" autocomplete="off" @change="idNoChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="性别：" prop="sex">
              <el-select v-model="addForm.sex" class="item-width" placeholder="请选择">
                <el-option v-for="option in sexOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="证件起止期：" prop="dateRange">
              <el-date-picker
                :disabled="addForm.validSign === true"
                v-model="addForm.dateRange"
                style="width: 205px;"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                @change="dateChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="1">
            <el-form-item style="padding-left: 75px;" label-width="0" prop="validSign">
              <el-checkbox :disabled="addForm.dateRange!==null&&addForm.dateRange.length !== 0" v-model="addForm.validSign" @change="rulesFlag">长期</el-checkbox>
            </el-form-item>
          </el-col>
          <el-col  :span="13"  :class="{'positionClass': (addForm.relation!== '00' && addForm.relation!== '')}">
            <el-form-item label="职业：" prop="occupation">
              <el-select v-model="addForm.occupation" filterable  class="item-width" placeholder="请选择" >
                <el-option v-for="option in payee_occupationOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13" :class="{'positionsass':addForm.payType!=='01' && addForm.relation== '00', 'positionClass2': (addForm.payType!=='01' && addForm.payType !== '' && addForm.relation !== '00' &&  addForm.relation !== '')}">
            <el-form-item  v-if="addForm.payType!=='01'" label="账号/支付宝号：" prop="bankNo">
              <el-input v-model="addForm.bankNo"  clearable  class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item  v-if="addForm.payType!=='01'" title="确认账号/支付宝号" label="确认账号/支付宝号：" prop="repeatBankNo">
              <el-input v-model="addForm.repeatBankNo" class="item-width" @paste.native.capture.prevent="handlePaste" clearable autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col  :span="13">
            <el-form-item v-if="addForm.payType==='04'||addForm.payType==='05'" title="银行名称" label="银行名称：" prop="bank">
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
          <el-col :span="13">
            <el-form-item label="分配比例(%)：" prop="getRate" :class="{'positionClass2': (addForm.payType=='01' && addForm.relation!== '00' && addForm.relation!== '')}">
              <el-input-number v-model="addForm.getRate" :min="1" :max="100" :precision="0" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="13">
            <el-form-item label="联系地址：" prop="address">
              <el-cascader
                v-model="addForm.address"
                :options="regions"
                class="item-width"/>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="详细地址：" prop="detailAddress">
              <el-input v-model="addForm.detailAddress" clearable size="mini" maxlength="200" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注：" prop="remark">
              <el-input type="textarea" style="width: 80%" v-model="addForm.remark" clearable size="mini" maxlength="500" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
import { validPhone } from '@/utils/validate'
import { getBankLike, payeeSave, getPayeeInfos, deletePayee }  from '@/api/claim/input'
import getAddressByFront from '../../common/mixins/address'

export default {
 // dicts: ['collectedmode', 'relation_ship_apply', 'nativeplace', 'card_type', 'sex', 'payee_occupation'],
  mixins: [getAddressByFront],
  props: {
    dictList: {
      type: Array
    },
    claimno: {
      type: String,
      default: function() {
        return ''
      }
    },
    informationOfTheInsured: {
      type: Object,
      default: function() {
        return {}
      }
    },
    status: {
      type: String,
      default: function() {
        return ''
      }
    },
    disabledFlag: {
      type: Boolean,
      default: function() {
        return false
      }
    },
    newimageState: {
      type: Array,
      default: function() {
        return []
      }
    },
  },
  data() {
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
    const checkPayNo = (rule, value, callback) => {
      if (!value) {
        callback(new Error('不能为空'))
      } else {
        if (value === this.addForm.bankNo) {
          callback()
        } else {
          callback(new Error('两次录入不相同'))
        }
      }
    }
    return {
      addForm: {
        payee: '',
        payeeNo: '',
        payType: '',
        relation: '',
        cardType: '',
        idCard: '',
        sex: '',
        mobile: '',
        validSign: false,
        dateRange: [],
        bankNo: '',
        repeatBankNo: '',
        nation: '',
        occupation: '',
        bank: '',
        address: '',
        detailAddress: '',
        getRate: 100,
        remark: ''
      },
      addFormTable: [],
      addFormRules: {
        payType: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        relation: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        payee: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        getRate: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        mobile: { required: true, validator: checkPhone, trigger: ['blur', 'change'] },
        sex: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        cardType: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        idCard: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        dateRange: { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
        nation: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        address: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        detailAddress: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        bankAddress: { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
        bank: { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
        bankNo: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        repeatBankNo: { required: true,validator: checkPayNo, trigger: ['input', 'change'] },
        occupation: { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      },
      source: '',
      regions: [],
      newRegions: [],
      bankList: [],
      showFlag: false,
      loading: false,
     /* 'collectedmode', 'relation_ship_apply', 'nativeplace', 'card_type', 'sex', 'payee_occupation'*/
      collectedmodeOptions: [],
      relation_ship_applyOptions: [],
      nativeplaceOptions: [],
      card_typeOptions: [],
      sexOptions: [],
      payee_occupationOptions: [],
    }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.collectedmodeOptions = this.dictList.find(item => {
        return item.dictType == 'collectedmode'
      }).dictDate
      this.relation_ship_applyOptions = this.dictList.find(item => {
        return item.dictType == 'relation_ship_apply'
      }).dictDate
      this.nativeplaceOptions = this.dictList.find(item => {
        return item.dictType == 'nativeplace'
      }).dictDate
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType == 'card_type'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType == 'sex'
      }).dictDate
      this.payee_occupationOptions = this.dictList.find(item => {
        return item.dictType == 'payee_occupation'
      }).dictDate
    }

  },
  created() {
    this.getAddressInfo()
    this.getPayeeInfos()
  },
  methods: {
    idNoChange(data) {
      // 身份证带出出生日期、性别
      this.getGender(data)
    },
    getGender(data) {
      if (this.addForm.cardType !== '0' || data === '') {
        return
      }
      if (parseInt(data.substr(16, 1)) % 2 === 1) {
        this.$set(this.addForm, 'sex', '0')
      } else {
        this.$set(this.addForm, 'sex', '1')
      }
    },
    resetForm() {
      this.$refs.addForm.resetFields()
      this.$set(this.addForm, 'payeeNo', '')
    },
    getRelation(row) {

     /* return this.dict.label.relation_ship_apply[row.relation]*/
      return this.selectDictLabel(this.relation_ship_applyOptions, row.relation);
    },
    delHandle(row) {
      deletePayee(row.payeeNo).then(response => {
        if (response.status === '1') {
          this.$message.success('删除成功！')
          this.resetForm()
          this.getPayeeInfos()
        } else {
          this.$message.error('删除失败！')
        }
      })
    },
    addPayeeinfo() {
      this.resetForm()
      this.$set(this.addForm, 'payeeNo', '')
    },
    rowClick(row) {
      this.addForm = JSON.parse(JSON.stringify(row))
    },
    getPayeeInfos() {
      getPayeeInfos(this.claimno).then(response => {
        if (response.status === '1') {
          this.addFormTable = response.data
        } else {
          this.$message.error('查询领款人失败！')
        }
      })
    },
    changePayType(value) {
      // 支付宝
      if(value === '06') {
        this.addFormRules.bankAddress =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
          this.addFormRules.bank =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      }else {
        this.addFormRules.bankAddress =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] },
          this.addFormRules.bank =  { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      }
      if(value === '04' || value === '05') {
        this.addFormRules.bank = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
      }
    },
    changePayInfo(value) {
      if (value === '00') {
        this.$set(this.addForm, 'payee', this.informationOfTheInsured.insuredName)
        this.$set(this.addForm, 'nation', this.informationOfTheInsured.nation)
        this.$set(this.addForm, 'cardType', this.informationOfTheInsured.cardType)
        this.$set(this.addForm, 'idCard', this.informationOfTheInsured.idNo)
        this.$set(this.addForm, 'sex', this.informationOfTheInsured.sex)
        this.$set(this.addForm, 'dateRange', this.informationOfTheInsured.dateRange)
        this.$set(this.addForm, 'validSign', this.informationOfTheInsured.validSign)
        this.$set(this.addForm, 'occupation', this.informationOfTheInsured.occupation)
        this.$set(this.addForm, 'mobile', this.informationOfTheInsured.mobile)
        this.$set(this.addForm, 'address', this.informationOfTheInsured.address)
        this.$set(this.addForm, 'detailAddress', this.informationOfTheInsured.detailAddress)
      }else {
        this.$set(this.addForm, 'payee', '')
        this.$set(this.addForm, 'nation', '')
        this.$set(this.addForm, 'cardType', '')
        this.$set(this.addForm, 'idCard', '')
        this.$set(this.addForm, 'sex', '')
        this.$set(this.addForm, 'dateRange', [])
        this.$set(this.addForm, 'validSign', '')
        this.$set(this.addForm, 'occupation', '')
        this.$set(this.addForm, 'mobile', '')
        this.$set(this.addForm, 'address', '')
        this.$set(this.addForm, 'detailAddress', '')
      }
    },
    dateChange(value) {
      console.log(value)
      if (value == null) {
        this.$set(this.addForm, 'dateRange', [])
      }
    },
    rulesFlag(value) {
      if(value) {
        this.addFormRules.dateRange = { required: false, message: '不能为空!', trigger: 'change' }
        this.addForm.dateRange = []
        this.$refs.addForm.clearValidate('dateRange')
      }else {
        this.addFormRules.dateRange = { required: true, message: '不能为空!', trigger: 'change' }
      }
    },
    handlePaste() {
      console.log('禁止粘贴！！！！')
    },
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
    // 保存领款人
    payeeSave() {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          const params = {
            claimNo: this.claimno,
            payeeInfo: JSON.parse(JSON.stringify(this.addForm))
          }
          // if (this.newimageState.length === 0) {
          //   let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
          //   if (warnings.length === 0) {
          //     this.$message.warning('请先选择影像状态！')
          //   }
          //   return
          // }
          payeeSave(params).then(response => {
            if (response.status === '1') {
              this.$message.success('保存成功！')
              this.getPayeeInfos()
            } else if (response.data.errCode == 'CL139') {
              this.$message({ message: response.data.errMsg ? response.data.errMsg:'操作失败！', type: 'error' })
            } else {
              this.$message.error('保存失败')
            }
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 200px;
  }
  /*.el-form-item /deep/.el-form-item__label {*/
  /*  padding-right: 0;*/
  /*}*/
</style>
