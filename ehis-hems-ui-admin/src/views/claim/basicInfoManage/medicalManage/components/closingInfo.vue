<template>
  <div>


    <el-form ref="closingFrom" :rules="closingFromRules" :model="closingFrom.baseProviderSettle" size="mini"
             label-width="170px"
             :disabled="disabledFlag" class="baseInfo_class">
      <el-card class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span>结算信息</span>
          <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle">保存</el-button>
        </span>
        </div>
        <el-row>
          <el-col :span="8">
            <el-form-item label="结算币种：" prop="currency">
              <el-select v-model="closingFrom.baseProviderSettle.currency" class="item-width"
                         placeholder="请选择" clearable>
                <el-option v-for="item in currencyOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否仅结算理赔责任：" prop="claimFlag">

              <el-select v-model="closingFrom.baseProviderSettle.claimFlag" class="item-width" placeholder="请选择"
                         clearable>

                <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="生效日期：" prop="effectTime">
              <el-date-picker v-model="closingFrom.baseProviderSettle.effectTime" type="date" class="item-width"
                              clearable
                              placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="支付通知时间：" prop="noticeDay">
              <el-input ref="hospitalname" v-model="closingFrom.baseProviderSettle.noticeDay" class="item-width"
                        clearable
                        placeholder="请输入"/>
              天
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="padding-left: 50px">
              <el-button size="mini" type="primary" @click="checkRecord">查看维护记录</el-button>
            </el-form-item>
          </el-col>

        </el-row>

      </el-card>
    </el-form>


    <el-card class="box-card department-style" style="margin-top: 1px;">
      <el-form ref="closingTableForm" :rules="closingFromRules" size="small" :model="closingFrom"
               :disabled="disabledFlag">
        <el-table ref="closingTable" :data="closingFrom.baseBankVo"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="mini" highlight-current-row style="width: 100%;">
          <el-table-column prop="accountType" align="center" label="账号类型" header-align="center">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.accountType'"
                            :rules="closingFromRules.accountType">
                <el-select v-model="scope.row.accountType" placeholder="请选择" size="mini" clearable>
                  <el-option v-for="item in account_type_newOptions" :label="item.dictLabel" :value="item.dictValue"
                             :key="item.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ scope.row.accountTypeName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accountName" align="center" header-align="center" label="银行账号名称">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.accountName'"
                            :rules="closingFromRules.accountName">
                <el-input v-model="scope.row.accountName" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.accountName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accountNo" align="center" header-align="center" label="银行账号">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.accountNo'"
                            :rules="closingFromRules.accountNo">
                <el-input v-model="scope.row.accountNo" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.accountNo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankCode" align="center" header-align="center" label="开户银行代码">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.bankCode'"
                            :rules="closingFromRules.bankCode">
                <el-input v-model="scope.row.bankCode" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bankCode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankName" align="center" header-align="center" label="开户银行名称">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.bankName'"
                            :rules="closingFromRules.bankName">
                <el-input v-model="scope.row.bankName" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bankName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankDetail" align="center" header-align="center" label="银行信息描述">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.bankDetail'"
                            :rules="closingFromRules.bankDetail">
                <el-input v-model="scope.row.bankDetail" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bankDetail }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bussinessStatus" align="center" header-align="center" label="账号状态">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'baseBankVo.' + scope.$index + '.bussinessStatus'"
                            :rules="closingFromRules.bussinessStatus">
                <el-select v-model="scope.row.bussinessStatus" placeholder="请选择" size="mini" clearable>
                  <el-option v-for="item in accountacc_statusOptions" :label="item.dictLabel" :value="item.dictValue"
                             :key="item.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-else>{{  getAccountacc_statusOptions(scope.row.bussinessStatus) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" min-width="94" fixed="right">
            <template slot-scope="scope">

              <span style="cursor: pointer;">
                  <el-button type="text" size="mini" v-if="closingTableShow && !scope.row.isSet"
                             @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                 <el-button type="text" size="mini" v-if="closingTableShow"
                            @click="delHandle(scope.$index, scope.row)">删除</el-button>
                </span>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="text" size="mini"
                   style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                   @click="addClosingHandle"> + 添加
        </el-button>
      </el-form>
    </el-card>
    <el-dialog
      :visible.sync="dialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">

        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">结算信息维护记录</span>
        </div>
        <el-table ref="medicalRecordTable"
                  v-loading="loading"
                  :data="closingLogData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  style="width: 100%;">

          <el-table-column key="1" align="center" prop="currencyName" min-width="150" label="结算币种"/>
          <el-table-column key="2" align="center" min-width="100" prop="claimFlagName" label="是否仅结算理赔责任"/>
          <el-table-column key="3" align="center" prop="effectTime" min-width="150" label="生效日期"/>
          <el-table-column key="4" align="center" min-width="100" prop="noticeDay" label="支付通知时间"/>
          <el-table-column key="5" align="center" prop="createBy" min-width="150" label="维护人"/>
          <el-table-column key="6" align="center" prop="updateTime" label="维护时间" min-width="120"/>
        </el-table>
