<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" width="160px" prop="claimno" label="赔案号" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="detailHandle(scope.row,'show')">{{scope.row.claimno}}</el-button>
      </template>
    </el-table-column>
    <el-table-column :formatter="getSurveySource" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="insuredname" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column align="center" width="160px" prop="idcardno" label="证件号码" show-overflow-tooltip/>
    <el-table-column :formatter="formatApplicaReason" align="center" prop="applicationreason" label="申请类别" show-overflow-tooltip/>
    <el-table-column align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
    <el-table-column align="center" prop="applicationdate" label="受理日期" show-overflow-tooltip/>
    <el-table-column :formatter="getCasestatus" align="center" prop="status" label="赔案状态" show-overflow-tooltip/>
    <el-table-column align="center" prop="returnlistsize" label="退件清单" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-tooltip class="item" effect="dark" :content="queryReturnlistname(scope.row.returnlistname)" placement="top-start">
          <el-button size="small" type="text">{{scope.row.returnlistsize}}</el-button>
        </el-tooltip>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="createdate" label="发起日期" show-overflow-tooltip/>
    <el-table-column align="center" v-if="status==='03'" prop="returneddate" label="处理日期" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
        <el-button v-if="status==='03'" size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['apply_sourcetype', 'queue_claim_status', 'application_category', 'image_type'],
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
    }
  },
  methods: {
    queryReturnlistname(list) {
        let imageTypeList = this.dict.image_type
        let _list = list.split(',')
        let returnStr = ''
        _list.forEach(item => {
            imageTypeList.forEach(val => {
                if (val.value === item) {
                   returnStr += ',' + val.label
                }
            })
        })
        return returnStr.slice(1)
    },
    // 处理跳转
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          status,
          claimno: encrypt(row.claimno) || '',
          refundno: row.refundno || ''
        })
      )
      const newpage = this.$router.resolve({
        name: 'returndetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/return-edit',
      //   query: {
      //    data
      //   }
      // })
    },
    detailHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          node: 'review'
        })
      )
      const newpage = this.$router.resolve({
        name: 'casedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // claimno: row.claimNo,
      //     // status,
      //     // node: 'review'
      //   }
      // })
    },
    // 获取调查来源名称
    getSurveySource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    // 获取案件状态
    getCasestatus(row, col) {
      return this.dict.label.queue_claim_status[row.status]
    },
    // 申请类别
    formatApplicaReason(row) {
      let reason = ''
      if (row.applicationreason) {
        row.applicationreason.split(',').map((item, i) => {
          this.dict.label.application_category[item] ? reason += `|${this.dict.label.application_category[item]}` : reason = ''
        })
      }
      return reason.slice(1)
    }
  }
}
</script>
