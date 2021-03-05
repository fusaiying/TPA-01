<template>
  <el-form ref="otherServiceForm" :rules="otherSerivceFormRules" :model="otherServiceForm" :disabled="disabledFlag"
           label-width="170px" size="mini" class="baseInfo_class">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务信息</span>
        <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle">保存</el-button>
        </span>
      </div>

      <el-row>
        <el-col :span="8">

          <el-form-item label="机构评分：" prop="grade">
            <el-input v-model="otherServiceForm.grade" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>


        </el-col>
        <el-col :span="8">
          <el-form-item label="机构类别：" prop="category">
            <el-select v-model="otherServiceForm.category" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in spprocurement_flagOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="联系人邮箱：" prop="emil">
            <el-input v-model="otherServiceForm.emil" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>


      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
            <el-select v-model="otherServiceForm.continentFlag" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
            <el-input v-model="otherServiceForm.continent" class="item-width" clearable
                      style="width: 450px" maxlength="500"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="8">
          <el-form-item label="服务定位：" prop="servicelocator">
            <el-checkbox-group v-if="otherServiceForm.servicelocator" v-model="otherServiceForm.servicelocator">
              <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  servicelocator_flagOptions" >{{item.dictLabel}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否虚拟机构：" prop="virtualOrg">
            <el-radio-group v-model="otherServiceForm.virtualOrg">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  virtual_orgOptions" >{{item.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否同步显示至外网：" prop="displaynetwork">
            <el-radio-group v-model="otherServiceForm.displaynetwork">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  yes_or_noOptions" >{{item.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否在网站显示：" prop="officialwebsite">
            <el-radio-group v-model="otherServiceForm.officialwebsite">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  yes_or_noOptions" >{{item.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="合作状态：" prop="cooperationStatus">
            <el-radio-group v-model="otherServiceForm.cooperationStatus">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  cooperationStatus_flagOptions" >{{item.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>


      </el-row>
      <el-col :span="24">
        <el-form-item label="特殊服务语种：" prop="speciallanguage">
          <el-checkbox-group v-model="otherServiceForm.speciallanguage">
            <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  speciallanguageOptions" @click.native="changeDisabled(item,$event)" :disabled="item.isShow" >{{item.dictLabel}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-col>
      <el-row>
        <el-form-item label="服务特色：" prop="sellingpoint">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherServiceForm.sellingpoint" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="预授权书发送及确认方式：" prop="preauthsendway">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherServiceForm.preauthsendway">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对外备注：" prop="foreignnote">
          <el-input
            type="textarea"
            placeholder="请输入内容（客户可见）"
            v-model="otherServiceForm.foreignnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对内备注：" prop="internalnote">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherServiceForm.internalnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>


    </el-card>

  </el-form>
</template>
<script>
import {getserviceInfo, addserviceInfo, updateserviceInfo} from "@/api/baseInfo/medicalManage";
import {formatDate} from "@/utils";

export default {
  props: {
    otherServiceForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    providerCode: String,
    status: String,
    disabledFlag: Boolean,
    dictList: Array
  },


  data() {
    const checkGrade = (rules, value, callback) => {
      const regx = /^\+?(?:\d(?:\.\d)?|10(?:\.0)?)$/
      if (!regx.test(value)) {
        callback(new Error('评分在0-10，最多保留一位小数！'))
      } else {
        callback()
      }
    }
    const checkContinentFlag = (rules, value, callback) => {
      if(value){
        if(this.otherServiceForm.continent!=null && this.otherServiceForm.continent!=''){
          callback()
        }
        else {
          callback(new Error('二证齐全是否发预授权书不能为空'))
        }
      }
      else {
        callback(new Error('二证齐全是否发预授权书不能为空'))
      }


    }
    return {

      otherSerivceFormRules: {
        displaynetwork: [{required: true, message: '不能为空！', trigger: 'change'}],
        grade: [{required: true,validator: checkGrade, trigger: 'blur'}],
        category: [{required: true, message: '不能为空！', trigger: 'change'}],
        cooperationStatus: [{required: true, message: '不能为空！', trigger: 'change'}],
        continentFlag: [{required: true,validator: checkContinentFlag, trigger: 'change'}],
        speciallanguage: [{required: true, message: '不能为空！', trigger: 'change'}],
        preauthsendway: [{required: true, message: '不能为空！', trigger: 'blur'}]
      },
      yes_or_noOptions: [],
      servicelocator_flagOptions: [],
      virtual_orgOptions: [],
      cooperationStatus_flagOptions: [],
      speciallanguageOptions: [],
      spprocurement_flagOptions: []
    }
  }
  ,
  watch: {
    dictList: function (newVal, oldVal) {
      this.init();
    }

  },
  mounted() {

  }
  ,
  methods: {
    changeDisabled(val, event) {

      if (val.dictValue == '10' && event.target.checked) {
        this.speciallanguageOptions.forEach(item => {
          if (item.index < 10) {
            item.isShow = true
          }
        })
        this.otherServiceForm.speciallanguage = []
      } else {
        this.speciallanguageOptions.map(item => {
          item.isShow = false
        })

      }
    },
    init(){
      if (this.dictList != null && this.dictList.length != 0) {
        this.yes_or_noOptions = this.dictList.find(item => {
          return item.dictType == 'yes_or_no'
        }).dictDate
        this.servicelocator_flagOptions = this.dictList.find(item => {
          return item.dictType == 'servicelocator_flag'
        }).dictDate
        this.virtual_orgOptions = this.dictList.find(item => {
          return item.dictType == 'virtual_org'
        }).dictDate
        this.cooperationStatus_flagOptions = this.dictList.find(item => {
          return item.dictType == 'cooperationStatus_flag'
        }).dictDate
        this.speciallanguageOptions = this.dictList.find(item => {
          return item.dictType == 'speciallanguage'
        }).dictDate

        this.spprocurement_flagOptions = this.dictList.find(item => {
          return item.dictType == 'spprocurement_flag'
        }).dictDate

      }
    },

    saveHandle() {
      this.$refs.otherServiceForm.validate((valid) => {
        this.otherServiceForm.providerCode = this.providerCode
        if (valid) {
          //存在调用服务信息保存的接口
          if (this.otherServiceForm.providerCode) {

            const subFormSearch = JSON.parse(JSON.stringify(this.otherServiceForm))
            subFormSearch.orgFlag=this.status
            addserviceInfo(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
              } else {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
          }
        } else {
          this.$message.warning('服务信息必录项未必录')
        }

      })

    },
    resetForm() {
      this.$refs.otherServiceForm.resetFields()
      this.otherServiceForm.continent=''

    },
    // 校验数据
    validateForm() {
      let flag = null
      this.$refs['otherServiceForm'].validate(valid => {
        if (valid) {
          flag = true
        } else {
          flag = false
        }
      })
      return flag
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item ::v-deep label {
  font-weight: normal;
}



.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}

.el-dialog {
  display: flex;
  flex-direction: column;
  margin: 0 !important;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*height:600px;*/
  max-height: calc(100% - 30px);
  max-width: calc(100% - 30px);
}

.el-dialog .el-dialog__body {
  flex: 1;
  overflow: auto;
}

</style>
