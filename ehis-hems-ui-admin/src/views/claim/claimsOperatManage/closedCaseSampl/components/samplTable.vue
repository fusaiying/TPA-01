<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column min-width="140px" align="center" prop="claimno" label="赔案号" key="1">
      <template slot-scope="scope">
        <el-button v-if="status==='03'" size="mini" type="text" @click="editHandle1(scope.row,'show')">{{ scope.row.claimno }}</el-button>
        <span v-else>{{ scope.row.claimno }}</span>
      </template>
    </el-table-column>
<!--    <el-table-column width="160px" align="center" prop="claimno" label="赔案号" show-overflow-tooltip/>-->
    <el-table-column :formatter="formatApplySourcetype" align="center" prop="applicationsource" key="2" label="申请来源" show-overflow-tooltip/>
    <el-table-column align="center" prop="name" label="被保人姓名" key="3" show-overflow-tooltip/>
    <el-table-column align="center" prop="idcardno" label="身份证" key="4" show-overflow-tooltip/>
    <el-table-column :formatter="formatApplicationreason" align="center" prop="applicationReason" key="5" label="申请类别" show-overflow-tooltip/>
    <el-table-column align="center" prop="eventdate" label="出险日期" key="6" show-overflow-tooltip/>
    <el-table-column align="center" prop="applicationdate" label="受理日期" key="7" show-overflow-tooltip/>
    <el-table-column align="center"  prop="operatedate" label="结案日期" key="8" show-overflow-tooltip/>
    <el-table-column align="center"  prop="createDate" label="抽检时间" key="9" show-overflow-tooltip/>
    <el-table-column :formatter="formatConclusion" align="center" prop="conclusion" key="10" label="赔付结论" show-overflow-tooltip/>
    <el-table-column align="center" prop="payableamount" label="赔付金额" key="11" show-overflow-tooltip/>
    <el-table-column v-if="status==='03'" align="center" prop="checkConclusion" label="抽检结论" key="12" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{scope.row.checkConclusion === '01' ? '通过-' + (scope.row.explanation ? scope.row.explanation : '') : '不通过' + '-' + (scope.row.explanation ? scope.row.explanation : '')}}</span>
      </template>
    </el-table-column>
    <el-table-column v-if="status==='03'" :formatter="formatexaminereject" align="center" prop="rejectreason" key="13" label="不通过类型" show-overflow-tooltip/>
    <el-table-column v-if="status==='01'" align="center" key="14" label="操作">
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="editHandle(scope.row,'show')">处理</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  dicts: ['apply_sourcetype', 'applicationreason', 'conclusion', 'application_category', 'examine_reject'],
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
    // 申请来源
      formatApplySourcetype(row) {
          return this.dict.label.apply_sourcetype[row.applicationsource]
      },
      // 申请原因
      formatApplicationreason(row) {
          // return this.dict.label.applicationreason[row.applicationReason]
        let reason = ''
        if (row.applicationReason) {
          row.applicationReason.split(',').map((item, i) => {
            this.dict.label.application_category[item] ? reason += `|${this.dict.label.application_category[item]}` : reason = ''
          })
        }
        return reason.slice(1)
      },
      // 赔付结论
      formatConclusion(row) {
          return this.dict.label.conclusion[row.conclusion]
      },
    //不通过类型
    formatexaminereject(row) {
      return this.dict.label.examine_reject[row.rejectreason]
    },
    // 处理跳转
    editHandle(row, status) {
      // this.$router.push({
      //     path :'/job-manage/com-handle',
      //     query:{
      //         claimno: row.claimno,
      //         status,
      //         node: 'spotCheck',
      //         conclusionType:'01',
      //         detailno:row.detailno
      //     }
      // })
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          node: 'spotCheck',
          conclusionType:'01',
          detailno:row.detailno,
          spotCheckFlag: '001',
          sopckDisabled: false
        })
      )
      const newpage = this.$router.resolve({
        name: 'checkdetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path :'/claims-handle/com-handle',
      //   query:{
      //     data
      //     // claimno: row.claimno,
      //     // status,
      //     // node: 'spotCheck'
      //   }
      // })
    },
      editHandle1(row, status) {
          // this.$router.push({
          //     path :'/job-manage/com-handle',
          //     query:{
          //         claimno: row.claimno,
          //         status,
          //         node: 'spotCheck',
          //         conclusionType:'01',
          //         detailno:row.detailno
          //     }
          // })
          let data = encodeURI(
              JSON.stringify({
                  claimno: encrypt(row.claimno),
                  status,
                  node: 'spotCheck',
                  conclusionType:'01',
                  detailno:row.detailno,
                  spotCheckFlag: '001',
                  sopckDisabled: true
              })
          )
          const newpage = this.$router.resolve({
            name: 'casedetail',
            params:{},
            query:{ data }
          })
          window.open(newpage.href, '_blank');
          // this.$router.push({
          //     path :'/claims-handle/com-handle',
          //     query:{
          //         data
          //         // claimno: row.claimno,
          //         // status,
          //         // node: 'spotCheck'
          //     }
          // })
      }
  }
}
</script>
