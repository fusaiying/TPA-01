<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="批次号：" prop="rptNo">
              <el-input v-model="searchForm.rptNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交单日期：" prop="endCaseDate">
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
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              search
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>批次信息列表</span>
        </div>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="batchNo" label="批次号" show-overflow-tooltip/>
          <el-table-column align="center" prop="receiveDate" label="账号" show-overflow-tooltip/>
          <el-table-column align="center" prop="sendBy" label="账户名" show-overflow-tooltip/>
          <el-table-column align="center" prop="companyName" label="银行名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="dept" label="转账金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="dept" label="转账日期" show-overflow-tooltip/>
          <el-table-column align="center" prop="dept" label="退票转账失败日期" width="130" show-overflow-tooltip/>
          <el-table-column align="center" prop="updateBy" label="收据号" show-overflow-tooltip/>
          <el-table-column align="center" prop="updateBy" label="退票转账失败原因" width="130" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="">更新</el-button>
              <el-button size="mini" type="text" @click="">支付</el-button>
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

  let dictss = [{dictType: 'claim_material'}]
  export default {
    data() {
      return {
        isListExport: false,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },

        tableData:[
          {
            batchNo:'CS001'
          }
        ],
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          rptNo: '',
          idNo: '',
          name: '',
          expressNumber: '',
          sendBy: '',
          receiveDate: [],
          receiveStartDate: '',
          receiveEndDate: '',
          //机构
          //操作人
        },
        totalCount: 0,
        dictList: [],
        claim_materialOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claim_materialOptions = this.dictList.find(item => {
        return item.dictType === 'claim_material'
      }).dictDate
      /*  listNew(this.searchForm).then(res=>{
          if (res!=null && res.code===200){
            this.tableData=res.rows
            this.totalCount=res.total
          }
        }).catch(res=>{})*/
    },
    methods: {
      handleChange(value) {

      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      search() {

      },
      listExport() {

      },

    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

</style>
