<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span style="color: blue">{{selectDictLabel(businessTypeOptions, divTitle.split('-')[0])+'-'+selectDictLabel(serviceItemOptions, divTitle.split('-')[1])}}</span>
    </div>
    <el-form ref="ruleForm" :model="acceptForm" style="padding-bottom: 30px;" label-width="160px" :disabled="isDisabled"
             label-position="right" size="mini">
      <el-row>
        <el-form-item label="受理渠道：" prop="channelCode" >
          <el-radio-group v-model="acceptForm.channelCode">
            <el-radio
              v-for="dict in channelOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="电话中心业务流水号：" prop="mobilePhone" >
            <el-input v-model="acceptForm.callPerson.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来电号码：" prop="mobilePhone" >
            <el-input v-model="acceptForm.callPerson.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="受理渠道：" prop="serviceItem" >
            <el-select v-model="acceptForm.serviceItem" class="item-width" placeholder="请选择" controls-position="right" :min="0">
              <el-option v-for="item in serviceItemOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="优先级：" prop="priorityLevel" >
            <el-select v-model="acceptForm.priorityLevel" class="item-width" placeholder="请选择">
              <el-option v-for="item in priorityOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来电人姓名：" prop="name" >
            <el-input v-model="acceptForm.callPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来电人与被保人关系：" prop="priority">
            <el-select v-model="acceptForm.callRelationBy" class="item-width" placeholder="请选择">
              <el-option v-for="item in relationOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="联系人姓名：" prop="lxperson">
            <el-input v-model="acceptForm.contactsPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人性别：" prop="sex">
            <el-select v-model="acceptForm.contactsPerson.sex" class="item-width" placeholder="请选择">
              <el-option v-for="item in sexOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人与被保人关系：" prop="contactsRelationBy">
            <el-select v-model="acceptForm.contactsRelationBy" class="item-width" placeholder="请选择">
              <el-option v-for="item in relationOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="联系人语言：" prop="language">
            <el-select v-model="acceptForm.contactsPerson.language" class="item-width" placeholder="请选择">
              <el-option v-for="item in communicationLanguageOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人移动电话：" prop="mobilePhone">
            <el-input v-model="acceptForm.contactsPerson.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="E-MAIL：" prop="email">
            <el-input v-model="acceptForm.contactsPerson.email" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="16">
          <el-form-item label="联系人固定电话："  style="white-space: nowrap" prop="phone">
            国家区号+<el-input v-model="acceptForm.contactsPerson.homePhone" class="item-width" clearable style="width: 75px"/>
            区号<el-input v-model="acceptForm.contactsPerson.homePhone" class="item-width" clearable size="mini" style="width: 75px" maxlength="50"/>
            号码<el-input v-model="acceptForm.contactsPerson.homePhone" class="item-width" clearable size="mini" style="width: 120px" maxlength="50"/>
            分机号<el-input v-model="acceptForm.contactsPerson.homePhone" class="item-width" clearable size="mini" style="width: 75px" maxlength="50"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="出单机构：" prop="organCode">
            <el-select v-model="acceptForm.organCode" class="item-width" placeholder="请选择">
              <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="所在地：" prop="address">
            <el-input v-model="acceptForm.contactsPerson.email" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="16">
          <el-form-item label="业务内容：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="acceptInfo.content">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>

</template>
<script>
let dictss = [{dictType: 'cs_service_item'}
,{dictType: 'cs_channel'}
,{dictType: 'cs_priority'}
,{dictType: 'cs_sex'}
,{dictType: 'cs_communication_language'}
,{dictType: 'cs_organization'}
,{dictType: 'cs_relation'}
,{dictType: 'cs_business_type'}
,{dictType: 'cs_demand_item'}
]

export default {
  props: {
    isDisabled: {
      type:Boolean,
      default:false
    },
    acceptInfo: {
      type: Object,
      default: function (){
        return {}
      }
    }
  },
  watch: {
    acceptInfo: function (newValue){
      this.acceptData = newValue
      console.info("newValue");
      console.info(newValue);
      this.acceptForm.channelCode= this.acceptData.channelCode,
        this.acceptForm.serviceItem= this.acceptData.serviceItem,
        this.divTitle= this.acceptData.businessService,
        console.info(this.divTitle);
        this.acceptForm.callRelationBy= this.acceptData.callRelationBy,
        this.acceptForm.priorityLevel= this.acceptData.priorityLevel,
        this.acceptForm.contactsRelationBy= this.acceptData.contactsRelationBy,
        this.acceptForm.content= this.acceptData.content,
        this.acceptForm.organCode= this.acceptData.organCode,
        this.acceptForm.contactsPerson= this.acceptData.contactsPerson?this.acceptData.contactsPerson: {},
        this.acceptForm.callPerson= this.acceptData.callPerson?this.acceptData.callPerson: {}
    }
  },
  data() {
    return {
      dictList: [],
      serviceItemOptions: [],
      channelOptions: [],
      priorityOptions: [],
      sexOptions: [],
      communicationLanguageOptions: [],
      organizationOptions: [],
      relationOptions: [],
      businessTypeOptions:[],
      acceptData: {},
      divTitle:'',
      acceptForm: {
        businessService:undefined,
        channelCode: undefined,
        serviceItem: undefined,
        contactsPerson: {},
        callRelationBy: undefined,
        priorityLevel: undefined,
        callPerson: {},
        contactsRelationBy: undefined,
        content: undefined,
        organCode: undefined,
      },
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    console.info(this.dictList);
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
  },
  methods: {}
}
</script>
<style scoped>

.item-width {
  width: 160px;
}
.check_box {
  margin-top: 16px;
}
/deep/ .el-radio{
  /*display: block;*/
  line-height: 30px;
  /*white-space: normal;*/
  /*margin-right: 0;*/
}
</style>

