<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="案件拆分"
    width="76%"
    @open="open">
    <el-form ref="splitForm" :model="splitForm" style="margin-bottom: 20px;">
      <el-checkbox-group v-model="splitForm.checkedList" size="mini">
        <el-checkbox v-for="item in descriptionList" :label="item" :key="item">{{getapplicationReason(item) }}</el-checkbox>
      </el-checkbox-group>
    </el-form>
    <el-table
      v-loading="loading"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark">
      <el-table-column width="55" align="center" label="">
        <template slot-scope="scope">
          <el-radio v-model="radio" :label="scope.row.id" @change.native="handleCurrentChange(scope.row, scope.$index)">{{ &nbsp; }}</el-radio>
        </template>
      </el-table-column>>
      <el-table-column :formatter="formatApplicaReason" property="description" show-overflow-tooltip label="申请类别" align="center"/>
      <el-table-column property="eventdate" show-overflow-tooltip label="出险日期" align="center"/>
      <el-table-column property="visitdate" show-overflow-tooltip label="就诊区间" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.visitdate + '~' + scope.row.dischargedate}}</span>
        </template>
      </el-table-column>
      <el-table-column property="sumday" show-overflow-tooltip label="就诊天数" align="center">
        <template slot-scope="scope">
          {{scope.row.sumday == 0 ? 1 : scope.row.sumday}}
        </template>
      </el-table-column>
      <el-table-column :formatter="getIncidenttype" property="incidenttype" show-overflow-tooltip label="出险类型" align="center"/>
      <el-table-column property="incident" show-overflow-tooltip label="事件信息" align="center"/>
      <el-table-column property="hospitalname" show-overflow-tooltip label="医院" align="center"/>
      <el-table-column property="departmentname" show-overflow-tooltip label="科室" align="center"/>
      <el-table-column property="maindiagnosis" show-overflow-tooltip label="诊断" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.maindiagnosis + (scope.row.secondarydiagnosis ? '|' + scope.row.secondarydiagnosis : '') + (scope.row.otherdiagnosis ? '|' + scope.row.otherdiagnosis : '')}}</span>
        </template>
      </el-table-column>
      <el-table-column property="hospitallevel" show-overflow-tooltip label="等级" align="center"/>
      <el-table-column :formatter="getRenewal" property="renewal" show-overflow-tooltip label="发生地" align="center"/>
    </el-table>
    <!--分页组件-->
    <el-pagination
      :total="totalCount"
      :current-page="pageInfo.page"
      :page-size="pageInfo.pageSize"
      :page-sizes="[5,10, 20, 30, 50,100]"
      style="margin-top: 8px; text-align: right;"
      layout="prev, pager, next, sizes"
      @size-change="sizeChange"
      @current-change="pageChange"
    />
    <span slot="footer" class="dialog-footer">
      <el-button size="mini" type="primary" @click="splitHandle">拆分</el-button>
      <el-button size="mini" @click="dialogClosed">取 消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { getSplitIncident, splitCase } from '@/api/splitCase/index.js'
export default {
  //dicts: ['application_category', 'incidenttype'],
  filters: {

  },
  props: {
    dictList:Array,
    claimno: {
      type: String,
      default: ''
    },
    value: Boolean,
    node: String,
    flag: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      searchForm: {},
      tableData: [],
      lists: ['门诊费用', '住院费用', '医疗津贴', '轻症疾病', '重大疾病'],
      splitForm: {
        checkedList: []
      },
      subTable: [{ policyNo: 'ERF23423645', insuredName: '小米', renewal: '是' }],
      totalCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      radio: null, // 当前表格选中行,
      descriptionList: [],
      that: {},
      incidentno: '',
      index: '',
      chooseClaimno: '',
      loading: false,
     /* 'application_category', 'incidenttype'*/
      application_categoryOptions: [],
      incidenttypeOptions: []
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
      this.application_categoryOptions = this.dictList.find(item => {
        return item.dictType == 'application_categor'
      }).dictDate
      this.incidenttypeOptions = this.dictList.find(item => {
        return item.dictType == 'incidenttype'
      }).dictDate
    }
  },
  methods: {
    getapplicationReason(value) {
      /*return that.dict.label.application_category[value]*/
      return this.selectDictLabel(this.application_categoryOptions,value);
    },
    open() {
      this.getSplitIncidentData()
      this.that = this
    },
    getIncidenttype(row) {
      /*return this.dict.label.incidenttype[row.incidenttype]*/
      return this.selectDictLabel(this.incidenttypeOptions, row.incidenttype)
    },
    formatApplicaReason(row) {
      let reason = ''
      if (row.description) {
        row.description.split(',').map((item, i) => {
          this.selectDictLabel(this.application_categoryOptions,item) /*this.dict.label.application_category[item]*/ ? reason += `|${/*this.dict.label.application_category[item]*/this.selectDictLabel(this.application_categoryOptions,item)}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    getSplitIncidentData() {
      this.loading = true
      const params = {
        claimno: this.claimno,
        // claimno: 'ADDCN03',
        pageno: this.pageInfo.page,
        pagesize: this.pageInfo.pageSize
      }
      getSplitIncident(params).then(res => {
        if (res.status === '1') {
          this.tableData = res.data.list
          this.tableData.forEach((item, ind) => {
            item.id = ind
          })
          this.totalCount = res.data.total
        }
      }).finally(() => {
        this.loading = false
      })
    },
    // 拼接发生地
    getRenewal(row) {
      return row.occurrenceprovince + row.occurrencecity + row.occurrencedistrict
    },
    handleCurrentChange(row, index) {
      this.radio = row.id
      this.descriptionList = row.description.split(',')
      this.incidentno = row.incidentno
      this.chooseClaimno = row.claimno
      this.index = index
    },
    dialogClosed() {
      this.descriptionList = []
      this.splitForm.checkedList = []
      this.radio = null
      this.$emit('input', false)
    },
    relationHandle() {},
    sizeChange(val) {
      this.pageInfo.pageSize = val
      this.getSplitIncidentData()
    },
    pageChange(val) {
      this.pageInfo.page = val
      this.getSplitIncidentData()
    },
    splitHandle() {
      if (this.radio === null) {
        this.$message.warning('请选择要拆分的事件信息！')
      } else {
        if (this.splitForm.checkedList.length === 0) {
          this.$message.warning('请选择申请类别！')
        } else if (this.descriptionList.length === 1) {
          this.$message.warning('申请类别只有一项时不可进行拆分！')
        } else if (this.splitForm.checkedList.length === this.descriptionList.length) {
            this.$message.warning('申请类别全部选择时不可进行拆分！')
        } else {
          const params = {
            categorycode: this.splitForm.checkedList.join(','),
            claimno: this.chooseClaimno,
            incidentno: this.incidentno
          }
          splitCase(params).then(res => {
            if (res.status === '1') {
              this.$message.success('拆分成功！')
              this.getSplitIncidentData()
              this.descriptionList = []
              this.splitForm.checkedList = []
              this.radio = null
            } else if (res.data.errCode === 'SL008' || res.data.errCode === 'SL009') {
              this.$message.error(res.data.errMsg)
            } else {
              this.$message.error('拆分失败！')
            }
          })
        }
      }
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.expand_span {
  font-size: 12px;
}
</style>
