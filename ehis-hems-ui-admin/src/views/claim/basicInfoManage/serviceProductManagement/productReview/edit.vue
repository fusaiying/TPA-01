<template>
  <div class="app-container">
    <!--产品基本信息-->
    <div >
      <product-info ref="productInfoForm"   :parProductChname="parProductChname" :parProductEnname="parProductEnname" :disableFlag="disabledFlag"
                    :parOutProductChname="parOutProductChname" :parOutProductEnname="parOutProductEnname" :productCode="productCode"
      ></product-info>
    </div>
    <!--服务项目-->
    <div >
      <services-available ref="servicesAvailableForm" :productCode="productCode" :disabledFlag="disabledFlag" ></services-available>
    </div>
<!--供应商信息-->
    <div >
      <el-form ref="supplierInfo"  :model="supplierInfo" size="small" :disabled="disabledFlag">
        <el-card class="box-card " style="margin-top: 10px;">
          <div slot="header" class="clearfix">
          <span>供应商信息</span>
        </div>
        <el-table ref="medicalRecordTable"
                  :data="supplierInfo.supplierData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  style="width: 100%;">

          <el-table-column align="center" min-width="50" type="selection" width="120px"  ></el-table-column>
          <el-table-column key="1" align="center" prop="supplierCode" min-width="150" label="供应商编码"/>
          <el-table-column key="2" align="center" min-width="100" prop="supplierName" label="供应商名称"/>
          <el-table-column key="3" align="center" prop="applicableArea" min-width="150" label="适用区域"/>
          <el-table-column key="4" align="center" min-width="100" prop="contractPeriod" label="合约止期"/>
          <el-table-column key="5" align="center" min-width="150" label="服务价格">
            <template slot-scope="scope">
              <span>{{ scope.row.minPrice }}-{{ scope.row.maxPrice }}</span>
            </template>
          </el-table-column>
          <el-table-column key="6" align="center" prop="priorityOrder" label="优先级次序" min-width="120">
            <template slot-scope="scope">
              <span >{{ scope.row.priorityOrder }}</span>
            </template>
          </el-table-column>
        </el-table>
        </el-card>
      </el-form>
    </div>

    <!--服务手册列表信息-->
    <div>
      <service-manual-list :productCode="productCode" :disabledFlag="disabledFlag"></service-manual-list>
    </div>

    <!--审核日志-->

<audit :productCode="productCode"></audit>





  </div>

</template>

<script>
import {selectSupplier} from '@/api/baseInfo/serviceProductManagement'
import productInfo   from './../components/productInfo'
import servicesAvailable   from './../components/servicesAvailable'
import serviceManualList   from './../components/serviceManualList'
import audit   from './../components/audit'


export default {
  components: {
    productInfo,
    servicesAvailable,
    serviceManualList,
    audit
  },
  data() {
    return {

      disabledFlag: false,
      parProductChname: '',
      parProductEnname: '',
      parOutProductChname: '',
      parOutProductEnname: '',
      productCode: '',
      supplierInfo: {
        supplierData: [{supplierCode: 'xx', priorityOrder: '1',index:0}, {
          supplierCode: 'xx',
          priorityOrder: '',index:1
        }, {supplierCode: 'xx', priorityOrder: '2',index:2}]
      },


    }
  },
  async created() {
    await this.init()



  },
  methods: {
    init(){
      //新增
      if(this.$route.query.status=='review'){
        this.disabledFlag=true
        this.productCode=this.$route.query.productCode
        //调用查询供应商的接口
        let query={
          productCode: this.$route.query.productCode
        }
        selectSupplier(query).then(res => {
          this.supplierInfo.supplierData = res.data;
        })


      }
    },

  /*  //提交审核
    submitHandle(){

      let produactInfoFlag=this.$refs.productInfoForm.validateForm()
      let servicesAvailableFlag=this.$refs.servicesAvailableForm.validateForm()
      if(produactInfoFlag){
        if(servicesAvailableFlag=='01'){

          let productInfoData= this.$refs.productInfoForm.baseForm
          let servicesAvailableData= this.$refs.servicesAvailableForm.serviceProForm
          let allData ={
            productInfoData: productInfoData,
            servicesAvailableData: servicesAvailableData
          }
          insertCheckInfo(allData).then(res => {
            if (res.code == '200') {
              this.$message({
                message: '保存成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.parProductChname=productInfoData.productChname
              this.parProductEnname=productInfoData.productEnname
              this.parOutProductChname=productInfoData.outProductChname
              this.parOutProductEnname=productInfoData.outProductEnname
              //给产品编码赋值

            } else {
              this.$message({
                message: '保存失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })


        }
        else if (servicesAvailableFlag=='02'){
          this.$message.warning('至少添加一条服务项目信息')
        }
        else if (servicesAvailableFlag=='03'){
          this.$message.warning('未选择供应商')
        }
        else if (servicesAvailableFlag=='04'){
          this.$message.warning('服务项目必录项未必录')
        }

      }
      else {
        this.$message.warning('产品基本信息必录项未必录')
      }




    },
    //保存
    saveAll(){
      let produactInfoFlag=this.$refs.productInfoForm.validateForm()
      let servicesAvailableFlag=this.$refs.servicesAvailableForm.validateForm()
      if(produactInfoFlag){
        if(servicesAvailableFlag=='01'){

          let productInfoData= this.$refs.productInfoForm.baseForm
          let servicesAvailableData= this.$refs.servicesAvailableForm.serviceProForm
          let allData ={
            productInfoData: productInfoData,
            servicesAvailableData: servicesAvailableData
          }
          saveProductInfo(allData).then(res => {
            if (res.code == '200') {
              this.$message({
                message: '保存成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.parProductChname=productInfoData.productChname
              this.parProductEnname=productInfoData.productEnname
              this.parOutProductChname=productInfoData.outProductChname
              this.parOutProductEnname=productInfoData.outProductEnname
            } else {
              this.$message({
                message: '保存失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })


        }
        else if (servicesAvailableFlag=='02'){
          this.$message.warning('至少添加一条服务项目信息')
        }
        else if (servicesAvailableFlag=='03'){
          this.$message.warning('未选择供应商')
        }
        else if (servicesAvailableFlag=='04'){
          this.$message.warning('服务项目必录项未必录')
        }

      }
      else {
        this.$message.warning('产品基本信息必录项未必录')
      }



    },*/
    //关闭
/*    goBack(){
      this.$router.go(-1);
    },*/


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

