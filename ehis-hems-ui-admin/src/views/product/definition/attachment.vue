<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="125px">
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>信息上载</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col>
              <el-form-item label="文件描述">
                <el-input type="textarea" v-model="form.alternatefield1" size="small"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row style="text-align: right;">
            <el-col>
              <el-button icon="el-icon-upload" size="mini" @click="handleImport" type="warning">产品服务条款</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>文件查询</span>
          </div>
          <el-row>
            <el-form :model="queryParams" ref="queryParams" :inline="true" label-width="125px">
              <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-form-item label="文件编码">
                  <el-input v-model="queryParams.filecode" placeholder="请输入文件编码"></el-input>
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-form-item label="文件名称">
                  <el-input v-model="queryParams.filename" placeholder="请输入文件名称"></el-input>
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-form-item label="上传人">
                  <el-input v-model="queryParams.operator" placeholder="请输入上传人"></el-input>
                </el-form-item>
              </el-col>
            </el-form>
          </el-row>

          <!-- 第二行 -->
          <el-row style="text-align: right;">
            <el-col>
              <el-form-item>
                <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="delRow">删除</el-button>
                <el-button icon="el-icon-d-arrow-left" size="mini" @click="close">返回</el-button>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第三行 -->
          <el-row>
              <el-table v-loading="loading" :data="fileList" @selection-change="handleSelectionChange">

                <el-table-column type="selection" width="50" align="center"></el-table-column>
                <el-table-column label="文件编码" align="center" prop="filecode"/>
                <el-table-column label="文件名称" align="center" prop="filename"/>
                <el-table-column label="上传日期" align="center" prop="makedate"/>
                <el-table-column label="上传时间" align="center" prop="maketime"/>
                <el-table-column label="上传人" align="center" prop="operator"/>
                <el-table-column label="文件描述" align="center" prop="alternatefield1"/>
                <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width" fixed="right">
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
                      v-hasPermi="['system:fileInfo:remove']"
                    >删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
          </el-row>

          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-card>
      </el-row>
    </el-form>
    <!-- 附件导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".doc, .pdf"
        :headers="upload.headers"
        :action="upload.url + '?productNo=' + upload.productNo+'&fileDesc='+this.form.alternatefield1"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <!--          <el-checkbox v-model="upload.updateSupport"/>-->
          <!--          是否更新已经存在的用户数据-->
          <!--<el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>-->
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“doc”或“pdf”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {getProductInfoById} from "@/api/product/info";
  import {listFile, delFile, downloadFile} from "@/api/system/file";
  import {getToken} from "@/utils/auth";
  export default {
    name: "attachment",
    data() {
      return {
        // 表格区域loading
        loading: false,
        total: 0,
        //产品id
        productno:"",
        // 表格ID
        ids: [],
        // 非多个禁用
        multiple: true,
        // 模拟表格数据
        fileList: [],
        // 用户导入参数
        // 文件描述
        form: {
          alternatefield1:""
        },
        // 查询表单
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          filecode: undefined,
          filename: undefined,
          filesource: undefined,
          operator: undefined
        },
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: "",
          // 是否禁用上传
          isUploading: false,
          //产品id
          productNo: "",
          //文件描述
          fileDesc: "",
          // 设置上传的请求头部
          headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/system/definition/uploadFile"
        },
      }
    },
    created() {
      this.productno = this.$route.params && this.$route.params.productno;
      this.upload.productNo = this.productno;
      this.getList();
    },
    methods: {
      /** 查询文件列表 */
      getList() {
        this.loading = true;
        console.log(this.productno);
        getProductInfoById(this.productno).then(response => {
          this.queryParams.filesource = response.data.productno;
          listFile(this.queryParams).then(response => {
              this.fileList = response.rows;
              this.total = response.total;
              this.loading = false;
            }
          );
        });
       },
       /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "产品服务条款导入";
        this.upload.open = true;
      },
      // 提交上传文件
      submitFileForm() {
        this.upload.productNo = this.productno;
        //this.upload.fileDesc = this.form.alternatefield1;
        this.$refs.upload.submit();
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
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
        this.queryForm = {
          pageNum: 1,
          pageSize: 10,
          filecode: undefined,
          filename: undefined,
          filesource: undefined,
          operator: undefined
        };
        this.getList();
      },
      // 删除按钮
      delRow(row) {
        const fileCode = row.filecode || this.ids;
        //console.log(fileCode);
        if(fileCode == '' || fileCode == undefined) {
          this.$message.error('请先选中再进行删除');
          return;
        }
        this.$confirm('是否确认删除文件编码为"' + fileCode + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delFile(fileCode);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
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
      /** 关闭按钮操作 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.back();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.filecode);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
    },
  }
</script>
