<template>
  <el-form size="small" :model="manuaInfoForm"  >
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务手册列表信息</span>
        <span style="float: right;" v-if="!disabledFlag || status==''" :disabled="disabledFlag">
<el-upload
  action="https://jsonplaceholder.typicode.com/posts/"
  :show-file-list="false"
  :on-success="handleSuccess"
  multiple
  :limit="3"
  :file-list="fileList">

           <el-button size="mini" type="primary" :disabled="disabledFlag">点击上传</el-button>
</el-upload>
        </span>
      </div>
      <el-table ref="manuaInfoForm" :data="manuaInfoForm.form" :header-cell-style="{color:'black',background:'#f8f8ff'}"   v-loading="loading"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column align="center" prop="annexname" min-width="160" label="服务手册列表信息" show-overflow-tooltip/>
        <el-table-column align="center" prop="manualName" min-width="160" label="服务手册名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="updataBy" min-width="160" label="操作人" show-overflow-tooltip/>
        <el-table-column align="center" prop="updataTime" min-width="160" label="操作时间" show-overflow-tooltip/>
        <el-table-column align="center" label="操作" min-width="94" fixed="right">
          <template slot-scope="scope">
            <el-button   type="text" size="mini" style="color: #1890ff;" @click="downHandle(scope.$index, scope.row)" :disabled="disabledFlag">
              下载
            </el-button>
            <el-button  v-if="!disabledFlag" type="text"  size="mini" style="color: #1890ff;" @click="delHandle(scope.$index, scope.row)" >
              删除
            </el-button>

          </template>
        </el-table-column>

      </el-table>
      <!--分页组件-->
      <pagination
        v-show="totalCount>0"
        :total="totalCount"
        :page.sync="params.pageNum"
        :limit.sync="params.pageSize"
        @pagination="getData"
      />


    </el-card>
  </el-form>






</template>
<script>

import {checkList, getManualInfoList} from '@/api/productManage/serviceProductManagement'

export default {
  props: {
    productCode: {
      type: String,
      default: ''
    },
    disabledFlag: Boolean,
    status: {
      type: String,
      default: ''
    },
  },
  /*  watch: {
      productCode: function (newValue){
        this.init()
      }
    },*/
  data() {
    return {
      fileList: [],
      loading:false,

      manuaInfoForm: {form:[]},
      totalCount: 0,
      params: {
        pageNum:1,
        pageSize: 10,
        productCode: '',
      }
    }
  },
  mounted() {
    this.init()
  },

  methods: {
    init(){
      if(this.productCode!=null && this.productCode!='') {
        let queryData = {
          productCode: this.productCode,
        }
        getManualInfoList(queryData).then(res => {
          if (res.code == '200' && res.rows.length > 0) {
            this.manuaInfoForm.form = res.rows
            this.totalCount=res.total
          }
        })
      }

    },

    getData() {
      this.params.productCode= this.productCode
      this.loading = true
      //调用查询接口
      checkList(this.params).then(res => {
        this.manuaInfoForm.form = res.rows;
        this.totalCount = res.total;
        this.loading = false;
      }).catch(res => {
        this.loading = false
      })

    },
    //添加附件
    saveHandle(){

    },
    delHandle(index,row){
      this.manuaInfoForm.form.splice(index,1)
    },
    downHandle(index,row){
     /* this.download('system/user/export', {
        ...this.manuaInfoForm.form[index]
      }, `annex_${new Date().getTime()}.xlsx`)*/
    },

    handleSuccess(){
      this.$emit('upload')
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


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}
</style>
