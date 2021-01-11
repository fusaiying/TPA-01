<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>已有材料</span>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column :formatter="getImageType" align="center" prop="materialtype" label="材料类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="receiveddate" label="提交时间" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ (scope.row.originals || scope.row.copies) ? scope.row.receiveddate : '' }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="originals" label="原件数量" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.originals ? scope.row.originals : 0 }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="copies" label="复印件数量" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.copies ? scope.row.copies : 0 }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="operator" label="操作人" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button size="small" type="text">{{ scope.row.operator }}</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card class="box-card" shadow="never" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>影像缩略图</span>
      </div>
      <el-tabs :stretch="true" v-model="activeName1" class="tabs-box" @tab-click="handleClick('1', activeName1)">
        <el-tab-pane
          v-loading="imageLoad1"
          v-for="tab in tabLabels.originallist"
          :label="`${tab.label}(${tab.total})`"
          :name="tab.name"
          :key="tab.name">
<!--          <p class="arrow arr-left" @click="pageChange('-','1',activeName1,tab.total)"><i class="el-icon-arrow-left"/></p>-->
<!--          <p class="arrow arr-right" @click="pageChange('+','1',activeName1,tab.total)"><i class="el-icon-arrow-right"/></p>-->
          <el-row v-if="imageInfo.originallist.list.length && activeName1===tab.name" class="imgList_div" :id="'image' + tab.name + '1'" @scroll.native="getScrollTop('1', page0, tab.total, tab.name)">
            <el-col v-for="(url, i) in imageInfo.originallist.list" :span="3" :key="i" class="block">
              <el-image
                :src="'data:image/jpeg;base64,'+ url.src"
                :preview-src-list="originallistPreviewList"
                @click="getoriginallistPreviewList(i,url.pageid,imageInfo.originallist.list,'1')"
                style="width: 96%; height: 160px"
                fit="fill"/>
              <div class="time-wrapper">
                <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
              </div>
            </el-col>
            <el-col v-show="load1" :span="24" style="text-align: center">没有更多了</el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    <el-card v-if="supplementaryno !== ''" class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>待补材料</span>
      </div>
      <el-table
        :data="materialTableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column :formatter="getImageType" align="center" prop="materialtype" label="材料类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="receiveddate" label="提交时间" show-overflow-tooltip/>
        <el-table-column align="center" prop="fillingsdays" label="补材天数" show-overflow-tooltip/>
        <!-- <el-table-column align="center" prop="description" label="备注" show-overflow-tooltip/> -->
        <el-table-column label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-upload
              :before-upload="beforeUpload"
              :on-success="handleSuccess"
              :on-error="handleError"
              :file-list="fileList"
              :disabled="disabled"
              :show-file-list="false"
              :headers="headers"
              :action="baseUrl + '?businessNo='+claimno+'&type=' + `${scope.row.materialtype}`+'&supplementarydetailno=' + `${scope.row.supplementarydetailno}`+'&receivedmode=' + receivedMode + '&fillingsflag=' + fillingsflag"
              accept=".jpg,.jpeg"
              class="upload-demo"
              multiple
            >
              <el-button :disabled="disabled" size="small" type="text" >上传</el-button>
              <!--              <el-button :disabled="disabled" type="text" size="small" style="" @click="toClient">扫描</el-button>-->
            </el-upload>
            <!-- <el-button v-else type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button> -->
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card v-if="supplementaryno !== ''" class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix" style="position:relative">
        <span>补充影像缩略图</span>
        <span>
          <el-button :disabled="disabled" type="primary" size="mini" style="position: absolute;right: 290px;top: 0;" @click="batchPrinting">打印条码</el-button>
          <el-button :disabled="disabled" type="primary" size="mini" style="position: absolute;right: 200px;top: 0;" @click="toClient">批量扫描</el-button>
          <el-upload
            :before-upload="beforeUpload"
            :on-success="handleSuccess"
            :on-error="handleError"
            :disabled="disabled"
            :file-list="fileList"
            :show-file-list="false"
            :headers="headers"
            :action="baseUrl1 + '?businessNo='+claimno+'&type=' + '9' +'&receivedmode=' + receivedMode"
            accept=".jpg,.jpeg,.png"
            class="upload-demo"
            multiple
            style="position: absolute;right: 110px;top: 0;"
          >
            <el-button :disabled="disabled" type="primary" size="mini">批量上传</el-button>
          </el-upload>
          <el-button :disabled="disabled" type="primary" size="mini" style="position: absolute;right: 20px;top: 0;" @click="toClassificat">影像分类</el-button>
        </span>
      </div>
      <el-tabs :stretch="true" v-model="activeName2" class="tabs-box" @tab-click="handleClick('2', activeName2)">
        <el-tab-pane
          v-loading="imageLoad2"
          v-for="tab in tabLabels.supplementlist"
          :label="`${tab.label}(${tab.total})`"
          :name="tab.name"
          :key="tab.name">
