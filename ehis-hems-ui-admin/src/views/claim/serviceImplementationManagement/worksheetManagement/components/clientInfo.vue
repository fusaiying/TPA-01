<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>客户信息</span>
    </div>
    <el-form ref="baseForm" :model="baseForm" label-width="170px"  disabled
             label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="客户姓名：">
            <el-input v-model="baseForm.name" class="item-width" clearable size="mini" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：" >
            <el-select v-model="baseForm.sex" class="item-width"  clearable>
              <el-option v-for="item in cs_sexOptionsOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：" >
            <el-date-picker v-model="baseForm.birthday" type="date" style="width: 200px" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="手机号码：" >
            <el-input v-model="baseForm.phone" class="item-width" clearable size="mini"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：">
            <el-select v-model="baseForm.idType" class="item-width"  clearable>
              <el-option v-for="item in card_typeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="证件号码：" >
            <el-input v-model="baseForm.idCode" class="item-width" clearable size="mini"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="保单号：" >
            <el-input v-model="baseForm.policyNo" class="item-width" clearable size="mini"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="分单号：" >
            <el-input v-model="baseForm.policyCertificateNo" class="item-width" clearable size="mini"  policy_certificate_no
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>


  </el-card>


</template>

<script>

import {getCustomerInfo} from '@/api/serviceProductManage/serviceImplManage'


export default {
  props: {

    orderCode: {
      type: String,
      default: ''
    },

  },

  watch: {

  },


  data() {

    return {

      baseForm: {},


      cs_sexOptionsOptions: [],
      card_typeOptions: []
    }
  },
  created() {

    this.getDicts("cs_sex").then(response => {
      this.cs_sexOptionsOptions = response.data;
    });
    this.getDicts("card_type").then(response => {
      this.card_typeOptions = response.data;
    });

    this.init()

  },
  methods: {
    init() {
      if (this.orderCode != null && this.orderCode != '') {
        let query={
          orderCode:this.orderCode
        }
        getCustomerInfo(query).then(res => {
          if (res.code == '200') {
            this.baseForm = res.data
          }


        })

      }

    },

    delHandle(index, row) {
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


</style>

