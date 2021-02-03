<template>
  <el-form ref="reserveInfoForm"  :model="reserveInfoForm"
           :disabled="disabledFlag"
           label-width="200px" size="mini" class="baseInfo_class">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>预约信息</span>
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
          <el-form-item label="预约提前时间：" prop="outpatientearly">
            <el-checkbox-group v-model="reserveInfoForm.outpatientearly" @change="outpatientearlyChange">
              <el-checkbox label="01">
                住院
                <el-input v-model="reserveInfoForm.hospitalizeDay" class="item-width" clearable @input="change"
                          :disabled="hospitalizedayInputShow"
                          placeholder="请输入" style="width: 150px"/>
                天

              </el-checkbox>
              <el-checkbox label="02">
                门诊
                <el-input v-model="reserveInfoForm.outpatientDay" class="item-width" clearable @input="change"
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
                          @input="change"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">邮件
                <el-input v-model="reserveInfoForm.mail" class="item-width" clearable :disabled="mailInputShow "
                          @input="change"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="03">传真
                <el-input v-model="reserveInfoForm.fax" class="item-width" clearable :disabled="faxInputShow "
                          @input="change"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检报告寄送方式：" prop="sendWay">
            <el-checkbox-group v-model="reserveInfoForm.sendWay" @change="sendWayChange">
              <el-checkbox label="01">

                快递
                <el-input v-model="reserveInfoForm.fastmailRemark" class="item-width" clearable
                          :disabled="fastmailflagInputShow" @input="change"
                          placeholder="请输入" style="width: 150px"/>

              </el-checkbox>
              <el-checkbox label="02">

                自取
                <el-input v-model="reserveInfoForm.inviteRemark" class="item-width" clearable
                          :disabled="inviteflagInputShow" @input="change"
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
            <el-select v-model="reserveInfoForm.selfpaymentFlag" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>

          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">

          <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
            <el-select v-model="reserveInfoForm.continentFlag" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>

            <el-input v-model="reserveInfoForm.continent" class="item-width" clearable
                      style="width: 60%" maxlength="500"
                      placeholder="请输入"/>
          </el-form-item>

          <!--          <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
                      <el-select v-model="reserveInfoForm.continentFlag" class="item-width" placeholder="请选择" clearable>
                        <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                                   :key="item.dictValue"/>
                      </el-select>
                      <el-input v-model="reserveInfoForm.continent" class="item-width" clearable
                                style="width: 60%" maxlength="500"
                                placeholder="请输入"/>
                    </el-form-item>
                    -->

        </el-col>
        <el-col :span="8">
          <el-form-item label="是否连锁机构(结算)：">
            <el-select v-model="reserveInfoForm.interlocking" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in yes_or_noOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
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
            >

            </el-time-picker>


            <el-time-picker
              v-model="reserveInfoForm.workendtime"
              format='HH:mm'
              value-format="HH:mm"
              style="padding-left: 5px; width: 150px"
            >
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
              >
              </el-time-picker>
              <el-time-picker
                v-model="reserveInfoForm.saturdayendtime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
              >
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
              >
              </el-time-picker>
              <el-time-picker
                v-model="reserveInfoForm.sundayendtime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
              >
              </el-time-picker>


              <el-input v-model="reserveInfoForm.sundayremark" class="item-width" clearable
                        style="padding-left: 10px;width: 350px"
                        placeholder="请输入"/>

            </div>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="直接结算部门医保支付情况：">
            <el-select v-model="reserveInfoForm.payment" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in paymentOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>

            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="对非直结客户是否提供门诊预约：">
            <el-radio-group v-model="reserveInfoForm.nondirectcustomer">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
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
            >
            </el-time-picker>
            <el-time-picker
              v-model="reserveInfoForm.emergencycallendtime"
              format='HH:mm'
              value-format="HH:mm"
              style="padding-left: 5px; width: 150px"
            >
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
              >
              </el-time-picker>
              <el-time-picker
                v-model="reserveInfoForm.nightemergencyendtime"
                format='HH:mm'
                value-format="HH:mm"
                style="padding-left: 5px; width: 150px"
              >
              </el-time-picker>


              <el-input ref="hospitalname" v-model="reserveInfoForm.nightemergencyremark" class="item-width"
                        clearable style="padding-left: 10px;width: 350px"
                        placeholder="请输入"/>
            </div>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否提供直结卡：">
            <el-radio-group v-model="reserveInfoForm.straightknotflag">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否可自行预约：">
            <el-radio-group v-model="reserveInfoForm.makeappointment">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否提供VIP门诊预约：">
            <el-radio-group v-model="reserveInfoForm.vipappointment">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-form-item style="margin-right: 20px;" label="门诊地址：" prop="outpatientAddress">
          <el-input v-model="reserveInfoForm.outpatientAddress" clearable size="mini" placeholder="请输入"
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
        <el-form-item style="margin-right: 20px;" label="预授权书发送及确认方式：" prop="preauthsendway">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="reserveInfoForm.preauthsendway" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="门诊病历提供：" prop="clinicmedicalrecord">
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
          <el-input type="textarea" v-model="reserveInfoForm.emergencyinfo" placeholder="网络医院直结类型备注" maxlength="50"
          />
        </el-form-item>
      </el-row>

      <el-row>
        <el-form-item style="margin-right: 20px;" label="网络医院直结类型备注：">
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
import {getapplyInfo, addapplyInfo, updateapplyInfo, getNewtworktypeList} from "@/api/baseInfo/medicalManage";

