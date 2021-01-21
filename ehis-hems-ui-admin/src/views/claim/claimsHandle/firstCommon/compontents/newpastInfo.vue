
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1">
        <template slot="title">
          <span style="font-size:16px;color:black">既往信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
        </template>
        <el-tabs v-model="activeName">
          <el-tab-pane :label="`既往赔案(${pastInfo.claim !==null && pastInfo.claim.length? pastInfo.claim.length:0})`" name="01">
            <el-table
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="pastInfo.claim"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">

              <el-table-column align="center" label="序号" type="index"/>
              <el-table-column align="center" prop="claimno" min-width="140" label="赔案号" show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-button size="mini" type="text" @click="editHandle(scope.row,'show')">{{ scope.row.claimno }}</el-button>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
              <el-table-column align="center"  prop="idcardno" label="证件号码" show-overflow-tooltip/>
              <el-table-column align="center" prop="coveragename" label="险种名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="getdutyname" label="责任名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="visitdate" label="入院时间" show-overflow-tooltip/>
              <el-table-column align="center" prop="closecasedate" label="结案日期" show-overflow-tooltip/>
              <el-table-column align="center" prop="payableamount" label="赔付金额">
                <template slot-scope="scope">
                  <span>{{scope.row.payableamount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="amount" :formatter="delaramount" label="发票金额" >
                <template slot-scope="scope">
                  <span>{{scope.row.amount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="conclusion" :formatter="getConclusion" label="赔付结论" show-overflow-tooltip/>
              <el-table-column align="center" prop="status" label="理赔状态" :formatter="getQueueClaimStatus" show-overflow-tooltip/>
            </el-table>
          </el-tab-pane>
          <el-tab-pane  name="02" :label="`既往调查(${pastInfo.survey !==null && pastInfo.survey.length? pastInfo.survey.length:0})`">
            <el-table
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="pastInfo.survey"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">

              <el-table-column align="center" prop="taskno" min-width="120" label="调查号">
                <template slot-scope="scope">
                  <span v-if="scope.row.claimno == claimno" style="color:red">{{scope.row.taskno}}</span>
                  <span v-else>{{scope.row.taskno}}</span>
                  <el-tooltip class="item" effect="dark" content="本次关联调查" placement="top-start">
                    <span v-if="scope.row.claimno == claimno" style="margin-left:5px">
                      <i class="el-icon-warning" v-if="scope.row.claimno == claimno" style="color:red"></i>
                    </span>
                  </el-tooltip>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
              <el-table-column align="center" prop="idcardno" label="证件号码" show-overflow-tooltip/>
              <el-table-column align="center" prop="source" :formatter="getSurveySource" label="调查来源" show-overflow-tooltip/>
              <el-table-column align="center" prop="surveytype" :formatter="getSurvetypes" label="调查类型" show-overflow-tooltip/>
              <el-table-column align="center" prop="initiatereasons" label="调查原因" :formatter="displaySurveyReason" show-overflow-tooltip/>
              <el-table-column align="center" prop="tasktype" :formatter="getTasktype" label="性质" show-overflow-tooltip/>
              <el-table-column align="center" prop="status" :formatter="getInvestigateStatus" label="调查状态" show-overflow-tooltip/>
              <el-table-column align="center" prop="initiatedate" label="提调日期" show-overflow-tooltip/>
              <el-table-column align="center" prop="closedate" label="调查结案日期" show-overflow-tooltip/>
            </el-table>
          </el-tab-pane>
          <el-tab-pane  :label="`既往垫付(${pastInfo.advance != null && pastInfo.advance.length? pastInfo.advance.length:0})`" name="03">
            <el-table
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="pastInfo.advance"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">

              <el-table-column align="center" prop="claimno" label="赔案号" show-overflow-tooltip/>
              <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
              <el-table-column align="center" prop="idcardno" label="证件号码" show-overflow-tooltip/>
              <el-table-column align="center" prop="name" label="就诊日期" show-overflow-tooltip/>
              <el-table-column align="center" prop="type" label="就诊医院" show-overflow-tooltip/>
              <el-table-column align="center" prop="type" label="主要诊断" show-overflow-tooltip/>
              <el-table-column align="center" prop="type" :formatter="delarNum" label="垫付金额">
                <template slot-scope="scope">
                  <span>{{scope.row.payableamount|numFilter}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="type" label="垫付状态" show-overflow-tooltip/>
            </el-table>
          </el-tab-pane>
          <el-tab-pane :label="`既往协谈(${pastInfo.negotiation != null && pastInfo.negotiation.length? pastInfo.negotiation.length:0})`"  name="04">
            <el-table
              ref="table"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="pastInfo.negotiation"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <!-- <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-width="120px" label-position="right" >
                    <el-col :span="8">
                      <el-form-item label="具体内容待定 ：">
                        <span>{{ props.row.xxx }}111</span>
                      </el-form-item>
                    </el-col>
                  </el-form>
                </template>
              </el-table-column> -->
              <el-table-column align="center" prop="orderno" label="工单号" show-overflow-tooltip/>
              <el-table-column align="center" prop="claimno" min-width="140" label="赔案号" show-overflow-tooltip/>
              <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
              <el-table-column align="center" prop="idcardno" label="证件号码" show-overflow-tooltip/>
              <el-table-column align="center" prop="negotiationtype" :formatter="getnegotiationType" label="协谈类型" show-overflow-tooltip/>
              <el-table-column align="center" prop="negotiationdescription" label="协谈描述" show-overflow-tooltip/>
              <el-table-column align="center" prop="negotiationdate" label="完成日期" show-overflow-tooltip/>
              <el-table-column align="center" prop="status" :formatter="getNegotiationStatus" label="协谈状态" show-overflow-tooltip/>
              <el-table-column align="center" prop="negotiationresults" label="协谈结果" show-overflow-tooltip/>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  //dicts: ['conclusion','queue_claim_status','apply_type','case_type','incidenttype','relationtoinsured','negotiation_type','negotiation_status','surve_types','task_type','survereason','investigate_status','survey_source','initiate_reasons','second_initiate_reasons','casecharacter'],
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: String,
    pastInfo: {
      type: Object,
      default: () => {
        return {
          negotiation: [],
          survey: [],
          claim: [],
          advance: []
        }
      }
    }
  },
  filters: {
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '') {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  data() {
    return {
      activeNames: ['1'],
      activeName: '01',
      tableData: [{
        date: '2019-10-10至2020-10-09',
        detailInfo: [
          { code: 'DWQE21345' }
        ]
      }],
      pastClaims: [{ name: '小米', type: '123' }],
      previousAdvances: [{ name: '小米', type: '123' }],
      previousInvestigat: [{ name: '小米', type: '123' }],
      previousNegotiat: [{ name: '小米', type: '123' }],
      tabsDate: [
        { label: '既往赔案', name: '01' },
        { label: '既往垫付', name: '02' },
        { label: '既往调查', name: '03' },
        { label: '既往协谈', name: '04' }
      ],
      conclusionOptions: [],
      queue_claim_statusOptions: [],
      apply_typeOptions: [],
      case_typeOptions: [],
      incidenttypeOptions: [],
      relationtoinsuredOptions: [],
      negotiation_typeOptions: [],
      negotiation_statusOptions: [],
      surve_typesOptions: [],
      task_typeOptions: [],
      survereasonOptions: [],
      investigate_statusOptions: [],
      survey_sourceOptions: [],
      initiate_reasonsOptions: [],
      second_initiate_reasonsOptions: [],
      casecharacterOptions: [],
    }
  },
  mounted() {
    if (this.dictList != null && this.dictList.length != 0) {
      this.conclusionOptions = this.dictList.find(item => {
        return item.dictType == 'conclusion'
      }).dictDate
      this.queue_claim_statusOptions = this.dictList.find(item => {
        return item.dictType == 'queue_claim_status'
      }).dictDate
      this.apply_typeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_type'
      }).dictDate
      this.case_typeOptions = this.dictList.find(item => {
        return item.dictType == 'case_type'
      }).dictDate
      this.incidenttypeOptions = this.dictList.find(item => {
        return item.dictType == 'incidenttype'
      }).dictDate
      this.relationtoinsuredOptions = this.dictList.find(item => {
        return item.dictType == 'relationtoinsured'
      }).dictDate
      this.negotiation_typeOptions = this.dictList.find(item => {
        return item.dictType == 'negotiation_type'
      }).dictDate
      this.negotiation_statusOptions = this.dictList.find(item => {
        return item.dictType == 'negotiation_status'
      }).dictDate
      this.surve_typesOptions = this.dictList.find(item => {
        return item.dictType == 'surve_types'
      }).dictDate
      this.task_typeOptions = this.dictList.find(item => {
        return item.dictType == 'task_type'
      }).dictDate
      this.survereasonOptions = this.dictList.find(item => {
        return item.dictType == 'survereason'
      }).dictDate
      this.investigate_statusOptions = this.dictList.find(item => {
        return item.dictType == 'investigate_status'
      }).dictDate
      this.survey_sourceOptions = this.dictList.find(item => {
        return item.dictType == 'survey_source'
      }).dictDate
      this.initiate_reasonsOptions = this.dictList.find(item => {
        return item.dictType == 'initiate_reasons'
      }).dictDate
      this.second_initiate_reasonsOptions = this.dictList.find(item => {
        return item.dictType == 'second_initiate_reasons'
      }).dictDate
      this.casecharacterOptions = this.dictList.find(item => {
        return item.dictType == 'casecharacter'
      }).dictDate
    }
  },
  methods: {
    getConclusion(row,col) {
      //return this.dict.label.conclusion[row.conclusion]
      return this.selectDictLabel(this.collectedmodeOptions, scope.row.payeetype)
    },
    getQueueClaimStatus(row,col) {
      //return this.dict.label.queue_claim_status[row.status]
      return this.selectDictLabel(this.collectedmodeOptions, scope.row.payeetype)
    },
    delarNum(row) {
      return Number(row.payableamount).toFixed(2)
    },
    delaramount(row) {
      return Number(row.amount).toFixed(2)
    },
    getApplicationType(row, col) {
      if (row.category != null) {
        if (row.category.length > 2) {
          const reasonArray = row.category.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              //reason = this.dict.label.apply_type[reasonArray[i]]
              reason = this.selectDictLabel(this.apply_typeOptions, reasonArray[i])
            } else {
              //reason = reason + ',' + this.dict.label.apply_type[reasonArray[i]]
              reason = reason + ',' + this.selectDictLabel(this.apply_typeOptions, reasonArray[i])
            }
          }
          return reason
        } else {
          //return this.dict.label.apply_type[row.category]
          return this.selectDictLabel(this.apply_typeOptions, row.category)
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
              //reason = this.dict.label.surve_types[reasonArray[i]]
              reason = this.selectDictLabel(this.surve_typesOptions, reasonArray[i])
            } else {
              //reason = reason + ',' + this.dict.label.surve_types[reasonArray[i]]
              reason = reason + ',' + this.selectDictLabel(this.surve_typesOptions, reasonArray[i])
            }
          }
          return reason
        } else {
          //return this.dict.label.surve_types[row.surveytype]
          return this.selectDictLabel(this.surve_typesOptions, row.surveytype)
        }
      }
    },
    // 任务类型
    getTasktype(row,col) {
      //return this.dict.label.casecharacter[row.tasktype]
      return this.selectDictLabel(this.casecharacterOptions, row.tasktype)
    },
    // 调查状态
    getInvestigateStatus(row,col) {
      //return this.dict.label.investigate_status[row.status]
      return this.selectDictLabel(this.investigate_statusOptions, row.status)
    },
    getIncidenttype(row, col) {
      if (row.incidenttype != null) {
        if (row.incidenttype.length > 2) {
          const reasonArray = row.incidenttype.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              //reason = this.dict.label.incidenttype[reasonArray[i]]
              reason = this.selectDictLabel(this.incidenttypeOptions, reasonArray[i])
            } else {
              //reason = reason + ',' + this.dict.label.incidenttype[reasonArray[i]]
              reason = reason + ',' + this.selectDictLabel(this.incidenttypeOptions, reasonArray[i])
            }
          }
          return reason
        } else {
          //return this.dict.label.incidenttype[row.incidenttype]
          return this.selectDictLabel(this.incidenttypeOptions, row.incidenttype)
        }
      }
    },
    // 调查来源
    getSurveySource(row, col) {
      if (row.source != null) {
        if (row.source.length > 2) {
          const reasonArray = row.source.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              //reason = this.dict.label.survey_source[reasonArray[i]]
              reason = this.selectDictLabel(this.survey_sourceOptions,reasonArray[i])
            } else {
              //reason = reason + ',' + this.dict.label.survey_source[reasonArray[i]]
              reason = reason + ',' + this.selectDictLabel(this.survey_sourceOptions,reasonArray[i])
            }
          }
          return reason
        } else {
          //return this.dict.label.survey_source[row.source]
          return this.selectDictLabel(this.survey_sourceOptions,row.source)
        }
      }
    },
    displaySurveyReason(row, col) {
      const dictName = 'second_initiate_reasons'
      const reasonArr = row.initiatereasons.split(',')
      if (reasonArr.length <= 1) {
          //return this.dict.label.initiate_reasons[row.initiatereasons]
          return this.selectDictLabel(this.initiate_reasonsOptions, row.initiatereasons)
      } else {
          //let reason1 = reasonArr[0] ? this.dict.label.initiate_reasons[reasonArr[0]] : ''
          let reason1 = reasonArr[0] ? this.selectDictLabel(this.initiate_reasonsOptions, reasonArr[0]) : ''
          //let reason2 = reasonArr[1] ? '/' + this.dict.label.second_initiate_reasons[reasonArr[1]] : ''
          let reason2 = reasonArr[1] ? '/' + this.selectDictLabel(this.second_initiate_reasonsOptions, reasonArr[1]) : ''
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
              //reason = this.dict.label.survereason[reasonArray[i]]
              reason = this.selectDictLabel(this.survereasonOptions, reasonArray[i])
            } else {
              //reason = reason + ',' + this.dict.label.survereason[reasonArray[i]]
              reason = reason + ',' + this.selectDictLabel(this.survereasonOptions, reasonArray[i])
            }
          }
          return reason
        } else {
          //return this.dict.label.survereason[row.initiatereasons]
          return this.selectDictLabel(this.survereasonOptions, row.initiatereasons)
        }
      }
    },
    getnegotiationType(row,col) {
      //return this.dict.label.negotiation_type[row.negotiationtype]
      return this.selectDictLabel(this.negotiation_typeOptions, row.negotiationtype)
    },
    getNegotiationStatus(row,col){
      //return this.dict.label.negotiation_status[row.status]
      return this.selectDictLabel(this.negotiation_statusOptions, row.status)
    },
    getCasetype(row,col) {
      //return this.dict.label.case_type[row.claimnotype]
      return this.selectDictLabel(this.case_typeOptions, row.claimnotype)
    },
    getRelationtoinsured(row,col) {
      //return this.dict.label.relationtoinsured[row.relationship]
      return this.selectDictLabel(this.relationtoinsuredOptions, row.relationship)
    },
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
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
      //   path :'/claims-handle/appeal-correct',
      //   query:{
      //     data
      //     // claimno: row.claimno,
      //     // status,
      //     // node: 'review'
      //   }
      // })
    }
    // getMaindiagnosis(row,col) {
    //   if (row.incidenttype != null) {
    //     if (row.incidenttype.length > 2) {
    //       const reasonArray = row.incidenttype.split(',')
    //       let reason = ''
    //       for (let i = 0; i < reasonArray.length; i++) {
    //         if (i === 0) {
    //           reason = this.dict.label.incidenttype[reasonArray[i]]
    //         } else {
    //           reason = reason + ',' + this.dict.label.incidenttype[reasonArray[i]]
    //         }
    //       }
    //       return reason
    //     } else {
    //       return this.dict.label.incidenttype[row.incidenttype]
    //     }
    // }
  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 120px;
}
.el-form /deep/ .el-form-item {
  margin-bottom: 0;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
