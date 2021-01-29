<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane :label="label.label01" name="01" v-if="hospShow">
        <!--基本信息-->
        <div >
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>基本信息</span>
              <span style="float: right;">
          <el-button size="mini" type="primary" @click="baseFormSaveHandle">保存</el-button>
        </span>
            </div>
            <el-form ref="baseForm" :rules="baseFormRules" :model="baseForm"
                     label-width="170px" size="mini" >
              <el-row>
                <el-col :span="8">
                  <el-form-item label="医院编码：" prop="providerCode">
                    <el-input v-model="baseForm.providerCode" class="item-width" clearable disabled size="mini"
                              placeholder="系统自动生成"/>
                  </el-form-item>
                </el-col>
                <el-col :span="10">
                  <el-form-item label="是否互联网医院：" prop="interHosp">
                    <el-select v-model="baseForm.interHosp" class="item-width" placeholder="请选择(默认否)" clearable>
                      <el-option v-for="item in inter_hospOptions" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.dictValue"/>

                    </el-select>

                    <i class="el-icon-warning-outline" v-bind:title="message"></i>
                  </el-form-item>
                </el-col>

              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="中文展示名称：" prop="chname1">
                    <el-input v-model="baseForm.chname1" class="item-width" clearable size="mini" placeholder="请输入" @blur="claimHospitalName"
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
                  <el-form-item label="省市区：" prop="address" >
                    <el-cascader clearable
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
                    <el-select v-model="baseForm.firstAttribute" class="item-width" placeholder="请选择" clearable @change="changeSecondAttribute">
                      <el-option v-for="item in first_attributeOptions" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.dictValue"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="二级属性：" prop="secondAttribute">
                    <el-select v-model="baseForm.secondAttribute" class="item-width" placeholder="请选择" clearable>
                      <el-option v-for="item in secondAttributeOptions" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.dictValue"/>
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

                      <el-option v-for="item in comprehensive_typeOptions" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.dictValue"/>
                      <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                    </el-select>
                    <!--选择专科时  显示出来-->

                    <el-select v-if="typeShow" v-model="baseForm.type2" class="item-width" placeholder="请选择" clearable
                               style="width: 120px"
                               multiple>
                      <el-option v-for="item in comprehensive_subtypeOptions" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.dictValue"/>
                      <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                    </el-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item label="曾用名：" prop="usedname">
                    <el-input v-model="baseForm.usedname" class="item-width" clearable size="mini" placeholder="请输入"
                              maxlength="50"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="邮编：" prop="zipcode">
                    <el-input v-model="baseForm.zipcode" class="item-width" clearable size="mini" placeholder="请输入"/>
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
                  <el-form-item label="传真：" prop="portraiture">
                    <el-input v-model="baseForm.portraiture" class="item-width" clearable size="mini"
                              placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="理赔医院代码：" prop="claimHospitalCode">
                    <el-input v-model="baseForm.claimHospitalCode" class="item-width" clearable size="mini" :disabled="true"
                              placeholder="系统自动生成"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="理赔医院名称：" prop="claimHospitalName">
                    <el-input v-model="baseForm.claimHospitalName" class="item-width" clearable size="mini"
                              placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="床位数：" prop="beds">
                    <el-input v-model="baseForm.beds" class="item-width" clearable size="mini" placeholder="请输入" />
                  </el-form-item>
                </el-col>
                <el-col>
                  <el-form-item label="医院特色：" prop="hospitalCharacteristics" >
                    <div>
                      <div>
                        <el-col :span="8">
                          <el-form-item label="是否国际部：" prop="international">
                            <el-radio-group v-model="baseForm.international">
                              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions " >{{item.dictLabel}}</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否特需部：" prop="special">
                            <el-radio-group v-model="baseForm.special">
                              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions " >{{item.dictLabel}}</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                        <el-col :span="8">
                          <el-form-item label="是否部队医院：" prop="armyhosptial">
                            <el-radio-group v-model="baseForm.armyhosptial">
                              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions " >{{item.dictLabel}}</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>


                      </div>
                      <div>
                        <el-col :span="8">
                          <el-form-item label="是否有住院：" prop="inhosptial">
                            <el-radio-group v-model="baseForm.inhosptial" >
                              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions " >{{item.dictLabel}}</el-radio>
                            </el-radio-group>
                          </el-form-item>
                        </el-col>
                      <el-col :span="8">
                        <el-form-item label="是否社保定点医院：" prop="flag">
                          <el-radio-group v-model="baseForm.flag">
                            <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions " >{{item.dictLabel}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </el-col>
                      <el-col :span="8">
                        <el-form-item label="是否康复医院：" prop="rehabilitationHospital">
                          <el-radio-group v-model="baseForm.rehabilitationHospital">
                            <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions " >{{item.dictLabel}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </el-col>
                    </div>
                    </div>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="状态：" prop="bussinessStatus">
                    <el-select v-model="baseForm.bussinessStatus" class="item-width" placeholder="请选择" clearable disabled>
                      <el-option v-for="item in bussiness_statusOptions" :label="item.dictLabel" :value="item.dictValue"
                                 :key="item.dictValue"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="经度：" prop="longitude">
                    <el-input v-model="baseForm.longitude" class="item-width" clearable size="mini" placeholder="请输入" />
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="纬度：" prop="latitude">
                    <el-input v-model="baseForm.latitude" class="item-width" clearable size="mini" placeholder="请输入" />
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
        <div >
          <department-info :providerCode="providerCode" :disabledFlag="disabledFlag" ref="departmentInfo"  :isAdd="isAdd"  :dictList="dictList"
                           :departmentForm="departmentForm"
                           :departmentTableShow="departmentTableShow" :status="activeName"></department-info>

        </div>

        <!--服务信息-->
        <div >
          <service-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="serviceInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                        :propServiceForm="serviceForm" :hospContractSaveFlag="hospContractSaveFlag" :hospContactFormData="hospContactFormData"></service-info>
        </div>


        <!--风控信息-->
        <!--                  <div class="app-container">
                            <risk-control-info :disabledFlag="disabledFlag" :hospitalcode="providercode" > </risk-control-info>
                          </div>-->
        <!--预约信息-->
        <div >
          <reserve-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="reserveInfo" :isAdd="isAdd" :status="activeName"
                        :reserveInfoForm="reserveInfoForm" v-if="flag"></reserve-info>
        </div>

        <!--联系信息-->
        <div >
          <contact-info :disabledFlag="disabledFlag" :supplierCode="providerCode" ref="contactInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                        :contactInfoForm="contactInfoForm" :contactInfoTableShow="contactInfoTableShow"></contact-info>
        </div>
        <!--结算信息-->
        <div >
          <closing-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="closingInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                        :closingTableShow="closingTableShow" :annexupload="annexupload"
                        :closingFrom="closingFrom"></closing-info>
        </div>
        <!--附件信息-->
        <!--        <div class="app-container">
                  <annex-info :disabledFlag="disabledFlag" :suppliercode="providerCode" @upload="changeAnnexFlag"></annex-info>
                </div>-->

        <div
          style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;float: right; ">
          <el-button size="mini" type="primary" @click="submitHandle">提交审核</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
          <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
        </div>


      </el-tab-pane>
      <el-tab-pane :label="label.label02" name="02" v-if="otherShow">

        <!--基本信息-->
        <div >
          <other-base-info :otherChname1="otherChname1" :otherEnname1="otherEnname1"    :providerCode="otherProviderCode" ref="otherBaseInfo" :status="activeName" :otherBaseForm="otherBaseForm" @baseSave="baseSaveChange" ></other-base-info>
        </div>
        <!--服务信息-->
        <div >
          <other-service-info :providerCode="otherProviderCode" ref="otherServiceInfo" :otherServiceForm="otherServiceForm"  :disabledFlag="otherDisabledFlag" :dictList="dictList"
                              :status="activeName"></other-service-info>
        </div>

        <!--预约信息-->
        <div >
          <other-reserve-info :providerCode="otherProviderCode" ref="otherReserveInfo" :otherReserveInfoForm="otherReserveInfoForm" :disabledFlag="otherDisabledFlag"
                              :status="activeName" v-if="otherReserveInfoShow"></other-reserve-info>
        </div>

        <!--联系信息-->
        <div >
          <contact-info :disabledFlag="otherDisabledFlag" :supplierCode="otherProviderCode" ref="otherContactInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                        :contactInfoForm="otherContactInfoForm" :contactInfoTableShow="contactInfoTableShow"></contact-info>
        </div>

        <!--结算信息-->
        <div >
          <closing-info :closingFrom="otherClosingFrom" :closingTableShow="closingTableShow" :status="activeName" :providerCode="otherProviderCode" :disabledFlag="otherDisabledFlag" :dictList="dictList"
                        ref="otherClosingInfo"></closing-info>
        </div>


        <!--        <div class="app-container">
                  <annex-info></annex-info>
                </div>-->


        <div
          style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;float: right; ">

          <el-button size="mini" type="primary" @click="otherResetForm">重置</el-button>
          <el-button size="mini" type="primary" @click="otherSaveHandle">保存</el-button>
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
  getcontactsInfo, getbankInfo, getAddress, checkfield, updateStatus, getNewtworktypeList, getList
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


