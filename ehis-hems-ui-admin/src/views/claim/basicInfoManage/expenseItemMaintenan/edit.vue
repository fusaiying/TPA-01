<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>费用项维护</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-form
        v-loading="loading"
        ref="baseForm"
        :rules="rules"
        :model="baseForm"
        style="padding-bottom: 30px;"
        element-loading-text="拼命加载中"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="费用项编码：" prop="feeitemcode">
              <el-input :disabled="isFeeitemcodeShow" v-model="baseForm.feeitemcode" class="item-width" clearable
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="费用项中文名称：" prop="feeitemname">
              <el-input v-model="baseForm.feeitemname" class="item-width" clearable size="mini" placeholder="请输入"
                        @change="nameChange"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item class="queryForm_button">
            <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
            <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import {listFeeitem, addFeeitem, updateFeeitem} from '@/api/baseInfo/expenseitemMaintenan'

  export default {
    data() {
      const checkChineseName = (rules, value, callback) => {
        let reg1 = /[`~!@#$%^&*()_\-+=<>?:"{}|,./;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/g;
        let reg2 = /.*[A-Za-z0-9]{1,}.*/;
        if (!value) {
          callback(new Error("费用项中文名称不能为空"));
        } else {
          if (reg1.test(value)) {
            callback(new Error("请录入中文名称"));
          } else if (reg2.test(value)) {
            callback(new Error("请录入中文名称"));
          } else {
            callback();
          }
        }
      }
      const checkFeeitemCode = (rules, value, callback) => {
        if (!value) {
          callback(new Error("费用项编码不能为空"));
        } else {
          callback();
        }
      }
      return {
        isFeeitemcodeShow: false,
        baseForm: {
          feeitemname:'',
          feeitemcode:''
        },
        totalCount: 0,
        rules: {
          //feeitemcode: [{required: this.$route.query.feeitemcode, message: '不可为空', trigger: 'blur'}],
          feeitemname: [{validator: checkChineseName, required: true, trigger: 'blur'}],
          feeitemcode: [{validator: checkFeeitemCode, required: true, trigger: 'blur'}],
        },
        loading: false
      }
    },
    mounted() {
      if (this.$route.query.feeitemcode) {
        this.isFeeitemcodeShow = true
        //this.loading = true
        this.baseForm = {
          feeitemcode: this.$route.query.feeitemcode,
          feeitemname: this.$route.query.feeitemname,
          pageNum: 1,
          pageSize: 5,
        }
        listFeeitem(this.baseForm).then(res => {
          this.baseForm = res.rows[0]
          this.loading = false
        })
      } else {
        this.isFeeitemcodeShow = false
      }
    },
    methods: {
      nameChange() {
        this.$refs.baseForm.validateField(['feeitemname'])
      }
      ,
      resetForm() {
        if (this.$route.query.feeitemcode) {
          this.baseForm.feeitemname = ''
        } else {
          this.baseForm.feeitemcode = ''
          this.baseForm.feeitemname = ''
        }
      },
      saveHandle() {
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            const params = this.baseForm
            if (this.$route.query.feeitemcode) {
              updateFeeitem(params).then(res => {
                if (res != null && res.code === 200 && res.data===1) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$store.dispatch("tagsView/delView", this.$route);
                  this.$router.go(-1)
                }else if (res != null && res.code === 200 && res.data===0){
                  return this.$message.warning(
                    "费用项已存在，请核实！"
                  )
                }

              }).catch(error => {

              })
            } else {
              addFeeitem(params).then(res => {
                if (res != null && res.code === 200 && res.data===1) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$store.dispatch("tagsView/delView", this.$route);
                  this.$router.go(-1)
                }else if (res != null && res.code === 200 && res.data===0){
                  return this.$message.warning(
                    "费用项已存在，请核实！"
                  )
                }
              }).catch(error => {

              })
            }
          } else {
            return false
          }
        })
      },
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

  .queryForm_button {
    text-align: right;
    margin: 0 10px 10px;
  }
</style>
