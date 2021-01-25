<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="poolQueryForm" :model="poolQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="结案日期：" prop="endCaseTime">
              <el-date-picker
                v-model="poolQueryForm.updateTime"
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
            <el-form-item label="出单机构：" prop="status">
              <el-select v-model="poolQueryForm.riskStatus" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理人：" prop="updateBy">
              <el-input v-model="poolQueryForm.updateBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="serviceItem">
              <el-select v-model="poolQueryForm.service_itemOptions" class="item-width" placeholder="请选择">
                <el-option v-for="item in service_itemOptions" :key="item.dictValue" :label="item.dictLabel"
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
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>待质检（{{ totalCount }}）</span>
        <span style="float: right;">
           <el-button type="primary" size="mini" @click="sendMany">获取</el-button>
        </span>
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
          <el-table-column align="center" prop="riskNature" label="服务项目" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="结案日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateBy" align="center" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="sendOne(scope.row)">获取</el-button>
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
        <span>质检中（{{ totalCount }}）</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="inspectionPersonalPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column align="center" width="140" prop="riskCode" label="工单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskNature" label="服务项目" show-overflow-tooltip/>

          <el-table-column prop="organCode" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="结案日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="sendOne(scope.row)">处理</el-button>
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
import {inspectionListAndPublicPool,inspectionListAndPersonalPool} from '@/api/customService/spotCheck'

let dictss = [{dictType: 'cs_service_item'}]
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
      dialogFormVisible: false,
      updateBy: undefined,
      poolQueryForm: {
        updateBy: undefined,
        status: undefined,
        serviceItem: undefined,
        endCaseTime: undefined,
      },
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      loading: true,
      inspectionPublicPoolData: [],
      inspectionPersonalPoolData: [],
      isinit: 'Y',
      totalCount: 0,
      dataonLineListSelections: [],
      service_itemOptions: [],
    }
  },
  // mounted:在模板渲染成html后调用，通常是初始化页面完成后，再对html的dom节点进行一些需要的操作。
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      console.log('response: ', dictss, response)
      this.dictList = response.data
    })
    // 下拉项赋值
    this.service_itemOptions = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.searchHandle()

    let queryPersonal = {
      pageNum: this.queryParams.pageNum,
      pageSize: this.queryParams.pageSize,
      workNumber: "1"
    }

    inspectionListAndPersonalPool(queryPersonal).then(res => {
      console.log('------------: ', res)
      if (res != null && res.code === 200) {
        this.inspectionPersonalPoolData = res.rows
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
  methods: {
    resetForm() {
      this.$refs.poolQueryForm.resetFields()
      //加载默认数据
      // this.searchHandle()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        serviceItemCode: this.poolQueryForm.serviceItem,
        channelCode: this.poolQueryForm.channel,
        acceptorName: this.poolQueryForm.acceptorName,
        endCaseStartTime: undefined,
        endCaseEndTime: undefined
      }
      if (this.poolQueryForm.acceptorTime) {
        query.endCaseStartTime = this.poolQueryForm.endCaseTime[0]
        query.endCaseEndTime = this.poolQueryForm.endCaseTime[1]
      }
      console.log('query: ', query)
      inspectionListAndPublicPool(query).then(res => {
        console.log('------------: ', res)
        if (res != null && res.code === 200) {
          this.inspectionPublicPoolData = res.rows
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
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
