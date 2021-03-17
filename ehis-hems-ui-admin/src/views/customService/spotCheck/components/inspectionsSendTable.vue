<template>
  <el-table
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;"
    @selection-change="handleSelectionChange"
    :row-class-name="setRowStyle"
  >
    <el-table-column type="selection" align="center" content="全选"/>
    <el-table-column prop="workOrderNo" label="工单号" min-width="160" align="center" :show-overflow-tooltip="true">
      <template slot-scope="scope">
        <router-link :to="'/dict/type/data/' + scope.row.workOrderNo" class="link-type">
          <span :class="scope.row.isRedWord? 'info-row':'info-row2'">{{ scope.row.workOrderNo }}</span>
        </router-link>
      </template>
    </el-table-column>
    <!-- table中下拉属性转换 -->
    <el-table-column align="center" prop="channel" min-width="120" label="受理渠道" show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.channelCode">
        <span>{{selectDictLabel(cs_channel, scope.row.channelCode)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="businessService" label="服务项目" min-width="160" align="center"show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.businessService">
        <span>{{selectDictLabel(cs_service_item, scope.row.businessService.split('-')[1])}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="policyNo" label="保单号" min-width="160" align="center"/>
    <el-table-column prop="policyItemNo" label="分单号" min-width="160" align="center"/>
    <el-table-column prop="riskCode" label="险种代码" min-width="80" align="center"/>
    <el-table-column prop="is" label="被保人" align="center"/>
    <el-table-column prop="holderPerson.name" label="投保人" align="center"/>
    <el-table-column align="center" prop="acceptTime" min-width="160" label="受理时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.acceptTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="updateTime" min-width="160" label="修改时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="acceptUserId" label="受理人" min-width="120" align="center"/>
    <el-table-column prop="updateBy" label="处理人" min-width="120" align="center"/>
    <el-table-column prop="vipFlag" label="VIP标识" align="center" show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.vipFlag">
        <span>{{selectDictLabel(cs_vip_flag, scope.row.vipFlag)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="priorityLevel" label="优先级" align="center"  show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.priorityLevel">
        <span>{{selectDictLabel(cs_priority, scope.row.priorityLevel)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="organization" label="出单机构" min-width="160" align="center"  show-overflow-tooltip>
    <!--      如果没有配置字典数据会异常-->
      <template slot-scope="scope" v-if="scope.row.organCode">
        <span>{{selectDictLabel(cs_organization, scope.row.organCode)}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="操作" min-width="94" fixed="right">
      <template slot-scope="scope">
        <el-button size="small" type="text" @click="subSendInspection(scope.row)">发起质检
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
// 定义table中需要的字典类型，必须存在，否则在赋值的时候异常
import {getHospitalInfo} from "@/api/claim/handleCom";

let dictss = [{dictType: 'cs_channel'}
,{dictType: 'cs_service_item'}
,{dictType: 'cs_vip_flag'}
,{dictType: 'cs_priority'}
,{dictType: 'cs_organization'}
,{dictType: 'cs_business_type'}
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
      show:false,//列隐藏
      dictList: [],
      cs_channel: [],
      cs_service_item: [],
      cs_vip_flag: [],
      cs_priority: [],
      cs_organization: [],
      cs_business_type: [],
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.cs_channel = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_vip_flag = this.dictList.find(item => {
      return item.dictType === 'cs_vip_flag'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_business_type = this.dictList.find(item => {
      return item.dictType === 'cs_business_type'
    }).dictDate

  },
  methods: {
    //给行的字体加颜色 talbe:row-class-name
    setRowStyle({ row, rowIndex }) {
      if (row.isRedWord == true) {
        //指定样式
        return 'info-row'
      }else{
        return 'info-row2'
      }
      return '';
    },

    handleSelectionChange(sel) {
      //调用父页面处理方法 并在父页面中引用处@定义
      this.$emit('getSelect', sel)
    },
    // 处理跳转
    subSendInspection(row) {
      let workOrderNos=[row.workOrderNo]
      //调用父页面处理方法 并在父页面中引用处@定义
      this.$emit('postHandle', workOrderNos)
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

<style>
.el-table .info-row {
  color:red;
}
.el-table .info-row2 {
  color:black;
}
</style>
