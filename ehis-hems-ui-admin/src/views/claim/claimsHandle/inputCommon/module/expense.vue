<template>
  <el-dialog
    title="费用项明细"
    :visible.sync="visible"
    :before-close="closeHandle"
    @open="openHandle">
    <el-table
      :data="expenseData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      @select-all="selectAll"
      @row-click="rowClick"
      @select="handleSelectionChange">
      <el-table-column prop="date" type="selection"/>
      <el-table-column prop="expenseName" label="费用名称"></el-table-column>
      <el-table-column prop="amount" label="总金额"/>
      <el-table-column :formatter="getDetailCategory" prop="expenseType" label="类别"/>
      <el-table-column prop="unitPrice" label="单价"/>
      <el-table-column prop="count" width="120" label="数量"/>
    </el-table>
    <el-row style="margin-top: 10px">
      <el-button :disabled="status === 'show'" type="danger" style="float: right;" size="mini" @click="deleteFeedDetail">删除</el-button>
    </el-row>
    <el-tabs v-model="expenseName">
      <el-tab-pane label="明细信息" name="first">
        <el-form ref="expenseForm" :disabled="status === 'show'" :rules="expenseFormRules" :model="expenseForm" style="margin-top: 20px;" size="mini" label-width="108px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="费用名称：" prop="expenseName">
<!--                <el-select v-model="expenseForm.expenseName" class="item_width" placeholder="请选择" clearable @change="expanseNameChange">-->
<!--                  <el-option v-for="option in list" :key="option.id" :label="option.label" :value="option.value" />-->
<!--                </el-select>-->
                <el-input v-model="expenseForm.expenseName" class="item_width" autocomplete="off" placeholder="请输入" clearable @change="expanseNameChange"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="总金额：" prop="amount">
                <el-input-number v-model="expenseForm.amount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="类别：" prop="expenseType">
                <el-select v-model="expenseForm.expenseType" filterable class="item_width" placeholder="请选择" clearable>
                  <el-option v-for="option in detail_categoryOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="单价：" prop="unitPrice">
                <el-input-number v-model="expenseForm.unitPrice" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="数量：" prop="count">
                <el-input-number v-model="expenseForm.count" :min="0" :step="1" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
              </el-form-item>
            </el-col>
<!--            <el-col :span="12">-->
<!--              <el-form-item label="自费比例：" prop="ratio">-->
<!--                <el-input-number v-model="expenseForm.ratio" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
          </el-row>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <span slot="footer" class="dialog-footer clearfix">
      <span style="float: right">
        <el-button type="primary" style="margin-right: 10px" @click="closeHandle">取 消</el-button>
        <el-button :disabled="status === 'show'" type="primary" style="float: right;" @click="feedDetailsSave">保存</el-button>
      </span>
    </span>
  </el-dialog>
</template>

<script>

import { feedDetailsSave, deleteFeedDetail, getFeedDetail } from '@/api/claim/input'

export default {
  //dicts: ['detail_category'],
  props: {
    dictList: {
      type: Array
    },
    visible: {
      type: Boolean,
      default: function() {
        return false
      }
    },
    detailedno: {
      type: String,
      default: function() {
        return ''
      }
    },
    status: {
      type: String,
      default: function() {
        return ''
      }
    },
    cosAmount: {
      type: Number,
      default: function() {
        return 0
      }
    }
  },
  data() {
    return {
      expenseData: [],
      expenseName: 'first',
      expenseFormRules: {
        expenseName : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        amount : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
      },
      expenseForm: {
        expenseName: '',
        amount: '',
        expenseType: '',
        unitPrice: '',
        count: ''
      },
      feeitems: [],
      list: [{
        value: '01',
        label: '绷带',
        id: '0'
      },{
        value: '02',
        label: '针管',
        id: '1'
      },{
        value: '03',
        label: '胶带',
        id: '3'
      }],
      delData: [],
      detail_categoryOptions: [],
    }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.detail_categoryOptions = this.dictList.find(item => {
        return item.dictType == 'detail_category'
      }).dictDate
    }
  },
  methods: {
    openHandle() {
      this.getFeedDetail()
    },
    confirmHandle() {
      this.$emit('confirmHandle')
    },
    closeHandle() {
      this.$emit('closeHandle')
    },
    getDetailCategory(row) {
      //return this.dict.label.detail_category[row.expenseType]
      return this.selectDictLabel(this.detail_categoryOptions, row.expenseType);
    },
    selectAll(selection) {
      if (selection.length !== 0) {
        this.delData = selection.map(item => item.expenseCode)
      } else {
        this.delData = []
      }
    },
    handleSelectionChange(val, row) {
      if (val.length > 0) {
        this.delData = val.map(item => item.expenseCode)
        this.expenseForm = JSON.parse(JSON.stringify(row))
      } else {
        this.delData = []
        this.resetForm()
      }
    },
    rowClick(row) {
      this.expenseForm = JSON.parse(JSON.stringify(row))
    },
    expanseNameChange() {
      // this.$refs.expenseForm.resetFields()
    },
    resetForm() {
      this.$refs.expenseForm.resetFields()
      this.$set(this.expenseForm, 'expenseCode', '')
    },
    feedDetailsSave() {
      this.$refs.expenseForm.validate((valid) => {
        if (valid) {
          let amount = 0
          this.expenseData.forEach(item => {
            if (this.expenseForm.expenseCode && item.expenseCode === this.expenseForm.expenseCode) {
            } else {
              amount += item.amount
            }
          })
          amount += this.expenseForm.amount
          if (amount > this.cosAmount) {
            let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
            if (warnings.length === 0) this.$message.warning('费用项明细总金额不可大于费用项总金额！')
            return
          }
          const params = {
            detailNo: this.detailedno,
            feedDetail: JSON.parse(JSON.stringify(this.expenseForm))
          }
          feedDetailsSave(params).then(response => {
            if (response.status === '1') {
              this.$message.success('保存成功！')
              this.getFeedDetail()
              this.resetForm()
            } else {
              this.$message.error('保存失败！')
            }
          })
        } else {
          return false;
        }
      });
    },
    getFeedDetail() {
      getFeedDetail(this.detailedno).then(response => {
        if (response.status === '1') {
          this.expenseData = response.data
          this.delData = []
        }
      })
    },
    deleteFeedDetail() {
      deleteFeedDetail(this.delData).then(response => {
        if (response.status === '1') {
          this.$message.success('删除成功！')
          this.getFeedDetail()
          this.resetForm()
        } else {
          this.$message.success('删除失败！')
        }
      })
    },
    filterExpenseName(row) {
      let data = this.list.filter(item => {
        if (item.value === row.expenseName) return item.label
      })
      return data[0].label
    }
  }
}
</script>

<style scoped>
  .item_width {
    width: 200px;
  }
  .clearfix:after,.clearfix:before{
    content: "";
    display: table;
  }
  .clearfix:after{
    clear: both;
  }
  .clearfix {
    *zoom: 1;
  }
</style>
