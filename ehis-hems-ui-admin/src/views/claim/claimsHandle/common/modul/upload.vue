<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="影像上传"
    width="60%">
    <el-upload
      :before-upload="beforeUpload"
      :on-success="handleSuccess"
      :on-error="handleError"
      :file-list="fileList"
      :headers="headers"
      :action="baseUrl + '?businessNo='+claimno+'&type=' + type+'&receivedmode=' + receivedMode"
      accept=".jpg,.jpeg,.png"
      class="upload-demo"
      multiple
    >
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" style="display: block;" class="el-upload__tip">支持 .jpg .jpeg .png 格式文件上传，且不超过10M</div>
    </el-upload>
    <el-table
      ref="multipleTable"
      :header-cell-style="{background:'#fafafa',textAlign:'center'}"
      :data="downloadFile">
      <el-table-column align="center" label="序号" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" prop="type" label="资料类型" show-overflow-tooltip/>
      <el-table-column align="center" prop="fileName" label="文件名称" show-overflow-tooltip/>
      <el-table-column align="center" prop="operator" label="操作人" show-overflow-tooltip/>
      <el-table-column :formatter="getCurrentTime" align="center" prop="createDate" label="上传时间" show-overflow-tooltip/>
    </el-table>
    <div class="page_div">
      <el-pagination
        :current-page="downloadFilePaging.currentPage"
        :page-size="downloadFilePaging.pageSize"
        :page-sizes="[5,10,20,30,50,100]"
        :total="downloadFilePaging.totalPages"
        style="text-align: right; margin: 10px 10px 0;"
        layout="prev, pager, next, sizes,jumper"
        @current-change="handleDownloadCurrentChange"
        @size-change="handleDownloadSizeChange"/>
    </div>
<!--    <div>-->
<!--      <el-form ref="materiForm" :rules="materiFormRules" :model="materiForm" style="margin-top: 20px;" label-width="120px" size="mini">-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="资料类型：" prop="type">-->
<!--              <el-select v-model="materiForm.type" placeholder="请选择">-->
<!--                <el-option label="一" value="1"/>-->
<!--                <el-option label="二" value="2"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="17">-->
<!--            <el-form-item style="position: relative;">-->
<!--              <el-button-->
<!--                size="mini"-->
<!--                type="primary"-->
<!--                style="width: 80px; position: absolute;top: 0;left: 100px;padding: 9px 0;"-->
<!--                @click="confirmHandle">上传文件</el-button>-->
<!--              <el-upload-->
<!--                :limit="3"-->
<!--                :file-list="fileList"-->
<!--                class="upload-demo"-->
<!--                multiple-->
<!--                action="https://jsonplaceholder.typicode.com/posts/">-->
<!--                <el-button size="small" type="success">添加文件</el-button>-->
<!--                <div slot="tip" class="el-upload__tip" style="color: '#E6A23C">只能上传jpg/png文件，且不超过500kb</div>-->
<!--              </el-upload>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--      </el-form>-->
<!--      <div>-->
<!--        <el-row class="row-box">-->
<!--          <el-col :span="20">影像上传列表</el-col>-->
<!--        </el-row>-->
<!--        <el-table-->
<!--          :header-cell-style="{color:'black',background:'#f8f8ff'}"-->
<!--          :data="tableData"-->
<!--          size="small"-->
<!--          highlight-current-row-->
<!--          tooltip-effect="dark">-->
<!--          <el-table-column align="center" type="index" label="序号" show-overflow-tooltip/>-->
<!--          <el-table-column align="center" prop="name" label="资料类型" show-overflow-tooltip/>-->
<!--          <el-table-column align="center" prop="tasktype" label="文件名称" show-overflow-tooltip>-->
<!--            <template slot-scope="scope">-->
<!--              <el-button size="mini" type="text">{{ scope.row.tasktype }}</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--          <el-table-column align="center" prop="currentstatus" label="操作人" show-overflow-tooltip/>-->
<!--          <el-table-column align="center" prop="currentstatus" label="上传时间" show-overflow-tooltip/>-->
<!--        </el-table>-->
<!--        <el-pagination-->
<!--          :current-page.sync="pageInfo.page"-->
<!--          :page-sizes="[10, 20, 30, 40]"-->
<!--          :page-size="100"-->
<!--          :total="total"-->
<!--          style="text-align: right; margin-top: 10px;"-->
<!--          layout="sizes, prev, pager, next"-->
<!--          @size-change="handleSizeChange"-->
<!--          @current-change="handleCurrentChange"/>-->
<!--      </div>-->
<!--    </div>-->
  </el-dialog>
