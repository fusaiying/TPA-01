<template>
  <el-form ref="departmentForm" :rules="departmentFormRules" :model="departmentForm" size="small"
           :disabled="disabledFlag">
    <el-card class="box-card department-style" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>科室信息</span>
        <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle" >保存</el-button>
        </span>
      </div>
      <el-table ref="departmentTable" :data="departmentForm.form"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column label="就诊类型" prop="visitingType" align="center" >
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id " :prop="'form.' + scope.$index + '.visitingType'"
                          :rules="departmentFormRules.visitingType">
              <el-select v-model="scope.row.visitingType" class="item-width" placeholder="请选择" clearable size="mini"
                         style="width:150px">

                <el-option v-for="item in visitingTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
            <span v-else>{{ scope.row.visitingTypeName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="一级科室" prop="firstDept" align="center" >
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.firstDept'"
                          :rules="departmentFormRules.firstDept">
              <el-input v-model="scope.row.firstDept" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.firstDept }}</span>
          </template>
        </el-table-column>
        <el-table-column label="二级科室" prop="secondDept" align="center" >
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.secondDept'"
                          :rules="departmentFormRules.secondDept">
              <el-input v-model="scope.row.secondDept" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.secondDept }}</span>
          </template>
        </el-table-column>
        <el-table-column label="就诊楼层" prop="visitingFloor" align="center" >
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.visitingFloor'"
                          :rules="departmentFormRules.visitingFloor">
              <el-input v-model="scope.row.visitingFloor" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.visitingFloor }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否特色科室" prop="charactdeptFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id">
              <el-select v-model="scope.row.charactdeptFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>

            <span v-else>{{getCharactdeptFlag(scope.row.charactdeptFlag) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否直结" prop="straightknotFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id">
              <el-select v-model="scope.row.straightknotFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
            <span v-else>{{ getCharactdeptFlag(scope.row.straightknotFlag )}}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否可医保卡" prop="cartevitalFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id ">
              <el-select v-model="scope.row.cartevitalFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
            <span v-else>{{ getCharactdeptFlag(scope.row.cartevitalFlag )}}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否特需" prop="spprocurementFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id">
              <el-select v-model="scope.row.spprocurementFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
            <span v-else>{{  getCharactdeptFlag(scope.row.spprocurementFlag )}}</span>
          </template>
        </el-table-column>
        <el-table-column label="出诊时间" align="center" width="400">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.callstarttime'"
                          :rules="departmentFormRules.callstarttime" align="center">
              <el-date-picker v-model="scope.row.callstarttime" type="datetime" placeholder="选择出诊开始时间" size="mini"  value-format="yyyy-MM-dd HH:mm:ss"
      style="width: 190px;"/>

            </el-form-item>
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.callendtime'"
                          :rules="departmentFormRules.callendtime" align="center">
              <el-date-picker v-model="scope.row.callendtime" type="datetime" placeholder="选择出诊结束时间" size="mini"  value-format="yyyy-MM-dd HH:mm:ss"

                              style="width: 190px;padding-left: 10px"/>

            </el-form-item>
            <span v-else>{{ scope.row.callstarttime }}-{{ scope.row.callendtime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="100" >
          <template slot-scope="scope">
                    <span>
                      <el-button type="text" size="mini" v-if="departmentTableShow && !scope.row.isSet"
                                 @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                       <el-button type="text" size="mini" v-if="departmentTableShow"
                                  @click="delHandle(scope.$index, scope.row)">删除</el-button>
                    </span>
          </template>
        </el-table-column>

      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addDepartmentHandle()"> + 添加
      </el-button>

    </el-card>
  </el-form>
</template>
<script>
import {getdepInfo, adddepInfo} from "@/api/baseInfo/medicalManage";

export default {
  props: {
    departmentForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    dictList: Array,
    departmentTableShow: Boolean,
    providerCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean,
    status: String


  },



  data() {
    const checkCallstarttime = (rules, value, callback) => {
      const index = rules.field.replace('form.', '').replace('.callstarttime', '').replace('.callendtime', '')
      const {callstarttime, callendtime} = this.departmentForm.form[index]
      if ((callstarttime==undefined ||callstarttime == null || callstarttime == ''  ) ) {
        console.log('---------------')
        this.indexContacts[index] = 1
        callback(new Error('出诊开始时间不能为空'))
      }
      else {
        if(callendtime!=undefined ||callendtime!=null || callendtime !='') {
          if (callstarttime >= callendtime) {
            callback(new Error('出诊开始时间要小于出结束至时间'))
          } else {
            callback()
          }
        }
        else {
          callback()
        }
      }
    }
    const checkCallendtime = (rules, value, callback) => {
      const index = rules.field.replace('form.', '').replace('.callstarttime', '').replace('.callendtime', '')
      const {callstarttime, callendtime} = this.departmentForm.form[index]
      if ((callendtime==undefined ||callendtime == null || callendtime == ''  ) ) {
        this.indexContacts[index] = 1
        callback(new Error('出诊结束时间不能为空'))
      }
      else {
        if(callstarttime!= undefined || callstarttime !=null || callstarttime!=''  ) {
          if (callstarttime >= callendtime) {
            callback(new Error('出诊结束时间要大于出诊开始时间'))
          } else {
            callback()
          }
        }
        else {
          callback()
        }
      }
    }

    return {
      //出诊开始时间下标
      indexContacts: [],
      departmentFormRules: {
        callendtime: [{required: true,validator: checkCallendtime, trigger: 'blur'}],
        callstarttime: [{required: true, validator: checkCallstarttime, trigger: 'blur'}],
        visitingType: [{required: true, message: '不能为空！', trigger: 'change'}],
        firstDept: [{required: true, message: '不能为空！', trigger: 'blur'}],
        secondDept: [{required: true, message: '不能为空！', trigger: 'blur'}],
        visitingFloor: [{required: true, message: '不能为空！', trigger: 'blur'}],

      },

      visitingTypeOptions: [],
      yes_or_noOptions: [],


    }
  },
  watch: {
    dictList: function (newVal, oldVal) {
      this.init();
    },


  },
  mounted() {

  },
  methods: {



    getCharactdeptFlag(data) {
      return this.selectDictLabel(this.yes_or_noOptions, data)
    },


    init() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.visitingTypeOptions = this.dictList.find(item => {
          return item.dictType == 'visiting_type'
        }).dictDate
        this.yes_or_noOptions = this.dictList.find(item => {
          return item.dictType == 'yes_or_no'
        }).dictDate
      }
    },
    //科室信息保存
    saveHandle() {
      this.$refs.departmentForm.validate((valid) => {
        if(this.departmentForm.form!=null && this.departmentForm.form.length>0) {
            if (valid) {
              //存在调用科室信息保存的接口
              if (this.providerCode) {
                //调用新增的接口
                /*  if(isAdd){*/
                let formData = {
                  providerCode: this.providerCode,
                  form: this.departmentForm.form,
                  orgFlag: this.status
                }
                adddepInfo(formData).then(res => {
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
              /* //修改的接口
           else{
             updatedepInfo(this.departmentFrom.form).then(res => {
               if (res.code=='200') {
                 this.$message({
                   message: '修改成功！',
                   type: 'success',
                   center: true,
                   showClose: true
                 })

               } else  {
                 this.$message({
                   message: '修改失败!',
                   type: 'error',
                   center: true,
                   showClose: true
                 })
               }
             })
           }

         }*/
            } else {
              return false
            }
        }
        else {
          this.$message({
            message: '至少添加一条科室信息！',
            center: true,
            type: "warning"
          });
        }

      })
    },
    //科室删除按钮  是否调用了数据库的接口

    delHandle(index, row) {
      this.departmentForm.form.splice(index, 1)
    },
    editHandle(index, row) {
      this.departmentForm.form[index].id = ''
      this.departmentForm.form[index].isSet = true

      this.departmentTableShow = true
    },
    //增加一行科室信息
    addDepartmentHandle() {
      const field = {
        visitingType: '',
        firstDept: '',
        secondDept: '',
        visitingFloor: '',
        charactdeptFlag: '',
        straightknotFlag: '',
        cartevitalFlag: '',
        spprocurementFlag: '',
        callTime: '',
        providerCode: this.providerCode,
        isSet: true,
      }


      this.departmentForm.form.push(field)
    },

    //重置表单
    resetForm() {
      this.$refs.departmentForm.resetFields();
      this.departmentForm.form = []

    },




// 校验数据
    validateForm() {
      let flag = null
      if(this.departmentForm.form!=null && this.departmentForm.form.length>0) {
        this.$refs['departmentForm'].validate(valid => {
          if (valid) {
            flag = '01'
          } else {
            flag = '03'
          }
        })
      }
      else {
        flag= '02'
      }
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
</style>
