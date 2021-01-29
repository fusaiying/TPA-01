<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="80%">
      <el-form :model="coOrganizerForm"  label-width="170px" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">协办方UM账号：</span>
          <el-row>
            <el-form-item  prop="attachmentType">
              <el-select v-model="coOrganizerForm.attachmentType" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
            <el-form-item prop="attachmentTypes">
              <el-input size="mini" v-model="coOrganizerForm.attachmentTypes"></el-input>
            </el-form-item>
            <el-form-item>
              <span style="color: gray">下拉框只能选择一个主账号，文本框可以填写多个以“，”分隔</span>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="征求处理意见：" prop="textarea">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="不超过500字符："
                v-model="coOrganizerForm.textarea">
              </el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="附件类型：" prop="attachmentType">
              <el-select v-model="coOrganizerForm.attachmentType" class="item-width" placeholder="请选择" controls-position="right" :min="0">
                <el-option v-for="item in serves" :key="item.value" :label="item.label"
                           :value="item.value"/>
              </el-select>
            </el-form-item>

          </el-row>
          <el-row>
            <el-form-item label="附件文件" prop="attachmentFile">

              <el-input v-model="coOrganizerForm.attachmentFile" class="item-width"  size="mini" placeholder="请选择上传文件">
              </el-input>
              <el-button>上传附件</el-button>
            </el-form-item>
          </el-row>
          <span >

            *上传文件大小不能超过10MB<br>
            *支持文件格式：xls.doc.jpg.htm.bmp.tif.ppt.gif.mht.mpp.msg.rtf.rar.zip.txt.log.html.docx.xlsx.pptx.csv.pdf<br>
            需以html.htm格式上传的附件，请以htm上传

          </span>

          <span style="float: right;">
          <el-button size="mini" @click="defineButton">提交</el-button>
          <el-button size="mini" @click="changeDialogVisable">取消</el-button>
        </span>
        </div>


      </el-form>
    </el-dialog>
  </div>
</template>

<script>
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


        dialogVisable: false,
        coOrganizerForm: {
          attachmentType: "",
          attachmentTypes: "",
          attachmentFile: "",
          textarea: ""

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
      //打开窗口
      open(){
        console.log("调用到了子组件")
        this.dialogVisable=true
      },
      //确定保存数据
      defineButton() {

      },
      //关闭对话框
      changeDialogVisable() {
        //清空对话框中的数据
        this.dialogVisable=false
        /* console.log('-------')
       this.radio = undefined
       this.copyRadio = undefined
       this.expands = []
       this.$refs.searchForm.resetFields()
       this.$emit('closeProblemShipment')*/
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
