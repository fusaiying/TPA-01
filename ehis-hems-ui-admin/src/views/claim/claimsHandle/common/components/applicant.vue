<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;申请人信息</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;申请人信息</i>
        <span style="float: right;">
        <el-button v-if="status==='edit' && (node==='accept' || node==='calculateReview') " type="primary" :disabled="!collapsed"
                   :loading="saveHandleLoading" size="mini"
                   @click="saveHandle">保存</el-button>
      </span>
      </div>
    </div>
    <el-form v-show="collapsed" ref="baseForm" :rules="baseFormRule"
             :disabled="(node === 'accept' && status === 'show') || node==='input'  || node==='sport' "
             :model="baseForm" style="padding-bottom: 30px;" label-width="136px" label-position="right" size="mini">
      <el-row>
        <el-col :span="8">
          <el-form-item label="与被保人关系：" prop="relationIns">
            <el-select v-model="baseForm.relationIns" class="item-width" placeholder="请选择" @change="changeInfo">
              <el-option v-for="option in relation_ship_applyOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="申请人姓名：" prop="rgtName">
            <el-input v-model="baseForm.rgtName" class="item-width" clearable size="mini" placeholder="请输入" :disabled="relationsFlag"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：" prop="rgtBirthday">
            <el-date-picker
              :disabled="relationsFlag"
              v-model="baseForm.rgtBirthday"
              class="item-width"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：" prop="rgtIdType">
            <el-select v-model="baseForm.rgtIdType" class="item-width" placeholder="请选择" :disabled="relationsFlag">
              <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码：" prop="rgtIdNo">
            <el-input v-model="baseForm.rgtIdNo" class="item-width" clearable size="mini" placeholder="请输入" :disabled="relationsFlag"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：" prop="rgtSex">
            <el-select v-model="baseForm.rgtSex" class="item-width" placeholder="请选择" :disabled="relationsFlag">
              <el-option v-for="option in rgtSexOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话：" prop="rgtPhone">
            <el-input v-model="baseForm.rgtPhone" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机：" prop="rgtMobile">
            <el-input v-model="baseForm.rgtMobile" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱：" prop="rgtEmail">
            <el-input v-model="baseForm.rgtEmail" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="地址：">
            <el-cascader
              v-model="region"
              :options="regions"
              class="item-width"
              clearable
              filterable
              @change="handleChange"/>
            <el-input v-model="baseForm.rgtAddress" show-word-limit maxlength="400" style="width:60%;" clearable
                      size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>
