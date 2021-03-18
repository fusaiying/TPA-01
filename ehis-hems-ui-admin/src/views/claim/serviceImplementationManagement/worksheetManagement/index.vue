<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="120px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="supplierCode">
              <el-select v-model="formSearch.supplierCode" class="item-width" placeholder="请选择" clearable filterable>
                <el-option v-for="item in supplierNameList" :label="item.supplierName" :value="item.supplierCode"
                           :key="item.supplierCode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="productCode">
              <el-select v-model="formSearch.productCode" class="item-width"  clearable filterable>
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
        </el-row>
        <el-row>

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
          <span>工单信息列表（{{ totalCount }}）</span>
          <span style="float: right;">
            <el-button type="primary" size="mini" @click="listExport"  >清单导出</el-button>
          </span>
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
          <el-table-column label="产品名称" prop="productChname" align="center" show-overflow-tooltip/>
          <el-table-column label="服务项目" prop="serviceName"  align="center" show-overflow-tooltip/>
          <el-table-column label="供应商名称" prop="chname"  align="center" show-overflow-tooltip/>
          <el-table-column label="客户姓名" prop="name" align="center" show-overflow-tooltip/>
          <el-table-column label="性别" prop="sex" align="center" :formatter="getCsSex" show-overflow-tooltip/>
          <el-table-column label="证件类型" prop="idType" align="center" :formatter="getIdType" show-overflow-tooltip/>
          <el-table-column label="证件号码" prop="idCode" align="center" show-overflow-tooltip/>
          <el-table-column label="手机号码" prop="phone" align="center" show-overflow-tooltip/>
          <el-table-column label="申请日期" prop="applyTime" align="center" show-overflow-tooltip/>
          <el-table-column label="工单状态" prop="bussinessStatus" align="center" :formatter="getBussinessStatusOptions" show-overflow-tooltip/>

          <el-table-column label="操作" align="center" min-width="100" fixed="right">
            <template slot-scope="scope">
              <el-button v-show="scope.row.bussinessStatus =='04' || scope.row.bussinessStatus =='02'? false:true" type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.$index,scope.row)">处理</el-button>
              <el-button v-show="scope.row.bussinessStatus =='04' || scope.row.bussinessStatus =='02'? false:true" type="text" size="mini" style="color: #1890ff;" @click="distribution(scope.$index,scope.row)">分配</el-button>
              <el-button v-show="scope.row.bussinessStatus =='04' || scope.row.bussinessStatus =='02'? false:true" type="text" size="mini" style="color: #1890ff;" @click="cancelOrder(scope.$index,scope.row)">取消工单</el-button>
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
            <el-form-item label="选择供应商：" prop="supplierCode" >
              <el-select v-model="allotForm.supplierCode" class="item-width" placeholder="请选择" clearable style="width: 200px" filterable>
                <el-option v-for="item in supplierProductList" :label="item.supplierName" :value="item.supplierCode"
                           :key="item.supplierCode"/>
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
import {
  getSupplierInfo,
  getList,
  getProductInfo,
  getAllProSuppInfo,
  queryinfo,allocation,cancalOrder,list3Months
} from '@/api/serviceProductManage/serviceImplManage'


