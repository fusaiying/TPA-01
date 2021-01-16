<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="queryParams" :model="queryParams" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="交单日期：" prop="submitdate">
              <el-date-picker
                v-model="queryParams.submitdate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构：" prop="organcode">
              <el-select v-model="queryParams.organcode" class="item-width" placeholder="请选择">
                <el-option key="01" label="上海分公司"
                           value="01"/>
                <el-option key="02" label="北京分公司"
                           value="02"/>
                <el-option key="03" label="长沙分公司"
                           value="03"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊医院：" prop="hospitalname">
              <el-input v-model="queryParams.hospitalname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="操作日期：" prop="updateTime">
              <el-date-picker
                v-model="queryParams.updateTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批次号：" prop="batchno">
              <el-input v-model="queryParams.batchno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="理赔类型：" prop="claimtype">
              <el-select v-model="queryParams.claimtype" class="item-width" placeholder="请选择">
                <el-option v-for="item in claimTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="updateBy">
              <el-select v-model="queryParams.updateBy" class="item-width" placeholder="请选择">
                <el-option key="张三" label="张三"
                           value="张三"/>
                <el-option key="李四" label="李四"
                           value="李四"/>
                <el-option key="admin" label="admin"
                           value="admin"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="caseNumber" label="报案号：" prop="rptno">
              <el-input v-model="queryParams.rptno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              isinit='N',
              page=1,
              finishPage=1,
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>批次工作池</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="addPresenting('add')">新增交单</el-button>
            <el-button type="primary" size="mini" @click="listExport">清单导出</el-button>
        </span>
      </div>
      <div style="position: relative">
        <el-tabs v-model="activeName" v-loading="loading" @tab-click="handleClick">
          <el-tab-pane :label="`已退回(${backTotal})`" name="01">
            <claimsTable :table-data="backData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${dealTotal})`" name="02">
            <claimsTable @init-data="searchHandle" :table-data="dealData" :status="activeName"/>
          </el-tab-pane>
        </el-tabs>
      </div>
      <!--分页组件-->
      <pagination
        v-if="activeName==='01'"
        v-show="backTotal>0"
        :total="backTotal"
        :page.sync="backNum"
        :limit.sync="backSize"
        @pagination="searchHandle"
      />
      <pagination
        v-if="activeName==='02'"
        v-show="dealTotal>0"
        :total="dealTotal"
        :page.sync="dealNum"
        :limit.sync="dealSize"
        @pagination="searchHandle"
      />
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'
  import {getBackToList, getDealWithList} from '@/api/claim/presentingReview'
 let dictss=[{dictType: 'claimType'},]
  export default {
    components: {
      claimsTable
    },
    data() {
      return {
        caseNumber: false,//查询条件（报案号）是否显示
        backNum: 1,
        backSize: 10,
        dealNum: 1,
        dealSize: 10,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          submitdate: undefined,
          organcode: undefined,
          hospitalname: undefined,
          updateTime: undefined,
          batchno: undefined,
          claimtype: undefined,
          updateBy: undefined,
          caseNumber: undefined,
          rptno: undefined,
        },
        loading: true,
        dealData: [],//已退回
        backData: [],//已处理数据
        apply_sourcetypeOptions: [],
        isinit: 'Y',
        page: 1,
        backTotal: 0,
        dealTotal: 0,
        pageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        activeName: '01',
        changeSerchData: {},
        dictList:[],
        claimTypeOptions:[]
      }
    },
    created() {
    },
    computed: {},
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claimTypeOptions = this.dictList.find(item => {
        return item.dictType === 'claimType'
      }).dictDate
      //this.searchHandle();
      getBackToList(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.backData = res.rows
          this.backTotal = res.total
          if (this.backData.length !== 0) {
            this.backData.forEach(item => {
              item.minData = []
            })
          }
        }
      }).finally(() => {
        this.loading = false
      })
      getDealWithList(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.dealData = res.rows
          this.dealTotal = res.total
          if (this.dealData.length !== 0) {
            this.dealData.forEach(item => {
              item.minData = []
            })
          }
        }
      }).finally(() => {
        this.loading = false
      })

    },
    methods: {
      resetForm() {
        this.$refs.queryParams.resetFields()
      },
      searchHandle() {
        const params = {
          pageNum: this.activeName === '01' ? this.backNum : this.dealNum,
          pageSize: this.activeName === '01' ? this.backSize : this.dealSize,
          submitstartdate: undefined,
          submitenddate: undefined,
          organcode: this.queryParams.organcode,
          hospitalname: this.queryParams.hospitalname,
          updatestartTime: undefined,
          updateendTime: undefined,
          batchno: this.queryParams.batchno,
          claimtype: this.queryParams.claimtype,
          applicationsource: this.queryParams.applicationsource,
          caseNumber: this.queryParams.caseNumber,
          rptno: this.queryParams.rptno,
          updateBy: this.queryParams.updateBy,
        }
        if (this.queryParams.submitdate) {
          params.submitstartdate = this.queryParams.submitdate[0]
          params.submitenddate = this.queryParams.submitdate[1]
        }
        if (this.queryParams.updateTime) {
          params.updatestartTime = this.queryParams.updateTime[0]
          params.updateendTime = this.queryParams.updateTime[1]
        }
        if (this.activeName === '01') {
          getBackToList(params).then(res => {
            if (res != null && res.code === 200) {
              this.backData = res.rows
              this.backTotal = res.total
              if (this.backData.length !== 0) {
                this.backData.forEach(item => {
                  item.minData = []
                })
              }
              if (res.rows.length<=0){
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).finally(() => {
            this.loading = false
          })
        } else {
          getDealWithList(params).then(res => {
            if (res != null && res.code === 200) {
              this.dealData = res.rows
              this.dealTotal = res.total
              if (this.dealData.length !== 0) {
                this.dealData.forEach(item => {
                  item.minData = []
                })
              }
              if (res.rows.length<=0){
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).finally(() => {
            this.loading = false
          })
        }
      },
      //新增
      addPresenting(status) {
        this.$router.push({
          path: '/claims-handle/presenting-edit',
          query: {status: status}
        })
      },
      //清单导出
      listExport() {
        let query={
          submitdate: this.queryParams.submitdate,
          organcode: this.queryParams.organcode,
          hospitalname: this.queryParams.hospitalname,
          updateTime: this.queryParams.updateTime,
          batchno: this.queryParams.batchno,
          claimtype: this.queryParams.claimtype,
          updateBy: this.queryParams.updateBy,
          caseNumber: this.queryParams.caseNumber,
          rptno: this.queryParams.rptno,
        }
        if (this.activeName === '01') {//已退回
          this.download('system/batch/exportReturnedPool', {
            ...query
          }, `FYX_${new Date().getTime()}.xlsx`)
        } else {//已处理
          this.download('system/batch/exportProcessedPool', {
            ...query
          }, `FYX_${new Date().getTime()}.xlsx`)
        }
      },
      handleClick(tab, event) {
        if (tab.name === '01') {
          this.caseNumber = false
          this.queryParams.rptno = undefined
        } else {
          this.caseNumber = true
        }
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
