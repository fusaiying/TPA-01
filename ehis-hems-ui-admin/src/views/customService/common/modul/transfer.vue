<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="80%">
      <el-form :model="transferForm"  label-width="170px" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">请选择转交接收方UM账号：</span>
          <el-row>
            <el-form-item  prop="umCode">
              <el-select v-model="transferForm.umCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
            <el-form-item prop="attachmentTypes">
              <el-input size="mini" v-model="transferForm.attachmentTypes"></el-input>
            </el-form-item>
            <el-form-item>
              <span style="color: gray">下拉框只能选择一个主账号，文本框可以填写多个以“，”分隔</span>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="转交处原因：" prop="reason">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="不超过500字符："
                v-model="transferForm.reason">
              </el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="附件文件" prop="attachmentFile">

              <el-input v-model="transferForm.attachmentFile" class="item-width"  size="mini" placeholder="请选择上传文件">
                <span ><a href="www.baidu,com">上传文件</a></span>
              </el-input>
            </el-form-item>
          </el-row>

          <span style="float: right;">
          <el-button size="mini" @click="transferButton">确定</el-button>
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
        </div>


      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {transferSubmit} from "../../../../api/customService/demand";

  export default {
    name:'upLoad',
    //接受父传值
    props: {
      historicalProblemData: Array,
      value: {
        type: Boolean,
        default: false
      },
    },


      data() {
        return {
          cs_service_item:[],
          rules: {
            attachmentTypes: [{
              required: true, message: "UM账号必填", trigger: "blur"
            }

            ],
            textarea: [{
              required: true, message: "出理原因必填", trigger: "blur"
            }

            ]
          },

          //workOrderNo:"",
          dialogVisable: false,
          transferForm: {
            umCode: "",
            reason: "",
            textarea: "",
            workOrderNo:""

          }
        }
      },

      watch: {
        value: function (newValue) {
          this.dialogVisable = newValue
        },
        historicalProblemData: function (newValue) {
          this.baseForm = newValue
        }
      },


      mounted() {
        this.getDicts("cs_service_item").then(response => {
          this.cs_service_item = response.data;
          console.log("服务项目:", response)
        });
      },
      methods: {
        //提交页面数据
        transferButton(){
          let insert=this.transferForm
         // insert.workOrderNo=this.workOrderNo
          transferSubmit(insert).then(res => {
            if (res != null && res.code === 200) {
              console.log("insert",insert)
              alert("修改成功")
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "失败！"
                )
              }
            }
          }).catch(res => {

          })

        },

        //打开窗口
        open(){
          console.log("调用到了子组件")
          this.dialogVisable=true
        },

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
