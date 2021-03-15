<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    v-loading="loading"
    style="width: 100%;">
    <el-table-column align="center" min-width="150" prop="appealRptNo" label="报案号" show-overflow-tooltip/>
    <el-table-column :formatter="getDeliverySourceName" align="center" prop="caseStatus"  label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
    <el-table-column align="center" prop="claimType" :formatter="getClaimTypeName"  label="理赔类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="policyManageCom" label="承保机构" show-overflow-tooltip/>
    <el-table-column align="center" prop="payAmount" label="赔付金额" show-overflow-tooltip/>
    <el-table-column align="center" prop="auditor" label="审核人" show-overflow-tooltip/>
    <el-table-column align="center" :formatter="getAppealStatusName" prop="appealStatus" label="申诉状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="updateTime" label="操作日期" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ scope.row.updateTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="updateBy" label="操作人" show-overflow-tooltip/>
    <el-table-column align="center"  v-if="status === '02'" prop="monitoringTime" label="修正理赔号" show-overflow-tooltip/>-->
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button  v-if="status === '01'" size="mini"  type="text" @click="handleFun(scope.row,'initiate')">发起 </el-button>
        <el-button  v-if="status === '03'" size="mini"  type="text" @click="handleFun(scope.row,'audit')">处理 </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import moment from "moment";

let dictss = [{dictType: 'appeal_status'}]

export default {
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    deliverySource: {
      type: Array,
      default: function() {
        return []
      }
    },
    claimTypes: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String,
  },
  watch: {
    tableData:function(newValue) {
      this.loading = false;
    },
  },
  async mounted(){

    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    //申诉状态
    this.appealStatus = this.dictList.find(item => {
      return item.dictType === 'appeal_status'
    }).dictDate

  },
  data() {
    return {
      loading:true,
      appealStatus:[],
      detailInfo:{
        row:'',
        type:'',
      },
    }
  },
  methods: {
    handleFun(row,type) {
      this.detailInfo.row = row;
      this.detailInfo.type = type;
      this.$emit('openDialog',this.detailInfo);
    },
    getDeliverySourceName(row,col) {
      return this.selectDictLabel(this.deliverySource, row.source)
    },
    getClaimTypeName(row,col) {
      return this.selectDictLabel(this.claimTypes, row.claimType)
    },

    getAppealStatusName(row,col) {
      return this.selectDictLabel(this.appealStatus, row.appealStatus)
    },
  }
}
</script>
