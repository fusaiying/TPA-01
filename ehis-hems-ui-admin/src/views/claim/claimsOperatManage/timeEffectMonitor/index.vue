<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :rules="rules" :model="searchForm" style="padding-bottom: 30px;" label-width="90px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" class="item-width" clearable size="mini" placeholder="请输入"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="searchForm.applicationsource" clearable class="item-width" placeholder="请选择">
                 <el-option v-for="item in dict.apply_sourcetype" :label="item.label" :value="item.value" :key="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="slrq">
              <el-date-picker
                v-model="searchForm.slrq"
                class="item-width"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="作业组：" prop="group">
              <el-select v-model="searchForm.group" class="item-width" clearable placeholder="请选择" @change="getOperatorHandle">
                <el-option v-for="item in groupList" :key="item.operationgroupno" :label="item.groupnoname" :value="item.operationgroupno"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="operator">
              <el-select v-model="searchForm.operator" class="item-width" clearable placeholder="请选择">
                <el-option v-for="(item,ind) in operatorList" :key="ind" :label="item.username" :value="item.usercode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="案件状态：" prop="caseState">
              <el-select v-model="searchForm.caseState" clearable class="item-width" placeholder="请选择">
                 <el-option v-for="item in dict.queue_claim_status" :label="item.label" :value="item.value" :key="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoad"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-row style="margin-top: 5px;">
        <el-col :span="10">
          <el-card ref="pieChart" class="box-card" style="margin-right: 5px;">
            <div id="pieChart" style="width:100%;height: 400px;"/>
          </el-card>
        </el-col>
        <el-col :span="14">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>赔案清单</span>
              <el-button :loading="downLoading" style="float: right; width: 73px" size="mini" type="warning" @click="exportFile">下载</el-button>
            </div>
            <el-table
              v-loading="tableLoading"
              :data="tableData"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column width="150px" align="center" prop="claimno" label="赔案号">
                <template slot-scope="scope">
                  <el-button size="mini" type="text" @click="editHandle(scope.row, 'show')">{{ scope.row.claimno }}</el-button>
                </template>
              </el-table-column>
              <el-table-column :formatter="getApplySourcetype" align="center" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
              <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
              <el-table-column width="120px" :formatter="formatApplicaReason" align="center" prop="description" label="申请类别" show-overflow-tooltip/>
              <el-table-column width="120px" align="center" prop="applicationdate" label="受理日期" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span>{{scope.row.applicationdate|changeDate}}</span>
                </template>
              </el-table-column>
              <el-table-column :formatter="getClaimStatus" align="center" prop="status" label="案件状态" show-overflow-tooltip/>
              <el-table-column align="center" prop="operator" label="当前操作人" show-overflow-tooltip/>
              <el-table-column align="center" prop="limitation" label="案件时效" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span v-if="scope.row.limitation >= 0">{{scope.row.limitation}}<span v-if="scope.row.limitation!== ''">天</span></span>
                  <span v-else style="color: red">{{scope.row.limitation}}<span v-if="scope.row.limitation!== ''">天</span></span>
                </template>
              </el-table-column>
            </el-table>
            <!--分页组件-->
            <el-pagination
              :total="total"
              :current-page="pageInfo.page"
              :page-sizes="pageSizes"
              :page-size="pageInfo.pageSize"
              style="margin-top: 8px; text-align: right;"
              layout="sizes, prev, pager, next"
              @size-change="sizeChange"
              @current-change="pageChange"
            />
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import echarts from 'echarts'
import moment from 'moment'
import { getClaimList, getPieInfo, getOperatorList, queryOperationgroupInfo, getGroupList, exportFile } from '@/api/timeEffect/timeEffect.js'
import { atLeastOne, _debounce, changeDate } from '@/utils/commenMethods.js'
import { downloadFile } from '@/utils/index'
import Cookies from 'js-cookie'
import jwt from 'jsonwebtoken'
import {encrypt} from "@/utils/rsaEncrypt"

