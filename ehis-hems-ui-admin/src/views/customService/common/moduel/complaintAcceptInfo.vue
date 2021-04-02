<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span style="color: blue">{{getTitles(acceptForm.businessService)}}</span>
    </div>
    <el-form ref="ruleForm" :model="acceptForm" style="padding-bottom: 30px;" label-width="170px"
             :rules="acceptFormRule"
             :disabled="isAcceptInfo || routerParams.status=='show'"
             label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="工单号：">
            <el-input v-model="acceptForm.workOrderNo" class="item-width" clearable size="mini" disabled/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="受理渠道：" prop="channelCode">
            <el-select v-model="acceptForm.channelCode" class="item-width" placeholder="">
              <el-option v-for="item in channelOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话中心业务流水号：" prop="callCenterId">
            <el-input disabled v-model="acceptForm.callCenterId" class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人姓名：" prop="name1">
            <el-input v-model="acceptForm.contactsPerson.name" class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="联系人性别：" prop="sex">
            <el-select v-model="acceptForm.contactsPerson.sex" class="item-width" placeholder="">
              <el-option v-for="item in sexOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人地址：" prop="address">
            <el-input v-model="acceptForm.contactsPerson.address" maxlength="2000" class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客户号：" prop="insuredPersonId">
            <el-input v-model="acceptForm.insuredPersonId" class="item-width" size="mini" disabled/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="优先级：" prop="priorityLevel">
            <el-select v-model="acceptForm.priorityLevel" class="item-width" placeholder="">
              <el-option v-for="item in priorityOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来电号码：" prop="mobilePhone1">
            <el-input v-model="acceptForm.callPerson.mobilePhone" maxlength="15"  class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="E-MAIL：" prop="email">
            <el-input v-model="acceptForm.email" class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="联系人移动电话：" prop="mobilePhone2">
            <el-input v-model="acceptForm.contactsPerson.mobilePhone" class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人语言：" prop="language">
            <el-select v-model="acceptForm.contactsPerson.language" class="item-width" placeholder="">
              <el-option v-for="item in communicationLanguageOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉人姓名：" prop="name2">
            <el-input v-model="acceptForm.complaintPerson.name" class="item-width" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="投诉人性别：" prop="sex">
            <el-select v-model="acceptForm.complaintPerson.sex" class="item-width" placeholder="">
              <el-option v-for="item in sexOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉人身份：" prop="identity">
            <el-select v-model="acceptForm.complaintPerson.identity" class="item-width" placeholder="">
              <el-option v-for="item in cs_identityOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <!--<el-col :span="8">
          <el-form-item label="家庭电话：" prop="homePhone">
            <el-input v-model="acceptForm.contactsPerson.homePhone" class="item-width" size="mini" />
          </el-form-item>
        </el-col>-->
      </el-row>
      <el-row>
        <el-col :span="3">
          <el-form-item label="家庭电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[0]">
            国家区号:+
            <el-input v-model="acceptForm.contactsPerson.homePhone1[0]" class="item-width" style="width: 60px" maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[1]">
            区号
            <el-input v-model="acceptForm.contactsPerson.homePhone1[1]" class="item-width" size="mini" style="width: 145px"
                      maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[2]">
            号码
            <el-input v-model="acceptForm.contactsPerson.homePhone1[2]" class="item-width" size="mini" style="width: 145px"
                      maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[3]">
            分机号
            <el-input v-model="acceptForm.contactsPerson.homePhone1[3]" class="item-width" size="mini" style="width: 145px"
                      maxlength="4"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3">
          <el-form-item label="办公电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[0]">
            国家区号:+
            <el-input v-model="acceptForm.contactsPerson.workPhone1[0]" class="item-width" style="width: 60px" maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[1]">
            区号
            <el-input v-model="acceptForm.contactsPerson.workPhone1[1]" class="item-width" size="mini" style="width: 145px"
                      maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[2]">
            号码
            <el-input v-model="acceptForm.contactsPerson.workPhone1[2]" class="item-width" size="mini" style="width: 145px"
                      maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[3]">
            分机号
            <el-input v-model="acceptForm.contactsPerson.workPhone1[3]" class="item-width" size="mini" style="width: 145px"
                      maxlength="4"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <!-- <el-col :span="8">
           <el-form-item label="办公电话：" prop="workPhone">
             <el-input v-model="acceptForm.contactsPerson.workPhone" class="item-width" size="mini" />
           </el-form-item>
         </el-col>-->
        <el-col :span="8">
          <el-form-item label="出单机构：" prop="organCode">
            <el-select v-model="acceptForm.organCode" class="item-width" placeholder="">
              <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否已劝解：" prop="persuasionFlag">
            <el-select v-model="acceptForm.persuasionFlag" class="item-width" placeholder="">
              <el-option v-for="item in cs_whether_flagOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-form-item label="投诉内容：" prop="content">
          <el-input
            type="textarea"
            :rows="2"
            maxlength="2000"
            v-model="acceptForm.content">
          </el-input>
        </el-form-item>
      </el-row>
    </el-form>
  </el-card>

