<template>
  <el-form ref="otherServiceForm" :rules="otherSerivceFormRules" :model="otherServiceForm"
           style="padding-bottom: 30px;"
           label-width="170px" size="mini" class="baseInfo_class">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>服务信息</span>
        <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
        </span>
      </div>

      <el-row>
        <el-col :span="8">

          <el-form-item label="机构评分：" prop="grade">
            <el-input v-model="otherServiceForm.grade" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>


        </el-col>
        <el-col :span="8">
          <el-form-item label="机构类别：" prop="category">
            <el-select v-model="otherServiceForm.category" class="item-width" placeholder="请选择" clearable>
              <el-option value="01" label="A1"></el-option>
              <el-option value="02" label="B1"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="联系人邮箱：" prop="emil">
            <el-input v-model="otherServiceForm.emil" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="合作部门信息：" prop="coopDepInfo">
            <el-input v-model="otherServiceForm.coopDepInfo" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="集团统一编码：" prop="code">
            <el-select v-model="otherServiceForm.code" class="item-width" placeholder="请选择" clearable>
              <el-option value="01" label="一类"></el-option>
              <el-option value="02" label="二类"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院合作状态：" prop="cooperationStatus">
            <el-radio-group v-model="otherServiceForm.cooperationStatus">
              <el-radio label="01">正常</el-radio>
              <el-radio label="02">暂停</el-radio>
              <el-radio label="03">关闭</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="二证齐全是否发预授权书：" prop="continentFlag">
            <el-select v-model="otherServiceForm.continentFlag" class="item-width" placeholder="请选择是/否" clearable>
              <el-option label="是" value="01"></el-option>
              <el-option label="否" value="02"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

            <el-input v-model="otherServiceForm.continent" class="item-width" clearable
                      style="padding-left: 15px;width: 450px"
                      placeholder="请输入"/>

          </el-form-item>

        </el-col>
        <el-col :span="8">
          <el-form-item label="服务定位：" prop="servicelocator">
            <el-checkbox-group v-if="otherServiceForm.servicelocator" v-model="otherServiceForm.servicelocator">
              <el-checkbox label="01">普通部</el-checkbox>
              <el-checkbox label="02">特需部</el-checkbox>
              <el-checkbox label="03">国际部</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否虚拟机构：" prop="virtualOrg">
            <el-radio-group v-model="otherServiceForm.virtualOrg">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否有效：" prop="isEnabled">
            <el-radio-group v-model="otherServiceForm.isEnabled">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否同步显示至外网：" prop="displaynetwork">
            <el-radio-group v-model="otherServiceForm.displaynetwork">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否在网站显示：" prop="officialwebsite">
            <el-radio-group v-model="otherServiceForm.officialwebsite">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否周末营业：" prop="weekendsOpen">
            <el-radio-group v-model="otherServiceForm.weekendsOpen">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否流程通畅：" prop="processFlow">
            <el-radio-group v-model="otherServiceForm.processFlow">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

      </el-row>
      <el-col :span="24">
        <el-form-item label="特殊语种服务：" prop="speciallanguage">
          <el-checkbox-group v-model="otherServiceForm.speciallanguage">
            <el-checkbox label="01">英语</el-checkbox>
            <el-checkbox label="02">日语</el-checkbox>
            <el-checkbox label="03">法语</el-checkbox>
            <el-checkbox label="04">意大利语</el-checkbox>
            <el-checkbox label="05">粤语</el-checkbox>
            <el-checkbox label="06">西班牙语</el-checkbox>
            <el-checkbox label="07">其他</el-checkbox>
            <el-checkbox label="08">无</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-col>
      <el-row>
        <el-form-item label="服务特色：" prop="sellingpoint">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherServiceForm.sellingpoint" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="预授权书发送及确认方式：" prop="preauthsendway">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherServiceForm.preauthsendway">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对外备注：" prop="foreignnote">
          <el-input
            type="textarea"
            placeholder="请输入内容（客户可见）"
            v-model="otherServiceForm.foreignnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对内备注：" prop="internalnote">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="otherServiceForm.internalnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>


    </el-card>

  </el-form>
</template>
<script>
import {getserviceInfo, addserviceInfo, updateserviceInfo} from "@/api/baseInfo/medicalManage";
import {formatDate} from "@/utils";

export default {
  props: {
    providerCode: String,
status: String
  },


  data() {

    return {
      otherServiceForm: {
        servicelocator: [],
        speciallanguage: []

      },
      otherSerivceFormRules: {
        grade: [{required: true, message: '不能为空！', trigger: 'blur'}],
        category: [{required: true, message: '不能为空！', trigger: 'blur'}],
        cooperationStatus: [{required: true, message: '不能为空！', trigger: 'blur'}],
        continentFlag: [{required: true, message: '不能为空！', trigger: 'blur'}],
        virtualOrg: [{required: true, message: '不能为空！', trigger: 'blur'}],
        speciallanguage: [{required: true, message: '不能为空！', trigger: 'blur'}],
        sellingpoint: [{required: true, message: '不能为空！', trigger: 'blur'}]
      },
    }
  }
  ,
  mounted() {
        this.initData()
  }
  ,
  methods: {
    initData(){
      if(this.providerCode && this.status=='02' ){
        //调用查询的接口
      }

    },
    saveHandle(){

    },
    resetFrom(){

     /* this.otherServiceForm.grade=''
      this.otherServiceForm.category=''
      this.otherServiceForm.emil=''
      this.otherServiceForm.coopDepInfo=''
      this.otherServiceForm.code=''
      this.otherServiceForm.cooperationStatus=''
      this.otherServiceForm.continentFlag=''
      this.otherServiceForm.continent=''

      this.otherServiceForm.virtualOrg=''
      this.otherServiceForm.isEnabled=''
      this.otherServiceForm.displaynetwork=''
      this.otherServiceForm.officialwebsite=''
      this.otherServiceForm.weekendsOpen=''

      this.otherServiceForm.processFlow=''

      this.otherServiceForm.sellingpoint=''
      this.otherServiceForm.preauthsendway=''
      this.otherServiceForm.foreignnote=''

      this.otherServiceForm.internalnote=''*/

      this.otherServiceForm.servicelocator=[]
      this.otherServiceForm.speciallanguage=[]
      this.$refs.otherServiceForm.resetFields()

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

.el-dialog {
  display: flex;
  flex-direction: column;
  margin: 0 !important;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*height:600px;*/
  max-height: calc(100% - 30px);
  max-width: calc(100% - 30px);
}

.el-dialog .el-dialog__body {
  flex: 1;
  overflow: auto;
}

</style>
