<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="材料清单"
    width="76%">
    <el-form v-if="flag" ref="searchForm" :rules="searchFormRule" :model="searchForm" size="mini">
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="searchForm.tableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <el-table-column property="policyNo" label="材料类型" align="center"/>
        <el-table-column property="insuredName" label="渠道来源" align="center"/>
        <el-table-column property="riskName" label="提交时间" align="center"/>
        <el-table-column property="sfyj" label="是否原件" align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-switch v-model="scope.row.sfyj"/>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column property="sftj" label="是否提交" align="center">
          <template slot-scope="scope">
            <el-form-item prop="sftj">
              <el-switch v-model="scope.row.sftj"/>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column property="sftj" label="是否退件" align="center">
          <template slot-scope="scope">
            <el-form-item prop="sftuij">
              <el-switch v-model="scope.row.sftuij"/>
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
      <el-row style="padding-top: 20px; border-bottom: 1px solid #e6ebf5; margin-bottom: 20px; line-height: 50px;">
        <el-col :span="20">退件信息</el-col>
        <el-col :span="4" style="text-align: right;">
          <el-button size="mini" type="primary" @click="saveHandle">保 存</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label-width="120px" label=" 退件人：" prop="name">
            <el-input v-model="searchForm.name" class="item-width" autocomplete="off"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label-width="120px" label="手机：" prop="id">
            <el-input v-model="searchForm.id" class="item-width" autocomplete="off"/>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label-width="120px" label="地址：" prop="assress">
            <el-input v-model="searchForm.assress" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>
      <div style="text-align: right; margin-right: 10px;"/>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  props: {
    value: Boolean,
    flag: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      tableData: [
        { policyNo: 'SWD234215' }
      ],
      searchForm: {
        tableData: [
          { policyNo: 'ERF23423645', sftj: true, sfyj: false }
        ]
      },
      searchFormRule: {
        name: { required: true, message: '不能为空!', trigger: 'blur' },
        id: { required: true, message: '不能为空!', trigger: 'blur' },
        assress: { required: true, message: '不能为空!', trigger: 'blur' }
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
      this.$refs.searchForm.resetFields()
      this.$emit('input', false)
    },
    saveHandle() {
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
</style>
