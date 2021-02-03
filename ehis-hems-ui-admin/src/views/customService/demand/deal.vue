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
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">

        <span style="color: blue">信息需求-理赔类</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="phoneNumber">
              <el-input v-model="ruleForm.phone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电号码：" prop="phone">
              <el-input v-model="ruleForm.phone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="phone">
              <el-input v-model="ruleForm.phone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="ruleForm.priority" class="item-width" disabled="true" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人姓名：" prop="phone">
              <el-input v-model="ruleForm.phone" class="item-width" readonly size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人与被保人关系：" prop="priority" >
              <el-select v-model="ruleForm.priority" class="item-width" disabled="true">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人：" prop="lxperson">
              <el-input v-model="ruleForm.lxperson" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="priority" >
              <el-select v-model="ruleForm.priority" class="item-width" disabled="true">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人与被保人关系：" prop="priority" >
              <el-select v-model="ruleForm.priority" class="item-width" disabled="true">
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="priority" disabled="true" >
              <el-select v-model="ruleForm.priority" class="item-width" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系人移动电话：" prop="phone">
              <el-input v-model="ruleForm.phone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="phone">
              <el-input v-model="ruleForm.phone" class="item-width"  size="mini" readonly/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row >
          <el-col :span="5">
            <el-form-item label="联系人固定电话："  style="white-space: nowrap" prop="phone">
              国家区号:+<el-input v-model="ruleForm.areacode" class="item-width" readonly style="width: 75px"/>
              区号<el-input v-model="ruleForm.phone" class="item-width" readonly size="mini" style="width: 145px" maxlength="50"/>
              号码<el-input v-model="ruleForm.phone" class="item-width" readonly size="mini" style="width: 145px" maxlength="50"/>
              分机号<el-input v-model="ruleForm.phone" class="item-width" readonly size="mini" style="width: 145px" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="所在地："  prop="phone">
            <el-input v-model="ruleForm.phone" class="width-full"  size="mini" readonly/>
          </el-form-item>

        </el-row>
        <el-row>

          <el-col :span="8">
            <el-form-item label="出单机构：" prop="priority">
              <el-select v-model="ruleForm.priority" class="item-width" placeholder="请选择" disabled>
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否涉及银行转账" prop="bank" >
              <el-radio-group v-model="ruleForm.bank" disabled>
                <el-radio   :label="1">是</el-radio>
                <el-radio   :label="2">否</el-radio>

              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="开户行：" v-show="ruleForm.bank=='1'" prop="bankaa">
              <el-input size="mini" v-model="ruleForm.bankaa" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开户地：" v-show="ruleForm.bank=='1'" prop="bankbb">
              <el-input size="mini" v-model="ruleForm.bankbb" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="账号：" v-show="ruleForm.bank=='1'" prop="bankcc">
              <el-input size="mini" v-model="ruleForm.bankcc" readonly></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="户名：" v-show="ruleForm.bank=='1'" prop="bankdd">
              <el-input size="mini" v-model="ruleForm.bankdd" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="业务内容：" prop="textarea">
          <el-input
            type="textarea"
            :rows="2"
            readonly
            v-model="ruleForm.textarea">
          </el-input>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">业务流转情况</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;">
          <el-table-column align="center" width="140" prop="workNumber" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" prop="channel" label="操作" show-overflow-tooltip/>
          <el-table-column align="center" prop="Service" label="受/处理人" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyNumber" label="UM账号" show-overflow-tooltip/>
          <el-table-column prop="acceptorTime" label="时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="Acceptor" align="center" label="说明" show-overflow-tooltip/>
          <el-table-column prop="Handler" align="center" label="处理意见" show-overflow-tooltip/>
          <el-table-column prop="vip" align="center" label="流转部门" show-overflow-tooltip/>
          <el-table-column prop="priority" align="center" label="流传原因" show-overflow-tooltip/>
        </el-table>

        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">HCS服务预约预修改记录</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column prop="acceptorTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="Acceptor" align="center" label="修改序列号" show-overflow-tooltip/>
          <el-table-column prop="Handler" align="center" label="预约渠道" show-overflow-tooltip/>
          <el-table-column prop="vip" align="center"  style="width: available" label="修改内容描述" show-overflow-tooltip/>
        </el-table>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>

    <el-card>
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
                 label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
          <div style="text-align: right; margin-right: 8px;">
            <el-button type="primary" size="mini" @click="add">新增</el-button>
            <el-button type="primary" size="mini" @click="obtainButton">获取</el-button>
          </div>
          <el-divider/>
       <el-row>
        <el-col :span="8">
        <el-form-item label="业务处理情况" prop="businessProcess" >
          <el-radio-group v-model="ruleForm.businessProcess">
            <el-radio   :label="1">成功</el-radio>
            <el-radio   :label="2">响应</el-radio>
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
            v-model="ruleForm.remark">
          </el-input>
        </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="客户反馈" prop="customerFeedback" >
              <el-radio-group v-model="ruleForm.customerFeedback">
                <el-radio   :label="1">满意</el-radio>
                <el-radio   :label="2">接受</el-radio>
                <el-radio   :label="3">不接受</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="结案类型：" prop="closeType">
                <el-select v-model="ruleForm.closeType" class="item-width" placeholder="请选择" controls-position="right" :min="0">
                  <el-option v-for="item in serves" :key="item.value" :label="item.label"
                             :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="安抚或通融发生费用成本：" prop="costsIncurred">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="不超过500字符："
                v-model="ruleForm.costsIncurred">
              </el-input>
            </el-form-item>
          </el-row>

        </el-form>


    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">附件信息<el-button  type="primary" style="float: right" size="mini" @click="upload">上传附件</el-button></span>
       <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
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
              <el-button size="mini" type="text" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: right; margin-right: 1px;">
        <transfer ref="transfer"></transfer>
        <up-load ref="upload"></up-load>
        <co-organizer ref="coOrganizer"></co-organizer>
        <el-button  type="primary"  size="mini" @click="transfer">转办</el-button>
        <el-button  type="primary" size="mini" @click="coOrganizer">协办</el-button>
        <el-button  type="primary"  size="mini" @click="upload">保单信息查询</el-button>
        <el-button  type="primary" size="mini" @click="upload">暂存</el-button>
        <el-button type="primary" size="mini" @click="upload">提交</el-button>
      </div>
    </el-card>



  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool,dealAdd} from '@/api/customService/demand'
  import transfer from "../common/modul/transfer";
  import upLoad from "../common/modul/upload";
  import coOrganizer from "../common/modul/coOrganizer";

  let dictss = [{dictType: 'product_status'}]
  export default {
    components: { transfer ,
                  upLoad,
                  coOrganizer,
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
        //传过来的工单号
        workOrderNo:"",
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
          orderNum: [
            {required: true, message: "出单机构不能为空", trigger: "blur"}
          ],
          bankaa: [
            {required: true, message: "开户行不能为空", trigger: "blur"}
          ],
          bankbb: [
            {required: true, message: "开户地不能为空", trigger: "blur"}
          ],
          bankcc: [
            {required: true, message: "账号不能为空", trigger: "blur"}
          ],
          bankdd: [
            {required: true, message: "户名不能为空", trigger: "blur"}
          ],
          textarea: [
            {required: true, message: "业务内容不能为空", trigger: "blur"}
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
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10


        },
        loading: true,
        workPoolData: [],
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
      this.workOrderNo=this.$route.query.workOrderNo;
      debugger;
      window.aaa = this;
      this.searchHandle()
      this.getDicts("sys_oper_type").then(response => {
        this.states = response.data;
        console.log("response:",response)
      });

    },

    methods: {
      //新增按钮

      add(){
        let addQueryParams=this.ruleForm
        addQueryParams.workOrderNo=this.workOrderNo
        console.log("sdas",this.workOrderNo)
        dealAdd(addQueryParams).then(res => {
          console.log("增加",addQueryParams)
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
      searchHandle() {
        let query = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          Service: this.sendForm.Service,
          channel: this.sendForm.channel,
          Acceptor: this.sendForm.Acceptor,
          acceptorTime: this.sendForm.acceptorTime,
          Handler: this.sendForm.Handler,
          HandlerTime: this.sendForm.HandlerTime,
          workNumber: this.sendForm.workNumber,
          policyNumber: this.sendForm.policyNumber,
          secondNumber: this.sendForm.secondNumber,
          insuredName: this.sendForm.insuredName,
          beInsuredName: this.sendForm.beInsuredName,
          beInsuredNo: this.sendForm.beInsuredName,
          organization: this.sendForm.organization,
          appointmentTime:this.sendForm.appointmentTime,
          priority:this.sendForm.priority,
          vip:this.sendForm.vip,
          phone:this.sendForm.phone,
          state:this.sendForm.state
        }
        debugger;
        console.log('query: ',query)
        demandListAndPublicPool(query).then(res => {
          console.log('------------: ',res)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
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
      // getRiskStatus(row) {
      //   return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      // }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
