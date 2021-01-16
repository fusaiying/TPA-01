<template>
  <div class="app-container">
      <el-card class="box-card" style="margin-top: 10px;">
        <el-form ref="queryForm" :model="form" style="padding-bottom: 30px;padding-top: 30px" label-width="130px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="合约编码：" prop="contractNo">
                <el-input v-model="form.contractNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约名称：" prop="contractName" >
                <el-input v-model="form.contractName" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约类型：" prop="contractType">
                <el-select v-model="form.contractType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in contractTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="合约限期类型：" prop="contracttermType">
                <el-select v-model="form.contracttermType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in contractLimitTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约有效期：" prop="effectiveSDate">
                <el-date-picker
                  v-model="form.effectiveSDate"
                  style="width:220px;"
                  size="mini"
                  type="daterange"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                />
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="服务机构名称：" prop="providerCode">
                <el-select v-model="form.providerCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in providerInfoSelects" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="供应商名称：" prop="supplierName">
                <el-select v-model="form.supplierName" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in supplierInfoSelects" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="状态：" prop="bussinessStatus">
                <el-select v-model="form.bussinessStatus" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in statusSlects" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

          </el-row>

          <div style="text-align: right; margin-right: 10px;">
            <el-row>
              <el-form-item class="but1">
                <el-button size="mini" type="success" icon="el-icon-search" @click="searchByFormParms">查 询</el-button>
                <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('queryForm')">重 置</el-button>
              </el-form-item>
            </el-row>
          </div>

        </el-form>

        <el-divider/>

        <div>
          <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>合约信息（{{ pendingTotalNum }}）</span>
            <el-button icon="el-icon-plus" style="float: right; margin-top: 10px;" type="primary" size="mini"
                       @click="addHandle('add')">新增
            </el-button>
          </div>
          <el-table
            :data="pendingTableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="dark"
            class="receive_table"
            :cell-style="changeCellStyle"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
          >
            <el-table-column prop="contractNo" label="合约编码" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="contractName" label="合约名称" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="servcomNo" label="供应商名称" :formatter="getServcomNoName" align="center" show-overflow-tooltip />
            <el-table-column prop="providerName" label="服务机构名称" align="center" show-overflow-tooltip />

            <el-table-column prop="contractType" label="合约类型" :formatter="getContractTypeName" align="center" show-overflow-tooltip />
            <el-table-column prop="contracttermType" label="合约期限类型" :formatter="getContractLimitTypeName" align="center" show-overflow-tooltip />
            <el-table-column prop="cvaliDate" label="合约有效期" :formatter="getValiDate" align="center" show-overflow-tooltip />
            <el-table-column prop="status" label="状态"  :formatter="getStatuTypeName" align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;"  v-if="scope.row.bussinessStatus=='01'"  @click="update(scope.row)">
                  编辑
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--分页组件-->
          <pagination
            v-show="pendingTotalNum>0"
            :total="pendingTotalNum"
            :page.sync="pendingPageInfo.currentPage"
            :limit.sync="pendingPageInfo.pageSize"
            @pagination="initData"
          />
        </div>
      </el-card>
  </div>
</template>

