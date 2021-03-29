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
      width="540px">
        <el-form ref="userForm" :model="userForm" style="border:0;" label-width="180px" label-position="right" size="mini" :rules="rules" >
          <el-row>
            <el-col :span="24">
              <el-form-item label="角色：" prop="roleCode">
                <el-select disabled v-model="userForm.roleCode"  size="mini" class="item-width" placeholder="请选择">
                  <el-option v-for="option in roles" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="操作用户：" prop="userName">
                <el-select disabled v-model="userForm.userName"  size="mini" class="item-width" placeholder="请选择">
                  <el-option v-for="option in users" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="分配比例：" prop="rate">
                <el-input v-model="userForm.rate"  class="item-width" size="mini" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item  prop="status" label="分配状态">
                <el-radio-group v-model="userForm.status">
<!--                  <el-radio  v-for="dict in statusOptions"  :key="dict.dictValue"  :label="dict.dictValue" >{{dict.dictLabel}}  </el-radio>-->
                  <el-radio label="Y">有效</el-radio>
                  <el-radio label="N">无效</el-radio>
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

  import {editInfo ,roleInfo } from '@/api/scheduling/claimApi'

  import {getUserInfo, getUsersByOrganCode} from '@/api/claim/standingBookSearch'

  // import { getDspatchUser } from '@/api/dispatch/api'
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
    roleSelects: {
      type: Array,
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
        this.userForm.roleCode  = newVal.rowdata.roleCode;
        this.userForm.rate = newVal.rowdata.rate;
        this.userForm.userName = newVal.rowdata.userName;
        this.userForm.distId = newVal.rowdata.distId
        this.userForm.orangeCode = newVal.rowdata.orangeCode;
        if(newVal.rowdata.status === 'Y' || newVal.rowdata.status === '01') {
          this.userForm.status = 'Y';
        } else {
          this.userForm.status = 'N';
        }
      }
    },
    roleSelects: function (newVal){
      this.roles = newVal;
    },
  },
  data() {
    return {
      editData:{},
      custLevel:[],
        dialogVisible:false,
        userForm : {
          distId:'',
          userName: '',
          roleCode:'',
          rate :'',
          status:'',
          orangeCode:'',
        },
        rules: {
          roleCode: {trigger: ['change'], required: false, message: '角色必填'},
          userName: {trigger: ['change'], required: true, message: '操作用户必填'},
          rate: {trigger: ['change'], required: true, message: '分配比例必填'},
          status: {trigger: ['change'], required: true, message: '分配状态必填'},
        },
      roles:[],
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
          if(this.userForm.status == '01') {
            this.userForm.status = 'Y';
          } else {
            this.userForm.status = 'N';
          }

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
        }
      })
    },
    getUserData () {
      getUserInfo().then(res => {
        if (res != null && res.code === 200) {
          let option = {
            organCode: res.data.organCode,
            pageNum: 1,
            pageSize: 200,
          }
          getUsersByOrganCode(option).then(response => {
            if (response != null && response.code === 200) {
              //console.log("response getUsersByOrganCode",response)
              for (let i = 0; i < response.rows.length; i++) {
                let obj = new Object();
                let resdata = response.rows[i]
                let userName = resdata.userName;
                obj.dictLabel = userName;
                obj.dictValue = userName;
                this.users.push(obj);
              }
            }
          })
        }
      })
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

