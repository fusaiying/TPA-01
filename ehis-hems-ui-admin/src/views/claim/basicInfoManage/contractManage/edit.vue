<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>基本信息</span>
      </div>
      <div class="el-radio">
        <el-form ref="RadioForm" v-if="RadioFormShow" :model="RadioForm" style="margin-top:10px" label-width="135px">
          <el-form-item label="新增合约：" size="mini" class='form-label'>
            <div>
              <el-radio-group v-model="radioV" @change="changeRadio">
                <el-radio class="radio" v-model="addType" label="0">供应商</el-radio>
                <el-radio class="radio" v-model="addType" label="1">服务机构</el-radio>
              </el-radio-group>
            </div>
          </el-form-item>
        </el-form>
      </div>

      <el-form ref="searchForm" :model="searchForm" v-if="formTab" style="padding-bottom: 30px;margin-top:20px"
               label-width="130px" :rules="rules"
               label-position="right" size="mini">
        <el-row>
          <el-form-item label="conSerId：" v-if="false" prop="conSerId" key="hospContractCode1">
            <el-input disabled v-model="searchForm.conSerId" class="item-width" clearable size="mini"
                      placeholder=""/>
          </el-form-item>

          <el-col :span="8">
            <el-form-item label="合约名称：" prop="contractName" key="contractName11">
              <el-input maxlength="50" v-model="searchForm.contractName" class="item-width" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="供应商选择：" prop="servcomNo"> <!-- supplierInfoSelects -->
              <el-select v-model="searchForm.servcomNo" filterable class="item-width" placeholder="请选择"
                         @change="typeChange">
                <el-option v-for="dict in supplierInfoSelects" :key="dict.dictValue" :label="dict.dictLabel"
                           :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约编码：" prop="contractNo" key="contractNo">
              <el-input disabled v-model="searchForm.contractNo" class="item-width" clearable size="mini"
                        placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="合约类型" prop="contractType" key="contractType1">
              <el-select v-model="searchForm.contractType" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in supplierContractTypes" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约期限类型：" prop="contracttermType">
              <el-select v-model="searchForm.contracttermType" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in contractLimitTypes" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约分类：" prop="contractsort">
              <el-select v-model="searchForm.contractsort" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in contractCategorys" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="合约有效期：" prop="effectiveSDate" key="effectiveSDate1">
              <el-date-picker
                v-model="searchForm.effectiveSDate"
                class="item-width" size="mini"
                type="daterange"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="提前协商天数：" prop="contractadvance">
              <el-input maxlength="4" oninput="value=value.replace(/[^\d]/g,'')" :disabled="isShow"
                        v-model="searchForm.contractadvance" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus" key="bussinessStatus1">
              <el-select v-model="searchForm.bussinessStatus" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in statusSlects" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="23">
            <el-form-item :style="{width:'100%'}" :class="['long-input']" prop="remark" maxlength="2000" label="备注：">
              <el-input maxlength="2000" type="textarea" :rows="5" v-model="searchForm.remark" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <!--保存 关闭 start-->
        <el-row>
          <el-col :span="6" :offset="20">
            <el-button type="primary" size="mini" @click="saveBaseInfo">保存</el-button>
            <el-button type="primary" size="mini" @click="goBack">关闭</el-button>
          </el-col>
        </el-row>
        <!--保存 关闭 end-->

      </el-form>

      <el-form ref="providerForm" :model="providerForm" v-if="!formTab" style="padding-bottom: 30px;margin-top:20px"
               label-width="150px" :rules="providerRules"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8" v-if="false">
            <el-form-item label="hospContractCode：" prop="hospContractCode" key="hospContractCode1">
              <el-input disabled v-model="providerForm.hospContractCode" class="item-width" clearable size="mini"
                        placeholder=""/>
            </el-form-item>

            <el-form-item label="serialNo：" prop="serialNo" key="serialNo1">
              <el-input disabled v-model="providerForm.serialNo" class="item-width" clearable size="mini"
                        placeholder=""/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约编码：  " prop="contractNo" key="contractNo2">
              <!-- oninput = "value=value.replace(/[^\w\.\/]/ig,'')" -->
              <el-input disabled="disabled" maxlength="50" v-model="providerForm.contractNo" class="item-width"
                        clearable size="mini"
                        placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约名称：" prop="contractName" key="contractNameff">
              <!-- :class="['long-input']" maxlength="50" -->
              <el-input disabled v-model="providerForm.contractName" class="item-width" clearable size="mini"
                        placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="机构类别：" prop="providerType">
              <el-select :disabled="onlyAddPro" v-model="providerForm.providerType" class="item-width" size="mini"
                         placeholder="请选择" @change="proTypeChange">
                <el-option label="医院" value="01"/>
                <el-option label="其他" value="02"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="服务机构名称：" prop="providerCode" key="providerCode22">
              <el-select filterable :disabled="onlyAddPro" v-model="providerForm.providerCode" class="item-width"
                         placeholder="请选择" @change="typeServerChange">
                <el-option v-for="option in providerInfoSelects" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="合约甲方：" prop="contractPartyA">
              <el-input maxlength="100" :disabled="isShow" v-model="providerForm.contractPartyA" class="item-width"
                        clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约乙方：" prop="contractPartyB" key="contractPartyB111">
              <el-input maxlength="100" :disabled="isShow" v-model="providerForm.contractPartyB" class="item-width"
                        clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约丙方：" prop="contractPartyC">
              <el-input maxlength="100" :disabled="isShow" v-model="providerForm.contractPartyC" class="item-width"
                        clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="合约类型：" prop="contractType" key="contractType2">
              <el-select v-model="providerForm.contractType" class="item-width" size="mini" placeholder="请选择"
                         @change="contractTypeChange">
                <el-option v-for="option in contractTypes" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="关联合约：" prop="connectedContract">
              <el-select filterable :disabled="connectedContractDis ? 'disabled':false"
                         v-model="providerForm.connectedContract" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in historyContracts" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="押金金额：" prop="deposit">
              <el-input maxlength="14" :disabled="isShow" v-model="providerForm.deposit" class="item-width"
                        @input="changePrice('deposit')" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="合约有效期：" prop="cvaliDate" key="cvaliDate">
              <el-date-picker
                key="ff11"
                v-model="providerForm.cvaliDate"
                class="item-width" size="mini"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="开始日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约截止日期" prop="endDate" key="endDate">
              <el-date-picker
                key="ff"
                v-model="providerForm.endDate"
                class="item-width" size="mini"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态：" prop="bussinessStatus">
              <el-select v-model="providerForm.bussinessStatus" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in statusSlects" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="20">
            <el-form-item prop="ads" key="ads">
              <el-checkbox-group v-model="providerForm.ads" @change="changeAds">
                <el-checkbox label="01">自动续约</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="合约控费：" prop="contractControlFlagt">
              <el-checkbox-group v-model="providerForm.contractControlFlagt" @change="changeFlagType">
                <el-checkbox key="01" value="01" label="01">折扣</el-checkbox>
                <el-checkbox key="02" value="02" label="02">次均</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>

          <el-col :span="16" v-if="distcoteItem">
            <el-form-item label="诊疗费折扣：" prop="treatmentDiscount">
              <!-- oninput = "value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"  @input="changePrice('treatmentDiscount')" -->
              <el-input maxlength="4" @input="changePrice('treatmentDiscount')" :disabled="isShow"
                        v-model="providerForm.treatmentDiscount" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8" v-if="countItem">
            <el-form-item label="次均控费：" prop="averageCost">
              <el-input maxlength="14" @input="changePrice('averageCost')" :disabled="isShow"
                        v-model="providerForm.averageCost" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16" v-if="distcoteItem">
            <el-form-item label="检查费折扣：" prop="examineDiscount">
              <el-input maxlength="4" @input="changePrice('examineDiscount')" :disabled="isShow"
                        v-model="providerForm.examineDiscount" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8" v-if="countItem">
            <el-form-item label="次均控费类型：" prop="type">
              <el-select v-model="providerForm.type" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in onceTypes" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="16" v-if="distcoteItem">
            <el-form-item label="床位费折扣：" prop="bedDiscount">
              <el-input maxlength="4" @input="changePrice('bedDiscount')" :disabled="isShow"
                        v-model="providerForm.bedDiscount" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8" v-if="countItem">
            <el-form-item label="就诊次数：" prop="advicenum">
              <el-input maxlength="14" @input="changePrice('advicenum')" :disabled="isShow"
                        v-model="providerForm.advicenum" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16" v-if="distcoteItem">
            <el-form-item label="护理费折扣：" prop="allowance">
              <el-input maxlength="4" @input="changePrice('allowance')" :disabled="isShow"
                        v-model="providerForm.allowance" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8" v-if="countItem">
            <el-form-item label="次均控费除外项目：" prop="averageCostExcept">
              <el-input :disabled="isShow" v-model="providerForm.averageCostExcept" class="item-width" clearable
                        size="mini" maxlength="100"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16" v-if="distcoteItem">
            <el-form-item label="医药费折扣：" prop="costs">
              <el-input maxlength="4" @input="changePrice('costs')" :disabled="isShow" v-model="providerForm.costs"
                        class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16" v-if="distcoteItem">
            <el-form-item label="折扣信息：" prop="discountinfo">
              <el-input :disabled="isShow" v-model="providerForm.discountinfo" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="9" v-if="distcoteItem">
            <el-form-item label="特殊费折扣信息：" prop="specialDiscount">
              <el-input :disabled="isShow" v-model="providerForm.specialDiscount" class="item-width" clearable
                        size="mini" maxlength="10"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="24" v-if="distcoteItem">
            <el-form-item label="折扣除外项目：" prop="project">
              <el-input :disabled="isShow" maxlength="50" v-model="providerForm.project" class="item-width" clearable
                        size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合作单位：" prop="cooperativeUnit">
              <el-select v-model="providerForm.cooperativeUnit" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in cooperativeUnitSelects" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="直结类型：" prop="straightT">
              <el-checkbox-group v-model="providerForm.straightT">
                <el-checkbox v-for="item in directContractTypes" :label="item.dictValue" :key="item.dictValue">
                  {{item.dictLabel}}
                </el-checkbox>
              </el-checkbox-group>

            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约终止日期：" prop="expiryDate">
              <el-date-picker
                v-model="providerForm.expiryDate"
                class="item-width" size="mini"
                value-format="yyyy-MM-dd"
                type="date"
                range-separator="-"
                placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="合约终止原因：" prop="reason" key="reason1">
              <el-input maxlength="2000" :disabled="isShow" v-model="providerForm.reason" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人：" prop="liaison">
              <el-input maxlength="20" :disabled="isShow" v-model="providerForm.liaison" class="item-width" clearable
                        size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="手机：" prop="phone">
              <el-input maxlength="11" oninput="value=value.replace(/[^\d]/g,'')" :disabled="isShow"
                        v-model="providerForm.phone" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="盒脊编号：" prop="boxRidgeCode">
              <el-input maxlength="100" :disabled="isShow" v-model="providerForm.boxRidgeCode" class="item-width"
                        clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="办公电话：" prop="tel">
              <el-input maxlength="100" oninput="value=value.replace(/[^\d]/g,'')" :disabled="isShow"
                        v-model="providerForm.tel" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="电子邮件：" prop="email">
              <el-input maxlength="100" :disabled="isShow" v-model="providerForm.email" class="item-width" clearable
                        size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="最后维护人：" prop="exPer">
              <el-input disabled="disabled" v-model="providerForm.exPer" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="最后维护时间：" prop="exEndate">
              <el-date-picker
                disabled="disabled"
                v-model="providerForm.exEndate"
                class="item-width" size="mini"
                value-format="yyyy-MM-dd"
                type="date"
                range-separator="-"
                placeholder="最后维护时间">
              </el-date-picker>
            </el-form-item>
          </el-col>

        </el-row>
        <!--保存 关闭 start-->
        <el-row>
          <el-col :span="6" :offset="20">
            <el-button type="primary" size="mini" @click="saveBaseInfo">保存</el-button>
            <el-button v-if="!onlyAddPro" type="primary" size="mini" @click="goBack">关闭</el-button>
            <el-button v-if="onlyAddPro" type="primary" size="mini" @click="goBackPro">关闭</el-button>
          </el-col>
        </el-row>
        <!--保存 关闭 end-->
      </el-form>
      <el-divider/>
      <!-- 历史合约信息 START-->
      <div v-if="hospPro">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>历史合约信息</span>
        </div>
        <el-table
          :data="pendingTableData" size="mini" tooltip-effect="dark"
          class="receive_table" :header-cell-style="{color:'black',background:'#f8f8ff'}">
          <el-table-column prop="contractNo" label="合约编码" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="contractName" label="合约名称" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="contractType" label="合约类型" :formatter="getContractTypeName" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="contracttermType" label="合约期限类型" :formatter="getContractLimitTypeName" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="cvaliDate" label="合约有效期" :formatter="getValiDate" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="bussinessStatus" label="状态" :formatter="getStatuTypeName" align="center"
                           show-overflow-tooltip/>
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="viewDetail(scope.row)">查看</el-button>
              <el-button v-if="onlyAddPro" size="mini" type="text" @click="update(scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="hisContractTotalNum>0"
          :total="hisContractTotalNum"
          :page.sync="hisContractPageInfo.currentPage"
          :limit.sync="hisContractPageInfo.pageSize"
          @pagination="initData"
        />
      </div>
      <!-- 历史合约信息 END-->

      <!--供应商服务项目 Start-->
      <div v-if="formTab"
           style="line-height: 50px; margin-bottom: 20px;margin-top: 50px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>供应商服务项目</span>
        <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="save">保存</el-button>
      </div>
      <el-form v-if="formTab" ref="serverForm" :rules="serverInfoRules" :model="serverForm" size="small">
        <el-table :data="serverForm.serverInfo"
                  size="mini" tooltip-effect="dark" class="receive_table"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}" style="width: 100%">

          <el-table-column prop="serialNo" label="序列号" v-if="false" style="width: 100px" align="center">
            <template slot-scope="scope">
              <template v-if="scope.row.editing">
                <el-input v-model="scope.row.serialNo" disabled style="width: 10px" size="mini"></el-input>
              </template>
              <span v-else>{{ scope.row.serialNo }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="contractNo" label="合约编码" v-if="false" style="width: 280px" align="center">
            <template slot-scope="scope">
              <template v-if="scope.row.editing">
                <el-input v-model="scope.row.contractNo" disabled style="width: 80px" size="mini"></el-input>
              </template>
              <span v-else>{{ scope.row.contractNo }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="serviceCode" label="服务项目名称" align="center">  <!-- serviceInfoSelects -->
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;" v-if="scope.row.editing"
                            :rules="serverInfoRules.serviceCode" :prop="'serverInfo.' + scope.$index + '.serviceCode'">
                <el-select filterable style="width: 150px" size="mini" v-model="scope.row.serviceCode"
                           class="el-select item-width el-select--mini" placeholder="请选择">
                  <el-option v-for="dict in serviceInfoSelects" :key="dict.dictValue" :label="dict.dictLabel"
                             :value="dict.dictValue"/>
                </el-select>
              </el-form-item>
              <template v-else slot-scope="scope">
                <span>{{getServiceName(scope.$index, scope.row)}}</span>
              </template>
            </template>
          </el-table-column>

          <el-table-column prop="supplierCode" label="供应商项目名称" align="center">
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;" v-if="scope.row.editing"
                            :rules="serverInfoRules.supplierServiceName"
                            :prop="'serverInfo.' + scope.$index + '.supplierServiceName'">
                <el-input size="mini" maxlength="50" v-model="scope.row.supplierServiceName"
                          placeholder="请输入"></el-input>
              </el-form-item>

              <template v-else slot-scope="scope">
                <span>{{(scope.row.supplierServiceName)}}</span>
              </template>
            </template>
          </el-table-column>

          <el-table-column label="服务项目价格" width="300px" align="center">
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;" :inline="true" v-if="scope.row.editing"
                            :rules="serverInfoRules.minPrice" :prop="'serverInfo.' + scope.$index + '.minPrice'">
                <el-input maxlength="14" oninput="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
                          v-model="scope.row.minPrice" placeholder="请输入" style="width: 100px;margin-left: 10px"
                          size="mini"></el-input>
              </el-form-item>
              <el-form-item style="display: inline-flex !important;" :inline="true" v-if="scope.row.editing"
                            :rules="serverInfoRules.maxPrice" :prop="'serverInfo.' + scope.$index + '.maxPrice'">
                <el-input maxlength="14" oninput="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
                          v-model="scope.row.maxPrice" placeholder="请输入" style="width: 100px;margin-left: 10px"
                          size="mini"></el-input>
              </el-form-item>
              <span v-else>{{ scope.row.minPrice }} - {{ scope.row.maxPrice }} </span>
            </template>
          </el-table-column>


          <el-table-column prop="settleType" label="结算方式" align="center">
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;" v-if="scope.row.editing"
                            :rules="serverInfoRules.settleType" :prop="'serverInfo.' + scope.$index + '.settleType'">
                <el-select style="width: 150px" size="mini" v-model="scope.row.settleType"
                           class="el-select item-width el-select--mini" placeholder="请选择">
                  <el-option
                    v-for="dict in settleTypeSelect"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
              <template v-else slot-scope="scope">
                <span>{{gettleTypeName(scope.$index, scope.row)}}</span>
              </template>
            </template>
          </el-table-column>
          <el-table-column prop="settleCurrency" label="结算币种" align="center"> <!-- currencys --><!-- getCurrencyName -->
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;" v-if="scope.row.editing"
                            :rules="serverInfoRules.settleCurrency"
                            :prop="'serverInfo.' + scope.$index + '.settleCurrency'">
                <el-select style="width: 100px" size="mini" v-model="scope.row.settleCurrency"
                           class="el-select item-width el-select--mini" placeholder="请选择">

                  <el-option
                    v-for="dict in currencys"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
              <template v-else slot-scope="scope">
                <span>{{getCurrencyName(scope.$index, scope.row)}}</span>
              </template>
            </template>
          </el-table-column>

          <el-table-column prop="limitnum" label="日限次数" style="width: 280px" align="center">
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;" v-if="scope.row.editing"
                            :rules="serverInfoRules.limitnum" :prop="'serverInfo.' + scope.$index + '.limitnum'">
                <el-input maxlength="10" size="mini" oninput="value=value.replace(/[^\d]/g,'')"
                          v-model="scope.row.limitnum" placeholder="请输入" style="width: 90px"></el-input>
              </el-form-item>
              <span v-else>{{ scope.row.limitnum }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="settleCurrency" label="操作" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.editing !== true && scope.row.editing !== 'add'" size="mini"
                         type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
              </el-button>
              <el-button v-if="scope.row.editing == true" size="mini" type="text" icon="el-icon-edit"
                         @click="handleCancle(scope.$index, scope.row)">取消
              </el-button>
              <el-button v-if="scope.row.editing !== true && scope.row.editing !== 'add'" size="mini" type="text"
                         icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
              <el-button v-if="scope.row.editing == 'add'" size="mini" type="text"
                         icon="el-icon-delete" @click="removeLine(scope.$index, scope.row)">取消
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <el-button v-if="formTab" type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addLine()"> + 添加
      </el-button>
      <!--供应商服务项目 END-->


      <!--附件信息 START-->
      <div v-if="hospPro">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>附件信息</span>
          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini" @click="diagShow">点击上传
          </el-button>
        </div>
        <el-table
          :data="fileTableData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="annexName" label="附件名称" align="center" show-overflow-tooltip/>
          <el-table-column prop="annexType" label="附件类型" align="center" show-overflow-tooltip/>
          <!-- <el-table-column prop="supplierName" label="供应商名称" align="center" show-overflow-tooltip />-->
          <el-table-column prop="uploadMan" label="上传人" align="center" show-overflow-tooltip/>
          <el-table-column prop="uploadTime" label="上传时间" align="center" show-overflow-tooltip/>
          <el-table-column prop="remak" label="备注" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="deleteFile(scope.row)">删除</el-button>
              <el-button size="mini" type="text" @click="downloadFile(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--附件信息 END-->

      <!-- 附件上传弹框 start -->
      <el-dialog
        :visible.sync="dialogVisible"
        :dialog-visible="dialogVisible"
        :append-to-body="true"
        :before-close="closeDialog"
        :close-on-click-modal="false"
        title="添加附件"
        width="40%"
        @open="openFun">
        <!--  <el-card style="border: 0;box-shadow: none; margin-top: -20px;">-->
        <!-- <span slot="footer" class="dialog-footer">
           <el-button   @click="dialogVisible = false">取 消</el-button>
         </span>-->
        <el-form ref="fileForm" :model="fileForm" style="border:0" label-width="110px" label-position="right"
                 size="mini">

          <el-row>
            <el-col :span="8">
              <el-form-item label="附件类型：" prop="fileType">
                <el-select v-model="fileForm.fileType" class="item-width" size="mini" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="6">
              <el-form-item label="附件文件：" prop="fileName">
                <el-input v-model="fileForm.fileName" disabled class="item-width" size="mini"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-upload
                ref="my-upload"
                class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/"
                accept=".jpg,.png"
                :before-remove="beforeRemove"
                :before-upload="beforeUpload"
                :on-success="success"
                list-type="text"
                :on-error="handleError"
                :show-file-list="false"
                multiple
                trigger
                style="float: right;" size="mini">
                <el-button style="float: right;" size="mini" type="primary">上传文件</el-button>
              </el-upload>
              <el-image-viewer
                v-if="showViewer"
                disabled
                :on-close.stop="closeViewer"
                :url-list="file"/>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="备注：" prop="remark">
                <el-input :disabled="isShow" style="width: 330px;" col="2" type="textarea" row="4" maxlength="1000"
                          v-model="fileForm.fileRemark" class="item-width" clearable size="mini"
                          placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="saveFileInfo" type="primary">确认</el-button>
           <el-button @click="dialogVisible = false">取 消</el-button>
          </span>
        <!-- </el-card>-->
      </el-dialog>
      <!-- 附件上传弹框 end -->

    </el-card>
  </div>
</template>

<script>
  import {
    getSupplierContractList,
    addSupplierContract,
    updateSupplierContract,
    getSupplierContractDetail,
    getContractServerList,
    getFileList,
    deleteContractServer,
    addContractServer,
    getAllBaseSupplierInfo,
    getAllBaseProviderInfo,
    getAllBackBaseProviderInfo,
    getAllBaseServiceInfo,
    getSupplierContractBakDetail,
    getSupplierContractBakList

  } from '@/api/contractManage/contractManagement'
  import {itemList} from "../../../../api/claim/handleCom";
  import {listData} from "../../../../api/system/dict/data";

  export default {
    data() {
      const checkReContractName = (rule, value, callback) => {
        if (!value) {
          callback(new Error("合约名称必填"));
        } else if (value != this.preContractName) {

          let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
          if (!regx.test(value)) {
            callback(new Error("合约名称支持录入字母数字中文"));
          }
          const param = {
            pageNum: 1,
            pageSize: 1,
            contractName: value,
          };
          if (this.onlyAddPro) {
            getSupplierContractBakDetail(param).then(response => {
              if (response.total == 1) {
                callback(new Error("合约名称已存在"));
              } else {
                callback();
              }
            }).catch(error => {
              console.log(error);
            });
          } else {
            getSupplierContractList(param).then(response => {
              if (response.total == 1) {
                callback(new Error("合约名称已存在"));
              } else {
                callback();
              }
            }).catch(error => {
              console.log(error);
            });
          }
        } else {
          callback();
        }
      }; //
      const checkMaxPrice = (rule, value, callback) => {
        if (!value) {
          callback(new Error("最大价格不可为空"));
        } else {
          let listData = this.serverForm.serverInfo;
          let index = rule.field.split(".")[1];
          let minPrice = listData[index].minPrice;
          let maxPrice = listData[index].maxPrice;
          if (minPrice != '' && maxPrice != '' && parseFloat(minPrice) > parseFloat(maxPrice)) {
            callback(new Error("价格区间错误"));
          } else {
            callback();
          }
        }
      };
      const checkReSerCode = (rule, value, callback) => {
        if (!value) {
          callback(new Error("服务项目不可为空"));
        } else {
          let listData = this.serverForm.serverInfo;
          let count = 0;
          for (let i = 0; i < listData.length; i++) {
            if (listData[i].serviceCode == value) {
              count++;
            }
          }
          if (count > 1) {
            callback(new Error("服务项目名称重复"));
          } else {
            callback();
          }
        }
      };
      const checkSettleCurrency = (rule, value, callback) => {
        if (!value) {
          callback(new Error("结算币种不可为空"));
        } else {
          let listData = this.serverForm.serverInfo;
          let count = 0;
          for (let i = 0; i < listData.length; i++) {
            if (listData[i].settleCurrency != value) {
              count++;
            }
          }
          if (count > 1) {
            callback(new Error("请选择相同币种"));
          } else {
            callback();
          }
        }
      };
      const checkReSupplierCode = (rule, value, callback) => {
        if (!value) {
          callback(new Error("供应商项目名称不可为空"));
        } else {
          let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
          if (!regx.test(value)) {
            callback(new Error("支持录入字母数字中文"));
          }
          let listData = this.serverForm.serverInfo;
          let count = 0;
          for (let i = 0; i < listData.length; i++) {
            if (listData[i].supplierServiceName == value) {
              count++;
            }
          }
          if (count > 1) {
            callback(new Error("供应商项目名称重复"));
          } else {
            callback();
          }
        }
      };
      const checkcontractPartyB = (rule, value, callback) => {
        if (!value) {
          callback(new Error("合约乙方必填"));
        } else if (value != this.preContractPartyB) {
          let reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]");
          if (reg.test(value)) {
            callback(new Error("合约乙方禁止录入特殊字符"));
          }

          const param = {
            pageNum: 1,
            pageSize: 1,
            contractPartyB: value,
          };
          if (this.onlyAddPro) {
            getSupplierContractBakDetail(param).then(response => {
              if (response.total == 1) {
                callback(new Error("合约乙方已经存在"));
              } else {
                callback();
              }
            }).catch(error => {
              console.log(error);
            });
          } else {
            getSupplierContractList(param).then(response => {
              if (response.total == 1) {
                callback(new Error("合约乙方已经存在"));
              } else {
                callback();
              }
            }).catch(error => {
              console.log(error);
            });
          }
        } else {
          callback();
        }
      };
      const checkcontractPartyA = (rule, value, callback) => {
        if (!value) {
          callback();
        } else if (value != this.preContractPartyA) {
          let reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]");
          if (reg.test(value)) {
            callback(new Error("合约甲方禁止录入特殊字符"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      };

      const checkcontractPartyC = (rule, value, callback) => {
        if (!value) {
          callback();
        } else if (value != this.preContractPartyC) {

          // let reg = /^[\u4e00-\u9fa5]+$/
          // if (!reg.test(value)) {
          //   callback(new Error("合约乙方仅支持录入中文"));
          // }
          let reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]");
          if (reg.test(value)) {
            callback(new Error("合约丙方禁止录入特殊字符"));
          }

          const param = {
            pageNum: 1,
            pageSize: 1,
            contractPartyB: value,
          };
          if (this.onlyAddPro) {
            getSupplierContractBakDetail(param).then(response => {
              if (response.total == 1) {
                callback(new Error("合约丙方已经存在"));
              } else {
                callback();
              }
            }).catch(error => {
              console.log(error);
            });
          } else {
            getSupplierContractList(param).then(response => {
              if (response.total == 1) {
                callback(new Error("合约丙方已经存在"));
              } else {
                callback();
              }
            }).catch(error => {
              console.log(error);
            });
          }
        } else {
          callback();
        }
      };

      const checkNum = (rule, value, callback) => {
        const regx = /^[1-9]{1}[\d]*$/
        if (!value) {
          callback(new Error("协商天数额必填"));
        } else {
          if (!regx.test(value)) {
            callback(new Error("协商天数不为正整数，请检查"));
          } else {
            callback();
          }
        }
      };
      const checkTreatmentDiscount = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("诊疗费折扣必填"));
          } else {
            if (parseFloat(value) > 1) {
              callback(new Error("诊疗费折扣介于 0 - 1 之间"));
            } else {
              callback();
            }
          }
        } else {
          callback();
        }
      };
      const checkExamineDiscount = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("检查费折扣必填"));
          } else {
            // let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
            // if (!regx.test(value)) {
            //   callback(new Error("检查费折扣支持录入字母数字中文"));
            // } else {
            //   callback();
            // }
            if (parseFloat(value) > 1) {
              callback(new Error("检查费折扣介于 0 - 1 之间"));
            } else {
              callback();
            }
          }
        } else {
          callback();
        }
      };
      const checkBedDiscount = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("床位费折扣必填"));
          } else {
            if (parseFloat(value) > 1) {
              callback(new Error("床位费折扣介于 0 - 1 之间"));
            } else {
              callback();
            }
          }
        } else {
          callback();
        }
      };
      const checkAllowance = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("护理费折扣必填"));
          } else {
            if (parseFloat(value) > 1) {
              callback(new Error("护理费折扣折扣介于 0 - 1 之间"));
            } else {
              callback();
            }
          }
        } else {
          callback();
        }
      };
      const checkCosts = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("医药费折扣必填"));
          } else {
            if (parseFloat(value) > 1) {
              callback(new Error("医药费折扣折扣介于 0 - 1 之间"));
            } else {
              callback();
            }
          }
        } else {
          callback();
        }
      };
      const cheDiscountinfo = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("折扣信息必填"));
          } else {
            // let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
            // if (!regx.test(value)) {
            //   callback(new Error("折扣信息支持录入字母数字中文"));
            // } else {
            //   callback();
            // }
            callback();
          }
        } else {
          callback();
        }
      };
      const chesecialDiscount = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("特殊费折扣信息必填"));
          } else {
            // let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
            // if (!regx.test(value)) {
            //   callback(new Error("特殊费折扣信息支持录入字母数字中文"));
            // } else {
            //   callback();
            // }
            callback();
          }
        } else {
          callback();
        }
      };
      const cheProject = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("折扣除外项目必填"));
          } else {
            // let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
            // if (!regx.test(value)) {
            //   callback(new Error("折扣除外项目支持录入字母数字中文"));
            // } else {
            //   callback();
            // }
            callback();
          }
        } else {
          callback();
        }
      };
      const cheAverageCostExcept = (rule, value, callback) => {
        if (this.distcoteItem) {
          if (!value) {
            callback(new Error("次均控费除外项目必填"));
          } else {
            // let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
            // if (!regx.test(value)) {
            //   callback(new Error("次均控费除外项目支持录入字母数字中文"));
            // } else {
            //   callback();
            // }
            callback();
          }
        } else {
          callback();
        }
      };
      const checkAverageCost = (rule, value, callback) => {
        if (this.countItem) {
          if (!value) {
            callback(new Error("次均控费必填"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      const checkaDvicenum = (rule, value, callback) => {
        if (this.countItem) {
          if (!value) {
            callback(new Error("就诊次数必填"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      const chectype = (rule, value, callback) => {
        if (this.countItem) {
          if (!value) {
            callback(new Error("就诊次数必填"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      const checkboxRidgeCode = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          if ('' != value) {
            let regx = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;
            if (!regx.test(value)) {
              callback(new Error("盒脊编号支持录入字母数字中文"));
            } else {
              callback();
            }
          }
        }
      };
      const checkEmail = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          if ('' != value) {
            let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            if (!reg.test(value.toLocaleLowerCase())) {
              callback(new Error("电子邮件格式不正确"));
            } else {
              callback();
            }
          }
        }
      };
      const checkPhone = (rule, value, callback) => {
        if (!value) {
          callback();
        } else {
          if ('' != value) {
            if (value.trim().length != 11) {
              callback(new Error("手机号格式不正确"));
            } else {
              callback();
            }
          }
        }
      };
      const checkExistInfo = (rule, value, callback) => {
        if (this.pageOpe == 'add' && !this.formTab) {
          if (!value) {
            callback(new Error("合约编码必填"));
          } else {
            getSupplierContractDetail(value).then(response => {
              if (response.code == '200' && response.data != undefined) {
                callback(new Error("合约编码已经存在"));
              } else if (response.code != '200') {
                callback(new Error("合约编码重复验证错误"));
              } else {
                callback();
              }
            }).catch(error => {
              callback(new Error("合约编码重复验证错误"));
              console.log(error);
            });
          }
        } else {
          callback();
        }
      };
      const checkEndDate = (rule, value, callback) => {
        if (!this.adsFlag) {
          if (!value) {
            callback(new Error("合约有效期结束日期必填"));
          } else {
            if (this.providerForm.cvaliDate != '') {
              let startTime = Date.parse(this.providerForm.cvaliDate);
              let endTime = Date.parse(value);
              if (startTime > endTime) {
                callback(new Error("合约有效期结束日期错误"));
              } else {
                callback();
              }
            }
          }
        } else {
          if (value) {
            if (this.providerForm.cvaliDate != '') {
              let startTime = Date.parse(this.providerForm.cvaliDate);
              let endTime = Date.parse(value);
              if (startTime > endTime) {
                callback(new Error("合约有效期结束日期错误"));
              } else {
                callback();
              }
            }
          } else {
            callback();
          }
        }
      };
      const checkExpiryReason = (rule, value, callback) => {
        if (this.providerForm.expiryDate != '' && this.providerForm.expiryDate != null) {
          if (!value) {
            callback(new Error("合约终止原因必填"));
          } else {
            callback();
          }
        } else {
          callback();
        }
      };
      return {
        hisContractTotalNum: 0,
        hisContractPageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes: [5, 10, 20, 30, 50, 100]
        },
        provideConView: false,
        baseFomrmSub: false,
        pageOpe: '',
        queryContractNo: '',
        contractCategorys: [],
        contractLimitTypes: [],
        contractTypes: [],
        statusSlects: [],
        showViewer: false,
        isShow: false,
        effectiveSDate: '',
        pendingTotalNum: 0,
        fileTotalNum: 0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        radioV: '0',
        addType: '0',
        pendingPageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes: [5, 10, 20, 30, 50, 100]
        },
        fileTablePageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes: [5, 10, 20, 30, 50, 100]
        },
        rules: {
          servcomNo: {trigger: ['change'], required: true, message: '供应商必填'},
          contractName: {trigger: ['change', 'blur'], validator: checkReContractName, required: true},
          contractType: {trigger: ['change'], required: true, message: '合约类型必填'},
          contracttermType: {trigger: ['change'], required: true, message: '合约期限类型必填'},
          contractsort: {trigger: 'change', required: true, message: '合约分类必填'},
          bussinessStatus: {trigger: 'change', required: true, message: '状态必填'},
          effectiveSDate: {trigger: ['change'], required: true, message: '合约有效期'},
        },

        /*  validator: checkExistInfo, */
        providerRules: {
          providerCode: {trigger: ['change', 'blur'], required: true, message: '服务机构必填'},
          providerType: {trigger: ['change', 'blur'], required: true, message: '机构类别必填'},
          bussinessStatus: {trigger: 'change', required: true, message: '状态必填'},
          //contractName: {trigger: ['change','blur'],validator: checkReContractName, required: true},
          contractType: {trigger: ['change'], required: true, message: '合约类型必填'},
          cvaliDate: {trigger: ['change', 'blur'], required: true, message: '合约有效期必填'},
          endDate: {trigger: ['change', 'blur'], validator: checkEndDate, required: false},
          contractPartyA: {trigger: ['change', 'blur'], validator: checkcontractPartyA, required: false},
          contractPartyB: {trigger: ['change', 'blur'], validator: checkcontractPartyB, required: true},
          contractPartyC: {trigger: ['change', 'blur'], validator: checkcontractPartyC, required: false},
          phone: {trigger: ['change', 'blur'], validator: checkPhone, required: false},
          boxRidgeCode: {trigger: ['change', 'blur'], validator: checkboxRidgeCode, required: false},
          email: {trigger: ['change', 'blur'], validator: checkEmail, required: false},
          reason: {trigger: ['change', 'blur'], validator: checkExpiryReason},
          treatmentDiscount: [{validator: checkTreatmentDiscount, required: true, trigger: ['change', 'blur']}],
          examineDiscount: [{validator: checkExamineDiscount, required: true, trigger: ['change', 'blur']}],
          bedDiscount: [{validator: checkBedDiscount, required: true, trigger: ['change', 'blur']}],
          allowance: [{validator: checkAllowance, required: true, trigger: ['change', 'blur']}],
          costs: [{validator: checkCosts, required: true, trigger: ['change', 'blur']}],
          discountinfo: [{validator: cheDiscountinfo, required: true, trigger: 'blur'}],
          specialDiscount: [{validator: chesecialDiscount, required: true, trigger: 'blur'}],
          project: [{validator: cheProject, required: true, trigger: 'blur'}],
          averageCostExcept: [{validator: cheAverageCostExcept, required: true, trigger: 'blur'}],
          averageCost: [{validator: checkAverageCost, required: true, trigger: ['change', 'blur']}],
          advicenum: [{validator: checkaDvicenum, required: true, trigger: ['change', 'blur']}], //就诊次数
          type: {trigger: ['change', 'blur'], required: true, message: '次均控费类型必填'},
        },
        serverInfoRules: {
          serviceCode: [{required: true, validator:checkReSerCode, trigger:['change', 'blur']}],
          supplierServiceName: [{required: true, validator: checkReSupplierCode, trigger: ['change', 'blur']}],
          minPrice: [{required: true, message: '最小价格', trigger: ['change', 'blur']}],
          maxPrice: [{required: true, validator: checkMaxPrice, trigger: ['change', 'blur']}],
          limitnum: [{required: true, message: '日限次数', trigger: ['change', 'blur']}],
          settleType: [{required: true, message: '结算方式不可为空', trigger: ['change', 'blur']}],
          settleCurrency: [{required: true, validator:checkSettleCurrency, trigger: ['change', 'blur']}],
        },
        dialogVisible: false,
        dateRange: '',
        afterTableTotal: 0,
        page: 1,
        total: 0,
        finishTotal: 0,
        pageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        pageSizes: [5, 10, 20, 30, 50, 100],
        activeName: '01',
        changeSerchData: {},
        suTableData: [],
        afterTable: [],
        tableData: [],
        modifyData: [],
        prevValue: {},
        pendingTableData: [],
        fileTableData: [],
        searchForm: {
          contractNo: '',
          servcomNo: '',
          contractName: '',
          contractType: '',
          contracttermType: '',
          cvaliDate: '',
          contractadvance: '',
          bussinessStatus: '01',
          remark: '',
          contractsort: '',
          effectiveSDate: '',
          // signDate:'',
          conSerId: '',
          supplierServiceName: '',
        },
        providerForm: {
          serialNo: '',
          networkHospitalType: [],
          straightT: [],
          ads: ['01'],
          contractControlFlagt: [],
          //straight:'',
          providerCode: '',
          contractNo: '',
          bussinessStatus: '01',
          contractName: '',
          contractPartyA: '平安健康保险股份有限公司',
          contractPartyB: '',
          contractPartyC: '',
          connectedContract: '',
          contractType: '',
          deposit: '',
          effectiveSDate: '',
          treatmentDiscount: '',
          examineDiscount: '',
          averageCost: '',
          bedDiscount: '',
          type: '',
          allowance: '',
          advicenum: '',
          costs: '',
          averageCostExcept: '',
          discountinfo: '',
          specialDiscount: '',
          project: '',
          cooperativeUnit: '',
          endDate: '9999-12-31',
          reason: '',
          liaison: '',
          phone: '',
          boxRidgeCode: '',
          tel: '',
          email: '',
          exPer: '',
          exEndate: '',
          hospContractCode: '01',
          cvaliDate: '',
          expiryDate: '',
          providerType: '',
        },
        RadioForm: {},
        fileUpload: false,
        fileForm: {
          fileType: '',
          fileName: '',
          fileRemark: ''
        },
        serverForm: {
          serverInfo: []
        },
        formTab: true,
        RadioFormShow: true,
        supplierInfoSelects: [],
        providerInfoSelects: [],
        providerCode1: [],
        providerCode2: [],
        cooperativeUnitSelects: [],
        serviceInfoSelects: [],
        currencys: [],
        settleTypeSelect: [],
        historyContracts: [],
        distcoteItem: false,
        countItem: false,
        adsFlag: true,
        onlyAddPro: false,
        serverInfoSave: false,
        preContractPartyA: '',
        preContractPartyB: '',
        preContractPartyC: '',
        preContractName: '',
        conSerId: '',
        onceTypes: [],
        directContractTypes: [],
        supplierContractTypes: [],
        fsSub: true,
        fpSub: true,
        hospPro: true,
        connectedContractDis: false,
      }
    },
    created() {
      this.initData();
    },
    mounted() {
      this.getDicts("supplier_contract_type").then(response => {
        this.supplierContractTypes = response.data;
      });
      this.getDicts("direct_contract_type").then(response => {
        this.directContractTypes = response.data;
      });
      this.getDicts("type").then(response => {
        this.onceTypes = response.data;
      });
      this.getDicts("cooperator").then(response => {
        this.cooperativeUnitSelects = response.data;
      });
      this.getDicts("contract_limit_type").then(response => {
        this.contractLimitTypes = response.data;
      });
      this.getDicts("currency").then(response => {
        this.currencys = response.data;
      });
      this.getDicts("clearing_form").then(response => {
        this.settleTypeSelect = response.data;
      });
      this.getDicts("contract_type").then(response => {
        this.contractTypes = response.data;
      });
      this.getDicts("contract_category").then(response => {
        this.contractCategorys = response.data;
      });
      this.getDicts("state").then(response => {
        this.statusSlects = response.data;
      });

      //获取供应商下拉
      this.allBaseSupplierInfo();
      //获取服务机构下拉
      this.allBaseProviderInfo();
      //服务项目下拉
      this.allAllBaseServiceInfo();
      //更新操作,隐藏切换form表单,回显数据
      if (this.$route.query.status == 'update') {
        this.RadioFormShow = false;
        if (this.$route.query.flag == '02') {
          this.formTab = false;
          this.provideConView = true;
        }
        this.baseFomrmSub = true;
        this.pageOpe = 'update';
        this.queryContractNo = this.$route.query.contractNo;
        //详情
        this.getDetail(this.queryContractNo);
        //供应商服务项目
        this.getData();
        //附件信息
        // this.getfileTableData();
      } else {
        this.pageOpe = 'add';
        this.conSerId = this.getRandom(15);
        this.searchForm.conSerId = this.conSerId;

        /* 服务机构管理新增或更新  start */
        if (this.$route.query.flag == 'add' || this.$route.query.flag == 'update') {
          this.onlyAddPro = true;
          this.RadioFormShow = false;
          this.formTab = false;
          this.providerForm.providerCode = this.$route.query.providerCode;
          // this.providerForm.contractNo = this.$route.query.contractNo;
          // this.$route.query.status
          this.providerForm.providerType = this.$route.query.orgflag;

          //如果是更新反显数据
          if (this.$route.query.flag == 'update') {
            this.pageOpe = 'update';
            // this.getBakDetail(this.$route.query.providerCode);
          }
          if (this.$route.query.providerCode != '') {
            if (this.$route.query.contractNo != '' && this.$route.query.contractNo != undefined) {
              this.hospPro = false;
              this.getBakDetail();
            } else {
              this.getSupplierContractListByChangeType(2);
              this.pageOpe = 'add';
            }
          }
        }
        /* 服务机构管理新增或更新  end */
      }
    },
    watch: {
      '$route'(to, from) {
        this.hospPro = true;
        /* 服务机构管理新增或更新  start */
        if (this.$route.query.flag == 'add' || this.$route.query.flag == 'update') {
          this.onlyAddPro = true;
          this.RadioFormShow = false;
          this.formTab = false;
          this.providerForm.providerCode = this.$route.query.providerCode;
          // this.providerForm.contractNo = this.$route.query.contractNo;

          //如果是更新反显数据
          if (this.$route.query.flag == 'update') {
            this.pageOpe = 'update';
            // this.getBakDetail(this.$route.query.providerCode);
          }
          if (this.$route.query.providerCode != '') {
            if (this.$route.query.contractNo != '' && this.$route.query.contractNo != undefined) {
              this.hospPro = false;
              this.getBakDetail();
            } else {
              this.getSupplierContractListByChangeType(2)
              this.pageOpe = 'add';
            }
          }
        }
      }

    },
    methods: {
      initData() {
        let type = this.formTab ? 1 : 2;
        if (type == 1) {
          if (this.searchForm.servcomNo == '') {
            return false;
          }
        } else {
          if (this.providerForm.providerCode == '') {
            return false;
          }
        }
        this.getSupplierContractListByChangeType(type);
      },
      contractTypeChange(value) {
        if (value == '04') {
          this.connectedContractDis = true;
          this.providerForm.connectedContract = '';
        } else {
          this.connectedContractDis = false;
        }
      },
      proTypeChange(value) {
        this.providerForm.providerCode = '';
        this.providerInfoSelects = [];
        if (value == '01') {
          this.providerInfoSelects = this.providerCode1;
        } else {
          this.providerInfoSelects = this.providerCode2;
        }
      },
      changePrice(name) {
        this.providerForm[name] = this.providerForm[name].replace(/[^\d.]/g, "") //清除非 数字和小数点的字符
        this.providerForm[name] = this.providerForm[name].replace(/\.{2,}/g, ".") //清除第二个小数点
        this.providerForm[name] = this.providerForm[name].replace(/^\./g, ""); //验证第一个字符是数字而不是字符
        this.providerForm[name] = this.providerForm[name].replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        this.providerForm[name] = this.providerForm[name].replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //保留两位小数
        this.providerForm[name] = this.providerForm[name].indexOf(".") > 0 ? this.providerForm[name].split(".")[0].substring(0, 11) + "." + this.providerForm[name].split(".")[1] : this.providerForm[name].substring(0, 11); //限制只能输入7位正整数
      },
      changeFlagType(value) {
        if (value.indexOf('01') > -1) {
          this.distcoteItem = true;
        } else {
          this.distcoteItem = false;
        }
        if (value.indexOf('02') > -1) {
          this.countItem = true;
        } else {
          this.countItem = false;
        }
      },
      changeAds(value) {
        if (value.indexOf('01') > -1) {
          this.adsFlag = true;
        } else {
          this.adsFlag = false;
        }
      },
      getSuppName(index, row) {
        // console.log(row);
        return this.selectDictLabel(this.supplierInfoSelects, row.supplierCode);
      },
      getServiceName(index, row) {
        // console.log(row);
        return this.selectDictLabel(this.serviceInfoSelects, row.serviceCode);
      },
      gettleTypeName(index, row) {
        // console.log(row);
        return this.selectDictLabel(this.settleTypeSelect, row.settleType);
      },
      getCurrencyName(index, row) {
        // console.log(row);
        return this.selectDictLabel(this.currencys, row.settleCurrency);
      },
      allBaseProviderInfo() {
        const query = {
          pageNum: 1,
          pageSize: 10000,
          xadef: 'select',
          flag: '',
          bussinessStatus: '03',
          status: 'Y',
        };
        getAllBaseProviderInfo(query).then(response => {
          for (let i = 0; i < response.data.length; i++) {
            let bean = response.data[i];
            let obj = new Object();
            obj.dictLabel = bean.providerCode + " - " + bean.chname1;
            obj.dictValue = bean.providerCode;
            if (bean.orgFlag == '01') { // 医院
              this.providerCode1.push(obj);
            }
            if (bean.orgFlag == '02') { //其他
              this.providerCode2.push(obj);
            }
          }

          if (this.onlyAddPro) {
            const query = {
              pageNum: 1,
              pageSize: 10000,
              xadef: 'select',
              flag: '',
              bussinessStatus: '01',
              status: 'Y',
            };
            getAllBackBaseProviderInfo(query).then(response => {
              for (let i = 0; i < response.data.length; i++) {
                let bean = response.data[i];
                let obj = new Object();
                obj.dictLabel = bean.providerCode + " - " + bean.chname1;
                obj.dictValue = bean.providerCode;
                if (bean.orgFlag == '01') { // 医院
                  this.providerCode1.push(obj);
                }
                if (bean.orgFlag == '02') { //其他
                  this.providerCode2.push(obj);
                }
              }
            }).catch(error => {
              console.log(error);
            })
            if (this.$route.query.orgflag == '01') {
              this.providerInfoSelects = this.providerCode1;
            } else {
              this.providerInfoSelects = this.providerCode2;
            }
            this.providerForm.providerCode = this.$route.query.providerCode;
          }
        }).catch(error => {
          console.log(error);
        })
      },
      allAllBaseServiceInfo() {
        const query = {
          xtype: 'BaseServiceInfo',
          status: '02'
        };
        getAllBaseServiceInfo(query).then(response => {
          for (let i = 0; i < response.rows.length; i++) {
            let obj = new Object();
            obj.dictLabel = response.rows[i].serviceName;
            obj.dictValue = response.rows[i].serviceCode;
            this.serviceInfoSelects.push(obj);
          }
        }).catch(error => {
          console.log(error);
        })
      },
      allBaseSupplierInfo() {
        const query = {
          status: 'Y',
          xtype: 'BaseSupplierInfo',
        };
        getAllBaseSupplierInfo(query).then(response => {
          if (response.data != null) {

            response.data = response.data.reverse();
            for (let i = 0; i < response.data.length; i++) {
              let obj = new Object();
              obj.dictLabel = response.data[i].chname + " - " + response.data[i].enname;
              obj.dictValue = response.data[i].serialNo;
              this.supplierInfoSelects.push(obj);
            }
          }
        }).catch(error => {
          console.log(error);
        })
      },
      changeRadio(value) {
        this.addType = value;
        if (value == '1') {
          this.$refs["searchForm"].clearValidate();
          this.formTab = false;
          // this.$refs.providerForm.clearValidate();
        } else {
          this.$refs["providerForm"].clearValidate();
          this.formTab = true;
        }
      },
      viewDetail(row) {
        let flag = row.flag;
        if (this.onlyAddPro) {
          flag = '03';
        }
        this.$router.push({
          path: '/basic-info/contractManageDetail',
          query: {contractNo: row.contractNo, flag: flag, servcomNo: row.servcomNo, providerCode: row.providerCode}
        })
      },
      update(row) {
        this.$router.push({
          path: '/basic-info/contractManage-edit',
          query: {
            status: 'add',
            contractNo: row.contractNo,
            flag: 'update',
            providerCode: row.providerCode,
            hospPro: '0'
          }

        })
      },
      getContractTypeName(row, col) {
        if (row.flag == '02') {
          return this.selectDictLabel(this.contractTypes, row.contractType);
        }
        return this.selectDictLabel(this.supplierContractTypes, row.contractType);
      },
      getContractLimitTypeName(row, col) {
        return this.selectDictLabel(this.contractLimitTypes, row.contracttermType)
      },
      getValiDate(row, col) {
        let str = '';
        if (row.endDate != null) {
          str = " \u3000  " + row.endDate;
        }
        return row.cvaliDate + str;
      },
      getStatuTypeName(row, col) {
        return this.selectDictLabel(this.statusSlects, row.bussinessStatus)
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      // handleClose() {
      //   this.$emit('handleClose');
      //   this.fileList = []
      //   this.$refs.fileForm.resetFields()
      // },
      closeDialog() {
        this.open = false;
        this.dialogVisible = false;
      },
      goBack() {
        let flag = false
        this.serverForm.serverInfo.forEach(item => {
          if (item.supplierCode == null || item.supplierCode == '' || item.supplierCode == undefined) {
            flag = true
          }
        })

        if (flag) {
          return this.$message.warning("存在未保存的供应商服务项目，请先保存！");
        } else {
          this.$router.go(-1);
        }

      },
      goBackPro() {
        this.$router.push({
          path: '/basic-info/medical/edit',
          query: {
            providerCode: this.providerForm.providerCode,
            orgflag: '01',
            status: 'edit'
          }
        })
      },
      openFun() {

      },
      diagShow() {
        this.open = true;
        this.dialogVisible = true;
      },
      //供应商改变
      typeChange() {
        this.getSupplierContractListByChangeType(1);
      },
      //服务机构改变
      typeServerChange() {
        this.getSupplierContractListByChangeType(2);
      },
      //历史合约信息 信息
      getSupplierContractListByChangeType(type) {
        let query = {};
        if (type == 1) {
          query = {
            pageNum: this.hisContractPageInfo.currentPage,
            pageSize: this.hisContractPageInfo.pageSize,
            servcomNo: this.searchForm.servcomNo,
            orderByColumn: 'create_time',
            isAsc: 'desc'
          }

          //查询数据
          getSupplierContractList(query).then(response => {
            this.pendingTableData = response.rows;
            this.hisContractTotalNum = response.total;
          }).catch(error => {
            console.log(error);
          })
        } else {
          query = {
            pageNum: this.hisContractPageInfo.currentPage,
            pageSize: this.hisContractPageInfo.pageSize,
            providerCode: this.providerForm.providerCode,
            //status:'Y',
            orderByColumn: 'create_time',
            isAsc: 'desc',
            xtype: 'bckList'
          };
          if (this.onlyAddPro) {
            query.providerCode = this.$route.query.providerCode;
            //查询数据
            getSupplierContractBakList(query).then(response => {
              this.pendingTableData = response.rows;
              this.hisContractTotalNum = response.total;
            }).catch(error => {
              console.log(error);
            });

            query = {
              pageNum: 1,
              pageSize: 10000,
              providerCode: this.$route.query.providerCode,
              status: 'Y',
              orderByColumn: 'create_time',
              isAsc: 'desc',
              xtype: 'bckList'
            };
            getSupplierContractBakList(query).then(response => {
              this.historyContracts = [];
              for (let i = 0; i < response.rows.length; i++) {
                if (response.rows[i].contractNo == this.providerForm.contractNo) {
                  continue;
                }
                let obj = new Object();
                obj.dictLabel = response.rows[i].contractName;
                obj.dictValue = response.rows[i].contractNo;
                this.historyContracts.push(obj);
              }
            }).catch(error => {
              console.log(error);
            })
          } else {
            query = {
              pageNum: this.hisContractPageInfo.currentPage,
              pageSize: this.hisContractPageInfo.pageSize,
              providerCode: this.providerForm.providerCode,
              orderByColumn: 'create_time',
              isAsc: 'desc'
            };
            //查询数据
            getSupplierContractList(query).then(response => {
              this.pendingTableData = response.rows;
              this.hisContractTotalNum = response.total;
            }).catch(error => {
              console.log(error);
            })

            query = {
              pageNum: 1,
              pageSize: 10000,
              providerCode: this.providerForm.providerCode,
              status: 'Y',
              orderByColumn: 'create_time',
              isAsc: 'desc',
              xtype: 'bckList'
            };
            getSupplierContractList(query).then(response => {
              this.historyContracts = [];
              for (let i = 0; i < response.rows.length; i++) {
                if (response.rows[i].contractNo == this.providerForm.contractNo) {
                  continue;
                }
                let obj = new Object();
                obj.dictLabel = response.rows[i].contractName;
                obj.dictValue = response.rows[i].contractNo;
                this.historyContracts.push(obj);
              }
            }).catch(error => {
              console.log(error);
            })
          }
        }
      },

      uploadFile() {
        console.log('uploadFile')
      },
      downloadFile() {
        console.log('downloadFile')
      },
      deleteFile() {
        console.log('deleteFile')
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
      },
      beforeUpload(file) {
        let url = URL.createObjectURL(file);
        console.log("before")
      },
      success(response, file, fileList) {
        // this.$refs.upload.clearFiles()
        // this.$notify.success(res.data.F_FileName + "文件上传成功");
        this.fileForm.fileName = file.name;
        this.fileUpload = true;
        console.log('success');
        console.log(response);
      },
      saveFileInfo() {
        if (this.fileUpload == false) {
          this.$message.warning('请先上传文件！');
          return false;
        }
        console.log('saveFileInfo');
      },
      fileHandleCurrentChange() {
      },
      fileHandleSizeChange() {
      },
      handleError(file, fileList) {
        this.$message.error('上传失败！')
      },
      saveBaseInfo() {
        if (this.formTab) {
          if (this.pageOpe == 'add') {
            this.fsSub = false;
          }

          this.$refs.serverForm.validate((valid) => {
            if (valid) {
              let listDta = this.serverForm.serverInfo;
              if (listDta.length === 0) {
                this.$message({
                  message: '请至少添加一条供应商服务项目！',
                  type: 'warning'
                });
                return false;
              }
              this.$refs.searchForm.validate((valid) => {
                if (valid) {

                  /*          if (this.baseFomrmSub == false) {
                              this.$message({
                                message: '请至少添加一条供应商服务项目！',
                                type: 'warning'
                              });
                              this.fsSub = true;
                              return false;
                            }
          */
                  let baseSupplierContract = {
                    contractNo: this.searchForm.contractNo,
                    servcomNo: this.searchForm.servcomNo,
                    contractName: this.searchForm.contractName,
                    contractType: this.searchForm.contractType,
                    contracttermType: this.searchForm.contracttermType,
                    cvaliDate: this.searchForm.effectiveSDate[0],
                    endDate: this.searchForm.effectiveSDate[1],
                    expiryDate: this.searchForm.expiryDate,
                    contractadvance: this.searchForm.contractadvance,
                    remark: this.searchForm.remark,
                    contractsort: this.searchForm.contractsort,
                    bussinessStatus: this.searchForm.bussinessStatus,
                    flag: '01',
                    hospContractCode: '01',
                    conSerId: this.searchForm.conSerId
                  };
                  if (this.pageOpe == 'add' && !this.serverInfoSave) {
                    addSupplierContract(baseSupplierContract).then(res => {
                      if (res != null && res.code === 200) {
                        this.queryContractNo = res.data.contractNo;
                        this.searchForm.contractNo = res.data.contractNo;
                        this.searchForm.contractName = res.data.contractName;
                        this.baseFomrmSub = true;
                        this.serverInfoSave = true;
                        this.$message({
                          message: '保存成功！',
                          type: 'success',
                          center: true,
                          showClose: true
                        });
                        this.saveSupplier(listDta)
                        this.getSupplierContractListByChangeType(1);
                      } else {
                        this.fsSub = true;
                        this.$message.error('保存失败！')
                      }
                    })
                  } else {
                    updateSupplierContract(baseSupplierContract).then(res => {
                      if (res != null && res.code === 200) {
                        this.searchForm.contractName = res.data.contractName;
                        this.$message({
                          message: '更新成功！',
                          type: 'success',
                          center: true,
                          showClose: true
                        });
                        this.saveSupplier(listDta)
                        this.getSupplierContractListByChangeType(1)
                      } else {
                        this.$message.error('更新失败！')
                      }
                    })
                  }

                } else {
                  this.fsSub = true;
                  return false
                }
              })

            } else {

              return this.$message.warning("请录入供应商服务项目必录信息！");
            }
          })


        }
        // 服务机构表单提交
        else {
          if (this.pageOpe == 'add') {
            this.fpSub = false;
          }

          this.$refs.providerForm.validate((valid) => {
            if (valid) {
              // 如果是从服务机构管理页面进入的 插入临时表
              if (this.onlyAddPro) {
                this.providerForm.hospContractCode = '02';
              }
              let obj = {};
              obj = this.providerInfoSelects.find((item) => {
                return item.dictValue == this.providerForm.providerCode;
              });
              let proObjName = obj.dictLabel;
              let baseSupplierContract = {
                flag: '02',
                providerName: proObjName,
                providerType: this.providerForm.providerType,
                providerCode: this.providerForm.providerCode,
                contractNo: this.providerForm.contractNo,
                bussinessStatus: this.providerForm.bussinessStatus,
                contractName: this.providerForm.contractName,
                contractPartyA: this.providerForm.contractPartyA,
                contractPartyB: this.providerForm.contractPartyB,
                contractPartyC: this.providerForm.contractPartyC,
                contractType: this.providerForm.contractType,
                connectedContract: this.providerForm.connectedContract,
                deposit: this.providerForm.deposit,
                effectiveSDate: this.providerForm.effectiveSDate,
                renewFlag: this.providerForm.ads.toString(),
                contractControlFlag: this.providerForm.contractControlFlagt.toString(),
                treatmentDiscount: this.providerForm.treatmentDiscount,
                averageCost: this.providerForm.averageCost,
                bedDiscount: this.providerForm.bedDiscount,
                type: this.providerForm.type,
                allowance: this.providerForm.allowance,
                advicenum: this.providerForm.advicenum,
                costs: this.providerForm.costs,
                averageCostExcept: this.providerForm.averageCostExcept,
                discountinfo: this.providerForm.discountinfo,
                specialDiscount: this.providerForm.specialDiscount,
                project: this.providerForm.project,
                cooperativeUnit: this.providerForm.cooperativeUnit,
                examineDiscount: this.providerForm.examineDiscount,
                straight: this.providerForm.straightT.toString(),
                endDate: this.providerForm.endDate,
                reason: this.providerForm.reason,
                liaison: this.providerForm.liaison,
                phone: this.providerForm.phone,
                boxRidgeCode: this.providerForm.boxRidgeCode,
                tel: this.providerForm.tel,
                email: this.providerForm.email,
                exPer: this.providerForm.exPer,
                exEndate: this.providerForm.exEndate,
                cvaliDate: this.providerForm.cvaliDate,
                expiryDate: this.providerForm.expiryDate,
                hospContractCode: this.providerForm.hospContractCode,
                serialNo: this.providerForm.serialNo,
              };
              if (this.pageOpe == 'add') {
                addSupplierContract(baseSupplierContract).then(res => {
                  this.provideConView = true;
                  this.queryContractNo = res.data.contractNo;
                  this.providerForm.contractName = res.data.contractName;
                  this.providerForm.contractNo = res.data.contractNo;


                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    });
                    this.fpSub = false;
                    if (this.onlyAddPro) {
                      this.$router.push({
                        path: '/basic-info/medical/edit',
                        query: {
                          providerCode: this.providerForm.providerCode,
                          hospContractSave: '01',
                          orgflag: '01',
                          contractNo: this.queryContractNo,
                          formData: this.providerForm
                        }
                      })
                    }
                    this.getSupplierContractListByChangeType(2);
                  } else {
                    this.fpSub = true;
                    this.$message.error('保存失败！')
                  }
                })
              } else { // 更新
                updateSupplierContract(baseSupplierContract).then(res => {
                  if (res != null && res.code === 200) {
                    this.providerForm.contractName = res.data.contractName;
                    this.$message({
                      message: '更新成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    });
                    if (this.onlyAddPro) {
                      if (this.hospPro) {
                        this.goBack();
                      } else {
                        this.$router.push({
                          path: '/basic-info/medical/edit',
                          query: {
                            providerCode: this.providerForm.providerCode,
                            hospContractSave: '01',
                            orgflag: '01',
                            flag: 'update',
                            contractNo: this.queryContractNo,
                            formData: this.providerForm
                          }
                        })
                      }

                    }
                    this.getSupplierContractListByChangeType(2);
                  } else {
                    this.$message.error('更新失败！')
                  }
                })
              }
            } else {
              this.fpSub = true;
              return false;
            }
          });
        }


      },
      getRandom(nums) {
        let rdmNum = "";
        for (let i = 0; i < nums; i++) {
          rdmNum += Math.floor(Math.random() * 10); // [0,10)的整数
        }
        return rdmNum;
      },
      addLine() { //添加行数
        let newValue = {
          contractNo: this.queryContractNo == '' ? this.conSerId : this.queryContractNo,
          serviceCode: '',
          settleCurrency: '',
          minPrice: '',
          maxPrice: '',
          settleType: '',
          supplierCode: '',
          limitnum: '',
          editing: 'add'
        };
        //添加新的行数
        this.serverForm.serverInfo.push(newValue);
      },
      getData() {
        //参数集合
        if (this.searchForm.contractNo == '') {
          this.searchForm.contractNo = this.queryContractNo;
        }
        const param = {
          nowPage: 1,
          pageSize: 1000,
          status: 'Y',
          contractNo: this.searchForm.contractNo == '' ? this.conSerId : this.searchForm.contractNo,
          servcomNo: this.searchForm.servcomNo,
        };
        getContractServerList(param).then(response => {
          let _data = response.rows;
          let length = _data.length;
          for (let i = 0; i < length; i++) {
            this.$set(_data[i], 'editing', false)
          }
          //赋值
          this.serverForm.serverInfo = _data;
        }).catch(error => {
          console.log(error);
        })
      },
      getfileTableData() {
        //参数集合
        const param = {
          nowPage: 1,
          pageSize: 1000,
          contractNo: this.searchForm.contractNo,
          servcomNo: this.searchForm.servcomNo,
        };
        getFileList(param).then(response => {
          this.fileTableData = response.rows;
        }).catch(error => {
          console.log(error);
        })
      },
      getDetail(contractNo) {
        getSupplierContractDetail(contractNo).then(response => {
          if ('200' == response.code) {
            let detailData = response.data;
            if (this.formTab) {
              this.searchForm = detailData;
              this.preContractName = detailData.contractName;
              let effectiveArr = [];
              effectiveArr.push(detailData.cvaliDate);
              effectiveArr.push(detailData.endDate);
              this.$set(this.searchForm, `effectiveSDate`, effectiveArr);
              if (detailData.servcomNo != '') {
                this.getSupplierContractListByChangeType(1);
              }
            } else {

              //  复选框回显报错不考虑 控制台错误可以简写
              // this.providerForm = detailData;
              if (detailData.straight != null) {
                this.providerForm.straightT = detailData.straight.split(",");
              }
              if (detailData.contractControlFlag != null) {
                this.providerForm.contractControlFlagt = detailData.contractControlFlag.split(",");
                if (this.providerForm.contractControlFlagt.indexOf("01") > -1) {
                  this.distcoteItem = true;
                }
                if (this.providerForm.contractControlFlagt.indexOf("02") > -1) {
                  this.countItem = true;
                }
              }
              this.preContractName = detailData.contractName;
              this.preContractPartyA = detailData.contractPartyA;
              this.preContractPartyB = detailData.contractPartyB;
              this.preContractPartyC = detailData.contractPartyC;

              if (detailData.providerType == '01') {
                this.providerInfoSelects = this.providerCode1;
              }
              if (detailData.providerType == '02') {
                this.providerInfoSelects = this.providerCode2;
              }
              this.providerForm.providerType = detailData.providerType;
              this.providerForm.providerCode = detailData.providerCode;
              this.providerForm.contractNo = detailData.contractNo;
              this.providerForm.bussinessStatus = detailData.bussinessStatus;
              this.providerForm.contractName = detailData.contractName;
              this.providerForm.contractPartyA = detailData.contractPartyA;
              this.providerForm.contractPartyB = detailData.contractPartyB;
              this.providerForm.contractPartyC = detailData.contractPartyC;
              this.providerForm.contractType = detailData.contractType;
              this.providerForm.connectedContract = detailData.connectedContract;
              this.providerForm.deposit = detailData.deposit;
              this.providerForm.treatmentDiscount = detailData.treatmentDiscount;
              this.providerForm.examineDiscount = detailData.examineDiscount;
              this.providerForm.averageCost = detailData.averageCost;
              this.providerForm.bedDiscount = detailData.bedDiscount;
              this.providerForm.type = detailData.type;
              this.providerForm.allowance = detailData.allowance;
              this.providerForm.advicenum = detailData.advicenum;
              this.providerForm.costs = detailData.costs;
              this.providerForm.averageCostExcept = detailData.averageCostExcept;
              this.providerForm.discountinfo = detailData.discountinfo;
              this.providerForm.specialDiscount = detailData.specialDiscount;
              this.providerForm.project = detailData.project;
              this.providerForm.cooperativeUnit = detailData.cooperativeUnit;
              this.providerForm.endDate = detailData.endDate;
              this.providerForm.reason = detailData.reason;
              this.providerForm.cvaliDate = detailData.cvaliDate;
              this.providerForm.expiryDate = detailData.expiryDate;
              this.providerForm.phone = detailData.phone;
              this.providerForm.tel = detailData.tel;
              this.providerForm.email = detailData.email;
              this.providerForm.boxRidgeCode = detailData.boxRidgeCode;
              this.providerForm.exEndate = detailData.updateTime;
              this.providerForm.exPer = detailData.updateBy;
              this.providerForm.liaison = detailData.liaison;
              if (detailData.renewFlag == null || detailData.renewFlag == '') {
                this.providerForm.ads = [''];
              } else {
                this.providerForm.ads = detailData.renewFlag.split(",");
              }
              if (detailData.providerCode != '') {
                this.getSupplierContractListByChangeType(2);
              }
            }
          }
        }).catch(error => {
          console.log(error);
        })
      },
      getBakDetail() {
        let query = {
          pageNum: 1,
          pageSize: 1,
          contractNo: this.$route.query.contractNo,
          providerCode: this.$route.query.providerCode,
          xtype: 'getBakDetail',
          orderByColumn: 'create_time',
          isAsc: 'desc'
        };
        getSupplierContractBakDetail(query).then(response => {
          if ('200' == response.code && response.rows != '') {

            let detailData = response.rows[0];
            //  复选框回显报错不考虑 控制台错误可以简写
            // this.providerForm = detailData;
            if (detailData.straight != null) {
              this.providerForm.straightT = detailData.straight.split(",");
            }
            if (detailData.contractControlFlag != null) {
              this.providerForm.contractControlFlagt = detailData.contractControlFlag.split(",");
              if (this.providerForm.contractControlFlagt.indexOf("01") > -1) {
                this.distcoteItem = true;
              }
              if (this.providerForm.contractControlFlagt.indexOf("02") > -1) {
                this.countItem = true;
              }
            }

            this.preContractName = detailData.contractName;
            this.preContractPartyA = detailData.contractPartyA;
            this.preContractPartyB = detailData.contractPartyB;
            this.preContractPartyC = detailData.contractPartyC;

            ///providerType    providerInfoSelects providerCode1
            if (detailData.providerType == '01') {
              this.providerInfoSelects = this.providerCode1;
            }
            if (detailData.providerType == '02') {
              this.providerInfoSelects = this.providerCode2;
            }
            this.providerForm.serialNo = detailData.serialNo;
            this.providerForm.providerType = detailData.providerType;
            this.providerForm.providerCode = detailData.providerCode;
            this.providerForm.contractNo = detailData.contractNo;
            this.providerForm.contractName = detailData.contractName;
            this.providerForm.contractPartyA = detailData.contractPartyA;
            this.providerForm.contractPartyB = detailData.contractPartyB;
            this.providerForm.contractPartyC = detailData.contractPartyC;
            this.providerForm.contractType = detailData.contractType;
            this.providerForm.connectedContract = detailData.connectedContract;
            this.providerForm.deposit = detailData.deposit;
            this.providerForm.treatmentDiscount = detailData.treatmentDiscount;
            this.providerForm.examineDiscount = detailData.examineDiscount;
            this.providerForm.averageCost = detailData.averageCost;
            this.providerForm.bedDiscount = detailData.bedDiscount;
            this.providerForm.type = detailData.type;
            this.providerForm.allowance = detailData.allowance;
            this.providerForm.advicenum = detailData.advicenum;
            this.providerForm.costs = detailData.costs;
            this.providerForm.averageCostExcept = detailData.averageCostExcept;
            this.providerForm.discountinfo = detailData.discountinfo;
            this.providerForm.specialDiscount = detailData.specialDiscount;
            this.providerForm.project = detailData.project;
            this.providerForm.cooperativeUnit = detailData.cooperativeUnit;
            this.providerForm.endDate = detailData.endDate;
            this.providerForm.reason = detailData.reason;
            this.providerForm.cvaliDate = detailData.cvaliDate;
            this.providerForm.expiryDate = detailData.expiryDate;
            this.providerForm.phone = detailData.phone;
            this.providerForm.tel = detailData.tel;
            this.providerForm.email = detailData.email;
            this.providerForm.bussinessStatus = '01';// detailData.bussinessStatus;
            this.providerForm.boxRidgeCode = detailData.boxRidgeCode;
            this.providerForm.exEndate = detailData.updateTime;
            this.providerForm.exPer = detailData.updateBy;
            if (detailData.renewFlag == null || detailData.renewFlag == '') {
              this.providerForm.ads = [''];
            } else {
              this.providerForm.ads = detailData.renewFlag.split(",");
            }
          }
        }).catch(error => {
          console.log(error);
        })
      },
      handleDelete(index, row) {
        this.$confirm('确定删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteContractServer(row.serialNo).then(response => {
            if (response.code == '200') {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getData();
            } else {
              this.$message({
                type: 'info',
                message: '删除失败'
              });
            }

          }).catch(error => {
            console.log(error);
          })
        }).catch(() => {
        });
      },
      removeLine(index) { //删除行数
        this.serverForm.serverInfo.splice(index, 1)
      },
      save() {
        if (this.searchForm.contractNo==null || this.searchForm.contractNo=='' || this.searchForm.contractNo==undefined){
          return this.$message.warning("请先保存基本信息！");
        }else  {
          this.$refs.serverForm.validate((valid) => {
            if (valid) {
              let listDta = this.serverForm.serverInfo;
              if (listDta.length === 0) {
                this.$message({
                  message: '请至少添加一条供应商服务项目！',
                  type: 'warning'
                });
                return false;
              }
              addContractServer(listDta).then(response => {
                // console.log(response)
                if (response.code == '200') {
                  this.$message({
                    type: 'success',
                    message: '保存成功!'
                  });
                  this.baseFomrmSub = true;
                  this.getData();
                } else {
                  this.$message({
                    type: 'info',
                    message: '保存失败'
                  });
                }
              }).catch(error => {
                console.log(error);
              })

            } else {

              return false
            }
          })
        }


      },
      handleEdit(index, row) { //编辑
        row.editing = true;
        this.prevValue = JSON.parse(JSON.stringify(row));
      },
      handleCancle(index, row) { //取消
        row.editing = false;
        this.prevValue = JSON.parse(JSON.stringify(row));
      },
      saveSupplier(option) {
        option.forEach(item => {
          if (item.contractNo != null || item.contractNo != '' || item.contractNo != undefined) {
            item.contractNo = this.searchForm.contractNo
          }
        })
        addContractServer(option).then(response => {
          // console.log(response)
          if (response.code == '200') {
            this.baseFomrmSub = true;
            this.getData();
          } else {
          }
        }).catch(error => {
          console.log(error);
        })
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  .long-input /deep/ .el-form-item__content {
    width: calc(100% - 150px);
  }

</style>
