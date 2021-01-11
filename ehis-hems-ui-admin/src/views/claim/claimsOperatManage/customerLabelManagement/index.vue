<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>客户信息查询</span>
      </div>
      <el-form ref="formInline" :model="formInline" style="padding-bottom: 30px;padding-top: 10px" label-width="110px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="customerno">
              <el-input v-model="formInline.customerno" class="item-width" clearable placeholder="请输入" size="mini"
              @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="formInline.name" class="item-width" clearable placeholder="请输入" size="mini"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出生日期：" prop="birthday">
              <el-date-picker
                v-model="formInline.birthday"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="选择日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="formInline.idcardno" class="item-width" clearable placeholder="请输入" size="mini"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyno">
              <el-input v-model="formInline.policyno" class="item-width" clearable placeholder="请输入" size="mini"
                @keyup.native.enter="searchHandle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            :loading="searchLoad"
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="searchHandle"
          >查询</el-button>
          <el-button size="mini" type="primary" style="width: 73px" @click="resetForm">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>查询结果</span>
      </div>
      <el-table
        v-loading="loading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" width="55" label="" prop="checked">
          <template slot-scope="scope">
            <el-radio v-model="radio" :label="scope.row.id" @change.native="handleCurrentChange(scope.row, scope.$index)">{{ &nbsp; }}</el-radio>
          </template>
        </el-table-column>
        <el-table-column align="center" width="140" prop="customerno" label="客户号" show-overflow-tooltip/>
        <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip/>
        <el-table-column :formatter="querySex" align="center" prop="sex" label="性别" show-overflow-tooltip/>
        <el-table-column :formatter="queryCardType" align="center" prop="idtype" label="证件类型" show-overflow-tooltip/>
        <el-table-column align="center" prop="idcardno" label="证件号码" show-overflow-tooltip/>
        <el-table-column align="center" prop="mobilephone" label="手机" show-overflow-tooltip/>
        <el-table-column :formatter="queryJob" align="center" prop="occupation" label="职业" show-overflow-tooltip/>
        <el-table-column :formatter="queryNature" align="center" prop="nationality" label="国籍" show-overflow-tooltip/>
      </el-table>
      <el-pagination
        :total="totalCount"
        :current-page="pageInfo.currentPage"
        :page-size="pageInfo.pageSize"
        :page-sizes="[5, 10, 20, 30,50,100]"
        style="margin-top: 8px; text-align: right;"
        layout="prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"/>
    </el-card>
    <el-card v-if="isOpen" class="box-card" style="margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>标签信息</span>
      </div>
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="cusLableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;padding-bottom: 20px">
        <el-table-column width="80px" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button v-if="cusLableData.length - scope.$index === 1" type="text" @click="addHandle">
              <i class="el-icon-circle-plus"/>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column width="240px" align="center" prop="labelType" label="标签类型" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-model="scope.row.labelType" class="item-width" size="mini" clearable placeholder="请选择" @change="changeFlag(scope.row)" @visible-change="chooseChange">
              <el-option v-for="item in labelTypeList" :label="item.label" :value="item.value" :key="item.value" :disabled="item.disabled"/>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="remark" label="备注" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input v-model="scope.row.remark" width="80%" clearable maxlength="200" show-word-limit placeholder="请输入" size="mini" @change="changeFlag(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column width="80px" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button type="text" @click="delHandle(scope.row, scope.$index)">
              <i class="el-icon-remove"/>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-row style="padding: 20px 80px;display: flex;align-items: center" align="middle">
          <el-col :span="2" style="">
            <div style="text-align: center">说明:</div>
          </el-col>
          <el-col :span="18">
            <el-input
              v-model="description"
              type="textarea"
              placeholder="请输入内容"
              maxlength="1000"
              show-word-limit
              @change="desChange"/>
          </el-col>
          <el-col :span="4" style="margin-left: 40px">
            <el-button :loading="saveLoad" type="primary" size="mini" @click="saveHandle">保存</el-button>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getCustomerLableList, deleteLabel, getCustomerLableInfo, updateLabel, saveLabel } from '@/api/customerLabel/index.js'
