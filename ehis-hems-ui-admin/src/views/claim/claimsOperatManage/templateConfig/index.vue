<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :rules="searchFormRules" style="padding-bottom: 30px;" label-width="126px" label-position="right" size="mini">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>模板配置</span>
        </div>
        <div style="font-size: 14px; margin-top: 10px; color: #555;">
          模版类型：<el-radio v-model="radio" label="01">赔付结论</el-radio>
          <el-radio v-model="radio" label="02">补材</el-radio>
          <!--          <el-radio v-model="radio" label="3">撤案</el-radio>-->
          <el-radio v-model="radio" label="04">不予受理</el-radio>
        </div>
        <div class="tab_newNotice" style="margin-top: 20px;">
          <el-tabs v-model="radio" :before-leave="preventClick">
            <el-tab-pane name="01" v-show="radio === '01'" :disabled="true">
              <claims-table :loading="loading" :status="radio" :table-data="tableData" :chooseRadio="chooseRadio" @handleCurrentChange="handleCurrentChange" @delHandle="delHandle"/>
              <!--结论-->
              <el-pagination
                :total="conclusionTotal"
                :current-page="conclusionCurrentPage"
                :page-size="conclusionPageSize"
                :page-sizes="[10, 20, 30, 50]"
                style="margin-top: 8px; text-align: right;"
                layout="prev, pager, next, sizes"
                @size-change="conclusionSizeChange"
                @current-change="conclusionPageChange"/>
            </el-tab-pane>
            <el-tab-pane name="02" v-show="radio === '02'"  :disabled="true">
              <claims-table :loading="loading" :status="radio" :table-data="tableData" :chooseRadio="chooseRadio" @handleCurrentChange="handleCurrentChange" @delHandle="delHandle"/>
              <!--补材-->
              <el-pagination
                :total="supplementTotal"
                :current-page="supplementCurrentPage"
                :page-size="supplementPageSize"
                :page-sizes="[10, 20, 30, 50]"
                style="margin-top: 8px; text-align: right;"
                layout="prev, pager, next, sizes"
                @size-change="supplementSizeChange"
                @current-change="supplementPageChange"/>
            </el-tab-pane>
<!--            <el-tab-pane name="03">-->
<!--              <claims-table :status="radio" :table-data="withdrawnData" :chooseRadio="chooseRadio" @handleCurrentChange="handleCurrentChange" @delHandle="delHandle"/>-->
<!--              &lt;!&ndash;撤案&ndash;&gt;-->
<!--              <el-pagination-->
<!--                :total="withdrawnTotal"-->
<!--                :current-page="withdrawnCurrentPage"-->
<!--                :page-size="withdrawnPageSize"-->
<!--                :page-sizes="[10, 20, 30, 50]"-->
<!--                style="margin-top: 8px; text-align: right;"-->
<!--                layout="prev, pager, next, sizes"-->
<!--                @size-change="withdrawnSizeChange"-->
<!--                @current-change="withdrawnPageChange"/>-->
<!--            </el-tab-pane>-->
            <el-tab-pane name="04" v-show="radio === '04'"  :disabled="true">
              <claims-table :loading="loading" :status="radio" :chooseRadio="chooseRadio" :table-data="tableData" @handleCurrentChange="handleCurrentChange" @delHandle="delHandle"/>
              <!--不予-->
              <el-pagination
                :total="notAccepteTotal"
                :current-page="notAccepteCurrentPage"
                :page-size="notAcceptePageSize"
                :page-sizes="[10, 20, 30, 50]"
                style="margin-top: 8px; text-align: right;"
                layout="prev, pager, next, sizes"
                @size-change="notAccepteSizeChange"
                @current-change="notAcceptePageChange"/>
            </el-tab-pane>
          </el-tabs>
        </div>
        <el-divider/>
        <div style="margin-top: 40px;">
          <el-row v-if="radio==='01'">
            <el-col :span="8">
              <el-form-item label="险种：" prop="conclusionForm.insurancecode">
                <el-select v-model="searchForm.conclusionForm.insurancecode" clearable filterable placeholder="请选择" @change="$set(searchForm.conclusionForm, 'dutycode', '')">
                  <el-option
                    v-for="(item, ind) in riskList"
                    :label="item.insurancecode + ' ' + item.insurancename"
                    :value="item.insurancecode"
                    :key="ind">
                    <span style="float: left">{{ item.insurancecode }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.insurancename }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="责任：" prop="conclusionForm.dutycode">
                <el-select v-model="searchForm.conclusionForm.dutycode" clearable class="item-width" placeholder="请选择" @focus="getDutyList">
                  <el-option v-for="(item, ind) in dutyList" :label="item.dutycode + '' + item.dutyname" :value="item.dutycode" :key="ind">
                    <span style="float: left">{{ item.dutycode }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.dutyname }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
