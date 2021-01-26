<template>
  <div class="app-container">
      <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>理赔综合查询</span>
        </div>
        <el-form ref="form" :model="form" style="padding-top: 30px" label-width="130px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label=" 批次号：" prop="batchNo">
                <el-input v-model="form.batchNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label=" 报案号：" prop="rptNo">
                <el-input v-model="form.rptNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label=" 被保险人：" prop="name">
                <el-input v-model="form.name" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>

            <el-col :span="8">
              <el-form-item label="证件号码：" prop="idNo" >
                <el-input v-model="form.idNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="分单号：" prop="policyItemNo" >
                <el-input v-model="form.policyItemNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>
          </el-row>

          <div style="text-align: right; margin-right: 10px;">
            <el-row>
              <el-form-item class="but1">
                <el-button size="mini" type="success" icon="el-icon-search" @click="searchHandel">查 询</el-button>
                <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('form')">重 置</el-button>
              </el-form-item>
            </el-row>
          </div>
        </el-form>

        <el-divider/>
        <div>
          <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>理赔案件列表（{{totalNum}}）</span>
            <el-button  style="float: right; margin-top: 10px;" type="primary" size="mini" @click="exportData">清单导出
            </el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}">
            <el-table-column prop="batchNo" label="批次号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="rptNo" label="报案号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="caseStatus" :formatter="getClaimStatusName" label="案件状态" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="name" label="被保险人" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="idNo" label="证件号码"   width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="policyItemNo" label="分单号"  align="center" show-overflow-tooltip />
            <el-table-column prop="accDate" label="就诊日期" align="center" show-overflow-tooltip />
            <el-table-column prop="payConclusion" :formatter="getConclusionName" label="赔付结论" align="center" show-overflow-tooltip />
            <el-table-column prop="paymentAmount" label="给付金额"  align="center" show-overflow-tooltip />
            <el-table-column prop="updateBy" label="审核人"  align="center" show-overflow-tooltip />
            <el-table-column prop="investigation" label="有无调查"  align="center" show-overflow-tooltip />
          </el-table>
          <!--分页组件-->
          <pagination
             v-show="totalNum>0"
            :total="totalNum"
            :page.sync="pageInfo.currentPage"
            :limit.sync="pageInfo.pageSize"
            @pagination="initData"
          />
        </div>

      </el-card>

  </div>
</template>

<script>

  import { claimInformation } from '@/api/handel/common/api'

    export default {
      components: {

      },
      data() {
        return {
            form: {
              batchNo: '',
              rptNo: '',
              name: '',
              idNo: '',
              policyItemNo:'',
            },
            tableData: [],
            totalNum: 0,
            idKey: 'rptNo', // 标识列表数据中每一行的唯一键的名称
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
        this.initData();
      },
      methods: {
        /*getCaseStatusName(row,col){
          return this.selectDictLabel(this.caseStatusSelect, row.caseStatus)
        },*/
        initData(){
          this.getTableData();
        },
        //重置
        reset(form) {
            this.$refs[form].resetFields()
        },
        //查询
        getTableData () {

          this.loading = true;
          const params = {};
            params.pageNum = this.pageInfo.currentPage;
            params.pageSize = this.pageInfo.pageSize;
            params.batchNo = this.form.batchNo ;
            params.rptNo = this.form.rptNo ;
            params.name = this.form.name ;
            params.idNo = this.form.idNo ;
            params.policyItemNo = this.form.policyItemNo ;

          claimInformation(params).then(res => {
            if (res.code == '200') {
              this.totalNum = res.total;
              this.tableData = res.rows;
            }
            this.loading = false
          });
        },
        searchHandel(){
          this.pageInfo.currentPage = 1;
          this.pageInfo.pageSize = 10;
          this.getTableData();
        },
        getClaimStatusName(row,col){
          return this.selectDictLabel(this.claimStatusSelect, row.caseStatus)
        },
        getConclusionName(row,col){
          return this.selectDictLabel(this.conclusionSelect, row.payConclusion)
        },
        exportData() {
          const params = {};
          params.batchNo = this.form.batchNo ;
          params.rptNo = this.form.rptNo ;
          params.name = this.form.name ;
          params.idNo = this.form.idNo ;
          params.policyItemNo = this.form.policyItemNo ;
          this.download('system/case/exportClaimInformation', params, `FYX_${new Date().getTime()}.xlsx`);
        },
      }
    }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>

