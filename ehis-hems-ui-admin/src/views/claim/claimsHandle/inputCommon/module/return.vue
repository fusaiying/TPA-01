<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="录入回退操作"
    width="40%"
    @open="openHandle">
    <el-form ref="returnForm" :disabled="status === 'show'" :model="returnForm" label-width="150px" size="mini">
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
      <el-button type="primary" size="mini" :disabled="status === 'show'" @click="confirmHandle">确 定</el-button>
      <el-button size="mini" @click="dialogClosed">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
  /*import { casesuspend, revokeCase,getTempalte, getContents } from '@/api/claim/handleDeal'*/
  export default {
    //dicts: ['acceptreason', 'inputreturn_node', 'return_reason'],
    props: {
      dictList: {
        type: Array
      },
      value: {
        type: Boolean,
        default: function() {
          return false
        }
      },
      status: {
        type: String,
        default: function() {
          return ''
        }
      }
    },
    data() {
      return {
        returnForm: {
          status: '',
          rejectreason: '',
          explanation: '',
        },
        /*'acceptreason', 'inputreturn_node', 'return_reason'*/
        acceptreasonOptions: [],
        inputreturn_nodeOptions: [],
        return_reasonOptions: []

      }
    },
    mounted() {
      if (this.dictList!=null && this.dictList.length!=0) {
        this.acceptreasonOptions = this.dictList.find(item => {
          return item.dictType == 'acceptreason'
        }).dictDate
        this.inputreturn_nodeOptions = this.dictList.find(item => {
          return item.dictType == 'inputreturn_node'
        }).dictDate
        this.return_reasonOptions = this.dictList.find(item => {
          return item.dictType == 'return_reason'
        }).dictDate
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
