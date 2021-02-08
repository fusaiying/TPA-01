<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="交单日期：" prop="submitdate">
              <el-date-picker
                v-model="searchForm.submitdate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="2019-10-10"
                end-placeholder="2019-10-10"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构：" prop="organcode">
              <el-select
                v-model="searchForm.organcode"
                filterable
                remote
                reserve-keyword
                placeholder="请选择机构"
                :remote-method="remoteMethod"
                class="item-width"
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
              <el-input v-model="searchForm.hospitalname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="操作日期：" prop="updateTime">
              <el-date-picker
                v-model="searchForm.updateTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="2019-10-10"
                end-placeholder="2019-10-10"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批次号：" prop="batchno">
              <el-input v-model="searchForm.batchno" class="item-width" clearable size="mini" placeholder="请输入"/>
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
              search()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix" @click="open">
        <span><span v-show="!collapsed" class="el-icon-arrow-right" style="margin-right: 5px"></span>
            <span v-show="collapsed" class="el-icon-arrow-down" style="margin-right: 5px"></span>公共池</span>
        <span style="float: right;">
            <el-button type="primary" :disabled="!collapsed" size="mini" @click.stop="batchGet">批量获取</el-button>

        </span>
      </div>
      <div v-show="divShow">
        <publicTable ref="publicTable" :searchHandle="searchHandle" :table-data="publicData" :status="activeName"/>
        <!--分页组件-->
        <pagination
          v-show="publicTotal>0"
          :total="publicTotal"
          :page.sync="searchForm.pageNum"
          :limit.sync="searchForm.pageSize"
          @pagination="searchPublic"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>个人池</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
        </span>
      </div>
      <div style="position: relative">
        <el-tabs v-model="activeName" v-loading="loading">
          <el-tab-pane :label="`待处理(${backTotal})`" name="01">
            <person-table ref="personTable1" :table-data="backData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${dealTotal})`" name="02">
            <person-table ref="personTable2" @init-data="searchHandle" :table-data="dealData" :status="activeName"/>
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
  import personTable from './components/personTable'
  import publicTable from './components/publicTable'
  import {getPublicList, getUntreatedList, getProcessedList} from '@/api/claim/presentingReview'
  import {getUser,getDept} from '@/api/claim/standingBookSearch'
  export default {
    components: {
      personTable, publicTable
    },
    data() {
      return {
        isListExport: false,
        backNum: 1,
        backSize: 10,
        dealNum: 1,
        dealSize: 10,
        divShow: false,
        collapsed: false,
        publicTotal: 0,
        loading: false,
        isinit: 'Y',
        page: 1,
        finishPage: 1,
        backTotal: 0,
        dealTotal: 0,
        activeName: '01',
        publicData: [],
        backData: [],
        dealData: [],
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          submitdate: undefined,
          organcode: undefined,
          hospitalname: undefined,
          updateTime: undefined,
          batchno: undefined,
        },
        changeSerchData: {},
        sysDeptOptions: [],
      }
    },
    created() {
    },
    mounted() {
      //获取公共池
      getPublicList(this.searchForm).then(res => {
        this.publicData = res.rows
        this.publicTotal = res.total
      }).finally(() => {
        this.loading = false
      })
      //获取直结复核理赔批次待处理个人池
      getUntreatedList(this.searchForm).then(res => {
        this.backData = res.rows
        this.backTotal = res.total
      }).finally(() => {
        this.loading = false
      })
      //获取直结复核公共池获取到未处理个人池
      getProcessedList(this.searchForm).then(res => {
        this.dealData = res.rows
        this.dealTotal = res.total
      }).finally(() => {
        this.loading = false
      })
      let item={
        pageNum: 1,
        pageSize: 200,
      }
      getDept(item).then(res => {
        this.sysDeptOptions = res.deptlist
        this.searchForm.organcode = res.deptId
      }).catch(res => {
      })
    },
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      searchPublic() {
        //获取公共池
        let query = this.searchForm
        query.orderByColumn = this.$refs.publicTable.prop
        query.isAsc = this.$refs.publicTable.order
        getPublicList(query).then(res => {
          this.publicData = res.rows
          this.publicTotal = res.total
        }).finally(() => {
          this.loading = false
        })

      },
      searchHandle() {
        const params = {
          pageNum: this.activeName === '01' ? this.backNum : this.dealNum,
          pageSize: this.activeName === '01' ? this.backSize : this.dealSize,
          orderByColumn: this.activeName === '01' ? this.$refs.personTable1.prop : this.$refs.personTable2.prop,
          isAsc: this.activeName === '01' ? this.$refs.personTable1.order : this.$refs.personTable2.order,
        }
        if (this.activeName === '01') {
          //获取直结复核理赔批次待处理个人池
          getUntreatedList(params).then(res => {
            this.backData = res.rows
            this.backTotal = res.total
          }).finally(() => {
            this.loading = false
          })
        } else {
          //获取直结复核公共池获取到未处理个人池
          getProcessedList(params).then(res => {
            this.dealData = res.rows
            this.dealTotal = res.total
          }).finally(() => {
            this.loading = false
          })
        }
        //获取公共池
        getPublicList(this.searchForm).then(res => {
          this.publicData = res.rows
          this.publicTotal = res.total
        }).finally(() => {
          this.loading = false
        })
      },
      searchTable() {
        const params1 = {
          pageNum: this.activeName === '01' ? this.backNum : this.dealNum,
          pageSize: this.activeName === '01' ? this.backSize : this.dealSize,
          orderByColumn:this.$refs.personTable1.prop,
          isAsc:  this.$refs.personTable1.order,
        }
        const params2 = {
          pageNum: this.activeName === '01' ? this.backNum : this.dealNum,
          pageSize: this.activeName === '01' ? this.backSize : this.dealSize,
          orderByColumn:this.$refs.personTable2.prop,
          isAsc:this.$refs.personTable2.order,
        }
        //获取直结复核理赔批次待处理个人池
        getUntreatedList(params1).then(res => {
          this.backData = res.rows
          this.backTotal = res.total
        }).finally(() => {
          this.loading = false
        })
        //获取直结复核公共池获取到未处理个人池
        getProcessedList(params2).then(res => {
          this.dealData = res.rows
          this.dealTotal = res.total
        }).finally(() => {
          this.loading = false
        })
      },
      batchGet() {
        //调用子组件的方法
        this.$refs.publicTable.handle()
      },
      search() {
        const query = {
          pageNum: this.searchForm.pageNum,
          pageSize: this.searchForm.pageSize,
          submitstartdate: undefined,
          submitenddate: undefined,
          updatestartTime: undefined,
          updateendTime: undefined,
          organcode: this.searchForm.organcode,
          hospitalname: this.searchForm.hospitalname,
          batchno: this.searchForm.batchno,
        }
        if (this.searchForm.submitdate) {
          query.submitstartdate = this.searchForm.submitdate[0]
          query.submitenddate = this.searchForm.submitdate[1]
        }
        if (this.searchForm.updateTime) {
          query.updatestartTime = this.searchForm.updateTime[0]
          query.updateendTime = this.searchForm.updateTime[1]
        }
        //获取公共池
        getPublicList(query).then(res => {
          this.publicData = res.rows
          if (res.rows.length <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }).finally(() => {
          this.loading = false
        })
      },
      //清单导出
      listExport() {
        if (this.activeName === '02') {//已处理
          getUntreatedList().then(res => {
            if (res.rows.length > 0) {
              this.isListExport = true
              this.download('claimapt/batch/exportPersonalUntreated', {}, `FYX_${new Date().getTime()}.xlsx`)
            } else {
              return this.$message.warning(
                "没有查询到能导出的数据！"
              )
            }
          }).finally(() => {
          })
        } else {//待处理
          getProcessedList().then(res => {
            if (res.rows.length > 0) {
              this.isListExport = true
              this.download('claimapt/batch/exportPersonalProcessed', {}, `FYX_${new Date().getTime()}.xlsx`)
            } else {
              return this.$message.warning(
                "没有查询到能导出的数据！"
              )
            }
          }).finally(() => {
            this.loading = false
          })
        }
      },
      open() {
        this.collapsed = !this.collapsed
        this.divShow = !this.divShow
      },
      remoteMethod(query) {
        let data={
          deptName:query,
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
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
