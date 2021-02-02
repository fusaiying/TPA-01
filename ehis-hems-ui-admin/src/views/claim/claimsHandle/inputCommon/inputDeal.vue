<template>
  <div class="app-container" style="margin-bottom: 20px;">
    <el-card style="margin-bottom: 10px">
      <el-row>
        <span style="color:#303133;font-size:12px">赔案号：{{ claimNo }}</span>
        <span style="color:#303133;font-size:12px; padding: 0 20px;">被保人姓名：{{ insuredName }}</span>
        <span style="color:#303133;font-size:12px">被保人身份证号：{{ idNo }}</span>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header" class="clearfix">
        <span>人员清单信息</span>
        <span style="display: inline-block;float: right">
          <el-button v-if="!incidentNo && !editInvoice" type="primary" size="mini" :disabled="status === 'show'" @click="returnToComplete">退回</el-button>
          <el-button v-if="!incidentNo && !editInvoice" type="primary" size="mini" :disabled="status === 'show'" @click="entryToComplete">录入完毕</el-button>
          <el-button v-if="incidentNo || editInvoice" type="primary" size="mini" @click="closeHandle">关闭</el-button>
        </span>
      </div>
      <span style="margin-right: 20px">材料类型</span>
      <!--      <el-select v-model="materialTypeRadio" placeholder="请选择" size="mini" @change="materialTypeChange">-->
      <!--        <el-option-->
      <!--          v-for="item in materialType"-->
      <!--          :key="item.value"-->
      <!--          :label="item.label"-->
      <!--          :value="item.value">-->
      <!--        </el-option>-->
      <!--      </el-select>-->
      <el-radio-group v-model="materialTypeRadio" size="mini" @change="materialTypeChange">
        <el-radio-button v-for="item in materialType" :label="item.name" :key="item.name">{{item.label+(item.total?'('+item.total+')':'')}}</el-radio-button>
      </el-radio-group>
      <!--      <span style="display: inline-block;float: right">-->
      <!--        <el-button type="primary" size="mini" :disabled="status === 'show'" @click="saveHandle">保存</el-button>-->
      <!--      </span>-->
    </el-card>
    <el-card>
      <el-row>
        <el-col :span="13">
          <el-card class="img-card inner-header" shadow="never" style="margin-right: 6px;position: relative">
            <div v-if="isInput === 'Y'" style="position: absolute;top: 10px;left: 10px;width: 70px;height: 40px">已录入</div>
            <div id="wrap">
              <div id="box" :style="imgStyle" class="box" @mousedown="mousedownHandle($event)">
                <img id="bigImage" v-if="urls.length > 0 && src !== ''" :src="src" :style="imageSize">
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
          <div class="handle-box">
            <div v-for="(item, i) in urls" :key="i" class="block">
              <el-image
                :src="item.src"
                style="width: 70px; height: 50px; padding-right: 6px;cursor: pointer;position: relative"
                fit="fill"
                @click="changeImage(item.src,item.pageType,item.originals, i,item.pageId, item.isInput, true)"/>
              <span v-if="item.isInput === 'Y'" class="inputFlag" @click="changeImage(item.src,item.pageType,item.originals, i,item.pageId, item.isInput, true)">已录入</span>
              <p :class="{'active': imgIdx === i +1}" style="width: 64px; text-align: center; margin-top: 5px;">
                {{ i+1 }}
              </p>
            </div>
          </div>
        </el-col>
        <el-col :span="11">
          <el-card shadow="never" style="padding-bottom: 15px;">
            <el-tabs v-model="activeName" style="position: relative" :before-leave="activeChange">
              <el-tab-pane v-if="!incidentNo && !editInvoice" label="基本信息" name="first" style="height: 470px;overflow-y: auto;">
                <div style="height: 40px">
                  <el-checkbox-group v-model="imageState" style="float: left;margin-bottom: 10px;margin-left: 20px;width: 300px" :max="1" @change="imageStatusChange">
                    <el-checkbox v-for="item in imageStateList" :label="item.value" :key="item.value">{{item.label}}</el-checkbox>
                  </el-checkbox-group>
                  <el-button style="float: right;margin-right: 20px" type="primary" size="mini" :disabled="status === 'show'" @click="saveHandle">保存</el-button>
                </div>
                <el-tabs v-model="baseActive">
                  <el-tab-pane label="被保人信息" name="first">
                    <insured-modal v-if="isFlag" :dictList="dictList" ref="newinsured" :showStyle="showStyle" :informationOfTheInsured="informationOfTheInsured" :imageState="imageState" :status="status" :claimNo="this.claimNo" :disabled="disabled" @findInsuredInfoByClaimno="findInsuredInfoByClaimno"/>
                  </el-tab-pane>
                </el-tabs>
                <el-tabs v-model="baseActive">
                  <el-tab-pane label="申请人信息" name="first">
                    <applicant-modal v-if="isFlag" :dictList="dictList"  :showStyle="showStyle" :claimNo="claimNo" :status="status" />
                  </el-tab-pane>
                </el-tabs>
              </el-tab-pane>
              <el-tab-pane v-if="!incidentNo && !editInvoice" label="领款人信息" name="second">
                <el-checkbox-group v-model="newimageState" style="float: left;margin-bottom: 10px;margin-left: 20px;width: 300px" :max="1" @change="imageStatusChange">
                  <el-checkbox v-for="item in imageStateList" :label="item.value" :key="item.value">{{item.label}}</el-checkbox>
                </el-checkbox-group>
                <payeeInfo-modal v-if="isFlag" :dictList="dictList" ref="payeeInfo" :newimageState="newimageState" :status="status" :disabledFlag="newimageState[0] === '03'" :informationOfTheInsured="informationOfTheInsured" :claimno="claimNo"/>
                <el-button :disabled="status === 'show'" type="primary" style="margin-top: 10px" size="mini" @click="addPayeeinfo">重置</el-button>
                <el-button type="primary" size="mini" style="margin-top: 10px" :disabled="status === 'show'" @click="saveHandle">保存</el-button>
              </el-tab-pane>
              <el-tab-pane label="发票信息" name="third" style="">
                <el-tabs v-model="invoiceName" :before-leave="beforeLeave" @tab-click="tabClick">
                  <el-tab-pane  v-if="!tabShow" label="发票" name="first" style="">
                    <invoice-modal :dictList="dictList"  :disabled="disabled"  ref="invoice" :insuredName="insuredName" :editInvoice="editInvoice" :urls="urls" :isInput="isInput"
                                   :status="status" :showStyle="showStyle" :pageId="pageId" :claimNo="claimNo" :incidentNo="incidentNo" :invoiceNo="invoiceNo" @saveInvoiceHandle="saveInvoiceHandle"
                                   @getBillImages="getBillImages" @changeFlag="changeFlag" @delFlag="delFlag" @getInvoiceNo="getInvoiceNo" @changeImageStatus="changeImageStatus" @changeImage="changeImage"
                                   @getOldInvoice="getOldInvoice"/>
                  </el-tab-pane>
                  <el-tab-pane v-if="!tabShow" label="费用项" name="second" style="height: 470px;">
                    <costitem-modal  :dictList="dictList" ref="costitem" :showStyle="showStyle" :pageId="pageId" :editInvoice="editInvoice" :incidentNo="incidentNo" :status="status" :invoiceForm="invoiceForm" :claimNo="claimNo"/>
                  </el-tab-pane>
                  <el-tab-pane v-else-if="tabShow" label="结算单信息" name="third" style="height: 470px;">
                    <statements-modal  :dictList="dictList" :showStyle="showStyle" :insuredName="insuredName" :urls="urls" :status="status" :pageId="pageId" ref="newstate" :claimNo="claimNo"
                                      :isInput="isInput" :editInvoice="editInvoice" :incidentNo="incidentNo"
                                      @getBillImages="getBillImages" @changeImageStatus="changeImageStatus" @changeFlag="changeFlag" @delFlag="delFlag" @changeImage="changeImage"
                                      @getOldInvoice="getOldInvoice"/>
                  </el-tab-pane>
                </el-tabs>
              </el-tab-pane>
              <!--              <el-checkbox v-show="activeName === 'third' && !editInvoice && !incidentNo" v-model="statements" label="statements" style="position: absolute;top: 5px;right: 60px" @click.native="showConfirm">结算单</el-checkbox>-->
              <el-checkbox v-show="activeName === 'third' && !editInvoice && !incidentNo" v-model="statements" :disabled="!!incidentNo || !!editInvoice || status === 'show'" label="statements" style="position: absolute;top: 5px;right: 60px">结算单</el-checkbox>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </el-card >
    <timeline-modal :dictList="dictList"  v-if="!incidentNo && !editInvoice" :status="status" :claimno="claimNo" />
    <return-modul :dictList="dictList" v-model="returnVis" :status="status" @confirmHandle="returnConfirm"/>
    <el-dialog
      title="提示"
      :visible.sync="saveconsdialogVisible"
      width="270px"
      :append-to-body="true"
      :close-on-click-modal="false"
    >
      <span>结案权限不足，案件将继续流转！</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="saveConclusionCheck" type="primary" size="mini">确认</el-button>
        <el-button type="primary" size="mini" @click="saveconsdialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="扫描提交"
      :visible.sync="scanVisable"
      width="500px"
    >
      <el-form ref="scanFrom" :model="scanInfo" :rules="scanRules" label-width="150px" label-position="right" size="mini">
        <el-form-item label="退回分类/类型：" prop="errortype">
          <span class="form-item-span">{{selectDictLabel(scan_scanOptions, errortypecontent)}}</span>
        </el-form-item>
        <el-form-item label="退回描述：" prop="errortype">
          <span class="form-item-span">{{this.errortypecontentslist}}</span>
        </el-form-item>
        <el-form-item label="是否认同回退意见：" prop="errortype">
          <el-radio-group v-model="radio" @change="changeList">
            <el-radio :label="3">是</el-radio>
            <el-radio :label="6">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="不认同原因：" prop="disagreereason" :rules="scanRules.disagreereason"  v-if="showNoReason">
          <el-input
            type="textarea"
            style="width:300px"
            :autosize="{ minRows: 3, maxRows: 6}"
            placeholder="请输入内容"
            v-model="scanInfo.disagreereason">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="scanVisable = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="confirmSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import dragAndScale from '../common/mixins/dragAndScale'
  import {encrypt, decrypt} from "@/utils/rsaEncrypt"
  import { get } from '@/api/dictDetail'
  import { getInfo, judgeBnf } from '@/api/claim/register'
  import { getImages, findInsuredInfoByClaimno, inputCheck, billIsExist } from '@/api/claim/input'
  import { preView } from '@/api/claim/fileDeal'
  import { _debounce } from '@/utils/commenMethods.js'
  import { preViewThumbnail, processCheck, saveConclusion, getCaseLastNodeInfo, isReturn, returnOpinion, checkCaseDeal,getFileCount } from "@/api/claim/handleDeal";

  import returnModul from './module/return'
  import insuredModal from './components/insured'
  import applicantModal from './components/applicant'
  import payeeInfoModal from './components/payeeInfo'
  import invoiceModal from './components/invoice'
  import costitemModal from './components/costitem'
  import statementsModal from './components/statements'
  import timelineModal from './components/timeLine'

  let dictss=[{dictType:'material_type'},{dictType:'casestep'},{dictType:'scan_scan'},

    {dictType:'acceptreason'},{dictType:'inputreturn_node'},{dictType:'return_reason'},{dictType:'sex'},
    {dictType:'card_type'},{dictType:'relation_ship_apply'},{dictType:'collectedmode'},{dictType:'nativeplace'},
    {dictType:'payee_occupation'},{dictType:'Invoice_face_type'},{dictType:'invoicetype'},{dictType:'Invoice_type'},
    {dictType:'chapters_complete'},{dictType:'Invoice_bill_type'},{dictType:'health_care_type'},{dictType:'socialsecuritysettlement'},
    {dictType:'detail_category'},{dictType:'socialsecurity_type'},
    {dictType:'statement_invoice_type'},{dictType:'statement_type'},{dictType:'tasknode'},
    {dictType:'examine_conclusion'}]
  export default {
    mixins: [dragAndScale],
    //dicts: ['material_type', 'casestep', 'scan_scan'],
    components: {
      insuredModal,
      applicantModal,
      payeeInfoModal,
      invoiceModal,
      costitemModal,
      statementsModal,
      timelineModal,
      returnModul
    },
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
    watch:{
      statements: function(val) {
        /*
        * 根据结算单按钮手动切换发票、结算单，切换前根据是否有localstorage来判断是否触发暂存提示，触发的情况分两种
        * 1.已录入影像的暂存
        * 2.未录入影像的暂存
        * 以上判断之后，再通过invoiceName切换tab标签
        * oldFormData:进入或修改发票、结算单是需要赋值，用来比较是否触发暂存
        * */
        let currentData = ''
        if (val) {
          currentData = JSON.stringify(this.$refs.invoice.invoiceForm)
        } else {
          currentData = JSON.stringify(this.$refs.newstate.statementsForm)
        }
        if (this.oldFormData!==''&&this.oldFormData!==currentData&&this.status!=='show') {
          this.$confirm(this.invoiceName!=='third'?'是否暂存当前发票信息':'是否暂存当前结算单信息', '提示', {
            confirmButtonText: '暂存',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            if (this.statements) {
              this.$refs.invoice.saveInvoiceHandle()
            } else if (this.invoiceName === 'third') {
              this.$refs.newstate.saveAccountHandle()
            }
          }).catch(() => {
            this.tabShow = this.statements
            if (this.statements) {
              this.invoiceName = 'third'
            } else {
              this.invoiceName = 'first'
            }
          }).finally(() =>{
            this.tabShow = this.statements
            if (val) {
              this.$nextTick(() => {
                this.$refs.newstate.getInputStatus()
                this.invoiceName = 'third'
                this.$nextTick(function() {
                  if (this.isInput === 'Y') {
                    let data = JSON.parse(decodeURI(localStorage.getItem('statementsForm')));
                    this.$nextTick(() => {
                      if (data !== null&&data.pageId===this.pageId&&this.invoiceName==='third') {
                        this.$refs.newstate.statementsForm = data
                        this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
                      } else {
                        this.$refs.newstate.selectStatementBills()
                      }
                    })
                  } else {
                    if (this.status === 'show') {
                      this.$refs.newstate.resetForm()
                    }
                    let data = JSON.parse(decodeURI(localStorage.getItem('statementsForm')));
                    if (data !== null) {
                      this.$refs.newstate.statementsForm = data
                      this.$refs.newstate.hospitals = [data.hospital]
                      this.$set(this.$refs.newstate.statementsForm, 'hospital', data.hospital)
                    }
                  }
                })
              })
            } else {
              this.invoiceName = 'first'
              this.$nextTick(() => {
                this.$refs.invoice.getInputStatus()
                if (this.isInput !== 'Y') {
                  let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
                  if (data !== null) {
                    this.$refs.invoice.invoiceForm = data
                    this.$refs.invoice.hospitals = data.hosList
                    this.$refs.invoice.ICDArr = data.firstList
                    this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
                    this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
                  }
                } else {
                  let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
                  if (data !== null&&data.pageId===this.pageId&&val==='first') {
                    this.$refs.invoice.invoiceForm = data
                    this.$refs.invoice.hospitals = data.hosList
                    this.$refs.invoice.ICDArr = data.firstList
                    this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
                    this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
                    this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
                  } else {
                    this.$refs.invoice.inputgetBillInfo()
                  }
                }
              })
            }
          })
        } else {
          this.tabShow = val
          if (this.tabShow) {
            this.invoiceName = 'third'
            this.$nextTick(() => {
              this.$refs.newstate.getInputStatus()
              if (this.isInput === 'Y') {
                let data = JSON.parse(decodeURI(localStorage.getItem('statementsForm')));
                if (data !== null&&data.pageId===this.pageId&&this.invoiceName==='third') {
                  this.$refs.newstate.statementsForm = data
                  this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
                } else {
                  this.$refs.newstate.selectStatementBills()
                }
              } else {
                if (this.status === 'show') {
                  this.$refs.newstate.resetForm()
                }
                let data = JSON.parse(decodeURI(localStorage.getItem('statementsForm')));
                if (data !== null) {
                  this.$refs.newstate.statementsForm = data
                  this.$refs.newstate.hospitals = [data.hospital]
                  this.$set(this.$refs.newstate.statementsForm, 'hospital', data.hospital)
                }
              }
            })
          } else {
            this.invoiceName = 'first'
            this.$nextTick(() => {
              this.$refs.invoice.getInputStatus()
              if (this.isInput !== 'Y') {
                let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
                if (data !== null) {
                  this.$refs.invoice.invoiceForm = data
                  this.$refs.invoice.hospitals = data.hosList
                  this.$refs.invoice.ICDArr = data.firstList
                  this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
                  this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
                }
              } else {
                let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
                if (data !== null&&data.pageId===this.pageId&&this.invoiceName==='first') {
                  this.$refs.invoice.invoiceForm = data
                  this.$refs.invoice.hospitals = data.hosList
                  this.$refs.invoice.ICDArr = data.firstList
                  this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
                  this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
                  this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
                } else {
                  this.$refs.invoice.inputgetBillInfo()
                }
              }
            })
          }
        }
      },
      invoiceName: function (val) {
        this.$nextTick(() => {
          if (this.invoiceName === 'first') {
            this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
          } else if (this.invoiceName === 'third') {
            this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
          }
        })
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
        dictList: [],
        query: {},
        node: '',
        claimNo: '',
        insuredName: '',
        idNo: '',
        baseActive: 'first',
        materialTypeRadio: '',
        materialType: [],
        errortypecontentslist: '',
        isshowError: false,
        disabled: false,
        scanVisable: false,
        showNoReason: false,
        scanInfo: {
          disagreereason: ''
        },
        scanRules: {
          disagreereason: [
            { trigger: ['blur', 'change'], required: true, message: '原因必填' },
          ]
        },
        imageState: [],
        newimageState: [],
        imageStateList: [
          {value: '01', label: '复印件'},
          {value: '02', label: '影像模糊'},
          {value: '03', label: '无影像'}
        ],
        payeeInfoForm: {},
        invoiceName: 'first',
        invoiceForm: {},
        statements: false,
        recoverNodeFlag: false,
        showFlag: false,
        urls: [],
        activeName: 'first',
        src: '',
        imageSize: {},
        imageInfos: [],
        incidentNo: '', // 发票补录传事件号
        invoiceNo: '',
        returnVis: false,
        pageId: 0,
        isInput: '',
        informationOfTheInsured: {},
        resultForm: {},
        status: '',
        invoiceInfo: {},
        caseLastNodeInfo: {},
        returnFlag: false,
        saveconsdialogVisible: false, //确认权限流转的问题
        editInvoice: 0, // 发票修改传的pageid
        showId: 0,
        errortypecontent: '',
        opinionno: '',
        whetheridentify: '',
        radio: '',
        rejectreason: '',
        errortype: '',
        saveInvoiceForm: {},
        showStyle: true,
        oldFormData: '',
        tabShow: false,
        isFlag:false,
       // 'material_type', 'casestep', 'scan_scan'
        material_typeOptions: [],
        casestepOptions: [],
        scan_scanOptions: []
      }
    },
    created() {
      this.query = JSON.parse(decodeURI(this.$route.query.data))
      localStorage.removeItem('invoiceForm');
      localStorage.removeItem('statementsForm');
      this.node = this.query.node
      this.claimNo = decrypt(this.query.claimNo)
      this.insuredName = this.query.insuredName
      this.status = this.query.status
      this.incidentNo = this.query.incidentno
      this.invoiceNo = this.query.invoiceno
      this.editInvoice = this.query.pageid
      if (this.incidentNo || this.editInvoice) {
        this.activeName = 'third'
      }
      this.checkCaseDeal()
      this.getInfo(this.claimNo)
      // this.getMaterialType()
      // if (!this.incidentNo && !this.invoiceNo) {
      // this.getBillImages(this.materialTypeRadio)
      // }
      this.getCaseLastNodeInfo()
      this.isReturn()
      this.getFileCount()
    },
    update(){
      this.findInsuredInfoByClaimno()
    },
   async mounted() {
     await this.getDictsList(dictss).then(response =>{
       this.dictList=response.data
     })
     this.material_typeOptions=this.dictList.find(item=>{
       return item.dictType=='material_type'
     }).dictDate
     this.casestepOptions=this.dictList.find(item=>{
       return item.dictType=='casestep'
     }).dictDate
     this.scan_scanOptions=this.dictList.find(item=>{
       return item.dictType=='scan_scan'
     }).dictDate
      const wrap = document.getElementById('wrap')
      const box = document.getElementById('box')
      box.style.left = (wrap.offsetWidth - box.offsetWidth) / 2 + 'px'
      box.style.top = 50 + 'px'
      this.initLeft = box.style.left
      this.initTop = box.style.top
      this.searchFormWidth(); // 组件初始化的时候不会触发onresize事件，这里强制执行一次
      window.onresize = () => {
        if(!this.timer){ // 使用节流机制，降低函数被触发的频率
          this.timer = true;
          let that = this; // 匿名函数的执行环境具有全局性，为防止this丢失这里用that变量保存一下
          setTimeout(function(){
            that.searchFormWidth();
            that.timer = false;
          },400)
        }
      }
      this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
      this.handleScroll()
     this.isFlag=true
   },
    destroyed() {
      const wrap = document.getElementById('wrap')
      wrap.onmouseover = function() {
        wrap.onmousewheel = null
      }
    },
    methods: {
      handleScroll(e){
        const wrap = document.getElementById('wrap')
        let that = this
        wrap.onmouseover = function() {
          wrap.onmousewheel = function(e) {
            e.preventDefault()
            let direction = e.deltaY > 0 ? 'down' : 'up'
            if (direction == 'down') {
              that.handleActions('zoomOut')
            } else {
              that.handleActions('zoomIn')
            }
          }
        }
      },
      searchFormWidth() {
        let w = window.innerWidth;
        if(w < 1366) {
          this.showStyle = false
        } else {
          this.showStyle = true
        }
      },
      // 获取材料类型
      getMaterialType() {
        get('material_type').then(res => {
          this.materialType = [{value: '', label: '全部'}]
          this.materialType = this.materialType.concat(res.content)
          if (this.incidentNo || this.editInvoice) {
            // this.materialType = this.materialType.filter(item => item.value === '4')
            this.materialTypeRadio = '4'
          } else {
            this.materialTypeRadio = '1'
          }
          this.getBillImages()
        })
      },
      getCaseLastNodeInfo(){
        let params = {
          claimno: this.claimNo,
          node: 'claim_input'
        }
        getCaseLastNodeInfo(params).then(res => {
          if (res.status === '1') {
            this.caseLastNodeInfo = res.data
          } else {
            this.$message({ message: '请求出错！', type: 'error' })
          }
        })
      },
      // 证件号
      getInfo(data) {
        getInfo(data).then(response => {
          if (response.status === '1') {
            this.insuredName = response.data.name
            this.idNo = response.data.idcardno
          } else {
            this.$message({ message: '信息初始化失败！', type: 'error' })
          }
        })
      },
      imageStatusChange(val) {
        if (val[0] === '03') {
          this.disabled = true
        } else {
          this.disabled = false
        }
      },
      isReturn() {
        let params = {}
        params.claimno = this.claimNo
        params.tasknode = 'C003'
        isReturn(params).then(res =>{
          // conclusion 01 不是退回案件 02 退回案件
          if (res!=null && res.status =='1') {
            if (res.data.conclusion == '01') {
              this.isshowError = false
            } else {
              this.isshowError = true
              this.errortypecontent = res.data.errortype + res.data.rejectreason
              this.errortypecontentslist = res.data.explanation
              this.errortype = res.data.errortype
              this.rejectreason = res.data.rejectreason
              this.radio = res.data.whetheridentify == '01' ? 3 : 6
              this.whetheridentify = res.data.whetheridentify
              this.opinionno = res.data.opinionno
              if (this.radio == '3') {
                this.showNoReason = false
              } else {
                this.showNoReason = true
              }
              this.scanInfo.disagreereason = res.data.disagreereason
            }
          } else {
            this.$message.error('查询是否是退回案件错误！')
          }
        })
      },
      // 确认提交
      confirmSubmit() {
        this.$refs['scanFrom'].validate(valid => {
          if (valid) {
            let params = {}
            params.claimno = this.claimNo
            params.whetheridentify  = this.whetheridentify
            params.disagreereason = this.scanInfo.disagreereason
            params.tasknode = 'C003'
            params.opinionno  = this.opinionno? this.opinionno :''
            returnOpinion(params).then(res =>{
              if (res.status == '1') {
                this.opinionno = res.data.opinionno
                this.scanInfo.disagreereason = res.data.disagreereason
                this.radio = res.data.whetheridentify == '01' ? 3 : 6
                if (this.radio == '3') {
                  this.showNoReason = false
                } else {
                  this.showNoReason = true
                }
                this.scanVisable = false
                this.$message.success('提交成功！')
              } else {
                this.$message.error('更新数据出错！')
                this.scanVisable = false
              }
            })
          } else {
            return false
          }
        })
      },
      confirmInput() {},
      changeList(value) {
        if (value == '3') {
          this.showNoReason = false
          this.whetheridentify = '01'
        } else {
          this.showNoReason = true
          this.whetheridentify = '02'
        }
      },
      materialTypeChange(val) {
        this.src = ''
        // this.pageId = 0
        // this.isInput = ''
        this.imgIdx = 0
        this.getBillImages()
      },
      // 影像
      getBillImages(init=false) {
        this.urls = []
        let type = this.materialTypeRadio === '00' ? '' : this.materialTypeRadio
        const requestData = {
          claimNo: this.claimNo,
          type: type
        }
        getImages(requestData).then(response => {
          this.imageInfos = response.data
          let flag = this.imageInfos.some(item => item.isInput !== 'Y' && item.type === '4')
          if (this.incidentNo&&init&&!flag&&this.materialTypeRadio==='4') {
            this.$alert('没有未录入的医疗账单影像！', '温馨提示', {
              confirmButtonText: '确定',
            });
          }
          this.dealImages(this.imageInfos)
        }).catch(error => {
          this.$message.error('查询影像文件异常')
        })
      },
      getBillImages1() {
        this.urls = []
        const requestData = {
          claimNo: this.claimNo,
          type: '6'
        }
        getImages(requestData).then(response => {
          this.imageInfos = this.imageInfos.concat(response.data)
          this.dealImages(this.imageInfos)
        }).catch(error => {
          this.$message.error('查询影像文件异常')
        })
      },
      changeImageStatus(id) {
        this.showId = id
      },
      dealImages(data) {
        this.showList = []
        this.urls = []
        for (let i = 0; i < data.length; i++) {
          preViewThumbnail(data[i].src).then(response => {
            if (response!=null && response.status === '1') {
              const url = 'data:image/jpeg;base64,' + response.data
              this.urls.push({
                src: url,
                pageId: data[i].src,
                pageType: data[i].type,
                originals: data[i].originals,
                modifyTime: data[i].modifyTime,
                isClassification: data[i].isClassification,
                isInput: data[i].isInput
              })
              if (this.urls.length === this.imageInfos.length) {
                if (this.materialTypeRadio === '4'&&this.invoiceName==='first') {
                  this.$refs.invoice.getInputStatus()
                }
                this.sortImages()
                let index = 0
                // 发票修改切换至对应影像
                if (this.materialTypeRadio === '4'&&this.editInvoice) {
                  index = this.urls.findIndex(item => item.pageId === this.editInvoice)
                }
                this.changeImage(this.urls[index].src,this.urls[index].pageType,this.urls[index].originals, index,this.urls[index].pageId, this.urls[index].isInput, true)
              }
            } else {
              this.$message({ message: '图片加载失败！', type: 'error' })
            }
          })
        }
      },
      // 前端处理锁定后缩略图已录入状态
      changeFlag() {
        this.urls.forEach(item => {
          if (item.pageId === this.pageId) {
            item.isInput = 'Y'
            this.isInput = 'Y'
          }
        })
      },
      delFlag(id) {
        this.urls.forEach(item => {
          if (item.pageId === id) {
            item.isInput = ''
          }
        })
      },
      // 获取暂存前对比数据
      getOldInvoice(data) {
        this.oldFormData = JSON.stringify(data)
      },
      changeImage: _debounce(function(url,type,flag, i, id, isInput, showMassage=false, reset=false) {
        let currentInvoice = ''
        let currentStatement = ''
        let tabFlag = ''
        // 获取切换前的数据以及当前tab页
        if (this.invoiceName === 'first') {
          currentInvoice = JSON.stringify(this.$refs.invoice.invoiceForm)
        } else if (this.invoiceName === 'third') {
          currentStatement = JSON.stringify(this.$refs.newstate.statementsForm)
        }
        tabFlag = this.invoiceName
        let equal4 = false // 发票
        let equal6 = false // 结算单
        /*
        * 根据页面当前tab页位置、切换影像的类型、已录入状态获取比对的数据，并与页面最新的数据进行比对。
        * */
        if ((tabFlag==='first'&&type==='4')||(tabFlag==='first'&&type==='6'&&isInput==='Y')) {
          equal4 = this.oldFormData!==currentInvoice
        } else if ((tabFlag==='third'&&type==='6')||(tabFlag==='third'&&type==='4'&&isInput==='Y')) {
          equal6 = this.oldFormData!==currentStatement
        }
        this.$nextTick(() => {
          if (equal4&&this.status!=='show'&&showMassage) {
            this.$confirm('是否暂存当前发票信息？', '提示', {
              confirmButtonText: '暂存',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$refs.invoice.saveInvoiceHandle()
            }).catch(() => {
              if (this.invoiceName === 'first') {
                this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
              } else if (this.invoiceName === 'third') {
                this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
              }
            }).finally(() => {
              this.changeImageDeal(url,type,flag, i, id, isInput, showMassage, reset)
            })
          } else if (equal6&&this.status!=='show'&&showMassage) {
            this.$confirm('是否暂存当前结算单信息？', '提示', {
              confirmButtonText: '暂存',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$refs.newstate.saveAccountHandle()
            }).catch(() => {
              if (this.invoiceName === 'first') {
                this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
              } else if (this.invoiceName === 'third') {
                this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
              }
            }).finally(() => {
              this.changeImageDeal(url,type,flag, i, id, isInput, showMassage, reset)
            })
          } else {
            this.changeImageDeal(url,type,flag, i, id, isInput, showMassage, reset)
          }
        })
      }, 300),
      changeImageDeal(url,type,flag, i, id, isInput, showMassage, reset) {
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
        preView(id).then(response => {
          this.src = 'data:image/jpeg;base64,' + response.data
          this.$nextTick(() =>{
            let image = document.getElementById('bigImage')
            let width = image.offsetWidth
            let height = image.offsetHeight
            image.onload = () =>{
              if (width/height >1) {
                this.imageSize = {
                  width: '100%',
                  height: 'auto'
                }
              } else {
                this.imageSize = {
                  width: 'auto',
                  height: '100%'
                }
              }
            }
          })
          if (this.showId) {
            preView(this.showId).then(response => {
              this.src = 'data:image/jpeg;base64,' + response.data
              this.$nextTick(() =>{
                let image = document.getElementById('bigImage')
                let width = image.offsetWidth
                let height = image.offsetHeight
                image.onload = () =>{
                  if (width >= height) {
                    this.imageSize = {
                      width: '100%',
                      height: 'auto'
                    }
                  } else {
                    this.imageSize = {
                      width: 'auto',
                      height: '100%'
                    }
                  }
                }
              })
              this.showId = 0
            })
          }
          this.imgIdx = i + 1
          this.pageId = id
          this.isInput = isInput
          this.selType = type
          let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
          if (type === '4') {
            if (!this.statements) {
              // 切换发票影像，且tab页在发票或结算单，切换至发票
              this.invoiceName = 'first'
              if (isInput === 'Y') {
                this.$nextTick(() => {
                  this.$refs.invoice.resetForm()
                  if (data !== null&&data.pageId===id) {
                    this.$refs.invoice.invoiceForm = data
                    this.$refs.invoice.hospitals = data.hosList
                    this.$refs.invoice.ICDArr = data.firstList
                    this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
                    this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
                    this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
                  } else {
                    this.$refs.invoice.inputgetBillInfo()
                  }
                })
              } else {
                if (this.status === 'show') {
                  this.$nextTick(() => {
                    this.$refs.invoice.resetForm()
                  })
                } else {
                  if (reset&&this.invoiceName==='first') {
                    this.$nextTick(() => {
                      this.$refs.invoice.resetForm()
                      this.getOldInvoice(this.$refs.invoice.invoiceForm)
                    })
                  }
                  let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
                  if (data !== null && data.pageId === null) {
                    this.$nextTick(() => {
                      this.$refs.invoice.invoiceForm = data
                      this.$refs.invoice.hospitals = data.hosList
                      this.$refs.invoice.ICDArr = data.firstList
                      this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
                      this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
                      this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
                    })
                  }
                  if (this.invoiceName === 'first') {
                    this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
                  } else if (this.invoiceName === 'third') {
                    this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
                  }
                }
              }
            } else {
              if (isInput==='Y') {
                // 由结算单切换至发票，后续处理由watch监听进行。
                this.statements = false
              }
            }
          } else if (type === '6') {
            if (this.statements) {
              let data = JSON.parse(decodeURI(localStorage.getItem('statementsForm')));
              if (isInput === 'Y') {
                this.invoiceName = 'third'
                this.$nextTick(() => {
                  this.$refs.newstate.resetForm()
                  if (data !== null&&data.pageId===id&&this.invoiceName==='third') {
                    this.$refs.newstate.statementsForm = data
                    this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
                  } else {
                    this.$refs.newstate.selectStatementBills()
                  }
                })
              } else {
                if (this.status === 'show') {
                  this.$refs.newstate.resetForm()
                } else {
                  if (reset&&this.invoiceName === 'third') {
                    this.$nextTick(() => {
                      this.$refs.newstate.resetForm()
                      this.getOldInvoice(this.$refs.newstate.statementsForm)
                    })
                  }
                  let data = JSON.parse(decodeURI(localStorage.getItem('statementsForm')));
                  if (this.invoiceName==='third'&&data !== null&&data.pageId===null) {
                    this.$nextTick(() => {
                      this.$refs.newstate.statementsForm = data
                      this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
                    })
                  }
                  if (this.invoiceName === 'first') {
                    this.oldFormData = JSON.stringify(this.$refs.invoice.invoiceForm)
                  } else if (this.invoiceName === 'third') {
                    this.oldFormData = JSON.stringify(this.$refs.newstate.statementsForm)
                  }
                }
              }
            } else {
              if (isInput==='Y') {
                this.statements = true
              }
            }
          }
        })
      },
      preViewImage: _debounce(function(type) {
        if (type === 'pre') {
          this.imgIdx--
          preView(this.urls[this.imgIdx - 1].pageId).then(response => {
            this.src = 'data:image/jpeg;base64,'+ response.data
            this.transform = {
              scale: 1,
              deg: 0,
              offsetX: 0,
              offsetY: 0,
              enableTransition: false
            }
            this.$nextTick(() =>{
              let image = document.getElementById('bigImage')
              let width = image.offsetWidth
              let height = image.offsetHeight
              image.onload = () =>{
                if (width >= height) {
                  this.imageSize = {
                    width: '100%',
                    height: 'auto'
                  }
                } else {
                  this.imageSize = {
                    width: 'auto',
                    height: '100%'
                  }
                }
              }
            })
            this.isInput = this.urls[this.imgIdx - 1].isInput
            this.pageId = this.urls[this.imgIdx - 1].pageId
          })
        } else {
          this.imgIdx++
          preView(this.urls[this.imgIdx - 1].pageId).then(response => {
            this.src = 'data:image/jpeg;base64,'+ response.data
            this.transform = {
              scale: 1,
              deg: 0,
              offsetX: 0,
              offsetY: 0,
              enableTransition: false
            }
            this.$nextTick(() =>{
              let image = document.getElementById('bigImage')
              let width = image.offsetWidth
              let height = image.offsetHeight
              image.onload = () =>{
                if (width >= height) {
                  this.imageSize = {
                    width: '100%',
                    height: 'auto'
                  }
                } else {
                  this.imageSize = {
                    width: 'auto',
                    height: '100%'
                  }
                }
              }
            })
            this.isInput = this.urls[this.imgIdx - 1].isInput
            this.pageId = this.urls[this.imgIdx - 1].pageId
          })
        }
      }, 300),
      beforeLeave(val, oldVal) {
        return new Promise((resolve, reject) => {
          setTimeout(() => {
            if (oldVal === 'first' && val === 'second') {
              this.$refs.costitem.resetForm()
              this.$refs.costitem.detailedno = ''
              if (this.isInput === 'Y') {
                const requestData = {
                  claimNo: this.claimNo,
                  incidentNo: '',
                  billInfo: this.$refs.invoice.invoiceForm,
                }
                // 切换至费用项前校验发票号是否存在于当前赔案中。
                billIsExist(requestData).then(response => {
                  if (!response.data) {
                    this.$refs.costitem.getCostDetails()
                    this.$refs.invoice.getInvoiceNo()
                    resolve()
                  } else {
                    this.$message.warning('当前发票号与已录入发票号不一致，请先锁定！')
                  }
                })
              } else {
                let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
                if (warnings.length === 0) {
                  if (this.status === 'show') {
                    this.$message.warning('无相关费用项！')
                  } else {
                    this.$message.warning('请先保存发票！如已保存，选择关联的影像！')
                  }
                }
                reject(false)
              }
            } else {
              resolve()
            }
          }, 10)
        })
      },
      tabClick() {
        if (this.invoiceName === 'second') {
          this.returnFlag = true
        } else {
          this.returnFlag = false
        }
      },
      activeChange(val) {
        if (val === 'second') {
          this.findInsuredInfoByClaimno()
        }
      },
      removeLocalStorage() {
        let data = JSON.parse(decodeURI(localStorage.getItem('saveList')));
        if (data !== undefined && data !== null) {
          let index = -1
          data.forEach((item, ind) => {
            if (decrypt(item.claimNo) === this.claimNo && item.pageId === this.editInvoice) {
              index = ind
            }
          })
          if (index !== -1) {
            data.splice(index, 1)
          }
          localStorage.removeItem('saveList');
          localStorage.setItem('saveList', JSON.stringify(data))
        }
      },
      // 清除发票修改缓存
      closeHandle() {
        this.removeLocalStorage()
        localStorage.removeItem('invoiceForm');
        localStorage.removeItem('statementsForm');
        window.close()
      },
      saveHandle() {
        if (this.activeName === 'first') {
          if (this.imageState[0] === '03') {
            let warnings = document.getElementsByClassName('el-message__icon el-icon-warning')
            if (warnings.length === 0) this.$message.warning('无影像时无需保存！')
            return
          }
          this.$refs.newinsured.saveBaseHandle()
        }
        if (this.activeName === 'second') {
          this.$refs.payeeInfo.payeeSave()
        }
      },
      sortImages() {
        // 根据pageid排序
        let temp
        for(let i=0;i<this.urls.length-1;i++){
          for(let j=i+1;j<this.urls.length;j++){
            if(this.urls[i].pageId > this.urls[j].pageId){
              temp=this.urls[j]
              this.urls[j]=this.urls[i]
              this.urls[i]=temp
            }
          }
        }
      },
      getInvoiceNo(data) {
        this.invoiceForm = data
      },
      // 录入完毕
      entryToComplete() {
        this.$set(this.resultForm, 'conclusion', '01')
        this.$set(this.resultForm, 'explanation', '录入完毕')
        this.submitHandle()
      },
      // 退回完毕
      returnToComplete() {
        this.returnVis = true
      },
      returnConfirm(val) {
        this.$set(this.resultForm, 'conclusion', '02')
        this.$set(this.resultForm, 'rejectreason', (val.rejectreason).substring(2,4))
        this.$set(this.resultForm, 'errortype', (val.rejectreason).substring(0,2))
        this.$set(this.resultForm, 'explanation', val.explanation)
        this.$set(this.resultForm, 'status', val.status)
        this.submitHandle(val)
      },
      submitHandle() {
        const params = {
          claimno: this.claimNo,
          ...this.resultForm,
          tasknode: 'claim_input'
        }
        if(this.caseLastNodeInfo.conclusion==='02' && this.resultForm.conclusion === '01'){
          params.isreturn = 'Y'
          params.status = this.caseLastNodeInfo.status
          params.reviewer = this.caseLastNodeInfo.creator
          let nextCaseNode = this.selectDictLabel(this.casestepOptions, this.caseLastNodeInfo.tasknode)
          params.nexttasknode = this.mapNode(nextCaseNode)
        }
        if(this.resultForm.conclusion !=='02'){
          this.inputCheck(params)
        } else {
          this.saveConclusionCheck(params)
        }
      },
      saveConclusionCheck(params) {
        // 流转权限
        processCheck(params).then(res =>{
          if (res.status == '1') {
            params.node = res.data.node
            params.personalpool = res.data.personalpool
            if (res.data.personalpool == true) {
              this.saveconsdialogVisible = true
            } else {
              this.confireSaveConculsion(params)
            }
          } else {
            this.$message({ message: res.data.errMsg , type: 'warning' })
          }
        })
      },
      // 节点映射(前后端有定义了两套节点需要映射)
      mapNode(node){
        switch (node) {
          case 'accept':
            return 'claim_accept'
          case 'scan':
            return 'claim_scan'
          case 'input':
            return 'claim_input'
          case 'firstTrial':
            return 'claim_preliminary_examination'
          case 'review':
            return 'claim_examine'
          case 'complex':
            return 'claim_review'
          case 'spotCheck':
            return 'spot_check'
          case 'correct':
            return 'claim_approve'
          default:
            return node
        }
      },
      inputCheck(params) {
        judgeBnf(this.claimNo).then(response => {
          if (response.status === '1') {
            if(response.data !== '' && this.resultForm.conclusion !=='02') {
              this.$message({message: response.data, type: 'warning'})
            } else {
              // 录入完成校验
              inputCheck(this.claimNo).then(response => {
                if (response.status === '1') {
                  this.saveConclusionCheck(params)
                } else if (response.data.errCode === 'CL137') {
                  this.$message({ message: response.data.errMsg ? response.data.errMsg:'操作失败！', type: 'error' })
                } else if (response.data.errCode === 'CL138') {
                  this.$message({ message: response.data.errMsg ? response.data.errMsg:'操作失败！', type: 'error' })
                } else {
                  this.$message({ message: '录入保存校验失败！', type: 'error' })
                }
              })
            }
          }
        })
      },
      confireSaveConculsion(params) {
        saveConclusion(params).then(res => {
          if (res.status === '1') {
            this.$message({ message: '操作成功！', type: 'success' })
            this.status = 'show'
            this.returnVis = false
            setTimeout(() => {
              window.close()
            }, 2000)
          } else {
            if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
              this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'error' })
            } else {
              this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'warning' })
            }
          }
        }).finally(() => {
        })
      },
      findInsuredInfoByClaimno() {
        findInsuredInfoByClaimno(this.claimNo).then(response => {
          if (response.status === '1') {
            this.informationOfTheInsured = response.data
            this.$refs.newinsured.initForm()
          }
        })
      },
      addPayeeinfo() {
        this.$refs.payeeInfo.addPayeeinfo()
      },
      checkCaseDeal() {
        let params = {
          node: 'C003',
          claimno: this.claimNo
        }
        if (this.incidentNo || this.editInvoice) return
        checkCaseDeal(params).then(res =>{
          if (res.data == true) {
            this.status = 'handle'
          } else {
            this.status = 'show'
          }
        })
      },
      saveInvoiceHandle(val) {
        let data = JSON.parse(decodeURI(localStorage.getItem('invoiceForm')));
        if (data !== null) {
          this.$nextTick(() => {
            this.$refs.invoice.invoiceForm = data
            this.$refs.invoice.hospitals = data.hosList
            this.$refs.invoice.ICDArr = data.firstList
            this.$refs.invoice.secondarydiagnosisICDArr = data.secondList
            this.$refs.invoice.otherdiagnosisICDArr = data.thirdList
          })
        }
        this.saveInvoiceForm = JSON.parse(JSON.stringify(val))
      },
      getFileCount(){
        let temp = [
          { label: "全部", name: '00', total: 0 },
          { label: "其他证明材料", name: '9', total: 0 },
          { label: "理赔申请书", name: '0', total: 0 },
          { label: "身份证", name: '1', total: 0 },
          { label: "关系证明", name: '2', total: 0 },
          { label: "银行卡", name: '3', total: 0 },
          { label: "医疗票据", name: '4', total: 0 },
          { label: "病历", name: '5', total: 0 },
          { label: "费用清单", name: '6', total: 0 },
          { label: "检查报告", name: '7', total: 0 },
          { label: "保险事故证明", name: '8', total: 0 }
        ]
        getFileCount(this.claimNo).then(res => {
          if (res.status === '1') {
            if(res.data){
              this.materialType = res.data.originallist
              if (this.incidentNo || this.editInvoice) {
                this.materialTypeRadio = '4'
              } else {
                this.materialTypeRadio = '1'
              }
              this.getBillImages(true)
            }
          }
        })
      },
    }
  }
</script>
<style scoped>
  .block {
    position: relative;
    width: 70px;
    text-align: center;
  }
  .inputFlag {
    display:inline-block;
    width:40px;
    height:30px;
    position: absolute;
    top: 10px;
    left: 15px;
    background: #fff;
    font-size: 12px;
    text-align: center;
    line-height: 30px;
    cursor: pointer;
    opacity: 0.8;
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
  .item-width {
    width: 200px;
  }
  #box {
    max-width: 400px;
    max-height: 400px;
  }

  .operate-bar {
    line-height: 40px;
    border: 1px solid #eee;
    border-radius: 6px;
    margin-top: 10px;
    margin-right: 6px;
    text-align: center;
  }
  .operate-bar>i {
    display: inline-block;
    width: 80px;
    text-align: center;
    font-size: 20px;
    cursor: pointer;
  }
  .handle-box{
    display: flex;
    margin: 10px;
    height: 100px;
    overflow-x: scroll;
  }
  .img-card  ::v-deep .el-card__body{
    padding: 200px;
  }
</style>
