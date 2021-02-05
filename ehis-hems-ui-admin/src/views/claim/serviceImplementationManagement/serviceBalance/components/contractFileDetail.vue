<template>
  <el-card class="box-card" v-show="params.isShow">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">合约附件信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <el-table
          ref="contractFileTable"
          :data="contractFileList"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="annexName" label="附件名称"  align="center" show-overflow-tooltip />
          <el-table-column prop="annexType" label="附件类型"  align="center" show-overflow-tooltip />
          <el-table-column prop="uploadMan" label="上传人"  align="center" show-overflow-tooltip />
          <el-table-column prop="uploadTime" label="上传时间" align="center" show-overflow-tooltip />
          <el-table-column prop="remak" label="备注" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="downloadFile(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
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
          //供应商
          supplierCode: null
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
      contractFileList: []
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
    /** 附件下载 */
    downloadFile() {
      alert('该功能待开发。。。');
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
