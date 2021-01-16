
<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>案件分配规则</span>
      </div>
      <el-form ref="tableForm" :rules="tableFormRules" :model="baseForm" style="padding-bottom: 30px;" size="mini">
        <el-row>
          <el-table
            ref="table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.tableData"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column label="规则编码" prop="code" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{ scope.row.routerulesno }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="险种/比例" prop="ruletype" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.ruletype'" :rules="tableFormRules.ruletype">
                  <el-select v-model="scope.row.ruletype" placeholder="请选择" @change="scope.row.rulevalue1 = ''">
                    <el-option label="险种" value="01"/>
                    <el-option :disabled="baseForm.tableData.some(item => item.ruletype === '02')" label="比例" value="02"/>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.ruletype == '01' ? '险种' : '比例' }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="险种/比例信息" prop="policy" width="230" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.policy'">
                  <el-select
                    v-if="scope.row.ruletype === '01'"
                    v-model="scope.row.rulevalue1"
                    :remote-method="remoteMethod"
                    placeholder="请选择险种"
                    filterable
                    remote
                    multiple
                    collapse-tags
                    @change="upRisk(scope.row,scope.$index)">
                    <el-option
                      v-for="(item,index) in riskList"
                      :label="item.RISKNAME"
                      :value="item.RISKCODE"
                      :key="index">
                      <span style="float: left">{{ item.RISKCODE }}</span>
                      <span style="float: right; color: #8492a6; font-size: 13px">{{ item.RISKNAME }}</span>
                    </el-option>
                  </el-select>
                  <el-input style="width: 150px" v-model="scope.row.rulevalue1" placeholder="请输入比例" size="mini" v-else @input="upRatio(scope.row,scope.$index)">
                    <template slot="append">%</template>
                  </el-input>
                </el-form-item>
                <el-form-item v-else>
                  <span v-if="scope.row.ruletype === '01'">{{ scope.row.rulevalue2 }}</span>
                  <span v-else>{{ scope.row.rulevalue1 + '%' }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="规则状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.status'" :rules="tableFormRules.status">
                  <el-select v-model="scope.row.status" placeholder="请选择" @change="stateChange(scope.row, scope.$index)">
                    <el-option label="有效" value="1"/>
                    <el-option label="无效" value="2"/>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.status == 1 ? '有效' : '无效' }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="创建日期" width="130" prop="createdate" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{ scope.row.createdate }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="失效日期" width="160" prop="endDate" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.endDate'" :rules="tableFormRules.endDate">
                  <el-date-picker
                    v-model="scope.row.enddate"
                    :disabled="scope.row.status === '1'"
                    type="date"
                    placeholder="选择日期"
                    value-format="yyyy-MM-dd"
                    style="width: 150px;"
                    @change="enddataChange(scope.row)"/>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.enddate }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="120" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button v-if="scope.row.temp" type="text" size="mini" @click="delHandle(scope.$index, scope.row)">删除</el-button>
                  <el-button v-else type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addRowHandle"> + 添加</el-button>
        </el-row>
      </el-form>
      <div class="bottom clearfix" style="text-align: right;">
        <el-button :loading="saveLoad" type="primary" size="mini" class="button" @click="saveHandle">保存</el-button>
        <el-button type="primary" size="mini" class="button" @click="goBack">返回</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
import { getRouterulesss, addRouterulesss, getGroupNo, queryCoreRiskCodeDetaill } from '@/api/groupManage/groupWork.js'
import moment from 'moment'
import { _debounce } from '@/utils/commenMethods.js'

