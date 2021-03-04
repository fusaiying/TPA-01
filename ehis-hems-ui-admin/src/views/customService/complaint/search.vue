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
        </el-row>



        <div style="text-align: right; margin-right: 1px;">
          <el-button size="mini" type="primary" @click="searchHandle" disabled>查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">查询结果（{{ totalCount }}）</span>
        <div style="text-align: right; margin-right: 8px;">
          <el-button type="primary" size="mini" @click="relation" disabled>关联保单</el-button>
          <el-button type="primary" size="mini" @click="cancelRelation" disabled>解除关联</el-button>
          <el-button type="primary" size="mini" @click="hiddenShow">返回</el-button>
        </div>
        <el-divider></el-divider>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center"  prop="policyItemNo" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="center" label="姓名" show-overflow-tooltip/>
          <el-table-column prop="beInsuredName" align="center" label="性别 " show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="出生日期" show-overflow-tooltip/>
          <el-table-column prop="Acceptor" align="center" label="证件类型" show-overflow-tooltip/>
          <el-table-column prop="Handler" align="center" label="证件号码" show-overflow-tooltip/>
          <el-table-column prop="vip" align="center" label="手机" show-overflow-tooltip/>
          <el-table-column prop="priority" align="center" label="联系地址" show-overflow-tooltip/>
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
          return moment(value).format('YYYY-MM-DD HH:mm:ss')
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

    },

    methods: {
      //重置
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      //受理
      relation(s) {
        // this.$router.push({
        //   path: '/customService/complaint/addDetail',
        //   query:{
        //     policyNo:s.policyNo,
        //     policyItemNo:s.policyItemNo,
        //   }
        // })
      },
      //关闭按钮
      hiddenShow: function () {
        // 返回上级路由并关闭当前路由
        this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
        this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)

      },
      cancelRelation() {
      },
      searchHandle() {
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
