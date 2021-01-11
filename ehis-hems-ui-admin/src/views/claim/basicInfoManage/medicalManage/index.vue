<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务机构名称：" prop="chname1">
              <el-input v-model="formSearch.chname1" class="item-width" clearable size="mini"
                        placeholder="请输入(模糊查询)"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务机构类型：" prop="type">
              <el-select v-model="formSearch.type" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in hospitaltypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
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
            <el-form-item label="状态：" prop="status">
              <el-select v-model="formSearch.status" class="item-width" placeholder="请选择(有效/无效)" clearable>
                <el-option v-for="item in hospital_stateOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>


<!--          <el-col :span="24">
            <el-form-item label="所属地区：" prop="address">
&lt;!&ndash;              <el-cascader
                placeholder="省-市-区-县"
                v-model="formSearch.address"
                :props="{ checkStrictly: true }"
                :options="regions"
                class="item-width"
                @change="handleChange"/>&ndash;&gt;
&lt;!&ndash;              this.params.province = this.formSearch.address[0]
              this.params.city = this.formSearch.address[1]
              this.params.district = this.formSearch.address[2]&ndash;&gt;
              <el-select v-model="formSearch.province" clearable filterable placeholder="请选择省" @change="searchProvince">
                <el-option v-for="item in provinceList" :key="item.placecode" :label="item.placename" :value="item.placecode" ></el-option>
              </el-select>
              <el-select v-model="formSearch.city" clearable filterable placeholder="请选择市" @change="searchCity">
                <el-option v-for="item in cityList" :key="item.placecode" :label="item.placename" :value="item.placecode" ></el-option>
              </el-select>
              <el-select v-model="formSearch.district" clearable filterable placeholder="请选择区" >
                <el-option v-for="item in districtList" :key="item.placecode" :label="item.placename" :value="item.placecode" ></el-option>
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
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>服务机构列表</span>
          <el-button icon="el-icon-plus" style="float: right; margin-top: 10px;" type="primary" size="mini"
                     @click="addHandle">新增
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
          <el-table-column align="center" min-width="50" type="selection" width="120px"></el-table-column>
          <el-table-column label="服务机构编码" prop="providerCode" align="center"/>
          <el-table-column label="服务机构名称" prop="chname1" align="center"/>
          <el-table-column label="服务机构类型" prop="typeName" align="center"/>
          <el-table-column label="所属地区" prop="addressdetail" align="center"/>
          <el-table-column label="状态" prop="statusName" align="center">
            <template slot-scope="scope">
              {{ scope.row.statusName  }}
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">编辑</el-button>
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
import {getList, getAddress, getProvince,getCity} from '@/api/baseInfo/medicalManage.js'

export default {
  data() {
    return {
      districtList: [],
      cityList: [],
      provinceList: [],
      formSearch: {},
      params: {
        pageNum: 1,
        pageSize: 10,
        chname1: '',
        type: '',
        province: '',
        city: '',
        district: '',
        address: '',
        status: ''
      },

      regions: [],
      tableData: [],
      totalCount: 0,
      loading: false,
      hospitaltypeOptions: [],
      hospital_stateOptions: []
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
    this.getDicts("hospital_state").then(response => {
      this.hospital_stateOptions = response.data;
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
      const values = Object.values(this.formSearch)
      values.some(item => {
        return item != ''
      }) ? this.getData() : this.$message({message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true})
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

      getList(this.params).then(res => {
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

    addHandle(row) {
      this.$router.push({
        path: '/basic-info/medical/edit',

      })
    },
    updateHandle(row) {
      this.$router.push({
        path: '/basic-info/medical/edit',
        query: {providerCode: row.providerCode,
          organizationFlag: row.organizationFlag

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