let dictss = [{dictType: 'inter_hosp'},{dictType: 'virtual_org'},{dictType: 'first_attribute'},{dictType: 'second_attribute_a'},
  {dictType: 'second_attribute_b'},{dictType: 'comprehensive_type'},{dictType: 'yes_or_no'},{dictType: 'visiting_type'},
  {dictType: 'spprocurement_flag'},{dictType: 'classification'},{dictType: 'cooperationStatus_flag'},{dictType: 'servicelocator_flag'},
  {dictType: 'speciallanguage'},{dictType: 'networkHospitalType'},{dictType: 'type'},{dictType: 'payment'},
  {dictType: 'classification_type'},{dictType: 'leadFlag'},{dictType: 'place_type'},{dictType: 'currency'},{dictType: 'accountacc_status'},
  {dictType: 'account_type_new'},{dictType: 'comprehensive_subtype'},{dictType: 'bussiness_status'}]
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
      if (!(this.baseForm.phone && this.baseForm.areacode)) {
        callback(new Error('电话和区号不能位空！'))
      } else {
        callback()
      }
    }

    const checkAddreess = (rules, value, callback) => {
      if (this.baseForm.address!=null && this.baseForm.address.length>0) {
        callback()
      } else {
        callback(new Error('省市区不能为空！'))
      }
    }


    const checkOtherName = (rules, value, callback) => {
      if (value) {
        if (this.copyOtherName !== value) {
          let query = {
            otherName: this.baseForm.otherName
          }
          //调用查询的接口
          checkfield(query).then(res => {
            if (res != null && res.code == '200' && res.data.length > 0) {
              callback(new Error('别名已存在'))
            } else {
              callback()
            }
          })
        } else {
          callback()
        }
      }
      else {
        callback()
      }

    }


    const checkChregister = (rules, value, callback) => {
      if (value) {
        if (this.copyChregister !== value) {
        let query = {
          chregister: this.baseForm.chregister
        }
        //调用查询的接口
        checkfield(query).then(res => {
          if (res != null && res.code == '200' && res.data.length>0) {
            callback(new Error('中文工商注册名称已存在'))
          } else {
            callback()
          }
        })
        } else {
          callback()
        }
      } else {
        callback()
      }

    }

    const checkEnregister = (rules, value, callback) => {
      if (value) {
        let query = {
          enregister: this.baseForm.enregister
        }
        if (this.copyEnregister !== value) {
        //调用查询的接口
        checkfield(query).then(res => {
          if (res != null && res.code == '200' && res.data.length>0) {
            callback(new Error('英文工商注册名称已存在'))
          } else {
            callback()
          }
        })
        } else {
          callback()
        }
      } else {
        callback()
      }

    }

    const checkUsedname = (rules, value, callback) => {
      if (value) {
        let query = {
          usedname: this.baseForm.usedname
        }
        if (this.copyUsedname !== value) {
        //调用查询的接口
        checkfield(query).then(res => {
          if (res != null && res.code == '200' && res.data.length>0) {
            callback(new Error('曾用名已存在'))
          } else {
            callback()
          }
        })} else {
          callback()
        }
      } else {
        callback()
      }

    }
    const checkEnname1 = (rules, value, callback) => {
      if (value) {
        let query = {
          enname1: this.baseForm.enname1
        }
        if (this.copyEnname1 !== value) {
        //调用查询的接口
        checkfield(query).then(res => {
          if (res != null && res.code == '200' && res.data.length>0) {
            callback(new Error('英文展示名称已存在'))
          } else {
            callback()
          }
        })} else {
          callback()
        }
      } else {
        callback()
      }

    }


    const checkClaimHospitalName = (rules, value, callback) => {
      if (value) {
        let query = {
          claimHospitalName: this.baseForm.claimHospitalName
        }
        if (this.copyClaimHospitalName !== value) {
        //调用查询的接口
        checkfield(query).then(res => {
          if (res != null && res.code == '200' && res.data.length>0) {
            callback(new Error('理赔医院已存在'))
          } else {
            callback()
          }
        })
        } else {
          callback()
        }
      } else {
        callback(new Error('理赔医院不能为空'))


      }

    }
    const checkChname1 = (rules, value, callback) => {
      if (value) {
        let query = {
          chname1: this.baseForm.chname1
        }
        if (this.copyChname1 !== value) {
        //调用查询的接口
        checkfield(query).then(res => {
          if (res != null && res.code == '200' && res.data.length>0) {
            callback(new Error('中文展示名称已存在'))
          } else {
            callback()
          }
        })
        } else {
          callback()
        }
      } else {
        callback(new Error('中文展示名称不能为空'))

      }

    }
    const checkLongitude = (rules, value, callback) => {
      if(value){
        let reg= /^(\-|\+?)\d+(\.\d+)?$/
        if (!reg.test(value)) {
          callback(new Error("只能输入数字"));
        }
        else {
          callback()
        }
      }
      else {
        callback()
      }

    }

    const checkLatitude = (rules, value, callback) => {
      if(value){
        let reg= /^(\-|\+?)\d+(\.\d+)?$/
        if (!reg.test(value)) {
          callback(new Error("只能输入数字"));
        }
        else {
          callback()
        }
      }
      else {
        callback()
      }

    }
    const checkBeds = (rules, value, callback) => {
      let reg= /^(\d+|\d+\.)$/
      if (this.baseForm.inhosptial == '01') {
        if (!this.baseForm.beds) {
          callback(new Error('床位数不能位空！'))
        }
        else {
          if (value < 0) {
            callback(new Error("只能输入数字"));
          } else if (!reg.test(value)) {
            callback(new Error("只能输入数字"));
          } else {
            callback();
          }
        }
      } else {
        if(this.baseForm.beds) {
          if (value < 0) {
            callback(new Error("只能输入数字"));
          } else if (!reg.test(value)) {
            callback(new Error("只能输入数字"));
          } else {
            callback();
          }
        }
        else {
          callback();
        }
      }
    }
    const checkChaddreess= (rules, value, callback) => {
      if (this.baseForm.interHosp == '02') {
        if (!this.baseForm.chaddreess) {
          callback(new Error('中文地址不能位空！'))
        }
        else{
          callback()
        }
      } else {
        callback()
      }
    }

    return {
      otherChname1: '',
      otherEnname1: '',
      copyChname1: '',
      copyEnname1: '',
      copyChregister:'',
      copyEnregister:'',
      copyUsedname:'',
      copyClaimHospitalName:'',
      copyOtherName:'',
      hospContactFormData: {},
      isAdd: undefined,
      flag: false,
      otherReserveInfoShow: false,
      hospitalNetworkType:'',
      message: '互联网医院选择否,中文地址必填！',
      typeShow: false,
      annexupload: undefined,
      closingTableShow: undefined,
      departmentTableShow: undefined,
      contactInfoTableShow: undefined,
      otherBaseForm: {
        providerCode: undefined,
        longitude: undefined,
        latitude: undefined,
        chname1: undefined,
        enname1: undefined,
        website: undefined,
        chregister: undefined,
        enregister: undefined,
        areacode: undefined,
        phone: undefined,
        portraiture: undefined,
        email: undefined,
        zipcode: undefined,
        continent: undefined,
        country: undefined,
        address: [],
        bussinessStatus: '01',
        chaddreess: undefined,
        enaddress: undefined,
        landmarks: undefined,
        carpark: undefined,
        transitroute: undefined,
        introduction: undefined,
        chremarks: undefined,
        enremarks: undefined
      },
      otherServiceForm: {
        grade: undefined,
        category: undefined,
        emil: undefined,
        continentFlag: undefined,
        continent: undefined,
        officialwebsite: undefined,
        sellingpoint: undefined,
        preauthsendway: undefined,
        foreignnote: undefined,
        internalnote: undefined,
        servicelocator: [],
        speciallanguage: [],
        cooperationStatus: '01',
        virtualOrg: '02',
        displaynetwork: '02'

      },
      otherReserveInfoForm: {
        hotline: undefined,
        leadTime: undefined,
        appointmentCall: undefined,
        phone: undefined,
        mail: undefined,
        fax: undefined,
        fastmailRemark: undefined,
        inviteRemark: undefined,
        holidayTime: undefined,
        hotline2: undefined,
        confirmDeliverytime: undefined,
        straightknotflag: undefined,
        leadFlag: undefined,
        policiesOpen: undefined,
        nondirect: undefined,
        makeappointment: undefined,
        vipappointment: undefined,
        outpatientAddress: undefined,
        appointreminders: undefined,
        clinicmedicalrecord: undefined,
        medicalrecords: undefined,
        sendType: [],
        sendWay: []
      },
      otherClosingFrom: {
        baseProviderSettle: {
          currency: undefined,
          claimFlag: undefined,
          effectTime: undefined,
          noticeDay: undefined
        },
        baseBankVo: []
      },
      closingFrom: {
        baseProviderSettle: {
          currency: undefined,
          claimFlag: undefined,
          effectTime: undefined,
          noticeDay: undefined
        },
        baseBankVo: []
      },
      contactInfoForm: {
        contacts: []
      },
      otherContactInfoForm:{
        contacts: []
      },
      reserveInfoForm: {

        appointmentCall: undefined,
        outpatientInfo: undefined,
        hotline: undefined,

        hospitalizeDay: undefined,
        outpatientDay: undefined,
        confirmDeliverytime: undefined,
        holidayTime: undefined,
        phone: undefined,
        mail: undefined,
        fax: undefined,
        fastmailRemark: undefined,
        inviteRemark: undefined,
        receptionFlag: undefined,
        selfpaymentFlag: undefined,
        continentFlag: undefined,
        continent: undefined,
        workremark: undefined,

        saturdayremark: undefined,

        sundayremark: undefined,
        payment: undefined,
        nondirectcustomer: undefined,
        emergencycallremark: undefined,
        nightemergencystarttime: undefined,
        nightemergencyendtime: undefined,
        nightemergencyremark: undefined,
        straightknotflag: undefined,
        makeappointment: undefined,
        vipappointment: undefined,
        leadFlag: undefined,
        outpatientAddress: undefined,
        appointreminders: undefined,
        preauthsendway: undefined,
        clinicmedicalrecord: undefined,
        medicalrecords: undefined,
        hospitaldeposit: undefined,
        emergencyinfo: undefined,
        remarks: undefined,
        outpatientearly: [],
        sendType: [],
        sendWay: ['02'],
        interlocking: '02',
        workstarttime: '00:00',
        workendtime: '00:00',
        saturdaystarttime: '00:00',
        saturdayendtime: '00:00',
        sundaystarttime: '00:00',
        sundayendtime: '00:00',
        emergencycallstarttime: '00:00',
        emergencycallendtime: '24:00'
      },
      departmentForm: {
        form: []
      },

      serviceForm: {
        excludingFee: undefined,
        topten: undefined,
        specializedHospital: undefined,
        topthird: undefined,
        leadFlag: undefined,
        topfive: undefined,
        virtualOrg: undefined,
        category: undefined,
        classification: undefined,
        grade: undefined,
        cooperationStatus: undefined,
        displaynetwork: undefined,
        allcontopen: undefined,
        prehospital: undefined,
        treatmentDiscount: undefined,
        code: undefined,
        examineDiscount: undefined,
        type: undefined,
        bedDiscount: undefined,
        adviceNum: undefined,
        allowance: undefined,
        costs: undefined,
        discountinfo: undefined,
        specialdiscount: undefined,
        project: undefined,
        sellingpoint: undefined,
        foreignnote: undefined,
        internalnote: undefined,
        officialwebsite: '02',
        servicelocator: [],
        speciallanguage: [],
        networkHospitalType: []
      },
      show: true,
      otherDisabledFlag: undefined,
      disabledFlag: undefined,
      providerCode: '',
      otherProviderCode: '',
      activeName: '01',
      label: {
        label01: '医院',
        label02: '其他机构'
      },

      baseForm: {
        claimHospitalCode: undefined,
        interHosp: '02',
        bussinessStatus: '01',
        virtualOrg: undefined,
        chname1: undefined,
        enname1: undefined,
        otherName: undefined,
        chregister: undefined,
        enregister: undefined,
        website: undefined,
        continent: undefined,
        country: undefined,
        address: [],
        chaddreess: undefined,
        enaddress: undefined,
        firstAttribute: undefined,
        secondAttribute: undefined,
        areacode: undefined,
        phone: undefined,
        type: undefined,
        type2: undefined,
        usedname: undefined,
        zipcode: undefined,
        consultphone: undefined,
        email: undefined,
        portraiture: undefined,
        claimHospitalName: undefined,
        beds: undefined,
        international: undefined,
        special: undefined,
        specializedHospital: undefined,
        topthird: undefined,
        appointHospital: undefined,
        topfive: undefined,
        armyhosptial: undefined,
        inhosptial: undefined,
        topten: undefined,
        flag: undefined,
        rehabilitationHospital: undefined,
        longitude: undefined,
        latitude: undefined,
        landmarks: undefined,
        carpark: undefined,
        transitroute: undefined,
        introduction: undefined,
        chremarks: undefined,
        enremarks: undefined,
      },
      baseFormRules: {
        latitude:[{validator: checkLatitude, trigger: 'blur'}],
        longitude:[{validator: checkLongitude, trigger: 'blur'}],
        claimHospitalName: [{validator: checkClaimHospitalName, trigger: 'blur'}],
        beds: [{validator: checkBeds, trigger: 'blur'}],
        flag: [{required: true, message: '不能为空！', trigger: 'change'}],
        otherName: [{validator: checkOtherName, trigger: 'blur'}],
        chregister:[{validator: checkChregister, trigger: 'blur'}],
        enregister:[{validator: checkEnregister, trigger: 'blur'}],
        usedname:[{validator: checkUsedname, trigger: 'blur'}],
        enname1:[{validator: checkEnname1, trigger: 'blur'}],
        virtualOrg: [{required: true, message: '不能为空！', trigger: 'change'}],
        chname1: [{required: true, validator: checkChname1, trigger: 'blur'}],
        continent: [{required: true, message: '不能为空！', trigger: 'change'}],
        country: [{required: true, message: '不能为空！', trigger: 'change'}],
        address: [{validator: checkAddreess,required: true, trigger: 'change'}],
        chaddreess: [{validator: checkChaddreess, trigger: 'blur'}],
        firstAttribute: [{required: true, message: '不能为空！', trigger: 'change'}],
        secondAttribute: [{required: true, message: '不能为空！', trigger: 'change'}],
        type: [{required: true, message: '不能为空！', trigger: 'change'}],
        phone: [{validator: checkPhone, required: true, trigger: 'blur'}],
        international: [{required: true, message: '不能为空！', trigger: 'change'}],
        consultphone: [{required: true, message: '不能为空！', trigger: 'blur'}],
        special: [{required: true, message: '不能为空！', trigger: 'change'}],
        bussinessStatus: [{required: true, message: '不能为空！', trigger: 'blur'}],
        introduction: [{required: true, message: '不能为空！', trigger: 'blur'}],
      },
      medicalTypeData: [],
      bussiness_statusOptions: [],

      regions: [],
      dictList: [],
      inter_hospOptions: [],
      virtual_orgOptions: [],
      first_attributeOptions: [],
      second_attribute_aOptions: [],
      second_attribute_bOptions: [],
      comprehensive_typeOptions: [],
      secondAttributeOptions: [],
      yes_or_noOptions: [],
      comprehensive_subtypeOptions: [],
      hospContractSaveFlag: false,
      hospShow: true,
      otherShow:true



    }
  },
  async  created() {
     await this.getDictsList(dictss).then(response => {
       this.dictList = response.data

     })
    //调用查询维护的方法
/*    await  this.getNewtworktype()*/
   /* await getNewtworktypeList().then(res =>{
      this.medicalTypeData=res.rows
      if(this.medicalTypeData.filter(item => {
        return item.networktypeName= '网络医院'
      })[0].oldChoose=='02'){
        this.isNetHospital=true
      }
    }).catch(res=>{
      }
    )*/

    this.inter_hospOptions = this.dictList.find(item => {
      return item.dictType == 'inter_hosp'
    }).dictDate
    this.bussiness_statusOptions = this.dictList.find(item => {
      return item.dictType == 'bussiness_status'
    }).dictDate
    this.comprehensive_subtypeOptions = this.dictList.find(item => {
      return item.dictType == 'comprehensive_subtype'
    }).dictDate

    this.virtual_orgOptions = this.dictList.find(item => {
      return item.dictType == 'virtual_org'
    }).dictDate
    this.first_attributeOptions = this.dictList.find(item => {
      return item.dictType == 'first_attribute'
    }).dictDate
    this.second_attribute_aOptions = this.dictList.find(item => {
      return item.dictType == 'second_attribute_a'
    }).dictDate
    this.second_attribute_bOptions = this.dictList.find(item => {
      return item.dictType == 'second_attribute_b'
    }).dictDate
    this.comprehensive_typeOptions = this.dictList.find(item => {
      return item.dictType == 'comprehensive_type'
    }).dictDate
    this.yes_or_noOptions = this.dictList.find(item => {
      return item.dictType == 'yes_or_no'
    }).dictDate




     this.getAddressData()

  if(this.$route.query.status!=null && this.$route.query.status==='edit'){
    this.isAdd=false
    if(this.$route.query.orgflag=='01'){
      this.otherShow=false
    }
    if(this.$route.query.orgflag=='02'){
      this.hospShow=false
    }

  }
  else {
    this.isAdd=true


  }

    if (this.$route.query.providerCode) {


      this.disabledFlag = false
      this.otherDisabledFlag=false
       if( this.$route.query.orgflag=='01') {
         this.activeName=this.$route.query.orgflag
         this.otherReserveInfoShow=true
         this.providerCode = this.$route.query.providerCode
         let queryData={
           providerCode: this.providerCode,
           orgFlag: '01'
         }
         if(this.$route.query.hospContractSave!=null && this.$route.query.hospContractSave=='01'){
           this.hospContractSaveFlag=true
         }



      //调用服务机构基本信息查询
      getInfo(queryData).then(res => {
        this.baseForm = res.data
        this.baseForm.address=[]
        if(res.data.province) {
          this.baseForm.address[0] = res.data.province
        }
        if(res.data.city) {
          this.baseForm.address[1] = res.data.city
        }
        if(res.data.district) {
          this.baseForm.address[2] = res.data.district
        }
        this.copyChregister=this.baseForm.chregister
        this.copyUsedname=this.baseForm.usedname
        this.copyEnregister=this.baseForm.enregister
        this.copyClaimHospitalName=this.baseForm.claimHospitalName
        this.copyOtherName=this.baseForm.otherName
        this.copyChname1=this.baseForm.chname1
        this.copyEnname1=this.baseForm.enname1
        if(this.baseForm.firstAttribute=='01'){
          this.secondAttributeOptions=this.second_attribute_aOptions
        }
        else {
          this.secondAttributeOptions=this.second_attribute_bOptions
        }

        if(this.baseForm.type=='03'){
            this.typeShow=true

        }



      }).catch(res => {
      })
      //调用科室信息查询
      getdepInfo(queryData).then(res => {
        if(res.data!=null && res.data.length>0) {
          this.departmentForm.form = res.data
          this.departmentForm.form.map((data, index) => {
            data.id = index + 1
            data.isSet = false
          })
        }
      }).catch(res => {
      })

      getserviceInfo(queryData).then(res => {
        if(res.data !=null && res.data!='') {
          this.serviceForm = res.data
        }
        if(this.$route.query.formData!=null && this.$route.query.formData!=''){
          this.hospContactFormData=this.$route.query.formData
         /* this.serviceForm.treatmentDiscount=this.hospContactFormData.treatmentDiscount
          this.serviceForm.code=this.hospContactFormData.averageCost
          this.serviceForm.bedDiscount=this.hospContactFormData.bedDiscount
          this.serviceForm.type=this.hospContactFormData.type
          this.serviceForm.allowance=this.hospContactFormData.allowance
          this.serviceForm.advicenum=this.hospContactFormData.advicenum
          this.serviceForm.costs=this.hospContactFormData.costs
          this.serviceForm.excludingFee=this.hospContactFormData.averageCostExcept
          this.serviceForm.discountinfo=this.hospContactFormData.discountinfo
          this.serviceForm.specialDiscount=this.hospContactFormData.specialDiscount
          this.serviceForm.project=this.hospContactFormData.project*/


        }
      }).catch(res => {
      })

       getapplyInfo(queryData).then(res => {
         if(res.data!=null&& res.data!='')
         {
           this.reserveInfoForm = res.data
         }
        this.flag=true
      }).catch(res => {
      })

      getcontactsInfo(queryData).then(res => {
        if(res.data!=null && res.data.length>0) {
          this.contactInfoForm.contacts = res.data
          this.contactInfoForm.contacts.map((data, index) => {
            data.id = index + 1
            data.isSet = false
          })
        }
      }).catch(res => {
      })
      getbankInfo(queryData).then(res => {
        if(res.data.baseProviderSettle!=null && res.data.baseProviderSettle!='') {
          this.closingFrom.baseProviderSettle = res.data.baseProviderSettle
        }
        if(res.data.baseBankVo!=null && res.data.baseBankVo.length>0) {
          this.closingFrom.baseBankVo = res.data.baseBankVo
          this.closingFrom.baseBankVo.map((data, index) => {
            data.id = index + 1
            data.isSet = false
          })
        }
      }).catch(res => {
      })




       }
       else if(this.$route.query.orgflag=='02'){
         this.activeName=this.$route.query.orgflag
         this.otherProviderCode = this.$route.query.providerCode
         let queryData={
           providerCode:this.otherProviderCode,
           orgFlag: '02'
         }
         this.flag=true
         //调用基本信息查询的方法
         getInfo(queryData).then(res => {
           this.otherBaseForm = res.data
           this.otherBaseForm.address=[]
           if(res.data.province) {
             this.otherBaseForm.address[0] = res.data.province
           }
           if(res.data.city) {
             this.otherBaseForm.address[1] = res.data.city
           }
           if(res.data.district) {
             this.otherBaseForm.address[2] = res.data.district
           }
           this.otherChname1=this.otherBaseForm.chname1
           this.otherEnname1=this.otherBaseForm.enname1


         }).catch(res => {
         })
         //调用服务信息的方法

         getserviceInfo(queryData).then(res => {
         if(res.data!=null && res.data!='') {
           this.otherServiceForm = res.data
         }

         }).catch(res => {
         })
          //预约信息
         getapplyInfo(queryData).then(res => {
           if(res.data!=null && res.data!='') {
             this.otherReserveInfoForm = res.data
           }
           this.otherReserveInfoShow=true
         }).catch(res => {
         })

         //联系信息
         getcontactsInfo(queryData).then(res => {
           if(res.data!=null && res.data.length>0) {
             this.otherContactInfoForm.contacts = res.data
             this.otherContactInfoForm.contacts.map((data, index) => {
               data.id = index + 1
               data.isSet = false
             })
           }
         }).catch(res => {
         })

         //调用结算信息查询的方法
         getbankInfo(queryData).then(res => {
           if(res.data.baseProviderSettle!=null && res.data.baseProviderSettle!='') {
             this.otherClosingFrom.baseProviderSettle = res.data.baseProviderSettle
           }
           if(res.data.baseBankVo!=null && res.data.baseBankVo.length>0) {
             this.otherClosingFrom.baseBankVo = res.data.baseBankVo
             this.otherClosingFrom.baseBankVo.map((data, index) => {
               data.id = index + 1
               data.isSet = false
             })
           }

         }).catch(res => {
         })

       }
      this.departmentTableShow = true
      this.contactInfoTableShow = true
      this.closingTableShow = true

    } else {
      //医疗机构
      this.annexupload = true
      this.disabledFlag = true
      this.departmentTableShow = false
      this.closingTableShow = true
      this.flag=true
      this.contactInfoTableShow = true

      //其他机构
      this.otherDisabledFlag=true
      this.otherReserveInfoShow=true

    }





  },
  methods: {
    handleClick(){
      this.baseForm.beds = this.baseForm.beds.replace(/[^\w]/g, '');
    },
    /*getNewtworktype(){
      let query={
        providerCode: this.providerCode
      }
      console.log('---------------------')
      getNewtworktypeList(query).then(res =>{
        this.medicalTypeData=res.rows
        let flag=this.medicalTypeData.filter(item => {
          return item.networktypeName == '网络医院'
        })[0].oldChoose
        console.log(flag)
        if(flag=='02'){}
        if(this.medicalTypeData.filter(item => {
          return item.networktypeName == '网络医院'
        })[0].oldChoose=='02'){
          this.isNetHospital=true
        }
      }).catch(res=>{
        }
      )
      console.log(this.isNetHospital)

    },*/



    changeSecondAttribute(){
      this.baseForm.secondAttribute=''
      if(this.baseForm.firstAttribute=='01'){
        this.secondAttributeOptions=this.second_attribute_aOptions
      }
      else {
        this.secondAttributeOptions=this.second_attribute_bOptions
      }


    },



    baseSaveChange(){
      //服务商编码赋值
      this.otherProviderCode=this.$refs.otherBaseInfo.otherProviderCode

      //取消只读
      this.otherDisabledFlag=false
    },

    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },

    //同步
    claimHospitalName(){
      this.baseForm.claimHospitalName=this.baseForm.chname1
    },






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
        if (valid) {
          const subFormSearch = JSON.parse(JSON.stringify(this.baseForm))
          if (this.baseForm.address) {
            subFormSearch.province = this.baseForm.address[0]
            subFormSearch.district = this.baseForm.address[2]
            subFormSearch.city = this.baseForm.address[1]
          }
          subFormSearch.orgFlag = this.activeName
          //存在调用基本信息保存的接口
          if (this.providerCode) {
            updateInfo(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.copyChregister=this.baseForm.chregister
                this.copyUsedname=this.baseForm.usedname
                this.copyEnregister=this.baseForm.enregister
                this.copyClaimHospitalName=this.baseForm.claimHospitalName
                this.copyOtherName=this.baseForm.otherName
                this.copyChname1=this.baseForm.chname1
                this.copyEnname1=this.baseForm.enname1
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

            subFormSearch.orgFlag = this.activeName
            //不存在调用新增的接口
            addInfo(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.copyChregister=this.baseForm.chregister
                this.copyUsedname=this.baseForm.usedname
                this.copyEnregister=this.baseForm.enregister
                this.copyClaimHospitalName=this.baseForm.claimHospitalName
                this.copyOtherName=this.baseForm.otherName
                this.copyChname1=this.baseForm.chname1
                this.copyEnname1=this.baseForm.enname1

                this.providerCode = res.data.providerCode
                this.baseForm.providerCode = this.providerCode
                this.baseForm.claimHospitalCode = res.data.claimHospitalCode
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
        }
        else {
          this.$message.warning('基本信息必录项未必录')
        }

      })

    },
    //级联改变
    handleChange(value) {
    },

    //  重置
    resetForm() {
      this.$refs.baseForm.resetFields()
      this.baseForm.areacode=''
//  重置以后  调用查询方法
        this.$refs['departmentInfo'].resetForm();
        this.$refs['serviceInfo'].resetForm();
        this.$refs['reserveInfo'].resetForm();
        this.$refs['contactInfo'].resetForm();
        this.$refs['closingInfo'].resetForm();
        if(this.providerCode) {
          let queryData ={
            providerCode: this.providerCode,
            orgFlag:'01'
          }
          //调用服务机构基本信息查询
          getInfo(queryData).then(res => {
            this.baseForm = res.data
            this.baseForm.address = []
            if(res.data.province) {
              this.baseForm.address[0] = res.data.province
            }
            if(res.data.city) {
              this.baseForm.address[1] = res.data.city
            }
            if(res.data.district) {
              this.baseForm.address[2] = res.data.district
            }
            if (this.baseForm.firstAttribute == '01') {
              this.secondAttributeOptions = this.second_attribute_aOptions
            } else {
              this.secondAttributeOptions = this.second_attribute_bOptions
            }

            if (this.baseForm.type == '03') {
              this.typeShow = true

            }


          }).catch(res => {
          })
          //调用科室信息查询
          getdepInfo(queryData).then(res => {
            if (res.data != null && res.data.length > 0) {
              this.departmentForm.form = res.data
              this.departmentForm.form.map((data, index) => {
                data.id = index + 1
                data.isSet = false
              })
            }
          }).catch(res => {
          })

          getserviceInfo(queryData).then(res => {
            if (res.data != null && res.data != '') {
              this.serviceForm = res.data
            }
          }).catch(res => {
          })

          getapplyInfo(queryData).then(res => {
            if (res.data != null && res.data != '') {
              this.reserveInfoForm = res.data
            }
            this.flag = true
          }).catch(res => {
          })

          getcontactsInfo(queryData).then(res => {
            if (res.data != null && res.data.length > 0) {
              this.contactInfoForm.contacts = res.data
              this.contactInfoForm.contacts.map((data, index) => {
                data.id = index + 1
                data.isSet = false
              })
            }
          }).catch(res => {
          })
          getbankInfo(queryData).then(res => {
            if (res.data.baseProviderSettle != null && res.data.baseProviderSettle != '') {
              this.closingFrom.baseProviderSettle = res.data.baseProviderSettle
            }
            if (res.data.baseBankVo != null && res.data.baseBankVo.length > 0) {
              this.closingFrom.baseBankVo = res.data.baseBankVo
              this.closingFrom.baseBankVo.map((data, index) => {
                data.id = index + 1
                data.isSet = false
              })
            }
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

     /* let flag = this.$refs['departmentInfo'].validateForm() && this.$refs['serviceInfo'].validateForm() && this.$refs['reserveInfo'].validateForm()
        && this.$refs['contactInfo'].validateForm() && this.$refs['closingInfo'].validateForm();*/

      let baseInfoFlag=this.validateForm()
      let departmentInfoFlag=this.$refs['departmentInfo'].validateForm()
      let serviceInfoFlag=this.$refs['serviceInfo'].validateForm()
      let reserveInfoFlag=this.$refs['reserveInfo'].validateForm()
      let contactInfoFlag=this.$refs['contactInfo'].validateForm()
      let closingInfoFlag=this.$refs['closingInfo'].validateForm()

      if(baseInfoFlag){
        if(departmentInfoFlag=='01'){
          if(serviceInfoFlag=='01'){
            if(reserveInfoFlag){
              if(contactInfoFlag=='01'){
                if(closingInfoFlag=='01'){
                  let allData = new Object();
                  let departmentFormData = this.$refs.departmentInfo.departmentForm
                  departmentFormData.providerCode=this.providerCode
                  departmentFormData.orgFlag=this.activeName

                  let serviceFormData = this.$refs.serviceInfo.serviceForm
                  serviceFormData.providerCode=this.providerCode
                  serviceFormData.orgFlag=this.activeName

                  let reserveInfoFormData = this.$refs.reserveInfo.reserveInfoForm
                  reserveInfoFormData.providerCode=this.providerCode
                  reserveInfoFormData.orgFlag=this.activeName

                  let contactInfoFormData = this.$refs.contactInfo.contactInfoForm
                  contactInfoFormData.providerCode=this.providerCode
                  contactInfoFormData.orgFlag=this.activeName

                  let closingFromData = this.$refs.closingInfo.closingFrom
                  closingFromData.providerCode=this.providerCode
                  closingFromData.orgFlag=this.activeName
                  const baseFormData = JSON.parse(JSON.stringify(this.baseForm))
                  if (this.baseForm.address) {
                    baseFormData.province = this.baseForm.address[0]
                    baseFormData.district = this.baseForm.address[2]
                    baseFormData.city = this.baseForm.address[1]
                  }
                  baseFormData.orgFlag = this.activeName




                  allData.baseInfo = baseFormData

                  allData.departmentInfo = departmentFormData
                  allData.serviceInfo = serviceFormData
                  allData.reserveInfo = reserveInfoFormData
                  allData.contactInfo = contactInfoFormData
                  allData.closingInfo = closingFromData

                  //调用提交审核的接口
                  updateStatus(allData).then(res => {
                    if (res.code == '200') {
                      this.$message({
                        message: '提交成功！',
                        type: 'success',
                        center: true,
                        showClose: true
                      })
                      this.$store.dispatch("tagsView/delView", this.$route);
                      this.$router.push({
                        path: '/basic-info/medicalManage/medicalManage',
                      })
                    } else {
                      this.$message({
                        message: '提交失败!',
                        type: 'error',
                        center: true,
                        showClose: true
                      })
                    }
                  })

                }
                else if(closingInfoFlag=='02'){
                  this.$message.warning('至少添加一条结算账户信息！')
                }
                else {
                  this.$message.warning('结算信息必录项未必录！')
                }

              }
              else if(contactInfoFlag=='02'){
                this.$message.warning('至少添加一条联系人信息！')
              }
              else {
                this.$message.warning('联系信息必录项未必录！')
              }
            }
            else{
              this.$message.warning('预约信息必录项未必录！')
            }
          }
          else if(serviceInfoFlag=='02'){
            this.$message.warning('服务信息中合约信息未必录！')
          }
          else{
            this.$message.warning('服务信息必录项未必录！')
          }
        }
        else if(departmentInfoFlag=='02') {
          this.$message.warning('至少添加一条科室信息！')
        }
        else {
          this.$message.warning('科室信息必录项未必录')
        }

      }else{
        this.$message.warning('基本信息必录项未必录')
      }



 /*     if (this.validateForm() && flag) {
        //
        let allData = new Object();
        let departmentFormData = this.$refs.departmentInfo.departmentForm
        departmentFormData.providerCode=this.providerCode
        departmentFormData.orgFlag=this.activeName

        let serviceFormData = this.$refs.serviceInfo.serviceForm
        serviceFormData.providerCode=this.providerCode
        serviceFormData.orgFlag=this.activeName

        let reserveInfoFormData = this.$refs.reserveInfo.reserveInfoForm
        reserveInfoFormData.providerCode=this.providerCode
        reserveInfoFormData.orgFlag=this.activeName

        let contactInfoFormData = this.$refs.contactInfo.contactInfoForm
        contactInfoFormData.providerCode=this.providerCode
        contactInfoFormData.orgFlag=this.activeName

        let closingFromData = this.$refs.closingInfo.closingFrom
        closingFromData.providerCode=this.providerCode
        closingFromData.orgFlag=this.activeName
        const baseFormData = JSON.parse(JSON.stringify(this.baseForm))
        if (this.baseForm.address) {
          baseFormData.province = this.baseForm.address[0]
          baseFormData.district = this.baseForm.address[2]
          baseFormData.city = this.baseForm.address[1]
        }
        baseFormData.orgFlag = this.activeName




        allData.baseInfo = baseFormData

        allData.departmentInfo = departmentFormData
        allData.serviceInfo = serviceFormData
        allData.reserveInfo = reserveInfoFormData
        allData.contactInfo = contactInfoFormData
        allData.closingInfo = closingFromData

        //调用提交审核的接口
        updateStatus(allData).then(res => {
          if (res.code == '200') {
            this.$message({
              message: '提交成功！',
              type: 'success',
              center: true,
              showClose: true
            })
          } else {
            this.$message({
              message: '提交失败!',
              type: 'error',
              center: true,
              showClose: true
            })
          }
        })

      }
      else {
        this.$message.warning('必录项未必录！')
      }*/

    },
    //其他机构重置
    otherResetForm() {
      this.$refs['otherBaseInfo'].resetForm();
      this.$refs['otherServiceInfo'].resetForm();
      this.$refs['otherReserveInfo'].resetForm();
      this.$refs['otherClosingInfo'].resetForm();
      this.$refs['otherContactInfo'].resetForm();

      if(this.otherProviderCode) {
        //调用基本信息查询的方法
        let queryData={
          providerCode: this.otherProviderCode,
          orgFlag: '02'
        }
        getInfo(queryData).then(res => {
          this.otherBaseForm = res.data
          this.otherBaseForm.address=[]
          if(res.data.province) {
            this.otherBaseForm.address[0] = res.data.province
          }
          if(res.data.city) {
            this.otherBaseForm.address[1] = res.data.city
          }
          if(res.data.district) {
            this.otherBaseForm.address[2] = res.data.district
          }


        }).catch(res => {
        })
        //调用服务信息的方法

        getserviceInfo(queryData).then(res => {
          if(res.data!=null && res.data!='') {
            this.otherServiceForm = res.data
          }

        }).catch(res => {
        })
        //预约信息
        getapplyInfo(queryData).then(res => {
          if(res.data!=null && res.data!='') {
            this.otherReserveInfoForm = res.data
          }
          this.otherReserveInfoShow=true
        }).catch(res => {
        })

        //联系信息
        getcontactsInfo(queryData).then(res => {
          if(res.data!=null && res.data.length>0) {
            this.otherContactInfoForm.contacts = res.data
            this.otherContactInfoForm.contacts.map((data, index) => {
              data.id = index + 1
              data.isSet = false
            })
          }
        }).catch(res => {
        })

        //调用结算信息查询的方法
        getbankInfo(queryData).then(res => {
          if(res.data.baseProviderSettle!=null && res.data.baseProviderSettle!='') {
            this.otherClosingFrom.baseProviderSettle = res.data.baseProviderSettle
          }
          if(res.data.baseBankVo!=null && res.data.baseBankVo.length>0) {
            this.otherClosingFrom.baseBankVo = res.data.baseBankVo
            this.otherClosingFrom.baseBankVo.map((data, index) => {
              data.id = index + 1
              data.isSet = false
            })
          }

        }).catch(res => {
        })

      }
    },
    otherSaveHandle() {


      //调用保存的接口 基本信息
      let flag = this.$refs['otherBaseInfo'].validateForm() && this.$refs['otherServiceInfo'].validateForm() && this.$refs['otherReserveInfo'].validateForm()
        && this.$refs['otherClosingInfo'].validateForm() && this.$refs['otherContactInfo'].validateForm()

      let otherBaseInfoFlag=this.$refs['otherBaseInfo'].validateForm()

      let otherServiceInfoFlag=this.$refs['otherServiceInfo'].validateForm()
      let otherReserveInfoFlag=this.$refs['otherReserveInfo'].validateForm()
      let otherClosingInfoFlag=this.$refs['otherClosingInfo'].validateForm()
      let otherContactInfoFlag=this.$refs['otherContactInfo'].validateForm()

      if(otherBaseInfoFlag){
        if(otherServiceInfoFlag){
          if(otherReserveInfoFlag){
            if(otherContactInfoFlag=='01'){
              if(otherClosingInfoFlag=='01'){
                let otherAllData = new Object();
                let baseInfoData = this.$refs.otherBaseInfo.otherBaseForm
                const otherBaseInfoData = JSON.parse(JSON.stringify(baseInfoData))
                if (baseInfoData.address) {
                  otherBaseInfoData.province = baseInfoData.address[0]
                  otherBaseInfoData.district = baseInfoData.address[2]
                  otherBaseInfoData.city = baseInfoData.address[1]
                }
                otherBaseInfoData.orgFlag = this.activeName

                let otherServiceFormData = this.$refs.otherServiceInfo.otherServiceForm
                otherServiceFormData.orgFlag=this.activeName
                otherServiceFormData.providerCode=this.otherProviderCode


                let otherReserveInfoFormData = this.$refs.otherReserveInfo.otherReserveInfoForm

                otherReserveInfoFormData.orgFlag=this.activeName
                otherReserveInfoFormData.providerCode=this.otherProviderCode



                let otherClosingInfoData = this.$refs.otherClosingInfo.closingFrom
                otherClosingInfoData.orgFlag=this.activeName
                otherClosingInfoData.providerCode=this.otherProviderCode

                let otherContactInfoData = this.$refs.otherContactInfo.contactInfoForm
                otherContactInfoData.orgFlag=this.activeName
                otherContactInfoData.providerCode=this.otherProviderCode

                otherAllData.baseInfo=otherBaseInfoData
                otherAllData.serviceInfo = otherServiceFormData
                otherAllData.reserveInfo = otherReserveInfoFormData
                otherAllData.contactInfo = otherContactInfoData
                otherAllData.closingInfo = otherClosingInfoData


                //调用提交审核的接口
                updateStatus(otherAllData).then(res => {
                  if (res.code == '200') {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                    this.$store.dispatch("tagsView/delView", this.$route);
                    this.$router.push({
                      path: '/basic-info/medicalManage/medicalManage',
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
              else if(otherClosingInfoFlag=='02'){
                this.$message.warning('至少添加一条结算账户信息')
              }
              else {
                this.$message.warning('结算信息必录项未必录')
              }
            }
            else if(otherContactInfoFlag=='02'){
              this.$message.warning('至少添加一条联系人信息')
            }
            else {
              this.$message.warning('联系信息必录项未必录')
            }
          }
          else{
            this.$message.warning('预约信息必录项未必录')
          }
        }
        else {
          this.$message.warning('服务信息必录项未必录')
        }
      }
      else {
        this.$message.warning('基本信息必录项未必录')
      }



 /*     if(flag){
        let otherAllData = new Object();
        let baseInfoData = this.$refs.otherBaseInfo.otherBaseForm
        const otherBaseInfoData = JSON.parse(JSON.stringify(baseInfoData))
        if (baseInfoData.address) {
          otherBaseInfoData.province = baseInfoData.address[0]
          otherBaseInfoData.district = baseInfoData.address[2]
          otherBaseInfoData.city = baseInfoData.address[1]
        }
        otherBaseInfoData.orgFlag = this.activeName

        let otherServiceFormData = this.$refs.otherServiceInfo.otherServiceForm
        otherServiceFormData.orgFlag=this.activeName
        otherServiceFormData.providerCode=this.otherProviderCode


        let otherReserveInfoFormData = this.$refs.otherReserveInfo.otherReserveInfoForm

        otherReserveInfoFormData.orgFlag=this.activeName
        otherReserveInfoFormData.providerCode=this.otherProviderCode



        let otherClosingInfoData = this.$refs.otherClosingInfo.closingFrom
        otherClosingInfoData.orgFlag=this.activeName
        otherClosingInfoData.providerCode=this.otherProviderCode

        let otherContactInfoData = this.$refs.otherContactInfo.contactInfoForm
        otherContactInfoData.orgFlag=this.activeName
        otherContactInfoData.providerCode=this.otherProviderCode

        otherAllData.baseInfo=otherBaseInfoData
        otherAllData.serviceInfo = otherServiceFormData
        otherAllData.reserveInfo = otherReserveInfoFormData
        otherAllData.contactInfo = otherContactInfoData
        otherAllData.closingInfo = otherClosingInfoData


        //调用提交审核的接口
        updateStatus(otherAllData).then(res => {
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
      else {
        this.$message.warning('必录项未必录！')
      }*/
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
/deep/ .el-select__tags {
  width: 100%;
  display: inline-block;
  max-width: 200px;
  overflow: hidden;
  white-space: nowrap;
}



</style>
