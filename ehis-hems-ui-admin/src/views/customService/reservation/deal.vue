<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <!--clearable是清楚输入框内容 readly、只读不可以编辑 ；不可以共存-->
          <el-col :span="8">
            <el-form-item label="保单号：" prop="Service"  >
              <el-input readonly v-model="sendForm.acceptor" class="item-width"  size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="channel" readonly>
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="投保人证件类型:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="被保人性别:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人出生日期:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人证件号:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件类型:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人电话:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保日期:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="承保日期:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保溢满期日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="主招揽业务员:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="主招揽业务员电话:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="VIP标识:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="是否UHCG会员:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="与主保险人关系:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="险种代码:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划名称:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="首次生效日:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="AM(服务经理):"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="BD(销售经理):"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="特定医院赔付比例:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人性质:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保来源:"  prop="Acceptor">
              <el-input v-model="sendForm.acceptor" class="item-width" readonly size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </el-card>


    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="200px"
               label-position="right" size="mini">

        <span style="color: blue">口腔责任直接结算-服务受理信息</span>
        <el-divider/>

        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="callCenterNo">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterNo">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="ruleForm.priorityLevel" class="item-width" placeholder="请选择">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="phone">
              <el-input v-model="ruleForm.callName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="ruleForm.callMobilePhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="传真：" prop="phone">
              <el-input v-model="ruleForm.callMobilePhone" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="来电人与申请人关系：" prop="priority">
              <el-select v-model="ruleForm.callRelationBy" class="item-width" placeholder="请选择">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="ContactsLanguage">
              <el-select v-model="ruleForm.contactsLanguage" class="item-width" placeholder="请选择">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人家庭电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="联系人办公电话：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="sendForm.organCode" class="item-width" placeholder="请选择">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约时间：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医疗机构：" prop="email">
              <el-col :span="14">
                <el-input v-model="sendForm.beInsuredName" input-w readonly size="mini" placeholder="请输入"/>
              </el-col>
              <el-button type="primary" @click="searchHandle" disabled>详细信息</el-button>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院工作来电：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科室：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="医院赔付比例：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否持有效证件：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="*是否持保险卡：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次疾病/症状起病时间：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="类似疾病症状最早发生时间：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病名称：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="疾病或体征：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否首次就诊：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly  size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否意外：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="意外原因：" prop="email">
              <el-input v-model="ruleForm.email" class="item-width" readonly size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="业务内容：" prop="beInsuredName">
            <el-input v-model="sendForm.beInsuredName" class="width-full" readonly size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-row>

      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color:blue;">业务流转情况</span>
        <el-divider></el-divider>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="flowLogData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" width="140" prop="status" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" prop="operateCode" label="操作" show-overflow-tooltip/>
          <el-table-column align="center" prop="makeBy" label="受/处理人" show-overflow-tooltip/>
          <el-table-column align="center" prop="umNum" label="UM账号" show-overflow-tooltip/>
          <el-table-column prop="makeTime" label="时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.makeTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="说明" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-link v-if="scope.row.operateCode=='01'" style="font-size:12px" type="primary" @click="modifyDetails(scope.row)">修改说明</el-link>
            </template>
          </el-table-column>
          <modify-details ref="modifyDetails"></modify-details>
          <el-table-column prop="opinion" align="center" label="处理意见" show-overflow-tooltip/>
          <el-table-column prop="toDepartment" align="center" label="流转部门" show-overflow-tooltip/>
          <el-table-column prop="toReason" align="center" label="流传原因" show-overflow-tooltip/>
        </el-table>

        <pagination
          v-show="flowLogCount>0"
          :total="flowLogCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchFlowLog"
        />
      </div>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">HCS服务预约预修改记录</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="HCSPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column prop="alterTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.alterTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="alterId" align="center" label="修改序列号" show-overflow-tooltip/>
          <el-table-column prop="alterChannel" align="center" label="预约渠道" show-overflow-tooltip/>
          <el-table-column prop="alterContent" align="center"  style="width: available" label="修改内容描述" show-overflow-tooltip/>
        </el-table>
        <pagination
          v-show="HCSTotal>0"
          :total="HCSTotal"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHCS"
        />
      </div>
    </el-card>

    <el-card>
        <el-form ref="submitForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="130px"
                 label-position="right" size="mini">
        <span style="color: blue">服务处理
          <div style="text-align: right; margin-right: 8px;">
            <el-button type="primary" size="mini" @click="modify">修改</el-button>
            <el-button type="primary" size="mini" @click="cancle">取消</el-button>
          </div>
        </span>

          <el-divider/>
          <el-row>
            <el-form-item label="处理时长：" prop="times">
              <el-input v-model="submitForm.times" class="width-full"  size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-row>
       <el-row>
        <el-col :span="8">
        <el-form-item label="业务处理情况：" prop="businessProcess" >
          <el-radio-group v-model="submitForm.businessProcess">
            <el-radio   label="01">成功</el-radio>
            <el-radio   label="02">响应</el-radio>
          </el-radio-group>
        </el-form-item>
        </el-col>
        </el-row>
          <el-row>
        <el-form-item label="处理说明：" prop="remark">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="submitForm.remark">
          </el-input>
        </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="客户反馈：" prop="customerFeedback" >
              <el-radio-group v-model="submitForm.customerFeedback">
                <el-radio   label="01">满意</el-radio>
                <el-radio   label="02">接受</el-radio>
                <el-radio   label="03">不接受</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="是否需要担保函：" prop="closeType">
                <el-select v-model="submitForm.costIncurred" class="item-width" placeholder="请选择" controls-position="right" :min="0">
                  <el-option v-for="item in serves" :key="item.value" :label="item.label"
                             :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>


        </el-form>


    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button  type="primary" style="float: right" size="mini" @click="upload" disabled>上传附件</el-button></span>
       <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="HCSPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="policyNumber" label="附件名称" show-overflow-tooltip/>
          <el-table-column align="center"  prop="secondNumber" label="附件类型" show-overflow-tooltip/>
          <el-table-column prop="riskCode" align="center" label="上传人" show-overflow-tooltip/>
          <el-table-column prop="beInsuredName" align="center" label="上传时间 " show-overflow-tooltip/>
          <el-table-column prop="insuredName" align="center" label="备注" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="download(scope.row)" disabled>下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: right; margin-right: 1px;">
        <transfer ref="transfer"></transfer>
        <up-load ref="upload"></up-load>
        <co-organizer ref="coOrganizer"></co-organizer>
        <el-button  type="primary"  size="mini" @click="transfer">转办</el-button>
        <el-button  type="primary" size="mini" @click="coOrganizer" disabled>担保函导出</el-button>
        <el-button  type="primary"  size="mini" @click="upload" disabled>保单信息查询</el-button>
        <el-button  type="primary" size="mini" @click="temporary">暂存</el-button>
        <el-button type="primary" size="mini" @click="submit">提交</el-button>
        <el-button type="primary" size="mini" @click="upload" disabled>发送短信</el-button>

      </div>
    </el-card>



  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch,HMSSearch} from '@/api/customService/demand'
  import {demandListAndPublicPool,demandListAndPersonalPool,dealReservationSubmit} from '@/api/customService/reservation'

  import transfer from "../common/modul/transfer";
  import upLoad from "../common/modul/upload";
  import coOrganizer from "../common/modul/coOrganizer";
  import modifyDetails from "../common/modul/modifyDetails";

  let dictss = [{dictType: 'product_status'}]
  export default {
    components: { transfer ,
                  upLoad,
                  coOrganizer,
      modifyDetails,
    },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {

      return {
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        // //传过来的工单号
        // workOrderNo:"",
        // policyNo:"",
        // policyItemNo:"",
        // status:"",
        //信息需求反显数组
        //需要填入数据的部分
        ruleForm:{
          workOrderNo:"",
          businessProcess:"",
          remark:"",
          customerFeedback:"",
          closeType:"",
          costsIncurred:""
        },
        // 表单校验
        rules: {
          Service: [
            {required: true, message: "服务项目不能为空", trigger: "blur"}
          ],
          priority: [
            {required: true, message: "优先级不能为空", trigger: "blur"}
          ],
          lxperson: [
            {required: true, message: "联系人不能为空", trigger: "blur"}
          ],
          orderNum: [
            {required: true, message: "联系人与被保人关系不能为空", trigger: "blur"}
          ],
          orderNum: [
            {required: true, message: "联系人移动电话不能为空", trigger: "blur"}
          ],

        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        //新增的数据传输
        sendForm: {
          workOrderNo:'',
          businessProcess:'',
        },
        //submnit提交
        submitForm:{
          sign:"",
          workOrderNo:"",
          customerFeedback:"",
          times:"",
          businessProcess:"",
          remark:"",
          costIncurred:"",

        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          workOrderNo:"",
          policyNo:"",
          policyItemNo:"",
          status:"",
          pageNum: 1,
          pageSize: 10
        },
        loading: true,
        //数据反显用
        workPoolData:"",
        //HCS服务预约预修改记录
        HCSPoolData:[],
        HCSTotal:0,
        //业务处理信息
        dealInfo:[],

        isinit: 'Y',
        totalCount: 0,
        changeSerchData: {},
        states: [],
        serves: [{
          value: '1',
          label: '服务1'
        }, {
          value: '2',
          label: '服务2'
        }, {
          value: '3',
          label: '服务3'
        }, {
          value: '4',
          label: '服务4'
        }],
        sysUserOptions: [],
      }
    },
    created() {
        this.queryParams.workOrderNo=this.$route.query.workOrderNo;
        this.queryParams.policyNo=this.$route.query.policyNo;
        this.queryParams.policyItemNo=this.$route.query.policyItemNo;
        this.queryParams.status=this.$route.query.status;
      //window.aaa = this;
      this.searchHandle()
      this.searchFlowLog()
      this.searchHCS()
      // this.getDicts("sys_oper_type").then(response => {
      //   this.states = response.data;
      //   console.log("response:",response)
      // });

    },

    methods: {
      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //新增按钮
      submit(){
        const send=this.submitForm
        send.sign="02",
        send.workOrderNo=this.queryParams.workOrderNo=this.$route.query.workOrderNo
        dealReservationSubmit(send).then(res => {
          console.log("tijiao",send)
          if (res != null && res.code === 200) {
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "提交失败！"
              )
            }
          }
        }).catch(res => {

        })

      },
      temporary(){
        const send=this.submitForm
        send.sign="01",
        send.workOrderNo=this.queryParams.workOrderNo=this.$route.query.workOrderNo
        dealReservationSubmit(send).then(res => {
          console.log("zancun",send)
          if (res != null && res.code === 200) {
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "提交失败！"
              )
            }
          }
        }).catch(res => {

        })

      },
      //取消
      //修改按钮
      modify() {
        if (this.ids.length==0){
          alert("先选中一行")
        }else {if(this.ids.length>2){
          alert("选中一行")
        }else {
          this.$router.push({
            path: '/customService/reservation/modify',
            query: {
              workOrderNo: this.queryParams.workOrderNo,
              policyNo: this.queryParams.policyNo,
              policyItemNo: this.queryParams.policyItemNo,
              status: this.queryParams.status
            }
          })
        }
        }
      },
      //取消按钮
      cancle() {
        this.$router.push({
          path: '/customService/reservation/cancle',
          query:{
            workOrderNo:this.queryParams.workOrderNo,
            policyNo:this.queryParams.policyNo,
            policyItemNo:this.queryParams.policyItemNo,
            status:this.queryParams.status
          }
        })
      },
      //反显信息需求
      searchHandle() {
        let query=this.queryParams
        console.log("query",query)
        demandListAndPersonalPool(query).then(res => {
          console.log('共公池',res.rows)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows[0]
            this.totalCount = res.total
            console.log('response',res.total)
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //上传附件
      upload(){ this.$refs.upload.open();},
      //下载
      download(){},
      //提交页面数据
      submit(){},
      //转办
      transfer(){ this.$refs.transfer.open();},
      //协办
      coOrganizer(){ this.$refs.coOrganizer.open();},
      resetForm() {
        this.$refs.sendForm.resetFields()
      },

      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          console.log(workOrderNo)
          console.log('轨迹表',res.rows)
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            this.flowLogCount=res.total
            console.log("searchFlowLog",this.flowLogData)
            this.flowLogCount = res.total
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //查询HCS
      searchHCS() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        HMSSearch(workOrderNo).then(res => {
          console.log(workOrderNo)
          console.log('HCS',res.rows)
          if (res != null && res.code === 200) {
            this.HCSPoolData = res.rows
            this.HCSTotal = res.total
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },

    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
