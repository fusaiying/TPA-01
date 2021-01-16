<template>
  <div class="app-container">
    <el-form :model="form" :inline="true" label-width="125px">
      <!-- card1 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-chat-dot-square mr5"></i>
            <span>机构信息</span>
          </div>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="机构名称">
              <el-input disabled v-model="form.chname" clearable/>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="产品子项">
              <el-input disabled v-model="form.childproduct" clearable/>
            </el-form-item>
          </el-col>

        </el-card>
      </el-row>

      <!-- card2 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-upload2 mr5"></i>
            <span>网点数据导入</span>
          </div>

          <!-- 第三行 -->
          <el-row>
            <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

              <el-table-column type="selection" width="55" align="center"></el-table-column>

              <el-table-column label="机构名称" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.servcomno}}</template>
              </el-table-column>
              <el-table-column label="上级机构" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.supservcomname}}</template>
              </el-table-column>
              <el-table-column label="网点名称" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.websitename}}</template>
              </el-table-column>

              <el-table-column label="联系人" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.linkperson}}</template>
              </el-table-column>

              <el-table-column label="联系电话" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.phone}}</template>
              </el-table-column>

              <el-table-column label="联系邮箱" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.email}}</template>
              </el-table-column>

              <el-table-column label="工作时间" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.worktime}}</template>
              </el-table-column>

              <el-table-column label="洲" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.continent}}</template>
              </el-table-column>

              <el-table-column label="国家" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.country}}</template>
              </el-table-column>

              <el-table-column label="省" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.province}}</template>
              </el-table-column>

              <el-table-column label="城市" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.city}}</template>
              </el-table-column>

              <el-table-column label="区" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.district}}</template>
              </el-table-column>

              <el-table-column label="详细地址" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.address}}</template>
              </el-table-column>

              <el-table-column label="网点备注" show-overflow-tooltip>
                <template slot-scope="scope">{{scope.row.remark}}</template>
              </el-table-column>
            </el-table>
          </el-row>

          <el-row class="mb10">
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
          </el-row>

          <el-row class="mt10 mb10" style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button icon="el-icon-upload" size="mini" @click="handleImport" type="info">网点数据导入</el-button>
              <el-button icon="el-icon-delete" size="mini" @click="delRow" type="danger">删除选中</el-button>
              <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
            </el-col>
          </el-row>

        </el-card>
      </el-row>
    </el-form>

    <!-- 网点数据导入导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport + '&servcomno=' + this.queryParams.servcomno"
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
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm" size="mini">确 定</el-button>
        <el-button @click="upload.open = false" size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { getProvider, listProviderNetworkByNoTrans, deleteHmpComWebSiteByIds} from "@/api/provider/provider";
  import {getToken} from "@/utils/auth";

  export default {
    name: "import",
    data() {
      return {
        form: {},
        // 表格区域loading
        loading: false,
        // 表格ID
        ids: [],
        total: 0,
        table: {},
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
          // 上传的地址provider/provider/importTemplate
          url: process.env.VUE_APP_BASE_API + "/provider/provider/importData"
        },
        // 模拟表格数据
        tableData: [],
        // 根据servcomno查询服务网点配置对象
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          servcomno: null,
        },
      }
    },
    created() {
      // 获取机构编码
      const servcomno = this.$route.params && this.$route.params.servcomno;
      // 赋值
      this.queryParams.servcomno = servcomno;
      // 获取供应商信息
      this.getProviderByServcomno(servcomno);
      // 根据供应商编码查询服务网点配置对象
      this.getList();

    },
    methods: {
      // 获取供应商编码下的所有服务网点配置对象
      getList() {
        this.loading = true;
        listProviderNetworkByNoTrans(this.queryParams).then(response => {
          this.tableData = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 根据ID获取Provider */
      getProviderByServcomno(servcomno) {
        getProvider(servcomno).then(response => {
          // 往修改页面带数据
          this.form = response.data;
        });
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.websitecode)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },

      // 返回
      back() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/provider/manage/network'});
      },

      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "网点数据导入";
        this.upload.open = true;
      },
      /** 提交上传文件 */
      submitFileForm() {
        this.$refs.upload.submit();
      },
      /** 下载模板操作 */
      importTemplate() {
        this.download('provider/provider/importTemplate', {
          ...this.queryParams
        }, `网点数据模板_${new Date().getTime()}.xlsx`)
      },
      /** 文件上传中处理 */
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      /** 文件上传成功处理 */
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        this.getList();
      },
      /** 删除按钮操作 */
      delRow(row) {
        const websitecode = row.websitecode || this.ids;
        if (websitecode == '') {
          this.$message.error('请先选中再进行删除');
          return;
        }
        // 显示样式
        var websiteStr = "<br/>"
        for(var i=0; i<websitecode.length; i++) {
          websiteStr += websitecode[i] + "<br/>";
        }
        this.$confirm('是否确认删除网点编码为"' + websiteStr + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
          dangerouslyUseHTMLString: true,
        }).then(function () {
          return deleteHmpComWebSiteByIds(websitecode);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
    },
  }
</script>

<style scoped>

</style>
