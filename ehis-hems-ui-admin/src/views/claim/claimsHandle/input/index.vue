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
            <el-form-item label="报案号：" prop="batchNo">
              <el-input v-model="searchForm.batchNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批次号：" prop="rptNo">
              <el-input v-model="searchForm.rptNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="searchForm.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
            searchHandle()"
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <span>个人池</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
        </span>
        <el-divider/>
      </div>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName" >
          <el-tab-pane :label="`处理中(${total})`" name="01">
            <claimsTable ref="claimsTable1" :table-data="pendingTableData" :activeName="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${finishTotal})`" name="02">
            <claimsTable ref="claimsTable2" @init-data="searchHandle" :table-data="completedTableData" :activeName="activeName"/>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <pagination
          v-if="activeName==='01'"
          v-show="total>0"
          :total="total"
          :page.sync="backNum"
          :limit.sync="backSize"
          @pagination="searchHandle"
        />
        <pagination
          v-if="activeName==='02'"
          v-show="finishTotal>0"
          :total="finishTotal"
          :page.sync="dealNum"
          :limit.sync="dealSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'
  import {processingList, accomplishList} from '@/api/claim/input'

  export default {
    name: 'Input',
    components: {
      claimsTable
    },
    data() {
      return {
        isListExport:false,
        total: 0,
        finishTotal: 0,
        backNum: 1,
        backSize: 10,
        dealNum: 1,
        dealSize: 10,
        activeName: '01',
        completedTableData: [],
        pendingTableData: [],
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          batchNo: '',
          rptNo: '',
          name: '',
          orderByColumn: '',
          isAsc: '',
        },
      }
    },
    mounted() {

      //处理中
      processingList(this.searchForm).then(res => {
        if (res != null && res.code === 200) {
          this.pendingTableData = res.rows
          this.total = res.total
        }
      }).catch(res => {

      })
      //已处理
      accomplishList(this.searchForm).then(res => {
        if (res != null && res.code === 200) {
          this.completedTableData = res.rows
          this.finishTotal = res.total
        }
      }).catch(res => {

      })
    },
    computed: {},
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      // 查询
      searchHandle() {
        if (this.activeName === '01') {//处理中
          this.searchForm.pageNum=this.backNum
          this.searchForm.pageSize=this.backSize
          this.searchForm.orderByColumn=this.$refs.claimsTable1.prop
          this.searchForm.isAsc=this.$refs.claimsTable1.order
          processingList(this.searchForm).then(res => {
            if (res != null && res.code === 200) {
              this.pendingTableData = res.rows
              this.total = res.total
              if (res.rows.length<=0){
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })
        } else if (this.activeName === '02') {//已处理
          this.searchForm.pageNum=this.dealNum
          this.searchForm.pageSize=this.dealSize
          this.searchForm.orderByColumn=this.$refs.claimsTable2.prop
          this.searchForm.isAsc=this.$refs.claimsTable2.order
          accomplishList(this.searchForm).then(res => {
            if (res != null && res.code === 200) {
              this.completedTableData = res.rows
              this.finishTotal = res.total
              if (res.rows.length<=0){
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })
        }

      },
      //清单导出
      listExport() {
        //没有数据不导出
        if (this.activeName==='01'){
          processingList(this.searchForm).then(res => {
            if (res.rows.length>0){
              this.isListExport=true
              this.download('system/bill/exportProcess'+'?batchNo='+this.searchForm.batchNo+'&rptNo='+this.searchForm.rptNo
                +'&name='+this.searchForm.name, {
                ...this.searchForm
              }, `FYX_${new Date().getTime()}.xlsx`)
            }else {
              return this.$message.warning(
                "没有查询到能导出的数据！"
              )
            }
          }).catch(res => {

          })
        }else if (this.activeName==='02'){
          accomplishList(this.searchForm).then(res => {
            if (res.rows.length>0){
              this.isListExport=true
              this.download('system/bill/exportAccomplish'+'?batchNo='+this.searchForm.batchNo+'&rptNo='+this.searchForm.rptNo
                +'&name='+this.searchForm.name, {
                ...this.searchForm
              }, `FYX_${new Date().getTime()}.xlsx`)
            }else {
              return this.$message.warning(
                "没有查询到能导出的数据！"
              )
            }
          }).catch(res => {

          })
        }
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  #print-iframe {
    position: absolute;
    width: 0px;
    height: 0px;
    left: -500px;
    top: -500px;
  }

</style>
