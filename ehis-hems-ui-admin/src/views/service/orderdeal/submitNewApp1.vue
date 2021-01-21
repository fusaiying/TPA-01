<template>
  <div class="app-container">
    <el-steps :active="active" finish-status="success" align-center :space="1000">
      <el-step title="选择申请客户"></el-step>
      <el-step title="提交申请请求"></el-step>
    </el-steps>

    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-zoom-in mr5"></i>
          <span>客户查询条件</span>
        </div>
        <el-form :model="customerQuery" ref="customerQuery" :inline="true" label-width="125px">
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="客户姓名" prop="name">
                <el-input
                  v-model="customerQuery.name"
                  placeholder="请输入用户姓名"
                  clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件类型" prop="busNumType">
                <el-select v-model="customerQuery.idtype" placeholder="请输入证件类型" clearable>
                  <el-option
                    v-for="dict in idTypeOpTions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件号码" prop="idcardno">
                <el-input
                  v-model="customerQuery.idcardno"
                  placeholder="请输入证件号码"
                  clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </el-row>

    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-zoom-in mr5"></i>
          <span>客户信息列表</span>
        </div>
        <el-row class="mt20">
          <el-table v-loading="customerLoading" :data="customerList" highlight-current-row @current-change="customerListCurrentChange">
            <el-table-column label="客户ID" align="center" prop="customerid"/>
            <el-table-column label="客户姓名" align="center" prop="name"/>
            <el-table-column label="手机号码" align="center" prop="mobilephone"/>
            <el-table-column label="证件类型" align="center" prop="idtype"/>
            <el-table-column label="证件号码" align="center" prop="idcardno"/>
            <el-table-column label="保单号码" align="center" prop=""/>
            <el-table-column label="保单有效期" align="center" prop=""/>
            <el-table-column label="保险名称" align="center" prop=""/>
            <el-table-column label="服务产品数量" align="center" prop=""/>
          </el-table>
          <pagination
            v-show="customerQuery.total>0"
            :total="customerQuery.total"
            :page.sync="customerQuery.pageNum"
            :page-sizes="[5, 10, 15, 20]"
            :limit.sync="customerQuery.pageSize"
            @pagination="getCustomerList"
          />
        </el-row>
      </el-card>
    </el-row>
    <el-row class="mt10 mb10" v-show="productShow">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-zoom-in mr5"></i>
          <span>服务信息列表</span>
        </div>
        <el-row class="mt20">
          <el-table v-loading="productLoading" :data="productList" highlight-current-row @current-change="productListCurrentChange">
            <el-table-column label="服务产品编码" align="center" prop="productcode"/>
            <el-table-column label="服务产品名称" align="center" prop="productname"/>
            <el-table-column label="服务产品来源" align="center" prop="servicesource"/>
            <el-table-column label="服务项目编码" align="center" prop="servicecode"/>
            <el-table-column label="服务项目名称" align="center" prop="servicename"/>
            <el-table-column label="服务有效期" align="center" prop="enddate"/>
            <el-table-column label="服务剩余次数" align="center" prop="balance"/>
            <el-table-column label="服务消费详情" align="center" prop=""/>
          </el-table>
          <pagination
            v-show="productQuery.total>0"
            :total="productQuery.total"
            :page.sync="productQuery.pageNum"
            :page-sizes="[5, 10, 15, 20]"
            :limit.sync="productQuery.pageSize"
            @pagination="getProductList"
          />
        </el-row>
      </el-card>
    </el-row>
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
          <!-- 按钮区域 -->
          <el-row style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="success"
                icon="el-icon-check"
                size="mini"
                @click="nextInput"
              >下一步
              </el-button>
              <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
            </el-col>
          </el-row>
      </el-card>
    </el-row>
  </div>
</template>

<script>
  import {queryCustomerList, queryProductList} from "@/api/service/submitNewApp";
  import ElRow from "element-ui/packages/row/src/row";

  export default {
    components: {ElRow},
    name: "submitNewApp",
    data() {
      return {
        active: 0,
        customerLoading:false,
        productLoading:false,
        productShow:false,
        idTypeOpTions: [],
        customerList:[],
        productList:[],
        customerSelectList:[],
        productSelectList:[],
        customerQuery: {
          total: 0,
          pageNum: 1,
          pageSize: 5,
          name:null,
          idtype:null,
          idcardno:null
        },
        productQuery: {
          total: 0,
          pageNum: 1,
          pageSize: 5
        },
      }
    },
    created() {
      this.getCustomerList();
      // 业务号码类型
      this.getDicts("id_type").then(response => {
        this.idTypeOpTions = response.data;
      });
    },
    methods: {
      //查询客户列表
      getCustomerList(){
        this.customerLoading = true;
        queryCustomerList(this.customerQuery).then(response => {
          this.customerList = response.rows;
          this.customerQuery.total = response.total;
          this.customerLoading = false;
        });
      },

      //查询服务产品列表
      getProductList(){
        if(this.customerSelectList.length <= 0){
          this.msgError("请先选择客户！");
          return;
        }
        this.productShow = true;
        this.productLoading = true;
        this.productQuery.customerid = this.customerSelectList[0].customerid;
        queryProductList(this.productQuery).then(response => {
          this.productList = response.rows;
          this.productQuery.total = response.total;
          this.productLoading = false;
        });
      },

      //客户列表选中行
      customerListCurrentChange(val){
        this.customerSelectList.length = 0;
        this.customerSelectList.push(val);
        this.getProductList();
      },

      //服务产品列表选中行
      productListCurrentChange(val){
        this.productSelectList.length = 0;
        this.productSelectList.push(val);
      },

      // 下一步
      nextInput() {
        if(this.customerSelectList.length <= 0 || this.productSelectList.length <= 0 ){
          this.$message({
            message: '请先选择客户和服务产品！',
            type: 'warning'
          });
          return;
        }
        this.$router.push({path: '/service/orderdeal/newApp2/'+this.customerSelectList[0].customerid+"_"+this.productSelectList[0].productcode});
      },
      // 返回上一步
      back() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.back();
      },
    }
  }
</script>

<style scoped>

</style>
