<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" height="100%">
    <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
      <span style="font-size: 20px">查询条件</span>
      <span style="float: right;">
          <el-button size="mini" @click="changeDialogVisable">返回</el-button>
        </span>
    </div>
    <el-card style="border: 0; margin-top: -20px; margin-bottom: 30px">

      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="医院名称：" prop="chname1">
              <el-input v-model="searchForm.chname1" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
          <el-button
            size="mini"
            type="primary"
            @click="
            searchHandle()"
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="confirmHandle">确认</el-button>
        </div>
      </el-form>
      <el-divider/>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column width="150">
          <template slot-scope="scope">
            <el-radio class="radio" :label="scope.$index" v-model="radio"
                      @click.native="getCurrentRow(scope.$index,scope.row)"> {{ }}
            </el-radio>
            　　　　　
          </template>
        </el-table-column>
        <el-table-column sortable key="1" align="center" prop="chname1" min-width="150" label="医院名称"
                         show-overflow-tooltip/>
        <el-table-column key="2" align="center" min-width="100" prop="chaddreess" label="医院地址" show-overflow-tooltip/>
        <el-table-column key="3" align="center" prop="bankName" min-width="160" label="结算银行" show-overflow-tooltip/>
        <el-table-column key="5" align="center" prop="accountNo" label="银行账号" min-width="120" show-overflow-tooltip/>
        <el-table-column key="6" align="center" prop="accountName" label="账户名" min-width="120" show-overflow-tooltip/>
        <el-table-column key="7" align="center" prop="bankDetail" label="银行信息描述" min-width="120" show-overflow-tooltip/>
      </el-table>        <!--分页组件-->
      <pagination
        v-show="totalCount>0"
        :total="totalCount"
        :page.sync="searchForm.pageNum"
        :limit.sync="searchForm.pageSize"
        @pagination="getData"
      />

    </el-card>
  </el-dialog>
</template>

<script>
  import {getHospitalInfo} from '@/api/claim/handleCom'
  export default {
    props: {
      value: {
        type: Boolean,
        default: false
      },
      claimtype:String
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
      claimtype: function (newValue) {
        this.searchForm.isNetworkHospital = newValue
        getHospitalInfo(this.searchForm).then(res=>{
          if (res!=null && res!=='' ){
            this.tableData=res.rows
            this.totalCount=res.total
          }
        })
      },
    },
    data() {
      return {
        rowData: {},
        totalCount: 0,
        radio: undefined,
        dialogVisable:false,
        tableData: [],
        searchForm: {
          chname1: '',
          isNetworkHospital: '',
          pageNum:1,
          pageSize:10
        },
      }
    },
    mounted() {
      if (this.tableData.length<=0){
        getHospitalInfo(this.searchForm).then(res=>{
          if (res!=null && res!=='' ){
            this.tableData=res.rows
            this.totalCount=res.total
          }
        })
      }

    },
    computed: {},
    methods: {
      //自定义展开
      getCurrentRow(index, row) {
        this.rowData=row
      },

      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      // 查询
      searchHandle() {
        this.searchForm.pageNum=1
        this.searchForm.pageSize=10
        this.getData()
        /*        getHospitalInfo(this.searchForm).then(res=>{
                  if (res!=null && res!=='' ){
                    this.tableData=res.rows
                    this.totalCount=res.total
                    if (res.rows.length<1) {
                      return this.$message.warning(
                        "没有查询到数据！"
                      )
                    }
                  }
                })*/
      },

      getData(){
        getHospitalInfo(this.searchForm).then(res=>{
          if (res!=null && res!=='' ){
            this.tableData=res.rows
            this.totalCount=res.total
            if (res.rows.length<1) {
              return this.$message.warning(
                "没有查询到数据！"
              )
            }
          }
        })
      },
      changeDialogVisable() {
        this.$emit('closeHospital')
      },
      //确人按钮
      confirmHandle() {
        //关闭清空
        this.changeDialogVisable()
        this.$emit('getPropData', this.rowData)
      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  #print-iframe {
    position: absolute;
    width: 0px;
    height: 0px;
    left: -500px;
    top: -500px;
  }
</style>
