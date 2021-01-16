<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <div style="width: 100%;cursor: pointer;">
        <i v-show="!collapsed" class="el-icon-arrow-right" @click="collapsed=!collapsed">&nbsp;申请人信息</i>
        <i v-show="collapsed" class="el-icon-arrow-down" @click="collapsed=!collapsed">&nbsp;申请人信息</i>
        <span style="float: right;">
        <el-button type="primary" :disabled="!collapsed" :loading="saveHandleLoading" size="mini"
                   @click="saveHandle">保存</el-button>
      </span>
      </div>
    </div>
    <el-form v-show="collapsed" ref="baseForm" :rules="baseFormRule" :disabled="disableFlag || acceptFlag"
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
            <el-input v-model="baseForm.rgtName" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期：" prop="rgtBirthday">
            <el-input v-model="baseForm.rgtBirthday" class="item-width" clearable size="mini" placeholder="请输入"
                      @input="getGender('baseForm')"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型：" prop="rgtIdType">
            <el-select v-model="baseForm.rgtIdType" class="item-width" placeholder="请选择">
              <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel"
                         :value="option.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码：" prop="rgtIdNo">
            <el-input v-model="baseForm.rgtIdNo" class="item-width" clearable size="mini" placeholder="请输入"
                      @input="getGender('baseForm')"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：" prop="rgtSex">
            <el-select v-model="baseForm.rgtSex" class="item-width" placeholder="请选择">
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
              v-model="baseForm.region"
              :options="regions"
              class="item-width"
              clearable
              filterable
              @change="handleChange"/>
            <el-input v-model="baseForm.rgtAddress" show-word-limit maxlength="100" style="width:60%;" clearable
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
      status: String,
      node: String,
      claimno: String,
      insuredNo: String,
      insuredSave: Boolean,
      applicantData: {
        type: Object,
      default: function (){
        return {}
      }},
      fixInfo: {
        type: Object,
      default: function (){
        return {}
      }},
      acceptFlag: {
        type: Boolean,
        default: false
      },
      source: String
    },

    components: {},
    filters: {},
    watch : {
      applicantData: function(newValue) {
          this.batchInfo = newValue
          console.log(1)
          this.setInfo()
        }

      ,
      fixInfo: function (newVal){

        this.copyFixInfo=newVal

      }

    },
    data() {
      const validateEmail = (rule, value, callback) => {
        if (value) {
          let str = value.replace(/\s/g, "")
          if (!validEmail(str)) {
            callback(new Error('输入格式不正确'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const checkPhone = (rule, value, callback) => {
        if (!value) {
          callback(new Error('不能为空'))
        } else {
          if (value !== '' && value !== null) {
            const str = value.replace(/\s/g, '')
            if (!validPhone(str)) {
              callback(new Error('输入格式不正确'))
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
              callback(new Error('输入格式不正确'))
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
          region: [],
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
           relationIns: [{ required: true, message: '与被保人关系不能为空!', trigger: 'change' }],
          rgtName: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          rgtBirthday: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          rgtIdType: [{ required: true, message: '不能为空!', trigger: 'change' }],
          rgtIdNo: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          rgtSex: [{ required: true, message: '不能为空!', trigger: 'change' }],
          rgtPhone: { validator: checkValid, trigger: 'blur' },
          rgtMobile: {  validator: checkPhone, trigger: 'blur' },
          rgtEmail: { validator: validateEmail, trigger: 'blur' },

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

      getAddress().then(response => {
        this.regions = response
      }).catch(error => {
        console.log(error)
      })

      if (this.batchInfo.claimtype==='01'){//直结
          this.collapsed=false
      }else {//事后

      }
    },

    methods: {

      saveHandle() {
        this.$refs.baseForm.validate((valid) => {
          if(valid){
            const subFormSearch = JSON.parse(JSON.stringify(this.baseForm))
            if (this.baseForm.region) {
              subFormSearch.province = this.baseForm.region[0]
              subFormSearch.district = this.baseForm.region[2]
              subFormSearch.city = this.baseForm.region[1]
            }
            subFormSearch.rptNo=this.copyFixInfo.rptNo
            addRegister(subFormSearch).then(res => {
              if (res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
              } else {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }


            })



          }
          else {
            this.$message.warning('必录项未必录!')
          }


        })
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
        this.$emit('getApplicantData')
        if (value === '1') {
          console.log(2)
          this.baseForm.rgtName= this.batchInfo.name
          this.baseForm.rgtBirthday= this.batchInfo.birthday
          this.baseForm.rgtIdType= this.batchInfo.idType
          this.baseForm.rgtIdNo= this.batchInfo.idNo
          this.baseForm.rgtSex= this.batchInfo.sex
          this.baseForm.rgtPhone= this.batchInfo.phone
          this.baseForm.rgtMobile= this.batchInfo.mobile
          this.baseForm.rgtEmail= this.batchInfo.email
          this.baseForm.rgtProvince= this.batchInfo.province
          this.baseForm.rgtCity= this.batchInfo.city
          this.baseForm.rgtDistrict= this.batchInfo.district
          this.baseForm.rgtAddress= this.batchInfo.address
          this.baseForm.region=this.batchInfo.region
        }
        else {
          console.log(3)
          this.baseForm.rgtName= undefined
          this.baseForm.rgtBirthday= undefined
          this.baseForm.rgtIdType= undefined
          this.baseForm.rgtIdNo= undefined
          this.baseForm.rgtSex= undefined
          this.baseForm.rgtPhone= undefined
          this.baseForm.rgtMobile= undefined
          this.baseForm.rgtEmail= undefined
          this.baseForm.rgtProvince= undefined
          this.baseForm.rgtCity= undefined
          this.baseForm.rgtDistrict= undefined
          this.baseForm.rgtAddress= undefined
          this.baseForm.region=[]
        }
      }
      ,
      //自动带入被保人信息
      setInfo(){
        if (this.baseForm.relationIns === '1') {
          console.log(2)
          this.baseForm.rgtName= this.batchInfo.name
          this.baseForm.rgtBirthday= this.batchInfo.birthday
          this.baseForm.rgtIdType= this.batchInfo.idType
          this.baseForm.rgtIdNo= this.batchInfo.idNo
          this.baseForm.rgtSex= this.batchInfo.sex
          this.baseForm.rgtPhone= this.batchInfo.phone
          this.baseForm.rgtMobile= this.batchInfo.mobile
          this.baseForm.rgtEmail= this.batchInfo.email
          this.baseForm.rgtProvince= this.batchInfo.province
          this.baseForm.rgtCity= this.batchInfo.city
          this.baseForm.rgtDistrict= this.batchInfo.district
          this.baseForm.rgtAddress= this.batchInfo.address
          this.baseForm.region=this.batchInfo.region
        }
        else {
          console.log(3)
          this.baseForm.rgtName= undefined
          this.baseForm.rgtBirthday= undefined
          this.baseForm.rgtIdType= undefined
          this.baseForm.rgtIdNo= undefined
          this.baseForm.rgtSex= undefined
          this.baseForm.rgtPhone= undefined
          this.baseForm.rgtMobile= undefined
          this.baseForm.rgtEmail= undefined
          this.baseForm.rgtProvince= undefined
          this.baseForm.rgtCity= undefined
          this.baseForm.rgtDistrict= undefined
          this.baseForm.rgtAddress= undefined
          this.baseForm.region=[]
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


      getGender() {
        if (this.baseForm.cardType !== '0') {
          return
        }
        if (parseInt(this.baseForm.idCard.substr(16, 1)) % 2 === 1) {
          this.baseForm.gender = '0'
        } else {
          this.baseForm.gender = '1'
        }
      }
      ,

    }
  }
</script>
<style scoped>
  .item-width {
    width: 220px;
  }

  .el-form /deep/ .el-form-item__label {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .read-only.el-cascader /deep/ .el-input.is-disabled .el-input__inner {
    border: none;
    color: #333;
    padding: 0;
    background: transparent;
    width: 100%;
  }

  .read-only.el-cascader /deep/ .el-icon-arrow-down:before {
    display: none;
  }

  .long-select {
    width: 100%;
  }
</style>
