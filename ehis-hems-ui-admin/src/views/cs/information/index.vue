<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="服务项目：" prop="Service">
              <el-select v-model="sendForm.Service" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理渠道：" prop="channel">
              <el-select v-model="sendForm.channel" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理人：" prop="Acceptor">
              <el-input v-model="sendForm.Acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptorTime">
              <el-date-picker
                v-model="sendForm.acceptorTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理人：" prop="Handler">
              <el-input v-model="sendForm.Handler" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理日期：" prop="HandlerTime">
              <el-date-picker
                v-model="sendForm.HandlerTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工单号：" prop="workNumber">
              <el-input v-model="sendForm.workNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNumber">
              <el-input v-model="sendForm.policyNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="secondNumber">
              <el-input v-model="sendForm.secondNumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="投保人姓名：" prop="insuredName">
              <el-input v-model="sendForm.insuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="beInsuredName">
              <el-input v-model="sendForm.beInsuredName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件号：" prop="beInsuredNo">
              <el-input v-model="sendForm.beInsuredNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人电话：" prop="phone">
              <el-input v-model="sendForm.phone" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出单机构：" prop="organization">
              <el-select v-model="sendForm.organization" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约日期：" prop="appointmentTime">
              <el-date-picker
                v-model="sendForm.appointmentTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优先级：" prop="priority">
              <el-select v-model="sendForm.Service" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="vip标识：" prop="vip">
              <el-select v-model="sendForm.Service" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>  <el-col :span="8">
          <el-form-item label="状态：" prop="state">
            <el-select v-model="sendForm.Service" class="item-width" placeholder="请选择">
              <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>


        </el-row>
        <div style="text-align: right; margin-right: 1px;">
          <el-button size="mini" type="primary" @click="searchHandle">查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>待处理（{{ totalCount }}）</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="sendMany">新增</el-button>
           <el-button type="primary" size="mini" @click="sendMany">获取</el-button>
        </span>
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
          <el-table-column align="center" width="140" prop="riskCode" label="工单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskName" label="受理渠道" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskNature" label="服务项目" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskClass" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskStatus" label="分单号" show-overflow-tooltip/>
          <el-table-column prop="conclusionExplanation" align="center" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="受理时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>

          <el-table-column prop="updateBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="VIP标识" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="优先级" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="sendOne(scope.row)">获取</el-button>
              <el-button size="mini" type="text" @click="sendOne(scope.row)">修改</el-button>
              <el-button size="mini" type="text" @click="sendOne(scope.row)">取消</el-button>

            </template>
          </el-table-column>
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
        <span>处理中（{{ totalCount }}）</span>
        <el-divider/>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
<!--          <el-table-column type="selection" align="center" name/> sd-->
          <el-table-column align="center" width="140" prop="riskCode" label="工单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskName" label="受理渠道" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskNature" label="服务项目" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskClass" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" :formatter="getRiskStatus" prop="riskStatus" label="分单号"
                           show-overflow-tooltip/>
          <el-table-column prop="conclusionExplanation" align="center" label="险种代码" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="被保人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="投保人" show-overflow-tooltip/>
          <el-table-column prop="updateTime" label="受理时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="修改时间" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>

          <el-table-column prop="updateBy" align="center" label="受理人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="处理人" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="VIP标识" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="优先级" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="出单机构" show-overflow-tooltip/>
          <el-table-column prop="updateBy" align="center" label="状态" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="sendOne(scope.row)">获取</el-button>
              <el-button size="mini" type="text" @click="sendOne(scope.row)">修改</el-button>
              <el-button size="mini" type="text" @click="sendOne(scope.row)">取消</el-button>

            </template>
          </el-table-column>
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

<!--    <el-dialog title="任务改派" :visible.sync="dialogFormVisible" width="30%" hight="60%">-->
<!--      <el-form ref="sendForm" style="padding-bottom: 30px;" label-width="100px"-->
<!--               label-position="right" size="mini">-->
<!--        <el-row>-->

