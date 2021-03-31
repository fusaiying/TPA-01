<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="inspectionQueryForm" :model="inspectionQueryForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="结案日期：" prop="endCaseDate">
              <el-date-picker
                v-model="inspectionQueryForm.endCaseDate"
                style="width:220px;"
                size="mini"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="质检日期：" prop="inspectionDate">
              <el-date-picker
                v-model="inspectionQueryForm.inspectionDate"
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
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="inspectionQueryForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in organizationOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="质检人：" prop="updatedBy">
              <el-input v-model="inspectionQueryForm.updatedBy" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务项目：" prop="itemCode">
              <el-select v-model="inspectionQueryForm.itemCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in service_itemOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="质检状态：" prop="status">
              <el-select v-model="inspectionQueryForm.status" class="item-width" placeholder="请选择">
                <el-option v-for="item in inspection_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="质检结果：" prop="result">
              <el-select v-model="inspectionQueryForm.result" class="item-width" placeholder="请选择">
                <el-option v-for="item in inspection_resultOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
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
        <span style="float: right;">
          <el-dropdown @command="sendMany" style="padding-right: 10px">
            <el-button type="primary" size="mini" >质检详情清单导出<i class="el-icon-arrow-down el-icon--right"/></el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="01">信息需求</el-dropdown-item>
              <el-dropdown-item command="03">投诉</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

          <el-button type="primary" size="mini" @click="sendMany1" >质检差错清单导出</el-button>
        </span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-toolti>
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="dealButton(scope.row)">{{scope.row.workOrderNo}}</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="organCode" label="出单机构" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(organizationOptions, scope.row.organCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="itemCode" label="服务项目" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(service_itemOptions, scope.row.itemCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="endDate" label="结案日期" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.endDate | changeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updatedBy" align="center" label="质检分组" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>电话中心处理</span>
            </template>
          </el-table-column>
          <el-table-column prop="updatedBy" align="center" label="质检人" show-overflow-tooltip/>
          <el-table-column prop="updatedTime" align="center" label="质检日期" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{parseTime(scope.row.updatedTime, '{y}-{m}-{d}')}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="result" label="质检结果" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(inspection_resultOptions, scope.row.result)}}</span>
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
// import {selectProductQuery} from '@/api/insuranceRules/ruleDefin'
import {selectQualityVo} from '@/api/customService/spotCheck'

