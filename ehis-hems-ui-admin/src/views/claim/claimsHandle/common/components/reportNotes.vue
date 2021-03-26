<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <span style="font-size:16px;color:black">赔案备注</span>
        <div style="float: right;">
          <el-button v-if="status==='edit'" type="primary" size="mini" @click="addOrEdit('add')" >新增</el-button>
        </div>
      </div>
    </div>
    <el-table
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="reportData"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" width="140" prop="remarkType" label="备注类型" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ selectDictLabel(claim_noteOptions, scope.row.remarkType) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="remark" label="备注内容" show-overflow-tooltip/>
      <el-table-column align="center" prop="station" label="添加岗位" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ selectDictLabel(job_typeOptions, scope.row.station) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="createBy" label="添加人" show-overflow-tooltip/>
      <el-table-column align="center" prop="createTime" label="添加时间" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" v-if="status==='edit'" label="操作" width="140"><!--batchInfo.claimtype==='02' && -->
        <template slot-scope="scope" >
          <el-button :disabled="user!==scope.row.createBy && scope.row.station != '08'" size="mini" type="text" @click="addOrEdit('edit',scope.row)">编辑</el-button>
          <el-button :disabled="user!==scope.row.createBy || scope.row.station === '08'" size="mini" style="color: red" type="text" @click="deleteInfo(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogFormVisible" width="30%" hight="40%">
      <div>
        <el-form ref="baseForm" :rules="rule"
                 :model="baseForm" style="padding-bottom: 30px;" label-width="136px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="备注类型：" prop="remarkType">
                <el-select :disabled="baseForm.station==='08'" v-model="baseForm.remarkType" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="option in claim_noteOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="备注内容：" prop="remark">
                <el-input v-model="baseForm.remark" class="item-width" maxlength="1000" clearable size="mini"
                          placeholder="请输入"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="primary" @click="saveRemark">确认</el-button>
        </div>
      </div>
    </el-dialog>

  </el-card>
</template>

<script>
  let dictss = [{dictType: 'claim_note'},{dictType: 'job_type'}]
  import {delRemark, addRemark,updateRemark,getRemarkRptNo} from '@/api/claim/handleCom'

  export default {
    props: {
      batchInfo:Object,
      reportDatas: Object,
      status: String,
      node: String,
      claimno: String,
      fixInfo:Object,
      isAppeal: {
        type: Boolean,
        default: function () {
          return false
        }
      },
      payeeInfo: {
        type: Object,
        default: function () {
          return {};
        }
      },
      insuredInfo: Object,
      finalamount: String
    },
    watch: {
      reportDatas:function (newValue) {
        this.reportData=newValue.remarkList
        this.user=newValue.userName
      }
    },
    data() {
      return {
        user:'',
        reportData:[],
        isAddOrEdit:'',
        dialogFormVisible: false,
        activeNames: ["1"],
        amountFlag: false,
        beneficVis: false,
        baseForm: {
          rptNo:'',
          remarkType: '',
          claimCaseAccept:'',
          remark: '',
          station: '',
          createBy: '',
          createTime: '',
        },
        rule: {
          remark: {required: true, message: ' ', trigger: 'change'},
        },
        dictList: [],
        claim_noteOptions: [],
        job_typeOptions: [],
      };
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claim_noteOptions = this.dictList.find(item => {
        return item.dictType === 'claim_note'
      }).dictDate
      this.job_typeOptions = this.dictList.find(item => {
        return item.dictType === 'job_type'
      }).dictDate

    },
    methods: {
      openBeneficDia() {
        this.beneficVis = true
      },
      payamountChange(val, index) {
        this.$set(this.payeeInfo.data[index], 'payoutratio', null)
      },
      saveRemark() {
        if(this.baseForm.station !== '08') {
          if (this.node==='accept'){
            this.baseForm.station='01'
          }else if (this.node==='input'){
            this.baseForm.station='02'
          }else if(this.node==='calculateReview'){
            this.baseForm.station='03'
          }else if(this.node==='sport'){
            this.baseForm.station='04'
          }
        }

        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            if (this.isAddOrEdit==='add'){
              this.baseForm.rptNo=this.fixInfo.rptNo
              addRemark(this.baseForm).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                }
                this.dialogFormVisible = false
                getRemarkRptNo(this.fixInfo.rptNo).then(res => {
                  if (res != null && res.code === 200) {
                    this.reportData = res.data.remarkList
                  }
                }).catch(res => {
                })
              }).catch(res => {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              })
            }else if (this.isAddOrEdit==='edit'){
              updateRemark(this.baseForm).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '修改成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                }
                this.dialogFormVisible = false
                getRemarkRptNo(this.fixInfo.rptNo).then(res => {
                  if (res != null && res.code === 200) {
                    this.reportData = res.data.remarkList
                  }
                }).catch(res => {
                })
              }).catch(res => {
                this.$message({
                  message: '修改失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              })
            }

          } else {
            return this.$message.warning(
              "请录入必录项！"
            )
          }
        })

      },
      deleteInfo(row) {
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delRemark(row.remarkId).then(res => {
            if (res !== null && res.code === 200) {
              this.$message({
                message: '删除成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              getRemarkRptNo(this.fixInfo.rptNo).then(res => {
                if (res != null && res.code === 200) {
                  this.reportData = res.data.remarkList
                }
              }).catch(res => {
              })
            } else {
              this.$message({
                message: '删除失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          }).catch(res => {
          })
          this.$emit("refresh-item", 'report')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })

      },
      addOrEdit(status,row){
        this.dialogFormVisible=true
        if (status==='add'){
          this.baseForm= {
            rptNo:'',
            remarkType: '01',
            remark: '',
            station: '',
            createBy: '',
            createTime: '',
            claimCaseAccept:'',
          }
          this.isAddOrEdit='add'

        }else if (status==='edit'){
          this.isAddOrEdit='edit'
          this.baseForm.rptNo=row.rptNo
          this.baseForm.remarkType=row.remarkType
          this.baseForm.station=row.station
          this.baseForm.createBy=row.createBy
          this.baseForm.createTime=row.createTime
          this.baseForm.claimCaseAccept=''
          this.baseForm.remark=row.remark
          this.baseForm.status=row.status
          this.baseForm.remarkId=row.remarkId
          this.baseForm.remarkType=row.remarkType
        }
      },
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

  .el-tabs ::v-deep .el-tabs__item {
    width: 10%;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-size: 14px;
  }
</style>
