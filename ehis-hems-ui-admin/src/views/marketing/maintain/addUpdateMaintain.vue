<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">
      <!-- card1 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-data mr5"></i><span>基本信息</span>
          </div>

          <el-row class="mt10 mb10">
            请先选择需要维护的内容：
            <el-checkbox v-model="checked1">合作伙伴</el-checkbox>
            <el-checkbox v-model="checked2">销售平台</el-checkbox>
          </el-row>
          <!-- 第一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="编码">
                <el-input
                  disabled
                  placeholder="编码"
                  v-model="form.programno"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="中文名" prop="chname">
                <el-input
                  placeholder="请输入中文名"
                  v-model="form.chname"
                  />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="英文名">
                <el-input
                  placeholder="请输入英文名"
                  v-model="form.enname"
                  />
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="渠道属性" prop="channelno">
                <el-select v-model="form.channelno"
                           clearable
                           placeholder="请选择渠道属性">
                  <el-option
                    v-for="dict in channelno"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="公司类型">
                <el-select v-model="form.comType"
                           clearable
                           placeholder="请选择公司类型">
                  <el-option
                    v-for="dict in comType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="网址">
                <el-input
                  placeholder="请输入网址"
                  v-model="form.netAddress"
                  />
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第三行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="所属集团" prop="groupInfo">
                <el-input
                  placeholder="请输入所属集团"
                  v-model="form.groupInfo"
                  />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="总部所在地">
                <el-input
                  placeholder="请输入总部所在地"
                  v-model="form.location"
                />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="行业类别">
                <el-select v-model="form.industryType"
                           clearable
                           placeholder="请选择行业类别"
                >
                  <el-option
                    v-for="dict in industryType"
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
              <el-form-item label="结算方式" prop="payMoneyType">
                <el-select v-model="form.payMoneyType"
                           clearable
                           placeholder="请选择结算方式">
                  <el-option
                    v-for="dict in payMoneyType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="业务类型" prop="businessType">
                <el-select v-model="form.businessType"
                           clearable
                           placeholder="请选择行业类别">
                  <el-option
                    v-for="dict in businessType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <!-- card2 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-message mr5"></i><span>联系信息</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="州">
                <el-select v-model="form.states"
                           clearable
                           placeholder="请选择州">
                  <el-option
                    v-for="dict in states"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="国家">
                <el-select v-model="form.country"
                           clearable
                           placeholder="请选择国家">
                  <el-option
                    v-for="dict in country"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="地区">

