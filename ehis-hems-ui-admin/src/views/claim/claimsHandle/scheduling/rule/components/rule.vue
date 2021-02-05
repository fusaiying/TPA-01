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
        <el-form ref="ruleForm" :model="ruleForm" style="border:0;" label-width="180px" label-position="right" size="mini" :rules="rules" >

          <el-row>
            <el-col :span="24">
              <el-form-item label="规则编码：" prop="checkRuleNo">
                <el-input disabled v-model="ruleForm.checkRuleNo"  class="item-width" size="mini" placeholder="系统自动生成" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="案件类型：" prop="caseType">
                <el-select v-model="ruleForm.caseType"  size="mini" class="item-width" placeholder="请选择">
                  <el-option v-for="option in caseSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="赔付结论：" prop="payConclusion">
                <el-select v-model="ruleForm.payConclusion" multiple size="mini" class="item-width" placeholder="请选择">
                  <el-option v-for="option in conSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="金额类型：" prop="amountType">
                <el-select v-model="ruleForm.amountType"  size="mini" class="item-width" placeholder="请选择">
                  <el-option v-for="option in amountSelet" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="抽检金额：" prop="checkAmount">
                <el-input  maxlength="14" v-model="ruleForm.checkAmount" oninput = "value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"  class="item-width"  size="mini" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="抽检比例：" prop="rate">
                <el-input  maxlength="4" v-model="ruleForm.rate" oninput = "value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" class="item-width" size="mini" placeholder="请输入" />
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
      <div slot="footer" class="dialog-footer" >
        <el-button type="primary" @click="saveInfoFun" >确 定</el-button>
      </div>
    </el-dialog>
    <!-- 角色清单弹框 end -->
  </div>
</template>


<script>

  import { addInfo,editInfo } from '@/api/scheduling/ruleApi'

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
    caseTypeSelect: {
      type: Array,
      default: function () {
        return []
      }
    },
    conclusionSelect: {
      type: Array,
      default: function () {
        return []
      }
    },
    amountTypeSelet: {
      type: Array,
      default: function () {
        return []
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
        this.ruleForm.checkRuleNo = newVal.rowdata.checkRuleNo;
        this.ruleForm.caseType = newVal.rowdata.caseType;
        this.ruleForm.payConclusion = newVal.rowdata.payConclusion.split(",");
        this.ruleForm.amountType = newVal.rowdata.amountType;
        this.ruleForm.checkAmount = newVal.rowdata.checkAmount;
        this.ruleForm.rate = newVal.rowdata.rate;
        if(newVal.rowdata.status == 'Y' || newVal.rowdata.status == '01') {
          this.ruleForm.status = '01';
        } else {
          this.ruleForm.status = '02';
        }
      } else {
        this.resetForm();
      }
    },
    caseTypeSelect: function (newVal){
      this.caseSelect = newVal;
    },
    conclusionSelect: function (newVal){
      this.conSelect = newVal;
    },
    amountTypeSelet: function (newVal){
      this.amountSelet = newVal;
    },

  },
  data() {
    return {
        copyFormData:{},
        editData:{},
        caseSelect:[],
        conSelect:[],
        amountSelet:[],
        ruleForm : {
          checkRuleNo: '',
          caseType:'',
          payConclusion :[],
          amountType:'',
          checkAmount:'',
          rate:'',
          status:'01',
        },
        rules: {
          checkRuleNo: {trigger: ['change'], required: false, message: '规则编码'},
          caseType: {trigger: ['change'], required: true, message: '案件类型必填'},
          payConclusion: {trigger: ['change'], required: true, message: '赔付结论必填'},
          amountType: {trigger: ['change'], required: true, message: '金额类型必填'},
          checkAmount: {trigger: ['change'], required: true, message: '金额上限必填'},
          rate: {trigger: ['change'], required: true, message: '抽检金额必填'},
          status: {trigger: ['change'], required: true, message: '规则状态必填'},
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
    resetForm(){
      this.ruleForm.checkRuleNo = '';
      this.ruleForm.caseType = '';
      this.ruleForm.payConclusion = [];
      this.ruleForm.amountType = '';
      this.ruleForm.checkAmount = '';
      this.ruleForm.rate = '';
      this.ruleForm.status = '01';
      this.$refs['ruleForm'].clearValidate();

    },
    saveInfoFun(){

      let status = ''
      if(this.ruleForm.status == 'Y' || this.ruleForm.status == '01') {
        status = '01';
      } else {
        status = '02';
      }
      const params = {
        checkRuleNo : this.ruleForm.checkRuleNo,
        caseType : this.ruleForm.caseType,
        payConclusion : this.ruleForm.payConclusion.toString(),
        amountType : this.ruleForm.amountType,
        checkAmount : this.ruleForm.checkAmount,
        rate : this.ruleForm.rate,
        status:status,
      };
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          if( this.editData.type == 'edit') {
            this.updateData(params);
          } else {
            this.addData(params);
          }
        }
      })
    },
    addData(params){
      addInfo(params).then(response => {
        if (response.code == '200') {
          this.$message({
            message: '保存成功！',
            type: 'success',
            center: true,
            showClose: true
          });
          this.dialogVisible = false;
          this.$emit('initData');
          this.$emit('closeDialogVisable')
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
    updateData(params){
      editInfo(params).then(response => {
        if (response.code == '200') {
          this.$message({
            message: '更新成功！',
            type: 'success',
            center: true,
            showClose: true
          });
          this.dialogVisible = false;
          this.$emit('initData');
          this.$emit('closeDialogVisable')
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
    handleClose() {
      this.resetForm();
      this.dialogVisible = false;
      this.$emit('closeDialogVisable')
    },
    closeDialog(){
      this.resetForm();
      this.dialogVisible = false;
      this.$emit('closeDialogVisable')
    },
    changeDialogVisable() {
      //清空对话框中的数据
      // this.radio = undefined
      // this.copyRadio = undefined
      // this.expands = []
      // this.$refs.searchForm.resetFields()
      this.resetForm();
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

