<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;受理信息</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;受理信息</i>
        <span style="float: right;">
        <el-button v-if="status==='edit' && (node==='accept' || node==='calculateReview')" type="primary"
                   :disabled="!collapsed" size="mini" @click="save">保存</el-button>
      </span>
      </div>
    </div>
    <el-form v-show="collapsed" ref="baseForm" :rules="baseFormRule" :model="baseForm" style="padding-bottom: 30px;"
             :disabled="(node === 'accept' && status === 'show') || node==='input' || node==='sport'"
             label-width="150px" label-position="right" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="申请原因：" prop="applyTypes" style="position:relative">
            <el-checkbox-group v-model="baseForm.applyTypes">
              <el-checkbox v-for="option in application_reasonOptions" @change="checkedAllFun" :label="option.dictValue"
                           :key="option.dictValue"
                           :value="option.dictLabel">{{ option.dictLabel }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <!--<el-col :span="8">
          <el-form-item label="索赔金额：" prop="claimAmount">
            <el-input v-model="baseForm.claimAmount" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>-->
        <el-col :span="8">
          <el-form-item label="客户申请日期：" prop="customerApplicationDate">
            <el-date-picker
              v-model="baseForm.customerApplicationDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出险日期：" prop="accDate">
            <el-date-picker
              v-model="baseForm.accDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
      <!--  <el-col :span="8">
          <el-form-item v-if="firstSerIllnessDateShow" label="首次重疾确诊日期：" prop="firstSerIllnessDate">
            <el-date-picker
              v-model="baseForm.firstSerIllnessDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>-->
        <!--<el-col :span="8">
          <el-form-item label="出险类型：" prop="accType">
            <el-select v-model="baseForm.accType" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in incidenttypeOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="事故者现状：" prop="currSituation">
            <el-select v-model="baseForm.currSituation" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in current_stateOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>-->
        <el-col :span="8">
          <el-form-item label="死亡时间：" prop="deathDate">
            <el-date-picker
              v-model="baseForm.deathDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否预售权：" prop="preAuthFlag">
            <el-select v-model="baseForm.preAuthFlag" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in preAuthFlagOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="预授权项目：" v-if="baseForm.preAuthFlag==='01'" prop="preAuthItem">
            <el-input v-model="baseForm.preAuthItem" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="残疾确诊日期：" v-if="disabilityDateShow" prop="disabilityDate">
            <el-date-picker
              v-model="baseForm.disabilityDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="预授权号：" v-if="baseForm.preAuthFlag==='01'" prop="preAuthNo">
            <el-input v-model="baseForm.preAuthNo" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="材料齐全日期：" prop="materialCompleteDate">
            <el-date-picker
              v-model="baseForm.materialCompleteDate"
              :disabled="node==='accept' || node==='input' "
              class="item-width"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="公司签收日期：" prop="receiptDate">
            <el-date-picker
              v-model="baseForm.receiptDate"
              class="item-width"
              type="date"
              clearable
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="优先原因：" prop="priReason">
            <el-select v-model="baseForm.priReason" class="item-width" placeholder="请选择" clearable>
              <el-option v-for="option in priority_reasonOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="事故地点：">
            <el-cascader :options="regions" v-model="region" class="item-width" placeholder="请选择"
                         @change="handleChange"/>
            <el-input v-model="baseForm.accAddress" show-word-limit maxlength="100" style="width:60%;" clearable
                      size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="事故经过：" prop="accDescribe">
            <el-input type="textarea" style="width: 635px" :rows="4" maxlength="1000" v-model="baseForm.accDescribe"
                      clearable/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
  import {getAddress} from '@/api/supplierManager/supplier'
  import {addAccept} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'application_reason'}, {dictType: 'preAuthFlag'}, {dictType: 'priority_reason'},
    {dictType: 'accident_status'}, {dictType: 'current_state'}, {dictType: 'incidenttype'},]
  export default {
    mixins: [],
    props: {
      fixInfo: Object,
      baseInfo: Object,
      isSave: Boolean,
      claimtype: String,
      status: String,
      node: String,
      sonAcceptInfoData: Object,
    },
    watch: {
      baseInfo:function(newVal){

      },
      sonAcceptInfoData: function (newVal) {
        this.getAddressData()
        if (newVal !== null && newVal !== undefined) {
          if (newVal.claimCaseAccept != null) {
            this.baseForm = newVal.claimCaseAccept
            if (newVal.applyTypes!=null && newVal.applyTypes.length>0){
              this.$set(this.baseForm, 'applyTypes', newVal.applyTypes)
            }else {
              this.$set(this.baseForm, 'applyTypes', [])
            }
            this.region[0] = newVal.claimCaseAccept.accProvince
            this.region[1] = newVal.claimCaseAccept.accCity
            this.region[2] = newVal.claimCaseAccept.accDistrict
            if (this.baseForm.rptNo !== null && this.baseForm.rptNo !== '') {
              this.hasAcceptId = true
            }
          }else {
            this.baseForm.priReason=this.baseInfo.prireason
          }
          this.checkedAllFun()
          let date = new Date()
          let mon = date.getMonth() + 1
          let day = date.getDate()
          if ((date.getMonth() + 1) < 10) {
            mon = '0' + (date.getMonth() + 1)
          }
          if ((date.getDate()) < 10) {
            day = '0' + date.getDate()
          }
          if (newVal.claimCaseAccept!=null && (newVal.claimCaseAccept.materialCompleteDate == null || newVal.claimCaseAccept.materialCompleteDate === '')) {
            this.baseForm.materialCompleteDate = date.getFullYear() + "-" + mon + "-" + day
          }
        }
      }
    },
    components: {},
    filters: {},
    data() {
     /* const checkClaimAmount = (rule, value, callback) => {
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (value) {
          if (value < 0) {
            callback(new Error("索赔金额录入不合法，请检查"))
            return this.$message.warning(
              "索赔金额录入不合法，请检查！"
            )
          } else if (!regx.test(value)) {
            callback(new Error("索赔金额录入不合法，请检查"));
            return this.$message.warning(
              "索赔金额录入不合法，请检查！"
            )
          } else {
            callback();
          }
        } else {
          callback();
        }
      }*/
      const checkDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (!value) {
          callback(new Error("请录入出险日期！"));
        } else {
          if (value > date1) {
            callback(new Error("出险日期有误，请重新录入！"));
          } else {
            callback()
          }
        }
      }
      const checkFirstSerIllnessDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (!value) {
          callback(new Error("请录入首次重疾确诊日期！"));
        } else {
          if (value > date1) {
            callback(new Error("首次重疾确诊日期有误，请重新录入！"));
          } else {
            callback()
          }
        }
      }
      const checkDeathDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (value) {
          if (value > date1) {
            callback(new Error("死亡日期有误，请重新录入！"));
          } else {
            callback()
          }
        } else {
          if (this.baseForm.currSituation==='01'){
            callback(new Error("请录入死亡时间！"));
          }else {
            callback()
          }
        }
      }
      const checkDisabilityDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (value) {
          if (value > date1) {
            callback(new Error("残疾确诊日期有误，请重新录入！"));
          } else {
            callback()
          }
        } else {
          callback(new Error("请录入残疾确诊日期！"))
        }
      }
      const checkReceiptDate = (rule, value, callback) => {
        let date = new Date();
        let month = date.getMonth() + 1
        let day = date.getDate()
        if (month < 10) {
          month = '0' + month
        }
        if (day < 10) {
          day = '0' + day
        }
        let date1 = date.getFullYear() + "-" + month + "-" + day;
        if (value) {
          if (value > date1) {
            callback(new Error("公司签收日期有误，请重新录入！"));
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        hasAcceptId: false,
        isAcceptInfoSave: false,
        baseInfoData: {},
        firstSerIllnessDateShow: false,
        disabilityDateShow: false,
        collapsed: true,
        baseForm: {
          applyTypes: [],
          rptNo: undefined,
          accDate: undefined,
          customerApplicationDate: undefined,
          firstSerIllnessDate: undefined,
          accType: undefined,
          currSituation: undefined,
          deathDate: undefined,
          preAuthFlag: undefined,
          preAuthItem: undefined,
          disabilityDate: undefined,
          preAuthNo: undefined,
          materialCompleteDate: new Date(),
          receiptDate: undefined,
          priReason: undefined,
          accProvince: undefined,
          accCity: undefined,
          accDistrict: undefined,
          accAddress: undefined,
          accDescribe: undefined,
        },
        regions: [],
        region: [],
        baseFormRule: {
          applyTypes:[{required: true, message: '请选择申请原因', trigger: ['blur','change']}],
          accDate: [{validator: checkDate, required: true, trigger: ['blur','change']}],
          firstSerIllnessDate: [{validator: checkFirstSerIllnessDate, required: true, trigger: ['blur','change']}],
          currSituation: [{required: true, message: '请选择事故者现状', trigger: ['blur','change']}],
          deathDate: [{validator: checkDeathDate, trigger: ['blur','change']}],
          disabilityDate: [{validator: checkDisabilityDate, required: true, trigger: ['blur','change']}],
          preAuthItem: [{required: true, message: '请录入预授权项目', trigger: ['blur','change']}],
          accType: [{required: true, message: '请选择出险类型', trigger: ['blur','change']}],
          preAuthNo: [{required: true, message: '请录入预授权号', trigger: ['blur','change']}],
          receiptDate: [{validator: checkReceiptDate,  trigger: ['blur','change']}],
        },
        dictList: [],
        application_reasonOptions: [],
        preAuthFlagOptions: [],
        priority_reasonOptions: [],
        accident_statusOptions: [],
        current_stateOptions: [],
        incidenttypeOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.preAuthFlagOptions = this.dictList.find(item => {
        return item.dictType === 'preAuthFlag'
      }).dictDate
      this.application_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'application_reason'
      }).dictDate
      this.priority_reasonOptions = this.dictList.find(item => {
        return item.dictType === 'priority_reason'
      }).dictDate
      this.accident_statusOptions = this.dictList.find(item => {
        return item.dictType === 'accident_status'
      }).dictDate
      this.current_stateOptions = this.dictList.find(item => {
        return item.dictType === 'current_state'
      }).dictDate
      this.incidenttypeOptions = this.dictList.find(item => {
        return item.dictType === 'incidenttype'
      }).dictDate

    },

    methods: {
      // 地址下拉选
      getAddressData() {
        getAddress().then(response => {
          this.regions = response
        }).catch(error => {
          console.log(error)
        })

      },
      handleChange(value) {
        this.baseForm.accProvince = value[0]
        this.baseForm.accCity = value[1]
        this.baseForm.accDistrict = value[2]
      },
      checkedAllFun() {
        this.firstSerIllnessDateShow = false
        this.disabilityDateShow = false
        this.baseForm.applyTypes.forEach(item => {
          if (item === '03') {//重大疾病03
            this.firstSerIllnessDateShow = true
          }
          if (item === '04' || item === '05') {//疾病残疾 //意外残疾
            this.disabilityDateShow = true
          }
        })
      },
      save() {
        this.$emit('getClaimType')
        //被保险人信息未保存，请保存后重新操作
        //理赔类型为事后时，如果申请信息必录字段存在为空的情况，则阻断提示“申请信息录入不完整，请检查”，同时对应的字段框红显示；
        //录入的索赔金额不为大于0的数字或小数位大于两位，则阻断提示“索赔金额录入不合法，请检查”；
        let data = {
          applyTypes: this.baseForm.applyTypes,
          claimCaseAccept: {
            rptNo: this.fixInfo.rptNo,
            accDate: this.baseForm.accDate,
            customerApplicationDate: this.baseForm.customerApplicationDate,
            firstSerIllnessDate: this.baseForm.firstSerIllnessDate,
            accType: this.baseForm.accType,
            currSituation: this.baseForm.currSituation,
            deathDate: this.baseForm.deathDate,
            preAuthFlag: this.baseForm.preAuthFlag,
            preAuthItem: this.baseForm.preAuthItem,
            disabilityDate: this.baseForm.disabilityDate,
            preAuthNo: this.baseForm.preAuthNo,
            materialCompleteDate: this.baseForm.materialCompleteDate,
            receiptDate: this.baseForm.receiptDate,
            priReason: this.baseForm.priReason,
            accProvince: this.region[0],
            accCity: this.region[1],
            accDistrict: this.region[2],
            accAddress: this.baseForm.accAddress,
            accDescribe: this.baseForm.accDescribe,
          }
        }
        if (this.isSave) {
          if (this.baseInfo.claimtype === '02') {
            this.$refs.baseForm.validate((valid) => {
              if (valid) {
                addAccept(data).then(res => {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$emit("refresh-item", 'discussions')
                  this.isAcceptInfoSave = true
                }).catch(res => {
                  this.$message({
                    message: '保存失败!',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                })
              } else {
                return this.$message.warning(
                  "申请信息录入不完整，请检查！"
                )
              }
            })

          } else if (this.baseInfo.claimtype === '01') {
            this.$refs.baseForm.validate((valid) => {
              if (valid) {
                addAccept(data).then(res => {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.$emit("refresh-item", 'discussions')
                  this.isAcceptInfoSave = true
                }).catch(res => {
                  this.$message({
                    message: '保存失败!',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                })
              }else {
                return this.$message.warning(
                  "申请信息录入不完整，请检查！"
                )
              }
            })
          }
        } else {
          return this.$message.warning(
            "被保险人信息未保存，请保存后重新操作！"
          )
        }
      }

    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }

  .el-form ::v-deep .el-form-item__label {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .read-only.el-cascader ::v-deep .el-input.is-disabled .el-input__inner {
    border: none;
    color: #333;
    padding: 0;
    background: transparent;
    width: 100%;
  }

  .read-only.el-cascader ::v-deep .el-icon-arrow-down:before {
    display: none;
  }

  .long-select {
    width: 100%;
  }
</style>
