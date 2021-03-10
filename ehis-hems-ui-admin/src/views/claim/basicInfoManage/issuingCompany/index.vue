<template>
  <div class="app-container">
    <el-card class="box-card">

      <el-form ref="searchForm" :model="queryParams" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>查询条件</span>
        </div>
        <el-row>
          <el-col :span="8">
            <el-form-item label="出单公司编码：" prop="companycode">
              <el-input v-model="queryParams.companycode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司名称：" prop="companyname">
              <el-input v-model="queryParams.companyname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司简称：" prop="simplename">
              <el-input v-model="queryParams.simplename" class="item-width" clearable size="mini" placeholder="请输入"/>
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
          <span>出单公司列表（{{ totalCount }}）</span>
          <el-button icon="el-icon-plus" style="float: right; margin-top: 10px;" type="primary" size="mini"
                     @click="addCompany">新增
          </el-button>
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
          <el-table-column label="出单公司编码" prop="companycode" align="center" show-overflow-tooltip/>
          <el-table-column label="出单公司名称" prop="companyname" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.companyname}} | {{scope.row.simplename}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作日期" prop="updateTime" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{parseTime(scope.row.updateTime, '{y}-{m}-{d}')}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作人" prop="updateBy" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="editCompany(scope.row)">编辑</el-button>
              <el-button type="text" size="mini" :class="{blueClass:scope.row.flag==false,redClass:scope.row.flag==true}" @click="serviceRuleDefin(scope.row)">规则
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <div>
          <pagination
            v-show="totalCount>0"
            :total="totalCount"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getData"
          />
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import {listIssuingcompany} from '@/api/baseInfo/issuingCompany.js'

export default {
  data() {
    return {
      blueClass:'blueClass',
      redClass:'redClass',
      loading: true,
      formSearch: {},
      totalCount: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companycode: undefined,
        companyname: undefined,
        simplename: undefined
      },
      tableData: []
    }
  },
  mounted() {
    this.searchHandle()
  },
  created() {

  },
  methods: {
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 10
      this.getData()
    },
    getData() {
      this.loading = true
      listIssuingcompany(this.queryParams).then(res => {
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
    serviceRuleDefin(row) {
      this.$router.push({
        path: '/basic-info/serviceRuleDefin',
        query: {companycode: row.companycode, companyname: row.companyname}
      })
    },
    editCompany(row) {
      this.$router.push({
        path: '/basic-info/companyEdit',
        query: {companycode: row.companycode, companyname: row.companyname}

      })
    },
    addCompany(row) {
      this.$router.push({
        path: '/basic-info/companyEdit',
        isEmpty: false
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
  .blueClass{
    color: #1890ff;
  }
  .redClass{
    color: #f00;
  }
</style>
