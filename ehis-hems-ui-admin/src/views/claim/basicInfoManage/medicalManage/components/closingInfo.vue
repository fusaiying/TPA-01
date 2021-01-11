<template>
  <div>
    <el-form ref="closingFrom" :rules="closingFromRules" :model="closingFrom.fromData" size="mini" :disabled="disabledFlag"    class="baseInfo_class">
  <el-card class="box-card department-style" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>结算信息</span>
      <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
        </span>
    </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="结算币种：" prop="currency" >
            <el-select v-if="closingFrom.fromData" v-model="closingFrom.fromData.currency" class="item-width" placeholder="请选择" clearable>
              <el-option value="01" label="人民币"></el-option>
              <el-option value="02" label="美元"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否仅结算理赔责任：" prop="claimFlag">

            <el-select v-model="closingFrom.fromData.claimFlag" class="item-width" placeholder="请选择" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="生效日期：" prop="effectTime">
            <el-date-picker v-model="closingFrom.fromData.effectTime" type="date" class="item-width" clearable
                            placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="支付通知时间：" prop="noticeDay">
            <el-input ref="hospitalname" v-model="closingFrom.fromData.noticeDay" class="item-width" clearable
                      placeholder="请输入"/>
            天
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item style="padding-left: 50px">
            <el-button size="mini" type="success" @click="checkRecord">查看维护记录</el-button>
          </el-form-item>
        </el-col>

      </el-row>

  </el-card>
    </el-form>


    <el-card class="box-card department-style" style="margin-top: 1px;">
      <el-form ref="closingTableForm" :rules="closingFromRules"  size="small" :model="closingFrom" :disabled="disabledFlag">
        <el-table ref="closingTable" :data="closingFrom.tableData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="mini" highlight-current-row style="width: 100%;">
          <el-table-column prop="accountType" align="center" label="账号类型" header-align="center">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.accountType'"
                            :rules="closingFromRules.accountType">
                <el-select v-model="scope.row.accountType" placeholder="请选择" size="mini">
                  <el-option v-for="option in contacttype" :key="option.id" :label="option.label"
                             :value="option.value"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ scope.row.accountType }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accountName" align="center" header-align="center" label="银行账号名称">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.accountName'"
                            :rules="closingFromRules.accountName">
                <el-input v-model="scope.row.accountName" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.accountName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accountNo" align="center" header-align="center" label="银行账号">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.accountNo'"
                            :rules="closingFromRules.accountNo">
                <el-input v-model="scope.row.accountNo" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.accountNo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankCode" align="center" header-align="center" label="开户银行代码">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.bankCode'"
                            :rules="closingFromRules.bankCode">
                <el-input v-model="scope.row.bankCode" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bankCode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankName" align="center" header-align="center" label="开户银行名称">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.bankName'"
                            :rules="closingFromRules.bankName">
                <el-input v-model="scope.row.bankName" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bankName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankDetail" align="center" header-align="center" label="银行信息描述">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.bankDetail'"
                            :rules="closingFromRules.bankDetail">
                <el-input v-model="scope.row.bankDetail" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.bankDetail }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" header-align="center" label="账号状态">
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.status'"
                            :rules="closingFromRules.status">
                <el-select v-model="scope.row.status" placeholder="请选择" size="mini">
                  <!--                    <el-option v-for="option in contacttype" :key="option.id" :label="option.label"
                                                 :value="option.value"/>-->
                  <el-option value="01" label="有效"/>
                  <el-option value="01" label="无效"/>
                </el-select>
              </el-form-item>
              <span v-else>{{ scope.row.status }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" min-width="94" fixed="right">
            <template slot-scope="scope">

              <span style="cursor: pointer;">
                  <el-button type="text" size="mini"  v-if="closingTableShow && !scope.row.isSet"
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
  </div>




</template>
<script>
import {getbankInfo, addbankInfo, updataHospital} from "@/api/baseInfo/medicalManage";


export default {
  props: {
    status: String,
    annexupload: Boolean,
    closingFrom: {
      type : Object,
      default: function (){
        return {}
      }
    },
    closingTableShow: Boolean,
    providerCode: String,
    disabledFlag: Boolean
  },

  data() {
    return {
      contacttype: [
        {label: 'xxx', value: '1'},
        {label: 'ccc', value: '2'}
      ],
      closingFromRules: {
        currency: [{required: true, message: '不能为空！', trigger: 'change'}],
        claimFlag: [{required: true, message: '不能为空！', trigger: 'change'}],
        noticeDay: [{required: true, message: '不能为空！', trigger: 'blur'}],
        accountType: [{required: true, message: '不能为空！', trigger: 'change'}],
        accountName: [{required: true, message: '不能为空！', trigger: 'blur'}],
        accountNo: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bankCode: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bankName: [{required: true, message: '不能为空！', trigger: 'blur'}],
        bankDetail: [{required: true, message: '不能为空！', trigger: 'blur'}],
        status: [{required: true, message: '不能为空！', trigger: 'change'}]
      },
    }
  },
  mounted() {

  },
  methods: {
    //结算信息保存
    saveHandle() {
     /* if(!this.annexupload) {*/
        this.$refs.closingFrom.validate((valid1) => {
          this.$refs.closingTableForm.validate((valid2) => {
            if (valid1 && valid2) {
              //存在调用结算信息保存的接口
              if (this.hospitalcode) {
                addbankInfo(this.closingFrom).then(res => {
                  if (res.code === '200') {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                  } else  {
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
              return false
            }

          })
        })
      /*}
      else {
        this.$message.error('请先上传附件')
      }*/
    },
    checkRecord() {},
    //增加一行结算信息
    addClosingHandle() {
      alert(222222)
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

        this.closingFrom.tableData.push(field)


      /* setTimeout(() => {
         this.$refs.closingTable.setCurrentRow(field)
       }, 10)*/
    },
    editHandle(index, row) {
      this.closingFrom.tableData[index].id = ''
      this.closingFrom.tableData[index].isSet = true
      this.closingTableShow=true

    },
    //重置
    resetFrom()
    {
      this.closingFrom.tableData=[],
        this.closingFrom.fromData={}
      this.$refs.closingFrom.resetFields()
      this.$refs.closingTableForm.resetFields()



    },
    delHandle(index, row){
      this.closingFrom.tableData.splice(index,1)
    },

    // 校验数据
    validateForm () {
      let flag = null
      this.$refs.closingFrom.validate((valid1) => {
        this.$refs.closingTableForm.validate((valid2) => {
          if (valid1 && valid2) {
            flag = true
          } else {
            flag = false
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
