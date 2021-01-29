<!--供应商列表-->
<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="130px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="chname">
              <el-input v-model="searchForm.chname" class="item-width" size="mini" placeholder="请输入"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应商类型：" prop="servcomType">
              <el-select v-model="searchForm.servcomType" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="item in supplier_typeOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属地区：">
              <el-cascader :options="regions" v-model="region" class="item-width" placeholder="请选择"
                           @change="handleChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus">
              <el-select v-model="searchForm.bussinessStatus" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="item in accountacc_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应商编码：" prop="serialNo">
              <el-input v-model="searchForm.serialNo" class="item-width" size="mini" placeholder="请输入"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="success" icon="el-icon-search" @click="initData">查 询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重 置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>供应商列表信息（{{ totalCount }}）</span>
          <span style="float: right;">
            <el-button icon="el-icon-plus" type="primary" size="mini" @click="addSupplier('add')">新增</el-button>
          </span>
        </div>
        <el-table
          :data="pendingTableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="serialNo" label="供应商编码" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="chname" label="供应商名称" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="servcomType" label="供应商类型" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(supplier_typeOptions, scope.row.servcomType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="addressdetail" label="所属地区" align="center" show-overflow-tooltip/>
          <el-table-column prop="bussinessStatus" label="状态" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(accountacc_statusOptions, scope.row.bussinessStatus)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="editClick(scope.row.serialNo,'edit')">编辑
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="searchForm.pageNum"
          :limit.sync="searchForm.pageSize"
          @pagination="search"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {getAddress, listInfo} from '@/api/supplierManager/supplier'

  let dictss = [{dictType: 'linkman_type'}, {dictType: 'account_Types'}, {dictType: 'accountacc_status'}, {dictType: 'service_type'}, {dictType: 'supplier_type'},]
  export default {
    data() {
      return {
        regions: [], // 省市区下拉选项
        region: [], // 当前选中的省市区  '110000','110100','110105'
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          province: undefined, // 省
          city: undefined, // 市
          district: undefined, // 区
          servcomType: undefined,
          chname: undefined,
          enname: undefined,
          bussinessStatus: undefined,
          serialNo: undefined,
          orderByColumn:'create_time',
          isAsc:'desc'
        },
        totalCount: 0,
        pendingTableData: [],
        loading: true,
        pendingTotalNum: 0,
        dictList: [],
        dataOnLineListSelections: [],
        linkman_typeOptions: [],
        accountTypeOptions: [],
        accountacc_statusOptions: [],
        service_typeOptions: [],
        supplier_typeOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.linkman_typeOptions = this.dictList.find(item => {
        return item.dictType === 'linkman_type'
      }).dictDate
      this.accountTypeOptions = this.dictList.find(item => {
        return item.dictType === 'account_Types'
      }).dictDate
      this.accountacc_statusOptions = this.dictList.find(item => {
        return item.dictType === 'accountacc_status'
      }).dictDate
      this.service_typeOptions = this.dictList.find(item => {
        return item.dictType === 'service_type'
      }).dictDate
      this.supplier_typeOptions = this.dictList.find(item => {
        return item.dictType === 'supplier_type'
      }).dictDate
      listInfo(this.searchForm).then(res => {
        if (res != null && res.code === 200) {
          this.pendingTableData = res.rows
          this.totalCount = res.total
          this.loading = false
        }
      })
      this.getAddressData()
    },
    methods: {
      // 地址下拉选
      getAddressData() {
        getAddress().then(response => {
          this.regions = response
        }).catch(error => {
          console.log(error)
        })

      },
      handleChange(value) {
        this.searchForm.province = value[0]
        this.searchForm.city = value[1]
        this.searchForm.district = value[2]
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
        this.searchForm.province=''
        this.searchForm.city=''
        this.searchForm.district=''
        this.searchForm.orderByColumn='create_time'
        this.searchForm.isAsc='desc'

        this.region = []
      },
      initData() {
        this.searchForm.enname = this.searchForm.chname
        listInfo(this.searchForm).then(res => {
          if (res != null && res.code === 200) {
            this.pendingTableData = res.rows
            this.totalCount = res.total
            if (res.rows.length < 1) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res=>{})

      },
      search() {
        listInfo(this.searchForm).then(res => {
          if (res != null && res.code === 200) {
            this.pendingTableData = res.rows
            this.totalCount = res.total
            if (res.rows.length < 1) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        })
      }
      ,
      addSupplier(status) {
        const data = encodeURI(JSON.stringify({
          serialNo: '',
          status: status
        }))
        this.$router.push({
          path: 'addEdits',
          query: {
            data
          }
        })
      }
      ,
      editClick(value, status) {
        if (value === null || value === '') {
          alert('供应商编码为空')
        }
        const data = encodeURI(JSON.stringify({
          serialNo: value,
          status: status
        }))
        this.$router.push({
          path: 'addEdits',
          query: {
            data
          }
        })
      }
      ,
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

</style>
