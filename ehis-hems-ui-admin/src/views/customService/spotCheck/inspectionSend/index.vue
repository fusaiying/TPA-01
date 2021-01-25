<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendQueryForm" :model="sendQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="serviceItem">
              <el-select v-model="sendQueryForm.serviceItem" class="item-width" placeholder="请选择">
                <el-option v-for="item in service_itemOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channel">
              <el-select v-model="sendQueryForm.channel" class="item-width" placeholder="请选择">
                <el-option v-for="item in channelOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理人：" prop="acceptorName">
              <el-input v-model="sendQueryForm.acceptorName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptorTime">
              <el-date-picker
                v-model="sendQueryForm.acceptorTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理人：" prop="handlerName">
              <el-input v-model="sendQueryForm.handlerName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理日期：" prop="handlerTime">
              <el-date-picker
                v-model="sendQueryForm.handlerTime"
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
              <el-input v-model="sendQueryForm.workOrderNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="sendQueryForm.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyItemNo">
              <el-input v-model="sendQueryForm.policyItemNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="holderName">
              <el-input v-model="sendQueryForm.holderName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input v-model="sendQueryForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件号：" prop="insuredIdNumber">
              <el-input v-model="sendQueryForm.insuredIdNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人电话：" prop="phone">
              <el-input v-model="sendQueryForm.phone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organization">
              <el-select v-model="sendQueryForm.organization" class="item-width" placeholder="请选择">
                <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="complaintTime">
              <el-date-picker
                v-model="sendQueryForm.complaintTime"
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
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="sendQueryForm.priority" class="item-width" placeholder="请选择">
                <el-option v-for="item in priorityOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识：" prop="vipFlag">
              <el-select v-model="sendQueryForm.vipFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in vip_flagOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="status">
              <el-select v-model="sendQueryForm.status" class="item-width" placeholder="请选择">
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
          <el-button
            :loading="sendLoading"
            size="mini"
            type="primary"
            @click="batchSendHandle"
          >发起质检</el-button>

        </span>
        <el-divider/>
        <inspectionsSendTable v-loading="inspectionsSendTableLading" :table-data="workPoolData" @getSelect="getSelect"/>
        <!--分页控件 -->
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
// 引入外部公用的组件
import inspectionsSendTable from '../components/inspectionsSendTable'
// 引入请求接口所在的js文件及接口名称
import {inspectionQueryBy,inspectionSendByIds} from '@/api/customService/spotCheck'

