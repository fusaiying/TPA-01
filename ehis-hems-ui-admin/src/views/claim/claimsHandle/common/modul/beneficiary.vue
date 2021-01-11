<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="受益人信息"
    width="96%"
    @open="bnfInfo">
    <el-form style="padding-bottom: 30px;" ref="beneficiaryForm"
             :disabled="node !== 'accept' && node !== 'review' && node !== 'firstTrial' && node !=='input'"
             :model="beneficiaryForm" size="mini">
      <el-row>
        <el-table
          v-loading="loading"
          ref="table"
          size="small"
          highlight-current-row
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          :data="beneficiaryForm.tableData"
          style="width: 100%;">
          <el-table-column width="150" label="保单号" prop="contNo" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.contNo'"
                            :rules="{ required: true, message: '不能为空'}">
                <el-select style="width: 150px" v-model="scope.row.contNo"
                           @change="changePolicy(scope.row.contNo,scope.$index)" placeholder="请选择">
                  <el-option v-for="option in policyNos" :key="option" :label="option" :value="option"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ scope.row.contNo }}</span>
            </template>
          </el-table-column>
          <el-table-column width="150" label="险种名称" prop="riskCode" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.riskCode'"
                            :rules="{ required: true, message: '不能为空'}">
                <el-select style="width: 150px" v-model="scope.row.riskCode" placeholder="请选择">
                  <el-option v-for="option in scope.row.riskList" :key="option.id" :label="option.label"
                             :value="option.value"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ scope.row.riskName }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="与被保人关系" prop="relation" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.relation'"
                            :rules="{ required: true, message: '不能为空'}">
                <el-select style="width: 110px" v-model="scope.row.relation" placeholder="请选择"
                           @change="changeRelation(scope.row,scope.$index)">
                  <el-option v-for="option in payee_insuredrelationshipOptions" :key="option.id"
                             :label="option.dictLabel" :value="option.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ /*dict.label.payee_insuredrelationship[scope.row.relation]*/ this.selectDictLabel(this.payee_insuredrelationshipOptions, scope.row.relation)}}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="受益人姓名" prop="bnfName" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.bnfName'"
                            :rules="{ required: true, message: '不能为空',trigger: 'blur'}">
                <el-input style="width: 110px" v-model="scope.row.bnfName" maxlength="20"
                          :disabled="scope.row.disabledFlag" clearable placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bnfName }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="证件类型" prop="idCardType" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.idCardType'"
                            :rules="{ required: true, message: '不能为空',trigger: 'blur'}">
                <el-select style="width: 110px" v-model="scope.row.idCardType" :disabled="scope.row.disabledFlag"
                           placeholder="请选择">
                  <el-option v-for="option in card_typeOptions" :key="option.id" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ /*dict.label.card_type[scope.row.idCardType]*/ this.selectDictLabel(this.card_typeOptions, scope.row.idCardType) }}</span>
            </template>
          </el-table-column>
          <el-table-column width="150" label="证件号码" prop="idNo" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.idNo'"
                            :rules="{ required: true, message: '不能为空',trigger: 'blur'}">
                <el-input style="width: 150px" v-model="scope.row.idNo" maxlength="30"
                          :disabled="scope.row.disabledFlag" clearable placeholder="请输入"
                          @change="changeIdNo(scope.row,scope.$index)" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.idNo }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="性别" prop="sex" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.sex'"
                            :rules="{ required: true, message: '不能为空',trigger: 'blur'}">
                <el-select style="width: 110px" v-model="scope.row.sex" :disabled="scope.row.disabledFlag"
                           placeholder="请选择">
                  <el-option v-for="option in sexOptions" :key="option.id" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
              <span
                v-else>{{ /*dict.label.sex[scope.row.sex]*/ this.selectDictLabel(this.sexOptions, scope.row.sex) }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="出生日期" prop="birthday" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.birthday'"
                            :rules="{ required: true, message: '不能为空',trigger: 'blur'}">
                <el-date-picker style="width: 110px" v-model="scope.row.birthday" :disabled="scope.row.disabledFlag"
                                type="date" placeholder="选择日期"></el-date-picker>
              </el-form-item>
              <span v-else>{{ scope.row.birthday | dateFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="受益份额(%)" prop="bnfLot" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.bnfLot'"
                            :rules="{ required: true, message: '不能为空'}">
                <el-input-number style="width: 110px" v-model="scope.row.bnfLot" :min="1" :max="100" :precision="0"
                                 placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bnfLot }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="受益顺序" prop="bnfGrade" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.bnfGrade'"
                            :rules="{ required: true, message: '不能为空'}">
                <el-select style="width: 110px" v-model="scope.row.bnfGrade" placeholder="请选择">
                  <el-option v-for="option in bnf_gradeOptions" :key="option.id" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ /*dict.label.bnf_grade[scope.row.bnfGrade]*/ this.selectDictLabel(this.bnf_gradeOptions, scope.row.bnfGrade)}}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="国籍" prop="nationality" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.nationality'">
                <el-select style="width: 110px" v-model="scope.row.nationality" placeholder="请选择">
                  <el-option v-for="option in nativeplaceOptions" :key="option.id" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{/* dict.label.nativeplace[scope.row.nationality]*/ this.selectDictLabel(this.nativeplaceOptions, scope.row.nationality) }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="职业" prop="occupation" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.occupation'">
                <el-select style="width: 110px" v-model="scope.row.occupation" filterable placeholder="请选择">
                  <el-option v-for="option in bnf_occupationOptions" :key="option.id" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ /*dict.label.bnf_occupation[scope.row.occupation]*/ this.selectDictLabel(this.bnf_occupationOptions, scope.row.occupation)  }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="地址" prop="address" align="center">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.editFlag" :prop="'tableData.' + scope.$index + '.address'">
                <el-input style="width: 110px" v-model="scope.row.address" maxlength="100" clearable placeholder="请输入"
                          size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.address }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="操作" align="center">
            <template slot-scope="scope">
              <el-form-item>
                <el-button type="text" @click="editHandle(scope.$index,scope.row)">编辑</el-button>
                <el-button type="text" @click="delHandle(scope.$index,scope.row)">删除</el-button>
              </el-form-item>
            </template>
          </el-table-column>
        </el-table>
        <el-button @click="addRow" size="small" style="margin-top:0.3%;font-size:14px;width: 100%;border-style: dashed">
          + 添加
        </el-button>
      </el-row>
    </el-form>
    <div style="padding-bottom:10px">
      <span style="float: right;">
         <el-button type="primary" :loading="beneficiaryFormSaveLoading" size="mini"
                    :disabled="(node !== 'accept' && node !== 'review' && node !== 'firstTrial' && node !=='input')"
                    @click="beneficiaryFormSave">保存</el-button>
         <el-button type="primary" size="mini" @click="dialogClosed">返回</el-button>
      </span>
    </div>
  </el-dialog>
