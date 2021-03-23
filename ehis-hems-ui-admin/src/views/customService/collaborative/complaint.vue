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
              <el-input readonly v-model="sendForm.acceptor" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel" readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人出生日期:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件类型:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢生效日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢满期日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="主招揽业务员电话:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="是否UHCG会员:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构:" prop="Acceptor">
              <el-select v-model="sendForm.organCode" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="AM(服务经理):" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="BD(销售经理):" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="特定医院赔付比例:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源:" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>


    <el-card class="box-card" style="margin-top: 10px;" :disabled="flag=='show'">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="160px" :disabled="true"
               label-position="right" size="mini">
        <span
          style="color: blue">{{
            selectDictLabel(cs_service_item, workPoolData.itemCode)+'-服务受理信息'
          }}</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-select v-model="workPoolData.channelCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="callCenterId">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="sex">
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width" placeholder="">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人地址：" prop="address">
              <el-input v-model="workPoolData.contactsPerson.address" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="phone">
              <el-input v-model="workPoolData.insuredNo" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width" placeholder="">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人手机：" prop="mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="priority">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" placeholder="">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人姓名：" prop="phone">
              <el-input v-model="workPoolData.complaintPerson.name" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉人性别：">
              <el-select v-model="workPoolData.complaintPerson.sex" class="item-width" placeholder="">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：">
              <el-select v-model="workPoolData.complaintPerson.identity" class="item-width" placeholder="">
                <el-option v-for="item in cs_identity" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户电话：" prop="phone">
              <el-input v-model="workPoolData.insurer.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="办公电话：" prop="phone">
              <el-input v-model="workPoolData.insurer.workPhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="phone">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-form-item label="是否已劝解：" prop="phone">
            <el-select v-model="workPoolData.persuasionFlag" class="item-width" placeholder="">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="投诉内容：" prop="phone">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="workPoolData.content">
            </el-input>
          </el-form-item>
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
      <el-form style="padding-bottom: 30px;" label-width="145px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="一级投诉分类：" prop="priority">
              <el-select v-model="sendForm.level1" class="item-width" @change="classTwo('1')">
                <el-option v-for="item in cs_classify_level1" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉分类：" prop="priority">
              <el-select v-model="sendForm.level2" class="item-width">
                <el-option v-for="item in cs_classify_level2" :key="item.code" :label="item.codeName"
                           :value="item.code"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否计件：" prop="pieceworkFlag">
              <el-select v-model="sendForm.pieceworkFlag" class="item-width">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="撤诉状态：" prop="priority">
              <el-select v-model="sendForm.complaintStatus" class="item-width">
                <el-option v-for="item in cs_drop_status" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉是否成立：" prop="complaintTenable">
              <el-select v-model="sendForm.complaintTenable" class="item-width">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉不成立理由：" prop="faseReason">
              <el-input v-model="sendForm.faseReason"
                        class="item-width"
                        clearable size="mini"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="重复投诉：" prop="repeatedComplaint">
              <el-select v-model="sendForm.repeatedComplaint" class="item-width">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="一级投诉原因：" prop="reason1">
              <el-select v-model="sendForm.reason1" class="item-width" @change="reasonTwo('1')">
                <el-option v-for="item in cs_reason_level1" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉原因：" prop="reason2">
              <el-select v-model="sendForm.reason2" class="item-width" @change="reasonThree('1')">
                <el-option v-for="item in cs_reason_level2" :key="item.code" :label="item.codeName"
                           :value="item.code"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="三级投诉原因：" prop="reason1">
              <el-select v-model="sendForm.reason3" class="item-width">
                <el-option v-for="item in cs_reason_level3" :key="item.code" :label="item.codeName"
                           :value="item.code"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉环节(报保监)：" prop="complaintLink">
              <el-select v-model="sendForm.complaintLink" class="item-width">
                <el-option v-for="item in cs_link_circ" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉问题(报保监)：" prop="complaintQuestion">
              <el-select v-model="sendForm.complaintQuestion" class="item-width">
                <el-option v-for="item in cs_question_circ" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="行协调解或外部鉴定状态：" prop="outsideState">
              <el-select v-model="sendForm.outsideState" class="item-width">
                <el-option v-for="item in cs_mediation_appraisal" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="险种类型：" prop="riskType">
              <el-select v-model="sendForm.riskType" class="item-width">
                <el-option v-for="item in cs_risk_type" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="营销渠道：" prop="marketChannel">
              <el-select v-model="sendForm.marketChannel" class="item-width">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="投诉业务类别：" prop="complaintCategory">
            <el-input v-model="sendForm.complaintCategory" clearable size="mini" class="width-full"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="投诉根因部门：" prop="rootDepartment">
            <el-input v-model="sendForm.rootDepartment" clearable size="mini" class="width-full"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="质诉根因：" prop="actionCause">
            <el-input v-model="sendForm.actionCause" clearable size="mini" class="width-full"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="处理进展：" prop="treatmentProgress">
            <el-input v-model="sendForm.treatmentProgress" clearable size="mini" class="width-full"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="处理结果：" prop="treatmentResult">
            <el-input v-model="sendForm.treatmentResult" clearable size="mini" class="width-full"/>
          </el-form-item>
        </el-row>
        <el-row>


        </el-row>
        <el-col :span="8">
          <el-form-item label="客户反馈：" prop="customerFeedback">
            <el-select v-model="sendForm.customerFeedback" class="item-width">
              <el-option v-for="item in cs_feedback_type" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="跟因改善：" prop="rootImprovement">
            <el-input v-model="sendForm.rootImprovement" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉损失：" prop="actPromptly">
            <el-input v-model="sendForm.actPromptly" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
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
        <transfer ref="transfer"></transfer>
        <up-load ref="upload"></up-load>
        <co-organizer ref="coOrganizer"></co-organizer>
        <el-button type="primary" size="mini" @click="temporary">暂存</el-button>
        <el-button type="primary" size="mini" @click="transfer">转办</el-button>
        <el-button type="primary" size="mini" @click="coOrganizer">催办</el-button>
        <el-button type="primary" size="mini" @click="coCancel">撤销</el-button>
      </div>
    </el-card>


  </div>
