<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">
      <!-- card1 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-data mr5"></i><span>申请书信息</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="申请书编码">
                <el-input disabled placeholder="申请书编码自动生成" v-model="form.personalorderno"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="业务员姓名">
                <el-select v-model="form.salesmanno"
                           clearable
                           placeholder="请选择业务员姓名">
                  <el-option
                    v-for="dict in serviceName"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="申请日期" prop="applicationdate">
                <el-date-picker clearable
                                v-model="form.applicationdate"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择申请日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="服务起期" prop="orderstartdate">
                <el-date-picker clearable
                                v-model="form.orderstartdate"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择服务起期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="服务止期" prop="">
                <el-date-picker clearable
                                v-model="form.contractadvance"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择服务止期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="预付款或保障金">
                <el-input placeholder="请输入预付款或保障金" v-model="form.applyNo" clearable/>
              </el-form-item>
            </el-col>
          </el-row>


          <!-- 第三行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="结算周期" prop="calDate">
                <el-select v-model="form.calDate"
                           clearable
                           placeholder="请选择结算周期">
                  <el-option
                    v-for="dict in sendCard"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="结算频次">
                <el-input placeholder="结算频次" v-model="form.input2">
                  <template slot="append">次/月</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="是否寄送实体卡" prop="sendCard">
                <el-select v-model="form.sendCard"
                           clearable
                           placeholder="请选择结算周期">
                  <el-option
                    v-for="dict in sendCard"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>


          <!-- 第四行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="关联方代码" prop="relaCode">
                <el-select v-model="form.relaCode"
                           clearable
                           placeholder="请选择关联方代码">
                  <el-option
                    v-for="dict in billType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="纳税人类型" prop="taxType">
                <el-select v-model="form.taxType"
                           clearable
                           placeholder="请选择纳税人类型">
                  <el-option
                    v-for="dict in taxType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="发票类型" prop="billType">
                <el-select v-model="form.billType"
                           clearable
                           placeholder="请选择发票类型">
                  <el-option
                    v-for="dict in billType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第五行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="销售价格" prop="salePrice">
                <el-input placeholder="请输入销售价格" v-model="form.salePrice" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="收费方式" prop="feeType">
                <el-select v-model="form.feeType"
                           clearable
                           placeholder="请选择收费方式">
                  <el-option
                    v-for="dict in taxType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="结算方式" prop="settlement">
                <el-select v-model="form.settlement"
                           clearable
                           placeholder="请选择结算方式">
                  <el-option
                    v-for="dict in billType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第五行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="保底金额">
                <el-input placeholder="请输入保底金额" v-model="form.guaranteedAmount">
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="订单名称">
                <el-input placeholder="请输入订单名称" v-model="form.orderName" clearable/>
              </el-form-item>
            </el-col>

          </el-row>

        </el-card>
      </el-row>


      <!-- card2 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-document-copy mr5"></i><span>团体客户信息</span>
          </div>
          <!-- 第五行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="团体客户编码" prop="groupCustomerCode">
                <el-select v-model="form.groupCustomerCode"
                           clearable
                           placeholder="请选择团体客户编码">
                  <el-option
                    v-for="dict in groupCustomerCode"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="联系人">
                <el-input placeholder="请输入联系人" v-model="form.buyName" clearable />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="联系人电话" prop="concatPhone">
                <el-input placeholder="请输入联系人" v-model="form.concatPhone" clearable />
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第六行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="联系人职位" prop="concatJob">
                <el-input placeholder="请输入联系人职位" v-model="form.concatJob" clearable />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="所属集团" prop="group">
                <el-input placeholder="请输入所属集团" v-model="form.group" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 保存 -->

          <el-row class="mt10 mb10" style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click="submit1"
                v-hasPermi="['provider:provider:add']"
              >保存
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-row>


      <!-- card4 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-operation mr5"></i><span>产品包选择</span>
          </div>

          <!-- 表格区域 -->
          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="产品包编码" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netCode }}</template>
            </el-table-column>
            <el-table-column
              label="产品包名称" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netName }}</template>
            </el-table-column>
            <el-table-column
              label="售价（单位：元）" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMan }}</template>
            </el-table-column>

            <el-table-column
              label="使用人标记" show-overflow-tooltip >
              <template slot-scope="scope">{{ scope.row.netLinkPhone }}</template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="100>0"
            :total="total"
            :page-size="10"
          />


          <!-- 按钮区域 -->
          <el-row style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                @click="delRow"
                :disabled="single"
                v-hasPermi="['provider:provider:add']"
              >删除选中
              </el-button>
            </el-col>
          </el-row>
        </el-card>


      </el-row>

      <!-- card5 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-cooperation mr5"></i><span>产品包信息</span>
          </div>
          <!-- 第九行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="产品包名称" prop="feeType">
                <el-select v-model="form.feeType" clearable placeholder="请选择产品包名称">
                  <el-option
                    v-for="dict in feeType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="售价" prop="bankAccountName">
                <el-input clearable disabled />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click="submit1"
                v-hasPermi="['provider:provider:add']"
              >保存
              </el-button>
            </el-col>
          </el-row>
        </el-card>


      </el-row>

      <!-- card6 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-help mr5"></i><span>服务产品选择</span>
          </div>

          <!-- 表格区域 -->
          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="服务类型" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netCode }}</template>
            </el-table-column>
            <el-table-column
              label="产品包编码" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netName }}</template>
            </el-table-column>
            <el-table-column
              label="产品包名称" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMan }}</template>
            </el-table-column>

            <el-table-column
              label="产品编码" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkPhone }}</template>
            </el-table-column>

            <el-table-column
              label="产品名称" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMail }}</template>
            </el-table-column>

            <el-table-column
              label="标准价格" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="产品选择标记" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="服务生效日期" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="服务时效日期" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="100>0"
            :total="total"
            :page-size="10"
          />

          <el-row class="mt10 mb10" style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click="submit1"
                v-hasPermi="['provider:provider:add']"
              >保存
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <!-- card7 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
           <i class="el-icon-s-grid mr5"></i><span>合作伙伴/销售平台选择</span>
          </div>

          <el-row class="mb10 mt10">
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="业务类型">
                <el-select v-model="form.feeType" clearable placeholder="请选择业务类型">
                  <el-option
                    v-for="dict in feeType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="合作伙伴">
                <el-select v-model="form.feeType" clearable placeholder="请选择合作伙伴" >
                  <el-option
                    v-for="dict in feeType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="销售平台">
                <el-select v-model="form.feeType" clearable placeholder="请选择销售平台">
                  <el-option
                    v-for="dict in feeType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- 表格区域 -->
          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="营销方案编码" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="Campaign选择标记" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="100>0"
            :total="total"
            :page-size="10"
          />

          <el-row class="mt20 mb10" style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click="submit1"
                v-hasPermi="['provider:provider:add']"
              >提交
              </el-button>
              <el-button
                icon="el-icon-d-arrow-left" size="mini" @click="back"
                v-hasPermi="['provider:provider:add']"
              >返回
              </el-button>

            </el-col>
          </el-row>
        </el-card>
      </el-row>

    </el-form>
  </div>
