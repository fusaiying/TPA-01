<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title=""
      width="80%">
      <el-form ref="searchForm" :model="searchForm" label-width="170px" size="mini">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">被保人信息</span>
          <span style="float: right;">
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
        </div>
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户号：" prop="insuredNo">
              <el-input v-model="searchForm.insuredNo" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="姓名：" prop="name">
              <el-input v-model="searchForm.name" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件类型：" prop="idType">
              <el-select v-model="searchForm.idType" class="item-width" placeholder="请选择" clearable>
                <el-option v-for="option in card_typeOptions" :key="option.dictValue" :label="option.dictLabel"  :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idNo">
              <el-input  v-model="searchForm.idNo" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item   label="性别："  prop="sex"  >
              <el-select v-model="searchForm.sex" class="item-width"   placeholder="请选择" clearable>
                <el-option v-for="option in rgtSexOptions" :key="option.dictValue" :label="option.dictLabel"  :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item  label="出生日期："  prop="birthday" >
              <el-date-picker
                v-model="searchForm.birthday"  style="width: 200px;"  type="date" placeholder="选择日期"
                value-format="yyyy-MM-dd" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="24" style="text-align: right;">
          <span style="padding-right: 20px;">
             <el-button size="mini" type="primary" @click="restForm">重置</el-button>
            <el-button  size="mini"  type="primary"  @click="searchHandle"   >查询</el-button>
            <el-button size="mini" type="primary" @click="confirmHandle">确定</el-button>
          </span>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;"/>
      </el-form>

      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="tableData"
        size="small"
        ref="tableData"
        v-loading="tableDataloading"
        :expand-row-keys="expands"
        :row-key='getRowKeys'
        :row-class-name="getRowClass"
        highlight-current-row
        tooltip-effect="dark"
        style="margin-top: 20px">
        <el-table-column width="50">
          <template slot-scope="scope">
            <el-radio class="radio" :label="scope.$index" v-model="radio"  @click.native="getCurrentRow(scope.$index,scope.row)"> {{ }} </el-radio>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip width="150"/>
        <el-table-column align="center" prop="insuredNo" label="客户号" show-overflow-tooltip/>
        <el-table-column align="center" prop="idType" label="证件类型" width="200" :formatter="getIdType" show-overflow-tooltip/>
        <el-table-column  align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
        <el-table-column align="center" prop="sex" label="性别" :formatter="getSex"  show-overflow-tooltip/>
        <el-table-column align="center" prop="birthday" label="出生日期" show-overflow-tooltip/>
      </el-table>
      <!--    <el-pagination
            :total="queryPolicyDataPaging.totalPages"
            :current-page="queryPolicyDataPaging.currentPage"
            :page-size="queryPolicyDataPaging.pageSize"
            :page-sizes="[5, 10, 20, 30, 50, 100]"
            style="margin-top: 8px; text-align: right;"
            layout="prev, pager, next, sizes"
            @size-change="handleQueryPolicySizeChange"
            @current-change="handleQueryPolicyCurrentChange"/>-->

    </el-dialog>
  </div>

</template>

