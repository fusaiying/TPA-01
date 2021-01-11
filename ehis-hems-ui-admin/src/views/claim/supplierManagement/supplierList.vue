<template>
  <div class="supplierList_div">
    <!--Form查询条件 -->
    <div class="queryForm_div">
      <el-card class="queryForm_card">
        <el-form ref="resetData" :model="queryForm" label-width="100px" label-position="right">
          <el-row>
            <el-col :span="8">
              <el-form-item label="供应商类型：" prop="suppliertype">
                <el-select v-model="queryForm.suppliertype" style="width: 220px;" size="mini" placeholder="请选择">
                  <el-option v-for="option in dict.supplier_type" :key="option.id" :label="option.label" :value="option.value" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="供应商名称：" prop="chname">
                <el-input v-model="queryForm.chname" style="width: 220px;" size="mini" placeholder="请输入" @keyup.enter.native="initData"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="是否有效：" prop="state">
                <el-select v-model="queryForm.state" style="width: 220px;" size="mini" placeholder="请选择">
                  <el-option v-for="option in dict.supplier_states" :key="option.id" :label="option.label" :value="option.value" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item class="queryForm_button">
              <el-button size="mini" type="success" icon="el-icon-search" @click="initData" @click.native.prevent="initData">查 询</el-button>
              <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('resetData')">重 置</el-button>
            </el-form-item>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <!--供应商列表-->
    <div class="supplierList__card_div">
      <el-card class="supplierList_card">
        <div slot="header">
          <span>供应商列表({{ supplierListNum }})</span>
          <el-button icon="el-icon-plus" style="float: right;margin-right: 10px;" size="mini" type="primary" @click="addSupplier()">新增供应商</el-button>
        </div>
        <div >
          <el-table
            :data="supplierListData"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            size="mini"
            tooltip-effect="dark"
            class="supplierList_table"
          >
            <el-table-column prop="suppliercode" label="供应商编码" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="chname" label="供应商名称" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="address" label="所属地区" align="center" show-overflow-tooltip />
            <el-table-column :formatter="getSupplierTypeName" prop="suppliertype" label="供应商类型" align="center" show-overflow-tooltip />
            <el-table-column :formatter="getSupplierStateName" prop="state" label="是否有效" align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="mini" type="text" style="padding: 0px;" @click="editClick(scope.row.suppliercode)">编辑</el-button>
                <el-button v-if="scope.row.state === '01'" size="mini" type="text" style="color: #f00;" @click="deleteClick(scope.$index)">注销</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            :current-page="pageno"
            :page-size="pagesize"
            :total="supplierListNum"
            :page-sizes="pageSizes"
            layout="prev, pager, next, sizes,jumper"
            class="pages"
            @current-change="supplierHandleCurrentChange"
            @size-change="supplierHandleSizeChange"
          />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { getList, removeBySupplierCode } from '@/api/investigate/supplierManagement'

export default {
  dicts: ['supplier_states', 'supplier_type'],
  data() {
    return {
      queryForm: {
        suppliertype: '',
        chname: '',
        state: '01'
      },
      form: {
        suppliertype: '',
        chname: '',
        state: '01'
      },
      supplierListData: [],
      supplierListNum: 0,

      pageno: 1,
      pagesize: 10,
      pageSizes: [5, 10, 20, 30, 50, 100]

      // sendRequest:true
    }
  },
  mounted: function() {
    this.initData()
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
      // this.supplierListData = [];
      // this.supplierListNum = 0;
      // this.queryForm.state = '01';
      // this.sendRequest = false;

      /* this.form = {
        suppliertype: '',
        chname: '',
        state: ''
      }
      this.form.state = this.queryForm.state*/
    },
    supplierHandleCurrentChange(newPage) {
      this.pageno = newPage
      this.getSupplierListData()
    },
    supplierHandleSizeChange(val) {
      this.pagesize = val
      this.getSupplierListData('query')
    },
    getSupplierData() {
      // this.sendRequest = true;
      this.getSupplierListData('query')
    },
    getSupplierListData(type) {
      /* if(!this.sendRequest){
        return;
      }*/
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
    getSupplierTypeName(row, col) {
      return this.dict.label.supplier_type[row.suppliertype]
    },
    getSupplierStateName(row, col) {
      return this.dict.label.supplier_states[row.state]
    },
    addSupplier() {
      this.$router.push({
        path: '/supplierManagement/addEdit',
        query: {
          type: 'add', // 调查号
          suppliercode: ''
        }
      })
    },

    editClick(value) {
      if (value === null || value === '') {
        alert('供应商编码为空')
      }
      this.$router.push({
        path: '/supplierManagement/addEdit',
        query: {
          type: 'edit', // 调查号
          suppliercode: value
        }
      })
    },

    deleteClick(index) {
      this.$confirm('是否确认注销？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const suppliercode = this.supplierListData[index].suppliercode
        const pram = {
          suppliercode: suppliercode
        }

        removeBySupplierCode(pram).then(response => {
          const type = response.data.errMsg === '注销成功！' ? 'success' : 'warning'
          this.$message({
            type: type,
            message: response.data.errMsg
          })
          this.getSupplierData()
        }).catch(error => {
          console.log(error)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消注销！'
        })
      })
    }
  }
}
</script>

<style scoped>
  /*自定义样式*/
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .supplierList_div{
    width: 100%;
    margin-top: 10px;
  }

  .queryForm_div {
    width:100%;
  }

  .queryForm_card{
    margin: 0px 10px;
  }

  .supplierList__card_div {
    width: 100%;
    position: relative;
    margin-bottom: 33px;
  }

  .supplierList_card {
    margin: 10px 10px;
  }

  .queryForm_button {
    text-align: right;
    margin: 0 10px 10px;
  }

  .pages {
    float: right;
    margin: 10px 20px 10px;
  }
</style>
