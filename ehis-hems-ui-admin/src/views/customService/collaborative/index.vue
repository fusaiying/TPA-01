<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <!--v-model双向绑定 v-model的值为当前被选中的el-option的 value 属性值-->
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="sendForm.serviceItem" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channelCode">
              <el-select v-model="sendForm.channelCode" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理人：" prop="acceptBy">
              <el-input v-model="sendForm.acceptBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptorTime">
              <el-date-picker
                clearable
                v-model="sendForm.acceptorTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理人：" prop="updateBy">
              <el-input v-model="sendForm.updateBy" class="item-width" clearable size="mini" placeholder="请输入" disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理日期：" prop="HandlerTime">
              <el-date-picker
                clearable
                v-model="sendForm.handlerTime"
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
              <el-input v-model="sendForm.workOrderNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="sendForm.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyItemNo">
              <el-input v-model="sendForm.policyItemNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="holderName">
              <el-input v-model="sendForm.holderName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input v-model="sendForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件号：" prop="idNumber">
              <el-input v-model="sendForm.idNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人电话：" prop="mobilePhone">
              <el-input v-model="sendForm.mobilePhone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="sendForm.organCode" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="appointmentTime" >
              <el-date-picker
                clearable
                v-model="sendForm.appointmentTime"
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
              <el-select v-model="sendForm.priorityLevel" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识：" prop="vipFlag">
              <el-select v-model="sendForm.vipFlag" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_vip_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="status">
              <el-select v-model="sendForm.status" class="item-width" placeholder="请选择" disabled>
                <el-option v-for="item in cs_collaborative_state" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 1px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search" @click="searchHandle">查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">协办中（{{ totalPersonCount }}）</span>
        <el-divider/>
        <!--：data赋值的地方，下面prop对应好就自己遍历赋值了-->
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPersonPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
        >
          <el-table-column align="center" width="150" prop="workOrderNo" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-link :underline="false" @click="dealButton(scope.row)">{{ scope.row.workOrderNo }}</el-link>
            </template>
            </el-table-column>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.itemCode">
              <span>{{ selectDictLabel(cs_service_item, scope.row.itemCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyItemNo" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="riskCode" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="holderName" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="acceptTime" label="受理时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="修改时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="createBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{ selectDictLabel(cs_vip_flag, scope.row.vipFlag) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyTime" label="响应时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" align="center" label="响应内容" show-overflow-tooltip/>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip>
            <!--      如果没有配置字典数据会异常-->
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{ selectDictLabel(cs_organization, scope.row.organCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.status">
              <span>{{ selectDictLabel(cs_collaborative_state, scope.row.status) }}</span>
            </template>
          </el-table-column>
          <!--fixed="right"控制固定某一列-->
          <el-table-column align="center" label="操作" fixed="right" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="dealButton(scope.row)">处理</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="totalPersonCount>0"
          :total="totalPersonCount"
          :page.sync="pageNumPerson"
          :limit.sync="pageSizePerson"
          @pagination="searchHandle"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
import moment from 'moment'
import {selectAssist} from '@/api/customService/collaborative'

let dictss = [
  {dictType: 'cs_service_item'}
  ,{dictType: 'cs_channel'}
  ,{dictType: 'cs_organization'}
  ,{dictType: 'cs_priority'}
  ,{dictType: 'cs_vip_flag'}
  ,{dictType: 'cs_collaborative_state'}
]

export default {
  //日期统一格式化
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
    }
  },
  data() {
    return {
      ids: [],//多选框
      open: "",//是否弹出
      title: "",//弹出框名称
      dictList: [],
      cs_service_item: [],//服务项目
      cs_channel: [],//渠道
      cs_organization: [],//出单机构
      cs_priority: [],//优先级
      cs_vip_flag: [],// vip标识
      cs_collaborative_state: [],// 状态
      dialogFormVisible: false,
      sendForm: {//传值给后台
        serviceItem: undefined,//服务信息
        channelCode: undefined,//受理渠道
        acceptBy: undefined,//受理人
        modifyBy: undefined,//原处理人
        updateBy: undefined,//处理人
        acceptorTime: undefined,//受理时间数组
        appointmentTime: undefined,//预约时间数组
        handlerTime: undefined,//处理时间数组
        workOrderNo: undefined,//工单编号
        policyNo: undefined,//保单号
        policyItemNo: undefined,//分单号
        holderName: undefined,//投保人
        insuredName: undefined,//被保人
        idNumber: undefined,//证件号
        organCode: undefined,//出单机构
        priorityLevel: undefined,//优先级
        vipFlag: undefined,//VIP标识
        mobilePhone: undefined,//移动电话
      },
      loading: true,
      workPersonPoolData: [],
      totalPersonCount: 0,
      pageNumPerson: 1,
      pageSizePerson: 10,
      changeSerchData: {},
    }
  },
  created() {
    this.searchHandle()
  },
  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_channel = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_priority = this.dictList.find(item => {
      return item.dictType === 'cs_priority'
    }).dictDate
    this.cs_vip_flag = this.dictList.find(item => {
      return item.dictType === 'cs_vip_flag'
    }).dictDate
    this.cs_collaborative_state = this.dictList.find(item => {
      return item.dictType === 'cs_collaborative_state'
    }).dictDate
  },
  methods: {
    //工单页面超链接
    workOrderButton(s) {
      if (s.businessType == "01") {
        this.$router.push({
          path: '/customService/orderDetails',
          query: {
            workOrderNo: s.workOrderNo,
            policyNo: s.policyNo,
            policyItemNo: s.policyItemNo,
            status: s.status
          }
        })
      } else {
        this.$router.push({
          path: '/customService/complaint/orderDetails',
          query: {
            workOrderNo: s.workOrderNo,
            policyNo: s.policyNo,
            policyItemNo: s.policyItemNo,
            status: s.status
          }
        })

      }

    },
    //处理按钮
    dealButton(s) {
      if (s.businessType == "01") {
        this.$router.push({
          path: '/customService/collaborative/deal',
          query: {
            workOrderNo: s.workOrderNo,
            policyNo: s.policyNo,
            policyItemNo: s.policyItemNo,
            status: s.status
          }
        })
      } else {
        this.$router.push({
          path: '/customService/collaborative/complaint',
          query: {
            workOrderNo: s.workOrderNo,
            policyNo: s.policyNo,
            policyItemNo: s.policyItemNo,
            status: s.status
          }
        })
      }

    },
    resetForm() {
      this.$refs.sendForm.resetFields()
    },
    //查询
    searchHandle() {
      let queryParams = JSON.parse(JSON.stringify(this.sendForm));
      console.info(queryParams);
      queryParams.pageNum = this.pageNumPerson;
      queryParams.pageSize = this.pageSizePerson;
      if (this.sendForm.acceptorTime) {
        queryParams.acceptStartDate = this.sendForm.acceptorTime[0];
        queryParams.acceptEndDate = this.sendForm.acceptorTime[1];
      }
      if (this.sendForm.appointmentTime) {
        queryParams.appointmentStartDate = this.sendForm.appointmentTime[0];
        queryParams.appointmentEndDate = this.sendForm.appointmentTime[1];
      }
      if (this.sendForm.handlerTime) {
        queryParams.updateStartDate = this.sendForm.handlerTime[0];
        queryParams.updateEndDate = this.sendForm.handlerTime[1];
      }
      selectAssist(queryParams).then(res => {
        console.log('个人池：', res.rows)
        if (res != null && res.code === 200) {
          this.workPersonPoolData = res.rows
          this.totalPersonCount = res.total
          if (res.total <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }
      }).catch(res => {

      })
    },
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
