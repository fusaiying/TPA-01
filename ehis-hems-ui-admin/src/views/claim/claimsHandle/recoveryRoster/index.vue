<template>
  <div class="app-container">
      <el-card class="box-card">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>追讨白名单信息查询</span>
        </div>
        <el-form ref="form" :model="form" style="padding-bottom: 30px;padding-top: 30px" label-width="130px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="被保人名称：" prop="name">
                <el-input v-model="form.name" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="证件号码：" prop="idNo">
                <el-input v-model="form.idNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="等级：" prop="level">
                <el-select v-model="form.level" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in companySelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>

            <el-col :span="8">
              <el-form-item label=" 金额上限：" prop="debtAmountUp" >
                <el-input v-model="form.debtAmountUp" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
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
            <span>追讨白名单列表（{{totalNum}}）</span>
            <el-button  style="float: right; margin-top: 10px;" type="primary" size="mini" @click="exportData">清单导出</el-button>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="addRecovery">新增</el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}">

            <el-table-column prop="name" label="被保人名称" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="idNoType" label="证件类型" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="idNo" label="证件号码" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="sex" :formatter="getsexName" label="性别" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="birthday" label="出生日期"  width="150%" align="center" show-overflow-tooltip />

            <el-table-column prop="level" :formatter="getLevelName" label="等级"  align="center" show-overflow-tooltip />
            <el-table-column prop="debtAmountUp" label="金额上限" align="center" show-overflow-tooltip />
            <el-table-column prop="recMessageFlag" :formatter="getReFlagName" label="追缴通知" align="center" show-overflow-tooltip />
            <el-table-column prop="createTime" label="创建日期"  align="center" show-overflow-tooltip >
              <template slot-scope="scope">
                <span>{{ scope.row.createTime | changeDate}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="createBy" label="创建人"  align="center" show-overflow-tooltip />

            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button   size="mini" type="text" icon="el-icon-edit" @click="editFun(scope.row)">编辑</el-button>
                <el-button   size="mini"  type="text" icon="el-icon-delete" @click="delFun(scope.row)" >删除
                </el-button>
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

        <!-- 追讨白名单维护弹框 start -->
        <el-dialog
          :visible.sync="dialogVisible"
          :dialog-visible="dialogVisible"
          :append-to-body="true"
          :before-close="handleClose"
          :close-on-click-modal="false"
          :show-close="false"
          width="80%">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>追讨白名单维护</span>
              <span style="float: right;">
                <el-button v-if="addFlag" type="primary" size="mini" @click="searchFun">查询</el-button>
                <el-button type="primary" size="mini" @click="saveDataFun">保存</el-button>
                <el-button size="mini" @click="handleClose">返回</el-button>
              </span>
            </div>
          <el-form ref="recoveryForm" :model="recoveryForm" style="border:0;" label-width="110px" label-position="right" size="mini" :rules="rules" >

            <el-row>
              <el-col :span="8">
                <el-form-item label="被保人姓名：" prop="operator">
                  <span class="font_grey">{{recoveryInfo.name}}</span>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="年龄：" prop="operator">
                  <span class="font_grey">{{recoveryInfo.age}}</span>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="性别：" prop="operator">
                 <!-- <span class="font_grey">{{recoveryInfo.sex}}</span>-->
                  <span class="font_grey">{{selectDictLabel(rgtSexs,recoveryInfo.sex)}}</span>

                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="证件号码：" prop="operator">
                  <span class="font_grey">{{recoveryInfo.idNo}}</span>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="等级：" prop="level">
                  <el-select v-model="recoveryForm.level" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="option in custLevel" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="追缴通知：" prop="recMessageFlag">
                  <el-select v-model="recoveryForm.recMessageFlag" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="option in ysOrNo" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="金额上限：" prop="debtAmountUp">
                  <el-input maxlength="14" oninput = "value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" v-model="recoveryForm.debtAmountUp" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row v-if="false">
              <el-col :span="8">
                <el-form-item label="debtWhitelistId：" prop="debtWhitelistId">
                  <el-input maxlength="14"  v-model="recoveryForm.debtWhitelistId" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row v-if="false">
              <el-col :span="8">
                <el-form-item label="insuredNo：" prop="insuredNo">
                  <el-input maxlength="14"  v-model="recoveryForm.insuredNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
          </el-card>
        </el-dialog>

        <!-- 追讨白名单维护弹框 end -->
      </el-card>

    <!-- 保单信息查询模态框 -->
    <insured-modal :value="dialogPolicy"
                   @getPropData="getPropData" :fixInfo="fixInfo"
                   @closeDialogVisable="closeDialogVisable"/>


  </div>
</template>

<script>

  import moment from 'moment'
  import insuredModal from '../common/modul/insured'

  import { listInfo , editData,debtWhiteInfo,checkMoney} from '@/api/recoveryRoster/api'

    export default {
      filters: {
        changeDate: function(value) {
          if (value !== null) {
            return moment(value).format('YYYY-MM-DD')
          }
        }
      },
      components: {
        insuredModal
      },
        data() {
            return {
              dialogPolicy:false,
              fixInfo: {
                batchNo: undefined,
                filingNo: undefined,
                rptNo: undefined,
                source: undefined,
              },
                form: {
                  name: '',
                  idNo: '',
                  level: '',
                  debtAmountUp:'',
                },
                companySelect:[],
                operatorSelect:[],
                tableData: [],
                totalNum: 0,
                idKey: 'rptNo', // 标识列表数据中每一行的唯一键的名称
                pageInfo: {
                    currentPage: 1,
                    pageSize: 10,
                    pageSizes:[5,10,20,30,50,100]
                },
                loading: false,
                defaultData : true,
                dialogVisible:false,
                recoveryForm : {
                  level: '',
                  recMessageFlag:'',
                  debtAmountUp :'',
                  debtWhitelistId:'',
                  insuredNo:'',
                },
              rules: {
                level: {trigger: ['change'], required: true, message: '等级必填'},
                recMessageFlag: {trigger: ['change'], required: true, message: '缴费通知必填'},
                debtAmountUp: {trigger: ['change'], required: true, message: '金额上限必填'},
              },
                recoveryInfo:{
                  name:'',
                  sex:'',
                  idNo:'',
                  age:''


                },
              dialogVisable: false,
              rptNos : '',
              addFlag:false,
              ysOrNo:[],
              custLevel:[],
              rgtSexs:[],

            }
        },
      mounted(){
        // sys_yes_no
        this.getDicts("sys_yes_no").then(response => {
          this.ysOrNo = response.data;
        });
        // 等级
        this.getDicts("cust_level").then(response => {
          this.custLevel = response.data;
        });
        // rgtSex
        this.getDicts("rgtSex").then(response => {
          this.rgtSexs = response.data;
        });
      },
      computed: {

      },

      created: function() {
        this.initData();
      },
      methods: {
        getsexName(row,col) {
          return this.selectDictLabel(this.rgtSexs, row.sex)
        },
        getLevelName(row,col) {
          return this.selectDictLabel(this.custLevel, row.level)
        },
        getReFlagName(row,col) {
          return this.selectDictLabel(this.ysOrNo, row.recMessageFlag)
        },
        closeDialogVisable() {
          this.dialogPolicy = false
        },
        getPropData(backValue) {
          const backData = JSON.parse(JSON.stringify(backValue));
          let baseData = backData.caseInsuredData;
          // name  sex idNo  birthday insuredNo\
          if(baseData.birthday != '') {
            baseData.age = this.getAge(baseData.birthday)
          }
          // baseData.birthday = '118';
          // baseData.age = '118';
          this.recoveryInfo = baseData;
          this.recoveryForm.insuredNo = baseData.insuredNo
        },


        searchFun() {
          this.dialogPolicy = true;
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

          const params = {
            pageNum:this.pageInfo.currentPage,
            pageSize:this.pageInfo.pageSize,
            name:this.form.name,
            idNo:this.form.idNo,
            level:this.form.level,
            debtAmountUp:this.form.debtAmountUp,

            orderByColumn:'create_time',
            isAsc:'desc'
          };


          listInfo(params).then(response => {
               this.totalNum = response.total;
               this.tableData = response.rows;
                this.loading = false
          }).catch(error => {
            console.log(error);
          });
        },
        searchByFormParms(){
          this.pageInfo.currentPage = 1;
          this.pageInfo.pageSize = 10;
          this.gettableData();
        },
        editFun(row) {
          this.recoveryInfo = '';
          this.recoveryInfo = row;
          this.addFlag = false;
          this.dialogVisible = true;
          this.recoveryForm.debtWhitelistId =row.debtWhitelistId;
          this.recoveryForm.level = row.level;
          this.recoveryForm.debtAmountUp = row.debtAmountUp;
          this.recoveryForm.recMessageFlag = row.recMessageFlag;
          this.recoveryForm.insuredNo =  row.insuredNo;
        },
        delFun(row) {

          console.log(row.rptNo)
          const params = {
            rptNo:row.rptNo
          };
          // 存在欠款 ？
          checkMoney(params).then(response => {
            if(response.code == '200' && response.data.residualAmount >0) {
              this.$confirm(`该客户存在欠款，是否置为失效?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'info'
              }).then(() => {
                this. delDataFun(row);
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消！'
                })
              })
            } else if(response.code == '200') {
              this.$confirm('确定删除?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.delDataFun(row);
              }).catch(() => {
                console.log(error);
              });
            }
          });


        },
        delDataFun(row){
          debtWhiteInfo(row.debtWhitelistId).then(response => {
            if(response.code == '200') {
              this.gettableData();
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getData();
            } else {
              this.$message({
                type: 'info',
                message: '删除失败'
              });
            }

          }).catch(error => {
            console.log(error);
          })
        },
        addRecovery() {
          this.recoveryInfo = '';
          this.recoveryForm.debtWhitelistId  = '';
          this.recoveryForm.level  = [];
          this.recoveryForm.debtAmountUp  = '';
          this.recoveryForm.recMessageFlag  = [];
          this.recoveryForm.insuredNo =  '';
          this.addFlag = true;
          this.dialogVisible = true;
          this.$refs['recoveryForm'].clearValidate();
        },
        saveDataFun(){
          if(this.recoveryForm.insuredNo == '') {
            this.$message({
              message: '请先查询基本信息！',
              type: 'info',
              center: true,
              showClose: true
            });
            return false;
          }

          this.$refs.recoveryForm.validate((valid) => {
            if (valid) {
              const params = this. recoveryForm;
              editData(params).then(response => {
                this.dialogVisible = false;
                this.gettableData();
                if (response.code == '200') {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  });
                } else {
                  this.$message({
                    message: '保存失败！',
                    type: 'error',
                    center: true,
                    showClose: true
                  });
                }
              }).catch(error => {
                console.log(error);
              });
            }
          })
        },
        exportData() {

          const params = {
            name:this.form.name,
            idNo:this.form.idNo,
            level:this.form.level,
            debtAmountUp:this.form.debtAmountUp,
            orderByColumn:'create_time',
            isAsc:'desc'
          };
          this.download('claimflow/whitelist/export', params, `FYX_${new Date().getTime()}.xlsx`);
        },

        handleClose() {
          this.recoveryInfo = '';
          this.recoveryForm.debtWhitelistId  = '';
          this.recoveryForm.level  = [];
          this.recoveryForm.debtAmountUp  = '';
          this.recoveryForm.recMessageFlag  = [];
          this.recoveryForm.insuredNo =  '';
          this.$refs['recoveryForm'].clearValidate();
          this.dialogVisible = false;
        },
        // closeDialog(){
        //   this.dialogVisible = false;
        // },
        getAge (strBirthday) {
          let returnAge;
          let strBirthdayArr=strBirthday.split("-");
          let birthYear = strBirthdayArr[0];
          let birthMonth = strBirthdayArr[1];
          let birthDay = strBirthdayArr[2];
          let d = new Date();
          let nowYear = d.getFullYear();
          let nowMonth = d.getMonth() + 1;
          let nowDay = d.getDate();

          if(nowYear == birthYear) {
            returnAge = 0;
          }
          else {
            let ageDiff = nowYear - birthYear ;
            if(ageDiff > 0) {
              if(nowMonth == birthMonth){
                let dayDiff = nowDay - birthDay;
                if(dayDiff < 0) {
                  returnAge = ageDiff - 1;
                } else  {
                  returnAge = ageDiff ;
                }
              }  else {
                let monthDiff = nowMonth - birthMonth;
                if(monthDiff < 0) {
                  returnAge = ageDiff - 1;
                }  else {
                  returnAge = ageDiff ;
                }
              }
            }  else {
              returnAge = -1;
            }
          }
          return returnAge + 1;
        }
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
  .font_grey {
    color: #BFBFBF;
  }
  /*/deep/.el-dialog{*/
  /*  display: flex;*/
  /*  flex-direction: column;*/
  /*  margin:0 !important;*/
  /*  position:absolute;*/
  /*  top:50%;*/
  /*  left:50%;*/
  /*  transform:translate(-50%,-50%);*/
  /*  !*height:600px;*!*/
  /*  max-height:calc(100% - 30px);*/
  /*  max-width:calc(100% - 30px);*/
  /*}*/
  /*/deep/.el-dialog .el-dialog__body{*/
  /*  flex:1;*/
  /*  overflow: auto;*/
  /*}*/

</style>

