<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="120px"
               label-position="right" size="mini">
        <el-row>
          <!--clearable是清楚输入框内容 readly、只读不可以编辑 ；不可以共存-->
          <el-col :span="8">
            <el-form-item label="保单号：" prop="Service">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人出生日期:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件类型:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢生效日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢满期日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="主招揽业务员电话:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="是否UHCG会员:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构:" prop="organCode">
              <el-select v-model="sendForm.organCode" class="item-width">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="AM(服务经理):" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="BD(销售经理):" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="特定医院赔付比例:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>


    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="workPoolData" :model="workPoolData" style="padding-bottom: 30px;" label-width="180px" disabled
               label-position="right" size="mini">
        <span
          style="color: blue">{{
            selectDictLabel(cs_business_type, workPoolData.businessService != undefined && workPoolData.businessService != null ? workPoolData.businessService.split('-')[0] : '') + '-' + selectDictLabel(cs_service_item, workPoolData.businessService != null ? workPoolData.businessService.split('-')[1] : '')
          }}</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-select v-model="workPoolData.channelCode" class="item-width">
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="workPoolData.priorityLevel" class="item-width">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="callPerson.name">
              <el-input v-model="workPoolData.callPerson.name" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人与被保人关系：" prop="priority">
              <el-select v-model="workPoolData.callRelationBy" class="item-width">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人：" prop="contactsPerson.name">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="priority">
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人与被保人关系：" prop="priority">
              <el-select v-model="workPoolData.contactsRelationBy" class="item-width">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="联系人固定电话：" style="white-space: nowrap" prop="phone">
              国家区号+
              <el-input v-model="workPoolData.contactsPerson.linePhone1[0]" class="item-width"
                        style="width: 75px"/>
              区号
              <el-input v-model="workPoolData.contactsPerson.linePhone1[1]" class="item-width" size="mini"
                        style="width: 75px"/>
              号码
              <el-input v-model="workPoolData.contactsPerson.linePhone1[2]" class="item-width" size="mini"
                        style="width: 120px"/>
              分机号
              <el-input v-model="workPoolData.contactsPerson.linePhone1[3]" class="item-width" size="mini"
                        style="width: 75px"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="16">
            <el-form-item label="所在地：" prop="address">
              <el-input v-model="workPoolData.contactsPerson.address" class="width-full" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="业务内容：" prop="textarea">
              <el-input
                type="textarea"
                :rows="2"
                v-model="workPoolData.content">
              </el-input>
            </el-form-item>
          </el-col>
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
          <el-table-column align="center" width="140" prop="status" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.status">
              <span>{{ selectDictLabel(cs_order_state, scope.row.status) }}</span>
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
              <span>{{ scope.row.makeTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='01'" style="font-size:12px" type="primary"
                       @click="modifyDetails(scope.row)">修改说明
              </el-link>
            </template>
          </el-table-column>
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
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
        <el-divider/>

        <el-row>
          <el-col :span="8">
          <el-form-item label="业务处理情况：" prop="businessProcess">
            <el-radio-group v-model="ruleForm.businessProcess" @change="isBusinessProcess(ruleForm.businessProcess)">
              <el-radio label="01">成功</el-radio>
              <el-radio label="02">响应</el-radio>
            </el-radio-group>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="remark">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="ruleForm.remark">
            </el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="客户反馈：" prop="customerFeedback">
            <el-radio-group v-model="ruleForm.customerFeedback">
              <el-radio
                v-for="dict in cs_feedback_type"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{ dict.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="结案类型：" prop="closeType">
              <el-select v-model="ruleForm.closeType" class="item-width" placeholder="请选择" controls-position="right"
                         :min="0" @change="isCloseType(ruleForm.closeType)">
                <el-option v-for="item in cs_end_case" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button type="primary" style="float: right" size="mini" @click="upload"
                                                 disabled>上传附件</el-button></span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="HCSPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="policyNumber" label="附件名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="attachmentType" label="附件类型" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.attachmentType">
              <span>{{selectDictLabel(cs_attachment_type, scope.row.attachmentType)}}</span>
            </template>
          </el-table-column>
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
        <modify-details ref="modifyDetails"></modify-details>
        <transfer ref="transfer"></transfer>
        <up-load ref="upload"></up-load>
        <co-organizer ref="coOrganizer"></co-organizer>
        <el-button type="primary" size="mini" @click="temporary">暂存</el-button>
        <el-button type="primary" size="mini" @click="transfer" >协办</el-button>
        <el-button type="primary" size="mini" @click="urge" >催办</el-button>
        <el-button type="primary" size="mini" @click="coCancel" >撤销</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import moment from 'moment'
import { FlowLogSearch, dealADD} from '@/api/customService/demand'
import {coCancel, demandAccept, selectAssist} from '@/api/customService/collaborative'

import transfer from "../common/modul/transfer";
import upLoad from "../common/modul/upload";
import coOrganizer from "../common/modul/coOrganizer";
import modifyDetails from "../common/modul/modifyDetails";
import {complainSearchServer} from '@/api/customService/complaint';
/*
import {
  demandListAndPublicPool,
  demandListAndPersonalPool,
  dealAdd,
  FlowLogSearch,
  HMSSearch,
  dealADD
} from '@/api/customService/demand'
*/
let dictss = [
  {dictType: 'cs_channel'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_sex'},
  {dictType: 'cs_communication_language'},
  {dictType: 'cs_identity'},
  {dictType: 'cs_whether_flag'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_relation'},
  {dictType: 'cs_feedback_type'},
  {dictType: 'cs_end_case'},
  {dictType: 'cs_service_item'},
  {dictType: 'cs_business_type'},
  {dictType: 'cs_attachment_type'},
  {dictType: 'cs_order_state'},
  {dictType: 'cs_action_type'},
]
export default {
  components: {
    transfer,
    upLoad,
    coOrganizer,
    modifyDetails,
  },
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
    }
  },
  data() {
    // 表单校验
    const isRule = {
      businessProcess: [
        {required: true, message: "业务处理情况不能为空", trigger: "blur"}
      ],
      remark: [
        {required: true, message: "处理说明不能为空", trigger: "blur"},
        {min: 0, max: 2000, message: '长度2000 个字符'}

      ],
      customerFeedback: [
        {required: true, message: "客户反馈不能为空", trigger: "blur"}
      ],
      closeType: [
        {required: true, message: "结案类型不能为空", trigger: "blur"}
      ],
      costsIncurred: [
        {required: true, message: "安抚或通融发生费用成本不能为空", trigger: "blur"},
        {min: 0, max: 500, message: '长度500 个字符以内'}

      ],

    };
    const isCloseType = {
      businessProcess: [
        {required: true, message: "业务处理情况不能为空", trigger: "blur"}
      ],
      remark: [
        {required: true, message: "处理说明不能为空", trigger: "blur"},
        {min: 3, max: 100, message: '长度在 3 到 100 个字符'}

      ],
      customerFeedback: [
        {required: true, message: "客户反馈不能为空", trigger: "blur"}
      ],
      closeType: [
        {required: true, message: "结案类型不能为空", trigger: "blur"}
      ],
    };
    // 表单校验
    const noRules = {
      businessProcess: [
        {required: true, message: "业务处理情况不能为空", trigger: "blur"}
      ],
    };
    return {
      isDisabled: true,
      rules1: isRule,
      rules2: noRules,
      rules3: isCloseType,
      changeForm: {
        rules: isRule
      },
      ids: [],//多选框
      //流转用
      colStatus: "03",//是否协办
      flowLogData: [],
      flowLogCount: 0,
      //信息需求反显数组
      // 表单校验
      rules: {
        Service: [
          {required: true, message: "处理说明不能为空", trigger: "blur"}
        ],
      },
      dialogFormVisible: false,
      updateBy: undefined,
      //新增的数据传输
      sendForm: {
        sign: "",//控制暂存还是提交用
        workOrderNo: '',
        businessProcess: '',
      },
      ruleForm: {
        workOrderNo: "",
        businessProcess: "01",
        remark: "",
        customerFeedback: "01",
        closeType: "01",
        costsIncurred: "",
        sign: ""
      },

      // 查询参数
      queryParams: {
        workOrderNo: "",
        policyNo: "",
        policyItemNo: "",
        status: "",
        pageNum: 1,
        pageSize: 10
      },
      loading: true,
      //数据反显用
      workPoolData: {
        callCenterId: "",
        callPerson: {
          mobilePhone: "",
          name: "",
        },
        contactsPerson: {
          name: "",
          language: "",
          sex: "",
          mobilePhone: "",
          workPhone: "",
          address: "",
        },
        email: "",
        bankTransfer: "",
        content: ""
      },
      //HCS服务预约预修改记录
      HCSPoolData: [],
      HCSTotal: 0,
      //业务处理信息
      dealInfo: [],
      isinit: 'Y',
      totalCount: 0,
      changeSerchData: {},
      dictList: [],
      cs_order_state: [],//状态
      cs_action_type: [],//操作类型
      cs_channel: [],
      cs_priority: [],
      cs_sex: [],
      cs_communication_language: [],
      cs_identity: [],
      cs_whether_flag: [],
      cs_organization: [],
      cs_relation: [],
      cs_feedback_type: [],
      cs_end_case: [],
      cs_service_item: [],
      cs_business_type: [],
      cs_attachment_type: [],
    }
  },
  created() {
    //获取url传参
    this.queryParams.workOrderNo = this.$route.query.workOrderNo;
    this.queryParams.policyNo = this.$route.query.policyNo;
    this.queryParams.policyItemNo = this.$route.query.policyItemNo;
    this.queryParams.status = this.$route.query.status;

    //数据加载
    this.searchHandle();
    this.searchFlowLog();
    this.coSearch();
    this.searchHandleServer();
  },
  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.cs_channel = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
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
    this.cs_identity = this.dictList.find(item => {
      return item.dictType === 'cs_identity'
    }).dictDate
    this.cs_whether_flag = this.dictList.find(item => {
      return item.dictType === 'cs_whether_flag'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_relation = this.dictList.find(item => {
      return item.dictType === 'cs_relation'
    }).dictDate
    this.cs_feedback_type = this.dictList.find(item => {
      return item.dictType === 'cs_feedback_type'
    }).dictDate
    this.cs_end_case = this.dictList.find(item => {
      return item.dictType === 'cs_end_case'
    }).dictDate
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_business_type = this.dictList.find(item => {
      return item.dictType === 'cs_business_type'
    }).dictDate
    this.cs_attachment_type = this.dictList.find(item => {
      return item.dictType === 'cs_attachment_type'
    }).dictDate
    this.cs_order_state = this.dictList.find(item => {
      return item.dictType === 'cs_order_state'
    }).dictDate
    this.cs_action_type = this.dictList.find(item => {
      return item.dictType === 'cs_action_type'
    }).dictDate
  },
  methods: {
    //取消
    deal() {
    },
    //反显信息需求
    searchHandle() {
      let workOrderNo = this.queryParams.workOrderNo
      demandAccept(workOrderNo).then(res => {
        if (res != null && res.code === 200) {
          this.workPoolData = res.data;
        }
      }).catch(res => {

      })
    },//反显暂存信息
    searchHandleServer() {
      let insert = this.queryParams
      insert.businessType = "01"
      complainSearchServer(insert).then(res => {
        if (res != null && res.code === 200) {
          console.log("信息需求页面server反显数据",res.data)
          this.ruleForm = res.data;
          if (res.rows.length <= 0) {
            return this.$message.warning(
            )
          }
        }
      }).catch(res => {

      })
    },
    temporary() {
      let insert = this.ruleForm
      insert.sign = "01"
      insert.workOrderNo = this.$route.query.workOrderNo
      dealADD(insert).then(res => {
        if (res != null && res.code === 200) {
          this.$message.success("暂存成功")
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "失败！"
            )
          }
        }
      }).catch(res => {

      })
    },
//是否响应
    isBusinessProcess(s) {
      if (s == "01") {
        this.changeForm.rules = this.rules1
      } else {
        this.changeForm.rules = this.rules2
        this.$refs.ruleForm.clearValidate()
      }

      //更新按钮状态
      this.checkButton();

    },
    //上传附件
    upload() {
      this.$refs.upload.open();
    },
    //下载
    download() {
    },
    //转办
    transfer() {
      this.$refs.transfer.dynamicValidateForm.workOrderNo = this.queryParams.workOrderNo
      this.$refs.transfer.open()
    },
    //协办
    coOrganizer() {
      this.$refs.coOrganizer.open();
    },
    //超链接用
    modifyDetails(s) {
      this.$refs.modifyDetails.queryParams.subId = s.subId,
        this.$refs.modifyDetails.queryParams.workOrderNo = this.queryParams.workOrderNo;
      this.$refs.modifyDetails.open()
      ;
    },

    resetForm() {
      this.$refs.sendForm.resetFields()
    },
//结案类型是否正常
    isCloseType(s) {
      if (this.ruleForm.businessProcess == '01') {
        if (s == "01") {
          this.changeForm.rules = this.rules3
        } else {
          this.changeForm.rules = this.rules1
          this.$refs.ruleForm.clearValidate()
        }
      }

    },

    //查询轨迹表
    searchFlowLog() {
      let workOrderNo = this.queryParams
      workOrderNo.status = ""
      FlowLogSearch(workOrderNo).then(res => {
        if (res != null && res.code === 200) {
          this.flowLogData = res.rows
          this.flowLogCount = res.total
        }
      }).catch(res => {

      })
    },

    //取消按钮
    cancle() {
      this.$router.push({
        path: '/customService/cancle',
        query: {
          workOrderNo: this.queryParams.workOrderNo,
          policyNo: this.queryParams.policyNo,
          policyItemNo: this.queryParams.policyItemNo,
          status: this.queryParams.status
        }
      })
    },
    //查询协办表
    coSearch() {
      let send = this.queryParams
      selectAssist(send).then(res => {
        if (res != null && res.code === 200) {
          if (res.rows.length != 0) {
            this.colStatus = res.rows[0].status
            console.log(this.colStatus)
          }
          this.colStatus = "03"
          console.log(this.colStatus)
          if (res.rows.length <= 0) {
            return this.$message.warning(

            )
          }
        }
      }).catch(res => {

      })

    },
    //撤销
    coCancel() {
      let workOrderNo = this.queryParams
      coCancel(workOrderNo).then(res => {
        if (res != null && res.code === 200) {
          this.$message.success("撤销成功")
        }
      }).catch(res => {

      })
      this.coSearch()
    },
    // 催办
    urge() {

    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.workOrderNo);
    },
  }
}
</script>

<style scoped>
.item-width {
  width: 180px;
}
</style>
