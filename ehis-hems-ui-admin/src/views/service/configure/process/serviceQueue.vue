<template>
  <div class="app-container">
    <el-card class="box-card" shadow="hover" style="margin-bottom: 20px;">
      <div slot="header" class="clearfix">
        <i class="el-icon-notebook-2 mr5"></i>
        <span>服务流程列表</span>
      </div>
      <!-- 服务流程列表 -->
      <el-table v-loading="loading" :data="serviceList">
        <el-table-column label="选择" width="65">
          <template scope="scope">
            <el-radio :label="scope.$index" v-model="radio" @change.native="getCurrentRow(scope.row)">
              <span>&nbsp;</span></el-radio>
          </template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="序号">
          <template slot-scope="scope">{{ scope.row.index }}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="服务名称">
          <template slot-scope="scope">{{ scope.row.serviceName }}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="流程节点">
          <template slot-scope="scope">{{ scope.row.progress }}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="队列名称">
          <template slot-scope="scope">{{ scope.row.queueName }}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="申请渠道">
          <template slot-scope="scope">{{ scope.row.appChannel }}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="产品服务实施类型">
          <template slot-scope="scope">{{ scope.row.serviceType}}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="产品名称">
          <template slot-scope="scope">{{ scope.row.productName}}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="服务商类型">
          <template slot-scope="scope">{{ scope.row.servicerType}}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="服务商名称">
          <template slot-scope="scope">{{ scope.row.servicerName}}</template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          label="自建签约机构">
          <template slot-scope="scope">{{ scope.row.signCom}}</template>
        </el-table-column>

      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>
    <!-- 表单区域 -->
    <el-form ref="form" :model="form" :inline="true" :rules="rules" label-width="125px">
      <el-card class="box-card" shadow="hover" style="margin-bottom: 20px;">
        <div slot="header" class="clearfix">
          <i class="el-icon-setting mr5"></i>
          <span>服务流程配置</span>
        </div>
        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务名称">
              <el-input
                disabled
                v-model="form.serviceName"
                placeholder=""
                clearable></el-input>
            </el-form-item>
          </el-col>

          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="节点">
              <el-select v-model="form.progress"
                         clearable
                         placeholder="请选择节点">
                <el-option
                  v-for="dict in progress"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="目标类型" prop="targetType">
              <el-select v-model="form.targetType"
                         clearable
                         placeholder="请选择目标类型">
                <el-option
                  v-for="dict in targetType"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="队列目标" prop="queueTarget">
              <el-select v-model="form.queueTarget"
                         clearable
                         placeholder="请选择队列目标">
                <el-option
                  v-for="dict in queueTarget"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="申请渠道">
              <el-select v-model="form.appChannel"
                         clearable
                         placeholder="请选择申请渠道">
                <el-option
                  v-for="dict in appChannel"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="客户类型">
              <el-select v-model="form.customerType"
                         clearable
                         placeholder="请选择客户类型">
                <el-option
                  v-for="dict in customerType"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="产品名称">
              <el-select v-model="form.productName"
                         clearable
                         placeholder="请选择产品名称">
                <el-option
                  v-for="dict in productName"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="供应商服务">
              <el-select v-model="form.providerService"
                         clearable
                         placeholder="请选择供应商服务">
                <el-option
                  v-for="dict in providerService"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="优先级">
              <el-input-number v-model="num" @change="handleChange" :min="1"
                               label="描述文字"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="产品服务实施类型">
              <el-select v-model="form.productServiceType"
                         clearable
                         placeholder="请选择产品服务实施类型">
                <el-option
                  v-for="dict in productServiceType"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务商名称">
              <el-input
                v-model="form.servicerName"
                placeholder="请输入服务商名称"
                clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务商类型">
              <el-select v-model="form.providerType"
                         clearable
                         placeholder="请选择服务商类型">
                <el-option
                  v-for="dict in providerType"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务商所在省">
              <el-select v-model="form.province"
                         clearable
                         placeholder="请选择服务商所在省">
                <el-option
                  v-for="dict in province"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="服务商所在市">
              <el-select v-model="form.city"
                         clearable
                         placeholder="请选择服务商所在市">
                <el-option
                  v-for="dict in city"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="自建签约机构">
              <el-select v-model="form.signCom"
                         clearable
                         placeholder="请选择自建签约机构">
                <el-option
                  v-for="dict in signCom"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="项目名称">
              <el-select v-model="form.projectName"
                         clearable
                         placeholder="请选择项目名称">
                <el-option
                  v-for="dict in projectName"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-form-item label="当前状态" prop="currentStatus">
              <el-select v-model="form.currentStatus"
                         clearable
                         placeholder="请选择当前状态">
                <el-option
                  v-for="dict in currentStatus"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-card>

      <el-row style="text-align: right;">
        <el-col class="mt10 mb10">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="submit"
          >保存
          </el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="reset">重置</el-button>
          <el-button icon="el-icon-d-arrow-left" size="mini" @click="backFun">返回</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "serviceQueue",
    data() {
      return {
        // 查询参数 - 基础信息维护
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          serviceType: null,
          serviceName: null,
        },
        // 显示搜索条件 - 基础
        showSearch: true,
        //加载转圈
        loading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 表格数据总数
        total: 3,
        // 表格数据
        serviceList: [
          {
            index: '1',
            serviceName: '齿科服务',
            progress: '待分诊',
            queueName: '临时负责人分配',
            appChannel: '管家安排',
            serviceType: null,
            productName: '洁牙套餐爱康（人保寿）',
            servicerType: '口腔保健机构',
            servicerName: '三亚口腔医院',
            signCom: '总公司',
          },
          {
            index: '2',
            serviceName: '齿科服务',
            progress: '待预约',
            queueName: '更新队列',
            appChannel: '管家安排',
            serviceType: null,
            productName: '洁牙套餐爱康（人保寿）',
            servicerType: '口腔保健机构',
            servicerName: '三亚口腔医院',
            signCom: '总公司',
          },
          {
            index: '3',
            serviceName: '齿科服务',
            progress: '待分诊',
            queueName: '总公司',
            appChannel: '管家安排',
            serviceType: null,
            productName: '洁牙套餐爱康（人保寿）',
            servicerType: '口腔保健机构',
            servicerName: '三亚口腔医院',
            signCom: '总公司',
          },
        ],
        // 单选
        radio: "",
        form: {},
        rules: {
          targetType: [
            {required: true, message: "目标类型不能为空", trigger: "change"}
          ],
          queueTarget: [
            {required: true, message: "队列目标不能为空", trigger: "change"}
          ],
          currentStatus: [
            {required: true, message: "当前状态不能为空", trigger: "change"}
          ],


        },

        // 节点下拉框
        progress: [],
        targetType: [],
        queueTarget: [],
        appChannel: [],
        customerType: [],
        productName: [],
        providerService: [],
        productServiceType: [],
        providerType: [],
        province: [],
        city: [],
        signCom: [],
        projectName: [],
        currentStatus: [],
        // 优先级 默认为1
        num: 1,
      }
    },
    methods: {
      // 优先级 点击+-出发事件
      handleChange(value) {
        console.log(value);
      },
      // 单选出发事件
      getCurrentRow(row) { //获取选中数据this.templateSelection = row;
        this.form = row;
      },
      getList() {
      },
      submit() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.$message({
              showClose: true,
              message: '保存成功',
              type: 'success'
            });
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
          ;
        });
      },
      reset() {
        this.form = {};
        this.radio = '';
      },
      backFun() {
        this.$router.push({path: '/service/configure/process'});
      },

    },
  }
</script>

<style scoped>

</style>
