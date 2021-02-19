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
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">

        <span style="color: blue">信息需求-理赔类</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="phoneNumber">
              <el-input v-model="workPoolData.length" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callMobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-input v-model="workPoolData.channelCode" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="workPoolData.priorityLevel" class="item-width" disabled >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="phone">
              <el-input v-model="workPoolData.callName" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人与被保人关系：" prop="priority" >
              <el-select v-model="workPoolData.callRelationBy" class="item-width" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人：" prop="lxperson">
              <el-input v-model="workPoolData.contactsRelationBy" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="priority" >
              <el-select v-model="workPoolData.contactsSex" class="item-width" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人与被保人关系：" prop="priority" >
              <el-select v-model="workPoolData.contactsRelationBy" class="item-width" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="phone">
              <el-input v-model="workPoolData.ContactsMobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row >
          <el-col :span="5">
            <el-form-item label="联系人固定电话："  style="white-space: nowrap" prop="phone">
              <el-input v-model="workPoolData.contactsCountry" class="item-width" readonly style="width: 75px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="所在地："  prop="phone">
            <el-input v-model="workPoolData.phone" class="width-full"  size="mini" readonly/>
          </el-form-item>

        </el-row>
        <el-row>

          <el-col :span="8">
            <el-form-item label="出单机构：" prop="priority">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="请选择" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否涉及银行转账" prop="bank" >
              <el-radio-group v-model="workPoolData.bankTransfer" disabled>
                <el-radio   :label="1">是</el-radio>
                <el-radio   :label="2">否</el-radio>

              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="开户行：" v-show="ruleForm.bank=='1'" prop="bankaa">
              <el-input size="mini" v-model="workPoolData.bankName" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开户地：" v-show="ruleForm.bank=='1'" prop="bankbb">
              <el-input size="mini" v-model="workPoolData.bankLocation" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="账号：" v-show="ruleForm.bank=='1'" prop="bankcc">
              <el-input size="mini" v-model="workPoolData.accountNumber" readonly></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="户名：" v-show="ruleForm.bank=='1'" prop="bankdd">
              <el-input size="mini" v-model="workPoolData.bankHolder" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="业务内容：" prop="textarea">
          <el-input
            type="textarea"
            :rows="2"
            readonly
            v-model="workPoolData.textarea">
          </el-input>
        </el-form-item>
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
              <span>{{ scope.row.makeTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope" class="link-type">
              <span  @click="modifyDetails" a style="color: #3CB4E5;text-decoration: underline" href=" " >{{scope.row.umNum}}</span>
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
        <el-form-item label="处理说明：" prop="remark">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="ruleForm.remark">
          </el-input>
        </el-form-item>
        </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button  type="primary" style="float: right" size="mini" @click="upload">上传附件</el-button></span>
       <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="HCSPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="policyNumber" label="附件名称" show-overflow-tooltip/>
          <el-table-column align="center"  prop="secondNumber" label="附件类型" show-overflow-tooltip/>
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
        <el-button  type="primary" size="mini" @click="temporary">暂存</el-button>
        <el-button  type="primary"  size="mini" @click="transfer">转办</el-button>
        <el-button  type="primary" size="mini" @click="urge">催办</el-button>
        <el-button  type="primary"  size="mini" @click="cancle">撤销</el-button>
      </div>
    </el-card>



  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,dealAdd,FlowLogSearch,HMSSearch,dealADD} from '@/api/customService/demand'
  import transfer from "../common/modul/transfer";
  import upLoad from "../common/modul/upload";
  import coOrganizer from "../common/modul/coOrganizer";
  import modifyDetails from "../common/modul/modifyDetails";

  let dictss = [{dictType: 'product_status'}]
  export default {
    components: { transfer ,
                  upLoad,
                  coOrganizer,
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
        ids:[],//多选框
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
            {required: true, message: "处理说明不能为空", trigger: "blur"}
          ],

        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        //新增的数据传输
        sendForm: {
          sign:"",//控制暂存还是提交用
          workOrderNo:'',
          businessProcess:'',
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
      //window.aaa = this;
      this.searchHandle()
      this.searchFlowLog()
      // this.getDicts("sys_oper_type").then(response => {
      //   this.states = response.data;
      //   console.log("response:",response)
      // });

    },

    methods: {
      //新增按钮
      add(){
        let addQueryParams=this.ruleForm
        addQueryParams.workOrderNo=this.workOrderNo
        console.log("sdas",this.workOrderNo)
        dealAdd(addQueryParams).then(res => {
          console.log("增加",addQueryParams)
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
      deal(){},
      //反显信息需求
      searchHandle() {
        let query=this.queryParams
        console.log("query",query)
        demandListAndPersonalPool(query).then(res => {
          console.log('共公池',res.rows)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows[0]
            this.totalCount = res.total
            console.log('response',res.total)
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
      transfer(){
        this.$refs.transfer.transferForm.workOrderNo=this.queryParams.workOrderNo
        this.$refs.transfer.open()
        },
      //协办
      coOrganizer(){ this.$refs.coOrganizer.open();},
      //超链接用
      modifyDetails(){
        this.$refs.modifyDetails.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
      ;},

      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      //暂存
      temporary(){
        let insert=this.ruleForm
        insert.sign="01"
        insert.workOrderNo=this.$route.query.workOrderNo
        dealADD(insert).then(res => {
          if (res != null && res.code === 200) {
            console.log("insert",insert)
            alert("修改成功")
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
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          console.log(workOrderNo)
          console.log('轨迹表',res.rows)
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

      //取消按钮
      cancle() {
        this.$router.push({
          path: '/customService/cancle',
          query:{
            workOrderNo:this.queryParams.workOrderNo,
            policyNo:this.queryParams.policyNo,
            policyItemNo:this.queryParams.policyItemNo,
            status:this.queryParams.status
          }
        })
      },
     // 催办
      urge(){},
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.workOrderNo);

      },

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
