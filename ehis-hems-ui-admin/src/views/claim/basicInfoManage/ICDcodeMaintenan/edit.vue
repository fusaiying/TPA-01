<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>ICD维护</span>
        <span style="float: right;" >
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-form
        v-loading="loading"
        ref="baseForm"
        :rules="baseFormRules"
        :model="baseForm"
        element-loading-text="拼命加载中"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="ICD编码：" prop="icdcode">
              <el-input :disabled="isShow" v-model="baseForm.icdcode" class="item-width" maxlength="20" show-word-limit clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="ICD中文名称：" prop="icdmname">
              <el-input v-model="baseForm.icdmname" class="item-width" clearable size="mini" maxlength="50" show-word-limit placeholder="请输入" @change="nameChange"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { addICDCode, updateICDCode, getICDList } from '@/api/baseInfo/ICDcodeMaintenan.js'
export default {
  data() {
    const checkChineseName = (rules, value, callback) => {
      let reg1 = /[`~!@#$%^&*()_\-+=<>?:"{}|,./;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/g;
      let reg2 = /.*[A-Za-z0-9]{1,}.*/;
      if (value === "") {
        callback(new Error("请录入中文名称"));
      } else if (reg1.test(value)) {
        callback(new Error("请录入中文名称"));
      } else if (reg2.test(value)) {
        callback(new Error("请录入中文名称"));
      } else {
        callback();
      }
    }
    return {
      isShow:false,
      baseForm: {
        icdcode: '',
        icdmname: ''
      },
      totalCount: 0,
      baseFormRules: {
        icdmname: [{ validator: checkChineseName, trigger: 'blur' }],
        /* englishName: [{ validator: checkEnglishName, trigger: 'blur' }],*/
        // name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        // englishName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        icdcode: [{ required: true, message: 'ICD编码不能为空', trigger: 'blur' }]
      },
      loading: false
    }
  },
  mounted() {
    if (this.$route.query.icdcode) {
      this.isShow=true
      //this.loading = true
      /*const params = this.baseForm
      params.pageNo = 0
      params.pageSize = 0*/
      this.baseForm.icdcode = this.$route.query.icdcode,
        this.baseForm.icdmname=this.$route.query.icdmname
      /*   this.getByICDCode(params)*/
    }else {
      this.isShow=false
    }
  },
  methods: {
    nameChange() {
      this.$refs.baseForm.validateField(['icdmname', 'icdcode'])
    },
    resetForm() {
      if(this.$route.query.icdcode) {
        this.baseForm.icdmname=undefined;
      }
      else {
        this.$refs.baseForm.resetFields();
      }
    },
    saveHandle() {
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          if (this.$route.query.icdcode) {
            // 编辑时保存
            updateICDCode(this.baseForm).then(res => {
              if (res.code === 200) {
                if(res.data!=121) {
                  this.$message({
                    message: '修改成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$store.dispatch("tagsView/delView", this.$route);
                  this.$router.push({
                    path: '/basic-info/icd',
                  })
                }
                else {
                  this.$message.warning('ICD已存在，请核实')
                }

              } else {
                this.$message({
                  message: '修改失败！',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
          }else {
            //  新增时保存
            addICDCode(this.baseForm).then(res => {
              if (res!=null && res.code === 200) {
                if(res.data!=121) {
                  this.$message({
                    message: '新增成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$store.dispatch("tagsView/delView", this.$route);
                  this.$router.push({
                    path: '/basic-info/icd',
                  })
                }
                else {
                  this.$message.warning('ICD已存在，请核实')
                }

              }


            })
          }
        } else {
          return false
        }
      })
    },
    /* //  打开方式为编辑时获取数据
     getByICDCode(params) {
       getICDList(params).then(res => {
         this.baseForm = res.data.list[0]
         this.loading = false
       })
     },*/
    goBack() {
      this.$router.go(-1)
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
