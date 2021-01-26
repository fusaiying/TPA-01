<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="报案号：" prop="rptNo">
              <el-input v-model="searchForm.rptNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idNo">
              <el-input v-model="searchForm.idNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保险人：" prop="insuredName">
              <el-input v-model="searchForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="快递号：" prop="insuredName">
              <el-input v-model="searchForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交件人：" prop="idNo">
              <el-input v-model="searchForm.idNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="接单日期：" prop="endCaseDate">
              <el-date-picker
                v-model="searchForm.endCaseDate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构：" prop="whiteStatus">
              <el-input v-model="searchForm.whiteStatus" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="policyItemNo">
              <el-input v-model="searchForm.policyItemNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="

            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>追讨工作池</span>
          <span style="float: right;">
            <el-button type="primary" size="mini" :disabled="isListExport" @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="rptNo" label="报案号" show-overflow-tooltip/>
          <el-table-column align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
          <el-table-column align="center" prop="insuredName" label="被保险人" show-overflow-tooltip/>
          <el-table-column align="center" prop="insuredName" label="理赔材料" width="90" show-overflow-tooltip/>
          <el-table-column align="center" prop="idNo" label="备注" show-overflow-tooltip/>
          <el-table-column align="center" prop="treatmentEndDate" label="其他(案件去向)" show-overflow-tooltip/>
          <el-table-column align="center" prop="appntName" label="快递号" show-overflow-tooltip/>
          <el-table-column align="center" prop="contNo" label="接单日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="debtAmount" label="交件人" width="100" show-overflow-tooltip/>
          <el-table-column align="center" prop="colAmount" label="出单公司" show-overflow-tooltip/>
          <el-table-column align="center" prop="residualAmount" label="机构" width="100" show-overflow-tooltip/>
          <el-table-column align="center" prop="endCaseTime" label="操作人" show-overflow-tooltip/>
          <el-table-column align="center" prop="colStatus" label="状态"
                           show-overflow-tooltip/>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="">编辑</el-button>
              <el-button size="mini" type="text" @click="">保存</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination=""
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {listInfo} from '@/api/supplierManager/supplier'

  let dictss = [{dictType: 'linkman_type'}, {dictType: 'account_Types'}, {dictType: 'accountacc_status'}, {dictType: 'service_type'}, {dictType: 'supplier_type'},]
  export default {
    data() {
      return {
        isListExport:false,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        tableData:[],
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          province: undefined, // 省
          city: undefined, // 市
          district: undefined, // 区
          servcomType: undefined,
          chname: undefined,
          enname: undefined,
          bussinessStatus: undefined,
          serialNo: undefined
        },
        totalCount: 0,
        dictList: [],
        linkman_typeOptions: [],
        accountTypeOptions: [],
        accountacc_statusOptions: [],
        service_typeOptions: [],
        supplier_typeOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.linkman_typeOptions = this.dictList.find(item => {
        return item.dictType === 'linkman_type'
      }).dictDate
      this.accountTypeOptions = this.dictList.find(item => {
        return item.dictType === 'account_Types'
      }).dictDate
      this.accountacc_statusOptions = this.dictList.find(item => {
        return item.dictType === 'accountacc_status'
      }).dictDate
      this.service_typeOptions = this.dictList.find(item => {
        return item.dictType === 'service_type'
      }).dictDate
      this.supplier_typeOptions = this.dictList.find(item => {
        return item.dictType === 'supplier_type'
      }).dictDate

    },
    methods: {
      handleChange(value) {

      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      search() {

      },
      addSupplier(status) {

      }
      ,
      editClick(value, status) {

      },
      listExport(){

      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }

  /*element原有样式修改*/
  .el-form-item /deep/ label {
    font-weight: normal;
  }

</style>
