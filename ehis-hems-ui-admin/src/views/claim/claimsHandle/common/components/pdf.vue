<template>
  <div id="pdfDom">
    <div>
      <div class="barcode">
        <barcode
          :value="claimno"
          style="float:right"
          :options="barcode_option">
        </barcode>
      </div>
      <p style="margin:0 auto 20px auto;width:700px;color:Gray;text-align: center;font-size:20px;color:black">理赔申请回执</p>
      <div style="margin-top:50px;margin:0 auto;width:699px;color:Black;text-align: center;">
        <div class="item_list">
          <span class="item_first">被保险人</span>
          <span class="item_soced">{{pdfInfo.insuredName}}</span>
          <span class="item_first">性别</span>
          <span class="item_soced">{{/*dict.label.sex[pdfInfo.sex]*/this.selectDictLabel(this.sexOptions, pdfInfo.sex)}}</span>
        </div>
        <div class="item_list2">
          <span class="item_first">证件号码</span>
          <span class="item_soced">{{pdfInfo.idNo}}</span>
          <span class="item_first">出生日期</span>
          <span class="item_soced">{{pdfInfo.birthday|formatApprai}}</span>
        </div>
        <div class="item_list3">
          <span class="item_first">联系方式</span>
          <span class="item_soced">{{pdfInfo.mobiles}}</span>
          <span class="item_first">邮政编码</span>
          <span class="item_soced">{{pdfInfo.postcode}}</span>
        </div>
        <div class="item_list4">
          <span class="item_first">联系地址</span>
          <span class="item_thred">{{pdfInfo.address}}</span>
        </div>
        <div class="item_list5">
          <span class="item_first1">申请人</span>
          <span class="item_soced2">{{pdfInfo. applicant}}</span>
          <span class="item_first1">联系电话</span>
          <span class="item_soced3">{{pdfInfo.applicantMobile}}</span>
        </div>
        <div class="item_last">
          <div class="item_left">
            <div class="item_content">
              <span class="item_first4">申请日期</span>
              <span class="item_content_soced">{{pdfInfo.applicationDate|formatApprai}}</span>
            </div>
            <div class="item_content">
              <span class="item_first4">事故日期</span>
              <span class="item_content_soced">{{pdfInfo.eventDate|formatApprai}}</span>
            </div>
            <div class="item_content">
              <span class="item_first">理赔给付方式</span>
              <span class="item_content_soced2">{{pdfInfo.claimGetType}}</span>
            </div>
          </div>
          <div class="item_right">
            <div class="item_right_left">申请原因</div>
            <div class="item_rigth_right">
              <span class="item_content_last1">{{pdfInfo.applicantReasons.length>0?/*dict.label.apply_type[pdfInfo.applicantReasons[0]]*/this.selectDictLabel(this.apply_typeOptions,pdfInfo.applicantReasons[0]):''}}</span>
              <span class="item_content_last3">{{pdfInfo.applicantReasons.length>1?/*dict.label.apply_type[pdfInfo.applicantReasons[1]]*/this.selectDictLabel(this.apply_typeOptions,pdfInfo.applicantReasons[1]):''}}</span>
              <span class="item_content_last">{{pdfInfo.applicantReasons.length>2?/*dict.label.apply_type[pdfInfo.applicantReasons[2]]*/this.selectDictLabel(this.apply_typeOptions,pdfInfo.applicantReasons[2]):''}}</span>
              <span class="item_content_last2">{{pdfInfo.applicantReasons.length>3?/*dict.label.apply_type[pdfInfo.applicantReasons[3]]*/this.selectDictLabel(this.apply_typeOptions,pdfInfo.applicantReasons[3]):''}}</span>
              <span class="item_content_last8">{{pdfInfo.applicantReasons.length>4?/*dict.label.apply_type[pdfInfo.applicantReasons[4]]*/this.selectDictLabel(this.apply_typeOptions,pdfInfo.applicantReasons[4]):''}}</span>
              <span class="item_content_last9">{{pdfInfo.applicantReasons.length>5?/*dict.label.apply_type[pdfInfo.applicantReasons[5]]*/this.selectDictLabel(this.apply_typeOptions,pdfInfo.applicantReasons[5]):''}}</span>
            </div>
          </div>
        </div>
      </div>
      <div style="width:700px;margin: 0 auto;min-height:100px">
        <p style="color:Black">您提供的申请材料</p>
        <span v-for="(item,index) in pdfInfo.materials" :key="index">
          <span class="materials_list_span">
            <span>{{index+1}}.</span>
            <span style="margin-left:5px">{{/*dict.label.material_type[item.name]*/this.selectDictLabel(this.material_typeOptions, item.name)}}</span>
            <span style="margin-left:20px">{{item.number}}份</span>
          </span>
        </span>
      </div>
      <div style="width:700px;margin: 0 auto;border-bottom:1px solid black"></div>
      <div style="width:700px;margin: 0 auto;min-height:100px">
        <p style="color:Black">您需要补充以下证明和材料</p>
        <p v-for="(item,index) in pdfInfo.materialsOther" :key="index">
          <span>{{index}}</span>
          <span>{{item.name}}</span>
          <span>{{item.num}}份</span>
        </p>
      </div>
      <div style="width:700px;margin: 0 auto;border-bottom:1px solid black"></div>
      <div style="width:700px;margin: 0 auto;padding:20px 0">
        <p style="color:Black">备注信息</p>
      </div>
      <div style="width:700px;margin: 0 auto;border-bottom:1px solid black"></div>
      <div style="width:700px;margin: 100px auto;padding:20px 0;color:Black">
        <div style="width:700px;margin: 0 auto;padding:20px 0;color:Black;float:right">
          <span class="list_span">经办人：</span>
          <span class="list_span">{{pdfInfo.name}}</span>
        </div>
        <div style="width:700px;margin: 0 auto;padding:20px 0;color:Black;float:right">
          <span class="list_span">日期：</span>
          <span class="list_span">{{pdfInfo.date}}</span>
          <span class="list_span">客户签字：</span>
          <span class="list_span">{{pdfInfo.laname}}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import moment from 'moment'
