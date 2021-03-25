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
            <el-form-item label="处理日期：" prop="handlerTime">
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
            <el-form-item label="预约日期：" prop="complaintTime">
              <el-date-picker
                v-model="sendForm.complaintTime"
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
            icon="el-icon-search" @click="searchHandles">查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
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
          v-if="isShow"
        >
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="workOrderButton(scope.row)">{{ scope.row.workOrderNo }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.itemCode">
              <span>{{ selectDictLabel(cs_service_item, scope.row.itemCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="complaintTime" label="预约日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.complaintTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="medicalInstitution" label="预约医院" show-overflow-tooltip/>
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
          <el-table-column prop="changeTime" label="修改时间" align="center" show-overflow-tooltip width="140">
            <!--<template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate }}</span>
            </template>-->
          </el-table-column>

          <el-table-column prop="createBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="modifyBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{ selectDictLabel(cs_vip_flag, scope.row.vipFlag) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip>
            <!--      如果没有配置字典数据会异常-->
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{ selectDictLabel(cs_organization, scope.row.organCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.status">
              <span>{{ selectDictLabel(cs_order_state, scope.row.status) }}</span>
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
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPersonPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange"
        >
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="dealButton(scope.row)">{{ scope.row.workOrderNo }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.itemCode">
              <span>{{ selectDictLabel(cs_service_item, scope.row.itemCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="complaintTime" label="预约日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.complaintTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="medicalInstitution" label="预约医院" show-overflow-tooltip/>
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
          <el-table-column prop="changeTime" label="修改时间" align="center" show-overflow-tooltip width="140">
            <!--<template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate }}</span>
            </template>-->
          </el-table-column>
          <el-table-column prop="createBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="oldmodifyBy" align="center" label="原处理人" show-overflow-tooltip/>
          <el-table-column prop="vipFlag" align="center" label="VIP标识" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.vipFlag">
              <span>{{ selectDictLabel(cs_vip_flag, scope.row.vipFlag) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip>
            <!--      如果没有配置字典数据会异常-->
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{ selectDictLabel(cs_organization, scope.row.organCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.status">
              <span>{{ selectDictLabel(cs_order_state, scope.row.status) }}</span>
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
import {
  demandListAndPublicPool,
  demandListAndPersonalPool,
  demandObtain,
  demandObtainMany,
  updateClickTime
} from '@/api/customService/reservation'
import secondPhone from "../common/modul/secondPhone";
import {selectCallAgain} from "@/api/customService/demand";

export default {
  components: {secondPhone},
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
    }
  },
  data() {
    return {
      showClass: "el-icon-arrow-right",//图表样式
      isShow: false,//控制是否显示个人池
      ids: [],//多选框
      open: "",//是否弹出
      title: "",//弹出框名称
      cs_service_item: [],//服务项目
      cs_channel: [],//渠道
      cs_organization: [],//出单机构
      cs_priority: [],//优先级
      cs_vip_flag: [],// vip标识
      cs_order_state: [],// 状态：
      secondPhone: [],
      riskCodes: [],
      dialogFormVisible: false,
      updateBy: undefined,
      sendForm: {//传值给后台
        pageNum: 1,
        pageSize: 10,
        itemCode: "",//服务信息
        channelCode: "",//受理渠道
        acceptBy: "",//受理人
        modifyBy: "",//处理人
        acceptorTime: [],//受理时间数组
        appointmentTime: [],//预约时间数组
        handlerTime: [],//处理时间数组
        workOrderNo: "",//工单编号
        policyNo: "",//保单号
        policyItemNo: "",//分单号
        holderName: "",//投保人
        insuredName: "",//被保人
        idNumber: "",//证件号
        organCode: "",//出单机构
        priorityLevel: "",//优先级
        vipFlag: "",//VIP标识
        mobilePhone: "",//移动电话
        status: "",//状态
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
      workPersonPoolData: [],
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
      isRemind:false,
    }
  },
  created() {
    this.searchSecondHandle();
    this.searchHandles();
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

    //查询是否存在未处理的call_again记录，并显示


  },

  methods: {
    //工单页面超链接
    workOrderButton(s) {
      this.$router.push({
        path: '/customService/reservation/orderDetail',
        query: {
          workOrderNo: s.workOrderNo,
          policyNo: s.policyNo,
          policyItemNo: s.policyItemNo,
          status: s.status
        }
      })
    },

    //修改按钮
    modifyButton(s) {
      this.$router.push({
        path: '/customService/reservation/modify',
        query: {
          workOrderNo: s.workOrderNo,
          policyNo: s.policyNo,
          policyItemNo: s.policyItemNo,
          status: s.status
        }
      })
    },
    //取消按钮
    cancleBytton(s) {
      this.$router.push({
        path: '/customService/reservation/cancle',
        query: {
          workOrderNo: s.workOrderNo,
          policyNo: s.policyNo,
          policyItemNo: s.policyItemNo,
          status: s.status
        }
      })
    },
    //获取按钮
    obtainButton(s) {
      if (s.workOrderNo == null && this.ids.length === 0) {
        this.$message.warning("请先选中一行！")
      } else {
        if (s.workOrderNo != null) {
          let workOrderNo = s.workOrderNo
          demandObtain(workOrderNo).then(res => {
            if (res != null && res.code === 200) {
              this.searchHandles();
            }
          }).catch(res => {

          })
        } else {
          const workOrderNos = this.ids
          console.log("ids:", workOrderNos)
          demandObtainMany(workOrderNos).then(res => {
            if (res != null && res.code === 200) {
              this.$message.success("批量获取成功");
              this.searchHandles();
            }
          }).catch(res => {

          })
        }

      }
    },
    //处理按钮
    dealButton(s) {
      //先保存点击处理时的时间
      //存在协办 转办后  默认都置灰
      let queryParams = JSON.parse(JSON.stringify(this.sendForm));
      queryParams.workOrderNo = s.workOrderNo;
      updateClickTime(queryParams).then(res => {
        if (res != null && res.code === 200) {
          //数据保存成功  跳转页面
          this.$router.push({
            path: '/customService/reservation/deal',
            query: {
              workOrderNo: s.workOrderNo,
              policyNo: s.policyNo,
              policyItemNo: s.policyItemNo,
              status: s.status
            }
          })
        }else{
          //数据保存成功  跳转页面
          this.$router.push({
            path: '/customService/reservation/deal',
            query: {
              workOrderNo: s.workOrderNo,
              policyNo: s.policyNo,
              policyItemNo: s.policyItemNo,
              status: s.status
            }
          })
        }
      }).catch(res => {
        console.log('error submit!!');
      })


    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.workOrderNo);

    },
    show() {
      if (this.isShow == false) {
        this.isShow = true
        this.showClass = "el-icon-arrow-down"
      } else {
        this.isShow = false
        this.showClass = "el-icon-arrow-right"

      }
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
      queryParams = JSON.parse(JSON.stringify(this.sendForm));
      if (this.sendForm.acceptorTime) {
        this.$set(queryParams,'acceptTimeStart',this.sendForm.acceptorTime[0]);
        this.$set(queryParams,'acceptTimeEnd',this.sendForm.acceptorTime[1]);
      }
      if(this.sendForm.complaintTime){
        this.$set(queryParams,'complaintTimeStart',this.sendForm.complaintTime[0]);
        this.$set(queryParams,'complaintTimeEnd',this.sendForm.complaintTime[1]);
      }

      demandListAndPublicPool(queryParams).then(res => {
        console.log('共公池', res.rows)
        if (res != null && res.code === 200) {
          this.workPoolData = res.rows
          this.totalCount = res.rows.length
          console.log('response', res.total)
          if (res.rows.length <= 0) {
            this.isRemind=true;
            console.info("预约公共池没有满足条件的数据！");
          }
        }
      }).catch(res => {

      })
    },
    //处理中查询
    searchHandle1() {
      let queryParams;
      queryParams = JSON.parse(JSON.stringify(this.sendForm));
      if (this.sendForm.acceptorTime) {
        this.$set(queryParams,'acceptTimeStart',this.sendForm.acceptorTime[0]);
        this.$set(queryParams,'acceptTimeEnd',this.sendForm.acceptorTime[1]);
      }
      if(this.sendForm.appointmentTime){
        this.$set(queryParams,'appointmentTimeStart',this.sendForm.appointmentTime[0]);
        this.$set(queryParams,'appointmentTimeEnd',this.sendForm.appointmentTime[1]);
      }

      demandListAndPersonalPool(queryParams).then(res => {
        console.log('个人池：', res.rows)
        if (res != null && res.code === 200) {
          this.workPersonPoolData = res.rows
          this.totalPersonCount = res.rows.length
          console.log('response', res.total)
          if (res.rows.length <= 0 && this.isRemind) {
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
      this.searchHandle();
      this.searchHandle1();
    },
    //二次来电查询
    searchSecondHandle() {
      selectCallAgain().then(res => {
        if (res != null && res.code === 200) {
          if (res.rows.length >= 1) {
            this.$refs.secondPhone.workPoolData = res.rows
            this.$refs.secondPhone.open()
          }
          if (res.rows.length <= 0) {
            console.info("没有再次来电数据！");
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
