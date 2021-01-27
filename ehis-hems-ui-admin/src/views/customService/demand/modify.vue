<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">

        <span style="color: blue">服务项目-信息需求受理【修改】</span>
        <el-divider></el-divider>

        <el-row>
              <el-form-item label="受理渠道：" prop="bank" >
                <el-radio-group v-model="ruleForm.bank">
                  <el-radio   :label="1">电话</el-radio>
                  <el-radio   :label="2">邮箱</el-radio>
                  <el-radio   :label="3">网站</el-radio>
                  <el-radio   :label="4">电话中心</el-radio>
                  <el-radio   :label="5">柜面</el-radio>
                  <el-radio   :label="6">医网</el-radio>
                  <el-radio   :label="7">平安内网</el-radio>
                  <el-radio   :label="8">APP</el-radio>
                  <el-radio   :label="9">寿险</el-radio>
                  <el-radio   :label="10">微信</el-radio>
                </el-radio-group>
              </el-form-item>
          </el-row>
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
            <el-form-item label="联系人语言：" prop="priority"  >
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
        <span style="color:blue;">业务流转情况</span>
        <el-divider></el-divider>
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


    <el-card>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
        <el-divider></el-divider>
        <el-row>
          <el-col :span="8">
            <el-form-item label="业务处理情况" prop="bank" >
              <el-radio-group v-model="ruleForm.bank">
                <el-radio   :label="1">成功</el-radio>
                <el-radio   :label="2">失败</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="处理说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="ruleForm.textarea">
            </el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="客户反馈" prop="bank" >
            <el-radio-group v-model="ruleForm.bank">
              <el-radio   :label="1">满意</el-radio>
              <el-radio   :label="2">接受</el-radio>
              <el-radio   :label="3">不接受</el-radio>
            </el-radio-group>
          </el-form-item>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="结案类型：" prop="Service">
              <el-select v-model="ruleForm.service" class="item-width" placeholder="请选择" controls-position="right" :min="0">
                <el-option v-for="item in serves" :key="item.value" :label="item.label"
                           :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="安抚或通融发生费用成本：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="不超过500字符："
              v-model="ruleForm.textarea">
            </el-input>
          </el-form-item>
        </el-row>

      </el-form>


    </el-card>

    <el-card>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue">修改原因</span>
        <el-divider></el-divider>
        <el-row>
            <el-form-item label="修改原因" prop="bank" >
              <el-radio-group v-model="ruleForm.bank">
                <el-radio   :label="1">客户申请变动</el-radio>
                <el-radio   :label="2">操作失误</el-radio>
                <el-radio   :label="3">其他原因</el-radio>
              </el-radio-group>
            </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="ruleForm.textarea">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>

      <el-button  type="primary" style="float: right" size="mini" @click="upload">保存</el-button>
      <el-button  type="primary" style="float: right" size="mini" @click="upload">关闭</el-button>
    </el-card>





  </div>
</template>

<script>
  import moment from 'moment'
  import {demandListAndPublicPool,demandListAndPersonalPool} from '@/api/customService/demand'

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
        //需要填入数据的部分
        ruleForm:{
          radio:"",
          service:"",
          phone:"",
          priority:"",
          lxperson:"",
          bankaa: "",
          bankbb: "",
          bankcc: "",
          bankdd: "",
          bank:"2",
          textarea:"",
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
          // email: [
          //   {
          //     type: "email",
          //     message: "'请输入正确的邮箱地址",
          //     trigger: ["blur", "change"]
          //   }
          // ],
          // phone: [
          //   {
          //     pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
          //     message: "请输入正确的手机号码",
          //     trigger: "blur"
          //   }
          // ]
        },

        readonly: true,
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          channle:"",
          textarea:"",
          service: "1",
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
      debugger;
      window.aaa = this;
      this.searchHandle()
      this.getDicts("sys_oper_type").then(response => {
        this.states = response.data;
        console.log("response:",response)
      });

    },

    methods: {
      //上传附件
      upload(){},
      //下载
      download(){},
      //提交页面数据
      submit(){},
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      searchHandle() {
        debugger;
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
