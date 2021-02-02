<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1">
        <template slot="title">
          <span style="font-size:16px;color:black">{{title}}</span>
          <span v-show="!activeNames.length"
                style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
        </template>
        <el-form :disabled="status==='show'?true:false || acceptFlag || node ==='physical'"
                 style="padding-bottom: 30px;" ref="tableForm" :model="caseRemark" :rules="tableFormRules" size="mini"
                 class="from_class_div">
          <el-row>
            <el-table
              ref="table"
              size="small"
              highlight-current-row
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              tooltip-effect="dark"
              :data="caseRemark.data"
              style="width: 100%;">
              <el-table-column v-if="node!=='report'" label="备注类型" width="200px" prop="remarkstype" align="center">
                <template slot-scope="scope">
                  <!-- <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.remarkstype'"
                    :rules="{ required: true, message: '不能为空'}">
                    <el-select style="width: 140px;" v-model="scope.row.remarkstype" placeholder="请选择">
                      <el-option v-for="item in dict.remarks_type" :key="item.value" :label="item.label" :value="item.value"/>
                    </el-select>
                  </el-form-item> -->
                  <span v-if="scope.row.editFlag">备注</span>
                  <span v-else>{{getRemarks_type(scope.row.remarkstype) }}</span>
                </template>
              </el-table-column>
              <el-table-column label="备注内容" prop="remark" align="center">
                <template slot-scope="scope">
                  <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.remark'"
                                :rules="tableFormRules.remark">
                    <el-input maxlength="1000" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" show-word-limit
                              v-model="scope.row.remark" placeholder="请输入" size="mini"/>
                  </el-form-item>
                  <el-form-item v-else>
                    <el-tooltip :content="scope.row.remark" placement="top" effect="light">
                      <p class="form-span" style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">{{
                        scope.row.remark }}</p>
                    </el-tooltip>
                  </el-form-item>
                  <!-- <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.remark'"
                    :rules="{ required: true, message: '不能为空'}">
                    <el-input maxlength="1000" show-word-limit v-model="scope.row.remark" placeholder="请输入" size="mini"/>
                  </el-form-item>
                  <span class="form-span" v-else>{{ scope.row.remark }}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="添加岗位" prop="casestep" width="200px" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span v-if="scope.row.editFlag" class="form-span">{{ scope.row.casestep }}</span>
                    <span class="form-span">{{ getTasknode(scope.row.casestep) }}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="添加人" prop="creator" width="120px" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span class="form-span">{{ scope.row.creator }}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="添加时间" prop="createdate" width="200px" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span class="form-span">{{ scope.row.createdate }}</span>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="120px" align="center">
                <template slot-scope="scope">
                  <el-form-item v-if="scope.row.editFlag">
                    <el-button type="text" v-if="!scope.row.remarksno" @click="delHandle(scope.$index,scope.row)">取消
                    </el-button>
                    <el-button type="text" @click="saveCaseRemark(scope.$index,scope.row)">保存</el-button>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
            <el-button :disabled="flag" @click="openDia" size="small"
                       style="margin-top:0.3%;font-size:14px;width: 100%;border-style: dashed"> + 添加
            </el-button>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <el-row v-if="node === 'report' || node === 'accept'">
      <span style="float: right; padding-top: 20px; padding-bottom: 30px">
        <el-button type="primary" size="mini" @click="goBack">关闭</el-button>
      </span>
    </el-row>
    <el-dialog title="添加备注" :visible.sync="dialogVisible" append-to-body
               :close-on-click-modal="false"
               :before-close="closeHandle">
      <el-form ref="diaForm" :model="form" label-width="100px">
        <el-form-item label="备注内容：" prop="remark"
                      :rules="{ required: true, message: '不能为空', trigger: 'blur'}">
          <el-input v-model="form.remark" maxlength="1000" :rows="5" show-word-limit style="width: 90%;" type="textarea"
                    autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="closeHandle">取 消</el-button>
        <el-button size="mini" type="primary" @click="addHandel">确 定</el-button>
      </span>
    </el-dialog>
  </el-card>
