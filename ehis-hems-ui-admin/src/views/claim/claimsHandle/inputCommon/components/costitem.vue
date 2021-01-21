<template>
  <div>
    <el-table
      :data="detailData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      @select-all="selectAll"
      @row-click="rowClick"
      @select="handleSelectionChange">
      <el-table-column align="center" prop="date" type="selection"/>
      <el-table-column align="center" prop="feeitemname" label="费用类型"/>
      <el-table-column align="center" prop="amount" label="总金额"/>
      <el-table-column align="center" prop="unreasonableamount" label="不合理费用"/>
      <el-table-column align="center" prop="unreasonableexplain" width="120" label="不合理费用说明"/>
    </el-table>
    <p>
      <span style="font-size: 14px; display: inline-block; padding-top: 6px;">累计总金额：{{ amount | numFilter }}</span>
      <el-button :disabled="status === 'show'" type="danger" style="float: right;" size="mini" @click="deleteDetail">删除</el-button>
      <el-button type="primary" style="float: right;margin-right: 10px" size="mini" @click="expenseDetails">明细信息</el-button>
      <el-button type="primary" style="float: right;" size="mini" :disabled="status === 'show'" @click="costDetailSave">保存</el-button>
    </p>
    <el-divider/>
    <el-form ref="invoiceFormDetail" :rules="invoiceFormDetailRules" :disabled="status === 'show'" :model="invoiceFormDetail" label-width="108px" label-position="right" size="mini">
      <el-row>
        <el-col :span="12">
          <el-form-item label="费用项类型：" prop="feetIem">
            <el-select v-model="invoiceFormDetail.feetIem" filterable class="item_width" placeholder="请选择" clearable @change="feeItemChange">
              <el-option v-for="option in feeitems" :key="option.id" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总金额：" prop="amount">
            <el-input-number v-model="invoiceFormDetail.amount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="统筹支付：" prop="overallPayment">
            <el-input-number v-model="invoiceFormDetail.overallPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="第三方支付：" prop="thirdPartyPayment">
            <el-input-number v-model="invoiceFormDetail.thirdPartyPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="比例自付：" prop="payByCategory">
            <el-input-number v-model="invoiceFormDetail.payByCategory" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="自费金额：" prop="selfPayment">
            <el-input-number v-model="invoiceFormDetail.selfPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="不合理费用：" prop="unReasonableAmount">
            <el-input-number v-model="invoiceFormDetail.unReasonableAmount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" @change="inputAmount" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="不合理费用说明：" prop="unReasonableExplain">
            <el-input style="width: 90%" v-model="invoiceFormDetail.unReasonableExplain" type="textarea" placeholder="请输入内容" maxlength="500" show-word-limit class="item_width" autocomplete="off" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <expense-modal :dictList="dictList" :cosAmount="cosAmount" :status="status" :visible="dialogVisible" :detailedno="detailedno" @confirmHandle="confirmHandle" @closeHandle="closeHandle"></expense-modal>
  </div>
</template>

<script>
import { getCostDetailList, costDetailSave, deleteDetails } from '@/api/claim/input'
import { getFeeitems } from '@/api/claim/register'
import expenseModal from "../module/expense";
/*import handel from "../../specialCase/handel";*/

