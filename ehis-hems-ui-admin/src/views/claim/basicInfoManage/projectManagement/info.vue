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
            <el-form-item label="类别：" prop="categoryCode">
              <el-select v-model="form.categoryCode"
                         @change="changeCategory"
                         class="item-width"
                         placeholder="请选择服务项目类别"
                         clearable
              >
                <el-option
                  v-for="dict in categoryOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="种类：" prop="servertypeCode">
              <el-select v-model="form.servertypeCode"
                         class="item-width"
                         placeholder="请选择服务项目种类"
                         clearable
              >
                <el-option
                  v-for="dict in serverTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务项目编码：" prop="serviceCode">
              <el-input :disabled="true"
                        v-model="form.serviceCode"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务项目名称：" prop="serviceName">
              <el-input v-model="form.serviceName"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="别名：" prop="alias">
              <el-input v-model="form.alias"
                        class="item-width"
                        clearable
                        maxlength="50"
                        size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="原名称：" prop="originalNname">
              <el-input v-model="form.originalName"
                        class="item-width"
                        clearable
                        size="mini"
                        maxlength="50"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="原8位编码：" prop="originalCode">
              <el-input v-model="form.originalCode"
                        class="item-width"
                        clearable
                        size="mini"
                        maxlength="8"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来源：" prop="orgin">
              <el-select v-model="form.orgin"
                         class="item-width"
                         placeholder="请选择服务项目来源"
                         clearable
              >
                <el-option
                  v-for="dict in orginOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col >
            <el-form-item label="服务条款：" prop="clause">
              <el-input
                type="textarea"
                :rows="3"
                maxlength="2000"
                placeholder="请输入内容"
                v-model="form.clause">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item class="queryForm_button">
            <el-button size="mini" type="success" @click="saveProject">保存</el-button>
            <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import { getCategoryType, getServerType, saveProjectInfo, getProjectById } from "@/api/provider/project";

  export default {
    name: "ProjectInfo",
    data() {
      let validateInput = (rule, value, callback) => {
        if (!this.checkSpecialKey(value)) {
          callback(new Error("不能含有特殊字符！！"));
        } else {
          callback();
        }
      };

      return {
        form: {
          categoryCode: undefined,
          servertypeCode: undefined,
          serviceCode: undefined,
          serviceName: undefined,
          alias: undefined,
          originalName: undefined,
          originalCode: undefined,
          orgin: undefined,
          clause: undefined
        },
        // 字典数据
        categoryOptions: [],
        serverTypeOptions: [],
        orginOptions: [],
        // 表单校验
        rules: {
          categoryCode: [
            {required: true, message: "类别不能为空", trigger: "change"}
          ],
          servertypeCode: [
            {required: true, message: "种类不能为空", trigger: "change"}
          ],
          serviceName: [
            {required: true, message: "服务项目名称不能为空", trigger: "blur"},
            { validator: validateInput, trigger: 'blur' }
          ],
          originalCode: [
            {pattern: /^[A-Za-z0-9]+$/, message: "支持录入字母、数字", trigger: "blur"},
            {len: 8, message: "请输入8位", trigger: "blur"}
          ]
        },
      }
    },
    async created() {
      if(this.$route.query.servicecode != null){
        const projectno = this.$route.query.servicecode;
        const serialNo = this.$route.query.serialNo;
        console.log("参数："+projectno);
        this.form.serialNo = serialNo;
        this.form.serviceCode = projectno;
        await getProjectById(this.form.serviceCode).then(response => {
          console.log("返回数据：",response)
          this.form = response.data;
        });
        console.log("category:"+this.form.categoryCode);
        getServerType(this.form.categoryCode).then(response => {
          console.log("res:",response);
          this.serverTypeOptions = response.data;
        });
      }
      getCategoryType().then(response => {
        this.categoryOptions = response.data;
      });
      this.getDicts("project_origin").then(response => {
        this.orginOptions = response.data;
      });
    },
    methods: {
      // 类别改变触动方法
      changeCategory(){
        this.form.servertypeCode = undefined;
        console.log("category:"+this.form.categoryCode)
        getServerType(this.form.categoryCode).then(response => {
          this.serverTypeOptions = response.data;
        });
      },
      saveProject() {
        this.$refs["form"].validate(valid => {
          if (!valid){
            return this.$message.warning(
              "请先录入必录项！"
            )
          }
          if (valid){
            saveProjectInfo(this.form).then(response => {
              if (response.code === 200) {
                this.form = response.data;
                this.msgSuccess("保存成功");
              }else{
                this.msgError(response.msg);
              }
            });
          }
          // else {
          //   if (this.form.categoryCode == undefined || this.form.categoryCode == null){
          //     this.msgError("请选择一个类别！");
          //     return false;
          //   }
          //   if (this.form.servertypeCode == undefined){
          //     this.msgError("请选择一个种类");
          //     return false;
          //   }
          //   if (this.form.serviceName == undefined || this.form.serviceName == ' '){
          //     this.msgError("请输入服务项目名称");
          //     return false;
          //   }
          // }
        });
        this.form.status = "01"

      },
      goBack() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.go(-1);
      },
      // 特殊符号校验
      checkSpecialKey(str) {
        let specialKey = "[`~!#$^&*()=|{}':;'\\[\\].<>/?~！#￥……&*（）——|{}【】‘；：”“'。，、？]‘'";
        for (let i = 0; i < str.length; i++) {
          if (specialKey.indexOf(str.substr(i, 1)) != -1) {
            return false;
          }
        }
        return true;
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

  .queryForm_button {
    text-align: right;
    margin: 0 10px 10px;
  }
</style>
