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
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
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
      <el-form ref="workPoolData" :model="workPoolData"  style="padding-bottom: 30px;" label-width="160px"
               label-position="right" size="mini">

        <span style="color: blue">口腔责任直接结算-服务受理信息</span>
        <el-divider/>

        <el-row>
          <el-form-item label="受理渠道：" prop="channelCode" >
            <el-radio-group v-model="workPoolData.channelCode">
              <el-radio   label="01">电话</el-radio>
              <el-radio   label="02">邮箱</el-radio>
              <el-radio   label="03">网站</el-radio>
              <el-radio   label="04">电话中心</el-radio>
              <el-radio   label="05">柜面</el-radio>
              <el-radio   label="06">医网</el-radio>
              <el-radio   label="07">平安内网</el-radio>
              <el-radio   label="08">APP</el-radio>
              <el-radio   label="09">寿险</el-radio>
              <el-radio   label="10">微信</el-radio>
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
            <el-form-item label="申请人姓名：" prop="complainantPerson.name">
              <el-input  v-model="workPoolData.complaintPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="callPerson.name">
              <el-input v-model="workPoolData.callPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="callPerson.mobilePhone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="与申请人关系：" prop="callRelationBy">
              <el-select v-model="workPoolData.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="传真：" prop="callPerson.fax">
              <el-input v-model="workPoolData.callPerson.fax" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊类型：" prop="visitType">
              <el-select v-model="workPoolData.visitType" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="hospitalWorkCall">
              <el-select v-model="workPoolData.hospitalWorkCall" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-input v-model="workPoolData.contactsPerson.sex" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机：" prop="contactsPerson.mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row >
          <el-col :span="16">
            <el-form-item label="家庭电话:"  style="white-space: nowrap" >
              国家区号:+<el-input v-model="workPoolData.contactsPerson.homePhone1[0]" class="item-width"  style="width: 75px"/>
              区号<el-input v-model="workPoolData.contactsPerson.homePhone1[1]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
              号码<el-input v-model="workPoolData.contactsPerson.homePhone1[2]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
              分机号<el-input v-model="workPoolData.contactsPerson.homePhone1[3]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <el-form-item label="办公室电话:"  style="white-space: nowrap">
              国家区号:+<el-input v-model="workPoolData.contactsPerson.workPhone1[0]" class="item-width"  style="width: 75px"/>
              区号<el-input v-model="workPoolData.contactsPerson.workPhone1[1]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
              号码<el-input v-model="workPoolData.contactsPerson.workPhone1[2]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
              分机号<el-input v-model="workPoolData.contactsPerson.workPhone1[3]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">

            <el-form-item label="预约时间："  style="white-space: nowrap" prop="complaintTime">
              <el-date-picker
                v-model="workPoolData.complaintTime"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
          </el-col>


        </el-row>


        <el-row>
          <el-col :span="8">
              <el-form-item label="就诊医院：" prop="medicalInstitution">
                省份：<el-input v-model="workPoolData.province" style="width: 60px"  />
                城市：<el-input v-model="workPoolData.city" style="width: 60px" />
              </el-form-item>

          </el-col>

          <el-col :span="8">
            <el-form-item label="医疗机构：" prop="medicalInstitution">
              <el-col :span="15">
                <el-input v-model="workPoolData.medicalInstitution"  readonly size="mini" />
              </el-col>
              <el-button type="primary" @click="searchHandle" disabled>详细信息</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="department">
              <el-input v-model="workPoolData.department" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>

        </el-row>



        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="validCertificate">
              <el-input v-model="workPoolData.validCertificate" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否持保险卡：" prop="settlementCard">
              <el-input v-model="workPoolData.settlementCard" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" prop="compensationRatio">
              <el-input v-model="workPoolData.compensationRatio" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="disease">
              <el-input v-model="workPoolData.disease" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="symptomTimes">
              <el-input v-model="workPoolData.symptomTimes" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="earliestTime">
              <el-input v-model="workPoolData.earliestTime" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col >
            <el-form-item label="疾病或体征：" prop="symptomsSigns">
              <el-input v-model="workPoolData.symptomsSigns" class="width-full"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

          <el-col :span="8">
            <el-form-item label="是否意外：" prop="accidentFlag">
              <el-input v-model="workPoolData.accidentFlag" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="意外原因：" prop="accidentReason">
              <el-input v-model="workPoolData.accidentReason" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="content">
            <el-input v-model="workPoolData.content" class="width-full"  size="mini" />
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
          <el-table-column align="center" width="140" prop="status" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" prop="operateCode" label="操作" show-overflow-tooltip/>
          <el-table-column align="center" prop="makeBy" label="受/处理人" show-overflow-tooltip/>
          <el-table-column align="center" prop="umNum" label="UM账号" show-overflow-tooltip/>
          <el-table-column prop="makeTime" label="时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='01'" style="font-size:12px" type="primary" @click="modifyDetails(scope.row)">修改说明</el-link>
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
      <el-form ref="ruleForm" :model="ruleForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini" v-if="this.queryParams.status=='05'">
        <span style="color: blue">服务处理</span>
        <el-divider></el-divider>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况" prop="bank"  >
              <el-radio-group v-model="ruleForm.bank" disabled>
                <el-radio   :label="1">成功</el-radio>
                <el-radio   :label="2">失败</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder=""
              v-model="ruleForm.textarea"
              disabled
            >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>

    <el-card>
      <el-form ref="ruleForm" :model="workPoolData"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue">修改原因</span>
        <el-divider></el-divider>
        <el-row>
            <el-form-item label="修改原因" prop="editReason" >
              <el-radio-group v-model="workPoolData.editReason">
                <el-radio   label="01">客户申请变动</el-radio>
                <el-radio   label="02">操作失误</el-radio>
                <el-radio   label="03">其他原因</el-radio>
              </el-radio-group>
            </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="editRemark">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="workPoolData.editRemark">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div style="text-align: right; margin-right: 1px;">
      <el-button  type="primary" size="mini" @click="submit">保存</el-button>
      <el-button  type="primary"size="mini" @click="hiddenShow">关闭</el-button>
      </div>
    </el-card>





  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch} from '@/api/customService/demand'
  import modifyDetails from "../common/modul/modifyDetails";
  import {demandListAndPublicPool,demandListAndPersonalPool,modifyReservationSubmit} from '@/api/customService/reservation'


  let dictss = [{dictType: 'product_status'}]
  export default {
    components: {
      modifyDetails,
    },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {

      return {
        cs_relation:[],//关系
        cs_service_item:[],//服务项目
        cs_sex:[],//性别
        cs_priority:[],//优先级
        cs_communication_language:[],//语言
        cs_organization:[],//机构
        cs_handle_state:[],// 状态：
        cs_channel:[],
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        //服务项目
        workPoolData: {
          complainantPerson:{
            name:"",
          },
          medicalInstitution:"",
          callRelationBy:"",
          workOrderNo:"",
          hospitalWorkCall:"",
          channelCode:"",
          callCenterId:"",
          priorityLevel:"",
          callPerson: {
            name:"",
            sex:"",
            mobilePhone:"",
            fax:"",
          },
          fax:"",
          visitType:"",
          settlementCard:"",
          compensationRatio:"",
          disease:"",
          symptomTimes:"",
          earliestTime:"",
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
          symptomsSigns:"",
          accidentFlag:"",
          accidentReason:"",
          content:"",
          editReason:"",
          editRemark:"",

          complaintPerson:{
            name:"",
          }
        },
        totalCount: 0,
        //需要填入数据的部分
        ruleForm:{

        },
        // 表单校验
        rules: {
          priority: [
            {required: true, message: "优先级不能为空", trigger: "blur"}
          ],
        },

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
        //提交数据
        sendForm:{

        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          workOrderNo:"",
          queryParams:"",
          policyItemNo:"",
          status:""

        },


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
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      this.searchHandle()
      this.searchFlowLog()
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
      this.getDicts("cs_priority").then(response => {
        this.cs_priority = response.data;
      });
      this.getDicts("cs_sex").then(response => {
        this.cs_sex = response.data;
      });
      this.getDicts("cs_communication_language").then(response => {
        this.cs_communication_language = response.data;
      });
      this.getDicts("cs_organization").then(response => {
        this.cs_organization = response.data;
      });
      this.getDicts("cs_relation").then(response => {
        this.cs_relation = response.data;
      });

    },

    methods: {
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //提交页面数据
      submit(){
        this.workPoolData.workOrderNo=this.$route.query.workOrderNo;
        let send=this.workPoolData
        modifyReservationSubmit(send).then(res => {
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
      //关闭页面
      close(){

      },
      //反显信息需求
      searchHandle() {
        if (this.queryParams.status=="01") {
          let query = this.queryParams
          demandListAndPublicPool(query).then(res => {
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows[0]
              this.totalCount = res.total
              console.log('公共', res.rows)
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
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows[0]
              this.totalCount = res.total
              console.log('个人', res.total)
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })

        }
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          console.log(workOrderNo)
          console.log('轨迹表',res.rows)
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
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
  .item-width {
    width: 220px;
  }
</style>
