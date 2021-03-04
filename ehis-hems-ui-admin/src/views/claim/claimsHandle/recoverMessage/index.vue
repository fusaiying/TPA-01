<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="rptNo">
              <el-input v-model="searchForm.rptNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="searchForm.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊医院：" prop="hospitalCode">
              <el-select
                v-model="searchForm.hospitalCode"
                filterable
                remote
                clearable
                reserve-keyword
                placeholder="请选择医院"
                :remote-method="remoteMethod"
                class="item-width"
                size="mini">
                <el-option
                  v-for="(item, ind) in hospitalOptions"
                  :key="ind"
                  :label="item.chname1"
                  :value="item.providerCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input v-model="searchForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idNo">
              <el-input v-model="searchForm.idNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="结案日期：" prop="endCaseDate">
              <el-date-picker
                v-model="searchForm.endCaseDate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="白名单标记：" prop="whiteStatus">
              <el-select v-model="searchForm.whiteStatus" class="item-width" placeholder="请选择" clearable
                         @change="">
                <el-option v-for="option in white_list_labelOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyItemNo">
              <el-input v-model="searchForm.policyItemNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              searchHandle('search')
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>追讨工作池</span>
          <span style="float: right;">
            <el-button type="primary" size="mini"   @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="rptNo" label="报案号" show-overflow-tooltip><!--超链接，点击可查看“理赔详情”页面-->
            <template slot-scope="scope">
              <el-link style="font-size: 11px" type="primary" @click="claimRouter(scope.row)">{{scope.row.rptNo}}</el-link>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="source" label="交单来源" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(delivery_sourceOptions, scope.row.source)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="hospitalCode" label="就诊医院" show-overflow-tooltip/>
          <el-table-column align="center" prop="insuredName" label="被保人姓名" width="90" show-overflow-tooltip/>
          <el-table-column align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
          <el-table-column align="center" prop="treatmentEndDate" label="就诊日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="appntName" label="投保人" show-overflow-tooltip/>
          <el-table-column align="center" prop="contNo" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="debtAmount" label="追讨金额CNY" width="100" show-overflow-tooltip/>
          <el-table-column align="center" prop="colAmount" label="收款金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="residualAmount" label="剩余欠款CNY" width="100" show-overflow-tooltip/>
          <el-table-column align="center" prop="endCaseTime" label="结案日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="whiteStatus" label="白名单标记" width="90" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(white_list_labelOptions, scope.row.whiteStatus)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" :formatter="getRiskStatus" prop="colStatus" label="状态"
                           show-overflow-tooltip/>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="">下载</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle('workPool')"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>收款明细</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="detailData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="receiptNo" label="收据号" show-overflow-tooltip/>
          <el-table-column align="center" prop="insuredNo" label="被保人" show-overflow-tooltip/>
          <el-table-column align="center" prop="receiptAmount" label="收款金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="receiptCurrency" label="收款币种" width="90" show-overflow-tooltip/>
          <el-table-column align="center" prop="receiptDate" label="收款日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="remark" label="收款备注" show-overflow-tooltip/>
          <el-table-column align="center" prop="createBy" label="操作人" show-overflow-tooltip/>
          <el-table-column align="center" prop="residual" label="余额" show-overflow-tooltip/>
        </el-table>
        <pagination
          v-show="detailTotal>0"
          :total="detailTotal"
          :page.sync="detailNum"
          :limit.sync="detailSize"
          @pagination="searchHandle('detail')"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
  import moment from 'moment'
  import {initDebt, initReceipt} from '@/api/claim/recoverMessage'
  import {getListNew} from '@/api/insuranceRules/ruleDefin'
  let dictss = [{dictType: 'product_status'}, {dictType: 'approvalconclusion'}, {dictType: 'white_list_label'},
    {dictType: 'delivery_source'},{dictType: 'recover_status'},]
  export default {
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {
      return {
        isListExport:false,
        riskCodes: [],
        dialogFormVisible: false,
        updateBy: undefined,
        searchForm: {
          rptNo: undefined,
          policyNo: undefined,
          hospitalCode: undefined,
          insuredName: undefined,
          idNo: undefined,
          endCaseDate: [],
          policyItemNo: undefined,
          whiteStatus: undefined,
        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        loading: true,
        workPoolData: [],
        detailData: [],
        detailTotal: 0,
        detailNum: 1,
        detailSize: 10,
        isinit: 'Y',
        totalCount: 0,
        hospitalOptions: [],
        changeSerchData: {},
        product_statusOptions: [],
        approvalconclusionOptions: [],
        sysUserOptions: [],
        white_list_labelOptions: [],
        delivery_sourceOptions: [],
        recover_statusOptions: [],
      }
    },

    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate
      this.approvalconclusionOptions = this.dictList.find(item => {
        return item.dictType === 'approvalconclusion'
      }).dictDate
      this.white_list_labelOptions = this.dictList.find(item => {
        return item.dictType === 'white_list_label'
      }).dictDate
      this.delivery_sourceOptions = this.dictList.find(item => {
        return item.dictType === 'delivery_source'
      }).dictDate
      this.recover_statusOptions = this.dictList.find(item => {
        return item.dictType === 'recover_status'
      }).dictDate
      initDebt(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.workPoolData = res.rows
          this.totalCount = res.total
        }
      }).catch(res => {})
      initReceipt(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.detailData = res.rows
          this.detailTotal = res.total
        }
      }).catch(res => {})
      let data = {
        pageNum:1,
        pageSize:200
      }
      getListNew(data).then(res => {
        this.hospitalOptions = res.rows
      })
    },
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      searchHandle(flag) {
        let query = {
          pageNum:1,
          pageSize:10,
          rptNo: this.searchForm.rptNo,
          policyNo: this.searchForm.policyNo,
          hospitalCode: this.searchForm.hospitalCode,
          insuredName: this.searchForm.insuredName,
          idNo: this.searchForm.idNo,
          startDate: '',
          endDate:'',
          policyItemNo: this.searchForm.policyItemNo,
          whiteStatus: this.searchForm.whiteStatus,
        }
        if ( this.searchForm.endCaseDate!=null && this.searchForm.endCaseDate.loading>0){
          query.startDate=this.searchForm.endCaseDate[0]
          query.endDate=this.searchForm.endCaseDate[1]
        }
        if (flag==='workPool'){
          query.pageNum=this.queryParams.pageNum
          query.pageSize=this.queryParams.pageSize
          initDebt(query).then(res => {
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows
              this.totalCount = res.total
            }
          }).catch(res => {})
        }else if (flag==='detail'){
          query.pageNum=this.detailNum
          query.pageSize=this.detailSize
          initReceipt(query).then(res => {
            if (res != null && res.code === 200) {
              this.detailData = res.rows
              this.detailTotal = res.total
            }
          }).catch(res => {})
        }else{
          initDebt(query).then(res => {
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows
              this.totalCount = res.total
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到工作池数据！"
                )
              }
            }
          }).catch(res => {})
          initReceipt(query).then(res => {
            if (res != null && res.code === 200) {
              this.detailData = res.rows
              this.detailTotal = res.total
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到收款明细数据！"
                )
              }
            }
          }).catch(res => {})
        }
      },
      listExport(){
        let query = {
          rptNo: this.searchForm.rptNo,
          policyNo: this.searchForm.policyNo,
          hospitalCode: this.searchForm.hospitalCode,
          insuredName: this.searchForm.insuredName,
          idNo: this.searchForm.idNo,
          startDate: '',
          endDate:'',
          policyItemNo: this.searchForm.policyItemNo,
          whiteStatus: this.searchForm.whiteStatus,
        }
        if ( this.searchForm.endCaseDate!=null && this.searchForm.endCaseDate.loading>0){
          query.startDate=this.searchForm.endCaseDate[0]
          query.endDate=this.searchForm.endCaseDate[1]
        }
        initDebt(query).then(res => {
          if (res.rows.length>0){
            this.isListExport=true
            this.download('claimflow/debt/export'+'?idNo='+this.searchForm.idNo+'&rptNo='+this.searchForm.rptNo
              +'&policyNo='+this.searchForm.policyNo +'&hospitalCode='+this.searchForm.hospitalCode
              +'&insuredName='+this.searchForm.insuredName+'&startDate='+this.searchForm.startDate
              +'&endDate='+this.searchForm.endDate+'&policyItemNo='+this.searchForm.policyItemNo
              +'&whiteStatus='+this.searchForm.whiteStatus, {
              ...this.searchForm
            }, `FYX_${new Date().getTime()}.xlsx`)
          }else {
            return this.$message.warning(
              "没有查询到能导出的数据！"
            )
          }
        }).catch(res => {

        })
      },
      getRiskStatus(row) {
        return this.selectDictLabel(this.recover_statusOptions, row.colStatus)
      },
      remoteMethod(query) {
        if (query !== '' && query != null) {//调用特殊医院查询接口
          let data = {
            chname1: query,
            pageNum:1,
            pageSize:200
          }
          getListNew(data).then(res => {
            this.hospitalOptions = res.rows
          })
        }
      },
      claimRouter(row){
        let data = encodeURI(
          JSON.stringify({
            batchNo: row.batchNo,
            claimType: '',
            rptNo: row.rptNo,
            status:'show',
            node: 'calculateReview',
            styleFlag: 'list',
          })
        )
        this.$router.push({
          path: '/claims-handle/accept-process',
          query: {
            data
          }
        })
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
