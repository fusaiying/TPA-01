<template>
  <el-form ref="serviceForm" :rules="serivceFormRules" :model="serviceForm" :disabled="disabledFlag"
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
          <el-form-item label="所属医疗网络类型：" prop="networkType">
<!--            <el-select v-model="serviceForm.networkType" class="item-width" placeholder="请选择" clearable>
              <el-option label="类型一" value="01"></el-option>
              <el-option label="类型二" value="02"></el-option>
              &lt;!&ndash;                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>&ndash;&gt;
            </el-select>-->
            <el-button size="mini" type="primary" @click="maintainBtn">维护</el-button>
            <el-button size="mini" type="primary" @click="maintainRecordBtn">维护记录</el-button>
            <el-button size="mini" type="primary" @click="insuranceMainBtn">险种维护</el-button>
            <el-button size="mini" type="primary" @click="resetForm">险种维护记录</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院类别：" prop="category">
            <el-select v-model="serviceForm.category" class="item-width" placeholder="请选择" clearable>
              <el-option value="01" label="A1"></el-option>
              <el-option value="02" label="B1"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="医院分类：" prop="classification">
            <el-select v-model="serviceForm.classification" class="item-width" placeholder="请选择" clearable>
              <el-option value="01" label="一类"></el-option>
              <el-option value="02" label="二类"></el-option>
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="医院评分：" prop="grade">
            <el-input ref="hospitalname" v-model="serviceForm.grade" class="item-width" clearable size="mini"
                      placeholder="请输入(0-10 1位小数)"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否同步显示至外网：" prop="displaynetwork">
            <el-radio-group v-model="serviceForm.displaynetwork">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否在网站显示：" prop="officialwebsite">
            <el-radio-group v-model="serviceForm.officialwebsite">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否优选医院：" prop="prehospital">
            <el-radio-group v-model="serviceForm.prehospital">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否对所有保单开放：" prop="allcontopen">
            <el-radio-group v-model="serviceForm.allcontopen">
              <el-radio label="01">是</el-radio>
              <el-radio label="02">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院合作状态：" prop="cooperationStatus">
            <el-radio-group v-model="serviceForm.cooperationStatus">
              <el-radio label="01">正常</el-radio>
              <el-radio label="02">暂停</el-radio>
              <el-radio label="03">关闭</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务定位：" prop="servicelocator">
            <el-checkbox-group v-if="serviceForm.servicelocator" v-model="serviceForm.servicelocator">
              <el-checkbox label="01">普通部</el-checkbox>
              <el-checkbox label="02">特需部</el-checkbox>
              <el-checkbox label="03">国际部</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="特殊语种服务：" prop="speciallanguage">
            <el-checkbox-group v-model="serviceForm.speciallanguage">
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

        <el-col :span="24">
          <el-form-item label="网络医院直结类型：" prop="networkHospitalType">
            <el-checkbox-group v-model="serviceForm.networkHospitalType">
              <el-checkbox label="01">门诊</el-checkbox>
              <el-checkbox label="02">住院</el-checkbox>
              <el-checkbox label="03">体检</el-checkbox>
              <el-checkbox label="04">牙科</el-checkbox>
              <el-checkbox label="05">生育</el-checkbox>
              <el-checkbox label="06">眼科</el-checkbox>
              <el-checkbox label="07">疫苗</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
      </el-row>

        <el-row>
          <el-col :span="13">
            <el-form-item label="诊疗费折扣：" prop="treatmentDiscount">
              <el-input v-model="serviceForm.treatmentDiscount" class="item-width" clearable maxlength="50"
                        style="width: 450px"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="次均控费：" prop="code">
              <el-input v-model="serviceForm.code" class="item-width" clearable maxlength="50"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="检查费折扣：" prop="examineDiscount">
              <el-input v-model="serviceForm.examineDiscount" class="item-width" clearable
                        style="width: 450px"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="次均控费类型：" prop="type">
              <el-select v-model="serviceForm.type" class="item-width" placeholder="请选择" clearable>
                <el-option value="01" label="控费类型1"> </el-option>
                <el-option value="02" label="控费类型2"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="床位费折扣：" prop="bedDiscount">
              <el-input v-model="serviceForm.bedDiscount" class="item-width" clearable
                        style="width: 450px"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="就诊次数：" prop="adviceNum">
              <el-input v-model="serviceForm.adviceNum" class="item-width" clearable
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="护理费折扣：" prop="allowance">
            <el-input v-model="serviceForm.allowance" class="item-width" clearable
                      style="width: 450px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="医药费折扣：" prop="costs">
            <el-input v-model="serviceForm.costs" class="item-width" clearable
                      style="width: 450px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="折扣信息：" prop="discountinfo">
            <el-input v-model="serviceForm.discountinfo" class="item-width" clearable maxlength="50"
                      style="width: 550px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="特殊费折扣信息：" prop="specialdiscount">
            <el-input v-model="serviceForm.specialdiscount" class="item-width" clearable maxlength="50"
                      style="width: 550px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="折扣除外项目：" prop="project">
            <el-input v-model="serviceForm.project" class="item-width" clearable maxlength="50"
                      style="width: 550px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-row>

      <el-row>
        <el-form-item label="服务特色：" prop="sellingpoint">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="serviceForm.sellingpoint" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对外备注：" prop="foreignnote">
          <el-input
            type="textarea"
            placeholder="请输入内容（客户可见）"
            v-model="serviceForm.foreignnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对内备注：" prop="internalnote">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="serviceForm.internalnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>

    </el-card>
    <el-dialog
      :visible.sync="maintainDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">
        <el-card class="box-card department-style" style="margin-top: 10px;">
          <div slot="header" class="clearfix">
            <span>医疗网络类型维护</span>
          </div>
          <el-table ref="medicalTable"
            :data="medicalTypeData"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">

            <el-table-column key="1"  align="center" prop="medicalType" min-width="150" label="医疗网络类型" />
            <el-table-column key="2"  align="center" min-width="100" prop="productcode" label="产品代码" />
            <el-table-column key="3"  align="center" prop="oldoption" label="当前选项" min-width="120" >
              <template slot-scope="scope">
              <span>{{scope.row.oldoption=='01'?'是':'否'}}</span>
              </template>
            </el-table-column>new
            <el-table-column key="4"  align="center" prop="newoption" min-width="160" label="新选项" >
              <template slot-scope="scope">
                <el-checkbox-group v-model="scope.row.newoption" v-if="scope.row.newoption">
                <el-checkbox :label="scope.row.oldoption=='01'?'02':'01'" v-model="scope.row.newoption" @change="changeOption(scope.$index,scope.row)">
                    <span>{{scope.row.oldoption=='01'?'否':'是'}}</span>
                </el-checkbox>
                </el-checkbox-group>
              </template>

            </el-table-column>
            <el-table-column key="5"   align="center" min-width="150" prop="newoptionDate" label="新选项生效日"  style="width: 180px" >
            <template slot-scope="scope">

                  <el-date-picker v-if="scope.row.newInputShow"  v-model="scope.row.newoptionDate"
                    type="datetime"
                    placeholder="选择日期时间">
                  </el-date-picker>


            </template>
            </el-table-column>
            <el-table-column key="6"  align="center" prop="operator" label="操作人" min-width="120" />
          </el-table>


        </el-card>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="delConfirm">确 定</el-button>
          <el-button @click="cancelBtn">取 消</el-button>

        </span>
    </el-dialog>
    <!--医疗维护记录弹出框-->
    <el-dialog
      :visible.sync="medicalRecordDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">
      <el-card class="box-card department-style" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span>所属医疗网络类型维护记录</span>
        </div>
        <el-table ref="medicalRecordTable"
                  :data="medicalRecordData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  style="width: 100%;">

          <el-table-column key="1"  align="center" prop="medicalType" min-width="150" label="医疗网络类型" />
          <el-table-column key="2"  align="center" min-width="100" prop="option" label="选项" />
          <el-table-column key="3"  align="center" prop="startTime" min-width="150" label="选项生效日" />
          <el-table-column key="4"  align="center" min-width="100" prop="endTime" label="选项失效日" />
          <el-table-column key="5"  align="center" prop="maintenance" min-width="150" label="维护人" />
          <el-table-column key="6"  align="center" prop="maintenanceTime" label="维护时间" min-width="120" />
        </el-table>
         <div>
         <el-pagination
           :total="totalCount"
           :current-page="formSearch.pageNum"
           :page-size="formSearch.pageSize"
           :page-sizes="[10, 20, 30, 40]"
           style="margin-top: 8px; text-align: right;"
           layout="sizes, prev, pager, next"
           @size-change="handleSizeChange"
           @current-change="handleCurrentChange"/>
       </div>

      </el-card>


    </el-dialog>


