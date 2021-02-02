<template>
  <div class="app-container" style="margin-bottom: 20px;">
    <el-card style="margin-bottom: 10px">
      <el-row>
        <span style="color:#409EFF;font-size:12px">赔案号：{{ claimNo }}</span>
        <span style="color:#409EFF;font-size:12px; padding: 0 20px;">被保人姓名：{{ insuredName }}</span>
        <span style="color:#409EFF;font-size:12px">被保人身份证号：{{ idNo }}</span>
        <el-button v-if="recoverNodeFlag == true && showFlag == true" type="primary" style="float:right" :disabled="node !== 'classificat'" size="mini" @click="recoverNode">回退</el-button>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header" class="clearfix">
        <span>{{ title }}</span>
        <!-- <el-button style="float: right;" type="primary" size="mini" @click="goBack">返回</el-button> -->
      </div>
      <el-row>
        <el-col :span="15">
          <el-card class="img-card inner-header" shadow="never" style="margin-right: 6px;">
            <div v-if="node==='classificat'" slot="header" class="clearfix" style="height: 18px;text-align: center">
              <span>{{dict.label.image_type[selType]}}</span>
            </div>
            <div id="wrap">
              <div id="box" :style="imgStyle" class="box" @mousedown="mousedownHandle($event)">
                <img v-if="urls.length > 0 && src !== ''" :src="src" style="height:80%;width:80%">
              </div>
            </div>
          </el-card>
          <div class="operate-bar">
            <i class="el-icon-zoom-in" @click="handleActions('zoomIn')"/>
            <i class="el-icon-zoom-out" @click="handleActions('zoomOut')"/>
            <el-button
              :disabled="imgIdx===1||!urls.length"
              type="text"
              style="font-size: 18px; padding-right: 6px;"
              @click="preViewImage('pre')"><i class="el-icon-arrow-left"/></el-button>
            <span style="vertical-align: top;">{{ imgIdx }} / {{ urls.length }}</span>
            <el-button
              :disabled="imgIdx===urls.length||!urls.length"
              type="text"
              style="font-size: 18px; padding-left: 6px;"
              @click="preViewImage('next')"><i class="el-icon-arrow-right"/></el-button>
            <i class="el-icon-refresh-right" @click="handleActions('clocelise')"/>
          </div>
          <div v-if="node==='classificat'" class="handle-box">
            <div v-for="(item, i) in urls" :key="i" class="block" style="position: relative;border:1px solid #EBEEF5">
              <!-- <i v-show="item.pageType!=='9'" class="el-icon-circle-check icon-badge"></i> -->
              <!--               <el-checkbox v-model="item.checked">{{ i+1 }}</el-checkbox>-->
              <i v-if="item.isClassification === 'Y'" class="el-icon-success" style="color:#00FF00;position: absolute;top: 0;right: 0;z-index:999;margin-right: 5px"></i>
              <el-image
                :src="item.src"
                style="width: 70px; height: 50px; padding-right: 6px;cursor: pointer;"
                :class="item.isClassification !== 'Y' ? 'show1' : 'show2'"
                fit="fill"
                @click="changeImage(item.src,item.pageType,item.originals, i,item.pageId)"/>
              <p :class="{'active': imgIdx === i +1}" style="width: 64px; text-align: center; margin-top: 5px;">
                <!--                {{ i+1 }}-->
                <el-checkbox ref="checkImage" :label="i" style="display: inline-block">{{ &nbsp; }}</el-checkbox> {{ i+1 }}
              </p>
            </div>
          </div>
          <div v-if="node==='input'" class="handle-box">
            <div v-for="(item, i) in urls" :key="i" class="block">
              <!-- <i v-show="item.pageType!=='9'" class="el-icon-circle-check icon-badge"></i> -->
              <!--               <el-checkbox v-model="item.checked">{{ i+1 }}</el-checkbox>-->
              <el-image
                :src="item.src"
                style="width: 70px; height: 50px; padding-right: 6px;cursor: pointer;"
                fit="fill"
                @click="changeImage(item.src,item.pageType,item.originals, i,item.pageId)"/>
              <p :class="{'active': imgIdx === i +1}" style="width: 64px; text-align: center; margin-top: 5px;">
                {{ i+1 }}
              </p>
            </div>
          </div>
        </el-col>
        <el-col v-if="node==='classificat' && showFlag == true" :span="9" class="inner-header">
          <el-card shadow="never" style="padding-bottom: 15px">
            <div slot="header" class="clearfix">
              <span>材料类型</span>
            </div>
            <div class="type_box">
              <el-radio-group v-model="selType" @change="saveTypeHandle">
                <p v-for="option in dict.more_image_type" :key="option.id" class="type_item">
<!--                <p v-for="option in dict.more_image_type" :key="option.id" class="type_item">-->
                  <el-radio :label="option.value" :value="option.value">{{ option.label }}</el-radio>
                </p>
              </el-radio-group>
              <div style="margin-left: 24px;font-size: 14px;color: #606266;font-weight: 500;line-height: 1">未分类（{{unclassifiednum}}）</div>
            </div>
            <el-form :model="form" size="mini">
              <el-form-item label-width="0px">
                <el-checkbox v-model="form.checked">复印件</el-checkbox>
              </el-form-item>
              <el-form-item label="备注">
                <el-input
                  v-model="form.remark"
                  :rows="2"
                  type="textarea"
                  maxlength="30"
                  show-word-limit
                  autocomplete="off"
                  placeholder="请输入" />
              </el-form-item>
            </el-form>
            <span style="float: right;">
              <el-button type="success" size="mini" @click="classifyDone">分类完成</el-button>