<!--          <p class="arrow arr-left" @click="pageChange('-','2',activeName2,tab.total)"><i class="el-icon-arrow-left"/></p>-->
<!--          <p class="arrow arr-right" @click="pageChange('+','2',activeName2,tab.total)"><i class="el-icon-arrow-right"/></p>-->
          <el-row v-if="imageInfo.supplementlist.list.length && activeName2===tab.name" class="imgList_div" :id="'image' + tab.name + '2'" @scroll.native="getScrollTop('2', page1, tab.total, tab.name)">
            <el-col v-for="(url, i) in imageInfo.supplementlist.list" :span="3" :key="i" class="block">
              <el-image
                :src="'data:image/jpeg;base64,'+ url.src"
                :preview-src-list="originallistPreviewList"
                @click="getoriginallistPreviewList(i,url.pageid,imageInfo.supplementlist.list,'1')"
                style="width: 96%;height:160px"
                fit="fill"/>
              <div class="time-wrapper">
                <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
              </div>
            </el-col>
            <el-col v-show="load2" :span="24" style="text-align: center">没有更多了</el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    <el-card v-if="supplementaryno !== ''" class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>退件信息</span>
        <span style="float:right" >
          <el-button :disabled="disabled" type="primary" size="mini" @click="handleRefund">保存</el-button>
        </span>
      </div>
      <el-form ref="baseForm" :model="baseForm" :rules="baseFromRules" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="退件方式：" prop="refundtype">
              <el-select v-model="baseForm.refundtype" class="item-width" placeholder="请选择" clearable @change="changeRefundtype">
                <el-option v-for="item in dict.refund_type" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收件人姓名：" label-width="120px" prop="addressee">
              <el-input v-model="baseForm.addressee" maxlength="20" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机：" prop="telephone">
              <el-input v-model="baseForm.telephone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="地址：" prop="address">
              <el-input v-model="baseForm.address" maxlength="100" show-word-limit type="textarea" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注：" prop="remarks">
              <el-input v-model="baseForm.remarks" maxlength="500" show-word-limit type="textarea" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table
        v-show="taskTableFlag"
        ref="dataTable"
        :data="taskTableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;"
        @selection-change="handleSelectionChange">
        <el-table-column :disabled="disabled" align="center" type="selection" width="50"/>
        <el-table-column :formatter="getImagetype" align="center" prop="materialtype" label="材料类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="receiveddate" label="提交时间" show-overflow-tooltip/>
        <el-table-column align="center" prop="originals" label="原件数量" show-overflow-tooltip/>
        <el-table-column align="center" prop="copies" label="复印件数量" show-overflow-tooltip/>
      </el-table>
    </el-card>
    <el-card v-if="querys.flag === '1'" class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>重开结论</span>
      </div>
      <el-row>
        <el-form ref="resultForm1" :model="resultForm1" :rules="addFormRules" size="mini" label-width="94px">
          <el-col :span="24">
            <el-form-item label="处理结论：" prop="reasontype">
              <el-select v-model="resultForm1.reasontype" :disabled="disabled" style="width: 200px;" placeholder="请选择" clearable size="mini" @change="changeReasntype">
                <el-option v-for="option in dict.reopen_conclusion" :key="option.id" :label="option.label" :value="option.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="结论说明：" prop="description">
              <el-input v-model="resultForm1.description" :disabled="disabled" type="textarea" style="width:800px" maxlength="1000" show-word-limit clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <span style="float: right; padding: 3px 0">
            <el-button slot="reference" :disabled="disabled" type="primary" size="mini" @click="submitHandle1">处理完毕</el-button>
            <el-button type="primary" size="mini" @click="goBack">关闭</el-button>
          </span>
        </el-form>
      </el-row>
    </el-card>
    <el-card v-else class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>补材结论</span>
      </div>
      <el-row>
        <el-form ref="resultForm" :model="resultForm" :rules="resultFromRules" size="mini" label-width="94px">
          <el-col :span="24">
            <el-form-item
              label="处理结论："
              prop="result">
              <el-select v-model="resultForm.result" :disabled="disabled" style="width: 140px;" placeholder="请选择" size="mini">
                <el-option v-for="item in dict.supplementaryconclusion" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="结论说明：" prop="desc">
              <el-input v-model="resultForm.desc" :disabled="disabled" maxlength="200" show-word-limit clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <span style="float: right; padding: 3px 0">
            <el-button :disabled="disabled" type="primary" size="mini" @click="submitHandle">处理完毕</el-button>
            <el-button type="primary" size="mini" @click="goBack">关闭</el-button>
          </span>
        </el-form>
      </el-row>
    </el-card>
    <div v-show="iframeShow" id="printall">
      <div v-for="item in barCodeList" :key="item.barcodes" style="margin-bottom: 20px">
        <barcode :value="item.barcodes" :options="barcode_option" tag="svg" />
      </div>
    </div>
    <iframe v-show="iframeShow" id="print-iframe"/>
  </div>
</template>

