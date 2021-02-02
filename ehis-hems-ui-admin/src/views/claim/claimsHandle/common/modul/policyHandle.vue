<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="保单操作"
    width="76%">
    <el-table
      ref="table"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="props.subTable"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column align="center" width="55" type="selection"/>
            <el-table-column align="center" prop="policyNo" label="险种编码" show-overflow-tooltip/>
            <el-table-column align="center" prop="riskName" label="险种名称" show-overflow-tooltip/>
            <el-table-column align="center" prop="date" label="保额/份数" show-overflow-tooltip/>
            <el-table-column align="center" prop="policyStatus" label="期交保费" show-overflow-tooltip/>
            <el-table-column align="center" prop="special" label="生效日期" show-overflow-tooltip/>
            <el-table-column align="center" prop="special" label="交至日期" show-overflow-tooltip/>
            <el-table-column align="center" prop="special" label="险种状态" show-overflow-tooltip/>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column align="center" width="55" type="selection"/>
      <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
      <el-table-column align="center" prop="riskName" label="被保人姓名" show-overflow-tooltip/>
      <el-table-column align="center" prop="date" label="证件号码" show-overflow-tooltip/>
      <el-table-column align="center" prop="policyStatus" label="客户号" show-overflow-tooltip/>
      <el-table-column align="center" prop="special" label="生效日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="special" label="交至日期" show-overflow-tooltip/>
      <el-table-column align="center" prop="special" label="交费间隔" show-overflow-tooltip/>
      <el-table-column align="center" prop="special" label="期交保费" show-overflow-tooltip/>
      <el-table-column align="center" prop="special" label="保单状态" show-overflow-tooltip/>
    </el-table>
    <el-form ref="baseForm" :rules="baseFormRule" :model="baseForm" style="padding-top: 30px;" label-width="110px" size="mini">
      <el-row v-if="node!=='policy'">
        <el-col :span="8">
          <el-form-item label="操作类型：" prop="insuredName">
            <el-select v-model="baseForm.cardType" class="item-width" placeholder="请选择">
              <el-option label="一" value="1"/>
              <el-option label="二" value="2"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="处理项目：" prop="insuredName">
            <el-select v-model="baseForm.cardType" class="item-width" placeholder="请选择">
              <el-option label="一" value="1"/>
              <el-option label="二" value="2"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="领款账号：" prop="insuredName">
            <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="退款金额：" prop="insuredName">
            <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-button size="mini" type="primary" @click="calcHandle">试 算</el-button>
        </el-col>
        <el-col :span="24">
          <el-form-item label="处理原因：" prop="insuredName">
            <el-input v-model="baseForm.insuredName" type="textarea" maxlength="30" show-word-limit clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-else>
        <el-card shadow="never">
          <div slot="header" class="clearfix">
            <span>结论信息</span>
          </div>
          <el-row v-if="handleType==='01'">
            <el-col :span="8">
              <el-form-item label="领款方式：" prop="insuredName">
                <el-select v-model="baseForm.cardType" class="item-width" placeholder="请选择">
                  <el-option label="一" value="1"/>
                  <el-option label="二" value="2"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="领款人：" prop="insuredName">
                <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="银行名称：" prop="insuredName">
                <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="银行账号：" prop="insuredName">
                <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="确认账号：" prop="insuredName">
                <el-input v-model="baseForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="处理结论：" prop="insuredName">
                <el-select v-model="baseForm.cardType" class="item-width" placeholder="请选择">
                  <el-option label="一" value="1"/>
                  <el-option label="二" value="2"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="结论说明：" prop="insuredName">
                <el-input v-model="baseForm.insuredName" type="textarea" maxlength="30" show-word-limit clearable size="mini" placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="mini" type="primary" @click="submitHandle">处理完毕</el-button>
      <el-button size="mini" @click="dialogClosed">返 回</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  props: {
    value: Boolean,
    node: String,
    policyInfo: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  data() {
    return {
      baseForm: {},
      baseFormRule: {},
      handleType: '01', // 解约（01）/保费豁免（02）（根据带过来的数据判断）
      tableData: [
        { policyNo: 'ERF23423645', subTable: [{ policyNo: 'ERF23423645' }] }
      ],
      lists: ['门诊费用', '住院费用', '医疗津贴', '轻症疾病', '重大疾病'],
      subTable: [{ policyNo: 'ERF23423645', insuredName: '小米', renewal: '是' }],
      totalCount: 0,
      pageInfo: {
        page: 1,
        pageSize: 10
      }
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
    },
    relationHandle() {},
    sizeChange(val) {
      this.pageInfo.pageSize = val
    },
    pageChange(val) {
      this.pageInfo.page = val
    },
    saveHandle() {},
    submitHandle() {},
    calcHandle() {}
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.expand_span {
  font-size: 12px;
}
</style>
