<template>
  <el-card class="box-card" v-show="params.isShow">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">账户信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <el-table
          ref="accountDetailTable"
          :data="accountList"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column align="center" prop="accountType" label="账户类型" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(accountTypeOptions, scope.row.accountType)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="businessCode" label="银联商户代码" show-overflow-tooltip />
          <el-table-column align="center" prop="accountName" label="银行账户名称" show-overflow-tooltip />
          <el-table-column align="center" prop="accountNo" label="账户号" show-overflow-tooltip />
          <el-table-column align="center" prop="bankCode" label="开户银行代码" show-overflow-tooltip />
          <el-table-column align="center" prop="bankName" label="开户银行名称" show-overflow-tooltip />
          <el-table-column align="center" prop="bankDetail" label="银行信息描述" show-overflow-tooltip />
        </el-table>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<script>

import {listBank} from '@/api/supplierManager/supplier'
export default {
  name: "accountDetailTable",
  props: {
    //查询参数
    params: {
      type: Object,
      required: true,
      default: function () {
        return {
          //是否展示
          isShow: false,
          //任务号
          supplierCode: null
        }
      }
    },
    //字典数组
    dictList: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  watch: {
    params: {
      immediate: true,
      handler: function(newVal) {
        this.initData();
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
      //列表
      accountList: [],
      //字典
      //账户类型
      accountTypeOptions: []
    };
  },
  created() {

  },
  async mounted() {

  },
  methods: {
    /** 初始化数据 */
    initData() {
      if (this.params.isShow) {
        this.activeNames = ['1'];
        this.loading = true;
        const accountParams = {
          providerCode: this.params.supplierCode
        };
        listBank(accountParams).then(res => {
          if (res != null && res.code === 200) {
            this.accountList = res.rows;
            this.loading = false;
          }
        }).catch(res => {
          this.loading = false
        });
      }
    },
    /** 初始化字典 */
    initDictData() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.accountTypeOptions = this.dictList.find(item => {
          return item.dictType == 'account_Types'
        }).dictDate
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
