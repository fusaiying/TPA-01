<template>
  <div class="app-container" style="margin-bottom: 20px;">
    <el-backtop :bottom="60"/>
    <div v-if="navFlag == true" :class="{'opened-adapt': sidebar.opened, 'close-adapt': !sidebar.opened, 'top-instance': this.$store.state.settings.tagsView }" class="nav-bar">
      <div class="nav-flex-box ">
        <div v-for="(item, i) in btnArr" :key="i" :name="item.name"
             class="flex-item-btn"
             :class="{'active':activeNav===item.name}"
             @click="goAnchor(item.name)">
          <span>{{ item.label }}</span>
        </div>
      </div>
      <el-card class="top-card">
        <span v-if="node==='report'">
          <span style="color:#409EFF; font-size: 12px;">报案号：{{ registerNo }}</span>
          <el-button style="cursor: default;" type="text" size="small"> 报案来源：{{ selectDictLabel(this.channel_sourceOptions, registerSource) }}</el-button>
        </span>
        <span v-else>
          <span style="color:#409EFF;font-size:12px">赔案号：{{ claimno }}</span>
          <el-button style="cursor: default;" type="text" size="small"> 申请来源：{{ selectDictLabel(this.apply_sourcetypeOptions, fixInfo.applicationsource) }}</el-button>
        </span>
        <span v-if="node==='firstTrial'|| node==='complex' || node==='review' || node === 'spotCheck' || node==='correct'">
          <el-dropdown :hide-on-click="false" size="mini" style="margin: 0 20px;">
            <span class="el-dropdown-link" style="font-size: 12px; color: #1890ff;cursor: pointer;">
              异常信息:
              <span v-if="fixInfo.exceptioninfo.length" style="color:#F56C6C;">有
               <i class="el-icon-arrow-down el-icon--right"/>
              </span>
              <span v-if="fixInfo.exceptioninfo.length == 0" style="font-size: 12px; color: #67C23A;">：无</span>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="(item, i) in fixInfo.exceptioninfo" :key="i">{{ item }}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
        <el-tooltip class="item" effect="dark"  placement="top-start">
          <div slot="content">当前岗位处理时效：{{fixInfo.stationtime}}天<br/>案件处理时效:{{fixInfo.handletime}}天<br/>受理时效：{{fixInfo.accepttime}}天</div>
          <el-button style="cursor: default;" type="text" size="small">时效:{{ fixInfo.stationtime }}天</el-button>
        </el-tooltip>
        <!-- <el-button v-if="node !=='report'" style="cursor: default;" type="text" size="small">时效：{{ fixInfo.currentaging }}/{{ fixInfo.totalaging }}</el-button> -->
        <el-form style="float: right; padding: 3px 0">
          <span v-if="node==='accept'">
            <el-button :disabled="!acceptFlag" :loading="pdfLoading" type="primary" size="mini" @click="getPdfs()">打印受理回执</el-button>
            <el-button :disabled="!acceptFlag" type="primary" size="mini" @click="batchPrinting">打印条码</el-button>
            <el-button  type="primary" size="mini" @click="openReporInfo('edit')">关联报案({{relationCounts}})</el-button>
            <el-popconfirm
              title="是否确认受理？"
              @onConfirm="acceptConfirm"
            >
              <el-button slot="reference" :disabled="disableFlag || acceptFlag" type="primary" :loading="acceptLoading" size="mini">受理确认</el-button>
            </el-popconfirm>
          </span>
        </el-form>
        <el-form  style="float: right; padding: 3px 0">
          <span v-if="node==='review'">
            <el-button :disabled="!reportCount" type="primary" size="mini" @click="openReporInfo('show')">报案({{reportCount}})</el-button>
            <el-button :disabled="!fillsCount"  type="primary" size="mini" @click="openFillis">补材({{fillsCount}})</el-button>
            <el-button type="primary" size="mini" @click="openTone">提调({{investigaeNumber}})</el-button>
            <el-button type="primary" size="mini" @click="openNegotiat">协谈({{negotiationCount}})</el-button>
            <el-button type="primary" size="mini" @click="openHandle('1','不予受理话术模版')">不予受理</el-button>
            <el-button v-if="suspendFlag==='N'" type="primary" size="mini" @click="openHandle('0','悬挂说明')">悬挂</el-button>
            <el-button v-if="suspendFlag!=='N' && status !== 'show'" :disabled="status == 'show'" type="primary" size="mini" @click="cancelHandel">解挂</el-button>
            <el-button type="primary" size="mini" @click="reviewReturn('review')">审核回退</el-button>
            <el-button type="primary" :disabled="status == 'show'" size="mini" @click="reviewOver">审核完毕</el-button>
          </span>
          <span v-if="node==='complex'">
            <el-button type="primary" size="mini" @click="openBreakOff">解约</el-button>
            <el-button type="primary" size="mini" @click="openSplit">案件拆分</el-button>
            <el-button :disabled="!reportCount" type="primary" size="mini" @click="openReporInfo('show')">报案({{reportCount}})</el-button>
            <el-button type="primary" size="mini" @click="openNegotiat">协谈({{negotiationCount}})</el-button>
            <el-button v-if="suspendFlag==='N'" type="primary" size="mini" @click="openHandle('0','悬挂说明')">悬挂</el-button>
            <el-button v-if="suspendFlag!=='N' && status !== 'show'" :disabled="status == 'show'" type="primary" size="mini" @click="cancelHandel">解挂</el-button>
            <el-button type="primary" size="mini" @click="reviewReturn('complex')">复核回退</el-button>
            <el-button type="primary" :disabled="status == 'show'" size="mini" @click="reviewOver">复核完毕</el-button>
          </span>
          <span v-if="node==='spotCheck'||node==='correct'">
            <el-button v-if="node==='spotCheck'" type="primary" size="mini" @click="openBreakOff">解约</el-button>
            <el-button :disabled="!reportCount" type="primary" size="mini" @click="openReporInfo('show')">报案({{reportCount}})</el-button>
            <el-button v-if="node == 'spotCheck'" type="primary" size="mini" @click="openNegotiat">协谈({{negotiationCount}})</el-button>
            <el-button v-if="suspendFlag==='N'" type="primary" size="mini" @click="openHandle('0','悬挂说明')">悬挂</el-button>
            <el-button v-if="suspendFlag!=='N' && status !== 'show'" :disabled="status == 'show'" type="primary" size="mini" @click="cancelHandel">解挂</el-button>
            <el-button v-if="node == 'correct'" type="primary" size="mini" @click="reviewReturn('correct')">审批回退</el-button>
            <el-button v-if="node == 'correct'" type="primary" :disabled="status == 'show'" size="mini" @click="reviewOver">审批完毕</el-button>
            <el-button v-if="node == 'spotCheck'" type="primary" size="mini" @click="reviewReturn('spotCheck')">抽检回退</el-button>
            <el-button v-if="node == 'spotCheck'" type="primary" :disabled="status == 'show'" size="mini" @click="reviewOver">抽检完毕</el-button>
          </span>
        </el-form>
      </el-card>
    </div>
    <div id="start_jump"  class="startjump" :class="{'navFalgClass': navFlag == false}">
      <!-- 基本信息 -->
      <div v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node === 'physical'" id="#anchor-1" class="baseInfo_class">
        <el-row style="margin-top: 10px;">
          <el-col :span="17">
            <el-form :disabled="rejectReturnFlag||disabled||((this.node==='complex'||this.node==='correct'||this.node==='spotCheck'||this.node==='physical')?true:false)" class="basic-box" label-width="140px" label-position="right" size="mini">
              <el-card style="min-height: 555px;">
                <div class="card-title">
                  <span style="margin-left:15px;color:black;font-size:16px">基本信息</span>
                </div>
                <div class="child_class">
                  <el-row style="margin:10px">
                    <el-col :span="24" style="font-size:14px;margin-left:10px">申请人信息</el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="姓名：">
                        <span class="form-item-span">{{ applicationInfo.applicant }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="与被保人关系：">
                        <span class="form-item-span">{{ selectDictLabel(relation_ship_applyOptions, applicationInfo.relationship) }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="证件类型：">
                        <span class="form-item-span">{{  selectDictLabel(applicant_idtypeOptions, applicationInfo.applicantidtype) }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="证件号码：">
                        <span class="form-item-span">{{ applicationInfo.applicantidcardno }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="电话：">
                        <span class="form-item-span">{{ applicationInfo.applicantmobile }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="邮箱：">
                        <span class="form-item-span">{{ applicationInfo.applicantmail }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="申请类别：">
                        <span class="form-item-span">{{ formateAppli(applicationInfo.applicationtype) }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="申请日期：">
                        <span class="form-item-span">{{ applicationInfo.applicationdate }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="材料完成日期：">
                        <span class="form-item-span">{{ applicationInfo.completedate }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="24">
                      <el-form-item label="申请原因：">
                        <span class="form-item-span">{{ applicationInfo.reason }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </div>
                <el-row style="margin:10px">
                  <el-col :span="24" style="font-size:14px;margin-left:10px">客户信息</el-col>
                </el-row>
                <el-row>
                  <!-- <span class="baseinfo_class_title">客户信息</span> -->
                  <el-col :span="12">
                    <el-form-item label="客户号：">
                      <span class="form-item-span">{{ insuredInfo.customerno }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="姓名：">
                      <span class="form-item-span">{{ insuredInfo.name }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="性别：">
                      <span class="form-item-span">{{ selectDictLabel(applicant_sexOptions, insuredInfo.sex)}}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="证件类型：">
                      <span class="form-item-span">{{ selectDictLabel(applicant_idtypeOptions, insuredInfo.idtype)}}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="证件号码：">
                      <span class="form-item-span">{{ insuredInfo.idcardno }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="电话：">
                      <span class="form-item-span">{{ insuredInfo.mobilephone }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="职业：">
                      <span class="form-item-span">{{ selectDictLabel(payee_occupationOptions, insuredInfo.occupation)}}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="国籍：">
                      <span class="form-item-span">{{ selectDictLabel(nativeplaceOptions, insuredInfo.nationality) }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="证件有效期：" v-if="insuredInfo.longtime !== 'Y'">
                      <span class="form-item-span">{{ insuredInfo.effectivedate }} 至 {{ insuredInfo.expirationdate }}</span>
                    </el-form-item>
                    <el-form-item label="证件有效期：" v-else>
                      <span class="form-item-span">长期</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="年龄：">
                      <span class="form-item-span">{{ GetAge(insuredInfo.idcardno) }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24" style="margin-bottom: 10px;">
                    <el-form-item label="标签：">
                      <el-tag
                        v-for="item in insuredInfo.labelList"
                        :key="item.labelno"
                        :type="item.labeltype==='01'?'success':
                        item.labeltype==='02'?'danger':'warning'"
                        size="small"
                      >
                        <el-tooltip class="item" effect="dark" :content="item.labelcontent" placement="top-start">
                          <span>{{selectDictLabel(label_typeOptions, item.labeltype)  }}</span>
                        </el-tooltip>
                      </el-tag>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-card>
            </el-form>
          </el-col>
          <el-col :span="7">
            <el-form ref="payInfoForm" :disabled="rejectReturnFlag||disabled || this.node==='physical'" :model="payInfo" :rules="payInfoRules" label-width="110px" label-position="right" size="mini">
              <el-card style="margin-left: 4px; min-height: 555px;">
                <div class="card-title">
                  <span style="margin-left:15px;color:black;font-size:16px">赔付信息</span>
                </div>
                <div style="margin-top:10px">
                  <el-form-item label="申请金额：" prop="claimamount">
                    <span class="form-item-span">{{ payInfo.claimamount |numFilter}}</span>
                  </el-form-item>
                  <el-form-item label="垫付金额：" prop="advancepayment">
                    <span class="form-item-span">{{ payInfo.advancepayment |numFilter}}</span>
                  </el-form-item>
                  <el-form-item label="总扣除金额：" prop="deductedamount">
                    <span class="form-item-span">{{ payInfo.deductedamount |numFilter}}</span>
                  </el-form-item>
                  <el-form-item label="理算金额：" prop="payableamount">
                    <span class="form-item-span">{{ payInfo.payableamount |numFilter}}</span>
                  </el-form-item>
                  <el-form-item :rules="payInfoRules.finalamount" label="实赔金额：" prop="finalamount">
                    <el-input
                      v-model="payInfo.finalamount"
                      type="text"
                      :disabled="disabledFinalamount"
                      style="width:85%"
                      placeholder="请输入金额"
                      clearable/>
                  </el-form-item>
                  <el-form-item :rules="payInfoRules.conclusion" label="赔付结论：" prop="conclusion">
                    <el-select v-model="payInfo.conclusion" clearable placeholder="请选择"
                              style="width:85%"
                              @change="concluChange">
                      <el-option v-for="item in payment_conclusionOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item :rules="payInfoRules.conclusionreason" label="结论依据：" prop="conclusionreason">
                    <el-select v-model="payInfo.conclusionreason"  style="width:85%" clearable placeholder="请选择" :disabled="disabledCon" @change="conclusionreasonChange">
                      <el-option v-for="item in conclusionreasonList" :key="item.dictValue" :value="item.dictValue" :label="item.dictLabel"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item :rules="payInfoRules.explanation" label="赔付说明信息：" prop="explanation">
                    <el-autocomplete
                      v-model="payInfo.explanation"
                      :rows="5"
                      style="width:85%"
                      :fetch-suggestions="querySearch"
                      type="textarea"
                      placeholder="请输入内容"
                      :maxlength="1000"
                      @focus="focusSerch"
                      show-word-limit>
                    </el-autocomplete>
                  </el-form-item>
                  <div style="text-align: right; margin-top: 20px;margin-right:10px">
                    <el-button type="primary" size="mini" @click="savePayInfo">确认</el-button>
                  </div>
                </div>
              </el-card>
            </el-form>
          </el-col>
        </el-row>
      </div>
      <!-- 领款人信息模块 -->
      <div v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node ==='physical'" id="#anchor-3">
        <payeeInfo-com :status="rejectReturnFlag?'show':status" :isAppeal="isAppeal" :claimno="claimno" :node="node" :finalamount="finalamount" :payee-info="{data: payeeInfo}" :insured-info="insuredInfo"/>
      </div>
      <!-- 保单信息模块 -->
      <div v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node === 'physical'" id="#anchor-4">
        <policyInfo-com :policy-info="policyInfo" :status="rejectReturnFlag?'show':status" :node="node"/>
      </div>
      <!-- 既往信息 -->
      <div v-if="(node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='accept'||node==='materials'||node==='physical') && navFlag == true" id="#anchor-15">
        <pastInfo-com :status="status" :node="node" :claimno="claimno" :past-info="pastInfo"/>
      </div>
      <!-- 风险信息模块 -->
      <div v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node === 'physical'" id="#anchor-5">
        <risk-info-com :status="rejectReturnFlag?'show':status" :dangerInfoActiveName="dangerInfoActiveName" :node="node" :applysourcetype="fixInfo.applicationsource" :danger-info="dangerInfo" :claimno="claimno"/>
      </div>
      <!-- 影像文件模块 -->
      <div v-loading="imageLoad" v-if="navFlag == true" id="#anchor-14">
        <images-com v-if="tabLabels.supplementlist !=null &&tabLabels.supplementlist.length!=0" ref="imagecom" :imageLoad="imageLoad" :tab-labels="tabLabels" :acceptFlag="acceptFlag" :disableFlag="disableFlag" :status="rejectReturnFlag?'show':status" :node="node" :image-info="imageInfo" :claimno="node==='report'?registerNo:claimno" @imageUpdate="imageUpdate" @getFileCount="getFileCount" @changeList="changeList"/>
      </div>
      <!-- 事件信息模块 -->
      <div v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node === 'physical'" id="#anchor-6">
        <eventInfo-com :status="rejectReturnFlag?'show':status" :accidentdate="applicationInfo.accidentdate" :node="node" :event-info="eventInfo" :claimno="claimno" @getbasicInfo="getbasicInfo" :insuredInfo="insuredInfo" @relationEvent="relationEvent"/>
      </div>
      <!-- 赔付信息模块 -->
      <div v-if="node==='firstTrial'||node==='review'||node==='complex'||node==='spotCheck'||node==='correct'||node==='materials' || node === 'physical'" id="#anchor-9">
        <compen-info-com ref="compenInfo" :status="rejectReturnFlag?'show':status" :node="node" :claimno="claimno" @getPayInfo="getPayInfo" @getPayeeInfo="getPayeeInfo" :base-form="{tableData: payDetail}"/>
      </div>
      <!-- 材料信息模块 -->
      <div v-show="node!=='report' && navFlag == true" id="#anchor-13">
        <materialInfo-com :acceptFlag="acceptFlag" :suspendFlag="suspendFlag" @getMateriaList="getMateriaList" :status="rejectReturnFlag?'show':status" :node="node" :claimno="claimno" :material-info="materialInfo" :tab-labels="tabLabels" :image-info="imageInfo" :email="applicationInfo.applicantmail" @changeList="changeList"  @imageUpdate="imageUpdate"/>
      </div>
      <!-- 报案模块 赔案备注 -->
      <div id="#anchor-16" v-if="navFlag == true">
        <new-report-notes :acceptFlag="acceptFlag" :status="rejectReturnFlag?'show':status" :node="node" @caseRemarkList="caseRemarkList" @getCaseRemark="getCaseRemark" :flag="disableFlag" :case-remark="caseRemark" :claimno="node==='report'?registerNo:claimno"></new-report-notes>
        <!-- <notes-com :acceptFlag="acceptFlag" :status="rejectReturnFlag?'show':status" :node="node" :flag="disableFlag" :case-remark="caseRemark" :claimno="node==='report'?registerNo:claimno"/> -->
      </div>
      <!-- 案件标签 -->
      <div id="#anchor-22" v-if="navFlag == true">
        <new-case-label :node="node" :status="status" :claimNo="claimno" @getPublicList="getPublicList" :caselabelInfo="caselabelInfo"></new-case-label>
      </div>
      <!-- 案件历史记录 -->
      <div id="#anchor-23" v-if="navFlag == true">
        <new-loag :status="status" :node="node" :timelineInfo="timelineInfo"></new-loag>
      </div>
      <!-- 物理件模块 -->h
      <div v-show="node==='physical'" id="#anchor-18">
        <physical-com :status="status" :tabLabels="tabLabels" :supplementaryFrom="supplementaryFrom" @imageUpdate="imageUpdate" @getMaterialPendingQuery="getMaterialPendingQuery" @getFileCount="getFileCount" @getImageInfo="getImageInfo" :noAllList="noAllList" :supplementaryno="supplementaryno" :claimno="claimno" :node="node" />
      </div>
    </div>
    <!-- 模态框 -->
    <trial-repor-modul v-model="trialReporVis" :status="status" :claimno="claimno"/>
    <report-modul v-model="reportVis" :flag="reportType" :acceptFlag="acceptFlag" :status="status" :claimno="claimno" :insured-no="insuredNo" @reportCountsUpdate="reportCountsUpdate"/>
    <file-list-modul v-model="fileListVis" :claimno="claimno"/>
    <fills-modul ref="minimize" v-model="fillisVis" @pastSupplementaryList="pastSupplementaryList" @toMinimize="toMinimize" @updateStatus="updateStatus" @changeList="changeList" @imageUpdate="imageUpdate" @getFileCount="getFileCount" :imageLoad="imageLoad" :tab-labels="tabLabels" :status="status" :node="node" :image-info="imageInfo" :fillsCount="fillsCount" :email="applicationInfo.applicantmail" :claimno="claimno" type="01"/>
    <negotiat-modul v-model="negotiatVis" @updateStatus="updateStatus" @getNegotiationInfo="getNegotiationInfo" :status="status" :claimno="claimno" :node="node"/>
    <handle-modul v-model="handleVis" :diaTitle="diaTitle" :node="node" :placeholder="placeholder" :status="status" :claimno="claimno" @caseSchedule="caseSchedule" @changeButtonDisabled="changeButtonDisabled"/>
    <case-split-modul v-model="splitVis" :status="status" :claimno="claimno"/>
    <!-- <policy-info-modul v-model="policyVis" /> -->
    <break-off-modul v-model="breakVis" :status="status" :claimno="claimno"/>
    <tone-info-modul v-model="toneVis" @getCountInvestigaeNumber="getCountInvestigaeNumber" @updateStatus="updateStatus" :status="status" :node="node" :claimno="claimno"/>
    <div v-show="iframeShow" id="printall" ref="printall">
      <div style="margin-bottom: 20px">
        <barcode :value="claimno" :options="barcode_option" tag="svg" />
      </div>
    </div>
    <iframe v-show="iframeShow" id="print-iframe"/>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="300px"
      :append-to-body="true"
      :close-on-click-modal="false"
      >
      <span>{{exemptionTitle}}</span>
      <span slot="footer" class="dialog-footer" v-if="exemptionTitleFlag == true">
        <el-button @click="confireExemption" type="primary" size="mini">确认豁免</el-button>
        <el-button @click="calseExemption" type="primary" size="mini">取消豁免</el-button>
        <el-button type="primary" size="mini" @click="dialogVisible = false">取消</el-button>
      </span>
      <span slot="footer" class="dialog-footer" v-if="exemptionTitleFlag == false">
        <el-button type="primary" @click="dialogVisible = false" size="mini">是</el-button>
        <el-button type="primary" @click="calseExemption2" size="mini">否</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="提示"
      :visible.sync="cancelHandelModal"
      width="250px"
      :append-to-body="true"
      :close-on-click-modal="false"
      >
      <span>是否确认解挂?</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="caserecovery" type="primary" size="mini">确认</el-button>
        <el-button type="primary" size="mini" @click="cancelHandelModal = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :title="returnTitle"
      :visible.sync="firstTrialReturnMedal"
      width="450px"
      :append-to-body="true"
      :show-close="false"
      :close-on-click-modal="false"
      >
      <el-form :model="ruleForm" :rules="rules" ref="returnForm" label-width="120px" class="demo-ruleForm">
        <el-form-item
          label="不通过类型："
          v-if="node !== 'review'"
          prop="rejectreason">
          <el-select v-model="ruleForm.rejectreason" style="width: 200px;" placeholder="请选择" size="mini"
                      @change="rejectreasonChange">
            <el-option v-for="item in examine_rejectOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item
          label="类型："
          v-if="node !== 'review'"
          prop="errortype">
          <el-select v-model="ruleForm.errortype" style="width: 200px;" placeholder="请选择" size="mini">
            <el-option v-if="resultForm.rejectreason==='01'" v-for="item in err_typeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
            <el-option v-if="resultForm.rejectreason==='02'" v-for="item in modify_typeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
            <el-option v-if="resultForm.rejectreason==='03'" v-for="item in invest_err_typeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
            <el-option v-if="resultForm.rejectreason==='04'" v-for="item in accommodation_typeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="回退原因" prop="conclusionContent">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入内容"
            clearable
            maxlength="100"
            show-word-limit
            v-model="ruleForm.conclusionContent">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="confireReturnag" :disabled="status == 'show'" type="primary" size="mini">确认</el-button>
        <el-button type="primary" size="mini" @click="scallReturn">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="提示"
      :visible.sync="saveconsdialogVisible"
      width="270px"
      :append-to-body="true"
      :close-on-click-modal="false"
      >
      <span>结案权限不足，案件将继续流转！</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="confireSaveConculsion" type="primary" size="mini">确认</el-button>
        <el-button type="primary" size="mini" @click="saveconsdialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import Bus from '../bus/bus'
  import { mapGetters } from 'vuex'

  import mixinAnchor from '../common/mixins/reviewMixinAnchor'
  import {decrypt,encrypt } from "@/utils/rsaEncrypt"
  import moment from 'moment'
  import imagesCom from '../common/components/images'
  import payeeInfoCom from '../common/components/payeeInfo'
  import insuredCom from '../common/components/Insured'
  import reporterCom from '../common/components/reporter'
  import applicantCom from '../common/components/applicant'
  import applyCom from '../common/components/apply'
  import policyInfoCom from '../common/components/policyInfo'
  import riskInfoCom from '../common/components/riskInfo'
  import eventInfoCom from '../common/components/eventInfo'
  // import invoiceInfoCom from '../common/components/invoiceInfo'
  // import accidentInfoCom from '../common/components/accidentInfo'
  import compenInfoCom from '../common/components/compenInfo'
  import materialInfoCom from '../common/components/materialInfo'
  import pastInfoCom from '../common/components/pastInfo'
  import notesCom from '../common/components/reportNotes'
  import physicalCom from '../common/components/physical'
  import invoiceInfoCom from '../common/components/invoiceInfo'
  import pdf from '../common/components/pdf'
  import customer from '../common/components/customer'
  import newLoag from '../firstCommon/compontents/newLoag'
  import newCaseLabel from '../firstCommon/compontents/caselabel'
  import newReportNotes from '../firstCommon/compontents/newreportNotes'
  // 模态框
  import reportModul from '../common/modul/report'
  import trialReporModul from '../common/modul/trialRepor'
  import fileListModul from '../common/modul/fileList'
  import fillsModul from '../common/modul/fills'
  import negotiatModul from '../common/modul/negotiation'
  import handleModul from '../common/modul/handle'
  import caseSplitModul from '../common/modul/caseSplit'
  // import policyInfoModul from '../common/modul/policyHandle'
  import toneInfoModul from '../common/modul/toneInfo'
  import breakOffModul from '../common/modul/breakOff'
  // api
  import {
    getApplicantInfo,
    getInsuredInfo,
    getPayInfo,
    savePayInfo,
    getPayeeInfo,
    getDangerInfo,
    getEventInfoList,
    getCaseRemark,
    relationPolicy,
    getPayDetail,
    getMateriaList,
    casesuspend,
    saveConclusion,
    caseSchedule,
    getImageInfo,
    pastSurvey,
    pastNegotiation,
    updateCheck,
    materialPendingQuery,
    basicInfo,
    saveConclusionCheck,
    getCaseLastNodeInfo,
    isSuspend,
    getFileCount,
    getPastClaimsList,
    getTempalte,
    registerInfo,
    returnInformationQuery,
    getLastOptlog,
    getGroupUserList,
    pastSupplementaryList,
    getNegotiationInfo,
    getContents,
    getCheckOptLog,
    checkExemption,
    saveExemption,
    checkCaseDeal,
    judgeReopen,
    blockCheck,
    judgeStatus,
    countInvestigaeNumber,
    isAppealCorrection,
    downloadPdf,
    queryWaitExemptionCount,
    queryAccordWithExemption,
    queryPolicyExemptionDetail,
    queryExemptionStartDate,
    processCheck,
    caserecovery,
    getCasePublicList,
    caseRemarkList,
    calculationPayAmount, caseProcessLog} from '@/api/claim/handleDeal'
  import { getRegisterInfo, registerConfirm, getClaimInfo, acceptConfirm, getCounts, imageDeal,judgeClaim, judgeBnf, judgeRegister, getReceipt, getCustomerLabel } from '@/api/claim/register'
  import { getBillInfos, updateMaterial } from '@/api/claim/input'
  import { downloadFile } from "@/utils/index";
  import { get } from '@/api/dictDetail'
  let dictss = [{dictType:'channel_source'},{dictType:'relation_ship_apply'},{dictType:'applicant_idtype'},{dictType:'apply_type'},
    {dictType:'applicant_sex'},{dictType:'payment_conclusion'},{dictType:'apply_sourcetype'},{dictType:'tasknode'},
    {dictType:'examine_conclusion'},{dictType:'nativeplace'},{dictType:'payee_occupation'},{dictType:'casestep'},
    {dictType:'examine_reject'},{dictType:'conclusionreason'},{dictType:'err_type'},{dictType:'accommodation_type'},
    {dictType:'modify_type'},{dictType:'invest_err_type'},{dictType:'conclusionreason2'},{dictType:'conclusionreason3'},
    {dictType:'conclusionreason4'},{dictType:'conclusionreason5'},{dictType:'sex'},{dictType:'apply_type'},
    {dictType:'material_type'},{dictType:'label_type'},{dictType:'conclusion'},
  ]
  export default {
    components: {
      imagesCom,
      payeeInfoCom,
      insuredCom,
      reporterCom,
      applicantCom,
      applyCom,
      policyInfoCom,
      riskInfoCom,
      eventInfoCom,
      // invoiceInfoCom,
      // accidentInfoCom,
      compenInfoCom,
      materialInfoCom,
      pastInfoCom,
      notesCom,
      physicalCom,
      invoiceInfoCom,
      pdf,
      customer,
      newLoag,
      newCaseLabel,
      // 模态框
      reportModul,
      trialReporModul,
      fileListModul,
      negotiatModul,
      handleModul,
      caseSplitModul,
      // policyInfoModul,
      toneInfoModul,
      fillsModul,
      breakOffModul,
      newReportNotes
    },
    provide() {
      return {
        getCaseRemark: this.getCaseRemark,
        getMateriaList: this.getMateriaList,
        getPayDetail: this.getPayDetail,
        getDangerInfo: this.getDangerInfo,
        isSuspend: this.isSuspend,
        getImageInfo: this.getImageInfo,
        getEventInfoList: this.getEventInfoList,
        getPayeeInfo: this.getPayeeInfo,
        getCountInvestigaeNumber: this.getCountInvestigaeNumber
      }
    },
    mixins: [mixinAnchor],
    /*dicts: [
      'channel_source', 'relation_ship_apply', 'applicant_idtype', 'apply_type',
      'applicant_sex', 'payment_conclusion', 'apply_sourcetype', 'tasknode', 'examine_conclusion',
      'nativeplace', 'payee_occupation', 'casestep', 'examine_reject', 'conclusionreason',
      'err_type', 'accommodation_type', 'modify_type', 'invest_err_type', 'conclusionreason2','conclusionreason3',
      'conclusionreason4',
      'conclusionreason5','sex', 'apply_type','material_type','label_type','conclusion'
    ],*/
    computed: {
      disabled() {
        return this.status === 'show'
      },
      ...mapGetters([
        'sidebar'
      ])
    },
    filters: {
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
      },
      formatApprai(val){
        if(val) {
          return moment(val).format('YYYY-MM-DD')
        }
      },
    },
    data() {
      // 校验金额（数字 最多三位小数）
      const isAmount = (rule, value, callback) => {
        if (value) {
          // const regx = /^(\d+|\d+\.\d{1,3})$/   //不能为负
          const regx = this.isAppeal ? /^-?\d+(\.\d{1,3})?$/ : /^(\d+|\d+\.\d{1,3})$/
          if (!regx.test(value)) {
            callback(new Error('无效输入'))
          } else {
            if(this.payInfo.conclusion==='4'||this.payInfo.conclusion==='5'){
              callback()
            } else {
              if(this.payInfo.explanation){
                let num = parseFloat(this.payInfo.payableamount).toFixed(2)
                if(num<Number(value)){
                  callback(new Error('实赔金额不能大于理算金额！'))
                } else {
                  callback()
                }
              } else {
                callback()
              }
            }
          }
        } else {
          if (value == 0) {
            callback()
          } else {
            callback(new Error('实赔金额必填!'))
          }
        }
      }
      return {
        table:{
          name: '张三',
          sex: '男',
          idNo: '89898989898989898',
          date: '2020-09-09',
          orangeList: [
            {
              name: '身份证',
              num: '2份'
            },
            {
              name: '原件',
              num: '7份'
            }
          ]
        },
        labelType: {
          '01': 'VIP',
          '02': '爱投诉',
          '03': '高风险'
        },
        conclusionreasonList: [], // 结论依据
        showHeader: false,
        navFlag: true,
        saveconsdialogVisible: false, //确认权限流转的问题
        processCheckData: {}, // 权限流转传参数据
        watchForm: {
          applicationInfo: {},
          insuredInfo: {},
          payInfo: {},
          insuredBaseInfo: {},
          eventInfo: []
        },
        caselabelInfo: {
          data:[]
        }, // 案件标签数据
        firstTrialReturnMedal: false,
        returnTitle: '审核回退确认',
        rules: {
          conclusionContent: [
            { required: true, message: '请输入回退原因', trigger: 'blur' },
          ],
          rejectreason: [
            { required: true, message: '不能为空', trigger: 'blur' },
          ],
          errortype: [
            { required: true, message: '不能为空', trigger: 'blur' },
          ],
        },
        ruleForm: {
          conclusionContent: '',
          rejectreason: '',
          errortype: ''
        },
        disabledClass: false,
        cancelHandelModal: false,
        htmlTitle: '受理回执信息',
        disabledCon: false,
        submitLoading: false,
        rejectReturnFlag: false,
        reportCount: 0, // 关联报案条数
        negotiationCount: 0, // 协谈条数
        fillsCount: '0', // 补材条数
        currentData: moment(new Date()).format('YYYY-MM-DD HH:mm'),
        restaurants: [],
        errDoms: [],
        disabledFinalamount: false,
        activeName: 'anchor-0',
        reportVis: false,
        trialReporVis: false,
        pdfAcceptFlag: false,
        reportType: '',
        fileListVis: false,
        fillisVis: false,
        negotiatVis: false,
        diaTitle: '',
        handleVis: false,
        supplementaryno: '', // 补财号
        splitVis: false,
        policyVis: false,
        breakVis: false,
        toneVis: false,
        dialogVisible: false,
        spotCheckFlag: false, // 结案抽检工作池
        conclusionDisable: false,
        disabledConclusion: false, // 不通过时是否禁用
        resultForm: {},
        tempScrollId: '',
        basicInfo: {},
        status: '',
        node: '',
        relationCounts: 0,
        btnArr: [],
        acceptLoading: false,
        reportConfirmLoading: false,
        // 初审/审核/复核锚点
        firstTrialArr: [
          { label: '基本信息', name: '#anchor-1' },
          // { label: '领款人信息', name: '#anchor-3' },
          { label: '保单信息', name: '#anchor-4' },
          { label: '既往信息', name: '#anchor-15' },
          { label: '风险查询', name: '#anchor-5' },
          { label: '影像文件', name: '#anchor-14' },
          { label: '就诊信息', name: '#anchor-6' },
          { label: '赔付理算', name: '#anchor-9' },
          { label: '材料信息', name: '#anchor-13' },
          { label: '赔案备注', name: '#anchor-16' },
          { label: '案件标签', name: '#anchor-22' },
          { label: '案件历史记录', name: '#anchor-23' },
        ],
        // 物理件锚点
        physicalArr: [
          { label: '基本信息', name: '#anchor-1' },
          { label: '领款人信息', name: '#anchor-3' },
          { label: '保单信息', name: '#anchor-4' },
          { label: '风险信息', name: '#anchor-5' },
          { label: '事件信息', name: '#anchor-6' },
          // {label: '发票信息', name: '#anchor-7'},
          // {label: '出险信息', name: '#anchor-8'},
          { label: '赔付明细', name: '#anchor-9' },
          { label: '材料信息', name: '#anchor-13' },
          { label: '影像文件', name: '#anchor-14' },
          { label: '既往信息', name: '#anchor-15' },
          { label: '赔案备注', name: '#anchor-16' },
          { label: '补充物理件', name: '#anchor-18' }
        ],
        nodeStatus: {
          'accept': 'C001',
          'classificat': 'C002',
          'input': 'C003',
          'firstTrial': 'C004',
          'review': 'C005',
          'complex': 'C006',
          'spotCheck': 'C007',
          'correct': 'C008',
        },
        // 初审页面数据
        claimno: '', // 赔案号
        pdfLoading: false,
        applicationInfo: {}, // 基本信息数据
        insuredInfo: {}, // 被保人信息
        finalamount: null, // 实赔金额
        payInfo: {
          conclusionreason: null,
          conclusion: null,
          explanation: null
        }, // 赔付信息
        expList: [],
        payeeInfo: {}, // 领款人信息
        policyInfo: [], // 保单信息
        dangerInfo: {}, // 出险信息
        eventInfo: [], // 事件信息
        caseRemark: {
          data: []
        }, // 赔案备注信息
        exemptionTitle: '',
        exemptionTitleFlag: true,
        fixInfo: {
          exceptioninfo: []
        },
        placeholder: '', // 暂停和不予受理时的默认提示
        pastInfo: {
          negotiation: [],
          survey: [],
          claim: [],
          advance: []
        }, // 既往信息
        materialInfo: {
          data: [],
          typeList: []
        }, // 材料信息
        // 补充物理件
        supplementaryFrom: {},
        supplementaryArr: [],
        payDetail: {}, // 赔付明细
        // 赔付信息规则
        noAllList: [], // 不齐全资料列表
        payInfoRules: {
          finalamount: [{ validator: isAmount, trigger: 'blur', required: true }], // 实赔金额
          conclusion: [{ trigger: 'change', required: true, message: '赔付结论必填' }], // 赔付结论
          conclusionreason: { trigger: 'change', required: true, message: '赔付依据必填' }, // 赔付依据
          explanation: [{ trigger: ['blur', 'change'], required: true, message: '赔付说明必填' }] // 赔付说明
        },
        // 报案页面数据
        acceptFlag: false, // 受理未确认标识
        registerNo: '', // 报案号
        registerSource: '', // 报案来源
        registerStatus: '', // 报案状态
        insuredSave: false, // 被保人信息保存标志
        reportSave: false, // 报案人信息保存标志
        applySave: false, // 申请信息保存标志
        applicantSave: false, // 申请人信息保存标志
        insuredNo: '',
        insuredName: '',
        insuredBaseInfo: null, // 被保人信息
        billsInfo: [], // 发票信息
        // 时间轴信息
        timelineInfo: [],
        timelineInfo1: [],
        loadTimeline: false,
        isrequired: false,
        errortypeRequired: false,
        suspendFlag: '',
        dangerInfoActiveName: [],
        fillMount: 0, // 暂时存储案件层金额
        // 影像文件
        imageInfo: {
          originallist: {
            list: [],
            previewList: []
          },
          supplementlist: {
            list: [],
            previewList: []
          },
          surveylist: {
            list: [],
            previewList: []
          },
          supplementpartlist: {
            list: [],
            previewList: []
          }
        },
        conclusionTypeData: [],
        tabLabels: {
          originallist: [],
          supplementlist: [],
          supplementpartlist: [],
          surveylist: []
        },
        imageLoad: false,
        iframeShow: false,
        barCodeList: [],
        barcode_option: {
          displayValue: true, // 是否默认显示条形码数据
          background: '#fff', // 条形码背景颜色
          valid: function(valid) {
          },
          width: '1px', // 单个条形码的宽度
          height: '40px',
          fontSize: '12px' // 字体大小
        },
        caseLastNodeInfo: {},
        disableFlag: false,
        operationGroupUserDOList: [], // 审核人列表
        investigaeNumber: 0, // 提调数量
        querys: null,
        sopckDisabled: false,
        pdfMessage: {},
        pdfInfo: {
          applicantReasons:[]
        },
        isAppeal: false, // 是否申诉纠错
        calseExemptionFrom: {},
        dictList:[],
        channel_sourceOptions:[],
        relation_ship_applyOptions:[],
        applicant_idtypeOptions:[],
        apply_typeOptions:[],
        applicant_sexOptions:[],
        payment_conclusionOptions:[],
        apply_sourcetypeOptions:[],
        tasknodeOptions:[],
        examine_conclusionOptions:[],
        nativeplaceOptions:[],
        payee_occupationOptions:[],
        casestepOptions:[],
        examine_rejectOptions:[],
        conclusionreasonOptions:[],
        err_typeOptions:[],
        accommodation_typeOptions:[],
        modify_typeOptions:[],
        invest_err_typeOptions:[],
        conclusionreason2Options:[],
        conclusionreason3Options:[],
        conclusionreason4Options:[],
        conclusionreason5Options:[],
        sexOptions:[],
        material_typeOptions:[],
        label_typeOptions:[],
        conclusionOptions:[],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response =>{
        this.dictList=response.data
      })
      this.channel_sourceOptions=this.dictList.find(item=>{
        return item.dictType=='channel_source'
      }).dictDate
      this.relation_ship_applyOptions=this.dictList.find(item=>{
        return item.dictType=='relation_ship_apply'
      }).dictDate
      this.applicant_idtypeOptions=this.dictList.find(item=>{
        return item.dictType=='applicant_idtype'
      }).dictDate
      this.apply_typeOptions=this.dictList.find(item=>{
        return item.dictType=='apply_type'
      }).dictDate
      this.applicant_sexOptions=this.dictList.find(item=>{
        return item.dictType=='applicant_sex'
      }).dictDate
      this.payment_conclusionOptions=this.dictList.find(item=>{
        return item.dictType=='payment_conclusion'
      }).dictDate
      this.apply_sourcetypeOptions=this.dictList.find(item=>{
        return item.dictType=='apply_sourcetype'
      }).dictDate
      this.tasknodeOptions=this.dictList.find(item=>{
        return item.dictType=='tasknode'
      }).dictDate
      this.examine_conclusionOptions=this.dictList.find(item=>{
        return item.dictType=='examine_conclusion'
      }).dictDate
      this.nativeplaceOptions=this.dictList.find(item=>{
        return item.dictType=='nativeplace'
      }).dictDate
      this.payee_occupationOptions=this.dictList.find(item=>{
        return item.dictType=='payee_occupation'
      }).dictDate
      this.casestepOptions=this.dictList.find(item=>{
        return item.dictType=='casestep'
      }).dictDate
      this.examine_rejectOptions=this.dictList.find(item=>{
        return item.dictType=='examine_reject'
      }).dictDate
      this.conclusionreasonOptions=this.dictList.find(item=>{
        return item.dictType=='conclusionreason'
      }).dictDate
      this.err_typeOptions=this.dictList.find(item=>{
        return item.dictType=='err_type'
      }).dictDate
      this.accommodation_typeOptions=this.dictList.find(item=>{
        return item.dictType=='accommodation_type'
      }).dictDate
      this.modify_typeOptions=this.dictList.find(item=>{
        return item.dictType=='modify_type'
      }).dictDate
      this.invest_err_typeOptions=this.dictList.find(item=>{
        return item.dictType=='invest_err_type'
      }).dictDate
      this.conclusionreason2Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason2'
      }).dictDate
      this.conclusionreason3Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason3'
      }).dictDate
      this.conclusionreason4Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason4'
      }).dictDate
      this.conclusionreason5Options=this.dictList.find(item=>{
        return item.dictType=='conclusionreason5'
      }).dictDate
      this.sexOptions=this.dictList.find(item=>{
        return item.dictType=='sex'
      }).dictDate
      this.material_typeOptions=this.dictList.find(item=>{
        return item.dictType=='material_type'
      }).dictDate
      this.label_typeOptions=this.dictList.find(item=>{
        return item.dictType=='label_type'
      }).dictDate
      this.conclusionOptions=this.dictList.find(item=>{
        return item.dictType=='conclusion'
      }).dictDate

      if (this.$route.query) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        this.status = this.querys.status
        this.node = this.querys.node
        this.spotCheckFlag = this.querys.spotCheckFlag ? true : false
        this.claimno = this.querys.claimno?decrypt(this.querys.claimno):''
        this.sopckDisabled = this.querys.sopckDisabled
        // this.sopckDisabled = this.sopckDisabled ? this.sopckDisabled : this.sopckDisabled === undefined ? true : this.sopckDisabled
        this.supplementaryno = this.querys.supplementaryno
        this.insuredName = this.querys.insuredName
        // this.claimno = this.$route.query.claimno
        // this.node = this.$route.query.node
        // this.claimno = this.$route.query.claimno
        // this.supplementaryno = this.$route.query.supplementaryno
        // this.insuredName = this.$route.query.insuredName
        this.btnArr = this.node === 'report' ? this.reportButtonArr
          : this.node === 'accept' ? this.acceptArr
            : this.node === 'input' ? this.inputArr : this.firstTrialArr
        if(this.node==='physical'){
          this.btnArr = this.physicalArr
        }
        if (this.claimno) {
          this.getpdfMessage()
          this.disabledClass = true
        }
        this.getConclusionTypeData() // 赔付结论
        // 初审及后续菜单初始化页面
        if (this.node === 'firstTrial' || this.node === 'review' || this.node === 'complex'  || this.node === 'correct'|| this.node === 'spotCheck' || this.node === 'materials' || this.node === 'physical') {
          this.initExamine()
          if (this.node === 'physical') {
            this.getMaterialPendingQuery() // 补充物理件
          }
        }
        if(this.node === 'input'){
          this.getCaseLastNodeInfo()
        }
        this.registerNo = this.querys.registerNo?decrypt(this.querys.registerNo):''
        this.registerSource = this.querys.registerSource
        this.registerStatus = this.querys.registerStatus
        // this.registerNo = this.$route.query.registerNo
        // this.registerSource = this.$route.query.registerSource
        // this.registerStatus = this.$route.query.registerStatus
        if (this.node === 'report') {
          if(this.status === 'edit'){
            this.getRegisterDetailInfo()
            // this.getCaseRemark()
            this.getMateriaList()
            this.getImageInfo('1', 1)
          }
          this.getFileCount()
        }
        if (this.node === 'accept' || this.node === 'input') {
          this.fixInfo.applicationsource = this.querys.applySource
          // this.fixInfo.applicationsource = this.$route.query.applySource
          if(this.status !== 'handle'){
            this.getClaimDetailInfo()
            // this.getCaseRemark()
            this.getMateriaList()
            if (this.node === 'accept') {
              this.caseSchedule1()
            } else {
              this.caseSchedule()
            }
            if(this.claimno !== '' && this.claimno !== null) {
              this.getbasicInfo() // 顶部固定展示内容接口
            }
            //影像件接口
            this.getFileCount()
            this.getImageInfo('1',  1)
            this.getImageInfo('2',  1)
            this.getImageInfo('3',  1)
            this.getImageInfo('4',  1)
          } else {
            this.getFileCount()
          }
          if (this.claimno) {
            this.getInsuredInfo()
          }
        }
        if (this.node === 'input') {
          this.getBills()
          this.getLastOptlog()
        }
        this.imageDeal()
        // this.input_filter()
        if (this.claimno !== null && this.claimno !== "" && this.claimno!== undefined) {
          this.updateMaterialList()
        }
        if (this.node == 'report' || this.node == 'accept') {
          this.navFlag = this.querys.styleFlag == 'report' ? false : true
        }
        let casedetailFlag = this.querys.casedetailFlag ? false : true
        Bus.$on('updateFlag',data =>{
          this.navFlag = data
        })
        Bus.$on('updatePhysicalStatus',data =>{
          this.status = data
        })
        Bus.$on('changeList1',() =>{
          this.changeList(4)
        })
        Bus.$on('finalamountMethods', data =>{
          this.fillMount = data
          this.payInfo.finalamount = (data)
        })
        if (this.claimno && casedetailFlag == true && (this.node == 'accept' ||this.node == 'classificat' ||this.node == 'input' ||this.node == 'firstTrial' ||this.node == 'review' ||this.node == 'complex' ||this.node == 'spotCheck' || this.node == 'correct')) {
          this.checkCaseDeal()
        }
        if (this.node == 'physical') {
          this.judePhysicalStatus()
        }
        if (this.registerNo && this.node == 'report') {
          this.judgeStatusReport()
        }
        // 判断是否申诉纠错案件
        this.isAppealCorrection(this.claimno)
      }
    },
    updated(){},
    methods: {
      initExamine() {
        this.isSuspend() // 是否暂停
        this.caseRemarkList() // 赔案备注列表
        if(this.status==='handle'){
          this.isSuspend() // 是否暂停
          this.getCaseLastNodeInfo() // 是否退回
        } else {
          this.getLastOptlog()
        }
        this.getPublicList()
        this.registerInfo()
        this.getNegotiationInfo()// 获取协谈列表条数
        this.getCountInvestigaeNumber()// 获取提调列表条数
        this.pastSupplementaryList()// 获取补材列表条数
        this.getApplicantInfo() // 获取申请人信息
        this.getInsuredInfo() // 获取被保人信息
        this.getPayInfo() // 获取赔付信息
        this.getPayeeInfo() // 获取领款人信息
        this.relationPolicy() // 保单信息
        this.getDangerInfo() // 风险信息
        this.getEventInfoList() // 事件信息
        // this.getCaseRemark() // 赔案备注信息
        this.getMateriaList() // 材料信息
        this.getPayDetail() // 赔付明细
        this.caseSchedule() // 查询操作轨迹信息
        // this.getImageInfo() // 影像文件
        this.getImageInfo('1', 1)
        this.getImageInfo('2', 1)
        this.getImageInfo('3', 1)
        this.getImageInfo('4', 1)
        this.getFileCount()
        if (this.node === 'firstTrial') {
          this.getGroupUserList()
        }
        this.getbasicInfo() // 顶部固定展示内容接口
      },
      // 查询赔付模板
      conclusionreasonChange() {
        this.loadAll()
      },
      // 判断当前节点的状态
      checkCaseDeal() {
        let params = {
          node: this.nodeStatus[this.node],
          claimno: this.claimno
        }
        checkCaseDeal(params).then(res =>{
          if (res.data == true) {
            this.status = 'handle'
          } else {
            this.status = 'show'
            this.disableFlag = true
            this.acceptFlag = true
          }
        }).then(() =>{
          if (this.querys.serchNode == true) {
            this.status = 'show'
            this.disableFlag = true
            this.acceptFlag = true
          }
        })
      },
      // 节点为物理件时判断状态
      judePhysicalStatus() {
        let params = {
          supplementaryno :  this.supplementaryno,
          sign: '02'
        }
        let _this = this
        blockCheck(params).then(res =>{
          if (res.data == true) {
            _this.status = 'handle'
          } else {
            _this.status = 'show'
          }
        })
      },
      // 判断报案节点状态
      judgeStatusReport() {
        judgeStatus(this.registerNo).then(res =>{
          if (res.data == true) {
            this.status = 'handle'
            this.disableFlag = false
            this.acceptFlag = false
          } else {
            this.status = 'show'
            this.disableFlag = true
            this.acceptFlag = true
          }
        })
      },
      saveConclusionCheck(params) {
        let query = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        this.calseExemptionFrom = params
        this.submitLoading = true
        saveConclusionCheck(query).then(res => {
          if (res.status === '1') {
            if (res.data.istips == true) {
              this.$message({ message: res.data.msg, type: 'error' })
              return false
            } else {
              if (res.data.msg == ''){
              } else {
                this.$message({ message: res.data.msg, type: 'warning' })
              }
              if (this.node == 'firstTrial' || this.node == 'review' ||this.node == 'spotCheck' || this.node == 'complex' || this.node == 'correct') {
                queryAccordWithExemption(this.claimno).then(res =>{
                  if (res.status == '1') {
                    if (res.data == 0) {
                      this.saveConclusion(params)
                    } else {
                      queryWaitExemptionCount(this.claimno).then(res =>{
                        if (res.status == '1') {
                          if (res.data  == 0) {
                            this.saveConclusion(params)
                          } else {
                            queryPolicyExemptionDetail(this.claimno).then(res =>{
                              if (res.status == '1') {
                                if (res.data == 0) {
                                  queryExemptionStartDate(this.claimno).then(res =>{
                                    if (res.status == '1') {
                                      this.dialogVisible = true
                                      this.exemptionTitle = '触发自动豁免规则，确认后将按照豁免责任进行自动豁免'
                                      this.exemptionTitleFlag =true
                                    } else {
                                      this.$message({message: res.data.errMsg?res.data.errMsg : '获取豁免开始日期失败！', type: 'error' })
                                    }
                                  })
                                } else {
                                  this.dialogVisible = true
                                  this.exemptionTitleFlag = false
                                  this.exemptionTitle = '存在未豁免的责任信息，是否进行处理？'
                                }
                              } else {
                                this.$message({message: res.data.errMsg?res.data.errMsg : '查询已豁免条数失败！', type: 'error' })
                              }
                            })
                          }
                        } else {
                          this.$message({message: res.data.errMsg?res.data.errMsg : '查询待豁免条数失败！', type: 'error' })
                        }
                      })
                    }
                  } else {
                    this.$message({message: res.data.errMsg?res.data.errMsg : '豁免失败！', type: 'error' })
                  }
                })
              } else {
                this.saveConclusion(params)
              }
            }
          } else {
            this.$message({ message: '操作失败！', type: 'error' })
          }
        }).finally(() => {
          this.submitLoading = false
        })
      },
      saveConclusion(params) {
        this.submitLoading = true
        processCheck(params).then(res =>{
          if (res.status == '1') {
            params.node = res.data.node
            params.personalpool = res.data.personalpool
            saveConclusion(params).then(res => {
              if (res.status === '1') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.status = 'show'
                this.acceptFlag = true
                this.disableFlag = true
                this.sopckDisabled = true
                if (this.resultForm.conclusion =='02') {
                  deleteAllExemptionDetail(this.claimno).then(res =>{
                  })
                }
              } else {
                if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
                  this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'error' })
                } else {
                  this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'warning' })
                }
              }
            }).finally(() => {
              this.submitLoading = false
            })
          } else {
            this.$message({ message: res.data.errMsg , type: 'warning' })
          }
        })
      },
      // 审核回退
      reviewReturn(node) {
         if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        if (node == 'review') {
          this.returnTitle = '审核回退确认'
        } else if (node == 'complex') {
          this.returnTitle = '复核回退确认'
        } else if (node == 'correct') {
          this.returnTitle = '审批回退确认'
        } else {
          this.returnTitle = '抽检回退确认'
        }
        this.firstTrialReturnMedal = true
        this.resultForm.conclusion = '02'
      },
      GetAge(identityCard) {
        var len = (identityCard + "").length;
        if (len == 0) {
          return 0;
        } else {
          if ((len != 15) && (len != 18)){//身份证号码只能为15位或18位其它不合法
            return 0;
          }
        }
        var strBirthday = "";
        if (len == 18)//处理18位的身份证号码从号码中得到生日和性别代码
        {
            strBirthday = identityCard.substr(6, 4) + "/" + identityCard.substr(10, 2) + "/" + identityCard.substr(12, 2);
        }
        if (len == 15) {
            strBirthday = "19" + identityCard.substr(6, 2) + "/" + identityCard.substr(8, 2) + "/" + identityCard.substr(10, 2);
        }
        //时间字符串里，必须是“/”
        var birthDate = new Date(strBirthday);
        var nowDateTime = new Date();
        var age = nowDateTime.getFullYear() - birthDate.getFullYear();
        //再考虑月、天的因素;.getMonth()获取的是从0开始的，这里进行比较，不需要加1
        if (nowDateTime.getMonth() < birthDate.getMonth() || (nowDateTime.getMonth() == birthDate.getMonth() && nowDateTime.getDate() < birthDate.getDate())) {
            age--;
        }
        return age;
      },
      // 审核完毕
      reviewOver () {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.resultForm.conclusion = '01'
        this.confireReturn()
      },
      // 解挂
      cancelHandel() {
        this.cancelHandelModal = true
      },
      caserecovery() {
        caserecovery(this.claimno).then(res =>{
          if (res.status == '1') {
            this.$message.success('解挂成功！')
            this.cancelHandelModal = false
            this.isSuspend()
          } else {
            this.$message.error('解挂失败！')
            this.cancelHandelModal = false
          }
        })
      },
      // 取消回退
      scallReturn() {
        this.firstTrialReturnMedal = false
        this.ruleForm.conclusionContent = ''
      },
      confireReturnag() {
        if (this.ruleForm.conclusionContent!== '' && this.ruleForm.conclusionContent!== null) {
          this.ruleForm.conclusionContent  = this.ruleForm.conclusionContent + '  '
          this.ruleForm.conclusionContent = this.ruleForm.conclusionContent.replace(/\s+/g, "");
        }
        this.$refs.returnForm.validate((valid) => {
          if (valid) {
            this.confireReturn()
          } else {
            return false
          }
        })
      },
      confireReturn() {
        judgeBnf(this.claimno).then(response => {
          if (response.status === '1') {
            this.firstTrialReturnMedal = false
            if(response.data !== '' && this.resultForm.conclusion !=='02') {
              this.$message({ message: response.data, type: 'warning' })
            }else{
              const params = {
                claimno: this.claimno,
                conclusion: this.resultForm.conclusion ,
                rejectreason: this.resultForm.conclusion == '02' ?  this.ruleForm.rejectreason : '',
                errortype: this.resultForm.conclusion == '02' ?  this.ruleForm.errortype : '',
                explanation: this.resultForm.conclusion == '02' ?  this.ruleForm.conclusionContent : '',
                tasknode: this.mapNode(this.node)
              }
              if(this.caseLastNodeInfo.conclusion==='02'){
                params.isreturn = 'Y'
                params.status = this.caseLastNodeInfo.status
                params.reviewer = this.caseLastNodeInfo.creator
                let nextCaseNode = this.selectDictLabel(this.casestepOptions, this.caseLastNodeInfo.tasknode)
                params.nexttasknode = this.mapNode(nextCaseNode)
              }
              if (this.querys.conclusionType) {
                this.submitLoading = true
                params.detailno = this.querys.detailno
                updateCheck(params).then(res => {
                  if (res.status === '1') {
                    this.$message({ message: '操作成功！', type: 'success' })
                  } else {
                    if (res.data.errCode=='CL001' || res.data.errCode == 'CL000') {
                      this.$message({ message: res.data.errMsg, type: 'error' })
                    } else {
                      this.$message({ message: res.data.errMsg, type: 'warning' })
                    }
                  }
                }).finally(() => {
                  this.submitLoading = false
                  this.sopckDisabled = true
                })
              } else {
                if(this.resultForm.conclusion !=='02'){
                  this.saveConclusionCheck(params)
                } else {
                  this.saveConclusion(params)
                }
              }
            }
          }
        })
      },
      // 赔案备注列表
      caseRemarkList() {
        const type = this.node === 'report' ? '01' : '02'
        let node = ''
        node = this.nodeStatus[this.node]
        const params = { relationno: this.node === 'report'?this.registerNo: this.claimno, type ,node}
        caseRemarkList(params).then(response =>{
          if(response.data){
            this.caseRemark = response
            this.caseRemark.data.map((item) =>{
              item.editFlag = false
            })
          } else {
            this.$message.error('查询赔案备注列表错误！')
          }
        })
      },
      // 不予受理后修改状态
      changeButtonDisabled() {
        this.status = 'show'
      },
      beforeunloadHandler (e) {
        e = e || window.event
        if (e) {
          e.returnValue = '关闭提示'
        }
        return '关闭提示'
      },
      // 实赔金额校验
      concluChange(value){
        this.payInfo.conclusionreason = ''
        if (value == '3') {
          this.payInfo.finalamount = '0'
          this.disabledFinalamount = true
        } else {
          this.disabledFinalamount = false
          if (this.fillMount == 0) {
            this.payInfo.finalamount = this.payInfo.finalamount
          } else {
            this.payInfo.finalamount = (this.fillMount)
          }
        }
        this.conclusionreasonList = []
        this.$refs.payInfoForm.validateField('finalamount')
        if (value == '1') {
          this.conclusionreasonList = this.conclusionreasonOptions
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '2') {
          this.conclusionreasonList = this.conclusionreasonOptions
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '3') {
          this.conclusionreasonList = this.conclusionreason2Options
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if (value == '4') {
          this.conclusionreasonList = this.conclusionreason3Options
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else if(value == '5') {
          this.conclusionreasonList = this.conclusionreason4Options
          this.payInfoRules.conclusionreason.required = true
          this.disabledCon = false
        } else {
          this.conclusionreasonList = this.conclusionreason5Options
          this.disabledCon = false
          this.payInfoRules.conclusionreason.required = true
        }
      },
      // 获取审核人列表
      getGroupUserList(){
        let params = 'claim_examine'
        // let params = this.mapNode(this.node)
        getGroupUserList(params).then(res => {
          if (res.status === '1') {
            this.operationGroupUserDOList = res.data
          } else {
            this.$message({ message: res.data.errMsg, type: 'error' })
          }
        })
      },
      focusSerch() {
        this.loadAll()
      },
      // 获取报案列表数量
      registerInfo(){
        registerInfo(this.claimno).then(res => {
          if (res.status === '1') {
            this.reportCount = res.data?res.data.length:0
          }
        })
      },
      // 获取案件标签列表
      getPublicList() {
        let params = {}
        params.claimNo = this.claimno
        getCasePublicList(params).then(res =>{
          if (res.status == '1') {
            this.caselabelInfo.data = res.data == '未查询出数据' ? [] : res.data
            this.caselabelInfo.data.map((item,index) =>{
              item.id = index+1
            })
          } else {
            this.$message.error('查询案件标签列表异常！')
          }
        })
      },
      // 获取补材列表数量
      pastSupplementaryList(){
        let params = {
          relationno: this.claimno,
          type: '01'
        }
        pastSupplementaryList(params).then(res => {
          if (res.status === '1') {
            if(res.data&&res.data.length){
              let flag = res.data.find(f=>{
                return f.status==='01'
              })
              if(flag){
                this.fillsCount='?'
              } else {
                this.fillsCount = res.data.length.toString()
              }
            }
          }
        })
      },
      // 获取补材列表数量
      getNegotiationInfo(){
        getNegotiationInfo(this.claimno).then(res => {
          if (res.status === '1') {
            if(res.data){
              let flag = res.data.list.some(f=>{
                return f.status==='01'
              })
              if(flag){
                this.negotiationCount='?'
              } else {
                this.negotiationCount = res.data.list.length
              }
            }
          }
        })
      },
      // 获取提调数量
      getCountInvestigaeNumber() {
        countInvestigaeNumber(this.claimno).then(res => {
          if (res.status == '1') {
            if (res.data.ralationInvastigation) {
              this.investigaeNumber = '?'
            } else {
              this.investigaeNumber = res.data.completeCount
            }
          }
          this.$forceUpdate()
        })
      },
      // 查看处理结论接口
      getLastOptlog(){
        // if (this.node === 'spotCheck' && this.sopckDisabled === undefined) {
        //   return
        // }
        let params = {
          claimno: this.claimno,
          node: this.mapNode(this.node),
          detailno: this.querys.detailno
        }
        if (this.sopckDisabled) {
          getCheckOptLog(params).then(res =>{
            if (res.status === '1') {
              if(res.data) {
                let row = res.data
                this.$set(this.resultForm, 'conclusion', row.conclusion)
                this.$set(this.resultForm, 'reviewer', row.reviewer)
                this.$set(this.resultForm, 'rejectreason', row.rejectreason)
                this.$set(this.resultForm, 'errortype', row.errortype)
                this.$set(this.resultForm, 'explanation', row.explanation)
              }
            } else {
              this.$message({ message: '请求出错！', type: 'error' })
            }
          })
        } else if (this.sopckDisabled === undefined) {
          delete params.detailno
          getLastOptlog(params).then(res => {
            if (res.status === '1') {
              if(res.data) {
                let row = res.data
                this.$set(this.resultForm, 'conclusion', row.conclusion)
                this.$set(this.resultForm, 'reviewer', row.reviewer)
                this.$set(this.resultForm, 'rejectreason', row.rejectreason)
                this.$set(this.resultForm, 'errortype', row.errortype)
                this.$set(this.resultForm, 'explanation', row.explanation)
              }
            } else {
              this.$message({ message: '请求出错！', type: 'error' })
            }
          })
        }
      },
      getCaseLastNodeInfo(){
        let params = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        getCaseLastNodeInfo(params).then(res => {
          if (res.status === '1') {
            this.caseLastNodeInfo = res.data
          } else {
            this.$message({ message: '请求出错！', type: 'error' })
          }
        })
      },
      isSuspend(){
        isSuspend(this.claimno).then(res => {
          if (res.status === '1') {
            this.suspendFlag = res.data
          } else {
            this.$message({ message: '请求出错！', type: 'error' })
          }
        })
      },
      // 初始化调用返回顶部固定位置的展示信息
      getbasicInfo() {
        const params = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        basicInfo(params).then(response => {
          if (response!=null && response.status === '1') {
            this.fixInfo = response.data
            this.fixInfo.exceptioninfo = response.data.exceptioninfo == null ? [] : response.data.exceptioninfo
            this.fixInfo.applicationsource = this.fixInfo.applicationsource == null ? '1' : this.fixInfo.applicationsource
          } else {
            this.$message({ message: '请求出错！', type: 'error' })
          }
        })
      },
      formateAppli: function (val){
        let str = ''
        if(val){
          let arrVal = val.split(',')
          arrVal.map(item => {
            if(this.selectDictLabel(this.apply_typeOptions, item)){
              str += '｜'+this.selectDictLabel(this.apply_typeOptions, item)
            }
          })
          return str.slice(1)
        } else {
          return val
        }
      },
      getpdfMessage () {
        getReceipt(this.claimno).then(response => {
          if (response.status === '1') {
            this.pdfInfo = response.data
            // this.$refs['pdf'].claimno = this.claimno
            // this.$refs['pdf'].pdfInfo = response.data
            let arr = response.data.materials.filter((item) =>{
              return item.number > 0
            })
            this.pdfInfo.materials = arr
          }
        })
      },
      getPdfs() {
        this.pdfLoading = true
        this.disabledClass = true
        downloadPdf(this.claimno).then(res => {
          downloadFile(res, '受理回执信息', 'pdf')
          this.pdfLoading = false
          this.disabledClass = false
        })
      },
      // 查询申请人信息
      getApplicantInfo() {
        getApplicantInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.applicationInfo = response.data
          } else {
            this.$message({ message: '查询申请人信息错误！', type: 'error' })
          }
        })
      },
      // 查询被保人信息
      getInsuredInfo() {
        getInsuredInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.insuredInfo = response.data
            this.pastSurvey(this.insuredInfo.customerno)
            this.pastNegotiation(this.insuredInfo.customerno)
            this.getPastClaimsList(this.insuredInfo.customerno)
            // nsuredInfo.customerno
          } else {
            this.$message({ message: '查询被保人信息错误！', type: 'error' })
          }
        })
      },
      // 既往调查
      pastSurvey(insuredno) {
        let data = {
          insuredno: insuredno || '',
          pageno: 1,
          pagesize: 100,
          ispage: false
        }
        pastSurvey(data).then(response => {
          if (response.status === '1') {
            this.pastInfo.survey = response.data && response.data.list || []
          } else {
            this.$message({ message: '查询既往调查错误！', type: 'error' })
          }
        })
      },
      // 既往协谈
      pastNegotiation(insuredno) {
        pastNegotiation(insuredno).then(response => {
          if (response.status === '1') {
            this.pastInfo.negotiation = response.data
          } else {
            this.$message({ message: '查询既往协谈错误！', type: 'error' })
          }
        })
      },
      // 既往赔案
      getPastClaimsList(insuredno) {
        getPastClaimsList(insuredno).then(response => {
          if (response.status === '1') {
            this.pastInfo.claim = response.data
          } else {
            this.$message({ message: '查询既往赔案错误！', type: 'error' })
          }
        })
      },
      // 查询赔付信息
      getPayInfo() {
        getPayInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.payInfo = response.data
            this.conclusionreasonList = []
            if (this.payInfo.conclusion == '1') {
              this.conclusionreasonList = this.conclusionreasonOptions
            } else if (this.payInfo.conclusion == '2') {
              this.conclusionreasonList = this.conclusionreasonOptions
            } else if (this.payInfo.conclusion == '3') {
              this.conclusionreasonList = this.conclusionreason2Options
            } else if (this.payInfo.conclusion == '4') {
              this.conclusionreasonList = this.conclusionreason3Options
            } else if(this.payInfo.conclusion == '5') {
              this.conclusionreasonList = this.conclusionreason4Options
            } else {
              this.conclusionreasonList = this.conclusionreason5Options
            }
            if (response.data.finalamount != '' && response.data.finalamount != null && response.data.finalamount != undefined) {
              this.payInfo.finalamount = parseFloat(response.data.finalamount).toFixed(2)
            }
            this.finalamount = response.data.finalamount
            this.loadAll()
          } else {
            this.$message({ message: '查询赔付信息错误！', type: 'error' })
          }
        })
      },
      // 保存赔付信息
      savePayInfo() {
        if (this.payInfo.explanation!== '' && this.payInfo.explanation!== null) {
          this.payInfo.explanation = this.payInfo.explanation.replace(/\s+/g, "");
          if (this.payInfo.explanation == '') {
            this.$message.warning('请填写赔付说明信息！')
            return false
          }
        }
        if (this.payInfo.finalamount == 0) {
          if (this.payInfo.conclusion !== '3') {
            this.$message.warning('赔付金额为0，赔付结论为全额拒付！')
            return false
          }
        }
        this.$refs['payInfoForm'].validate(valid => {
          if (valid) {
            const { conclusion, finalamount, conclusionreason, explanation, applicationreason } = this.payInfo
            const params = {
              claimno: this.claimno,
              conclusion,
              finalamount,
              conclusionreason,
              explanation,
              applicationreason
            }
            savePayInfo(params).then(response => {
              if (response.status === '1') {
                this.getPayInfo()
                if(this.payeeInfo.length){
                  this.payeeInfo.map((item, i) => {
                    if(item.payoutratio){
                      let params = {
                        claimno: this.claimno,
                        payoutratio: item.payoutratio
                      }
                      calculationPayAmount(params).then(res => {
                        if (res.status === "1") {
                          if(res.data===0){
                            // this.amountFlag=true
                          }
                          this.$set(this.payeeInfo[i], 'payamount', res.data)
                        } else {
                          // this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: "error"})
                        }
                      })
                    } else {
                      this.$set(this.payeeInfo[i],'payamount',null)
                    }
                  })
                }
                this.$message({ message: '操作成功！', type: 'success' })
              } else {
                this.$message({ message: response.data.errMsg, type: 'error' })
              }
            })
          }
        })
      },
      // 查询领款人信息
      getPayeeInfo() {
        getPayeeInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.payeeInfo = response.data
          } else {
            this.$message({ message: '查询领款人信息错误！', type: 'error' })
          }
        })
      },
      // 查询保单信息
      relationPolicy() {
        relationPolicy(this.claimno).then(response => {
          if (response.status === '1') {
            this.policyInfo = response.data || []
          } else {
            this.$message({ message: '查询保单信息错误！', type: 'error' })
          }
        })
      },
      // 查询风险信息
      getDangerInfo() {
        getDangerInfo(this.claimno).then(response => {
          if (response.status === '1') {
            this.dangerInfo = response.data
            if (this.dangerInfo && this.dangerInfo.riskdetail == null) {
              this.dangerInfoActiveName = []
            } else {
              this.dangerInfoActiveName = ['1']
            }
          } else {
            this.$message({ message: '查询风险信息错误！', type: 'error' })
          }
        })
      },
      // 查询事件信息
      getEventInfoList() {
        getEventInfoList(this.claimno).then(response => {
          if (response.status === '1') {
            // this.eventInfo = response.data || []
            var arr = []
            arr = response.data || []
            arr.forEach((item,index) =>{
              item.id = index + 1
              item.casediseaselist.forEach((ele) =>{
                ele.judgmentbasisnameList = ele.judgmentbasisname ==  null ? '' : ele.judgmentbasisname.indexOf("$") == -1 ? [ele.judgmentbasisname] : ele.judgmentbasisname.split('$')
                return ele
              })
              return item
            })
            this.eventInfo = arr || []
          } else {
            this.$message({ message: '查询事件信息错误！', type: 'error' })
          }
        })
      },
      /**
       * 事件信息保存后需更新材料信息列表
       */
      relationEvent() {
        this.getEventInfoList()
        this.getMateriaList()
      },
      // 修改页面的编辑与只读状态
      updateStatus(status) {
        this.status = status
      },
      // 赔案备注信息
      getCaseRemark() {
        // type 报案 ‘01’， 赔案：‘02’
        const type = this.node === 'report' ? '01' : '02'
        const params = { relationno: this.node === 'report'?this.registerNo: this.claimno, type }
        getCaseRemark(params).then(response => {
          if (response.status === '1') {
            // if(response.data){
            //   this.caseRemark = response
            // }
          } else {
            this.$message({ message: '查询赔案备注信息错误！', type: 'error' })
          }
        })
      },
      // 获取材料信息
      getMateriaList() {
        let params = this.claimno?this.claimno:this.registerNo
        getMateriaList(params).then(response => {
          if (response.status === '1') {
            this.materialInfo.data = response.data
          } else {
            this.$message({ message: '查询材料信息错误！', type: 'error' })
          }
        })
      },
      getConclusionTypeData() {
        this.conclusionTypeData = []
        this.conclusionTypeData = this.conclusionOptions
      },
      // 获取赔付明细
      getPayDetail() {
        getPayDetail(this.claimno).then(response => {
          if (response.status === '1') {
            if (response.data) {
              let from = response.data.casegetdutylist
              let arr = this.conclusionTypeData
              let isExemptArr = []
              let exemptArr = []
              isExemptArr = arr.filter((item) =>{
                return item.value == '6' || item.value == '7'
              })
              exemptArr = arr.filter((item) =>{
                return item.value !== '6' && item.value !== '7'
              })
              response.data.casegetdutylist.map((item,i) =>{
                if (item.isExempt == true) {
                  item.conclusionList = isExemptArr
                } else {
                  item.conclusionList = exemptArr
                }
                if (item.conclusion == '1') {
                  item.reList = this.conclusionreasonOptions
                  item.disabledCon = false
                  item.disabledFinalamount = false
                } else if (item.conclusion == '2') {
                  item.reList = this.conclusionreasonOptions
                  item.disabledFinalamount = false
                  item.disabledCon = false
                } else if (item.conclusion == '3') {
                  item.reList = this.conclusionreason2Options
                  item.disabledFinalamount = true
                  item.disabledCon = false
                } else if (item.conclusion == '4') {
                  item.reList = this.conclusionreason3Options
                  item.disabledFinalamount = false
                  item.disabledCon = false
                } else if(item.conclusion == '5') {
                  item.reList = this.conclusionreason4Options
                  item.disabledFinalamount = false
                  item.disabledCon = false
                } else {
                  item.reList = this.conclusionreason5Options
                  item.disabledFinalamount = false
                  item.disabledCon = false
                }

                return item
              })
              this.payDetail = response.data.casegetdutylist
            }
          } else {
            this.$message({ message: '查询赔付明细错误！', type: 'error' })
          }
        })
      },
      // 查询补充物理件
      getMaterialPendingQuery() {
        const params = {}
        params.claimno = this.claimno || ''
        params.supplementaryno = this.supplementaryno || ''
        materialPendingQuery(params).then(res => {
          if (res.data) {
            if (res.data.errCode !== '' || res.data.errCode !== undefined) {
              this.$message({message: '查询物理件错误！', type: 'error'});
            }
          } else {
            this.supplementaryFrom = {
              tableData: res || []
            }
            this.noAllList = []
            res.length ? res.map((item) => {
              let ele = {}
              ele.materialtype = item.materialtype
              ele.materialname = item.materialname
              this.noAllList.push(ele)
            }) : []
          }
        })
      },
      // 时间轴
      caseSchedule() {
        this.loadTimeline = true
        this.timelineInfo = []
        caseProcessLog(this.claimno).then(response => {
          if (response.status === '1') {
            this.timelineInfo = response.data
          } else {
            this.$message({ message: '查询操作轨迹错误！', type: 'error' })
          }
        }).finally(() => {
          this.loadTimeline = false
        })
      },
      // // 时间轴
      // caseSchedule() {
      //   this.loadTimeline = true
      //   caseSchedule(this.claimno).then(response => {
      //     if (response.status === '1') {
      //       this.timelineInfo = []
      //       this.timelineInfo = response.data
      //     } else {
      //       this.$message({ message: '查询操作轨迹错误！', type: 'error' })
      //     }
      //   }).finally(() => {
      //     this.loadTimeline = false
      //   })
      // },
      caseSchedule1() {
        this.loadTimeline = true
        caseProcessLog(this.claimno).then(response => {
          if (response.status === '1') {
            this.timelineInfo1 = response.data
          } else {
            this.$message({ message: '查询操作轨迹错误！', type: 'error' })
          }
        }).finally(() => {
          this.loadTimeline = false
        })
      },
      getFileCount(){
        let temp = [
          { label: "全部", name: '00', total: 0 },
          { label: "其他证明材料", name: '9', total: 0 },
          { label: "理赔申请书", name: '0', total: 0 },
          { label: "身份证", name: '1', total: 0 },
          { label: "关系证明", name: '2', total: 0 },
          { label: "银行卡", name: '3', total: 0 },
          { label: "医疗票据", name: '4', total: 0 },
          { label: "病历", name: '5', total: 0 },
          { label: "费用清单", name: '6', total: 0 },
          { label: "检查报告", name: '7', total: 0 },
          { label: "保险事故证明", name: '8', total: 0 }
        ]
        let businessNo = ''
        if(this.node === 'report') {
          businessNo = this.registerNo
        } else {
          businessNo = this.claimno
        }
        getFileCount(businessNo).then(res => {
          if (res.status === '1') {
            if(res.data){
              this.tabLabels = res.data
            } else {
              for(let key in this.tabLabels){
                this.tabLabels[key] = temp
              }
              // this.tabLabels.originallist = temp
              // this.tabLabels.supplementlist = temp
              // this.tabLabels.supplementpartlist = temp
              // this.tabLabels.surveylist = temp
            }
          } else {
            for(let key in this.tabLabels){
              this.tabLabels[key] = temp
            }
          }
        })
      },
      changeList(type) {
        if(type==='1'){
          this.imageInfo.originallist.list = []
          this.imageInfo.originallist.previewList = []
        } else if(type==='2'){
          this.imageInfo.supplementlist.list = []
          this.imageInfo.supplementlist.previewList = []
        } else if(type==='3'){
          this.imageInfo.surveylist.list = []
          this.imageInfo.surveylist.previewList = []
        } else if(type==='4'){
          this.imageInfo.supplementpartlist.list =  []
          this.imageInfo.supplementpartlist.previewList = []
        }
      },
      // 影像文件
      getImageInfo(type,pageno,subtype=null, change=false) {
        let businessNo = ''
        if(this.node === 'report') {
          businessNo = this.registerNo
        } else {
          businessNo = this.claimno
        }
        if(businessNo !== null && businessNo !== '' && businessNo !== undefined) {
          this.imageLoad = true
          let params = {
            claimno: businessNo,
            type,
            subtype,
            pageno,
            pagesize: 50
          }
          // imageInfo: {
          //     originallist: [],
          //     supplementlist: [],
          //     surveylist: [],
          //     supplementpartlist: []
          // },
          getImageInfo(params).then(response => {
            if (response!=null && response.status === '1') {
              // 切换标签或上传触发
              if (change) {
                this.changeList(type)
              }
              if(params.type==='1'){
                // this.imageInfo.originallist = response.data || []
                // this.imageInfo.originallist.previewList = []
                if(response.data){
                  response.data.list.map((item, i) => {
                    this.imageInfo.originallist.list.push(item)
                    this.imageInfo.originallist.previewList.push('data:image/jpeg;base64,'+ item.src)
                  })
                }
              } else if(params.type==='2'){
                // this.imageInfo.supplementlist = response.data || []
                // this.imageInfo.supplementlist.previewList = []
                if(response.data){
                  response.data.list.map((item, i) => {
                    this.imageInfo.supplementlist.previewList.push('data:image/jpeg;base64,'+ item.src)
                    this.imageInfo.supplementlist.list.push(item)
                  })
                }
              } else if(params.type==='3'){
                // this.imageInfo.surveylist = response.data || []
                // this.imageInfo.surveylist.previewList = []
                if(response.data){
                  response.data.list.map((item, i) => {
                    this.imageInfo.surveylist.previewList.push('data:image/jpeg;base64,'+ item.src)
                    this.imageInfo.surveylist.list.push(item)
                  })
                }
              } else if(params.type==='4'){
                // this.imageInfo.supplementpartlist = response.data || []
                // this.imageInfo.supplementpartlist.previewList = []
                if(response.data){
                  response.data.list.map((item, i) => {
                    this.imageInfo.supplementpartlist.previewList.push('data:image/jpeg;base64,'+ item.src)
                    this.imageInfo.supplementpartlist.list.push(item)
                    this.imageInfo.supplementpartlist.previewList = [...this.imageInfo.supplementpartlist.previewList]
                    this.imageInfo.supplementpartlist.list = [...this.imageInfo.supplementpartlist.list]
                  })
                }
              }
            } else {
              this.$message({ message: '查询影像文件错误！', type: 'error' })
            }
          }).finally(() => {
            this.imageLoad = false
          })
        }
      },
      querySearch(queryString, cb) {
        let expList = this.expList
        let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
        cb(results ? results : [])
      },
      createFilter(queryString) {
        return (expList) => {
          return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        }
      },
      loadAll() {
        // let type = '01'
        // getTempalte(type).then(res =>{
        //   if (res.status == '1') {
        //     this.expList = res.data || []
        //   } else {
        //     this.$message.error('查询模版消息错误！')
        //   }
        // })
        this.expList = []
        const params = {
          templatetype: '01',
          supplementarymode: '',
          // insurancecode: '',
          // dutycode: '',
          circulationvalue1: this.payInfo.conclusion,
          circulationvalue2: this.payInfo.conclusionreason
        }
        getContents(params).then(res =>{
          if (res.status == '1') {
            res.data.forEach(item => {
              this.expList.push({ value: item })
            })
            // this.expList = res.data || []
          }
        })
      },
      invoiceHandle() {
        // this.$router.push({
        //   path: '/claims-handle/classificat',
        //   query: {
        //     node: 'input',
        //     claimNo: encrypt(this.claimno),
        //     insuredInfo: this.insuredBaseInfo,
        //     insuredName: this.insuredName
        //   }
        // })
        // let data = {
        //   node: 'input',
        //   claimNo: encrypt(this.claimno),
        //   insuredInfo: this.insuredBaseInfo,
        //   insuredName: this.insuredName
        // }
        const newpage = this.$router.resolve({
          name: 'invoiceHandle',
          params:{},
          query:{  node: 'input',
            claimNo: encrypt(this.claimno),
            insuredInfo:JSON.stringify(this.insuredBaseInfo),
            insuredName: this.insuredName }
        })
        window.open(newpage.href, '_blank');
      },
      openReporInfo(type) {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.reportType = type
        if(this.node==='accept' || this.node==='input'){
          this.reportVis = true
        } else {
          this.trialReporVis = true
        }
      },
      openFileList() {
        this.fileListVis = true
      },
      openFillis() {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.fillisVis = true
      },
      openNegotiat() {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.negotiatVis = true
      },
      openHandle(title,placeholder) {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.diaTitle = title
        this.handleVis = true
        this.placeholder = placeholder
      },
      openSplit() {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.splitVis = true
      },
      // openPolicy() {
      //     this.policyVis = true
      // },
      openBreakOff(){
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.breakVis = true
      },
      openTone() {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.toneVis = true
      },
      conclusionChange(val) {
        if (this.node==='firstTrial') {
          this.$set(this.resultForm, 'reviewer', '')
        }
        // sopckDisabled
        if(val==='03'){
          this.disabledConclusion = false
          this.status = 'show'
          this.rejectReturnFlag = true
        } else if(val === '01'){
          this.disabledConclusion = false
          this.status = 'handle'
          this.rejectReturnFlag = false
        } else {
          this.disabledConclusion = true
          this.status = 'handle'
          this.rejectReturnFlag = false
        }
        if (this.sopckDisabled === false) {
          this.status = 'show'
        }
        this.$refs.resultForm.clearValidate(['rejectreason', 'errortype'])
        val === '01' ? this.isrequired = false : this.isrequired = true
      },
      rejectreasonChange(val){
        this.$refs.returnForm.clearValidate(['rejectreason', 'errortype'])
        if(this.ruleForm.errortype){
          this.$set(this.ruleForm, 'errortype', '')
        }
        // this.resultForm.conclusion==='02'?this.errortypeRequired = true : this.errortypeRequired = false
        // val === '01' ? this.errortypeRequired = true : this.errortypeRequired = false
      },
      confireExemption() {
        saveExemption(this.claimno).then(res =>{
          if (res.status == '1') {
            this.saveConclusion(this.calseExemptionFrom)
            this.dialogVisible = false
          } else {
            this.$message({ message: res.data.errMsg?res.data.errMsg : '豁免失败！', type: 'error' })
            this.dialogVisible = false
          }
        })
      },
      calseExemption() {
        this.saveConclusion(this.calseExemptionFrom)
        this.dialogVisible = false
      },
      calseExemption2() {
        this.saveConclusion(this.calseExemptionFrom)
        this.dialogVisible = false
      },
      saveConclusionCheck(params) {
        let query = {
          claimno: this.claimno,
          node: this.mapNode(this.node)
        }
        this.calseExemptionFrom = params
        this.submitLoading = true
        saveConclusionCheck(query).then(res => {
          if (res.status === '1') {
            if (res.data.istips == true) {
              this.$message({ message: res.data.msg, type: 'error' })
              return false
            } else {
              if (res.data.msg == ''){
              } else {
                this.$message({ message: res.data.msg, type: 'warning' })
              }
              if (this.node == 'firstTrial' || this.node == 'review' ||this.node == 'spotCheck' || this.node == 'complex' || this.node == 'correct') {
                queryAccordWithExemption(this.claimno).then(res =>{
                  if (res.status == '1') {
                    if (res.data == 0) {
                      this.saveConclusion(params)
                    } else {
                      queryWaitExemptionCount(this.claimno).then(res =>{
                        if (res.status == '1') {
                          if (res.data  == 0) {
                            this.saveConclusion(params)
                          } else {
                            queryPolicyExemptionDetail(this.claimno).then(res =>{
                              if (res.status == '1') {
                                if (res.data == 0) {
                                  queryExemptionStartDate(this.claimno).then(res =>{
                                    if (res.status == '1') {
                                      this.dialogVisible = true
                                      this.exemptionTitle = '触发自动豁免规则，确认后将按照豁免责任进行自动豁免'
                                      this.exemptionTitleFlag =true
                                    } else {
                                      this.$message({message: res.data.errMsg?res.data.errMsg : '获取豁免开始日期失败！', type: 'error' })
                                    }
                                  })
                                } else {
                                  this.dialogVisible = true
                                  this.exemptionTitleFlag = false
                                  this.exemptionTitle = '存在未豁免的责任信息，是否进行处理？'
                                }
                              } else {
                                this.$message({message: res.data.errMsg?res.data.errMsg : '查询已豁免条数失败！', type: 'error' })
                              }
                            })
                            // this.dialogVisible = true
                            // this.calseExemptionFrom = params
                          }
                        } else {
                          this.$message({message: res.data.errMsg?res.data.errMsg : '查询待豁免条数失败！', type: 'error' })
                        }
                      })
                    }
                  } else {
                    this.$message({message: res.data.errMsg?res.data.errMsg : '豁免失败！', type: 'error' })
                  }
                })
              } else {
                this.saveConclusion(params)
              }
            }
          } else {
            this.$message({ message: '操作失败！', type: 'error' })
          }
        }).finally(() => {
          this.submitLoading = false
        })
      },
      confireSaveConculsion(params) {
        this.submitLoading = true
        saveConclusion(this.processCheckData).then(res => {
          if (res.status === '1') {
            this.saveconsdialogVisible = false
            this.$message({ message: '操作成功！', type: 'success' })
            this.status = 'show'
            this.acceptFlag = true
            this.disableFlag = true
            this.sopckDisabled = true
          } else {
            if (res.data.errCode == 'CL001' || res.data.errCode == 'CL000') {
              this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'error' })
            } else {
              this.$message({ message: res.data.errMsg ? res.data.errMsg:'操作失败！', type: 'warning' })
            }
            this.saveconsdialogVisible = false
          }
        }).finally(() => {
          this.submitLoading = false
          this.saveconsdialogVisible = false
        })
      },
      saveConclusion(params) {
        this.processCheckData = params
        processCheck(params).then(res =>{
          if (res.status == '1') {
            this.processCheckData.node = res.data.node
            this.processCheckData.personalpool = res.data.personalpool
            if (res.data.personalpool == true) {
              this.saveconsdialogVisible = true
            } else {
             this.confireSaveConculsion(this.processCheckData)
            }
          } else {
            this.$message({ message: res.data.errMsg , type: 'warning' })
          }
        })
      },
      submitHandle() {
        this.$refs.resultForm.validate((valid) => {
          if (valid) {
            judgeBnf(this.claimno).then(response => {
              if (response.status === '1') {
                if(response.data !== '' && this.resultForm.conclusion !=='02') {
                  this.$message({ message: response.data, type: 'warning' })
                }else{
                  const params = {
                    claimno: this.claimno,
                    ...this.resultForm,
                    tasknode: this.mapNode(this.node)
                  }
                  if(this.caseLastNodeInfo.conclusion==='02'){
                    params.isreturn = 'Y'
                    params.status = this.caseLastNodeInfo.status
                    params.reviewer = this.caseLastNodeInfo.creator
                    let nextCaseNode =this.selectDictLabel(this.casestepOptions, this.caseLastNodeInfo.tasknode)
                    params.nexttasknode = this.mapNode(nextCaseNode)
                  }
                  if (this.querys.conclusionType) {
                    this.submitLoading = true
                    params.detailno = this.querys.detailno
                    updateCheck(params).then(res => {
                      if (res.status === '1') {
                        this.$message({ message: '操作成功！', type: 'success' })
                        // setTimeout(() =>{
                        //   this.goBack()
                        // },2000)
                      } else {
                        if (res.data.errCode=='CL001' || res.data.errCode == 'CL000') {
                          this.$message({ message: res.data.errMsg, type: 'error' })
                        } else {
                          this.$message({ message: res.data.errMsg, type: 'warning' })
                        }
                      }
                    }).finally(() => {
                      this.submitLoading = false
                      this.sopckDisabled = true
                    })
                  } else {
                    if(this.resultForm.conclusion !=='02'){
                      this.saveConclusionCheck(params)
                    } else {
                      this.saveConclusion(params)
                    }
                  }
                }
              }
            })
          } else {
            return false
          }
        })
        // }
      },
      goBack() {
        window.close()
        // this.$router.go(-1)
      },
      registerNoUpdate(data) {
        this.registerNo = data
        this.getRegisterDetailInfo()
      },
      registerSourceUpdate(data) {
        this.registerSource = data
      },
      applySourceUpdate(data) {
        this.fixInfo.applicationsource = data
      },
      insuredNoUpdate(data) {
        this.insuredNo = data
      },
      insuredInfoUpdate(data) {
        this.insuredBaseInfo = data
        if(this.node === 'report') {
          if(this.$refs['reporterCom'].baseForm.relationship === '00') {
            this.$refs['reporterCom'].baseForm.reportName = this.insuredBaseInfo.insuredName
            this.$refs['reporterCom'].baseForm.cardType = this.insuredBaseInfo.idType
            this.$refs['reporterCom'].baseForm.idCard = this.insuredBaseInfo.idNo
            this.$refs['reporterCom'].baseForm.gender = this.insuredBaseInfo.sex
            if(this.insuredBaseInfo.phone !== null && this.insuredBaseInfo.phone !== ''){
              this.$refs['reporterCom'].baseForm.phone = this.insuredBaseInfo.phone
            }
            if(this.insuredBaseInfo.mobiles[0] !== null && this.insuredBaseInfo.mobiles[0] !== '') {
              this.$refs['reporterCom'].baseForm.mobile = this.insuredBaseInfo.mobiles[0]
            }
            if(this.insuredBaseInfo.email !== null && this.insuredBaseInfo.email !== '') {
              this.$refs['reporterCom'].baseForm.email = this.insuredBaseInfo.email
            }
          }
        }else {
          if(this.$refs['applicant'].baseForm.relationship === '00') {
            this.$refs['applicant'].baseForm.reportName = this.insuredBaseInfo.insuredName
            this.$refs['applicant'].baseForm.cardType = this.insuredBaseInfo.idType
            this.$refs['applicant'].baseForm.idCard = this.insuredBaseInfo.idNo
            this.$refs['applicant'].baseForm.gender = this.insuredBaseInfo.sex
            if(this.insuredBaseInfo.phone !== null && this.insuredBaseInfo.phone !== ''){
              this.$refs['applicant'].baseForm.phone = this.insuredBaseInfo.phone
            }
            if(this.insuredBaseInfo.mobiles[0] !== null && this.insuredBaseInfo.mobiles[0] !== '') {
              this.$refs['applicant'].baseForm.mobile = this.insuredBaseInfo.mobiles[0]
            }
            if(this.insuredBaseInfo.email !== null && this.insuredBaseInfo.email !== '') {
              this.$refs['applicant'].baseForm.email = this.insuredBaseInfo.email
            }
            // this.$refs['applicant'].baseForm.phone = this.insuredBaseInfo.phone
            // this.$refs['applicant'].baseForm.mobile = this.insuredBaseInfo.mobiles[0]
            // this.$refs['applicant'].baseForm.email = this.insuredBaseInfo.email
            if(this.$refs['applicant'].tableData.length > 0) {
              const data = this.$refs['applicant'].tableData
              for(let i=0;i<data.length;i++) {
                if(data[i].relation === '00') {
                  data[i].payee = this.insuredBaseInfo.insuredName
                  data[i].cardType = this.insuredBaseInfo.idType
                  data[i].idCard = this.insuredBaseInfo.idNo
                  data[i].sex = this.insuredBaseInfo.sex
                  if(this.insuredBaseInfo.mobiles[0] !== null && this.insuredBaseInfo.mobiles[0] !== '') {
                    data[i].mobile = this.insuredBaseInfo.mobiles[0]
                  }
                  // data[i].mobile = this.insuredBaseInfo.mobiles[0]
                  data[i].validSign = this.insuredBaseInfo.checked
                  data[i].dateRange = this.insuredBaseInfo.dateRange
                  data[i].nation = this.insuredBaseInfo.nationality
                  data[i].occupation = this.insuredBaseInfo.occupation
                  data[i].address = this.insuredBaseInfo.area
                  data[i].detailAddress = this.insuredBaseInfo.address
                }
              }
            }
          }
        }
      },
      insuredSaveUpdate(data) {
        this.insuredSave = data
      },
      reportSaveUpdate(data) {
        this.reportSave = data
      },
      reportCountsUpdate(data) {
        this.getRelationReports(data)
      },
      applySaveUpdate(data) {
        this.applySave = data
      },
      renovateList(data) {
        this.getMateriaList()
      },
      // 获取报案详情数据
      getRegisterDetailInfo() {
        getRegisterInfo(this.registerNo).then(res => {
          if (res.status === '1') {
            if(res.data.insuredNo !== null && res.data.insuredNo !== '') {
              this.insuredNo = res.data.insuredNo
              this.insuredBaseInfo = res.data.insuredInfo
              this.$refs['insuredCom'].baseForm = res.data.insuredInfo
              this.$refs['insuredCom'].tableData = res.data.policyShowList
              this.$refs['insuredCom'].baseForm.phones = []
              this.getLabel(res.data.insuredNo)
              if(res.data.insuredInfo.checked === true) {
                this.$refs['insuredCom'].tableFormRules.certificate = { required: false, message: '不能为空!', trigger: 'change' }
              }
              if (res.data.insuredInfo.mobiles.length > 0) {
                res.data.insuredInfo.mobiles.forEach(item => this.$refs['insuredCom'].baseForm.phones.push({ value: item }))
              } else {
                this.$refs['insuredCom'].baseForm.phones = [{ value: '' }]
              }
              this.insuredSave = true
            }
            if (res.data.report.relationship != null) {
              this.$refs['reporterCom'].baseForm = res.data.report
              this.reportSave = true
            }
            if (res.data.apply.reason !== null && res.data.apply.reason.length > 0) {
              this.$refs['applyCom'].baseForm = res.data.apply
              this.applySave = true
            }
            if (this.registerStatus === '01') {
              this.$refs['insuredCom'].disableFlag = true
              this.$refs['reporterCom'].disableFlag = true
              this.$refs['applyCom'].disableFlag = true
              this.disableFlag = true
            }
          } else {
            this.$message({ message: '查询报案详情数据失败！', type: 'error' })
          }
        })
      },
      // 获取受理详情数据
      getClaimDetailInfo() {
        getClaimInfo(this.claimno).then(res => {
          if (res.status === '1') {
            this.insuredNo = res.data.insuredNo
            this.insuredBaseInfo = res.data.insuredInfo
            this.$refs['insuredCom'].baseForm = res.data.insuredInfo
            this.$refs['insuredCom'].lightList = res.data.pitchList
            this.$refs['insuredCom'].tableData = res.data.policyShowList
            this.$refs['insuredCom'].dealData(this.$refs['insuredCom'].tableData)
            this.$refs['insuredCom'].baseForm.phones = []
            this.getLabel(res.data.insuredNo)
            this.getRelationReports(this.claimno)
            if(res.data.insuredInfo.checked === true) {
              this.$refs['insuredCom'].tableFormRules.dateRange = { required: false, message: '不能为空!', trigger: 'change' }
            }
            if (res.data.insuredInfo.mobiles.length > 0) {
              res.data.insuredInfo.mobiles.forEach(item => this.$refs['insuredCom'].baseForm.phones.push({ value: item }))
            } else {
              this.$refs['insuredCom'].baseForm.phones = [{ value: '' }]
            }
            this.insuredSave = true
            if (res.data.report.relationship != null) {
              this.$refs['applicant'].baseForm = res.data.report
              this.$refs['applicant'].tableData = res.data.payeeInfoList
              this.applicantSave = true
            }
            if (res.data.apply.reason !== null && res.data.apply.reason.length > 0) {
              this.$refs['applyCom'].baseForm = res.data.apply
              this.applySave = true
            }
            if (this.status === 'show') {
              this.$refs['insuredCom'].disableFlag = true
              this.$refs['applicant'].disableFlag = true
              this.$refs['applyCom'].disableFlag = true
              this.disableFlag = true
              this.acceptFlag = true
            }
          } else {
            this.$message({ message: '查询赔案详情数据失败！', type: 'error' })
          }
        })
      },
      getRelationReports(value) {
        getCounts(value).then(res => {
          if (res.status === '1') {
            this.relationCounts = res.data
          }
        })
      },
      // 报案确认
      reportConfirm() {
        this.reportConfirmLoading = true
        if (this.insuredSave && this.reportSave && this.applySave) {
          registerConfirm(this.registerNo).then(res => {
            if (res.status === '1') {
              this.$message({ message: '报案确认成功！', type: 'success' })
              this.disableFlag = true
              this.acceptFlag = true
              this.$refs['reporterCom'].disableFlag = true
              this.reportConfirmLoading = false
              // this.$router.push('report')
            } else {
              this.$message({ message: '报案确认失败！', type: 'error' })
              this.reportConfirmLoading = false
            }
          })
        } else {
          if(!this.insuredSave) {
            this.$message({ message: '请保存被保人信息！', type: 'warning' })
            this.reportConfirmLoading = false
            return
          }
          if(!this.reportSave) {
            this.$message({ message: '请保存报案人信息！', type: 'warning' })
            this.reportConfirmLoading = false
            return
          }
          if(!this.applySave) {
            this.$message({ message: '请保存报案信息！', type: 'warning' })
            this.reportConfirmLoading = false
            return
          }
        }
      },
      // 受理确认
      acceptConfirm() {
        this.acceptLoading = true
        if (this.insuredSave && this.applicantSave && this.applySave) {
          const requestData = {
            insuredNo:this.insuredNo,
            claimNo:this.claimno
          }
          judgeBnf(this.claimno).then(response => {
            if (response.status === '1') {
              if(response.data !== '') {
                this.$message({ message: response.data, type: 'warning' })
                this.acceptLoading = false
              }else{
                judgeClaim(requestData).then(res => {
                  if(res.status === '1') {
                    if(res.data.flag) {
                      this.$confirm(res.data.tips, '温馨提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                      }).then(() => {
                        judgeRegister(this.claimno).then(res => {
                          if(res.status === '1') {
                            if(res.data) {
                              this.$confirm('存在尚未关联的报案信息，是否继续操作', '温馨提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                              }).then(() => {
                                this.acceptDeal()
                              }).catch(error => {
                                this.acceptLoading = false
                              })
                            }else{
                              this.acceptDeal()
                            }
                          }
                        })
                      }).catch(error => {
                        this.acceptLoading = false
                      })
                    } else {
                      judgeRegister(this.claimno).then(res => {
                        if(res.status === '1') {
                          if(res.data) {
                            this.$confirm('存在尚未关联的报案信息，是否继续操作', '温馨提示', {
                              confirmButtonText: '确定',
                              cancelButtonText: '取消',
                              type: 'warning'
                            }).then(() => {
                              this.acceptDeal()
                            }).catch(error => {
                              this.acceptLoading = falsethis.acceptLoading = false
                            })
                          }else{
                            this.acceptDeal()
                          }
                        }
                      })
                    }
                  } else {
                    this.$message({ message: '校验异常！', type: 'error' })
                    this.acceptLoading = false
                  }
                })
              }
            }
          })
        } else {
          if(!this.insuredSave) {
            this.$message({ message: '请保存被保人信息！', type: 'warning' })
            this.acceptLoading = false
            return
          }
          if(!this.applicantSave) {
            this.$message({ message: '请保存申请人信息！', type: 'warning' })
            this.acceptLoading = false
            return
          }
          if(!this.applySave) {
            this.$message({ message: '请保存申请信息！', type: 'warning' })
            this.acceptLoading = false
            return
          }
        }
      },
      // 受理完成
      acceptDeal() {
        this.acceptLoading = true
        acceptConfirm(this.claimno).then(res => {
          if (res.status === '1') {
            this.$message({ message: '受理确认成功！', type: 'success' })
            this.getpdfMessage()
            this.acceptFlag = true
            this.acceptLoading = false
            // this.$router.push('accept')
          } else {
            this.$message({ message: '受理确认失败！', type: 'error' })
            this.acceptLoading = false
          }
        })
      },
      // 录入完成
      inputConfirm() {
        const requestData = {
          claimno: this.claimno,
          conclusion: this.resultForm.result,
          explanation: this.resultForm.desc,
          tasknode: 'claim_input'
        }

        this.submitLoading = true
        saveConclusion(requestData).then(res => {
          if (res.status === '1') {
            this.$message({ message: '录入确认成功！', type: 'success' })
            this.$router.push('input')
          } else {
            this.$message({ message: '录入确认失败！', type: 'error' })
          }
        }).finally(() => {
          this.submitLoading = false
        })
      },
      claimNoUpdate(data) {
        this.claimno = data
        this.getClaimDetailInfo()
        this.getbasicInfo()
      },
      applicantSaveUpdate(data) {
        this.applicantSave = data
        // this.getClaimDetailInfo()
      },
      batchPrinting() {
        const printIframe = document.getElementById('print-iframe')
        const print = printIframe.contentWindow
        const doc = print.document
        doc.write(this.$refs.printall.innerHTML)
        doc.close()
        print.focus()
        print.print()
      },
      getBills() {
        getBillInfos(this.claimno).then(res => {
          if (res.status === '1') {
            this.billsInfo = res.data
          } else {
            this.$message({ message: '账单信息查询失败！', type: 'error' })
          }
        })
      },
      imageUpdate(data) {
        if((this.claimno !== null && this.claimno !== "" && this.claimno!== undefined) || (this.registerNo !== null && this.registerNo !== "" && this.registerNo!== undefined)){
          this.imageInfo.originallist.list = []
          this.imageInfo.originallist.previewList = []
          this.imageInfo.supplementlist.list = []
          this.imageInfo.supplementlist.previewList = []
          this.imageInfo.surveylist.list = []
          this.imageInfo.surveylist.previewList = []
          this.imageInfo.supplementpartlist.list = []
          this.imageInfo.supplementpartlist.previewList = []
          this.getImageInfo('1', 1, null, true)
          if(this.node !== 'report') {
            this.getImageInfo('2', 1, null, true)
            this.getImageInfo('3', 1, null, true)
            this.getImageInfo('4', 1, null, true)
          }
          this.getFileCount()
          if (this.node !== 'report') {
            this.updateMaterialList()
          }
          this.$refs.imagecom.initImages()
        }
      },
      updateMaterialList() {
        if (this.node === 'accept') return
        updateMaterial(this.claimno).then(res => {
          if (res.status === '1') {
            this.getMateriaList()
          }
        })
      },
      imageDeal() {
        if((this.claimno !== null && this.claimno !== "" && this.claimno!== undefined) || (this.registerNo !== null && this.registerNo !== "" && this.registerNo!== undefined)){
          let businessNo = ''
          if(this.node === 'report') {
            businessNo = this.registerNo
          }else {
            businessNo = this.claimno
          }
          imageDeal(businessNo).then(res => {
            if (res.status === '1') {
            }
          })
        }
      },
      getLabel(data) {
        getCustomerLabel(data).then(res => {
          if (res.status === '1') {
            this.$refs['insuredCom'].labelList = res.data
            this.$refs['insuredCom'].showFlag = true
          }
        })
      },
      isAppealCorrection(claimno) {
        isAppealCorrection(claimno).then(res => {
          if (res.status === '1') {
            // this.isAppeal = res.data
          }
        })
      },
      // 最小化
      toMinimize() {
        let minimize = this.$refs.minimize.$el
        let wrapper = document.getElementsByClassName('v-modal')[0]
        setTimeout(() => {
          minimize.style.transform = 'scale(0.05, 0.05)'
          minimize.style.transformOrigin = 'left bottom'
          minimize.style.transition = 'transform 0.5s'
          // minimize.style.zIndex = '999'
          wrapper.style.transform = 'scale(0.05, 0.05)'
          wrapper.style.transformOrigin = 'left bottom'
          wrapper.style.transition = 'transform 0.5s'
          wrapper.style.zIndex = '3000'
          wrapper.onclick = this.toBigmize
        }, 0)
      },
      // 最大化
      toBigmize() {
        let minimize = this.$refs.minimize.$el
        let wrapper = document.getElementsByClassName('v-modal')[0]
        setTimeout(() => {
          // minimize.style.zIndex = '2001'
          minimize.style.transform = 'scale(1, 1)'
          minimize.style.transformOrigin = 'left bottom'
          minimize.style.transition = 'transform 0.5s'
          wrapper.style.transform = 'scale(1, 1)'
          wrapper.style.transformOrigin = 'left bottom'
          wrapper.style.transition = 'transform 0.5s'
          wrapper.style.zIndex = '2000'
          wrapper.onclick = null
        }, 0)
      }
    }
  }
