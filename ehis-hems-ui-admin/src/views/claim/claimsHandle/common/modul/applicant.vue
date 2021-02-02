<template>
  <el-dialog
    :visible.sync="dialogVis"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="申请人信息"
    width="76%">
    <el-form label-width="150px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="与被保人关系：">
            <span>{{ insuredInfo.name }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="申请方式：">
            <span>{{ insuredInfo.idType }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="申请人姓名：">
            <span>{{ insuredInfo.idno }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：">
            <span>{{ insuredInfo.sex }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码：">
            <span>{{ insuredInfo.birthdate }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：">
            <span>{{ insuredInfo.sex }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话：">
            <span>{{ insuredInfo.phone }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号码：">
            <span>{{ insuredInfo.mobie }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱：">
            <span>{{ insuredInfo.email }}</span>
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
  .el-row ::v-deep .el-col-8 {
    height: 40px;
  }
  .el-form-item ::v-deep label {
    font-weight: normal;
  }
  span {
    color: #555;
    line-height: 2.5;
  }
</style>