<script>
  import {getBankLike} from '@/api/claim/input'
  import moment from 'moment'
  import {validEmail, validPhone} from '@/utils/validate'
  import {getAddress} from '@/api/supplierManager/supplier'
  import {addInsuredAndPolicy, addRegister} from '@/api/claim/handleCom'

  let dictss = [{dictType: 'relation_ship_apply'}, {dictType: 'card_type'}, {dictType: 'rgtSex'},]
  export default {
    mixins: [],
    props: {
      isSave:Boolean,
      baseInfo:Object,
      status: String,
      sonRegisterData: Object,
      node: String,
      claimno: String,
      insuredNo: String,
      insuredSave: Boolean,
      applicantData: {
        type: Object,
        default: function () {
          return {}
        }
      },
      fixInfo: {
        type: Object,
        default: function () {
          return {}
        }
      },
      acceptFlag: {
        type: Boolean,
        default: false
      },
      source: String
    },

    components: {},
    filters: {},
    watch: {
      applicantData: function (newValue) {
        this.batchInfo = newValue

        this.setInfo()
      },
      baseInfo: function (newValue) {
        if (newValue.claimtype === '01'){
          this.collapsed=false
        }
      },
      fixInfo: function (newVal) {
        this.copyFixInfo = newVal
      },
      sonRegisterData: function (newVal) {
        getAddress().then(response => {
          this.regions = response
        }).catch(error => {

        })
        if (newVal !== null && newVal !== undefined) {
          let regionArr=[]
          regionArr[0] = newVal.rgtProvince
          regionArr[1] = newVal.rgtCity
          regionArr[2] = newVal.rgtDistrict
          this.baseForm = newVal
          console.log(regionArr)

          // this.$set(this,'baseForm',newVal)
          this.$set(this,'region',regionArr)

          if (this.baseForm.rptNo!==null && this.baseForm.rptNo!=='' ){
            this.hasApplicantId=true
          }
        }
        if(this.sonRegisterData!=null && this.sonRegisterData!=''){
          if(this.sonRegisterData.relationIns==1){
            this.relationsFlag=true
          }
          else {
            this.relationsFlag=false
          }
        }
      }

    },
    data() {
      const validateEmail = (rule, value, callback) => {
        if (value) {
          let str = value.replace(/\s/g, "")
          if (!validEmail(str)) {
            callback(new Error('邮箱格式不合法，请检查'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkPhone = (rule, value, callback) => {
        if (!value) {
          callback()
        } else {
          if (value !== '' && value !== null) {
            const str = value.replace(/\s/g, '')
            if (!validPhone(str)) {
              callback(new Error('手机录入不合法，请检查'))
            } else {
              callback()
            }
          } else {
            callback()
          }
        }
      }
      let checkValid = (rule, value, callback) => {
        if (value) {
          let str = value.replace(/\s/g, "")
          if (!validPhone(str)) {
            if (!/\d{3}-\d{8}|\d{4}-\d{7,8}/.test(str)) {
              callback(new Error('电话录入不合法，请检查'))
            } else {
              callback()
            }
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        relationsFlag: false,
        isApplicantSave: false,
        hasApplicantId: false,
        copyFixInfo: {},
        batchInfo: {},
        collapsed: true,
        beneficVis: false,
        dialogFormVisible: false,
        disableFlag: false,
        showFlag: false,
        tableData: [],

        saveHandleLoading: false,
        baseForm: {
          relationIns: undefined,
          rgtName: undefined,
          rgtBirthday: undefined,
          rgtIdType: undefined,
          rgtIdNo: undefined,
          rgtSex: undefined,
          rgtPhone: undefined,
          rgtMobile: undefined,
          rgtEmail: undefined,
          rgtProvince: undefined,
          rgtCity: undefined,
          rgtDistrict: undefined,
          rgtAddress: undefined,
        },
        baseFormRule: {
          relationIns: [{required: true, message: '与被保人关系不能为空!', trigger: ['change','blur']}],
          rgtName: [{required: true, message: '申请人姓名不能为空!', trigger: ['change','blur']}],
          rgtBirthday: [{required: true, message: '出生日期不能为空!', trigger: ['change','blur']}],
          rgtIdType: [{required: true, message: '证件类型不能为空!', trigger: ['change','blur']}],
          rgtIdNo: [{required: true, message: '证件号码不能为空!', trigger: ['change','blur']}],
          rgtSex: [{required: true, message: '性别不能为空!', trigger: ['change','blur']}],
          rgtPhone: {validator: checkValid, trigger: 'blur'},
          rgtMobile: {validator: checkPhone, trigger: 'blur'},
          rgtEmail: {validator: validateEmail, trigger: 'blur'},

          /*  applyWay: [{ required: true, message: '不能为空!', trigger: 'change' }],
            cardType: [{ required: true, message: '不能为空!', trigger: 'change' }],
            gender: [{ required: true, message: '不能为空!', trigger: 'change' }],
            insuredName: [{ required: true, message: '不能为空!', trigger: 'blur' }],
            reportName: [{ required: true, message: '不能为空!', trigger: 'blur' }],
            idCard: [{ required: true, message: '不能为空!', trigger: 'blur' }],*/

        },
        regions: [],
        region: [],

        loading: false,
        index: 99,
        rowIndex: '',

        dictList: [],
        relation_ship_applyOptions: [],
        card_typeOptions: [],
        rgtSexOptions: [],

        apply_wayOptions: [],
        collect_wayOptions: [],
        apply_sourcetypeOptions: [],
        collectedmodeOptions: [],
        nativeplaceOptions: [],
        payee_occupationOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })

      this.relation_ship_applyOptions = this.dictList.find(item => {
        return item.dictType === 'relation_ship_apply'
      }).dictDate
      this.card_typeOptions = this.dictList.find(item => {
        return item.dictType === 'card_type'
      }).dictDate
      this.rgtSexOptions = this.dictList.find(item => {
        return item.dictType === 'rgtSex'
      }).dictDate

      if (this.batchInfo.claimtype === '01') {//直结
        this.collapsed = false
      } else {//事后

      }
    },

    methods: {
      saveHandle() {
        if (this.isSave){
          this.$refs.baseForm.validate((valid) => {
            if (valid) {
              const subFormSearch = JSON.parse(JSON.stringify(this.baseForm))
              if (this.region) {
                subFormSearch.rgtProvince = this.region[0]
                subFormSearch.rgtDistrict = this.region[2]
                subFormSearch.rgtCity = this.region[1]
              }
              subFormSearch.rptNo = this.copyFixInfo.rptNo
              addRegister(subFormSearch).then(res => {
                if (res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  this.isApplicantSave = true
                } else {
                  this.$message({
                    message: '保存失败!',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                }
              })


            } else {
              this.$message.warning('必录项未必录!')
            }


          })
        }else {
          return this.$message.warning(
            "被保险人信息未保存，请保存后重新操作！"
          )
        }
      },


      handleChange(data) {
        /*if (data !== '' && data !== null) {
          let obj = {};
          obj = this.bankList.find((item) => {//这里的userList就是上面遍历的数据源
            return item.value === data;//筛选出匹配数据
          });
          if (obj === undefined) {
            this.addForm.bankName = data
          } else {
            this.addForm.bankName = obj.label
          }
        } else {
          this.addForm.bankName = ''
        }*/
      }


      ,
      // 申请人选择被保人关系为本人时，自动带入被保人信息
      changeInfo(value) {
        //this.$emit('getApplicantData')
        let applicantData=this.$parent.getApplicantDatas();
        this.batchInfo=applicantData
        if (value === '1') {
          this.baseForm.rgtName = this.batchInfo.name
          this.baseForm.rgtBirthday = this.batchInfo.birthday
          this.baseForm.rgtIdType = this.batchInfo.idType
          this.baseForm.rgtIdNo = this.batchInfo.idNo
          this.baseForm.rgtSex = this.batchInfo.sex
          this.baseForm.rgtPhone = this.batchInfo.phone
          this.baseForm.rgtMobile = this.batchInfo.mobile
          this.baseForm.rgtEmail = this.batchInfo.email
          this.baseForm.rgtProvince = this.batchInfo.province
          this.baseForm.rgtCity = this.batchInfo.city
          this.baseForm.rgtDistrict = this.batchInfo.district
          this.baseForm.rgtAddress = this.batchInfo.address

          /*  this.region[0] = this.batchInfo.province
            this.region[1] = this.batchInfo.city
            this.region[2] = this.batchInfo.district*/
          let regionArr=[]
          regionArr[0] = this.batchInfo.province
          regionArr[1] = this.batchInfo.city
          regionArr[2] = this.batchInfo.district


          // this.$set(this,'baseForm',newVal)
          this.$set(this,'region',regionArr)
          this.relationsFlag=true


          /*  this.region.push(this.batchInfo.province)
            this.region.push(this.batchInfo.city)
            this.region.push(this.batchInfo.district)*/
        } else {
          this.baseForm.rgtName = undefined
          this.baseForm.rgtBirthday = undefined
          this.baseForm.rgtIdType = undefined
          this.baseForm.rgtIdNo = undefined
          this.baseForm.rgtSex = undefined
          this.baseForm.rgtPhone = undefined
          this.baseForm.rgtMobile = undefined
          this.baseForm.rgtEmail = undefined
          this.baseForm.rgtProvince = undefined
          this.baseForm.rgtCity = undefined
          this.baseForm.rgtDistrict = undefined
          this.baseForm.rgtAddress = undefined
          this.region = []
          this.relationsFlag=false
        }
      }
      ,
      //自动带入被保人信息
      setInfo() {
        if (this.baseForm.relationIns === '1') {

          this.baseForm.rgtName = this.batchInfo.name
          this.baseForm.rgtBirthday = this.batchInfo.birthday
          this.baseForm.rgtIdType = this.batchInfo.idType
          this.baseForm.rgtIdNo = this.batchInfo.idNo
          this.baseForm.rgtSex = this.batchInfo.sex
          this.baseForm.rgtPhone = this.batchInfo.phone
          this.baseForm.rgtMobile = this.batchInfo.mobile
          this.baseForm.rgtEmail = this.batchInfo.email
          this.baseForm.rgtProvince = this.batchInfo.province
          this.baseForm.rgtCity = this.batchInfo.city
          this.baseForm.rgtDistrict = this.batchInfo.district
          this.baseForm.rgtAddress = this.batchInfo.address
          this.region.push(this.batchInfo.province)
          this.region.push(this.batchInfo.city)
          this.region.push(this.batchInfo.district)
        } else {

          this.baseForm.rgtName = undefined
          this.baseForm.rgtBirthday = undefined
          this.baseForm.rgtIdType = undefined
          this.baseForm.rgtIdNo = undefined
          this.baseForm.rgtSex = undefined
          this.baseForm.rgtPhone = undefined
          this.baseForm.rgtMobile = undefined
          this.baseForm.rgtEmail = undefined
          this.baseForm.rgtProvince = undefined
          this.baseForm.rgtCity = undefined
          this.baseForm.rgtDistrict = undefined
          this.baseForm.rgtAddress = undefined
          this.region = []
        }
      },


      getCNCollect(row) {
        //return this.dict.label.collectedmode[row.payType]
        return this.selectDictLabel(this.collectedmodeOptions, row.payType)
      }
      ,
      getCNRelation(row) {
        //return this.dict.label.relation_ship_apply[row.relation]
        return this.selectDictLabel(this.relation_ship_applyOptions, row.relation)
      }
      ,
      getCNNative(row) {
        //return this.dict.label.nativeplace[row.nation]
        return this.selectDictLabel(this.nativeplaceOptions, row.nation)
      }
      ,
      getCNOccupation(row) {
        //return this.dict.label.payee_occupation[row.occupation]
        return this.selectDictLabel(this.payee_occupationOptions, row.occupation)
      }
      ,
      getCNIdType(row) {
        //return this.dict.label.card_type[row.cardType]
        return this.selectDictLabel(this.card_typeOptions, row.cardType)
      }
      ,

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
