<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>流程节点-服务申请-基础信息</span>
      </div>
      <el-form
        ref="serviceapply"
        :model="serviceapply"
        :rules="serviceapplyRules"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否可取消：" prop="cancelFlag">
              <el-select v-model="serviceapply.cancelFlag"
                         class="item-width"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否发送消息：" prop="sendmessageFlag">
              <el-select v-model="serviceapply.sendmessageFlag"
                         class="item-width"
                         placeholder="请选择"
                         @change="selectApplyMess()"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button
              size="mini"
              class="item-width"
              type="text"
              style="padding: 0px;"
              :disabled="ableI"
              @click="MessageTemplate"
            >消息模板
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否可变更：" prop="updateFlag">
              <el-select v-model="serviceapply.updateFlag"
                         class="item-width"
                         @change="selectUpdateFlag"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="变更次数：" prop="updateNum" v-if="showUpdateNum">
              <el-input v-model="serviceapply.updateNum"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否本人使用：" prop="selfused">
              <el-select v-model="serviceapply.selfused"
                         class="item-width"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card department-style">
      <div slot="header" class="clearfix">
        <span>流程节点-服务申请-业务字段</span>
      </div>
      <el-form ref="applyfieldList" :rules="applyfieldListRule" :model="applyfieldList" size="mini">
        <el-table :data="applyfieldList.form" highlight-current-row>
          <el-table-column label="字段名称" prop="businessName" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.businessName'" :rules="applyfieldListRule.businessName">
                <el-select v-model="scope.row.businessName" v-show="scope.row.show" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="dict in fieldNameOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue+' - '+dict.dictLabel"
                    :value="dict.dictLabel"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-show="!scope.row.show" class="item-width">{{scope.row.businessName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="录入方式" prop="recondType" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.recondType'" :rules="applyfieldListRule.recondType">
                <el-select v-model="scope.row.recondType" v-show="scope.row.show" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="dict in recondTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictLabel"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-show="!scope.row.show" class="item-width">{{scope.row.recondType}}</span>
            </template>
          </el-table-column>
          <el-table-column label="是否必输" prop="isrecond" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.isrecond'" :rules="applyfieldListRule.isrecond">
                <el-select v-model="scope.row.isrecond" v-show="scope.row.show" class="item-width" placeholder="请选择" clearable>
                  <el-option v-for="dict in booleanOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictLabel"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-show="!scope.row.show" class="item-width">{{(scope.row.isrecond)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="备注" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.remark'" :rules="applyfieldListRule.remark">
                <el-input v-model="scope.row.remark"
                          v-show="scope.row.show"
                          class="item-width"
                          placeholder="请输入备注信息"
                          clearable/>
              </el-form-item>
              <span v-show="!scope.row.show" class="item-width">{{scope.row.remark}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"
                           fixed="right">
            <template slot-scope="scope">
              <el-form-item>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="deleteApplyFieldOneRow(scope.$index)"
                >删除
                </el-button>
              </el-form-item>
            </template>
          </el-table-column>
        </el-table>
        <el-row :gutter="10" class="mb8">
          <el-col :span="24">
            <el-button
              style="width: 100%; margin-top: 3px;"
              icon="el-icon-plus"
              size="mini"
              @click="addApplyFieldOneRow"
            >添加
            </el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>流程节点-分配-基础信息</span>
      </div>
      <el-form
        ref="allocationForm"
        :model="allocationForm"
        :rules="allocationFormRule"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否可取消：" prop="cancelFlag">
              <el-select v-model="allocationForm.cancelFlag"
                         class="item-width"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否发送消息：" prop="sendmessageFlag">
              <el-select v-model="allocationForm.sendmessageFlag"
                         class="item-width"
                         placeholder="请选择"
                         @change="selectAllocationMess()"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button
              size="mini"
              type="text"
              style="padding: 0px;"
              :disabled="ableII"
              @click=" MessageTemplate"
              class="item-width"
            >消息模板
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否自动分配：" prop="distribute">
              <el-select v-model="allocationForm.distribute"
                         class="item-width"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>流程节点-接单-基础信息</span>
      </div>
      <el-form
        ref="orderForm"
        :model="orderForm"
        :rules="orderFormRule"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否可取消：" prop="cancelFlag">
              <el-select v-model="orderForm.cancelFlag"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否发送消息：" prop="sendmessageFlag">
              <el-select v-model="orderForm.sendmessageFlag"
                         placeholder="请选择"
                         @change="selectOrderMess()"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button
              size="mini"
              type="text"
              style="padding: 0px;"
              :disabled="ableIII"
              @click=" MessageTemplate"
            >消息模板
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="时效：" prop="aging">
              <el-input v-model="orderForm.aging"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="请输入，单位：分钟"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>流程节点-实施-基础信息</span>
      </div>
      <el-form
        ref="implement"
        :model="implement"
        :rules="implementRule"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否可取消：" prop="cancelFlag">
              <el-select v-model="implement.cancelFlag" placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否发送消息：" prop="sendmessageFlag">
              <el-select v-model="implement.sendmessageFlag"
                         placeholder="请选择"
                         @change="selectImplementMess()"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button
              size="mini"
              type="text"
              style="padding: 0px;"
              :disabled="ableIV"
              @click=" MessageTemplate"
            >消息模板
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否记录信息：" prop="recordFlag">
              <el-select v-model="implement.recordFlag"
                         @change="selectRecordFlag"
                         placeholder="请选择"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="记录次数限制：" prop="frequency" v-if="showFrequency">
              <el-input v-model="implement.frequency"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="若需要记录信息，请填写此条"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="时效：" prop="aging">
              <el-input v-model="implement.aging"
                        class="item-width"
                        clearable
                        size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card department-style">
      <div slot="header" class="clearfix">
        <span>流程节点-服务实施-业务字段</span>
      </div>
      <el-form ref="imfieldList" :rules="imfieldListRule" :model="imfieldList" size="mini">
        <el-table :data="imfieldList.form" highlight-current-row>
          <el-table-column label="字段名称" prop="businessName" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.businessName'" :rules="imfieldListRule.businessName">
                <el-select v-model="scope.row.businessName" v-show="scope.row.show" placeholder="请选择" clearable>
                  <el-option v-for="dict in imFieldNameOptions"
                             :key="dict.dictValue"
                             :label="dict.dictValue+' - '+dict.dictLabel"
                             :value="dict.dictLabel"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-show="!scope.row.show">{{scope.row.businessName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="录入方式" prop="recondType" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.recondType'" :rules="imfieldListRule.recondType">
                <el-select v-model="scope.row.recondType" v-show="scope.row.show" placeholder="请选择" clearable>
                  <el-option v-for="dict in recondTypeOptions"
                             :key="dict.dictValue"
                             :label="dict.dictValue + ' - ' +dict.dictLabel"
                             :value="dict.dictLabel"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-show="!scope.row.show">{{scope.row.recondType}}</span>
            </template>
          </el-table-column>
          <el-table-column label="是否必输" prop="isrecond" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.isrecond'" :rules="imfieldListRule.isrecond">
                <el-select v-model="scope.row.isrecond" v-show="scope.row.show" placeholder="请选择" clearable>
                  <el-option v-for="dict in booleanOptions"
                             :key="dict.dictValue"
                             :label="dict.dictValue + ' - ' +dict.dictLabel"
                             :value="dict.dictLabel"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-show="!scope.row.show">{{(scope.row.isrecond)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="备注" align="center">
            <template slot-scope="scope">
              <el-form-item :prop="'form.' + scope.$index + '.remark'" :rules="imfieldListRule.remark">
                <el-input v-model="scope.row.remark"
                          v-show="scope.row.show"
                          placeholder="请输入备注信息"
                          clearable/>
              </el-form-item>
              <span v-show="!scope.row.show">{{scope.row.remark}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"
                           fixed="right">
            <template slot-scope="scope">
              <el-form-item>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="deleteServiceImFieldOneRow(scope.$index)"
                >删除
                </el-button>
              </el-form-item>
            </template>
          </el-table-column>
        </el-table>
        <el-row :gutter="10" class="mb8">
          <el-col :span="24">
            <el-button
              style="width: 100%; margin-top: 3px;"
              icon="el-icon-plus"
              size="mini"
              @click="addServiceImFieldOneRow"
            >添加
            </el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>流程节点-用户评价</span>
      </div>
      <el-form
        ref="userForm"
        :model="userForm"
        :rules="userRule"
        style="padding-bottom: 30px;"
        label-width="134px"
        label-position="right"
        size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否发送消息：" prop="sendmessageFlag">
              <el-select v-model="userForm.sendmessageFlag"
                         placeholder="请选择"
                         @change="selectUserFormMess"
                         clearable
              >
                <el-option
                  v-for="dict in booleanOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue+' - '+dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button
              size="mini"
              type="text"
              style="padding: 0px;"
              :disabled="ableV"
              @click=" MessageTemplate"
            >消息模板
            </el-button>
          </el-col>
        </el-row>
        <el-row>
        </el-row>
      </el-form>
    </el-card>
    <el-row >
      <el-row :gutter="10" class="mt20" style="text-align: right;">
        <el-button
          type="primary"
          size="mini"
          @click="tempStorage"
        >暂存</el-button>
        <el-button
          type="primary"
          size="mini"
          @click="projectPublish"
        >保存并发布</el-button>
        <el-button
          size="mini"
          @click="close"
        >关闭</el-button>
      </el-row>
    </el-row>
  </div>
</template>

<script>
  import {selectDictLabel} from "@/utils/sinoutils";
  import {getServiceProcess, saveServiceProcess,} from "@/api/provider/project"
  import breakOff from "../../claimsHandle/common/modul/breakOff";

  export default {
    name: "workFlow",
    data() {
      const checkServiceApply = (rules, value, callback) => {
        if ((this.serviceapply.cancelFlag == undefined || this.serviceapply.cancelFlag == "") && (this.serviceapply.sendmessageFlag == undefined || this.serviceapply.sendmessageFlag == "")
            && (this.serviceapply.updateFlag == undefined || this.serviceapply.updateFlag == "") && (this.serviceapply.selfused == undefined || this.serviceapply.selfused == "")){
          callback(new Error('至少填一项'))
        } else {
          callback()
        }
      }
      const checkUpdateNum = (rules, value, callback) => {
        if (this.serviceapply.updateFlag == '01' && (this.serviceapply.updateNum == undefined || this.serviceapply.updateNum == "")){
          callback(new Error('请输入变更次数'))
        } else {
          callback()
        }
      }
      const chaeckAllocation = (rules, value, callback) => {
        if ((this.allocationForm.cancelFlag == undefined || this.allocationForm.cancelFlag == "") && (this.allocationForm.sendmessageFlag == undefined || this.allocationForm.sendmessageFlag == "") && (this.allocationForm.distribute == undefined || this.allocationForm.distribute =="")){
          callback(new Error('至少填一项'))
        } else {
          callback()
        }
      }
      const checkOrder = (rules, value, callback) => {
        if ((this.orderForm.cancelFlag == undefined || this.orderForm.cancelFlag == "") && (this.orderForm.sendmessageFlag == undefined || this.orderForm.sendmessageFlag == "") && (this.orderForm.aging == undefined || this.orderForm.aging == "")){
          callback(new Error('至少填一项'))
        } else {
          callback()
        }
      }
      const checkImplement = (rules, value, callback) => {
        if (this.implement.cancelFlag == undefined && this.implement.sendmessageFlag == undefined && this.implement.recordFlag == undefined && this.implement.aging == undefined){
          callback(new Error('至少填一项'))
        } else {
          callback()
        }
      }
      const checkFrequency = (rules,value, callback) => {
        if (this.implement.recordFlag == '01' && (this.implement.frequency == undefined || this.implement.frequency == "")){
          callback(new Error("请输入记录次数限制次数"))
        } else {
          callback()
        }
      }
      return {
        serviceapplyRules: {
          cancelFlag: [{validator: checkServiceApply, trigger: 'change'}],
          sendmessageFlag: [{validator: checkServiceApply, trigger: 'change'}],
          updateFlag: [{validator: checkServiceApply, trigger: 'change'}],
          selfused: [{validator: checkServiceApply, trigger: 'change'}],
          updateNum: [{validator: checkUpdateNum, trigger: 'blur'},
                      {pattern: /^((?!0)\d{1,2})$/, message: '请输入数字(大于0，小于100)', trigger: 'blur' },
                      ]
        },
        applyfieldListRule: {
          businessName:  [{required: true, message: '不能为空！', trigger: 'change'}],
          recondType:  [{required: true, message: '不能为空！', trigger: 'change'}],
          isrecond: [{required: true, message: '不能为空！', trigger: 'change'}],
          remark: [{max: 30,message: '长度不超过30', trigger:'change'}],
        },
        allocationFormRule: {
          cancelFlag: [{validator: chaeckAllocation, trigger: 'change'}],
          sendmessageFlag: [{validator: chaeckAllocation, trigger: 'change'}],
          distribute: [{validator: chaeckAllocation, trigger: 'change'}],
        },
        orderFormRule: {
          cancelFlag: [{validator: checkOrder, trigger: 'change'}],
          sendmessageFlag: [{validator: checkOrder, trigger: 'change'}],
          aging: [{validator: checkOrder, trigger: 'blur'},
                  {pattern:/^((?!0)\d{1,3})$/, message: '请输入数字(大于0，小于1000)', trigger: 'blur' },
                  ],
        },
        implementRule: {
          cancelFlag: [{validator: checkImplement, trigger: 'change'}],
          sendmessageFlag: [{validator: checkImplement, trigger: 'change'}],
          recordFlag: [{validator: checkImplement, trigger: 'change'}],
          frequency: [{validator: checkFrequency, trigger: 'blur'},
                      {pattern:/^((?!0)\d{1,2})$/, message: '请输入数字(大于0，小于100)', trigger: 'blur' },
                      ],
          aging: [{validator: checkImplement, trigger: 'blur'},
                  {pattern:/^((?!0)\d{1,3})$/, message: '请输入数字(大于0，小于1000)', trigger: 'blur'},
                  ],
        },
        imfieldListRule: {
          businessName:  [{required: true, message: '不能为空！', trigger: 'change'}],
          recondType:  [{required: true, message: '不能为空！', trigger: 'change'}],
          isrecond: [{required: true, message: '不能为空！', trigger: 'change'}],
          remark: [{max: 30,message: '长度不超过30', trigger:'blur'}],
        },
        userRule: {
          sendmessageFlag: [{required: true, message: "不能为空", trigger: "change"}]
        },
        // 遮罩层
        loading: true,
        servicecode: undefined,
        // 基础数据
        serviceapply: {
          processNode: '01',
          cancelFlag: undefined,
          sendmessageFlag: undefined,
          updateFlag: undefined,
          updateNum: undefined,
          distribute: undefined,
          aging: undefined,
          selfused: undefined,
          recordFlag: undefined,
          frequency: undefined,
          status: 'Y'
        },
        allocationForm: {
          processNode: '02',
          cancelFlag: undefined,
          sendmessageFlag: undefined,
          updateFlag: undefined,
          updateNum: undefined,
          distribute: undefined,
          aging: undefined,
          selfused: undefined,
          recordFlag: undefined,
          frequency: undefined,
          status: 'Y'
        },
        orderForm: {
          processNode: '03',
          cancelFlag: undefined,
          sendmessageFlag: undefined,
          updateFlag: undefined,
          updateNum: undefined,
          distribute: undefined,
          aging: undefined,
          selfused: undefined,
          recordFlag: undefined,
          frequency: undefined,
          status: 'Y'
        },
        implement: {
          processNode: '04',
          cancelFlag: undefined,
          sendmessageFlag: undefined,
          updateFlag: undefined,
          updateNum: undefined,
          distribute: undefined,
          aging: undefined,
          selfused: undefined,
          recordFlag: undefined,
          frequency: undefined,
          status: 'Y'
        },
        userForm: {
          processNode: '05',
          cancelFlag: undefined,
          sendmessageFlag: undefined,
          updateFlag: undefined,
          updateNum: undefined,
          distribute: undefined,
          aging: undefined,
          selfused: undefined,
          recordFlag: undefined,
          frequency: undefined,
          status: 'Y'
        },
        // 消息模板 置亮置灰
        ableI: true,
        ableII: true,
        ableIII: true,
        ableIV: true,
        ableV: true,
        // 变更次数显示与否
        showUpdateNum: true,
        // 记录信息限制 现实与否
        showFrequency: true,
        // 表格数据
        applyfieldList: {
          form: []
        },
        imfieldList: {
          form: []
        },
        // 数据字典
        booleanOptions: [],
        fieldNameOptions: [],
        recondTypeOptions: [],
        imFieldNameOptions: [],
      };
    },
    created() {
      const servicecode = this.$route.params && this.$route.params.projectno;
      this.servicecode = servicecode;
      this.getProjectProcess(this.servicecode);
      this.getDicts("sys_yes_no").then(response => {
        this.booleanOptions = response.data;
      });
      this.getDicts("service_apply_field").then(response => {
        this.fieldNameOptions = response.data;
      });
      this.getDicts("entry_mode").then(response => {
        this.recondTypeOptions = response.data;
      });
      this.getDicts("service_imple_field").then(response => {
        this.imFieldNameOptions = response.data;
      });
    },
    methods: {
      // 根据服务项目编码获取项目流程配置
      getProjectProcess(servicecode) {
        getServiceProcess(servicecode).then(response => {
          // 后台返回数据前端页面展示
          if (response.code == 200) {
            if (response.data.processList != null) {
              response.data.processList.forEach(item => {
                if (item.processNode == "01") {
                  if (item.cancelFlag != "" && item.cancelFlag != null){
                    this.serviceapply.cancelFlag = item.cancelFlag;
                  }
                  if (item.sendmessageFlag != "" && item.sendmessageFlag != null){
                    this.serviceapply.sendmessageFlag = item.sendmessageFlag;
                  }
                  if (item.updateFlag != "" && item.updateFlag != null){
                    this.serviceapply.updateFlag = item.updateFlag;
                  }
                  if (this.serviceapply.updateFlag == '02'){
                    this.showUpdateNum = false
                  }
                  if (item.updateNum != "" && item.updateNum != null){
                    this.serviceapply.updateNum = item.updateNum;
                  }
                  if (item.selfused != "" && item.selfused != null){
                    this.serviceapply.selfused = item.selfused;
                  }
                  this.serviceapply.distribute = item.distribute;
                  this.serviceapply.aging = item.aging;
                  this.serviceapply.recordFlag = item.recordFlag;
                  this.serviceapply.frequency = item.frequency;
                }
                if (item.processNode == "02") {
                  if (item.cancelFlag != "" && item.cancelFlag != null) {
                    this.allocationForm.cancelFlag = item.cancelFlag;
                  }
                  if (item.sendmessageFlag != "" && item.sendmessageFlag != null) {
                    this.allocationForm.sendmessageFlag = item.sendmessageFlag;
                  }
                  this.allocationForm.updateFlag = item.updateFlag;
                  this.allocationForm.updateNum = item.updateNum;
                  this.allocationForm.selfused = item.selfused;
                  if (item.distribute != "" && item.distribute != null){
                    this.allocationForm.distribute = item.distribute;
                  }
                  this.allocationForm.aging = item.aging;
                  this.allocationForm.recordFlag = item.recordFlag;
                  this.allocationForm.frequency = item.frequency;
                }
                if (item.processNode == "03") {
                  if (item.cancelFlag != "" && item.cancelFlag != null) {
                    this.orderForm.cancelFlag = item.cancelFlag;
                  }
                  if (item.sendmessageFlag != "" && item.sendmessageFlag != null) {
                    this.orderForm.sendmessageFlag = item.sendmessageFlag;
                  }
                  this.orderForm.updateFlag = item.updateFlag;
                  this.orderForm.updateNum = item.updateNum;
                  this.orderForm.selfused = item.selfused;
                  this.orderForm.distribute = item.distribute;
                  this.orderForm.aging = item.aging;
                  this.orderForm.recordFlag = item.recordFlag;
                  this.orderForm.frequency = item.frequency;
                }
                if (item.processNode == "04") {
                  if (item.cancelFlag != "" && item.cancelFlag != null) {
                    this.implement.cancelFlag = item.cancelFlag;
                  }
                  if (item.sendmessageFlag != "" && item.sendmessageFlag != null) {
                    this.implement.sendmessageFlag = item.sendmessageFlag;
                  }
                  this.implement.updateFlag = item.updateFlag;
                  this.implement.updateNum = item.updateNum;
                  this.implement.selfused = item.selfused;
                  this.implement.distribute = item.distribute;
                  this.implement.aging = item.aging;
                  if (item.recordFlag != "" && item.recordFlag != null) {
                    this.implement.recordFlag = item.recordFlag;
                  }
                  if (this.implement.recordFlag == '02'){
                    this.showFrequency = false
                  }
                  this.implement.frequency = item.frequency;
                }
                if (item.processNode == "05") {
                  if (item.sendmessageFlag != "" && item.sendmessageFlag != null ){
                    this.userForm.sendmessageFlag = item.sendmessageFlag;
                  }
                  this.userForm.cancelFlag = item.cancelFlag;
                  this.userForm.updateFlag = item.updateFlag;
                  this.userForm.updateNum = item.updateNum;
                  this.userForm.selfused = item.selfused;
                  this.userForm.distribute = item.distribute;
                  this.userForm.aging = item.aging;
                  this.userForm.recordFlag = item.recordFlag;
                  this.userForm.frequency = item.frequency;
                }
              });
              if (response.data.fieldList != null) {
                response.data.fieldList.forEach(item => {
                  if (item.processNode == "01") {
                    this.applyfieldList.form.push(item);
                  }
                  if (item.processNode == "04") {
                    this.imfieldList.form.push(item);
                  }
                })
              }
            }


          }
        })
      },
      // 基础信息-是否发送消息 监听
      selectApplyMess() {
        if (this.serviceapply.sendmessageFlag == '02') {
          this.ableI = true
        } else {
          this.ableI = false
        }
      },
      selectAllocationMess() {
        if (this.allocationForm.sendmessageFlag == '02') {
          this.ableII = true
        } else {
          this.ableII = false
        }
      },
      selectOrderMess() {
        if (this.orderForm.sendmessageFlag == '02') {
          this.ableIII = true
        } else {
          this.ableIII = false
        }
      },
      selectImplementMess() {
        if (this.implement.sendmessageFlag == '02') {
          this.ableIV = true
        } else {
          this.ableIV = false
        }
      },
      selectUserFormMess() {
        if (this.userForm.sendmessageFlag == '02') {
          this.ableV = true
        } else {
          this.ableV = false
        }
      },
      // 变更次数显示与否
      selectUpdateFlag(){
        if (this.serviceapply.updateFlag == '02'){
          this.showUpdateNum = false
        } else {
          this.showUpdateNum = true
        }
      },
      // 记录次数限制 显示与否
      selectRecordFlag(){
        if (this.implement.recordFlag == '02'){
          this.showFrequency = false
        } else {
          this.showFrequency = true
        }
      },
      // 消息模板
      MessageTemplate() {
        this.$router.push({path: '/basicInfo/projectManagement/messageTemp'});
      },
      // 服务申请-业务字段 添加
      addApplyFieldOneRow() {
        var serviceInfoLength = this.applyfieldList.form.length;
        if (serviceInfoLength == 0) {
          const row = {
            serviceCode: this.servicecode,
            processNode: '01',
            businessName: '',
            recondType: '',
            isrecond: '',
            reamrk: '',
            status: 'Y',
            show: true
          };
          this.applyfieldList.form.push(row);
        } else {
          if (this.applyfieldList.form[serviceInfoLength - 1].businessname != '') {
            const row = {
              serviceCode: this.servicecode,
              processNode: '01',
              businessName: '',
              recondType: '',
              isrecond: '',
              reamrk: '',
              status: 'Y',
              show: true
            };
            this.applyfieldList.form.push(row);
          } else {
            this.$message({
              showClose: true,
              message: '请确认业务字段已经选择！',
              type: 'error'
            });
          }
        }
      },
      // 服务申请-业务字段 删除
      deleteApplyFieldOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.applyfieldList.form.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      addServiceImFieldOneRow() {
        const imFieldLength = this.imfieldList.form.length;
        if (imFieldLength == 0) {
          const row = {
            serviceCode: this.servicecode,
            processNode: '04',
            businessName: '',
            recondType: '',
            isrecond: '',
            reamrk: '',
            status: 'Y',
            show: true,
          };
          this.imfieldList.form.push(row);
        } else {
          if (this.imfieldList.form[imFieldLength - 1].name != '') {
            const row = {
              serviceCode: this.servicecode,
              processNode: '04',
              businessName: '',
              recondType: '',
              isrecond: '',
              reamrk: '',
              status: 'Y',
              show: true
            };
            this.imfieldList.form.push(row);
          } else {
            this.$message({
              showClose: true,
              message: '请确认业务字段已经选择！',
              type: 'error'
            });
          }
        }
      },
      deleteServiceImFieldOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.imfieldList.form.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      // 调用封装方法 显示下拉框名称  注意：本方法只适用于配置在字典中的 TODO
      getRecondTypeName(recondtype) {
        // return selectDictLabel(this.supsettlementtype, recondtype);
      },
      // 暂存
      tempStorage() {
        if (this.serviceapply.updateNum != undefined && this.serviceapply.updateNum != ""){
          const reg1 = /^((?!0)\d{1,2})$/;
          if (!reg1.test(this.serviceapply.updateNum)){
            return this.$message.warning(
              "数字格式输入不正确，请重新输入！"
            )
          }
        }
        if(this.orderForm.aging != undefined && this.orderForm.aging != ""){
          const reg2 = /^((?!0)\d{1,3})$/;
          if (!reg2.test(this.orderForm.aging)){
            return this.$message.warning(
              "数字格式输入不正确，请重新输入！"
            )
          }
        }
        if (this.implement.frequency != undefined && this.implement.frequency != ""){
          const reg1 = /^((?!0)\d{1,2})$/;
          if (!reg1.test(this.implement.frequency)){
            return this.$message.warning(
              "数字格式输入不正确，请重新输入！"
            )
          }
        }
        if(this.implement.aging != undefined && this.implement.aging !=""){
          const reg2 = /^((?!0)\d{1,3})$/;
          if (!reg2.test(this.implement.aging)) {
            return this.$message.warning(
              "数字格式输入不正确，请重新输入！"
            )
          }
        }
        // 设置状态为暂存 01
        const status = "01";
        const processList = [this.serviceapply, this.allocationForm, this.orderForm, this.implement, this.userForm];
        const fieldList = [];
        this.applyfieldList.form.forEach(item => fieldList.push(item));
        this.imfieldList.form.forEach(item => fieldList.push(item));
        saveServiceProcess(this.servicecode, status, processList, fieldList).then(response => {
          if (response.code == 200) {
            this.msgSuccess("保存成功");
          } else {
            this.msgError(response.msg);
          }
        })
      },

      // 保存并发布
      projectPublish() {
        var flag = false;
        var msg = "";
        this.$refs["serviceapply"].validate(valid => {
          if (valid) {
            this.serviceapply.serviceCode = this.servicecode;
            flag = true
          }
        });
        if (flag){
          this.$refs["applyfieldList"].validate(valid => {
            flag = valid;
            if (valid){
              if (this.applyfieldList.form.length == 0){
                msg = "流程节点-服务申请-业务字段 请填写信息！";
                return this.$message.warning(
                  "流程节点-服务申请-业务字段 至少输入一条！"
                )
                flag = false
                return flag
              }
            }
          })
        }
        if (flag) {
          this.$refs["allocationForm"].validate(valid => {
            flag = valid
            if (valid) {
              this.allocationForm.serviceCode = this.servicecode;
            }
          })
        }
        if (flag) {
          this.$refs["orderForm"].validate(valid => {
            flag = valid
            if (valid) {
              this.orderForm.serviceCode = this.servicecode;
            }
          })
        }
        if (flag) {
          this.$refs["implement"].validate(valid => {
            console.log("implement:", valid)
            flag = valid
            if (valid) {
              this.implement.serviceCode = this.servicecode;
            }
          })
        }
        if (flag){
          this.$refs["imfieldList"].validate(valid => {
            flag = valid
            if (valid) {
              if (this.imfieldList.form.length == 0){
                msg = "流程节点-服务实施-业务字段 请填写字段！";
                return this.$message.warning(
                  "流程节点-服务实施-业务字段 至少输入一条！"
                )
                flag = false
                return flag
              }
            }
          })
        }
        if (flag) {
          this.$refs["userForm"].validate(valid => {
            console.log("userForm:", valid)
            flag = valid
            if (valid) {
              this.userForm.serviceCode = this.servicecode;
            }
          })
        }
        if (!flag && msg == ""){
          return this.$message.warning(
              "请先录入必录项！"
          )
        }
        if (flag) {
          // 设置状态为发布 02
          const status = "02";
          const processList = [this.serviceapply, this.allocationForm, this.orderForm, this.implement, this.userForm];
          const fieldList = [];
          this.applyfieldList.form.forEach(item => fieldList.push(item));
          this.imfieldList.form.forEach(item => fieldList.push(item));
          saveServiceProcess(this.servicecode, status, processList, fieldList).then(response => {
            if (response.code == 200) {
              this.msgSuccess("发布成功");
            } else {
              this.msgError(response.msg);
            }
          })
        }
      },
      // 关闭
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push('/basic-info/projectManagement/projectlist');
      }
    }
  };
</script>

<style scoped>
  .department-style .el-form-item {
    display: inline-flex !important;
  }
</style>
