<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="codeQueryForm" :model="codeQueryForm" style="padding-bottom: 30px;" label-width="150px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="投保来源ID：" prop="insuranceSourceCode">
              <el-input v-model="codeQueryForm.insuranceSourceCode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="投保来源名称：" prop="insuranceSourceName">
              <el-input v-model="codeQueryForm.insuranceSourceName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="投诉业务类别ID：" prop="complaintBusinessCode">
              <el-input v-model="codeQueryForm.complaintBusinessCode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉业务类别名称：" prop="complaintBusinessName">
              <el-input v-model="codeQueryForm.complaintBusinessName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              isinit='N',
              page=1,
              finishPage=1,
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询结果（{{ totalCount }}）</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="downloadTemplate">下载模板</el-button>
          <el-upload
            class="upload-demo"
            :headers="upload.headers"
            :action="upload.url"
            :disabled="upload.isUploading"
            :show-file-list="false"
            :on-success="uploadSuccess"
            :before-upload="beforeUpload"
            :on-progress="uploadProgress"
            accept=".xls,.xlsx"
            title="提示：仅允许导入文件名为任务号，且“xls”或“xlsx”格式文件！"
            style="display: initial; margin: 0px 10px;"
          >
              <el-button size="mini" type="primary">清单导入</el-button>
            </el-upload>
          <el-button type="primary" size="mini" @click="listExport">导出清单</el-button>
        </span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="codeDictData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" width="100" prop="insuranceSourceCode" label="投保来源ID" show-overflow-tooltip/>
          <el-table-column align="center" prop="insuranceSourceName" label="投保来源名称" show-overflow-tooltip/>
          <el-table-column align="center" width="120" prop="complaintBusinessCode" label="投保业务类别ID" show-overflow-tooltip/>
          <el-table-column align="center" prop="complaintBusinessName" label="投保业务类别名称" show-overflow-tooltip/>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import {selectCodeDictQuery,uploadComplaintCategory} from '@/api/customService/codeDict';
import {getToken} from "@/utils/auth";

export default {
  data() {
    return {
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/cs/dict/internal/uploadComplaintCategory"
      },
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      codeQueryForm: {
        insuranceSourceCode: undefined,
        insuranceSourceName: undefined,
        complaintBusinessCode: undefined,
        complaintBusinessName: undefined
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        insuranceSourceType:"cs_insurance_source",
        complaintBusinessType:"cs_complaint_business_item"
      },
      loading: true,
      codeDictData: [],
      isinit: 'Y',
      page: 1,
      totalCount: 0,
      finishPageSize: 10
    }
  },
  created() {
  },
  async mounted() {

    this.searchHandle()
  },
  methods: {
    resetForm() {
      this.$refs.codeQueryForm.resetFields()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        insuranceSourceCode: this.codeQueryForm.insuranceSourceCode,
        insuranceSourceName: this.codeQueryForm.insuranceSourceName,
        complaintBusinessCode: this.codeQueryForm.complaintBusinessCode,
        complaintBusinessName: this.codeQueryForm.complaintBusinessName
      }
      selectCodeDictQuery(query).then(res => {
        if (res != null && res.code === 200) {
          this.codeDictData = res.rows
          this.totalCount = res.total
          if (res.rows.length<=0){
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
    listExport() {
      let query ={
        insuranceSourceType:this.queryParams.insuranceSourceType,
        complaintBusinessType:this.queryParams.complaintBusinessType,
        insuranceSourceCode:this.codeQueryForm.insuranceSourceCode,
        insuranceSourceName:this.codeQueryForm.insuranceSourceName,
        complaintBusinessCode:this.codeQueryForm.complaintBusinessCode,
        complaintBusinessName:this.codeQueryForm.complaintBusinessName
      }
      console.info(query);
      this.download('cs/dict/internal/exportNew', {
        ...query
      }, `ComplaintBusiness_${new Date().getTime()}.xlsx`).catch(res=>{
        this.$message({
          message: res,
          type: 'error',
          center: true,
          showClose: true
        })
      })
    },
    listUpload() {

    },

    /*下载导入模板*/
    downloadTemplate(){
      let query;
      this.download('cs/dict/internal/downloadTemplant', {
        ...query
      }, `ComplaintBusiness_导入模板.xlsx`).catch(res=>{
        this.$message({
          message: res,
          type: 'error',
          center: true,
          showClose: true
        })
      })
    },

    /** 上传前 */
    beforeUpload(file) {
      const fileName = file.name.substring(file.name.lastIndexOf('.')+1);
      if (fileName === "xls" || fileName === "xlsx") {
        return true;
      } else {
        this.$message.warning('只能上传excel文件！');
        return false;
      }
    },

    /** 上传中 */
    uploadProgress() {
      this.upload.isUploading = true;
    },

    /** 导入成功后事件 */
    uploadSuccess(response, file, fileList){
      this.upload.isUploading = false;
      if (response != null && response.code === 200) {
        this.msgSuccess('导入成功'+response.data.cgNumber+'条，失败'+response.data.sbNumber+'条！');
      } else {
        this.$message({
          message: response.msg,
          type: 'warning',
          showClose: true,
          duration: 5000
        })
      }
    },

  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
