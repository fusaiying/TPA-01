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
                <!--受理岗02-撤件可申诉选择框隐藏，默认类型为撤件，不可修改-->

                <el-radio v-if="pullout_typeOptions.length>0" :key="pullout_typeOptions[0].dictValue" :label="pullout_typeOptions[0].dictValue "
                >{{ pullout_typeOptions[0].dictLabel }}
                </el-radio>
                <el-radio v-if="node!=='accept' && pullout_typeOptions.length>0" :key="pullout_typeOptions[1].dictValue" :label="pullout_typeOptions[1].dictValue"
                >{{ pullout_typeOptions[1].dictLabel }}
                </el-radio>
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
              <el-input type="textarea" :rows="3" v-model="baseForm.pulloutDescribe" maxlength="1000" show-word-limit
                        class="item-widths"
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {removeCase} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'pullout_type'},]
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo:Object,
      node:String,

    },

    data() {

      const checkPulloutDescribe = (rule, value, callback) => {
        if (!value) {
           if (this.baseForm.pulloutType==='01'){
             callback(new Error(" "))
           }else {
             callback()
           }
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
      },
      node: function (newValue) {
        if (newValue==='accept'){
          this.baseForm.pulloutType='01'
        }
      }
    },


    async created() {
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
            this.$confirm(`是否确定撤件?`, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              removeCase(this.baseForm).then(res=>{
                if(res!=null && res.code===200){
                  this.$message({
                    message: '撤件成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.changeDialogVisable()
                  this.$store.dispatch("tagsView/delView", this.$route);
                  this.$router.go(-1)
                }
              }).catch(res=>{
                this.$message({
                  message: '撤件失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消！'
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
  ::v-deep .row-expand-cover .el-table__expand-column .el-icon {
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
