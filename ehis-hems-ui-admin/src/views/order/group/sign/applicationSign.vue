<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">
      <!-- card1 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-data mr5"></i><span>申请书信息</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="申请书编码">
                <el-input disabled placeholder="申请书编码自动生成" clearable v-model="form.personalorderno"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="业务员姓名">
                <el-select v-model="form.salesmanno" clearable placeholder="请选择业务员姓名">
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
                <el-date-picker clearable v-model="form.applicationdate"
                                type="date" value-format="yyyy-MM-dd" placeholder="选择申请日期">
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
              <el-form-item label="是否寄送实体卡">
                <el-select v-model="form.appname"
                           clearable
                           placeholder="请选择是否寄送实体卡">
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
              <el-form-item label="关联方代码" prop="relatedcode">
                <el-select v-model="form.relatedcode"
                           clearable
                           placeholder="请选择关联方代码">
                  <el-option
                    v-for="dict in relaCode"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="纳税人类型" prop="taxpayertype">
                <el-select v-model="form.taxpayertype"
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

          </el-row>

          <!-- 第四行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="发票类型" prop="invoicetype">
                <el-select v-model="form.invoicetype"
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
        </el-card>
      </el-row>

      <!-- card2 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-custom mr5"></i><span>购买人信息</span>
          </div>
          <!-- 第五行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="购买人姓名" prop="buyName">
                <el-input placeholder="请输入购买人姓名" v-model="form.buyName" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="购买人性别" prop="buySex">
                <el-select v-model="form.buySex"
                           clearable
                           placeholder="请选择购买人性别">
                  <el-option
                    v-for="dict in buySex"
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
          </el-row>

          <!-- 第六行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="证件号码" prop="buyId">
                <el-input placeholder="请输入证件号码" v-model="form.buyId" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="出生日期">
                <el-input placeholder="请输入出生日期" v-model="form.buyBirth" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="手机" prop="buyPhone">
                <el-input placeholder="请输入手机号码" v-model="form.buyPhone" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第七行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="E-mail" prop="buyEmail">
                <el-input placeholder="请输入E-mail" v-model="form.buyEmail" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="邮编" prop="buyPostCode">
                <el-input placeholder="请输入邮编" v-model="form.buyPostCode" maxlength="6" show-word-limit
                          clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第八行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="通讯地址" prop="buyAddress">
                <el-input type="textarea" placeholder="请输入通讯地址" v-model="form.buyAddress"/>
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
          <!-- 第九行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="收费方式" prop="feeType">
                <el-select v-model="form.feeType" clearable placeholder="请选择收费方式" >
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
              <el-form-item label="银行账户名" prop="bankAccountName">
                <el-input placeholder="请输入银行账户名" v-model="form.bankAccountName" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="银行名称">
                <el-select v-model="form.bankName"
                           clearable
                           placeholder="请选择银行名称">
                  <el-option
                    v-for="dict in backName"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>

          </el-row>

          <!-- 第十行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="银行区域" prop="bankPlaceArea">
                <el-select v-model="form.bankPlaceArea" clearable placeholder="请选择银行区域">
                  <el-option
                    v-for="dict in bankPlaceArea"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="银行账号">
                <el-input placeholder="请输入银行账号" v-model="form.bankAccount" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="银行地址">
                <el-input placeholder="请输入银行地址" v-model="form.bankAddress" clearable/>
              </el-form-item>
            </el-col>

          </el-row>

          <!-- 第十一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="发票模式" prop="feePattern">
                <el-select v-model="form.feePattern" clearable placeholder="请选择发票模式">
                  <el-option
                    v-for="dict in feePattern"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
              <el-form-item label="是否代收平台" prop="replacePlatform">
                <el-select v-model="form.replacePlatform"
                           clearable
                           placeholder="请选择是否代收平台">
                  <el-option
                    v-for="dict in replacePlatform"
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

      <!-- card4 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-menu mr5"></i><span>产品包选择</span>
          </div>

          <!-- 表格区域 -->
          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="网点编码"
              width="200">
              <template slot-scope="scope">{{ scope.row.netCode }}</template>
            </el-table-column>
            <el-table-column
              label="网点名称"
              width="200" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netName }}</template>
            </el-table-column>
            <el-table-column
              label="联系人"
              width="200" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMan }}</template>
            </el-table-column>

            <el-table-column
              label="联系电话"
              width="200">
              <template slot-scope="scope">{{ scope.row.netLinkPhone }}</template>
            </el-table-column>

            <el-table-column
              label="联系邮箱"
              width="200">
              <template slot-scope="scope">{{ scope.row.netLinkMail }}</template>
            </el-table-column>

            <el-table-column
              label="工作时间"
              show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
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
            <i class="el-icon-tickets mr5"></i><span>产品包信息</span>
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
                <el-input clearable disabled/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click="submitForm"
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
            <i class="el-icon-s-operation mr5"></i><span>服务产品选择</span>
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
                @click="submitForm"
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
                <el-select v-model="form.feeType" clearable placeholder="请选择销售平台" >
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

          <el-row class="mt10 mb10" style="text-align: right;">
            <el-col class="mt10 mb10">
              <el-button
                type="primary"
                icon="el-icon-s-claim"
                size="mini"
                @click="submitForm"
                v-hasPermi="['provider:provider:add']"
              >保存
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <!-- card8 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-user-solid mr5"></i><span>使用人信息列表</span>
          </div>


          <!-- 表格区域 -->
          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">

            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="使用人编码" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netCode }}</template>
            </el-table-column>
            <el-table-column
              label="个单号" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netName }}</template>
            </el-table-column>
            <el-table-column
              label="服务卡片" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMan }}</template>
            </el-table-column>

            <el-table-column
              label="客户号" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkPhone }}</template>
            </el-table-column>

            <el-table-column
              label="服务起期" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMail }}</template>
            </el-table-column>

            <el-table-column
              label="姓名" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="手机" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="证件类型" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="证件号码" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="性别" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>

            <el-table-column
              label="出生日期" show-overflow-tooltip>
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
                @click="submitForm"
                v-hasPermi="['provider:provider:add']"
              >申请书签发
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
          invoicetype: [
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
            this.msgSuccess("签发成功");
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          };
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
        this.$router.push({path: '/order/group/sign'});
      },
    },
  }
</script>

<style scoped>

</style>
