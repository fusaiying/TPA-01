
<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>案件报备规则</span>
      </div>
      <el-row>
        <el-table
          v-loading="loading"
          ref="table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="specialruleslist"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;"
          @row-click="updateData">
          <el-table-column :formatter="getRuletypeName" label="规则类型" prop="ruletype" align="center"/>
          <el-table-column :formatter="getRisktypeName" label="险种类型" prop="risktype" align="center"/>
          <el-table-column :formatter="getRiskName1" label="险种名称" prop="riskname" align="center"/>
          <el-table-column label="规则值" prop="elementvalue" align="center"/>
          <el-table-column :formatter="getCumulativemodeName" label="累计方式" prop="cumulativemode" align="center"/>
          <<!--el-table-column :formatter="getPendingflagName" label="是否挂起" prop="pendingflag" align="center"/>-->
          <el-table-column label="报备机构" prop="targetbranch,targetuser" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.branchName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="设置时间" prop="createdate" align="center"/>
        </el-table>
      </el-row>
      <el-form ref="baseForm" :rules="baseFormRules" :model="baseForm" size="mini" label-width="100px" style="margin-top: 40px;">
        <el-row>
          <el-col :span="8">
            <el-form-item :show-message="false" label="规则类型：" prop="ruletype">
              <el-select v-model="baseForm.ruletype" :rules="baseFormRules.ruletype" class ="item-width" placeholder="请选择" clearable>
                <el-option v-for="option in specialrules" :key="option.id" :label="option.label" :value="option.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :show-message="false" label="险种类型：" prop="risktype">
              <el-select v-model="baseForm.risktype" :rules="baseFormRules.risktype" class ="item-width" placeholder="请选择" clearable @change="riskTypeChange">
                <el-option v-for="option in risktypes" :key="option.id" :label="option.label" :value="option.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :show-message="false" label="险种名称：" prop="riskname">
              <el-select
                class="item-width"
                v-model="baseForm.riskname"
                filterable
                remote
                placeholder="请输入关键词"
                :remote-method="querySearch"
                :loading="loading">
                <el-option
                  v-for="item in riskList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="规则值：" prop="elementvalue">
              <el-input v-model="baseForm.elementvalue" :rules="baseFormRules.elementvalue" class ="item-width" placeholder="请输入" size="mini" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :show-message="false" label="累计方式：" prop="cumulativemode">
              <el-select v-model="baseForm.cumulativemode" :rules="baseFormRules.cumulativemode" class ="item-width" placeholder="请选择" clearable>
                <el-option v-for="option in modes" :key="option.id" :label="option.label" :value="option.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :show-message="false" label="上报机构：" prop="targetbranch">
              <el-select v-model="baseForm.targetbranch" :rules="baseFormRules.targetbranch" class ="item-width" placeholder="请选择" clearable>
                <el-option v-for="(option,index) in agencies" :key="index" :label="option.managename" :value="option.managecode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :show-message="false" label="处理用户：" prop="targetuser">
              <el-select v-model="baseForm.targetuser" :rules="baseFormRules.targetuser" class ="item-width" placeholder="请选择" clearable>
                <el-option v-for="(option,index) in users" :key="index" :label="option.label" :value="option.label"/>
              </el-select>
            </el-form-item>
          </el-col>
         <!-- <el-col :span="8">
            <el-form-item label="是否挂起：" prop="pendingflag">
              <el-select v-model="baseForm.pendingflag" :rules="baseFormRules.pendingflag" class ="item-width" placeholder="请选择" clearable>
                <el-option v-for="option in status" :key="option.id" :label="option.label" :value="option.value"/>
              </el-select>
            </el-form-item>
          </el-col>-->
          <el-col :span="24">
            <el-form-item :show-message="false" label="备注：" prop="remarks">
              <el-input v-model="baseForm.remarks" type="textarea" maxlength="100" show-word-limit placeholder="请输入" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div class="bottom clearfix" style="text-align: right;">
        <el-button :loading="saveLoading" type="primary" size="mini" class="button" @click="saveHandle">添加规则</el-button>
        <el-button :loading="delLoading" type="primary" size="mini" class="button" @click="delHandle">删除</el-button>
        <el-button type="primary" size="mini" class="button" @click="resetForm">重置</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
