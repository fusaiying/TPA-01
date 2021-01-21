<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column sortable align="center" prop="claimno" min-width="160" label="赔案号" show-overflow-tooltip/>
    <!-- <el-table-column sortable align="center" prop="externalclaimno" width="140" label="蚂蚁赔案号" show-overflow-tooltip/> -->
    <el-table-column sortable :formatter="getCNSource" align="center"  min-width="100" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="name" min-width="120" label="被保人姓名" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="idcardno" min-width="160" label="证件号码" show-overflow-tooltip/>
    <el-table-column sortable :formatter="getCNReasonType" align="center" min-width="120" prop="applicationreason" label="申请类别" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="eventdate" min-width="120" label="出险日期" show-overflow-tooltip/>
<!--    <el-table-column sortable align="center" prop="acceptdate" min-width="120" label="受理日期" show-overflow-tooltip/>-->
    <el-table-column sortable align="center" min-width="140"prop="completedate" label="案件最新状态时间" show-overflow-tooltip/>
    <el-table-column sortable align="center" :formatter="formatLimitation" min-width="70" prop="limitation" label="时效" show-overflow-tooltip/>
    <el-table-column sortable align="center" :formatter="formatConclusion" prop="conclusion" min-width="120" label="当前结论" show-overflow-tooltip/>
    <el-table-column sortable v-if="status==='02'" :formatter="formatClaimStatus" sortable align="center" min-width="100" prop="status" label="赔案状态" show-overflow-tooltip/>
    <el-table-column sortable align="center" prop="billcount" label="发票数量" min-width="100" show-overflow-tooltip/>
    <el-table-column v-if="status==='02'" sortable align="center" prop="operator" label="操作人" show-overflow-tooltip/>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='02'" size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
        <el-button v-if="status==='02'" size="small" type="text" :disabled="scope.row.status != '07' || scope.row.lastnode !='06'" @click="recovery(scope.row)">回收</el-button>
        <el-button v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'edit')">处理</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { recovery } from "@/api/claim/handleDeal";
import codeMap from "../../common/mixins/codeMap"
import {encrypt} from "@/utils/rsaEncrypt"
let dictss= [{dictType:'application_category'},{dictType:'apply_sourcetype'},{dictType:'tasknode'},{dictType:'claim_status'}];
export default {
  mixins: [codeMap],
  dicts:['application_category','apply_sourcetype','tasknode','claim_status'],
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
      dictList:[],
      application_categoryOptions: [],
      apply_sourcetypeOptions: [],
      tasknodeOptions: [],
      claim_statusOptions: [],
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response =>{
      this.dictList=response.data
    })
    this.application_categoryOptions=this.dictList.find(item=>{
      return item.dictType=='application_category'
    }).dictDate
    this.apply_sourcetypeOptions=this.dictList.find(item=>{
      return item.dictType=='apply_sourcetype'
    }).dictDate
    this.tasknodeOptions=this.dictList.find(item=>{
      return item.dictType=='tasknode'
    }).dictDate
    this.claim_statusOptions=this.dictList.find(item=>{
      return item.dictType=='claim_status'
    }).dictDate

  },
  methods: {
    // 处理跳转
    editHandle(row, status) {
    //   let data = encodeURI(
    //     JSON.stringify({
    //       claimno: encrypt(row.claimno),
    //       status,
    //       applySource: row.applicationsource,
    //       insuredName: row.name,
    //       node: 'input'
    //     })
    //   )
      // const newpage = this.$router.resolve({
      //   name: 'inputdetail',
      //   params:{},
      //   query:{ data }
      // })
      // window.open(newpage.href, '_blank');
      let data = encodeURI(
        JSON.stringify({
          node: 'input',
          claimNo: encrypt(row.claimno),
          status,
          // applySource: row.applicationsource,
          insuredName: row.name
        })
      )
/*      const newpage = this.$router.resolve({
        name: 'newinputdetail',
        params:{},
        query:{
          data
        }
      })
      window.open(newpage.href, '_blank');*/
      this.$router.push({
        path: '/claims-handle/handle',
        query:{
          data
        }
      })
    },
    recovery(row) {
      this.$confirm(`是否确定回收？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const params = {
          claimno:row.claimno,
          node:'claim_input'
        }
        recovery(params).then(response => {
          if(response.status === '1'){
            //刷新列表
            this.$message({message: '回收成功！', type: 'success'});
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
    getCNReasonType(row, col) {
      if (row.applicationreason != null) {
        if (row.applicationreason.length > 2) {
          const reasonArray = row.applicationreason.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              reason = this.selectDictLabel(this.application_categoryOptions, reasonArray[i]);
            } else {
              reason = reason + '|' + this.selectDictLabel(this.application_categoryOptions, reasonArray[i]);
            }
          }
          return reason
        } else {
          //return this.dict.label.application_category[row.applicationreason]
          return this.selectDictLabel(this.application_categoryOptions, row.applicationreason);
        }
      }
    },
    getCNSource(row, col) {
      //return this.dict.label.apply_sourcetype[row.applicationsource]
      return this.selectDictLabel(this.apply_sourcetypeOptions, row.applicationsource);
    }
  }
}
</script>
