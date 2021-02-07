<template>
  <el-form ref="contactInfoForm" :rules="contactInfoFormRules" :model="contactInfoForm" size="small" :disabled="disabledFlag">
    <el-card class="box-card" style="margin-top: 10px;" >
      <div slot="header" class="clearfix">
        <span>联系人信息</span>
        <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle" >保存</el-button>
        </span>
      </div>

      <el-table ref="contactTable" :data="contactInfoForm.contacts"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column prop="placeType" align="center" header-align="center" label="联系人类型" show-overflow-tooltip>
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
        <el-table-column prop="name" align="center" header-align="center" label="姓名" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.name'"
                          :rules="contactInfoFormRules.name" style="display: inline-flex !important;">
              <el-input v-if="!scope.row.id" v-model="scope.row.name" placeholder="请输入" size="mini"/>
            </el-form-item>

            <span v-else>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" align="center" header-align="center" label="手机" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.phone'"
                          :rules="contactInfoFormRules.phone" style="display: inline-flex !important;">
              <el-input  v-model="scope.row.phone" placeholder="请输入" size="mini" @change="nameChange(scope.$index)"/>
            </el-form-item>
            <span v-else>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="mobile" align="center" header-align="center" label="电话" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.mobile'"
                          :rules="contactInfoFormRules.mobile" style="display: inline-flex !important;">
              <el-input v-if="!scope.row.id" v-model="scope.row.mobile" placeholder="请输入" size="mini" @change="nameChange(scope.$index)"/>
            </el-form-item>
            <span v-else>{{ scope.row.mobile }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="email" align="center" header-align="center" label="邮箱" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.email'"
                          :rules="contactInfoFormRules.email" style="display: inline-flex !important;">
              <el-input v-if="!scope.row.id" v-model="scope.row.email" placeholder="请输入" size="mini"/>
            </el-form-item>

            <span v-else>{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="workingTime" align="center" header-align="center" label="工作时间" width="400px" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.workingStart'"
                          :rules="contactInfoFormRules.workingStart" style="display: inline-flex !important;">
              <el-input v-if="!scope.row.id" v-model="scope.row.workingStart" placeholder="请输入" size="mini"
                        style="width: 150px"/>
            </el-form-item>
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.workingEnd'"
                          :rules="contactInfoFormRules.workingEnd" style="display: inline-flex !important;">
              <el-input v-if="!scope.row.id" v-model="scope.row.workingEnd" placeholder="请输入" size="mini"
                        style="width: 150px ;padding-left: 10px"/>
            </el-form-item>
            <span v-else>{{ scope.row.workingStart }} - {{ scope.row.workingEnd }} </span>
          </template>

        </el-table-column>
        <el-table-column prop="role" align="center" header-align="center" label="登录名" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.role'"
                          :rules="contactInfoFormRules.role" style="display: inline-flex !important;">
              <el-input v-if="!scope.row.id" v-model="scope.row.role" placeholder="请输入" size="mini"/>
            </el-form-item>

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
    <!--    <el-dialog
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
        </el-dialog>-->
  </el-form>


</template>
<script>
import {addcontactsInfo, getNewtworktypeList} from "@/api/baseInfo/medicalManage";
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

    const checkFormMobilephone = (rules, value, callback) => {
      const index = rules.field.replace('contacts.', '').replace('.phone', '').replace('.mobile', '')
      const {mobile, phone} = this.contactInfoForm.contacts[index]
      const phonereg = /^[1][3|4|5|6|7|8|9][0-9]{9}$/
      if (( phone === null || phone === '') && (mobile === null || mobile === '')) {
        this.indexContacts[index] = 1
        callback(new Error('手机,电话至少填一项'))
      } else {
        if (phone !== null && phone !== '' && !phonereg.test(phone)) {
          this.indexContacts[index] = 1
          callback(new Error('请输入正确移动电话'))
        }

        callback()
      }
    }
    const checkFormOfficephone = (rules, value, callback) => {
      const index = rules.field.replace('contacts.', '').replace('.phone', '').replace('.mobile', '')
      const {phone, mobile} = this.contactInfoForm.contacts[index]
      if ((  phone === null || phone === '') && (mobile === null || mobile === '')) {
        this.indexContacts[index] = 1
        callback(new Error('手机,电话至少填一项'))
      } else {
        this.indexContacts[index] = 1
        callback()
      }
    }
    return {
      isNetHospFlag: false,
      medicalTypeData: [],
      dialogVisible: false,
      modalValue: false,
      index: '',
      indexContacts: [],
      place_typeOptions: [],
      contactInfoFormRules: {
        placeType: [{required: true, message: '联系人类型不能为空', trigger: 'change'}],
        phone: [{validator: checkFormMobilephone, trigger: 'blur'}],
        mobile: [{validator: checkFormOfficephone, trigger: 'blur'}],
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        email: [{required: true, message: '请输入邮箱', trigger: 'blur'}],
        workingStart: [{required: true, message: '请输入工作开始时间', trigger: 'blur'}],
        workingEnd: [{required: true, message: '请输入工作结束时间', trigger: 'blur'}],
        role: [{required: true, message: '请输入登录名', trigger: 'blur'}],


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
    nameChange(index) {
      let mobile='contacts.'+index +'.mobile'
      let phone='contacts.'+index +'.phone'
      this.$refs.contactInfoForm.validateField([phone, mobile])
    },
    init(){
      if (this.dictList != null && this.dictList.length != 0) {
        this.place_typeOptions = this.dictList.find(item => {
          return item.dictType == 'place_type'
        }).dictDate

      }
    },
//联系信息保存
    async saveHandle() {
      let query={
        providerCode: this.supplierCode
      }

      if(this.status=='01') {
        await new Promise((resolve, reject) => {
          getNewtworktypeList(query).then(res => {
            this.medicalTypeData = res.rows
            if (this.medicalTypeData.filter(item => {
              return item.networktypeName == '网络医院'
            })[0].oldChoose == '02') {
              this.isNetHospFlag = true
            } else {
              this.isNetHospFlag = false
            }
            resolve(this.isNetHospFlag)
          })
        }).then(res => {
          let obj = this.$parent.$parent.$parent.getServiceInfoData()
          this.$refs.contactInfoForm.validate((valid) => {
            if (this.contactInfoForm.contacts != null && this.contactInfoForm.contacts.length > 0) {
              //找到是否有平安服务人员联系人
              let flag = true
              let placeTypeFlag
              if (this.isNetHospFlag) {
                let obj = this.contactInfoForm.contacts.find(item => {
                  return item.placeType == '03'
                })
                if (obj != null && obj != '') {

                  flag = true
                } else {
                  flag = false
                }

              } else {
                flag = true
              }
              if (flag) {
                if (obj.leadFlag == '03') {
                  //联系信息是否有驻点人员信息
                  let placeType = this.contactInfoForm.contacts.find(item => {
                    return item.placeType == '01'
                  })
                  if (placeType != null && placeType != '') {
                    placeTypeFlag = true
                  } else {
                    placeTypeFlag = false
                  }
                } else {
                  placeTypeFlag = true
                }
                if (placeTypeFlag) {
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
                    this.$message.warning('联系信息必录项未必录')
                  }
                } else {
                  this.$message.warning('导检/陪检/驻点为驻点时，至少有一位驻点人员联系人信息')
                }


              } else {
                this.$message.warning('网络医院为是，至少有一位平安服务人员联系人信息')
              }
            } else {
              this.$message({
                message: '至少添加一条联系人信息',
                center: true,
                type: "warning"
              });
            }

          })
        })
      }
      else {
        let obj = this.$parent.$parent.$parent.getOtherReservelInfoData()

        this.$refs.contactInfoForm.validate((valid) => {
          if (this.contactInfoForm.contacts != null && this.contactInfoForm.contacts.length > 0) {
            let placeTypeFlag
              if (obj.leadFlag == '03') {
                //联系信息是否有驻点人员信息
                let placeType = this.contactInfoForm.contacts.find(item => {
                  return item.placeType == '01'
                })
                if (placeType != null && placeType != '') {
                  placeTypeFlag = true
                } else {
                  placeTypeFlag = false
                }
              } else {
                placeTypeFlag = true
              }
              if (placeTypeFlag) {
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
                  this.$message.warning('联系信息必录项未必录')
                }
              } else {
                this.$message.warning('导检/陪检/驻点为驻点时，至少有一位驻点人员联系人信息')
              }



          } else {
            this.$message({
              message: '至少添加一条联系人信息',
              center: true,
              type: "warning"
            });
          }

        })
      }

    },

    delHandle(index, row) {
      //this.dialogVisible = true
      this.index = index
      this.$confirm('是否删除当前行联系人信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.contactInfoForm.contacts.splice(this.index,1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    /*    delConfirm() {
          this.dialogVisible = false
          this.contactInfoForm.contacts.splice(this.index,1)
        },*/
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
    async validateForm () {

      let flag
      let hospFlag
      let placeTypeFlag
      let query={
        providerCode: this.supplierCode
      }
      let obj=this.$parent.$parent.$parent.getServiceInfoData()
      await new Promise((resolve, reject) => {
        getNewtworktypeList(query).then(res => {
          this.medicalTypeData = res.rows
          if (this.medicalTypeData.filter(item => {
            return item.networktypeName == '网络医院'
          })[0].oldChoose == '02') {
            this.isNetHospFlag = true
          }
          else {
            this.isNetHospFlag = false
          }

          resolve(this.isNetHospFlag)
        })
      }).then(res=>{
        if(this.contactInfoForm.contacts!=null && this.contactInfoForm.contacts.length>0) {
          //网络医院为是，联系信息中联系人是否有一位平安服务人员
          if(this.isNetHospFlag){
            let obj=this.contactInfoForm.contacts.find(item=>{
              return item.placeType=='03'
            })
            if(obj!=null && obj!=''){
              hospFlag=true
            }
            else {
              hospFlag=false
            }
          }
          else {
            hospFlag=true
          }
          if(hospFlag) {
            if(obj.leadFlag=='03') {
              //联系信息是否有驻点人员信息
              let placeType = this.contactInfoForm.contacts.find(item => {
                return item.placeType == '01'
              })
              if (placeType != null && placeType != '') {
                placeTypeFlag = true
              } else {
                placeTypeFlag = false
              }
            }
            else {
              placeTypeFlag=true
            }
            if(placeTypeFlag){
              this.$refs['contactInfoForm'].validate(valid => {
                if (valid) {
                  flag = '01'
                } else {
                  flag = '03'
                }
              })
            }
            else {
              flag='05'
            }
          }
          else {
            flag = '04'
          }
        }
        else {
          flag='02'
        }
      })
      return flag
    },

    //其他机构校验数据
    otherValidataForm(){
      let flag
      let placeTypeFlag
      if(this.contactInfoForm.contacts!=null && this.contactInfoForm.contacts.length>0) {
        let obj = this.$parent.$parent.$parent.getOtherReservelInfoData()

          if(obj.leadFlag=='03') {
            //联系信息是否有驻点人员信息
            let placeType = this.contactInfoForm.contacts.find(item => {
              return item.placeType == '01'
            })
            if (placeType != null && placeType != '') {
              placeTypeFlag = true
            } else {
              placeTypeFlag = false
            }
          }
          else {
            placeTypeFlag=true
          }
          if(placeTypeFlag){
            this.$refs['contactInfoForm'].validate(valid => {
              if (valid) {
                flag = '01'
              } else {
                flag = '03'
              }
            })
          }
          else {
            flag='05'
          }


      }
      else {
        flag='02'
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
.el-form-item ::v-deep label {
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
