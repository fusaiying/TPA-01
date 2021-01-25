<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
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
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人生日：" prop="beBirthday">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>



        <div style="text-align: right; margin-right: 1px;">
          <el-button size="mini" type="primary" @click="searchHandle">查询</el-button>
          <el-button size="mini" type="primary" @click="acceptButton">跳过</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询结果（{{ totalCount }}）</span>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" prop="policyNumber" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center"  prop="secondNumber" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="center" label="姓名" show-overflow-tooltip/>
          <el-table-column prop="beInsuredName" align="center" label="性别 " show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="出生日期" show-overflow-tooltip/>
          <el-table-column prop="Acceptor" align="center" label="证件类型" show-overflow-tooltip/>
          <el-table-column prop="Handler" align="center" label="证件号码" show-overflow-tooltip/>
          <el-table-column prop="vip" align="center" label="手机" show-overflow-tooltip/>
          <el-table-column prop="priority" align="center" label="联系地址" show-overflow-tooltip/>
          <el-table-column align="center" label="处理" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="acceptButton(scope.row)">受理</el-button>
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
  import {demandListAndPublicPool,demandListAndPersonalPool} from '@/api/customService/demand'

  export default {
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {
      return {
        sendForm: {
          Service: "",
          channel: "",
          Acceptor: "",
          acceptorTime:"",
          Handler: "",
          HandlerTime: "",
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
        isinit: 'Y',
        totalCount: 0,
        states: [],
        sysUserOptions: [],
      }
    },
    created() {
      debugger;
      this.searchHandle()
      this.getDicts("sys_oper_type").then(response => {
        this.states = response.data;
        console.log("response:",response)
      });

    },

    methods: {
      //增加按钮
      acceptButton(row) {
        this.$router.push({
          path: '/customService/demand-editDetail',
          isEmpty: false
        })
      },
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      searchHandle() {
        debugger;
        let query = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          Service: this.sendForm.Service,
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
        debugger;
        console.log('query: ',query)
        demandListAndPublicPool(query).then(res => {
          console.log('------------: ',res)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
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
