<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>申诉信息</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
      </div>
      <el-form ref="appealForm" :inline="true" :model="appealForm"
               style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" label-position="right"
               size="mini">
        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="申诉类型：" prop="appealType">
              <el-select disabled="disabled" v-model="appealForm.appealType" class="item-width" size="mini" placeholder="申诉类型">
                <el-option v-for="dict in appealTypes" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>

            <el-form-item label="发起人：" prop="applyOperator">
              <el-input disabled="disabled" v-model="appealForm.applyOperator" class="item-width" size="mini" placeholder="发起人"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="申诉原因：" prop="appealReason">
              <el-select disabled="disabled" v-model="appealForm.appealReason" class="item-width" size="mini" placeholder="申诉原因">
                <el-option v-for="dict in appealReasons" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>

            <el-form-item label="" prop="appealSubReason">
              <el-select disabled="disabled" v-model="appealForm.appealSubReason"  size="mini" placeholder="申诉原因">
                <el-option v-for="dict in appealSubReasons" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="申诉意见说明：" prop="appealRemark">
              <el-input disabled="disabled" style="min-width: 520px" col="2" type="textarea" row="4" maxlength="1000"
                        v-model="appealForm.appealRemark" clearable size="mini" placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 5px">
      <el-form ref="appealForm2" :inline="true" :model="appealForm2" style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" label-position="right"  size="mini">
        <el-row>
          <el-col :span="20" :xs="24">
            <el-form-item label="初审决定：" prop="isAgree">
              <el-select disabled="disabled" v-model="appealForm2.isAgree" class="item-width" size="mini" placeholder="初审决定">
                <el-option v-for="dict in isAgrees" :key="dict.dictValue" :label="dict.dictLabel"  :value="dict.dictValue" />
              </el-select>
            </el-form-item>
            <el-form-item label="初审人：" prop="auditor" key="op2">
              <el-input disabled="disabled" v-model="appealForm2.auditor" class="item-width" size="mini" placeholder="初审人"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="处理意见：" prop="conclusionRemark" key = "remark2">
              <el-input disabled="disabled" style="min-width: 520px" col="2" type="textarea" row="4" maxlength="1000"  v-model="appealForm2.conclusionRemark" clearable size="mini" placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </el-dialog>
</template>
<script>
import { getAppealInfo } from '@/api/handel/common/api'
let dictss = [{dictType: 'appeal_type'}, {dictType: 'appeal_reason'}, {dictType: 'appeal_sub_reason'}, {dictType: 'is_agree'}]
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      fixInfo: Object,//可以获取报案号 this.fixInfo.rptNo
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
      fixInfo: function (newValue) {
        this.rptNo = newValue.rptNo;
        if(this.rptNo !== '') {
          this.getDetail();
        }
      },
    },
    data() {
      return {
        rptNo:'',
        appealTypes:[],
        appealReasons:[],
        appealSubReasons:[],
        isAgrees:[],
        dictList: [],
        dialogVisable: false,
        appealForm: {
          appealType: '',
          applyOperator: '',
          appealReason: '',
          appealSubReason: '',
          appealRemark: '',

        },
        appealForm2: {
          isAgree: '',
          auditor:'',
          conclusionRemark:'',

        },
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
      this.appealSubReasons = this.dictList.find(item => {
        return item.dictType === 'appeal_sub_reason'
      }).dictDate
      this.isAgrees = this.dictList.find(item => {
        return item.dictType === 'is_agree'
      }).dictDate
    },
    created: function() {
    },
    methods: {
      getDetail() {
        const params = {
          newRptNo:this.rptNo,
        };
        getAppealInfo(params).then(response => {
          if(response.code === 200 && response.data) {
            this.appealForm = response.data;
            this.appealForm2 = response.data;
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeAppealDialog')
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
