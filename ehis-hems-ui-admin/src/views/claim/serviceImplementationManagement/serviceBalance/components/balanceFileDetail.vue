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
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :before-upload="beforeUpload"
          multiple
          accept=".xls,.xlsx,.jpg,.png,.pdf"
          :file-list="fileList">
          <el-button slot="trigger" :disabled="!params.isAdd" size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传excel/jpg/png/pdf文件，且不超过500kb</div>
        </el-upload>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<script>

// import {listBank} from '@/api/supplierManager/supplier'
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
      // 遮罩层
      loading: false,
      // 显示
      activeNames: [],
      //列表
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
    };
  },
  created() {

  },
  async mounted() {

  },
  methods: {
    /** 初始化数据 */
    initData() {
      if (this.params.isShow) {
        this.activeNames = ['1'];
      }
    },
    /** 初始化字典 */
    initDictData() {
      if (this.dictList != null && this.dictList.length != 0) {

      }
    },
    /** 删除文件 */
    handleRemove(file, fileList) {

    },
    /** 删除文件前 */
    beforeRemove(file, fileList) {
      const fileName = file.name.substring(file.name.lastIndexOf('.')+1);
      //excel/jpg/png/pdf
      if (fileName === "xls" || fileName === "xlsx" || fileName === "jpg" || fileName === "png" || fileName === "pdf") {
        return this.$confirm(`确定移除 ${ file.name }？`);
      } else {
        return true;
      }
    },
    /** 上传文件前 */
    beforeUpload(file) {
      const fileName = file.name.substring(file.name.lastIndexOf('.')+1);
      //excel/jpg/png/pdf
      if (fileName === "xls" || fileName === "xlsx" || fileName === "jpg" || fileName === "png" || fileName === "pdf") {
        return true;
      } else {
        this.$message.warning('只能上传excel/jpg/png/pdf文件！');
        return false;
      }
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
