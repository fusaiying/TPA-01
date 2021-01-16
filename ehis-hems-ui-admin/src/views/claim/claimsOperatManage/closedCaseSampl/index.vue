<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>抽检规则</span>
      </div>
      <el-form ref="formulateForm" :rules="rules" :model="formulateForm" style="padding-bottom: 30px;" label-width="126px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="作业组：" prop="group">
              <el-select v-model="formulateForm.group" clearable class="item-width" placeholder="请选择" @change="getOperatorHandle">
                <el-option v-for="item in groupList" :key="item.operationgroupno" :label="item.groupnoname" :value="item.operationgroupno"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="审核人：" prop="checkName">
              <el-select v-model="formulateForm.checkName" clearable class="item-width" placeholder="请选择">
                <el-option v-for="item in operatorList" :key="item.usercode" :label="item.username" :value="item.usercode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="结案日期：" prop="date">
              <el-date-picker
                v-model="formulateForm.date"
                value-format="yyyy-MM-dd"
                type="daterange"
                size="mini"
                style="width:240px"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="抽检金额：" prop="money">
              <el-input v-model="formulateForm.amount1" style="width: 95px;" clearable size="mini" placeholder="请输入"/> -
              <el-input v-model="formulateForm.amount2" style="width: 95px;" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="抽检比例：" prop="ratio">
              <el-input v-model="formulateForm.ratio" class="item-width" clearable size="mini" placeholder="请输入">
                <template slot="append">%</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="saveLoading"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="saveHandle"
          >保存</el-button>
          <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-table
        v-loading="ruleLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="pagingFlag ? newTableData : cusTableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" width="140" prop="rulecode" label="规则编号" show-overflow-tooltip/>
        <el-table-column align="center" width="140" prop="operationgroup" label="作业组" show-overflow-tooltip/>
        <el-table-column align="center" prop="checkName" label="审核人" show-overflow-tooltip/>
        <el-table-column align="center" width="360px" prop="date" label="结案日期区间" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-date-picker
              v-model="scope.row.date"
              value-format="yyyy-MM-dd"
              type="daterange"
              size="mini"
              range-separator="~"
              start-placeholder="开始日期"
              end-placeholder="结束日期"/>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="payMoney" label="赔付金额区间" show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.amount1 | payMoney(scope.row.amount2) }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="ratio" label="抽检比例" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.ratio | changeRatio }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="spotCheck(scope.row)">抽检</el-button>
            <el-button type="text" size="mini" @click="delRuleHandle(scope.row, scope.$index)">删除</el-button>
          </template>
        </el-table-column>>
      </el-table>
      <!--保存分页组件-->
      <el-pagination
        v-if="pagingFlag"
        :total="cusTableData.length"
        :current-page="cusPageInfo.currentPage"
        :page-size="cusPageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="cusSizeChange"
        @current-change="cusPageChange"/>
      <!--查询分页组件-->
      <el-pagination
        v-if="!pagingFlag"
        :total="cusTotalCount"
        :current-page="cusPageInfo.currentPage"
        :page-size="cusPageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="cusSizeChange"
        @current-change="cusPageChange"/>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>抽检结果</span>
        <span style="float: right;" >
          <el-button :loading="lauchLoading" :disabled="polTableData.length === 0" size="mini" type="primary" @click="lauchSpotCheck">发起抽检</el-button>
          <el-button size="mini" type="primary" @click="historHandle">抽检历史</el-button>
        </span>
      </div>
      <el-table
        v-loading="resultLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="polTableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" width="160" prop="claimno" label="赔案号" show-overflow-tooltip/>
        <el-table-column :formatter="queryApplySourcetype" align="center" width="80" prop="applicationsource" label="申请来源" show-overflow-tooltip/>
        <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
        <el-table-column :formatter="formatApplicaReason" align="center" prop="applicationReason" label="申请类别" show-overflow-tooltip/>
        <el-table-column width="120" align="center" prop="eventdate" label="出险日期" show-overflow-tooltip/>
        <el-table-column width="120" align="center" prop="applicationdate" label="受理日期">
          <template slot-scope="scope">
            {{ scope.row.applicationdate|formatApprai}}
          </template>
        </el-table-column>
        <el-table-column width="120" align="center" prop="closeddate" label="结案日期" show-overflow-tooltip/>
        <el-table-column :formatter="queryConclusion" align="center" prop="conclusion" label="赔付结论" show-overflow-tooltip/>
        <el-table-column align="center" prop="payableamount" label="赔付金额">
          <template slot-scope="scope">
            {{ scope.row.payableamount|numFilter}}
          </template>
        </el-table-column>
        <el-table-column width="100" align="center" prop="groupnoname" label="作业组" show-overflow-tooltip/>
        <el-table-column align="center" prop="operator" label="审核人" show-overflow-tooltip/>
        <el-table-column align="center" label="操作" width="80">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="delResultHandle(scope.row, scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <el-pagination
        :total="polTotalCount"
        :current-page="polPageInfo.currentPage"
        :page-size="polPageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="polSizeChange"
        @current-change="polPageChange"/>
    </el-card>
    <historModul v-model="historDia"/>
  </div>
