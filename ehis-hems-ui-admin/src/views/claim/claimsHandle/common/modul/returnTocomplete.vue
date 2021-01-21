<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="录入回退操作"
    width="40%"
    @open="openHandle">
    <el-form ref="returnForm" :model="returnForm" label-width="150px" size="mini">
      <el-form-item
        label="退回节点"
        :rules="{required: true, message: '不能为空', trigger: 'change'}"
        prop="status">
        <el-select v-model="returnForm.status" class="item-width" placeholder="请选择">
          <el-option v-for="item in inputreturn_nodeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item
        label="回退原因"
        prop="rejectreason"
        :rules="{required: true, message: '不能为空', trigger: 'change'}">
        <el-select v-model="returnForm.rejectreason" class="item-width" placeholder="请选择">
          <el-option v-for="item in return_reasonOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item
        label="退回描述"
        prop="explanation">
        <el-input
          :maxlength="1000"
          show-word-limit
          clearable
          size="mini"
          placeholder="请输入"
          v-model="returnForm.explanation">
        </el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="confirmHandle">确 定</el-button>
      <el-button size="mini" @click="dialogClosed">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
  /*import { casesuspend, revokeCase,getTempalte, getContents } from '@/api/claim/handleDeal'*/
  export default {
    dicts: ['acceptreason', 'inputreturn_node', 'return_reason'],
    props: {
      value: {
        type: Boolean,
        default: true
      }
    },
    data() {
      return {
        returnForm: {
          status: '',
          rejectreason: '',
          explanation: ''
        },

        acceptreasonOptions: [],
        inputreturn_nodeOptions: [],
        return_reasonOptions: []
      }
    },
    computed: {
      dialogVisable: {
        set() {},
        get() {
          return this.value
        }
      }
    },
    mounted() {
      this.getDicts("acceptreason").then(response => {
        this.acceptreasonOptions = response.data;
      });
      this.getDicts("inputreturn_node").then(response => {
        this.inputreturn_nodeOptions = response.data;
      });
      this.getDicts("return_reason").then(response => {
        this.return_reasonOptions = response.data;
      });
    },
    methods: {
      openHandle(){
      },
      dialogClosed() {
        this.$refs.returnForm.resetFields()
        this.$emit('input', false)
      },
      confirmHandle() {
        this.$refs.returnForm.validate((valid) => {
          if (valid) {
            this.$emit('confirmHandle', this.returnForm)
          } else {
            return false
          }
        })
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 260px;
  }
</style>
