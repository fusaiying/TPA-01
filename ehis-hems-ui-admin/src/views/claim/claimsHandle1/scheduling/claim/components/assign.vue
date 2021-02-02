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
      width="30%">
      <el-card class="box-card">
        <el-form ref="userForm" :model="userForm" style="border:0;" label-width="110px" label-position="right" size="mini" :rules="rules" >
          <el-row>
            <el-col :span="24">
              <el-form-item label="角色：" prop="level">
                <el-select v-model="userForm.level"  size="mini" placeholder="请选择">
                  <el-option v-for="option in users" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
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
        <span style="margin-right:50px; margin-bottom: 25px;float: right;" class="dialog-footer">
            <el-button size="mini" type="primary">确认</el-button>
        </span>
      </el-card>
    </el-dialog>
    <!-- 一件均分弹框 end -->
  </div>
</template>


<script>
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
      console.log('------------')
      console.log(newVal)
      console.log('------------')

    },

  },
  data() {
    return {
      custLevel:[],
        dialogVisible:false,
        userForm : {
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
            obj.dictValue = response.rows[i].userName;
            this.users.push(obj);
          }
        }
      }).catch(error => {
        console.log(error);
      });
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

