<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="补充物理件" name="1" style="position: relative;">
        <el-form ref="supplementaryFrom" :rules="tableFormRules" :model="supplementaryFrom" size="mini">
          <span style="position: absolute;right: 40px;top: 0;" >
            <el-button :disabled="status !== 'handle'" type="primary" size="mini" style="position: absolute;right: 290px;top: 0;" @click="batchPrinting">打印条码</el-button>
            <el-button :disabled="status !== 'handle'" type="primary" size="mini" style="position: absolute;right: 200px;top: 0;" @click="toClient">批量扫描</el-button>
            <el-upload
              :before-upload="beforeUpload"
              :on-success="handleSuccess"
              :on-error="handleError"
              :file-list="fileList"
              :show-file-list="false"
              :headers="headers"
              :action="baseUrl + '?businessNo='+claimno+'&type=' + '9' +'&receivedmode=' + receivedMode"
              accept=".jpg,.jpeg,.png"
              class="upload-demo"
              multiple
              style="position: absolute;right: 110px;top: 0;"
            >
              <el-button :disabled="status !== 'handle'" type="primary" size="mini">批量上传</el-button>
            </el-upload>
            <el-button :disabled="status !== 'handle'" type="primary" size="mini" style="position: absolute;right: 20px;top: 0;" @click="toClassificat">影像分类</el-button>
          </span>
          <el-row>
            <el-table
              ref="table1"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="supplementaryFrom.tableData"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="index" width="55" label="序号" align="center"/>
              <el-table-column label="材料类型" prop="materialtype" align="center" width="180">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.materialtype'" :rules="tableFormRules.materialtype">
                    <el-select v-model="scope.row.materialtype" :disabled="scope.row.editFlag !== 'add'" style="width: 100%;" placeholder="请选择" clearable>
                      <el-option
                        v-for="item in materialtypeArr"
                        :label="item.materialname"
                        :value="item.materialtype"
                        :key="item.materialtype"
                        :disabled="supplementaryFrom.tableData.find(f=>{
                        return f.materialtype == item.materialtype }) != null"
                      />
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="发起日期" prop="createdate" align="center">
                <template slot-scope="scope">
<!--                  <el-form-item :prop="'tableData.' + scope.$index + '.createdate'">-->
<!--                    <el-date-picker-->
<!--                      v-model="scope.row.createdate"-->
<!--                      :disabled="true"-->
<!--                      type="date"-->
<!--                      format="yyyy-MM-dd HH:mm"-->
<!--                      placeholder="发起日期"/>-->
<!--                  </el-form-item>-->
                  <span>{{scope.row.createdate}}</span>
                </template>
              </el-table-column>
              <el-table-column label="提交日期" prop="receiveddate" align="center">
                <template slot-scope="scope">
