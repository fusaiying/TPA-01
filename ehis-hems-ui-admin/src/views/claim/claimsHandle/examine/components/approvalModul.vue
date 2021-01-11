<template>
  <el-dialog
    :visible.sync="dialogVis"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    :show-close="false"
    @open="openHandle"
    style="padding-bottom: 30px"
    width="70%">
    <p>
      <span>补材任务列表</span>
      <span><el-button style="float: right" type="primary" size="mini" @click="dialogClosed">返回</el-button></span>
    </p>
    <el-table
      size="small"
      highlight-current-row
      :data="tableList"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" type="index" label="序号" width="50"></el-table-column>
      <el-table-column :formatter="formatImageType" align="center" prop="materialtype" label="材料类型" show-overflow-tooltip></el-table-column>
      <el-table-column :formatter="formatSupplementmode" align="center" prop="supplementarymode" label="推送方式" show-overflow-tooltip></el-table-column>
      <el-table-column :formatter="getStatusSupple" align="center" prop="status" label="补材状态" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="remarks" label="备注" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="createdate" label="发起时间" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="creator" label="操作人" show-overflow-tooltip></el-table-column>
    </el-table>
    <el-form style="margin-top: 40px;" ref="approvalForm" :disabled="showFlag" label-width="100px" :rules="approvalFormRules" :model="approvalForm" size="mini">
      <el-form-item label="审批结论：" prop="reason">
        <el-select v-model="approvalForm.reason" placeholder="请选择" @change="changeReason">
          <el-option label="通过" value="0"></el-option>
          <el-option label="不通过" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="结论说明：" prop="mark">
        <el-input :rows="2" type="textarea" style="width: 80%;" maxlength="100" @input="changeMark" show-word-limit v-model="approvalForm.mark" placeholder="请输入" size="mini"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="confirmHandel" :disabled="showFlag">审批确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {updateApprovalsupplementaryMaterial, getApprovalsupplementaryMaterial} from '@/api/examine.js'
export default {
  dicts: ['supplementmode', 'image_type','claim_status','status_supple'],
  props: {
    value: Boolean,
    showFlag: Boolean,
     row: {
      type: Object,
      default: function() {
        return {}
      }
    }
  },
  data() {
    return {
      approvalFormRules: {
        reason: [{ required: true, message: '不能为空!', trigger: 'change' }],
        mark: [{ required: true, message: '不能为空!', trigger: 'blur' }]
      },
      tableList: [],
      approvalForm: {
        reason: '',
        mark: ''
      }
    }
  },
  computed: {
    dialogVis: {
      set() {},
      get() {
        return this.value
      }
    },
  },
  mounted() {},
  methods: {
      formatSupplementmode(row) {
          let reason = ''
          if (row.supplementarymode) {
              row.supplementarymode.split(',').map((item, i) => {
                  this.dict.label.supplementmode[item] ? reason += `|${this.dict.label.supplementmode[item]}` : reason = ''
              })
          }
          return reason.slice(1)
      },
      formatImageType(row) {
          let reason = ''
          if (row.materialtype) {
              row.materialtype.split('|').map((item, i) => {
                  this.dict.label.image_type[item] ? reason += `|${this.dict.label.image_type[item]}` : reason = ''
              })
          }
          return reason.slice(1)
      },
    openHandle(){
      let params = {
        claimno: this.row.claimNo || '',
        supplementaryno : this.row.supplementaryNo || ''
      }
      getApprovalsupplementaryMaterial(params).then(res => {
        this.tableList = (res && res.data && res.data.supplementaryDetailResponseDOList) || []
        this.approvalForm.reason = (res && res.data && res.data.conclusion) || ''
        if (res && res.data && res.data.conclusion) {
          if (res.data.conclusion == '01' || res.data.conclusion == '02' || res.data.conclusion == '03') {
            this.$set(this.approvalForm, 'reason', '0')
          }
        }
        this.approvalForm.mark = (res && res.data && res.data.explanation) || ''
      })
    },
    dialogClosed() {
      this.$refs.approvalForm.resetFields()
      this.$emit('input', false)
    },
    confirmHandel(){
      this.$refs.approvalForm.validate((valid) => {
        if (valid) {
         let params = {}
         console.log(this.row.claimNo)
         if (!this.row.claimNo) {
           this.$message.warning('赔案号为空，不可进行审批！')
           return
         }
         if (!this.row.supplementaryNo) {
           this.$message.warning('补材号为空，不可进行审批！')
           return
         }
         params.conclusion = this.approvalForm.reason || ''
         params.explanation = this.approvalForm.mark || ''
         params.supplementaryno = this.row.supplementaryNo || ''
         params.claimno = this.row.claimNo || ''
         updateApprovalsupplementaryMaterial(params).then(res =>{
           if (res.status == '1') {
            this.$emit('input', false)
            this.$message({ message: '审批成功！', type: 'success', showClose: true, center: true })
             this.$emit('searchHandle')
           } else {
            this.$message({ message: '审批失败', type: 'error', showClose: true, center: true })
           }
         })
        } else {
          return false;
        }
      })
    },
    changeReason(data) {
      this.$set(this.approvalForm, 'reason',data)
      this.$forceUpdate()
    },
    changeMark(data) {
      this.$set(this.approvalForm, 'mark',data)
      this.$forceUpdate()
    },
    // 赔案状态
    getClaimStatus(row, col) {
      return this.dict.label.claim_status[row.status]
    },
    // 补材状态
  getStatusSupple(row, col) {
      return this.dict.label.status_supple[row.status]
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
