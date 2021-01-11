<template>
  <div style="position: relative; margin-top: 30px;margin-left: 30px">
    <el-tabs v-model="activeName">
      <el-tab-pane :label="label.label01" name="01">
        <!--基本信息-->
        <div class="app-container">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>基本信息</span>
              <span style="float: right;">
          <el-button size="mini" type="success" @click="baseFormSaveHandle">保存</el-button>
        </span>
            </div>
            <el-form ref="baseForm" :rules="baseFormRules" :model="baseForm" style="padding-bottom: 30px;"
                     label-width="170px" size="mini" class="baseInfo_class">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="医疗编码：" prop="providerCode">
                    <el-input v-model="baseForm.providerCode" class="item-width" clearable disabled size="mini"
                              placeholder="系统自动生成（待定）"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="是否互联网医院：" prop="interHosp">
                    <el-select v-model="baseForm.interHosp" class="item-width" placeholder="请选择(默认否)" clearable>
                      <el-option label="是" value="01"/>
                      <el-option label="否" value="02"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="是否虚拟机构：" prop="virtualOrg">
                    <el-radio-group v-model="baseForm.virtualOrg">
                      <el-radio label="01">是</el-radio>
                      <el-radio label="02">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="中文展示名称：" prop="chname1">
                    <el-input v-model="baseForm.chname1" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="英文展示名称：" prop="enname1">
                    <el-input v-model="baseForm.enname1" class="item-width" clearable size="mini" maxlength="50"
                              placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="别名：" prop="otherName">
                    <el-input v-model="baseForm.otherName" class="item-width" clearable size="mini" maxlength="50"
                              placeholder="请输入"/>
                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item label="中文工商注册名称：" prop="chregister">
                    <el-input v-model="baseForm.chregister" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="英文工商注册名称：" prop="enregister">
                    <el-input v-model="baseForm.enregister" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="官方网址：" prop="website">
                    <el-input v-model="baseForm.website" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="300"/>
                  </el-form-item>
                </el-col>


                <el-col :span="8">
                  <el-form-item label="州：" prop="continent">
                    <el-select v-model="baseForm.continent" class="item-width" placeholder="请选择" clearable>
                      <el-option label="亚洲" value="01"/>
                      <el-option label="欧洲" value="02"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="国家：" prop="country">
                    <el-select v-model="baseForm.country" class="item-width" placeholder="请选择" clearable>
                      <el-option label="中国" value="01"/>
                      <el-option label="日本" value="02"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="省市区：" prop="address">
                    <el-cascader
                      v-model="baseForm.address"
                      :props="{ checkStrictly: true }"
                      :options="regions"
                      class="item-width"
                      @change="handleChange"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-form-item style="margin-right: 20px;" label="中文地址：" prop="chaddreess">
                  <el-input v-model="baseForm.chaddreess" clearable size="mini" placeholder="请输入" maxlength="300"/>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item style="margin-right: 20px;" label="英文地址：" prop="enaddress">
                  <el-input v-model="baseForm.enaddress" clearable size="mini" placeholder="请输入" maxlength="300"/>
                </el-form-item>
              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="一级属性：" prop="firstAttribute">
                    <el-select v-model="baseForm.firstAttribute" class="item-width" placeholder="请选择" clearable>
                      <el-option value="01" label="公立"/>
                      <el-option value="02" label="非公立"/>
                      <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="二级属性：" prop="secondAttribute">
                    <el-select v-model="baseForm.secondAttribute" class="item-width" placeholder="请选择" clearable>
                      <el-option value="01" label="三级甲等"/>
                      <el-option value="02" label="三级乙等"/>
                      <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="官方电话：" prop="phone">
                    <el-input v-model="baseForm.areacode" class="item-width" clearable style="width: 75px"
                              placeholder="区号"/>
                    —
                    <el-input v-model="baseForm.phone" class="item-width" clearable size="mini" style="width: 145px"
                              placeholder="请输入电话" maxlength="50"/>
                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item label="综专科类型：" prop="type">
                    <el-select v-model="baseForm.type" class="item-width" placeholder="请选择" clearable
                               @change="inputTypeShow" style="width: 120px">
                      <el-option label="中医" value="01"/>
                      <el-option label="综合" value="02"/>
                      <el-option label="专科" value="03"/>

                      <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                    </el-select>
                    <!--选择专科时  显示出来-->

                    <el-select v-if="typeShow" v-model="baseForm.type2" class="item-width" placeholder="请选择" clearable
                               style="width: 120px"
                               multiple>
                      <el-option label="妇产" value="01"/>
                      <el-option label="儿科" value="02"/>
                      <el-option label="体检" value="03"/>
                      <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                    </el-select>

                  </el-form-item>
                </el-col>

                <el-col :span="16">
                  <el-form-item label="曾用名：" prop="usedname">
                    <el-input v-model="baseForm.usedname" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="咨询电话：" prop="consultphone">
                    <el-input v-model="baseForm.consultphone" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="公共邮箱：" prop="email">
                    <el-input v-model="baseForm.email" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="邮编：" prop="zipcode">
                    <el-input v-model="baseForm.zipcode" class="item-width" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item label="传真：" prop="portraiture">
                    <el-input v-model="baseForm.portraiture" class="item-width" clearable size="mini"
                              placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="床位数：" prop="beds">
                    <el-input v-model="baseForm.beds" class="item-width" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col>
                  <el-form-item label="医院特色：" prop="hospitalCharacteristics" class="redItem">
                    <div>
                      <div>
                        <el-col :span="8">
                          <el-form-item label="是否国际部：" prop="international">
                            <el-radio-group v-model="baseForm.international">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否全国前十：" prop="topten">
                            <el-radio-group v-model="baseForm.topten">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否专科医院：" prop="specializedHospital">
                            <el-radio-group v-model="baseForm.specializedHospital">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                      </div>
                      <div>
                        <el-col :span="8">
                          <el-form-item label="是否当地前三：" prop="topthird">
                            <el-radio-group v-model="baseForm.topthird">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否特定医院：" prop="appointHospital">
                            <el-radio-group v-model="baseForm.appointHospital">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="6">
                          <el-form-item label="是否全国专科前五：" prop="topfive">
                            <el-radio-group v-model="baseForm.topfive">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                      </div>
                      <div>
                        <el-col :span="8">
                          <el-form-item label="是否部队医院：" prop="armyhosptial">
                            <el-radio-group v-model="baseForm.armyhosptial">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否有住院：" prop="inhosptial">
                            <el-radio-group v-model="baseForm.inhosptial">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否特需部：" prop="special">
                            <el-radio-group v-model="baseForm.special">
                              <el-radio label="01">是</el-radio>
                              <el-radio label="02">否</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                      </div>
                      <el-col :span="8">
                        <el-form-item label="是否社保定点医院：" prop="flag">
                          <el-radio-group v-model="baseForm.flag">
                            <el-radio label="01">是</el-radio>
                            <el-radio label="02">否</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </el-col>
                      <el-col :span="8">
                        <el-form-item label="是否康复医院：" prop="rehabilitationHospital">
                          <el-radio-group v-model="baseForm.rehabilitationHospital">
                            <el-radio label="01">是</el-radio>
                            <el-radio label="02">否</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </el-col>
                    </div>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="状态：" prop="state">
                    <el-select v-model="baseForm.state" class="item-width" placeholder="请选择" clearable>
                      <el-option label="有效" value="01"/>
                      <el-option label="无效" value="02"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="经度：" prop="longitude">
                    <el-input v-model="baseForm.longitude" class="item-width" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="纬度：" prop="latitude">
                    <el-input v-model="baseForm.latitude" class="item-width" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item label="标志性建筑：" prop="landmarks">
                    <el-input v-model="baseForm.landmarks" class="item-width" clearable size="mini" maxlength="500"
                              placeholder="c端客户可看"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="停车场：" prop="carpark">
                    <el-input v-model="baseForm.carpark" class="item-width" clearable size="mini" placeholder="c端客户可看"
                              maxlength="500"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="交通路线：" prop="transitroute">
                    <el-input v-model="baseForm.transitroute" class="item-width" clearable size="mini" maxlength="500"
                              placeholder="c端客户可看"/>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-form-item style="margin-right: 20px;" label="机构简介：" prop="introduction">
                  <el-input type="textarea" v-model="baseForm.introduction" clearable size="mini" placeholder="请输入"
                            maxlength="500"/>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item style="margin-right: 20px;" label="备注(中文)：" prop="chremarks">
                  <el-input v-model="baseForm.chremarks" clearable size="mini" placeholder="请输入" maxlength="2000"/>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item style="margin-right: 20px;" label="备注(英文)：" prop="enremarks">
                  <el-input v-model="baseForm.enremarks" clearable size="mini" placeholder="请输入" maxlength="2000"/>
                </el-form-item>
              </el-row>
            </el-form>
          </el-card>
        </div>
        <!--科室信息-->
        <div class="app-container">
          <department-info :providerCode="providerCode" :disabledFlag="disabledFlag" ref="departmentInfo"
                           :departmentFrom="departmentFrom"
                           :departmentTableShow="departmentTableShow"></department-info>

        </div>

        <!--服务信息-->
        <div class="app-container">
          <service-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="serviceInfo"
                        :serviceForm="serviceForm"></service-info>
        </div>


        <!--风控信息-->
        <!--                  <div class="app-container">
                            <risk-control-info :disabledFlag="disabledFlag" :hospitalcode="providercode" > </risk-control-info>
                          </div>-->
        <!--预约信息-->
        <div class="app-container">
          <reserve-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="reserveInfo"
                        :reserveInfoForm="reserveInfoForm"></reserve-info>
        </div>

        <!--联系信息-->
        <div class="app-container">
          <contact-info :disabledFlag="disabledFlag" :supplierCode="providerCode" ref="contactInfo"
                        :contactInfoFrom="contactInfoFrom" :contactInfoTableShow="contactInfoTableShow"></contact-info>
        </div>
        <!--结算信息-->
        <div class="app-container">
          <closing-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="closingInfo"
                        :closingTableShow="closingTableShow" :annexupload="annexupload" :status="activeName"
                        :closingFrom="closingFrom"></closing-info>
        </div>
        <!--附件信息-->
        <!--        <div class="app-container">
                  <annex-info :disabledFlag="disabledFlag" :suppliercode="providerCode" @upload="changeAnnexFlag"></annex-info>
                </div>-->

        <div
          style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;float: right; ">
          <el-button size="mini" type="success" @click="submitHandle">提交审核</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
          <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
        </div>

        <!--          <manageDeal :hospitalcode="hospitalcode" :table-data="pendingTableData" :status="activeName" @getSelect="getSelect"/>-->
      </el-tab-pane>
      <el-tab-pane :label="label.label02" name="02">
        <!--          <manageDeal :table-data="pendingTableData" :status="activeName" @getSelect="getSelect"/>-->
        <!--基本信息-->
        <div class="app-container">
          <other-base-info :providerCode="providerCode" ref="otherBaseInfo" :status="activeName"></other-base-info>
        </div>
        <!--服务信息-->
        <div class="app-container">
          <other-service-info :providerCode="providerCode" ref="otherServiceInfo"
                              :status="activeName"></other-service-info>
        </div>

        <!--预约信息-->
        <div class="app-container">
          <other-reserve-info :providerCode="providerCode" ref="otherReserveInfo"
                              :status="activeName"></other-reserve-info>
        </div>

        <!--结算信息-->
        <div class="app-container">
          <closing-info :closingFrom="otherClosingFrom" :closingTableShow="closingTableShow" :status="activeName"
                        ref="otherClosingInfo"></closing-info>
        </div>


        <!--        <div class="app-container">
                  <annex-info></annex-info>
                </div>-->


        <div
          style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;float: right; ">

          <el-button size="mini" type="primary" @click="otherResetForm">重置</el-button>
          <el-button size="mini" type="success" @click="otherSubmitHandle">保存</el-button>
          <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {
  getInfo, addInfo, updateInfo,
  getdepInfo,
  getserviceInfo,
  getapplyInfo,
  getcontactsInfo, getbankInfo, checkUp
} from '@/api/baseInfo/medicalManage.js'


