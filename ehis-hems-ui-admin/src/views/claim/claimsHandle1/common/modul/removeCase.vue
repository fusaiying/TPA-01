<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="40%" height="35%">
      <el-form ref="baseForm" :model="baseForm" label-width="170px" size="mini" :rules="rule">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">撤件信息</span>
          <span style="float: right;">
          <el-button type="primary" size="mini" @click="save">确认</el-button>
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
        </div>
        <el-row>
          <el-col :span="16">
            <el-form-item label="撤件类型：" prop="pulloutType" style="position:relative">
              <el-radio-group v-model="baseForm.pulloutType">
                <el-radio v-for="option in pullout_typeOptions" :label="option.dictValue" v-if=""
                          :key="option.dictValue"
                          :value="option.dictLabel">{{ option.dictLabel }}
                </el-radio><!--受理岗02-撤件可申诉选择框隐藏，默认类型为撤件，不可修改-->
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="撤件原因：" prop="pulloutReason" style="position:relative">
              <el-input v-model="baseForm.pulloutReason" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="撤件描述：" prop="pulloutDescribe" style="position:relative">
              <el-input type="textarea" :rows="3" v-model="baseForm.pulloutDescribe" maxlength="1000"
                        class="item-widths" clearable
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {editCaseAndRecordInfoCancel} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'pullout_type'},]
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo:Object
    },

    data() {

      const checkPulloutDescribe = (rule, value, callback) => {
        if (!value) {
          /* if ()*/
          callback(new Error(" "))
        } else {
          callback()
        }
      }
      return {

        dialogVisable: false,

        baseForm: {
          rptNo:'',
          pulloutType: '',
          pulloutReason: '',
          pulloutDescribe: '',
        },
        rule: {
          pulloutType: [{required: true, message: ' ', trigger: 'blur'}],
          pulloutReason: [{required: true, message: ' ', trigger: 'blur'}],
          pulloutDescribe: [{validator: checkPulloutDescribe, trigger: 'blur'}],
        },
        dictList: [],
        pullout_typeOptions: [],

      }
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue

      }
    },


    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.pullout_typeOptions = this.dictList.find(item => {
        return item.dictType === 'pullout_type'
      }).dictDate
    },
    methods: {
      //关闭对话框
      changeDialogVisable() {
        //清空对话框中的数据
        this.$refs.baseForm.resetFields()
        this.$emit('closeRemoveDialog')
      },
      save() {
        this.baseForm.rptNo=this.fixInfo.rptNo
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            editCaseAndRecordInfoCancel().then(res=>{
              if(res!=null && res.code===200){
                this.$message({
                  message: '撤件成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.changeDialogVisable()
              }
            }).catch(res=>{
              this.$message({
                message: '撤件失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            })
          }else {
            if (this.baseForm.pulloutType==null || this.baseForm.pulloutType===''){
              return this.$message.warning(
                "请选择撤案类型！"
              )
            }else {
              return this.$message.warning(
                "录入不完整，请检查！"
              )
            }

          }
        })
      }
    }
  }
</script>

<style scoped>
  /deep/ .row-expand-cover .el-table__expand-column .el-icon {
    visibility: hidden;
  }

  .item-width {
    width: 200px;
  }

  .item-widths {
    width: 300px;
  }

  .duty-p {
    width: 170px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

</style>
