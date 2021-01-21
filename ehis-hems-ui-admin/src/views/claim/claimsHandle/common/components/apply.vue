
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>{{ node==='report'?'报案信息':'申请信息' }}</span>
      <span style="float: right;" >
        <el-button :disabled="disableFlag || acceptFlag" :loading="saveHandleLoading" type="primary" size="mini" @click="saveHandle">保存</el-button>
      </span>
    </div>
    <el-form ref="baseForm" :rules="baseFormRules" :disabled="disableFlag || acceptFlag" :model="baseForm" label-width="160px" label-position="right" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="申请类别：" prop="reason" style="position:relative">
            <el-checkbox-group v-model="baseForm.reason" @change="groupChange">
              <el-checkbox v-for="option in application_categoryOptions" :label="option.dictValue" :key="option.dictValue" :value="option.dictValue">{{ option.dictLabel }}</el-checkbox>
            </el-checkbox-group>
            <el-button type="text" size="mini" class="clear_span" @click="clearcategory">清 空</el-button>
          </el-form-item>
        </el-col>
        <!--        <el-col v-if="node!=='report'" :span="8">-->
        <!--          <el-form-item label="受理日期：" prop="accessDate">-->
        <!--            <el-date-picker-->
        <!--              v-model="baseForm.accessDate"-->
        <!--              style="width: 200px;"-->
        <!--              type="date"-->
        <!--              placeholder="选择日期"/>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <el-col v-if="baseForm.reason.includes('01')" :span="8">
          <el-form-item label="门诊索赔金额（元）：" prop="outpatientClaimAmount">
            <el-input-number v-model="baseForm.outpatientClaimAmount" :min="0" :precision="2" :step="0.1" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col v-if="baseForm.reason.includes('02') || baseForm.reason.includes('03')" :span="8">
          <el-form-item label="住院索赔金额（元）：" prop="hospitalizationClaimAmount">
            <el-input-number v-model="baseForm.hospitalizationClaimAmount" :min="0" :precision="2" :step="0.1" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col v-if="baseForm.reason.includes('04') || baseForm.reason.includes('05') || baseForm.reason.includes('06') || baseForm.reason.includes('07') || baseForm.reason.includes('08') || baseForm.reason.includes('09') || baseForm.reason.includes('10') || baseForm.reason.includes('11') || baseForm.reason.includes('12') || baseForm.reason.includes('13')" :span="8">
          <el-form-item label="定额：" prop="quota">
            <el-input-number :disabled="true" v-model="baseForm.quota" :min="0" :precision="2" :step="0.1" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col v-if="baseForm.reason.includes('04')" :span="8">
          <el-form-item title="首次重疾确诊日期" label="首次重疾确诊日期：" prop="seriousIllnessDate">
            <el-date-picker
              v-model="baseForm.seriousIllnessDate"
              style="width: 200px;"
              type="date"
              placeholder="选择日期"/>
          </el-form-item>
        </el-col>
        <el-col v-if="baseForm.reason.includes('08')" :span="8">
          <el-form-item label="残疾确诊日期：" prop="deformityDate">
            <el-date-picker
              v-model="baseForm.deformityDate"
              style="width: 200px;"
              type="date"
              placeholder="选择日期"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="案件类型：" prop="caseType">
            <el-select v-model="baseForm.caseType" class="item-width" placeholder="请选择">
              <el-option v-for="option in case_typeOptions" :key="option.dictValue" :disabled="option.dictValue==='J' || option.dictValue==='S'" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="事故者现状：" prop="accidentStatus">
            <el-select :disabled="flag" v-model="baseForm.accidentStatus" class="item-width" placeholder="请选择">
              <el-option v-for="option in current_stateOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col v-if="baseForm.accidentStatus === '07'" :span="8">
          <el-form-item label="死亡日期：" prop="deathDate">
            <el-date-picker
              v-model="baseForm.deathDate"
              style="width: 200px;"
              type="date"
              placeholder="选择日期"/>
          </el-form-item>
        </el-col>
        <el-col v-if="baseForm.accidentStatus === '07'" :span="8">
          <el-form-item label="是否火化：" prop="isCremation">
            <el-select v-model="baseForm.isCremation" class="item-width" placeholder="请选择">
              <el-option v-for="option in is_cremationOptions" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出险日期：" prop="eventDate">
            <el-date-picker
              v-model="baseForm.eventDate"
              style="width: 200px;"
              type="date"
              placeholder="选择日期"/>
          </el-form-item>
        </el-col>
        <el-col v-if="node==='report'"  :span="8">
          <el-form-item label="出险地址：" prop="incidentArea">
            <el-cascader
              v-model="baseForm.incidentArea"
              :options="regions"
              class="item-width"/>
          </el-form-item>
        </el-col>
        <el-col v-if="node==='report'" :span="16">
          <el-form-item  label="详细地址：" prop="incidentAddress">
            <el-input v-model="baseForm.incidentAddress" maxlength="200" show-word-limit style="width: 98%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col v-if="node!=='report' && baseForm.accidentStatus === '07'" :span="24">
          <el-form-item label="事故地点：" prop="incidentAddress">
            <el-input v-model="baseForm.incidentAddress" maxlength="200" show-word-limit style="width: 98%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col v-if="node==='report'" :span="24">
          <el-form-item label="报案内容：" prop="accidentDescription">
            <el-input v-model="baseForm.accidentDescription" maxlength="500" type="textarea"  rows="5" show-word-limit style="width: 98%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="24">
          <el-form-item v-if="node!=='report'" prop="isReceipt">
            <el-checkbox v-model="baseForm.isReceipt">是否收件</el-checkbox>
          </el-form-item>
        </el-col> -->
      </el-row>
      <el-row v-if="baseForm.isReceipt">
        <el-col :span="8">
          <el-form-item label="收件日期：" prop="receivedDate">
            <el-date-picker
              v-model="baseForm.receivedDate"
              :disabled="node === 'input'"
              style="width: 200px;"
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
              <!-- <el-button slot="append" icon="el-icon-edit-outline" @click="openDia"/> -->
              <!-- <el-input-number v-model="baseForm.billAmount" controls-position="left" :min="0" :precision="2" :step="0.1" class="item-width" clearable size="mini" placeholder="请输入"/> -->
              <el-button slot="append" @click="openDia">
                <svg class="svg-icon" style="width: 20px; height: 20px; margin-top: 6px;">
                  <use xlink:href="#icon-cacl" />
                </svg>
              </el-button>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="baseForm.isReceipt">
        <el-col :span="8">
          <el-form-item label="快递单号：" prop="trackingNumber">
            <el-input v-model="baseForm.trackingNumber" :disabled="node === 'input'" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="备注：" prop="remarks">
            <el-input v-model="baseForm.remarks" :disabled="node === 'input'" maxlength="500" show-word-limit style="width: 96%;" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-row style="text-align: right;">
        <el-button type="primary" size="mini">打印受理回执</el-button>
      </el-row> -->
    </el-form>
    <el-dialog
      :visible.sync="dialogTableVis"
      :before-close="closeHandle"
      :close-on-click-modal="false"
      width="26%"
      title="发票金额"
      append-to-body>
      <el-form ref="diaForm" :model="diaForm" label-width="0">
        <div ref="diaTable" style="max-height: 300px; overflow: scroll;" >
          <el-table ref="table" :data="diaForm.tableData">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="发票金额" prop="acount" align="center">
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
            <el-table-column fixed="right" label="操作" width="100" align="center">
              <template slot-scope="scope">
                <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delate(scope.$index, scope.row)">删除</el-button>
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
  </el-card>
