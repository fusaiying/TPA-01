<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" @keyup.enter.native="searchHandle(isinit='N')" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请来源：" prop="applicationsource">
              <el-select v-model="searchForm.applicationsource" class="item-width" placeholder="请选择">
                <el-option v-for="item in apply_sourcetypeOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出险日期：" prop="eventdate">
              <el-date-picker
                v-model="searchForm.eventdate"
                class="item-width"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="searchForm.name" @keyup.enter.native="searchHandle(isinit='N')" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idcardno">
              <el-input v-model="searchForm.idcardno" @keyup.enter.native="searchHandle(isinit='N')" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受理日期：" prop="acceptDate">
              <el-date-picker
                v-model="searchForm.acceptDate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
            isinit='N'
            searchHandle()"
          >查询</el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName">
          <el-tab-pane :label="label.label01" name="01">
            <claimsTable :table-data="pendingTableData" :status="activeName" @getSelect="getSelect"/>
          </el-tab-pane>
          <el-tab-pane :label="label.label02" name="02">
            <claimsTable @init-data="searchHandle" :table-data="completedTableData" :status="activeName" @getSelect="getSelect"/>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <el-pagination
          :total="activeName==='01'?total:finishTotal"
          :current-page="activeName==='01'?page:finishPage"
          :page-sizes="pageSizes"
          :page-size="activeName==='01'?pageSize:finishPageSize"
          style="margin-top: 8px; text-align: right;"
          layout="prev, pager, next, sizes"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
        <el-button
          :loading="caseLoading"
          :disabled="activeName === '01'"
          class="filter-item"
          size="mini"
          type="primary"
          style="position: absolute;right:200px;top:5px;"
          @click="batchPrinting"
        >批量扫码打印</el-button>

        <el-button
          class="filter-item"
          size="mini"
          type="primary"
          style="position: absolute;right:105px;top:5px;"
          @click="addHandle">新增受理</el-button>

        <el-button
          :loading="caseLoading"
          class="filter-item"
          size="mini"
          disabled
          type="primary"
          style="position: absolute;right:10px;top:5px;"
          @click="manualApplyHandle"
        >案件获取</el-button>
      </div>
    </el-card>
    <div v-show="iframeShow" id="printall" ref="printall">
      <div v-for="item in barCodeList" :key="item.barcodes" style="margin-bottom: 20px">
        <barcode :value="item.barcodes" :options="barcode_option" tag="svg" />
      </div>
    </div>
    <iframe v-show="iframeShow" id="print-iframe"/>
  </div>
</template>