<!--                  <el-form-item :prop="'tableData.' + scope.$index + '.receiveddate'">-->
<!--                    <el-date-picker-->
<!--                      v-model="scope.row.receiveddate"-->
<!--                      :disabled="true"-->
<!--                      format="yyyy-MM-dd HH:mm"-->
<!--                      type="date"-->
<!--                      placeholder="提交日期"/>-->
<!--                  </el-form-item>-->
                  <span>{{scope.row.receiveddate}}</span>
                </template>
              </el-table-column>
              <el-table-column v-if="status == 'handle'" label="操作" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span v-if="scope.row.editFlag">
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="saveHandle(scope.$index, scope.row, 1)">保存</el-button>
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row, 1)">删除</el-button>
                    </span>
                    <span v-else>
                      <el-upload
                        :before-upload="beforeUpload"
                        :on-success="handleSuccess"
                        :on-error="handleError"
                        :file-list="fileList"
                        :show-file-list="false"
                        :headers="headers"
                        :action="baseUrl1 + '?businessNo='+claimno+'&type=' + `${scope.row.materialtype}`+'&supplementarydetailno=' + `${scope.row.supplementarydetailno}`+'&receivedmode=' + receivedMode +'&fillingsflag=' + fillingsflag"
                        accept=".jpg,.jpeg,.png"
                        class="upload-demo"
                        style="display: inline-block"
                        multiple
                      >
                        <el-button :disabled="status !== 'handle'" type="text" style="margin-bottom: 10px;" size="mini">上传</el-button>
                      </el-upload>
                      <!--                      <el-button :disabled="status !== 'handle'" type="text" size="mini" style="margin-bottom: 10px;" @click="toClient">扫描</el-button>-->
                      <!-- <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 1)">上传</el-button> -->
                      <!-- <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 1)">扫描</el-button> -->
                    </span>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
            <el-button v-if="node!=='report'" :disabled="status !== 'handle'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(1)"> + 添加</el-button>
          </el-row>
          <el-row>
            <div class="clearfix">
              <span style="float:right" >
                <el-button :disabled="status !== 'handle'" type="primary" size="mini" @click="handleRefund">保存</el-button>
              </span>
            </div>
            <el-form ref="reForm" :model="reForm" :rules="reFromRules" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="退件方式：" prop="refundtype">
                    <el-select v-model="reForm.refundtype" class="item-width" placeholder="请选择" clearable @change="changeRefundtype">
                      <el-option v-for="item in refund_typeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="收件人姓名：" label-width="120px" prop="addressee">
                    <el-input v-model="reForm.addressee" maxlength="20" class="item-width" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="手机：" prop="telephone">
                    <el-input v-model="reForm.telephone" class="item-width" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="地址：" prop="address">
                    <el-input v-model="reForm.address" maxlength="100" type="textarea" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="备注：" prop="remarks">
                    <el-input v-model="reForm.remarks" maxlength="500" type="textarea" clearable size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
            <el-table
              v-show="tableFlag"
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
          </el-row>
          <el-row style="margin-left: 40px; margin-top: 20px;">
            <el-col :span="8">
              <el-form-item label="处理结论：" prop="conclusion">
                <el-select v-model="baseForm.conclusion" :disabled="status !=='handle'" style="width: 220px" placeholder="请选择" @change="changeList">
                  <el-option v-for="item in physicalpartsconclusionOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item v-if="shownoAllList" label="不齐全资料：" prop="cardType">
                <el-select v-model="baseForm.cardType" style="width: 220px" multiple placeholder="请选择">
                  <el-option
                    v-for="item in noAllList"
                    :label="item.materialname"
                    :value="item.materialtype"
                    :key="item.materialtype"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item v-if="shownoAllList" label="快递单号：" prop="couriernumber">
                <el-input v-model="baseForm.couriernumber" :disabled="status !=='handle'" size="mini" maxlength="20" class="item-width"/>
              </el-form-item>
            </el-col>
            <el-col :span="20" v-if="shownoAllList">
              <el-form-item label="推送方式：" prop="checked">
                <el-checkbox-group v-model="baseForm.checked" :disabled="status !=='handle'">
                  <el-checkbox label="01">蚂蚁平台</el-checkbox>
                  <el-checkbox label="02">邮件</el-checkbox>
                  <el-checkbox label="03">短信</el-checkbox>
                  <el-checkbox label="04">工单</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
            <el-col :span="22">
              <el-form-item label="结论说明：" prop="explanation" label-width="82px">
                <!--                <el-input :rows="2" v-model="baseForm.explanation" :disabled="status !=='handle'" type="textarea" placeholder="请输入内容"/>-->
                <el-autocomplete
                  v-model="baseForm.explanation"
                  :disabled="status !== 'handle'"
                  :fetch-suggestions="querySearch"
                  clearable
                  placeholder="请输入内容"
                  type="textarea"
                  style="width: 100%"
                  @select="handleSelect"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row style="text-align: right;">
            <el-button :disabled="status !== 'handle'" type="primary" size="mini" @click="finishedProcessing">处理完毕</el-button>
            <el-button type="primary" size="mini" @click="goBack">关闭</el-button>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <div v-show="iframeShow" id="printall" ref="printall">
      <!--      <div v-for="(item, ind) in barCodeList" :key="ind" style="margin-bottom: 20px">-->
      <div style="margin-bottom: 20px">
        <barcode :value="claimno" :options="barcode_option" tag="svg" />
      </div>
    </div>
    <iframe v-show="iframeShow" id="print-iframe"/>
  </el-card>
