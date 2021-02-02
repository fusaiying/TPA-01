<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="hospitalcode">
              <el-input v-model="formSearch.hospitalcode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人：" prop="hospitalname">
              <el-input v-model="formSearch.hospitalname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="hospitalname">
              <el-input v-model="formSearch.hospitalname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="hospitalname">
              <el-input v-model="formSearch.hospitalname" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="制卡标记：" prop="leval">
              <el-select v-model="formSearch.leval" class="item-width" placeholder="请选择" clearable>
                <!--                <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
                <el-option label="测试" value="测试"/>
                <el-option label="测试" value="测试"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否已制卡：" prop="state">
              <el-select v-model="formSearch.state" class="item-width" placeholder="请选择" clearable>
                <el-option label="测试" value="测试"/>
                <el-option label="测试" value="测试"/>
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
          >查询
          </el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>直结卡信息（{{ totalCount }}）</span>
          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="editHandle">清单导出
          </el-button>
        </div>
        <el-table
          :data="tableData"
          ref="policytable"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          @selection-change="handleExpendRow"
          style="width: 100%;">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table :data="minData"
                        :header-cell-style="{color:'black',background:'#f8f8ff'}"
                        highlight-current-row
                        size="small"
                        tooltip-effect="dark"
                        style="width: 100%;">
                <el-table-column prop="minCode" label="责任编码" align="center"/>
                <el-table-column prop="minName" label="责任名称" align="center"/>
                <el-table-column prop="minType" label="直结类型" align="center"/>
                <el-table-column prop="minDate"label="等待期" align="center"/>
                <el-table-column prop="minProportion"label="赔付比例" align="center"/>
                <el-table-column prop="deductible" label="免赔额(已用/总额)" align="center"/>
                <el-table-column prop="coverage" label="保额(已用/总额)" align="center"/>
                <el-table-column prop="coverage" label="操作" align="center"/>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column label="保单号" prop="guarantee" align="center"/>
          <el-table-column label="分单号" prop="lading" align="center"/>
          <el-table-column label="保障计划" prop="hospitalcode" align="center"/>
          <el-table-column label="产品名称" prop="hospitalcode" align="center"/>
          <el-table-column label="有效日期" prop="hospitalcode" align="center"/>
          <el-table-column label="被保人姓名" prop="hospitalname" align="center"/>
          <el-table-column label="客户号" prop="address" align="center"/>
          <el-table-column label="投保人" prop="hospitallevel" align="center"/>
          <el-table-column label="承保单位" prop="managementmode" align="center"/>
          <el-table-column label="制卡次数" prop="managementmode" align="center"/>
          <el-table-column label="保单类型" prop="managementmode" align="center"/>
          <el-table-column label="直结卡状态" prop="managementmode" align="center"/>
          <el-table-column label="操作" align="center" width="155px" >
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">编辑
              </el-button>
              <el-button type="text" size="mini" style="color: #1890ff;"
                         @click="delHandle(scope.row, scope.$index)">直结医院
              </el-button>
              <el-button type="text" size="mini" style="color: #1890ff;"
                         @click="delHandle(scope.row, scope.$index)">白名单
              </el-button>
            </template>
          </el-table-column>
        </el-table>
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
  import {getList, getAddress, deleteOne} from '@/api/baseInfo/medicalManage.js'

  export default {
    dicts: ['hospitaltype', 'hospitallevel', 'hospital_state'],
    filters: {
      getHospitalTypeName(code) {
        switch (code) {
          case '01' :
            return '部队医院'
          case '02' :
            return '齿科诊所'
        }
      },
      getStateName(code) {
        switch (code) {
          case '00' :
            return '黑名单'
          case '01' :
            return '有效'
          case '02' :
            return '无效'
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
        tableData: [{
          guarantee:'A321654',
          lading:'A3251654',
        },{
          guarantee:'A321654',
          lading:'A3251654',
        }],
        minData:[{
          minCode:'S15416546516',
          minName:'测试',
        },{
          minCode:'S15416546516',
          minName:'测试',
        },],
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
        values.some(item => {
          return item !== ''
        }) ? this.getData() : this.$message({message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true})
      },
      getData() {

      },
      // 重置
      resetForm() {
        this.$refs.searchForm.resetFields()
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
          query: {hospitalcode: row.hospitalcode, isEmpty: true}
        })
      },
      handleExpendRow(row, expandedRows) { //这里是点击每一行会触发的方法
        /*if (!row.dicts) { //这里做了一个判断，首先判断这一行的数据对象有没有dicts这个属性，如果没说明没有数据我们需要请求后台，相当于懒加载
          this.queryDictData(row.id, row.labelType) //关键就是这个方法，row.id是父分组的id需要传给后台查询该子分组的信息
        }*/

      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }
  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

</style>
