<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="125px">
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="服务项目">
            <el-input
              v-model="queryParams.servicecode"
              placeholder="请输入服务项目名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="产品名称">
            <el-input
              v-model="queryParams.productcode"
              placeholder="请输入产品名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
<!--        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--          <el-form-item label="当前状态">-->
<!--            <el-select v-model="queryParams.status" placeholder="请选择当前状态" clearable>-->
<!--              <el-option label="启动" value="启动"/>-->
<!--              <el-option label="关闭" value="关闭"/>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="申请方式">
            <el-select v-model="queryParams.ordersource" placeholder="请选择申请方式" clearable>
              <el-option
                v-for="dict in serviceOrderSourceOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="就诊人姓名">
            <el-input
              v-model="queryParams.serviceOrderApplication.user"
              placeholder="请输入就诊人姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="就诊人性别">
            <el-select v-model="queryParams.serviceOrderApplication.usersex" placeholder="请选择就诊人性别" clearable>
              <el-option
                v-for="dict in sexOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="就诊人手机号">
            <el-input
              v-model="queryParams.serviceOrderApplication.usermobile"
              placeholder="请输入就诊人手机号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="就诊人证件类型">
            <el-select v-model="queryParams.serviceOrderApplication.useridtype" placeholder="请选择就诊人证件类型" clearable>
              <el-option
                v-for="dict in idTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue+' - '+dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="就诊人证件号码">
            <el-input
              v-model="queryParams.serviceOrderApplication.useridcode"
              placeholder="请输入就诊人证件号码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-form-item label="服务申请日期">
            <el-date-picker
              v-model="dateRange"
              value-format="yyyy-MM-dd"
              type="daterange"
              style="width: 250px"
              clearable
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row>
        <el-col>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>


    <el-row class="mb10">
      <!-- 查询显示 -->
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-zoom-in mr5"></i>
          <span>查询显示</span>
        </div>

        <el-row :gutter="10" class="mb10">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['service:queryDeal:add']"
            >提交新的申请
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>

        </el-row>

        <el-table v-loading="loading" :data="personServList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="服务名称" align="center" prop="servicecode"/>
          <el-table-column label="服务商名称" align="center" prop="supplier"/>
          <el-table-column label="使用人姓名" align="center" prop="serviceOrderApplication.user"/>
          <el-table-column label="使用人性别" align="center" prop="serviceOrderApplication.usersex"/>
          <el-table-column label="使用人证件类型" align="center" prop="serviceOrderApplication.useridtype"/>
          <el-table-column label="使用人证件号码" align="center" prop="serviceOrderApplication.useridcode"/>
          <el-table-column label="使用人手机号码" align="center" prop="serviceOrderApplication.usermobile"/>
          <el-table-column label="申请日期" align="center" prop="appointmenttime" >
            <template slot-scope="scope">
              <span>{{ parseDateFull(scope.row.appointmenttime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="所属机构" align="center" prop="supplieroutlets"/>
        </el-table>
        <pagination
          v-show="queryTotal>0"
          :total="queryTotal"
          :page.sync="queryParams.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
        <el-row style="text-align: right;" class="mt20">
          <el-col>
              <el-button
                type="danger"
                icon="el-icon-zoom-in"
                size="mini"
                @click="receiveTask"
                :disabled="single"
                v-hasPermi="['service:queryDeal:add']"
              >获取任务
              </el-button>
            <el-button
              type="primary"
              icon="el-icon-edit-outline"
              size="mini"
              @click="taskSend"
              :disabled="single"
              v-hasPermi="['service:queryDeal:tasksend']"
            >任务指派
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>


    <el-row class="mb10">
      <!-- 我的任务 -->
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix mb10">
          <i class="el-icon-document mr5"></i>
          <span>我的任务</span>
        </div>
        <el-table v-loading="ownerloading" :data="ownerPersonServList" @selection-change="handleSelectionChangeOwner">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="产品名称" align="center" prop="productcode" show-overflow-tooltip/>
          <el-table-column label="服务名称" align="center" prop="servicecode" show-overflow-tooltip/>
          <el-table-column label="服务商名称" align="center" prop="supplier" show-overflow-tooltip/>
          <el-table-column label="工单状态" align="center" prop="orderstatus" show-overflow-tooltip/>
          <el-table-column label="使用人姓名" align="center" prop="serviceOrderApplication.user" show-overflow-tooltip/>
          <el-table-column label="使用人性别" align="center" prop="serviceOrderApplication.usersex" show-overflow-tooltip/>
          <el-table-column label="证件类型" align="center" prop="serviceOrderApplication.useridtype" show-overflow-tooltip/>
          <el-table-column label="证件号码" align="center" prop="serviceOrderApplication.useridcode" show-overflow-tooltip/>
          <el-table-column label="手机号码" align="center" prop="serviceOrderApplication.usermobile" show-overflow-tooltip/>
          <el-table-column label="申请日期" align="center" prop="appointmenttime" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ parseDateFull(scope.row.appointmenttime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-set-up" @click="serviceDeal(scope.row)">服务处理</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="ownerTotal>0"
          :total="ownerTotal"
          :page.sync="ownerParams.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :limit.sync="ownerParams.pageSize"
          @pagination="getOwnerList"
        />
        <el-row style="text-align: right;" class="mt20">
          <el-col>
            <el-button
              type="warning"
              icon="el-icon-zoom-in"
              size="mini"
              @click="serviceDeal"
              :disabled="ownerSingle"
              v-hasPermi="['provider:provider:add']"
            >服务处理
            </el-button>
            <el-button
              type="danger"
              icon="el-icon-edit-outline"
              size="mini"
              @click="release"
              :disabled="ownerSingle"
              v-hasPermi="['provider:provider:add']"
            >任务释放
            </el-button>
            <el-button
              type="primary"
              icon="el-icon-edit-outline"
              size="mini"
              @click="ownertasksend"
              :disabled="ownerSingle"
              v-hasPermi="['service:queryDeal:ownertasksend']"
            >任务指派
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>

  </div>
</template>

<script>
  import {listPersonService, listOwnerPersonService, taskGetOwner,taskRelease} from "@/api/service/queryDeal";

  export default {
    name: "Examine",
    data() {
      return {
        // 遮罩层
        loading: false,
        ownerloading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非单个禁用
        ownerSingle: true,
        // 日期范围
        dateRange: [],
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        //就诊人性别
        sexOptions: [],
        //申请方式
        serviceOrderSourceOptions: [],
        //就诊人证件类型
        idTypeOptions: [],
        //服务工单号
        ordercode: null,
        // 总条数
        queryTotal: 0,
        // 我的列表总条数
        ownerTotal: 0,
        // 服务就诊人列表
        personServList: [],
        // 我的服务就诊人列表
        ownerPersonServList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 5,
          servicecode: null,
          productcode: null,
          ordersource: null,
          serviceOrderApplication: {
            useridtype: null,
            useridcode: null,
            usermobile: null,
            user: null,
            usersex: null
          },
        },
        // 查询参数
        ownerParams: {
          pageNum: 1,
          pageSize: 5
        },
        tableData: [
          {
            index: 1,
            productName: '健康相知卡',
            serviceName: '健康管理中心-体机检测服务',
            servicerName: '中国人民保险健康管理中心天津示范店',
            contNo: '',
            serviceCardNo: 'JKXZ10001',
            patientName: '木块',
            patientSex: '男',
            idType: '身份证',
            idNo: '1110100100100',
            phoneNum: '15832312',
            appDate: '2020-10-13',
          },
          {
            index: 2,
            productName: '人民健康一卡通钻石卡',
            serviceName: '门诊预约(含陪诊)服务',
            servicerName: '首都医科大学附属北京安贞医院',
            contNo: '',
            serviceCardNo: 'JKXZ10002',
            patientName: '一百八',
            patientSex: '男',
            idType: '身份证',
            idNo: '11101001001012120',
            phoneNum: '155589898',
            appDate: '2020-10-12',
          },
          {
            index: 3,
            productName: '上海分公司瑞金MDT多学科会诊',
            serviceName: '多学科会诊服务',
            servicerName: '上海交通大学附属瑞金医院',
            contNo: '',
            serviceCardNo: 'JKXZ10003',
            patientName: '存的',
            patientSex: '男',
            idType: '护照',
            idNo: '888',
            phoneNum: '155565469898',
            appDate: '2020-10-16',
          },
          {
            index: 4,
            productName: '2020年财总员工健管计划A',
            serviceName: '门诊预约(含陪诊)服务',
            servicerName: '首都医科大学附属北京同仁医院',
            contNo: '',
            serviceCardNo: 'JKXZ10004',
            patientName: '肖玉英',
            patientSex: '女',
            idType: '身份证',
            idNo: '1001231231',
            phoneNum: '154324235',
            appDate: '2020-10-11',
          },
          {
            index: 5,
            productName: '贴心管家产品至卡',
            serviceName: '门诊预约(含陪诊)服务',
            servicerName: '首都医科大学附属北京安贞医院',
            contNo: '',
            serviceCardNo: 'JKXZ10005',
            patientName: '谭婉馨',
            patientSex: '女',
            idType: '身份证',
            idNo: '1001231231232131',
            phoneNum: '1543242335',
            appDate: '2020-10-12',
          },
        ],
        // 表单校验
        rules: {
        },
      };
    },
    created() {
      this.getList();
      this.getOwnerList();
      this.getDicts("service_ordersource").then(response => {
        this.serviceOrderSourceOptions = response.data;
      });
      this.getDicts("id_type").then(response => {
        this.idTypeOptions = response.data;
      });
      this.getDicts("sys_user_sex").then(response => {
        this.sexOptions = response.data;
      });
    },
    methods: {
      /** 查询服务就诊人列表 */
      getList() {
        this.loading = true;
        listPersonService(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.personServList = response.rows;
          this.queryTotal = response.total;
          this.loading = false;
        });
      },
      /** 查询我的服务就诊人列表 */
      getOwnerList() {
        this.ownerloading = true;
        listOwnerPersonService(this.queryParams).then(response => {
          this.ownerPersonServList = response.rows;
          this.ownerTotal = response.total;
          this.ownerloading = false;
        });
      },
      //预约日期截取
      parseDateFull(time) {
        var x = new Date(time);
        var z = {
          y: x.getFullYear(),
          M: x.getMonth() + 1,
          d: x.getDate(),
          h: x.getHours(),
          m: x.getMinutes(),
          s: x.getSeconds(),
        };
        if (z.M < 10) {
          z.M = "0" + z.M;
        }
        if (z.d < 10) {
          z.d = "0" + z.d;
        }
        if (z.h < 10) {
          z.h = "0" + z.h;
        }
        if (z.m < 10) {
          z.m = "0" + z.m;
        }
        return z.y + "-" + z.M + "-" + z.d ;
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.dateRange = [];
        this.queryParams = {
          pageNum: 1,
          pageSize: 5,
          servicecode: null,
          productcode: null,
          ordersource: null,
          serviceOrderApplication: {
            useridtype: null,
            useridcode: null,
            usermobile: null,
            user: null,
            usersex: null
          },
        };
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.ordercode);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      // 多选框选中数据
      handleSelectionChangeOwner(selection) {
        this.ids = selection.map(item => item.ordercode);
        this.ownerSingle = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 提交新的申请 */
      handleAdd() {
        this.$router.push({path: '/service/serviceImp/submit/newApp1'});
      },
      // 服务处理按钮
      serviceDeal(row) {
        const ordercode = this.ids[0] || row.ordercode;
        this.$router.push('/service/serviceImp/service/deal/' + ordercode);
      },
      //任务释放按钮
      release() {
        const ordercode = this.ids[0];
        taskRelease(ordercode).then(response => {
            if (response.code === 200) {
              this.getList();
              this.getOwnerList();
              this.msgSuccess("任务已释放！");
            }else{
              this.msgError(response.msg);
            }
          }
        );
        // this.$message({
        //   message: '任务已释放',
        //   type: 'success'
        // });
      },
      //获取任务按钮
      receiveTask() {
        const ordercode = this.ids[0];
        taskGetOwner(ordercode).then(response => {
          if (response.code === 200) {
            this.getList();
            this.getOwnerList();
            this.msgSuccess("任务已获取！");
          }else{
            this.msgError(response.msg);
          }
        });
        // this.$message({
        //   message: '任务已获取',
        //   type: 'success'
        // });
      },
      //任务指派
      taskSend(row) {
        const ordercode = this.ids[0];
        this.$router.push({path: '/service/serviceImp/task/assign/'+ordercode});
      },
      ownertasksend(row) {
        const ordercode = this.ids[0];
        this.$router.push({path: '/service/serviceImp/task/assign/'+ordercode});
      },

    }
  };
</script>
