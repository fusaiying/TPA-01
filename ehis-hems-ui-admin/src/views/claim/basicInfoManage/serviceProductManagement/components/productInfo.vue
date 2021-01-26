<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>产品基本信息</span>
      <span style="float: right;">
          <el-button size="mini" type="primary" @click="baseFormSaveHandle">保存</el-button>
        </span>
    </div>
    <el-form ref="baseForm" :model="baseForm" label-width="170px" :rules="baseInfoRule" :disabled="disableFlag && status=='edit'"
             label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="产品编码：" >
            <el-input v-model="baseForm.productCode" class="item-width" clearable size="mini" disabled
                      placeholder="系统自动生成"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品中文名称：" prop="productChname">
            <el-input v-model="baseForm.productChname" class="item-width" clearable size="mini" maxlength="300"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品英文名称：" prop="productEnname">
            <el-input v-model="baseForm.productEnname" class="item-width" clearable size="mini" maxlength="300"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="对外产品中文名称：" prop="outProductChname">
            <el-input v-model="baseForm.outProductChname" class="item-width" clearable size="mini" maxlength="300"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="对外产品英文名称：" prop="outProductEnname">
            <el-input v-model="baseForm.outProductEnname" class="item-width" clearable size="mini" maxlength="300"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品类型：" prop="productType">
            <el-select v-model="baseForm.productType" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品期限：" prop="productDeadline">
            <el-input v-model="baseForm.productTimeInfo" class="item-width" clearable size="mini" style="width: 90px"
                      clearable
                      placeholder="请输入"/>
            <el-select v-model="baseForm.productTimeTime" class="item-width" placeholder="请选择" size="mini" clearable
                       style="width: 110px">
              <el-option v-for="item in productTimeTimeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医护管家：" prop="steward">
            <el-select v-model="baseForm.steward" class="item-width" placeholder="请选择" clearable>
              <!--                <el-option v-for="item in bussiness_statusOptions" :label="item.dictLabel" :value="item.dictValue" multiple collapse-tags="true"
                                         :key="item.dictValue"/>-->
              <el-option label="测试" value="01"></el-option>
            </el-select>
          </el-form-item>
        </el-col>


      </el-row>
    </el-form>


  </el-card>


</template>

<script>

import {checkField,getProductInfo,insertProductInfo} from '@/api/baseInfo/serviceProductManagement'


