<template>
  <el-dialog
    :visible.sync="policeHoder"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="投保人信息"
    width="1000px">
    <el-form label-width="150px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="投保人客户号：">
            <span>{{ insuredInfo.appntNo }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投保人姓名：">
            <span>{{ insuredInfo.appntName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投保人性别：">
            <span>{{/*dict.label.sex[insuredInfo.appntSex]*/ this.selectDictLabel(this.sexOptions, insuredInfo.appntSex)}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：">
            <span>{{ insuredInfo.appntBirthday }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：">
            <span>{{ /*dict.label.card_type[insuredInfo.appntIdType]*/ this.selectDictLabel(this.card_typeOptions,insuredInfo.appntIdType)}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码：">
            <span>{{ insuredInfo.appntIdNo }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话：">
            <span>{{ insuredInfo.phone }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱(Email):">
            <span>{{ insuredInfo.email }}</span>
          </el-form-item>
        </el-col>
<!--        <el-col :span="8">-->
<!--          <el-form-item label="职业类别：">-->
<!--            <span>{{ dict.label.payee_occupation[insuredInfo.nation] }}</span>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="缴费方式：">-->
<!--            <span>{{ dict.label.payMethods[insuredInfo.payMode]}}</span>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :span="8">
          <el-form-item label="开户银行：">
            <span>{{ insuredInfo.bankName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账户名：">
            <span>{{ insuredInfo.accName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账号：">
            <span>{{ insuredInfo.accNo }}</span>
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
  dicts: ['registrantidtype', 'sex', 'payMethods','payee_occupation','card_type'],
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
    return {
      /*'registrantidtype', 'sex', 'payMethods','payee_occupation','card_type'*/
      registrantidtypeOptions: [],
      sexOptions: [],
      payMethodsOptions: [],
      payee_occupationOptions: [],
      card_typeOptions: []
    }
  },
  computed: {
    policeHoder: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  mounted() {
    this.getDicts("registrantidtype").then(response => {
      this.registrantidtypeOptions = response.data;
    });
    this.getDicts("sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("payMethods").then(response => {
      this.payMethodsOptions = response.data;
    });
    this.getDicts("payee_occupation").then(response => {
      this.payee_occupationOptions = response.data;
    });
    this.getDicts("card_type").then(response => {
      this.card_typeOptions = response.data;
    });
    },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
    },
    // 获取身份类型码表
    getRegistrantidtype(row, col) {
      /*return this.dict.label.registrantidtype[row.registrantidtype]*/
      return this.selectDictLabel(this.registrantidtypeOptions, row.registrantidtype)
    },
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
