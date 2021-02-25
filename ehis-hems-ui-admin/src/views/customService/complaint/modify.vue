<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="workPoolData" :model="workPoolData" :rules="rules" style="padding-bottom: 30px;" label-width="160px"
               label-position="right" size="mini">

        <span style="color: blue">服务项目-信息需求受理【修改】</span>
        <el-divider></el-divider>

        <el-row>
              <el-form-item label="受理渠道：" prop="channelCode" >
                <el-radio-group v-model="workPoolData.channelCode">
                  <el-radio   label="01">电话</el-radio>
                  <el-radio   label="02">邮箱</el-radio>
                  <el-radio   label="03">网站</el-radio>
                  <el-radio   label="04">电话中心</el-radio>
                  <el-radio   label="05">柜面</el-radio>
                  <el-radio   label="06">医网</el-radio>
                  <el-radio   label="07">平安内网</el-radio>
                  <el-radio   label="08">APP</el-radio>
                  <el-radio   label="09">寿险</el-radio>
                  <el-radio   label="10">微信</el-radio>
                </el-radio-group>
              </el-form-item>
          </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电话中心业务流水号：" prop="callCenterId">
              <el-input v-model="workPoolData.callCenterId" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人姓名：" prop="contactsPerson.name">
              <el-input  v-model="workPoolData.contactsPerson.name" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人性别：" prop="contactsPerson.sex">
              <el-select v-model="workPoolData.contactsPerson.sex" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priorityLevel">
              <el-select v-model="workPoolData.priorityLevel" class="item-width"  >
                <el-option v-for="item in cs_priority" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来电人号码：" prop="callPerson.mobilePhone">
              <el-input v-model="workPoolData.callPerson.mobilePhone" class="item-width"  size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="E-MAIL：" prop="email">
              <el-input v-model="workPoolData.email" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人手机：" prop="contactsPerson.mobilePhone">
              <el-input v-model="workPoolData.contactsPerson.mobilePhone" class="item-width"  size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人地址：" prop="contactsPerson.address"  >
              <el-select v-model="workPoolData.contactsPerson.address" class="item-width" >
                <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">

            <el-form-item label="预约时间："  style="white-space: nowrap">
              <el-date-picker
                v-model="workPoolData.complaintTime"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row >
          <el-col :span="8">
            <el-form-item label="投诉人姓名：" prop="complainantPerson.mobilePhone">
              <el-input v-model="workPoolData.complainantPerson.mobilePhone" class="item-width"  size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人性别：" prop="complainantPerson.sex">
              <el-select v-model="workPoolData.complainantPerson.sex" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉人身份：" prop="complainantPerson.identity">
              <el-select v-model="workPoolData.complainantPerson.identity" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.identity" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-col :span="8">
          <el-form-item label="家庭电话：" prop="contactsPerson.homePhone">
            <el-input v-model="workPoolData.contactsPerson.homePhone" class="item-width"  size="mini"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="办公电话：" prop="contactsPerson.workPhone">
            <el-input v-model="workPoolData.contactsPerson.workPhone" class="item-width"  size="mini"/>
          </el-form-item>
        </el-col>
        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人语言：" prop="contactsPerson.language">
              <el-select v-model="workPoolData.contactsPerson.language" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organCode">
              <el-select v-model="workPoolData.organCode" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否已劝解：" prop="contactsSex">
              <el-select v-model="workPoolData.contactsPerson.persuasionFlag" class="item-width"  >
                <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="投诉内容：" prop="content">
          <el-input
            type="textarea"
            :rows="2"
            v-model="workPoolData.content">
          </el-input>
        </el-form-item>
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

    <el-card>
      <el-form   style="padding-bottom: 30px;" label-width="145px"
               label-position="right" size="mini">
        <span style="color: blue">服务处理</span>
        <el-divider/>
        <el-row>
          <el-col :span="8">
            <el-form-item label="一级投诉分类：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉分类：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="三级投诉分类：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="撤诉状态：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉是否成立：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉不成立理由：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>


        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="重复投诉：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="一级投诉原因：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二级投诉原因：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="是否计件：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉环节(报保监)：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投诉问题(报保监)：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>


        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="行协调解或外部鉴定状态：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="险种类型：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="营销渠道：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>


        </el-row>
        <el-row>
          <el-col :span="8">
          <el-form-item label="投诉业务类别：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="item-width"/>
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户反馈：" prop="priority"  >
              <el-select v-model="workPoolData.contactsLanguage" class="item-width" >
                <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="跟因改善：" prop="policyNo">
              <el-input v-model="workPoolData.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉损失：" prop="policyNo">
              <el-input v-model="workPoolData.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="投诉根因部门：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" />
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="质诉根因：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full" />
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="处理结果：" prop="policyNo" >
            <el-input v-model="workPoolData.policyNo"  clearable size="mini" class="width-full"/>
          </el-form-item>
        </el-row>
      </el-form>


    </el-card>

    <el-card>
      <el-form  :model="workPoolData"  style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <span style="color: blue">修改原因</span>
        <el-divider></el-divider>
        <el-row>
            <el-form-item label="修改原因"  >
              <el-radio-group v-model="workPoolData.editInfo.editReason">
                <el-radio label="1">客户申请变动</el-radio>
                <el-radio label="2">操作失误</el-radio>
                <el-radio label="3">其他原因</el-radio>
              </el-radio-group>
            </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" >
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="workPoolData.editInfo.editRemark">
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div style="text-align: right; margin-right: 1px;">
      <el-button  type="primary" size="mini" @click="submit">保存</el-button>
      <el-button  type="primary"size="mini" @click="hiddenShow">关闭</el-button>
      </div>
    </el-card>





  </div>
