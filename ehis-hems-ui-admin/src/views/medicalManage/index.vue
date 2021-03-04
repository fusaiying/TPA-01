<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="医疗机构编码：" prop="hospitalcode">
              <el-input v-model="formSearch.hospitalcode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构名称：" prop="hospitalname">
              <el-input v-model="formSearch.hospitalname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构类型：" prop="hospitaltype">
              <el-select v-model="formSearch.hospitaltype" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in dict.hospitaltype" :label="item.label" :value="item.value" :key="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属地区：" prop="address">
              <el-cascader
                v-model="formSearch.address"
                :props="{ checkStrictly: true }"
                :options="regions"
                class="item-width"
                @change="handleChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构等级：" prop="leval">
              <el-select v-model="formSearch.leval" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="state">
              <el-select v-model="formSearch.state" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in dict.hospital_state" :label="item.label" :value="item.value" :key="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>医疗机构列表（{{ totalCount }}）</span>
          <el-button icon="el-icon-plus" style="float: right; margin-top: 10px;" type="primary" size="mini" @click="editHandle">新增</el-button>
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
          <el-table-column label="医疗机构编码" prop="hospitalcode" align="center"/>
          <el-table-column label="医疗机构名称" prop="hospitalname" align="center"/>
          <el-table-column label="所属地区" prop="address" align="center"/>
          <el-table-column label="类型" prop="hospitaltype" align="center">
            <template slot-scope="scope">
              <span>
                {{ scope.row.hospitaltype | getHospitalTypeName }}
              </span>
            </template>
          </el-table-column>
          <el-table-column label="医院等级" prop="hospitallevel" align="center"/>
          <el-table-column label="经营方式" prop="managementmode" align="center"/>
          <el-table-column label="状态" prop="state" align="center" >
            <template slot-scope="scope">
              {{ scope.row.state | getStateName }}
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">编辑</el-button>
              <el-button type="text" size="mini" style="color: #f00;" @click="delHandle(scope.row, scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog
          :visible.sync="dialogVisible"
          :modal="modalValue"
          :close-on-click-modal="false"
          title="提示"
          width="30%">
          <span>{{ '是否确认删除此医疗机构？' }}</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="delConfirm">确 定</el-button>
          </span>
        </el-dialog>
        <!--分页组件-->
        <div>
          <el-pagination
            :total="totalCount"
            :current-page="pageInfo.page"
            :page-size="pageInfo.pageSize"
            :page-sizes="[10, 20, 30, 40]"
            style="margin-top: 8px; text-align: right;"
            layout="sizes, prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getList, getAddress, deleteOne } from '@/api/baseInfo/medicalManage.js'

export default {
  dicts: ['hospitaltype', 'hospitallevel', 'hospital_state'],
  filters: {
    getHospitalTypeName(code) {
      switch (code) {
        case '01' : return '部队医院'
        case '02' : return '齿科诊所'
      }
    },
    getStateName(code) {
      switch (code) {
        case '00' : return '黑名单'
        case '01' : return '有效'
        case '02' : return '无效'
      }
    }
  },
  data() {
    return {
      formSearch: {},
      params: {
        city: '',
        district: '',
        hospitalcode: '',
        hospitallevel: '',
        hospitalname: '',
        hospitaltype: '',
        pageno: 1,
        pagesize: 10,
        province: '',
        state: '01'
      },
      address: [],
      regions: [],
      tableData: [],
      totalCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      typeName: '',
      loading: false,
      dialogVisible: false,
      hospitalcode: '',
      index: null,
      modalValue: false
    }
  },
  created() {
    this.getData()
    // 所属地
    this.getAddressData()
  },
  methods: {
    getAddressData() {
      getAddress().then(response => {
        this.regions = response.data
      }).catch(error => {
      })
    },
    handleChange(value) {
    },
    searchHandle() {
      this.params.pageno = 1
      this.params.pagesize = 10
      const values = Object.values(this.formSearch)
      values.some(item => { return item !== '' }) ? this.getData() : this.$message({ message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true })
    },
    getData() {
      for (const i in this.formSearch) {
        this.params[i] = this.formSearch[i]
      }
      if (this.formSearch.address) {
        this.params.province = this.formSearch.address[0]
        this.params.city = this.formSearch.address[1]
        this.params.district = this.formSearch.address[2]
      }
      this.loading = true
      getList(this.params).then(res => {
        if (res.data.total === 1) {
          this.tableData = res.data.list
          this.totalCount = res.data.total
        }
        this.loading = false
      }).catch(res => {
        this.loading = false
      })
    },
    // 重置
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    delConfirm() {
      this.dialogVisible = false
      deleteOne(this.hospitalcode).then(res => {
        this.tableData.splice(this.index, 1)
        if (res.status === '1') {
          this.$message({
            message: '删除成功！',
            type: 'success',
            center: true,
            showClose: true
          })
        } else if (res.status === '0') {
          this.$message({
            message: '删除失败,' + res.data.errMsg,
            type: 'error',
            center: true,
            showClose: true
          })
        }
      })
    },
    delHandle(row, index) {
      this.dialogVisible = true
      this.hospitalcode = row.hospitalcode
      this.index = index
    },
    handleSizeChange(val) {
      this.pageInfo.pageSize = val
      this.params.pagesize = val
      this.getData()
    },
    // 分页
    handleCurrentChange(val) {
      this.pageInfo.page = val
      this.params.pageno = val
      this.getData()
    },
    editHandle(row) {
      this.$router.push({
        path: '/basic-info/medical/edit',
        isEmpty: false
      })
    },
    updateHandle(row) {
      this.$router.push({
        path: '/basic-info/medical/edit',
        query: { hospitalcode: row.hospitalcode, isEmpty: true }
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