<!--        <div>
          <el-pagination
            :total="totalCount"
            :current-page="formSearch.pageNum"
            :page-size="formSearch.pageSize"
            :page-sizes="[10, 20, 30, 40]"
            style="margin-top: 8px; text-align: right;"
            layout="sizes, prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
        </div>-->
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="formSearch.pageNum"
          :limit.sync="formSearch.pageSize"
          @pagination="getData"
        />




    </el-dialog>
  </div>


</template>
<script>
import {getbankInfo, addbankInfo, selectsettleInfo} from "@/api/baseInfo/medicalManage";


export default {
  props: {
    status: String,
    annexupload: Boolean,
    closingFrom: {
      type: Object,
      default: function () {
        return {}
      }
    },
    closingTableShow: Boolean,
    providerCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean,
    dictList: Array,

  },

  data() {
    const checkEffectTime = (rules, value, callback) => {
      if (this.closingFrom.baseProviderSettle.claimFlag == '01') {
        if (!this.closingFrom.baseProviderSettle.effectTime) {
          callback(new Error('生效日期不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkNoticeDay = (rules, value, callback) => {
        let reg= /^(\d+|\d+)$/
      if(value) {
        if (value < 0) {
          callback(new Error("请输入正整数"));
        } else if (!reg.test(value)) {
          callback(new Error("请输入正整数"));
        } else {
          callback();
        }
      }
      else {
        callback(new Error("支付通知时间不能为空"));
      }

    }
    return {
      totalCount: 0,
      // 查询参数
      formSearch: {
        pageNum: 1,
        pageSize: 10,
        providerCode: undefined,
        orgFlag: undefined,
      },
      closingLogData: [],
      dialogVisible: false,
      loading: false,
      modalValue: false,
      contacttype: [
        {label: 'xxx', value: '1'},
        {label: 'ccc', value: '2'}
      ],
      closingFromRules: {
        effectTime: [{validator: checkEffectTime, trigger: 'blur'}],

        currency: [{required: true, message: '不能为空！', trigger: 'change'}],
        claimFlag: [{required: true, message: '不能为空！', trigger: 'change'}],
        noticeDay: [{required: true, validator: checkNoticeDay, trigger: 'blur'}],
        accountType: [{required: true, message: '不能为空！', trigger: 'change'}],
        accountName: [{required: true, message: '不能为空！', trigger: 'blur'}],
        accountNo: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bankCode: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bankName: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bankDetail: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bussinessStatus: [{required: true, message: '不能为空！', trigger: 'change'}]
      },
      currencyOptions: [],
      yes_or_noOptions: [],
      account_type_newOptions: [],
      accountacc_statusOptions: []
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
    getAccountacc_statusOptions(data) {
      return this.selectDictLabel(this.accountacc_statusOptions, data)
    },
    init() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.currencyOptions = this.dictList.find(item => {
          return item.dictType == 'currency'
        }).dictDate
        this.yes_or_noOptions = this.dictList.find(item => {
          return item.dictType == 'yes_or_no'
        }).dictDate
        this.account_type_newOptions = this.dictList.find(item => {
          return item.dictType == 'account_type_new'
        }).dictDate
        this.accountacc_statusOptions = this.dictList.find(item => {
          return item.dictType == 'accountacc_status'
        }).dictDate

      }
    },

    //结算信息保存
    saveHandle() {

      /* if(!this.annexupload) {*/

      this.$refs.closingFrom.validate((valid1) => {

        if (this.closingFrom.baseBankVo != null && this.closingFrom.baseBankVo.length > 0) {
          this.$refs.closingTableForm.validate((valid2) => {
            if (valid1 && valid2) {
              //存在调用结算信息保存的接口
              if (this.providerCode) {
                this.closingFrom.providerCode = this.providerCode
                /* if (this.isAdd) {*/
                /* let formData ={
              formData: this.closingFrom,
              orgFlag :this.status
            }*/
                const subFormSearch = JSON.parse(JSON.stringify(this.closingFrom))
                subFormSearch.orgFlag = this.status
                addbankInfo(subFormSearch).then(res => {
                  if (res != null && res.code == '200') {
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
              } /*else {
                updatebankInfo(this.closingFrom).then(res => {
                  if (res.code === '200') {
                    this.$message({
                      message: '修改成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                  } else {
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
              this.$message.warning('结算信息必录项未必录')
            }

          })
        }
        else {
          this.$message({
            message: '至少添加一条结算账户信息！',
            center: true,
            type: "warning"
          });
        }
      })
      /*}
      else {
        this.$message.error('请先上传附件')
      }*/
    },
    checkRecord() {
      this.dialogVisible = true
      //调用查询方法

      this.getData()
    },

    getData() {
      this.formSearch.orgFlag=this.status
      this.formSearch.providerCode = this.providerCode
      selectsettleInfo(this.formSearch).then(res => {
        this.closingLogData = res.rows
        this.totalCount=res.total
      }).catch(res => {

      })
    },
/*
    handleSizeChange(val) {
      this.formSearch.pageSize = val
      this.getData()
    },
    handleCurrentChange(val) {
      this.formSearch.pageNum = val
      this.getData()
    },*/

    //增加一行结算信息
    addClosingHandle() {
      const field = {
        accountType: '',
        accountName: '',
        accountNo: '',
        bankCode: '',
        bankName: '',
        bankDetail: '',
        status: '',
        providerCode: this.providerCode,
        isSet: true
      }

      this.closingFrom.baseBankVo.push(field)


      /* setTimeout(() => {
         this.$refs.closingTable.setCurrentRow(field)
       }, 10)*/
    },
    editHandle(index, row) {
      this.closingFrom.baseBankVo[index].id = ''
      this.closingFrom.baseBankVo[index].isSet = true
      this.closingTableShow = true

    },
    //重置
    resetForm() {

      this.$refs.closingFrom.resetFields()
      this.$refs.closingTableForm.resetFields()
      this.closingFrom.baseBankVo = [],
        this.closingFrom.baseProviderSettle = {}


    },
    delHandle(index, row) {
      this.closingFrom.baseBankVo.splice(index, 1)
    },

    // 校验数据
    validateForm() {
      let flag = null
      this.$refs.closingFrom.validate((valid1) => {
        if (this.closingFrom.baseBankVo != null && this.closingFrom.baseBankVo.length > 0) {
          this.$refs.closingTableForm.validate((valid2) => {
            if (valid1 && valid2) {
              flag = '01'
            } else {
              flag = '03'
            }
          })
        }
        else {
          flag='02'
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
