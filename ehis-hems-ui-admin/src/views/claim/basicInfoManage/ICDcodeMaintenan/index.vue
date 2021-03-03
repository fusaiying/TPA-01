<template>
  <div class="app-container">
    <el-card class="box-card">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>ICD查询</span>
      </div>
      <el-form ref="searchForm" :model="formSearch"  style="padding-bottom: 30px;" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="ICD编码：" prop="icdcode">
              <el-input v-model="formSearch.icdcode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="ICD中文名称：" prop="icdmname">
              <el-input ref="diseaseName" v-model="formSearch.icdmname" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询
          </el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>ICD列表（{{ totalCount }}）</span>
          <span style="float: right;">
            <el-button icon="el-icon-plus" type="primary" size="mini" @click="editHandle">新增</el-button>
            <el-button type="primary" size="mini" @click="listExport"  >清单导出</el-button>
            <!--            <el-button icon="el-icon-download" type="warning" size="mini">导出</el-button>-->
          </span>
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
          <el-table-column label="ICD编码" prop="icdcode" align="center" show-overflow-tooltip/>
          <el-table-column label="ICD中文名称" prop="icdmname" align="center" show-overflow-tooltip/>
          <el-table-column label="基础库标志" prop="source" align="center" :formatter="getBaseSource" show-overflow-tooltip/>


          <el-table-column label="创建日期" prop="createTime" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{parseTime(scope.row.createTime, '{y}-{m}-{d}')}}</span>
            </template>
          </el-table-column>
          <el-table-column label="创建人" prop="createBy" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" style="color: #1890ff;" @click="updateEditHandle(scope.row)">编辑
              </el-button>
              <el-button type="text" size="mini" style="color: #f00;" @click="delHandle(scope.row, scope.$index)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="formSearch.pageNum"
          :limit.sync="formSearch.pageSize"
          @pagination="getData"
        />
        <!--分页组件-->
        <!-- <div>
           <el-pagination
             :total="totalCount"
             :current-page="pageInfo.page"
             :page-size="pageInfo.pageSize"
             :page-sizes="[10, 20, 30, 40]"
             style="margin-top: 8px; text-align: right;"
             layout="sizes, prev, pager, next"
             @size-change="handleSizeChange"
             @current-change="handleCurrentChange"/>
         </div>-->
        <!--        <el-dialog
                  :visible.sync="dialogVisible"
                  :modal="modalValue"
                  :close-on-click-modal="false"
                  title="提示"
                  width="30%">
                  <span>{{ '是否确认删除此ICD项？' }}</span>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="delConfirm">确 定</el-button>
                  </span>
                </el-dialog>-->
      </div>
    </el-card>
  </div>
</template>

<script>
import {getICDList, removeOneICDCode} from '@/api/baseInfo/ICDcodeMaintenan.js'

export default {

  data() {

    return {
      // 查询参数
      formSearch: {
        pageNum: 1,
        pageSize: 10,
        icdcode: '',
        icdmname: '',
      },
      tableData: [],
      totalCount: 0,
      loading: false,
      isListExport: false,
      dialogVisible: false,
      modalValue: false,
      icdcode: '',
      index: '',
      delRowData: {},
      base_library_markOptions: []
    }
  },
  mounted() {
/*    getICDList(this.formSearch).then(response => {
      this.tableData = response.rows;
      this.totalCount = response.total;
      this.loading = false;
    }).catch(res => {
      this.loading = false
    })*/
    this.getDicts("base_library_mark").then(response => {
      this.base_library_markOptions = response.data;
    });

  },
  methods: {
    searchHandle() {
      if((this.formSearch.icdcode===null || this.formSearch.icdcode==='') &&  (this.formSearch.icdmname===null || this.formSearch.icdmname==='') ){
        return this.$message.warning("至少输入一个查询条件！")
      }else {
        this.$refs.searchForm.validate((valid) => {
          if (valid) {
            this.loading = true
            this.formSearch.pageNum = 1
            this.formSearch.pageSize = 10
            this.getData()
          }
        })
      }

    },
    getBaseSource(row, col){
      return this.selectDictLabel(this.base_library_markOptions, row.source);
    },

    getData() {
      getICDList(this.formSearch).then(response => {
        this.tableData = response.rows;
        this.totalCount = response.total;
        if(this.totalCount===0){
          this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
        }
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })
    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    // 删除
    delHandle(row, index) {
      if (row.source === '1') {
        this.$message.warning('此项ICD不可删除！')
      }else {
        //  this.dialogVisible = true
        this.index = index
        this.icdcode = row.icdcode
        this.delRowData=row
        this.$confirm('是否删除此ICD项?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          removeOneICDCode(this.delRowData).then(res => {
            this.tableData.splice(this.index, 1)
            if (res.code === 200) {
              this.$message({
                message: '删除成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.getData()
            }
          })
          /*  this.$message({
              type: 'success',
              message: '删除成功!'
            });*/
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    /*      delConfirm() {
            this.dialogVisible = false

            removeOneICDCode(this.delRowData).then(res => {
              this.tableData.splice(this.index, 1)
              if (res.code === 200) {
                this.$message({
                  message: '删除成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.getData()
              } else   {
                this.$message({
                  message: '删除失败！',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
          },*/
    editHandle(row) {
      this.$router.push({
        path: '/basic-info/icd/edit', query: {
          isEmpty: false
        }
      })
    },
    updateEditHandle(row) {
      this.$router.push({
        path: '/basic-info/icd/edit', query: {
          icdcode: row.icdcode,
          icdmname: row.icdmname,
          isEmpty: true
        }
      })
    },
    //清单导出
    listExport() {
      let query={
        icdcode: this.formSearch.icdcode,
        icdmname: this.formSearch.icdmname,
      }
      let queryData={
        pageNum: 1,
        pageSize: 10,
      }
      //
      getICDList(queryData).then(res => {
        if (res.rows.length>0){
          this.isListExport=true
          this.download('provider/icd10/export', {
            ...query}, `icd_${new Date().getTime()}.xlsx`).catch(res=>{
            this.$message({
              message: res,
              type: 'error',
              center: true,
              showClose: true
            })
          })
        }else {
          return this.$message.warning(
            "没有查询到能导出的数据！"
          )
        }
      }).catch(res => {

      })




    }
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
