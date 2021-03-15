<template>
  <el-form ref="serviceProForm" :rules="serviceProFormRules" :model="serviceProForm" size="small"
           :disabled="disabledFlag">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务项目</span>
      </div>
      <el-table ref="contactTable" :data="serviceProForm.form"
                :header-cell-style="{color:'black',background:'#f8f8ff'}" :key="keyValue"
                size="small" highlight-current-row style="width: 100%;">
        <el-table-column align="center" min-width="50" type="selection" width="120px"
                         v-if="disabledFlag && (status=='review'|| status == 'management')"></el-table-column>
        <el-table-column prop="serviceCodeName" align="center" header-align="center" label="服务项目名称"
                         show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id && scope.row.showFlag" :prop="'form.'+scope.$index +'.serviceCodeName'"
                          :rules="serviceProFormRules.serviceCodeName" style="display: inline-flex !important;">
              <el-select v-model="scope.row.serviceCodeName" placeholder="请选择" size="mini" clearable  filterable
                         @change="setOtherValue(scope.$index,scope.row)" style="width: 220px">
                <!--                <el-option v-for="option in contacttype"  :label="option.label"
                                           :value="option.value"/>
                                <el-option/>-->

                <el-option
                  v-for="item in serviceInfo"
                  :key="item.serviceCodeName"
                  :label="item.serviceCodeName"
                  :value="item.serviceCodeName">
                </el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{ scope.row.serviceCodeName }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="categoryCodeName" align="center" header-align="center" label="类别" show-overflow-tooltip>
          <template slot-scope="scope">
            <!--            <el-form-item :prop="'form.'+scope.$index +'.serviceCodeName'"
                                      :rules="serviceProFormRules.categoryCodeName" style="display: inline-flex !important; ">-->
            <span >{{ scope.row.categoryCodeName }}</span>
            <!--            </el-form-item>-->

            <!--            <span v-if="scope.row.serviceCodeName">{{ scope.row.categoryCodeName }}</span>-->
            <!--            <span v-if="!scope.row.serviceCodeName"></span>-->
          </template>
        </el-table-column>
        <el-table-column prop="typeCodeName" align="center" header-align="center" label="种类" show-overflow-tooltip>
          <template slot-scope="scope">
            <!--            <el-form-item :prop="'form.'+scope.$index +'.typeCodeName'"
                                      :rules="serviceProFormRules.typeCodeName" style="display: inline-flex !important;">-->
            <span >{{ scope.row.typeCodeName }}</span>
            <!--            </el-form-item>-->

            <!--            <span v-if="scope.row.serviceCodeName">{{ scope.row.typeCodeName }}</span>-->
            <!--            <span v-else>{{ scope.row.typeCodeName }}</span>-->
          </template>
        </el-table-column>
        <el-table-column prop="deductWay" align="center" header-align="center" label="扣减方式" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.'+scope.$index +'.deductWay'"
                          :rules="serviceProFormRules.deductWay" style="display: inline-flex !important;">
              <el-select v-model="scope.row.deductWay" placeholder="请选择" size="mini" clearable>
                <el-option
                  v-for="item in deductWayOptions"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{ getDeductWay(scope.row) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="costPrice" align="center" header-align="center" label="成本价格" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item v-if="!scope.row.id" :prop="'form.'+scope.$index +'.costPrice'"
                          :rules="serviceProFormRules.costPrice" style="display: inline-flex !important;">
              <el-input v-model="scope.row.costPrice" placeholder="请输入" size="mini"/>
            </el-form-item>
            <span v-else>{{ scope.row.costPrice }}</span>
          </template>
        </el-table-column>

        <el-table-column prop="number" align="center" header-align="center" label="配置供应商" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-form-item :prop="'form.'+scope.$index +'.number'" v-if="!scope.row.id"
                          :rules="serviceProFormRules.number" style="display: inline-flex !important;">
              <a style="color: #3CB4E5;text-decoration: underline"
                 @click="opendialogVisible(scope.$index,scope.row)">{{ scope.row.number | getNumber }}</a>
            </el-form-item>
            <span v-else>{{ scope.row.number | getNumber }}</span>


          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" min-width="94" fixed="right">
          <template slot-scope="scope">

              <span style="cursor: pointer;">
                  <el-button type="text" size="mini" v-if="!scope.row.isSet"
                             @click="editHandle(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="text" size="mini" @click="delHandle(scope.$index,scope.row)">删除</el-button>

                </span>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="mini"
                 style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;"
                 @click="addContactHandle"> + 添加
      </el-button>
    </el-card>
    <!--    <el-dialog
          :visible.sync="dialogVisible"
          :modal="modalValue"
          :close-on-click-modal="false"
          title="提示"
          width="30%">
          <span>{{ '请问是否删除该条目？' }}</span>
          <span slot="footer" class="dialog-footer">
             <el-button type="primary" @click="delConfirm">确 定</el-button>
              <el-button @click="dialogVisible = false">取 消</el-button>

            </span>
        </el-dialog>-->


    <!--供应商弹出框-->
    <el-dialog
      :visible.sync="supplierDialogVisible"
      :modal="modalValue"
      :close-on-click-modal="false"
      title=""
      width="70%">
      <el-form ref="supplierInfo" :rules="supplierInfoRules" :model="supplierInfo" size="small" :disabled="false">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span style="font-size: 20px">供应商信息</span>
        </div>
        <el-table ref="medicalRecordTable"
                  :data="supplierInfo.supplierData"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="small"
                  v-loading="loading"
                  highlight-current-row
                  tooltip-effect="dark"
                  @select="handleSelectionChange"
                  @select-all="checkAll"
                  style="width: 100%;">

          <el-table-column align="center" min-width="50" type="selection" width="120px"></el-table-column>
          <el-table-column key="1" align="center" prop="supplierCode" min-width="150" label="供应商编码"
                           show-overflow-tooltip/>
          <el-table-column key="2" align="center" min-width="100" prop="chname" label="供应商名称" show-overflow-tooltip/>
          <el-table-column key="3" align="center" prop="addressdetail" min-width="150" label="适用区域"
                           show-overflow-tooltip/>
          <el-table-column key="4" align="center" min-width="100" prop="endDate" label="合约止期" show-overflow-tooltip/>
          <el-table-column key="5" align="center" min-width="150" label="服务价格" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{ scope.row.minPrice }}-{{ scope.row.maxPrice }}</span>
            </template>
          </el-table-column>
          <el-table-column key="6" align="center" prop="priority" label="优先级次序" min-width="120" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item v-if="!scope.row.id" :prop="'supplierData.'+scope.$index +'.priority'"
                            :rules="supplierInfoRules.priority" style="display: inline-flex !important;">
                <el-input v-model="scope.row.priority" placeholder="请输入" size="mini"/>
              </el-form-item>
              <span v-else>{{ scope.row.priority }}</span>
            </template>
          </el-table-column>
        </el-table>
        <!--        &lt;!&ndash;分页组件&ndash;&gt;
                <pagination
                  v-show="totalCount>0"
                  :total="totalCount"
                  :page.sync="formSearch.pageNum"
                  :limit.sync="formSearch.pageSize"
                  @pagination="getData"
                />-->
      </el-form>

      <span slot="footer" class="dialog-footer">
     <el-button size="mini" type="primary" @click="saveHandle">保存</el-button>
        <el-button size="mini" @click="supplierDialogVisible=false">关闭</el-button>

        </span>

    </el-dialog>
  </el-form>


