<template>
  <el-card class="box-card" style="margin-top: 10px;" >
    <div slot="header" class="clearfix" >
      <span>投诉处理</span>
    </div>
    <el-form ref="ruleForm" :model="form" style="padding-bottom: 30px;" label-width="200px"
             label-position="right" size="mini" :disabled="true">
      <el-row>
        <el-col :span="8">
          <el-form-item label="一级投诉分类：" prop="priority">
            <el-select v-model="form.level1" class="item-width" @change="classTwo()">
              <el-option v-for="item in cs_classify_level1" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="二级投诉分类：" prop="priority">
            <el-select v-model="form.level2" class="item-width">
              <el-option v-for="item in cs_classify_level2" :key="item.code" :label="item.codeName"
                         :value="item.code"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否计件：" prop="pieceworkFlag">
            <el-select v-model="form.pieceworkFlag" class="item-width">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="撤诉状态：" prop="priority">
            <el-select v-model="form.complaintStatus" class="item-width">
              <el-option v-for="item in cs_drop_status" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉是否成立：" prop="complaintTenable">
            <el-select v-model="form.complaintTenable" class="item-width">
              <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉不成立理由：" prop="faseReason">
            <el-input v-model="form.faseReason" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="一级投诉原因：" prop="reason1">
            <el-select v-model="form.reason1" class="item-width" @change="reasonTwo()">
              <el-option v-for="item in cs_reason_level1" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="二级投诉原因：" prop="reason2">
            <el-select v-model="form.reason2" class="item-width" @change="reasonThree()">
              <el-option v-for="item in cs_reason_level2" :key="item.code" :label="item.codeName"
                         :value="item.code"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级投诉原因：" prop="reason3">
            <el-select v-model="form.reason3" class="item-width">
              <el-option v-for="item in cs_reason_level3" :key="item.code" :label="item.codeName"
                         :value="item.code"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="投诉环节(报保监)：" prop="complaintLink">
            <el-select v-model="form.complaintLink" class="item-width">
              <el-option v-for="item in cs_link_circ" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉问题(报保监)：" prop="complaintQuestion">
            <el-select v-model="form.complaintQuestion" class="item-width">
              <el-option v-for="item in cs_question_circ" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="行协调解或外部鉴定状态：" prop="outsideState">
            <el-select v-model="form.outsideState" class="item-width">
              <el-option v-for="item in cs_mediation_appraisal" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="险种类型：" prop="riskType">
            <el-select v-model="form.riskType" class="item-width">
              <el-option v-for="item in cs_risk_type" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="营销渠道：" prop="marketChannel">
            <el-select v-model="form.marketChannel" class="item-width">
              <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉业务类别：" prop="complaintCategory">
            <el-input v-model="form.complaintCategory" clearable size="mini" class="item-width"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="客户反馈：" prop="customerFeedback">
            <el-select v-model="form.customerFeedback" class="item-width">
              <el-option v-for="item in cs_feedback_type" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="根因改善：" prop="rootImprovement">
            <el-input v-model="form.rootImprovement" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="投诉损失：" prop="actPromptly">
            <el-input v-model="form.actPromptly" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22">
          <el-form-item label="投诉根因部门：" prop="rootDepartment">
            <el-input type="text" v-model="form.rootDepartment" class="width-full"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22">
          <el-form-item label="致诉根因：" prop="actionCause">
            <el-input type="text" v-model="form.actionCause" class="width-full"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22">
          <el-form-item label="处理结果：" prop="treatmentResult">
            <el-input type="text" v-model="form.treatmentResult" class="width-full"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>

</template>
<script>
  let dictss = [{dictType: 'cs_classify_level1'}
    ,{dictType: 'cs_classify_level2'}
    ,{dictType: 'cs_whether_flag'}
    ,{dictType: 'cs_drop_status'}
    ,{dictType: 'cs_reason_level1'}
    ,{dictType: 'cs_reason_level2'}
    ,{dictType: 'cs_reason_level3'}
    ,{dictType: 'cs_link_circ'}
    ,{dictType: 'cs_question_circ'}
    ,{dictType: 'cs_mediation_appraisal'}
    ,{dictType: 'cs_risk_type'}
    ,{dictType: 'serves'}
    ,{dictType: 'cs_feedback_type'}
  ]

  export default {

    props: {
      isDisabled: {
        type:Boolean,
        default:false
      },
      acceptInfo: {
        type: Object,
        default: function (){
          return {}
        }
      }
    },

    data() {
      return {
        queryParams:{
          workOrderNo:'',
          businessType:'03',
        },
        form:{
          level1: '',
          level2: '',
          pieceworkFlag: '',
          complaintStatus: '',
          faseReason: '',
          repeatedComplaint: '',
          reason1: '',
          reason2: '',
          reason3: '',
          complaintLink: '',
          complaintQuestion: '',
          outsideState: '',
          riskType: '',
          marketChannel: '',
          complaintCategory: '',
          customerFeedback: '',
          rootImprovement: '',
          actPromptly: '',
          rootDepartment: '',
          actionCause: '',
          treatmentResult: '',
        },

      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.serviceItemOptions = this.dictList.find(item => {
        return item.dictType === 'cs_demand_item'
      }).dictDate
      this.channelOptions = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.priorityOptions = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.communicationLanguageOptions = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.organizationOptions = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.relationOptions = this.dictList.find(item => {
        return item.dictType === 'cs_relation'
      }).dictDate
      this.businessTypeOptions = this.dictList.find(item => {
        return item.dictType === 'cs_business_type'
      }).dictDate
      this.getInfo();
    },
    methods: {

    }
  }
</script>
<style scoped>
  .info_span {
    font-size: 13px;
  }

  .item-width {
    width: 160px;
  }

  .el-tree /deep/ .el-tree-node__content {
    height: 30px;
  }

  .check_box {
    margin-top: 16px;
  }

  .collapse-card.el-card /deep/ .el-card__body {
    padding: 0;
  }

  .el-collapse /deep/ .el-collapse-item__header {
    background-color: #f8f8f8;
    padding: 0 10px;
  }

  .el-collapse /deep/ .el-collapse-item__wrap {
    padding: 0 10px;
  }

  .el-checkbox {
    margin-left: 20px;
  }

  .el-checkbox-group {
    margin-left: 20px;
  }

  .el-checkbox-group /deep/ .el-checkbox {
    display: block;
  }

  .to_right {
    width: 120px;
    text-align: right;
  }

  /deep/ .custom-tree-node {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }

  /deep/ .custom-tree-node span {
    flex: 1;
    display: inline-block;
    width: 80%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  /deep/ .el-radio{
    /*display: block;*/
    line-height: 30px;
    /*white-space: normal;*/
    /*margin-right: 0;*/
  }

</style>
<style>
  .el-tooltip__popper {
    max-width: 400px;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }

</style>
