<template>
  <div class="app-container">
    <el-card class="box-card">
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>角色清单</span>
          <el-button icon="el-icon-plus" style="float: right; margin-top: 10px;" type="primary" size="mini"
                     @click="addUser">新增
          </el-button>
        </div>
        <el-table
          v-loading="loading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          element-loading-text="拼命加载中"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column label="操作用户" prop="username" align="center"/>
          <el-table-column label="角色" prop="role" align="center"/>
          <el-table-column label="承接人" prop="continuePerson" align="center"/>
          <el-table-column label="承接状态" prop="continueStatus" align="center"/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="editUser(scope.row)">编辑</el-button>
              <el-button type="text" size="mini" style="color: #f00;" @click="deleteUser(scope.row)">删除</el-button>
            </template>
          </el-table-column>


          <el-dialog  :visible.sync="dialogFormVisible" width="500px" append-to-body>
           <div style="padding-left: 50px">
            <el-form ref="formName" :model="form" label-width="100px" >
              <el-form-item label="交接人:" prop="handoverPerson" >
                <el-select v-model="form.handoverPerson" placeholder="请选择" style="width: 75%">
                  <el-option label="测试" value="测试"></el-option>
                  <el-option label="测试" value="测试"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="承接人:" prop="continuePerson">
                <el-select v-model="form.continuePerson" placeholder="请选择"style=" width: 75%">
                  <el-option label="测试" value="测试"></el-option>
                  <el-option label="测试" value="测试"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="承接状态:" prop="continueStatus" >
                <el-radio v-model="form.continueStatus" label="有效">有效</el-radio>
                <el-radio v-model="form.continueStatus" label="无效">无效</el-radio>
              </el-form-item>
            </el-form>
           </div>
            <div slot="footer" class="dialog-footer" >
              <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
          </el-dialog>
        </el-table>
        <!--分页组件-->
        <div>
          <el-pagination
            :total="totalCount"
            :current-page="pageInfo.page"
            :page-size="pageInfo.pageSize"
            :page-sizes="[10, 20, 30, 40]"
            style="margin-top: 8px; text-align: right;"
            layout="sizes, prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        loading:false,
        dialogFormVisible: false,
        form: {},
        formLabelWidth: '120px',
        dialogStatus:'',
        totalCount: 0,
        pageInfo: {
          page: 1,
          pageSize: 10
        },
        tableData: [
          {
            username: '张三',
            role: '受理',
            continuePerson: '王五',
            continueStatus: '有效'
          },
          {
            username: '李四',
            role: '初审',
            continuePerson: '赵六',
            continueStatus: '无效'
          },
        ]
      }
    },
    created() {

    },
    methods: {
      searchHandle() {
        this.params.pageno = 1
        this.params.pagesize = 10
        const values = Object.values(this.formSearch)
        values.some(item => {
          return item !== ''
        }) ? this.getData() : this.$message({message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true})
      },
      handleSizeChange(val) {
        this.pageInfo.pageSize = val
        this.params.pagesize = val
        this.getData()
      },
      // 分页
      handleCurrentChange(val) {
        this.pageInfo.page = val
        this.params.pageno = val
        this.getData()
      },
      deleteUser(row) {
        this.$router.push({
          path: '/basic-info/serviceRuleDefin',
          isEmpty: false
        })
      },
      editUser(row) {
        this.reset();
        //显示弹框
        this.form.handoverPerson=row.handoverPerson;
        this.form.continuePerson=row.continuePerson;
        this.form.continueStatus=row.continueStatus;
        this.dialogFormVisible = true;
      },

      // 表单重置
      reset() {
        this.form = {
          handoverPerson: undefined,
          continuePerson: undefined,
          continueStatus: undefined
        };
        this.resetForm("form");
      },
      //新增
      addUser() {
        this.reset();
        //显示弹框
        this.dialogFormVisible = true;

      },

    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

</style>
