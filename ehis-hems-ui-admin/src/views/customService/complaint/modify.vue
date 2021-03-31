<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="workPoolData" :model="workPoolData" :rules="rules" label-position="right" label-width="160px"
               size="mini" style="padding-bottom: 30px;">

        <span style="color: blue">服务项目：{{selectDictLabel(cs_service_item, workPoolData.itemCode)}}受理【修改】</span>
        <el-divider></el-divider>

        <el-row>
          <el-form-item label="受理渠道：" prop="channelCode">
              <el-radio-group v-model="workPoolData.channelCode">
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
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input  v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width"  >
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人号码：" prop="callPerson.mobilePhone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人手机：" prop="contactsPerson.mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" maxlength="15"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人地址：" prop="contactsPerson.address"  >
              <el-input v-model="workPoolData.contactsPerson.address" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">

            <el-form-item label="预约时间：" prop="complaintTime"  style="white-space: nowrap">
              <el-date-picker
                v-model="workPoolData.complaintTime"
                class="item-width"
                placeholder="选择日期时间"
                type="datetime">
              </el-date-picker>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row >
          <el-col :span="8">
            <el-form-item label="投诉人姓名：" prop="complaintPerson.name">
              <el-input v-model="workPoolData.complaintPerson.name" class="item-width"  size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人性别：" prop="complaintPerson.sex">
              <el-select v-model="workPoolData.complaintPerson.sex" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：" prop="complaintPerson.identity">
              <el-select v-model="workPoolData.complaintPerson.identity" class="item-width"  >
                <el-option v-for="item in cs_identity" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="3">
            <el-form-item label="家庭电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[0]">
              国家区号:+
              <el-input v-model="workPoolData.contactsPerson.homePhone1[0]" class="item-width" style="width: 60px" maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[1]">
              区号
              <el-input v-model="workPoolData.contactsPerson.homePhone1[1]" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[2]">
              号码
              <el-input v-model="workPoolData.contactsPerson.homePhone1[2]" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.homePhone1[3]">
              分机号
              <el-input v-model="workPoolData.contactsPerson.homePhone1[3]" class="item-width" size="mini" style="width: 145px"
                        maxlength="4"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <el-form-item label="办公电话：" style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[0]">
              国家区号:+
              <el-input v-model="workPoolData.contactsPerson.workPhone1[0]" class="item-width" style="width: 60px" maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[1]">
              区号
              <el-input v-model="workPoolData.contactsPerson.workPhone1[1]" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[2]">
              号码
              <el-input v-model="workPoolData.contactsPerson.workPhone1[2]" class="item-width" size="mini" style="width: 145px"
                        maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item style="white-space: nowrap;" :inline="true" prop="contactsPerson.workPhone1[3]">
              分机号
              <el-input v-model="workPoolData.contactsPerson.workPhone1[3]" class="item-width" size="mini" style="width: 145px"
                        maxlength="4"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width"  >
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width"  >
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否已劝解：" prop="contactsSex">
              <el-select v-model="workPoolData.persuasionFlag" class="item-width"  >
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="预约时间："  style="white-space: nowrap"  prop="complaintTime">
              <el-date-picker
                v-model="workPoolData.complaintTime"
                type="datetime"
                placeholder="选择日期时间"
                value-format="yyyy-MM-dd HH:mm:ss">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="投诉内容：" prop="content">
          <el-input
            v-model="workPoolData.content"
            :rows="3"
            type="textarea">
          </el-input>
        </el-form-item>
      </el-form>
    </el-card>

    <!--服务处理 -->
    <el-card v-if="showFlag" class="box-card" style="margin-top: 10px;">
      <el-form  ref="sendForm" :model="sendForm"  style="padding-bottom: 30px;" label-width="180px"
                label-position="right" size="mini" >
        <span style="color: blue">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="一级投诉分类：" prop="level1">
              <el-select v-model="sendForm.level1" class="item-width" @change="classTwo('1')">
                <el-option v-for="item in cs_classify_level1" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉分类：" prop="level2">
              <el-select v-model="sendForm.level2" class="item-width" @change="checkLevel2()">
                <el-option v-for="item in cs_classify_level2" :key="item.code" :label="item.codeName"
                           :value="item.code"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否计件：" prop="pieceworkFlag"  v-show="sendForm.level2=='05'">
              <el-select v-model="sendForm.pieceworkFlag" class="item-width">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="撤诉状态：" prop="complaintStatus" v-show="sendForm.level2=='05'">
              <el-select v-model="sendForm.complaintStatus" class="item-width" >
                <el-option v-for="item in cs_drop_status" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉是否成立：" prop="complaintTenable">
              <el-select v-model="sendForm.complaintTenable" class="item-width" @change="checkComplaintTenable()">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉不成立理由：" prop="faseReason" >
              <el-input v-model="sendForm.faseReason"
                        class="item-width"
                        clearable size="mini"
                        :disabled="sendForm.complaintTenable!='02'"
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
            <el-form-item label="三级投诉原因：" prop="reason3">
              <el-select v-model="sendForm.reason3" class="item-width">
                <el-option v-for="item in cs_reason_level3" :key="item.code" :label="item.codeName"
                           :value="item.code"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保环节（报保监）" prop="complaintLink">
              <el-select v-model="sendForm.complaintLink" class="item-width">
                <el-option v-for="item in cs_link_circ" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保问题(报保监)：" prop="complaintQuestion">
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
                <el-option v-for="item in cs_marketingchannel_codeOption" :key="item.dictValue" :label="item.dictLabel"
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
          <el-form-item label="致诉根因：" prop="actionCause">
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
          <el-form-item label="根因改善：" prop="rootImprovement">
            <el-input v-model="sendForm.rootImprovement" class="item-width" clearable size="mini" placeholder="请输入" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉损失：" prop="actPromptly">
            <el-input v-model="sendForm.actPromptly" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-form>
    </el-card>
