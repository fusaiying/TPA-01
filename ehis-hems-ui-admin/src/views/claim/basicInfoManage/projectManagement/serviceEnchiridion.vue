<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>服务手册列表信息</span>
        <span style="float: right;">
<!--          <el-button size="mini" type="success" @click="upload">上传</el-button>-->
          <el-upload
            class="upload-demo"
            action="url"
            :headers="headers"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="1"
            :on-exceed="handleExceed"
            :file-list="fileList">
            <el-button size="small" type="primary">上传</el-button>
            <!--&lt;!&ndash;            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>&ndash;&gt;-->
          </el-upload>
        </span>
      </div>
      <el-table :data="enchiridionList"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="mini"
                tooltip-effect="dark"
                class="projectList_table"
      >
        <el-table-column label="服务手册编码" align="center" prop="manualCode" show-overflow-tooltip/>
        <el-table-column label="服务手册名称" align="center" prop="manualName" show-overflow-tooltip/>
        <el-table-column label="操作人" align="center" prop="createBy" show-overflow-tooltip/>
        <el-table-column label="操作时间" align="center" prop="createTime" show-overflow-tooltip/>
        <el-table-column label="操作" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              style="padding: 0px;"
              @click="download(scope.row)"
            >下载
            </el-button>
            <el-button
              size="mini"
              type="text"
              @click="deleteOne(scope.row)"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="totalNum>0"
        :total="totalNum"
        :page-sizes="pageSizes"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>
  </div>
</template>

<script>
  import {listInfo, delInfo} from '@/api/provider/manual.js'

  export default {
    name: "serviceEnchiridion",
    data(){
      return{
        // 服务手册列表
        enchiridionList:[],
        // 页码
        totalNum: 0,
        pageSizes: [5,10,15],
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        // 是否支持多选文件
        multiple: false,
        // 上传地址
        url: '',
        // 上传的请求头部
        headers: {},
        // 上传的文件列表 格式：[{name: 'food.jpg', url: 'https://xxx.cdn.com/xxx.jpg'}]
        fileList: [],
      };
    },
    created() {
      this.getList();
    },
    methods: {
      getList(){
        listInfo(this.queryParams).then(res => {
          this.enchiridionList = res.rows;
          this.totalNum = res.total;
          console.log("后端数据：",res)
        })
      },
      // 文件列表移除文件
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      // 点击文件列表中已上传的文件
      handlePreview(file) {
        console.log(file);
      },
      // 文件超出限制个数
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      // 删除文件
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      download(row){

      },
      deleteOne(row){
        const code = row.manualCode;
        this.$confirm('是否确认删除编号为"' + code + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(code);
        }).then(() => {
          if(this.enchiridionList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      }
    }
  };
</script>