import departmentInfo from './components/departmentInfo'
import serviceInfo from './components/serviceInfo'

import reserveInfo from './components/reserveInfo'
import contactInfo from './components/contactInfo'
import closingInfo from './components/closingInfo'
import annexInfo from './components/annexInfo'

import otherBaseInfo from './components/otherBaseInfo'
import otherServiceInfo from './components/otherServiceInfo'
import otherReserveInfo from './components/otherReserveInfo'

import {getAddress} from '@/api/investigate/supplierManagement'

export default {
  components: {
    departmentInfo,
    serviceInfo,
    reserveInfo,
    contactInfo,
    closingInfo,
    annexInfo,
    otherBaseInfo,
    otherServiceInfo,
    otherReserveInfo
  },
  data() {
    const checkPhone = (rules, value, callback) => {
      if (!this.baseForm.phone) {
        callback(new Error('电话不能位空！'))
      } else {
        callback()
      }
    }
    const checkOtherName = (rules, value, callback) => {
      /* if (this.baseForm.othername==this.baseForm.othername) {
         callback(new Error('电话不能位空！'))
       } else {
         callback()
       }*/
      callback()
    }
    const checkBeds = (rules, value, callback) => {
      if (this.baseForm.inhosptial == '01') {
        if (!this.baseForm.beds) {
          callback(new Error('床位数不能位空！'))
        }
      } else {
        callback()
      }
    }
    return {
      typeShow: false,
      annexupload: undefined,
      closingTableShow: undefined,
      departmentTableShow: undefined,
      contactInfoTableShow: undefined,
      otherClosingFrom: {
        fromData: {},
        tableData: []
      },
      closingFrom: {
        fromData: {},
        tableData: []
      },
      contactInfoFrom: {
        contacts: []
      },
      reserveInfoForm: {
        outpatientearly: [],
        sendType: [],
        sendWay: [],
      },
      departmentFrom: {
        form: []
      },

      serviceForm: {
        networktype: '',
        category: '',
        classification: '',
        grade: '',
        displaynetwork: '',
        officialwebsite: '',
        prehospital: '',
        allcontopen: '',
        cooperationstatus: '',
        treatmentdiscount: '',
        code: '',
        examinediscount: '',
        type: '',
        beddiscount: '',
        advicenum: '',
        allowance: '',
        costs: '',
        discountinfo: '',
        specialdiscount: '',
        project: '',
        sellingpoint: '',
        foreignnote: '',
        internalnote: '',
        servicelocator: [],
        speciallanguage: [],
        networkHospitalType: []
      },
      show: true,
      disabledFlag: undefined,
      providerCode: '',
      activeName: '01',
      label: {
        label01: '医院',
        label02: '其他机构'
      },

      baseForm: {
        interHosp: '02',
        state: '01'
      },
      baseFormRules: {
        beds: [{validator: checkBeds, trigger: 'blur'}],
        flag: [{required: true, message: '不能为空！', trigger: 'blur'}],
        otherName: [{validator: checkOtherName, trigger: 'blur'}],
        virtualOrg: [{required: true, message: '不能为空！', trigger: 'blur'}],
        chname1: [{required: true, message: '不能为空！', trigger: 'blur'}],
        continent: [{required: true, message: '不能为空！', trigger: 'change'}],
        country: [{required: true, message: '不能为空！', trigger: 'change'}],
        address: [{required: true, message: '不能为空！', trigger: 'change'}],
        chaddreess: [{required: true, message: '不能为空！', trigger: 'blur'}],
        firstAttribute: [{required: true, message: '不能为空！', trigger: 'change'}],
        secondAttribute: [{required: true, message: '不能为空！', trigger: 'change'}],
        type: [{required: true, message: '不能为空！', trigger: 'change'}],
        phone: [{validator: checkPhone, required: true, trigger: 'blur'}],
        website: [{required: true, message: '不能为空！', trigger: 'blur'}],
        international: [{required: true, message: '不能为空！', trigger: 'blur'}],
        consultphone: [{required: true, message: '不能为空！', trigger: 'blur'}],
        special: [{required: true, message: '不能为空！', trigger: 'blur'}],
        state: [{required: true, message: '不能为空！', trigger: 'blur'}],
        introduction: [{required: true, message: '不能为空！', trigger: 'blur'}],
      },

      regions: [{
        value: 'zhinan',
        label: '指南',
        children: [{
          value: 'shejiyuanze',
          label: '设计原则',
          children: [{
            value: 'yizhi',
            label: '一致'
          }, {
            value: 'fankui',
            label: '反馈'
          }, {
            value: 'xiaolv',
            label: '效率'
          }, {
            value: 'kekong',
            label: '可控'
          }]
        }]
      }],
    }
  },
  mounted() {
    if (this.$route.query.providerCode) {
      this.providerCode = this.$route.query.providerCode
      this.disabledFlag = false
      /* if( this.$route.query.organizationFlag=='01') {*/
      //调用服务机构基本信息查询
      getInfo(this.providerCode).then(res => {
        this.baseForm = res.data
      }).catch(res => {
      })
      //调用科室信息查询
      getdepInfo(this.providerCode).then(res => {
        this.departmentFrom.form = res.data
        this.departmentFrom.form.map((data, index) => {
          data.id = index + 1
          data.isSet = false
        })
      }).catch(res => {
      })

      getserviceInfo(this.providerCode).then(res => {

        this.serviceForm = res.data

      }).catch(res => {
      })

      getapplyInfo(this.providerCode).then(res => {
        this.reserveInfoForm = res.data
      }).catch(res => {
      })

      getcontactsInfo(this.providerCode).then(res => {
        this.contactInfoFrom.contacts = res.data
        this.contactInfoFrom.contacts.map((data, index) => {
          data.id = index + 1
          data.isSet = false
        })
      }).catch(res => {
      })
      getbankInfo(this.providerCode).then(res => {
        this.closingFrom.fromData = res.data.baseProviderSettle
        this.closingFrom.tableData = res.data.baseBankVo
        this.closingFrom.tableData.map((data, index) => {
          data.id = index + 1
          data.isSet = false
        })
      }).catch(res => {
      })
      this.departmentTableShow = true
      this.contactInfoTableShow = true
      this.closingTableShow = true
      /* }
       else {
         //调用结算信息查询的方法


       }*/


    } else {
      //医疗机构
      this.annexupload = true
      this.disabledFlag = true
      this.departmentTableShow = false
      this.contactInfoTableShow = false
      this.closingTableShow = true

      //其他机构

    }


  },
  methods: {


    inputTypeShow() {
      if (this.baseForm.type == '03') {
        this.typeShow = true
      } else {
        this.typeShow = false
        this.baseForm.type2 = undefined
      }
    },

    //基本信息保存
    baseFormSaveHandle() {
      this.$refs.baseForm.validate((valid) => {
        this.departmentTableShow = true
        this.contactInfoTableShow = true
        this.closingTableShow = true
        this.disabledFlag = false
        if (valid) {
          const subFormSearch = JSON.parse(JSON.stringify(this.baseForm))
          if (this.baseForm.address) {
            subFormSearch.province = this.baseForm.address[0]
            subFormSearch.district = this.baseForm.address[2]
            subFormSearch.city = this.baseForm.address[1]
          }
          //存在调用基本信息保存的接口
          if (this.providerCode) {
            subFormSearch.activeName = this.activeName
            updateInfo(subFormSearch).then(res => {
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
          } else {

            subFormSearch.organizationFlag = this.activeName

            //不存在调用新增的接口
            addInfo(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.providerCode = res.data.providerCode
                this.baseForm.providerCode = this.providerCode
                this.disabledFlag = false
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
          this.disabledFlag = false
          return false
        }

      })
    },
    //级联改变
    handleChange(value) {
    },

    /* //  保存
     saveHandle() {
       this.$refs.baseForm.validate((valid1) => {
         this.$refs.plusForm.validate((valid2) => {
           if (valid1 && valid2) {
             const subFormSearch = JSON.parse(JSON.stringify(this.formSearch))
             if (this.formSearch.city) {
               subFormSearch.province = this.formSearch.city[0]
               subFormSearch.district = this.formSearch.city[2]
               subFormSearch.city = this.formSearch.city[1]
             }
             const param = {
               baseinfo: subFormSearch,
               contactinfo: this.form.contacts,
               departmentinfo: this.form.departments
             }
             if (this.$route.query.hospitalcode) {
               updataHospital(param).then(res => {
                 if (res.status === '1') {
                   this.$message({
                     message: '保存成功！',
                     type: 'success',
                     center: true,
                     showClose: true
                   })
                 } else if (res.status === '0') {
                   this.$message({
                     message: '保存失败,' + res.data.errMsg,
                     type: 'error',
                     center: true,
                     showClose: true
                   })
                 }
               })
             } else {
               addHospital(param).then(res => {
                 if (res.status === '1') {
                   this.$message({
                     message: '保存成功！',
                     type: 'success',
                     center: true,
                     showClose: true
                   })
                 } else if (res.status === '0') {
                   this.$message({
                     message: '保存失败,' + res.data.errMsg,
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
     },*/
    //  重置
    resetForm() {
      this.$refs.baseForm.resetFields()
      this.baseForm = {}
//  重置以后  调用查询方法
      if (!this.disabledFlag) {
        this.$refs['departmentInfo'].resetFrom();
        this.$refs['serviceInfo'].resetFrom();
        this.$refs['reserveInfo'].resetFrom();
        this.$refs['contactInfo'].resetFrom();
        this.$refs['closingInfo'].resetFrom();

        //调用服务机构基本信息查询
        getInfo(this.providerCode).then(res => {
          this.baseForm = res.data
        }).catch(res => {
        })
        //调用科室信息查询
        getdepInfo(this.providerCode).then(res => {
          this.departmentFrom.form = res.data
          this.departmentFrom.form.map((data, index) => {
            data.id = index + 1
            data.isSet = false
          })
        }).catch(res => {
        })

        getserviceInfo(this.providerCode).then(res => {

          this.serviceForm = res.data

        }).catch(res => {
        })

        getapplyInfo(this.providerCode).then(res => {
          this.reserveInfoForm = res.data
        }).catch(res => {
        })

        getcontactsInfo(this.providerCode).then(res => {
          this.contactInfoFrom.contacts = res.data
          this.contactInfoFrom.contacts.map((data, index) => {
            data.id = index + 1
            data.isSet = false
          })
        }).catch(res => {
        })

        getbankInfo(this.providerCode).then(res => {
          this.closingFrom.fromData = res.data.baseProviderSettle
          this.closingFrom.tableData = res.data.baseBankVo
          this.closingFrom.tableData.map((data, index) => {
            data.id = index + 1
            data.isSet = false
          })
        }).catch(res => {
        })
      }
      /*this.show = false //清空结点
      this.$nextTick(() => {
        this.show = true  //还原结点
      })*/
      //
    },
    changeAnnexFlag() {
      this.annexupload = false
    },


    // 校验数据
    validateForm() {
      let flag = null
      this.$refs['baseForm'].validate(valid => {
        if (valid) {
          flag = true
        } else {
          flag = false
        }
      })
      return flag
    },


    //提交审核
    submitHandle() {

      let flag = this.$refs['departmentInfo'].validateForm() && this.$refs['serviceInfo'].validateForm() && this.$refs['reserveInfo'].validateForm()
        && this.$refs['contactInfo'].validateForm() && this.$refs['closingInfo'].validateForm();

      if (this.validateForm() && flag) {
        //
        let allData = new Object();

        let departmentFromData = this.$refs.departmentInfo.departmentFrom
        let serviceFormData = this.$refs.serviceInfo.serviceForm
        let reserveInfoFormData = this.$refs.reserveInfo.reserveInfoForm
        let contactInfoFromData = this.$refs.contactInfo.contactInfoFrom
        let closingFromData = this.$refs.closingInfo.closingFrom
        allData.baseInfo = this.baseForm
        allData.departmentInfo = departmentFromData
        allData.serviceInfo = serviceFormData
        allData.reserveInfo = reserveInfoFormData
        allData.contactInfo = contactInfoFromData
        allData.closingInfo = closingFromData
        //调用提交审核的接口
        checkUp(allData).then(res => {
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

    },
    //其他机构重置
    otherResetForm() {
      this.$refs['otherBaseInfo'].resetFrom()
      this.$refs['otherServiceInfo'].resetFrom()
      this.$refs['otherReserveInfo'].resetFrom()
      this.$refs['otherClosingInfo'].resetFrom()
    },
    otherSubmitHandle() {
      //调用保存的接口 基本信息

    },


    goBack() {
      this.$router.go(-1);
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


/*修改标签页的字体*/
/deep/ .el-tabs__item {
  font-size: 20px;
  font-weight: 400;
  color: #000000;
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


</style>
