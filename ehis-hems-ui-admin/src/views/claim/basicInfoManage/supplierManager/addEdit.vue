<template>
  <div class="supplier">
    <!-- 基本信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>基本信息</span>
        <span style="float: right;">
            <el-button size="mini" type="primary" @click="saveInfo">保存</el-button>
        </span>
      </div>
      <el-form ref="supplier" :rules="supplierRule" :inline="true" :model="supplier"
               style="padding-bottom: 30px;" label-position="right" label-width="135px" size="small">
        <el-row>
          <el-col :span="8">
            <el-form-item label="供应商编码：" prop="serialNo">
              <el-input disabled class="item-width" v-model="supplier.serialNo" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="chname" label="供应商中文名称：">
              <el-input v-model="supplier.chname" maxlength="200" :placeholder="placeType2" class="item-width"
                        clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="enname" label="供应商英文名称：">
              <el-input v-model="supplier.enname" maxlength="200" class="item-width" :placeholder="placeType2"
                        clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item prop="servcomType" label="供应商类型：">
              <el-select v-model="supplier.servcomType" class="item-width" :placeholder="placeType1">
                <el-option v-for="item in supplier_typeOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="continent" label="洲：">
              <el-select v-model="supplier.continent" :placeholder="placeType1" class="item-width">
                <el-option key="01" label="亚洲"
                           value="01"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="country" label="国家：">
              <el-select v-model="supplier.country" :placeholder="placeType1" class="item-width">
                <el-option key="01" label="中国"
                           value="01"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="所属地区：" prop="region">
              <el-cascader :options="regions" v-model="region" class="item-width" :placeholder="placeType1"
                           @change="handleChange"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="bussinessStatus" label="状态：">
              <el-select v-model="supplier.bussinessStatus" :placeholder="placeType1" class="item-width">
                <el-option v-for="item in accountacc_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item :style="{width:'100%'}" :class="['long-input']" prop="detailedAddress"
                          label="详细地址：">
              <el-input v-model="supplier.detailedAddress" :placeholder="placeType2" maxlength="300" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item :style="{width:'100%'}" :class="['long-input']" prop="remark" label="备注：">
              <el-input type="textarea" :rows="5" v-model="supplier.remark" :placeholder="placeType2" maxlength="2000"
                        clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <!-- 开票信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>开票信息</span>
      </div>
      <el-form ref="receiptForm" :model="receiptForm"
               style="padding-bottom: 30px;" label-position="right" label-width="135px" size="small">
        <el-col :span="8">
          <el-form-item prop="companyTitle" label="开票名称：">
            <el-input v-model="receiptForm.companyTitle" :placeholder="placeType2" class="item-width" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="accNum" label="账号：">
            <el-input v-model="receiptForm.accNum" class="item-width" :placeholder="placeType2" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="address" label="地址：">
            <el-input v-model="receiptForm.address" :placeholder="placeType2" class="item-width" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="billInfo" label="开票信息：">
            <el-input v-model="receiptForm.billInfo" class="item-width" :placeholder="placeType2" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="dutynum" label="纳税人识别号：">
            <el-input v-model="receiptForm.dutynum" :placeholder="placeType2" class="item-width" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="phone" label="电话：">
            <el-input v-model="receiptForm.phone" class="item-width" :placeholder="placeType2" clearable/>
          </el-form-item>
        </el-col>
      </el-form>
    </el-card>

    <!-- 联系人信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>联系人信息</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="saveConstact">保存</el-button>
        </span>
      </div>
      <el-form ref="contactForm" :rules="constactRules" :model="contactForm" size="small">
        <el-table ref="contacttable" :data="contactForm.contacts"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}" highlight-current-row
                  size="small" style="width: 100%;">
          <el-table-column prop="placeType" align="center" header-align="center" label="联系人类型" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'contacts.' + scope.$index + '.placeType'"
                            :rules="constactRules.placeType">
                <el-select v-model="scope.row.placeType" placeholder="请选择" size="mini">
                  <el-option v-for="item in linkman_typeOptions" :key="item.dictValue" :label="item.dictLabel"
                             :value="item.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{selectDictLabel(linkman_typeOptions, scope.row.placeType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="name" align="center" header-align="center" label="姓名" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'contacts.' + scope.$index + '.name'"
                            :rules="constactRules.name">
                <el-input v-model="scope.row.name" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{scope.row.name}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="phone" align="center" header-align="center" label="手机" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'contacts.' + scope.$index + '.phone'"
                            :rules="constactRules.phone">
                <el-input v-model="scope.row.phone" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{scope.row.phone}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="mobile" align="center" header-align="center" label="电话" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'contacts.' + scope.$index + '.mobile'"
                            :rules="constactRules.mobile">
                <el-input v-model="scope.row.mobile" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{scope.row.mobile}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="email" align="center" header-align="center" label="邮箱" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'contacts.' + scope.$index + '.email'"
                            :rules="constactRules.email">
                <el-input v-model="scope.row.email" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{scope.row.email}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="role" align="center" header-align="center"
                           label="登录名" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'contacts.' + scope.$index + '.role'"
                            :rules="constactRules.role">
                <el-input v-model="scope.row.role" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{scope.row.role}}</span>
            </template>
          </el-table-column>
          <el-table-column fixed="right" align="center" header-align="center" label="操作" min-width="1">
            <template slot-scope="scope">
              <el-form-item>
                <el-button v-if="!scope.row.isShow" type="text" size="small"
                           @click="scope.row.isShow=true">编辑
                </el-button>
                <el-button type="text" size="small" style="color: red"
                           @click="delContactRow(scope.$index,scope.row)">删除
                </el-button>
              </el-form-item>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addContactRow()"> + 添加
      </el-button>
    </el-card>

    <!-- 账户信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>账户信息</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="saveAccount">保存</el-button>
        </span>
      </div>
      <el-form ref="accountForm" :rules="accountRules" :model="accountForm" size="small">
        <el-table ref="accounttable" :data="accountForm.account"
                  :header-cell-style="{color:'black',background:'#f8f8f8'}" highlight-current-row
                  size="small" style="width: 100%;">
          <el-table-column prop="accountType" align="center" header-align="center" label="账户类型" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.accountType'"
                            :rules="accountRules.accountType">
                <el-select v-model="scope.row.accountType" placeholder="请选择" size="mini">
                  <el-option v-for="item in accountTypeOptions" :key="item.dictValue" :label="item.dictLabel"
                             :value="item.dictValue"/>
                </el-select>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ selectDictLabel(accountTypeOptions, scope.row.accountType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="businessCode" align="center" header-align="center" label="银联商户代码" min-width="3"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.businessCode'"
                            :rules="accountRules.businessCode">
                <el-input v-model="scope.row.businessCode" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.businessCode}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accountName" align="center" header-align="center" label="银行账户名称" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.accountName'"
                            :rules="accountRules.accountName">
                <el-input v-model="scope.row.accountName" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.accountName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accountNo" align="center" header-align="center" label="银行账号" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.accountNo'"
                            :rules="accountRules.accountNo">
                <el-input v-model="scope.row.accountNo" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.accountNo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankCode" align="center" header-align="center" label="开户银行代码" min-width="3"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.bankCode'"
                            :rules="accountRules.bankCode">
                <el-input v-model="scope.row.bankCode" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.bankCode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankName" align="center" header-align="center" label="开户银行名称" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.bankName'"
                            :rules="accountRules.bankName">
                <el-input v-model="scope.row.bankName" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.bankName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankDetail" align="center" header-align="center" label="银行信息描述"
                           min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.bankDetail'"
                            :rules="accountRules.bankDetail">
                <el-input v-model="scope.row.bankDetail" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-if="!scope.row.isShow">{{ scope.row.bankDetail }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bussinessStatus" align="center" header-align="center" label="账户状态" min-width="2"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isShow" :prop="'account.' + scope.$index + '.bussinessStatus'"
                            :rules="accountRules.bussinessStatus">
                <el-select v-model="scope.row.bussinessStatus" placeholder="请选择" size="mini">
                  <el-option v-for="item in accountacc_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                             :value="item.dictValue"/>
                </el-select>
              </el-form-item>
              <span
                v-if="!scope.row.isShow">{{selectDictLabel(accountacc_statusOptions,scope.row.bussinessStatus)}}</span>
            </template>
          </el-table-column>&ndash;&gt;
          <el-table-column fixed="right" align="center" header-align="center" label="操作" min-width="2">
            <template slot-scope="scope">
              <el-form-item>
                <el-button v-if="!scope.row.isShow" type="text" size="small"
                           @click="scope.row.isShow=true">编辑
                </el-button>
                <el-button type="text" size="small" style="color: red"
                           @click="delAccountRow(scope.$index,scope.row)">删除
                </el-button>
              </el-form-item>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec; margin: 10px 0 20px;"
                 @click="addAccountRow"> + 添加
      </el-button>
    </el-card>

    <!--已选服务机构列表-->
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>已选服务机构列表</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="addService">新增</el-button>
        </span>
      </div>
      <div style="position: relative">
        <el-table
          size="small"
          highlight-current-row
          :data="serviceData"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="websiteCode" label="服务机构编码" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="websitecName" label="服务机构名称" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="websitecType" label="服务机构类型" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(service_typeOptions, scope.row.websitecType)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="addressdetail" label="所属地区" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" label="操作" min-width="94" fixed="right">
            <template slot-scope="scope">
              <el-button size="small" type="text" style="color: red" @click="deleteService(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--     <el-divider/>-->
        <div style="text-align: right; margin-right: 10px;margin-top: 50px">
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
          <el-button size="mini" type="primary" @click="saveAll">保存</el-button>
          <el-button size="mini" type="primary" @click="closeAll">关闭</el-button>
        </div>
      </div>
    </el-card>

    <!--新增服务机构列表弹出框-->
    <el-dialog :visible.sync="dialogFormVisible" width="80%" hight="90%">
      <el-card class="box-card">
        <el-form :model="serviceForm" label-width="110px" :rules="serviceFormRules" ref="serviceForm">
          <el-row>
            <el-col :span="8">
              <el-form-item label="服务机构名称：" prop="websitecName">
                <el-input v-model="serviceForm.websitecName" class="item-width" size="mini" placeholder="请输入"
                />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="服务机构类型：" prop="websitecType">
                <el-select v-model="serviceForm.websitecType" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="item in service_typeOptions" :key="item.dictValue" :label="item.dictLabel"
                             :value="item.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="所属地区：">
                <el-cascader :options="serviceRegions" v-model="serviceRegion" class="item-width" size="mini"
                             placeholder="请选择"
                             @change="serviceChange"/>
              </el-form-item>
            </el-col>
          </el-row>
          <div style="text-align: right; margin-right: 10px;">
            <el-button size="mini" type="success" @click="searchService">查询</el-button>
            <el-button size="mini" type="primary" @click="resetServiceForm">重置</el-button>
          </div>
        </el-form>
      </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>服务机构列表</span>
          <span style="text-align: right; margin-right: 13px; float: right">
           <el-button type="success" size="mini" @click="saveService">确定</el-button>
              <el-button type="primary" size="mini"
                         @click="dialogFormVisible=false">取消</el-button>
        </span>
        </div>

        <el-table
          :data="serviceTableData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          @selection-change="selectionLineChangeHandle"
        >
          <el-table-column
            type="selection"
            width="40"/>
          <el-table-column prop="providerCode" label="服务机构编码" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="chname1" label="服务机构名称" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="orgFlag" label="服务机构类型" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(service_typeOptions, scope.row.orgFlag)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="addressdetail" label="所属地区" align="center" show-overflow-tooltip/>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="serviceInfo.pageNum"
          :limit.sync="serviceInfo.pageSize"
          @pagination="search"
        />
      </el-card>
    </el-dialog>
  </div>
</template>

<script>
  import {
    getAddress, getInfo, getReceip, listContacts, listBank, listProviderInfo, saveAll,
    saveContacts, saveBank, getService, saveService, deleteService, getListInfo, addInfo,
    deleteContacts
  } from '@/api/supplierManager/supplier'
  import service from "../../../../utils/request";

  let dictss = [{dictType: 'linkman_type'}, {dictType: 'account_Types'}, {dictType: 'accountacc_status'}
    , {dictType: 'service_type'}, {dictType: 'supplier_type'},]
  export default {
    name: 'AddEdit',
    filters: {},
    data() {
      const checkFormMobilephone = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.phone', '').replace('.mobile', '')
        const {mobile, phone} = this.contactForm.contacts[index]
        const phonereg = /^[1][3|4|5|6|7|8|9][0-9]{9}$/
        if ((phone === null || phone === '') && (mobile === null || mobile === '')) {
          this.indexContacts[index] = 1
          callback(new Error('至少填一项'))
        } else {
          if (phone !== null && phone !== '' && !phonereg.test(phone)) {
            this.indexContacts[index] = 1
            callback(new Error('请输入正确移动电话'))
          }
          callback()
        }
      }
      const checkFormOfficephone = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.phone', '').replace('.mobile', '')
        const {phone, mobile} = this.contactForm.contacts[index]
        if ((phone === null || phone === '') && (mobile === null || mobile === '')) {
          this.indexContacts[index] = 1
          callback(new Error('至少填一项'))
        } else {
          callback()
        }
      }
      const isChName = (rules, value, callback) => {
        let query = {
          chname: this.supplier.chname
        }
        if (!value) {
          callback(new Error('请输入供应商中文名称'))
        } else {
          if (this.supplier.serialNo != null && this.supplier.serialNo !== '') {
            if (value !== this.chnameFlag) {
              getListInfo(query).then(res => {
                if (res != null && res.code === 200 && res.total > 0) {
                  callback(new Error('供应商中文名称已存在！'))
                } else {
                  callback()
                }
              })
            } else {
              callback()
            }
          } else {
            getListInfo(query).then(res => {
              if (res != null && res.code === 200 && res.total > 0) {
                callback(new Error('供应商中文名称已存在！'))
              } else {
                callback()
              }
            })
          }
        }
      }
      const isEnglish = (rules, value, callback) => {
        let query = {
          enname: this.supplier.enname
        }
        const num = /^[a-zA-Z]+$/
        if (!value) {
          callback(new Error('请输入英文供应商名称'))
        } else {
          if (this.supplier.serialNo != null && this.supplier.serialNo !== '') {
            if (value !== this.ennameFlag) {
              getListInfo(query).then(res => {
                if (res != null && res.code === 200 && res.total > 0) {
                  callback(new Error('供应商英文名称已存在！'))
                } else {
                  callback()
                }
              })
            } else {
              callback()
            }
          } else {
            getListInfo(query).then(res => {
              if (res != null && res.code === 200 && res.total > 0) {
                callback(new Error('供应商英文名称已存在！'))
              } else {
                callback()
              }
            })
          }
        }
      }
      const checkRegion = (rules, value, callback) => {
        if (this.region === null || this.region.length === 0) {
          callback(new Error('请选择所属地区'))
        } else {
          callback()
        }
      }
      const checkRole = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.role', '')
        if (!value) {
          if (this.contactForm.contacts[index].placeType==='01'){
            callback(new Error('请输入登录名'))
          }else {
            callback()
          }
        }else {
          callback()
        }
      }
      const checkOne = (rules, value, callback) => {
        const form = this.serviceForm
        if ((form.websitecName === null || form.websitecName === '') && (form.websitecType === null || form.websitecType === '') && (this.serviceRegion === null || this.serviceRegion.length === 0)) {
          callback(new Error('至少填一项'))
        } else {
          callback()
        }
      }
      return {
        chnameFlag: undefined,
        ennameFlag: undefined,
        isShow: false,
        hasBlock: false,
        dialogFormVisible: false,
        contactShow: true,
        accountShow: true,
        querys: [],
        serviceData: [],//已选服务机构列表
        serviceRegions: [],
        serviceRegion: [],
        serviceTableData: [],
        totalCount: 0,
        serviceInfo: {
          pageNum: 1,
          pageSize: 10,
        },
        serviceForm: {
          websitecName: '',
          websitecType: '',
          province: '', // 省
          city: '', // 市
          district: '', // 区
        },
        addbtn: true, // 操作类型
        placeType1: '请选择',
        placeType2: '请输入',
        supplier: { // 基本信息
          serialNo: '', // 供应商编码
          servcomType: '', // 供应商类型
          bussinessStatus: '01', // 状态，是否有效
          chname: '', // 中文名
          enname: '', // 英文名
          continent: '',//洲
          country: '',//国家
          province: '', // 省
          city: '', // 市
          district: '', // 区
          detailedAddress: '', // 地址
          remark: '' // 备注
        },
        receiptForm: {//开票信息
          companyTitle: '',//开票名称
          accNum: '',//账号
          address: '',//地址
          billInfo: '',//开票信息
          dutynum: '',//纳税人识别号
          phone: '',//电话

        },
        indexContacts: [], // 联系人报错下标
        indexAccount: [], // 账户报错下标
        contactForm: {
          contacts: []
          // 联系人列表
        },
        accountForm: {
          account: [] // 账户信息
        },
        /*        receiptRules: {
                  companyTitle: [{required: true, message: '请输入开票名称', trigger: 'blur'}],
                  dutynum: [{required: true, message: '请输入纳税人识别号名称', trigger: 'blur'}],
                  accNum: [{required: true, message: '请输入账号', trigger: 'blur'}],
                  address: [{required: true, message: '请输入地址', trigger: 'blur'}],
                  billInfo: [{required: true, message: '请输入开票信息', trigger: 'blur'}],
                  phone: [{required: true, message: '请输入电话', trigger: 'blur'}],
                },//开票信息表单验证*/
        constactRules: {
          placeType: [{required: true, message: '请输入联系人类型', trigger: 'blur'}],
          name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
          role:  [{validator: checkRole, trigger: 'blur'}],
          phone: [{validator: checkFormMobilephone, trigger: 'blur'}],
          mobile: [{validator: checkFormOfficephone, trigger: 'blur'}],
        },
        accountms: [],
        accountRules: {
          accountType: [{required: true, message: '请选择账户类型', trigger: 'blur'}],
          businessCode: [{required: true, message: '请输入银联商户代码', trigger: 'blur'}],
          accountName: [{required: true, message: '请输入银行账户名称', trigger: 'blur'}],
          accountNo: [{required: true, message: '请输入账户号', trigger: 'blur'}],
          bankCode: [{required: true, message: '请输入开户银行代码', trigger: 'blur'}],
          bankName: [{required: true, message: '请输入开户银行名称', trigger: 'blur'}],
          bankDetail: [{required: true, message: '请输入银行信息描述', trigger: 'blur'}],
          bussinessStatus: [{required: true, message: '请选择账户状态', trigger: 'blur'}],
        },
        regions: [], // 省市区下拉选项
        region: [], // 当前选中的省市区  '110000','110100','110105'
        supplierRule: {
          chname: [{validator: isChName, required: true, trigger: 'blur'}],
          enname: [{validator: isEnglish, required: true, trigger: 'blur'}],
          servcomType: [{required: true, message: '请选择供应商类型', trigger: 'blur'}],
          continent: [{required: true, message: '请选择洲', trigger: 'blur'}],
          country: [{required: true, message: '请选择国家', trigger: 'blur'}],
          region: [{validator: checkRegion, required: true, trigger: 'blur'}],
          detailedAddress: [{required: true, message: '请输入详情地址', trigger: 'blur'}],
        },
        serviceFormRules: {
          websitecName: [{validator: checkOne, trigger: 'blur'}],
          websitecType: [{validator: checkOne, trigger: 'blur'}],
          serviceRegion: [{validator: checkOne, trigger: 'blur'}],
        },
        show: false,
        dataOnLineListSelections: [],
        linkman_typeOptions: [],
        accountTypeOptions: [],
        accountacc_statusOptions: [],
        service_typeOptions: [],
        supplier_typeOptions: [],
        dictList: [],
      }
    },
    created
      () {
      this.getAddressData()

      // 判断是编辑还是新增
      this.querys = JSON.parse(decodeURI(this.$route.query.data))
      if (this.querys.status === 'edit') {//修改
        //调用初始化查询方法  this.querys.serialNo
        this.initSearch(this.querys.serialNo)
      }

    }
    ,
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.linkman_typeOptions = this.dictList.find(item => {
        return item.dictType === 'linkman_type'
      }).dictDate
      this.accountTypeOptions = this.dictList.find(item => {
        return item.dictType === 'account_Types'
      }).dictDate
      this.accountacc_statusOptions = this.dictList.find(item => {
        return item.dictType === 'accountacc_status'
      }).dictDate
      this.service_typeOptions = this.dictList.find(item => {
        return item.dictType === 'service_type'
      }).dictDate
      this.supplier_typeOptions = this.dictList.find(item => {
        return item.dictType === 'supplier_type'
      }).dictDate
    }
    ,
    methods: {
      initSearch(serialNo) {
        //查询基本信息
        getInfo(serialNo).then(res => {
          if (res != null && res.code === 200) {
            this.supplier = res.data
            this.region = res.data.detail
            this.ennameFlag = res.data.enname
            this.chnameFlag = res.data.chname
          }
        })
        //查询开票信息
        getReceip(serialNo).then(res => {
          if (res != null && res.code === 200 && res.data) {
            this.receiptForm = res.data
          }
        })
        //查询联系人信息
        const query = {
          supplierCode: serialNo
        }
        listContacts(query).then(res => {
          if (res != null && res.code === 200) {
            this.contactForm.contacts = res.rows
            this.contactForm.contacts.forEach(item => {
              item.isShow = false
            })
          }
        })
        //查询账户信息
        const query2 = {
          providerCode: serialNo
        }
        listBank(query2).then(res => {
          this.accountForm.account = res.rows
          this.accountForm.account.forEach(item => {
            item.isShow = false
            item.accountFlag = item.accountNo
          })
        })
        //查询服务机构信息
        const query3 = {
          supplierCode: serialNo
        }
        listProviderInfo(query3).then(res => {
          this.serviceData = res.rows
        })

      }
      ,
      selectChange(row, value) {
        row.unitebankcode = value[3]
        /* this.accountForm.account[i].unitebankcode = ''
          this.accountunitebankcode[i] = [{ label: value[3], value: value[3] }]*/
      }
      ,
      // 增加一行联系人信息
      addContactRow() {//placeType name phone mobile email accountNo
        const field = {
          supplierCode: '',
          placeType: '',
          name: '',
          phone: '',
          mobile: '',
          email: '',
          accountNo: '',
          isShow: true
        }
        this.contactForm.contacts.push(field)
      }
      ,
      // 删除一行联系人信息
      delContactRow(index, row) {
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (row.supplierCode!='' && row.supplierCode!=null){
            let data={
              serialNo:row.serialNo
            }
            deleteContacts(data).then(res=>{
              if (res!=null && res.code===200){
                this.$message({
                  message: '删除成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                //查询联系人信息
                const query = {
                  supplierCode: this.querys.serialNo
                }
                listContacts(query).then(res => {
                  if (res != null && res.code === 200) {
                    this.contactForm.contacts = res.rows
                    this.contactForm.contacts.forEach(item => {
                      item.isShow = false
                    })
                  }
                })
              }else {
                this.$message.error('删除失败！')
              }
            })
          }else {
            this.contactForm.contacts.splice(index, 1)
            this.$message({
              message: '删除成功！',
              type: 'success',
              center: true,
              showClose: true
            })
          }



        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      }
      ,
      // 添加一行账户信息
      addAccountRow() {//accountType providerCode accountName accountNo bankCode bankName bankDetail status
        const field = {
          providerCode: '',
          accountType: '',
          businessCode: '',
          accountName: '',
          accountNo: '',
          bankCode: '',
          bankName: '',
          bankDetail: '',
          bussinessStatus: '',
          isShow: true
        }
        this.accountForm.account.push(field)
      }
      ,
      // 删除一行账户信息
      delAccountRow(index, row) {
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.accountForm.account.splice(index, 1)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      }
      ,
      // 重置
      resetForm() {
        this.$confirm('确定重置？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.supplier.serialNo !== null && this.supplier.serialNo !== '') {
            this.initSearch(this.supplier.serialNo)
          } else {
            this.$refs['supplier'].resetFields()
            this.$refs['receiptForm'].resetFields()
            this.contactForm.contacts = []
            this.accountForm.account = []
            this.region = []
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      }
      ,
      resetServiceForm() {
        this.$refs['serviceForm'].resetFields()
        this.serviceRegion = []
      }
      ,
      searchService() {
        let params = {
          pageNum: this.serviceInfo.pageNum,
          pageSize: this.serviceInfo.pageSize,
          chname1: this.serviceForm.websitecName,
          orgFlag: this.serviceForm.websitecType,
          supplierCode: this.querys.serialNo,
          province: undefined,
          city: undefined,
          district: undefined,
        }
        if (this.serviceRegion) {
          params.province = this.serviceRegion[0] // 省
          params.city = this.serviceRegion[1] // 市
          params.district = this.serviceRegion[2]// 区
        }

        getService(params).then(res => {
          if (res != null && res.code === 200) {
            this.serviceTableData = res.rows
            this.totalCount = res.total
            if (res.rows.length < 1) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(() => {
        })

      },
      search() {
        let params = {
          pageNum: this.serviceInfo.pageNum,
          pageSize: this.serviceInfo.pageSize,
          chname1: this.serviceForm.websitecName,
          orgFlag: this.serviceForm.websitecType,
          province: undefined,
          city: undefined,
          district: undefined,
        }
        getService(params).then(res => {
          if (res != null && res.code === 200) {
            this.serviceTableData = res.rows
            this.totalCount = res.total
          }
        }).catch(() => {

        })
      },
      handleChange(value) {
        this.supplier.province = value[0]
        this.supplier.city = value[1]
        this.supplier.district = value[2]
      }
      ,
      serviceChange(value) {
        this.serviceForm.province = value[0]
        this.serviceForm.city = value[1]
        this.serviceForm.district = value[2]
      }
      ,
      // 地址下拉选
      getAddressData() {
        getAddress().then(response => {
          this.regions = response
          this.serviceRegions = response
        }).catch(error => {
          console.log(error)
        })
      }
      ,

      returnHistory() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.go(-1)
      }
      ,
      selectionLineChangeHandle(val) {
        this.dataOnLineListSelections = val
      }
      ,
      saveConstact() {
        this.hasBlock = true
        this.$refs.contactForm.validate((valid) => {
          if (!valid) {
            this.hasBlock = false
          }
        })
        this.contactForm.contacts.forEach(item => {
          item.supplierCode = this.supplier.serialNo
        })
        if (this.hasBlock) {
          if (this.supplier.serialNo !== null && this.supplier.serialNo !== '') {
            if (this.contactForm.contacts.length > 0) {
              saveContacts(this.contactForm.contacts).then(res => {
                if (res != null && res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                }
              }).catch(res => {
                this.$message.error('保存失败！')
              })
            } else {
              return this.$message.warning("请先添加联系人信息！");
            }

          } else {
            return this.$message.warning("请先保存基本信息！");
          }
        }
      }
      ,
      saveAccount() {
        this.hasBlock = true
        this.$refs.accountForm.validate((valid) => {
          if (!valid) {
            this.hasBlock = false
          }
        })
        this.accountForm.account.forEach(item => {
          item.providerCode = this.supplier.serialNo
        })
        if (this.hasBlock && this.supplier.serialNo != null && this.supplier.serialNo !== '') {
          if (this.supplier.serialNo !== null && this.supplier.serialNo !== '')
            if (this.accountForm.account.length > 0) {
              let flag = true
              let accountTypeNum = 0
              for (let i = 0; i < this.accountForm.account.length - 1; i++) {
                for (let j = i + 1; j < this.accountForm.account.length; j++) {
                  if (this.accountForm.account[i].accountNo === this.accountForm.account[j].accountNo) {
                    flag = false
                  }
                }
              }
              this.accountForm.account.forEach(item => {
                if (item.accountType === '01') {
                  accountTypeNum++
                }
              })
              if (accountTypeNum > 1) {
                return this.$message.warning("对公账号只能存在一个！");
              } else {
                if (flag) {
                  saveBank(this.accountForm.account).then(res => {
                    if (res != null && res.code === 200) {
                      this.$message({
                        message: '保存成功！',
                        type: 'success',
                        center: true,
                        showClose: true
                      })
                    }
                  }).catch(res => {
                    this.$message.error('保存失败！')
                  })
                } else {
                  return this.$message.warning("存在相同的账户号！");
                }
              }
            } else {
              return this.$message.warning("请先添加账户信息！");
            }

        } else {
          return this.$message.warning("请先保存基本信息！");
        }
      }
      ,
      saveService() {
        if (this.supplier.serialNo !== null && this.supplier.serialNo !== '') {
          let data = {
            serialNo: this.supplier.serialNo,
            baseProviderInfoList: this.dataOnLineListSelections
          }
          if (this.dataOnLineListSelections.length <= 0) {
            return this.$message.warning("请选择新增的数据！");
          } else {
            saveService(data).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '新增成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.addService()
                this.initSearch(this.supplier.serialNo)
              }
            }).catch(res => {
              this.$message.error('新增失败！')
            })
          }
        } else {
          return this.$message.warning("请先保存基本信息！");
        }

      }
      ,
      deleteService(row) {
        let data = {
          websiteCode: row.websiteCode,
          supplierCode: this.supplier.serialNo
        }
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteService(data).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '删除成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              //查询服务机构信息
              const query3 = {
                supplierCode: this.supplier.serialNo
              }
              listProviderInfo(query3).then(res => {
                this.serviceData = res.rows
              })
            }
          }).catch(res => {
            this.$message.error('删除失败！')
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })


      },
      saveAll() {
        this.$refs.supplier.validate((valid) => {
          if (valid) {
            this.$refs.receiptForm.validate((valid) => {
              if (valid) {
                this.$refs.contactForm.validate((valid) => {
                  if (valid) {
                    this.$refs.accountForm.validate((valid) => {
                      if (valid) {
                        if (this.contactForm.contacts.length <= 0) {
                          return this.$message.warning("至少添加一条联系人信息！");
                        }
                        let data = {
                          serialNo: this.supplier.serialNo, // 供应商编码
                          servcomType: this.supplier.servcomType, // 供应商类型
                          bussinessStatus: this.supplier.bussinessStatus, // 状态，是否有效
                          chname: this.supplier.chname, // 中文名
                          enname: this.supplier.enname, // 英文名
                          continent: this.supplier.continent,//洲
                          country: this.supplier.country,//国家
                          province: this.supplier.province, // 省
                          city: this.supplier.city, // 市
                          district: this.supplier.district, // 区
                          detailedAddress: this.supplier.detailedAddress, // 地址
                          remark: this.supplier.remark, // 备注
                          baseBankList: this.accountForm.account,//账户信息
                          baseContactsList: this.contactForm.contacts,//账户信息
                          baseSupplierReceipList: [this.receiptForm],//开票信息

                        }

                        let flag = true
                        let accountTypeNum = 0
                        for (let i = 0; i < this.accountForm.account.length - 1; i++) {
                          for (let j = i + 1; j < this.accountForm.account.length; j++) {
                            if (this.accountForm.account[i].accountNo === this.accountForm.account[j].accountNo) {
                              flag = false
                            }
                          }
                        }
                        this.accountForm.account.forEach(item => {
                          if (item.accountType === '01') {
                            accountTypeNum++
                          }
                        })
                        if (accountTypeNum > 1) {
                          return this.$message.warning("对公账号只能存在一个！");
                        } else {
                          if (flag) {
                            saveAll(data).then(res => {
                              if (res != null && res.code === 200) {
                                this.$message({
                                  message: '保存成功！',
                                  type: 'success',
                                  center: true,
                                  showClose: true
                                })
                                this.ennameFlag = this.supplier.enname
                                this.chnameFlag = this.supplier.chname
                                this.supplier.serialNo = res.data.serialNo
                              } else {
                                this.$message.error(
                                  "保存提交失败!"
                                );
                              }
                            })
                          } else {
                            return this.$message.warning("存在相同的账户号！");
                          }
                        }
                      } else {
                        return this.$message.warning("请填写完必要信息！");
                      }
                    })
                  } else {
                    return this.$message.warning("请填写完必要信息！");
                  }
                })
              } else {
                return this.$message.warning("请填写完必要信息！");
              }
            })
          } else {
            return this.$message.warning("请填写完必要信息！");
          }
        })


      }
      ,
      closeAll() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.go(-1)
      }
      ,
      addService() {
        this.dialogFormVisible = true
        let params = {
          pageNum: 1,
          pageSize: 10,
          supplierCode: this.supplier.serialNo,
        }
        getService(params).then(res => {
          if (res != null && res.code === 200) {
            this.serviceTableData = res.rows
            this.totalCount = res.total
          }
        }).catch(() => {

        })
      },
      saveInfo() {
        this.$refs.supplier.validate((valid) => {
          if (valid) {
            let data = {
              serialNo: this.supplier.serialNo, // 供应商编码
              servcomType: this.supplier.servcomType, // 供应商类型
              bussinessStatus: this.supplier.bussinessStatus, // 状态，是否有效
              chname: this.supplier.chname, // 中文名
              enname: this.supplier.enname, // 英文名
              continent: this.supplier.continent,//洲
              country: this.supplier.country,//国家
              province: this.supplier.province, // 省
              city: this.supplier.city, // 市
              district: this.supplier.district, // 区
              detailedAddress: this.supplier.detailedAddress, // 地址
              remark: this.supplier.remark, // 备注
            }
            addInfo(data).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.ennameFlag = this.supplier.enname
                this.chnameFlag = this.supplier.chname
                this.supplier.serialNo = res.data.serialNo
              } else {
                this.$message.error(
                  "保存提交失败!"
                );
              }
            })
          }else {
            return this.$message.warning("请填写完必要信息！");
          }
        })
      }
    }
    ,
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  .baseInfo_class .el-tag--small {
    margin-right: 10px !important;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .box-card {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
  }

  .supplier {
    margin-bottom: 33px;
  }

  .long-input ::v-deep .el-form-item__content {
    width: calc(100% - 150px);
  }


  .long-select {
    width: 100%;
  }

  .el-tb-edit .el-form-item {
    display: none;
    width: 100%;
  }

  .el-tb-edit .current-row .el-form-item {
    display: inherit;
  }

  .el-tb-edit .current-row .el-form-item + span {
    display: none;
  }

  .el-form-item ::v-deep label {
    font-weight: normal;
  }

</style>

