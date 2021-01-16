<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :rules="rules" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="claimno">
              <el-input v-model="formSearch.claimno" @keyup.enter.native="searchHandle" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="理赔类型：" prop="applicationsource">
              <el-select v-model="formSearch.applicationsource" clearable class="item-width" placeholder="请选择">

              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司：" prop="eventdate">
              <el-select v-model="formSearch.applicationsource" clearable class="item-width" placeholder="请选择">
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="formSearch.name" @keyup.enter.native="searchHandle" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="formSearch.idcardno" @keyup.enter.native="searchHandle" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="接收日期：" prop="applyDate">
              <el-date-picker
                v-model="formSearch.applyDate"
                class="item-width"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~ "
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="案件状态：" prop="status">
              <el-select v-model="formSearch.status" clearable class="item-width" placeholder="请选择">
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="作业组：" prop="groupname">
              <el-select v-model="formSearch.groupname" class="item-width" clearable placeholder="请选择" @change="getOperatorHandle">
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="operator">
              <el-input v-model="formSearch.idcardno" @keyup.enter.native="searchHandle" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
            :loading="searchHandleLoading"
          >查询</el-button>
          <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>工作池</span>
          <div style="float: right; margin-top: 10px;">
            <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">案件调度</el-button>
            <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">清单导出</el-button>
          </div>

        </div>
        <div class="tab-title">
          <span>案件数({{ totalCount ? totalCount : 0 }})</span>
        </div>
        <el-table
          v-loading="tableLoading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column align="center" min-width="50" type="selection"/>
          <el-table-column align="center" sortable min-width="160" prop="claimno" label="报案号">
            <template slot-scope="scope">
              <el-button size="small" type="text" @click="clickModal(scope.row)">{{ scope.row.claimno }}</el-button>
            </template>
          </el-table-column>
          <el-table-column  min-width="95" align="center" sortable prop="applicationsource" label="交单来源" show-overflow-tooltip/>
          <el-table-column :sort-method="sortByName" min-width="110" align="center" sortable prop="name" label="被保人姓名" show-overflow-tooltip/>
          <el-table-column align="center" min-width="155px" sortable prop="idcardno" label="证件号码" show-overflow-tooltip/>
          <el-table-column :formatter="formatApplicaReason"  min-width="100" align="center" sortable :sort-method="descriptionOrder" prop="claimsCategory" label="理赔类别" show-overflow-tooltip/>
          <el-table-column align="center"  min-width="140" sortable prop="clinicHospital" label="就诊医院"/>
          <!-- <el-table-column align="center" width="140" sortable prop="eventdate" label="出险日期"/> -->
          <!-- <el-table-column align="center" width="140" sortable prop="applicationdate" label="受理日期"/> -->
          <el-table-column :formatter="getClaimStatus"  min-width="100" align="center" sortable prop="issuingCompany" label="出单公司" show-overflow-tooltip/>
          <el-table-column min-width="100" align="center" sortable prop="receiptDate" label="接收日期" show-overflow-tooltip/>
          <el-table-column min-width="100" align="center" sortable prop="caseState" label="案件状态" show-overflow-tooltip/>

          <!-- <el-table-column align="center"  min-width="95" :sort-method="sortByExplanation" sortable prop="explanation" :formatter="getHandleConclusion" label="当前结论" show-overflow-tooltip/> -->
          <el-table-column :sort-method="sortByOp"  min-width="100" align="center" sortable prop="operator" label="操作人"/>
          <el-table-column align="center" sortable label="操作"  min-width="70">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="assignHandle(scope.row)" :disabled="scope.row.status == '17' || scope.row.status == '18' ||  scope.row.status == '19' || scope.row.status == '20' || scope.row.status == '21' ">工作流查看</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <el-pagination
          :total="totalCount"
          :current-page="pageInfo.page"
          :page-size="pageInfo.pageSize"
          :page-sizes="pageSizes"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="sizeChange"
          @current-change="pageChange"/>
      </div>
    </el-card>
  </div>
