<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="poolQueryForm" :model="poolQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="结案日期：" prop="endCaseTime">
              <el-date-picker
                v-model="poolQueryForm.endCaseTime"
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
            <el-form-item label="出单机构：" prop="organization">
              <el-select v-model="poolQueryForm.organization" class="item-width" placeholder="请选择">
                <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
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
              <el-select v-model="poolQueryForm.serviceItem" class="item-width" placeholder="请选择">
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


    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>待质检（{{ publicTotalCount }}）</span>
        <span style="float: right;">
           <el-button :loading="getLoading" type="primary" size="mini" @click="getMore">获取</el-button>
        </span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="inspectionPublicPoolData"
          size="small"
          v-loading = "publicLoading"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-tooltip/>
          <el-table-column prop="businessService" label="服务项目" min-width="160" align="center"show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.businessService">
              <span>{{scope.row.businessService.split('-')[0]+'-'+selectDictLabel(service_itemOptions, scope.row.businessService.split('-')[1])}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organization" label="出单机构" min-width="160" align="center"  show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{selectDictLabel(organizationOptions, scope.row.organCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyUser.umCode" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="结案日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateBy" align="center" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="getOne(scope.row)">获取</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="publicTotalCount>0"
          :total="publicTotalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchForm"
        />
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>质检中（{{ personTotalCount }}）</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="inspectionPersonalPoolData"
          size="small"
          v-loading = "personLoading"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-tooltip/>
          <el-table-column prop="businessService" label="服务项目" min-width="160" align="center"show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.businessService">
              <span>{{scope.row.businessService.split('-')[0]+'-'+selectDictLabel(service_itemOptions, scope.row.businessService.split('-')[1])}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="organization" label="出单机构" min-width="160" align="center"  show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.organCode">
              <span>{{selectDictLabel(organizationOptions, scope.row.organCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="modifyUser.umCode" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="结案日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="status" align="center" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleOne(scope.row)">处理</el-button>
            </template>
          </el-table-column>

        </el-table>

        <pagination
          v-show="personTotalCount>0"
          :total="personTotalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchForm"
        />
      </div>
    </el-card>

  </div>
</template>

<script>
import moment from 'moment'
import {
  inspectionListAndPublicPool,
  inspectionListAndPersonalPool,
  inspectionGetByIds
} from '@/api/customService/spotCheck'

let dictss = [{dictType: 'cs_service_item'}
  ,{dictType: 'cs_organization'}
]
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
        endCaseTime: undefined,
        organization: undefined,
        updateBy: undefined,
        serviceItem: undefined,
      },
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      publicLoading: false,
      personLoading: false,
      inspectionPublicPoolData: [],
      inspectionPersonalPoolData: [],
      isinit: 'Y',
      publicTotalCount: 0,
      personTotalCount: 0,
      getLoading: false,
      dataonLineListSelections: [],

      dictList: [],
      service_itemOptions:[],
      organizationOptions:[],
      //是否提示
      isRemind:true,
    }
  },
  // mounted:在模板渲染成html后调用，通常是初始化页面完成后，再对html的dom节点进行一些需要的操作。
  async mounted() {

    await this.getDictsList(dictss).then(response => {
      console.log('response: ', dictss, response)
      this.dictList = response.data
    })
    this.service_itemOptions = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.organizationOptions = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.isRemind=false
    this.searchHandle()
  },
  methods: {
    resetForm() {
      this.$refs.poolQueryForm.resetFields()
      this.isRemind=false
      this.searchHandle()
    },
    searchForm() {
      this.isRemind=true
      this.searchHandle()
    },
    searchHandle() {
      this.publicLoading=true;
      this.personLoading=true;
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
        serviceItemCode: this.poolQueryForm.serviceItem,
        organCode: this.poolQueryForm.organization,
        acceptorName: this.poolQueryForm.acceptorName,
        endCaseStartTime: undefined,
        endCaseEndTime: undefined
      }
      if (this.poolQueryForm.endCaseTime) {
        query.endCaseStartTime = this.poolQueryForm.endCaseTime[0]
        query.endCaseEndTime = this.poolQueryForm.endCaseTime[1]
      }
      console.log('query: ', query)
      inspectionListAndPublicPool(query).then(res => {
        console.log('------------: ', res)
        if (res != null && res.code === 200) {
          this.inspectionPublicPoolData = res.rows
          this.publicTotalCount = res.total
          this.publicLoading=false;
          if (res.rows.length <= 0 && this.isRemind) {
            return this.$message.warning(
              "未查询到待质检数据！"
            )
          }
        }
      }).catch(res => {
      }).finally(() => {
        this.publicLoading=false;
      }),
      inspectionListAndPersonalPool(query).then(res => {
        console.log('------------: ', res)
        if (res != null && res.code === 200) {
          this.inspectionPersonalPoolData = res.rows
          this.personTotalCount = res.total
          this.personLoading=false;
          if (res.rows.length <= 0 && this.isRemind) {
            return this.$message.warning(
              "未查询到质检中数据！"
            )
          }
        }
      }).catch(res => {
      }).finally(() => {
        this.personLoading=false;
      })
    },
    handleSelectionChange(val) {
      this.dataonLineListSelections = val
    },
    getMore(){
      this.getLoading = true
      if (this.dataonLineListSelections.length < 1) {
        this.$message({ message: '请选择待质检的记录，进行批量获取操作', type: 'warning' })
        this.getLoading = false
        return
      }
      //后台请求参数处理
      let workOrderNos=[]
      for (let i = 0; i < this.dataonLineListSelections.length; i++) {
        workOrderNos.push(this.dataonLineListSelections[i].workOrderNo)
      }
      console.log(workOrderNos);
      if (workOrderNos.length<1){
        this.getLoading = false
        return this.$message.warning(
          "请选择待质检的记录，进行批量获取操作"
        )
      }else {
        //后台方法调用及返回结果处理
        this.getHandle(workOrderNos)
      }
    },
    //获取
    getOne(row){
      let workOrderNos=[row.workOrderNo]
      //后台方法调用及返回结果处理
      this.getHandle(workOrderNos)
    },
    getHandle(ids){
      inspectionGetByIds(ids).then(res=>{
        if (res!=null && res.code===200){
          //数据重新加载
          this.isRemind=false
          this.searchHandle();
          this.$message({
            message: '获取质检成功！',
            type: 'success',
            center: true,
            showClose: true
          })
        }
        this.getLoading = false

      }).catch(res => {
        this.$message.error('获取质检失败！')
        this.getLoading = false
      })
    },
    //处理
    handleOne(row){
      this.$message({
        message: '将要处理工单【'+row.workOrderNo+"】....",
        type: 'success',
        center: true,
        showClose: true
      })
      let data = encodeURI(
        JSON.stringify({
          workOrderNo: row.workOrderNo, //批次号
          policyNo: row.policyNo, //保单号
          policyItemNo: row.policyItemNo //分单号
        })
      )
      this.$router.push({
        path: '/claims-handle/presenting-edit',
        query: {
          data
        }
      })
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
