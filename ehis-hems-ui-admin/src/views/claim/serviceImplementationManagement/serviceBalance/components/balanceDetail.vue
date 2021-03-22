<template>
  <el-card class="box-card" v-show="queryParams.isShow">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">结算明细列表{{ listTitle }}</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
          <span v-show="activeNames.length && node" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">
              <el-button type="primary" size="mini" @click.native.stop="listExport">清单导出</el-button>
          </span>
        </template>
        <el-table
          ref="balanceDetailTable"
          :data="balanceList"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column type="index" label="序号" align="center" show-overflow-tooltip width="50px"/>
          <el-table-column v-if="queryParams.isSearche" key="taskNo" prop="taskNo" label="任务号" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isPrice" key="supplierCode" prop="supplierCode" label="供应商名称" width="150%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(supplierData, scope.row.supplierCode)}}</span>
            </template>
          </el-table-column>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="orderCode" prop="orderCode" label="工单编号" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="settlementTime" prop="settlementTime" label="结算时间区间" min-width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont" key="riskCode" prop="riskName" label="险种" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="policyNo" prop="policyNo" label="保单号" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="policyItemNo" prop="policyItemNo" label="分单号" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isPrice" key="serviceCode" prop="supplierServiceName" label="供应商项目名称" width="150%" align="center" show-overflow-tooltip>
<!--            <template slot-scope="scope">
              <span>{{selectDictLabel(serviceData, scope.row.serviceCode)}}</span>
            </template>-->
          </el-table-column>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="appntName" prop="appntName" label="投保人" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="custName" prop="custName" label="客户姓名" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="sex" prop="sex" label="性别" width="150%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(sexOptions, scope.row.sex)}}</span>
            </template>
          </el-table-column>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="idNo" prop="idNo" label="证件号码" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase" key="serviceDate" prop="serviceDate" label="服务日期" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCont" key="insuredNum" prop="insuredNum" label="承保人数" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCont" key="unitPrice" prop="unitPrice" label="客单价" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont" key="remark" prop="remark" label="备注" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="amount" prop="amount" label="服务费" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="actualAmount" prop="actualAmount" label="实际服务费用" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.settleType===settleTypeData.isCase || queryParams.settleType===settleTypeData.isCont || queryParams.settleType===settleTypeData.isPrice" key="reason" prop="reason" label="编辑原因" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column v-if="queryParams.isSearche" key="bussinessStatus" prop="bussinessStatus" label="状态" width="150%" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(balanceStatusOptions, scope.row.bussinessStatus)}}</span>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList()"
        />
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<script>

import {listBalanceDetail_1, listBalanceDetail_2} from "@/api/claim/serviceBalance";
import {getAllBaseSupplierInfo, getContractServerList} from "@/api/contractManage/contractManagement";


export default {
  name: "balanceDetailTable",
  props: {
    //查询参数
    queryParams: {
      type: Object,
      required: true,
      default: function () {
        return {
          //是否展示
          isShow: false,
          //是否结算
          isSearche: false
        }
      }
    },
    //字典数组
    dictList: {
      type: Array,
      default: function () {
        return []
      }
    },
    //供应商数组
    supplierData: {
      type: Array,
      default: function () {
        return []
      }
    },
    //供应商服务项目数组
    serviceData: {
      type: Array,
      default: function () {
        return []
      }
    },
    //
    node: {
      type:Boolean,
      default: false
    }
  },
  watch: {
    queryParams: {
      immediate: true,
      handler: function(newVal) {
        this.init();
      }
    },
    dictList: function (newVal, oldVal) {
      this.initDictData();
    }
  },
  data() {
    return {
      // 遮罩层
      loading: false,
      // 显示
      activeNames: [],
      //结算方式变量
      settleTypeData: {
        //按case
        isCase: "01",
        //按人头
        isCont: "02",
        //按一口价
        isPrice: "03"
      },
      //总条数
      total: 0,
      //明细列表标题
      listTitle: null,
      //明细列表
      balanceList: [],
      //字典
      sexOptions: [],
      //状态
      balanceStatusOptions: []
    };
  },
  created() {

  },
  async mounted() {

  },
  methods: {
    //清单导出
    listExport(){
      listBalanceDetail_2(this.queryParams).then(res => {

        if (res != null && res.rows.length>0){
          this.download('order/balanceDetail/export2', {
            ...this.queryParams}, `invoice_${new Date().getTime()}.xlsx`).catch(res=>{
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }else {
          return this.$message.warning(
            "没有查询到能导出的数据！"
          )
        }
      }).catch(res => {
      })

    },

    //初始化
    init() {
      if (this.queryParams.isShow) {
        this.activeNames = ['1'];
        if (this.queryParams.settleType) {
          let flag = false;
          //按case
          if (this.queryParams.settleType == this.settleTypeData.isCase) {
            this.listTitle = "（按case）";
            flag = true;
            //按人头
          } else if (this.queryParams.settleType == this.settleTypeData.isCont) {
            this.listTitle = "（按人头）";
            //按一口价
          } else if (this.queryParams.settleType == this.settleTypeData.isPrice) {
            this.listTitle = "（按一口价）";
            flag = true;
          }
          //获取明细列表
          this.getList();
          if (flag) {
            /** 供应商 */
            const query ={
              status:'Y'
            };
            if (this.supplierData && this.supplierData.length == 0) {
              getAllBaseSupplierInfo(query).then(res => {
                if(res.data != null) {
                  for(let i=0; i<res.data.length; i++) {
                    let obj= new Object();
                    obj.dictLabel = res.data[i].chname + " - " + res.data[i].enname;
                    obj.dictValue = res.data[i].serialNo;
                    this.supplierData.push(obj);
                  }
                }
              }).catch(error => {
                console.log(error);
              });
            }

            if (this.serviceData && this.serviceData.length == 0) {
              /** 供应商项目 */
              getContractServerList(query).then(res => {
                if (res.rows != null) {
                  for(let i=0; i<res.rows.length; i++) {
                    let obj= new Object();
                    obj.dictLabel = res.rows[i].supplierServiceName;
                    obj.dictValue = res.rows[i].serialNo;
                    this.serviceData.push(obj);
                  }
                }
              }).catch(error => {
                console.log(error);
              });
            }
          }
        }
      }
    },
    /** 初始化字典 */
    initDictData() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.sexOptions = this.dictList.find(item => {
          return item.dictType == 'sex'
        }).dictDate;
        if (this.queryParams.isSearche || this.queryParams.isWrite) {
          this.balanceStatusOptions = this.dictList.find(item => {
            return item.dictType == 'balance_status'
          }).dictDate;
        }
      }
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      if (!this.queryParams.isSearche) {
        //明细
        listBalanceDetail_1(this.queryParams).then(res => {
          if (res != null && res.code === 200) {
            this.balanceList = res.rows;
            this.total = res.total;
            this.loading = false;
            if (res.rows.length < 1) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {
          this.loading = false
        });
      } else {
        //结算清单
        listBalanceDetail_2(this.queryParams).then(res => {
          if (res != null && res.code === 200) {
            this.balanceList = res.rows;
            this.total = res.total;
            this.loading = false;
            if (res.rows.length < 1) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {
          this.loading = false
        });
      }
    }
  }
};
</script>

<style scoped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
/*自定义样式*/
.receive_table {
  width: 98%;
  padding: 0px;
  margin-left: 1%;
  margin-right: 1%;
}

</style>
