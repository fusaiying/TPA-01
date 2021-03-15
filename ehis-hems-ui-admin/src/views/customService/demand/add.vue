<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="sendForm" :model="sendForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNumber">
              <el-input v-model="sendForm.policyNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="secondNumber">
              <el-input v-model="sendForm.policyItemNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="beInsuredName">
              <el-input v-model="sendForm.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人证件号：" prop="beInsuredNo">
              <el-input v-model="sendForm.idNo" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item style="white-space: nowrap" label="被保人生日：" prop="beBirthday">
              <el-input v-model="sendForm.birthday" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

        <div style="text-align: right; margin-right: 1px;">
          <el-button size="mini" type="primary" @click="searchHandle">查询</el-button>
          <el-button size="mini" type="primary" @click="jumpButton">跳过</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">查询结果（{{ totalCount }}）</span>
        <el-divider></el-divider>
        <el-table
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="workPoolData"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style=" width: 100%;"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" align="center" content="全选"/>
          <el-table-column prop="policyNo" align="center" label="保单号" show-overflow-tooltip/>
          <el-table-column prop="policyItemNo" align="center"  label="分单号" show-overflow-tooltip/>
          <el-table-column prop="name" align="center" label="姓名" show-overflow-tooltip/>
          <el-table-column prop="sex" align="center" label="性别 " show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(rgtSex, scope.row.sex)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="birthday" align="center" label="出生日期" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.birthday | changeDate}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="idType" align="center" label="证件类型" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{selectDictLabel(card_type, scope.row.idType)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="idNo" align="center" label="证件号码" show-overflow-tooltip/>
          <el-table-column prop="mobile" align="center" label="手机" show-overflow-tooltip/>
          <el-table-column prop="address" align="center" label="联系地址" show-overflow-tooltip/>
          <el-table-column align="center" fixed="right" label="处理" width="140">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="acceptButton(scope.row)">受理</el-button>
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

  </div>
</template>

<script>
  import moment from 'moment'
  import {policyListDataPool} from '@/api/customService/common'
  let dictss = [
    {dictType: 'rgtSex'},
    {dictType: 'card_type'},
  ]
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
        sendForm: {
          policyNo: "",
          policyItemNo: "",
          name: "",
          idNo: "",
          birthday: "",
        },
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10
        },
        loading: true,
        workPoolData: [],
        totalCount: 0,
        dictList: [],
        rgtSex: [],
        card_type: [],
      }
    },
    created() {

    },
    async mounted() {
        // 字典数据统一获取
        await this.getDictsList(dictss).then(response => {
          this.dictList = response.data
        })
        // 下拉项赋值
        this.rgtSex = this.dictList.find(item => {
          return item.dictType === 'rgtSex'
        }).dictDate
        this.card_type = this.dictList.find(item => {
          return item.dictType === 'card_type'
        }).dictDate
    },
    methods: {
      //跳过
      jumpButton() {
        this.$router.push({
          path: '/customService/demand-editDetail',
          isEmpty: false
        })
      },
      //受理
      acceptButton(s) {
        this.$router.push({
          path: '/customService/demand-editDetail',
          query:{
            policyNo:s.policyNo,
            policyItemNo:s.policyItemNo,
          }
        })
      },

      resetForm() {
        this.$refs.sendForm.resetFields()
      },
      searchHandle() {
        let params = JSON.parse(JSON.stringify(this.sendForm));
        params.pageNum = this.queryParams.pageNum;
        params.pageSize = this.queryParams.pageSize;
        console.info(params);
        policyListDataPool(params).then(res => {
            if (res != null && res.code === 200) {
              this.workPoolData = res.rows
              this.totalCount = res.total
              if (res.total <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                );
              }
            }
          }
        ).catch(res => {

        })
      },
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
