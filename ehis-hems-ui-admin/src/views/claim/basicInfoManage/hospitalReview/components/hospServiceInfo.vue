<template>
  <el-form ref="serviceForm"  :model="serviceForm"
           label-width="170px" size="mini" class="baseInfo_class">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务信息</span>
      </div>

      <el-row>
        <el-col :span="24">
          <el-form-item label="所属医疗网络类型：" prop="networkType">
            <!--            <el-select v-model="serviceForm.networkType" class="item-width" placeholder="请选择" clearable>
                          <el-option label="类型一" value="01"></el-option>
                          <el-option label="类型二" value="02"></el-option>
                          &lt;!&ndash;                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>&ndash;&gt;
                        </el-select>-->
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
            <el-select v-model="serviceForm.category" class="item-width" placeholder="请选择" clearable disabled>
              <el-option v-for="item in spprocurement_flagOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="医院分类：" prop="classification" >
            <el-select v-model="serviceForm.classification" class="item-width" placeholder="请选择" clearable disabled>
              <el-option v-for="item in classificationOptions" :label="item.dictLabel" :value="item.dictValue"
                         :key="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医院评分：" prop="grade">
            <el-input ref="hospitalname" v-model="serviceForm.grade" class="item-width" clearable size="mini" disabled
                      placeholder="请输入(0-10 1位小数)"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务定位：" prop="servicelocator">
            <el-checkbox-group v-if="serviceForm.servicelocator" v-model="serviceForm.servicelocator" disabled>
              <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  servicelocator_flagOptions">
                {{ item.dictLabel }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否周末营业：" prop="weekendsWorking">
            <el-radio-group v-model="serviceForm.weekendsWorking" disabled>
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
            <el-radio-group v-model="serviceForm.cooperationStatus" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  cooperationStatus_flagOptions">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否同步显示至外网：" prop="displaynetwork">
            <el-radio-group v-model="serviceForm.displaynetwork" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否在网站显示：" prop="officialwebsite">
            <el-radio-group v-model="serviceForm.officialwebsite" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否对所有保单开放：" prop="allcontopen">
            <el-radio-group v-model="serviceForm.allcontopen" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否优选医院：" prop="prehospital">
            <el-radio-group v-model="serviceForm.prehospital" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否全国前十：" prop="topten">
            <el-radio-group v-model="serviceForm.topten" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否专科医院：" prop="specializedHospital">
            <el-radio-group v-model="serviceForm.specializedHospital" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="是否当地前三：" prop="topthird">
            <el-radio-group v-model="serviceForm.topthird" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="导检/陪检/驻点：" >
            <el-radio-group v-model="serviceForm.leadFlag" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in leadFlagOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否全国专科前五：" prop="topfive">
            <el-radio-group v-model="serviceForm.topfive" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in yes_or_noOptions ">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否虚拟机构：" prop="virtualOrg">
            <el-radio-group v-model="serviceForm.virtualOrg" disabled>
              <el-radio :label="item.dictValue" :key="item.dictValue" v-for="item in  virtual_orgOptions">
                {{ item.dictLabel }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>


        <el-col :span="24">
          <el-form-item label="特殊服务语种：" prop="speciallanguage">
            <el-checkbox-group v-model="serviceForm.speciallanguage" disabled>
              <el-checkbox :label="item.dictValue" :key="item.dictValue" v-for="item in  speciallanguageOptions">
                {{ item.dictLabel }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="网络医院直结类型：" prop="networkHospitalType">
            <el-checkbox-group v-model="serviceForm.networkHospitalType" disabled>
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
                    style="width: 450px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="特殊费折扣信息：" prop="specialDiscount" v-if="this.serviceForm.specialDiscount">
          <el-input v-model="serviceForm.specialDiscount" class="item-width" clearable maxlength="50" disabled
                    style="width: 450px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="折扣除外项目：" prop="project" v-if="this.serviceForm.project">
          <el-input v-model="serviceForm.project" class="item-width" clearable maxlength="50" disabled
                    style="width: 450px"
                    placeholder="请输入"/>
        </el-form-item>
      </el-row>


      <el-row>
        <el-form-item label="服务特色：" prop="sellingpoint">
          <el-input disabled
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="serviceForm.sellingpoint" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对外备注：" prop="foreignnote">
          <el-input disabled
                    type="textarea"
                    placeholder="请输入内容（客户可见）"
                    v-model="serviceForm.foreignnote" maxlength="500">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="对内备注：" prop="internalnote">
          <el-input
            type="textarea" disabled
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

      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span style="font-size: 20px">医疗网络类型维护</span>
      </div>
      <el-table ref="medicalTable"
                disabled
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
        <el-table-column key="11" align="center" min-width="120px" label="新选项" >
          <template slot-scope="scope">
            <input type="checkBox" v-model="scope.row.isCheck" @click="changeOption(scope.$index,scope.row)"  disabled>
            {{ scope.row.newChoose == '02' ? '是' : '否' }}
          </template>

        </el-table-column>
        <!--            <el-table-column key="4"  align="left" width="120"  >

                    </el-table-column>-->
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
              <el-button size="mini" type="primary" @click="insuranceCloingHandle" >关闭</el-button>
        </span>
      </div>
      <el-table ref="insuranceRecordTable"
                disabled
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

              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>

            <span>{{ scope.row.insurancecode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="所属网络类型" prop="netType" align="center">

          <template slot-scope="scope">
            <el-select v-model="scope.row.netType" class="item-width" v-if="!scope.row.id" placeholder="请选择" clearable
                       size="mini"
            >
              <!--                  <el-option v-for="item in dict.hospitallevel" :label="item.label" :value="item.value" :key="item.value"/>-->
            </el-select>
            <span>{{ scope.row.netType }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" fixed="right">
          <template slot-scope="scope">
                    <span>
                       <el-button type="text" size="mini"
                                  @click="delHandle(scope.$index, scope.row)" disabled>移除</el-button>
                    </span>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addInsuranceRecordHandle()" disabled> + 添加
      </el-button>




    </el-dialog>


    <!--险种维护记录-->
    <el-dialog
      :visible.sync="insuranceLogMainDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      disabled
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

    <!--合约信息-->
    <!--    <service-agency-contract :dialogVisable="serviceDialogVisible" :providerCode="providerCode"
                                 @saveSuccess="changeService"></service-agency-contract>-->

  </el-form>
</template>
<script>
import {getserviceInfo, addserviceInfo, updateserviceInfo,getNewtworktypeList,updateNewtworktypeList,getNewtworktypeLogList} from "@/api/baseInfo/medicalManage";
import {formatDate} from "@/utils";
/*import serviceAgencyContract from './serviceAgencyContract'*/


export default {
  props: {
    propServiceForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    dictList: Array,
    providerCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean,

  },



  data()
  {


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

      serviceForm: {
        weekendsWorking:undefined,
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
        specialDiscount: undefined,
        project: undefined,
        sellingpoint: undefined,
        foreignnote: undefined,
        internalnote: undefined,
        officialwebsite: '02',
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
    providerCode: function (newVal){
      this.getNewtworktype()
    },


  },
  mounted()
  {

  }
  ,
  methods: {
    getNewtworktype(){
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
        /*   if(this.medicalTypeData.filter(item => {
             return item.networktypeName == '网络医院'
           })[0].oldChoose=='02'){
             this.historyContractBtnShow=true
           }*/
      }).catch(res => {
        }
      )
    },
    setServiceForm(){
      this.serviceForm = this.propServiceForm
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



    setHistoryContractBtnShow(){
      console.log(this.isNetHospital)
      this.historyContractBtnShow=this.isNetHospital
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


      /* if(data.isCheck==true){
         data.isCheck=false


       }
       else {
         data.isCheck=true

       }*/
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

    historyContract(){
      //跳到合约页面
      this.$router.push({
        path: '/basic-info/contractManageDetail',
        query: {flag:'03',  providerCode:this.providerCode}
      })
    },





    insuranceMainBtn(){
      //调用接口
      this.insuranceMainDialogVisible=true
    },

    insuranceSaveHandle(){

    },
    insuranceCloingHandle(){
      this.insuranceMainDialogVisible=false
    },

    addInsuranceRecordHandle(){
      const inRecord={
        insurancecode: '',
        netType: ''
      };
      this.insuranceRecordData.push(inRecord)
    },

    //移除
    delHandle(index,row){
      this.insuranceRecordData.splice(index,1)
    },


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


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
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
.el-dialog{
  display: flex;
  flex-direction: column;
  margin:0 !important;
  position:absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
  /*height:600px;*/
  max-height:calc(100% - 30px);
  max-width:calc(100% - 30px);
}
.el-dialog .el-dialog__body{
  flex:1;
  overflow: auto;
}

</style>
