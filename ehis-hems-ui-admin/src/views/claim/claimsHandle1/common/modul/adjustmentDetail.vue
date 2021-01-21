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
          <span>赔付使用规则</span>
          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="changeDialogVisable">返回
          </el-button>
        </div>
        <el-table
          :data="adIsTableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="contractNo" label="规则编码" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractName" label="责任明细名称" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="" label="费用项名称" align="center"  show-overflow-tooltip />
          <el-table-column prop="servcomNo" label="规则描述"  align="center" show-overflow-tooltip />
          <el-table-column prop="contractType" label="使用值"  align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="剩余值"  align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;"  v-if="scope.row.bussinessStatus=='01'" >
                不参与理算
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="adIsTotalNum>0"
          :total="adIsTotalNum"
          :page.sync="adIsPageInfo.currentPage"
          :limit.sync="adIsPageInfo.pageSize"
          @pagination="initAdIsData"
        />

      <!-- 不参与理算规则 start -->
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>不参与理算规则</span>
      </div>
      <el-table
        :data="adNoTableData"
        v-loading="loading"
        size="mini"
        tooltip-effect="dark"
        class="receive_table"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
      >
        <el-table-column prop="contractNo" label="规则编码" width="150%" align="center" show-overflow-tooltip />
        <el-table-column prop="contractName" label="规则描述" width="150%" align="center" show-overflow-tooltip />
        <el-table-column prop="" label="规则要素" align="center"  show-overflow-tooltip />
        <el-table-column prop="contractType" label="要素值"  align="center" show-overflow-tooltip />
        <el-table-column prop="contracttermType" label="要素单位"  align="center" show-overflow-tooltip />
        <el-table-column label="操作" align="center" style="padding-top: 0px;">
          <template slot-scope="scope">
            <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;"  v-if="scope.row.bussinessStatus=='01'" >
              撤销
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination
        v-show="adNoTotalNum>0"
        :total="adNoTotalNum"
        :page.sync="adNoPageInfo.currentPage"
        :limit.sync="adNoPageInfo.pageSize"
        @pagination="initAdNoData"
      />
      <!-- 不参与理算规则 end -->


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
        dialogVisable:false,
        adIsTableData: [],
        adIsTotalNum: 0,
        adIsPageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },

        adNoTableData: [],
        adNoTotalNum: 0,
        adNoPageInfo: {
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

    // 获取跳转地址的数据
    created: function() {
      this.initAdIsData();
      this.initAdNoData();
    },
    methods: {
      initAdIsData(){

      },
      initAdNoData(){

      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeAdjustmentDialog')
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

