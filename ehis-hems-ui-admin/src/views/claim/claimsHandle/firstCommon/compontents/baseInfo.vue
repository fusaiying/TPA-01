<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>基本信息</span>
      </div>
      <div>
        <el-form :disabled="true" class="basic-box" label-width="100px" label-position="right" size="mini">
          <el-row>
            <!-- 被保人信息 -->
            <el-col :span="12">
              <!-- <el-card style="min-height:180px"> -->
              <el-row style="margin-top:20px">
                <el-col :span="8">
                  <el-form-item label="客户号：">
                    <span class="form-item-span">{{ insuredInfo.customerno }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="被保人姓名：">
                    <span class="form-item-span">{{ insuredInfo.name }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="被保人性别：">
                    <span class="form-item-span">{{ /*dict.label.applicant_sex[insuredInfo.sex]*/getApplicant_sex(insuredInfo.sex)}}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="被保人证件类型：">
                    <span class="form-item-span">{{ /*dict.label.applicant_idtype[insuredInfo.idtype]*/getApplicant_idtype(insuredInfo.idtype)}}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="被保人证件号码：">
                    <span class="form-item-span">{{ insuredInfo.idcardno }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="被保人电话：">
                    <span class="form-item-span">{{ insuredInfo.mobilephone }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="职业：">
                    <span class="form-item-span">{{ /*dict.label.payee_occupation[insuredInfo.occupation]*/getPayee_occupation(insuredInfo.occupation) }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="国籍：">
                    <span class="form-item-span">{{/* dict.label.nativeplace[insuredInfo.nationality]*/getNativeplace(insuredInfo.nationality)}}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="被保人年龄：">
                    <span class="form-item-span">{{ GetAge(insuredInfo.idcardno) }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row style="margin-bottom:10px">
                <el-col :span="24">
                  <el-form-item label="被保人标签：">
                    <el-tag
                      v-for="item in insuredInfo.labelList"
                      :key="item.labelno"
                      :type="item.labeltype==='01'?'success':
                        item.labeltype==='02'?'danger':'warning'"
                      size="small"
                    >
                      <el-tooltip class="item" effect="dark" :content="item.labelcontent" placement="top-start">
                        <span>{{ /*dict.label.label_type[item.labeltype]*/getLabel_type(item.labeltype)}}</span>
                      </el-tooltip>
                    </el-tag>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- </el-card> -->
            </el-col>
            <!-- 申请人信息 -->
            <el-col :span="12" style="border-left:1px solid #dcdcdc">
              <!-- <el-card style="min-height:180px"> -->
              <el-row style="margin-top:20px">
                <el-col :span="8">
                  <el-form-item label="申请人姓名：">
                    <span class="form-item-span">{{ applicationInfo.applicant }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="与被保人关系：">
                    <span class="form-item-span">{{ /*dict.label.relation_ship_apply[applicationInfo.relationship] */getRelation_ship_apply(applicationInfo.relationship)}}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="申请人证件类型：">
                    <span class="form-item-span">{{ /*dict.label.applicant_idtype[applicationInfo.applicantidtype] */getApplicant_idtype(applicationInfo.applicantidtype) }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="申请人证件号码：">
                    <span class="form-item-span">{{ applicationInfo.applicantidcardno }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="申请人电话：">
                    <span class="form-item-span">{{ applicationInfo.applicantmobile }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="申请类别：">
                    <span class="form-item-span">{{ formateAppli(applicationInfo.applicationtype) }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="8">
                  <el-form-item label="申请人邮箱：">
                    <span class="form-item-span">{{ applicationInfo.applicantmail  }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="申请日期：">
                    <span class="form-item-span">{{ applicationInfo.applicationdate  }}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="资料完成日期：">
                    <span class="form-item-span">{{ applicationInfo.completedate }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row style="margin-bottom:10px">
                <el-col :span="24">
                  <el-form-item label="申请原因：">
                    <span class="form-item-span">{{ applicationInfo.reason }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- </el-card> -->
            </el-col>
          </el-row>
        </el-form>
      </div>
      <el-card>
        <div slot="header" class="clearfix">
          <span style="font-szie:12px">领款人信息</span>
          <el-button type="primary" :disabled="status == 'show'" size="mini" @click="openBeneficDia"
                     style="float:right;margin-left:30px">受益人信息
          </el-button>
        </div>
        <el-form
          style="padding: 15px;"
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
                <!-- <el-form-item
                  :prop="'data.' + scope.$index + '.payoutratio'"
                  :rules="payeeInfoRules.payoutratio">
                  <el-input v-model="scope.row.payoutratio" placeholder="请输入" size="mini"/>
                </el-form-item> -->
                <span>{{scope.row.payoutratio}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="金额" width="140">
              <template slot-scope="scope">
                <span>{{scope.row.payamount}}</span>
                <!-- <el-form-item
                  v-if="isAppeal"
                  :prop="'data.' + scope.$index + '.payamount'"
                  :rules="payeeInfoRules.payamount">
                  <el-input-number style="width: 130px;" v-model="scope.row.payamount" :precision="2"
                    @change="val=>payamountChange(val, scope.$index)"></el-input-number>
                </el-form-item>
                <el-form-item
                  v-else
                  :prop="'data.' + scope.$index + '.payamount'"
                  :rules="payeeInfoRules.payamount">
                  <el-input-number style="width: 130px;" v-model="scope.row.payamount" :min="0" :precision="2"
                                  @change="val=>payamountChange(val, scope.$index)"></el-input-number>
                </el-form-item> -->
              </template>
            </el-table-column>
            <el-table-column align="center" label="领款方式" prop="payeetype">
              <template slot-scope="scope">
                {{ /*dict.label.collectedmode[scope.row.payeetype]*/getCollectedmode(scope.row.payeetype)}}
              </template>
            </el-table-column>
            <el-table-column align="center" label="与被保人关系" prop="relationship" width="120">
              <template slot-scope="scope">
                {{/*
                dict.label.relation_ship_apply[scope.row.relationship]*/getRelation_ship_apply(scope.row.relationship)}}
              </template>
            </el-table-column>
            <el-table-column align="center" label="领款人" prop="payee"></el-table-column>
            <el-table-column align="center" label="手机" width="100" prop="telephone"></el-table-column>
            <el-table-column align="center" label="银行名称" width="160" prop="bankname"></el-table-column>
            <el-table-column align="center" label="账户" width="160" prop="accountno"></el-table-column>
            <el-table-column align="center" label="证件类型" prop="idtype">
              <template slot-scope="scope">
                {{ /*dict.label.applicant_idtype[scope.row.idtype]*/getApplicant_idtype(scope.row.idtype)}}
              </template>
            </el-table-column>
            <el-table-column align="center" label="证件号码" width="150" prop="idcardno"></el-table-column>
            <el-table-column align="center" min-width="220" label="证件有效期" prop="longterm"></el-table-column>
            <el-table-column align="center" label="国籍" prop="nationality">
              <template slot-scope="scope">
                {{ /*dict.label.nativeplace[scope.row.nationality]*/getNativeplace(scope.row.nationality)}}
              </template>
            </el-table-column>
            <el-table-column align="center" label="职业" prop="occupation">
              <template slot-scope="scope">
                {{/* dict.label.payee_occupation[scope.row.occupation]*/getPayee_occupation(scope.row.occupation) }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="详细地址" width="160" prop="address"></el-table-column>
            <!-- <el-table-column align="center" label="操作" fixed="right">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button type="text" size="mini" @click="savePayeeInfo(scope.row)">保存</el-button>
                </el-form-item>
              </template>
            </el-table-column> -->
          </el-table>
        </el-form>
        <beneficiary :dictList="dictList" v-model="beneficVis" :node="node" :claimno="claimno" :insured-info="insuredInfo"></beneficiary>
      </el-card>
    </el-card>
  </div>
</template>
<script>
  import {savePayeeInfo, calculationPayAmount} from "@/api/claim/handleDeal";
  import Beneficiary from "../../common/modul/beneficiary";

  export default {
    components: {Beneficiary},
    props: {
      dictList: Array,
      status: String,
      node: String,
      registerNo: String,
      insuredNo: String,
      reportSave: Boolean,
      insuredSave: Boolean,
      claimno: String,
      acceptFlag: {
        type: Boolean,
        default: false
      },
      applicationInfo: Object,
      insuredInfo: Object,
      isAppeal: {
        type: Boolean,
        default: function () {
          return false
        }
      },
      payeeInfo: {
        type: Object,
        default: function () {
          return {};
        }
      },
      finalamount: String
    },
    inject: ['getPayeeInfo'],
    watch: {
      finalamount(val, oldVal) {
        if (val) {
          this.$refs.payeeInfoForm.clearValidate()
          if (this.payeeInfo.data.length) {
            this.payeeInfo.data.map((item, i) => {
              if (item.payoutratio) {
                this.calculationPayAmount(item.payoutratio, i)
              } else {
                this.$set(this.payeeInfo.data[i], 'payamount', null)
              }
            })
          }
        }
      }
    },
     /* dicts: [
        'channel_source', 'relation_ship_apply', 'applicant_idtype', 'apply_type',
        'applicant_sex', 'payment_conclusion', 'apply_sourcetype', 'tasknode', 'examine_conclusion',
        'nativeplace', 'payee_occupation', 'casestep', 'examine_reject', 'conclusionreason',
        'err_type', 'accommodation_type', 'modify_type', 'invest_err_type', 'conclusionreason2','conclusionreason3',
        'conclusionreason4',
        'conclusionreason5','sex', 'apply_type','material_type','label_type','conclusion',
        'applicant_idtype', 'collectedmode', 'relation_ship_apply', 'nativeplace', 'payee_occupation'
      ],*/
    data() {
      //校验金额（数字 最多三位小数）
      const isAmount = (rule, value, callback) => {
        const index = rule.field.replace("data.", "").replace(".payamount", "")
        // this.$set(this.payeeInfo.data[index],'payoutratio',null)
        if (this.finalamount) {
          if (this.amountFlag) {
            callback()
          } else {
            if ((value == null || value == '') && value !== 0) {
              callback(new Error("金额必填"))
            } else {
              callback()
            }
          }
        } else {
          this.$set(this.payeeInfo.data[index], 'payamount', null)
          callback(new Error("请先录入实赔金额"))
        }
      }
      //比例（0-100 最多一位小数）
      const isRatio = (rule, value, callback) => {
        const index = rule.field.replace("data.", "").replace(".payoutratio", "")
        // this.$set(this.payeeInfo.data[index],'payamount',null)
        if (this.finalamount) {
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
                this.calculationPayAmount(this.payeeInfo.data[index].payoutratio, index)
              }
            }
          } else {
            callback()
          }
        } else {
          this.$set(this.payeeInfo.data[index], 'payoutratio', null)
          callback(new Error("请先录入实赔金额"))
        }
      }
      return {
        activeNames: ['1'],
        amountFlag: false,
        beneficVis: false,
        //赔付信息规则
        payeeInfoRules: {
          payamount: [{validator: isAmount, trigger: ["blur", "change"], required: true}], //实赔金额
          payoutratio: [{validator: isRatio, trigger: "blur", required: false}] //赔付结论
        },

        channel_sourceOptions: [],
        relation_ship_applyOptions: [],
        applicant_idtypeOptions: [],
        apply_typeOptions: [],
        applicant_sexOptions: [],
        payment_conclusionOptions: [],
        apply_sourcetypeOptions: [],
        tasknodeOptions: [],
        examine_conclusionOptions: [],
        nativeplaceOptions: [],
        payee_occupationOptions: [],
        casestepOptions: [],
        examine_rejectOptions: [],
        conclusionreasonOptions: [],
        err_typeOptions: [],
        accommodation_typeOptions: [],
        modify_typeOptions: [],
        invest_err_typeOptions: [],
        conclusionreason2Options: [],
        conclusionreason3Options: [],
        conclusionreason4Options: [],
        conclusionreason5Options: [],
        sexOptions: [],
        material_typeOptions: [],
        label_typeOptions: [],
        conclusionOptions: [],
        collectedmodeOptions: []

      }
    },
    mounted() {
      if (this.dictList != null && this.dictList.length != 0) {
        this.channel_sourceOptions = this.dictList.find(item => {
          return item.dictType == 'channel_source'
        }).dictDate
        this.relation_ship_applyOptions = this.dictList.find(item => {
          return item.dictType == 'relation_ship_apply'
        }).dictDate
        this.applicant_idtypeOptions = this.dictList.find(item => {
          return item.dictType == 'applicant_idtype'
        }).dictDate
        this.apply_typeOptions = this.dictList.find(item => {
          return item.dictType == 'apply_type'
        }).dictDate
        this.applicant_sexOptions = this.dictList.find(item => {
          return item.dictType == 'applicant_sex'
        }).dictDate
        this.payment_conclusionOptions = this.dictList.find(item => {
          return item.dictType == 'payment_conclusion'
        }).dictDate
        this.apply_sourcetypeOptions = this.dictList.find(item => {
          return item.dictType == 'apply_sourcetype'
        }).dictDate
        this.tasknodeOptions = this.dictList.find(item => {
          return item.dictType == 'tasknode'
        }).dictDate
        this.examine_conclusionOptions = this.dictList.find(item => {
          return item.dictType == 'examine_conclusion'
        }).dictDate
        this.nativeplaceOptions = this.dictList.find(item => {
          return item.dictType == 'nativeplace'
        }).dictDate
        this.payee_occupationOptions = this.dictList.find(item => {
          return item.dictType == 'payee_occupation'
        }).dictDate
        this.casestepOptions = this.dictList.find(item => {
          return item.dictType == 'casestep'
        }).dictDate
        this.examine_rejectOptions = this.dictList.find(item => {
          return item.dictType == 'examine_reject'
        }).dictDate
        this.conclusionreasonOptions = this.dictList.find(item => {
          return item.dictType == 'conclusionreason'
        }).dictDate
        this.err_typeOptions = this.dictList.find(item => {
          return item.dictType == 'err_type'
        }).dictDate
        this.accommodation_typeOptions = this.dictList.find(item => {
          return item.dictType == 'accommodation_type'
        }).dictDate
        this.modify_typeOptions = this.dictList.find(item => {
          return item.dictType == 'modify_type'
        }).dictDate
        this.invest_err_typeOptions = this.dictList.find(item => {
          return item.dictType == 'invest_err_type'
        }).dictDate
        this.conclusionreason2Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason2'
        }).dictDate
        this.conclusionreason3Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason3'
        }).dictDate
        this.conclusionreason4Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason4'
        }).dictDate
        this.conclusionreason5Options = this.dictList.find(item => {
          return item.dictType == 'conclusionreason5'
        }).dictDate
        this.sexOptions = this.dictList.find(item => {
          return item.dictType == 'sex'
        }).dictDate
        this.material_typeOptions = this.dictList.find(item => {
          return item.dictType == 'material_type'
        }).dictDate
        this.label_typeOptions = this.dictList.find(item => {
          return item.dictType == 'label_type'
        }).dictDate
        this.conclusionOptions = this.dictList.find(item => {
          return item.dictType == 'conclusion'
        }).dictDate
        this.collectedmodeOptions = this.dictList.find(item => {
          return item.dictType == 'collectedmode'
        }).dictDate
      }
    },
    methods: {
      openBeneficDia() {
        this.beneficVis = true
      },
      payamountChange(val, index) {
        this.$set(this.payeeInfo.data[index], 'payoutratio', null)
      },
      payoutratioChange(val, index) {
        // this.$set(this.payeeInfo.data[index],'payamount',null)
      },
      calculationPayAmount(payoutratio, index) {
        let params = {
          claimno: this.claimno,
          payoutratio
        }
        calculationPayAmount(params).then(res => {
          if (res.status === "1") {
            if (res.data === 0) {
              this.amountFlag = true
            }
            this.$set(this.payeeInfo.data[index], 'payamount', res.data)
          } else {
            this.$message({message: res.data.errMsg ? res.data.errMsg : '操作失败！', type: "error"})
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
                this.$message({message: "操作成功", type: "success"})
              } else {
                this.$message({message: res.data.errMsg ? res.data.errMsg : '操作失败！', type: "error"})
              }
            })
          } else {
            return false
          }
        })
      },
      formateAppli: function (val) {
        let str = ''
        if (val) {
          let arrVal = val.split(',')
          arrVal.map(item => {
            if (/*this.dict.label.apply_type[item]*/this.selectDictLabel(this.apply_typeOptions, item)) {
              /*  str += '｜'+this.dict.label.apply_type[item]*/
              str += '｜' + this.selectDictLabel(this.apply_typeOptions, item)
            }
          })
          return str.slice(1)
        } else {
          return val
        }
      },
      GetAge(identityCard) {
        var len = (identityCard + "").length;
        if (len == 0) {
          return 0;
        } else {
          if ((len != 15) && (len != 18)) {//身份证号码只能为15位或18位其它不合法
            return 0;
          }
        }
        var strBirthday = "";
        if (len == 18)//处理18位的身份证号码从号码中得到生日和性别代码
        {
          strBirthday = identityCard.substr(6, 4) + "/" + identityCard.substr(10, 2) + "/" + identityCard.substr(12, 2);
        }
        if (len == 15) {
          strBirthday = "19" + identityCard.substr(6, 2) + "/" + identityCard.substr(8, 2) + "/" + identityCard.substr(10, 2);
        }
        //时间字符串里，必须是“/”
        var birthDate = new Date(strBirthday);
        var nowDateTime = new Date();
        var age = nowDateTime.getFullYear() - birthDate.getFullYear();
        //再考虑月、天的因素;.getMonth()获取的是从0开始的，这里进行比较，不需要加1
        if (nowDateTime.getMonth() < birthDate.getMonth() || (nowDateTime.getMonth() == birthDate.getMonth() && nowDateTime.getDate() < birthDate.getDate())) {
          age--;
        }
        return age;
      },
      getApplicant_sex(value) {
        return this.selectDictLabel(this.applicant_sexOptions, value)
      },
      getApplicant_idtype(value) {
        return this.selectDictLabel(this.applicant_idtypeOptions, value)
      },
      getPayee_occupation(value) {
        return this.selectDictLabel(this.payee_occupationOptions, value)
      },
      getNativeplace(value) {
        return this.selectDictLabel(this.nativeplaceOptions, value)
      },
      getLabel_type(value) {
        return this.selectDictLabel(this.label_typeOptions, value)
      },
      getRelation_ship_apply(value) {
        return this.selectDictLabel(this.relation_ship_applyOptions, value)
      },
      getCollectedmode(value) {
        return this.selectDictLabel(this.collectedmodeOptions, value)
      }
    },
  }
</script>
<style scoped>
  ::v-deep .el-card__body {
    padding: 0;
  }

  ::v-deep .el-form-item__label {
    padding: 0;
  }
</style>
<style scoped>
  .basic-box .el-tag--small {
    margin-right: 10px !important;
  }

  .el-tooltip__popper {
    max-width: 50%;
  }

  .el-autocomplete-suggestion li {
    width: 100%;
    white-space: pre-wrap;
    word-break: break-word;
    height: auto;
    line-height: 18px;
    padding: 0 5px;
  }

  .el-collapse-item__wrap {
    border: none !important;
  }

  .el-collapse {
    border: none;
  }

  .basic-box ::v-deep .el-form-item__label {
    font-size: 12px;
  }

  .el-col-7 ::v-deep .el-form-item {
    margin-bottom: 10px;
  }

  .el-col-12 ::v-deep .el-form-item {
    margin-bottom: 4px;
  }

  .el-col-7 ::v-deep .el-form-item__label,
  .form-item-span {
    font-size: 12px;
  }

  .item-width {
    width: 200px;
  }

  .el-collapse {
    border: none;
  }

  .el-tabs ::v-deep .el-tabs__item {
    width: 10%;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-weight: 360;
    font-size: 14px;
  }
</style>
