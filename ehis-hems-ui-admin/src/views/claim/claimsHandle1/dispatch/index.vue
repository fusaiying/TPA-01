<template>
  <div class="app-container">
      <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>查询条件</span>
        </div>
        <el-form ref="form" :model="form" style="padding-bottom: 30px;padding-top: 30px" label-width="130px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="报案号：" prop="rptNo">
                <el-input v-model="form.rptNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="理赔类型：" prop="claimType">
                <el-select v-model="form.claimType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in claimTypeSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="出单公司：" prop="companyCode">
                <el-select v-model="form.companyCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in companySelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>

            <el-col :span="8">
              <el-form-item label="被保人姓名：" prop="name" >
                <el-input v-model="form.name" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="证件号码：" prop="idNo" >
                <el-input v-model="form.idNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="交单日期：" prop="submitdate">
                <el-date-picker
                  v-model="form.submitdate"
                  style="width:220px;"
                  size="mini"
                  type="daterange"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                />
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>

            <el-col :span="8">
              <el-form-item label="案件状态：" prop="caseStatus">
                <el-select v-model="form.caseStatus" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in caseStatusSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="操作人：" prop="operator">
                <el-select v-model="form.operator" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in operatorSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
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

       <!-- <div class="batchInfo_class" style="margin-top: 10px;">
          <discussionSurvey :show="dialogVisable" @closeDialogVisable="changeDialogVisable"/>
        </div>-->
        <el-divider/>

        <div>
          <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>工作池</span>

            <el-button  style="float: right; margin-top: 10px;" type="primary" size="mini"
                       @click="exportData">清单导出
            </el-button>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"
                        @click="claimDispatch">案件调度
            </el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            @selection-change="handleSelectionChange"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
          >
            <el-table-column type="selection" width="50" align="center"/>
            <el-table-column prop="rptNo" label="报案号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="source" :formatter="getDeliverySourceName" label="交单来源" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="name" label="被保人姓名" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="idNo" label="证件号码" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="claimType" label="理赔类型" :formatter="getClaimTypeName"  width="150%" align="center" show-overflow-tooltip />

            <el-table-column prop="companyCode" label="出单公司"  :formatter="getCompanyName" align="center" show-overflow-tooltip />
            <el-table-column prop="monitoringTime" label="监控时效" align="center" show-overflow-tooltip />
            <el-table-column prop="caseStatus" :formatter="getCaseStatusName" label="案件状态" align="center" show-overflow-tooltip />
            <el-table-column prop="operator" label="操作人"  align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;"  v-if="scope.row.caseStatus=='01'"  @click="viewStream(scope.row)">
                  工作流查看
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


        <!-- 案件调度弹框 start -->
        <el-dialog
          :visible.sync="dialogVisible"
          :dialog-visible="dialogVisible"
          :append-to-body="true"
          :before-close="handleClose"
          :close-on-click-modal="false"
          title=""
          width="30%">
          <el-form ref="operatorForm" :model="operatorForm" style="border:0" label-width="110px" label-position="right" size="mini">

            <el-row>
              <el-col :span="8">
                <el-form-item label="操作人：" prop="operator">
                  <el-select v-model="operatorForm.operator" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="option in operatorSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="updateOperator" type="primary">确认</el-button>
            <el-button size="mini" @click="dialogVisible = false">取 消</el-button>
          </span>
        </el-dialog>
        <!-- 案件调度弹框 end -->
      </el-card>

  </div>
</template>

