
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">保单信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <el-table
          ref="table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="policyInfo"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table
                ref="table"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                :data="props.row.risklist"
                size="small"
                highlight-current-row
                tooltip-effect="dark"
                style="width: 100%;">
                <el-table-column align="center" prop="riskname" label="险种名称" width="100"/>
                <el-table-column align="center" prop="dutyname" label="责任名称" width="100"/>
                <el-table-column align="center" prop="amount" label="责任保额">
                  <template slot-scope="scope">
                    <span>{{scope.row.amount|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="surplusamount" label="剩余保额">
                  <template slot-scope="scope">
                    <span>{{scope.row.surplusamount|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="deductible" label="责任免赔额">
                  <template slot-scope="scope">
                    <span>{{scope.row.deductible|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="surplusdeductible" label="剩余免赔额">
                  <template slot-scope="scope">
                    <span>{{scope.row.surplusdeductible|numFilter}}</span>
                  </template>
                </el-table-column>
                <el-table-column label="给付责任编码" width="130" align="center">
                  <template slot-scope="scope">
                    <P v-for="item in scope.row.getdutylist" :key="item.getDutyCode">{{item.getDutyCode}}</P>
                  </template>
                </el-table-column>
                <el-table-column label="给付责任名称" width="180" align="center">
                  <template slot-scope="scope">
                    <P class="duty-p" v-for="item in scope.row.getdutylist" :key="item.getDutyCode" :title="item.getDutyName">{{item.getDutyName}}</P>
                  </template>
                </el-table-column>
                <el-table-column label="给付责任类型" width="130" align="center">
                  <template slot-scope="scope">
                    <P v-for="item in scope.row.getdutylist" :key="item.getDutyCode">{{/*dict.label.get_duty_kind[item.getDutyKind]*/getDuty_kind(item.getDutyKind)?/*dict.label.get_duty_kind[item.getDutyKind]*/getDuty_kind(item.getDutyKind):item.getDutyKind}}</P>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="contno" width="140" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskinfo" width="150" label="险种名称"/>
          <el-table-column align="center" prop="effectivedate" label="有效日期" width="160" show-overflow-tooltip/>
          <el-table-column align="center" :formatter="formateStatus" prop="status" label="保单状态" show-overflow-tooltip/>
          <el-table-column align="center" prop="special" label="特约信息" show-overflow-tooltip/>
          <el-table-column align="center" prop="isrecuperation" label="是否复效" show-overflow-tooltip>
            <template slot-scope="scope">
              <div v-if="scope.row.isrecuperation==='是'">
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click">
                  <el-table
                    :data="[scope.row]"
                    :header-cell-style="{color:'#555', fontWeight: '400'}"
                    size="mini">
                    <el-table-column property="recuperationdate" label="复效日期"/>
                    <el-table-column property="startdate" label="时效日期"/>
                    <el-table-column property="recuperationnotification" label="复效健康告知"/>
                  </el-table>
                  <el-button slot="reference" type="text" size="mini">{{ scope.row.isrecuperation }}</el-button>
                </el-popover>
              </div>
              <div v-else>{{scope.row.isrecuperation}}</div>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="isrenewal" label="是否续保" show-overflow-tooltip/>
          <el-table-column align="center" prop="socialinsurance" label="社保投保" show-overflow-tooltip/>
          <el-table-column align="center" prop="ismatch" label="是否匹配" show-overflow-tooltip>
            <template slot-scope="scope">
              <span style="font-size: 14px;">
                <i v-if="scope.row.ismatch==='N'" style="color: #F56C6C;" class="el-icon-circle-close"></i>
                <i v-if="scope.row.ismatch==='Y'" style="color: #409EFF;" class="el-icon-circle-check"></i>
              </span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="120" align="center">
            <template slot-scope="scope">
              <!-- <el-button style="margin-bottom: 10px;" type="text" size="mini">预览</el-button> -->
              <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="download(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import { des3Encode } from '@/api/claim/register'
export default {
  //dicts: ['policy_status', 'get_duty_kind'],
  props: {
    dictList:Array,
    status: String,
    node: String,
    policyInfo: {
      type: Array,
      default: {
        return: []
      }
    }
  },
  filters: {
    numFilter(value)
    {
      let realVal = ''
      if (!isNaN(value) && value!== '' && value !== null) {
        // 截取当前数据到小数点后两位
        realVal = parseFloat(value).toFixed(2)
      } else {
        realVal = ''
      }
      return realVal
    }
  },
  data() {
    return {
      activeNames: ['1'],

      policy_statusOptions: [],
      get_duty_kindOptions: []
    }
  },
  mounted() {
    /*'policy_status', 'get_duty_kind'*/
    if (this.dictList!=null && this.dictList.length!=0) {
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
      this.get_duty_kindOptions = this.dictList.find(item => {
        return item.dictType == 'get_duty_kind'
      }).dictDate
    }
  },
  methods: {
    getDuty_kind(value){
      return this.selectDictLabel(this.get_duty_kindOptions, value)
    },
    formateStatus(row) {
      if (row.status){
        /*return this.dict.label.policy_status[row.status]*/
        return  this.selectDictLabel(this.policy_statusOptions, row.status)
      } else {
        return row.status
      }
    },
    download(row) {
      // const url = 'https://m-health.mypicc.com.cn/toDownload?contNo=' + row.contno + '&pdfFlag=download&ip=10.130.129.21'
      // window.open(url)
      // 生产
      const requestData = {
        policyNo: row.contno,
        privateKey: 'piccandthirdpart'
      }
      des3Encode(requestData).then(res => {
        if (res.status === '1') {
          const url = 'http://e-shop.picchealth.com/pdf/checkPDF?contno=' + res.data + '&type=d'
          window.open(url)
        }
      })
    },
  }
}
</script>
<style scoped>
.duty-p {
  width: 170px;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.item-width {
  width: 200px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10%;
}
.el-table /deep/ .el-table__expanded-cell {
  padding: 20px;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
