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
              <el-select v-model="sendForm.organCode" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
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
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="130px"
               label-position="right" size="mini">

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
        <el-form-item label="服务项目：" prop="itemCode">
          <el-select v-model="ruleForm.itemCode" class="item-width" placeholder="请选择" controls-position="right" :min="0">
            <el-option v-for="item in cs_complaint_item" :key="item.dictValue" :label="item.dictLabel"
                       :value="item.dictValue"/>
          </el-select>
        </el-form-item>
        </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="ruleForm.callPerson.mobilePhone" class="item-width" maxlength="15" clearable size="mini" placeholder="请输入"/>
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
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input v-model="ruleForm.contactsPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-select v-model="ruleForm.contactsPerson.sex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人姓名：" prop="complaintPerson.name">
              <el-input v-model="ruleForm.complaintPerson.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉人性别：" prop="complaintPerson.sex">
              <el-select v-model="ruleForm.complaintPerson.sex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：" prop="complaintPerson.identity">
              <el-select v-model="ruleForm.complaintPerson.identity" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_identity" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人手机：" prop="contactsPerson.mobilePhone">
              <el-input v-model="ruleForm.contactsPerson.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="ruleForm.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="3">
              <el-form-item label="家庭电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[0]">
                国家区号:+
                <el-input v-model="ruleForm.contactsPerson.homePhone1[0]" class="item-width" style="width: 60px" maxlength="50"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[1]">
                区号
                <el-input v-model="ruleForm.contactsPerson.homePhone1[1]" class="item-width" size="mini" style="width: 145px"
                          maxlength="50"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[2]">
                号码
                <el-input v-model="ruleForm.contactsPerson.homePhone1[2]" class="item-width" size="mini" style="width: 145px"
                          maxlength="50"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[3]">
                分机号
                <el-input v-model="ruleForm.contactsPerson.homePhone1[3]" class="item-width" size="mini" style="width: 145px"
                          maxlength="4"/>
              </el-form-item>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <el-form-item label="办公电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[0]">
              国家区号:+
              <el-input v-model="ruleForm.contactsPerson.workPhone1[0]" class="item-width" style="width: 60px" maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[1]">
              区号
              <el-input v-model="ruleForm.contactsPerson.workPhone1[1]" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[2]">
              号码
              <el-input v-model="ruleForm.contactsPerson.workPhone1[2]" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[3]">
              分机号
              <el-input v-model="ruleForm.contactsPerson.workPhone1[3]" class="item-width" size="mini" style="width: 145px"
                        maxlength="4"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="联系地址：" maxlength="2000" style="white-space: nowrap" prop="contactsPerson.address">
           <el-input
              type="textarea"
              :rows="3"
              placeholder="请输入"
              v-model="ruleForm.contactsPerson.address">
            </el-input>
          </el-form-item>

        </el-row>
        <el-row>
          <el-col :span="8">

          <el-form-item label="预约时间："  style="white-space: nowrap"  prop="complaintTime">
            <el-date-picker
              v-model="ruleForm.complaintTime"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
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

        <el-form-item label="业务内容：" prop="content">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入内容"
          v-model="ruleForm.content">
        </el-input>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <el-row>
        <span style="color: blue">附件信息</span>
                <div  style="text-align: right; margin-right: 1px;">
                 <up-load ref="upload" > </up-load>
                  <el-button size="mini" type="primary" @click="upload()" disabled>上传附件</el-button>
                </div>
        </el-row>
        <el-divider/>
