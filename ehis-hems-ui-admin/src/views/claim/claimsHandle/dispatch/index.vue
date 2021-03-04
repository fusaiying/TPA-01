<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>查询条件</span>
      </div>
      <el-form ref="form" :model="form" style="padding-bottom: 30px;padding-top: 30px" label-width="130px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="rptNo">
              <el-input v-model="form.rptNo" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="getTableData"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="理赔类型：" prop="claimType">
              <el-select v-model="form.claimType" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in claimTypeSelect" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="出单公司：" prop="companyCode">
              <el-select v-model="form.companyCode" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in companySelect" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="form.name" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="getTableData"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idNo">
              <el-input v-model="form.idNo" class="item-width" size="mini" placeholder="请输入"
                        @keyup.enter.native="getTableData"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="交单日期：" prop="submitdate">
              <el-date-picker
                v-model="form.submitdate"
                style="width:220px;"
                size="mini"
                type="daterange"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="8">
            <el-form-item label="案件状态：" prop="caseStatus">
              <el-select v-model="form.caseStatus" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in caseStatusSelect" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="操作人：" prop="operator">
              <el-select v-model="form.operator" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in operatorSelect" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <div style="text-align: right; margin-right: 10px;">
          <el-row>
            <el-form-item class="but1">
              <el-button size="mini" type="success" icon="el-icon-search" @click="searchByFormParms">查 询</el-button>
              <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('form')">重 置</el-button>
            </el-form-item>
          </el-row>
        </div>

      </el-form>

      <!-- <div class="batchInfo_class" style="margin-top: 10px;">
         <discussionSurvey :show="dialogVisable" @closeDialogVisable="changeDialogVisable"/>
       </div>-->
      <el-divider/>

      <div>
        <div
          style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>工作池({{totalNum}})</span>

          <el-button style="float: right; margin-top: 10px;" type="primary" size="mini"
                     @click="exportData">清单导出
          </el-button>
          <el-button style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"
                     @click="claimDispatch">案件调度
          </el-button>
        </div>
        <el-table
          :data="tableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          @sort-change="onSortChange"
          @selection-change="handleSelectionChange"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column sortable prop="rptNo" label="报案号" sortable="custom"
                           :sort-orders="['ascending','descending',null]" width="150%" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="source" :formatter="getDeliverySourceName" label="交单来源" width="150%" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="name" label="被保人姓名" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="idNo" label="证件号码" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="claimType" label="理赔类型" :formatter="getClaimTypeName" width="150%" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="companyName" label="出单公司" align="center" show-overflow-tooltip/>
          <el-table-column sortable prop="submitdate" label="交单日期" width="150%" align="center" show-overflow-tooltip/>
          <el-table-column prop="monitoringTime" label="监控时效" align="center" show-overflow-tooltip/>
          <el-table-column prop="caseStatus" :formatter="getCaseStatusName" label="案件状态" align="center"
                           show-overflow-tooltip/>
          <el-table-column prop="operator" label="操作人" align="center" show-overflow-tooltip/>
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;"
                         v-if="scope.row.caseStatus=='01'" @click="viewStream(scope.row)">
                工作流查看
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalNum>0"
          :total="totalNum"
          :page.sync="pageInfo.currentPage"
          :limit.sync="pageInfo.pageSize"
          @pagination="initData"
        />
      </div>


      <!-- 案件调度弹框 start -->
      <el-dialog
        :visible.sync="dialogVisible"
        :dialog-visible="dialogVisible"
        :append-to-body="true"
        :before-close="handleClose"
        :close-on-click-modal="false"
        title=""
        width="30%">
        <el-form ref="operatorForm" :model="operatorForm" style="border:0" label-width="110px" label-position="right"
                 size="mini">

          <el-row>
            <el-col :span="8">
              <el-form-item label="操作人：" prop="operator">
                <el-select v-model="operatorForm.operator" class="item-width" size="mini" placeholder="请选择"
                           @change="getRole">
                  <el-option v-for="option in operatorSelect" :key="option.dictValue" :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="checkPermit" type="primary">确认</el-button>
            <el-button size="mini" @click="dialogVisible = false">取 消</el-button>
          </span>
      </el-dialog>
      <!-- 案件调度弹框 end -->
    </el-card>

  </div>
</template>

