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
        <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="170px" size="mini">
          <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span style="font-size: 20px ;color:blue;">协办信息</span>
            <el-divider/>
            <el-row>

              <el-form-item v-for="(umCode, index) in dynamicValidateForm.umCode"
                            :label="'协办方UM账号:'"
                            :key="umCode.key"
                            :prop="'umCode.' + index + '.value'"
                            :rules="{
                               required: true, message: 'UM账号不能为空', trigger: 'blur'
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
              <el-form-item label="征求处理意见：" prop="solicitOpinion" :rules="{
             required: true, message: '征求处理意见', trigger: 'blur'
            }">
                <el-input
                  type="textarea"
                  :rows="2"
                  placeholder="不超过500字符："
                  v-model="dynamicValidateForm.solicitOpinion">
                </el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="附件类型：" prop="attachmentType">
                <el-select v-model="dynamicValidateForm.attachmentType" class="item-width" placeholder="请选择"
                           controls-position="right" :min="0">
                  <el-option v-for="item in serves" :key="item.value" :label="item.label"
                             :value="item.value"/>
                </el-select>
              </el-form-item>

            </el-row>
            <el-row>
              <el-form-item label="附件文件：" prop="attachmentFile">

                <el-input v-model="dynamicValidateForm.attachmentFile" class="item-width" size="mini"
                          placeholder="请选择上传文件">
                </el-input>
                <el-button size="mini" type="primary" disabled>上传附件</el-button>
              </el-form-item>
            </el-row>
            <span>
            *上传文件大小不能超过10MB<br>
            *支持文件格式：xls.doc.jpg.htm.bmp.tif.ppt.gif.mht.mpp.msg.rtf.rar.zip.txt.log.html.docx.xlsx.pptx.csv.pdf<br>
            需以html.htm格式上传的附件，请以htm上传
          </span>
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
import {coOrganizerSubmit} from "../../../../api/customService/demand";

export default {
  name: 'upLoad',
  data() {
    return {
      dynamicValidateForm: {
        umCode: [{
          key: '00000001',
          value: ''
        }],
        solicitOpinion: "",
        workOrderNo: ""
      },
      serves: [],
      // rules: {
      //   attachmentTypes: [{
      //     required: true, message: "UM账号必填", trigger: "blur"
      //   }
      //
      //   ],
      //   textarea: [{
      //     required: true, message: "出理原因必填", trigger: "blur"
      //   }
      //
      //   ]
      // },

      dialogVisable: false,
      coOrganizerForm: {
        umCode: "",
        solicitOpinion: "",
        workOrderNo: ""

      }
    }
  },
  mounted() {
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
    open() {
      this.dialogVisable = true
    },
    submitButton(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let insert = this.dynamicValidateForm
          coOrganizerSubmit(insert).then(res => {
            if (res != null && res.code === 200) {
              this.$message.success("协办成功")
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
      this.dialogVisable = false
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