<!--          <el-button  type="primary" style="float: right" size="mini" @click="tan">上传附件</el-button></span>-->
        <up-load  :dialogVisable="dialogVisable"></up-load>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="policyNumber" label="附件名称" show-overflow-tooltip/>
          <el-table-column align="center"  prop="secondNumber" label="附件类型" show-overflow-tooltip/>
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
        <el-button  type="primary" size="mini" @click="submit" :disabled="this.checkSubmitFlag=='01'">提交</el-button>
        <el-button  type="primary"size="mini" @click="hiddenShow">关闭</el-button>
      </div>
    </el-card>



  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,addInsert} from '@/api/customService/demand'
  import {complaintAddInsert} from '@/api/customService/complaint'


  import upLoad from "../common/modul/upload";


  let dictss = [{dictType: 'product_status'}]
  export default {
    components: { upLoad },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {
      const checkComplaintTime= (rule, value, callback) => {
        let tDate = new Date();
        if(tDate > value){
          callback(new Error("预约时间不能早于当前日期"));
        }else{
          callback();
        }
      }

      return {
        checkSubmitFlag: '',
        cs_channel: [],//
        cs_complaint_item:[],//服务项目
        cs_sex:[],//性别
        cs_identity:[],//身份
        cs_priority:[],//优先级
        cs_communication_language:[],//语言
        cs_organization:[],//机构
        cs_handle_state:[],// 状态：
        //需要填入数据的部分
        ruleForm:{
          channelCode:"03",//受理渠道
          itemCode:"B00006",//服务项目
          callPerson:{
            mobilePhone:"",//来电人电话

          },
          contactsPerson:{
            name:"",
            sex:"",
            mobilePhone:"",
            address:"",
            workPhone1:[],
            homePhone1:[],
            language: "01",

          },
          complaintPerson:{
            name:"",
            sex:"",
            identity:"",

          },
          workOrderNo:"",
          complaintTime:"",
          priorityLevel:"",//优先级
          callName:"",//来电人
          callRelationBy:"",//来电人与被保人关系
          contactsName:"",//联系人
          contactsSex: "",//联系人性别
          contactsRelationBy: "",//联系人与别抱人关系
          contactsLanguage: "",//联系人语言
          contactsMobilePhone: "",//联系人电话
          email:"",//邮件
          organCode:"",//出单机构
          bankTransfer:"",//是否涉及转账
          bankName:"",//开户行
          bankLocation:"",//开户地
          accountNumber:"",//账号
          bankHolder:"",//户名
          content:"",
          contactsCountry:"",//
          contactsQuhao:"",
           contactsNumber:"",
          contactsSecondNumber:"",


        },
        // 表单校验根据Form 组件提供了表单验证的功能，只需要通过 rules 属性传入约定的验证规则，并将 Form-Item 的 prop 属性设置为需校验的字段名即可
        rules: {
          channelCode: [
            {required: true, message: "受理渠道不能为空", trigger: "blur"}
          ],
          itemCode: [
            {required: true, message: "服务项目不能为空", trigger: "blur"}
          ],
          priorityLevel: [
            {required: true, message: "优先级不能为空", trigger: "blur"}
          ],
          'contactsPerson.sex': [
              {required: true, message: "联系人性别", trigger: "blur"}
            ],
          'contactsPerson.name': [
            {required: true, message: "联系人姓名不能为空", trigger: "blur"}
          ],
          'complaintPerson.name':[
            {required: true, message: "投诉人姓名不能为空", trigger: "blur"}
          ],
          'complaintPerson.sex': [
            {required: true, message: "投诉人性别不能为空", trigger: "blur"}
          ],
          complaintTime: [
            {required: true, message: "预约时间不能为空", trigger: "blur"},
            {required: true, validator: checkComplaintTime, trigger: "blur"}
          ],
          'contactsPerson.mobilePhone': [
            {required: false,
              message: "目前只支持中国大陆的手机号码",
              pattern: /^1[34578]\d{9}$/,
              trigger: ['blur','change']},
          ],
          'callPerson.mobilePhone': [
            //{required: true, message: "来电号码不能为空", trigger: "blur"},
            {required: true,
              message: "目前只支持录入数字",
              pattern: /^\d+$/,//可以写正则表达式呦呦呦,
              trigger: ['blur','change']},
          ],
          organCode:  [
            {required: true, message: "出单机构不能为空", trigger: "blur"}
          ],
          content: [
            {required: true, message: "业务内容不能为空", trigger: "blur"},
            { min: 0, max: 2000, message: '长度不超过2000个字符' }
          ],
          'contactsPerson.address': [
            { min: 0, max: 2000, message: '长度不超过2000个字符' }
          ],
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
          email: [
            {required: false,
              message: "邮箱格式不正确",
              pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
              trigger: ["blur","change"]
            }
          ],
        },
        readonly: true,
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
      debugger;
      window.aaa = this;
      this.searchHandle()
      this.getDicts("cs_complaint_item").then(response => {
        this.cs_complaint_item = response.data;
      });
      this.getDicts("cs_priority").then(response => {
        this.cs_priority = response.data;
      });
      this.getDicts("cs_sex").then(response => {
        this.cs_sex = response.data;
      });
      this.getDicts("cs_identity").then(response => {
        this.cs_identity = response.data;
      });
      this.getDicts("cs_communication_language").then(response => {
        this.cs_communication_language = response.data;
      });
      this.getDicts("cs_organization").then(response => {
        this.cs_organization = response.data;
      });
      this.getDicts("cs_channel").then(response => {
        this.cs_channel = response.data;
      });


    },

    methods: {
     //上传附件
      upload(){
        this.$refs.upload.open();
      },
      download(){},
      //提交页面数据
       submit(){
         this.$refs.ruleForm.validate((valid) => {
           if (valid) {
             let insert = this.ruleForm
             complaintAddInsert(insert).then(res => {
               if (res != null && res.code === 200) {
                 this.$message.success("保存成功");
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
        debugger;
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
        demandListAndPublicPool(query).then(res => {
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
            if (res.rows.length <= 0) {
              /*return this.$message.warning(
                "未查询到数据！"
              )*/
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

    }
  }
</script>

<style scoped>
  .batchInfo_class .el-tag--small {
    margin-right: 10px !important;
  }
  .item-width {
    width: 220px;
  }
</style>
