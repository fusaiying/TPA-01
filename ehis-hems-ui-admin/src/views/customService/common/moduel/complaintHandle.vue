<template>
  <el-card class="box-card" style="margin-top: 10px;" >
    <div slot="header" class="clearfix" >
      <span style="color: blue">投诉处理</span>
    </div>
    <el-form ref="ruleForm" :model="form" style="padding-bottom: 30px;" label-width="200px" :rules="ruleForm"
             label-position="right" size="mini" :disabled="isAcceptInfo || routerParams.status=='show'" >
      <el-row>
        <el-col :span="8">
          <el-form-item label="一级投诉分类：" prop="level1">
            <el-select v-model="form.level1" class="item-width" @change="classTwo()">
              <el-option v-for="item in cs_classify_level1" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="二级投诉分类：" prop="level2">
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
          <el-form-item label="质诉根因：" prop="actionCause">
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
  import moment from "moment";

  import { reasonThree, reasonTwo, classTwo, complainSearchServer} from '@/api/customService/complaint'
  import {complainSearch, } from '@/api/customService/consultation'
  let dictss =
    [{dictType: 'cs_classify_level1'}
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
      formData:Object,
      routerParams:Object,
      isAcceptInfo: {
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
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD HH:mm:ss')
        }
      }
    },
    watch: {
      formData: function (value) {
        if (value !== null) {
         this.form=value
          this.searchHandleServer()
        }
      }
    },


    data() {
      return {
        //字段校验
        ruleForm: {
          level1: [{ required: true, message: '一级投诉分类不能为空', trigger: 'change' }],
          level2: [{ required: true, message: '二级投诉分类不能为空', trigger: 'change' }],
          complaintTenable: [{ required: true, message: '投诉是否成立不能为空', trigger: 'change' }],
          reason1: [{ required: true, message: '一级投诉原因不能为空', trigger: 'change' }],
          reason2: [{ required: true, message: '二级投诉原因不能为空', trigger: 'change' }],
          reason3: [{ required: true, message: '三级投诉原因不能为空', trigger: 'change' }],
          complaintLink: [{ required: true, message: '投诉环节(报保监)不能为空', trigger: 'change' }],
          complaintQuestion: [{ required: true, message: '投诉问题(报保监)不能为空', trigger: 'change' }],
          outsideState: [{ required: true, message: '行协调解或外部鉴定状态不能为空', trigger: 'change' }],
          riskType: [{ required: true, message: '险种类型不能为空', trigger: 'change' }],
          marketChannel: [{ required: true, message: '营销渠道不能为空', trigger: 'change' }],
          complaintCategory: [{ required: true, message: '投诉业务类别不能为空', trigger: 'blur' }],
          rootDepartment: [{ required: true, message: '投诉根因部门不能为空', trigger: 'blur' }],
          actionCause: [{ required: true, message: '质诉根因不能为空', trigger: 'blur' }],
          treatmentResult: [{ required: true, message: '处理结果不能为空', trigger: 'blur' }],
          customerFeedback: [{ required: true, message: '客户反馈不能为空', trigger: 'change' }],
          actPromptly: [{ required: true, message: '投诉损失不能为空', trigger: 'blur' }]
        },
        dictList:[],
        queryParams:{
          workOrderNo:'',
          businessType:'03',
        },
        workPoolData: {
          contactsPerson: {
            homePhone1: []
          },
          callPerson: {},
          complaintPerson: {},
          insurer: {},
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
        cs_classify_level1:[],
        cs_classify_level2:[],
        cs_whether_flag:[],
        cs_drop_status:[],
        cs_reason_level1:[],
        cs_reason_level2:[],
        cs_reason_level3:[],
        cs_link_circ:[],
        cs_question_circ:[],
        cs_mediation_appraisal:[],
        cs_risk_type:[],
        serves:[],
        cs_feedback_type:[],
      }
    },
    created() {
      this.queryParams.workOrderNo = this.$route.query.workOrderNo;
      this.queryParams.policyNo = this.$route.query.policyNo;
      this.queryParams.policyItemNo = this.$route.query.policyItemNo;
      this.queryParams.status = this.$route.query.status;
      //window.aaa = this;
      this.searchHandle();
      this.searchHandle1();
      //this.searchHandleServer();
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.cs_classify_level1 = this.dictList.find(item => {
        return item.dictType === 'cs_classify_level1'
      }).dictDate
      this.cs_classify_level2 = this.dictList.find(item => {
        return item.dictType === 'cs_classify_level2'
      }).dictDate
      this.cs_whether_flag = this.dictList.find(item => {
        return item.dictType === 'cs_whether_flag'
      }).dictDate
      this.cs_drop_status = this.dictList.find(item => {
        return item.dictType === 'cs_drop_status'
      }).dictDate
      this.cs_reason_level1 = this.dictList.find(item => {
        return item.dictType === 'cs_reason_level1'
      }).dictDate
      this.cs_reason_level2 = this.dictList.find(item => {
        return item.dictType === 'cs_reason_level2'
      }).dictDate
      this.cs_reason_level3 = this.dictList.find(item => {
        return item.dictType === 'cs_reason_level3'
      }).dictDate
      this.cs_link_circ = this.dictList.find(item => {
        return item.dictType === 'cs_link_circ'
      }).dictDate
      this.cs_question_circ = this.dictList.find(item => {
        return item.dictType === 'cs_question_circ'
      }).dictDate
      this.cs_mediation_appraisal = this.dictList.find(item => {
        return item.dictType === 'cs_mediation_appraisal'
      }).dictDate
      this.cs_risk_type = this.dictList.find(item => {
        return item.dictType === 'cs_risk_type'
      }).dictDate
      this.serves = this.dictList.find(item => {
        return item.dictType === 'serves'
      }).dictDate
      this.cs_feedback_type = this.dictList.find(item => {
        return item.dictType === 'cs_feedback_type'
      }).dictDate

    },
    methods: {
      reasonTwo(flag) {
        const query = {}
        query.parentCode = this.form.reason1;
        if(flag=='1'){
          this.form.reason2 = '';
          this.form.reason3 = '';
        }
        reasonTwo(query).then(res => {
          if (res != null && res.code === 200) {
            console.log("cs_reason_level2", res.data)
            this.cs_reason_level2 = res.data
            if (res.rows.length <= 0) {
              return false
            }
          }
        }).catch(res => {

        })

      },
      reasonThree(flag) {
        const query = {}
        query.parentCode = this.form.reason2
        if(flag=='1'){
          this.sendForm.reason3 = '';
        }
        reasonThree(query).then(res => {
          if (res != null && res.code === 200) {
            console.log("cs_reason_level2", res.data)
            this.cs_reason_level3 = res.data
            if (res.rows.length <= 0) {
              return false
            }
          }
        }).catch(res => {

        })

      },
      classTwo(flag) {
        const query = {}
        query.parentCode = this.form.level1;
        if(flag=='1'){
          this.sendForm.level2 = '';
        }
        classTwo(query).then(res => {
          if (res != null && res.code === 200) {
            console.log("二级分类", res.data)
            this.cs_classify_level2 = res.data
            if (res.rows.length <= 0) {
              return false
            }
          }
        }).catch(res => {

        })

      },
      searchHandle() {
        let workOrderNo = this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            console.log("投诉页面反显数据", res.data)
            this.workPoolData = res.data
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //反显信息需求
      searchHandleServer() {
        this.reasonTwo('0');
        this.reasonThree('0');
        this.classTwo('0');
      /*  let query=this.queryParams
        complainSearchServer(query).then(res => {
          if (res != null && res.code === 200) {
            console.log("投诉页面server反显数据",res.data)
            this.form = res.data;

            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })*/
      },
      //客户信息匹配
      matching() {
        this.$router.push({
          path: '/customService/complaint/search',
          query: {
            workOrderNo: this.queryParams.workOrderNo,
            policyNo: this.queryParams.policyNo,
            policyItemNo: this.queryParams.policyItemNo,
            status: this.queryParams.status
          }
        })
      },
      searchHandle1() {
        let workOrderNo=this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            const workPoolData=res.data
            let editInfo = {
              editReason: "",
              editRemark: ""
            }
            workPoolData.editInfo=editInfo
            this.workPoolData=workPoolData
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
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
