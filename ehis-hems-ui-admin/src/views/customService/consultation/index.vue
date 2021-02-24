<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：" prop="workOrderNo">
              <el-input v-model="sendForm.workOrderNo" class="item-width" clearable size="mini"  />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="流转号：" prop="collaborativeId">
              <el-input v-model="sendForm.collaborativeId" class="item-width" clearable size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="流转日期：" prop="handlerTime">
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
            <el-form-item label="任务状态：" prop="handleState">
              <el-select v-model="sendForm.handleState" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_handle_state" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
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
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="sendForm.itemCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in cs_service_item" :key="item.dictValue" :label="item.dictLabel"
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
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">查询结果（{{ totalCount }}）</span>

        <el-divider style=""/>
        <el-table
          ref="multipleTable"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-tooltip>
            <template slot-scope="scope" class="link-type">
              <span  @click="workOrderButton(scope.row)" a style="color: #3CB4E5;text-decoration: underline" href=" " >{{scope.row.workOrderNo}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="collaborativeId" label="流转号" show-overflow-tooltip/>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip/>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column align="center" prop="createdBy" label="案件发起人" show-overflow-tooltip/>
          <el-table-column prop="status" align="handleState" label="处理状态" show-overflow-tooltip/>
          <el-table-column prop="modifyTime" label="流转时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="acceptTime" label="处理时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="policyNo" label="处理时效（工作日）" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope" v-if="scope.row.status!='02'">
              <el-button size="mini" type="text" @click="dealButton(scope.row)">处理</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="pageNum"
          :limit.sync="pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
  import moment from 'moment'
  import {consultationPool} from '@/api/customService/consultation'

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
        ids:[],//多选框
        open:"",//是否弹出
        title:"",//弹出框名称
        cs_service_item:[],//服务项目
        cs_handle_state:[],// 状态：
        riskCodes:[],
        dialogFormVisible: false,
        updateBy: undefined,

        sendForm: {//传值给后台
          pageNum: 1,
          pageSize: 10,
          workOrderNo: "",//工单编号
          collaborativeId:"",//协办号码
          handlerTime:"",//时间
          handleState:"",
          policyNo:"",
          policyItemNo:"",
          itemCode:""
        },
        workPoolData: [],//公共池
        pageNum: 1,
        pageSize: 10,
        workPoolData:[],
        pageNumPerson: 1,
        pageSizePerson: 10,
        totalCount: 0,
        totalPersonCount: 0,
        changeSerchData: {},
        sysUserOptions: [],
      }
    },
    created() {
      this.searchHandle()
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
      this.getDicts("cs_handle_state").then(response => {
        this.cs_handle_state = response.data;
      });


    },

    methods: {

      //工单页面超链接
      workOrderButton(s){
        if (s.businessType=="01"){
          this.$router.push({
            path: '/customService/orderDetails',
            query:{
              workOrderNo:s.workOrderNo,
              policyNo:s.policyNo,
              policyItemNo:s.policyItemNo,
              status:s.status
            }
          })
        }else {
          this.$router.push({
            path: '/customService/complaint/orderDetails',
            query:{
              workOrderNo:s.workOrderNo,
              policyNo:s.policyNo,
              policyItemNo:s.policyItemNo,
              status:s.status
            }
          })

        }

      },

      //处理按钮
      dealButton(s){
      if (s.businessType=="01"){
          this.$router.push({
            path: '/customService/consultation/deal',
            query:{
              workOrderNo:s.workOrderNo,
              collaborativeId:s.collaborativeId,
            }
          })
     }
      else {
        this.$router.push({
          path: '/customService/consultation/complaint',
          query:{
            workOrderNo:s.workOrderNo,
            collaborativeId:s.collaborativeId,

          }
        })
         }
      },
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      //池查询
      searchHandle() {
        let queryParams;
        if (this.sendForm.handlerTime.length > 0) {
          queryParams = JSON.parse(JSON.stringify(this.sendForm));
          queryParams.acceptTimeStart=this.sendForm.handlerTime[0]
          queryParams.acceptTimeEnd=this.sendForm.handlerTime[1]
        } else {
          queryParams = this.sendForm;
        }
        consultationPool(queryParams).then(res => {
          console.log('个人池：',res.rows)
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

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
