<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="riskForm" :model="riskForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="产品编码：" prop="riskCode">
              <el-input v-model="riskForm.riskCode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="riskStatus">
              <el-select v-model="riskForm.riskStatus" class="item-width" placeholder="请选择">
                <el-option v-for="item in product_statusOptions" :key="item.dictValue" :label="item.dictLabel"
                           :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="同步日期：" prop="synchronizeTime">
              <el-date-picker
                v-model="riskForm.synchronizeTime"
                style="width:220px;"
                size="mini"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="riskName">
              <el-input v-model="riskForm.riskName" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="updateBy">
              <el-input v-model="riskForm.updateBy" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作日期：" prop="updateTime">
              <el-date-picker
                v-model="riskForm.updateTime"
                style="width:220px;"
                size="mini"
                type="daterange"
                value-format="yyyy-MM-dd"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                placeholder="选择日期"
              />
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
              page=1,
              finishPage=1,
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>工作池（{{ totalCount }}）</span>
        <el-divider/>
        <claimsTable :table-data="workPoolData" />
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'
  import {selectProductQuery} from '@/api/insuranceRules/ruleDefin'
  let dictss = [{dictType: 'product_status'}]
  export default {
    components: {
      claimsTable
    },
    data() {
      return {
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        riskForm: {
          riskCode: undefined,
          riskStatus: undefined,
          synchronizeTime: undefined,
          riskName: undefined,
          updateBy: undefined,
          updateTime: undefined,
        },
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        loading: true,
        workPoolData: [],
        isinit: 'Y',
        page: 1,
        totalCount: 0,
        finishPageSize: 10,
        changeSerchData: {},
        dictList:[],
        product_statusOptions:[],
      }
    },
    created() {
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.product_statusOptions = this.dictList.find(item => {
        return item.dictType === 'product_status'
      }).dictDate
      this.searchHandle()
    },
    methods: {
      resetForm() {
        this.$refs.riskForm.resetFields()
      },
      searchHandle() {
        let query = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          riskCode: this.riskForm.riskCode,
          riskStatus: this.riskForm.riskStatus,
          riskName: this.riskForm.riskName,
          updateBy: this.riskForm.updateBy,
          synchronizeStartTime: undefined,
          synchronizeEndTime: undefined,
          updateStartTime: undefined,
          updateEndTime: undefined,
        }
        if (this.riskForm.synchronizeTime) {
          query.synchronizeStartTime = this.riskForm.synchronizeTime[0]
          query.synchronizeEndTime = this.riskForm.synchronizeTime[1]
        }
        if (this.riskForm.updateTime) {
          query.updateStartTime = this.riskForm.updateTime[0]
          query.updateEndTime = this.riskForm.updateTime[1]
        }
        selectProductQuery(query).then(res => {
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
            if (res.rows.length<=0){
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })
      },
      send() {

      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
