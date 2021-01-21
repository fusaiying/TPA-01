<template>
  <div class="dialog-wrapper">
    <el-dialog
      :visible.sync="visible"
      :append-to-body="true"
      :before-close="handleClose"
      :close-on-click-modal="false"
      title="协谈"
      width="80%"
      @open="open">
      <el-card style="border: 0;box-shadow: none; margin-top: -20px;">
        <div slot="header" class="clearfix">
          <span>协谈要求</span>
          <el-button :loading="finishLoad" :disabled="node === 'show'" style="float: right" type="primary" size="mini" @click="finishHandle">处理完毕</el-button>
        </div>
        <el-form ref="searchForm" :model="formSearch" style="border:0" label-width="110px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="赔案号：" prop="claimno">
                <el-input :disabled="node === 'show'" v-model="formSearch.claimno" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="协谈次数：" prop="negotiationnum">
                <el-input :disabled="node === 'show'" v-model="formSearch.negotiationnum" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="发起人：" prop="username">
                <el-input :disabled="node === 'show'" v-model="formSearch.username" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="协谈类型：" prop="negotiationtype">
                <el-input :disabled="node === 'show'" v-model="formSearch.negotiationtype" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="协谈方式：" prop="negotiationmode">
                <el-input :disabled="node === 'show'" v-model="formSearch.negotiationmode" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="协谈要求：" prop="negotiationrequest">
                <el-input :disabled="node === 'show'" v-model="formSearch.negotiationrequest" disabled type="text" maxlength="1000" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
      <el-card style="border: 0;box-shadow: none">
        <div slot="header" class="clearfix">
          <span>协谈结果</span>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button :disabled="node === 'show'" @click="dialogVisible = false">取 消</el-button>
          <el-button :disabled="node === 'show'" type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
        <el-form ref="baseForm" :disabled="node === 'show'" :model="baseForm" style="padding-bottom: 30px;border:0" label-width="110px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item :rules="{ required: true, message: '不能为空', trigger: 'blur'}" label="完成时间：" prop="negotiationdate">
                <el-date-picker
                  :disabled="node === 'show'"
                  v-model="baseForm.negotiationdate"
                  class="item-width"
                  clearable
                  size="mini"
                  type="date"
                  placeholder="选择日期"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :rules="{ required: true, message: '不能为空', trigger: 'blur'}" label="处理人：" prop="negotiator">
                <el-input :disabled="true" v-model="baseForm.negotiator" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item :rules="{ required: true, message: '不能为空', trigger: 'blur'}" label="协谈结果：" prop="negotiationresults">
                <el-input :disabled="node === 'show'" type="textarea"  :autosize="{ minRows: 2, maxRows: 8}" v-model="baseForm.negotiationresults" maxlength="1000" show-word-limit class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-upload
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                :on-success="onSeccuss"
                :on-exceed="handleExceed"
                :file-list="fileList"
                :action="url + negotiationno"
                :headers="headers"
                :before-upload="beforeUpload"
                :on-error="onError"
                :disabled="node === 'show'"
                :with-credentials="true"
                class="upload-demo"
                multiple>
                <el-button size="small" type="primary" class="upload-container">点击上传</el-button>
<!--                <div slot="tip" class="el-upload__tip">不超过5M</div>-->
              </el-upload>
            </el-col>
          </el-row>
        </el-form>
        <div style="float: right;padding: 10px">
          <el-button :loading="saveLoad" :disabled="node === 'show'" type="primary" style="width: 80px" size="mini" @click="savaHandle">保存</el-button>
          <el-button type="primary" style="width: 80px" size="mini" @click="handleClose">返回</el-button>
        </div>
      </el-card>
    </el-dialog>
  </div>
</template>

<script>
const baseAddress = process.env.BASE_API
import { negotiationBack, updateNegotiation, finishNegotiation, getImageInfo, deleteImage, dowloadFile } from '@/api/claim/negotation.js'
import { getToken } from '@/utils/auth'
import { toClient } from '@/utils/commenMethods.js'
import { downloadData } from '@/utils/index.js'

