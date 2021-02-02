<!--供应商合约-->
<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-form ref="searchForm" :model="form" style="padding-bottom: 30px;" label-width="130px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="合约编码：" prop="chname"  >
              <el-input v-model="form.chname" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="initData"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="合约名称：" prop="servcomType">
              <el-select v-model="form.servcomType" class="item-width" size="mini" placeholder="请选择">
                <el-option key="01" label="医疗机构" value="01"/>
                <el-option key="02" label="救援机构" value="02"/>
                <el-option key="03" label="健康管理机构" value="03"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="合约类型：" prop="suppliertype" >
              <el-select v-model="form.suppliertype" class="item-width" size="mini" placeholder="省-市-区-县">

              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="合约期限类型：" prop="status">
              <el-select v-model="form.status" class="item-width" size="mini" placeholder="请选择">
                <el-option key="01" label="有效" value="01"/>
                <el-option key="02" label="无效" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="合约有效期：" prop="servcomNo">
              <el-input v-model="form.servcomNo" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="initData"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="供应商名称：" prop="servcomNo">
              <el-input v-model="form.servcomNo" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="initData"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="status">
              <el-select v-model="form.status" class="item-width" size="mini" placeholder="请选择">
                <el-option key="01" label="有效" value="01"/>
                <el-option key="02" label="无效" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="success" icon="el-icon-search" @click="initData">查 询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('resetData')">重 置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>供应商列表信息（{{ totalCount }}）</span>
          <span style="float: right;">
            <el-button icon="el-icon-plus" type="primary" size="mini" @click="addSupplier">新增</el-button>
          </span>
        </div>
        <el-table
          :data="pendingTableData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="servcomNo" label="供应商编码" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="chname" label="供应商名称" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="servcomType" label="供应商类型" align="center" show-overflow-tooltip/>
          <el-table-column prop="address" label="所属地区" align="center" show-overflow-tooltip/>
          <el-table-column prop="status" label="状态" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="editClick(scope.row.suppliercode)">编辑
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="form.pageNum"
          :limit.sync="form.pageSize"
          @pagination="getData"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {getContractTableInfo} from '@/api/supplierManager/supplierManagement'

  export default {
    data() {
      return {
        form: {
          pageNum: 1,
          pageSize: 10,
          servcomType: undefined,
          chname: undefined,
          status: undefined,
          servcomNo: undefined
        },
        totalCount: 0,
        pendingTableData: [{
          servcomNo: '001'
        }],
        pendingTotalNum: 0,
      }
    },
    mounted() {
      this.initData();
    },
    methods: {
      initData() {
        this.pageno = 1
        this.pagesize = 10
        this.form.suppliertype = this.queryForm.suppliertype
        this.form.chname = this.queryForm.chname
        this.form.state = this.queryForm.state
        // this.sendRequest = true;
        this.getSupplierListData('query')
      },
      reset(resetData) {
        this.$refs[resetData].resetFields()
      },
      getSupplierData() {
        this.getSupplierListData('query')
      },
      getSupplierListData(type) {
        if (type === 'query') {
          this.pageno = 1
        }
        const queryParam = {
          suppliertype: this.form.suppliertype,
          chname: this.form.chname,
          state: this.form.state,
          pageno: this.pageno,
          pagesize: this.pagesize
        }
        getList(queryParam).then(response => {
          this.supplierListData = response.data.supplierlist
          this.supplierListNum = response.data.totalnum
        }).catch(error => {
          console.log(error)
        })
      },
      addSupplier() {
        const data = encodeURI(JSON.stringify({
          type: encrypt('add'), // 调查号
          suppliercode: ''
        }))
        this.$router.push({
          path: 'basic-info/supplierManager/addEdits',
          query: {
            data
          }
        })
      },
      editClick(value) {
        if (value === null || value === '') {
          alert('供应商编码为空')
        }
        const data = encodeURI(JSON.stringify({
          type: encrypt('edit'), // 调查号
          suppliercode: encrypt(value)
        }))
        this.$router.push({
          path: '/addEdits',
          query: {
            data
          }
        })
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
