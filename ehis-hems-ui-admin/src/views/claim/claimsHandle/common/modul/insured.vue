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
              <el-input
                v-model="searchForm.idNo" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item
              label="性别："
              prop="sex"
            >
              <el-select v-model="searchForm.sex" class="item-width"
                         placeholder="请选择" clearable>
                <el-option v-for="option in rgtSexOptions" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item
              label="出生日期："
              prop="birthday"
            >
              <el-date-picker
                v-model="searchForm.birthday"
                style="width: 200px;"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"
                clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="保单号：" prop="policyNo">
              <el-input v-model="searchForm.policyNo" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分单号：" prop="policyItemNo">
              <el-input v-model="searchForm.policyItemNo" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="UHCG会员号：" prop="UHCGVIPNo">
              <el-input v-model="searchForm.UHCGVIPNo" class="item-width" autocomplete="off" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="24" style="text-align: right;">
          <span style="padding-right: 20px;">
            <el-button size="mini" type="primary" @click="problemOut">问题件转出</el-button>

             <el-button size="mini" type="primary" @click="restForm">重置</el-button>
            <el-button
              size="mini"
              type="primary"
              @click="searchHandle"
            >查询</el-button>

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
        @expand-change="getPolicyInfoList"
        tooltip-effect="dark"
        style="margin-top: 20px">
        <el-table-column width="150">
          <template slot-scope="scope">
            <el-radio class="radio" :label="scope.$index" v-model="radio"
                      @click.native="getCurrentRow(scope.$index,scope.row)"> {{ }}
            </el-radio>
            　　　　　
          </template>
        </el-table-column>
        <el-table-column type="expand" width="1">
          <template slot-scope="props">
            <el-table
              :ref="'tableExpand'+props.$index"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="props.row.policyInfoList"
              size="small"
              v-loading="policyLoading"
              @expand-change="getMinDataList"
              @selection-change="handleSelectionChange"
              highlight-current-row
              tooltip-effect="dark"
              style="width: 100%;">
              <el-table-column type="selection"></el-table-column>


              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-table
                    :ref="'table'+props.$index"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    :data="props.row.minData"
                    size="small"
                    v-loading="minLoading"
                    highlight-current-row
                    tooltip-effect="dark"
                    style="width: 100%;">
                    <el-table-column align="center" prop="riskCode" label="险种编码" show-overflow-tooltip/>
                    <el-table-column align="center" prop="riskName" label="险种名称" show-overflow-tooltip/>
                    <el-table-column label="责任编码" align="center" prop="dutyCode">
                      <!--                      <template slot-scope="scope">
                                              <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                                            </template>-->
                    </el-table-column>
                    <el-table-column label="责任名称" align="center" prop="dutyName">
                      <!--                      <template slot-scope="scope">
                                              <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                                            </template>-->
                    </el-table-column>
                    <el-table-column label="责任明细编码" align="center" prop="dutyDetailCode">
                      <!--                      <template slot-scope="scope">
                                              <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">{{ item.getDutyCode }}</P>
                                            </template>-->
                    </el-table-column>
                    <el-table-column label="责任明细名称" align="center" prop="dutyDetailName">
                      <!--                      <template slot-scope="scope">
                                              <P class="duty-p" v-for="item in scope.row.getDutyList" :key="item.getDutyCode"
                                                 :title="item.getDutyName">{{ item.getDutyName }}</P>
                                            </template>-->
                    </el-table-column>
                    <el-table-column label="申请原因" align="center" prop="applyReason">
                      <!--                      <template slot-scope="scope">
                                              <P v-for="item in scope.row.getDutyList" :key="item.getDutyCode">
                                                {{ item.getDutyKind === null ? '' : getDuty_kind(item.getDutyKind.trim()) }}</P>
                                            </template>-->
                    </el-table-column>
                  </el-table>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="policyNo" label="保单号" show-overflow-tooltip/>
              <el-table-column align="center" prop="policyItemNo" label="分单号" show-overflow-tooltip/>
              <el-table-column align="center" prop="name" label="被保人" show-overflow-tooltip/>
              <el-table-column align="center" prop="appName" label="投保人" show-overflow-tooltip/>
              <el-table-column align="center"  label="有限日期" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span v-if="scope.row">{{ scope.row.validStartDate }}~{{ scope.row.validEndDate }}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="policyRiskType" label="保单险类" :formatter="getPolicyRiskType" show-overflow-tooltip/>
              <el-table-column align="center" prop="policyStatus" label="保单状态" show-overflow-tooltip :formatter="getPolicy_status"/>
              <el-table-column align="center" prop="specialAgreement" label="特约信息" show-overflow-tooltip/>
              <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
              <el-table-column align="center" prop="policyType" label="保单类型" show-overflow-tooltip :formatter="getPolicy_type"/>
              <el-table-column align="center" prop="ssFlag" label="社保标记" show-overflow-tooltip :formatter="getSocialinsurance1"/>
