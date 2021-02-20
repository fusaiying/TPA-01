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
            <el-form-item label="是否申述：" prop="complainStatus">
              <el-select v-model="searchForm.complainStatus" class="item-width" placeholder="请选择"
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
            <el-form-item label="交单日期：" prop="endCaseDate">
              <el-date-picker
                v-model="searchForm.caseDate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交单机构：" prop="organCode">
              <el-select v-model="searchForm.organCode" class="item-width"
                         filterable
                         remote
                         reserve-keyword
                         placeholder="请选择机构"
                         :remote-method="remoteDeptMethod">
                <el-option v-for="option in deptOptions" :key="option.deptId"
                           :label="option.deptName"
                           :value="option.deptId"/>
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
          <el-table-column align="center" prop="hospitalCode" label="医院名称" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel( hospitals, scope.row.hospitalCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="caseload" label="批次案件总数" width="110" show-overflow-tooltip/>
          <el-table-column align="center" prop="batchTotal" label="账单总金额" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.batchTotal}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="calAmount" label="理赔总金额" width="100" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.calAmount}} {{scope.row.currency}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="isAppeal" label="是否申述" width="100" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel( sys_yes_noOptions, scope.row.isAppeal)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="organCode" label="交单机构" width="100" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{getDeptName( deptOptions,scope.row.organCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="submitDate" label="交单日期" show-overflow-tooltip/>
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
          @pagination="search('tab')"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {getListNew} from '@/api/insuranceRules/ruleDefin'
  import {getDept, getDeptById} from '@/api/claim/standingBookSearch'
  import {initList, list} from '@/api/claim/corporatePay'
  import {getHospitalInfo} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'sys_yes_no'},]
  export default {
    data() {
      return {
        isListExport: false,
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
          startDate: undefined,
          endDate: undefined,
          organCode: undefined,
          orderByColumn: '',
          isAsc: '',
        },
        totalCount: 0,
        dictList: [],
        deptOptions: [],
        hospitalOptions: [],
        sys_yes_noOptions: [],
        hospitals: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.sys_yes_noOptions = this.dictList.find(item => {
        return item.dictType === 'sys_yes_no'
      }).dictDate
      let item = {
        pageNum: 1,
        pageSize: 200,
      }
      getDept(item).then(res => {
        this.deptOptions = res.deptlist
        this.searchForm.organCode = res.deptId
      }).catch(res => {
      })
      initList(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.tableData = res.rows
        }
      })
      getHospitalInfo({}).then(res => {
        if (res != null && res !== '') {
          this.hospitals = res.rows
        }
      })
    },
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
        this.searchForm.caseDate=[]
      },
      search(status) {
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
        list(this.searchForm).then(res => {
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
      listExport() {

      },
      startPay(row, status) {
        let data = encodeURI(
          JSON.stringify({
            batchNo: row.batchNo,
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
            chname1: query
          }
          getListNew(data).then(res => {
            this.hospitalOptions = res.rows
          })
        }
      },
      remoteDeptMethod(query) {
        let data = {
          deptName: query,
          pageNum: 1,
          pageSize: 200,
        }
        if (query !== '' && query != null) {
          getDept(data).then(res => {
            this.deptOptions = res.deptlist
          }).catch(res => {
          })
        }
      },
      getDeptName(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
          if (datas[key].deptId === parseInt(value)) {
            actions.push(datas[key].deptName);
            return true;
          }
        })
        return actions.join('');
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
        /* if(rows.row.bussinessStatus != "01"){
           return 'color: red'  // 修改的样式
         }else{
           return ''
         }*/
      },
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
