
<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>理赔作业组</span>
      </div>
      <el-form style="padding-bottom: 30px;" :rules="tableFormRules" ref="tableForm" :model="baseForm" size="mini">
        <el-row>
          <el-table
            ref="table"
            size="small"
            highlight-current-row
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            tooltip-effect="dark"
            :height="height"
            :data="baseForm.tableData"
            style="width: 100%;">
            <el-table-column label="作业组编码" prop="code" align="center" min-width="150px">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{scope.row.operationgroupno}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="类型" prop="type" align="center" min-width="150px">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.grouptype'" :rules="tableFormRules.grouptype">
                  <el-select v-model="scope.row.grouptype" placeholder="请选择" style="width:100%" @change="changeCom(scope.row.grouptype)">
                    <el-option label="保险公司" value="01"></el-option>
                    <el-option label="TPA" value="02"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{scope.row.grouptype=='01'?'保险公司':'TPA'}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="所属机构" prop="affiliation" align="center" min-width="150px">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.affiliation'" :rules="tableFormRules.affiliation">
                  <el-select v-model="scope.row.affiliation" placeholder="请选择">
                    <el-option v-for="item in comArr"
                               :key="item.managecode"
                               :label="item.managename"
                               :value="item.managecode"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <el-tooltip class="item" effect="dark" :content="scope.row.affiliationname" placement="top-start">
                    <span class="class_div">{{scope.row.affiliationname}}</span>
                    <!-- <el-button type="text" size="mini" @click="nodeHandel(scope.row)" v-model="scope.row.nodename">{{scope.row.nodename}}</el-button> -->
                  </el-tooltip>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="作业组名称" prop="groupname" align="center" min-width="200px">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.groupnoname'" :rules="tableFormRules.groupnoname">
                  <el-input maxlength="30" show-word-limit v-model="scope.row.groupnoname" placeholder="请输入" size="mini"/>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{scope.row.groupnoname}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作员数量" prop="num" align="center" min-width="80px">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button type="text" @click="operatorHandle(scope.row)" v-model="scope.row.operatornumber">{{scope.row.operatornumber}}</el-button>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="处理节点" prop="node" align="center" min-width="150px">
              <template slot-scope="scope">
                <el-form-item>
                  <el-tooltip class="item" effect="dark" :content="scope.row.nodename" placement="top-start">
                    <el-button type="text" size="mini" @click="nodeHandel(scope.row)" v-model="scope.row.nodename">{{scope.row.nodename}}</el-button>
                  </el-tooltip>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="案件分配规则" min-width="120" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button type="text" size="mini" @click="detailHandel(scope.row)">详情</el-button>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="状态" prop="status" align="center" min-width="130px">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.status'">
                  <el-select v-model="scope.row.status" placeholder="请选择">
                    <el-option label="有效" value="01"></el-option>
                    <el-option label="无效" value="02"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{scope.row.status == '01' ? '有效' : '无效'}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="创建日期" min-width="130" prop="createdate" align="center">
              <template slot-scope="scope">
                <el-form-item :prop="'tableData.' + scope.$index + '.creatDate'" :rules="tableFormRules.createdate">
                  <span>{{scope.row.createdate}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作" min-width="120" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                  <el-button :loading="delLoading" type="text" size="mini" @click="delHandle(scope.$index, scope.row)">删除</el-button>
                  <!--                  <el-button v-if="!scope.row.editFlag || scope.row.temp" type="text" size="mini" @click="delHandle(scope.$index, scope.row)">删除</el-button>-->
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addRowHandle"> + 添加</el-button>
        </el-row>
      </el-form>
      <div class="bottom clearfix" style="text-align: right;">
        <el-button :loading="saveLoad" type="primary" size="mini" class="button" @click="saveHandle">保存</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
  import {getGroupWorkList, addGroupWork, getGroupNo, getUsercode, queryTPAMangecom, queryBSMangecom, queryWorkGroupTrajectory, deleteOperationgroups}  from '@/api/groupManage/groupWork.js'
  import Cookies from 'js-cookie'
  import moment from 'moment'
  export default {
    data() {
      return {
        baseForm: {
          tableData: []
        },
        tableFormRules: {
          grouptype: [{ required: true, message: '不能为空!', trigger: 'change' }],
          affiliation: [{ required: true, message: '不能为空!', trigger: 'change' }],
          status: [{ required: true, message: '不能为空!', trigger: 'change' }],
          groupnoname: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          comCode: [{ required: true, message: '不能为空!', trigger: 'change' }]
        },
        address: [{
          value: 'zhinan',
          label: '指南',
          children: [{
            value: 'shejiyuanze',
            label: '设计原则',
            children: [{
              value: 'yizhi',
              label: '一致'
            }]
          }]
        }],
        flag: false,
        workNumber: '',
        TPAComArr: [],
        BSComArr: [],
        comArr: [],
        editForm: {},
        editSta: false,
        saveArr: [],
        checkList: [],
        editFlag: true,
        checkGroupList: [],
        saveLoad: false,
        delLoading: false,
        height: (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 340
      }
    },
    created() {
      let that = this
      window.onresize = function(){ // 定义窗口大小变更通知事件
        that.height = (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 340
      }
    },
    methods: {
      // 跳转到节点页面
      nodeHandel(row){
        if (this.allowCheck(row)) {
          this.$router.push({
            path: '/job-manage/auth',
            query: {
              operationgroupno: row.operationgroupno
            }
          })
        } else {
          this.$message.warning('请先保存！')
        }
      },
      // 查看详情页面
      detailHandel(row){
        if (this.allowCheck(row)) {
          this.$router.push({
            path: '/job-manage/distribut',
            query: {
              operationgroupno: row.operationgroupno
            }
          })
        } else {
          this.$message.warning('请先保存！')
        }
      },
      // 跳转到操作员页面
      operatorHandle(row){
        if (this.allowCheck(row)) {
          this.$router.push({
            path: '/job-manage/personal',
            query: {
              operationgroupno: row.operationgroupno,
              affiliation: row.affiliation,
              affiliationname: row.affiliationname
            }
          })
        } else {
          this.$message.warning('请先保存！')
        }
      },
      // 添加一列数据
      addRowHandle(){
        if (this.flag == false) {
          let data = '01'
          getGroupNo(data).then(res =>{
            this.workNumber = res.data
            const field = {
              operationgroupno: this.workNumber ,
              grouptype: '',
              groupnoname: '',
              operatornumber: 0,
              nodename: 'N/A',
              affiliation: '',
              createdate: moment().format("YYYY-MM-DD"),
              status: '01',
              temp: true
            }
            this.baseForm.tableData.push(field)
            setTimeout(() => {
              this.$refs.table.setCurrentRow(field)
              this.flag = true
            }, 10)
          })
        } else {
          this.$message.warning('请先保存！')
        }
      },
      // 添加时删除当前行
      delHandle(index,row){
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 保存无效作业组时检验案件
          let groups = [row.operationgroupno]
          queryWorkGroupTrajectory(groups).then(res => {
            if (res.status === '1') {
              if (JSON.stringify(res.data) !== JSON.stringify({})) {
                let mess = []
                Object.keys(res.data).forEach(item => {
                  mess.push(item)
                })
                this.$message.warning('当前' + mess.join('、') + '，作业组操作员存在未处理完成的任务信息，请处理完成后，再进行操作！')
              } else {
                this.delLoading = true
                deleteOperationgroups(row.operationgroupno).then(res => {
                  if (res.status === '1') {
                    this.baseForm.tableData.splice(index, 1)
                    this.flag = false
                    this.$message.success('删除成功！')
                  } else {
                    this.$message.error('删除失败！')
                  }
                  this.delLoading = false
                })
              }
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      // 查询当前列表
      getGroupsList() {
        const data = {
          "operationgroupno": "",
          "pageno": 1,
          "pagesize": 100
        }
        getGroupWorkList(data).then(res=>{
          if (res.status == '1') {
            this.baseForm.tableData = res && res.data && res.data.list
            this.baseForm.tableData.map((data,index) =>{
              data.id = index+1
              data.editFlag = true
            })
            this.checkList = JSON.parse(JSON.stringify(res.data.list))
          } else {
            this.$message({
              type: 'error',
              message: '查询结果异常！'
            })
          }
        })
      },
      // 添加时获取作业组号
      getGroupWorkNo() {
        let data = '01' // 作业组号=‘01’ 规则编码 = ‘02’
        getGroupNo(data).then(res =>{
          if (res.status == '1') {
            this.workNumber = res.data
          } else {
            this.$message({
              type: 'error',
              message: '查询作业组号异常！'
            })
          }
        })
      },
      getUsercode() {
        getUsercode().then(res => {
        })
      },
      // 编辑当前行
      editHandle(i, row){
        row.editFlag = false
        if (row.grouptype ==='01') {
          this.comArr = this.BSComArr
        } else {
          this.comArr = this.TPAComArr
        }
        this.baseForm.tableData[i].id = ''
        this.editForm = row
        this.editSta = true
      },
      // 保存前不可编辑
      allowCheck(row) {
        return this.checkList.some(item => item.operationgroupno === row.operationgroupno)
      },
      // 保存当前的编辑及新增
      saveHandle(){
        this.$refs.tableForm.validate((valid) => {
          if (valid) {
            this.checkGroupList = []
            this.saveArr = this.baseForm.tableData.filter((item,index) => {
              return item.affiliation == item.affiliation
              item.groupnoname == item.groupnoname
              item.grouptype == item.grouptype
              item.operationgroupno == item.operationgroupno
              item.status == item.status
            })
            let groups = []
            this.checkGroupList = new Map()
            this.saveArr.forEach(item => {
              this.checkGroupList.set(item.operationgroupno, item.groupnoname)
              if (item.status === '02') {
                groups.push(item.operationgroupno)
              }
            })
            this.saveLoad = true
            // 保存无效作业组时检验案件,作业组全部有效时，不调用
            if (groups.length > 0) {
              queryWorkGroupTrajectory(groups).then(res => {
                if (res.status === '1') {
                  if (JSON.stringify(res.data) !== JSON.stringify({})) {
                    let mess = []
                    Object.keys(res.data).forEach(item => {
                      mess.push(item)
                    })
                    // res.data.forEach(item => {
                    //   let code = Object.keys(item)[0]
                    //   mess.push(this.checkGroupList.get(code))
                    // })
                    this.$message.warning('当前' + mess.join('、') + '，作业组操作员存在未处理完成的任务信息，请处理完成后，再进行操作！')
                  } else {
                    let params = {
                      operationgroupVOList: this.saveArr
                    }
                    addGroupWork(params).then(res => {
                      if (res.status == '1') {
                        this.getGroupsList()
                        this.flag = false
                        this.$message({
                          type: 'success',
                          message: '保存成功！'
                        })
                      } else {
                        this.$message({
                          type: 'error',
                          message: '保存异常！'
                        })
                      }
                    })
                  }
                }
                this.saveLoad = false
              })
            } else {
              let params = {
                operationgroupVOList: this.saveArr
              }
              addGroupWork(params).then(res => {
                if (res.status == '1') {
                  this.getGroupsList()
                  this.flag = false
                  this.$message({
                    type: 'success',
                    message: '保存成功！'
                  })
                } else {
                  this.$message({
                    type: 'error',
                    message: '保存异常！'
                  })
                }
                this.saveLoad = false
              })
            }
          } else {
            return false;
          }
        });
      },
      // 作业组保存，无效时检查作业组下案件
      queryWorkGroupTrajectory(data) {
        this.checkGroupList = []
        queryWorkGroupTrajectory(data).then(res => {
          if (res.status === '1' && res.data) {
            this.checkGroupList = res.data
          }
        })
        return this.checkGroupList
      },
      // 查询TPA下所属公司
      getTpaCom() {
        queryTPAMangecom().then(res => {
          if (res.status == '1') {
            this.TPAComArr = res.data || []
          } else {
            this.$message({
              type: 'error',
              message: '查询TPA所属公司异常！'
            })
          }
        })
      },
      // 查询保险公司
      getBsCom() {
        queryBSMangecom().then(res => {
          if (res.status == '1') {
            this.BSComArr = res.data || []
          } else {
            this.$message({
              type: 'error',
              message: '查询保险公司异常！'
            })
          }
        })
      },
      changeCom(data) {
        this.comArr = []
        if (data ==='01') {
          this.comArr = this.BSComArr
        } else {
          this.comArr = this.TPAComArr
        }
      }
    },
    mounted() {
      this.getGroupsList()
      // this.getUsercode()
      this.getTpaCom()
      this.getBsCom()
    },
  }
</script>
<style scoped>
  .form-span {
    display: inline-block;
    margin-bottom: 10px;;
  }
  .class_div{
    display: inline-block;
    width: 90%;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
    padding: 0 3px;
  }
  .el-form-item--mini {
    margin-bottom: 0;
    padding-bottom: 0;
  }
  .el-form-item--mini /deep/ .el-form-item__content {
    height: 28px;
  }
</style>
