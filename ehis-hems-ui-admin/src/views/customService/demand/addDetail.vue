<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider></el-divider>
      <el-form ref="personInfo" :model="baseInfo" label-width="180px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="保单号：">
              <span>{{ baseInfo.policyNo }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：">
              <span>{{ baseInfo.appName }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人证件号：">
              <span>{{ baseInfo.appName }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="投保人证件类型：">
              <span>{{ baseInfo.appName }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：">
              <span>{{ baseInfo.policyItemNo }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：">
              <span>{{ baseInfo.name }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别：">
              <span>{{selectDictLabel(rgtSex, baseInfo.sex)}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人出生日期：">
              <span>{{ baseInfo.birthday }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号：">
              <span>{{ baseInfo.idNo }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人证件类型：">
              <span>{{selectDictLabel(card_type, baseInfo.idType)}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话：">
              <span>{{ baseInfo.mobile }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期：">
              <span>{{ baseInfo.validStartDate }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期：">
              <span>{{ baseInfo.validStartDate }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保益生效日：">
              <span>{{ baseInfo.validStartDate }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保益满期日：">
              <span>{{ baseInfo.validEndDate }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员：">
              <span>{{ baseInfo.mainSolicit }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="主招揽业务员电话：">
              <span>{{ baseInfo.solicitPhone }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识：">
              <span>{{ baseInfo.vipFlag }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="是否UHCG会员：">
              <span>{{ baseInfo.uhcgFlag }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="与主保险人关系：">
              <span>{{ baseInfo.relationBy }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：">
              <span>{{ baseInfo.companyName }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码：">
              <span>{{ baseInfo.riskCodesStr }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称：">
              <span>{{ baseInfo.planCode }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日：">
              <span>{{ baseInfo.validStartDate }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日：">
              <span>{{ baseInfo.firstValidDate }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="AM(服务经理)：">
              <span>{{ baseInfo.acceptor }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="BD(销售经理)：">
              <span>{{ baseInfo.acceptor }}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="特定医院赔付比例：">
              <span>{{ baseInfo.specialRatio }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质：">
              <span>{{ baseInfo.insuredType }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源：">
              <span>{{ baseInfo.policyFrom }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">服务受理信息</span>
      </div>

      <el-form ref="ruleForm" :model="ruleForm" :rules="changeForm.rules" style="padding-bottom: 30px;"
               label-width="180px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：">
              <el-input v-model="ruleForm.workOrderNo" class="item-width" clearable size="mini" disabled placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="受理渠道：" prop="channelCode">
            <el-radio-group v-model="ruleForm.channelCode">
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
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="ruleForm.itemCode" class="item-width" placeholder="请选择" controls-position="right"
                         :min="0">
                <el-option v-for="item in cs_demand_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callMobilePhone">
              <el-input v-model="ruleForm.callMobilePhone" class="item-width" maxlength="15" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="ruleForm.priorityLevel" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人：" prop="callName">
              <el-input v-model="ruleForm.callName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人与被保人关系：" prop="priority">
              <el-select v-model="ruleForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人：" prop="contactsName">
              <el-input v-model="ruleForm.contactsName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="ContactsSex">
              <el-select v-model="ruleForm.contactsSex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人与被保人关系：" prop="contactsRelationBy">
              <el-select v-model="ruleForm.contactsRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="ContactsLanguage">
              <el-select v-model="ruleForm.contactsLanguage" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="contactsMobilePhone">
              <el-input v-model="ruleForm.contactsMobilePhone" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" clearable size="mini" placeholder="请输入"/>
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
          <el-col :span="3">
            <el-form-item label="联系人固定电话：" style="white-space: nowrap;" :inline="true" prop="contactsCountry">
              国家区号:+
              <el-input v-model="ruleForm.contactsCountry" class="item-width" style="width: 60px" maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsQuhao">
              区号
              <el-input v-model="ruleForm.contactsQuhao" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsNumber">
              号码
              <el-input v-model="ruleForm.contactsNumber" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsSecondNumber">
              分机号
              <el-input v-model="ruleForm.contactsSecondNumber" class="item-width" size="mini" style="width: 145px"
                        maxlength="4"/>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="16">
            <el-form-item label="业务内容：" prop="content">
              <el-input
                type="textarea"
                :rows="3"
                placeholder="请输入内容"
                v-model="ruleForm.content">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <el-row>
          <span style="color: blue">附件信息</span>
          <div style="text-align: right; margin-right: 1px;">
            <up-load ref="upload"></up-load>
            <el-button size="mini" type="primary" @click="upload()" disabled>上传附件</el-button>
          </div>
        </el-row>
        <el-divider/>
        <up-load :dialogVisable="dialogVisable"></up-load>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="policyNumber" label="附件名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="secondNumber" label="附件类型" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="center" label="上传人" show-overflow-tooltip/>
          <el-table-column prop="beInsuredName" align="center" label="上传时间 " show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="备注" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="download(scope.row)" disabled>下载</el-button>
            </template>
          </el-table-column>
        </el-table>

      </div>
      <div style="text-align: right; margin-right: 1px;">
        <!--<el-button type="primary" size="mini" @click="submit" :disabled="this.checkSubmitFlag == '01'">提交</el-button>-->
        <el-button type="primary" size="mini" @click="submit" >提交</el-button>
        <el-button type="primary" size="mini" @click="hiddenShow">关闭</el-button>
      </div>
    </el-card>


  </div>
</template>

<script>
import moment from 'moment'
import {addInsert} from '@/api/customService/demand'
import {policyInfoData} from '@/api/customService/common'
import upLoad from "../common/modul/upload";


let dictss = [
  {dictType: 'cs_relation'},
  {dictType: 'cs_demand_item'},
  {dictType: 'cs_sex'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_communication_language'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_channel'},
  {dictType: 'rgtSex'},
  {dictType: 'card_type'},
  ]

export default {
  components: {upLoad},
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  data() {
    // 表单校验根据Form 组件提供了表单验证的功能，只需要通过 rules 属性传入约定的验证规则，并将 Form-Item 的 prop 属性设置为需校验的字段名即可
    const rules_bank = {
      channelCode: [
        {required: true, message: "受理渠道不能为空", trigger: "blur"}
      ],
      itemCode: [
        {required: true, message: "服务项目不能为空", trigger: "blur"}
      ],
      priorityLevel: [
        {required: true, message: "优先级不能为空", trigger: "blur"}
      ],
      contactsName: [
        {required: true, message: "联系人不能为空", trigger: "blur"}
      ],
      contactsMobilePhone: [
        {required: true, message: "联系人移动电话不能为空", trigger: "blur"}
      ],
      organCode: [
        {required: true, message: "出单机构不能为空", trigger: "blur"}
      ],
      content: [
        {required: true, message: "业务内容不能为空", trigger: "blur"}
      ],
    };
    const rules_noBank = {
      channelCode: [
        {required: true, message: "受理渠道不能为空", trigger: "blur"}
      ],
      itemCode: [
        {required: true, message: "服务项目不能为空", trigger: "blur"}
      ],
      priorityLevel: [
        {required: true, message: "优先级不能为空", trigger: "blur"}
      ],
      contactsName: [
        {required: true, message: "联系人不能为空", trigger: "blur"}
      ],
      contactsRelationBy: [
        {required: true, message: "联系人与被保人关系不能为空", trigger: "blur"}
      ],
      contactsMobilePhone: [
        {required: true, message: "联系电话不能为空", trigger: "blur"},
        {required: true,
          message: "目前只支持中国大陆的手机号码",
          pattern: /^1[34578]\d{9}$/,//可以写正则表达式呦呦呦,
          trigger: "blur"},
      ],
      'E-MAIL': [
        {required: true, message: "Email不能为空", trigger: "blur"},
        {required: true,
          message: "请输入正确的格式",
          pattern:  /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/,//可以写正则表达式呦呦呦,
          trigger: "blur"},
      ],
      callMobilePhone: [
        //{required: false, message: "联系电话不能为空", trigger: "blur"},
        {required: false,
          message: "目前只支持录入数字",
          pattern: /^\d+$/,//可以写正则表达式呦呦呦,
          trigger: ['blur','change']},
      ],
      contactsCountry: [
        {required: false,
          message: "国家区号只能录入数字",
          pattern: /^\d*$/,
          trigger: ['change','blur']
        }
      ],
      contactsQuhao: [
        {required: false,
          message: "区号只能录入数字",
          pattern: /^\d*$/,
          trigger: ['change','blur']
        }
      ],
      contactsNumber: [
        {required: false,
          message: "号码只能录入数字",
          pattern: /^\d*$/,
          trigger: ['change','blur']
        }
      ],
      contactsSecondNumber: [
        {required: false,
          message: "分机号只能录入数字",
          pattern: /^\d*$/,
          trigger: ['change','blur']
        }
      ],
      email: [
        {required: false,
          message: "邮箱格式不正确",
          pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
          trigger: ["blur","change"]
        }
      ],
      organCode: [
        {required: true, message: "出单机构不能为空", trigger: "blur"}
      ],
      content: [
        {required: true, message: "业务内容不能为空", trigger: "blur"},
        { min: 0, max: 2000, message: '长度不超过2000个字符' }
      ],
    };
    return {
      dictList: [],
      cs_relation: [],//关系
      cs_demand_item: [],//服务项目
      cs_sex: [],//性别
      cs_priority: [],//优先级
      cs_communication_language: [],//语言
      cs_organization: [],//机构
      cs_channel: [],//
      rgtSex: [],//
      card_type: [],//
      //需要填入数据的部分
      ruleForm: {
        workOrderNo:"",
        channelCode: "03",//受理渠道
        itemCode: "",//服务项目
        callMobilePhone: "",//来电人电话
        priorityLevel: "",//优先级
        callName: "",//来电人
        callRelationBy: "",//来电人与被保人关系
        contactsName: "",//联系人
        contactsSex: "",//联系人性别
        contactsRelationBy: "",//联系人与别抱人关系
        contactsLanguage: "01",//联系人语言
        contactsMobilePhone: "",//联系人电话
        email: "",//邮件
        organCode: "",//出单机构
      },
      rules1: rules_bank,
      rules2: rules_noBank,
      changeForm: {
        rules: rules_noBank
      },//用来区分有无转账的校验
      readonly: true,
      dialogVisable: "",//上传附件用
      historicalProblemData: Array,//上传附件用
      updateBy: undefined,
      baseInfo: {
        policyNo: undefined,
        policyItemNo: undefined,
        appName: undefined,
        validStartDate: "",
        orgPolicyItemNo: "",
        validEndDate: "",
        policyRiskType: "",
        policyStatus: "",
        specialAgreement: "",
        companyName: undefined,
        companyCode: undefined,
        policyType: "",
        planCode: "",
        orgPolicyNo: "",
        ssFlag: "",
        policyManageCom: "",
        riskCodesStr: undefined,
        insuredNo: undefined,
        name: undefined,
        idType: undefined,
        idNo: undefined,
        birthday: undefined,
        occupation: "",
        nationality: "",
        idStartDate: "",
        idEndDate: "",
        mobile: undefined,
        email: undefined,
        phone: undefined,
        province: undefined,
        city: undefined,
        district: undefined,
        address: undefined,
        orgInsuredNo: undefined,
      },
      workPoolData: [],
      checkSubmitFlag: '',
    }
  },
  created() {
    this.baseInfo.policyNo = this.$route.query.policyNo;
    this.baseInfo.policyItemNo = this.$route.query.policyItemNo;
    this.searchBaseInfo();
  },
  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.cs_demand_item = this.dictList.find(item => {
      return item.dictType === 'cs_demand_item'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_sex = this.dictList.find(item => {
      return item.dictType === 'cs_sex'
    }).dictDate
    this.cs_communication_language = this.dictList.find(item => {
      return item.dictType === 'cs_communication_language'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_relation = this.dictList.find(item => {
      return item.dictType === 'cs_relation'
    }).dictDate
    this.cs_channel = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.rgtSex = this.dictList.find(item => {
      return item.dictType === 'rgtSex'
    }).dictDate
    this.card_type = this.dictList.find(item => {
      return item.dictType === 'card_type'
    }).dictDate
  },
  methods: {
    //监听是否银行转账事件
    bankChange(s) {
      if (s == "1") {
        this.changeForm.rules = this.rules1
      } else {
        this.changeForm.rules = this.rules2
      }
    },
    //上传附件
    upload() {
      this.$refs.upload.open();
    },
    download() {
    },
    //提交页面数据
    submit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          let insert = this.ruleForm;
          if(this.baseInfo.policyNo){
            this.$set(insert,'policyNo',this.baseInfo.policyNo);
          }
          if(this.baseInfo.policyItemNo){
            this.$set(insert,'policyItemNo',this.baseInfo.policyItemNo);
          }
          if(this.baseInfo.companyCode){
            insert.organCode=this.baseInfo.companyCode;
          }
          addInsert(insert).then(res => {
            if (res != null && res.code === 200) {
              this.$message.success("工单受理成功!");
              this.checkSubmitFlag='01';
              this.ruleForm.workOrderNo=res.msg;
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "失败！"
                )
              }
            }
          }).catch(res => {

          })
        } else {
          return this.$message.warning(
            "请检查信息格式！"
          )
        }
      });
    },
    // 获取基本信息
    searchBaseInfo() {
      let query = {
        policyNo: this.baseInfo.policyNo,
        policyItemNo: this.baseInfo.policyItemNo,
      }
      if(this.baseInfo.policyNo != null && this.baseInfo.policyNo !=""){
        policyInfoData(query).then(res => {
          if (res != null && res.code === 200) {
            this.baseInfo = res.data;
          }
        }).catch(res => {

        })
      }
    },
    //关闭按钮
    hiddenShow: function () {
      // 返回上级路由并关闭当前路由
      this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
      this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)

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

.to_right {
  width: 150px;
  text-align: right;
}
/deep/ .el-radio{
  line-height: 30px;
}
</style>
