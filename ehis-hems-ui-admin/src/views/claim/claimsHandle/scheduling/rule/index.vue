<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>抽检规则</span>
        <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"  @click="handleEdit(null,'add')">新增</el-button>
      </div>
      <el-table
        :data="tableData"
         v-loading="loading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <el-table-column align="center" prop="checkRuleNo" min-width="120" label="规则编码" show-overflow-tooltip/>
        <el-table-column align="center" prop="caseType" min-width="120" :formatter="getCaseTypeNameName" label="案件类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="payConclusion" min-width="110" :formatter="getClussionName" label="赔付结论" show-overflow-tooltip/>
        <el-table-column align="center" prop="amountType" min-width="120" :formatter="getAmountName" label="金额类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="checkAmount" min-width="120" label="抽检金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="rate" min-width="110" label="抽检比例" show-overflow-tooltip/>
        <el-table-column align="center" prop="status" min-width="120" :formatter="getStatusName" label="规则状态" show-overflow-tooltip/>
        <el-table-column align="center" prop="createTime" min-width="120" label="创建日期" show-overflow-tooltip>
          <template slot-scope="scope">
            <span >{{ scope.row.createTime | changeDate}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="updateTime" min-width="110" label="失效日期" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.status=='02' || scope.row.status=='N'">{{ scope.row.updateTime | changeDate}}</span>
          </template>
        </el-table-column>
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
    <rule-modal :value="diaVisible"  :caseTypeSelect="caseTypeSelect" :conclusionSelect="conclusionSelect" :amountTypeSelet="amountTypeSelet" :fixInfo="fixInfo" @initData="initData"  @closeDialogVisable="closeDialogVisable"/>
  </div>
</template>

<script>

  import ruleModal from './components/rule'
  import moment from 'moment'

  import { listInfo } from '@/api/scheduling/ruleApi'


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
        rowData:{},
        type:'',
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
      caseTypeSelect:[], // 案件类型
      conclusionSelect:[], // 赔付结论
      amountTypeSelet:[], // 金额类型

    }
  },
  mounted(){

    // 案件类型
    this.getDicts("caseType").then(response => {
      this.caseTypeSelect = response.data;
    });

    //赔付结论 conclusion
    this.getDicts("conclusion").then(response => {
      this.conclusionSelect = response.data;
    });

    // 金额类型
    this.getDicts("amount_type").then(response => {
      this.amountTypeSelet = response.data;
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
    handleEdit(row,type){
      if(type == 'edit') {
        this.fixInfo = {
          rowdata :row,
          type :'edit'
        };
      }  else {
        this.fixInfo = {
          rowdata :{},
          type :'add'
        };
      }
      this.diaVisible = true;
    },
    getCaseTypeNameName(row,col){
      return this.selectDictLabel(this.caseTypeSelect, row.caseType)
    },
    getClussionName(row,col){
      let result = '';
      if(row.payConclusion == null) {return result};
      let cons = row.payConclusion.split(",");
      for(let i=0; i<cons.length; i++) {
        if(i >0) {
          result += " \u3000 ";
        }
        result += this.selectDictLabel(this.conclusionSelect, cons[i]);
      }
      return result;
    },
    getAmountName(row,col){
      return this.selectDictLabel(this.amountTypeSelet, row.amountType)
    },
    getStatusName(row, col){
      if(row.status == 'Y' || row.status == '01') {
        return '有效';
      } else {
        return '无效';
      }
    },
    initData(){
      this.loading = true;
      const params = {};
      params.pageNum =  this.pageInfo.currentPage;
      params.pageSize = this.pageInfo.pageSize;

      listInfo(params).then(res => {
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
