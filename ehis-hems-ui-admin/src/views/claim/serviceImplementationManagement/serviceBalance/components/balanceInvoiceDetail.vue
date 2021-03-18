<template>
  <el-card class="box-card" v-show="params.isShow" id="balanceInvoiceDiv">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">发票信息列表</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 10px;" >
          <el-button v-if="params.isWrite" type="primary" size="mini" @click="handleWrite">核 销</el-button>
          <el-button v-if="params.isAdd" type="primary" size="mini" @click="handleAdd">添加发票</el-button>
        </span>
        <el-table
          ref="balanceInvoiceTable"
          :data="balanceInvoiceList"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column align="center" prop="serialNo" label="主键" show-overflow-tooltip v-if=false />
          <el-table-column align="center" prop="taskNo" label="任务号" show-overflow-tooltip v-if=false />
          <el-table-column align="center" prop="status" label="状态" show-overflow-tooltip v-if=false />
          <el-table-column align="center" prop="invoiceType" label="发票类型" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(invoiceTypeOptions, scope.row.invoiceType)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="invoiceCode" label="发票代码" show-overflow-tooltip/>
          <el-table-column align="center" prop="invoiceNo" label="发票号" show-overflow-tooltip/>
          <el-table-column align="center" prop="amount" label="发票金额" show-overflow-tooltip/>
          <el-table-column align="center" prop="amountTax" label="发票金额（不含税）" show-overflow-tooltip width="140px"/>
          <el-table-column align="center" prop="tax" label="发票税额" show-overflow-tooltip/>
          <el-table-column align="center" prop="auth" label="认证结果" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(invoiceAuthOptions, scope.row.auth)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="invoiceDate" label="开票日期" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.invoiceDate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column v-if="params.isAdd" label="操作" align="center" class-name="small-padding fixed-width" width="120px">
            <template slot-scope="scope">
              <el-button v-if="params.isAdd" size="mini" style="padding: 0px;" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
              <el-button v-if="params.isAdd" size="mini" style="padding: 0px;" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
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
        <!-- 添加或修改base_balance_invoice(服务结算发票信息)对话框 -->
        <el-dialog title="开票信息" :visible.sync="open" width="70%" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="170px" label-position="right">
            <el-input v-model="form.serialNo" type="hidden" />
            <el-input v-model="form.taskNo" type="hidden" />
            <el-row>
              <el-col :span="8">
                <el-form-item label="发票类型:" prop="invoiceType">
                  <el-select v-model="form.invoiceType" placeholder="请选择发票类型" class="item-width">
                    <el-option v-for="item in invoiceTypeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="发票代码:" prop="invoiceCode">
                  <el-input v-model="form.invoiceCode" placeholder="请输入发票代码" class="item-width" />
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="发票号码:" prop="invoiceNo">
                  <el-input v-model="form.invoiceNo" placeholder="请输入发票号码" class="item-width" />
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="发票金额:" prop="amount">
                  <el-input v-model="form.amount" placeholder="请输入发票金额" class="item-width" />
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="发票金额（税）:" prop="amountTax">
                  <el-input v-model="form.amountTax" placeholder="请输入发票金额（不含税）" class="item-width" />
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="发票税额:" prop="tax">
                  <el-input v-model="form.tax" placeholder="请输入发票税额" class="item-width" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">认证提交</el-button>
            <el-button @click="reset">重 置</el-button>
          </div>
        </el-dialog>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<script>
import moment from 'moment';
import {updateBalance, listBalanceInvoice, addBalanceInvoice, updateBalanceInvoice} from "@/api/claim/serviceBalance";

