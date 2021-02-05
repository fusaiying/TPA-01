<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>案件信息列表</span>
          <span style="float: right;">
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="rptNo" label="报案号" show-overflow-tooltip/>
          <el-table-column align="center" prop="receiveDate" label="就诊日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="sendBy" label="姓名"  show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="账单金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="折后金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="先期给付" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="自付额" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="理赔金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="追讨金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="汇率" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="外币支付金额" width="110" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="借款金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="案件状态" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(claim_statusOptions, scope.row.claimType)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="companyName" label="支付状态" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(case_pay_statusOptions, scope.row.claimType)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="backspace">回退</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-form ref="caseForm" style="padding-bottom: 30px;margin-top:60px;" label-width="150px"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">支付币种：</span><span class="info_span">{{  }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">支付总金额：</span><span class="info_span">{{  }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">理赔总金额：</span><span class="info_span">{{  }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='public'" :span="8">
              <span class="info_span_col to_right">开户行：</span><span class="info_span">{{  }}</span>
            </el-col>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <span class="info_span_col to_right">外币支付总金额：</span><span class="info_span">{{  }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">账户名：</span><span class="info_span">{{ }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">账户号：</span><span class="info_span">{{  }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col v-if="querys.status==='publicForeign'" :span="8">
              <span class="info_span_col to_right">开户行：</span><span class="info_span">{{  }}</span>
            </el-col>
          </el-row>

          <div style="float: right;margin-top: 50px;margin-bottom: 10px">
            <el-button v-if="querys.status==='publicForeign'" type="primary" size="mini" @click="">保存</el-button>
            <el-button type="primary" size="mini" @click="confirmPay">确认支付</el-button>
            <el-button type="primary" size="mini" @click="caseBorrow">案件借款</el-button>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {startPay,confirmPayment,rollback,borrowingCase} from '@/api/claim/corporatePay'
  let dictss = [{dictType: 'case_pay_status'},{dictType: 'claim_status'},]
  export default {
    data() {
      return {
        querys:{},
        tableData:[],
        isListExport: false,
        dictList: [],
        claim_statusOptions: [],
        case_pay_statusOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.case_pay_statusOptions = this.dictList.find(item => {
        return item.dictType === 'case_pay_status'
      }).dictDate
      this.claim_statusOptions = this.dictList.find(item => {
        return item.dictType === 'claim_status'
      }).dictDate
      if (this.$route.query) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        if (this.querys.status==='public'){//对公支付
          startPay(this.querys.batchNo).then(res=>{

          })
        }else if (this.querys.status==='publicForeign'){//对公外币支付

        }
      }
    },
    methods: {
      search() {

      },
      confirmPay(){//确认支付
        if (this.querys.status==='public'){
          confirmPayment().then(res=>{

          })
        }else if (this.querys.status==='publicForeign'){

        }
      },
      backspace(){//回退
        if (this.querys.status==='public'){
          rollback().then(res=>{

          })
        }else if (this.querys.status==='publicForeign'){

        }
      },
      caseBorrow(){//案件借款
        if (this.querys.status==='public'){
          borrowingCase().then(res=>{

          })
        }else if (this.querys.status==='publicForeign'){

        }
      },
      listExport() {

      },
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
  ::v-deep.info_span_col {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #606266;
    line-height: 40px;
    padding: 0 12px 0 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }
  ::v-deep.to_right {
    width: 130px;
    text-align: right;
  }
</style>
