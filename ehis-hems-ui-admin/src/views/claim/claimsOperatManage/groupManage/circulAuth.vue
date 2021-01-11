
<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>权限列表</span>
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
            style="width: 100%;"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" align="center"/>
            <el-table-column label="节点编码" prop="node" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{ scope.row.nodecode }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="节点名称" prop="code" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{ scope.row.nodename }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="时效设置" width="200px" align="center" class-name="date" prop="timeout">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.limitation1'">
                  <el-row>
                    <el-col :span="11">
                      <el-form-item :prop="'tableData.' + scope.$index + '.limitation1'" :rules="tableFormRules.limitation1">
                        <el-input v-model="scope.row.limitation1" maxlength="100" placeholder="请输入" size="mini"/>
                      </el-form-item>
                    </el-col>
                    <el-col :span="2">
                      <span>*</span>
                    </el-col>
                    <el-col :span="11">
                      <el-form-item :prop="'tableData.' + scope.$index + '.limitation2'" :rules="tableFormRules.limitation2">
                        <el-input v-model="scope.row.limitation2" maxlength="100" placeholder="请输入" size="mini"/>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.limitation1 }}*{{ scope.row.limitation2 }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="流转条件" width="560px" align="center">
              <template slot-scope="scope">
                <el-row v-if="scope.row.nodecode === 'C005' || scope.row.nodecode === 'C006' || scope.row.nodecode === 'C007' || scope.row.nodecode === 'C008'">
                  <el-col :span="8">
                    <el-form-item v-if="!scope.row.id">
                      <el-input v-model="scope.row.ratio" :disabled="scope.row.nodecode === 'C005' || scope.row.nodecode === 'C006' || scope.row.nodecode === 'C007'" style="width: 160px" placeholder="请输入比例" size="mini" @input="ratioChange">
                        <template slot="append">%</template>
                      </el-input>
                    </el-form-item>
                    <el-form-item v-else>
                      <span>{{ scope.row.ratio || scope.row.ratio == '0' ? scope.row.ratio + '%' : ''}}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.money'" :rules="tableFormRules.money">
                      <el-input v-model="scope.row.money" style="width: 130px;" placeholder="请输入金额" size="mini"/>
                    </el-form-item>
                    <el-form-item v-else>
                      <span>{{ scope.row.money }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.risk'">
                      <el-select
                        v-model="scope.row.risk"
                        :remote-method="remoteMethod"
                        multiple
                        filterable
                        collapse-tags
                        style="margin-left: 20px;"
                        remote
                        placeholder="请选择险种">
                        <el-option v-for="(item,index) in riskList" :label="item.RISKNAME" :value="item.RISKCODE" :key="index">
                          <span style="float: left">{{ item.RISKCODE }}</span>
                          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.RISKNAME }}</span>
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item v-else>
                      <el-tooltip :content="scope.row.riskname" class="item" effect="dark" placement="top-start">
                        <span style="display:inline-block;width:140px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">{{ scope.row.riskname }}</span>
                      </el-tooltip>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row v-else>N/A</el-row>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="100" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button v-if="scope.row.temp" type="text" size="mini" @click="delHandle(scope.$index, scope.row)">删除</el-button>
                  <el-button v-else type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
          <!-- <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addRowHandle"> + 添加</el-button> -->
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
import { getGroupLimitations, addGroupLimitations, queryUser, queryCoreRiskCodeDetaill } from '@/api/groupManage/groupWork.js'
import { patch } from '../../../../utils/commenMethods'
export default {
  data() {
    const checkLimitation1 = (rule, value, callback) => {
      var reg = new RegExp('^[0-9]*$')
      if (value) {
        if (!reg.test(value)) {
          callback(new Error('格式错误！'))
        } else {
          if (value > 30) {
            callback(new Error('不能大于30！'))
          } else {
            callback()
          }
        }
      } else {
        callback(new Error('不能为空！'))
      }
    }
    const checkLimitation2 = (rule, value, callback) => {
      var reg = new RegExp('^[0-9]*$')
      if (value) {
        if (!reg.test(value)) {
          callback(new Error('格式错误！'))
        } else {
          if (value > 24) {
            callback(new Error('不能大于24！'))
          } else {
            callback()
          }
        }
      } else {
        callback(new Error('不能为空！'))
      }
    }
    return {
      baseForm: {
        tableData: []
      },
      tableFormRules: {
        limitation1: { validator: checkLimitation1, trigger: 'change' },
        limitation2: { validator: checkLimitation2, trigger: 'change' },
        money: { validator: checkLimitation2, trigger: 'change' }
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
      addArr: [], // 表格选中数据
      saveArr: [], // 编辑传参数零时数组
      userArr: [], // 操作用户
      nodecodeArr: [], // 已有节点
      hasselectList: [],
      riskList: [],
      saveLoad: false
    }
  },
  mounted() {
    this.limitationsList()
    // this.getUserList()
    this.queryCoreRiskCodeDetaill()
  },
  methods: {
    ratioChange() {
      // this.baseForm.tableData = [...this.baseForm.tableData]
    },
    remoteMethod(query) {
      query = query.replace(/(^\s*)|(\s*$)/g, '')
      this.riskList = [...this.rulevalueArr.filter(array => array.RISKCODE.match(query)), ...this.rulevalueArr.filter(array => array.RISKNAME.match(query))]
    },
    // 添加一行数据
    addRowHandle() {
      const field = {
        node: '',
        nodeName: '',
        timeSet: '',
        rate: '',
        // ...具体字段根据后端定义的添加
        id: '',
        timeout: '',
        money: '',
        temp: true
      }
      this.baseForm.tableData.push(field)
      setTimeout(() => { this.$refs.table.setCurrentRow(field) }, 10)
    },
    queryCoreRiskCodeDetaill() {
      queryCoreRiskCodeDetaill().then(res => {
        if (res.status == '1') {
          this.rulevalueArr = res.data || []
          this.riskList = this.rulevalueArr
        } else {
          this.$message.error('查询险种信息错误！')
        }
      })
    },
    // 删除一行数据
    delHandle(index, row) {
      this.$confirm(`是否确定取消?`, '提示', {
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
    // 编辑当前行
    editHandle(i, row) {
      this.$set(this.baseForm.tableData[i], 'risk', row.riskcode ? row.riskcode.split(',') : '')
      this.$set(this.baseForm.tableData[i], 'id', '')
    },
    goBack() {
      this.$router.go(-1)
    },
    inputTimeout(index, value) {
      this.$set(this.baseForm.tableData[index], 'timeout', value)
      this.baseForm.tableData = [...this.baseForm.tableData]
      this.$nextTick(() => {
        this.baseForm.tableData.forEach(row => {
          if (this.hasselectList.indexOf(row.nodecode) >= 0) {
            this.$refs.table.toggleRowSelection(row, true)
          }
        })
      })
      this.$forceUpdate()
    },
    // 查询权限列表
    limitationsList() {
      const data = {
        'operationgroupno': this.$route.query.operationgroupno,
        'pageno': 1,
        'pagesize': 10
      }
      getGroupLimitations(data).then(res => {
        if (res.status == '1') {
          let arr = []
          this.nodecodeArr = []
          arr = res && res.data && res.data.operationgroupLimitationVOList || []
          this.baseForm.tableData = res && res.data && res.data.operationgroupLimitationVOList || []
          this.hasselectList = []
          arr.length ? arr.map((item, inx) => {
            this.nodecodeArr.push(item.nodecode)
            this.hasselectList.push(item.nodecode)
          }) : []
          // 接口初始化数据显示
          if (this.nodecodeArr.indexOf('C001') == -1) {
            const item = { id: 1, nodecode: 'C001', nodename: '受理', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(0, 0, item)
          }
          if (this.nodecodeArr.indexOf('C009') == -1) {
            const item = { id: 1, nodecode: 'C009', nodename: '扫描', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(1, 0, item)
          }
          if (this.nodecodeArr.indexOf('C002') == -1) {
            const item = { id: 1, nodecode: 'C002', nodename: '分类', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(2, 0, item)
          }
          if (this.nodecodeArr.indexOf('C003') == -1) {
            const item = { id: 1, nodecode: 'C003', nodename: '录入', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(3, 0, item)
          }
          if (this.nodecodeArr.indexOf('C004') == -1) {
            const item = { id: 1, nodecode: 'C004', nodename: '初审', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(4, 0, item)
          }
          if (this.nodecodeArr.indexOf('C005') == -1) {
            const item = { id: 1, nodecode: 'C005', nodename: '审核', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(5, 0, item)
          }

          if (this.nodecodeArr.indexOf('C006') == -1) {
            const item = { id: 1, nodecode: 'C006', nodename: '复核', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(6, 0, item)
          }
          if (this.nodecodeArr.indexOf('C007') == -1) {
            const item = { id: 1, nodecode: 'C007', nodename: '抽检', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(7, 0, item)
          }
          if (this.nodecodeArr.indexOf('C008') == -1) {
            const item = { id: 1, nodecode: 'C008', nodename: '审批', limitation1: 1, limitation2: 24 }
            this.baseForm.tableData.splice(8, 0, item)
          }
          this.$nextTick(() => {
            this.baseForm.tableData.forEach(row => {
              // 处理比例格式
              if (row.ratio) {
                const m = Math.pow(10, 3)
                const value = row.ratio * 100
                row.ratio = parseInt(value * m, 10) / m
              }
              if (this.hasselectList.indexOf(row.nodecode) >= 0) {
                this.$refs.table.toggleRowSelection(row, true)
              }
            })
            console.log(this.baseForm.tableData,7878)
          })
        } else {
          this.$message.error('查询权限列表异常！')
        }
      })
    },
    // 保存当前编辑内容
    saveHandle() {
      this.$refs.tableForm.validate((valid) => {
        if (valid) {
          if (this.addArr.length == 0) {
            this.$message.warning('未选择要保存的信息！')
            return false
          }
          var reg = new RegExp('^[0-9]*$')
          this.saveArr = []
          let list = []
          this.addArr.some((item) =>{
            if (item.money !== '' && item.money !== null && item.money !== undefined) {
              if (!reg.test(item.money)) {
                list.push(item.money)
              }
            }
          })
          if (list.length>0) {
            this.$message.warning('录入金额格式错误！')
            return false
          }
          this.addArr.map((item) => {
            const params = {}
            params.limitation1 = item.limitation1
            params.limitation2 = item.limitation2
            params.limitationunit = 'h'
            params.money = item.money
            params.nodecode = item.nodecode
            params.nodename = item.nodename
            params.operator = item.operator
            params.ratio = item.ratio
            params.riskcode = item.risk ? item.risk.join(',') : ''
            params.riskname = item.riskname
            if (params.nodecode === 'C009' || params.nodecode === 'C001' || params.nodecode === 'C002' || params.nodecode === 'C003' || params.nodecode === 'C004') {
              delete params.money
              delete params.riskcode
              delete params.ratio
            }
            this.saveArr.push(params)
          })
          this.saveArr = [...this.saveArr]
          let valueFlag = false
          this.saveArr.forEach(item => {
            // 比例校验
            if (item.ratio && parseInt(item.ratio) < 0 || item.ratio && parseInt(item.ratio) > 100) {
              valueFlag = true
              return
            } else {
              const str = /^\d+(\.\d{0,2})?$/
              if (item.ratio && !str.test(item.ratio)) {
                valueFlag = true
                return
              }
            }
            // 处理后端格式，传给后端小数
            const m = Math.pow(10, 3)
            item.ratio = item.ratio ? parseInt(item.ratio / 100 * m, 10) / m : item.ratio
          })
          if (valueFlag) {
            this.$message.warning('比例允许录入0~100的数字，最多两位小数！')
            return
          }
          const data = {
            operationgroupLimitationVOList: this.saveArr,
            operationgroupno: this.$route.query.operationgroupno
          }
          this.saveLoad = true
          addGroupLimitations(data).then(res => {
            if (res.status == '1') {
              this.$message.success('保存成功！')
              this.limitationsList()
            } else {
              this.$message.error('保存异常！')
            }
            this.saveLoad = false
          })
        } else {
          return false
        }
      })
    },
    // 表格选择
    handleSelectionChange(data) {
      this.addArr = []
      this.addArr = data
    },
    // 查询操作用户
    // getUserList() {
    //   queryUser(this.$route.query.affiliation).then(res => {
    //     if (res.status == '1') {
    //       this.userArr = res.data || []
    //     } else {
    //       this.$message.error('查询操作用户异常！')
    //     }
    //   })
    // }
  }
}
</script>
<style scoped>
.form-span {
  display: inline-block;
  margin-bottom: 10px;
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
<style>
/* .date .el-form-item--mini.el-form-item {
  margin-bottom: 10px;
} */
</style>