export default {
  data() {
    return {
      serviceInfo:{},
      allotDialogVisable: false,
      allotForm: {
        supplierCode: ''
      },
      remarkForm: {
        reason: ''
      },
      dialogVisable:false,
      index: '',
      modalValue: false,
      dialogVisible:false,
      formSearch: {
        supplierCode:'',
        productCode:'',
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
        supplierCode:'',
        productCode:'',
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
      allotFormRules:{supplierCode: [{required:true,message:'供应商不能为空',trigger:'change'}]},

      tableData: [],
      totalCount: 0,
      loading: false,

      supplierProductList:[],
      supplierNameList:[],
      productNameList:[],
      product_bussiness_statusOptions: [],


      worksheetBussinessStatusOptions: [],
      cs_sexOptions: [],
      card_typeOptions: []

    }
  },
  created() {
    //获取工单信息
    this.init()
    //供应商名称list
    this.getSupplierList()
    //服务产品list
    this.getProductList()
    this.getDicts("worksheetBussinessStatus").then(response => {
      this.worksheetBussinessStatusOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.cs_sexOptions = response.data;
    });
    this.getDicts("card_type").then(response => {
      this.card_typeOptions = response.data;
    });

    this.getDicts("product_bussiness_status").then(response => {
      this.product_bussiness_statusOptions = response.data;
    });



  },
  methods: {

    init(){
      this.loading = true
        /*/order/info/list3Months*/
      //调用默认查询接口
      list3Months(this.params).then(res => {
        this.tableData = res.rows;
        this.totalCount = res.total;
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })

    },
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
    distribution(index,row) {
      this.index = index
      this.allotDialogVisable = true
      //调用服务编码查询供应商的接口
      let query = {
        serviceCode: row.serviceCode,
        productCode: row.productCode
      }
      //供应商【】清空
      this.supplierProductList = []
      getAllProSuppInfo(query).then(res => {
        if (res.code == '200') {
          this.supplierProductList = res.data
        }
      })
      //服务信息清空
      this.serviceInfo={}
      let queryData={
        orderCode : row.orderCode
      }
      //查询服务信息
      queryinfo(queryData).then(res => {
        if (res.code == '200') {
          this.serviceInfo = res.data
          //this.baseForm.secondDept=''
          // this.$set(this.baseForm,'secondDept','')

        }
      })

    },
    //取消工单
    cancelOrder(index,row){
      this.index=index
      this.dialogVisable=true
    },



    getBussinessStatusOptions(row){
      return row.detailInfo;
    },
    getCsSex(row){
      return this.selectDictLabel(this.cs_sexOptions, row.sex)
    },
    getIdType(row){
      return this.selectDictLabel(this.card_typeOptions, row.idType)
    },
    getBussinessStatus(row){
      return this.selectDictLabel(this.product_bussiness_statusOptions, row.bussinessStatus)
    },

    //查询
    searchHandle() {
      const values = Object.values(this.formSearch)
      let flag= values.some(item => {return  item!=null && item !='' })

        this.params.pageSize=10
        this.params.pageNum=1
        this.getData()

    },

    getData() {
      this.params.supplierCode= this.formSearch.supplierCode
      this.params.productCode= this.formSearch.productCode
      this.params.name= this.formSearch.name
      this.params.idCode= this.formSearch.idCode
      this.params.phone= this.formSearch.phone
      this.params.policyNo= this.formSearch.policyNo
      this.params.policyCertificateNo= this.formSearch.policyCertificateNo
      this.params.bussinessStatus= this.formSearch.bussinessStatus
      this.params.orderCode= this.formSearch.orderCode
      this.params.applyStartTime= ''
      this.params.applyEndTime= ''
      if(this.formSearch.daterangeArr!=null && this.formSearch.daterangeArr.length==2){
        this.params.applyStartTime= this.formSearch.daterangeArr[0]
        this.params.applyEndTime= this.formSearch.daterangeArr[1]
      }
      this.loading = true
      const values = Object.values(this.formSearch)
      let flag= values.some(item => {return  item!=null && item !='' })
      if(flag) {
        //调用查询接口
        getList(this.params).then(res => {
          this.tableData = res.rows;
          this.totalCount = res.total;
          if (this.totalCount === 0) {
            this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
          }
          this.loading = false;
        }).catch(res => {
          this.loading = false
        })
      }
      else{
        //调用默认查询的接口
        list3Months(this.params).then(res => {
          this.tableData = res.rows;
          this.totalCount = res.total;
          this.loading = false;
        }).catch(res => {
          this.loading = false
        })
      }

    },
    // 重置
    resetForm() {
      this.$refs.searchForm.resetFields()
    },

    saveRemark(){
      this.$refs.remarkForm.validate(valid => {
        if(valid){
          let data={
            orderCode: this.tableData[this.index].orderCode,
            reason: this.remarkForm.reason
          }
          cancalOrder(data).then(res=>{
            if (res !=null && res.code == '200') {
              this.$message({
                message: '取消工单成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.dialogVisable=false
              this.getData()
            } else {
              this.$message({
                message: '取消工单失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })

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
          //供应商编码重新赋值
          this.serviceInfo.supplierCode=this.allotForm.supplierCode
          //找到对应的供应商对象  给联系人  联系电话赋值
          let obj= this.supplierProductList.find(item=>{
            return item.supplierCode==this.allotForm.supplierCode
          })
          //给供应商名称赋值
          this.serviceInfo.chname=obj.supplierName

          this.serviceInfo.phone=obj.contractInfo[0].phone
          this.serviceInfo.contactName=obj.contractInfo[0].contactName

          allocation(this.serviceInfo).then(res=>{
            if (res !=null && res.code == '200') {
              this.$message({
                message: '分配成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.changeAllotDialogVisable()

              this.getData()

            } else {
              this.$message({
                message: '分配失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })
        }
      })

    },
    changeAllotDialogVisable(){
      this.$refs.allotForm.resetFields()
      this.allotDialogVisable=false
    },



    updateHandle(index,row) {
      this.$router.push({
        path: '/implementation/worksheetManagement/edit',
        query: {
          orderCode: row.orderCode,
          status: 'edit',
          nodeStatus: row.nodeStatus,
          serviceCode: row.serviceCode,
          productCode: row.productCode

        }
      })
    },
    //工单导出
    listExport(){
      this.params.pageSize=10
      this.params.pageNum=1
      this.params.supplierCode= this.formSearch.supplierCode
      this.params.productCode= this.formSearch.productCode
      this.params.name= this.formSearch.name
      this.params.idCode= this.formSearch.idCode
      this.params.phone= this.formSearch.phone
      this.params.policyNo= this.formSearch.policyNo
      this.params.policyCertificateNo= this.formSearch.policyCertificateNo
      this.params.bussinessStatus= this.formSearch.bussinessStatus
      this.params.orderCode= this.formSearch.orderCode
      this.params.applyStartTime= ''
      this.params.applyEndTime= ''
      if(this.formSearch.daterangeArr!=null && this.formSearch.daterangeArr.length==2){
        this.params.applyStartTime= this.formSearch.daterangeArr[0]
        this.params.applyEndTime= this.formSearch.daterangeArr[1]
      }
      let query={}


      getList(this.params).then(res => {

        if (res.rows.length>0){
          const values = Object.values(this.formSearch)
          let flag= values.some(item => {return  item!=null && item !='' })
          if(flag) {
             query=this.params
          }
          else{
            query={}
          }

          this.download('order/info/export', {
            ...query}, `order_${new Date().getTime()}.xlsx`).catch(res=>{
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }else {
          return this.$message.warning(
            "没有查询到能导出的数据！"
          )
        }
      }).catch(res => {

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

