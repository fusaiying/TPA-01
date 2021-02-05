<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>批次信息</span>
    </div>
    <el-row style="margin: 0 40px;padding-bottom: 20px">
      <el-col :span="8">
        <span class="info_span to_right">交单日期：</span><span class="info_span">{{ baseInfo.submitdate }}</span>
      </el-col>
      <el-col :span="8">
        <span class="info_span to_right">理赔类型：</span><span class="info_span">{{ selectDictLabel(claimTypeOptions, baseInfo.claimtype) }}</span>
      </el-col>
      <el-col :span="8">
        <span class="info_span to_right">团险/个险：</span><span class="info_span">{{ selectDictLabel(insurance_typeOptions, baseInfo.conttype) }}</span>
      </el-col>
      <el-col :span="8">
        <span class="info_span to_right">就诊医院：</span><span class="info_span">{{ selectHospitalName(hospitalOptions, baseInfo.hospitalcode) }}</span>
      </el-col>
      <el-col :span="8">
        <span class="info_span to_right">案件数：</span><span class="info_span">{{ baseInfo.casenum }}</span>
      </el-col>
      <el-col :span="8">
        <span class="info_span to_right">批次总金额：</span><span class="info_span">{{ baseInfo.batchtotal }}{{selectDictLabel(currencyOptions, baseInfo.currency) }}</span>
      </el-col>
      <el-col :span="8">
        <span class="info_span to_right">特殊案件：</span><span class="info_span">{{ selectDictLabel(special_caseOptions, baseInfo.speccasetype) }}</span>
      </el-col>
    </el-row>
  </el-card>
</template>
<script>
  let dictss = [{dictType: 'claimType'}, {dictType: 'insurance_type'}, {dictType: 'special_case'},{dictType: 'currency'},]
  import {getHospitalInfo} from '@/api/claim/handleCom'
  export default {
    props: {
      baseInfo:Object
    },
    data() {
      return {
        /*    baseInfo:{
              submitdate:undefined,
              claimtype:undefined,
              conttype:undefined,
              hospitalcode:undefined,
              casenum:undefined,
              batchtotal:undefined,
            },*/
        dictList:[],
        claimTypeOptions:[],
        insurance_typeOptions:[],
        special_caseOptions:[],
        hospitalOptions:[],
        currencyOptions:[],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claimTypeOptions = this.dictList.find(item => {
        return item.dictType === 'claimType'
      }).dictDate
      this.insurance_typeOptions = this.dictList.find(item => {
        return item.dictType === 'insurance_type'
      }).dictDate
      this.special_caseOptions = this.dictList.find(item => {
        return item.dictType === 'special_case'
      }).dictDate
      this.currencyOptions = this.dictList.find(item => {
        return item.dictType === 'currency'
      }).dictDate
      getHospitalInfo({}).then(res => {
        if (res != null && res !== '') {
          this.hospitalOptions = res.rows

        }
      })
    },
    methods:{
      selectHospitalName(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
          if (datas[key].providerCode === ('' + value)) {
            actions.push(datas[key].chname1);
            actions.push('|');
            actions.push(datas[key].enname1);
            return true;
          }
        })
        return actions.join('');
      },
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

  .el-tree ::v-deep .el-tree-node__content {
    height: 30px;
  }

  .check_box {
    margin-top: 16px;
  }

  .collapse-card.el-card ::v-deep .el-card__body {
    padding: 0;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    background-color: #f8f8f8;
    padding: 0 10px;
  }

  .el-collapse ::v-deep .el-collapse-item__wrap {
    padding: 0 10px;
  }

  .el-checkbox {
    margin-left: 20px;
  }

  .el-checkbox-group {
    margin-left: 20px;
  }

  .el-checkbox-group ::v-deep .el-checkbox {
    display: block;
  }
  ::v-deep.to_right {
    width: 130px;
    text-align: right;
  }

  ::v-deep .custom-tree-node {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }

  ::v-deep .custom-tree-node span {
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
  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
</style>
