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
    <el-table-column align="center" prop="source" label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
    <el-table-column align="center" prop="claimType" label="理赔类型" show-overflow-tooltip/>
    <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
    <el-table-column align="center" prop="policyManageCom" label="承保机构" show-overflow-tooltip/>
    <el-table-column align="center" prop="payAmount" label="赔付金额" show-overflow-tooltip/>
    <el-table-column align="center" prop="updateBy" label="审核人" show-overflow-tooltip/>
    <el-table-column align="center" prop="caseStatus" label="状态" show-overflow-tooltip/>
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
    // getCurrentRow(row){//获取当前行的数据
    //   this.$emit('radioVue',row.collectionId);
    // }
  }
}
</script>
