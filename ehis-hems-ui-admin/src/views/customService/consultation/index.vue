<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="工单号：" prop="workOrderNo">
              <el-input v-model="sendForm.workOrderNo" class="item-width" clearable size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="流转号：" prop="collaborativeId">
              <el-input v-model="sendForm.collaborativeId" class="item-width" clearable size="mini"/>
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
                <el-option v-for="item in cs_opinion_handle" :key="item.dictValue" :label="item.dictLabel"
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
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="dealButton(scope.row)">{{ scope.row.workOrderNo }}</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="collaborativeId" label="流转号" show-overflow-tooltip/>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.itemCode">
              <span>{{ selectDictLabel(cs_service_item, scope.row.itemCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip>
            <!--      如果没有配置字典数据会异常-->
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{ selectDictLabel(cs_organization, scope.row.organCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="createdBy" label="案件发起人" show-overflow-tooltip/>
          <el-table-column prop="status" align="handleState" label="处理状态" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.handleState">
              <span>{{ selectDictLabel(cs_opinion_handle, scope.row.handleState) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyTime" label="流转时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.modifyTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="acceptTime" label="处理时间" align="center" show-overflow-tooltip width="140">
            <template slot-scope="scope">
              <span>{{ scope.row.acceptTime | changeDate }}</span>
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
          :page.sync="sendForm.pageNum"
          :limit.sync="sendForm.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
import moment from 'moment'
import {consultationPool} from '@/api/customService/consultation'


let dictss = [
  {dictType: 'cs_organization'},
  {dictType: 'cs_service_item'},
  {dictType: 'cs_opinion_handle'},
]

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
      ids: [],//多选框
      open: "",//是否弹出
      title: "",//弹出框名称
      dictList:[],
      cs_service_item: [],//服务项目
      cs_organization: [],
      cs_opinion_handle: [],
      dialogFormVisible: false,
      updateBy: undefined,
      sendForm: {//传值给后台
        pageNum: 1,
        pageSize: 10,
        workOrderNo: "",//工单编号
        collaborativeId: "",//协办号码
        handlerTime: undefined,//时间
        handleState: "",
        policyNo: "",
        policyItemNo: "",
        itemCode: ""
      },
      workPoolData: [],//公共池
      totalCount: 0,
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
    this.cs_organization = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.cs_service_item = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.cs_opinion_handle = this.dictList.find(item => {
      return item.dictType === 'cs_opinion_handle'
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
          path: '/customService/consultation/deal',
          query: {
            workOrderNo: s.workOrderNo,
            collaborativeId: s.collaborativeId,
          }
        })
      } else {
        this.$router.push({
          path: '/customService/consultation/complaint',
          query: {
            workOrderNo: s.workOrderNo,
            collaborativeId: s.collaborativeId,
          }
        })
      }
    },
    resetForm() {
      this.$refs.sendForm.resetFields()
    },
    //池查询
    searchHandle() {
      let queryParams = JSON.parse(JSON.stringify(this.sendForm));
      if (this.sendForm.handlerTime) {
        queryParams.acceptTimeStart = this.sendForm.handlerTime[0];
        queryParams.acceptTimeEnd = this.sendForm.handlerTime[1];
      }
      consultationPool(queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.workPoolData = res.rows
          this.totalCount = res.total
          if (res.total <= 0) {
            return this.$message.warning(
              "未查询到数据！"
            );
          }
        }
      }).catch(res => {
        return this.$message.warning(
          "加载数据异常！"
        );
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
