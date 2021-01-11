<template>
  <el-form ref="departmentFrom" :rules="departmentFormRules" :model="departmentFrom" size="small"
           :disabled="disabledFlag">
    <el-card class="box-card department-style" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>科室信息</span>
        <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
        </span>
      </div>
      <el-table ref="departmentTable" :data="departmentFrom.form"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column label="就诊类型" prop="visitingType" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id " :prop="'form.' + scope.$index + '.visitingType'"
                          :rules="departmentFormRules.visitingType">
              <el-select v-model="scope.row.visitingType" class="item-width" placeholder="请选择" clearable size="mini"
                         style="width:150px">
                <el-option label="测试01" value="01"></el-option>
                <el-option label="测试02" value="02"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>
            <span v-else>{{ scope.row.visitingType }}</span>
          </template>
        </el-table-column>
        <el-table-column label="一级科室" prop="firstDept" align="center" width="130">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.firstDept'"
                          :rules="departmentFormRules.firstDept">
              <el-input v-model="scope.row.firstDept" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.firstDept }}</span>
          </template>
        </el-table-column>
        <el-table-column label="二级科室" prop="secondDept" align="center" width="130">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.secondDept'"
                          :rules="departmentFormRules.secondDept">
              <el-input v-model="scope.row.secondDept" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.secondDept }}</span>
          </template>
        </el-table-column>
        <el-table-column label="就诊楼层" prop="visitingFloor" align="center" width="130">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.visitingFloor'"
                          :rules="departmentFormRules.visitingFloor">
              <el-input v-model="scope.row.visitingFloor" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.visitingFloor }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否特色科室" prop="charactdeptFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id">
              <el-select v-model="scope.row.charactdeptFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option value="01" label="是"></el-option>
                <el-option value="02" label="否"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>

            <span v-else>{{ scope.row.charactdeptFlag }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否直结" prop="straightknotFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id">
              <el-select v-model="scope.row.straightknotFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option value="01" label="是"></el-option>
                <el-option value="02" label="否"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>
            <span v-else>{{ scope.row.straightknotFlag }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否可医保卡" prop="cartevitalFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id ">
              <el-select v-model="scope.row.cartevitalFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option value="01" label="是"></el-option>
                <el-option value="02" label="否"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>
            <span v-else>{{ scope.row.cartevitalFlag }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否特需" prop="spprocurementFlag" align="center" width="160">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id">
              <el-select v-model="scope.row.spprocurementFlag" class="item-width" placeholder="请选择" clearable
                         size="mini" style="width:150px"
              >
                <el-option value="01" label="是"></el-option>
                <el-option value="02" label="否"></el-option>
                <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
              </el-select>
            </el-form-item>
            <span v-else>{{ scope.row.spprocurementFlag }}</span>
          </template>
        </el-table-column>
        <el-table-column label="出诊时间" prop="callTime" align="center" width="400">
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.' + scope.$index + '.callTime'"
                          :rules="departmentFormRules.callTime" align="center">
              <el-date-picker v-model="scope.row.callstarttime" type="datetime" placeholder="选择出诊开始时间" size="mini" format="yyyy-MM-DD HH:mm:ss" value-format="yyyy-MM-DD HH:mm:ss"
                              style="width: 190px;"/>
              <el-date-picker v-model="scope.row.callendtime" type="datetime" placeholder="选择出诊结束时间" size="mini" format="yyyy-MM-DD HH:mm:ss" value-format="yyyy-MM-DD HH:mm:ss"
                              style="width: 190px;padding-left: 10px"/>
            </el-form-item>
            <span v-else>{{ scope.row.callstarttime }}-{{ scope.row.callendtime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="100" fixed="right">
          <template slot-scope="scope">
                    <span>
                      <el-button type="text" size="mini" v-if="departmentTableShow && !scope.row.isSet"
                                 @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                       <el-button type="text" size="mini" v-if="departmentTableShow"
                                  @click="delHandle(scope.$index, scope.row)">删除</el-button>
                    </span>
          </template>
        </el-table-column>

      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addDepartmentHandle()"> + 添加
      </el-button>

    </el-card>
  </el-form>
</template>
<script>
import {getdepInfo, adddepInfo} from "@/api/baseInfo/medicalManage";

export default {
  props: {
    departmentFrom: {
      type: Object,
      default: function (){
        return {}
      }
    },
    departmentTableShow: Boolean,
    providerCode : String,
    disabledFlag : Boolean

  },

  data() {
    const checkCallTime = (rules, value, callback) => {
      if (!(this.departmentFrom.form[0].callendtime &&this.departmentFrom.form[0])) {
        callback(new Error('出诊时间不能空'))
      } else {
        callback()
      }
    }
    return {
      departmentFormRules: {
        visitingType: [{required: true, message: '不能为空！', trigger: 'change'}],
        firstDept: [{required: true, message: '不能为空！', trigger: 'blur'}],
        secondDept: [{required: true, message: '不能为空！', trigger: 'blur'}],
        visitingFloor: [{required: true, message: '不能为空！', trigger: 'blur'}],
        callTime: [{validator: checkCallTime, trigger: 'blur'}]
      },
    }
  },
    mounted() {
  /*  if(this.departmentFromData){
      this.departmentFrom.form=this.departmentFromData
    }*/
    /*if(this.departmentFrom.form  && this.departmentFrom.form.length>0){
      this.departmentTableShow=false
    }*/
  },
  methods: {
    //科室信息保存
    saveHandle() {
      this.$refs.departmentFrom.validate((valid) => {
        if (valid) {
          //存在调用科室信息保存的接口
          if (this.providerCode) {
            adddepInfo(this.departmentFrom.form).then(res => {
              if (res.code=='200') {
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
    },
    //科室删除按钮  是否调用了数据库的接口

    delHandle(index, row) {
      this.departmentFrom.form.splice(index, 1)
    },
    editHandle(index, row) {
      this.departmentFrom.form[index].id = ''
      this.departmentFrom.form[index].isSet = true

      this.departmentTableShow=true
    },
    //增加一行科室信息
    addDepartmentHandle() {
      const field = {
        visitingType: '',
        firstDept: '',
        secondDept: '',
        visitingFloor: '',
        charactdeptFlag: '',
        straightknotFlag: '',
        cartevitalFlag: '',
        spprocurementFlag: '',
        callTime: '',
        providerCode: this.providerCode,
        isSet: true,
      }


      this.departmentFrom.form.push(field)
    },

    //重置表单
    resetFrom(){
      this.$refs.departmentFrom.resetFields();
      this.departmentFrom.form=[]

    },

// 校验数据
    validateForm () {
      let flag = null
      this.$refs['departmentFrom'].validate(valid => {
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
</style>
