<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="queryParams" style="padding-bottom: 30px;" label-width="130px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="费用项编码：" prop="feeitemcode">
              <el-input v-model="queryParams.feeitemcode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="费用项中文名称：" prop="feeitemname">
              <el-input v-model="queryParams.feeitemname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button

            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询
          </el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>费用项列表（{{ totalCount }}）</span>
          <span style="float: right;">
            <el-button icon="el-icon-plus" type="primary" size="mini" @click="addFeeitem">新增</el-button>
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-table
          v-loading="loading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          element-loading-text="拼命加载中"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column label="费用项编码" prop="feeitemcode" align="center" show-overflow-tooltip/>
          <el-table-column label="费用项中文名称" prop="feeitemname" align="center" show-overflow-tooltip/>
          <el-table-column label="创建日期" prop="createTime" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{parseTime(scope.row.createTime, '{y}-{m}-{d}')}}</span>
            </template>
          </el-table-column>
          <el-table-column label="创建人" prop="createBy" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateEditHandle(scope.row)">编辑
              </el-button>
              <!--<el-button type="text" size="mini" style="color: #f00;" @click="delHandle(scope.row, scope.$index)">删除</el-button>-->
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getData"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {listFeeitem} from '@/api/baseInfo/expenseitemMaintenan'

  export default {
    data() {
      return {
        isListExport:false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          feeitemcode: '',
          feeitemname: '',
          status: undefined
        },
        tableData: [],
        totalCount: 0,
        pageInfo: {
          page: 1,
          pageSize: 10
        },
        loading: true,
        dialogVisible: false,
        modalValue: false,
        feeitemcode: '',
        index: ''
      }
    },
    mounted() {
      listFeeitem(this.queryParams).then(res => {
        this.tableData = res.rows
        this.totalCount = res.total
        this.loading = false
      }).catch(res => {
        this.loading = false
      })
    },
    methods: {
      // 查询
      searchHandle() {
        this.queryParams.pageNum = 1
        this.queryParams.pageSize = 10
        if ((this.queryParams.feeitemcode===null || this.queryParams.feeitemcode==='') && (this.queryParams.feeitemname===null || this.queryParams.feeitemname==='')){
          return this.$message.warning(
            "至少输入一个查询条件！"
          )
        }else {
          this.getData()
        }

      },
      // 获取数据
      getData() {
        this.loading = true
        listFeeitem(this.queryParams).then(res => {
          this.tableData = res.rows
          this.totalCount = res.total
          this.loading = false
          if (res.rows.length<=0){
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }).catch(res => {
          this.loading = false
        })
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      delHandle(row, index) {
        this.dialogVisible = true
        this.feeitemcode = row.feeitemcode
        this.index = index
      },
      handleSizeChange(val) {
        this.pageInfo.pageSize = val
        this.formSearch.pagesize = val
        this.getData()
      },
      handleCurrentChange(val) {
        this.pageInfo.page = val
        this.formSearch.pageno = val
        this.getData()
      },
      addFeeitem() {
        this.$router.push('/basic-info/expense/edit')
      },
      updateEditHandle(row) {
        this.$router.push({
          path: '/basic-info/expense/edit',
          query: {feeitemcode: row.feeitemcode, feeitemname: row.feeitemname}
        })
      },
      //清单导出
      listExport() {
        let query ={
          feeitemcode: this.queryParams.feeitemcode,
          feeitemname:  this.queryParams.feeitemname,
        }
        let data={
          pageNum: 1,
          pageSize: 10,
        }
        listFeeitem(data).then(res => {
          if (res.rows.length>0){
            this.isListExport=true
            this.download('provider/feeitem/export', {
            }, `feeitem_${new Date().getTime()}.xlsx`).catch(res=>{
              this.$message({
                message: res,
                type: 'error',
                center: true,
                showClose: true
              })
            })
          }else {
            return this.$message.warning(
              "没有查询到能导出的数据！"
            )
          }
        }).catch(res => {
          this.loading = true
        })
      }
    }
  }
</script>
<style scoped>
  .item-width {
    width: 200px;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }
</style>
