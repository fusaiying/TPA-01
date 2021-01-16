<template>
  <el-table
      size="small"
      highlight-current-row
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" prop="claimno" min-width="160" key="1" label="赔案号"></el-table-column>
      <el-table-column align="center" prop="applicationsource"  key="2" label="申请来源" show-overflow-tooltip>
         <template slot-scope="scope">
          <span>{{dict.label.apply_sourcetype[scope.row.applicationsource]}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="name" key="3" label="被保人姓名" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="idcardno" key="4" label="证件号码" show-overflow-tooltip></el-table-column>
      <el-table-column sortable align="center" min-width="100" key="5" :formatter="formatApplicaReason" prop="applicationreason" label="申请类别" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="acceptdate" label="受理日期" key="6" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="conclusion" label="当前结论" key="7" v-if="status==='01'" :formatter="formatConclusion"  show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="explanation" label="回退描述" key="8" v-if="status==='01'" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="counts" label="影像数量" key="9" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="操作" v-if="status=='02'" key="10">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" v-if="status=='01'" key="11">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="editHandle(scope.row,'handel')">处理</el-button>
        </template>
      </el-table-column>
  </el-table>
</template>

<script>
const baseAddress = process.env.BASE_API
/*import { getToken } from '@/utils/auth'*/
import {encrypt} from "@/utils/rsaEncrypt"
/*import jwt from 'jsonwebtoken'*/
import Cookies from 'js-cookie'
export default {
  props: {
    tableData: {
      type: Array,
        default:function () {
            return []
        }
    },
    status: String
  },
  dicts: ['apply_type','apply_sourcetype','application_category','statusdesc','tasknode'],
  data() {
    return {
    }
  },
  methods: {
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          idNo: encrypt(row.idcardno),
          name: row.name,
          num: row.counts,
          status,
          node: 'firstTrial'
        })
      )
      // 页面跳转打开空白页面
      const newpage = this.$router.resolve({
        name: 'imageshow',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
    },
    formatApplicaReason(row) {
      let reason = ''
      if (row.applicationreason) {
        row.applicationreason.split(',').map((item, i) => {
          this.dict.label.application_category[item] ? reason += `、${this.dict.label.application_category[item]}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    formatConclusion(row) {
      return this.dict.label.tasknode[row.tasknode] + '-' + row.conclusion
    },
    // 扫描
    toClient() {
      const jwt = require('jsonwebtoken')
      const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
      const name = str.user_name
      window.open(`${process.env.BASE_API}/claim-easyscan5/EasyScanContainer?usercode=${name}&receivedmode=4`)
    },
  }
};
</script>
<style>
.el-tooltip__popper{
  max-width: 50%;
}
</style>
