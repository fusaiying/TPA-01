<template>
  <div class="app-container">
    <el-card class="box-card">
      <div style="position: relative;">
        <div @click="collapse"  style="width: 70%;cursor: pointer;">
          <i v-if="collapsed" class="el-icon-arrow-down">&nbsp;公共池({{publicTotal}})</i>
          <i v-else class="el-icon-arrow-right">&nbsp;公共池({{publicTotal}})</i>
        </div>
        <div style="position: absolute; top: 0; right: 10px;">
          <el-button
            class="filter-item"
            size="mini"
            type="warning"
            icon="el-icon-plus"
            style="right:10px;top:5px;"
            @click="batchApplyHandle"
            :disabled="multipleSelection.length<1"
          >批量申请<span v-if="multipleSelection.length>0">({{ multipleSelection.length }})</span></el-button>
        </div>
      </div>
      <el-divider></el-divider>
      <div v-show="collapsed">
        <el-table
          :data="pubTableData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          size="small"
          highlight-current-row
          style="width: 100%;"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" align="center" />
          查询领款人信息   <el-table-column prop="claimno" label="赔案号" width="140" align="center" show-overflow-tooltip/>
          <el-table-column :formatter="formatApplicaReason" prop="applicationsource" label="申请来源" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{dict.label.apply_sourcetype[scope.row.applicationsource]}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="被保人姓名" align="center" show-overflow-tooltip />
          <el-table-column prop="idcardno" label="证件号码" align="center" show-overflow-tooltip />
          <el-table-column prop="applicationreason" :formatter="formatApplicaReason" label="申请类别" align="center" show-overflow-tooltip />
          <el-table-column prop="eventdate" label="出险日期" align="center" show-overflow-tooltip />
          <el-table-column prop="acceptdate" label="受理日期" align="center" show-overflow-tooltip />
          <el-table-column align="center" :formatter="formatLimitation" prop="limitation" label="时效" show-overflow-tooltip/>
          <el-table-column prop="status" :formatter="formatClaimStatus" label="赔案状态" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" @click="applyHandle(scope.row)" type="text" style="padding: 0px;">申请</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <el-pagination
          :total="publicTotal"
          :current-page="publicPage"
          :page-sizes="pageSizes"
          :page-size="publicPageSize"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="totalSizeChange"
          @current-change="totalPageChange"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form style="padding-bottom: 30px;" ref="searchForm" :model="searchForm" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input class="item-width" clearable v-model="searchForm.claimno" size="mini" placeholder="请输入"
              @keyup.native.enter="
              page=1
              finishPage=1
              hangUpPage=1,
              isinit='N'
              initData()"/>
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
                v-model="searchForm.eventdate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input class="item-width" clearable v-model="searchForm.name" size="mini" placeholder="请输入"
              @keyup.native.enter="
              page=1
              finishPage=1
              hangUpPage=1,
              isinit='N'
              initData()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input class="item-width" clearable v-model="searchForm.idcardno" size="mini" placeholder="请输入"
              @keyup.native.enter="
              page=1
              finishPage=1
              hangUpPage=1,
              isinit='N'
              initData()"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptDate">
              <el-date-picker
                class="item-width"
                v-model="searchForm.acceptDate"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~"
                start-placeholder="开始日期"
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
              hangUpPage=1,
              isinit='N'
              initData()"
            :loading="loading"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider></el-divider>
      <div style="position: relative; margin-top: 30px;">
        <!-- <el-tabs v-model="activeName" v-loading="loading" @tab-click="searchHandle(activeName)"> -->
        <el-tabs v-model="activeName" v-loading="loading">
          <el-tab-pane :label='`处理中(${total})`' name="01">
            <claimsTable :tableData="pendingTableData" :status="activeName"></claimsTable>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${finishTotal})`" name="02">
            <claimsTable @init-data="initData" :tableData="completedTableData" :status="activeName"></claimsTable>
          </el-tab-pane>
          <el-tab-pane :label="`悬挂(${hangUpTotal})`" name="03">
            <claimsTable :tableData="hangUpTableData" :status="activeName"></claimsTable>
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
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from "./components/claimsTable";
  import { getList,getPublicList,apply } from "@/api/claim/handleDeal";
  import codeMap from "../common/mixins/codeMap"
  export default {
    mixins: [codeMap],
    dicts: ['apply_sourcetype', 'apply_type', 'claim_status'],
    components: {
      claimsTable
    },
    data() {
      return {
        loading:false,
        publicTotal:0,
        publicPage:1,
        publicPageSize:5,
        page: 1,
        total: 0,
        finishTotal:0,
        hangUpTotal:0,
        pageSize: 10,
        hangUpPage:1,
        hangUpPageSize:10,
        finishPage:1,
        finishPageSize:10,
        pageSizes:[5,10,20,30,50,100],
        activeName: "01",
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
        multipleSelection:[],
        isinit: 'Y',
        changeSerchData: {}
      };
    },
    created(){
      this.initData()
      this.getPublicList()
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
      getPublicList(){
        let params = {
          pageno: this.publicPage,
          pagesize: this.publicPageSize,
          node:'spot_check'
        };
        getPublicList(params).then(response => {
          if (response.status === "1") {
            this.publicTotal = response.data.total;
            this.pubTableData = response.data.list;
          } else {
            this.$message({message: '查询公共池结果错误！', type: 'error'});
          }
        })
      },
      initData() {
        this.changeSerchData.claimno = this.searchForm.claimno
        this.changeSerchData.applicationsource = this.searchForm.applicationsource
        this.changeSerchData.eventdate = this.searchForm.eventdate
        this.changeSerchData.name = this.searchForm.name
        this.changeSerchData.idcardno = this.searchForm.idcardno
        this.changeSerchData.acceptDate = this.searchForm.acceptDate
        this.changeSerchData.acceptDate = this.searchForm.acceptDate
        this.finishTotal = null
        this.total = null
        this.hangUpTotal = null
        this.finishTotal = 0
        this.total = 0
        this.hangUpTotal = 0
        this.searchHandle("01")
        this.searchHandle("02")
        this.searchHandle("03")
      },
      //个人池信息
      searchHandle(tabno){
        this.loading = true
        let params = {
          tabno,
          acceptstartdate: "",
          isinit: this.isinit,
          acceptenddate: "",
          pageno: tabno==='01'?this.page:tabno==='02'?this.finishPage:this.hangUpPage,
          pagesize: tabno==='01'?this.pageSize:tabno==='02'?this.finishPageSize:this.hangUpPageSize,
          node:'spot_check',
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
            } else if (params.tabno === '02') {
              this.finishTotal = res.data.total;
              this.completedTableData = res.data.list;
            }else{
              this.hangUpTotal = res.data.total;
              this.hangUpTableData = res.data.list;
            }
          } else {
            // this.$message.error({message: '查询失败！'})
          }
        }).finally(() => {
          this.loading = false
        })
      },
      collapse: function() {
        this.collapsed = !this.collapsed;
      },
      resetForm(){
        this.$refs.searchForm.resetFields()
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      pageChange(val) {
        this.activeName==='01'?this.page = val:
          this.activeName==='02'?this.finishPage = val
            :this.hangUpPage = val
        this.searchHandle(this.activeName)
      },
      sizeChange(val) {
        this.activeName==='01'?this.pageSize = val:
          this.activeName==='02'?this.finishPageSize = val
            :this.hangUpPageSize = val
        this.searchHandle(this.activeName)
      },
      totalPageChange(val){
        this.publicPage = val
        this.getPublicList()
      },
      totalSizeChange(val){
        this.publicPageSize = val
        this.getPublicList()
      },
      // 公共池到个人池
      applyHandle(row){
        const claimnolist = []
        claimnolist.push(row.claimno)
        this.apply(claimnolist)
      },
      batchApplyHandle() {
        let params = []
        this.multipleSelection.map(item => {
          params.push(item.claimno)
        })
        this.apply(params)
      },
      apply(list){
        const params = {
          claimno:list,
          node:'spot_check'
        }
        apply(params).then(response => {
          if(response.status === '1'){
            this.$message({message: '申请成功！', type: 'success'});
            //刷新列表
            this.initData()
            this.getPublicList()
          }else{
            if(response.data.errCode === 'CL002'){
              this.$message({message: response.data.errMsg, type: 'warning'});
            }else{
              this.$message({message: '案件申请错误！', type: 'error'});
            }
          }
        }).catch(error => {
          this.$message({message: '案件申请异常异常！', type: 'error'});
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
