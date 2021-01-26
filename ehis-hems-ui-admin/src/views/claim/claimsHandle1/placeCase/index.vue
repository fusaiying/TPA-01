<template>
  <div class="app-container">
      <el-card class="box-card">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>查询条件</span>
        </div>
        <el-form ref="form" :model="form" style="padding-bottom: 30px;padding-top: 30px" label-width="130px" label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <el-form-item label="机构：" prop="rptNo">
                <el-input v-model="form.rptNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="理赔类型：" prop="companyCode">
                <el-select v-model="form.companyCode" class="item-width" size="mini" placeholder="请选择">
                  <el-option v-for="option in companySelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="批次号：" prop="claimType">
                <el-input v-model="form.rptNo" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label=" 报案号：" prop="name" >
                <el-input v-model="form.name" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label=" 盒号：" prop="name" >
                <el-input v-model="form.name" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
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
      </el-card>

      <el-card  style="margin-top: 10px;">
        <div>
          <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>归档信息</span>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"@click="addRecovery">新增</el-button>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini"@click="openListDialog">详情</el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
          >
            <el-table-column prop="rptNo" label="盒号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="source" label="报案号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="name" label="批次号" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="idNo" label="机构" width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="claimType" label="新增时间"  width="150%" align="center" show-overflow-tooltip />
            <el-table-column prop="companyCode" label="末次修改时间"  align="center" show-overflow-tooltip />
            <el-table-column prop="" label="末次修改人" align="center" show-overflow-tooltip />
            <el-table-column prop="caseStatus" label="已归档案件数" align="center" show-overflow-tooltip />
            <el-table-column prop="updateBy" label="是否销毁"  align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button size="mini" type="text" style="z-index: 1;padding:0px;margin-top:0px;"  v-if="scope.row.caseStatus=='01'"  @click="viewStream(scope.row)">
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

        <!-- 追讨白名单维护弹框 start -->
        <el-dialog
          :visible.sync="dialogVisible"
          :dialog-visible="dialogVisible"
          :append-to-body="true"
          :before-close="handleClose"
          :close-on-click-modal="false"
          title=""
          width="27%">
          <el-card class="box-card">
          <el-form ref="recoveryForm" :model="recoveryForm" style="border:0;" label-width="110px" label-position="right" size="mini">
            <el-row>
              <el-col :span="8">
                <el-form-item label="机构：" prop="operator">
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="理赔类型：" prop="operator">
                  <el-select v-model="recoveryForm.operator" class="item-width" size="mini" placeholder="请选择">
                    <el-option v-for="option in operatorSelect" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="批次号：" prop="operator">
                  <el-input v-model="recoveryForm.operator" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="盒号：" prop="operator">
                  <el-input v-model="recoveryForm.operator" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="报案号起止：" prop="operator">
                  <el-input v-model="recoveryForm.operator" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="" prop="operator">
                  <el-input v-model="recoveryForm.operator" class="item-width" size="mini" placeholder="请输入" @keyup.enter.native="getTableData"/>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
          <span style="margin-right:25px; margin-bottom: 25px;float: right;" class="dialog-footer">
            <el-button size="mini"  type="primary">确认</el-button>
          </span>
          </el-card>
        </el-dialog>

        <!-- 追讨白名单维护弹框 end -->
      </el-card>
    <case-list :value="listDialog" :rptNo="rptNo" @closeListDialog="closeListDialog"/>

  </div>
</template>

<script>

   import caseList from './components/caseList'

   import { caseFilingList } from '@/api/placeCase/api'

    export default {
      components: {
        caseList
      },
        data() {
            return {
                form: {
                  rptNo: '',
                  companyCode: '',
                  name: '',
                  idNo:'',
                },
                companySelect:[],
                operatorSelect:[],
                tableData: [],
                totalNum: 0,
                idKey: 'rptNo', // 标识列表数据中每一行的唯一键的名称
                pageInfo: {
                    currentPage: 1,
                    pageSize: 10,
                    pageSizes:[5,10,20,30,50,100]
                },
                loading: false,
                defaultData : true,
                dialogVisible:false,
                recoveryForm : {
                  operator :'',
                },
              dialogVisable: false,
              listDialog:false,
              rptNo : '',

            }
        },
      mounted(){
        // this.getDicts("queue_claim_status").then(response => {
        //   this.caseStatusSelect = response.data;
        // });
      },
      computed: {

      },

      created: function() {
        this.initData();
      },
      methods: {
        openListDialog(){
          this.listDialog = true
        },
        closeListDialog() {
          this.listDialog = false
        },
        changeDialogVisable(){
          this.dialogVisible = false
        },
        initData(){
          this.gettableData();
        },
        //重置
        reset(form) {
            this.$refs[form].resetFields()
        },
        //查询
        gettableData () {


          const params = {
            pageNum:this.pageInfo.currentPage,
            pageSize:this.pageInfo.pageSize,

            // rptNo:this.form.rptNo,
            // companyCode:this.form.companyCode,
            // name: this.form.name,
            // idNo:this.form.idNo,

            orderByColumn:'create_time',
            isAsc:'desc'
          };


          caseFilingList(params).then(response => {
               this.totalNum = response.total;
               this.tableData = response.rows;
                this.loading = false
          }).catch(error => {
            console.log(error);
          });
        },
        searchByFormParms(){
          this.gettableData();
        },
        viewStream(row) {
          // this.$router.push({
          //   path: '/basic-info/contractManage-edit',
          //   query: {status:'update',rptNo: row.rptNo,flag : row.flag ,providerCode :row.providerCode}
          //
          // })
        },
        addRecovery() {

          // if(this.rptNos == '') {
          //   this.$message.warning('请先选择需要处理的数据！');
          //   return false;
          // }
          this.dialogVisible = true;
        },
        handleClose() {
          this.dialogVisible = false;
        },
        closeDialog(){
          //this.open = false;
          this.dialogVisible = false;
        },
        /*updateOperator(){

          if(this.recoveryForm.operator == '') {
            return false;
          }
          const params = {
            rptNo: this.rptNos,
            updateBy: this.recoveryForm.operator
          };
          dispatchUpdate(params).then(response => {
            if(response.code == 200) {
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
        },*/
      }
    }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /deep/.el-table .warning-row {
    background: oldlace;
  }
  /*/deep/.el-dialog{*/
  /*  display: flex;*/
  /*  flex-direction: column;*/
  /*  margin:0 !important;*/
  /*  position:absolute;*/
  /*  top:50%;*/
  /*  left:50%;*/
  /*  transform:translate(-50%,-50%);*/
  /*  !*height:600px;*!*/
  /*  max-height:calc(100% - 30px);*/
  /*  max-width:calc(100% - 30px);*/
  /*}*/
  /*/deep/.el-dialog .el-dialog__body{*/
  /*  flex:1;*/
  /*  overflow: auto;*/
  /*}*/

</style>

