<template>
  <el-form ref="invoiceForm" class="form_div" :disabled="status === 'show'" :rules="invoiceFormRules"
           :model="invoiceForm" size="mini" label-width="112px">
    <el-row>
      <el-col :span="12">
        <el-form-item label="就诊人姓名：" prop="patient">
          <el-input v-model="invoiceForm.patient" :maxlength="20" class="item_width" autocomplete="off"
                    placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="发票号：" prop="invoiceNo">
          <el-input v-model="invoiceForm.invoiceNo" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="发票类型：" prop="invoiceType">
          <el-select v-model="invoiceForm.invoiceType" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in invoicetypeOptions" :key="option.id" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="票面类型：" prop="faceType">
          <el-select v-model="invoiceForm.faceType" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in Invoice_face_typeOptions" :key="option.id" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="票据类型：" prop="noteType">
          <el-select v-model="invoiceForm.noteType" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in Invoice_typeOptions" :key="option.id" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="两章是否齐全：" prop="isCompleteTwoChapters">
          <el-select v-model="invoiceForm.isCompleteTwoChapters" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in chapters_completeOptions" :key="option.id" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="发票起/止日期：" prop="dateRange">
          <el-date-picker
            v-model="invoiceForm.dateRange"
            style="width: 205px;"
            type="daterange"
            range-separator="~"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <!--      <el-col :span="12">-->
      <!--        <el-form-item label="账单类型：" prop="billingType">-->
      <!--          <el-select v-model="invoiceForm.billingType" class="item_width" placeholder="请选择" clearable>-->
      <!--            <el-option v-for="option in dict.Invoice_bill_type" :key="option.id" :label="option.label" :value="option.value" />-->
      <!--          </el-select>-->
      <!--        </el-form-item>-->
      <!--      </el-col>-->
      <el-col :span="12">
        <el-form-item label="医保类型：" prop="medicalInsuranceTypes">
          <el-select v-model="invoiceForm.medicalInsuranceTypes" class="item_width" placeholder="请选择" clearable>
            <el-option v-for="option in health_care_typeOptions" :key="option.id" :label="option.dictLabel"
                       :value="option.dictValue"/>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="就诊医院：" prop="hospital">
          <el-select
            v-model="invoiceForm.hospital"
            :remote-method="remoteHospitals"
            :loading="loading"
            filterable
            remote
            style="width: 160px"
            reserve-keyword
            placeholder="请输入医院关键字"
            @change="handleChange"
            clearable>
            <el-option v-for="option in hospitals" :key="option.value" :label="option.label" :value="option.value"/>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="医院备注：" prop="hospitalRemark">
          <el-input v-model="invoiceForm.hospitalRemark" :maxlength="20" class="item_width" autocomplete="off"
                    placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="医院科室：" prop="hospitalDepartments">
          <el-input v-model="invoiceForm.hospitalDepartments" :maxlength="20" class="item_width" autocomplete="off"
                    placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="总金额：" prop="amount">
          <el-input-number v-model="invoiceForm.amount" :min="0" :precision="2" :step="0.01" class="item_width"
                           autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="统筹支付：" prop="overallPayment">
          <el-input-number v-model="invoiceForm.overallPayment" :min="0" :precision="2" :step="0.01" class="item_width"
                           autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="第三方支付：" prop="thirdPartyPayment">
          <el-input-number v-model="invoiceForm.thirdPartyPayment" :min="0" :precision="2" :step="0.01"
                           class="item_width" autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="比例自付：" prop="payByCategory">
          <el-input-number v-model="invoiceForm.payByCategory" :min="0" :precision="2" :step="0.01" class="item_width"
                           autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="自费金额：" prop="selfPayment">
          <el-input-number v-model="invoiceForm.selfPayment" :min="0" :precision="2" :step="0.01" class="item_width"
                           autocomplete="off" placeholder="请输入" clearable/>
        </el-form-item>
      </el-col>
      <el-col v-if="!invoiceForm.isMedicalRecord" :span="12">
        <el-form-item label="第一诊断：" prop="firstDiagnosis">
          <el-select v-model="invoiceForm.firstDiagnosis"
                     reserve-keyword
                     filterable
                     :remote-method="remoteICD"
                     remote
                     clearable
                     style="width: 160px"
                     placeholder="请选择">
            <el-option v-for="(item, i) in ICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item v-if="!invoiceForm.isMedicalRecord" label="第二诊断：" prop="secondDiagnosis">
          <el-select v-model="invoiceForm.secondDiagnosis"
                     :remote-method="secondarydiagnosisICD"
                     filterable
                     remote
                     reserve-keyword
                     clearable
                     style="width: 160px"
                     placeholder="请选择">
            <el-option v-for="(item, i) in secondarydiagnosisICDArr" :label="item.label + '-' +item.value"
                       :value="item.value" :key="i"/>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item v-if="!invoiceForm.isMedicalRecord" label="第三诊断：" prop="thirdDiagnosis">
          <el-select v-model="invoiceForm.thirdDiagnosis"
                     ref="otherdia"
                     :remote-method="otherdiagnosisICD"
                     filterable
                     multiple
                     collapse-tags
                     remote
                     reserve-keyword
                     clearable
                     style="width: 160px"
                     placeholder="请选择"
                     @change="otherdiaChange">
            <el-option v-for="(item, i) in otherdiagnosisICDArr" :label="item.label + '-' +item.value"
                       :value="item.value" :key="i"/>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="无病历：" prop="isMedicalRecord">
          <el-checkbox v-model="invoiceForm.isMedicalRecord"></el-checkbox>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="影像模糊：" prop="isBlurredImage">
          <el-checkbox v-model="invoiceForm.isBlurredImage"></el-checkbox>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-form-item label="备注：" prop="remark">
          <el-input type="textarea" v-model="invoiceForm.remark" placeholder="请输入" style="width: 90%" clearable/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-button :disabled="status === 'show' || disabled" type="primary" size="mini" @click="invoiceFormRealSave">锁定影像
    </el-button>
    <el-button v-if="!editInvoice" :disabled="status === 'show' || disabled" type="primary" size="mini"
               @click="addInvoice">新建
    </el-button>
  </el-form>
