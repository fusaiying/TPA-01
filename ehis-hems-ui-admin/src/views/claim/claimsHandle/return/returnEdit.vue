<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>退件信息</span>
        <el-button style="float: right;" type="primary" size="mini" @click="goBack">关闭</el-button>
        <el-button :disabled="showFlag" size="mini" style="width: 80px;float: right;margin-right:20px" type="primary" @click="saveHandleExpress">保存</el-button>
      </div>
      <el-form ref="baseForm" :disabled="showFlag" :rules="baseFormRules" :model="baseForm" label-width="110px" size="mini">
        <el-row style="margin-bottom: 30px">
          <el-col :span="12">
            <el-table
              v-show="tableFlag"
              ref="dataTable"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="tableData.filter((item, ind) => ind % 2 === 0)"
              size="small"
              highlight-current-row
              class="return_class"
              tooltip-effect="dark"
              @selection-change="handleSelectionChange">
              <el-table-column align="center"  v-if="showEle" type="selection" width="55"/>
              <el-table-column align="center"  :formatter="getmaterialtype" property="materialtype" label="材料类型"/>
              <el-table-column align="center"  property="receiveddate" label="提交时间"/>
              <el-table-column align="center"  property="originals" label="原件数量"/>
              <el-table-column align="center"  property="copies" label="复印件数量"/>
              <el-table-column align="center"  property="returnedquantity" label="退件数量">
                <template slot-scope="scope">
                  <el-input
                    v-model="scope.row.returnedquantity"
                    clearable
                    style="min-width: 95px"
                    size="mini"
                    placeholder="请输入"
                    @input="scope.row.returnedquantity = scope.row.returnedquantity > scope.row.remainReturned ? scope.row.remainReturned : scope.row.returnedquantity"/>
                </template>
              </el-table-column>
<!--              <el-table-column align="center"  property="remainReturned" label="剩余退件数量">-->
<!--                <template slot-scope="scope">-->
<!--                  {{ scope.row.remainReturned }}-->
<!--                </template>-->
<!--              </el-table-column>-->
            </el-table>
          </el-col>
          <el-col :span="12">
            <el-table
              v-show="tableFlag"
              ref="dataTable"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="tableData.filter((item, ind) => ind % 2 !== 0)"
              size="small"
              highlight-current-row
              class="return_class"
              tooltip-effect="dark"
              @selection-change="handleSelectionChange">
              <el-table-column align="center"  v-if="showEle" type="selection" width="55"/>
              <el-table-column align="center"  :formatter="getmaterialtype" property="materialtype" label="材料类型"/>
              <el-table-column align="center"  property="receiveddate" label="提交时间"/>
              <el-table-column align="center"  property="originals" label="原件数量"/>
              <el-table-column align="center"  property="copies" label="复印件数量"/>
              <el-table-column align="center"  property="returnedquantity" label="退件数量">
                <template slot-scope="scope">
                  <el-input
                    v-model="scope.row.returnedquantity"
                    clearable
                    size="mini"
                    placeholder="请输入"
                    @input="scope.row.returnedquantity = scope.row.returnedquantity > scope.row.remainReturned ? scope.row.remainReturned : scope.row.returnedquantity"/>
                </template>
              </el-table-column>
