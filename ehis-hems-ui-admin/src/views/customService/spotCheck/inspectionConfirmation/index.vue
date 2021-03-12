<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="confirmationQueryForm" :model="confirmationQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="案件首次结案日期：" prop="firstEndCaseTime">
              <el-date-picker
                v-model="confirmationQueryForm.firstEndCaseTime"
                style="width:220px;"
                size="mini"
                type="daterange"
                value-format="yyyy-MM-dd"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="serviceItem">
              <el-select v-model="confirmationQueryForm.serviceItem" class="item-width" placeholder="请选择">
                <el-option v-for="item in service_itemOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工单号：" prop="workOrderNo">
              <el-input v-model="confirmationQueryForm.workOrderNo" class="item-width" clearable size="mini" placeholder="请输入"/>
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
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="inspectionPublicPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" width="140" prop="riskCode" label="工单号" show-overflow-tooltip/>
          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskNature" label="服务项目" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="结案日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateBy" align="center" label="质检分组" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="质检人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="质检结果" show-overflow-tooltip/>
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
import {selectConfirmationQuery} from '@/api/customService/spotCheck'

let dictss = [{dictType: 'cs_service_item'}]
export default {
  components: {
  },
  data() {
    return {
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      confirmationQueryForm: {
        firstEndCaseTime: undefined,
        serviceItem: undefined,
        workOrderNo: undefined
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      loading: true,
      workPoolData: [],
      isinit: 'Y',
      page: 1,
      totalCount: 0,
      finishPageSize: 10,
      changeSerchData: {},
      dictList: [],
      service_itemOptions: [],
    }
  },
  created() {
  },
  async mounted() {
    // 字典数据赋值
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    // 下拉项赋值
    this.service_itemOptions = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.searchHandle()
  },
  methods: {
    resetForm() {
      this.$refs.confirmationQueryForm.resetFields()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,

        firstEndCaseStartTime: undefined,
        firstEndCaseEndTime: undefined,
        serviceItemCode: this.confirmationQueryForm.serviceItem,
        workOrderNo: this.confirmationQueryForm.workOrderNo
      }
      if (this.confirmationQueryForm.firstEndCaseTime) {
        query.firstEndCaseStartTime = this.confirmationQueryForm.firstEndCaseTime[0]
        query.firstEndCaseEndTime = this.confirmationQueryForm.firstEndCaseTime[1]
      }
      selectConfirmationQuery(query).then(res => {
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
    send() {

    } AA
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
