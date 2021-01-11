<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    @expand-change="getMinData"
    style="width: 100%;">
    <el-table-column v-if="status==='02'" type="expand">
      <template slot-scope="scope">
        <el-table :data="scope.row.minData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  highlight-current-row
                  size="small"
                  v-loading="loading"
                  tooltip-effect="dark"
                  style="width: 100%;">
          <el-table-column prop="rptno" label="报案号" align="center"/>
          <el-table-column prop="status" label="案件状态" align="center"/><!--status待确认-->
          <el-table-column prop="updateTime" label="操作日期" align="center"/>
          <el-table-column prop="updateBy" label="操作人" align="center"/>
          <el-table-column label="操作" align="center">
            <template slot-scope="props">
              <el-button size="small" type="text" @click="codePrint">条码打印</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </el-table-column>
    <el-table-column sortable align="center" prop="batchno" min-width="160" label="批次号" show-overflow-tooltip/>
    <el-table-column align="center" min-width="100" prop="source" label="交单来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="hospitalcode" min-width="120" label="就诊医院" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{scope.row.chname1}} | {{scope.row.enname1}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="claimtype" min-width="160" label="理赔类型" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="submitdate" min-width="120" label="交单日期" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.submitdate, '{y}-{m}-{d}')}}</span>
      </template>
    </el-table-column>
    <el-table-column sortable align="center" prop="updateTime" min-width="120" label="操作日期" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.updateTime, '{y}-{m}-{d}')}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="casenum" label="案件数" min-width="90" show-overflow-tooltip/>
    <el-table-column align="center" min-width="110" prop="batchtotal" label="批次总金额" show-overflow-tooltip/>
    <el-table-column prop="updateBy" label="操作人" min-width="90" show-overflow-tooltip/>
    <el-table-column prop="organcode" min-width="120" label="机构" show-overflow-tooltip/>
    <el-table-column align="center" min-width="100" prop="batchstatus" label="批次状态" show-overflow-tooltip/>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='02'" size="small" type="text" @click="editPresenting(scope.row,'show')">查看
        </el-button>
        <el-button v-if="status==='01'" size="small" type="text" @click="editPresenting(scope.row,'edit')">处理
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import {getMinData} from '@/api/claim/presentingReview'
  import {encrypt} from "@/utils/rsaEncrypt"


  export default {

    props: {
      tableData: {
        type: Array,
        default: function () {
          return []
        }
      },
      status: String,
    },
    data() {
      return {
        loading:true
      }
    },
  mounted() {

  },
    methods: {
      // 处理跳转
      editPresenting(row, status) {
        let data = encodeURI(
          JSON.stringify({
            batchno: row.batchno, //批次号
            status,//新增or查看
            claimtype: row.claimtype//理赔类型
          })
        )
        this.$router.push({
          path: '/claims-handle/presenting-edit',
          query: {
            data
          }
        })
      },
      //条码打印
      codePrint() {

      },
      getMinData(row, expandedRows) {
        this.loading=true
        //判断只有展开是做请求
        if (expandedRows.length > 0) {
          getMinData(row.batchno).then(res => {
            this.tableData.forEach((temp,index)=>{
              if (temp.batchno===row.batchno){
                this.tableData[index].minData=res.rows
              }
            })
            this.loading=false
          })
        }
      }
    }
  }
</script>
<style scoped>

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>

