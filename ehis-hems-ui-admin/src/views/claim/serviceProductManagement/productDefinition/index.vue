<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编码：" prop="productCode">
              <el-input v-model="formSearch.productCode" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="productChname">
              <el-input v-model="formSearch.productChname" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品类别：" prop="productType">
              <el-select v-model="formSearch.productType" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus">
              <el-select v-model="formSearch.bussinessStatus" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in product_bussiness_statusOptions" :label="item.dictLabel" :value="item.dictValue"
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
          <el-button size="mini"  type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>产品定义列表</span>
          <el-button  style="float: right; margin-top: 10px;" type="primary" size="mini" icon="el-icon-plus"
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
          <el-table-column label="产品编码" prop="productCode" align="center"/>
          <el-table-column label="产品名称" prop="productChname" align="center"/>
          <el-table-column label="产品类型" prop="productType" :formatter="getProductType" align="center"/>
          <el-table-column label="产品限期" prop="productTimeInfo" :formatter="getProductInfo" align="center"/>
          <el-table-column label="状态" prop="bussinessStatus" align="center" :formatter="getBussinessStatus">
<!--            <template slot-scope="scope">
              {{ scope.row.bussinessStatus  }}
            </template>-->
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">编辑</el-button>
                            <el-button v-if="scope.row.bussinessStatus =='01' ? true:false" type="text" size="mini" style="color: #1890ff;" @click="delHandle(scope.$index,scope.row)">删除</el-button>
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
    <el-dialog
      :visible.sync="dialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title="提示"
      width="30%">
      <span>{{ '删除当前行的产品信息？' }}</span>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="delConfirm">确 定</el-button>
        </span>
    </el-dialog>
  </div>

</template>

<script>
import {deleteProductInfo,getList} from '@/api/baseInfo/serviceProductManagement'


export default {
  data() {
    return {
      index: '',
      modalValue: false,
      dialogVisible:false,
      formSearch: {},
      params: {
        pageNum: 1,
        pageSize: 10,
        productCode: '',
        productChname: '',
        productType: '',
        bussinessStatus: ''
      },


      tableData: [],
      totalCount: 0,
      loading: false,



      productTypeOptions:[],

      product_bussiness_statusOptions: [],
      productTimeTimeOptions:[]
    }
  },
  created() {
  //获取产品定义信息
    this.getData()
    this.getDicts("product_bussiness_status").then(response => {
      this.product_bussiness_statusOptions = response.data;
    });
    this.getDicts("productType").then(response => {
      this.productTypeOptions = response.data;
    });
    this.getDicts("productTimeTime").then(response => {
      this.productTimeTimeOptions = response.data;
    });

  },
  methods: {
    delHandle(index, row) {
      this.dialogVisible = true
      this.index = index
    },
    delConfirm(){
      this.dialogVisible = false
      let delProductCode= this.tableData[this.index].productCode
      deleteProductInfo(delProductCode).then(res => {
        if (res !=null && res.code == '200') {
          this.$message({
            message: '删除成功！',
            type: 'success',
            center: true,
            showClose: true
          })
          this.getData()
        } else {
          this.$message({
            message: '删除失败!',
            type: 'error',
            center: true,
            showClose: true
          })
        }
      })
    },

    getProductType(row){
      return this.selectDictLabel(this.productTypeOptions, row.productType)
    },
    getProductInfo(row){
      return row.productTimeInfo +'/'+this.selectDictLabel(this.productTimeTimeOptions, row.productTimeTime)
    },
    getBussinessStatus(row){
      return this.selectDictLabel(this.product_bussiness_statusOptions, row.bussinessStatus)
    },

    //查询
    searchHandle() {
      this.getData()
    },
    getData() {
      this.params.productCode= this.formSearch.productCode
      this.params.productChname= this.formSearch.productChname
      this.params.productType= this.formSearch.productType
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
        path: '/service-product/addEdit',
        query: {
          status: 'add'
        }

      })
    },
    updateHandle(row) {
      this.$router.push({
        path: '/service-product/addEdit',
        query: {productCode: row.productCode,
                status: 'edit'

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

