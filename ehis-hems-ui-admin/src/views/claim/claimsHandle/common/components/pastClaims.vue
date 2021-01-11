
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>既往赔案</span>
    </div>
    <el-row>
      <el-table
        ref="table"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-row>
              <el-col :span="8">
                <span class="expand_span">原型及需求文档未列出展示内容：{{ props.row.policyNo }} </span>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="policyNo" label="赔案号" show-overflow-tooltip/>
        <el-table-column align="center" prop="riskName" label="被保人姓名" show-overflow-tooltip/>
        <el-table-column align="center" prop="date" label="身份证号" width="240" show-overflow-tooltip/>
        <el-table-column align="center" prop="policyStatus" label="险种名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="special" label="责任名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="renewal" label="赔付金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="renewal" label="赔付结论" show-overflow-tooltip/>
        <el-table-column align="center" prop="match" label="理赔状态" show-overflow-tooltip/>
      </el-table>
    </el-row>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      tableData: [
        { policyNo: 'QWE23423645' }
      ]
    }
  }
}
</script>
