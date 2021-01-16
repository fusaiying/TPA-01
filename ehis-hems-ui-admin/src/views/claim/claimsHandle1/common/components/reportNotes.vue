<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <span style="font-size:16px;color:black">赔案备注</span>
        <div style="float: right;">
          <el-button type="primary" size="mini" @click="addOrEdit('add')" >新增</el-button>
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
      <el-table-column align="center" prop="station" label="添加岗位" show-overflow-tooltip/>
      <el-table-column align="center" prop="createBy" label="添加人" show-overflow-tooltip/>
      <el-table-column align="center" prop="createTime" label="添加时间" show-overflow-tooltip/>
      <el-table-column align="center" v-if="" label="操作" width="140">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="addOrEdit('edit',scope.row)">编辑</el-button>
          <el-button size="mini" style="color: red" type="text" @click="deleteInfo(scope.row)">删除</el-button>
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
                <el-select v-model="baseForm.remarkType" class="item-width" placeholder="请选择">
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
  let dictss = [{dictType: 'claim_note'}]
  import {delRemark, addRemark,updateRemark} from '@/api/claim/handleCom'

  export default {
    props: {
      reportData: Array,
      status: String,
      node: String,
      claimno: String,
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
    watch: {},
    data() {
      return {
        isAddOrEdit:'',
        dialogFormVisible: false,
        activeNames: ["1"],
        amountFlag: false,
        beneficVis: false,
        baseForm: {
          rptNo:'',
          remarkType: '',
          remark: '',
          station: '',
          createBy: '',
          createTime: '',
        },
        rule: {
          remarkType: {required: true, message: ' ', trigger: 'change'},
          remark: {required: true, message: ' ', trigger: 'change'},
        },
        dictList: [],
        claim_noteOptions: [],
      };
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claim_noteOptions = this.dictList.find(item => {
        return item.dictType === 'claim_note'
      }).dictDate

    },
    methods: {
      openBeneficDia() {
        this.beneficVis = true
      },
      payamountChange(val, index) {
        this.$set(this.payeeInfo.data[index], 'payoutratio', null)
      },
      payoutratioChange(val, index) {
        // this.$set(this.payeeInfo.data[index],'payamount',null)
      },

      saveRemark() {
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            if (this.isAddOrEdit==='add'){
              this.baseForm.rptNo=this.rowData.rptNo
              addRemark(this.baseForm).then(res => {
                if (res != null && res.code === 200) {
                  this.reportData=res.rows
                }
                this.dialogFormVisible = false
              }).catch(res => {
              })
            }else if (this.isAddOrEdit==='edit'){
              updateRemark(this.baseForm).then(res => {
                if (res != null && res.code === 200) {
                  this.reportData=res.rows
                }
                this.dialogFormVisible = false
              }).catch(res => {
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
        delRemark(row.remarkId).then(res => {

        })
      },
      addOrEdit(status,row){
        this.baseForm= {
          rptNo:'',
          remarkType: '',
          remark: '',
          station: '',
          createBy: '',
          createTime: '',
        }
        this.dialogFormVisible=true
        if (status==='add'){
          this.isAddOrEdit='add'

        }else if (status==='edit'){
          this.isAddOrEdit='edit'
          this.baseForm=row
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
