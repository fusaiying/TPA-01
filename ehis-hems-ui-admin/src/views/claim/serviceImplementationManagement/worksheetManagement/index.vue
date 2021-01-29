<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="120px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="supplierName">
              <el-select v-model="formSearch.supplierName" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in supplierNameList" :label="item.supplierName" :value="item.supplierCode"
                           :key="item.supplierCode"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="产品名称：" prop="productName">
              <el-select v-model="formSearch.productName" class="item-width"  clearable >
                <el-option v-for="item in productNameList" :label="item.productName" :value="item.productCode"
                           :key="item.productCode"/>
              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="申请日期："  prop="daterangeArr">
              <el-date-picker
                v-model="formSearch.daterangeArr"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~"
                start-placeholder="开始日期"
                style="width: 220px;"
                end-placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="客户姓名：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idCode">
              <el-input v-model="formSearch.idCode" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机号码：" prop="phone">
              <el-input v-model="formSearch.phone" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号："  prop="policyNo">
              <el-input v-model="formSearch.policyNo" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyCertificateNo">
              <el-input v-model="formSearch.policyCertificateNo" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus">
              <el-select v-model="formSearch.bussinessStatus" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in worksheetBussinessStatusOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工单编号：" prop="orderCode">
              <el-input v-model="formSearch.orderCode" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
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
          <span>工单信息列表</span>
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
          <el-table-column label="工单编号" prop="orderCode" align="center" show-overflow-tooltip/>
          <el-table-column label="产品名称" prop="productName" align="center" show-overflow-tooltip/>
          <el-table-column label="服务项目" prop="serviceName"  align="center" show-overflow-tooltip/>
          <el-table-column label="供应商名称" prop="supplierName"  align="center" show-overflow-tooltip/>
          <el-table-column label="客户姓名" prop="name" align="center" show-overflow-tooltip/>
          <el-table-column label="性别" prop="sex" align="center" :formatter="getCsSex" show-overflow-tooltip/>
          <el-table-column label="证件类型" prop="idType" align="center" :formatter="getIdType" show-overflow-tooltip/>
          <el-table-column label="证件号码" prop="idCode" align="center" show-overflow-tooltip/>
          <el-table-column label="手机号码" prop="phone" align="center" show-overflow-tooltip/>
          <el-table-column label="申请日期" prop="applyTime" align="center" show-overflow-tooltip/>
          <el-table-column label="工单状态" prop="bussinessStatus" align="center" :formatter="getBussinessStatusOptions" show-overflow-tooltip/>

          <el-table-column label="操作" align="center" min-width="100" fixed="right">
            <template slot-scope="scope">
              <el-button v-if="scope.row.bussinessStatus !=='04' ? true:false" type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.$index,scope.row)">处理</el-button>
              <el-button v-if="scope.row.bussinessStatus !=='04' ? true:false" type="text" size="mini" style="color: #1890ff;" @click="distribution(scope.$index,scope.row)">分配</el-button>
              <el-button v-if="scope.row.bussinessStatus !=='04' ? true:false" type="text" size="mini" style="color: #1890ff;" @click="cancelOrder(scope.$index,scope.row)">取消工单</el-button>
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
<!--    分配-->
    <el-dialog
      :visible.sync="allotDialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeAllotDialogVisable"
      title="分配"
      width="500px"
    >
      <el-form ref="allotForm" :model="allotForm"  size="mini" label-width="150px"  :rules="allotFormRules">
        <el-row>
          <el-col :span="24">
            <el-form-item label="选择供应商：" prop="supplierName" >
              <el-select v-model="allotForm.supplierName" class="item-width" placeholder="请选择" clearable style="width: 200px">
                <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="saveAllot">确认</el-button>
        <el-button size="mini" @click="changeAllotDialogVisable">返回</el-button>
      </div>

    </el-dialog>
<!--    取消工单-->
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="取消工单"
      width="600px">
      <el-form ref="remarkForm" :model="remarkForm"  size="mini" label-width="80px" :rules="remarkFormRules">
        <el-row>
          <el-col :span="8">
            <el-form-item prop="reason" style="position:relative;width: 500px" label="取消原因">
              <el-input type="textarea" :rows="7" v-model="remarkForm.reason"
                        class="item-widths" clearable maxlength="1000"
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
import {getSupplierInfo,getList,getProductInfo} from '@/api/serviceProductManage/serviceImplManage'


