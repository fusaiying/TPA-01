<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>基本信息</span>
      </div>
      <el-form
        ref="form"
        :rules="rules"
        :model="form"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="医生编码：" prop="docCode">
              <el-input :disabled="true"
                        v-model="form.docCode"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医生姓名：" prop="docName">
              <el-input v-model="form.docName" placeholder="请输入医生姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系电话：" prop="docPhone">
              <el-input v-model="form.docPhone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属服务机构：" prop="supplierList">
              <el-select v-model="form.supplierList" multiple placeholder="请选择" class="item-width">
                <el-option
                  v-for="item in supplierOptions"
                  :key="item.supplierCode"
                  :label="item.comment"
                  :value="item.supplierCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="一级科室：" prop="fiestDept">
              <el-input v-model="form.fiestDept"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级科室：" prop="secondDept">
              <el-input v-model="form.secondDept"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="就诊时间：" prop="startTime">
              <el-input v-model="form.startTime"
                        class="item-width"
                        size="mini"
                        clearable
                        placeholder="请输入就诊时间，中间用-连接"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col >
            <el-form-item label="备注：" prop="remark">
              <el-input
                type="textarea"
                :rows="3"
                placeholder="请输入内容"
                v-model="form.remark" maxlength="2000">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item class="queryForm_button">
            <el-button size="mini" type="success" @click="saveDoctor">保存</el-button>
            <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import {listDoc, getDoctorInfo, getSupplierOptions, saveDoctor} from "@/api/provider/doctor";

  export default {
    name: "doctorInfo",
    data() {
      const checkPhone = (rules, value, callback) => {
        if (value) {
          if (this.form.copyPhone !== value) {
            let query = {
              docPhone: this.form.docPhone
            }
            //调用查询的接口
            listDoc(query).then(res => {
              if (res != null && res.code == '200' && res.rows.length > 0) {
                callback(new Error('联系电话已存在'))
              } else {
                callback()
              }
            })
          } else {
            callback()
          }
        }
        else {
          callback()
        }
      }
      // const checkName = (rules, value, callback) => {
      //   if (value) {
      //     if (this.form.copyName !== value) {
      //       let query = {
      //         docName: this.form.docName
      //       }
      //       //调用查询的接口
      //       listDoc(query).then(res => {
      //         if (res != null && res.code == '200' && res.rows.length > 0) {
      //           callback(new Error('医生姓名已存在'))
      //         } else {
      //           callback()
      //         }
      //       })
      //     } else {
      //       callback()
      //     }
      //   }
      //   else {
      //     callback()
      //   }
      // }
      const checkSupplierList = (rules, value, callback) => {
        if (this.form.supplierList.length == 0){
          callback(new Error("至少选择一家服务机构"))
        } else {
          callback()
        }
      }
      return {
        form: {
          docCode: undefined,
          docName: undefined,
          docPhone: undefined,
          supplierList: [],
          fiestDept: undefined,
          secondDept: undefined,
          startTime: undefined,
          remark: undefined,
          copyPhone: undefined,
          copyName: undefined
        },
        supplierOptions: [],
        // 校验
        rules: {
          docName: [{required:true, message:"请输入医生姓名",trigger:'blur'},
            { max:20, message: "长度不超过20字符", trigger: 'blur'}],
          // {validator: checkName,trigger:'blur'},
          docPhone: [{required:true, message:'请输入联系电话', trigger:'blur'},
            {pattern:/^[0-9]*$/,message:"请输入正确的联系方式",trigger: 'blur'},
            {max: 20,message: "最大支持20位",trigger: 'blur'},
            {validator: checkPhone,trigger:'blur'}],
          supplierList: [{validator: checkSupplierList,trigger:'change'}],
          fiestDept: [{required:true, message:"请输入一级科室信息",trigger:'blur'},
            { max:50, message: "长度不超过50", trigger: 'blur'}],
          secondDept: [{required:true, message:"请输入二级科室信息",trigger:'blur'},
            { max:50, message: "长度不超过50", trigger: 'blur'}],
          startTime: [{required:true, message:"请输入就诊时间",trigger:'blur'},
            { max:100, message: "长度不超过100字符", trigger: 'blur'}],
          remark: [{ max:2000, message: "长度不超过2000", trigger: 'blur'}]
        }
      }
    },
    created() {
      const docno = this.$route.params && this.$route.params.docno;
      if (docno != '0'){
        getDoctorInfo(docno).then(response => {
          console.log("doctor返回数据：",response)
          this.form = response.data;
          this.form.copyName = response.data.docName;
          this.form.copyPhone = response.data.docPhone;
        })
      }
      // 获取所属服务机构下拉框
      getSupplierOptions().then(res =>{
        if (res.code == 200) {
          this.supplierOptions = res.data;
          console.log("data:", this.supplierOptions)
        }
      })
    },
    methods: {
      // 保存按钮
      saveDoctor(){
        this.$refs["form"].validate(valid => {
          if (valid){
            saveDoctor(this.form).then(response => {
              if (response.code == 200) {
                this.form = response.data;
                this.form.copyName = response.data.docName;
                this.form.copyPhone = response.data.docPhone;
                this.msgSuccess("保存成功");
                this.$store.dispatch("tagsView/delView", this.$route);
                this.$router.go(-1);
              }else{
                this.msgError(response.msg);
              }
            })
          } else {
            return this.$message.warning(
              "请先录入必录项！"
            )
          }
        })
      },
      // 关闭按钮
      goBack() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.go(-1);
      }
    },
  }
</script>