<!--险种维护表-->
    <el-dialog
      :visible.sync="insuranceMainDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="50%">
      <el-card class="box-card department-style" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span>险种维护列表</span>
          <span style="float: right;">
          <el-button size="mini" type="primary" @click="insuranceSaveHandle">保存</el-button>
              <el-button size="mini" type="primary" @click="insuranceCloingHandle">关闭</el-button>
        </span>
        </div>
        <el-table ref="insuranceRecordTable"
                  :data="insuranceRecordData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  style="width: 100%;">

          <el-table-column label="险种代码" prop="insurancecode" align="center" >
            <template slot-scope="scope">
              <el-select v-model="scope.row.insurancecode" class="item-width" placeholder="请选择" clearable v-if="!scope.row.id"
                         size="mini"
              >
                <el-option value="01" label="是"></el-option>
                <el-option value="02" label="否"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>

              <span v-else>{{ scope.row.insurancecode }}</span>
            </template>
          </el-table-column>
          <el-table-column label="所属网络类型" prop="netType" align="center" >

            <template slot-scope="scope">
              <el-select v-model="scope.row.netType" class="item-width" v-if="!scope.row.id" placeholder="请选择" clearable
                         size="mini"
              >
                <el-option value="01" label="是"></el-option>
                <el-option value="02" label="否"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
              <span v-else>{{ scope.row.netType }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center"  fixed="right">
            <template slot-scope="scope">
                    <span>
                       <el-button type="text" size="mini"
                                  @click="delHandle(scope.$index, scope.row)">移除</el-button>
                    </span>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="text" size="mini"
                   style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                   @click="addInsuranceRecordHandle()"> + 添加
        </el-button>


      </el-card>

    </el-dialog>

  </el-form>
</template>
<script>
import {getserviceInfo, addserviceInfo, updateserviceInfo} from "@/api/baseInfo/medicalManage";
import {formatDate} from "@/utils";

export default {
  props: {
    serviceForm: {
      type: Object,
      default: function(){
      return {}
      }
    },
    providerCode: String,
    disabledFlag: Boolean
  },


data()
{
  const checkGrade = (rules, value, callback) => {
    const regx = /^\+?(?:\d(?:\.\d)?|10(?:\.0)?)$/
    if (!regx.test(value)) {
      callback(new Error('评分在0-10，最多保留一位小数！'))
    } else {
      callback()
    }
  }
  const checkDiscount = (rules, value, callback) => {
    if(value) {
      const regx = /^(0(\.\d{1,2})?|1(\.0{1,2})?)$/
      if (!regx.test(value)) {
        callback(new Error('评分在0-1，最多保留二位小数！'))
      } else {
        callback()
      }
    }
    else {
      callback()
    }
  }
  return {
    totalCount: 0,
    insuranceMainDialogVisible: false,
    insuranceRecordData: [{id:'1',netType: '1'}],

    // 查询参数
    formSearch: {
      pageNum: 1,
      pageSize: 10,
      providerCode: undefined
    },
    medicalRecordDialogVisible: false,
    medicalRecordData: [{medicalType: '01'},{medicalType: '01'},{medicalType: '01'}],
    medicalTypeData: [{medicalType: '01',newoption:[]},
      {medicalType: '02',newoption: []},
      {medicalType: '02',oldoption:'01',newoption: []}],
    maintainDialogVisible: false,
    modalValue: false,
    serivceFormRules: {
      grade: [{validator: checkGrade, trigger: 'blur'}],
      cooperationStatus: [{required: true, message: '不能为空！', trigger: 'change'}],
      foreignnote: [{required: true, message: '不能为空！', trigger: 'blur'}],
      internalnote: [{required: true, message: '不能为空！', trigger: 'blur'}],
      examineDiscount: [{validator: checkDiscount, trigger: 'blur'}],
      bedDiscount: [{validator: checkDiscount, trigger: 'blur'}],
      adviceNum: [{required: true, message: '不能为空！', trigger: 'blur'}],
      allowance: [{validator: checkDiscount,  trigger: 'blur'}],
      costs: [{validator: checkDiscount, trigger: 'blur'}]

    },
  }
}
,
mounted()
{

}
,
methods: {
  //服务信息保存
  saveHandle()
  {
    this.$refs.serviceForm.validate((valid) => {
      this.serviceForm.providerCode = this.providerCode
      if (valid) {
        //存在调用服务信息保存的接口
        if (this.serviceForm.providerCode) {
          addserviceInfo(this.serviceForm).then(res => {
            if (res.code == '200') {
              this.$message({
                message: '保存成功！',
                type: 'success',
                center: true,
                showClose: true
              })
            } else  {
              this.$message({
                message: '保存失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })
        }
      } else {
        return false
      }

    })


  }
,

  //重置
  resetFrom() {
    this.serviceForm.networkType=''
    this.serviceForm.category=''
    this.serviceForm.classification=''
    this.serviceForm.grade=''
    this.serviceForm.displaynetwork=''
    this.serviceForm.officialwebsite=''
    this.serviceForm.prehospital=''
    this.serviceForm.allcontopen=''
    this.serviceForm.cooperationStatus=''
    this.serviceForm.treatmentDiscount=''
    this.serviceForm.code=''
    this.serviceForm.examineDiscount=''
    this.serviceForm.type=''
    this.serviceForm.bedDiscount=''
    this.serviceForm.adviceNum=''
    this.serviceForm.allowance=''
    this.serviceForm.costs=''
    this.serviceForm.discountinfo=''
    this.serviceForm.specialdiscount=''
    this.serviceForm.project=''
    this.serviceForm.sellingpoint=''
    this.serviceForm.foreignnote=''
    this.serviceForm.internalnote=''
    this.serviceForm.servicelocator=[]
    this.serviceForm.speciallanguage=[]
    this.serviceForm.networkHospitalType=[]
    this.$refs.serviceForm.resetFields()


  },

  //维护弹出框
  maintainBtn(){
    this.maintainDialogVisible=true
    //调用查询方法
    if(this.medicalTypeData) {
      this.medicalTypeData.map((data, index) => {
        data.newInputShow = false
      })
      this.copyMedicalData=this.medicalTypeData
    }
  },

  changeOption(index,data){
    if(data.newoption && data.newoption!='') {
      let defaultTime = new Date()
      this.$set(this.medicalTypeData[index], "newoptionDate", defaultTime);
      if (data.newoptionDate) {
        data.newInputShow = true
      }
    }
    else {
      data.newoptionDate = undefined,
        data.newInputShow = false
    }
  },
  cancelBtn(){
  //清空
  //在查询

    this.maintainDialogVisible=false
  },
  delConfirm(){
    //调用保存接口
  },
  //维护记录弹出框
  maintainRecordBtn(){
    this.medicalRecordDialogVisible=true
    //调用查询方法

  },
  //维护记录查询方法
  getData(){

  },

  handleSizeChange(val) {

    this.formSearch.pageSize = val
    this.getData()
  },
  handleCurrentChange(val) {
    this.formSearch.pageNum = val
    this.getData()
  },
  insuranceMainBtn(){
    //调用接口
    this.insuranceMainDialogVisible=true
  },

  insuranceSaveHandle(){

  },
  insuranceCloingHandle(){
    this.insuranceMainDialogVisible=false
  },

  addInsuranceRecordHandle(){
    const inRecord={
      insurancecode: '',
      netType: ''
    };
    this.insuranceRecordData.push(inRecord)
  },

  //移除
  delHandle(index,row){
    this.insuranceRecordData.splice(index,1)
  },

  // 校验数据
  validateForm () {
    let flag = null
    this.$refs['serviceForm'].validate(valid => {
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
.el-dialog{
  display: flex;
  flex-direction: column;
  margin:0 !important;
  position:absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
  /*height:600px;*/
  max-height:calc(100% - 30px);
  max-width:calc(100% - 30px);
}
.el-dialog .el-dialog__body{
  flex:1;
  overflow: auto;
}

</style>
