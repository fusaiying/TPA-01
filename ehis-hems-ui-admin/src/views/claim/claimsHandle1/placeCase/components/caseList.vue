<template>
  <el-dialog
    :visible.sync="dialogListVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>归档案件列表（100）</span>
          <el-button style="float: right; margin-top: 10px;" size="mini" @click="changeDialogVisable">返回
          </el-button>
          <el-button style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="changeDialogVisable">保存
          </el-button>
        </div>
        <el-table
          :data="tableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          @selection-change="handleSelectionChange"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column prop="contractNo" label="报案号"  align="center" show-overflow-tooltip />
          <el-table-column prop="contractName" label="盒号"  align="center" show-overflow-tooltip />
          <el-table-column prop="" label="机构" align="center" width="150%" show-overflow-tooltip />
          <el-table-column prop="servcomNo" label="理赔类型"  align="center" width="150%"show-overflow-tooltip />
          <el-table-column prop="contractType" label="发票是否归档"  align="center" width="150%" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="发票是否退回"  align="center" width="150%" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="批次是否单张发票"  align="center" width="150%" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="备注"  align="center"  show-overflow-tooltip />
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
      rptNo: String,
    },
    watch: {
      value: function (newValue) {
        this.dialogListVisable = newValue;
        if(this.dialogListVisable) {
          // todo:获取数据
        }
      },
      rptNo: function (newValue) {
        this.detailRptNo = newValue;
      },
    },
    data() {
      return {
        dialogListVisable:false,
        tableData: [],
        totalNum: 0,
        pageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },
        loading: false,
        detailRptNo :'',
      }
    },
    mounted(){
     /* this.getDicts("contract_limit_type").then(response => {
        this.contractLimitTypes = response.data;
      });*/
    },
    computed: {

    },

    // 获取跳转地址的数据
    created: function() {
      this.initData();
    },
    methods: {
      initData(){

      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        // this.rptNos = (selection.map(item => item.rptNo)); //rptNo
        // // this.ids = selection.map(item => item.rptNo);
        // this.single = selection.length != 1;
        // this.multiple = !selection.length;
      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeListDialog')
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

