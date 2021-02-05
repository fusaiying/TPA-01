<!-- 清单确认 -->
<template>
  <div class="app-container">
    <div style="margin-bottom: 10px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;text-align: right; ">
      <el-button type="primary" size="mini" @click="handelConfim">确认清单</el-button>
      <el-button type="primary" size="mini" @click="handelCancel">取消</el-button>
    </div>

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

  </div>
</template>

<script>
import accountDetail from './../components/accountDetail'
import contractFileDetail from './../components/contractFileDetail'
import balanceFileDetail from './../components/balanceFileDetail'
import balanceDetail from './../components/balanceDetail'
import balanceInvoiceDetail from './../components/balanceInvoiceDetail'

import {updateBalance} from "@/api/claim/serviceBalance";

let dictss = [{dictType: 'sex'},{dictType: 'account_Types'},{dictType: 'balance_invoice_type2'},{dictType: 'invoice_auth'}]
export default {
  name: "balanceConfirm",
  components: {
    accountDetail,
    contractFileDetail,
    balanceFileDetail,
    balanceDetail,
    balanceInvoiceDetail
  },
  data() {
    return {
      // 查询参数
      queryParams: {
        //任务号
        taskNo: null,
        //发票类型
        balanceInvoiceType: null
      },
      //账号查询参数
      accountParams: {
        //是否展示
        isShow: true,
        //任务号
        supplierCode: null
      },
      //合同附件查询参数
      contractFileParams: {
        //是否展示
        isShow: true,
        //供应商
        supplierCode: null
      },
      //清单附件查询参数
      balanceFileParams: {
        //是否展示
        isShow: true,
        //加载类型
        isAdd: true,
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
        isAdd: true,
        //是否核销
        isWrite: false,
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
    this.queryParams.taskNo = this.$route.query.taskNo;
    //供应商
    this.accountParams.supplierCode = this.$route.query.supplierCode;
    this.contractFileParams.supplierCode = this.$route.query.supplierCode;
    //结算类型
    this.queryDetailParams.settleType = this.$route.query.settleType;
    //发票类型
    this.queryParams.balanceInvoiceType = this.$route.query.balanceInvoiceType;
    //初始化数据
    this.initData();
  },
  async mounted() {
    this.getDictsList(dictss).then(res => {
      this.dictList = res.data
    });
  },
  methods: {
    /** 初始化数据 */
    initData() {

    },
    /** 清单确认 */
    handelConfim() {
      //合约附件不能为空
      if (this.$refs.contractFileTable.fileList == 0) {
        this.$message.warning('此任务为先票后付，需先添加发票信息！');
        window.scrollTo({
          'top': document.getElementById("balanceFileDiv").offsetTop,
          'behavior': 'smooth'
        });
        return;
      }
      //先票后付
      if (this.queryParams.balanceInvoiceType == "01" && this.$refs.balanceInvoiceTable.balanceInvoiceList.length == 0) {
        this.$message.warning('此任务为先票后付，需先添加发票信息！');
        window.scrollTo({
          'top': document.getElementById("balanceInvoiceDiv").offsetTop,
          'behavior': 'smooth'
        });
        this.$refs.balanceInvoiceTable.activeNames = ['1'];
        return;
      }
      const params = {
        params: {type: "CONFIRM"},
        taskNo: this.queryParams.taskNo
      };
      updateBalance(params).then(res => {
        if (res != null && res.code === 200) {
          // 关闭本标签页
          this.$store.dispatch("tagsView/delView", this.$route);
          this.$router.push({
            path: '/implementation/service-balance/balance-start'
          });
        }
      }).catch(res => {
        this.msgError('清单确认失败！')
      });
    },
    /** 取消 */
    handelCancel() {
      // 关闭本标签页
      this.$store.dispatch("tagsView/delView", this.$route);
      this.$router.push({
        path: '/implementation/service-balance/balance-start'
      });
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

  .box-card {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
  }
</style>
