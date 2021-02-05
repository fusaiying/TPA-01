<!-- 审核 -->
<template>
  <div class="app-container">
    <el-card class="box-card">
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
      <balance-detail ref="balanceDetailTable" :queryParams="queryDetailParams" :dictList="dictList" />
      <!-- 结算明细列表 END-->

      <!-- 发票信息列表 START-->
      <balance-invoice-detail ref="balanceInvoiceTable" :params="invoiceParams" :dictList="dictList"/>
      <!-- 发票信息列表 END-->

      <!-- 审核 START-->
      <el-tabs v-model="activeName">
        <el-tab-pane :label="label.label01" name="01">
          <el-form ref="examForm" :model="examForm" :rules="examForm.rules" label-width="170px" size="mini">
            <el-row>
              <el-col :span="8">
                <el-form-item label="审核结论：" prop="examineConclusion">
                  <el-select v-model="examForm.examineConclusion" class="item-width" size="mini" placeholder="请选择" @change="examineConclusionChange">
                    <el-option v-for="item in examineConclusionOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="16">
                <el-form-item label="审核意见：" prop="examineOpinion">
                  <el-input :rows="3" v-model="examForm.examineOpinion" type="textarea" placeholder="请输入内容"
                            maxlength="255" show-word-limit/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row style="text-align: right">
              <el-button type="cyan" size="mini" @click="handelExam">审核完成</el-button>
              <el-button type="primary" size="mini" @click="handelCancel">关闭</el-button>
            </el-row>
          </el-form>
        </el-tab-pane>
        <el-tab-pane :label="label.label02" name="02">
          <balance-exam-log-detail ref="balanceExamLogDetail" :params="balanceExamlogParams" :dictList="dictList"/>
        </el-tab-pane>
      </el-tabs>
      <!-- 审核 END-->

    </el-card>
  </div>
</template>

<script>
import accountDetail from './../components/accountDetail'
import contractFileDetail from './../components/contractFileDetail'
import balanceFileDetail from './../components/balanceFileDetail'
import balanceDetail from './../components/balanceDetail'
import balanceInvoiceDetail from './../components/balanceInvoiceDetail'
import balanceExamLogDetail from './../components/balanceExamLogDetail'

import {updateBalance} from "@/api/claim/serviceBalance";

let dictss = [{dictType: 'sex'},{dictType: 'account_Types'},{dictType: 'balance_invoice_type2'},{dictType: 'invoice_auth'}]
export default {
  name: "balanceExam",
  components: {
    accountDetail,
    contractFileDetail,
    balanceFileDetail,
    balanceDetail,
    balanceInvoiceDetail,
    balanceExamLogDetail
  },
  data() {
    //同意校验规则
    const agreeRules = {
      examineConclusion: [{required: true, message: "审核结论不能为空！", trigger: "blur"}]
    };
    //不同意校验规则
    const disAgreeRules = {
      examineConclusion: [{required: true, message: "审核结论不能为空！", trigger: "blur"}],
      examineOpinion: [{required: true, message: "审核结论为不同意，审核意见不能为空！", trigger: "blur"}]
    };
    return {
      // 查询参数
      queryParams: {
        //任务号
        taskNo: null
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
      //tabs
      activeName: "01",
      //label
      label:{
        label01: "结算审核",
        label02: "日志"
      },
      //同意校验规则
      agreeRules: agreeRules,
      //不同意校验规则
      disAgreeRules: disAgreeRules,
      //结算审核
      examForm: {
        //审核校验
        rules: agreeRules,
        //审核结论
        examineConclusion: null,
        //审核意见
        examineOpinion: null
      },
      //审核结论
      examineConclusionOptions: [
        {dictValue: "04", dictLabel: "同意"},
        {dictValue: "03", dictLabel: "不同意"}
      ],
      //字典数组
      dictList: []
    };
  },
  created() {
    //任务号
    this.queryParams.taskNo = this.$route.query.taskNo;
    this.balanceFileParams.taskNo = this.$route.query.taskNo;
    this.queryDetailParams.taskNo = this.$route.query.taskNo;
    this.invoiceParams.taskNo = this.$route.query.taskNo;
    this.balanceExamlogParams.taskNo = this.$route.query.taskNo;
    //供应商
    this.accountParams.supplierCode = this.$route.query.supplierCode;
    this.contractFileParams.supplierCode = this.$route.query.supplierCode;
    //结算类型
    this.queryDetailParams.settleType = this.$route.query.settleType;
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
    /** 审核结论事件 */
    examineConclusionChange() {
      //同意
      if (this.examForm.examineConclusion == "04") {
        this.examForm.rules = this.agreeRules;
      }
      //不同意
      else {
        this.examForm.rules = this.disAgreeRules;
      }
    },
    /** 审核完成 */
    handelExam() {
      this.$refs.examForm.validate((valid) =>{
        if (valid){
          const params = {
            params: {type: "EXAMIN"},
            bussinessStatus: this.examForm.examineConclusion,
            taskNo: this.queryParams.taskNo,
            remark: this.examForm.examineOpinion
          };
          updateBalance(params).then(res => {
            if (res != null && res.code === 200) {
              // 关闭本标签页
              this.$store.dispatch("tagsView/delView", this.$route);
              this.$router.push({
                path: '/implementation/service-balance/balance-exam'
              });
            }
          }).catch(res => {
            this.msgError('清单确认失败！')
          });
        }
      });
    },
    /** 关闭 */
    handelCancel() {
      // 关闭本标签页
      this.$store.dispatch("tagsView/delView", this.$route);
      this.$router.push({
        path: '/implementation/service-balance/balance-exam'
      });
    }

  }
};
</script>

<style scoped>

</style>