<!--              <el-table-column align="center"  property="remainReturned" label="剩余退件数量">-->
<!--                <template slot-scope="scope">-->
<!--                  {{ scope.row.remainReturned }}-->
<!--                </template>-->
<!--              </el-table-column>-->
            </el-table>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="收件人姓名：" prop="addressee">
              <el-input v-model="baseForm.addressee" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="退件方式：" prop="refundtype">
              <el-select v-model="baseForm.refundtype" :disabled="!showEle" class="item-width" placeholder="请选择" clearable @change="changeRefundtype">
                <!--              <el-option label="整单退件" value="01"/>-->
                <!--              <el-option label="按类型退件" value="02"/>-->
                <el-option v-for="item in dict.refund_type" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            <!-- <el-input class="item-width"  autocomplete="off"></el-input> -->
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机：" prop="telephone">
              <el-input v-model="baseForm.telephone" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="23">
            <el-form-item label="地址：" prop="address">
              <el-input v-model="baseForm.address" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="23">
            <el-form-item label="备注：" prop="remarks">
              <el-input v-model="baseForm.remarks" autocomplete="off"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-divider/>
      <el-form ref="expressFrom" :disabled="showFlag" :rules="expressFromRules" :model="expressFrom" label-width="110px" size="mini">
        <el-row class="row-box" style="margin-top: -20px;">
          <el-col :span="16">退件处理</el-col>
          <el-col :span="8" style="text-align: right;">
            <el-button size="mini" style="width: 80px;" type="primary" @click="sendSMS">发送短信</el-button>
            <el-button :disabled="true" size="mini" style="width: 80px;" type="primary">打印运单</el-button>
            <el-button size="mini" style="width: 80px;" type="primary" @click="saveHandle">处理完毕</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="运单号：" prop="expressnumber">
              <el-input v-model="expressFrom.expressnumber" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="退件日期：" prop="returneddate">
              <el-date-picker
                v-model="expressFrom.returneddate"
                style="width: 200px;"
                type="datetime"
                format="yyyy-MM-dd"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { returnProcessingDisplay, returnCompleted, returnInformationSave, sendSMS } from '@/api/return/returnProcessing.js'
