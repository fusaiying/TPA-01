<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="130px"
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
      <el-form ref="ruleForm" :model="ruleForm"  style="padding-bottom: 30px;" label-width="165px"
               label-position="right" size="mini">

        <span style="color: blue">信息需求-理赔类-质疑理赔结果</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="phoneNumber">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilephone" class="item-width"  size="mini" readonly/>
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
              <el-input v-model="workPoolData.callPerson.name" class="item-width" readonly size="mini"/>
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
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="priority" >
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width" disabled>
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
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" readonly/>
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
              国家区号:+<el-input v-model="workPoolData.contactsPerson.homePhone1[0]" class="item-width" readonly style="width: 75px"/>
              区号<el-input v-model="workPoolData.contactsPerson.homePhone1[1]" class="item-width" readonly size="mini" style="width: 145px" maxlength="50"/>
              号码<el-input v-model="workPoolData.contactsPerson.homePhone1[2]" class="item-width" readonly size="mini" style="width: 145px" maxlength="50"/>
              分机号<el-input v-model="workPoolData.contactsPerson.homePhone1[3]" class="item-width" readonly size="mini" style="width: 145px" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="所在地："  prop="phone">
            <el-input v-model="workPoolData.contactsPerson.address" class="width-full"  size="mini" readonly/>
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
            <el-form-item label="开户行：" v-show="workPoolData.bankTransfer=='1'" >
              <el-input size="mini" v-model="workPoolData.bankName" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开户地：" v-show="workPoolData.bankTransfer=='1'" >
              <el-input size="mini" v-model="workPoolData.bankLocation" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="账号：" v-show="workPoolData.bankTransfer=='1'" >
              <el-input size="mini" v-model="workPoolData.accountNumber" readonly></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="户名：" v-show="workPoolData.bankTransfer=='1'" >
              <el-input size="mini" v-model="workPoolData.bankHolder" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="业务内容：" prop="textarea">
          <el-input
            type="textarea"
            :rows="2"
            readonly
            v-model="workPoolData.content">
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
      <el-form ref="ruleForm" :model="ruleForm"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini" v-show="this.queryParams.status>2">
        <span style="color: blue">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况" prop="bank" >
             <el-input v-model="workPoolData.phone" class="width-full"  size="mini" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              v-model="workPoolData.textarea" readonly>
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>


    </el-card>

    <el-card>
      <el-form  :model="submitForm"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue" >取消处理</span>
        <el-divider style="color: blue" ></el-divider>
        <el-row>
          <el-form-item label="取消原因"  >
            <el-radio-group v-model="submitForm.cancelReason">
              <el-radio   label="1">客户申请变动</el-radio>
              <el-radio   label="2">操作失误</el-radio>
              <el-radio   label="3">其他原因</el-radio>
            </el-radio-group>
          </el-form-item>

        </el-row>
        <el-row>
          <el-form-item label="处理说明：">
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
  import {demandListAndPublicPool,demandListAndPersonalPool,FlowLogSearch,cancelSubmit} from '@/api/customService/demand'

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
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        //传过来的工单号
        workOrderNo:"",
        //需要填入数据的部分
        ruleForm:{
          cancelReason:"",
          editRemark:"",
          workOrderNo:"",

        },
        submitForm:{
          cancelReason:"",
          editRemark:"",
          workOrderNo:"",
        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          channle:"",
          textarea:"",
          service: "",
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
        workPoolData: {},
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
      this.searchHandle()
      this.searchFlowLog()
      this.getDicts("sys_oper_type").then(response => {
        this.states = response.data;
        console.log("response:",response)
      });

    },

    methods: {
      //上传附件
      upload(){},
      //下载
      download(){},
      //提交页面数据
      submit(){
        let insert=this.submitForm
        insert.workOrderNo=this.$route.query.workOrderNo
        cancelSubmit(insert).then(res => {
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
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
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
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
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
