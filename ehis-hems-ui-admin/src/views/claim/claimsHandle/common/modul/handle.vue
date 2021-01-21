<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    :title="`${title}操作`"
    width="40%"
    @open="openHandle">
    <el-form ref="resultForm" :model="resultForm" label-width="150px" size="mini">
      <el-form-item
        :label="`${title}原因：`"
        :rules="{required: true, message: '不能为空'}"
        prop="reason"
        v-if="title==='不予受理'">
        <el-select v-model="resultForm.reason" class="item-width" placeholder="请选择" @change="loadAll">
          <el-option v-for="item in acceptreasonOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item :label="`${title}说明：`" prop="description"
        :rules="{required: true, message: '不能为空'}">
        <el-input v-if="diaTitle =='0'" v-model="resultForm.description" maxlength="1000" show-word-limit :rows="3" class="item-width" type="textarea" autocomplete="off" placeholder="请输入"/>
        <el-autocomplete
          v-else
          v-model="resultForm.description"
          :rows="3"
          style="width: 260px;"
          :fetch-suggestions="querySearch"
          type="textarea"
          :maxlength="maxLength"
          show-word-limit
          :placeholder="placeholder">
        </el-autocomplete>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="confirmHandle">确 定</el-button>
      <el-button size="mini" @click="dialogClosed">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { casesuspend, revokeCase,getTempalte, getContents } from '@/api/claim/handleDeal'
export default {
  inject: ['isSuspend'],
 // dicts: ['acceptreason'],
  props: {
    dictList:Array,
    value: {
      type: Boolean,
      default: true
    },
    node: {
      type: String,
      default: ''
    },
    diaTitle: {
      type: String,
      default: ''
    },
    claimno: {
      type: String,
      default: ''
    },
    placeholder: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tableData: [
        { policyNo: 'ERF23423645' }
      ],
      resultForm: {
        reason: '',
        description: ''
      },
      expList: [],
      maxLength: 100,
      acceptreasonOptions: []
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    },
    title(){
      return this.diaTitle==='0'?'悬挂':'不予受理'
    },
    // isrequired: function () {
    //   return this.title==='暂停'?true:false
    // }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.acceptreasonOptions = this.dictList.find(item => {
        return item.dictType == 'acceptreason'
      }).dictDate
    }
  },
  methods: {
    openHandle(){
      this.expList = this.loadAll()
    },
    createFilter(queryString) {
      return (expList) => {
        return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      }
    },
    querySearch(queryString, cb) {
      let expList = this.expList
      let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
      cb(results ? results : [])
    },
    loadAll(){
      // getTempalte('02').then(res =>{
      //   if (res.status == '1') {
      //     this.expList = res.data || []
      //   } else {
      //     this.$message.error('查询模版消息错误！')
      //   }
      // })
      this.resultForm.description = ''
      this.expList = []
      const params = {
        templatetype: '04',
        supplementarymode: '',
        insurancecode: '',
        dutycode: '',
        circulationvalue1: this.resultForm.reason,
        circulationvalue2: ''
      }
      if (!this.resultForm.reason) {
        return
      }
      getContents(params).then(res =>{
        if (res.status == '1') {
          // this.expList = res.data || []
          res.data.forEach(item => {
            this.expList.push({ value: item })
          })
        }
      })
    },
    dialogClosed() {
      this.$refs.resultForm.resetFields()
      this.$emit('input', false)
    },
    confirmHandle() {
      this.$refs.resultForm.validate((valid) => {
        if (valid) {
          let params = {
            claimno: this.claimno,
            ...this.resultForm,
            operationtype: this.title==='不予受理'?'02':'01'
          }
          if(this.title==='不予受理'){
            revokeCase(params).then(res => {
              if (res.status === '1') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.dialogClosed()
                this.$emit('changeButtonDisabled')
                this.$router.go(-1)
              } else {
                this.$message({ message: res.data.errMsg, type: 'error' })
              }
            })
          } else {
            delete params.reason
            delete params.operationtype
            casesuspend(params).then(response => {
              if (response.status === '1') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.isSuspend()
                this.dialogClosed()
              } else {
                this.$message({ message: '操作失败！', type: 'error' })
              }
            })
          }
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
