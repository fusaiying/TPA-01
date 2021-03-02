<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="120px"
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
      <el-form ref="ruleForm" :model="ruleForm"  style="padding-bottom: 30px;" label-width="150px"
               label-position="right" size="mini">
        <span style="color: blue">基因改善-服务受理信息</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-select v-model="workPoolData.channelCode" class="item-width" disabled>
                <el-option v-for="item in cs_channel" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="callCenterId">
              <el-input v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="sex" >
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width" disabled>
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人地址：" prop="address">
              <el-input v-model="workPoolData.contactsPerson.address" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="insuredNo">
              <el-input v-model="workPoolData.insuredNo" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width" disabled>
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="priority"  >
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width" disabled>
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人姓名：" prop="phone">
              <el-input v-model="workPoolData.complainantPerson.name" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉人性别：" prop="phone">
              <el-select v-model="workPoolData.complainantPerson.sex" class="item-width" disabled>
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：" prop="phone">
              <el-select v-model="workPoolData.complainantPerson.identity" class="item-width" disabled>
                <el-option v-for="item in cs_identity" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户电话：" prop="phone">
              <el-input v-model="workPoolData.insurer.mobilePhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="办公电话：" prop="phone">
              <el-input v-model="workPoolData.insurer.workPhone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width" disabled>
                <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>  <el-form-item label="是否已劝解：" prop="persuasionFlag">
          <el-select v-model="workPoolData.persuasionFlag" class="item-width" disabled>
            <el-option v-for="item in cs_whether_flag" :key="item.dictValue" :label="item.dictLabel"
                       :value="item.dictValue"/>
          </el-select>
        </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="投诉内容：" prop="phone">
            <el-input
              disabled
              type="textarea"
              :rows="2"
              v-model="workPoolData.content">
            </el-input>
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

<!--    <el-card>-->
<!--      <el-form   style="padding-bottom: 30px;" label-width="145px"-->
<!--                 label-position="right" size="mini">-->
<!--        <span style="color: blue">服务处理</span>-->
<!--        <el-divider/>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="一级投诉分类：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="二级投诉分类：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉是否成立：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="撤诉状态：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉是否成立：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉不成立理由：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->


<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="重复投诉：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="一级投诉原因：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="二级投诉原因：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="三级投诉原因：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉环节(报保监)：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉问题(报保监)：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="行协调解或外部鉴定状态：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="险种类型：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="营销渠道：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="投诉根因部门：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" />-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="质诉根因：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" />-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="处理结果：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full"/>-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="改善措施：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full"/>-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--      </el-form>-->


<!--    </el-card>-->



    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button  type="primary" style="float: right" size="mini" @click="upload" disabled>上传附件</el-button></span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="attachmentInfoData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" prop="attachmentName" label="附件名称" show-overflow-tooltip/>
          <el-table-column prop="attachmentType" align="center" label="附件类型" show-overflow-tooltip>
            <template slot-scope="scope" v-if="scope.row.attachmentType">
              <span>{{selectDictLabel(cs_attachment_type, scope.row.attachmentType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="uplodBy" align="center" label="上传人" show-overflow-tooltip/>
          <el-table-column prop="uploadTime" align="center" label="上传时间 " show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.uploadTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" align="center" label="备注" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,dealAdd,FlowLogSearch,HMSSearch,dealADD} from '@/api/customService/demand'
  import upLoad from "../common/modul/upload";
  import {complainSearch,comSearch}  from  '@/api/customService/consultation'
  import {getAttachmentListById} from "@/api/customService/spotCheck";


  let dictss = [
    {dictType: 'cs_channel'},
    {dictType: 'cs_priority'},
    {dictType: 'cs_sex'},
    {dictType: 'cs_communication_language'},
    {dictType: 'cs_identity'},
    {dictType: 'cs_whether_flag'},
    {dictType: 'cs_organization'},
    {dictType: 'cs_relation'},
    ]
  export default {
    components: {
      upLoad,

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
        ids:[],//多选框
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
          costsIncurred:"",
          sign:""
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
            {required: true, message: "联系人移动电话不能为空", trigger: "blur"}
          ],

        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        //新增的数据传输
        sendForm: {
          sign:"",//控制暂存还是提交用
          workOrderNo:'',
          businessProcess:'',
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
        workPoolData: {
          contactsPerson:{
            homePhone1:[]
          },
          callPerson: {},
          complainantPerson:{},
          insurer:{},
        }

        ,
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
        dictList: [],
        cs_channel: [],
        cs_priority: [],
        cs_sex: [],
        cs_communication_language: [],
        cs_identity: [],
        cs_whether_flag: [],
        cs_organization: [],
        cs_relation: [],
        attachmentInfoData: [],
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

      let query = {
        workOrderNo: this.params.workOrderNo,
        businessType: this.params.businessType,
      }
      //获取附件列表信息
      getAttachmentListById(query).then(res => {
        if (res != null && res.code === 200) {
          console.info(res.data);
          this.attachmentInfoData=res.data;
        }
      }).catch(res => {
      })
    },
    async mounted() {
      // 字典数据统一获取
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      // 下拉项赋值
      this.cs_channel = this.dictList.find(item => {
        return item.dictType === 'cs_channel'
      }).dictDate
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.cs_sex = this.dictList.find(item => {
        return item.dictType === 'cs_sex'
      }).dictDate
      this.cs_priority = this.dictList.find(item => {
        return item.dictType === 'cs_priority'
      }).dictDate
      this.cs_communication_language = this.dictList.find(item => {
        return item.dictType === 'cs_communication_language'
      }).dictDate
      this.cs_identity = this.dictList.find(item => {
        return item.dictType === 'cs_identity'
      }).dictDate
      this.cs_whether_flag = this.dictList.find(item => {
        return item.dictType === 'cs_whether_flag'
      }).dictDate
      this.cs_organization = this.dictList.find(item => {
        return item.dictType === 'cs_organization'
      }).dictDate
      this.cs_relation = this.dictList.find(item => {
        return item.dictType === 'cs_relation'
      }).dictDate
    },
    methods: {
      //新增按钮
      //取消
      deal(){},
      //反显信息需求
      searchHandle() {
        let workOrderNo=this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.workPoolData = res.data

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
      upload(){ this.$refs.upload.open()},
      //下载
      download(){},

      //超链接用
      modifyDetails(s){
        this.$refs.modifyDetails.queryParams.subId=s.subId,
          this.$refs.modifyDetails.queryParams.workOrderNo=this.queryParams.workOrderNo;
        this.$refs.modifyDetails.open()
        ;},
      //打开修改对话框



      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
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
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.workOrderNo);

      },


    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
