<template>
  <el-form ref="baseForm" :rules="baseFormRules" :disabled="status === 'show' || disabled" :model="baseForm" label-width="98px" label-position="right" size="mini">
    <el-row>
      <el-col :span="12">
        <el-form-item label="姓名：" prop="insuredName">
          <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="性别：" prop="sex">
          <el-select v-model="baseForm.sex" class="item-width" placeholder="请选择">
            <el-option v-for="option in sexOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="证件类型：" prop="idType">
          <el-select v-model="baseForm.idType" class="item-width" placeholder="请选择">
            <el-option v-for="option in card_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="证件号：" prop="idNo">
          <el-input v-model="baseForm.idNo" class="item-width" clearable size="mini" placeholder="请输入" @change="idNoChange"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="出生日期：" prop="birthDate">
          <el-date-picker
            v-model="baseForm.birthdate"
            value-format="yyyy-MM-dd"
            class="item-width"
            type="date"
            placeholder="选择日期"/>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="证件有效期：" prop="dateRange">
          <el-date-picker
            v-model="baseForm.dateRange"
            :disabled="baseForm.checked === true"
            value-format="yyyy-MM-dd"
            class="item-width"
            style="width: 205px;"
            type="daterange"
            range-separator="~"
            start-placeholder="开始日期"
            end-placeholder="结束日期"/>
        </el-form-item>
      </el-col>
      <el-col :span="1">
        <el-form-item style="padding-left: 55px;" label-width="0" prop="validSign">
          <el-checkbox :disabled="baseForm.dateRange!==null&&baseForm.dateRange.length !== 0" v-model="baseForm.checked" @change="rulesFlag">长期</el-checkbox>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
import { updateInsured, comparjsonInsuredInfo } from '@/api/claim/input'

export default {
  //dicts: ['sex', 'card_type'],
  props: {
    dictList:Array,
    claimNo: {
      type: String,
      default: function() {
        return ''
      },
    },
    disabled: {
      type: Boolean,
      default: function() {
        return false
      }
    },
    status: {
      type: String,
      default: function() {
        return ''
      }
    },
    imageState: {
      type: Array,
      default: function() {
        return []
      }
    },
  },
  data() {
    return {
      baseForm: {
        insuredName: '',
        sex: '',
        idType: '0',
        idNo: '',
        birthdate: '',
        dateRange: [],
        checked: false
      },
      baseFormRules: {
        insuredName: { required: true, message: '不能为空!', trigger: 'change' },
        idNo: { required: true, message: '不能为空!', trigger: 'change' },
        dateRange: { required: true, message: '不能为空!', trigger: 'change' },
      },
      sexOptions: [],
      card_typeOptions: []
    }
  },
  watch: {
    baseForm: {
      immediate: true,
      handler(newVal, oldVal) {
        this.idNoChange(newVal.idNo)
      }
    }
  },
  mounted() {
      this.sexOptions = this.dictList.find(item => {
        return item.dictType == 'sex'
      }).dictDate
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType == 'card_type'
      }).dictDate
  },
  methods: {
    saveBaseHandle() {
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          this.comparjsonInsuredInfo()
        } else {
          return false;
        }
      });
    },
    comparjsonInsuredInfo() {
      console.log(this.imageState)
      // if (this.imageState.length === 0) {
      //   let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
      //   if (warnings.length === 0) {
      //     this.$message.warning('请先选择影像状态！')
      //   }
      //   return
      // }
      const params = JSON.parse(JSON.stringify(this.baseForm))
      params.claimNo = this.claimNo
      comparjsonInsuredInfo(params).then(response => {
        if (response.data === '确认要更改数据吗？') {
          this.$confirm('录入基本信息与受理时不一致，请处理！', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.updateInsured()
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
          });
        } else if (response.data === '数据一样，无需更改') {
          this.$message.warning(response.data + '！')
        } else {
          this.$message.error('比对被保人失败！')
        }
      })
    },
    rulesFlag(value) {
      if(value) {
        this.baseFormRules.dateRange = { required: false, message: '不能为空!', trigger: 'change' }
        this.baseForm.dateRange = []
        this.$refs.baseForm.clearValidate('dateRange')
      }else {
        this.baseFormRules.dateRange = { required: true, message: '不能为空!', trigger: 'change' }
      }
    },
    updateInsured() {
      const params = JSON.parse(JSON.stringify(this.baseForm))
      params.claimNo = this.claimNo
      updateInsured(params).then(response => {
        if (response.status === '1') {
          this.$message.success('保存成功！')
          this.$emit('findInsuredInfoByClaimno')
        } else {
          this.$message.success('保存失败！')
        }
      })
    },
    getGender(data) {
      if (this.baseForm.idType !== '0' || data === '') {
        return
      }
      if (parseInt(data.substr(16, 1)) % 2 === 1) {
        this.$set(this.baseForm, 'sex', '0')
      } else {
        this.$set(this.baseForm, 'sex', '1')
      }
    },
    getBirth(data) {
      if (this.baseForm.idType !== '0') {
        return
      }
      let birthday = ''
      if(data.length === 15){
        birthday = "19" + data.slice(6,12)
      } else if(data.length === 18){
        birthday = data.slice(6,14)
      }
      //通过正则表达式来指定输出格式为:1990-01-01
      this.$set(this.baseForm, 'birthdate', birthday.replace(/(.{4})(.{2})/,"$1-$2-"))
    },
    idNoChange(data) {
      // 身份证带出出生日期、性别
      this.getGender(data)
      this.getBirth(data)
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 160px;
  }
  .el-form-item /deep/.el-form-item__label {
    padding-right: 0;
  }
</style>
