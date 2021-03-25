  <template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
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
            <el-form-item label="保溢生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢满期日:"  prop="Acceptor">
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
              <el-select v-model="sendForm.acceptor" class="item-width" readonly size="mini" >
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构:"  prop="organCode">
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
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="150px"
               :disabled="isDisabled"
               label-position="right" size="mini">

        <span style="color: blue">口腔责任直接结算-服务受理信息</span>
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
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="ruleForm.callCenterId" class="item-width" readonly size="mini" placeholder="请输入"/>
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
            <el-form-item label="来电人姓名：" prop="callName">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="ruleForm.callPerson.mobilePhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <!--<el-col :span="8">
            <el-form-item label="传真：" prop="fax">
              <el-input v-model="ruleForm.fax" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>-->
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人与申请人关系：" prop="callRelationBy">
              <el-select v-model="ruleForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input v-model="ruleForm.contactsPerson.name" class="item-width" readonly size="mini" placeholder="请输入"/>
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

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="ruleForm.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人电话：" prop="contactsPerson.mobilePhone">
              <el-input v-model="sendForm.contactsPerson.mobilePhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <!--<el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人家庭电话：" prop="contactsPerson.homePhone">
              <el-input v-model="ruleForm.contactsPerson.homePhone" class="item-width" size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>-->
        </el-row>

        <el-row>
          <!--<el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人办公电话：" prop="contactsPerson.workPhone">
              <el-input v-model="ruleForm.contactsPerson.workPhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>-->
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" size="mini" placeholder="请输入"/>
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
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="complaintTime">
              <el-input v-model="ruleForm.complaintTime" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <!--<el-col :span="8">
            <el-form-item label="预约时间：" prop="complaintTime">
              <el-input v-model="ruleForm.complaintTime" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>-->
          <el-col :span="8">
            <el-form-item label="医疗机构：" prop="email">
              <el-col :span="14">
                <el-input v-model="ruleForm.beInsuredName" input-w readonly size="mini" placeholder="请输入"/>
              </el-col>
              <el-button type="primary" @click="searchHandle" disabled>详细信息</el-button>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="hospitalWorkCall">
              <el-input v-model="ruleForm.hospitalWorkCall" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="department">
              <el-input v-model="ruleForm.department" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" prop="compensationRatio">
              <el-input v-model="ruleForm.compensationRatio" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="validCertificate">
              <el-select v-model="ruleForm.validCertificate" class="item-width" readonly size="mini" placeholder="请输入">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="*是否持保险卡：" prop="settlementCard">
              <el-select v-model="ruleForm.settlementCard" class="item-width" readonly size="mini" placeholder="请输入">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="symptomTimes">
              <el-input v-model="ruleForm.a" style="width: 90px" clearable size="mini" placeholder="请输入"maxlength="4"/>
              <el-select v-model="ruleForm.b" style="width: 90px" placeholder="请选择"  >
                <el-option v-for="item in cs_time_unit" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <!--<el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="earliestTime">
              <el-input v-model="ruleForm.earliestTime" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>-->
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="disease">
              <el-input v-model="ruleForm.disease" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病或体征：" prop="symptomsSigns">
              <el-input v-model="ruleForm.symptomsSigns" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否首次就诊：" prop="firstFlag">
              <el-select v-model="ruleForm.firstFlag" class="item-width" readonly  size="mini" placeholder="请输入">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否意外：" prop="accidentFlag">
              <el-select v-model="ruleForm.accidentFlag" class="item-width" readonly size="mini" placeholder="请输入">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="意外原因：" prop="accidentReason">
              <el-input v-model="ruleForm.accidentReason" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="content">
            <el-input v-model="ruleForm.content" class="width-full" readonly size="mini" placeholder="请输入"/>
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
              <span>{{ scope.row.createdTime | changeDate}}</span>
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



    <el-card>
      <el-form ref="submitForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="130px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
        <el-divider/>
        <el-row>
          <el-form-item label="处理时长：" prop="times">
            <el-input v-model="submitForm.times" class="width-full"  size="mini" placeholder="请输入" readonly/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况：" prop="businessProcess" >
              <el-radio-group v-model="submitForm.businessProcess" disabled>
                <el-radio   label="01">成功</el-radio>
                <el-radio   label="02">响应</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="remark">
            <el-input
              type="textarea"
              :rows="2"
              v-model="submitForm.remark" disabled>
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch,HMSSearch} from '@/api/customService/demand'
  import {demandListAndPublicPool,demandListAndPersonalPool,dealReservationSubmit} from '@/api/customService/reservation'

  import transfer from "../common/modul/transfer";
  import upLoad from "../common/modul/upload";
  import coOrganizer from "../common/modul/coOrganizer";
  import modifyDetails from "../common/modul/modifyDetails";

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
    components: { transfer ,
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
        isDisabled: true,
        cs_action_type:[],
        cs_communication_language:[],
        cs_sex:[],
        cs_relation: [],
        cs_priority:[],
        cs_channel:[],
        cs_organization:[],
        cs_order_state:[],
        cs_whether_flag: [],
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
            callPerson : {
                mobilePhone:'',
            },
            contactsPerson :{
                name:'',
            }
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
            {required: true, message: "联系人与被保人关系不能为空", trigger: "blur"}
          ],
          // orderNum: [
          //   {required: true, message: "联系人移动电话不能为空", trigger: "blur"}
          // ],

        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        //新增的数据传输
        // sendForm: {
        //   workOrderNo:'',
        //   businessProcess:'',
        // },
        sendForm: {
          email: "",
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
        submitForm:{
          sign:"",
          workOrderNo:"",
          customerFeedback:"",
          times:"",
          businessProcess:"",
          remark:"",
          costIncurred:"",

        },
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
        workPoolData:"",
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
          value: '01',
          label: '服务1'
        }, {
          value: '02',
          label: '服务2'
        }, {
          value: '03',
          label: '服务3'
        }, {
          value: '04',
          label: '服务4'
        }],
        sysUserOptions: [],
      }
    },
    created() {
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
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
      this.cs_action_type = this.dictList.find(item => {
        return item.dictType === 'cs_action_type'
      }).dictDate
      this.cs_communication_language = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.cs_sex = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.cs_relation = this.dictList.find(item => {
          return item.dictType === 'cs_relation'
      }).dictDate
      this.cs_whether_flag = this.dictList.find(item => {
          return item.dictType === 'cs_whether_flag'
      }).dictDate
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.cs_channel = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.cs_organization = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.cs_order_state = this.dictList.find(item => {
        return item.dictType === 'cs_order_state'
      }).dictDate
    },
    methods: {
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //新增按钮
      submit(){
        const send=this.submitForm
        send.sign="02",
          send.workOrderNo=this.queryParams.workOrderNo=this.$route.query.workOrderNo
        dealReservationSubmit(send).then(res => {

          if (res != null && res.code === 200) {
            this.$message.success("保存成功！")
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "提交失败！"
              )
            }
          }
        }).catch(res => {

        })

      },
      temporary(){
        const send=this.submitForm
        send.sign="01",
          send.workOrderNo=this.queryParams.workOrderNo=this.$route.query.workOrderNo
        dealReservationSubmit(send).then(res => {

          if (res != null && res.code === 200) {
            alert("暂存成功！")
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
        if (this.ids.length==0){
          alert("先选中一行")
        }else {if(this.ids.length>2){
          alert("选中一行")
        }else {
          this.$router.push({
            path: '/customService/reservation/modify',
            query: {
              workOrderNo: this.queryParams.workOrderNo,
              policyNo: this.queryParams.policyNo,
              policyItemNo: this.queryParams.policyItemNo,
              status: this.queryParams.status
            }
          })
        }
        }
      },
      //取消按钮
      cancle() {
        this.$router.push({
          path: '/customService/reservation/cancle',
          query:{
            workOrderNo:this.queryParams.workOrderNo,
            policyNo:this.queryParams.policyNo,
            policyItemNo:this.queryParams.policyItemNo,
            status:this.queryParams.status
          }
        })
      },
      //反显信息需求
      searchHandle() {
        let query=this.queryParams
       // console.log("query",query)
        demandListAndPersonalPool(query).then(res => {
         // console.log('共公池',res.rows)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows[0]
            this.totalCount = res.total
           // console.log('response',res.total)
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
      upload(){ this.$refs.upload.open();},
      //下载
      download(){},

      //转办
      transfer(){ this.$refs.transfer.open();},
      //协办
      coOrganizer(){ this.$refs.coOrganizer.open();},
      resetForm() {
        this.$refs.sendForm.resetFields()
      },

      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      //反显信息需求
      searchHandle() {
          let workOrderNo = this.queryParams
          console.log("workOrderNo", workOrderNo)
          demandListAndPersonalPool(workOrderNo).then(res => {
              console.log('共公池', res.rows)
              if (res != null && res.code === 200) {
                  this.sendForm = res.rows[0]
                  this.totalCount = res.total
                  console.log('response', res.total)
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
      searchHandle() {
          let workOrderNo = this.queryParams
          console.log("workOrderNo", workOrderNo)
          demandListAndPublicPool(workOrderNo).then(res => {
              console.log('个人池', res.rows)
              if (res != null && res.code === 200) {
                  this.ruleForm = res.rows[0]
                  // console.log("res.rows[0]" , res.rows[0])
                  // this.ruleForm.callRelationBy = res.rows[0].callRelationBy.toString();


                  this.totalCount = res.total
                  console.log('response', res.total)
                  if (res.rows.length <= 0) {
                      return this.$message.warning(
                          "未查询到数据！"
                      )
                  }
              }
          }).catch(res => {

          })
      },
      //反显暂存信息
      searchHandleServer() {
          let workOrderNo = this.queryParams
          workOrderNo.businessType = "02"
          complainSearchServer(workOrderNo).then(res => {
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
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          // console.log(workOrderNo)
          // console.log('轨迹表',res.rows)
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            this.flowLogCount=res.total
            // console.log("searchFlowLog",this.flowLogData)
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
          // console.log(workOrderNo)
          // console.log('HCS',res.rows)
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

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
