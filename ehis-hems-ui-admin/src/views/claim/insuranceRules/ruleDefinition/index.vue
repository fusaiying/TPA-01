<template>
  <div class="app-container">
    <el-card class="box-card">
      <div style="position: relative;">
        <div style="width: 84%;cursor: pointer;" @click.prevent="triggerHandle">
          <i v-show="!collapsed" class="el-icon-arrow-right">&nbsp;公共池</i>
          <i v-show="collapsed" class="el-icon-arrow-down">&nbsp;公共池</i>
        </div>
        <div style="position: absolute; top: 0; right: 10px;">
          <el-button type="primary" :disabled="!collapsed" size="mini" @click.stop="batchGet">产品同步</el-button>
          <el-button type="primary" :disabled="!collapsed" size="mini" @click.stop="batchGet">批量获取</el-button>
        </div>
      </div>
      <el-divider/>
      <div v-show="collapsed">
        <el-table
          v-loading="publicLoading"
          :data="pubTableData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          size="small"
          highlight-current-row
          style="width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" />
          <el-table-column prop="insurancecode" label="产品编码" align="center" show-overflow-tooltip/>
          <el-table-column prop="insurancename" label="产品名称" align="center" show-overflow-tooltip />
          <el-table-column :formatter="getMainflag" prop="mainflag" label="产品性质" align="center" show-overflow-tooltip/>
          <el-table-column :formatter="getInsuranceCategory" prop="category" label="产品分类" align="center" show-overflow-tooltip/>
          <el-table-column prop="startdate" label="同步日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.startdate|changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="enddate" label="操作日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.enddate|changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column :formatter="getInsuranceStatus" prop="status" label="规则状态" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="applyHandle(scope.row)">获取</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          :total="pubTotalCount"
          :page.sync="pubPageInfo.pageNum"
          :limit.sync="pubPageInfo.pageSize"
          @pagination="pubChange"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini" @submit.native.prevent>
        <el-row>
          <el-col :span="8">
              <el-form-item label="产品编码：" prop="insurancecode">
                <el-input v-model="formSearch.insurancecode" class="item-width" clearable size="mini" placeholder="请录入"/>
              </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="insurancename">
              <el-input v-model="formSearch.insurancename" class="item-width" clearable size="mini" placeholder="请录入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoad2"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >搜索</el-button>
          <el-button size="mini" type="primary" class="same-width" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-tabs v-model="activeName" @tab-click="tabClick">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01">
          <claimsTable v-loading="personListLading" :table-data="pendingTableData" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="03">
          <claimsTable :table-data="completedTableData" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <el-pagination
        :total="activeName==='01'?pendingTotal: completedTotal"
        :current-page="activeName==='01'?pendPageInfo.page:completPageInfo.page"
        :page-size="activeName==='01'?pendPageInfo.pageSize:completPageInfo.pageSize"
        :page-sizes="[5,10,20,30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="sizes, prev, pager, next"
        @size-change="sizeChange"
        @current-change="pageChange"
      />
    </el-card>
  </div>
