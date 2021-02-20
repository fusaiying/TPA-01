<template>
  <div class="app-container">
      <el-card class="box-card">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>查询条件</span>
        </div>
        <el-form ref="form" :model="form" style="padding-bottom: 30px;padding-top: 30px" label-width="130px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="机构：" prop="deptCode">
                <el-select v-model="form.deptCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in sysDepts" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="理赔类型：" prop="claimType">
                <el-select v-model="form.claimType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in claimTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="批次号：" prop="batchNo">
                <el-input v-model="form.batchNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label=" 报案号：" prop="rptNo" >
                <el-input v-model="form.rptNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label=" 盒号：" prop="caseBoxNo" >
                <el-input v-model="form.caseBoxNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>
          </el-row>

          <div style="text-align: right; margin-right: 10px;">
            <el-row>
              <el-form-item class="but1">
                <el-button size="mini" type="success" icon="el-icon-search" @click="searchByFormParms">查 询</el-button>
                <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('form')">重 置</el-button>
              </el-form-item>
            </el-row>
          </div>
        </el-form>
      </el-card>

      <el-card  style="margin-top: 10px;">
        <div>
          <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>归档信息</span>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"@click="addRecovery('','add')">新增</el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
          >
            <el-table-column prop="caseBoxNo" label="盒号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="rptStartNo" :formatter="getFullRptNo" label="报案号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="batchNo" label="批次号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="deptCode" label="机构" width="150%" align="center" :formatter="getDeptName" show-overflow-tooltip />
            <el-table-column prop="createTime" label="新增时间"  width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="updateTime" label="末次修改时间"  align="center" show-overflow-tooltip />
            <el-table-column prop="updateBy" label="末次修改人" align="center" show-overflow-tooltip />
            <el-table-column prop="casenum" label="已归档案件数" align="center" show-overflow-tooltip />
            <el-table-column prop="status" :formatter="getYesOrNo" label="是否销毁"  align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button  size="small" type="text" @click="addRecovery(scope.row,'edit')">编辑</el-button>
                <el-button  size="small" type="text" @click="openListDialog(scope.row,'show')">明细</el-button>
                <el-button :disabled="scope.row.status == 'N' ? 'disabled' : false" size="small" type="text" @click="destroyFun(scope.row)">销毁</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--分页组件-->
          <pagination
             v-show="totalNum>0"
            :total="totalNum"
            :page.sync="pageInfo.currentPage"
            :limit.sync="pageInfo.pageSize"
            @pagination="initData"
          />
        </div>

        <!-- 案件归档 start -->
        <el-dialog
          :visible.sync="dialogVisible"
          :dialog-visible="dialogVisible"
          :append-to-body="true"
          :before-close="closeDialog"
          :close-on-click-modal="false"
          title=""
          width="540px">
          <el-form ref="pbaceCaseForm" :model="pbaceCaseForm" style="border:0;" label-width="180px" label-position="right" size="mini" :rules="rules" >
            <el-row>
              <el-col :span="24">
                <el-form-item label="机构：" prop="deptCode">
                  <el-select disabled v-model="pbaceCaseForm.deptCode" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="option in sysDepts" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item label="理赔类型：" prop="claimType">
                  <el-select  :disabled="read ? 'disabled' : false"  v-model="pbaceCaseForm.claimType" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="option in claimTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">  <!--:readonly="read ? 'readonly' : false"-->
                <el-form-item label="批次号：" prop="batchNo">
                  <el-input :disabled="read ? 'disabled' : false" v-model="pbaceCaseForm.batchNo" class="item-width" size="mini" placeholder="请输入"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item label="盒号：" prop="caseBoxNo">
                  <el-input :disabled="read ? 'disabled' : false" v-model="pbaceCaseForm.caseBoxNo" class="item-width" size="mini" placeholder="请输入"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item label="报案号起止：" prop="rptStartNo">
                  <el-input v-model="pbaceCaseForm.rptStartNo" class="item-width" size="mini" placeholder="请输入"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item label="" prop="rptEndNo">
                  <el-input v-model="pbaceCaseForm.rptEndNo" class="item-width" size="mini" placeholder="请输入"/>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
         <!-- <span style="margin-right:25px; margin-bottom: 25px;float: right;" class="dialog-footer">
            <el-button size="mini" @click="saveInfo" type="primary">确认</el-button>
          </span>-->
            <div slot="footer" class="dialog-footer" >
              <el-button type="primary" @click="saveInfo" >确 定</el-button>
            </div>
        </el-dialog>

        <!-- 案件归档 end -->
      </el-card>
    <case-list :value="listDialog" :editPower="editPower" :fixInfo="fixInfo" @closeListDialog="closeListDialog"/>

  </div>
