<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>被保人详情</span>
        <span style="float: right;" >
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-row style="margin: 0 40px;">
        <el-col :span="8">
          <span class="info_span">被保人客户号：{{detailFrom.insuredNo}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">被保人姓名：<span style="color:rgba(95,159,248,1)" @click="searchPylice">{{detailFrom.insuredName}}</span></span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">性别：{{`${dict.label.sex[detailFrom.sex]}`}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">证件类型：{{`${dict.label.card_type[detailFrom.idType]}`}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">证件号码：{{detailFrom.idNo}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">出生日期：{{detailFrom.birthdate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">联系电话：{{detailFrom.phone}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span">邮箱（Email）：{{detailFrom.email}}</span>
        </el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top:20px">
      <el-tabs v-model="activeName" type="card" style="margin-bottom:10px" @tab-click="choseActive(activeName)">
        <el-tab-pane label="赔案信息" name="0">
          <componentsTab v-loading="tableLoading" :claimInformationTotalCount="claimInformationTotalCount"  :claimInformationData="claimInformationData" :status="activeName" @getList="getList"/>
        </el-tab-pane>
        <el-tab-pane label="垫付信息" name="1">
          <componentsTab v-loading="tableLoading"  :AdvancePayData="AdvancePayData" :status="activeName" @getList="getList"/>
        </el-tab-pane>
        <el-tab-pane label="调查信息" name="2">
          <componentsTab v-loading="tableLoading" :investigateDataTotalCount="investigateDataTotalCount"   :InvestigateData="InvestigateData" :status="activeName" @getList="getList"/>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    <insuredInfo v-model="policeHoder" :insured-info="insuredInfo"/>
  </div>
</template>
<script>
import componentsTab from './componentsTab'
import {getClaimInformationList, getInvestigateList, getAdvancePayList} from '@/api/search/index.js'
import {pastSurvey} from '../../../../api/claim/handleDeal'
import insuredInfo from '../../claimsHandle/common/modul/insuredInfo'
export default {
  components: {
    componentsTab,
    insuredInfo
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
  dicts: ['registrantidtype','sex','card_type'],
  data() {
    return {
      activeName: '0',
      tableLoading: false,
      pendingTableData: [],
      claimInformationData: [],
      AdvancePayData: [],
      InvestigateData: [],
      name: '',
      investigateDataTotalCount: 0,
      claimInformationTotalCount: 0,
      policeHoder: false,
      // methodData: 'getClaimInformationList',
      insuredInfo: {},
      detailFrom: {},
      pageno: 1,
      pagesize: 10,
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    choseActive(activeName) {
      this.activeName = activeName
    },
    searchPylice() {
      this.policeHoder = true
      this.insuredInfo = this.detailFrom
    },
    getList(pagesize,page,activeName) {
      let query = {
        policyno: this.$route.query.policyno || '',
        name: this.$route.query.name || '',
        idcardno: this.$route.query.idcardno || '',
        insuredno: this.detailFrom.insuredNo || '',
        claimno: this.$route.query.claimno || '',
        pageno: page,
        pagesize: pagesize,
      }
      let data = {
        insuredno: this.detailFrom.insuredNo || '',
        pageno: page,
        pagesize: pagesize,
        ispage: true
      }
      if (activeName === '0') {
        getClaimInformationList(query).then(res => {
          this.claimInformationData = (res.data && res.data.list) || []
          this.claimInformationTotalCount = (res.data && res.data.total) || 0
        })
      } else if (activeName === '1') {

      } else {
        pastSurvey(data).then(response => {
          if (response.status === '1') {
            this.InvestigateData = response.data && response.data.list || []
            this.investigateDataTotalCount = response.data.total
          } else {
            this.$message({ message: '查询既往调查错误！', type: 'error' })
          }
        })
      }
    },

  },
  mounted() {
    this.detailFrom = JSON.parse(this.$route.query.data)
    this.insuredInfo = JSON.parse(this.$route.query.data)
    console.log(this.$route.query.data,3456789)
    this.getList(this.pagesize,this.pageno,'0')
    this.getList(this.pagesize,this.pageno,'2')
  }
}
</script>
