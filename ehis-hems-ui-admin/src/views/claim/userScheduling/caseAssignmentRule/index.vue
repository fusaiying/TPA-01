<template>
  <div class="app-container">
    <el-card class="box-card">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>查询条件</span>
        </div>
        <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
          <el-col :span="8">
            <el-form-item label="角色查询：" prop="hospitaltype">
              <el-select v-model="formSearch.hospitaltype" class="item-width" placeholder="请选择" clearable>
              </el-select>
            </el-form-item>
          </el-col>
          <div style="text-align: right; margin-right: 10px;">
            <el-button
              size="mini"
              type="success"
              icon="el-icon-search"
              @click="searchHandle"
            >查询</el-button>
          </div>
        </el-form>
        <el-divider/>

      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>角色列表</span>
          <el-button icon="el-icon-plus" style="float: right; margin-top: 10px;" type="primary" size="mini" @click="add">新增</el-button>
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
          <el-table-column label="角色" prop="role" align="center"/>
          <el-table-column label="操作用户" prop="userOperation" align="center"/>
          <el-table-column label="分配比例%" prop="distributionRatio" align="center"/>
          <el-table-column label="是否有效" prop="IsEnabled" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateHandle(scope.row)">编辑</el-button>
            </template>
          </el-table-column>
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

      <el-dialog  :visible.sync="isAdd" width="500px" append-to-body>
        <div style="padding-left: 50px">
        <el-form ref="formName" :model="form" label-width="100px">
          <el-form-item label="角色:" prop="role">
            <el-select v-model="form.role" placeholder="请选择" style="width: 75%">
              <el-option label="测试" value="测试"></el-option>
              <el-option label="测试" value="测试"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="操作用户:" prop="userOperation">
            <el-select v-model="form.userOperation" placeholder="请选择" style="width: 75%">
              <el-option label="测试" value="测试"></el-option>
              <el-option label="测试" value="测试"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="分配比例%:" prop="distributionRatio">
            <el-input v-model="form.distributionRatio" placeholder="请输入" style="width: 75%"/>
          </el-form-item>
          <el-form-item label="承接状态:" prop="isEnabled" >
            <el-radio v-model="form.isEnabled" label="有效">有效</el-radio>
            <el-radio v-model="form.isEnabled" label="无效">无效</el-radio>
          </el-form-item>
        </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="isAdd = false">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        form: {},
        isAdd: false,
        formSearch: {},
        params: {
          city: '',
          district: '',
          hospitalcode: '',
          hospitallevel: '',
          hospitalname: '',
          hospitaltype: '',
          pageno: 1,
          pagesize: 10,
          province: '',
          state: '01'
        },
        address: [],
        regions: [],
        tableData: [
          { role:'受理',
            userOperation: 'ceshi',
            distributionRatio: '29',
            IsEnabled: '有效'},
          { role:'抽检',
            userOperation: 'ceshi',
            distributionRatio: '49',
            IsEnabled: '无效'}
        ],
        totalCount: 0,
        pageInfo: {
          page: 1,
          pageSize: 10
        },
        typeName: '',
        loading: false,
        dialogVisible: false,
        hospitalcode: '',
        index: null,
        modalValue: false,
        formLabelWidth:'120px'
      }
    },
    created() {
      this.getData()
      // 所属地
      this.getAddressData()
    },
    methods: {
      getAddressData() {
        /*getAddress().then(response => {
          this.regions = response.data
        }).catch(error => {
        })*/
      },
      handleChange(value) {
      },
      searchHandle() {
        this.params.pageno = 1
        this.params.pagesize = 10
        const values = Object.values(this.formSearch)
        values.some(item => { return item !== '' }) ? this.getData() : this.$message({ message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true })
      },
      getData() {

      },
      // 表单重置
      reset() {
        this.form = {
          role: undefined,
          userOperation: undefined,
          distributionRatio: undefined,
          isEnabled: undefined
        };
        this.resetForm("form");
      },
      /** 新增按钮操作 */
      add() {
        this.reset();
        this.isAdd = true;
      },
      cancel() {
        this.handleAdd=false;
        //this.$refs[form].resetFields();
      },
      delConfirm() {
        /*this.dialogVisible = false
        deleteOne(this.hospitalcode).then(res => {
          this.tableData.splice(this.index, 1)
          if (res.status === '1') {
            this.$message({
              message: '删除成功！',
              type: 'success',
              center: true,
              showClose: true
            })
          } else if (res.status === '0') {
            this.$message({
              message: '删除失败,' + res.data.errMsg,
              type: 'error',
              center: true,
              showClose: true
            })
          }*/

      },
      delHandle(row, index) {
        this.dialogVisible = true
        this.hospitalcode = row.hospitalcode
        this.index = index
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
      updateHandle(data){
        this.reset();
        this.isAdd=true;
        this.form.role=data.role;
        this.form.userOperation=data.userOperation;
        this.form.distributionRatio=data.distributionRatio;
        this.form.isEnabled=data.IsEnabled;
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 200px;
  }
  /*element原有样式修改*/
  .el-form-item /deep/ label {
    font-weight: normal;
  }
</style>
