<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="beforeClose"
      :show-close="false"
      width="80%">

      <el-form ref="searchForm" :model="searchForm" label-width="170px" size="mini">
        <div style="line-height: 50px;  border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>赔付结论</span>
          <span style="float: right;">
          <el-button size="mini"  @click="">退回处理</el-button>
        </span>
        </div>
        <div style="text-align: right; margin-right: 10px;"/>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
  export default {
    props: {
      show: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        dialogVisable: false,
      }
    },
    watch: {
      show: function (value){
        this.dialogVisable = value

      }
    },


    mounted() {
    },
    methods: {
      beforeClose(){
        this.dialogVisable = false;
        this.$emit('closeDialogVisable')
      },
      showDialog(){
        this.dialogVisable=true
      },
    }
  }
</script>
