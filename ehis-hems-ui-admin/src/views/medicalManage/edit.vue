<template>
  <div v-loading="loading" class="app-container" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>基本信息</span>
        <span style="float: right;" >
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-form ref="baseForm" :rules="baseFormRules" :model="formSearch" style="padding-bottom: 30px;" label-width="120px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="医疗机构编码：" prop="hospitalcode">
              <el-input v-model="formSearch.hospitalcode" class="item-width" clearable readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="中文名称：" prop="hospitalname">
              <el-input ref="hospitalname" v-model="formSearch.hospitalname" class="item-width" clearable size="mini" placeholder="请输入" @change="nameChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英文名称：" prop="englishname">
              <el-input ref="englishname" v-model="formSearch.englishname" class="item-width" clearable size="mini" placeholder="请输入" @change="nameChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构国家编码：" prop="country">
              <el-input v-model="formSearch.country" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构别名：" prop="othername">
              <el-input v-model="formSearch.othername" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="建院年份：" prop="builtyear">
              <el-input v-model="formSearch.builtyear" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构类型：" prop="hospitaltype">
              <el-select v-model="formSearch.hospitaltype" class="item-width" placeholder="请选择" clearable>
                <el-option label="区域一" value="01"/>
                <el-option label="区域二" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构等级：" prop="hospitallevel">
              <el-select v-model="formSearch.hospitallevel" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医当地社保：" prop="socialinsurance">
              <el-select v-model="formSearch.socialinsurance" class="item-width" placeholder="请选择" clearable>
                <el-option label="一" value="01"/>
                <el-option label="二" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="经营方式：" prop="managementmode">
              <el-select v-model="formSearch.managementmode" class="item-width" placeholder="请选择" clearable>
                <el-option label="一" value="01"/>
                <el-option label="二" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="床位数：" prop="bednumber">
              <el-input v-model="formSearch.bednumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属地区：" prop="city">
              <el-cascader
                v-model="formSearch.city"
                :props="{ checkStrictly: true }"
                :options="regions"
                class="item-width"
                @change="handleChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label=" 门诊量(日)：" prop="outpatientvolume">
              <el-input v-model="formSearch.outpatientvolume" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="state">
              <el-select v-model="formSearch.state" class="item-width" placeholder="请选择" clearable>
                <el-option label="黑名单" value="00"/>
                <el-option label="有效" value="01"/>
                <el-option label="无效" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item style="margin-right: 20px;" label="重点科室：" prop="zdks">
            <el-input v-model="formSearch.zdks" clearable size="mini" placeholder="请输入" maxlength="200"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item style="margin-right: 20px;" label="详细地址：" prop="address">
            <el-input v-model="formSearch.address" clearable size="mini" placeholder="请输入" maxlength="200"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item style="margin-right: 20px;" label="备注：" prop="remark">
            <el-input v-model="formSearch.remark" clearable size="mini" placeholder="请输入" maxlength="2000"/>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>

    <el-form ref="plusForm" :rules="formRules" :model="form" size="small">
      <!--      <el-card class="box-card" style="margin-top: 10px;">-->
      <!--        <div slot="header" class="clearfix">-->
      <!--          <span>医院科室</span>-->
      <!--        </div>-->
      <!--        <el-table-->
      <!--          ref="departmenttable"-->
      <!--          :header-cell-style="{color:'black',background:'#f8f8ff'}"-->
      <!--          :data="form.departments"-->
      <!--          size="small"-->
      <!--          highlight-current-row-->
      <!--          tooltip-effect="dark"-->
      <!--          style="width: 100%;">-->
      <!--          <el-table-column label="编码" prop="departmentcode" align="center">-->
      <!--            <template slot-scope="scope">-->
      <!--              <el-form-item v-if="scope.row.departmentno === ''" :prop="'departments.' + scope.$index + '.departmentcode'" :rules="formRules.departmentcode">-->
      <!--                <el-input v-model="scope.row.departmentcode" placeholder="请输入" size="mini"/>-->
      <!--              </el-form-item>-->
      <!--              <span v-else>{{ scope.row.departmentcode }}</span>-->
      <!--            </template>-->
      <!--          </el-table-column>-->
      <!--          <el-table-column label="中文名称" prop="departmentname" align="center">-->
      <!--            <template slot-scope="scope">-->
      <!--              <el-form-item v-if="scope.row.departmentno === ''" :prop="'departments.' + scope.$index + '.departmentname'" :rules="formRules.departmentname">-->
      <!--                <el-input v-model="scope.row.departmentname" placeholder="请输入" size="mini"/>-->
      <!--              </el-form-item>-->
      <!--              <span v-else>{{ scope.row.departmentname }}</span>-->
      <!--            </template>-->
      <!--          </el-table-column>-->
      <!--          <el-table-column label="英文名称" prop="englishname" align="center">-->
      <!--            <template slot-scope="scope">-->
      <!--              <el-form-item v-if="scope.row.departmentno === ''" :prop="'departments.' + scope.$index + '.englishname'">-->
      <!--                <el-input v-model="scope.row.englishname" placeholder="请输入" size="mini"/>-->
      <!--              </el-form-item>-->
      <!--              <span v-else>{{ scope.row.englishname }}</span>-->
      <!--            </template>-->
      <!--          </el-table-column>-->
      <!--          <el-table-column label="科室说明" prop="description" align="center">-->
      <!--            <template slot-scope="scope">-->
      <!--              <el-form-item v-if="scope.row.departmentno === ''" :prop="'departments.' + scope.$index + '.remark'">-->
      <!--                <el-input v-model="scope.row.remark" placeholder="请输入" size="mini"/>-->
      <!--              </el-form-item>-->
      <!--              <span v-else>{{ scope.row.remark }}</span>-->
      <!--            </template>-->
      <!--          </el-table-column>-->
      <!--          <el-table-column label="操作" align="center">-->
      <!--            <template slot-scope="scope">-->
      <!--              <span v-if="scope.row.departmentno === ''" style="cursor: pointer;">-->
      <!--                <el-button style="margin-bottom: 14px;" type="text" size="mini" @click="cancelDepartmentHandle(scope.$index, scope.row)">取消</el-button>-->
      <!--              </span>-->
      <!--              <span v-else style="cursor: pointer;">-->
      <!--                <el-button type="text" size="mini" @click="delDepartmentHandle(scope.$index, scope.row)">删除</el-button>-->
      <!--              </span>-->
      <!--            </template>-->
      <!--          </el-table-column>-->
      <!--        </el-table>-->
      <!--        <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addDepartmentHandle()"> + 添加</el-button>-->
      <!--      </el-card>-->
      <el-card class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span>联系信息</span>
        </div>
        <el-table ref="contacttable" :data="form.contacts" :header-cell-style="{color:'black',background:'#f8f8ff'}" size="small" highlight-current-row style="width: 100%;">
          <el-table-column prop="contacttype" align="center" header-align="center" label="联系人类型">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactno === ''" :prop="'contacts.' + scope.$index + '.contacttype'" :rules="formRules.contacttype">
                <el-select v-model="scope.row.contacttype" placeholder="请选择" size="mini">
                  <el-option v-for="option in contacttype" :key="option.id" :label="option.label" :value="option.value"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ getContactTypeName(scope.row.contacttype) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="name" align="center" header-align="center" label="姓名">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactno === ''" :prop="'contacts.' + scope.$index + '.name'" :rules="formRules.name">
                <el-input v-model="scope.row.name" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="mobilephone" align="center" header-align="center" label="移动电话">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactno === ''" :prop="'contacts.' + scope.$index + '.mobilephone'" :rules="formRules.mobilephone">
                <el-input v-model="scope.row.mobilephone" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.mobilephone }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="officephone" align="center" header-align="center" label="办公电话">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactno === ''" :prop="'contacts.' + scope.$index + '.officephone'">
                <el-input v-model="scope.row.telephone" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.telephone }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="email" align="center" header-align="center" label="电子邮箱">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactno === ''" :prop="'contacts.' + scope.$index + '.email'">
                <el-input v-model="scope.row.email" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.email }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.contactno === ''" style="cursor: pointer;">
                <el-button style="margin-bottom: 14px;" type="text" size="mini" @click="cancelContactHandle(scope.$index,scope.row)">取消</el-button>
              </span>
              <span v-else style="cursor: pointer;">
                <el-button type="text" size="mini" @click="delContactHandle(scope.$index,scope.row)">删除</el-button>
              </span>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addContactHandle"> + 添加</el-button>
      </el-card>
    </el-form>
    <el-dialog
      :visible.sync="dialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title="提示"
      width="30%">
      <span>{{ '是否确认删除此医疗机构？' }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="delConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { addHospital, updataHospital, getAddress, getOne } from '@/api/baseInfo/medicalManage.js'
// import { getAddress } from '@/api/investigate/supplierManagement'

export default {
  name: 'Edit',
  dicts: ['hospitallevel'],
  data() {
    const checkMobile = (rules, value, callback) => {
      const mobilephonereg = /^1([38]\d|5[0-35-9]|7[3678])\d{8}$/
      if (!value) {
        callback(new Error('移动电话不能为空！'))
      } else if (!mobilephonereg.test(value)) {
        callback(new Error('请输入正确的手机号！'))
      } else {
        callback()
      }
    }
    const checkEnglishName = (rules, value, callback) => {
      if (!value) {
        if (!this.formSearch.hospitalname) {
          callback(new Error('中文名称和英文名称必录其一'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkChineseName = (rules, value, callback) => {
      if (!value) {
        if (!this.formSearch.englishname) {
          callback(new Error('中文名称和英文名称必录其一'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    return {
      city: [],
      formSearch: {},
      regions: [],
      tableData: [{
        name: 'xxx'
      }],
      form: {
        contacts: [],
        departments: []
      },
      formRules: {
        contacttype: [{ required: true, message: '请选择联系人类型', trigger: 'change' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        mobilephone: [{ required: true, validator: checkMobile, trigger: 'blur' }],
        departmentcode: [{ required: true, message: '请输入医疗机构编码', trigger: 'blur' }],
        departmentname: [{ required: true, message: '请输入医疗机构中文名', trigger: 'blur' }]
      },
      baseFormRules: {
        hospitalcode: [{ required: this.$route.query.isEmpty, message: '不能为空！', trigger: 'blur' }],
        hospitalname: [{ validator: checkChineseName, trigger: 'blur' }],
        hospitaltype: [{ required: true, message: '不能为空！', trigger: 'change' }],
        hospitallevel: [{ required: true, message: '不能为空！', trigger: 'change' }],
        socialinsurance: [{ required: true, message: '不能为空！', trigger: 'change' }],
        city: [{ required: true, message: '不能为空！', trigger: 'change' }],
        outpatient: [{ required: true, message: '不能为空！', trigger: 'blur' }],
        zdks: [{ required: true, message: '不能为空！', trigger: 'blur' }],
        state: [{ required: true, message: '不能为空！', trigger: 'change' }],
        englishname: [{ validator: checkEnglishName, trigger: 'blur' }]
      },
      contacttype: [
        { label: 'xxx', value: '1' },
        { label: 'ccc', value: '2' }
      ],
      loading: false,
      dialogVisible: false,
      modalValue: false,
      index: ''
    }
  },
  mounted() {
    this.getAddressData()
    if (this.$route.query.hospitalcode) {
      this.loading = true
      this.getOneHospital(this.$route.query.hospitalcode)
    }
  },
  methods: {
    nameChange() {
      this.$refs.baseForm.validateField(['englishname', 'hospitalname'])
    },
    // 所属地
    getAddressData() {
      getAddress().then(response => {
        this.regions = response.data
      }).catch(error => {
      })
    },
    // 当打开方式为编辑
    getOneHospital(data) {
      getOne(data).then(res => {
        const cityArr = []
        cityArr.push(res.data.baseinfo.province)
        cityArr.push(res.data.baseinfo.city)
        cityArr.push(res.data.baseinfo.district)
        this.formSearch = res.data.baseinfo
        this.form.contacts = res.data.contactinfo
        this.form.departments = res.data.departmentinfo
        this.formSearch.city = cityArr
        this.loading = false
      })
    },
    handleChange(value) {
    },
    //  保存
    saveHandle() {
      this.$refs.baseForm.validate((valid1) => {
        this.$refs.plusForm.validate((valid2) => {
          if (valid1 && valid2) {
            const subFormSearch = JSON.parse(JSON.stringify(this.formSearch))
            if (this.formSearch.city) {
              subFormSearch.province = this.formSearch.city[0]
              subFormSearch.district = this.formSearch.city[2]
              subFormSearch.city = this.formSearch.city[1]
            }
            const param = {
              baseinfo: subFormSearch,
              contactinfo: this.form.contacts,
              departmentinfo: this.form.departments
            }
            if (this.$route.query.hospitalcode) {
              updataHospital(param).then(res => {
                if (res.status === '1') {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                } else if (res.status === '0') {
                  this.$message({
                    message: '保存失败,' + res.data.errMsg,
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                }
              })
            } else {
              addHospital(param).then(res => {
                if (res.status === '1') {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                } else if (res.status === '0') {
                  this.$message({
                    message: '保存失败,' + res.data.errMsg,
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
      })
    },
    //  重置
    resetForm() {
      this.$refs.baseForm.resetFields()
      this.form = {
        contacts: [],
        departments: []
      }
    },
    addDepartmentHandle() {
      const field = {
        departmentno: '',
        departmentcode: '',
        departmentname: '',
        englishname: '',
        remark: ''
      }
      this.form.departments.push(field)
      setTimeout(() => { this.$refs.departmenttable.setCurrentRow(field) }, 10)
    },
    delDepartmentHandle(index, row) {
      this.form.departments.splice(index, 1)
    },
    cancelDepartmentHandle(index, row) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.form.departments.splice(index, 1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    addContactHandle() {
      const field = {
        contactno: '',
        contacttype: '',
        name: '',
        mobilephone: '',
        telephone: '',
        email: ''
      }
      this.form.contacts.push(field)
      setTimeout(() => { this.$refs.contacttable.setCurrentRow(field) }, 10)
    },
    delContactHandle(index, row) {
      this.dialogVisible = true
      this.index = index
    },
    delConfirm() {
      this.form.contacts.splice(this.index, 1)
      this.dialogVisible = false
    },
    cancelContactHandle(index, row) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.form.contacts.splice(index, 1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    getContactTypeName(value) {
      for (const i in this.contacttype) {
        if (this.contacttype[i].value === value) {
          return this.contacttype[i].label
        }
      }
    },
    goBack() {
      this.$router.go(-1)
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
</style>
