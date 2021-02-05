<template>
  <div>
    <!-- 角色清单弹框 start -->
    <el-dialog
      :visible.sync="dialogVisible"
      :dialog-visible="dialogVisible"
      :append-to-body="true"
      :before-close="handleClose"
      :close-on-click-modal="false"
      :show-close="true"
      width="37%">
      <el-card class="box-card">
        <el-form ref="ruleForm" :model="ruleForm" style="border:0;" label-width="180px" label-position="right" size="mini" :rules="rules" >

          <el-row>
            <el-col :span="24">
              <el-form-item label="规则编码：" prop="rate">
                <el-input v-model="ruleForm.rate"  class="item-width" size="mini" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="案件类型：" prop="level">
                <el-select v-model="ruleForm.level"  size="mini" class="item-width" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="赔付结论：" prop="level">
                <el-select v-model="ruleForm.level"  size="mini" class="item-width" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="金额类型：" prop="level">
                <el-select v-model="ruleForm.level"  size="mini" class="item-width" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="抽检金额：" prop="rate">
                <el-input v-model="ruleForm.rate"  class="item-width"  size="mini" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="抽检比例：" prop="rate">
                <el-input v-model="ruleForm.rate"  class="item-width" size="mini" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="承接状态：">
                <el-radio-group v-model="ruleForm.status">
                  <el-radio  v-for="dict in statusOptions"  :key="dict.dictValue"  :label="dict.dictValue" >{{dict.dictLabel}}  </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <span style="margin-right:50px; margin-bottom: 25px;float: right;" class="dialog-footer">
            <el-button size="mini" type="primary">确认</el-button>
        </span>
      </el-card>
    </el-dialog>
    <!-- 角色清单弹框 end -->
  </div>
</template>


<script>
  export default {
  props: {
    value: {
      type: Boolean,
      default: false
    },
    fixInfo: {
      type: Object,
      default: function () {
        return {}
      }
    },
  },
  watch: {
    value: function (newValue) {
      this.dialogVisible = newValue;
    },
    fixInfo: function (newVal){
      console.log('------------')
      console.log(newVal)
      console.log('------------')

    },

  },
  data() {
    return {
      custLevel:[],
        dialogVisible:false,
        ruleForm : {
          level: '',
          recMessageFlag:'',
          debtAmountUp :'',
          debtWhitelistId:'',
          insuredNo:'',
          status:'01',
          rate:'',
        },
        rules: {
          level: {trigger: ['change'], required: true, message: '等级必填'},
          recMessageFlag: {trigger: ['change'], required: true, message: '缴费通知必填'},
          debtAmountUp: {trigger: ['change'], required: true, message: '金额上限必填'},
        },
      dialogVisible: false,
      // 状态数据字典
      statusOptions: [],
    }
  },
  mounted(){

  },
  created() {
    this.getDicts("state").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    handleClose() {
      this.dialogVisible = false;
      this.$emit('closeDialogVisable')
    },
    closeDialog(){
      this.dialogVisible = false;
      this.$emit('closeDialogVisable')
    },
    changeDialogVisable() {
      //清空对话框中的数据
      // this.radio = undefined
      // this.copyRadio = undefined
      // this.expands = []
      // this.$refs.searchForm.resetFields()
      this.dialogVisible=false;
      this.$emit('closeDialogVisable')
    },
  }
}
</script>

<style scoped>
  ::v-deep.item-width {
    width: 220px;
  }

  ::v-deep.el-table .warning-row {
    background: oldlace;
  }
  .font_grey {
    color: #BFBFBF;
  }
</style>

