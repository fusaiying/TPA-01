<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="workPoolData" :model="workPoolData" :rules="changeForm.rules" style="padding-bottom: 30px;" label-width="160px"
               label-position="right" size="mini">

        <span style="color: blue">服务项目-信息需求受理【修改】</span>
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
            <el-form-item label="联系人性别：" prop="contactsSex">
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
            <el-form-item label="来电人号码：" prop="callMobilePhone">
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
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsLanguage"  >
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" >
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="请选择" >
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row >
          <el-col :span="5">
            <el-form-item label="联系人固定电话："  style="white-space: nowrap" prop="phone">
              国家区号:+<el-input v-model="workPoolData.contactsPerson.linePhone1[0]" class="item-width"  style="width: 75px"/>
              区号<el-input v-model="workPoolData.contactsPerson.linePhone1[1]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
              号码<el-input v-model="workPoolData.contactsPerson.linePhone1[2]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
              分机号<el-input v-model="workPoolData.contactsPerson.linePhone1[3]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>

<!--        <el-row >-->
<!--          <el-col :span="5">-->
<!--            <el-form-item label="办公室电话:"  style="white-space: nowrap" prop="phone">-->
<!--              国家区号:+<el-input v-model="workPoolData.contactsPerson.workPhone1[0]" class="item-width"  style="width: 75px"/>-->
<!--              区号<el-input v-model="workPoolData.contactsPerson.workPhone1[1]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>-->
<!--              号码<el-input v-model="workPoolData.contactsPerson.workPhone1[2]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>-->
<!--              分机号<el-input v-model="workPoolData.contactsPerson.workPhone1[3]" class="item-width"  size="mini" style="width: 145px" maxlength="50"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->

<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="是否涉及银行转账" prop="bankTransfer" >-->
<!--              <el-radio-group v-model="workPoolData.bankTransfer" @change="bankChange(workPoolData.bankTransfer)">-->
<!--                <el-radio   label="1">是</el-radio>-->
<!--                <el-radio   label="2">否</el-radio>-->
<!--              </el-radio-group>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="开户行：" v-show="workPoolData.bankTransfer=='1'" prop="bankName">-->
<!--              <el-input size="mini" v-model="workPoolData.bankName" ></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="开户地：" v-show="workPoolData.bankTransfer=='1'" prop="bankLocation">-->
<!--              <el-input size="mini" v-model="workPoolData.bankLocation" ></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="账号：" v-show="workPoolData.bankTransfer=='1'" prop="accountNumber">-->
<!--              <el-input size="mini" v-model="workPoolData.accountNumber" ></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

