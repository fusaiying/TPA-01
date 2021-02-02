
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
        <span style="font-size:16px;color:black">发票信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <el-table
          ref="table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="invoiceInfo"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
<!--          <el-table-column type="expand">-->
<!--            <template slot-scope="props">-->
<!--              <el-table-->
<!--                ref="table"-->
<!--                :header-cell-style="{color:"black",background:'#f8f8ff'}"-->
<!--                :data="props.row.subList"-->
<!--                size="small"-->
<!--                highlight-current-row-->
<!--                tooltip-effect="dark"-->
<!--                style="width: 100%;">-->
<!--                <el-table-column align="center" prop="riskname" label="原型未列出展示字段" show-overflow-tooltip/>-->
<!--                <el-table-column align="center" prop="riskname" label="文档没找到展示字段" show-overflow-tooltip/>-->
<!--              </el-table>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column align="center" type="index" label="序号"/>
          <el-table-column align="center" prop="invoiceNo" label="发票号" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.pastClaimNo === ''">{{scope.row.invoiceNo}}</span>
              <el-tooltip v-else class="item" effect="dark" :content="'重复理赔，既往赔案号' + scope.row.pastClaimNo" placement="top-start">
                <span style="color: red;">
                  <span>{{scope.row.invoiceNo}}</span>
                  <span class="el-icon-warning"></span>
                </span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column align="center" :formatter="getCNType" prop="invoiceType" label="发票类型" show-overflow-tooltip/>
          <el-table-column align="center" prop="hospitalName" label="医院名称" width="160" show-overflow-tooltip/>
          <el-table-column align="center" prop="range" label="就诊区间" show-overflow-tooltip/>
          <el-table-column align="center" prop="amount" label="总金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="overallPayment" label="统筹支付" show-overflow-tooltip />
          <el-table-column align="center" prop="thirdPartyPayment" label="第三方支付" show-overflow-tooltip/>
          <el-table-column align="center" prop="payByCategory" label="比例自付" show-overflow-tooltip/>
          <el-table-column align="center" prop="selfPayment" label="自费金额" show-overflow-tooltip/>
          <el-table-column label="操作" width="120" align="center">
            <template slot-scope="scope">
              <el-button style="margin-bottom: 10px;" :disabled="status == 'show'" type="text" size="mini" @click="editInvoice(scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import {encrypt} from "@/utils/rsaEncrypt"
import Bus from "../../bus/bus";
export default {
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: String,
    invoiceInfo: {
      type: Array,
      default: {
        return: []
      }
    },
    insuredInfo: Object
  },
  //dicts: ['invoicetype'],
  data() {
    return {
      activeNames: ['1'],
      invoicetypeOptions: [],
    }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.invoicetypeOptions = this.dictList.find(item => {
        return item.dictType == 'invoicetype'
      }).dictDate
    }
  },
  methods: {
    getCNType(row,rol) {
      //return this.dict.label.invoicetype[row.invoiceType]
      return this.selectDictLabel(this.invoicetypeOptions, row.invoiceType)
    },
    editInvoice(data) {
      // let query =  {
      //   node: 'input',
      //   claimNo: encrypt(this.claimno),
      //   status: 'edit',
      //   invoiceInfo: data,
      //   insuredInfo: this.insuredInfo
      // }
      const newpage = this.$router.resolve({
        name: 'invoiceHandle',
        params:{},
        query:{
          node: 'input',
          claimNo: encrypt(this.claimno),
          status: 'edit',
          invoiceInfo: JSON.stringify(data),
          insuredInfo: JSON.stringify(this.insuredInfo)
        }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/classificat',
      //   query: {
      //     node: 'input',
      //     claimNo: encrypt(this.claimno),
      //     status: 'edit',
      //     invoiceInfo: data,
      //     insuredInfo: this.insuredInfo
      //   }
      // })
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
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.el-collapse ::v-deep .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
