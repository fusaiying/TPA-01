<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>基本信息</span>
        <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-form
        v-loading="loading"
        :rules="rules"
        ref="baseForm"
        :model="baseForm"
        style="padding-bottom: 30px;"
        element-loading-text="拼命加载中"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="出单公司编码：" prop="companycode">
              <el-input disabled v-model="baseForm.companycode" class="item-width" clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司名称：" prop="companyname">
              <el-input v-model="baseForm.companyname" maxlength="30" class="item-width" clearable size="mini"
                        placeholder="请输入"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单公司简称：" prop="simplename">
              <el-input v-model="baseForm.simplename" maxlength="10" class="item-width" clearable size="mini"
                        placeholder="请输入"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="关联产品：" prop="riskName">
              <el-input v-model="baseForm.riskName" class="item-width" disabled clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>开票信息</span>
        </div>
        <el-table
          ref="bankTable"
          v-loading="loading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          element-loading-text="拼命加载中"
          size="small"
          highlight-current-row
          tooltip-effect="dark" bankAccount
          style="width: 100%;">
          <el-table-column label="开票名称" prop="invoicename" align="center">
            <template slot-scope="scope">
              <el-input size="mini" maxlength="40" v-show="show" v-model="scope.row.invoicename"
                        placeholder="请输入"></el-input>
              <span v-show="!show" class="form-span">{{scope.row.invoicename}}</span>
            </template>
          </el-table-column>
          <el-table-column label="纳税人识别号" prop="ratepayernumber" align="center">
            <template slot-scope="scope">
              <el-input size="mini" maxlength="18" v-show="show" v-model="scope.row.ratepayernumber"
                        placeholder="请输入"></el-input>
              <span v-show="!show" class="form-span">{{scope.row.ratepayernumber}}</span>
            </template>
          </el-table-column>
          <el-table-column label="账号" prop="account" align="center">
            <template slot-scope="scope">
              <el-input size="mini" maxlength="19" v-show="show" v-model="scope.row.account"
                        placeholder="请输入"></el-input>
              <span v-show="!show" class="form-span">{{scope.row.account}}</span>
            </template>
          </el-table-column>
          <el-table-column label="地址" prop="address" align="center">
            <template slot-scope="scope">
              <el-input size="mini" maxlength="100" v-show="show" v-model="scope.row.address"
                        placeholder="请输入"></el-input>
              <span v-show="!show" class="form-span">{{scope.row.address}}</span>
            </template>
          </el-table-column>
          <el-table-column label="电话" prop="telephone" align="center">
            <template slot-scope="scope">
              <el-input size="mini" maxlength="11" v-show="show" v-model="scope.row.telephone"
                        placeholder="请输入"></el-input>
              <span v-show="!show" class="form-span">{{scope.row.telephone}}</span>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" v-if="editFlag" size="mini" style="color: #1890ff;"
                         @click="editCompany(scope.$index,scope.row)">编辑
              </el-button>
              <el-button type="text" v-if="!editFlag" @click="saveCaseRemark(scope.$index,scope.row)"
                         size="mini" style="color: #1890ff;">保存
              </el-button>
              <el-button type="text" v-if="!editFlag && isCancel"
                         @click="cancel(scope.$index,scope.row)" size="mini" style="color: #1890ff;">取消
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>合同附件</span>
        </div>
        <el-upload
          ref="my-upload"
          :disabled="!canUpload"
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          accept=".jpg,.png"
          :before-remove="beforeRemove"
          :before-upload="beforeUpload"
          :on-success="success"
          list-type="text"
          multiple
          trigger
          :file-list="fileList" style="width: 40%">
          <el-button :disabled="!canUpload" size="small" type="primary">点击上传</el-button>
          <!--          <el-button size="small" type="primary" @click.stop="handlePreview1" >预览</el-button>-->
          <span @click.stop="handlePreview1" class="mySpan" style="color: #36a3f7">预览</span>
          <span style="color:red" @click.stop="cleanImg">批量删除</span>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
        <el-image-viewer
          v-if="showViewer"
          :on-close.stop="closeViewer"
          :url-list="file"/>
      </div>
    </el-card>
  </div>
</template>

<script>
// 导入组件
import ElImageViewer from 'element-ui/packages/image/src/image-viewer'
import {
  getIssuingcompany,
  listIssuingcompany,
  addissuingAndCompanyDTO,
  updateIssuingcompany,
  getInvoice,
  updateInvoice,
  addInvoice
} from '@/api/baseInfo/issuingCompany'
import moment from 'moment'