let dictss = [{dictType:'sex'},{dictType:'apply_type'},{dictType:'material_type'},]
export default {
  filters: {
    formatApprai(val){
      if(val) {
        return moment(val).format('YYYY-MM-DD')
      }
    },
  },
  //dicts: ['sex', 'apply_type','material_type'],
  props: {
    pdfInfo: Object,
    claimno: String,
  },
  data () {
    return {
      // pdfInfo:{
      //   applicantReasons: [],
      // },
      // claimno: '123455555',
      dictList:[],
      barcode_option: {
        displayValue: true, // 是否默认显示条形码数据
        background: '#fff', // 条形码背景颜色
        valid: function(valid) {
        },
        width: '1px', // 单个条形码的宽度
        height: '40px',
        fontSize: '12px' // 字体大小
      },

      sexOptions: [],
      apply_typeOptions: [],
      material_typeOptions: []
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.sexOptions = this.dictList.find(item => {
      return item.dictType == 'sex'
    }).dictDate
    this.apply_typeOptions = this.dictList.find(item => {
      return item.dictType == 'apply_type'
    }).dictDate
    this.material_typeOptions = this.dictList.find(item => {
      return item.dictType == 'material_type'
    }).dictDate
  }
}
</script>
<style scoped>
#pdfDom {
  width:1000px;
  background: white;
  margin: 0 auto;
}
.barcode {
  height: 100px;
  width: 700px;
  margin: 140px auto 0 auto;
}
.item_first {
  display: inline-block;
  border-right: 1px solid black;
  height: 30px;
  line-height: 30px;
  width:100px;
  float: left;
}
.item_first4 {
  display: inline-block;
  border-right: 1px solid black;
  border-bottom: 1px solid black;
  height: 30px;
  line-height: 30px;
  width:100px;
  float: left;
}
.item_first1 {
  display: inline-block;
  border-bottom: 1px solid black;
  border-right: 1px solid black;
  height: 30px;
  line-height: 30px;
  width:100px;
  float: left;
}
.item_soced {
  display: inline-block;
  border-right: 1px solid black;
  height: 30px;
  width:249px;
  line-height: 30px;
  float: left;
}
.item_soced2 {
  display: inline-block;
  border-bottom: 1px solid black;
  border-right: 1px solid black;
  height: 30px;
  width:249px;
  line-height: 30px;
  float: left;
}
.item_soced3 {
  display: inline-block;
  border-bottom: 1px solid black;
  border-right: 1px solid black;
  height: 30px;
  width:249px;
  line-height: 30px;
  float: left;
}
.item_content_soced {
  display: inline-block;
  border-right: 1px solid black;
  border-bottom: 1px solid black;
  height: 30px;
  width:249px;
  line-height: 30px;
  float: left;
}
.item_content_soced2 {
  display: inline-block;
  border-right: 1px solid black;
  /* border-bottom: 1px solid black; */
  height: 30px;
  width:249px;
  line-height: 30px;
  float: left;
}
.item_thred{
  display: inline-block;
  border-right: 1px solid black;
  height: 30px;
  width:598px;
  line-height: 30px;
  float: left;
}
.item_list {
  width: 100%;
  height: 30px;
  line-height: 30px;
  border-top: 1px solid black;
  border-left: 1px solid black;
  border-bottom: 1px solid black;
  margin: 0px 0px;
}
.item_list2 {
  width: 100%;
  height: 30px;
  line-height: 30px;
  border-top: 0px solid black;
  border-left: 1px solid black;
  border-bottom: 1px solid black;
  margin: 0px 0px;
}
.item_list3 {
  width: 100%;
  height: 30px;
  line-height: 30px;
  border-top: 0px solid black;
  border-left: 1px solid black;
  border-bottom: 1px solid black;
  margin: 0px 0px;
}
.item_list4 {
  width: 100%;
  height: 30px;
  line-height: 30px;
  border-top: 0px solid black;
  border-left: 1px solid black;
  border-bottom: 1px solid black;
  margin: 0px 0px;
}
.item_list5 {
  width: 100%;
  height: 30px;
  line-height: 30px;
  border-top: 0px solid black;
  border-left: 1px solid black;
  border-bottom: 0px solid black;
  margin: 0px 0px;
}
.item_last {
  width: 100%;
  height: 92px;
  line-height: 92px;
  border-top: 0px solid black;
  border-left: 1px solid black;
  border-bottom: 1px solid black;
  /* border-right: 1px solid DarkGray; */
  margin: 0px 0px;
}
.item_left {
  height: 92px;
  width: 349px;
  float: left;
  margin: 0px 0px;
  /* border: 1px solid black; */
}
.item_right {
  height: 92px;
  width: 349px;
  float: left;
  margin: 0px 0px;
  /* border: 1px solid black; */
}
.item_content {
  height: 30px;
  line-height: 30px;
  width: 100%;
  /* border: 1px solid black; */
  float: left;
}
.item_right_left {
  height: 90px;
  width:99px;
  float: left;
  line-height: 90px;
  text-align: center;
}
.item_rigth_right {
  height: 90px;
  width:250px;
  float: left;
  line-height: 90px;
  border-right: 1px solid black;
  border-left: 1px solid black;
  text-align: center;
}
.item_content_last {
  height: 30px;
  line-height: 30px;
  width: 122px;
  float: left;
  display: inline-block;
  border-right: 1px solid black;
  border-bottom: 1px solid black;
}
.item_content_last8 {
  height: 30px;
  line-height: 30px;
  width: 122px;
  float: left;
  display: inline-block;
  border-right: 1px solid black;
  /* border-bottom: 1px solid black; */
}
.item_content_last1 {
  height: 30px;
  line-height: 30px;
  width: 122px;
  float: left;
  display: inline-block;
  border-right: 1px solid black;
  border-bottom: 1px solid black;
}
.item_content_last3 {
  height: 30px;
  line-height: 30px;
  width: 126px;
  float: left;
  display: inline-block;
  /* border-right: 1px solid black; */
  border-bottom: 1px solid black;
}
.item_content_last2 {
  height: 30px;
  line-height: 30px;
  width: 126px;
  float: left;
  display: inline-block;
  /* border-right: 1px solid DarkGray; */
  border-bottom: 1px solid black;
}
.item_content_last9 {
  height: 30px;
  line-height: 30px;
  width: 126px;
  float: left;
  display: inline-block;
  /* border-right: 1px solid DarkGray; */
  /* border-bottom: 1px solid black; */
}
.list_span {
  display: inline-block;
  float: right;
  margin-right: 30px;
}
.materials_list_span {
  display: inline-block;
  height: 20px;
  line-height: 20px;
  width: 290px;
  margin:5px 0
}
</style>
