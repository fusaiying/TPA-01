<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <span style="font-size:16px;color:black">领款人信息</span>
        <div style="float: right;">
          <el-button v-if="status==='edit' && (node==='accept' || node==='calculateReview')" type="primary" size="mini"
                     @click="addOrEdit('add')">新增
          </el-button>
        </div>
      </div>
    </div>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" width="140" prop="payMode" label="收款方式" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ selectDictLabel(collectedmodeOptions, scope.row.payMode) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" v-if="baseInfo.claimtype==='02'" key="1" prop="relationIns" label="与被保人关系"
                       show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ selectDictLabel(relation_ship_applyOptions, scope.row.relationIns) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="payeeName" label="领款人" show-overflow-tooltip/>
      <el-table-column align="center" v-if="baseInfo.claimtype==='02'" key="2" prop="payeeMobile" label="手机号"
                       show-overflow-tooltip/>
      <el-table-column align="center" prop="accAttribute" label="账户属性" show-overflow-tooltip>
        <template slot-scope="scope">
          <span v-if="baseInfo.claimtype==='02'">{{ selectDictLabel(account_attributeOptions, '0') }}</span>
          <span v-else>{{ selectDictLabel(account_attributeOptions, '1') }}</span>
        </template>

      </el-table-column>
      <el-table-column prop="payeeBank" align="center" label="开户行" show-overflow-tooltip/><!--查码表-->
      <el-table-column prop="accNo" align="center" label="账户" show-overflow-tooltip/>
      <el-table-column align="center" v-if="baseInfo.claimtype==='02' && status==='edit' && node==='accept'"
                       label="操作" width="140" key="9">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="addOrEdit('edit',scope.row)">编辑</el-button>
          <el-button size="mini" style="color: red" type="text" @click="delPayee(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogFormVisible" width="80%" hight="90%">
      <div>
        <div>
          <span style="font-size: 20px">账户信息维护</span>
          <span style="float: right;">
          <el-button type="primary" size="mini" @click="">健康险客户账户查询</el-button>
          <el-button type="primary" size="mini" @click="save">保存</el-button>
          <el-button size="mini" @click="goBack">返回</el-button>
        </span>
        </div>
        <el-divider/>
        <el-table
          v-if="false"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tkTableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;margin-bottom: 10px">
          <el-table-column label="" width="40">
            <template slot-scope="scope">
              <el-radio :label="scope.$index" v-model="radio" @change.native="getCurrentRow(scope.row)" style="color: #fff;padding-left: 10px; margin-right: -25px;"></el-radio>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="payMode" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="payMode" label="分单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="payMode" label="保单生效日" show-overflow-tooltip/>
          <el-table-column align="center" prop="payMode" label="银行代码" show-overflow-tooltip/>
          <el-table-column align="center" prop="payMode" label="银行描述" show-overflow-tooltip/>
          <el-table-column align="center" prop="accNo" label="银行账号" show-overflow-tooltip/>
          <el-table-column align="center" prop="payeeName" label="账户名" show-overflow-tooltip/>
          <el-table-column align="center" prop="payeeIdType" label="证件类型" show-overflow-tooltip/>
          <el-table-column align="center" prop="payeeIdNo" label="证件号码" show-overflow-tooltip/>
          <el-table-column align="center" prop="addressId" label="地区码" show-overflow-tooltip/>
        </el-table>
        <el-form ref="baseForm" :model="baseForm" :rules="tableFormRules"
                 style="padding-bottom: 30px;" label-width="150px" size="mini" class="baseInfo_class">
          <el-row>
            <el-col :span="8">
              <el-form-item label="领款方式：" prop="payMode">
                <el-select v-model="baseForm.payMode" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="option in collectedmodeOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="与被保人关系：" prop="relationIns">
                <el-select v-model="baseForm.relationIns" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="option in relation_ship_applyOptions" :key="option.dictValue"
                             :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="领款人姓名：" prop="payeeName">
                <el-input v-model="baseForm.payeeName" class="item-width" clearable size="mini"
                          placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="性别：" prop="payeeSex">
                <el-select v-model="baseForm.payeeSex" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="option in rgtSexOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="手机号码：" prop="payeeMobile">
                <el-input v-model="baseForm.payeeMobile" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="国籍：" prop="payeeNationality"
                            :rules="tableFormRules.payeeNationality">
                <el-select v-model="baseForm.payeeNationality" class="item-width"
                           placeholder="请选择" clearable>
                  <el-option key="01" label="中国"
                             value="01"/>
                  <!--<el-option v-for="option in nativeplaceOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>-->
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="证件类型：" prop="payeeIdType">
                <el-select v-model="baseForm.payeeIdType" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件号码：" prop="payeeIdNo">
                <el-input v-model="baseForm.payeeIdNo" class="item-width" clearable size="mini"
                          placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件有效日期：" prop="idEndDate">
                <el-date-picker
                  v-model="baseForm.idEndDate"
                  :disabled="baseForm.checked"
                  style="width: 168px;"
                  type="date"
                  clearable
                  placeholder="选择日期"/>
                <el-checkbox v-model="baseForm.checked" @change="longDate">长期</el-checkbox>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="开户行：" prop="payeeBank">
                <el-select v-model="baseForm.payeeBank" class="item-width" placeholder="请选择" clearable>
                  <!-- <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>-->
                  <el-option key="01" label="上海交通银行"
                             value="01"/>
                  <el-option key="02" label="上海中国银行"
                             value="02"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="分配比例：" prop="payeeRatio">
                <el-input v-model="baseForm.payeeRatio" class="item-width" clearable size="mini"
                          placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <span>%</span>
            </el-col>
            <el-col :span="8">
              <el-form-item label="职业：" prop="payeeOccupation">
                <el-select v-model="baseForm.payeeOccupation" filterable class="item-width"
                           placeholder="请选择" clearable>
                  <!-- <el-option v-for="option in payee_occupationOptions" :key="option.dictValue" :label="option.dictLabel"
                              :value="option.dictValue"/>-->
                  <el-option key="01" label="老师"
                             value="01"/>
                  <el-option key="02" label="销售"
                             value="02"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="账号：" prop="accNo">
                <el-input v-model="baseForm.accNo" class="item-width" clearable size="mini"
                          placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="账户属性：" prop="accAttribute">
                <el-select v-model="baseForm.accAttribute" filterable class="item-width"
                           placeholder="请选择" clearable>
                  <el-option v-for="option in account_attributeOptions" :key="option.dictValue"
                             :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="确认账号：" prop="accNoCheck">
                <el-input v-model="baseForm.accNoCheck" class="item-width" clearable size="mini"
                          @paste.native.capture.prevent="handlePaste"
                          placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="详细地址：" prop="address">
                <el-cascader
                  v-model="region"
                  :options="regions"
                  class="item-width"
                  clearable
                  filterable
                  @change="changeAddress"/>
                <el-input v-model="baseForm.address" show-word-limit maxlength="100"
                          style="width:61%;" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-dialog>

  </el-card>
