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
          <span>产品保障信息</span>
          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="changeDialogVisable">返回
          </el-button>
        </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        @expand-change="getMinData"
        v-loading="loading"
        style="width: 100%;">
        <el-table-column  type="expand">
          <template slot-scope="scope">
            <el-table :data="scope.row.minData"
                      :header-cell-style="{color:'black',background:'#f8f8ff'}"
                      highlight-current-row
                      size="small"
                      v-loading="minLoading"
                      tooltip-effect="dark"
                      style="width: 100%;">
              <el-table-column prop="rptNo" label="保单号" align="center"/>
              <el-table-column prop="name" label="保单号" align="center"/>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="batchNo" min-width="120" label="产品保障明细" show-overflow-tooltip/>
        <el-table-column align="center" min-width="200" prop="invoiceBoxNo" label="规则信息" show-overflow-tooltip/>
        <el-table-column align="center" prop="organcode"  min-width="120" label="有效期内至今花费" show-overflow-tooltip/>
        <el-table-column align="center" prop="remark" min-width="110" label="保险年度余额" show-overflow-tooltip/>
        <el-table-column align="center" prop="createTime" min-width="110" label="规则描述" show-overflow-tooltip/>
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
import {childData, invoiceData} from "@/api/invoice/api";
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
        minLoading:false,
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
        this.loading = true;
        const params = {};
        params.pageNum =  this.pageInfo.currentPage;
        params.pageSize =  this.pageInfo.pageSize;
        params.rptNo = this.formSearch.rptNo;
        params.batchNo = this.formSearch.batchNo;
        params.hospitalCode = this.formSearch.hospitalCode;
        params.invoiceBoxNo = this.formSearch.invoiceBoxNo;

        if(!this.searchBtn) {
          params.billrecevieflag = "02";
        }

        invoiceData(params).then(res => {
          if (res.code == '200') {
            this.totalNum = res.total;
            let _data = res.rows;
            if (_data.length !== 0) {
              _data.forEach(item => {
               // item.editing = false;
                // item.minData = [item]
              })
            }
         //   this.tableData= _data;
          }
        }).finally(() => {
          this.loading = false;
        })
      },

      getMinData(row, expandedRows) {
        //判断只有展开是做请求
        let query = {
          batchNo: row.batchNo
        }
        this.minLoading = true;
        if (expandedRows.length > 0) {
          childData(query).then(res => {
            if (res != null && res.code === 200) {
              this.tableData.forEach((temp, index) => {
                if (temp.batchNo === row.batchNo) {
                  if(res.rows.length > 0) {
                    for (let i=0; i<res.rows.length; i++) {
                      if(res.rows[i].caseStatus != '98') {
                        res.rows[i].print = '01';
                        break;
                      }
                    }
                  }
                  this.tableData[index].minData = res.rows
                }
              })
            }
          }).finally(() => {
            this.minLoading = false
          })
        }
      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeGuaranteeDialog')
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

