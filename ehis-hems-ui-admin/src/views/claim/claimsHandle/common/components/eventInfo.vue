
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">事件信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button :disabled="node ==='physical'" type="primary" size="mini" @click="openDia">出险信息</el-button>
          <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" type="primary" size="mini" @click="invoiceHandle">发票补录</el-button>
        </span>
        <el-row>
          <el-table
            ref="table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="eventInfo"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;"
            @selection-change="handleSelectChange">
            <el-table-column type="expand">
              <template slot-scope="props">
                <div style="margin-bottom: 20px;">
                  <p style="margin-top: 0; font-weight: 500;" v-if="props.row.casediseaselist && props.row.casediseaselist.length!=0">疾病信息</p>
                  <el-row style="margin-left: 20px;" v-for="(list,index) in props.row.casediseaselist" :key="index">
                    <el-col :span="4">疾病类型：{{selectDictLabel(diseasetypeOptions,list.diseasetype)}}</el-col>
                    <el-col :span="4">
                      <el-tooltip class="item" effect="dark" :content="list.diseasename" placement="top-start">
                        <span type="text" class="overEslap_class">疾病名称：{{list.diseasename}}</span>
                      </el-tooltip>
                    </el-col>
                    <el-col :span="4">
                      <!-- :content="list.judgmentbasisname" -->
                      <el-tooltip class="item" effect="dark" placement="top-start">
                        <div slot="content" v-for="(item,i) in list.judgmentbasisnameList" :key="i">
                          <p>{{item}}</p>
                        </div>
                        <span type="text" class="overEslap_class">判断依据：{{list.judgmentbasisname}}</span>
                      </el-tooltip>
                    </el-col>
                    <el-col :span="4">风险等级：{{selectDictLabel(risk_levelOptions,list.risklevel)}}</el-col>
                    <el-col :span="4">认定结果：{{selectDictLabel(IdentificationresultsOptions,list.identificationresults)}}</el-col>
                    <el-col :span="4">确诊日期：{{list.diagnosisdate |formatApprai}}</el-col>
                  </el-row>
                  <p style="margin-top: 0; font-weight: 500;" v-if="props.row.disabilityList && props.row.disabilityList.length!=0">伤残信息</p>
                  <el-row style="margin-left: 20px;" v-for="(list,index) in props.row.disabilityList" :key="index+1">
                    <el-col :span="6">
                      <el-tooltip class="item" effect="dark" :content="list.disabilityname" placement="top-start">
                        <span type="text" class="overEslap_class"> 伤残名称：{{list.disabilityname}}</span>
                      </el-tooltip>
                    </el-col>
                    <el-col :span="6">伤残级别：{{list.disabilitylevel}}</el-col>
                    <el-col :span="6">鉴定日期：{{list.appraisaldate | formatApprai}}</el-col>
                    <el-col :span="6">给付比例：{{list.paymentratio |numFilter}}</el-col>
                  </el-row>
                  <p style="margin-top: 0; font-weight: 500;" v-if="props.row.caseDeformityList && props.row.caseDeformityList.length !=0">残疾信息</p>
                  <el-row style="margin-left: 20px;" v-for="(list,index) in props.row.caseDeformityList" :key="index">
                    <el-col :span="6">
                      <el-tooltip class="item" effect="dark" :content="list.disabilityname" placement="top-start">
                        <span type="text" class="overEslap_class"> 残疾名称：{{list.disabilityname}}</span>
                      </el-tooltip>
                    </el-col>
                    <el-col :span="6">残疾级别：{{list.disabilitylevel}}</el-col>
                    <el-col :span="6">鉴定日期：{{list.appraisaldate | formatApprai}}</el-col>
                    <el-col :span="6">给付比例：{{list.paymentratio|numFilter}}</el-col>
                  </el-row>
                  <p style="font-weight: 500;"  v-if="props.row.operationList !=null">手术信息</p>
                  <el-row style="margin-left: 20px;" v-for="(list,index) in props.row.operationList" :key="index+ 1">
                    <el-col :span="6">
                      <el-tooltip class="item" effect="dark" :content="list.operationname" placement="top-start">
                        <span type="text" class="overEslap_class">手术名称：{{list.operationname}}</span>
                      </el-tooltip>
                    </el-col>
                    <el-col :span="6">手术等级：{{list.operationlevel}}</el-col>
                  </el-row>
                  <p style="font-weight: 500;" v-if="props.row.caseAccidentlist && props.row.caseAccidentlist.length !=0">意外名称</p>
                  <el-row style="margin-left: 20px;" v-for="(list,index) in props.row.caseAccidentlist" :key="index + 1">
                    <el-col :span="6">
                      <el-tooltip class="item" effect="dark" :content="list.accidentdescription" placement="top-start">
                        <span type="text" class="overEslap_class">意外名称：{{list.accidentdescription}}</span>
                      </el-tooltip>
                    </el-col>
                    <el-col :span="6">出险时职业名称：{{selectDictLabel(payee_occupationOptions,list.occupationname)}}</el-col>
                    <el-col :span="6">出险时职业类别：{{selectDictLabel(occupationnameOptions,list.occupationcategory)}}</el-col>
                    <el-col :span="6">
                      <el-tooltip class="item" effect="dark" :content="list.remarks" placement="top-start">
                        <span type="text" class="overEslap_class"> 备注：{{list.remarks}}</span>
                      </el-tooltip>
                    </el-col>
                  </el-row>
                </div>
                <el-table
                  :data="props.row.invoicelist"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  highlight-current-row
                  tooltip-effect="dark">
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-table
                        :data="props.row.invoicedetail"
                        :header-cell-style="{color:'black',background:'#f8f8ff'}"
                        size="small"
                        highlight-current-row
                        tooltip-effect="dark">
                        <el-table-column property="feetiem" label="费用项名称" align="center"></el-table-column>
                        <el-table-column property="amount" label="总金额" align="center">
                          <template slot-scope="scope">
                            <span>{{scope.row.amount|numFilter}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column property="paycategoryamount" label="比例自付" align="center">
                          <template slot-scope="scope">
                            <span>{{scope.row.paycategoryamount|numFilter}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column property="ownamount" label="自费金额" align="center">
                          <template slot-scope="scope">
                            <span>{{scope.row.ownamount|numFilter}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column property="overallpayment" label="统筹支付" align="center">
                          <template slot-scope="scope">
                            <span>{{scope.row.overallpayment|numFilter}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column property="thirdpartypayment" label="第三方支付" align="center">
                          <template slot-scope="scope">
                            <span>{{scope.row.thirdpartypayment|numFilter}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column property="unreasonableamount" label="不合理金额" align="center">
                          <template slot-scope="scope">
                            <span>{{scope.row.unreasonableamount|numFilter}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column property="unreasonableexplain" label="不合理原因" align="center"></el-table-column>
                      </el-table>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" width="50">
                    <template slot-scope="scope">
                      <el-radio :disabled="selIncidentno!==scope.row.incidentno" v-model="selRadio" :label="scope.$index" @change="val => invoiceSelHandle(scope.row)"></el-radio>
                    </template>
                  </el-table-column>
                  <el-table-column type="index" align="center" label="序号"></el-table-column>
<!--                  <el-table-column property="invoiceno" label="发票号" align="center"></el-table-column>-->
                  <el-table-column align="center" prop="invoiceno" label="发票号" show-overflow-tooltip>
                    <template slot-scope="scope" style="display: flex;justify-content: space-between">
                      <span v-if="scope.row.pastClaimNo === ''">{{scope.row.invoiceno}}</span>
                      <el-tooltip v-else class="item" effect="dark" :content="'重复理赔，既往赔案号' + scope.row.pastClaimNo" placement="top-start">
                        <span style="color: red;">
                          <span>{{scope.row.invoiceno}}</span>
                          <span class="el-icon-warning"></span>
                        </span>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                  <el-table-column property="invoicetype" label="发票类型" :formatter="getInvoicetype" align="center"></el-table-column>
                  <el-table-column property="hospitalname" label="医院名称" align="center"></el-table-column>
                  <el-table-column property="visitdate" label="就诊区间" align="center">
                    <template slot-scope="scope">
                      {{scope.row.visitdate}} ~ {{scope.row.dischargedate}}
                    </template>
                  </el-table-column>
                  <el-table-column property="amount" label="总金额" align="center">
                    <template slot-scope="scope">
                      <span>{{scope.row.amount|numFilter}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column property="overallpayment" label="统筹支付" align="center">
                    <template slot-scope="scope">
                      <span>{{scope.row.overallpayment|numFilter}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column property="thirdpartypayment" label="第三方支付" align="center">
                    <template slot-scope="scope">
                      <span>{{scope.row.thirdpartypayment|numFilter}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column property="paybycategory" label="比例自付" align="center">
                    <template slot-scope="scope">
                      <span>{{scope.row.paybycategory|numFilter}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column property="ownamount" label="自费金额" align="center">
                    <template slot-scope="scope">
                      <span>{{scope.row.ownamount|numFilter}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作" align="center" width="120">
                    <template slot-scope="props">
                      <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" type="text" size="mini" @click="openTransDia(props.row)">转移</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </template>
            </el-table-column>
            <el-table-column type="selection" width="50" align="center"/>
            <el-table-column label="事件号" prop="incidentno" min-width="140" align="center"></el-table-column>
            <el-table-column label="申请类别" prop="categorycode" :formatter="formatApplicaReason"  align="center">
              <template slot-scope="scope">
                <span>{{selectDictLabel(apply_typeOptions,scope.row.categorycode)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="出险日期" prop="eventdate" align="center" width="100"></el-table-column>
            <el-table-column label="就诊区间" align="center" width="160">
              <template slot-scope="scope">
                <span>{{ scope.row.visitdate|formatApprai}}至{{ scope.row.dischargedate }}</span>
              </template>
            </el-table-column>
            <el-table-column label="就诊天数" prop="visitday" align="center"></el-table-column>
            <el-table-column label="出险类型" prop="incidenttype" align="center">
              <template slot-scope="scope">
                <span>{{selectDictLabel(incidenttypeOptions,scope.row.incidenttype)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="事件信息" prop="incident" align="center">
              <template slot-scope="scope">
                <el-tooltip class="item" effect="dark" :content="scope.row.incident" placement="top-start">
                  <span type="text" class="incident_class">{{scope.row.incident}}</span>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column label="医院" prop="hospitalname" align="center"></el-table-column>
            <el-table-column label="科室" prop="departmentname" align="center"></el-table-column>
            <el-table-column label="诊断" width="140" prop="maindiagnosisname" align="center">
              <template slot-scope="scope">
                <el-popover
                  :ref="`popover-${scope.$index}`"
                  placement="right"
                  width="460"
                  trigger="hover">
                  <el-table :data="[scope.row]"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    size="small">
                    <el-table-column property="maindiagnosisname" align="center" label="第一诊断"></el-table-column>
                    <el-table-column property="secondarydiagnosisname" align="center" label="第二诊断"></el-table-column>
                    <el-table-column property="otherdiagnosisname" align="center" label="其他诊断"></el-table-column>
                  </el-table>
                  <el-button slot="reference" type="text" size="mini">{{ scope.row.maindiagnosisname }}</el-button>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="肿瘤形态学名称" prop="tumorcode" :formatter="getTumormorphologicalcode" align="center" width="110"></el-table-column>
            <el-table-column label="等级" prop="hospitallevel" align="center">
              <template slot-scope="scope">
                {{scope.row.hospitallevel==='其他其他'?'其他':scope.row.hospitallevel}}
              </template>
            </el-table-column>
            <el-table-column label="发生地" prop="area" align="center" width="160"></el-table-column>
            <el-table-column label="操作" width="140" align="center">
              <template slot-scope="scope">
                <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 0)">编辑</el-button>
                <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" style="margin-bottom: 10px;" type="text" size="mini" @click="delEventHandle(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(0)"> + 添加</el-button>
        </el-row>
      </el-collapse-item>
    </el-collapse>
    <!-- 出险信息模态框 -->
    <el-dialog
      :visible.sync="dialogVis"
      :before-close="riskDiaClosed"
      :close-on-click-modal="false"
      @opened="openedModel"
      :lock-scroll="true"
      title="出险信息"
      width="90%"
      append-to-body>
      <el-form :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" ref="baseForm" :rules="tableFormRules" label-width="0" :model="baseForm" size="mini">
        <el-row v-if="baseForm.casediseaselist" class="judeClass">
          <el-table
            ref="table1"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.casediseaselist"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column type="index" width="55" label="序号" align="center"/>
            <el-table-column label="疾病类型" width="130" prop="diseasetype" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.diseasetype'" :rules="tableFormRules.diseasetype">
                  <el-select v-model="scope.row.diseasetype" style="width: 100%;" placeholder="请选择" :disabled="scope.row.editFlag && !scope.row.editFlagT" @change="changeType(scope.row.diseasetype,scope.$index)" clearable>
                    <el-option v-for="item in dict.diseasetype" :label="item.label"
                      :value="item.value" :key="item.value"/>
                  </el-select>
                </el-form-item>
                <span v-else>{{selectDictLabel(diseasetypeOptions,scope.row.diseasetype) }}</span>
              </template>
            </el-table-column>
            <el-table-column label="疾病名称" prop="diseasecode" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.diseasecode'" :rules="tableFormRules.diseasecode">
                  <el-select v-model="scope.row.diseasecode"
                    :remote-method="remotejibinMethods"
                    filterable
                    remote
                    reserve-keyword
                    :disabled="scope.row.editFlag && !scope.row.editFlagT"
                   style="width: 100%;" @change="val => changeName(scope.row,scope.$index)" @focus="focusName(scope.row,scope.$index)" placeholder="请输入关键字搜索" clearable>
                    <el-option v-for="(item,index) in stateListArr" :label="item.name" :value="item.value" :key="index"
                    :disabled="baseForm.casediseaselist.find(f=>{
                      return (f.diseasecode+'-'+f.diseasename == item.name)}) != null"/>
                  </el-select>
                </el-form-item>
                <span v-else>{{scope.row.diseasename}}</span>
              </template>
            </el-table-column>
            <el-table-column label="判断依据" align="center" width="160">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.judgmentbasis'" :rules="tableFormRules.judgmentbasis">
                  <el-select v-model="scope.row.judgmentbasis" multiple  style="width: 100%" placeholder="请选择" clearable>
                    <el-option v-for="(item,index) in scope.row.reList" :label="item.name" :value="item.value" :key="index"/>
                  </el-select>
                </el-form-item>
                <el-tooltip v-else class="item" effect="dark" placement="top-start">
                  <div slot="content" v-for="(item,i) in scope.row.judgmentbasisNameList" :key="i">
                    <p>{{item}}</p>
                  </div>
                  <el-button type="text" style="color: #555; font-size: 12px;">{{scope.row.judgmentbasisName}}</el-button>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column label="风险等级" prop="risklevel" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.risklevel'" :rules="tableFormRules.risklevel">
                  <el-select v-model="scope.row.risklevel" style="width: 100%;" placeholder="请选择" clearable>
                    <el-option v-for="item in risk_levelOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                  </el-select>
                </el-form-item>
                <span v-else>{{selectDictLabel(risk_levelOptions,scope.row.risklevel)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="认定结果" prop="identificationresults" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.identificationresults'" :rules="tableFormRules.identificationresults">
                  <el-select v-model="scope.row.identificationresults" style="width: 100%;" placeholder="请选择" clearable>
                    <el-option v-for="(item,index) in dict.Identificationresults" :label="item.label" :value="item.value" :key="index"/>
                  </el-select>
                </el-form-item>
                <span v-else>{{scope.row.identificationresults == null ? '' : scope.row.identificationresults  == 'Y' ? '成立':'不成立'}}</span>
              </template>
            </el-table-column>
            <el-table-column label="确诊日期" prop="diagnosisdate" align="center" width="150">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.diagnosisdate'" :rules="tableFormRules.diagnosisdate">
                  <el-date-picker
                    v-model="scope.row.diagnosisdate"
                    value-format="yyyy-MM-dd"
                    style="width: 140px"
                    type="date"
                    :picker-options="timeChange"
                    placeholder="选择日期"/>
                </el-form-item>
                <span v-else>{{scope.row.diagnosisdate|formatApprai}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag">
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 1, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 1)">编辑</el-button>
                </el-form-item>
                <span v-else>
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 1, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 1)">编辑</el-button>
                </span>
              </template>
            </el-table-column>
          </el-table>
          <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(1)"> + 添加</el-button>
        </el-row>
        <!-- 伤残模块 -->
        <el-row v-if="baseForm.casedisabilitylist">
          <el-table
            ref="table2"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.casedisabilitylist"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column type="index" width="55" label="序号" align="center"/>
            <el-table-column label="伤残名称" prop="disabilitycode" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedisabilitylist.' + scope.$index + '.disabilitycode'" :rules="tableFormRules.disabilitycode">
                <!-- <el-form-item :prop="'casedisabilitylist.' + scope.$index + '.disabilitycode'"> -->
                  <el-select v-model="scope.row.disabilitycode"
                    ref="disabilitycode"
                    :disabled="scope.row.editFlag && !scope.row.editFlagT"
                    :remote-method="remoteDisabilityMethods"
                    filterable
                    remote
                    reserve-keyword
                    style="width: 100%;" @change="changeDisability(scope.row.disabilitycode,scope.$index)" @focus="focusDisability(scope.row.disabilitycode,scope.$index)" placeholder="请输入关键字搜索" clearable>
                    <el-option v-for="(operat,index) in disease08" :label="operat.name" :value="operat.value" :key="index"
                     :disabled="baseForm.casedisabilitylist.find(f=>{
                      return f.disabilityname == operat.name }) != null"
                    />
                  </el-select>
                </el-form-item>
                <span v-else>{{scope.row.disabilityname}}</span>
              </template>
            </el-table-column>
            <el-table-column label="伤残级别" prop="disabilitylevel" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedisabilitylist.' + scope.$index + '.disabilitylevel'" :rules="tableFormRules.disabilitylevel">
                  <el-input v-model="scope.row.disabilitylevel"  @input="inputdisabilitylevelFlag(scope.$index,scope.row.disabilitylevel)"/>
                </el-form-item>
                <span v-else>{{scope.row.disabilitylevel}}</span>
              </template>
            </el-table-column>
            <el-table-column label="鉴定日期" prop="appraisaldate" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedisabilitylist.' + scope.$index + '.appraisaldate'" :rules="tableFormRules.appraisaldate">
                  <el-date-picker
                    v-model="scope.row.appraisaldate"
                    style="width: 100%"
                    value-format="yyyy-MM-dd"
                    type="date"
                    :picker-options="timeChange"
                    placeholder="选择日期"/>
                </el-form-item>
                <span v-else>{{scope.row.appraisaldate|formatApprai}}</span>
              </template>
            </el-table-column>
            <el-table-column label="给付比例" prop="paymentratio" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedisabilitylist.' + scope.$index + '.paymentratio'" :rules="tableFormRules.paymentratio">
                  <el-input-number v-model="scope.row.paymentratio" style="width:100%" :min="0" :max="100"/>
                </el-form-item>
                <span v-else>{{scope.row.paymentratio}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag">
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 2, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 2)">编辑</el-button>
                </el-form-item>
                <span v-else>
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 2, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 2)">编辑</el-button>
                </span>
              </template>
            </el-table-column>
          </el-table>
          <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(2)"> + 添加</el-button>
        </el-row>
        <!-- 残疾 -->
        <el-row v-if="baseForm.casedeformitylist">
          <el-table
            ref="table5"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.casedeformitylist"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column type="index" width="55" label="序号" align="center"/>
            <el-table-column label="残疾名称" prop="disabilitycode" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedeformitylist.' + scope.$index + '.disabilitycode'" :rules="tableFormRules.disabilitycode">
                  <el-select v-model="scope.row.disabilitycode"
                    :remote-method="remotecanjiMethods"
                    filterable
                    :disabled="scope.row.editFlag && !scope.row.editFlagT"
                    remote
                    reserve-keyword
                    style="width: 100%;" @change="changeCasede(scope.row.disabilitycode,scope.$index)" @focus="focusCasede(scope.row.disabilitycode,scope.$index)" placeholder="请输入关键字搜索" clearable>
                    <el-option v-for="(operat,index) in disease06" :label="operat.name" :value="operat.value" :key="index"
                      :disabled="baseForm.casedeformitylist.find(f=>{
                        return f.disabilityname == operat.name }) != null"
                    />
                  </el-select>
                </el-form-item>
                <span v-else>{{scope.row.disabilityname}}</span>
              </template>
            </el-table-column>
            <el-table-column label="残疾级别" prop="disabilitylevel" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedeformitylist.' + scope.$index + '.disabilitylevel'" :rules="tableFormRules.disabilitylevel">
                  <el-input v-model="scope.row.disabilitylevel"/>
                </el-form-item>
                <span v-else>{{scope.row.disabilitylevel}}</span>
              </template>
            </el-table-column>
            <el-table-column label="鉴定日期" prop="appraisaldate" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedeformitylist.' + scope.$index + '.appraisaldate'" :rules="tableFormRules.appraisaldate">
                  <el-date-picker
                    v-model="scope.row.appraisaldate"
                    style="width: 100%"
                    type="date"
                    :picker-options="timeChange"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"/>
                </el-form-item>
                <span v-else>{{scope.row.appraisaldate|formatApprai}}</span>
              </template>
            </el-table-column>
            <el-table-column label="给付比例" prop="paymentratio" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'casedeformitylist.' + scope.$index + '.paymentratio'"  :rules="tableFormRules.paymentratio">
                  <el-input-number v-model="scope.row.paymentratio" style="width:100%" :min="0" :max="100"/>
                </el-form-item>
                <span v-else>{{scope.row.paymentratio}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag">
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 5, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 5)">编辑</el-button>
                </el-form-item>
                <span v-else>
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 5, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 5)">编辑</el-button>
                </span>
              </template>
            </el-table-column>
          </el-table>
          <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(5)"> + 添加</el-button>
        </el-row>
        <el-row v-if="baseForm.caseoperationlist">
          <el-table
            ref="table3"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.caseoperationlist"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column type="index" width="55" label="序号" align="center"/>
            <el-table-column label="手术名称" prop="operationcode" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'caseoperationlist.' + scope.$index + '.operationcode'" :rules="tableFormRules.operationcode">
                  <el-select v-model="scope.row.operationcode" style="width: 100%;"
                    :remote-method="remoteshoushuMethods"
                    filterable
                    :disabled="scope.row.editFlag && !scope.row.editFlagT"
                    remote
                    reserve-keyword
                    @focus="focusOperationName(scope.row.operationcode,scope.$index)"
                   @change="changeOperationName(scope.row.operationcode,scope.$index)" placeholder="请输入关键字搜索" clearable>
                    <el-option v-for="(operat,index) in disease07" :label="operat.name" :value="operat.value" :key="index"
                      :disabled="baseForm.caseoperationlist.find(f=>{
                        return f.operationname == operat.name }) != null"
                    />
                  </el-select>
                </el-form-item>
                <span v-else>{{scope.row.operationname}}</span>
              </template>
            </el-table-column>
            <el-table-column label="手术等级" prop="operationlevel" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'caseoperationlist.' + scope.$index + '.operationlevel'" :rules="tableFormRules.operationlevel">
                  <el-input @input="inputdisabilitylevel(scope.$index,scope.row.operationlevel)" v-model="scope.row.operationlevel"/>
                </el-form-item>
                <span v-else>{{scope.row.operationlevel}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag">
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 3, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 3)">编辑</el-button>
                </el-form-item>
                <span v-else>
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 3, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 3)">编辑</el-button>
                </span>
              </template>
            </el-table-column>
          </el-table>
          <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(3)"> + 添加</el-button>
        </el-row>
        <el-row v-if="baseForm.caseaccidentlist">
          <el-table
            ref="table4"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="baseForm.caseaccidentlist"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style="width: 100%;">
            <el-table-column type="index" width="55" label="序号" align="center"/>
            <el-table-column label="意外名称" prop="accidentcode" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'caseaccidentlist.' + scope.$index + '.accidentcode'" :rules="tableFormRules.accidentcode">
                  <el-select v-model="scope.row.accidentcode"
                    :remote-method="remoteyiwaiMethods"
                    filterable
                    remote
                    :disabled="scope.row.editFlag && !scope.row.editFlagT"
                    reserve-keyword
                    @focus="focusAccidentcode(scope.row,scope.$index)"
                    style="width: 100%;"  placeholder="请输入关键字搜索" clearable>
                    <el-option v-for="(operat,index) in disease02" :label="operat.name" :value="operat.value" :key="index"
                      :disabled="baseForm.caseaccidentlist.find(f=>{
                        return f.accidentdescription == operat.name }) != null"
                    />
                  </el-select>
                </el-form-item>
                <span v-else>{{scope.row.accidentdescription}}</span>
              </template>
            </el-table-column>
            <el-table-column label="出险时职业名称" prop="occupationname" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'caseaccidentlist.' + scope.$index + '.occupationname'" :rules="tableFormRules.occupationname">
                  <el-select v-model="scope.row.occupationname" filterable style="width: 100%;" placeholder="请选择" clearable>
                    <el-option v-for="(item,index) in dict.payee_occupation" :label="item.label" :value="item.value" :key="index"/>
                  </el-select>
                </el-form-item>
                <span v-else>{{this.selectDictLabel(this.payee_occupationOptions,scope.row.occupationname)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="出险时职业类别" prop="occupationcategory" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'caseaccidentlist.' + scope.$index + '.occupationcategory'" :rules="tableFormRules.occupationcategory">
                  <el-select v-model="scope.row.occupationcategory" style="width: 100%;" placeholder="请选择" clearable>
                    <el-option v-for="(item,index) in occupationnameOptions" :label="item.dictLabel" :value="item.dictValue" :key="index"/>
                  </el-select>
                </el-form-item>
                <span v-else>{{selectDictLabel(occupationnameOptions,scope.row.occupationcategory)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="备注" prop="remarks" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag" :prop="'caseaccidentlist.' + scope.$index + '.remarks'">
                  <el-input v-model="scope.row.remarks" style="width: 200px;" :maxlength="500" placeholder="请选择" clearable></el-input>
                </el-form-item>
                <span v-else>{{scope.row.remarks}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="120">
              <template slot-scope="scope">
                <el-form-item v-if="scope.row.editFlag">
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 4, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 4)">编辑</el-button>
                </el-form-item>
                <span v-else>
                  <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, 4, scope.row)">删除</el-button>
                  <el-button v-if="scope.row.creator" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row, 4)">编辑</el-button>
                </span>
              </template>
            </el-table-column>
          </el-table>
          <el-button v-if="node!=='report'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(4)"> + 添加</el-button>
        </el-row>
        <el-row style="text-align: right; margin-top: 10px;">
          <el-button type="primary" size="mini" @click="allSaveHandle(1)">保 存</el-button>
        </el-row>
      </el-form>
    </el-dialog>
    <!-- 事件信息模态框  -->
    <el-dialog :visible.sync="addEventDia" :title="title" :close-on-click-modal="false" width="90%" append-to-body
      :before-close="eventDiaClosed"
      @open="openHandle">
      <el-form ref="addForm" :model="addForm" :rules="addFormRules" label-width="140px" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="申请类别：" prop="categorycode">
              <el-select v-model="addForm.categorycode" multiple collapse-tags class="item-width"  @change="changeTumorcode" placeholder="请选择">
                <el-option v-for="item in apply_typeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="addForm.eventdate"
                class="item-width"
                value-format="yyyy-MM-dd"
                type="date"
                :disabled="true"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊区间：" prop="daterange">
              <el-date-picker
                v-model="addForm.daterange"
                class="item-width"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="出险类型：" prop="incidenttype">
              <el-select v-model="addForm.incidenttype" class="item-width" placeholder="请选择">
                <el-option v-for="item in incidenttypeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="事件信息：" prop="incident">
              <el-input v-model="addForm.incident" class="item-width" maxlength="500" show-word-limit autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院：" prop="hospitalcode">
              <el-select
                class="item-width"
                v-model="addForm.hospitalcode"
                :remote-method="remoteHospitals"
                @change="changeHospital"
                filterable
                remote
                reserve-keyword
                placeholder="请输入医院关键字"
                clearable>
                <el-option v-for="option in hospitals" :key="option.id" :label="option.label" :value="option.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="departmentname">
              <el-input v-model="addForm.departmentname" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="肿瘤形态学名称：" prop="tumorcode">
              <el-select v-model="addForm.tumorcode" class="item-width" placeholder="请选择" clearable filterable>
                <el-option v-for="item in tumormorphologicalcodeArr" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="等级：" prop="hospitallevel">
              <el-input v-model="addForm.hospitallevel" :disabled="true" class="item-width" autocomplete="off"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="诊断：" prop="maindiagnosis">
              <el-select v-model="addForm.maindiagnosis"
                reserve-keyword
                filterable
                :remote-method="remoteICD"
                remote
                clearable
                class="item-width"
                placeholder="请选择">
                <el-option v-for="(item, i) in ICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="第二诊断：" prop="secondarydiagnosis">
              <el-select v-model="addForm.secondarydiagnosis"
                :remote-method="secondarydiagnosisICD"
                filterable
                remote
                reserve-keyword
                clearable
                class="item-width"
                placeholder="请选择">
                <el-option v-for="(item, i) in secondarydiagnosisICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="其他诊断：" prop="otherdiagnosis">
              <el-select v-model="addForm.otherdiagnosis"
                ref="otherdia"
                :remote-method="otherdiagnosisICD"
                filterable
                multiple
                remote
                reserve-keyword
                clearable
                class="item-width"
                placeholder="请选择"
                @change="otherdiaChange">
                <el-option v-for="(item, i) in otherdiagnosisICDArr" :label="item.label + '-' +item.value" :value="item.value" :key="i"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发生地：" prop="address">
              <el-cascader
                v-model="addForm.address"
                :options="address"
                class="item-width"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="eventDiaClosed">取 消</el-button>
        <el-button size="mini" type="primary" :loading="saveEventLoading" @click="saveEvent">保 存</el-button>
      </div>
    </el-dialog>
    <!-- 转移弹框 -->
    <el-dialog title="事件号" :visible.sync="transDiaVis" width="40%" :close-on-click-modal="false" append-to-body>
      <el-form :model="transForm" ref="transForm" size="mini" label-width="100px">
        <el-form-item label="事件号" label-width="100" prop="incidentno"
          :rules="{ required: true, message: '请选择！', trigger: 'change' }">
          <el-select v-model="transForm.incidentno" placeholder="请选择事件号">
            <el-option v-for="item in eventInfo" :label="item.incidentno" :value="item.incidentno" :key="item.incidentno"
              :disabled="transParams.incidentno===item.incidentno"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="transDiaClose" size="mini">取 消</el-button>
        <el-button type="primary" @click="transformHandle" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
import {getInDangerInfo, saveDangerInfo, saveEventInfo, deleteDangerInfo, saveEventRelationBills, getFirstMedicalInstitution, getLikeICD10,getICD10ByCode, getHosptailByCode, getInfoByName,getaccident, getProofNameByCode, deleteEvent,getProofNameBySubCode } from '@/api/claim/handleDeal'
import moment from 'moment'
import {encrypt} from "@/utils/rsaEncrypt"
import { getHospitalLike } from '@/api/claim/input'
import getAddressByFront from "../mixins/address"
let incidentnolist = []
export default {
  mixins:[getAddressByFront],
  inject: ['getEventInfoList'],
  //dicts: ['apply_type', 'incidenttype','payee_occupation' ,'diseasetype', 'risk_code', 'risk_level', 'disability_code', 'deformity_code', 'operation_code', 'accident_type','invoicetype','tumormorphologicalcode','occupationname', 'Identificationresults'],
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: {
      type: String,
      default: ''
    },
    eventInfo: {
      type: Array,
      default: function() {
        return [];
      }
    },
    insuredInfo: Object,
    accidentdate: String,
  },
  filters: {
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '') {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  data() {
    return {
      activeNames: ['1'],
      saveEventLoading: false,
      selRadio: false,
      inciSelRadio: null,
      title: '新增事件信息',
      operatList: [
        {label: '一', value: '1'},
        {label: '二', value: '2'},
      ],
      hospitals: [],
      tumormorphologicalcodeArr: [],
      routerList: [],
      visible: false,
      flag: false,
      ICDArr: [],
      otherdiagnosisICDArr:[],
      secondarydiagnosisICDArr: [],
      stateFrom: {},
      // diagnForm: {},
      incidentnolist: [],
      timeChange: {
        disabledDate(time){
          return time.getTime() > Date.now()  //选择时间范围 ||
        }
      },
      tableData: [{ policyNo: 'QWE23423645', diagnosis: '上呼吸道', days: 5, subTable: [{ policyNo: 'QWE23423645', subTable: [{ policyNo: 'QWE23423645' }] }] }],
      // 模态框数据
      showFlag: false,
      dialogVis: false,
      addEventDia: false,
      addForm: {
        daterange: null,
        address: null,
        categorycode: null,
        otherdiagnosis: null
      },
      address: [],
      baseForm: {
        casediseaselist:[],
        casedisabilitylist: [],
        casedeformitylist: [],
        caseoperationlist: [],
        caseaccidentlist: []
      },
      transDiaVis: false,
      tumorcodeFlag: false,
      transForm: {},
      transParams: {},
      tableFormRules: {
        diseasetype: { required: true, message: '不能为空!', trigger: 'change' }, // 疾病类型
        diseasecode: { required: true, message: '不能为空!', trigger: 'change' }, // 疾病名称
        risklevel: { required: false, message: '不能为空!', trigger: 'change' }, // 风险等级
        identificationresults: { required: false, message: '不能为空!', trigger: 'change' }, // 认定结论
        judgmentbasis: { required: false, message: '不能为空!', trigger: 'change' }, //日期
        diagnosisdate: { required: true, message: '不能为空!', trigger: 'change' }, //日期
        disabilitycode: { required: true, message: '不能为空!', trigger: 'change' }, // 伤残名称
        disabilitylevel: { required: true, message: '不能为空!', trigger: 'change' }, // 伤残等级
        appraisaldate: { required: true, message: '不能为空!', trigger: 'change' }, // 日期
        paymentratio: { required: true, message: '不能为空!', trigger: 'change' }, // 比例
        operationcode: { required: true, message: '不能为空!', trigger: 'change' }, // 手术名称
        operationlevel: { required: true, message: '不能为空!', trigger: 'change' }, // 手术等级
        accidentcode: { required: true, message: '不能为空!', trigger: 'change' }, // 意外名称
        occupationname: { required: true, message: '不能为空!', trigger: 'change' }, // 出险职业名称
        occupationcategory: { required: true, message: '不能为空!', trigger: 'change' }, // 出险职业类别
      },
      addFormRules: {
        categorycode: { required: true, message: '不能为空!', trigger: 'change' },
        eventdate: { required: true, message: '不能为空!', trigger: 'change' },
        address: { required: true, message: '不能为空!', trigger: 'change' },
        daterange: { required: true, message: '不能为空!', trigger: 'change' },
        incidenttype: { required: true, message: '不能为空!', trigger: 'change' },
        incident: { required: true, message: '不能为空!', trigger: 'change' },
        hospitalcode: { required: true, message: '不能为空!', trigger: 'change' },
        maindiagnosis: { required: true, message: '不能为空!', trigger: 'change' },
        tumorcode: { required: false, message: '申请类别为重大疾病时，肿瘤形态学必录!', trigger: 'change' },
      },
      selIncidentno: null,
      selInvoiceno: null,
      stateListArr: [],
      reList: [],
      disabilityName: {
        grade: '',
        ratio: null
      }, //伤残等级
      disabilitylevelArr: {
        grade: '',
        ratio: ''
      }, // 残疾等级
      operationListArr: {
        grade: 0,
        ratio: 0
      }, // 手术等级
      occupationnameListArr: {
        grade: 0,
        ratio: 0
      }, // 意外等级
      listBill: {},
      disease01:[],
      restList: [],
      //中症
      "disease02":[],

      // 轻症
      "disease03":[],

      //少儿特疾
      "disease04":[],
      //伤残
      "disease05":[],



      //残疾
      "disease06":[],


      //手术
      "disease07":[],


      //意外
      "disease08":[],
      queryType: '', // 类型
      querysubtype: '',//疾病类型
      queryname: '', // 名称
      querycode: '', // code
      apply_typeOptions: [],
      incidenttypeOptions: [],
      payee_occupationOptions: [],
      diseasetypeOptions: [],
      risk_codeOptions: [],
      risk_levelOptions: [],
      disability_codeOptions: [],
      deformity_codeOptions: [],
      operation_codeOptions: [],
      accident_typeOptions: [],
      invoicetypeOptions: [],
      tumormorphologicalcodeOptions: [],
      occupationnameOptions: [],
      IdentificationresultsOptions: [],
    }
  },

  mounted() {
    this.gettumormorphologicalcodeArr()
    if (this.dictList!=null && this.dictList.length!=0) {
      this.apply_typeOptions = this.dictList.find(item => {
        return item.dictType == 'apply_type'
      }).dictDate
      this.incidenttypeOptions = this.dictList.find(item => {
        return item.dictType == 'incidenttype'
      }).dictDate
      this.payee_occupationOptions = this.dictList.find(item => {
        return item.dictType == 'payee_occupation'
      }).dictDate
      this.diseasetypeOptions = this.dictList.find(item => {
        return item.dictType == 'diseasetype'
      }).dictDate
      this.risk_codeOptions = this.dictList.find(item => {
        return item.dictType == 'risk_code'
      }).dictDate
      this.risk_levelOptions = this.dictList.find(item => {
        return item.dictType == 'risk_level'
      }).dictDate
      this.disability_codeOptions = this.dictList.find(item => {
        return item.dictType == 'disability_code'
      }).dictDate
      this.deformity_codeOptions = this.dictList.find(item => {
        return item.dictType == 'deformity_code'
      }).dictDate
      this.operation_codeOptions = this.dictList.find(item => {
        return item.dictType == 'operation_code'
      }).dictDate
      this.accident_typeOptions = this.dictList.find(item => {
        return item.dictType == 'accident_type'
      }).dictDate
      this.invoicetypeOptions = this.dictList.find(item => {
        return item.dictType == 'invoicetype'
      }).dictDate
      this.tumormorphologicalcodeOptions = this.dictList.find(item => {
        return item.dictType == 'tumormorphologicalcode'
      }).dictDate
      this.occupationnameOptions = this.dictList.find(item => {
        return item.dictType == 'occupationname'
      }).dictDate
      this.IdentificationresultsOptions = this.dictList.find(item => {
        return item.dictType == 'Identificationresults'
      }).dictDate
    }
  },
  methods: {
    otherdiaChange(val){
      this.$refs.otherdia.query=''
    },
    openedModel() {
      this.baseForm.casediseaselist = [...this.baseForm.casediseaselist]
    },
    changeType(value,index) {
      this.baseForm.casediseaselist[index].diseasecode = ''
      if (value == '0') {
        this.querysubtype = '4'
      } else if (value == '1') {
        this.querysubtype = '3'
      } else if (value == '2') {
        this.querysubtype = '2'
      } else {
        this.querysubtype = '1'
      }
    },
    // 残疾名称
    remotejibinMethods(query) {
      if (query !== '') {
        this.stateListArr  = []
        const requestData = {
          type: '1',
          subtype: this.querysubtype,
          name: query,
          code: this.querycode,
        }
        getInfoByName(requestData).then(response => {
          if (response.status == '0') {
            if (response.data.errMsg == '请求参数不合法') {
              this.$message({message:'请先选择疾病类型！'})
              return false
            }
          } else {
            this.stateListArr = response.data
          }
        }).catch(error => {
          this.$message.error('查询异常')
        })
      }
    },
    changeName(row,index) {
      this.getProofNameByCode('1',row.diseasecode,this.querysubtype,index)
    },
    focusName(row,index) {
      this.stateListArr = []
    },
    // 根据名称查依据
    getProofNameByCode(type,code,subtype,index) {
      let data = {
        type: type,
        subtype: subtype,
        // name: query,
        code: code
      }
      getProofNameByCode(data).then(res =>{
        this.$set(this.baseForm.casediseaselist[index],'reList',res.data)
        this.baseForm.casediseaselist[index].reList = [...this.baseForm.casediseaselist[index].reList]
        this.$forceUpdate()
      })
    },
    formatApplicaReason(row) {
      let reason = ''
      if (row.categorycode) {
        row.categorycode.split(',').map((item, i) => {
          this.dict.label.apply_type[item] ? reason += `｜${this.selectDictLabel(this.apply_typeOptions, item)}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    // 伤残名称
    remoteDisabilityMethods(query) {
      this.disease08 = []
      if (query !== '') {
        const requestData = {
          type: '2',
          subtype: '0',
          name: query,
          code: this.querycode,
        }
        getInfoByName(requestData).then(response => {
          this.disease08 = response.data
        }).catch(error => {
          this.$message.error('查询异常')
        })
      }
    },
    // 残疾名称
    remotecanjiMethods(query) {
      this.disease06 = []
      if (query !== '') {
        const requestData = {
          type: '3',
          subtype: '0',
          name: query,
          code: this.querycode,
        }
        getInfoByName(requestData).then(response => {
          this.disease06 = response.data
        }).catch(error => {
          this.$message.error('查询异常')
        })
      }
    },
    // 手术名称
    remoteshoushuMethods(query) {
      this.disease07 = []
        if (query !== '') {
          const requestData = {
            type: '4',
            subtype: '0',
            name: query,
            code: this.querycode,
          }
          getInfoByName(requestData).then(response => {
            this.disease07 = response.data
          }).catch(error => {
            this.$message.error('查询异常')
          })
        }
    },
    // 意外名称
    remoteyiwaiMethods(query) {
      this.disease02 = []
        if (query !== '') {
          const requestData = {
            type: '5',
            subtype: '0',
            name: query,
            code: this.querycode,
          }
          getInfoByName(requestData).then(response => {
            this.disease02 = response.data
          }).catch(error => {
            this.$message.error('查询异常')
          })
        }
    },
    // 根据code查等级
    getaccident (code,type,subtype,index) {
      let data = {
        type: type,
        subtype: subtype,
        // name: query,
        code: code
      }
      getaccident(data).then(res =>{
        if (type == '2') {
          this.$set(this.baseForm.casedisabilitylist[index], 'disabilitylevel', res.data.grade)
          this.$set(this.baseForm.casedisabilitylist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.casedisabilitylist[index].disabilitylevel = res.data.grade
          // this.baseForm.casedisabilitylist[index].paymentratio = res.data.ratio
        }
        if (type == '3') {
          this.$set(this.baseForm.casedeformitylist[index], 'disabilitylevel', res.data.grade)
          this.$set(this.baseForm.casedeformitylist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.casedeformitylist[index].disabilitylevel = res.data.grade
          // this.baseForm.casedeformitylist[index].paymentratio = res.data.ratio
        }
        if (type == '4') {
          this.$set(this.baseForm.caseoperationlist[index], 'operationlevel', res.data.grade)
          this.$set(this.baseForm.caseoperationlist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.caseoperationlist[index].operationlevel = res.data.grade
          // this.baseForm.caseoperationlist[index].paymentratio = res.data.ratio
        }
      })
    },
    inputdisabilitylevel(index,value) {
      this.baseForm.caseoperationlist[index].operationlevel = value
      this.$set(this.baseForm.caseoperationlist[index],'operationlevel',value)
      this.baseForm.caseoperationlist = [...this.baseForm.caseoperationlist]
      this.$forceUpdate()
    },
    inputdisabilitylevelFlag(index,value) {
      this.baseForm.casedisabilitylist[index].disabilitylevel = value
      this.$set(this.baseForm.casedisabilitylist[index],'disabilitylevel',value)
      this.baseForm.casedisabilitylist = [...this.baseForm.casedisabilitylist]
      this.$forceUpdate()
    },
    changeDisability(value,index) {
     this.getaccident(value,'2','0',index)
    },
    focusDisability(row,index) {
      this.disease08 = []
    },
    changeCasede(value,index) {
     this.getaccident(value,'3','0',index)
    },
    focusCasede(row,index) {
      this.disease06 = []
    },
    changeOperationName(value,index) {
     this.getaccident(value,'4','0',index)
    },
    focusOperationName(row,index) {
      this.disease07 = []
    },
    focusAccidentcode(row,indx) {
      this.disease02 = []
    },
    openDia(){
      if(this.incidentnolist.length===0){
        return this.$message.warning('至少选择一条事件信息！')
      }
      this.dialogVis=true
      this.getInDangerInfo()
    },
    //出险信息信息
    getInDangerInfo(){
      let {claimno} = this
      incidentnolist = []
      this.incidentnolist.map(item => {
        incidentnolist.push(item.incidentno)
      })
      getInDangerInfo({claimno, incidentnolist}).then(response => {
        if (response.status == "1") {
          for(let key in this.baseForm){
            response.data[key]=response.data[key]===null?[]:response.data[key]
            if(key==='caseaccidentlist'){
              response.data.casediseaselist.map((item, i) => {
                item.editFlag = false
                response.data.casediseaselist[i].judgmentbasis = response.data.casediseaselist[i].judgmentbasis.split(',')
                // type,code,subtype
                let params = {
                  '0': '4',
                  '1':'3',
                  '2':'2',
                  '3':'1'
                }
                let subtype = params[response.data.casediseaselist[i].diseasetype]
                response.data.casediseaselist[i].judgmentbasis.map(list=> {
                  this.reList = []
                  let subcode = response.data.casediseaselist[i].judgmentbasis.join(',')
                  let requetdata = {
                    type: '1',
                    subtype: subtype,
                    code: response.data.casediseaselist[i].diseasecode,
                    subcode: subcode
                  }
                  getProofNameBySubCode(requetdata).then(res =>{
                    // this.reList = res.data
                    let arr = res.data
                    var list = []
                    arr.map((item,index) =>{
                      response.data.casediseaselist[i].judgmentbasisName
                      ?response.data.casediseaselist[i].judgmentbasisName
                      :response.data.casediseaselist[i].judgmentbasisName=''
                      response.data.casediseaselist[i].judgmentbasisName += ',' + item.proofname
                      list.push(item.proofname)
                    })
                    response.data.casediseaselist[i].judgmentbasis = response.data.casediseaselist[i].judgmentbasis
                    response.data.casediseaselist[i].judgmentbasisName = response.data.casediseaselist[i].judgmentbasisName.slice(1)
                    response.data.casediseaselist[i].judgmentbasisNameList = list
                  })
                  response.data.casediseaselist = [...response.data.casediseaselist]
                })
              })
            }
          }
          this.baseForm = response.data
        } else {
          this.$message({message: '查询出险信息错误！', type: 'error'});
        }
      })
    },
    handleSelectChange(val){
      this.restList = []
      this.accidentReList = []
      this.incidentnolist=[]
      if(val.length){
        this.incidentnolist = val
        this.selIncidentno = val[0].incidentno
        this.routerList = val
        val.map((item) =>{
          this.restList.push(item.categorycode)
          this.accidentReList.push(item.incidenttype)
        })
      }
    },
    remoteHospitals(query) {
      this.hospitals = []
      if (query !== '') {
        const requestData = {
          hospitalName: query,
          oldHospitals: []
        }
        getHospitalLike(requestData).then(response => {
          this.hospitals = response.data
        }).catch(error => {
          this.loading = false
          this.$message.error('查询医院列表异常')
        })
      }
    },
    remoteICD(query) {
      console.log(query,1234)
      this.ICDArr = []
      if (query !== '') {
        getLikeICD10(query).then(response => {
          this.ICDArr = response.data
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    secondarydiagnosisICD(query) {
      this.secondarydiagnosisICDArr = []
      if (query !== '') {
        getLikeICD10(query).then(response => {
          this.secondarydiagnosisICDArr = response.data
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    otherdiagnosisICD(query) {
      this.otherdiagnosisICDArr = []
      if (query !== '') {
        getLikeICD10(query).then(response => {
          this.otherdiagnosisICDArr = response.data
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    changeHospital(value) {
      this.getHospitallevel(value)
    },
    getHospitallevel(value) {
      getFirstMedicalInstitution(value).then(res =>{
        if(res.data.hospitalGradeName==='其他'&&res.data.hospitalLevelName==='其他'){
          this.addForm.hospitallevel = res.data.hospitalGradeName
          // this.$set(this.addForm,'address', [res.data.zoneProvinceCode,res.data.zoneCityCode,res.data.zoneCountyCode])
        } else {
          this.addForm.hospitallevel = res.data.hospitalGradeName + res.data.hospitalLevelName
          // this.$set(this.addForm,'address', [res.data.zoneProvinceCode,res.data.zoneCityCode,res.data.zoneCountyCode])
        }
      })
    },
    invoiceHandle(row, status) {
      if(this.incidentnolist.length!==1){
        this.$message({message: '请选择一条事件记录！', type: 'warning'})
      } else {
        let invoiceInfo = {}
        let invoiceInfoFrom = {}
        if (this.listBill) {
          invoiceInfoFrom = this.listBill
        }
        invoiceInfo.invoiceNo = invoiceInfoFrom.invoiceno
        invoiceInfo.invoiceType = invoiceInfoFrom.invoicetype
        invoiceInfo.socialSecuritySettlement = invoiceInfoFrom.socialecuritysettlement
        invoiceInfo.amount = invoiceInfoFrom.amount
        invoiceInfo.overallPayment = invoiceInfoFrom.overallpayment
        invoiceInfo.thirdPartyPayment = invoiceInfoFrom.thirdpartypayment
        if (invoiceInfoFrom.visitdate == '' || invoiceInfoFrom.dischargedate == '') {
          invoiceInfo.dateRange  = []
        } else {
          invoiceInfo.dateRange = [invoiceInfoFrom.visitdate,invoiceInfoFrom.dischargedate]
        }
        invoiceInfo.overallPaymentShare = invoiceInfoFrom.overallpaymentshare
        invoiceInfo.cashPayment = invoiceInfoFrom.cashpayment
        invoiceInfo.accountPayment = invoiceInfoFrom.accountpayment
        invoiceInfo.thirdPartyPaymentShare = invoiceInfoFrom.thirdpartypaymentshare
        invoiceInfo.payByCategory = invoiceInfoFrom.paybycategory
        invoiceInfo.selfPayment = invoiceInfoFrom.selfpayment
        invoiceInfo.patient = invoiceInfoFrom.patient
        invoiceInfo.hospital = invoiceInfoFrom.hospitalcode
        invoiceInfo.hospitalname = invoiceInfoFrom.hospitalname
        invoiceInfo.incidentno = invoiceInfoFrom.incidentno
        // let data = {
        //   node: 'input',
        //   status: 'edit',
        //   incidentNo: this.flag == true ? '' : this.selIncidentno,
        //   claimNo: encrypt(this.claimno),
        //   insuredInfo: this.insuredInfo,
        //   invoiceInfo: this.flag == true ? invoiceInfo : ''
        // }
        const newpage = this.$router.resolve({
          name: 'invoiceHandle',
          params:{},
          query:{
            node: 'input',
            status: 'edit',
            incidentNo: this.selIncidentno,
            claimNo: encrypt(this.claimno),
            // insuredInfo: JSON.stringify(this.insuredInfo) ,
            invoiceInfo: this.flag == true ? JSON.stringify(invoiceInfo) : ''
          }
        })
        window.open(newpage.href, '_blank');
        // this.$router.push({
        //   path: '/claims-handle/classificat',
        //   query: {
        //     node: 'input',
        //     status: 'edit',
        //     incidentNo: this.flag == true ? '' : this.selIncidentno,
        //     claimNo: encrypt(this.claimno),
        //     insuredInfo: this.insuredInfo,
        //     invoiceInfo: this.flag == true ? invoiceInfo : ''
        //   }
        // })
      }
    },
    openTransDia(row){
      this.transDiaVis = true
      this.transParams = row
    },
    transDiaClose(){
      this.$refs.transForm.resetFields()
      this.transDiaVis = false
    },
    transformHandle() {
      this.$refs.transForm.validate(valid => {
        if(valid){
          let params = {
            claimno: this.claimno,
            currentincidentno: this.transParams.incidentno,
            incidentno: this.transForm.incidentno,
            invoiceno: this.transParams.invoiceno
          }
          saveEventRelationBills(params).then(response => {
            if (response.status === '1') {
              this.$message({ message: '操作成功！', type: 'success' })
              this.transDiaClose()
              this.$emit('relationEvent')
            } else {
              this.$message({ message: '操作失败！', type: 'error' })
            }
          })
        } else {
          return false
        }
      })
    },
    openPop() {
      this.visible = true
    },
    // 获取地址信息
    getAddress() {
      this.address = this.getAddressByFront()
    },
    otherdiagnosisICDByCode(query,index) {
      if (query !== '' && query!== null) {
        getICD10ByCode(query).then(response => {
          if (index == '1') {
            if(response.data){
              this.ICDArr = []
              this.ICDArr.push(response.data)
            }
          } else if (index == '2') {
            if(response.data){
              this.secondarydiagnosisICDArr = []
              this.secondarydiagnosisICDArr.push(response.data)
            }
          } else {
            if(response.data){
              this.otherdiagnosisICDArr = []
              this.otherdiagnosisICDArr.push(response.data)
            }
          }
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    otherdiagnosisHostpByCode(query) {
      if (query !== '') {
        let code =  query
        getHosptailByCode(code).then(response => {
          this.hospitals = []
          this.hospitals.push(response.data)
        }).catch(error => {
          this.$message.error('查询异常！')
        })
      }
    },
    openHandle(){
      if(!this.address.length){
        this.getAddress()
      }
    },
    getTumormorphologicalcode(row,col) {
      //return this.dict.label.tumormorphologicalcode[row.tumorcode]
      return this.selectDictLabel(this.tumormorphologicalcodeOptions, row.tumorcode)
    },
    gettumormorphologicalcodeArr() {
      this.tumormorphologicalcodeArr = this.tumormorphologicalcodeOptions || []
    },
    // 查询依据bycode
    getProofNameBySubCode() {},
    // 模态框方法
    editHandle(idx, row, no) {
      if(no === 0){
        this.title = '编辑事件信息'
        this.otherdiagnosisICDByCode(row.maindiagnosis,'1')
        this.otherdiagnosisICDByCode(row.secondarydiagnosis,'2')
        if(row.otherdiagnosis){
          let otherdiagnos = row.otherdiagnosis.split(',')
          if(otherdiagnos.length){
            otherdiagnos.map(item => {
              this.otherdiagnosisICDByCode(item,'3')
            })
          }
        }
        this.otherdiagnosisHostpByCode(row.hospitalcode)
        this.gettumormorphologicalcodeArr()
        this.$nextTick(() => {
          if(!row.areacode.length){
            row.address=null
          }
          this.addForm = {...row}
          if(this.addForm.hospitallevel=='其他其他'){
            this.$set(this.addForm, 'hospitallevel', '其他')
          }
          if(row.otherdiagnosis){
            this.addForm.otherdiagnosis = row.otherdiagnosis.split(',')
          }
          this.addForm.categorycode = row.categorycode.split(',')
          if (row.visitdate == '' || row.dischargedate == '') {
            this.$set(this.addForm, 'daterange', [])
          } else {
            this.$set(this.addForm, 'daterange', [row.visitdate, row.dischargedate])
          }
          this.addForm.address = row.areacode.length?[row.areacode[0], row.areacode[1], row.areacode[2]]: []
        })
        this.addEventDia = true
        this.$nextTick(()=>{
          this.$refs.addForm.clearValidate()
        })
      } else if (no === 1) {
        this.baseForm.casediseaselist[idx].editFlag = true
        let params = {
          '0': '4',
          '1':'3',
          '2':'2',
          '3':'1'
        }
        let subtype = params[row.diseasetype]
        let item = this.baseForm.casediseaselist[idx]
        let data = {
          type: '1',
          subtype: subtype,
          name: '',
          code: item.diseasecode,
        }
        let subcode = item.judgmentbasis.join(',')
        let requetdata = {
          type: '1',
          subtype: subtype,
          code: item.diseasecode,
          subcode: subcode
        }
        this.$nextTick(() => {
          this.baseForm.casediseaselist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.stateListArr.push(res.data)
          this.stateListArr.map((item) =>{
            return item.value = item.code
          })
        })
        getProofNameBySubCode(requetdata).then(res =>{
          row.reList = res.data
          row.reList = row.reList.map((item) =>{
            item.name = item.proofname
            item.value = item.proofnameId
            return item
          })
        })
      } else if (no === 2) {
        let item = this.baseForm.casedisabilitylist[idx]
        let data = {
          type: '2',
          subtype: '0',
          name: '',
          code: item.disabilitycode,
        }
        this.$nextTick(() => {
          this.baseForm.casedisabilitylist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease08.push(res.data)
          this.disease08.map((item) =>{
            return item.value = item.code
          })
        })
      } else if (no === 3) {
        let item = this.baseForm.caseoperationlist[idx]
        let data = {
          type: '4',
          subtype: '0',
          name: '',
          code: item.operationcode,
        }
        this.$nextTick(() => {
          this.baseForm.caseoperationlist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease07.push(res.data)
          this.disease07.map((item) =>{
            return item.value = item.code
          })
        })
      } else if (no== 5) {
        let item = this.baseForm.casedeformitylist[idx]
        let data = {
          type: '3',
          subtype: '0',
          name: '',
          code: item.disabilitycode,
        }
        this.$nextTick(() => {
          this.baseForm.casedeformitylist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease06.push(res.data)
          this.disease06.map((item) =>{
            return item.value = item.code
          })
        })
      } else {
        let item = this.baseForm.caseaccidentlist[idx]
        let data = {
          type: '5',
          subtype: '0',
          name: '',
          code: item.accidentcode,
        }
        this.$nextTick(() => {
          this.baseForm.caseaccidentlist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease02.push(res.data)
          this.disease02.map((item) =>{
            return item.value = item.code
          })
        })
      }
    },
    delEventHandle(row){
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let params = {
          claimno: this.claimno,
          incidentno: row.incidentno
        }
        deleteEvent(params).then(res => {
          if (res.status === "1") {
            this.getEventInfoList()
            this.$message({ message: '删除成功！', type: 'success' })
          } else {
            this.$message({message: res.data.errMsg? res.data.errMsg:'删除失败！', type: 'error'});
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    addHandel(idx) {
      const field = {
        editFlag: true,
        editFlagT: true,
      }
      if (idx === 0) {
        this.title = '新增事件信息'
        this.addForm = {}
        this.addEventDia = true
        this.$nextTick(()=>{
          this.$refs.addForm.clearValidate()
        })
        this.addForm.eventdate = this.accidentdate
      } else if (idx === 1) {
        this.baseForm.casediseaselist.push(field)
        setTimeout(() => { this.$refs.table1.setCurrentRow(field) }, 10)
      } else if (idx === 2) {
        this.baseForm.casedisabilitylist.push(field)
        setTimeout(() => { this.$refs.table2.setCurrentRow(field) }, 10)
      } else if (idx === 3) {
        this.baseForm.caseoperationlist.push(field)
        setTimeout(() => { this.$refs.table3.setCurrentRow(field) }, 10)
      }  else if (idx === 4) {
        this.baseForm.caseaccidentlist.push(field)
        setTimeout(() => { this.$refs.table4.setCurrentRow(field) }, 10)
      } else {
        this.baseForm.casedeformitylist.push(field)
        setTimeout(() => { this.$refs.table5.setCurrentRow(field) }, 10)
      }
    },
    delHandle(idx, no, row) {
      this.$confirm(`是否确定取消?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(!row.creator) {
          if (no === 1) {
            this.baseForm.casediseaselist.splice(idx, 1)
          } else if (no === 2) {
            this.baseForm.casedisabilitylist.splice(idx, 1)
          } else if (no === 3) {
            this.baseForm.caseoperationlist.splice(idx, 1)
          } else if (no === 4){
            this.baseForm.caseaccidentlist.splice(idx, 1)
          } else {
            this.baseForm.casedeformitylist.splice(idx, 1)
          }
        } else {
          let params = {
            claimno: this.claimno,
            incidentno: incidentnolist,
          }
          if (no === 1) {
            params.key = row.diseasetype
            params.diseasecode = row.diseasecode
            params.type = '01'
          } else if (no === 2) {
            params.key = row.disabilitycode
            params.type = '02'
          } else if (no === 3) {
            params.key = row.operationcode
            params.type = '04'
          } else if (no === 4){
            params.key = row.accidentcode
            params.type = '05'
          } else {
            params.key = row.disabilitycode
            params.type = '03'
          }
          deleteDangerInfo(params).then(res => {
            if (res.status === "1") {
              this.$message({ message: '操作成功！', type: 'success' })
              this.getInDangerInfo()
            } else {
              this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: 'error'});
            }
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消！'
        })
      })
    },
    allSaveHandle(idx) {
      if (idx === 1) {
        this.$refs.baseForm.validate(valid => {
          if (valid) {
            let params = this.baseForm
            let keys = Object.keys(params)
            keys.map(item => {
              params[item].map((list, i)=> {
                params[item][i].incidentno = incidentnolist
                params[item][i].claimno = this.claimno
              })
            })
            params.claimno = this.claimno
            saveDangerInfo(params).then(res => {
              if (res.status === "1") {
                this.$message({message: res.data.errMsg? res.data.errMsg:'操作成功！', type: 'success'})
                this.getInDangerInfo()
                this.$emit('relationEvent')
                this.riskDiaClosed()
              } else {
                this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: 'error'})
              }
            }).finally(res => {
              delete params.claimno
            })
          } else {
            setTimeout(()=>{
              let errDoms= document.getElementsByClassName("is-error")
              errDoms[0].querySelector('input')?
              errDoms[0].querySelector('input').focus():
              errDoms[0].querySelector('select').focus()
            },500)
            return false
          }
        })
      }
    },
    changeTumorcode(value) {
      // let flag = value.some((item) =>{
      //   return item == '04'
      // })
      // if (flag) {
      //   this.tumorcodeFlag = true
      //   this.addFormRules.tumorcode.required = true
      // } else {
      //    this.tumorcodeFlag = false
      //    this.addFormRules.tumorcode.required = false
      // }
    },
    saveEvent() {
      this.$refs.addForm.validate(valid => {
        if(valid){
          // let flag = this.addForm.categorycode.some((item) =>{
          //   return item == '04'
          // })
          // if (flag && this.addForm.tumorcode == null) {
          //   this.$message({message:'肿瘤形态学名称必填！', type: 'error'})
          //   return false
          // }
          let params = {...this.addForm}
          console.log(params,99999)
          if(params.daterange && params.daterange.length){
            params.visitdate = params.daterange[0]
            params.dischargedate = params.daterange[1]
          }
          if(params.address.length){
            params.occurrenceprovince = params.address[0]
            params.occurrencecity = params.address[1]
            params.occurrencedistrict = params.address[2]
          }
          if(this.title==="新增事件信息"){
            params.incidentno = null
          }
          params.claimno = this.claimno
          if(!params.otherdiagnosis){
            params.otherdiagnosis=[]
          }
          delete params.daterange
          delete params.address
          this.saveEventLoading=true
          saveEventInfo(params).then(res => {
            if (res.status === "1") {
              this.$message({message: '操作成功！', type: 'success'})
              this.addForm.daterange=[]
              this.eventDiaClosed()
              this.$emit('relationEvent')
            } else {
              this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: 'error'})
            }
          }).finally(() => {
            this.saveEventLoading=false
          })
        } else {
          return
        }
      })
    },
    selHandle(row){
      this.selIncidentno = row.incidentno
      this.incidentnolist.push(row)
    },
    invoiceSelHandle(row){
      this.listBill = row
      let list = []
      list.push(row)
      this.flag = list.length ? true : false
      this.selInvoiceno = row.invoiceno
    },
    riskDiaClosed() {
      this.$refs.baseForm.resetFields()
      this.baseForm.casediseaselist = [],
      this.baseForm.casedisabilitylist = [],
      this.baseForm.casedeformitylist = [],
      this.baseForm.caseoperationlist = [],
      this.baseForm.caseaccidentlist = []
      incidentnolist.length = 0
      this.dialogVis = false
    },
    eventDiaClosed(){
      this.$refs.addForm.resetFields()
      this.addEventDia = false
    },
    // 发票类型
    getInvoicetype(row,col) {
      return this.dict.label.invoicetype[row.invoicetype]
    },
  }
}
</script>
<style scoped>
.item-width {
  width: 220px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10%;
}
.el-table /deep/ .el-table__expanded-cell {
  padding: 20px;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
.el-radio /deep/ .el-radio__label {
  display: none;
}
.list_span {
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.overEslap_class{
  color: #555;
  font-size: 12px;
  width:50%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.incident_class {
  color: #555;
  font-size: 12px;
  width:100%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.el-select-dropdown__item {
  max-width: 800px;
  height: auto;
  white-space: pre-wrap;
  word-break: break-word;
}
/* /deep/ .el-select__tags-text {
  min-width: 30px;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
} */
</style>
<style>
.el-select-dropdown el-popper is-multiple{
  max-width: 800px;
}
</style>
