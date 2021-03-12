<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>基本信息</span>
      <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle">保存</el-button>
        </span>
    </div>
    <el-form ref="otherBaseForm" :rules="otherBaseFormRules" :model="otherBaseForm" style="padding-bottom: 30px;"
             label-width="170px" size="mini" class="baseInfo_class">
      <el-row>
        <el-col :span="8">
          <el-form-item label="其他机构编码：" prop="providerCode">
            <el-input v-model="otherBaseForm.providerCode" class="item-width" clearable disabled size="mini"
                      placeholder="系统自动生成"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="经度：" prop="longitude">
            <el-input v-model="otherBaseForm.longitude" class="item-width" clearable size="mini" placeholder="请输入" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="纬度：" prop="latitude">
            <el-input v-model="otherBaseForm.latitude" class="item-width" clearable size="mini" placeholder="请输入" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="中文展示名称：" prop="chname1">
            <el-input v-model="otherBaseForm.chname1" class="item-width" clearable size="mini" placeholder="请输入" maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="英文展示名称：" prop="enname1">
            <el-input v-model="otherBaseForm.enname1" class="item-width" clearable size="mini" maxlength="50"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="官方网址：" prop="website">
            <el-input v-model="otherBaseForm.website" class="item-width" clearable size="mini" placeholder="请输入" maxlength="50"/>
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="中文工商注册名称：" prop="chregister">
            <el-input v-model="otherBaseForm.chregister" class="item-width" clearable size="mini" placeholder="请输入" maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="英文工商注册名称：" prop="enregister">
            <el-input v-model="otherBaseForm.enregister" class="item-width" clearable size="mini" placeholder="请输入" maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col  :span="8">
          <el-form-item label="官方电话："  prop="phone">
            <el-input v-model="otherBaseForm.areacode" class="item-width" clearable style="width: 75px"
                      placeholder="区号"/>
            —
            <el-input v-model="otherBaseForm.phone" class="item-width" clearable size="mini" style="width: 145px"
                      placeholder="请输入电话" maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="传真：" prop="portraiture">
            <el-input v-model="otherBaseForm.portraiture" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="公共邮箱：" prop="email">
            <el-input v-model="otherBaseForm.email" class="item-width" clearable size="mini" placeholder="请输入" maxlength="50"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮编：" prop="zipcode">
            <el-input v-model="otherBaseForm.zipcode" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>



      <el-row>
        <el-col :span="8">
          <el-form-item label="州：" prop="continent">
            <el-select v-model="otherBaseForm.continent" class="item-width" placeholder="请选择" clearable>
              <el-option label="亚洲" value="01"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="国家：" prop="country">
            <el-select v-model="otherBaseForm.country" class="item-width" placeholder="请选择" clearable>
              <el-option label="中国" value="01"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="省市区：" prop="address">
            <el-cascader
              v-model="otherBaseForm.address" clearable
              :props="{ checkStrictly: true }"
              :options="regions"
              class="item-width"
              @change="handleChange"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="状态：" prop="bussinessStatus">
            <el-select v-model="otherBaseForm.bussinessStatus" class="item-width" placeholder="请选择" clearable disabled>
              <el-option v-for="item in bussiness_statusOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="中文地址：" prop="chaddreess">
          <el-input v-model="otherBaseForm.chaddreess" clearable size="mini" placeholder="请输入" maxlength="300"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="英文地址：" prop="enaddress">
          <el-input v-model="otherBaseForm.enaddress" clearable size="mini" placeholder="请输入"  maxlength="300"/>
        </el-form-item>
      </el-row>
      <el-row>

        <el-col :span="8">
          <el-form-item label="标志性建筑：" prop="landmarks">
            <el-input v-model="otherBaseForm.landmarks" class="item-width" clearable size="mini" maxlength="500"
                      placeholder="c端客户可看"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="停车场：" prop="carpark">
            <el-input v-model="otherBaseForm.carpark" class="item-width" clearable size="mini" placeholder="c端客户可看" maxlength="500"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="交通路线：" prop="transitroute">
            <el-input v-model="otherBaseForm.transitroute" class="item-width" clearable size="mini" maxlength="500"
                      placeholder="c端客户可看"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="机构简介：" prop="introduction">
          <el-input type="textarea" v-model="otherBaseForm.introduction" clearable size="mini" placeholder="请输入"
                    maxlength="2000"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="备注(中文)：" prop="chremarks">
          <el-input v-model="otherBaseForm.chremarks" clearable size="mini" placeholder="请输入" maxlength="2000"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="备注(英文)：" prop="enremarks">
          <el-input v-model="otherBaseForm.enremarks" clearable size="mini" placeholder="请输入" maxlength="2000"/>
        </el-form-item>
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
import {
  getapplyInfo,
  addapplyInfo,
  updateapplyInfo,
  updateInfo,
  addInfo,
  getAddress, checkfield,checkfieldNew
} from "@/api/baseInfo/medicalManage";