let dictss = [{dictType: 'inter_hosp'}, {dictType: 'virtual_org'}, {dictType: 'first_attribute'}, {dictType: 'second_attribute_a'},
  {dictType: 'second_attribute_b'}, {dictType: 'comprehensive_type'}, {dictType: 'yes_or_no'}, {dictType: 'visiting_type'},
  {dictType: 'spprocurement_flag'}, {dictType: 'classification'}, {dictType: 'cooperationStatus_flag'}, {dictType: 'servicelocator_flag'},
  {dictType: 'speciallanguage'}, {dictType: 'networkHospitalType'}, {dictType: 'type'}, {dictType: 'payment'},
  {dictType: 'classification_type'}, {dictType: 'leadFlag'}, {dictType: 'place_type'}, {dictType: 'currency'},{dictType: 'payment'},
  {dictType: 'account_type_new'}]
export default {
  props: {
    reserveInfoForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    providerCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean
  },
  data() {

    return {
      isNetHospFlag: false,
      inviteflagInputShow: true,
      fastmailflagInputShow: true,
      hospitalizedayInputShow: true,
      outpatientdayInputShow: true,
      mailInputShow: true,
      faxInputShow: true,
      phoneInputShow: true,

      medicalTypeData:[],
      yes_or_noOptions: [],
      leadFlagOptions: [],
      dictList: [],
      paymentOptions: []
    }
  },
  watch: {
    providerCode: function (newVal){
      this.getNewtworktype()
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.yes_or_noOptions = this.dictList.find(item => {
      return item.dictType == 'yes_or_no'
    }).dictDate
    this.leadFlagOptions = this.dictList.find(item => {
      return item.dictType == 'leadFlag'
    }).dictDate
    this.paymentOptions = this.dictList.find(item => {
      return item.dictType == 'payment'
    }).dictDate
    this.sendWayChange()
    this.sendTypeChange()
    this.outpatientearlyChange()


  },


  methods: {
    // 调用查询维护的方法
    getNewtworktype() {
      let query={
        providerCode: this.providerCode
      }
      getNewtworktypeList(query).then(res => {
        this.medicalTypeData = res.rows
        if (this.medicalTypeData.filter(item => {
          return item.networktypeName == '网络医院'
        })[0].oldChoose == '02') {
          this.isNetHospFlag = true
        }
      }).catch(res => {

      })
    },

    //强制刷新
    change() {
      this.$forceUpdate()
    },



    //体检确认单发送方式是否选中
    sendTypeChange() {
      if (this.reserveInfoForm.sendType.length > 0) {
        if (!this.reserveInfoForm.sendType.find(item => {
          return item == '01'
        })) {
          this.reserveInfoForm.phone = ''
          this.phoneInputShow = true
        } else {
          this.phoneInputShow = false;
        }
        if (!this.reserveInfoForm.sendType.find(item => {
          return item == '02'
        })) {
          this.reserveInfoForm.mail = ''
          this.mailInputShow = true;
        } else {
          this.mailInputShow = false;
        }
        if (!this.reserveInfoForm.sendType.find(item => {
          return item == '03'
        })) {
          this.reserveInfoForm.fax = ''
          this.faxInputShow = true;
        } else {
          this.faxInputShow = false;
        }
      } else {
        this.reserveInfoForm.fax = ''
        this.reserveInfoForm.mail = ''
        this.reserveInfoForm.phone = ''
        this.phoneInputShow = true;
        this.mailInputShow = true;
        this.faxInputShow = true;
      }

    },
    //预约提前时间是否选中
    outpatientearlyChange() {
      if (this.reserveInfoForm.outpatientearly.length > 0) {
        if (!this.reserveInfoForm.outpatientearly.find(item => {
          return item == '01'
        })) {
          this.reserveInfoForm.hospitalizeDay = ''
          this.hospitalizedayInputShow = true
        } else {
          this.hospitalizedayInputShow = false;
        }
        if (!this.reserveInfoForm.outpatientearly.find(item => {
          return item == '02'
        })) {
          this.reserveInfoForm.outpatientDay = ''
          this.outpatientdayInputShow = true
        } else {
          this.outpatientdayInputShow = false;
        }

      } else {
        this.reserveInfoForm.hospitalizeDay = ''
        this.reserveInfoForm.outpatientDay = ''
        this.hospitalizedayInputShow = true;
        this.outpatientdayInputShow = true
      }
    },
    //体检报告寄送方式
    sendWayChange() {
      if (this.reserveInfoForm.sendWay.length > 0) {
        if (!this.reserveInfoForm.sendWay.find(item => {
          return item == '01'
        })) {
          this.reserveInfoForm.fastmailRemark = ''
          this.fastmailflagInputShow = true
        } else {
          this.fastmailflagInputShow = false;
        }

        if (!this.reserveInfoForm.sendWay.find(item => {
          return item == '02'
        })) {
          this.reserveInfoForm.inviteRemark = ''
          this.inviteflagInputShow = true
        } else {
          this.inviteflagInputShow = false;
        }
      } else {
        this.reserveInfoForm.fastmailRemark = ''
        this.reserveInfoForm.inviteRemark = ''
        this.inviteflagInputShow = true;
        this.fastmailflagInputShow = true
      }
    },




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
