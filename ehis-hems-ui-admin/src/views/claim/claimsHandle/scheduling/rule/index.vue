<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>抽检规则</span>
        <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"  @click="handleEdit(null,'add')">新增</el-button>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <el-table-column align="center" prop="batchNo" min-width="120" label="规则编码" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" min-width="120" label="案件类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="updateBy" min-width="110" label="赔付结论" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" min-width="120" label="金额类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" min-width="120" label="抽检金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="updateBy" min-width="110" label="抽检比例" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" min-width="120" label="规则状态" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" min-width="120" label="创建日期" show-overflow-tooltip/>
        <el-table-column align="center" prop="updateBy" min-width="110" label="失效日期" show-overflow-tooltip/>
        <el-table-column fixed="right" align="center" prop="editing" min-width="110"  label="操作" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit"
              @click="handleEdit(scope.row,'edit')">编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination
        v-show="totalNum>0"
        :total="totalNum"
        :page.sync="pageInfo.currentPage"
        :limit.sync="pageInfo.pageSize"
        @pagination="initData"
      />
    </el-card>
    <!-- 编辑弹框 -->
    <rule-modal :value="diaVisible"  :fixInfo="fixInfo"  @closeDialogVisable="closeDialogVisable"/>
  </div>
</template>

<script>

  import ruleModal from './components/rule'
  import moment from 'moment'
  import {invoiceData, updateInvoice} from '@/api/invoice/api'

  export default {
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  components: {
    ruleModal
  },
  data() {
    return {
      fixInfo:{

      },
      diaVisible:false,
      loading : false,
      tableData: [],
      pageInfo: {
        currentPage: 1,
        pageSize: 10,
        pageSizes:[5,10,20,30,50,100]
      },
      totalNum:0,
      ysOrNo:[],
      claimTypes:[],
      providerInfoSelects:[],

    }
  },
  mounted(){
    // sys_yes_no
    this.getDicts("sys_yes_no").then(response => {
      this.ysOrNo = response.data;
    });
    // claimType
    this.getDicts("claimType").then(response => {
      this.claimTypes = response.data;
    });
  },
  created() {
    this.initData();
  },
  watch: {
  },
  computed: {

  },
  methods: {
    handleEdit(){

      this.diaVisible = true;
    },
    getYesOrNoName(value){
      return this.selectDictLabel(this.ysOrNo, value)
    },
    getClaimTypeName(row,col){
      return this.selectDictLabel(this.claimTypes, row.claimType)
    },
    initData(){
      this.loading = true;
      const params = {};
      params.pageNum =  this.pageInfo.currentPage;
      params.pageSize = this.pageInfo.pageSize;

      invoiceData(params).then(res => {
          if (res.code == '200') {
            this.totalNum = res.total;
            let _data = res.rows;
            // if (_data.length !== 0) {
            //   _data.forEach(item => {
            //     item.editing = false;
            //   })
            // }
            this.tableData= _data;
          }
        }).finally(() => {
            this.loading = false;
      })
    },
    closeDialogVisable() {
      this.diaVisible = false
    },
  },


}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
