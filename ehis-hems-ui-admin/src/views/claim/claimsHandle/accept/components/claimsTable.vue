<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    :selectable="selectable"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;"
    @selection-change="handleSelectionChange">
    <el-table-column v-if="status==='02'" align="center" key="0" type="selection" width="55"/>
    <el-table-column key="1" sortable align="center" prop="claimno" min-width="150" label="赔案号" show-overflow-tooltip/>
    <el-table-column :formatter="getCNSource" key="2" sortable align="center" min-width="100" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
    <el-table-column key="3" sortable align="center" prop="name" label="被保人姓名" min-width="120" show-overflow-tooltip/>
    <el-table-column key="4" sortable align="center" prop="idcardno" min-width="160" label="证件号码" show-overflow-tooltip/>
    <el-table-column key="5" :formatter="getCNReasonType" sortable align="center" min-width="120" prop="applicationreason" label="申请类别" show-overflow-tooltip/>
    <el-table-column key="6" sortable align="center" prop="eventdate" label="出险日期" min-width="120" show-overflow-tooltip/>
<!--    <el-table-column key="7" sortable align="center" prop="acceptdate" label="受理日期" min-width="120" show-overflow-tooltip/>-->
<!--    <el-table-column key="7" sortable align="center" min-width="120"prop="completedate" label="资料齐全时间" show-overflow-tooltip/>-->
    <el-table-column key="8" sortable align="center" min-width="140"prop="completedate" label="案件最新状态时间" show-overflow-tooltip/>
    <el-table-column key="9" sortable :formatter="formatLimitation" min-width="70" align="center" prop="limitation" label="时效" show-overflow-tooltip/>
    <el-table-column key="10" v-if="status==='02'" :formatter="formatClaimStatus" min-width="130" sortable align="center" prop="status" label="赔案状态" show-overflow-tooltip/>
    <el-table-column key="11" sortable align="center" :formatter="formatConclusion" min-width="100" prop="conclusion" label="当前结论" show-overflow-tooltip/>
    <el-table-column key="12" v-if="status==='02'" sortable align="center" prop="operator" label="操作人" show-overflow-tooltip min-width="80"/>
    <el-table-column key="13" align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='01'" size="small" type="text" @click="editHandle(scope.row,'edit')">处理</el-button>
        <el-button v-if="status==='02'" size="small" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
        <el-button v-if="status==='02'" size="small" type="text" :disabled="scope.row.status !='22' || scope.row.lastnode !='02'" @click="recovery(scope.row)">回收</el-button>
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
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: String,
  },
  dicts: ['application_category', 'apply_sourcetype', 'tasknode', 'claim_status',],
  data() {
    return {
      dictList:[],
      application_categoryOptions:[],
      tasknodeOptions:[],
      claim_statusOptions:[],
      apply_typeOptions:[],
      apply_sourcetypeOptions:[],
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
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          applySource: row.applicationsource,
          node: 'accept',
          styleFlag: 'list',
        })
      )
/*      const newpage = this.$router.resolve({
        name: 'acceptdetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');*/
      this.$router.push({
        path: '/claims-handle/com-handle',
        query: {
          data
          // claimno: row.claimno,
          // status,
          // applySource: row.applicationsource,
          // node: 'accept'
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
          node:'claim_accept'
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
              //reason = this.dict.label.application_category[reasonArray[i]]
              reason = this.selectDictLabel(this.application_categoryOptions, reasonArray[i]);
            } else {
             // reason = reason + '|' + this.dict.label.application_category[reasonArray[i]]
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
    handleSelectionChange(sel) {
      this.$emit('getSelect', sel)
    },
    selectable(row, index) {
      if (this.status === '01') {
      }
    },
    getCNSource(row, col) {
      //return this.dict.label.apply_sourcetype[row.applicationsource]
      return this.selectDictLabel(this.apply_sourcetypeOptions, row.applicationsource);
    }
  }
}
</script>
<style scoped>
  .mycell .el-checkbox__input {
    display: none;
  }
</style>
