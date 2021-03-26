<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="60%" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span v-if="initiateBtn">修正处理</span>
        <span v-if="!initiateBtn">初审处理</span>
        <span v-show="delBtn" style="float: right;">
           <el-button v-if="initiateBtn" type="primary" size="mini" @click="updateData('initiate')">确认发起</el-button>
           <el-button v-if="!initiateBtn" type="primary" size="mini" @click="updateData('audit')">初审确认</el-button>
           <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
        <span v-show="!delBtn" style="float: right;">
           <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
      </div>
      <el-form ref="appealForm" :inline="true" :model="appealForm" style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" label-position="right"
               size="mini"  :rules="appealRules" >
        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="申诉类型：" prop="appealType">
              <el-select :disabled="!initiateBtn || !delBtn" v-model="appealForm.appealType" class="item-width" size="mini" placeholder="申诉类型">
                <el-option v-for="dict in appealTypes" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="申诉原因：" prop="appealReason">
              <el-select :disabled="!initiateBtn || !delBtn" v-model="appealForm.appealReason" class="item-width" size="mini" placeholder="申诉原因" @change="validSubType">
                <el-option v-for="dict in appealReasons" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>
            <el-form-item prop="appealSubReason">
              <el-select :disabled="!initiateBtn  || !delBtn" v-model="appealForm.appealSubReason" class="item-width" size="mini" placeholder="申诉原因">
                <el-option v-for="dict in appealSubReasons" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="申诉意见说明：" prop="appealRemark">
              <el-input :disabled="!initiateBtn  || !delBtn" style="min-width: 520px" col="2" type="textarea" row="4" maxlength="1000"
                        v-model="appealForm.appealRemark" clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <el-card v-if="auditBtn " class="box-card" style="margin-top: 5px">
      <el-form ref="auditForm" :inline="true" :model="auditForm"
               style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" label-position="right"
               size="mini" :rules="auditRules" >
        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="初审决定：" prop="isAgree">
              <el-select :disabled="!delBtn" v-model="auditForm.isAgree" class="item-width" size="mini" placeholder="初审决定">
                <el-option v-for="dict in isAgrees" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="处理意见：" prop="conclusionRemark" key = "remark2">
              <el-input :disabled="!delBtn" style="min-width: 520px" col="2" type="textarea" row="4" maxlength="1000"
                        v-model="auditForm.conclusionRemark" clearable size="mini"  placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </el-dialog>
</template>
<script>

import { updateAppeal } from '@/api/appeal/api'

