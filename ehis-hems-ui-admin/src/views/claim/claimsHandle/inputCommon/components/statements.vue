<template>
  <el-form ref="statementsForm" class="form_div" :disabled="status === 'show'" :rules="statementsFormRules" :model="statementsForm" size="mini" label-width="108px"
    style="height: 450px;overflow-y: scroll">
    <el-row>
      <el-col :span="12">
        <el-form-item label="就诊人姓名：" prop="name">
          <el-input v-model="statementsForm.name" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="结算单编号：" prop="statementno">
          <el-input v-model="statementsForm.statementno" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="结算单类型：" prop="statementtype">
          <el-select v-model="statementsForm.statementtype" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in statement_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="票面类型：" prop="invoicetype">
          <el-select v-model="statementsForm.invoicetype" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in statement_invoice_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="账单起止日期：" prop="statementdate">
          <el-date-picker
            v-model="statementsForm.statementdate"
            class="item-width"
            type="daterange"
            style="width: 220px"
            range-separator="~"
            start-placeholder="开始日期"
            end-placeholder="结束日期"/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="社保类型：" prop="socialsecuritytype">
          <el-select v-model="statementsForm.socialsecuritytype" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in socialsecurity_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="就诊医院：" prop="hospital">
          <el-select
            v-model="statementsForm.hospital"
            :remote-method="remoteHospitals"
            :loading="loading"
            filterable
            remote
            reserve-keyword
            class="item_width"
            placeholder="请输入医院关键字"
            @change="handleChange"
            clearable>
            <el-option v-for="option in hospitals" :key="option.id" :label="option.label" :value="option" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="医院备注：" prop="hospitalremarks">
          <el-input v-model="statementsForm.hospitalremarks" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="医院科室：" prop="department">
          <el-input v-model="statementsForm.department" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="总金额：" prop="totalaccount">
          <el-input-number v-model="statementsForm.totalaccount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="统筹支付：" prop="overallpayment">
          <el-input-number v-model="statementsForm.overallpayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="第三方支付：" prop="thirdpartypayment">
          <el-input-number v-model="statementsForm.thirdpartypayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="起付线：" prop="startline">
          <el-input-number v-model="statementsForm.startline" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="自付比例：" prop="proportion">
          <el-input-number v-model="statementsForm.proportion" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="自费金额：" prop="selfpayment">
          <el-input-number v-model="statementsForm.selfpayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="超大额：" prop="bigaccount">
          <el-input-number v-model="statementsForm.bigaccount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="有无发票：" prop="withinvoice">
          <el-radio-group v-model="statementsForm.withinvoice">
            <el-radio :label="'1'">有</el-radio>
            <el-radio :label="'2'">无</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="已关联发票号：" prop="invoicelist">
          <el-select
            v-model="statementsForm.invoicelist"
           reserve-keyword
           filterable
           remote
           clearable
           style="width: 160px"
           placeholder="请选择">
            <el-option v-for="(item, i) in statementsForm.statementBillsVOS" disabled :key="item.invoiceno" :label="item.invoiceno" :value="item.invoiceno" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="字段模糊：" prop="imageblured">
          <el-checkbox v-model="statementsForm.imageblured"></el-checkbox>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="备注：" prop="remarks">
          <el-input v-model="statementsForm.remarks" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-divider />
    <el-tabs v-show="statementsShow" v-model="statementsActive">
      <el-tab-pane label="已关联发票" name="first">
        <el-table
          :data="statementsForm.statementBillsVOS"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          style="width: 95%;margin: 0 auto"
          highlight-current-row
          tooltip-effect="dark">
          <el-table-column align="center" prop="invoiceno" label="发票号"/>
          <el-table-column align="center" prop="hospitalname" label="所属医院"/>
          <el-table-column align="center" prop="name" label="姓名"/>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="delHandle(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row>
          <el-button style="float: right;margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="addHandle">添加</el-button>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-form ref="addInvoiceForm" :rules="addInvoiceFormRules" :model="addInvoiceForm" size="mini" label-width="130px">
            <el-col :span="12">
              <el-form-item label="发票号：" prop="invoiceno">
                <el-input v-model="addInvoiceForm.invoiceno" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="所属医院：" prop="hospital">
                <el-select
                  v-model="addInvoiceForm.hospital"
                  :remote-method="remoteHospitals"
                  :loading="loading"
                  filterable
                  remote
                  reserve-keyword
                  placeholder="请输入医院关键字"
                  @change="handleChange2"
                  clearable>
                  <el-option v-for="option in hospitals" :key="option.id" :label="option.label" :value="option" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="姓名：" prop="name">
                <el-input v-model="addInvoiceForm.name" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
      </el-tab-pane>
    </el-tabs>
    <el-button :disabled="status === 'show'" type="primary" size="mini" @click="addAccount">锁定影像</el-button>
    <el-button :disabled="status === 'show'" type="primary" size="mini" @click="newAccount">新建</el-button>
  </el-form>
