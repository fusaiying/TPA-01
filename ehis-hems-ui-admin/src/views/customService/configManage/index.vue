<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="acceptQueryForm" :model="acceptQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="投保来源ID：" prop="acceptorName">
              <el-input v-model="acceptQueryForm.acceptorName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="投保来源名称：" prop="handlerName">
              <el-input v-model="acceptQueryForm.handlerName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="投诉业务类别ID：" prop="workOrderNo">
              <el-input v-model="acceptQueryForm.workOrderNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉业务类别名称：" prop="holderName">
              <el-input v-model="acceptQueryForm.holderName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              isinit='N',
              page=1,
              finishPage=1,
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询结果（{{ totalCount }}）</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="upload">上传清单</el-button>
          <el-button type="primary" size="mini" @click="dowload">导出清单</el-button>
        </span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" width="140" prop="rptno" label="投保来源ID" show-overflow-tooltip/>
          <el-table-column align="center" prop="rptno" label="投保来源名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="rptno" label="投保业务类别ID" show-overflow-tooltip/>
          <el-table-column align="center" prop="rptno" label="投保业务类别名称" show-overflow-tooltip/>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import workOrderTable from '../common/components/workOrderTable'
import {selectAcceptQuery} from '@/api/customService/acceptQuery'

let dictss = [{dictType: 'cs_service_item'}
  ,{dictType: 'cs_channel'}
  ,{dictType: 'cs_organization'}
  ,{dictType: 'cs_priority'}
  ,{dictType: 'cs_vip_flag'}
  ,{dictType: 'cs_handle_state'}
]
export default {
  components: {
    workOrderTable
  },
  data() {
    return {
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      acceptQueryForm: {
        serviceItem: undefined,
        channel: undefined,
        acceptorName: undefined,
        acceptorTime: undefined,
        handlerName: undefined,
        handlerTime: undefined,
        workOrderNo: undefined,
        policyNo: undefined,
        policyItemNo: undefined,
        holderName: undefined,
        insuredName: undefined,
        insuredIdNumber: undefined,
        phone: undefined,
        organization: undefined,
        complaintTime: undefined,
        priority: undefined,
        vipFlag: undefined,
        status: undefined
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      loading: true,
      workPoolData: [],
      isinit: 'Y',
      page: 1,
      totalCount: 0,
      finishPageSize: 10,
      changeSerchData: {},
      //字典数据对象
      dictList:[],
      //下拉数据对象
      service_itemOptions:[],
      channelOptions:[],
      organizationOptions:[],
      priorityOptions:[],
      vip_flagOptions:[],
      statusOptions:[]
    }
  },
  created() {
  },
  async mounted() {
    // 字典数据赋值
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.service_itemOptions = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.channelOptions = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.organizationOptions = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.priorityOptions = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.vip_flagOptions = this.dictList.find(item => {
      return item.dictType === 'cs_vip_flag'
    }).dictDate
    this.statusOptions = this.dictList.find(item => {
      return item.dictType === 'cs_handle_state'
    }).dictDate
    this.searchHandle()
  },
  methods: {
    resetForm() {
      this.$refs.acceptQueryForm.resetFields()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        serviceItemCode: this.acceptQueryForm.serviceItem,
        channelCode: this.acceptQueryForm.channel,
        acceptorName: this.acceptQueryForm.acceptorName,
        acceptorStartTime: undefined,
        acceptorEndTime: undefined,
        handlerName: this.acceptQueryForm.handlerName,
        handlerStartTime: undefined,
        handlerEndTime: undefined,
        workOrderNo: this.acceptQueryForm.workOrderNo,
        policyNo: this.acceptQueryForm.policyNo,
        policyItemNo: this.acceptQueryForm.policyItemNo,
        holderName: this.acceptQueryForm.holderName,
        insuredName: this.acceptQueryForm.insuredName,
        insuredIdNumber: this.acceptQueryForm.insuredIdNumber,
        phone: this.acceptQueryForm.phone,
        organizationCode: this.acceptQueryForm.organization,
        complaintStartTime: undefined,
        complaintEndTime: undefined,
        priorityCode: this.acceptQueryForm.priority,
        vipFlag: this.acceptQueryForm.vipFlag,
        status: this.acceptQueryForm.status
      }
      if (this.acceptQueryForm.acceptorTime) {
        query.acceptorStartTime = this.acceptQueryForm.acceptorTime[0]
        query.acceptorEndTime = this.acceptQueryForm.acceptorTime[1]
      }
      if (this.acceptQueryForm.handlerTime) {
        query.handlerStartTime = this.acceptQueryForm.handlerTime[0]
        query.handlerEndTime = this.acceptQueryForm.handlerTime[1]
      }
      if (this.acceptQueryForm.complaintTime) {
        query.complaintStartTime = this.acceptQueryForm.complaintTime[0]
        query.complaintEndTime = this.acceptQueryForm.complaintTime[1]
      }

      selectAcceptQuery(query).then(res => {
        if (res != null && res.code === 200) {
          this.workPoolData = res.rows
          this.totalCount = res.total
          if (res.rows.length<=0){
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
    send() {

    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
