<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="150px"
               label-position="right" size="mini"disabled>
        <el-row>
          <!--clearable是清楚输入框内容 readly、只读不可以编辑 ；不可以共存-->
          <el-col :span="8">
            <el-form-item label="保单号：" prop="Service"  >
              <el-input readonly v-model="sendForm.acceptor" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel" readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
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
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
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
      <el-form ref="ruleForm" :model="ruleForm" style="padding-bottom: 30px;" label-width="170px" :disabled="isDisabled"
               label-position="right" size="mini">

        <span style="color: blue">{{selectDictLabel(cs_service_item, ruleForm.itemCode)}}-服务受理信息</span>
        <el-divider/>

        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channelCode">
              <el-select v-model="ruleForm.channelCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" >
              <el-input v-model="ruleForm.callPerson.name" class="item-width" size="mini" placeholder="请输入"/>
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
            <el-form-item label="来电人与被保人关系：" >
              <el-select v-model="ruleForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="ruleForm.callCenterId" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：">
              <el-input v-model="ruleForm.callPerson.mobilePhone" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" >
              <el-input v-model="ruleForm.contactsPerson.name" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="传真：" >
              <el-input v-model="ruleForm.contactsPerson.fax" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：">
              <el-select v-model="ruleForm.contactsPerson.sex" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>

          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人家庭电话：">
              <el-input v-model="ruleForm.contactsPerson.homePhone" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人办公电话：">
              <el-input v-model="ruleForm.contactsPerson.workPhone" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人手机：" >
              <el-input v-model="ruleForm.contactsPerson.mobilephone" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：">
              <el-select v-model="ruleForm.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系邮箱：" >
              <el-input v-model="ruleForm.email" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" >
              <el-select v-model="ruleForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="就诊日期：" prop="appointmentDate">
              <el-input v-model="ruleForm.appointmentDate" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊时间：" prop="appointmentDate">
              <el-time-picker class="item-width"
                              is-range
                              v-model="ruleForm.complaintTimes"
                              range-separator="-"
                              start-placeholder="开始时间"
                              end-placeholder="结束时间"
                              value-format="HH:mm:ss">
              </el-time-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构：" prop="medicalInstitution">
              <el-input v-model="ruleForm.medicalInstitution" input-w  class="item-width2" size="mini"
                        placeholder="请输入"/>
              <el-button type="primary" @click="searchHandle" size="mini">详细信息</el-button>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="hospitalWorkCall">
              <el-input v-model="ruleForm.hospitalWorkCall" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊科室：" >
              <el-input v-model="ruleForm.department" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" >
              <el-input v-model="ruleForm.compensationRatio" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" >
              <el-select v-model="ruleForm.validCertificate" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否持保险卡：" >
              <el-select v-model="ruleForm.settlementCard" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" >
              <el-input v-model="ruleForm.symptomTimes" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="email">
              <el-input v-model="ruleForm.symptomTimes" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病名称：">
              <el-input v-model="ruleForm.disease" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病或体征：" >
              <el-input v-model="ruleForm.symptomsSigns" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否意外：" prop="accidentFlag">
              <el-select v-model="ruleForm.accidentFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="意外原因：" >
              <el-input v-model="ruleForm.accidentReason" class="item-width" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否申请直结：" >
              <el-select v-model="ruleForm.settlementFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="就诊类型：" >
              <el-select v-model="ruleForm.visitType" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_consultation_type" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-form-item label="业务内容：">
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

<!--    <el-card>-->
<!--      <el-form ref="ruleForm" :model="ruleForm"  style="padding-bottom: 30px;" label-width="100px"-->
<!--               label-position="right" size="mini">-->
<!--        <span style="color: blue">服务处理</span>-->
<!--        <el-divider/>-->
<!--        <el-row>-->
<!--          <el-form-item label="处理时常：" prop="beInsuredName">-->
<!--            <el-input v-model="sendForm.beInsuredName" class="width-full" readonly size="mini" />-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="业务处理情况" prop="bank" >-->
<!--             <el-input v-model="ruleForm.phone" class="width-full"  size="mini" readonly></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="处理说明：" >-->
<!--            <el-input-->
<!--              type="textarea"-->
<!--              :rows="2"-->
<!--              v-model="ruleForm.textarea" readonly>-->
<!--            </el-input>-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--      </el-form>-->


 <!--   </el-card>
