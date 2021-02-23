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
              <el-select
                v-model="queryParams.organcode"
                filterable
                remote
                reserve-keyword
                placeholder="请选择机构"
                :remote-method="remoteMethod"
                class="item-width"
                @change="getUsers"
                size="mini">
                <el-option
                  v-for="(item, ind) in sysDeptOptions"
                  :key="ind"
                  :label="item.deptName"
                  :value="item.deptId">
                </el-option>
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
              <el-select v-model="queryParams.updateBy" class="item-width" placeholder="请选择"
                         remote
                         reserve-keyword
                         filterable
                         :remote-method="remoteUserMethod">
                <el-option v-for="(item,ind) in sysUserOptions" :label="item" :value="item"
                           :key="ind"/>
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
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
        </span>
      </div>
      <div style="position: relative">
        <el-tabs v-model="activeName" v-loading="loading" @tab-click="handleClick">
          <el-tab-pane :label="`处理中(${processedTotal})`" name="03">
            <claimsTable ref="claimsTable3" :table-data="processedData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已退回(${backTotal})`" name="01">
            <claimsTable ref="claimsTable1" :table-data="backData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${dealTotal})`" name="02">
            <claimsTable ref="claimsTable2" @init-data="searchHandle" :table-data="dealData" :status="activeName"/>
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
      <pagination
        v-if="activeName==='03'"
        v-show="processedTotal>0"
        :total="processedTotal"
        :page.sync="processedNum"
        :limit.sync="processedSize"
        @pagination="searchProcessed"
      />
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'
  import {getUser, getDept} from '@/api/claim/standingBookSearch'

  import {getBackToList, getDealWithList, getPendingList} from '@/api/claim/presentingReview'

  let dictss = [{dictType: 'claimType'},]
  export default {
    components: {
      claimsTable
    },
    data() {
      return {
        isListExport: false,
        caseNumber: false,//查询条件（报案号）是否显示
        backNum: 1,
        backSize: 10,
        dealNum: 1,
        dealSize: 10,
        processedNum: 1,
        processedSize: 10,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          submitdate: [],
          organcode: '',
          hospitalname: '',
          updateTime: [],
          batchno: '',
          claimtype: '',
          updateBy: '',
          caseNumber: '',
          rptno: '',
        },
        loading: true,
        backData: [],//已退回数据
        dealData: [],//已处理
        processedData: [],//处理中
        apply_sourcetypeOptions: [],
        isinit: 'Y',
        page: 1,
        backTotal: 0,
        dealTotal: 0,
        processedTotal: 0,
        pageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        activeName: '01',
        changeSerchData: {},
        dictList: [],
        claimTypeOptions: [],
        sysUserOptions: [],
        sysDeptOptions: []
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
      let query = {
        pageNum: 1,
        pageSize: 10,
      }
      getPendingList(query).then(res => {
        if (res != null && res.code === 200) {
          this.processedData = res.rows
          this.processedTotal = res.total
          if (this.processedData.length !== 0) {
            this.processedData.forEach(item => {
              item.minData = []
            })
          }
        }
      }).finally(() => {
        this.loading = false
      })
      getBackToList(query).then(res => {
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
      getDealWithList(query).then(res => {
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
      let item = {
        pageNum: 1,
        pageSize: 200,
      }
      getDept(item).then(res => {
        this.sysDeptOptions = res.deptlist
        this.queryParams.organcode = res.deptId
        let data = {
          organcode: res.deptId,
          pageNum: 1,
          pageSize: 200,
        }
        getUser(data).then(res => {
          if (res != null && res.code === 200) {
            this.sysUserOptions = res.data
          }
        })

      }).catch(res => {
      })
    },
    methods: {
      resetForm() {
        this.$refs.queryParams.resetFields()
      },
      searchProcessed() {//处理中查询
        getPendingList().then(res => {
        })
      },
      searchHandle() {
        const params = {
          pageNum: this.activeName === '01' ? this.backNum : this.dealNum,
          pageSize: this.activeName === '01' ? this.backSize : this.dealSize,
          orderByColumn: this.activeName === '01' ? this.$refs.claimsTable1.prop : this.$refs.claimsTable2.prop,
          isAsc: this.activeName === '01' ? this.$refs.claimsTable1.order : this.$refs.claimsTable2.order,
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
        if (this.isinit === 'N') {
          params.pageNum = 1
          params.pageSize = 10
          params.orderByColumn = ''
          params.isAsc = ''
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
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).finally(() => {
            this.loading = false
          })
        } else if ((this.activeName === '02')) {
          getDealWithList(params).then(res => {
            if (res != null && res.code === 200) {
              this.dealData = res.rows
              this.dealTotal = res.total
              if (this.dealData.length !== 0) {
                this.dealData.forEach(item => {
                  item.minData = []
                })
              }
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).finally(() => {
            this.loading = false
            this.isinit = 'Y'
          })
        } else {//处理中
          const query = {
            pageNum: this.processedNum,
            pageSize: this.processedSize,
            orderByColumn: this.$refs.claimsTable3.prop,
            isAsc: this.$refs.claimsTable3.order,
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
            query.submitstartdate = this.queryParams.submitdate[0]
            query.submitenddate = this.queryParams.submitdate[1]
          }
          if (this.queryParams.updateTime) {
            query.updatestartTime = this.queryParams.updateTime[0]
            query.updateendTime = this.queryParams.updateTime[1]
          }
          getPendingList(query).then(res => {
            if (res != null && res.code === 200) {
              this.processedData = res.rows
              this.processedTotal = res.total
              if (this.processedData.length !== 0) {
                this.processedData.forEach(item => {
                  item.minData = []
                })
              }
              if (res.rows.length <= 0) {
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
        let query = {
          submitstartdate: this.queryParams.submitdate[0],
          submitenddate: this.queryParams.submitdate[1],
          organcode: this.queryParams.organcode,
          hospitalname: this.queryParams.hospitalname,
          updatestartTime: this.queryParams.updateTime[0],
          updateendTime: this.queryParams.updateTime[1],
          batchno: this.queryParams.batchno,
          claimtype: this.queryParams.claimtype,
          updateBy: this.queryParams.updateBy,
          caseNumber: this.queryParams.caseNumber,
          rptno: this.queryParams.rptno,
        }
        if (this.activeName === '01') {
          getBackToList(query).then(res => {
            if (res.rows.length > 0) {
              this.isListExport = true
              let subDate = ''
              if (this.queryParams.submitdate.length > 0) {
                subDate = '&submitstartdate=' + this.queryParams.submitdate[0] + '&submitenddate=' + this.queryParams.submitdate[1]
                  + '&updatestartTime=' + this.queryParams.updateTime[0]
              }
              let upDate = ''
              if (this.queryParams.updateTime.length > 0) {
                upDate = '&updatestartTime=' + this.queryParams.updateTime[0] + '&updateendTime=' + this.queryParams.updateTime[1]
              }

              this.download('claimapt/batch/exportReturnedPool' + '?hospitalname=' + this.queryParams.hospitalname + '&organcode=' + this.queryParams.organcode +
                '&batchno=' + this.queryParams.batchno + '&claimtype=' + this.queryParams.claimtype + '&updateBy=' + this.queryParams.updateBy + subDate
                + upDate, {
                ...query
              }, `FYX_${new Date().getTime()}.xlsx`)
            } else {
              return this.$message.warning(
                "没有查询到能导出的数据！"
              )
            }
          }).finally(() => {
          })
        } else {//已处理
          getDealWithList(query).then(res => {
            if (res.rows.length > 0) {
              this.isListExport = true
              let subDate = ''
              if (this.queryParams.submitdate.length > 0) {
                subDate = '&submitstartdate=' + this.queryParams.submitdate[0] + '&submitenddate=' + this.queryParams.submitdate[1]
                  + '&updatestartTime=' + this.queryParams.updateTime[0]
              }
              let upDate = ''
              if (this.queryParams.updateTime.length > 0) {
                upDate = '&updatestartTime=' + this.queryParams.updateTime[0] + '&updateendTime=' + this.queryParams.updateTime[1]
              }
              this.download('claimapt/batch/exportProcessedPool' + '?hospitalname=' + this.queryParams.hospitalname
                + '&organcode=' + this.queryParams.organcode + '&batchno=' + this.queryParams.batchno + '&claimtype=' +
                this.queryParams.claimtype + '&updateBy=' + this.queryParams.updateBy + subDate + upDate, {
                ...query
              }, `FYX_${new Date().getTime()}.xlsx`)
            } else {
              return this.$message.warning(
                "没有查询到能导出的数据！"
              )
            }
          }).finally(() => {
          })
        }
      },
      handleClick(tab, event) {
        if (tab.name === '01' || tab.name === '03') {
          this.caseNumber = false
          this.queryParams.rptno = undefined
        } else {
          this.caseNumber = true
        }
      },
      remoteMethod(query) {
        let data = {
          deptName: query,
          pageNum: 1,
          pageSize: 200,
        }
        if (query !== '' && query != null) {
          getDept(data).then(res => {
            this.sysDeptOptions = res.deptlist
          }).catch(res => {
          })
        }
      },
      remoteUserMethod(query) {
        let data = {
          organcode: this.queryParams.organcode,
          userName: query,
          pageNum: 1,
          pageSize: 200,
        }
        if (query !== '' && query != null) {
          getUser(data).then(res => {
            if (res != null && res.code === 200) {
              this.sysUserOptions = res.data
            }
          })
        }
      },
      getUsers(val) {
        let data = {
          organcode: val,
          pageNum: 1,
          pageSize: 200,
        }
        getUser(data).then(res => {
          if (res != null && res.code === 200) {
            this.sysUserOptions = res.data
          }
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
