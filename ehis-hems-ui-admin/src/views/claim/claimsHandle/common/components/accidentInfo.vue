<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="出险信息" name="1" style="position: relative;">
        <el-form ref="baseForm1" :rules="tableFormRules" :model="baseForm1" size="mini">
          <el-row>
            <el-table
              ref="table1"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="baseForm1.tableData"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="index" width="55" label="序号" align="center"/>
              <el-table-column label="疾病类型" prop="type" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.type'" :rules="tableFormRules.type">
                    <el-select v-model="scope.row.type" style="width: 120px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="疾病名称" prop="name" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.name'">
                    <el-select v-model="scope.row.name" style="width: 120px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="判断依据" prop="basis" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.basis'">
                    <el-select v-model="scope.row.basis" multiple collapse-tags style="width: 130px; margin-left: 10px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="认定结果" prop="result" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.result'">
                    <el-select v-model="scope.row.result" style="width: 120px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="确诊日期" prop="date" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.date'">
                    <el-date-picker
                      v-model="scope.row.date"
                      :disabled="showFlag"
                      style="width: 140px"
                      type="date"
                      placeholder="选择日期"/>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span v-if="scope.row.editFlag">
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="saveHandle(scope.$index, scope.row, 1)">保存</el-button>
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row, 1)">删除</el-button>
                    </span>
                    <el-button v-else :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 1)">编辑</el-button>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
            <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(1)"> + 添加</el-button>
          </el-row>
        </el-form>
        <el-form ref="baseForm2" :rules="tableFormRules" :model="baseForm2" size="mini">
          <el-row>
            <el-table
              ref="table2"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="baseForm2.tableData"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="index" width="55" label="序号" align="center"/>
              <el-table-column label="伤残名称" prop="type" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.type'">
                    <el-select v-model="scope.row.type" style="width: 120px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="伤残级别" prop="name" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.name'">
                    <el-select v-model="scope.row.name" style="width: 120px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="鉴定日期" prop="date" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.date'">
                    <el-date-picker
                      v-model="scope.row.date"
                      :disabled="showFlag"
                      style="width: 140px"
                      type="date"
                      placeholder="选择日期"/>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="给付比例" prop="rate" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.rate'">
                    <el-input-number v-model="scope.row.rate" :min="0" :max="100"/>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span v-if="scope.row.editFlag">
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="saveHandle(scope.$index, scope.row, 2)">保存</el-button>
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row, 2)">删除</el-button>
                    </span>
                    <el-button v-else :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 2)">编辑</el-button>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
            <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(2)"> + 添加</el-button>
          </el-row>
        </el-form>
        <el-form ref="baseForm3" :rules="tableFormRules" :model="baseForm3" size="mini">
          <el-row>
            <el-table
              ref="table3"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="baseForm3.tableData"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="index" width="55" label="序号" align="center"/>
              <el-table-column label="手术名称" prop="name" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.name'">
                    <el-select v-model="scope.row.name" style="width: 160px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="手术等级" prop="grade" align="center">
                <template slot-scope="scope">
                  <el-form-item :prop="'tableData.' + scope.$index + '.grade'">
                    <el-select v-model="scope.row.grade" style="width: 160px;" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-form-item>
                    <span v-if="scope.row.editFlag">
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="saveHandle(scope.$index, scope.row, 3)">保存</el-button>
                      <el-button :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row, 3)">删除</el-button>
                    </span>
                    <el-button v-else :disabled="false" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 3)">编辑</el-button>
                  </el-form-item>
                </template>
              </el-table-column>
            </el-table>
            <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(3)"> + 添加</el-button>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
export default {
  props: {
    status: String,
    node: String
  },
  data() {
    return {
      activeNames: '1',
      showFlag: false,
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
      baseForm1: {
        tableData: [{ basis: [] }]
      },
      baseForm2: {
        tableData: [{ basis: [], rate: 0 }]
      },
      baseForm3: {
        tableData: [{ basis: [], rate: 0 }]
      },
      tableFormRules: {
        date: { required: true, message: '不能为空!', trigger: 'change' },
        result: { required: true, message: '不能为空!', trigger: 'change' },
        type: { required: true, message: '不能为空!', trigger: 'change' }
      }
    }
  },
  methods: {
    handleChange() {
      this.activeNames = ''
    },
    editHandle() {
      this.showFlag = false
    },
    addressChange() {},
    dialogClosed() {
      this.$refs.searchForm.resetFields()
      this.dialogPolicy = false
    },
    addHandel(idx) {
      // 表单字段具体要根据后端定义进行修改
      const field = {
        editFlag: 'add',
        info: ''
      }
      if (idx === 1) {
        this.baseForm1.tableData.push(field)
        setTimeout(() => { this.$refs.table1.setCurrentRow(field) }, 10)
      } else if (idx === 2) {
        this.baseForm2.tableData.push(field)
        setTimeout(() => { this.$refs.table2.setCurrentRow(field) }, 10)
      } else {
        this.baseForm3.tableData.push(field)
        setTimeout(() => { this.$refs.table3.setCurrentRow(field) }, 10)
      }
    },
    delHandle(index, row, idx) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (idx === 1) {
          this.baseForm1.tableData.splice(index, 1)
        } else if (idx === 2) {
          this.baseForm2.tableData.splice(index, 1)
        } else {
          this.baseForm3.tableData.splice(index, 1)
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    saveHandle(index, row, idx) {
      // ⚠️这块校验比较难需要传需校验字段的集合
      if (idx === 1) {
        this.$refs.baseForm1.validateField(['tableData.' + index + '.date', 'tableData.' + index + '.result', 'tableData.' + index + '.type'], (valid) => {
          if (valid) {
            alert('submit!')
          } else {
            return false
          }
        })
      }
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
.el-tabs ::v-deep .el-tabs__item {
  width: 10%;
}
.el-collapse ::v-deep .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
