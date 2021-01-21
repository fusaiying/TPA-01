<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="insuredName">
              <el-input v-model="searchForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="cardType">
              <el-select v-model="searchForm.cardType" class="item-width" placeholder="请选择">
                <el-option label="一" value="1"/>
                <el-option label="二" value="2"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="cardType">
              <el-date-picker
                v-model="searchForm.cxrq"
                class="item-width"
                type="date"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
          <el-row>
            <el-col :span="8">
              <el-form-item label="被保人姓名：" prop="idCard">
                <el-input v-model="searchForm.idCard" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件号码：" prop="phone">
                <el-input v-model="searchForm.phone" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受理日期：" prop="cardType">
                <el-date-picker
                  v-model="searchForm.slrq"
                  class="item-width"
                  type="daterange"
                  range-separator="~"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-col :span="8">
            <el-form-item label="操作类型：" prop="cardType">
              <el-select v-model="searchForm.cardType" class="item-width" placeholder="请选择">
                <el-option label="一" value="1"/>
                <el-option label="二" value="2"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName">
          <el-tab-pane :label="label.label01" name="01">
            <claimsTable :table-data="pendingTableData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="label.label03" name="03">
            <claimsTable :table-data="completedTableData" :status="activeName"/>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <el-pagination
          :total="activeName==='01'?total:finishTotal"
          :current-page="activeName==='01'?page:finishPage"
          :page-sizes="pageSizes"
          :page-size="activeName==='01'?pageSize:finishPageSize"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes,jumper"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import claimsTable from './components/claimsTable'
export default {
  components: {
    claimsTable
  },
  data() {
    return {
      page: 1,
      total: 0,
      finishTotal: 0,
      pageSize: 5,
      finishPage: 1,
      finishPageSize: 5,
      pageSizes: [5, 10, 20, 30, 50, 100],
      activeName: '01',
      label: {
        label01: '处理中(0)',
        label03: '已处理(0)'
      },
      completedTableData: [
        { taskno: 'WEF2344R5' }
      ],
      pendingTableData: [
        { taskno: 'JI23454R5' }
      ],
      searchForm: {
        slrq: []
      }
    }
  },
  methods: {
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {},
    multiScanHandle() {},
    pageChange(val) {
      this.page = val
      this.activeName === '01' ? this.page = val : this.finishPage = val
    },
    sizeChange(val) {
      this.activeName === '01' ? this.pageSize = val : this.finishPageSize = val
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
</style>
