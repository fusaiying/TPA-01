<!-- 服务结算明细 -->
<template>
  <div class="app-container">
    <!-- 账号信息 START-->
    <account-detail ref="accountDetailTable" :params="accountParams" :dictList="dictList"/>
    <!-- 账号信息 END-->

    <!-- 合约附件信息 START-->
    <contract-file-detail ref="contractFileTable" :params="contractFileParams" :dictList="dictList"/>
    <!-- 合约附件信息 END-->

    <!-- 清单附件信息 START-->
    <balance-file-detail ref="balanceFileTable" :params="balanceFileParams" :dictList="dictList"/>
    <!-- 清单附件信息 END-->

    <!-- 结算明细列表 START-->
    <balance-detail ref="balanceDetailTable" :queryParams="queryDetailParams" :dictList="dictList"/>
    <!-- 结算明细列表 END-->

    <!-- 发票信息列表 START-->
    <balance-invoice-detail ref="balanceInvoiceTable" :params="invoiceParams" :dictList="dictList"/>
    <!-- 发票信息列表 END-->

    <!-- 结算审核日志 START -->
    <el-card class="box-card">
      <el-collapse v-model="activeNames">
        <el-collapse-item name="1" style="position: relative;">
          <template slot="title">
            <span style="font-size:16px;color:black">日志</span>
            <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
          </template>
          <balance-exam-log-detail ref="balanceExamLogDetail" :params="balanceExamlogParams" :dictList="dictList"/>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <!-- 结算审核日志 END -->
  </div>
</template>

<script>
import accountDetail from './../components/accountDetail'
import contractFileDetail from './../components/contractFileDetail'
import balanceFileDetail from './../components/balanceFileDetail'
import balanceDetail from './../components/balanceDetail'
import balanceInvoiceDetail from './../components/balanceInvoiceDetail'
import balanceExamLogDetail from './../components/balanceExamLogDetail'

let dictss = [{dictType: 'sex'},{dictType: 'account_Types'},{dictType: 'balance_invoice_type2'},{dictType: 'invoice_auth'}]
export default {
  components: {
    accountDetail,
    contractFileDetail,
    balanceFileDetail,
    balanceDetail,
    balanceInvoiceDetail,
    balanceExamLogDetail
  },
  data() {
    return {
      // 显示
      activeNames: ["1"],
      //结算方式变量
      settleTypeData: {
        //按case
        isCase: "01",
        //按人头
        isCont: "02",
        //按一口价
        isPrice: "03"
      },
      //账号查询参数
      accountParams: {
        //是否展示
        isShow: false,
        //任务号
        supplierCode: null
      },
      //合同附件查询参数
      contractFileParams: {
        //是否展示
        isShow: false,
        //供应商
        supplierCode: null
      },
      //清单附件查询参数
      balanceFileParams: {
        //是否展示
        isShow: false,
        //加载类型
        isAdd: false,
        //任务号
        taskNo: null
      },
      //结算明细查询参数
      queryDetailParams: {
        //是否展示
        isShow: true,
        //是否结算
        isSearche: false,
        // 状态
        status: "Y",
        // 页数
        pageNum: 1,
        // 分页数
        pageSize: 10,
        //任务号
        taskNo: null,
        //结算方式
        settleType: null
      },
      //发票信息查询参数
      invoiceParams: {
        //是否展示
        isShow: true,
        //加载类型
        isAdd: false,
        //是否核销
        isWrite: false,
        //任务号
        taskNo: null
      },
      //日志查询参数
      balanceExamlogParams: {
        //是否展示
        isShow: true,
        //任务号
        taskNo: null
      },
      //字典数组
      dictList: []
    };
  },
  created() {
    //任务号
    this.balanceFileParams.taskNo = this.$route.query.taskNo;
    this.queryDetailParams.taskNo = this.$route.query.taskNo;
    this.invoiceParams.taskNo = this.$route.query.taskNo;
    this.balanceExamlogParams.taskNo = this.$route.query.taskNo;
    //供应商
    this.accountParams.supplierCode = this.$route.query.supplierCode;
    this.contractFileParams.supplierCode = this.$route.query.supplierCode;
    //结算类型
    this.queryDetailParams.settleType = this.$route.query.settleType;
    //是否展示
    if (this.$route.query.bussinessStatus != "01") {
      this.accountParams.isShow = true;
      this.contractFileParams.isShow = true;
      this.balanceFileParams.isShow = true;
      this.invoiceParams.isShow = true;
    } else {
      this.accountParams.isShow = false;
      this.contractFileParams.isShow = false;
      this.balanceFileParams.isShow = false;
      this.invoiceParams.isShow = false;
    }
    this.initData();
  },
  async mounted() {
    this.getDictsList(dictss).then(res => {
      this.dictList = res.data
    });
  },
  methods: {
    initData (){

    }
  }
};
</script>

<style scoped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

</style>
