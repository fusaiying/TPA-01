<template>
  <div class="app-container">
    <el-card class="box-card">
        <div>
          <div style="line-height: 50px;margin-right: 10px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
            <span>角色清单</span>
            <el-button  style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="addUser">新增</el-button>
          </div>
          <el-table
            :data="tableData"
            v-loading="loading"
            size="mini"
            tooltip-effect="darky"
            class="receive_table"
            :header-cell-style="{color:'black',background:'#f8f8ff'}">

            <el-table-column prop="updateBy" label="操作用户"  align="center" show-overflow-tooltip />
            <el-table-column prop="roleName" label="角色"  align="center" show-overflow-tooltip />
            <el-table-column prop="userName" label="承接人"   align="center" show-overflow-tooltip />
            <el-table-column prop="status" label="承接状态" :formatter="getStatusName"   align="center" show-overflow-tooltip />
            <el-table-column label="操作" align="center" style="padding-top: 0px;">
              <template slot-scope="scope">
                <el-button   size="mini" type="text" icon="el-icon-edit" @click="editFun(scope.row)">编辑</el-button>
                <el-button   size="mini"  type="text" icon="el-icon-delete" @click="delFun(scope.row)" >删除
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
      </el-card>

    <!-- 保单信息查询模态框 -->
    <user-modal :value="diaVisible" :fixInfo="fixInfo"  @closeDialogVisable="closeDialogVisable" @gettableData="gettableData"/>

  </div>
</template>

<script>

  import moment from 'moment'
  import userModal from './components/user'

  import { listInfo,editInfo  } from '@/api/scheduling/userApi'

    export default {
      filters: {
        changeDate: function(value) {
          if (value !== null) {
            return moment(value).format('YYYY-MM-DD')
          }
        }
      },
      components: {
        userModal
      },
        data() {
            return {
                fixInfo: {
                  rowdata:'',
                  type:'',
                },
                tableData: [],
                totalNum: 0,
                pageInfo: {
                    currentPage: 1,
                    pageSize: 10,
                    pageSizes:[5,10,20,30,50,100]
                },
                loading: false,
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
        initData(){
          this.gettableData();
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
        editFun(row) {
          this.diaVisible = true;
          this.fixInfo = {
            rowdata :row,
            type :'edit'
          };
        },
        delFun(row) {
          let vm = this;
          this.$confirm('确定删除?', "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function () {
            const params = {
              takeOnId:row.takeOnId,
              status:'N'
            };
            editInfo(params).then(response => {
              if (response.code == '200') {
                vm.$message({
                  message: '删除成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                });
                vm.gettableData();
              } else {
                vm.$message({
                  message: '删除失败！',
                  type: 'error',
                  center: true,
                  showClose: true
                });
              }
            }).catch(error => {
              console.log(error);
            });
          }).then(() => {
          }).catch(function (error) {
            console.log(error)
          });

        },
        addUser() {
          this.diaVisible = true;
          this.fixInfo = {
            rowdata :{},
            type :'add'
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

