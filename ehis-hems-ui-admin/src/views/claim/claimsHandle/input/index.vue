<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.enter.native="isinit='N'
                page=1
                finishPage=1
                searchHandle()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="searchForm.applicationsource" class="item-width" placeholder="请选择">
                <el-option v-for="item in apply_sourcetypeOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
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
                        @keyup.enter.native="isinit='N'
                page=1
                finishPage=1
                searchHandle()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="searchForm.idcardno" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.enter.native="isinit='N'
                page=1
                finishPage=1
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
              isinit='N'
              page=1
              finishPage=1
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div style="position: relative; margin-top: 30px;">
        <!-- <el-tabs v-model="activeName" @tab-click="getTableList(activeName)"> -->
        <el-tabs v-model="activeName" v-loading="loading">
          <el-tab-pane :label="`处理中(${total})`" name="01" >
            <claimsTable :table-data="pendingTableData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${finishTotal})`" name="02">
            <claimsTable @init-data="searchHandle" :table-data="completedTableData" :status="activeName"/>
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
          :loading="caseLoading"
          class="filter-item"
          size="mini"
          type="primary"
          style="position: absolute;right:10px;top:5px;"
          @click="manualApplyHandle"
        >案件获取
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'
  import {getList, getOneCase} from '@/api/claim/handleDeal'
  import {encrypt} from "@/utils/rsaEncrypt"
  //let dicts= [{dictType:"apply_sourcetype"}];

  export default {
    components: {
      claimsTable
    },
    //dicts:['apply_sourcetype'],
    data() {
      return {
        loading:false,
        apply_sourcetypeOptions: [],
        isinit: 'Y',
        caseLoading: false,
        page: 1,
        total: 0,
        finishTotal: 0,
        pageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        pageSizes: [5, 10, 20, 30, 50, 100],
        activeName: '01',
        completedTableData: [],
        pendingTableData: [],
        searchForm: {
          slrq: null,
          claimno: null,
          applicationsource: null,
          eventdate: null,
          name: null,
          idcardno: null,
          acceptDate: null,
        },
        changeSerchData: {},
      }
    },
    created() {
    },
    mounted() {
      this.searchHandle();
      this.getDicts("apply_sourcetype").then(response => {
        this.apply_sourcetypeOptions = response.data;
      });
    },
    watch: {
      totalChange: function (newVal, oldVal) {
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
        this.loading=true,
        this.page = 1
        this.finishPage = 1
        this.changeSerchData.slrq = this.searchForm.slrq
        this.changeSerchData.claimno = this.searchForm.claimno
        this.changeSerchData.applicationsource = this.searchForm.applicationsource
        this.changeSerchData.eventdate = this.searchForm.eventdate
        this.changeSerchData.name = this.searchForm.name
        this.changeSerchData.idcardno = this.searchForm.idcardno
        this.changeSerchData.acceptDate = this.searchForm.acceptDate
        this.finishTotal = null
        this.total = null
        this.finishTotal = 0
        this.total = 0
        this.getTableList('01')
        this.getTableList('02')
      },
      manualApplyHandle() {
        this.caseLoading = true
        const node = 'claim_input'
        getOneCase(node).then(res => {
          if (res.status === '1') {
            // 获取到案件并跳转详情页
            if (res.data === null) {
              this.$message({message: '获取案件错误！', type: 'error'})
            } else {
              // let data = encodeURI(
              //   JSON.stringify({
              //     claimno: encrypt(res.data.claimno),
              //     status: 'edit',
              //     node: 'input'
              //   })
              // )
              // const newpage = this.$router.resolve({
              //   name: 'inputdetail',
              //   params:{},
              //   query:{ data }
              // })
              let data = encodeURI(
                JSON.stringify({
                  claimNo: encrypt(res.data.claimno),
                  status: 'edit',
                  node: 'input'
                })
              )
              const newpage = this.$router.resolve({
                name: 'newinputdetail',
                params: {},
                query: {data}
              })
              window.open(newpage.href, '_blank');
              // this.$router.push({
              //   path: '/claims-handle/com-handle',
              //   query: {
              //     data
              //     // claimno: res.data.claimno,
              //     // status: 'edit',
              //     // node: 'input'
              //   }
              // })
            }
          } else {
            if (res.data.errCode === 'CL003' || res.data.errCode === 'CL012') {
              this.$message({message: res.data.errMsg, type: 'warning'})
            } else {
              this.$message({message: '获取案件错误！', type: 'error'})
            }
          }
        }).catch(error => {
          this.$message({message: '获取案件异常！', type: 'error'})
        }).finally(() => {
          this.caseLoading = false
        })
      },
      pageChange(val) {
        this.activeName === '01' ? this.page = val : this.finishPage = val
        this.getTableList(this.activeName)
      },
      sizeChange(val) {
        this.activeName === '01' ? this.pageSize = val : this.finishPageSize = val
        this.getTableList(this.activeName)
      },
      getTableList(tabno) {
        const params = {
          tabno,
          acceptstartdate: '',
          acceptenddate: '',
          isinit: this.isinit,
          node: 'claim_input',
          ...this.changeSerchData
        }
        if (tabno === '01') {
          params.pageno = this.page
          params.pagesize = this.pageSize
        } else {
          params.pagesize = this.finishPageSize
          params.pageno = this.finishPage
        }
        if (this.changeSerchData.acceptDate) {
          params.acceptstartdate = this.changeSerchData.acceptDate[0]
          params.acceptenddate = this.changeSerchData.acceptDate[1]
        }
        getList(params).then(res => {
          if (res.status === '1') {
            if (params.tabno === '01') {
              this.total = res.data.total
              this.pendingTableData = res.data.list
            } else {
              this.completedTableData = res.data.list
              this.finishTotal = res.data.total
            }
          } else {
            // this.$message.error({ message: '查询失败！' })
          }
        }).finally(() => {
          this.loading = false
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
