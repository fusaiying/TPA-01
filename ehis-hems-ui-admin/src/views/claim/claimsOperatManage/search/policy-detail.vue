<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>合同信息</span>
        <span style="float: right;" >
          <el-button type="primary" size="mini" @click="goBack">关闭</el-button>
        </span>
      </div>
      <el-row style="margin: 0 40px;">
        <el-col :span="8">
          <span class="info_span">保单状态：{{ dict.label.policy_status[info.status] }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">管理机构：{{info.manageComName}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">销售渠道：{{dict.label.saveChannel[info.saleChnl]}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">业务员代码：{{info.businessCode}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">业务员姓名：{{info.businessName}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">所属部门：{{info.department}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">销售机构代码：{{info.manageCom}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">销售机构地址：{{info.saleAddress}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">保单回执客户签收期：{{info.returnDate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">承保日期：{{info.acceptDate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">保单生效日：{{info.cvalidate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">保单期满日：{{info.expireDate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">保单交至日：{{info.payToDate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">承保结论：{{info.acceptConclusion}}</span>
        </el-col>
        <el-col :span="24" style="display: flex">
          <span class="info_span">特别约定：</span>
          <el-input :disabled="true" type="textarea" style="flex: 1" :autosize="{ minRows: 3, maxRows: 10}" placeholder="请输入内容" v-model="info.special"></el-input>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px">
      <div slot="header" class="clearfix">
        <span>投保人信息</span>
      </div>
      <el-row style="margin: 0 40px;">
        <el-col :span="8">
          <span class="info_span">客户号：{{info.appntNo}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">姓名：{{info.appntName}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">性别：{{dict.label.sex[info.appntSex]}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">出生日期：{{info.appntBirthday}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">职业：{{dict.label.payee_occupation[info.appntOccupation]}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">国籍：{{dict.label.nativeplace[info.appntNationality]}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">证件类型：{{dict.label.card_type[info.appntIdType]}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">证件号码：{{info.appntIdNo}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">移动电话：{{info.appntMobile}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">联系电话：{{info.appntPhone}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">邮箱：{{info.appntEmail}}</span>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px">
      <div slot="header" class="clearfix">
        <span>缴费资料</span>
      </div>
      <el-row style="margin: 0 40px;">
        <el-col :span="8">
          <span class="info_span">缴费方式：{{dict.label.payMethods[info.payMode]}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">开户银行：{{info.bankName}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">户名：{{info.accName}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">账号：{{info.accNo}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">期缴保费：{{info.regularPay}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">交至日期：{{info.payToDate}}</span>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px">
      <el-tabs type="card" v-model="activeName1">
        <el-tab-pane :label="'被保人信息(' + tableData.length + ')'" name="first">
          <el-table
            :data="tableData"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column align="center" prop="insuredNo" label="客户号" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="relationshipAppnt" :formatter="getRelationshipAppnt" label="与投保人关系" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="insuredName" label="姓名" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="sex" :formatter="sex" label="性别" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="birthdate" label="出生日期" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="occupation" :formatter="occupation" label="职业" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="nationality" :formatter="nationality" label="国籍" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="idType" :formatter="idType" label="证件类型" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="idNo" width="180" label="证件号码"></el-table-column>
            <el-table-column align="center" prop="contectNumber" label="移动电话" show-overflow-tooltip></el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px">
      <el-tabs type="card" v-model="activeName1">
        <el-tab-pane :label="'保单险种信息(' + info.riskList.length + ')'" name="first">
          <el-table
            :data="info.riskList"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column type="expand">
              <template slot-scope="scope">
                <el-table
                  :data="scope.row.rowList"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  style="width: 100%;">
                  <el-table-column align="center" prop="endDate" label="终止日期" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="payYears" label="交费年期" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="payIntv" :formatter="getpayIntv" label="交费频次" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="reason" label="原因" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="gracePeriod" label="宽限期" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="unpaid" label="应缴但未缴保费" show-overflow-tooltip></el-table-column>
                </el-table>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="riskSeqNo" label="险种序号" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="insuredName" label="被保人姓名" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="insuredNo" label="客户号" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="riskName" label="险种名称" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="riskCode" label="险种代码" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="amount" label="保额" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="grade" label="档次" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="prem" label="保费" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="cvalidate" label="生效日期" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="expireDate" label="满期日期" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="payToDate" label="交至日期" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="riskState" :formatter="riskState" label="险种状态" show-overflow-tooltip></el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>
<script>
import policeHoder from '../../claimsHandle/common/modul/policeHoder.vue'
import localStorage from '../../../../utils/localstage'
export default {
  dicts: ['payMethods','policy_status','paymentmode','saveChannel',
    'relation_ship_register','payee_insuredrelationship','sex','nativeplace',
    'payee_occupation','card_type','payMethods','policy_status','paymentmode',
    'relation_ship_apply'
  ],
  components: {
    policeHoder
  },
  filters: {
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '') {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(0)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  data() {
    return {
      tableData: [],
      riskTableData: [
        {
          name: 111,
          data: []
        },
        {
          name: 222,
          data: []
        }
      ],
      info: {},
      totalCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      dialogVis: false,
      insuredInfo: {},
      list: [],
      activeName1: 'first'
    }
  },
  mounted(){
    let data = JSON.parse(localStorage.get('data'))
    let list = JSON.parse(localStorage.get('insuredList'))
    // let data = JSON.parse(this.$route.query.data)
    // let list = JSON.parse(this.$route.query.insuredList)
    this.tableData = list
    // this.setCurrentPageData()
    console.log(data,1234,this.tableData)
    this.info = data
    this.info && this.info.riskList.map((item) =>{
      item.rowList = []
      let from = {}
      from.endDate = item.endDate
      from.payYears = item.payYears
      from.payIntv = item.payIntv
      from.reason = item.reason
      from.gracePeriod = item.gracePeriod
      from.unpaid = item.unpaid
      item.rowList.push(from)
    })
  },
  methods: {
    sex(row) {
      return this.dict.label.sex[row.sex]
    },
    nationality(row) {
      return this.dict.label.nativeplace[row.nationality]
    },
    occupation(row) {
      return this.dict.label.payee_occupation[row.occupation]
    },
    idType(row) {
      return this.dict.label.card_type[row.idType]
    },
    riskState(row) {
      return this.dict.label.policy_status[row.riskState]
    },
    getRelationshipAppnt(row) {
      return this.dict.label.relation_ship_apply[row.relationshipAppnt]
    },
    sizeChange(val){
      this.pageInfo.pageSize = val
      this.setCurrentPageData()
    },
    getpayIntv(row) {
      return this.dict.label.paymentmode[row.payIntv]
    },
    pageChange(val){
      this.pageInfo.page = val
      this.setCurrentPageData()
    },
    goBack(){
      localStorage.remove('data')
      localStorage.remove('insuredList')
      window.close()
    },
    searchDetail() {
      this.dialogVis = true
      this.insuredInfo = this.info
    },
    getPolicyStatus(row,col) {
      return this.dict.label.policy_status[row.riskState]
    },
    setCurrentPageData() {
      let begin = (this.pageInfo.page - 1) * this.pageInfo.pageSize;
      let end = this.pageInfo.page * this.pageInfo.pageSize;
      this.list = this.tableData.slice(begin,end);
      this.totalCount = this.tableData.length
      this.list = [...this.list]
    },
    prevPage() {
      if (this.pageInfo.page == 1) return;
      this.pageInfo.page--;
      this.setCurrentPageData();
    },
    nextPage() {
      if (this.pageInfo.page == this.totalCount )return ;
      this.pageInfo.page++;
      this.setCurrentPageData();
    },
  }
}
</script>
<style scoped>
  .desc_box {
    width: 100%;
    min-height: 60px;
    border: 1px solid #eee;
    padding: 10px; color: #555;
    font-size: 14px;
  }
</style>
