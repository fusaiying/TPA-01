<template>
  <div>
    <el-dialog title="修改说明" :visible.sync="dialogVisable" width="600px" append-to-body>
      <el-form  label-width="100px" >
<!--        label-width设置了lanbl的宽度-->
       <el-row justify="center" align="center" >
         <el-form-item label="修改原因：" prop="insuredName">
           <el-input v-model="ss.editReason" class="item-width"  size="mini" readonly/>
         </el-form-item>
       </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="insuredName"  >
            <el-input v-model="ss.editRemark" class="item-width"  size="mini" readonly/>
          </el-form-item>
        </el-row>
        <el-card class="box-card" style="margin-top: 10px;">
          <div slot="header" class="clearfix">
            <span style="color: blue">修改细节</span>
            <el-divider/>
            <!--：data赋值的地方，下面prop对应好就自己遍历赋值了-->
            <el-table
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="workPoolData"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              style=" width: 100%;"
            >
              <el-table-column align="center" width="140" prop="itemKey" label="修改项" show-overflow-tooltip/>
              <el-table-column align="center" prop="oldValue" label="新值" show-overflow-tooltip/>
              <el-table-column align="center" prop="nowValue" label="旧值" show-overflow-tooltip/>

              <!--fixed="right"控制固定某一列-->
            </el-table>
            <pagination
              v-show="totalCount>0"
              :total="totalCount"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="searchHandle"
            />

          </div>
          <span style="float: right;">
          <el-button size="mini" @click="changeDialogVisable" type="primary">关 闭</el-button>
        </span>
        </el-card>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
   import {modifyDetailsSearch} from '@/api/customService/demand'

  export default {
    name:'modify',
    data() {
      return {
        totalCount:0,
        workPoolData:[],
        ss:{
          editReason:"",
          editRemark:""

        },
        workOrderNo:"",
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          workOrderNo:"",
          flowId:""
        },
        dialogVisable:false,
      }
    },
    created() {

    },


    methods: {
      //查询
      searchHandle(){
        let query = this.queryParams
        modifyDetailsSearch(query).then(res => {
          if (res != null && res.code === 200) {
            if (res.rows[0]!=undefined){
              this.ss=res.rows[0]
            }
            this.workPoolData = res.rows
            this.totalCount = res.total
            console.log('response',res.rows)
            if (res.rows.length <= 0) {
              return this.$message.warning(
                "未查询到数据！"
              )
            }
          }
        }).catch(res => {

        })

      },
      //关闭对话框
      changeDialogVisable() {
        //清空对话框中的数据
        this.dialogVisable=false

      },
      //打开窗口
      open(){
        console.log("调用到了子组件")
        this.dialogVisable=true;
        this.searchHandle();
      },
    }
  }
</script>
