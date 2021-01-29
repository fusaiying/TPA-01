<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编码：" prop="riskCode">
              <el-input v-model="sendForm.riskCode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="riskStatus">
              <el-select v-model="sendForm.riskStatus" class="item-width" placeholder="请选择">
                <el-option key="02" label="规则设置"
                           value="02"/>
                <el-option key="03" label="规则审核"
                           value="03"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="同步日期：" prop="synchronizeTime">
              <el-date-picker
                v-model="sendForm.synchronizeTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="riskName">
              <el-input v-model="sendForm.riskName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="updateBy">
              <el-input v-model="sendForm.updateBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作日期：" prop="updateTime">
              <el-date-picker
                v-model="sendForm.updateTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              isinit='N',
              queryParams.pageNum=1,
              queryParams.pageSize=10,
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>工作池（{{ totalCount }}）</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="sendMany">任务改派</el-button>
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
          <el-table-column type="selection" align="center"/>
          <el-table-column align="center" width="140" prop="riskCode" label="产品编码" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskName" label="产品名称" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskNature" label="产品性质" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskClass" label="产品分类" show-overflow-tooltip/>
          <el-table-column align="center" :formatter="getRiskStatus" prop="riskStatus" label="状态"
                           show-overflow-tooltip/>
          <el-table-column prop="examineConclusion" align="center" label="当前结论" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(approvalconclusionOptions, scope.row.examineConclusion)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="synchronizeTime" label="同步日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.synchronizeTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="操作日期" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateBy" align="center" label="操作人" show-overflow-tooltip/>
          <el-table-column align="center" label="操作" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="sendOne(scope.row)">改派</el-button>
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

    <el-dialog title="任务改派" :visible.sync="dialogFormVisible" width="30%" hight="60%">
      <el-form ref="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="10">
            <el-form-item label="操作人：" prop="updateBy">
              <el-select
                v-model="updateBy"
                filterable
                remote
                reserve-keyword
                placeholder="请选择操作人"
                :remote-method="remoteMethod"
                :loading="userLoading"
                class="item-width"
                size="mini">
                <el-option
                  v-for="(item, ind) in sysUserOptions"
                  :key="ind"
                  :label="item.userName"
                  :value="item.userName">
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;margin-top: 50px">
          <el-button size="mini" type="success" @click="send">确认</el-button>
          <el-button size="mini" type="primary" @click="dialogFormVisible=false">取消</el-button>
        </div>
      </el-form>

    </el-dialog>

  </div>
</template>

<script>
  import moment from 'moment'
  import {selectClaimProductList, updateClaimProductList, selectSysUser} from '@/api/insuranceRules/ruleDefin'

  let dictss = [{dictType: 'product_status'}, {dictType: 'approvalconclusion'},]
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
        userLoading: false,
        riskCodes: [],
        dialogFormVisible: false,
        updateBy: undefined,
        sendForm: {
          riskCode: undefined,
          riskStatus: undefined,
          synchronizeTime: undefined,
          riskName: undefined,
          updateBy: undefined,
          updateTime: undefined,
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
        approvalconclusionOptions: [],
        sysUserOptions: [],
      }
    },

    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate
      this.approvalconclusionOptions = this.dictList.find(item => {
        return item.dictType === 'approvalconclusion'
      }).dictDate
      this.searchHandle()
    },
    methods: {
      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      searchHandle() {
        let query = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          riskCode: this.sendForm.riskCode,
          riskStatus: this.sendForm.riskStatus,
          synchronizeStartTime: undefined,
          synchronizeEndTime: undefined,
          riskName: this.sendForm.riskName,
          updateBy: this.sendForm.updateBy,
          updateStartTime: undefined,
          updateEndTime: undefined,
        }
        if (this.sendForm.synchronizeTime) {
          query.synchronizeStartTime = this.sendForm.synchronizeTime[0]
          query.synchronizeEndTime = this.sendForm.synchronizeTime[1]
        }
        if (this.sendForm.updateTime) {
          query.updateStartTime = this.sendForm.updateTime[0]
          query.updateEndTime = this.sendForm.updateTime[1]
        }
        selectClaimProductList(query).then(res => {
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
      send() {
        if (this.updateBy != null && this.updateBy !== '') {
          let data = {
            updateBy: this.updateBy,
            riskCode: this.riskCodes
          }
          updateClaimProductList(data).then(res => {
            if (res != null && res.code === 200) {
              this.$message({
                message: '改派成功！',
                type: 'success',
                center: true,
                showClose: true
              })
              this.updateBy = undefined
              this.searchHandle()
              this.dialogFormVisible = false
            }
          }).catch(res => {
            this.updateBy = undefined
            this.$message.error('改派失败！')
          })
        } else {
          return this.$message.warning(
            "请先选择操作人！"
          )
        }
      },
      sendOne(row) {
        this.updateBy = ''
        this.dialogFormVisible = true
        this.riskCodes = []
        this.riskCodes.push(row.riskCode)
      },
      sendMany() {
        this.updateBy=''
        if (this.dataonLineListSelections.length <= 0) {
          return this.$message.warning(
            "请先选择需要改派的数据！"
          )
        } else {
          this.dialogFormVisible = true
          this.riskCodes = []
          for (let i = 0; i < this.dataonLineListSelections.length; i++) {
            this.riskCodes.push(this.dataonLineListSelections[i].riskCode)
          }
        }
      },
      handleSelectionChange(val) {
        this.dataonLineListSelections = val
      },
      getRiskStatus(row) {
        return this.selectDictLabel(this.product_statusOptions, row.riskStatus)
      },
      remoteMethod(query) {
        let data = {
          userName: query
        }
        if (query !== '' && query != null) {
          selectSysUser(data).then(res => {
            this.sysUserOptions = res.data
          })
        }
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
