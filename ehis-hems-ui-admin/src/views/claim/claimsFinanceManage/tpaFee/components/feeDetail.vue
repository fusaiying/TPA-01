<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>结算明细信息</span>
        <el-button style="float: right; margin-top: 10px;" size="mini" @click="changeDialogVisable">返回
        </el-button>
        <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="exportData">清单导出
        </el-button>
        <el-button v-if="confimInfo" style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="importData">清单导入
        </el-button>
        <el-button v-if="confimInfo" style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="importData">确认
        </el-button>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;"> <!--   @expand-change="getMinData" -->
        <el-table-column  type="expand">
          <template slot-scope="scope">
            <el-table :data="scope.row.minData"
                      :header-cell-style="{color:'black',background:'#f8f8ff'}"
                      highlight-current-row
                      size="small"
                      v-loading="loading"
                      tooltip-effect="dark"
                      style="width: 100%;">
              <el-table-column prop="rptNo" label="保单号" align="center"/>
              <el-table-column prop="rptNo" label="分单号" align="center"/>
              <el-table-column prop="rptNo" label="投保人" align="center"/>
              <el-table-column prop="rptNo" label="被保人" align="center"/>
              <el-table-column prop="rptNo" label="险种" align="center"/>
              <el-table-column prop="rptNo" label="生效日期" align="center"/>

              <el-table-column prop="rptNo" label="保费比例%" align="center"/>
              <el-table-column prop="rptNo" label="保费" align="center"/>

              <el-table-column prop="name" label="服务费CNY" align="center"/>
              <el-table-column prop="rptNo" label="备注" align="center"/>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="batchNo"  label="出单公司" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" label="险种" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" label="总人数" show-overflow-tooltip/>
        <el-table-column align="center" prop="batchNo" label="服务费总金额CNY" show-overflow-tooltip/>
        <el-table-column align="center" prop="updateBy" label="备注" show-overflow-tooltip/>
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
  </el-dialog>
</template>

<script>

  import moment from 'moment'
  import {taskViewDetail, initiateTask} from '@/api/tpaFee/api'
  import {updateConfirm} from "@/api/paymentFee/api";

  export default {
  props: {
    value: {
      type: Boolean,
      default: false
    },
    fixInfo:Object,
  },
  watch: {
    fixInfo: function (newValue) {
      console.log("detail newValue")
      console.log(newValue)
      console.log("detail newValue")

      this.fixInfoDetail = newValue;
      console.log("fixInfoDetail")
      console.log(this.fixInfoDetail)
      console.log("fixInfoDetail")

    },
    value: function (newValue) {
      this.dialogVisable = newValue;
      if(this.dialogVisable) {
        this.totalNum = 0;
        this.tableData= [];
        let type = this.fixInfoDetail.type ;
        // 发起结算
        if(type == "launch") {
          this.initiateTaskData();
        }
        if(type == "show") {
          this.initData();
        }
        if(type == 'confirm') {
          this.confimInfo = true;
          this.initData();
        }
      }
    },
  },
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  components: {
  },
  data() {
    return {
      confimInfo:false,
      loading : false,
      dialogVisable : false,
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
      fixInfoDetail:{},

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
    //this.initData();
  },
  computed: {

  },
  methods: {
    getYesOrNoName(value){
      return this.selectDictLabel(this.ysOrNo, value)
    },
    initData(){
      this.loading = true;
      const params = {};
      params.pageNum =  this.pageInfo.currentPage;
      params.pageSize =  this.pageInfo.pageSize;
      params.settleTaskNo = this.fixInfoDetail.rowData.settleTaskNo;
      taskViewDetail(params).then(res => {
          if (res.code == '200') {
            this.totalNum = res.total;

            let _data = res.rows;
            if (_data.length !== 0) {
              _data.forEach(item => {
                item.editing = false;
                item.minData = [item]
              })
            }
            this.tableData= _data;
          }
        }).finally(() => {
            this.loading = false;
      })
    },
    // 发起结算
    initiateTaskData(){
      this.loading = true;
      const params = {};
      params.pageNum =  this.pageInfo.currentPage;
      params.pageSize =  this.pageInfo.pageSize;

      params.settleTaskNo = this.fixInfoDetail.rowData.settleTaskNo;
      params.companyCode = this.fixInfoDetail.rowData.companyCode;
      params.settleEndDate = this.fixInfoDetail.rowData.settleEndDate;
      initiateTask(params).then(res => {
        if (res.code == '200') {
          this.totalNum = res.total;
          let _data = res.rows;
          if (_data.length !== 0) {
            _data.forEach(item => {
              item.editing = false;
              item.minData = [item]
            })
          }
          this.tableData= _data;
        }
      }).finally(() => {
        this.loading = false;
      })
    },
    //导出
    exportData(){
    },
    //导入
    importData(){
      this.$emit('openImportDialog');
    },
    //确认
    confirmDataInfo(){
      let settleTaskNo  = this.fixInfoDetail.rowData.settleTaskNo;
      this.$confirm('确定处理', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        updateConfirm(settleTaskNo).then(response => {
          if(response.code == '200') {
            this.confimInfo = false;
            this.$emit('initData');
            this.$message({
              type: 'success',
              message: '处理成功!'
            });
          } else {
            this.$message({
              type: 'info',
              message: '处理失败'
            });
          }
        }).catch(error => {
          console.log(error);
        })
      }).catch(() => {
      })
    },
    //关闭对话框
    changeDialogVisable() {
      this.confimInfo = false;
      this.$emit('closeDetailDialog')
    },
  },


}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