export default {
  dicts: ['sn_management_operationgroup_limitation', 'apply_sourcetype', 'claim_status', 'applicationreason','queue_claim_status'],
  filters: {
    changeDate: changeDate
  },
  data() {
    const checkGroup = function(rules, value, callback) {
      if (value) {
        if (this.searchForm.group === '') {
          callback(new Error('请先选择作业组'))
        } else {
          callback()
        }
      }
    }
    return {
      searchForm: {
        slrq: [moment().subtract(29, 'days').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')],
        // slrq: [moment().subtract(1, 'months').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')],
        claimno: '',
        applicationsource: '',
        group: '',
        operator: '',
        caseState: ''
      },
      tableData: [],
      total: 0,
      pageSizes: [5,10, 20, 30, 50,100],
      pageInfo: {
        page: 1,
        pageSize: 10
      },
      groupList: [], // 作业组
      applicationSourceList: [], // 申请涞源
      operatorList: [], // 操作人
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7// 如果没有后面的-8.64e7就是不可以选择今天的
        }
      },
      tableLoading: true,
      dataList: [],
      nameList: [],
      exportLoading: false,
      rules: {
        operator: [{ validator: checkGroup, trigger: 'focus' }]
      },
      showList: [
          {name: '受理', value: 0},
          {name: '录入', value: 0},
          {name: '初审', value: 0},
          {name: '审核', value: 0},
          {name: '复核', value: 0},
          {name: '抽检', value: 0},
          {name: '审批', value: 0},
          {name: '分类', value: 0},
          {name: '已结案', value: 0},
          {name: '待给付', value: 0},
          {name: '已撤件', value: 0},
          {name: '已给付', value: 0},
          {name: '不予受理', value: 0},
          {name: '扫描中', value: 0}
      ],
      statusList: [
          {code: '01', status: 'accept', name: '受理'},
          {code: '02', status: 'accept', name: '受理'},
          {code: '05', status: 'input', name: '录入'},
          {code: '06', status: 'input', name: '录入'},
          {code: '07', status: 'firstTrial', name: '初审'},
          {code: '08', status: 'firstTrial', name: '初审'},
          {code: '09', status: 'review', name: '审核'},
          {code: '10', status: 'review', name: '审核'},
          {code: '11', status: 'complex', name: '复核'},
          {code: '12', status: 'complex', name: '复核'},
          {code: '13', status: 'soprt-check', name: '抽检'},
          {code: '14', status: 'soprt-check', name: '抽检'},
          {code: '15', status: 'correct', name: '审批'},
          {code: '16', status: 'correct', name: '审批'},
          {code: '03', status: 'review', name: '分类'},
          {code: '04', status: 'review', name: '分类'},
          {code: '17', status: 'review', name: '已结案'},
          {code: '18', status: 'review', name: '待给付'},
          {code: '19', status: 'review', name: '已撤件'},
          {code: '20', status: 'review', name: '已给付'},
          {code: '21', status: 'review', name: '不予受理'},
          {code: '22', status: 'review', name: '扫描中'}
      ],
      colorList: [],
      pieList: [],
      listParams: {},
      searchLoad: false,
      downLoading: false
    }
  },
  mounted() {
    // this.getList()
    // this.getPieData()
    // this.getRandomColor()
    this.getGroup()
    this.getCurrentGroup()
    // 监听屏幕窗口尺寸
    const that = this
    window.onresize = function(){
        if (!that.timer) {
            that.timer = true
            setTimeout(function () {
                if(that.charts){
                    that.charts.resize()
                }
                that.timer = false
            }, 10)
        }
    }
  },
  methods: {
    // 申请原因
    formatApplicaReason(row) {
        let reason = ''
        if (row.description) {
            row.description.split(',').map((item, i) => {
                this.dict.label.applicationreason[item] ? reason += `|${this.dict.label.applicationreason[item]}` : reason = ''
            })
        }
        return reason.slice(1)
    },
    getApplySourcetype(row) {
      return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    getClaimStatus(row) {
      return this.dict.label.claim_status[row.status]
    },
    // 获取操作人列表
    getOperatorHandle() {
      const params = {
        operationgroupno: this.searchForm.group,
        pageno: 1,
        pagesize: 100
      }
      if (!this.searchForm.group) {
        this.$set(this.searchForm, 'operator', '')
      }
      getOperatorList(params).then(res => {
        if (res.status === '1') {
          this.operatorList = res.data.list
        } else {
          this.operatorList = []
        }
      })
    },
    // 获取当前用户所属操作组
    getCurrentGroup() {
      const jwt = require('jsonwebtoken')
      const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
      getGroupList(str.user_name).then(res => {
        this.currentGroup = res.data.operationgroupno
        this.searchForm.group = this.currentGroup
        this.getList(true)
        this.getPieData()
        this.getOperatorHandle()
      })
    },
    // 获取作业组列表
    getGroup() {
      queryOperationgroupInfo().then(res => {
        this.groupList = res.data
      })
    },
    changeData() {
      this.pieList = []
      this.showList.forEach(item => {
        item.value = 0
      })
      this.statusList.forEach(item => {
        this.nameList.forEach(val => {
          if (item.code === val.name) {
            val.name = item.name
          }
        })
        this.dataList.forEach(val => {
          if (item.code === val.name) {
            this.showList.forEach(nval => {
              if (nval.name === item.name) {
                nval.value += val.value
              }
            })
          }
        })
      })
      this.showList.forEach(item => {
        if (item.value != 0) {
          this.pieList.push(item)
        }
      })
    },
    getRandomColor() {
      this.colorList = []
      for (let j = 0; j < this.showList.length; j ++) {
        let color = '#'
        for (let i = 0; i < 6; i++) {
            color += parseInt(Math.random() * 16).toString(16)
        }
        if (this.colorList.find(item => item == color)) {
            this.pieList.length += 1
        } else {
            this.colorList.push(color)
        }
      }
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
      // this.$set(this.searchForm, 'slrq', [moment().subtract(29, 'days').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')])
      // this.$set(this.searchForm, 'slrq', [moment().subtract(1, 'months').format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')])
      this.$set(this.searchForm, 'slrq', [])
      this.operatorList = []
      // this.getCurrentGroup()
    },
    searchHandle: _debounce(function(){
      const checkResult = atLeastOne(this.searchForm)
      this.pageInfo.page = 1
      if (!checkResult) {
        this.$message.warning('至少录入一项查询条件！')
      } else {
        this.getList(true)
        this.getPieData()
      }
    },500),
    pageChange(val) {
      this.pageInfo.page = val
      this.getList()
    },
    getPieData() {
      this.dataList = []
      this.nameList = []
      const params = this.getParams()
      params.currentPage = this.pageInfo.page
      params.pageSize = this.pageInfo.pageSize
      this.searchLoad = true
      getPieInfo(params).then(res => {
        if (res.status === '1' && !res.data) {
          this.$message.success('未查询到列表信息！')
        } else if (res.status !== '1') {
          this.$message.error('查询失败！' + res.data.errMsg)
        }
        res.data.forEach(item => {
          const eachData = { code: item.status, value: item.number, name: item.status }
          this.dataList.push(eachData)
          this.nameList.push({ value: item.number, name: item.status })
        })
        this.charts = echarts.init(document.getElementById('pieChart'))
        this.changeData()
        this.charts.setOption({
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'horizontal',
            // left: 'left',
            bottom: 10,
            left: 'center',
            // data: this.nameList
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '55%',
              center: ['50%', '35%'],
              data: this.pieList,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ],
          color: ['#ef5b9c','#deab8a','#b2d235','#2a5caa','#cde6c7','#843900','#ed1941','#fcaf17','#c77eb5','#f15a22','#ffe600','#a1a3a6','#ae6642','#8552a1']
        })
      }).finally(res => {
        this.searchLoad = false
      })
    },
    sizeChange(val) {
      this.pageInfo.pageSize = val
      this.getList()
    },
    getParams() {
      const params = JSON.parse(JSON.stringify(this.searchForm))
      if (this.searchForm.group === '000000000002') {
        params.group = ''
      }
      if (this.searchForm.slrq) {
        params.applicationdateStart = this.searchForm.slrq ? this.searchForm.slrq[0] : ''
        params.applicationdateEnd = this.searchForm.slrq[1] ? this.searchForm.slrq[1] : ''
      }
      return params
    },
    // 下载参数
    getExportParams() {
      const params = JSON.parse(JSON.stringify(this.searchForm))
      if (this.searchForm.slrq) {
        params.applicationdateStart = this.searchForm.slrq[0]
        params.applicationdateEnd = this.searchForm.slrq[1]
      }
      return params
    },
    getList(flag) {
      this.searchLoad = true
      let params
      if (flag) {
        params = this.getParams()
        this.listParams = JSON.parse(JSON.stringify(params))
      } else {
        params = JSON.parse(JSON.stringify(this.listParams))
      }
      params.currentPage = this.pageInfo.page
      params.pageSize = this.pageInfo.pageSize
      getClaimList(params).then(res => {
        if (res.status === '1') {
          this.tableData = res.data.list
          this.tableData.map((item) =>{
            if (item.limitation!== null && item.limitation !== '' && item.limitation !== undefined) {
              item.limitation = parseFloat(item.limitation).toFixed(0)
            } else {
              item.limitation = ''
            }
          })
          this.total = res.data.total
          this.tableLoading = false
          if (!res.data.total) {
            let that = this
              setTimeout(function() {
                that.$message.warning('未查询到信息！')
              }, 100)
          }
        }
      }).finally(res => {
        this.tableLoading = false
        this.searchLoad = false
      })
    },
    // 下载
    exportFile() {
      this.exportLoading = true
      this.downLoading = true
      exportFile(this.getExportParams())
        .then(response => {
          downloadFile(response, '赔案清单下载', 'xlsx')
        })
        .catch(error => {
          this.$message({ message: '赔案清单下载异常！', type: 'error' })
        }).finally(() => {
          this.exportLoading = false
          this.downLoading = false
        })
    },
    editHandle(row, status) {
        // statusList
        // row.status
        // console.log(this.statusList.find(item => item.code === row.status))
        let data
        if (row.status === '13' || row.status === '14') {
            data = encodeURI(
                JSON.stringify({
                    claimno: encrypt(row.claimno),
                    status,
                    node: 'spotCheck',
                    conclusionType:'01',
                    detailno:row.detailno,
                    casedetailFlag: true,
                })
            )
        } else {
            console.log(this.statusList.find(item => item.code === row.status)['status'])
            data = encodeURI(
                JSON.stringify({
                    claimno: encrypt(row.claimno),
                    status,
                    casedetailFlag: true,
                    node: this.statusList.find(item => item.code === row.status)['status']
                })
            )
        }
        const newpage = this.$router.resolve({
          name: 'casedetail',
          params:{},
          query:{ data }
        })
        window.open(newpage.href, '_blank');
        // this.$router.push({
        //     path: '/claims-handle/com-handle',
        //     query: {
        //         data
        //     }
        // })
    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
  /* color: rgb(211, 63, 63); */
}
</style>
