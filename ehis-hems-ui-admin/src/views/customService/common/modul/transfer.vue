<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="60%">
      <el-form :model="transferForm"  label-width="200px" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px;color: blue">转办：</span>
          <el-divider/>
          <el-row>
            <el-form-item  prop="umCode" label="请选择转交接收方UM账号："
            :rules="{ required: true, message: '转交处原因不能为空', trigger: 'blur' }"
            >

              <el-select v-model="transferForm.umCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
              <el-col :span="12">
                <el-form-item prop="umCodes">
                  <el-input size="mini" v-model="transferForm.umCodes" placeholder="文本框可以填写多个以“，”分隔"></el-input>
                </el-form-item>
              </el-col>
            </el-form-item>
<!--            <el-col :span="10">-->
<!--              <el-form-item>-->
<!--                <span style="color: gray">下拉框只能选择一个主账号，文本框可以填写多个以“，”分隔</span>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
          </el-row>
          <el-row>
            <el-form-item label="转交处原因：" prop="reason">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="不超过500字符："
                v-model="transferForm.reason"
                :rules="{ required: true, message: '转交处原因不能为空', trigger: 'blur' }"
              >
              </el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="附件文件：" prop="attachmentFile">

              <el-input v-model="transferForm.attachmentFile" class="item-width"  size="mini" placeholder="请选择上传文件" >
                <span ><a href="www.baidu,com">上传文件</a></span>
              </el-input>
              <el-button type="primary" size="mini" disabled>上传文件</el-button>
            </el-form-item>
          </el-row>

          <span style="float: right;">
          <el-button size="mini" @click="transferButton" type="primary">确定</el-button>
          <el-button size="mini" @click="changeDialogVisable" type="primary">返回</el-button>
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

          },

          dialogVisable: false,
          transferForm: {
            umCode: "",
            umCodes:"",
            reason: "",
            // textarea: "",
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