</template>
<script>
import moment from 'moment'
import claimsTable from './components/claimsTable'
import { getPublicList, bacthApply, getPersonList, getAllRiskList } from '@/api/insuranceRules/ruleDefinition.js'
export default {

  components: {
    claimsTable
  },
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.pendingTotal === 0 && newVal.completedTotal > 0) {
        this.activeName = '03'
      } else {
        this.activeName = '01'
      }
    }
  },
  computed: {
    totalChange() {
      const {pendingTotal, completedTotal} = this
      return {pendingTotal, completedTotal}
    }
  },
  data() {
    return {
      collapsed: false,
      pubApplyLoading: false,
      publicLoading: false,
      aplayFlag: 0,
      pubTotalCount: 0,
      pubParams: {
        insurancecode: '',
        link: '',
        pageno: 0,
        pagesize: 10,
        status: ''
      },
      perParams: {
        insurancecode: '',
        link: '',
        pageno: 1,
        pagesize: 10,
        status: ''
      },
      pubPageInfo: {
        pageNum: 1,
        pageSize: 10
      },
      pubTableData: [],
      formSearch: {
        insurancecode: '',
        status: ''
      },
      activeName: '01',
      pendingTableData: [],
      completedTableData: [],
      pendingTotal: 0,
      completedTotal: 0,
      pendPageInfo: {
        page: 1,
        pageSize: 10
      },
      completPageInfo: {
        page: 1,
        pageSize: 10
      },
      personListLading: false,
      allRiskList: [],
      chooseList: [],
      pubForm: {
        insurancecode: '',
        insurancename: ''
      },
      pageChangeParams: {},
      searchLoad1: false,
      searchLoad2: false,
      insurance_statusOptions:[],
      mainflagOptions:[],
      insurance_categoryOptions:[],
      ins_insurancetypeOptions:[],
    }
  },
  mounted() {
    this.getPublicData(true)
    this.getPersonData()
    this.getAllRiskListHandle()
    this.getDicts("insurance_status").then(response => {
      this.insurance_statusOptions = response.data;
    });
    this.getDicts("mainflag").then(response => {
      this.mainflagOptions = response.data;
    });
    this.getDicts("insurance_category").then(response => {
      this.insurance_categoryOptions = response.data;
    });
    this.getDicts("ins_insurancetype").then(response => {
      this.ins_insurancetypeOptions = response.data;
    });
  },
  methods: {
    getMainflag(row, col) {
      //return this.dict.label.mainflag[row.mainflag]
      return this.selectDictLabel(this.mainflagOptions, row.mainflag);
    },
    getInsuranceCategory(row, col) {
      //return this.dict.label.insurance_category[row.category]
      return this.selectDictLabel(this.insurance_categoryOptions, row.category);
    },
    getInsInsurancetype(row, col) {
      //return this.dict.label.ins_insurancetype[row.insurancetype]
      return this.selectDictLabel(this.ins_insurancetypeOptions, row.insurancetype);
    },
    getInsuranceStatus(row, col) {
      //return this.dict.label.insurance_status[row.status]
      return this.selectDictLabel(this.insurance_statusOptions, row.status);
    },
    getAllRiskListHandle() {
      getAllRiskList().then(res => {
        this.allRiskList = res.data
      }).catch((e) => {})
    },
    handleSelect(item) {
      console.log(item)
    },
    querySearchAsync(queryString, cb) {
      const results = this.allRiskList.filter(array => array.value.match(queryString))
      cb(results)
    },

    getPublicData(flag = false) {
/*      let params = {
        insurancecode: this.pubForm.insurancecode,
        insurancename: this.pubForm.insurancename,
        link: 'R1',
        status: ''
      }
      if (flag) {
        this.pubParams = JSON.parse(JSON.stringify(params))
      } else {
        params = JSON.parse(JSON.stringify(this.pubParams))
      }
      params.pageno = this.pubPageInfo.page
      params.pagesize = this.pubPageInfo.pageSize
      this.searchLoad1 = true
      getPublicList(params).then(res => {
        this.pubTableData = res.data.list
        this.pubTotalCount = res.data.total
        this.searchLoad1 = false
      }).catch((e) => {})*/
    },
    triggerHandle() {
      this.collapsed = !this.collapsed
    },
    batchApply() {
      if (this.chooseList.length <= 0) {
        this.$message({ message: '请选择要申请的记录，进行批量申请操作。', type: 'warning', center: true, showClose: false })
      } else {
        this.pubApplyLoading = true
        const params = { insuranceResponseDOList: this.chooseList }
        bacthApply(params).then(res => {
          if (res.status === '1') {
            this.pubApplyLoading = false
            this.$message({ message: '申请成功', type: 'success', center: true, showClose: true })
            this.getPublicData(true)
            this.getPersonData()
          } else if (res.data === '存在险种已被申请，请重新选择') {
            this.$message({ message: res.data, type: 'error', center: true, showClose: true })
          } else {
            this.$message({ message: '申请失败', type: 'error', center: true, showClose: true })
          }
        }).catch(res => {
          this.pubApplyLoading = false
        })
      }
    },
    applyHandle(row) {
      const params = { insuranceResponseDOList: [row] }
      bacthApply(params).then(res => {
        if (res.status === '1') {
          this.$message({ message: '申请成功', type: 'success', center: true, showClose: true })
          this.getPublicData(true)
          this.getPersonData()
        } else if (res.data === '存在险种已被申请，请重新选择') {
          this.$message({ message: res.data, type: 'error', center: true, showClose: true })
        } else {
          this.$message({ message: '申请失败', type: 'error', center: true, showClose: true })
        }
      })
    },
    handleSelectionChange(sel) {
      this.chooseList = sel
      this.aplayFlag = sel.length
    },
    pubChange(val) {
      this.getPublicData()
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
      } else {
        this.completPageInfo.pageSize = val
      }
      this.getPersonData('1')
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
      } else {
        this.completPageInfo.page = val
      }
      this.getPersonData('1')
    },
    searchHandle() {
      this.activeName === '01' ? this.pendPageInfo.page = 1 : this.completPageInfo.page = 1
      this.activeName === '01' ? this.pendPageInfo.pageSize = 10 : this.completPageInfo.pageSize = 10
      this.getPersonData()
    },
    tabClick() {

    },
    // 获取个人池数据
    getPersonData(type='') {
      this.searchLoad2 = true
      this.personListLading = true
      let params = {}
      if (type === '1') {
        params = JSON.parse(JSON.stringify(this.perParams))
        params.pageno = this.activeName === '01' ? this.pendPageInfo.page : this.completPageInfo.page
        params.pagesize = this.activeName === '01' ? this.pendPageInfo.pageSize : this.completPageInfo.pageSize
      } else {
        params = {
          insurancecode: this.formSearch.insurancecode,
          link: 'R1',
          // pageno: this.activeName === '01' ? this.pendPageInfo.page : this.completPageInfo.page,
          // pagesize: this.activeName === '01' ? this.pendPageInfo.pageSize : this.completPageInfo.pageSize,
          status: this.formSearch.status
        }
        this.perParams = JSON.parse(JSON.stringify(params))
        params.pageno = this.activeName === '01' ? this.pendPageInfo.page : this.completPageInfo.page
        params.pagesize = this.activeName === '01' ? this.pendPageInfo.pageSize : this.completPageInfo.pageSize
      }
      getPersonList(params).then(res => {
        this.pendingTableData = res.data.waitList.list
        this.completedTableData = res.data.passList.list
        this.pendingTotal = res.data.waitList.total
        this.completedTotal = res.data.passList.total
        this.personListLading = false
        this.searchLoad2 = false
      }).catch(res => {
        this.searchLoad2 = false
        this.personListLading = false
      })
      this.pendingTableData = [{ insurancecode: 1 }]
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    resetPubForm() {
      this.$refs.pubForm.resetFields()
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .same-width{
    width: 73px;
  }
</style>
