<template>
  <div class="app-container">
    <!--客户基本信息 -->
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="baseInfo" :model="baseInfo" label-width="180px"
               label-position="right" size="mini" >
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

    <!--基因改善-服务受理信息 -->
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="ruleForm" :model="ruleForm"  style="padding-bottom: 30px;" label-width="180px"
               label-position="right" size="mini" :disabled="flag">
        <span style="color: blue">基因改善-服务受理信息</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-select v-model="workPoolData.channelCode" class="item-width" disabled>
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="callCenterId">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="sex" >
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width" disabled>
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人地址：" prop="address">
              <el-input v-model="workPoolData.contactsPerson.address" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="insuredNo">
              <el-input v-model="workPoolData.insuredNo" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width" disabled>
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="priority"  >
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" disabled>
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人姓名：" prop="phone">
              <el-input v-model="workPoolData.complaintPerson.name" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉人性别：" prop="phone">
              <el-select v-model="workPoolData.complaintPerson.sex" class="item-width" disabled>
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：" prop="phone">
              <el-select v-model="workPoolData.complaintPerson.identity" class="item-width" disabled>
                <el-option v-for="item in cs_identity" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="客户电话：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.homePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="办公电话：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.workPhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width" disabled>
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>  <el-form-item label="是否已劝解：" prop="persuasionFlag">
          <el-select v-model="workPoolData.persuasionFlag" class="item-width" disabled>
            <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                       :value="item.dictValue"/>
          </el-select>
        </el-form-item>
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

        <el-row>
          <el-form-item label="投诉内容：" prop="phone">
            <el-input
              disabled
              type="textarea"
              :rows="3"
              v-model="workPoolData.content">
            </el-input>
          </el-form-item>
        </el-row>



      </el-form>
    </el-card>
