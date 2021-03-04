<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    v-loading="loading"
    style="width: 100%;">
    <el-table-column align="center" min-width="150" prop="rptNo" label="报案号" show-overflow-tooltip/>
    <el-table-column align="center" :formatter="getDeliverySourceName" prop="source" label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
    <el-table-column align="center" prop="claimType" :formatter="getClaimTypeName" label="理赔类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="policyManageCom" label="承保机构" show-overflow-tooltip/>
    <el-table-column align="center" prop="payAmount" label="赔付金额" show-overflow-tooltip/>
    <el-table-column align="center" prop="updateBy" label="审核人" show-overflow-tooltip/>
    <el-table-column align="center" prop="caseStatus" :formatter="getStatusName" label="状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="endCaseTime" label="结案日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="createBy" label="操作" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button size="mini" type="text"   @click="appealClaimFun(scope.row)">获取  </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

export default {
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
    payStatus: {
      type: Array,
      default: function() {
        return []
      }
    },
    claimStatus: {
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
  data() {
    return {
      loading:true,
      radio:false,
    }
  },
  methods: {
    // 获取案件
    appealClaimFun(row){

    },
    getDeliverySourceName(row,col) {
      return this.selectDictLabel(this.deliverySource, row.source)
    },
    getClaimTypeName(row,col) {
      return this.selectDictLabel(this.claimTypes, row.claimType)
    },

    getStatusName(row,col) {
     let v1 =  this.selectDictLabel(this.claimStatus, row.caseStatus);
     let v2 = '';
     if(row.payStatus != null) {
       v2 = this.selectDictLabel(this.payStatus, row.payStatus);
     }
     if (v2 != '') {
       v1  +=  "  |  " + v2;
     }
      return v1;
    },
  }
}
</script>
