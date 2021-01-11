<template>
  <el-table
    size="small"
    highlight-current-row
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    tooltip-effect="dark"
    style="width: 100%;">
   <el-table-column sortable align="center" min-width="160" prop="claimno" label="赔案号" show-overflow-tooltip></el-table-column>
   <!-- <el-table-column sortable align="center" prop="externalclaimno" min-width="140" label="蚂蚁赔案号" show-overflow-tooltip></el-table-column> -->
   <el-table-column sortable align="center" min-width="100" prop="applicationsource" label="申请来源" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{dict.label.apply_sourcetype[scope.row.applicationsource]}}</span>
      </template>
    </el-table-column>
   <el-table-column sortable align="center" min-width="110" prop="name" label="被保人姓名" show-overflow-tooltip></el-table-column>
   <el-table-column sortable align="center" min-width="100" prop="idcardno" label="证件号码" show-overflow-tooltip></el-table-column>
   <el-table-column sortable align="center" min-width="100" :formatter="formatApplicaReason" prop="applicationreason" label="申请类别" show-overflow-tooltip></el-table-column>
   <el-table-column sortable align="center" min-width="100" prop="eventdate" label="出险日期" show-overflow-tooltip></el-table-column>
<!--   <el-table-column sortable align="center" min-width="100" prop="acceptdate" label="受理日期" show-overflow-tooltip></el-table-column>-->
   <el-table-column sortable align="center" min-width="100" :formatter="formatLimitation" prop="limitation" label="时效" show-overflow-tooltip/>
   <el-table-column sortable v-if="status==='02'" align="center" min-width="100" :formatter="formatClaimStatus" prop="status" label="赔案状态" show-overflow-tooltip></el-table-column>
    <el-table-column v-if="status!=='03'" sortable align="center" min-width="100" :formatter="formatConclusion" prop="conclusion" label="当前结论" show-overflow-tooltip></el-table-column>
    <el-table-column v-if="status==='03'" sortable align="center" min-width="100" prop="conclusion" label="当前结论">
      <template slot-scope="scope">
        <span>抽检-{{scope.row.conclusion}}</span>
      </template>
    </el-table-column>
   <el-table-column sortable align="center" min-width="100" prop="payamount" label="赔付金额" show-overflow-tooltip></el-table-column>
    <el-table-column sortable align="center" min-width="140"prop="completedate" label="案件最新状态时间" show-overflow-tooltip/>
    <el-table-column align="center" min-width="94" label="操作" fixed="right">
      <template slot-scope="scope">
        <el-button size="small" v-if="status!=='01'" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
        <el-button size="small" v-if="status==='01'" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
        <el-button size="small" v-if="status==='02'" :disabled="scope.row.status !='15' || scope.row.lastnode !='14'" type="text" @click="recovery(scope.row)">回收</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { recovery } from "@/api/claim/handleDeal"
import codeMap from "../../common/mixins/codeMap"
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  mixins: [codeMap],
  props: {
    tableData: {
      type: Array,
      default:function () {
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
    recovery(row){
      this.$confirm(`是否确定回收？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const params = {
          claimno:row.claimno,
          node:'spot_check'
        }
        recovery(params).then(response => {
          if(response.status === '1'){
            //刷新列表
            this.$emit('init-data')
          }else{
            this.$message({message: '案件回收错误！', type: 'error'});
          }
        }).catch(error => {
          this.$message({message: '案件回收异常！', type: 'error'});
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    //处理跳转
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          node: 'spotCheck'
        })
      )
      // this.$router.push({
      //   path :'/claims-handle/com-handle',
      //   query:{
      //     data
      //     // claimno: row.claimno,
      //     // status,
      //     // node: 'spotCheck'
      //   }
      // })
        const newpage = this.$router.resolve({
          name: 'sportcheckdetail',
          params:{},
          query:{ data }
        })
        window.open(newpage.href, '_blank');
    }
  }
}
</script>
