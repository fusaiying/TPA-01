<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider></el-divider>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="150px"
               label-position="right" size="mini">
        <el-row>
          <!--clearable是清楚输入框内容 readly、只读不可以编辑 ；不可以共存-->
          <el-col :span="8">
            <el-form-item label="保单号：" prop="Service"  >
              <el-input readonly v-model="sendForm.acceptor" class="item-width"  size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel" readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人出生日期:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件类型:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保益生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保益满期日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="主招揽业务员电话:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="是否UHCG会员:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="AM(服务经理):"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="BD(销售经理):"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="特定医院赔付比例:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="150px"
               label-position="right" size="mini" >

        <span style="color: blue">服务受理信息</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：">
              <el-input v-model="ruleForm.workOrderNo" class="item-width" clearable size="mini" disabled placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="受理渠道" prop="channelCode">
            <el-row>
              <el-radio-group v-model="ruleForm.channelCode">
                <el-radio
                  v-for="dict in cs_channel"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-row>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="ruleForm.callCenterId" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="callPerson.name">
              <el-input v-model="ruleForm.callPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="ruleForm.callPerson.mobilePhone" class="item-width" maxlength="15" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="ruleForm.priorityLevel" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人与申请人关系：" prop="callRelationBy">
              <el-select v-model="ruleForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="门诊直接结算服务项目：" prop="outpatientSettlement">
              <el-select v-model="ruleForm.outpatientSettlement" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_direct_settlement" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="ruleForm.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊类型：" prop="visitType">
              <el-select v-model="ruleForm.visitType" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_consultation_type" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="disease">
              <el-input v-model="ruleForm.disease" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="8">
            <el-form-item label="症状或体征：" prop="symptomsSigns">
              <el-input v-model="ruleForm.symptomsSigns" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="a">
              <el-input v-model="ruleForm.a" style="width: 90px" clearable size="mini" maxlength="4" placeholder="请输入" />
              <el-select v-model="ruleForm.b" style="width: 90px" placeholder="请选择"  >
                <el-option v-for="item in cs_time_unit" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否意外：" prop="accidentFlag">
              <el-select v-model="ruleForm.accidentFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="意外原因：" prop="accidentReason">
              <el-input v-model="ruleForm.accidentReason" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input v-model="ruleForm.contactsPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人电话：" prop="contactsPerson.mobilePhone">
              <el-input v-model="ruleForm.contactsPerson.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-select v-model="ruleForm.contactsPerson.sex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="validCertificate">
              <el-select v-model="ruleForm.validCertificate" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

          <el-col :span="8">
            <el-form-item label="是否持有直结卡：" prop="settlementCard">
              <el-select v-model="ruleForm.settlementCard" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约日期："  style="white-space: nowrap" prop="appointmentDate">
              <el-date-picker class="item-width"
                v-model="ruleForm.appointmentDate"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约时间："  style="white-space: nowrap" prop="complaintTimes">
              <el-time-picker class="item-width"
                is-range
                v-model="ruleForm.complaintTimes"
                range-separator="-"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                value-format="HH:mm:ss">
              </el-time-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预计住院天数：" prop="hospitalDays">
              <el-input v-model="ruleForm.hospitalDays" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="预约医院：" style="white-space: nowrap;" :inline="true" prop="province">
              <el-cascader
                v-model="region"
                :options="regions"
                class="item-width"
                placeholder="请选择"
                :props="{ checkStrictly: true }"
                @change="handleChange"
                clearable/>
            </el-form-item>
            <!--<el-form-item label="预约医院：" style="white-space: nowrap;" :inline="true" prop="province">
              省份：<el-input v-model="ruleForm.province" style="width: 50px"  />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="city" >
              城市：<el-input v-model="ruleForm.city" style="width: 50px" />
            </el-form-item>-->
          </el-col>
        <el-col :span="8">
            <el-form-item label="医疗机构：" prop="medicalInstitution" >
              <el-col :span="16">
                <!--  <el-input v-model="ruleForm.medicalInstitution" input-w clearable size="mini" placeholder="请输入"/>-->
                <el-input v-model="ruleForm.hospitalName" input-w clearable size="mini" disabled/>
              </el-col>
              <el-button type="primary" @click="openHospitalDialog">查询</el-button>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="科室：" prop="department">
              <el-select v-if="departmentOption.length>0" v-model="ruleForm.department" class="item-width" placeholder="请选择">
                <el-option v-for="item in departmentOption" :key="item" :label="item"
                           :value="item"/>
              </el-select>
              <el-input v-else v-model="ruleForm.department" class="item-width" clearable size="mini" maxlength="20" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="床位：" prop="bunk">
              <el-input v-model="ruleForm.bunk" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院地址：" prop="hospitalAddress">
              <el-input v-model="ruleForm.hospitalAddress" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例%：" prop="compensationRatio">
              <el-input v-model="ruleForm.compensationRatio" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院电话：" prop="hospitalWorkCall">
              <el-input v-model="ruleForm.hospitalWorkCall" class="item-width" maxlength="20" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="ruleForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="content">
            <el-input
              type="textarea"
              :rows="3"
              maxlength="2000"
              v-model="ruleForm.content">
            </el-input>
          </el-form-item>
        </el-row>
        <div style="text-align: right; margin-right: 8px;">
          <el-button type="primary" size="mini" @click="submit" :disabled="this.checkSubmitFlag=='01'">提交</el-button>
          <el-button type="primary" size="mini" @click="hiddenShow">关闭</el-button>
        </div>

      </el-form>
      <hospital :value="hospitalDialog" @closeHospital="closeHospital" :queryData="queryData"
                @getPropData="getPropData"/>
    </el-card>





  </div>
