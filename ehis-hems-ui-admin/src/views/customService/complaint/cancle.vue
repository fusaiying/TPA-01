<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <span style="color: blue">客户基本信息</span>
      <el-divider/>
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="130px"
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
      <el-form ref="ruleForm" :model="ruleForm"  style="padding-bottom: 30px;" label-width="160px"
               label-position="right" size="mini">

        <span style="color: blue">基因改善-服务受理信息</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-input v-model="workPoolData.channelCode" class="item-width"  size="mini" readonly/>
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
              <el-input v-model="workPoolData.contactsPerson.sex" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人地址：" prop="address">
              <el-input v-model="workPoolData.contactsPerson.address" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="phone">
              <el-input v-model="workPoolData.insuredNo" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-input v-model="workPoolData.priorityLevel" class="item-width"  size="mini" readonly/>
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
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
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
              <el-input v-model="workPoolData.complainantPerson.sex" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：" prop="phone">
              <el-input v-model="workPoolData.complainantPerson.identity" class="item-width"  size="mini" readonly/>
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
              <el-input v-model="workPoolData.organCode" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>  <el-form-item label="是否已劝解：" prop="persuasionFlag">
          <el-input v-model="workPoolData.persuasionFlag" class="item-width"  size="mini" readonly/>
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
        <span style="color:blue;">附件信息</span>
        <el-divider></el-divider>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="flowLogData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" width="140" prop="status" label="附件名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="operateCode" label="附件类型" show-overflow-tooltip/>
          <el-table-column align="center" prop="makeBy" label="上传人" show-overflow-tooltip/>
          <el-table-column prop="makeTime" label="上传时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.makeTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" align="center" label="备注" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
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
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="二级投诉分类：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉是否成立：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="一级投诉原因：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉不成立理由：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="二级投诉原因：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->


<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="三级投诉原因：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉环节(报保监)：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉问题(报保监)：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->


<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="行协调解或外部鉴定状态：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="险种类型：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="营销渠道：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="是否记件：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="撤诉状态：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="投诉业务类别：：" prop="priority"  >-->
<!--              <el-select v-model="workPoolData.contactsLanguage" class="item-width" disabled>-->
<!--                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"-->
<!--                           :value="item.dictValue"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->


<!--        </el-row>-->

<!--        <el-row>-->
<!--          <el-form-item label="投诉根因部门：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" readonly/>-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="质诉根因：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" readonly/>-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-form-item label="处理结果：" prop="policyNo" >-->
<!--            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" readonly/>-->
<!--          </el-form-item>-->
<!--        </el-row>-->
<!--      </el-form>-->
<!--    </el-card>-->



    <el-card>
      <el-form :model="ruleForm"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue" >取消处理</span>
        <el-divider style="color: blue" ></el-divider>
        <el-row>
          <el-form-item label="取消原因：" >
            <el-radio-group v-model="ruleForm.editReason">
              <el-radio   label="1">客户申请变动</el-radio>
              <el-radio   label="2">操作失误</el-radio>
              <el-radio   label="3">其他原因</el-radio>
            </el-radio-group>
          </el-form-item>

        </el-row>
        <el-row>
          <el-form-item label="取消说明：">
            <el-input
              type="textarea"
              :rows="2"
              v-model="ruleForm.editRemark">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <el-row :gutter="20" align="right">
        <div style="text-align: right; margin-right: 1px;">
        <el-button type="primary"  size="mini" @click="submit()">提交</el-button>
        <el-button type="primary"  size="mini" @click="hiddenShow()">关闭</el-button>
         </div>
      </el-row>


    </el-card>





  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,FlowLogSearch,cancelSubmit} from '@/api/customService/demand'
  import {complaintCancelSubmit} from '@/api/customService/complaint'
  import {complainSearch,comSearch}  from  '@/api/customService/consultation'

  let dictss = [{dictType: 'product_status'}]
  export default {
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
        //传过来的工单号
        workOrderNo:"",
        //需要填入数据的部分
        ruleForm:{
          cancelReason:"",
          editRemark:"",
          workOrderNo:"",

        },
        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          channle:"",
          textarea:"",
          service: "",
          channel: "",
          acceptor: "",
          acceptorTime:"",
          handler: "",
          handlerTime: "",
          workNumber: "",
          policyNumber: "",
          secondNumber: "",
          insuredName: "",
          beInsuredName: "",
          beInsuredNo: "",
          organization: "",
          appointmentTime:"",
          priority:"",
          vip:"",
          phone:"",
          state:""
        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
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
        workPoolData: {
          contactsPerson:{
            homePhone1:[]
          },
          callPerson: {},
          complainantPerson:{},
          insurer:{},
        }

        ,
        isinit: 'Y',
        totalCount: 0,
        changeSerchData: {},
        states: [],
        serves: [{
          dictValue: '1',
          dictLabel: '服务1'
        }, {
          dictValue: '2',
          dictLabel: '服务2'
        }, {
          dictValue: '3',
          dictLabel: '服务3'
        }, {
          dictValue: '4',
          dictLabel: '服务4'
        }],
        sysUserOptions: [],
      }
    },
    created() {
      this.queryParams.workOrderNo=this.$route.query.workOrderNo;
      this.queryParams.policyNo=this.$route.query.policyNo;
      this.queryParams.policyItemNo=this.$route.query.policyItemNo;
      this.queryParams.status=this.$route.query.status;
      this.searchHandle()
      this.searchFlowLog()
      this.getDicts("sys_oper_type").then(response => {
        this.states = response.data;
      });

    },

    methods: {
      //上传附件
      upload(){},
      //下载
      download(){},
      //提交页面数据
      submit(){
        let insert=this.ruleForm
        insert.workOrderNo=this.$route.query.workOrderNo
        complaintCancelSubmit(insert).then(res => {
          if (res != null && res.code === 200) {
            console.log("insert",insert)
            alert("取消成功")
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "失败！"
              )
            }
          }
        }).catch(res => {

        })
      },
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      //反显信息需求
      searchHandle() {
        let workOrderNo=this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.workPoolData = res.data
            console.log("投诉取消页面数据",this.workPoolData)

            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
            this.flowLogCount=res.total
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
      //关闭按钮
      hiddenShow:function () {
        // 返回上级路由并关闭当前路由
        this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
        this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length - 1].path)

      },


    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
