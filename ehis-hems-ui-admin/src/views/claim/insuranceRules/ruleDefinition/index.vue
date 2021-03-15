<template>
  <div class="app-container">
    <el-card class="box-card">
      <div style="position: relative;">
        <div style="width: 84%;cursor: pointer;" @click.prevent="triggerHandle">
          <i v-show="!collapsed" class="el-icon-arrow-right">&nbsp;公共池</i>
          <i v-show="collapsed" class="el-icon-arrow-down">&nbsp;公共池</i>
        </div>
        <div style="position: absolute; top: 0; right: 10px;">
          <el-button type="primary" :disabled="!collapsed" size="mini" @click.stop="">产品同步</el-button>
          <el-button type="primary" :disabled="!collapsed" size="mini" @click.stop="batchApply">批量获取</el-button>
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
          <el-table-column prop="riskCode" label="产品编码" align="center" show-overflow-tooltip/>
          <el-table-column prop="riskName" label="产品名称" align="center" show-overflow-tooltip />
          <el-table-column prop="riskNature" label="产品性质" align="center" show-overflow-tooltip/>
          <el-table-column prop="riskClass" label="产品分类" align="center" show-overflow-tooltip/>
          <el-table-column prop="synchronizeTime" label="同步日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.synchronizeTime|changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="操作日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime|changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="riskStatus" :formatter="getRiskStatus" label="规则状态" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="applyHandle(scope.row)">获取</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="pubTotalCount>0"
          :total="pubTotalCount"
          :page.sync="pubPageInfo.pageNum"
          :limit.sync="pubPageInfo.pageSize"
          @pagination="getPublicData"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini" @submit.native.prevent>
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编码：" prop="riskCode">
              <el-input v-model="formSearch.riskCode" class="item-width" clearable size="mini" placeholder="请录入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="riskName">
              <el-input v-model="formSearch.riskName" class="item-width" clearable size="mini" placeholder="请录入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoad2"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="getPersonData"
          >查询</el-button>
          <el-button size="mini" type="primary" class="same-width" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-tabs v-model="activeName" @tab-click="flash">
        <el-tab-pane :label="`待处理(${pendingTotal})`" name="01" >
          <claimsTable v-loading="personListLading" :table-data="pendingTableData" :status="activeName"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="02">
          <claimsTable v-loading="personListLading" :table-data="completedTableData" @refresh-item="refreshList" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <pagination
        v-if="activeName==='01'"
        v-show="pendingTotal>0"
        :total="pendingTotal"
        :page.sync="pendPageInfo.pageNum"
        :limit.sync="pendPageInfo.pageSize"
        @pagination="searchPending"
      />
      <!--分页组件-->
      <pagination
        v-if="activeName==='02'"
        v-show="completedTotal>0"
        :total="completedTotal"
        :page.sync="completPageInfo.pageNum"
        :limit.sync="completPageInfo.pageSize"
        @pagination="searchCompleted"
      />
    </el-card>
  </div>
