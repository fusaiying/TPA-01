<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="文件清单"
    width="45%"
    @open="getFileList">
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      v-loading="loadFile"
      @selection-change="selectionChange"
      size="small"
      highlight-current-row
      tooltip-effect="dark">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column width="80" property="type" label="类型" :formatter="getImagetype" align="center"/>
      <el-table-column width="280" property="name" label="文件" align="center">
        <template slot-scope="scope">
          <span @click="dowloadFileOne(scope.row)" style="font-size: 12px; color: #1890ff;cursor: pointer;">{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column property="addtime" label="添加时间" align="center"/>
    </el-table>
    <div style="margin-top: 30px; text-align: right;">
      <el-button size="mini" type="primary" @click="dowloadFileAll" :disabled="disabled">批量下载</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { getFileList, dowloadFileOne, dowloadFileAll } from '@/api/claim/handleDeal'
import { downloadData } from '@/utils'
export default {
  //dicts: ['image_type'],
  props: {
    dictList:Array,
    value: Boolean,
    node: String,
    claimno: String
  },
  data() {
    return {
      tableData: [
        { policyNo: 'ERF23423645' }
      ],
      loadFile: false,
      dowloadFileAllList: [], //下载暂存数组
      disabled: true, // 批量下载按钮禁用控制
      image_typeOptions: []
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.image_typeOptions = this.dictList.find(item => {
        return item.dictType == 'image_type'
      }).dictDate
    }
  },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
    },
    getFileList(){
      this.loadFile = true
      getFileList(this.claimno).then(res => {
        if (res.status === '1') {
          this.tableData = res.data
        } else {
          this.$message({ message: '查询下载列表错误！', type: 'error' })
        }
      }).finally(() => {
        this.loadFile = false
      })
    },
    multDownloadHandle() {},
    // 材料类型转换
    getImagetype(row,col) {
      /*return this.dict.label.image_type[row.type]*/
      return this.selectDictLabel(this.image_typeOptions, row.type);
    },
    // 单个下载文件
    dowloadFileOne(row) {
      let pageid = row.pageid
      dowloadFileOne(pageid).then(res =>{
        downloadData(res,row.name)
      })
    },
    // 选择下载数据
    selectionChange(val) {
      this.dowloadFileAllList = []
      this.dowloadFileAllList = val
      this.disabled = this.dowloadFileAllList.length ? false : true
    },
    // 批量下载文件
    dowloadFileAll() {
      let list = []
      this.dowloadFileAllList.map((item) => {
        list.push(item.pageid)
      })
      dowloadFileAll(list).then(res =>{
        downloadData(res, '文件' + '.rar')
      })
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.el-table /deep/ .el-table__expanded-cell {
  padding: 20px;
}
.expand_span {
  font-size: 12px;
}
</style>
