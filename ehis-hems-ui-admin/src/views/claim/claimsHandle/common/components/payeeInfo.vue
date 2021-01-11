<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <el-button type="primary" :disabled="status == 'show'" size="mini"  @click="openBeneficDia" style="float:right;position:absolute;top:8px;right:30px">受益人信息</el-button>
        <template slot="title">
          <span style="font-size:16px;color:black">领款人信息</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <el-form
          style="padding-bottom: 30px;"
          :model="payeeInfo"
          :disabled="(status==='show' || node ==='physical')?true:false"
          ref="payeeInfoForm"
          size="mini"
          :rules="payeeInfoRules">
          <el-table
            :data="Array.isArray(payeeInfo.data)?payeeInfo.data:[]"
            size="small"
            highlight-current-row
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            tooltip-effect="dark"
            style="width: 100%">
            <el-table-column align="center" label="序号" type="index" width="50"></el-table-column>
            <el-table-column align="center" label="分配比例（%）" width="140">
              <template slot-scope="scope">
                <el-form-item
                  :prop="'data.' + scope.$index + '.payoutratio'"
                  :rules="payeeInfoRules.payoutratio">
                  <el-input v-model="scope.row.payoutratio" placeholder="请输入" size="mini"/>
                  <!-- <el-input-number style="width: 130px;" v-model="scope.row.payoutratio" :precision="2"></el-input-number> -->
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column align="center" label="金额" width="140">
              <template slot-scope="scope">
                <el-form-item
                  v-if="isAppeal"
                  :prop="'data.' + scope.$index + '.payamount'"
                  :rules="payeeInfoRules.payamount">
                  <!-- <el-input :disabled="scope.row.payamountDisabled" v-model="scope.row.payamount" placeholder="请输入" size="mini"/> -->
                  <el-input-number style="width: 130px;" v-model="scope.row.payamount" :precision="2"
                    @change="val=>payamountChange(val, scope.$index)"></el-input-number>
                </el-form-item>
                <el-form-item
                  v-else
                  :prop="'data.' + scope.$index + '.payamount'"
                  :rules="payeeInfoRules.payamount">
                  <!-- <el-input :disabled="scope.row.payamountDisabled" v-model="scope.row.payamount" placeholder="请输入" size="mini"/> -->
                  <el-input-number style="width: 130px;" v-model="scope.row.payamount" :min="0" :precision="2"
                                   @change="val=>payamountChange(val, scope.$index)"></el-input-number>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column align="center" label="领款方式" prop="payeetype">
              <template slot-scope="scope">
                {{ /*dict.label.collectedmode[scope.row.payeetype]*/ getCollectedmodeOptions(scope.row.payeetype)}}
              </template>
            </el-table-column>
            <el-table-column align="center" label="与被保人关系" prop="relationship" width="120">
              <template slot-scope="scope">
                {{ /*dict.label.relation_ship_apply[scope.row.relationship]*/ getRelation_ship_applyOptions( scope.row.relationship)}}
              </template>
            </el-table-column>
            <el-table-column align="center" label="领款人" prop="payee"></el-table-column>
            <el-table-column align="center" label="手机" width="100" prop="telephone"></el-table-column>
            <el-table-column align="center" label="银行名称" width="160" prop="bankname"></el-table-column>
            <el-table-column align="center" label="账户" width="160" prop="accountno"></el-table-column>
            <el-table-column align="center" label="证件类型" prop="idtype">
              <template slot-scope="scope">
                {{ /*dict.label.applicant_idtype[scope.row.idtype]*/getapplicant_idtypeOptions(scope.row.idtype) }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="证件号码" width="150" prop="idcardno"></el-table-column>
            <el-table-column align="center" min-width="220" label="证件有效期" prop="longterm"></el-table-column>
            <el-table-column align="center" label="国籍" prop="nationality">
              <template slot-scope="scope">
                {{ /*dict.label.nativeplace[scope.row.nationality]*/getNativeplaceOptions(scope.row.nationality) }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="职业" prop="occupation">
              <template slot-scope="scope">
                {{ /*dict.label.payee_occupation[scope.row.occupation]*/getPayee_occupationOptions( scope.row.occupation) }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="详细地址" width="160" prop="address"></el-table-column>
            <el-table-column align="center" label="操作" fixed="right">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button type="text" size="mini" @click="savePayeeInfo(scope.row)">保存</el-button>
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <beneficiary v-model="beneficVis" :node="node" :claimno="claimno" :insured-info="insuredInfo"></beneficiary>
  </el-card>
</template>
<script>
import { savePayeeInfo,calculationPayAmount } from "@/api/claim/handleDeal";
import Beneficiary from "../modul/beneficiary";
export default {
  components: {Beneficiary},
  //dicts: ['applicant_idtype', 'collectedmode', 'relation_ship_apply', 'nativeplace', 'payee_occupation'],
  inject: ['getPayeeInfo'],
  props: {
    dictList:Array,
    status: String,
    node: String,
    claimno: String,
    isAppeal: {
      type: Boolean,
      default: function() {
        return false
      }
    },
    payeeInfo: {
      type: Object,
      default: function() {
        return {};
      }
    },
    insuredInfo: Object,
    finalamount: String
  },
  watch:{
    finalamount(val, oldVal){
      if(val){
        this.$refs.payeeInfoForm.clearValidate()
        if(this.payeeInfo.data.length){
          this.payeeInfo.data.map((item, i) => {
            if(item.payoutratio){
              this.calculationPayAmount(item.payoutratio, i)
            } else {
              this.$set(this.payeeInfo.data[i],'payamount',null)
            }
          })
        }
      }
    }
  },
  data() {
    //校验金额（数字 最多三位小数）
    console.log(11111,this.isAppeal)
    const isAmount = (rule, value, callback) => {
      const index = rule.field.replace("data.", "").replace(".payamount", "")
      // this.$set(this.payeeInfo.data[index],'payoutratio',null)
      if(this.finalamount){
        if(this.amountFlag){
          callback()
        } else {
          if ((value==null||value=='')&&value!==0) {
            callback(new Error("金额必填"))
          } else {
            callback()
          }
        }
      } else {
        this.$set(this.payeeInfo.data[index],'payamount',null)
        callback(new Error("请先录入实赔金额"))
      }
    }
    //比例（0-100 最多一位小数）
    const isRatio = (rule, value, callback) => {
      const index = rule.field.replace("data.", "").replace(".payoutratio", "")
      // this.$set(this.payeeInfo.data[index],'payamount',null)
      if(this.finalamount){
        if (value) {
          if (value > 100 || value < 0) {
            callback(new Error("输入0-100之间的数字"))
          } else {
            const regx = /^(\d+|\d+\.\d{1,2})$/
            if (!regx.test(value)) {
              callback(new Error("最多保留两位小数"))
            } else {
              callback()
              this.$refs.payeeInfoForm.clearValidate()
              this.calculationPayAmount(this.payeeInfo.data[index].payoutratio,index)
            }
          }
        } else {
          callback()
        }
      } else {
        this.$set(this.payeeInfo.data[index],'payoutratio',null)
        callback(new Error("请先录入实赔金额"))
      }
    }
    return {
      activeNames: ["1"],
      amountFlag: false,
      beneficVis: false,
      //赔付信息规则
      payeeInfoRules: {
        payamount: [{ validator: isAmount, trigger: ["blur","change"], required: true }], //实赔金额
        payoutratio: [{ validator: isRatio, trigger: "blur", required: false }] //赔付结论
      },
      /*'applicant_idtype', 'collectedmode', 'relation_ship_apply', 'nativeplace', 'payee_occupation'*/
      applicant_idtypeOptions: [],
      collectedmodeOptions: [],
      relation_ship_applyOptions: [],
      nativeplaceOptions: [],
      payee_occupationOptions: []
    };
  },
  mounted() {
    if (this.dictList!=null && this.dictList.length!=0){
      this.applicant_idtypeOptions=this.dictList.find(item=>{
        return item.dictType=='applicant_idtype'
      }).dictDate
      this.collectedmodeOptions=this.dictList.find(item=>{
        return item.dictType=='collectedmode'
      }).dictDate
      this.relation_ship_applyOptions=this.dictList.find(item=>{
        return item.dictType=='relation_ship_apply'
      }).dictDate
      this.nativeplaceOptions=this.dictList.find(item=>{
        return item.dictType=='nativeplace'
      }).dictDate
      this.payee_occupation=this.dictList.find(item=>{
        return item.dictType=='payee_occupation'
      }).dictDate
    }
  },
  methods: {

      getCollectedmodeOptions(data) {
      return  this.selectDictLabel(this.collectedmodeOptions,data)
      },
    getRelation_ship_applyOptions(data) {
      return  this.selectDictLabel(this.relation_ship_applyOptions,data)
    },
    getapplicant_idtypeOptions(data) {
      return  this.selectDictLabel(this.applicant_idtypeOptions,data)
    },
    getNativeplaceOptions(data) {
      return  this.selectDictLabel(this.nativeplaceOptions,data)
    },
    getPayee_occupationOptions(data) {
      return  this.selectDictLabel(this.payee_occupationOptions,data)
    },

    openBeneficDia(){
      this.beneficVis=true
    },
    payamountChange(val, index){
      this.$set(this.payeeInfo.data[index],'payoutratio',null)
    },
    payoutratioChange(val, index){
      // this.$set(this.payeeInfo.data[index],'payamount',null)
    },
    calculationPayAmount(payoutratio, index){
      let params = {
        claimno: this.claimno,
        payoutratio
      }
      calculationPayAmount(params).then(res => {
          if (res.status === "1") {
            if(res.data===0){
              this.amountFlag=true
            }
            this.$set(this.payeeInfo.data[index], 'payamount', res.data)
          } else {
            this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: "error"})
          }
        })
    },
    savePayeeInfo(row) {
      this.$refs.payeeInfoForm.validate(valid => {
        if (valid) {
          const params = {
            claimno: this.claimno,
            payeeno: row.payeeno,
            payoutratio: row.payoutratio,
            payamount: row.payamount
          }
          savePayeeInfo(params).then(res => {
              if (res.status === "1") {
                // this.getPayeeInfo()
                this.$message({message: "操作成功",type: "success"})
              } else {
                this.$message({message: res.data.errMsg? res.data.errMsg:'操作失败！', type: "error"})
              }
            })
        } else {
          return false
        }
      })
    }
  }
};
</script>
<style scoped>
.item-width {
  width: 200px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10%;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
