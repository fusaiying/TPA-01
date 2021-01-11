<template>
  <div class="container">
    <!-- 供应商合同信息 -->
    <el-card class="box-card topCard">
      <el-collapse v-model="activeNames">
        <el-collapse-item name="1">
          <template slot="title">
            <span style="font-weight: 400;margin-left: 20px;">供应商合同信息</span>
          </template>
          <el-divider></el-divider>
          <el-form style="color: #555; padding: 0 20px;" :inline="true" :model="supplierInfo" label-position="right" label-width="130px" class="demo-form-inline" size="small">
            <el-form-item :style="{width:'33%'}" label="供应商编码：">
              <span>{{ supplierInfo.suppliercode }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="供应商类型：">
              <span>{{ /*dict.label.supplier_type[supplierInfo.suppliertype]*/getSupplierType(supplierInfo.suppliertype) }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="是否有效：">
              <span>{{/* dict.label.supplier_states[supplierInfo.state]*/getSupplierStates(supplierInfo.state) }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="供应商中文名称：">
              <span>{{ supplierInfo.chname }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="供应商英文名称：">
              <span>{{ supplierInfo.enname }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="所属地区：">
              <span>{{ supplierInfo.district }}</span>
            </el-form-item>
            <el-form-item :style="{width:'100%'}" label="详细地址：">
              <span>{{ supplierInfo.address }}</span>
            </el-form-item>
            <el-form-item :style="{width:'100%'}" label="备注：">
              <span>{{ supplierInfo.remarks }}</span>
            </el-form-item>
          </el-form>
        </el-collapse-item>
      </el-collapse>
    </el-card>

    <!-- 合同列表 -->
    <el-card class="box-card">
      <div slot="header">
        <span>合同列表</span>
      </div>
      <div >
        <el-table
          size="mini"
          :data="contractPageInfo"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
        >
          <el-table-column prop="contractcode" label="合同编码" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractname" label="合同名称" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="startdate" label="合同生效日期" align="center" show-overflow-tooltip />
          <el-table-column prop="enddate" label="合同终止日期" align="center" show-overflow-tooltip />
          <el-table-column prop="signaturedate" label="合同签订日期" align="center" show-overflow-tooltip />
          <el-table-column prop="contstate" label="合同状态" :formatter="getContractStateName" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="editClick(suppliercode,scope.row.contractno)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          :current-page="pageno"
          :page-size="pagesize"
          :total="contractTotal"
          layout="prev, pager, next, sizes,jumper"
          class="pages"
          :page-sizes="pageSizes"
          @current-change="contractCurrentChange"
          @size-change="contractSizeChange"
        />
      </div>
    </el-card>

    <!-- 合同信息 -->
    <el-card class="box-card">
      <div style="position: relative;">
        <el-tabs v-model="contractActiveName">
          <!-- 合同信息 -->
          <el-tab-pane label="合同信息" name="contractInfo">
            <div style="margin: 20px; font-size: 16px;">合同基本信息</div>
            <el-form :inline="true" :model="sn_supplier_contractDO" ref="contractRule" :rules="rulesContract" label-position="right" label-width="120px" class="demo-form-inline" size="small">
              <el-row>
                <el-col :span="8">
                  <el-form-item prop="contractcode" label="合同编码：">
                    <el-input style="width: 200px;" v-model="sn_supplier_contractDO.contractcode" size="mini" placeholder="请输入" :disabled="contractSaveState"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contractname" label="合同名称：">
                    <el-input style="width: 200px;" v-model="sn_supplier_contractDO.contractname" size="mini" placeholder="请输入" :disabled="contractSaveState"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contstate" label="合同状态：">
                    <el-select style="width: 200px;" v-model="sn_supplier_contractDO.contstate" size="mini" placeholder="请选择" :disabled="contractSaveState">
                      <el-option v-for="item in constract_statusOptions" :key="item.id" :label="item.dictLabel" :value="item.dictValue">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contractStartEndDate" label="合同起止日期：">
                    <el-date-picker style="width: 216px;" v-model="sn_supplier_contractDO.contractStartEndDate" size="mini" value-format="yyyy-MM-dd"
                      type="daterange" range-separator="~" start-placeholder="开始日期" end-placeholder="结束日期" :disabled="contractSaveState"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="signaturedate" label="合同签订日期：">
                    <el-date-picker style="width: 200px;" v-model="sn_supplier_contractDO.signaturedate" value-format="yyyy-MM-dd" :disabled="contractSaveState"
                      type="date" placeholder="选择日期" size="mini">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-form-item :style="{width:'100%'}" :class="['short-input']" label="合同备注：" prop="remarks">
                  <el-input v-model="sn_supplier_contractDO.remarks" size="mini"  placeholder="请输入"/>
                </el-form-item>
              </el-row>
            <div style="margin: 20px; font-size: 16px;">合同附件信息</div>
            <el-divider></el-divider>
              <el-form-item :style="{width:'100%'}" label="上传附件：" prop="fileList">
                <el-upload
                  class="upload-demo"
                  ref="upload"
                  :action="baseUrl+'?supplierno='+suppliercode"
                  accept=".rar,.zip,.doc,.docx,.pdf,.jpg,.png"
                  :on-preview="handlePreview"
                  :on-remove="handle_remove"
                  :before-remove="beforeRemove"
                  :before-upload="uploadBefore"
                  :headers="headers"
                  :on-success="handleSuccess"
                  :on-error="handleError"
                  :file-list="sn_supplier_contractDO.fileList"
                  :disabled="contractSaveState">
                  <el-button size="small" type="primary" :disabled="contractSaveState"><i class="el-icon-upload2"/>上传文件</el-button>&nbsp;&nbsp;
                  <span slot="tip" class="el-upload__tip">支持扩展名：.rar .zip .doc .docx .pdf .jpg .png</span>
                </el-upload>
              </el-form-item>
              <el-form-item :style="{width:'100%'}" :class="['short-input']" prop="attachmentDORemarks" label="附件备注：">
                <el-input v-model="sn_supplier_contractDO.attachmentDORemarks" tpye="textarea" :disabled="contractSaveState"/>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>

        <el-button v-if="contractActiveName == 'contractInfo'" type="primary" size="mini" style="position: absolute;right:213px;top:0px;" :disabled="contractSaveState" icon="el-icon-check" @click="contractSubmit">合同保存</el-button>
        <el-button v-if="contractActiveName == 'contractInfo'" type="warning" size="mini" style="position: absolute;right:121px;top:0px;" :disabled="contractSaveState" icon="el-icon-refresh" @click="reset">重 置</el-button>
        <el-button type="primary" size="mini" style="position: absolute;right:30px;top:0px;" icon="el-icon-back" @click="returnHistoryRouter">返 回</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import { submitContractInfo,getContractAndSupplierInfo,downloadFile,deleteContractAnnex} from '@/api/supplierManager/supplierManagement'
  import { getToken } from '@/utils/auth'
  import { downloadData } from '@/utils'
    export default {
      dicts:['supplier_states','supplier_type','constract_status'],
      name: "index",
      data(){
        const baseAddress = process.env.BASE_API
        return{
          headers: { 'Authorization': 'Bearer ' + getToken() },
          baseUrl: baseAddress + '/file/file/claim/supplier/uploadContractAnnex',
          contractno:null,
          activeNames:['2'],
          contractActiveName:'contractInfo',
          supplierInfo:{},//供应商信息

          suppliercode:null,//传过来的供应号
          contractInfo:[],//合同列表信息
          contractPageInfo:[],//合同列表分页信息
          pageno: 1,
          pagesize: 10,
          pageSizes:[5,10,20,30,50,100],
          contractTotal:0,
          index:-1,//时效push序号
          suppliertype:null,//供应商类型
          //合同录入信息
          sn_supplier_contractDO:
            {
              contractname: '',
              contstate: '01',
              enddate: '',
              remarks: '',
              startdate:'',
              contractcode:'',
              suppliercode: '',
              contractStartEndDate:[],//合同起止日期
              attachmentDORemarks:'',//附件备注
              fileList:[],//文件列表


            },

          //合同录入规则
          rulesContract: {
            contractname: { required: true, message: "请输入合同名称", trigger: "blur" },
            contractStartEndDate: {required: true, message: "请选择起止日期", trigger: "blur"},
            signaturedate: { required: true, message: "请选择签订日期", trigger: "blur" },
            contractcode: { required: true, message: "请输入合同编码", trigger: "blur" },
          },
          props: { multiple: true },

          contractSaveState:false, //合同保存状态

          currentUploadFile:[],//当前合同附件上传历史记录
          isUpload:true,
          supplier_statesOptions: [],
          supplier_typeOptions: [],
          constract_statusOptions: []
        }
      },
      mounted(){
          // 取到路由带过来的参数
          this.suppliercode = this.$route.query.suppliercode;
          this.suppliertype = this.$route.query.suppliertype

        this.getContractListData(0); //获取供应商信息和合同列表
        this.getDicts("supplier_states").then(response => {
          this.supplier_statesOptions = response.data;
        });
        this.getDicts("supplier_type").then(response => {
          this.supplier_typeOptions = response.data;
        });
        this.getDicts("constract_status").then(response => {
          this.constract_statusOptions = response.data;
        });
      },
      methods:{

        getSupplierType(data){
          return this.selectDictLabel(this.supplier_typeOptions,data)
        },

        getSupplierStates(data){
          return this.selectDictLabel(this.supplier_statesOptions,data)
        },
        contractCurrentChange(newPage) {
          this.pageno = newPage
          this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(this.pagesize * (this.pageno - 1), this.pagesize * this.pageno);
        },
        contractSizeChange(val) {
          this.pagesize = val;
          this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(0,this.pagesize);
        },
        //获取合同列表
        getContractListData(pagenow){
          const paramInfo = {
            suppliercode:this.suppliercode,
            pagesize:this.pagesize,
            pagenow:pagenow
          }
          getContractAndSupplierInfo(paramInfo).then(response => {
            if(response.status == "1"){
              this.contractInfo = response.data.sn_supplier_contractDOList;
              this.supplierInfo = response.data.sn_supplierDO;
              this.contractTotal = response.data.total;
            }
            this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(this.pagesize * (this.pageno - 1), this.pagesize * this.pageno);
          }).catch(error => {
          })
        },
        //获取合同状态名称
        getContractStateName(row,col){
         /* return this.dict.label.constract_status[row.contstate];*/
          return this.selectDictLabel(this.constract_statusOptions, row.contstate)
        },
        returnHistoryRouter: function() {
          this.$router.go(-1)
        },
        handlePreview(file) {
          this.$message.info("正在下载"+file.name+"，请稍候！")
          let attachmentno = file.url
          downloadFile(attachmentno).then(result => {
            downloadData(result, file.name)
          })
        },
        //列表移除
        handle_remove(file, fileList) {
          if(this.isUpload){
            let attachmentno = []
            attachmentno.push(file.url)

            deleteContractAnnex(attachmentno).then(response => {
              if(response.status === '0'){
                this.$message.error("删除错误！")
              }else{
                this.$message.success("删除成功！")
                if(this.currentUploadFile!==null&&this.currentUploadFile!==undefined&&this.currentUploadFile.length>0){
                  this.currentUploadFile = this.currentUploadFile.filter(item=>{
                    return item.url !== file.url
                  })
                }
                this.isUpload=true
                this.sn_supplier_contractDO.fileList = this.sn_supplier_contractDO.fileList.filter(item => {
                  return item.url !== file.url
                })
              }
            }).catch(error=>{
              this.$message.error("删除异常！")
            })
          }else{
            this.isUpload=true
            this.sn_supplier_contractDO.fileList = this.sn_supplier_contractDO.fileList.filter(item => {
              return item.url !== file.url
            })
          }
        },
        beforeRemove(file, fileList) {
          if(!this.isUpload){
            return true;
          }
          if (file && file.status==="success") {
            return this.$confirm(`确定移除 ${file.name}？`)
          }
        },
        uploadBefore(file) {
          let fileName = file.name
          let pos = fileName.lastIndexOf('.')
          let lastName = fileName.substring(pos, fileName.length)
          if (lastName.toLowerCase() !== '.zip' && lastName.toLowerCase() !== '.rar' && lastName.toLowerCase() !== '.doc' &&
            lastName.toLowerCase() !== '.docx' && lastName.toLowerCase() !== '.pdf' && lastName.toLowerCase() !== '.jpg' &&
            lastName.toLowerCase() !== '.png') {
            this.$message.error('文件类型不正确！');
            this.isUpload = false;
            return false;
          }
          let isUpload = true
          if(this.currentUploadFile!==null&&this.currentUploadFile!==undefined&&this.currentUploadFile.length>0){
            this.currentUploadFile.forEach(item => {
              if(item.name===fileName&&item.size===file.size){
                isUpload = false
              }
            })
          }
          if(!isUpload){
            this.$message.error('相同文件已存在！');
            this.isUpload = false;
            return false;
          }
        },
        handleError(file, fileList){
          this.$message.error('上传失败！')
        },
        handleSuccess(response, file, fileList){
          if(response.status === '1' && response.data !== null && response.data !== ''
            && response.data.bussinessNo !== null && response.data.bussinessNo !== ''){
            this.sn_supplier_contractDO.fileList.push({
              name: response.data.fileName,
              url: response.data.bussinessNo
            })
            if(this.currentUploadFile===null){
              this.currentUploadFile=[]
            }
            this.currentUploadFile.push({
              name:file.name,
              size:file.size,
              url:response.data.bussinessNo
            })
          }else{
            this.$message.error('上传异常！')
          }
        },
        reset() {
          this.$refs['contractRule'].resetFields();
        },
        //合同提交
        contractSubmit(){
          this.$refs['contractRule'].validate((valid) => {
            if (!valid) {
              return;
            }
            const contractVO = {
              contractcode: this.sn_supplier_contractDO.contractcode,
              contractname: this.sn_supplier_contractDO.contractname,
              suppliercode: this.suppliercode,
              contstate: this.sn_supplier_contractDO.contstate,
              createdate: this.sn_supplier_contractDO.createdate,
              startdate:this.sn_supplier_contractDO.contractStartEndDate[0],
              enddate:this.sn_supplier_contractDO.contractStartEndDate[1],
              remarks: this.sn_supplier_contractDO.remarks,
              signaturedate:this.sn_supplier_contractDO.signaturedate
              }

          const contractAttachmentVO = []
          let _tmp = this.sn_supplier_contractDO.fileList;
          let fileUrl
          let fileName = ''
          let type
          let attachmentno
          if (this.sn_supplier_contractDO.fileList){
            //优化判空等
            for (var i = 0, len = _tmp.length; i < len; i++) {
              if (_tmp[i] != null){
                fileName = _tmp[i].name ? _tmp[i].name : ''
                attachmentno = _tmp[i].url
              }
              let pos = 0
              if (fileName) {
                pos = fileName.lastIndexOf('.')
              }
              let lastName = fileName.substring(pos, fileName.length)
              let filetype = lastName.toLowerCase()
              if(filetype === '.zip'){
                type = '01'
              }else if(filetype === '.rar'){
                type = '02'
              }else if(filetype === '.doc'){
                type = '03'
              }else if(filetype === '.docx'){
                type = '04'
              }else if(filetype === '.pdf'){
                type = '05'
              }else if(filetype === '.jpg'){
                type = '06'
              }else if(filetype === '.png'){
                type = '07'
              }

              contractAttachmentVO.push({
                attachmentno:attachmentno,
                contractno:'',
                createdate: 'null',
                name: fileName,
                remarks: this.sn_supplier_contractDO.attachmentDORemarks,
                state: '01',
                type: type
              });
            }
          }
            //合同信息临时变量
          const  SupplierContractAttachmentVO = {
            contractVO: contractVO,
            contractAttachmentVO: contractAttachmentVO
          };

            submitContractInfo(SupplierContractAttachmentVO).then(response => {
              if (response.status == '1'){
                this.contractSaveState = true;
                this.contractno = response.data
                this.$message.success('合同信息保存成功！');
                this.getContractListData(0); //获取供应商信息和合同列表
              } else if (response.status == '2'){
                this.$message.warning( '合同编码已存在！');
              } else if(response.status == '3'){
                // this.$message.warning('该合同不在有效范围内！');
                this.$message.warning('起止期间内已存在其他有效合同！');
              } else if(response.status == '4') {this.$message.warning('新合同的生效期早于当前有效合同终止日期！');
              } else{
                this.$message.error('合同信息保存失败！');
              }
            }).
          catch(error => {
            this.$message.warning('合同信息保存异常！');
        })
          });
        },

        editClick(value,contractno){
          if (value === null || value === '' || contractno === null || contractno === '') {
            this.$message.warning('供应商或合同号为空！')
            return;
          }
          this.$router.push({
            path:'contractEdit', //编辑页面
            query: {
              surveyNo: value,
              contractno:contractno
            }
          })
        },

      }
    }
</script>

<style lang="css">
  .el-tooltip__popper{
    display: none;
  } /*设置显示隐藏部分内容*/
</style>

<style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .el-divider{
    margin: 10px 0;
  }
  .clearfix:after {
    clear: both
  }
  .box-card {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
  }
  .box-card /deep/ .el-card__body{
    padding: 0;
    padding-top: 10px;
    padding-bottom: 10px;
  }
  .topCard /deep/ .el-card__body{
    padding: 0;
    padding-top: 0;
    padding-bottom: 0;
  }
  .container{
    margin-bottom: 33px;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .el-form-item{
    margin-right: 0;
  }
  .pages {
    float: right;
    margin: 10px 20px 10px;
  }
  .list_span {
    display: inline-block;
    line-height: 2.5;
    color: #555;
  }
  .el-tabs /deep/ .el-tabs__item{
    width: 180px;
    text-align: center;
    color:rgba(85,85,85,0.5);
  }
  .el-tabs /deep/ .el-tabs__item.is-active {
    color: #409EFF;
  }
  .el-tabs /deep/ .el-tabs__item:hover {
    color: #409EFF;
  }
  .el-table{
    padding-left: 10px;
    padding-right: 10px;
  }


  .short-select .el-select{
    width: 100%;
  }
  .short-select /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }
  .el-cascader{
    width: 100%;
  }

  .el-tb-edit .el-form-item {
    display: none;
    width: 100%;
  }
  .el-tb-edit .current-row .el-form-item{
    display: inline-block;
  }
  .el-tb-edit .current-row .el-form-item+span{
    display: none;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .el-form /deep/ .el-form-item {
    margin-bottom: 16px;
  }
  .el-form-item{
    margin-right: 0;
  }
  .timeInput /deep/ .el-input__inner{
    margin: 0;
    padding: 0;
  }
  .el-collapse-item /deep/ .el-collapse-item__header{
    font-size: 16px;
  }
  .el-table /deep/ .el-tooltip{
    padding:0;
  }
  .el-divider{
    margin-top:0;
    margin-bottom: 10px;
  }
  .el-row{
    margin-left: 20px;
    margin-right: 20px;
  }
  .el-table /deep/ .cell {
    padding: 0;
  }
  .short-input /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }
  .short-input /deep/ .el-date-editor{
    width: 100%;
  }
</style>
