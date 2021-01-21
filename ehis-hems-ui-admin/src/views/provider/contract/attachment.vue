<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="125px">
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-upload mr5"></i>
            <span>信息上载</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col>
              <el-form-item label="文件描述">
                <el-input type="textarea" v-model="form.filedesc" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row style="text-align: right;">
            <el-col>
              <el-button icon="el-icon-upload"  size="mini" @click="handleImport" type="info">合约附件</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-files mr5"></i>
            <span>文件查询</span>
          </div>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="文件编码">
                <el-input v-model="queryForm.filecode" placeholder="请输入机构名称" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="文件名称">
                <el-input v-model="queryForm.filename" placeholder="请输入文件名称" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="上传人">
                <el-input v-model="queryForm.operator" placeholder="请输入上传人" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row style="text-align: right;" class="mb10">
            <el-col>
              <el-form-item>
                <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="delRow" :disabled="multiple">删除</el-button>
                <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第三行 -->
          <el-row>
              <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

                <el-table-column
                  type="selection"
                  width="55" align="center">
                </el-table-column>

                <el-table-column
                  label="文件编码" show-overflow-tooltip>
                  <template slot-scope="scope">{{ scope.row.filecode }}</template>
                </el-table-column>
                <el-table-column
                  label="文件名称" show-overflow-tooltip>
                  <template slot-scope="scope">{{ scope.row.filename }}</template>
                </el-table-column>
                <el-table-column
                  label="上传日期" show-overflow-tooltip>
                  <template slot-scope="scope">{{ scope.row.makedate }}</template>
                </el-table-column>

                <el-table-column
                  label="上传时间" show-overflow-tooltip>
                  <template slot-scope="scope">{{ scope.row.maketime }}</template>
                </el-table-column>

                <el-table-column
                  label="上传人" show-overflow-tooltip>
                  <template slot-scope="scope">{{ scope.row.operator }}</template>
                </el-table-column>

                <el-table-column
                  label="文件描述" show-overflow-tooltip>
                  <template slot-scope="scope">{{ scope.row.alternatefield1 }}</template>
                </el-table-column>

                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right"
                                 width="120px">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-download"
                      @click="downloadFile(scope.row)"
                      v-hasPermi="['system:fileInfo:download']"
                    >下载</el-button>
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                      @click="delRow(scope.row)"
                      v-hasPermi="['provider:provider:remove']"
                    >删除
                    </el-button>
                  </template>
                </el-table-column>

              </el-table>
          </el-row>

          <!-- 分页 -->
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryForm.pageNum"
            :limit.sync="queryForm.pageSize"
            @pagination="getList"/>
        </el-card>
      </el-row>
    </el-form>
    <!-- 合约附件导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :on-exceed="handleExceed"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :before-remove="beforeRemove"
        :limit="1"
        accept=".doc, .docx, .pdf, .xls, .xlsx"
        :headers="upload.headers"
        :action="upload.url"
        :data="{'contractno': this.contractno, 'filedesc': this.form.filedesc}"
        :disabled="upload.isUploading"
        :auto-upload="false"
        drag>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入".doc, .docx, .pdf, .xls, .xlsx"格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {getToken} from "@/utils/auth";
  import {listFile, delFile, downloadFile} from "@/api/system/file";

  export default {
    name: "ContractAttachment",
    data() {
      return {
        // 非多个禁用
        multiple: true,
        // 文件描述
        form: {
          filedesc: '',
        },
        // 查询表单
        queryForm: {
          pageNum: 1,
          pageSize: 10,
        },
        // 表格区域loading
        loading: false,
        total: 4,
        // 表格ID
        ids: [],
        // 模拟表格数据
        tableData: [
          {
            fileCode: 'F000006015',
            fileName: '9.csv',
            uploadDate: '2018-04-26',
            uploadTime: '16:23:51',
            uploader: 'admin',
            fileDesc: 'csv文件'
          },
          {
            fileCode: 'F000006014',
            fileName: 'test0426.txt',
            uploadDate: '2018-04-26',
            uploadTime: '16:23:51',
            uploader: 'admin',
            fileDesc: 'txt文件'
          },
          {
            fileCode: 'F000006013',
            fileName: '文件下载.png',
            uploadDate: '2018-04-26',
            uploadTime: '18:59:27',
            uploader: 'admin',
            fileDesc: 'png文件'
          },
          {
            fileCode: 'F000006012',
            fileName: '订单打印.txt',
            uploadDate: '2018-04-26',
            uploadTime: '18:59:27',
            uploader: 'admin',
            fileDesc: 'txt文件'
          },
        ],
        // 用户导入参数
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/provider/contract/uploadFile"
        },
        // 合约编码
        contractno: '',
      }
    },
    created() {
      this.contractno = this.$route.params && this.$route.params.contractno;
      this.getList();
    },
    methods: {
      /** 查询文件列表 */
      getList() {
        this.loading = true;
        this.queryForm.filesource = this.contractno;
        listFile(this.queryForm).then(response => {
          this.tableData = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "合约附件导入";
        this.upload.open = true;
      },
      // 提交上传文件
      submitFileForm() {
        this.upload.contractno = this.contractno;
        this.upload.filedesc = this.form.filedesc;
        this.$refs.upload.submit();
      },
      /** 下载模板操作 */
      importTemplate() {
        this.download('system/user/importTemplate', {
          ...this.queryParams
        }, `comAttachment_${new Date().getTime()}.xlsx`)
      },
      // 点击多个文件，超出文件限制后
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传框 点击X 删除文件
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        this.getList();
      },

      handleQuery() {
        this.getList();
      },
      resetQuery() {
        this.queryForm = {pageNum: 1, pageSize: 10,};
        this.getList();
      },
      // 删除按钮
      delRow(row) {
        const filecodes = row.filecode || this.ids;
        // 编号换行
        var filecodeStr = '<br/>';
        for (var i = 0; i < filecodes.length; i++) {
          filecodeStr += filecodes[i] + "<br/>";
        }
        this.$confirm('是否确认删除文件编码为"' + filecodeStr + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
          dangerouslyUseHTMLString: true,
        }).then(function () {
          return delFile(filecodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      back() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/provider/contract'});
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.filecode)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      // 下载文件
      downloadFile(row) {
        const fileCode = row.filecode || this.ids;
        if(fileCode == '' || fileCode == undefined) {
          this.$message.error('请先选中再进行下载');
          return;
        }
        row.params = undefined;
        this.download('system/fileInfo/download', row, row.filename)
      },
    },
  }
</script>

<style scoped>

</style>
