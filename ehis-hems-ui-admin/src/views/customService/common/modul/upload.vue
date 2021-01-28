<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="上传附件"
      width="40%"
      style="color: blue"
    >
      <el-form :model="baseForm"  label-width="170px" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
<!--          <span style="font-size: 20px">上传附件</span>-->
          <el-row>
            <el-form-item label="附件类型" prop="fjKind">
              <el-select v-model="baseForm.fjKind" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
          </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="附件文件" prop="fjFile">

              <el-input v-model="baseForm.fjFile" class="item-width"  size="mini" placeholder="请选择上传文件">
                <el-link href="https://www.baidu,com" target="_blank">上传文件</el-link>
              </el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="备注：" prop="textarea">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="不超过500字符："
                v-model="baseForm.textarea">
              </el-input>
            </el-form-item>
          </el-row>
          <span style="float: right;">
          <el-button size="mini" @click="defineButton">确定</el-button>
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name:'upLoad',
    props: {
      historicalProblemData:Array,
      value: {
        type: Boolean,
        default: false
      },
    },

    data() {
      return {
        dialogVisable:false,
        baseForm: {
          fjKind:"",
          fjFile:"",
          textarea:""

        }
    }
      },

    // watch: {
    //   value: function (newValue) {
    //     this.dialogVisable = newValue
    //   },
    //   historicalProblemData: function (newValue) {
    //     this.baseForm = newValue
    //   }
    // },


    mounted() {
        this.getDicts("cs_service_item").then(response => {
          this.cs_service_item = response.data;
          console.log("服务项目:",response)
        });
    },
    methods: {
      //打开窗口
      open(){
        console.log("调用到了子组件")
        this.dialogVisable=true
      },
      //确定保存数据
      defineButton(){

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
      },
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
