<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>{{selectDictLabel(businessTypeOptions, divTitle.split('-')[0])+'-'+selectDictLabel(serviceItemOptions, divTitle.split('-')[1])}}</span>
    </div>
    <el-form ref="ruleForm" :model="acceptForm" style="padding-bottom: 30px;" label-width="100px" :disabled="isDisabled"
             label-position="right" size="mini">
      <el-row>
        <el-form-item label="受理渠道：" prop="channelCode" class=".to_right">
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
          <el-form-item label="服务项目：" prop="serviceItem" class=".to_right">
            <el-select v-model="acceptForm.serviceItem" class="item-width" placeholder="请选择" controls-position="right" :min="0">
              <el-option v-for="item in serviceItemOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来电人电话：" prop="mobilePhone" class=".to_right">
            <el-input v-model="acceptForm.callPerson.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="优先级：" prop="priorityLevel" class=".to_right">
            <el-select v-model="acceptForm.priorityLevel" class="item-width" placeholder="请选择">
              <el-option v-for="item in priorityOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="来电人：" prop="name" class=".to_right">
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
        <el-col :span="8">
          <el-form-item label="联系人：" prop="lxperson">
            <el-input v-model="acceptForm.contactsPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
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
        <el-col :span="8">
          <el-form-item label="联系人语言：" prop="language">
            <el-select v-model="acceptForm.contactsPerson.language" class="item-width" placeholder="请选择">
              <el-option v-for="item in communicationLanguageOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>

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
        <el-col :span="8">
          <el-form-item label="出单机构：" prop="organCode">
            <el-select v-model="acceptForm.organCode" class="item-width" placeholder="请选择">
              <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
<!--          <el-form-item label="是否涉及银行转账" prop="bank" >-->
<!--            <el-radio-group v-model="acceptInfo.acceptor">-->
<!--              <el-radio   :label="1">是</el-radio>-->
<!--              <el-radio   :label="2">否</el-radio>-->

<!--            </el-radio-group>-->
<!--          </el-form-item>-->
        </el-col>
      </el-row>
<!--      <el-row>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="开户行：" v-show="ruleForm.bank=='1'" prop="bankaa">-->
<!--            <el-input size="mini" v-model="ruleForm.bankaa"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="开户地：" v-show="ruleForm.bank=='1'" prop="bankbb">-->
<!--            <el-input size="mini" v-model="ruleForm.bankbb"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="账号：" v-show="ruleForm.bank=='1'" prop="bankcc">-->
<!--            <el-input size="mini" v-model="ruleForm.bankcc"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->

<!--        <el-col :span="8">-->
<!--          <el-form-item label="户名：" v-show="ruleForm.bank=='1'" prop="bankdd">-->
<!--            <el-input size="mini" v-model="ruleForm.bankdd"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--      </el-row>-->
      <el-form-item label="业务内容：" prop="textarea">
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="acceptInfo.content">
        </el-input>
      </el-form-item>
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
.info_span {
  font-size: 13px;
}

.item-width {
  width: 160px;
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

/deep/ .el-radio{
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

