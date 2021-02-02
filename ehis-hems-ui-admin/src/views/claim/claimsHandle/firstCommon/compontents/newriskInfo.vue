<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">风险信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button :disabled="applysourcetype!='2'" type="primary" size="mini" @click="getRiskInfo">风险查询</el-button>
        </span>
        <el-row style="margin: 0 20px 10px;">
          <el-col :span="6">风险类型：{{ dangerInfo.risktypename }}</el-col>
          <el-col :span="6">风险等级：<span style="color: #f00;">{{ dangerInfo.risklevelname }}</span></el-col>
          <el-col :span="12">风险详情：<span style="color: #f00;">{{ dangerInfo.riskdescription }}</span></el-col>
        </el-row>
        <el-table
          size="small"
          highlight-current-row
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          :data="dangerInfo.riskdetail"
          style="width: 100%;">
          <el-table-column align="center" prop="area" label="地区"></el-table-column>
          <el-table-column align="center" prop="hospitalname" label="医院"></el-table-column>
          <el-table-column align="center" prop="diagnosistype" label="诊断类型"></el-table-column>
          <el-table-column align="center" prop="diagnosisname" label="诊断信息"></el-table-column>
          <el-table-column align="center" label="就诊日期">
            <template slot-scope="scope">
              <span v-if="scope.row.visitdate">{{ scope.row.visitdate }}至{{ scope.row.dischargedate }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="dataresource" label="数据来源"></el-table-column>
        </el-table>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import{ getRiskInfo } from '@/api/claim/handleDeal'
export default {
  inject: ['getDangerInfo'],
  props: {
    status: String,
    node: String,
    claimno: String,
    applysourcetype: String,
    dangerInfo:{
      type: Object,
      default: function() {
        return {}
      }
    }
  },
  data() {
    return {
      activeNames: ['1']
    }
  },
  methods: {
    getRiskInfo(){
      getRiskInfo(this.claimno).then(res => {
        if (res.status === '1') {
          this.getDangerInfo(this.claimno)
        } else {
          this.$message({ message: '风险查询失败！', type: 'error' })
        }
      })
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}
.el-collapse {
  border: none;
}
.el-tabs ::v-deep .el-tabs__item {
  width: 10%;
}
.el-collapse ::v-deep .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
