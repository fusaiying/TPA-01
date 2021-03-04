<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane :label="label.label02" name="02">
        <!--基本信息-->
        <div >
          <other-base-info    :providerCode="otherProviderCode" ref="otherBaseInfo" :status="activeName" :otherBaseForm="otherBaseForm"  ></other-base-info>
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
          <hosp-contact-info :disabledFlag="otherDisabledFlag" :supplierCode="otherProviderCode" ref="otherContactInfo" :isAdd="isAdd" :dictList="dictList"
                             :contactInfoForm="otherContactInfoForm" :contactInfoTableShow="contactInfoTableShow"></hosp-contact-info>
<!--          <contact-info :disabledFlag="otherDisabledFlag" :supplierCode="otherProviderCode" ref="otherContactInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                        :contactInfoForm="otherContactInfoForm" :contactInfoTableShow="contactInfoTableShow"></contact-info>-->
        </div>
        <!--结算信息-->
        <div >
          <hosp-closing-info  :providerCode="otherProviderCode" ref="otherClosingInfo" :isAdd="isAdd" :dictList="dictList" :status="activeName"
                             :closingTableShow="closingTableShow" :annexupload="annexupload"
                             :closingFrom="otherClosingFrom"></hosp-closing-info>

<!--          <closing-info :closingFrom="otherClosingFrom" :closingTableShow="closingTableShow" :status="activeName" :providerCode="otherProviderCode" :disabledFlag="otherDisabledFlag" :dictList="dictList"
                        ref="otherClosingInfo"></closing-info>-->
        </div>
        <div
          style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  color: #303133;float: right; ">
          <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
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






import  hospContactInfo from '../hospitalReview/components/hospContactInfo'
import  hospClosingInfo from '../hospitalReview/components/hospClosingInfo'
import  otherBaseInfo from './otherView/otherBaseInfo'
import  otherReserveInfo from './otherView/otherReserveInfo'
import  otherServiceInfo from './otherView/otherServiceInfo'


let dictss = [{dictType: 'inter_hosp'},{dictType: 'virtual_org'},{dictType: 'first_attribute'},{dictType: 'second_attribute_a'},
  {dictType: 'second_attribute_b'},{dictType: 'comprehensive_type'},{dictType: 'yes_or_no'},{dictType: 'visiting_type'},
  {dictType: 'spprocurement_flag'},{dictType: 'classification'},{dictType: 'cooperationStatus_flag'},{dictType: 'servicelocator_flag'},
  {dictType: 'speciallanguage'},{dictType: 'networkHospitalType'},{dictType: 'type'},{dictType: 'payment'},
  {dictType: 'classification_type'},{dictType: 'leadFlag'},{dictType: 'place_type'},{dictType: 'currency'},{dictType: 'accountacc_status'},
  {dictType: 'account_type_new'},{dictType: 'comprehensive_subtype'},{dictType: 'bussiness_status'},{dictType: 'bussiness_status_review'}]

export default {
    components: {
      hospContactInfo,
      hospClosingInfo,
      otherBaseInfo,
      otherReserveInfo,
      otherServiceInfo
    },

  data() {


    return {
      isAdd: undefined,
      flag: false,
      otherReserveInfoShow: false,


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


      otherContactInfoForm:{
        contacts: []
      },

      show: true,
      otherDisabledFlag: true,
      otherProviderCode: '',
      activeName: '02',
      label: {
        label02: '其他机构'
      },



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




    }
  },
  async  created() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data

    })

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
    }
    else {
      this.isAdd=true
    }

    if (this.$route.query.providerCode) {

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

    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },




    //级联改变
    handleChange(value) {
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
.el-form-item ::v-deep label {
  font-weight: normal;
}


/*修改标签页的字体*/
::v-deep .el-tabs__item {
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
::v-deep .el-select__tags {
  width: 100%;
  display: inline-block;
  max-width: 200px;
  overflow: hidden;
  white-space: nowrap;
}


</style>