</template>
<script>
  import { getList, taskReassign, getOperatorList, queryOperationgroupInfo, queryOperationgroupDetailInfo, queryBSMangecom, queryTPAMangecom, queryMangecomsGroup } from '@/api/taskReassign/index.js'
  import { changeDate, atLeastOne } from '@/utils/commenMethods.js'
  import moment from 'moment'
  import {encrypt} from "@/utils/rsaEncrypt"
  //import vPinyin from './pinyin/vue-py'

  export default {
    data() {
      const that = this
      const checkGroup = function(rules, value, callback) {
        if (that.formSearch.groupname === '' && value) {
          callback(new Error('请先选择作业组'))
        } else {
          callback()
        }
      }
      const checkDescription = function(rules, value, callback) {
        if (that.formSearch.name && !value) {
          callback(new Error('录入被保人姓名查询时，必录证件号码'))
        } else {
          callback()
        }
      }
      return {
        pageSizes: [5,10, 20, 30, 50,100],
        dialogVisible: false,
        diaForm: {
          company: '',
          groupnoname: '',
          name: ''
        },
        searchHandleLoading: false,
        reSetloading: false,
        multipleSelection: [],
        formSearch: {
          claimno: '',
          applicationsource: '',
          eventdate: '',
          name: '',
          applyDate: [moment().subtract(1, 'months').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')],
          operator: '',
          groupname: '',
          description: '',
          idcardno: '',
          status: ''
        },
        tableData: [
          {
            claimno: 'ADDCN01',
            applicationsource: '线下-北京分公司',
            name: '张三',
            idcardno: '2333333',
            claimsCategory: '直结',
            clinicHospital: '健康险',
            issuingCompany: '健康险',
            receiptDate: '2020-08-11',
            caseState: '受理中',
            operator: '001'
          },
          {
            claimno: 'ADDCN02',
            applicationsource: '线下-北京分公司',
            name: '李四',
            idcardno: '2333333',
            claimsCategory: '直结',
            clinicHospital: '健康险',
            issuingCompany: '健康险',
            receiptDate: '2020-08-11',
            caseState: '受理中',
            operator: ''
          },
          {
            claimno: 'ADDCN01',
            applicationsource: '线下-北京分公司',
            name: '王五',
            idcardno: '2333333',
            claimsCategory: '直结',
            clinicHospital: '健康险',
            issuingCompany: '健康险',
            receiptDate: '2020-08-11',
            caseState: '受理中',
            operator: '003'
          }




        ],
        totalCount: 0,
        pageInfo: {
          page: 1,
          pageSize: 10
        },
        modify: false,
        tableLoading: false,
        groupnoname: '',
        groupList: [],
        operatorList: [],
        rules: {
          idcardno: [{ validator: checkDescription, trigger: 'change' }],
          operator: [{ validator: checkGroup, trigger: 'focus' }]
        },
        reassignOperatorList: [],
        reassignType: '',
        eachChoose: {},
        companyList: [],
        grouptype: '',
        applyType: [],
        re: /[^\u4E00-\u9FA5]/,
        disabledList: false, //是否可以任务改派
        changeSerchData: {
          applyDate: [],
          groupname: ''
        },
        searchParams: {},
        defaultGroup: ''
      }
    },
    async mounted() {

    },
    methods: {
      sortByName(a, b) {

      },
      sortByOp(a, b) {

      },
      sortByExplanation(a, b) {

      },
      descriptionOrder(a, b) {

      },
      formatApplicaReason(row) {

      },
      getHandleConclusion(row,col) {

      },
      sizeChange(val) {

      },
      pageChange(val) {

      },
      handleSelectionChange(val) {

      },
      handleClose() {

      },
      /*  getApplySourcetype(row) {
          return this.dict.label.apply_sourcetype[row.applicationsource]
        },*/
      getApplyType(row) {

      },
      getClaimStatus(row) {

      },
      searchHandle() {

      },
      getData(flag) {
        // 处理参数

      },
      // 获取操作人列表
      getOperatorHandle() {

      },
      getReassignOperator() {

      },
      // 获取作业组列表
      getGroup() {

      },
      // 获取当前用户所在作业组信息
      getGroupDetail() {

      },
      // 获取保司机构
      getBSCompany() {

      },
      // 获取TPA机构
      getTPACompany() {

      },
      // 根据机构查询作业组
      getGroupList() {

      },
      clickModal(row) {

      },
      resetForm() {

      },
      batchAssignHandle() {

      },
      assignHandle(row) {

      },
      confirmHandle() {

      }
    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }
</style>

