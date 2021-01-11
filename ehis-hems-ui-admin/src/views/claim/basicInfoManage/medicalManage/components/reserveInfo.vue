<template>
  <el-form ref="reserveInfoForm" :rules="reserveFormRules" :model="reserveInfoForm" style="padding-bottom: 30px;" :disabled="disabledFlag"
           label-width="170px" size="mini" class="baseInfo_class">
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>预约信息</span>
      <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
        </span>
    </div>

      <el-row>
        <el-col :span="8">
          <el-form-item label="预约电话：" prop="appointmentCall">
            <el-input v-model="reserveInfoForm.appointmentCall" class="item-width" clearable maxlength="30"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="本院普通门诊部急诊信息及电话：" prop="outpatientInfo">
            <el-input v-model="reserveInfoForm.outpatientInfo" class="item-width" clearable maxlength="30"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客户咨询电话：" prop="hotline">
            <el-input v-model="reserveInfoForm.hotline" class="item-width" clearable
                      placeholder="请输入" maxlength="30"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="预约提前时间：" prop="leadTime">
            <el-checkbox-group v-model="reserveInfoForm.outpatientearly" @change="outpatientearlyChange">
              <el-checkbox label="01">住院
                <el-input v-model="reserveInfoForm.hospitalizeDay" class="item-width" clearable
                          :disabled="hospitalizedayInputShow"
                          placeholder="请输入" style="width: 150px"/>
                天
              </el-checkbox>
              <el-checkbox label="02">门诊
                <el-input v-model="reserveInfoForm.outpatientDay" class="item-width" clearable
                          :disabled="outpatientdayInputShow"
                          placeholder="请输入" style="width: 150px"/>
                天
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检确认单发送时间：" prop="confirmDeliverytime">
            <el-input v-model="reserveInfoForm.confirmDeliverytime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="法定假日营业日期：" prop="holidayTime">
            <el-input v-model="reserveInfoForm.holidayTime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="体检确认单发送方式：" prop="sendType">
            <el-checkbox-group v-model="reserveInfoForm.sendType" @change="sendTypeChange">
              <el-checkbox label="01">电话
                <el-input v-model="reserveInfoForm.phone" class="item-width" clearable :disabled="phoneInputShow "
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">邮件
                <el-input v-model="reserveInfoForm.mail" class="item-width" clearable :disabled="mailInputShow "
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="03">传真
                <el-input v-model="reserveInfoForm.fax" class="item-width" clearable :disabled="faxInputShow "
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检报告寄送方式：" prop="sendWay">
            <el-checkbox-group v-model="reserveInfoForm.sendWay" @change="sendWayChange">
              <el-checkbox label="01">快递
                <el-input v-model="reserveInfoForm.fastmailRemark" class="item-width" clearable
                          :disabled="inviteflagInputShow"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">自取
                <el-input v-model="reserveInfoForm.inviteRemark" class="item-width" clearable
                          :disabled="fastmailflagInputShow"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="直接结算手续接待处：" prop="receptionFlag">
            <el-input v-model="reserveInfoForm.receptionFlag" class="item-width" clearable maxlength="500"
                      placeholder="请输入"/>
          </el-form-item>
          <el-form-item label="医院是否配合收取自付费：" prop="selfpaymentFlag">
            <el-select v-model="reserveInfoForm.selfpaymentFlag" class="item-width" placeholder="请选择是/否" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
            <el-select v-model="reserveInfoForm.continentFlag" class="item-width" placeholder="请选择是/否" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

            <el-input v-model="reserveInfoForm.continent" class="item-width" clearable
                      style="padding-left: 15px;width: 450px" maxlength="500"
                      placeholder="请输入"/>

          </el-form-item>

        </el-col>
        <el-col :span="8">
          <el-form-item label="是否连锁机构(结算)：">
            <el-select v-model="reserveInfoForm.interlocking" class="item-width" placeholder="请选择是/否" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="门诊时间：" prop="consultingHours">

            <span>周一至周五</span>
            <el-time-picker
              v-model="reserveInfoForm.workstarttime"
              format='HH:mm'
              value-format="HH:mm"
              style="padding-left: 5px; width: 150px"
              :picker-options="{
        selectableRange:`00:00:00 -${reserveInfoForm.workendtime ? reserveInfoForm.workendtime +':00' : '23:59:00'}`
    }">
            </el-time-picker>
            <el-time-picker
              v-model="reserveInfoForm.workendtime"
              format='HH:mm'
              value-format="HH:mm"
              style="padding-left: 5px; width: 150px"
              :picker-options="{
        selectableRange:`${reserveInfoForm.workstarttime ?reserveInfoForm.workstarttime +':00' : '00:00:00'}-23:59:00`
    }">
            </el-time-picker>
            <el-input v-model="reserveInfoForm.workremark" class="item-width" clearable
                      style="padding-left: 10px;width: 350px"
                      placeholder="请输入"/>


            <div style="padding-top: 5px">
              <span style="padding-left: 40px">周六</span>
              <el-time-picker
                v-model="reserveInfoForm.saturdaystarttime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
                :picker-options="{
        selectableRange:`00:00:00 -${reserveInfoForm.saturdayendtime ? reserveInfoForm.saturdayendtime +':00' : '23:59:00'}`
    }">
              </el-time-picker>
              <el-time-picker
                v-model="reserveInfoForm.saturdayendtime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
                :picker-options="{
        selectableRange:`${reserveInfoForm.saturdaystarttime ?reserveInfoForm.saturdaystarttime +':00' : '00:00:00'}-23:59:00`
    }">
              </el-time-picker>

              <el-input v-model="reserveInfoForm.saturdayremark" class="item-width" clearable
                        style="padding-left: 10px;width: 350px"
                        placeholder="请输入"/>
            </div>
            <div style="padding-top: 5px">
              <span style="padding-left: 40px">周日</span>
              <el-time-picker
                v-model="reserveInfoForm.sundaystarttime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
                :picker-options="{
        selectableRange:`00:00:00 -${reserveInfoForm.sundayendtime ? reserveInfoForm.sundayendtime +':00' : '23:59:00'}`
    }">
              </el-time-picker>
              <el-time-picker
                v-model="reserveInfoForm.sundayendtime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
                :picker-options="{
        selectableRange:`${reserveInfoForm.sundaystarttime ?reserveInfoForm.sundaystarttime +':00' : '00:00:00'}-23:59:00`
    }">
              </el-time-picker>


              <el-input v-model="reserveInfoForm.sundayremark" class="item-width" clearable
                        style="padding-left: 10px;width: 350px"
                        placeholder="请输入"/>

            </div>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="直接结算部门医保支付情况：">
            <el-select v-model="reserveInfoForm.payment" class="item-width" placeholder="请选择是/否" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="对非直接客户是否提供门诊预约：">
            <el-radio-group v-model="reserveInfoForm.nondirectcustomer">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="急诊时间：" prop="emergencyTime">
            <span style="padding-left:5px"> 24h急诊</span>
            <el-time-picker
              v-model="reserveInfoForm.emergencycallstarttime"
              format='HH:mm'
              value-format="HH:mm"
              style="padding-left: 5px; width: 150px"
              :picker-options="{
        selectableRange:`00:00:00 -${reserveInfoForm.emergencycallendtime ? reserveInfoForm.emergencycallendtime +':00' : '23:59:00'}`
    }">
            </el-time-picker>
            <el-time-picker
              v-model="reserveInfoForm.emergencycallendtime"
              format='HH:mm'
              value-format="HH:mm"
              style="padding-left: 5px; width: 150px"
              :picker-options="{
        selectableRange:`${reserveInfoForm.emergencycallstarttime ?reserveInfoForm.emergencycallstarttime +':00' : '00:00:00'}-23:59:00`
    }">
            </el-time-picker>


            <el-input ref="hospitalname" v-model="reserveInfoForm.emergencycallremark" class="item-width" clearable
                      style="padding-left: 10px;width: 350px"
                      placeholder="请输入"/>
            <div style="padding-top: 5px">
              <span>夜间急诊</span>

              <el-time-picker
                v-model="reserveInfoForm.nightemergencystarttime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
                :picker-options="{
        selectableRange:`00:00:00 -${reserveInfoForm.nightemergencyendtime ? reserveInfoForm.nightemergencyendtime +':00' : '23:59:00'}`
    }">
              </el-time-picker>
              <el-time-picker
                v-model="reserveInfoForm.nightemergencyendtime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
                :picker-options="{
        selectableRange:`${reserveInfoForm.nightemergencystarttime ?reserveInfoForm.nightemergencystarttime +':00' : '00:00:00'}-23:59:00`
    }">
              </el-time-picker>


              <el-input ref="hospitalname" v-model="reserveInfoForm.nightemergencyremark" class="item-width"
                        clearable style="padding-left: 10px;width: 350px"
                        placeholder="请输入"/>
            </div>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否提供直接卡：">
            <el-radio-group v-model="reserveInfoForm.straightknotflag">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否可自行预约：">
            <el-radio-group v-model="reserveInfoForm.makeappointment">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否提供VIP门诊预约：">
            <el-radio-group v-model="reserveInfoForm.vipappointment">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否驻点：">
            <el-radio-group v-model="reserveInfoForm.leadFlag">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-form-item style="margin-right: 20px;" label="门诊地址：" prop="outpatientaddress">
          <el-input v-model="reserveInfoForm.outpatientaddress" clearable size="mini" placeholder="请输入"
                    maxlength="200"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="预约提示：" prop="appointreminders">
          <el-input v-model="reserveInfoForm.appointreminders" clearable size="mini" placeholder="请输入"
                    maxlength="500"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="预授权书发送方式：" prop="preauthsendway">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="reserveInfoForm.preauthsendway" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="门诊病历提供：" prop="clinicmedicalrecord" >
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="reserveInfoForm.clinicmedicalrecord" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="住院病历提供：" prop="medicalrecords">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="reserveInfoForm.medicalrecords" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="住院押金情况说明及办理流程：" prop="hospitaldeposit">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="reserveInfoForm.hospitaldeposit" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="直接结算合作部门急诊信息及电话：" prop="emergencyinfo">
          <el-input v-model="reserveInfoForm.emergencyinfo" clearable size="mini" placeholder="网络医院直结类型备注" maxlength="50"
                    />
        </el-form-item>
      </el-row>

    <el-row>
      <el-form-item style="margin-right: 20px;" label="网络医院直结类型备注：" >
        <el-input
          type="textarea"
          placeholder="请输入内容"
          v-model="reserveInfoForm.remarks" maxlength="500">
        </el-input>
      </el-form-item>
    </el-row>




  </el-card>
  </el-form>
