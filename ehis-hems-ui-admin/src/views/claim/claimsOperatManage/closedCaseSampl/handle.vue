<template>
  <div class="app-container" style="margin-bottom: 10px;">
    <el-card class="top-card">
      <el-button type="text" size="small">赔案号：  CL202003140001</el-button>
      <el-button type="text" size="small">状态：</el-button>
      <span style="float: right; padding: 3px 0">
        <el-button type="primary" size="mini">提调(0)</el-button>
        <el-button type="primary" size="mini" @click="getImagesList">影像(0)</el-button>
        <el-button v-if="node==='reopen'" type="primary" size="mini" @click="goBack">返回</el-button>
      </span>
    </el-card>
    <el-row style="margin-top: 10px;">
      <el-col :span="6">
        <el-card :class="{'simple-card': node!=='reopen', 'reopen-card': node==='reopen'}">
          <div slot="header" class="clearfix">
            <span>基本信息</span>
          </div>
          <el-progress v-if="node!=='reopen'" :percentage="100" status="success" style="position: relative; top: -8px;"></el-progress>
          <el-form label-width="120px" label-position="right" size="mini" class="insured-info">
            <el-form-item label="被保险人号：">
              <span>ewrjfgitjg</span>
            </el-form-item>
            <el-form-item label="被保人姓名：">
              <el-button size="mini" type="text" @click="showInsuredInfo">测试</el-button>
            </el-form-item>
            <div style="margin-left: 24px;">
              <el-button size="mini" type="primary" plain>VIP</el-button>
              <el-button size="mini" type="danger" plain>高风险</el-button>
              <el-button size="mini" type="warning" plain>爱投诉</el-button>
            </div>
            <el-form-item label="性别：">
              <span>{{  }}</span>
            </el-form-item>
            <el-form-item label="出生日期：">
              <span>{{  }}</span>
            </el-form-item>
            <el-form-item label="证件号码：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="手机号：">
              <span>{{  }}</span>
            </el-form-item>
            <el-form-item label="申请来源：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="领款人：">
              <el-button size="mini" type="text" @click="showPayeeInfo">测试</el-button>
            </el-form-item>
            <el-form-item label="申请人：">
              <el-button size="mini" type="text" @click="showApplicantInfo">测试</el-button>
            </el-form-item>
            <el-form-item label="报案人：">
              <span>{{ }}</span>
            </el-form-item>
            <p class="card-title">赔付信息</p>
            <el-form-item label="理赔申请日：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="资料完整日：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="申请金额：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="垫付金额：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="总扣除金额：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item label="总赔付金额：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item v-if="node==='reopen'" label="赔付结论：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item v-if="node==='sampl'" label="案件结论：">
              <span>{{ }}</span>
            </el-form-item>
            <el-form-item v-if="node==='sampl'" label="结论说明：">
              <span>{{ }}</span>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="18">
        <div style="margin-left: 6px;">
          <el-card class="box-card">
            <el-tabs v-model="activeName">
              <el-tab-pane :label="`关联保单(${relatTableData.length})`" name="01">
                <policyTable :tableData="relatTableData" :status="activeName"></policyTable>
              </el-tab-pane>
              <el-tab-pane :label="`其他保单(${otherTableData.length})`" name="02">
                <policyTable :tableData="otherTableData" :status="activeName"></policyTable>
              </el-tab-pane>
            </el-tabs>
            <div class="tab-title" style="margin-top: 20px;">
              <span>风险信息</span>
            </div>
            <el-table
              size="small"
              highlight-current-row
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              tooltip-effect="dark"
              :data="riskTableData"
              style="width: 100%;">
              <el-table-column align="center" prop="code" label="地区" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="name" label="医院" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="诊断类型" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="诊断信息" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="就诊日期" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="数据来源" show-overflow-tooltip></el-table-column>
            </el-table>
          </el-card>
          <el-card style="margin-top: 10px;">
            <div class="tab-title">
              <span>事件信息</span>
            </div>
            <el-table
              size="small"
              highlight-current-row
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              tooltip-effect="dark"
              :data="infoTableData"
              style="width: 100%;">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-row>
                    <el-col :span="6">
                      <span class="expand_span">文档未列出需展示字段：{{props.row.code}} 123</span>
                    </el-col>
                  </el-row>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="code" label="事件号" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="name" label="出险日期" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="就诊日期" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="事件类型" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="事件信息" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="发生地" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="就诊医院" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="发票" show-overflow-tooltip></el-table-column>
            </el-table>
            <div class="tab-title" style="margin-top: 20px;">
              <span>发票信息</span>
            </div>
            <el-table
              size="small"
              highlight-current-row
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              tooltip-effect="dark"
              :data="infoTableData"
              style="width: 100%;">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-row>
                    <el-col :span="6">
                      <span class="expand_span">文档未列出需展示字段：{{props.row.code}} 123</span>
                    </el-col>
                  </el-row>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="code" label="发票号" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="name" label="账单类型" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="总金额" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="统筹金额" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="第三方支付" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="诊断" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="给付责任" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="不合理金额" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="赔付金额" show-overflow-tooltip></el-table-column>
              <el-table-column align="center" prop="type" label="备注" show-overflow-tooltip></el-table-column>
            </el-table>
            <el-tabs v-model="detaiActiveName" style="margin-top: 30px; position: relative;">
              <el-button style="position: absolute; top: 10px; right: 10px;" type="primary" size="mini">案件备注</el-button>
              <el-tab-pane label="赔付明细" name="01">
                <claimsTable :tableData="compensateTableData" :status="detaiActiveName"></claimsTable>
              </el-tab-pane>
              <el-tab-pane :label="`既往赔案(${pastTableData.length})`" name="02">
                <claimsTable :tableData="pastTableData" :status="detaiActiveName"></claimsTable>
              </el-tab-pane>
              <el-tab-pane :label="`既往垫付(${advanceTableData.length})`" name="03">
                <claimsTable :tableData="advanceTableData" :status="detaiActiveName"></claimsTable>
              </el-tab-pane>
            </el-tabs>
            <div class="tab-title" style="margin-top: 20px;">
              <span>抽检结论</span>
            </div>
            <el-row>
              <el-form :disabled="node!=='sample'" ref="resultForm" :model="resultForm" size="mini" label-width="94px">
                <el-col :span="7">
                  <el-form-item label="结论类型：" prop="result"
                    :rules="{ required: true, message: '不能为空'}">
                    <el-select style="width: 100px;" v-model="resultForm.result" placeholder="请选择" size="mini">
                      <el-option label="通过" value="1"></el-option>
                      <el-option label="退回" value="2"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="17">
                  <el-form-item label="结论说明：" prop="desc">
                    <el-input maxlength="1000" show-word-limit clearable v-model="resultForm.desc" size="mini" placeholder="请输入"/>
                  </el-form-item>
                </el-col>
                <span v-if="node==='sampl'" style="float: right; padding: 3px 0">
                  <el-button type="primary" size="mini" @click="submitHandle">抽检完毕</el-button>
                  <el-button type="primary" size="mini" @click="goBack">返回</el-button>
                </span>
              </el-form>
            </el-row>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <insure-modal v-model="insureDia"></insure-modal>
    <applicant-modal v-model="applicantDia"></applicant-modal>
    <payee-modal v-model="payeeDia" :tableData="payeeData"></payee-modal>
    <images-modal v-model="imagesDia"></images-modal>
    <!-- <materials-modal v-model="materialsDia" :flag="false"></materials-modal> -->
  </div>
