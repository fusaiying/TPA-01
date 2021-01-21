<template>
  <div class="app-container">
    <!-- 基本信息 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-chat-square mr5"></i>
          <span>使用人信息</span>
        </div>
        <el-form ref="userform" :model="userform" :inline="true" label-width="125px">
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="姓名">
                <el-input
                  v-model="userform.user"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="性别">
                <el-input
                  v-model="userform.usersex"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="出生日期">
<!--                <el-input-->
<!--                  v-model="userform.userbirthday"-->
<!--                  placeholder=""-->
<!--                  clearable-->
<!--                  disabled>-->
<!--                </el-input>-->
                <el-date-picker
                  v-model="userform.userbirthday"
                  type="datetime"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  disabled
                  placeholder="">
                </el-date-picker>
              </el-form-item>
            </el-col>

          </el-row>
          <el-row >
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件类型">
                <el-input
                  v-model="userform.useridtype"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="证件号码">
                <el-input
                  v-model="userform.useridcode"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系电话">
                <el-input
                  v-model="userform.usermobile"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </el-row>

    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-chat-square mr5"></i>
          <span>服务信息</span>
        </div>
        <el-form ref="form" :model="form" :inline="true" label-width="125px">
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="产品名称">
                <el-input
                  v-model="form.productcode"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="服务名称">
                <el-input
                  v-model="form.servicecode"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="服务商名称">
                <el-input
                  v-model="form.supplier"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="申请日期">
                <el-date-picker
                  v-model="form.appointmenttime"
                  type="datetime"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  disabled
                  placeholder="">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="申请方式">
                <el-input
                  v-model="form.ordersource"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="当前状态">
                <el-input
                  v-model="form.orderstatus"
                  placeholder=""
                  clearable
                  disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>

    </el-row>


    <!-- 查询条件 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-search mr5"></i>
          <span>查询条件</span>
        </div>

        <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="125px">
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="系统账号">
                <el-input
                  v-model="queryParams.userName"
                  placeholder="请输入系统账号"
                  clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="姓名">
                <el-input
                  v-model="queryParams.nickName"
                  placeholder="请输入姓名"
                  clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="管理机构">
                <el-select v-model="queryParams.deptId"
                           clearable
                           placeholder="请选择管理机构">
                  <el-option
                    v-for="dict in manageCom"
                    :key="dict.deptId"
                    :label="dict.deptId + ' - ' +dict.deptName"
                    :value="dict.deptId"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-col>
          </el-row>
      </el-card>
    </el-row>

    <!-- 查询结果 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-chat-dot-square mr5"></i>
          <span>查询结果</span>
        </div>
        <!-- 供应商列表 表格 -->
        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">

          <el-table-column type="selection" width="50" align="center"></el-table-column>
          <el-table-column label="系统账号" align="center" prop="userName"/>
          <el-table-column label="姓名" align="center" prop="nickName"/>
          <el-table-column label="所属机构" align="center" prop="deptId"/>
          <el-table-column label="联系电话" align="center" prop="phonenumber"/>
          <el-table-column label="状态" align="center" prop="status"/>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :limit.sync="queryParams.pageSize"
          @pagination="getUserList"
        />

        <el-row style="text-align: right;" class="mt20">
          <el-button
            type="primary"
            icon="el-icon-edit-outline"
            size="mini"
            @click="tasksend"
            :disabled="single"
            v-hasPermi="['service:queryDeal:ownertasksend']"
          >任务指派
          </el-button>
          <el-button icon="el-icon-d-arrow-left" size="mini" @click="close">返回</el-button>
        </el-row>

      </el-card>
    </el-row>

  </div>
</template>

<script>
  import {listUser, getManageCom, queryPersonService, queryServiceOrder, tasksend} from "@/api/service/queryDeal";

  export default {
    name: "taskAssign",
    data() {
      return {
        total: 0,
        loading: false,
        ordercode:null,
        single:true,
        //使用人信息
        userform:{
          user: null,
          usersex: null,
          userbirthday: null,
          useridtype: null,
          useridcode: null,
          usermobile: null
        },
        //服务信息
        form: {
          productcode:null,
          servicecode:null,
          supplier:null,
          appointmenttime:null,
          ordersource:null,
          orderstatus:null
        },
        // 管理机构
        manageCom: [
        ],
        // 查询参数 - 基础信息维护
        queryParams: {
          pageNum: 1,
          pageSize: 5,
          userName:null,
          nickName:null,
          deptId:null
        },
        userList: [],
      }
    },
    created() {
      const ordercode = this.$route.params && this.$route.params.ordercode;
      this.ordercode = ordercode;
      this.getPersonService(ordercode);
      this.getUserList();
      getManageCom().then(response => {
          this.manageCom = response.data;
          console.log(this.manageCom);
        }
      );
    },
    methods: {
      getPersonService(ordercode){
        queryPersonService(ordercode).then(response => {
          if(response.code === 200){
            this.userform = response.data;
          }else{
            this.msgError(response.msg);
          }
        });
        queryServiceOrder(ordercode).then(response => {
          if(response.code === 200){
            this.form = response.data;
          }else{
            this.msgError(response.msg);
          }
        });
      },
      getUserList(){
        this.loading = true;
        listUser(this.queryParams).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getUserList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.queryParams = {
          pageNum: 1,
          pageSize: 5,
          userName:null,
          nickName:null,
          deptId:null
        };
        this.handleQuery();
      },
      handleSelectionChange(selection) {
        if(selection.length === 1){
          this.ids = selection[0].userId;
        }
        //this.ids = selection.map(item => item.userName);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 返回按钮  */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.back();
      },
      /**  任务指派按钮   */
      tasksend(){
        this.queryParams.userId = this.ids;
        this.form.ordercode = this.ordercode;
        tasksend(this.form,this.queryParams).then(response => {
          if(response.code === 200){
            this.msgSuccess("任务指派成功！");
          }else{
            this.msgError(response.msg);
          }
        });
      },
    }
  }
</script>

<style scoped>

</style>