<!--              <el-table-column label="操作" align="center" width="100" show-overflow-tooltip fixed="right">
                <template slot-scope="scope">
                    <span>
                      <el-button type="text" size="mini"
                                 @click="editHandle(scope.$index, scope.row)">预览</el-button>
                       <el-button type="text" size="mini"
                                  @click="delHandle(scope.$index, scope.row)">下载</el-button>
                    </span>
                </template>
              </el-table-column>-->


            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="name" label="被保人姓名" show-overflow-tooltip width="150"/>
        <el-table-column align="center" prop="insuredNo" label="客户号" show-overflow-tooltip/>
        <el-table-column align="center" prop="idType" label="证件类型" width="200" :formatter="getIdType" show-overflow-tooltip/>
        <el-table-column  align="center" prop="idNo" label="证件号码" show-overflow-tooltip/>
        <el-table-column align="center" prop="sex" label="性别" :formatter="getSex"  show-overflow-tooltip/>
        <el-table-column align="center" prop="birthday" label="出生日期" show-overflow-tooltip/>
        <el-table-column align="center" prop="UHCGVIPNo" label="UHCG会员号" show-overflow-tooltip/>
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

      <el-dialog
        :visible.sync="innerDialogVisable"
        :append-to-body="true"
        :before-close="closingDialogVisable"
        title=""
        width="40%" height="35%">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">问题件转出</span>
          <div style="float: right;">
            <el-button  type="primary" size="mini" @click="confirmSave">确认</el-button>
          </div>
        </div>
        <el-form ref="caseForm" :model="caseForm" label-width="120px" size="mini" :rules="caseFormRules">
          <el-row>
            <el-col :span="8">
              <el-form-item label="问题件类型：" prop="problemType">
                <el-select v-model="caseForm.problemType" class="item-width" placeholder="请选择">
                  <el-option v-for="option in problem_shipment_typeOptions" :key="option.dictValue"
                             :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="16">
              <el-form-item label="转出意见：" prop="problemView">
                <el-input type="textarea" style="width: 635px" :rows="4" maxlength="1000" v-model="caseForm.problemView"
                          clearable/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-dialog>
    </el-dialog>
  </div>

</template>

<script>
import {getCoreInfo} from '@/api/claim/register'
import Templates from "@/views/product/templates";


import {selectRecognizee, selectInsuredList, addProblem} from '@/api/claim/handleCom'

