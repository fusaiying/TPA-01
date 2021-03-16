<template>
  <div class="app-container">
    <!--产品基本信息-->
    <div >
      <product-info ref="productInfoForm"   :parProductChname="parProductChname" :parProductEnname="parProductEnname" :disableFlag="disabledFlag"
                    :parOutProductChname="parOutProductChname" :parOutProductEnname="parOutProductEnname" :productCode="productCode" @saveFlag="changeDisabledFlag"
      ></product-info>
    </div>
    <!--服务项目-->
    <div >
      <services-available ref="servicesAvailableForm" :productCode="productCode" :disabledFlag="disabledFlag" ></services-available>
    </div>

    <!--服务手册列表信息-->
    <div>
      <service-manual-list :productCode="productCode" :disabledFlag="disabledFlag"></service-manual-list>
    </div>


    <div
      style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;float: right; ">
      <el-button size="mini" type="primary" @click="submitHandle" :disabled="disabledFlag">提交审核</el-button>
      <el-button size="mini" type="primary" @click="saveAll" :disabled="disabledFlag">保存</el-button>
      <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
    </div>



  </div>

</template>

<script>
import {saveProductInfo,checkProductInfo} from '@/api/productManage/serviceProductManagement'
import productInfo   from './../components/productInfo'
import servicesAvailable   from './../components/servicesAvailable'
import serviceManualList   from './../components/serviceManualList'

export default {
  components: {
    productInfo,
    servicesAvailable,
    serviceManualList
  },
  data() {
    return {

      disabledFlag: false,
      parProductChname: '',
      parProductEnname: '',
      parOutProductChname: '',
      parOutProductEnname: '',
      productCode: '',


    }
  },
  async created() {
    await this.init()



  },
  methods: {
    init(){
      //新增
      if(this.$route.query.status=='add'){
        this.disabledFlag=true
      }
      else if( this.$route.query.status=='edit'){
        this.productCode=this.$route.query.productCode
      }
    },
    getSaveFlag(val){
      console.log(val)
    },
    changeDisabledFlag(){
      this.productCode=this.$refs.productInfoForm.otherProductCode
      this.disabledFlag=false
    },


    //提交审核
    submitHandle(){
      this.$refs['productInfoForm'].validateForm().then(res=>{
        let servicesAvailableFlag=this.$refs['servicesAvailableForm'].validateForm()
        if(res){

          if(servicesAvailableFlag=='01'){

            let productInfoData= this.$refs.productInfoForm.baseForm
            let servicesAvailableData= this.$refs.servicesAvailableForm.serviceProForm
            let allData ={
              productInfoData: productInfoData,
              servicesAvailableData: servicesAvailableData
            }
            checkProductInfo(allData).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '提交成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.parProductChname=productInfoData.productChname
                this.parProductEnname=productInfoData.productEnname
                this.parOutProductChname=productInfoData.outProductChname
                this.parOutProductEnname=productInfoData.outProductEnname
                this.$store.dispatch("tagsView/delView", this.$route);
                this.$router.push({
                  path: '/service-product/product-definition',
                })

              } else {
                this.$message({
                  message: '提交失败!',
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
      })







    },
    //保存
    saveAll(){
      this.$refs['productInfoForm'].validateForm().then((res)=>{
          let servicesAvailableFlag=this.$refs['servicesAvailableForm'].validateForm()
          if(res){

            if(servicesAvailableFlag=='01' || servicesAvailableFlag=='03') {
              let productInfoData = this.$refs.productInfoForm.baseForm
              let servicesAvailableData = this.$refs.servicesAvailableForm.serviceProForm

              let allData = {
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
                  this.parProductChname = productInfoData.productChname
                  this.parProductEnname = productInfoData.productEnname
                  this.parOutProductChname = productInfoData.outProductChname
                  this.parOutProductEnname = productInfoData.outProductEnname
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
            /*      else if (servicesAvailableFlag=='03'){
                    this.$message.warning('未选择供应商')
                  }*/
            else if (servicesAvailableFlag=='04'){
              this.$message.warning('服务项目必录项未必录')
            }

          }
          else {
            this.$message.warning('产品基本信息必录项未必录')
          }


        }


      )





    },
    //关闭
    goBack(){
      this.$router.go(-1);
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

