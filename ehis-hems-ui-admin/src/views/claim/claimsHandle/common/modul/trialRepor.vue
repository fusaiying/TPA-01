<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="报案信息"
    width="80%"
    @open="registerInfo">
    <el-table
      v-loading="loading"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="props.row.policylist"
            size="small"
            highlight-current-row
            tooltip-effect="dark">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table
                ref="table"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                :data="props.row.risklist"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">
                <el-table-column align="center" prop="riskname" label="险种名称" show-overflow-tooltip/>
                <el-table-column align="center" prop="dutyname" label="责任名称" show-overflow-tooltip/>
                <el-table-column align="center" prop="amount" label="责任保额" show-overflow-tooltip/>
                <el-table-column align="center" prop="surplusamount" label="剩余保额" show-overflow-tooltip/>
                <el-table-column align="center" prop="deductible" label="责任免赔额" show-overflow-tooltip/>
                <el-table-column align="center" prop="surplusdeductible" label="剩余免赔额" show-overflow-tooltip/>
                <el-table-column label="给付责任编码" width="130" align="center">
                  <template slot-scope="scope">
                    <P v-for="item in scope.row.getdutylist" :key="item.getDutyCode">{{item.getDutyCode}}</P>
                  </template>
                </el-table-column>
                <el-table-column label="给付责任名称" width="160" align="center">
                  <template slot-scope="scope">
                    <P v-for="item in scope.row.getdutylist" :key="item.getDutyCode">{{item.getDutyName}}</P>
                  </template>
                </el-table-column>
                <el-table-column label="给付责任类型" width="130" align="center">
                  <template slot-scope="scope">
                    <P v-for="item in scope.row.getdutylist" :key="item.getDutyCode">{{ getgetDutyKind(item.getDutyKind)?getgetDutyKind(item.getDutyKind):item.getDutyKind}}</P>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="contno" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskinfo" label="险种名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="effectivedate" label="有效日期" width="160" show-overflow-tooltip/>
          <el-table-column align="center" prop="status" label="保单状态" show-overflow-tooltip>
            <template slot-scope="scope">
              {{getPolicy_status(scope.row.status)}}
            </template>
          </el-table-column>
          <el-table-column align="center" prop="special" label="特约信息" show-overflow-tooltip/>
          <el-table-column align="center" prop="isrecuperation" label="是否复效" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.isrecuperation==='是'">
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click">
                  <el-table
                    :data="[scope.row]"
                    :header-cell-style="{color:'#555', fontWeight: '400'}"
                    size="mini">
                    <el-table-column property="recuperationdate" label="复效日期"/>
                    <el-table-column property="startdate" label="失效日期"/>
                    <el-table-column property="recuperationnotification" label="复效健康告知"/>
                  </el-table>
                  <el-button slot="reference" type="text" size="mini">{{ scope.row.isrecuperation }}</el-button>
                </el-popover>
              </span>
              <span v-else>{{scope.row.isrecuperation}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="isrenewal" label="是否续保" show-overflow-tooltip/>
          <el-table-column align="center" prop="socialinsurance" label="社保投保" show-overflow-tooltip/>
          <el-table-column align="center" prop="ismatch" label="是否匹配" show-overflow-tooltip>
            <template slot-scope="scope">
              <span style="font-size: 14px;">
                <i v-if="scope.row.ismatch==='N'" style="color: #F56C6C;" class="el-icon-circle-close"></i>
                <i v-if="scope.row.ismatch==='Y'" style="color: #409EFF;" class="el-icon-circle-check"></i>
              </span>
            </template>
          </el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column property="registerno" width="116" label="报案号" sortable align="center"/>
      <el-table-column property="category" :formatter="formatCategory" label="申请类别" align="center"/>
      <el-table-column property="claimamount" label="门诊索赔金额" align="center"/>
      <el-table-column property="hospitalmount" label="住院索赔金额" align="center"/>
      <el-table-column property="currentstate" label="事故者现状" align="center">
        <template slot-scope="scope">
          {{getCurrent_state(scope.row.currentstate)}}
        </template>
      </el-table-column>
      <el-table-column property="name" label="被保人" align="center"/>
      <el-table-column property="idno" label="证件号码" width="150" align="center"/>
      <el-table-column property="registrant" label="报案人" align="center"/>
      <el-table-column property="registerdate" label="报案日期" align="center"/>
    </el-table>
  </el-dialog>
</template>

<script>
import {registerInfo} from '@/api/claim/handleDeal'
import codeMap from "../../common/mixins/codeMap"
export default {
  mixins: [codeMap],
 // dicts: ['apply_type', 'current_state', 'policy_status', 'get_duty_kind'],
  props: {
    dictList:Array,
    value: Boolean,
    node: String,
    claimno: String
  },
  data() {
    return {
      loading: false,
      searchForm: {},
      tableData: [
        { policyNo: 'ERF23423645' }
      ],
      subTable: [{ policyNo: 'ERF23423645', insuredName: '小米', renewal: '是' }],
      policyCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      },

      apply_typeOptions: [],
      current_stateOptions: [],
      policy_statusOptions: [],
      get_duty_kindOptions: []
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
      this.apply_typeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_type'
      }).dictDate
      this.current_stateOptions = this.dictList.find(item => {
        return item.dictType == 'current_state'
      }).dictDate
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
      this.get_duty_kindOptions = this.dictList.find(item => {
        return item.dictType == 'get_duty_kind'
      }).dictDate
    }
  },
  methods: {
    getgetDutyKind(data){
       return this.selectDictLabel(this.get_duty_kindOptions, data)
    },
    getPolicy_status(data){
      return this.selectDictLabel(this.policy_statusOptions, data)
    },

    getCurrent_state(data){
      return this.selectDictLabel(this.current_stateOptions, data)
    },
    dialogClosed() {
      this.$emit('input', false)
    },
    formatCategory(row, column) {
      let reason = ''
      if (row.category) {
        row.category.split(',').map((item, i) => {
          /*this.dict.label.apply_type[item] ? reason += `｜${this.dict.label.apply_type[item]}` : reason = ''*/
          this.selectDictLabel(this.apply_typeOptions,item)? reason += `｜${this.selectDictLabel(this.apply_typeOptions,item)}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    registerInfo(){
      this.loading = true
      registerInfo(this.claimno).then(response => {
        if (response.status === '1') {
          this.tableData = response.data
        } else {
          this.$message({ message: '查询报案信息失败！', type: 'error' })
        }
      }).finally(() => {
        this.loading = false
      })
    },
    relationHandle() {}
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