</template>
<script>
  import Cookies from 'js-cookie'
  import {getCaseRemark, saveCaseRemark} from "@/api/claim/handleDeal"
  import moment from 'moment'

  export default {
    //dicts: ['casestep', 'remarks_type', 'tasknode'],
    props: {
      dictList: Array,
      status: String,
      node: String,
      claimno: String,
      flag: Boolean,
      acceptFlag: {
        type: Boolean,
        default: false
      },
      caseRemark: {
        type: Object,
        default: () => {
          return {
            data: []
          }
        }
      }
    },
    inject: ['getCaseRemark'],
    data() {
      return {
        dialogVisible: false,
        title: this.node === 'report' ? '报案备注' : '赔案备注',
        activeNames: ['1'],
        form: {
          remark: ''
        },
        saveRemark: false,
        user: this.$store.state.user.user,
        tableFormRules: {
          remark: [{required: true, message: '不能为空!', trigger: 'change'}],
        },
        casestepOptions: [],
        remarks_typeOptions: [],
        tasknodeOptions: [],
      }
    },
    mounted() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.casestepOptions = this.dictList.find(item => {
          return item.dictType == 'casestep'
        }).dictDate
        this.remarks_typeOptions = this.dictList.find(item => {
          return item.dictType == 'remarks_type'
        }).dictDate
        this.tasknodeOptions = this.dictList.find(item => {
          return item.dictType == 'tasknode'
        }).dictDate
      }
    },
    methods: {
      getCasestepLabel() {
        let nodeLabel = this.casestepOptions.find((item) => {
          return item.dictLabel === this.node
        })
        switch (nodeLabel.dictLabel) {
          case 'report':
            return '报案'
          case 'accept':
            return '受理'
          case 'classificat':
            return '分类'
          case 'input':
            return '录入'
          case 'firstTrial':
            return '初审'
          case 'review':
            return '审核'
          case 'complex':
            return '复核'
          case 'spotCheck':
            return '抽检'
          case 'correct':
            return '审批'
          default:
            return nodeLabel
        }
      },
      goBack() {
        //window.close()
        //this.$router.go(-1)
        this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
        this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)
      },
      openDia() {
        let flied = {
          id: '',
          editFlag: true,
          claimno: this.claimno,
          remarkstype: '04',
          remark: this.form.remark,
          createdate: moment(new Date()).format('YYYY-MM-DD HH:mm'),
          creator: Cookies.get("username"),
          casestep: this.getCasestepLabel(),
        }
        this.caseRemark.data.push(flied)
      },
      closeHandle() {
        this.$refs.diaForm.resetFields()
        this.dialogVisible = false
      },
      addHandel() {
        this.$refs.diaForm.validate((valid) => {
          if (valid) {
            const field = {
              editFlag: true,
              claimno: this.claimno,
              remarkstype: '04',
              remark: this.form.remark,
              createdate: moment(new Date()).format('YYYY-MM-DD HH:mm'),
              creator: Cookies.get("username"),
              // dept: this.user.dept,
              casestep: this.getCasestepLabel(),
            }
            this.caseRemark.data.push(field)
            setTimeout(() => {
              this.$refs.table.setCurrentRow(field)
            }, 10)
            this.dialogVisible = false
          } else {
            return false
          }
        })
      },
      delHandle(index, row) {
        this.$confirm(`是否确定取消?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.caseRemark.data.splice(index, 1)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      saveCaseRemark(i, row) {
        this.$refs.tableForm.validate((valid) => {
          if (valid) {
            if (this.claimno == null || this.claimno == undefined || this.claimno == '') {
              this.$message.warning('请先保存被保人信息！')
              return false
            }
            let params = {
              ...row,
              remarkstype: '04',
              claimno: this.claimno
            }
            let casestep = this.casestepOptions.find((item) => {
              return item.dictLabel === this.node
            })
            params.casestep = casestep.dictValue || ''
            if (this.node === 'report') {
              params.remarkstype = '01'
            }
            delete params.editFlag
            saveCaseRemark(params).then(res => {
              if (res.status === "1") {
                this.$message({message: '操作成功！', type: 'success'})
                this.getCaseRemark()
                this.caseRemark.data[i].id = ''
              } else {
                this.$message({message: res.data.errMsg ? res.data.errMsg : '操作失败！', type: 'error'})
              }
            })
          } else {
            return false
          }
        })
      },
      getRemarks_type(value) {
        return this.selectDictLabel(this.remarks_typeOptions, value)
      },
      getTasknode(value) {
        return this.selectDictLabel(this.tasknodeOptions, value)
      },
    }
  }
</script>
<style scoped>
  .el-collapse {
    border: none;
  }

  .el-textarea ::v-deep .el-form-item__content {
    line-height: 1;
  }

  .el-textarea ::v-deep .el-input__count {
    height: 20px;
    line-height: 20px;
    right: 18px
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    font-size: 16px;
    color: #555;
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-weight: 400;
    font-size: 14px;
  }

  .form-span {
    font-size: 12px;
  }

  /* .from_class_div .el-form-item--mini.el-form-item{
    height: 30px;
  } */
</style>