<!--              <el-button type="primary" size="mini" @click="imageTypeSave">保存</el-button>-->
            </span>
          </el-card>
        </el-col>
        <el-col v-if="node==='input'" :span="9">
          <el-card shadow="never" style="padding-bottom: 15px;height: 490px;overflow-y: scroll;">
            <el-tabs v-model="activeName"  :before-leave="beforeLeave">
              <el-tab-pane label="发票信息" name="first">
                <el-form ref="invoiceForm" :rules="invoiceFormRules" :model="invoiceForm" size="mini" label-width="130px">
                  <el-form-item label="发票号：" prop="invoiceNo">
                    <el-input v-model="invoiceForm.invoiceNo" :disabled="disabledButton" class="item_width"  autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="发票类型：" prop="invoiceType">
                    <el-select v-model="invoiceForm.invoiceType" class="item_width" placeholder="请选择" clearable>
                      <el-option v-for="option in dict.invoicetype" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="是否医保结算：" prop="socialSecuritySettlement">
                    <el-select v-model="invoiceForm.socialSecuritySettlement" class="item_width" placeholder="请选择" @change="dataChange" clearable>
                      <el-option v-for="option in dict.socialsecuritysettlement" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="总金额：" prop="amount">
                    <el-input-number v-model="invoiceForm.amount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable />
                  </el-form-item>
                  <el-form-item label="统筹支付：" prop="overallPayment">
                    <el-input-number v-model="invoiceForm.overallPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入"  clearable/>
                  </el-form-item>
                  <el-form-item label="第三方支付：" prop="thirdPartyPayment">
                    <el-input-number v-model="invoiceForm.thirdPartyPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable />
                  </el-form-item>
                  <!-- <el-form-item label="结算类型：" prop="settlementType">
                    <el-select v-model="invoiceForm.settlementType" class="item_width" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item> -->
                  <el-form-item label="发票起/止日期：" prop="dateRange">
                    <el-date-picker
                      v-model="invoiceForm.dateRange"
                      style="width: 240px;"
                      type="daterange"
                      range-separator="~"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      clearable/>
                  </el-form-item>
                  <el-form-item label="是否统筹分摊：" prop="overallPaymentShare"  label-width="130px">
                    <el-select v-model="invoiceForm.overallPaymentShare" class="item_width" placeholder="请选择" clearable>
                      <el-option v-for="option in dict.overallpaymentshare" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="现金支付：" prop="cashPayment">
                    <el-input-number v-model="invoiceForm.cashPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入"  clearable/>
                  </el-form-item>
                  <el-form-item label="账户支付：" prop="accountPayment">
                    <el-input-number v-model="invoiceForm.accountPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="是否第三方分摊：" title="是否第三方分摊" prop="thirdPartyPaymentShare">
                    <el-select v-model="invoiceForm.thirdPartyPaymentShare" disabled class="item_width" placeholder="请选择" clearable>
                      <el-option v-for="option in dict.thirdpartypaymentshare" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="比例自付：" prop="payByCategory">
                    <el-input-number v-model="invoiceForm.payByCategory" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="自费金额：" prop="selfPayment">
                    <el-input-number v-model="invoiceForm.selfPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="就诊人姓名：" prop="patient">
                    <el-input v-model="invoiceForm.patient" :maxlength="20" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="就诊医院：" prop="hospital">
                    <el-select
                      v-model="invoiceForm.hospital"
                      :remote-method="remoteHospitals"
                      :loading="loading"
                      filterable
                      remote
                      reserve-keyword
                      placeholder="请输入医院关键字"
                      @change="handleChange"
                      style="width:240px"
                      clearable>
                      <el-option v-for="option in hospitals" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="费用项信息" name="second">
                <el-table
                  :data="detailData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark"
                  @selection-change="handleSelectionChange">
                  <el-table-column prop="date" type="selection"/>
                  <el-table-column prop="feeitemname" label="费用类型"/>
                  <el-table-column prop="amount" label="总金额"/>
                  <el-table-column prop="unreasonableamount" label="不合理费用"/>
                  <el-table-column prop="unreasonableexplain" width="120" label="不合理费用说明"/>
                </el-table>
                <p>
                  <span style="font-size: 14px; display: inline-block; padding-top: 6px;">累计总金额：{{ amount | numFilter }}</span>
                  <el-button type="danger" style="float: right;" size="mini" @click="deleteDetail">删除</el-button>
                </p>
                <el-divider/>
                <el-form ref="invoiceFormDetail" :rules="invoiceFormDetailRules" :model="invoiceFormDetail" style="margin-top: 20px;" size="mini" label-width="90px">
                  <el-form-item label="费用项类型：" prop="feetIem" label-width="130px">
                    <el-select v-model="invoiceFormDetail.feetIem" class="item_width" placeholder="请选择" clearable>
                      <el-option v-for="option in feeitems" :key="option.id" :label="option.label" :value="option.value" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="总金额：" prop="amount" label-width="130px">
                    <el-input-number v-model="invoiceFormDetail.amount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="统筹支付：" prop="overallPayment" label-width="130px">
                    <el-input-number v-model="invoiceFormDetail.overallPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="第三方支付：" prop="thirdPartyPayment" label-width="130px">
                    <el-input-number v-model="invoiceFormDetail.thirdPartyPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="比例自付：" prop="payByCategory" label-width="130px">
                    <el-input-number v-model="invoiceFormDetail.payByCategory" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="自费金额：" prop="selfPayment" label-width="130px">
                    <el-input-number v-model="invoiceFormDetail.selfPayment" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" clearable/>
                  </el-form-item>
                  <el-form-item label="不合理费用：" prop="unReasonableAmount" label-width="130px">
                    <el-input-number v-model="invoiceFormDetail.unReasonableAmount" :min="0" :precision="2" :step="0.01" class="item_width" autocomplete="off" placeholder="请输入" @change="inputAmount" clearable/>
                  </el-form-item>
                  <el-form-item label="不合理费用说明：" prop="unReasonableExplain" label-width="130px">
                    <el-input v-model="invoiceFormDetail.unReasonableExplain" type="textarea" placeholder="请输入内容" maxlength="500" show-word-limit class="item_width" autocomplete="off" clearable/>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
            </el-tabs>
            <el-divider/>
            <!-- <el-form ref="invoiceForm" :model="invoiceForm" size="mini" label-width="90px">
              <el-form-item label="明细编码：" prop="fph">
                <span>20200525001</span>
              </el-form-item>
              <el-form-item label="明细名称：">
                <el-select v-model="invoiceForm.type" class="item_width" placeholder="请选择" clearable>
                  <el-option label="一" value="1"/>
                  <el-option label="二" value="2"/>
                </el-select>
              </el-form-item>
              <el-form-item label="明细金额：" prop="fph">
                <el-input v-model="invoiceForm.fph" class="item_width" autocomplete="off" placeholder="请输入" />
              </el-form-item>
              <el-form-item label="社保类型：">
                <el-select v-model="invoiceForm.type" class="item_width" placeholder="请选择" clearable>
                  <el-option label="一" value="1"/>
                  <el-option label="二" value="2"/>
                </el-select>
              </el-form-item>
              <el-form-item label="给付比例：" prop="fph">
                <el-input v-model="invoiceForm.fph" class="item_width" autocomplete="off" placeholder="请输入">
                  <template slot="append">%</template>
                </el-input>
              </el-form-item>
            </el-form> -->
          </el-card>
          <div style="float: right; margin-top: 45px;">
            <!-- <el-button type="primary" size="mini">录入完毕</el-button> -->
            <div v-show="activeName === 'first'">
              <el-button type="primary" size="mini" @click="invoiceFormSave">保 存</el-button>
              <el-button type="primary" size="mini" @click="resetForm">重 置</el-button>
            </div>
            <div v-show="activeName === 'second'">
              <el-button type="primary" size="mini" @click="next">下一张</el-button>
              <el-button type="primary" size="mini" @click="invoiceFormDetailSave">保 存</el-button>
              <el-button type="primary" size="mini" @click="resetDetailForm">重 置</el-button>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card >
    <el-dialog
      title="分类回退操作"
      :visible.sync="dialogVisible"
      width="560px"
      :close-on-click-modal="false"
      >
      <el-form v-if="dialogVisible" ref="diaForm" :model="diaForm" :rules="tableFormRules" label-width="100px" size="mini">
        <el-form-item label="退回节点：" prop="status">
          <el-select v-model="diaForm.status" clearable style="width:300px" @change="changeStatus" placeholder="请选择">
            <el-option label="受理" value="02"/>
            <el-option label="扫描" value="22"/>
          </el-select>
        </el-form-item>
        <el-form-item label="回退原因：" prop="error">
          <el-select v-model="diaForm.error" style="width:300px" @change="changeErrortype" clearable class="item-width" placeholder="请选择">
            <el-option v-for="item in errortypeList" :label="item.label" :value="item.value" :key="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="退回描述：" prop="explanation">
          <el-input
            type="textarea"
            style="width:300px"
            :autosize="{ minRows: 3, maxRows: 6}"
            placeholder="请输入内容"
            maxlength="200"
            show-word-limit
            v-model="diaForm.explanation">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" type="primary" :loading="submitLoading" @click="confireList">确认</el-button>
        <el-button size="mini" @click="dialogVisible=false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import dragAndScale from './mixins/dragAndScale'
  import { classifyFinish, getFeeitems, getInfo } from '@/api/claim/register'
  import { billInfoSave, costDetailSave, getCostDetailList, getHospitalLike, getImages, classification, deleteDetails, judgeBills } from '@/api/claim/input'
  import { preView } from '@/api/claim/fileDeal'
  import {decrypt} from "@/utils/rsaEncrypt"
  import { _debounce } from '@/utils/commenMethods.js'
  import {saveConclusion,preViewThumbnail} from '@/api/claim/handleDeal'

  export default {
    mixins: [dragAndScale],
    dicts: ['image_type', 'invoicetype', 'socialsecuritysettlement', 'thirdpartypaymentshare', 'overallpaymentshare', 'more_image_type','scan_accept','scan_scan'],
    filters: {
      numFilter(value)
      {
        let realVal = ''
        if (!isNaN(value) && value!== '') {
          // 截取当前数据到小数点后两位
          realVal = parseFloat(value).toFixed(2)
        } else {
          realVal = '--'
        }
        return realVal
      }
    },
    data() {
      const validateAmount = (rule, value, callback) => {
        if (!value) {
          callback(new Error('金额不能为空且不能小于0！'))
        } else {
          callback()
        }
      }
      const checkInvoive = (rule, value, callback) => {
        if (!value) {
          callback(new Error('发票号不可为空'))
        } else {
          if (value.length > 100) {
            callback(new Error('发票号小于等于100位'))
          } else {
            const str = /^[a-zA-Z0-9\-]*$/g
            if (!str.test(value)) {
              callback(new Error('发票号允许录入数字、字母、"-"'))
            } else {
              callback()
            }
          }
        }
      }
      return {
        node: '',
        claimNo: '',
        incidentNo: '',
        materialNo: '',
        recievedMode: '',
        // insuredInfo: {},
        insuredName: '',
        idNo: '',
        diaForm: {},
        tableFormRules: {
          status  : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          explanation  : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          error : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        },
        errortypeList: [],
        activeName: 'first',
        billNo: '',
        src: '',
        loading: false,
        hospitals: [],
        urls: [],
        imageInfos: [],
        multipleSelection: [],
        feeitems: [],
        selType: '',
        beforeLeaveFlag: false,
        submitLoading: false,
        dialogVisible: false,
        title: '',
        form: {
          checked: false,
          remark: ''
        },
        recoverNodeFlag: false,
        rejectreason: '',
        errortype: '',
        invoiceForm: {
          overallPaymentShare: 'Y',
          thirdPartyPaymentShare: 'N',
          patient: ''
        },
        invoiceFormRules: {
          invoiceNo : { validator: checkInvoive, required: true, trigger: ['blur', 'change'] },
          invoiceType : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          socialSecuritySettlement : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          amount : { validator: validateAmount, required: true, trigger: ['blur', 'change'] },
          dateRange  : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          overallPaymentShare : { message: '不能为空!', required: true, trigger: ['blur', 'change'] },
          thirdPartyPaymentShare : { message: '不能为空!', required: true, trigger: ['blur', 'change'] },
          patient: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          hospital: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },

        },
        invoiceFormDetail: {
          detailNo: '',
          feetIem: '',
          amount: '',
          overallPayment: '',
          thirdPartyPayment: '',
          payByCategory: '',
          selfPayment: '',
          unReasonableAmount: '',
          unReasonableExplain: ''
        },
        invoiceFormDetailRules: {
          feetIem : { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
          amount : { validator: validateAmount, required: true, trigger: ['blur', 'change'] },
          unReasonableExplain : { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
        },
        detailData: [],
        // 总金额
        amount: 0.00,
        editAmount: 0.00,
        keyCode1: null,
        keyCode2: null,
        showList: [],
        showFlag: true,
        disabledButton: false,
        unclassifiednum: 0, //未分类数量
        initLeft: '',
        initTop: ''
      }
    },
    created() {
      if (this.$route.query.node) {
        this.node = this.$route.query.node
        this.claimNo = this.$route.query.claimNo?decrypt(this.$route.query.claimNo):''
        this.showFlag = this.$route.query.showFlag == '01' ? true : false
        this.recoverNodeFlag = this.$route.query.recoverNode ? true : false
        this.incidentNo = this.$route.query.incidentNo
        // this.insuredInfo = JSON.parse(this.$route.query.insuredInfo)
        this.materialNo = this.$route.query.materialNo
        this.recievedMode = this.$route.query.recievedMode
        // this.insuredName = this.$route.query.insuredName
        this.node === 'classificat' ? this.title = '影像分类' : this.title = '账单信息'
        this.getInfo(this.claimNo)
        if(this.$route.query.status === 'edit') {
          let invoiceInfo = {}
          let from = {}
          console.log(this.$route.query.invoiceInfo,9099)
          if (this.$route.query.invoiceInfo) {
            from = JSON.parse(this.$route.query.invoiceInfo)
          } else {
            from = {}
          }
          // from = JSON.parse(this.$route.query.invoiceInfo)
          // const invoiceInfo = this.$route.query.invoiceInfo
          if (Object.keys(from).length!=0) {
            invoiceInfo = from
            this.billNo = invoiceInfo.invoiceNo
            this.invoiceForm = invoiceInfo
            this.invoiceForm.oldInvoiceNO = invoiceInfo.invoiceNo
          }
          if (invoiceInfo.invoiceNo != null) {
            this.beforeLeaveFlag = true
          } else {
            this.beforeLeaveFlag = false
          }
          // this.invoiceForm.oldInvoiceNO = ''
          // if(from!== null) {
          //   this.billNo = invoiceInfo.invoiceNo
          //   this.invoiceForm.overallPaymentShare == undefined ? 'Y' : this.invoiceForm.overallPaymentShare
          //   this.invoiceForm.thirdPartyPaymentShare == undefined ? 'N' : this.invoiceForm.thirdPartyPaymentShare
          //   console.log(this.invoiceForm,45)
          //   this.invoiceForm = invoiceInfo
          //   this.invoiceForm.oldInvoiceNO = invoiceInfo.invoiceNo
          //   console.log(this.invoiceForm.overallPaymentShare,3456)

          // }
          if(invoiceInfo.hospitalDTO !== null &&  invoiceInfo.hospitalDTO !== undefined) {
            this.hospitals.push(invoiceInfo.hospitalDTO)
          } else{
            this.hospitals.push({label:invoiceInfo.hospitalname,value: invoiceInfo.hospital})
          }
          if(this.billNo !== null && this.billNo !== "" && this.billNo !== undefined) {
            this.getCostDetails()
          }
        }
      }
      this.getFeeitem()
      if (this.node === 'classificat' && (this.claimNo !== null && this.claimNo !== '')) {
        if(this.recievedMode !== '' && this.recievedMode !== null && this.recievedMode !== undefined) {
          this.getAllImages(this.recievedMode)
        }else {
          this.getAllImages('')
        }
      }
      if (this.node === 'input') {
        this.getBillImages()
      }
      this.monitor()
      if (this.node==='classificat' && this.showFlag == true) {
        this.handleKeyDown()
        this.handleKeyUp()
      }
      // 监听快捷键
      // window.addEventListener('keyup', this.handleKeyUp)
      // window.addEventListener('keydown', this.handleKeyDown)
    },
    mounted() {
      const wrap = document.getElementById('wrap')
      const box = document.getElementById('box')
      box.style.left = (wrap.offsetWidth - box.offsetWidth) / 2 + 'px'
      box.style.top = 50 + 'px'
      this.initLeft = box.style.left
      this.initTop = box.style.top
    },
    destroyed() {
      if (this.node==='classificat') {
        document.onkeydown = null
        document.onkeyup = null
      }
      // window.removeEventListener("onkeydown", this.handleKeyDown, true)
      // window.removeEventListener("onkeyup", this.handleKeyUp, true)
    },
    methods: {
      getCostDetails() {
        const requestData = {
          claimNo: this.claimNo,
          invoiceNo: this.billNo
        }
        getCostDetailList(requestData).then(response => {
          if (response.status === '1') {
            this.detailData = response.data
            this.amount = 0.00
            if (response.data !== null && response.data.length > 0) {
              for (let i = 0; i < response.data.length; i++) {
                this.amount += response.data[i].amount
              }
            }
          } else {
            this.$message({ message: '发票明细列表查询失败！', type: 'error' })
          }
        })
      },
      changeStatus(data) {
        this.errortypeList = []
        if (data == '') {
          this.errortypeList = []
          this.diaForm.error = ''
        } else {
          if (data == '02') {
            this.errortypeList = this.dict.scan_accept
          } else {
            this.errortypeList = this.dict.scan_scan
          }
        }
      },
      changeErrortype(data) {
        this.rejectreason = ''
        this.errortype = ''
        let str = data.substring(0,2)
        let str2 = data.substring(2,4)
        this.rejectreason = str2
        this.errortype = str
      },
      confireList() {
        this.$refs.diaForm.validate((valid) => {
          if (valid) {
            this.submitLoading = true
            let params = {}
            params.tasknode = 'claim_scan'
            params.claimno = this.claimNo
            params.rejectreason =  this.rejectreason
            params.conclusion ='02'
            params.errortype = this.errortype
            params.explanation = this.diaForm.explanation
            params.status = this.diaForm.status
            saveConclusion(params).then(res => {
              if (res.status === '1') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.dialogVisible = false
                setTimeout(() =>{
                  window.close()
                },3000)
              } else {
                if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
                  this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'error' })
                } else {
                  this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'warning' })
                }
              }
            }).finally(() => {
              this.submitLoading = false
            })
          } else {
            return false
          }
        })
      },
      recoverNode() {
        document.onkeydown = null
        document.onkeyup = null
        this.dialogVisible = true
      },
      monitor(){
        var that = this;
        document.onkeydown = function(e) {
          let key = window.event.keyCode;
          // 监听键盘左键
          if (key == 37) {
            if (that.imgIdx===1) {
              return false
            }
            that.preViewImage('pre')
          } else if(key == 39) {  // 监听键盘右键
            if (that.urls.length>0) {
              if (that.imgIdx=== that.urls.length) {
                return false
              }
              that.preViewImage('next')
            }
          }
        };
      },
      beforeLeave() {
        if (this.beforeLeaveFlag == false) {
          this.$message({ message: '请先保存发票信息！', type: 'warning' })
          return false
        }
      },
      inputAmount(value) {
        if(value > 0) {
          this.invoiceFormDetailRules.unReasonableExplain = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
        } else {
          this.invoiceFormDetailRules.unReasonableExplain = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
        }
      },
      dataChange(value) {
        if(value === 'Y') {
          this.invoiceForm.thirdPartyPaymentShare = 'Y'
        }else{
          this.invoiceForm.thirdPartyPaymentShare = 'N'
        }
      },
      resetForm() {
        // this.$refs.invoiceForm.resetFields()
        this.invoiceForm = {
          overallPaymentShare: 'Y',
          thirdPartyPaymentShare: 'N'
          // patient: this.insuredName
        }
      },
      resetDetailForm() {
        // this.$refs.invoiceFormDetail.resetFields()
        this.invoiceFormDetailRules.unReasonableExplain = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
        this.invoiceFormDetail = {
          detailNo: '',
          feetIem: '',
          amount: '',
          overallPayment: '',
          thirdPartyPayment: '',
          payByCategory: '',
          selfPayment: '',
          unReasonableAmount: '',
          unReasonableExplain: ''
        }
      },
      changeImage(url,type,flag, i, id) {
        this.transform = {
          scale: 1,
          deg: 0,
          offsetX: 0,
          offsetY: 0,
          enableTransition: false
        }
        const box = document.getElementById('box')
        box.style.left = this.initLeft
        box.style.top = this.initTop
        let params = {
          pageid: id
        }
        preView(id).then(response => {
          this.src = 'data:image/jpeg;base64,'+ response.data
        })
        this.imgIdx = i + 1
        this.selType = type
        this.form.checked = flag
        // this.src = url
        // this.imgIdx = index + 1
        // this.selType = '1'
      },
      preViewImage(type) {
        if (type === 'pre') {
          this.imgIdx--
          let params = {
            pageid: this.urls[this.imgIdx - 1].pageId
          }
          preView(this.urls[this.imgIdx - 1].pageId).then(response => {
            this.src = 'data:image/jpeg;base64,'+ response.data
            this.transform = {
              scale: 1,
              deg: 0,
              offsetX: 0,
              offsetY: 0,
              enableTransition: false
            }
          })
          // this.src = this.urls[this.imgIdx - 1].src
          this.selType = this.urls[this.imgIdx - 1].pageType
          this.form.checked = this.urls[this.imgIdx - 1].originals
        } else {
          this.imgIdx++
          let params = {
            pageid: this.urls[this.imgIdx - 1].pageId
          }
          preView(this.urls[this.imgIdx - 1].pageId).then(response => {
            this.src = 'data:image/jpeg;base64,'+ response.data
            this.transform = {
              scale: 1,
              deg: 0,
              offsetX: 0,
              offsetY: 0,
              enableTransition: false
            }
          })
          // this.src = this.urls[this.imgIdx - 1].src
          this.selType = this.urls[this.imgIdx - 1].pageType
          this.form.checked = this.urls[this.imgIdx - 1].originals
        }
      },
      goBack() {
        if(this.invoiceForm.amount !== null && this.invoiceForm.amount !== '' && this.invoiceForm.amount !== undefined) {
          if(this.invoiceForm.amount !== (parseFloat(this.amount).toFixed(2) - 0.00)) {
            this.$message({ message: '发票总金额与费用项总金额不一致，请核实！', type: 'warning' })
            return false
          }
          let Mover = 0.00
          let over = 0.00
          let Mthird = 0.00
          let third = 0.00
          if(this.invoiceForm.overallPayment !== '' && this.invoiceForm.overallPayment !== null && this.invoiceForm.overallPayment !== undefined) {
            Mover = this.invoiceForm.overallPayment
          }
          if(this.invoiceForm.thirdPartyPayment !== '' && this.invoiceForm.thirdPartyPayment !== null && this.invoiceForm.thirdPartyPayment !== undefined) {
            Mthird = this.invoiceForm.thirdPartyPayment
          }
          if(this.detailData !== null && this.detailData.length > 0) {
            this.detailData.forEach(item => over+=item.overallpayment)
            this.detailData.forEach(item => third+=item.thirdpartypayment)
          }
          if((parseFloat(Mover).toFixed(2)-0.00) !== (parseFloat(over).toFixed(2)-0.00)) {
            this.$message({ message: '发票统筹金额与费用项统筹总金额不一致，请核实！', type: 'warning' })
            return false
          }
          if((parseFloat(Mthird).toFixed(2)-0.00) !== (parseFloat(third).toFixed(2)-0.00)) {
            this.$message({ message: '发票第三方支付金额与费用项第三方支付总金额不一致，请核实！', type: 'warning' })
            return false
          }
        }
        this.$router.go(-1)
      },
      // 快捷键调用方法
      handleKeyUp(e) {
        let that = this
        document.onkeyup = function(e) {
          if (e.keyCode === 16) {
            that.keyCode1 = null
          } else {
            that.keyCode2 = null
          }
        }
      },
      handleKeyDown: _debounce(function(e) {
        let that = this
        document.onkeydown = function(e) {
          that.keyCode1 = null
          that.keyCode2 = null
          if (e.keyCode === 16) {
            that.keyCode1 = e.keyCode
          } else if (e.keyCode === 83 || e.keyCode === 90 ||
            e.keyCode === 71 || e.keyCode === 66 || e.keyCode === 70
            || e.keyCode === 82 || e.keyCode === 68 || e.keyCode === 67
            || e.keyCode === 65 || e.keyCode === 69) {
            that.keyCode2 = e.keyCode
          } else if (e.keyCode == 37) {
            if (that.imgIdx===1) {
              return false
            }
            that.preViewImage('pre')
          } else if (e.keyCode == 39) {
             if (that.imgIdx=== that.urls.length) {
                return false
              }
              that.preViewImage('next')
          }
          switch (that.keyCode2) {
            case 83 : that.selType = '0'; break // S 理赔申请书
            case 90 : that.selType = '1'; break // Z 身份证
            case 71 : that.selType = '2'; break // G 关系证明
            case 66 : that.selType = '3'; break // B 银行卡
            case 70 : that.selType = '4'; break // F 医疗票据
            case 82 : that.selType = '5'; break // R 病历
            case 68 : that.selType = '6'; break // D 费用清单
            case 67 : that.selType = '7'; break // C 检查报告
            case 65: that.selType = '8'; break // A 保险事故证明
            case 69 : that.selType = '9'; break // E 其他
            default : that.selType = that.selType
          }
          if (!that.keyCode1 && that.keyCode2) {
            // that.form.checked = false
            that.imageTypeSave()
          }
          if (that.keyCode1 && !that.keyCode2) {
            that.form.checked = !that.form.checked
          }
          if (that.keyCode1 && that.keyCode2) {
            that.form.checked = true
            that.imageTypeSave()
          }
        }
      }, 500),
      imageTypeSave: _debounce(function() {
        let checkFlag = this.$refs.checkImage.findIndex(item => item.isChecked)
        // if (this.src === '' && checkFlag === -1 || this.src === null && checkFlag === -1) {
        if (checkFlag === -1) {
          this.$message({ message: '请先选择图片！', type: 'warning' })
          return false
        }
        if (this.selType === '' || this.selType === null) {
          this.$message({ message: '请先选择类型！', type: 'warning' })
          return false
        }
        let flag = true
        const params = []
        this.$refs.checkImage.forEach(item => {
          if (item.isChecked) {
            const requestData = {
              pageId: this.urls[item.label].pageId,
              type: this.selType,
              original: this.form.checked
            }
            params.push(requestData)
            if (this.urls[item.label].isClassification === 'Y') {
              flag = false
            }
          }
        })
        // const requestData = {
        //   pageId: this.urls[this.imgIdx - 1].pageId,
        //   type: this.selType,
        //   original: this.form.checked
        // }
        // if(this.urls[this.imgIdx - 1].pageType === '9') {
        if(flag) {
          classification(params).then(response => {
            if (response.status === '1') {
              this.$message({ message: '保存成功！', type: 'success' })
              this.showList = []
              this.urls.forEach((item, ind) => {
                params.forEach(val => {
                  if (item.pageId === val.pageId) {
                    this.$set(this.urls[ind], 'pageType', val.type)
                    this.$set(this.urls[ind], 'originals', val.original)
                    this.$set(this.urls[ind], 'isClassification', 'Y')
                    // item.pageType = val.type
                    // item.originals = val.original
                  }
                })
              })
              this.$refs.checkImage.forEach(item => {
                item.model = false
              })
              // this.sortImages()
              this.urls = [...this.urls]
              // 页面显示图片
              let index = this.urls.findIndex(item => item.isClassification !== 'Y')
              if (index !== -1) {
                this.changeImage(this.urls[index].src,this.urls[index].pageType,this.urls[index].originals, index,this.urls[index].pageId) //src pageType
              } else {
                this.src = ''
                this.imgIdx = 0
                this.selType = ''
              }
              // this.urls[this.imgIdx - 1].pageType = requestData.type
              // this.urls[this.imgIdx - 1].originals = requestData.original
              this.getUnclassifiedNum()
            } else {
              this.$message({ message: '保存失败！', type: 'error' })
            }
          })
        } else {
          this.$confirm('当前选择的影像文件，已进行分类，请确认？', '温馨提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            classification(params).then(response => {
              if (response.status === '1') {
                this.$message({ message: '保存成功！', type: 'success' })
                this.urls.forEach((item, ind)=> {
                  params.forEach(val => {
                    if (item.pageId == val.pageId) {
                      // item.pageType = val.type
                      // item.originals = val.original
                      this.$set(this.urls[ind], 'pageType', val.type)
                      this.$set(this.urls[ind], 'originals', val.original)
                      this.$set(this.urls[ind], 'isClassification', 'Y')
                    }
                  })
                })
                this.$refs.checkImage.forEach(item => {
                  item.model = false
                })
                // this.sortImages()
                this.urls = [...this.urls]
                // 页面显示图片
                let index = this.urls.findIndex(item => item.isClassification !== 'Y')
                if (index !== -1) {
                  this.changeImage(this.urls[index].src,this.urls[index].pageType,this.urls[index].originals, index,this.urls[index].pageId) //src pageType
                } else {
                  this.src = ''
                  this.imgIdx = 0
                  this.selType = ''
                }
                // this.urls[this.imgIdx - 1].pageType = requestData.type
                // this.urls[this.imgIdx - 1].originals = requestData.original
              } else {
                this.$message({ message: '保存失败！', type: 'error' })
              }
              this.getUnclassifiedNum()
            })
          }).catch(error => {
          })
        }
      }, 500),
      // getPreView(data) {
      //   if(data !== '' && data !== null) {
      //     preView(data).then(response => {
      //       if (response.status === '1') {
      //         const url = 'data:image/jpeg;base64,' + response.data
      //         return url
      //       } else {
      //         this.$message({ message: '图片加载失败！', type: 'error' })
      //       }
      //     })
      //   }
      // },
      classifyDone() {
        if(this.urls !== null && this.urls.length > 0) {
          // this.$confirm('是否分类完成？', '温馨提示', {
          this.$confirm('影像分类完成，确认提交？', '温馨提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let businessNo = ''
            let mode = ''
            if(this.materialNo !== undefined) {
              businessNo = this.materialNo
              mode = this.recievedMode
            }
            const requestData = {
              claimNo: this.claimNo,
              materialNo: businessNo,
              recievedMode: mode
            }
            classifyFinish(requestData).then(response => {
              if (response.status === '1') {
                this.$message({ message: '分类成功！', type: 'success' })
                this.goBack()
                setTimeout(() =>{
                  window.close()
                },2000)
                // this.$router.push('image-class')
              } else {
                this.$message({ message: '分类失败！', type: 'error' })
              }
            })
          }).catch(error => {
          })
        } else {
          this.$message({ message: '请先上传影像文件，再进行影像分类！', type: 'warning' })
        }
      },
      next() {
        this.$confirm('是否进行下一张录入？', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.invoiceForm.amount !== null && this.invoiceForm.amount !== '' && this.invoiceForm.amount !== undefined) {
            if(this.invoiceForm.amount !== (parseFloat(this.amount).toFixed(2)-0.00)) {
              this.$message({ message: '发票总金额与费用项总金额不一致，请核实！', type: 'warning' })
              return false
            }
            let Mover = 0.00
            let over = 0.00
            let Mthird = 0.00
            let third = 0.00
            if(this.invoiceForm.overallPayment !== '' && this.invoiceForm.overallPayment !== null && this.invoiceForm.overallPayment !== undefined) {
              Mover = this.invoiceForm.overallPayment
            }
            if(this.invoiceForm.thirdPartyPayment !== '' && this.invoiceForm.thirdPartyPayment !== null && this.invoiceForm.thirdPartyPayment !== undefined) {
              Mthird = this.invoiceForm.thirdPartyPayment
            }
            if(this.detailData !== null && this.detailData.length > 0) {
              this.detailData.forEach(item => over+=item.overallpayment)
              this.detailData.forEach(item => third+=item.thirdpartypayment)
            }
            if((parseFloat(Mover).toFixed(2)-0.00) !== (parseFloat(over).toFixed(2)-0.00)) {
              this.$message({ message: '发票统筹金额与费用项统筹总金额不一致，请核实！', type: 'warning' })
              return false
            }
            if((parseFloat(Mthird).toFixed(2)-0.00) !== (parseFloat(third).toFixed(2)-0.00)) {
              this.$message({ message: '发票第三方支付金额与费用项第三方支付总金额不一致，请核实！', type: 'warning' })
              return false
            }
          }
          this.activeName = 'first'
          this.resetForm()
          this.resetDetailForm()
          this.detailData = []
          this.billNo = ''
          this.amount = 0.00
          this.editAmount = 0.00
          this.disabledButton = false
        }).catch(error => {
        })
      },
      invoiceFormSave() {
        this.$refs.invoiceForm.validate((valid) => {
          if (valid) {
            if(this.invoiceForm.patient.trim() !== this.insuredName) {
              this.$confirm('就诊人姓名与被保人姓名不一致,是否保存？', '温馨提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.invoiceFormRealSave()
              }).catch(error => {
              })
            }else {
              this.invoiceFormRealSave()
            }
          } else {
            return  false
          }
        })
      },
      invoiceFormRealSave() {
        let  flag = true
        if(this.$route.query.invoiceInfo !== null && this.$route.query.invoiceInfo !== undefined && this.$route.query.invoiceInfo !== '') {
          flag = false
        }
        const requestData = {
          claimNo: this.claimNo,
          incidentNo: this.incidentNo,
          billInfo: this.invoiceForm
        }
        // if(flag) {
          judgeBills(requestData).then(response => {
            if (response.status === '1') {
              if(response.data) {
                billInfoSave(requestData).then(response => {
                  if (response.status === '1') {
                    this.$message({ message: '发票信息保存成功！', type: 'success' })
                    this.billNo = response.data
                    this.beforeLeaveFlag = true
                    this.disabledButton = true
                  } else {
                    this.$message({ message: '发票信息保存失败！', type: 'error' })
                  }
                })
              }else{
                this.$confirm('该账单号重复存在，是否继续操作？', '温馨提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  billInfoSave(requestData).then(response => {
                    if (response.status === '1') {
                      this.$message({ message: '发票信息保存成功！', type: 'success' })
                      this.billNo = response.data
                      this.beforeLeaveFlag = true
                      this.disabledButton = true
                    } else {
                      this.$message({ message: '发票信息保存失败！', type: 'error' })
                    }
                  })
                }).catch(error => {
                })
              }
            } else {
              this.$message({ message: '发票信息校验失败！', type: 'error' })
            }
          })
        // }else{
        //   billInfoSave(requestData).then(response => {
        //     if (response.status === '1') {
        //       this.$message({ message: '发票信息保存成功！', type: 'success' })
        //       this.billNo = response.data
        //       this.beforeLeaveFlag = true
        //     } else {
        //       this.$message({ message: '发票信息保存失败！', type: 'error' })
        //     }
        //   })
        // }
      },
      invoiceFormDetailSave() {
        this.$refs.invoiceFormDetail.validate((valid) => {
          if (valid) {
            if(this.billNo !== '' && this.billNo !== null) {
              if(this.invoiceFormDetail.amount < (parseFloat(this.invoiceFormDetail.overallPayment + this.invoiceFormDetail.thirdPartyPayment + this.invoiceFormDetail.unReasonableAmount).toFixed(2)-0.00)) {
                this.$message({ message: '统筹+第三方+不合理金额总和不能大于总金额', type: 'warning' })
                return false
              }
              if(this.invoiceForm.amount < (parseFloat(this.amount + this.invoiceFormDetail.amount - this.editAmount).toFixed(2)-0.00)) {
                this.$message({ message: '费用项总金额不能大于发票总金额！', type: 'warning' })
                return false
              }
              const requestData = {
                claimNo: this.claimNo,
                invoiceNo: this.billNo,
                costDetail: this.invoiceFormDetail
              }
              costDetailSave(requestData).then(response => {
                if (response.status === '1') {
                  this.$message({ message: '费用项信息保存成功！', type: 'success' })
                  this.getCostDetails()
                } else {
                  this.$message({ message: '费用项信息保存失败！', type: 'error' })
                }
              })
            } else {
              this.$message({ message: '请先保存发票信息！', type: 'warning' })
              return false
            }
          }else {
            return  false
          }
        })
      },
      remoteHospitals(query) {
        if (query !== '') {
          this.loading = true
          const requestData = {
            hospitalName: query,
            oldHospitals: []
          }
          getHospitalLike(requestData).then(response => {
            this.hospitals = response.data
            this.loading = false
          }).catch(error => {
            this.loading = false
            this.$message.error('查询医院列表异常')
          })
        }
      },
      getAllImages(mode) {
        const requestData = {
          claimNo: this.claimNo,
          type: '',
          recievedMode: mode
        }
        getImages(requestData).then(response => {
          this.imageInfos = response.data
          this.dealImages(this.imageInfos)
        }).catch(error => {
          this.$message.error('查询影像文件异常')
        })
      },
      getBillImages() {
        const requestData = {
          claimNo: this.claimNo,
          type: '4'
        }
        getImages(requestData).then(response => {
          this.imageInfos = response.data
          this.dealImages(this.imageInfos)
        }).catch(error => {
          this.$message.error('查询影像文件异常')
        })
      },
      // 获取未分类数量
      getUnclassifiedNum() {
        let num = 0
        this.urls.forEach(item => {
          if (item.isClassification !== 'Y') {
            num ++
          }
        })
        this.unclassifiednum = num
      },
      dealImages(data) {
        this.showList = []
        for (let i = 0; i < data.length; i++) {
          preViewThumbnail(data[i].src).then(response => {
            if (response.status === '1') {
              const url = 'data:image/jpeg;base64,' + response.data
              this.urls.push({ src: url, pageId: data[i].src, pageType: data[i].type, originals: data[i].originals, modifyTime: data[i].modifyTime, isClassification: data[i].isClassification })
              if (this.urls.length === this.imageInfos.length) {
                // 根据type排序
                this.sortImages()
                // 页面显示图片
                let index = this.urls.findIndex(item => item.isClassification !== 'Y')
                if (index !== -1) {
                  this.changeImage(this.urls[index].src,this.urls[index].pageType,this.urls[index].originals, index,this.urls[index].pageId) //src pageType
                  this.imgIdx = index + 1
                } else {
                  this.imgIdx = 0
                }
                this.getUnclassifiedNum()
              }
            } else {
              this.$message({ message: '图片加载失败！', type: 'error' })
            }
          })
        }
      },
      sortImages() {
        let temp
        // if (this.urls.every(item => item.pageType !== '9')) {
        //   // 全部分类完毕后显示最后一张保存的
        //   for(let i=0;i<this.urls.length-1;i++){
        //     for(let j=i+1;j<this.urls.length;j++){
        //       if(Date.parse(new Date(this.urls[i].modifyTime)) < Date.parse(new Date(this.urls[j].modifyTime))){
        //         temp=this.urls[j]
        //         this.urls[j]=this.urls[i]
        //         this.urls[i]=temp
        //       }
        //     }
        //   }
        //   this.changeImage(this.urls[0].src,this.urls[0].pageType,this.urls[0].originals, 0,this.urls[0].pageId) //src pageType
        //   return
        // }
        // 根据pageid排序
        for(let i=0;i<this.urls.length-1;i++){
          for(let j=i+1;j<this.urls.length;j++){
            if(this.urls[i].pageId > this.urls[j].pageId){
              temp=this.urls[j]
              this.urls[j]=this.urls[i]
              this.urls[i]=temp
            }
          }
        }
        this.getUnclassifiedNum()
        // // 复印件排序
        // for(let i=0;i<this.urls.length-1;i++){
        //   for(let j=i+1;j<this.urls.length;j++){
        //     if (this.urls[i].pageType === '9' &&  this.urls[j].pageType ==='9') {
        //       if ( this.urls[i].originals && !this.urls[j].originals) {
        //         temp=this.urls[j]
        //         this.urls[j]=this.urls[i]
        //         this.urls[i]=temp
        //       }
        //     }
        //   }
        // }
      },
      saveTypeHandle() {
        this.imageTypeSave()
      },
      handleChange(data) {
        let obj = {};
        obj = this.hospitals.find((item)=>{//这里的userList就是上面遍历的数据源
          return item.value === data;//筛选出匹配数据
        });
        this.invoiceForm.hospitalName = obj.label
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
        if(this.multipleSelection.length === 1) {
          this.invoiceFormDetail.detailNo = this.multipleSelection[0].detailedno
          this.invoiceFormDetail.feetIem = this.multipleSelection[0].feeitemcode
          this.invoiceFormDetail.amount = this.multipleSelection[0].amount
          this.invoiceFormDetail.overallPayment = this.multipleSelection[0].overallpayment
          this.invoiceFormDetail.thirdPartyPayment = this.multipleSelection[0].thirdpartypayment
          this.invoiceFormDetail.payByCategory = this.multipleSelection[0].paybycategory
          this.invoiceFormDetail.selfPayment = this.multipleSelection[0].selfpayment
          this.invoiceFormDetail.unReasonableAmount = this.multipleSelection[0].unreasonableamount
          this.invoiceFormDetail.unReasonableExplain = this.multipleSelection[0].unreasonableexplain
          this.editAmount = this.multipleSelection[0].amount
          if(this.multipleSelection[0].unreasonableamount === null || this.multipleSelection[0].unreasonableamount === 0) {
            this.invoiceFormDetailRules.unReasonableExplain = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
          }else{
            this.invoiceFormDetailRules.unReasonableExplain = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
          }
        }
        if(this.multipleSelection.length > 1) {
          this.invoiceFormDetail.detailNo = this.multipleSelection[this.multipleSelection.length - 1].detailedno
          this.invoiceFormDetail.feetIem = this.multipleSelection[this.multipleSelection.length - 1].feeitemcode
          this.invoiceFormDetail.amount = this.multipleSelection[this.multipleSelection.length - 1].amount
          this.invoiceFormDetail.overallPayment = this.multipleSelection[this.multipleSelection.length - 1].overallpayment
          this.invoiceFormDetail.thirdPartyPayment = this.multipleSelection[this.multipleSelection.length - 1].thirdpartypayment
          this.invoiceFormDetail.payByCategory = this.multipleSelection[this.multipleSelection.length - 1].paybycategory
          this.invoiceFormDetail.selfPayment = this.multipleSelection[this.multipleSelection.length - 1].selfpayment
          this.invoiceFormDetail.unReasonableAmount = this.multipleSelection[this.multipleSelection.length - 1].unreasonableamount
          this.invoiceFormDetail.unReasonableExplain = this.multipleSelection[this.multipleSelection.length - 1].unreasonableexplain
          this.editAmount = this.multipleSelection[this.multipleSelection.length - 1].amount
          if(this.multipleSelection[this.multipleSelection.length - 1].unreasonableamount === null || this.multipleSelection[this.multipleSelection.length - 1].unreasonableamount === 0) {
            this.invoiceFormDetailRules.unReasonableExplain = { required: false, message: '不能为空!', trigger: ['blur', 'change'] }
          }else{
            this.invoiceFormDetailRules.unReasonableExplain = { required: true, message: '不能为空!', trigger: ['blur', 'change'] }
          }
        }
        if(this.multipleSelection.length === 0) {
          this.resetDetailForm()
          this.editAmount = 0.00
        }
      },
      deleteDetail() {
        if (this.multipleSelection.length === 0) {
          this.$message.warning('请至少选择一条明细信息！')
          return
        }
        const details = []
        this.multipleSelection.forEach(item => details.push(item.detailedno))
        deleteDetails(details).then(response => {
          if (response.status === '1') {
            this.$message({ message: '费用明细删除成功！', type: 'success' })
            this.getCostDetails()
          } else {
            this.$message({ message: '费用明细删除失败！', type: 'error' })
          }
        })
      },
      getFeeitem() {
        getFeeitems().then(response => {
          if (response.status === '1') {
            this.feeitems = response.data
          } else {
            this.$message({ message: '费用项信息初始化失败！', type: 'error' })
          }
        })
      },
      getInfo(data) {
        getInfo(data).then(response => {
          if (response.status === '1') {
            this.insuredName = response.data.name
            this.idNo = response.data.idcardno
            // if(this.invoiceForm.patient === '' || this.invoiceForm.patient === null) {
            //   this.invoiceForm.patient = response.data.name
            // }
          } else {
            this.$message({ message: '信息初始化失败！', type: 'error' })
          }
        })
      }
    }
  }
</script>
<style scoped>
  .block {
    position: relative;
    width: 70px;
    text-align: center;
  }
  .icon-badge {
    position: absolute;
    right: 6px;
    top: -4px;
    z-index: 99;
    color: #67C23A;
  }
  .handle-box{
    display: flex;
    margin: 10px;
    height: 100px;
    overflow-x: scroll;
  }
  .type_box {
    height: 355px;
    border-bottom: 1px solid #e6ebf5;
    /*overflow: scroll;*/
  }
  .type_item {
    color: #333;
    font-size: 14px;
    cursor: pointer;
  }
  .item_width {
    width: 240px;
  }
  .el-tabs ::v-deep .el-tabs__active-bar {
    width: 100px;
  }
  .el-tabs ::v-deep .el-tabs__item {
    width: 120px;
  }
  .el-form ::v-deep .el-form-item__label {
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
  }
  .inner-header ::v-deep.el-card__header {
    padding: 10px 20px;
  }
  .show1 {
    opacity: 0.5;
  }
  .show2 {
    opacity: 1;
  }
</style>
