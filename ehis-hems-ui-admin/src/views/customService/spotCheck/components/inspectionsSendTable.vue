<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;"
    @selection-change="handleSelectionChange">
    <el-table-column type="selection" align="center" content="全选"/>
    <el-table-column prop="workOrderNo" label="工单号" align="center"/>
    <!-- table中下拉属性转换 -->
    <el-table-column align="center" prop="channel" min-width="160" label="受理渠道" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(channelOptions, scope.row.channel)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="serviceItem" label="服务项目" align="center"show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(service_itemOptions, scope.row.serviceItem)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="policyNo" label="保单号" align="center"/>
    <el-table-column prop="policyItemNo" label="分单号" align="center"/>
    <el-table-column prop="riskCode" label="险种代码" align="center"/>
    <el-table-column prop="insuerd" label="被保人" align="center"/>
    <el-table-column prop="holder" label="投保人" align="center"/>
    <el-table-column align="center" prop="acceptTime" min-width="120" label="受理时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.acceptTime, '{y}-{m}-{d}')}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="modifyTime" min-width="120" label="修改时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.modifyTime, '{y}-{m}-{d}')}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="acceptBy" label="受理人" align="center"/>
    <el-table-column prop="modifyBy" label="处理人" align="center"/>
    <el-table-column prop="vipFlag" label="VIP标识" align="center" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(cs_vip_flagOptions, scope.row.vipFlag)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="priority" label="优先级" align="center"  show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(cs_priorityOptions, scope.row.priority)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="organization" label="出单机构" align="center"  show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{selectDictLabel(cs_organizationOptions, scope.row.organization)}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button v-if="status==='01'" size="small" type="text" @click="subSendInspection(scope.row)">发起质检
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {encrypt} from "@/utils/rsaEncrypt"

// 定义table中需要的字典类型
let dictss = [{dictType: 'cs_channel'}
,{dictType: 'cs_service_item'}
,{dictType: 'cs_vip_flag'}
,{dictType: 'cs_priority'}
,{dictType: 'cs_organization'}
]
export default {
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
      loading:true,
      dictList: [],
      channelOptions: [],
      service_itemOptions: [],
      cs_vip_flagOptions: [],
      cs_priorityOptions: [],
      cs_organizationOptions: [],
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.channelOptions = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.service_itemOptions = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_vip_flagOptions = this.dictList.find(item => {
      return item.dictType === 'cs_vip_flag'
    }).dictDate
    this.cs_priorityOptions = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_organizationOptions = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate

  },
  methods: {
    handleSelectionChange(sel) {
      //调用父页面处理方法
      this.$emit('getSelect', sel)
    },
    // 处理跳转
    subSendInspection(row) {
      //调用父页面处理方法
      this.$emit('sendHandle', row)
      // 新页面处理
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
    }
  }
}
</script>
<style scoped>

.el-table /deep/ .el-table__expanded-cell {
  padding: 10px;
}
</style>

