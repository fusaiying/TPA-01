<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane :label="label.label01" name="01">
        <!--基本信息-->
        <div >
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>基本信息</span>

            </div>
            <el-form ref="baseForm"  :model="baseForm"  disabled
                     label-width="170px" size="mini" class="baseInfo_class">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="医院编码：" prop="providerCode">
                    <el-input v-model="baseForm.providerCode" class="item-width" clearable disabled size="mini"
                              placeholder="系统自动生成"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="是否互联网医院：" prop="interHosp">
                    <el-select v-model="baseForm.interHosp" class="item-width" placeholder="请选择" clearable>
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
                    <el-input v-model="baseForm.beds" class="item-width" clearable size="mini" placeholder="请输入"/>
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
        <div >
          <hosp-department-info :providerCode="providerCode" :disabledFlag="disabledFlag" ref="departmentInfo"  :isAdd="isAdd"  :dictList="dictList"
                                :departmentForm="departmentForm"
                                :departmentTableShow="departmentTableShow"  ></hosp-department-info>
        </div>

        <!--服务信息-->
        <div >
          <hosp-service-info :providerCode="providerCode" ref="serviceInfo" :isAdd="isAdd" :dictList="dictList"
                             :propServiceForm="serviceForm"></hosp-service-info>
        </div>


        <!--预约信息-->
        <div >
          <hosp-reserve-info :disabledFlag="disabledFlag" :providerCode="providerCode" ref="reserveInfo" :isAdd="isAdd"
                             :reserveInfoForm="reserveInfoForm" v-if="flag"></hosp-reserve-info>
        </div>

        <!--联系信息-->
        <div >
          <hosp-contact-info :disabledFlag="disabledFlag" :supplierCode="providerCode" ref="contactInfo" :isAdd="isAdd" :dictList="dictList"
                             :contactInfoForm="contactInfoForm" :contactInfoTableShow="contactInfoTableShow"></hosp-contact-info>
        </div>
        <!--结算信息-->
        <div >
          <hosp-closing-info  :providerCode="providerCode" ref="closingInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                             :closingTableShow="closingTableShow" :annexupload="annexupload"
                             :closingFrom="closingFrom"></hosp-closing-info>
        </div>
        <!--附件信息-->
                <div >
                  <annex-info :disabledFlag="annexFlag" :suppliercode="providerCode" :specialAnnexFlag="specialAnnexFlag"></annex-info>
                </div>

      <!--历史合约-->
<!--        <div class="app-container">
        <history-contract :contractLogTableData="contractLogTableData"></history-contract>
  </div>-->
        <!--审核-->
        <div >
         <audit  :providerCode="providerCode"></audit>
        </div>


      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {
  insertCheckInfo,
  getInfo,
  addInfo,
  updateInfo,
  getdepInfo,
  getserviceInfo,
  getapplyInfo,
  getcontactsInfo,
  getbankInfo,
  checkUp,
  getAddress,
  checkfield,
  getHistoryConlist,
  updateStatus,
  selectCheckInfo,
  getNewtworktypeList
} from '@/api/baseInfo/medicalManage.js'


import annexInfo from '../medicalManage/components/annexInfo'
import  historyContract from './components/historyContract'
import  audit from './components/audit'
import  hospServiceInfo from './components/hospServiceInfo'
import  hospDepartmentInfo from './components/hospDepartmentInfo'
import  hospReserveInfo from './components/hospReserveInfo'
import  hospContactInfo from './components/hospContactInfo'
import  hospClosingInfo from './components/hospClosingInfo'


let dictss = [{dictType: 'inter_hosp'},{dictType: 'virtual_org'},{dictType: 'first_attribute'},{dictType: 'second_attribute_a'},
  {dictType: 'second_attribute_b'},{dictType: 'comprehensive_type'},{dictType: 'yes_or_no'},{dictType: 'visiting_type'},
  {dictType: 'spprocurement_flag'},{dictType: 'classification'},{dictType: 'cooperationStatus_flag'},{dictType: 'servicelocator_flag'},
  {dictType: 'speciallanguage'},{dictType: 'networkHospitalType'},{dictType: 'type'},{dictType: 'payment'},
  {dictType: 'classification_type'},{dictType: 'leadFlag'},{dictType: 'place_type'},{dictType: 'currency'},{dictType: 'accountacc_status'},
  {dictType: 'account_type_new'},{dictType: 'comprehensive_subtype'},{dictType: 'bussiness_status'},{dictType: 'bussiness_status_review'}]

export default {
  components: {
    annexInfo,
    historyContract,
    audit,
    hospServiceInfo,
    hospDepartmentInfo,
    hospReserveInfo,
    hospContactInfo,
    hospClosingInfo

  },
  data() {


    return {

      specialAnnexFlag:true,
      annexFlag: false,
      contractLogTableData: [],
      isNetHospital:false,

      copyChregister:'',
      copyEnregister:'',
      copyUsedname:'',
      copyClaimHospitalName:'',
      copyOtherName:'',
      //是否网络医院的flag

      isAdd: undefined,
      flag: false,

      hospitalNetworkType:'',
      message: '互联网医院选择否,中文地址必填！',
      typeShow: false,
      annexupload: undefined,
      closingTableShow: undefined,
      departmentTableShow: undefined,
      contactInfoTableShow: undefined,
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

      disabledFlag: true,
      providerCode: '',

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
      comprehensive_subtypeOptions: []



    }
  },
  async created() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data

    })


    this.inter_hospOptions = this.dictList.find(item => {
      return item.dictType == 'inter_hosp'
    }).dictDate
    this.bussiness_statusOptions = this.dictList.find(item => {
      return item.dictType == 'bussiness_status_review'
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


    if (this.$route.query.providerCode) {
      this.isAdd = false
      this.providerCode = this.$route.query.providerCode
      let queryData={
        providerCode: this.providerCode,
        orgFlag: '01'
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

/*      //历史合约的接口
      getHistoryConlist(this.providerCode).then(res => {
        this.contractLogTableData = res.data
      }).catch(res => {
      })*/
   /*   //审核日志的接口
      selectCheckInfo(this.providerCode).then(res=> {
        this.reviewLogTableData=res.data
      })*/


    }
    if(this.baseForm.type=='03'){
      this.typeShow=true
    }

    this.departmentTableShow = true
    this.contactInfoTableShow = true
    this.closingTableShow = true


    //医疗机构
    this.annexupload = true
    this.flag = true
    this.isAdd = true


  },
  methods: {


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


    //级联改变
    handleChange(value) {
    },



    changeAnnexFlag() {
      this.annexupload = false
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
