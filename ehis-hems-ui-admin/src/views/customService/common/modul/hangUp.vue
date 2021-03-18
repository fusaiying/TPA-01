<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="工单挂起"
      width="40%"
      style="color: blue"
    >
      <el-form :model="hangUpForm"  label-width="170px" size="mini" >
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <el-row>
            <el-form-item label="挂起原因" prop="hangReason" >
              <el-select v-model="hangUpForm.hangReason" class="item-width" placeholder="请选择" :disabled="hangUpForm.hangFlag=='01'">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-row>
          <span style="float: right;">
          <el-button size="mini" @click="cancelHangUp" :disabled="hangUpForm.hangFlag=='02'" type="primary">案件解挂</el-button>
          <el-button size="mini" @click="HangUp" :disabled="hangUpForm.hangFlag=='01'" type="primary">挂起</el-button>
          <el-button size="mini" @click="changeDialogVisable" type="primary">取消</el-button>
        </span>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {cancelHangUp} from '@/api/customService/complaint'

  export default {
    name:'hangUp',
    props: {
      historicalProblemData:Array,
      value: {
        type: Boolean,
        default: false
      },
    },

    data() {
      return {
        cs_service_item:[],
        dialogVisable:false,
        hangUpForm: {
         workOrderNo:"",
          hangReason:"",
          hangFlag:"",
        }
      }
    },

    mounted() {
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
    },
    methods: {
      //案件解卦
      cancelHangUp(){
        let send=this.hangUpForm
        send.hangFlag="02"
        cancelHangUp(send).then(res=>{
          if (res != null && res.code === 200) {
            this.$emit('hangUpSearch');
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "解卦失败！"
              )
            }
          }
        }).catch(res => {

        })

      },
      //案件挂起
      HangUp(){
        let send=this.hangUpForm
        send.hangFlag="01"
        cancelHangUp(send).then(res=>{
          if (res != null && res.code === 200) {
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "挂起失败！"
              )
            }
          }
        }).catch(res => {

        })


      },

      //确定保存数据
      defineButton(){

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
