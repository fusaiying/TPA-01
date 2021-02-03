<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>申诉信息</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
      </div>
      <el-form ref="appealForm" :inline="true" :model="appealForm"
               style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" label-position="right"
               size="mini">
        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="协谈类型：" prop="fileType">
              <el-select v-model="appealForm.discussType" class="item-width" size="mini" placeholder="请选择">
              </el-select>
            </el-form-item>

            <el-form-item label="发起人：" prop="operator">
              <el-input v-model="appealForm.operator" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="getTableData"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="申诉原因：" prop="fileType">
              <el-select v-model="appealForm.reasion" class="item-width" size="mini" placeholder="请选择">
              </el-select>
              <el-select v-model="appealForm.resion2" class="item-width" size="mini" placeholder="核保原因">
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="申诉意见说明：" prop="remark">
              <el-input style="min-width: 520px" col="2" type="textarea" row="4" maxlength="1000"
                        v-model="appealForm.remark" clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <el-card class="box-card">
      <el-form ref="appealForm2" :inline="true" :model="appealForm2"
               style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" label-position="right"
               size="mini">
        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="初审决定：" prop="fileType">
              <el-select v-model="appealForm2.decide" class="item-width" size="mini" placeholder="请选择">
              </el-select>
            </el-form-item>

            <el-form-item label="初审人：" prop="operator">
              <el-input v-model="appealForm2.operator2" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="getTableData"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="处理意见：" prop="remark">
              <el-input style="min-width: 520px" col="2" type="textarea" row="4" maxlength="1000"
                        v-model="appealForm2.remark" clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </el-dialog>
</template>
<script>

  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo: Object,//可以获取报案号 this.fixInfo.rptNo
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
    },
    data() {
      return {
        dialogVisable: false,
        appealForm: {
          discussType: '',
          operator: '',
          reasion: '',
          resion2: '',
          remark: '',
        },
        appealForm2: {
          decide: '',
          operator2: '',
          remark: '',

        },
        surveyInfo: {},
      }
    },

    mounted() {
      this.getDetail();
    },
    methods: {
      getDetail() {

      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeAppealDialog')
      },
    }
  }
</script>

<style scoped>
  .small-input {
    width: 80%;
  }

  .to_right {
    width: 120px;
    text-align: right;
  }

  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
</style>
