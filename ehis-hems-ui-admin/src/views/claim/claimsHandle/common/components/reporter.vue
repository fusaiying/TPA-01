
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item  name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">报案人信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button :disabled="disableFlag" type="primary" :loading="saveHandleLoading" size="mini" @click="saveHandle">保存</el-button>
        </span>
        <el-form ref="baseForm" :rules="baseFormRule" :disabled="disableFlag" :model="baseForm" style="padding-bottom: 30px;" label-width="136px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="与被保人关系：" prop="relationship">
                <el-select v-model="baseForm.relationship" class="item-width" style="width:90%" placeholder="请选择" @change="changeInfo">
                  <el-option v-for="option in relation_ship_registerOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="报案来源：" prop="source">
                <el-select :disabled="true" v-model="source" class="item-width" style="width:90%" placeholder="请选择">
                  <el-option v-for="option in channel_sourceOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="报案人姓名：" prop="reportName">
                <el-input v-model="baseForm.reportName" class="item-width" style="width:90%" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件类型：" prop="cardType">
                <el-select v-model="baseForm.cardType" class="item-width" style="width:90%" placeholder="请选择">
                  <el-option v-for="option in card_typeOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件号码：" prop="idCard">
                <el-input v-model="baseForm.idCard" class="item-width" style="width:90%" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="性别：" prop="gender">
                <el-select v-model="baseForm.gender" class="item-width" style="width:90%" placeholder="请选择">
                  <el-option v-for="option in sexOptions" :key="option.id" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="电话：" prop="phone">
                <el-input v-model="baseForm.phone" class="item-width" style="width:90%" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="手机：" prop="mobile">
                <el-input v-model="baseForm.mobile" class="item-width" style="width:90%" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="邮箱：" prop="email">
                <el-input v-model="baseForm.email" class="item-width" style="width:90%" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import { reportSave } from '@/api/claim/register'
import { validEmail, validPhone } from '@/utils/validate'
export default {
  props: {
    dictList:Array,
    status: String,
    node: String,
    registerNo: String,
    insuredNo: String,
    insuredSave: Boolean,
    insuredBaseInfo: Object,
    source:String
  },
  //dicts: ['relation_ship_register', 'relation_ship_apply', 'card_type', 'sex', 'apply_way', 'channel_source'],
  data() {
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
                let str = value.replace(/\s/g, "")
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
      activeNames: ['1'],
      baseForm: {
        reportName:'',
        cardType:'',
        idCard:'',
        gender:'',
        phone:'',
        mobile:'',
        email: ''
      },
      saveHandleLoading: false,
      disableFlag: false,
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

      relation_ship_registerOptions: [],
      relation_ship_applyOptions: [],
      card_typeOptions: [],
      sexOptions: [],
      apply_wayOptions: [],
      channel_sourceOptions: []
    }
  },
  mounted() {
    /*'relation_ship_register', 'relation_ship_apply', 'card_type', 'sex', 'apply_way', 'channel_source'*/
    if (this.dictList!=null && this.dictList.length!=0) {
      this.relation_ship_registerOptions = this.dictList.find(item => {
        return item.dictType == 'relation_ship_register'
      }).dictDate
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
      this.channel_sourceOptions = this.dictList.find(item => {
        return item.dictType == 'channel_source '
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
            const requestData = {
              flag: flag,
              insuredNo: this.insuredNo,
              businessNo: this.registerNo,
              report: this.baseForm
            }
            reportSave(requestData).then(res => {
              if (res.status === '1') {
                this.$emit('reportSaveUpdate', true)
                this.$message({ message: '报案人信息保存成功！', type: 'success' })
                this.saveHandleLoading = false
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
    // 选择被保人关系为本人时，自动带入被保人信息
    changeInfo(value) {
      if (value === '00') {
        this.baseForm.reportName = this.insuredBaseInfo.insuredName
        this.baseForm.cardType = this.insuredBaseInfo.idType
        this.baseForm.idCard = this.insuredBaseInfo.idNo
        this.baseForm.gender = this.insuredBaseInfo.sex
        this.baseForm.phone = this.insuredBaseInfo.phone
        this.baseForm.mobile = this.insuredBaseInfo.mobiles[0]
        this.baseForm.email = this.insuredBaseInfo.email
      }else {
        this.baseForm.reportName = ''
        this.baseForm.cardType = ''
        this.baseForm.idCard = ''
        this.baseForm.gender = ''
        this.baseForm.phone = ''
        this.baseForm.mobile = ''
        this.baseForm.email = ''
      }
    }
  }
}
</script>
<style scoped>
.el-collapse {
  border: none;
}
.el-collapse /deep/ .el-collapse-item__header {
  font-size: 16px;
  color: #555;
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 400;
  font-size: 14px;
}
</style>