</template>
<script>
import { handleSubmit, getMaterialtype, addMentarydetail, addreturnInformation, returnInformationQuery, getScriptTemplate, getConclusion,blockCheck } from '@/api/claim/handleDeal.js'
const baseAddress = process.env.BASE_API
import { getToken } from '@/utils/auth'
import {encrypt} from "@/utils/rsaEncrypt"
/*import jwt from 'jsonwebtoken'*/
import Cookies from 'js-cookie'
import { validPhone } from '@/utils/validate'
import Bus from '../../bus/bus'

export default {
 // dicts: ['physicalpartsconclusion', 'image_type', 'refund_type'],
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: String,
    supplementaryno: String,
    supplementaryFrom: {
      type: Object,
      default: function() {
        return {}
      }
    },
    supplementaryArr: {
      type: Array,
      default: function() {
        return []
      }
    },
    noAllList: {
      type: Array,
      default: function() {
        return []
      }
    },
    tabLabels: {
      type: Object,
      default: function() {
        return {}
      }
    }
  },
  data() {
    const validcodeName = (rule, value, callback) => {
      if (this.resultForm.result == '02' || this.resultForm.result == '03') {
        if (this.resultForm.desc.length < 0) {}
        callback(new Error('不能为空！'))
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
      activeNames: '1',
      showFlag: false,
      baseForm: {
        tableData: [],
        checked: ['04'],
        cardType: [],
        couriernumber: '',
        conclusion: '',
        explanation: ''
      },
      paramsarr: [],
      iframeShow: false,
      barCodeList: [],
      shownoAllList: false,
      taskTableData: [],
      barcode_option: {
        displayValue: true, // 是否默认显示条形码数据
        background: '#fff', // 条形码背景颜色
        valid: function(valid) {
        },
        width: '1px', // 单个条形码的宽度
        height: '40px',
        fontSize: '12px' // 字体大小
      },
      receivedMode: '4',
      fillingsflag: '01',
      headers: { 'Authorization': 'Bearer ' + getToken() },
      fileList: [],
      baseUrl: baseAddress + '/file/file/claim/claimUpload',
      baseUrl1: baseAddress + '/file/file/claim/claimSupplementaryMaterialUpload',
      materialtypeArr: [],
      // noAllList: [],
      tableFormRules: {
        // date: { required: true, message: '不能为空!', trigger: 'change' },
        // result: { required: true, message: '不能为空!', trigger: 'change' },
        materialtype: { required: true, message: '不能为空!', trigger: 'change' }
      },
      reForm: {},
      disabled: false,
      paramsList: [],
      hasSelectList: [],
      reFromRules: {
        addressee: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        refundtype: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        // telephone: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        telephone: [{ required: true, validator: checkPhone, trigger: 'blur' }],
        address: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        remarks: [{ required: true, message: '不能为空!', trigger: 'blur' }]
        // expressnumber: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        // returneddate: [{ required: true, message: '不能为空!', trigger: 'blur' }],
      },
      tableFlag: false,

      physicalpartsconclusionOptions: [],
      image_typeOptions: [],
      refund_typeOptions: []
    }
  },
  created() {
    setTimeout(() => {
      if (this.node == 'physical') {
        this.querySearch()
        this.getMaterialtype()
        this.noAllList = []
        this.supplementaryFrom.tableData.map((item) => {
          const ele = {}
          ele.materialtype = item.materialtype
          ele.materialname = item.materialname
          this.noAllList.push(ele)
        })
        // this.disabled = this.$route.query.disabled === 'show'
      }
    }, 10)
  },
  mounted() {

    /*'physicalpartsconclusion', 'image_type', 'refund_type'*/
    if (this.dictList!=null && this.dictList.length!=0) {
      this.physicalpartsconclusionOptions = this.dictList.find(item => {
        return item.dictType == 'physicalpartsconclusion'
      }).dictDate
      this.image_typeOptions = this.dictList.find(item => {
        return item.dictType == 'image_type'
      }).dictDate
      this.refund_typeOptions = this.dictList.find(item => {
        return item.dictType == 'refund_type'
      }).dictDate
    }
    this.$forceUpdate()
    this.$nextTick(function() {
      if (this.node == 'physical') {
        this.getPhysicalMateriaList()
        this.getTemplate()
        this.getMaterialtype()
        if (this.status === 'show') {
          this.getConclusion()
        }
      }
    })
  },
  methods: {
    getTemplate() {
      const params = {
        templatetype: '02',
        supplementarymode: '04',
        status: '01'
      }
      getScriptTemplate(params).then(res => {
        if (res.status === '1') {
          this.temp = res.data
          this.temp.forEach(item => {
            item.value = item.contents
          })
        }
      })
    },
    // 审物理件查看反显处理结论
    getConclusion() {
      const params = {
        claimno: this.claimno,
        supplementaryno: this.supplementaryno
      }
      getConclusion(params).then(res => {
        if (res.status === '1') {
          this.baseForm = res.data
          if (res.data.conclusion == '02') {
            this.shownoAllList = true
            this.baseForm.cardType = res.data.incompletematerialtype
          }
          this.baseForm.checked = res.data.supplementarymode
        }
      })
    },
    querySearch(queryString, cb) {
      const results = this.temp.filter(array => array.value.match(queryString))
      cb(results)
    },
    handleSelect(item) {
    },
    // 审核物理件退件信息
    getPhysicalMateriaList() {
      const params = {
        supplementaryno: this.supplementaryno
      }
      returnInformationQuery(params).then(response => {
        if (response.status === '1') {
          this.taskTableData = response.data
          this.taskTableData.map((item) => {
            if (item.flag === '01') {
              this.reForm = item
              // if (item.refundtype === '0') {
              //   return
              // }
              this.$refs.dataTable.toggleRowSelection(item, true)
              this.hasSelectList.push(item.flag)
            }
          })
          this.$nextTick(function() {
            if (this.reForm.refundtype == '0' || this.reForm.refundtype == null) {
              this.tableFlag = false
            } else {
              this.tableFlag = true
            }
            this.taskTableData.forEach(row => {
              if (this.hasSelectList.indexOf(row.flag) >= 0) {
                this.$refs.dataTable.toggleRowSelection(row, true)
              }
            })
          })
        } else {
          this.$message({ message: '查询退件信息错误！', type: 'error' })
        }
      })
    },
    handleChange() {
      this.activeNames = ''
    },
    editHandle() {
      this.showFlag = false
    },
    getImagetype(row, col) {
      /*return this.dict.label.image_type[row.materialtype]*/
      return this.selectDictLabel(this.image_typeOptions, row.materialtype)
    },
    addHandel(idx) {
      // 表单字段具体要根据后端定义进行修改
      const field = {
        editFlag: 'add',
        timeFlag: 'add',
        materialtype: '',
        createdate: '',
        receiveddate: ''
      }
      this.supplementaryFrom.tableData.push(field)
      setTimeout(() => { this.$refs.table1.setCurrentRow(field) }, 10)
    },
    // 退件方式
    changeRefundtype(val) {
      if (this.reForm.refundtype == '0') {
        this.tableFlag = false
      } else {
        this.tableFlag = true
      }
    },
    // 选择退件的数据
    handleSelectionChange(val) {
      this.paramsList = val
    },
    // 获取材料类型
    getMaterialtype() {
      const claimno = this.claimno
      getMaterialtype(claimno).then(res => {
        if (res.status == '1') {
          const data = res.data || []
          this.materialtypeArr = data
        } else {
          this.$message.error('查询异常！')
        }
      })
    },
    // 上传失败回调
    handleError(file, fileList) {
      this.$message({ message: '上传失败！', type: 'error' })
    },
    // 文件上传之前校验
    beforeUpload(file) {
      const type = file.name.split('.')[file.name.split('.').length - 1].toLowerCase()
      const pdfType = type === 'jpg' || type === 'jpeg' || type === 'png'
      if (!pdfType) {
        this.$message.warning('文件上传格式不合法，请重新上传！')
        return false
      }
      let isLt2M = true
      isLt2M = file.size / 1024 / 1024 < 10
      if (!isLt2M) {
        this.$message.warning('上传文件大小不能超过 10MB!')
      }
      return isLt2M
    },
    // 上传成功回调
    handleSuccess(response, file, fileList) {
      this.fileList = fileList
      if (response.status === '1') {
        this.$message.success('上传成功！')
        // 物理件上传触发
        Bus.$emit('changePage3')
        this.$emit('getImageInfo', '4', 1, null, true)
        this.$emit('imageUpdate')
        this.$emit('getFileCount')
        this.getPhysicalMateriaList()
        this.$emit('getMaterialPendingQuery')
      } else {
        this.$message({ message: '上传异常！', type: 'error' })
      }
    },
    delHandle(index, row, idx) {
      this.$confirm(`是否确定删除?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.supplementaryFrom.tableData.splice(index, 1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    // 打印条码
    batchPrinting() {
      this.barCodeList = []
      this.barCodeList.push({ barcodes: this.claimno })
      const printIframe = document.getElementById('print-iframe')
      const print = printIframe.contentWindow
      const doc = print.document
      // doc.write(document.getElementById('printall').innerHTML)
      doc.write(this.$refs.printall.innerHTML)
      doc.close()
      print.focus()
      print.print()
    },
    // 扫描
    toClient() {
      const jwt = require('jsonwebtoken')
      const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
      const name = str.user_name
      window.open(`${process.env.BASE_API}/claim-easyscan5/EasyScanContainer?usercode=${name}&receivedmode=4`)
    },
    // 影像分类
    toClassificat() {
      // let data = {
      //   node: 'classificat',
      //   type: 'classify',
      //   claimNo: encrypt(this.claimno),
      //   materialNo: this.supplementaryno, // 补材号
      //   recievedMode: '4'// 大类
      // }
      const newpage = this.$router.resolve({
        name: 'ificatdetail',
        params:{},
        query:{
          node: 'classificat',
          type: 'classify',
          showFlag: '01',
          claimNo: encrypt(this.claimno),
          materialNo: this.supplementaryno, // 补材号
          recievedMode: '4'// 大类
        }
      })
      window.open(newpage.href, '_blank');
        // this.$router.push({
        //     path: '/claims-handle/classificat',
        //     query: {
        //         node: 'classificat',
        //         type: 'classify',
        //         claimNo: encrypt(this.claimno),
        //         materialNo: this.supplementaryno, // 补材号
        //         recievedMode: '4'// 大类
        //     }
        // })
    },
    changeList() {
      this.shownoAllList = false
      if (this.baseForm.conclusion == '02') {
        this.shownoAllList = true
      }
      this.baseForm.cardType = ''
      this.baseForm.couriernumber = ''
    },
    saveHandle(index, row, idx) {
      if (idx === 1) {
        this.$refs.supplementaryFrom.validate((valid) => {
          if (valid) {
            const params = {}
            params.materialtype = row.materialtype
            params.supplementaryno = this.supplementaryno || ''
            params.claimno = this.claimno || ''
            addMentarydetail(params).then(res => {
              if (res.status == '1') {
                this.supplementaryFrom.tableData[index].editFlag = ''
                this.$message.success('保存成功！')
                this.$emit('getMaterialPendingQuery')
                // 查询成功查询退件信息
                this.getPhysicalMateriaList()
              } else {
                this.$message.error('保存异常！')
              }
            })
          } else {
            return false
          }
        })
      }
    },
    goBack() {
      window.close()
      // this.$router.go(-1)
    },
    // 处理完毕
    finishedProcessing() {
      if (this.baseForm.conclusion == '' || this.baseForm.conclusion == undefined) {
        this.$message.warning('未选择处理结论！')
        return false
      }
      this.$emit('getFileCount')
      let tabFlag = false
      this.baseForm.explanation = this.baseForm.explanation.replace(/\s+/g, "");
      this.baseForm.couriernumber = this.baseForm.couriernumber.replace(/\s+/g, "");
      this.tabLabels.supplementpartlist.forEach(tab => {
        if (tab.label == '全部' && tab.total <= 0) {
          tabFlag = true
        }
      })
      if (this.baseForm.conclusion == "02") {
        if (this.baseForm.cardType == '') {
          this.$message.warning('未选择不齐全材料！')
          return false
        }
        if (this.baseForm.checked && this.baseForm.checked.length == 0) {
          this.$message.warning('未选择推送方式！')
          return false
        }
        if (this.baseForm.explanation == '' || this.baseForm.explanation == undefined) {
          this.$message.warning('未选择结论说明！')
          return false
        }
      }
      if (tabFlag) {
        this.$confirm('当前赔案仍存在待补材料，请确认是否处理?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const str = this.baseForm.checked.join(',')
          const params = {}
          params.claimno = this.claimno || ''
          params.supplementaryno = this.supplementaryno || ''
          params.conclusion = this.baseForm.conclusion || ''
          params.incompletematerialtype = this.baseForm.cardType || []
          params.explanation = this.baseForm.explanation || ''
          params.couriernumber = this.baseForm.couriernumber || ''
          params.supplementarymode = str || ''
          handleSubmit(params).then(res => {
            if (res.status === '1') {
              this.$message.success('处理成功！')
              Bus.$emit('updatePhysicalStatus','show')
              setTimeout(() =>{
                window.close()
              },3000)
              this.$router.go(-1)
              this.statusUpdate()
            } else {
              this.$message.error(res.data.errMsg)
            }
          })
        }).catch(() => {
          this.$message.info('已取消！')
        })
      } else {
        const str = this.baseForm.checked.join(',')
        const params = {}
        params.claimno = this.claimno || ''
        params.supplementaryno = this.supplementaryno || ''
        params.conclusion = this.baseForm.conclusion || ''
        params.incompletematerialtype = this.baseForm.cardType || []
        params.explanation = this.baseForm.explanation || ''
        params.couriernumber = this.baseForm.couriernumber || ''
        params.supplementarymode = str || ''
        handleSubmit(params).then(res => {
          if (res.status === '1') {
            this.$message.success('处理成功！')
            setTimeout(() =>{
              window.close()
            },3000)
            Bus.$emit('updatePhysicalStatus','show')
            this.statusUpdate()
            this.$router.go(-1)
          } else {
            this.$message.error(res.data.errMsg)
          }
        })
      }
    },
    statusUpdate() {
      let params = {
        supplementaryno : this.supplementaryno,
        sign: '02'
      }
      blockCheck(params).then(res =>{
        console.log(typeof(res.data),123,res.data)
        if (res.data == true) {
          this.status == 'handle'
        } else {
          this.status == 'show'
        }
        console.log(this.status,566)
      })
    },
    // 保存退件
    handleRefund() {
      if (this.paramsList.length === 0 && this.reForm.refundtype != '0') {
        this.$message.warning('请勾选需要退件的材料类型！')
        return false
      }
      this.$refs.reForm.validate((valid) => {
        if (valid) {
          if (this.reForm.refundtype === '0') {
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
              this.$message({ message: '原件数量都为0，不能保存！', type: 'warning'})
              return
            }
          } else {
            if (this.paramsList.some(item => item.originals == '0')) {
              this.$message({ message: '原件数量为0，不能保存！', type: 'warning'})
              return
            }
            this.taskTableData.forEach(item => {
              this.paramsList.some(function(val) {
                return val.materialtype === item.materialtype
              }) ? item.flag = '01' : item.flag = '02'
            })
          }
          const params = {}
          params.address = this.reForm.address || ''
          params.addressee = this.reForm.addressee || ''
          params.refundno = this.reForm.refundno || ''
          params.claimno = this.claimno || ''
          params.telephone = this.reForm.telephone || ''
          params.remarks = this.reForm.remarks || ''
          params.refundtype = this.reForm.refundtype || ''
          params.supplementaryno = this.supplementaryno || ''
          // params.materialRequestVo = this.paramsarr || []
          params.materialRequestVo = this.taskTableData
          params.fillingsflag = this.$route.query.fillingsflag || '01'
          addreturnInformation(params).then(res => {
            if (res.status == '1') {
              this.$message.success('保存成功！')
              this.getPhysicalMateriaList()
            } else {
              this.$message({ message: res.data.errMsg, type: 'error' })
            }
          })
        }
      })
    }
  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10%;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
