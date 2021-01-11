
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">保单信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <!-- <span style="position: absolute;right: 40px;top: 0;" v-if="activetab == '3'">
          <el-button type="primary" size="mini" @click="savePoilcy">保存</el-button>
        </span> -->
        <el-table
          ref="policytable"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="policyInfo"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          @selection-change="handleSelectChange"
          style="width: 100%;">
          <el-table-column type="expand" >
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
                <el-table-column align="center" prop="riskcode" label="险种编码" width="100"/>
                <el-table-column align="center" prop="dutyname" label="责任名称" width="100"/>
                <el-table-column align="center" prop="dutycode" label="责任编码" width="100"/>
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
                   <template slot-scope="scope" >
                     <P v-for="item in scope.row.getdutylist" :key="item.getDutyCode">{{getGet_duty_kind(item.getDutyKind)?getGet_duty_kind(item.getDutyKind):item.getDutyKind}}</P>
                   </template>
                 </el-table-column>-->
              </el-table>
            </template>
          </el-table-column>
          <el-table-column type="selection" width="50" align="center" v-if="activetab == '3'"/>
          <el-table-column align="center" prop="contno" width="140" label="保单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="name" width="90" label="被保人姓名" show-overflow-tooltip/>
          <el-table-column align="center" prop="riskinfo" width="150" label="险种名称"/>
          <el-table-column align="center" prop="effectivedate" label="有效日期" width="160"></el-table-column>
          <el-table-column align="center" :formatter="formateStatus" prop="status" label="保单状态" show-overflow-tooltip/>
          <el-table-column align="center" prop="special" label="特约信息" show-overflow-tooltip/>
          <el-table-column align="center" prop="ismatch" label="是否匹配" show-overflow-tooltip>
            <template slot-scope="scope">
              <span style="font-size: 14px;">
                <i v-if="scope.row.ismatch==='N'" style="color: #F56C6C;" class="el-icon-circle-close"></i>
                <i v-if="scope.row.ismatch==='Y'" style="color: #409EFF;" class="el-icon-circle-check"></i>
              </span>
            </template>
          </el-table-column>
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
import {savePolicyInfo} from '@/api/claim/handleDeal'
/*import { list } from '../../../../api/examine'*/
import bus from '../../bus/bus'
export default {
  //dicts: ['policy_status', 'get_duty_kind'],
  props: {
    dictList: Array,
    status: String,
    node: String,
    policyInfo: {
      type: Array,
      default: {
        return: []
      }
    },
    claimno: String,
    istpolicyListDisablsed: Boolean,
    activetab: String
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
      risklist:[],
      activeNames: ['1'],
      policyList: [],
      dutyList: [],
      hasSelectList: [],
      policy_statusOptions: [],
      get_duty_kindOptions: [],
    }
  },
  mounted() {
    if (this.dictList != null && this.dictList.length != 0) {
      this.policy_statusOptions = this.dictList.find(item => {
        return item.dictType == 'policy_status'
      }).dictDate
      this.get_duty_kindOptions = this.dictList.find(item => {
        return item.dictType == 'get_duty_kind'
      }).dictDate
    }
    this.$nextTick(function() {
      this.policyInfo.map((item) =>{
        if (item.ismatch == 'Y') {
          this.hasSelectList.push(item.ismatch)
          // this.policylist.push(item)
        }
      })
      this.$nextTick(()=>{
        this.policyInfo.forEach(row => {
          if(this.hasSelectList.indexOf(row.ismatch) >= 0){
            this.$refs.policytable.toggleRowSelection(row,true);
            bus.$emit('updatePolicyList',this.policylist)
          }
        })
      })
    })
  },
  updated() {
    this.$nextTick(function() {
      this.policyInfo.map((item) =>{
        if (item.ismatch == 'Y') {
          this.hasSelectList.push(item.ismatch)
          this.policyList.push(item)
        }
      })
      this.$nextTick(()=>{
        this.policyInfo.forEach(row => {
          if(this.hasSelectList.indexOf(row.ismatch) >= 0){
            this.$refs.policytable.toggleRowSelection(row,true);
            bus.$emit('updatePolicyList',this.policyList)
          }
        })
      })
    })
  },
  methods: {
    formateStatus(row) {
      if (row.status){
        //return this.dict.label.policy_status[row.status]
        return this.selectDictLabel(this.policy_statusOptions, row.status)
      } else {
        return row.status
      }
      console.log(this.policyInfo);
    },
    savePoilcy() {
      let params = {}
      params.claimno = this.claimno
      params.policylist = []
      this.policyList.map((item) =>{
        let policydo = {}
        policydo.policyno = item.contno
        let dutylist = []
        item.risklist.map((ele) =>{
          if (item.contno == ele.contno) {
            let duty = {}
            duty.converageno = ele.riskcode
            duty.dutycode = ele.dutycode
            dutylist.push(duty)
          }
        })
        policydo.dutylist = dutylist
        params.policylist.push(policydo)
      })
      savePolicyInfo(params).then(res =>{
        if (res.status == '1') {
          this.$message.success('保存成功！')
          bus.$emit('updateIstpolicyListDisablsed',false)
        } else {
          this.$message({ message: res.data.errMsg, type: 'warning' })
        }
      })
    },
    handleSelectChange(val) {
      this.policyList = val
      bus.$emit('updatePolicyList',this.policyList)
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
    getGet_duty_kind(value){
      return this.selectDictLabel(this.get_duty_kindOptions, value);
    }
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