<script>
  import { selectRecognizee } from '@/api/claim/handleCom'

  export default {
    props: {
      batchInfo:Object,
      dictList: Array,
      value: {
        type: Boolean,
        default: false
      },
      fixInfo: {
        type: Object,
        default: function () {
          return {}
        }
      },
      saveVFlag: {
        type: Boolean,
        default: true
      },
      node: String
    },

    data() {
      return {
        saveFlag : true,
        copyFixInfo:{},
        caseForm: {
          problemType: '',
          problemView: ''
        },
        problem_shipment_typeOptions: [],

        expands: [],
        copyRadio: undefined,
        minLoading: false,
        policyLoading: false,
        tableDataloading: false,

        radio: undefined,
        dialogVisable: false,
        searchForm: {
          insuredNo:'',
          name:'',
          idType:'',
          idNo:'',
          sex:'',
          birthday:'',
          policyNo:'',
          policyItemNo:'',
          UHCGVIPNo:''
        },
        totalCount: 0,
        tableData: [ ],
        multipleSelection: [],
        card_typeOptions: [],
        get_duty_kindOptions: [],
        rgtSexOptions: [],
        getRowKeys(row) {
          return row.id
        },
      }
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
      fixInfo: function (newVal){
        this.copyFixInfo=newVal
      },
      saveVFlag: function (newValue) {
        this.saveFlag = newValue
      },
    },
    mounted() {
      this.getDicts("card_type").then(response => {
        this.card_typeOptions = response.data;
      });
      this.getDicts("rgtSex").then(response => {
        this.rgtSexOptions = response.data;
      });
      this.getDicts("problem_shipment_type").then(response => {
        this.problem_shipment_typeOptions = response.data;
      });
    },
    methods: {
      getPolicy_status (row, col) {
        return this.selectDictLabel(this.policy_statusOptions, row.policyStatus)
      },
      getDataList() {
        selectRecognizee(this.searchForm).then(res => {
          if (res != null && res.code == '200') {

            this.tableData = res.rows;
            if (res.total > 0) {
              this.tableData.map((item, index) => {
                item.policyInfoList = [];
                item.id = index
              })
            }
            let totalCount = res.total;
            if (totalCount === 0) {
              this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
            }

          } else {

          }
        })
      },

      //隐藏expand图标
      getRowClass(row, index) {
        let res = [];
        res.push('row-expand-cover')
        return res.join(' ')
      },
      //自定义展开
      getCurrentRow(index, row) {
        if(this.saveVFlag) {
          this.$refs.tableData.toggleRowExpansion(row, true) // 点击radio展开
        }
      },

      //关闭对话框
      changeDialogVisable() {
        this.radio = undefined
        this.copyRadio = undefined
        this.expands = []
        this.tableData=[]
        this.$refs.searchForm.resetFields()

        this.dialogVisable=false
        this.$emit('closeDialogVisable')
      },

      //重置
      restForm() {
        this.radio = undefined
        this.expands = []
        this.tableData=[]
        this.$refs.searchForm.resetFields()
      },
      //确人按钮
      confirmHandle() {
        if(this.radio!=undefined && this.radio!=null ){
          let propData = {
                caseInsuredData: this.tableData[this.radio],
                policyInfoData: this.multipleSelection
              }
          this.changeDialogVisable()
          this.$emit('getPropData', propData)
        }
        else {
          this.$message({message:'请选择一条被保人信息', type:'warning',showClose:true,center:true})
        }

      },

      //查询
      searchHandle() {
        const values = Object.values(this.searchForm)
        let flag= values.some(item => {return  item!=null && item !='' })

        if(flag){
          this.getSelectRecogn()
        }
        else {
          this.$message({message: '请至少录入一项查询条件', type: 'warning', showClose: true, center: true})
        }
      },
      //调用查询被保人信息的接口
      getSelectRecogn(){
        //
        this.radio = undefined
        this.expands = []
        this.tableData=[]
        selectRecognizee(this.searchForm).then(res => {
          //查询成功
          if (res != null && res.code == '200' ) {
            this.tableData = res.data.policyInsuredList;
            // if(res.data.policyInsuredList.length>0) {
            //   // this.tableData.map((item, index) => {
            //   //   item.policyInfoList = [];
            //   //   item.id = index
            //   // })
            // }
            let totalCount = res.data.policyInsuredList.length;
            if(totalCount===0) {
              this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
            }
          }
          else {

          }
        }).finally(() => {

        })
      },

      getIdType(row,col){
        return this.selectDictLabel( this.card_typeOptions, row.idType);
      },
      getSex(row,col){
        return this.selectDictLabel( this.rgtSexOptions, row.sex);
      }
    }
  }
</script>

<style scoped>
::v-deep .row-expand-cover .el-table__expand-column .el-icon {
  visibility: hidden;
}

.item-width {
  width: 200px;
}

.duty-p {
  width: 170px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

</style>