import { get } from '@/api/dictDetail'
import { getAllSpecialRules, addSpecialRules, getInsuranceName, getDealUserName,getAllBranch, fuzzyQuery, deleteSpecialRule } from '@/api/claim/specialCase'
export default {
  dicts: ['special_rules', 'ins_insurancetype', 'special_agency', 'deal_user', 'special_isPending', 'special_cumulativemode'],
  data() {
    const checkNameValue = (rules, value, callback) => {
      if (value === null || value === '') {
        callback(new Error(' '))
      } else if (value !== null && value !== '') {
        const num = /^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/
        if (!num.test(value)) {
          callback(new Error('规则值录入不合法，请检查。'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    return {
      specialruleslist: [],
      baseForm: {
        specialruleno: '',
        ruletype: '',
        risktype: '',
        riskname: '',
        elementvalue: '',
        cumulativemode: '',
        targetbranch: '',
        targetuser: '',
        pendingflag: '',
        remarks: ''
      },
      loading: false,
      specialrules: [],
      risktypes: [],
      risknames: [],
      agencies: [],
      users: [],
      status: [],
      modes: [],
      checkflag: false,
      baseFormRules: {
        ruletype: [{ required: true, message: '页面信息录入不完整，请检查。', trigger: 'change' }],
        risktype: [{ required: true, message: '页面信息录入不完整，请检查。', trigger: 'change' }],
        targetbranch: [{ required: true, message: '页面信息录入不完整，请检查。', trigger: 'change' }],
        targetuser: [{ required: true, message: '页面信息录入不完整，请检查。', trigger: 'change' }],
        pendingflag: [{ required: true, message: '页面信息录入不完整，请检查。', trigger: 'change' }],
        elementvalue: [{ validator: checkNameValue, required: true, trigger: 'change' }],
        cumulativemode: [{ required: true, message: '页面信息录入不完整，请检查。', trigger: 'change' }]
      },
      riskNameList: [],
      riskList: [],
      delFlag: false,
      saveLoading: false,
      delLoading: false
    }
  },
  created() {
    this.getSpecialRules()// 特案报备规则
    this.getRiskTypes()// 险种类型
    this.getRiskName()// 险种名称
    this.getAllBranch()// 上报机构
    this.getUsers()// 处理用户
    this.isPending()// 是否挂起
    this.getCumulativemode()// 累计方式
    this.getAllSpecialRules()
  },
  methods: {
    updateData(row) {
      this.delFlag = true
      this.baseForm = row
    },
    getSpecialRules() {
      const dictName = 'special_rules'
      get(dictName).then((response) => {
        this.specialrules = response.content
      })
    },
    getRiskTypes() {
      const dictName = 'ins_insurancetype'
      get(dictName).then((response) => {
        this.risktypes = response.content
      })
    },
    querySearch(queryString) {
        let list = []
        let params = {
            insurancetype: this.baseForm.risktype,
            riskinfo: queryString
        }
        fuzzyQuery(params).then(res => {
            if (res.status === '1') {
                res.data.forEach(item => {
                    list.push({ value: item.value, label: item.value + ' ' + item.label })
                })
                this.riskList = list
            }
        })
    },
    getRiskName1(row) {
      let label = null
      this.riskNameList.forEach(item => {
        if (row.riskname === item.value) {
          label = `${item.value}  ${item.label}`
        }
      })
      return label
    },
    getRiskName() {
      const params = {
        insurancetype: this.baseForm.risktype
      }
      getInsuranceName(params).then(response => {
        // 表格过滤险种名称的数据
        if (!this.baseForm.risktype) {
          this.riskNameList = response.data
        }
        // form表单险种名称的数据
        if (response.status === '1') {
          this.risknames = response.data
        }
      }).catch(error => {
      })
    },
    riskTypeChange() {
      this.baseForm.riskname = ''
      this.getRiskName()
    },
    // getAgencies() {
    //   const dictName = 'special_agency'
    //   get(dictName).then((response) => {
    //     this.agencies = response.content
    //   })
    // },
    getUsers() {
      getDealUserName().then(res => {
        if (res.status == '1') {
          this.users = res.data
        } else {
          this.$message.error("查询处理用户失败！")
        }
      })
    },
    isPending() {
      const dictName = 'special_isPending'
      get(dictName).then((response) => {
        this.status = response.content
      })
    },
    getCumulativemode() {
      const dictName = 'special_cumulativemode'
      get(dictName).then((response) => {
        this.modes = response.content
      })
    },
    getRuletypeName(row, col) {
      return this.dict.label.special_rules[row.ruletype]
    },
    getRisktypeName(row, col) {
      return this.dict.label.ins_insurancetype[row.risktype]
    },
    getCumulativemodeName(row, col) {
      return this.dict.label.special_cumulativemode[row.cumulativemode]
    },
    getPendingflagName(row, col) {
      return this.dict.label.special_isPending[row.pendingflag]
    },
    // getTarget(row, col) {
    //   return this.getTargetbranchName(row, col) + ' to ' + this.getTargetUserName(row, col)
    // },
    // getTargetbranchName(row, col) {
    //   return this.dict.label.special_agency[row.targetbranch]
    // },
    // getTargetUserName(row, col) {
    //   return this.dict.label.deal_user[row.targetuser]
    // },
    changeUSers(user) {
      let userName = ''
      this.users.forEach(val => {
        if (user == val.value) {
          userName = val.label
        }
      })
      return userName
    },
    changeBranch(comp) {
      let branch = ''
      this.agencies.forEach(item => {
        if (comp == item.managecode) {
          branch = item.managename
        }
      })
      return branch
    },
    getAllSpecialRules() {
      this.loading = true
      getAllSpecialRules().then(response => {
        this.specialruleslist = response.data
        let _this = this
        this.specialruleslist.map((item) =>{
          item.branchName = `${this.changeBranch(item.targetbranch)} to ${item.targetuser}`
        })
      }).catch(err => {
        // alert('查询异常' + err)
        this.$message({ message: '查询异常' + err })
      }).finally(() => {
        this.loading = false
      })
    },
    resetForm() {
      this.$refs.baseForm.resetFields()
      this.getAllSpecialRules()
      this.baseForm.specialruleno = null
    },
    saveHandle() {
      let rulevalidate = true
      this.$refs.baseForm.validate(valid => {
        rulevalidate = valid
      })
      if (!rulevalidate) {
        this.$message.warning('页面信息录入不完整，请检查！')
        return false
      }
      const rulesmsg = this.baseForm
      delete rulesmsg.branchName
      this.saveLoading = true
      addSpecialRules(rulesmsg).then(response => {
        if (response.status === '1') {
          this.$message.success('添加成功！')
          this.resetForm()
        } else {
          if (response.data.errCode === 'SL004') {
            this.$message.error('规则重复，请检查！')
          } else {
            this.$message.error('添加失败！')
          }
        }
        this.saveLoading = false
      }).catch(error => {
        // this.$message.error('保存异常' + error)
      })
    },
    getAllBranch(){
      getAllBranch().then(res =>{
        if (res.status == '1') {
          this.agencies = res.data
        } else {
          this.$message.error('查询上报机构错误！')
        }
      })
    },
    delHandle() {
        if (!this.delFlag) {
            this.$message.warning('请选择一条数据进行删除！')
            return
        }
        this.$confirm('请确认是否进行删除？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            this.delLoading = true
            deleteSpecialRule(this.baseForm.specialruleno).then(res => {
                if (res.status === '1') {
                    this.$message.success('删除成功！')
                    this.resetForm()
                    this.getAllSpecialRules()
                } else if (res.data.errCode === 'SL007') {
                    this.$message.error(res.data.errMsg)
                } else {
                    this.$message.error('删除失败！')
                }
                this.delLoading = false
            })
        }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除！'
            })
        })
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