<!--            <el-col :span="8">-->
<!--              <el-form-item label="推送方式：" prop="conclusionForm.supplementarymode">-->
<!--                <el-select-->
<!--                  v-model="searchForm.conclusionForm.supplementarymode"-->
<!--                  clearable-->
<!--                  class="item-width"-->
<!--                  multiple-->
<!--                  collapse-tags-->
<!--                  placeholder="请选择">-->
<!--                  <el-option-->
<!--                    v-for="(item, ind) in dict.supplementmode"-->
<!--                    :key="ind"-->
<!--                    :label="item.label"-->
<!--                    :value="item.value"/>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
            <el-col :span="8">
              <el-form-item label="赔付结论：" prop="conclusionForm.payConclusion">
                <el-select v-model="searchForm.conclusionForm.payConclusion" clearable class="item-width" placeholder="请选择" @change="changeConclusion">
                   <el-option v-for="(item, ind) in dict.conclusion" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="赔付结论依据：" prop="conclusionForm.claimEvidence">
                <el-select v-model="searchForm.conclusionForm.claimEvidence" clearable class="item-width" placeholder="请选择">
                  <el-option v-for="(item, ind) in claimEvidence" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="拒付类型：" prop="conclusionForm.templatetype">
                <el-select
                  v-model="searchForm.conclusionForm.templatetype"
                  clearable
                  class="item-width"
                  multiple
                  collapse-tags
                  placeholder="请选择">
                   <el-option
                    v-for="(item, ind) in dict.compensationtype"
                    :key="ind"
                    :label="item.label"
                    :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="话术变量：" prop="conclusionForm.scriptVariate">
                <el-select v-model="searchForm.conclusionForm.scriptVariate" clearable class="item-width" placeholder="请选择">
                   <el-option v-for="(item, ind) in dict.words_variables" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="话术模板：" prop="conclusionForm.contents">
                <el-input :rows="2" v-model="searchForm.conclusionForm.contents" maxlength="2000" show-word-limit type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="radio==='02'">
            <el-col :span="8">
              <el-form-item label="推送方式：" prop="supplementForm.supplementarymode">
                <el-select
                  v-model="searchForm.supplementForm.supplementarymode"
                  clearable
                  class="item-width"
                  multiple
                  collapse-tags
                  placeholder="请选择">
                  <el-option
                    v-for="(item, ind) in dict.supplementmode"
                    :key="ind"
                    :label="item.label"
                    :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="补材类型：" prop="supplementForm.supplementtype">
                <el-select v-model="searchForm.supplementForm.supplementtype" clearable class="item-width" placeholder="请选择">
                  <el-option v-for="(item, ind) in dict.supplementarytype" :value="item.value" :label="item.label" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
