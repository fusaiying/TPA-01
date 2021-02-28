<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <second-phone :work-pool-data="workPoolData"></second-phone>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
<!--v-model双向绑定 v-model的值为当前被选中的el-option的 value 属性值-->
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="sendForm.itemCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channelCode">
              <el-select v-model="sendForm.channelCode" class="item-width" placeholder="请选择">
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
              <el-select v-model="sendForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="appointmentTime">
              <el-date-picker
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
              <el-select v-model="sendForm.priorityLevel" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识：" prop="vipFlag">
              <el-select v-model="sendForm.vipFlag" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_vip_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
          <el-form-item label="状态：" prop="status">
            <el-select v-model="sendForm.status" class="item-width" placeholder="请选择">
              <el-option v-for="item in cs_handle_state" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>


        </el-row>
        <div style="text-align: right; margin-right: 1px;">
          <el-button size="mini" type="primary" @click="searchHandles">查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">待处理（{{ totalCount }}）</span>
        <div style="text-align: right; margin-right: 8px;">
            <el-button type="primary" size="mini" @click="add">新增</el-button>
           <el-button type="primary" size="mini" @click="obtainButton">获取</el-button>
        </div>
        <el-divider style=""/>
        <el-table
          ref="multipleTable"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange"

        >
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="workOrderButton(scope.row)">{{scope.row.workOrderNo}}</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.itemCode">
              <span>{{selectDictLabel(cs_service_item, scope.row.itemCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="complaintTime" label="预约日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="medicalInstitution" label="预约医院" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center"  prop="policyItemNo" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="riskCode" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="holderName" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="acceptTime" label="受理时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>

          <el-table-column prop="acceptBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="modifyBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{selectDictLabel(cs_vip_flag, scope.row.vipFlag)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip>
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
          :page.sync="sendForm.pageNum"
          :limit.sync="sendForm.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">处理中（{{ totalPersonCount }}）</span>
        <el-divider/>
        <!--：data赋值的地方，下面prop对应好就自己遍历赋值了-->
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPersonPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="workOrderButton(scope.row)">{{scope.row.workOrderNo}}</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.itemCode">
              <span>{{selectDictLabel(cs_service_item, scope.row.itemCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="complaintTime" label="预约日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="medicalInstitution" label="预约医院" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center"  prop="policyItemNo" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="riskCode" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="holderName" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="acceptTime" label="受理时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="acceptBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="createBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{selectDictLabel(cs_vip_flag, scope.row.vipFlag)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip>
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
          :page.sync="sendForm.pageNum"
          :limit.sync="sendForm.pageSize"
          @pagination="searchHandle1"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,demandObtain,demandObtainMany} from '@/api/customService/reservation'
  import secondPhone from "../common/modul/secondPhone";

  export default {
    components:{secondPhone},
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {
      return {
        ids:[],//多选框
        open:"",//是否弹出
        title:"",//弹出框名称
        cs_service_item:[],//服务项目
        cs_channel:[],//渠道
        cs_organization:[],//出单机构
        cs_priority:[],//优先级
        cs_vip_flag:[],// vip标识
        cs_order_state:[],// 状态：
        secondPhone:[],
        riskCodes:[],
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {//传值给后台
          pageNum: 1,
          pageSize: 10,
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
          // acceptTimeStart:"",//开始受理时间
          // acceptTimeEnd:"",//结束受理时间
          // modifyTimeStart:"",//开始修改时间
          // modifyTimeEnd:"",//结束修改时间
          // complaintTimeStart:"",//开始预约时间
          // complaintTimeEnd:""//结束预约时间

        },
        caseNumber: false,//查询条件（报案号）是否显示
        loading: true,
        workPoolData: [],
        workPersonPoolData:[],
        isinit: 'Y',
        totalCount: 0,
        totalPersonCount: 0,
        changeSerchData: {},
        serves: [{
          value: '1',
          label: '服务1'
        }, {
          value: '2',
          label: '服务2'
        }, {
          value: '3',
          label: '服务3'
        }, {
          value: '4',
          label: '服务4'
        }],
        sysUserOptions: [],
      }
    },
    created() {
      this.searchHandles()
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
      this.getDicts("cs_organization").then(response => {
        this.cs_organization = response.data;
      });
      this.getDicts("cs_priority").then(response => {
        this.cs_priority = response.data;
      });
      this.getDicts("cs_vip_flag").then(response => {
        this.cs_vip_flag = response.data;
      });
      this.getDicts("cs_order_state").then(response => {
        this.cs_order_state = response.data;
      });
      this.getDicts("cs_channel").then(response => {
        this.cs_channel = response.data;
      });



    },

    methods: {
      //工单页面超链接
      workOrderButton(s){
        this.$router.push({
          path: '/customService/reservation/orderDetail',
          query:{
            workOrderNo:s.workOrderNo,
            policyNo:s.policyNo,
            policyItemNo:s.policyItemNo,
            status:s.status
          }
        })
      },
      //修改按钮
      modifyButton(s){
        this.$router.push({
          path: '/customService/reservation/modify',
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
          path: '/customService/reservation/cancle',
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
          alert("请先选中一行！")
        }else {
        if (s.workOrderNo!=null) {
          let workOrderNo=s.workOrderNo
          demandObtain(workOrderNo).then(res => {
            if (res != null && res.code === 200) {
            }
          }).catch(res => {

          })


        }else {
           const workOrderNos=this.ids
           console.log("ids:",workOrderNos)
           demandObtainMany(workOrderNos).then(res => {
            if (res != null && res.code === 200) {
              alert("success")
            }
          }).catch(res => {

          })
        }
          this.searchHandles()
      }
        },
      //处理按钮
      dealButton(s){
          this.$router.push({
            path: '/customService/reservation/deal',
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

      //增加按钮
      add(row) {
        this.$router.push({
          path: '/customService/reservation/add',
          isEmpty: false
        })
      },
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      //待处理查询
      searchHandle() {
        let queryParams;
        if (this.sendForm.acceptorTime.length > 0) {
          queryParams = JSON.parse(JSON.stringify(this.sendForm));
          queryParams.acceptTimeStart=acceptorTime[0]
          queryParams.acceptTimeEnd=acceptorTime[1]
        } else {
          queryParams = this.sendForm;
        }
        demandListAndPublicPool(queryParams).then(res => {
          console.log('共公池',res.rows)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.rows.length
            console.log('response',res.total)
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //处理中查询
      searchHandle1() {
        let queryParams;
        if (this.sendForm.acceptorTime.length > 0) {
          queryParams = JSON.parse(JSON.stringify(this.sendForm));
          queryParams.acceptTimeStart=acceptorTime[0]
          queryParams.acceptTimeEnd=acceptorTime[1]
        } else {
          queryParams = this.sendForm;
        }
        demandListAndPersonalPool(this.sendForm).then(res => {
          console.log('个人池：',res.rows)
          if (res != null && res.code === 200) {
            this.workPersonPoolData = res.rows
            this.totalPersonCount = res.rows.length
            console.log('response',res.total)
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },

      //查询按钮
      searchHandles() {
        this.searchHandle()
        this.searchHandle1()
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
