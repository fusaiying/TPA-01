<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="处理结论"
    width="50%"
    @open="open">
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="tableData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      @row-click="reflexionData">
      <el-table-column type="index" width="50" label="序号" align="center"/>
      <el-table-column prop="proccess" label="过程信息" align="center"/>
      <el-table-column prop="createdate" label="添加时间" align="center"/>
    </el-table>
    <el-form ref="baseForm" :disabled="showFlag" :rules="baseFormRules" :model="baseForm" style="margin-top: 20px;" label-width="120px" size="mini">
      <el-row>
        <el-col :span="22">
          <el-form-item label="过程说明：" prop="proccess">
            <el-input :rows="2" v-model="baseForm.proccess" type="textarea" maxlength="100" show-word-limit/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="处理结论：" prop="conclusion">
            <el-cascader v-model="baseForm.conclusion" :options="reasontypes" class="item-width" placeholder="请选择"/>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="结论说明：" prop="explanation">
            <el-input :rows="2" v-model="baseForm.explanation" type="textarea" maxlength="100" show-word-limit/>
          </el-form-item>
        </el-col>
      </el-row>
      <div style="text-align: right; margin-right: 10px;"/>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="confirmHandel">确 认</el-button>
      <el-button type="primary" size="mini" @click="dialogClosed">返 回</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getByClaimnoComplete, addClaimConclusion } from '@/api/claim/specialCase'
import { get } from '@/api/dictDetail'
export default {
  dicts: ['report_disagreereason'],
  props: {
    value: Boolean,
    showFlag: Boolean,
    claimNo: String,
    specialreportNo: String
  },
  data() {
    return {
      baseForm: {
        proccess: '',
        conclusion: '',
        explanation: ''
      },
      baseFormRules: {
        proccess: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        conclusion: [{ required: true, message: '不能为空!', trigger: 'change' }],
        explanation: [{ required: true, message: '不能为空!', trigger: 'blur' }]
      },
      reasontypes: [],
      tableData: [],
      disagreeReason: []
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
  created() {
    this.getConclusionTypeData()
  },
  methods: {
    // 处理结论下拉选
    getConclusionTypeData() {
      const dictName = 'special_conclusion'
      get(dictName).then(response => {
        this.reasontypes = response.content
        this.getReportDisagreereason()
      })
    },
    // 不同意原因
    getReportDisagreereason() {
      const dictName = 'report_disagreereason'
      get(dictName).then(response => {
        this.disagreeReason = response.content
        this.reasontypes.forEach(item => {
          if (item.value === '02') {
            item.children = this.disagreeReason
          }
        })
      })
    },
    reflexionData(row) {
      this.baseForm = row
    },
    open() {
      this.disagreeReason = [...this.disagreeReason]
      if (this.showFlag) {
        getByClaimnoComplete(this.claimNo).then(response => {
          if (response.status === '1') {
            this.tableData = response.data
            this.$nextTick(function() {
              this.tableData.forEach(item => {
                if (!item.conclusion === '01') {
                  item.conclusion = ['02', item.conclusion]
                } else {
                  item.conclusion = [item.conclusion]
                }
              })
            })
            this.baseForm = this.tableData[this.tableData.length - 1]
          } else {
            this.$message({ message: '查询结果错误！', type: 'error' })
          }
        })
      }
    },
    dialogClosed() {
      this.$refs.baseForm.resetFields()
      this.$emit('input', false)
    },
    confirmHandel() {
      if (this.showFlag) {
        this.dialogClosed()
      } else {
        let validateform = true
        this.$refs.baseForm.validate(valid => {
          validateform = valid
        })
        if (!validateform) {
          return false
        }
        const params = {
          specialreportno: this.specialreportNo,
          claimno: this.claimNo,
          proccess: this.baseForm.proccess,
          conclusion: this.baseForm.conclusion[0] === '01' ? this.baseForm.conclusion[0] : this.baseForm.conclusion[1],
          explanation: this.baseForm.explanation
        }
        addClaimConclusion(params).then(response => {
          if (response.status === '1') {
            this.$message.success('保存成功')
            this.dialogClosed()
            this.$emit('getList1')
          }
        })
      }
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 200px;
  }
</style>
