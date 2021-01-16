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
              <el-form-item label="证件号码：" prop="idNumber" >
                <el-input v-model="form.idNumber" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="交单日期：" prop="sdDate">
                <el-date-picker
                  v-model="form.sdDate"
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
            <el-table-column prop="name" label="交单来源" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="name" label="被保人姓名" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="name" label="证件号码" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="claimType" label="理赔类型" width="150%" align="center" show-overflow-tooltip />

            <el-table-column prop="companyCode" label="出单公司"  align="center" show-overflow-tooltip />
            <el-table-column prop="companyCode" label="监控时效" align="center" show-overflow-tooltip />
            <el-table-column prop="caseStatus" label="案件状态" align="center" show-overflow-tooltip />
            <el-table-column prop="caseStatus" label="操作人"  align="center" show-overflow-tooltip />
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
    // import {
    //   getAllBaseProviderInfo
    // } from '@/api/contractManage/contractManagement'

    /*import discussionSurvey from './components/discussionSurvey' /!*协谈 调查*!/*/

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
                  idNumber:'',
                  sdDate:[],
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
              dialogVisable: false

            }
        },
      mounted(){
        this.getDicts("claimType").then(response => {
          this.claimTypeSelect = response.data;
        });
        this.getDicts("claimType").then(response => {
          this.companySelect = response.data;
        });
        this.getDicts("queue_claim_status").then(response => {
          this.caseStatusSelect = response.data;
        });
        this.getDicts("state").then(response => {
          this.operatorSelect = response.data;
        });
      },
      computed: {

      },

      created: function() {
        this.initData();
      },
      methods: {
        /*changeDialogVisable(){
          this.dialogVisable=false
        },*/

        initData(){
          let currentDate = new Date();
          let beforeDate = new Date();

          let endStr = this.dateformat('yyyy-MM-dd',currentDate);
          currentDate.setMonth(currentDate.getMonth() - 3);
          let starStr = this.dateformat('yyyy-MM-dd',currentDate);

          this.gettableData(this.pageInfo.currentPage);
        },
        dateformat(fmt,date) {
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
         addMonth(num) {
          num = parseInt(num);
          let sDate =  new Date();

           let sYear = sDate.getFullYear();
           let sMonth = sDate.getMonth() + 1;
           let sDay = sDate.getDate();

           let eYear = sYear;
           let eMonth = sMonth - num;
           let eDay = sDay;
            while (eMonth > 12) {
              eYear++;
              eMonth -= 12;
            }

           let eDate = new Date(eYear, eMonth - 1, eDay);

          while (eDate.getMonth() != eMonth - 1) {
            eDay--;
            eDate = new Date(eYear, eMonth - 1, eDay);
          }

          return eDate;
        },
        //重置
        reset(form) {
            this.$refs[form].resetFields()
        },
        //查询
        gettableData () {
            this.defaultData = false;
           // this.gettableData(1);
        },
        searchByFormParms(){
          // if(this.form.rptNo == '' && this.form.name == '' && this.form.claimType == ''
          //     && this.form.contracttermType == '' && this.form.effectiveSDate == '' && this.form.providerCode == undefined
          //     && this.form.supplierName == undefined && this.form.caseStatus == undefined
          //   ) {
          //       this.$message.warning('请至少录入一项查询条件');
          // } else {
          //   this.defaultData = false;
          //   this.gettableData(1);
          // }
        },
/*        gettableData(page) {

            let startTime = "";
            let endTime = "";
            let effectiveDate = this.form.effectiveSDate;
            if('' != effectiveDate) {
              startTime = effectiveDate[0];
              endTime = effectiveDate[1];
            }
            //参数集合
            const baseSupplierContract = {
                pageNum:page,
                pageSize:this.pageInfo.pageSize,
                rptNo:this.form.rptNo,
                name: this.form.name,
                claimType: this.form.claimType,
                contracttermType: this.form.contracttermType,
                cvaliDate: startTime,
                expiryDate :endTime,
                providerCode:this.form.providerCode,
                servcomNo:this.form.supplierName,
                caseStatus:this.form.caseStatus,
            };

              let currentDate = new  Date();
              let signEndDate  = this.dateformat('yyyy-MM-dd',currentDate);
              currentDate.setMonth(currentDate.getMonth() - 3);
              let signStartDate = this.dateformat('yyyy-MM-dd',currentDate);
            //参数集合
            const firstParam = {
              pageNum:page,
              pageSize:this.pageInfo.pageSize,
              caseStatus:'01',
              // cvaliDate:signStartDate,
              // endDate:signEndDate,
            };

            let param = '';
            if(this.defaultData) {
              param = firstParam;
            } else {
              param = baseSupplierContract;
            }
            // getSupplierContractList(baseSupplierContract).then(response => {
            //      this.totalNum = response.total;
            //      this.tableData = response.rows;
            //       this.loading = false
            // }).catch(error => {
            //   console.log(error);
            // });

        },*/

        viewStream(row) {
          // this.$router.push({
          //   path: '/basic-info/contractManage-edit',
          //   query: {status:'update',rptNo: row.rptNo,flag : row.flag ,providerCode :row.providerCode}
          //
          // })
        },
        claimDispatch() {
          //this.dialogVisable = true;
          this.dialogVisible = true;

        },
        exportData() {
          // this.$router.push({
          //   path: '/claims-handle/discussion',
          //   query: {discussion:'01'}
          // })
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
          this.ids = selection.map(item => item.userId);
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

