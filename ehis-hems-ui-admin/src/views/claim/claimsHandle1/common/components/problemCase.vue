<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <span style="font-size:16px;color:black">问题件</span>
        <div style="float: right;">
          <el-button v-if="status==='edit'" type="primary" size="mini" @click="confirmSave">确认</el-button>
        </div>
      </div>
    </div>
    <el-form ref="caseForm" :model="caseForm" label-width="120px" size="mini" :rules="caseFormRules"
             :disabled="node === 'accept' && status === 'show'">
      <el-row>
        <el-col :span="8">
          <el-form-item label="问题件类型：" prop="problemType">
            <el-select v-model="caseForm.problemType" class="item-width" placeholder="请选择">
              <el-option v-for="option in problem_shipment_typeOptions" :key="option.dictValue"
                         :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="转出意见：" prop="problemView">
            <el-input type="textarea" style="width: 635px" :rows="4" maxlength="1000" v-model="caseForm.problemView"
                      clearable/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

  </el-card>
</template>
<script>
  import {editCaseInfoSuspend, addProblem} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'problem_shipment_type'},]

  export default {
    props: {
      fixInfo: Object,
      sonProblemData: Array,
      status: String,
      node: String,
    },
    watch: {
      sonProblemData: function (newVal) {
        if (newVal !== null && newVal !== undefined && newVal.length > 0) {
          this.caseForm = newVal[0]
        }
      }
    },
    data() {
      return {
        activeNames: ["1"],
        amountFlag: false,
        beneficVis: false,
        caseForm: {
          problemType: undefined,
          problemView: undefined,
        },
        caseFormRules: {
          problemType: {required: true, message: '问题件类型不能为空', trigger: 'change'},
          problemView: {required: true, message: '转出意见不能为空', trigger: 'change'},
        },
        dictList: [],
        problem_shipment_typeOptions: [],
      };
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.problem_shipment_typeOptions = this.dictList.find(item => {
        return item.dictType === 'problem_shipment_type'
      }).dictDate
    },
    methods: {
      confirmSave() {
        this.$refs.caseForm.validate((valid) => {
          if (valid) {
            this.$confirm(`是否发起问题件?`, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              let data = {
                rptNo: this.fixInfo.rptNo,
                problemType: this.caseForm.problemType,
                problemView: this.caseForm.problemView,
              }
              addProblem(data).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '发起问题件成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$router.push({path: '/claims-handle/pbclaim'});
                }
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消！'
              })
            })
          } else {
            return this.$message.warning(
              "请录入必录项！"
            )
          }
        })


      }
    }
  };
</script>
<style scoped>
  .item-width {
    width: 200px;
  }

  .el-collapse {
    border: none;
  }

  .el-tabs /deep/ .el-tabs__item {
    width: 10%;
  }

  .el-collapse /deep/ .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-size: 14px;
  }
</style>