</template>
<script>
  import {getAddress} from '@/api/supplierManager/supplier'
  import {listRemark, addPayee, editPayee, delPayee, listRemarkRptNo} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'relation_ship_apply'}, {dictType: 'card_type'}, {dictType: 'rgtSex'},
    {dictType: 'collectedmode'}, {dictType: 'account_attribute'},]
  export default {
    props: {
      baseInfo: Object,
      fixInfo: Object,
      applicantData: Object,
      sonPayeeInfoData: Array,
      status: String,
      node: String,
    },
    watch: {
      applicantData: function (newValue) {
        this.insuredData = newValue
        this.baseForm.payeeName = this.insuredData.name,
          this.baseForm.payeeSex = this.insuredData.sex,
          this.baseForm.payeeMobile = this.insuredData.mobile,
          this.baseForm.payeeNationality = this.insuredData.nationality,
          this.baseForm.payeeIdType = this.insuredData.idType,
          this.baseForm.payeeIdNo = this.insuredData.idNo
        if (this.insuredData.idEndDate!=null && this.insuredData.idEndDate!==''){
          this.$set(this.baseForm,'idEndDate',this.insuredData.idEndDate)
          if (this.insuredData.idEndDate==='9999-12-31'){
            this.$set(this.baseForm,'checked',true)
          }
        }else {
          this.$set(this.baseForm,'idEndDate',this.insuredData.dateRange ? this.insuredData.dateRange[1] : '')
        }
        if (this.baseForm.idEndDate === '9999-12-31') {
          this.checked = true
        }
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue
      },
      sonPayeeInfoData: function (newValue) {
        if (newValue !== null && newValue !== undefined && newValue.length > 0) {
          this.tableData = newValue
        }
      }
    },
    data() {
      const checkRequired = (rule, value, callback) => {
        if (this.baseForm.relationIns !== '1') {
          if (!value) {
            callback(new Error('与被保人关系非本人时必录'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkAccNo = (rule, value, callback) => {
        let reg = /^[A-Za-z0-9]+$/;
        if (!value) {
          callback(new Error('请输入账号'))
        } else {
          if (!reg.test(value)) {
            callback(new Error('只允许录入数字和字母'))
          } else {
            callback()
          }
        }
      }
      const checkAccNoCheck = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请再次输入账号'))
        } else {
          if (value !== this.baseForm.accNo) {
            callback(new Error('输入的账号不一致，请重新输入'))
            return this.$message.warning(
              "输入的账号不一致，请重新输入!"
            )
          } else {
            callback()
          }
        }
      }
      const checkAddress = (rule, value, callback) => {
        if (!value) {
          callback(new Error('详细地址不能为空'))
        } else {
          if (this.region.length <= 0) {
            callback(new Error('省市区不能为空'))
          } else {
            callback()
          }
        }
      }
      return {
        fixInfoData: {},
        batchInfoData: {},
        insuredData: {},
        isAddOrEdit: '',
        radio:false,
        tkTableData:[{
          payeeName:'a',
        },{
          payeeName:'b',
        }],
        dialogFormVisible: false,
        baseForm: {
          rptNo: undefined,
          checked: false,
          payMode: undefined,
          relationIns: '1',
          payeeName: undefined,
          payeeSex: undefined,
          payeeMobile: undefined,
          payeeNationality: undefined,
          payeeIdType: undefined,
          payeeIdNo: undefined,
          idEndDate: undefined,
          payeeBank: undefined,
          payeeRatio: '100',
          payeeOccupation: undefined,
          accNo: undefined,
          accAttribute: '0',
          accNoCheck: undefined,
          province: undefined,
          city: undefined,
          district: undefined,
          address: undefined,
        },
        regions: [],
        region: [],
        activeNames: ["1"],
        amountFlag: false,
        beneficVis: false,
        //赔付信息规则
        payeeInfoRules: {},
        tableData: [],
        dictList: [],
        relation_ship_applyOptions: [],
        account_attributeOptions: [],
        collectedmodeOptions: [],
        card_typeOptions: [],
        rgtSexOptions: [],
        tableFormRules: {
          payMode: [{required: true, message: '领款方式不能为空!', trigger: 'blur'}],
          relationIns: [{required: true, message: '与被保人关系不能为空!', trigger: 'blur'}],
          payeeName: [{required: true, message: '领款人姓名不能为空!', trigger: 'blur'}],
          payeeSex: [{required: true, message: '性别不能为空!', trigger: 'blur'}],
          payeeMobile: {validator: checkRequired, trigger: 'blur'},//与被保人关系非本人时必录，否则非必录
          payeeNationality: {validator: checkRequired, trigger: 'blur'},//与被保人关系非本人时必录，否则非必录
          payeeIdType: {validator: checkRequired, trigger: 'blur'},//与被保人关系非本人时必录，否则非必录
          payeeIdNo: {validator: checkRequired, trigger: 'blur'},//与被保人关系非本人时必录，否则非必录
          idEndDate: {validator: checkRequired, trigger: 'blur'},//与被保人关系非本人时必录，否则非必录
          payeeBank: [{required: true, message: '开户行不能为空!', trigger: 'blur'}],
          payeeRatio: [{required: true, message: '分配比例不能为空!', trigger: 'blur'}],
          payeeOccupation: {validator: checkRequired, trigger: 'blur'},//与被保人关系非本人时必录，否则非必录
          accAttribute: [{required: true, message: '账户属性不能为空!', trigger: 'blur'}],
          accNo: {validator: checkAccNo, required: true, trigger: 'blur'},//允许录入数字和字母
          accNoCheck: {validator: checkAccNoCheck, required: true, trigger: 'blur'},//与账户录入信息不一致时阻断谈框提示  1、不允许复制
          address: {validator: checkAddress, required: true, trigger: 'blur'},
        }
      };
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.relation_ship_applyOptions = this.dictList.find(item => {
        return item.dictType === 'relation_ship_apply'
      }).dictDate
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType === 'card_type'
      }).dictDate
      this.rgtSexOptions = this.dictList.find(item => {
        return item.dictType === 'rgtSex'
      }).dictDate
      this.collectedmodeOptions = this.dictList.find(item => {
        return item.dictType === 'collectedmode'
      }).dictDate
      this.account_attributeOptions = this.dictList.find(item => {
        return item.dictType === 'account_attribute'
      }).dictDate

      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
        console.log(error)
      })
      this.getData()

    },
    methods: {
      save() {//新增  编辑
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            if (this.isAddOrEdit === 'add') {
              this.baseForm.rptNo = this.fixInfoData.rptNo
              addPayee(this.baseForm).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                }
                this.$emit("refresh-item", 'payeeInfo')
                this.dialogFormVisible = false
              }).catch(res => {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              })
            } else if (this.isAddOrEdit === 'edit') {
              editPayee(this.baseForm).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                }
                this.$emit("refresh-item", 'payeeInfo')
                this.dialogFormVisible = false
              }).catch(res => {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              })
            }
          }
        })


      },
      goBack() {
        this.dialogFormVisible = false
        this.$refs.baseForm.resetFields()
      },
      handlePaste() {
        //粘贴会触发来到这里,无操作就行
      },
      getData() {
        if (this.batchInfoData != null && this.batchInfoData.claimtype === '02') {//事后
          listRemark(this.fixInfoData.rptNo).then(res => {
            this.tableData = res.rows
          }).catch(error => {
            console.log(error)
          })
        } else if (this.batchInfoData != null && this.batchInfoData.claimtype === '01') {//直结掉医院的接口

        }
      },
      addOrEdit(status, row) {
        this.isAddOrEdit = status
        this.$emit('getApplicantData')
        this.dialogFormVisible = true
        this.baseForm = {
          rptNo: undefined,
          checked: false,
          payMode: undefined,
          relationIns: '1',
          payeeName: this.insuredData.name,
          payeeSex: this.insuredData.sex,
          payeeMobile: this.insuredData.mobile,
          payeeNationality: this.insuredData.nationality,
          payeeIdType: this.insuredData.idType,
          payeeIdNo: this.insuredData.idNo,
          idEndDate: '',
          payeeBank: undefined,
          payeeRatio: '100',
          payeeOccupation: undefined,
          accNo: undefined,
          accAttribute: '0',
          accNoCheck: undefined,
          province: undefined,
          city: undefined,
          district: undefined,
          address: undefined,
        }
        if (this.insuredData.idEndDate!=null && this.insuredData.idEndDate!==''){
          this.$set(this.baseForm,'idEndDate',this.insuredData.idEndDate)
          if (this.insuredData.idEndDate==='9999-12-31'){
            this.$set(this.baseForm,'checked',true)
          }
        }else {
          this.$set(this.baseForm,'idEndDate',this.insuredData.dateRange ? this.insuredData.dateRange[1] : '')
        }
        if (this.baseForm.idEndDate === '9999-12-31') {
          this.checked = true
        }
        if (status === 'edit') {
          this.isAddOrEdit = 'edit'
          this.baseForm = row
          if (row.idEndDate === '9999-12-31') {
            this.baseForm.checked = true
          }
        }
      },
      longDate() {
        if (this.baseForm.checked) {
          this.baseForm.idEndDate = '9999-12-31'
        }

      },
      delPayee(row) {
        this.$confirm('确认要删除当前数据吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delPayee(row).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '删除成功',
                type: 'success',
                center: true,
                showClose: true
              })
              //调用查询方法
              listRemarkRptNo(this.fixInfo.rptNo).then(res => {
                if (res != null && res.code === 200) {
                  this.tableData = res.data
                }
              })
            }
          }).catch(res => {
            this.$message({
              message: '删除失败',
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      changeAddress() {
        this.baseForm.province = this.region[0]
        this.baseForm.city = this.region[1]
        this.baseForm.district = this.region[2]
      },
      getCurrentRow(row){//获取当前行的数据
        console.log(row)
      }
    }
  };
</script>
<style scoped>
  .item-width {
    width: 200px;
  }

  .el-collapse {
    border: none;
  }

  .el-tabs ::v-deep .el-tabs__item {
    width: 10%;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-size: 14px;
  }
</style>