</template>

<script>
import moment from 'moment'
import {demandListAndPublicPool,demandListAndPersonalPool,addInsert} from '@/api/customService/demand'
import upLoad from "../common/modul/upload";
import {addReservationInsert} from '@/api/customService/reservation'
import Hospital from "../hospital/hospital";
import {getAddress} from "@/api/baseInfo/medicalManage";
let dictss = [
  {dictType: 'cs_channel'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_sex'},
  {dictType: 'cs_communication_language'},
  {dictType: 'cs_identity'},
  {dictType: 'cs_whether_flag'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_relation'},
  {dictType: 'cs_feedback_type'},
  {dictType: 'cs_end_case'},
  {dictType: 'cs_direct_settlement'},
  {dictType: 'cs_consultation_type'},
  {dictType: 'cs_time_unit'},
]
export default {
  components: { upLoad,Hospital },
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  data() {
    const checkComplaintTime= (rule, value, callback) => {
      let tDate = moment(new Date()).format('yyyy-MM-DD');
      if(tDate > value){
        callback(new Error("预约日期不能早于当前日期"));
      }else{
        callback();
      }
    };

    //意外原因
    const checkAccidentReason = (rule, value, callback) => {
      if (this.ruleForm.accidentFlag === '01') {
        if (!value) {
          callback(new Error("意外原因必填"));
        } else {
          callback();
        }
      } else {
        callback();
      }
    };

    //床位
    const checkBunk = (rule, value, callback) => {
      if (this.ruleForm.visitType === '02') {
        if (!value) {
          callback(new Error("床位必填"));
        } else {
          callback();
        }
      } else {
        callback();
      }
    };
    return {
      regions: [], // 省市区下拉选项
      region: [], // 省市区下拉选项
      cs_channel:[],//受理渠道
      queryData:{
        region:[],
        hospitalName:''
      },//受理渠道
      cs_reservation_item:[],//服务项目
      cs_sex:[],//性别
      cs_priority:[],//优先级
      cs_communication_language:[],//语言
      cs_organization:[],//机构
      cs_handle_state:[],// 状态：
      dictList: [],
      cs_identity: [],
      cs_whether_flag: [],
      cs_relation: [],
      cs_feedback_type: [],
      cs_end_case: [],
      cs_direct_settlement: [],
      cs_consultation_type: [],
      cs_time_unit: [],
      departmentOption: [],
      //需要填入数据的部分
      ruleForm:{
        workOrderNo:"",
        hospitalName:"",
        complaintTime:"",
        hospitalWorkCall:"",
        hospitalAddress:"",
        channelCode:"03",//受理渠道
        callCenterId:"",//中心

        callPerson:{
          name:"",
          mobilePhone:"",
        },
        callMobilePhone:"",//来电人电话
        priorityLevel:"",//优先级

        callName:"",//来电人
        callRelationBy:"",//来电人与被保人关系
        outpatientSettlement:"",
        contactsPerson:{
          language:"",
          name:"",
          mobilePhone:"",
          sex:"",
        },
        province:"",
        city:"",
        visitType:"",
        disease:"",
        symptomsSigns:"",
        symptomTimes:"",
        accidentFlag:"",
        accidentReason:"",
        validCertificate:"",
        settlementCard:"",
        hospitalDays:"",
        medicalInstitution:"",
        department:"",
        bunk:"",
        compensationRatio:"",
        contactsRelationBy: "",//联系人与别抱人关系
        contactsLanguage: "",//联系人语言
        contactsMobilePhone: "",//联系人电话
        email:"",//邮件
        organCode:"",//出单机构
        a:"",
        b:"",
        checkSubmitFlag:'',
      },
      checkSubmitFlag:'',
      // 表单校验根据Form 组件提供了表单验证的功能，只需要通过 rules 属性传入约定的验证规则，并将 Form-Item 的 prop 属性设置为需校验的字段名即可
      rules: {
        channelCode: [
          {required: true, message: "受理渠道不能为空",  trigger: ["blur","change"]}
        ],
        'callPerson.name': [
          {required: true, message: "来电人不能为空",  trigger: ["blur","change"]}
        ],
        'callPerson.mobilePhone': [
          {required: false,
            message: "来电号码只能录入数字",
            pattern: /^\d*$/,
            trigger: ['change','blur']}
        ],
        priorityLevel: [
          {required: true, message: "优先级不能为空",  trigger: ["blur","change"]}
        ],
        callRelationBy: [
          {required: true, message: "来电人与申请人关系不能为空",  trigger: ["blur","change"]}
        ],
        outpatientSettlement: [
          {required: true, message: "门诊直接结算服务项目", trigger: ["blur","change"]}
        ],
        'contactsPerson.language': [
          {required: true, message: "联系人语言不能为空", trigger: ["blur","change"]}
        ],
        visitType: [
          {required: true, message: "就诊类型不能为空", trigger: ["blur","change"]}
        ],
        symptomsSigns: [
          {required: true, message: "症状或体征不能为空", trigger: ["blur","change"]}
        ],
        a: [
          {required: true, message: "本次疾病/症状起病时间不能为空", trigger: ["blur","change"]},
          {required: true,
            message: "本次疾病/症状起病时间只能录入数字",
            pattern: /^\d*$/,
            trigger: ['change','blur']}
        ],
        accidentFlag: [
          {required: true, message: "是否意外不能为空", trigger: ["blur","change"]}
        ],
        accidentReason: [
          {required: false, validator: checkAccidentReason, trigger: ["blur","change"]}
        ],
        bunk: [
          {required: false, validator: checkBunk, trigger: ["blur","change"]}
        ],
        'contactsPerson.name': [
          {required: true, message: "联系人姓名不能为空", trigger: ["blur","change"]}
        ],
        'contactsPerson.mobilePhone': [
          {required: true, message: "联系人电话不能为空", trigger: ["blur","change"]},
          {
            required: true,
            message: "目前只支持中国大陆的手机号码",
            pattern: /^1[34578]\d{9}$/,//可以写正则表达式呦呦呦,
            trigger: "blur"
          },
        ],
        'contactsPerson.sex': [
          {required: true, message: "联系人性别不能为空", trigger: ["blur","change"]}
        ],
        validCertificate: [
          {required: true, message: "是否持有有效证件不能为空", trigger: ["blur","change"]}
        ],
        settlementCard: [
          {required: true, message: "是否持有直结卡不能为空", trigger: ["blur","change"]}
        ],
        hospitalDays: [
          {required: true,
            message: "预计住院天数只能录入0-1000的数字",
            pattern: /^(([1-9]\d{1,2})|\d|1000)$/,
            trigger: ['change','blur']
          }
        ],
        medicalInstitution: [
          {required: true, message: "医疗机构不能为空", trigger: ["blur","change"]}
        ],
        appointmentDate: [
          {required: true, message: "预约日期不能为空", trigger: ["blur","change"]},
          {required: true, validator: checkComplaintTime, trigger: "blur"}
        ],
        complaintTimes: [
          {required: true, message: "预约时间不能为空", trigger: ["blur","change"]}
        ],
        province: [
          {required: true, message: "预约医院不能为空", trigger: ["blur","change"]}
        ],
        compensationRatio: [
          {required: false,
            message: "赔付比例只能录入0-100的数字",
            pattern: /^(([1-9]\d)|\d|100)$/,
            trigger: ['change','blur']}
        ],
        city: [
          {required: true, message: "预约医院不能为空", trigger: ["blur","change"]}
        ],
        department: [
          {required: true, message: "科室不能为空", trigger: ["blur","change"]}
        ],
        organCode: [
          {required: true, message: "出单机构不能为空", trigger: ["blur","change"]}
        ],
        'email': [
          {required: true, message: "Email不能为空", trigger: "blur"},
          {required: true,
            message: "邮箱格式不正确",
            pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
            trigger: ["blur","change"]
          }
        ],
      },
      readonly: true,
      hospitalDialog: false,
      dialogVisable: "",//上传附件用
      historicalProblemData:Array,//上传附件用
      updateBy: undefined,
      sendForm: {
        channelCode:"",
        textarea:"",
        service: "1",
        channel: "",
        acceptor: "",
        acceptorTime:"",
        handler: "",
        handlerTime: "",
        workNumber: "",
        policyNumber: "",
        secondNumber: "",
        insuredName: "",
        beInsuredName: "",
        beInsuredNo: "",
        organization: "",
        appointmentTime:"",
        priority:"",
        vip:"",
        phone:"",
        state:""
      },
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10


      },
      loading: true,
      workPoolData: [],
      isinit: 'Y',
      totalCount: 0,
      changeSerchData: {},
      states: [],
      serves: [{
        value: '1',
        label: '服务1'
      }, {
        value: '2',
        label: '服务2'
      }, {
        value: '3',
        label: '服务3'
      }, {
        value: '4',
        label: '服务4'
      }],
      sysUserOptions: [],
    }
  },
  created() {

  },
  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.cs_channel = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_sex = this.dictList.find(item => {
      return item.dictType === 'cs_sex'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_communication_language = this.dictList.find(item => {
      return item.dictType === 'cs_communication_language'
    }).dictDate
    this.cs_identity = this.dictList.find(item => {
      return item.dictType === 'cs_identity'
    }).dictDate
    this.cs_whether_flag = this.dictList.find(item => {
      return item.dictType === 'cs_whether_flag'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_relation = this.dictList.find(item => {
      return item.dictType === 'cs_relation'
    }).dictDate
    this.cs_feedback_type = this.dictList.find(item => {
      return item.dictType === 'cs_feedback_type'
    }).dictDate
    this.cs_end_case = this.dictList.find(item => {
      return item.dictType === 'cs_end_case'
    }).dictDate
    this.cs_direct_settlement = this.dictList.find(item => {
      return item.dictType === 'cs_direct_settlement'
    }).dictDate
    this.cs_consultation_type = this.dictList.find(item => {
      return item.dictType === 'cs_consultation_type'
    }).dictDate
    this.cs_time_unit = this.dictList.find(item => {
      return item.dictType === 'cs_time_unit'
    }).dictDate
    // 地址下拉选
    this.getAddressData()
  },
  methods: {
    //关闭按钮
    hiddenShow:function () {
      // 返回上级路由并关闭当前路由
      this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
      this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)

    },
    //上传附件
    upload(){
      this.$refs.upload.open();
    },
    download(){},
    //提交页面数据
    submit(){
      this.$refs.ruleForm.validate((valid) => {
      if (valid) {
      this.ruleForm.symptomTimes=this.ruleForm.a+'-'+this.ruleForm.b;
      this.ruleForm.complaintTime = this.ruleForm.complaintTimes[0]+'-'+this.ruleForm.complaintTimes[1];
      let insert=this.ruleForm
      addReservationInsert(insert).then(res => {
        console.log("insert",insert)
        if (res != null && res.code === 200) {
          this.$message.success("插入成功");
          this.checkSubmitFlag = '01';
          this.ruleForm.workOrderNo=res.msg;
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "失败！"
            )
          }
        }
      }).catch(res => {

      })
    }else {
      return false
    }
  })
    },
    resetForm() {
      this.$refs.sendForm.resetFields()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        Service: this.sendForm.Service,
        channel: this.sendForm.channel,
        Acceptor: this.sendForm.Acceptor,
        acceptorTime: this.sendForm.acceptorTime,
        Handler: this.sendForm.Handler,
        HandlerTime: this.sendForm.HandlerTime,
        workNumber: this.sendForm.workNumber,
        policyNumber: this.sendForm.policyNumber,
        secondNumber: this.sendForm.secondNumber,
        insuredName: this.sendForm.insuredName,
        beInsuredName: this.sendForm.beInsuredName,
        beInsuredNo: this.sendForm.beInsuredName,
        organization: this.sendForm.organization,
        appointmentTime:this.sendForm.appointmentTime,
        priority:this.sendForm.priority,
        vip:this.sendForm.vip,
        phone:this.sendForm.phone,
        state:this.sendForm.state
      }
      console.log('query: ',query)
      demandListAndPublicPool(query).then(res => {
        console.log('------------: ',res)
        if (res != null && res.code === 200) {
          this.workPoolData = res.rows
          this.totalCount = res.total
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
    handleSelectionChange(val) {
      this.dataonLineListSelections = val
    },
    closeHospital() {
      this.hospitalDialog = false
    },
    openHospitalDialog() {
      if (this.region.length<1){
        return this.$message.warning(
          "请先选择预约医院省市！"
        )
      }else {
        this.queryData={
          region:this.region,
          hospitalName:this.ruleForm.hospitalName
        }
        this.hospitalDialog = true
      }

    },
    getPropData(val) {
      if (val.deptList && val.deptList.length>0){
        this.departmentOption=val.deptList
      }else {
        this.departmentOption=[]
      }
      this.ruleForm.medicalInstitution=val.hospitalCode
      this.ruleForm.hospitalName=val.hospitalName
      this.ruleForm.hospitalWorkCall=val.consultPhone
      this.ruleForm.hospitalAddress=val.address
    },
    handleChange(){
      this.ruleForm.province = this.region[0]
      this.ruleForm.city = this.region[1]
    },
    // 地址下拉选
    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },
  }
}
</script>

<style scoped>
.batchInfo_class .el-tag--small {
  margin-right: 10px !important;
}
.item-width {
  width: 180px;
}
/deep/ .el-radio{
  /*display: block;*/
  line-height: 30px;
  /*white-space: normal;*/
  /*margin-right: 0;*/
}
</style>
