<template>
  <div class="app-container">
    <el-card class="box-card">
      <div style="position: relative;">
        <div style="width: 84%;cursor: pointer;" @click.prevent="triggerHandle">
          <i v-show="!collapsed" class="el-icon-arrow-right">&nbsp;公共池</i>
          <i v-show="collapsed" class="el-icon-arrow-down">&nbsp;公共池</i>
        </div>
        <div style="position: absolute; top: 0; right: 10px;">
          <el-button
            :disabled="!collapsed"
            :loading="pubApplyLoading"
            class="filter-item"
            size="mini"
            type="primary"
            @click="batchApply"
          >批量获取</el-button>
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
          <el-table-column prop="insurancecode" label="险种编码" align="center" show-overflow-tooltip/>
          <el-table-column prop="insurancename" label="险种名称" align="center" show-overflow-tooltip />
          <el-table-column :formatter="getMainflag" align="center" prop="mainflag" label="主附险标记" show-overflow-tooltip/>
          <el-table-column :formatter="getInsuranceCategory" align="center" prop="category" label="险种类别" show-overflow-tooltip/>
          <el-table-column :formatter="getInsInsurancetype" align="center" prop="insurancetype" label="险种分类" show-overflow-tooltip/>
          <el-table-column prop="startdate" label="起售日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.startdate|changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="enddate" label="停售日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.enddate|changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column :formatter="getInsuranceStatus" prop="state" label="规则状态" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="applyHandle(scope.row)">获取</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <el-pagination
          :total="pubTotalCount"
          :current-page="pubPageInfo.page"
          :page-size="pubPageInfo.pageSize"
          :page-sizes="[5,10,20,30,50,100]"
          style="margin-top: 8px; text-align: right;"
          layout="sizes, prev, pager, next"
          @size-change="pubSizeChange"
          @current-change="pubPageChange"
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
            :loading="personListLading"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >搜索</el-button>
          <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-tabs v-model="activeName">
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
import { getPublicList, getPersonList, updatePublicRisk, getAllRiskList } from '@/api/insuranceRules/ruleDefinition.js'
export default {
  //dicts: ['insurance_status', 'mainflag', 'insurance_category', 'ins_insurancetype'],
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  components: {
    claimsTable
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
      pubPageInfo: {
        page: 1,
        pageSize: 10
      },
      pubTableData: [],
      formSearch: {
        riskCode: '',
        riskStatus: ''
      },
      activeName: '01',
      pendingTableData: [{
        insurancecode:'0001'
      }],
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
      subParams: {
        approvalnolist: {}
      },
      ruleLoading: false,
      allRiskList: [],
      chooseList: [],
      saveParams: {},
      insurance_statusOptions:[],
      mainflagOptions:[],
      insurance_categoryOptions:[],
      ins_insurancetypeOptions:[],
    }
  },
  mounted() {
    this.getPublicData()
    this.getPersonData(true)
    this.getAllRiskListHandle();
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
      })
    },
    handleSelect(item) {
      console.log(item)
    },
    querySearchAsync(queryString, cb) {
      const results = this.allRiskList.filter(array => array.value.match(queryString))
      cb(results)
    },
    getPublicData() {
      this.ruleLoading = true
      this.pubParams = {
        insurancecode: '',
        link: 'R2',
        pageno: this.pubPageInfo.page,
        pagesize: this.pubPageInfo.pageSize,
        status: ''
      }
      getPublicList(this.pubParams).then(res => {
        this.pubTableData = res.data.list
        this.pubTotalCount = res.data.total
        this.ruleLoading = false
      }).catch(res => {
        this.ruleLoading = false
      })
    },
    // 获取个人池数据
    searchHandle() {
      this.activeName === '01' ? this.pendPageInfo.page = 1 : this.completPageInfo.page = 1
      this.activeName === '01' ? this.pendPageInfo.pageSize = 10 : this.completPageInfo.pageSize = 10
      this.getPersonData(true)
    },
    getPersonData(flag=false) {
      this.personListLading = true
      let params = {}
      this.perParams = {
        insurancecode: this.formSearch.insurancecode,
        link: 'R2',
        status: this.formSearch.status
      }
      if (flag) {
        this.saveParams = JSON.parse(JSON.stringify(this.perParams))
        params = JSON.parse(JSON.stringify(this.perParams))
      } else {
        params = JSON.parse(JSON.stringify(this.saveParams))
      }
      params.pageno = this.activeName === '01' ? this.pendPageInfo.page : this.completPageInfo.page,
      params.pagesize = this.activeName === '01' ? this.pendPageInfo.pageSize : this.completPageInfo.pageSize,
      getPersonList(params).then(res => {
        //this.pendingTableData = res.data.waitList.list
        this.completedTableData = res.data.passList.list
        this.pendingTotal = res.data.waitList.total
        this.completedTotal = res.data.passList.total
        this.personListLading = false
      }).catch(res => {
        this.personListLading = false
      })
    },
    triggerHandle() {
      this.collapsed = !this.collapsed
    },
    // 批量获取
    batchApply() {
      if (this.chooseList.length <= 0) {
        this.$message({ message: '请选择要获取的记录，进行批量获取操作。', type: 'warning', center: true, showClose: false })
      } else {
        this.pubApplyLoading = true
        updatePublicRisk(this.subParams).then(res => {
          if (res.status === '1') {
            this.getPublicData()
            this.getPersonData(true)
            this.$message({
              message: '获取成功',
              showClose: true,
              center: true,
              type: 'success'
            })
          } else if (res.data === '存在险种已被申请，请重新选择') {
            this.$message({ message: res.data, type: 'error', center: true, showClose: true })
          } else {
            this.$message({
              message: '获取失败',
              showClose: true,
              center: true,
              type: 'error'
            })
          }
        }).finally(() => {
          this.pubApplyLoading = false
        })
      }
    },
    // 申请
    applyHandle(row) {
      const params = {
        approvalnolist: [
          {
            insurancecode: row.insurancecode
          }
        ]
      }
      updatePublicRisk(params).then(res => {
        if (res.status === '1') {
          this.pubApplyLoading = false
          this.getPublicData()
          this.getPersonData(true)
          this.$message({
            message: '申请成功',
            showClose: true,
            center: true,
            type: 'success'
          })
        } else if (res.data === '存在险种已被申请，请重新选择') {
          this.$message({ message: res.data, type: 'error', center: true, showClose: true })
        } else {
          this.$message({
            message: '申请失败',
            showClose: true,
            center: true,
            type: 'error'
          })
        }
      })
    },
    handleSelectionChange(sel) {
      this.chooseList = sel
      this.aplayFlag = sel.length
      this.subParams = {
        approvalnolist: []
      }
      sel.forEach(item => {
        this.subParams.approvalnolist.push({ insurancecode: item.insurancecode })
      })
    },
    pubSizeChange(val) {
      this.pubPageInfo.pageSize = val
      this.getPublicData()
    },
    pubPageChange(val) {
      this.pubPageInfo.page = val
      this.getPublicData()
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getPersonData()
      } else {
        this.completPageInfo.pageSize = val
        this.getPersonData()
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getPersonData()
      } else {
        this.completPageInfo.page = val
        this.getPersonData()
      }
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
