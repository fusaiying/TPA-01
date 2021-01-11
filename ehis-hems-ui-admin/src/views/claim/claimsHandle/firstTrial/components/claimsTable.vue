<template>
  <el-table
      size="small"
      highlight-current-row
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column sortable align="center" prop="claimno" min-width="150" label="赔案号" show-overflow-tooltip></el-table-column>
      <!-- <el-table-column sortable align="center" prop="externalclaimno" min-width="140" label="蚂蚁赔案号" show-overflow-tooltip></el-table-column> -->
     <!-- <el-table-column sortable align="center" min-width="100" prop="applicationsource" label="申请来源" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{dict.label.apply_sourcetype[scope.row.applicationsource]}}</span>
        </template>
      </el-table-column>-->
    <el-table-column :formatter="getCNSource" key="2" sortable align="center" min-width="100" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
      <el-table-column sortable align="center" min-width="105" prop="name" label="被保人姓名" show-overflow-tooltip></el-table-column>
      <el-table-column sortable align="center" min-width="155" prop="idcardno" label="证件号码" show-overflow-tooltip/>
      <el-table-column sortable align="center" min-width="95" :formatter="formatApplicaReason" prop="applicationreason" label="申请类别" show-overflow-tooltip></el-table-column>
      <el-table-column sortable align="center" min-width="140" prop="eventdate" label="出险日期" show-overflow-tooltip></el-table-column>
<!--      <el-table-column sortable align="center" min-width="130" prop="acceptdate" label="受理日期" show-overflow-tooltip></el-table-column>-->
<!--      <el-table-column sortable align="center" min-width="130" prop="completedate" label="资料齐全时间" show-overflow-tooltip></el-table-column>-->
      <el-table-column sortable align="center" min-width="75" :formatter="formatLimitation" prop="limitation" label="时效" show-overflow-tooltip></el-table-column>
      <el-table-column sortable align="center" min-width="95" v-if="status==='02'" :formatter="formatClaimStatus" prop="status" label="赔案状态" show-overflow-tooltip></el-table-column>
      <el-table-column v-if="status!=='03'" sortable align="center" min-width="100" :formatter="formatConclusion" prop="conclusion" label="当前结论" show-overflow-tooltip></el-table-column>
      <el-table-column v-if="status==='03'" sortable align="center" min-width="100" prop="conclusion" label="当前结论">
        <template slot-scope="scope">
          <span>初审-{{scope.row.conclusion}}</span>
        </template>
      </el-table-column>
    <el-table-column sortable align="center" min-width="140"prop="completedate" label="案件最新状态时间" show-overflow-tooltip/>
    <el-table-column sortable v-if="status==='02'" align="center" prop="operator" label="操作人" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" min-width="94" label="操作" fixed="right">
        <template slot-scope="scope">
          <el-button size="small" v-if="status!=='01'" type="text" @click="editHandle(scope.row,'show')">查看</el-button>
          <el-button size="small" v-if="status==='01'" type="text" @click="editHandle(scope.row,'handle')">处理</el-button>
          <el-button size="small" v-if="status==='02'" type="text" :disabled="scope.row.status !='09' || scope.row.lastnode !='08'" @click="recovery(scope.row)">回收</el-button>
        </template>
      </el-table-column>
  </el-table>
</template>

<script>
import { recovery } from "@/api/claim/handleDeal"
import {encrypt} from "@/utils/rsaEncrypt"
import codeMap from "../../common/mixins/codeMap"
let dictss= [{dictType:'application_category'},{dictType:'apply_sourcetype'},{dictType:'tasknode'},{dictType:'claim_status'},{dictType:'apply_type'}];
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
  dicts: ['application_category', 'apply_sourcetype', 'tasknode', 'claim_status'],
  data() {
    return {
      dictList:[],
      application_categoryOptions:[],
      tasknodeOptions:[],
      claim_statusOptions:[],
      apply_typeOptions:[],
      apply_sourcetypeOptions:[]
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
    this.apply_typeOptions=this.dictList.find(item=>{
      return item.dictType=='apply_type'
    }).dictDate

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
          node:'claim_preliminary_examination'
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
    //处理跳转
    editHandle(row, status) {
      let data = encodeURI(
        JSON.stringify({
          claimno: encrypt(row.claimno),
          status,
          node: 'firstTrial'
        })
      )
      this.$router.push({
        path :'/claims-handle/newdetail1',
        query:{
          data
        }
        // {
        //   claimno: row.claimno,
        //   status,
        //   node: 'firstTrial'
        // }
      })
      // 页面跳转打开空白页面
      // const newpage = this.$router.resolve({
      //   name: 'detail',
      //   params:{},
      //   query:{ data }
      // })
      // window.open(newpage.href, '_blank');
      // 新版初审页面
/*      const newpage = this.$router.resolve({
        name: 'newdetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');*/
    },
    getCNSource(row, col) {
      //return this.dict.label.apply_sourcetype[row.applicationsource]
      return this.selectDictLabel(this.apply_sourcetypeOptions, row.applicationsource);
    }
  }
}
</script>