import { atLeastOne } from '@/utils/commenMethods.js'
import { get } from '@/api/dictDetail'

export default {
  name: 'Index',
  dicts: ['sex', 'card_type', 'payee_occupation', 'nativeplace', 'label_type'],
  data() {
    return {
      totalCount: 0,
      pageInfo: {
        currentPage: 1,
        pageSize: 10
      },
      formInline: {
        customerno: '',
        name: '',
        birthday: '',
        idcardno: '',
        policyno: ''
      },
      tableData: [],
      currentRow: null,
      cusLableData: [],
      textarea1: '',
      loading: false,
      description: '',
      customerno: '',
      isOpen: '',
      desFlag: false,
      labelTypeList: [
        { label: 'VIP', value: '01', disabled: false },
        { label: '爱投诉', value: '02', disabled: false },
        { label: '高风险', value: '03', disabled: false }
      ],
      radio: '',
      messFlag: null,
      changeSerchData: {},
      searchLoad: false,
      saveLoad: false
    }
  },
  watch: {
    messFlag: function(val) {
      // 保存和修改的情况分别会调用不通接口，会存在同时调用，所以监听一个标识
      if (val) {
        this.$message.success('保存成功！')
      }
    }
  },
  created() {
    get('label_type').then(res => {
      if (res.content) {
        this.labelTypeList = res.content.map(item => {
          if (item.value === '04' || item.value === '05' || item.value === '06' || item.value === '07') {
            item.disabled = false
            return item
          } else {
            item.disabled = true
            return item
          }
        })
      }
      this.labelTypeList = [...this.labelTypeList]
    })
  },
  methods: {
    handleCurrentChange(currentRow, index) {
      this.isOpen = false
      this.tableData.forEach((item, ind) => {
        item.id = ind
      })
      this.radio = currentRow.id
      this.isOpen = true
      this.customerno = currentRow.customerno
      this.getLabelList()
    },
    // 性别
    querySex(row) {
      return this.dict.label.sex[row.sex]
    },
    // 证件类型
    queryCardType(row) {
      return this.dict.label.card_type[row.idtype]
    },
    // 职业
    queryJob(row) {
      return this.dict.label.payee_occupation[row.occupation]
    },
    // 国籍
    queryNature(row) {
      return this.dict.label.nativeplace[row.nationality]
    },
    getLabelList() {
      getCustomerLableInfo(this.customerno).then(res => {
        if (res.status === '1') {
          this.cusLableData = res.data.claimCustomerLabelInfoDOList
          this.description = res.data.description
        } else {
          this.$message.error('客户标签查询异常！')
        }
      }).finally((res) => {
        if (!this.cusLableData) {
          this.cusLableData = [{ addFlag: true }]
          this.description = ''
        }
      })
    },
    addHandle() {
      if (this.cusLableData.length < this.labelTypeList.length) {
        this.cusLableData.push({ addFlag: true })
      }
    },
    delHandle(row, index) {
      if (this.cusLableData.length <= 1) {
        return
      }
      // 删除接口
      this.$confirm('是否确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (row.addFlag) {
          this.cusLableData.splice(index, 1)
          this.$message.success('删除成功！')
        } else {
          deleteLabel(row.labelno).then(res => {
            if (res.status === '1') {
              this.cusLableData.splice(index, 1)
              this.$message.success('删除成功！')
            } else {
              this.$message.success('删除失败！')
              return
            }
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除！'
        });
      });
    },
    searchHandle() {
      const flag = atLeastOne(this.formInline)
      if (!flag) {
        this.$message.warning('必须录入一项查询条件！')
        return
      }
      if (this.formInline.birthday && !this.formInline.name) {
        this.$message.warning('请录入被保人姓名！')
        return
      }
      this.changeSerchData.customerno = this.formInline.customerno
      this.changeSerchData.name = this.formInline.name
      this.changeSerchData.birthday = this.formInline.birthday
      this.changeSerchData.idcardno = this.formInline.idcardno
      this.changeSerchData.policyno = this.formInline.policyno
      this.pageInfo.currentPage = 1
      this.pageInfo.pageSize = 10
      this.getCustomerData()
    },
    getCustomerData() {
      this.searchLoad = true
      const params = JSON.parse(JSON.stringify(this.changeSerchData))
      params.currentPage = this.pageInfo.currentPage
      params.pageSize = this.pageInfo.pageSize
      this.loading = true
      this.tableData = []
      getCustomerLableList(params).then(res => {
        if (res.status === '1') {
          res.data.list.forEach(item => {
            if (this.tableData.findIndex(val => val.customerno === item.customerno) == -1) {
              item.checked = false
              this.tableData.push(item)
            }
          })
          this.totalCount = res.data.total
        }
      }).finally(() => {
        this.loading = false
        this.isOpen = false
        this.searchLoad = false
      })
    },
    resetForm() {
      this.$refs.formInline.resetFields()
    },
    sizeChange(val) {
      this.pageInfo.pageSize = val
      this.getCustomerData()
    },
    pageChange(val) {
      this.pageInfo.currentPage = val
      this.getCustomerData()
    },
    saveHandle() {
      const updateParams = []
      const addParams = []
      let subFlag = false
      this.messFlag = false
      let remarkFlag = false
      this.cusLableData.forEach((item, ind) => {
        if (!item.labelType) {
          // this.$message.warning('标签不可为空，请选择！')
          subFlag = true
        }
        if (item.labelType && !item.remark) {
          remarkFlag = true
        }
        this.cusLableData.forEach((val, newInd) => {
          if (item.labelType === val.labelType && ind !== newInd && item.labelType !== '' && item.labelType !== null && item.labelType !== undefined) {
            let length = document.getElementsByClassName('el-message--warning').length
            if (length === 0) {
              this.$message.warning('请勿选择重复的标签！')
              subFlag = true
            }
          }
        })
        if (this.desFlag) {
          item.labelno ? item.cFlag = true : item.addFlag = true
        }
      })
      if (subFlag) {
        let length = document.getElementsByClassName('el-message--warning').length
        if (length === 0) {
          this.$message.warning('标签不可为空，请选择！')
        }
        return
      }
      if (remarkFlag) {
        let length = document.getElementsByClassName('el-message--warning').length
        if (length === 0) {
          this.$message.warning('已选择标签，备注不可为空，请填写！')
        }
        return
      }
      this.cusLableData.forEach((item, ind) => {
        const eachChange = {
          labelno: item.labelno ? item.labelno : '',
          customerno: this.customerno,
          description: this.description,
          remark: item.remark ? item.remark : '',
          labelType: item.labelType
        }
        if (item.cFlag) {
          updateParams.push(eachChange)
        } else if (item.addFlag) {
          addParams.push(eachChange)
        }
      })
      if (updateParams.length) {
        this.saveLoad = true
        updateLabel(updateParams).then(res => {
          if (res.status === '1') {
            // this.$message.success('保存成功！')
            this.messFlag = true
            this.getLabelList(this.customerno)
          } else {
            this.$message.error('保存失败！')
          }
          this.saveLoad = false
        })
      }
      if (addParams.length) {
        this.saveLoad = true
        saveLabel(addParams).then(res => {
          if (res.status === '1') {
            this.messFlag = true
            // this.$message.success('保存成功！')
            this.getLabelList(this.customerno)
          } else {
            this.$message.error('保存失败！')
          }
          this.saveLoad = false
        })
      }
    },
    desChange() {
      this.desFlag = true
    },
    changeFlag(row) {
      this.cusLableData.forEach(item => {
        if (!row.addFlag && row === item) {
          item.cFlag = true
        }
      })
    },
    checkChoose() {
      this.labelTypeList.forEach(val => {
        val.disabled = false
      })
      this.cusLableData.forEach(item => {
        this.labelTypeList.forEach(val => {
          if (item.labelType === val.value) {
            val.disabled = true
          }
        })
      })
    },
    chooseChange() {
      this.checkChoose()
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
