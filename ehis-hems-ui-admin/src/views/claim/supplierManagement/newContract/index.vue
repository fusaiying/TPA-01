<template>
  <div class="container">
    <!-- 供应商合同信息 -->
    <el-card class="box-card topCard">
      <el-collapse v-model="activeNames">
        <el-collapse-item name="1">
          <template slot="title">
            <span style="font-weight: 400;margin-left: 20px;">供应商合同信息</span>
          </template>
          <el-divider></el-divider>
          <el-form style="color: #555; padding: 0 20px;" :inline="true" :model="supplierInfo" label-position="right" label-width="130px" class="demo-form-inline" size="small">
            <el-form-item :style="{width:'33%'}" label="供应商编码：">
              <span>{{ supplierInfo.suppliercode }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="供应商类型：">
              <span>{{ dict.label.supplier_type[supplierInfo.suppliertype] }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="是否有效：">
              <span>{{ dict.label.supplier_states[supplierInfo.state] }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="供应商中文名称：">
              <span>{{ supplierInfo.chname }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="供应商英文名称：">
              <span>{{ supplierInfo.enname }}</span>
            </el-form-item>
            <el-form-item :style="{width:'33%'}" label="所属地区：">
              <span>{{ supplierInfo.district }}</span>
            </el-form-item>
            <el-form-item :style="{width:'100%'}" label="详细地址：">
              <span>{{ supplierInfo.address }}</span>
            </el-form-item>
            <el-form-item :style="{width:'100%'}" label="备注：">
              <span>{{ supplierInfo.remarks }}</span>
            </el-form-item>
          </el-form>
        </el-collapse-item>
      </el-collapse>
    </el-card>

    <!-- 合同列表 -->
    <el-card class="box-card">
      <div slot="header">
        <span>合同列表</span>
      </div>
      <div >
        <el-table
          size="mini"
          :data="contractPageInfo"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
        >
          <el-table-column prop="contractcode" label="合同编码" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="contractname" label="合同名称" width="150%" align="center" show-overflow-tooltip />
          <el-table-column prop="startdate" label="合同生效日期" align="center" show-overflow-tooltip />
          <el-table-column prop="enddate" label="合同终止日期" align="center" show-overflow-tooltip />
          <el-table-column prop="signaturedate" label="合同签订日期" align="center" show-overflow-tooltip />
          <el-table-column prop="contstate" label="合同状态" :formatter="getContractStateName" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="padding: 0px;" @click="editClick(suppliercode,scope.row.contractno)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          :current-page="pageno"
          :page-size="pagesize"
          :total="contractTotal"
          layout="prev, pager, next, sizes,jumper"
          class="pages"
          :page-sizes="pageSizes"
          @current-change="contractCurrentChange"
          @size-change="contractSizeChange"
        />
      </div>
    </el-card>

    <!-- 合同信息 -->
    <el-card class="box-card">
      <div style="position: relative;">
        <el-tabs v-model="contractActiveName" :before-leave="beforeLeave">
          <!-- 合同信息 -->
          <el-tab-pane label="合同信息" name="contractInfo">
            <div style="margin: 20px; font-size: 16px;">合同基本信息</div>
            <el-form :inline="true" :model="sn_supplier_contractDO" ref="contractRule" :rules="rulesContract" label-position="right" label-width="120px" class="demo-form-inline" size="small">
              <el-row>
                <el-col :span="8">
                  <el-form-item prop="contractcode" label="合同编码：">
                    <el-input style="width: 200px;" v-model="sn_supplier_contractDO.contractcode" size="mini" placeholder="请输入" :disabled="contractSaveState"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contractname" label="合同名称：">
                    <el-input style="width: 200px;" v-model="sn_supplier_contractDO.contractname" size="mini" placeholder="请输入" :disabled="contractSaveState"/>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contstate" label="合同状态：">
                    <el-select style="width: 200px;" v-model="sn_supplier_contractDO.contstate" size="mini" placeholder="请选择" :disabled="contractSaveState">
                      <el-option v-for="item in dict.constract_status" :key="item.id" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="contractStartEndDate" label="合同起止日期：">
                    <el-date-picker style="width: 216px;" v-model="sn_supplier_contractDO.contractStartEndDate" size="mini" value-format="yyyy-MM-dd"
                      type="daterange" range-separator="~" start-placeholder="开始日期" end-placeholder="结束日期" :disabled="contractSaveState"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="signaturedate" label="合同签订日期：">
                    <el-date-picker style="width: 200px;" v-model="sn_supplier_contractDO.signaturedate" value-format="yyyy-MM-dd" :disabled="contractSaveState"
                      type="date" placeholder="选择日期" size="mini">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-form-item :style="{width:'100%'}" :class="['short-input']" label="合同备注：" prop="remarks">
                  <el-input v-model="sn_supplier_contractDO.remarks" size="mini"  placeholder="请输入"/>
                </el-form-item>
              </el-row>
            <div style="margin: 20px; font-size: 16px;">合同附件信息</div>
            <el-divider></el-divider>
              <el-form-item :style="{width:'100%'}" label="上传附件：" prop="fileList">
                <el-upload
                  class="upload-demo"
                  ref="upload"
                  :action="baseUrl+'?supplierno='+suppliercode"
                  accept=".rar,.zip,.doc,.docx,.pdf,.jpg,.png"
                  :on-preview="handlePreview"
                  :on-remove="handle_remove"
                  :before-remove="beforeRemove"
                  :before-upload="uploadBefore"
                  :headers="headers"
                  :on-success="handleSuccess"
                  :on-error="handleError"
                  :file-list="sn_supplier_contractDO.fileList"
                  :disabled="contractSaveState">
                  <el-button size="small" type="primary" :disabled="contractSaveState"><i class="el-icon-upload2"/>上传文件</el-button>&nbsp;&nbsp;
                  <span slot="tip" class="el-upload__tip">支持扩展名：.rar .zip .doc .docx .pdf .jpg .png</span>
                </el-upload>
              </el-form-item>
              <el-form-item :style="{width:'100%'}" :class="['short-input']" prop="attachmentDORemarks" label="附件备注：">
                <el-input v-model="sn_supplier_contractDO.attachmentDORemarks" tpye="textarea" :disabled="contractSaveState"/>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <!-- 业务规则 -->
          <el-tab-pane label="业务规则" name="rulesInfo">
            <div style="margin: 20px; font-size: 16px;">计费规则</div>
            <el-form :inline="true" ref="priceRule" :model="priceInfo" :rules="rules" label-position="right" label-width="130px" class="demo-form-inline" size="small">
              <el-form-item :style="{width:'33%'}" prop="suppliertype" :class="['short-select ']" label="调查类型：">
                <el-select v-model="priceInfo.suppliertype" placeholder="请选择" size="mini"
                           multiple filterable allow-create collapse-tags default-first-option :disabled="ruleSaveState">
                  <el-option v-for="item in dict.investigate_type" :key="item.id" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :style="{width:'33%'}" prop="limitation" :class="['short-select ']" label="调查时效：">
                <el-select v-model="priceInfo.limitation" placeholder="请选择" size="mini"
                           multiple filterable allow-create collapse-tags default-first-option @change="getSelectOptions" :disabled="ruleSaveState">
                  <el-option v-for="item in dict.surve_Prescription" :key="item.id" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :style="{width:'33%'}" prop="delegatemode" :class="['short-select ']" label="委托方式：">
                <el-select v-model="priceInfo.delegatemode" placeholder="请选择" size="mini"
                           multiple filterable allow-create collapse-tags default-first-option :disabled="ruleSaveState">
                  <el-option v-for="item in dict.commission_types" :key="item.id" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :style="{width:'33%'}" prop="billingmode" :class="['short-select ']" label="计费方式：">
                <el-select v-model="priceInfo.billingmode" placeholder="请选择" size="mini"
                           multiple filterable allow-create collapse-tags default-first-option :disabled="ruleSaveState">
                  <el-option v-for="item in dict.billing_types" :key="item.id" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :style="{width:'33%'}" prop="casepointtype" :class="['short-select ']" label="案件点类型：">
                <el-select v-model="priceInfo.casepointtype" placeholder="请选择" size="mini"
                           multiple filterable allow-create collapse-tags default-first-option :disabled="ruleSaveState">
                  <el-option v-for="item in dict.casePoint_type" :key="item.id" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :style="{width:'33%'}" prop="targetarea" :class="['short-select ']" label="案件地区：">
                <el-cascader size="mini" v-model="priceInfo.targetarea" :options="caseArea" :props="props" collapse-tags clearable :disabled="ruleSaveState"></el-cascader>
              </el-form-item>
            </el-form>
            <div style="margin: 20px; font-size: 16px;">时效规则</div>
            <el-divider></el-divider>

            <el-form size="mini" ref="contractForm" :rules="timeRules" :model="timeForm" :inline="true" class="demo-form-inline">
              <el-table style="padding: 0;" size="mini" ref="timeTable" :data="timeForm.addtimeRulesJson" :header-cell-style="{color:'black',background:'#f8f8ff'}" :highlight-current-row="true" class="el-tb-edit">
                <el-table-column width='140' align="center" label="时效类型">
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="padding: 0; width: 140px;" :prop="'addtimeRulesJson.' + scope.$index + '.tasktype'" :rules="timeRules.tasktype">
                      <el-select style="width: 140px;" @change="selectChange(scope.row)" size="mini" v-model="scope.row.tasktype" placeholder="请选择">
                        <el-option v-for="item in selectOptions" :key="item.value" :label="item.label" :value="item.value" :disabled="item.is_true"></el-option>
                      </el-select>
                    </el-form-item>
                    <span>{{dict.label.surve_Prescription[scope.row.tasktype]}}</span>
                  </template>
                </el-table-column>

                <el-table-column min-width='2' align="center" label="分配时效" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.assignLimitationDay'"  :rules="timeRules.assignLimitationDay">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model= "scope.row.assignLimitationDay"></el-input>x
                    </el-form-item>
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.assignLimitationHour'" :rules="timeRules.assignLimitationHour">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model="scope.row.assignLimitationHour"></el-input>
                    </el-form-item>
                    <span>{{scope.row.assignLimitationDay}}x{{scope.row.assignLimitationHour}}</span>
                  </template>
                </el-table-column>

                <el-table-column min-width='2' align="center" label="接收时效" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.receiveLimitationDay'"  :rules="timeRules.receiveLimitationDay">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model= "scope.row.receiveLimitationDay"></el-input>
                      <span>x</span>
                    </el-form-item>
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.receiveLimitationHour'" :rules="timeRules.receiveLimitationHour">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model="scope.row.receiveLimitationHour"></el-input>
                    </el-form-item>
                    <span>{{scope.row.receiveLimitationDay}}x{{scope.row.receiveLimitationHour}}</span>
                  </template>
                </el-table-column>

                <el-table-column min-width='2' align="center" label="处理时效" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.manageLimitationDay'"  :rules="timeRules.manageLimitationDay">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model= "scope.row.manageLimitationDay"></el-input>
                      <span>x</span>
                    </el-form-item>
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.manageLimitationHour'" :rules="timeRules.manageLimitationHour">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model="scope.row.manageLimitationHour"></el-input>
                    </el-form-item>
                    <span>{{scope.row.manageLimitationDay}}x{{scope.row.manageLimitationHour}}</span>
                  </template>
                </el-table-column>

                <el-table-column min-width='2' align="center" label="初审时效" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.firstLimitationDay'"  :rules="timeRules.firstLimitationDay">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model= "scope.row.firstLimitationDay"></el-input>
                      <span>x</span>
                    </el-form-item>
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.firstLimitationHour'" :rules="timeRules.firstLimitationHour">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model="scope.row.firstLimitationHour"></el-input>
                    </el-form-item>
                    <span>{{scope.row.firstLimitationDay}}x{{scope.row.firstLimitationHour}}</span>
                  </template>
                </el-table-column>

                <el-table-column min-width='2' align="center" label="审核时效" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.auditLimitationDay'"  :rules="timeRules.auditLimitationDay">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model= "scope.row.auditLimitationDay"></el-input>
                      <span>x</span>
                    </el-form-item>
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.auditLimitationHour'" :rules="timeRules.auditLimitationHour">
                      <el-input @change="timeChange(scope.row)" size="small" style="width: 40px" :class="['timeInput']" v-model="scope.row.auditLimitationHour"></el-input>
                    </el-form-item>
                    <span>{{scope.row.auditLimitationDay}}x{{scope.row.auditLimitationHour}}</span>
                  </template>
                </el-table-column>

                <el-table-column min-width='2' align="center" label="调查总时效" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.totalLimitationDay'"  :rules="timeRules.totalLimitationDay">
                      <el-input size="small" style="width: 40px" :class="['timeInput']" v-model= "scope.row.totalLimitationDay"></el-input>
                      <span>x</span>
                    </el-form-item>
                    <el-form-item v-if="!ruleSaveState" style="width: 52px" :prop="'addtimeRulesJson.' + scope.$index + '.totalLimitationHour'" :rules="timeRules.totalLimitationHour">
                      <el-input size="small" style="width: 40px" :class="['timeInput']" v-model="scope.row.totalLimitationHour"></el-input>
                    </el-form-item>
                    <span>{{scope.row.totalLimitationDay}}x{{scope.row.totalLimitationHour}}</span>
                  </template>
                </el-table-column>

                <el-table-column width='96' align="center" label="预警时效比例" show-overflow-tooltip>
                  <template scope="scope">
                    <el-form-item v-if="!ruleSaveState" :style="{width:'60px'}" :prop="'addtimeRulesJson.' + scope.$index + '.warningratio'"  :rules="timeRules.warningratio">
                      <el-input size="small" :class="['timeInput']" v-model="scope.row.warningratio"></el-input>%
                    </el-form-item>
                    <span>{{scope.row.warningratio}}%</span>
                  </template>
                </el-table-column>

                <el-table-column width='80' align="center" label="时效单位" show-overflow-tooltip>
                  <template scope="scope">
                    <el-form-item v-if="!ruleSaveState">
                      <span style="font-size: 12px;">{{scope.row.limitationunit}}</span>
                    </el-form-item>
                    <span>{{scope.row.limitationunit}}</span>
                  </template>
                </el-table-column>

                <el-table-column width='60' align="center" label="操作">
                  <template scope="scope">
                    <el-form-item v-if="!ruleSaveState">
                      <el-button :disabled="ruleSaveState" @click="cancelTime(scope.$index,scope.row.tasktype)" type="text" size="small">取消</el-button>
                    </el-form-item>
                    <span>
                      <el-button :disabled="ruleSaveState" @click="cancelTime(scope.$index,scope.row.tasktype)" type="text" size="small">取消</el-button>
                    </span>
                  </template>
                </el-table-column>
              </el-table>
            </el-form>
            <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin-top: 10px;" @click="addTimeRow()" :disabled="ruleSaveState"> + 添加</el-button>
          </el-tab-pane>
        </el-tabs>

        <el-button v-if="contractActiveName == 'contractInfo'" type="primary" size="mini" style="position: absolute;right:213px;top:0px;" :disabled="contractSaveState" icon="el-icon-check" @click="contractSubmit">合同保存</el-button>
        <el-button v-else type="primary" size="mini" style="position: absolute;right:213px;top:0px;" :disabled="ruleSaveState" icon="el-icon-check" @click="ruleSubmit">规则保存</el-button>
        <el-button v-if="contractActiveName == 'contractInfo'" type="warning" size="mini" style="position: absolute;right:121px;top:0px;" :disabled="contractSaveState" icon="el-icon-refresh" @click="reset">重 置</el-button>
        <el-button v-else type="primary" size="mini" style="position: absolute;right:121px;top:0px;" :disabled="ruleSaveState" icon="el-icon-refresh" @click="resetRule">重 置</el-button>
        <el-button type="primary" size="mini" style="position: absolute;right:30px;top:0px;" icon="el-icon-back" @click="returnHistoryRouter">返 回</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import { submitContractInfo, submitRuleInfo,getContractAndSupplierInfo,
    getAddress,uploadContractAnnex,downloadFile,deleteContractAnnex} from '@/api/investigate/supplierManagement'
  import { getToken } from '@/utils/auth'
  import { downloadData } from '@/utils'
    export default {
      dicts:['supplier_states','supplier_type','constract_status','investigate_type','surve_Prescription',
        'commission_types','billing_types','casePoint_type'],
      name: "index",
      data(){
        const changeTotal = (rule, value, callback) => {
          const num=/^([1-9][0-9]*)$/
          if (!num.test(value)) {
            callback(new Error('无效输入'))
          }else {
            //判断总时效是否与各时效之和相等
            const index = rule.field.replace('addtimeRulesJson.', '').replace('.totalLimitationDay', '').replace('.totalLimitationHour', '')
            const timeData = this.timeForm.addtimeRulesJson[index];
            let totalTime = 0;
            if(timeData.assignLimitationDay !== '' && timeData.assignLimitationHour !== ''){
              totalTime = totalTime + (timeData.assignLimitationDay * timeData.assignLimitationHour);
            }
            if(timeData.receiveLimitationDay !== '' && timeData.receiveLimitationHour !== ''){
              totalTime = totalTime + (timeData.receiveLimitationDay * timeData.receiveLimitationHour);
            }
            if(timeData.manageLimitationDay !== '' && timeData.manageLimitationHour !== ''){
              totalTime = totalTime + (timeData.manageLimitationDay * timeData.manageLimitationHour);
            }
            if(timeData.firstLimitationDay !== '' && timeData.firstLimitationHour !== ''){
              totalTime = totalTime + (timeData.firstLimitationDay * timeData.firstLimitationHour);
            }
            if(timeData.auditLimitationDay !== '' && timeData.auditLimitationHour !== ''){
              totalTime = totalTime + (timeData.auditLimitationDay * timeData.auditLimitationHour);
            }
            let thisTotal = 0
            if(timeData.totalLimitationDay !=='' && timeData.totalLimitationHour !== ''){
              thisTotal = timeData.totalLimitationDay * timeData.totalLimitationHour;
            }
            if (totalTime != thisTotal) {
              callback(new Error('总和不相等'))
              if(totalTime > 20*24){
                callback(new Error('时效超20天'))
              } else {
                callback();
              }
            } else {
              if(totalTime > 20*24){
                callback(new Error('时效超20天'))
              } else {
                callback();
              }
              callback();
            }
          }
        };
        const isNum = (rule, value, callback) => {
          if(value !== '' && value != null){
            const num= /^([1-9][0-9]*)$/
            if (!num.test(value)) {
              callback(new Error('无效输入'))
            }else{
              callback()
            }
          }else{
            callback()
          }
        };
        const isLongNum = (rule, value, callback) => {
          if(value > 100){
            callback(new Error('无效输入'))
          }else {
            const num = /^[1-9][0-9]?[0-9]?$/
            if (!num.test(value)) {
              callback(new Error('无效输入'))
            } else {
              callback()
            }
          }
        };
        const baseAddress = process.env.FILE_APT
        return{
          headers: { 'Authorization': 'Bearer ' + getToken() },
          baseUrl: baseAddress + '/invest/contract/uploadContractAnnex',
          contractno:null,
          activeNames:['2'],
          contractActiveName:'contractInfo',
          supplierInfo:{},//供应商信息

          suppliercode:null,//传过来的供应号
          contractInfo:[],//合同列表信息
          contractPageInfo:[],//合同列表分页信息
          pageno: 1,
          pagesize: 10,
          pageSizes:[5,10,20,30,50,100],
          contractTotal:0,
          index:-1,//时效push序号
          ruletypeL: null,//规则类型暂存
          rulecontentKey:null,//规则内容暂存
            sn_supplier_contract_priceDOL:[],
          suppliertype:null,//供应商类型
          //合同录入信息
          sn_supplier_contractDO:
            {
              contractname: '',
              contstate: '01',
              enddate: '',
              remarks: '',
              startdate:'',
              contractcode:'',
              suppliercode: '',
              contractStartEndDate:[],//合同起止日期
              attachmentDORemarks:'',//附件备注
              fileList:[],//文件列表
            },

          //合同录入规则
          rulesContract: {
            contractname: { required: true, message: "请输入合同名称", trigger: "blur" },
            contractStartEndDate: {required: true, message: "请选择起止日期", trigger: "blur"},
            signaturedate: { required: true, message: "请选择签订日期", trigger: "blur" },
            contractcode: { required: true, message: "请输入合同编码", trigger: "blur" },
          },

          //计费信息
          priceInfo:{
              suppliertype: [],
              limitation: [],
              delegatemode: [],
              billingmode: [],
              casepointtype: [],
              targetarea: [],
          },
          rules: {
            suppliertype: [{ required: true, message: "请选择调查类型", trigger: 'change' }],
            limitation: [{ required: true, message: "请选择调查时效", trigger: "change" }],
            delegatemode: [{ required: true, message: "请选择委托方式", trigger: "change" }],
            billingmode: [{ required: true, message: "请选择计费方式", trigger: "change" }],
          },

          caseArea: [],//地区
          props: { multiple: true },

          //时效规则
          timeForm:{
            addtimeRulesJson:[]
          },
          selectOptions:[],//所有可选时效
          timeRules:{
            assignLimitationDay:[{ validator: isNum, trigger: 'blur' }],
            assignLimitationHour:[{ validator: isNum, trigger: 'blur' }],
            receiveLimitationDay:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: isNum, trigger: 'blur' }],
            receiveLimitationHour:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: isNum, trigger: 'blur' }],
            manageLimitationDay:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: isNum, trigger: 'blur' }],
            manageLimitationHour:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: isNum, trigger: 'blur' }],
            firstLimitationDay:[{ validator: isNum, trigger: 'blur' }],
            firstLimitationHour:[{ validator: isNum, trigger: 'blur' }],
            auditLimitationDay:[{ validator: isNum, trigger: 'blur' }],
            auditLimitationHour:[{ validator: isNum, trigger: 'blur' }],
            totalLimitationDay:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: changeTotal, trigger: 'blur' }],
            totalLimitationHour:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: isNum, trigger: 'blur' }],
            tasktype:[{required: true, message: "请选择时效", trigger: 'change'}],
            warningratio:[{ required: true, message: '无效输入', trigger: 'blur' },
              { validator: isLongNum, trigger: 'blur' }]
          },

          contractSaveState:false, //合同保存状态
          ruleSaveState:false, //业务规则保存状态

          currentUploadFile:[],//当前合同附件上传历史记录
          isUpload:true
        }
      },
      mounted(){
          // 取到路由带过来的参数
          this.suppliercode = this.$route.query.suppliercode;
          this.suppliertype = this.$route.query.suppliertype

        this.getContractListData(0); //获取供应商信息和合同列表
        this.getAddressData();
      },
      methods:{
        contractCurrentChange(newPage) {
          this.pageno = newPage
          this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(this.pagesize * (this.pageno - 1), this.pagesize * this.pageno);
        },
        contractSizeChange(val) {
          this.pagesize = val;
          this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(0,this.pagesize);
        },
        //获取合同列表
        getContractListData(pagenow){
          const paramInfo = {
            suppliercode:this.suppliercode,
            pagesize:this.pagesize,
            pagenow:pagenow
          }
          getContractAndSupplierInfo(paramInfo).then(response => {
            if(response.status == "1"){
              this.contractInfo = response.data.sn_supplier_contractDOList;
              this.supplierInfo = response.data.sn_supplierDO;
              this.contractTotal = response.total;
            }
            // this.contractTotal = response.data.sn_supplier_contractDOList===null?0:response.data.sn_supplier_contractDOList.length;
            this.contractPageInfo = this.contractInfo===null?[]:this.contractInfo.slice(this.pagesize * (this.pageno - 1), this.pagesize * this.pageno);
          }).catch(error => {
            // console.log(error)
          })
        },
        //获取合同状态名称
        getContractStateName(row,col){
          return this.dict.label.constract_status[row.contstate];
        },
        returnHistoryRouter: function() {
          this.$router.go(-1)
        },
        handlePreview(file) {
          this.$message.info("正在下载"+file.name+"，请稍候！")
          let attachmentno = file.url
          downloadFile(attachmentno).then(result => {
            downloadData(result, file.name)
          })
        },
        //列表移除
        handle_remove(file, fileList) {
          if(this.isUpload){
            let attachmentno = []
            attachmentno.push(file.url)

            deleteContractAnnex(attachmentno).then(response => {
              if(response.status === '0'){
                this.$message.error("删除错误！")
              }else{
                this.$message.success("删除成功！")
                if(this.currentUploadFile!==null&&this.currentUploadFile!==undefined&&this.currentUploadFile.length>0){
                  this.currentUploadFile = this.currentUploadFile.filter(item=>{
                    return item.url !== file.url
                  })
                }
                this.isUpload=true
                this.sn_supplier_contractDO.fileList = this.sn_supplier_contractDO.fileList.filter(item => {
                  return item.url !== file.url
                })
              }
            }).catch(error=>{
              this.$message.error("删除异常！")
            })
          }else{
            this.isUpload=true
            this.sn_supplier_contractDO.fileList = this.sn_supplier_contractDO.fileList.filter(item => {
              return item.url !== file.url
            })
          }
        },
        beforeRemove(file, fileList) {
          if(!this.isUpload){
            return true;
          }
          if (file && file.status==="success") {
            return this.$confirm(`确定移除 ${file.name}？`)
          }
        },
        uploadBefore(file) {
          let fileName = file.name
          let pos = fileName.lastIndexOf('.')
          let lastName = fileName.substring(pos, fileName.length)
          if (lastName.toLowerCase() !== '.zip' && lastName.toLowerCase() !== '.rar' && lastName.toLowerCase() !== '.doc' &&
            lastName.toLowerCase() !== '.docx' && lastName.toLowerCase() !== '.pdf' && lastName.toLowerCase() !== '.jpg' &&
            lastName.toLowerCase() !== '.png') {
            this.$message.error('文件类型不正确！');
            this.isUpload = false;
            return false;
          }
          let isUpload = true
          if(this.currentUploadFile!==null&&this.currentUploadFile!==undefined&&this.currentUploadFile.length>0){
            this.currentUploadFile.forEach(item => {
              if(item.name===fileName&&item.size===file.size){
                isUpload = false
              }
            })
          }
          if(!isUpload){
            this.$message.error('相同文件已存在！');
            this.isUpload = false;
            return false;
          }
        },
        handleError(file, fileList){
          this.$message.error('上传失败！')
        },
        handleSuccess(response, file, fileList){
          if(response.status === '1' && response.data !== null && response.data !== ''
            && response.data.bussinessNo !== null && response.data.bussinessNo !== ''){
            this.sn_supplier_contractDO.fileList.push({
              name: response.data.fileName,
              url: response.data.bussinessNo
            })
            if(this.currentUploadFile===null){
              this.currentUploadFile=[]
            }
            this.currentUploadFile.push({
              name:file.name,
              size:file.size,
              url:response.data.bussinessNo
            })
          }else{
            this.$message.error('上传异常！')
          }
        },
        //上传合同附件
        /*uploadContractAnnex(param){
          const formData = new FormData();

          let fileName = param.file.name;
          let type
          let pos = fileName.lastIndexOf('.')
          let lastName = fileName.substring(pos, fileName.length)
          let filetype = lastName.toLowerCase()
          if(filetype === '.zip'){
            type = '01'
          }else if(filetype === '.rar'){
            type = '02'
          }else if(filetype === '.doc'){
            type = '03'
          }else if(filetype === '.docx'){
            type = '04'
          }else if(filetype === '.pdf'){
            type = '05'
          }else if(filetype === '.jpg'){
            type = '06'
          }else if(filetype === '.png'){
            type = '07'
          }

          formData.append('file', param.file);
          formData.append('supplierno',this.suppliercode);
          formData.append('name',param.file.name);
          formData.append('type',type);

          uploadContractAnnex(formData).then(response => {
            if(response.status === '1' && response.data !== null && response.data !== ''){
              this.sn_supplier_contractDO.fileList.push({
                name: param.file.name,
                size:param.file.size,
                url: response.data,
                attachmentno:response.data
              });
              this.currentUploadFile.push({
                name:param.file.name,
                size:param.file.size,
                url:response.data,
                attachmentno:response.data
              })
              param.onSuccess();
            }else{
              param.onError()
            }
          }).catch(error => {
            param.onError()
          })
        },*/
        //获取地址
        getAddressData(){
          getAddress().then(response => {
            this.caseArea = response.data
            for (let k = 0; k < this.caseArea.length; k++) {
              for (let l = 0; l < this.caseArea[k].children.length; l++) {
                this.caseArea[k].children[l].children = null
              }
            }
          }).catch(error => {
          })
        },
        getSelectOptions(){
          this.selectOptions = [];
          this.dict.surve_Prescription.forEach(item => {
            for (let i=0;i<this.priceInfo.limitation.length;i++){
              if(this.priceInfo.limitation[i] === item.value){
                this.selectOptions.push({value:item.value,label:item.label,is_true:false})
              }
            }
          })
          this.selectChange(null);
        },
        selectChange(row){
          const temp = this.selectOptions;
          this.selectOptions = [];
          temp.forEach(item => {
            for(let i=0;i<this.timeForm.addtimeRulesJson.length;i++){
              if(item.value === this.timeForm.addtimeRulesJson[i].tasktype){
                this.selectOptions.push({value:item.value,label:item.label,is_true:true})
                return;
              }
            }
            this.selectOptions.push({value:item.value,label:item.label,is_true:false})
          })
          //处理时效
          if(row !== null){
            let s = /\b(0+)/gi;//去掉前面的0正则
            row.manageLimitationDay = row.tasktype.replace(s,'');
            row.manageLimitationHour = 24;
          }

          //this.timeChange(row);
        },

        //计算调查总时效
        timeChange(row){
          if(row != null){
             let totalTime = 0;
            if(row.assignLimitationDay !== '' && row.assignLimitationHour !== ''){
              totalTime = totalTime + (row.assignLimitationDay * row.assignLimitationHour);
            }
            if(row.receiveLimitationDay !== '' && row.receiveLimitationHour !== ''){
              totalTime = totalTime + (row.receiveLimitationDay * row.receiveLimitationHour);
            }
            if(row.manageLimitationDay !== '' && row.manageLimitationHour !== ''){
              totalTime = totalTime + (row.manageLimitationDay * row.manageLimitationHour);
            }
            if(row.firstLimitationDay !== '' && row.firstLimitationHour !== ''){
              totalTime = totalTime + (row.firstLimitationDay * row.firstLimitationHour);
            }
            if(row.auditLimitationDay !== '' && row.auditLimitationHour !== ''){
              totalTime = totalTime + (row.auditLimitationDay * row.auditLimitationHour);
            }
            if(totalTime != 0){
              if(totalTime % 24 == 0){
                  row.totalLimitationDay = totalTime/24,
                  row.totalLimitationHour = '24';
              }else{
                  row.totalLimitationDay = '1',
                  row.totalLimitationHour = totalTime;
              }
            }
          }
        },
        // 添加一行账户信息
        addTimeRow() {
          //判断是否已经全部新增
          let canSelect = false;
          this.selectOptions.forEach(item => {
            if(item.is_true === false){
              canSelect = true;
              return;
            }
          })

          if(!canSelect || (this.timeForm.addtimeRulesJson.length >= this.selectOptions.length)){
            this.$message.warning('请先选择调查时效！');
            return;
          }

          const field = {
            // assignLimitationDay: '',
            // assignLimitationHour: '',
            // receiveLimitationDay: '',
            // receiveLimitationHour: '',
            // manageLimitationDay: '',
            // manageLimitationHour: '',
            // firstLimitationDay: '',
            // firstLimitationHour: '',
            // auditLimitationDay: '',
            // auditLimitationHour: '',
            // totalLimitationDay: '',
            // totalLimitationHour: '',
            // limitationunit:'',
            // tasktype:'',
            // warningratio:'80'

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

          }
          this.timeForm.addtimeRulesJson.push(field)
          setTimeout(() => { this.$refs.timeTable.setCurrentRow(field) }, 10)
        },
        reset() {
          this.$refs['contractRule'].resetFields();

        },
        resetRule() {
          this.$refs['priceRule'].resetFields();
          this.$refs['contractForm'].resetFields();
          this.timeForm.addtimeRulesJson = [];
        },
        //取消
        cancelTime(index,value){
          this.timeForm.addtimeRulesJson.splice(index, 1)
          if(value !== null && value !== ''){
            this.selectOptions.forEach(item => {
              if(item.value === value){
                item.is_true = false;
              }
            })
          }
        },
        //合同提交
        contractSubmit(){
          this.$refs['contractRule'].validate((valid) => {
            if (!valid) {
              return;
            }
            // console.log(this.sn_supplier_contractDO)
            const contractVO = {
              contractcode: this.sn_supplier_contractDO.contractcode,
              contractname: this.sn_supplier_contractDO.contractname,
              suppliercode: this.suppliercode,
              contstate: this.sn_supplier_contractDO.contstate,
              createdate: this.sn_supplier_contractDO.createdate,
              startdate:this.sn_supplier_contractDO.contractStartEndDate[0],
              enddate:this.sn_supplier_contractDO.contractStartEndDate[1],
              remarks: this.sn_supplier_contractDO.remarks,
              signaturedate:this.sn_supplier_contractDO.signaturedate
              }

          const contractAttachmentVO = []
          let _tmp = this.sn_supplier_contractDO.fileList;
          let fileUrl
          let fileName = ''
          let type
          let attachmentno
          if (this.sn_supplier_contractDO.fileList){
            //优化判空等
            for (var i = 0, len = _tmp.length; i < len; i++) {
              if (_tmp[i] != null){
                fileName = _tmp[i].name ? _tmp[i].name : ''
                attachmentno = _tmp[i].url
              }
              let pos = 0
              if (fileName) {
                pos = fileName.lastIndexOf('.')
              }
              let lastName = fileName.substring(pos, fileName.length)
              let filetype = lastName.toLowerCase()
              if(filetype === '.zip'){
                type = '01'
              }else if(filetype === '.rar'){
                type = '02'
              }else if(filetype === '.doc'){
                type = '03'
              }else if(filetype === '.docx'){
                type = '04'
              }else if(filetype === '.pdf'){
                type = '05'
              }else if(filetype === '.jpg'){
                type = '06'
              }else if(filetype === '.png'){
                type = '07'
              }

              contractAttachmentVO.push({
                attachmentno:attachmentno,
                contractno:'',
                createdate: 'null',
                name: fileName,
                remarks: this.sn_supplier_contractDO.attachmentDORemarks,
                state: '01',
                type: type
              });
            }
          }
            //合同信息临时变量
          const  SupplierContractAttachmentVO = {
            contractVO: contractVO,
            contractAttachmentVO: contractAttachmentVO
          };

            submitContractInfo(SupplierContractAttachmentVO).then(response => {
              if (response.status == '1'){
                this.contractSaveState = true;
                this.contractno = response.data
                this.$message.success('合同信息保存成功！');
                this.getContractListData(0); //获取供应商信息和合同列表
              } else if (response.status == '2'){
                this.$message.warning( '合同编码已存在！');
              } else if(response.status == '3'){
                // this.$message.warning('该合同不在有效范围内！');
                this.$message.warning('起止期间内已存在其他有效合同！');
              } else if(response.status == '4') {this.$message.warning('新合同的生效期早于当前有效合同终止日期！');
              } else{
                this.$message.error('合同信息保存失败！');
              }
            }).
          catch(error => {
            this.$message.warning('合同信息保存异常！');
        })
          });
        },
        //规则保存
        ruleSubmit(){
          this.$refs['priceRule'].validate((valid) => {
            if (!valid) {
              return false;
            }
            this.$refs['contractForm'].validate((valid) => {
              if (!valid) {
                return false;
              }

              if(this.timeForm.addtimeRulesJson == null || this.timeForm.addtimeRulesJson.length <= 0){
                this.$message.warning('请先录入时效规则！');
                return false;
              }

              if(this.timeForm.addtimeRulesJson.length !== this.priceInfo.limitation.length){
                this.$message.warning('请录入与时效对应的时效规则！');
                return false;
              }

              /*let isEffective = true;
              this.timeForm.addtimeRulesJson.forEach(item => {
                let temp = false;
                this.priceInfo.limitation.forEach(single => {
                  if(item.tasktype === single){
                    temp = true;
                  }
                });
                if(!temp){
                  isEffective = false;
                }
              });

              if(!isEffective){
                this.$message.warning('请录入与时效对应的时效规则！');
                return false;
              }*/

              const contractLimitationsList = [];//this.timeForm;
              const contractPriceVOList = [];//this.priceInfo;

          if(true){
            for (let i in this.timeForm.addtimeRulesJson) {
              for (let j in this.timeForm.addtimeRulesJson[i]) {
                if (j.indexOf("Day") != -1) {
                  this.index = this.index + 1;

                  contractLimitationsList.push(
                    {
                      contractno: this.contractno,
                      limitationtype:null,
                      limitationunit: '小时',
                      tasktype: this.timeForm.addtimeRulesJson[i]['tasktype'],
                      warningratio: this.timeForm.addtimeRulesJson[i]['warningratio'].split("%")[0],
                      limitation1:null,
                      limitation2:null
                    }
                  )
                }

                if ('assignLimitationDay' == j){
                  contractLimitationsList[this.index]['limitation1'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '01'
                }else if ('assignLimitationHour' == j){
                  contractLimitationsList[this.index]['limitation2'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '01'
                }else if ('receiveLimitationDay' == j){
                  contractLimitationsList[this.index]['limitation1'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '02'
                }else if ('receiveLimitationHour' == j){
                  contractLimitationsList[this.index]['limitation2'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '02'
                }else if ('manageLimitationDay' == j){
                  contractLimitationsList[this.index]['limitation1'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '03'
                }else if ('manageLimitationHour' == j){
                  contractLimitationsList[this.index]['limitation2'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '03'
                }else if ('firstLimitationDay' == j){
                  contractLimitationsList[this.index]['limitation1'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '04'
                }else if ('firstLimitationHour' == j){
                  contractLimitationsList[this.index]['limitation2'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '04'
                }else if ('auditLimitationDay' == j){
                  contractLimitationsList[this.index]['limitation1'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '05'
                }else if ('auditLimitationHour' == j){
                  contractLimitationsList[this.index]['limitation2'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '05'
                }else if ('totalLimitationDay' == j){
                  contractLimitationsList[this.index]['limitation1'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '06'
                }else if ('totalLimitationHour' == j){
                  contractLimitationsList[this.index]['limitation2'] = this.timeForm.addtimeRulesJson[i][j];
                  contractLimitationsList[this.index]['limitationtype'] = '06'
                }
              }
            }
          }
                this.index = -1

          //时效规则信息合并
          for (let j in this.priceInfo) {
            if ('suppliertype' == j){
              for (let k in this.priceInfo.suppliertype) {
                this.putPrice(j,k)
              }
            }else if ('limitation' == j){
              for (let k in this.priceInfo.limitation) {
                this.putPrice(j,k)
              }
            }else if ('delegatemode' == j){
              for (let k in this.priceInfo.delegatemode) {
                this.putPrice(j,k)
              }
            }else if ('billingmode' == j){
              for (let k in this.priceInfo.billingmode) {
                this.putPrice(j,k)
              }
            }else if ('casepointtype' == j){
              for (let k in this.priceInfo.casepointtype) {
                this.putPrice(j,k)
              }
            }else if ('targetarea' == j){
              for (let k in this.priceInfo.targetarea) {
                this.putPrice(j,k)
              }
            }
          }
          const  SupplierContractPriceLimitationVO = {
            contractLimitationsList: contractLimitationsList,
            contractPriceVOList:this.sn_supplier_contract_priceDOL
          };

              submitRuleInfo(SupplierContractPriceLimitationVO).then(response => {
                if (response.status == '1'){
                  this.ruleSaveState = true;
                  this.$message.success('规则信息保存成功！');
                }else {
                  this.$message.error('规则保存失败！');
                }
              })
            });
          });
        },

        putPrice(index,childrenIndex){
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
                    contractno:this.contractno,
                    suppliertype: this.suppliertype,
                    ruletype: this.ruletypeL,
                    rulecontent:this.rulecontentKey
                }
            )
        },

        beforeLeave(item) {
          if (!this.contractSaveState){
            this.$message.warning('请先保存合同信息！');
            return false
          }
        },

        editClick(value,contractno){
          if (value === null || value === '' || contractno === null || contractno === '') {
            this.$message.warning('供应商或合同号为空！')
            return;
          }
          this.$router.push({
            path:'updateContract', //编辑页面
            query: {
              surveyNo: value,
              contractno:contractno
            }
          })
        },

      }
    }
</script>

<style lang="css">
  .el-tooltip__popper{
    display: none;
  } /*设置显示隐藏部分内容*/
</style>

<style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .el-divider{
    margin: 10px 0;
  }
  .clearfix:after {
    clear: both
  }
  .box-card {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
  }
  .box-card /deep/ .el-card__body{
    padding: 0;
    padding-top: 10px;
    padding-bottom: 10px;
  }
  .topCard /deep/ .el-card__body{
    padding: 0;
    padding-top: 0;
    padding-bottom: 0;
  }
  .container{
    margin-bottom: 33px;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .el-form-item{
    margin-right: 0;
  }
  .pages {
    float: right;
    margin: 10px 20px 10px;
  }
  .list_span {
    display: inline-block;
    line-height: 2.5;
    color: #555;
  }
  .el-tabs /deep/ .el-tabs__item{
    width: 180px;
    text-align: center;
    color:rgba(85,85,85,0.5);
  }
  .el-tabs /deep/ .el-tabs__item.is-active {
    color: #409EFF;
  }
  .el-tabs /deep/ .el-tabs__item:hover {
    color: #409EFF;
  }
  .el-table{
    padding-left: 10px;
    padding-right: 10px;
  }


  .short-select .el-select{
    width: 100%;
  }
  .short-select /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }
  .el-cascader{
    width: 100%;
  }

  .el-tb-edit .el-form-item {
    display: none;
    width: 100%;
  }
  .el-tb-edit .current-row .el-form-item{
    display: inline-block;
  }
  .el-tb-edit .current-row .el-form-item+span{
    display: none;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .el-form /deep/ .el-form-item {
    margin-bottom: 16px;
  }
  .el-form-item{
    margin-right: 0;
  }
  .timeInput /deep/ .el-input__inner{
    margin: 0;
    padding: 0;
  }
  .el-collapse-item /deep/ .el-collapse-item__header{
    font-size: 16px;
  }
  .el-table /deep/ .el-tooltip{
    padding:0;
  }
  .el-divider{
    margin-top:0;
    margin-bottom: 10px;
  }
  .el-row{
    margin-left: 20px;
    margin-right: 20px;
  }
  .el-table /deep/ .cell {
    padding: 0;
  }
  .short-input /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }
  .short-input /deep/ .el-date-editor{
    width: 100%;
  }
</style>
