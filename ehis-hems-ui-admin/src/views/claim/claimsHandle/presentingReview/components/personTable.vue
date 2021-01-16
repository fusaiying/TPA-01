<template>
  <div class="app-container">
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column sortable align="center" prop="batchno" min-width="160" label="批次号" show-overflow-tooltip/>
      <el-table-column  align="center" min-width="100" prop="source" label="交单来源"
                       show-overflow-tooltip/>
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
      <el-table-column align="center"  min-width="110" prop="batchtotal" label="批次总金额"
                       show-overflow-tooltip/>
      <el-table-column align="center" prop="updateBy" label="操作人" min-width="90" show-overflow-tooltip/>
      <el-table-column align="center" prop="organcode" min-width="120" label="机构" show-overflow-tooltip/>
      <el-table-column v-if="status === '02'" align="center" min-width="100" prop="batchstatus" label="批次状态"
                       show-overflow-tooltip/>
      <el-table-column align="center" label="操作" min-width="94" fixed="right">
        <template slot-scope="scope">
          <el-button v-if="status==='02'" size="small" type="text" @click="editPresenting(scope.row,'show')">查看
          </el-button>
          <el-button v-if="status==='01'" size="small" type="text" @click="editPresenting(scope.row,'editReview')">处理
          </el-button>
          <el-button v-if="status==='01'" style="color: red" size="small" type="text" @click="invalid(scope.row)">无效</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {invalid} from '@/api/claim/presentingReview'
  export default {

    props: {
      tableData: {
        type: Array,
        default: function () {
          return []
        }
      },
      status: String
    },
    data() {
      return {

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
      recovery(row) {
      },
      getCNReasonType(row, col) {

      },
      invalid(row) {
        this.$confirm(`是否确认对批次进行无效处理?`, '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          //调用修改接口  传批次号
          invalid(row.batchno).then(res=>{
            if (res!=null && res.code===200){
              this.$message({
                message: '无效处理成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.searchHandle()
            }else {
              this.$message.error('无效处理失败！')
            }
          })
        })
      },

    }
  }
</script>
