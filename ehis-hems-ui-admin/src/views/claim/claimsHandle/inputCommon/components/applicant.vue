<template>
  <el-form ref="applicantForm" :disabled="status === 'show'" :rules="applicantFormRules" :model="applicantForm" label-width="98px" label-position="right" size="mini">
    <el-row>
      <el-col :span="12">
        <el-form-item label="申请人姓名：" prop="applicant">
          <el-input v-model="applicantForm.applicant" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="性别：" prop="applicantsex">
          <el-select v-model="applicantForm.applicantsex" disabled class="item-width" placeholder="请选择">
            <el-option v-for="option in sexOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="证件类型：" prop="applicantidtype">
          <el-select v-model="applicantForm.applicantidtype" disabled class="item-width" placeholder="请选择">
            <el-option v-for="option in card_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="证件号码：" prop="applicantidcardno">
          <el-input v-model="applicantForm.applicantidcardno" disabled class="item-width" clearable size="mini" placeholder="请输入" @input="getGender('baseForm')"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="与被保人关系：" prop="relationship">
          <el-select v-model="applicantForm.relationship" disabled class="item-width" placeholder="请选择" @change="changeInfo">
            <el-option v-for="option in relation_ship_applyOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="手机：" prop="applicantmobile">
          <el-input v-model="applicantForm.applicantmobile" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="邮箱：" prop="applicantmail">
          <el-input v-model="applicantForm.applicantmail" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="地址：" prop="applicationaddress">
          <el-input v-model="applicantForm.applicationaddress" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
import { applicationInfoByClaimNo } from '@/api/claim/input'

export default {
  //dicts: ['sex', 'card_type', 'relation_ship_apply'],
  props: {
    dictList: {
      type:Array
    },
    claimNo: {
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
    }
  },
  mounted() {
    this.applicationInfoByClaimNo()
    if (this.dictList!=null && this.dictList.length!=0) {
      this.sexOptions = this.dictList.find(item => {
        return item.dictType == 'sex'
      }).dictDate
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType == 'card_type'
      }).dictDate
      this.relation_ship_applyOptions = this.dictList.find(item => {
        return item.dictType == 'relation_ship_apply'
      }).dictDate

    }
  },
  data() {
    return {
      applicantForm: {
        applicant: '',
        applicantsex: '',
        applicantidtype: '',
        applicantidcardno: '',
        relationship: '',
        applicanttelephone: '',
        applicantmail: '',
        accidentaddress: ''
      },
      applicantFormRules: {
        idCard: [{required: true, message: '不能为空!', trigger: 'blur'}],
        mobile: [{required: true, message: '不能为空!', trigger: 'blur'}]
      },
      /*'sex', 'card_type', 'relation_ship_apply'*/
      sexOptions: [],
      card_typeOptions: [],
      relation_ship_applyOptions: []
    }
  },

  methods: {
    // 获取性别
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
    // 申请人选择被保人关系为本人时，自动带入被保人信息
    changeInfo(value) {

    },
    applicationInfoByClaimNo() {
      applicationInfoByClaimNo(this.claimNo).then(response => {
        if (response.status === '1' && response.data) {
          this.applicantForm = response.data
        } else {
          this.$message.error('查询申请人失败！')
        }
      })
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
