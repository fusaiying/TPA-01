<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;账单明细</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;账单明细</i>
        <div style="float: right;">
          <el-button v-if="status==='edit' && (node==='input' || node==='calculateReview') " :disabled="!collapsed"
                     type="primary"
                     size="mini" @click="saveBill">保存
          </el-button>
        </div>
      </div>
    </div>
    <div v-show="collapsed">
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        highlight-current-row
        @expand-change="getCostData"
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column type="expand" v-if="node==='calculateReview'"/>
        <el-table-column align="center" width="110" prop="billNo" label="账单号/发票号" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ getNo(scope.row) }} </span>
          </template>

        </el-table-column>
        <!--显示录入的账单号/发票号-->
        <el-table-column align="center" prop="treatmentType" label="治疗类型" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ selectDictLabel(	treat_typeOptions, scope.row.treatmentType) }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="hospitalCode" label="治疗医院" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ selectHospitalName(	hospitalOptions, scope.row.hospitalCode) }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="createBy" label="治疗日期" show-overflow-tooltip>
          <!--显示发票的治疗日期区间，格式YYYY-MM-DD - YYYY-MM-DD-->
          <template slot-scope="scope">
            <span>{{ scope.row.treatmentStartDate }} - {{ scope.row.treatmentEndDate }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="billAmount" label="账单金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="hosDiscountAmount" label="折扣金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="reasonAmount" label="合理费用" show-overflow-tooltip/>
        <el-table-column align="center" prop="selfAmount" label="自费金额" show-overflow-tooltip/>
        <el-table-column align="center" prop="partSelfAmount" label="部分自费" show-overflow-tooltip/>
        <el-table-column align="center" prop="advancePayment" label="先期给付" show-overflow-tooltip/>
        <el-table-column align="center" prop="unableAmount" label="不合理金额" show-overflow-tooltip/>
        <el-table-column align="center" v-if="status==='edit'" label="操作" width="140">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="addOrEdit('edit',scope.row)">编辑</el-button>
            <el-button v-if="status==='edit' && (node==='input' || node==='calculateReview')" size="mini"
                       style="color: red" type="text" @click="deleteInfo(scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="mini" v-if="node==='calculateReview'"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addBill"> + 添加
      </el-button>
      <div style="background-color: #bedbf1;width: 400px;height: 55px;text-align: center;line-height: 55px">汇总信息</div>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="pageNum"
        :limit.sync="pageSize"
        @pagination="getBillList"
      />
      <div style="height: 15px">

      </div>
      <el-form v-if="node === 'input' || isFormShow || node==='sport'" ref="baseForm" :rules="baseFormRule"
               :model="baseForm"
               style="padding-top:20px;padding-bottom: 30px;"
               :disabled="status === 'show' || node==='sport'"
               label-width="188px" label-position="right" size="mini">
        <el-col :span="8">
          <el-form-item label="就诊医院：" prop="hospitalName">
            <el-input disabled v-model="baseForm.hospitalName" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
            <el-button v-if="claimtype==='02'" type="success" size="mini" @click="openHospitalDialog"
                       icon="el-icon-search"></el-button>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院性质：" prop="firstAttribute">
            <span class="size">{{selectDictLabel( first_attributeOptions, baseForm.firstAttribute)}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院等级：" prop="secondAttribute">
            <span v-if="baseForm.firstAttribute==='01'" class="size">{{selectDictLabel( second_attribute_aOptions, baseForm.secondAttribute)}}</span>
            <span v-if="baseForm.firstAttribute==='02'" class="size">{{selectDictLabel( second_attribute_bOptions, baseForm.secondAttribute)}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="科室：" prop="department">
            <el-select v-model="baseForm.department" clearable class="item-width" placeholder="请选择">
              <el-option v-for="option in departmentOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否定点医院：" prop="isDesHospital">
            <el-select v-model="baseForm.isDesHospital" disabled clearable filterable remote class="item-width"
                       placeholder="请输入">
              <el-option v-for="option in sys_yes_noOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出险类型：" prop="accType">
            <el-select v-model="baseForm.accType" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in incidenttypeOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账户币种：" prop="billCurrency">
            <el-select :disabled="claimtype==='01'" v-model="baseForm.billCurrency" class="item-width"
                       placeholder="请选择" clearable>
              <el-option v-for="option in claim_currencyOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账单金额：" prop="billAmount">
            <el-input v-model="baseForm.billAmount" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="就诊次数：" prop="visNumber">
            <el-input v-model="baseForm.visNumber" disabled class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="治疗类型：" prop="treatmentType">
            <el-select v-model="baseForm.treatmentType" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in treat_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="治疗起期：" prop="treatmentStartDate">
            <el-date-picker
              v-model="baseForm.treatmentStartDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="治疗止期：" prop="treatmentEndDate">
            <el-date-picker
              :disabled="this.$route.query.status==='add'"
              v-model="baseForm.treatmentEndDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="治疗天数：" prop="treatmentDays">
            <el-input v-model="baseForm.treatmentDays" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="发票号：" prop="invoiceNo">
            <el-input v-model="baseForm.invoiceNo" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账单号：" prop="billNo">
            <el-input v-model="baseForm.billNo" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账单类型：" prop="billType">
            <el-select v-model="baseForm.billType" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in bill_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="社保先期给付：" prop="ssAdvancePayment">
            <el-input v-model="baseForm.ssAdvancePayment" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="第三方先期给付：" prop="tpAdvancePayment">
            <el-input v-model="baseForm.tpAdvancePayment" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否分摊先期给付：" prop="isShareAp">
            <el-select v-model="baseForm.isShareAp" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in input_statusOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="交易流水号：" prop="transSerialNo">
            <el-input v-model="baseForm.transSerialNo" maxlength="100" class="item-width" clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="交易流水号自付额(CNY)：" prop="transSerialCopay">
            <span class="size">{{ baseForm.transSerialCopay }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="自付额(CNY)：" prop="copay">
            <el-input v-model="baseForm.copay" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否分摊自付额(CNY)：" prop="isShareCopay">
            <el-select v-model="baseForm.isShareCopay" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in input_statusOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院折扣：" prop="hosDiscountAmount">
            <el-input v-model="baseForm.hosDiscountAmount" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否分摊折扣：" prop="isShareDisAmount">
            <el-select v-model="baseForm.isShareDisAmount" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in input_statusOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="主要诊断(ICD)：" prop="icdCode">
            <el-select v-model="baseForm.icdCode" class="item-width" placeholder="请选择" clearable>
              <!--<el-option v-for="option in current_stateOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>-->
              <el-option key="01" label="垂直斜视"
                         value="01"/>
              <el-option key="02" label="缺血性心肌病"
                         value="02"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col
          v-for="(icd, index) in baseForm.icdCodes"
          :key="icd.key"
          :span="8">
          <el-form-item label="次要诊断(ICD)：" prop="icdCodes"
                        :label="index===0?'次要诊断(ICD)':'次要诊断(ICD)' + index"
                        :prop="'icdCodes.' + index + '.icdCode'">
            <el-select v-model="icd.icdCode" class="item-width" placeholder="请选择" clearable>
              <!--<el-option v-for="option in current_stateOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>-->
              <el-option key="01" label="垂直斜视"
                         value="01"/>
              <el-option key="02" label="缺血性心肌病"
                         value="02"/>
            </el-select>
            <el-button v-if="index===0" type="primary" icon="el-icon-plus"
                       style="position: absolute; top: 0; right: 8px;"
                       size="mini" circle @click="addIcd"></el-button>
            <el-button
              v-if="index!==0"
              style="position: absolute; top: 0; right: 8px;"
              size="mini"
              type="primary"
              icon="el-icon-minus"
              circle
              @click="removeIcd(icd)"/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="临床诊断：" prop="clinicalDiagnosis">
            <el-input v-model="baseForm.clinicalDiagnosis" maxlength="500" style="width: 598px" class="item-width"
                      clearable size="mini"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-form>
      <el-form v-if="node === 'input' || isFormShow || isCostShow  || node==='sport'" ref="costForm"
               :rules="accountRules"
               :model="costForm" size="small">
        <el-table ref="costFormTable" :data="costForm.costData"
                  :header-cell-style="{color:'black',background:'#f8f8f8'}" highlight-current-row
                  size="small" style="width: 100%;">
          <el-table-column prop="feeItemCode" align="center" header-align="center" label="费用项名称" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.feeItemCode'"
                            :rules="accountRules.feeItemCode" style="display: inline-flex !important;">
                <el-select v-model="scope.row.feeItemCode" placeholder="请选择" size="mini">
                  <el-option v-for="item in feeOptions" :key="item.feeitemCode" :label="item.feeitemName"
                             :value="item.feeitemcode"/>
                </el-select>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ selectFee(feeOptions,scope.row.feeItemCode) }}</span>
              <span v-show="false">{{ scope.row.feeItemName=selectFee(feeOptions,scope.row.feeItemCode) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="billDetailAmount" align="center" header-align="center" label="费用金额" min-width="3"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.billDetailAmount'"
                            :rules="accountRules.billDetailAmount" style="display: inline-flex !important;">
                <el-input v-model="scope.row.billDetailAmount" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.billDetailAmount}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="hosDiscountAmount" align="center" header-align="center" label="折扣金额" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow && baseForm.isShareDisAmount!=='01'"
                            :prop="'costData.' + scope.$index + '.hosDiscountAmount'"
                            :rules="accountRules.hosDiscountAmount" style="display: inline-flex !important;">
                <el-input v-model="scope.row.hosDiscountAmount" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span
                v-if="!scope.row.isShow || baseForm.isShareDisAmount==='01'">{{ scope.row.hosDiscountAmount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="selfAmount" align="center" header-align="center" label="自费金额" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.selfAmount'"
                            :rules="accountRules.selfAmount" style="display: inline-flex !important;">
                <el-input v-model="scope.row.selfAmount" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.selfAmount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="partSelfAmount" align="center" header-align="center" label="部分自费" min-width="3"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.partSelfAmount'"
                            :rules="accountRules.partSelfAmount" style="display: inline-flex !important;">
                <el-input v-model="scope.row.partSelfAmount" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.partSelfAmount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unableAmount" align="center" header-align="center" label="不合理金额" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.unableAmount'"
                            :rules="accountRules.unableAmount" style="display: inline-flex !important;">
                <el-input v-model="scope.row.unableAmount" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.unableAmount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="advancePayment" align="center" header-align="center" label="先期给付"
                           min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow && baseForm.isShareAp!=='01'"
                            :prop="'costData.' + scope.$index + '.advancePayment'"
                            :rules="accountRules.advancePayment" style="display: inline-flex !important;">
                <el-input v-model="scope.row.advancePayment" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow || baseForm.isShareAp==='01'">{{ scope.row.advancePayment }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="billDetailCopay" align="center" header-align="center" label="自付额" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow && baseForm.isShareCopay!=='01'"
                            :prop="'costData.' + scope.$index + '.billDetailCopay'"
                            :rules="accountRules.billDetailCopay" style="display: inline-flex !important;">
                <el-input v-model="scope.row.billDetailCopay" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span
                v-if="!scope.row.isShow || baseForm.isShareCopay==='01'">{{scope.row.billDetailCopay}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="visNumber" align="center" header-align="center" label="次数" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.visNumber'"
                            :rules="accountRules.visNumber" style="display: inline-flex !important;">
                <el-input v-model="scope.row.visNumber" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span
                v-if="!scope.row.isShow">{{scope.row.visNumber}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" align="center" header-align="center" label="备注" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'costData.' + scope.$index + '.remark'"
                            :rules="accountRules.remark" style="display: inline-flex !important;">
                <el-input v-model="scope.row.remark" maxlength="500" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span
                v-if="!scope.row.isShow">{{scope.row.remark}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" width="140"
                           v-if="!(node === 'input' && status === 'show') && node !== 'sport'">
            <template slot-scope="scope">
              <el-button v-if="!scope.row.isShow" size="mini" type="text" @click="scope.row.isShow=true">编辑</el-button>
              <el-button size="mini" style="color: red" type="text" @click="deleteRow(scope.$index,scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="text" size="mini" :disabled="status === 'show' || node === 'sport'"
                   style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                   @click="addRow()"> + 添加
        </el-button>
      </el-form>
    </div>
    <hospital :value="hospitalDialog" @closeHospital="closeHospital" @getPropData="getPropData"/>
  </el-card>
</template>

<script>
  import Hospital from "../../../basicInfoManage/publicVue/hospital";
  import {getInfoBaseCodeMappingNew,} from '@/api/claim/presentingReview'

  let dictss = [{dictType: 'department'}, {dictType: 'incidenttype'}, {dictType: 'treat_type'}, {dictType: 'bill_type'}, {dictType: 'sys_yes_no'},
    {dictType: 'input_status'}, {dictType: 'first_attribute'}, {dictType: 'second_attribute_a'}, {dictType: 'second_attribute_b'}, {dictType: 'claim_currency'},]
  import {getBillList, saveBill, editBill, getFee, getHospitalInfo, deleteBill} from '@/api/claim/handleCom'


  export default {
    components: {Hospital},
    props: {
      sonBillingInfoData: Array,
      claimtype: String,
      billData: Array,
      batchData: Object,
      acceptData: Object,
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
      fixInfo: Object,
      finalamount: String
    },
    watch: {
      sonBillingInfoData: function (newVal) {
        if (newVal !== null && newVal !== undefined) {
          this.tableData = newVal
        }
      },
      acceptData: function (newVal) {
        if (newVal.claimCaseAccept !== null && newVal.claimCaseAccept !== undefined) {
          this.baseForm.accType = newVal.claimCaseAccept.accType
        }
      },
      fixInfo: function (newVal) {
        if (newVal !== null && newVal !== undefined) {
          getFee(newVal.rptNo).then(res => {
            if (res != null && res.code === 200) {
              this.feeOptions = res.rows
            }
          })
        }
      },
      batchData: function (newVal) {
        getHospitalInfo({}).then(res => {
          if (res != null && res !== '') {
            this.hospitalOptions = res.rows
          }
          if (newVal !== null && newVal !== undefined) {
            let val = this.hospitalOptions.find(item => {
              return item.providerCode === newVal.hospitalcode
            })
            if (val !== null && val !== undefined) {
              if (val.enname1 != null && val.enname1 !== '') {
                this.baseForm.hospitalName = val.chname1 + '|' + val.enname1
              } else {
                this.baseForm.hospitalName = val.chname1
              }
              this.baseForm.hospitalCode = val.providerCode

              this.baseForm.firstAttribute = val.firstAttribute
              this.baseForm.secondAttribute = val.secondAttribute
              this.baseForm.isDesHospital = val.flag
            }
          }
        })
        if (newVal !== null && newVal !== undefined) {
          this.baseForm.billCurrency = newVal.currency
        }
      },
    },
    data() {
      const checkNum = (rule, value, callback) => {
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (value) {
          if (value < 0) {
            callback(new Error("允许录入正数，保留两位小数"));
          } else if (!regx.test(value)) {
            callback(new Error("允许录入正数，保留两位小数"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      }
      const checkNums = (rule, value, callback) => {
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (value) {
          if (value < 0) {
            callback(new Error("请录入正数"));
          } else if (!regx.test(value)) {
            callback(new Error("请保留两位小数"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      }
      const checkVisNumber = (rule, value, callback) => {
        const regx = /^[1-9][0-9]*$/
        if (value) {
          if (!regx.test(value)) {
            callback(new Error("请录入正整数"));
          } else {
            let sum = 0
            for (let i = 0; i < this.costForm.costData.length; i++) {
              sum = sum + parseInt(this.getZero(this.costForm.costData[i].visNumber))
            }
            this.baseForm.visNumber = sum + ''
            callback();
          }
        } else {
          callback();
        }
      }
      const checkBillDetailAmount = (rule, value, callback) => {
        const index = rule.field.replace('costData.', '').replace('.billDetailAmount', '')
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (value) {
          if (value < 0) {
            callback(new Error("允许录入正数，保留两位小数"));
          } else if (!regx.test(value)) {
            callback(new Error("允许录入正数，保留两位小数"));
          } else {
            let dataSum = 0
            for (let i = 0; i <= index; i++) {
              dataSum = dataSum + parseFloat(this.costForm.costData[i].billDetailAmount)
            }
            if (dataSum <= parseFloat(this.baseForm.billAmount)) {
              if (this.baseForm.isShareAp === '01') {
                let paymentSum = 0
                for (let i = 0; i < this.costForm.costData.length - 1; i++) {
                  this.costForm.costData[i].advancePayment = (this.costForm.costData[i].billDetailAmount / this.baseForm.billAmount * (parseFloat(this.getZero(this.baseForm.ssAdvancePayment)) + parseFloat(this.getZero(this.baseForm.tpAdvancePayment))).toFixed(2)).toFixed(2)
                  paymentSum = paymentSum + parseFloat(this.costForm.costData[i].advancePayment)
                }
                this.costForm.costData[this.costForm.costData.length - 1].advancePayment=((parseFloat(this.getZero(this.baseForm.ssAdvancePayment)) + parseFloat(this.getZero(this.baseForm.tpAdvancePayment))).toFixed(2)- paymentSum).toFixed(2)
              }
              if (this.baseForm.isShareDisAmount === '01') {
                let hosDiscountAmountNum = 0
                for (let i = 0; i < this.costForm.costData.length - 1; i++) {
                  this.costForm.costData[i].hosDiscountAmount = (this.costForm.costData[i].billDetailAmount / this.baseForm.billAmount * this.getZero(this.baseForm.hosDiscountAmount)).toFixed(2)
                  hosDiscountAmountNum = hosDiscountAmountNum + parseFloat(this.costForm.costData[i].hosDiscountAmount)
                }

                this.costForm.costData[this.costForm.costData.length - 1].hosDiscountAmount = (this.getZero(this.baseForm.hosDiscountAmount) - hosDiscountAmountNum).toFixed(2)
              }
              if (this.baseForm.isShareCopay === '01' && (this.baseForm.transSerialCopay == null || this.baseForm.transSerialCopay === '')) {
                let copayNum = 0
                for (let i = 0; i < this.costForm.costData.length - 1; i++) {
                  this.costForm.costData[i].billDetailCopay = (this.costForm.costData[i].billDetailAmount / this.baseForm.billAmount * this.getZero(this.baseForm.copay)).toFixed(2)
                  copayNum = copayNum + parseFloat(this.costForm.costData[i].billDetailCopay)
                }
                this.costForm.costData[this.costForm.costData.length - 1].billDetailCopay = (this.getZero(this.baseForm.copay) - copayNum).toFixed(2)
              } else if (this.baseForm.isShareCopay === '01' && (this.baseForm.transSerialCopay !== null || this.baseForm.transSerialCopay !== '')) {
                let copayNum = 0
                for (let i = 0; i < this.costForm.costData.length - 1; i++) {
                  this.costForm.costData[i].billDetailCopay = (this.costForm.costData[i].billDetailAmount / this.baseForm.billAmount * this.getZero(this.baseForm.transSerialCopay)).toFixed(2)
                  copayNum = copayNum + parseFloat(this.costForm.costData[i].billDetailCopay)
                }
                this.costForm.costData[this.costForm.costData.length - 1].billDetailCopay = (this.getZero(this.baseForm.transSerialCopay) - copayNum).toFixed(2)
              }

              callback();
            } else {
              callback(new Error("录入费用金额有误，请检查！"));
            }
          }
        } else {
          callback(new Error("请录入费用金额"));
        }
      }
      const checkBillAmount = (rule, value, callback) => {
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (value) {
          if (value < 0) {
            callback(new Error("账单金额不为正数，请检查"));
          } else if (!regx.test(value)) {
            callback(new Error("账单金额最多保留两位小数，请检查"));
          } else {
            callback();
          }
        } else {
          callback(new Error("账单金额不能为空"));
        }
      }
      const checkTreatmentStartDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (!value) {
          callback(new Error("治疗起期不能为空！"));
        } else {
          if (this.baseForm.treatmentType === '1') {
            this.baseForm.treatmentEndDate = value
          }

          if (this.baseForm.treatmentType === '1' && (this.baseForm.treatmentEndDate !== null || this.baseForm.treatmentEndDate !== '')) {
            this.baseForm.treatmentDays = this.DateDiff(this.baseForm.treatmentEndDate, this.baseForm.treatmentStartDate) + 1
          }
          if (this.baseForm.treatmentType === '2' && (this.baseForm.treatmentEndDate !== null || this.baseForm.treatmentEndDate !== '')) {
            this.baseForm.treatmentDays = this.DateDiff(this.baseForm.treatmentEndDate, this.baseForm.treatmentStartDate)
          }
          if (value > date1) {
            callback(new Error("不允许录入晚于当前操作日期！"));
          } else if (value > this.baseForm.treatmentEndDate) {
            callback(new Error("不允许晚于治疗止期！"));
          } else {
            callback()
          }
        }
      }
      const checkTreatmentEndDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (!value) {
          callback(new Error("治疗止期不能为空！"));
        } else {
          if (this.baseForm.treatmentType === '1' && (this.baseForm.treatmentStartDate !== null || this.baseForm.treatmentStartDate !== '')) {
            this.baseForm.treatmentDays = this.DateDiff(this.baseForm.treatmentEndDate, this.baseForm.treatmentStartDate) + 1
          }
          if (this.baseForm.treatmentType === '2' && (this.baseForm.treatmentStartDate !== null || this.baseForm.treatmentStartDate !== '')) {
            this.baseForm.treatmentDays = this.DateDiff(this.baseForm.treatmentEndDate, this.baseForm.treatmentStartDate)
          }
          if (value > date1) {
            callback(new Error("不允许录入晚于当前操作日期！"));
          } else if (value < this.baseForm.treatmentStartDate) {
            callback(new Error("不允许早于治疗止期！"));
          } else {
            callback()
          }
        }
      }
      const checkTreatmentDays = (rule, value, callback) => {
        if (!value) {
          callback(new Error("治疗天数不能为空！"));
        } else {
          callback()
        }
      }
      const checkOne = (rule, value, callback) => {
        if (!value) {
          if ((this.baseForm.invoiceNo == null || this.baseForm.invoiceNo === '' || this.baseForm.invoiceNo === undefined) &&
            (this.baseForm.billNo == null || this.baseForm.billNo === '' || this.baseForm.billNo === undefined)) {
            callback(new Error("发票号与账单号必录其一！"));
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkIsShareCopay = (rule, value, callback) => {
        if (!value) {
          if (this.baseForm.transSerialNo != null && this.baseForm.transSerialNo !== '') {
            callback(new Error("交易流水号非空，该字段必录！"));
          } else {
            callback()
          }
        } else {
          callback()
        }
      }

      const checkRemark = (rule, value, callback) => {
        const index = rule.field.replace('costData.', '').replace('.remark', '')
        if (!value) {
          if (this.costForm.costData[index].unableAmount != null && this.costForm.costData[index].unableAmount !== '') {
            callback(new Error("请录入备注！"));
          } else {
            callback()
          }
        } else {
          callback()
        }
      }

      return {
        isBillInfoSave: false,
        feeOptions: [],
        hospitalDialog: false,
        collapsed: true,
        isFormShow: false,
        isCostShow: false,
        isAddOrEdit: '',
        activeNames: ["1"],
        amountFlag: false,
        beneficVis: false,
        costForm: {
          costData: [],
        },
        tableData: [],
        total: 1,
        pageNum: 1,
        pageSize: 10,
        baseForm: {
          billId: undefined,
          rptNo: '',
          hospitalCode: undefined,
          hospitalName: undefined,
          firstAttribute: undefined,//医院性质
          secondAttribute: undefined,//医院等级
          department: undefined,
          isDesHospital: undefined,
          accType: undefined,
          billCurrency: undefined,
          billAmount: undefined,
          visNumber: undefined,
          treatmentType: undefined,
          treatmentStartDate: undefined,
          treatmentEndDate: undefined,
          treatmentDays: undefined,
          invoiceNo: undefined,
          billNo: undefined,
          billType: undefined,//
          ssAdvancePayment: undefined,
          tpAdvancePayment: undefined,
          isShareAp: '01',
          transSerialNo: undefined,
          transSerialCopay: undefined,
          copay: undefined,
          isShareCopay: '01',
          hosDiscountAmount: undefined,
          flag: undefined,
          isShareDisAmount: '01',
          icdCode: undefined,
          icdCodes: [{
            icdCode: ''
          }],
          clinicalDiagnosis: undefined,
        },
        baseFormRule: {
          hospitalName: [{required: true, message: '就诊医院不能为空', trigger: ['blur', 'change']}],
          billCurrency: [{required: true, message: '账户币种不能为空', trigger: ['blur', 'change']}],
          billAmount: [{validator: checkBillAmount, required: true, trigger: ['blur', 'change']}],
          treatmentType: [{required: true, message: '治疗类型不能为空', trigger: ['blur', 'change']}],
          treatmentStartDate: [{validator: checkTreatmentStartDate, required: true, trigger: ['blur', 'change']}],
          treatmentEndDate: [{validator: checkTreatmentEndDate, required: true, trigger: ['blur', 'change']}],
          treatmentDays: [{validator: checkTreatmentDays, required: true, trigger: ['blur', 'change']}],
          ssAdvancePayment: [{validator: checkNum, trigger: ['blur', 'change']}],
          tpAdvancePayment: [{validator: checkNum, trigger: ['blur', 'change']}],
          isShareAp: [{required: true, message: '请选择是否分摊先期给付', trigger: ['blur', 'change']}],
          transSerialCopay: [{validator: checkIsShareCopay, required: true, trigger: ['blur', 'change']}],
          copay: [{validator: checkNum, trigger: ['blur', 'change']}],
          isShareCopay: [{required: true, message: '请选择是否分摊自付额', trigger: ['blur', 'change']}],
          hosDiscountAmount: [{validator: checkNum, trigger: 'blur'}],
          isShareDisAmount: [{required: true, message: '请选择主要诊断(ICD)', trigger: ['blur', 'change']}],
          icdCode: [{required: true, message: '请选择是否分摊自付额', trigger: ['blur', 'change']}],
          clinicalDiagnosis: [{required: true, message: '临床诊断不能为空', trigger: ['blur', 'change']}],
          invoiceNo: [{validator: checkOne, trigger: ['blur', 'change']}],
          billNo: [{validator: checkOne, trigger: ['blur', 'change']}],
        },
        accountRules: {
          feeItemCode: [{required: true, message: '请选择费用项名称', trigger: ['blur', 'change']}],
          billDetailAmount: [{validator: checkBillDetailAmount, required: true, trigger: ['blur', 'change']}],
          selfAmount: [{validator: checkNums, trigger: ['blur', 'change']}],
          partSelfAmount: [{validator: checkNums, trigger: ['blur', 'change']}],
          unableAmount: [{validator: checkNums, trigger: ['blur', 'change']}],
          advancePayment: [{validator: checkNums, trigger: ['blur', 'change']}],
          visNumber: [{validator: checkVisNumber, trigger: ['blur', 'change']}],
          remark: [{validator: checkRemark, trigger: ['blur', 'change']}],
          hosDiscountAmount: [{validator: checkNums, trigger: ['blur', 'change']}],
          billDetailCopay: [{validator: checkNums, trigger: ['blur', 'change']}],
        },
        dictList: [],
        departmentOptions: [],
        incidenttypeOptions: [],
        treat_typeOptions: [],
        bill_typeOptions: [],
        input_statusOptions: [],
        first_attributeOptions: [],
        second_attribute_aOptions: [],
        second_attribute_bOptions: [],
        sys_yes_noOptions: [],
        hospitalOptions: [],
        claim_currencyOptions: [],
      };
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.departmentOptions = this.dictList.find(item => {
        return item.dictType === 'department'
      }).dictDate
      this.incidenttypeOptions = this.dictList.find(item => {
        return item.dictType === 'incidenttype'
      }).dictDate
      this.treat_typeOptions = this.dictList.find(item => {
        return item.dictType === 'treat_type'
      }).dictDate
      this.bill_typeOptions = this.dictList.find(item => {
        return item.dictType === 'bill_type'
      }).dictDate
      this.input_statusOptions = this.dictList.find(item => {
        return item.dictType === 'input_status'
      }).dictDate
      this.first_attributeOptions = this.dictList.find(item => {
        return item.dictType === 'first_attribute'
      }).dictDate
      this.second_attribute_aOptions = this.dictList.find(item => {
        return item.dictType === 'second_attribute_a'
      }).dictDate
      this.second_attribute_bOptions = this.dictList.find(item => {
        return item.dictType === 'second_attribute_b'
      }).dictDate
      this.sys_yes_noOptions = this.dictList.find(item => {
        return item.dictType === 'sys_yes_no'
      }).dictDate
      this.claim_currencyOptions = this.dictList.find(item => {
        return item.dictType === 'claim_currency'
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
      deleteInfo(row) {
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteBill(row.billId).then(res => {
            if (res !== null && res.code === 200) {
              this.$message({
                message: '删除成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.$emit("refresh-item", 'bill')
            } else {
              this.$message({
                message: '删除失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          }).catch(res => {
          })
          this.$emit("refresh-item", 'bill')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      addOrEdit(status, row) {
        editBill(row.billId).then(res => {
          if (res != null && res.code === 200) {
            this.isFormShow = true
            this.baseForm.billId = res.data.bill.billId
            this.baseForm.rptNo = res.data.bill.rptNo
            this.baseForm.hospitalCode = res.data.bill.hospitalCode
            this.baseForm.department = res.data.bill.department
            this.baseForm.isDesHospital = res.data.bill.isDesHospital
            this.baseForm.accType = res.data.bill.accType
            this.baseForm.billCurrency = res.data.bill.billCurrency
            this.baseForm.billAmount = res.data.bill.billAmount
            this.baseForm.visNumber = res.data.bill.visNumber
            this.baseForm.treatmentType = res.data.bill.treatmentType
            this.baseForm.treatmentStartDate = res.data.bill.treatmentStartDate
            this.baseForm.treatmentEndDate = res.data.bill.treatmentEndDate
            this.baseForm.treatmentDays = res.data.bill.treatmentDays
            this.baseForm.invoiceNo = res.data.bill.invoiceNo
            this.baseForm.billNo = res.data.bill.billNo
            this.baseForm.billType = res.data.bill.billType
            this.baseForm.ssAdvancePayment = res.data.bill.ssAdvancePayment
            this.baseForm.tpAdvancePayment = res.data.bill.tpAdvancePayment
            this.baseForm.isShareAp = res.data.bill.isShareAp
            this.baseForm.transSerialNo = res.data.bill.transSerialNo
            this.baseForm.transSerialCopay = res.data.bill.transSerialCopay
            this.baseForm.copay = res.data.bill.copay
            this.baseForm.isShareCopay = res.data.bill.isShareCopay
            this.baseForm.hosDiscountAmount = res.data.bill.hosDiscountAmount
            this.baseForm.isShareDisAmount = res.data.bill.isShareDisAmount
            this.baseForm.icdCode = res.data.bill.icdCode
            this.baseForm.icdCodes = res.data.bill.icdCodes
            this.baseForm.clinicalDiagnosis = res.data.bill.clinicalDiagnosis

            this.costForm.costData = res.data.billDetail
            if (this.baseForm.icdCodes === null || this.baseForm.icdCodes.length === 0) {
              this.baseForm.icdCodes = [{
                icdCode: ''
              }]
            }
            if (res.data.bill.hospitalCode != null && res.data.bill.hospitalCode !== '') {
              let data = {
                providerCode: res.data.bill.hospitalCode
              }
              getHospitalInfo(data).then(res => {
                if (res != null && res !== '') {
                  if (res.rows[0].enname1 != null && res.rows[0].enname1 !== '') {
                    this.baseForm.hospitalName = res.rows[0].chname1 + '|' + res.rows[0].enname1
                  } else {
                    this.baseForm.hospitalName = res.rows[0].chname1
                  }
                  this.baseForm.firstAttribute = res.rows[0].firstAttribute
                  this.baseForm.secondAttribute = res.rows[0].secondAttribute
                  this.baseForm.isDesHospital = res.rows[0].flag
                }
              })

            }
          }
        }).catch(res => {
        })

      },
      closeHospital() {
        this.hospitalDialog = false
      },
      addRow() {
        const field = {//
          feeItemCode: '',
          feeItemName: '',
          billDetailAmount: '',
          hosDiscountAmount: '',
          selfAmount: '',
          partSelfAmount: '',
          unableAmount: '',
          advancePayment: '',
          billDetailCopay: '',
          visNumber: '',
          remark: '',
          isShow: true
        }
        this.costForm.costData.push(field)
      },
      deleteRow(index, row) {
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.costForm.costData.splice(index, 1)
          let sum = 0
          for (let i = 0; i < this.costForm.costData.length; i++) {
            sum = sum + parseInt(this.getZero(this.costForm.costData[i].visNumber))
          }
          this.baseForm.visNumber = sum + ''
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      addIcd() {
        this.baseForm.icdCodes.push({
          icdCode: '',
          key: Date.now()
        })
      },
      removeIcd(icd) {
        var index = this.baseForm.icdCodes.indexOf(icd)
        if (index !== -1) {
          this.baseForm.icdCodes.splice(index, 1)
        }
      },
      DateDiff(Date_end, Date_start) {
        let aDate, oDate1, oDate2, iDays;
        Date_end = Date_end.split(" "); //将时间以空格划分为两个数组  第一个数组是 2019-05-20 第二个数组是 00：00：00
        aDate = Date_end[0].split("-"); //获取第一个数组的值
        oDate1 = new Date(aDate[0], aDate[1], aDate[2]);  //将前半个数组以-拆分，每一个是一个数值
        Date_start = Date_start.split(" ");
        aDate = Date_start[0].split("-");
        oDate2 = new Date(aDate[0], aDate[1], aDate[2]);
        iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);    //把相差的毫秒数转换为天数
        return iDays;
      },
      addBill() {
        this.baseForm = {
          rptNo: '',
          hospitalCode: undefined,
          hospitalName: undefined,
          firstAttribute: undefined,//医院性质
          bsecondAttribute: undefined,//医院等级
          department: undefined,
          isDesHospital: undefined,
          accType: undefined,
          billCurrency: undefined,
          billAmount: undefined,
          visNumber: undefined,
          treatmentType: undefined,
          treatmentStartDate: undefined,
          treatmentEndDate: undefined,
          treatmentDays: undefined,
          invoiceNo: undefined,
          billNo: undefined,
          billType: undefined,//
          ssAdvancePayment: undefined,
          tpAdvancePayment: undefined,
          isShareAp: '01',
          transSerialNo: undefined,
          transSerialCopay: undefined,
          copay: undefined,
          isShareCopay: '01',
          hosDiscountAmount: undefined,
          flag: undefined,
          isShareDisAmount: '01',
          icdCode: undefined,
          icdCodes: [{
            icdCode: ''
          }],
          clinicalDiagnosis: undefined,
        }
        this.costForm.costData = []
        this.isFormShow = true
        this.isCostShow = false
      },
      getCostData(row, expandedRows) {
        if (expandedRows.length > 0) {
          editBill(row.billId).then(res => {
            if (res != null && res.code === 200) {
              this.baseForm.billId = res.data.bill.billId
              this.baseForm.rptNo = res.data.bill.rptNo
              this.baseForm.hospitalCode = res.data.bill.hospitalCode
              this.baseForm.department = res.data.bill.department
              this.baseForm.isDesHospital = res.data.bill.isDesHospital
              this.baseForm.accType = res.data.bill.accType
              this.baseForm.billCurrency = res.data.bill.billCurrency
              this.baseForm.billAmount = res.data.bill.billAmount
              this.baseForm.visNumber = res.data.bill.visNumber
              this.baseForm.treatmentType = res.data.bill.treatmentType
              this.baseForm.treatmentStartDate = res.data.bill.treatmentStartDate
              this.baseForm.treatmentEndDate = res.data.bill.treatmentEndDate
              this.baseForm.treatmentDays = res.data.bill.treatmentDays
              this.baseForm.invoiceNo = res.data.bill.invoiceNo
              this.baseForm.billNo = res.data.bill.billNo
              this.baseForm.billType = res.data.bill.billType
              this.baseForm.ssAdvancePayment = res.data.bill.ssAdvancePayment
              this.baseForm.tpAdvancePayment = res.data.bill.tpAdvancePayment
              this.baseForm.isShareAp = res.data.bill.isShareAp
              this.baseForm.transSerialNo = res.data.bill.transSerialNo
              this.baseForm.transSerialCopay = res.data.bill.transSerialCopay
              this.baseForm.copay = res.data.bill.copay
              this.baseForm.isShareCopay = res.data.bill.isShareCopay
              this.baseForm.hosDiscountAmount = res.data.bill.hosDiscountAmount
              this.baseForm.isShareDisAmount = res.data.bill.isShareDisAmount
              this.baseForm.icdCode = res.data.bill.icdCode
              this.baseForm.icdCodes = res.data.bill.icdCodes
              this.baseForm.clinicalDiagnosis = res.data.bill.clinicalDiagnosis
              this.costForm.costData = res.data.billDetail
              if (this.baseForm.icdCodes === null || this.baseForm.icdCodes.length === 0) {
                this.baseForm.icdCodes = [{
                  icdCode: ''
                }]
              }
              if (res.data.bill.hospitalCode != null && res.data.bill.hospitalCode !== '') {
                let data = {
                  providerCode: res.data.bill.hospitalCode
                }
                getHospitalInfo(data).then(res => {
                  if (res != null && res !== '') {
                    if (res.rows[0].enname1 != null && res.rows[0].enname1 !== '') {
                      this.baseForm.hospitalName = res.rows[0].chname1 + '|' + res.rows[0].enname1
                    } else {
                      this.baseForm.hospitalName = res.rows[0].chname1
                    }
                    this.baseForm.firstAttribute = res.rows[0].firstAttribute
                    this.baseForm.secondAttribute = res.rows[0].secondAttribute
                    this.baseForm.isDesHospital = res.rows[0].flag
                  }
                })
              }
            }
          }).catch(res => {
          })
          this.isFormShow = false
          this.isCostShow = true
        } else {
          this.isCostShow = false
        }
      },
      openHospitalDialog() {
        this.hospitalDialog = true
      },
      saveBill() {
        this.baseForm.rptNo = this.fixInfo.rptNo
        this.$refs.baseForm.validate((valid) => {
          if (valid) {
            this.$refs.costForm.validate((valid) => {
              if (valid) {
                let feeSum = 0
                let number = 0
                for (let i = 0; i < this.costForm.costData.length; i++) {
                  number = parseFloat(this.getZero(this.costForm.costData[i].billDetailAmount)) - parseFloat(this.getZero(this.costForm.costData[i].hosDiscountAmount)) - parseFloat(this.getZero(this.costForm.costData[i].selfAmount))
                    - parseFloat(this.getZero(this.costForm.costData[i].partSelfAmount)) - parseFloat(this.getZero(this.costForm.costData[i].unableAmount)) -
                    parseFloat(this.getZero(this.costForm.costData[i].advancePayment)) - parseFloat(this.getZero(this.costForm.costData[i].copay))
                  if (number < 0) {
                    return this.$message.warning(
                      "录入的金额有误,请检查！"
                    )
                  }
                  break
                }
                this.costForm.costData.forEach(item => {
                  feeSum = feeSum + parseInt(item.billDetailAmount)
                })
                if (number >= 0 && feeSum !== parseInt(this.baseForm.billAmount)) {
                  return this.$message.warning(
                    "录入的费用金额与账单金额不一致,请检查！")
                } else if (number >= 0 && feeSum === parseInt(this.baseForm.billAmount)) {
                  let data = {
                    bill: this.baseForm,
                    billDetail: this.costForm.costData
                  }
                  saveBill(data).then(res => {
                    if (res !== null && res.code === 200) {
                      this.$message({
                        message: '保存成功！',
                        type: 'success',
                        center: true,
                        showClose: true
                      })
                      this.isBillInfoSave = true
                      if (this.node === 'calculateReview') {
                        this.$emit("refresh-item", 'calculate')
                      }

                    }
                    let data = {
                      rptNo: this.fixInfo.rptNo
                    }
                    getBillList(data).then(res => {
                      if (res !== null && res.code === 200) {
                        this.tableData = res.rows
                      }
                    }).catch(res => {
                    })
                    this.baseForm = {
                      billId: undefined,
                      rptNo: '',
                      hospitalCode: undefined,
                      hospitalName: undefined,
                      firstAttribute: undefined,//医院性质
                      secondAttribute: undefined,//医院等级
                      department: undefined,
                      isDesHospital: undefined,
                      accType: undefined,
                      billCurrency: undefined,
                      billAmount: undefined,
                      visNumber: undefined,
                      treatmentType: undefined,
                      treatmentStartDate: undefined,
                      treatmentEndDate: undefined,
                      treatmentDays: undefined,
                      invoiceNo: undefined,
                      billNo: undefined,
                      billType: undefined,//
                      ssAdvancePayment: undefined,
                      tpAdvancePayment: undefined,
                      isShareAp: '01',
                      transSerialNo: undefined,
                      transSerialCopay: undefined,
                      copay: undefined,
                      isShareCopay: '01',
                      hosDiscountAmount: undefined,
                      flag: undefined,
                      isShareDisAmount: '01',
                      icdCode: undefined,
                      icdCodes: [{
                        icdCode: ''
                      }],
                      clinicalDiagnosis: undefined,
                    }
                    this.costForm.costData = []
                  }).catch(res => {
                    this.$message({
                      message: '保存失败!',
                      type: 'error',
                      center: true,
                      showClose: true
                    })
                  })
                }

              } else {
                return this.$message.warning(
                  "费用项信息录入不完整，请检查！"
                )
              }
            })
          } else {
            return this.$message.warning(
              "账单信息录入不完整，请检查！"
            )
          }
        })
      },
      getBillList() {
        let data = {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          rptNo: this.fixInfo.rptNo
        }
        getBillList(data).then(res => {
          if (res !== null && res.code === 200) {
            this.tableData = res.rows
          }
        }).catch(res => {
        })
      },
      selectFee(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
          if (datas[key].feeitemcode === ('' + value)) {
            actions.push(datas[key].feeitemname);
            return true;
          }
        })
        return actions.join('');
      },
      getPropData(val) {
        this.baseForm.hospitalCode = val.providerCode
        if (val.enname1 != null && val.enname1 !== '') {
          this.baseForm.hospitalName = val.chname1 + '|' + val.enname1
        } else {
          this.baseForm.hospitalName = val.chname1
        }
        let data = {
          codeType: 'YYBZ',
          originalCode: val.currency,
        }
        getInfoBaseCodeMappingNew(data).then(res => {
          if (res != null && res.code === 200) {
            this.baseForm.billCurrency = res.data.targetCode
          }
        })
        this.baseForm.firstAttribute = val.firstAttribute
        this.baseForm.secondAttribute = val.secondAttribute
        this.baseForm.isDesHospital = val.flag
      },
      selectHospitalName(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
          if (datas[key].providerCode === ('' + value)) {
            actions.push(datas[key].chname1);
            return true;
          }
        })
        return actions.join('');
      },
      getZero(str) {
        if (str === undefined || str === null || str === '') {
          return 0;
        } else {
          return str
        }
      },
      getNo(row) {
        let strNo = ''
        if (row.billNo !== null && row.billNo !== '' && row.invoiceNo !== null && row.invoiceNo !== '') {
          strNo = row.billNo + '|' + row.invoiceNo
        } else if (row.billNo !== null && row.billNo !== '' && (row.invoiceNo == null || row.invoiceNo === '')) {
          strNo = row.billNo
        } else if ((row.billNo == null || row.billNo === '') && row.invoiceNo !== null && row.invoiceNo !== '') {
          strNo = row.invoiceNo
        }
        return strNo
      }
    }

  }

</script>
<style scoped>

  .item-width {
    width: 180px;
  }

  .el-collapse {
    border: none;
  }

  .el-tabs ::v-deep .el-tabs__item {
    width: 10%;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-size: 14px;
  }
</style>
