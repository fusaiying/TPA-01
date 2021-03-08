<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="acceptQueryForm" :model="acceptQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="acceptQueryForm.itemCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in service_itemOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channelCode">
              <el-select v-model="acceptQueryForm.channelCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in channelOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理人：" prop="acceptBy">
              <el-input v-model="acceptQueryForm.acceptBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptorTime">
              <el-date-picker
                v-model="acceptQueryForm.acceptorTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理人：" prop="modifyBy">
              <el-input v-model="acceptQueryForm.modifyBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理日期：" prop="HandlerTime">
              <el-date-picker
                v-model="acceptQueryForm.handlerTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：" prop="workOrderNo">
              <el-input v-model="acceptQueryForm.workOrderNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="acceptQueryForm.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyItemNo">
              <el-input v-model="acceptQueryForm.policyItemNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="holderName">
              <el-input v-model="acceptQueryForm.holderName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input v-model="acceptQueryForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件号：" prop="idNumber">
              <el-input v-model="acceptQueryForm.insuredIdNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人电话：" prop="mobilePhone">
              <el-input v-model="acceptQueryForm.phone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="acceptQueryForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="appointmentTime">
              <el-date-picker
                v-model="acceptQueryForm.complaintTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="acceptQueryForm.priorityLevel" class="item-width" placeholder="请选择">
                <el-option v-for="item in priorityOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识：" prop="vipFlag">
              <el-select v-model="acceptQueryForm.vipFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in vip_flagOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="status" placeholder="请选择">
              <el-select v-model="acceptQueryForm.status" class="item-width" placeholder="请选择">
                <el-option v-for="item in statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
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
            <el-button type="primary" size="mini" @click="sendMany">清单导出</el-button>
        </span>
        <el-divider/>

        <workOrderTable :table-data="workPoolData" />
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
import {selectAcceptQuery,selectWorkOrder} from '@/api/customService/acceptQuery'

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
        itemCode: "",//服务信息
        channelCode: "",//受理渠道
        acceptBy: "",//受理人
        modifyBy: "",//处理人
        acceptorTime:[],//受理时间数组
        appointmentTime:[],//预约时间数组
        handlerTime:[],//处理时间数组
        workOrderNo: "",//工单编号
        policyNo: "",//保单号
        policyItemNo: "",//分单号
        holderName: "",//投保人
        insuredName: "",//被保人
        idNumber: "",//证件号
        organCode: "",//出单机构
        priorityLevel:"",//优先级
        vipFlag:"",//VIP标识
        mobilePhone:"",//移动电话
        status:"",//状态
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
      finishPageSize: 0,
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
    this.searchHandle()
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
    // this.searchHandle()
  },
  methods: {
    resetForm() {
      this.$refs.acceptQueryForm.resetFields()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        itemCode: this.acceptQueryForm.itemCode,
        channelCode: this.acceptQueryForm.channelCode,
        acceptBy: this.acceptQueryForm.acceptBy,
        acceptorStartTime: undefined,
        acceptorEndTime: undefined,
        modifyBy: this.acceptQueryForm.modifyBy,
        handlerStartTime: undefined,
        handlerEndTime: undefined,
        workOrderNo: this.acceptQueryForm.workOrderNo,
        policyNo: this.acceptQueryForm.policyNo,
        policyItemNo: this.acceptQueryForm.policyItemNo,
        holderName: this.acceptQueryForm.holderName,
        insuredName: this.acceptQueryForm.insuredName,
        insuredIdNumber: this.acceptQueryForm.insuredIdNumber,
        phone: this.acceptQueryForm.phone,
        organCode: this.acceptQueryForm.organCode,
        complaintStartTime: undefined,
        complaintEndTime: undefined,
        priorityLevel: this.acceptQueryForm.priorityLevel,
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

      selectWorkOrder(query).then(res => {
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
    sendMany(){

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
