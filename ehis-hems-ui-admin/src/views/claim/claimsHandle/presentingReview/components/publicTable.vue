<template>
  <el-table
    ref="publicTable"
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    @selection-change="selectionLineChangeHandle"
    style="width: 100%;">
    <el-table-column
      type="selection"
      width="40"/>
    <el-table-column sortable align="center" prop="batchno" min-width="160" label="批次号" show-overflow-tooltip/>
    <el-table-column  align="center"  min-width="100" prop="source" label="交单来源" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel( delivery_sourceOptions, scope.row.source)}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="hospitalcode" min-width="120" label="就诊医院" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{scope.row.chname1}} | {{scope.row.enname1}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="claimtype" min-width="160" label="理赔类型" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel( claimtypeOptions, scope.row.claimtype)}}</span>
      </template>
    </el-table-column>
    <el-table-column sortable align="center" prop="submitdate" min-width="120" label="交单日期" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="updateTime" min-width="120" label="操作日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="casenum" label="案件数" min-width="90" show-overflow-tooltip/>
    <el-table-column align="center"  min-width="110" prop="batchtotal" label="批次总金额" show-overflow-tooltip/>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="handleOne(scope.row.batchno)">获取</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import {changeStatus} from '@/api/claim/presentingReview'
  let dictss = [{dictType: 'delivery_source'}, {dictType: 'claimtype'},]
export default {

  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      },
    },
    // 子组件接收函数
    searchHandle: {
      type: Function
    },
    status: String
  },
  data() {
    return {
      publicTotal:0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: undefined
      },
      dictList:[],
      dataonLineListSelections: [],
      delivery_sourceOptions:[],
      claimtypeOptions:[],
    }
  },
  async created() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.delivery_sourceOptions = this.dictList.find(item => {
      return item.dictType === 'delivery_source'
    }).dictDate
    this.claimtypeOptions = this.dictList.find(item => {
      return item.dictType === 'claimtype'
    }).dictDate

  },
  methods: {
    // 单个获取
    handleOne(batchno) {
      let batchnoes =[batchno]
      changeStatus(batchnoes).then(res=>{
        if (res!=null && res.code===200){
          this.$message({
            message: '获取成功！',
            type: 'success',
            center: true,
            showClose: true
          })
          this.searchHandle()
        }else {
          this.$message.error('获取失败！')
        }
      })
    },
    selectionLineChangeHandle (val) {
      this.dataonLineListSelections = val
    },
    handle(){
      let batchnoes =[]
      for(let i = 0; i< this.dataonLineListSelections.length; i++){
        batchnoes.push(this.dataonLineListSelections[i].batchno)
      }
      if (batchnoes.length>0){
        changeStatus(batchnoes).then(res=>{
          if (res!=null && res.code===200){
            this.$message({
              message: '获取成功！',
              type: 'success',
              center: true,
              showClose: true

            })
            this.searchHandle()
          }else {
            this.$message.error('获取失败！')
          }
        })
      }else {
        return this.$message.warning(
          "请选择要获取的记录，进行批量获取操作。！"
        );
      }

    }
  }
}
</script>
