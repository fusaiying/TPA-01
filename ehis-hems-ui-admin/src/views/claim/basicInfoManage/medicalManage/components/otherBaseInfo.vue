<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>基本信息</span>
      <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
        </span>
    </div>
    <el-form ref="otherBaseForm" :rules="otherBaseFormRules" :model="otherBaseForm" style="padding-bottom: 30px;"
             label-width="170px" size="mini" class="baseInfo_class">
      <el-row>
        <el-col :span="8">
          <el-form-item label="医疗编码：" prop="providerCode">
            <el-input v-model="otherBaseForm.providerCode" class="item-width" clearable disabled size="mini"
                      placeholder="系统自动生成（待定）"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="经度：" prop="longitude">
            <el-input v-model="otherBaseForm.longitude" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="纬度：" prop="latitude">
            <el-input v-model="otherBaseForm.latitude" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="中文展示名称：" prop="chname1">
            <el-input v-model="otherBaseForm.chname1" class="item-width" clearable size="mini" placeholder="请输入" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="英文展示名称：" prop="enname1">
            <el-input v-model="otherBaseForm.enname1" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="官方网址：" prop="website">
            <el-input v-model="otherBaseForm.website" class="item-width" clearable size="mini" placeholder="请输入" />
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="中文工商注册名称：" prop="chregister">
            <el-input v-model="otherBaseForm.chregister" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="英文工商注册名称：" prop="enregister">
            <el-input v-model="otherBaseForm.enregister" class="item-width" clearable size="mini" placeholder="请输入" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="官方电话：" prop="phone">
            <el-input v-model="otherBaseForm.phone" class="item-width" clearable size="mini"
                      placeholder="请输入电话" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="咨询电话：" prop="consultphone">
            <el-input v-model="otherBaseForm.consultphone" class="item-width" clearable size="mini" placeholder="请输入"
                      maxlength="50"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="公共邮箱：" prop="email">
            <el-input v-model="otherBaseForm.email" class="item-width" clearable size="mini" placeholder="请输入"
                     />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮编：" prop="zipcode">
            <el-input v-model="otherBaseForm.zipcode" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="区号：" prop="areacode">
            <el-input v-model="otherBaseForm.areacode" class="item-width" clearable
                      placeholder="区号"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="传真：" prop="portraiture">
            <el-input v-model="otherBaseForm.portraiture" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>




<el-row>
        <el-col :span="8">
          <el-form-item label="州：" prop="continent">
            <el-select v-model="otherBaseForm.continent" class="item-width" placeholder="请选择" clearable>
              <el-option label="亚洲" value="01"/>
              <el-option label="欧洲" value="02"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="国家：" prop="country">
            <el-select v-model="otherBaseForm.country" class="item-width" placeholder="请选择" clearable>
              <el-option label="中国" value="01"/>
              <el-option label="日本" value="02"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="省市区：" prop="address">
            <el-cascader
              v-model="otherBaseForm.address"
              :props="{ checkStrictly: true }"
              :options="regions"
              class="item-width"
              @change="handleChange"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="中文地址：" prop="chaddreess">
          <el-input v-model="otherBaseForm.chaddreess" clearable size="mini" placeholder="请输入" />
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="英文地址：" prop="enaddress">
          <el-input v-model="otherBaseForm.enaddress" clearable size="mini" placeholder="请输入" />
        </el-form-item>
      </el-row>
      <el-row>

        <el-col :span="8">
          <el-form-item label="标志性建筑：" prop="landmarks">
            <el-input v-model="otherBaseForm.landmarks" class="item-width" clearable size="mini"
                      placeholder="c端客户可看"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="停车场：" prop="carpark">
            <el-input v-model="otherBaseForm.carpark" class="item-width" clearable size="mini" placeholder="c端客户可看" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="交通路线：" prop="transitroute">
            <el-input v-model="otherBaseForm.transitroute" class="item-width" clearable size="mini"
                      placeholder="c端客户可看"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="机构简介：" prop="introduction">
          <el-input type="textarea" v-model="otherBaseForm.introduction" clearable size="mini" placeholder="请输入"
                    maxlength="2000"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="备注(中文)：" prop="chremarks">
          <el-input v-model="otherBaseForm.chremarks" clearable size="mini" placeholder="请输入" maxlength="2000"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="margin-right: 20px;" label="备注(英文)：" prop="enremarks">
          <el-input v-model="otherBaseForm.enremarks" clearable size="mini" placeholder="请输入" maxlength="2000"/>
        </el-form-item>
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
import {getapplyInfo, addapplyInfo, updateapplyInfo} from "@/api/baseInfo/medicalManage";

export default {
  props: {
    providerCode: String,
status:String
  },

  data() {

    return {
      regions: [],
      otherBaseForm: {},
      otherBaseFormRules: {
        confirmDeliverytime: [{required: true, message: '不能为空！', trigger: 'blur'}],
        longitude: [{required: true, message: '不能为空！', trigger: 'blur'}],
        latitude: [{required: true, message: '不能为空！', trigger: 'blur'}],
        chname1: [{required: true, message: '不能为空！', trigger: 'blur'}],
        enname1: [{required: true, message: '不能为空！', trigger: 'blur'}],
        website: [{required: true, message: '不能为空！', trigger: 'blur'}],
        continent: [{required: true, message: '不能为空！', trigger: 'blur'}],
        country: [{required: true, message: '不能为空！', trigger: 'blur'}],
        address: [{required: true, message: '不能为空！', trigger: 'blur'}],
        chaddreess: [{required: true, message: '不能为空！', trigger: 'blur'}],
        enaddress: [{required: true, message: '不能为空！', trigger: 'blur'}],
        landmarks: [{required: true, message: '不能为空！', trigger: 'blur'}],
        carpark: [{required: true, message: '不能为空！', trigger: 'blur'}],
        introduction: [{required: true, message: '不能为空！', trigger: 'blur'}]
      },
    }
  },

  mounted() {
    this.initData
  },

  methods: {
    initData(){
      if(this.providerCode&& this.status=='02'){
        //调用查询方法
      }

    },
    saveHandle(){

    },
    handleChange(){
    },
    resetFrom(){
      this.$refs.otherBaseForm.resetFields()
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


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}
</style>
