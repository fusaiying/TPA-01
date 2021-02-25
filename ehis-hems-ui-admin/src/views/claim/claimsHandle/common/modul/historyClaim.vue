<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>理赔案件列表（{{ totalNum }}）</span>
          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="changeDialogVisable">返回
          </el-button>
          <el-button style="float: right; margin-top: 10px;margin-right:15px" type="primary" size="mini" @click="exportData">清单导出
          </el-button>
        </div>
        <el-table
          :data="tableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}">
          <el-table-column prop="batchNo" label="批次号" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="rptNo" label="报案号" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="caseStatus" :formatter="getClaimStatusName" label="案件状态" align="center"  show-overflow-tooltip />
          <el-table-column prop="name" label="被保险人"  align="center" show-overflow-tooltip />
          <el-table-column prop="idNo" label="证件号码"  align="center" show-overflow-tooltip />
          <el-table-column prop="policyItemNo" label="分单号"  align="center" show-overflow-tooltip />
          <el-table-column prop="accDate" label="就诊日期"  align="center" show-overflow-tooltip />
          <el-table-column prop="payConclusion" :formatter="getConclusionName" label="赔付结论"  align="center" show-overflow-tooltip />
          <el-table-column prop="paymentAmount" label="给付金额"  align="center" show-overflow-tooltip />
          <el-table-column prop="operator" label="审核人"  align="center" show-overflow-tooltip />
          <el-table-column prop="investigation" :formatter="getInvestigationName" label="有无调查"  align="center" show-overflow-tooltip />
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalNum>0"
          :total="totalNum"
          :page.sync="pageInfo.currentPage"
          :limit.sync="pageInfo.pageSize"
          @pagination="initData"
        />
    </el-card>
  </el-dialog>
</template>

<script>
  import { claimInformation } from '@/api/handel/common/api'
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo:Object,
      insuredNo:String,
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue;
        if(this.dialogVisable) {
          this.initData();
        }
      },
      insuredNo:function (newValue) {
        this.paramInsuredNo = newValue;
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue;
        this.rptNo = this.fixInfoData.rptNo;
      },
    },
    data() {
      return {
        fixInfoData : '',
        rptNo :'',
        paramInsuredNo:'',
        dialogVisable: false,
        tableData: [],
        totalNum: 0,
        pageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },

        loading: false,
        claimStatusSelect:[],
        conclusionSelect:[],
      }
    },
    mounted(){
      //案件状态 claim_status
      this.getDicts("claim_status").then(response => {
        this.claimStatusSelect = response.data;
      });
      //赔付结论 conclusion
      this.getDicts("conclusion").then(response => {
        this.conclusionSelect = response.data;
      });
    },
    computed: {

    },
    created: function() {

    },
    methods: {
      initData(){
        if(this.paramInsuredNo == '') {
          return false;
        }
        this.loading = true;
        const params = {};
        params.pageNum = this.pageInfo.currentPage;
        params.pageSize = this.pageInfo.pageSize;
        params.insuredNo = this.paramInsuredNo;

        claimInformation(params).then(res => {
          console.log(res);
          if (res.code == '200') {
            this.totalNum = res.total;
            this.tableData = res.rows;
          }
          this.loading = false
        });
      },
      getClaimStatusName(row,col){
        return this.selectDictLabel(this.claimStatusSelect, row.caseStatus)
      },
      getConclusionName(row,col){
        return this.selectDictLabel(this.conclusionSelect, row.payConclusion)
      },
      exportData(){
        const params = {};
        params.rptNo = this.rptNo;
        this.download('claimflow/case/exportClaimInformation', params, `理赔案件_${new Date().getTime()}.xlsx`);
      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeHistoryClaimDialog')
      },
      getInvestigationName(row, col){
        return row.investigation == '01' ? '是' : '否'  ;
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  ::v-deep.el-table .warning-row {
    background: oldlace;
  }

</style>

