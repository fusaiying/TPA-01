<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column sortable align="center" prop="claimno" width="160" label="赔案号" show-overflow-tooltip/>
    <el-table-column :formatter="getCNSource" sortable align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="name" label="被保人姓名" width="120" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="idcardno" label="证件号码" width="170"></el-table-column>
    <el-table-column :formatter="getCNReasonType" sortable align="center" prop="applicationreason" label="申请类别" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="acceptdate" label="受理日期" show-overflow-tooltip/>
    <el-table-column :formatter="formatLimitation" sortable align="center" prop="limitation" label="时效" show-overflow-tooltip/>
    <el-table-column v-if="status==='02'" :formatter="formatClaimStatus" sortable align="center" prop="status" label="赔案状态" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="counts" label="影像数量" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button size="small" v-if="status==='01'" type="text" @click="editHandle(scope.row,status)">影像分类</el-button>
        <el-button size="small" v-if="status==='02'" type="text" @click="showimage(scope.row,status)">影像查看</el-button>
        <el-button v-if="status==='02'" size="small" type="text" :disabled="scope.row.status !='05' || scope.row.lastnode !='04'" @click="recovery(scope.row)">回收</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import codeMap from '../../common/mixins/codeMap'
import { recovery } from "@/api/claim/handleDeal"
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  mixins: [codeMap],
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String
  },
  dicts: ['application_category', 'apply_sourcetype', 'claim_status'],
  data() {
    return {
    }
  },
  methods: {
    editHandle(row, status) {
      // this.$router.push({
      //   path: '/claims-handle/classificat',
      //   query: {
      //     node: 'classificat',
      //     type: 'classify',
      //     claimNo: encrypt(row.claimno)
      //   }
      // })
      const newpage = this.$router.resolve({
        name: 'ificatdetail',
        params:{},
        query:{ 
          node: 'classificat',
          type: 'classify',
          showFlag: status,
          recoverNode: true,
          claimNo: encrypt(row.claimno) 
        }
      })
      window.open(newpage.href, '_blank');
    },
    showimage(row,status) {
      const newpage = this.$router.resolve({
        name: 'classificatViewshow',
        params:{},
        query:{ 
          node: 'classificat',
          type: 'classify',
          claimNo: encrypt(row.claimno) 
        }
      })
      window.open(newpage.href, '_blank');
    },
    recovery(row){
      this.$confirm(`是否确定回收？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const params = {
          claimno:row.claimno,
          node:'claim_scan'
        }
        recovery(params).then(response => {
          if(response.status === '1'){
            //刷新列表
            this.$message({message: '回收成功！', type: 'success'});
            this.$emit('uploadList')
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
    getCNReasonType(row, col) {
      if (row.applicationreason != null) {
        if (row.applicationreason.length > 2) {
          const reasonArray = row.applicationreason.split(',')
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
          return this.dict.label.application_category[row.applicationreason]
        }
      }
    },
    getCNSource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    }
  }
}
</script>
