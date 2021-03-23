<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :close-on-click-modal="true"
    :before-close="changeDialogVisable"
    title=""
    width="80%" height="100%">
    <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
      <span style="font-size: 20px">医院信息</span>
    </div>
    <el-card style="border: 0; margin-top: -20px; margin-bottom: 30px">
      <div style="float: right;height: 50px">
        <el-button size="mini" type="primary" @click="confirmHandle">确认</el-button>
        <el-button size="mini" type="primary" @click="changeDialogVisable">返回</el-button>
      </div>
      <el-table
        :data="tableData"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column width="100" align="center"  label="选择">
          <template slot-scope="scope">
            <el-radio class="radio" :label="scope.$index" v-model="radio"
                      @click.native="getCurrentRow(scope.$index,scope.row)"> {{ }}
            </el-radio>
            　　　　　
          </template>
        </el-table-column>
        <el-table-column key="1" align="center" prop="chname1" min-width="150" label="医疗机构ID"
                         show-overflow-tooltip/>
        <el-table-column key="2" align="center" min-width="100" prop="chaddreess" label="机构名称" show-overflow-tooltip/>
        <el-table-column key="3" align="center" prop="bankName" min-width="160" label="机构地址" show-overflow-tooltip/>
        <el-table-column key="5" align="center" prop="accountNo" label="机构电话" min-width="120" show-overflow-tooltip/>
        <el-table-column label="医院介绍" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" style="color: #1890ff;" @click="openHospitalShow(scope.row)">医院介绍
            </el-button>
          </template>
        </el-table-column>
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
      claimtype: String
    },
    watch: {
      value: function (newValue) {
        this.dialogVisable = newValue
      },
      claimtype: function (newValue) {
        this.searchForm.isNetworkHospital = newValue
        getHospitalInfo(this.searchForm).then(res => {
          if (res != null && res !== '') {
            this.tableData = res.rows
            this.totalCount = res.total
          }
        })
      },
    },
    data() {
      return {
        rowData: {},
        totalCount: 0,
        radio: undefined,
        dialogVisable: false,
        tableData: [],
        searchForm: {
          chname1: '',
          isNetworkHospital: '',
          pageNum: 1,
          pageSize: 10
        },
      }
    },
    mounted() {
      if (this.tableData.length <= 0) {
        getHospitalInfo(this.searchForm).then(res => {
          if (res != null && res !== '') {
            this.tableData = res.rows
            this.totalCount = res.total
          }
        })
      }

    },
    computed: {},
    methods: {
      //自定义展开
      getCurrentRow(index, row) {
        this.rowData = row
      },

      // 查询
      searchHandle() {
        this.searchForm.pageNum = 1
        this.searchForm.pageSize = 10
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

      getData() {
        getHospitalInfo(this.searchForm).then(res => {
          if (res != null && res !== '') {
            this.tableData = res.rows
            this.totalCount = res.total
            if (res.rows.length < 1) {
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
      openHospitalShow(row) {
        const newpage = this.$router.resolve({
          name: 'hospitalDetail',
          query: {
            providerCode: row.providerCode
          }
        })
        window.open(newpage.href, '_blank');
      }
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