export default {
  data() {
    return {
      allotDialogVisable: false,
      allotForm: {
        supplierName: ''
      },
      remarkForm: {
        reason: ''
      },
      dialogVisable:false,
      index: '',
      modalValue: false,
      dialogVisible:false,
      formSearch: {
        supplierName:'',
        productName:'',
        daterangeArr: [],
        name:'',
        idCode:'',
        phone:'',
        policyNo:'',
        policyCertificateNo:'',
        bussinessStatus:'',
        orderCode:''

      },
      params: {
        pageNum: 1,
        pageSize: 10,
        supplierName:'',
        productName:'',
        applyStartTime: '',
        applyEndTime: '',
        name:'',
        idCode:'',
        phone:'',
        policyNo:'',
        policyCertificateNo:'',
        bussinessStatus:'',
        orderCode:''
      },
      remarkFormRules:{reason: [{required:true,message:'取消原因不能为空',trigger:'blur'}]},
      allotFormRules:{supplierName: [{required:true,message:'供应商不能为空',trigger:'change'}]},

      tableData: [],
      totalCount: 0,
      loading: false,


      supplierNameList:[],

      productTypeOptions:[],


      product_bussiness_statusOptions: [],
      productTimeTimeOptions:[],

      worksheetBussinessStatusOptions: [],
      cs_sexOptions: [],
      card_typeOptions: []

    }
  },
  created() {
  //获取产品定义信息
    this.getData()
    //供应商名称list
    this.getSupplierList()
    //服务产品list
    this.getProductList()
    this.getDicts("worksheetBussinessStatus").then(response => {
      this.worksheetBussinessStatusOptions = response.data;
    });
    this.getDicts("cs_sex").then(response => {
      this.cs_sexOptions = response.data;
    });
    this.getDicts("card_type").then(response => {
      this.card_typeOptions = response.data;
    });



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
    //供应商名称
    getSupplierList(){
      getSupplierInfo().then(res=>{
            this.supplierNameList=res.data
      })
    },
    //产品名称
    getProductList(){
      getProductInfo().then(res=>{
        this.productNameList=res.data
      })
    },

    //分配
    distribution(index,row){
      this.index=index
      this.allotDialogVisable=true
      //调用服务编码查询供应商的接口
      let query={
        serviceCode: this.tableData[index].serviceName
      }
      //供应商【】清空

    },
    //取消工单
    cancelOrder(index,row){
      this.index=index
      this.dialogVisable=true
    },



    getBussinessStatusOptions(row){
      return this.selectDictLabel(this.worksheetBussinessStatusOptions, row.bussinessStatus)
    },
    getCsSex(row){
      return this.selectDictLabel(this.cs_sexOptions, row.sex)
    },
    getIdType(row){
      return this.selectDictLabel(this.card_typeOptions, row.idType)
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
      this.params.pageNum=1
      this.params.pageSize=10

      this.getData()
    },
    getData() {
      this.params.supplierName= this.formSearch.supplierName
      this.params.productName= this.formSearch.productName
      this.params.name= this.formSearch.name
      this.params.idCode= this.formSearch.idCode
      this.params.phone= this.formSearch.phone
      this.params.policyNo= this.formSearch.policyNo
      this.params.policyCertificateNo= this.formSearch.policyCertificateNo
      this.params.bussinessStatus= this.formSearch.bussinessStatus
      this.params.orderCode= this.formSearch.orderCode

      if(this.formSearch.daterangeArr!=null && this.formSearch.daterangeArr.length==2){
        this.params.applyStartTime= this.formSearch.daterangeArr[0]
        this.params.applyEndTime= this.formSearch.daterangeArr[1]
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
    },

    saveRemark(){
      this.$refs.remarkForm.validate(valid => {
        if(valid){
          let productData=this.tableData[this.index]
          //调用接口
/*          insertMangerInfo(productData).then(res=>{
            if (res !=null && res.code == '200') {
              this.$message({
                message: '操作成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.saveFlag=true
            } else {
              this.$message({
                message: '操作失败!',
                type: 'error',
                center: true,
                showClose: true
              })
              this.tableData[this.checkIndex].bussinessStatus='03'
            }
          })*/
        }
      })
    },

    changeDialogVisable(){
      this.$refs.remarkForm.resetFields()
      this.dialogVisable=false
    },
    saveAllot(){
      this.$refs.allotForm.validate(valid => {
        if (valid) {
          let productData = this.tableData[this.index]
          //调用分配的接口
        }
      })

    },
    changeAllotDialogVisable(){
      this.$refs.allotForm.resetFields()
      this.allotDialogVisable=false
    },



    updateHandle(row) {
      this.$router.push({
        path: '/implementation/worksheetManagement/edit',
        query: {
          orderCode: row.orderCode,
          status: 'edit'
        }
      })
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
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

