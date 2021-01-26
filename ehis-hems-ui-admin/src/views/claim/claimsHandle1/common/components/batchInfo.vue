<template>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>批次信息</span>
      </div>
      <el-row v-loading="dutyLoading" style="margin: 0 40px;padding-bottom: 20px">
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
          <span class="info_span to_right">就诊医院：</span><span class="info_span">{{ baseInfo.hospitalcode }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">案件数：</span><span class="info_span">{{ baseInfo.casenum }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">批次总金额：</span><span class="info_span">{{ baseInfo.batchtotal }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">特殊案件：</span><span class="info_span">{{ baseInfo.speccasetype }}</span>
        </el-col>
      </el-row>
    </el-card>
</template>
<script>
  let dictss = [{dictType: 'claimType'}, {dictType: 'insurance_type'},]
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
        dutyLoading:false,
        dictList:[],
        claimTypeOptions:[],
        insurance_typeOptions:[],
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
    },
    methods:{

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
    width: 100px;
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
