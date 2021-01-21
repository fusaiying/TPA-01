<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;案件理算</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;案件理算</i>
        <span style="float: right;">
        <el-button v-if="!isOpen"  type="primary" :disabled="!collapsed" size="mini" @click="openAll">一键展开</el-button>
        <el-button v-else type="primary" :disabled="!collapsed" size="mini" @click="closeAll">一键收起</el-button>
        <el-button  type="primary" :disabled="!collapsed" size="mini" @click="">赔付计算</el-button>
        <el-button  type="primary" :disabled="!collapsed" size="mini" @click="save">保存</el-button>
      </span>
      </div>
    </div>
    <el-form ref="caseForm" :rules="caseRules" :model="caseForm" size="small" v-if="collapsed">
    <el-table
      v-loading="caseLoading"
      :header-cell-style="{color:'black',background:'#f8f8ff'}"
      :data="caseForm.caseData"
      size="small"
      highlight-current-row
      @expand-change="getMinData"
      :expand-row-keys="expands"
      :row-key="getRowKeys"
      tooltip-effect="dark"
      style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-table :data="scope.row.minData"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    highlight-current-row
                    size="small"
                    v-loading="minLoading"
                    tooltip-effect="dark"
                    style="width: 100%;">
            <el-table-column align="center" width="140" prop="feeItemName" label="费用项" show-overflow-tooltip/>
            <el-table-column align="center" prop="dutyName"width="200" label="赔付责任" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-select v-model="scope.row.occupation" filterable height="10" style="width: 95px" clearable
                           placeholder="请选择">
                 <!-- <el-option v-for="option in occupationOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>-->
                </el-select>
                <el-select v-model="scope.row.occupation" filterable  style="width: 95px" clearable
                           placeholder="请选择">
                 <!-- <el-option v-for="option in occupationOptions" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>-->
                </el-select>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="dutyDetailCode" label="费用项金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="dutyDetailName" label="折扣金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="applyReason" label="自费金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="applyReason" label="部分自费" show-overflow-tooltip/>
            <el-table-column align="center" prop="advancePayment" label="先期给付" show-overflow-tooltip/>
            <el-table-column align="center" prop="unableAmount" label="不合理金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="deduUsed" label="免赔额" show-overflow-tooltip/>
            <el-table-column align="center" prop="payRate" label="赔付比例%" show-overflow-tooltip/>
            <el-table-column align="center" prop="calAmount" label="理算金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="refusedAmount" label="拒赔金额" show-overflow-tooltip/>
            <el-table-column align="center" prop="billDetailCopay" label="自付额" show-overflow-tooltip/>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column align="center" width="140" prop="billNo" label="账单号/发票号" show-overflow-tooltip/>
      <el-table-column align="center" prop="treatmentStartDate" label="就诊日期" show-overflow-tooltip>
       <!-- <template slot-scope="scope">
          <span>{{ scope.row.treatmentStartDate | changeDate }}~{{ scope.row.treatmentEndDate | changeDate }}</span>
        </template>-->
      </el-table-column>
      <el-table-column align="center" prop="billAmount" label="账单金额" show-overflow-tooltip/>
      <el-table-column align="center" prop="hosDiscountAmount" label="折扣金额" show-overflow-tooltip/>
      <el-table-column align="center" prop="unableAmount" label="不合理金额" show-overflow-tooltip/>
      <el-table-column align="center" prop="ensureRegion" label="免赔额" show-overflow-tooltip/><!---->
      <el-table-column align="center" prop="calAmount" label="理算金额" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-link style="font-size: 11px" type="primary"  @click="openAdjustmentDialog(scope.$index)">{{ scope.row.calAmount }}</el-link>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="payAmount" label="赔付金额"  show-overflow-tooltip>
        <template slot-scope="scope">
          <el-form-item style="display: inline-flex !important;" :prop="'caseData.' + scope.$index + '.payAmount'"> <!-- :rules="caseRules.placeType"-->
            <el-input  v-model="scope.row.payAmount" placeholder="请输入" size="mini"/>
          </el-form-item>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="refusedAmount" label="拒赔金额" show-overflow-tooltip/><!--账单金额-折扣金额-赔付金额=拒赔金额-->
      <el-table-column align="center" prop="remark" label="备注"  show-overflow-tooltip>
        <template slot-scope="scope">
          <el-link style="font-size: 11px" v-if="scope.row.remark" type="primary" @click="changeRemark(scope.$index,scope.row.remark)">{{ scope.row.remark }}</el-link>
          <el-link style="font-size: 11px" type="primary" v-else @click="changeRemark(scope.$index)">请点击输入备注</el-link>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="payConclusion" label="赔付结论" show-overflow-tooltip/>
      <el-table-column align="center" prop="copay" label="自付额" show-overflow-tooltip/>
    </el-table>
    </el-form>

    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="备注明细"
      width="40%" height="35%">
      <div style="height: 200px">
      <el-form ref="remarkForm" :model="remarkForm"  size="mini" :rules="remarkRule">
        <el-row>
          <el-col :span="8">
            <el-form-item prop="pulloutDescribe" style="position:relative;width: 550px">
              <el-input type="textarea" :rows="7" v-model="remarkForm.remark"
                        class="item-widths" clearable
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="saveRemark">确认</el-button>
          <el-button size="mini" @click="dialogVisable=false">返回</el-button>
        </span>
      </el-form>
      </div>
    </el-dialog>
    <!-- 理算明细 -->
    <adjustment-detail :value="adjustmentDialog" @closeAdjustmentDialog="closeAdjustmentDialog"/>
  </el-card>