</template>

<script>
import { addAccount, getHospitalLike, selectStatementBills, selectStatementInvoiceno } from '@/api/claim/input'

export default {
  //dicts: ['socialsecurity_type', 'statement_invoice_type', 'statement_type'],
  props: {
    dictList: {
      type: Array
    },
    claimNo: {
      type: String,
      default: function() {
        return ''
      }
    },
    pageId: {
      type: Number,
      default: function() {
        return 0
      }
    },
    status: {
      type: String,
      default: function() {
        return ''
      }
    },
    urls: {
      type: Array,
      default: function() {
        return []
      }
    }
  },
  data() {
    const validateAmount = (rule, value, callback) => {
      if (!value) {
        callback(new Error('金额不能为空且不能小于0！'))
      } else {
        callback()
      }
    }
    const checkInvoive = (rule, value, callback) => {
      if (!value) {
        callback(new Error('发票号不可为空'))
      } else {
        if (value.length > 100) {
          callback(new Error('发票号小于等于100位'))
        } else {
          const str = /^[a-zA-Z0-9\-]*$/g
          if (!str.test(value)) {
            callback(new Error('发票号允许录入数字、字母、"-"'))
          } else {
            callback()
          }
        }
      }
    }
    return {
      statementsFormRules: {
        name: { required: true, message: '不能为空!', trigger:  ['blur', 'change'] },
        statementno: { required: true, message: '不能为空!', trigger:  ['blur', 'change'] },
        statementtype: { required: true, message: '不能为空!', trigger:  ['blur', 'change'] },
        invoicetype: { required: true, message: '不能为空!', trigger:  ['blur', 'change'] },
        statementdate: { required: true, message: '不能为空!', trigger:  ['blur', 'change'] },
        hospital: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        socialsecuritytype: { required: true, message: '不能为空!', trigger:  ['blur', 'change'] },
        totalaccount : { validator: validateAmount, required: true, trigger: ['blur', 'change'] },
        hospitalremarks: { required: false, message: '不能为空!', trigger:  ['blur', 'change'] },
        withinvoice: { required: false, message: '不能为空!', trigger:  ['blur', 'change'] }
      },
      statementsForm: {
        name: '',
        statementno: '',
        statementtype: '',
        invoicetype: '',
        statementdate: [],
        hospital: '',
        department: '',
        totalaccount: '',
        overallpayment: '',
        thirdpartypayment: '',
        startline: '',
        proportion: '',
        selfpayment: '',
        bigaccount: '',
        withinvoice: '1',
        imageblured: false,
        remarks: '',
        statementBillsVOS: [],
        invoicelist: '',
        hospitalremarks: ''
      },
      statementsActive: 'first',
      addInvoiceForm: {
        invoiceno: '',
        hospital: [],
        name: ''
      },
      addInvoiceFormRules: {
        invoiceno : { validator: checkInvoive, required: true, trigger: ['blur', 'change'] },
        hospital: { required: true, message: '不能为空!', trigger: 'change' },
        name: { required: true, message: '不能为空!', trigger: 'change' }
      },
      hospitals: [],
      loading: false,
      statementsShow: false,
      index: 0,
      // associatedinvoice: []
      socialsecurity_typeOptions: [],
      statement_invoice_typeOptions: [],
      statement_typeOptions: [],
    }
  },
  watch: {
    statementsForm: {
      handler(newVal, oldVal) {
        if (newVal.withinvoice === '1') {
          this.statementsShow = true
        } else {
          this.statementsShow = false
        }
      },
      deep: true,
    },
    pageId(val) {
      // this.selectStatementBills()
    }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.socialsecurity_typeOptions = this.dictList.find(item => {
        return item.dictType == 'socialsecurity_type'
      }).dictDate
      this.statement_invoice_typeOptions = this.dictList.find(item => {
        return item.dictType == 'statement_invoice_type'
      }).dictDate
      this.statement_typeOptions = this.dictList.find(item => {
        return item.dictType == 'statement_type'
      }).dictDate
    }
  },
  methods: {
    addHandle() {
      this.$refs.addInvoiceForm.validate((valid) => {
        if (valid) {
          if (this.statementsForm.statementBillsVOS.find(item => item.invoiceno === this.addInvoiceForm.invoiceno)) {
            let warnings = document.getElementsByClassName('el-icon-warning')
            if(warnings.length === 0) {
              this.$message.warning('发票号重复！')
            }
            return
          }
          if (this.statementsForm.hospital.value !== this.addInvoiceForm.hospital.value) {
            let warnings = document.getElementsByClassName('el-icon-warning')
            if(warnings.length === 0) {
              this.$message.warning('发票所属医院应与结算单就诊医院相同！')
            }
            return
          }
          let data = {
            invoiceno: this.addInvoiceForm.invoiceno,
            hospitalcode: this.addInvoiceForm.hospital ? this.addInvoiceForm.hospital.value : '',
            hospitalname: this.addInvoiceForm.hospital ? this.addInvoiceForm.hospital.label : '',
            name: this.addInvoiceForm.name
          }
          this.statementsForm.statementBillsVOS.push(data)
          this.statementsForm.invoicelist = this.statementsForm.statementBillsVOS.map(item => {
            return item.invoiceno
          })
        } else {
          return false;
        }
      })
    },
    remoteBills() {
      this.statementsForm.statementBillsVOS = [...this.statementsForm.statementBillsVOS]
    },
    delHandle(index) {
      this.statementsForm.statementBillsVOS.splice(index, 1)
    },
    remoteHospitals(query) {
      if (query !== '') {
        this.loading = true
        const requestData = {
          hospitalName: query,
          oldHospitals: []
        }
        getHospitalLike(requestData).then(response => {
          this.hospitals = response.data
          this.loading = false
        }).catch(error => {
          this.loading = false
          this.$message.error('查询医院列表异常')
        })
      }
    },
    handleChange(data) {
      let obj = {}
      obj = this.hospitals.find((item)=>{//这里的userList就是上面遍历的数据源
        return item.value === data.value;//筛选出匹配数据
      })
      this.$set(this.statementsForm, 'hospitalname', obj.label)
      this.$set(this.statementsForm, 'hospitalcode', obj.value)
      if (data.value === '1100099' || data.value === '1100999' || data.value === '1100019' || data.value === '1100009') {
        this.$set(this.statementsFormRules, 'hospitalremarks', { required: true, message: '不能为空!', trigger: 'change' })
      } else {
        this.$refs.statementsForm.clearValidate('hospitalremarks')
        this.$set(this.statementsFormRules, 'hospitalremarks', { required: false, message: '不能为空!', trigger: 'change' })
      }
    },
    handleChange2(data) {
      let obj = {}
      obj = this.hospitals.find((item)=>{//这里的userList就是上面遍历的数据源
        return item.value === data.value;//筛选出匹配数据
      })
      this.$set(this.addInvoiceForm, 'hospitalname', obj.label)
      this.$set(this.addInvoiceForm, 'hospitalcode', obj.value)
    },
    addAccount() {
      this.$refs.statementsForm.validate((valid) => {
        if (valid) {
          let flag = this.statementsForm.withinvoice === '1'
          if (flag && this.statementsForm.statementBillsVOS.length === 0) {
            let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
            if (warnings.length === 0) this.$message.warning('已选择有发票，请进行关联！')
            return
          }
          let imgType = ''
          this.urls.map(item => {
            if (item.pageId === this.pageId) {
              imgType = item.pageType
            }
          })
          if (this.pageId === 0 || imgType !== '6') {
            let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
            if (warnings.length === 0) this.$message.warning('请选择结算单影像后再进行锁定！')
            return
          }
          if (this.statementsForm.withinvoice === '1') {
            if (this.statementsForm.statementBillsVOS.length === 0) {
              let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
              if (warnings.length === 0) this.$message.warning('已选择有发票，请进行关联！')
              return
            }
          }
          const params = JSON.parse(JSON.stringify(this.statementsForm))
          params.claimno = this.claimNo
          params.startdate = params.statementdate.length ? params.statementdate[0] : ''
          params.enddate = params.statementdate.length ? params.statementdate[1] : ''
          params.hospitalname = params.hospital.value ? params.hospital.label : ''
          params.hospitalcode = params.hospital.value ? params.hospital.value : ''
          params.statementid = params.statementid ? params.statementid : ''
          delete params.statementdate
          delete params.hospital
          delete params.invoicelist
          params.pageid = this.pageId
          if (params.withinvoice === '2') {
            params.statementBillsVOS = []
          }
          addAccount(params).then(response => {
            if (response.status === '1') {
              this.$message.success('保存成功！')
              this.$emit('getBillImages')
              this.resetForm()
              this.$emit('changeImageStatus', this.pageId)
            } else if (response.data.errCode == 'SL012' || response.data.errCode == 'SL011') {
              let warnings = document.getElementsByClassName('el-message__icon el-icon-error')
              if (warnings.length === 0) this.$message({ message: response.data.errMsg ? response.data.errMsg:'操作失败！', type: 'error' })
            } else {
              this.$message.error('保存失败！')
            }
          })
        } else {
          return false;
        }
      });
    },
    selectStatementBills() {
      const params = {
        claimno: this.claimNo, //赔案号
        pageid: this.pageId//图片id
      }
      selectStatementBills(params).then(response => {
        if (response.status === '1' && response.data.length) {
          this.statementsForm = response.data[this.index].snStatementDO
          this.$set(this.statementsForm, 'statementdate', [response.data[this.index].snStatementDO.startdate, response.data[this.index].snStatementDO.enddate])
          this.remoteHospitals(response.data[this.index].snStatementDO.hospitalname)
          this.hospitals = this.hospitals.filter(item => {
            return response.data[this.index].snStatementDO.hospitalcode === item.value
          })
          this.$set(this.statementsForm, 'hospital', { label: response.data[this.index].snStatementDO.hospitalname,value: response.data[this.index].snStatementDO.hospitalcode })
          this.$set(this.statementsForm, 'statementBillsVOS', response.data[this.index].list ? response.data[this.index].list : [])
          this.$set(this.statementsForm, 'imageblured', !!response.data[this.index].snStatementDO.imageblured)
          this.statementsForm.invoicelist = this.statementsForm.statementBillsVOS.map(item => {
            return item.invoiceno
          })
        }
      })
    },
    selectStatementInvoiceno() {
      selectStatementInvoiceno(this.statementsForm.statementno).then(response => {
        if (response.status === '1') {
          // this.associatedinvoice = response.data
        }
      })
    },
    resetForm() {
      this.$refs.statementsForm.resetFields()
      this.$refs.addInvoiceForm.resetFields()
      this.$set(this.statementsForm, 'statementid', '')
      this.statementsForm.invoicelist = []
    },
    newAccount() {}
  }
}
</script>

<style scoped>
 .item_width {
   width: 160px;
 }
 .form_div /deep/.el-form-item__label {
   padding-right: 0;
 }
</style>
