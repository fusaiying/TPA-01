<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="160px"
               label-position="right" size="mini">
        <el-row>
          <!--clearable是清楚输入框内容 readly、只读不可以编辑 ；不可以共存-->
          <el-col :span="8">
            <el-form-item label="保单号："  >
              <el-input readonly v-model="sendForm.acceptor" class="item-width"  size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名："  readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人出生日期:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件类型:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢生效日:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢满期日:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="主招揽业务员电话:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="是否UHCG会员:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称:" >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="AM(服务经理):"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="BD(销售经理):"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="特定医院赔付比例:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源:"  >
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>


    <el-card class="box-card" style="margin-top: 10px;">
      <el-form  style="padding-bottom: 30px;" label-width="180px"
               label-position="right" size="mini">

        <span style="color: blue">信息需求-理赔类</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="phoneNumber">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini" readonly/>
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
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='01'" style="font-size:12px" type="primary" @click="modifyDetails(scope.row)">修改说明</el-link>
            </template>
          </el-table-column>
          <el-table-column prop="opinion" align="center" label="处理意见" show-overflow-tooltip/>
          <el-table-column prop="toDepartment" align="center" label="流转部门" show-overflow-tooltip/>
          <el-table-column prop="toReason" align="center" label="流传原因" show-overflow-tooltip/>
        </el-table>
        <modify-details ref="modifyDetails"></modify-details>

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
      <el-form  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color:blue;">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况:"  >
              <el-input v-model="orderDetailSearch.handleProp1" class="width-full"  size="mini" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              v-model="orderDetailSearch.remark" readonly>
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>


    </el-card>


  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool ,FlowLogSearch,orderDetailSearch} from '@/api/customService/demand'
  import coOrganizer from "../common/modul/coOrganizer";
  import modifyDetails from "../common/modul/modifyDetails";
  export default {
    components: {
      coOrganizer,modifyDetails
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
        //最下面数据
        orderDetailSearch:{
          handleProp1:"",
          remark:""
        },
        //客户信息查询
        sendForm:{},
        //质疑理赔结果用
        //数据反显用
        workPoolData:{
          contactsPerson:{
            homePhone1:[]
          },
          callPerson: {},

        },
        serves:[],
        // 查询参数
        queryParams: {
          workOrderNo:"",
          policyNo:"",
          policyItemNo:"",
          status:"",
          pageNum: 1,
          pageSize: 10
        },
        search:{
          workOrderNo:"",
        },
        dealPool:"",
        dealTotal:0,
        totalCount: 0,
        flowLogData:[],
        flowLogCount: 0,
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
      this.searchHandle2()

    },


    methods: {
      //最下面结论用
      searchHandle2() {
        this.search.workOrderNo=this.$route.query.workOrderNo;
        let query=this.search
        orderDetailSearch(query).then(res => {
          if (res != null && res.code === 200) {
            if (res.rows[0]!="undefined"&&res.rows[0]!=null){
              this.orderDetailSearch = res.rows[0]
              console.log('工单详情',this.orderDetailSearch)
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
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //反显信息需求
      searchHandle() {
        if (this.queryParams.status=="01") {
          console.log("status值",this.queryParams.status)
          const query = this.queryParams
          demandListAndPublicPool(query).then(res => {
            if (res!=null && res.code === 200) {
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
              this.totalCount = res.total
              console.log("公共",this.workPoolData)
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
              this.totalCount = res.total
              console.log(this.workPoolData)

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
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
