<template>
  <div>
    <el-card>
      <div class="tab-title">
        <span>调查详情</span>
        <el-button size="mini" type="primary" style="float:right;margin-right:20px" @click="close">关闭</el-button>
      </div>
      <el-card>
        <div class="tab-title">
          <span>提调信息</span>
        </div>
        <div>
          <el-form ref="searchForm" :model="formSearch" label-width="126px" label-position="right" size="mini">
            <el-row>
              <el-col :span="8">
                <el-form-item label="赔案号：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{investigateInfoFrom.claimno}}</span>
                  </template>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="调查次数：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{investigateInfoFrom.numberOfPreviousSurveys}}</span>
                  </template>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="提调人：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{investigateInfoFrom.initiator}}</span>
                  </template>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="调查方式：" prop="searchtype">
                  <span>按案件点</span>
                  <!-- <span>{{investigateInfoFrom.surveymode}}</span> -->
                </el-form-item>
              </el-col>
                <el-col :span="8">
                <el-form-item label="调查类型：" prop="searchtype">
                  {{dict.label.surve_types[investigateInfoFrom.surveytype]}}
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="调查来源：" prop="searchtype">
                  {{dict.label.survey_source[investigateInfoFrom.source]}}
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="提调原因：" prop="searchtype">
                  <span v-if="investigateInfoFrom.initiatereasons == '01'">{{dict.label.initiate_reasons[investigateInfoFrom.initiatereasons] + '/' + dict.label.second_initiate_reasons[investigateInfoFrom.upperreasons]}}</span>
                  <span v-else>{{dict.label.initiate_reasons[investigateInfoFrom.initiatereasons]}}</span>
                </el-form-item>
              </el-col>
                <el-col :span="8">
                <el-form-item label="提调依据：" prop="searchtype">
                  <span>{{investigateInfoFrom.initiatebasis}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="调查地区：" prop="searchtype">
                  <span>{{investigateInfoFrom.investigateAreasFroLP}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="提调日期：" prop="searchtype">
                  <span>{{investigateInfoFrom.initiatedate}}</span>
                </el-form-item>
              </el-col>
                <el-col :span="8">
                <el-form-item label="目标医院：" prop="searchtype">
                  <span v-if="investigateInfoFrom.hospital == '00'">{{investigateInfoFrom.otherHospital}}</span>
                  <span v-else>{{investigateInfoFrom.hospitalname}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="主要诊断：" prop="searchtype">
                  <span>{{investigateInfoFrom.maindiagnosis}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="疑点描述：" prop="searchtype">
                  <el-tooltip class="item" effect="dark" :content="investigateInfoFrom.doubtfuldescription" placement="top-start">
                    <span class="overfolw_class">{{investigateInfoFrom.doubtfuldescription}}</span>
                  </el-tooltip>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="调查要求：" prop="searchtype">
                  <el-tooltip class="item" effect="dark" :content="investigateInfoFrom.initiatedemand" placement="top-start">
                    <span class="overfolw_class">{{investigateInfoFrom.initiatedemand}}</span>
                  </el-tooltip>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-card>
      <el-card class="box-card" style="margin-top: 20px;" shadow="never">
        <div slot="header" class="clearfix">
          <span>调查结论</span>
        </div>
        <div>
          <el-form :model="tableForm" style="margin-top: 20px" label-width="110px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="调查人：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{paginInvestDataFrom.investigation}}</span>
                  </template>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="证件号码：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{paginInvestDataFrom.idNo}}</span>
                  </template>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="服务商：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{paginInvestDataFrom.serviceProvider}}</span>
                  </template>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="结论性质：" prop="searchtype">
                  <template slot-scope="scope">
                    <span>{{dict.label.casecharacter[paginInvestDataFrom.nature]}}</span>
                  </template>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-form :model="tableForm" style="margin-top: 20px" label-width="80px" size="mini">
            <el-table
              :data="paginInvestData"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="mini"
              highlight-current-row
              tooltip-effect="dark">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-table :data="props.row.surveyPointList" :show-header="false" :span-method="arraySpanMethod" border size="mini">
                    <el-table-column prop="hospitalname" label="医院"/>
                    <el-table-column width="260" prop="hospital">
                      <template slot-scope="scope">
                        <el-form-item label="是否调查">
                          <el-radio :disabled="true" v-model="scope.row.isInvestigate" label="01">是</el-radio>
                          <el-radio :disabled="true" v-model="scope.row.isInvestigate" label="02">否</el-radio>
                        </el-form-item>
                      </template>
                    </el-table-column>
                    <el-table-column>
                      <template slot-scope="scope">
                        <el-row>
                          <el-col v-if="scope.row.isInvestigate === '01'" :span="12">
                            <el-form-item label="是否异常">
                              <el-radio :disabled="true" v-model="scope.row.isAbnormal" label="01" size="mini">是</el-radio>
                              <el-radio :disabled="true" v-model="scope.row.isAbnormal" label="02" size="mini">否</el-radio>
                            </el-form-item>
                          </el-col>
                          <el-col v-if="scope.row.isInvestigate === '01'" :span="12">
                            <el-form-item label="打卡照片">
                              <el-button type="text" size="mini" @click="downloadAttachment(scope.row.annexList)">打卡照片({{ scope.row.annexList.length }})</el-button>
                            </el-form-item>
                          </el-col>
                          <el-col v-else :span="12">
                            <el-form-item label="备注信息">
                              <span>{{ scope.row.casePointDescription }}</span>
                            </el-form-item>
                          </el-col>
                        </el-row>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column :formatter="getCasePointType" prop="casePointType" label="案件点类型"/>
              <el-table-column prop="investigationRequirements" label="调查要求"/>
            </el-table>
          </el-form>
          <el-pagination
            v-if="total03>10"
            :total="total03"
            :current-page="page03"
            :page-sizes="[10, 20, 30]"
            :page-size="pageSize03"
            style="margin-top: 8px; text-align: right;"
            layout="sizes, prev, pager, next"
            @size-change="investSizeChange"
            @current-change="investPageChange"
          />
        </div>
        <div style="margin-top: 20px;">调查报告：</div>
        <div class="doc_list" style="">
          <p v-for="(item, index) in investigationReport" :key="index">
            <el-button class="del_padding" type="text" @click="downloadAttachment([item])">
              <i class="el-icon-link"/>
              {{ item.name }}
            </el-button>
          </p>
        </div>
        <div style="margin-top: 20px;">
          <span style="vertical-align: top;">结论描述：</span>
          <el-input v-model="conclusionDescription" style="width:  90%;" disabled type="textarea">备注信息</el-input>
        </div>
      </el-card>
    </el-card>
  </div>
</template>
<script>
import {getInvestigateInfo,getInvestigateDetail} from '../../../../api/search/index'
import {decrypt,encrypt } from "@/utils/rsaEncrypt"
import { claimDownloadFile } from '@/api/toneInfo/toneinfo.js'
import { downloadData } from '@/utils/index.js'
export default {
  dicts: ['casepoint_status','survey_source','invest_err_type','initiate_reasons','casecharacter','surve_types','second_initiate_reasons'],
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
  data () {
    return {
      formSearch: {},
      tableForm: {},
      taskno: '',
      querys: {},
      investigateInfoFrom: {
        Areas: {

        }
      },
      paginInvestData: [],
      paginInvestDataFrom: {},
      investigationReport: [],
      conclusionDescription: '',
      pageSize03: 10,
      page03: 1,
      total03: 0,
      source: ''
    }
  },
  created() {
    this.querys = JSON.parse(decodeURI(this.$route.query.data))
    this.taskno = this.querys.taskno?decrypt(this.querys.taskno):''
    this.source = this.querys.source || ''
    this.getInvestigateInfo()
    this.getInvestigateDetail()
  },
  methods: {
    close() {
      window.close()
    },
    investSizeChange(val) {
      this.pageSize03 = val
    },
    investPageChange(val) {
      this.page03 = val
    },
    arraySpanMethod({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 1) {
        if (columnIndex === 2) {
          return [1, 2]
        }
      }
    },
    downloadAttachment(fileList) {
      fileList.forEach(item => {
        const pushObj = {
          filePath: item.url,
          fileName: item.name
        }
        claimDownloadFile(pushObj).then(res => {
          downloadData(res, item.name)
        })
      })
    },
    // 案件点类型
    getCasePointType(row) {
      return this.dict.label.casepoint_status[row.casePointType]
    },
    // 提调信息
    getInvestigateInfo() {
      let data = {
        taskno : this.taskno,
        source : this.source
      }
      getInvestigateInfo(data).then(res =>{
        this.investigateInfoFrom = res.data.data
        this.investigateInfoFrom.Areas = res.data.data.investigateAreas[0]
      })
    },
    // 调查报告
    getInvestigateDetail() {
      getInvestigateDetail(this.taskno).then(res =>{
        this.paginInvestData = res.data.data.casePointList || []
        this.paginInvestDataFrom = res.data.data
        this.total03 = res.data.data.casePointList.length
        this.investigationReport = res.data.data.investigationReport || []
        this.conclusionDescription = res.data.data.conclusionDescription
      })
    },
  }
}
</script>
<style scoped>
.overfolw_class {
  color: #303133;
  font-size: 14px;
  width:80%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.el-tooltip__popper{
  max-width: 50%;
}
</style>
<style>
.el-tooltip__popper{
  max-width: 50%;
}
</style>