</template>

<script>
  import {getPersonal} from "@/api/order/personal";

  export default {
    name: "AddPersonalOrder",
    data() {
      return {
        // 是否寄送实体卡 下拉框
        sendCard: [],
        // 业务员姓名  下拉框
        serviceName: [],
        // 关联方代码 下拉框
        relaCode: [],
        // 纳税类型 下拉
        taxType: [],
        // 发票类型 下拉
        billType: [],
        // 购买人性别
        buySex: [],
        // 收费方式 下拉
        feeType: [],
        // 银行账户名称 下拉
        bankAccountName: [],
        backName: [],
        // 银行区域 下拉
        bankPlaceArea: [],
        // 发票模式 下拉
        feePattern: [],
        // 代收平台 下拉
        replacePlatform: [],
        // 团体客户编码 下拉
        groupCustomerCode: [],
        form: {},
        // 表单校验
        rules: {
          applicationdate: [
            {required: true, message: '申请日期必填', trigger: 'change'},
          ],
          orderstartdate: [
            {required: true, message: '服务起期必填', trigger: 'change'},
          ],
          relatedcode: [
            {required: true, message: '关联方代码必填', trigger: 'change'},
          ],
          taxpayertype: [
            {required: true, message: '纳税人类型必填', trigger: 'change'},
          ],
          billType: [
            {required: true, message: '发票类型必填', trigger: 'change'},
          ],
          buyName: [
            {required: true, message: '购买人姓名必填', trigger: 'blur'},
          ],
          buySex: [
            {required: true, message: '购买人性别必填', trigger: 'change'},
          ],
          buyPhone: [
            {required: true, message: '手机号必填', trigger: 'blur'},
          ],
          feeType: [
            {required: true, message: '收费方式必填', trigger: 'change'},
          ],
          replacePlatform: [
            {required: true, message: '代收平台不能为空', trigger: 'change'},
          ],
          calDate: [
            {required: true, message: '结算周期不能为空', trigger: 'change'},
          ],
          relaCode: [
            {required: true, message: '关联方代码不能为空', trigger: 'change'},
          ],
          taxType: [
            {required: true, message: '纳税人类型不能为空', trigger: 'change'},
          ],
          salePrice: [
            {required: true, message: '销售价格不能为空', trigger: 'blur'},
          ],
          settlement: [
            {required: true, message: '结算方式不能为空', trigger: 'change'},
          ],
          concatPhone: [
            {required: true, message: '联系电话不能为空', trigger: 'blur'},
          ],
          concatJob: [
            {required: true, message: '联系人职位不能为空', trigger: 'blur'},
          ],
          group: [
            {required: true, message: '所属集团不能为空', trigger: 'blur'},
          ],


        },
        // 总条数
        total: 0,
        // 模拟表格数据
        tableData: [],
        // 选中复选框数组
        ids: [],
        // 遮罩层
        loading: false,
        // 非单个禁用
        single: true,
      }
    },
    created() {
      // 获取申请书编码
      const personalorderno = this.$route.params && this.$route.params.personalorderno;
      // 只有在申请书编码存在的情况下（也就是编辑的时候），才会根据申请书编码
      if (personalorderno != undefined) {
        this.getPersonalByNo(personalorderno);
      }
      // 查询字典 性别
      this.getDicts("sys_user_sex").then(response => {
        this.buySex = response.data;
      });

    },
    methods: {
      /** 根据ID获取Provider */
      getPersonalByNo(personalorderno) {
        getPersonal(personalorderno).then(response => {
          // 往修改页面带数据
          this.form = response.data;
        });
      },
      submitForm() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.msgSuccess("修改成功");
          }
        });
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.netCode)
        this.single = !selection.length
      },

      /** 删除按钮操作 */
      delRow(row) {
        const servcomnos = row.netCode || this.ids;
        this.$confirm('是否确认删除供应商编号为"' + servcomnos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          // return delProvider(servcomnos);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

      /** 返回 */
      back() {
        this.$router.push({path: '/order/group/enter'});
      },
      submit1() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.msgSuccess("保存成功");
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
        });
      },
    },
  }
</script>

<style scoped>

</style>