</template>

<script>
  import {getPolicy, getRisk, bnfInfoSave, getBnfInfo, deleteBnf} from '@/api/claim/register'
  import moment from 'moment'

  export default {
    props: {
      dictList: Array,
      value: Boolean,
      node: String,
      insuredBaseInfo: Object,
      claimno: String,
      insuredInfo: Object
    },
    //dicts:['payee_insuredrelationship', 'card_type', 'sex', 'nativeplace', 'bnf_occupation', 'bnf_grade'],
    filters: {
      dateFilter(value) {
        if (value !== null && value !== '') {
          return moment(value).format("YYYY-MM-DD")
        }
      }
    },
    data() {
      return {
        beneficiaryForm: {
          tableData: []
        },
        policyNos: [],
        loading: false,
        beneficiaryFormSaveLoading: false,
        /*'payee_insuredrelationship', 'card_type', 'sex', 'nativeplace', 'bnf_occupation', 'bnf_grade'*/
        payee_insuredrelationshipOptions: [],
        card_typeOptions: [],
        sexOptions: [],
        nativeplaceOptions: [],
        bnf_occupationOptions: [],
        bnf_gradeOptions: []
      }
    },
    mounted() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.payee_insuredrelationshipOptions = this.dictList.find(item => {
          return item.dictType == 'payee_insuredrelationship'
        }).dictDate
        this.card_typeOptions = this.dictList.find(item => {
          return item.dictType == 'card_type'
        }).dictDate
        this.sexOptions = this.dictList.find(item => {
          return item.dictType == 'sex'
        }).dictDate
        this.nativeplaceOptions = this.dictList.find(item => {
          return item.dictType == 'nativeplace'
        }).dictDate
        this.bnf_occupationOptions = this.dictList.find(item => {
          return item.dictType == 'bnf_occupation'
        }).dictDate
        this.bnf_gradeOptions = this.dictList.find(item => {
          return item.dictType == 'bnf_grade'
        }).dictDate
      }
    },
    computed: {
      dialogVisable: {
        set() {
        },
        get() {
          return this.value
        }
      }
    },
    methods: {
      addRow() {
        const field = {
          bnfNo: '',
          editFlag: true,
          riskCode: '',
          bnfLot: 100,
          bnfName: '',
          idCardType: '',
          sex: '',
          idNo: '',
          birthday: '',
          nationality: 'ML',
          address: '',
          disabledFlag: false
        }
        this.beneficiaryForm.tableData.push(field)
        setTimeout(() => {
          this.$refs.table.setCurrentRow(field)
        }, 10)
      },
      editHandle(i, row) {
        this.beneficiaryForm.tableData[i].editFlag = true
        if (row.relation === '00') {
          this.beneficiaryForm.tableData[i].disabledFlag = true
        }
        this.editData(row.contNo, i)
      },
      delHandle(index, row) {
        this.$confirm(`是否确定取消?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.beneficiaryForm.tableData.splice(index, 1)
          if (row.bnfNo !== null && row.bnfNo !== '') {
            deleteBnf(row.bnfNo).then(res => {
              console.log(res.status)
            })
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      dialogClosed() {
        this.$refs.beneficiaryForm.resetFields()
        this.$emit('input', false)
      },
      bnfInfo() {
        if (this.claimno !== '' && this.claimno !== null && this.claimno !== undefined) {
          this.getPolicyNo()
          this.getBnf()
        }
      },
      changePolicy(policyNo, index) {
        getRisk(policyNo).then(res => {
          if (res.status === '1') {
            this.beneficiaryForm.tableData[index].riskCode = ''
            this.$set(this.beneficiaryForm.tableData[index], 'riskList', res.data)
          }
        })
      },
      changeRelation(row, index) {
        if (row.relation === '00') {
          console.log(this.insuredInfo)
          if (this.node === 'accept' || this.node === 'input') {
            if (this.insuredBaseInfo !== null && this.insuredBaseInfo !== undefined) {
              this.beneficiaryForm.tableData[index].bnfName = this.insuredBaseInfo.insuredName
              this.beneficiaryForm.tableData[index].idCardType = this.insuredBaseInfo.idType
              this.beneficiaryForm.tableData[index].idNo = this.insuredBaseInfo.idNo
              this.beneficiaryForm.tableData[index].sex = this.insuredBaseInfo.sex
              this.beneficiaryForm.tableData[index].birthday = this.insuredBaseInfo.birthdate
              this.beneficiaryForm.tableData[index].nationality = this.insuredBaseInfo.nationality
              this.beneficiaryForm.tableData[index].address = this.insuredBaseInfo.address
            }
          }
          if (this.node === 'firstTrial' || this.node === 'review') {
            if (this.insuredInfo !== null && this.insuredInfo !== undefined) {
              this.beneficiaryForm.tableData[index].bnfName = this.insuredInfo.name
              this.beneficiaryForm.tableData[index].idCardType = this.insuredInfo.idtype
              this.beneficiaryForm.tableData[index].idNo = this.insuredInfo.idcardno
              this.beneficiaryForm.tableData[index].sex = this.insuredInfo.sex
              this.beneficiaryForm.tableData[index].birthday = this.insuredInfo.birthday
              this.beneficiaryForm.tableData[index].nationality = this.insuredInfo.nationality
              this.beneficiaryForm.tableData[index].address = this.insuredInfo.address
            }
          }
          this.beneficiaryForm.tableData[index].disabledFlag = true
        } else {
          this.$refs.beneficiaryForm.clearValidate(['tableData.' + index + '.bnfName',
            'tableData.' + index + '.idCardType',
            'tableData.' + index + '.idNo', 'tableData.' + index + '.sex', 'tableData.' + index + '.birthday', 'tableData.' + index + '.nationality'])
          this.beneficiaryForm.tableData[index].bnfName = ''
          this.beneficiaryForm.tableData[index].idCardType = ''
          this.beneficiaryForm.tableData[index].idNo = ''
          this.beneficiaryForm.tableData[index].sex = ''
          this.beneficiaryForm.tableData[index].birthday = ''
          this.beneficiaryForm.tableData[index].nationality = ''
          this.beneficiaryForm.tableData[index].disabledFlag = false
        }
      },
      changeIdNo(row, index) {
        if (row.idCardType === '0') {
          this.go(row.idNo, row.idNo.length, index)
        }
      },
      editData(policyNo, index) {
        getRisk(policyNo).then(res => {
          if (res.status === '1') {
            this.$set(this.beneficiaryForm.tableData[index], 'riskList', res.data)
          }
        })
      },
      getPolicyNo() {
        getPolicy(this.claimno).then(res => {
          if (res.status === '1') {
            this.policyNos = res.data
          } else {
            this.$message({message: '保单号初始化失败！', type: 'error'})
          }
        })
      },
      beneficiaryFormSave() {
        this.$refs.beneficiaryForm.validate(valid => {
          if (valid) {
            if (this.beneficiaryForm.tableData.length === 0) {
              this.$message({message: '请先录入受益人信息！', type: 'warning'})
              return false
            }
            this.beneficiaryFormSaveLoading = true
            let rate = 0;
            for (let i = 0; i < this.beneficiaryForm.tableData.length; i++) {
              rate += parseInt(this.beneficiaryForm.tableData[i].bnfLot)
              if (this.beneficiaryForm.tableData[i].idCardType === '0') {
                if (!this.judgeIdNo(this.beneficiaryForm.tableData[i].idNo, this.beneficiaryForm.tableData[i].idNo.length,
                  this.beneficiaryForm.tableData[i].sex, this.beneficiaryForm.tableData[i].birthday)) {
                  this.$message({message: '证件类型为身份证，证件号与性别和出生日期不对应，请核实！', type: 'warning'})
                  this.beneficiaryFormSaveLoading = false
                  return false
                }
              }
            }
            if (rate !== 100) {
              this.$message({message: '所有受益人的受益份额之和应等于100%！', type: 'warning'})
              this.beneficiaryFormSaveLoading = false
              return false
            }
            const requestData = {
              bnfList: this.beneficiaryForm.tableData,
              claimNo: this.claimno
            }
            bnfInfoSave(requestData).then(res => {
              if (res.status === '1') {
                if (res.data) {
                  this.$message({message: '受益人信息存储成功！', type: 'success'})
                  this.beneficiaryFormSaveLoading = false
                  this.getBnf()
                } else {
                  this.$message({message: '同一保单同一险种下不能录入相同的受益人！', type: 'warning'})
                  this.beneficiaryFormSaveLoading = false
                }
              } else {
                this.$message({message: '受益人信息存储失败！', type: 'error'})
                this.beneficiaryFormSaveLoading = false
              }
            })
          }
        })
      },
      getBnf() {
        this.loading = true
        getBnfInfo(this.claimno).then(res => {
          if (res.status === '1') {
            this.beneficiaryForm.tableData = res.data
          } else {
            this.$message({message: '受益人信息初始化失败！', type: 'error'})
          }
        }).finally(() => {
          this.loading = false
        })
      },
      // 实现自动生成生日，性别，年龄
      go(value, length, index) {
        let iden = value;
        let sex = null;
        let birth = null;
        let myDate = new Date();
        let month = myDate.getMonth() + 1;
        let day = myDate.getDate();
        let age = 0;
        if (length === 18) {
          age = myDate.getFullYear() - iden.substring(6, 10) - 1;
          sex = iden.substring(16, 17);
          birth = iden.substring(6, 10) + "-" + iden.substring(10, 12) + "-" + iden.substring(12, 14);
          if (iden.substring(10, 12) < month || iden.substring(10, 12) == month && iden.substring(12, 14) <= day) age++;

        }
        if (length === 15) {
          age = myDate.getFullYear() - iden.substring(6, 8) - 1901;
          sex = iden.substring(13, 14);
          birth = "19" + iden.substring(6, 8) + "-" + iden.substring(8, 10) + "-" + iden.substring(10, 12);
          if (iden.substring(8, 10) < month || iden.substring(8, 10) == month && iden.substring(10, 12) <= day) age++;
        }

        if (sex % 2 === 0)
          sex = '1';
        else
          sex = '0';

        this.beneficiaryForm.tableData[index].sex = sex
        this.beneficiaryForm.tableData[index].birthday = birth
      },
      judgeIdNo(value, length, csex, cbirthday) {
        let iden = value;
        let sex = null;
        let birth = null;
        let myDate = new Date();
        let month = myDate.getMonth() + 1;
        let day = myDate.getDate();
        let age = 0;
        if (length === 18) {
          age = myDate.getFullYear() - iden.substring(6, 10) - 1;
          sex = iden.substring(16, 17);
          birth = iden.substring(6, 10) + "-" + iden.substring(10, 12) + "-" + iden.substring(12, 14);
          if (iden.substring(10, 12) < month || iden.substring(10, 12) == month && iden.substring(12, 14) <= day) age++;

        }
        if (length === 15) {
          age = myDate.getFullYear() - iden.substring(6, 8) - 1901;
          sex = iden.substring(13, 14);
          birth = "19" + iden.substring(6, 8) + "-" + iden.substring(8, 10) + "-" + iden.substring(10, 12);
          if (iden.substring(8, 10) < month || iden.substring(8, 10) == month && iden.substring(10, 12) <= day) age++;
        }

        if (sex % 2 === 0)
          sex = '1';
        else
          sex = '0';
        if (sex === csex && birth === moment(cbirthday).format("YYYY-MM-DD")) {
          return true
        } else {
          return false
        }
      }

    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }
</style>
