<template>
  <el-dialog
    :visible.sync="dialogVis"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="被保人信息"
    width="76%">
    <el-form label-width="150px">
      <el-row>
        <!-- <el-col :span="8">
          <el-form-item label="被保人姓名：">
            <span>{{ insuredInfo.insuredName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：">
            <span>{{`${dict.label.registrantidtype[insuredInfo.idType ]}`}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="身份证号：">
            <span>{{ insuredInfo.idNo }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：">
            <span>{{ `${dict.label.sex[insuredInfo.sex]}`}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话：">
            <span>{{ insuredInfo.phone }}</span>
          </el-form-item>
        </el-col> -->
        <!-- <el-col :span="8">
          <el-form-item label="邮箱：">
            <span>{{ insuredInfo.email }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：">
            <span>{{ insuredInfo.birthdate }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="国籍：">
            <span>{{ insuredInfo.nation }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="职业类别：">
            <span>{{ insuredInfo.accountname }}</span>
          </el-form-item>
        </el-col> -->
        <!-- <el-col :span="8">
          <el-form-item label="手机号码：">
            <span>{{ insuredInfo.mobie }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="固定电话：">
            <span>{{ insuredInfo.number }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="社保号码：">
            <span>{{ insuredInfo.shebao }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="详细地址：">
            <span>{{ insuredInfo.address }}</span>
          </el-form-item>
        </el-col> -->
        <el-col :span="8">
          <el-form-item label="开户银行：">
            <span>{{ insuredInfo.bankname }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="户名：">
            <span>{{ insuredInfo.accountName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账号：">
            <span>{{ insuredInfo.accnumber }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系地址（省）：">
            <span>{{ insuredInfo.province }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系地址（市）：">
            <span>{{ insuredInfo.city }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系地址（区/县）：">
            <span>{{ insuredInfo.district }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="详细地址：">
            <span>{{ insuredInfo.address }}</span>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="dialogClosed">关 闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    value: Boolean,
    insuredInfo: {
      type: Object,
      default: function() {
        return {}
      }
    } // 上个页面带过来的值
  },
  data() {
    return {}
  },
  computed: {
    dialogVis: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
    }
  }
}
</script>
<style scoped>
  .el-row /deep/ .el-col-8 {
    height: 40px;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  span {
    color: #555;
    line-height: 2.5;
  }
</style>
