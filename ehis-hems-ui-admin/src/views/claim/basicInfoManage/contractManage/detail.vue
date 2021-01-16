<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>合约管理</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <div style="margin-top: 0px">
        <span style="size: 2px">基本信息</span>
      </div>
      <el-divider/>
      <form v-if="formTab">
        <el-row v-loading="dutyLoading" style="margin: 0 40px;padding-bottom: 20px">
          <el-col :span="8">
            <span class="info_span to_right">合约编码：</span><span class="info_span">{{ serverContractInfo.contractNo }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">供应商：</span><span class="info_span">{{ getSuppNameByValue(serverContractInfo.servcomNo) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约名称：</span><span class="info_span">{{ serverContractInfo.contractName }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约类型：</span><span class="info_span">{{ getContractTypeNameByValue(serverContractInfo.contractType) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约期限类型：</span><span class="info_span">{{ getContractLimitTypeNameByValue(serverContractInfo.contracttermType) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合同分类：</span><span class="info_span">{{ getContractCategorysByValue(serverContractInfo.contractsort) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">提前协商天数：</span><span class="info_span">{{ serverContractInfo.contractadvance }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">状态：</span><span class="info_span">{{ getStatuTypeNameByValue(serverContractInfo.bussinessStatus) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约有效期：</span>
            <span class="info_span">{{ serverContractInfo.cvaliDate }} /  {{ serverContractInfo.expiryDate }} </span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">备注：</span><span class="info_span">{{ serverContractInfo.remark }}</span>
          </el-col>
       </el-row>
      </form>


      <!-- 服务机构  -->
      <form v-if="!formTab">
        <el-row v-loading="dutyLoading" style="margin: 0 40px;padding-bottom: 20px">
          <el-col :span="8">
            <span class="info_span to_right">服务机构名称：</span><span class="info_span">{{ providerContractInfo.providerName }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约编码：</span><span class="info_span">{{ providerContractInfo.contractNo }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">状态：</span><span class="info_span">{{ getStatuTypeNameByValue(providerContractInfo.bussinessStatus) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约名称：</span><span class="info_span">{{ providerContractInfo.contractName }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约甲方：</span><span class="info_span">{{ providerContractInfo.contractPartyA }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约乙方：</span><span class="info_span">{{ providerContractInfo.contractPartyB }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约丙方：</span><span class="info_span">{{ providerContractInfo.contractPartyC }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约类型：</span><span class="info_span">{{ getContractTypeNameByValue(providerContractInfo.contractType) }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">关联合约：</span><span class="info_span">{{ providerContractInfo.connectedContract }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">押金金额：</span><span class="info_span">{{ providerContractInfo.deposit }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约有效期：</span><span class="info_span">{{ providerContractInfo.cvaliDate }}{{ providerContractInfo.expiryDate }}</span>
          </el-col>

          <el-col :span="8">
            <span class="info_span to_right">诊疗费折扣：</span><span class="info_span">{{ providerContractInfo.treatmentDiscount }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">次均控费：</span><span class="info_span">{{ providerContractInfo.averageCost }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">床位费折扣：</span><span class="info_span">{{ providerContractInfo.bedDiscount }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">次均控费类型：</span><span class="info_span">{{ providerContractInfo.type }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">护理费折扣：</span><span class="info_span">{{ providerContractInfo.allowance }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">就诊次数：</span><span class="info_span">{{ providerContractInfo.advicenum }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">医药费折扣：</span><span class="info_span">{{ providerContractInfo.costs }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">次均控费除外项目：</span><span class="info_span">{{ providerContractInfo.averageCostExcept }}</span>
          </el-col>

          <el-col :span="8">
            <span class="info_span to_right">折扣信息：</span><span class="info_span">{{ providerContractInfo.discountinfo }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">特殊费折扣信息：</span><span class="info_span">{{ providerContractInfo.specialDiscount }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">折扣除外项目：</span><span class="info_span">{{ providerContractInfo.distcoteItem }}</span>
          </el-col>
          <el-col :span="8">
          <span class="info_span to_right">合作单位：</span><span class="info_span">{{ getCooperativeUnitNameByValue(providerContractInfo.cooperativeUnit) }}</span>
         </el-col>

          <el-col :span="8">
            <span class="info_span to_right">直结类型：</span><span class="info_span">{{ providerContractInfo.straight }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约终止日期：</span><span class="info_span">{{ providerContractInfo.endDate }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">合约终止原因：</span><span class="info_span">{{ providerContractInfo.reason }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">联系人：</span><span class="info_span">{{ providerContractInfo.reason }}</span>
          </el-col>

          <el-col :span="8">
            <span class="info_span to_right">手机：</span><span class="info_span">{{ providerContractInfo.phone }}</span>
          </el-col>

          <el-col :span="8">
            <span class="info_span to_right">盒脊编号：</span><span class="info_span">{{ providerContractInfo.boxRidgeCode }}</span>
          </el-col>


          <el-col :span="8">
            <span class="info_span to_right">办公电话：</span><span class="info_span">{{ providerContractInfo.tel }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">电子邮件：</span><span class="info_span">{{ providerContractInfo.email }}</span>
          </el-col>
          <el-col :span="8">
            <span class="info_span to_right">最后维护人：</span><span class="info_span">{{ providerContractInfo.exPer }}</span>
          </el-col>

          <el-col :span="8">
            <span class="info_span to_right">最后维护时间：</span><span class="info_span"></span>
          </el-col>

        </el-row>
      </form>
      <el-divider/>

      <!-- 历史合约信息 START-->
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>历史合约信息</span>
        </div>
        <el-table
          :data="pendingTableData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}">
          <el-table-column prop="contractNo" label="合约编码" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractName" label="合约名称" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractType" label="合约类型" :formatter="getContractTypeName" align="center" show-overflow-tooltip />
          <el-table-column prop="contracttermType" label="合约期限类型" :formatter="getContractLimitTypeName" align="center" show-overflow-tooltip />
          <el-table-column prop="cvaliDate" label="合约有效期" :formatter="getValiDate" align="center" show-overflow-tooltip />
          <el-table-column prop="bussinessStatus" label="状态" :formatter="getStatuTypeName"  align="center" show-overflow-tooltip />
        </el-table>
      </div>
      <!-- 历史合约信息 END-->


      <!--供应商服务项目 Start-->
      <div v-if="formTab" style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>供应商服务项目</span>
      </div>
      <el-form v-if="formTab"  size="small">
        <el-table
          :data="serverContractData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          style="width: 100%">

          <el-table-column prop="serviceCode" label="服务项目名称" align="center">
            <template slot-scope="scope">
              <span >{{ getServiceName(scope.$index, scope.row) }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="supplierCode" label="供应商名称" align="center">
            <template slot-scope="scope">
              <span>{{getSuppName(scope.$index, scope.row)}}</span>
            </template>
          </el-table-column>

          <el-table-column prop="minPrice" label="服务项目价格" style="width: 280px" align="center">
            <template slot-scope="scope" >
              <span> {{ scope.row.minPrice }} - {{ scope.row.maxPrice }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="settleType" label="结算方式" align="center">
            <template slot-scope="scope">
              <span>{{gettleTypeName(scope.$index, scope.row)}}</span>
            </template>

          </el-table-column>
          <el-table-column prop="settleCurrency" label="结算币种"  align="center">
            <template slot-scope="scope">
              <span>{{getCurrencyName(scope.$index, scope.row)}}</span>
            </template>
          </el-table-column>

          <el-table-column prop="limitnum" label="日限次数" style="width: 280px" align="center">
            <template slot-scope="scope" >
              <span >{{ scope.row.limitnum }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <!--供应商服务项目 END-->


      <!--附件信息 START-->
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>附件信息</span>
        </div>
        <el-table
          :data="fileTableData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="annexName" label="附件名称"  align="center" show-overflow-tooltip />
          <el-table-column prop="annexType" label="附件类型"  align="center" show-overflow-tooltip />
          <el-table-column prop="uploadMan" label="上传人"  align="center" show-overflow-tooltip />
          <el-table-column prop="uploadTime" label="上传时间" align="center" show-overflow-tooltip />
          <el-table-column prop="remak" label="备注" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="downloadFile(scope.row)">下载</el-button>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--附件信息 END-->

    </el-card>
  </div>
</template>
<script>
  let that
  import {
    getSupplierContractList ,
    getSupplierContractDetail,
    getContractServerList ,
    getAllBaseSupplierInfo,
    getAllBaseServiceInfo,
    getSupplierContractBakDetail,
    getFileList ,

  } from '@/api/contractManage/contractManagement'

  export default {
    data() {
      return {
        fileTableData:[],
        serverContractData: [],
        pendingTableData: [],
        minLoading: false,
        loading:false,
        dutyLoading: false,

        straightT:{'01':'门诊','02':'住院','03':'体检','04':'牙科','05':'生育','06':'眼科','07':'疫苗'},
        serverContractInfo: {
          contractNo:'',
          servcomNo: '',
          contractName:'',
          contractType:'',
          contracttermType:'',
          cvaliDate:'',
          expiryDate:'',
          contractadvance:'',
          bussinessStatus:'',
          remark:'',
          contractsort:'',
          effectiveSDate:''
        },
        providerContractInfo :{
          networkHospitalType: [],
          ads:['01'],
          contractControlFlagt:[],
          providerCode:'',
          providerName:'',
          contractNo:'',
          status:'',
          contractName:'',
          contractPartyA:'',
          contractPartyB:'',
          connectedContract:'',
          contractType:'',
          deposit:'',
          effectiveSDate:'',
          treatmentDiscount:'',
          averageCost:'',
          bedDiscount:'',
          type:'',
          allowance:'',
          advicenum:'',
          costs:'',
          averageCostExcept:'',
          discountinfo:'',
          specialDiscount:'',
          project:'',
          cooperativeUnit:'',
          endDate:'',
          reason:'',
          relp:'',
          phone:'',
          boxRidgeCode:'',
          tel:'',
          email:'',
          exPer:'',
          exEndate:'',
          straight:'',
          bussinessStatus:''
        },
        id:'', // 主键
        contractNo:'',// 合约编码
        flag:'',     // 01：供应商合约  02:机构合约
        servcomNo:'',  //供应商编码
        providerCode:'', //机构编码
        formTab : true,  // 详情表单显示
        contractCategorys:[],
        contractLimitTypes: [],
        contractTypes:[],
        statusSlects:[],
        supplierInfoSelects:[],
        cooperativeUnitSelects:[],
        serviceInfoSelects:[],
      }
    },

     mounted() {

     // this.id = this.$route.query.id;
      this.contractNo = this.$route.query.contractNo;
      this.flag =  this.$route.query.flag;
      this.servcomNo = this.$route.query.servcomNo;
      this.providerCode = this.$route.query.providerCode;

      this.getDicts("cooperator").then(response => {
        this.cooperativeUnitSelects = response.data;
      });
      this.getDicts("contract_limit_type").then(response => {
        this.contractLimitTypes = response.data;
      });
      this.getDicts("currency").then(response => {
        this.currencys = response.data;
      });
      this.getDicts("clearing_form").then(response => {
        this.settleTypeSelect = response.data;
      });
      this.getDicts("contract_type").then(response => {
        this.contractTypes = response.data;
      });
      this.getDicts("contract_category").then(response => {
        this.contractCategorys = response.data;
      });
      this.getDicts("state").then(response => {
        this.statusSlects = response.data;
      });

      if (this.flag == '01') {
        //供应商合约
        this.formTab = true;
        this.getInfo(1);
      } else if(this.flag == '02') {
        //机构合约
        this.formTab = false;
        this.getInfo(2);
      } else if(this.flag == '03') {
        //机构合约
        this.formTab = false;
        // 要根据服务机构id 查到最近的一条有效的合约
        this.queryInitParams();
      }
    },
      methods: {
      getInfo(type){
        this.getDetail();
        this.allBaseSupplierInfo();
        this.allAllBaseServiceInfo();
        this.getSupplierContractListByChangeType(type);
        if(this.formTab) {
          this.getServerContractData();
        }
      },
      getCooperativeUnitNameByValue(value){
        return this.selectDictLabel(this.cooperativeUnitSelects, value)
      },
      getContractTypeName(row,col){
        return this.selectDictLabel(this.contractTypes, row.contractType)
      },
      getContractTypeNameByValue(value){
        return this.selectDictLabel(this.contractTypes, value)
      },
      getContractLimitTypeName(row,col){
        return this.selectDictLabel(this.contractLimitTypes, row.contracttermType)
      },
      getContractLimitTypeNameByValue(value){
        return this.selectDictLabel(this.contractLimitTypes, value)
      },
      getContractCategorysByValue(value){
          return this.selectDictLabel(this.contractCategorys, value)
      },
      getValiDate(row,col){
        let str = '';
        if(row.endDate != null) {
          str = "  \u3000 " + row.endDate;
        }
        return  row.cvaliDate + str;
      },
      getStatuTypeName(row,col){
        return this.selectDictLabel(this.statusSlects, row.bussinessStatus)
      },
      getStatuTypeNameByValue(value){
        return this.selectDictLabel(this.statusSlects, value)
      },
      getSuppName(index,row) {
        return this.selectDictLabel(this.supplierInfoSelects, row.supplierCode);
      },
      getServiceName(index,row) {
        return this.selectDictLabel(this.serviceInfoSelects, row.serviceCode);
      },
      getSuppNameByValue(vulue) {
        return this.selectDictLabel(this.supplierInfoSelects, vulue);
      },
      gettleTypeName(index,row) {
        return this.selectDictLabel(this.settleTypeSelect, row.settleType);
      },
      getCurrencyName(index,row) {
        return this.selectDictLabel(this.currencys, row.settleCurrency);
      },
      goBack() {
        this.$router.go(-1)
      },
      downloadFile(){

      },
      allAllBaseServiceInfo(){
        const query ={
          xtype:'BaseServiceInfo'
        //  status:'02'
        };
        getAllBaseServiceInfo(query).then(response => {
          for(let i=0; i<response.rows.length; i++) {
            let obj= new Object();
            obj.dictLabel = response.rows[i].serviceName;
            obj.dictValue = response.rows[i].serviceCode;
            this.serviceInfoSelects.push(obj);
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
      getDetail(){
        if(this.contractNo == '' || this.contractNo == undefined) {
          return false;
        }
        getSupplierContractDetail(this.contractNo).then(response => {
          if('200' == response.code) {
            let detailData = response.data;
            if(this.formTab) {
              this.serverContractInfo = detailData;

            } else {
              this.providerContractInfo = detailData;
              // straightT
              let straightStr = '';
              if(detailData.straight != '') {
                let straightData =detailData.straight.split(",");
                for (let i=0; i<straightData.length; i++) {
                   let str = this.straightT[straightData[i]];
                   if(str != undefined) {
                     straightStr +="," + str;
                   }
                }
                this.providerContractInfo.straight = (straightStr.substring(1));
              }
            }
          }
        }).catch(error => {
          console.log(error);
        })
      },
      queryInitParams() {
        let query = {
          pageNum:1,
          pageSize:1,
        //  bussinessStatus:'01', todo:
          providerCode:this.providerCode,
          flag :'02',
          status:'Y',
          orderByColumn:'create_time',
          isAsc:'desc'
        };

        //查询数据
        getSupplierContractBakDetail(query).then(response => {
          if(response.rows != null) {
            let data = response.rows[0];
            // this.contractNo = data.contractNo;
            // this.flag =  data.flag;
            // this.servcomNo = data.servcomNo;
            // this.providerCode = data.providerCode;
            this.providerContractInfo = data;
            this.allBaseSupplierInfo();
            this.allAllBaseServiceInfo();
            this.getSupplierContractListByChangeType(2);
            //this.getInfo(2);
          }
        }).catch(error => {
          console.log(error)
        })
        },
      //历史合约信息 信息
      getSupplierContractListByChangeType(type) {
        let query = {};
        if(type == 1) {
          query = {
            nowPage:1,
            pageSize:10000,
            servcomNo:this.servcomNo
          }
        } else {
          query ={
            nowPage:1,
            pageSize:10000,
            providerCode:this.providerCode
          };
        }
        //查询数据
        getSupplierContractList(query).then(response => {
          this.pendingTableData = response.rows
        }).catch(error => {

        })
      },
      //供应商服务项目
      getServerContractData(){
        //参数集合
        const param = {
          nowPage:1,
          pageSize:1000,
          status:'Y',
          contractNo:this.contractNo,
          servcomNo:this.servcomNo,
        };
        getContractServerList(param).then(response => {
          //赋值
          this.serverContractData = response.rows;
        }).catch(error => {
          console.log(error);
        })
      },
    }
  }
</script>

<style scoped>
  .small-input {
    width: 80%;
  }

  .to_right {
    width: 120px;
    text-align: right;
  }
  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>
