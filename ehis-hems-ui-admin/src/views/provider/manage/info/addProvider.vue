<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="125px">
      <!-- the first card start -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-custom mr5"></i>
            <span>供应商基础信息</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商编码">
                <el-input disabled placeholder="供应商编码自动生成" v-model="form.servcomno" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商类型" prop="servcomtype">
                <el-select v-model="form.servcomtype"
                           filterable
                           clearable
                           style="width: 100%;"
                           placeholder="请选择供应商类型">
                  <el-option
                    v-for="dict in medicaltypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商中文名称" prop="chname">
                <el-input v-model.trim="form.chname" placeholder="请输入供应商中文名称" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商英文名称" prop="enname">
                <el-input v-model.trim="form.enname" placeholder="请输入供应商英文名称" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商属性">
                <el-select v-model="form.servcomattribute"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择供应商属性">
                  <el-option
                    v-for="dict in comAttribute"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="医疗类型">
                <el-select v-model="form.medicaltype"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择医疗类型">
                  <el-option
                    v-for="dict in medicalType"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第三行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="医院部门">
                <el-select v-model="form.department"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择医院部门">
                  <el-option
                    v-for="dict in hospitalDepartment"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="供应商等级">
                <el-select v-model="form.medicallevel"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择供应商等级">
                  <el-option
                    v-for="dict in medicallevel"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="医疗资质">
                <el-select v-model="form.qualifiedmdc"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择医疗资质">
                  <el-option
                    v-for="dict in medicalQualification"
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
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="州">
                <el-select v-model="form.continent"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择州" @change="selectContinentOption">
                  <el-option
                    v-for="dict in continent"
                    :key="dict.placecode"
                    :label="dict.placecode + ' - ' + dict.placename"
                    :value="dict.placecode"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="国家">
                <el-select v-model="form.country"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择国家"
                           @change="selectCoutryOption">
                  <el-option
                    v-for="dict in coutryOptions"
                    :key="dict.placecode"
                    :label="dict.placecode + ' - ' + dict.placename"
                    :value="dict.placecode"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="所在地区">
                <el-cascader clearable
                             :options="areaSelectData"
                             @change="handleChange"
                             filterable
                             style="width: 100%;"
                             v-model="form.selectedOptions" placeholder="请选择您所在的城市"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第五行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="网址" prop="website">
                <el-input v-model.trim="form.website" clearable placeholder="请输入网址"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="邮编" prop="postcode">
                <el-input v-model.trim="form.postcode" placeholder="请输入邮编" clearable
                          maxlength="6"
                          show-word-limit/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="经度">
                <el-input v-model.trim="form.longitude" clearable placeholder="请输入经度"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="纬度">
                <el-input v-model.trim="form.latitude" clearable placeholder="请输入纬度"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第六行 -->
          <el-row>
            <el-col>
              <el-form-item label="详细地址" prop="detailedaddress">
                <el-input type="textarea" v-model.trim="form.detailedaddress" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第七行 -->
          <el-row>
            <el-col>
              <el-form-item label="简介">
                <el-input type="textarea" v-model.trim="form.instructions" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第八行 -->
          <el-row>
            <el-col>
              <el-form-item label="交通路线">
                <el-input type="textarea" v-model.trim="form.trafficroute" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第九行 -->
          <el-row>
            <el-col>
              <el-form-item label="备注">
                <el-input type="textarea" v-model.trim="form.remark" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
      <!-- end the first card -->
    </el-form>

    <!-- the second card start -->
    <el-form ref="characterForm" :model="characterForm" :rules="characterRules" :inline="true" label-width="125px">
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-thumb mr5"></i>
            <span>医疗特色信息</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="床位数" prop="bednum">
                <el-input v-model.trim.number="characterForm.bednum" placeholder="请输入床位数" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="日均门诊量" prop="outpatient">
                <el-input v-model.trim.number="characterForm.outpatient" placeholder="请输入日均门诊量" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="年出院人数" prop="average">
                <el-input v-model.trim.number="characterForm.average" placeholder="请输入年出院人数" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="科室">
                <el-input v-model.trim="characterForm.hospitaldeps" placeholder="请输入科室" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="特色诊疗项目">
                <el-input v-model.trim="characterForm.feature" placeholder="请输入特色诊疗项目" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="工作时间">
                <el-input v-model.trim="characterForm.workingtime" placeholder="请输入工作时间" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
    <!-- end the second card -->

    <!-- the third card start -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-service mr5"></i>
          <span>联系人信息</span>
          <span style="margin-left: 20px;font-size: 12px" id="master">(请录入至少一条联系人信息(姓名、电话或手机1必录)</span>
        </div>
        <el-table :data="concatInfo" highlight-current-row>
          <el-table-column type="index" width="50" align="center">
          </el-table-column>
          <el-table-column label="姓名" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"/>
              <span v-show="!scope.row.show">{{scope.row.name}}</span>
            </template>
          </el-table-column>
          <el-table-column label="手机或电话1" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.phone"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.phone}}</span>
            </template>
          </el-table-column>
          <el-table-column label="手机或电话2" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.mobile"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.mobile}}</span>
            </template>
          </el-table-column>
          <el-table-column label="职务" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.title"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.title}}</span>
            </template>
          </el-table-column>
          <el-table-column label="邮箱" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.email"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.email}}</span>
            </template>
          </el-table-column>
          <el-table-column label="传真" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.fax"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.fax}}</span>
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
                @click="deleteServiceOrgListOneRow(scope.$index)"
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
              @click="addConcatInfoOneRow"
              v-hasPermi="['system:definition:add']"
            >新增
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <!-- end the third card -->

    <!-- the forth card start -->
    <el-row class="mt10 mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-service mr5"></i>
          <span>账户信息</span>
        </div>
        <el-table :data="accountInfo" highlight-current-row>
          <el-table-column type="index" width="50" align="center">
          </el-table-column>
          <el-table-column label="账户类型" align="center">
            <template slot-scope="scope">
              <el-select v-model="scope.row.accountType"
                         v-show="scope.row.show"
                         clearable
                         size="mini">
                <el-option
                  v-for="dict in accountType"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
              <span v-show="!scope.row.show">{{scope.row.accountType}}</span>
            </template>
          </el-table-column>
          <el-table-column label="银行账户名称" align="center">
            <template slot-scope="scope">
              <el-select v-model="scope.row.accountName"
                         v-show="scope.row.show"
                         clearable
                         size="mini">
                <el-option
                  v-for="dict in accountName"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
              <span v-show="!scope.row.show">{{scope.row.accountName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="账户号" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.accountNo"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.accountNo}}</span>
            </template>
          </el-table-column>
          <el-table-column label="开户行" align="center">
            <template slot-scope="scope">
              <el-select v-model="scope.row.bankName"
                         v-show="scope.row.show"
                         clearable
                         size="mini">
                <el-option
                  v-for="dict in bankName"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"/>
              </el-select>
              <span v-show="!scope.row.show">{{scope.row.bankName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="联行号" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bankNo"
                        v-show="scope.row.show"
                        clearable
                        size="mini" placeholder="请输入"></el-input>
              <span v-show="!scope.row.show">{{scope.row.bankNo}}</span>
            </template>
          </el-table-column>
          <el-table-column label="账户状态" align="center">
            <template slot-scope="scope">
              <el-select v-model="scope.row.accountStatus"
                         v-show="scope.row.show"
                         clearable
                         size="mini">
                <el-option
                  v-for="dict in accountStatus"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"/>
              </el-select>
              <span v-show="!scope.row.show">{{scope.row.accountStatus}}</span>
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
              >保存
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-show="!scope.row.show"
                @click="scope.row.show=true"
              >编辑
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="deleteAccountInfoOneRow(scope.row)"
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
              @click="addAccountInfoOneRow"
            >新增
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <!-- end the forth card-->

    <!-- 按钮区域 -->
    <el-row style="text-align: right;">
      <el-col class="mt10 mb10">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="submitForm">保存</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="reset">重置</el-button>
        <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {getProvider, addProvider, updateProvider, getCharacterByNo, getContactsByNo} from "@/api/provider/provider";
  import {getToken} from "@/utils/auth";
  import {regionData} from 'element-china-area-data'

  export default {
    name: "AddProvider",
    data() {
      // 校验床位数
      var validateBedNum = (rule, value, callback) => {
        if (!value) { // 没有输入床位数 不用校验
          callback();
        } else { // 输入了床位数 进行数字格式校验
          if (typeof this.characterForm.bednum != 'number') {
            callback(new Error('床位数格式不匹配'));
          } else {
            callback();
          }
        }
      };
      // 日均门诊量
      var validateAvgDaily = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          if (typeof this.characterForm.outpatient != 'number') {
            callback(new Error('日均门诊量格式不匹配'));
          } else {
            callback();
          }
        }
      };
      // 年出院人数
      var validateYearNum = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          if (typeof this.characterForm.average != 'number') {
            callback(new Error('年出院人数格式不匹配'));
          } else {
            callback();
          }
        }
      };
      // 校验邮编
      var validatePostcode = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          var msg = /^[0-9]\d{5}(?!\d)$/;
          if (!(this.form.postcode.match(msg))) {
            callback(new Error('邮编格式不匹配'));
          } else {
            callback();
          }
        }
      };
      // 校验网址
      var validateWebsit = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          var msg = '(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?';
          if (!(this.form.website.match(msg))) {
            callback(new Error('网址格式不匹配'));
          } else {
            callback();
          }
        }
      };
      return {
        areaSelectData: regionData, // options绑定的数据就是引入的 provinceAndCityData
        // 加载
        loading: false,
        // 表单数据
        form: {
          servcomtype: '01',
          continent: 'Asia',
          country: 'CHN',
          selectedOptions: [], // 地区选择参数，['省区域码', '市区域码']
        },
        // 医疗特色表单
        characterForm: {},
        // 页面修改
        modifyNum: 0,
        // 保留form表单
        saveForm: this.form,
        // 表单校验
        rules: {
          servcomtype: [
            {required: true, message: '供应商类型为必填', trigger: 'change'},
          ],
          chname: [
            {required: true, message: "供应商中文名称不能为空", trigger: "blur"}
          ],
          detailedaddress: [
            {required: true, message: "详细地址不能为空", trigger: "blur"}
          ],
          postcode: [
            {required: false, trigger: 'blur', validator: validatePostcode}
          ],
          website: [
            {required: false, trigger: 'blur', validator: validateWebsit}
          ],
        },
        // 医疗特色校验
        characterRules: {
          bednum: [
            {required: false, trigger: 'blur', validator: validateBedNum}
          ],
          outpatient: [
            {required: false, trigger: 'blur', validator: validateAvgDaily}
          ],
          average: [
            {required: false, trigger: 'blur', validator: validateYearNum}
          ],
        },
        // 表格验证
        tableRules: {
          concatName: [
            {required: true, message: '联系人姓名不能为空', trigger: 'blur'}
          ],
          concatTel1: [
            {required: true, message: '联系方式不能为空', trigger: 'blur'}
          ],
        },
        // 机构类型字典 下拉
        medicaltypeOptions: [],
        // 机构属性字典 下拉
        comAttribute: [],
        // 医疗类型字典 下拉
        medicalType: [],
        // 医院类型字典 下拉
        hospitalDepartment: [],
        // 机构等级字典 下拉
        medicallevel: [],
        // 直付类型字典 下拉
        directPaymentType: [],
        // 直付方式字典 下拉
        paymentWay: [],
        // 医疗资质字典 下拉
        medicalQualification: [],
        // 是否昂贵字典 下拉
        isExpensive: [],
        // 州 下拉
        continent: [],
        // 国家
        coutryOptions: [],
        // 省份
        province: [],
        // 市
        city: [],
        // 区县
        district: [],
        // 联系人信息
        concatInfo: [],
        // 账户信息
        accountInfo: [],
        // 账户信息 - 账户类型 - 下拉
        accountType: [],
        // 账户信息 - 银行账户名称 - 下拉
        accountName: [],
        // 账户信息 - 银行账户名称 - 下拉
        bankName: [],
        // 账户信息 - 用户状态 - 下拉
        accountStatus: [],

        // 是否显示弹出层
        open: false,
        // 弹出层标题
        title: "",
        // 表格数据
        table: {},
      }
    },
    created() {
      // 获取机构编码
      const servcomno = this.$route.params && this.$route.params.servcomno;
      // 只有在机构编码存在的情况下（也就是编辑的时候），才会根据机构编码查询供应商
      if (servcomno != undefined) {
        // 获取供应商基本信息
        this.getProviderByServcomno(servcomno);
        // 获取医疗特色信息
        this.selectHmpHospCharacterByNo(servcomno);
        // 获取联系人信息
        this.selectHmpServContactsByNo(servcomno);
        // 获取对应州下的国家
        this.selectCountryByContinent(this.form.continent).then(response => {
          this.coutryOptions = response.data;
        });
      } else { // 新增
        // 新增页面一加载，页面显示为亚洲，所以国家默认加载为亚洲的国家
        this.selectCountryByContinent('Asia').then(response => {
          this.coutryOptions = response.data;
        });
      }

      // 获取数据字典 供应商类型
      this.getDicts("provider_servcomtype").then(response => {
        this.medicaltypeOptions = response.data;
      });
      // 获取数据字典 供应商属性
      this.getDicts("provider_servcomattribute").then(response => {
        this.comAttribute = response.data;
      });
      // 获取数据字典 医疗类型
      this.getDicts("provider_medicaltype").then(response => {
        this.medicalType = response.data;
      });
      // 获取数据字典 医院部门
      this.getDicts("provider_department").then(response => {
        this.hospitalDepartment = response.data;
      });
      // 获取数据字典 供应商等级
      this.getDicts("provider_medicallevel").then(response => {
        this.medicallevel = response.data;
      });
      // 获取数据字典 支付类型
      this.getDicts("provider_directPaymentType").then(response => {
        this.directPaymentType = response.data;
      });
      // 获取数据字典 支付类型
      this.getDicts("provider_paymentWay").then(response => {
        this.paymentWay = response.data;
      });
      // 获取数据字典 医疗资质
      this.getDicts("provider_qualifiedmdc").then(response => {
        this.medicalQualification = response.data;
      });
      // 获取数据字典 是否昂贵
      this.getDicts("provider_isExpensive").then(response => {
        this.isExpensive = response.data;
      });
      // 获取字典数据 州
      this.selectContinent().then(response => {
        this.continent = response.data;
      });


    },
    methods: {
      // 编辑格式化地址
      handleChange(value) {
        // console.log(value);
        //
        // var provinceCode = this.form.selectedOptions[0];
        // var cityCode = this.form.selectedOptions[1];
        // var districtCode = this.form.selectedOptions[2];
        // console.log(provinceCode);
        // console.log(cityCode);
        // console.log(districtCode);

        // var provinceName = CodeToText[provinceCode];
        // var cityName = CodeToText[cityCode];
        // var districtName = CodeToText[districtCode];
        //
        // var newProvinceCode = null;
        // this.selectPlaceCodeByPlaceName(cityName).then(response => {
        //   newProvinceCode = response.data.placecode;
        // });
        //
        // console.log(newProvinceCode);
        // this.selectPlaceCodeByPlaceName(cityName).then(response => {
        //   this.form.selectedOptions[1] = response.data.placecode;
        // });
        // this.selectPlaceCodeByPlaceName(districtName).then(response => {
        //   this.form.selectedOptions[2] = response.data.placecode;
        // });
        // CodeToText属性是区域码，属性值是汉字 CodeToText['110000']输出北京市
        // self.infoForm.province = CodeToText[provinceCode];
        // self.infoForm.city = CodeToText[cityCode];
        // console.log("选择的省市：", self.infoForm.province, self.infoForm.city);
      },
      // 获取到省市数据，进行格式化
      // formatCity() {
      //   var self = this;
      //   console.log(self.infoForm.province, self.infoForm.city);
      //   // TextToCode属性是汉字，属性值是区域码 TextToCode['北京市'].code输出110000
      //   // 省份
      //   var provinceCode = TextToCode[self.infoForm.province].code;
      //   // 城市
      //   var cityCode = TextToCode[self.infoForm.province][self.infoForm.city].code;
      //   self.selectedOptions = [provinceCode, cityCode];
      // },

      /** 新增或修改页面点击返回之后进行路由跳转和重新查询 */
      back() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/provider/manage/info'});
      },
      /** 根据servcomno获取Provider */
      getProviderByServcomno(servcomno) {
        getProvider(servcomno).then(response => {
          // 往修改页面带数据
          this.form = response.data;
        });
      },
      /** 根据servcomno获取医疗特色信息 */
      selectHmpHospCharacterByNo(servcomno) {
        getCharacterByNo(servcomno).then(response => {
          this.characterForm = response.data;
        });
      },
      /** 根据servcomno获取联系人信息 */
      selectHmpServContactsByNo(servcomno) {
        getContactsByNo(servcomno).then(response => {
          if (response.code == 200) {
            // 循环遍历每一条联系人信息 将show设置为false
            response.hmpServContactsList.forEach(item => {
              item.show = false;
            });
            this.concatInfo = response.hmpServContactsList;
          }
        });
      },
      /**
       * 添加或者修改按钮
       */
      submitForm() {
        // 表单为空校验
        this.$refs["form"].validate(valid1 => {
            this.$refs["characterForm"].validate(valid2 => {
              if (valid1 && valid2) {
                // 如果机构编码不为空 那么就是修改
                if (this.form.servcomno != null) {
                  // 如果没有输入联系人 并且 页面没有修改
                  if (this.concatInfo.length == 0 && this.modifyNum == 0) {
                    this.$message({
                      message: '当前服务机构已保存，请勿重复保存!',
                      type: 'error'
                    });
                    return false;
                  }
                  // 归零
                  this.modifyNum = 0;
                  // 创建数组，为联系人数组
                  var arr = new Array();
                  arr = this.concatInfo;
                  // 循环遍历联系人数组
                  for (var i = 0; i < arr.length; i++) {
                    if (arr[i] == undefined) {
                      this.$message({
                        message: '请输入正确的电话或者手机',
                        type: 'error'
                      });
                      continue;
                    }
                    var phone = arr[i].phone;
                    var mobile = arr[i].mobile;
                    var pattern3 = /(^([0-9]{3,4}(-)?)([0-9]{7,8})(-)?([0-9]{2,4})?$)|(^1[3|4|5|8|7][0-9]\d{4,8}$)/;
                    // var pattern3 = /(^[0-9]{3,3}\-[0-9]{7,7}$)|(^[0-9]{7,7}$)|(^\([0-9]{3,4}\)[0-9]{3,7}$)|(^0{0,1}13[0-9]{8}$)/;
                    if (!phone.match(pattern3)) {
                      this.$message({
                        message: '请输入正确的电话或者手机',
                        type: 'error'
                      });
                      return false;
                    }
                    if (!mobile.match(pattern3) && mobile != '' && mobile != 'null' && mobile != undefined) {
                      this.$message({
                        message: '请输入正确的第二个电话或者手机，或者可以选择不录入',
                        type: 'error'
                      });
                      return false;
                    }

                  }
                  // 修改操作
                  updateProvider(this.form, this.characterForm, this.concatInfo).then(response => {
                    if (response.code === 200) {
                      this.msgSuccess("修改成功");
                    }
                  });
                } else { // 新增
                  // 如果没有输入联系人 并且 页面没有修改
                  if (this.concatInfo.length == 0 && this.modifyNum == 0) {
                    this.$message({
                      message: '当前服务机构已保存，请勿重复保存!',
                      type: 'error'
                    });
                    return false;
                  }
                  // 归零
                  this.modifyNum = 0;
                  // 创建数组，为联系人数组
                  var arr = new Array();
                  arr = this.concatInfo;
                  // 循环遍历联系人数组
                  for (var i = 0; i <= arr.length; i++) {
                    if (arr[i] == undefined) {
                      this.$message({
                        message: '请输入正确的电话或者手机',
                        type: 'error'
                      });
                      continue;
                    }
                    var phone = arr[i].phone;
                    var mobile = arr[i].mobile;
                    var pattern3 = /(^([0-9]{3,4}(-)?)([0-9]{7,8})(-)?([0-9]{2,4})?$)|(^1[3|4|5|8|7][0-9]\d{4,8}$)/;
                    // var pattern3 = /(^[0-9]{3,3}\-[0-9]{7,7}$)|(^[0-9]{7,7}$)|(^\([0-9]{3,4}\)[0-9]{3,7}$)|(^0{0,1}13[0-9]{8}$)/;
                    if (!phone.match(pattern3)) {
                      this.$message({
                        message: '请输入正确的电话或者手机',
                        type: 'error'
                      });
                      return false;
                    }
                    if (!mobile.match(pattern3) && mobile != '' && mobile != 'null' && mobile != undefined) {
                      this.$message({
                        message: '请输入正确的第二个电话或者手机，或者可以选择不录入',
                        type: 'error'
                      });
                      return false;
                    }
                    // 新增操作
                    addProvider(this.form, this.characterForm, this.concatInfo).then(response => {
                      if (response.code === 200) {
                        this.msgSuccess("新增成功");
                      }
                    });
                    return false;
                  }
                }
              } else {
                // 页面跳转至 第一个不符合规范的地方
                setTimeout(() => {
                  var isError = document.getElementsByClassName("is-error");
                  isError[0].querySelector(['input', 'textarea']).focus();
                }, 100);
                this.$message({
                  message: '请检查输入内容是否符合规范，再进行保存',
                  type: 'warning'
                });
              }
            });
          }
        );
      },
      /** 重置按钮 */
      reset() {
        // 获取机构编码
        const servcomno = this.$route.params && this.$route.params.servcomno;
        // 只有在机构编码存在的情况下（也就是编辑的时候），重置需要重新查询一下
        if (servcomno != undefined) {
          this.getProviderByServcomno(servcomno);
        } else {
          this.form = {servcomtype: '01', states: 'Asia'};
        }

        this.$message({
          message: '已重置',
          type: 'success'
        });
      },
      /** 联系人信息添加一行 */
      addConcatInfoOneRow() {
        var concatInfoLength = this.concatInfo.length;
        // 新增第二行开始
        if (concatInfoLength >= 1) {
          if (this.concatInfo[concatInfoLength - 1] != undefined) {
            var name = this.concatInfo[concatInfoLength - 1].name;
            var phone = this.concatInfo[concatInfoLength - 1].phone;
            var mobile = this.concatInfo[concatInfoLength - 1].mobile;
            if (name == "") {
              this.$message({
                showClose: true,
                message: '请确认姓名已经输入！',
                type: 'error'
              });
            } else if (phone == "" && mobile == "") {
              this.$message({
                showClose: true,
                message: '手机和电话至少录入一项！',
                type: 'error'
              });
            } else {
              const row = {
                name: '',
                phone: '',
                mobile: '',
                title: '',
                email: '',
                fax: '',
                show: true,
              };
              this.concatInfo.push(row);
            }
          }
        } else { // 新增第一行
          const row = {
            name: '',
            phone: '',
            mobile: '',
            title: '',
            email: '',
            fax: '',
            show: true
          };
          this.concatInfo.push(row);
        }
      },
      /** 账户信息添加一行 */
      addAccountInfoOneRow() {
        const row = {
          accountType: '',
          accountName: '',
          accountNo: '',
          bankName: '',
          bankNo: '',
          bankStatus: '',
          show: true,
        };
        this.accountInfo.push(row);
      },
      /** 联系人信息删除一行 */
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

      /** 账户信息删除一行 */
      deleteAccountInfoOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.accountInfo.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      // 选择州
      selectContinentOption() {
        if (this.form.continent != '') {
          // 清空国家下拉框选中
          this.form.country = '';
          this.form.selectedOptions = '';
          this.areaSelectData = undefined;
          // 设置国家下拉框内容列表
          this.selectCountryByContinent(this.form.continent).then(response => {
            this.coutryOptions = response.data;
          });
        } else { // clear州下拉框的时候
          this.form.country = '';
          this.coutryOptions = undefined;
          this.form.selectedOptions = '';
          this.areaSelectData = undefined;
        }
      },
      // 选择 国家
      selectCoutryOption() {
        if (this.form.country != 'CHN') {
          this.areaSelectData = undefined;
        } else {
          this.areaSelectData = regionData;
        }
      },

    },
    // 监听函数
    watch: {
      form: { // 监听form
        handler() {
          this.modifyNum++;
        },
        deep: true,
      },
      characterForm: {
        handler() {
          this.modifyNum++;
        },
        deep: true,
      },
    },
  }
</script>

<style scoped>
  #master:before {
    content: '*';
    color: #ff4949;
    margin-right: 4px;
  }
</style>
