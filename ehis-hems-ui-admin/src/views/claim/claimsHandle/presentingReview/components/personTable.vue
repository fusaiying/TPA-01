<template>
  <div class="app-container">
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      @sort-change="onSortChange"
      style="width: 100%;">
      <el-table-column sortable="custom" :sort-orders="['ascending','descending',null]" align="center" prop="batchno" min-width="160" label="批次号" show-overflow-tooltip/>
      <el-table-column  align="center" min-width="100" prop="source" label="交单来源"
                        show-overflow-tooltip>
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
      <el-table-column align="center" prop="organcode" min-width="120" label="机构" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{getDeptName( deptOptions, scope.row.organcode)}}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="status === '02'" align="center" min-width="100" prop="batchstatus" label="批次状态"
                       show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{selectDictLabel( batchs_statusOptions, scope.row.batchstatus)}}</span>
        </template>
      </el-table-column>
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
  import {invalid,getThisDept} from '@/api/claim/presentingReview'
  let dictss = [{dictType: 'delivery_source'}, {dictType: 'claimtype'}, {dictType: 'batchs_status'}]
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
        prop:'',
        order:'',
        dictList:[],
        delivery_sourceOptions:[],
        claimtypeOptions:[],
        batchs_statusOptions:[],
        deptOptions:[],
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
      this.batchs_statusOptions = this.dictList.find(item => {
        return item.dictType === 'batchs_status'
      }).dictDate
      getThisDept().then(res=>{
          this.deptOptions=res.deptlist
      })

    },
    methods: {
      // 处理跳转
      editPresenting(row, status) {
        let data = encodeURI(
          JSON.stringify({
            batchno: row.batchno, //批次号
            batchstatus: row.batchstatus, //批次状态
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
              this.$parent.$parent.$parent.$parent.searchTable()
            }else {
              this.$message.error('无效处理失败！')
            }
          })
        })
      },
      getDeptName(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
          if (datas[key].deptId === parseInt(value)) {
            actions.push(datas[key].deptName);
            return true;
          }
        })
        return actions.join('');
      },
      onSortChange({ prop, order }) {
        this.prop=prop
        if (order==='ascending'){
          this.order='asc'
        }else if (order==='descending'){
          this.order='desc'
        }else if (order==null){
          this.prop=''
          this.order=''
        }
        this.$parent.$parent.$parent.$parent.searchHandle()
      }
    }
  }
</script>