export default {
  props: {
    parProductChname: {
      type: String,
      default: ''
    },
    parProductEnname: {
      type: String,
      default: ''
    },
    parOutProductChname: {
      type: String,
      default: ''
    },
    parOutProductEnname: {
      type: String,
      default: ''
    },
    productCode: {
      type: String,
      default: ''
    },
    status: {
      type: String,
      default: ''
    },
    disableFlag: Boolean
  },

  watch: {
    parProductChname: function (newValue) {
      this.copyProductChname = newValue
    },
    parProductEnname: function (newValue) {
      this.copyProductEnname = newValue
    },
    parOutProductChname: function (newValue) {
      this.copyOutProductChname = newValue
    },
    parOutProductEnname: function (newValue) {
      this.copyOutProductEnname = newValue
    }
  },


  data() {

    const checkProductChname = (rules, value, callback) => {
      if (value) {
        let query = {
          productChname: this.baseForm.productChname
        }
        if (this.copyProductChname !== value) {
          //调用查询的接口
          checkField(query).then(res => {
            if (res != null && res.code == '200' && res.data.length > 0) {
              callback(new Error('产品中文名称已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      } else {
        callback(new Error('产品中文名称不能为空'))

      }

    }
    const checkProductEnname = (rules, value, callback) => {
      if (value) {
        let query = {
          productEnname: this.baseForm.productEnname
        }
        if (this.copyProductEnname !== value) {
          //调用查询的接口
          checkField(query).then(res => {
            if (res != null && res.code == '200' && res.data.length > 0) {
              callback(new Error('产品英文名称已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      } else {
        callback(new Error('产品英文名称不能为空'))

      }

    }
    const checkOutProductChname = (rules, value, callback) => {
      if (value) {
        let query = {
          outProductChname: this.baseForm.outProductChname
        }
        if (this.copyOutProductChname !== value) {
          //调用查询的接口
          checkField(query).then(res => {
            if (res != null && res.code == '200' && res.data.length > 0) {
              callback(new Error('对外产品中文名称已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      } else {
        callback(new Error('对外产品中文名称不能为空'))

      }

    }
    const checkOutProductEnname = (rules, value, callback) => {
      if (value) {
        let query = {
          outProductEnname: this.baseForm.outProductEnname
        }
        if (this.copyOutProductEnname !== value) {
          //调用查询的接口
          checkField(query).then(res => {
            if (res != null && res.code == '200' && res.data.length > 0) {
              callback(new Error('对外产品英文名称已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      } else {
        callback(new Error('对外产品英文名称不能为空'))

      }

    }
    const checkProductDeadline = (rules, value, callback) => {
      let reg = /^(\d+|\d+\.)$/
      if(this.baseForm.productTimeInfo){
        if (this.baseForm.productTimeInfo < 0) {
          callback(new Error("请输入正整数"));
        } else if (!reg.test(this.baseForm.productTimeInfo)) {
          callback(new Error("请输入正整数"));
        } else {
          if(this.baseForm.productTimeTime) {
            callback();
          }
          else {
            callback(new Error("产品期限不能为空"))
          }
        }
      }
      else {
        callback(new Error('产品期限不能为空'))
      }


    /*  if (this.baseForm.productTimeInfo && this.baseForm.productTimeTime) {
        /!*if(this.baseForm.produc tTimeInfo)*!/
        if (this.baseForm.productTimeInfo < 0) {
          callback(new Error("只能输入数字"));
        } else if (!reg.test(this.baseForm.productTimeInfo)) {
          callback(new Error("只能输入数字"));
        } else {
          callback();
        }
      } else {
        callback(new Error('产品期限不能为空'))
      }*/
    }
    return {
      copyOutProductEnname: '',
      copyOutProductChname: '',
      copyProductChname: '',
      copyProductEnname: '',
      otherProductCode:'',

      baseForm: {},

      baseInfoRule: {
        productChname: [{required: true, validator: checkProductChname, trigger: 'blur'}],
        productEnname: [{required: true, validator: checkProductEnname, trigger: 'blur'}],
        outProductChname: [{required: true, validator: checkOutProductChname, trigger: 'blur'}],
        outProductEnname: [{required: true, validator: checkOutProductEnname, trigger: 'blur'}],
        productType: [{required: true, message: '产品类型不能为空', trigger: 'change'}],
        steward: [{required: true, message: '医院管家不能为空', trigger: 'change'}],
        productDeadline: [{required: true, validator: checkProductDeadline, trigger: ['blur', 'change']}],
      },
      productTypeOptions: [],
      productTimeTimeOptions: []
    }
  },
  created() {
    //获取产品定义信息
    this.getDicts("productType").then(response => {
      this.productTypeOptions = response.data;
    });
    this.getDicts("productTimeTime").then(response => {
      this.productTimeTimeOptions = response.data;
    });

    this.init()

  },
  methods: {
    init(){
     if(this.productCode!=null && this.productCode!='') {
       let queryData = {
         productCode: this.productCode,
       }
       getProductInfo(queryData).then(res => {
         if (res.code == '200') {
           this.baseForm = res.data
         }
         this.copyOutProductEnname=this.baseForm.outProductEnname
         this.copyOutProductChname=this.baseForm.outProductChname
         this.copyProductChname=this.baseForm.productChname
         this.copyProductEnname=this.baseForm.productEnname

       })

     }

    },

    delHandle(index, row) {
    },
    baseFormSaveHandle(){
      this.$refs['baseForm'].validate(valid => {
        if(valid){
          insertProductInfo(this.baseForm).then(res => {
            if (res.code == '200') {
              this.$message({
                message: '保存成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.otherProductCode=res.data.productCode
              this.baseForm.productCode=res.data.productCode
              this.copyOutProductEnname=this.baseForm.outProductEnname
              this.copyOutProductChname=this.baseForm.outProductChname
              this.copyProductChname=this.baseForm.productChname
              this.copyProductEnname=this.baseForm.productEnname
              this.$emit('saveFlag')
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

      })
    },


    // 校验数据
    async validateForm() {
      let flag
      await new  Promise((resolve, reject) => {
        this.$refs['baseForm'].validate(valid => {
          if (valid) {
            flag = true
          } else {
            flag = false
          }
        })
        resolve(flag)
      })
      return flag
    }



    /*    getOrgFlag(row){
    return this.selectDictLabel(this.org_flagOptions, row.orgFlag)
  },*/


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


</style>

