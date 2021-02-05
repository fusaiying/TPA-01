<template>
  <div>
    <!-- 一件均分弹框 start -->
    <el-dialog
      :visible.sync="dialogVisible"
      :dialog-visible="dialogVisible"
      :append-to-body="true"
      :before-close="handleClose"
      :close-on-click-modal="false"
      :show-close="true"
      width="540px">
        <el-form ref="userForm" :model="userForm" style="border:0;" label-width="110px" label-position="right" size="mini" :rules="rules" >
          <el-row>
            <el-col :span="24">
              <el-form-item label="角色：" prop="level">
                <el-select v-model="userForm.roleId"  size="mini" placeholder="请选择">
                  <el-option v-for="option in roles" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="分配状态">
                <el-radio-group v-model="userForm.status">
                  <el-radio  v-for="dict in statusOptions"  :key="dict.dictValue"  :label="dict.dictValue" >{{dict.dictLabel}}  </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer" >
          <el-button type="primary" @click="saveInfoFun" >确 定</el-button>
        </div>
    </el-dialog>
    <!-- 一件均分弹框 end -->
  </div>
</template>


<script>
  import {editInfoAverage } from '@/api/scheduling/claimApi'

  export default {
  props: {
    value: {
      type: Boolean,
      default: false
    },
    roleSelects: {
      roleSelects: Object,
      default: function () {
        return {}
      }
    },
  },
  watch: {
    value: function (newValue) {
      this.dialogVisible = newValue;
    },
    roleSelects: function (newVal){
      this.roles = newVal;

    },

  },
  data() {
    return {
        roles:[],
        dialogVisible:false,
        userForm : {
          roleId:'',
          status:'01',
        },
        rules: {
          roleId: {trigger: ['change'], required: true, message: '角色必填'},
          status: {trigger: ['change'], required: true, message: '分配状态必填'},
        },
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
    saveInfoFun(){
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          const params = this.userForm;
          editInfoAverage(params).then(response => {
            if (response.code == '200') {
              this.$message({
                message: '更新成功！',
                type: 'success',
                center: true,
                showClose: true
              });
              this.dialogVisible = false;
              this.$emit('gettableData');
              this.$emit('closeAssignDiaVisible');
            } else {
              this.$message({
                message: '更新失败！',
                type: 'error',
                center: true,
                showClose: true
              });
            }
          }).catch(error => {
            console.log(error);
          });
        }
      })
    },
    handleClose() {
      this.dialogVisible = false;
      this.$emit('closeAssignDiaVisible')
    },
    closeDialog(){
      this.dialogVisible = false;
      this.$emit('closeAssignDiaVisible')
    },
    changeDialogVisable() {
      //清空对话框中的数据
      // this.radio = undefined
      // this.copyRadio = undefined
      // this.expands = []
      // this.$refs.searchForm.resetFields()
      this.dialogVisible=false;
      this.$emit('closeAssignDiaVisible')
    },
  }
}
</script>

<style scoped>
  /deep/.item-width {
    width: 220px;
  }

  /deep/.el-table .warning-row {
    background: oldlace;
  }
  .font_grey {
    color: #BFBFBF;
  }
</style>

