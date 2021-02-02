
<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>撤件重开处理</span>
        <el-button style="float: right;" size="mini" type="primary">扫描</el-button>
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
            <el-table-column label="是否原件" prop="original" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.claimno" :prop="'tableData.' + scope.$index + '.original'" :rules="tableFormRules.original">
                  <el-select v-model="scope.row.original" placeholder="请选择">
                    <el-option v-for="option in originals" :key="option.id" :label="option.label" :value="option.value"/>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.original }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="资料类型" prop="materialtype" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.claimno" :prop="'tableData.' + scope.$index + '.materialtype'" :rules="tableFormRules.materialtype">
                  <el-select v-model="scope.row.materialtype" placeholder="请选择">
                    <el-option v-for="option in materialtypes" :key="option.id" :label="option.label" :value="option.value"/>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.materialtype }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="备注" prop="materialdescription" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.claimno" :prop="'tableData.' + scope.$index + '.materialdescription'">
                  <el-input v-model="scope.row.materialdescription" maxlength="100" placeholder="请输入" size="mini"/>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{ scope.row.materialdescription }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="添加时间" width="130" prop="createdate" align="center">
              <template slot-scope="scope">
                <el-form-item :prop="'tableData.' + scope.$index + '.createdate'">
                  <span>{{ scope.row.createdate }}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="120" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/">
                    <el-button size="mini" type="text">上传</el-button>
                  </el-upload>
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addRowHandle"> + 添加</el-button>
        </el-row>
        <el-row>
          <el-form-item :rules="tableFormRules.reasontype" class="item-reson" label="重开原因：" label-width="100px" prop="reasontype">
            <el-select v-model="baseForm.reasontype" placeholder="请选择">
              <el-option v-for="option in reasontypes" :key="option.id" :label="option.label" :value="option.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label-width="100px" label="备注：" prop="description">
            <el-input v-model="baseForm.description" style="width: 80%;" maxlength="500" show-word-limit placeholder="请输入" size="mini"/>
          </el-form-item>
        </el-row>
      </el-form>
      <div class="bottom clearfix" style="text-align: right;">
        <el-button type="primary" size="mini" class="button" @click="saveHandle">确认重开</el-button>
        <el-button type="primary" size="mini" class="button" @click="goBack">返回</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      baseForm: {
        reasontype: '',
        description: '',
        tableData: []
      },
      originals: [],
      materialtypes: [],
      reasontypes: [],
      tableFormRules: {
        original: [{ required: true, message: '不能为空!', trigger: 'change' }],
        materialtype: [{ required: true, message: '不能为空!', trigger: 'change' }],
        reasontype: [{ required: true, message: '不能为空!', trigger: 'change' }]
      }
    }
  },
  methods: {
    addRowHandle() {
      // 表单字段具体要根据后端定义进行修改
      const field = {
        code: '',
        comCode: '',
        groupName: '',
        node: '',
        status: '',
        creatDate: '',
        temp: true
      }
      this.baseForm.tableData.push(field)
      setTimeout(() => { this.$refs.table.setCurrentRow(field) }, 10)
    },
    saveHandle() {
      this.$refs.tableForm.validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          return false
        }
      })
    },
    goBack() {
      this.$router.go(-1)
    }
  }
}
</script>
<style scoped>
.el-form-item ::v-deep .el-form-item__error {
  padding-left: 30px;
}
.item-reson ::v-deep .el-form-item__error {
  padding-left: 0;
}
</style>