<script>
  import { selectCaseDispatchList
           ,getDspatchUser
           ,dispatchUpdate
           ,getIssuingcompanyList
        } from '@/api/dispatch/api'

    export default {
      components: {
        /*discussionSurvey*/
      },
        data() {
            return {
                form: {
                  rptNo: '',
                  claimType: '',
                  companyCode: '',
                  name: '',
                  idNo:'',
                  submitdate:[],
                  caseStatus:'',
                  operator: '',
                },
                claimTypeSelect:[],
                companySelect:[],
                caseStatusSelect:[],
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
                operatorForm : {
                  operator :'',
                },
              dialogVisable: false,
              rptNos : '',
              deliverySource:[],

            }
        },
      mounted(){
        //交单来源
        this.getDicts("delivery_source").then(response => {
          this.deliverySource = response.data;
        });
        this.getDicts("claimType").then(response => {
          this.claimTypeSelect = response.data;
        });
        this.getDicts("queue_claim_status").then(response => {
          this.caseStatusSelect = response.data;
        });
        this.getDspatchUserData();
        this.getIssuingcompanySelect();
      },
      computed: {

      },

      created: function() {
        this.initData();
      },
      methods: {
        getDeliverySourceName(row,col) {
          return this.selectDictLabel(this.deliverySource, row.source)
        },
        getIssuingcompanySelect (){  //getIssuingcompanyList
          const params = {
            pageNum:1,
            pageSize:1000,
            status:'Y',
            xtype:'getIssuingcompanySelect'
          };
          getIssuingcompanyList(params).then(response => {
            if(response.rows != null) {
              for(let i=0; i<response.rows.length; i++) {
                let obj= new Object();
                obj.dictValue = response.rows[i].companycode ;
                obj.dictLabel = response.rows[i].companyname;
                this.companySelect.push(obj);
              }
            }
          }).catch(error => {
            console.log(error);
          });
        },
        getDspatchUserData () {
          const params = {
            pageNum:1,
            pageSize:1000,
            status:'0',
            delFlag:0,
            xtype:'getDspatchUser'
          };
          getDspatchUser(params).then(response => {
            if(response.rows != null) {
              for(let i=0; i<response.rows.length; i++) {
                let obj= new Object();
                obj.dictLabel = response.rows[i].userName ;
                obj.dictValue = response.rows[i].userName;
                this.operatorSelect.push(obj);
              }
            }
          }).catch(error => {
            console.log(error);
          });
        },
        getCaseStatusName(row,col){
          return this.selectDictLabel(this.caseStatusSelect, row.caseStatus)
        },
        getClaimTypeName(row,col){
          return this.selectDictLabel(this.claimTypeSelect, row.claimType)
        },
        getCompanyName(row,col){
          return this.selectDictLabel(this.companySelect, row.companycode)
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

          let startTime = "";
          let endTime = "";
          let submitdate = this.form.submitdate;
          if('' != submitdate) {
            startTime = submitdate[0];
            endTime = submitdate[1];
          }

          const params = {
            pageNum:this.pageInfo.currentPage,
            pageSize:this.pageInfo.pageSize,
            rptNo:this.form.rptNo,
            claimType:this.form.claimType,
            companyCode:this.form.companyCode,
            name: this.form.name,
            idNo:this.form.idNo,
            submitstartdate:startTime,
            submitenddate:endTime,
            caseStatus:this.form.caseStatus,
            operator:this.form.operator,

            orderByColumn:'cc.create_time',
            isAsc:'desc'
          };


          selectCaseDispatchList(params).then(response => {
               this.totalNum = response.total;
               this.tableData = response.rows;
                this.loading = false
          }).catch(error => {
            console.log(error);
          });
        },
        searchByFormParms(){
          this.gettableData();
        },
        viewStream(row) {
          // this.$router.push({
          //   path: '/basic-info/contractManage-edit',
          //   query: {status:'update',rptNo: row.rptNo,flag : row.flag ,providerCode :row.providerCode}
          //
          // })
        },
        claimDispatch() {

          if(this.rptNos == '') {
            this.$message.warning('请先选择需要处理的数据！');
            return false;
          }
          this.dialogVisible = true;
        },
        exportData() {
          // this.$router.push({
          //   path: '/claims-handle/discussion',
          //   query: {discussion:'01'}
          // })

          const params = {
            rptNo:this.form.rptNo,
            claimType:this.form.claimType,
            companyCode:this.form.companyCode,
            name: this.form.name,
            idNo:this.form.idNo,
            submitstartdate:startTime,
            submitenddate:endTime,
            caseStatus:this.form.caseStatus,
            updateBy:this.form.operator,
          };
          this.download('system/case/exportDispatchList', params, `FYX_${new Date().getTime()}.xlsx`);
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
          this.rptNos = (selection.map(item => item.rptNo)); //rptNo
          // this.ids = selection.map(item => item.rptNo);
          this.single = selection.length != 1;
          this.multiple = !selection.length;
        },
        handleClose() {
          this.dialogVisible = false;
        },
        closeDialog(){
          //this.open = false;
          this.dialogVisible = false;
        },
        updateOperator(){

          if(this.operatorForm.operator == '') {
            return false;
          }
          const params = {
            rptNo: this.rptNos,
            updateBy: this.operatorForm.operator
          };
          dispatchUpdate(params).then(response => {
            if(response.code == 200) {
              this.dialogVisible = false;
              this.$message.success('调度成功！');
              this.gettableData();
            } else {
              this.dialogVisible = false;
              this.$message.error('调度失败！');
            }
          }).catch(error => {
            console.log(error);
          });
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
  /deep/.el-dialog{
    display: flex;
    flex-direction: column;
    margin:0 !important;
    position:absolute;
    top:50%;
    left:50%;
    transform:translate(-50%,-50%);
    /*height:600px;*/
    max-height:calc(100% - 30px);
    max-width:calc(100% - 30px);
  }
  /deep/.el-dialog .el-dialog__body{
    flex:1;
    overflow: auto;
  }

</style>

