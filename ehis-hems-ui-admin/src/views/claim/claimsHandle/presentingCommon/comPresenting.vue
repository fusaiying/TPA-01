<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>批次交单信息</span>
        <span style="float: right;">
          <el-button v-if="isSaveSub" :disabled="eSaveSub" type="primary" size="mini"
                     @click="saveSubmit">保存提交</el-button>
          <el-button v-if="isSaveOrSub" :disabled="eSaveOrSub" type="primary" size="mini" @click="save">保存</el-button>
          <el-button v-if="isSaveOrSub" :disabled="eSaveOrSub" type="primary" size="mini" @click="submit">提交</el-button>
          <el-button v-if="isReview" :disabled="eReview" type="primary" size="mini" @click="review">复核完毕</el-button>
          <el-button v-if="canPrint" :disabled="isPrint" type="primary" size="mini" @click="print">条码打印</el-button>
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="110px" :rules="rules"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="批次号：" prop="batchno">
              <el-input disabled v-model="searchForm.batchno" class="item-width" clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="理赔类型：" prop="claimtype">
              <el-select :disabled="isShow" v-model="searchForm.claimtype" class="item-width" placeholder="请选择"
                         @change="typeChange">
                <el-option v-for="option in claimTypeOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交单日期：" prop="submitdate">
              <el-date-picker
                :disabled="this.$route.query.status==='add'|| isShow"
                v-model="searchForm.submitdate"
                class="item-width"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="就诊医院：" prop="chname1">
              <el-input disabled v-model="searchForm.chname1" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
              <el-button :disabled="isShow" type="success" size="mini" @click="openHospitalDialog"
                         icon="el-icon-search"></el-button>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批次总金额：" prop="batchtotal">
              <el-input :disabled="isShow" v-model="searchForm.batchtotal" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="账户币种：" prop="currency">
              <el-select :disabled="isShow" v-model="searchForm.currency" class="item-width" placeholder="请选择">
                <el-option v-for="option in claim_currencyOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="个险/团险：" prop="conttype">
              <el-select :disabled="isShow" v-model="searchForm.conttype" class="item-width" placeholder="请选择">
                <el-option v-for="option in insurance_typeOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="案件数：" prop="casenum">
              <el-input :disabled="isShow" v-model="searchForm.casenum" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="机构：" prop="organcode">
              <span class="size">{{ deptName }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="发票是否收到：" prop="billrecevieflag">
              <el-select :disabled="isShow" v-model="searchForm.billrecevieflag" class="item-width" placeholder="请选择">
                <el-option v-for="option in sys_yes_noOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="isAfter" label="优先原因：" prop="prireason">
              <el-select :disabled="isShow" v-model="searchForm.prireason" class="item-width" placeholder="请选择">
                <el-option v-for="option in priority_reasonOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="isAfter" label="快递号：" prop="expressnumber">
              <el-input :disabled="isShow" v-model="searchForm.expressnumber" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="isAfter" label="收单日期：" prop="receivedate">
              <el-date-picker
                :disabled="isShow"
                v-model="searchForm.receivedate"
                class="item-width"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="isAfter" label="交件人：" prop="sendby">
              <el-input :disabled="isShow" v-model="searchForm.sendby" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="特殊案件：" prop="speccasetype">
              <el-select :disabled="isShow" v-model="searchForm.speccasetype" class="item-width" placeholder="请选择">
                <el-option v-if="searchForm.claimtype==='01'" :key="special_caseOptions[0].dictValue"
                           :label="special_caseOptions[0].dictLabel"
                           :value="special_caseOptions[0].dictValue"/>
                <el-option v-if="searchForm.claimtype==='02'" :key="special_caseOptions[1].dictValue"
                           :label="special_caseOptions[1].dictLabel"
                           :value="special_caseOptions[1].dictValue"/>
                <el-option v-if="searchForm.claimtype==='02'" :key="special_caseOptions[2].dictValue"
                           :label="special_caseOptions[2].dictLabel"
                           :value="special_caseOptions[2].dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="searchForm.speccasetype==='03'" label="投保单位：" prop="Issuingunit">
              <el-select :disabled="isShow" v-model="searchForm.Issuingunit" class="item-width" placeholder="请选择">
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item v-if="searchForm.speccasetype==='03'" label="保单号：" prop="contno">
              <el-input :disabled="isShow" v-model="searchForm.contno" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="批次备注：" prop="remark">
              <el-input :disabled="isShow" style="width: 638px;" type="textarea" row="2" maxlength="1000"
                        v-model="searchForm.remark" class="item-width" clearable size="mini"
                        placeholder="请输入批次备注"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!--<el-divider/>-->
      <div class="clearfix">
        <span v-if="isDirect">医院账户</span>
        <span v-if="isAfter">事后案件台账</span>
      </div>
      <el-divider/>
      <el-table
        v-show="isDirect"
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" prop="chname1" min-width="120" label="医院名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="accountName" min-width="160" label="账户名" show-overflow-tooltip/>
        <el-table-column align="center" prop="bankName" min-width="160" label="开户行" show-overflow-tooltip/>
        <el-table-column align="center" prop="bankCode" min-width="160" label="账号" show-overflow-tooltip/>
        <el-table-column align="center" prop="chaddreess" label="医院地址" min-width="90" show-overflow-tooltip/>
        <el-table-column align="center" prop="bankDetail" min-width="120" label="银行信息描述" show-overflow-tooltip/>
      </el-table>
      <el-table
        v-show="isAfter"
        :data="afterTable"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" prop="rptno" min-width="120" label="报案号" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input disabled size="mini" v-if="show" v-model="scope.row.rptno"
                      placeholder="请输入"></el-input>
            <span v-show="!show" class="form-span">{{scope.row.rptno}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="idno" min-width="160" label="证件号码" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="mini" v-if="show" v-model="scope.row.idno"
                      placeholder="请输入"></el-input>
            <span v-show="!show" class="form-span">{{scope.row.idno}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="name" min-width="100" label="被保险人" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="mini" v-if="show" v-model="scope.row.name"
                      placeholder="请输入"></el-input>
            <span v-show="!show" class="form-span">{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="isAfter" align="center" prop="claimmaterials" label="理赔材料" min-width="180"
                         show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-if="show" multiple size="mini" v-model="scope.row.claimmaterials" class="item-width"
                       placeholder="请选择">
              <el-option v-for="option in claim_materialOptions" :key="option.dictValue"
                         :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
            <span v-if="!show"
                  class="form-span">{{getClaimmaterials(scope.row.claimmaterials)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="remark" min-width="120" label="备注" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="mini" maxlength="1000" v-if="show" v-model="scope.row.remark"
                      placeholder="请输入"></el-input>
            <span v-show="!show" class="form-span">{{scope.row.remark}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="otherinfo" min-width="120" label="其他(案件去向)" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="mini" v-if="show" v-model="scope.row.otherinfo"
                      placeholder="请输入"></el-input>
            <span v-show="!show" class="form-span">{{scope.row.otherinfo}}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-divider v-if="isShowFooter"/>
      <el-form v-if="isShowFooter" ref="recordForm" :model="recordForm" style="padding-bottom: 30px;"
               label-width="100px"
               :rules="recordRule"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="复核结论：" prop="conclusion">
              <el-select :disabled="eShowFooter" v-model="recordForm.conclusion" class="item-width" placeholder="请选择">
                <el-option v-for="option in examine_resultOptions" :key="option.dictValue"
                           :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <!--<el-col :span="8">
            <el-form-item v-if="isRecord" label="不通过类型：" prop="nopasstype">
              <el-select :disabled="eShowFooter" v-model="recordForm.nopasstype" class="item-width" placeholder="请选择">
                <el-option key="1" label="不合格"
                           value="1"/>
                <el-option key="2" label="不合规"
                           value="2"/>
              </el-select>
            </el-form-item>
          </el-col>-->
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="结论说明：" prop="remark">
              <el-input :disabled="eShowFooter" style="width: 638px;" type="textarea" row="2" maxlength="1000"
                        v-model="recordForm.remark"
                        class="item-width" clearable size="mini"
                        placeholder="请输入结论说明"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <hospital :value="hospitalDialog" @closeHospital="closeHospital" :claimtype="searchForm.claimtype"
                @getPropData="getPropData"/>
    </el-card>
  </div>
</template>

<script>
  import {
    addBatchAndStanding,
    addBatch,
    getBatch,
    addBatchAndStandingPresent,
    updateClaimBatch,
    selectRecordByBatchno,
    getStanding,
    getThisDept,
    getInfoBaseCodeMappingNew,
  } from '@/api/claim/presentingReview'
  import {getDeptById} from '@/api/claim/standingBookSearch'
  import Hospital from "../../basicInfoManage/publicVue/hospital";
  import {getHospitalInfo} from '@/api/claim/handleCom'
  //医院
  let dictss = [{dictType: 'priority_reason'}, {dictType: 'insurance_type'}, {dictType: 'claimType'}
    , {dictType: 'sys_yes_no'}, {dictType: 'special_case'}, {dictType: 'claim_material'},
    {dictType: 'examine_result'}, {dictType: 'claim_currency'},]
  export default {
    components: {Hospital},
    data() {
      const checkBatchtotal = (rule, value, callback) => {
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (!value) {
          callback(new Error("批次总金额必填"));
        } else {
          if (value < 0) {
            callback(new Error("批次总名额不为正数，请检查"));
          } else if (!regx.test(value)) {
            callback(new Error("批次总名额最多保留两位小数，请检查"));
          } else {
            callback();
          }
        }
      }
      const checkCasenum = (rule, value, callback) => {
        const regx = /^[1-9]{1}[\d]*$/
        if (!value) {
          callback(new Error("案件数额必填"));
        } else {
          if (!regx.test(value)) {
            callback(new Error("案件数不为正整数，请检查"));
          } else {
            callback();
          }
        }
      }
      const checkRemark = (rule, value, callback) => {
        if (!value) {
          if (this.recordForm.conclusion === '02') {
            callback(new Error("请录入结论说明"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      }
      return {
        hospitalDialog: false,
        eShowFooter: false,
        eReview: false,
        eSaveSub: false,
        eSaveOrSub: false,
        canPrint: true,
        isPrint: true,
        isReview: false,//复核完毕
        isSaveOrSub: false,//提交或保存
        isSaveSub: false,//提交保存
        show: false,//事后案件台账
        isAfter: false,//事后
        isDirect: false,//直结
        isShowFooter: false,//结论
        isShow: false,
        querys: null,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        rules: {
          claimtype: {trigger: ['blur', 'change'], required: true, message: '理赔类型必填'}, // 理赔类型
          submitdate: {trigger: 'blur', required: true, message: '交单日期必填'}, // 交单日期
          chname1: {trigger: ['blur', 'change'], required: true, message: '就诊医院必填'}, // 就诊医院
          batchtotal: [{validator: checkBatchtotal, required: true, trigger: 'blur'}], // 批次总名额
          currency: {trigger: ['blur', 'change'], required: true, message: '账单币种必填'}, // 账单币种
          conttype: {trigger: ['blur', 'change'], required: true, message: '个险/团险必填'}, // 个险/团险
          casenum: [{validator: checkCasenum, required: true, trigger: 'blur'}], // 案件数
          organcode: {trigger: ['blur', 'change'], required: true, message: '机构必填'}, // 机构
          expressnumber: {trigger: 'blur', required: true, message: '快递号必填'}, // 快递号
          receivedate: {trigger: 'blur', required: true, message: '接单日期必填'}, // 接单日期
          sendby: {trigger: ['blur', 'change'], required: true, message: '交件人必填'}, // 交件人
          contno: {trigger: ['blur', 'change'], required: true, message: '特殊案件为健康委托件时，保单号必录'}, //
        },
        recordRule: {
          conclusion: {trigger: ['blur', 'change'], required: true, message: '审核结论必填'}, // 审核结论
          remark: [{validator: checkRemark, trigger: 'blur'}], // 结论说明
        },
        afterTableTotal: 0,
        afterTable: [],
        tableData: [],
        loading: false,
        isinit: 'Y',
        hasBlock: false,
        caseLoading: false,
        page: 1,
        total: 0,
        finishTotal: 0,
        pageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        pageSizes: [5, 10, 20, 30, 50, 100],
        activeName: '01',
        completedTableData: [],
        pendingTableData: [],
        deptName: '',
        searchForm: {
          source: '03',//交单来源 01线下 02线上 03线下
          batchno: undefined,//批次号
          claimtype: undefined,//理赔类型
          submitdate: undefined,//交单日期
          hospitalcode: '01',//就诊医院id
          chname1: undefined,//就诊医院名称
          hospitalename: undefined,//就诊医院名称
          batchtotal: undefined,//批次总金额
          currency: undefined,//账户币种
          conttype: undefined,//个险/团险
          casenum: undefined,//案件数
          organcode: undefined,//机构
          billrecevieflag: undefined,//是否收到发票
          prireason: undefined,//优先原因
          expressnumber: undefined,//快递号
          receivedate: undefined,//收单日期
          sendby: undefined,//交件人
          remark: undefined,//批次备注
          speccasetype: undefined,//特殊案件类型
          Issuingunit: undefined,//投保单位
          contno: undefined,//保单号
          batchstatus: undefined,//状态
        },
        recordForm: {
          conclusion: '',//结论
          remark: '',//备注
        },
        changeSerchData: {},
        dictList: [],
        priority_reasonOptions: [],
        insurance_typeOptions: [],
        claimTypeOptions: [],
        sys_yes_noOptions: [],
        special_caseOptions: [],
        claim_materialOptions: [],
        examine_resultOptions: [],
        hospitalOptions: [],
        claim_currencyOptions: [],
        deptOptions: [],
      }
    },

    async created() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.priority_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'priority_reason'
      }).dictDate
      this.insurance_typeOptions = this.dictList.find(item => {
        return item.dictType === 'insurance_type'
      }).dictDate
      this.claimTypeOptions = this.dictList.find(item => {
        return item.dictType === 'claimType'
      }).dictDate
      this.sys_yes_noOptions = this.dictList.find(item => {
        return item.dictType === 'sys_yes_no'
      }).dictDate
      this.special_caseOptions = this.dictList.find(item => {
        return item.dictType === 'special_case'
      }).dictDate
      this.claim_materialOptions = this.dictList.find(item => {
        return item.dictType === 'claim_material'
      }).dictDate
      this.examine_resultOptions = this.dictList.find(item => {
        return item.dictType === 'examine_result'
      }).dictDate
      this.claim_currencyOptions = this.dictList.find(item => {
        return item.dictType === 'claim_currency'
      }).dictDate
      getThisDept().then(res => {
        this.deptOptions = res.deptlist
      })
    },
    async mounted() {
      let date = new Date()
      this.searchForm.submitdate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
      if (this.$route.query.data) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        await getBatch(this.querys.batchno).then(res => {
          this.searchForm = res.data
          if (this.searchForm.claimtype === '01') { //直结 特殊案件码表待定  E生宝贝
            this.isSaveSub = true
            this.isSaveOrSub = false
            this.isDirect = true
            this.isAfter = false
          } else if (this.searchForm.claimtype === '02') {//事后 特殊案件码表待定 基金物流
            this.isSaveSub = false
            this.isSaveOrSub = true
            this.isDirect = false
            this.isAfter = true
          }
        })
        if (this.querys.status === 'show') {
          this.isSaveSub = false
          this.isSaveOrSub = false
          this.isShow = true
          this.eShowFooter = true
          selectRecordByBatchno(this.searchForm.batchno).then(res => {
            if (res != null && res.code === 200 && res.data.length > 0) {
              if (res.data[0]!=null){
                this.recordForm = res.data[0]
                this.isShowFooter = true
              }
            } else {
              this.isShowFooter = false
            }
          })
          //查看时是直结调用查询医院接口
          if (this.searchForm.claimtype === '01') {
            if (this.searchForm.hospitalcode != null && this.searchForm.hospitalcode !== '') {
              let data = {
                //医院编码
                providerCode: this.searchForm.hospitalcode
              }
              //医院
              getHospitalInfo(data).then(res => {
                if (res != null && res !== '') {
                  this.tableData = res.rows
                }
              }).catch(res => {
              })
            }
          } else {//查看时是事后调用查询事后案件台账
            let item = {
              batchNo: this.querys.batchno
            }
            getStanding(item).then(res => {
              if (res != null && res.code === 200) {
                this.afterTable = res.rows
              }
            }).catch(res => {
            })
          }
          if (this.searchForm.batchstatus === '05') {//失效状态  条码打印不可用
            this.isPrint = true
          }
        } else if (this.querys.status === 'edit') {
          this.eShowFooter = true
          selectRecordByBatchno(this.searchForm.batchno).then(res => {
            if (res != null && res.code === 200 && res.data.length > 0) {
              if (res.data[0]!=null){
                this.recordForm = res.data[0]
                this.isShowFooter = true
              }
            } else {
              this.isShowFooter = false
            }
          })
          //查看时是直结调用查询医院接口
          if (this.searchForm.claimtype === '01') {
            if (this.searchForm.hospitalcode != null && this.searchForm.hospitalcode !== '') {
              let data = {
                //医院编码
                providerCode: this.searchForm.hospitalcode
              }
              //医院
              getHospitalInfo(data).then(res => {
                if (res != null && res !== '') {
                  this.tableData = res.rows
                }
              }).catch(res => {
              })
            }
          } else {//查看时是事后调用查询事后案件台账
            let item = {
              batchNo: this.querys.batchno
            }
            getStanding(item).then(res => {
              if (res != null && res.code === 200) {
                this.afterTable = res.rows
              }
            }).catch(res => {
            })
          }
        } else if (this.querys.status === 'editReview') {
          this.isShowFooter = true
          this.isSaveSub = false
          this.isReview = true
          //查看时是直结调用查询医院接口
          if (this.searchForm.claimtype === '01') {
            if (this.searchForm.hospitalcode != null && this.searchForm.hospitalcode !== '') {
              let data = {
                //医院编码
                providerCode: this.searchForm.hospitalcode
              }
              //医院
              getHospitalInfo(data).then(res => {
                if (res != null && res !== '') {
                  this.tableData = res.rows
                }
              }).catch(res => {
              })
            }
          } else {//查看时是事后调用查询事后案件台账
            let item = {
              batchNo: this.querys.batchno
            }
            getStanding(item).then(res => {
              if (res != null && res.code === 200) {
                this.afterTable = res.rows
              }
            }).catch(res => {
            })
          }
        }
        if (this.querys.batchstatus === '03') {
          this.isPrint = false
        }
      } else {
        getThisDept().then(res => {
          if (res != null) {
            this.searchForm.organcode = res.deptId
            if (res.deptId != null && res.deptId !== '') {
              getDeptById(res.deptId).then(res => {
                if (res != null && res.code === 200) {
                  this.deptName = res.data.deptName
                }
              })
            }
          }
        })

      }
      let data = {}
      getHospitalInfo(data).then(res => {
        if (res != null && res !== '') {
          this.hospitalOptions = res.rows
          this.$set(this.searchForm, 'chname1', this.selectHospitalName(this.hospitalOptions, this.searchForm.hospitalcode))
        }
      })
    },
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      openHospitalDialog() {
        if (this.searchForm.claimtype === null || this.searchForm.claimtype === '' || this.searchForm.claimtype === undefined) {
          return this.$message.warning(
            "请先选择理赔类型！"
          );
        } else {
          this.hospitalDialog = true
        }

      },
      goBack() {
        this.$router.go(-1)
      },
      //理赔类型改变
      typeChange() {
        if (this.searchForm.claimtype === '01') {//直结 特殊案件码表待定  E生宝贝
          if (this.$route.query.status || this.querys.status !== 'editReview') {
            this.isSaveSub = true
            this.isSaveOrSub = false
          }
          this.isDirect = true
          this.isAfter = false
          this.searchForm.prireason = undefined
          this.searchForm.expressnumber = undefined
          this.searchForm.receivedate = undefined
          this.searchForm.sendby = undefined
        } else if (this.searchForm.claimtype === '02') {//事后 特殊案件码表待定 基金物流
          if (this.$route.query.status || this.querys.status !== 'editReview') {
            this.isSaveSub = false
            this.isSaveOrSub = true
          }
          this.isAfter = true
          this.isDirect = false
        }
      },
      save() {
        this.hasBlock = false
        this.$refs.searchForm.validate((valid) => {
          if (valid) {
            let num = 0;
            if (this.searchForm.casenum != null) {
              num = this.searchForm.casenum
            }
            if (num >= this.afterTableTotal) {
              //筛选已填写身份证号的  后面都必须填写
              let table = this.afterTable.filter(item => {
                return item.idno != null && item.idno !== ''
              })
              console.log(table);
              table.forEach((v, i) => {
                for (const val in v) {
                  if (this.hasBlock) {
                    break
                  }
                  if (v[val] === "" || v[val] === null || v[val].loading) {
                    for (const childrenVal in v) {
                      if (v[childrenVal] === "" || v[childrenVal] === null) {
                        //el-table中列绑定的字段
                        if (`${childrenVal}` === "name") {
                          this.hasBlock = true;
                          // el-table中列表头内容
                          return this.$message.warning(
                            "被保人不能为空"
                          );
                        } else if (`${childrenVal}` === "claimmaterials") {
                          this.hasBlock = true;
                          return this.$message.warning(
                            "理赔材料不能为空"
                          );
                        } else if (`${childrenVal}` === "remark") {
                          this.hasBlock = true;
                          return this.$message.warning(
                            "备注不能为空"
                          );
                        } else if (`${childrenVal}` === "otherinfo") {
                          this.hasBlock = true;
                          return this.$message.warning(
                            "其他（案件去向）不能为空"
                          );
                        }
                      }
                    }
                  }
                  if (v.claimmaterials.length <= 0) {
                    this.hasBlock = true;
                    return this.$message.warning(
                      "理赔材料不能为空"
                    );
                  }
                }
              });
              //前面都通过则继续进行
              if (!this.hasBlock) {
                //请求接口保存table获取afterTable  给afterTableTotal赋值
                //then里面的
                let data = {
                  claimBatch: this.searchForm, //
                  standingData: table//
                }
                addBatchAndStanding(data).then(res => {
                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                    if (res.data.standingData != null) {
                      this.afterTable = res.data.standingData
                      this.afterTableTotal = res.data.standingData.length
                    } else {
                      this.afterTable = []
                    }
                    this.searchForm = res.data.claimBatch
                    this.$set(this.searchForm, 'chname1', this.selectHospitalName(this.hospitalOptions, res.data.claimBatch.hospitalcode))
                    this.show = true;
                    for (let i = 0; i < res.data.claimBatch.casenum - this.afterTableTotal; i++) {
                      let data = {
                        rptno: '',
                        idno: '',
                        name: '',
                        claimmaterials: '',
                        remark: '',
                        otherinfo: '',
                      }
                      this.afterTable.push(data);
                    }
                  } else {
                    this.$message.error('保存失败！')
                  }
                })
              }
            } else {
              return this.$message.warning(
                "修改案件数量不能少于以保存的案件数量!"
              );
            }
          } else {
            return this.$message.warning(
              "请录入必要信息！"
            );
          }
        })


      },
      saveSubmit() {
        this.$refs.searchForm.validate((valid) => {
          if (valid) {
            let claimBatch = this.searchForm
            addBatch(claimBatch).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                //更新数据
                if (res.data != null) {
                  this.searchForm = res.data
                  this.$set(this.searchForm, 'chname1', this.selectHospitalName(this.hospitalOptions, res.data.hospitalcode))
                }
                this.eSaveSub = true
                this.isSaveSub = true
                this.isPrint = true
                this.isSaveOrSub = false
                this.isShow = true
                this.eShowFooter = true
              } else {
                this.$message.error(
                  "保存提交失败!"
                );
              }
            })
          } else {
            return this.$message.warning(
              "请录入必要信息！"
            );
          }
        })
      },

      print() {
      },
      submit() {
        if (this.searchForm.batchno != null) {
          this.hasBlock = false
          this.$refs.searchForm.validate((valid) => {
            if (valid) {
              this.afterTable.forEach((v, i) => {
                for (const val in v) {
                  if (this.hasBlock) {
                    break
                  }
                  if (v[val] === "" || v[val] === null) {
                    for (const childrenVal in v) {
                      if (v[childrenVal] === "" || v[childrenVal] === null) {
                        //el-table中列绑定的字段
                        if (`${childrenVal}` === "idno") {
                          this.hasBlock = true;
                          // el-table中列表头内容
                          return this.$message.warning(
                            "证件号码不能为空"
                          );
                        } else if (`${childrenVal}` === "name") {
                          this.hasBlock = true;
                          // el-table中列表头内容
                          return this.$message.warning(
                            "被保人不能为空"
                          );
                        } else if (`${childrenVal}` === "claimmaterials") {
                          this.hasBlock = true;
                          return this.$message.warning(
                            "理赔材料不能为空"
                          );
                        } else if (`${childrenVal}` === "remark") {
                          this.hasBlock = true;
                          return this.$message.warning(
                            "备注不能为空"
                          );
                        } else if (`${childrenVal}` === "otherinfo") {
                          this.hasBlock = true;
                          return this.$message.warning(
                            "其他（案件去向）不能为空"
                          );
                        }
                      }
                    }
                  }
                  if (v.claimmaterials.length <= 0) {
                    this.hasBlock = true;
                    return this.$message.warning(
                      "理赔材料不能为空"
                    );
                  }
                }

              })
              //前面都通过则继续进行
              if (!this.hasBlock) {
                //请求接口保存table获取afterTable  给afterTableTotal赋值
                //then里面的
                let data = {
                  claimBatch: this.searchForm, //
                  standingData: this.afterTable//
                }
                addBatchAndStandingPresent(data).then(res => {
                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                    if (res.data.standingData != null) {
                      this.afterTable = res.data.standingData
                      this.afterTableTotal = res.data.standingData.length
                    }
                    this.show = true
                    this.isSaveSub = false
                    this.isPrint = false
                    this.eSaveOrSub = true
                    this.isShow = true
                    this.eShowFooter = true
                  } else {
                    this.$message.error('保存失败！')
                  }
                })
              }
            } else {
              return this.$message.warning(
                "请录入必要信息！"
              );
            }
          })
        } else {
          return this.$message.warning(
            "请先保存！"
          )
        }

      },
      review() {
        this.$refs.searchForm.validate((valid) => {
          if (valid) {
            this.$refs.recordForm.validate((valid) => {
              if (valid) {
                let data = {
                  claimBatch: this.searchForm, //
                  claimBatchRecord: this.recordForm//
                }
                updateClaimBatch(data).then(res => {
                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '复核成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    })
                    this.show = true
                    if (this.recordForm.conclusion === '01') {
                      this.isPrint = false
                    } else {
                      this.isPrint = true
                    }
                    this.eReview = true
                    this.isShow = true
                  } else {
                    this.$message.error('复核失败！')
                  }
                })
              } else {
                return this.$message.warning(
                  "请录入必要信息！"
                )
              }
            });
          } else {
            return this.$message.warning(
              "请录入必要信息！"
            )
          }
        });
      },
      closeHospital() {
        this.hospitalDialog = false
      },
      getPropData(val) {
        this.searchForm.chname1 = val.chname1
        this.searchForm.hospitalename = val.enname1
        this.searchForm.hospitalcode = val.providerCode
        let data = {
          codeType: 'YYBZ',
          originalCode: val.currency,
        }
        getInfoBaseCodeMappingNew(data).then(res => {
          if (res != null && res.code === 200) {
            this.searchForm.currency = res.data.targetCode
          }
        })

        this.tableData = []
        this.tableData.push(val)
      },
      getClaimmaterials(value) {
        let material = ''
        if (value !== null && value !== undefined && value !== '') {
          let list = value.split(',')
          for (let i = 0; i < list.length; i++) {
            if (i === list.length - 1) {
              material = material + this.selectDictLabel(this.claim_materialOptions, list[i])
            } else {
              material = material + this.selectDictLabel(this.claim_materialOptions, list[i]) + '，'
            }
          }
        }
        return material
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
      getDeptName(deptId) {
        let deptName = ''
        if (deptId != null && deptId !== '') {
          getDeptById(deptId).then(res => {
            if (res != null && res.code === 200) {
              deptName = res.data.deptName
              return deptName
            }
          })
        }
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  .baseInfo_class .el-tag--small {
    margin-right: 10px !important;
  }
</style>