</template>

<script>
  import moment from 'moment'
  import {FlowLogSearch,modifySubmit} from '@/api/customService/demand'
  import upLoad from "../common/modul/upload";
  import {complainSearch,comSearch}  from  '@/api/customService/consultation'


  let dictss = [{dictType: 'product_status'}]
  export default {
    components: { upLoad },
    filters: {
      changeDate: function (value) {
        if (value !== null) {
          return moment(value).format('YYYY-MM-DD')
        }
      }
    },
    data() {

      return {
        //下拉框
        cs_priority:[],//优先级
        cs_service_item:[],//服务项目
        cs_channel:[],//渠道
        cs_organization:[],//出单机构
        cs_sex:[],//性别
        cs_communication_language:[],//语言
        //流转用
        flowLogData:[],
        flowLogCount: 0,
        //回显服务项目
        //需要填入数据的部分
        workPoolData: {
          contactsPerson:{
            homePhone1:[]
          },
          callPerson: {},
          complainantPerson:{},
          insurer:{},
          editInfo:{
            editReason:"",
            editRemark:""
          }
        },

        totalCount: 0,
        // 表单校验
        rules: {
          channelCode: [
            {required: true, message: "受理渠道不能为空", trigger: "blur"}
          ],
          priorityLevel: [
            {required: true, message: "优先级不能为空", trigger: "blur"}
          ],
          contactsName: [
            {required: true, message: "联系人不能为空", trigger: "blur"}
          ],
          contactsMobilePhone: [
            {required: true, message: "联系人手机不能为空", trigger: "blur"}
          ],
          organCode: [
            {required: true, message: "出单机构不能为空", trigger: "blur"}
          ],
          bankName: [
            {required: true, message: "开户行不能为空", trigger: "blur"}
          ],
          bankLocation: [
            {required: true, message: "开户地不能为空", trigger: "blur"}
          ],
          accountNumber: [
            {required: true, message: "账号不能为空", trigger: "blur"}
          ],
          bankHolder: [
            {required: true, message: "户名不能为空", trigger: "blur"}
          ],
          content: [
            {required: true, message: "业务内容不能为空", trigger: "blur"}
          ],
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          workOrderNo:"",
          policyNo:"",
          policyItemNo:"",
          status:""
        },
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
      this.getDicts("cs_sex").then(response => {
        this.cs_sex = response.data;
      });
      this.getDicts("cs_communication_language").then(response => {
        this.cs_communication_language = response.data;
      });
      this.getDicts("cs_service_item").then(response => {
        this.cs_service_item = response.data;
      });
      this.getDicts("cs_organization").then(response => {
        this.cs_organization = response.data;
      });
      this.getDicts("cs_priority").then(response => {
        this.cs_priority = response.data;
      });
      this.searchHandle()
      this.searchFlowLog()
    },

    methods: {
      //提交页面数据
      submit(){
        let insert=this.workPoolData
        insert.workOrderNo=this.$route.query.workOrderNo
        modifySubmit(insert).then(res => {
          if (res != null && res.code === 200) {
            alert("修改成功")
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "失败！"
              )
            }
          }
        }).catch(res => {

        })

      },
      //关闭页面
      close(){

      },
      //反显信息需求
      //反显信息需求
      searchHandle() {
        let workOrderNo=this.queryParams.workOrderNo
        complainSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
              const workPoolData=res.data
            let editInfo = {
              editReason: "",
              editRemark: ""
            }
              workPoolData.editInfo=editInfo
              this.workPoolData=workPoolData
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      //查询轨迹表
      searchFlowLog() {
        let workOrderNo=this.queryParams
        workOrderNo.status=""
        FlowLogSearch(workOrderNo).then(res => {
          if (res != null && res.code === 200) {
            this.flowLogData = res.rows
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
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
        //提交页面数据
        submit(){
          let insert=this.workPoolData
          modifySubmit(insert).then(res => {
            if (res != null && res.code === 200) {
              alert("修改成功")
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "失败！"
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
      //上传附件
      upload(){
        this.$refs.upload.open();
      },



    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