export default {
  data() {
    const checkPolicy = (rule, value, callback) => {
      if (!value) {
        callback(new Error('不可为空'))
      } else {
        const str = /^\d+(\.\d{0,2})?$/
        if (value >= 0 || value <= 100) {
          if (str.test(value)) {
            callback()
          } else {
            callback(new Error('最多两位小数'))
          }
        } else {
          callback(new Error('请输入大于0小于等于100的数字'))
        }
      }
    }
    return {
      baseForm: {
        tableData: []
      },
      tableFormRules: {
        status: { required: true, message: '不能为空!', trigger: 'change' },
        riskCode: { required: true, message: '不能为空!', trigger: 'change' },
        enddate: { required: true, message: '不能为空!', trigger: 'change' },
        policy: { validator: checkPolicy, trigger: 'blur', require: true }
      },
      address: [{
        value: 'zhinan',
        label: '指南',
        children: [{
          value: 'shejiyuanze',
          label: '设计原则',
          children: [{
            value: 'yizhi',
            label: '一致'
          }]
        }]
      }],
      rulevalueArr: [],
      editForm: {},
      editSta: false,
      workNumber: '',
      saveArr: [],
      riskList: [],
      saveLoad: false
    }
  },
  methods: {
    upRisk(row, index) {
      this.$set(this.baseForm.tableData[index], 'rulevalue1', row.rulevalue1)
      this.baseForm.tableData = [...this.baseForm.tableData]
    },
    upRatio(row, index) {
      this.$set(this.baseForm.tableData[index], 'rulevalue1', row.rulevalue1)
      this.baseForm.tableData = [...this.baseForm.tableData]
    },
    remoteMethod(query) {
      query = query.replace(/(^\s*)|(\s*$)/g, '')
      this.riskList = [...this.rulevalueArr.filter(array => array.RISKCODE.match(query)), ...this.rulevalueArr.filter(array => array.RISKNAME.match(query))]
    },
    enddataChange(row) {
      // this.$set(row, 'enddate', row.enddate)
      this.baseForm.tableData = [...this.baseForm.tableData]
      // this.$forceUpdate()
    },
    stateChange(row, index) {
      if (row.status === '1') {
        this.$set(row, 'enddate', '')
      } else {
        this.$set(row, 'enddate', moment(new Date()).format('YYYY-MM-DD'))
      }
      this.baseForm.tableData = [...this.baseForm.tableData]
    },
    // 添加一行数据
    addRowHandle() {
      const data = '02'
      getGroupNo(data).then(res => {
        this.workNumber = res.data
        const field = {
          routerulesno: this.workNumber,
          status: '1',
          id: '',
          temp: true,
          createdate: moment(new Date()).format('YYYY-MM-DD')
        }
        this.baseForm.tableData.push(field)
        this.baseForm.tableData = [...this.baseForm.tableData]
        this.$forceUpdate()
        setTimeout(() => { this.$refs.table.setCurrentRow(field) }, 10)
      })
    },
    // 删除当前行
    delHandle(index, row) {
      this.$confirm(`是否确定删除?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.baseForm.tableData.splice(index, 1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    // 获取案件号
    getGroupWorkNo() {
      const data = '02' // 作业组号=‘01’ 规则编码 = ‘02’
      getGroupNo(data).then(res => {
        if (res.status == '1') {
          this.workNumber = res.data
        } else {
          this.$message.error('查询规则编码异常！')
        }
      })
    },
    // 编辑当前列
    editHandle(i, row) {
      this.baseForm.tableData = [...this.baseForm.tableData]
      this.baseForm.tableData[i].id = ''
      if (this.baseForm.tableData[i].ruletype === '01') {
        this.$set(this.baseForm.tableData[i], 'rulevalue1', this.baseForm.tableData[i].rulevalue1 ? this.baseForm.tableData[i].rulevalue1.split(',') : [])
      } else {
        this.$set(this.baseForm.tableData[i], 'rulevalue1', this.baseForm.tableData[i].rulevalue1 || this.baseForm.tableData[i].rulevalue1 == 0 ? this.baseForm.tableData[i].rulevalue1 : '')
      }
      this.editForm = row
    },
    goBack() {
      this.$router.go(-1)
    },
    // 保存
    saveHandle: _debounce(function() {
      this.$refs.tableForm.validate((valid) => {
        if (valid) {
          this.saveArr = this.baseForm.tableData.filter((item, index) => {
            return moment(item.enddate).format('YYYY-MM-DD') == moment(item.enddate).format('YYYY-MM-DD')
            item.routerulesno == item.routerulesno
            item.ruletype == item.ruletype
            item.rulevalue1 == item.rulevalue1
            item.status == item.status
            item.createdate == item.createdate
          })
          this.saveArr = JSON.parse(JSON.stringify(this.saveArr))
          let emptyFlag = false
          let valueFlag = false
          let dateFlag = false
          let statusFlag = false
          // 处理险种/比例信息格式
          this.saveArr.forEach(item => {
            // 校验
            if (this.changeTime(item.enddate) && this.changeTime(item.enddate) < this.changeTime(item.createdate)) {
              dateFlag = true
            }
            if (item.status === '2' && !this.changeTime(item.enddate)) {
              statusFlag = true
              return
            }
            if (item.rulevalue1 && item.rulevalue1.length === 0) {
              emptyFlag = true
              return
            }
            if (!item.ruletype) {
              emptyFlag = true
              return
            }
            if (typeof item.rulevalue1 === 'object') {
              item.rulevalue1 = item.rulevalue1.join(',')
            }
            if (item.ruletype === '02') {
              if (parseInt(item.rulevalue1) < 0 || parseInt(item.rulevalue1) > 100) {
                valueFlag = true
                return
              } else {
                const str = /^\d+(\.\d{0,2})?$/
                if (!str.test(item.rulevalue1)) {
                  valueFlag = true
                  return
                }
              }
              const m = Math.pow(10, 3)
              const value = item.rulevalue1 / 100
              item.rulevalue1 = parseInt(value * m, 10) / m
            }
          })
          if (dateFlag) {
            this.$message.warning('失效日期不可小于创建日期！')
            return
          }
          if (statusFlag) {
            this.$message.warning('规则状态设置无效时，请选择失效日期！')
            return
          }
          if (emptyFlag) {
            this.$message.warning('险种/比例、险种/比例信息不可为空！')
            return
          }
          if (valueFlag) {
            this.$message.warning('比例信息允许录入0~100的数字，最多两位小数！')
            return
          }
          const params = {
            operationgroupRouterulesVOList: this.saveArr,
            'operationgroupno': this.$route.query.operationgroupno
          }
          this.saveLoad = true
          addRouterulesss(params).then(res => {
            if (res.status == '1') {
              this.$message.success('保存成功！')
              this.getList()
            } else {
              this.$message.error('保存异常！')
            }
            this.saveLoad = false
          })
        } else {
          return false
        }
      })
    }, 500),
    // 获取时间戳
    changeTime(date) {
      date = date ? new Date(Date.parse(date.replace(/-/g, "/"))) : '';
      return date ? date.getTime() : date;
    },
    // 获取案件页列表
    getList() {
      const data = {
        'operationgroupno': this.$route.query.operationgroupno,
        'pageno': 1,
        'pagesize': 10
      }
      getRouterulesss(data).then(res => {
        if (res.status == '1') {
          this.baseForm.tableData = res && res.data && res.data.list
          this.baseForm.tableData.map((data, index) => {
            data.id = index + 1
            if (data.ruletype === '02') {
              const m = Math.pow(10, 3)
              data.rulevalue1 = data.rulevalue1 ? parseFloat(data.rulevalue1 * 100 * m, 10) / m : data.rulevalue1
            }
          })
        } else {
          this.$message.error('查询案件列表异常！')
        }
      })
    },
    addRouterulesssList() {},
    // 获取案件信息
    queryCoreRiskCodeDetaill() {
      queryCoreRiskCodeDetaill().then(res => {
        if (res.status == '1') {
          this.rulevalueArr = res.data || []
          this.riskList = this.rulevalueArr
        } else {
          this.$message.error('查询险种信息错误！')
        }
      })
    }
  },
  mounted() {
    this.getList()
    this.queryCoreRiskCodeDetaill()
  }
}
</script>
<style scoped>
  .form-span {
    display: inline-block;
    margin-bottom: 10px;;
  }
  /deep/ .el-select__tags-text {
    width: 39px;
    display: inline-block;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
  }
  .el-form-item--mini {
    margin-bottom: 0;
    padding-bottom: 0;
  }
  .el-form-item--mini /deep/ .el-form-item__content {
    height: 28px;
  }
</style>
