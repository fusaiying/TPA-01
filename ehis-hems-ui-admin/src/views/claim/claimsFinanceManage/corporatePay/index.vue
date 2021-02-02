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
            <el-form-item label="医院名称：" prop="idNo">
              <el-select v-model="searchForm.idNo" class="item-width" placeholder="请选择"
                         @change="">
                <!-- <el-option v-for="option in claimTypeOptions" :key="option.dictValue"
                            :label="option.dictLabel"
                            :value="option.dictValue"/>-->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否申述：" prop="name">
              <el-select v-model="searchForm.name" class="item-width" placeholder="请选择"
                         @change="">
                <!-- <el-option v-for="option in claimTypeOptions" :key="option.dictValue"
                            :label="option.dictLabel"
                            :value="option.dictValue"/>-->
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
          <el-col :span="8">
            <el-form-item label="交单机构：" prop="whiteStatus">
              <el-select v-model="searchForm.whiteStatus" class="item-width" placeholder="请选择"
                         @change="">
                <!-- <el-option v-for="option in claimTypeOptions" :key="option.dictValue"
                            :label="option.dictLabel"
                            :value="option.dictValue"/>-->
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
            <el-table-column align="center" prop="receiveDate" label="医院名称" show-overflow-tooltip/>
            <el-table-column align="center" prop="sendBy" label="批次案件总数" width="110" show-overflow-tooltip/>
            <el-table-column align="center" prop="companyName" label="账单总金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="dept" label="理赔总金额" width="100" show-overflow-tooltip/>
            <el-table-column align="center" prop="dept" label="是否申述" width="100" show-overflow-tooltip/>
            <el-table-column align="center" prop="dept" label="交单机构" width="100" show-overflow-tooltip/>
            <el-table-column align="center" prop="updateBy" label="交单日期" show-overflow-tooltip/>
            <el-table-column align="center" label="操作" fixed="right">
              <template slot-scope="scope">
                <el-button size="mini" type="text" @click="startPay(scope.row,'public')">开始对公支付</el-button>
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
      startPay(row,status){
        let data = encodeURI(
          JSON.stringify({
            status,
          })
        )
        this.$router.push({
          path: '/claims-financial/case-message',
          query: {
            data
          }
        })
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