let dictss = [{dictType: 'appeal_type'}, {dictType: 'appeal_reason'}, {dictType: 'appeal_sub_reason'}, {dictType: 'is_agree'}]
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo: Object,
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
      fixInfo:function(newValue) {
        this.appealInfo = newValue;
        let data = this.appealInfo.row;
        if(data === undefined) {
          return;
        }
        let appealStatus = data.appealStatus;
        if(this.appealInfo.type === "initiate") {
          this.initiateBtn = true;
          if(appealStatus === '04') {
            this.auditForm.isAgree = '02';
          }
        }
        /**
         * appealInfo.type    申诉发起 01 ： 待处理  | 02 ： 已处理    /   申诉初审  03 ： 待处理  |  04 ： 已处理
         * appealStatus  申诉发起	01  申诉初审	02   申诉完成	03  申诉退回	04
         */
        if(this.appealInfo.type === "audit" || this.appealInfo.type === '02' || this.appealInfo.type === '04') {
          this.auditBtn = true;
          if(this.appealInfo.type === '02' || this.appealInfo.type === '04') {
            this.delBtn = false;
            this.initiateBtn = true;
            this.auditForm.conclusionRemark = data.conclusionRemark;
            if(appealStatus === '03') {
                this.auditForm.isAgree = '01';
            }
            if(appealStatus === '02') {
              this.auditBtn = false;
            }

          }
        }

        if(appealStatus !== '01') {
          this.appealForm.appealType = data.appealType;
          this.appealForm.appealReason = data.appealReason;
          if(data.appealType != '' && data.appealType != null) {
            this.validSubType(data.appealReason)
            if(data.appealSubReason != '' && data.appealSubReason != null) {
              this.appealForm.appealSubReason = data.appealSubReason;
            }
          }
          this.appealForm.appealRemark = data.appealRemark;
        }
      },
    },
    data() {
      return {
        operatorSelect:[],
        appealTypes:[],
        appealReasons:[],
        appealSubReasons:[],
        isAgrees:[],
        dictList: [],
        dialogVisable: false,
        appealForm: {
          appealType: '',
          appealReason: '',
          appealSubReason: '',
          appealRemark: '',
        },
        appealRules: {
          appealType: {trigger: ['change'], required: true, message: '不可为空'},
          appealReason: {trigger: ['change'], required: true, message: '不可为空'},
          appealSubReason: {trigger: ['change'], required: true, message: '不可为空'},
          amountType: {trigger: ['change'], required: true, message: '不可为空'},
          appealRemark: {trigger: ['change'], required: true, message: '不可为空'},
        },
        auditForm: {
          isAgree: '',
          conclusionRemark: '',
        },
        auditRules: {
          isAgree: {trigger: ['change'], required: true, message: '不可为空'},
          conclusionRemark: {trigger: ['change'], required: true, message: '不可为空'},
        },
        appealInfo: {},
        initiateBtn:false,
        appealAllSubReason:[],
        delBtn:true,
        auditBtn:false,
      }
    },

    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.appealTypes = this.dictList.find(item => {
        return item.dictType === 'appeal_type'
      }).dictDate
      this.appealReasons = this.dictList.find(item => {
        return item.dictType === 'appeal_reason'
      }).dictDate
      this.appealAllSubReason = this.dictList.find(item => {
        return item.dictType === 'appeal_sub_reason'
      }).dictDate
      this.isAgrees = this.dictList.find(item => {
        return item.dictType === 'is_agree'
      }).dictDate
    },
    created: function() {
    },
    methods: {
      validSubType(value){
        this.appealSubReasons = [];
        this.appealForm.appealSubReason = '';
        for(let i=0 ; i<this.appealAllSubReason.length; i++) {
          if(this.appealAllSubReason[i].listClass === value) {
            this.appealSubReasons.push(this.appealAllSubReason[i])
          }
        }
      },
      updateData (type) {
        let params = {};
        if(type === 'initiate') {
          params = this.appealForm;
          params.dealType = 'initiate';
          this.$refs.appealForm.validate((valid) => {
            if (valid) {
              this.updateAppeal(params);
            }
          })
        } else {
          params = this.auditForm;
          params.dealType = 'audit';
          this.$refs.auditForm.validate((valid) => {
            if (valid) {
              this.updateAppeal(params);
            }
          })
        }
      },
      updateAppeal(params) {
        params.taskId = this.appealInfo.row.taskId;
        params.appealRptNo = this.appealInfo.row.appealRptNo;
        updateAppeal(params).then(response => {
          if (response.code == '200') {
            this.$message({
              message: '处理成功！',
              type: 'success',
              center: true,
              showClose: true
            });
            this.$emit('initAppealData');
            this.changeDialogVisable();
          } else {
            this.$message({
              message: '处理失败！',
              type: 'error',
              center: true,
              showClose: true
            });
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //关闭对话框
      changeDialogVisable() {
        this.delBtn = true;
        this.auditBtn = false;
        this.initiateBtn = false;
        this.resetForm("appealForm");
        this.resetForm("auditForm");
        this.auditForm.conclusionRemark = '';
        this.auditForm.isAgree = '';
        this.appealForm.appealType = '';
        this.appealForm.appealReason = '';
        this.appealForm.appealSubReason = '';
        this.appealForm.appealRemark = '';
        this.$emit('closeDialog')
      },
    }
  }
</script>

<style scoped>
  .small-input {
    width: 80%;
  }

  .to_right {
    width: 120px;
    text-align: right;
  }

  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
</style>
