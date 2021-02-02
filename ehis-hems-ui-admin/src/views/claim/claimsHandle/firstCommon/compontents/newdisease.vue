<template>
  <div>
    <el-card>
      <el-collapse v-model="activeNames6">
        <el-collapse-item name="1" style="position: relative;">
          <template slot="title">
            <span style="font-size:16px;color:black;margin-left:-10px">出险信息</span>
            <span v-show="!activeNames6.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
          </template>
          <span style="position: absolute;right: 40px;top: 0;" >
            <el-button style="float:right" type="primary" size="mini" @click="allSaveHandle(1)">保 存</el-button>
          </span>
          <el-form :disabled="(status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false" ref="accidentInfoFrom" :rules="tableFormRules" label-width="0" :model="accidentInfoFrom" size="mini">
            <el-card class="judeClass">
              <el-collapse v-model="activeNames8">
                <el-collapse-item name="1" style="position: relative;">
                  <template slot="title">
                    <span style="font-size:14px;color:black">疾病信息</span>
                    <span v-show="!activeNames8.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
                  </template>
                  <!-- <div slot="header" class="clearfix">
                    <span>疾病信息</span>
                  </div> -->
                  <el-table
                    ref="table1"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="accidentInfoFrom.casediseaselist"
                    size="small"
                    highlight-current-row
                    tooltip-effect="dark"
                    style="width: 100%;">
                    <el-table-column type="index" width="55" label="序号" align="center"/>
                    <el-table-column label="疾病类型" width="130" prop="diseasetype" align="center">
                      <template slot-scope="scope">
                        <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.diseasetype'" :rules="tableFormRules.diseasetype">
                          <el-select v-model="scope.row.diseasetype" style="width: 100%;" placeholder="请选择" :disabled="scope.row.editFlag && !scope.row.editFlagT" @change="changeType(scope.row.diseasetype,scope.$index)" clearable>
                            <el-option v-for="item in diseasetypeOptions" :label="item.dictLabel"
                              :value="item.dictValue" :key="item.dictValue"/>
                          </el-select>
                        </el-form-item>
                        <span v-else>{{/* dict.label.diseasetype[scope.row.diseasetype]*/this.selectDictLabel(this.diseasetypeOptions, scope.row.diseasetype)}}</span>
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
                            :disabled="accidentInfoFrom.casediseaselist.find(f=>{
                              return (f.diseasecode+'-'+f.diseasename == item.name)}) != null"/>
                          </el-select>
                        </el-form-item>
                        <span v-else>{{scope.row.diseasename}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="医生" prop="doctor" align="center">
                      <template slot-scope="scope">
                        <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.diseasecode'">
                          <el-input v-model="scope.row.doctor" style="width:100px" class="item-width" clearable size="mini" placeholder="请输入"/>
                        </el-form-item>
                        <span v-else>{{scope.row.doctor}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="肿瘤形态学代码" prop="tumorcode" align="center">
                      <template slot-scope="scope">
                        <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.diseasecode'">
                          <el-select v-model="scope.row.tumorcode" style="width:100px" class="item-width" placeholder="请选择" clearable filterable>
                            <el-option v-for="item in tumormorphologicalcodeArr" :label="item.label" :value="item.value" :key="item.value"/>
                          </el-select>
                        </el-form-item>
                        <span v-else>{{scope.row.tumorname}}</span>
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
                        <span v-else>{{/*dict.label.risk_level[scope.row.risklevel]*/this.selectDictLabel(this.risk_levelOptions, scope.row.risklevel)}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="认定结果" prop="identificationresults" align="center">
                      <template slot-scope="scope">
                        <el-form-item v-if="scope.row.editFlag" :prop="'casediseaselist.' + scope.$index + '.identificationresults'" :rules="tableFormRules.identificationresults">
                          <el-select v-model="scope.row.identificationresults" style="width: 100%;" placeholder="请选择" clearable>
                            <el-option v-for="(item,index) in IdentificationresultsOptions" :label="item.dictLabel" :value="item.dictValue" :key="index"/>
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
                </el-collapse-item>
              </el-collapse>
            </el-card>
            <!-- 伤残模块 -->
            <el-card style="margin-top:10px">
              <el-collapse v-model="activeNames9">
                <el-collapse-item name="1" style="position: relative;">
                  <template slot="title">
                    <span style="font-size:14px;color:black">伤残信息</span>
                    <span v-show="!activeNames9.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
                  </template>
                  <el-table
                    ref="table2"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="accidentInfoFrom.casedisabilitylist"
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
                            :disabled="accidentInfoFrom.casedisabilitylist.find(f=>{
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
                </el-collapse-item>
              </el-collapse>
            </el-card>
            <!-- 残疾 -->
            <el-card style="margin:10px 0">
              <el-collapse v-model="activeNames10">
                <el-collapse-item name="1" style="position: relative;">
                  <template slot="title">
                    <span style="font-size:14px;color:black">残疾信息</span>
                    <span v-show="!activeNames10.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
                  </template>
                  <el-table
                    ref="table5"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="accidentInfoFrom.casedeformitylist"
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
                              :disabled="accidentInfoFrom.casedeformitylist.find(f=>{
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
                </el-collapse-item>
              </el-collapse>
            </el-card>
            <!-- // 手术信息 -->
            <el-card>
              <el-collapse v-model="activeNames11">
                <el-collapse-item name="1" style="position: relative;">
                  <template slot="title">
                    <span style="font-size:14px;color:black">手术信息</span>
                    <span v-show="!activeNames11.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
                  </template>
                  <el-table
                    ref="table3"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="accidentInfoFrom.caseoperationlist"
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
                              :disabled="accidentInfoFrom.caseoperationlist.find(f=>{
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
                </el-collapse-item>
              </el-collapse>
            </el-card>
            <el-card style="margin:10px 0">
              <el-collapse v-model="activeNames12">
                <el-collapse-item name="1" style="position: relative;">
                  <template slot="title">
                    <span style="font-size:14px;color:black">意外信息</span>
                    <span v-show="!activeNames12.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
                  </template>
                  <el-table
                    ref="table4"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="accidentInfoFrom.caseaccidentlist"
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
                              :disabled="accidentInfoFrom.caseaccidentlist.find(f=>{
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
                            <el-option v-for="(item,index) in payee_occupationOptions" :label="item.dictLabel" :value="item.dictValue" :key="index"/>
                          </el-select>
                        </el-form-item>
                        <span v-else>{{/*dict.label.payee_occupation[scope.row.occupationname]*/this.selectDictLabel(this.payee_occupationOptions, scope.row.occupationname)}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="出险时职业类别" prop="occupationcategory" align="center">
                      <template slot-scope="scope">
                        <el-form-item v-if="scope.row.editFlag" :prop="'caseaccidentlist.' + scope.$index + '.occupationcategory'" :rules="tableFormRules.occupationcategory">
                          <el-select v-model="scope.row.occupationcategory" style="width: 100%;" placeholder="请选择" clearable>
                            <el-option v-for="(item,index) in occupationnameOptions" :label="item.dictLabel" :value="item.dictValue" :key="index"/>
                          </el-select>
                        </el-form-item>
                        <span v-else>{{/*dict.label.occupationname[scope.row.occupationcategory]*/this.selectDictLabel(this.occupationnameOptions, scope.row.occupationcategory)}}</span>
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
                </el-collapse-item>
              </el-collapse>
            </el-card>
          </el-form>
        </el-collapse-item>
      </el-collapse>
    </el-card>
  </div>
</template>
<script>
import {getInDangerInfo, saveDangerInfo, saveEventInfo, deleteDangerInfo, saveEventRelationBills, getFirstMedicalInstitution, getLikeICD10,getICD10ByCode, getHosptailByCode, getInfoByName,getaccident, getProofNameByCode, deleteEvent,getProofNameBySubCode } from '@/api/claim/handleDeal'
import moment from 'moment'
import {encrypt} from "@/utils/rsaEncrypt"
import { getHospitalLike } from '@/api/claim/input'
import getAddressByFront from "../../common/mixins/address"
import bus from '../../bus/bus'
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
    accidentInfoFrom: {
      type: Object,
      default: function() {
        return {};
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
      activeNames6: ['1'],
      activeNames8: ['1'],
      activeNames12: [],
      activeNames9: [],
      activeNames10: [],
      activeNames11: [],
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
      // accidentInfoFrom: {
      //   casediseaselist:[],
      //   casedisabilitylist: [],
      //   casedeformitylist: [],
      //   caseoperationlist: [],
      //   caseoperationlist: [],
      //   caseaccidentlist: []
      // },
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
        paymentratio: { required: false, message: '不能为空!', trigger: 'change' }, // 比例
        operationcode: { required: true, message: '不能为空!', trigger: 'change' }, // 手术名称
        operationlevel: { required: false, message: '不能为空!', trigger: 'change' }, // 手术等级
        accidentcode: { required: true, message: '不能为空!', trigger: 'change' }, // 意外名称
        occupationname: { required: false, message: '不能为空!', trigger: 'change' }, // 出险职业名称
        occupationcategory: { required: false, message: '不能为空!', trigger: 'change' }, // 出险职业类别
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
      incidentNoList: [],
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
      IdentificationresultsOptions: []
    }
  },
  mounted() {
    this.gettumormorphologicalcodeArr()
    bus.$on('getEventNo', data =>{
      this.incidentNoList = data
    })
    bus.$on('updateaccidentdata',data =>{
      this.accidentInfoFrom.casediseaselist = data.casediseaselist
      this.accidentInfoFrom.casedisabilitylist = data.casedisabilitylist
      this.accidentInfoFrom.casedeformitylist = data.casedeformitylist
      this.accidentInfoFrom.caseoperationlist = data.caseoperationlist
      this.accidentInfoFrom.caseaccidentlist = data.caseaccidentlist
    })
    if (this.dictList != null && this.dictList.length != 0) {
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
        return item.dictType == 'diseasetyp'
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
      this.accidentInfoFrom.casediseaselist = [...this.accidentInfoFrom.casediseaselist]
    },
    changeType(value,index) {
      if (value == '0') {
        this.querysubtype = '4'
      } else if (value == '1') {
        this.querysubtype = '3'
      } else if (value == '2') {
        this.querysubtype = '2'
      } else if(value == '3'){
        this.querysubtype = '1'
      } else {
        this.querysubtype = '5'
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
        this.$set(this.accidentInfoFrom.casediseaselist[index],'reList',res.data)
        this.accidentInfoFrom.casediseaselist[index].reList = [...this.accidentInfoFrom.casediseaselist[index].reList]
        this.$forceUpdate()
      })
    },
    formatApplicaReason(row) {
      let reason = ''
      if (row.categorycode) {
        row.categorycode.split(',').map((item, i) => {
         /* this.dict.label.apply_type[item] ? reason += `｜${this.dict.label.apply_type[item]}` : reason = ''*/
          this.selectDictLabel(this.apply_typeOptions,item)? reason += `｜${this.selectDictLabel(this.apply_typeOptions,item)}` : reason = ''
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
          this.$set(this.accidentInfoFrom.casedisabilitylist[index], 'disabilitylevel', res.data.grade)
          this.$set(this.accidentInfoFrom.casedisabilitylist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.casedisabilitylist[index].disabilitylevel = res.data.grade
          // this.baseForm.casedisabilitylist[index].paymentratio = res.data.ratio
        }
        if (type == '3') {
          this.$set(this.accidentInfoFrom.casedeformitylist[index], 'disabilitylevel', res.data.grade)
          this.$set(this.accidentInfoFrom.casedeformitylist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.casedeformitylist[index].disabilitylevel = res.data.grade
          // this.baseForm.casedeformitylist[index].paymentratio = res.data.ratio
        }
        if (type == '4') {
          this.$set(this.accidentInfoFrom.caseoperationlist[index], 'operationlevel', res.data.grade)
          this.$set(this.accidentInfoFrom.caseoperationlist[index], 'paymentratio', res.data.ratio)
          // this.baseForm.caseoperationlist[index].operationlevel = res.data.grade
          // this.baseForm.caseoperationlist[index].paymentratio = res.data.ratio
        }
      })
    },
    inputdisabilitylevel(index,value) {
      this.accidentInfoFrom.caseoperationlist[index].operationlevel = value
      this.$set(this.accidentInfoFrom.caseoperationlist[index],'operationlevel',value)
      this.accidentInfoFrom.caseoperationlist = [...this.accidentInfoFrom.caseoperationlist]
      this.$forceUpdate()
    },
    inputdisabilitylevelFlag(index,value) {
      this.accidentInfoFrom.casedisabilitylist[index].disabilitylevel = value
      this.$set(this.accidentInfoFrom.casedisabilitylist[index],'disabilitylevel',value)
      this.accidentInfoFrom.casedisabilitylist = [...this.accidentInfoFrom.casedisabilitylist]
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
          for(let key in this.accidentInfoFrom){
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
          this.accidentInfoFrom = response.data
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
        const newpage = this.$router.resolve({
          name: 'invoiceHandle',
          params:{},
          query:{
            node: 'input',
            status: 'edit',
            incidentNo: this.selIncidentno,
            claimNo: encrypt(this.claimno),
            invoiceInfo: this.flag == true ? JSON.stringify(invoiceInfo) : ''
          }
        })
        window.open(newpage.href, '_blank');
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
      /*return this.dict.label.tumormorphologicalcode[row.tumorcode]*/
      return this.selectDictLabel(this.tumormorphologicalcodeOptions, row.tumorcode)
    },
    gettumormorphologicalcodeArr() {
      /*this.tumormorphologicalcodeArr = this.dict.tumormorphologicalcode || []*/
      this.tumormorphologicalcodeArr =this.tumormorphologicalcodeOptions || []
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
        this.accidentInfoFrom.casediseaselist[idx].editFlag = true
        let params = {
          '0': '4',
          '1':'3',
          '2':'2',
          '3':'1',
          '4': '5'
        }
        let subtype = params[row.diseasetype]
        let item = this.accidentInfoFrom.casediseaselist[idx]
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
          this.accidentInfoFrom.casediseaselist[idx].editFlag = true
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
        let item = this.accidentInfoFrom.casedisabilitylist[idx]
        let data = {
          type: '2',
          subtype: '0',
          name: '',
          code: item.disabilitycode,
        }
        this.$nextTick(() => {
          this.accidentInfoFrom.casedisabilitylist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease08.push(res.data)
          this.disease08.map((item) =>{
            return item.value = item.code
          })
        })
      } else if (no === 3) {
        let item = this.accidentInfoFrom.caseoperationlist[idx]
        let data = {
          type: '4',
          subtype: '0',
          name: '',
          code: item.operationcode,
        }
        this.$nextTick(() => {
          this.accidentInfoFrom.caseoperationlist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease07.push(res.data)
          this.disease07.map((item) =>{
            return item.value = item.code
          })
        })
      } else if (no== 5) {
        let item = this.accidentInfoFrom.casedeformitylist[idx]
        let data = {
          type: '3',
          subtype: '0',
          name: '',
          code: item.disabilitycode,
        }
        this.$nextTick(() => {
          this.accidentInfoFrom.casedeformitylist[idx].editFlag = true
        })
        getaccident(data).then(res =>{
          this.disease06.push(res.data)
          this.disease06.map((item) =>{
            return item.value = item.code
          })
        })
      } else {
        let item = this.accidentInfoFrom.caseaccidentlist[idx]
        let data = {
          type: '5',
          subtype: '0',
          name: '',
          code: item.accidentcode,
        }
        this.$nextTick(() => {
          this.accidentInfoFrom.caseaccidentlist[idx].editFlag = true
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
      if (this.incidentNoList && this.incidentNoList.length == 0) {
        this.$message.warning('请先选择事件信息！')
        return false
      }
      const field = {
        editFlag: true,
        editFlagT: true,
        diseasecode: '',
        disabilitycode: '',
        operationcode: '',
        accidentcode: ''
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
        this.accidentInfoFrom.casediseaselist.push(field)
        setTimeout(() => { this.$refs.table1.setCurrentRow(field) }, 10)
      } else if (idx === 2) {
        this.accidentInfoFrom.casedisabilitylist.push(field)
        setTimeout(() => { this.$refs.table2.setCurrentRow(field) }, 10)
      } else if (idx === 3) {
        this.accidentInfoFrom.caseoperationlist.push(field)
        setTimeout(() => { this.$refs.table3.setCurrentRow(field) }, 10)
      }  else if (idx === 4) {
        this.accidentInfoFrom.caseaccidentlist.push(field)
        setTimeout(() => { this.$refs.table4.setCurrentRow(field) }, 10)
      } else {
        this.accidentInfoFrom.casedeformitylist.push(field)
        setTimeout(() => { this.$refs.table5.setCurrentRow(field) }, 10)
      }
    },
    delHandle(idx, no, row) {
      this.$confirm(`是否确定删除?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(!row.creator) {
          if (no === 1) {
            this.accidentInfoFrom.casediseaselist.splice(idx, 1)
          } else if (no === 2) {
            this.accidentInfoFrom.casedisabilitylist.splice(idx, 1)
          } else if (no === 3) {
            this.accidentInfoFrom.caseoperationlist.splice(idx, 1)
          } else if (no === 4){
            this.accidentInfoFrom.caseaccidentlist.splice(idx, 1)
          } else {
            this.accidentInfoFrom.casedeformitylist.splice(idx, 1)
          }
        } else {
          let params = {
            claimno: this.claimno,
            incidentno: this.incidentNoList,
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
              bus.$emit('updateaccidentInfo', this.incidentNoList)
              // this.getInDangerInfo()
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
        this.$refs.accidentInfoFrom.validate(valid => {
          if (valid) {
            let params = this.accidentInfoFrom
            let keys = Object.keys(params)
            keys.map(item => {
              params[item].map((list, i)=> {
                params[item][i].incidentno = this.incidentNoList
                params[item][i].claimno = this.claimno
              })
            })
            params.claimno = this.claimno
            saveDangerInfo(params).then(res => {
              if (res.status === "1") {
                this.$message({message: res.data.errMsg? res.data.errMsg:'操作成功！', type: 'success'})
                // this.getInDangerInfo()
                bus.$emit('updateaccidentInfo', this.incidentNoList)
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
      this.$refs.accidentInfoFrom.resetFields()
      this.accidentInfoFrom.casediseaselist = [],
      this.accidentInfoFrom.casedisabilitylist = [],
      this.accidentInfoFrom.casedeformitylist = [],
      this.accidentInfoFrom.caseoperationlist = [],
      this.accidentInfoFrom.caseaccidentlist = []
      incidentnolist.length = 0
      this.dialogVis = false
    },
    eventDiaClosed(){
      this.$refs.accidentInfoFrom.resetFields()
      this.addEventDia = false
    },
    // 发票类型
    getInvoicetype(row,col) {
      /*return this.dict.label.invoicetype[row.invoicetype]*/
      return this.selectDictLabel(this.invoicetypeOptions, row.invoicetype)
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
.el-tabs ::v-deep .el-tabs__item {
  width: 10%;
}
.el-table ::v-deep .el-table__expanded-cell {
  padding: 20px;
}
.el-collapse ::v-deep .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
.el-radio ::v-deep .el-radio__label {
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
/* ::v-deep .el-select__tags-text {
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
