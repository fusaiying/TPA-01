<template>
  <div class="app-container">
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>文件查询</span>
          </div>
          <el-row>
            <el-form :model="queryParams" ref="queryParams" :inline="true" label-width="125px">
              <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-form-item label="文件编码">
                  <el-input v-model="queryParams.filecode" placeholder="请输入文件编码" clearable ></el-input>
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-form-item label="文件名称">
                  <el-input v-model="queryParams.filename" placeholder="请输入文件名称" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-form-item label="上传人">
                  <el-input v-model="queryParams.operator" placeholder="请输入上传人" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-form>
          </el-row>

          <!-- 第二行 -->
          <el-row :gutter="10" class="mb8" style="text-align: right;">
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            <el-button icon="el-icon-d-arrow-left" size="mini" @click="close">返回</el-button>
          </el-row>

          <!-- 第三行 -->
          <el-row>
              <el-table v-loading="loading" :data="fileList">
                <el-table-column type="index" width="50" align="center"></el-table-column>
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
        // 查询表单
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          filecode: undefined,
          filename: undefined,
          filesource: undefined,
          operator: undefined
        }
      }
    },
    created() {
      this.productno = this.$route.params && this.$route.params.productno;
      this.getList();
    },
    methods: {
      /** 查询文件列表 */
      getList() {
        this.loading = true;
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
      }
    },
  }
</script>
