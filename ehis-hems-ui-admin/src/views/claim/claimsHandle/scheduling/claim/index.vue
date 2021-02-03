<template>
  <div class="app-container">

    <el-card class="box-card">
      <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
        <span>查询条件</span>
      </div>
      <el-form ref="form" :model="form" style="" label-width="130px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="等级：" prop="level">
              <el-select v-model="form.level" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in ysOrNo" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
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

            <el-table-column prop="idNoType" label="角色"  align="center" show-overflow-tooltip />
            <el-table-column prop="name" label="操作用户"  align="center" show-overflow-tooltip />
            <el-table-column prop="idNo" label="分配比例"   align="center" show-overflow-tooltip />
            <el-table-column prop="idNo" label="是否有效"    align="center" show-overflow-tooltip />
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
    <user-modal :value="diaVisible"  :fixInfo="fixInfo"  @closeDialogVisable="closeDialogVisable"/>

    <!-- 一键分配弹框 -->
    <assign-modal :value="assignDiaVisible"  :fixInfo="fixInfo"  @closeAssignDiaVisible="closeAssignDiaVisible"/>

  </div>
</template>

<script>

  import userModal from './components/user'
  import assignModal from './components/assign'



  import moment from 'moment'

  import { listInfo , editData,debtWhiteInfo,checkMoney} from '@/api/recoveryRoster/api'

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
                level:'',
              },
              fixInfo: {

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
              diaVisible: false,
              addFlag:false,
              ysOrNo:[],

            }
        },
      mounted(){
        // sys_yes_no
        this.getDicts("sys_yes_no").then(response => {
          this.ysOrNo = response.data;
        });
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

          const params = {
            pageNum:this.pageInfo.currentPage,
            pageSize:this.pageInfo.pageSize,
            orderByColumn:'create_time',
            isAsc:'desc'
          };


          listInfo(params).then(response => {
               this.totalNum = response.total;
               this.tableData = response.rows;
                this.loading = false
          }).catch(error => {
            console.log(error);
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
          this.addFlag = false;
          this.diaVisible = true;
          // this.recoveryForm.debtWhitelistId =row.debtWhitelistId;
          // this.recoveryForm.level = row.level;
          // this.recoveryForm.debtAmountUp = row.debtAmountUp;
          // this.recoveryForm.recMessageFlag = row.recMessageFlag;
          // this.recoveryForm.insuredNo =  row.insuredNo;
        },
        saveDataFun(){
          // if(this.recoveryForm.insuredNo == '') {
          //   this.$message({
          //     message: '请先查询基本信息！',
          //     type: 'info',
          //     center: true,
          //     showClose: true
          //   });
          //   return false;
          // }

          this.$refs.recoveryForm.validate((valid) => {
            if (valid) {
              const params = this. recoveryForm;
              editData(params).then(response => {
                this.diaVisible = false;
                this.gettableData();
                if (response.code == '200') {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  });
                } else {
                  this.$message({
                    message: '保存失败！',
                    type: 'error',
                    center: true,
                    showClose: true
                  });
                }
              }).catch(error => {
                console.log(error);
              });
            }
          })
        },
      }
    }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  ::v-deep.el-table .warning-row {
    background: oldlace;
  }
  .font_grey {
    color: #BFBFBF;
  }
</style>