</template>
<script>
  import {getAddress} from '@/api/supplierManager/supplier'
  import {addAccept} from '@/api/claim/handleCom'
  import adjustmentDetail from '../modul/adjustmentDetail' //理算明细
  let dictss = [{dictType: 'application_reason'}, {dictType: 'preAuthFlag'}, {dictType: 'priority_reason'}, {dictType: 'accident_status'}, {dictType: 'current_state'},]
  export default {
    components: {
      adjustmentDetail
    },
    mixins: [],
    props: {
      status: String,
      node: String,
    },
    watch: {
    },
    filters: {
      changeDate: function(value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {
      return {
        dialogVisable:false,
        index:undefined,
        expands:[],
        caseForm:{
          caseData:[{
            remark:'aa',
            billAmount:'哈哈哈',
            calAmount:'125',
            minData:[{
              feeItemName:'00001'
            }],
          },{
            remark:''
          }]
        },
        caseRules:{

        },
        remarkForm:{
          remark: ''
        },
        remarkRule:{

        },
        collapsed:false,
        isOpen:false,
        adjustmentDialog:false,
        dictList: [],
        caseLoading:false,
        minLoading:false,
        application_reasonOptions: [],
        preAuthFlagOptions: [],
        priority_reasonOptions: [],
        accident_statusOptions: [],
        current_stateOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.preAuthFlagOptions = this.dictList.find(item => {
        return item.dictType === 'preAuthFlag'
      }).dictDate
      this.application_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'application_reason'
      }).dictDate
      this.priority_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'priority_reason'
      }).dictDate
      this.accident_statusOptions = this.dictList.find(item => {
        return item.dictType === 'accident_status'
      }).dictDate
      this.current_stateOptions = this.dictList.find(item => {
        return item.dictType === 'current_state'
      }).dictDate


    },

    methods: {
      openAdjustmentDialog(index){
        this.adjustmentDialog = true
      },
      closeAdjustmentDialog() {
        this.adjustmentDialog = false
      },
      checkedAllFun() {

      },
      save() {
      },
      getMinData(){

      },
      changeRemark(index,remark){
        this.index=index
        this.dialogVisable=true
        this.remarkForm.remark=remark
      },
      changeDialogVisable(){
        this.dialogVisable=false
      },
      saveRemark(){
        this.caseForm.caseData[this.index].remark=this.remarkForm.remark.trim()
        this.dialogVisable=false
      },
      openAll(){
        this.isOpen=true
      },
      closeAll(){
        this.isOpen=false
      },
      //设置table中的扩展项，展开的id，此处我需要全部展开
      getExpends(){
        var id = this.caseForm.caseData.map(item => item.id)
        this.expends = id
      },
      getRowKeys(row){
        return row.id
      },
    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }

  .el-form /deep/ .el-form-item__label {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .read-only.el-cascader /deep/ .el-input.is-disabled .el-input__inner {
    border: none;
    color: #333;
    padding: 0;
    background: transparent;
    width: 100%;
  }

  .read-only.el-cascader /deep/ .el-icon-arrow-down:before {
    display: none;
  }

  .long-select {
    width: 100%;
  }
</style>
