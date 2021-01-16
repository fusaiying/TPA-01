<template>
  <el-form ref="contactInfoForm" :rules="contactInfoFormRules" :model="contactInfoForm" size="small" :disabled="disabledFlag">
    <el-card class="box-card" style="margin-top: 10px;" >
      <div slot="header" class="clearfix">
        <span>联系信息</span>
        <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle" >保存</el-button>
        </span>
      </div>

      <el-table ref="contactTable" :data="contactInfoForm.contacts"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column prop="placeType" align="center" header-align="center" label="联系人类型">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.placeType'"
                          :rules="contactInfoFormRules.placeType" style="display: inline-flex !important;">
              <el-select v-model="scope.row.placeType" placeholder="请选择" size="mini" clearable>
<!--                <el-option v-for="option in contacttype"  :label="option.label"
                           :value="option.value"/>
                <el-option/>-->
                <el-option
                  v-for="item in place_typeOptions"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{scope.row.placeTypeName }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="name" align="center" header-align="center" label="姓名">
          <template slot-scope="scope">
            <el-input v-if="!scope.row.id" v-model="scope.row.name" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" align="center" header-align="center" label="手机">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.phone'"
                          :rules="contactInfoFormRules.phone" style="display: inline-flex !important;">
            <el-input  v-model="scope.row.phone" placeholder="请输入" size="mini"/>
            </el-form-item>

            <span v-else>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="mobile" align="center" header-align="center" label="电话">
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.mobile" placeholder="请输入" size="mini"/>
            <span v-else>{{ scope.row.mobile }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="email" align="center" header-align="center" label="邮箱">
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.email" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="workingTime" align="center" header-align="center" label="工作时间" width="400px">
          <template slot-scope="scope">
            <el-input v-if="!scope.row.id" v-model="scope.row.workingStart" placeholder="请输入" size="mini"
                      style="width: 150px"/>
            <el-input v-if="!scope.row.id" v-model="scope.row.workingEnd" placeholder="请输入" size="mini"
                      style="width: 150px ;padding-left: 10px"/>
            <span v-else>{{ scope.row.workingStart }} - {{ scope.row.workingEnd }} </span>
          </template>

        </el-table-column>
        <el-table-column prop="role" align="center" header-align="center" label="登录名">
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.role" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.role }}</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" min-width="94" fixed="right">
          <template slot-scope="scope">

              <span style="cursor: pointer;">
                  <el-button type="text" size="mini" v-if="contactInfoTableShow && !scope.row.isSet"
                             @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="text"  v-if="contactInfoTableShow" size="mini" @click="delHandle(scope.$index,scope.row)">删除</el-button>

                </span>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addContactHandle"> + 添加
      </el-button>
    </el-card>
    <el-dialog
      :visible.sync="dialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title="提示"
      width="30%">
      <span>{{ '删除当前行联系人信息？' }}</span>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="delConfirm">确 定</el-button>
        </span>
    </el-dialog>
  </el-form>


</template>
<script>
import {addcontactsInfo} from "@/api/baseInfo/medicalManage";
import {validPhone} from "@/utils/validate";



export default {
  props: {
    contactInfoForm: {
      type : Object,
      default: function (){
        return {}
      }
    },
    contactInfoTableShow: Boolean,
    supplierCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean,
    dictList: Array,
    status: String
  },

  data() {
    let checkValid = (rule, value, callback) => {
      if (value) {
        let str = value.replace(/\s/g, "")
        if (!validPhone(str)) {
          if (!/\d{3}-\d{8}|\d{4}-\d{7,8}/.test(str)) {
            callback(new Error('输入格式不正确'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    return {
      dialogVisible: false,
      modalValue: false,
      index: '',
      place_typeOptions: [],
      contactInfoFormRules: {
        placeType: [{required: true, message: '不能为空！', trigger: 'change'}],
        phone:[{validator: checkValid, trigger: 'blur'}],

      },
    }
  },


  watch: {
    dictList: function (newVal, oldVal) {
      this.init();
    }

  },
  mounted() {

  },


  methods: {
    init(){
      if (this.dictList != null && this.dictList.length != 0) {
        this.place_typeOptions = this.dictList.find(item => {
          return item.dictType == 'place_type'
        }).dictDate

      }
    },
//联系信息保存
    saveHandle() {

      this.$refs.contactInfoForm.validate((valid) => {
        if(this.contactInfoForm.contacts!=null && this.contactInfoForm.contacts.length>0) {
          if (valid) {
            if (this.supplierCode) {
              this.contactInfoForm.contacts.supplierCode = this.supplierCode
              /*  if(this.isAdd){*/
              let formData = {
                providerCode: this.supplierCode,
                contacts: this.contactInfoForm.contacts,
                orgFlag: this.status
              }
              addcontactsInfo(formData).then(res => {
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
            /*else{
              updatecontactsInfo(this.contactInfoForm.contacts).then(res => {
                if (res.code == '200') {
                  this.$message({
                    message: '修改成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                } else  {
                  this.$message({
                    message: '修改失败!' ,
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
        this.$message({
          message: '联系信息不能为空',
          center: true,
          type: "warning"
        });

      })
    },

    delHandle(index, row) {
      this.dialogVisible = true
      this.index = index
    },
    delConfirm() {
      this.dialogVisible = false
      this.contactInfoForm.contacts.splice(this.index,1)
    },
    //联系信息添加
    addContactHandle() {
      const field = {
        placeType: '',
        name: '',
        phone: '',
        mobile: '',
        email: '',
        workingStart: '',
        workingEnd: '',
        role: '',
        supplierCode: this.supplierCode,
        isSet: true
      }
      this.contactInfoForm.contacts.push(field)

    },
    editHandle(index,row){
      this.contactInfoForm.contacts[index].id = ''
      this.contactInfoForm.contacts[index].isSet = true
      this.departmentTableShow=true
    },

    //重置
    resetForm()
    {
      this.$refs.contactInfoForm.resetFields();
      this.contactInfoForm.contacts=[]


    },
    // 校验数据
    validateForm () {
      let flag = null
      this.$refs['contactInfoForm'].validate(valid => {
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
</style>