export default {
  props: {
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

      node: String
  },


  data() {
    return {
      copyFixInfo:{},
      innerDialogVisable:false,
      caseForm: {
        problemType: '',
        problemView: ''
      },
      caseFormRules: {
        problemType: {required: true, message: '问题件类型不能为空', trigger: 'change'},
        problemView: {required: true, message: '转出意见不能为空', trigger: 'change'},
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


      tableData: [/*{
        contNo: 'xx', id: 0,
        policyInfoList: [{riskName: 'xx',dutyName: 'xx',riskName1:'xx'},{contNo: 'xx',dutyName: 'xx'}]
      },*/
        /* {
           contNo: 'x1x',
           id: 1,
           policyInfoList: [{riskName: 'xx', minData: [{riskName1: 'xx222'}]}, {
             riskName: 'xx',
             minData: [{riskName1: 'xx222'}]
           }]
         },*/
        /* {contNo: 'x2x', id: 2,policyInfoList: [{riskName: 'xx',minData:{riskName1: 'xx'}},{riskName: 'xx',minData:{riskName1: 'xx'}}]},*/
      ],
      multipleSelection: [],
      card_typeOptions: [],




      /*'policy_status','get_duty_kind'*/
      policy_statusOptions: [],
      get_duty_kindOptions: [],
      rgtSexOptions: [],
      policy_typeOptions: [],
      socialinsurance1Options: [],
      insurance_typeOptions: [],

      getRowKeys(row) {
        return row.id
      },


    }
  },
  watch: {
    value: function (newValue) {
      this.dialogVisable = newValue
      this.getDataList()
    },
    fixInfo: function (newVal){

      this.copyFixInfo=newVal
      console.log('------------')
      console.log(this.copyFixInfo)
    },

  },


  mounted() {
    /*  if (this.dictList != null && this.dictList.length != 0) {
        this.policy_statusOptions = this.dictList.find(item => {
          return item.dictType == 'policy_status'
        }).dictDate
        this.get_duty_kindOptions = this.dictList.find(item => {
          return item.dictType == 'get_duty_kind'
        }).dictDate
      }*/
    this.getDicts("card_type").then(response => {
      this.card_typeOptions = response.data;
    });
    this.getDicts("rgtSex").then(response => {
      this.rgtSexOptions = response.data;
    });
    this.getDicts("problem_shipment_type").then(response => {
      this.problem_shipment_typeOptions = response.data;
    });

    this.getDicts("policy_status").then(response => {
      this.policy_statusOptions = response.data;
    });
    this.getDicts("policy_type").then(response => {
      this.policy_typeOptions = response.data;
    });
    this.getDicts("socialinsurance1").then(response => {
      this.socialinsurance1Options = response.data;
    });
    this.getDicts("insurance_type").then(response => {
      this.insurance_typeOptions = response.data;
    });
    //this.getDataList()
  },
  methods: {
    getPolicyRiskType(row, col) {
      return this.selectDictLabel(this.insurance_typeOptions, row.policyRiskType)
    },
    getPolicy_status (row, col) {
      return this.selectDictLabel(this.policy_statusOptions, row.policyStatus)
    },
    getPolicy_type(row, col) {
     return this.selectDictLabel(this.policy_typeOptions, row.policyType)
    },
    getSocialinsurance1(row, col) {
      return this.selectDictLabel(this.socialinsurance1Options, row.ssFlag)
    },

    closingDialogVisable(){
      this.$refs.caseForm.resetFields()
      this.innerDialogVisable=false
    },
    //问题件转出
    problemOut(){
        this.innerDialogVisable=true
    },
    confirmSave() {
      this.$refs.caseForm.validate((valid) => {
        if (valid) {
          this.$confirm(`是否发起问题件?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let data = {
              rptNo: this.copyFixInfo.rptNo,
              problemType: this.caseForm.problemType,
              problemView: this.caseForm.problemView,
            }

            addProblem(data).then(res => {
              if (res != null && res.code === 200) {
                this.$message({
                  message: '发起问题件成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.$router.push({path: '/claims-handle/pbclaim'});
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消！'
            })
          })
        } else {
          return this.$message.warning(
            "请录入必录项！"
          )
        }
      })


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

    /*    goBack() {
          this.dialogVisable = false
          this.$emit('closeDialogVisable')
        },*/

    //隐藏expand图标
    getRowClass(row, index) {
      let res = [];
      res.push('row-expand-cover')
      return res.join(' ')
    },
    //自定义展开
    getCurrentRow(index, row) {
      this.$refs.tableData.toggleRowExpansion(row, true) // 点击radio展开
      //调用查询保单信息接口
      //保单信息的table ref

      /*      this.multipleSelection = []
            if (this.copyRadio != null && this.copyRadio !== '') {
              let dynamicRef = 'tableExpand' + this.copyRadio

                this.$refs[dynamicRef].clearSelection();


              this.copyRadio = this.radio
              console.log(this.copyRadio + '------------------------')
            } else {
              this.copyRadio = index

            }
            this.radio = index*/

    },

    //展开得到保单信息
    getPolicyInfoList(row, expandedRows) {
      let that = this
      this.radio=row.id

      //只展开一行
      if (expandedRows.length) {//说明展开了
        that.expands = []
        if (row) {
          that.expands.push(row.id)//只展开当前行id
        }
      } else {//说明收起了
        that.expands = []
      }
      this.policyLoading = true
      //判断只有展开是做请求
      if (expandedRows.length > 0) {
        this.multipleSelection=[]
        let query={
          insuredNo: row.insuredNo
        }
        selectInsuredList(query).then(res => {
          /*this.tableData.forEach((temp,index)=>{
            if (temp.insuredNo===row.insuredNo){
              this.tableData[index].policyInfoList=res.rows
              this.tableData[index].policyInfoList.map(item => {
                item.minData=[]
              })

            }
          })*/
          this.tableData[this.radio].policyInfoList=res.data
          console.log(this.tableData[this.radio])
          this.policyLoading=false
        }).catch(res => {
          this.policyLoading=false
        })
      }
      else {
        this.policyLoading=false
      }



    },
    //展开责任明细信息
    getMinDataList(row, expandedRows) {
    },

//关闭对话框
    changeDialogVisable() {
      //清空对话框中的数据

      this.radio = undefined
      this.copyRadio = undefined
      this.expands = []
      this.$refs.searchForm.resetFields()
      this.dialogVisable=false
      this.$emit('closeDialogVisable')
    },

    //重置
    restForm() {
      this.$refs.searchForm.resetFields()
    },



    //确人按钮
    confirmHandle() {
      let propData = {
        caseInsuredData: this.tableData[this.radio],
        policyInfoData: this.multipleSelection

      }

      console.log(propData)
      //关闭清空
      this.changeDialogVisable()
      this.$emit('getPropData', propData)
    },

    //查询
    searchHandle() {

      //调用查询就诊人信息的接口
      selectRecognizee(this.searchForm).then(res => {
        if (res != null && res.code == '200' ) {

          this.tableData = res.rows;
          if(res.total>0) {
            this.tableData.map((item, index) => {
              item.policyInfoList = [];
              item.id = index
            })
          }
          let totalCount = res.total;
          if(totalCount===0) {
            this.$message({message: '未找到符合条件的查询结果', type: 'warning', showClose: true, center: true})
          }

        }
        else {

        }
      }).finally(() => {

      })

    },

    //选中复选框
    handleSelectionChange(val){
      this.multipleSelection=[],
        console.log(val)
      this.multipleSelection=val
    },



    getCNStatus(row, col) {
      /*return this.dict.label.policy_status[row.status]*/
      return this.selectDictLabel(this.policy_statusOptions, row.status);
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