import { validPhone } from '@/utils/validate'
import {decrypt} from "@/utils/rsaEncrypt"
import moment from 'moment'
export default {
  dicts: ['material_type', 'image_type', 'refund_type'],
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('不能为空！'))
      } else {
        if (value !== '' && value !== null) {
          const str = value.replace(/\s/g, '')
          if (!validPhone(str)) {
            callback(new Error('输入手机格式不正确！'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }

    }
    return {
      showFlag: false,
      showSelect: false,
      showEle: false,
      baseForm: {},
      expressFrom: {
        returneddate: ''
      },
      status: '',
      paramsList: [],
      paramsListCase: [],
      tableData: [],
      zanData: [],
      paramsarr: [],
      refuNo: '',
      hasSelectList: [],
      baseFormRules: {
        addressee: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        refundtype: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        telephone: [{ validator: checkPhone, required: true, trigger: 'blur' }],
        address: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        remarks: [{ required: true, message: '不能为空!', trigger: 'blur' }]
        // expressnumber: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        // returneddate: [{ required: true, message: '不能为空!', trigger: 'blur' }],
      },
      expressFromRules: {
        expressnumber: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        returneddate: [{ required: true, message: '不能为空!', trigger: 'blur' }]
      },
      tableFlag: false
    }
  },
  created() {
    this.querys = JSON.parse(decodeURI(this.$route.query.data))
    this.querys.claimno=this.querys.claimno? decrypt(this.querys.claimno):''
    this.status = this.querys.status
    if (this.$route.query) {
      if (this.querys.status === 'show') {
        this.showFlag = true
      } else {
        this.showFlag = this.querys.showSelect
      }
      this.showEle = this.querys.showEle ? this.querys.showEle : false
    }
    this.refuNo = this.querys.refundno ? this.querys.refundno : ''
    this.getList()
  },
  methods: {
    saveHandle() {
      this.$refs.expressFrom.validate((valid) => {
        if (valid) {
          const params = {}
          params.claimno = this.querys.claimno || ''
          params.expressnumber = this.expressFrom.expressnumber || ''
          params.refundno = this.refuNo || ''
          params.flag = this.querys.showEle ? '02' : '01'
          params.returneddate = moment(this.expressFrom.returneddate).format('YYYY-MM-DD HH:mm:ss') || ''
          returnCompleted(params).then(res => {
            if (res.status == '1') {
              this.$message.success('处理完毕！')
              this.$router.go(-1)
            } else {
              if (res.data.errMsg == '未发送短信或短信发送失败') {
                this.$message({ message: '未发送短信或短信发送失败!', type: 'warning' })
              } else {
                this.$message({ message: res.data.errMsg, type: 'error' })
              }
            }
          })
        } else {
          return false
        }
      })
    },
    saveHandleExpress() {
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          let checkFlag = false
          if (this.baseForm.refundtype === '0') {
            // this.tableData.forEach(item => {
            //   const checkResult = this.checkReturn(item.returnedquantity)
            //   if (checkResult) {
            //     checkFlag = true
            //     return
            //   }
            // })
          } else {
            this.paramsListCase.forEach(item => {
              const checkResult = this.checkReturn(item.returnedquantity)
              if (checkResult) {
                checkFlag = true
                return
              }
            })
          }
          if (checkFlag) {
            this.$message.warning('退件数量请输入大于0的整数！')
            return
          }
          if (this.paramsListCase.length === 0 && this.baseForm.refundtype !== '0' && this.querys.showEle) {
            this.$message.warning('请先选择数据！')
            return false
          }
          if (this.baseForm.refundtype === '0') {
            const flag = this.tableData.some(item => item.originals - (item.returned - item.returnedquantity) < 0)
            if (flag) {
              this.$message.warning('剩余退件数量为负数，无法整单保存！')
              return
            }
          } else {
            let typeStr = ''
            this.paramsListCase.forEach(item => {
              if (item.originals - (item.returned - item.returnedquantity) < 0) {
                typeStr += item.materialtype
              }
            })
            if (typeStr) {
               this.$message.warning('剩余退件数量为负数，无法按类型保存！')
               return
            }
          }
          if (this.querys.showEle) {
            if (this.baseForm.refundtype === '0') {
              let backFlag = false
              this.tableData.forEach(item => {
                if (item.originals != '0') {
                  backFlag = true
                }
              })
              if (!backFlag) {
                 this.$message.warning('原件数量都为0，不能保存！')
                 return
              }
              let flag = 0
              this.tableData.forEach(item => {
                if (item.originals > 0) {
                  const remainReturned = item.originals - (item.returned - item.returnedquantity)
                  if (remainReturned > 0) {
                    item.returnedquantity = remainReturned
                    flag++
                    item.flag = '01'
                  } else {
                    item.flag = '02'
                  }
                } else {
                  item.flag = '02'
                }
              })
              if (flag == 0) {
                  this.$message.warning('没有可退件的材料，不能保存！')
                  return
              }
            } else {
              let flag = false
              let backFlag = false
              this.paramsListCase.forEach(item => {
                if (item.originals == '0') {
                  flag = true
                }
                if (item.returnedquantity == '0') {
                  backFlag = true
                }
              })
              if (flag) {
                  this.$message.warning('原件数量为0，不能保存！')
                  return
              }
              if (backFlag) {
                  this.$message.warning('退件数量为0，不能保存！')
                  return
              }
              this.tableData.forEach(item => {
                this.paramsListCase.some(function(val) {
                  return val.materialtype === item.materialtype
                }) ? item.flag = '01' : item.flag = '02'
              })
            }
          } else {
            if (this.baseForm.refundtype === '0') {
              let backFlag = false
              this.tableData.forEach(item => {
                if (item.originals != '0') {
                  backFlag = true
                }
              })
              if (!backFlag) {
                  this.$message.warning('原件数量都为0，不能保存！')
                  return
              }
              let flag = 0
              this.tableData.forEach(item => {
                if (item.originals > 0) {
                  const remainReturned = item.originals - (item.returned - item.returnedquantity)
                  if (remainReturned) {
                    item.returnedquantity = remainReturned
                    flag++
                    item.flag = '01'
                  } else {
                    item.flag = '02'
                  }
                } else {
                  item.flag = '02'
                }
              })
              if (flag == 0) {
                  this.$message.warning('没有可退件的材料，不能保存！')
                  return
              }
            } else {
              let flag = false
              let backFlag = false
              this.tableData.forEach(item => {
                if (item.originals == '0') {
                  flag = true
                }
                if (item.returnedquantity == '0') {
                  backFlag = true
                }
              })
              if (flag) {
                  this.$message.warning('原件数量为0，不能保存！')
                  return
              }
              if (backFlag) {
                  this.$message.warning('退件数量为0，不能保存！')
                   return
              }
              this.tableData.forEach(item => {
                item.flag = '01'
              })
            }
          }
          const params = {}
          params.claimno = this.querys.claimno || ''
          params.refundno = this.refuNo || ''
          params.caseclosedreturnflag = this.querys.caseclosedreturnflag || ''
          params.remarks = this.baseForm.remarks || ''
          // params.materialRequestVo = (this.$route.query.showEle ? this.paramsarr : this.paramsList) || []
          params.materialRequestVo = this.tableData
          params.flag = this.querys.showEle ? '02' : '01'
          params.addressee = this.baseForm.addressee || ''
          params.refundtype = this.baseForm.refundtype || ''
          params.telephone = this.baseForm.telephone || ''
          params.address = this.baseForm.address || ''
          returnInformationSave(params).then(res => {
            if (res.status === '1') {
              this.refuNo = (res && res.data) || ''
              this.getList()
              this.$message.success('保存成功！')
            } else {
              this.$message.error(res.data.errMsg)
            }
          })
        } else {
          return false
        }
      })
    },
    getList() {
      const params = {}
      params.claimno = this.querys.claimno || ''
      params.refundno = this.refuNo || ''
      params.flag = this.querys.flag ? '02' : '01'
      params.tabno = this.querys.status === 'show' ? '01' : '02'
      if (!this.querys.showSelect) {
        delete params.tabno
      }
      returnProcessingDisplay(params).then(res => {
        if (res.status == '1') {
          this.baseForm = (res && res.data && res.data.returnOperationVO) || {}
          this.expressFrom = (res && res.data && res.data.returnOperationVO) || {}
          if (this.expressFrom.returneddate == null || this.expressFrom.returneddate == '') {
            // this.expressFrom.returneddate = moment().format('YYYY-MM-DD')
              this.$set(this.expressFrom, 'returneddate', moment().format('YYYY-MM-DD'))
          }
          this.tableData = (res && res.data && res.data.materialsVOList) || []
          this.zanData = JSON.stringify((res && res.data && res.data.materialsVOList))
          this.tableData.map((item) => {
            if (item.flag === '01') {
              this.hasSelectList.push(item.flag)
            }
            if (this.status == 'show') {
              item.remainReturned = item.originals - item.returned
            } else {
              item.remainReturned = item.originals - (item.returned - item.returnedquantity)
            }
          })
          this.$nextTick(() => {
            if (this.baseForm.refundtype == '0') {
              this.tableFlag = false
            } else {
              this.tableFlag = true
            }
            this.tableData.forEach(row => {
              if (this.hasSelectList.indexOf(row.flag) >= 0) {
                this.$refs.dataTable.toggleRowSelection(row, true)
              }
            })
          })
        } else {
          this.$message.error(res.data.errMsg)
        }
      })
    },
    sendSMS() {
      if (this.expressFrom.expressnumber !== null) {
        this.expressFrom.expressnumber = this.expressFrom.expressnumber.replace(/\s+/g, "");
        if (this.expressFrom.expressnumber == '' ) {
          this.$message.warning('运单号不能为空！')
          return false
        }
      }
      this.$refs.expressFrom.validate((valid) => {
        if (valid) {
          let arr = JSON.parse(this.zanData)
          let flag = arr.every((item) =>{
            return item.returnedquantity == 0
          })
          if (flag) {
            this.$message.warning('未保存退件任务！')
            return false
          }
          const params = {}
          params.claimno = this.querys.claimno || ''
          params.refundno = this.refuNo || ''
          params.expressnumber = this.expressFrom.expressnumber || ''
          params.returneddate = moment(this.expressFrom.returneddate).format('YYYY-MM-DD HH:mm:ss') || ''
          sendSMS(params).then(res => {
            if (res.status == '1') {
              this.$message.success('发送成功！')
            } else {
              this.$message.error(res.data.errMsg)
            }
          })
        } else {
          return false
        }
      })
    },
    goBack() {
      window.close()
      // this.$router.go(-1)
    },
    changeRefundtype(val) {
      if (this.baseForm.refundtype == '0') {
        this.tableFlag = false
      } else {
        this.tableFlag = true
      }
    },
    handleSelectionChange(val) {
      this.paramsListCase = val
    },
    editHandle() {},
    getmaterialtype(row, col) {
      return this.dict.label.image_type[row.materialtype]
    },
    checkReturn(row) {
      const str = /^\+?[1-9]\d*$/
      return !str.test(row)
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .row-box {
    padding-top: 20px;
    border-bottom: 1px solid #e6ebf5;
    margin-bottom: 20px;
    line-height: 50px;
  }
  .return_class /deep/ .el-table__empty-block {
    min-height: 45px;
  }
  .return_class /deep/ .el-table__empty-text {
    min-height: 45px;
    line-height: 45px;
  }
</style>
<style>
.return_class .el-input__inner{
  text-align: center;
  min-width:95px;
}
</style>