<script>
    import {
      getSupplierContractList,
      getAllBaseSupplierInfo,
      getAllBaseProviderInfo
    } from '@/api/contractManage/contractManagement'
    export default {
        data() {
            return {
                form: {
                  contractNo: '',
                  contractName: '',
                  contractType: '',
                  contracttermType: '',
                  servcomNo:'',
                  effectiveSDate:[],
                  contState:''
                },
                queryForm: {
                  contractNo: '',
                  contractName: '',
                  contractType: '',
                  contracttermType: '',
                  servcomNo:'',
                  effectiveSDate:[],
                  contState:'',
                  cvaliDate: '',
                  expiryDate:'',
                },
                datearray:[],
                supplierTypes:[],
                contractTypes:[],
                statusSlects:[],
                pendingTableData: [],
                pendingTotalNum: 0,
                idKey: 'contractNo', // 标识列表数据中每一行的唯一键的名称
                resultInfo:[],
                pendingPageInfo: {
                    currentPage: 1,
                    pageSize: 10,
                    pageSizes:[5,10,20,30,50,100]
                },
                contractLimitTypes: [],
                providerTypes:[],
                supplierInfoSelects : [],
                providerInfoSelects:[],
                loading: true,
                defaultData : true,
            }
        },
      mounted(){
        this.getDicts("contract_limit_type").then(response => {
          this.contractLimitTypes = response.data;
        });
        this.getDicts("contract_type").then(response => {
          this.contractTypes = response.data;
        });
        this.getDicts("state").then(response => {
          this.statusSlects = response.data;
        });
        this.allBaseSupplierInfo();
        this.allBaseProviderInfo();
      },
      computed: {

      },

      // 获取跳转地址的数据
      created: function() {
        this.initData();
        this.allBaseSupplierInfo();
      },
      methods: {
        initData(){
          let currentDate = new Date();
          let beforeDate = new Date();

          let endStr = this.dateformat('yyyy-MM-dd',currentDate);
          currentDate.setMonth(currentDate.getMonth() - 3);
          let starStr = this.dateformat('yyyy-MM-dd',currentDate);

          this.getPendingTableData(this.pendingPageInfo.currentPage);
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
        allBaseProviderInfo(){
          const query ={
            pageNum:1,
            pageSize:10000,
            xadef:'select',
          };
          getAllBaseProviderInfo(query).then(response => {
           // this.providerInfoSelects = response.data;
            if(response.data != null) {
              for(let i=0; i<response.data.length; i++) {
                let obj= new Object();
                obj.dictLabel = response.data[i].chname1 + " - " + response.data[i].enname1;
                obj.dictValue = response.data[i].providerCode;
                this.providerInfoSelects.push(obj);
              }
            }
          }).catch(error => {
            console.log(error);
          })
        },
        allBaseSupplierInfo (){
          const query ={
            status:'Y'
          };
          getAllBaseSupplierInfo(query).then(response => {
            if(response.data != null) {
              for(let i=0; i<response.data.length; i++) {
                let obj= new Object();
                obj.dictLabel = response.data[i].chname + " - " + response.data[i].enname;
                obj.dictValue = response.data[i].serialNo;
                this.supplierInfoSelects.push(obj);
              }
            }
          }).catch(error => {
            console.log(error);
          })

        },
        changeCellStyle (rows, column, rowIndex, columnIndex) {
          if(rows.row.bussinessStatus != "01"){
            return 'color: red'  // 修改的样式
          }else{
            return ''
          }
        },
        //重置
        reset(queryForm) {
            this.$refs[queryForm].resetFields()
        },
        //查询
        getTableData() {
            this.defaultData = false;
            this.getPendingTableData(1);
        },
        searchByFormParms(){
          // if(this.form.contractNo == '' && this.form.contractName == '' && this.form.contractType == ''
          //     && this.form.contracttermType == '' && this.form.effectiveSDate == '' && this.form.providerCode == undefined
          //     && this.form.supplierName == undefined && this.form.bussinessStatus == undefined
          //   ) {
          //       this.$message.warning('请至少录入一项查询条件');
          // } else {
          //   this.defaultData = false;
          //   this.getPendingTableData(1);
          // }

          this.defaultData = false;
          this.getPendingTableData(1);
        },
        getPendingTableData(page) {

            let startTime = "";
            let endTime = "";
            let effectiveDate = this.form.effectiveSDate;
            if('' != effectiveDate) {
              startTime = effectiveDate[0];
              endTime = effectiveDate[1];
            } else {
                let currentDate = new  Date();
                endTime   = this.dateformat('yyyy-MM-dd',currentDate);
                currentDate.setMonth(currentDate.getMonth() - 3);
                startTime = this.dateformat('yyyy-MM-dd',currentDate);
            }
            //参数集合
            const baseSupplierContract = {
                pageNum:page,
                pageSize:this.pendingPageInfo.pageSize,
                contractNo:this.form.contractNo,
                contractName: this.form.contractName,
                contractType: this.form.contractType,
                contracttermType: this.form.contracttermType,
                cvaliDate: startTime,
                endDate :endTime,
                providerCode:this.form.providerCode,
                servcomNo:this.form.supplierName,
                bussinessStatus:this.form.bussinessStatus,
                orderByColumn:'create_time',
                isAsc:'desc'
            };


            //参数集合
            const firstParam = {
              pageNum:page,
              pageSize:this.pendingPageInfo.pageSize,
              bussinessStatus:'01',
              cvaliDate:startTime,
              endDate:endTime,
              orderByColumn:'create_time',
              isAsc:'desc'
            };

            let param = '';
            if(this.defaultData) {
              param = firstParam;
            } else {
              param = baseSupplierContract;
            }
            getSupplierContractList(param).then(response => {
                 this.pendingTotalNum = response.total;
                 this.pendingTableData = response.rows;
                 this.loading = false
            }).catch(error => {
              console.log(error);
            });

        },
        getContractTypeName(row,col){
            return this.selectDictLabel(this.contractTypes, row.contractType)
        },
        getContractLimitTypeName(row,col){
          return this.selectDictLabel(this.contractLimitTypes, row.contracttermType)
        },
        getValiDate(row,col){
          let str = '';
          if(row.endDate != null) {
            str = " \u3000  " + row.endDate;
          }
          return  row.cvaliDate + str;
        },
        getStatuTypeName(row,col){
          return this.selectDictLabel(this.statusSlects, row.bussinessStatus)
        },
        getServcomNoName(row,col) {
          return this.selectDictLabel(this.supplierInfoSelects, row.servcomNo);
        },
        // getProviderCodeName(row,col) {
        //   return this.selectDictLabel(this.providerInfoSelects, row.providerCode);
        // },
        alertMsg(title, msg, type) {
            this.$notify({
                title: title,
                message: msg,
                type: type
            })
        },
        addHandle(status){

          // this.$router.push({
          //   path: '/basic-info/contractManageDetail',
          //   query: {id:'19',contractNo: 'testC',flag:'02',servcomNo:'4224',providerCode:'M000000121'}
          // })

          this.$router.push({
            path: '/basic-info/contractManage-edit',
            query: {status: status}
          });
        },
        update(row) {
          this.$router.push({
            path: '/basic-info/contractManage-edit',
            query: {status:'update',contractNo: row.contractNo,flag : row.flag ,providerCode :row.providerCode}

          })
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