<!--                <el-select v-model="form.province"-->
<!--                           size="mini"-->
<!--                           clearable-->
<!--                           style="width: 260px;"-->
<!--                           placeholder="省">-->
<!--                  <el-option-->
<!--                    v-for="dict in province"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' + dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--                <el-select v-model="form.city"-->
<!--                           size="mini"-->
<!--                           clearable-->
<!--                           style="width: 260px;"-->
<!--                           placeholder="市">-->
<!--                  <el-option-->
<!--                    v-for="dict in city"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' + dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--                <el-select v-model="form.district"-->
<!--                           size="mini"-->
<!--                           clearable-->
<!--                           style="width: 260px;"-->
<!--                           placeholder="区县">-->
<!--                  <el-option-->
<!--                    v-for="dict in district"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' + dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
                <!-- 地区选择 -->
                <el-form-item  prop="sendcontract" >
                  <el-cascader :options="areaSelectData"
                               @change="handleChange"
                               class="full-width"
                               v-model="selectedOptions"
                               placeholder="请选择您所在的城市" />
                </el-form-item>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="中文地址">
                <el-input placeholder="请输入中文地址"
                          v-model="form.cnAddress"
                />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="英文地址">
                <el-input
                  placeholder="请输入英文地址"
                  v-model="form.enName"
                  clearable
                />
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="邮编">
                <el-input
                  placeholder="请输入邮编"
                  v-model="form.zipCode"
                  clearable
                  maxlength="6"
                  show-word-limit
                />
              </el-form-item>
            </el-col>
          </el-row>

        </el-card>
      </el-row>

      <!-- card3 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-marketing mr5"></i><span>财务信息</span>
          </div>
          <el-table :data="concatInfo" highlight-current-row>
            <el-table-column type="index" width="50" align="center">
            </el-table-column>
            <el-table-column label="银行账户" align="center">
              <template slot-scope="scope">
                <el-input v-model="scope.row.account"
                          v-show="scope.row.show"
                          clearable
                />
                <span v-show="!scope.row.show">{{scope.row.account}}</span>
              </template>
            </el-table-column>
            <el-table-column label="银行名称" align="center">
              <template slot-scope="scope">
                <el-input v-model="scope.row.bankName"
                          v-show="scope.row.show"
                          clearable
                ></el-input>
                <span v-show="!scope.row.show">{{scope.row.concatPhone1}}</span>
              </template>
            </el-table-column>
            <el-table-column label="银行区域" align="center">
              <template slot-scope="scope">
                <el-input v-model="scope.row.bankArea"
                          v-show="scope.row.show"
                          clearable
                ></el-input>
                <span v-show="!scope.row.show">{{scope.row.bankArea}}</span>
              </template>
            </el-table-column>
            <el-table-column label="银行账号" align="center">
              <template slot-scope="scope">
                <el-input v-model="scope.row.bankAccount"
                          v-show="scope.row.show"
                          clearable
                ></el-input>
                <span v-show="!scope.row.show">{{scope.row.bankAccount}}</span>
              </template>
            </el-table-column>
            <el-table-column label="银行地址" align="center">
              <template slot-scope="scope">
                <el-input v-model="scope.row.bankAddress"
                          v-show="scope.row.show"
                          clearable
                ></el-input>
                <span v-show="!scope.row.show">{{scope.row.bankAddress}}</span>
              </template>
            </el-table-column>
            <el-table-column label="发票模式" align="center">
              <template slot-scope="scope">
                <el-input v-model="scope.row.faxModel"
                          v-show="scope.row.show"
                          clearable
                ></el-input>
                <span v-show="!scope.row.show">{{scope.row.faxModel}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"
                             fixed="right">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-s-claim"
                  v-show="scope.row.show"
                  @click="scope.row.show=false"
                  v-hasPermi="['system:information:remove']"
                >保存
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  v-show="!scope.row.show"
                  @click="scope.row.show=true"
                  v-hasPermi="['system:information:remove']"
                >编辑
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="deleteServiceOrgListOneRow(scope.row)"
                  v-hasPermi="['system:information:remove']"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-row :gutter="10" class="mb8">
            <el-col :span="24">
              <el-button
                style="width: 100%; margin-top: 3px;"
                icon="el-icon-plus"
                size="mini"
                @click="addServiceOrgListOneRow"
                v-hasPermi="['system:definition:add']"
              >新增
              </el-button>
            </el-col>
          </el-row>
        </el-card>

      </el-row>


    </el-form>

    <!-- 按钮区域 -->
    <el-row style="text-align: right;">
      <el-col class="mt10 mb10">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="submitForm"
          v-hasPermi="['provider:provider:add']"
        >保存
        </el-button>
        <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>

  import {
    listMarketplan,
    getMarketplan,
    delMarketplan,
    addMarketplan,
    updateMarketplan,
    openView
  } from "@/api/marketing/marketplan";
  import {CodeToText, regionData, TextToCode} from "element-china-area-data";
  import city from "../../service/serviceImp/management/area.json";

  export default {
    name: "AddUpdateMaintain",
    data() {
      return {
        areaSelectData: regionData, // options绑定的数据就是引入的 provinceAndCityData
        selectedOptions: [], // 地区选择参数，['省区域码', '市区域码']
        // 整个省市县数据
        city: city,
        // 被选中的市数据
        cityList: [],
        // 被选中的县数据
        areaList: [],
        selectProvince: {},
        selectCity: {},
        selectArea: {},

        // 复选框
        checked1: true,
        checked2: false,
        // 公司类型
        comType: [],
        // 渠道属性
        channelno: [],
        // 行业类别
        industryType: [],
        // 结算方式
        payMoneyType: [],
        // 业务类型
        businessType: [],
        states: [],
        country: [],
        province: [],
        city: [],
        district: [],

        // 财务信息
        concatInfo: [],
        form: {
          states: 'Asia - 亚洲',
          country: 'CHN - 中国大陆',
        },
        // 表单校验
        rules: {
          chname: [
            {required: true, message: '中文名称必填', trigger: 'blur'},
          ],
          channelno: [
            {required: true, message: '渠道属性必填', trigger: 'change'},
          ],
          groupInfo: [
            {required: true, message: '所属集团必填', trigger: 'blur'},
          ],
          payMoneyType: [
            {required: true, message: '结算方式必填', trigger: 'blur'},
          ],
          businessType: [
            {required: true, message: '业务类型必填', trigger: 'blur'},
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
      const programno = this.$route.params && this.$route.params.programno;
      if (programno != undefined) {
        this.getMarketingByNo(programno);
      }
    },
    methods: {
      // 编辑格式化地址
      handleChange() {
        var self = this;
        var provinceCode = self.selectedOptions[0];
        var cityCode = self.selectedOptions[1];
        // CodeToText属性是区域码，属性值是汉字 CodeToText['110000']输出北京市
        self.infoForm.province = CodeToText[provinceCode];
        self.infoForm.city = CodeToText[cityCode];
        console.log("选择的省市：", self.infoForm.province, self.infoForm.city);
      },
      // 获取到省市数据，进行格式化
      formatCity(){
        var self = this;
        console.log(self.infoForm.province,self.infoForm.city);
        // TextToCode属性是汉字，属性值是区域码 TextToCode['北京市'].code输出110000
        // 省份
        var provinceCode = TextToCode[self.infoForm.province].code;
        // 城市
        var cityCode = TextToCode[self.infoForm.province][self.infoForm.city].code;
        self.selectedOptions = [provinceCode, cityCode];
      },
      /** 根据ID */
      getMarketingByNo(programno) {
        console.log(programno);
        getMarketplan(programno).then(response => {
          // 往修改页面带数据
          this.form = response.data;
        });
      },
      submitForm() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.msgSuccess("修改成功");
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
          ;
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
        this.$router.push({path: '/marketing/maintain'});
      },


      //ServiceOrgList添加一行
      addServiceOrgListOneRow() {
        const row = {
          account: null,
          bankName: null,
          bankArea: null,
          bankAccount: null,
          bankAddress: null,
          faxModel: null,
          show: true
        };
        this.concatInfo.push(row);
      },


      /** ServiceOrgList删除一行 */
      deleteServiceOrgListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.concatInfo.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

    },
  }
</script>

<style scoped>

</style>