export default {
  props: {
    otherBaseForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    providerCode: String,
    status: String,
    isAdd: Boolean,
    otherChname1: String,
    otherEnname1: String,
  },
  watch: {
    otherChname1: function (newValue){
      this.copyChname1=newValue
    },
    otherEnname1: function (newValue){
      this.copyEnname1=newValue
    }
  },

  data() {
    const checkLatitude = (rules, value, callback) => {
      if(value){
        let reg= /^(\-|\+?)\d+(\.\d+)?$/
        if (!reg.test(value)) {
          callback(new Error("只能输入数字"));
        }
        else {
          callback()
        }
      }
      else {
        callback('不能为空')
      }

    }
    const checkLongitude = (rules, value, callback) => {
      if(value){
        let reg= /^(\-|\+?)\d+(\.\d+)?$/
        if (!reg.test(value)) {
          callback(new Error("只能输入数字"));
        }
        else {
          callback()
        }
      }
      else {
        callback('不能为空')
      }

    }
    const checkAddreess = (rules, value, callback) => {
      if (this.otherBaseForm.address!=null && this.otherBaseForm.address.length>0) {
        callback()
      } else {
        callback(new Error('省市区不能为空！'))
      }
    }
    const checkChname1 = (rules, value, callback) => {
      if (value) {
        let query = {
          chname1: this.otherBaseForm.chname1
        }
        if (this.copyChname1 !== value) {
          //调用查询的接口
          checkfieldNew(query).then(res => {
            if (res != null && res.code == '200' && res.data.length>0) {
              callback(new Error('中文展示名称已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      } else {
        callback(new Error('中文展示名称不能为空'))

      }

    }
    const checkEnname1 = (rules, value, callback) => {
      if (value) {
        let query = {
          enname1: this.otherBaseForm.enname1
        }
        if (this.copyEnname1 !== value) {
          //调用查询的接口
          checkfieldNew(query).then(res => {
            if (res != null && res.code == '200' && res.data.length>0) {
              callback(new Error('英文展示名称已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      } else {
        callback(new Error('英文展示名称不能为空'))

      }

    }
    return {
      copyChname1: '',
      copyEnname1: '',
      otherProviderCode: '',
      regions: [],
      bussiness_statusOptions: [],
      otherBaseFormRules: {
        bussinessStatus: [{required: true, message: '不能为空！', trigger: 'change'}],
        confirmDeliverytime: [{required: true, message: '不能为空！', trigger: 'blur'}],
        longitude: [{required: true,validator: checkLongitude, trigger: 'blur'}],
        latitude: [{required: true, validator: checkLatitude,  trigger: 'blur'}],
        chname1: [{required: true,  validator: checkChname1,  trigger: 'blur'}],
        enname1: [{required: true, validator: checkEnname1, trigger: 'blur'}],
        website: [{required: true, message: '不能为空！', trigger: 'blur'}],
        continent: [{required: true, message: '不能为空！', trigger: 'change'}],
        country: [{required: true, message: '不能为空！', trigger: 'change'}],
        address: [{required: true,validator: checkAddreess, trigger: 'change'}],
        chaddreess: [{required: true, message: '不能为空！', trigger: 'blur'}],
        enaddress: [{required: true, message: '不能为空！', trigger: 'blur'}],
        landmarks: [{required: true, message: '不能为空！', trigger: 'blur'}],
        carpark: [{required: true, message: '不能为空！', trigger: 'blur'}],
        introduction: [{required: true, message: '不能为空！', trigger: 'blur'}]
      },
    }
  },

  created() {
    this.getAddressData()

    this.getDicts("bussiness_status").then(response => {
      this.bussiness_statusOptions = response.data;
    });
  },

  methods: {
    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },

    saveHandle() {
      this.$refs.otherBaseForm.validate((valid) => {
        if (valid) {
          const subFormSearch = JSON.parse(JSON.stringify(this.otherBaseForm))
          if (this.otherBaseForm.address) {
            if(this.otherBaseForm.address[0]) {
              subFormSearch.province = this.otherBaseForm.address[0]
            }
            if(this.otherBaseForm.address[2]) {
              subFormSearch.district = this.otherBaseForm.address[2]
            }
            if(this.otherBaseForm.address[1]) {
              subFormSearch.city = this.otherBaseForm.address[1]
            }
          }
          subFormSearch.orgFlag = this.status
          //存在调用基本信息保存的接口
          if (this.providerCode) {
            updateInfo(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.copyChname1=this.otherBaseForm.chname1
                this.copyEnname1=this.otherBaseForm.enname1
              } else {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
          } else {
            subFormSearch.orgFlag = this.status
            //不存在调用新增的接口
            addInfo(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.otherProviderCode = res.data.providerCode
                this.otherBaseForm.providerCode = this.otherProviderCode
                this.copyChname1=this.otherBaseForm.chname1
                this.copyEnname1=this.otherBaseForm.enname1
                //
                this.$emit('baseSave')

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
          this.$message.warning('基本信息必录项未必录')
        }

      })

    },
    handleChange() {
    },
    resetForm() {
      this.otherBaseForm.areacode=''
      this.$refs.otherBaseForm.resetFields()

    },

    // 校验数据
    async  validateForm () {
      let flag
      await new Promise((resolve, reject)=> {
        this.$refs['otherBaseForm'].validate(valid => {
          if (valid) {
            flag = true
            resolve(flag)
          } else {
            flag = false
            resolve(flag)
          }
        })
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
</style>
