<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div style="position: relative">
      <div slot="header" class="clearfix">
        <div style="width: 100%;cursor: pointer;">
          <span style="color: #1890ff">申述案件赔付结论</span>
        </div>
      </div>
    </div>
    <el-divider/>
    <el-form ref="conclusionForm" :model="conclusionInfo" style="padding-bottom: 30px;margin-top:20px;margin-left: 5%"
             label-width="130px"
             label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">账单金额：</span><span
          class="info_span">{{ conclusionInfo.contractNo }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">折扣金额：</span><span class="info_span">{{ conclusionInfo.amount }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">赔付金额：</span><span
          class="info_span">{{ conclusionInfo.contractName }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">拒赔金额：</span><span
          class="info_span">{{ conclusionInfo.contractNo }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">追讨金额：</span><span class="info_span">{{ conclusionInfo.amount}}</span>
        </el-col>
        <!--<el-col :span="8">
          <span class="info_span_col to_right">本次支付差额：</span><span
          class="info_span_col">{{ conclusionInfo.amount}}</span>
        </el-col>-->
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">账单币种：</span><span
          class="info_span">{{ conclusionInfo.contractNo }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">汇率：</span><span class="info_span">{{ conclusionInfo.amount}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">外币给付金额：</span><span
          class="info_span">{{ conclusionInfo.contractName }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">赔付结论：</span><span
          class="info_span">{{ conclusionInfo.contractNo }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">拒赔原因：</span><span class="info_span">{{ conclusionInfo.amount}}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="客户备注：" prop="remark">
            <el-input disabled style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                      v-model="conclusionInfo.customerRemark" class="item-width" clearable size="mini"
                      placeholder=""/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="核赔依据：" prop="remark">
            <el-input disabled style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                      v-model="conclusionInfo.fileRemark" class="item-width" clearable size="mini"
                      placeholder=""/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
  import {calInfo} from '@/api/handel/common/api'

  export default {
    props: {
      fixInfo: Object,
    },
    components: {},
    data() {
      return {
        conclusionInfo: {},

      }
    },
    mounted() {

    },
    watch: {
      fixInfo: function (newVal) {
        this.getCalInfo()

      }
    },
    computed: {},
    methods: {
      getCalInfo() {
        calInfo(this.fixInfo.rptNo).then(res => {
          if (res.code === 200) {
            this.conclusionInfo = res.data;
          }
        })
      },
    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }

  ::v-deep .info_span_col {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #606266;
    line-height: 40px;
    padding: 0 12px 0 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }

  ::v-deep .to_right {
    width: 130px;
    text-align: right;
  }
</style>
