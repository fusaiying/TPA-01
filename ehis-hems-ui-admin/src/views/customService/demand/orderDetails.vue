<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-form ref="baseInfo" :model="baseInfo" label-width="180px"
               label-position="right" size="mini">
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

    <el-card class="box-card" style="margin-top: 10px;">
      <el-form style="padding-bottom: 30px;" label-width="180px"
               label-position="right" size="mini" :disabled="true">
        <span style="color: blue">{{ selectDictLabel(cs_business_type, workPoolData.businessService != undefined && workPoolData.businessService != null ? workPoolData.businessService.split('-')[0] : '') + '-' + selectDictLabel(cs_service_item, workPoolData.businessService != null ? workPoolData.businessService.split('-')[1] : '') }}</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：">
              <el-input v-model="workPoolData.workOrderNo" class="item-width" clearable size="mini" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="phoneNumber">
              <el-input v-model="workPoolData.callCenterId" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-select v-model="workPoolData.channelCode" class="item-width" >
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="workPoolData.priorityLevel" class="item-width" >
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="phone">
              <el-input v-model="workPoolData.callPerson.name" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人与被保人关系：" prop="priority">
              <el-select v-model="workPoolData.callRelationBy" class="item-width" >
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人：" prop="lxperson">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="priority">
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width" >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人与被保人关系：" prop="priority">
              <el-select v-model="workPoolData.contactsRelationBy" class="item-width" >
                <el-option v-for="item in cs_relation" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="priority">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" >
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width" size="mini"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item  label="联系人固定电话：" style="white-space: nowrap" prop="phone">
              国家区号+
              <el-input v-model="workPoolData.contactsPerson.linePhone1[0]" class="item-width"
                        style="width: 75px"/>
              区号
              <el-input v-model="workPoolData.contactsPerson.linePhone1[1]" class="item-width" size="mini"
                        style="width: 75px" maxlength="50"/>
              号码
              <el-input v-model="workPoolData.contactsPerson.linePhone1[2]" class="item-width" size="mini"
                        style="width: 120px" maxlength="50"/>
              分机号
              <el-input v-model="workPoolData.contactsPerson.linePhone1[3]" class="item-width" size="mini"
                        style="width: 75px" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="所在地：" prop="phone">
              <el-input v-model="workPoolData.contactsPerson.address" class="width-full" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="priority">
              <el-select v-model="workPoolData.organCode" class="item-width" placeholder="请选择" >
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="16">
            <el-form-item label="业务内容：" prop="textarea">
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
            <el-table-column prop="makeTime" label="时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.makeTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='03'" style="font-size:12px" type="primary"
                       @click="modifyDetails(scope.row)">修改说明
              </el-link>
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

    <el-card class="box-card" style="margin-top: 10px;">
      <el-form style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini" :disabled="true">
        <span style="color:blue;">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况:">
              <el-input v-model="orderDetailSearch.handleProp1" class="width-full" size="mini" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              v-model="orderDetailSearch.remark" >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import moment from 'moment'
import {
  demandListAndPublicPool,
  demandListAndPersonalPool,
  FlowLogSearch,
  orderDetailSearch
} from '@/api/customService/demand'
import coOrganizer from "../common/modul/coOrganizer";
import modifyDetails from "../common/modul/modifyDetails";
import {policyInfoData} from "@/api/customService/common";