</template>
<script>
import { getToken } from '@/utils/auth'
import { parseTime } from '@/utils'
export default {
  props: {
    value: Boolean,
    claimno: String
  },
  data() {
    const baseAddress = process.env.BASE_API
    return {
      fileList: [],
      // materiForm: {},
      // materiFormRules: {
      //   isReturn: [{ required: true, message: '不能为空!', trigger: 'change' }],
      //   type: [{ required: true, message: '不能为空!', trigger: 'change' }]
      // },
      // tableData: [],
      // total: 0,
      // pageInfo: {
      //   page: 1,
      //   pageSize: 10
      // },
      type: '9',
      receivedMode: '1',
      headers: { 'Authorization': 'Bearer ' + getToken() },
      baseUrl: baseAddress + '/file/file/claim/claimUpload',
      downloadFile: [],
      downloadFilePaging: {
        currentPage: 1,
        totalPages: 0,
        pageSize: 5,
        powerData: []
      }
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
    // handleSizeChange(val) {
    //   this.pageInfo.pageSize = val
    // },
    // handleCurrentChange(val) {
    //   this.pageInfo.page = val
    // },
    confirmHandle() {
      this.$refs.materiForm.validate((valid) => {
        if (valid) {
          alert('submit')
        } else {
          return false
        }
      })
    },
    dialogClosed() {
      // this.$refs.materiForm.resetFields()
      this.$emit('input', false)
      // this.$emit('renovateImageInfo', false)
    },
    handleSuccess(response, file, fileList) {
      this.fileList = fileList
      if (response.status === '1') {
        this.downloadFilePaging.powerData.push({ fileName: response.data.fileName, type: response.data.type, createDate: response.data.createDate, operator: response.data.operator })
        this.handleDownloadDataChange(this.downloadFilePaging.currentPage, this.downloadFilePaging.pageSize)
      } else {
        this.$message({ message: '上传异常！', type: 'error' })
      }
      this.$emit('renovateImageInfo', false)
    },
    beforeUpload(file) {
      const type = file.name.split('.')[file.name.split('.').length - 1]
      const pdfType = type === 'jpg' || type === 'jpeg' || type === 'png'
      if (!pdfType) {
        this.$message.error('文件上传格式不合法，请重新上传！')
        return false
      }
      let isLt2M = true
      isLt2M = file.size / 1024 / 1024 < 10
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 10MB!')
      }
      return isLt2M
    },
    // 监听上传失败
    handleError(file, fileList) {
      this.$message({ message: '上传失败！', type: 'error' })
    },
    // 下载分页
    handleDownloadDataChange(ind, pag) {
      const start = (ind - 1) * pag
      const end = (ind - 1) * pag + pag
      this.downloadFile = this.downloadFilePaging.powerData.slice(start, end)
      this.downloadFilePaging.totalPages = this.downloadFilePaging.powerData.length
    }, handleDownloadSizeChange(val) {
      this.downloadFilePaging.pageSize = val
      const num = this.downloadFilePaging.pageSize
      this.handleDownloadDataChange(1, num)
    }, handleDownloadCurrentChange(val) {
      this.downloadFilePaging.currentPage = val
      const curre = this.downloadFilePaging.currentPage
      const num = this.downloadFilePaging.pageSize
      this.handleDownloadDataChange(curre, num)
    },
    getCurrentTime(row, col) {
      return parseTime(row.createDate)
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .row-box {
    padding-top: 20px;
    border-bottom: 1px solid #e6ebf5;
    margin-bottom: 20px;
    line-height: 50px;
  }
</style>
