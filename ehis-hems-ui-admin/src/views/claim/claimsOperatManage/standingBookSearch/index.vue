<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="rptno">
              <el-input v-model="searchForm.rptno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件号码：" prop="idno">
              <el-input v-model="searchForm.idno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保险人：" prop="name">
              <el-input v-model="searchForm.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="快递号：" prop="expressnumber">
              <el-input v-model="searchForm.expressnumber" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交件人：" prop="sendby">
              <el-input v-model="searchForm.sendby" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="接单日期：" prop="receiveDate">
              <el-date-picker
                v-model="searchForm.receiveDate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="机构：" prop="organcode">
              <el-select v-model="searchForm.organcode" class="item-width" placeholder="请选择"
                         remote
                         clearable
                         reserve-keyword
                         filterable
                         :remote-method="remoteMethod"
                         @change="getUsers">
                <el-option v-for="option in deptOptions" :key="option.organCode"
                           :label="option.organName"
                           :value="option.organCode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="createBy">
              <el-select v-model="searchForm.createBy" class="item-width" placeholder="请选择"
                         remote
                         clearable
                         reserve-keyword
                         filterable
                         :remote-method="remoteUserMethod">
                <el-option v-for="option in userOptions" :key="option.userName"
                           :label="option.userName"
                           :value="option.userName"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              search('form')
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>台账信息列表（{{totalCount}}）</span>
          <span style="float: right;">
            <el-button type="primary" size="mini" @click="listExport">清单导出</el-button>
          </span>
        </div>
        <el-form ref="standingForm" :rules="standingRules" :model="standingForm" size="small">
          <el-table
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="standingForm.tableData"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style=" width: 100%;">
            <el-table-column align="center" prop="rptno" width="90" label="报案号" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.isEdit"
                              :prop="'tableData.' + scope.$index + '.rptno'"
                              :rules="standingRules.rptno" style="display: inline-flex !important;">
                  <el-input v-model="scope.row.rptno" placeholder="请输入" size="mini"/>
                </el-form-item>
                <span v-if="scope.row.isEdit">{{ scope.row.rptno}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="idno" width="90" label="证件号码" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.isEdit" :prop="'tableData.' + scope.$index + '.idno'"
                              :rules="standingRules.idno" style="display: inline-flex !important;">
                  <el-input v-model="scope.row.idno" placeholder="请输入" size="mini"/>
                </el-form-item>
                <span v-if="scope.row.isEdit">{{ scope.row.idno}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="name" width="90" label="被保险人" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.isEdit" :prop="'tableData.' + scope.$index + '.name'"
                              :rules="standingRules.name" style="display: inline-flex !important;">
                  <el-input v-model="scope.row.name" placeholder="请输入" size="mini"/>
                </el-form-item>
                <span v-if="scope.row.isEdit">{{ scope.row.name}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="claimmaterialList" label="理赔材料" width="130" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-select v-if="!scope.row.isEdit" multiple size="mini" v-model="scope.row.claimmaterialList"
                           placeholder="请选择">
                  <el-option v-for="option in claim_materialOptions" :key="option.dictValue"
                             :label="option.dictLabel"
                             :value="option.dictValue"/>
                </el-select>
                <span class="form-span"
                      v-if="scope.row.isEdit">{{getClaimmaterials(scope.row.claimmaterialList)}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="remark" width="90" label="备注" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.isEdit" :prop="'tableData.' + scope.$index + '.remark'"
                              :rules="standingRules.remark" style="display: inline-flex !important;">
                  <el-input v-model="scope.row.remark" placeholder="请输入" size="mini"/>
                </el-form-item>
                <span v-if="scope.row.isEdit">{{ scope.row.remark}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="otherinfo" width="110" label="其他(案件去向)" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-form-item v-if="!scope.row.isEdit" :prop="'tableData.' + scope.$index + '.otherinfo'"
                              :rules="standingRules.otherinfo" style="display: inline-flex !important;">
                  <el-input v-model="scope.row.otherinfo" placeholder="请输入" size="mini"/>
                </el-form-item>
                <span v-if="scope.row.isEdit">{{ scope.row.otherinfo}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="expressnumber" label="快递号" show-overflow-tooltip/>
            <el-table-column align="center" prop="receivedate" label="接单日期" show-overflow-tooltip/>
            <el-table-column align="center" prop="sendby" label="交件人" width="100" show-overflow-tooltip/>
            <el-table-column align="center" prop="companyName" label="出单公司" show-overflow-tooltip/>
            <el-table-column align="center" prop="organcode" label="机构" width="100" show-overflow-tooltip>
              <template slot-scope="scope">
                <span>{{selectDeptName(sysDeptOptions,scope.row.organcode)}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="createBy" label="操作人" show-overflow-tooltip/>
            <el-table-column align="center" label="操作" fixed="right">
              <template slot-scope="scope">
                <el-button v-if="scope.row.isEdit" size="mini" type="text" @click="scope.row.isEdit=false">编辑
                </el-button>
                <el-button v-if="!scope.row.isEdit" size="mini" type="text" @click="save(scope.row)">保存</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="search('table')"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import {listNew, editStanding, getUserInfo, getOrganList,getUsersByOrganCode, listFirst} from '@/api/claim/standingBookSearch'

  let dictss = [{dictType: 'claim_material'}]
  export default {
    data() {
      return {
        isListExport: false,
        organCode: '',
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },

        standingForm: {
          tableData: []
        },
        standingRules: {
          idno: [{required: true, message: '请输入', trigger: 'blur'}],
        },
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          rptno: '',
          idno: '',
          name: '',
          expressnumber: '',
          sendby: '',
          receiveDate: [],
          receiveStartDate: '',
          receiveEndDate: '',
          organcode: undefined,//机构
          createBy: ''//操作人
        },
        totalCount: 0,
        dictList: [],
        claim_materialOptions: [],
        deptOptions: [],
        sysDeptOptions: [],
        userOptions: [],
      }
    },
    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.claim_materialOptions = this.dictList.find(item => {
        return item.dictType === 'claim_material'
      }).dictDate
      listFirst(this.searchForm).then(res => {
        if (res != null && res.code === 200) {
          this.standingForm.tableData = res.rows
          this.standingForm.tableData.forEach(item => {
            item.isEdit = true
          })
          this.totalCount = res.total
        }
      }).catch(res => {
      })
      getUserInfo().then(res => {
        if (res != null && res.code === 200) {
          this.organCode=res.data.organCode
          let item = {
            organCode: '',
            pageNum: 1,
            pageSize: 200,
          }
          if (res.data != null) {
            item.organCode = res.data.organCode
            this.searchForm.createBy = res.data.userName
          }
          getOrganList(item).then(response => {
            if (response != null && response.code === 200) {
              this.deptOptions = response.rows
              this.sysDeptOptions = response.rows
              this.searchForm.organcode = res.data.organCode
              let option = {
                organCode: this.searchForm.organcode ,
                pageNum: 1,
                pageSize: 200,
              }
              getUsersByOrganCode(option).then(res => {
                if (res!=null && res.code===200){
                  this.userOptions=res.rows
                }
              })
            }
          }).catch(res => {
          })
        }
      })
    },
    methods: {
      resetForm() {
        this.searchForm.rptno = '',
          this.searchForm.idno = '',
          this.searchForm.name = '',
          this.searchForm.expressnumber = '',
          this.searchForm.sendby = '',
          this.searchForm.receiveDate = [],
          this.searchForm.receiveStartDate = '',
          this.searchForm.receiveEndDate = '',
          this.searchForm.organcode = undefined,
          this.searchForm.createBy = ''//操作人
      },
      search(val) {
        let data = {
          pageNum: 1,
          pageSize: 10,
          rptno: this.searchForm.rptno,
          idno: this.searchForm.idno,
          name: this.searchForm.name,
          expressnumber: this.searchForm.expressnumber,
          sendby: this.searchForm.sendby,
          receiveStartDate: this.searchForm.receiveDate ? this.searchForm.receiveDate[0] : '',
          receiveEndDate: this.searchForm.receiveDate ? this.searchForm.receiveDate[1] : '',
          organcode: this.searchForm.organcode,//机构
          createBy: this.searchForm.createBy//操作人
        }

        data.pageNum = this.queryParams.pageNum
        data.pageSize = this.queryParams.pageSize
        let day=0
        const getDaysDiffBetweenDates = (dateInitial, dateFinal) =>
          (dateFinal - dateInitial) / (1000 * 3600 * 24);
        if (data.receiveStartDate!=null && data.receiveStartDate!='' && data.receiveEndDate!=null && data.receiveEndDate!=''){
          day=getDaysDiffBetweenDates(new Date(data.receiveStartDate), new Date(data.receiveEndDate));
        }
        if (day>90){
          this.$confirm(`时间跨度过长，是否确认查询?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            listNew(data).then(res => {
              if (res != null && res.code === 200) {
                this.standingForm.tableData = res.rows
                this.standingForm.tableData.forEach(item => {
                  item.isEdit = true
                })
                this.totalCount = res.total
                if (res.rows.length <= 0) {
                  return this.$message.warning(
                    "未查询到数据！"
                  )
                }
              }
            }).catch(res => {
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消！'
            })
          })
        }else {
          listNew(data).then(res => {
            if (res != null && res.code === 200) {
              this.standingForm.tableData = res.rows
              this.standingForm.tableData.forEach(item => {
                item.isEdit = true
              })
              this.totalCount = res.total
              if (res.rows.length <= 0) {
                return this.$message.warning(
                  "未查询到数据！"
                )
              }
            }
          }).catch(res => {
          })
        }
      },
      listExport() {
        this.searchForm.pageNum = 1
        this.searchForm.pageSize = 10
        this.searchForm.receiveStartDate = this.searchForm.receiveDate ? this.searchForm.receiveDate[0] : ''
        this.searchForm.receiveEndDate = this.searchForm.receiveDate ? this.searchForm.receiveDate[1] : ''
        listNew(this.searchForm).then(res => {

          if (res.rows.length > 0) {
            this.isListExport = true
            /* let subDate = ''
             if (this.searchForm.receiveDate.length > 0) {
               subDate = '&receiveStartDate=' + this.searchForm.receiveDate[0] + '&receiveEndDate=' + this.searchForm.receiveDate[1]
             }*/
            this.download('claimmgt/standing/exportNew' /*+ '?expressnumber=' + this.searchForm.expressnumber + '&sendby=' + this.searchForm.sendby
              + '&organcode=' + this.searchForm.organcode + '&createBy=' + this.searchForm.createBy + subDate*/, {
              ...this.searchForm
            }, `standingBook_${new Date().getTime()}.xlsx`)
          } else {
            return this.$message.warning(
              "没有查询到能导出的数据！"
            )
          }
        }).catch(res => {

        })
      },
      getClaimmaterials(value) {
        let material = ''
        if (value != null && value.length > 0) {
          for (let i = 0; i < value.length; i++) {
            if (i === value.length - 1) {
              material = material + this.selectDictLabel(this.claim_materialOptions, value[i])
            } else {
              material = material + this.selectDictLabel(this.claim_materialOptions, value[i]) + '，'
            }
          }
        }
        return material
      },
      save(row) {
        row.isEdit = true
        editStanding(row).then(res => {
          if (res != null && res.code === 200) {
            this.$message({
              message: '保存成功！',
              type: 'success',
              center: true,
              showClose: true
            })
          }
        }).catch(res => {
          this.$message({
            message: '保存失败!',
            type: 'error',
            center: true,
            showClose: true
          })
        })
        this.search('form')
      },
      getUsers(val) {
        if (val != null && val != '' && val != undefined) {
          let data = {
            organCode: val,
            pageNum: 1,
            pageSize: 200,
          }
          getUsersByOrganCode(data).then(res => {
            if (res != null && res.code === 200) {
              this.userOptions = res.rows
            }
          })
        } else {
          this.sysUserOptions = []
        }
      },
      remoteMethod(query) {
        if (query != null && query != '' && query != undefined) {
          let data = {
            organCode: this.organCode,
            organName: query,
            pageNum: 1,
            pageSize: 200,
          }
          if (query !== '' && query != null) {
            getOrganList(data).then(res => {
              this.deptOptions = res.rows
            }).catch(res => {
            })
          }
        }
      },
      remoteUserMethod(query) {
        if (query != null && query != '' && query != undefined) {

          let data = {
            organCode: this.searchForm.organcode,
            userName: query,
            pageNum: 1,
            pageSize: 200,
          }
          if (data.organCode != null && data.organCode != '' && data.organCode != undefined){
            if (query !== '' && query != null) {
              getUsersByOrganCode(data).then(res => {
                if (res != null && res.code === 200) {
                  this.userOptions = res.rows
                }
              })
            }
          }
        }
      },
      selectDeptName(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
          if (datas[key].organCode == value) {
            actions.push(datas[key].organName);
            return true;
          }
        })
        return actions.join('');
      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /*element原有样式修改*/
  .el-form-item ::v-deep label {
    font-weight: normal;
  }

</style>