-->
    <el-card>
      <el-form ref="submitForm" :model="submitForm"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue" >取消处理</span>
        <el-divider style="color: blue" ></el-divider>
        <el-row>
          <el-form-item label="取消原因：" prop="bank" >
            <el-radio-group v-model="submitForm.editReason">
              <el-radio   label="1">客户申请变动</el-radio>
              <el-radio   label="2">操作失误</el-radio>
              <el-radio   label="3">其他原因</el-radio>
            </el-radio-group>
          </el-form-item>

        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              v-model="submitForm.editRemark"
            >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <el-row :gutter="20" align="right">
        <div style="text-align: right; margin-right: 1px;">
        <el-button type="primary"  size="mini" @click="submit()">提交</el-button>
        <el-button type="primary"  size="mini" @click="hiddenShow()">关闭</el-button>
         </div>
      </el-row>


    </el-card>





  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch} from '@/api/customService/demand'
  import {
    demandListAndPublicPool,
    demandListAndPersonalPool,
    cancelReservationSubmit,
    getPersonalPool
  } from '@/api/customService/reservation'
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
    {dictType: 'cs_feedback_type'},
    {dictType: 'cs_end_case'},
    {dictType: 'cs_direct_settlement'},
    {dictType: 'cs_consultation_type'},
    {dictType: 'cs_time_unit'},
    {dictType: 'cs_feedback_type'},
    {dictType: 'cs_service_item'},
    {dictType: 'cs_order_state'},
    {dictType: 'cs_action_type'},
    {dictType: 'cs_link_code'},
  ]
  export default {
    components: {
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
        isDisabled: true,
        //流转用
        flowLogData:[],
        cs_link_code:[],
        flowLogCount: 0,
        //传过来的工单号
        workOrderNo:"",
        //需要填入数据的部分
        ruleForm:{
         callPerson:{
         },
          contactsPerson:{
          },

        },
        //
        submitForm:{
          workOrderNo:"",
          editReason:"",
          editRemark:""
        },
        // 表单校验
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          channle:"",
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
        workPoolData: [],
        isinit: 'Y',
        totalCount: 0,
        changeSerchData: {},
        states: [],
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
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      this.searchHandle()
      this.searchFlowLog()
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
      this.cs_link_code = this.dictList.find(item => {
        return item.dictType === 'cs_link_code'
      }).dictDate
    },
    methods: {
      //客户信息加载
      searchSendFormInfo() {
        let query = {
          policyNo: this.queryParams.policyNo,
          policyItemNo: this.queryParams.policyItemNo,
        }
        if(this.queryParams.policyNo != null && this.queryParams.policyNo !=""){
          policyInfoData(query).then(res => {
            if (res != null && res.code === 200) {
              if (res.data != null) {
                this.sendForm = res.data;
              }
            }
          }).catch(res => {

          })
        }
      },
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //上传附件
      upload(){},
      //下载
      download(){},
      //提交页面数据
      submit(){
        this.$refs.submitForm.validate((valid) => {
            let insert = this.submitForm
            insert.workOrderNo = this.$route.query.workOrderNo
            cancelReservationSubmit(insert).then(res => {
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

        })
      },
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      //反显信息需求
      //服务受理信息查询
      searchHandle() {
        let workOrderNo = this.queryParams
        console.log("workOrderNo", workOrderNo)
        getPersonalPool(workOrderNo).then(res => {

          if (res != null && res.code === 200) {
            this.ruleForm = res.data
            if (this.ruleForm.symptomTimes != null && this.ruleForm.symptomTimes != '') {
              let arr=this.ruleForm.symptomTimes.split('-');
              this.$set(this.ruleForm, `a`, arr[0]);
              this.$set(this.ruleForm, `b`, arr[1]);
            }
            if(this.ruleForm.complaintTime != null && this.ruleForm.complaintTime !=''){
              //预约时间反显
              let timeArr=this.ruleForm.complaintTime.split('-');
              this.$set(this.ruleForm, `complaintTimes`, timeArr);
            }

          }
        }).catch(res => {

        })

  /*      if (this.queryParams.status=="01") {
          const query = this.queryParams
          demandListAndPublicPool(query).then(res => {
            if (res!=null && res.code === 200) {
              this.ruleForm =  res.rows[0];
              this.totalCount = res.total;
              if(this.ruleForm.complaintTime != null && this.ruleForm.complaintTime !=''){
                //预约时间反显
                let timeArr=this.ruleForm.complaintTime.split('-');
                this.$set(this.ruleForm, `complaintTimes`, timeArr);
              }
              console.log('公共', this.ruleForm)
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })
        }else {
          let query = this.queryParams
          demandListAndPersonalPool(query).then(res => {
            if (res!= null && res.code === 200) {
               this.ruleForm = res.rows[0];
              if(this.ruleForm.complaintTime != null && this.ruleForm.complaintTime !=''){
                //预约时间反显
                let timeArr=this.ruleForm.complaintTime.split('-');
                this.$set(this.ruleForm, `complaintTimes`, timeArr);
              }
              console.log(this.workPoolData)
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })

        }*/
      },
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            this.flowLogCount=res.total
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
  .item-width {
    width: 160px;
  }
  .item-width2 {
    width: 100px;
  }
</style>
