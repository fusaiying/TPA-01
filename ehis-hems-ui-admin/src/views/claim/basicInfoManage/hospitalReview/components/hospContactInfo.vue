<template>
  <el-form ref="contactInfoForm"  :model="contactInfoForm" size="small" :disabled="disabledFlag">
    <el-card class="box-card" style="margin-top: 10px;" >
      <div slot="header" class="clearfix">
        <span>联系人信息</span>
      </div>

      <el-table ref="contactTable" :data="contactInfoForm.contacts"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column prop="placeType" align="center" header-align="center" label="联系人类型" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'contacts.'+scope.$index +'.placeType'"
                          :rules="contactInfoFormRules.placeType" style="display: inline-flex !important;">
              <el-select v-model="scope.row.placeType" placeholder="请选择" size="mini">
                <!--                <el-option v-for="option in contacttype"  :label="option.label"
                                           :value="option.value"/>
                                <el-option/>-->
                <el-option
                  v-for="item in place_typeOptions"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{scope.row.placeTypeName }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="name" align="center" header-align="center" label="姓名" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input v-if="!scope.row.id" v-model="scope.row.name" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" align="center" header-align="center" label="手机" show-overflow-tooltip>
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.phone" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="mobile" align="center" header-align="center" label="电话" show-overflow-tooltip>
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.mobile" placeholder="请输入" size="mini"/>
            <span v-else>{{ scope.row.mobile }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="email" align="center" header-align="center" label="邮箱" show-overflow-tooltip>
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.email" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="workingTime" align="center" header-align="center" label="工作时间" width="400px" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input v-if="!scope.row.id" v-model="scope.row.workingStart" placeholder="请输入" size="mini"
                      style="width: 150px"/>
            <el-input v-if="!scope.row.id" v-model="scope.row.workingEnd" placeholder="请输入" size="mini"
                      style="width: 150px ;padding-left: 10px"/>
            <span v-else>{{ scope.row.workingStart }} - {{ scope.row.workingEnd }} </span>
          </template>

        </el-table-column>
        <el-table-column prop="role" align="center" header-align="center" label="登录名" show-overflow-tooltip>
          <template slot-scope="scope">

            <el-input v-if="!scope.row.id" v-model="scope.row.role" placeholder="请输入" size="mini"/>

            <span v-else>{{ scope.row.role }}</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" min-width="94" fixed="right">
          <template slot-scope="scope">

              <span style="cursor: pointer;">
                  <el-button type="text" size="mini" v-if="contactInfoTableShow && !scope.row.isSet"
                             @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="text"  v-if="contactInfoTableShow" size="mini" @click="delHandle(scope.$index,scope.row)">删除</el-button>

                </span>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addContactHandle"> + 添加
      </el-button>
    </el-card>
    <el-dialog
      :visible.sync="dialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title="提示"
      width="30%">
      <span>{{ '删除当前行联系人信息？' }}</span>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="delConfirm">确 定</el-button>
        </span>
    </el-dialog>
  </el-form>


</template>
<script>
import {addcontactsInfo} from "@/api/baseInfo/medicalManage";


export default {
  props: {
    contactInfoForm: {
      type : Object,
      default: function (){
        return {}
      }
    },
    contactInfoTableShow: Boolean,
    supplierCode: String,
    disabledFlag: Boolean,
    isAdd: Boolean,
    dictList: Array
  },

  data() {
    return {
      dialogVisible: false,
      modalValue: false,
      index: '',
      place_typeOptions: [],

    }
  },


  watch: {
    dictList: function (newVal, oldVal) {
      this.init();
    }

  },
  mounted() {

  },


  methods: {
    init(){
      if (this.dictList != null && this.dictList.length != 0) {
        this.place_typeOptions = this.dictList.find(item => {
          return item.dictType == 'place_type'
        }).dictDate

      }
    },


    delHandle(index, row) {
      this.dialogVisible = true
      this.index = index
    },
    delConfirm() {
      this.dialogVisible = false
      this.contactInfoForm.contacts.splice(this.index,1)
    },
    //联系信息添加
    addContactHandle() {
      const field = {
        placeType: '',
        name: '',
        phone: '',
        mobile: '',
        email: '',
        workingStart: '',
        workingEnd: '',
        supplierCode: this.supplierCode,
        isSet: true
      }
      this.contactInfoForm.contacts.push(field)

    },
    editHandle(index,row){
      this.contactInfoForm.contacts[index].id = ''
      this.contactInfoForm.contacts[index].isSet = true
      this.departmentTableShow=true
    },




  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item ::v-deep label {
  font-weight: normal;
}


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}
</style>