</template>
<script>
import { applyInfoSave, getAmount, judgeBnf } from '@/api/claim/register'
import getAddressByFront from '../mixins/address'
export default {
  mixins: [getAddressByFront],
  props: {
    dictList:Array,
    status: String,
    node: String,
    registerNo: String,
    insuredNo: String,
    reportSave: Boolean,
    insuredSave: Boolean,
    claimno: String,
    acceptFlag: {
      type: Boolean,
      default: false
    }
  },
  //dicts: ['application_category', 'current_state', 'case_type', 'is_cremation'],
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
    const validateClaimAmount = (rule, value, callback) => {
      if (value !== '' && value !== null && value !== undefined) {
        if (isNaN(parseFloat(value)) || parseFloat(value) <= 0) {
          callback(new Error('索赔金额录入不合法，请检查'))
        } else {
          callback()
        }
      }else{
        callback()
      }
    }
    const validateDate = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请选择日期'))
      } else {
        const val = new Date(value).getTime()
        const current = new Date().getTime()
        if (val > current) {
          callback(new Error('录入日期有误，请重新确认'))
        }
        callback()
      }
    }
    const validateDate1 = (rule, value, callback) => {
      const val = new Date(value).getTime()
      const current = new Date().getTime()
      if (val > current) {
        callback(new Error('录入日期有误，请重新确认'))
      }
      callback()
    }
    return {
      baseForm: {
        tableData: [],
        reason: [],
        isReceipt: false,
        accidentStatus: ''
      },
      diaForm: {
        tableData: []
      },
      flag: false,
      regions: [], // 省市区下拉选项
      disableFlag: false,
      dialogTableVis: false,
      baseFormRules: {
        accessDate: { validator: validateDate, required: true, trigger: 'change' },
        outpatientClaimAmount: { validator: validateClaimAmount, required: false, trigger: ['blur', 'change'] },
        hospitalizationClaimAmount: { validator: validateClaimAmount, required: false, trigger: ['blur', 'change'] },
        reason: { required: true, message: '不能为空!', trigger: 'change' },
        caseType: { required: true, message: '不能为空!', trigger: 'change' },
        accidentStatus: { required: true, message: '不能为空!', trigger: 'change' },
        deathDate: { validator: validateDate1, required: false, trigger: 'change' },
        eventDate: { validator: validateDate, required: true, trigger: 'change' },
        isCremation: { required: false, message: '不能为空!', trigger: 'change' },
        incidentAddress: { required: false, message: '不能为空!', trigger: 'blur' },
        seriousIllnessDate: { validator: validateDate1, required: false, trigger: 'change' },
        deformityDate: { validator: validateDate1, required: false, trigger: 'change' },
        incidentArea: { required: false, message: '不能为空!', trigger: 'change' },
        // 是否收件
        receivedDate: { required: true, message: '不能为空!', trigger: 'change' },
        billCount: { required: false, message: '不能为空!', trigger: 'blur' },
        billAmount: { validator: validateAmount, required: false, trigger: ['blur', 'change'] },
        trackingNumber: { required: true, message: '不能为空!', trigger: 'change' }
      },
      saveHandleLoading: false,
      application_categoryOptions:[],
      current_stateOptions:[],
      case_typeOptions:[],
      is_cremationOptions:[],
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
    this.getAddressInfo()
    if (this.dictList!=null && this.dictList.length!=0) {
      this.application_categoryOptions = this.dictList.find(item => {
        return item.dictType == 'application_category'
      }).dictDate
      this.current_stateOptions = this.dictList.find(item => {
        return item.dictType == 'current_state'
      }).dictDate
      this.case_typeOptions = this.dictList.find(item => {
        return item.dictType == 'case_type'
      }).dictDate
      this.is_cremationOptions = this.dictList.find(item => {
        return item.dictType == 'is_cremation'
      }).dictDate
    }
  },
  methods: {
    saveHandle() {
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          this.saveHandleLoading = true
          if (this.insuredSave) {
            let flag
            if (this.node === 'report') {
              flag = '01'
            } else {
              flag = '02'
            }
            let businessNo
            if (this.node === 'report') {
              businessNo = this.registerNo
            } else {
              businessNo = this.claimno
            }
            const requestData = {
              flag: flag,
              insuredNo: this.insuredNo,
              businessNo: businessNo,
              apply: this.baseForm
            }
            applyInfoSave(requestData).then(res => {
              if (res.status === '1') {
                this.$emit('applySaveUpdate', true)
                this.$emit('renovateList',true)
                if(this.node === 'report') {
                  this.$message({ message: '报案信息保存成功！', type: 'success' })
                  this.saveHandleLoading = false
                } else {
                  this.$message({ message: '申请信息保存成功！', type: 'success' })
                  this.saveHandleLoading = false
                  // this.judgeBnf()
                }
              } else {
                this.$message({ message: '数据存储失败！', type: 'error' })
                this.saveHandleLoading = false
              }
            })
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
    groupChange(val) {
      if (val.includes('04') || val.includes('05') || val.includes('06') || val.includes('07') || val.includes('08') || val.includes('09') || val.includes('10') || val.includes('11') || val.includes('12') || val.includes('13')) {
        if (this.insuredNo !== '' && this.insuredNo != null) {
          getAmount(this.insuredNo).then(response => {
            if (response.status === '1') {
              this.baseForm.quota = response.data
            } else {
              this.baseForm.quota = 0.00
            }
          }).catch(error => {
          })
        }
      }
      if (val.includes('05')) {
        this.baseForm.accidentStatus = '07'
        this.flag = true
      } else {
        this.baseForm.accidentStatus = ''
        this.flag = false
      }
    },
    clearcategory() {
      this.baseForm.reason = []
    },
    delate(index,row){
      console.log(row,56)
      this.$confirm(`是否确定删除?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.diaForm.tableData.splice(index, 1)
        // this.sumCount()
      })
    },
    restSumCount() {
      this.$refs.diaForm.resetFields()
      // this.diaForm.tableData = []
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
    judgeBnf() {
      judgeBnf(this.claimno).then(response => {
          if (response.status === '1') {
            if(response.data) {
              this.$message({ message: '申请类别选择身故，需录入受益人信息！', type: 'warning' })
            }
          }
        })
    }
  }
}
</script>
<style scoped>
.el-dialog__body {
  padding-bottom: 0px;
}
.item-width {
  width: 200px;
}
.el-form /deep/ .el-form-item__label {
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.clear_span {
  position: absolute;
  left: -70px;
  top: 33px;
}
</style>
