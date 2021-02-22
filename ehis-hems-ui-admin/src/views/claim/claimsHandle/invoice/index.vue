<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="rptNo">
              <el-input v-model="formSearch.rptNo" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="批次号：" prop="batchNo">
              <el-input v-model="formSearch.batchNo" class="item-width" clearable size="mini" placeholder="请输入"
                        @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="就诊医院：" prop="hospitalCode"> <!-- providerInfoSelects -->
              <el-select filterable   v-model="formSearch.hospitalCode"  class="item-width" placeholder="请选择">
                <el-option
                  v-for="dict in providerInfoSelects"
                  :key="dict.dictLabel"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="发票盒号：" prop="invoiceBoxNo">
              <el-input v-model="formSearch.invoiceBoxNo" class="item-width" clearable size="mini" placeholder="请输入"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button :loading="searchLoad" size="mini" type="success" icon="el-icon-search" @click="searchHandle">查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm">重置</el-button>
          <!--<el-button size="mini" type="primary" icon="el-icon-refresh" @click="viewDetail">详情</el-button>-->
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>任务工作池</span>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;"> <!--   @expand-change="getMinData" -->
        <el-table-column  type="expand">
          <template slot-scope="scope">
            <el-table :data="scope.row.minData"
                      :header-cell-style="{color:'black',background:'#f8f8ff'}"
                      highlight-current-row
                      size="small"
                      v-loading="loading"
                      tooltip-effect="dark"
                      style="width: 100%;">
              <el-table-column prop="rptNo" label="报案号" align="center"/>
              <el-table-column prop="name" label="被保险人" align="center"/>
              <el-table-column label="操作" align="center">
                <template slot-scope="props">
                  <el-button size="small" type="text" @click="codePrint">条码打印</el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column sortable align="center" prop="batchNo" min-width="120" label="批次号" show-overflow-tooltip/>
        <el-table-column align="center" min-width="200" prop="invoiceBoxNo" label="发票盒号" show-overflow-tooltip>
          <template slot-scope="scope" >
            <template v-if="scope.row.editing" >
              <el-input  v-model="scope.row.invoiceBoxNo"  size="mini"></el-input>
            </template>
            <span  v-else>{{ scope.row.invoiceBoxNo }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="hospitalCode" :formatter="getHospitalName" min-width="120" label="就诊医院" show-overflow-tooltip/>
        <el-table-column align="center" prop="claimType" :formatter="getClaimTypeName" label="理赔类型" min-width="90" show-overflow-tooltip/>
        <el-table-column align="center" min-width="120" prop="isFiling" label="发票是否归档" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-if="scope.row.editing" style="width: 80px" v-model="scope.row.isFiling" size="mini"  class= "el-select item-width el-select--mini" placeholder="请选择">
              <el-option
                v-for="dict in ysOrNo"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
            <span  v-else>{{getYesOrNoName(scope.row.isFiling)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="isInvoiceBack" label="发票是否退回" min-width="120" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-if="scope.row.editing"  style="width: 80px" v-model="scope.row.isInvoiceBack" size="mini"  class= "el-select item-width el-select--mini" placeholder="请选择">
              <el-option
                v-for="dict in ysOrNo"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
            <span  v-else>{{getYesOrNoName(scope.row.isInvoiceBack)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="isSingle" label="批次是否单张发票" min-width="130" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-if="scope.row.editing" style="width: 80px"  v-model="scope.row.isSingle" size="mini"  class= "el-select item-width el-select--mini" placeholder="请选择">
              <el-option
                v-for="dict in ysOrNo"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
            <span  v-else>{{getYesOrNoName(scope.row.isSingle)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="organcode" min-width="120" label="机构" show-overflow-tooltip/>
        <el-table-column align="center" prop="remark" min-width="110" label="备注" show-overflow-tooltip>
          <template slot-scope="scope" >
            <template v-if="scope.row.editing" >
              <el-input  v-model="scope.row.remark"  size="mini"></el-input>
            </template>
            <span  v-else>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="createTime" min-width="110" label="档案新建日期" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | changeDate}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="updateTime" min-width="110" label="操作日期" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime | changeDate}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="updateBy" min-width="110" label="操作人" show-overflow-tooltip/>
        <el-table-column fixed="right" align="center" prop="editing" min-width="110"  label="操作" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.editing == false"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              v-if="scope.row.editing == true"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleSave(scope.$index, scope.row)">保存
            </el-button>

            <el-button
              v-if="scope.row.editing  == true"
              size="mini"
              type="text"
              @click="handleCancle(scope.$index, scope.row)">取消
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
    </el-card>
  </div>
</template>

<script>

  import { getAllBaseProviderInfo } from '@/api/contractManage/contractManagement'
  import moment from 'moment'
  import {invoiceData, updateInvoice} from '@/api/invoice/api'

  export default {
  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  components: {
  },
  data() {
    return {
      searchBtn:false,
      loading : false,
      searchLoad : false,
      formSearch: {
        rptNo: '',
        batchNo: '',
        hospitalCode: '',
        invoiceBoxNo: '',
      },
      tableData: [],
      pageInfo: {
        currentPage: 1,
        pageSize: 10,
        pageSizes:[5,10,20,30,50,100]
      },
      totalNum:0,
      ysOrNo:[],
      claimTypes:[],
      providerInfoSelects:[],

    }
  },
  mounted(){
    // sys_yes_no
    this.getDicts("sys_yes_no").then(response => {
      this.ysOrNo = response.data;
    });
    // claimType
    this.getDicts("claimType").then(response => {
      this.claimTypes = response.data;
    });
  },
  created() {
    this.initData();
    this.allBaseProviderInfo();
  },
  watch: {
  },
  computed: {

  },
  methods: {
    getYesOrNoName(value){
      return this.selectDictLabel(this.ysOrNo, value)
    },
    getClaimTypeName(row,col){
      return this.selectDictLabel(this.claimTypes, row.claimType)
    },
    handleSave(index,row){ //保存
      delete row.minData;
      updateInvoice(row).then(res => {
        if (res.code == '200') {
          row.editing = false;
          this.$message({
            message: '更新成功！',
            type: 'success',
            center: true,
            showClose: true
          });
        }
      }).finally(() => {
      })
    },
    handleEdit(index,row){ //编辑
      row.editing = true;
    },
    handleCancle(index,row){ //取消
      row.editing = false;
    },
    codePrint(){

    },
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    searchHandle() {
      this.searchBtn = true;
      this.initData();
    },
    initData(){
      this.loading = true;
      const params = {};
      params.pageNum = this.searchBtn ? 1 : this.pageInfo.currentPage;
      params.pageSize = this.searchBtn ? 10 : this.pageInfo.pageSize;
      params.rptNo = this.formSearch.rptNo;
      params.batchNo = this.formSearch.batchNo;
      params.hospitalCode = this.formSearch.hospitalCode;
      params.invoiceBoxNo = this.formSearch.invoiceBoxNo;


      invoiceData(params).then(res => {
          if (res.code == '200') {
            this.totalNum = res.total;

            if(this.totalNum ===0 && this.searchBtn){
              this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
            }

            let _data = res.rows;
            if (_data.length !== 0) {
              _data.forEach(item => {
                item.editing = false;
                item.minData = [item]
              })
            }
            this.tableData= _data;
          }
        }).finally(() => {
            this.searchBtn = false;
            this.loading = false;
      })
    },
    allBaseProviderInfo(){
      const query ={
        nowPage:1,
        pageSize:10000,
        xtype:'BaseProviderInfo',
        status:'Y'
      };
      getAllBaseProviderInfo(query).then(response => {
        console.log(response)
        for(let i=0; i<response.data.length; i++) {
          let obj= new Object();
          obj.dictLabel = response.data[i].chname1.toString();
          obj.dictValue = response.data[i].providerCode.toString();
          this.providerInfoSelects.push(obj);
        }
      }).catch(error => {
        console.log(error);
      })
    },
    getHospitalName(row,col) {
      let result = this.selectDictLabel(this.providerInfoSelects, row.hospitalCode)
      if(result != '') {
        return result;
      }
      return row.hospitalCode;
    },
  },


}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
