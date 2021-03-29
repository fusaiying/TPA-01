<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="批次号：" prop="batchNo">
              <el-input v-model="searchForm.batchNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院名称：" prop="hospitalCode">
              <el-select
                v-model="searchForm.hospitalCode"
                filterable
                remote
                clearable
                reserve-keyword
                placeholder="请选择医院"
                :remote-method="remoteMethod"
                class="item-width"
                size="mini">
                <el-option
                  v-for="(item, ind) in hospitalOptions"
                  :key="ind"
                  :label="item.chname1"
                  :value="item.providerCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否申诉：" prop="complainStatus">
              <el-select v-model="searchForm.complainStatus" class="item-width" placeholder="请选择" clearable
                         @change="">
                <el-option v-for="option in sys_yes_noOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="交单日期：" prop="caseDate">
              <el-date-picker
                v-model="searchForm.caseDate"
                class="item-width"
                type="daterange"
                clearable
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
               search('but')
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
          @sort-change="onSortChange"
          :cell-style="changeCellStyle"
          style=" width: 100%;">
          <el-table-column sortable="custom" :sort-orders="['ascending','descending',null]" align="center"
                           prop="batchNo" label="批次号" show-overflow-tooltip/>
          <el-table-column align="center" prop="hospitalCode" label="医院名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="caseload" label="批次案件总数" width="110" show-overflow-tooltip/>
          <el-table-column align="center" prop="batchTotal" label="账单总金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.batchTotal}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="calAmount" label="理赔总金额" width="100" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.calAmount}} CNY</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="isAppeal" label="是否申诉" width="100" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel( sys_yes_noOptions, scope.row.isAppeal)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="organName" label="交单机构" width="100" show-overflow-tooltip/>
          <el-table-column align="center" prop="submitDate" label="交单日期" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="startPay(scope.row,'publicForeign')">开始对公支付</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="search('tab')"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {getListNew} from '@/api/insuranceRules/ruleDefin'
  import {getUserInfo, getOrganList} from '@/api/claim/standingBookSearch'
  import {initForeignList, foreignList} from '@/api/claim/corporatePay'
  import {getHospitalInfo} from '@/api/claim/handleCom'
  let dictss = [{dictType: 'sys_yes_no'}]
  export default {
    data() {
      return {
        isListExport: false,
        organCode: '',
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },

        tableData: [],
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          batchNo: '',
          hospitalCode: '',
          complainStatus: '',
          caseDate: [],
          startDate: '',
          endDate: '',
          organCode: '',
          orderByColumn: '',
          isAsc: '',
        },
        totalCount: 0,
        dictList: [],
        hospitalOptions: [],
        deptListOptions: [],
        sys_yes_noOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.sys_yes_noOptions = this.dictList.find(item => {
        return item.dictType === 'sys_yes_no'
      }).dictDate
      getUserInfo().then(res => {
        if (res != null && res.code === 200) {
          this.organCode=res.data.organCode
          let item = {
            organCode: '',
            pageNum: 1,
            pageSize: 200,
          }
          if (res.data != null) {
            item.organCode = res.data.organCode
          }
          getOrganList(item).then(res => {
            if (res != null && res.code === 200) {
              this.deptListOptions = res.rows
            }
          }).catch(res => {
          })
          let query={
            pageNum: 1,
            pageSize: 10,
            organCode:res.data.organCode
          }
          initForeignList(query).then(res => {
            if (res != null && res.code === 200) {
              this.tableData = res.rows
              this.totalCount = res.total
            }
          })
        }
      })
      let data = {
        pageNum:1,
        pageSize:200
      }
      getListNew(data).then(res => {
        this.hospitalOptions = res.rows
      })
    },
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
        this.searchForm.caseDate=[]
        this.searchForm.startDate=''
        this.searchForm.endDate=''
      },
      search(status) {
        if (this.searchForm.organCode=='' || this.searchForm.organCode==null){
          this.searchForm.organCode=this.organCode
        }
        if (this.searchForm.caseDate != null && this.searchForm.caseDate.length > 0) {
          this.searchForm.startDate = this.searchForm.caseDate[0]
          this.searchForm.endDate = this.searchForm.caseDate[1]
        }
        if (status === 'tab') {
          this.searchForm.pageNum = this.queryParams.pageNum
          this.searchForm.pageSize = this.queryParams.pageSize
        } else {
          this.searchForm.pageNum = 1
          this.searchForm.pageSize = 10
        }
        foreignList(this.searchForm).then(res => {
          if (res != null && res.code === 200) {
            this.tableData = res.rows
            this.totalCount = res.total
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        })
      },
      startPay(row, status) {
        let data = encodeURI(
          JSON.stringify({
            batchNo: row.batchNo,
            batchTotal: row.batchTotal,
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
      remoteMethod(query) {
        if (query !== '' && query != null) {//调用特殊医院查询接口
          let data = {
            chname1: query,
            pageNum:1,
            pageSize:200
          }
          getListNew(data).then(res => {
            this.hospitalOptions = res.rows
          })
        }
      },
      onSortChange({prop, order}) {
        this.searchForm.orderByColumn = prop
        if (order === 'ascending') {
          this.searchForm.isAsc = 'asc'
        } else if (order === 'descending') {
          this.searchForm.isAsc = 'desc'
        } else if (order == null) {
          this.searchForm.orderByColumn = ''
          this.searchForm.isAsc = ''
        }
        this.search()
      },
      changeCellStyle(rows, column, rowIndex, columnIndex) {
        if (rows.row.flag === 'N') {
          return 'color: red'  // 修改的样式
        } else {
          return ''
        }

      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

</style>
