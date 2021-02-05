<template>

  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="70%" height="100%">
    <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
      <span style="font-size: 20px">历史问题件信息</span>
      <span style="float: right;">
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
    </div>
    <el-card style="border: 0; margin-top: -20px; margin-bottom: 30px" v-for="item in baseForm" :key="item.problemId">
      <el-form label-width="120px" size="mini" disabled><!-- v-for="option in baseForm"-->
        <el-col :span="8">
          <el-form-item label="问题件类型：" prop="problemType">
            <el-input v-model="item.problemType" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="发起时间：" prop="createTime">
            <el-input v-model="item.createTime" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="发起人：" prop="createBy">
            <el-input v-model="item.createBy" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="转出意见：" prop="problemView">
            <el-input v-model="item.problemView" type="textarea" :rows="5" class="item-widths" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="处理结论：" prop="conclusion">
            <el-input v-model="item.conclusion" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="处理时间：" prop="updateTime">
            <el-input v-model="item.updateTime" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="处理人：" prop="updateBy">
            <el-input v-model="item.updateBy" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="处理意见：" prop="conclusionView">
            <el-input v-model="item.conclusionView" type="textarea" :rows="5" class="item-widths" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>

      </el-form>
    </el-card>
  </el-dialog>
</template>

<script>
  export default {
    props: {
      historicalProblemData: Array,
      value: {
        type: Boolean,
        default: false
      },
    },

    data() {
      return {
        dialogVisable: false,
        baseForm: [
          {
            problemType:'01'
          },
          {
            problemType:'01'
          }
        ]
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
    },
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
  ::v-deep .row-expand-cover .el-table__expand-column .el-icon {
    visibility: hidden;
  }

  .item-width {
    width: 180px;
  }
  .item-widths {
    width: 650px;
  }

  .duty-p {
    width: 170px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

</style>
