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
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="180px"
               label-position="right" size="mini">

        <span style="color: blue">口腔责任直接结算-服务受理信息</span>
        <el-divider/>

        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="callCenterNo">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterNo">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
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
            <el-form-item label="来电人姓名：" prop="phone">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="ruleForm.callMobilePhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="传真：" prop="phone">
              <el-input v-model="ruleForm.callMobilePhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人与申请人关系：" prop="priority">
              <el-select v-model="ruleForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="ContactsLanguage">
              <el-select v-model="ruleForm.contactsLanguage" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人家庭电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人办公电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" readonly size="mini" placeholder="请输入"/>
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
            <el-form-item label="预约日期：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约时间：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构：" prop="email">
              <el-col :span="15">
                <el-input v-model="sendForm.beInsuredName" input-w readonly size="mini" placeholder="请输入"/>
              </el-col>
              <el-button type="primary" @click="searchHandle">详细信息</el-button>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否持保险卡：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病或体征：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否首次就诊：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly  size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否意外：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="意外原因：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="beInsuredName">
            <el-input v-model="sendForm.beInsuredName" class="width-full" readonly size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-row>
        <div style="text-align: right; margin-right: 8px;">
          <el-button type="primary" size="mini" @click="submit">提交</el-button>
          <el-button type="primary" size="mini" @click="hiddenShow">关闭</el-button>
        </div>

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
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip/>
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
              placeholder="请输入内容"
              v-model="ruleForm.textarea"
              disabled
            >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>

    <el-card>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue">修改原因</span>
        <el-divider></el-divider>
        <el-row>
            <el-form-item label="修改原因" prop="bank" >
              <el-radio-group v-model="ruleForm.bank">
                <el-radio   :label="1">客户申请变动</el-radio>
                <el-radio   :label="2">操作失误</el-radio>
                <el-radio   :label="3">其他原因</el-radio>
              </el-radio-group>
            </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="ruleForm.textarea">
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
  import {demandListAndPublicPool,demandListAndPersonalPool,FlowLogSearch} from '@/api/customService/demand'

  let dictss = [{dictType: 'product_status'}]
  export default {
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {

      return {
        cs_channel:[],
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        //服务项目
        workPoolData:"",
        totalCount: 0,
        //需要填入数据的部分
        ruleForm:{
          radio:"",
          service:"",
          phone:"",
          priority:"",
          lxperson:"",
          bankaa: "",
          bankbb: "",
          bankcc: "",
          bankdd: "",
          bank:"2",
          textarea:"",
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
          orderNum: [
            {required: true, message: "联系人移动电话不能为空", trigger: "blur"}
          ],
          orderNum: [
            {required: true, message: "出单机构不能为空", trigger: "blur"}
          ],
          bankaa: [
            {required: true, message: "开户行不能为空", trigger: "blur"}
          ],
          bankbb: [
            {required: true, message: "开户地不能为空", trigger: "blur"}
          ],
          bankcc: [
            {required: true, message: "账号不能为空", trigger: "blur"}
          ],
          bankdd: [
            {required: true, message: "户名不能为空", trigger: "blur"}
          ],
          textarea: [
            {required: true, message: "业务内容不能为空", trigger: "blur"}
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
      this.getDicts("cs_channel").then(response => {
        this.cs_channel = response.data;
      });

    },

    methods: {
      //提交页面数据
      submit(){},
      //关闭页面
      close(){

      },
      //反显信息需求
      searchHandle() {
        if (this.queryParams.status=="01") {
          console.log("status:",this.queryParams.status="01")
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
