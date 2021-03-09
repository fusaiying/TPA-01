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
                <el-option v-for="item in product_management_statusOptions" :label="item.dictLabel" :value="item.dictValue"
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
          <span>产品发布列表</span>
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
          <el-table-column label="产品编码" prop="productCode" align="center" show-overflow-tooltip/>
          <el-table-column label="产品名称" prop="productChname" align="center" show-overflow-tooltip/>
          <el-table-column label="产品类别" prop="productType" :formatter="getProductType" align="center" show-overflow-tooltip/>
          <el-table-column label="产品限期" prop="productTimeInfo" :formatter="getProductInfo" align="center" show-overflow-tooltip/>
          <el-table-column label="状态" prop="bussinessStatus"  align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.bussinessStatus=='03'" style="padding-right: 6px">产品上线</span>
              <span v-if="scope.row.bussinessStatus=='04'" style="padding-right: 6px" >产品下线</span>

              <el-switch
                v-model="scope.row.bussinessStatus"
                active-value="03"
                inactive-value="04" @change="changeStatus(scope.$index,scope.row)"
              >
              </el-switch>
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
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="产品下线"
      width="600px">
      <el-form ref="remarkForm" :model="remarkForm"  size="mini" label-width="80px" :rules="remarkFormRules">
        <el-row>
          <el-col :span="8">
            <el-form-item prop="reason" style="position:relative;width: 500px" label="下线原因">
              <el-input type="textarea" :rows="7" v-model="remarkForm.reason"
                        class="item-widths" clearable maxlength="200"
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="saveRemark">确认</el-button>
        <el-button size="mini" @click="changeDialogVisable">返回</el-button>
      </div>

    </el-dialog>

  </div>

</template>

<script>
import {mangerList,insertMangerInfo} from '@/api/productManage/serviceProductManagement'


export default {
  data() {
    return {
      remarkForm: {
        reason: ''
      },
      dialogVisable: false,
      formSearch: {},
      checkIndex: '',
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
      remarkFormRules:{reason: [{required:true,message:'下线原因不能为空',trigger:'blur'}]},


      productTypeOptions:[],
      product_management_statusOptions: [],
      productTimeTimeOptions:[]
    }
  },
  created() {
    this.init()
    this.getDicts("product_management_status").then(response => {
      this.product_management_statusOptions = response.data;
    });
    this.getDicts("productType").then(response => {
      this.productTypeOptions = response.data;
    });
    this.getDicts("productTimeTime").then(response => {
      this.productTimeTimeOptions = response.data;
    });

  },
  methods: {
    init(){
      this.loading = true
      //调用查询接口
      mangerList(this.params).then(res => {
        this.tableData = res.rows;
        this.totalCount = res.total;
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })
    },

    getProductType(row){
      return this.selectDictLabel(this.productTypeOptions, row.productType)
    },
    getProductInfo(row){
      return row.productTimeInfo +'/'+this.selectDictLabel(this.productTimeTimeOptions, row.productTimeTime)
    },
    /*    getBussinessStatus(row){
          return this.selectDictLabel(this.product_bussiness_statusOptions, row.bussinessStatus)
        },*/

    changeDialogVisable(){
      this.$refs.remarkForm.resetFields()

     this.tableData[this.checkIndex].bussinessStatus='03'

      this.dialogVisable=false
    },

    saveRemark(){
      this.$refs.remarkForm.validate(valid => {
        if(valid){
          let productData=this.tableData[this.checkIndex]
          insertMangerInfo(productData).then(res=>{
            if (res !=null && res.code == '200') {
              this.$message({
                message: '操作成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.$refs.remarkForm.resetFields()
              this.dialogVisable=false
            } else {
              this.$message({
                message: '操作失败!',
                type: 'error',
                center: true,
                showClose: true
              })
              this.$refs.remarkForm.resetFields()
              this.tableData[this.checkIndex].bussinessStatus='03'
            }
          })
        }
      })
    },

    changeStatus(index,data){
      this.checkIndex=index


      //产品上线
      if(data.bussinessStatus=='03'){
        //跳转至审核页面
        this.$nextTick(() => {
          this.$router.push({
            path: '/service-product/productReview/edit',
            query: {
              productCode: data.productCode,
              status: 'management'
            }
          })
        })



      }
      else {

        this.dialogVisable = true

      }

    },




    //查询
    searchHandle() {
      const values = Object.values(this.formSearch)
      let flag= values.some(item => {return  item!=null && item !='' })
      if(flag){
        this.params.pageSize=10
        this.params.pageNum=1
        this.getData()
      }
      else {
        this.$message({message: '至少输入一个查询条件', type: 'warning', showClose: true, center: true})
      }

    },
    getData() {
      this.params.productCode= this.formSearch.productCode
      this.params.productChname= this.formSearch.productChname
      this.params.productType= this.formSearch.productType
      this.params.bussinessStatus= this.formSearch.bussinessStatus
      this.loading = true
      //调用查询接口
      mangerList(this.params).then(res => {
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

