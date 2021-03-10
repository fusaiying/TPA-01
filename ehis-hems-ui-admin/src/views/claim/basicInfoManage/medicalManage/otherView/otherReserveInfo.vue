<template>
  <el-form ref="otherReserveInfoForm"  :model="otherReserveInfoForm" :disabled="disabledFlag"
           label-width="200px" size="mini" class="baseInfo_class">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>预约信息</span>
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




      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="体检确认单发送方式：" prop="sendType">
            <el-checkbox-group v-model="otherReserveInfoForm.sendType" @change="sendTypeChange">
              <el-checkbox label="01" >电话
                <el-input v-model="otherReserveInfoForm.phone" class="item-width" clearable :disabled="phoneInputShow " @input="change"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">邮件
                <el-input v-model="otherReserveInfoForm.mail" class="item-width" clearable :disabled="mailInputShow " @input="change"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="03">传真
                <el-input v-model="otherReserveInfoForm.fax" class="item-width" clearable :disabled="faxInputShow " @input="change"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检报告寄送方式：" prop="sendWay">
            <el-checkbox-group v-model="otherReserveInfoForm.sendWay" @change="sendWayChange">
              <el-checkbox label="01" >快递
                <el-input v-model="otherReserveInfoForm.fastmailRemark" class="item-width" clearable @input="change"
                          :disabled="fastmailflagInputShow"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
              <el-checkbox label="02">自取
                <el-input v-model="otherReserveInfoForm.inviteRemark" class="item-width" clearable @input="change"
                          :disabled="inviteflagInputShow"
                          placeholder="请输入" style="width: 150px"/>
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="法定营业日期：" prop="holidayTime">
            <el-input v-model="otherReserveInfoForm.holidayTime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="咨询电话：" prop="hotline2">
            <el-input v-model="otherReserveInfoForm.hotline2" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体检确认单发送时间：" prop="confirmDeliverytime">
            <el-input v-model="otherReserveInfoForm.confirmDeliverytime" class="item-width" clearable
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="是否提供直结卡：" prop="straightknotflag">
            <el-radio-group v-model="otherReserveInfoForm.straightknotflag">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="导检/陪检/驻点：" prop="leadFlag">
            <el-radio-group v-model="otherReserveInfoForm.leadFlag">
              <el-radio label="01">导检</el-radio>
              <el-radio label="02">陪检</el-radio>
              <el-radio label="03">驻点</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否对所有保单开放：" prop="policiesOpen">
            <el-radio-group v-model="otherReserveInfoForm.policiesOpen">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="对非直接客户是否提供预约：" prop="nondirect">
            <el-radio-group v-model="otherReserveInfoForm.nondirect">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否可自行预约：" prop="makeappointment">
            <el-radio-group v-model="otherReserveInfoForm.makeappointment">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否提供VIP门诊预约：" prop="vipappointment">
            <el-radio-group v-model="otherReserveInfoForm.vipappointment">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>

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
        <el-form-item style="margin-right: 20px;" label="住院病历提供：" prop="medicalrecords">
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
let dictss = [{dictType: 'inter_hosp'},{dictType: 'virtual_org'},{dictType: 'first_attribute'},{dictType: 'second_attribute_a'},
  {dictType: 'second_attribute_b'},{dictType: 'comprehensive_type'},{dictType: 'yes_or_no'},{dictType: 'visiting_type'},
  {dictType: 'spprocurement_flag'},{dictType: 'classification'},{dictType: 'cooperationStatus_flag'},{dictType: 'servicelocator_flag'},
  {dictType: 'speciallanguage'},{dictType: 'networkHospitalType'},{dictType: 'type'},{dictType: 'payment'},
  {dictType: 'classification_type'},{dictType: 'leadFlag'},{dictType: 'place_type'},{dictType: 'currency'},
  {dictType: 'account_type_new'}]
export default {
  props: {
    otherReserveInfoForm: {
      type: Object,
      default: function (){
        return {}
      }
    },
    providerCode: String,
    status: String,
    disabledFlag: Boolean
  },


  data() {
    return {

      inviteflagInputShow: true,
      fastmailflagInputShow: true,
      hospitalizedayInputShow: true,
      outpatientdayInputShow: true,
      mailInputShow: true,
      faxInputShow: true,
      phoneInputShow: true,
    }
  },

  mounted() {

  },

  methods: {


//强制刷新
    change(){
      this.$forceUpdate()
    },

    //体检确认单发送方式是否选中
    sendTypeChange() {
      if (this.otherReserveInfoForm.sendType.length > 0) {
        if(!this.otherReserveInfoForm.sendType.find(item => {
          return item=='01'
        })){
          this.otherReserveInfoForm.phone=''
          this.phoneInputShow = true
        }else {
          this.phoneInputShow = false;
        }
        if(!this.otherReserveInfoForm.sendType.find(item => {
          return item=='02'
        })){
          this.otherReserveInfoForm.mail=''
          this.mailInputShow = true;
        }else {
          this.mailInputShow = false;
        }
        if(!this.otherReserveInfoForm.sendType.find(item => {
          return item=='03'
        })){
          this.otherReserveInfoForm.fax=''
          this.faxInputShow = true;
        }else {
          this.faxInputShow = false;
        }
      } else {
        this.otherReserveInfoForm.fax=''
        this.otherReserveInfoForm.mail=''
        this.otherReserveInfoForm.phone=''
        this.phoneInputShow = true;
        this.mailInputShow = true;
        this.faxInputShow = true;
      }

    },

    //体检报告寄送方式
    sendWayChange() {
      if (this.otherReserveInfoForm.sendWay.length > 0) {
        if(!this.otherReserveInfoForm.sendWay.find(item => {
          return item=='01'
        })){
          this.otherReserveInfoForm.fastmailRemark=''
          this.fastmailflagInputShow = true
        }else {
          this.fastmailflagInputShow = false;
        }

        if(!this.otherReserveInfoForm.sendWay.find(item => {
          return item=='02'
        })){
          this.otherReserveInfoForm.inviteRemark=''
          this.inviteflagInputShow = true
        }else {
          this.inviteflagInputShow = false;
        }
      } else {
        this.otherReserveInfoForm.fastmailRemark=''
        this.otherReserveInfoForm.inviteRemark=''
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
