/* eslint-disable */
<template>
  <div id="receive_div" class="receive_div">
    <div id="receive_form_div" class="receive_form_div">
      <el-card class="receive_form_card">
        <el-form ref="resetData" :model="form" label-width="100px" label-position="right">
          <el-row>
            <el-col :span="8">
              <el-form-item label="供应商类型：" prop="suppliertype" class="item1">
                <el-select v-model="form.suppliertype" class="inp1" size="mini" placeholder="请选择">
                  <el-option v-for="option in dict.supplier_type" :key="option.id" :label="option.label" :value="option.value" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="供应商名称：" prop="chname" class="item1">
                <el-input v-model="form.chname" class="inp1" size="mini" placeholder="请输入" @keyup.enter.native="initData"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合同名称：" prop="contractname" class="item1">
                <el-input v-model="form.contractname" class="inp1" size="mini" placeholder="请输入" @keyup.enter.native="initData"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="签约期间：" class="item1" prop="date1">
                <el-date-picker
                  v-model="form.date1"
                  style="width:220px;"
                  size="mini"
                  type="daterange"
                  placeholder="选择日期"
                />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合同状态：" prop="contstate" class="item1">
                <el-select v-model="form.contstate" class="inp1" size="mini" placeholder="请选择">
                  <el-option v-for="option in dict.contract_status" :key="option.id" :label="option.label" :value="option.value" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item class="but1">
              <el-button size="mini" type="success" icon="el-icon-search" @click="initData">查 询</el-button>
              <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('resetData')">重 置</el-button>
            </el-form-item>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <div id="receive_pending_completed_div" class="receive_pending_completed_div">
      <el-card class="receive_pending_completed_card">
        <div slot="header">
          <span>合同列表({{ pendingTotalNum }})</span>
        </div>
        <div>
          <el-table
            :data="pendingTableData"
            size="mini"
            tooltip-effect="dark"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
          >
            <el-table-column prop="suppliercode" label="供应商编码" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="chname" label="供应商名称" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="address" label="所属地区" align="center" show-overflow-tooltip />
            <el-table-column prop="suppliertype" label="供应商类型" :formatter="getSupplierTypeName" align="center" show-overflow-tooltip />
            <el-table-column prop="contstate" label="是否有效" :formatter="getContractStateName" align="center" show-overflow-tooltip />
            <el-table-column prop="contractcode" label="合同编码" align="center" show-overflow-tooltip />
            <el-table-column prop="contractname" label="合同名称" align="center" show-overflow-tooltip />
            <el-table-column prop="contractDate" label="合同起止日期" align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button size="mini" type="text" :disabled=" scope.row.state=='02' " @click="dealClick(scope.row)">新增</el-button>
                <!-- <span style="font-size:15px;color: #66b1ff">/</span> -->
                <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;" :disabled="scope.row.contractcode==null" @click="update(scope.row)">编辑
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            :current-page="pendingPageInfo.currentPage"
            :page-size="pendingPageInfo.pageSize"
            layout="  prev, pager, next,sizes, jumper"
            :total="pendingTotalNum"
            :page-sizes="pendingPageInfo.pageSizes"
            class="pages"
            @current-change="pendingHandleCurrentChange"
            @size-change="supplierHandleSizeChange"
          />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
    import { getContractTableInfo } from '@/api/investigate/supplierManagement'
    import {get} from "@/api/dictDetail";

    export default {
        dicts:['contract_status','supplier_type'],
        data() {
            return {
                form: {
                    suppliertype: '',
                    chname: '',
                    contractname: '',
                    contstate: '',
                    startTime: '',
                    enddate:'',
                    date1:[],
                    signaturedate:''
                },
                queryForm: {
                  suppliertype: '',
                  chname: '',
                  contractname: '',
                  contstate: '',
                  startTime: '',
                  enddate:'',
                  date1:[],
                  signaturedate:''
                },
                //pageChange:true,
                datearray:[],
                supplierTypes:[],
                contractTypes:[],
                pendingTableData: [],
                pendingTotalNum: 0,
                idKey: 'contNo', // 标识列表数据中每一行的唯一键的名称
                resultInfo:[],
                pendingPageInfo: {
                    currentPage: 1,
                    pageSize: 10,
                    pageSizes:[5,10,20,30,50,100]
                },
            }
        },

        computed: {
            initDate: function () {
                const currentDate = new Date()
                const endDate = currentDate.getFullYear() + '-' + (currentDate.getMonth() + 1) + '-' + currentDate.getDate()
                currentDate.setMonth(currentDate.getMonth() - 1)
                const startDate = currentDate.getFullYear() + '-' + (currentDate.getMonth() + 1) + '-' + currentDate.getDate()
                return [startDate, endDate]
            }
        },

        // 获取跳转地址的数据
        created: function() {
          this.form.date1 = this.initDate
          this.initData();
        },
        methods: {
            initData(){
              this.pendingPageInfo.currentPage = 1
              this.pendingPageInfo.pageSize = 10

              this.queryForm.suppliertype = this.form.suppliertype
              this.queryForm.chname = this.form.chname
              this.queryForm.contractname = this.form.contractname
              this.queryForm.contstate = this.form.contstate
              this.queryForm.startTime = this.form.startTime
              this.queryForm.enddate = this.form.enddate
              this.queryForm.date1 = this.form.date1
              this.queryForm.signaturedate = this.form.signaturedate

              //this.pageChange = true
              this.getPendingTableData(1)
            },
            supplierHandleSizeChange(val) {
                this.pendingPageInfo.pageSize = val;
                //if (this.pageChange){
                    this.getPendingTableData(this.pendingPageInfo.currentPage)
                //}
            },

            // peneding
            pendingHandleCurrentChange(newPage) {
                this.pendingPageInfo.currentPage = newPage

                //if(this.pageChange){
                    this.getPendingTableData(newPage)
                //}
            },

            //重置
            reset(resetData) {
                this.$refs[resetData].resetFields()
                // this.getPendingTableData(1)
                /*this.pendingTableData = []
                this.pendingTotalNum = 0
                this.pageChange = false*/
                // this.form.contstate = ''
                // this.form.date1 = this.initDate

              /*this.queryForm = {
                  suppliertype: '',
                  chname: '',
                  contractname: '',
                  contstate: '',
                  startTime: '',
                  enddate:'',
                  date1:[],
                  signaturedate:''
              },
              this.queryForm.date1 = this.form.date1*/
            },

            //查询
            getTableData() {
                this.getPendingTableData(1)
                this.pendingPageInfo.currentPage = 1
                //this.pageChange = true
            },

            getPendingTableData(page) {
                let nowPage = page
                let startdate = ""
                let enddate = ""
                let contractStatus = this.queryForm.contstate

                if(this.queryForm.date1 != null && this.queryForm.date1 != '' && this.queryForm.date1.length !== 0){
                    this.datearray = this.queryForm.date1.toString().split(',')//时间日期按逗号分割
                    if(this.queryForm.date1 != null && this.queryForm.date1 != ''){
                        let start = new Date(this.datearray[0])
                        let end = new Date(this.datearray[1])
                        // console.log('分割后时间'+this.datearray+" first:"+start+" end:"+end)
                        //时间格式转换
                        startdate  = start.getFullYear() + '-' + this.addZero((start.getMonth() + 1)) + '-' + this.addZero(start.getDate()) //+ ' ' +
                        this.addZero(start.getHours()) + ':' + this.addZero(start.getMinutes()) + ':' + this.addZero(start.getSeconds())
                        enddate  = end.getFullYear() + '-' + this.addZero((end.getMonth() + 1)) + '-' + this.addZero(end.getDate()) //+ ' ' + '24:59:59'
                        this.addZero(end.getHours()) + ':' + this.addZero(end.getMinutes()) + ':' + this.addZero(end.getSeconds())
                    }
                }
                // console.log(startdate+'-----------'+enddate)
                if((this.queryForm.suppliertype == '' || this.queryForm.suppliertype == null) &&
                    (this.queryForm.chname == '' || this.queryForm.chname == null) &&
                    (this.queryForm.contractname == '' || this.queryForm.contractname == null) &&
                    (contractStatus == '' || contractStatus == null) &&
                    (this.queryForm.date1 == '' || this.queryForm.date1 == null)){

                    this.$message.warning('请至少选择一个条件！');
                    return;
                }

                //参数集合
                const contractDTO = {
                    nowPage:nowPage,
                    pageSize:this.pendingPageInfo.pageSize,
                    suppliertype:this.queryForm.suppliertype,
                    chname:this.queryForm.chname,
                    contractname:this.queryForm.contractname,
                    contstate:contractStatus,
                    startdate:startdate,
                    enddate:enddate
                }

                // console.log(contractDTO)
                getContractTableInfo(contractDTO).then(response => {
                    this.pendingTableData = response.data.contractList
                    // this.resultInfo = response.data.contractList
                    //  console.log(this.pendingTableData)
                    this.pendingTotalNum = response.data.resultCount
                    // console.log(this.pendingTotalNum)
                }).catch(error => {
                    // console.log(error)
                })
            },
            //转换时间
            addZero(s) {
                return s < 10 ? '0' + s : s
            },

            getSupplierTypeName(row,col){
                return this.dict.label.supplier_type[row.suppliertype];
            },
            getContractStateName(row,col){
                return this.dict.label.contract_status[row.contstate];
            },

            dealClick(value) {
                if (value === null || value === '') {
                    alert('供应商为空')
                }
                this.$router.push({
                    path:'newContract', //新增页面
                    query: {
                        suppliercode: value.suppliercode,
                        suppliertype: value.suppliertype
                    }
                })
            },
            update(value){
                if(value === null || value === ''){
                    alert('供应商编码为空')
                }
                this.$router.push({
                    path: 'updateContract',//修改页面
                    query: {
                        surveyNo: value.suppliercode,
                        contractno:value.contractno
                    }
                })
            },
            alertMsg(title, msg, type) {
                this.$notify({
                    title: title,
                    message: msg,
                    type: type
                })
            },
        }
    }
