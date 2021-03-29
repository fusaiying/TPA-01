<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span style="color:blue;">业务流转情况</span>
      <el-divider></el-divider>
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="flowLogData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style=" width: 100%;">
        <el-table-column align="center" prop="linkCode" label="状态" show-overflow-tooltip>
          <template slot-scope="scope" v-if="scope.row.linkCode">
            <span>{{ selectDictLabel(cs_link_code, scope.row.linkCode) }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="operateCode" label="操作" show-overflow-tooltip>
          <template slot-scope="scope" v-if="scope.row.operateCode">
            <span>{{ selectDictLabel(cs_action_type, scope.row.operateCode) }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="makeBy" label="受/处理人" show-overflow-tooltip/>
        <el-table-column align="center" prop="umNum" label="UM账号" show-overflow-tooltip/>
        <el-table-column prop="makeTime" label="时间" align="center" show-overflow-tooltip width="140">
          <template slot-scope="scope">
            <span>{{ scope.row.createdTime | changeDate }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link v-if="scope.row.operateCode=='03'" style="font-size:12px" type="primary"
                     @click="modifyDetails(scope.row)">修改说明
            </el-link>
          </template>
        </el-table-column>
        <modify-details ref="modifyDetails"></modify-details>
        <el-table-column prop="opinion" align="center" label="处理意见" show-overflow-tooltip/>
        <el-table-column prop="toDepartment" align="center" label="流转部门" show-overflow-tooltip/>
        <el-table-column prop="toReason" align="center" label="流传原因" show-overflow-tooltip/>
      </el-table>

      <pagination
        v-show="flowLogCount>0"
        :total="flowLogCount"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="searchFlowLog"
      />
    </div>
  </el-card>
</template>

<script>
  import moment from "moment";
  import {FlowLogSearch} from '@/api/customService/demand';
  import modifyDetails from "../modul/modifyDetails";

  let dictss = [
    {dictType: 'cs_identity'},
    {dictType: 'cs_sex'},
    {dictType: 'cs_communication_language'},
    {dictType: 'cs_service_item'},
    {dictType: 'cs_organization'},
    {dictType: 'cs_priority'},
    {dictType: 'cs_channel'},
    {dictType: 'cs_whether_flag'},
    {dictType: 'cs_link_code'},
    {dictType: 'cs_action_type'},
  ]
  export default {
    components: {
      modifyDetails,
    },
    name: "flowLogInfo",
    props: {
      routerParams: Object
    },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD HH:mm:ss')
        }
      }
    },
    data() {
      return {
        flowLogData: [],
        flowLogCount: 0,
        totalCount: 0,
        states: [],
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          workOrderNo: "",
          policyNo: "",
          policyItemNo: "",
          status: "",
        },
        dictList: [],
        cs_link_code: [],
        cs_action_type: [],
        cs_service_item: [],
        cs_communication_language: [],
        cs_organization: [],
      }
    },

    created() {
      this.queryParams.workOrderNo = this.$route.query.workOrderNo;
      this.queryParams.policyNo = this.$route.query.policyNo;
      this.queryParams.policyItemNo = this.$route.query.policyItemNo;
      this.queryParams.status = this.$route.query.status;
      //this.searchHandle()
      this.searchFlowLog()
    },
    async mounted() {// 字典数据统一获取
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      // 下拉项赋值
      this.cs_action_type = this.dictList.find(item => {
        return item.dictType === 'cs_action_type'
      }).dictDate
      this.cs_link_code = this.dictList.find(item => {
        return item.dictType === 'cs_link_code'
      }).dictDate
      this.cs_communication_language = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.cs_service_item = this.dictList.find(item => {
        return item.dictType === 'cs_service_item'
      }).dictDate
      this.cs_organization = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.cs_channel = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.cs_whether_flag = this.dictList.find(item => {
        return item.dictType === 'cs_whether_flag'
      }).dictDate
      this.cs_sex = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.cs_identity = this.dictList.find(item => {
        return item.dictType === 'cs_identity'
      }).dictDate
    },
    methods: {
      modifyDetails(s) {
          this.$refs.modifyDetails.queryParams.subId = s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo = this.queryParams.workOrderNo;
          this.$refs.modifyDetails.open()
        ;
      },
      searchFlowLog() {
        let workOrderNo = this.queryParams
        workOrderNo.workOrderNo=this.routerParams.workOrderNo
        workOrderNo.status = ""
        FlowLogSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            this.flowLogCount = res.total
            if (res.rows.length <= 0) {
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

</style>
