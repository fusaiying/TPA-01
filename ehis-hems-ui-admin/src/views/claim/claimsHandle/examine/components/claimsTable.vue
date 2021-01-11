<template>
  <div>
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" prop="claimNo" :width="180" label="赔案号" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="detailHandle(scope.row,'show')">{{ scope.row.claimNo }}</el-button>
        </template>
      </el-table-column>
      <el-table-column :formatter="getApplicationsource" align="center" prop="applicationSource" label="申请来源" show-overflow-tooltip/>
      <el-table-column align="center" prop="insuredName" label="被保人姓名" show-overflow-tooltip/>
      <el-table-column align="center" prop="idcardNo" label="证件号码" show-overflow-tooltip/>
      <el-table-column :formatter="formatApplicaReason" align="center" prop="applicationcategory" label="申请类别" show-overflow-tooltip/>
      <el-table-column align="center" prop="eventDate" label="出险日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="applicationDate" label="受理日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="dataList" label="资料清单" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.materialnames" placement="top-start">
            <el-button size="small" type="text">{{scope.row.dataList}}</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="fillingsdescription" label="补材描述" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button size="small" type="text">{{ scope.row.fillingsdescription }}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="createDate" label="发起时间" show-overflow-tooltip/>
      <el-table-column align="center" prop="status" label="赔案状态" :formatter="getClaimStatus" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
          <el-button v-if="status==='02'" size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <approval-modul v-model="diaVis" :show-flag="showFlag" :row="selRow" @searchHandle="searchHandle"/>
  </div>
</template>

<script>
import approvalModul from '../components/approvalModul'
import codeMap from '../../common/mixins/codeMap'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  components: {
    approvalModul
  },
  mixins: [codeMap],
  dicts: ['apply_sourcetype', 'apply_type', 'application_category','claim_status'],
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String
  },
  data() {
    return {
      dialogClosed: false,
      showFlag: false,
      claimno: '',
      supplementaryNo: '',
      selRow: {},
      diaVis: false
    }
  },
  created() {
    console.log(this.tableData)
  },
  methods: {
    getApplicationsource(row,col) {
      return this.dict.label.apply_sourcetype[row.applicationSource]
    },
    // 处理跳转
    editHandle(row, status) {
      status === 'show' ? this.showFlag = true : false
      this.selRow = row
      this.diaVis = true
    },
    // 申请类别
    formatApplicaReason(row) {
      let reason = ''
      if (row.applicationcategory) {
        row.applicationcategory.split(',').map((item, i) => {
          this.dict.label.application_category[item] ? reason += `|${this.dict.label.application_category[item]}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    // 赔案状态
    getClaimStatus(row, col) {
      return this.dict.label.claim_status[row.status]
    },
    detailHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimNo),
          status,
          node: 'review'
        })
      )
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // claimno: row.claimNo,
      //     // status,
      //     // node: 'review'
      //   }
      // })
      const newpage = this.$router.resolve({
        name: 'casedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
    },
    // 申请来源
    getapplicationsource(row,col) {
      return this.dict.label.apply_sourcetype[row.status]
    },
    searchHandle() {
      this.$emit('searchHandle')
    }
  }
}
</script>
<style>
  .el-tooltip__popper{
    max-width: 800px;
  }
</style>
