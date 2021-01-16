<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="80%">
      <el-form v-for="option in baseForm"  label-width="170px" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">历史问题件信息</span>
          <span style="float: right;">
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
        </div>


      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
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
        baseForm:[]
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


    mounted() {},
    methods: {
      //关闭对话框
      changeDialogVisable() {
        //清空对话框中的数据
        this.$emit('closeHistoricalProblem')
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
