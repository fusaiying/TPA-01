<template>
  <el-form ref="otherReserveInfoForm" :rules="otherReserveFormRules" :model="otherReserveInfoForm"
           style="padding-bottom: 30px;"
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
          <el-form-item label="客户咨询电话：" prop="hotline">
            <el-input v-model="otherReserveInfoForm.hotline" class="item-width" clearable
                      placeholder="请输入" maxlength="30"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="预约提前时间：" prop="leadTime">
            <el-input v-model="otherReserveInfoForm.leadTime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="预约电话：" prop="appointmentCall">
            <el-input v-model="otherReserveInfoForm.appointmentCall" class="item-width" clearable maxlength="30"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="平安服务联系人及联系方式：" prop="contactInfo">
            <el-input v-model="otherReserveInfoForm.contactInfo" class="item-width" clearable maxlength="50"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="医疗机构服务联系人及联系方式：" prop="medicalContactInfo">
            <el-input v-model="otherReserveInfoForm.medicalContactInfo" class="item-width" clearable maxlength="50"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="法定假日营业日期：" prop="holidayTime">
            <el-input v-model="otherReserveInfoForm.holidayTime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="体检确认单发送方式：" prop="sendType">
            <el-checkbox-group v-model="otherReserveInfoForm.sendType" @change="sendTypeChange">
              <el-checkbox label="01" >电话
                <el-input v-model="otherReserveInfoForm.phone" class="item-width" clearable :disabled="phoneInputShow "
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">邮件
                <el-input v-model="otherReserveInfoForm.mail" class="item-width" clearable :disabled="mailInputShow "
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="03">传真
                <el-input v-model="otherReserveInfoForm.fax" class="item-width" clearable :disabled="faxInputShow "
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检报告寄送方式：" prop="sendWay">
            <el-checkbox-group v-model="otherReserveInfoForm.sendWay" @change="sendWayChange">
              <el-checkbox label="01" >快递
                <el-input v-model="otherReserveInfoForm.fastmailRemark" class="item-width" clearable
                          :disabled="inviteflagInputShow"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">自取
                <el-input v-model="otherReserveInfoForm.inviteRemark" class="item-width" clearable
                          :disabled="fastmailflagInputShow"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检确认单发送时间：" prop="confirmDeliverytime">
            <el-input v-model="otherReserveInfoForm.confirmDeliverytime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="是否提供直接卡：">
            <el-radio-group v-model="otherReserveInfoForm.straightknotflag">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="导检/赔检/驻点：">
            <el-select v-model="otherReserveInfoForm.stagnationPoint" class="item-width" placeholder="选择驻点才有驻点" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否对所有保单开放：">
            <el-radio-group v-model="otherReserveInfoForm.policiesOpen">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="对非直接客户是否提供预约：">
            <el-radio-group v-model="otherReserveInfoForm.nondirect">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否可自行预约：">
            <el-radio-group v-model="otherReserveInfoForm.makeappointment">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否提供VIP门诊预约：">
            <el-radio-group v-model="otherReserveInfoForm.vipappointment">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="驻点人员姓名：" prop="stationaryPointName">
            <el-input v-model="otherReserveInfoForm.stationaryPointName" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="驻点人员联系电话：" prop="stationaryPointPhone">
            <el-input v-model="otherReserveInfoForm.stationaryPointPhone" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="驻点人员工作时间：" prop="stationaryPointWorkTime">
            <el-input v-model="otherReserveInfoForm.stationaryPointWorkTime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="地址：" prop="outpatientaddress">
          <el-input v-model="otherReserveInfoForm.outpatientaddress" clearable size="mini" placeholder="请输入"
                    maxlength="200"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="预约提示：" prop="appointreminders">
          <el-input v-model="otherReserveInfoForm.appointreminders" clearable size="mini" placeholder="请输入"
          />
        </el-form-item>
      </el-row>

      <el-row>
        <el-form-item style="margin-right: 20px;" label="门诊病历提供：" prop="clinicmedicalrecord">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherReserveInfoForm.clinicmedicalrecord">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="住院病历提供：">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherReserveInfoForm.medicalrecords">
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
    providerCode: String,
    status: String
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
      otherReserveInfoForm: {
        outpatientearly: [],
        sendType: [],
        sendWay: []
      },
      inviteflagInputShow: true,
      fastmailflagInputShow: true,
      hospitalizedayInputShow: true,
      outpatientdayInputShow: true,
      mailInputShow: true,
      faxInputShow: true,
      phoneInputShow: true,
      otherReserveFormRules: {
        appointmentCall: [{required: true, message: '不能为空！', trigger: 'blur'}],
        contactInfo: [{required: true, message: '不能为空！', trigger: 'blur'}],
        medicalContactInfo: [{required: true, message: '不能为空！', trigger: 'blur'}],
        sendWay: [{required: true, message: '不能为空！', trigger: 'blur'}],
        stationaryPointName: [{required: true, message: '不能为空！', trigger: 'blur'}],
        stationaryPointPhone: [{required: true, message: '不能为空！', trigger: 'blur'}],
        stationaryPointWorkTime: [{required: true, message: '不能为空！', trigger: 'blur'}],
      },
    }
  },

  mounted() {
      this.initData()
  },

  methods: {
    initData(){
      if(this.providerCode && this.status=='02'){

      }
    },
    saveHandle(){},



    //体检确认单发送方式是否选中
    sendTypeChange() {
      if (this.otherReserveInfoForm.sendType.length > 0) {
        /*let i=this.otherReserveInfoForm.phone
        alert(i)
        this.otherReserveInfoForm.phone=''*/
        this.phoneInputShow = true;
        this.mailInputShow = true;
        this.faxInputShow = true;

        for (let i of this.otherReserveInfoForm.sendType) {
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
      if (this.otherReserveInfoForm.outpatientearly.length > 0) {
        this.hospitalizedayInputShow = true;
        this.outpatientdayInputShow = true;
        for (let i of this.otherReserveInfoForm.outpatientearly) {
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
      if (this.otherReserveInfoForm.sendWay.length > 0) {
        this.inviteflagInputShow = true;
        this.fastmailflagInputShow = true;
        for (let i of this.otherReserveInfoForm.sendWay) {
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

    resetFrom() {
      this.otherReserveInfoForm.outpatientearly = []
      this.otherReserveInfoForm.sendType = []
      this.otherReserveInfoForm.sendWay = []

      this.$refs.otherReserveInfoForm.resetFields()
    },
    // 校验数据
    validateForm() {
      let flag = null
      this.$refs['otherReserveInfoForm'].validate(valid => {
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
