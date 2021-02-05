<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>理赔案件列表（{{totalNum}}）</span>
        <el-button style="float: right; margin-top: 10px;" size="mini" @click="changeDialogVisable">返回
        </el-button>
        <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="exportData">清单导出
        </el-button>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" prop="batchNo" width="140" label="批次号" show-overflow-tooltip/>
        <el-table-column align="center" prop="rptNo" width="140" label="报案号" show-overflow-tooltip/>
        <el-table-column align="center" prop="insuredNo" width="140" label="客户号" show-overflow-tooltip/>
        <el-table-column align="center" prop="name" width="120" label="就诊人" show-overflow-tooltip/>
        <el-table-column align="center" prop="hospitalCode"  width="120" label="就诊医院" show-overflow-tooltip/>

        <el-table-column align="center" prop="treatmentStartDate" width="120" label="就诊日期" show-overflow-tooltip/>
        <el-table-column align="center" prop="billNo" width="120" label="账单号/发票号" show-overflow-tooltip/>
        <el-table-column align="center" prop="caseStatus"  width="120" label="案件状态" show-overflow-tooltip/>
        <el-table-column align="center" prop="billAmount" width="120" label="账单总金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="discountedAmount" width="120" label="折后金额" show-overflow-tooltip/>

        <el-table-column align="center" prop="payConclusion" width="120" label="赔付结论" show-overflow-tooltip/>
        <el-table-column align="center" prop="submitDate" width="120" label="交单日期" show-overflow-tooltip/>
        <el-table-column align="center" prop="endCaseTime" width="120" label="结案日期" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.endCaseTime|changeDate }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="source" width="120" label="交单来源" show-overflow-tooltip/>
        <el-table-column align="center" prop="payAmount" width="120" label="给付金额" show-overflow-tooltip/>

        <el-table-column align="center" prop="payDate" width="120" label="付款日期" show-overflow-tooltip/>
        <el-table-column align="center" prop="advanceAmount" width="120" label="结算金额CNY" show-overflow-tooltip/>
        <el-table-column align="center" prop="remark" width="120" label="备注" show-overflow-tooltip/>
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

  import moment from 'moment'

  import { getInfo } from '@/api/paymentFee/api'

  export default {
  props: {
    value: {
      type: Boolean,
      default: false
    },
    fixInfo:Object,
  },
  watch: {
    fixInfo: function (newValue) {
      this.fixInfoDetail = newValue;
      console.log("fixInfo ---------------------------")
      console.log(newValue);
      console.log("fixInfo ---------------------------")

    },
    value: function (newValue) {
      console.log(newValue)
      this.dialogVisable = newValue;
      if(this.dialogVisable) {
         this.initData();
      }
    },
  },
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  components: {
  },
  data() {
    return {
      loading : false,
      dialogVisable : false,
      tableData: [],
      pageInfo: {
        currentPage: 1,
        pageSize: 10,
        pageSizes:[5,10,20,30,50,100]
      },
      totalNum:0,
      ysOrNo:[],
      claimTypes:[],
      providerInfoSelects:[],
      fixInfoDetail:{},

    }
  },
  mounted(){
    // sys_yes_no
    this.getDicts("sys_yes_no").then(response => {
      this.ysOrNo = response.data;
    });
    // claimType
    this.getDicts("claimType").then(response => {
      this.claimTypes = response.data;
    });
  },
  created() {
   //  this.initData();
  },
  computed: {

  },
  methods: {
    getYesOrNoName(value){
      return this.selectDictLabel(this.ysOrNo, value)
    },
    initData(){
      this.loading = true;
      const params = {};
      params.pageNum =  this.pageInfo.currentPage;
      params.pageSize =  this.pageInfo.pageSize;

      params.settleTaskNo = this.fixInfoDetail.rowData.settleTaskNo;

      getInfo(params).then(res => {
          if (res.code == '200') {
            this.totalNum = res.total;
            this.tableData= res.rows;
          }
        }).finally(() => {
            this.loading = false;
      })
    },
    //导出
    exportData(){

    },
    //关闭对话框
    changeDialogVisable() {
      this.$emit('closeDetailDialog')
    },
  },


}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