export default {

  props: {
    caseRemark: {
      type: Object,
      default: () => {
        return {
          data: []
        }
      }
    }
  },
  //注册组件
  components: {ElImageViewer},
  data() {
    return {
      isCancel: true,
      canUpload: true,
      show: true,
      editFlag: false,
      rules: {
        companyname: [{required: true, message: '出单公司名称不能为空', trigger: 'blur'}],
        simplename: [{required: true, message: '出单公司简称不能为空', trigger: 'blur'}],
      },
      hasBlock: false,
      isCompanyShow: false,
      rowData: [{
        invoicename: '',
        ratepayernumber: '',
        account: '',
        address: '',
        telephone: '',
      }],
      uploadData: [],
      myUpload: {
        index: 0,
        url: '',
        name: ''
      },
      fileList: [],
      //基本信息
      baseForm: {
        companycode: ''
      },
      //关联产品
      //totalCount: 0,
      loading: false,
      //定义需要使用的属性
      imgUrl: '',//图片
      showViewer: false, // 显示查看器
      file: [],
      //开票信息
      tableData: [{
        companycode: '',
        invoicename: '',
        ratepayernumber: '',
        account: '',
        address: '',
        telephone: '',
      }]
    }
  },
  mounted() {
    if (this.$route.query.companycode) {
      this.show = false
      this.editFlag = true
      this.loading = true
      this.baseForm = {
        companycode: this.$route.query.companycode,
        companyname: this.$route.query.companyname,
        pageNum: 1,
        pageSize: 5,
      }
      getIssuingcompany(this.baseForm.companycode).then(res => {
        this.baseForm = res.data
      })
      getInvoice(this.$route.query.companycode).then(res => {
        if (res.data != null) {
          let data = [];
          data.push(res.data)
          this.tableData = data;
        } else {
          this.show = true
          this.editFlag = false
          this.isCancel = false
        }
        this.loading = false
      })
    } else {
      this.editFlag = false
      this.canUpload = false
      this.isCancel = false
    }
  },
  methods: {
    ifBlock() {
      //el-table中绑定的数组对象
      this.tableData.forEach((v, i) => {
        for (const val in v) {
          if (v[val] === "" || v[val] === null) {
            for (const childrenVal in v) {
              if (v[childrenVal] === "" || v[childrenVal] === null) {
                //el-table中列绑定的字段
                if (`${childrenVal}` === "invoicename") {
                  this.hasBlock = true;
                  // el-table中列表头内容
                  return this.$message.warning(
                    "开票名称不能为空"
                  );
                } else if (`${childrenVal}` === "ratepayernumber") {
                  this.hasBlock = true;
                  return this.$message.warning(
                    "纳税人识别号不能为空"
                  );
                } else if (`${childrenVal}` === "account") {
                  this.hasBlock = true;
                  return this.$message.warning(
                    "账号不能为空"
                  );
                } else if (`${childrenVal}` === "address") {
                  this.hasBlock = true;
                  return this.$message.warning(
                    "地址不能为空"
                  );
                } else if (`${childrenVal}` === "telephone") {
                  this.hasBlock = true;
                  return this.$message.warning(
                    "电话不能为空"
                  );
                }
              }
            }
          }
        }
      });
    },
    async saveHandle() {//invoicename ratepayernumber account address telephone
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          let data = this.tableData[0]
          this.hasBlock = false;
          if ((data.invoicename !== null && data.invoicename !== '') ||
            (data.ratepayernumber !== null && data.ratepayernumber !== '') ||
            (data.account !== null && data.account !== '') ||
            (data.address !== null && data.address !== '') ||
            (data.telephone !== null && data.telephone !== '')) {
            this.ifBlock()
          }
          if (!this.hasBlock) {
            let params = {
              baseIssuingcompany: this.baseForm,
              baseIssuingcompanyInvoice: this.tableData[0]
            }
            addissuingAndCompanyDTO(params).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.baseForm = res.data
                if (this.baseForm.companycode) {
                  this.tableData[0].companycode = this.baseForm.companycode
                }
                this.canUpload = true
              } else {
                this.$message.error('保存失败！')
              }
            }).catch(error => {
              this.$message({
                message: '保存异常',
                type: 'error',
                center: true,
                showClose: true
              })
            })

          }
        } else {
          return false
        }
      })


    },
    goBack() {
      this.$router.go(-1)
    },
    success(response, file, fileList) {
      console.log('b');
      for (let i = 0; i < this.uploadData.length; i++) {
        this.fileList[fileList.length - (this.uploadData.length - i)] = {
          name: file.name,
          url: this.uploadData[i]
        }
      }
      this.uploadData = []
    },
    beforeUpload(file) {
      console.log('a');
      let url = URL.createObjectURL(file)
      this.uploadData.push(url);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    //定义需要的事件
    // 关闭查看器
    closeViewer() {
      this.showViewer = false
    },

    cleanImg: function () {

      this.fileList = []
    },

    //定义预览按钮中绑定的事件handlePreview
    handlePreview1: function () {
      {
        this.file = [],
          this.fileList.filter(item => {
            this.file.push(item.url)
          })
        this.showViewer = true
      }
    },
    saveCaseRemark(i, row) {
      this.hasBlock = false;
      this.ifBlock()
      if (!this.hasBlock) {
        if (this.$route.query.companycode) {
          this.tableData[0].companycode=this.baseForm.companycode
          updateInvoice(this.tableData[0]).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '保存成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.show = false;
              this.editFlag = true;
            } else {
              this.$message.error('保存失败！')
            }
          }).catch(error => {
            this.$message({
              message: '保存异常',
              type: 'error',
              center: true,
              showClose: true
            })
          })
        } else {
          if (this.canUpload) {
            this.tableData[0].companycode=this.baseForm.companycode
            addInvoice(this.tableData[0]).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.show = false;
                this.editFlag = true;
              } else {
                this.$message.error('保存失败！')
              }
            }).catch(error => {
              this.$message({
                message: '保存异常',
                type: 'error',
                center: true,
                showClose: true
              })
            })

          } else {
            return this.$message.warning(
              "请先保存出单公司基本信息！"
            );
          }
        }
      }
    },
    editCompany(index, row) {
      this.rowData[0].invoicename = row.invoicename
      this.rowData[0].ratepayernumber = row.ratepayernumber
      this.rowData[0].account = row.account
      this.rowData[0].address = row.address
      this.rowData[0].telephone = row.telephone

      this.show = true
      this.editFlag = false

    },
    cancel(index, row) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        row.invoicename = this.rowData[0].invoicename
        row.ratepayernumber = this.rowData[0].ratepayernumber
        row.account = this.rowData[0].account
        row.address = this.rowData[0].address
        row.telephone = this.rowData[0].telephone

        this.show = false
        this.editFlag = true
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
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

.mySpan {
  margin-left: 50px;
  margin-right: 10px;
}

.form-span {
  font-size: 12px;
}

.uploadButton {
  background-color: #534d4d
}
</style>
