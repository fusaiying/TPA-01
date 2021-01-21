<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.enter.native="
              page=1
              finishPage=1
              isinit='N'
              searchHandle()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="searchForm.applicationsource" class="item-width" placeholder="请选择">
                <el-option v-for="item in dict.apply_sourcetype" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="searchForm.eventdate"
                class="item-width"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="searchForm.name" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.enter.native="
              page=1
              finishPage=1
              isinit='N'
              searchHandle()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="searchForm.idcardno" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.enter.native="
              page=1
              finishPage=1
              isinit='N'
              searchHandle()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptDate">
              <el-date-picker
                v-model="searchForm.acceptDate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
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
              isinit='N'
              searchHandle()
            "
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName">
          <el-tab-pane :label="label.label01" name="01">
            <claimsTable :table-data="pendingTableData" @getPending="getPending" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="label.label02" name="02">
            <claimsTable :table-data="completedTableData" @uploadList='searchHandle' :status="activeName"/>
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
        <!-- <el-button
          class="filter-item"
          size="mini"
          type="primary"
          style="position: absolute;right:120px;top:5px;"
          @click="toClient"
        >影像扫描</el-button> -->
        <el-button
          :loading="caseLoading"
          class="filter-item"
          size="mini"
          type="primary"
          style="position: absolute;right:10px;top:5px;"
          @click="manualApplyHandle"
        >案件获取</el-button>
      </div>
    </el-card>
  </div>
 <!-- <div>
    临时注释
  </div>-->
</template>

<script>
import claimsTable from './components/claimsTable'
import Cookies from 'js-cookie'
/*import jwt from 'jsonwebtoken'*/
import { getList, getOneCase } from '@/api/claim/handleDeal'
/*import pro from '../../../../config/prod.env'*/
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  components: {
    claimsTable
  },
  dicts: ['apply_sourcetype'],
  data() {
    return {
      caseLoading: false,
      isinit: 'Y',
      page: 1,
      total: 0,
      finishTotal: 0,
      pageSize: 10,
      finishPage: 1,
      finishPageSize: 10,
      pageSizes: [5, 10, 20, 30, 50, 100],
      activeName: '01',
      label: {
        label01: '处理中(0)',
        label02: '已处理(0)'
      },
      completedTableData: [],
      pendingTableData: [],
      searchForm: {
        claimno: null,
        applicationsource: null,
        eventdate: null,
        name: null,
        idcardno: null,
        acceptDate: null
      },
      type:true,
      changeSerchData: {}
    }
  },
  mounted() {
    this.searchHandle()
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
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {
      this.changeSerchData.claimno = this.searchForm.claimno
      this.changeSerchData.applicationsource = this.searchForm.applicationsource
      this.changeSerchData.eventdate = this.searchForm.eventdate
      this.changeSerchData.name = this.searchForm.name
      this.changeSerchData.idcardno = this.searchForm.idcardno
      this.changeSerchData.acceptDate = this.searchForm.acceptDate
      this.page = 1;
      this.finishPage = 1;
      this.total = null
      this.finishTotal = null
      this.total = 0
      this.finishTotal = 0
      this.getPending()
      this.getCompleted()
    },
    manualApplyHandle() {
      this.caseLoading = true
      const node = 'claim_scan'
      getOneCase(node).then(res => {
        if (res.status === '1') {
          // 获取到案件并跳转详情页
          if (res.data === null) {
            this.$message({ message: '获取案件错误！', type: 'error' })
          } else {
            // this.$router.push({
            //   path: '/claims-handle/classificat',
            //   query: {
            //     claimNo: encrypt(res.data.claimno),
            //     type: 'classify',
            //     node: 'classificat'
            //   }
            // })
            const newpage = this.$router.resolve({
              name: 'ificatdetail',
              params:{},
              query:{
                node: 'classificat',
                type: 'classify',
                showFlag: '01',
                recoverNode: true,
                claimNo: encrypt(res.data.claimno)
              }
            })
            window.open(newpage.href, '_blank');
          }
        } else {
          if (res.data.errCode === 'CL003' || res.data.errCode==='CL012') {
            this.$message({ message: res.data.errMsg, type: 'warning' })
          } else {
            this.$message({ message: '获取案件错误！', type: 'error' })
          }
        }
      }).catch(error => {
        this.$message({ message: '获取案件异常！', type: 'error' })
      }).finally(() => {
        this.caseLoading = false
      })
    },
    pageChange(val) {
      this.activeName === '01' ? this.page = val : this.finishPage = val
      if (this.activeName === '01') {
        this.getPending()
      } else {
        this.getCompleted()
      }
    },
    sizeChange(val) {
      this.activeName === '01' ? this.pageSize = val : this.finishPageSize = val
      if (this.activeName === '01') {
        this.getPending()
      } else {
        this.getCompleted()
      }
    },
    getPending() {
      const params = {
        isinit: this.isinit,
        tabno: '01',
        acceptstartdate: '',
        acceptenddate: '',
        pageno: this.page,
        pagesize: this.pageSize,
        node: 'claim_scan',
        ...this.changeSerchData
      }
      if (this.changeSerchData.acceptDate) {
        params.acceptstartdate = this.changeSerchData.acceptDate[0]
        params.acceptenddate = this.changeSerchData.acceptDate[1]
      }
      getList(params).then(res => {
        if (res.status === '1') {
          this.total = res.data.total
          this.label.label01 = `处理中(${this.total})`
          this.pendingTableData = res.data.list
        } else {
          this.$message.error({ message: '查询失败！' })
        }
      })
    },
    getCompleted() {
      const params = {
        isinit: this.isinit,
        type:this.type,
        tabno: '02',
        acceptstartdate: '',
        acceptenddate: '',
        pageno: this.finishPage,
        pagesize: this.finishPageSize,
        node: 'claim_scan',
        ...this.changeSerchData
      }
      if (this.type) {
          this.type = false
      }
      if (this.changeSerchData.acceptDate) {
        params.acceptstartdate = this.changeSerchData.acceptDate[0]
        params.acceptenddate = this.changeSerchData.acceptDate[1]
      }
      getList(params).then(res => {
        if (res.status === '1') {
          this.finishTotal = res.data.total
          this.label.label02 = `已处理(${this.finishTotal})`
          this.completedTableData = res.data.list
        } else {
          this.$message.error({ message: '查询失败！' })
        }
      })
    },
    toClient() {
      const jwt = require('jsonwebtoken')
      const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
      const name = str.user_name
      window.open(`${process.env.BASE_API}/claim-easyscan5/EasyScanContainer?usercode=${name}&&receivedmode=1`)
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
