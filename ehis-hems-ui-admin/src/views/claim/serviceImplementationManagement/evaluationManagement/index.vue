<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户姓名：" prop="name">
              <el-input v-model="formSearch.name" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机号：" prop="phone">
              <el-input v-model="formSearch.phone" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="formSearch.policyNo" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件类型：" prop="idType">
              <el-select v-model="formSearch.idType" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="item in card_typeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idCode">
              <el-input v-model="formSearch.idCode" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="服务项目名称：" prop="serviceCode">
              <el-select v-model="formSearch.serviceCode" class="item-width" clearable filterable>
                <el-option v-for="item in serviceInfo" :label="item.serviceName" :value="item.serviceCode"
                           :key="item.serviceCode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="supplierCode">
              <!--              <el-input v-model="formSearch.supplierName" class="item-width" clearable size="mini"
                                      placeholder="请输入"/>
                          </el-form-item>-->
              <el-select v-model="formSearch.supplierCode" class="item-width" placeholder="请选择" clearable filterable>
                <el-option v-for="item in supplierNameList" :label="item.supplierCode+'-'+item.supplierName" :value="item.supplierCode"
                           :key="item.supplierCode"/>
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
          <span>评价信息列表</span>
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
<!--          <el-table-column align="center" min-width="50" type="selection" width="120px"></el-table-column>-->
          <el-table-column label="产品名称" prop="productName" align="center" show-overflow-tooltip/>
          <el-table-column label="服务项目名称" prop="serviceName" align="center" show-overflow-tooltip/>
          <el-table-column label="供应商名称" prop="supplierName"  align="center"
                           show-overflow-tooltip/>
          <el-table-column label="客户姓名" prop="name" align="center" show-overflow-tooltip/>
          <el-table-column label="手机号" prop="phone" align="center" show-overflow-tooltip/>
          <el-table-column label="客户评价" prop="evaluationContent" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="openHandle(scope.$index,scope.row)">
                详情
              </el-button>

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
      title="客户评价信息"
      width="600px">
      <el-form ref="remarkForm" :model="remarkForm" size="mini" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="评价：">
              <el-radio-group v-model="remarkForm.customerEvaluation" disabled>
                <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in customerEvaluationOptions ">
                  {{ item.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item prop="reason" style="position:relative;width: 500px" label="评价内容" >
              <el-input type="textarea" :rows="7" v-model="remarkForm.evaluationContent" disabled
                        class="item-widths" clearable
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="dialogVisable=false">关闭</el-button>
      </div>

    </el-dialog>
  </div>

</template>

<script>
import {getEvaluateInfo, getSupplierInfo} from '@/api/serviceProductManage/serviceImplManage'
import {getServiceInfo} from "@/api/productManage/serviceProductManagement";


export default {
  data() {
    return {
      dialogVisable: false,
      serviceInfo: [],
      remarkForm: {
        customerEvaluation:'',
        evaluationContent:''
      },
      index: '',
      modalValue: false,
      dialogVisible: false,
      formSearch: {
        name: '',
        phone: '',
        policyNo: '',
        idType: '',
        idCode: '',
        serviceCode: '',
        supplierCode: ''
      },
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        phone: '',
        policyNo: '',
        idType: '',
        idCode: '',
        serviceCode: '',
        supplierCode: ''
      },


      tableData: [],
      totalCount: 0,
      loading: false,
      supplierNameList:[],
      card_typeOptions: [],
      customerEvaluationOptions: [],

      productTypeOptions: [],

      product_bussiness_statusOptions: [],
      productTimeTimeOptions: []
    }
  },
  created() {
    //获取产品评价信息
    this.init()
    this.getSupplierList()
    this.getDicts("card_type").then(response => {
      this.card_typeOptions = response.data;
    });
    this.getDicts("customerEvaluation").then(response => {
      this.customerEvaluationOptions = response.data;
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
    init(){
      this.loading = true
      //调用查询接口
      getEvaluateInfo(this.params).then(res => {
        this.tableData = res.rows;
        this.totalCount = res.total;
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })
      //初始化服务项目名称
      getServiceInfo().then(res => {
        this.serviceInfo = res
      })
    },

    openHandle(index, row) {
      this.dialogVisable = true
      this.index = index
      //调用接口
      this.remarkForm.customerEvaluation=row.customerEvaluation
      this.remarkForm.evaluationContent=row.evaluationContent
    },





    //查询
    searchHandle() {
      this.params.pageNum = 1
      this.params.pageSize = 10

      this.getData()
    },
    getData() {
      this.params.name= this.formSearch.name
      this.params.phone= this.formSearch.phone
      this.params.policyNo= this.formSearch.policyNo
      this.params.idType= this.formSearch.idType
      this.params.idCode= this.formSearch.idCode
      this.params.serviceCode= this.formSearch.serviceCode
      this.params.supplierCode= this.formSearch.supplierCode
      this.loading = true
      //调用查询接口
      getEvaluateInfo(this.params).then(res => {
        this.tableData = res.rows;
        this.totalCount = res.total;
        if (this.totalCount === 0) {
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

/deep/ .el-table__header-wrapper .el-checkbox__input::after {
  content: '全选';
  position: absolute;
  font-weight: bolder;
  margin-left: 5px;
}
</style>