<script>
import claimsTable from './components/claimsTable'
import { getList, getOneCase } from '@/api/claim/handleDeal'
import {encrypt} from "@/utils/rsaEncrypt"
export default {
  name: 'Accecpt',
  components: {
    claimsTable
  },
  dicts: ['apply_sourcetype'],
  data() {
    return {
      isinit: 'Y',
      caseLoading: false,
      page: 1,
      total: 0,
      pageSize: 10,
      finishPage: 1,
      finishTotal: 0,
      finishPageSize: 10,
      pageSizes: [5, 10, 20, 30, 50, 100],
      activeName: '01',
      label: {
        label01: '处理中',
        label02: '已处理'
      },
      completedTableData: [],
      pendingTableData: [],
      searchForm: {
        claimno: null,
        applicationsource: null,
        eventdate: null,
        name: null,
        idcardno: null,
        acceptDate: null
      },
      iframeShow: false,
      barCodeList: [],
      barcode_option: {
        displayValue: true, // 是否默认显示条形码数据
        background: '#fff', // 条形码背景颜色
        valid: function(valid) {
        },
        width: '1px', // 单个条形码的宽度
        height: '40px',
        fontSize: '12px' // 字体大小
      },
      selList: [],
      type:true,
      changeSerchData: {},
      // 申请来源数据字典
      apply_sourcetypeOptions:[]
    }
  },
  mounted() {
    this.searchHandle()
    this.getDicts("apply_sourcetype").then(response => {
      this.apply_sourcetypeOptions = response.data;
    });
  },
  watch: {
    totalChange: function(newVal, oldVal) {
      if (newVal.total === 0 && newVal.finishTotal > 0) {
        this.activeName = '02'
      } else {
        this.activeName = '01'
      }
    }
  },
  computed: {
    totalChange() {
      const {total, finishTotal} = this
      return {total, finishTotal}
    }
  },
  methods: {
    resetForm() {
      this.$refs.searchForm.resetFields()
    },
    // 查询
    searchHandle() {
      this.changeSerchData.claimno = this.searchForm.claimno
      this.changeSerchData.applicationsource = this.searchForm.applicationsource
      this.changeSerchData.eventdate = this.searchForm.eventdate
      this.changeSerchData.name = this.searchForm.name
      this.changeSerchData.idcardno = this.searchForm.idcardno
      this.changeSerchData.acceptDate = this.searchForm.acceptDate
      this.page = 1;
      this.finishPage = 1;
      this.total = null
      this.finishTotal = null
      this.total = 0
      this.finishTotal = 0
      this.getPending()
      this.getCompleted()
    },
    // 案件获取
    manualApplyHandle() {
      this.caseLoading = true
      const node = 'claim_accept'
      getOneCase(node).then(res => {
        if (res.status === '1') {
          // 获取到案件并跳转详情页
          if (res.data === null) {
            this.$message({ message: '获取案件错误！', type: 'error' })
          } else {
            let data = encodeURI(
              JSON.stringify({
                claimno: encrypt(res.data.claimno),
                type: 'edit',
                node: 'accept'
              })
            )
            this.$router.push({
              path: '/claims-handle/com-handle',
              query: {
                data
                // claimno: res.data.claimno,
                // type: 'edit',
                // node: 'accept'
              }
            })
          }
        } else {
          if (res.data.errCode === 'CL003' || res.data.errCode==='CL012') {
            this.$message({ message: res.data.errMsg, type: 'warning' })
          } else {
            this.$message({ message: '获取案件错误！', type: 'error' })
          }
        }
      }).catch(error => {
        this.$message({ message: '获取案件异常！', type: 'error' })
      }).finally(() => {
        this.caseLoading = false
      })
    },
    pageChange(val) {
      this.activeName === '01' ? this.page = val : this.finishPage = val
      if (this.activeName === '01') {
        this.getPending()
      } else {
        this.getCompleted()
      }
    },
    sizeChange(val) {
      this.activeName === '01' ? this.pageSize = val : this.finishPageSize = val
      if (this.activeName === '01') {
        this.getPending()
      } else {
        this.getCompleted()
      }
    },
    addHandle() {
      let data = encodeURI(
        JSON.stringify({
          status: 'handle',
          node: 'accept',
          styleFlag: 'report',
        })
      )
      const newpage = this.$router.resolve({
        name: 'acceptdetail',
        params:{},
        query:{ data }
      })
      window.open(newpage.href, '_blank');
      // this.$router.push({
      //   path: '/claims-handle/com-handle',
      //   query: {
      //     data
      //     // status: 'handle',
      //     // node: 'accept'
      //   }
      // })
    },
    getPending() {
      const params = {
        isinit: this.isinit,
        tabno: '01',
        acceptstartdate: '',
        acceptenddate: '',
        pageno: this.page,
        pagesize: this.pageSize,
        node: 'claim_accept',
        ...this.changeSerchData
      }
      if (this.changeSerchData.acceptDate) {
        params.acceptstartdate = this.changeSerchData.acceptDate[0]
        params.acceptenddate = this.changeSerchData.acceptDate[1]
      }
      getList(params).then(res => {
        if (res.status === '1') {
          this.total = res.data.total
          this.label.label01 = `处理中(${this.total})`
          this.pendingTableData = res.data.list
          // if (this.pendingTableData.length>0  && this.completedTableData.length>0) {
          //   this.activeName = '01'
          // }
          // if (this.pendingTableData.length>0  && this.completedTableData.length<0) {
          //   this.activeName = '01'
          // }
          // if (this.pendingTableData.length<0  && this.completedTableData.length>0) {
          //   this.activeName = '02'
          // }
          // if (this.pendingTableData.length<0  && this.completedTableData.length<0) {
          //   this.activeName = '01'
          // }
        } else {
          this.$message.error({ message: '查询失败！' })
        }
      })
    },
    getCompleted() {
      const params = {
        isinit: this.isinit,
        type:this.type,
        tabno: '02',
        acceptstartdate: '',
        acceptenddate: '',
        pageno: this.finishPage,
        pagesize: this.finishPageSize,
        node: 'claim_accept',
        ...this.changeSerchData
      }
      if (this.type) {
        this.type = false
      }
      if (this.changeSerchData.acceptDate) {
        params.acceptstartdate = this.changeSerchData.acceptDate[0]
        params.acceptenddate = this.changeSerchData.acceptDate[1]
      }
      getList(params).then(res => {
        if (res.status === '1') {
          this.finishTotal = res.data.total
          this.label.label02 = `已处理(${this.finishTotal})`
          this.completedTableData = res.data.list
          // if (this.pendingTableData.length>0  && this.completedTableData.length>0) {
          //   this.activeName = '01'
          // }
          // if (this.pendingTableData.length>0  && this.completedTableData.length<0) {
          //   this.activeName = '01'
          // }
          // if (this.pendingTableData.length<0  && this.completedTableData.length>0) {
          //   this.activeName = '02'
          // }
          // if (this.pendingTableData.length<0  && this.completedTableData.length<0) {
          //   this.activeName = '01'
          // }
        } else {
          // this.$message.error({ message: '查询失败！' })
        }
      })
    },
    addCase() {
      this.barCodeList = []
      for (let i = 0; i < this.selList.length; i++) {
        this.barCodeList.push({ barcodes: this.selList[i].claimno })
      }
    },
    batchPrinting() {
      if (this.selList.length < 1) {
        this.$message({ message: '请选择需要扫描打印的赔案', type: 'warning' })
        return
      }
      const printIframe = document.getElementById('print-iframe')
      const print = printIframe.contentWindow
      const doc = print.document
      doc.write(this.$refs.printall.innerHTML)
      doc.close()
      print.focus()
      print.print()
    },
    getSelect(sel) {
      this.selList = sel
      this.addCase()
    }
  }
}
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
  #print-iframe{
    position:absolute;
    width:0px;
    height:0px;
    left:-500px;
    top:-500px;
  }
</style>
