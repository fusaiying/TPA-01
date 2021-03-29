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
    <el-form ref="conclusionForm" :model="conclusionInfo" style="padding-bottom: 30px;margin-top:20px;margin-left: 5%"  label-width="130px" label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">账单金额：</span> <span class="info_span">{{ conclusionInfo.sumBillAmount }} {{ conclusionInfo.sumBillAmount == ''  ? '' : conclusionInfo.billCurrency }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">折扣金额：</span> <span class="info_span money_class">{{ conclusionInfo.sumHosDiscountAmount }} {{ conclusionInfo.sumHosDiscountAmount == ''  ? '' : conclusionInfo.billCurrency}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">赔付金额：</span> <span class="info_span money_class">{{ conclusionInfo.calAmount }} {{ conclusionInfo.calAmount == '' || conclusionInfo.calAmount == null  ? '' : 'CNY' }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">拒赔金额 ：</span> <span class="info_span money_class">{{ conclusionInfo.refusedAmount }} {{ conclusionInfo.refusedAmount == '' || conclusionInfo.refusedAmount == null ? '' : 'CNY' }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">追讨金额：</span> <span class="info_span money_class">{{ conclusionInfo.debtAmount}} {{ conclusionInfo.debtAmount == '' || conclusionInfo.debtAmount == null ? '' : 'CNY' }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">账单币种：</span> <span class="info_span">{{ conclusionInfo.billCurrency }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">汇率：</span><span class="info_span money_class">{{ conclusionInfo.exchangeRate}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">外币给付金额：</span> <span class="info_span money_class">{{ conclusionInfo.payAmountForeign}}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span_col to_right">赔付结论：</span><span
          class="info_span">{{selectDictLabel(conclusionSelect, conclusionInfo.payConclusion) }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span_col to_right">拒赔原因：</span><span class="info_span">{{selectDictLabel(rejectedReasons, conclusionInfo.refusedReason)}}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="客户备注：" prop="remark">
            <el-input disabled style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                      v-model="conclusionInfo.remark" class="item-width" clearable size="mini"
                      placeholder=""/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="核赔依据：" prop="remark">
            <el-input disabled style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                      v-model="conclusionInfo.claimCheck" class="item-width" clearable size="mini"
                      placeholder=""/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
import {preClaimInfo} from '@/api/handel/common/api';

let dictss = [{dictType: 'conclusion'}, {dictType: 'rejected_reasons'}];
export default {
  props: {
    fixInfo: Object,
  },
  components: {},
  data() {
    return {
      //赔付结论信息 start
      conclusionInfo:{
        sumBillAmount:'', // 账单金额
        sumHosDiscountAmount:'',// 折扣金额
        payAmount:'', // 赔付金额
        refusedAmount:'', //拒赔金额
        debtAmount:'', // 追讨金额
        billCurrency:'', // 账单币种
        exchangeRate:'', // 汇率
        payAmountForeign:'', // 账单币种
        payConclusion:'', // 赔付结论
        refusedReason:'', // 拒赔原因
        remark:'',
        claimCheck:'',
      },
      //赔付结论信息 end
      //赔付结论
      conclusionSelect:[],
      //拒赔原因
      rejectedReasons :[],

    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.conclusionSelect = this.dictList.find(item => {
      return item.dictType === 'conclusion'
    }).dictDate
    this.rejectedReasons = this.dictList.find(item => {
      return item.dictType === 'rejected_reasons'
    }).dictDate

    if(this.fixInfo.isAppeal === '01') {
      this.getCalInfo()
    }
  },
  watch: {
    fixInfo: function (newVal) {
      this.getCalInfo()

    }
  },
  computed: {},
  methods: {
    getCalInfo() {
      preClaimInfo(this.fixInfo.rptNo).then(res => {
        if (res.code === 200 && res.data) {
         // console.log("申述案件赔付结论 : ",res.data)
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

.money_class{
  color: #2CC38E
}
</style>
