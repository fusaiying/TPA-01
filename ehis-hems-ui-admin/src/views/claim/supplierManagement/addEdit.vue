<template>
  <div class="supplier">
    <!-- 基本信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>基本信息</span>
        <el-button style="margin:-6px;right: 60px;position: absolute;" size="mini" @click="returnHistory" type="primary">返 回</el-button>
        <!-- 新增 -->
        <el-button v-if="addbtn" :disabled="alreadySave" icon="el-icon-refresh" style="margin:-6px;right: 134px;position: absolute;" size="mini" type="primary" @click="resetForm">重 置</el-button>
        <el-button v-if="addbtn" :disabled="alreadySave" style="margin:-6px;right: 226px;position: absolute;" icon="el-icon-check" size="mini" type="primary" @click="submit">保 存</el-button>
        <!-- 编辑 -->
        <el-button v-if="!addbtn" style="margin:-6px;right: 134px;position: absolute;" size="mini" type="primary" icon="el-icon-check" @click="submit">修改</el-button>
      </div>
      <el-form style="margin: 20px;" ref="supplierForm" :disabled="alreadySave" :rules="supplierRule" :inline="true" :model="supplier" label-position="right" label-width="135px" class="demo-form-inline" size="small">
        <el-form-item :style="{width:'33%'}" label="供应商编码：">
          <el-input :style="{display:'none'}" v-model="supplier.suppliercode"/>
          <span>{{ supplier.suppliercode }}</span>
        </el-form-item>
        <el-form-item :style="{width:'33%'}" :class="['short-select']" prop="suppliertype" label="供应商类型：">
          <el-select v-model="supplier.suppliertype" :placeholder="placeType1">
            <el-option v-for="option in suppliertypes" :key="option.id" :label="option.label" :value="option.value"/>
          </el-select>
        </el-form-item>
        <el-form-item :style="{width:'33%'}" :class="['short-select']" prop="state" label="是否有效：">
          <el-select v-model="supplier.state" :placeholder="placeType1">
            <el-option v-for="option in supplierstates" :key="option.id" :label="option.label" :value="option.value"/>
          </el-select>
        </el-form-item>
        <el-form-item :style="{width:'33%'}" :class="['short-input']" prop="chname" label="供应商中文名称：" :rules="supplierRule.chname">
          <el-input v-model="supplier.chname" :placeholder="placeType2" clearable/>
        </el-form-item>
        <el-form-item :style="{width:'33%'}" :class="['short-input']" prop="enname" label="供应商英文名称：" :rules="supplierRule.enname">
          <el-input v-model="supplier.enname" :placeholder="placeType2" clearable/>
        </el-form-item>
        <el-form-item :style="{width:'33%'}" :class="['short-select']" label="所属地区：">
          <el-cascader :options="regions" v-model="region" :placeholder="placeType1" @change="handleChange"/>
        </el-form-item>
        <el-form-item :style="{width:'100%'}" :class="['long-input']" prop="address" label="详细地址：" >
          <el-input v-model="supplier.address" :placeholder="placeType2" clearable/>
        </el-form-item>
        <el-form-item :style="{width:'100%'}" :class="['long-input']" prop="remarks" label="备注：">
          <el-input v-model="supplier.remarks" :placeholder="placeType2" clearable/>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 联系人信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>联系人信息</span>
      </div>
      <el-form ref="contactForm" :rules="constactRules" :model="contactForm" size="small">
        <el-table size="small" ref="contacttable" :data="contactForm.contacts" :header-cell-style="{color:'black',background:'#f8f8ff'}" :highlight-current-row="!alreadySave" style="width: 100%;" class="el-tb-edit">
          <el-table-column prop="type" align="center" header-align="center" label="联系人类型" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactsno === ''" :prop="'contacts.' + scope.$index + '.type'" :rules="constactRules.type">
                <el-select v-model="scope.row.type" placeholder="请选择" size="mini">
                  <el-option v-for="option in contacttype" :key="option.id" :label="option.label" :value="option.value"/>
                </el-select>
              </el-form-item>
              <span>{{ getContactTypeName(scope.row.type) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="name" align="center" header-align="center" label="姓名" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactsno === ''" :prop="'contacts.' + scope.$index + '.name'" :rules="constactRules.name">
                <el-input v-model="scope.row.name" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="mobilephone" align="center" header-align="center" label="移动电话" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactsno === ''" :prop="'contacts.' + scope.$index + '.mobilephone'" :rules="constactRules.mobilephone">
                <el-input v-model="scope.row.mobilephone" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.mobilephone }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="officephone" align="center" header-align="center" label="办公电话" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactsno === ''" :prop="'contacts.' + scope.$index + '.officephone'" :rules="constactRules.officephone">
                <el-input v-model="scope.row.officephone" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.officephone }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="email" align="center" header-align="center" label="电子邮箱" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactsno === ''" :prop="'contacts.' + scope.$index + '.email'" :rules="constactRules.email">
                <el-input v-model="scope.row.email" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.email }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed="right" align="center" header-align="center" label="操作" min-width="1">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.contactsno === ''">
                <el-button :disabled="alreadySave" type="text" size="small" @click="delContactRow(scope.$index,scope.row)">{{ scope.row.contactsno!=null&&scope.row.contactsno!=""?'删除':'取消' }}</el-button>
              </el-form-item>
              <span>
                <el-button :disabled="alreadySave" type="text" size="small" @click="delContactRow(scope.$index,scope.row)">{{ scope.row.contactsno!=null&&scope.row.contactsno!=""?'删除':'取消' }}</el-button>
              </span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <el-button :disabled="alreadySave" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addContactRow()"> + 添加</el-button>
    </el-card>

    <!-- 账户信息 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>账户信息</span>
      </div>
      <el-form ref="accountForm" :rules="accountRules" :model="accountForm" size="small">
        <el-table size="small" ref="accounttable" :data="accountForm.account" :header-cell-style="{color:'black',background:'#f8f8ff'}" :highlight-current-row="!alreadySave" style="width: 100%;" class="el-tb-edit">
          <el-table-column prop="acctype" align="center" header-align="center" label="账户类型" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''" :prop="'account.' + scope.$index + '.acctype'" :rules="accountRules.acctype">
                <el-select v-model="scope.row.acctype" placeholder="请选择" size="mini">
                  <el-option v-for="option in accounttype" :key="option.id" :label="option.label" :value="option.value"/>
                </el-select>
              </el-form-item>
              <span>{{ getAccountTypeName(scope.row.acctype) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accname" align="center" header-align="center" label="银行账户名称" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''" :prop="'account.' + scope.$index + '.accname'" :rules="accountRules.accname">
                <el-input v-model="scope.row.accname" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.accname }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accnumber" align="center" header-align="center" label="账户号" min-width="3" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''" :prop="'account.' + scope.$index + '.accnumber'" :rules="accountRules.accnumber">
                <el-input v-model="scope.row.accnumber" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.accnumber }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankname" align="center" header-align="center" label="开户行" min-width="4" show-overflow-tooltip>
            <!--<template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''" :prop="'account.' + scope.$index + '.bankname'" :rules="accountRules.bankname">
                <el-cascader :options="platOptions" :props="props" v-model="scope.row.bankname" :show-all-levels="false" size="mini" clearable @change="value => selectChange(scope.$index, value)" @active-item-change="handleItemChange"/>
              </el-form-item>
              <span v-if="scope.row.accountcode !== ''">{{ scope.row.bankname }}</span>
              <span v-else>{{ scope.row.bankname| fin }}</span>
            </template>-->
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''" :prop="'account.' + scope.$index + '.bankname'" :rules="accountRules.bankname" >
                <el-cascader :class="['long-select']" :options="platOptions" :props="props" v-model="scope.row.bankname" :show-all-levels="false" size="mini" clearable @change="value => selectChange(scope.row, value)"/>
              </el-form-item>
              <span v-if="scope.row.accountcode !== ''">{{ scope.row.bankname }}</span>
              <span v-else>{{ scope.row.bankname|fin }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unitebankcode" align="center" header-align="center" label="联行号" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''">
                <!--<el-select v-model="scope.row.unitebankcode" placeholder="请选择" size="mini" filterable>
                  <el-option v-for="option in accountunitebankcode[scope.$index]" :key="option.id" :label="option.label" :value="option.value"/>
                </el-select>-->
                <el-input v-model="scope.row.unitebankcode" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.unitebankcode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bankaddress" v-if="show" align="center" header-align="center" label="银行地址" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''">
                <el-input v-model="scope.row.bankaddress" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.bankaddress }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="internatoncode" v-if="show" align="center" header-align="center" label="银行国际代码" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''">
                <el-input v-model="scope.row.internatoncode" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span>{{ scope.row.internatoncode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="accstatus" align="center" header-align="center" label="账户状态" min-width="2" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''">
                <el-select v-model="scope.row.accstatus" placeholder="请选择" size="mini">
                  <el-option v-for="option in accountaccstatus" :key="option.id" :label="option.label" :value="option.value"/>
                </el-select>
              </el-form-item>
              <span>{{ getAccountaccStatusName(scope.row.accstatus) }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed="right" align="center" header-align="center" label="操作" min-width="1">
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.accountcode === ''">
                <el-button :disabled="alreadySave" type="text" size="small" @click="delAccountRow(scope.$index, scope.row)">{{ scope.row.accountcode!=null&&scope.row.accountcode!=""?'删除':'取消' }}</el-button>
              </el-form-item>
              <span>
                <el-button :disabled="alreadySave" type="text" size="small" @click="delAccountRow(scope.$index, scope.row)">{{ scope.row.accountcode!=null&&scope.row.accountcode!=""?'删除':'取消' }}</el-button>
              </span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <el-button :disabled="alreadySave" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec; margin: 10px 0 20px;" @click="addAccountRow()"> + 添加</el-button>
    </el-card>
  </div>
</template>

<script>
  import { addSupplier, getAddress, getCategory, getCategoryBank,getHeadBankProvinceCity,getHeadBank,getProvince,getCity } from '@/api/investigate/supplierManagement'
  import { updateOne, deleteContact, deleteAccount, getCategorySon } from '@/api/investigate/supplierManagement'
  import { getSupplierContactAccountInfo } from '@/api/investigate/supplierManagement'
  import { get } from '@/api/dictDetail'
  var that
  export default {
    name: 'AddEdit',
    filters: {
      fin: function(value) {
        for (let n = 0; n < that.accountbancknames.length; n++) {
          if (that.accountbancknames[n].code === value[3]) {
            return that.accountbancknames[n].name
          }
        }
      }
    },
    data() {
      const checkFormType = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.type', '')
        const { type } = this.contactForm.contacts[index]
        if (type === null || type === '') {
          this.indexContacts[index] = 1
          callback(new Error('请选择联系人类型'))
        } else {
          this.indexContacts[index] = null
        }
        callback()
      }
      const checkFormName = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.name', '')
        const { name } = this.contactForm.contacts[index]
        if (name === null || name === '') {
          this.indexContacts[index] = 1
          callback(new Error('请填写姓名'))
        }
        callback()
      }
      const checkFormMobilephone = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.mobilephone', '').replace('.officephone', '').replace('.email', '')
        const { email, officephone, mobilephone } = this.contactForm.contacts[index]
        const mobilephonereg = /^[1][3|4|5|6|7|8|9][0-9]{9}$/
        if ((mobilephone === null || mobilephone === '') && (officephone === null || officephone === '') && (email === null || email === '')) {
          this.indexContacts[index] = 1
          callback(new Error('至少填一项'))
        } else {
          if (mobilephone !== null && mobilephone !== '' && !mobilephonereg.test(mobilephone)) {
            this.indexContacts[index] = 1
            callback(new Error('请输入正确移动电话'))
          }
          callback()
        }
      }
      const checkFormOfficephone = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.mobilephone', '').replace('.officephone', '').replace('.email', '')
        const { email, officephone, mobilephone } = this.contactForm.contacts[index]
        const officephonereg = /^[0-9]\d{2,3}-\d{7,8}$/
        if ((mobilephone === null || mobilephone === '') && (officephone === null || officephone === '') && (email === null || email === '')) {
          this.indexContacts[index] = 1
          callback(new Error('至少填一项'))
        } else {
          if (officephone !== null && officephone !== '' && !officephonereg.test(officephone)) {
            this.indexContacts[index] = 1
            callback(new Error('请输入正确办公电话'))
          }
          callback()
        }
      }
      const checkFormEmail = (rules, value, callback) => {
        const index = rules.field.replace('contacts.', '').replace('.mobilephone', '').replace('.officephone', '').replace('.email', '')
        const { email, officephone, mobilephone } = this.contactForm.contacts[index]
        const emailreg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
        if ((mobilephone === null || mobilephone === '') && (officephone === null || officephone === '') && (email === null || email === '')) {
          this.indexContacts[index] = 1
          callback(new Error('至少填一项'))
        } else {
          if (email !== null && email !== '' && !emailreg.test(email)) {
            this.indexContacts[index] = 1
            callback(new Error('请输入正确邮箱'))
          }
          callback()
        }
      }
      const checkFormAcctype = (rules, value, callback) => {
        const index = rules.field.replace('account.', '').replace('.acctype', '')
        const { acctype } = this.accountForm.account[index]
        if (acctype === null || acctype === '') {
          this.indexAccount[index] = 1
          callback(new Error('请选择账户类型'))
        } else {
          this.indexAccount[index] = null
        }
        callback()
      }
      const checkFormAccname = (rules, value, callback) => {
        const index = rules.field.replace('account.', '').replace('.accname', '')
        const { accname } = this.accountForm.account[index]
        if (accname === null || accname === '') {
          this.indexAccount[index] = 1
          callback(new Error('请填写银行账户名称'))
        }
        callback()
      }
      const checkFormAccnumber = (rules, value, callback) => {
        const index = rules.field.replace('account.', '').replace('.accnumber', '')
        const { accnumber } = this.accountForm.account[index]
        if (accnumber === null || accnumber === '') {
          this.indexAccount[index] = 1
          callback(new Error('请填写账户号'))
        }
        callback()
      }
      const checkFormBankname = (rules, value, callback) => {
        const index = rules.field.replace('account.', '').replace('.bankname', '')
        const { bankname } = this.accountForm.account[index]
        if (bankname === null || bankname === '') {
          this.indexAccount[index] = 1
          callback(new Error('请选择开户行'))
        }
        callback()
      }
      const isEnglish = (rules, value, callback) => {
        if(value!==null&&value!==''){
          const num=/^[a-zA-Z]+$/
          if (!num.test(value)) {
            callback(new Error('请输入英文供应商名称'))
          }else{
            callback()
          }
        }else{
          callback()
        }
      }
      const _self = this;
      return {
        type: '', // 添加或者更新（操作类型）
        subType: '', // 是否是添加保存后刷新
        alreadySave: false, // 是否是添加保存后刷新
        addbtn: true, // 操作类型
        placeType1: '请选择',
        placeType2: '请输入',
        props: {
          label: 'name',
          value: 'code',
          children: 'child',
          lazy: true,
          lazyLoad (node, resolve) {
            const { level,value } = node;
            let nodes = []
            if(level < 3){
              nodes = _self.handleItemChange(node,value,resolve)
              //resolve(nodes);
            }else{
              nodes = _self.getChildNodes(node,resolve)
            }
          }
        },
        supplier: { // 基本信息
          suppliercode: '', // 供应商编码
          suppliertype: '', // 供应商类型
          state: '01', // 状态，是否有效
          chname: '', // 中文名
          enname: '', // 英文名
          province: '', // 省
          city: '', // 市
          district: '', // 区
          address: '', // 地址
          remarks: '' // 备注
        },
        indexContacts: [], // 联系人报错下标
        indexAccount: [], // 账户报错下标
        contactForm: {
          contacts: []
          // 联系人列表
        },
        constactRules: {
          type: [{ validator: checkFormType, trigger: 'change' }],
          name: [{ validator: checkFormName, trigger: 'blur' }],
          mobilephone: [{ validator: checkFormMobilephone, trigger: 'blur' }],
          officephone: [{ validator: checkFormOfficephone, trigger: 'blur' }],
          email: [{ validator: checkFormEmail, trigger: 'blur' }]
        },
        accountForm: {
          account: [] // 账户信息
        },
        accountms: [],
        accountRules: {
          acctype: [{ validator: checkFormAcctype, trigger: 'change' }],
          accname: [{ validator: checkFormAccname, trigger: 'blur' }],
          accnumber: [{ validator: checkFormAccnumber, trigger: 'blur' }],
          bankname: [{ validator: checkFormBankname, trigger: ['blur', 'change'] }]
        },

        suppliertypes: [], // 供应商类型下拉选项
        supplierstates: [], // 是否有效下拉选项
        regions: [], // 省市区下拉选项
        region: [], // 当前选中的省市区  '110000','110100','110105'
        contacttype: [], // 联系人类型
        accounttype: [], // 账户类型
        platOptions: [], // 开户行
        tempOptions:[],//开户行临时存储
        accountbancknames: [], // 开户行
        bancknamestemp: [],
        accountunitebankcode: [], // 银联号
        accountaccstatus: [], // 账户状态

        supplierRule: {
          chname: [{required: true,message: "请输入供应商中文名称", trigger: "blur" }],
          enname: [{ validator: isEnglish, trigger: 'change' }]
        },
        show:false
      }
    },
    beforeCreate: function() {
      that = this
    },
    created() {
      // 下拉选初始化 供应商类型 是否有效 联系人类型 账户类型 账户状态
      this.getSupplierTypeData()
      this.getSupplierStateData()
      this.getContactTypeData()
      this.getAccountTypeData()
      this.getAccountaccStatusData()
      this.getAddressData()
      //this.getHeadBankProvinceCity()
      this.getHeadBank()

      // 判断是编辑还是新增
      this.type = this.$router.history.current.query.type // 操作类型，add或者edit
      this.supplier.suppliercode = this.$router.history.current.query.suppliercode // 供应商编码
      this.subType = this.$router.history.current.query.subType
      if (this.type === 'add') {
        this.addbtn = true
      }
      if (this.type === 'edit') {
        this.addbtn = false
        // 初始化数据
        this.getSupplierContactAccountData()
        this.alreadySave = false
      }
      if (this.type === 'add' && this.subType === 'add') {
        this.addbtn = false
        // 初始化数据
        this.getSupplierContactAccountData()
        this.addbtn = true
        this.alreadySave = true
        this.placeType1 = ''
        this.placeType2 = ''
      }
    },
    methods: {
      // 查询供应商基本信息
      getSupplierContactAccountData() {
        getSupplierContactAccountInfo(this.supplier.suppliercode).then(res => {
          this.supplier = res.data.supplierDO
          this.contactForm.contacts = res.data.contactsDOList
          this.accountForm.account = res.data.accountDOList
          const regionArr = []
          regionArr.push(res.data.supplierDO.province, res.data.supplierDO.city, res.data.supplierDO.district)
          this.region = regionArr
        }).catch(err => {
          alert('查询供应商信息异常' + err)
        })
      },
      // 供应商类型下拉选
      getSupplierTypeData() {
        const dictName = 'supplier_type'
        get(dictName).then((response) => {
          this.suppliertypes = response.content
        })
      },
      // 是否有效下拉选
      getSupplierStateData() {
        const dictName = 'supplier_states'
        get(dictName).then((response) => {
          this.supplierstates = response.content
        })
      },
      // 联系人类型下拉选
      getContactTypeData() {
        const dictName = 'contact_type'
        get(dictName).then(response => {
          this.contacttype = response.content
        })
      },
      getContactTypeName(value) {
        for (const i in this.contacttype) {
          if (this.contacttype[i].value === value) {
            return this.contacttype[i].label
          }
        }
      },
      // 账户类型下拉选
      getAccountTypeData() {
        const dictName = 'account_type'
        get(dictName).then(response => {
          this.accounttype = response.content
        })
      },
      getAccountTypeName(value) {
        for (const i in this.accounttype) {
          if (this.accounttype[i].value === value) {
            return this.accounttype[i].label
          }
        }
      },
      // 账户状态下拉选
      getAccountaccStatusData() {
        const dictName = 'accountacc_status'
        get(dictName).then(response => {
          this.accountaccstatus = response.content
        })
      },
      getAccountaccStatusName(value) {
        for (const i in this.accountaccstatus) {
          if (this.accountaccstatus[i].value === value) {
            return this.accountaccstatus[i].label
          }
        }
      },
      handleChange(value) {
        this.supplier.province = value[0]
        this.supplier.city = value[1]
        this.supplier.district = value[2]
      },
      // 异步查询开户行
      getHeadBankProvinceCity() {
        // 请求顶级分类，省
        getHeadBankProvinceCity().then(res => {
          if(res.status === '0'){
            this.$message.warning("开户行信息查询错误！")
          }else{
            this.tempOptions = res.data
            /*this.platOptions.map((item, index, array) => {
              // 因为数组和对象更新后不会更新视图，这里必须用$set方法
              this.$set(array[index], 'child', [])
            })*/
          }
        }).catch(error => {
          this.$message.warning("开户行信息查询异常！")
        })
      },
      //获取总行
      getHeadBank() {
        // 请求顶级分类，省
        getHeadBank().then(res => {
          if(res.status === '0'){
            this.$message.warning("总行信息查询错误！")
          }else{
            this.platOptions = res.data
          }
        }).catch(error => {
          this.$message.warning("总行信息查询异常！")
        })
      },
      selectChange(row, value) {
        row.unitebankcode = value[3]
        /*this.accountForm.account[i].unitebankcode = ''
        this.accountunitebankcode[i] = [{ label: value[3], value: value[3] }]*/
      },
      getChildNodes(node,resolve){
        const params = {
          proCode: node.parent.value,
          cityCode: node.value,
          headBank:node.parent.parent.value
        }
        getCategoryBank(params).then(res => {
          if (res.status === '0') {
            this.$message.error('查询支行信息错误！')
          } else {
            this.bancknamestemp = []
            this.bancknamestemp = res.data
            for (var i = 0; i < this.bancknamestemp.length; i++) {
              for (var j = 0, l = this.accountbancknames.length; j < l; j++) {
                if (this.bancknamestemp[i] === this.accountbancknames[j]) {
                  break
                }
              }
              if (j === l) {
                this.accountbancknames.push(this.bancknamestemp[i])
              }
            }
            resolve(res.data)
          }
        }).catch(error => {
          this.$message.error('查询支行信息异常！')
        })
      },
      // 异步查询
      handleItemChange(node,code,resolve) {
        var nodes = []
        /*if(node.level === 0){
          nodes = options;
          return nodes
        }*/
        if(node.level === 1){
          /*options.map(item =>{
            if(item.code === code){
              nodes = item.child;
            }
          })
          return nodes*/

          getProvince().then(res => {
            if (res.status === '0') {
              this.$message.error('查询省份信息错误！')
            } else {
              resolve(res.data)
            }
          }).catch(error => {
            this.$message.error('查询省份信息异常！')
          })
        }

        if(node.level === 2){
          /*options.map(item =>{
            if(item.code === node.parent.value){
              item.child.map(single => {
                if(single.code === code){
                  nodes = single.child;
                }
              })
            }
          })
          return nodes*/
          const province = code
          getCity(province).then(res => {
            if (res.status === '0') {
              this.$message.error('查询省份信息错误！')
            } else {
              if(res.data.length===0){
                resolve({code:'',name:''})
              }else {
                resolve(res.data)
              }
            }
          }).catch(error => {
            this.$message.error('查询省份信息异常！')
          })
        }




        /*const self = this
        if(value.length === 3){
          //请求获取相应支行
          const params = {
            proCode: value[1],
            cityCode: value[2],
            headBank:value[0]
          }
          this.platOptions.map((item, index) => {
            if (item.code === value[0]) {
            item.child.map((innerItem, innerIndex) => {
              if (innerItem.code === value[1]) {
              innerItem.child.map((childInner, childIndex) => {
                if (childInner.code === value[2]) {
                  getCategoryBank(params).then(res => {
                    if (res.status === '0') {
                      this.$message.error('查询支行信息错误！')
                    } else {
                      self.$set(innerItem.child[childIndex], 'child', res.data)
                      self.$nextTick(()=> {
                        self.platOptions = JSON.parse(JSON.stringify(self.platOptions))
                      })
                      console.log(JSON.stringify(self.platOptions))


                      this.bancknamestemp = []
                      this.bancknamestemp = res.data
                      for (var i = 0; i < this.bancknamestemp.length; i++) {
                        for (var j = 0, l = this.accountbancknames.length; j < l; j++) {
                          if (this.bancknamestemp[i] === this.accountbancknames[j]) {
                            break
                          }
                        }
                        if (j === l) {
                          this.accountbancknames.push(this.bancknamestemp[i])
                        }
                      }
                    }
                  }).catch(error => {
                    this.$message.error('查询支行信息异常！')
                  })
                }
              })
            }
            })
          }
          })
        }*/

        /*let parentId
        if (value.length === 1) {
          // 如果点击的是一级分类，市
          parentId = value[0]
          this.platOptions.map((item, index) => {
            if (item.code === parentId && item.child.length === 0) {
              // 当顶级分类的的child为空时才请求数据
              getCategorySon(parentId).then(res => {
                this.$set(this.platOptions[index], 'child', res.data)
                item.child.map((innerItem, innerIndex) => {
                  // 二级分类下必须要设置一个空的child数组，不然点击@active-item-change没反应
                  this.$set(item.child[innerIndex], 'child', [])
                })
              })
            }
          })
        } else if (value.length === 2) {
          var topId = value[0]
          parentId = value[1]
          const varity = {
            proCode: topId,
            cityCode: parentId
          }
          this.platOptions.map((item, index) => {
            // item是每一个省，child是每个省下面的市，item.child.map城市遍历
            item.child.map((innerItem, innerIndex) => {
              if (innerItem.code === parentId && innerItem.child.length === 0) {
                // 当二级分类的的child为空时才请求一次数据
                getCategoryBank(varity).then(res => {
                  this.$set(item.child[innerIndex], 'child', res.data)
                  this.bancknamestemp = []
                  this.bancknamestemp = res.data
                  for (var i = 0; i < this.bancknamestemp.length; i++) {
                    for (var j = 0, l = this.accountbancknames.length; j < l; j++) {
                      if (this.bancknamestemp[i] === this.accountbancknames[j]) {
                        break
                      }
                    }
                    if (j === l) {
                      this.accountbancknames.push(this.bancknamestemp[i])
                    }
                  }
                  // console.log('---' + this.accountbancknames.length)
                })
              }
            })
          })
        }*/
      },
      // 增加一行联系人信息
      addContactRow() {
        const field = {
          contactsno: '',
          type: '',
          name: '',
          mobilephone: '',
          officephone: '',
          email: ''
        }
        this.contactForm.contacts.push(field)
        setTimeout(() => { this.$refs.contacttable.setCurrentRow(field) }, 10)
      },
      // 删除一行联系人信息
      delContactRow(index,row) {
        let text = row.contactsno === null || row.contactsno === '' ? '取消':'删除'
        this.$confirm(`是否确定${text}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const contactsno = this.contactForm.contacts[index].contactsno
          if (contactsno === null || contactsno === '') {
            this.contactForm.contacts.splice(index, 1)
          } else {
            deleteContact(contactsno).then(response => {
              window.location.reload()
            }).catch(error => {
              console.log(error)
            })
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      // 添加一行账户信息
      addAccountRow() {
        const field = {
          accountcode: '',
          acctype: '',
          accname: '',
          accnumber: '',
          bankname: '',
          unitebankcode: '',
          bankaddress: '',
          internatoncode: '',
          accstatus: '01'
        }
        this.accountForm.account.push(field)
        setTimeout(() => { this.$refs.accounttable.setCurrentRow(field) }, 10)
      },
      // 删除一行账户信息
      delAccountRow(index, row) {
        let text = row.accountcode === null || row.accountcode === '' ? '取消':'删除'
        this.$confirm(`是否确定${text}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const accountcode = this.accountForm.account[index].accountcode
          if (accountcode === null || accountcode === '') {
            //this.$delete(this.accountunitebankcode, index)
            this.accountForm.account.splice(index, 1)
          } else {
            deleteAccount(accountcode).then(response => {
              const url = window.location
              location.replace(url)
              // window.location.reload();
            }).catch(error => {
              console.log(error)
            })
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      // 重置
      resetForm() {
        this.$confirm('确定重置？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.$refs['supplierForm'] !== undefined) {
            this.$refs['supplierForm'].resetFields()
          }
          this.contactForm.contacts = []
          this.accountForm.account = []
          this.region = []
        }).catch(() => {
        })
      },
      // 保存、修改
      submit() {
        // 验证是否为空
        let supplierFormValid = true
        let contactFormValid = true
        let accountFormValid = true
        this.$refs.supplierForm.validate(valid => {
          supplierFormValid = valid
        })
        this.$refs.contactForm.validate(valid => {
          contactFormValid = valid
        })
        this.$refs.accountForm.validate(valid => {
          accountFormValid = valid
        })
        if (!supplierFormValid) {
          return false
        }
        if (!contactFormValid) {
          for (i = 0; i <= (this.indexContacts.length - 1); i++) {
            // console.log('this.indexContacts=')
            // console.log(this.indexContacts)
            if (this.indexContacts[i] != null) {
              this.$refs.contacttable.setCurrentRow(this.contactForm.contacts[i])
              return false
            }
          }
        }
        if (!accountFormValid) {
          for (i = 0; i <= (this.indexAccount.length - 1); i++) {
            if (this.indexAccount[i] != null) {
              this.$refs.accounttable.setCurrentRow(this.accountForm.account[i])
              return false
            }
          }
        }
        if (this.type === 'add') {
          this.accountms = []
          /* 基本信息 联系人信息 账户信息信息 */
          if (this.accountForm.account.length > 0) {
            for (var i = 0; i < this.accountForm.account.length; i++) {
              if (this.accountForm.account[i].accountcode === null || this.accountForm.account[i].accountcode === '') {
                this.accountms.push({
                  accountcode: this.accountForm.account[i].accountcode,
                  acctype: this.accountForm.account[i].acctype,
                  accname: this.accountForm.account[i].accname,
                  accnumber: this.accountForm.account[i].accnumber,
                  bankname: this.accountForm.account[i].bankname[3],
                  unitebankcode: this.accountForm.account[i].unitebankcode,
                  bankaddress: this.accountForm.account[i].bankaddress,
                  internatoncode: this.accountForm.account[i].internatoncode,
                  accstatus: this.accountForm.account[i].accstatus
                })
              }
            }
            var addParam = {
              baseinfo: this.supplier,
              contactinfo: this.contactForm.contacts,
              accinfo: this.accountms
            }
          } else {
            addParam = {
              baseinfo: this.supplier,
              contactinfo: this.contactForm.contacts,
              accinfo: this.accountForm.account
            }
          }
          addSupplier(addParam).then(response => {
            if(response.status==='1'){
              this.$message.success("保存成功！")
              this.supplier.suppliercode = response.data
              this.alreadySave=true
              this.getSupplierContactAccountData()
              this.placeType1 = ''
              this.placeType2 = ''
            }else{
              this.$message.error("保存失败！")
            }
          }).catch(error => {
            this.$message.error("保存异常！")
          })
        } else if (this.type === 'edit') {
          this.accountms = []
          if (this.accountForm.account.length > 0) {
            for (var j = 0; j < this.accountForm.account.length; j++) {
              if (this.accountForm.account[j].accountcode === null || this.accountForm.account[j].accountcode === '') {
                this.accountms.push({
                  accountcode: this.accountForm.account[j].accountcode,
                  acctype: this.accountForm.account[j].acctype,
                  accname: this.accountForm.account[j].accname,
                  accnumber: this.accountForm.account[j].accnumber,
                  bankname: this.accountForm.account[j].bankname[3],
                  unitebankcode: this.accountForm.account[j].unitebankcode,
                  bankaddress: this.accountForm.account[j].bankaddress,
                  internatoncode: this.accountForm.account[j].internatoncode,
                  accstatus: this.accountForm.account[j].accstatus
                })
              }
            }
            var updateVO = {
              supplierUpdateVO: this.supplier,
              listContactsUpdateVO: this.contactForm.contacts,
              listAccountUpdateVO: this.accountms
            }
          } else {
            updateVO = {
              supplierUpdateVO: this.supplier,
              listContactsUpdateVO: this.contactForm.contacts,
              listAccountUpdateVO: this.accountForm.account
            }
          }
          updateOne(updateVO).then(response => {
            var msg = response.data
            // console.log('===========' + msg)
            if (msg === 'success') {
              this.$message({
                type: 'success',
                message: '修改成功！'
              })
              this.getSupplierContactAccountData()
            } else if (msg === 'false'){
              this.$message({
                type: 'warning',
                message: '该供应商存在有效合同，禁止把供应商状态修改为无效！'
              })
            }else{
              this.$message.error("修改失败！")
            }
          }).catch(error => {
            this.$message.error("修改异常！")
          })
        }
      },
      // 地址下拉选
      getAddressData() {
        getAddress().then(response => {
          this.regions = response.data
        }).catch(error => {
          console.log(error)
        })
      },
      // 框框取消按钮点击后刷新
      refreshCurrent() {
        // 刷新
        let url = window.location
        if (this.type === 'add') {
          url = url + this.supplier.suppliercode + '&subType=add'
        }
        location.replace(url)
        this.stopdel = true
      },
      // 点击x刷新
      handleClose() {
        // 刷新
        let url = window.location
        if (this.type === 'add') {
          url = url + this.supplier.suppliercode + '&subType=add'
        }
        location.replace(url)
      },
      returnHistory() {
        this.$router.go(-1)
      }
    }
  }
</script>

<style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .box-card {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
  }
  .supplier{
    margin-bottom: 33px;
  }
  .long-input /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }

  .short-select .el-select{
    width: 100%;
  }
  .short-select .el-cascader{
    width: 100%;
  }
  .long-select{
    width: 100%;
  }
  .short-select /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }
  .short-input /deep/ .el-form-item__content{
    width: calc(100% - 150px);
  }

  .el-tb-edit .el-form-item {
    display: none;
    width: 100%;
  }
  .el-tb-edit .current-row .el-form-item{
    display: inherit;
  }
  .el-tb-edit .current-row .el-form-item+span{
    display: none;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }

  .el-form-item{
    margin-right: 0;
  }
</style>
