<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院名称：" prop="chname1">
              <el-input v-model="formSearch.chname1" class="item-width" clearable size="mini"
                        placeholder="请输入(模糊查询)"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属地区：" prop="address">
              <el-cascader
                v-model="formSearch.address"
                :props="{ checkStrictly: true }"
                :options="regions"
                clearable
                class="item-width"
                @change="handleChange"/>
            </el-form-item>
          </el-col>

<!--          <el-col :span="8">
            <el-form-item label="状态：" prop="status">
              <el-select v-model="formSearch.status" class="item-width" placeholder="请选择(有效/无效)" clearable>
                <el-option v-for="item in bussiness_statusOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>-->


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
        <el-table
          v-loading="loading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          element-loading-text="拼命加载中"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" min-width="50" type="selection" width="120px"></el-table-column>
          <el-table-column label="医院编码" prop="providerCode" align="center"/>
          <el-table-column label="医院名称" prop="chname1" align="center"/>
          <el-table-column label="所属地区" prop="addressdetail" align="center"/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">审核</el-button>
              <!--              <el-button type="text" size="mini" style="color: #f00;" @click="delHandle(scope.row, scope.$index)">删除</el-button>-->
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="params.pageNum"
          :limit.sync="params.pageSize"
          @pagination="getData"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import {getCheckUpList, getAddress} from '@/api/baseInfo/medicalManage.js'

export default {
  data() {
    return {
      districtList: [],
      cityList: [],
      provinceList: [],
      formSearch: {

      },
      params: {
        pageNum: 1,
        pageSize: 10,
        chname1: '',
        type: '',
        province: '',
        city: '',
        district: '',
        address: '',
      },

      regions: [],
      tableData: [],
      totalCount: 0,
      loading: false,
      hospitaltypeOptions: [],
      bussiness_statusOptions: []
    }
  },
  created() {
   /* this.getProvinceList()*/
    // 所属地
    this.getAddressData()
    this.getData()
    this.getDicts("hospitaltype").then(response => {
      this.hospitaltypeOptions = response.data;
    });
    this.getDicts("bussiness_status").then(response => {
      this.bussiness_statusOptions = response.data;
    });

  },
  methods: {

    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },
    handleChange(value) {
    },
    searchHandle() {
      this.params.pageNum = 1
      this.params.pageSize = 10
      if(this.formSearch.chname1 ||this.formSearch.address ||this.formSearch.status ){
        this.getData()
      }
      else{
        this.$message({message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true})
      }
     /* const values = Object.values(this.formSearch)
      values.some(item => {
        return item != ''
      }) ? this.getData() : this.$message({message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true})*/
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
      //调用查询接口
      getCheckUpList(this.params).then(res => {
        this.tableData = res.rows;
        this.totalCount = res.total;
        if(this.totalCount===0){
          this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
        }
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })

    },
    // 重置
    resetForm() {
      this.$refs.searchForm.resetFields()
      this.formSearch={}
    },

    updateHandle(row) {
      this.$router.push({
        path: '/basic-info/hospitalReview/edit',
        query: {providerCode: row.providerCode
        }
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
.el-form-item /deep/ label {
  font-weight: normal;
}

/deep/ .el-table__header-wrapper .el-checkbox__input::after {
  content: '全选';
  position: absolute;
  font-weight: bolder;
  margin-left: 5px;
}
</style>

