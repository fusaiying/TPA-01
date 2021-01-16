<template>
    <div>
      <div v-if="status === '0'">
        <el-table
          :data="claimInformationData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="claimno" width="140" label="赔案号" show-overflow-tooltip>
            <template slot-scope="scope">
            <el-button size="small" type="text" @click="toLinkDetail(scope.row)">{{ scope.row.claimno }}</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="idcardno" label="身份证号" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="riskname" label="险种名称" width="140"> </el-table-column>
          <el-table-column align="center" prop="dutyname" label="责任名称" width="140">
            <template slot-scope="scope">
            <span>{{ scope.row.dutyname}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="payableamount" label="赔付金额" width="140">
            <template slot-scope="scope">
            <span>{{ scope.row.payableamount|numFilter }}</span>
            </template>
          </el-table-column>
            <el-table-column align="center" prop="conclusion" :formatter="getConclusion"  label="赔付结论" show-overflow-tooltip/></el-table-column>
            <el-table-column align="center" prop="status" :formatter="getQueueclaimstatus" label="理赔状态" show-overflow-tooltip/></el-table-column>
        </el-table>
        <el-pagination
          :total="claimInformationTotalCount"
          :current-page="claimInformationPageInfo.page"
          :page-size="claimInformationPageInfo.pageSize"
          :page-sizes="[5, 10, 20, 30,50,100]"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="claimInformationSizeChange"
          @current-change="claimInformationPageChange"/>
      </div>
      <div v-if="status === '1'">
        <el-table
          :data="AdvancePayData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="name" width="140" label="被保人姓名" show-overflow-tooltip>
          </el-table-column>
          <el-table-column align="center" prop="idcardno" label="身份证号" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="visitdate" label="就诊日期" width="140" show-overflow-tooltip>
          </el-table-column>
          <el-table-column align="center" prop="hospitalcode" label="就诊医院" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="maindiagnosis" label="主要诊断" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="advancepayment" label="垫付金额" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="status" label="垫付状态" show-overflow-tooltip/></el-table-column>
        </el-table>
        <el-pagination
          :total="polTotalCount"
          :current-page="polPageInfo.page"
          :page-size="polPageInfo.pageSize"
          :page-sizes="[5, 10, 20, 30,50,100]"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="polSizeChange"
          @current-change="polPageChange"/>
      </div>
      <div v-if="status === '2'">
        <el-table
          :data="InvestigateData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="taskno" width="140" label="调查号" show-overflow-tooltip>
            <!-- <template slot-scope="scope">
            <el-button size="small" type="text">{{ scope.row.taskno }}</el-button>
            </template> -->
          </el-table-column>
          <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="source" label="调查来源" :formatter="getSurveySource" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="surveytype" label="调查类型" :formatter="getSurvetypes" width="140"> </el-table-column>
          <el-table-column align="center" prop="initiatereasons" label="调查原因" :formatter="displaySurveyReason" width="140">
            <!-- <template slot-scope="scope">
            <span>{{ scope.eventdate}}</span>
            </template> -->
          </el-table-column>
          <el-table-column align="center" prop="tasktype" label="任务类型" :formatter="getTasktype" width="140">
            <!-- <template slot-scope="scope">
            <span>{{ scope.applicationdate}}</span>
            </template> -->
          </el-table-column>
          <el-table-column align="center" prop="currentstatus" label="调查状态" :formatter="getInvestigateStatus" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="initiatedate" label="提调日期" show-overflow-tooltip/></el-table-column>
          <el-table-column align="center" prop="closedate" label="结案日期" show-overflow-tooltip/></el-table-column>
        </el-table>
        <el-pagination
          :total="investigateDataTotalCount"
          :current-page="investigateDataInfo.page"
          :page-size="investigateDataInfo.pageSize"
          :page-sizes="[5, 10, 20, 30,50,100]"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="investigateDataSizeChange"
          @current-change="investigateDataPageChange"/>
      </div>
    </div>
</template>
<script>
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['conclusion','queue_claim_status','survey_source','surve_types','survereason','task_type','investigate_status','initiate_reasons','second_initiate_reasons'],
  filters: {
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '' && value !== null) {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  props: {
    AdvancePayData: {
      type: Array,
      default: function() {
        return []
      }
    },
    InvestigateData: {
      type: Array,
      default: function() {
        return []
      }
    },
    claimInformationData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: {
      type: String,
      default: function() {
        return ''
      }
    },
    investigateDataTotalCount: {
      type: Number,
      default: function() {
        return  12
      }
    },
    claimInformationTotalCount: {
      type: Number,
      default: function() {
        return  12
      }
    }
  },
  data() {
    return {
      polPageInfo: {
        page: 1,
        pageSize: 10
      },
      polTotalCount: 0,
      cusPageInfo: {
        page: 1,
        pageSize: 10
      },
      investigateDataInfo: {
        page: 1,
        pageSize: 10
      },
      claimInformationPageInfo: {
        page: 1,
        pageSize: 10
      }
    }
  },
  methods: {
    getQueueclaimstatus(row,col) {
      return this.dict.label.queue_claim_status[row.status]
    },
    polSizeChange(val) {
      this.polPageInfo.pageSize = val
      this.$emit('getList',this.polPageInfo.pageSize,this.polPageInfo.page,'1')
    },
    polPageChange(val) {
      this.cusPageInfo.page = val
      this.$emit('getList',this.polPageInfo.pageSize,this.polPageInfo.page,'1')
    },
    investigateDataSizeChange(val) {
      this.investigateDataInfo.pageSize = val
      this.$emit('getList',this.investigateDataInfo.pageSize,this.investigateDataInfo.page,'2')
    },
    investigateDataPageChange(val) {
      this.investigateDataInfo.page = val
      this.$emit('getList',this.investigateDataInfo.pageSize,this.investigateDataInfo.page,'2')
    },
    claimInformationSizeChange(val) {
      this.claimInformationPageInfo.pageSize = val
      this.$emit('getList',this.claimInformationPageInfo.pageSize,this.claimInformationPageInfo.page,'0')
    },
    claimInformationPageChange(val) {
      this.claimInformationPageInfo.page = val
      this.$emit('getList',this.claimInformationPageInfo.pageSize,this.claimInformationPageInfo.page,'0')
    },
    getConclusion(row,col){
      return this.dict.label.conclusion[row.conclusion]
    },
    // 调查来源
    getSurveySource(row, col) {
      if (row.source != null) {
        if (row.source.length > 2) {
          const reasonArray = row.source.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              reason = this.dict.label.survey_source[reasonArray[i]]
            } else {
              reason = reason + ',' + this.dict.label.survey_source[reasonArray[i]]
            }
          }
          return reason
        } else {
          return this.dict.label.survey_source[row.source]
        }
      }
    },
    // 调查类型
    getSurvetypes(row, col) {
      if (row.surveytype != null) {
        if (row.surveytype.length > 2) {
          const reasonArray = row.surveytype.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              reason = this.dict.label.surve_types[reasonArray[i]]
            } else {
              reason = reason + ',' + this.dict.label.surve_types[reasonArray[i]]
            }
          }
          return reason
        } else {
          return this.dict.label.surve_types[row.surveytype]
        }
      }
    },
    displaySurveyReason(row, col) {
      const dictName = 'second_initiate_reasons'
      const reasonArr = row.initiatereasons.split(',')
      if (reasonArr.length <= 1) {
          return this.dict.label.initiate_reasons[row.initiatereasons]
      } else {
          let reason1 = reasonArr[0] ? this.dict.label.initiate_reasons[reasonArr[0]] : ''
          let reason2 = reasonArr[1] ? '/' + this.dict.label.second_initiate_reasons[reasonArr[1]] : ''
          return reason1 + reason2
      }
    },
    // 调查原因
    getSurvereason(row, col) {
      if (row.initiatereasons != null) {
        if (row.initiatereasons.length > 2) {
          const reasonArray = row.initiatereasons.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              reason = this.dict.label.survereason[reasonArray[i]]
            } else {
              reason = reason + ',' + this.dict.label.survereason[reasonArray[i]]
            }
          }
          return reason
        } else {
          return this.dict.label.survereason[row.initiatereasons]
        }
      }
    },
    // 任务类型
    getTasktype(row,col) {
      return this.dict.label.task_type[row.tasktype]
    },
    // 调查状态
    getInvestigateStatus(row,col) {
      return this.dict.label.investigate_status[row.status]
    },
    toLinkDetail(row) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status:'show',
          node: 'review'
        })
      )
      const newpage = this.$router.resolve({
        name: 'casedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path :'/claims-handle/com-handle',
      //   query:{
      //     data
      //   }
      // })
    }
  }
}
</script>
<style scoped>

</style>