</template>

<script>
import policyTable from "./components/policyTable"
import claimsTable from  "./components/claimsTable"
/*import insureModal from "../../claimsHandle/common/modul/insured"
import applicantModal from '../../claimsHandle/common/modul/applicant';
import payeeModal from "../../claimsHandle/common/modul/payee";
import imagesModal from "../../claimsHandle/common/modul/images"*/

export default {
  components: {
    policyTable,
    claimsTable,
    insureModal,
    applicantModal,
    payeeModal,
    imagesModal,
    // materialsModal
  },
  data() {
    return {
      insureDia: false,
      applicantDia: false,
      payeeDia: false,
      imagesDia: false,
      // materialsDia: false,
      activeName: '01',
      detaiActiveName: '01',
      relatTableData: [
        {policyNo: 'DEF36454744'}
      ],
      otherTableData: [
        {policyNo: 'FRH23464527'},
        {policyNo: 'DWE34546546'},
      ],
      infoTableData: [
        {type: 'qwd'}
      ],
      riskTableData: [
        {type: 'qwd'}
      ],
      compensateTableData: [
        {taskno: 'qwd'}
      ],
      pastTableData: [
        {taskno: 'qwd'}
      ],
      advanceTableData: [
        {taskno: 'qwd'}
      ],
      resultForm: {},
      payeeData: [
        {name: '小米'}
      ],
      node: '',
    }
  },
  mounted(){
    if(this.$route.query){
      this.node = this.$route.query.node
      // if(this.$router.query.node === 'reopen'){

      // }
    }
  },
  methods: {
    getImagesList(){
      this.imagesDia = true
    },
    showInsuredInfo(){
      this.insureDia = true
    },
    showApplicantInfo(){
      this.applicantDia = true
    },
    showPayeeInfo(){
      this.payeeDia = true
    },
    goBack(){
      this.$router.go(-1)
    },
    submitHandle(){
      this.$refs.resultForm.validate(valid => {
        if(valid){
          alert('submit!')
        } else {
          return false
        }
      })
    }
  }
}
</script>
<style scoped>
  .card-title {
    height: 54px;
    line-height: 54px;
    border-bottom: 1px solid #e6ebf5;
    color: #303133;
    margin: 0;
    padding: 0 20px;
    background: #eee;
    margin-bottom: 20px;
  }
  .insured-info .el-form-item {
    margin-bottom: 6px;
  }
  .reopen-card /deep/ .el-card__body,
  .simple-card /deep/ .el-card__body {
    padding: 0;
  }
  .reopen-card /deep/ .el-card__header {
    background: #eee;
  }
  .simple-card /deep/ .el-card__header {
    background: #3ada3780;
  }
  .top-card /deep/ .el-card__body {
    padding: 10px 20px;
  }
</style>
