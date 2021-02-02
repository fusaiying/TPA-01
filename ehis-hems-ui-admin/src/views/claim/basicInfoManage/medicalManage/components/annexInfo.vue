<template>
  <el-form    size="small" :model="annexInfoForm" :disabled="disabledFlag" >
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>附件信息</span>
        <span style="float: right;">
<el-upload
  :disabled="specialAnnexFlag"
  class="upload-demo"
  action="https://jsonplaceholder.typicode.com/posts/"
  :show-file-list="false"
  :on-success="handleSuccess"
  multiple
  :limit="3"
  :file-list="fileList">
</el-upload>
        </span>
      </div>
      <el-table ref="annexInfoForm" :data="annexInfoForm.form" :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column align="center" prop="annexname" min-width="160" label="附件名称"/>
        <el-table-column align="center" prop="annextype" min-width="160" label="附件类型"/>
        <el-table-column align="center" prop="uploadman" min-width="160" label="上传人"/>
        <el-table-column align="center" prop="uploadtime" min-width="160" label="上传时间"/>
        <el-table-column align="center" prop="remak" min-width="160" label="备注"/>
        <el-table-column align="center" label="操作" min-width="94" fixed="right">
          <template slot-scope="scope">
            <el-button  size="small" type="text" @click="delHandle(scope.$index, scope.row)" :disabled="specialAnnexFlag">
              删除
            </el-button>
            <el-button  size="small" type="text" @click="downHandle(scope.$index, scope.row)">
              下载
            </el-button>
          </template>
        </el-table-column>

      </el-table>

    </el-card>
  </el-form>





</template>
<script>
import {addHospital, updataHospital} from "@/api/baseInfo/medicalManage";
import {deleteICDCode} from "@/api/baseInfo/ICDcodeMaintenan";

export default {
  props: {
    suppliercode: String,
    disabledFlag : Boolean,
    specialAnnexFlag: Boolean
  },
  data() {
    return {
      fileList: [],
      annexInfoForm: {form:[]}
    }
  },
  mounted() {

  },

  methods: {
    //添加附件
    saveHandle(){

    },
    delHandle(index,row){
      this.annexInfoForm.splice(index,1)
    },
    downHandle(index,row){
      this.download('system/user/export', {
        ...this.annexInfoForm[index]
      }, `annex_${new Date().getTime()}.xlsx`)
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
::v-deep .el-tabs__item{
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