</template>
<script>
import {
  getServiceInfo,
  getProductServiceList,
  selectSupplier,
  insertSupplier,
  updateSupplierStatus, deleteProductInfo
} from '@/api/productManage/serviceProductManagement'


export default {
  props: {
    productCode: {
      type: String,
      default: ''
    },
    status: {
      type: String,
      default: ''
    },
    disabledFlag: Boolean,


  },


  data() {
    const checkPriority = (rules, value, callback) => {

      const index = rules.field.replace('supplierData.', '').replace('.priority', '')

      let flag = this.indexList.find(item => {
        return item == index
      })

      if (flag != null && flag >= 0) {

        let reg = /^(\d+|\d+\.)$/

        if (value != null && value != '') {
          //
          //判断优先次序是否重复
          let flag=true
            let list=new Set(this.multipleSelection.map(item=>{
              return item.priority}//过滤掉尼不想重复的字段
            ))
            if(list.size!=this.multipleSelection.length){//去除重复字段后的数据条数，相同数据只计算一次
               flag=false
            }
        if(flag) {
          if (value <= 0) {
            callback(new Error("只能输入正整数"));
          } else if (!reg.test(value)) {
            callback(new Error("只能输入正整数"));
          } else {
            callback();
          }
        }
        else {
          callback(new Error("优先级次序不能重复"))
        }



        } else {
          callback(new Error("已选中供应商,此项不能为空"))
        }
      } else {
        callback()
      }
    }

    const checkCostPrice = (rules, value, callback) => {

      const regx = /^(\d+|\d+\.\d{1,2})$/
      const regx1 = /[^\d+\d-\.]/g
      if (value < 0) {
        callback(new Error("只能输入数字且保留两位小数"));
      } else if (!regx.test(value)) {
        callback(new Error("只能输入数字且保留两位小数"));
      } else {
        callback();
      }


    }

    return {
      keyValue:1,
      loading: false,
      serviceProForm: {
        form: []
      },
      formSearch: {
        pageNum: 1,
        pageSize: 10,
        productCode: '',
        serviceCode: ''

      },
      supplierInfo: {
        supplierData: []
      },
      totalCount: 0,

      supplierDialogVisible: false,
      dialogVisible: false,
      modalValue: false,
      index: '',
      serviceTableIndex: '',

      multipleSelection: [],

      place_typeOptions: [],


      serviceProFormRules: {
        serviceCodeName: [{required: true, message: '服务项目名称不能为空', trigger: 'change'}],

        /*        categoryCodeName: [{required: true, message: '类别不能为空', trigger: 'blur'}],
                typeCodeName: [{required: true, message: '种类不能为空', trigger: 'blur'}],*/
        deductWay: [{required: true, message: '扣减方式不能为空', trigger: 'change'}],
        costPrice: [{required: true, validator: checkCostPrice, trigger: 'blur'}],
        /*   number: [{required: true, message: '配置供应商不能为空', trigger: 'blur'}],*/
      },
      supplierInfoRules: {
        priority: [{required: true, validator: checkPriority, trigger: ['blur','change']}]
      },


      deductWayOptions: [],
      serviceInfo: [],
      indexList: []
    }
  },
  filters: {
    getNumber(val) {
      if (val === undefined || val === null || val === '') {
        return '0'
      } else {
        return val
      }
    },

  },

  watch: {
    /*  productCode: function (newValue){
        this.init()
      }*/

  },
  mounted() {
    this.getDicts("deductWay").then(response => {
      this.deductWayOptions = response.data;
    });
    this.init()
    this.getServiceInfoList()
  },


  methods: {
    getDeductWay(row) {
      return this.selectDictLabel(this.deductWayOptions, row.deductWay)
    },
    checkAll(val) {

      this.multipleSelection = []
      this.multipleSelection = val;
      console.log(this.multipleSelection)
      this.indexList = []
      this.multipleSelection.forEach(item => {
        this.indexList.push(item.index)
      })
    },
    //选中供应商的个数
    handleSelectionChange(val, row) {

      console.log(row.index)
      this.multipleSelection = []
      this.multipleSelection = val;
      console.log(this.multipleSelection)
      this.indexList = []
      this.multipleSelection.forEach(item => {
        this.indexList.push(item.index)
      })
      //清空未选中的优先级次序
      this.supplierInfo.supplierData.forEach(item => {
        let num = 0
        this.indexList.forEach(option => {
          if (item.index === option) {
            num++
          }
        })
        if (num === 0) {
          item.priority = ''
        }
      })


    },

    //获取服务项目名称下拉框
    getServiceInfoList() {
      getServiceInfo().then(res => {
        this.serviceInfo = res
      })
    },

    //改变服务项目名称 给类别等字段赋值
    setOtherValue(index, data) {
      if (data.serviceCodeName != null && data.serviceCodeName != '') {
        //判断表格中是否存在次服务
        let objService = this.serviceProForm.form.filter(item => {
          return item.serviceCodeName == data.serviceCodeName
        })
        //不存在
        if (objService.length == 1) {
          //根据选中的找到对应的数据
          let obj = this.serviceInfo.find(item => {
            return item.serviceCodeName == data.serviceCodeName
          })
          let query = {
            productCode: this.productCode,
            serviceCode: obj.serviceCode
          }



          updateSupplierStatus(query).then(res => {
          })
          if (obj != undefined && obj != null) {
            data.categoryCodeName = obj.categoryCodeName
            data.typeCodeName = obj.typeCodeName
            data.categoryCode = obj.categoryCode
            data.categoryName = obj.categoryName
            data.serviceCode = obj.serviceCode
            data.serviceName = obj.serviceName
            data.typeCode = obj.servertypeCode
            data.typeName = obj.servertypeName
            data.deductWay = ''
            data.costPrice = ''
            data.number = ''


          } else {
            data.categoryCodeName = ''
            data.typeCodeName = ''
            data.categoryCode = ''
            data.categoryName = ''
            data.serviceCode = ''
            data.serviceName = ''
            data.typeCode = ''
            data.typeName = ''
            data.deductWay = ''
            data.costPrice = ''
            data.number = ''
          }
        } else {
          data.serviceCodeName = ''
          data.categoryCodeName = ''
          data.typeCodeName = ''
          data.categoryCode = ''
          data.categoryName = ''
          data.serviceCode = ''
          data.serviceName = ''
          data.typeCode = ''
          data.typeName = ''
          data.deductWay = ''
          data.costPrice = ''
          data.number = ''
          this.$message.warning('此服务项目名称已存在')

        }
      } else {
        data.categoryCodeName = ''
        data.typeCodeName = ''
        data.categoryCode = ''
        data.categoryName = ''
        data.serviceCode = ''
        data.serviceName = ''
        data.typeCode = ''
        data.typeName = ''
        data.deductWay = ''
        data.costPrice = ''
        data.number = ''
      }

    },


    //供应商弹出框
    async opendialogVisible(index, row) {
      this.serviceTableIndex = index

      //调用查询供应商接口
      /*      let query={
              productCode: this.productCode,
              serviceCode: row.serviceCode
            }*/
      this.formSearch.productCode = this.productCode
      this.formSearch.serviceCode = row.serviceCode
      if (this.formSearch.serviceCode != null && this.formSearch.serviceCode != '') {
        this.supplierDialogVisible = true
        //供应商查询页面
        await new Promise((resolve, reject) => {
          this.loading = true
          selectSupplier(this.formSearch).then(res => {
            this.supplierInfo.supplierData = res.data.data;
            this.supplierInfo.supplierData.map((item, index) => {
              item.index = index
            })
            this.loading = false
            this.totalCount = res.total;
            resolve(this.supplierInfo.supplierData)
          })
        }).then(res => {
          //找到有数据的优先次序
          let rows = this.supplierInfo.supplierData.filter(item => {
            return item.priority != null && item.priority != ''
          })

          this.multipleSelection = rows
          this.indexList = []
          this.multipleSelection.forEach(item => {
            this.indexList.push(item.index)
          })
          this.checkSelection()
        })


      } else {
        this.$message.warning('请先选择服务项目名称')
      }




    },
//默认选中
    checkSelection() {
      this.$nextTick(() => {

        if (this.multipleSelection.length > 0) {
          this.multipleSelection.forEach(row => {
            this.$refs.medicalRecordTable.toggleRowSelection(row, true);
          });

        } else {
          this.$refs.medicalRecordTable.clearSelection();
        }
      })
    },

    //供应商查询页面
    getData(query) {
      this.loading = true
      selectSupplier(query).then(res => {
        this.supplierInfo.supplierData = res.data;
        this.supplierInfo.supplierData.map((item, index) => {
          item.index = index
        })
        this.loading = false
        this.totalCount = res.total;
      })

    },


    init() {
      if (this.productCode != null && this.productCode != '') {
        let queryData = {
          productCode: this.productCode,
        }
        getProductServiceList(queryData).then(res => {
          if (res.code == '200' && res.rows.length > 0) {
            this.serviceProForm.form = res.rows
            this.serviceProForm.form.map((data, index) => {
              data.id = index + 1
              data.isSet = false
              data.showFlag = true
            })
          }
        })
      }
    },
//供应商信息保存
    saveHandle() {

      if (this.multipleSelection.length > 0) {

        //调用供应商保存的接口
        //
        this.$refs.supplierInfo.validate(valid => {
          if (valid) {
            let supplierData = {
              productCode: this.productCode,
              productSupplierInfos: this.multipleSelection,
              serviceCode: this.serviceProForm.form[this.serviceTableIndex].serviceCode
            }
            insertSupplier(supplierData).then(res => {
              if (res != null && res.code == '200') {
                this.$message({
                  message: '保存成功！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.serviceProForm.form[this.serviceTableIndex].number = this.multipleSelection.length

              } else {
                this.$message({
                  message: '保存失败!',
                  type: 'error',
                  center: true,
                  showClose: true
                })
              }
            })
          } else {
            this.$message.warning('请填写完必要信息！')
          }

        })


      } else {
        this.$message.warning('请选择供应商')
      }

    },

    delHandle(index, row) {
      //this.dialogVisible = true
      this.$confirm('是否删除该条目?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.serviceProForm.form.splice(index, 1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    /*    delConfirm() {
          this.dialogVisible = false
          this.serviceProForm.form.splice(this.index, 1)
        },*/
    //服务项目添加一行
    addContactHandle() {
      const field = {
        serviceCodeName: '',
        categoryCodeName: '',
        typeCodeName: '',
        deductWay: '',
        costPrice: '',
        number: '',
        isSet: true,
        showFlag: true
      }

      this.serviceProForm.form.push(field)
    },
    editHandle(index, row) {

      this.serviceProForm.form[index].id = ''
      this.serviceProForm.form[index].showFlag = false
      this.serviceProForm.form[index].isSet = true
      this.keyValue++
    },

    // 校验数据
    validateForm() {
      let flag = null
      if (this.serviceProForm.form != null && this.serviceProForm.form.length > 0) {
        this.$refs['serviceProForm'].validate(valid => {
          if(valid){
            if (this.serviceProForm.form.filter(item => {
              return item.number == '' || item.number==0
            }).length > 0) {
              flag = '03'
            }
            else {
              flag='01'
            }
          }
          else {
            flag = '04'
          }

          /*
                    if (this.serviceProForm.form.filter(item => {
                      return item.number == ''
                    }).length > 0) {
                      flag = '03'
                    }
                    else {
                      if (valid) {
                        flag = '01'
                      } else {
                        flag = '04'
                      }
                    }*/
        })
      } else {
        flag = '02'
      }
      return flag
    }

  }
}
</script>
<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item ::v-deep label {
  font-weight: normal;
}

::v-deep .el-table__header-wrapper .el-checkbox__input::after {
  content: '全选';
  position: absolute;
  font-weight: bolder;
  margin-left: 5px;
}


/*!*修改标签页的字体*!
/deep/ .el-tabs__item{
  font-size: 20px ;
  font-weight: 400;
  color: #000000;
}*/
.baseInfo_class .el-tag--small {
  margin-right: 10px !important;
}

.redItem .el-form-item__label {
  color: red !important;
}

.el-radio {
  padding: 3px;
}


.department-style .el-form-item {
  display: inline-flex !important;
}
</style>
