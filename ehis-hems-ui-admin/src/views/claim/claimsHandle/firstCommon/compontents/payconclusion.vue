<template>
  <el-card style="margin:10px 0">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">赔付结论</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;">
          <el-button :loading="loading" type="primary" size="mini" @click="savePayInfo">保存</el-button>
        </span>
        <el-form  class="basic-box" ref="payInfoForm" label-width="124px" :model="payInfo" :rules="payInfoRules"  label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="申请金额：">
                <span class="form-item-span">{{ payInfo.claimamount |numFilter}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="垫付金额：">
                <span class="form-item-span">{{ payInfo.advancepayment |numFilter}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="总扣除金额：">
                <span class="form-item-span">{{ payInfo.deductedamount |numFilter}}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="理算金额：">
                <span class="form-item-span">{{ payInfo.payableamount |numFilter}}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="实赔金额：" prop="finalamount" :rules="payInfoRules.finalamount">
                <el-input
                  v-model="payInfo.finalamount"
                  type="text"
                  :disabled="disabledFinalamount"
                  style="width:85%"
                  placeholder="请输入金额"
                  clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="赔付结论：" prop="conclusion" :rules="payInfoRules.conclusion">
                <el-select v-model="payInfo.conclusion" clearable placeholder="请选择"
                  style="width:85%"
                  @change="concluChange">
                  <el-option v-for="item in payment_conclusionOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="赔付依据：" prop="conclusionreason" :rules="payInfoRules.conclusionreason">
                <el-select v-model="payInfo.conclusionreason"  style="width:85%" clearable placeholder="请选择" :disabled="disabledCon" @change="conclusionreasonChange">
                  <el-option v-for="item in conclusionreasonList" :key="item.dictValue" :value="item.dictValue" :label="item.dictLabel"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="赔付说明：" prop="explanation" :rules="payInfoRules.explanation">
                <el-autocomplete
                  v-model="payInfo.explanation"
                  :rows="4"
                  style="width:85%"
                  :fetch-suggestions="querySearch"
                  type="textarea"
                  placeholder="请输入内容"
                  :maxlength="1000"
                  @focus="focusSerch"
                  show-word-limit>
                </el-autocomplete>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import {savePayInfo,getContents,calculationPayAmount} from '@/api/claim/handleDeal'
import bus from '../../bus/bus'
import Bus from '../../bus/bus'
export default {
  /*dicts:['payment_conclusion','conclusionreason','conclusionreason2','conclusionreason3','conclusionreason4','conclusionreason5',
    'collectedmode','relation_ship_apply','applicant_idtype','nativeplace','payee_occupation'
  ],*/
  filters: {
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
    },
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
  },
  props: {
    dictList:Array,
    payInfo: Object,
    claimno: String,
    payeeInfo:  Array,
    conclusionreasonList: Array,
  },
  data () {
    // 校验金额（数字 最多三位小数）
    const isAmount = (rule, value, callback) => {
      if (value) {
        // const regx = /^(\d+|\d+\.\d{1,3})$/   //不能为负
        const regx = this.isAppeal ? /^-?\d+(\.\d{1,3})?$/ : /^(\d+|\d+\.\d{1,3})$/
        if (!regx.test(value)) {
          callback(new Error('无效输入'))
        } else {
          if(this.payInfo.conclusion==='4'||this.payInfo.conclusion==='5'){
            callback()
          } else {
            if(this.payInfo.explanation){
              if(Number(this.payInfo.payableamount)<Number(value)){
                callback(new Error('实赔金额不能大于理算金额！'))
              } else {
                callback()
              }
            } else {
              callback()
            }
          }
        }
      } else {
        if (value == 0) {
          callback()
        } else {
          callback(new Error('实赔金额必填!'))
        }
      }
    }
    return {
      activeNames: ['1'],
      expList: [],
      disabledFinalamount: false,
      disabledCon: false,
      fillMount: 0,
      loading: false,
      payInfoRules: {
        finalamount: [{ validator: isAmount, trigger: 'blur', required: true }], // 实赔金额
        conclusion: [{ trigger: 'change', required: true, message: '赔付结论必填' }], // 赔付结论
        conclusionreason: { trigger: 'change', required: true, message: '赔付依据必填' }, // 赔付依据
        explanation: [{ trigger: ['blur', 'change'], required: true, message: '赔付说明必填' }] // 赔付说明
      },
      payment_conclusionOptions: [],
      conclusionreasonOptions: [],
      conclusionreason2Options: [],
      conclusionreason3Options: [],
      conclusionreason4Options: [],
      conclusionreason5Options: [],
      collectedmodeOptions: [],
      relation_ship_applyOptions: [],
      applicant_idtypeOptions: [],
      nativeplaceOptions: [],
      payee_occupationOptions: [],
    }
  },
  mounted() {
    Bus.$on('finalamountMethods', data =>{
      this.fillMount = data
      this.payInfo.finalamount = (data)
    })

    if (this.dictList != null && this.dictList.length != 0) {
      this.payment_conclusionOptions=this.dictList.find(item=>{
        return item.dictType=='payment_conclusion'
      }).dictDate
      this.conclusionOptions=this.dictList.find(item=>{
        return item.dictType=='conclusion'
      }).dictDate
      this.conclusionreason2Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason2'
      }).dictDate
      this.conclusionreason3Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason3'
      }).dictDate
      this.conclusionreason4Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason4'
      }).dictDate
      this.conclusionreason5Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason5'
      }).dictDate
      this.collectedmodeOptions=this.dictList.find(item=>{
        return item.dictType=='collectedmode'
      }).dictDate
      this.relation_ship_applyOptions=this.dictList.find(item=>{
        return item.dictType=='relation_ship_apply'
      }).dictDate
      this.applicant_idtypeOptions=this.dictList.find(item=>{
        return item.dictType=='applicant_idtype'
      }).dictDate
      this.nativeplaceOptions=this.dictList.find(item=>{
        return item.dictType=='nativeplace'
      }).dictDate
      this.payee_occupationOptions=this.dictList.find(item=>{
        return item.dictType=='payee_occupation'
      }).dictDate
    }

  },
  methods: {
    focusSerch() {
      this.loadAll()
    },
    loadAll() {
      this.expList = []
      const params = {
        templatetype: '01',
        supplementarymode: '',
        circulationvalue1: this.payInfo.conclusion,
        circulationvalue2: this.payInfo.conclusionreason
      }
      getContents(params).then(res =>{
        if (res.status == '1') {
          res.data.forEach(item => {
            this.expList.push({ value: item })
          })
        }
      })
    },
    // 实赔金额校验
    concluChange(value){
      this.payInfo.conclusionreason = ''
      if (value == '3') {
        this.payInfo.finalamount = '0'
        this.disabledFinalamount = true
      } else {
        this.disabledFinalamount = false
        if (this.fillMount == 0) {
          this.payInfo.finalamount = this.payInfo.finalamount
        } else {
          this.payInfo.finalamount = (this.fillMount)
        }
      }
      this.conclusionreasonList = []
      this.$refs.payInfoForm.validateField('finalamount')
      if (value == '1') {
        this.conclusionreasonList = this.conclusionreasonOptions
        this.payInfoRules.conclusionreason.required = true
        this.disabledCon = false
      } else if (value == '2') {
        this.conclusionreasonList = this.conclusionreasonOptions
        this.payInfoRules.conclusionreason.required = true
        this.disabledCon = false
      } else if (value == '3') {
        this.conclusionreasonList = this.conclusionreason2Options
        this.payInfoRules.conclusionreason.required = true
        this.disabledCon = false
      } else if (value == '4') {
        this.conclusionreasonList = this.conclusionreason3Options
        this.payInfoRules.conclusionreason.required = true
        this.disabledCon = false
      } else if(value == '5') {
        this.conclusionreasonList = this.conclusionreason4Options
        this.payInfoRules.conclusionreason.required = true
        this.disabledCon = false
      } else {
        this.conclusionreasonList = this.conclusionreason5Options
        this.disabledCon = false
        this.payInfoRules.conclusionreason.required = true
      }
    },
    savePayInfo() {
      if (this.payInfo.explanation!== '' && this.payInfo.explanation!== null) {
        this.payInfo.explanation = this.payInfo.explanation.replace(/\s+/g, "");
        if (this.payInfo.explanation == '') {
          this.$message.warning('请填写赔付说明信息！')
          return false
        }
      }
      this.$refs['payInfoForm'].validate(valid => {
        if (valid) {
          this.loading = true
          const { conclusion, finalamount, conclusionreason, explanation, applicationreason } = this.payInfo
          const params = {
            claimno: this.claimno,
            conclusion,
            finalamount,
            conclusionreason,
            explanation,
            applicationreason
          }
          savePayInfo(params).then(response => {
            if (response.status === '1') {
              // this.getPayInfo()
              this.loading = false
              this.$emit('getPayInfo')
              if(this.payeeInfo.length){
                this.payeeInfo.map((item, i) => {
                  if(item.payoutratio){
                    let params = {
                      claimno: this.claimno,
                      payoutratio: item.payoutratio
                    }
                    calculationPayAmount(params).then(res => {
                      if (res.status === "1") {
                        if(res.data===0){
                          // this.amountFlag=true
                        }
                        this.$set(this.payeeInfo[i], 'payamount', res.data)
                      } else {
                        // this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: "error"})
                      }
                    })
                  } else {
                    this.$set(this.payeeInfo[i],'payamount',null)
                  }
                })
              }
              this.$message({ message: '操作成功！', type: 'success' })
              bus.$emit('updateactives',['1'])
            } else {
              this.loading = false
              this.$message({ message: response.data.errMsg, type: 'error' })
            }
          })
        }
      })
    },
    // 查询赔付模板
    conclusionreasonChange() {
      this.loadAll()
    },
    querySearch(queryString, cb) {
      let expList = this.expList
      let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
      cb(results ? results : [])
    },
    createFilter(queryString) {
      return (expList) => {
        return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      }
    },
  }
}
</script>
<style scoped>

</style>
