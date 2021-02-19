<template>
  <div class="app-container">

    <el-card class="box-card">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>查询条件</span>
      </div>
      <el-form ref="form" :model="form" style="" label-width="130px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="角色查询：" prop="roleId">
              <el-select v-model="form.roleId" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in roleSelects" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
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
    </el-card>

    <el-card class="box-card">
        <div>
          <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>角色列表</span>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" @click="assignFun" size="mini">一键均分</el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}">

            <el-table-column prop="roleName" label="角色"  align="center" show-overflow-tooltip />
            <el-table-column prop="userName" label="操作用户"  align="center" show-overflow-tooltip />
            <el-table-column prop="rate" label="分配比例" :formatter="rateOrEqually"  align="center" show-overflow-tooltip />
            <el-table-column prop="status" label="是否有效" :formatter="getStatusName" align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button   size="mini" type="text" icon="el-icon-edit" @click="editFun(scope.row)">编辑</el-button>
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
      </el-card>

    <!-- 编辑弹框 -->
    <user-modal :value="diaVisible"  :fixInfo="fixInfo"  @closeDialogVisable="closeDialogVisable" @gettableData="gettableData"/>

    <!-- 一键分配弹框 -->
    <assign-modal :value="assignDiaVisible"  :roleSelects="roleSelects"  @closeAssignDiaVisible="closeAssignDiaVisible" @gettableData="gettableData"/>

  </div>
</template>

<script>

  import userModal from './components/user'
  import assignModal from './components/assign'



  import moment from 'moment'

  import { listInfo ,roleAll } from '@/api/scheduling/claimApi'

    export default {
      filters: {
        changeDate: function(value) {
          if (value !== null) {
            return moment(value).format('YYYY-MM-DD')
          }
        }
      },
      components: {
        userModal,assignModal
      },
        data() {
            return {
              form:{
                roleId:'',
              },
              fixInfo: {
                rowdata :'',
                type :''
              },
              tableData: [],
              totalNum: 0,
              pageInfo: {
                  currentPage: 1,
                  pageSize: 10,
                  pageSizes:[5,10,20,30,50,100]
              },
              loading: false,
              diaVisible:false,
              assignDiaVisible:false,
              recoveryForm : {
                level: '',
                recMessageFlag:'',
                debtAmountUp :'',
                debtWhitelistId:'',
                insuredNo:'',
              },
              rules: {
                level: {trigger: ['change'], required: true, message: '等级必填'},
                recMessageFlag: {trigger: ['change'], required: true, message: '缴费通知必填'},
                debtAmountUp: {trigger: ['change'], required: true, message: '金额上限必填'},
              },
              ysOrNo:[],
              roleSelects:[],

            }
        },
      mounted(){
        // sys_yes_no
        this.getDicts("sys_yes_no").then(response => {
          this.ysOrNo = response.data;
        });
        this.getAllRole();
      },
      computed: {

      },

      created: function() {
        this.initData();
      },
      methods: {

        closeDialogVisable() {
          this.diaVisible = false
        },
        closeAssignDiaVisible() {
          this.assignDiaVisible = false
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

          this.loading = true;
          const params = {
            pageNum:this.pageInfo.currentPage,
            pageSize:this.pageInfo.pageSize,
            roleId: this.form.roleId,
            orderByColumn:'t1.create_time',
            isAsc:'desc'
          };

          listInfo(params).then(response => {
            this.totalNum = response.total;
            this.tableData = response.rows;
            this.loading = false
          }).catch(error => {
            console.log(error);
            this.loading = false
          });
        },
        searchByFormParms(){
          this.pageInfo.currentPage = 1;
          this.pageInfo.pageSize = 10;
          this.gettableData();
        },
        assignFun(){
          this.assignDiaVisible = true;
        },
        editFun(row) {
          this.fixInfo = {
            rowdata :row,
            type :'edit'
          };
          this.diaVisible = true;
        },
        getStatusName(row, col){
          if(row.status == 'Y' || row.status == '01') {
            return '有效';
          } else {
            return '无效';
          }
        },
        rateOrEqually(row, col){
          if(row.isEqually == '01') {
            return '均分';
          } else {
            return row.rate;
          }
        },
        getAllRole(){
          const query ={
           // status:'Y',
            xtype:'roleAll',
          };
          roleAll(query).then(response => {
            if(response.rows) {
              for(let i=0; i<response.rows.length; i++) {
                let obj= new Object();
                obj.dictLabel = response.rows[i].roleName;
                obj.dictValue = response.rows[i].roleId.toString();
                this.roleSelects.push(obj);
              }
            }
          }).catch(error => {
            console.log(error);
          })
        },
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
  .font_grey {
    color: #BFBFBF;
  }
</style>

