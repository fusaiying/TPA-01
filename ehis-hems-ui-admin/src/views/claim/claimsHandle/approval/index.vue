<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimNo">
              <el-input v-model="formSearch.claimNo" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="formSearch.applicationsource" class="item-width" placeholder="请选择">
                <el-option
                  v-for="(item,index) in applicationsourceArr"
                  :key="index"
                  :label="item.label"
                  :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventDate">
              <el-date-picker
                v-model="formSearch.eventDate"
                style="width: 220px;"
                type="date"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="insuredName">
              <el-input v-model="formSearch.insuredName" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardNo">
              <el-input v-model="formSearch.idcardNo" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="daterange">
              <el-date-picker
                v-model="formSearch.daterange"
                style="width: 220px;"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoading"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-tabs v-model="activeName">
        <el-tab-pane :label="`查询结果(${pendingTotal})`" name="01">
          <claimsTable ref="child" :table-data="pendingTableData" :status="activeName" @openTable="openTable" @getBaseform = "getBaseform" @resetTable="resetTable"/>
        </el-tab-pane>
        <el-tab-pane :label="`已处理(${completedTotal})`" name="02">
          <claimsTable :table-data="completedTableData" :status="activeName"/>
        </el-tab-pane>
      </el-tabs>
      <!--分页组件-->
      <el-pagination
        :total="activeName==='01'?pendingTotal: completedTotal"
        :current-page="activeName==='01'?pendPageInfo.page:completePageInfo.page"
        :page-size="activeName==='01'?pendPageInfo.pageSize:completePageInfo.pageSize"
        :page-sizes="[5,10, 20, 30, 50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"/>
    </el-card>
    <el-card v-show="showElement" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>补材信息</span>
        <el-button style="float: right;" size="mini" type="primary" @click="fillingsHandle">发起补材</el-button>
      </div>
      <el-form ref="tableForm" :rules="tableFormRules" :model="baseForm" style="padding-bottom: 30px;" size="mini">
        <el-row>
          <el-col :span="20">
            <el-form-item label="推送方式：" prop="checked">
              <el-checkbox-group v-model="baseForm.checked">
                <el-checkbox v-for="item in dict.supplementmode" :key="item.value" :label="item.value">{{ item.label }}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="材料说明：" prop="description" label-width="92px">
              <el-input :rows="2" v-model="baseForm.description" type="textarea" placeholder="请输入内容" maxlength="500" show-word-limit />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-table
            ref="table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.tableData"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" align="center"/>
            <el-table-column label="材料类型" prop="materialname" align="center"/>
            <el-table-column label="提交时间" prop="receiveddate" align="center"/>
            <el-table-column label="原件数量" prop="originals" align="center"/>
            <el-table-column label="复印件数量" prop="returned" align="center"/>
          </el-table>
        </el-row>
      </el-form>
    </el-card>
<!--    <fillsModul v-model="fillsDia" />-->
  </div>