let dictss = [{dictType: 'cs_organization'}
  ,{dictType: 'cs_service_item'}
  ,{dictType: 'cs_inspection_state'}
  ,{dictType: 'cs_inspection_result'}
]
export default {
  components: {},
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    },

  },
  data() {
    return {
      caseNumber: false,//查询条件（报案号）是否显示
      // 查询参数
      inspectionQueryForm: {
        endCaseDate: [],
        inspectionDate: [],
        organCode: '',
        updatedBy: '',
        itemCode: '',
        status: '',
        result: ''
      },
      confirmationQueryForm: {
        firstEndCaseTime: undefined,
        serviceItem: undefined,
        workOrderNo: undefined,
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
      organizationOptions:[],
      service_itemOptions: [],
      inspection_statusOptions: [],
      inspection_resultOptions: [],
    }
  },
  created() {
    this.searchHandle()
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.organizationOptions = this.dictList.find(item => {
      return item.dictType === 'cs_organization'
    }).dictDate
    this.service_itemOptions = this.dictList.find(item => {
      return item.dictType === 'cs_service_item'
    }).dictDate
    this.inspection_statusOptions = this.dictList.find(item => {
      return item.dictType === 'cs_inspection_state'
    }).dictDate
    this.inspection_resultOptions = this.dictList.find(item => {
      return item.dictType === 'cs_inspection_result'
    }).dictDate
   // this.searchHandle()
  },
  methods: {
    resetForm() {
      this.inspectionQueryForm.endCaseDate=[]
      this.inspectionQueryForm.inspectionDate=[]
      this.inspectionQueryForm.result=''
      this.$refs.inspectionQueryForm.resetFields()
    },
    searchHandle() {
      let query = {
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,

        endCaseStartDate: undefined,
        endCaseEndDate: undefined,
        endCaseupdateTime: undefined,
        inspectionStartDate: undefined,
        inspectionEndDate: undefined,
        organCode: this.inspectionQueryForm.organCode,
        updatedBy: this.inspectionQueryForm.updatedBy,
        itemCode: this.inspectionQueryForm.itemCode,
        status: this.inspectionQueryForm.status,
        result: this.inspectionQueryForm.result

      }
      if (this.inspectionQueryForm.endCaseDate) {
        query.endCaseStartDate = this.inspectionQueryForm.endCaseDate[0]
        query.endCaseEndDate = this.inspectionQueryForm.endCaseDate[1]
      }
      if (this.inspectionQueryForm.inspectionDate) {
        query.inspectionStartDate = this.inspectionQueryForm.inspectionDate[0]
        query.inspectionEndDate = this.inspectionQueryForm.inspectionDate[1]
      }
      selectQualityVo(query).then(res => {
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
    //处理按钮
    dealButton(row){
      let data = encodeURI(
        JSON.stringify({
          workOrderNo: row.workOrderNo, //批次号
          inspectionId: row.inspectionId, //批次号
          businessType: row.businessType,
          inspectionHandlerId: row.inspectionHandlerId,
          node:'mistake',//质检工作池
          status:'show'
        })
      )
      console.info("handleOne:"+data)
      this.$router.push({
        path: '/workOrder/inspectionHandle',
        query: {
          data
        }
      })

    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.workOrderNo);

    },
    //质检详情清单导出
    sendMany(query){
      alert(query)
      console.log(this.inspectionQueryForm.endCaseDate)

    const params={
        endCaseStartDate: '',
        endCaseEndDate:'',
        inspectionStartDate: '',
        inspectionEndDate: '',
        organCode: '',
        updatedBy: '',
        itemCode: '',
        status: '',
        result: ''
    }
if(this.inspectionQueryForm.endCaseDate!=null && this.inspectionQueryForm.endCaseDate!=''){
      if(this.inspectionQueryForm.endCaseDate.length>0){
        params.endCaseStartDate=this.inspectionQueryForm.endCaseDate[0]
        params.endCaseEndDate=this.inspectionQueryForm.endCaseDate[1]
      }
}
      if(this.inspectionQueryForm.inspectionDate!=null && this.inspectionQueryForm.inspectionDate!=''){
        if(this.inspectionQueryForm.inspectionDate.length>0){
          params.inspectionStartDate=this.inspectionQueryForm.inspectionDate[0]
          params.inspectionEndDate=this.inspectionQueryForm.inspectionDate[1]
        }
      }
      params.organCode=this.inspectionQueryForm.organCode
      params.updatedBy=this.inspectionQueryForm.updatedBy
      params.itemCode=this.inspectionQueryForm.itemCode
      params.status=this.inspectionQueryForm.status
      params.result=this.inspectionQueryForm.result

    if(query=='01'){
      //调用信息需求质检详情导出的接口
      this.download('cs/spotCheck/internal/selectWorkOrder/exportOneInformation', params, `质检查询_${new Date().getTime()}.xlsx`)
    }
    else if (query=='03') {
      this.download('cs/spotCheck/internal/selectWorkOrder/exportOne', params, `质检查询_${new Date().getTime()}.xlsx`)
    }
/*
      this.download('cs/spotCheck/internal/selectWorkOrder/exportOne', params, `质检查询_${new Date().getTime()}.xlsx`);*/
    },
    //质检差错清单导出
    sendMany1(){
      const params = {
        firstEndCaseStartTime: undefined,
        firstEndCaseEndTime: undefined,
        workOrderNo :this.confirmationQueryForm.workOrderNo,
        serviceItemCode: this.confirmationQueryForm.serviceItem,
      };
      this.download('cs/spotCheck/internal/selectWorkOrder/exportTwo', params, `质检差错_${new Date().getTime()}.xlsx`);
    },
    send() {

    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
