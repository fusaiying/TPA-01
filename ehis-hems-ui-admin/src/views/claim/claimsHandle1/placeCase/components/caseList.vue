<template>
  <el-dialog
    :visible.sync="dialogListVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" >
    <el-card class="box-card" style="margin-top: 10px;">
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>归档案件列表（{{totalNum}}）</span>
          <el-button style="float: right; margin-top: 10px;" size="mini" @click="changeDialogVisable">返回
          </el-button>
          <el-button v-if="edit" style="float: right; margin-top: 10px;margin-right: 10px" type="primary" size="mini" @click="saveData">保存
          </el-button>
        </div>

      <el-form  ref="dataForm" :model="dataForm" :rules="dataRules"  size="small" >

      <el-table
          :data="dataForm.tableData"
          v-loading="loading"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          @selection-change="handleSelectionChange"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column prop="rptNo" label="报案号"  align="center" show-overflow-tooltip >
            <template slot-scope="scope" >
              <template v-if="1==2" >
                <el-input  v-model="scope.row.rptNo"  disabled style="width: 10px" size="mini"></el-input>
              </template>
              <span  v-else>{{ scope.row.rptNo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="caseBoxNo" label="盒号"  align="center" show-overflow-tooltip >
            <template slot-scope="scope" >
              <template v-if="1==2" >
                <el-input  v-model="scope.row.caseBoxNo"  disabled style="width: 10px" size="mini"></el-input>
              </template>
              <span  v-else>{{ scope.row.caseBoxNo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="deptCode" label="机构" align="center" width="150%" show-overflow-tooltip >
            <template slot-scope="scope" >
              <template v-if="1==2" >
                <el-input  v-model="scope.row.deptCode"  disabled style="width: 10px" size="mini"></el-input>
              </template>
              <span  v-else>{{ scope.row.deptCode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="claimType" label="理赔类型"  align="center" width="150%"show-overflow-tooltip >
            <template slot-scope="scope" >
              <template v-if="1==2" >
                <el-input  v-model="scope.row.claimType"  disabled style="width: 10px" size="mini"></el-input>
              </template>
              <span  v-else>{{ scope.row.claimType }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" min-width="120" prop="isFiling" label="发票是否归档" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;"   v-if="scope.row.status == 'Y'" :rules="dataRules.isFiling"  :prop="'tableData.' + scope.$index + '.isFiling'">
                <el-select  style="width: 100px" v-model="scope.row.isFiling"  size="mini"  class= "el-select item-width el-select--mini" placeholder="请选择">
                  <el-option
                    v-for="dict in yesOrNo"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>

              <template  v-else slot-scope="scope">
                <span>{{getYesOrNoName(scope.row.isFiling)}}</span>
              </template>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="isInvoiceBack" label="发票是否退回" min-width="120" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;"   v-if="scope.row.status == 'Y'" :rules="dataRules.isInvoiceBack"  :prop="'tableData.' + scope.$index + '.isInvoiceBack'">
                <el-select  style="width: 100px" v-model="scope.row.isInvoiceBack"  size="mini"  class= "el-select item-width el-select--mini" placeholder="请选择">
                  <el-option
                    v-for="dict in yesOrNo"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>

              <template  v-else slot-scope="scope">
                <span>{{getYesOrNoName(scope.row.isInvoiceBack)}}</span>
              </template>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="isSingle" label="批次是否单张发票" min-width="120" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-form-item style="display: inline-flex !important;"   v-if="scope.row.status == 'Y'" :rules="dataRules.isSingle"  :prop="'tableData.' + scope.$index + '.isSingle'">
                <el-select  style="width: 100px" v-model="scope.row.isSingle"  size="mini"  class= "el-select item-width el-select--mini" placeholder="请选择">
                  <el-option
                    v-for="dict in yesOrNo"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>

              <template  v-else slot-scope="scope">
                <span>{{getYesOrNoName(scope.row.isSingle)}}</span>
              </template>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="remark" min-width="110" label="备注" show-overflow-tooltip>
            <template slot-scope="scope" >
              <template  v-if="scope.row.status == 'Y'" >
                <el-input v-model="scope.row.remark"  size="mini"></el-input>
              </template>
              <span  v-else>{{ scope.row.remark }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
        <!--分页组件-->
 <!--       <pagination
          v-show="totalNum>0"
          :total="totalNum"
          :page.sync="pageInfo.currentPage"
          :limit.sync="pageInfo.pageSize"
          @pagination="initData"
        />-->
    </el-card>
  </el-dialog>
</template>

<script>

  import { getInfoCaseFilingList,updateCaseFilingInfo } from '@/api/placeCase/api'

  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      rptNo: String,
      batchNo: String,
      editPower: {
        type: Boolean,
        default: false
      },
    },
    watch: {
      value: function (newValue) {
        this.dialogListVisable = newValue;
        if(this.dialogListVisable) {
          this.initData();
        }
      },
      rptNo: function (newValue) {
        this.detailRptNo = newValue;
      },
      batchNo: function (newValue) {
        this.detailBatchNo = newValue;
      },
      editPower: function (newValue) {
        console.log(newValue);
        this.edit = newValue;
      },

    },
    data() {
      return {
        dataForm:{
          tableData:[]
        },
        dataRules:{
          isFiling: [{required: true, message: '不可为空', trigger: ['change','blur']}],
          isInvoiceBack: [{required: true, message: '不可为空', trigger: ['change','blur']}],
          isSingle: [{required: true, message: '不可为空', trigger: ['change','blur']}],
        },
        dialogListVisable:false,
        yesOrNo:[],
        totalNum: 0,
        pageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },
        loading: false,
        detailRptNo :'',
        detailBatchNo:'',
        selectData:'',
        edit:true,
      }
    },
    mounted(){
      // sys_yes_no
      this.getDicts("sys_yes_no").then(response => {
        this.yesOrNo = response.data;
      });
    },
    computed: {

    },

    // 获取跳转地址的数据
    created: function() {
      this.initData();
    },
    methods: {
      saveData() {
        let listDta = this.selectData;
        if(listDta.length == 0) {
          this.$message({
            type: 'info',
            message: '请选择要保存的数据!'
          });
          return false;
        }
        updateCaseFilingInfo(listDta).then(response => {
          // console.log(response)
          if(response.code == '200') {
            this.$message({
              type: 'success',
              message: '保存成功!'
            });
            this.baseFomrmSub = true;
            this.getData();
          } else {
            this.$message({
              type: 'info',
              message: '保存失败'
            });
          }
        }).catch(error => {
          console.log(error);
        })
        // this.$refs.dataForm.validate((valid) => {
        //   if (valid) {
        //     let listDta = this.dataForm.tableData;
        //     console.log(listDta)
        //     console.log(listDta)
        //     console.log(listDta)
        //     console.log(listDta)
        //   } else {
        //     return false
        //   }
        // })

      },
      initData(){
        const params = {
          pageNum:this.pageInfo.currentPage,
          pageSize:this.pageInfo.pageSize,
          batchNo:this.batchNo,
          orderByColumn:'ccf.create_time',
          isAsc:'desc'
        };

        this.loading = true;
        getInfoCaseFilingList(params).then(response => {
          this.totalNum = response.total;
          this.dataForm.tableData = response.rows;
          this.loading = false
        }).catch(error => {
          console.log(error);
        });
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.selectData = selection;
        console.log(selection);
        console.log(selection);
        console.log(selection);

        // this.rptNos = (selection.map(item => item.rptNo)); //rptNo
        // // this.ids = selection.map(item => item.rptNo);
        // this.single = selection.length != 1;
        // this.multiple = !selection.length;
      },
      //关闭对话框
      changeDialogVisable() {
        this.$emit('closeListDialog')
      },
      getYesOrNoName(value){
        return this.selectDictLabel(this.yesOrNo, value)
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

</style>