<!--            <el-col :span="8">-->
<!--              <el-form-item label="材料类型：" prop="supplementForm.datumType">-->
<!--                <el-select-->
<!--                  v-model="searchForm.supplementForm.datumType"-->
<!--                  clearable-->
<!--                  class="item-width"-->
<!--                  multiple-->
<!--                  collapse-tags-->
<!--                  placeholder="请选择">-->
<!--                  <el-option-->
<!--                    v-for="(item, ind) in dict.image_type"-->
<!--                    :key="ind"-->
<!--                    :label="item.label"-->
<!--                    :value="item.value"/>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
            <el-col :span="8">
              <el-form-item label="话术变量：" prop="supplementForm.scriptVariate">
                <el-select v-model="searchForm.supplementForm.scriptVariate" clearable class="item-width" placeholder="请选择">
                  <el-option v-for="(item, ind) in dict.words_variables" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="补材业务类型：" prop="supplementForm.templatetype">
                <el-select v-model="searchForm.supplementForm.templatetype" clearable class="item-width" placeholder="请选择">
                  <el-option v-for="(item, ind) in dict.supplementary_material_business_type" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="话术模板：" prop="supplementForm.contents">
                <el-input :rows="2" v-model="searchForm.supplementForm.contents" maxlength="2000" show-word-limit type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-row>
          <!--          <el-row v-if="radio==='3'">-->
          <!--            <el-col :span="8">-->
          <!--              <el-form-item label="撤案原因：" prop="withdrawnReason">-->
          <!--                <el-select v-model="withdrawnForm.withdrawnReason" clearable class="item-width" placeholder="请选择">-->
          <!--                  <el-option v-for="item in dict.withdrawnReason" :label="item.label" :value="item.value+item.label" :key="item.value"/>-->
          <!--                </el-select>-->
          <!--              </el-form-item>-->
          <!--            </el-col>-->
          <!--            <el-col :span="8">-->
          <!--              <el-form-item label="推送方式：" prop="supplementarymode">-->
          <!--                <el-select v-model="withdrawnForm.supplementarymode" clearable class="item-width" placeholder="请选择">-->
          <!--                  <el-option v-for="item in dict.supplementarymode" :label="item.label" :value="item.value+item.label" :key="item.value"/>-->
          <!--                </el-select>-->
          <!--              </el-form-item>-->
          <!--            </el-col>-->
          <!--            <el-col  :span="8">-->
          <!--              <el-form-item label="模板类型：" prop="templatetype">-->
          <!--                <el-select v-model="withdrawnForm.templatetype" clearable class="item-width" placeholder="请选择">-->
          <!--                  <el-option v-for="item in dict.templatetype" :label="item.label" :value="item.value+item.label" :key="item.value"/>-->
          <!--                </el-select>-->
          <!--              </el-form-item>-->
          <!--            </el-col>-->
          <!--            <el-col v-if="radio!=='1'" :span="8">-->
          <!--              <el-form-item label="话术变量：" prop="scriptVariate">-->
          <!--                <el-select v-model="withdrawnForm.scriptVariate" clearable class="item-width" placeholder="请选择">-->
          <!--                  <el-option v-for="item in dict.scriptVariate" :label="item.label" :value="item.value+item.label" :key="item.value"/>-->
          <!--                </el-select>-->
          <!--              </el-form-item>-->
          <!--            </el-col>-->
          <!--            <el-col :span="24">-->
          <!--              <el-form-item label="话术模板：" prop="policyNo">-->
          <!--                <el-input :rows="2" v-model="withdrawnForm.textarea" type="textarea" placeholder="请输入内容"/>-->
          <!--              </el-form-item>-->
          <!--            </el-col>-->
          <!--          </el-row>-->
          <el-row v-if="radio==='04'">
            <el-col :span="8">
              <el-form-item label="不予受理原因：" prop="notAccepteForm.notAccepteReason">
                <el-select v-model="searchForm.notAccepteForm.notAccepteReason" clearable class="item-width" placeholder="请选择">
                  <el-option v-for="(item, ind) in dict.acceptreason" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
<!--            <el-col :span="8">-->
<!--              <el-form-item label="推送方式：" prop="notAccepteForm.supplementarymode">-->
<!--                <el-select-->
<!--                  v-model="searchForm.notAccepteForm.supplementarymode"-->
<!--                  clearable-->
<!--                  class="item-width"-->
<!--                  multiple-->
<!--                  collapse-tags-->
<!--                  placeholder="请选择">-->
<!--                  <el-option-->
<!--                    v-for="(item, ind) in dict.supplementmode"-->
<!--                    :key="ind"-->
<!--                    :label="item.label"-->
<!--                    :value="item.value"/>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="不予受理类型：" prop="notAccepteForm.templatetype">-->
<!--                <el-select v-model="searchForm.notAccepteForm.templatetype" clearable class="item-width" placeholder="请选择">-->
<!--                  <el-option v-for="(item, ind) in dict.inadmissibility_type" :label="item.label" :value="item.value" :key="ind"/>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
            <el-col :span="8">
              <el-form-item label="话术变量：" prop="notAccepteForm.scriptVariate">
                <el-select v-model="searchForm.notAccepteForm.scriptVariate" clearable class="item-width" placeholder="请选择">
                  <el-option v-for="(item, ind) in dict.words_variables" :label="item.label" :value="item.value" :key="ind"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="话术模板：" prop="notAccepteForm.contents">
                <el-input :rows="2" v-model="searchForm.notAccepteForm.contents" maxlength="2000" show-word-limit type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <div style="text-align: right; margin-right: 10px;">
          <el-button :loading="saveLoad" size="mini" type="primary" @click="saveHandle">保存</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-card>
    </el-form>
  </div>
