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
      width="35%">
        <el-form ref="userForm" :model="userForm" style="border:0;" label-width="30%" label-position="right" size="mini" :rules="rules" >
          <el-row>
            <el-col :span="24">
              <el-form-item label="交接人：" prop="userId">
                <el-select v-model="userForm.userId"  size="mini" placeholder="请选择">
                  <el-option v-for="option in users" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="承接人：" prop="takeOnUserId">
                <el-select v-model="userForm.takeOnUserId"  size="mini" placeholder="请选择">
                  <el-option v-for="option in users" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="承接状态" prop="status">
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
    <!-- 角色清单弹框 end -->
  </div>
</template>


<script>

  import { addInfo,editInfo  } from '@/api/scheduling/userApi'

  import { getDspatchUser } from '@/api/dispatch/api'
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
     this.editData = newVal;
     if( this.editData.type == 'edit') {
       this.userForm.takeOnId = newVal.rowdata.takeOnId;
       this.userForm.userId = newVal.rowdata.userId.toString();
       this.userForm.takeOnUserId = newVal.rowdata.takeOnUserId.toString();
       if(newVal.rowdata.status == 'Y' || newVal.rowdata.status == '01') {
        this.userForm.status = '01';
       } else {
         this.userForm.status = '02';
       }

     }
    },

  },
  data() {
    return {
      editData:{},
      custLevel:[],
        dialogVisible:false,
        userForm : {
          takeOnId:'',
          userId: '',
          takeOnUserId:'',
          status:'01',
        },
        rules: {
          userId: {trigger: ['change'], required: true, message: '交接人必填'},
          takeOnUserId: {trigger: ['change'], required: true, message: '承接人必填'},
          status: {trigger: ['change'], required: true, message: '金额上限必填'},
        },
      dialogVisible: false,
      // 承接人 交接人 users
       users:[],
      // 状态数据字典
      statusOptions: [],
    }
  },
  mounted(){
    this.getUserData();
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
          if( this.editData.type == 'edit') {
            this.updateData();
          } else {
            this.addData()
          }
        }
      })
    },
    addData(){
      const params = this.userForm;
      addInfo(params).then(response => {
        if (response.code == '200') {
          this.$message({
            message: '保存成功！',
            type: 'success',
            center: true,
            showClose: true
          });
          this.dialogVisible = false;
          this.$emit('gettableData');
          this.$emit('closeDialogVisable');
        } else {
          this.$message({
            message: '保存失败！',
            type: 'error',
            center: true,
            showClose: true
          });
        }
      }).catch(error => {
        console.log(error);
      });
    },
    updateData(){
      const params = this.userForm;
      editInfo(params).then(response => {
        if (response.code == '200') {
          this.$message({
            message: '更新成功！',
            type: 'success',
            center: true,
            showClose: true
          });
          this.dialogVisible = false;
          this.$emit('gettableData');
          this.$emit('closeDialogVisable');
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
    },
    getUserData () {
      const params = {
        pageNum:1,
        pageSize:1000,
        status:'0',
        delFlag:0,
        xtype:'getUserData'
      };
      getDspatchUser(params).then(response => {
        if(response.rows != null) {
          for(let i=0; i<response.rows.length; i++) {
            let obj= new Object();
            obj.dictLabel = response.rows[i].userName ;
            obj.dictValue = response.rows[i].userId.toString();
            this.users.push(obj);
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
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