</script>

<style scoped>
  /*自定义样式*/
  .receive_div {
    width: 100%;
    margin-top: 10px;
  }

  .receive_form_div {
    width: 100%;
  }

  .receive_form_card {
    margin: 0px 10px;
    padding: 20px;
    border-radius: 5px;
  }

  .receive_pending_completed_div {
    width: 100%;
    position: relative;
    margin-bottom: 33px;
  }

  .receive_pending_completed_card {
    margin: 10px 10px 10px 10px;
    border-radius: 5px;
  }

  .item1 {
    margin-left: 2%;
    margin-bottom: 0px;
  }

  .inp1 {
    width: 220px;
    padding: 0px;
    margin-left: 0px;
  }

  .but1 {
    text-align: right;
    margin-right: 10px;
    margin-bottom: 10px;
  }

  .pages {
    float: right;
    margin: 10px 20px 10px;
  }

  .receive_table {
    width: 98%;
    padding: 0px;
    margin-left: 1%;
    margin-right: 1%;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

  .el-dialog__wrapper ::v-deep .el-dialog__header {
    padding:15px 20px 5px;
    border-bottom: 1px solid #ebebeb;
  }

  .el-dialog__wrapper ::v-deep .el-dialog__title{
    font-size:16px;
    line-height: 20px;
  }

  .el-dialog__wrapper ::v-deep .el-dialog__headerbtn {
    top:16px;
  }

  /*.el-card ::v-deep .el-card__header {*/
  /*height:40px;*/
  /*padding: 5px 0px;*/
  /*}*/

  .el-card ::v-deep .el-card__body {
    padding: 0px;
  }

  .el-tabs ::v-deep .el-tabs__item {
    height:40px;
    width: 200px;
    text-align: center;
    padding-bottom: 0px;
  }

  .el-tabs ::v-deep .el-tabs__active-bar {
    width: 200px;
  }

  .el-tabs ::v-deep .el-tabs__nav-wrap::after{
    height: 1px;
  }

</style>
