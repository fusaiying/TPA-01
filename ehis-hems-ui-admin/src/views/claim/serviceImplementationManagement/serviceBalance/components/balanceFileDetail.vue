<template>
  <el-card class="box-card" v-show="params.isShow" id="balanceFileDiv">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">清单附件（必须）</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <el-upload
          ref="balanceFile"
          :disabled="!params.isAdd"
          class="upload-demo"
          :headers="headers"
          :action="url"
          :data="{'taskNo': this.params.taskNo}"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :before-upload="beforeUpload"
          :on-success="uploadSuccess"
          :on-progress="uploadProgress"
          :on-preview="handlePreview"
          multiple
          accept=".xls,.xlsx,.jpg,.png,.pdf"
          :file-list="fileList">
          <el-button slot="trigger" :disabled="!params.isAdd" size="small" type="primary">点击上传</el-button>
          <el-button size="mini" type="text"  style="padding-left: 10px;color:red;" @click.stop="clearfile" :disabled="!params.isAdd">批量删除</el-button>
          <div slot="tip" class="el-upload__tip">只能上传excel/jpg/png/pdf文件，且不超过500kb</div>
        </el-upload>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<script>

import {listBalanceFile, updateBalanceFile} from "@/api/claim/serviceBalance";
import {getToken} from "@/utils/auth";

export default {
  name: "contractFileTable",
  props: {
    params: {
      type: Object,
      required: true,
      default: function () {
        return {
          //是否展示
          isShow: false,
          //加载类型
          isAdd: false,
          //任务号
          taskNo: null
        }
      }
    },
    //字典数组
    dictList: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  watch: {
    params: {
      immediate: true,
      handler: function(newVal) {
        this.initData();
      }
    },
    dictList: function (newVal, oldVal) {
      this.initDictData();
    }
  },
  data() {
    return {
      // 显示
      activeNames: [],
      // 设置上传的请求头部
      headers: {Authorization: "Bearer " + getToken()},
      // 上传的地址
      url: process.env.VUE_APP_BASE_API + "/order/balanceFile/upload",
      //列表
      fileList: []
    };
  },
  created() {

  },
  async mounted() {

  },
  methods: {
    //批量删除
    clearfile(){
      let count=0
      if(this.fileList.length>0){
        new Promise(resolve => {
          this.fileList.forEach(file => {
            const data = {
              serialNo: file.serialNo,
              status: "N"
            };
            updateBalanceFile(data).then(res => {
               count++
                if(count==this.fileList.length){
                  resolve()
                }
            });
          })
          }
        ).then(res=>{
          this.getList()
          this.msgSuccess('删除成功！');
        })
      }
    },
    /** 初始化数据 */
    initData() {
      if (this.params.isShow) {
        this.activeNames = ['1'];
        this.getList();
      }
    },
    /** 初始化字典 */
    initDictData() {
      if (this.dictList != null && this.dictList.length != 0) {

      }
    },
    /** 查询列表 */
    getList() {
      //清除列表
      this.fileList = [];
      const query = {
        fileSource: this.params.taskNo,
        status: "Y"
      };
      listBalanceFile(query).then(res => {
        if (res != null && res.code === 200) {
          res.rows.forEach(item => {
            let obj= new Object();
            obj.serialNo = item.serialNo;
            obj.name = item.fileName;
            this.fileList.push(obj);
          });
        }
      }).catch(res => {

      });
    },
    /** 删除文件 */
    handleRemove(file, fileList) {
      if (file && file.status==="success") {
        const data = {
          serialNo: file.serialNo,
          status: "N"
        };
        updateBalanceFile(data).then(res => {
          if (res != null && res.code === 200) {
            this.msgSuccess('删除成功！');
          } else {
            this.msgError('删除失败！');
          }
          this.getList();
        });
      }

    },

    /** 删除文件前 */
    beforeRemove(file, fileList) {
      if (file && file.status==="success") {
        const fileName = file.name.substring(file.name.lastIndexOf('.') + 1);
        //excel/jpg/png/pdf
        if (fileName === "xls" || fileName === "xlsx" || fileName === "jpg" || fileName === "png" || fileName === "pdf") {
          return this.$confirm(`确定移除 ${file.name}？`);
        } else {
          return true;
        }
      }

      //return false
    },
    /** 上传文件前 */
    beforeUpload(file) {
      //限制文件不能超过500kb
      if (file.size / 1024 > 500) {  // 限制文件大小
        this.$message.warning(`上传的单个文件大小不能超过500kb`)
        return false;
      }

      const fileName = file.name.substring(file.name.lastIndexOf('.')+1);
      //excel/jpg/png/pdf
      if (fileName === "xls" || fileName === "xlsx" || fileName === "jpg" || fileName === "png" || fileName === "pdf") {
        return true;
      } else {
        this.$message.warning('只能上传excel/jpg/png/pdf文件！');
        return false;
      }



    },
    /** 导入成功后事件 */
    uploadSuccess(response, file, fileList){
      this.params.isAdd = true;
      if (response != null && response.code === 200) {
        this.msgSuccess('导入成功！');
      } else {
        this.msgError('导入失败：' + response.msg);
      }
      this.getList();
    },
    /** 上传中 */
    uploadProgress() {
     // this.params.isAdd = false;
    },
    /** 预览 */
    handlePreview(file) {
      this.download('order/balanceFile/download?serialNo=' + file.serialNo, {}, file.name);
    }
  }
};
</script>

<style scoped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
/*自定义样式*/
.receive_table {
  width: 98%;
  padding: 0px;
  margin-left: 1%;
  margin-right: 1%;
}

</style>
