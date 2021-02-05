<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>服务信息</span>
    </div>
    <el-form ref="baseForm" :model="baseForm" label-width="170px" :rules="baseFormRules" v-loading="loading"
             label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="供应商名称：" prop="supplierCode">
            <el-select v-model="baseForm.supplierCode" class="item-width"  @change="changeSupplierName" filterable>
              <el-option v-for="item in productTypeOptions" :label="item.supplierName" :value="item.supplierCode"

                         :key="item.supplierCode"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人：" prop="contactName">
            <el-select v-model="baseForm.contactName"
                       style="width: 200px;"
                       filterable
                       placeholder="请选择"
                       @change="changeContactName">
              <el-option
                v-for="item in contactNameList"
                :key="item.index"
                :label="item.contactName"
                :value="item.contactName">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话：" prop="phone">
            <el-select v-model="baseForm.phone"
                       style="width: 200px;"
                       filterable
                       placeholder="请选择"
                       @change="changePhone"
            >
              <el-option
                v-for="item in contactNameList"
                :key="item.index"
                :label="item.phone"
                :value="item.phone">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品名称：" prop="productChname">
            <!--            <el-select v-model="baseForm.productCode" class="item-width" clearable  productCode>
                          <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                                     :key="item.dictValue"/>
                        </el-select>-->
            <el-input v-model="baseForm.productChname" class="item-width" clearable size="mini" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务类型：" prop="serviceType">
            <el-select v-model="baseForm.serviceType" class="item-width"  filterable @change="changeSupplier">
              <el-option v-for="item in serviceInfo" :label="item.serviceName" :value="item.serviceCode"
                         :key="item.serviceCode"/>
            </el-select>


          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务预约时间：" prop="applyTime">
            <!--            <el-input v-model="baseForm.applyTime" class="item-width" clearable size="mini"/>-->
            <el-date-picker v-model="baseForm.applyTime" type="date" placeholder="选择服务预约时间" size="mini"   style="width: 200px"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="住院地区：" prop="inpatientAreaList">
            <el-cascader clearable
                         v-model="baseForm.inpatientAreaList"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="期望地区：" prop="expectedAreaList">
            <el-cascader clearable
                         v-model="baseForm.expectedAreaList"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="就诊地区：" prop="visitingAreaList">
            <el-cascader clearable
                         v-model="baseForm.visitingAreaList"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="所在城市：" prop="cityList">
            <el-cascader clearable
                         v-model="baseForm.cityList"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="期望医院：" prop="expectationHospital">
            <el-select v-model="baseForm.expectationHospital" class="item-width" clearable @change="changeFirstDept">
              <el-option v-for="item in hospList" :label="item.hospitalName" :value="item.hospitalCode"
                         :key="item.hospitalCode"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="期望科室：" >
            <!--            <el-input v-model="baseForm.firstDept" class="item-width" clearable size="mini" style="width: 100px"/>-->
            <el-select v-model="baseForm.firstDept" class="item-width" clearable  size="mini" style="width: 100px" @change="changeSecondDept">
              <el-option v-for="item in firstDeptList" :label="item.firstDeptName" :value="item.firstDeptName"
                         :key="item.index"/>
            </el-select>
            <!--            <el-input v-model="baseForm.secondDept" class="item-width" clearable size="mini" style="width: 100px"/>-->
            <el-select v-model="baseForm.secondDept" class="item-width" clearable  size="mini" style="width: 100px" >
              <el-option v-for="item in secondDeptList" :label="item.secondDeptName" :value="item.secondDeptName"
                         :key="item.index"/>
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="重疾名称：" prop="seriousDisease">
            <el-input v-model="baseForm.seriousDisease" class="item-width" clearable size="mini"  placeholder="请输入"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手术名称：" prop="operation">
            <el-input v-model="baseForm.operation" class="item-width" clearable size="mini" placeholder="请输入"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出院诊断：" prop="finalDiagnosis">
            <el-input v-model="baseForm.finalDiagnosis" class="item-width" clearable size="mini" placeholder="请输入"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="症状描述：" prop="symptomDescription">
          <el-input type="textarea" v-model="baseForm.symptomDescription" clearable size="mini" placeholder="请输入"

          />
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="备注：" prop="remark">
          <el-input type="textarea" v-model="baseForm.remark" clearable size="mini" placeholder="请输入"
          />
        </el-form-item>
      </el-row>

    </el-form>



  </el-card>


