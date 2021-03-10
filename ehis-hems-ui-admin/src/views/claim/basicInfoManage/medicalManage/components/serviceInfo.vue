<template>
  <el-form ref="serviceForm" :rules="serivceFormRules" :model="serviceForm" :disabled="disabledFlag"
           label-width="170px" size="mini" class="baseInfo_class">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务信息</span>
        <span style="float: right;">
          <el-button size="mini" type="primary" @click="saveHandle">保存</el-button>
        </span>
      </div>

      <el-row>
        <el-col :span="24">
          <el-form-item label="所属医疗网络类型：" prop="networkType">
            <el-button size="mini" type="primary" @click="maintainBtn">维护</el-button>
            <el-button size="mini" type="primary" @click="maintainRecordBtn">维护记录</el-button>
            <el-button size="mini" type="primary" @click="insuranceMainBtn">险种维护</el-button>
            <el-button size="mini" type="primary" @click="insuranceMainLogBtn">险种维护记录</el-button>
            <el-button size="mini" type="primary" @click="historyContract" :disabled="!historyContractBtnShow">合约录入
            </el-button>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院类别：" prop="category">
            <el-select v-model="serviceForm.category" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in spprocurement_flagOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="医院分类：" prop="classification">
            <el-select v-model="serviceForm.classification" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="item in classificationOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院评分：" prop="grade">
            <el-input ref="hospitalname" v-model="serviceForm.grade" class="item-width" clearable size="mini"
                      placeholder="请输入(0-10 1位小数)"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务定位：" prop="servicelocator">
            <el-checkbox-group v-if="serviceForm.servicelocator" v-model="serviceForm.servicelocator">
              <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  servicelocator_flagOptions">
                {{ item.dictLabel }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否周末营业：" prop="weekendsWorking">
            <el-radio-group v-model="serviceForm.weekendsWorking">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="医院合作状态：" prop="cooperationStatus">
            <el-radio-group v-model="serviceForm.cooperationStatus">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  cooperationStatus_flagOptions">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否同步显示至外网：" prop="displaynetwork">
            <el-radio-group v-model="serviceForm.displaynetwork">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否在网站显示：" prop="officialwebsite">
            <el-radio-group v-model="serviceForm.officialwebsite">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否对所有保单开放：" prop="allcontopen">
            <el-radio-group v-model="serviceForm.allcontopen">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否优选医院：" prop="prehospital">
            <el-radio-group v-model="serviceForm.prehospital">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否全国前十：" prop="topten">
            <el-radio-group v-model="serviceForm.topten">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否专科医院：" prop="specializedHospital">
            <el-radio-group v-model="serviceForm.specializedHospital">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="是否当地前三：" prop="topthird">
            <el-radio-group v-model="serviceForm.topthird">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="导检/陪检/驻点：" prop="leadFlag">
            <el-radio-group v-model="serviceForm.leadFlag">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in leadFlagOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否全国专科前五：" prop="topfive">
            <el-radio-group v-model="serviceForm.topfive">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否虚拟机构：" prop="virtualOrg">
            <el-radio-group v-model="serviceForm.virtualOrg">
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  virtual_orgOptions">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>


        <el-col :span="24">
          <el-form-item label="特殊服务语种：" prop="speciallanguage">
            <el-checkbox-group v-model="serviceForm.speciallanguage">
              <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  speciallanguageOptions"
                           @click.native="changeDisabled(item,$event)" :disabled="item.isShow">
                {{ item.dictLabel }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="网络医院直结类型：" prop="networkHospitalType">
            <el-checkbox-group v-model="serviceForm.networkHospitalType">
              <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  networkHospitalTypeOptions">
                {{ item.dictLabel }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="13">
          <el-form-item label="诊疗费折扣：" prop="treatmentDiscount" v-if="this.serviceForm.treatmentDiscount">
            <el-input v-model="serviceForm.treatmentDiscount" class="item-width" clearable maxlength="50" disabled
                      style="width: 450px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="次均控费：" prop="code" v-if="this.serviceForm.code">
            <el-input v-model="serviceForm.code" class="item-width" clearable maxlength="50" disabled
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="13">
          <el-form-item label="检查费折扣：" prop="examineDiscount" v-if="this.serviceForm.examineDiscount">
            <el-input v-model="serviceForm.examineDiscount" class="item-width" clearable disabled
                      style="width: 450px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="次均控费类型：" prop="type" v-if="this.serviceForm.type">
            <el-select v-model="serviceForm.type" class="item-width" placeholder="请选择" clearable disabled>
              <el-option v-for="item in typeOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="13">
          <el-form-item label="床位费折扣：" prop="bedDiscount" v-if="this.serviceForm.bedDiscount">
            <el-input v-model="serviceForm.bedDiscount" class="item-width" clearable disabled
                      style="width: 450px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="就诊次数：" prop="adviceNum" v-if="this.serviceForm.adviceNum">
            <el-input v-model="serviceForm.adviceNum" class="item-width" clearable disabled
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="13">
          <el-form-item label="护理费折扣：" prop="allowance" v-if="this.serviceForm.allowance">
            <el-input v-model="serviceForm.allowance" class="item-width" clearable disabled
                      style="width: 450px"
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="次场控费除外项目：" prop="excludingFee" v-if="this.serviceForm.excludingFee">
            <el-input v-model="serviceForm.excludingFee" class="item-width" clearable disabled
                      placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="医药费折扣：" prop="costs" v-if="this.serviceForm.costs">
          <el-input v-model="serviceForm.costs" class="item-width" clearable disabled
                    style="width: 450px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>
      <el-row>

        <el-form-item label="折扣信息：" prop="discountinfo" v-if="this.serviceForm.discountinfo">
          <el-input v-model="serviceForm.discountinfo" class="item-width" clearable maxlength="50" disabled
                    style="width: 550px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="特殊费折扣信息：" prop="specialdiscount" v-if="this.serviceForm.specialdiscount">
          <el-input v-model="serviceForm.specialdiscount" class="item-width" clearable maxlength="50" disabled
                    style="width: 550px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="折扣除外项目：" prop="project" v-if="this.serviceForm.specialdiscount">
          <el-input v-model="serviceForm.project" class="item-width" clearable maxlength="50" disabled
                    style="width: 550px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>


      <el-row>
        <el-form-item label="服务特色：" prop="sellingpoint">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="serviceForm.sellingpoint" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对外备注：" prop="foreignnote">
          <el-input
            type="textarea"
            placeholder="请输入内容（客户可见）"
            v-model="serviceForm.foreignnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对内备注：" prop="internalnote">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="serviceForm.internalnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>

    </el-card>
    <el-dialog
      :visible.sync="maintainDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">

      <div  style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span style="font-size: 20px">医疗网络类型维护</span>
      </div>

      <el-table ref="medicalTable"
                :data="medicalTypeData"
                v-loading="loading"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">

        <el-table-column key="1" align="center" prop="networktypeName" min-width="150" label="医疗网络类型"/>
        <el-table-column key="2" align="center" min-width="100" prop="productCode" label="产品代码"/>
        <el-table-column key="3" align="center" prop="oldChoose" label="当前选项" min-width="120">
          <template slot-scope="scope">
            <span>{{ scope.row.oldChoose == '01' ? '否' : '是' }}</span>
          </template>
        </el-table-column>
        <el-table-column key="11" align="center" min-width="120px" label="新选项">
          <template slot-scope="scope">
            <input type="checkBox" v-model="scope.row.isCheck" @click="changeOption(scope.$index,scope.row)">
            {{ scope.row.newChoose == '02' ? '是' : '否' }}
          </template>

        </el-table-column>
        <el-table-column key="5" align="center" min-width="150" prop="newDate" label="新选项生效日" style="width: 180px">
          <template slot-scope="scope">
            <el-date-picker v-if="scope.row.newInputShow " v-model="scope.row.newDate"
                            type="datetime"
                            placeholder="选择日期时间">
            </el-date-picker>
            <span v-else="scope.row.newDate">{{ scope.row.newDate }}</span>


          </template>
        </el-table-column>
        <el-table-column key="6" align="center" prop="updateBy" label="操作人" min-width="120"/>
      </el-table>




      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="delConfirm">确 定</el-button>
          <el-button @click="cancelBtn">取 消</el-button>

        </span>
    </el-dialog>
    <!--医疗维护记录弹出框-->
    <el-dialog
      :visible.sync="medicalRecordDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">

      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span style="font-size: 20px">所属医疗网络类型维护记录</span>
      </div>
      <el-table ref="medicalRecordTable"
                :data="medicalRecordData"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">

        <el-table-column key="1" align="center" prop="networktypeName" min-width="150" label="医疗网络类型"/>
        <el-table-column key="2" align="center" min-width="100" prop="newChooseName" label="选项"/>
        <el-table-column key="3" align="center" prop="newDate" min-width="150" label="选项生效日"/>
        <el-table-column key="4" align="center" min-width="100" prop="failureTime" label="选项失效日"/>
        <el-table-column key="5" align="center" prop="createBy" min-width="150" label="维护人"/>
        <el-table-column key="6" align="center" prop="updateTime" label="维护时间" min-width="120"/>
      </el-table>

      <!--分页组件-->
      <pagination
        v-show="totalCount>0"
        :total="totalCount"
        :page.sync="formSearch.pageNum"
        :limit.sync="formSearch.pageSize"
        @pagination="getData"
      />






    </el-dialog>


    <!--险种维护表-->
    <el-dialog
      :visible.sync="insuranceMainDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="50%">

      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span style="font-size: 20px">险种维护列表</span>
        <span style="float: right;">
          <el-button size="mini" type="primary" @click="insuranceSaveHandle">保存</el-button>
              <el-button size="mini" type="primary" @click="insuranceCloingHandle">关闭</el-button>
        </span>
      </div>
      <el-table ref="insuranceRecordTable"
                :data="insuranceRecordData"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">

        <el-table-column label="险种代码" prop="insurancecode" align="center">
          <template slot-scope="scope">
            <el-select v-model="scope.row.insurancecode" class="item-width" placeholder="请选择" clearable
                       v-if="!scope.row.id"
                       size="mini"
            >
              <!--                <el-option value="01" label="是"></el-option>
                              <el-option value="02" label="否"></el-option>-->
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

            <span v-else>{{ scope.row.insurancecode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="所属网络类型" prop="netType" align="center">

          <template slot-scope="scope">
            <el-select v-model="scope.row.netType" class="item-width" v-if="!scope.row.id" placeholder="请选择" clearable
                       size="mini"
            >
              <!--                <el-option value="01" label="是"></el-option>
                              <el-option value="02" label="否"></el-option>-->
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
            <span v-else>{{ scope.row.netType }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" fixed="right">
          <template slot-scope="scope">
                    <span>
                       <el-button type="text" size="mini"
                                  @click="delHandle(scope.$index, scope.row)">移除</el-button>
                    </span>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addInsuranceRecordHandle()"> + 添加
      </el-button>




    </el-dialog>


    <!--险种维护记录-->
    <el-dialog
      :visible.sync="insuranceLogMainDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">

      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span style="font-size: 20px">险种维护记录</span>
      </div>
      <el-table ref="medicalRecordTable"
                v-loading="loading"
                :data="insuranceLogData"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">

        <el-table-column key="1" align="center" prop="networktypeCode" min-width="150" label="险种"/>
        <el-table-column key="2" align="center" min-width="100" prop="oldChoose" label="选项"/>
        <el-table-column key="3" align="center" prop="newDate" min-width="150" label="选项生效日"/>
        <el-table-column key="4" align="center" min-width="100" prop="failureTime" label="选项失效日"/>
        <el-table-column key="5" align="center" prop="createBy" min-width="150" label="维护人"/>
        <el-table-column key="6" align="center" prop="updateTime" label="维护时间" min-width="120"/>
      </el-table>
      <!--分页组件-->
      <pagination
        v-show="insuranceLogTotalCount>0"
        :total="insuranceLogTotalCount"
        :page.sync="insuranceLogFormSearch.pageNum"
        :limit.sync="insuranceLogFormSearch.pageSize"
        @pagination="getInsuranceLogData"
      />





    </el-dialog>




  </el-form>
</template>
<script>
import {

  getserviceInfo,
  addserviceInfo,
  updateserviceInfo,
  getNewtworktypeList,
  updateNewtworktypeList,
  getNewtworktypeLogList,
  getNewtworktypeRisklog
} from "@/api/baseInfo/medicalManage";
import {formatDate} from "@/utils";
import {getSupplierContractBakDetail} from "@/api/contractManage/contractManagement";


export default {
  props: {
    propServiceForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    hospContactFormData: {
      type: Object,
      default: function () {
        return {}
      }
    },
    dictList: Array,
    providerCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean,
    hospContractSaveFlag: Boolean,
    status: String
  },


  data() {
    const checkGrade = (rules, value, callback) => {
      const regx = /^\+?(?:\d(?:\.\d)?|10(?:\.0)?)$/
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.grade) {
          callback(new Error('不能为空！'))
        }
        else{
          if (!regx.test(value)) {
            callback(new Error('评分在0-10，最多保留一位小数！'))
          } else {
            callback()
          }
        }
      }
      else{
        if(value) {
          if (!regx.test(value)) {
            callback(new Error('评分在0-10，最多保留一位小数！'))
          } else {
            callback()
          }
        }
        else {
          callback()
        }
      }
    }


    const checkDisplaynetwork = (rules, value, callback) => {

      if (this.historyContractBtnShow) {
        if (!this.serviceForm.displaynetwork) {
          callback(new Error('同步显示至外网不能为空！'))
        } else {
          callback()
        }

      } else {
        callback()
      }
    }
    const checkOfficialwebsite = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.officialwebsite) {
          callback(new Error('网站显示不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkSellingpoint = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.sellingpoint) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }


    const checkAllcontopen = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.allcontopen) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkPrehospital = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.prehospital) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkTopten = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.topten) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkSpecializedHospital = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.specializedHospital) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkTopthird = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.topthird) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkLeadFlag = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.leadFlag){
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkTopfive = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.topfive) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    const checkVirtualOrg=(rules, value, callback) => {
      if(this.historyContractBtnShow){
        if(!this.serviceForm.virtualOrg){
          callback(new Error('不能为空！'))
        }
        else {
          callback()
        }
      }
      else {
        callback()
      }
    }

    const checkSpeciallanguage = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (this.serviceForm.speciallanguage.length<=0) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkNetworkHospitalType = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (this.serviceForm.networkHospitalType.length<=0) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkCooperationStatus = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.cooperationStatus) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkWeekendsWorking = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.weekendsWorking) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkForeignnote = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.foreignnote) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const checkInternalnote = (rules, value, callback) => {
      if (this.historyContractBtnShow) {
        if (!this.serviceForm.internalnote) {
          callback(new Error('不能为空！'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }

    return {
      loading: false,
      insuranceLogTotalCount: 0,
      totalCount: 0,
      insuranceMainDialogVisible: false,
      insuranceRecordData: [],
      serviceDialogVisible: false,

      // 查询参数
      formSearch: {
        pageNum: 1,
        pageSize: 10,
        providerCode: undefined
      },
      //险种维护记录查询条件
      insuranceLogFormSearch: {
        pageNum: 1,
        pageSize: 10,
        providerCode: undefined
      },
      insuranceLogData: [],
      insuranceLogMainDialogVisible: false,
      medicalRecordDialogVisible: false,
      medicalRecordData: [],
      medicalTypeData: [],
      maintainDialogVisible: false,
      modalValue: false,
      serivceFormRules: {
        grade: [{validator: checkGrade, trigger: 'blur'}],
        cooperationStatus: [{validator: checkCooperationStatus, trigger: 'change'}],
        foreignnote: [{validator: checkForeignnote, trigger: 'blur'}],
        internalnote: [{validator:checkInternalnote, trigger: 'blur'}],
        //examineDiscount: [{validator: checkExamineDiscount, trigger: 'blur'}],
        // bedDiscount: [{validator: checkDiscount, trigger: 'blur'}],
        // adviceNum: [{required: true, message: '不能为空！', trigger: 'blur'}],
        // allowance: [{validator: checkAllowance, trigger: 'blur'}],
        // costs: [{validator: checkCosts, trigger: 'blur'}],

        displaynetwork: [{validator: checkDisplaynetwork, trigger: 'change'}],
        officialwebsite: [{validator: checkOfficialwebsite, trigger: 'blur'}],
        allcontopen: [{validator: checkAllcontopen, trigger: 'change'}],
        prehospital: [{validator: checkPrehospital, trigger: 'change'}],
        topten: [{validator: checkTopten, trigger: 'change'}],
        specializedHospital: [{validator: checkSpecializedHospital, trigger: 'change'}],
        topthird: [{validator: checkTopthird, trigger: 'change'}],
        leadFlag: [{validator: checkLeadFlag, trigger: 'change'}],
        topfive: [{validator: checkTopfive, trigger: 'change'}],
        virtualOrg: [{validator: checkVirtualOrg, trigger: 'change'}],
        speciallanguage: [{validator: checkSpeciallanguage, trigger: 'change'}],
        networkHospitalType: [{validator: checkNetworkHospitalType, trigger: 'change'}],
        weekendsWorking: [{validator: checkWeekendsWorking, trigger: 'change'}],
        sellingpoint: [{validator: checkSellingpoint, trigger: 'blur'}],


      },
      saveFlag: true,
      historyContractBtnShow: false,
      spprocurement_flagOptions: [],
      classificationOptions: [],
      yes_or_noOptions: [],
      cooperationStatus_flagOptions: [],
      servicelocator_flagOptions: [],
      speciallanguageOptions: [],
      networkHospitalTypeOptions: [],
      typeOptions: [],
      leadFlagOptions: [],
      virtual_orgOptions: [],
      //历史合约的数据
      pendingTableData: [],
      serviceForm: {
        excludingFee: undefined,
        topten: undefined,
        specializedHospital: undefined,
        topthird: undefined,
        leadFlag: undefined,
        topfive: undefined,
        virtualOrg: undefined,
        category: undefined,
        classification: undefined,
        grade: undefined,
        cooperationStatus: undefined,
        displaynetwork: undefined,
        allcontopen: undefined,
        prehospital: undefined,
        treatmentDiscount: undefined,
        code: undefined,
        examineDiscount: undefined,
        type: undefined,
        bedDiscount: undefined,
        adviceNum: undefined,
        allowance: undefined,
        costs: undefined,
        discountinfo: undefined,
        specialdiscount: undefined,
        project: undefined,
        sellingpoint: undefined,
        foreignnote: undefined,
        internalnote: undefined,
        officialwebsite: '02',
        weekendsWorking: undefined,
        servicelocator: [],
        speciallanguage: [],
        networkHospitalType: []
      },

    }
  },
  watch: {
    dictList: function (newVal, oldVal) {
      this.init();
    },

    propServiceForm: function (newVal, oldVal) {
      this.setServiceForm();
    },
    providerCode: function (newVal) {
      this.getNewtworktype()
    },
    hospContactFormData: function (newVal) {
      this.getHospContactFormData()
    }


  },

  mounted() {
    this.setServiceForm()
  }
  ,
  methods: {
    changeDisabled(val, event) {



      if (val.dictValue == '10' && event.target.checked) {
        this.speciallanguageOptions.forEach(item => {
          if (item.index < 10) {
            item.isShow = true
          }
        })
        this.serviceForm.speciallanguage = []
        console.log(this.serviceForm.speciallanguage)
      } else {
        this.speciallanguageOptions.map(item => {
          item.isShow = false
        })

      }
    },

    getHospContactFormData() {
      this.serviceForm.treatmentDiscount = this.hospContactFormData.treatmentDiscount
      this.serviceForm.code = this.hospContactFormData.averageCost
      this.serviceForm.bedDiscount = this.hospContactFormData.bedDiscount
      this.serviceForm.type = this.hospContactFormData.type
      this.serviceForm.allowance = this.hospContactFormData.allowance
      this.serviceForm.advicenum = this.hospContactFormData.advicenum
      this.serviceForm.costs = this.hospContactFormData.costs
      this.serviceForm.excludingFee = this.hospContactFormData.averageCostExcept
      this.serviceForm.discountinfo = this.hospContactFormData.discountinfo
      this.serviceForm.specialDiscount = this.hospContactFormData.specialDiscount
      this.serviceForm.project = this.hospContactFormData.project
    },


    getNewtworktype() {
      let query = {
        providerCode: this.providerCode
      }
      getNewtworktypeList(query).then(res => {
        this.medicalTypeData = res.rows
        console.log(this.medicalTypeData)
        let flag = this.medicalTypeData.filter(item => {
          return item.networktypeName == '网络医院'
        })[0].oldChoose
        if (flag == '02') {
          this.historyContractBtnShow = true
        } else {
          this.historyContractBtnShow = false
        }

      }).catch(res => {
        }
      )
    },
    setServiceForm() {
      if (localStorage.getItem('portFlaws')) {
        this.serviceForm = JSON.parse(localStorage.getItem('portFlaws'))
        localStorage.removeItem('portFlaws')
        console.log(this.serviceForm)
      } else {
        this.serviceForm = this.propServiceForm
      }
    },

    init() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.spprocurement_flagOptions = this.dictList.find(item => {
          return item.dictType == 'spprocurement_flag'
        }).dictDate
        this.classificationOptions = this.dictList.find(item => {
          return item.dictType == 'classification'
        }).dictDate
        this.yes_or_noOptions = this.dictList.find(item => {
          return item.dictType == 'yes_or_no'
        }).dictDate
        this.cooperationStatus_flagOptions = this.dictList.find(item => {
          return item.dictType == 'cooperationStatus_flag'
        }).dictDate
        this.servicelocator_flagOptions = this.dictList.find(item => {
          return item.dictType == 'servicelocator_flag'
        }).dictDate
        this.speciallanguageOptions = this.dictList.find(item => {
          return item.dictType == 'speciallanguage'
        }).dictDate
        if (this.speciallanguageOptions.length > 0) {
          this.speciallanguageOptions.map((item, index) => {
            item.isShow = false
            item.index = index + 1
          })
        }
        this.networkHospitalTypeOptions = this.dictList.find(item => {
          return item.dictType == 'networkHospitalType'
        }).dictDate
        this.typeOptions = this.dictList.find(item => {
          return item.dictType == 'type'
        }).dictDate
        this.leadFlagOptions = this.dictList.find(item => {
          return item.dictType == 'leadFlag'
        }).dictDate
        this.virtual_orgOptions = this.dictList.find(item => {
          return item.dictType == 'virtual_org'
        }).dictDate


      }
    },


    //服务信息保存
    saveHandle() {
      if (this.saveFlag || this.hospContractSaveFlag) {
        this.$refs.serviceForm.validate((valid) => {
          this.serviceForm.providerCode = this.providerCode
          if (valid) {
            //存在调用服务信息保存的接口
            if (this.serviceForm.providerCode) {
              const subFormSearch = JSON.parse(JSON.stringify(this.serviceForm))
              subFormSearch.orgFlag = this.status
              addserviceInfo(subFormSearch).then(res => {
                if (res.code == '200') {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                } else {
                  this.$message({
                    message: '保存失败!',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                }
              })
            }

          } else {
            this.$message.warning('服务信息必录项未必录')
          }

        })
      } else {
        this.$message.warning('合约信息必录')
      }


    }
    ,

    //重置
    resetForm() {
      this.$refs.serviceForm.resetFields()
    },

    //维护弹出框
    maintainBtn() {
      this.medicalTypeData = []
      this.maintainDialogVisible = true
      this.loading = true
      let query = {
        providerCode: this.providerCode
      }
      getNewtworktypeList(query).then(res => {
        this.medicalTypeData = res.rows
        this.medicalTypeData.map((data, index) => {
          data.newInputShow = false
          data.isCheck = '01',
            data.copyNewDate = data.newDate
        })
        this.loading = false
      }).catch(res => {
          this.loading = false
        }
      )
    },

    changeOption(index, data) {



      if (data.isCheck == '01') {
        data.isCheck = '02'
        let defaultTime = new Date()
        this.$set(this.medicalTypeData[index], "newDate", defaultTime);
        data.newInputShow = true
      } else {
        data.isCheck = '01'
        data.newDate = data.copyNewDate,
          data.newInputShow = false
      }

    },
    cancelBtn() {
      this.maintainDialogVisible = false
    },
//险种维护记录
    insuranceMainLogBtn() {
      this.insuranceLogMainDialogVisible = true
      this.loading = true

      /*getNewtworktypeList().then(res =>{
        this.insuranceLogData=res.rows
        this.loading=false
      }).catch(res=>{
          this.loading = false
        }
      )*/
      this.getInsuranceLogData()
      this.loading = false
    },
    //调用查询险种维护记录的接口
    getInsuranceLogData() {


    },


    delConfirm() {
      let filterData = this.medicalTypeData.filter(item => {
        return item.isCheck == '02'
      })
      let medicalData = {
        providerCode: this.providerCode,
        medicalTypeData: filterData
      }
      console.log(filterData)
      if (filterData.length > 0) {
        //调用保存接口
        updateNewtworktypeList(medicalData).then(res => {
          if (res.code == '200') {
            this.$message({
              message: '保存成功！',
              type: 'success',
              center: true,
              showClose: true
            })


          } else {
            this.$message({
              message: '保存失败!',
              type: 'error',
              center: true,
              showClose: true
            })
          }
        })
      } else {
        this.$message.warning('提示请至少选择一个医疗网络类型!')
      }

      let objNetHosp = this.medicalTypeData.find(item => {
        return item.networktypeName === '网络医院'
      })
      console.log(objNetHosp)
      if (objNetHosp != null && objNetHosp != '') {
        if (objNetHosp.oldChoose == '02' && objNetHosp.isCheck == '01' || objNetHosp.oldChoose == '01' && objNetHosp.isCheck == '02') {
          this.historyContractBtnShow = true,
            this.saveFlag = false
        } else {
          this.historyContractBtnShow = false,
            this.saveFlag = true
        }
      }
      /*  getNewtworktypeList().then(res =>{
          this.copyMedicalTypeData=res.rows
          if(this.medicalTypeData.filter(item => {
            return item.networktypeName== '网络医院'
          })[0].oldChoose=='02'){
            this.historyContractBtnShow=true,
              this.saveFlag=false
          }
        }).catch(res=>{

          }
        )*/
      /*  this.historyContractBtnShow=true*/

    },
    //维护记录弹出框
    maintainRecordBtn() {
      this.medicalRecordDialogVisible = true
      //调用查询方法
      this.getData()

    },
    //维护记录查询方法
    getData() {
      this.formSearch.providerCode = this.providerCode
      getNewtworktypeLogList(this.formSearch).then(res => {
        if(res!=null && res.code=='200') {
          this.medicalRecordData = res.rows
          this.totalCount = res.total
        }
      })
    },


    historyContract() {
      console.log('---------------------')
      localStorage.setItem('portFlaws', JSON.stringify(this.serviceForm))
      if (this.isAdd) {
        //跳到合约新增页面
        this.$router.push({
          path: '/basic-info/contractManage-edit',
          query: {
            status: 'add',
            flag: 'add',
            providerCode: this.providerCode,
            orgflag: '01'
          }

        })
      } else {
        //跳到合约编辑页面
        this.$router.push({
          path: '/basic-info/contractManage-edit',
          query: {
            status: 'add',
            flag: 'update',
            providerCode: this.providerCode,
            orgflag: '01'
          }

        })
      }
    },




    insuranceMainBtn() {
      //调用接口
      this.insuranceMainDialogVisible = true
    },

    insuranceSaveHandle() {

    },
    insuranceCloingHandle() {
      this.insuranceMainDialogVisible = false
    },

    addInsuranceRecordHandle() {
      const inRecord = {
        insurancecode: '',
        netType: ''
      };
      this.insuranceRecordData.push(inRecord)
    },

    //移除
    delHandle(index, row) {
      this.insuranceRecordData.splice(index, 1)
    },

    // 校验数据
    async validateForm() {


      //合约是否录入

      this.pendingTableData = []
      let query = {
        pageNum: 1,
        pageSize: 10,
        providerCode: this.providerCode,
        orderByColumn: 'create_time',
        isAsc: 'desc'
      };
      let flag
      await new Promise((resolve, reject) => {
        //查询数据
        getSupplierContractBakDetail(query).then(response => {
          this.pendingTableData = response.rows;
          resolve(this.pendingTableData)
        })
      }).then(res => {
        if(this.historyContractBtnShow) {
          if (this.pendingTableData.length > 0) {
            this.$refs['serviceForm'].validate(valid => {
              if (valid) {
                flag = '01'
              } else {
                flag = '03'
              }
            })
          } else {
            flag = '02';
          }
        }
        else {
          this.$refs['serviceForm'].validate(valid => {
            if (valid) {
              flag = '01'
            } else {
              flag = '03'
            }
          })
        }



      })
      return flag
    }
  }


}
</script>
<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item ::v-deep label {
  font-weight: normal;
}


.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}

.el-dialog {
  display: flex;
  flex-direction: column;
  margin: 0 !important;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*height:600px;*/
  max-height: calc(100% - 30px);
  max-width: calc(100% - 30px);
}

.el-dialog .el-dialog__body {
  flex: 1;
  overflow: auto;
}

</style>