</template>
<script>
  import moment from 'moment'
  import claimsTable from './components/claimsTable'
  import {searchPublicPool,searchProcessedList,searchUntreatedList,acquireDefinition} from '@/api/insuranceRules/ruleDefin'
  let dictss = [{dictType: 'product_status'}]
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
    data() {
      return {
        collapsed: false,
        pubApplyLoading: false,
        publicLoading: false,
        pubTotalCount: 0,
        pubPageInfo: {
          pageNum: 1,
          pageSize: 10
        },
        pubTableData: [],
        formSearch: {
          riskCode:undefined,
          riskName:undefined,
        },
        activeName: '01',
        pendingTableData: [],
        completedTableData: [],
        pendingTotal: 0,
        completedTotal: 0,
        pendPageInfo: {
          pageNum: 1,
          pageSize: 10
        },
        completPageInfo: {
          pageNum: 1,
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
        product_statusOptions:[],
        dictList:[],
        dataonLineListSelections:[],
      }

    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate
      this.getPublicData()
      this.searchPending('B')
      this.searchCompleted('B')
    },
    methods: {
      flash(){
        if (this.activeName==='01'){
          this.searchPending('B')
        }
      },
      getPublicData() {
        this.ruleLoading = true
        this.pubParams = {
          pageNum: this.pubPageInfo.pageNum,
          pageSize: this.pubPageInfo.pageSize,
        }
        searchPublicPool(this.pubParams).then(res => {
          this.pubTableData = res.rows
          this.pubTotalCount = res.total
          this.ruleLoading = false
        }).catch(res => {
          this.ruleLoading = false
        })
      },
      // 获取待处理个人池数据
      searchPending(status) {
        this.personListLading = true
        let params = {
          pageNum: this.pendPageInfo.pageNum,
          pageSize: this.pendPageInfo.pageSize,
          riskCode: this.formSearch.riskCode,
          riskName: this.formSearch.riskName
        }
        searchUntreatedList(params).then(res => {
          if (res != null && res.code === 200) {
            this.pendingTableData = res.rows
            this.pendingTotal = res.total
            this.personListLading=false
            if (status==='A' && res.rows.length<=0){
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {
          this.personListLading = false
        })
      },
      // 获取已处理个人池数据
      searchCompleted(status) {
        this.personListLading = true
        let params = {
          pageNum: this.completPageInfo.pageNum,
          pageSize: this.completPageInfo.pageSize,
          riskCode: this.formSearch.riskCode,
          riskName: this.formSearch.riskName
        }
        searchProcessedList(params).then(res => {
          if (res != null && res.code === 200) {
            this.completedTableData = res.rows
            this.completedTotal = res.total
            this.personListLading = false
            if (status==='A' && res.rows.length<=0){
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {
          this.personListLading = false
        })
      },
      getPersonData() {
        if (this.activeName === '01') {
          this.searchPending('A')
        } else if (this.activeName === '02') {
          this.searchCompleted('A')
        }
      },
      triggerHandle() {
        this.collapsed = !this.collapsed
      },
      // 批量获取
      batchApply() {
        let riskCodes=[]
        for(let i = 0; i< this.dataonLineListSelections.length; i++){
          riskCodes.push(this.dataonLineListSelections[i].riskCode)
          console.log(this.dataonLineListSelections[i].riskCode);
        }
        if (riskCodes.length<1){
          return this.$message.warning(
            "请选择需要获取的记录，进行批量获取操作。"
          )
        }else {
          acquireDefinition(riskCodes).then(res=>{
            console.log(riskCodes);
            if (res!=null && res.code===200){
              if (res.rows.length<=0){
                this.$message({
                  message: '获取成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
              }else {
                let riskCodeList=''
                for (let i = 0; i < res.rows.length-1; i++) {
                  riskCodeList=riskCodeList+res.rows[i]+','
                }
                riskCodeList=riskCodeList+res.rows[res.rows.length-1]
                this.$message({
                  message: '产品编码'+riskCodeList+'已被获取！',
                  type: 'warning',
                  center: true,
                  showClose: true
                })
              }

            }
            this.getPublicData()
            this.searchPending('B')
            this.searchCompleted('B')
          }).catch(res => {
            this.$message.error('获取失败！')
          })
        }

      },
      // 获取
      applyHandle(row) {
        let riskCodes=[row.riskCode]
        acquireDefinition(riskCodes).then(res=>{
          if (res!=null && res.code===200){
            if (res.rows.length<=0){
              this.$message({
                message: '获取成功！',
                type: 'success',
                center: true,
                showClose: true
              })
            }else {
              this.$message({
                message: '该产品已被获取，请重新获取！',
                type: 'warning',
                center: true,
                showClose: true
              })
            }

          }
          this.getPublicData()
          this.searchPending('B')
          this.searchCompleted('B')
        }).catch(res => {
          this.$message.error('获取失败！')
        })
      },
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      getRiskStatus(row){
        return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      },
      refreshList(item){
        if(item === 200){
          this.searchPending('B')
          this.searchCompleted('B')
        }
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
  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }
</style>