export default {
  dicts: ['negotiation_mode', 'negotiation_type'],
  name: 'ClaimsDialog',
  props: {
    dialogVisible: {
      type: Boolean,
      default: function() {
        return false
      }
    },
    orderno: {
      type: String,
      default: function() {
        return ''
      }
    },
    claimno: {
      type: String,
      default: function() {
        return ''
      }
    },
    node: {
      type: String,
      default: function() {
        return ''
      }
    },
    negotiationno: {
      type: String,
      default: function() {
        return ''
      }
    }
  },
  data() {
    return {
      formSearch: {},
      baseForm: {
        negotiationdate: '',
        negotiator: '',
        negotiationresults: ''
      },
      fileList: [],
      url: baseAddress + '/file/file/claim/NegotiationUpload?negotiationno=',
      headers: { 'Authorization': 'Bearer ' + getToken() },
      saveFlag: false,
      flag: false,
      saveLoad: false,
      finishLoad: false
    }
  },
  computed: {
    visible: function() {
      return this.dialogVisible
    }
  },
  methods: {
    beforeUpload(file) {
      this.flag = false
      let checkList = ['zip','rar','doc','docx','xls','xlsx','bmp','gif','jpg','pic','png','tif','jpeg','txt','wps','eml','msg','xml','pdf']
      const testmsg = file.name.substring(file.name.lastIndexOf('.') + 1).toLowerCase()
      checkList.findIndex(item => item === testmsg) == '-1' ? this.flag = true : this.flag = false
      if (this.flag) {
          this.$message.warning('不允许上传.' + testmsg + '格式的文件，请重新选择！')
          return false
      }
      const isLt2M = file.size / 1024 / 1024 < 5
      if (!isLt2M) {
        this.$message({
          message: '上传文件大小不能超过 5M！',
          type: 'warning'
        })
        return false
      }
      // return isLt2M
    },
    open() {
      this.getNegotiationBack()
      this.getImageInfo()
      this.baseForm.negotiationdate = new Date()
    },
    getImageInfo() {
      getImageInfo(this.negotiationno).then(res => {
        if (res.status === '1') {
          this.fileList = res.data
        }
      })
    },
    getNegotiationBack() {
      const params = {
        accidentdate: '',
        applicationsource: '',
        claimcreatedate: '',
        claimenddate: '',
        claimno: '',
        claimstartdate: '',
        dualdays: '',
        insuredidno: '',
        insuredname: '',
        negotiationtype: '',
        orderno: this.orderno,
        negotiationno: this.negotiationno,
        pageno: 0,
        pagesize: 0,
        startdate: '',
        status: '',
        username: ''
      }
      negotiationBack(params).then(res => {
        if (res.status === '1') {
          this.formSearch = res.data
          this.paramsForm = JSON.parse(JSON.stringify(res.data))
          this.formSearch.negotiationmode = this.dict.label.negotiation_mode[res.data.negotiationmode]
          this.formSearch.negotiationtype = this.dict.label.negotiation_type[res.data.negotiationtype]
          this.baseForm.negotiationdate = res.data.negotiationdate ? res.data.negotiationdate : new Date()
          this.$nextTick(function() {
            this.baseForm.negotiator = res.data.negotiator ? res.data.negotiator : toClient()
            this.baseForm.negotiationresults = res.data.negotiationresults
            if (this.baseForm.negotiationdate && this.baseForm.negotiator && this.baseForm.negotiationresults) {
              this.saveFlag = true
            }
          })
        }
      })
    },
    parseTime(time) {
      if (time) {
        const date = new Date(time)
        const year = date.getFullYear()
        /* 在日期格式中，月份是从0开始的，因此要加0
           * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
           * */
        const month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
        const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
        return year + '-' + month + '-' + day + ' '
      } else {
        return ''
      }
    },
    savaHandle() {
      this.baseForm.negotiationresults = this.trimDescription()
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          const chooseTime = this.parseTime(this.baseForm.negotiationdate)
          const currentTime = this.parseTime(new Date())
          if (chooseTime < currentTime) {
            this.$message({ message: '完成时间应大于等于当前日期！', type: 'warning' })
            return false
          }
          const params = JSON.parse(JSON.stringify(this.baseForm))
          params.orderno = this.orderno
          params.negotiationno = this.negotiationno
          this.saveLoad = true
          updateNegotiation(params).then(res => {
            if (res.status === '1') {
              this.$message({ message: '保存成功！', type: 'success' })
              this.saveFlag = true
            } else {
              this.$message({ message: '保存失败！', type: 'error' })
            }
            this.saveLoad = false
          })
        } else {
          return false
        }
      })
    },
    trimDescription() {
      return this.baseForm.negotiationresults.replace(/(^\s*)|(\s*$)/g, "")
    },
    handleClose() {
      this.$emit('handleClose')
      this.fileList = []
      this.$refs.baseForm.resetFields()
    },
    refreshTable() {
      this.$emit('refreshTable')
    },
    finishHandle() {
      if (!this.saveFlag) {
        this.$message({ message: '请保存后再进行处理！', type: 'warning' })
        return
      }
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          // this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          //     confirmButtonText: '确定',
          //     cancelButtonText: '取消',
          //     type: 'warning'
          // }).then(() => {
          //     this.$message({
          //         type: 'success',
          //         message: '删除成功!'
          //     });
          // }).catch(() => {
          //     this.$message({
          //         type: 'info',
          //         message: '已取消删除'
          //     });
          // });
          const params = Object.assign({}, this.paramsForm, this.baseForm )
          params.negotiationno = this.negotiationno
          this.finishLoad = false
          finishNegotiation(params).then(res => {
            if (res.status === '1') {
              this.$message({ message: '处理成功！', type: 'success' })
              this.refreshTable()
              this.handleClose()
            } else {
              this.$message({ message: '处理失败！', type: 'error' })
            }
            this.finishLoad = true
          })
        }
      })
    },
    handlePreview(file) {
      dowloadFile(file.attachmentno).then(res => {
        downloadData(res, file.name)
      })
    },
    deleteImage(fileno) {
      deleteImage(fileno).then(res => {
        if (res.status === '1') {
          this.$message({ message: '删除成功！', type: 'success' })
        } else {
          this.$message({ message: '删除失败！', type: 'error' })
        }
      })
    },
    onSeccuss() {
      this.getImageInfo()
    },
    handleRemove(file) {
      if (file && file.status === 'success') {
        this.deleteImage(file.attachmentno)
      }
    },
    onError(err) {
      this.$message({ message: '上传失败！', type: 'error' })
    },
    beforeRemove(file) {
      if (file && file.status === 'success') {
        return this.$confirm(`确定移除 ${file.name}？`)
      }
    },
    handleExceed() {}
  }
}
</script>

<style scoped>
  .upload-container{
    margin-bottom: 20px;
  }
</style>
