<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="60%">
      <el-card>
        <el-form :model="dynamicValidateForm"   ref="dynamicValidateForm" label-width="170px" size="mini">
          <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span style="font-size: 20px ;color:blue;" >转办</span>
            <el-divider/>
            <el-row>

              <el-form-item    v-for="(umCode, index) in dynamicValidateForm.umCode"
                               :label="'转办方UM账号:'"
                               :key="umCode.key"
                               :prop="'umCode.' + index + '.value'"
                               :rules="{
                               required: true, message: '域名不能为空', trigger: 'blur'
                               }"
              >
                <el-col :span="12">
                  <el-input v-model="umCode.value"></el-input>
                </el-col>
                <el-button size="mini" type="primary" @click="addDomain" v-show="index==0">增加行</el-button>
                <el-button size="mini" type="primary" @click="removeDomain(umCode)" v-show="index!=0">删除行</el-button>
              </el-form-item>


            </el-row>
            <el-row>
              <el-form-item label="转交处理原因:" prop="reason" :rules="{
             required: true, message: '转交处理原因不能而为空', trigger: 'blur'
            }">
                <el-input
                  type="textarea"
                  :rows="2"
                  placeholder="不超过500字符："
                  v-model="dynamicValidateForm.reason">
                </el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="附件文件：" prop="attachmentFile">

                <el-input v-model="dynamicValidateForm.attachmentFile" class="item-width"  size="mini" placeholder="请选择上传文件">
                </el-input>
                <el-button size="mini" type="primary" disabled>上传附件</el-button>
              </el-form-item>
            </el-row>
            <span style="float: right;">
          <el-button size="mini" type="primary" @click="submitButton('dynamicValidateForm')">提交</el-button>
          <el-button size="mini" type="primary" @click="changeDialogVisable">取消</el-button>
        </span>
          </div>


        </el-form>
      </el-card>
    </el-dialog>
  </div>
</template>

<script>
  import {transferSubmit} from "../../../../api/customService/demand";

  export default {
    name:'upLoad',
    data() {
      return {
        dynamicValidateForm: {
          umCode: [{
            key:'00000001',
            value: ''
          }],
          reason: "",
          workOrderNo: ""
        },
        serves: [],
        cs_service_item: [],
        dialogVisable: false,
      }
    },







    mounted() {
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
    },
    methods: {
      removeDomain(item) {
        const index = this.dynamicValidateForm.umCode.indexOf(item);
        if (index !== -1) {
          this.dynamicValidateForm.umCode.splice(index, 1)
        }
      },
      addDomain() {
        this.dynamicValidateForm.umCode.push({
          value: '',
          key: Date.now()
        });
      },
      //打开窗口
      open(){
        this.dialogVisable=true
      },
      submitButton(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let insert=this.dynamicValidateForm
            transferSubmit(insert).then(res => {
              if (res != null && res.code === 200) {
                this.$message.success("转办成功")
                if (res.rows.length <= 0) {
                  return this.$message.warning(
                    "失败！"
                  )
                }
              }
            }).catch(res => {

            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //确定保存数据
      // submitButton() {
      //   let insert=this.coOrganizerForm
      //   coOrganizerSubmit(insert).then(res => {
      //     if (res != null && res.code === 200) {
      //       alert("修改成功")
      //       if (res.rows.length <= 0) {
      //         return this.$message.warning(
      //           "失败！"
      //         )
      //       }
      //     }
      //   }).catch(res => {
      //
      //   })
      //
      // },
      //关闭对话框
      changeDialogVisable() {
        //清空对话框中的数据
        this.dialogVisable=false
      }
    }
  }

</script>

<style scoped>
  /deep/ .row-expand-cover .el-table__expand-column .el-icon {
    visibility: hidden;
  }

  .item-width {
    width: 200px;
  }

  .duty-p {
    width: 170px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

</style>
