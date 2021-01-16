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
              <span>{{ /*dict.label.supplier_type[supplierInfo.suppliertype]*/getSupplier_type(supplierInfo.suppliertype) }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="是否有效：">
              <span>{{ /*dict.label.supplier_states[supplierInfo.state]*/getSupplier_states(supplierInfo.state) }}</span>
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
          class="supplierList_table"
        >
          <el-table-column prop="contractcode" label="合同编码" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractname" label="合同名称" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="startdate" label="合同生效日期" align="center" show-overflow-tooltip />
          <el-table-column prop="enddate" label="合同终止日期" align="center" show-overflow-tooltip />
          <el-table-column prop="signaturedate" label="合同签订日期" align="center" show-overflow-tooltip />
          <el-table-column prop="contstate" label="合同状态" :formatter="getContractStateName" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="editContract(scope.row.contractno)">编辑</el-button>
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
            <div style="margin-bottom: 20px;margin-left: 20px;font-size: 16px;">合同基本信息</div>
            <el-form :inline="true" :model="sn_supplier_contractDO" ref="contractRule" :rules="rulesContract" label-position="right" label-width="120px" class="demo-form-inline" size="small">
              <el-row>
                <el-col :span="8">
                  <el-form-item prop="contractcode" :class="['short-input']" label="合同编码：">
                    <el-input style="width: 200px;" :disabled="currentContractState" v-model="sn_supplier_contractDO.contractcode" size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contractname" :class="['short-input']" label="合同名称：">
                    <el-input style="width: 200px;" :disabled="currentContractState" v-model="sn_supplier_contractDO.contractname" size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contstate" :class="['short-select']" label="合同状态：">
                    <el-select style="width: 200px;" :disabled="currentContractState" v-model="sn_supplier_contractDO.contstate" size="mini" placeholder="请选择">
                      <el-option v-for="item in constract_statusOptions" :key="item.id" :label="item.dictLabel" :value="item.dictValue">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contractStartEndDate" :class="['short-input']" label="合同起止日期：">
                    <el-date-picker style="width: 216px;" :disabled="currentContractState" v-model="sn_supplier_contractDO.contractStartEndDate" size="mini" value-format="yyyy-MM-dd"
                                    type="daterange" range-separator="~" start-placeholder="开始日期" end-placeholder="结束日期"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="signaturedate" :class="['short-input']" label="合同签订日期：">
                    <el-date-picker style="width: 200px;" :disabled="currentContractState" v-model="sn_supplier_contractDO.signaturedate" value-format="yyyy-MM-dd"
                                    type="date" placeholder="选择日期" size="mini">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item :style="{width:'100%'}" prop="remarks" :class="['short-input']" label="合同备注：">
                    <el-input :disabled="currentContractState" v-model="sn_supplier_contractDO.remarks" size="mini"  placeholder="请输入"/>
                  </el-form-item>
                </el-col>
              </el-row>

              <div style="margin-bottom: 20px;margin-left: 20px;font-size: 16px;display: inline-block;">合同附件信息</div>
              <el-button :disabled="currentContractState" type="danger" size="mini" style="float: right;margin-right:30px;" icon="el-icon-delete" @click="deleteAttachmentFile">删除附件</el-button>
              <el-button type="success" size="mini" style="float: right;margin-right:15px;" icon="el-icon-download" @click="dowloadAttachmentFile">下载附件</el-button>

              <el-upload
                class="upload-demo"
                style="margin-left: 20px; margin-bottom: 20px;"
                ref="upload"
                :action="baseUrl+'?supplierno='+suppliercode+'&contractno='+sn_supplier_contractDO.contractno"
                accept=".rar,.zip,.doc,.docx,.pdf,.jpg,.png"
                :on-preview="handlePreview"
                :on-remove="handle_remove"
                :before-remove="beforeRemove"
                :before-upload="uploadBefore"
                :headers="headers"
                :on-success="handleSuccess"
                :on-error="handleError"
                :file-list="fileList"
                :disabled="currentContractState">
                <el-button :disabled="currentContractState" type="primary" size="small"><i class="el-icon-upload2"/>上传文件</el-button>&nbsp;&nbsp;
                <span slot="tip" class="el-upload__tip">支持扩展名：.rar .zip .doc .docx .pdf .jpg .png</span>
              </el-upload>
              <el-divider></el-divider>
              <div style="margin-bottom: 18px;">
                  <el-table  ref="singleTable" :data="attachmentDOList"
                             size="mini"
                             :header-cell-style="{color:'blkack',background:'#f8f8ff'}"
                             @row-click="selectOne"
                             highlight-current-row>
                    <el-table-column align="center" label="" min-width="1">
                      <template slot-scope="scope">
                        <el-radio v-model="currentRadio" :label="scope.row.attachmentno">
                          <span class="el-radio__label"></span>
                        </el-radio>
                      </template>
                    </el-table-column>
                    <el-table-column label="序号" width="80" align="center" min-width="2" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <span>{{ scope.$index + 1 }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="附件名称" align="center" min-width="2" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <span>{{scope.row.name}}</span>
                      </template>
                    </el-table-column>
                  </el-table>
              </div>
              <el-form-item :style="{width:'100%'}" :class="['short-input']" label="附件备注：">
                <el-input :disabled="currentContractState" v-model="currentRow.remarks" tpye="textarea"/>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>

        <el-button v-if="contractActiveName == 'contractInfo'" type="primary" size="mini" style="position: absolute;right:213px;top:0px;" icon="el-icon-check" :disabled="currentContractState" @click="contractSubmit">合同修改</el-button>
        <el-button v-if="contractActiveName == 'contractInfo'" type="warning" size="mini" style="position: absolute;right:121px;top:0px;" icon="el-icon-refresh" :disabled="currentContractState" @click="resetContractInfo">重 置</el-button>
        <el-button type="primary" size="mini" style="position: absolute;right:30px;top:0px;" icon="el-icon-back" @click="returnHistoryRouter">返 回</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
  import { getContractAndSupplierInfo,getByConstractCodeInfo,getAddress,
    downloadFile,updateConstract,deleteContractAnnex} from '@/api/supplierManager/supplierManagement'
  import { downloadData } from '@/utils'
  import { getToken } from '@/utils/auth'

    export default {
      dicts:['supplier_states','supplier_type','constract_status'],
      name: "updateContract",
      data(){
        const baseAddress = process.env.BASE_API
        return{
          headers: { 'Authorization': 'Bearer ' + getToken() },
          baseUrl: baseAddress + '/file/file/claim/supplier/uploadUpdateContractAnnex',
          isUpload:true,
          currentUploadFile:[],//当前合同附件上传历史记录
          fileList:[],
          activeNames:['2'],
          contractActiveName:'contractInfo',
          supplierInfo:{},//供应商信息

          suppliercode:'',//传过来的供应号
          contractnoTemp:'',//选中的合同
          contractInfo:[],//合同列表信息
          contractPageInfo:[],//合同列表分页信息
          pageno: 1,
          pagesize: 10,
          pageSizes:[5,10,20,30,50,100],
          contractTotal:0,

          caseArea: [],//地区
          props: { multiple: true },

          //合同录入信息
          sn_supplier_contractDO: {
            contractno:'',//合同号
            contractcode: '',//合同编码
            contractname: '',//合同名称
            contstate: '',//合同状态
            contractStartEndDate:[],//合同起止日期
            startdate: '',//合同开始时间
            enddate: '',//合同结束时间
            signaturedate:'',//合同签订日期
            remarks: '',//备注
          },

          attachmentDOList:[],//文件列表
          //合同录入规则
          rulesContract: {
            contractname: { required: true, message: "请输入合同名称", trigger: "blur" },
            contractStartEndDate: {required: true, message: "请选择起止日期", trigger: "blur"},
            signaturedate: { required: true, message: "请选择签订日期", trigger: "blur" },
            contractcode: { required: true, message: "请输入合同编码", trigger: "blur" },
          },

          currentRadio:'',//当前行
          contstate:null,//合同是否有效

          currentRow:{},//选中行
          currentContractState:true,//当前合同状态
          supplier_statesOptions: [],
          supplier_typeOptions: [],
          constract_statusOptions: []
        }
      },
      mounted(){
        this.suppliercode = this.$route.query.surveyNo;
        this.contractnoTemp = this.$route.query.contractno;
        this.contstate = this.$route.query.contstate
        this.getContractListData(1); //获取供应商信息和合同列表
        this.getAddressData();
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

        getSupplier_type(data){
          return  this.selectDictLabel(this.supplier_typeOptions,data)
        },

        getSupplier_states(data){
          return  this.selectDictLabel(this.supplier_statesOptions,data)
        },
        contractCurrentChange(newPage) {
          this.pageno = newPage
          this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(this.pagesize * (this.pageno - 1), this.pagesize * this.pageno);
        },
        contractSizeChange(val) {
          this.pagesize = val;
          this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(0,this.pagesize);
        },
        //获取合同状态名称
        getContractStateName(row,col){
         /* return this.dict.label.constract_status[row.contstate];*/
          return  this.selectDictLabel(this.constract_statusOptions,row.contstate)
        },
        returnHistoryRouter: function() {
          this.$router.go(-1)
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
              this.contractTotal = response.total;
            }
            this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(this.pagesize * (this.pageno - 1), this.pagesize * this.pageno);
          })
        },

        handleError(file, fileList){
          this.$message.error('上传失败！')
        },
        handleSuccess(response, file, fileList){
          if(response.status === '1' && response.data !== null && response.data !== ''
            && response.data.bussinessNo !== null && response.data.bussinessNo !== ''){
            this.fileList.push({
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
                this.fileList = this.fileList.filter(item => {
                  return item.url !== file.url
                })
              }
            }).catch(error=>{
              this.$message.error("删除异常！")
            })

          }else{
            this.isUpload=true
            this.fileList = this.fileList.filter(item => {
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
        //获取地址
        getAddressData(){
          getAddress().then(response => {
            this.caseArea = response.data
            for (let k = 0; k < this.caseArea.length; k++) {
              for (let l = 0; l < this.caseArea[k].children.length; l++) {
                this.caseArea[k].children[l].children = null
              }
            }
            //获取选中编辑合同的数据
            this.editContract(this.contractnoTemp);
          }).catch(error => {
            this.$message.error("获取区域信息异常！")
          })
        },
        //获取选定合同的数数据
        editContract(contractcode){
          if(contractcode === null || contractcode === ''){
            this.$message.warning('合同号无效！');
            return false;
          }
          getByConstractCodeInfo(contractcode).then(response => {
            if(response.status == '0'){
              this.$message.error('查询合同信息异常！');
            }else{
              if(response.data === null){
                this.$message.warning('未查询到当前合同信息！');
                return false;
              }
              //合同信息
              this.sn_supplier_contractDO = response.data.contractDO//合同信息
              this.currentContractState = this.sn_supplier_contractDO.contstate === '01'?false:true;
              this.$set(this.sn_supplier_contractDO,'contractStartEndDate', [this.sn_supplier_contractDO.startdate,this.sn_supplier_contractDO.enddate])
              this.attachmentDOList = response.data.attachmentDOList//合同
            }
          });
        },
        selectOne(row){
          this.currentRow = row;
          this.currentRadio = row.attachmentno;
        },
        //删除附件
        deleteAttachmentFile(){
          if(this.currentRow === null || this.currentRow.attachmentno === null ||
            this.currentRow.attachmentno === '' || this.currentRow.attachmentno === undefined){
            this.$message.warning('请先选择附件！');
            return false;
          }
          this.$confirm('是否删除？', '提示', {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'
          }).then(() => {
            let attachmentno = []
            attachmentno.push(this.currentRow.attachmentno)

            deleteContractAnnex(attachmentno).then(response => {
              if(response.status === '0'){
                this.$message.error("删除错误！")
              }else{
                this.$message.success("删除成功！")

                this.attachmentDOList = this.attachmentDOList.filter(item => {
                  return this.currentRow.attachmentno!==item.attachmentno
                })
              }
            }).catch(error=>{
              this.$message.error("删除异常！")
            })
          }).catch(() => {this.$message({type: 'info', message: '已取消删除！'});});
        },
        //下载附件
        dowloadAttachmentFile(){
          if(this.currentRow === null || this.currentRow.attachmentno === null ||
            this.currentRow.attachmentno === '' || this.currentRow.attachmentno === undefined){
            this.$message.warning('请先选择附件！');
            return false;
          }
          downloadFile(this.currentRow.attachmentno).then(result => {
            downloadData(result, this.currentRow.name)
          })
        },
        //合同信息重置
        resetContractInfo(){
          if(this.sn_supplier_contractDO.contractno === null || this.sn_supplier_contractDO.contractno === ''){
            this.$message.warning('合同号无效！');
            return false;
          }
          getByConstractCodeInfo(this.sn_supplier_contractDO.contractno).then(response => {
            if(response.status == '0'){
              this.$message.error('查询合同信息异常！');
            }else{
              if(response.data === null){
                this.$message.warning('未查询到当前合同信息！');
                return false;
              }
              this.sn_supplier_contractDO = response.data.contractDO//合同信息
              this.currentContractState = this.sn_supplier_contractDO.contstate === '01'?false:true;
              this.$set(this.sn_supplier_contractDO,'contractStartEndDate', [this.sn_supplier_contractDO.startdate,this.sn_supplier_contractDO.enddate])
              this.attachmentDOList = response.data.attachmentDOList//合同附件信息
            }
          });
        },
        //合同保存
        contractSubmit(){
          this.$refs['contractRule'].validate((valid) => {
            if (!valid) {
              return;
            }
            this.sn_supplier_contractDO.startdate = this.sn_supplier_contractDO.contractStartEndDate[0]
            this.sn_supplier_contractDO.enddate = this.sn_supplier_contractDO.contractStartEndDate[1]
            let _tmp = this.fileList;
            let fileUrl
            let fileName = ''
            let type
            if (this.fileList){
              //优化判空等
              for (var i = 0, len = _tmp.length; i < len; i++) {
                if (_tmp[i] != null){
                  fileUrl = _tmp[i].url
                  fileName = _tmp[i].name ? _tmp[i].name : ''
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
                this.attachmentDOList.push({
                  contractno:'',
                  createdate: 'null',
                  //服务端存储地址
                  filepath: fileUrl,
                  name: fileName,
                  remarks: null,
                  state: '01',
                  type: type
                });
              }
            }

            let queryParam = {
              sn_supplier_contractDO:this.sn_supplier_contractDO,
              attachmentDOlist:this.attachmentDOList
            }


            updateConstract(queryParam).then(response => {
              if (response.status == '1'){
                this.editContract(this.sn_supplier_contractDO.contractno);
                this.$message.success('合同信息修改成功！');
                this.fileList=[],
                  this.currentUploadFile=[],
                /*刷新合同列表*/
                this.getContractListData(1)
              } else if(response.status=='2'){
                this.$message.warning('起止期间内已存在其他有效合同！');
              }else{
                this.$message.error("合同修改失败！");

              }
            })
          });
        },
      }
    }
</script>

<style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
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
    width: calc(100% - 130px);
  }
  .short-input /deep/ .el-form-item__content{
    width: calc(100% - 130px);
  }
  .short-input /deep/ .el-date-editor{
    width: 100%;
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
  .el-form-item{
    margin-right: 0;
  }
  .timeInput /deep/ .el-input__inner{
    margin: 0;
    padding: 0;
  }
  .el-divider{
    margin: 10px 0;
  }
  .el-collapse-item /deep/ .el-collapse-item__header{
    font-size: 16px;
  }
  .el-tooltip__popper{
    display: none;
  }
  .el-table /deep/.el-tooltip{
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
  .el-form /deep/ .el-form-item {
    margin-bottom: 16px;
  }
  .el-table /deep/ .cell {
    padding: 0;
  }
</style>
