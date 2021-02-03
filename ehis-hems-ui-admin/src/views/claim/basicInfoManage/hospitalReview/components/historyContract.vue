<template>
  <el-card class="box-card department-style" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>历史合约</span>
    </div>
    <el-table  :data="contractLogTableData"
               :header-cell-style="{color:'black',background:'#f8f8ff'}"
               size="small" highlight-current-row style="width: 100%;">
      <el-table-column key="1"  align="center" prop="contractNo" min-width="150" label="合约编码" show-overflow-tooltip/>
      <el-table-column  key="2"  align="center" min-width="100" prop="contractName" label="合约名称" show-overflow-tooltip/>
      <el-table-column key="3"  align="center" prop="providerName" label="服务机构名称" min-width="120" show-overflow-tooltip/>
      <el-table-column key="4"  align="center" prop="contractType" min-width="160" label="合约类型" show-overflow-tooltip/>
      <el-table-column  key="5" label="合约有效期"  align="center" width="300">
        <template slot-scope="scope">
          <span >{{ scope.row.cvali_date }} 一 {{ scope.row.expiry_date }}</span>
        </template>
      </el-table-column>
      <el-table-column key="6"   align="center" min-width="120" prop="applicationreason" label="状态" show-overflow-tooltip/>
      <el-table-column key="7" align="center" label="操作" min-width="94" fixed="right">
        <template slot-scope="scope">
          <el-button  size="small" type="text" @click="editHandle(scope.$index,scope.row)">查看</el-button>
        </template>
      </el-table-column>

    </el-table>

  </el-card>
</template>
<script>
import {getdepInfo, adddepInfo} from "@/api/baseInfo/medicalManage";

export default {
  props: {
    contractLogTableData: Array

  },

  data() {
    return {
      /* contractLogTableData: [{'providerName':'xxx',cvali_date: '2222',expiry_date: '33333'},
         {'providerName':'xxx',cvali_date: '2222111',expiry_date: '33333111'},
         {'providerName':'xxx',cvali_date: '2222555',expiry_date: '33333555'}]*/
    }
  },
  mounted() {

  },
  methods: {
    editHandle(index,row){
      //跳到合约编辑的页面
      this.$router.push({
        path: '/basic-info/medicalManage/medicalManage',
        contractNo: row.contractNo
      })
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item ::v-deep label {
  font-weight: normal;
}


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}
</style>
