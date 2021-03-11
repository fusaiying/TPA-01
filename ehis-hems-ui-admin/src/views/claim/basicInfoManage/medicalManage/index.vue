<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="服务机构类型：" prop="orgFlag">
              <el-select v-model="formSearch.orgFlag" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in org_flagOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务机构名称：" prop="chname1">
              <el-input v-model="formSearch.chname1" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属地区：" prop="address">
              <el-cascader clearable
                           v-model="formSearch.address"
                           :props="{ checkStrictly: true }"
                           :options="regions"
                           class="item-width"
                           @change="handleChange"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus">
              <el-select v-model="formSearch.bussinessStatus" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in bussiness_statusOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
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
          <el-table-column label="服务机构编码" prop="providerCode" align="center" show-overflow-tooltip/>
          <el-table-column label="服务机构名称" prop="chname1" align="center" show-overflow-tooltip/>
          <el-table-column label="服务机构类型" prop="orgFlag" :formatter="getOrgFlag" align="center" show-overflow-tooltip/>
          <el-table-column label="所属地区" prop="addressdetail" align="center" show-overflow-tooltip/>
          <el-table-column label="状态" prop="bussinessStatus" align="center" :formatter="getBussinessStatus" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">编辑</el-button>
              <el-button type="text" size="mini" style="color: #1890ff;" @click="viewHandle(scope.row, scope.$index)">查看</el-button>
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
import {getList, getAddress} from '@/api/baseInfo/medicalManage.js'

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
        orgFlag: '',
        province: '',
        city: '',
        district: '',
        address: '',
        bussinessStatus: ''
      },

      regions: [],
      tableData: [],
      totalCount: 0,
      loading: false,
      org_flagOptions: [],
      bussiness_statusOptions: [],
      hosp_statusOptions: []
    }
  },
  created() {
    /* this.getProvinceList()*/
    // 所属地
    this.getAddressData()
    this.init()
    this.getDicts("org_flag").then(response => {
      this.org_flagOptions = response.data;
    });
    this.getDicts("bussiness_status").then(response => {
      this.bussiness_statusOptions = response.data;
    });
    this.getDicts("hosp_status").then(response => {
      this.hosp_statusOptions = response.data;
    });


  },
  methods: {
    init(){
      this.loading = true
      getList(this.params).then(res => {
        this.tableData = res.rows;
        this.totalCount = res.total;
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })
    },
    getOrgFlag(row){
      return this.selectDictLabel(this.org_flagOptions, row.orgFlag)
    },
    getBussinessStatus(row){
      return this.selectDictLabel(this.hosp_statusOptions, row.bussinessStatus)
    },
    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },
    handleChange(value) {
    },
    searchHandle() {
      this.params.pageNum=1
      this.params.pageSize=10
      this.getData()
    },
    getData() {
      if (this.formSearch.address) {
        this.params.province = this.formSearch.address[0]
        this.params.city = this.formSearch.address[1]
        this.params.district = this.formSearch.address[2]
      }
      this.params.orgFlag= this.formSearch.orgFlag
      this.params.chname1= this.formSearch.chname1
      this.params.bussinessStatus= this.formSearch.bussinessStatus
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
        query: {
        }

      })
    },
    updateHandle(row) {
      this.$router.push({
        path: '/basic-info/medical/edit',
        query: {providerCode: row.providerCode,
          orgflag: row.orgFlag,
          status: 'edit',
          node: row.bussinessStatus,
        }
      })

    },
    //查看
    viewHandle(row,index){
      if(row.orgFlag=='01') {
        this.$router.push({
          path: '/basic-info/hospView',
          query: {
            providerCode: row.providerCode

          }
        })
      }
      else if(row.orgFlag=='02') {
        this.$router.push({
          path: '/basic-info/otherView',
          query: {
            providerCode: row.providerCode
          }
        })
      }

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

::v-deep .el-table__header-wrapper .el-checkbox__input::after {
  content: '全选';
  position: absolute;
  font-weight: bolder;
  margin-left: 5px;
}
</style>