<!--业务流转情况 -->
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
              <span>{{ scope.row.makeTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='03'" style="font-size:12px" type="primary" @click="modifyDetails(scope.row)">修改说明</el-link>
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
    <!--服务处理 -->
    <el-card v-if="showFlag" class="box-card" style="margin-top: 10px;">
      <el-form  ref="sendForm" :model="sendForm"  style="padding-bottom: 30px;" label-width="180px"
               label-position="right" size="mini" disabled>
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
          <el-form-item label="根因改善：" prop="rootImprovement">
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
    <!--附件信息 -->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button  type="primary" style="float: right" size="mini" @click="upload" disabled>上传附件</el-button></span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="attachmentInfoData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="attachmentName" label="附件名称" show-overflow-tooltip/>
          <el-table-column prop="attachmentType" align="center" label="附件类型" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.attachmentType">
              <span>{{selectDictLabel(cs_attachment_type, scope.row.attachmentType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="uplodBy" align="center" label="上传人" show-overflow-tooltip/>
          <el-table-column prop="uploadTime" align="center" label="上传时间 " show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.uploadTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" align="center" label="备注" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: right; margin-right: 1px;">
        <modify-details ref="modifyDetails"></modify-details>
      </div>
    </el-card>
  </div>
</template>

<script>
  import moment from 'moment'
  import { complaintDealSubmit, selectHangFlag, reasonThree, reasonTwo, classTwo, selectServiceProcess ,collaborative,transferInfo } from '@/api/customService/complaint'
  import {demandListAndPublicPool,demandListAndPersonalPool,dealAdd,FlowLogSearch,HMSSearch,dealADD} from '@/api/customService/demand'
  import upLoad from "../common/modul/upload";
  import {complainSearch,comSearch}  from  '@/api/customService/consultation'
  import {getAttachmentListById} from "@/api/customService/spotCheck";
  import modifyDetails from "../common/modul/modifyDetails";
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
    {dictType: 'cs_order_state'},
    {dictType: 'cs_action_type'},
    {dictType: 'cs_feedback_type'},

    {dictType: 'cs_end_case'},
    {dictType: 'cs_link_circ'},
    {dictType: 'cs_classify_level1'},
    {dictType: 'cs_drop_status'},
    {dictType: 'cs_reason_level1'},
    {dictType: 'cs_service_item'},
    {dictType: 'cs_mediation_appraisal'},
    {dictType: 'cs_risk_type'},
    {dictType: 'cs_question_circ'},
    {dictType: 'cs_marketingchannel_code'},
    {dictType: 'card_type'},
    {dictType: 'rgtSex'},
    {dictType: 'cs_link_code'}



    ]
  export default {
    components: {
      upLoad,
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
        card_type: [],//证件类型
        ids:[],//多选框
        cs_link_code:[],
        flag:false,
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        // //传过来的工单号
        // workOrderNo:"",
        // policyNo:"",
        // policyItemNo:"",
        // status:"",
        //信息需求反显数组
        //需要填入数据的部分
        ruleForm:{
          workOrderNo:"",
          businessProcess:"",
          remark:"",
          customerFeedback:"",
          closeType:"",
          costsIncurred:"",
          sign:""
        },
        // 表单校验
        rules: {
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
            {required: true, message: "联系人移动电话不能为空", trigger: "blur"}
          ],

        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        //新增的数据传输
        //客户基本信息数据
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
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          workOrderNo:"",
          policyNo:"",
          policyItemNo:"",
          status:"",
          pageNum: 1,
          pageSize: 10
        },
        loading: true,
        //数据反显用
        workPoolData: {
          contactsPerson:{
            homePhone1:[]
          },
          callPerson: {},
          complaintPerson:{},
          insurer:{},
        }

        ,
        //HCS服务预约预修改记录
        HCSPoolData:[],
        HCSTotal:0,
        //业务处理信息
        dealInfo:[],

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
        dictList: [],
        cs_channel: [],
        cs_priority: [],
        cs_sex: [],
        cs_communication_language: [],
        cs_identity: [],
        cs_whether_flag: [],
        cs_organization: [],
        cs_relation: [],
        cs_order_state: [],
        cs_action_type: [],
        attachmentInfoData: [],
        cs_reason_level1: [],
        cs_reason_level2: [],
        cs_reason_level3: [],
        cs_end_case: [],
        cs_link_circ: [],
        cs_classify_level1: [],
        cs_classify_level2: [],
        cs_drop_status: [],
        cs_service_item: [],//服务项目
        cs_mediation_appraisal: [],
        cs_risk_type: [],
        cs_marketingchannel_codeOption: [],
        cs_question_circ: [],
        cs_feedback_type: [],
        rgtSex:[]//性别
      }
    },
    created() {
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      if (this.$route.query.flag!=null && this.$route.query.flag!=''){
        this.flag = true;
      }
      if(this.queryParams.status=='04'){
        this.showFlag=true
      }
      else {
        this.showFlag=false
      }

      //window.aaa = this;
      this.searchHandle()
      this.searchFlowLog()
      this.searchHCS()
      this.searchBaseInfo()
      let query = {
        workOrderNo: this.queryParams.workOrderNo,
        businessType: this.queryParams.businessType,
      }
      //获取附件列表信息
      getAttachmentListById(query).then(res => {
        if (res != null && res.code === 200) {
          console.info(res.data);
          this.attachmentInfoData=res.data;
        }
      }).catch(res => {
      })
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
      this.cs_feedback_type = this.dictList.find(item => {
        return item.dictType === 'cs_feedback_type'
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
      this.cs_order_state = this.dictList.find(item => {
        return item.dictType === 'cs_order_state'
      }).dictDate
      this.cs_question_circ = this.dictList.find(item => {
        return item.dictType === 'cs_question_circ'
      }).dictDate
      this.cs_risk_type = this.dictList.find(item => {
        return item.dictType === 'cs_risk_type'
      }).dictDate
      this.cs_mediation_appraisal = this.dictList.find(item => {
        return item.dictType === 'cs_mediation_appraisal'
      }).dictDate
      this.cs_service_item = this.dictList.find(item => {
        return item.dictType === 'cs_service_item'
      }).dictDate
      this.cs_classify_level1 = this.dictList.find(item => {
        return item.dictType === 'cs_classify_level1'
      }).dictDate
      this.cs_drop_status = this.dictList.find(item => {
        return item.dictType === 'cs_drop_status'
      }).dictDate
      this.cs_action_type = this.dictList.find(item => {
        return item.dictType === 'cs_action_type'
      }).dictDate
      this.cs_end_case = this.dictList.find(item => {
        return item.dictType === 'cs_end_case'
      }).dictDate
      this.cs_link_circ = this.dictList.find(item => {
        return item.dictType === 'cs_link_circ'
      }).dictDate
      this.card_type = this.dictList.find(item => {
        return item.dictType === 'card_type'
      }).dictDate

      this.rgtSex = this.dictList.find(item => {
        return item.dictType === 'rgtSex'
      }).dictDate
      this.cs_marketingchannel_codeOption = this.dictList.find(item => {
        return item.dictType === 'cs_marketingchannel_code'
      }).dictDate
      this.cs_link_code = this.dictList.find(item => {
        return item.dictType === 'cs_link_code'
      }).dictDate
    },
    methods: {
      //客户基本信息查询
      searchBaseInfo() {
        let query = {
          policyNo: this.queryParams.policyNo,
          policyItemNo: this.queryParams.policyItemNo,
        }
        if(this.queryParams.policyNo != null && this.queryParams.policyNo !=""){
          policyInfoData(query).then(res => {
            if (res != null && res.code === 200) {
              if (res.data != null) {
                this.baseInfo = res.data;
              }
            }
          }).catch(res => {

          })
        }
      },
      //新增按钮
      //取消
      deal(){},
      //服务处理查询
      searchHandleServer() {
        selectServiceProcess(this.queryParams.workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            console.log("投诉页面server反显数据",res.data)
            if(res.data!=null && res.data!='') {
              this.sendForm = res.data;
              this.reasonTwo('0');
              this.reasonThree('0');
              this.classTwo('0');
            }
/*            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }*/
          }
        }).catch(res => {

        })
      },
      //反显信息需求
      searchHandle() {
        let workOrderNo=this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
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
      //上传附件
      upload(){ this.$refs.upload.open()},
      //下载
      download(){},

      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId;
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open();
        ;},
      //打开修改对话框



      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            this.flowLogCount=res.total
            console.log("searchFlowLog",this.flowLogData)
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
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        HMSSearch(workOrderNo).then(res => {
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
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.workOrderNo);

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
      //选中二级投诉分类后，控制是否计件、撤诉状态的显示与隐藏
      checkLevel2(){
        if(this.sendForm.level2 != "05"){
          this.sendForm.pieceworkFlag = "";
          this.sendForm.complaintStatus = "";
        }
      },
      //投诉是否成立选择是，投诉不成立理由置灰，文本框内容清空
      checkComplaintTenable(){
        if(this.sendForm.complaintTenable == "01"){
          this.sendForm.faseReason = "";
        }
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
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
