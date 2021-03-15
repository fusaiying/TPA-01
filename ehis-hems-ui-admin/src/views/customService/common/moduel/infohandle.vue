<template>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务处理</span>
      </div>
      <el-form ref="ruleForm" :model="infoForm" style="padding-bottom: 30px;" label-width="160px" :disabled="true"
               label-position="right" size="mini">
        <el-col :span="22">
          <el-form-item label="业务处理情况：">
            <el-row>
              <el-input  type="text" v-model="infoForm.businessProcess"/>
            </el-row>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="处理说明：" >
            <el-row>
              <el-input type="textarea" :row="2" v-model="infoForm.remark"/>
            </el-row>
          </el-form-item>
        </el-col>
      </el-form>
    </el-card>
</template>

<script>
export default {
  name: "infohandle",
  props: {
    isDisabled: {
      type:Boolean,
      default:false
    },
    acceptInfo: {
      type: Object,
      default: function (){
        return {}
      }
    },
  },
  watch: {
    acceptInfo: function (newValue){
      this.acceptData = newValue
      console.log("infohandleValue");
      console.info(newValue);
      this.infoForm.businessProcess= this.acceptData.businessProcess,
      this.infoForm.remark= this.acceptData.remark
    }
  },
  data(){
    return {
      infoForm:{
        businessProcess:'',
        remark:'',
      },

    }
  },
  created() {

  },
  methods: {

  }
}
</script>

<style scoped>
  .el-tooltip__popper {
    max-width: 400px;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>