</template>
<script>
import historModul from './modul/history'
import { paginData } from '@/utils/commenMethods.js'
import moment from 'moment'
import { getSamplingRules, startCheck, getSamplingResults, saveSamplingRules, delSamplingRule, updateCheck, delSamplingResults, getOperatorList, queryOperationgroupInfo, queryGroup, queryUser } from '@/api/closedCaseSampl/closedCaseSampl'
export default {
  dicts: ['apply_sourcetype', 'application_category', 'conclusion'],
  components: {
    historModul
  },
  filters: {
    changeRatio(value) {
      return value + '%'
    },
    payMoney(amount1, amount2) {
      return amount1 + '~' + amount2
    },
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '') {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    },
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
  },
  data() {
    const checkGroup = (rule, value, callback) => {
      if (value !== '' && !this.formulateForm.group) {
        return callback(new Error('请先选择作业组'))
      } else {
        callback()
      }
    }
    const chekMoney = (rule, value, callback) => {
      if (this.formulateForm.amount1 && this.formulateForm.amount2) {
        if (this.formulateForm.amount2.length > 10 || this.formulateForm.amount1.length > 10 || isNaN(Number(this.formulateForm.amount2)) || isNaN(Number(this.formulateForm.amount1))) {
          callback(new Error('请输入小于等于10位的数'))
        } else {
          if (parseFloat(this.formulateForm.amount2) < 0 || parseFloat(this.formulateForm.amount1) < 0) {
            callback(new Error('金额不可为负数'))
          } else {
            if (parseFloat(this.formulateForm.amount2) <= parseFloat(this.formulateForm.amount1)) {
              callback(new Error('后一个金额大于第一个金额的数值'))
            } else {
              callback()
            }
          }
        }
      } else {
        callback(new Error('金额不可为空'))
      }
    }
    const checkRatio = (rule, value, callback) => {
      if (value) {
        if (isNaN(Number(value))) {
          callback(new Error('请输入数字'))
        } else {
          if (value <= 0 || value > 100) {
            callback(new Error('抽检比例取值范围0 ~ 100'))
          } else {
            callback()
          }
        }
      } else {
        callback(new Error('抽检比例不可为空'))
      }
    }
    return {
      historDia: false,
      formulateForm: {
        group: '',
        checkName: '',
        date: [],
        amount1: '',
        amount2: '',
        ratio: ''
      },
      rules: {
        group: { required: true, message: '不能为空!', trigger: 'change' },
        ratio: { validator: checkRatio, required: true, trigger: 'blur' },
        money: { validator: chekMoney, required: true, trigger: 'blur' },
        // date: { required: true, message: '不能为空!', trigger: 'blur' },
        checkname: [{ validator: checkGroup, trigger: 'focus' }]
      },
      cusTableData: [],
      cusTotalCount: 0,
      cusPageInfo: {
        currentPage: 1,
        pageSize: 10
      },
      polTableData: [],
      polTotalCount: 0,
      polPageInfo: {
        currentPage: 1,
        pageSize: 10
      },
      ruleLoading: false,
      resultLoading: false,
      userGroup: '',
      groupList: [],
      operatorList: [],
      pagingFlag: false,
      saveLoading: false,
      lauchLoading: false
    }
  },
  computed: {
    newTableData() {
      // 这里做的前端分页，业务需要，查询规则列表获取到的数据进行后端分页，保存规则后获取的数据使用前端分页
      return paginData(this.cusTableData, this.cusPageInfo.currentPage, this.cusPageInfo.pageSize)
    }
  },
  mounted() {
    this.getGroup()
    this.getRuleList()
    this.getResultList()
  },
  methods: {
    // 申请来源
    queryApplySourcetype(row) {
        return this.dict.label.apply_sourcetype[row.applicationsource]
    },
    // 申请类别
    formatApplicaReason(row) {
        let reason = ''
        if (row.applicationReason) {
            row.applicationReason.split(',').map((item, i) => {
                this.dict.label.application_category[item] ? reason += `、${this.dict.label.application_category[item]}` : reason = ''
            })
        }
        return reason.slice(1)
    },
    // 赔付结论
    queryConclusion(row) {
        return this.dict.label.conclusion[row.conclusion]
    },
    // 获取操作人列表
    getOperatorHandle() {
      const params = {
        operationgroupno: this.formulateForm.group,
        pageno: 1,
        pagesize: 100
      }
      getOperatorList(params).then(res => {
        this.operatorList = res.data.list
      })
    },
    // 获取作业组列表
    // getGroup() {
    //   queryUser().then(response => {
    //     if (response.status === '1') {
    //       queryGroup(response.data.affiliation).then(res => {
    //         res.data.forEach(item => {
    //           if (response.data.affiliation === item.affiliation) {
    //             this.groupList = item.operationGroupDOList
    //           }
    //         })
    //       })
    //     }
    //   })
    // },
    getGroup() {
      queryOperationgroupInfo().then(res => {
        this.groupList = res.data
      })
    },
    // 保存规则
    saveHandle() {
      this.$refs.formulateForm.validate((valid) => {
        if (valid) {
          this.pagingFlag = true
          this.cusPageInfo.currentPage = 1
          const params = JSON.parse(JSON.stringify(this.formulateForm))
          params.closeddate1 = params.date ? params.date[0] : ''
          params.closeddate2 = params.date ? params.date[1] : ''
          params.amount1 = parseInt(params.amount1)
          params.amount2 = parseInt(params.amount2)
          this.saveLoading = true
          saveSamplingRules(params).then(res => {
            // 保存成功时会有返回的表格数据，查询抽检规则的接口仅在页面初始化时调用一次
            if (res.status === '1') {
              this.cusTotalCount = res.data.total
              res.data.forEach(item => {
                if (item.closeddate1 && item.closeddate2) {
                  item.date = []
                  item.date.push(item.closeddate1)
                  item.date.push(item.closeddate2)
                }
              })
              this.cusTableData = res.data
              this.$message.success('保存成功！')
            } else {
              if (res.data.errCode === 'CL102') {
                this.$message({ message: res.data.errMsg, type: 'warning' })
              } else {
                this.$message({ message: '保存失败！', type: 'error' })
              }
            }
            this.saveLoading = false
          })
        } else {
          return false
        }
      })
    },
    // 查询规则列表
    getRuleList() {
      this.ruleLoading = true
      const params = JSON.parse(JSON.stringify(this.cusPageInfo))
      getSamplingRules(params).then(res => {
        this.cusTableData = res.data.list
        this.cusTotalCount = res.data.total
        this.ruleLoading = false
        this.cusTableData = [...this.cusTableData]
      }).catch(res => {
        this.ruleLoading = false
      })
    },
    // 抽检
    spotCheck(row) {
      const params = JSON.parse(JSON.stringify(row))
      params.closeddate1 = params.date ? params.date[0] : ''
      params.closeddate2 = params.date ? params.date[1] : ''
      // if (params.closeddate1 && params.closeddate2) {
      params.ratio /= 100
      updateCheck(params).then(res => {
        if (res.status === '1') {
          this.$message.success('发起抽检成功！')
          this.polTotalCount = res.data.total
          this.polTableData = res.data.list
          this.getResultList()
        } else if (res.data.errCode === 'CL005') {
          this.$message.error(res.data.errMsg)
        } else {
          this.$message.error('发起抽检失败！')
        }
      })
      // } else {
      //   this.$message.warning('请选择结案区间！')
      // }
    },
    // 查询抽检结果
    getResultList() {
      this.resultLoading = true
      const params = JSON.parse(JSON.stringify(this.polPageInfo))
      getSamplingResults(params).then(res => {
        if (res.status === '1') {
          this.polTotalCount = res.data.total
          this.polTableData = res.data.list
        }
        this.polTableData = [...this.polTableData]
        this.resultLoading = false
      }).catch(res => {
        this.resultLoading = false
      })
    },
    // 删除抽检规则
    delRuleHandle(row, index) {
      const params = row.rulecode
      delSamplingRule(params).then(res => {
        if (res.status === '1') {
          this.$message.success('删除成功！')
          // 删除查询得到的数据，直接请求接口重新渲染，删除保存的到的数据，只删除表格数据的选中项
          if (!this.pagingFlag) {
            this.cusTableData.splice(index, 1)
            let cPage = Math.ceil(this.cusTotalCount / this.cusPageInfo.pageSize)
            if (cPage === this.cusPageInfo.currentPage && this.cusTableData.length === 0) {
              this.cusPageInfo.currentPage--
            }
            this.getRuleList()
          } else {
            this.cusTableData.splice(index + (this.cusPageInfo.currentPage - 1) * this.cusPageInfo.pageSize, 1)
            let cPage = Math.ceil(this.cusTableData.length / this.cusPageInfo.pageSize)
            if (cPage < this.cusPageInfo.currentPage) {
              this.cusPageInfo.currentPage = cPage
            }
          }
        } else {
          this.$message.error('删除失败！')
        }
      })
    },
    // 删除抽检结果
    delResultHandle(row, index) {
      const params = row.claimno
      delSamplingResults(params).then(res => {
        if (res.status === '1') {
          this.$message.success('删除成功！')
          this.polTableData.splice(index, 1)
          let cPage = Math.ceil(this.polTotalCount / this.polPageInfo.pageSize)
          if (cPage === this.polPageInfo.currentPage && this.polTableData.length === 0) {
            this.polPageInfo.currentPage--
          }
          this.getResultList()
        } else {
          this.$message.error('删除失败！')
        }
      })
    },
    // 发起抽检
    lauchSpotCheck() {
      this.lauchLoading = true
      startCheck().then(res => {
        if (res.status === '1') {
          this.$message.success('发起抽检成功！')
          this.getResultList()
        } else {
          this.$message.error('发起抽检失败！')
        }
        this.lauchLoading = false
      })
    },
    cusSizeChange(val) {
      this.cusPageInfo.pageSize = val
      if (!this.pagingFlag) {
        this.getRuleList()
      }
    },
    cusPageChange(val) {
      this.cusPageInfo.currentPage = val
      if (!this.pagingFlag) {
        this.getRuleList()
      }
    },
    polSizeChange(val) {
      this.polPageInfo.pageSize = val
      this.getResultList()
    },
    polPageChange(val) {
      this.polPageInfo.currentPage = val
      this.getResultList()
    },
    resetForm() {
      this.$refs.formulateForm.resetFields()
      this.formulateForm.amount1 = ''
      this.formulateForm.amount2 = ''
    },
    historHandle() {
      this.historDia = true
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
</style>