</template>
<script>
  import {validEmail, validPhone} from '@/utils/validate'
  let dictss = [{dictType: 'cs_service_item'}
    , {dictType: 'cs_channel'}
    , {dictType: 'cs_priority'}
    , {dictType: 'cs_sex'}
    , {dictType: 'cs_communication_language'}
    , {dictType: 'cs_demand_item'}
    , {dictType: 'cs_organization'}
    , {dictType: 'cs_relation'}
    , {dictType: 'cs_business_type'}
    , {dictType: 'cs_identity'}
    , {dictType: 'cs_whether_flag'}
  ]

  export default {
    props: {
      routerParams: Object,
      isAcceptInfo: {
        type: Boolean,
        default: false
      },
      acceptInfo: {
        type: Object,
        default: function () {
          return {}
        }
      }
    },
    watch: {
      acceptInfo: function (newValue) {
        this.acceptData = newValue
        console.info("newValue");
        console.info(newValue);
        this.acceptForm.channelCode = newValue.channelCode;
        this.acceptForm.insuredPersonId = newValue.insuredPersonId;
        this.acceptForm.email = newValue.email;
        this.$set(this.acceptForm,'persuasionFlag',newValue.persuasionFlag);
        this.acceptForm.callCenterId = newValue.callCenterId;
        this.acceptForm.workOrderNo = newValue.workOrderNo;
        this.acceptForm.serviceItem = newValue.serviceItem;
        this.acceptForm.businessService = newValue.businessService;
        this.acceptForm.callRelationBy = newValue.callRelationBy;
        this.acceptForm.priorityLevel = newValue.priorityLevel;
        this.acceptForm.contactsRelationBy = newValue.contactsRelationBy;
        this.acceptForm.content = newValue.content;
        this.acceptForm.organCode = newValue.organCode;
        if (newValue.contactsPerson.homePhone1 == null) {
          newValue.contactsPerson.homePhone1 = ['', '', '', '']
        }
        if (newValue.contactsPerson.workPhone1 == null) {
          newValue.contactsPerson.workPhone1 = ['', '', '', '']
        }
        this.acceptForm.contactsPerson = newValue.contactsPerson ? newValue.contactsPerson : {}
        this.acceptForm.callPerson = newValue.callPerson ? newValue.callPerson : {}
        if (newValue.complaintPerson != null && newValue.complaintPerson != '') {
          this.acceptForm.complaintPerson = newValue.complaintPerson
        }

      }
    },
    data() {
      const checkName1= (rule, value, callback) => {
        if (this.acceptForm.contactsPerson && this.acceptForm.contactsPerson.name!='' && this.acceptForm.contactsPerson.name!=null && this.acceptForm.contactsPerson.name!=undefined) {
          callback()
        } else {
          callback(new Error('联系人姓名不能为空'))
        }
      }
      const checkName2= (rule, value, callback) => {
        if (this.acceptForm.complaintPerson && this.acceptForm.complaintPerson.name!='' && this.acceptForm.complaintPerson.name!=null && this.acceptForm.complaintPerson.name!=undefined) {
          callback()
        } else {
          callback(new Error('投诉人姓名不能为空'))
        }
      }
      const checkMobilePhone1= (rule, value, callback) => {
        if (this.acceptForm.callPerson && this.acceptForm.callPerson.mobilePhone!='' && this.acceptForm.callPerson.mobilePhone!=null && this.acceptForm.callPerson.mobilePhone!=undefined) {
          let str = this.acceptForm.callPerson.mobilePhone.replace(/\s/g, "")
          if (!validPhone(str)) {
            if (!/^\d+$/.test(str)) {
              callback(new Error('目前只支持录入数字'))
            } else {
              callback()
            }
          } else {
            callback()
          }
        } else {
          callback(new Error('来电号码不能为空'))
        }
      }
      const checkMobilePhone2= (rule, value, callback) => {
        if (this.acceptForm.contactsPerson && this.acceptForm.contactsPerson.mobilePhone!='' && this.acceptForm.contactsPerson.mobilePhone!=null && this.acceptForm.contactsPerson.mobilePhone!=undefined) {
          let str = this.acceptForm.contactsPerson.mobilePhone.replace(/\s/g, "")
          if (!validPhone(str)) {
            if (!/\d{3}-\d{8}|\d{4}-\d{7,8}/.test(str)) {
              callback(new Error('电话号码格式不合法，请检查'))
            } else {
              callback()
            }
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const validateEmail = (rule, value, callback) => {
        if ( this.acceptForm.email!='' && this.acceptForm.email!=null && this.acceptForm.email!=undefined) {
          if (!validEmail(this.acceptForm.email)) {
            callback(new Error('邮箱格式不合法，请检查'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkPhone1 = (rule, value, callback) => {
        if ( this.acceptForm.email!='' && this.acceptForm.email!=null && this.acceptForm.email!=undefined) {
          if (!validEmail(this.acceptForm.email)) {
            callback(new Error('邮箱格式不合法，请检查'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        dictList: [],
        serviceItemOptions: [],
        cs_whether_flagOptions: [],
        channelOptions: [],
        priorityOptions: [],
        cs_identityOptions: [],
        sexOptions: [],
        communicationLanguageOptions: [],
        organizationOptions: [],
        relationOptions: [],
        businessTypeOptions: [],
        acceptData: {},
        divTitle: '',
        acceptForm: {
          businessService: undefined,
          insuredPersonId: undefined,
          workOrderNo: undefined,
          channelCode: undefined,
          serviceItem: undefined,
          contactsPerson: {
            workPhone1: ['', '', '', ''],
            homePhone1: ['', '', '', '']
          },
          callRelationBy: undefined,
          priorityLevel: undefined,
          callPerson: {},
          contactsRelationBy: undefined,
          content: undefined,
          organCode: undefined,
          complaintPerson: {
            name: '',
            sex: '',
            identity: ''
          }
        },
        //字段校验
        acceptFormRule: {
          channelCode: [{required: true, message: '受理渠道不能为空', trigger: 'change'}],
          priorityLevel: [{required: true, message: '优先级不能为空', trigger: 'change'}],
          name1:{validator: checkName1, required: true, trigger: ['blur']},
          name2: {validator: checkName2, required: true, trigger: ['blur']},
          mobilePhone1: {validator: checkMobilePhone1, required: true, trigger: ['blur']},
          mobilePhone2: {validator: checkMobilePhone2, trigger: ['blur']},
          email: {validator: validateEmail, trigger: ['blur']},
        /*  phone1: {validator: checkPhone1, trigger: ['blur']},
          phone2: {validator: checkPhone2, trigger: ['blur']},*/
          organCode: [{required: true, message: '出单机构不能为空', trigger: 'change'}],
          content: [{required: true, message: '投诉内容不能为空', trigger: 'blur'}],
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
        }
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.serviceItemOptions = this.dictList.find(item => {
        return item.dictType === 'cs_demand_item'
      }).dictDate
      this.channelOptions = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.priorityOptions = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.communicationLanguageOptions = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.organizationOptions = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.relationOptions = this.dictList.find(item => {
        return item.dictType === 'cs_relation'
      }).dictDate
      this.businessTypeOptions = this.dictList.find(item => {
        return item.dictType === 'cs_business_type'
      }).dictDate
      this.cs_identityOptions = this.dictList.find(item => {
        return item.dictType === 'cs_identity'
      }).dictDate
      this.cs_whether_flagOptions = this.dictList.find(item => {
        return item.dictType === 'cs_whether_flag'
      }).dictDate
    },
    methods: {
      getTitles(value) {
        if (value != null && value != '' && value != undefined) {
          return this.selectDictLabel(this.businessTypeOptions, value.split('-')[0]) + '-' + this.selectDictLabel(this.serviceItemOptions, value.split('-')[1])
        }
      },
      checkForm() {
        let data=false
        this.$refs.ruleForm.validate((valid) => {
          if (valid) {
            data= true
          }else {
            data= false
          }
        })
        return data
      }
    }
  }
</script>
<style scoped>
  .info_span {
    font-size: 13px;
  }

  .item-width {
    width: 170px;
  }

  .el-tree /deep/ .el-tree-node__content {
    height: 30px;
  }

  .check_box {
    margin-top: 16px;
  }

  .collapse-card.el-card /deep/ .el-card__body {
    padding: 0;
  }

  .el-collapse /deep/ .el-collapse-item__header {
    background-color: #f8f8f8;
    padding: 0 10px;
  }

  .el-collapse /deep/ .el-collapse-item__wrap {
    padding: 0 10px;
  }

  .el-checkbox {
    margin-left: 20px;
  }

  .el-checkbox-group {
    margin-left: 20px;
  }

  .el-checkbox-group /deep/ .el-checkbox {
    display: block;
  }

  .to_right {
    width: 120px;
    text-align: right;
  }

  /deep/ .custom-tree-node {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }

  /deep/ .custom-tree-node span {
    flex: 1;
    display: inline-block;
    width: 80%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  /deep/ .el-radio {
    /*display: block;*/
    line-height: 30px;
    /*white-space: normal;*/
    /*margin-right: 0;*/
  }

</style>
<style>
  .el-tooltip__popper {
    max-width: 400px;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }

</style>

