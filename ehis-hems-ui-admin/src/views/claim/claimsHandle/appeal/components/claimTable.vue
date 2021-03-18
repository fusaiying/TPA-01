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

import {addAppeal} from "@/api/appeal/api";

import {getUserInfo} from '@/api/claim/standingBookSearch'



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
      orgId:'',
    }
  },
  created() {
    this.getLoginInfo();
  },
  methods: {
    // 获取案件
    appealClaimFun(row){
      /**
       可支付	01
       支付中	02
       已支付	03
       转账失败	04
       退票	05
       该案件还未支付，请在支付环节进行回退操作，请核实
       */
      if(row.appealClaim !== '0') {
        this.$message({ type: 'info',  message: '无法获取，该案件在申诉中'});
        return false;
      }
      if(row.payStatus !== '03') {
        this.$message({ type: 'info',  message: '该案件还未支付，请在支付环节进行回退操作，请核实。'});
        return false;
      }

      const params = {};
      params.appealRptNo = row.rptNo;
      params.deptCode = this.orgId;

      addAppeal(params).then(res => {
        if (res.code === 200) {
          this.$message({
            message: '获取成功！',
            type: 'success',
            center: true,
            showClose: true
          });
          this.$emit('initAppealData');
        }else {
          this.$message({
            message: '获取失败！',
            type: 'error',
            center: true,
            showClose: true
          });
        }
      }).catch(error => {
        console.log(error);
      });
    },
    getLoginInfo(){
      getUserInfo().then(response => {
        if(response.data) {
          this.orgId = response.data.organCode.toString();
        }
      })
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
