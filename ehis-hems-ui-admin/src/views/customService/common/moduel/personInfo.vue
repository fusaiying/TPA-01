<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span style="color: blue">客户基本信息</span>
    </div>
    <el-form ref="personInfo" :model="personInfo" label-width="100px"
             label-position="right" size="mini" :disabled="routerParams.status=='show'">
      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">保单号：</span><span class="info_span">{{ personInfo.policyNo }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">投保人姓名：</span><span class="info_span">{{ personInfo.policyHolder.name }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">投保人证件号：</span><span class="info_span">{{ personInfo.policyHolder.idNumber }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">投保人证件类型：</span><span class="info_span">{{ personInfo.policyHolder.idType }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">分单号：</span><span class="info_span">{{ personInfo.policyItemNo }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">被保人姓名：</span><span class="info_span">{{ personInfo.insured.name }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">被保人性别：</span><span class="info_span">{{ personInfo.insured.sex }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">被保人出生日期：</span><span class="info_span">{{ personInfo.insured.birthDate }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">被保人证件号：</span><span class="info_span">{{ personInfo.insured.idNumber }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">被保人证件类型：</span><span class="info_span">{{ personInfo.insured.idType }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">被保人电话：</span><span class="info_span">{{ personInfo.insured.phone }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">投保日期：</span><span class="info_span">{{ personInfo.insuranceDate }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">承保日期：</span><span class="info_span">{{ personInfo.effectiveDate }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">保溢生效日：</span><span class="info_span">{{ personInfo.effectiveStartDate }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">保溢满期日：</span><span class="info_span">{{ personInfo.effectiveEndDate }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">主招揽业务员：</span><span class="info_span">{{ personInfo.mainSolicit }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">主招揽业务员电话：</span><span class="info_span">{{ personInfo.solicitPhone }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">VIP标识：</span><span class="info_span">{{ personInfo.vipFlag }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">是否UHCG会员：</span><span class="info_span">{{ personInfo.uhcgFlag }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">与主保险人关系：</span><span class="info_span">{{ personInfo.relationBy }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">出单机构：</span><span class="info_span">{{ personInfo.companyCode }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">险种代码：</span><span class="info_span">{{ personInfo.risckCode }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">计划名称：</span><span class="info_span">{{ personInfo.planCode }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">保单生效日：</span><span class="info_span">{{ personInfo.validDate }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">首次生效日：</span><span class="info_span">{{ personInfo.firstValidDate }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">AM(服务经理)：</span><span class="info_span">{{ personInfo.acceptor }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">BD(销售经理)：</span><span class="info_span">{{ personInfo.acceptor }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <span class="info_span to_right">特定医院赔付比例：</span><span class="info_span">{{ personInfo.specialRatio }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">被保人性质：</span><span class="info_span">{{ personInfo.insuredType }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">投保来源：</span><span class="info_span">{{ personInfo.policyFrom }}</span>
        </el-col>
      </el-row>
    </el-form>
  </el-card>

</template>
<script>
import {getPersonInfoData} from "@/api/customService/spotCheck";
import {policyInfoData} from "@/api/customService/common";

let dictss = [{dictType: 'claimType'},]

export default {
  props: {
    routerParams:Object,
    policyNo:String,
    policyItemNo:String,
    fixInfo: {
      type: Object,
      default: function (){
        return {}
      }
    },
  },
  data() {
    return {
      dutyLoading: false,
      dictList: [],
      claimTypeOptions: [],
      personInfo:{
        policyNo:undefined,
        policyHolder: {},
        policyItemNo:undefined,
        insured: {},
        insuranceDate:undefined,
        effectiveDate:undefined,
        effectiveStartDate:undefined,
        effectiveEndDate:undefined,
        mainSolicit:undefined,
        solicitPhone:undefined,
        vipFlag:undefined,
        uhcgFlag:undefined,
        relationBy:undefined,
        companyCode:undefined,
        risckCode:undefined,
        planCode:undefined,
        validDate:undefined,
        firstValidDate:undefined,
        acceptor:undefined,
        specialRatio:undefined,
        insuredType:undefined,
        policyFrom:undefined,
      }
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.claimTypeOptions = this.dictList.find(item => {
      return item.dictType === 'claimType'
    }).dictDate
    this.getPersonInfo();
  },
  methods: {
    //获取客户基本信息
    getPersonInfo() {
      let getQuery = {
        policyNo:this.policyNo,
        policyItemNo:this.policyItemNo,
      }
      console.info(getQuery);
      //查询外部接口获取保单和被保人信息
      if (getQuery.policyNo!=null && getQuery.policyNo!='' && getQuery.policyNo!=undefined
        && getQuery.policyItemNo!=null && getQuery.policyItemNo!='' && getQuery.policyItemNo!=undefined){
        policyInfoData(getQuery).then(res => {
          console.log('++++++++------------: ', res)
          if (res !== undefined && res.code === 200) {
            if (res.data){
              this.personInfo = res.data
            }
          }
        }).catch(res => {

        }).finally(() => {

        })
      }

    }
  }
}
</script>
<style scoped>
.info_span {
  font-size: 13px;
}

.item-width {
  width: 160px;
}

.el-tree /deep/ .el-tree-node__content {
  height: 30px;
}

.check_box {
  margin-top: 16px;
}

.collapse-card.el-card /deep/ .el-card__body {
  padding: 0;
}

.el-collapse /deep/ .el-collapse-item__header {
  background-color: #f8f8f8;
  padding: 0 10px;
}

.el-collapse /deep/ .el-collapse-item__wrap {
  padding: 0 10px;
}

.el-checkbox {
  margin-left: 20px;
}

.el-checkbox-group {
  margin-left: 20px;
}

.el-checkbox-group /deep/ .el-checkbox {
  display: block;
}

.to_right {
  width: 150px;
  text-align: right;
}

/deep/ .custom-tree-node {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
}

/deep/ .custom-tree-node span {
  flex: 1;
  display: inline-block;
  width: 80%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
<style>
.el-tooltip__popper {
  max-width: 400px;
}

.el-table /deep/ .el-table__expanded-cell {
  padding: 10px;
}
</style>