export default {
  name: "balanceInvoiceTable",
  props: {
    //查询参数
    params: {
      type: Object,
      required: true,
      default: function () {
        return {
          //是否展示
          isShow: false,
          //加载类型
          isAdd: false,
          //是否核销
          isWrite: false,
          //任务号
          taskNo: null,
          //支付金额
          payAmount: null
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
      handler: function (newVal) {
        this.queryParams.taskNo = newVal.taskNo;
        if (newVal.isShow) {
          this.activeNames = ['1'];
          this.getList();
        }
      }
    },
    dictList: function (newVal, oldVal) {
      this.initDictData();
    }
  },
  data() {
    const checkAmount = (rules, value, callback) => {
      const regx = /^(\d+|\d+\.\d{1,2})$/
      const regx1 = /[^\d+\d-\.]/g
      if (value < 0) {
        callback(new Error("只能输入数字且保留两位小数"));
      } else if (!regx.test(value)) {
        callback(new Error("只能输入数字且保留两位小数"));
      } else {
        callback();
      }
    };

    return {
      // 遮罩层
      loading: false,
      // 显示
      activeNames: [],
      //总条数
      total: 0,
      // 查询参数
      queryParams: {
        // 状态
        status: "Y",
        // 页数
        pageNum: 1,
        // 分页数
        pageSize: 10,
        //任务号
        taskNo: null
      },
      //列表
      balanceInvoiceList: [],
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        invoiceType: [{required: true, message: "发票类型不能为空", trigger: "change"}],
        amount: [{validator: checkAmount, trigger: 'blur'}],
        amountTax: [{validator: checkAmount, trigger: 'blur'}],
        tax: [{validator: checkAmount, trigger: 'blur'}]
      },
      //字典
      //发票类型
      invoiceTypeOptions: [],
      //发票认证
      invoiceAuthOptions: []
    };
  },
  created() {

  },
  async mounted() {

  },
  methods: {
    /** 初始化字典 */
    initDictData() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.invoiceTypeOptions = this.dictList.find(item => {
          return item.dictType == 'balance_invoice_type2'
        }).dictDate
        this.invoiceAuthOptions = this.dictList.find(item => {
          return item.dictType == 'invoice_auth'
        }).dictDate
      }
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      listBalanceInvoice(this.queryParams).then(res => {
        if (res != null && res.code === 200) {
          this.balanceInvoiceList = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      }).catch(res => {
        this.loading = false
      });
    },
    // 表单重置
    reset() {
      const serialNo = this.form.serialNo
      const taskNo = this.form.taskNo;
      this.resetForm("form");
      this.form.serialNo = serialNo;
      this.form.taskNo = taskNo;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.form.taskNo = this.queryParams.taskNo;
      this.reset();
      this.open = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.form.serialNo = row.serialNo;
      this.form.taskNo = row.taskNo;
      this.form.invoiceType = row.invoiceType;
      this.form.invoiceCode = row.invoiceCode;
      this.form.invoiceNo = row.invoiceNo;
      this.form.amount = row.amount;
      this.form.amountTax = row.amountTax;
      this.form.tax = row.tax;
      this.open = true;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serialNo) {
            updateBalanceInvoice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addBalanceInvoice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除数据?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        row.status = "N";
        updateBalanceInvoice(row).then(response => {
          if (response.code === 200) {
            this.msgSuccess("删除成功");
            this.getList();
          }
        });
      }).catch(function () {

      });
    },
    /** 核销 */
    handleWrite() {
      if (this.params.payAmount > 0) {
        let amount = 0;
        this.balanceInvoiceList.forEach(item => {
          amount += item.amount;
        })
        if (this.params.payAmount != amount) {
          this.$message.warning('发票金额与支付金额不一致！')
          return;
        }
      } else {
        this.$message.warning('发票金额与支付金额不一致！');
        return;
      }
      const params = {
        params: {type: "INVOICE"},
        taskNo: this.queryParams.taskNo
      };
      updateBalance(params).then(res => {
        if (res != null && res.code === 200) {
          this.msgSuccess("核销成功");
          //刷新
          this.$parent.setInvoiceStatus(this.queryParams.taskNo);
        }
      }).catch(res => {
        this.msgError("核销失败");
      });
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

.el-table .hidden-row {
  display: none;
}

.item-width {
  width: 220px;
}

</style>
