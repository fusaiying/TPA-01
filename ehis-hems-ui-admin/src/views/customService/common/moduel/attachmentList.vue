<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span style="color: blue">附件信息</span>
    </div>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="attachmentInfoData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style=" width: 100%;">
      <el-table-column prop="attachmentName" align="center" label="附件名称" show-overflow-tooltip/>
      <el-table-column prop="attachmentType" align="center" label="附件类型" show-overflow-tooltip>
        <template slot-scope="scope" v-if="scope.row.attachmentType">
          <span>{{selectDictLabel(cs_attachment_type, scope.row.attachmentType)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="uplodBy" align="center" label="上传人" show-overflow-tooltip/>
      <el-table-column prop="uploadTime" align="center" label="上传时间 " show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.uploadTime | changeDate}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="remark" align="center" label="备注" show-overflow-tooltip/>
      <el-table-column align="center" fixed="right" label="操作" width="140">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="download(scope.row)">下载</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

</template>
<script>


import moment from "moment";

export default {
  props: {
    routerParams: Object,
    attachmentInfoData: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
    }
  },
  data() {
    return {
      cs_attachment_type: [],
    }
  },
  async mounted() {
    await this.getDicts("cs_attachment_type").then(response => {
      this.cs_attachment_type = response.data;
    })
  },
  methods: {}
}
</script>
<style scoped>
.info_span {
  font-size: 13px;
}

.item-width {
  width: 160px;
}

.el-tree /deep/ .el-tree-node__content {
  height: 30px;
}

.check_box {
  margin-top: 16px;
}

.collapse-card.el-card /deep/ .el-card__body {
  padding: 0;
}

.el-collapse /deep/ .el-collapse-item__header {
  background-color: #f8f8f8;
  padding: 0 10px;
}

.el-collapse /deep/ .el-collapse-item__wrap {
  padding: 0 10px;
}

.el-checkbox {
  margin-left: 20px;
}

.el-checkbox-group {
  margin-left: 20px;
}

.el-checkbox-group /deep/ .el-checkbox {
  display: block;
}

.to_right {
  width: 120px;
  text-align: right;
}

/deep/ .custom-tree-node {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
}

/deep/ .custom-tree-node span {
  flex: 1;
  display: inline-block;
  width: 80%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/deep/ .el-radio{
  /*display: block;*/
  line-height: 30px;
  /*white-space: normal;*/
  /*margin-right: 0;*/
}

</style>
<style>
.el-tooltip__popper {
  max-width: 400px;
}

.el-table /deep/ .el-table__expanded-cell {
  padding: 10px;
}

</style>