</template>

<script>
import moment from 'moment'
import {collaborativeTemporary, complainSearch} from '@/api/customService/collaborative'
import {
  demandListAndPublicPool,
  demandListAndPersonalPool,
  dealAdd,
  FlowLogSearch,
  HMSSearch,
  dealADD,
  coCancel
} from '@/api/customService/demand'
import transfer from "../common/modul/transfer";
import upLoad from "../common/modul/upload";
import coOrganizer from "../common/modul/coOrganizer";
import modifyDetails from "../common/modul/modifyDetails";
import {classTwo, complainSearchServer, reasonThree, reasonTwo} from "@/api/customService/complaint";

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
  {dictType: 'cs_link_circ'},
  {dictType: 'cs_classify_level1'},
  {dictType: 'cs_drop_status'},
  {dictType: 'cs_reason_level1'},
  {dictType: 'cs_action_type'},
  {dictType: 'cs_order_state'},
  {dictType: 'cs_service_item'},
  {dictType: 'cs_business_type'},
  {dictType: 'cs_mediation_appraisal'},
  {dictType: 'cs_risk_type'},
  {dictType: 'cs_question_circ'},
];
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

    return {
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
        businessProcess: "",
        remark: "",
        customerFeedback: "",
        closeType: "",
        costsIncurred: "",
        sign: ""
      },
      // 表单校验
      rules: {},
      flag: '',
      readonly: true,
      dialogFormVisible: false,
      updateBy: undefined,
      //新增的数据传输
      sendForm: {
        sign: "",//控制暂存还是提交用
        level1: "",
        level2: "",
        pieceworkFlag: "",
        complaintStatus: "",
        complaintTenable: "",
        faseReason: "",
        repeatedComplaint: "",
        reason1: "",
        reason2: "",
        reason3: "",
        complaintLink: "",
        complaintQuestion: "",
        outsideState: "",
        riskType: "",
        marketChannel: "",
        complaintCategory: "",
        rootDepartment: "",
        actionCause: "",
        treatmentProgress: "",
        treatmentResult: "",
        customerFeedback: "",
        rootImprovement: "",
        actPromptly: "",
        improvementMeasures: "",
        businessProcess: "",
        workOrderNo: '',

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
      //数据反显用
      workPoolData: {
        contactsPerson: {
          homePhone1: []
        },
        callPerson: {},
        complaintPerson: {},
        insurer: {},
      },
      //HCS服务预约预修改记录
      HCSPoolData: [],
      HCSTotal: 0,
      //业务处理信息
      dealInfo: [],

      isinit: 'Y',
      totalCount: 0,
      changeSerchData: {},
      serves: [{
        dictValue: '1',
        dictLabel: '服务1'
      }, {
        dictValue: '2',
        dictLabel: '服务2'
      }, {
        dictValue: '3',
        dictLabel: '服务3'
      }, {
        dictValue: '4',
        dictLabel: '服务4'
      }],
      cs_link_circ: [],
      cs_classify_level1: [],
      cs_classify_level2: [],
      cs_drop_status: [],
      cs_reason_level1: [],
      cs_reason_level2: [],
      cs_reason_level3: [],
      dictList: [],
      cs_identity: [],
      cs_relation: [],
      cs_feedback_type: [],
      cs_end_case: [],
      cs_order_state: [],//状态
      cs_action_type: [],//操作类型
      cs_priority: [],//优先级
      cs_service_item: [],//服务项目
      cs_business_type: [],//服务项目
      cs_channel: [],//渠道
      cs_organization: [],//出单机构
      cs_sex: [],//性别
      cs_communication_language: [],//语言
      cs_whether_flag: [],
      cs_mediation_appraisal: [],
      cs_risk_type: [],
      cs_question_circ: [],
    }
  },
  created() {
    debugger
    this.queryParams.workOrderNo = this.$route.query.workOrderNo;
    this.queryParams.policyNo = this.$route.query.policyNo;
    this.queryParams.policyItemNo = this.$route.query.policyItemNo;
    this.queryParams.status = this.$route.query.status;
    this.flag= this.$route.query.flag;
    //window.aaa = this;
    this.searchHandle()
    this.searchFlowLog()
    this.searchHCS()
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
    this.cs_link_circ = this.dictList.find(item => {
      return item.dictType === 'cs_link_circ'
    }).dictDate
    this.cs_classify_level1 = this.dictList.find(item => {
      return item.dictType === 'cs_classify_level1'
    }).dictDate
    this.cs_drop_status = this.dictList.find(item => {
      return item.dictType === 'cs_drop_status'
    }).dictDate
    this.cs_reason_level1 = this.dictList.find(item => {
      return item.dictType === 'cs_reason_level1'
    }).dictDate
    this.cs_action_type = this.dictList.find(item => {
      return item.dictType === 'cs_action_type'
    }).dictDate
    this.cs_order_state = this.dictList.find(item => {
      return item.dictType === 'cs_order_state'
    }).dictDate
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_business_type = this.dictList.find(item => {
      return item.dictType === 'cs_business_type'
    }).dictDate
    this.cs_mediation_appraisal = this.dictList.find(item => {
      return item.dictType === 'cs_mediation_appraisal'
    }).dictDate
    this.cs_risk_type = this.dictList.find(item => {
      return item.dictType === 'cs_risk_type'
    }).dictDate
    this.cs_question_circ = this.dictList.find(item => {
      return item.dictType === 'cs_question_circ'
    }).dictDate
  },
  methods: {
    reasonTwo(flag) {
      const query = {}
      query.parentCode = this.sendForm.reason1;
      if(flag=='1'){
        this.sendForm.reason2 = '';
        this.sendForm.reason3 = '';
      }
      reasonTwo(query).then(res => {
        if (res != null && res.code === 200) {
          console.log("cs_reason_level2", res.data)
          this.cs_reason_level2 = res.data
          if (res.rows.length <= 0) {
            return false
          }
        }
      }).catch(res => {

      })

    },
    reasonThree(flag) {
      const query = {}
      query.parentCode = this.sendForm.reason2
      if(flag=='1'){
        this.sendForm.reason3 = '';
      }
      reasonThree(query).then(res => {
        if (res != null && res.code === 200) {
          console.log("cs_reason_level2", res.data)
          this.cs_reason_level3 = res.data
          if (res.rows.length <= 0) {
            return false
          }
        }
      }).catch(res => {

      })

    },
    classTwo(flag) {
      const query = {}
      query.parentCode = this.sendForm.level1;
      if(flag=='1'){
        this.sendForm.level2 = '';
      }
      classTwo(query).then(res => {
        if (res != null && res.code === 200) {
          console.log("二级分类", res.data)
          this.cs_classify_level2 = res.data
          if (res.rows.length <= 0) {
            return false
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
      let workOrderNo = this.queryParams.workOrderNo
      complainSearch(workOrderNo).then(res => {
        console.log('callcenter', res.data)
        if (res != null && res.code === 200) {
          this.workPoolData = res.data

          if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
    //反显信息需求
    searchHandleServer() {
      let query=this.queryParams
      complainSearchServer(query).then(res => {
        if (res != null && res.code === 200) {
          console.log("投诉页面server反显数据",res.data)
          this.sendForm = res.data;
          this.reasonTwo('0');
          this.reasonThree('0');
          this.classTwo('0');
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
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

    //转办
    transfer() {
      this.$refs.transfer.transferForm.workOrderNo = this.queryParams.workOrderNo
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
    //打开修改对话框

    //暂存
    temporary() {
      let insert = this.sendForm
      insert.sign = "01"
      insert.workOrderNo = this.$route.query.workOrderNo
      collaborativeTemporary(insert).then(res => {
        if (res != null && res.code === 200) {
          console.log("insert", insert)
          this.$message.success("修改成功")
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
        console.log(workOrderNo)
        console.log('轨迹表', res.rows)
        if (res != null && res.code === 200) {
          this.flowLogData = res.rows
          this.flowLogCount = res.total
          console.log("searchFlowLog", this.flowLogData)
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
        console.log(workOrderNo)
        console.log('HCS', res.rows)
        if (res != null && res.code === 200) {
          this.HCSPoolData = res.rows
          this.HCSTotal = res.total
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
    //修改按钮
    modify() {
      if (this.ids.length == 0) {
        this.$message.success("先选中一行")
      }
        // else {if(this.ids.length>2){
        //   this.$message.success("选中一行")
      // }
      else {
        this.$router.push({
          path: '/customService/modify',
          query: {
            workOrderNo: this.queryParams.workOrderNo,
            policyNo: this.queryParams.policyNo,
            policyItemNo: this.queryParams.policyItemNo,
            status: this.queryParams.status
          }
        })
        // }
      }
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.workOrderNo);

    },

  }
}
</script>

<style scoped>
.item-width {
  width: 170px;
}
</style>
