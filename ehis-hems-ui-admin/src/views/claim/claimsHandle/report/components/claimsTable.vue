<template>
  <div>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column sortable align="center" min-width="140" prop="register" label="报案号" show-overflow-tooltip/>
      <el-table-column sortable align="center" prop="registrant" label="报案人姓名" min-width="110" show-overflow-tooltip/>
      <el-table-column sortable align="center" prop="insuredName" label="被保人姓名" min-width="110" show-overflow-tooltip/>
      <el-table-column sortable align="center" prop="idNo" min-width="160" label="证件号码" show-overflow-tooltip/>
      <el-table-column :formatter="getCNReasonType" min-width="120" sortable align="center" prop="applicationReason" label="申请类别" show-overflow-tooltip/>
      <el-table-column :formatter="formatTime" sortable align="center" min-width="100" prop="eventDate" label="出险日期" show-overflow-tooltip/>
      <el-table-column sortable align="center" prop="registerDate" min-width="100" label="报案日期" show-overflow-tooltip/>
      <el-table-column sortable align="center" prop="creator" label="操作人" show-overflow-tooltip/>
      <el-table-column :formatter="getCNChannelSource" sortable min-width="120" align="center" prop="source" label="报案来源" show-overflow-tooltip/>
      <el-table-column align="center" label="操作" min-width="80">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="editHandle(scope.row, 'edit')">处理</el-button>
          <!-- <el-button v-else size="mini" type="text" @click="editHandle(scope.row, 'show')">详情</el-button> -->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {encrypt} from "@/utils/rsaEncrypt"
import moment from 'moment'
export default {
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    total: Number
  },
  dicts: ['application_category', 'channel_source'],
  data() {
    return {
    }
  },
  methods: {
    // 处理跳转
    editHandle(row) {
      let data = encodeURI(
        JSON.stringify({
          registerNo: encrypt(row.register),
          registerSource: row.source,
          registerStatus: row.status,
          status: 'edit',
          node: 'report',
          styleFlag: 'list'
        })
      )
      const newpage = this.$router.resolve({
        name: 'reportdetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/report-handle',
      //   query: {
      //     data
      //     // registerNo: row.register,
      //     // registerSource: row.source,
      //     // registerStatus: row.status,
      //     // status: 'edit',
      //     // node: 'report'
      //   }
      // })
    },
    getCNReasonType(row, col) {
      if (row.applicationReason != null) {
        if (row.applicationReason.length > 2) {
          const reasonArray = row.applicationReason.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              reason = this.dict.label.application_category[reasonArray[i]]
            } else {
              reason = reason + '|' + this.dict.label.application_category[reasonArray[i]]
            }
          }
          return reason
        } else {
          return this.dict.label.application_category[row.applicationReason]
        }
      }
    },
    getCNChannelSource(row, col) {
      return this.dict.label.channel_source[row.source]
    },
    formatTime(row) {
      if(row.eventDate !== null && row.eventDate!== '') {
        return moment(row.eventDate).format("YYYY-MM-DD")
      }
    }
  }
}
</script>