let dictss = [
  {dictType: 'cs_business_type'},
  {dictType: 'cs_service_item'},
  {dictType: 'cs_channel'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_relation'},
  {dictType: 'cs_sex'},
  {dictType: 'cs_communication_language'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_order_state'},
  {dictType: 'cs_action_type'},
  {dictType: 'rgtSex'},
  {dictType: 'card_type'},
]

export default {
  components: {
    coOrganizer, modifyDetails
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

      //最下面数据
      orderDetailSearch: {
        handleProp1: "",
        remark: ""
      },
      //客户信息查询
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
      //质疑理赔结果用
      //数据反显用
      workPoolData: {
        contactsPerson: {
          linePhone1: ['','','','']
        },
        callPerson: {},

      },
      // 查询参数
      queryParams: {
        workOrderNo: "",
        policyNo: "",
        policyItemNo: "",
        status: "",
        pageNum: 1,
        pageSize: 10
      },
      search: {
        workOrderNo: "",
      },
      flowLogData: [],
      flowLogCount: 0,
      dictList: [],
      cs_business_type: [],
      cs_service_item: [],
      cs_channel: [],
      cs_priority: [],
      cs_relation: [],
      cs_sex: [],
      cs_communication_language: [],
      cs_organization: [],
      cs_order_state: [],//状态
      cs_action_type: [],//操作类型
      attachmentInfoData: [],
      rgtSex: [],//
      card_type: [],//
    }
  },
  created() {
    //入参
    this.queryParams.workOrderNo = this.$route.query.workOrderNo;
    this.queryParams.policyNo = this.$route.query.policyNo;
    this.queryParams.policyItemNo = this.$route.query.policyItemNo;
    this.queryParams.status = this.$route.query.status;
    this.search.workOrderNo = this.$route.query.workOrderNo;
    this.searchHandle()
    this.searchFlowLog()
    this.searchHandle2()
    this.searchBaseInfo();
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
    this.cs_relation = this.dictList.find(item => {
      return item.dictType === 'cs_relation'
    }).dictDate
    this.cs_sex = this.dictList.find(item => {
      return item.dictType === 'cs_sex'
    }).dictDate
    this.cs_communication_language = this.dictList.find(item => {
      return item.dictType === 'cs_communication_language'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_business_type = this.dictList.find(item => {
      return item.dictType === 'cs_business_type'
    }).dictDate
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_action_type = this.dictList.find(item => {
      return item.dictType === 'cs_action_type'
    }).dictDate
    this.cs_order_state = this.dictList.find(item => {
      return item.dictType === 'cs_order_state'
    }).dictDate
    this.rgtSex = this.dictList.find(item => {
      return item.dictType === 'rgtSex'
    }).dictDate
    this.card_type = this.dictList.find(item => {
      return item.dictType === 'card_type'
    }).dictDate
  },

  methods: {
    //客户信息加载
    searchBaseInfo() {
      let query = {
        policyNo: this.queryParams.policyNo,
        policyItemNo: this.queryParams.policyItemNo,
      }
      if(this.queryParams.policyNo != null && this.queryParams.policyNo !=""){
        policyInfoData(query).then(res => {
          if (res != null && res.code === 200) {
            this.baseInfo = res.data;
          }
        }).catch(res => {

        })
      }
    },
    //最下面结论用
    searchHandle2() {
      let query = this.search
      orderDetailSearch(query).then(res => {
        if (res != null && res.code === 200) {
          if (res.rows.length>0) {
            this.orderDetailSearch = res.rows[0]
            console.log('工单详情', this.orderDetailSearch)
          }
        }
      }).catch(res => {

      })
    },
    //超链接用
    modifyDetails(s) {
      this.$refs.modifyDetails.queryParams.subId = s.subId;
      this.$refs.modifyDetails.queryParams.workOrderNo = this.queryParams.workOrderNo;
      this.$refs.modifyDetails.open();
      ;
    },
    //反显信息需求
    searchHandle() {
      if (this.queryParams.status == "01") {
        console.log("status值", this.queryParams.status)
        const query = this.queryParams
        demandListAndPublicPool(query).then(res => {
          if (res != null && res.code === 200) {
            if (res.rows.length>0){
              let workPoolData = res.rows[0];
              let editInfo = {
                editReason: "",
                editRemark: ""
              };
              workPoolData.editInfo = editInfo;
              workPoolData.officeCountry = "";
              workPoolData.officeNumber = "";
              workPoolData.officeQuhao = "";
              workPoolData.officeSecondNumber = "";
              this.workPoolData = workPoolData;
            }

          }
        }).catch(res => {
          return this.$message.error(
            "信息需求受理数据加载异常！"
          )
        })
      } else {
        let query = this.queryParams
        demandListAndPersonalPool(query).then(res => {
          if (res != null && res.code === 200) {
            if (res.rows.length>0){
              let workPoolData = res.rows[0];
              let editInfo = {
                editReason: "",
                editRemark: ""
              };
              workPoolData.editInfo = editInfo
              workPoolData.officeCountry = ""
              workPoolData.officeNumber = ""
              workPoolData.officeQuhao = ""
              workPoolData.officeSecondNumber = ""
              this.workPoolData = workPoolData;
            }

            console.log(this.workPoolData);
          }
        }).catch(res => {
          return this.$message.error(
            "信息需求受理数据加载异常！"
          );
        })

      }
    },
    //查询轨迹表
    searchFlowLog() {
      let workOrderNo = this.queryParams
      workOrderNo.status = ""
      FlowLogSearch(workOrderNo).then(res => {
        if (res != null && res.code === 200) {
          if (res.rows.length>0){
            this.flowLogData = res.rows;
            this.flowLogCount = res.total;
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
  width: 170px;
}
</style>
