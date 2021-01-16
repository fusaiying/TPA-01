<template>
  <div class="container">
    <el-card class="box-card topCard">
      <el-collapse>
        <el-collapse-item name="1">
          <template slot="title">
            <span style="font-size: 16px;font-weight: 400;" >供应商合同信息</span>
          </template>
          <el-form :inline="true" :model="supplierInfo" label-position="right" label-width="130px" class="demo-form-inline" size="small">
            <el-form-item class="list_item" style="width: 32%" label="供应商编码：">
              <span>{{ supplierInfo.suppliercode }}</span>
            </el-form-item>
            <el-form-item class="list_item" style="width: 32%" label="供应商类型：">
              <span>{{getSupplierTypeName(supplierInfo.suppliertype)}}</span>
            </el-form-item>
            <el-form-item class="list_item" style="width: 32%" label="是否有效：">
              <span>{{getSupplierStateName(supplierInfo.state)}}</span>
            </el-form-item>
            <el-form-item class="list_item" style="width: 32%" label="供应商中文名称：">
              <span>{{ supplierInfo.chname }}</span>
            </el-form-item>
            <el-form-item class="list_item" style="width: 32%" label="供应商英文名称：">
              <span>{{ supplierInfo.enname }}</span>
            </el-form-item>
            <el-form-item class="list_item" style="width: 32%" label="所属地区：">
              <span>{{ supplierInfo.district }}</span>
            </el-form-item>
            <el-form-item class="list_item" :style="{width:'100%'}" label="详细地址：">
              <span>{{ supplierInfo.address }}</span>
            </el-form-item>
            <el-form-item class="list_item" :style="{width:'100%'}" label="备注：">
              <span>{{ supplierInfo.remarks }}</span>
            </el-form-item>
          </el-form>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <el-card class="box-card1">
      <p style="font-size:16px;">合同列表</p>
      <el-table size="mini" :data="contractData" :header-cell-style="{color:'black',background:'#f8f8ff'}" style="width: 100%" @current-change="clickChange">
        <el-table-column prop="contractcode" label="合同编号" align="center" show-overflow-tooltip >
        </el-table-column>
        <el-table-column prop="contractname" label="合同名称" align="center" show-overflow-tooltip >
        </el-table-column>
        <el-table-column prop="startdate" label="合同生效日期" align="center" show-overflow-tooltip>
          <template  slot-scope="scope">
            {{scope.row.startdate.substring(0,10)}}
          </template>
        </el-table-column>
        <el-table-column prop="enddate" label="合同终止日期" align="center" show-overflow-tooltip>
          <template  slot-scope="scope">
            {{scope.row.enddate.substring(0,10)}}
          </template>
        </el-table-column>
        <el-table-column prop="enddate" label="合同签订日期" align="center" show-overflow-tooltip>
          <template  slot-scope="scope">
            {{scope.row.signaturedate.substring(0,10)}}
          </template>
        </el-table-column>
        <el-table-column prop="contstate" label="合同状态" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.contstate=='01'">有效</span>
            <span v-if="scope.row.contstate=='02'">无效</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button size="small" type="text"  @click="editClick(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block"  style="margin-top: 10px; text-align: right;">
        <el-pagination
          background=""
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 30, 50, 100]"
          :page-size="pageSize"
          layout=" prev,pager, next,sizes,jumper"
          :total="constractList.length">
        </el-pagination>
      </div>
    </el-card>

    <div class="box-card1" style="margin-top: 20px">
      <span style="position: absolute; right: 20px; top: 20px;">
        <el-button type="primary"
                    size="mini"
                    v-on:click="amendRule"
                    :disabled="this.cansubmit||this.isableedit"><a style="margin-left: -4px">{{amendButton}}</a>
        </el-button>
        <el-button type="primary"
                    size="mini"
                    v-on:click="reset"
                    :disabled="this.cansubmit||this.isableedit"><a style="margin-left: -4px">重置</a>
        </el-button>
        <el-button type="primary"
                    size="mini"
                    v-on:click="jump"><a style="margin-left: -4px">取消</a>
        </el-button>
      </span>
      <el-card style="margin-bottom: 40px;">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="合同信息" name="first">
            <div style="color: #303133;">
              <p style="margin: 20px; font-size: 16px;">合同基本信息</p>
              <el-form :rules="billingRules" :model="contractInfo">
                <el-row :gutter="2">
                  <el-col :span="8">
                    <div class="grid-content">
                      <el-row :gutter="20">
                        <el-col :span="10">
                          <div class="grid-content0" style="float: right">合同编码：  </div>
                        </el-col>
                        <el-col :span="14">
                          <el-input :disabled="cansubmit || isableedit"
                                    v-model="contractInfo.contractcode"
                                    :rules='ConstractRules.contractcode'
                                    size="mini" placeholder="请输入" />
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content">
                      <el-row :gutter="20">
                        <el-col :span="10">
                          <div class="grid-content0" style="float: right">合同名称：  </div>
                        </el-col>
                        <el-col :span="14">
                          <el-input :disabled="cansubmit || isableedit" v-model="contractInfo.contractname" style="width: 200px" size="mini" placeholder="请输入" />
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content">
                      <el-row :gutter="20">
                        <el-col :span="10">
                          <div class="grid-content0" style="float: right">合同状态：  </div>
                        </el-col>
                        <el-col :span="14">
                          <div>
                            <el-select :disabled="cansubmit || isableedit" v-model="contractInfo.contstate" placeholder="请选择" size="mini">
                              <el-option
                                v-for="item in contstatestatus"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                              </el-option>
                            </el-select>
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                </el-row>
                <br>
                <el-row :gutter="2">
                  <el-col :span="8">
                    <div class="grid-content">
                      <el-row :gutter="20">
                        <el-col :span="10">
                          <div class="grid-content0" style="float: right">合同起止日期：  </div>
                        </el-col>
                        <el-col :span="14">
                          <div class="purple0 input0">
                            <div class="block">
                              <el-date-picker
                                :disabled="cansubmit || isableedit"
                                style="width: 100%"
                                size="mini"
                                v-model="fulltime"
                                value-format="yyyy-MM-dd"
                                type="daterange"
                                align="right"
                                @input="textClick"
                                range-separator="~"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                              </el-date-picker>
                            </div>
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content">
                      <el-row :gutter="20">
                        <el-col :span="10">
                          <div class="grid-content0" style="float: right">签订日期：  </div>
                        </el-col>
                        <el-col :span="14">
                          <div class="block">
                            <el-date-picker
                              :disabled="cansubmit || isableedit"
                              style="width: 205px"
                              v-model="contractInfo.signaturedate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择日期"
                              size="mini">
                            </el-date-picker>
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                </el-row>
                <br/>
                <el-row :gutter="20">
                  <el-col :span="24">
                    <div class="grid-content" style="margin-left: -3%">
                      <el-row :gutter="20">
                        <el-col :span="4">
                          <div class="grid-content0" style="float: right">合同备注： </div>
                        </el-col>
                        <el-col :span="19">
                          <el-input :disabled="cansubmit || isableedit" v-model="contractInfo.remarks"  size="mini" placeholder="请输入" />
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                </el-row>
                <br>
            <div>
              <div style="margin-bottom: -10px;">
                <span style="display: inline-block; margin: 10px 20px; font-size: 16px;">合同附件信息</span>
                <span style="margin-left: 68%">
                  <el-button type="primary" @click="download" size="mini">下载附件</el-button>
                  <el-button type="primary" :disabled="this.cansubmit||this.isableedit" @click="del" size="mini">删除附件</el-button>
                </span>
              </div>
              <el-divider></el-divider>
                <el-table
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  ref="singleTable"
                  size="mini"
                  :data="contractAppendicesInfo0"
                  style="width: 96%; margin: 0 auto;"
                  highlight-current-row
                  @current-change="handleSelectionChange"
                  @row-click="chooseone">
                  <el-table-column label="" align="center" width="65px">
                    <template slot-scope="scope">
                      <el-radio  v-model="radio" :label="scope.row.attachmentno">
                        <span class="el-radio__label"></span>
                      </el-radio>
                    </template>
                  </el-table-column>
                  <el-table-column label="序号" width="80" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <span>{{scope.row.attachmentno}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column label="附件名称" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <span>{{scope.row.name}}</span>
                    </template>
                  </el-table-column>
                </el-table>
                <el-form size="mini">
                  <el-form-item class="list_item" label="附件备注:" v-show=" this.contractAppendicesInfo0.length>0">
                    <el-input :disabled="cansubmit || isableedit" v-model="attRemarkes" tpye="textarea" placeholder="请输入"/>
                  </el-form-item>
                </el-form>
              </div>
              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="业务规则" name="second">
            <div>
              <p style="margin: 20px; font-size: 16px;">计费规则</p>
              <el-form :rules="billingRules" :model="accountingRules" label-width="110px">
                  <el-row>
                    <el-col :span="8">
                      <el-form-item class="list_item" prop="suppliertype" label="调查类型：">
                        <el-select v-model="accountingRules.suppliertype"
                                    multiple
                                    placeholder="请选择"
                                    size="mini"
                                    :disabled="cansubmit||isableedit"
                                    filterable
                                    allow-create
                                    collapse-tags
                                    default-first-option>
                          <el-option
                            v-for="item in accountingRulesstatus"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item class="list_item" prop="limitation" label="调查时效：">
                        <el-select v-model="accountingRules.limitation"
                            multiple
                            placeholder="请选择"
                            size="mini"
                            :disabled="cansubmit||isableedit"
                            filterable
                            :remove-ta="dealonepeice"
                            allow-create
                            collapse-tags
                            default-first-option>
                          <el-option
                            v-for="item in limitationstatus"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item class="list_item" prop="delegatemode" label="委托方式：">
                        <el-select :disabled="cansubmit || isableedit"
                                    v-model="accountingRules.delegatemode"
                                    multiple
                                    placeholder="请选择" size="mini"
                                    filterable
                                    allow-create
                                    collapse-tags
                                    default-first-option>
                          <el-option
                            v-for="item in delegatemodestatus"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item class="list_item" prop="billingmode" label="计费方式：">
                        <el-select :disabled="cansubmit ||isableedit"
                                    v-model="accountingRules.billingmode"
                                    multiple
                                    placeholder="请选择"
                                    size="mini"
                                    filterable
                                    allow-create
                                    collapse-tags
                                    default-first-option>
                          <el-option
                            v-for="item in billingmodestatus"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item class="list_item" prop="casepointtype" label="案件点类型：">
                        <el-select :disabled="cansubmit || isableedit"
                                    v-model="accountingRules.casepointtype"
                                    multiple placeholder="请选择" size="mini"
                                    filterable
                                    allow-create
                                    collapse-tags
                                    default-first-option>
                          <el-option
                            v-for="item in casepointtypestatus"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item class="list_item" prop="targetarea" label="案件地区：">
                        <el-cascader :options="targetareastatus"
                                      :props="{ multiple: true }"
                                      size="mini"
                                      :disabled="cansubmit || isableedit"
                                      v-model="accountingRules.targetarea"
                                      collapse-tags
                                      default-first-option></el-cascader>
                      </el-form-item>
                    </el-col>
                  </el-row>
              </el-form>
            </div>
            <!--时效添加-->
            <div>
              <p style="margin: 20px; font-size: 16px;">时效规则</p>
                <el-table
                  v-show="showTime"
                  :data="addtimeRules"
                  style="width: 100%"
                  size="mini">
                  <el-table-column
                    align="center"
                    label="时效类型"
                    style="width: 65px">
                    <template slot-scope="scope">
                      <el-select @change="selected(scope.row.tasktype)" size="mini" v-model="scope.row.tasktype" placeholder="请选择">
                        <el-option
                          v-for="item in tasktypestatus"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                          :disabled="item.disabled">
                        </el-option>
                      </el-select>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="分配时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation01" @keyup.native="proving1($event,scope.row.limitation01)"  placeholder=""></el-input>
                      <span style="font-size: 12px;margin-left: 9px">x</span>
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation02" @keyup.native="proving1($event,scope.row.limitation02)"  placeholder=""></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="接收时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation03" @keyup.native="proving1($event,scope.row.limitation03)"  placeholder=""></el-input>
                      <span style="font-size: 12px;margin-left: 9px">x</span>
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation04" @keyup.native="proving1($event,scope.row.limitation04)"  placeholder=""></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="处理时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-input  size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation05" @keyup.native="proving1($event,scope.row.limitation05)"  placeholder=""></el-input>
                      <span style="font-size: 12px;margin-left: 9px">x</span>
                      <el-input  size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation06" @keyup.native="proving1($event,scope.row.limitation06)"  placeholder=""></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="初审时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation07" @keyup.native="proving1($event,scope.row.limitation07)"  placeholder=""></el-input>
                      <span style="font-size: 12px;margin-left: 9px">x</span>
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation08" @keyup.native="proving1($event,scope.row.limitation08)"  placeholder=""></el-input>
                    </template>

                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="审核时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation09" @keyup.native="proving1($event,scope.row.limitation09)"  placeholder=""></el-input>
                      <span style="font-size: 12px;margin-left: 9px">x</span>
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation10" @keyup.native="proving1($event,scope.row.limitation10)"  placeholder=""></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="调查总时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation11" @keyup.native="proving1($event,scope.row.limitation11)"  placeholder=""></el-input>
                      <span style="font-size: 12px;margin-left: 9px">x</span>
                      <el-input size="small" style="width: 35px;padding-left:6px" v-model="scope.row.limitation12" @keyup.native="proving1($event,scope.row.limitation12)"  placeholder=""></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    prop="warningratio"
                    label="预警时效比例"
                    width="110px">
                    <template scope="scope">
                      <el-input v-model="scope.row.warningratio"  @keyup.native="proving2($event,scope.row.warningratio)"size="mini" style="width: 60px"></el-input>%
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    prop="limitationunit"
                    label="时效单位"
                    show-overflow-tooltip>
                  </el-table-column>
                  <el-table-column
                    prop="address"
                    label="操作"
                  >
                    <template scope="scope">
                      <el-button @click="cancelClick(scope.$index)" type="text" size="small">取消</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <!-- 时效规则展示-->
                <el-table
                  :show-header='showTimeHeader'
                  :data="prescriptionRules"
                  style="width: 100%, padding: 0;"
                  size="mini">
                  <el-table-column
                    align="center"
                    label="时效类型"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      <span v-if="scope.row.tasktype=='01'">特急（1X24h）</span>
                      <span v-if="scope.row.tasktype=='02'">紧急（3X24h）</span>
                      <span v-if="scope.row.tasktype=='03'">非紧急（4X24h）</span>
                      <span v-if="scope.row.tasktype=='04'">非特急（5X24h）</span>
                      <span v-if="scope.row.tasktype=='05'">非紧急（6X24h）</span>
                      <span v-if="scope.row.tasktype=='06'">非紧急（7X24h）</span>
                      <span v-if="scope.row.tasktype=='07'">非紧急（8X24h）</span>
                      <span v-if="scope.row.tasktype=='08'">非紧急（9X24h）</span>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="分配时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      {{scope.row.allotlimitation1}}X{{scope.row.allotlimitation2}}
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="接收时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      {{scope.row.acceptlimitation1}}X{{scope.row.acceptlimitation2}}
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="处理时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      {{scope.row.deallimitation1}}X{{scope.row.deallimitation2}}
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="初审时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      {{scope.row.triallimitation1}}X{{scope.row.triallimitation2}}
                    </template>

                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="审核时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      {{scope.row.auditlimitation1}}X{{scope.row.auditlimitation2}}
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="调查总时效"
                    show-overflow-tooltip>
                    <template slot-scope="scope">
                      {{scope.row.totallimitation1}}X{{scope.row.totallimitation2}}
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    prop="warningratio"
                    label="预警时效比例"
                    width="110px">
                    <template scope="scope">
                      {{scope.row.warningratio}}<span>%</span>
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    prop="limitationunit"
                    label="时效单位"
                    show-overflow-tooltip>
                  </el-table-column>
                  <el-table-column
                    prop="address"
                    label="操作"
                  >
                    <template scope="scope">
                      <el-button @click="delClick0(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div align="center">
                  <el-button  @click="timeRuleAdd" size="small" style="margin-top:0.3%;font-size:14px;width: 100%;border-style: dashed"> + 添加</el-button>
                </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </div>
</template>

<script  type="text/javascript" >
  import {get} from '@/api/dictDetail'
  import {getAddress} from '@/api/investigate/supplierManagement'
  import {deletelimition,limitationAdd,getBySupplierCodeInfo,getByConstractCodeInfo,updateConstract,updateBusiness} from '@/api/investigate/supplierManagement'
  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        /*计费规则改变时获取计费规则中的值*/
        prices:'',
        /*无效不可编辑*/
        isableedit:false,
        /*供应商信息*/
        supplierInfo:'',
        contractcode:'',
        /*合同列表*/
        constractList:[],
        limitationtype:'',
        creatorStart:'',
        creatorEnd:'',
        warningratio:'',
        limitationunit:'',
        /*合同提交信息*/
        cons:null,
        rulesDo:null,
        arr1:[],
        cansubmit:false,
        subConstract:false,
        subRules:false,
        fulltime:[],
        suppliertypes:[],
        supplierstates:[],

        /*点击编辑，带出四项信息*/
        contractInfo:{
          contractname: null,
          contstate: '01',
          createdate: null,
          creator: null,
          enddate: null,
          operatedate: null,
          operator: null,
          remarks: null,
          startdate: null,
          signaturedate:null,
          suppliercode: null
        },//合同信息
        contractAppendicesInfo:'',//合同附件信息
        contractAppendicesInfo0:[],
        prescriptionRules:'',//时效规则
        accountingRules:{
          billingmode: null,
          casepointtype: null,
          contractno: null,
          createdate: '2020-01-17',
          creator: 'test',
          delegatemode: null,
          operatedate: null,
          operator: null,
          price: 0,//待确认
          pricetype: '1',//待确认
          suppliertype: '1',//待确认
          targetarea: null
        },
        limitList:[{limitationno:"",
          contractno:"",
          tasktype:"",
          limitationtype:	"01",
          limitation1:"",
          limitation2:"",
          limitationunit:"",
          warningratio:"",
          creator:"",
          createdate:"",
          operator:"",
          operatedate:""},
          {limitationno:"",
            contractno:"",
            tasktype:"",
            limitationtype:	"02",
            limitation1:"",
            limitation2:"",
            limitationunit:"",
            warningratio:"",
            creator:"",
            createdate:"",
            operator:"",
            operatedate:""},
          {limitationno:"",
            contractno:"",
            tasktype:"",
            limitationtype:	"03",
            limitation1:"",
            limitation2:"",
            limitationunit:"",
            warningratio:"",
            creator:"",
            createdate:"",
            operator:"",
            operatedate:""},
          {limitationno:"",
            contractno:"",
            tasktype:"",
            limitationtype:	"04",
            limitation1:"",
            limitation2:"",
            limitationunit:"",
            warningratio:"",
            creator:"",
            createdate:"",
            operator:"",
            operatedate:""},
          {limitationno:"",
            contractno:"",
            tasktype:"",
            limitationtype:	"05",
            limitation1:"",
            limitation2:"",
            limitationunit:"",
            warningratio:"",
            creator:"",
            createdate:"",
            operator:"",
            operatedate:""},
          {limitationno:"",
            contractno:"",
            tasktype:"",
            limitationtype:	"06",
            limitation1:"",
            limitation2:"",
            limitationunit:"",
            warningratio:"",
            creator:"",
            createdate:"",
            operator:"",
            operatedate:""}],
        limitation:{
          limitationno:'',
          contractno:'',
          tasktype:'',
          limitationtype:'',
          limitationunit:'',
          warningratio:'',
          limitation01:'',
          limitation02:'',
          limitation03:'',
          limitation04:'',
          limitation05:'',
          limitation06:'',
          limitation08:'',
          limitation09:'',
          limitation10:'',
          limitation11:'',
          limitation12:''
        },
        limitation0:{
          limitationno:'',
          contractno:'',
          tasktype:'',
          limitationtype:'',
          limitationunit:'',
          warningratio:'',
          limitation01:'',
          limitation02:'',
          limitation03:'',
          limitation04:'',
          limitation05:'',
          limitation06:'',
          limitation08:'',
          limitation09:'',
          limitation10:'',
          limitation11:'',
          limitation12:''
        },
        limitationlist:[],
        /*展示数据*/
        prescriptionRuleslist:'',
        /*合同附件单选框*/
        radio:'',
        attRemarkes:'',//附件信息
        attr:null,//更改选中行信息
        flag:false,//有删除附件则执行ture
        delindex:'',
        currentRow: null,

        /*合同详情初始化结束*/
        /*合同列表数据*/
        activeName:'first',//tabs页换页
        amendButton:'合同修改',//按钮内容
        amendContract:false,//合同列表显示
        contract:'',
        contractCode:'',
        contractName:'',
        remark0:'',
        /*换页*/
        currentPage:1,
        pageSize:10,
        /*显示的数据*/
        contractData:[],
        /*业务规则数据*/
        accountRules:'',//结算规则数据
        payType:'01',//结算方式
        addtimeRules:[],//添加一行时效的数据
        timeRules:'',//时效数据
        showAddTable:false,//添加结算规则数据表格显示
        showTableHeader:true,//展示结算规则数据表头
        showTime:false,//添加时效数据展示
        showTimeHeader:true,//展示时效数据表头
        /*计费规则验证*/
        priceDOs:[],
        ruletypeL:null,
        rulecontentKey:null,
        ConstractRules:{
          contractcode:[{ required: true, trigger: 'change', message: '请选择调查类型' }]

        },
        billingRules:{
          suppliertype:[{ required: true, trigger: 'change', message: '请选择调查类型' }],
          limitation:[{ required: true, trigger: 'change', message:'请选择调查时效' }],
          delegatemode:[{ required: true, trigger: 'change', message: '请选择委托方式' }],
          billingmode:[{ required: true, trigger: 'change', message: '请选择计费方式' }]
        },
        value1:'',//签订日期
        dispatchDate0:'',//结束日期
        dispatchDate:'',//开始日期
        statusValue:'',//合同状态
        contstatestatus:null,
        accountingRulesstatus: null,
        delegatemodestatus:null,
        billingmodestatus:null,
        casepointtypestatus: null,
        limitationstatus:null,
        targetareastatus:null,
        //props:{multiple},
        tasktypestatus:null,

      }

    },
    created() {
      this.fetchData()
      this.getAddressData()
      /*得到枚举值*/
      this.getConstractStatus()
      this.getaccountingRulesstatus()
      this.getdelegatemodestatus()
      this.getbillingmodestatus()
      this.getcasepointtypestatus()
      this.getlimitationstatus()
      this.getSupplierTypeData()
      this.getSupplierStateData()

    },
    methods: {
      getSupplierTypeData() {
        const dictName = 'supplier_type'
        get(dictName).then(response => {
          this.suppliertypes = response.content
        })
      },
      getSupplierTypeName(value){
        for (let i in this.suppliertypes) {
          if (this.suppliertypes[i].value === value){
            return this.suppliertypes[i].label;
          }
        }
      },
      // 供应商是否有效下拉选
      getSupplierStateData() {
        const dictName = 'supplier_states'
        get(dictName).then(response => {
          this.supplierstates = response.content
        })
      },
      getSupplierStateName(value){
        for (let i in this.supplierstates) {
          if (this.supplierstates[i].value === value){
            return this.supplierstates[i].label;
          }
        }
      },
      getConstractStatus(){
        const dictName = 'constract_status'
        get(dictName).then(response => {
          this.contstatestatus = response.content
        }).catch(error => {
          console.log(error)
        })

      },
      getaccountingRulesstatus(){
        const dictName = 'investigate_type'
        get(dictName).then(response => {
          this.accountingRulesstatus = response.content
        }).catch(error => {
          console.log(error)
        })

      },
      getdelegatemodestatus(){
        const dictName = 'gate_status'
        get(dictName).then(response => {
          this.delegatemodestatus = response.content
        }).catch(error => {
          console.log(error)
        })

      },
      getbillingmodestatus(){
        const dictName = 'mode_status'
        get(dictName).then(response => {
          this.billingmodestatus = response.content
        }).catch(error => {
          console.log(error)
        })

      },
      getcasepointtypestatus(){
        const dictName = 'casepoint_status'
        get(dictName).then(response => {
          this.casepointtypestatus = response.content
        }).catch(error => {
          console.log(error)
        })

      },
      getlimitationstatus(){
        const dictName = 'limita_status'
        get(dictName).then(response => {
          this.limitationstatus = response.content
          this.tasktypestatus =this.limitationstatus
        }).catch(error => {
          console.log(error)
        })

      },
      /*添加时效规则验证*/
      proving1(e,f) {
        var keynum = window.event ? e.keyCode : e.which;   //获取键盘码
        var keychar = String.fromCharCode(keynum);  //获取键盘码对应的字符
        if (keynum ==189|| keynum==190||keynum == 109) {
          this.$message.warning('禁止输入小数以及负数')
          e.target.value = null
          return
        }
        if(f==0){
          this.$message.warning('禁止直接输入0')
          e.target.value = null
          return
        }
        var reg =/^([1-9][0-9]*)$/
        if(!reg.test(f)){
          this.$message.warning('禁止输入非数字或者直接输入0')
          e.target.value = null
          return

        }
        if(f>99){
          this.$message.warning('禁止输入三位数')
          e.target.value = null
          return
        }

      },
      /*预警时效比例验证*/
      proving2(e,f) {
        var keynum = window.event ? e.keyCode : e.which;   //获取键盘码
        var keychar = String.fromCharCode(keynum);  //获取键盘码对应的字符
        if (keynum ==189|| keynum==190||keynum == 109) {
          this.$message.warning('禁止输入小数以及负数')
          e.target.value = ' '
        }
        if(f>99||f==0){
          this.$message.warning('预警时效比例应该在0%-100%之间,不含0')
          e.target.value = ' '
        }
      },

      fetchData() {
        const suppliercode = this.$route.query.surveyNo;
        getBySupplierCodeInfo(suppliercode).then(response => {
          this.constractList = response.data.contractDOlist
          this.supplierInfo = response.data.sn_supplierDO
          this.contractData = this.constractList.slice(0,5)
          /*默认显示第一条有效合同*/
          for(let i=0;i<this.constractList.length;i++){
          if(this.constractList[i].contstate=='01') {
            this.contractcode= this.constractList[i].contractno
            this. getByConstractCode(this.constractList[i].contractno)
            break;

          }


        }
      }).catch(error => {
          console.log(error)
        })


      },

      /*根据相应的合同带出不同信息*/
      editClick(value) {
        /*每点一次编辑，下拉框复原*/
        if(value.contstate=='02'){
          this.isableedit =true;
        }else{
          this.isableedit =false;
        }
        for(let i = 0;i<this.tasktypestatus.length;i++){
          this.tasktypestatus[i].disabled = false
        }
        this.showTime = false,
          this.showTimeHeader = true
        this.contractcode= value.contractno
        /*禁用复原*//*计费规则部分复原*/
        this.contractAppendicesInfo0=[]
        this.cansubmit = false
        this.subConstract=false
        this.subRules=false
        this.arr =[]
        this.arr1 =[]
        this.priceDOs=[]
        this.accountingRules={
          suppliertype:[],
          limitation:[],
          delegatemode:[],
          billingmode:[],
          casepointtype:[],
          targetarea:[]
        }
        this. getByConstractCode(value.contractno)


      },
      /*得到相应合同数据*/
      getByConstractCode(value) {
        getByConstractCodeInfo(value).then(response => {
          this.limitationlist = []
          this.contractInfo = response.data.contractDO//合同信息
          this.contractAppendicesInfo = response.data.attachmentDOList//合同附件信息
          this.accountingRules0 = response.data.priceDO//计费规则
          this.prescriptionRules = response.data.limitationDOList//时效规则
          /*合同备注处理*/
          this.fulltime = [this.contractInfo.startdate, this.contractInfo.enddate]
          this.radio = this.contractAppendicesInfo[0].attachmentno;
        for(let i = 0;i<this.contractAppendicesInfo.length;i++){
          this.contractAppendicesInfo0.push(this.contractAppendicesInfo[i])

        }
        this.$refs.singleTable.setCurrentRow(this.contractAppendicesInfo[0])
        this.attr = this.contractAppendicesInfo[0]
        /*计费规则处理*/
        this.dealprice();

      }).catch(error => {
          console.log(error)
        })
      },
      /*附件单选*/
      handleSelectionChange(row) {
        console.log(row);
        this.attRemarkes = row.remarks
        row.remarks = this.attRemarkes
        /*切换行之后才可以保存数据，需要改进*/


      },
      chooseone(row) {
        this.currentRow=row
        this.radio=row.attachmentno
        this.attRemarkes = row.remarks
        this.attr= row;

      },
      /*重置*/
      reset(){
        if(this.activeName=='first'){
          this.contractInfo.signaturedate=null,
            this.contractInfo.contractcode=null,
            this.contractInfo.contractname=null,
            this.contractInfo.contstate=null,
            this.contractInfo.startdate=null,
            this.contractInfo.enddate=null,
            this.contractInfo.createdate=null,
            this.contractInfo.remarks=null
          this.fulltime=[];
        }else if(this.activeName=='second'){
          this.accountingRules.suppliertype = []
          this.accountingRules.limitation = []
          this.accountingRules.delegatemode = []
          this.accountingRules.billingmode = []
          this.accountingRules.casepointtype = []
          this.accountingRules.targetarea = []
        }
      },
      jump(){
        this.$router.go(-1);
      },
      dealonepeice(val){
       alert(val)
      },
      /*时效规则添加*/
      timeRuleAdd() {
        if(this.prescriptionRules.length==this.accountingRules.limitation.length){
          alert("已经不允许添加，请先在计费规则中添加调查时效")

          return;
        }

        /*其次挑出已经选择过的*/
        for(let i=0;i<this.prescriptionRules.length;i++){
          for(let j=0;j<this.tasktypestatus.length;j++ ){
            if(this.tasktypestatus[j].value ==this.prescriptionRules[i].tasktype){
              this.tasktypestatus[j].disabled = true;

            }
          }

        }
        /*首先只选出上面可选的*/
        for(let i=0;i<this.tasktypestatus.length;i++){
          if(!this.accountingRules.limitation.includes(this.tasktypestatus[i].value) ){
            this.tasktypestatus[i].disabled = true;
          }
        };


        this.additem()

      },
      dealprice(){
        if (this.accountingRules0 == null) {
          this.accountingRules.suppliertype = null
          this.accountingRules.billingmode = null
          this.accountingRules.casepointtype = null
          this.accountingRules.delegatemode = null
          this.accountingRules.limitation = null
          this.accountingRules.targetarea = null
          return;
        }
        let falg1=1, falg2=1,falg3=1,falg4=1,falg5=1;
        for (let i = 0; i < this.accountingRules0.length; i++) {
          if(falg1==1){ this.accountingRules.suppliertype=[],falg1++}
          if (this.accountingRules0[i].ruletype == '01') {
            this.accountingRules.suppliertype.push(this.accountingRules0[i].rulecontent)
          }
          if (this.accountingRules0[i].ruletype == '03') {
            if(falg2==1){ this.accountingRules.delegatemode=[],falg2++}
            this.accountingRules.delegatemode.push(this.accountingRules0[i].rulecontent)
          }
          if (this.accountingRules0[i].ruletype == '04') {
            if( falg3==1){ this.accountingRules.billingmode=[],falg3++}
            this.accountingRules.billingmode.push(this.accountingRules0[i].rulecontent)
          }
          if (this.accountingRules0[i].ruletype == '05') {
            if(falg4==1){ this.accountingRules.casepointtype=[],falg4++}
            this.accountingRules.casepointtype.push(this.accountingRules0[i].rulecontent)
          }
          if (this.accountingRules0[i].ruletype == '02') {
            if (falg5 == 1) {
              this.accountingRules.limitation = [], falg5++}
            this.accountingRules.limitation.push(this.accountingRules0[i].rulecontent)

          }
          if (this.accountingRules0[i].ruletype == '06') {
            for (let k = 0; k < this.targetareastatus.length; k++) {
              for (let l = 0; l < this.targetareastatus[k].children.length; l++) {
                if(this.accountingRules0[i].rulecontent==this.targetareastatus[k].children[l].value){
                  this.arr1.push([this.targetareastatus[k].value,
                    this.targetareastatus[k].children[l].value])
                }

              }

            }

          }
          this.$set(this.accountingRules, 'targetarea', this.arr1)

        }

      },
      /*添加一行元素*/
      additem(){
        this.addtimeRules = []
        this.addtimeRules.push(
          {
            contractno: '',
            tasktype: '',
            limitationtype: '',
            limitationunit: '小时',
            warningratio: '80',
            limitation01: '',
            limitation02: '',
            limitation03: '',
            limitation04: '',
            limitation05: '',
            limitation06: '',
            limitation08: '',
            limitation09: '',
            limitation10: '',
            limitation11: '',
            limitation12: ''}
        )
        this.showTime = true,//添加时效数据展示
          this.showTimeHeader = false//展示时效数据表头
      },
      /*换页*/
      handleSizeChange(pageSize) {
        this.pageSize = pageSize
        /*更换页码size后从第一页开始*/
        this.currentPage = 1
        this.contractData = this.constractList.slice(0, this.pageSize)
      },
      handleCurrentChange(currentPage) {
        this.currentPage = currentPage
        this.contractData = this.constractList.slice(this.pageSize * (currentPage - 1), this.pageSize * currentPage)


      },

      /*tsbs发生跳转*/
      handleClick() {
        this.cansubmit = false
        if (this.activeName == 'first') {
          this.amendButton = '合同修改'
          this.cansubmit = this.subConstract
        } else if (this.activeName == 'second') {
          this.cansubmit = this.subRules
          this.amendButton = '规则修改'
        }

      },
      /*下载附件*/
      download() {
        alert("调用下载附件接口，待完成")
      },
      /*删除附件*/
      del() {
        this.$confirm('此操作将永久删除该附件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.flag=true

          //alert(this.radio)

          for(let i=0;i<this.contractAppendicesInfo.length;i++){
          if(this.contractAppendicesInfo[i].attachmentno== this.radio){
            this.contractAppendicesInfo[i].state='02'
          }
        }
        let no = 0;
        for(let i=0;i<this.contractAppendicesInfo0.length;i++){
          if(this.contractAppendicesInfo0[i].attachmentno== this.radio){
            no =i
          }
        }
        this.contractAppendicesInfo0.splice(no,1)
        if(this.contractAppendicesInfo0.length>0){
          this.radio = this.contractAppendicesInfo0[0].attachmentno}
        this.$message({
          type: 'success',
          message: '删除成功!保存后生效'
        });
      }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
      });

      },
      /*计费规则添加*/
      putPrice(index,childrenIndex, date){
        this.ruletypeL = null
        if(index == 'suppliertype'){
          this.ruletypeL = '01'
          this.rulecontentKey = this.accountingRules.suppliertype[childrenIndex]
        }else if (index == 'limitation'){
          this.ruletypeL = '02'
          this.rulecontentKey = this.accountingRules.limitation[childrenIndex]
        }else if (index == 'delegatemode'){
          this.ruletypeL = '03'
          this.rulecontentKey = this.accountingRules.delegatemode[childrenIndex]
        }else if (index == 'billingmode'){
          this.ruletypeL = '04'
          this.rulecontentKey = this.accountingRules.billingmode[childrenIndex]
        }else if (index == 'casepointtype'){
          this.ruletypeL = '05'
          this.rulecontentKey = this.accountingRules.casepointtype[childrenIndex]
        }else if (index == 'targetarea'){
          this.ruletypeL = '06'
          this.rulecontentKey = this.accountingRules.targetarea[childrenIndex][1]
        }
        this.priceDOs.push(
          {
            contractno: this.contractcode,//没取到
            suppliertype: '1',
            ruletype: this.ruletypeL,
            rulecontent:this.rulecontentKey,
            creator: 'test',
            createdate: date,
            operator: null,
            operatedate: null,
          }
        )
      },
      getRules(){
        for (let j in this.accountingRules) {
          var datePrice= new Date();
          let date = {
            year: datePrice.getFullYear(),
            month: datePrice.getMonth() + 1,
            date: datePrice.getDate(),
          };
          console.log(date);
          if (date.month < 10) {
            date.month = '0' + date.month;
          }
          if (date.date < 10) {
            date.date = '0' + date.date;
          }
          let systemDate = date.year + '-' + date.month + '-' + date.date;
          if ('suppliertype' == j){
            for (let k in this.accountingRules.suppliertype) {
              this.putPrice(j,k,systemDate)
            }

          }else if ('limitation' == j){
            for (let k in this.accountingRules.limitation) {
              this.putPrice(j,k,systemDate)
            }
          }else if ('delegatemode' == j){
            for (let k in this.accountingRules.delegatemode) {
              this.putPrice(j,k,systemDate)
            }
          }else if ('billingmode' == j){
            for (let k in this.accountingRules.billingmode) {
              this.putPrice(j,k,systemDate)
            }
          }else if ('casepointtype' == j){
            for (let k in this.accountingRules.casepointtype) {
              this.putPrice(j,k,systemDate)
            }
          }else if ('targetarea' == j){
            for (let k in this.accountingRules.targetarea) {
              this.putPrice(j,k,systemDate)
            }
          }

        }

        return this.priceDOs;
      },
      /*删除时效规则*/
      delClick0(index) {
        if(this.accountingRules.limitation.includes(index.tasktype) ){
          alert("请先删除计费规则中相应调查时效")
          return;
        }
        this.$confirm('此操作在保存后将永久删除该时效规则, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const  deleteitem = [this.contractcode,index.tasktype]

          deletelimition(deleteitem).then(response => {

            getByConstractCodeInfo(this.contractcode).then(response => {
          this.limitationlist = []
          this.prescriptionRules = response.data.limitationDOList//时效规则
        }).catch(error => {
          console.log(error)
        })
      }).catch(error => {
          console.log(error)
        })
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
      });

      },
      /*时效规则取消*/
      cancelClick(index) {
        this.addtimeRules.splice(index, 1)
        if (this.addtimeRules == '' | this.addtimeRules == null) {
          this.showTime = false,//添加时效数据展示
            this.showTimeHeader = true//展示时效数据表头
        }
      },
      /*多选框*/
      changeSuppliertype(val){
       alert(val)

      },
      amendRule() {

        if(this.amendButton=='规则修改'){
          /*计费规则修改*/
          this.subRules = true;
          if(this.accountingRules.limitation.length==0||this.accountingRules.suppliertype.length==0||this.accountingRules.delegatemode.length==0||this.accountingRules.billingmode.length==0){
            this.$notify.error({
              title: '错误',
              message: '请按提示补全信息'
            });
          }else{
            this.getRules()
            updateBusiness(this.priceDOs).then(response => {
              this.cansubmit = true
              this.$notify({
                title: '成功',
                message: '计费规则修改成功',
                type: 'success'
              });
            /*首先删除全部*/
            /*调用费规则保存接口*/
          }).catch(error => {
              console.log(error)
            })
          }
          /*时效修改*/
          for (let i = 0; i < this.addtimeRules.length; i++) {
            this.limitationlist.push(this.addtimeRules[i])
          }
          //this.addtimeRules = []
          this.showTime  = false
          this.showTimeHeader = true
          //得到添加的数
          if(this.addtimeRules.length>0){
            for(let i=0;i<this.addtimeRules.length;i++){
              this.limitList[0].limitation1 = this.addtimeRules[i].limitation01
              this.limitList[0].limitation2 = this.addtimeRules[i].limitation02
              this.limitList[1].limitation1 = this.addtimeRules[i].limitation03
              this.limitList[1].limitation2 = this.addtimeRules[i].limitation04
              this.limitList[2].limitation1 = this.addtimeRules[i].limitation05
              this.limitList[2].limitation2 = this.addtimeRules[i].limitation06
              this.limitList[3].limitation1 = this.addtimeRules[i].limitation07
              this.limitList[3].limitation2 = this.addtimeRules[i].limitation08
              this.limitList[4].limitation1 = this.addtimeRules[i].limitation09
              this.limitList[4].limitation2 = this.addtimeRules[i].limitation10
              this.limitList[5].limitation1 = this.addtimeRules[i].limitation11
              this.limitList[5].limitation2 = this.addtimeRules[i].limitation12
              for(let j=0;j<this.limitList.length;j++){
                this.limitList[j].contractno = this.contractcode
                this.limitList[j].warningratio = this.addtimeRules[i].warningratio
                this.limitList[j].limitationunit = this.addtimeRules[i].limitationunit
                this.limitList[j].tasktype = this.addtimeRules[i].tasktype
              }
              limitationAdd(this.limitList).then(response => {
                getByConstractCodeInfo(this.contractcode).then(response => {
                this.limitationlist = []
                this.accountingRules0 = response.data.priceDO//计费规则
                this.prescriptionRules= response.data.limitationDOList//时效规则
                this.dealprice();
              /*该合同计费规则已经修改*/
              this.$notify({
                title: '成功',
                message: '时效规则修改成功',
                type: 'success'
              });

            }).catch(error => {
                console.log(error)
              })
            }).catch(error => {
                console.log(error)
              })

            }
          }


          /*updateBusiness(this.rulesDo).then(response => {

          }).catch(error => {
            console.log(error)
          })*/
        }else if(this.amendButton=='合同修改'){
          if(this.contractInfo.contstate==null|this.contractInfo.contractcode==null|this.contractInfo.signaturedate==null|this.contractInfo.enddate==null|this.contractInfo.startdate==null){
            this.$notify.error({
              title: '错误',
              message: '请补全合同基本信息,只有备注可以为空'
            });
            return
          }
          this.updateConst()
        }

      },
      updateConst(){
        this.contractInfo.startdate=this.fulltime[0],
          this.contractInfo.enddate=this.fulltime[1],
          this.attr.remarks=this.attRemarkes
        this.cons={sn_supplier_contractDO:this.contractInfo,
          attachmentDOlist:this.contractAppendicesInfo}
        updateConstract(this.cons).then(response => {
          this.subConstract = true;
        this.cansubmit =true;
        this.$notify({
          title: '成功',
          message: '合同修改成功',
          type: 'success'
        });
        this.$router.go(0);
      }).catch(error => {
          console.log(error)
        })
      },
      selected(value){
        this.addtimeRules[0].limitation06=24;
        if(value=='01'){
          this.addtimeRules[0].limitation05=1;

        }
        if(value=='02'){
          this.addtimeRules[0].limitation05=3;

        }
        if(value=='03'){
          this.addtimeRules[0].limitation05=4;

        }
        if(value=='04'){
          this.addtimeRules[0].limitation05=5;

        }
        if(value=='05'){
          this.addtimeRules[0].limitation05=6;

        }
        if(value=='06'){
          this.addtimeRules[0].limitation05=7;

        }
        if(value=='07'){
          this.addtimeRules[0].limitation05=8;

        }
        if(value=='08'){
          this.addtimeRules[0].limitation05=9;

        }


      },

      //地址下拉选
      getAddressData(){
        getAddress().then(response => {
          console.log(response.data);
        console.log(response.data.addressList);
        this.targetareastatus = response.data
        for (let k = 0; k < this.targetareastatus.length; k++) {
          for (let l = 0; l < this.targetareastatus[k].children.length; l++) {
            this.targetareastatus[k].children[l].children=null

          }
        }

      }).catch(error => {
          console.log(error)
        })
      },
    }
  }