</template>
<script>
import claimsTable from './components/claimsTable'
import fillsModul from '../common/modul/fills'
import { getapprovalList, addClosingSupplement } from '@/api/approval.js'
export default {
  dicts: ['material', 'apply_sourcetype', 'supplementmode'],
  components: {
    claimsTable,
    fillsModul
  },
  data() {
    return {
      fillsDia: false,
      formSearch: {
        daterange: [],
        claimNo: '',
        idcardNo: '',
        applicationsource: '',
        eventDate: '',
        insuredName: '',
        idcardNo: ''
      },
      claimno: '',
      applicationsourceArr: [],
      paramsList: [],
      showElement: false,
      activeName: '01',
      pendingTableData: [],
      completedTableData: [],
      pendingTotal: 0,
      completedTotal: 0,
      pendPageInfo: {
        page: 1,
        pageSize: 10
      },
      completePageInfo: {
        page: 1,
        pageSize: 10
      },
      baseForm: {
        checked: [],
        tableData: [],
        description: ''
      },
      changeSerchData: {},
      tableFormRules: {
        type: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        comCode: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        num: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        reason: [{ required: true, message: '不能为空!', trigger: 'change' }],
        mark: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        checked: [{ required: true, message: '不能为空!', trigger: 'change' }],
        description: [{ required: true, message: '不能为空!', trigger: 'blur' }]
      },
      getSex(row, col) {
        return this.dict.label.sex[row.sex]
      },
      getmaterialType(row, col) {
        return this.dict.label.material[row.materialtype]
      },
      searchLoading: false
    }
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.pendingTotal === 0 && newVal.completedTotal > 0) {
        this.activeName = '02'
      } else {
        this.activeName = '01'
      }
    }
  },
  computed: {
    totalChange() {
      const {pendingTotal, completedTotal} = this
      return {pendingTotal, completedTotal}
    }
  },
  mounted() {
    this.getApproval('02')
    // this.getApproval('01')
    // this.searchHandle()
    this.getApplicationsource()
  },
  methods: {
    searchHandle() {
      this.changeSerchData.claimNo = this.formSearch.claimNo
      this.changeSerchData.idcardNo = this.formSearch.idcardNo
      this.changeSerchData.applicationsource = this.formSearch.applicationsource
      this.changeSerchData.eventDate = this.formSearch.eventDate
      this.changeSerchData.insuredName = this.formSearch.insuredName
      this.changeSerchData.daterange = this.formSearch.daterange
      this.pendPageInfo.page = 1
      this.completePageInfo.page = 1
      this.pendPageInfo.pageSize = 10
      this.completePageInfo.pageSize = 10
      this.pendingTotal = null
      this.completedTotal = null
      this.pendingTotal = 0
      this.completedTotal = 0
      this.getApproval('02')
      this.getApproval('01')
      // this.getApproval(this.activeName)
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    resetTable() {
      this.$refs.tableForm.resetFields()
      this.$refs.table.clearSelection()
    },
    sizeChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.pageSize = val
        this.getApproval(this.activeName)
      } else {
        this.completePageInfo.pageSize = val
        this.getApproval(this.activeName)
      }
    },
    pageChange(val) {
      if (this.activeName === '01') {
        this.pendPageInfo.page = val
        this.getApproval(this.activeName)
      } else {
        this.completePageInfo.page = val
        this.getApproval(this.activeName)
      }
    },
    getApplicationsource(row, col) {
      return this.applicationsourceArr = this.dict.apply_sourcetype || []
    },
    fillingsHandle() {
      this.$refs.tableForm.validate((valid) => {
        if (valid) {
          if (!this.paramsList.length) {
            this.$message({
              message: '未选择补材内容或方式',
              type: 'warning'
            })
          } else {
            const params = {}
            let list = []
            const _this = this
            list = this.paramsList.map((item, index) => {
              delete item.creator
              return item
            })
            params.claimno = this.claimno
            params.description = this.baseForm.description || ''
            params.supplementarymode = this.baseForm.checked || []
            params.materialRequestVos = list || []
            addClosingSupplement(params).then(res => {
              this.$message({
                message: '结案补材发起成功！',
                type: 'success'
              })
              this.getApproval('02')
              this.getApproval('01')
              this.$refs.child.clearRadio()
              this.showElement = false
              this.$refs.tableForm.resetFields()
            })
          }
        } else {
          this.$message({
            message: '未选择补材内容或方式',
            type: 'warning'
          })
          return false
        }
      })
      // this.fillsDia = true
    },
    openTable() {
      this.showElement = true
    },
    handleSelectionChange(val) {
      this.paramsList = val
    },
    getBaseform(data) {
      this.claimno = data[0].claimno || ''
      this.baseForm.tableData = data[0].res || []
      // this.$refs.tableForm.resetFields()
    },
    getApproval(no) {
      const params = {}
      params.acceptStartDate = this.changeSerchData.daterange ? this.changeSerchData.daterange[0] : ''
      params.acceptendDate = this.changeSerchData.daterange ? this.changeSerchData.daterange[1] : ''
      params.applicationSource = this.changeSerchData.applicationsource
      params.claimNo = this.changeSerchData.claimNo
      params.eventDate = this.changeSerchData.eventDate
      params.idcardNo = this.changeSerchData.idcardNo
      params.insuredName = this.changeSerchData.insuredName
      params.pageno = no === '01' ? this.pendPageInfo.page : this.completePageInfo.page
      params.pagesize = no === '01' ? this.pendPageInfo.pageSize : this.completePageInfo.pageSize
      params.tabno = no
      this.searchLoading = true
      getapprovalList(params).then(res => {
        if (res.data) {
          if (res.data.errCode !== '' || res.data.errCode !== undefined) {
            if (no === '01') {
              this.$message({ message: '查询待处理结果错误！', type: 'error' })
            } else {
              this.$message({ message: '查询已处理结果错误！', type: 'error' })
            }
          }
        } else {
          const data = res.list || []
          if (no === '01') {
            this.pendingTableData = data
            this.pendingTableData.forEach((item, ind) => {
              item.id = ind
            })
            // this.pendingTotal = data.length || 0
            this.pendingTotal = res.total || 0
          } else {
            this.completedTableData = data
            this.completedTableData.map((item) => {
              if (item.state == '' || item.state == null) {
                item.approvalStatus = '待审批'
              }
              if ((item.state == '' || item.state == null) && item.conclusion ==1 ) {
                item.approvalStatus = '审批不通过'
              }
              if ((item.state == '01') && item.conclusion == 0) {
                item.approvalStatus = '待补材'
              }
              // if ((item.state == '02') && item.conclusion == 0) {
              //   item.approvalStatus = '已完成'
              // }
              if (item.state == '02') {
                item.approvalStatus = '已完成'
              }
              return item
            })
            // this.completedTotal = data.length || 0
            this.completedTotal = res.total || 0
          }
        }
        this.searchLoading = false
      })
    },
    choseActive() {
      if (this.activeName === '02') {
        this.showElement = false
        this.$refs.child.clearRadio()
        // this.$refs.tableForm.resetFields()
      }
      // } else {
      //   this.showElement = true
      // }
      this.getApproval(this.activeName)
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .row-box {
    padding-top: 20px;
    border-bottom: 1px solid #e6ebf5;
    margin-bottom: 20px;
    line-height: 50px;
  }
</style>
