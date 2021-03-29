<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="170px"
               label-position="right" size="mini" disabled>
        <el-row>
          <!--clearable是清楚输入框内容 readly、只读不可以编辑 ；不可以共存-->
          <el-col :span="8">
            <el-form-item label="保单号：" prop="Service">
              <el-input readonly v-model="sendForm.acceptor" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel" readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人出生日期：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件类型：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保益生效日：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保益满期日：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="主招揽业务员电话：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="是否UHCG会员：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="AM(服务经理)：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="BD(销售经理)：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="特定医院赔付比例：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>


    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="170px"
               :disabled="isDisabled"
               label-position="right" size="mini">

        <span style="color: blue">{{selectDictLabel(cs_service_item, sendForm.itemCode)}}-服务受理信息</span>
        <el-divider/>

        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channelCode">
              <el-select v-model="sendForm.channelCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterNo">
              <el-input v-model="sendForm.callCenterId" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="sendForm.priorityLevel" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="callPerson.name">
              <el-input v-model="sendForm.callPerson.name" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="sendForm.callPerson.mobilePhone" class="item-width" size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="传真：" prop="phone">
              <el-input v-model="sendForm.fax" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人与申请人关系：" prop="priority">
              <el-select v-model="sendForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input v-model="sendForm.contactsPerson.name" class="item-width" size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-select v-model="sendForm.contactsPerson.sex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="ContactsLanguage">
              <el-select v-model="sendForm.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人电话：" prop="contactsPerson.mobilePhone">
              <el-input v-model="sendForm.contactsPerson.mobilePhone" class="item-width" size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人家庭电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.contactsPerson.homePhone" class="item-width" size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人办公电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.contactsPerson.workPhone" class="item-width" size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="beInsuredName">
              <el-input v-model="sendForm.email" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="sendForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="appointmentDate">
              <el-date-picker class="item-width"
                              v-model="sendForm.appointmentDate"
                              type="date"
                              placeholder="选择日期时间"
                              value-format="YYYY-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约时间："  style="white-space: nowrap" prop="complaintTimes">
              <el-time-picker class="item-width"
                              is-range
                              v-model="sendForm.complaintTimes"
                              range-separator="-"
                              start-placeholder="开始时间"
                              end-placeholder="结束时间"
                              value-format="HH:mm:ss">
              </el-time-picker>
            </el-form-item>
          </el-col>
          <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="170px"
                   label-position="right" size="mini">
            <el-col :span="8">
              <el-form-item label="医疗机构：" prop="hospitalName">
                <el-input :disabled="isDisabled" v-model="sendForm.hospitalName" input-w class="item-width2" size="mini"
                          placeholder="请输入"/>
                <el-button type="primary" @click="openHospitalShow">详细信息</el-button>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="hospitalWorkCall">
              <el-input v-model="sendForm.hospitalWorkCall" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="department">
              <el-input v-model="sendForm.department" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" prop="compensationRatio">
              <el-input v-model="sendForm.compensationRatio" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="validCertificate">
              <el-select v-model="sendForm.validCertificate" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否持保险卡：" prop="settlementCard">
              <el-select v-model="sendForm.settlementCard" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="symptomTimes">
              <el-input v-model="sendForm.symptomTimes" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="earliestTime">
              <el-input v-model="sendForm.earliestTime" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="disease">
              <el-input v-model="sendForm.disease" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病或体征：" prop="symptomsSigns">
              <el-input v-model="sendForm.symptomsSigns" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否首次就诊：" prop="firstFlag">
              <el-select v-model="sendForm.firstFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否意外：" prop="accidentFlag">
              <el-select v-model="sendForm.accidentFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="意外原因：" prop="accidentReason">
              <el-input v-model="sendForm.accidentReason" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="beInsuredName">
            <el-input v-model="sendForm.content" class="width-full" size="mini" placeholder="请输入"/>
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
          <el-table-column align="center" prop="status" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.linkCode">
              <span>{{ selectDictLabel(cs_order_state, scope.row.linkCode) }}</span>
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
              <span>{{ scope.row.createdTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='03'" style="font-size:12px" type="primary"
                       @click="modifyDetails(scope.row)">修改说明
              </el-link>
            </template>
          </el-table-column>
          <modify-details ref="modifyDetails"></modify-details>
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
          <el-table-column prop="alterTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.alterTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="alterId" align="center" label="修改序列号" show-overflow-tooltip/>
          <el-table-column prop="alterChannel" align="center" label="预约渠道" show-overflow-tooltip/>
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

    <el-card>
      <el-form ref="submitForm" :model="submitForm" :rules="changeForm.rules" style="padding-bottom: 30px;"
               label-width="130px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理
          <div style="text-align: right; margin-right: 8px;">
            <el-button type="primary" size="mini" @click="modify">修改</el-button>
            <el-button type="primary" size="mini" @click="cancle">取消</el-button>
          </div>
        </span>

        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="处理时长：" prop="times">
              <el-input v-model="submitForm.times" class="width-full" size="mini" disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况：" prop="businessProcess">
              <el-radio-group v-model="submitForm.businessProcess"
                              @change="isBusinessProcess(submitForm.businessProcess)">
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
              v-model="submitForm.remark">
            </el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="客户反馈：" prop="customerFeedback">
            <el-radio-group v-model="submitForm.customerFeedback">
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
            <el-form-item label="是否需要担保函：" prop="costIncurred">
              <el-select v-model="submitForm.costIncurred" class="item-width" placeholder="请选择"
                         controls-position="right" :min="0">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
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
        <modify-details ref="modifyDetails"></modify-details>
        <transfer ref="transfer" @checkButton="checkButton"></transfer>
        <up-load ref="upload"></up-load>
        <co-organizer ref="coOrganizer" @checkButton="checkButton"></co-organizer>
        <el-button type="primary" size="mini" @click="transfer" :disabled="checkButtonFlag.transferFlag">转办</el-button>
        <el-button type="primary" size="mini" @click="coOrganizer" disabled>担保函导出</el-button>
        <el-button type="primary" size="mini" @click="upload" disabled>保单信息查询</el-button>
        <el-button type="primary" size="mini" @click="temporary" :disabled="checkButtonFlag.temporaryFlag">暂存
        </el-button>
        <el-button type="primary" size="mini" @click="submit" :disabled="checkButtonFlag.submitFlag">提交</el-button>
        <el-button type="primary" size="mini" @click="upload" disabled>发送短信</el-button>

      </div>
    </el-card>


  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch, HMSSearch} from '@/api/customService/demand'
  import {
    demandListAndPublicPool,
    demandListAndPersonalPool,
    dealReservationSubmit
  } from '@/api/customService/reservation'

  import transfer from "../common/modul/transfer";
  import upLoad from "../common/modul/upload";
  import coOrganizer from "../common/modul/coOrganizer";
  import modifyDetails from "../common/modul/modifyDetails";
  import {complainSearchServer} from '@/api/customService/complaint';


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
    {dictType: 'cs_direct_settlement'},
    {dictType: 'cs_consultation_type'},
    {dictType: 'cs_time_unit'},
    {dictType: 'cs_service_item'},
    {dictType: 'cs_action_type'},
    {dictType: 'cs_order_state'},
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
        Service: [
          {required: true, message: "服务项目不能为空", trigger: "blur"}
        ],
        priority: [
          {required: true, message: "优先级不能为空", trigger: "blur"}
        ],
        lxperson: [
          {required: true, message: "联系人不能为空", trigger: "blur"}
        ],
        orderNum: [
          {required: true, message: "联系人与被保人关系不能为空", trigger: "blur"}
        ],
        times: [
          {required: true, message: "处理时长关系不能为空", trigger: "blur"}
        ],
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
        costsIncurred: [
          {required: true, message: "是否需要担保函不能为空", trigger: "blur"}
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
        changeForm: {
          rules: isRule
        },
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
          fax: "",
          workOrderNo: "",
          businessProcess: "01",
          remark: "",
          customerFeedback: "01",
          closeType: "01",
          costsIncurred: ""
        },
        checkButtonFlag: {
          transferFlag: false,//协办
          coOrganizerFlag: false,//转办
          temporaryFlag: false,//暂存
          submitFlag: false//提交
        },
        checkButtonFrom: {//传值给后台
          acceptorTime: undefined,//受理时间数组
          appointmentTime: undefined,//预约时间数组
          handlerTime: undefined,//处理时间数组
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
          priorityLevel: "",//优先级
          vipFlag: "",//VIP标识
          mobilePhone: "",//移动电话
          status: "",//状态
          modifyTime: "",
          updateTime: ""
        },

        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        //新增的数据传输
        sendForm: {
          email: "",
          hospitalCode: "",
          hospitalName: "",
          workOrderNo: '',
          businessProcess: '',
          contactsPerson: {
            homePhone1: [],
            name: "",
            sex: "",
            mobilePhone: "",
            address: "",
            homePhone: "",
            workPhone: "",
            language: "",
          },//联系人
          callPerson: {},
          validCertificate: "",
        },
        //submnit提交
        submitForm: {
          sign: "",
          workOrderNo: "",
          customerFeedback: "",
          times: "",
          businessProcess: "",
          remark: "",
          costIncurred: "",

        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          workOrderNo: "",
          policyNo: "",
          policyItemNo: "",
          businessType: "02",
          status: "",
          pageNum: 1,
          pageSize: 10
        },
        loading: true,
        //数据反显用
        workPoolData: "",
        //HCS服务预约预修改记录
        HCSPoolData: [],
        HCSTotal: 0,
        //业务处理信息
        dealInfo: [],

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
        cs_channel: [],//受理渠道
        cs_reservation_item: [],//服务项目
        cs_sex: [],//性别
        cs_priority: [],//优先级
        cs_communication_language: [],//语言
        cs_organization: [],//机构
        cs_handle_state: [],// 状态：
        dictList: [],
        cs_identity: [],
        cs_whether_flag: [],
        cs_relation: [],
        cs_feedback_type: [],
        cs_end_case: [],
        cs_direct_settlement: [],
        cs_consultation_type: [],
        cs_time_unit: [],
        cs_service_item: [],
        cs_order_state: [],
        cs_action_type: [],
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
      this.searchHCS()
      this.searchHandleServer()

      // this.getDicts("sys_oper_type").then(response => {
      //   this.states = response.data;
      //   console.log("response:",response)
      // });

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
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
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
      this.cs_direct_settlement = this.dictList.find(item => {
        return item.dictType === 'cs_direct_settlement'
      }).dictDate
      this.cs_consultation_type = this.dictList.find(item => {
        return item.dictType === 'cs_consultation_type'
      }).dictDate
      this.cs_time_unit = this.dictList.find(item => {
        return item.dictType === 'cs_time_unit'
      }).dictDate
      this.cs_service_item = this.dictList.find(item => {
        return item.dictType === 'cs_service_item'
      }).dictDate
      this.cs_order_state = this.dictList.find(item => {
        return item.dictType === 'cs_order_state'
      }).dictDate
      this.cs_action_type = this.dictList.find(item => {
        return item.dictType === 'cs_action_type'
      }).dictDate
      //初始化按钮状态
      this.checkButton();
    },
    methods: {

      hiddenShow: function () {
        // 返回上级路由并关闭当前路由
        this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
        this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)

      },

      //是否响应
      isBusinessProcess(s) {
        if (s == "01") {
          this.changeForm.rules = this.rules1
        } else {
          this.changeForm.rules = this.rules2
          this.$refs.submitForm.clearValidate()
        }
        //更新按钮状态
        this.checkButton();
      },
      //设置按钮状态
      checkButton() {
        this.checkButtonFlag.transferFlag = false;//协办
        this.checkButtonFlag.coOrganizerFlag = false;//转办
        this.checkButtonFlag.temporaryFlag = false;//暂存
        this.checkButtonFlag.submitFlag = false;//提交

        //选择响应后  除【提交按钮外其余都置灰】
        if (this.submitForm.businessProcess == '02') {
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
      //超链接用
      modifyDetails(s) {
        this.$refs.modifyDetails.queryParams.subId = s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo = this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;
      },
      //反显暂存信息
      searchHandleServer() {
        let insert = this.queryParams
        insert.businessType = "02"
        complainSearchServer(insert).then(res => {
          if (res != null && res.code === 200) {
            console.log("预约页面server反显数据", res.data)

            this.submitForm = res.data;
            console.log(this.submitForm, "85848541484848")
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //新增按钮
      submit() {
        this.$refs.submitForm.validate((valid) => {
          if (valid) {
            const send = this.submitForm
            send.sign = "02",
              send.workOrderNo = this.queryParams.workOrderNo = this.$route.query.workOrderNo
            dealReservationSubmit(send).then(res => {

              if (res != null && res.code === 200) {
                this.$message.success("保存成功！");
                this.hiddenShow();
                if (res.rows.length <= 0) {
                  return this.$message.warning(
                    "提交失败！"
                  )
                }
              }
            }).catch(res => {

            })
          }else{
            this.$message.warning("请录入必录项");
          }
        })
      },
      temporary() {
        const send = this.submitForm
        send.sign = "01",
          send.workOrderNo = this.queryParams.workOrderNo = this.$route.query.workOrderNo
        dealReservationSubmit(send).then(res => {

          if (res != null && res.code === 200) {
            this.$message.success("暂存成功！")
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
      //修改按钮
      modify() {
        this.$router.push({
          path: '/customService/reservation/modify',
          query: {
            workOrderNo: this.queryParams.workOrderNo,
            policyNo: this.queryParams.policyNo,
            policyItemNo: this.queryParams.policyItemNo,
            status: this.queryParams.status
          }
        })
      },
      //取消按钮
      cancle() {
        this.$router.push({
          path: '/customService/reservation/cancle',
          query: {
            workOrderNo: this.queryParams.workOrderNo,
            policyNo: this.queryParams.policyNo,
            policyItemNo: this.queryParams.policyItemNo,
            status: this.queryParams.status
          }
        })
      },
      //反显信息需求
      searchHandle() {
        let query = this.queryParams
        console.log("query", query)
        demandListAndPersonalPool(query).then(res => {
          console.log('共公池', res.rows)
          if (res != null && res.code === 200) {
            this.sendForm = res.rows[0];
            this.totalCount = res.total;
            console.log('response', res.total);
            if(this.sendForm.complaintTime != null && this.sendForm.complaintTime !=''){
              //预约时间反显
              let timeArr=this.sendForm.complaintTime.split('-');
              this.$set(this.sendForm, `complaintTimes`, timeArr);
            }
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

      //转办
      transfer() {
        console.info("ddddd" + this.queryParams.workOrderNo);
        this.$refs.transfer.dynamicValidateForm.workOrderNo = this.queryParams.workOrderNo;
        this.$refs.transfer.open();
      },
      //协办
      coOrganizer() {
        this.$refs.coOrganizer.open(this.queryParams.workOrderNo);

      },
      resetForm() {
        this.$refs.sendForm.resetFields()
      },

      handleSelectionChange(val) {
        this.dataonLineListSelections = val
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
              /*return this.$message.warning(
                "未查询到数据！"
              )*/
            }
          }
        }).catch(res => {

        })
      },
      openHospitalShow() {
        const newpage = this.$router.resolve({
          name: 'hospitalDetail',
          query: {
            providerCode: this.sendForm.medicalInstitution
          }
        })
        window.open(newpage.href, '_blank');
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 180px;
  }

  .item-width2 {
    width: 100px;
  }
</style>