<!--          <el-col :span="8">-->
<!--            <el-form-item label="户名：" v-show="workPoolData.bankTransfer=='1'" prop="bankHolder">-->
<!--              <el-input size="mini" v-model="workPoolData.bankHolder" ></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
        <el-row>
          <el-col :span="16">
            <el-form-item label="业务内容：" prop="content">
              <el-input
                type="textarea"
                :rows="2"
                v-model="workPoolData.content">
              </el-input>
            </el-form-item>
          </el-col>
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
              <span>{{selectDictLabel(cs_order_state, scope.row.status)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="operateCode" label="操作" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.operateCode">
              <span>{{selectDictLabel(cs_action_type, scope.row.operateCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="makeBy" label="受/处理人" show-overflow-tooltip/>
          <el-table-column align="center" prop="umNum" label="UM账号" show-overflow-tooltip/>
          <el-table-column prop="makeTime" label="时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
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


    <el-card v-if="this.queryParams.status>2">
      <el-form ref="workPoolData2" :model="workPoolData"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini"
      >
        <span style="color: blue">服务处理</span>
        <el-divider></el-divider>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况" prop="bank" >
              <el-radio-group v-model="workPoolData.bank">
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
              v-model="workPoolData.textarea">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>


    </el-card>

    <el-card>
      <el-form ref="workPoolData3"  :model="workPoolData"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini" :rules="changeForm.rules">
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
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="workPoolData.editInfo.editRemark">
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
  import {demandListAndPublicPool,demandListAndPersonalPool,FlowLogSearch,modifySubmit} from '@/api/customService/demand'
  import modifyDetails from "../common/modul/modifyDetails";

  let dictss = [{dictType: 'product_status'}]
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
      const  rules_bank= {
          editReason: [
            {required: true, message: "修改原因不能为空", trigger: "blur"}
          ],
          editRemark: [
            {required: true, message: "修改说明不能为空", trigger: "blur"}
          ],
          'contactsPerson.mobilePhone':[
            {required: true, message: "联系人手机不能为空", trigger: "blur"}
          ],
          channelCode: [
            {required: true, message: "受理渠道不能为空", trigger: "blur"}
          ],
          itemCode: [
            {required: true, message: "服务项目不能为空", trigger: "blur"}
          ],
          priorityLevel: [
            {required: true, message: "优先级不能为空", trigger: "blur"}
          ],
          'contactsPerson.name': [
            {required: true, message: "联系人不能为空", trigger: "blur"}
          ],
          organCode: [
            {required: true, message: "出单机构不能为空", trigger: "blur"}
          ],
          // bankName: [
          //   {required: true, message: "开户行不能为空", trigger: "blur"}
          // ],
          // bankLocation: [
          //   {required: true, message: "开户地不能为空", trigger: "blur"}
          // ],
          // accountNumber: [
          //   {required: true, message: "账号不能为空", trigger: "blur"}
          // ],
          // bankHolder: [
          //   {required: true, message: "户名不能为空", trigger: "blur"}
          // ],
          content: [
            {required: true, message: "业务内容不能为空", trigger: "blur"}
          ],

        };
      const rules_noBank= {
        editReason: [
          {required: true, message: "修改原因不能为空", trigger: "blur"}
        ],
          editRemark: [
          {required: true, message: "修改说明不能为空", trigger: "blur"}
        ],
          'contactsPerson.mobilePhone':[
          {required: true, message: "联系人手机不能为空", trigger: "blur"},
            {required: true,
              message: "目前只支持中国大陆的手机号码",
              pattern: /^1[34578]\d{9}$/,//可以写正则表达式呦呦呦,
              trigger: "blur"},
        ],
          channelCode: [
          {required: true, message: "受理渠道不能为空", trigger: "blur"}
        ],
          itemCode: [
          {required: true, message: "服务项目不能为空", trigger: "blur"}
        ],
          priorityLevel: [
          {required: true, message: "优先级不能为空", trigger: "blur"}
        ],
          'contactsPerson.name': [
          {required: true, message: "联系人不能为空", trigger: "blur"}
        ],
          organCode: [
          {required: true, message: "出单机构不能为空", trigger: "blur"}
        ],
          content: [
          {required: true, message: "业务内容不能为空", trigger: "blur"}
        ],
        'editInfo.editRemark':[
          {required: true, message: "修改说明不能为空", trigger: "blur"},
          { min: 3, max: 100, message: '长度在 3 到 100 个字符' }
        ],
        'editInfo.editReason':[
          {required: true, message: "修改原因不能为空", trigger: "blur"}
        ]

      };

      return {
        cs_order_state:[],//状态
        cs_action_type:[],//操作类型
        workPoolDataFlag:false,
        workPoolDataFlag3:false,
        //下拉框
        cs_priority:[],//优先级
        cs_service_item:[],//服务项目
        cs_channel:[],//渠道
        cs_organization:[],//出单机构
        cs_sex:[],//性别
        cs_communication_language:[],//语言
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        //回显服务项目
        //需要填入数据的部分
        workPoolData:{
          callPerson:{},
          contactsPerson:{
            linePhone1:[],
            workPhone1:[],
            homePhone1:[],
          },//联系人
          workOrderNo:"",//工单号
          channelCode:"",//受理渠道
          callCenterId:"",//电话中心业务流水号
          callMobilePhone:"",//来电人电话
          priorityLevel:"",//优先级
          callName:"",//来电人
          callRelationBy:"",//来电人与被保人关系
          contactsName:"",//联系人
          contactsSex: "",//联系人性别
          contactsRelationBy: "",//联系人与别抱人关系
          contactsLanguage: "",//联系人语言
          contactsMobilePhone: "",//联系人电话
          email:"",//邮件
          organCode:"",//出单机构
          bankTransfer:"",//是否涉及转账
          bankName:"",//开户行
          bankLocation:"",//开户地
          accountNumber:"",//账号
          bankHolder:"",//户名
          content:"",//内容业务
          contactsCountry:"",//
          contactsQuhao:"",
          contactsNumber:"",
          contactsSecondNumber:"",
          officeCountry:"",//
          officeQuhao:"",//
          officeNumber:"",//
          officeSecondNumber:"",//
          editInfo:{
            editReason:"",//修改原因
            editRemark:"" //修改说明

          },
          alterId:[]
        },
        totalCount: 0,
        // 表单校验
        rules1:rules_bank,
        rules2:rules_noBank,
        changeForm:{
          rules:rules_noBank
        },//用来区分有无转账的校验
        // 查询参数
        queryParams: {
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
      }
    },
    created() {
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      this.getDicts("cs_sex").then(response => {
        this.cs_sex = response.data;
      });
      this.getDicts("cs_communication_language").then(response => {
        this.cs_communication_language = response.data;
      });
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
      this.getDicts("cs_organization").then(response => {
        this.cs_organization = response.data;
      });
      this.getDicts("cs_priority").then(response =>{
        this.cs_priority = response.data;
      });
      this.getDicts("cs_channel").then(response => {
        this.cs_channel = response.data;
      });
      this.getDicts("cs_action_type").then(response => {
        this.cs_action_type = response.data;
      });
      this.getDicts("cs_order_state").then(response => {
        this.cs_order_state = response.data;
      });
      this.searchHandle()
      this.searchFlowLog()
    },

    methods: {
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //监听是否银行转账事件
      // bankChange(s){
      //   if (s=="1"){
      //     this.changeForm.rules=this.rules1
      //   }else {
      //     this.changeForm.rules=this.rules2
      //   }
      //
      // },
      //提交页面数据
      submit(){
        this.$refs.workPoolData.validate((valid) => {
           this.workPoolDataFlag=valid
        })
        this.$refs.workPoolData3.validate((valid) => {
            this.workPoolDataFlag3=valid
        })
          if (this.workPoolDataFlag&&this.workPoolDataFlag3) {
            let insert=this.workPoolData
            if (this.$route.query.alterId!=null){
              insert.alterId=JSON.parse(this.$route.query.alterId)
            }
            insert.workOrderNo=this.$route.query.workOrderNo
            modifySubmit(insert).then(res => {
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
          }else {
            return this.$message.warning(
              "请检查输入信息格式！"
            )
          }




      },
      //关闭页面
      close(){

      },
      //反显信息需求
      searchHandle() {
        if (this.queryParams.status=="01") {
          let query = this.queryParams
          demandListAndPublicPool(query).then(res => {
            if (res!=null && res.code === 200) {
              let workPoolData = res.rows[0];
              let editInfo = {
                editReason: "",
                editRemark: ""
              };
              workPoolData.editReason="",
              workPoolData.editRemark="",
              workPoolData.editInfo=editInfo
              workPoolData.officeCountry=""
              workPoolData.officeNumber=""
              workPoolData.officeQuhao=""
              workPoolData.officeSecondNumber=""
              workPoolData.item=[]
              this.workPoolData = workPoolData;
            //  this.bankChange(workPoolData.bankTransfer),//初始化是否校验银行
                this.totalCount = res.total
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
              let workPoolData = res.rows[0];
              let editInfo = {
                editReason: "",
                editRemark: ""
              };
              workPoolData.editInfo=editInfo
              workPoolData.officeCountry=""
              workPoolData.officeNumber=""
              workPoolData.officeQuhao=""
              workPoolData.officeSecondNumber=""
              this.workPoolData = workPoolData;
             // this.bankChange(workPoolData.bankTransfer),
              this.totalCount = res.total
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
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
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