</script>

<style scoped>

  .box-card1 {
    position: relative;
    margin-top: 1%;
    left: 1%;
    width: 98%;
    background: inherit;
    background-color: rgba(255, 255, 255, 1);
    box-sizing: border-box;
    border-width: 1px;
    border-style: solid;
    border-color: rgba(217, 217, 217, 1);
    border-radius: 4px;
    height: auto;
  }
  .grid-content0{
    font-size: 14px;
    color: #555;
  }
  .grid-content1 {
    /*margin-left: -20%;*/
    height: 40px;
    font-family: 'Arial Normal', 'Arial', sans-serif;
    font-weight: 400;
    font-style: normal;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.250980392156863);
    line-height: 22px;

  }
  /*tabs下横线加长*/
  .el-tabs /deep/ .el-tabs__item {
    /* padding-bottom: 50px;*//*分页高度*/
    text-align: center;
    width: 200px;
  }
  .el-tabs /deep/ .el-tabs__active-bar {
    width: 200px;
  }
  .el-collapse-item /title/ {
    color: #13ce66;
  }
  .el-input__inner {
    padding-left: 6px;
    padding-right: 6px;
  }
  /*element原有样式修改*/
  .el-form-item /deep/ label {
    font-weight: normal;
  }

  .list_span {
    display: inline-block;
    line-height: 2.5;
    color: #555;
  }

  .box-card {
    margin: 10px;
    padding: 0 20px;
  }
  .box-card /deep/ .el-card__body{
    padding: 0;
  }
  .list_item {
    margin-bottom: 0 !important;
  }

</style>
