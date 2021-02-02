<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="协谈信息"
    width="86%"
    @open="getNegotiationInfo"
    v-bind="$attrs">
    <el-card class="box-card" shadow="never" style="margin: -20px 0 10px;">
      <div slot="header" class="clearfix">
        <span>协谈信息</span>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <!-- @row-click="rowClickHandle" -->
        <el-table-column align="center" width="60">
          <template scope="scope">
            <el-radio :label="scope.$index" v-model="selRadio" @change.native="rowClickHandle(scope.row, scope.$index)"></el-radio>
          </template>
        </el-table-column>
        <el-table-column width="160" align="center" property="orderno" label="协谈/工单号"/>
        <el-table-column width="160" align="center" property="claimno" label="赔案号"/>
        <el-table-column align="center" property="negotiationtype" label="协谈类型">
          <template slot-scope="scope">
            {{/*dict.label.negotiation_type[scope.row.negotiationtype]*/ this.selectDictLabel(this.negotiation_typeOptions,scope.row.negotiationtype)}}
          </template>
        </el-table-column>
        <el-table-column align="center" property="negotiationmode" label="协谈方式">
          <template slot-scope="scope">
            {{/*dict.label.negotiation_mode[scope.row.negotiationmode]*/ this.selectDictLabel(this.negotiation_modeOptions,scope.row.negotiationmode)}}
          </template>
        </el-table-column>
        <el-table-column align="center" property="createdate" label="发起日期"/>
        <el-table-column align="center" property="creator" label="发起人"/>
        <el-table-column align="center" property="status" label="协谈状态">
          <template slot-scope="scope">
            {{/*dict.label.negotiationstatus[scope.row.status]*/this.selectDictLabel(this.negotiation_statusOptions,scope.row.status)}}
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-form v-loading="loading" ref="baseForm" :rules="baseFormRule" :model="baseForm" label-width="120px" size="mini">
      <el-card class="box-card" shadow="never">
        <div slot="header" class="clearfix">
          <span>协谈录入</span>
        </div>
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：">
              <span>{{ claimno }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="协谈次数：">
              <span>{{ readInfo.count }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发起人：">
              <span>{{ readInfo.username }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="协谈类型：" prop="negotiationtype">
              <el-select v-model="baseForm.negotiationtype" :disabled="node==='spotCheck'||node==='correct'" class="item-width" placeholder="请选择">
                <el-option v-for="item in negotiation_typeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                <!-- <el-option label="沟通" value="01"/>
                <el-option label="身故" value="02"/>
                <el-option label="协谈" value="03"/> -->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="协谈方式：" prop="negotiationmode">
              <el-select v-model="baseForm.negotiationmode" :disabled="node==='spotCheck'||node==='correct'" class="item-width" placeholder="请选择">
                <el-option v-for="item in negotiation_modeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                <!-- <el-option label="客服工单" value="01"/>
                <el-option label="手工处理" value="02"/> -->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="23">
            <el-form-item label="协谈要求：" prop="negotiationdescription">
              <el-input maxlength="1000" type="textarea" :autosize="{ minRows: 2, maxRows: 4}" :disabled="node==='spotCheck'||node==='correct'" show-word-limit v-model="baseForm.negotiationdescription"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right;">
          <el-button :disabled="node==='spotCheck'||node==='correct'" type="primary" size="mini" @click="startNegotiation">发起协谈</el-button>
          <el-button :disabled="node==='spotCheck'||node==='correct'" type="primary" size="mini" @click="resetForm">重 置</el-button>
        </div>
      </el-card>
      <el-card class="box-card" shadow="never" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span>协谈结果</span>
        </div>
        <el-row v-if="resultTableData.length">
          <el-col :span="8">
            <el-form-item label="完成时间：">
              <span>{{ resultForm.negotiationdate }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理人：">
              <span>{{ resultForm.negotiator }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="协谈结果：">
              <span>{{ resultForm.negotiationresults }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="协谈经过：">
              <span>{{ resultForm.textprocess }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-table
          :data="resultTableData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          size="small"
          highlight-current-row
          tooltip-effect="dark">
          <el-table-column align="center" property="negotiationmode" label="协谈方式">
            <template slot-scope="scope">
              <span>{{/*dict.label.negotiation_mode[scope.row.negotiationmode]*/this.selectDictLabel(this.negotiation_modeOptions,scope.row.negotiationmode)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" property="orderno" label="协谈/工单号"/>
          <el-table-column align="center" property="negotiationtype" label="协谈类型">
            <template slot-scope="scope">
              <span>{{/*dict.label.negotiation_type[scope.row.negotiationtype]*/this.selectDictLabel(this.negotiation_typeOptions,scope.row.negotiationtype)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="文件">
            <template slot-scope="scope">
              <span>{{scope.row.name}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" property="createdate" label="添加时间"/>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button :disabled="!scope.row.name" type="text" size="mini" @click="dowloadImageFile(scope.row)">下 载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-form>
  </el-dialog>
</template>

<script>
import { getNegotiationInfo, startNegotiation, dowloadImageFile } from '@/api/claim/handleDeal'
import { downloadData } from '@/utils'
export default {
  //dicts: ['negotiation_type', 'negotiation_mode', 'casestep', 'negotiationstatus'],
  props: {
    dictList:Array,
    value: {
      type: Boolean,
      default: false
    },
    node: {
      type: String,
      default: ''
    },
    claimno: String
  },
  data() {
    return {
      selRadio: '',
      loading: false,
      tableData: [],
      resultTableData: [],
      readInfo: {},
      baseForm: {
        negotiationtype: '',
        negotiationmode: '',
        negotiationdescription: ''
      },
      baseFormRule: {
        negotiationtype: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        negotiationmode: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        negotiationdescription: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
      },
      resultForm: {},
      /*'negotiation_type', 'negotiation_mode', 'casestep', 'negotiationstatus'*/
      negotiation_typeOptions: [],
      negotiation_modeOptions: [],
      casestepOptions: [],
      negotiation_statusOptions: []
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0) {
      this.negotiation_typeOptions = this.dictList.find(item => {
        return item.dictType == 'negotiation_type'
      }).dictDate
      this.negotiation_modeOptions = this.dictList.find(item => {
        return item.dictType == 'negotiation_mode'
      }).dictDate
      this.casestepOptions = this.dictList.find(item => {
        return item.dictType == 'casestep'
      }).dictDate
      this.negotiation_statusOptions = this.dictList.find(item => {
        return item.dictType == 'negotiation_status'
      }).dictDate
    }
  },
  methods: {
    dowloadImageFile(row){
      dowloadImageFile(row.attachmentno).then(res => {
        downloadData(res, row.name)
      })
    },
    dialogClosed() {
      this.$refs.baseForm.resetFields()
      this.resultTableData = []
      this.selRadio = ''
      this.$emit('input', false)
    },
    getNegotiationInfo(){
      this.loading = true
      getNegotiationInfo(this.claimno).then(res => {
        if (res.status === '1') {
          this.readInfo = {
            count: res.data.count,
            username: res.data.username
          }
          this.tableData = res.data.list
        } else {
          this.$message({ message: '查询协谈列表出错！', type: 'error' })
        }
      }).finally(() => {
        this.loading = false
      })
    },
    resetForm() {
      this.$refs.baseForm.resetFields()
    },
    startNegotiation() {
      this.baseForm.negotiationdescription = this.trimDescription()
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          let params = {
            claimno: this.claimno,
            ...this.baseForm
          }
          let currentCase= this.casestepOptions.find((item) => {
            return item.label === this.node
          })
          params.tasknode = currentCase.value
          startNegotiation(params).then(res => {
            if (res.status === '1') {
              this.$message({ message: '操作成功！', type: 'success' })
              // this.getNegotiationInfo()
              this.$router.go(-1)
              this.$emit('updateStatus','show')
              this.dialogClosed()
            } else {
              this.$message({ message: res.data.errMsg, type: 'error' })
            }
          })
        } else {
          return false
        }
      })
    },
    rowClickHandle(row, idx){
      this.resultForm = this.tableData[idx]
      this.$set(this.baseForm, 'negotiationtype', row.negotiationtype)
      this.$set(this.baseForm, 'negotiationmode', row.negotiationmode)
      this.$set(this.baseForm, 'negotiationdescription', row.negotiationdescription)
      this.resultTableData = []
      if(row.attachmentlist.length){
        row.attachmentlist.map((item, i)=> {
          item.negotiationtype = row.negotiationtype
          item.negotiationmode = row.negotiationmode
          item.orderno = row.orderno
          this.resultTableData.push(item)
        })
      } else {
         this.resultTableData.push(row)
      }
    },
    trimDescription() {
      return this.baseForm.negotiationdescription.replace(/(^\s*)|(\s*$)/g, "")
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.expand_span {
  font-size: 12px;
}
.el-radio ::v-deep .el-radio__label {
  display: none;
}
</style>
