<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <second-phone ref="secondPhone"></second-phone>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <!--v-model双向绑定 v-model的值为当前被选中的el-option的 value 属性值-->
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="sendForm.itemCode" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_demand_item" :key="item.dictValue" :label="item.dictLabel"
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
            <el-form-item label="处理人：" prop="modifyBy">
              <el-input v-model="sendForm.modifyBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理日期：" prop="HandlerTime">
              <el-date-picker
                v-model="sendForm.handlerTime"
                class="item-width"
                clearable
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
            <el-form-item label="预约日期：" prop="appointmentTime">
              <el-date-picker
                :disabled="true"
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
              <el-select v-model="sendForm.status" class="item-width" clearable placeholder="请选择">
                <el-option v-for="item in cs_order_state" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <div style="text-align: right; margin-right: 1px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandles">查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>

    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
         <span @click="show">
          <i :class="showClass"/>
        </span>
        <span style="color: blue">待处理（{{ totalCount }}）</span>
        <div style="text-align: right; margin-right: 8px;">
          <el-button type="primary" size="mini" @click="add">新增</el-button>
          <el-button type="primary" size="mini" @click="obtainButton">获取</el-button>
        </div>
        <el-divider style="" v-if="isShow"/>
        <el-table
          ref="multipleTable"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange"
          :row-class-name="setRowStyle"
          v-if="isShow"
        >
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column prop="workOrderNo" align="center" width="140" label="工单号" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="workOrderButton(scope.row)">{{scope.row.workOrderNo}}</el-button>
            </template>
          </el-table-column>
          <el-table-column prop="channelCode" align="center" label="受理渠道" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.channelCode">
              <span>{{selectDictLabel(cs_channel, scope.row.channelCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="businessService" align="center" width="140" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.businessService">
              <span>{{selectDictLabel(cs_business_type, scope.row.businessService.split('-')[0])+'-'+selectDictLabel(cs_service_item, scope.row.businessService.split('-')[1])}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="policyNo" align="center" width="140" label="保单号" show-overflow-tooltip/>
          <el-table-column prop="policyItemNo" align="center" width="140" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="center" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="holderName" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="acceptTime" label="受理时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="修改时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>

          <el-table-column prop="acceptBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="modifyBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{selectDictLabel(cs_vip_flag, scope.row.vipFlag)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="priorityLevel" align="center" label="优先级" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.priorityLevel">
              <span>{{selectDictLabel(cs_priority, scope.row.priorityLevel)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organCode" align="center" width="140" label="出单机构" show-overflow-tooltip>
            <!--      如果没有配置字典数据会异常-->
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{selectDictLabel(cs_organization, scope.row.organCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.status">
              <span>{{selectDictLabel(cs_order_state, scope.row.status)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="obtainButton(scope.row)">获取</el-button>
              <el-button size="mini" type="text" @click="modifyButton(scope.row)">修改</el-button>
              <el-button size="mini" type="text" @click="cancleBytton(scope.row)">取消</el-button>

            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="pageNum"
          :limit.sync="pageSize"
          @pagination="searchHandle"
          v-if="isShow"
        />
      </div>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">处理中（{{ totalPersonCount }}）</span>
        <el-divider/>
        <!--：data赋值的地方，下面prop对应好就自己遍历赋值了-->
        <el-table
          v-loading="personLoading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPersonPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange"
          :row-class-name="setRowStyle"
        >
          <el-table-column prop="workOrderNo" align="center" width="150" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-link :underline="false" @click="dealButton(scope.row)">{{ scope.row.workOrderNo }}</el-link>
            </template>
          </el-table-column>
          <el-table-column prop="businessService" align="center" width="140" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.businessService">
              <span>{{selectDictLabel(cs_business_type, scope.row.businessService.split('-')[0])+'-'+selectDictLabel(cs_service_item, scope.row.businessService.split('-')[1])}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="policyNo" align="center" width="140" label="保单号" show-overflow-tooltip/>
          <el-table-column prop="policyItemNo" align="center" width="140" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="center" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="holderName" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="acceptTime" label="受理时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="修改时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{selectDictLabel(cs_vip_flag, scope.row.vipFlag)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="acceptBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="modifyTime" label="响应时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" align="center" label="响应内容" show-overflow-tooltip/>
          <el-table-column prop="organCode" align="center" width="140" label="出单机构" show-overflow-tooltip>
            <!--      如果没有配置字典数据会异常-->
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{selectDictLabel(cs_organization, scope.row.organCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.status">
              <span>{{selectDictLabel(cs_order_state, scope.row.status)}}</span>
            </template>
          </el-table-column>
          <!--fixed="right"控制固定某一列-->
          <el-table-column align="center" label="操作" fixed="right" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="dealButton(scope.row)">处理</el-button>
              <el-button size="mini" type="text" @click="modifyButton(scope.row)">修改</el-button>
              <el-button size="mini" type="text" @click="cancleBytton(scope.row)">取消</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalPersonCount"
          :page.sync="pageNumPerson"
          :limit.sync="pageSizePerson"
          @pagination="searchHandlePerson"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
import moment from 'moment'
import {demandListAndPublicPool,demandListAndPersonalPool,demandObtain,demandObtainMany} from '@/api/customService/demand'
import secondPhone from "../common/modul/secondPhone";
import {selectCallAgain} from '@/api/customService/demand'

let dictss = [
  {dictType: 'cs_demand_item'},
  {dictType: 'cs_service_item'},
  {dictType: 'cs_channel'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_vip_flag'},
  {dictType: 'cs_order_state'},
  {dictType: 'cs_business_type'},
]
export default {
  components:{secondPhone},
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
    }
  },
  data() {
    return {
      showClass:"el-icon-arrow-right",//图表样式
      isShow:false,//控制是否显示个人池
      ids:[],//多选框
      open:"",//是否弹出
      title:"",//弹出框名称
      dictList:[],//总的码表
      cs_demand_item:[],//信息需求服务项目
      cs_service_item:[],//服务项目
      cs_channel:[],//渠道
      cs_organization:[],//出单机构
      cs_priority:[],//优先级
      cs_vip_flag:[],// vip标识
      cs_order_state:[],// 状态
      cs_business_type:[],//服务项目

      secondPhone:[],
      dialogFormVisible: false,
      sendForm: {//传值给后台
        acceptorTime:undefined,//受理时间数组
        appointmentTime:undefined,//预约时间数组
        handlerTime:undefined,//处理时间数组
        pageNum: 1,
        pageSize: 10,
        itemCode: "",//服务信息
        channelCode: "",//受理渠道
        acceptBy: "",//受理人
        modifyBy: "",//处理人
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
        modifyTime:"",
        updateTime:""
      },
      loading: true,
      workPoolData: [],//公共池
      pageNum: 1,
      pageSize: 10,
      totalCount: 0,
      personLoading: true,
      workPersonPoolData:[],//个人池
      pageNumPerson: 1,
      pageSizePerson: 10,
      totalPersonCount: 0,
      changeSerchData: {},
      isRemind:true,
    }
  },
  created() {
    this.isRemind=false;
    this.searchSecondHandle();
    this.searchHandles();
  },
  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.cs_demand_item = this.dictList.find(item => {
      return item.dictType === 'cs_demand_item'
    }).dictDate
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
    this.cs_order_state = this.dictList.find(item => {
      return item.dictType === 'cs_order_state'
    }).dictDate
    this.cs_business_type = this.dictList.find(item => {
      return item.dictType === 'cs_business_type'
    }).dictDate
  },
  methods: {
    //给行的字体加颜色 talbe:row-class-name
    setRowStyle({ row, rowIndex }) {
      if (row.isRedWord == '1') {
        //指定样式
        return 'info-row'
      }else{
        return 'info-row2'
      }
      return '';
    },
    //修改按钮
    modifyButton(s){
      this.$router.push({
        path: '/customService/modify',
        query:{
          workOrderNo:s.workOrderNo,
          policyNo:s.policyNo,
          policyItemNo:s.policyItemNo,
          status:s.status
        }
      })
    },
    //取消按钮
    cancleBytton(s){
      this.$router.push({
        path: '/customService/cancle',
        query:{
          workOrderNo:s.workOrderNo,
          policyNo:s.policyNo,
          policyItemNo:s.policyItemNo,
          status:s.status
        }
      })
    },
    //获取按钮
    obtainButton(s){
      if(s.workOrderNo==null&&this.ids.length===0){
        this.$message.warning("请先选中一行！")
      }else {
        if (s.workOrderNo!=null) {
          let workOrderNo=s.workOrderNo
          demandObtain(workOrderNo).then(res => {
            if (res != null && res.code === 200) {
              this.searchHandles();
              return this.$message.success("获取工单成功！");
            }
          }).catch(res => {
            return this.$message.error("获取工单失败！")
          })
        }else {
          const workOrderNos=this.ids
          demandObtainMany(workOrderNos).then(res => {
            if (res != null && res.code === 200) {
              this.searchHandles();
              return this.$message.success("获取工单成功！");
            }
          }).catch(res => {
            return this.$message.error("获取工单失败！")
          })
        }

      }
    },
    //工单页面超链接
    workOrderButton(s){
      this.$router.push({
        path: '/customService/orderDetails',
        query:{
          workOrderNo:s.workOrderNo,
          policyNo:s.policyNo,
          policyItemNo:s.policyItemNo,
          status:s.status
        }
      })
    },
    //处理按钮
    dealButton(s){
      this.$router.push({
        path: '/customService/deal',
        query:{
          workOrderNo:s.workOrderNo,
          policyNo:s.policyNo,
          policyItemNo:s.policyItemNo,
          status:s.status
        }
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.workOrderNo);

    },

    show(){
      if (this.isShow==false){
        this.isShow=true
        this.showClass="el-icon-arrow-down"
      }else {
        this.isShow=false
        this.showClass="el-icon-arrow-right"

      }
    },
    //增加按钮
    add(row) {
      this.$router.push({
        path: '/customService/demand-edit',
        isEmpty: false
      })
    },
    resetForm() {
      this.$refs.sendForm.resetFields()
    },
    //查询按钮及页面初始化工作池查询
    searchHandles() {
      //公共池
      this.searchHandle();
      //个人池
      this.searchHandlePerson();
    },
    //待处理查询
    searchHandle() {
      let queryParams = JSON.parse(JSON.stringify(this.sendForm));
      if (this.sendForm.acceptorTime) {
        queryParams.acceptTimeStart=this.sendForm.acceptorTime[0]
        queryParams.acceptTimeEnd=this.sendForm.acceptorTime[1]
      }
      if (this.sendForm.handlerTime) {
        queryParams.acceptTimeStart=this.sendForm.handlerTime[0]
        queryParams.acceptTimeEnd=this.sendForm.handlerTime[1]
      }
      if (this.sendForm.appointmentTime) {
        queryParams.acceptTimeStart=this.sendForm.appointmentTime[0]
        queryParams.acceptTimeEnd=this.sendForm.appointmentTime[1]
      }
      queryParams.pageNum = this.pageNum;
      queryParams.pageSize = this.pageSize;
      demandListAndPublicPool(queryParams).then(res => {
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
            if (res.rows.length <= 0) {
              this.isRemind=true;
              console.info("信息需求公共池没有满足条件的数据！")
            }
          }
        }
      ).catch(res => {
        console.error("信息需求公共池数据加载异常！");
      })
    },
    //处理中查询
    searchHandlePerson() {
      this.personLoading=true;
      let queryParams = JSON.parse(JSON.stringify(this.sendForm));
      if (this.sendForm.acceptorTime) {
        queryParams.acceptStartDate=this.sendForm.acceptorTime[0];
        queryParams.acceptEndDate=this.sendForm.acceptorTime[1];
      }
      if (this.sendForm.handlerTime) {
        queryParams.modifyStartDate=this.sendForm.handlerTime[0];
        queryParams.modifyEndDate=this.sendForm.handlerTime[1];
      }
      if (this.sendForm.appointmentTime) {
        queryParams.appointmentStartDate=this.sendForm.appointmentTime[0];
        queryParams.appointmentEndDate=this.sendForm.appointmentTime[1];
      }
      queryParams.pageNum = this.pageNumPerson;
      queryParams.pageSize = this.pageSizePerson;
      demandListAndPersonalPool(queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.workPersonPoolData = res.rows;
          this.totalPersonCount = res.total;
          this.personLoading=false;
          if (res.total <= 0 && this.isRemind) {
            return this.$message.warning(
              "未查询到数据！"
            );
          }
        }
      }).catch(res => {
        this.personLoading=false;
        console.error("信息需求个人池数据加载异常！");
      })
    },

    //二次来电查询
    searchSecondHandle() {
      const query ={
        businessType:"01"
      }
      selectCallAgain(query).then(res => {
        if (res != null && res.code === 200) {
          if (res.rows.length>=1){
            this.$refs.secondPhone.workPoolData=res.rows
            this.$refs.secondPhone.open()
          }
          if (res.rows.length <= 0) {
            console.info("没有再次来电数据！");
          }
        }
      }).catch(res => {
        console.error("信息需求再次来电数据加载异常！");
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


<style>
.el-table .info-row {
  color:red;
}
.el-table .info-row2 {
  color:black;
}
</style>