export default {
  components: {
    expenseModal
  },
  filters: {
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '') {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = '--'
      }
      return realVal
    }
  },
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
    invoiceForm: {
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
    return {
      amount: 0.00,
      editAmount: 0.00,
      detailData: [],
      invoiceFormDetailRules: {
        feetIem : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        amount : { validator: validateAmount, required: true, trigger: ['blur', 'change'] },
        unReasonableExplain : { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      },
      invoiceFormDetail: {
        feetIem: '',
        amount: '',
        overallPayment: '',
        thirdPartyPayment: '',
        payByCategory: '',
        selfPayment: '',
        unReasonableAmount: '',
        unReasonableExplain: ''
      },
      feeitems: [],
      dialogVisible: false,
      detailedno: '',
      delData: [],
      cosAmount: 0
    }
  },
  mounted() {
    this.getFeeitem()
  },
  methods: {
    resetForm() {
      this.$refs.invoiceFormDetail.resetFields()
      this.$set(this.invoiceFormDetail, 'detailedno', '')
    },
    feeItemChange() {
      // this.$refs.invoiceFormDetail.resetFields()
    },
    selectAll(selection) {
      if (selection.length !== 0) {
        this.delData = selection.map(item => item.detailedno)
      } else {
        this.delData = []
      }
    },
    handleSelectionChange(selection, row) {
      if (selection.length !== 0) {
        this.delData = selection.map(item => item.detailedno)
        this.detailedno = selection[selection.length - 1].detailedno
        this.resetForm()
        this.$set(this.invoiceFormDetail, 'feetIem', row.feeitemcode)
        this.$set(this.invoiceFormDetail, 'amount', row.amount)
        this.$set(this.invoiceFormDetail, 'overallPayment', row.overallpayment)
        this.$set(this.invoiceFormDetail, 'thirdPartyPayment', row.thirdpartypayment)
        this.$set(this.invoiceFormDetail, 'payByCategory', row.paybycategory)
        this.$set(this.invoiceFormDetail, 'selfPayment', row.selfpayment)
        this.$set(this.invoiceFormDetail, 'unReasonableAmount', row.unreasonableamount)
        this.$set(this.invoiceFormDetail, 'unReasonableExplain', row.unreasonableexplain)
        this.cosAmount = row.amount
      } else {
        this.delData = []
        this.resetForm()
      }
    },
    rowClick(row) {
      // this.resetForm()
      this.$set(this.invoiceFormDetail, 'detailedno', row.detailedno)
      this.$set(this.invoiceFormDetail, 'feetIem', row.feeitemcode)
      this.$set(this.invoiceFormDetail, 'amount', row.amount)
      this.$set(this.invoiceFormDetail, 'overallPayment', row.overallpayment)
      this.$set(this.invoiceFormDetail, 'thirdPartyPayment', row.thirdpartypayment)
      this.$set(this.invoiceFormDetail, 'payByCategory', row.paybycategory)
      this.$set(this.invoiceFormDetail, 'selfPayment', row.selfpayment)
      this.$set(this.invoiceFormDetail, 'unReasonableAmount', row.unreasonableamount)
      this.$set(this.invoiceFormDetail, 'unReasonableExplain', row.unreasonableexplain)
    },
    getCostDetails() {
      const requestData = {
        claimNo: this.claimNo,
        invoiceNo: this.invoiceForm.invoiceNo
      }
      getCostDetailList(requestData).then(response => {
        if (response.status === '1') {
          this.detailData = response.data
          this.amount = 0.00
          if (response.data !== null && response.data.length > 0) {
            for (let i = 0; i < response.data.length; i++) {
              this.amount += response.data[i].amount
            }
          }
          this.delData = []
        } else {
          this.$message({ message: '发票明细列表查询失败！', type: 'error' })
        }
      })
    },
    deleteDetail() {
      if (this.delData.length === 0) {
        let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
        if (warnings.length === 0) {
          this.$message.warning('请先选择费用项！')
        }
        return
      }
      deleteDetails(this.delData).then(response => {
        if (response.status === '1') {
          this.$message.success('删除成功！')
          this.getCostDetails()
          this.resetForm()
        } else {
          this.$message.error('删除失败！')
        }
      })
    },
    inputAmount(value) {
      if(value > 0) {
        this.invoiceFormDetailRules.unReasonableExplain = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
      } else {
        this.invoiceFormDetailRules.unReasonableExplain = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
      }
    },
    getFeeitem() {
      getFeeitems().then(response => {
        if (response.status === '1') {
          this.feeitems = response.data
        } else {
          this.$message({ message: '费用项信息初始化失败！', type: 'error' })
        }
      })
    },
    expenseDetails() {
      if (!this.detailedno) {
        let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
        if (warnings.length === 0) {
          this.$message.warning('请先选择费用项！')
        }
        return
      }
      this.dialogVisible = true
    },
    confirmHandle() {
      this.dialogVisible = false
    },
    closeHandle() {
      this.dialogVisible = false
    },
    costDetailSave() {
      this.$refs.invoiceFormDetail.validate((valid) => {
        if (valid) {
          // this.invoiceForm.amount
          let amount = 0
          let sameFlag = false
          // this.invoiceForm.detailedno
          this.detailData.forEach(item => {
            if (this.invoiceFormDetail.detailedno && item.detailedno === this.invoiceFormDetail.detailedno) {
            } else {
              amount += item.amount
            }
            // if (item.feeitemcode === this.invoiceFormDetail.feetIem) {
            //   sameFlag = true
            // }
          })
          amount += this.invoiceFormDetail.amount
          let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
          // if (sameFlag) {
          //   if (warnings.length === 0) {
          //     this.$message.warning('费用项不可重复！')
          //   }
          //   return
          // }
          // this.invoiceForm.detailedno
          if (amount > this.invoiceForm.amount) {
            if (warnings.length === 0) {
              this.$message.warning('费用项总金额不可大于发票总金额！')
            }
            return
          }
          if(this.invoiceFormDetail.amount < (parseFloat(this.invoiceFormDetail.overallPayment + this.invoiceFormDetail.thirdPartyPayment + this.invoiceFormDetail.unReasonableAmount).toFixed(2)-0.00)) {
            this.$message({ message: '统筹+第三方+不合理金额总和不能大于总金额', type: 'warning' })
            return false
          }
          const params = {
            claimNo: this.claimNo,
            invoiceNo: this.invoiceForm.invoiceNo,
            costDetail: this.invoiceFormDetail
          }
          costDetailSave(params).then(response => {
            if (response.status === '1') {
              this.$message({ message: '费用项信息保存成功！', type: 'success' })
              this.getCostDetails()
              this.resetForm()
            } else {
              this.$message({ message: '费用项信息保存失败！', type: 'error' })
            }
          })
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
  .item_width {
    width: 150px;
  }
</style>