//字典类型集合请求参数定义
let dictss = [{dictType: 'cs_service_item'}
,{dictType: 'cs_channel'}
,{dictType: 'cs_organization'}
,{dictType: 'cs_priority'}
,{dictType: 'cs_vip_flag'}
,{dictType: 'cs_handle_state'}
]
// 默认输出
export default {
  //组件
  components: {
    // 同 import 中定义的名称
    inspectionsSendTable
  },
  //数据
  data() {
    // 返回
    return {
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询表单参数定义
      sendQueryForm: {
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
      //分页控件参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      //工作池加载状态
      inspectionsSendTableLading: false,
      //工作池数据返回对象
      workPoolData: [],
      isinit: 'Y',
      //默认显示第一页
      page: 1,
      //总记录数，查询标题显示，分页控件使用
      totalCount: 0,
      //每页显示10条记录
      finishPageSize: 10,
      changeSerchData: {},
      //下拉框字典数据返回对象
      dictList: [],
      //下拉框字典数据返回对象
      service_itemOptions:[],
      channelOptions:[],
      organizationOptions:[],
      priorityOptions:[],
      vip_flagOptions:[],
      statusOptions:[],
      //质检按钮加载状态
      sendLoading: false,
      //table选中集合
      selList: [],
    }
  },
  //生命函数：created:在模板渲染成html前调用，即通常初始化某些属性值，然后再渲染成视图。
  created() {
  },
  // 异步初始化数据加载
  //生命函数：mounted:在模板渲染成html后调用，通常是初始化页面完成后，再对html的dom节点进行一些需要的操作。
  async mounted() {
    // 字典数据获取
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
  // 控件方法定义
  methods: {
    //抽检成功后工作池数据更新

    // 重置方法
    resetForm() {
      this.$refs.sendQueryForm.resetFields()
    },
    // 查询操作
    searchHandle() {
      this.inspectionsSendTableLading = true
      // 准备查询条件
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        serviceItemCode: this.sendQueryForm.serviceItem,
        channelCode: this.sendQueryForm.channel,
        acceptorName: this.sendQueryForm.acceptorName,
        acceptorStartTime: undefined,
        acceptorEndTime: undefined,
        handlerName: this.sendQueryForm.handlerName,
        handlerStartTime: undefined,
        handlerEndTime: undefined,
        workOrderNo: this.sendQueryForm.workOrderNo,
        policyNo: this.sendQueryForm.policyNo,
        policyItemNo: this.sendQueryForm.policyItemNo,
        holderName: this.sendQueryForm.holderName,
        insuredName: this.sendQueryForm.insuredName,
        insuredIdNumber: this.sendQueryForm.insuredIdNumber,
        phone: this.sendQueryForm.phone,
        organCode: this.sendQueryForm.organization,
        complaintStartTime: undefined,
        complaintEndTime: undefined,
        priorityCode: this.sendQueryForm.priority,
        vipFlag: this.sendQueryForm.vipFlag,
        status: this.sendQueryForm.status
      }
      //日期区间拆分
      if (this.sendQueryForm.acceptorTime) {
        query.acceptorStartTime = this.sendQueryForm.acceptorTime[0]
        query.acceptorEndTime = this.sendQueryForm.acceptorTime[1]
      }
      if (this.sendQueryForm.handlerTime) {
        query.handlerStartTime = this.sendQueryForm.handlerTime[0]
        query.handlerEndTime = this.sendQueryForm.handlerTime[1]
      }
      if (this.sendQueryForm.complaintTime) {
        query.complaintStartTime = this.sendQueryForm.complaintTime[0]
        query.complaintEndTime = this.sendQueryForm.complaintTime[1]
      }
      // API 方法名称
      inspectionQueryBy(query).then(res => {
        if (res != null && res.code === 200) {
          //初始化列表参数数据赋值
          this.workPoolData = res.rows
          //初始化参数进行赋值
          this.totalCount = res.total
          //清除table加载状态
          this.inspectionsSendTableLading=false;
          if (res.rows.length <= 0) {
            //没有满足条件数据提示
            return this.$message.warning(
              "未查询到数据！"
            )
          }
        }else{
          //清除table加载状态
          this.inspectionsSendTableLading=false;
        }
      }).catch(res => {
      //异常处理
        //清除table加载状态
        this.inspectionsSendTableLading=false;
      })
    },
    //包含子页面选中条目父页面参数赋值方法
    getSelect(sel) {
      this.selList = sel
    },
    // 批量发起质检
    batchSendHandle() {
      this.sendLoading = true
      if (this.selList.length < 1) {
        this.$message({ message: '请选择需要质检的记录，进行批量获取操作', type: 'warning' })
        this.sendLoading = false
        return
      }
      //后台请求参数处理
      let workOrderNos=[]
      for (let i = 0; i < this.selList.length; i++) {
        workOrderNos.push(this.selList[i].workOrderNo)
      }
      console.log(workOrderNos);
      if (workOrderNos.length<1){
        this.sendLoading = false
        return this.$message.warning(
          "请选择需要质检的记录，进行批量获取操作。"
        )
      }else {
        //后台方法调用及返回结果处理
        this.postHandle(workOrderNos)
      }

    },
    // 发起单个质检
    sendHandle(row) {
      let workOrderNos=[row.workOrderNo]
      //后台方法调用及返回结果处理
      this.postHandle(workOrderNos)
    },
    postHandle(ids){
      inspectionSendByIds(ids).then(res=>{
        if (res!=null && res.code===200){
          this.$message({
            message: '获取成功！',
            type: 'success',
            center: true,
            showClose: true
          })
        }
        this.sendLoading = false

      }).catch(res => {
        this.$message.error('获取失败！')
        this.sendLoading = false
      })
      this.searchHandle();
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>

