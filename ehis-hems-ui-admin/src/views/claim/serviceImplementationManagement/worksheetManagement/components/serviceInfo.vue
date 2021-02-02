<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>服务信息</span>
    </div>
    <el-form ref="baseForm" :model="baseForm" label-width="170px" :rules="baseFormRules"
             label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="供应商名称：" prop="supplierCode">
            <el-select v-model="baseForm.supplierCode" class="item-width" >
              <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                         @change="changeSupplierName"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人：" prop="contactName">
            <el-select v-model="baseForm.contactName"
                       style="width: 200px;"
                       filterable
                       remote
                       reserve-keyword
                       placeholder="请选择"
                       :remote-method="remoteMethod"
                       :loading="loading" @change="changeContactName">
              <el-option
                v-for="item in contactNameList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话：" prop="phone">
            <el-select v-model="baseForm.phone"
                       style="width: 200px;"
                       filterable
                       remote
                       reserve-keyword
                       placeholder="请选择"
                       :remote-method="remoteMethod"
                       @change="changePhone"
                       :loading="loading">
              <el-option
                v-for="item in phoneList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="产品名称：" prop="productCode">
            <el-select v-model="baseForm.productCode" class="item-width" clearable >
              <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务类型：" prop="serviceType">
            <el-select v-model="baseForm.serviceType" class="item-width" clearable >
              <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务预约时间：" prop="applyTime">
            <el-input v-model="baseForm.applyTime" class="item-width" clearable size="mini"
            />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="住院地区：" prop="InpatientArea">
            <el-cascader clearable
                         v-model="baseForm.InpatientArea"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="期望地区：" prop="expectedArea">
            <el-cascader clearable
                         v-model="baseForm.expectedArea"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="就诊地区：" prop="visitingArea">
            <el-cascader clearable
                         v-model="baseForm.visitingArea"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="所在城市：" prop="city">
            <el-cascader clearable
                         v-model="baseForm.city"
                         :props="{ checkStrictly: true }"
                         :options="regions"
                         class="item-width"

            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="期望医院：">
            <el-select v-model="baseForm.expectationHospital" class="item-width" clearable >
              <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="期望科室：">
            <el-input v-model="baseForm.expectedDepartment" class="item-width" clearable size="mini"
            />
            <!--            <el-input v-model="baseForm.outProductChname" class="item-width" clearable size="mini" style="width: 100px"-->

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="重疾名称：">
            <el-input v-model="baseForm.seriousDisease" class="item-width" clearable size="mini"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手术名称：">
            <el-input v-model="baseForm.operation" class="item-width" clearable size="mini"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出院诊断：">
            <el-input v-model="baseForm.finalDiagnosis" class="item-width" clearable size="mini"
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

import {checkField, getProductInfo, insertProductInfo} from '@/api/baseInfo/serviceProductManagement'
import {getAddress} from '@/api/baseInfo/medicalManage.js'


export default {
  props: {

    orderCode: {
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
      options: [{label: '11', value: '11'}],
      baseFormRules: {
        supplierCode: [{required: true, message: '供应商名称不能为空', trigger: 'change'}],
        contactName: [{required: true, message: '联系人不能为空', trigger: 'change'}],
        phone: [{required: true, message: '联系电话不能为空', trigger: 'change'}],
        productCode: [{required: true, message: '产品名称不能为空', trigger: 'change'}],
        serviceType: [{required: true, message: '服务类型不能为空', trigger: 'change'}],
        applyTime: [{required: true, message: '服务预约时间不能为空', trigger: 'blur'}],
        symptomDescription: [{required: true, message: '症状描述不能为空', trigger: 'blur'}],

      },
      productTypeOptions: [],
      productTimeTimeOptions: []
    }
  },
  created() {
    //获取产品定义信息
    this.getDicts("productType").then(response => {
      this.productTypeOptions = response.data;
    });
    this.getDicts("productTimeTime").then(response => {
      this.productTimeTimeOptions = response.data;
    });
    this.getAddressData()

    this.init()

  },
  methods: {
    init() {
      if (this.productCode != null && this.productCode != '') {
        let queryData = {
          productCode: this.productCode,
        }
        getProductInfo(queryData).then(res => {
          if (res.code == '200') {
            this.baseForm = res.data
          }


        })

      }

    },
    getAddressData() {
      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
      })
    },

    delHandle(index, row) {
    },
//联系人搜索
    remoteMethod() {

    },
    changePhone() {

    },
    changeContactName(){},
    changeSupplierName(){},
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