</template>
<script>
import {getapplyInfo, addapplyInfo, updateapplyInfo} from "@/api/baseInfo/medicalManage";

export default {
  props: {
    reserveInfoForm: {
      type: Object,
      default: function (){
        return {}
      }
    },
    providerCode : String,
    disabledFlag : Boolean
  },
  data() {
    const checkSendWay = (rules, value, callback) => {
      if (!this.reserveInfoForm.inviteflag && !this.reserveInfoForm.fastmailflag) {
        callback(new Error('体检报告寄送方式不能空'))
      } else {
        callback()
      }
    }
    const checkConsultingHours = (rules, value, callback) => {
      if (!(this.reserveInfoForm.workstarttime && this.reserveInfoForm.workendtime && this.reserveInfoForm.workremark &&
        this.reserveInfoForm.saturdaystarttime && this.reserveInfoForm.workendtime && this.reserveInfoForm.saturdayremark &&
        this.reserveInfoForm.sundaystarttime && this.reserveInfoForm.sundayendtime && this.reserveInfoForm.sundayremark)) {
        callback(new Error('门诊时间不能空'))
      } else {
        callback()
      }
    }

    const checkEmergencyTime = (rules, value, callback) => {
      if (!(this.reserveInfoForm.emergencycallstarttime && this.reserveInfoForm.emergencycallendtime && this.reserveInfoForm.emergencycallremark &&
        this.reserveInfoForm.nightemergencystarttime && this.reserveInfoForm.nightemergencyendtime && this.reserveInfoForm.nightemergencyremark)) {
        callback(new Error('急诊时间不能空'))
      } else {
        callback()
      }
    }
    return {

      inviteflagInputShow: true,
      fastmailflagInputShow: true,
      hospitalizedayInputShow: true,
      outpatientdayInputShow: true,
      mailInputShow: true,
      faxInputShow: true,
      phoneInputShow: true,
      reserveFormRules: {
        confirmDeliverytime: [{required: true, message: '不能为空！', trigger: 'blur'}]
      },
    }
  },

  mounted() {

  },

  methods: {
    //inputShow
    inputShow(){
      if(this.reserveInfoForm.outpatientearly && this.reserveInfoForm.outpatientearly.length>0){
        for(let i of this.reserveInfoForm.outpatientearly){

            if (i == '01') {
              this.phoneInputShow = false;
            }
            if (i == '02') {
              this.mailInputShow = false;
            }
            if (i == '03') {
              this.faxInputShow = false;
            }

        }

      }
    },


    //预约信息保存
   saveHandle() {
     this.reserveInfoForm.providerCode=this.providerCode
      this.$refs.reserveInfoForm.validate((valid) => {
        if (valid) {
          //存在调用预约信息保存的接口
          if (this.reserveInfoForm.providerCode) {
            addapplyInfo(this.reserveInfoForm).then(res => {
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

        } else {
          return false
        }

      })
    },
    //体检确认单发送方式是否选中
    sendTypeChange() {
      if (this.reserveInfoForm.sendType.length > 0) {
        alert(2)
        let i=this.reserveInfoForm.phone
        this.phoneInputShow = true;
        this.mailInputShow = true;
        this.faxInputShow = true;
        this.reserveInfoForm.phone=''
        for (let i of this.reserveInfoForm.sendType) {
          if (i == '01') {
            this.phoneInputShow = false;
          }
          if (i == '02') {
            this.mailInputShow = false;
          }
          if (i == '03') {
            this.faxInputShow = false;
          }
        }
      } else {
        this.phoneInputShow = true;
        this.mailInputShow = true;
        this.faxInputShow = true;
      }

    },
    //预约提前时间是否选中
    outpatientearlyChange() {
      if (this.reserveInfoForm.outpatientearly.length > 0) {
        this.hospitalizedayInputShow = true;
        this.outpatientdayInputShow = true;
        for (let i of this.reserveInfoForm.outpatientearly) {
          if (i == '01') {
            this.hospitalizedayInputShow = false;
          }
          if (i == '02') {
            this.outpatientdayInputShow = false;
          }

        }
      } else {
        this.hospitalizedayInputShow = true;
        this.outpatientdayInputShow = true
      }
    },
    //体检报告寄送方式
    sendWayChange() {
      if (this.reserveInfoForm.sendWay.length > 0) {
        this.inviteflagInputShow = true;
        this.fastmailflagInputShow = true;
        for (let i of this.reserveInfoForm.sendWay) {
          if (i == '01') {
            this.inviteflagInputShow = false;
          }
          if (i == '02') {
            this.fastmailflagInputShow = false;
          }

        }
      } else {
        this.inviteflagInputShow = true;
        this.fastmailflagInputShow = true
      }
    },

    resetFrom(){
      this.reserveInfoForm.outpatientearly=[]
     this.$refs.reserveInfoForm.resetFields()

    },
    // 校验数据
    validateForm () {
      let flag = null
      this.$refs['reserveInfoForm'].validate(valid => {
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
