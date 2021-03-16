<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    @expand-change="getMinData"
    style="width: 100%;">
    <el-table-column type="selection" align="center" content="全选"/>
    <el-table-column prop="workOrderNo" width="140" label="工单号" align="center"/>
    <el-table-column prop="channelCode" width="100" label="受理渠道" align="center">
      <template slot-scope="scope" v-if="scope.row.channelCode">
        <span>{{ selectDictLabel(cs_channel, scope.row.channelCode) }}</span>
      </template>
    </el-table-column>

    <el-table-column prop="itemCode" width="100" label="服务项目" align="center">
      <template slot-scope="scope" v-if="scope.row.serviceItem">
        <span>{{ selectDictLabel(cs_service_item, scope.row.serviceItem) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="policyNo" width="140" label="保单号" align="center"/>
    <el-table-column prop="policyItemNo" width="140" label="分单号" align="center"/>
    <el-table-column prop="riskCode" label="险种代码" align="center"/>
    <el-table-column prop="insuredName" label="被保人" align="center"/>
    <el-table-column prop="holderName" label="投保人" align="center"/>
    <el-table-column align="acceptTime" prop="acceptTime" min-width="120" label="受理时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ scope.row.acceptTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="updateTime" min-width="120" label="修改时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{ scope.row.updateTime | changeDate}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="acceptUserId" label="受理人" align="center"/>
    <el-table-column prop="updateBy" label="处理人" align="center"/>
    <el-table-column prop="vipFlag" label="VIP标识" align="center">
      <template slot-scope="scope" v-if="scope.row.priorityLevel">
        <span>{{ selectDictLabel(cs_vip_flag, scope.row.priorityLevel) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="priorityLevel" label="优先级" align="center">
      <template slot-scope="scope" v-if="scope.row.priorityLevel">
        <span>{{ selectDictLabel(cs_priority, scope.row.priorityLevel) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="organCode" width="140" label="出单机构" align="center">
      <template slot-scope="scope" v-if="scope.row.organCode">
        <span>{{ selectDictLabel(cs_organization, scope.row.organCode) }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="操作" min-width="140" fixed="right">
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="obtainButton(scope.row)">获取
        </el-button>
        <el-button size="small" type="text" @click="modifyButton(scope.row)">修改
        </el-button>
        <el-button size="small" type="text" @click="cancleBytton(scope.row)">取消
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {getMinData} from '@/api/claim/presentingReview'
import {encrypt} from "@/utils/rsaEncrypt"
import moment from "moment";

let dictss = [
  {dictType: 'cs_channel'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_sex'},
  {dictType: 'cs_communication_language'},
  {dictType: 'cs_identity'},
  {dictType: 'cs_whether_flag'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_relation'},
  {dictType: 'cs_feedback_type'},
  {dictType: 'cs_vip_flag'},
  {dictType: 'cs_direct_settlement'},
  {dictType: 'cs_consultation_type'},
  {dictType: 'cs_service_item'},
]

export default {
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
    }
  },
  props: {
    tableData: {
      type: Array,
      default: function () {
        return []
      }
    },
    status: String,
  },
  data() {
    return {
      loading: true,
      cs_channel:[],//受理渠道
      cs_reservation_item:[],//服务项目
      cs_sex:[],//性别
      cs_priority:[],//优先级
      cs_communication_language:[],//语言
      cs_organization:[],//机构
      cs_handle_state:[],// 状态：
      dictList: [],
      cs_identity: [],
      cs_whether_flag: [],
      cs_relation: [],
      cs_feedback_type: [],
      cs_vip_flag: [],
      cs_direct_settlement: [],
      cs_consultation_type: [],
      cs_service_item: [],
    }
  },
  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.cs_channel = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_sex = this.dictList.find(item => {
      return item.dictType === 'cs_sex'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_communication_language = this.dictList.find(item => {
      return item.dictType === 'cs_communication_language'
    }).dictDate
    this.cs_identity = this.dictList.find(item => {
      return item.dictType === 'cs_identity'
    }).dictDate
    this.cs_whether_flag = this.dictList.find(item => {
      return item.dictType === 'cs_whether_flag'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_relation = this.dictList.find(item => {
      return item.dictType === 'cs_relation'
    }).dictDate
    this.cs_feedback_type = this.dictList.find(item => {
      return item.dictType === 'cs_feedback_type'
    }).dictDate
    this.cs_vip_flag = this.dictList.find(item => {
      return item.dictType === 'cs_vip_flag'
    }).dictDate
    this.cs_direct_settlement = this.dictList.find(item => {
      return item.dictType === 'cs_direct_settlement'
    }).dictDate
    this.cs_consultation_type = this.dictList.find(item => {
      return item.dictType === 'cs_consultation_type'
    }).dictDate
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
  },
  methods: {
    //获取
    obtainButton(row) {

    },
    //修改
    modifyButton(row) {
      let url='/customService/modify';
      if(row.businessType=='02'){
        url='/customService/complaint/modify';
      }else if(row.businessType=='03'){
        url='/customService/reservation/modify';
      }
      this.$router.push({
        path: url,
        query:{
          workOrderNo:row.workOrderNo,
          policyNo:row.policyNo,
          policyItemNo:row.policyItemNo,
          status:row.status,
          businessType:row.businessType
        }
      })
      // let data = encodeURI(
      //   JSON.stringify({
      //     batchno: row.batchno, //批次号
      //     status,//新增or查看
      //     claimtype: row.claimtype//理赔类型
      //   })
      // )
      // this.$router.push({
      //   path: '/claims-handle/presenting-edit',
      //   query: {
      //     data
      //   }
      // })
    },
    cancleBytton(row) {
      let url='/customService/cancle';
      if(row.businessType=='02'){
        url='/customService/complaint/cancle';
      }else if(row.businessType=='03'){
        url='/customService/reservation/cancle';
      }
      this.$router.push({
        path: url,
        query:{
          workOrderNo:row.workOrderNo,
          policyNo:row.policyNo,
          policyItemNo:row.policyItemNo,
          status:row.status
        }
      })
    },


    getMinData(row, expandedRows) {
      this.loading = true
      //判断只有展开是做请求
      if (expandedRows.length > 0) {
        getMinData(row.batchno).then(res => {
          this.tableData.forEach((temp, index) => {
            if (temp.batchno === row.batchno) {
              this.tableData[index].minData = res.rows
            }
          })
          this.loading = false
        })
      }
    }
  }
}
</script>
<style scoped>

.el-table /deep/ .el-table__expanded-cell {
  padding: 10px;
}
</style>

