<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.native.enter="
              page=1
              finishPage=1
              hangUpPage=1
              isinit='N'
              initData()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="searchForm.applicationsource" class="item-width" placeholder="请选择">
                <el-option v-for="item in apply_sourcetypeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="searchForm.eventdate"
                class="item-width"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="searchForm.name" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.native.enter="
              page=1
              finishPage=1
              hangUpPage=1
              isinit='N'
              initData()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="searchForm.idcardno" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.native.enter="
              page=1
              finishPage=1
              hangUpPage=1
              isinit='N'
              initData()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptDate">
              <el-date-picker
                v-model="searchForm.acceptDate"
                class="item-width"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="loading"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              page=1
              finishPage=1
              hangUpPage=1
              isinit='N'
              initData()"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div style="position: relative; margin-top: 30px;">
        <!-- <el-tabs v-model="activeName" v-loading="loading" @tab-click="searchHandle(activeName)"> -->
        <el-tabs v-model="activeName" v-loading="loading">
          <el-tab-pane :label="`处理中(${total})`" name="01">
            <claimsTable :table-data="pendingTableData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${finishTotal})`" name="02">
            <claimsTable :table-data="completedTableData" :status="activeName" @init-data="initData"/>
          </el-tab-pane>
          <el-tab-pane :label="`悬挂(${hangUpTotal})`" name="03">
            <claimsTable :table-data="hangUpTableData" :status="activeName"/>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <el-pagination
          :total="activeName==='01'?total:activeName==='02'?finishTotal:hangUpTotal"
          :current-page="activeName==='01'?page:activeName==='02'?finishPage:hangUpPage"
          :page-sizes="pageSizes"
          :page-size="activeName==='01'?pageSize:activeName==='02'?finishPageSize:hangUpPageSize"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
        <el-button
          class="filter-item"
          size="mini"
          type="primary"
          style="position: absolute;right:10px;top:5px;"
          :loading="caseLoading"
          @click="getOneCase"
        >案件获取</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'
  import { getList, getPublicList, apply, getOneCase } from '@/api/claim/handleDeal'
  import codeMap from "../common/mixins/codeMap"
  import {encrypt} from "@/utils/rsaEncrypt";
  export default {
    mixins: [codeMap],
    components: {
      claimsTable
    },
   // dicts: ['apply_sourcetype', 'apply_type','claim_status'],
    data() {
      return {
        caseLoading:false,
        loading: false,
        // publicTotal: 0,
        // publicPage: 1,
        // publicPageSize: 5,
        page: 1,
        total: 0,
        finishTotal: 0,
        hangUpTotal: 0,
        pageSize: 10,
        hangUpPage: 1,
        hangUpPageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        pageSizes: [5, 10, 20, 30, 50, 100],
        activeName: '01',
        collapsed: false,
        pubTableData: [],
        completedTableData: [],
        hangUpTableData: [],
        pendingTableData: [],
        searchForm: {
          claimno: null,
          applicationsource: null,
          eventdate: null,
          name: null,
          idcardno: null,
          acceptDate: null
        },
        multipleSelection: [],
        isinit: 'Y',
        changeSerchData: {},
        apply_sourcetypeOptions: [],
        apply_typeOptions: [],
        claim_statusOptions: []
      }
    },
    mounted() {
      this.initData()
      // this.getPublicList()
      this.getDicts("apply_sourcetype").then(response => {
        this.apply_sourcetypeOptions = response.data;
      });
      this.getDicts("apply_type").then(response => {
        this.apply_typeOptions = response.data;
      });
      this.getDicts("claim_status").then(response => {
        this.claim_statusOptions = response.data;
      });
    },
    watch: {
      totalChange: function(newVal, oldVal) {
        if (newVal.total === 0 && newVal.hangUpTotal === 0 && newVal.finishTotal > 0) {
          this.activeName = '02'
        } else if (newVal.total === 0 && newVal.finishTotal === 0 && newVal.hangUpTotal > 0){
          this.activeName = '03'
        } else if (newVal.total === 0 && newVal.finishTotal > 0 && newVal.hangUpTotal > 0) {
          this.activeName = '02'
        } else {
          this.activeName = '01'
        }
      }
    },
    computed: {
      totalChange() {
        const {total, finishTotal, hangUpTotal} = this
        return {total, finishTotal, hangUpTotal}
      }
    },
    methods: {
      // getPublicList() {
      //   const params = {
      //     pageno: this.publicPage,
      //     pagesize: this.publicPageSize,
      //     node: 'claim_examine'
      //   }
      //   getPublicList(params).then(response => {
      //     if (response.status === '1') {
      //       this.publicTotal = response.data.total
      //       this.pubTableData = response.data.list
      //     } else {
      //       this.$message({ message: '查询公共池结果错误！', type: 'error' })
      //     }
      //   }).catch(error => {
      //     this.$message({ message: '查询公共池结果异常！', type: 'error' })
      //   })
      // },
      initData() {
        this.changeSerchData.claimno = this.searchForm.claimno
        this.changeSerchData.applicationsource = this.searchForm.applicationsource
        this.changeSerchData.eventdate = this.searchForm.eventdate
        this.changeSerchData.name = this.searchForm.name
        this.changeSerchData.idcardno = this.searchForm.idcardno
        this.changeSerchData.acceptDate = this.searchForm.acceptDate
        this.finishTotal = null
        this.hangUpTotal = null
        this.total = null
        this.finishTotal = 0
        this.hangUpTotal = 0
        this.total = 0
        this.searchHandle('01')
        this.searchHandle('02')
        this.searchHandle('03')
      },
      // 个人池列表信息
      searchHandle(tabno) {
        this.loading = true
        const params = {
          tabno,
          acceptstartdate: '',
          acceptenddate: '',
          isinit: this.isinit,
          pageno: tabno === '01' ? this.page : tabno === '02' ? this.finishPage : this.hangUpPage,
          pagesize: tabno === '01' ? this.pageSize : tabno === '02' ? this.finishPageSize : this.hangUpPageSize,
          node: 'claim_examine',
          ...this.changeSerchData
        }
        if (this.changeSerchData.acceptDate) {
          params.acceptstartdate = this.changeSerchData.acceptDate[0]
          params.acceptenddate = this.changeSerchData.acceptDate[1]
        }
        delete params.acceptDate
        getList(params).then(res => {
          if (res.status === '1') {
            if (params.tabno === '01') {
              this.total = res.data.total
              this.pendingTableData = res.data.list
            } else if (params.tabno === '02') {
              this.finishTotal = res.data.total
              this.completedTableData = res.data.list
            } else {
              this.hangUpTotal = res.data.total
              this.hangUpTableData = res.data.list
            }
          } else {
            // this.$message.error({ message: '查询失败！' })
          }
        }).finally(() => {
          this.loading = false
        })
      },
      getOneCase(){
        this.caseLoading = true
        const node = 'claim_examine'
        getOneCase(node).then(res => {
          if (res){
            if (res.status === '1') {
              //获取到案件并跳转详情页
              if (res.data === null) {
                this.$message({message: res.data.errMsg ? res.data.errMsg : '获取案件错误！', type: 'error'})
              } else {
                // let data = encodeURI(
                //   JSON.stringify({
                //     claimno: encrypt(res.data.claimno),
                //     status: 'handle',
                //     node: 'review'
                //   })
                // )
                // this.$router.push({
                //   path :'/claims-handle/com-handle',
                //   query:{
                //     data
                //   }
                // })
                let data = encodeURI(
                  JSON.stringify({
                    claimno: encrypt(res.data.claimno),
                    status: 'handle',
                    node: 'review'
                  })
                )
                // this.$router.push({
                //   path :'/claims-handle/com-handle',
                //   query:{
                //     data
                //     // claimno: row.claimno,
                //     // status,
                //     // node: 'review'
                //   }
                // })
                const newpage = this.$router.resolve({
                  name: 'reviewdetail',
                  params: {},
                  query: {data}
                })
                window.open(newpage.href, '_blank');
              }
            } else {
              if (res.data.errCode === 'CL003' || res.data.errCode === 'CL012') {
                this.$message({message: res.data.errMsg ? res.data.errMsg : '获取案件异常！', type: 'warning'})
              } else {
                this.$message({message: res.data.errMsg ? res.data.errMsg : '获取案件错误！', type: 'error'})
              }
            }
        }
        }).finally(() => {
          this.caseLoading = false
        })
      },
      collapse: function() {
        this.collapsed = !this.collapsed
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      // handleSelectionChange(val) {
      //   this.multipleSelection = val
      // },
      pageChange(val) {
        this.activeName === '01' ? this.page = val
          : this.activeName === '02' ? this.finishPage = val
          : this.hangUpPage = val
        this.searchHandle(this.activeName)
      },
      sizeChange(val) {
        this.activeName === '01' ? this.pageSize = val
          : this.activeName === '02' ? this.finishPageSize = val
          : this.hangUpPageSize = val
        this.searchHandle(this.activeName)
      },
      // totalPageChange(val) {
      //   this.publicPage = val
      //   this.getPublicList()
      // },
      // totalSizeChange(val) {
      //   this.publicPageSize = val
      //   this.getPublicList()
      // },
      // 公共池到个人池
      // applyHandle(row) {
      //   const claimnolist = []
      //   claimnolist.push(row.claimno)
      //   this.apply(claimnolist)
      // },
      // batchApplyHandle() {
      //   const params = []
      //   this.multipleSelection.map(item => {
      //     params.push(item.claimno)
      //   })
      //   this.apply(params)
      // },
      // apply(list) {
      //   const params = {
      //     claimno: list,
      //     node: 'claim_examine'
      //   }
      //   apply(params).then(response => {
      //     if (response.status === '1') {
      //       this.$message({ message: '申请成功！', type: 'success' })
      //       // 刷新列表
      //       this.initData()
      //       this.getPublicList()
      //     } else {
      //       if (response.data.errCode === 'CL002') {
      //         this.$message({ message: response.data.errMsg, type: 'warning' })
      //       } else {
      //         this.$message({ message: '案件申请错误！', type: 'error' })
      //       }
      //     }
      //   })
      // }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
