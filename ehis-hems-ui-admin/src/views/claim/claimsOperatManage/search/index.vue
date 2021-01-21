<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form ref="formSearch" :model="formSearch" label-width="126px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="查询类型：" prop="searchtype">
              <el-select v-model="formSearch.searchtype" class="item-width" placeholder="请选择" @change="changeSerchType">
                <el-option v-for="(item,index) in serchtypeList" :key="index" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input class="item-width" clearable v-model="formSearch.claimno" size="mini" placeholder="请输入"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input class="item-width" clearable v-model="formSearch.policyNo" size="mini" placeholder="请输入"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="insuredNo">
              <el-input class="item-width" clearable v-model="formSearch.insuredNo" size="mini" placeholder="请输入"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input class="item-width" clearable v-model="formSearch.name" size="mini" placeholder="请输入"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idNo">
              <el-input class="item-width" clearable v-model="formSearch.idNo" size="mini" placeholder="请输入"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="formSearch.searchtype == '03'">
          <el-col :span="8">
            <el-form-item label="案件状态：" prop="status">
              <el-select v-model="formSearch.status" clearable class="item-width" placeholder="请选择">
                 <el-option v-for="item in dict.queue_claim_status" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            :loading="serchLoding"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <!-- 客户信息 -->
    <el-card style="margin:15px 0">
      <el-collapse v-model="activeNames0">
        <el-collapse-item name="1">
          <template slot="title">
            <span style="font-size:15px">客户信息({{cusTotalCount}})</span>
            <span v-show="!activeNames0.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
          </template>
          <el-card>
            <div>
              <el-table
                size="small"
                highlight-current-row
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                tooltip-effect="dark"
                :data="CulpolTableData"
                style="width: 100%;">
                <el-table-column align="center" width="55" label="" prop="checked">
                  <template slot-scope="scope">
                    <el-radio v-model="radio" :label="scope.row.insuredNo" @change.native="handleSelect(scope.row, scope.$index)">{{ &nbsp; }}</el-radio>
                  </template>
                </el-table-column>
                <el-table-column align="center" width="160" prop="insuredNo" label="客户号" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" prop="insuredName" label="客户姓名" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" prop="sex" :formatter="getSex" label="性别" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" prop="birthdate" label="出生日期" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{scope.row.birthdate|formatApprai}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="idType" :formatter="getRegistrantidtype" label="证件类型" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" width="180" prop="idNo" label="证件号码" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" prop="customerType" label="客户类别" show-overflow-tooltip></el-table-column>
              </el-table>
              <!--分页组件-->
              <el-pagination
                v-if="cusTotalCount >10"
                :total="cusTotalCount"
                :current-page="cusPageInfo.page"
                :page-size="cusPageInfo.pageSize"
                :page-sizes="[5, 10, 20, 30,50,100]"
                style="margin-top: 8px; text-align: right;"
                layout="prev, pager, next, sizes"
                @size-change="cusSizeChange"
                @prev-click="prevPageCul"
                @next-click="nextPageCul"
                @current-change="cusPageChange"/>
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <!-- 保单信息 -->
    <el-card v-if="showList && changeSerchData.searchtype !=='03'" :loading="policyLoading">
      <el-collapse v-model="activeNames4">
        <el-collapse-item name="1">
          <template slot="title">
            保单信息({{polTotalCount}})
            <span v-show="!activeNames1.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
          </template>
          <el-card>
            <div>
              <el-table
                size="small"
                highlight-current-row
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                tooltip-effect="dark"
                :data="AxpolTableData"
                style="width: 100%;">
                <el-table-column type="expand">
                  <template slot-scope="props">
                      <el-col :span="5">
                        <span>印刷号：{{props.row.prtNo}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>客户性质：{{props.row.customerQuality}}</span>
                      </el-col>
                      <el-col :span="5" v-if="props.row.status == '1'">
                        <span>保单性质：{{props.row.policyQuality}}</span>
                      </el-col>
                      <el-col :span="5" v-if="props.row.status !== '1'">
                        <span>终止原因：{{props.row.endReason}}</span>
                      </el-col>
                      <el-col :span="5" v-if="props.row.status == '1'">
                        <span>服务机构：{{props.row.manageComName}}</span>
                      </el-col>
                      <el-col :span="5" v-if="props.row.status !== '1'">
                        <span>退保原因：{{props.row.surrender}}</span>
                      </el-col>
                      <el-col :span="4" v-if="props.row.status == '1'">
                        <span>新单回访状态：{{props.row.returnVisit}}</span>
                      </el-col>
                      <el-col :span="4" v-if="props.row.status !== '1'">
                        <span>承保机构：{{props.row.manageComName}}</span>
                      </el-col>
                      <el-col :span="5" v-if="props.row.status == '1'">
                        <span>服务人员：{{props.row.businessName}}</span>
                      </el-col>
                      <el-col :span="5" v-if="props.row.status !== '1'">
                        <span>新单回访状态：{{props.row.returnVisit}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>保单类型：{{props.row.policyType}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>机构代码：{{props.row.manageCom}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>签单日期：{{props.row.signDate}}</span>
                      </el-col>
                      <el-col :span="4">
                        <span>签单时间：{{props.row.signTime}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>渠道：{{props.row.saleChnlName}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>市场类型：{{props.row.marketType}}</span>
                      </el-col>
                      <el-col :span="5">
                        <span>移动电话：{{props.row.appntMobile}}</span>
                      </el-col>
                      <el-col :span="9">
                        <span>联系电话：{{props.row.appntPhone}}</span>
                      </el-col>
                      <el-col :span="24">
                        <span>联系地址：{{props.row.postAddress}}</span>
                      </el-col>
                  </template>
                </el-table-column>
                <el-table-column align="center" min-width="140" prop="contNo" label="保单号" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="80" prop="appntName" label="投保人" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="80" prop="insuredName" label="被保人" show-overflow-tooltip></el-table-column>
                <!-- <el-table-column align="center" width="140" prop="appntNo" label="投保人客户号" show-overflow-tooltip></el-table-column> -->
                <!-- <el-table-column align="center" prop="appntName" label="投保人姓名" show-overflow-tooltip></el-table-column> -->
                <el-table-column align="center" min-width="100" prop="cvalidate" label="生效日期" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="100" prop="endDate" label="满期/终止日期" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="100" prop="payToDate" label="保费交至日" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="80" prop="regularPay" label="期缴保费" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="80" prop="status" :formatter="getPolicystatus" label="保单状态" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="80" prop="isRenewal" label="续保标识" show-overflow-tooltip></el-table-column>
                <el-table-column align="center" min-width="80" prop="medicalInsurance" label="医保标识" show-overflow-tooltip></el-table-column>
                <!-- <el-table-column align="center" prop="manageCom" label="机构代码" show-overflow-tooltip></el-table-column> -->
                <el-table-column align="center" label="操作" min-width="140">
                  <template slot-scope="scope">
                    <el-button size="mini" type="text" @click="toPolicDetail(scope.row, 'policy')">保单详情</el-button>
                    <el-button size="mini" type="text" @click="downloadHandle(scope.row)">保单下载</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <!--分页组件-->
              <el-pagination
                v-if="polTotalCount >10"
                :total="polTotalCount"
                :page-size="polPageInfo.pageSize"
                :page-sizes="[5, 10, 20, 30,50,100]"
                style="margin-top: 8px; text-align: right;"
                layout="prev, pager, next, sizes"
                @prev-click="prevPage"
                @next-click="nextPage"
                @size-change="polSizeChange"
                @current-change="polPageChange"
                />
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <!-- 赔案信息 -->
    <el-card class="box-card" style="margin: 15px 0px" v-if="showList && changeSerchData.searchtype !=='02'">
      <el-collapse v-model="activeNames1" v-if="showList && changeSerchData.searchtype !=='02'">
        <el-collapse-item name="1">
          <template slot="title">
            赔案信息({{caselistToatl}})
            <span v-show="!activeNames1.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
          </template>
          <el-card v-if="showList && changeSerchData.searchtype !=='02'">
            <div>
              <!-- <div class="tab-title">
                <span>)</span>
              </div> -->
              <el-table
                :data="claimInformationData"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">
                <el-table-column align="center" prop="claimno" width="140" label="赔案号" show-overflow-tooltip>
                  <template slot-scope="scope">
                  <el-button size="small" type="text" @click="tocaseDetail(scope.row)">{{ scope.row.claimno }}</el-button>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/></el-table-column>
                <!-- <el-table-column align="center" prop="idcardno" label="身份证号" show-overflow-tooltip/></el-table-column> -->
                <el-table-column align="center" prop="riskname" label="险种名称" width="140"> </el-table-column>
                <el-table-column align="center" prop="dutyname" label="责任名称" width="140">
                  <template slot-scope="scope">
                  <span>{{ scope.row.dutyname}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/></el-table-column>
                <el-table-column align="center" prop="completedate" label="最新状态时间" show-overflow-tooltip/></el-table-column>
                <el-table-column align="center" prop="payableamount" label="赔付金额" width="140">
                  <template slot-scope="scope">
                  <span>{{ scope.row.payableamount|numFilter }}</span>
                  </template>
                </el-table-column>
                  <el-table-column align="center" prop="conclusion" :formatter="getConclusion"  label="结论" show-overflow-tooltip/></el-table-column>
                  <el-table-column align="center" prop="status" :formatter="getQueueclaimstatus" label="理赔状态" show-overflow-tooltip/></el-table-column>
              </el-table>
              <el-pagination
                v-if="caselistToatl >10"
                :total="caselistToatl"
                :page-size="casePageSize"
                :page-sizes="[5, 10, 20, 30,50,100]"
                style="margin-top: 8px; text-align: right;"
                layout="prev, pager, next, sizes"
                @prev-click="prevPage"
                @next-click="nextPage"
                @size-change="caseSizeChange"
                @current-change="caseCurrentChange"
                />
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <!-- 调查信息 -->
    <el-card style="margin: 15px 0px" v-if="showList && changeSerchData.searchtype !=='02'">
      <el-collapse v-model="activeNames3" v-if="showList && changeSerchData.searchtype !=='02'">
        <el-collapse-item name="1">
          <template slot="title">
            调查信息({{InvestigateDataTotal}})
            <span v-show="!activeNames2.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
          </template>
          <el-card>
            <el-table
              :data="InvestigateData"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form ref="searchForm" :model="formSearch" label-width="120px" label-position="right" size="mini">
                    <el-col :span="8">
                      <el-form-item label="出险地区：">
                        <span>{{scope.row.province}}<span v-if="scope.row.city !== ''">/</span>{{scope.row.city}}<span v-if="scope.row.district !==''">/</span>{{scope.row.district}}</span>
                        <!-- <span v-else>{{scope.row.investigateAreas2}}</span> -->
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="调查地区：">
                        <span>{{scope.row.investigateAreas2}}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="提调依据：">
                        <span v-if="changeSerchData.searchtype !== '03'">{{scope.row.investigatebasis}}</span>
                        <span v-else>{{dict.label.promotionbasis[scope.row.investigatebasis]}}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="调查要求：">
                        <el-tooltip class="item" effect="dark" :content="scope.row.initiatedemand" placement="top-start">
                          <span class="overEslap_class">{{scope.row.initiatedemand}}</span>
                        </el-tooltip>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="调查结论：">
                        <el-tooltip class="item" effect="dark" :content="scope.row.conclusion" placement="top-start">
                          <span class="overEslap_class">{{scope.row.conclusion}}</span>
                        </el-tooltip>
                      </el-form-item>
                    </el-col>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="taskno" min-width="140" label="调查号" show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-button size="small" type="text" @click="toClaimSerch(scope.row.taskno,scope.row.source)">{{ scope.row.taskno }}</el-button>
                </template>
              </el-table-column>
              <el-table-column align="center" min-width="130" prop="name" label="被保人姓名" show-overflow-tooltip/></el-table-column>
              <el-table-column align="center" min-width="90" prop="source" label="调查来源" :formatter="getSurveySource" show-overflow-tooltip/></el-table-column>
              <el-table-column align="center" min-width="140" prop="surveytype" label="调查类型" :formatter="getSurvetypes"> </el-table-column>
              <el-table-column align="center" min-width="140" prop="initiatereasons" label="调查原因" :formatter="displaySurveyReason"></el-table-column>
              <el-table-column align="center" min-width="80" prop="casecharacter" :formatter="investigatenature" label="性质"></el-table-column>
              <el-table-column align="center" min-width="140" prop="currentstatus" label="调查状态" :formatter="getInvestigateStatus" show-overflow-tooltip/></el-table-column>
              <el-table-column align="center" min-width="140" prop="initiatedate" label="提调日期">
                <template slot-scope="scope">
                  <span>{{scope.row.initiatedate|formatApprai}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" min-width="140" prop="closedate" label="调查结案日期">
                <template slot-scope="scope">
                  <span>{{scope.row.closedate|formatApprai}}</span>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              v-if="InvestigateDataTotal>10"
              :total="InvestigateDataTotal"
              :page-size="investigatePageSize"
              :page-sizes="[5, 10, 20, 30,50,100]"
              style="margin-top: 8px; text-align: right;"
              layout="prev, pager, next, sizes"
              @prev-click="prevPage"
              @next-click="nextPage"
              @size-change="InvestigateSizeChange"
              @current-change="InvestigatePageChange"
              />
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </el-card>
     <!-- 垫付信息 -->
    <el-card v-if="showList && changeSerchData.searchtype !=='02'" style="margin-bottom:20px">
      <el-collapse v-model="activeNames5">
        <el-collapse-item name="1">
          <template slot="title">
            垫付信息({{0}})
            <span v-show="!activeNames5.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
          </template>
          <el-card>
            <el-table
              :data="dianfuData"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column align="center" prop="claimno" width="140" label="垫付号" show-overflow-tooltip>
                <template slot-scope="scope">
                <el-button size="small" type="text">{{ scope.row.claimno }}</el-button>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/></el-table-column>
              <!-- <el-table-column align="center" prop="idcardno" label="身份证号" show-overflow-tooltip/></el-table-column> -->
              <el-table-column align="center" prop="riskname" label="险种名称" width="140"> </el-table-column>
              <el-table-column align="center" prop="dutyname" label="就诊日期" width="140">
                <template slot-scope="scope">
                <span>{{ scope.row.dutyname}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="payableamount" label="就诊医院" width="140">
                <template slot-scope="scope">
                <span>{{ scope.row.payableamount|numFilter }}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="conclusion" :formatter="getConclusion"  label="主要诊断" show-overflow-tooltip/></el-table-column>
              <el-table-column align="center" prop="conclusion" :formatter="getConclusion"  label="垫付金额" show-overflow-tooltip/></el-table-column>
              <el-table-column align="center" prop="status" :formatter="getQueueclaimstatus" label="结论" show-overflow-tooltip/></el-table-column>
              <el-table-column align="center" prop="status" :formatter="getQueueclaimstatus" label="垫付状态" show-overflow-tooltip/></el-table-column>
            </el-table>
            <el-pagination
              v-if="polTotalCount >10"
              :total="polTotalCount"
              :page-size="polPageInfo.pageSize"
              :page-sizes="[5, 10, 20, 30,50,100]"
              style="margin-top: 8px; text-align: right;"
              layout="prev, pager, next, sizes"
              @prev-click="prevPage"
              @next-click="nextPage"
              @size-change="polSizeChange"
              @current-change="polPageChange"
              />
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </el-card>
  </div>
</template>
<script>
import { getMessageList,findInsurednoByClaimno, getClaimInformationList,getClaimInvestigateList,getClaimInvestigateList2,isMySystem4ClaimNo,getCustomerNo} from '@/api/search/index.js'
import { atLeastOne } from '@/utils/commenMethods.js'
import localStorage from '../../../../utils/localstage'
import {decrypt,encrypt } from "@/utils/rsaEncrypt"
import moment from 'moment'
export default {
  dicts:['sex','card_type','policy_status','conclusion','queue_claim_status','task_type','initiate_reasons','second_initiate_reasons',
    'surve_types','survey_source','investigate_status','promotionbasis','saveChannel','investigate_nature'
  ],
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
  data() {
    return {
      formSearch: {
       searchtype: '03',
       claimno: '',
       policyNo: '',
       insuredNo: '',
       name: '',
       idNo: '',
       status: '',
      },
      showList: false,
      serchtypeList: [
        {
          value:'01',
          label: '全部'
        },
        {
          value:'02',
          label: '保单'
        },
        {
          value:'03',
          label: '理赔'
        },
      ],
      activeNames0: ['1'],
      activeNames4: [],
      activeNames1: [],
      activeNames2: [],
      activeNames3: [],
      activeNames5: [],
      poltotalPage: 1, // 统共页数，默认为1
      polcurrentPage: 1, //当前页数 ，默认为1
      polpageSize: 5, // 每页显示数量
      polcurrentPageData: [], //当前页显示内容
      cusTableData: [],
      dianfuData: [],
      cusTotalCount: 0,
      serchLoding: false,
      cusPageInfo: {
        page: 1,
        pageSize: 10
      },
      serchData: [],
      polTableData: [],
      insuredList: [],
      investTableData: [{policyNo: 'QE1234432464527'}],
      advanceTableData: [{policyNo: 'QE1234432464527'}],
      polTotalCount: 0,
      polPageInfo: {
        page: 1,
        pageSize: 10
      },
      AxpolTableData: [],
      CulpolTableData: [],
      changeSerchData: {},
      radio: '',
      claimInformationData: [],
      InvestigateData: [],
      InvestigateDataList: [],
      InvestigateDataTotal: 0,
      caselistToatl: 0,
      casePageSize: 10,
      casePageNo:1,
      investigatePageSize: 10,
      investigatePage: 1,
      address: '',
      policyLoading: false
    }
  },
  // 210105196003164048
  // 离开之后
  beforeRouteLeave(to,from,next){
    if (to.path == '/job-manage/policy-detail' || to.path == '/job-manage/insured-detail') {
      localStorage.set('claimno', this.formSearch.claimno)
      localStorage.set('name', this.formSearch.insuredName)
      localStorage.set('openId', this.formSearch.insuredId)
    } else {
      localStorage.remove('claimno')
      localStorage.remove('name')
      localStorage.remove('openId')
    }
    next()
  },
  // 进入之前
  beforeRouteEnter:(to,from,next)=>{
    next(vm =>{
      if (from.path == '/job-manage/policy-detail' || from.path == '/job-manage/insured-detail') {
        vm.formSearch.claimno = localStorage.get('claimno')
        vm.formSearch.insuredName = localStorage.get('name')
        vm.formSearch.insuredId = localStorage.get('openId')
        vm.changeSerchData.claimno = localStorage.get('claimno')
        vm.changeSerchData.insuredName = localStorage.get('name')
        vm.changeSerchData.insuredId = localStorage.get('openId')
        vm.getList()
      }
    })
    next()
  },
  methods: {
    InvestigateSizeChange(val) {
      this.investigatePageSize = val
      this.setInvasterList()
    },
    InvestigatePageChange(val) {
      this.investigatePage = val
      this.setInvasterList()
    },
    cusSizeChange(val){
      this.cusPageInfo.pageSize = val
      this.setCulPageData()
    },
    cusPageChange(val){
      this.cusPageInfo.page = val
      this.setCulPageData()
    },
    caseSizeChange(val){
      this.casePageSize = val
      this.setCaseList()
    },
    caseCurrentChange(val){
      this.casePageNo = val
      this.setCaseList()
    },
    polSizeChange(val){
      this.polPageInfo.pageSize = val
      this.setCurrentPageData()
    },
    polPageChange(val){
      this.polPageInfo.page = val
      this.setCurrentPageData()
    },
    investigatenature(row) {
      return this.dict.label.investigate_nature[row.casecharacter]
    },
    // 性别码表
    getSex(row, col) {
      return this.dict.label.sex[row.sex]
    },
    // 获取身份类型码表
    getRegistrantidtype(row, col) {
      return this.dict.label.card_type[row.idType]
    },
    // 结论
    getConclusion(row,col){
      let reg = row.conclusion + '   '
      let str = reg.replace(/\s+/g, "")
      return this.dict.label.conclusion[str]
    },
    // 赔案状态
    getQueueclaimstatus(row,col) {
      return this.dict.label.queue_claim_status[row.status]
    },
    // 获取保单状态
    getPolicystatus(row,col) {
      return this.dict.label.policy_status[row.status]
    },
    changeSerchType(val) {
      if (val !== '03') {
        this.formSearch.status = ''
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
    // 调查状态
    getInvestigateStatus(row,col) {
      return this.dict.label.investigate_status[row.currentstatus]
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
    // 调查原因
    displaySurveyReason(row, col) {
      const dictName = 'second_initiate_reasons'
      let reasonArr = []
      if (row.initiatereasons == undefined) {
        return
      }
      if (this.changeSerchData.searchtype == '01') {
        reasonArr = row.initiatereasons.split('&')
      } else {
        reasonArr = row.initiatereasons.split(',')
      }
      if (reasonArr.length <= 1) {
          return this.dict.label.initiate_reasons[row.initiatereasons]
      } else {
          let reason1 = reasonArr[0] ? this.dict.label.initiate_reasons[reasonArr[0]] : ''
          let reason2 = reasonArr[1] ? '/' + this.dict.label.second_initiate_reasons[reasonArr[1]] : ''
          return reason1 + reason2
      }
    },
    // 任务类型
    getTasktype(row,col) {
      return this.dict.label.task_type[row.tasktype]
    },
    toClaimSerch(no,source) {
      let data = encodeURI(
        JSON.stringify({
          taskno: encrypt(no),
          source: source
        })
      )
      const newpage = this.$router.resolve({
        name: 'serchDetail',
        params:{},
        query:{data}
      })
      window.open(newpage.href, '_blank');
    },
    // 搜索查询列表
    searchHandle(){
      this.radio = ''
      this.showList = false
      this.claimInformationData = []
      this.cusTableData = []
      this.CulpolTableData = []
      this.InvestigateDataList = []
      this.claimInformationDataList = []
      // this.serchData = []
      this.polTableData = []
      this.caselistToatl = 0
      this.cusTotalCount = 0
      this.polTotalCount = 0
      this.InvestigateDataTotal = 0
      this.changeSerchData.searchtype = this.formSearch.searchtype
      this.changeSerchData.claimno = this.formSearch.claimno
      this.changeSerchData.policyNo = this.formSearch.policyNo
      this.changeSerchData.insuredNo = this.formSearch.insuredNo
      this.changeSerchData.idNo = this.formSearch.idNo
      this.changeSerchData.status = this.formSearch.status
      this.changeSerchData.name = this.formSearch.name
      if (this.changeSerchData.claimno == '' && this.changeSerchData.policyNo == '' && this.changeSerchData.insuredNo == '' && this.changeSerchData.idNo == '' && this.changeSerchData.status == '' && this.changeSerchData.name == '') {
        this.$message.warning('查询类型需和其他条件组合查询！')
        return false
      }
      this.getList()
    },
    tocaseDetail(row) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status: 'show',
          node: 'review',
          serchNode: true
        })
      )
        isMySystem4ClaimNo(row.claimno).then(res =>{
          if (res.status == '1') {
            if (res.data == false) {
              this.$message.warning('核心赔案，理赔系统未查询到相关赔案信息！')
            } else {
              const newpage = this.$router.resolve({
                name: 'casedetail',
                params:{},
                query:{ data }
              })
              window.open(newpage.href, '_blank');
            }
          } else {
            this.$message.error('查询案件错误！')
          }
        })
    },
    // 重置表单
    resetForm(){
      this.$refs.formSearch.resetFields()
    },
    toPolicDetail(row) {
      let data = JSON.stringify(row)
      let insuredList = JSON.stringify(this.insuredList)
      const newpage = this.$router.resolve({
      name: 'policyDetail',
      params:{},
      query: {}
      // query:{data:data,insuredList:insuredList}
    })
    localStorage.set('data', data)
    localStorage.set('insuredList', insuredList)
    window.open(newpage.href, '_blank');
    },
    // 跳转至保单详情页面
    linkDetail(row, type) {
      let path = type === 'policy' ? '/job-manage/policy-detail':'/job-manage/insured-detail'
      this.$router.push({
        path: path,
        query: {policyno: this.formSearch.policyNo || '', name: this.formSearch.insuredName || '', idcardno: this.formSearch.insuredId || '',claimno: this.formSearch.claimno || '', data: JSON.stringify(row) }
      })
    },
     downloadHandle(row) {
      // const url = 'https://m-health.mypicc.com.cn/toDownload?contNo=' + row.contNo + '&pdfFlag=download&ip=10.130.129.21'
      // window.open(url)
      // 生产
      const requestData = {
        policyNo: row.contNo,
        privateKey: 'piccandthirdpart'
      }
      des3Encode(requestData).then(res => {
        if (res.status === '1') {
          const url = 'http://e-shop.picchealth.com/pdf/checkPDF?contno=' + res.data + '&type=d'
          window.open(url)
        }
      })
    },
    handleSelect(row,index){
      this.insuredList = []
      this.insuredList.push(row)
      this.showList = true
      this.radio = row.insuredNo
      this.changeSerchData.insuredNo = row.insuredNo
      this.address = row.address
      this.delPolicyList()
      if (this.formSearch.searchtype !== '02') {
        this.getcaseList()
        if (this.formSearch.searchtype == '01') {
          this.getClaimInvestigateList2()
        } else {
          this.getClaimInvestigateList()
        }
      }
    },
    // 查询赔案信息
    getcaseList() {
      let params = {
        searchtype:this.changeSerchData.searchtype || '03' ,
        claimno: this.changeSerchData.claimno,
        policyno: this.changeSerchData.policyNo,
        name: this.changeSerchData.name,
        insuredNo:this.changeSerchData.insuredNo,
        idcardno: this.changeSerchData.idNo,
        status: this.changeSerchData.status,
        pageno: 1,
        pagesize: 2000,
      }
      getClaimInformationList(params).then(res =>{
        if (res.status == '1') {
          this.claimInformationDataList = []
          this.claimInformationDataList = res.data.list
          this.caselistToatl = res.data.total || 0
        } else {
          this.$message.error('查询赔案信息错误！')
        }
      }).then(() =>{
        this.setCaseList()
      })
    },
    // 查询理赔调查信息
    getClaimInvestigateList() {
      let params = {
        searchtype:this.changeSerchData.searchtype || '03' ,
        claimno: this.changeSerchData.claimno,
        policyno: this.changeSerchData.policyNo,
        name: this.changeSerchData.name,
        insuredNo:this.changeSerchData.insuredNo,
        idcardno: this.changeSerchData.idNo,
        status: this.changeSerchData.status,
        pageno: 1,
        pagesize: 2000,
      }
      getClaimInvestigateList(params).then(res =>{
        if (res.status == '1') {
          this.InvestigateDataList = []
          this.InvestigateDataList = res.data.list || []
          this.InvestigateDataTotal = res.data.total || 0
        } else {
          this.$message.error('查询调查信息错误！')
        }
      }).then(() =>{
        this.setInvasterList()
      })
    },
    getClaimInvestigateList2() {
      let params = {
        searchtype:this.changeSerchData.searchtype || '01' ,
        claimno: this.changeSerchData.claimno,
        policyno: this.changeSerchData.policyNo,
        name: this.changeSerchData.name,
        insuredNo:this.changeSerchData.insuredNo,
        idcardno: this.changeSerchData.idNo,
        status: this.changeSerchData.status,
        pageno: 1,
        pagesize: 2000
      }
      getClaimInvestigateList2(params).then(res =>{
        if (res.status == '1') {
          this.InvestigateDataList = []
          this.InvestigateDataList = res.data.data.list || []
          this.InvestigateDataTotal = res.data.data.total || 0
        } else {
          this.$message.error('查询调查信息错误！')
        }
      }).then(() =>{
        this.setInvasterList()
      })
    },
    // 查询列表
    getList() {
      this.claimInformationData = []
      this.cusTableData = []
      this.CulpolTableData = []
      this.InvestigateDataList = []
      this.claimInformationDataList = []
      this.cusPageInfo.page = 1,
      this.cusPageInfo.pageSize = 10,
      this.polPageInfo.page = 1
      this.polPageInfo.pageSize = 10
      this.casePageSize =  10,
      this.casePageNo = 1,
      this.investigatePageSize = 10,
      this.investigatePage = 1,
      this.polTableData = []
      this.serchLoding = true
      if (this.formSearch.claimno !== '') {
        if (this.changeSerchData.searchtype == '01' || this.changeSerchData.searchtype == '02' ) {
          getCustomerNo(this.changeSerchData.claimno).then(res =>{
            if (res.status == '1') {
              let params = {
                searchtype:this.changeSerchData.searchtype || '03' ,
                claimno: this.changeSerchData.claimno,
                policyNo: this.changeSerchData.policyNo,
                name: this.changeSerchData.name,
                insuredNo:this.changeSerchData.insuredNo || res.data,
                idcardno: this.changeSerchData.idNo,
                status: this.changeSerchData.status,
                pageno: 1,
                pagesize: 2000,
              }
              getMessageList(params).then(res =>{
                if (res.status == '1') {
                  this.serchData = []
                  if (this.formSearch.searchtype == '03') {
                    this.serchData = res.data.list
                  } else {
                    this.serchData = (res && res.data) || []
                  }
                  this.serchLoding = false
                  if (this.serchData.length == 0) {
                    this.$message({ message: '未查询到信息！', type: 'warning'})
                  }
                } else {
                  this.$message.error('查询结果异常！')
                  this.serchLoding = false
                }
              }).then(() => {
                this.processingData(this.serchData)
              }).then(() => {
                this.setCurrentPageData()
                this.setCulPageData()
              })
            } else {
              this.$message.error('未查询到信息！')
              this.serchLoding = false
            }
          })
        } else {
          findInsurednoByClaimno(this.formSearch.claimno).then(res =>{
            if (res.status == '1') {
              let params = {
                searchtype:this.changeSerchData.searchtype || '03' ,
                claimno: this.changeSerchData.claimno,
                policyNo: this.changeSerchData.policyNo,
                name: this.changeSerchData.name,
                insuredNo:this.changeSerchData.insuredNo || res.data,
                idcardno: this.changeSerchData.idNo,
                status: this.changeSerchData.status,
                pageno: 1,
                pagesize: 2000,
              }
              getMessageList(params).then(res =>{
                if (res.status == '1') {
                  this.serchData = []
                  if (this.formSearch.searchtype == '03') {
                    this.serchData = res.data.list
                  } else {
                    this.serchData = (res && res.data) || []
                  }
                  this.serchLoding = false
                  if (this.serchData.length == 0) {
                    this.$message({ message: '未查询到信息！', type: 'warning'})
                  }
                } else {
                  this.$message.error('查询结果异常！')
                  this.serchLoding = false
                }
              }).then(() => {
                this.processingData(this.serchData)
              }).then(() => {
                this.setCurrentPageData()
                this.setCulPageData()
              })
            } else {
              this.$message.error('未查询到信息！')
              this.serchLoding = false
            }
          })
        }
      } else {
        let params = {
          searchtype:this.changeSerchData.searchtype || '03' ,
          claimno: this.changeSerchData.claimno,
          policyNo: this.changeSerchData.policyNo,
          name: this.changeSerchData.name,
          insuredNo:this.changeSerchData.insuredNo,
          idcardno: this.changeSerchData.idNo,
          status: this.changeSerchData.status,
          pageno: 1,
          pagesize: 2000,
        }
        getMessageList(params).then(res =>{
          if (res.status == '1') {
            this.serchData = []
            if (this.formSearch.searchtype == '03') {
              this.serchData = res.data.list
            } else {
              this.serchData = (res && res.data) || []
            }
            // this.serchData = (res && res.data) || []
            this.serchLoding = false
            if (this.serchData.length == 0) {
              this.$message({ message: '未查询到信息！', type: 'warning'})
            }
          } else {
            this.$message.error('查询结果异常！')
            this.serchLoding = false
          }
        }).then(() => {
          this.processingData(this.serchData)
        }).then(() => {
          this.setCurrentPageData()
          this.setCulPageData()
        })
      }
    },
    // 单独处理保单数据
    delPolicyList() {
      this.policyLoading = true
      let params = {
        searchtype:this.changeSerchData.searchtype || '03' ,
        claimno: this.changeSerchData.claimno,
        policyno: this.changeSerchData.policyNo,
        name: this.changeSerchData.name,
        insuredNo:this.changeSerchData.insuredNo,
        idcardno: this.changeSerchData.idNo,
        status: this.changeSerchData.status,
        pageno: 1,
        pagesize: 2000,
      }
      getMessageList(params).then(res =>{
        if (res.status == '1') {
          this.serchData = []
          if (this.formSearch.searchtype == '03') {
            this.serchData = res.data.list
          } else {
            this.serchData = (res && res.data) || []
          }
          // this.serchData = (res && res.data) || []
          this.serchLoding = false
          if (this.serchData.length == 0) {
            this.$message({ message: '未查询到信息！', type: 'warning'})
          }
          this.policyLoading = false
        } else {
          this.$message.error('查询结果异常！')
          this.serchLoding = false
          this.policyLoading = false
        }
      }).then(() => {
        this.filterPolicyData(this.serchData)
      }).then(() => {
        this.setCurrentPageData()
        // this.setCulPageData()
      })
    },
    // 筛选保单信息
    filterPolicyData(data) {
      this.polTableData = []
      let arr = []
      arr  = data || []
      let _this = this
      arr.length ? (arr.map((item) => {
        item.address = _this.address
        this.polTableData.push(item.policyInfo)
      })) : []
      this.setCurrentPageData()
    },
    // 处理数据（查到的是一整个列表，需分配给不同的分列表）
    processingData(data) {
      let arr = []
      arr  = data || []
      let _this = this
      if (this.formSearch.searchtype == '03') {
        this.cusTableData = arr
        return
      }
      var cusData = []
      arr.length ? (arr.map((item) => {
        cusData.push(item.insuredForm)
      })) : []
      _this.cusTableData = _this.dealObjArr(cusData)
      this.setCulPageData()
    },
    dealObjArr(objArray) {
      var result = []
      var temp = {}
      for (var i=0; i<objArray.length ; i++) {
        var myinsuredNo = objArray[i].insuredNo
        if (temp[myinsuredNo]) {
          continue
        }
        temp[myinsuredNo] = true
        result.push(objArray[i])
      }
      return result
    },
    // 处理保单信息数据（前端分页）
    setCurrentPageData() {
      let begin = (this.polPageInfo.page - 1) * this.polPageInfo.pageSize;
      let end = this.polPageInfo.page * this.polPageInfo.pageSize;
      this.AxpolTableData = this.polTableData.slice(begin,end);
      this.polTotalCount = this.polTableData.length
      this.AxpolTableData = [...this.AxpolTableData]
    },
    // 处理赔案信息列表
    setCaseList() {
      let begin = (this.casePageNo - 1) * this.casePageSize;
      let end = this.casePageNo * this.casePageSize;
      this.claimInformationData = this.claimInformationDataList.slice(begin,end);
      this.claimInformationData = [...this.claimInformationData]
    },
    // 处理调查信息列表
    setInvasterList() {
      let begin = (this.investigatePage - 1) * this.investigatePageSize;
      let end = this.investigatePage * this.investigatePageSize;
      this.InvestigateData = this.InvestigateDataList.slice(begin,end);
      this.InvestigateData = [...this.InvestigateData]
    },
    // 上一页
    prevPage() {
      if (this.polPageInfo.page == 1) return;
      this.polPageInfo.page--;
      this.setCurrentPageData();
    },
    // 下一页
    nextPage() {
      if (this.polPageInfo.page == this.polTotalCount )return ;
      this.polPageInfo.page++;
      this.setCurrentPageData();
    },
    // 处理客户信息的数据（前端分页）
    setCulPageData() {
      let begin = (this.cusPageInfo.page - 1) * this.cusPageInfo.pageSize;
      let end = this.cusPageInfo.page * this.cusPageInfo.pageSize;
      this.CulpolTableData = this.cusTableData.slice(begin,end);
      this.cusTotalCount = this.cusTableData.length
      this.CulpolTableData = [...this.CulpolTableData]
    },
    // 上一页
    prevPageCul() {
      if (this.cusPageInfo.page == 1) return;
      this.cusPageInfo.page--;
      this.setCulPageData();
    },
    // 下一页
    nextPageCul() {
      if (this.cusPageInfo.page == this.cusTotalCount )return ;
      this.cusPageInfo.page++;
      this.setCulPageData();
    }
  },
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
<style>
  .el-collapse-item__wrap {
    border: none !important;
  }
  .el-collapse {
    border: none;
  }
.overEslap_class{
  color: #555;
  font-size: 14px;
  width:50%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.el-tooltip__popper{
  max-width: 50%;
}
</style>