<!--          <el-col :span="10">-->
<!--            <el-form-item label="操作人：" prop="updateBy">-->
<!--              <el-select v-model="updateBy" class="item-width" placeholder="请选择" clearable>-->
<!--                <el-option v-for="item in sysUserOptions" :key="item.userName" :label="item.userName"-->
<!--                           :value="item.userName"/>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <div style="text-align: right; margin-right: 10px;margin-top: 50px">-->
<!--          <el-button size="mini" type="success" @click="send">确认</el-button>-->
<!--          <el-button size="mini" type="primary" @click="dialogFormVisible=false">取消</el-button>-->
<!--        </div>-->
<!--      </el-form>-->

<!--    </el-dialog>-->

  </div>
</template>

<script>
  import moment from 'moment'
  // import {selectInformation, updateClaimProductList, selectSysUser} from '@/api/insuranceRules/ruleDefin'
  import {selectInformation} from '@/api/cs/imformation'

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
        riskCodes:[],
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          Service: undefined,
          channel: undefined,
          Acceptor: undefined,
          acceptorTime: undefined,
          Handler: undefined,
          HandlerTime: undefined,
          workNumber: undefined,
          policyNumber: undefined,
          secondNumber: undefined,
          insuredName: undefined,
          beInsuredName: undefined,
          beInsuredNo: undefined,
          organization: undefined,
          appointmentTime:undefined,
          priority:undefined,
          vip:undefined,
          phone:undefined
        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        loading: true,
        workPoolData: [],
        isinit: 'Y',
        totalCount: 0,
        changeSerchData: {},
        dataonLineListSelections: [],
        product_statusOptions: [],
        sysUserOptions: [],
      }
    },
    // mounted:在模板渲染成html后调用，通常是初始化页面完成后，再对html的dom节点进行一些需要的操作。
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        console.log('response: ',dictss,response)
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate

      this.searchHandle()
      selectSysUser().then(res => {
        this.sysUserOptions = res.data
      })
    },
    methods: {
      clickForm() {
        console.log('查询：',this.sendForm)
        this.$refs.sendForm.resetFields()
      },
      searchHandle() {
        debugger;
        let query = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          workNumber: "1"

        }
        console.log('query: ',query)
        // if (this.sendForm.synchronizeTime) {
        //   query.synchronizeStartTime = this.sendForm.synchronizeTime[0]
        //   query.synchronizeEndTime = this.sendForm.synchronizeTime[1]
        // }
        // if (this.sendForm.updateTime) {
        //   query.updateStartTime = this.sendForm.updateTime[0]
        //   query.updateEndTime = this.sendForm.updateTime[1]
        // }
        selectInformation(query).then(res => {
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
      // send() {
      //   if (this.updateBy != null && this.updateBy !== '') {
      //     let data = {
      //       updateBy: this.updateBy,
      //       riskCode: this.riskCodes
      //     }
      //     updateClaimProductList(data).then(res => {
      //       if (res != null && res.code === 200) {
      //         this.$message({
      //           message: '改派成功！',
      //           type: 'success',
      //           center: true,
      //           showClose: true
      //         })
      //         this.updateBy = undefined
      //         this.searchHandle()
      //         this.dialogFormVisible=false
      //       }
      //     }).catch(res => {
      //       this.updateBy = undefined
      //       this.$message.error('改派失败！')
      //     })
      //   } else {
      //     return this.$message.warning(
      //       "请先选择操作人！"
      //     )
      //   }
      // },
      // sendOne(row){
      //   this.dialogFormVisible = true
      //   this.riskCodes=[]
      //   this.riskCodes.push(row.riskCode)
      // },
      // sendMany() {
      //   if (this.dataonLineListSelections.length <= 0) {
      //     return this.$message.warning(
      //       "请先选择需要改派的数据！"
      //     )
      //   } else {
      //     this.dialogFormVisible = true
      //     if (this.updateBy != null && this.updateBy !== '') {
      //       this.riskCodes = []
      //       for (let i = 0; i < this.dataonLineListSelections.length; i++) {
      //         this.riskCodes.push(this.dataonLineListSelections[i].riskCode)
      //       }
      //     }
      //   }
      // },
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      getRiskStatus(row) {
        return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
