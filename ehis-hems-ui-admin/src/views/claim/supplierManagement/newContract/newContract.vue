<template>
  <div>
    <el-collapse v-model="activeNames" class="box-card1">
      <el-collapse-item name="1">
        <template slot="title">
          <span style="font-size: 16px;font-weight: bold;margin-left: 1%" >供应商合同信息</span>
        </template>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0" style="float: right">供应商编码：</div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1">{{supplierInfo.suppliercode}}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0"style="float: right">供应商类型：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1" v-if="supplierInfo.suppliertype=='01'">TPA公司</div>
                  <div class="grid-content1" v-if="supplierInfo.suppliertype=='02'">公估公司</div>
                  <div class="grid-content1" v-if="supplierInfo.suppliertype=='03'">垫付公司</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0"style="float: right">是否有效：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1" v-if="supplierInfo.state=='01'">是</div>
                  <div class="grid-content1" v-if="supplierInfo.state=='02'">否</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0"style="float: right">供应商中文名称：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1">{{supplierInfo.chname}}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0"style="float: right">供应商英文名称：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1">{{supplierInfo.enname}}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0"style="float: right">所属地区：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1">{{supplierInfo.district}}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0" style="float: right">详细地址：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1">{{supplierInfo.address}}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-row :gutter="20">
                <el-col :span="10">
                  <div class="grid-content0"style="float: right">备注：  </div>
                </el-col>
                <el-col :span="14">
                  <div class="grid-content1">{{supplierInfo.remarks}}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
    <div>
      <el-card class="box-card1">
        <p style="margin-left:2%;font-weight: bold;font-size:16px;">合同列表</p>
        <el-table :data="contractPageInfo":header-cell-style="{color:'black',background:'#f8f8ff'}" style="width: 100%" @current-change="clickChange">
          <!-- <el-table-column label="" width="65">
             <template slot-scope="scope">
              </template>
           </el-table-column>-->
          <el-table-column prop="contractcode" label="合同编码" align="center" show-overflow-tooltip >
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
          <el-table-column prop="signaturedate" label="合同签订日期" align="center" show-overflow-tooltip >
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
              <el-button type="text" @click="updateClick(scope.row)" :disabled="scope.row.contstate=='02'">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block"  style="margin-left: 58%;margin-top: 1%">
          <el-pagination
            background=""
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30,50,100]"
            :page-size="pageSize"
            layout=" prev,pager, next,sizes,jumper"
            :total="contractTotal">
          </el-pagination>
        </div>
      </el-card>
      <div class="box-card1" style="margin-top: 1%" >
        <el-button type="primary"
                   size="mini"
                   style="z-index: 99999;position:absolute;left: 76%;margin-top: 0.5%"
                   :disabled="showContractDisabledButton"
                   v-on:click="submitRuleInfo"><a style="margin-left: -4px">{{this.tabType}}</a>
        </el-button>
        <el-button type="primary"
                   :disabled="showContractDisabledButton"
                   size="mini"
                   style="z-index: 99999;position:absolute;left: 83%;margin-left: 3%;margin-top: 0.5%"
                   v-on:click="resetInfo"><a style="margin-left: -4px">重置</a>
        </el-button>
        <el-button type="primary"
                   size="mini"
                   style="z-index: 99999;position:absolute;left: 90%;margin-left: 3%;margin-top: 0.5%"
                   v-on:click="jump"><a style="margin-left: -4px">取消</a>
        </el-button>

        <template>
          <el-tabs v-model="activeName" @tab-click="handleSelect"
                   :before-leave="beforeLeave">
            <el-tab-pane label="合同信息" name="first">
              <div>
                <p style="margin-left:2%;font-weight: bold;color:black;font-size:16px;">合同基本信息</p>
                <el-form :model="sn_supplier_contractDO" label-width="120px" label-position="right" ref="contractRule" style="margin-left: 2%" :rules="rulesContract">
                  <el-form-item label="合同编码：" prop="contractcode" style="display:inline-block;width:33%;" class="item1">
                    <el-input v-model="sn_supplier_contractDO.contractcode" class="inp1" size="mini" placeholder="请输入" :disabled="showContractDisabled"/>
                  </el-form-item>
                  <el-form-item label="合同名称：" prop="contractname" style="display:inline-block;width:33%;" class="item1">
                    <el-input v-model="sn_supplier_contractDO.contractname" class="inp1" size="mini" placeholder="请输入" :disabled="showContractDisabled"/>
                  </el-form-item>
                  <el-form-item label="合同状态：" prop="contstate" style="display:inline-block;width:33%;" class="item1">
                    <el-select v-model="sn_supplier_contractDO.contstate" class="inp1"size="mini" placeholder="请选择" :disabled="showContractDisabled">
                      <el-option
                        v-for="item in status"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="合同起止日期：" style="display:inline-block;width:33%;" class="item1" prop="effectivedate">
                    <el-date-picker
                      :disabled="showContractDisabled"
                      v-model="sn_supplier_contractDO.effectivedate"
                      style="width:100%;"
                      size="mini"
                      value-format="yyyy-MM-dd"
                      type="daterange"
                      range-separator="~"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                    />
                  </el-form-item>

                  <el-form-item label="合同签订日期：" style="display:inline-block;width:33%;" class="item1" prop="signaturedate">
                    <el-date-picker
                      :disabled="showContractDisabled"
                      v-model="sn_supplier_contractDO.signaturedate"
                      value-format="yyyy-MM-dd"
                      type="date"
                      placeholder="选择日期"
                      size="mini">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="合同备注：" prop="remarks" style="display:inline-block;width:93%;" class="item1">
                    <el-input v-model="sn_supplier_contractDO.remarks" class="inp1" size="mini"  placeholder="请输入" :disabled="showContractDisabled"/>
                  </el-form-item>
                </el-form>
                <el-card>
                  <p style="margin-left:1%;font-weight: bold;color:black;font-size:16px;">合同附件信息</p>
                  <el-form class="box-card">
                    <el-upload
                      class="upload-demo"
                      ref="upload"
                      action="String"
                      accept=".rar,.zip,.doc,.docx,.pdf,.jpfg,.png"
                      :on-remove="handle_remove"
                      :before-remove="beforeRemove"
                      :http-request="uploadContractAnnex"
                      :before-upload="uploadBefore"
                      multiple
                      :file-list="fileList"
                      :disabled="showContractDisabled">
                      <el-button size="small" type="primary">上传附件</el-button>
                      <div slot="tip" class="el-upload__tip">支持扩展名：.rar .zip .doc .docx .pdf .jpfg .png</div>
                    </el-upload>
                    <el-form-item label="附件备注：">
                      <div class="text-area"><el-input v-model="attachmentDORemarks" tpye="textarea" :disabled="showContractDisabled"/></div>
                    </el-form-item>
                  </el-form>
                </el-card>
              </div>
            </el-tab-pane>
            <el-tab-pane label="业务规则" name="second">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>计费规则</span>
                </div>
                <el-form ref="priceRule" :model="priceInfo" label-width="100px" label-position="right" style="margin-left: 10%" :rules="rules">
                  <el-form-item label="调查类型：" prop="suppliertype" style="display:inline-block;width:28%;" class="item1">
                    <el-select v-model="priceInfo.suppliertype" placeholder="请选择" size="mini"
                               :disabled="showDisabled"
                               multiple
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
                  <el-form-item label="调查时效：" prop="limitation" style="display:inline-block;width:28%;" class="item1">
                    <el-select v-model="priceInfo.limitation" placeholder="请选择" size="mini" @change="limitSelect"
                               :disabled="showDisabled"
                               multiple
                               filterable
                               allow-create
                               collapse-tags
                               default-first-option>
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="委托方式：" prop="delegatemode" style="display:inline-block;width:28%;" class="item1">
                    <el-select v-model="priceInfo.delegatemode" placeholder="请选择" size="mini"
                               :disabled="showDisabled"
                               multiple
                               filterable
                               allow-create
                               collapse-tags
                               default-first-option>
                      <el-option
                        v-for="item in entrustWay"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="计费方式：" prop="billingmode" style="display:inline-block;width:28%;" class="item1">
                    <el-select v-model="priceInfo.billingmode" placeholder="请选择" size="mini"
                               :disabled="showDisabled"
                               multiple
                               filterable
                               allow-create
                               collapse-tags
                               default-first-option>
                      <el-option
                        v-for="item in chargeWay"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="案件点类型：" prop="casepointtype" style="display:inline-block;width:28%;" class="item1">
                    <el-select v-model="priceInfo.casepointtype" placeholder="请选择" size="mini"
                               :disabled="showDisabled"
                               multiple
                               filterable
                               allow-create
                               collapse-tags
                               default-first-option>
                      <el-option
                        v-for="item in caseType"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="案件地区：" prop="targetarea" style="display:inline-block;width:28%;" class="item1">
                    <el-cascader
                      :disabled="showDisabled"
                      size="mini"
                      v-model="priceInfo.targetarea"
                      :options="caseArea"
                      :props="props"
                      collapse-tags
                      clearable></el-cascader>
                  </el-form-item>
                </el-form>

              </el-card>
              <!--时效规则-->

              <el-card style="margin-top: 1%" class="box-card">
                <div slot="header" class="clearfix">
                  <span style="margin-left:2%;font-weight: bold;font-size:16px;">时效规则</span>
                </div>
                <template>
                  <el-table
                    v-show="showTime"
                    :data="addtimeRulesJson"
                    style="width: 100%">
                    <el-table-column
                      align="center"
                      label="时效类型"
                      style="width: 65px">
                      <template slot-scope="scope">
                        <el-select @change="selected(scope.row.tasktype)" size="mini" v-model="scope.row.tasktype" placeholder="请选择">
                          <el-option
                            v-for="item in options"
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
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.assignLimitationDay" @keyup.native="proving1($event,scope.row.assignLimitationDay)"  placeholder=""></el-input>
                        <span style="font-size: 12px;margin-left: 9px">x</span>
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.assignLimitationHour" @keyup.native="proving1($event,scope.row.assignLimitationHour)"  placeholder=""></el-input>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="接收时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.receiveLimitationDay" @keyup.native="proving1($event,scope.row.receiveLimitationDay)"  placeholder=""></el-input>
                        <span style="font-size: 12px;margin-left: 9px">x</span>
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.receiveLimitationHour" @keyup.native="proving1($event,scope.row.receiveLimitationHour)"  placeholder=""></el-input>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="处理时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-input  size="small" style="width: 35px;padding-left:6px"v-model="scope.row.manageLimitationDay" @keyup.native="proving1($event,scope.row.manageLimitationDay)"  placeholder=""></el-input>
                        <span style="font-size: 12px;margin-left: 9px">x</span>
                        <el-input  size="small" style="width: 35px;padding-left:6px"v-model="scope.row.manageLimitationHour" @keyup.native="proving1($event,scope.row.manageLimitationHour)"  placeholder=""></el-input>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="初审时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.firstLimitationDay" @keyup.native="proving1($event,scope.row.firstLimitationDay)"  placeholder=""></el-input>
                        <span style="font-size: 12px;margin-left: 9px">x</span>
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.firstLimitationHour" @keyup.native="proving1($event,scope.row.firstLimitationHour)"  placeholder=""></el-input>
                      </template>

                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="审核时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.auditLimitationDay" @keyup.native="proving1($event,scope.row.auditLimitationDay)"  placeholder=""></el-input>
                        <span style="font-size: 12px;margin-left: 9px">x</span>
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.auditLimitationHour" @keyup.native="proving1($event,scope.row.auditLimitationHour)"  placeholder=""></el-input>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="调查总时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.totalLimitationDay" @keyup.native="proving1($event,scope.row.totalLimitationDay)"  placeholder=""></el-input>
                        <span style="font-size: 12px;margin-left: 9px">x</span>
                        <el-input size="small" style="width: 35px;padding-left:6px"v-model="scope.row.totalLimitationHour" @keyup.native="proving1($event,scope.row.totalLimitationHour)"  placeholder=""></el-input>
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
                    :data="showTimeRulesJson"
                    style="width: 100%">
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
                        {{scope.row.assignLimitationDay}}X{{scope.row.assignLimitationHour}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="接收时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.receiveLimitationDay}}X{{scope.row.receiveLimitationHour}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="处理时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.manageLimitationDay}}X{{scope.row.manageLimitationHour}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="初审时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.firstLimitationDay}}X{{scope.row.firstLimitationHour}}
                      </template>

                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="审核时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.auditLimitationDay}}X{{scope.row.auditLimitationHour}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="调查总时效"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.totalLimitationDay}}X{{scope.row.totalLimitationHour}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"total
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
                  <br/> <br/>
                </template>
              </el-card>



            </el-tab-pane>
          </el-tabs>
        </template>
      </div>
    </div>
  </div>
</template>
<script>
    import { submitContractInfo, submitRuleInfo,getContractAndSupplierInfo,getAddress,uploadContractAnnex,deletelimition,limitationAdd,getBySupplierCodeInfo,getByConstractCodeInfo} from '@/api/investigate/supplierManagement'
    import {get} from '@/api/dictDetail'
    export default {
        filters: {
            statusFilter(status) {
                const statusMap = {
                    published: 'success',
                    draft: 'gray',
                    deleted: 'danger'
                };
                return statusMap[status]
            }
        },
        data() {
            return {
                contractno:null,
                props: { multiple: true },
              options: [{
                    value: '01',
                    label: '特急 (1X24)',
                    is_true:true
                }, {
                    value: '03',
                    label: '紧急 (3X24)',
                    is_true:true
                }, {
                    value: '04',
                    label: '非紧急 (4X24)',
                    is_true:true
                }, {
                    value: '05',
                    label: '非紧急 (5X24)',
                    is_true:true
                }, {
                    value: '06',
                    label: '非紧急 (6X24)',
                    is_true:true
                },
                    {
                        value: '07',
                        label: '非紧急 (7X24)',
                        is_true:true
                    },
                    {
                        value: '08',
                        label: '非紧急 (8X24)',
                        is_true:true
                    },
                    {
                        value: '09',
                        label: '非紧急 (9X24)',
                        is_true:true
                    }
                ],
                contractPageInfo:null,
                contractInfo:[],
                activeNames:[],
                statusValue:'',
                contractTotal:0,
                suppliertype:null,
              accountingRulesstatus: null,//调查类型
              tasktypestatus:null,//时效类型
                option: [{
                    value: '01',
                    label: '是'
                }, {
                    value: '02',
                    label: '否'
                }],
                status: [{
                    value: '01',
                    label: '有效'
                }, {
                    value: '02',
                    label: '无效'
                }],
                status1: [{
                    value: '02',
                    label: '深度调查'
                }, {
                    value: '03',
                    label: '一般调查'
                }],
                surveyStatus: [{
                    value: '01',
                    label: '一般时效'
                }, {
                    value: '02',
                    label: '深度时效'
                }],
                entrustWay: [{
                    value: '02',
                    label: '个案委托'
                }, {
                    value: '01',
                    label: '全案委托'
                }],
                chargeWay: [{
                    value: '01',
                    label: '打包计费'
                }, {
                    value: '02',
                    label: '单点计费'
                }, {
                    value: '03',
                    label: '整案计费'
                }],
                caseType: [{
                    value: '01',
                    label: '医院'
                }, {
                    value: '02',
                    label: '社保'
                }, {
                    value: '03',
                    label: '走访'
                }, {
                    value: '04',
                    label: '同业'
                }, {
                    value: '05',
                    label: '体检'
                }, {
                    value: '06',
                    label: '其他'
                }],
                caseArea: [],
                autoValue:'',//自动续约
                autoRenew: [{
                    label:'是',
                    value:'1',
                    id:1
                },
                    {
                        label:'否',
                        value:'2',
                        id:2
                    }],
                saveType:'first',
                addtimeRulesJson:[{
                    assignLimitationDay: null,
                    assignLimitationHour: '24',
                    receiveLimitationDay: null,
                    receiveLimitationHour: '24',
                    manageLimitationDay: null,
                    manageLimitationHour: '24',
                    firstLimitationDay: null,
                    firstLimitationHour: '24',
                    auditLimitationDay: null,
                    auditLimitationHour: '24',
                    totalLimitationDay: null,
                    totalLimitationHour: '24',
                    limitationunit:'小时',
                    tasktype:null,
                    warningratio:'80'
                }],
                sn_supplier_contractDO:
                    {
                        contractname: null,
                        contstate: '01',
                        createdate: null,
                        creator: null,
                        enddate: null,
                        operatedate: null,
                        operator: null,
                        remarks: null,
                        startdate: null,
                        effectivedate:[],
                        signaturedate:null,
                        suppliercode: null
                    }
                ,
                /*                sn_supplier_contract_attachmentDO:{
                                    contractno: '15',
                                    createdate: null,
                                    creator: null,
                                    filepath: null,
                                    name: null,
                                    operatedate: null,
                                    operator: null,
                                    remarks: null,
                                    state: null,
                                    type: '1'
                                },*/
                attachmentDORemarks:null,
                index:-1,
                priceInfo:{
                    suppliertype: null,
                    limitation: null,
                    delegatemode: null,
                    billingmode: null,
                    casepointtype: null,
                    targetarea: null,
                },
                showTime:false,//添加时效数据展示
                showTimeHeader:true,//展示时效数据表头
                list: null,
                list1: null,
                listLoading: true,
                list1Loading: true,
                tableData1: [{
                    check1: '',
                    num: '1001'
                }],
                ruleStatus:0,
                form: {
                    num: '',
                    type: '',
                    init: '',
                    cName: '',
                    eName: '',
                    address: '',
                    xAddress: '',
                    text: ''
                },
                //auto: null,
                supplierInfo:null,
                suppliercode:null,
                fileList: [],
                activeName: 'first',
                currentPage:1,
                pageSize:10,
                contract:'',
                showTimeRulesJson:null,
                showDisabled:false,
                showContractDisabled:false,
                showContractDisabledButton:false,
                tabType:'合同保存',
                saveStatus:null,
                rulecontentKey:null,
                clickChange(item) {
                    this.tableRadio = item
                },
                sn_supplier_contract_priceDOL:[],
                ruletypeL:null,
                rules: {
                    suppliertype: [{ required: true, message: "请选择调查类型", trigger: "blur" }],
                    limitation: [{ required: true, message: "请选择调查时效", trigger: "blur" }],
                    delegatemode: [{ required: true, message: "请选择委托方式", trigger: "blur" }],
                    billingmode: [{ required: true, message: "请选择计费方式", trigger: "blur" }],
                },
                rulesContract: {
                    contractname: { required: true, message: "请输入合同名称", trigger: "blur" },
                    effectivedate: { required: true, message: "请选择起止日期", trigger: "blur" },
                    signaturedate: { required: true, message: "请选择签订日期", trigger: "blur" },
                    contractcode: { required: true, message: "请输入合同编码", trigger: "blur" },
                }
            }
        },
        created() {
            this.getParams()
            this.getContractAndSupplierInfo()
            this.getAddressData()
          this.getaccountingRulesstatus()//调查类型
          this.getlimitationstatus//时效类型
        },
        methods: {
            limitSelect(val){
              const  a = val
                for (let i in val){
                    if(val[i] == '01'){
                        this.options[0]['is_true'] = false
                    }else if(val[i] == '03'){
                        this.options[1]['is_true'] = false
                    }else if(val[i] == '04'){
                        this.options[2]['is_true'] = false
                    }else if(val[i] == '05'){
                        this.options[3]['is_true'] = false
                    }else if(val[i] == '06'){
                        this.options[4]['is_true'] = false
                    }else if(val[i] == '07'){
                        this.options[5]['is_true'] = false
                    }else if(val[i] == '08'){
                        this.options[6]['is_true'] = false
                    }else if(val[i] == '09'){
                        this.options[7]['is_true'] = false
                    }
                }
            },
            selected(value){
                if(value=='01'){
                    this.addtimeRulesJson[0].manageLimitationDay=1;

                }
               /* if(value=='03'){
                    this.addtimeRulesJson[0].manageLimitationDay=3;

                }*/
                if(value=='04'){
                    this.addtimeRulesJson[0].manageLimitationDay=4;

                }
                if(value=='05'){
                    this.addtimeRulesJson[0].manageLimitationDay=5;

                }
                if(value=='06'){
                    this.addtimeRulesJson[0].manageLimitationDay=6;

                }
                if(value=='07'){
                    this.addtimeRulesJson[0].manageLimitationDay=7;

                }
                if(value=='08'){
                    this.addtimeRulesJson[0].manageLimitationDay=8;

                }
                if(value=='09'){
                    this.addtimeRulesJson[0].manageLimitationDay=9;

                }


            },
          /*删除时效规则*/
          delClick0(index) {
            if(this.priceInfo.limitation.includes(index.tasktype) ){
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
                  this.showTimeRulesJson = response.data.limitationDOList//时效规则
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
          /*时效规则添加*/
          timeRuleAdd() {
     /*       if(this.showTimeRulesJson.length==this.priceInfo.limitation.length){
              alert("已经不允许添加，请先在计费规则中添加调查时效")

              return;
            }

            /!*其次挑出已经选择过的*!/
            for(let i=0;i<this.showTimeRulesJson.length;i++){
              for(let j=0;j<this.tasktypestatus.length;j++ ){
                if(this.tasktypestatus[j].value ==this.showTimeRulesJson[i].tasktype){
                  this.tasktypestatus[j].disabled = true;

                }
              }

            }*/
            /*首先只选出上面可选的*/
            for(let i=0;i<this.options.length;i++){
              if(!this.priceInfo.limitation.includes(this.options[i].value) ){
                this.options[i].disabled = true;
              }
            };


            this.additem()

          },
          /*添加一行元素*/
          additem(){
            this.addtimeRules = []
            this.addtimeRules.push(
              {
                contractno: '',
                tasktype: '',
                limitationtype: '',
                limitationunit: '',
                warningratio: '',
                assignLimitationDay: '',
                assignLimitationHour: '',
                receiveLimitationDay: '',
                receiveLimitationHour: '',
                manageLimitationDay: '',
                manageLimitationHour: '',
                firstLimitationDay: '',
                firstLimitationHour: '',
                auditLimitationDay: '',
                auditLimitationHour: '',
                totalLimitationDay: '',
                totalLimitationHour: ''}
            )
            this.showTime = true,//添加时效数据展示
              this.showTimeHeader = false//展示时效数据表头
          },
          /*时效规则取消*/
          cancelClick(index) {
            this.addtimeRules.splice(index, 1)
            if (this.addtimeRules == '' | this.addtimeRules == null) {
              this.showTime = false,//添加时效数据展示
                this.showTimeHeader = true//展示时效数据表头
            }
          },
          /*
            dataRule(val){
              this.auto = val.toString()
            },
            */
            //列表移除，缺服务端移除
            handle_remove(file, fileList) {
              if (file && file.status==="success") {
                this.fileList = fileList.filter(item => {
                  return item.uid !== file.uid
                })
              }
            },

            handle_success(response, file, fileList) {
                this.fileList.push({
                    name: file.name,
                    //服务端存储地址
                    url: 'test'
                });
            },

          //上传合同附件
          uploadContractAnnex(param){
            const formData = new FormData();
            formData.append('file', param.file);
            formData.append('supplierno',this.suppliercode);
            formData.append('name',param.file.name);
            uploadContractAnnex(formData).then(response => {
              if(response.status === '1' && response.data !== null && response.data !== ''){
                this.fileList.push({
                  name: param.file.name,
                  url: response.data
                });
                param.onSuccess();
              }else{
                param.onError()
              }
            }).catch(error => {
              param.onError()
            })
          },

          uploadBefore(file) {
                //上传前判断
              let fileName = file.name
              let pos = fileName.lastIndexOf('.')
              let lastName = fileName.substring(pos, fileName.length)
              if (lastName.toLowerCase() !== '.zip' && lastName.toLowerCase() !== '.rar' && lastName.toLowerCase() !== '.doc' &&
                lastName.toLowerCase() !== '.docx' && lastName.toLowerCase() !== '.pdf' && lastName.toLowerCase() !== '.jpfg' &&
                lastName.toLowerCase() !== '.png') {
                this.$message.error('文件类型不正确！');
                return false;
              }
            },

            handleExceed(files, fileList) {
                this.$message.warning(`文件数量请小于 5（包含5）!`);
            },

            beforeRemove(file, fileList) {
              if (file && file.status==="success") {
                return this.$confirm(`确定移除 ${file.name}？`)
              }
            },
            fetchData() {
                this.listLoading = true;
                login().then(response => {
                    this.list = response.data.items;
                    this.list1 = response.data.detailsData;
                    this.listLoading = false
                })
            },
            jump() {
                this.$router.push({ path: './contractManagement' })
            },
            resetInfo(){
                if ('first' == this.saveType){
                    this.sn_supplier_contractDO = {}
                    /* {
                         contractname: null,
                             contstate: null,
                         createdate: null,
                         creator: null,
                         enddate: null,
                         operatedate: null,
                         operator: null,
                         remarks: null,
                         startdate: null,
                         signaturedate:null,
                         suppliercode: null
                     }*/
                    this.attachmentDORemarks = null
                    //this.auto = null
                }else{
                    this.priceInfo = {
                        suppliertype: null,
                        limitation: null,
                        delegatemode: null,
                        billingmode: null,
                        casepointtype: null,
                        targetarea: null,
                    }
                    this.addtimeRulesJson = [{
                        assignLimitationDay: null,
                        assignLimitationHour: null,
                        receiveLimitationDay: null,
                        receiveLimitationHour: null,
                        manageLimitationHour: null,
                        manageLimitationDay: null,
                        firstLimitationHour: null,
                        firstLimitationDay: null,
                        auditLimitationHour: null,
                        auditLimitationDay: null,
                        accraditationLimitationHour: null,
                        accraditationLimitationDay: null,
                        totalLimitationHour: null,
                        totalLimitationDay: null,
                        limitationunit:null,
                        tasktype:null,
                        warningratio:'80%'
                    }]
                  /*重置后，取消下拉框已选中的--张瑞*/
                  for(var i=0;i<this.options.length;i++){
                    this.options[i].is_true = true
                  }
                }
            },
            submitRuleInfo() {
                if ('first' == this.saveType){
                    // 验证通过之后的操作
                    this.$refs['contractRule'].validate((valid) => {
                        if (valid) {
                            // 验证通过之后的操作

                            submitContractInfo(this.getContractInfo()).then(response => {
                                this.saveStatus = response.status
                                if (response.status == '1'){
                                    //this.showTimeRulesJson = this.addtimeRulesJson
                                    this.showContractDisabled = true
                                    this.showContractDisabledButton = true
                                    this.contractno = response.data
                                    this.getContractAndSupplierInfo()
                                    this.$notify({
                                        title: '成功',
                                        message: '合同信息保存成功',
                                        type: 'success'
                                    });
                                } else if (response.status == '2'){
                                    this.$notify({
                                        title: '警告',
                                        message: '该合同已存在',
                                    });
                                }else if(response.status == '3'){
                                  this.$notify({
                                    title: '警告',
                                    message: '该合同不在有效范围内',
                                    });
                                }else if(response.status == '4') {
                                  this.$notify({
                                    title: '警告',
                                    message: '新合同起期早于当前有效合同止日期',
                                  });
                                }else{
                                    this.$notify({
                                        title: '错误',
                                        message: '合同信息保存失败',
                                    });
                                }
                            })
                            // ...
                        } else {
                            // 验证不通过之后的操作
                            // ...
                            return false;
                        }
                    });


                } else{
                    this.$refs['priceRule'].validate((valid) => {
                        if (valid) {
                            // 验证通过之后的操作
                            if(this.addtimeRulesJson[0]['tasktype'] == null){
                                this.$notify({
                                    title: '警告',
                                    message: '请先填写时效规则',
                                });
                                return false;
                            }else if(this.addtimeRulesJson[0]['receiveLimitationDay'] == null){
                            this.$notify({
                              title: '警告',
                              message: '请先填写接收时效',
                            });
                            return false;
                          }else if(this.addtimeRulesJson[0]['manageLimitationDay'] == null){
                            this.$notify({
                              title: '警告',
                              message: '请先填写处理时效',
                            });
                            return false;
                          }else if(this.addtimeRulesJson[0]['warningratio'] == null){
                              this.$notify({
                                title: '警告',
                                message: '请先填写预警时效比例',
                              });
                              return false;
                            }
                            submitRuleInfo(this.getRulesInfo()).then(response => {
                                //为什么 会成功
                                this.ruleStatus = response.status
                                if (response.status == '1'){
                                    this.showContractDisabledButton = true
                                    //this.showTimeRulesJson = this.addtimeRulesJson
                                    this.showDisabled = true
                                    this.$notify({
                                        title: '成功',
                                        message: '规则信息保存成功',
                                        type: 'success'
                                    });
                                }else {
                                    this.$notify({
                                        title: '错误',
                                        message: '规则保存失败',
                                    });
                                }
                            })
                            // ...
                        } else {
                            // 验证不通过之后的操作
                            // ...
                            return false;
                        }
                    });
                }
            },
            putPrice(index,childrenIndex, date){
                this.ruletypeL = null
                if(index == 'suppliertype'){
                    this.ruletypeL = '01'
                    this.rulecontentKey = this.priceInfo.suppliertype[childrenIndex]
                }else if (index == 'limitation'){
                    this.ruletypeL = '02'
                    this.rulecontentKey = this.priceInfo.limitation[childrenIndex]
                    /*                    this.priceInfo.limitation[childrenIndex].forEach((item,i) => {
                                            if(i != 0){
                                                this.rulecontentKey  =  this.rulecontentKey + ',' + item
                                            }
                                        })*/
                }else if (index == 'delegatemode'){
                    this.ruletypeL = '03'
                    this.rulecontentKey = this.priceInfo.delegatemode[childrenIndex]
                }else if (index == 'billingmode'){
                    this.ruletypeL = '04'
                    this.rulecontentKey = this.priceInfo.billingmode[childrenIndex]
                }else if (index == 'casepointtype'){
                    this.ruletypeL = '05'
                    this.rulecontentKey = this.priceInfo.casepointtype[childrenIndex]
                }else if (index == 'targetarea'){
                    this.ruletypeL = '06'
                    this.rulecontentKey = this.priceInfo.targetarea[childrenIndex][1]
                }
                this.sn_supplier_contract_priceDOL.push(

                    {
                        contractno: this.contractno,//没取到
                        suppliertype: this.suppliertype,
                        ruletype: this.ruletypeL,
                        rulecontent:this.rulecontentKey,
                        creator: 'test',
                        createdate: date,
                        operator: null,
                        operatedate: null,
                    }
                )
            },
            //合同基本信息及附件信息
            getContractInfo() {

                //合同信息临时变量
                //局部变量
                const  SupplierContractAttachmentVO = {
                  contractVO: {},
                  contractAttachmentVO: []
                };
                this.sn_supplier_contractDO.startdate = this.sn_supplier_contractDO.effectivedate[0]
                this.sn_supplier_contractDO.enddate =this.sn_supplier_contractDO.effectivedate[1]

                /*
                if(this.auto){
                    this.sn_supplier_contractDO.startdate = this.auto[0]
                    this.sn_supplier_contractDO.enddate = this.auto[1]
                }
                */
                this.sn_supplier_contractDO.signaturedate = this.sn_supplier_contractDO.signaturedate
                this.sn_supplier_contractDO.creator = 'test';
                this.sn_supplier_contractDO.suppliercode = this.suppliercode;
                let _tmp = this.fileList;
                let fileUrl
                let fileName
                if (this.fileList){
                    //优化判空等
                    for (var i = 0, len = _tmp.length; i < len; i++) {
                        if (_tmp[i] != null){
                            fileUrl = _tmp[i].url
                            fileName = _tmp[i].name
                        }
                      SupplierContractAttachmentVO.contractAttachmentVO.push({
                            contractno:this.suppliercode,
                            createdate: 'null',
                            creator: 'test',
                            //服务端存储地址
                            filepath: fileUrl,
                            name: fileName,
                            operatedate: null,
                            operator: null,
                            remarks: this.attachmentDORemarks,
                            state: '01',
                            type: '01'
                        });
                    }
                }
              SupplierContractAttachmentVO.contractVO = this.sn_supplier_contractDO;

                return SupplierContractAttachmentVO
            },
            //计费规则及时效规则信息
            getRulesInfo() {
                //时效临时变量
                const  SupplierContractPriceLimitationVO = {
                  contractLimitationsList: [],
                  contractPriceVOList:[]
                };
                if(this.addtimeRulesJson[0]['tasktype'] != null){
                    for (let i in this.addtimeRulesJson) {
                        for (let j in this.addtimeRulesJson[i]) {
                            if (j.indexOf("Day") != -1) {
                                this.index = this.index + 1;
                                var dateRule = new Date();
                                let date = {
                                    year: dateRule.getFullYear(),
                                    month: dateRule.getMonth() + 1,
                                    date: dateRule.getDate(),
                                };
                                console.log(date);
                                if (date.month < 10) {
                                    date.month = '0' + date.month;
                                }
                                if (date.date < 10) {
                                    date.date = '0' + date.date;
                                }
                                let systemDate = date.year + '-' + date.month + '-' + date.date;
                              SupplierContractPriceLimitationVO.contractLimitationsList.push(
                                    {
                                        contractno: this.contractno,
                                        createdate: systemDate,
                                        creator: 'test',
                                        limitationtype:null,
                                        limitationunit: '小时',
                                        operatedate: null,
                                        operator: null,
                                        tasktype: this.addtimeRulesJson[i]['tasktype'],
                                        warningratio: this.addtimeRulesJson[i]['warningratio'].split("%")[0],
                                        limitation1:null,
                                        limitation2:null
                                    }
                                )
                            }

                            if ('assignLimitationDay' == j){
                                console.log(this.addtimeRulesJson[i][j]);
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation1'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '01'
                            }else if ('assignLimitationHour' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation2'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '01'
                            }else if ('receiveLimitationDay' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation1'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '02'
                            }else if ('receiveLimitationHour' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation2'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '02'
                            }else if ('manageLimitationDay' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation1'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '03'
                            }else if ('manageLimitationHour' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation2'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '03'
                            }else if ('firstLimitationDay' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation1'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '04'
                            }else if ('firstLimitationHour' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation2'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '04'
                            }else if ('auditLimitationDay' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation1'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '05'
                            }else if ('auditLimitationHour' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation2'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '05'
                            }else if ('totalLimitationDay' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation1'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '06'
                            }else if ('totalLimitationHour' == j){
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitation2'] = this.addtimeRulesJson[i][j];
                              SupplierContractPriceLimitationVO.contractLimitationsList[this.index]['limitationtype'] = '06'
                            }
                        }

                    }
                }
                //时效规则信息合并

                for (let j in this.priceInfo) {
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
                        for (let k in this.priceInfo.suppliertype) {
                            this.putPrice(j,k,systemDate)
                        }

                    }else if ('limitation' == j){
                        for (let k in this.priceInfo.limitation) {
                            this.putPrice(j,k,systemDate)
                        }
                    }else if ('delegatemode' == j){
                        for (let k in this.priceInfo.delegatemode) {
                            this.putPrice(j,k,systemDate)
                        }
                    }else if ('billingmode' == j){
                        for (let k in this.priceInfo.billingmode) {
                            this.putPrice(j,k,systemDate)
                        }
                    }else if ('casepointtype' == j){
                        for (let k in this.priceInfo.casepointtype) {
                            this.putPrice(j,k,systemDate)
                        }
                    }else if ('targetarea' == j){
                        for (let k in this.priceInfo.targetarea) {
                            this.putPrice(j,k,systemDate)
                        }
                    }




                }


              SupplierContractPriceLimitationVO.contractPriceVOList = this.sn_supplier_contract_priceDOL;
                return SupplierContractPriceLimitationVO
            },
            //添加时效事件
            addRuleInfo() {
                this.showTime=true,//添加时效数据展示
                    this.showTimeHeader=false//展示时效数据表头

            },
            //tab切换时间
            handleSelect(tab, event) {
                console.log(tab, event);
                if (tab.name == 'first') {
                    if(this.saveStatus == '1'){
                        this.showContractDisabledButton = true
                    }
                    this.saveType = 'first'
                    this.tabType = '合同保存'
                }else {
                    if ('1' == this.saveStatus){
                        if(this.ruleStatus == 0){
                            this.showContractDisabledButton = false
                            this.saveType = 'second'
                            this.tabType = '规则保存'
                        }

                    }
                }
            },
            getParams(){
                // 取到路由带过来的参数
                this.suppliercode = this.$route.query.suppliercode;
                this.suppliertype = this.$route.query.suppliertype
            },
            getContractAndSupplierInfo(){
                getContractAndSupplierInfo(this.suppliercode).then(response => {
                    // console.log(response);
                    // console.log(JSON.stringify(response));
                    this.contractInfo = response.data.sn_supplier_contractDOList;
                    this.supplierInfo = response.data.sn_supplierDO;
                    this.contractTotal = response.data.sn_supplier_contractDOList.length;
                    this.contractPageInfo = this.contractInfo.slice(0,this.pageSize);
                    // alert(JSON.stringify(this.list));
                    console.log(JSON.stringify(response));
                    this.listLoading = false
                })
            },
          //调查类型
          getaccountingRulesstatus(){
            const dictName = 'investigate_type'
            get(dictName).then(response => {
              this.accountingRulesstatus = response.content
            }).catch(error => {
              console.log(error)
            })

          },
          //时效类型
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
                  /*修改提示信息--张瑞*/
                    this.$message.warning('默认格式x*24，x为正数，禁止输入小数以及负数')
                    e.target.value = ' '
                }
                if(f>99|f==0){
                    this.$message.warning('默认格式x*24，x为正数，禁止输入三位数')
                    e.target.value = ' '
                }
                if(f==0){
                    this.$message.warning('默认格式x*24，x为正数，禁止输入0')
                    e.target.value = ' '
                }
            },
            updateClick() {
                if (this.suppliercode == null || this.suppliercode == '') {
                    alert('供应商为空')
                }
                this.$router.push({
                    path:'updateContract', //编辑页面
                    query: {
                        surveyNo: this.suppliercode,
                    }
                })
            },
            /*换页*/
            handleSizeChange(pageSize) {
                this.pageSize = pageSize;
                /*更换页码size后从第一页开始*/
                this.currentPage = 1;
                this.contractPageInfo = this.contractInfo.slice(0,this.pageSize);
            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage;
                this.contractPageInfo = this.contractInfo.slice(this.pageSize * (currentPage - 1), this.pageSize * currentPage)
            },
            beforeLeave(item) {
                if ('1' != this.saveStatus){
                    if ('second' == item){

                        this.$notify({
                            title: '警告',
                            message: '请先保存合同信息',
                            type: 'warning'
                        });
                        return false
                    }
                }
            },
            sumLimitation(value){

/*                    this.addtimeRulesJson.totalLimitationDay = 0
                this.addtimeRulesJson.totalLimitationHour = 0
                for(let j in this.addtimeRulesJson[0]){
                    if(j.indexOf("Day") != -1){
                        this.addtimeRulesJson.totalLimitationDay = parseInt(this.addtimeRulesJson[0][j]) + this.addtimeRulesJson.totalLimitationDay
                    }else {
                        this.addtimeRulesJson.totalLimitationHour = parseInt(this.addtimeRulesJson[0][j]) + this.addtimeRulesJson.totalLimitationHour
                    }
                }
              console.log(value)*/
            },
            /*时效规则取消*/
            cancelTime(index) {
                this.addtimeRulesJson = null
                this.showTime = false
                this.showTimeHeader = true
            },
            getAddressData(){
                getAddress().then(response => {
                    this.caseArea = response.data
                    for (let k = 0; k < this.caseArea.length; k++) {
                        for (let l = 0; l < this.caseArea[k].children.length; l++) {
                            this.caseArea[k].children[l].children = null
                        }
                    }

                    for (let k = 0; k < this.targetareastatus.length; k++) {
                        for (let l = 0; l < this.targetareastatus[k].children.length; l++) {
                            this.targetareastatus[k].children[l].children=null

                        }
                    }

                    console.log(this.caseArea+"xax")
                }).catch(error => {
                    console.log(error)
                })
            }
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
