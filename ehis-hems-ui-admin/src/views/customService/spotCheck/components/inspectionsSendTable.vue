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
      <template slot-scope="scope" v-if="scope.row.channel">
        <span>{{selectDictLabel(channelOptions, scope.row.channel)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="businessService" label="服务项目" min-width="160" align="center"show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.businessService">
        <span>{{scope.row.businessService.split('-')[0]+'-'+selectDictLabel(service_itemOptions, scope.row.businessService.split('-')[1])}}</span>
      </template>
    </el-table-column>
<!--    <el-table-column v-if="show" prop="policyNo" label="保单号" min-width="160" align="center"/>-->
    <el-table-column prop="policyNo" label="保单号" min-width="160" align="center"/>
    <el-table-column prop="policyItemNo" label="分单号" min-width="160" align="center"/>
    <el-table-column prop="riskCode" label="险种代码" min-width="80" align="center"/>
    <el-table-column prop="insuredPerson.name" label="被保人" align="center"/>
    <el-table-column prop="holderPerson.name" label="投保人" align="center"/>
    <el-table-column align="center" prop="acceptTime" min-width="160" label="受理时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.acceptTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" prop="modifyTime" min-width="160" label="修改时间" show-overflow-tooltip>
      <template slot-scope="scope">
        <span>{{parseTime(scope.row.modifyTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="acceptUser.umCode" label="受理人" min-width="120" align="center"/>
    <el-table-column prop="modifyUser.umCode" label="处理人" min-width="120" align="center"/>
    <el-table-column prop="vipFlag" label="VIP标识" align="center" show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.vipFlag">
        <span>{{selectDictLabel(cs_vip_flagOptions, scope.row.vipFlag)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="priorityLevel" label="优先级" align="center"  show-overflow-tooltip>
      <template slot-scope="scope" v-if="scope.row.priorityLevel">
        <span>{{selectDictLabel(cs_priorityOptions, scope.row.priorityLevel)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="organization" label="出单机构" min-width="160" align="center"  show-overflow-tooltip>
    <!--      如果没有配置字典数据会异常-->
      <template slot-scope="scope" v-if="scope.row.organCode">
        <span>{{selectDictLabel(cs_organizationOptions, scope.row.organCode)}}</span>
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
      show:false,//列隐藏
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
    //给行的字体加颜色 talbe:row-class-name
    setRowStyle({ row, rowIndex }) {
      console.info(row);
      console.info(rowIndex);
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
      console.info(row);
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