</script>
<style lang='scss' scoped>
  .active {
    color: #67c23a;
    background: #fff;
  }
  .nav-flex-box {
    display: flex;
    width: 100%;
    height: 40px;
    line-height: 40px;
    color: #409eff;
    justify-content: center;
    background: #f8f8f8;
    z-index: 99999;
  }
  .flex-item-btn {
    flex: 1;
    font-size: 12px;
    padding: 0 3px;
    border: 1px solid #eee;
    border-right: none;
    margin: 0 auto;
    cursor: pointer;
  }
  .flex-item-btn:last-child {
    border-right: 1px solid #eee;
  }
  .flex-item-btn>span {
    display: inline-block;
    width: 100%;
    text-align: center;
  }
  .nav-bar {
    position: fixed;
    top: 0;
    right: 20px;
    z-index: 9;
    width: calc(100% - 240px) !important;
    transition: width 0.28s !important;
  }
  .hideSidebar .nav-bar {
    width: calc(100% - 94px) !important;
    transition: width 0.28s !important;
  }
  .top-instance {
    top: 84px;
  }
  .nav-bar /deep/ .el-tabs--border-card>.el-tabs__content {
    padding: 0
  }
  .el-tabs /deep/ .el-tabs__item {
    width: auto;
    font-size: 12px;
    padding: 0 12px;
  }
  .nav-bar /deep/ .el-card__body,
  .top-card /deep/ .el-card__body {
    padding: 10px 20px;
  }
  .basic-box /deep/ .el-form-item__label {
    font-size: 12px;
  }
  .el-col-7 /deep/ .el-form-item {
    margin-bottom: 10px;
  }
  .el-col-12 /deep/ .el-form-item {
    margin-bottom: 4px;
  }
  .el-col-7 /deep/ .el-form-item__label,
  .form-item-span {
    font-size: 12px;
  }
  .baseInfo_class /deep/.el-card__body {
  padding: 0;
  }
  .child_class {
    width: 100%;
    border-bottom: 1px solid rgb(220, 220, 220);
  }
  .card-title {
    margin-top: 0;
    width: 100%;
    border-bottom: 1px solid rgb(220, 220, 220);
    height: 50px;
    line-height: 50px;
    // border-bottom: 1px solid #666;
  }
  .baseinfo_class_title {
    width: 100%;
    height: 40px;
    line-height: 40px;
    border-bottom: 1px solid #666;
  }
  .opened-adapt {
    width: 97.2%;
    // width: calc(100% - 233px);
    transition: .5s;
  }
  .close-adapt {
    width: 97.2%;
    // width: calc(100% - 78px);
    transition: .5s;
  }
  .startjump {
    margin-top: 90px;
  }
  .navFalgClass {
    margin-top: 10px !important;
  }
</style>
<style>
  .baseInfo_class .el-tag--small {
    margin-right: 10px !important;
  }
  .el-tooltip__popper{
    max-width: 50%;
  }
  .el-autocomplete-suggestion li {
    width: 100%;
    white-space: pre-wrap;
    word-break: break-word;
    height: auto;
    line-height: 18px;
    padding: 0 5px;
  }
  .el-collapse-item__wrap {
    border: none !important;
  }
  .el-collapse {
    border: none;
  }
</style>
