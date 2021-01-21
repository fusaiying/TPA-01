<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div slot="header" class="clearfix">
        <i class="el-icon-s-custom mr5"></i>
        <span>客户基础信息</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="Return">返回</el-button>
      </div>
      <el-table v-loading="loading" :data="Info" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="45" align="center"/>
        <el-table-column label="购买人" align="center" width="80px" prop="purName"/>
        <el-table-column label="购买人手机号" align="center" width="120px" prop="purMobile"/>
        <el-table-column label="购买人身份证号" align="center" width="240px" prop="idNo"/>
        <el-table-column label="性别" align="center" width="120px" prop="purSex"/>
        <el-table-column label="出生日期" align="center" prop="purBirthday"/>
        <el-table-column label="E-mail" align="center" prop="purEmail"/>
      </el-table>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div slot="header" class="clearfix">
        <i class="el-icon-s-promotion mr5"></i>
        <span>订单基本信息</span>
      </div>
      <el-table v-loading="loading" :data="Info" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="45" align="center"/>
        <el-table-column label="团单号" align="center" prop="groupOrderNo"/>
        <el-table-column label="个单号" align="center" prop="personalOrderno"/>
        <el-table-column label="保单号" align="center" width="80px" prop="contNo"/>
        <el-table-column label="保险名称" align="center" width="120px" prop="riskCode"/>
        <el-table-column label="增值服务代码" align="center" width="120px" prop="serviceName"/>
        <el-table-column label="产品包编码" align="center" width="120px" prop="productPackageNo"/>
        <el-table-column label="产品包名称" align="center" prop="productPackageName"/>
        <el-table-column label="团单录入时间" align="center" prop="makeDate"/>
        <el-table-column label="服务起期" align="center" width="120px" prop="salstartDate"/>
        <el-table-column label="服务止期" align="center" width="120px" prop="salendDate"/>
        <el-table-column label="订单状态" align="center" width="120px" prop="orderStatus"/>

      </el-table>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div slot="header" class="clearfix">
        <i class="el-icon-user-solid mr5"></i>
        <span>使用人基本信息</span>
      </div>
      <el-table v-loading="loading" :data="Info" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="45" align="center"/>
        <el-table-column label="服务卡号" align="center" prop="serviceCardNo"/>
        <el-table-column label="使用人姓名" align="center" prop="userName"/>
        <el-table-column label="使用人手机号" align="center" width="120px" prop="userMobile"/>
        <el-table-column label="使用人身份证号" align="center" width="120px" prop="userIdno"/>
        <el-table-column label="性别" align="center" prop="userSex"/>
        <el-table-column label="出生日期" align="center" prop="userBirthday"/>
        <el-table-column label="购买人" align="center" width="80px" prop="purName"/>
        <el-table-column label="购买人手机号" align="center" width="120px" prop="purMobile"/>
        <el-table-column label="购买人身份证号" align="center" width="120px" prop="idNo"/>
        <el-table-column label="保单号" align="center" width="120px" prop="contNo"/>
        <el-table-column label="结算方式" align="center" prop="payType"/>

        <el-table-column label="产品包编码" align="center" width="120px" prop="productPackageNo"/>
        <el-table-column label="产品包名称" align="center" width="120px" prop="productPackageName"/>
        <el-table-column label="产品子项编码" align="center" width="120px" prop="productNo"/>
        <el-table-column label="产品子项名称" align="center" width="120px" prop="productName"/>
        <el-table-column label="服务起期" align="center" width="120px" prop="salstartDate"/>
        <el-table-column label="服务止期" align="center" width="120px" prop="salendDate"/>
        <el-table-column label="服务总次数" align="center" prop="times"/>
        <el-table-column label="已使用次数" align="center" prop="serviceTimes"/>
        <el-table-column label="剩余次数" align="center" prop="surplusTimes"/>

      </el-table>
    </el-card>
  </div>


</template>
<script>
  import {listCustomer} from "@/api/query/customer";

  export default {
    name: "info",
    data() {
      return {
        loading: true,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          purName: '李三点',
          productNo: 'HP000482',
          contNo: 'P00001279820'
        },
        Info: []
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        listCustomer(this.queryParams).then(response => {
          this.Info = response.rows;
          this.total = response.total;
          this.loading = false;
        })
      },
      Return() {
        this.$router.push({
          path: '/query/customerInfo/index/0'
        });
      }
    }
  }

</script>
