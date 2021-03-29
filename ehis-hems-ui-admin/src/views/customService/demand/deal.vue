<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="baseInfo" :model="baseInfo" label-width="180px"
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
      <el-form ref="ruleForm" :model="ruleForm" style="padding-bottom: 30px;" label-width="180px" :disabled="isDisabled"
               label-position="right" size="mini">

        <span
          style="color: blue">{{ selectDictLabel(cs_business_type, workPoolData.businessService != undefined && workPoolData.businessService != null ? workPoolData.businessService.split('-')[0] : '') + '-' + selectDictLabel(cs_service_item, workPoolData.businessService != null ? workPoolData.businessService.split('-')[1] : '') }}</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：">
              <el-input v-model="workPoolData.workOrderNo" class="item-width" clearable size="mini" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="phoneNumber">
              <el-input v-model="workPoolData.callCenterId" class="item-width" size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width" size="mini" readonly/>
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
            <el-form-item label="来电人姓名：" prop="phone">
              <el-input v-model="workPoolData.callPerson.name" class="item-width" readonly size="mini"/>
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
            <el-form-item label="联系人：" prop="lxperson">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width" size="mini" readonly/>
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
            <el-form-item label="联系人语言：" prop="priority">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width" size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width" size="mini" readonly/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="联系人固定电话：" style="white-space: nowrap" prop="phone">
              国家区号+
              <el-input v-model="workPoolData.contactsPerson.linePhones0" class="item-width"
                        style="width: 75px"/>
              区号
              <el-input v-model="workPoolData.contactsPerson.linePhones1" class="item-width" size="mini"
                        style="width: 75px" maxlength="50"/>
              号码
              <el-input v-model="workPoolData.contactsPerson.linePhones2" class="item-width" size="mini"
                        style="width: 120px" maxlength="50"/>
              分机号
              <el-input v-model="workPoolData.contactsPerson.linePhones3" class="item-width" size="mini"
                        style="width: 75px" maxlength="4"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="所在地：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.address" class="width-full" size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="priority">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="请选择">
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
                readonly
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
          <el-table-column align="center" prop="linkCode" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.linkCode">
              <span>{{ selectDictLabel(cs_link_code, scope.row.linkCode) }}</span>
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
              <el-link v-if="scope.row.operateCode=='03'" style="font-size:12px" type="primary"
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

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">HCS服务预约预修改记录</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="HCSPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column prop="alterTime" label="修改时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.alterTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="alterId" align="center" label="修改序列号" show-overflow-tooltip/>
          <el-table-column prop="alterChannel" align="center" label="预约渠道" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.alterChannel">
              <span>{{ selectDictLabel(cs_channel, scope.row.alterChannel) }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="alterContent" align="center" style="width: available" label="修改内容描述"
                           show-overflow-tooltip/>
        </el-table>
        <pagination
          v-show="HCSTotal>0"
          :total="HCSTotal"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHCS"
        />
      </div>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="ruleForm" :model="ruleForm" :rules="changeForm.rules" style="padding-bottom: 30px;"
               label-width="180px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
        <span style="float: right;">
           <el-button type="primary" size="mini" @click="modify">修改</el-button>
          <el-button type="primary" size="mini" @click="cancle">取消</el-button>
          </span>
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
        <el-row>
          <el-form-item label="安抚或通融发生费用成本：" prop="costsIncurred" v-if="ruleForm.closeType!='01'">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入："
              v-model="ruleForm.costsIncurred"
            >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>


    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button type="primary" style="float: right" size="mini"
                                                 @click="upload">上传附件</el-button></span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="HCSPoolData"
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
              <el-button size="mini" type="text" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: right; margin-right: 1px;">
        <modify-details ref="modifyDetails"></modify-details>
        <transfer ref="transfer" @checkButton="checkButton"></transfer>
        <up-load ref="upload"></up-load>
        <co-organizer ref="coOrganizer" @checkButton="checkButton"></co-organizer>
        <el-button type="primary" size="mini" @click="transfer" :disabled="checkButtonFlag.transferFlag">转办
        </el-button>
        <el-button type="primary" size="mini" @click="coOrganizer" :disabled="checkButtonFlag.coOrganizerFlag">协办
        </el-button>
        <el-button type="primary" size="mini" @click="upload" disabled>保单信息查询</el-button>
        <el-button type="primary" size="mini" @click="temporary" :disabled="checkButtonFlag.temporaryFlag">暂存
        </el-button>
        <el-button type="primary" size="mini" @click="submit" :disabled="checkButtonFlag.submitFlag">提交</el-button>
      </div>
    </el-card>


  </div>
</template>

<script>
import moment from 'moment'
import {
  demandListAndPublicPool,
  demandListAndPersonalPool,
  dealAdd,
  FlowLogSearch,
  HMSSearch,
  dealADD
} from '@/api/customService/demand'
import transfer from "../common/modul/transfer";
import upLoad from "../common/modul/upload";
import coOrganizer from "../common/modul/coOrganizer";
import modifyDetails from "../common/modul/modifyDetails";
import {complainSearchServer} from '@/api/customService/complaint';
import {policyInfoData} from "@/api/customService/common";


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
  {dictType: 'cs_action_type'},
  {dictType: 'cs_link_code'},
  {dictType: 'rgtSex'},
  {dictType: 'card_type'},
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
      },//用来区分是否响应
      ids: [],//多选框
      //流转用
      flowLogData: [],
      flowLogCount: 0,
      // //传过来的工单号
      // workOrderNo:"",
      // policyNo:"",
      // policyItemNo:"",
      // status:"",
      //信息需求反显数组
      //需要填入数据的部分
      ruleForm: {
        workOrderNo: "",
        businessProcess: "01",
        remark: "",
        customerFeedback: "01",
        closeType: "01",
        costsIncurred: "",
        sign: ""
      },

      checkButtonFlag: {
        transferFlag: false,//协办
        coOrganizerFlag: false,//转办
        temporaryFlag: false,//暂存
        submitFlag: false//提交
      },
      checkButtonFrom: {//传值给后台
        acceptorTime:undefined,//受理时间数组
        appointmentTime:undefined,//预约时间数组
        handlerTime:undefined,//处理时间数组
        pageNum: 1,
        pageSize: 10,
        itemCode: "",//服务信息
        channelCode: "",//受理渠道
        acceptBy: "",//受理人
        modifyBy: "",//处理人
        workOrderNo: "",//工单编号
        policyNo: "",//保单号
        policyItemNo: "",//分单号
        holderName: "",//投保人
        insuredName: "",//被保人
        idNumber: "",//证件号
        organCode: "",//出单机构
        priorityLevel:"",//优先级
        vipFlag:"",//VIP标识
        mobilePhone:"",//移动电话
        status:"",//状态
        modifyTime:"",
        updateTime:""
      },

      readonly: true,
      dialogFormVisible: false,
      updateBy: undefined,
      //新增的数据传输
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
      caseNumber: false,//查询条件（报案号）是否显示
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
        contactsPerson: {
          homePhone1: []
        },
        callPerson: {},

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
      cs_link_code: [],//状态
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
      rgtSex: [],
      card_type: [],
    }
  },
  created() {
    this.queryParams.workOrderNo = this.$route.query.workOrderNo;
    this.queryParams.policyNo = this.$route.query.policyNo;
    this.queryParams.policyItemNo = this.$route.query.policyItemNo;
    this.queryParams.status = this.$route.query.status;
    //window.aaa = this;
    this.searchHandle()
    this.searchFlowLog()
    this.searchHandleServer()
    this.searchHCS()
    this.searchBaseInfo();
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
    this.cs_action_type = this.dictList.find(item => {
      return item.dictType === 'cs_action_type'
    }).dictDate
    this.cs_link_code = this.dictList.find(item => {
      return item.dictType === 'cs_link_code'
    }).dictDate
    this.rgtSex = this.dictList.find(item => {
      return item.dictType === 'rgtSex'
    }).dictDate
    this.card_type = this.dictList.find(item => {
      return item.dictType === 'card_type'
    }).dictDate

    //初始化按钮状态
    this.checkButton();
  },
  methods: {

    hiddenShow:function () {
      // 返回上级路由并关闭当前路由
      this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1);
      this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path);

    },

    // 获取基本信息
    searchBaseInfo() {
      let query = {
        policyNo: this.queryParams.policyNo,
        policyItemNo: this.queryParams.policyItemNo,
      }
      if(this.queryParams.policyNo != null && this.queryParams.policyNo !=""){
        policyInfoData(query).then(res => {
          if (res != null && res.code === 200) {
            this.baseInfo = res.data;
          }
        }).catch(res => {

        })
      }
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

    //设置按钮状态
    checkButton(){
      this.checkButtonFlag.transferFlag = false;//协办
      this.checkButtonFlag.coOrganizerFlag = false;//转办
      this.checkButtonFlag.temporaryFlag = false;//暂存
      this.checkButtonFlag.submitFlag = false;//提交

      // alert(this.ruleForm.businessProcess);
      //选择响应后  除【提交按钮外其余都置灰】
      if(this.ruleForm.businessProcess == '02'){
        this.checkButtonFlag.transferFlag = true;//协办
        this.checkButtonFlag.coOrganizerFlag = true;//转办
        this.checkButtonFlag.temporaryFlag = true;//暂存
      }

      //存在协办 转办后  默认都置灰
      let queryParams = JSON.parse(JSON.stringify(this.checkButtonFrom));
      queryParams.workOrderNo = this.$route.query.workOrderNo;
      demandListAndPersonalPool(queryParams).then(res => {
        if (res != null && res.code === 200) {
          if (res.total <= 0) {
            //未查询到数据
            this.checkButtonFlag.transferFlag = true;//协办
            this.checkButtonFlag.coOrganizerFlag = true;//转办
            this.checkButtonFlag.temporaryFlag = true;//暂存
            this.checkButtonFlag.submitFlag = true;//提交
          }
        }
      }).catch(res => {
        console.log('error submit!!');
      })

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
    //新增按钮
    add() {
      let addQueryParams = this.ruleForm
      addQueryParams.workOrderNo = this.workOrderNo
      dealAdd(addQueryParams).then(res => {
        if (res != null && res.code === 200) {
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "提交失败！"
            )
          }
        }
      }).catch(res => {

      })

    },
    //取消
    deal() {
    },
    //反显信息需求
    searchHandle() {
      let query = this.queryParams
      demandListAndPersonalPool(query).then(res => {
        if (res != null && res.code === 200 && res.rows) {
          this.workPoolData = res.rows[0]
          this.totalCount = res.total

          this.workPoolData.contactsPerson.linePhones0 = this.workPoolData.contactsPerson.linePhone1[0];
          this.workPoolData.contactsPerson.linePhones1 = this.workPoolData.contactsPerson.linePhone1[1];
          this.workPoolData.contactsPerson.linePhones2 = this.workPoolData.contactsPerson.linePhone1[2];
          this.workPoolData.contactsPerson.linePhones3 = this.workPoolData.contactsPerson.linePhone1[3];

          /* if (res.rows.length <= 0) {
             return this.$message.warning(
               "未查询到数据！"
             )
           }*/
        }
      }).catch(res => {

      })
    },
    //反显暂存信息
    searchHandleServer() {
      let insert = this.queryParams
      insert.businessType = "01"
      complainSearchServer(insert).then(res => {
        if (res != null && res.code === 200) {
          console.log("信息需求页面server反显数据",res.data)
          this.ruleForm = res.data;
          this.isBusinessProcess(this.ruleForm.businessProcess);
          this.checkButton();
          if (res.rows.length <= 0) {
            return this.$message.warning(
            )
          }
        }
      }).catch(res => {

      })
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
      this.$refs.transfer.dynamicValidateForm.workOrderNo = this.queryParams.workOrderNo;
      this.$refs.transfer.open()
    },
    //协办
    coOrganizer() {
      this.$refs.coOrganizer.dynamicValidateForm.workOrderNo = this.queryParams.workOrderNo;
      this.$refs.coOrganizer.open(this.queryParams.workOrderNo);
    },
    //超链接用
    modifyDetails(s) {
      this.$refs.modifyDetails.queryParams.subId = s.subId,
        this.$refs.modifyDetails.queryParams.workOrderNo = this.queryParams.workOrderNo;
      this.$refs.modifyDetails.open()
      ;
    },

    //打开修改对话框

    //提交
    submit() {

      if (this.ruleForm.businessProcess == "01") {
        if (this.HCSTotal == 0) {
          this.$refs.ruleForm.validate((valid) => {
            if (valid) {
              let insert = this.ruleForm
              insert.sign = "02"
              insert.workOrderNo = this.$route.query.workOrderNo
              dealADD(insert).then(res => {
                if (res != null && res.code === 200) {
                  this.$message.success("提交成功");
                  this.hiddenShow();
                  if (res.rows.length <= 0) {
                    return this.$message.warning(
                      "失败！"
                    )
                  }
                }
              }).catch(res => {
              })
            } else{
              this.$message.warning("请录入必录项");
            }

          })
        } else {
          this.$message.warning("请先处理完成 HCS记录在进行提交")
        }
      } else {
        this.$refs.ruleForm.validate((valid) => {
          if (valid) {
            let insert = this.ruleForm
            insert.sign = "02"
            insert.workOrderNo = this.$route.query.workOrderNo
            dealADD(insert).then(res => {
              if (res != null && res.code === 200) {
                this.$message.success("提交成功")
                if (res.rows.length <= 0) {
                  return this.$message.warning(
                    "失败！"
                  )
                }
              }
            }).catch(res => {
            })
          } else {
            return false
          }

        })


      }

    },
    //暂存（说不用校验）
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

    //查询轨迹表
    searchFlowLog() {
      let workOrderNo = this.queryParams
      workOrderNo.status = ""
      FlowLogSearch(workOrderNo).then(res => {
        if (res != null && res.code === 200) {
          this.flowLogData = res.rows
          this.flowLogCount = res.total
          this.flowLogCount = res.total
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
    //查询HCS
    searchHCS() {
      let workOrderNo = this.queryParams
      workOrderNo.status = ""
      HMSSearch(workOrderNo).then(res => {
        if (res != null && res.code === 200) {
          this.HCSPoolData = res.rows
          this.HCSTotal = res.total
          /*if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }*/
        }
      }).catch(res => {

      })
    },
    //修改按钮
    modify() {
      var items;

      // if (this.ids.length == 0) {
      //   this.$message.warning("先选中一行")
      // } else {
      items = JSON.stringify(this.ids)
      // if (this.ids.length > 2) {
      //   // alert("选中一行")
      // } else {
      this.$router.push({
        path: '/customService/modify',
        query: {
          workOrderNo: this.queryParams.workOrderNo,
          policyNo: this.queryParams.policyNo,
          policyItemNo: this.queryParams.policyItemNo,
          status: this.queryParams.status,
          alterId: items

        }
      })
      // }
    }
    ,
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.alterId);

    },

  }
}
</script>

<style scoped>
.item-width {
  width: 180px;
}
</style>
