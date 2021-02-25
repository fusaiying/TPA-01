<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    @expand-change="getMinData"
    @sort-change="onSortChange"
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
          <el-table-column prop="rptNo" label="报案号" align="center"/>
          <el-table-column prop="caseStatus" label="案件状态" align="center">
            <template slot-scope="scope">
              <span>{{selectDictLabel( claim_statusOptions, scope.row.caseStatus)}}</span>
            </template>
          </el-table-column><!--status待确认-->
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
    <el-table-column sortable="custom" :sort-orders="['ascending','descending',null]" align="center" prop="batchno"
                     min-width="160" label="批次号" show-overflow-tooltip/>
    <el-table-column align="center" min-width="100" prop="source" label="交单来源" show-overflow-tooltip>
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
    <el-table-column align="center" min-width="110" prop="batchtotal" label="批次总金额" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ scope.row.batchtotal}} {{scope.row.currency}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="updateBy" label="操作人" min-width="90" show-overflow-tooltip/>
    <el-table-column prop="organcode" min-width="120" label="机构" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{getDeptName( deptOptions, scope.row.organcode)}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" min-width="100" prop="batchstatus" label="批次状态" show-overflow-tooltip>
      <template slot-scope="scope">
        <span v-if="status!='01'">{{selectDictLabel( batchs_statusOptions, scope.row.batchstatus)}}</span>
        <span v-else>{{selectDictLabel( batchs_statusOptions, scope.row.batchstatus)}}-{{scope.row.remark}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='02'" size="small" type="text" @click="editPresenting(scope.row,'show')">查看
        </el-button>
        <el-button v-if="status==='01' || status==='03'" size="small" type="text" @click="editPresenting(scope.row,'edit')">处理
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import {getMinData, getThisDept} from '@/api/claim/presentingReview'

  let dictss = [{dictType: 'delivery_source'}, {dictType: 'claimtype'}, {dictType: 'batchs_status'}, {dictType: 'claim_status'}]
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
        prop: '',
        order: '',
        loading: true,
        dictList: [],
        delivery_sourceOptions: [],
        claim_statusOptions: [],
        claimtypeOptions: [],
        batchs_statusOptions: [],
        deptOptions: [],
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
      this.claim_statusOptions = this.dictList.find(item => {
        return item.dictType === 'claim_status'
      }).dictDate
      let item = {
        pageNum: 1,
        pageSize: 200,
      }
      getThisDept(item).then(res => {
        this.deptOptions = res.deptlist
      })
    },
    methods: {
      // 处理跳转
      editPresenting(row, status) {
        let data = encodeURI(
          JSON.stringify({
            node:'presenting',
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
      //条码打印
      codePrint() {

      },
      getMinData(row, expandedRows) {
        this.loading = true
        //判断只有展开是做请求
        if (expandedRows.length > 0) {
          let data ={
            batchNo:row.batchno
          }
          getMinData(data).then(res => {
            this.tableData.forEach((temp, index) => {
              if (temp.batchno === row.batchno) {
                this.tableData[index].minData = res.rows
              }
            })
            this.loading = false
          })
        }
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
      onSortChange({prop, order}) {
        this.prop = prop
        if (order === 'ascending') {
          this.order = 'asc'
        } else if (order === 'descending') {
          this.order = 'desc'
        } else if (order == null) {
          this.prop = ''
          this.order = ''
        }
        this.$parent.$parent.$parent.$parent.searchHandle()
      }
    }
  }
</script>
<style scoped>

  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
</style>

