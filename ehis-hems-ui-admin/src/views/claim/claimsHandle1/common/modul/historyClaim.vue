<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>理赔案件列表（{{ totalNum }}）</span>
          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="changeDialogVisable">返回
          </el-button>
          <el-button style="float: right; margin-top: 10px;margin-right:15px" type="primary" size="mini" @click="exportData">清单导出
          </el-button>
        </div>
        <el-table
          :data="tableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}">
          <el-table-column prop="contractNo" label="批次号" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractName" label="报案号" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="" label="案件状态" align="center"  show-overflow-tooltip />
          <el-table-column prop="servcomNo" label="被保险人"  align="center" show-overflow-tooltip />
          <el-table-column prop="contractType" label="证件号码"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="分单号"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="就诊日期"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="赔付结论"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="给付金额"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="审核人"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="有无调查"  align="center" show-overflow-tooltip />
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
/*  import { } from '@/api/contractManage/contractManagement'*/
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
    },
    data() {
      return {
        dialogVisable: false,
        tableData: [],
        totalNum: 0,
        pageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },

        loading: false,
      }
    },
    mounted(){
     /* this.getDicts("contract_limit_type").then(response => {
        this.contractLimitTypes = response.data;
      });*/
    },
    computed: {

    },

    created: function() {
      this.initData();
    },
    methods: {
      initData(){

      },
      exportData(){

      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeHistoryClaimDialog')
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /deep/.el-table .warning-row {
    background: oldlace;
  }

</style>

