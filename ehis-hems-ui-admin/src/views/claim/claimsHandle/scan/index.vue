<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form style="padding-bottom: 30px;" ref="searchForm" :model="searchForm" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input class="item-width" clearable v-model="searchForm.claimno" size="mini" placeholder="请输入"
                @keyup.enter.native="page=1
                finishPage=1
                hangUpPage=1
                isinit='N'
                allSearch()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
               <el-select class="item-width" v-model="searchForm.applicationsource" placeholder="请选择">
                <el-option v-for="item in dict.apply_sourcetype" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                class="item-width"
                value-format="yyyy-MM-dd"
                v-model="searchForm.eventdate"
                type="date"
                :picker-options="pickerOptions"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input class="item-width" clearable v-model="searchForm.name" size="mini" placeholder="请输入"
                @keyup.enter.native="page=1
                finishPage=1
                hangUpPage=1
                isinit='N'
                allSearch()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input class="item-width" clearable v-model="searchForm.idcardno" size="mini" placeholder="请输入"
                @keyup.enter.native="page=1
                finishPage=1
                hangUpPage=1
                isinit='N'
                allSearch()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptDate">
              <el-date-picker
               class="item-width"
                v-model="searchForm.acceptDate"
                type="daterange"
                range-separator="~"
                :picker-options="pickerOptions2"
                start-placeholder="开始日期"
                value-format="yyyy-MM-dd"
                end-placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
           <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              page=1
              finishPage=1
              hangUpPage=1
              isinit='N'
              allSearch()"
            :loading="loading"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider></el-divider>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName" @tab-click="clickTable">
          <el-tab-pane :label="`待处理(${pendingTableDataTotal})`" name="01">
            <claimsTable :tableData="pendingTableData" :status="activeName"></claimsTable>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${completedTableDataTotal})`" name="02">
            <claimsTable :tableData="completedTableData" :status="activeName"></claimsTable>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <el-pagination
          :total="activeName==='01'?total:finishTotal"
          :current-page="activeName==='01'?page:finishPage"
          :page-sizes="pageSizes"
          :page-size="activeName==='01'?pageSize:finishPageSize"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
        <el-button
          class="filter-item"
          size="mini"
          type="warning"
          icon="el-icon-plus"
          style="position: absolute;right:10px;top:5px;"
          @click="toClient"
        >批量扫描</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import claimsTable from "./components/claimsTable";
import { getList,getOneCase } from "@/api/claim/handleDeal";
const baseAddress = process.env.BASE_API
/*import { getToken } from '@/utils/auth'
import {encrypt} from "@/utils/rsaEncrypt"
import jwt from 'jsonwebtoken'*/
import Cookies from 'js-cookie'
export default {
  components: {
    claimsTable
  },
  dicts: ['apply_sourcetype'],
  data() {
    return {
      page: 1,
      total: 0,
      finishTotal:0,
      pageSize: 10,
      finishPage:1,
      finishPageSize:10,
      loading: false,
      isinit: 'Y',
      pageSizes:[5,10,20,30,50,100],
      activeName: "01",
      pendingTableDataTotal: 0,
      completedTableDataTotal: 0,
      label: {
        label01: '待处理(0)',
        label03: '已处理(0)'
      },
      completedTableData: [
        {taskno: 'WEF2344R5'}
      ],
      pendingTableData: [
        {taskno: 'JI23454R5'}
      ],
      searchForm: {
        claimno: null,
        applicationsource: null,
        eventdate: null,
        name: null,
        idcardno: null,
        acceptDate: null,
        operator: ''
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now() - 8.64e6;
        }
      },
      pickerOptions2: {
        disabledDate: (time) => {
          return time.getTime() > Date.now() - 8.64e6 || time.getTime() < time.getTime() > Date.now() - 8.64e6
        }
      },
      changeSerchData: {}
      // searchForm: {
      //   slrq: []
      // }
    };
  },
  mounted() {
    this.allSearch()
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.total === 0 && newVal.finishTotal > 0) {
        this.activeName = '02'
      } else {
        this.activeName = '01'
      }
    }
  },
  computed: {
    totalChange() {
      const {total, finishTotal} = this
      return {total, finishTotal}
    }
  },
  methods: {
    resetForm(){
      this.$refs.searchForm.resetFields()
    },
    allSearch() {
      this.changeSerchData.claimno = this.searchForm.claimno
      this.changeSerchData.applicationsource = this.searchForm.applicationsource
      this.changeSerchData.eventdate = this.searchForm.eventdate
      this.changeSerchData.name = this.searchForm.name
      this.changeSerchData.idcardno = this.searchForm.idcardno
      this.changeSerchData.acceptDate = this.searchForm.acceptDate
      this.changeSerchData.operator = this.searchForm.operator
      this.page = 1
      this.pageSize = 10
      this.finishPage = 1
      this.finishPageSize = 10
      this.finishTotal = null
      this.total = null
      this.finishTotal = 0
      this.total = 0
      this.searchHandle('01')
      this.searchHandle('02')
    },
    // searchHandle(){
    //   this.page = 1
    //   this.pageSize = 5
    //   this.finishPage = 1
    //   this.finishPageSize = 5
    //   this.getData('01')
    //   this.getData('02')
    // },
     searchHandle(tabno){
      this.loading = true
      let params = {
        tabno,
        acceptstartdate: "",
        acceptenddate: "",
        isinit: this.isinit,
        pageno: tabno==='01'?this.page : this.finishPage,
        pagesize: tabno==='01'?this.pageSize : this.finishPageSize,
        node:'claim_scan_new',
        ...this.changeSerchData
      }
      if(this.changeSerchData.acceptDate){
        params.acceptstartdate = this.changeSerchData.acceptDate[0]
        params.acceptenddate = this.changeSerchData.acceptDate[1]
      }
      delete params.acceptDate
      getList(params).then(res => {
        if (res.status === "1") {
          if(params.tabno === '01'){
            this.total = res.data.total;
            this.pendingTableData = res.data.list
            this.pendingTableDataTotal = res.data.total
          } else {
            this.finishTotal = res.data.total;
            this.completedTableData = res.data.list;
            this.completedTableDataTotal = res.data.total
          }
        } else {
          this.$message.error({message: '查询失败！'})
        }
      }).finally(() => {
        this.loading = false
      })
    },
     // 扫描
    toClient() {
      const jwt = require('jsonwebtoken')
      const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
      const name = str.user_name
      window.open(`${process.env.BASE_API}/claim-easyscan5/EasyScanContainer?usercode=${name}&receivedmode=1`)
    },
    clickTable() {
      this.activeName = this.activeName
    },
    multiScanHandle(){},
    pageChange(val) {
      this.activeName==='01'?this.page = val:this.finishPage = val
      this.searchHandle(this.activeName)
    },
    sizeChange(val) {
      this.activeName==='01'?this.pageSize = val:this.finishPageSize = val
      this.searchHandle(this.activeName)
    }
  }
};
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