<!--附件信息-->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color:blue;">附件信息</span>
        <el-divider></el-divider>
        <el-table
          :data="flowLogData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          highlight-current-row
          size="small"
          style=" width: 100%;"
          tooltip-effect="dark">
          <el-table-column align="center" label="附件名称" prop="status" show-overflow-tooltip width="140"/>
          <el-table-column align="center" label="附件类型" prop="operateCode" show-overflow-tooltip/>
          <el-table-column align="center" label="上传人" prop="makeBy" show-overflow-tooltip/>
          <el-table-column align="center" label="上传时间" prop="makeTime" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.makeTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="备注" prop="remarks" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button disabled size="mini" type="text" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="flowLogCount>0"
          :limit.sync="queryParams.pageSize"
          :page.sync="queryParams.pageNum"
          :total="flowLogCount"
          @pagination="searchFlowLog"
        />
      </div>
    </el-card>

<!--    <el-card>
      <el-form   v-if="showFlag" label-position="right"
               label-width="145px" size="mini" style="padding-bottom: 30px;">
        <span style="color: blue">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="一级投诉分类：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉分类：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="三级投诉分类：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="撤诉状态：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉是否成立：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉不成立理由：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>


        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="重复投诉：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="一级投诉原因：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉原因：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否计件：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉环节(报保监)：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉问题(报保监)：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="行协调解或外部鉴定状态：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="险种类型：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="营销渠道：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in cs_marketingchannel_codeOption" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
          <el-form-item label="投诉业务类别：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  class="item-width" clearable size="mini"/>
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户反馈：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="跟因改善：" prop="policyNo">
              <el-input v-model="workPoolData.policyNo" class="item-width" clearable placeholder="请输入" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉损失：" prop="policyNo">
              <el-input v-model="workPoolData.policyNo" class="item-width" clearable placeholder="请输入" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="投诉根因部门：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  class="width-full" clearable size="mini" />
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="致诉根因：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  class="width-full" clearable size="mini" />
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="处理结果：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  class="width-full" clearable size="mini"/>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>-->

    <el-card class="box-card" style="margin-top: 10px;">
      <el-form  ref="workPoolData3"  :model="workPoolData" :rules="rules"
               label-position="right" label-width="100px" size="mini" style="padding-bottom: 30px;">
        <span style="color: blue">修改原因</span>
        <el-divider></el-divider>
        <el-row>
            <el-form-item label="修改原因："  prop="editInfo.editReason">
              <el-radio-group v-model="workPoolData.editInfo.editReason">
                <el-radio label="01">客户申请变动</el-radio>
                <el-radio label="02">操作失误</el-radio>
                <el-radio label="03">其他原因</el-radio>
              </el-radio-group>
            </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="editInfo.editRemark">
            <el-input
              v-model="workPoolData.editInfo.editRemark"
              :rows="2"
              placeholder="请输入内容"
              type="textarea">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div style="text-align: right; margin-right: 1px;">
      <el-button  size="mini" type="primary" @click="submit">保存</el-button>
      <el-button  size="mini"type="primary" @click="hiddenShow">关闭</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch} from '@/api/customService/demand'
  import upLoad from "../common/modul/upload";
  import {complainSearch,comSearch}  from  '@/api/customService/consultation'
  import {
    classTwo, complaintDealSubmit,
    modifyComplaintSubmit,
    reasonThree,
    reasonTwo,
    selectServiceProcess
  } from '@/api/customService/complaint'



  let dictss = [
    {dictType: 'cs_identity'},
    {dictType: 'cs_sex'},
    {dictType: 'cs_communication_language'},
    {dictType: 'cs_service_item'},
    {dictType: 'cs_organization'},
    {dictType: 'cs_priority'},
    {dictType: 'cs_channel'},
    {dictType: 'cs_whether_flag'},
    {dictType: 'cs_marketingchannel_code'},
    {dictType: 'cs_channel'},
    {dictType: 'cs_priority'},
    {dictType: 'cs_sex'},
    {dictType: 'cs_communication_language'},
    {dictType: 'cs_identity'},
    {dictType: 'cs_whether_flag'},
    {dictType: 'cs_organization'},
    {dictType: 'cs_relation'},
    {dictType: 'cs_order_state'},
    {dictType: 'cs_action_type'},
    {dictType: 'cs_feedback_type'},
    {dictType: 'cs_question_circ'},
    {dictType: 'cs_risk_type'},
    {dictType: 'cs_mediation_appraisal'},
    {dictType: 'cs_drop_status'},
    {dictType: 'cs_reason_level1'},
    {dictType: 'cs_link_circ'},
    {dictType: 'cs_classify_level1'}

  ]
  export default {
    components: { upLoad },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD HH:mm:ss')
        }
      }
    },
    data() {

      const checkComplaintTime= (rule, value, callback) => {
        let tDate = moment(new Date()).format('yyyy-MM-DD HH:mm:ss');
        if(tDate > value){
          callback(new Error("预约时间不能早于当前日期"));
        }else{
          callback();
        }
      }

      return {
        //服务处理数据
        sendForm: {
          sign: "",//控制暂存还是提交用
          level1: "",
          level2: "",
          pieceworkFlag: "",
          complaintStatus: "",
          complaintTenable: "",
          acceptor: "",
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
        //服务信息是否展示
        showFlag: false,
        workPoolDataFlag:false,
        workPoolDataFlag3:false,
        //下拉框
        cs_priority:[],//优先级
        cs_service_item:[],//服务项目
        cs_channel:[],//渠道
        cs_organization:[],//出单机构
        cs_sex:[],//性别
        cs_communication_language:[],//语言
        cs_whether_flag:[],
        cs_identity:[],
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        //回显服务项目
        //需要填入数据的部分
        workPoolData: {
          channelCode:"",//受理渠道
          callCenterId:"",//电话中心业务流水号
          contactsPerson:{
            homePhone1:[],
            workPhone1:[],
            name:"",
            sex:"",
            mobilePhone:"",
            address:"",
            homePhone:"",
            workPhone:"",
            language:"",
          },//联系人
          persuasionFlag:"",
          callPerson: {
            mobilePhone:"",
          },
          priorityLevel:"",//优先级
          email:"",//邮件
          workOrderNo:"",//工单号
          complaintTime:"",
          organCode:"",//出单机构

          complaintPerson:{
            name:"",
            sex:"",
            identity:"",

          },
          content:"",
          editInfo:{
            editReason:"",//修改原因
            editRemark:"" //修改说明

          },





        },





        cs_reason_level1: [],
        cs_reason_level2: [],
        cs_reason_level3: [],
        cs_link_circ: [],
        cs_classify_level1: [],
        cs_classify_level2: [],
        cs_drop_status: [],
        cs_mediation_appraisal: [],
        cs_risk_type: [],
        cs_question_circ: [],
        cs_feedback_type: [],


        businessType:"",
        totalCount: 0,
        // 表单校验
        rules: {
          'contactsPerson.sex': [
            {required: true, message: "联系人性别", trigger: "blur"}
          ],
          'contactsPerson.name': [
            {required: true, message: "联系人姓名不能为空", trigger: "blur"}
          ],
          complaintTime: [
            {required: true, message: "预约时间不能为空", trigger: "blur"},
            {required: true, validator: checkComplaintTime, trigger: "blur"}
          ],
          'complaintPerson.name': [
            {required: true, message: "投诉人姓名不能为空", trigger: "blur"}
          ],
        'complaintPerson.sex': [
            {required: true, message: "投诉人性别不能为空", trigger: "blur"}
          ],
          content: [
            {required: true, message: "投诉内容不能为空", trigger: "blur"},
            { min: 0, max: 2000, message: '长度不超过2000个字符' }
          ],
          'editInfo.editRemark':[
            {required: true, message: "修改说明不能为空", trigger: "blur"},
            { min: 3, max: 100, message: '长度在 3 到 100 个字符' }
          ],
          'editInfo.editReason':[
            {required: true, message: "修改原因不能为空", trigger: "blur"}
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
          email: [
            {required: false,
              message: "邮箱格式不正确",
              pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
              trigger: ["blur","change"]
            }
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
        },
        // 查询参数
        queryParams: {
          businessType:"",
          pageNum: 1,
          pageSize: 10,
          workOrderNo:"",
          policyNo:"",
          policyItemNo:"",
          status:""
        },
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
        cs_marketingchannel_codeOption: [],
      }
    },
    created() {
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      this.queryParams.businessType=this.$route.query.businessType;
      if(this.queryParams.status=='04'){
        this.showFlag=true
        this.searchHandleServer()
      }
      else {
        this.showFlag=false
      }

      this.searchHandle()
      this.searchFlowLog()
    },
    async mounted() {
      // 字典数据统一获取
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      // 下拉项赋值
      this.cs_communication_language = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.cs_service_item = this.dictList.find(item => {
        return item.dictType === 'cs_service_item'
      }).dictDate
      this.cs_organization = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.cs_channel = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.cs_whether_flag = this.dictList.find(item => {
        return item.dictType === 'cs_whether_flag'
      }).dictDate
      this.cs_sex = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.cs_identity = this.dictList.find(item => {
        return item.dictType === 'cs_identity'
      }).dictDate
      this.cs_marketingchannel_code = this.dictList.find(item => {
        return item.dictType === 'cs_marketingchannel_code'
      }).dictDate
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
      this.cs_order_state = this.dictList.find(item => {
        return item.dictType === 'cs_order_state'
      }).dictDate
      this.cs_action_type = this.dictList.find(item => {
        return item.dictType === 'cs_action_type'
      }).dictDate
      this.cs_feedback_type = this.dictList.find(item => {
        return item.dictType === 'cs_feedback_type'
      }).dictDate
      this.cs_question_circ = this.dictList.find(item => {
        return item.dictType === 'cs_question_circ'
      }).dictDate
      this.cs_risk_type = this.dictList.find(item => {
        return item.dictType === 'cs_risk_type'
      }).dictDate
      this.cs_marketingchannel_codeOption = this.dictList.find(item => {
        return item.dictType === 'cs_marketingchannel_code'
      }).dictDate




      this.cs_mediation_appraisal = this.dictList.find(item => {
        return item.dictType === 'cs_mediation_appraisal'
      }).dictDate
      this.cs_drop_status = this.dictList.find(item => {
        return item.dictType === 'cs_drop_status'
      }).dictDate
      this.cs_reason_level1 = this.dictList.find(item => {
        return item.dictType === 'cs_reason_level1'
      }).dictDate
      this.cs_link_circ = this.dictList.find(item => {
        return item.dictType === 'cs_link_circ'
      }).dictDate
      this.cs_classify_level1 = this.dictList.find(item => {
        return item.dictType === 'cs_classify_level1'
      }).dictDate





    },
    methods: {
      //提交页面数据
      submit(){
        this.$refs.workPoolData.validate((valid) => {
          this.workPoolDataFlag=valid
        })
        this.$refs.workPoolData3.validate((valid) => {
          this.workPoolDataFlag3=valid
        })
        if (this.workPoolDataFlag&&this.workPoolDataFlag3) {
          let insert = this.workPoolData
          insert.workOrderNo = this.$route.query.workOrderNo
          if(this.showFlag){
            insert.flag='true'
            insert.complaintDealVo=this.sendForm
          /*  insert.sign = this.sendForm.sign
            insert.level1 = this.sendForm.level1
            insert.level2 = this.sendForm.level2
            insert.pieceworkFlag = this.sendForm.pieceworkFlag
            insert.complaintStatus = this.sendForm.complaintStatus
            insert.complaintTenable = this.sendForm.complaintTenable
            insert.acceptor = this.sendForm.acceptor
            insert.faseReason = this.sendForm.faseReason
            insert.repeatedComplaint = this.sendForm.repeatedComplaint
            insert.reason1 = this.sendForm.reason1
            insert.reason2 = this.sendForm.reason2
            insert.reason3 = this.sendForm.reason3
            insert.complaintLink = this.sendForm.complaintLink
            insert.complaintQuestion = this.sendForm.complaintQuestion
            insert.outsideState = this.sendForm.outsideState
            insert.riskType = this.sendForm.riskType
            insert.marketChannel = this.sendForm.marketChannel
            insert.complaintCategory = this.sendForm.complaintCategory
            insert.rootDepartment = this.sendForm.rootDepartment
            insert.actionCause = this.sendForm.actionCause
            insert.treatmentProgress = this.sendForm.treatmentProgress

            insert.treatmentResult = this.sendForm.treatmentResult
            insert.customerFeedback = this.sendForm.customerFeedback
            insert.rootImprovement = this.sendForm.rootImprovement
            insert.actPromptly = this.sendForm.actPromptly
            insert.improvementMeasures = this.sendForm.improvementMeasures
            insert.businessProcess = this.sendForm.businessProcess*/
          }
          modifyComplaintSubmit(insert).then(res => {
            if (res != null && res.code === 200) {
              this.$message.success("保存成功")
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "失败！"
                )
              }
            }
          }).catch(res => {

          })
        }else {
          return this.$message.warning(
            "请检查输入信息格式！"
          )
        }

      },
      //关闭页面
      close(){

      },
      //服务处理查询
      searchHandleServer() {
        selectServiceProcess(this.queryParams.workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            console.log('-------------')
            if(res.data!=null && res.data!='') {
              this.sendForm = res.data;
              this.reasonTwo('0');
              this.reasonThree('0');
              this.classTwo('0');
            }
          }
        }).catch(res => {

        })
      },
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
      //投诉是否成立选择是，投诉不成立理由置灰，文本框内容清空
      checkComplaintTenable(){
        if(this.sendForm.complaintTenable == "01"){
          this.sendForm.faseReason = "";
        }
      },
      //反显信息需求
      searchHandle() {
        let workOrderNo=this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
              const workPoolData=res.data
            let editInfo = {
              editReason: "",
              editRemark: ""
            }
              workPoolData.editInfo=editInfo
              this.workPoolData=workPoolData
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        // FlowLogSearch(workOrderNo).then(res => {
        //   if (res != null && res.code === 200) {
        //     this.flowLogData = res.rows
        //     this.flowLogCount = res.total
        //     if (res.rows.length <= 0) {
        //       return this.$message.warning(
        //         "未查询到数据！"
        //       )
        //     }
        //   }
        // }).catch(res => {
        //
        // })
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
      //上传附件
      upload(){
        this.$refs.upload.open();
      },

      //选中二级投诉分类后，控制是否计件、撤诉状态的显示与隐藏
      checkLevel2(){
        if(this.sendForm.level2 != "05"){
          this.sendForm.pieceworkFlag = "";
          this.sendForm.complaintStatus = "";
        }
      },

    }
  }
</script>

<style scoped>
  .item-width {
    width: 190px;
  }
  .item-width1 {
    width: 50px;
  }
  /deep/ .el-radio{
    /*display: block;*/
    line-height: 30px;
    /*white-space: normal;*/
    /*margin-right: 0;*/
  }
</style>
