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
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="联系人邮箱：" prop="emil">
            <el-input v-model="otherServiceForm.emil" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

        <!--        <el-col :span="8">
                  <el-form-item label="合作部门信息：" prop="coopDepInfo">
                    <el-input v-model="otherServiceForm.coopDepInfo" class="item-width" clearable size="mini"
                              placeholder="请输入"/>
                  </el-form-item>
                </el-col>


                <el-col :span="8">
                  <el-form-item label="集团统一编码：" prop="code">
                    <el-select v-model="otherServiceForm.code" class="item-width" placeholder="请选择" clearable>
                      <el-option value="01" label="一类"></el-option>
                      <el-option value="02" label="二类"></el-option>
                      &lt;!&ndash;                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>&ndash;&gt;
                    </el-select>
                  </el-form-item>
                </el-col>-->
      </el-row>
      <el-row>
        <el-col :span="16">

          <el-col :span="5">
            <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
              <el-select v-model="otherServiceForm.continentFlag" class="item-width" placeholder="请选择是/否" clearable>
                <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item prop="continent">
              <el-input v-model="otherServiceForm.continent" class="item-width" clearable
                        style="padding-left: 15px;width: 450px" maxlength="500"
                        placeholder="请输入"/>

            </el-form-item>
          </el-col>


          <!--          <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
                      <el-select v-model="otherServiceForm.continentFlag" class="item-width" placeholder="请选择是/否" clearable>
                        <el-option label="是" value="01"></el-option>
                        <el-option label="否" value="02"></el-option>
                        &lt;!&ndash;                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>&ndash;&gt;
                      </el-select>

                      <el-input v-model="otherServiceForm.continent" class="item-width" clearable
                                style="padding-left: 15px;width: 450px"
                                placeholder="请输入"/>

                    </el-form-item>-->

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
        <!--        <el-col :span="8">
                  <el-form-item label="是否流程通畅：" prop="processFlow">
                    <el-radio-group v-model="otherServiceForm.processFlow">
                      <el-radio label="01">是</el-radio>
                      <el-radio label="02">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>-->

      </el-row>
      <el-col :span="24">
        <el-form-item label="特殊语种服务：" prop="speciallanguage">
          <el-checkbox-group v-model="otherServiceForm.speciallanguage">
            <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  speciallanguageOptions" >{{item.dictLabel}}</el-checkbox>
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

    return {

      otherSerivceFormRules: {
        displaynetwork: [{required: true, message: '不能为空！', trigger: 'change'}],
        grade: [{required: true, message: '不能为空！', trigger: 'blur'}],
        category: [{required: true, message: '不能为空！', trigger: 'blur'}],
        cooperationStatus: [{required: true, message: '不能为空！', trigger: 'change'}],
        continentFlag: [{required: true, message: '不能为空！', trigger: 'change'}],
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
          return false
        }

      })

    },
    resetForm() {

      /* this.otherServiceForm.grade=''
       this.otherServiceForm.category=''
       this.otherServiceForm.emil=''
       this.otherServiceForm.coopDepInfo=''
       this.otherServiceForm.code=''
       this.otherServiceForm.cooperationStatus=''
       this.otherServiceForm.continentFlag=''
       this.otherServiceForm.continent=''

       this.otherServiceForm.virtualOrg=''
       this.otherServiceForm.isEnabled=''
       this.otherServiceForm.displaynetwork=''
       this.otherServiceForm.officialwebsite=''
       this.otherServiceForm.weekendsOpen=''

       this.otherServiceForm.processFlow=''

       this.otherServiceForm.sellingpoint=''
       this.otherServiceForm.preauthsendway=''
       this.otherServiceForm.foreignnote=''

       this.otherServiceForm.internalnote=''*/

      /*this.otherServiceForm.servicelocator=[]
      this.otherServiceForm.speciallanguage=[]*/
      this.$refs.otherServiceForm.resetFields()

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
.el-form-item /deep/ label {
  font-weight: normal;
}


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
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