<script>
import {decrypt, encrypt} from "@/utils/rsaEncrypt"
import { getImageInfo, getFileCount , judgeReopen , blockCheck } from '@/api/claim/handleDeal'
import { getSupplementInfo, getSupplementaryno, insertRestartCase, addreturnInformation, addSupplementaryMaterial, gethadMaterial } from '@/api/claim/reopenCase'
import { get } from '@/api/dictDetail'
/*import jwt from 'jsonwebtoken'*/
import { preView } from '@/api/claim/fileDeal'
import Cookies from 'js-cookie'
const baseAddress = process.env.BASE_API
import { getToken } from '@/utils/auth'
/*import pro from '../../../../config/prod.env'*/
import { validPhone } from '@/utils/validate'
import {dowloadFileOne} from '@/api/claim/handleDeal'
import { downloadData } from '@/utils'


export default {
  dicts: ['supplementaryconclusion', 'image_type', 'refund_type', 'reopen_conclusion'],
  data() {
    const validcodeName = (rule, value, callback) => {
        if (this.resultForm.result == '02' || this.resultForm.result == '03') {
            if (this.resultForm.desc.length <= 0) {
                callback(new Error('不能为空！'))
            } else {
                callback()
            }
        } else {
            callback()
        }
    }
    const checkPhone = (rule, value, callback) => {
        if (!value) {
            callback(new Error('不能为空！'))
        } else {
            if (value !== '' && value !== null) {
                const str = value.replace(/\s/g, '')
                if (!validPhone(str)) {
                    callback(new Error('请输入正确的手机号！'))
                } else {
                    callback()
                }
            } else {
                callback()
            }
        }
    }
    return {
      baseFromRules: {
        telephone: [{ required: true, validator: checkPhone, trigger: 'blur' }],
        addressee: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        refundtype: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        address: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        remarks: [{ required: true, message: '不能为空!', trigger: 'blur' }],
      },
      addFormRules: {
        description: { required: false, message: '不能为空!', trigger: 'change' }, // 疾病类型
        reasontype: { required: true, message: '不能为空!', trigger: 'change' }
      },
      originallistPreviewList: [''],
      tempList: [],
      fileList: [],
      originallistId: '',
      AllfileList: [],
      headers: { 'Authorization': 'Bearer ' + getToken() },
      claimno: '',
      flag: false,
      supplementaryno: '',
      fillingsflag: '',
      tableData: [],
      paramsList: [],
      receivedMode: '2',
      tabItem: [
        { label: '全部', name: '00' },
        { label: '申请书', name: '01' },
        { label: '身份证', name: '02' },
        { label: '银行卡', name: '03' },
        { label: '发票', name: '04' },
        { label: '清单', name: '05' },
        { label: '病历', name: '06' },
        { label: '其他', name: '07' }
      ],
      iframeShow: false,
      materialTableData: [],
      disabled: false,
      taskTableData: [],
      activeName2: '00',
      activeName1: '00',
      baseForm: {},
      resultForm: {
        desc: '',
        result: ''
      },
      resultFromRules: {
          result: [{ required: true, message: '不能为空!', trigger: 'change' }],
          desc: [{ required: this.descFlag, validator: validcodeName, trigger: 'blur' }]
      },
      resultForm1: {
        reasontype: '',
        description: ''
      },
      page0: 1,
      page1: 1,
      page2: 1,
      page3: 1,
      hasSelectList: [],
      reasontypes: [],
      paramsarr: [],
      descFlag: false,
      // 影像文件
      imageInfo: {
        originallist: {
          list: [],
          previewList: []
        },
        supplementlist: {
          list: [],
          previewList: []
        },
        surveylist: [],
        supplementpartlist: []
      },
      tabLabels: {
        originallist: [],
        supplementlist: [],
        supplementpartlist: [],
        surveylist: []
      },
      baseUrl: baseAddress + '/file/file/claim/claimSupplementaryMaterialUpload',
      baseUrl1: baseAddress + '/file/file/claim/claimUpload',
      barCodeList: [],
      urls: [
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
      ],
      barcode_option: {
        displayValue: true, // 是否默认显示条形码数据
        background: '#fff', // 条形码背景颜色
        valid: function(valid) {
        },
        width: '1px', // 单个条形码的宽度
        height: '40px',
        fontSize: '12px' // 字体大小
      },
      taskTableFlag: true,
      querys: null,
      imageLoad1: false,
      imageLoad2: false,
      activeName: '',
      load1: false,
      load2: false,
      top1: 0
    }
  },
  created() {
    this.querys = JSON.parse(decodeURI(this.$route.query.data))
    this.flag = this.querys.flag
    this.claimno = decrypt(this.querys.claimno)
    this.initData()
    this.getConclusionTypeData()
    this.barCodeList.push({ barcodes: this.claimno })
    this.disabled = this.querys.disabled === 'show'
    this.getImageInfo('1', 1)
    this.getImageInfo('2', 1)
    this.getFileCount()
    if (this.querys.node == 'reopen') {
     this.judgeReopenStatus()
    }
    if (this.querys.node == 'materials') {
      this.judeNodeStatus()
    }
    this.monitor()
    // this.clickClose()
  },
  methods: {
    getImageType(row) {
      return this.dict.label.image_type[row.materialtype]
    },
    initData() {
      if (this.flag === '1') {
        getSupplementaryno(this.claimno).then(response => {
          if (response.status === '1') {
            this.supplementaryno = response.data
            console.log(this.supplementaryno)
            this.fillingsflag = '01'
            if (response.data != '') {
              this.getSupplementInfo()
            } else {
              this.getList()
            }
          } else {
            this.$message.error('查询失败！')
          }
        })
      } else if (this.flag === '2') {
        this.supplementaryno = this.querys.supplementaryno
        this.fillingsflag = this.querys.fillingsflag
        this.claimno = decrypt(this.querys.claimno)
        this.getSupplementInfo()
      }
    },
    stopBodyScroll (isFixed) {
        var bodyEl = document.body
        if (isFixed) {
          this.top1 = window.scrollY
          bodyEl.style.position = 'fixed'
          bodyEl.style.top = -this.top1 + 'px'
          bodyEl.style.width = '100%'
        } else {
          bodyEl.style.position = ''
          bodyEl.style.top = ''
          window.scrollTo(0, this.top1) // 回到原先的top
          bodyEl.style.width = '100%'
        }
      },
    // 补材时判断当前节点的状态
    judeNodeStatus () {
      let params = {
        supplementaryno : this.querys.supplementaryno,
        sign: '01',
        fillingsflag: this.fillingsflag === '02' ? '02' : ''
      }
      blockCheck(params).then(res =>{
        if (res.data == true) {
          this.disabled = false
        } else {
          this.disabled = true
        }
      })
    },
    monitor(){
      var that = this;
      document.onkeydown = function(e) {
        let key = window.event.keyCode;
        // 监听键盘左键
        if (key == 37) {
          that.pre(that.tempList)
        } else if(key == 39) {  // 监听键盘右键
          that.next(that.tempList)
        }
      };
    },
    getoriginallistPreviewList(index,id,data,type) {
      let list = []
      list = data
      this.tempList = data
      this.originallistPreviewList = []
      let params = {
        pageid: id
      }
      preView(id).then(response => {
        this.originallistPreviewList = []
        this.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        this.$nextTick(() =>{
          this.appendDel(this.tempList,index)
        })
      })
      this.stopBodyScroll(true)
    },
    generateLabel(name) {
      let buttonBox = document.getElementsByClassName('el-image-viewer__actions__inner')[0]
      let newEle = document.createElement('i')
      newEle.className = name
      buttonBox.appendChild(newEle)
    },
    appendDel(data,index) {
      this.originallistId = index
      let _that = this
      // 第一次打开预览时生成
      this.$nextTick(function() {
        if (document.getElementsByClassName('has').length === 0) {
          this.generateLabel('el-icon-arrow-left has')
          this.generateLabel('el-icon-arrow-right has')
          this.generateLabel('el-icon-download has')
        }

        // 左
        let left = document.getElementsByClassName('el-icon-arrow-left has')[0] // 左
        left.onclick = function pre() {
          if (_that.originallistId == 0) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId -1
          }
          let id = data[_that.originallistId].pageid
          preView(id).then(response => {
            // _that.originallistPreviewList = []
            // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
            _that.originallistPreviewList = ['data:image/jpeg;base64,'+ response.data]
            _that.originallistPreviewList = [..._that.originallistPreviewList]
          })
        }
        var element = document.getElementsByClassName('el-image-viewer__btn el-image-viewer__close')[0]
        let _this = this
        element.onclick = function next() {
          _this.stopBodyScroll(false)
        }
        // 右
        let right = document.getElementsByClassName('el-icon-arrow-right has')[0]// 右
        right.onclick = function next() {
          if (_that.originallistId == data.length-1) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId + 1
          }
          let id = data[_that.originallistId].pageid
          preView(id).then(response => {
            // _that.originallistPreviewList = []
            // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
            _that.originallistPreviewList = ['data:image/jpeg;base64,'+ response.data]
            _that.originallistPreviewList = [..._that.originallistPreviewList]
          })
        }

        //下载
        let download = document.getElementsByClassName('el-icon-download has')[0]// 下载
        download.onclick =function () {
          let id = data[_that.originallistId].pageid
          if (_that.originallistId == 0) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId -1
          }
          dowloadFileOne(id).then(response => {
            downloadData(response,data[_that.originallistId].name)
          })
        }
      })
    },
    pre () {
      let _that = this
      if (_that.originallistId == 0) {
        _that.originallistId = _that.originallistId
      } else {
        _that.originallistId = _that.originallistId -1
      }
      let id = _that.tempList[_that.originallistId].pageid
      preView(id).then(response => {
        _that.originallistPreviewList = []
        _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        _that.originallistPreviewList = [..._that.originallistPreviewList]
      })
    },
    next () {
      let _that = this
      if (_that.originallistId == _that.tempList.length-1) {
        _that.originallistId = _that.originallistId + 1
      } else {
        _that.originallistId = _that.originallistId + 1
      }
      let id = _that.tempList[_that.originallistId].pageid
      preView(id).then(response => {
        _that.originallistPreviewList = []
        _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        _that.originallistPreviewList = [..._that.originallistPreviewList]
      })
    },
    // 撤案重开判断当前节点的状态
    judgeReopenStatus() {
      judgeReopen(this.claimno).then(res =>{
        if (res.data == false) {
          this.disabled = false
        } else {
          this.disabled = true
        }
      })
    },
    getList() {
      gethadMaterial(this.claimno).then(res => {
        if (res.status == '1') {
          this.tableData = res.data
        } else {
          this.$message.error('查询异常！')
        }
      })
    },
    changeReasntype(value) {
      if (value == '02' || value == '04') {
        this.addFormRules.description.required = true
      } else {
        this.addFormRules.description.required = false
      }
    },
    getSupplementInfo() {
      var params = {
        claimno: this.claimno,
        supplementaryno: this.supplementaryno,
        fillingsflag: this.fillingsflag
      }
      getSupplementInfo(params).then(response => {
        if (response.status === '1') {
          this.tableData = (response.data && response.data.materialProcessedlQueryDOS) || []
          // let tableData = (response.data && response.data.materialProcessedlQueryDOS) || []
          // tableData.forEach(item => {
          //   if (item.originals > 0 || item.copies > 0) {
          //     this.tableData.push(item)
          //   }
          // })
          this.materialTableData = (response.data && response.data.materialPendingQueryDOS) || []
          this.taskTableData = (response.data && response.data.returnMaterialInfoDOS) || []
          if (this.querys.disabled === 'show') {
            this.resultForm.result = response.data.conclusion
            this.resultForm.desc = response.data.explanation
          }
          this.taskTableData.map((item) => {
            // let arr = []
            if (item.flag === '01') {
              this.hasSelectList.push(item.flag)
              // arr.push(item)
            }
            // this.baseForm = arr.length ? arr[0] : {}
          })
          this.baseForm = (response.data && response.data.returnInformationQuery) || {}
          this.$nextTick(() => {
            if (this.baseForm.refundtype !== '1') {
              this.taskTableFlag = false
            } else {
              this.taskTableFlag = true
            }
            this.taskTableData.forEach(row => {
              if (this.hasSelectList.indexOf(row.flag) >= 0) {
                this.$refs.dataTable.toggleRowSelection(row, true)
              }
            })
          })
        } else {
          this.$message.error('查询失败！')
        }
      })
    },
    getImagetype(row, col) {
      return this.dict.label.image_type[row.materialtype]
    },
    // 上传失败回调
    handleError(file, fileList) {
      this.$message({ message: '上传失败！', type: 'error' })
    },
    // 文件上传之前校验
    beforeUpload(file) {
      const type = file.name.split('.')[file.name.split('.').length - 1]
      const pdfType = type === 'jpg' || type === 'jpeg' || type === 'png'
      if (!pdfType) {
        this.$message.error('文件上传格式不合法，请重新上传！')
        return false
      }
      let isLt2M = true
      isLt2M = file.size / 1024 / 1024 < 10
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 10MB!')
      }
      return isLt2M
    },
    // 上传成功回调
    handleSuccess(response, file, fileList) {
      this.fileList = fileList
      if (response.status === '1') {
        this.$message.success('上传成功！')
        // this.imageInfo.originallist.list = []
        // this.imageInfo.originallist.previewList = []
        this.imageInfo.supplementlist.list = []
        this.imageInfo.supplementlist.previewList = []
        // this.getImageInfo('1', 1, null, true)
        this.activeName2 = '00'
        this.page1 = 1
        this.getImageInfo('2', 1, null, true)
        this.getFileCount()
        this.initData()
        // this.getSupplementInfo()
      } else {
        this.$message({ message: '上传异常！', type: 'error' })
      }
    },
    // 退件方式
    changeRefundtype(val) {
      if (this.baseForm.refundtype === '0') {
        this.taskTableFlag = false
      } else {
        this.taskTableFlag = true
      }
    },
    // 选择退件的数据
    handleSelectionChange(val) {
      this.paramsList = val
    },
    handleRefund() {
      if (this.paramsList.length === 0 && this.baseForm.refundtype !== '0') {
        this.$message.warning('请勾选需要退件的材料类型！')
        return false
      }
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          if (this.baseForm.refundtype === '0') {
            let flag = 0
            this.taskTableData.forEach(item => {
              if (item.originals > 0) {
                flag++
                item.flag = '01'
              } else {
                item.flag = '02'
              }
            })
            if (flag == 0) {
                this.$message.warning('原件数量都为0，不能保存！')
                return
            }
          } else {
            if (this.paramsList.some(item => item.originals == '0')) {
                this.$message.warning('原件数量为0，不能保存！')
                return
            }
            this.taskTableData.forEach(item => {
              this.paramsList.some(function(val) {
                return val.materialtype === item.materialtype
              }) ? item.flag = '01' : item.flag = '02'
            })
          }
          const params = {}
          params.address = this.baseForm.address || ''
          params.addressee = this.baseForm.addressee || ''
          params.refundno = this.baseForm.refundno || ''
          if(this.flag ==='1') {
            params.supplementaryno = this.supplementaryno
          } else {
            params.supplementaryno = this.querys.supplementaryno || ''
          }
          params.claimno = this.claimno || ''
          params.telephone = this.baseForm.telephone || ''
          params.remarks = this.baseForm.remarks || ''
          params.refundtype = this.baseForm.refundtype || ''
          // params.materialRequestVo = this.paramsarr || []
          params.materialRequestVo = this.taskTableData
          params.fillingsflag = this.querys.fillingsflag || '01'
          addreturnInformation(params).then(res => {
            if (res.status == '1') {
              this.$message.success('保存成功！')
              this.getSupplementInfo()
            } else {
              this.$message({ message: res.data.errMsg, type: 'error' })
            }
          })
        } else {
          return false
        }
      })
    },
    // 处理结论下拉选
    getConclusionTypeData() {
      const dictName = 'reopen_conclusion'
      get(dictName).then(response => {
        this.reasontypes = response.content
      })
    },
    submitHandle() {
      this.resultForm.desc = this.resultForm.desc.replace(/\s+/g, "");
      this.resultForm.result === '02' || this.resultForm.result === '03' ? this.descFlag = true : this.descFlag = false
      this.$forceUpdate()
      this.$refs.resultForm.validate((valid) => {
        if (valid) {
          let tabFlag = false
          this.tabLabels.supplementlist.forEach(tab => {
            if (tab.label == '全部' && tab.total <= 0) {
              tabFlag = true
            }
          })
          if (tabFlag) {
            this.$confirm('当前赔案仍存在待补材料，请确认是否处理?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              const params = {}
              params.conclusion = this.resultForm.result || ''
              params.explanation = this.resultForm.desc || ''
              params.claimno = this.claimno || ''
              params.fillingsflag = this.querys.fillingsflag || ''
              params.supplementaryno = this.querys.supplementaryno || ''
              addSupplementaryMaterial(params).then(res => {
                if (res.status === '1') {
                  this.$router.go(-1)
                  this.$message.success('处理成功！')
                  this.judeNodeStatus()
                } else {
                  this.$message({ message: res.data.errMsg, type: 'error' })
                }
              })
            }).catch(() => {
              this.$message.info('已取消！')
            })
          } else {
            const params = {}
            params.conclusion = this.resultForm.result || ''
            params.explanation = this.resultForm.desc || ''
            params.claimno = this.claimno || ''
            params.fillingsflag = this.querys.fillingsflag || ''
            params.supplementaryno = this.querys.supplementaryno || ''
            addSupplementaryMaterial(params).then(res => {
              if (res.status === '1') {
                this.$router.go(-1)
                this.$message.success('处理成功！')
                this.judeNodeStatus()
              } else {
                this.$message({ message: res.data.errMsg, type: 'error' })
              }
            })
          }
        } else {
          return false
        }
      })
    },
    submitHandle1() {
      this.resultForm1.description = this.resultForm1.description.replace(/\s+/g, "")
      // if (this.resultForm1.reasontype == '02' && this.resultForm1.description == '') {
      //   this.$message.warning('结论说明不能为空！')
      //   return false
      // }
      this.$refs.resultForm1.validate(valid => {
        if (valid) {
          let tabFlag = false
          this.tabLabels.supplementlist.forEach(tab => {
            if (tab.label == '全部' && tab.total <= 0) {
              tabFlag = true
            }
          })
          if (tabFlag && this.supplementaryno) {
            this.$confirm('当前赔案仍存在待补材料，请确认是否处理?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              var params = {
                claimno: this.claimno,
                reasontype: this.resultForm1.reasontype,
                description: this.resultForm1.description,
                supplementaryno: this.supplementaryno || ''
              }
              insertRestartCase(params).then(response => {
                if (response.status === '1') {
                  this.$router.go(-1)
                  this.$message.success('重开处理完成！')
                  this.disabled = true
                } else {
                  this.$message.error('保存失败！')
                }
              }).catch(error => {
                this.$message.error('保存异常！')
              })
            }).catch(() => {
              this.$message.info('已取消！')
            })
          } else {
            var params = {
              claimno: this.claimno,
              reasontype: this.resultForm1.reasontype,
              description: this.resultForm1.description,
              supplementaryno: this.supplementaryno || ''
            }
            insertRestartCase(params).then(response => {
              if (response.status === '1') {
                this.$router.go(-1)
                this.$message.success('重开处理完成！')
                this.disabled = true
              } else {
                this.$message.error('保存失败！')
              }
            }).catch(error => {
              this.$message.error('保存异常！')
            })
          }
        } else {
          return false
        }
      })
    },
    goBack() {
      window.close()
    },
    handleClick(catalog, subtype) {
      this.page0 = 1
      this.page1 = 1
      this.page2 = 1
      this.page3 = 1
      let sub = null
      if (subtype !== '00') {
        sub = subtype
      }
      this.getImageInfo(catalog, 1, sub, true)
      if (this.activeName !== subtype) {
        this.changeList(catalog)
      }
      this.activeName = subtype
    },
    changeList(type) {
      this.page0 = 1
      this.page1 = 1
      this.page2 = 1
      this.page3 = 1
      this.load1 = false
      this.load2 = false
      if(type==='1'){
        this.imageInfo.originallist.list = []
        this.imageInfo.originallist.previewList = []
      } else if(type==='2'){
        this.imageInfo.supplementlist.list = []
        this.imageInfo.supplementlist.previewList = []
      }
    },
    // pageChange(type, catalog, sub, total) {
    //   sub === '00' ? sub = null : sub
    //   if (type === '-') {
    //     if (catalog === '1') {
    //       if (this.page0 === 1) {
    //         this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //         return false
    //       } else {
    //         this.page0--
    //         this.getImageInfo(catalog, this.page0, sub)
    //       }
    //     } else if (catalog === '2') {
    //       if (this.page1 === 1) {
    //         this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //         return false
    //       } else {
    //         this.page1--
    //         this.getImageInfo(catalog, this.page1, sub)
    //       }
    //     } else if (catalog === '3') {
    //       if (this.page2 === 1) {
    //         return this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       } else {
    //         this.page2--
    //         this.getImageInfo(catalog, this.page2, sub)
    //       }
    //     } else if (catalog === '4') {
    //       if (this.page3 === 1) {
    //         return this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       } else {
    //         this.page3--
    //         this.getImageInfo(catalog, this.page3, sub)
    //       }
    //     }
    //   } else {
    //     if (catalog === '1') {
    //       if (this.page0 >= Math.ceil(total / 5)) {
    //         this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //         return false
    //       } else {
    //         this.page0++
    //         this.getImageInfo(catalog, this.page0, sub)
    //       }
    //     } else if (catalog === '2') {
    //       if (this.page1 >= Math.ceil(total / 5)) {
    //         this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //         return false
    //       } else {
    //         this.page1++
    //         this.getImageInfo(catalog, this.page1, sub)
    //       }
    //     } else if (catalog === '3') {
    //       if (this.page2 >= Math.ceil(total / 5)) {
    //         return this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //       } else {
    //         this.page2++
    //         this.getImageInfo(catalog, this.page2, sub)
    //       }
    //     } else if (catalog === '4') {
    //       if (this.page3 >= Math.ceil(total / 5)) {
    //         return this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //       } else {
    //         this.page3++
    //         this.getImageInfo(catalog, this.page3, sub)
    //       }
    //     }
    //   }
    // },
    // pageChange(type, catalog, sub,total) {
    //   console.log(total,123)
    //   sub === '00' ? sub = null : sub
    //   if (type === '-') {
    //     if (this.page1 === 1) {
    //       this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       return false
    //     } else {
    //       this.page1--
    //       this.getImageInfo(catalog, this.page1, sub)
    //     }
    //   } else {
    //     if (this.page == total ) {
    //       this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       return false
    //     }
    //     this.page1++
    //     this.getImageInfo(catalog, this.page1, sub)
    //   }
    // },
    getFileCount() {
      const temp = [
        { label: '全部', name: '00', total: 0 },
        { label: '申请书', name: '0', total: 0 },
        { label: '身份证', name: '1', total: 0 },
        { label: '银行卡', name: '3', total: 0 },
        { label: '发票', name: '4', total: 0 },
        { label: '清单', name: '6', total: 0 },
        { label: '病历', name: '5', total: 0 },
        { label: '其他', name: '9', total: 0 }
      ]
      getFileCount(this.claimno).then(res => {
        if (res.status === '1') {
          if (res.data) {
            this.tabLabels = res.data
          } else {
            for (const key in this.tabLabels) {
              this.tabLabels[key] = temp
            }
            // this.tabLabels.originallist = temp
            // this.tabLabels.supplementlist = temp
            // this.tabLabels.supplementpartlist = temp
            // this.tabLabels.surveylist = temp
          }
        } else {
          for (const key in this.tabLabels) {
            this.tabLabels[key] = temp
          }
        }
      })
    },
    // 影像文件
    getImageInfo(type, pageno, subtype = null, change=false) {
      const params = {
        claimno: this.claimno,
        type,
        subtype,
        pageno,
        pagesize: 5
      }
      if (type === '1') {
        this.imageLoad1 = true
      } else {
        this.imageLoad2 = true
      }
      // this.imageLoad = true
      getImageInfo(params).then(response => {
        if (response.status === '1') {
          if (change) {
            this.changeList(type)
          }
          if (type === '1') {
            response.data.list.map((item, i) => {
              this.imageInfo.originallist.list.push(item)
              this.imageInfo.originallist.previewList.push('data:image/jpeg;base64,'+ item.src)
            })
            // this.imageInfo.originallist = response.data || []
            // this.imageInfo.originallist.push(response.data)
          } else if (type === '2') {
            // this.imageInfo.supplementlist = response.data || []
            response.data.list.map((item, i) => {
              this.imageInfo.supplementlist.list.push(item)
              this.imageInfo.supplementlist.previewList.push('data:image/jpeg;base64,'+ item.src)
            })
            // this.imageInfo.supplementlist.push(response.data)
          }
        } else {
          this.$message({ message: '查询影像文件错误！', type: 'error' })
        }
      }).finally(() => {
        this.imageLoad1 = false
        this.imageLoad2 = false
      })
    },
    toClient() {
      const jwt = require('jsonwebtoken')
      const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
      const name = str.user_name
      window.open(`${process.env.BASE_API}/claim-easyscan5/EasyScanContainer?usercode=${name}&receivedmode=2`)
    },
    toClassificat() {
      // this.$router.push({
      //   path :'/claims-handle/image-class',
      //   query: {
      //     claimNo: this.claimno,
      //     node: 'classificat'
      //   }
      // })
      // let data =  {
      //   node: 'classificat',
      //   type: 'classify',
      //   claimNo: encrypt(this.claimno),
      //   materialNo: this.querys.supplementaryno, // 补材号
      //   recievedMode: '2'// 大类
      // }
      const newpage = this.$router.resolve({
        name: 'ificatdetail',
        params:{},
        query:{
          node: 'classificat',
          type: 'classify',
          showFlag: '01',
          claimNo: encrypt(this.claimno),
          materialNo: this.querys.supplementaryno, // 补材号
          recievedMode: '2'// 大类
        }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/classificat',
      //   query: {
      //     node: 'classificat',
      //     type: 'classify',
      //     claimNo: encrypt(this.claimno),
      //     materialNo: this.querys.supplementaryno, // 补材号
      //     recievedMode: '2'// 大类
      //   }
      // })
    },
    batchPrinting() {
      const printIframe = document.getElementById('print-iframe')
      const print = printIframe.contentWindow
      const doc = print.document
      doc.write(document.getElementById('printall').innerHTML)
      doc.close()
      print.focus()
      print.print()
    },
    load(type, page, total) {
      if (type === '1') {
        if (this.page0 < Math.ceil(total / 5)) {
          this.page0++
          this.type = type
          this.loadMore('1', this.page0, this.activeName1)
          this.load1 = false
        } else {
          this.load1 = true
        }
      } else if (type === '2') {
        if (this.page1 < Math.ceil(total / 5)) {
          this.page1++
          this.type = type
          this.loadMore('2', this.page1, this.activeName2)
          this.load2 = false
        } else {
          this.load2 = true
        }
      }
    },
    loadMore(type, page, subType) {
      if (subType === '00') {
        subType = ''
      }
      this.getImageInfo(type, page, subType)
    },
    getScrollTop(type, page, total, name) {
      const scrollTop = document.getElementById('image' + name + type).scrollTop
      const scrollHeight = document.getElementById('image' + name + type).scrollHeight
      const offsetHeight = document.getElementById('image' + name + type).offsetHeight
      if ((offsetHeight + scrollTop) - scrollHeight === 0) {
        this.load(type, page, total, name)
      }
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}
.el-collapse {
  border: none;
}
.arr-left {
  left: 5px;
}
.arr-right {
  right: 5px;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10.5%;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 400;
  font-size: 14px;
}
.arrow {
  display: inline-block;
  position: absolute;
  top: 20px;
  width: 30px;
  height: 30px;
  display: none;
  /* display: block; */
  font-size: 18px;
  border-radius: 50%;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
  background: rgba(0,0,0,.1);
  z-index: 999999;
}
.tabs-box:hover .arrow{
  display: block;
  transition: .5;
}
.arr-left {
  left: 5px;
}
.arr-right {
  right: 5px;
}
.imgList_div {
  height: 180px;
  width: 100%;
  overflow: auto;
}
.block {
  border: 1px solid #f6f6f6;
  width: 16.4%;
  height: 180px;
  text-align:center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 8px 0;
}
.block .el-image {
  margin: 0 auto;
}
.el-image-viewer__mask /deep/ .el-image-viewer__close {
  top: 60px;
  right: 30px;
}
.el-tabs__item  /deep/ .is-top is-active {
  padding: 0 5px;
}
.time-wrapper {
  padding-top: 2px;
  height: 16px;
  line-height: 16px;
  font-size: 12px;
  color: #909399;
  /* padding-top: 10px; */
}
</style>