</template>

<script>
  import {getHospitalLike, billInfoSave, judgeBills, getBillInfo} from '@/api/claim/input'
  import {getLikeICD10} from '@/api/claim/handleDeal'

  let dictss = [{dictType: 'Invoice_face_type'}, {dictType: 'invoicetype'}, {dictType: 'Invoice_type'}, {dictType: 'chapters_complete'},
    {dictType: 'Invoice_bill_type'}, {dictType: 'health_care_type'}, {dictType: 'socialsecuritysettlement'}]
  export default {
    //dicts: ['Invoice_face_type', 'invoicetype', 'Invoice_type', 'chapters_complete', 'Invoice_bill_type', 'health_care_type', 'socialsecuritysettlement'],
    props: {
      incidentNo: {
        type: String,
        default: function () {
          return ''
        }
      },
      invoiceNo: {
        type: String,
        default: function () {
          return ''
        }
      },
      claimNo: {
        type: String,
        default: function () {
          return ''
        }
      },
      pageId: {
        type: Number,
        default: function () {
          return 0
        }
      },
      status: {
        type: String,
        default: function () {
          return ''
        }
      },
      urls: {
        type: Array,
        default: function () {
          return []
        }
      },
      disabled: {
        type: Boolean,
        default: function () {
          return false
        }
      },
      editInvoice: {
        type: Number,
        default: function () {
          return 0
        }
      }
    },
    data() {
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
      const validateAmount = (rule, value, callback) => {
        if (!value) {
          callback(new Error('金额不能为空且不能小于0！'))
        } else {
          callback()
        }
      }
      return {
        invoiceForm: {
          invoiceNo: '',
          invoiceType: '',
          faceType: '',
          noteType: '',
          hospitalDepartments: '',
          isCompleteTwoChapters: '',
          billingType: '',
          medicalInsuranceTypes: '',
          socialSecuritySettlement: '',
          amount: '',
          overallPayment: '',
          thirdPartyPayment: '',
          dateRange: [],
          payByCategory: '',
          selfPayment: '',
          isMedicalRecord: false,
          isBlurredImage: false,
          patient: '',
          firstDiagnosis: '',
          secondDiagnosis: '',
          thirdDiagnosis: [],
          hospital: '',
          hospitalRemark: ''
        },
        invoiceFormRules: {
          noteType: {required: true, message: '不能为空!', trigger: 'change'},
          patient: {required: true, message: '不能为空!', trigger: 'change'},
          invoiceNo: {validator: checkInvoive, required: true, trigger: ['blur', 'change']},
          invoiceType: {required: true, message: '不能为空!', trigger: 'change'},
          isCompleteTwoChapters: {required: true, message: '不能为空!', trigger: 'change'},
          dateRange: {required: true, message: '不能为空!', trigger: 'change'},
          billingType: {required: true, message: '不能为空!', trigger: 'change'},
          hospital: {required: true, message: '不能为空!', trigger: 'change'},
          amount: {validator: validateAmount, required: true, trigger: ['blur', 'change']},
          firstDiagnosis: {required: true, message: '不能为空!', trigger: 'change'},
          faceType: {required: true, message: '不能为空!', trigger: 'change'}
        },
        loading: false,
        hospitals: [],
        ICDArr: [],
        dictList:[],
        secondarydiagnosisICDArr: [],
        otherdiagnosisICDArr: [],
        /*Invoice_face_type', 'invoicetype', 'Invoice_type', 'chapters_complete', 'Invoice_bill_type', 'health_care_type', 'socialsecuritysettlement'*/
        Invoice_face_typeOptions: [],
        invoicetypeOptions: [],
        Invoice_typeOptions: [],
        chapters_completeOptions: [],
        Invoice_bill_typeOptions: [],
        health_care_typeOptions: [],
        socialsecuritysettlementOptions: []
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.Invoice_face_typeOptions = this.dictList.find(item => {
        return item.dictType == 'Invoice_face_type'
      }).dictDate
      this.invoicetypeOptions = this.dictList.find(item => {
        return item.dictType == 'invoicetype'
      }).dictDate
      this.Invoice_typeOptions = this.dictList.find(item => {
        return item.dictType == 'Invoice_type'
      }).dictDate
      this.chapters_completeOptions = this.dictList.find(item => {
        return item.dictType == 'chapters_complete'
      }).dictDate
      this.Invoice_bill_typeOptions = this.dictList.find(item => {
        return item.dictType == 'Invoice_bill_type'
      }).dictDate
      this.health_care_typeOptions = this.dictList.find(item => {
        return item.dictType == 'health_care_type'
      }).dictDate
      this.socialsecuritysettlementOptions = this.dictList.find(item => {
        return item.dictType == 'socialsecuritysettlement'
      }).dictDate


    },
    methods: {
      dataChange() {
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
        let obj = {};
        obj = this.hospitals.find((item) => {//这里的userList就是上面遍历的数据源
          return item.value === data;//筛选出匹配数据
        });
        this.invoiceForm.hospitalName = obj.label
        if (data === '1100099' || data === '1100999' || data === '1100019' || data === '1100009') {
          this.$set(this.invoiceFormRules, 'hospitalRemark', {required: true, message: '不能为空!', trigger: 'change'})
        } else {
          this.$refs.invoiceForm.clearValidate('hospitalRemark')
          this.$set(this.invoiceFormRules, 'hospitalRemark', {required: false, message: '不能为空!', trigger: 'change'})
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
      otherdiaChange(val) {
        this.$refs.otherdia.query = ''
      },
      invoiceFormRealSave() {
        this.$refs.invoiceForm.validate((valid) => {
          if (valid) {
            let imgType = ''
            this.urls.map(item => {
              if (item.pageId === this.pageId) {
                imgType = item.pageType
              }
            })
            let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
            if (this.invoiceForm.invoiceType === '02') {
              if (this.invoiceForm.dateRange[0] === this.invoiceForm.dateRange[1]) {
                if (warnings.length === 0) this.$message.warning('发票类型为住院，发票起止日期不可为同一天！')
                return
              }
            }
            if (this.pageId === 0 || imgType !== '4') {
              if (warnings.length === 0) this.$message.warning('请选择发票影像后再进行锁定！')
              return
            }
            this.$set(this.invoiceForm, 'pageId', this.pageId)
            if (this.invoiceForm.dateRange === null || this.invoiceForm.dateRange === undefined || this.invoiceForm.dateRange === '') {
              this.$set(this.invoiceForm, 'dateRange', [])
            }
            if (this.invoiceForm.thirdDiagnosis === null || this.invoiceForm.thirdDiagnosis === undefined || this.invoiceForm.thirdDiagnosis === '') {
              this.$set(this.invoiceForm, 'thirdDiagnosis', [])
            }
            const requestData = {
              claimNo: this.claimNo,
              incidentNo: this.incidentNo,
              billInfo: this.invoiceForm,
            }
            // invoiceNo
            judgeBills(requestData).then(response => {
              if (response.status === '1') {
                if (response.data) {
                  if (this.invoiceNo) requestData.billInfo.oldInvoiceNO = this.invoiceNo
                  billInfoSave(requestData).then(response => {
                    if (response.status === '1') {
                      this.$message({message: '发票信息保存成功！', type: 'success'})
                      this.billNo = response.data
                      this.getInvoiceNo()
                      this.resetForm()
                      if (!this.incidentNo) {
                        this.$emit('getBillImages')
                      }
                      this.$emit('changeImageStatus', this.pageId)
                    } else {
                      this.$message({message: '发票信息保存失败！', type: 'error'})
                    }
                  })
                } else {
                  this.$confirm('该账单号重复存在，是否继续操作？', '温馨提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }).then(() => {
                    billInfoSave(requestData).then(response => {
                      if (response.status === '1') {
                        this.$message({message: '发票信息保存成功！', type: 'success'})
                        this.billNo = response.data
                        this.getInvoiceNo()
                        this.resetForm()
                        if (!this.incidentNo) {
                          this.$emit('getBillImages')
                        }
                        this.$emit('changeImageStatus', this.pageId)
                      } else {
                        this.$message({message: '发票信息保存失败！', type: 'error'})
                      }
                    })
                  }).catch(error => {
                  })
                }
              } else {
                this.$message({message: '发票信息校验失败！', type: 'error'})
              }
            })
          } else {
            return false;
          }
        });
      },
      getInvoiceNo() {
        this.$emit('getInvoiceNo', this.invoiceForm)
      },
      addInvoice() {
        this.resetForm()
      },
      inputgetBillInfo() {
        const params = {
          claimNo: '',
          invoiceNo: '',
          pageId: this.pageId,
        }
        this.resetForm()
        getBillInfo(params).then(response => {
          if (response.status === '1') {
            if (response.data.invoiceNo !== null) {
              this.invoiceForm = response.data
              this.$set(this.invoiceForm, 'isBlurredImage', response.data.isBlurredImage === 'true')
              this.$set(this.invoiceForm, 'isMedicalRecord', response.data.isMedicalRecord === 'true')
              this.hospitals = []
              this.hospitals.push(response.data.hospitalDTO)
              this.ICDArr = []
              this.secondarydiagnosisICDArr = []
              this.$set(this.invoiceForm, 'thirdDiagnosis', response.data.thirdDiagnosis[0] ? response.data.thirdDiagnosis : [])
              response.data.firstDiagnosisDTO ? this.ICDArr.push(response.data.firstDiagnosisDTO) : this.ICDArr = []
              response.data.secondDiagnosisDTO ? this.secondarydiagnosisICDArr.push(response.data.secondDiagnosisDTO) : this.secondarydiagnosisICDArr = []
              this.otherdiagnosisICDArr = response.data.thirdDiagnosisDTOList[0].value ? response.data.thirdDiagnosisDTOList : []
              this.getInvoiceNo()
            }
          } else if (response.status === '0') {
            this.$message.error('发票查询失败！')
          }
        })
      },
      resetForm() {
        this.$refs.invoiceForm.resetFields()
        this.$set(this.invoiceForm, 'firstDiagnosis', '')
        this.$set(this.invoiceForm, 'secondDiagnosis', '')
        this.$set(this.invoiceForm, 'thirdDiagnosis', '')
      }
    }
  }
</script>

<style scoped>
  .item_width {
    width: 155px;
  }

  /deep/ .el-select__tags-text {
    width: 39px;
    display: inline-block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .form_div /deep/ .el-form-item__label {
    padding-right: 0;
  }
</style>
