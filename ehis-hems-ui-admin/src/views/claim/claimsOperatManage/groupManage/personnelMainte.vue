
<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>人员清单</span>
      </div>
      <el-form style="padding-bottom: 30px;" :rules="tableFormRules"  ref="tableForm" :model="baseForm" size="mini">
        <el-row>
          <el-table
            ref="table"
            size="small"
            highlight-current-row
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            tooltip-effect="dark"
            :data="baseForm.tableData"
            style="width: 100%;">
            <el-table-column label="所属机构" prop="affiliation" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{scope.row.affiliation}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作用户" prop="operatUser" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.usercode'" :rules="tableFormRules.usercode">
                  <el-select style="width: 100%" v-model="scope.row.usercode" placeholder="请选择操作员"  @change="changeRole(scope.$index,scope.row.usercode)">
                    <el-option v-for="(item,index) in userArr"
                               :key="index"
                               :disabled="baseForm.tableData.find(f=>{
                      return f.usercode == item.username }) != null"
                               :label="item.username"
                               :value="item.username"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{scope.row.username}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="上级用户" prop="upperusercode" align="center">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.upperusercode'" :rules="tableFormRules.upperusercode">
                  <el-select style="width: 100%" v-model="scope.row.upperusercode" placeholder="请选择操作员" @click.native="queryUpperUser(scope.row.usercode)">
                    <el-option v-for="(item,index) in uuperArr"
                               :key="index"
                               :label="item.username"
                               :value="item.username"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{scope.row.upperusername}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="角色" prop="department" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{scope.row.role}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="授权金额区间" prop="department" align="center" class-name="department">
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.id" :prop="'tableData.' + scope.$index + '.amount1'">
                  <el-row>
                    <el-col :span="11" style="margin-top:15px">
                      <el-form-item :prop="'tableData.' + scope.$index + '.amount1'" :rules="tableFormRules.amount1">
                        <el-input maxlength="100" v-model="scope.row.amount1" placeholder="请输入" size="mini"/>
                      </el-form-item>
                    </el-col>
                    <el-col :span="2" style="margin-top:15px">
                      <span> - </span>
                    </el-col>
                    <el-col :span="11" style="margin-top:15px">
                      <el-form-item :prop="'tableData.' + scope.$index + '.amount2'" :rules="tableFormRules.amount2">
                        <el-input maxlength="100" v-model="scope.row.amount2" placeholder="请输入" size="mini"/>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-form-item v-else>
                  <span>{{scope.row.amount1}}-{{scope.row.amount2}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="创建日期" width="130" prop="startDate" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <span>{{scope.row.createdate}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="120" align="center">
              <template slot-scope="scope">
                <el-form-item>
                  <el-button  type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                  <el-button  type="text" size="mini" @click="delHandle(scope.$index, scope.row)">删除</el-button>
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addRowHandle"> + 添加</el-button>
        </el-row>
      </el-form>
      <div class="bottom clearfix" style="text-align: right;">
        <el-button :loading="saveLoad" type="primary" size="mini" class="button" @click="saveHandle">保存</el-button>
        <el-button type="primary" size="mini" class="button" @click="goBack">返回</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
  import { getGroupUsers, queryUser, addGroupUsers, delateOne, queryComcodeUserUpperDetail,queryUserTrajectory} from '@/api/groupManage/groupWork.js'
  import moment from 'moment'
  export default {
    data() {
      const checkMoney = (rule, value, callback) => {
        if (value || value == 0) {
          if (value >= 0) {
            let str = /^(\d+|\d+\.\d{1,2})$/
            if (str.test(value)) {
              callback()
            } else {
              callback(new Error('请输入大于等于0的数字，最多允许两位小数'))
            }
          } else {
            callback(new Error('请输入大于等于0的数字，最多允许两位小数'))
          }
        } else {
          callback(new Error('不能为空'))
        }
      }
      return {
        baseForm: {
          tableData: []
        },
        tableFormRules: {
          comCode: [{ required: true, message: '不能为空!', trigger: 'change' }],
          usercode: [{ required: true, message: '不能为空!', trigger: 'change' }],
          upperusercode: [{ required: true, message: '不能为空!', trigger: 'change' }],
          enddate: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          amount1: [{ validator: checkMoney, trigger: 'change' }],
          amount2: [{ validator: checkMoney, trigger: 'change' }]
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
        userArr: [],
        uuperArr: [],
        editForm: {},
        saveArr: [],
        saveLoad: false
      }
    },
    methods: {
      // 添加一行数据
      addRowHandle(){
        const field = {
          affiliation: this.$route.query.affiliationname,
          role: '',
          createdate: moment().format("YYYY-MM-DD"),
          id: '',
          temp: true
        }
        this.baseForm.tableData.push(field)
        setTimeout(() => { this.$refs.table.setCurrentRow(field) }, 10)
      },
      // 删除一行数据
      delHandle(index,row){
        this.$confirm(`是否确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let data = {
            usercode: row.usercode,
          }
          if (row.id !== '') {
            queryUserTrajectory(row.usercode).then(res =>{
              if (res.data == 0) {
                let params = {
                  usercode: row.username,
                  "operationgroupno": this.$route.query.operationgroupno
                }
                delateOne(params).then(res =>{
                }).then(()=>{
                  // this.getList()
                  this.baseForm.tableData.splice(index, 1)
                  this.$message.success('删除成功！')
                })
              } else {
                this.$message.warning('当前用户存在待处理案件，请处理后操作！')
              }
            })
          } else {
            this.baseForm.tableData.splice(index, 1)
            this.$message.success('删除成功！')
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消！'
          })
        })
      },
      // 编辑当前行
      editHandle(i, row){
        this.baseForm.tableData = [...this.baseForm.tableData]
        this.editForm = row
        this.baseForm.tableData[i].id = ''
        this.baseForm.tableData[i].temp = true
      },
      goBack(){
        this.$router.go(-1)
      },
      // 添加保存
      saveHandle(){
        this.$refs.tableForm.validate((valid) => {
          if (valid) {
            let _this = this
            let amountFlag = false
            this.saveArr = this.baseForm.tableData.filter((item,index) =>{
              return item.amount1 == item.amount1
              item.amount2 == item.amount2
              item.upperusercode == item.upperusername || ''
              item.upperusername == item.upperusername || ''
              item.usercode == item.username || ''
              item.username == item.username || ''
              item.createdate == item.createdate || ''
            })
            this.saveArr.forEach(item => {
              if (parseInt(item.amount1) > parseInt(item.amount2)) {
                amountFlag = true
              }
            })
            if (amountFlag) {
              this.$message.warning('授权金额区间第一个数小于等于第二个数！')
              return
            }
            let params = {
              operationgroupUserVOList: this.saveArr,
              "operationgroupno": this.$route.query.operationgroupno
            }
            this.saveLoad = true
            addGroupUsers(params).then(res => {
              if (res.status == '1') {
                this.$message.success('保存成功！')
                this.getList()
              } else {
                this.$message.error('保存失败！')
              }
              this.saveLoad = false
            })
          } else {
            return false;
          }
        });
      },
      // 查询人员维护列表
      getList() {
        let data = {
          "operationgroupno": this.$route.query.operationgroupno,
          "affiliation": this.$route.query.affiliation,
          "pageno": 1,
          "pagesize": 1000
        }
        getGroupUsers(data).then(res =>{
          if (res.status == '1') {
            this.baseForm.tableData = res && res.data && res.data.list
            this.baseForm.tableData.map((data,index) =>{
              data.id = index+1
            })
          } else {
            this.$message.error('查询人员维护列表异常！')
          }
        }).finally(res => {
          this.getUserList()
        })
      },
      // 查询操作用户上级用户
      queryUpperUser(currentUser) {
        this.uuperArr = []
        if (!currentUser) {
          return
        }
        queryComcodeUserUpperDetail(currentUser).then(res => {
          if (res.status === '1') {
            this.uuperArr = res.data
          } else {
            this.$message.error('查询操作用户上级用户异常！')
          }
        })
      },
      // 查询操作用户
      getUserList() {
        queryUser(this.$route.query.affiliation).then(res => {
          this.userArr = []
          if (res.status == '1') {
            this.baseForm.tableData.forEach(item => {
              this.userArr.push({ username: item.username })
              // this.uuperArr.push({ username: item.username })
            })
            let newArr = JSON.parse(JSON.stringify(res.data)) || []
            newArr.forEach(item => {
              this.userArr.push({ username: item.username, name: item.name })
              // this.uuperArr.push({ username: item.username })
            })
            // this.userArr = JSON.parse(JSON.stringify(res.data)) || []
            // this.uuperArr = JSON.parse(JSON.stringify(res.data)) || []
          } else {
            this.$message.error('查询操作用户异常！')
          }
        })
      },
      // 选择操作员显示当前操作员的角色
      changeRole(i,id) {
        let newArr = this.userArr.filter(item => item.username == id)
        this.baseForm.tableData[i].role = newArr[0].name
        this.queryUpperUser(id)
      },
    },
    mounted () {
      this.getList()
      // this.getUserList()
    },
  }
</script>
<style scoped>
  .form-span {
    display: inline-block;
    margin-bottom: 10px;;
  }
  .el-form-item--mini {
    margin-bottom: 0;
    padding-bottom: 0;
  }
  .el-form-item--mini /deep/ .el-form-item__content {
    height: 28px;
  }
</style>
<style>
  /* .department .el-form-item--mini.el-form-item {
    margin-bottom: 10px;
  } */
</style>
