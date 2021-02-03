<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    :show-close="false"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>赔付使用规则</span>
          <el-button style="float: right; margin-top: 10px;"  size="mini" @click="changeDialogVisable">返回
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
          <el-table-column prop="ruleNo" label="规则编码" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="dutyDetailName" label="责任明细名称" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="feeitemName" label="费用项名称" align="center"  show-overflow-tooltip />
          <el-table-column prop="ruleDescription" label="规则描述"  align="center" show-overflow-tooltip />
          <el-table-column prop="usedValue" label="使用值"  align="center" show-overflow-tooltip />
          <el-table-column prop="surplusValue" label="剩余值"  align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="revokeAdjustmentRule(scope.row)" style="z-index: 1;padding:0px;margin-top:0px;" >
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
          @pagination="initAdIsData"/>

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
        <el-table-column prop="ruleNo" label="规则编码" width="150%" align="center" show-overflow-tooltip />
        <el-table-column prop="ruleDescription" label="规则描述" width="150%" align="center" show-overflow-tooltip />
        <el-table-column prop="ruleElement" :formatter="getRuleelementName" label="规则要素" align="center"  show-overflow-tooltip />
        <el-table-column prop="elementValue" label="要素值"  align="center" show-overflow-tooltip />
        <el-table-column prop="elementUnit" :formatter="getUnitofelementName" label="要素单位"  align="center" show-overflow-tooltip />
        <el-table-column label="操作" align="center" style="padding-top: 0px;">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="cancelAdjustmentRule(scope.row)" style="z-index: 1;padding:0px;margin-top:0px;"  >
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
        @pagination="initAdNoData"/>
      <!-- 不参与理算规则 end -->


    </el-card>
  </el-dialog>
</template>

<script>
  import { adIsData, adNoData,revokeAdjustment,cancelAdjustment } from '@/api/handel/common/api'
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo:Object
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue;
        if(this.dialogVisable) {
          this.initAdIsData();
          this.initAdNoData();
        }
      },
      fixInfo: function (newValue) {
        this.fixInfoData = newValue;
        this.rptNo = this.fixInfoData.rptNo;
      },
    },
    data() {
      return {
        fixInfoData : '',
        rptNo :'',
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
        ruleelements:[],
        unitofelements:[],

      }
    },
    mounted(){
      //规则要素
      this.getDicts("ruleelement").then(response => {
        this.ruleelements = response.data;
      });
      //要素单位
      this.getDicts("unitofelement").then(response => {
        this.unitofelements = response.data;
      });
    },
    computed: {

    },

    created: function() {

    },
    methods: {
      getRuleelementName(row,col){
        return this.selectDictLabel(this.ruleelements, row.ruleElement);
      },
      getUnitofelementName(row,col){
        return this.selectDictLabel(this.unitofelements, row.elementUnit);
      },
      revokeAdjustmentRule(row){
        let vm = this;
        this.$confirm('确定不参与理算?', "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          revokeAdjustment(row).then(res => {
            if(res.code == '200') {
              vm.initAdIsData();
              vm.$message({
                message: '处理成功！',
                type: 'success',
                center: true,
                showClose: true
              });
            }
          });
        }).then(() => {
        }).catch(function (error) {
          console.log(error)
        });

        // revokeAdjustment(row).then(res => {
        //   if (res.code == '200') {
        //
        //   }
        // });
      },
      cancelAdjustmentRule(row){

        let vm = this;
        this.$confirm('确认撤销?', "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          cancelAdjustment(row).then(res => {
            if(res.code == '200') {
              vm.initAdNoData();
              vm.$message({
                message: '撤销成功！',
                type: 'success',
                center: true,
                showClose: true
              });
            }
          });
        }).then(() => {
        }).catch(function (error) {
          console.log(error)
        });

        // cancelAdjustment(row).then(res => {
        //   if (res.code == '200') {
        //
        //   }
        // });
      },
      initAdIsData(){

        if(this.rptNo == '') {
          return false;
        }

        this.loading = true;
        const params = {};
        params.pageNum = this.adIsPageInfo.currentPage;
        params.pageSize = this.adIsPageInfo.pageSize;
        params.rptNo = this.rptNo;

        adIsData(params).then(res => {
          console.log(res);
          if (res.code == '200') {
            this.adIsTotalNum = res.total;
            this.adIsTableData = res.rows;
          }
          this.loading = false
        });
      },
      initAdNoData(){

        if(this.rptNo == '') {
          return false;
        }

        this.loading = true;
        const params = {};
        params.pageNum = this.adNoPageInfo.currentPage;
        params.pageSize = this.adNoPageInfo.pageSize;
        params.rptNo = this.rptNo;

        adNoData(params).then(res => {
          console.log(res);
          if (res.code == '200') {
            this.adNoTotalNum = res.total;
            this.adNoTableData = res.rows;
          }
          this.loading = false
        });
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

  ::v-deep.el-table .warning-row {
    background: oldlace;
  }

</style>