</template>

<script>

import {getProductServiceList, getServiceInfo} from '@/api/productManage/serviceProductManagement'
import {getAddress} from '@/api/baseInfo/medicalManage.js'
import {queryinfo,getHosptialInfo,getAllProSuppInfo} from '@/api/serviceProductManage/serviceImplManage'

//import {getHosptialInfo} from '@/api/serviceProductManage/serviceImplManage'


export default {
  props: {
    orderCode: {
      type: String,
      default: ''
    },
    serviceCode: {
      type: String,
      default: ''
    },
    productCode: {
      type: String,
      default: ''
    },

  },

  watch: {},


  data() {

    return {
      regions: [],
      contactNameList: [],
      phoneList: [],
      baseForm: {},
      loading: false,
      options: [],
      baseFormRules: {
        supplierCode: [{required: true, message: '供应商名称不能为空', trigger: 'change'}],
        contactName: [{required: true, message: '联系人不能为空', trigger: 'change'}],
        phone: [{required: true, message: '联系电话不能为空', trigger: 'change'}],
        productChname: [{required: true, message: '产品名称不能为空', trigger: 'change'}],
        serviceType: [{required: true, message: '服务类型不能为空', trigger: 'change'}],
        applyTime: [{required: true, message: '服务预约时间不能为空', trigger: 'blur'}],
        symptomDescription: [{required: true, message: '症状描述不能为空', trigger: 'blur'}],
      },
      hospList: [],
      firstDeptList:[],
      hospObj:{},
      serviceInfo: [],

      secondDeptList:[],

      productTypeOptions: [],

    }
  },
  created() {
    this.getDicts("productType").then(response => {
      this.productTypeOptions = response.data;
    });

    this.getAddressData()

    this.getServiceInfoList()
    this.init()


  },

  methods: {
    async init() {
      await new Promise((resolve, reject) => {
        this.loading=true
        getHosptialInfo().then(res=>{
          this.hospList=res.data
          this.loading=false
          resolve(this.hospList)
        }).finally( ()=>{this.loading=false})
      }).then(resolve=>{
        if (this.orderCode != null && this.orderCode != '') {
          let queryData = {
            orderCode: this.orderCode,
          }
          queryinfo(queryData).then(res => {
            if (res.code == '200') {
              this.baseForm = res.data

              //this.baseForm.secondDept=''
              // this.$set(this.baseForm,'secondDept','')

            }


          })

        }
        if (this.serviceCode != null && this.serviceCode != '' && this.productCode!=null && this.productCode!='') {
          let queryData = {
            serviceCode: this.serviceCode,
            productCode: this.productCode
          }

          getAllProSuppInfo(queryData).then(res => {
            if (res.code == '200') {
              this.productTypeOptions = res.data

            }
            //初始化供应商的 联系人 联系电话下拉列表
            if(this.baseForm.supplierCode){
              //找到所选供应商对象
              let obj=this.productTypeOptions.find(item=>{
                return item.supplierCode==this.baseForm.supplierCode
              })
              if(obj!=null && obj!=null) {
                obj.contractInfo.map((data,index)=>{
                  data.index=index
                })
                this.contactNameList = obj.contractInfo
              }

            }
            //初始化选中医院的一级科室  二级科室
            if(this.baseForm.expectationHospital){

              //找到该医院下的list
              let hospObj= this.hospList.find(item=>{
                return item.hospitalCode==this.baseForm.expectationHospital
              })
              this.firstDeptList=[]
              if(hospObj!=null && hospObj!='') {
                hospObj.firstdeptInfos.map((data, index) => {
                  data.index = index + 1
                })
                this.hospObj=hospObj

                this.firstDeptList=hospObj.firstdeptInfos
              }

            }
            if(this.baseForm.firstDept){

              //找到一级科室
              let firstObj=this.hospObj.firstdeptInfos.find(item =>{
                return item.firstDeptName==this.baseForm.firstDept
              })
              this.secondDeptInfos=[]
              //找到二级科室
              firstObj.secondDeptInfos.map((data,index) =>{
                data.index=index+1
              })
              this.secondDeptList=firstObj.secondDeptInfos

            }
          })






        }
      })






    },
    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },
    //查询服务-供应商-联系人
    getSuppInfoAndPhone(){

    },
//获取医院 科室信息
    getHospList(){

    },


    //更改一级科室
    changeFirstDept(){

      this.firstDeptList=[]
      this.baseForm.firstDept=''
      this.hospObj={}
      this.secondDeptList=[]
      this.baseForm.secondDept=''
      if(this.baseForm.expectationHospital){
        //找到该医院下的list
        let hospObj= this.hospList.find(item=>{
          return item.hospitalCode==this.baseForm.expectationHospital
        })


        hospObj.firstdeptInfos.map((data,index) =>{
          data.index=index+1
        })
        this.hospObj=hospObj

        this.$set(this,'firstDeptList',hospObj.firstdeptInfos)

      }
      else {
        this.hospObj={}
        this.firstDeptList=[]
        this.baseForm.firstDept=''
        this.baseForm.secondDept=''
        this.secondDeptList=[]
      }
    },

    //更改二级科室
    changeSecondDept(){
      this.secondDeptList=[]
      this.baseForm.secondDept=''
      if(this.baseForm.firstDept){

        //找到一级科室
        let firstObj=this.hospObj.firstdeptInfos.find(item =>{
          return item.firstDeptName==this.baseForm.firstDept
        })
        //找到二级科室
        firstObj.secondDeptInfos.map((data,index) =>{
          data.index=index+1
        })
        /*this.secondDeptList=firstObj.secondDeptInfos*/
        this.$set(this,'secondDeptList',firstObj.secondDeptInfos)

      }
      else {
        this.baseForm.secondDept=''
        this.secondDeptList=[]
      }


    },

    //获取服务项目名称下拉框
    getServiceInfoList() {
      /*     getServiceInfo().then(res => {
             this.serviceInfo = res
           })*/

      if (this.productCode != null && this.productCode != '') {
        let queryData = {
          productCode: this.productCode,
        }
        getProductServiceList(queryData).then(res => {
          if (res.code == '200' && res.rows.length > 0) {
            this.serviceInfo = res.rows
          }
        })
      }
    },


    delHandle(index, row) {
    },

    changePhone() {
      //找到所含联系电话对象
      let obj=this.contactNameList.find(item =>{
        return item.phone==this.baseForm.phone
      })
      if(obj!=null && obj!=''){
        this.$set(this.baseForm,'contactName',obj.contactName)
      }
    },
    changeContactName(){
      //找到所含联系人对象
      let obj=this.contactNameList.find(item =>{
        return item.contactName==this.baseForm.contactName
      })
      if(obj!=null && obj!=''){
        this.$set(this.baseForm,'phone',obj.phone)
      }
    },
    //更改联系人 联系电话
    changeSupplierName(){
      this.baseForm.phone=''
      this.baseForm.contactName=''
      //找到所选供应商对象
      let obj=this.productTypeOptions.find(item=>{
        return item.supplierCode==this.baseForm.supplierCode
      })
      if(obj!=null && obj!=null) {
        obj.contractInfo.map((data,index)=>{
          data.index=index
        })
        this.contactNameList = obj.contractInfo
        this.$set(this.baseForm,'phone',this.contactNameList[0].phone)
        this.$set(this.baseForm,'contactName',this.contactNameList[0].contactName)
      }
    },
    //更改服务类型 改变供应商及其联系人
    changeSupplier(){
      //查询能提供服务的供应商
      let queryData = {
        serviceCode: this.serviceCode,
        productCode: this.baseForm.serviceType
      }
      this.productTypeOptions=[]
      this.contactNameList=[]
      this.baseForm.supplierCode=''
      this.baseForm.phone=''
      this.baseForm.contactName=''
      getAllProSuppInfo(queryData).then(res => {
        if (res.code == '200') {
          this.productTypeOptions = res.data
          if(this.productTypeOptions!=null && this.productTypeOptions.length>0){
            this.baseForm.supplierCode=this.productTypeOptions[0].supplierCode
            //初始化供应商的 联系人 联系电话下拉列表
            this.contactNameList=this.productTypeOptions[0].contractInfo
            //初始化联系人 联系电话
            this.$set(this.baseForm,'phone',this.contactNameList[0].phone)
            this.$set(this.baseForm,'contactName',this.contactNameList[0].contactName)
          }
        }
      })


    },
    //校验
    validateForm() {
      let flag=null
      this.$refs['baseForm'].validate(valid => {
        if (valid) {
          flag = true
        } else {
          flag = false
        }
      })
      return flag
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


</style>

