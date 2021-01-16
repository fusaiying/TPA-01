<template>
  <div>
    <el-table
      :data="tableData"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      size="small"
      highlight-current-row
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column align="center" prop="claimno" width="160" label="赔案号">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="clickModal(scope.row)">{{ scope.row.claimno }}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="applicationsource" :formatter="getApplicationsource" label="申请来源" show-overflow-tooltip/>
      <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
      <el-table-column align="center" v-if="status==='0'" width="180" prop="idNo" label="证件号码" show-overflow-tooltip/>
      <el-table-column align="center" prop="categorycode" label="申请类别" :formatter="getApplicationType" width="140"> </el-table-column>
      <el-table-column align="center" prop="eventdate" label="出险日期" width="140">
        <template slot-scope="scope">
          <span>{{ scope.row.eventdate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="applicationdate" label="申请日期" width="140">
        <template slot-scope="scope">
          <span>{{ scope.row.applicationdate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="finalamount" label="赔付金额">
        <template slot-scope="scope">
          <span>{{ scope.row.finalamount|numFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="auditor" label="审核人" show-overflow-tooltip/>
      <el-table-column v-if="status==='0'" align="center" prop="appealsource" label="申诉类型" :formatter="getApplicationReson" show-overflow-tooltip/>
      <el-table-column v-if="status==='1'" align="center" prop="appealsource" label="申诉类型" :formatter="getApplicationReson" show-overflow-tooltip/>
      <el-table-column v-if="status==='1'" align="center" label="处理结论">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="editHandle(scope.row,'1')">{{dict.label.appealconclusion[scope.row.conclusion]}}</el-button>
        </template>
      </el-table-column>
      <el-table-column v-if="status==='0'" align="center" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="editHandle(scope.row,'0')">处理</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      :close-on-click-modal="false"
      title="申诉处理"
      width="700px"
      @open="openDialog"
      append-to-body>
      <el-form v-if="dialogVisible" ref="diaForm" :disabled="disabled" :model="diaForm" :rules="tableFormRules" label-width="100px" size="mini">
        <div>
          <el-form-item label="申诉类型：">
            <template>
              <el-row>
                <el-col :span="7">
                  <el-form-item  prop="appealsource"  :rules="tableFormRules.appealsource">
                    <el-select clearable v-model="diaForm.appealsource" class="inp1" style="width:100%" size="mini" placeholder="请选择" @change="changeReaon">
                      <el-option v-for="option in dict.appealsource" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <!-- <el-col :span="2" style="margin-left:23px">
                  <span>原因：</span>
                </el-col> -->
                <el-col :span="12">
                  <el-form-item  prop="reason" label="原因：" :rules="tableFormRules.reason">
                    <el-select clearable v-model="diaForm.reason" class="inp1" size="mini" placeholder="请选择">
                      <el-option v-for="option in reasonArr" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
            </template>
          </el-form-item>
          <el-form-item label="申诉说明：" prop="desc">
            <el-input :rows="2" v-model="diaForm.content" maxlength="1000"  style="width:440px" type="textarea" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </div>
        <el-form-item label="处理结论：" prop="conclusion">
          <el-select v-model="diaForm.conclusion" clearable class="item-width" placeholder="请选择">
            <el-option label="正常处理" value="0"/>
            <el-option label="维持原结论" value="1"/>
          </el-select>
        </el-form-item>
        <el-form-item :rules="diaForm.conclusion ? {required: true, message:'当选取处理结论时，结论备注必录'} : {required: false}" label="结论备注：" prop="remark">
          <el-input :rows="2" v-model="diaForm.remark" maxlength="1000" style="width:440px" type="textarea" class="item-width" clearable size="mini" placeholder="请输入"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="handleClose">返 回</el-button>
        <el-button :loading="confirmLoading" size="mini" type="primary" @click="confirmHandle" :disabled="disabled">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { changeDate } from '@/utils/commenMethods.js'
import { getList, complaintCorrectionHandle, complaintCorrectionHandleFind } from '@/api/appealCorrect/index.js'
import { get } from '@/api/dictDetail';
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  filters: {
    changeDate: changeDate
  },
  dicts: ['apply_sourcetype','application_category','applicationreason','appealconclusion','appealsource','appealsource_1','appealsource_0'],
  props: {
    tableData: {
      type: Array,
      default: function() {
        return []
      }
    },
    status: {
      type: String,
      default: function() {
        return ''
      }
    }
  },
  filters: {
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '' && value!== null) {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  data() {
    return {
      dialogVisible: false,
      diaForm: {
        appealsource: '',
        reason: '',
        content: '',
        conclusion: '',
        remark: ''
      },
      tableFormRules: {
        appealsource: [{ required: true, message: '不能为空!', trigger: ['blur','change'] }],
        reason: [{ required: true, message: '不能为空!', trigger:  ['blur','change'] }],
      },
      page: 1,
      pageSize:10,
      disabled: false,
      dialogTableVisible: false,
      appealsourceArr: [],
      contentArr: [],
      paramsClaimno: '',
      paramsArr: [],
      paramsFrom: {},
      appealnoArr: [],
      explanationArr: [],
      managecomArr: [],
      appealno: '',
      isDeal: '01',
      reason:'',
      categorycode:'',
      reasonType_0:true,
      reasonType_1:true,
      appealsource_0:'',
      appealsource_1:'',
      reasonArr: [],// 申诉纠错
      zanFrom: {},
      confirmLoading: false
    }
  },
  methods: {
    // 处理跳转
    editHandle(row, type) {
      if (type === '1') {
        this.disabled = true
        this.isDeal = '02'
      }
      if(row.appealsource === '02'){
        this.reasonArr = this.dict.appealsource_0
      }else{
        this.reasonArr = this.dict.appealsource_1
      }
      this.zanFrom = JSON.stringify(row)
      this.paramsClaimno = row.claimno || ''
      this.appealno = row.appealno || ''
      this.dialogVisible = true
    },
    // 关闭弹窗
    handleClose() {
      this.dialogVisible = false
      this.$refs.diaForm.resetFields()
    },
    // 提交
    confirmHandle() {
      this.paramsArr = []
      this.$refs.diaForm.validate((valid) => {
        if (valid) {
            let params = {}
            params.claimno = this.paramsClaimno
            params.appealno = this.diaForm.appealno || ''
            params.appealsource = this.diaForm.appealsource || ''
            params.content =  this.diaForm.content || ''
            params.remark =   this.diaForm.remark || ''
            params.reason = this.diaForm.reason || ''
            params.conclusion =  this.diaForm.conclusion || ''
          this.confirmLoading = true
          complaintCorrectionHandle(params).then( res => {
            if (res.status == '1' ) {
              this.$message({ message: '处理成功！', type: 'success'})
              this.dialogVisible = false
              this.$refs.diaForm.resetFields()
              this.$emit('getData','0')
              this.$emit('getData','1')
            } else {
              if (res.data.errCode == 'CL105') {
                this.$message({ message: res.data.errMsg, type: 'error'})
              } else {
                this.$message.error('处理失败')
              }
            }
            this.confirmLoading = false
          })
        } else {
          return false
        }
      })
    },
    clickModal(row) {
      let data = encodeURI(
        JSON.stringify({
          status: 'show',
          node: 'review',
          nodeL: true,
          claimno: encrypt(row.claimno)
        })
      )
      const newpage = this.$router.resolve({
        name: 'casedetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path :'/claims-handle/appeal-correct',
      //   query:{
      //     data
      //   }
      // })
    },
    // 选择申诉原因
    changeReaon(value) {
      this.reasonArr = []
      if (value == '02') {
        this.reasonArr = this.dict.appealsource_0
      } else {
        this.reasonArr = this.dict.appealsource_1
      }
    },
    // 打开弹窗是获取数据
    openDialog() {
     this.diaForm = JSON.parse(this.zanFrom)
     console.log(this.diaForm,9999)
    },
    // 获取申请类别
    getApplicationType(row, col) {
      if (row.categorycode != null) {
        if (row.categorycode.length > 2) {
          const reasonArray = row.categorycode.split(',')
          let reason = ''
          for (let i = 0; i < reasonArray.length; i++) {
            if (i === 0) {
              reason = this.dict.label.application_category[reasonArray[i]]
            } else {
              reason = reason + '|' + this.dict.label.application_category[reasonArray[i]]
            }
          }
          return reason
        } else {
          return this.dict.label.application_category[row.categorycode]
        }
      }
    },
    // 获取申请原因
    getApplicationReson(row, col) {
      return this.dict.label.appealsource[row.appealsource]
    },
    // 获得申请来源
    getApplicationsource(row, col) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },

  }
}
</script>