</template>
<script>
    import claimsTable from './components/claimsTable'
    import { queryTemplate, getRiskDutyDetail, configTemplate, deleteTemplate } from '@/api/template/template'
    import { _debounce } from '@/utils/commenMethods.js'

    export default {
        components: {
            claimsTable
        },
        dicts: ['scriptTemplate', 'compensationtype', 'supplementary_material_business_type', 'scriptVariate', 'templatetype', 'notAccepteReason', 'withdrawnReason', 'datumType', 'supplementtype',
            'inadmissibility_type', 'supplementarytype', 'conclusionreason', 'conclusionreason1', 'conclusionreason2', 'conclusionreason3', 'conclusionreason4', 'grantpremiumexemption',
            'claimEvidence', 'payConclusion', 'supplementarymode', 'duty', 'product', 'acceptreason', 'supplementmode', 'words_variables', 'words_variables', 'conclusion', 'image_type'],
        data() {
            return {
                searchForm: {
                    conclusionForm: {
                        insurancecode: '',
                        dutycode: '',
                        supplementarymode: [],
                        payConclusion: '',
                        claimEvidence: '',
                        templatetype: [],
                        scriptVariate: '',
                        contents: ''
                    },
                    supplementForm: {
                        supplementtype: '',
                        datumType: [],
                        supplementarymode: [],
                        scriptVariate: '',
                        templatetype: '',
                        contents: ''
                    },
                    withdrawnForm: {
                    },
                    notAccepteForm: {
                        notAccepteReason: '',
                        supplementarymode: [],
                        templatetype: '',
                        scriptVariate: '',
                        contents: ''
                    },
                },
                searchFormRules: {
                    conclusionForm: {
                        // insurancecode: { required: true, message: '不可为空', trigger: 'change' },
                        // dutycode: { required: true, message: '不可为空', trigger: 'change' },
                        // supplementarymode: { required: true, message: '不可为空', trigger: 'change' },
                        payConclusion: { required: true, message: '不可为空', trigger: 'change' },
                        claimEvidence: { required: true, message: '不可为空', trigger: 'change' },
                        // templatetype: { required: true, message: '不可为空', trigger: 'blur' },
                        // scriptVariate: { required: true, message: '不可为空', trigger: 'blur' },
                        contents: { required: true, message: '不可为空', trigger: 'blur' }
                    },
                    supplementForm: {
                        // supplementtype: { required: true, message: '不可为空', trigger: 'blur' },
                        // datumType: { required: true, message: '不可为空', trigger: 'blur' },
                        supplementarymode: { required: true, message: '不可为空', trigger: 'change' },
                        // scriptVariate: { required: true, message: '不可为空', trigger: 'blur' },
                        // templatetype: { required: true, message: '不可为空', trigger: 'change' },
                        contents: { required: true, message: '不可为空', trigger: 'blur' }
                    },
                    withdrawnForm: {
                    },
                    notAccepteForm: {
                        notAccepteReason: { required: true, message: '不可为空', trigger: 'change' },
                        // supplementarymode: { required: true, message: '不可为空', trigger: 'change' },
                        // templatetype: { required: true, message: '不可为空', trigger: 'blur' },
                        // scriptVariate: { required: true, message: '不可为空', trigger: 'blur' },
                        contents: { required: true, message: '不可为空', trigger: 'blur' }
                    },
                },
                tableData: [],
                params: [],
                radio: '01',
                riskList: [],
                dutyList: [],
                conclusionData: [],
                supplementData: [],
                withdrawnData: [],
                notAccepteData: [],
                conclusionTotal: null,
                conclusionPageSize: 10,
                conclusionCurrentPage: 1,
                supplementTotal: null,
                supplementPageSize: 10,
                supplementCurrentPage: 1,
                withdrawnTotal: null,
                withdrawnPageSize: 10,
                withdrawnCurrentPage: 1,
                notAccepteTotal: null,
                notAcceptePageSize: 10,
                notAccepteCurrentPage: 1,
                chooseRadio: null,
                claimEvidence: [],
                pushList: [],
                loading: false,
                saveLoad: false
            }
        },
        created() {
            this.initData()
        },
        watch: {
            radio(val) {
              this.resetForm()
              this.getTemplate(val)
            }
        },
        methods: {
            initData() {
                this.getRiskDutyDetail()
                this.getTemplate('01')
                // this.getTemplate('02')
                // this.getTemplate('03')
                // this.getTemplate('04')
            },
            preventClick() {
                return false
            },
            // 获取险种下拉列表
            getRiskDutyDetail() {
                getRiskDutyDetail().then(res => {
                    if (res.status === '1') {
                        this.riskList = res.data
                    }
                })
            },
            // 获取责任下拉列表
            getDutyList() {
                this.riskList.forEach(item => {
                    if (item.insurancecode === this.searchForm.conclusionForm.insurancecode) {
                        this.dutyList = item.dutyDetailDOList
                    }
                })
            },
            // 获取推送方式
            getSupplementmode(value) {
                return this.dict.label.supplementmode[value]
            },
            getImageType(value) {
                return this.dict.label.image_type[value]
            },
            getTemplate(type,flag='') {
                let pageSize
                let currentPage
                if (type === '01') {
                    if (flag === 'del') this.conclusionCurrentPage -= 1
                    pageSize = this.conclusionPageSize
                    currentPage = this.conclusionCurrentPage
                } else if (type === '02') {
                    if (flag === 'del')  this.supplementCurrentPage -= 1
                    pageSize = this.supplementPageSize
                    currentPage = this.supplementCurrentPage
                } else if (type === '03') {
                    if (flag === 'del') this.withdrawnCurrentPage -= 1
                    pageSize = this.withdrawnPageSize
                    currentPage = this.withdrawnCurrentPage
                } else if (type === '04') {
                    if (flag === 'del') this.notAccepteCurrentPage -= 1
                    pageSize = this.notAcceptePageSize
                    currentPage = this.notAccepteCurrentPage
                }
                const param = {
                    pagesize: pageSize,
                    pageno: currentPage,
                    templatetype: type
                }
                this.loading = true
                queryTemplate(param)
                    .then(response => {
                        if (response.status === '1') {
                            response.data.list.forEach((item, ind) => {
                                item.id = ind
                            })
                            if (type === '01') {
                                this.tableData = response.data.list
                                this.conclusionTotal = response.data.total
                            } else if (type === '02') {
                                this.tableData = response.data.list
                                this.supplementTotal = response.data.total
                            } else if (type === '03') {
                                this.tableData = response.data.list
                            } else if (type === '04') {
                                this.tableData = response.data.list
                                this.notAccepteTotal = response.data.total
                            }
                        } else {
                            this.$message({ message: '查询失败！', type: 'error' })
                        }
                    })
                    .catch(error => {
                        this.$message({ message: '查询异常！', type: 'error' })
                    }).finally(res => {
                        this.loading = false
                })
            },
            cusSizeChange(val) {
                this.cusPageInfo.pageSize = val
            },
            cusPageChange(val) {
                this.cusPageInfo.page = val
            },
            resetForm() {
                this.$refs.searchForm.resetFields()
                this.chooseRadio = null
                this.searchForm.conclusionForm.contentcode = ''
                this.searchForm.conclusionForm.templatetype = []
                this.searchForm.supplementForm.contentcode = ''
                this.searchForm.supplementForm.supplementarymode = []
                this.searchForm.supplementForm.templatetype = ''
                this.searchForm.notAccepteForm.contentcode = ''
                this.searchForm.notAccepteForm.supplementarymode = []
            },
            saveHandle: _debounce(function(){
              this.$refs.searchForm.validate((valid) => {
                if (valid) {
                  this.saveLoad = true
                  let params = this.getParams()
                  if (params.contents.length > 2000) {
                    this.$message.warning('话术模板长度小于等于2000！')
                    return
                  }
                  configTemplate(params)
                    .then(response => {
                      if (response.status === '1') {
                        this.$message({ message: '保存成功', type: 'success'})
                        this.getTemplate(this.radio)
                        this.resetForm()
                      } else if (response.data.errCode === 'CL019') {
                        this.$message({ message: response.data.errMsg, type: 'error' })
                      } else {
                        this.$message({ message: '保存失败！', type: 'error' })
                      }
                      this.saveLoad = false
                    })
                    .catch(error => {
                      // this.$message({ message: '保存异常！', type: 'error' })
                    })
                } else {
                  return false
                }
              })
            }, 500),
            getParams() {
                const params = {
                    contentcode: '',// 内容编码
                    templatetype: '',// 模板类型
                    contents: '',// 模板内容
                    supplementarymode: '',// 推送方式
                    insurancecode: '',// 险种编码
                    dutycode: '',// 责任编码
                    circulationvalue1: '',
                    circulationvalue2: '',
                    circulationvalue3: ''
                }
                // 处理参数格式，主要是将多选项的数组转为字符串
                if (this.radio === '01') {
                    params.contentcode = this.searchForm.conclusionForm.contentcode || ''
                    params.templatetype = this.radio
                    params.insurancecode = this.searchForm.conclusionForm.insurancecode || ''
                    params.supplementarymode = this.searchForm.conclusionForm.supplementarymode.sort().join(',') || ''
                    params.dutycode = this.searchForm.conclusionForm.dutycode || ''
                    params.contents = this.searchForm.conclusionForm.contents || ''
                    params.circulationvalue1 = this.searchForm.conclusionForm.payConclusion || ''
                    params.circulationvalue2 = this.searchForm.conclusionForm.claimEvidence || ''
                    params.circulationvalue3 = this.searchForm.conclusionForm.templatetype.sort().join(',') || ''
                } else if (this.radio === '02') {
                    params.templatetype = this.radio
                    params.contentcode = this.searchForm.supplementForm.contentcode || ''
                    params.supplementarymode = this.searchForm.supplementForm.supplementarymode.sort().join(',') || ''
                    params.contents = this.searchForm.supplementForm.contents || ''
                    params.circulationvalue1 = this.searchForm.supplementForm.supplementtype || ''
                    params.circulationvalue2 = this.searchForm.supplementForm.datumType.sort().join(',') || ''
                    params.circulationvalue3 = this.searchForm.supplementForm.templatetype || ''
                } else if (this.radio === '03') {
                    this.params = this.searchForm.withdrawnForm
                } else {
                    params.templatetype = this.radio
                    params.contentcode = this.searchForm.notAccepteForm.contentcode || ''
                    params.supplementarymode = this.searchForm.notAccepteForm.supplementarymode.sort().join(',') || ''
                    params.contents = this.searchForm.notAccepteForm.contents || ''
                    params.circulationvalue1 = this.searchForm.notAccepteForm.notAccepteReason || ''
                    params.circulationvalue2 = this.searchForm.notAccepteForm.templatetype || ''
                }
                return params
            },
            delHandle(row, type) {
                this.$confirm('请确认是否进行删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let flag = ''
                    if (this.tableData.length === 1) {
                      flag = 'del'
                    }
                    deleteTemplate(row.contentcode).then(res => {
                        if (res.status === '1') {
                            this.$message.success('删除成功！')
                            this.getTemplate(this.radio, flag)
                            this.resetForm()
                        } else {
                            this.$message.error('删除失败！')
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除！'
                    });
                });
            },
            // 改变结论依据
            changeConclusion() {
                this.searchForm.conclusionForm.claimEvidence = ''
                switch(this.searchForm.conclusionForm.payConclusion) {
                    case '1' : this.claimEvidence = this.dict.conclusionreason; break
                    case '2' : this.claimEvidence = this.dict.conclusionreason; break
                    case '3' : this.claimEvidence = this.dict.conclusionreason2; break
                    case '4' : this.claimEvidence = this.dict.conclusionreason3; break
                    case '5' : this.claimEvidence = this.dict.conclusionreason4; break
                    case '6' : this.claimEvidence = this.dict.grantpremiumexemption; break
                    case '7' : this.claimEvidence = this.dict.grantpremiumexemption; break
                    default : this.claimEvidence = []
                }
                this.claimEvidence = [...this.claimEvidence]
            },
            changeConclusion1() {
                switch(this.searchForm.conclusionForm.payConclusion) {
                    case '1' : this.claimEvidence = this.dict.conclusionreason; break
                    case '2' : this.claimEvidence = this.dict.conclusionreason; break
                    case '3' : this.claimEvidence = this.dict.conclusionreason2; break
                    case '4' : this.claimEvidence = this.dict.conclusionreason3; break
                    case '5' : this.claimEvidence = this.dict.conclusionreason4; break
                    default : this.claimEvidence = []
                }
                this.claimEvidence = [...this.claimEvidence]
            },
            // 结论页码改变
            conclusionPageChange(val) {
                this.conclusionCurrentPage = val
                this.chooseRadio = null
                this.getTemplate('01')
            },
            // 结论每页条数改变
            conclusionSizeChange(val) {
                this.conclusionPageSize = val
                this.chooseRadio = null
                this.getTemplate('01')
            },
            // 补材页码改变
            supplementPageChange(val) {
                this.supplementCurrentPage = val
                this.chooseRadio = null
                this.getTemplate('02')
            },
            // 补材每页条数改变
            supplementSizeChange(val) {
                this.supplementPageSize = val
                this.chooseRadio = null
                this.getTemplate('02')
            },
            // 撤案页码改变
            withdrawnPageChange(val) {
                this.withdrawnCurrentPage = val
                this.getTemplate('03')
            },
            // 撤案每页条数改变
            withdrawnSizeChange(val) {
                this.withdrawnPageSize = val
                this.getTemplate('03')
            },
            // 不予页码改变
            notAcceptePageChange(val) {
                this.notAccepteCurrentPage = val
                this.chooseRadio = null
                this.getTemplate('04')
            },
            // 不予每页条数改变
            notAccepteSizeChange(val) {
                this.notAcceptePageSize = val
                this.chooseRadio = null
                this.getTemplate('04')
            },
            // 选中单选按钮反显在form表单
            handleCurrentChange(data) {
                this.chooseRadio = data.index
                let resetData = JSON.parse(JSON.stringify(data))
                if (this.radio === '01') {
                    this.searchForm.conclusionForm = resetData.row
                    this.getDutyList(resetData.row.insurancecode)
                    this.$set(this.searchForm.conclusionForm, 'supplementarymode', resetData.row.supplementarymode ? resetData.row.supplementarymode.split(',') : [])
                    this.$set(this.searchForm.conclusionForm, 'templatetype', resetData.row.circulationvalue3 ? resetData.row.circulationvalue3.split(',') : [])
                    this.$set(this.searchForm.conclusionForm, 'payConclusion', resetData.row.circulationvalue1)
                    this.$set(this.searchForm.conclusionForm, 'claimEvidence', resetData.row.circulationvalue2)
                    this.changeConclusion1()
                } else if (this.radio === '02') {
                    this.searchForm.supplementForm = resetData.row
                    this.$set(this.searchForm.supplementForm, 'supplementarymode', resetData.row.supplementarymode ? resetData.row.supplementarymode.split(',') : [])
                    this.$set(this.searchForm.supplementForm, 'datumType', resetData.row.circulationvalue2 ? resetData.row.circulationvalue2.split(',') : [])
                    this.$set(this.searchForm.supplementForm, 'supplementtype', resetData.row.circulationvalue1)
                    this.$set(this.searchForm.supplementForm, 'templatetype', resetData.row.circulationvalue3)
                } else if (this.radio === '04') {
                    this.searchForm.notAccepteForm = resetData.row
                    this.$set(this.searchForm.notAccepteForm, 'supplementarymode', resetData.row.supplementarymode ? resetData.row.supplementarymode.split(',') : [])
                    this.$set(this.searchForm.notAccepteForm, 'notAccepteReason', resetData.row.circulationvalue1)
                    this.$set(this.searchForm.notAccepteForm, 'templatetype', resetData.row.circulationvalue2)
                }
            }
        }
    }
</script>
<style scoped lang="scss">
  .item-width {
    width: 200px;
  }
  .el-radio .el-radio__label {
    font-weight: 400;
  }
  /deep/ .tab_newNotice .el-tabs__active-bar {
    background-color: transparent !important;
  }
  /deep/ .tab_newNotice .is-active {
   opacity: 0 !important;
  }
  /deep/ .el-select__tags-text {
    width: 39px;
    display: inline-block;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
  }
</style>
<style>
  .el-tooltip__popper{
    max-width: 80%;
  }
</style>
