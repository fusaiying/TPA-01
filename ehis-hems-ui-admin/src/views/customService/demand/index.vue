<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <second-phone :work-pool-data="workPoolData"></second-phone>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
<!--v-model双向绑定 v-model的值为当前被选中的el-option的 value 属性值-->
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="service">
              <el-select v-model="sendForm.service" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channel">
              <el-select v-model="sendForm.channel" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理人：" prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
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
            <el-form-item label="处理人：" prop="Handler">
              <el-input v-model="sendForm.handler" class="item-width" clearable size="mini" placeholder="请输入"/>
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
            <el-form-item label="工单号：" prop="workNumber">
              <el-input v-model="sendForm.workNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNumber">
              <el-input v-model="sendForm.policyNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="secondNumber">
              <el-input v-model="sendForm.secondNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="insuredName">
              <el-input v-model="sendForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件号：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人电话：" prop="phone">
              <el-input v-model="sendForm.phone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organization">
              <el-select v-model="sendForm.organization" class="item-width" placeholder="请选择">
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
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="sendForm.priority" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="vip标识：" prop="vip">
              <el-select v-model="sendForm.vip" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_vip_flag" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
          <el-form-item label="状态：" prop="state">
            <el-select v-model="sendForm.state" class="item-width" placeholder="请选择">
              <el-option v-for="item in cs_handle_state" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>


        </el-row>
        <div style="text-align: right; margin-right: 1px;">
          <el-button size="mini" type="primary" @click="searchHandle">查询</el-button>
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
           <el-button type="primary" size="mini" @click="add">获取</el-button>
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
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="channelCode" label="受理渠道" show-overflow-tooltip/>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip/>
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
          <el-table-column prop="modifyBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip/>
          <el-table-column prop="priorityLevel" align="center" label="优先级" show-overflow-tooltip/>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip/>
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
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
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
<!--          <el-table-column type="selection" align="center" name/> sd-->
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip/>
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
          <el-table-column prop="createBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip/>
          <el-table-column prop="acceptBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="modifyBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="modifyTime" label="响应时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="priorityLevel" align="center" label="响应内容" show-overflow-tooltip/>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip/>
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
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,demandObtain} from '@/api/customService/demand'
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
        cs_handle_state:[],// 状态：
        secondPhone:[],
        riskCodes:[],
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          service: "",//服务信息
          channel: "",
          acceptor: "",
          acceptorTime:"",
          handler: "",
          handlerTime: "",
          workNumber: "",
          policyNumber: "",
          secondNumber: "",
          insuredName: "",
          beInsuredName: "",
          beInsuredNo: "",
          organization: "",
          appointmentTime:"",
          priority:"",
          vip:"",
          phone:"",
          state:""
        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10


        },
        loading: true,
        workPoolData: [],
        workPersonPoolData:[],
        isinit: 'Y',
        totalCount: 0,
        totalPersonCount: 0,
        changeSerchData: {},
        states: [],
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
      this.searchHandle()
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
      this.getDicts("cs_handle_state").then(response => {
        this.cs_handle_state = response.data;
      });
      this.getDicts("cs_channel").then(response => {
        this.cs_channel = response.data;
      });



    },

    methods: {
      //修改按钮
      modifyButton(){
        this.$router.push({
          path: '/customService/modify',
          isEmpty: false
        })
      },
      //取消按钮
      cancleBytton(){
        this.$router.push({
          path: '/customService/cancle',
          isEmpty: false
        })
      },
      //获取按钮
      obtainButton(){
        if (this.workPoolData[scope.$index]!=null) {
          let query={

          }
          demandObtain(query).then(res => {
            console.log('共公池', res.rows)
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows
              this.totalCount = res.total
              console.log('response', res.total)
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {

          })
        }else {
          alert("请先选中一行！")
        }

      },
      //处理按钮
      dealButton(){
          this.$router.push({
            path: '/customService/deal',
            isEmpty: false
          })
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.userId);

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
      //个人池
      // searchPersonHandle(){
      //   demandListAndPersonalPool().then(res => {
      //     console.log('个人池：',res)
      //     if (res != null && res.code === 200) {
      //       this.workPersonPoolData = res.rows
      //       this.totalPersonCount = res.total
      //       console.log('response',res.total)
      //       if (res.rows.length <= 0) {
      //         return this.$message.warning(
      //           "未查询到数据！"
      //         )
      //       }
      //     }
      //   }).catch(res => {
      //
      //   })
      // },
      //公共池查询
      searchHandle() {
        let query = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          service: this.sendForm.service,
          channel: this.sendForm.channel,
          Acceptor: this.sendForm.Acceptor,
          acceptorTime: this.sendForm.acceptorTime,
          Handler: this.sendForm.Handler,
          HandlerTime: this.sendForm.HandlerTime,
          workNumber: this.sendForm.workNumber,
          policyNumber: this.sendForm.policyNumber,
          secondNumber: this.sendForm.secondNumber,
          insuredName: this.sendForm.insuredName,
          beInsuredName: this.sendForm.beInsuredName,
          beInsuredNo: this.sendForm.beInsuredName,
          organization: this.sendForm.organization,
          appointmentTime:this.sendForm.appointmentTime,
          priority:this.sendForm.priority,
          vip:this.sendForm.vip,
          phone:this.sendForm.phone,
          state:this.sendForm.state
        }
        console.log("查询条件",query)
        demandListAndPublicPool(query).then(res => {
          console.log('共公池',res.rows)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
            console.log('response',res.total)
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
        demandListAndPersonalPool(query).then(res => {
          console.log('个人池：',res.rows)
          if (res != null && res.code === 200) {
            this.workPersonPoolData = res.rows
            this.totalPersonCount = res.total
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

      getRiskStatus(row) {
        return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
