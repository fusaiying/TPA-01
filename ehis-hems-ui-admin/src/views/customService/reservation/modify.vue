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
              <el-input readonly v-model="sendForm.acceptor" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel" readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
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
            <el-form-item label="保溢生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢满期日:"  prop="Acceptor">
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
      <el-form ref="workPoolData" :rules="rules" :model="workPoolData"  style="padding-bottom: 30px;" label-width="150px"
               label-position="right" size="mini">

        <span style="color: blue">口腔责任直接结算-服务受理信息</span>
        <el-divider/>

        <el-row>
          <el-form-item label="受理渠道：" prop="channelCode" >
            <el-radio-group v-model="workPoolData.channelCode">
              <el-radio
                v-for="dict in cs_channel"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{ dict.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请人姓名：" prop="complaintPerson.name">
              <el-input  v-model="workPoolData.complaintPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width"  >
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="callPerson.name">
              <el-input v-model="workPoolData.callPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="与申请人关系：" prop="callRelationBy">
              <el-select v-model="workPoolData.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="传真：" prop="contactsPerson.fax">
              <el-input v-model="workPoolData.contactsPerson.fax" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊类型：" prop="visitType">

              <el-select v-model="workPoolData.visitType" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_consultation_type" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="hospitalWorkCall">
              <el-input v-model="workPoolData.hospitalWorkCall" class="item-width" maxlength="20"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机：" prop="contactsPerson.mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
<!--        <el-row>
          <el-col :span="8">
            <el-form-item label="就诊日期："  style="white-space: nowrap" prop="complaintTime">
              <el-date-picker
                v-model="workPoolData.complaintTime"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
          </el-col>

        </el-row>-->
        <el-row>
          <el-col :span="3">
            <el-form-item label="家庭电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[0]">
              国家区号:+
              <el-input v-model="workPoolData.contactsPerson.homePhone1[0]" class="item-width" style="width: 60px" maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[1]">
              区号
              <el-input v-model="workPoolData.contactsPerson.homePhone1[1]" class="item-width" size="mini" style="width: 145px"
                        maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[2]">
              号码
              <el-input v-model="workPoolData.contactsPerson.homePhone1[2]" class="item-width" size="mini" style="width: 145px"
                        maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[3]">
              分机号
              <el-input v-model="workPoolData.contactsPerson.homePhone1[3]" class="item-width" size="mini" style="width: 145px"
                        maxlength="4"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <el-form-item label="办公电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[0]">
              国家区号:+
              <el-input v-model="workPoolData.contactsPerson.workPhone1[0]" class="item-width" style="width: 60px" maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[1]">
              区号
              <el-input v-model="workPoolData.contactsPerson.workPhone1[1]" class="item-width" size="mini" style="width: 145px"
                        maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[2]">
              号码
              <el-input v-model="workPoolData.contactsPerson.workPhone1[2]" class="item-width" size="mini" style="width: 145px"
                        maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[3]">
              分机号
              <el-input v-model="workPoolData.contactsPerson.workPhone1[3]" class="item-width" size="mini" style="width: 145px"
                        maxlength="4"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="预约日期："  style="white-space: nowrap" prop="appointmentDate">
              <el-date-picker class="item-width"
                v-model="workPoolData.appointmentDate"
                type="date"
                placeholder="选择日期时间"
                value-format="YYYY-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约时间："  style="white-space: nowrap" prop="complaintTimes">
              <el-time-picker class="item-width"
                              is-range
                              v-model="workPoolData.complaintTimes"
                              range-separator="-"
                              start-placeholder="开始时间"
                              end-placeholder="结束时间"
                              value-format="HH:mm:ss">
              </el-time-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院地址：" prop="hospitalAddress">
              <el-input v-model="workPoolData.hospitalAddress" class="item-width" clearable size="mini" placeholder="请输入"/>
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
          </el-col>
         <!-- <el-col :span="3">
            <el-form-item label="预约医院：" style="white-space: nowrap;" :inline="true" prop="province">
              省份：<el-input v-model="workPoolData.province" style="width: 100px"  />
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="city" >
              城市：<el-input v-model="workPoolData.city" style="width: 100px" />
            </el-form-item>
          </el-col>-->

          <el-col :span="8">
            <el-form-item label="医疗机构：" prop="medicalInstitution">
                <!--<el-input v-model="workPoolData.medicalInstitution" size="mini" class="item-width2"/>
                <el-button type="primary" @click="searchHandle">详细信息</el-button>-->
              <el-col :span="16">
                <!--  <el-input v-model="workPoolData.medicalInstitution" input-w clearable size="mini" placeholder="请输入"/>-->
                <el-input v-model="workPoolData.hospitalName" input-w clearable size="mini" disabled/>
              </el-col>
              <el-button type="primary" @click="openHospitalDialog">查询</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="department">
<!--              <el-input v-model="workPoolData.department" class="item-width"  size="mini" />-->
              <el-select v-if="departmentOption.length>0" v-model="workPoolData.department" class="item-width" placeholder="请选择">
                <el-option v-for="item in departmentOption" :key="item" :label="item"
                           :value="item"/>
              </el-select>
              <el-input v-else v-model="workPoolData.department" class="item-width" clearable size="mini" maxlength="20" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="validCertificate">
              <el-select v-model="workPoolData.validCertificate" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否持保险卡：" prop="settlementCard">
              <el-select v-model="workPoolData.settlementCard" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" prop="compensationRatio">
              <el-input v-model="workPoolData.compensationRatio" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="disease">
              <el-input v-model="workPoolData.disease" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="a">
              <el-input v-model="workPoolData.a" style="width: 90px" clearable size="mini" placeholder="请输入"maxlength="4"/>
              <el-select v-model="workPoolData.b" style="width: 90px" placeholder="请选择"  >
                <el-option v-for="item in cs_time_unit" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="earliestTime">
              <el-input v-model="workPoolData.earliestTime" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col >
            <el-form-item label="疾病或体征：" prop="symptomsSigns">
              <el-input v-model="workPoolData.symptomsSigns" class="width-full"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

          <el-col :span="6">
            <el-form-item label="是否意外：" prop="accidentFlag">
              <el-select v-model="workPoolData.accidentFlag" class="width-full" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="18">
            <el-form-item label="意外原因：" prop="accidentReason">
              <el-input v-model="workPoolData.accidentReason" class="width-full"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="content">
            <el-input
              type="textarea"
              :rows="3"
              maxlength="2000"
              v-model="workPoolData.content">
            </el-input>
          </el-form-item>
        </el-row>


      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color:blue;">业务流转情况</span>
        <el-divider></el-divider>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="flowLogData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="status" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.linkCode">
              <span>{{ selectDictLabel(cs_order_state, scope.row.linkCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="operateCode" label="操作" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.operateCode">
              <span>{{ selectDictLabel(cs_action_type, scope.row.operateCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="makeBy" label="受/处理人" show-overflow-tooltip/>
          <el-table-column align="center" prop="umNum" label="UM账号" show-overflow-tooltip/>
          <el-table-column prop="makeTime" label="时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.createdTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='03'" style="font-size:12px" type="primary" @click="modifyDetails(scope.row)">修改说明</el-link>
            </template>
          </el-table-column>
          <modify-details ref="modifyDetails"></modify-details>
          <el-table-column prop="opinion" align="center" label="处理意见" show-overflow-tooltip/>
          <el-table-column prop="toDepartment" align="center" label="流转部门" show-overflow-tooltip/>
          <el-table-column prop="toReason" align="center" label="流传原因" show-overflow-tooltip/>
        </el-table>

        <pagination
          v-show="flowLogCount>0"
          :total="flowLogCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchFlowLog"
        />
      </div>
    </el-card>


    <el-card>
      <el-form ref="ruleForm" :model="ruleForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini" v-if="this.queryParams.status=='05'">
        <span style="color: blue">服务处理</span>
        <el-divider></el-divider>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况" prop="bank"  >
              <el-radio-group v-model="ruleForm.bank" disabled>
                <el-radio   :label="1">成功</el-radio>
                <el-radio   :label="2">失败</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder=""
              v-model="ruleForm.textarea"
              disabled
            >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>

    <el-card>
      <el-form ref="ruleForm" :model="workPoolData"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini" :rules="rules">
        <span style="color: blue">修改原因</span>
        <el-divider></el-divider>
        <el-row>
            <el-form-item label="修改原因" prop="editReason" >
              <el-radio-group v-model="workPoolData.editReason">
                <el-radio   label="01">客户申请变动</el-radio>
                <el-radio   label="02">操作失误</el-radio>
                <el-radio   label="03">其他原因</el-radio>
              </el-radio-group>
            </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="editRemark">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="workPoolData.editRemark">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div style="text-align: right; margin-right: 1px;">
      <el-button  type="primary" size="mini" @click="submit">保存</el-button>
      <el-button  type="primary"size="mini" @click="hiddenShow">关闭</el-button>
      </div>
      <hospital :value="hospitalDialog" @closeHospital="closeHospital" :queryData="queryData"
                @getPropData="getPropData"/>
    </el-card>





  </div>
</template>

<script>
  import moment from 'moment'
  import Hospital from "../hospital/hospital";
  import {FlowLogSearch} from '@/api/customService/demand'
  import modifyDetails from "../common/modul/modifyDetails";
  import {demandListAndPublicPool,demandListAndPersonalPool,modifyReservationSubmit} from '@/api/customService/reservation'
  import {getAddress} from "@/api/baseInfo/medicalManage";
  import {getHospitalInfo} from '@/api/customService/reservation'
  let dictss = [
    {dictType: 'cs_identity'},
    {dictType: 'cs_sex'},
    {dictType: 'cs_communication_language'},
    {dictType: 'cs_service_item'},
    {dictType: 'cs_organization'},
    {dictType: 'cs_priority'},
    {dictType: 'cs_channel'},
    {dictType: 'cs_whether_flag'},
    {dictType: 'cs_order_state'},
    {dictType: 'cs_action_type'},
    {dictType: 'cs_consultation_type'},
    {dictType: 'cs_relation'},
    {dictType: 'cs_time_unit'},
  ]
  export default {
    components: {
      modifyDetails,Hospital
    },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD HH:mm:ss')
        }
      }
    },
    data() {
      const checkComplaintTime= (rule, value, callback) => {
        let tDate = new Date();
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
        queryData:{
          region:[],
          hospitalName:''
        },
        region:[],
        regions:[],
        hospitalDialog:false,
        workPoolDataFlag:false,
        ruleFormFlag:false,
        cs_relation:[],//关系
        cs_service_item:[],//服务项目
        cs_sex:[],//性别
        cs_priority:[],//优先级
        cs_communication_language:[],//语言
        cs_organization:[],//机构
        cs_handle_state:[],// 状态：
        cs_channel:[],
        cs_order_state:[],
        cs_action_type:[],
        cs_consultation_type:[],
        cs_whether_flag:[],
        cs_time_unit: [],
        dictList: [],
        //流转用
        flowLogData:[],
        departmentOption:[],
        flowLogCount: 0,
        //服务项目
        workPoolData: {
          complaintPerson:{
            name:"",
          },
          medicalInstitution:"",
          hospitalName:"",
          callRelationBy:"",
          workOrderNo:"",
          hospitalWorkCall:"",
          hospitalAddress:"",
          channelCode:"",
          callCenterId:"",
          priorityLevel:"",
          callPerson: {
            name:"",
            sex:"",
            mobilePhone:"",
            fax:"",
          },
          fax:"",
          visitType:"",
          settlementCard:"",
          compensationRatio:"",
          disease:"",
          symptomTimes:"",
          earliestTime:"",
          contactsPerson:{
            homePhone1:[],
            workPhone1:[],
            name:"",
            sex:"",
            mobilePhone:"",
            address:"",
            homePhone:"",
            workPhone:"",
            language:"",
            fax:"",
          },//联系人
          symptomsSigns:"",
          accidentFlag:"",
          accidentReason:"",
          content:"",
          editReason:"",
          editRemark:"",

        },
        totalCount: 0,
        //需要填入数据的部分
        ruleForm:{

        },
        // 表单校验
        // 表单校验根据Form 组件提供了表单验证的功能，只需要通过 rules 属性传入约定的验证规则，并将 Form-Item 的 prop 属性设置为需校验的字段名即可
        rules: {
          'contactsPerson.homePhone1[0]': [
            {required: false,
              message: "国家区号只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.homePhone1[1]': [
            {required: false,
              message: "区号只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.homePhone1[2]': [
            {required: false,
              message: "号码只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.homePhone1[3]': [
            {required: false,
              message: "分机号只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.workPhone1[0]': [
            {required: false,
              message: "国家区号只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.workPhone1[1]': [
            {required: false,
              message: "区号只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.workPhone1[2]': [
            {required: false,
              message: "号码只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
          'contactsPerson.workPhone1[3]': [
            {required: false,
              message: "分机号只能录入数字",
              pattern: /^\d*$/,
              trigger: ['change','blur']
            }
          ],
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
            {required: true, message: "联系人电话不能为空", trigger: ["blur","change"]}
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
          editRemark:[
            {required: true, message: "修改说明不能为空", trigger: "blur"},
            { min: 3, max: 100, message: '长度在 3 到 100 个字符' }
          ],
          editReason:[
            {required: true, message: "修改原因不能为空", trigger: "blur"}
          ],
        },

        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          channle:"",
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
          pageSize: 10,
          workOrderNo:"",
          queryParams:"",
          policyItemNo:"",
          status:""

        },
        changeSerchData: {},
        states: [],
      }
    },
    created() {
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      this.searchHandle();
      this.searchFlowLog();
    },
    async mounted() {
      // 字典数据统一获取
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })

      // 下拉项赋值
      this.cs_action_type = this.dictList.find(item => {
        return item.dictType === 'cs_action_type'
      }).dictDate
      this.cs_order_state = this.dictList.find(item => {
        return item.dictType === 'cs_order_state'
      }).dictDate
      this.cs_communication_language = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.cs_service_item = this.dictList.find(item => {
        return item.dictType === 'cs_service_item'
      }).dictDate
      this.cs_organization = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.cs_channel = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.cs_whether_flag = this.dictList.find(item => {
        return item.dictType === 'cs_whether_flag'
      }).dictDate
      this.cs_sex = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.cs_relation = this.dictList.find(item => {
        return item.dictType === 'cs_relation'
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
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //提交页面数据
      submit(){

        this.$refs.workPoolData.validate((valid) => {
          this.workPoolDataFlag=valid
        })
        this.$refs.ruleForm.validate((valid) => {
          this.ruleFormFlag=valid
        })
        if (this.workPoolDataFlag&&this.ruleFormFlag) {
          this.workPoolData.workOrderNo=this.$route.query.workOrderNo;
          this.workPoolData.symptomTimes=this.workPoolData.a+'-'+this.workPoolData.b;
          this.workPoolData.complaintTime = this.workPoolData.complaintTimes[0]+'-'+this.workPoolData.complaintTimes[1];
          let send=this.workPoolData
          modifyReservationSubmit(send).then(res => {
            if (res != null && res.code === 200) {
              this.$message.success("保存成功！")
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "提交失败！"
                )
              }
            }
          }).catch(res => {

          })
        }
      },
      //关闭页面
      close(){

      },
      //反显信息需求
      searchHandle: function () {
        if (this.queryParams.status == "01") {
          let query = this.queryParams
          demandListAndPublicPool(query).then(res => {
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows[0];
              this.totalCount = res.total;
              if (this.workPoolData.symptomTimes != null && this.workPoolData.symptomTimes != '') {
                let arr=this.workPoolData.symptomTimes.split('-');
                this.$set(this.workPoolData, `a`, arr[0]);
                this.$set(this.workPoolData, `b`, arr[1]);
              }
              if(this.workPoolData.complaintTime != null && this.workPoolData.complaintTime !=''){
                //预约时间反显
                let timeArr=this.workPoolData.complaintTime.split('-');
                this.$set(this.workPoolData, `complaintTimes`, timeArr);
              }
              let queryData={
                hospitalCode:res.rows[0].medicalInstitution,
                hospitalName:res.rows[0].hospitalName,
                province:res.rows[0].province,
                city:res.rows[0].city,
              }
              if (res.rows[0].medicalInstitution!='9999'){
                getHospitalInfo (queryData).then(res => {
                  if (res != null && res !== '') {
                    if (res.rows[0].deptList && res.rows[0].deptList.length>0){
                      this.departmentOption=res.rows[0].deptList
                    }else {
                      this.departmentOption=[]
                    }
                  }
                })
              }else {
                this.departmentOption=[]
              }
              this.region.push(this.workPoolData.province)
              this.region.push(this.workPoolData.city)
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })
        } else {
          let query = this.queryParams
          demandListAndPersonalPool(query).then(res => {
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows[0]
              this.totalCount = res.total
              console.log('个人', res.total)
              if (this.workPoolData.symptomTimes != null && this.workPoolData.symptomTimes != '') {
                let arr=this.workPoolData.symptomTimes.split('-')
                console.log(arr)
                /*this.ruleForm.a=arr[0]
                this.ruleForm.b=arr[1]*/
                this.$set(this.workPoolData, `a`, arr[0]);
                this.$set(this.workPoolData, `b`, arr[1]);
              }
              this.region.push(this.workPoolData.province)
              this.region.push(this.workPoolData.city)
              let queryData={
                hospitalCode:res.rows[0].medicalInstitution,
                hospitalName:res.rows[0].hospitalName,
                province:res.rows[0].province,
                city:res.rows[0].city,
              }
            if (res.rows[0].medicalInstitution!='9999'){
              getHospitalInfo (queryData).then(res => {
                if (res != null && res !== '') {
                  if (res.rows[0].deptList && res.rows[0].deptList.length>0){
                    this.departmentOption=res.rows[0].deptList
                  }else {
                    this.departmentOption=[]
                  }
                }
              })
            }else {
              this.departmentOption=[]
            }

              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })

        }
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          console.log(workOrderNo)
          console.log('轨迹表',res.rows)
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            console.log("searchFlowLog",this.flowLogData)
            this.flowLogCount = res.total
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
      //关闭按钮
      hiddenShow:function () {
        // 返回上级路由并关闭当前路由
        this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
        this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)
      },
      openHospitalDialog() {
        if (this.region.length<1){
          return this.$message.warning(
            "请先选择预约医院省市！"
          )
        }else {
          this.queryData={
            region:this.region,
            hospitalName:this.workPoolData.hospitalName
          }
          this.hospitalDialog = true
        }
      },
      handleChange(){
        this.workPoolData.province = this.region[0]
        this.workPoolData.city = this.region[1]
      },
      closeHospital() {
        this.hospitalDialog = false
      },
      // 地址下拉选
      getAddressData() {
        getAddress().then(response => {
          this.regions = response
        }).catch(error => {
        })
      },
      getPropData(val) {
        if (val.deptList && val.deptList.length>0){
          this.departmentOption=val.deptList
        }else {
          this.departmentOption=[]
        }
        this.workPoolData.medicalInstitution=val.hospitalCode
        this.workPoolData.hospitalName=val.hospitalName
        this.workPoolData.hospitalWorkCall=val.consultPhone
        this.workPoolData.hospitalAddress=val.address
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 180px;
  }
  .item-width2 {
    width: 70px;
  }
  /deep/ .el-radio {
    /*display: block;*/
    line-height: 30px;
    /*white-space: normal;*/
    /*margin-right: 0;*/
  }
</style>