</template>

<script>

   import caseList from './components/caseList'
   import moment from 'moment'
   import {getDepts, caseFilingList,editCaseFiling,editDestroy,addInfo } from '@/api/placeCase/api'

   import {getDept} from '@/api/claim/standingBookSearch'

    export default {
      filters: {
        changeDate: function(value) {
          if (value !== null) {
            return moment(value).format('YYYY-MM-DD')
          }
        }
      },
      components: {
        caseList
      },

        data() {
          const checkExistInfo = (rule, value, callback) => {
            if (!value) {
              callback(new Error("盒号必填"));
            }  else if(!this.read) {
                const params = {
                  pageNum : 1,
                  pageSize : 1,
                  caseBoxNo:value ,
                  orderByColumn:'create_time',
                  isAsc:'desc'
                };
                caseFilingList(params).then(response => {
                  if(response.total == 1) {
                    callback(new Error("盒号已存在"));
                  } else {
                    callback();
                  }
                }).catch(error => {
                  console.log(error);
                });
            } else {
              callback();
            }

          };
            return {
               read:false,
                form: {
                  deptCode: '',
                  claimType: '',
                  batchNo: '',
                  rptNo:'',
                  caseBoxNo:'',
                },
                companySelect:[],
                operatorSelect:[],
                tableData: [],
                totalNum: 0,
                pageInfo: {
                    currentPage: 1,
                    pageSize: 10,
                    pageSizes:[5,10,20,30,50,100]
                },
                loading: false,
                defaultData : true,
                dialogVisible:false,
                pbaceCaseForm : {
                  deptCode :'',
                  claimType:'',
                  batchNo:'',
                  rptStartNo:'',
                  rptEndNo:'',
                  rpttNo:'',
                  caseBoxNo:'',
                },
                rules: {
                  deptCode: {trigger: ['change'], required: false, message: '机构必填'},
                  claimType: {trigger: ['change'], required: true, message: '理赔类型必填'},
                  batchNo: {trigger: ['change'], required: true, message: '批次号必填'},
                  rptStartNo: {trigger: ['change'], required: true, message: '报案号起必填'},
                  rptEndNo: {trigger: ['change'], required: true, message: '报案号止必填'},
                  caseBoxNo: {trigger: ['change'],validator: checkExistInfo, required: true},

                },
                dialogVisable: false,
                listDialog:false,
                searchBtn:false,
                fixInfo:{
                  batchNo:'',
                  rptStartNo:'',
                  rptEndNo:'',
                },
                claimTypes:[],
                yssOrNo:[],
                editPower:true,
                sysDepts:[],
            }
        },
      mounted(){
        // claimType
        this.getDicts("claimType").then(response => {
          this.claimTypes = response.data;
        });
      },
      computed: {

      },

      created: function() {
        this.getDepts();
        this.initData();
      },
      methods: {
        openListDialog(row,type){
          if(row.status == 'N') {
            this.editPower = false;
          } else {
            this.editPower = true;
          }
          this.fixInfo  = {
            caseBoxNo:row.caseBoxNo,
            rptStartNo:row.rptStartNo,
            rptEndNo:row.rptEndNo,
          };
          this.listDialog = true
        },
        closeListDialog() {
          this.listDialog = false
        },
        changeDialogVisable(){
          this.dialogVisible = false
        },
        initData(){
          this.gettableData();
        },
        //重置
        reset(form) {
            this.$refs[form].resetFields()
        },
        //查询
        gettableData () {

          let createStartStr  = '';
          let createEndStr = '';
          if(!this.searchBtn) {
            let currentDate = new  Date();
            createEndStr   = (this.dateFormat('yyyy-MM-dd',currentDate))  +" 23:59:59";
            currentDate.setMonth(currentDate.getMonth() - 1);
            createStartStr = this.dateFormat('yyyy-MM-dd',currentDate);
          }

          const params = {
            pageNum : this.searchBtn ? 1 : this.pageInfo.currentPage,
            pageSize : this.searchBtn ? 10 : this.pageInfo.pageSize,
            deptCode:this.form.deptCode ,
            claimType:this.form.claimType ,
            batchNo: this.form.batchNo ,
            rptNo:this.form.rptNo ,
            caseBoxNo:this.form.caseBoxNo ,
            createStartStr : createStartStr,
            createEndStr : createEndStr,
            orderByColumn:'create_time',
            isAsc:'desc'
          };
          this.loading = true;
          caseFilingList(params).then(response => {
            this.totalNum = response.total;
            this.tableData = response.rows;
            this.loading = false;
            this.searchBtn = false;
          }).catch(error => {
            this.loading = true;
            console.log(error);
          });
        },
        searchByFormParms(){
          this.searchBtn = true;
          this.gettableData();
        },
        addRecovery(row,type) {
          this.dialogVisible = true;
          if(type == 'edit') {
            this.read = true;
            this.pbaceCaseForm.claimType = row.claimType;
            this.pbaceCaseForm.batchNo = row.batchNo;
            this.pbaceCaseForm.caseBoxNo = row.caseBoxNo;
            this.pbaceCaseForm.rptStartNo = row.rptStartNo;
            this.pbaceCaseForm.rptEndNo = row.rptEndNo;
            this.pbaceCaseForm.caseBoxNo = row.caseBoxNo;
          } else {
            this.resetInfo();
          }

        },
        saveInfo(){
         let params = this.pbaceCaseForm;


          this.$refs.pbaceCaseForm.validate((valid) => {
            if (valid) {
              if(this.read) {  // 更新
                editCaseFiling(params).then(response => {
                  if(response.code == 200) {
                    this.dialogVisible = false;
                    this.$message.success('更新成功！');
                    this.gettableData();
                  } else {
                    this.dialogVisible = false;
                    this.$message.error('更新失败！');
                  }
                }).catch(error => {
                  console.log(error);
                });
              } else {  // 新增
                params.deptCode = this.pbaceCaseForm.deptCode;
                addInfo(params).then(response => {
                  if(response.code == 200) {
                    this.dialogVisible = false;
                    this.$message.success('新增成功！');
                    this.gettableData();
                  } else {
                    this.dialogVisible = false;
                    this.$message.error('新增失败！');
                  }
                }).catch(error => {
                  console.log(error);
                });
              }
            }
          })
        },
        destroyFun(row){
          // editDestroy

          this.pbaceCaseForm.batchNo = row.batchNo;
          this.pbaceCaseForm.caseBoxNo = row.caseBoxNo;

          const param = {
            batchNo : row.batchNo,
            caseBoxNo : row.caseBoxNo
          };
          let vm = this;
          this.$confirm('确定销毁该记录?', "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function () {

            editDestroy(param).then(res => {
              if(res.code == '200') {
                vm.gettableData();
                vm.$message({
                  message: '销毁成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
              } else {
                vm.$message({
                  message: '销毁失败！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
              }
            });
          }).then(() => {
          }).catch(function (error) {
            console.log(error)
          });

        },
        getDepts() {
          let query = {
            deptName: ''
          };
          getDepts(query).then(response => {

            if(response.data) {
              for(let i=0; i<response.data.length; i++) {
                let obj= new Object();
                obj.dictLabel = response.data[i].deptName;
                obj.dictValue = response.data[i].deptId.toString();
                this.sysDepts.push(obj);
              }
            }
            //当前登陆人部门
            getDept().then(response => {
              if(response.deptId) {
                this.form.deptCode = response.deptId.toString();
                this.pbaceCaseForm.deptCode  = response.deptId.toString();
              }
            })
          })
        },
        closeDialog(){
          this.resetInfo();
          this.dialogVisible = false;
        },
        resetInfo(){
          this.pbaceCaseForm.claimType = '';
          this.pbaceCaseForm.batchNo = '';
          this.pbaceCaseForm.rptStartNo = '';
          this.pbaceCaseForm.rptEndNo = '';
          this.pbaceCaseForm.caseBoxNo = '';
          this.read = false;
          this.$refs['pbaceCaseForm'].clearValidate();
        },
        getFullRptNo(row,col){
          return  row.rptStartNo + " - " +row.rptEndNo;
        },
        getYesOrNo(row,col){
          return row.status == 'N' ? '是' : '否'
        },
        getDeptName(row,col){
          return this.selectDictLabel(this.sysDepts, row.deptCode)
        },
        dateFormat(fmt,date) {
          let o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
          };
          if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
          }
          for(let k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
              fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
          }
          return fmt;
        },
      }
    }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /deep/.el-table .warning-row {
    background: oldlace;
  }
</style>