<script>
  import {getUserInfo} from '@/api/claim/standingBookSearch'
  import {
    selectCaseDispatchList
    , getDspatchUser
    , dispatchUpdate
    , getIssuingcompanyList
    , roleInfo
    , logInfo
  } from '@/api/dispatch/api'
  import moment from "moment";

  export default {
    components: {
      /*discussionSurvey*/
    },
    data() {
      return {
        prop: '',
        order: '',
        form: {
          rptNo: '',
          claimType: '',
          companyCode: '',
          name: '',
          idNo: '',
          submitdate: [],
          caseStatus: '',
          operator: '',
        },
        claimTypeSelect: [],
        companySelect: [],
        caseStatusSelect: [],
        operatorSelect: [],
        tableData: [],
        totalNum: 0,
        idKey: 'rptNo', // 标识列表数据中每一行的唯一键的名称
        pageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes: [5, 10, 20, 30, 50, 100]
        },
        loading: false,
        defaultData: true,
        dialogVisible: false,
        operatorForm: {
          operator: '',
        },
        dialogVisable: false,
        rptNos: '',
        deliverySource: [],
        searchBtn: false,
        logRoleName: '',
        checkRoleName: '',
        userIdName: {},
        checkArra: new Array(),
        uncheckArra: new Array(),
        checkSelection: [],
        userNameValue: '',
      }
    },
    mounted() {
      //交单来源
      this.getDicts("delivery_source").then(response => {
        this.deliverySource = response.data;
      });
      this.getDicts("claimType").then(response => {
        this.claimTypeSelect = response.data;
      });
      this.getDicts("claim_status").then(response => {
        this.caseStatusSelect = response.data;
      });
      this.getDspatchUserData();
      this.getIssuingcompanySelect();
    },
    computed: {},

    created: function () {
      this.initData();
      this.getLogRole();
    },
    methods: {
      getPermit(caseStatus, type) {
        /**
         挂起  00
         机构交单  04
         受理  05
         问题件  30
         撤件可申诉  97
         撤件  98
         录入  06
         审核  07
         协谈  31
         调查  32
         抽检  08
         结案  99
         */
        switch (caseStatus) {
          case '00':
            return true
            break;
          case '04':
            return true
            break;
          case '05':
            return type === 1 ? this.logRoleName.indexOf("受理") > -1 : this.checkRoleName.indexOf("受理") > -1
            break;
          case '30':
            return type === 1 ? this.logRoleName.indexOf("问题件") > -1 : this.checkRoleName.indexOf("问题件") > -1
            break;
          case '97':
            return true
          case '98':
            return true
          case '06':
            return type === 1 ? this.logRoleName.indexOf("录入") > -1 : this.checkRoleName.indexOf("录入") > -1
            break;
          case '07':
            return type === 1 ? this.logRoleName.indexOf("审核") > 0 : this.checkRoleName.indexOf("审核") > -1
            break;
          case '31':
            return type === 1 ? this.logRoleName.indexOf("协谈") > -1 : this.checkRoleName.indexOf("协谈") > -1
            break;
          case '32':
            return type === 1 ? this.logRoleName.indexOf("调查") > -1 : this.checkRoleName.indexOf("调查") > -1
            break;
          case '08':
            return type === 1 ? this.logRoleName.indexOf("抽检") > -1 : this.checkRoleName.indexOf("抽检") > -1
            break;
          case '99':
            return true
          default:
            return true
        }
      },
      getRole(value) {
        this.userNameValue = this.userIdName[value];
        if (value == '') {
          return false;
        }
      },
      getLogRole() {
        getUserInfo().then(response => {
          if (response.code == '200' && response.data) {
            //console.log(response)
            this.logRoleName = response.data.roles[0].roleName;
          }
        }).catch(error => {
          console.log(error);
        });
      },
      getDeliverySourceName(row, col) {
        return this.selectDictLabel(this.deliverySource, row.source)
      },
      getIssuingcompanySelect() {
        const params = {
          pageNum: 1,
          pageSize: 1000,
          status: 'Y',
          xtype: 'getIssuingcompanySelect'
        };
        getIssuingcompanyList(params).then(response => {
          if (response.rows != null) {
            for (let i = 0; i < response.rows.length; i++) {
              let obj = new Object();
              obj.dictValue = response.rows[i].companycode;
              obj.dictLabel = response.rows[i].companyname;
              this.companySelect.push(obj);
            }
          }
        }).catch(error => {
          console.log(error);
        });
      },
      getDspatchUserData() {
        const params = {
          pageNum: 1,
          pageSize: 1000,
          status: '0',
          delFlag: 0,
          xtype: 'getDspatchUser'
        };
        getDspatchUser(params).then(response => {
          if (response.rows != null) {
            for (let i = 0; i < response.rows.length; i++) {
              let obj = new Object();
              obj.dictLabel = response.rows[i].userName;
              obj.dictValue = response.rows[i].userName;
              this.operatorSelect.push(obj);
              // 根据用户名称映射用户id
              this.userIdName[response.rows[i].userName] = response.rows[i].userId;
            }
          }
        }).catch(error => {
          console.log(error);
        });
      },
      getCaseStatusName(row, col) {
        return this.selectDictLabel(this.caseStatusSelect, row.caseStatus)
      },
      getClaimTypeName(row, col) {
        return this.selectDictLabel(this.claimTypeSelect, row.claimType)
      },
      // getCompanyName(row,col){
      //   return this.selectDictLabel(this.companySelect, row.companycode)
      // },
      initData() {
        this.gettableData();
      },
      //重置
      reset(form) {
        this.$refs[form].resetFields()
      },
      //查询
      gettableData() {

        let startTime = "";
        let endTime = "";
        let submitdate = this.form.submitdate;
        if ('' != submitdate) {
          startTime = submitdate[0];
          endTime = submitdate[1];
        }

        if (!this.searchBtn) {
          startTime = moment().subtract('month', 1).format('YYYY-MM-DD') + ' ' + '00:00:00'
          endTime = moment(new Date().getTime()).format('YYYY-MM-DD') + ' ' + '23:59:59'
        }
        const params = {
          pageNum: this.pageInfo.currentPage,
          pageSize: this.pageInfo.pageSize,
          rptNo: this.form.rptNo,
          claimType: this.form.claimType,
          companyCode: this.form.companyCode,
          name: this.form.name,
          idNo: this.form.idNo,
          submitstartdate: startTime,
          submitenddate: endTime,
          caseStatus: this.form.caseStatus,
          operator: this.form.operator,

          orderByColumn: 'cc.create_time',
          isAsc: 'desc'
        };
        if (this.prop!=null && this.prop!==''){
          if (this.prop=='submitdate'){
            params.orderByColumn='submitDate'
          }else{
            params.orderByColumn=this.prop
          }

        }
        if (this.order!=null && this.order!==''){
          params.isAsc=this.order
        }

        this.loading = true;
        selectCaseDispatchList(params).then(response => {
          this.totalNum = response.total;
          this.tableData = response.rows;
          this.loading = false;
        }).catch(error => {
          this.loading = false;
          console.log(error);
        });
      },
      searchByFormParms() {
        this.searchBtn = true;
        this.pageInfo.currentPage = 1;
        this.pageInfo.pageSize = 10;
        this.gettableData();
      },
      viewStream(row) {
        // this.$router.push({
        //   path: '/basic-info/contractManage-edit',
        //   query: {status:'update',rptNo: row.rptNo,flag : row.flag ,providerCode :row.providerCode}
        //
        // })
      },
      claimDispatch() {
        if (this.checkSelection.length === 0) {
          this.$message.warning('请先选择需要处理的数据！');
          return false;
        }
        this.dialogVisible = true;
      },
      exportData() {
        let startTime = "";
        let endTime = "";
        let submitdate = this.form.submitdate;
        if ('' != submitdate) {
          startTime = submitdate[0];
          endTime = submitdate[1];
        }

        const params = {
          rptNo: this.form.rptNo,
          claimType: this.form.claimType,
          companyCode: this.form.companyCode,
          name: this.form.name,
          idNo: this.form.idNo,
          submitstartdate: startTime,
          submitenddate: endTime,
          caseStatus: this.form.caseStatus,
          updateBy: this.form.operator,
        };
        this.download('claimflow/case/exportDispatchList', params, `案件调度_${new Date().getTime()}.xlsx`);
      },
      arrayContain(array, obj) {
        for (let i = 0; i < array.length; i++) {
          if (array[i] == obj)
            return true;
        }
        return false;
      },
      remove(array, val) {
        let index = array.indexOf(val);
        if (index > -1) {
          array = array.splice(index, 1);
        }
        return array;
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        //console.log(selection)
        this.checkSelection = selection;
        this.single = selection.length != 1;
        this.multiple = !selection.length;
      },
      handleClose() {
        this.operatorForm.operator = '';
        this.dialogVisible = false;
      },
      checkPermit() {

        if (this.operatorForm.operator == '') {
          return false;
        }
        this.uncheckArra = [];
        this.checkArra = [];

        if (this.userNameValue == '') {
          return false;
        }
        roleInfo(this.userNameValue).then(response => {
          if (response.code == '200') {
            this.checkRoleName = response.data.roles[0].roleName;

            // 判断是否有权限
            for (let i = 0; i < this.checkSelection.length; i++) {
              let caseStatus = this.checkSelection[i].caseStatus;
              if (this.logRoleName.indexOf("管理员") < 0) {
                let permit = this.getPermit(caseStatus, 1);
                if (permit) {
                  this.checkArra.push(this.checkSelection[i].rptNo)
                } else {
                  this.uncheckArra.push(this.checkSelection[i].rptNo)
                }
              } else {
                this.checkArra.push(this.checkSelection[i].rptNo)
              }
              if (this.checkRoleName.indexOf("管理员") < 0) {
                let permit = this.getPermit(caseStatus, 2);
                if (permit) {
                  if (!this.arrayContain(this.checkArra, this.checkSelection[i].rptNo)) {
                    this.checkArra.push(this.checkSelection[i].rptNo)
                    this.remove(this.uncheckArra, this.checkSelection[i].rptNo)
                  }
                } else {
                  if (this.arrayContain(this.checkArra, this.checkSelection[i].rptNo)) {
                    this.remove(this.checkArra, this.checkSelection[i].rptNo)
                    this.uncheckArra.push(this.checkSelection[i].rptNo)
                  }
                }
              }
            }
            // console.log("******************************")
            // console.log(this.uncheckArra);
            // console.log("******************************")
            // console.log("+++++++++++++++++++++++++++++++++++++")
            // console.log(this.checkArra);
            // console.log("+++++++++++++++++++++++++++++++++++++")
            // console.log(this.uncheckArra);
            // console.log(this.checkArra);
            if (this.uncheckArra.length > 0) {

              let confirmText = this.uncheckArra.toString().split(",")
              let h = this.$createElement
              let newDatas = []
              newDatas.push(h('p', null, "以下案件操作人无处理权限，请重新分配"))
              newDatas.push(h('p', null, ""))
              for (const i in confirmText) {
                newDatas.push(h('p', null, confirmText[i]))
              }

              this.$confirm('提示', {
                title: '提示',
                message: h('div', null, newDatas),
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'info'
              }).then(() => {
                this.updateOperator()
              }).catch(() => {

              })
            } else {
              this.updateOperator()
            }

          } else {
            console.log(response);
          }
        }).catch(error => {
          console.log(error);
        });

        // const params = {
        //   rptNo: this.rptNos,
        //   operator: this.operatorForm.operator
        // };
        // dispatchUpdate(params).then(response => {
        //   if(response.code == 200) {
        //     this.operatorForm.operator = '';
        //     this.dialogVisible = false;
        //     this.$message.success('调度成功！');
        //     this.gettableData();
        //   } else {
        //     this.dialogVisible = false;
        //     this.$message.error('调度失败！');
        //   }
        // }).catch(error => {
        //   console.log(error);
        // });
      },
      updateOperator() {
        if(this.checkArra.length === 0) {
          return false;
        }
        const params = {
          rptNo: this.checkArra,
          operator: this.operatorForm.operator
        };
        dispatchUpdate(params).then(response => {
          if (response.code == 200) {
            this.operatorForm.operator = '';
            this.dialogVisible = false;
            this.$message.success('调度成功！');
            this.gettableData();
          } else {
            this.dialogVisible = false;
            this.$message.error('调度失败！');
          }
        }).catch(error => {
          console.log(error);
        });
      },
      onSortChange({prop, order}) {
        this.prop = prop
        if (order === 'ascending') {
          this.order = 'asc'
        } else if (order === 'descending') {
          this.order = 'desc'
        } else if (order == null) {
          this.prop = ''
          this.order = ''
        }
        //调用查询接口
        this.gettableData();
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /deep/ .el-table .warning-row {
    background: oldlace;
  }

  /deep/ .el-dialog {
    display: flex;
    flex-direction: column;
    margin: 0 !important;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    /*height:600px;*/
    max-height: calc(100% - 30px);
    max-width: calc(100% - 30px);
  }

  /deep/ .el-dialog .el-dialog__body {
    flex: 1;
    overflow: auto;
  }

</style>

