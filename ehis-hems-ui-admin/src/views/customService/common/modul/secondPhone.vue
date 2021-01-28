<template>
  <div>
<el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
  <el-form  label-width="80px">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
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
          <el-table-column align="center" width="140" prop="state" label="序号" show-overflow-tooltip/>
          <el-table-column align="center" prop="channel" label="工单号" show-overflow-tooltip/>
          <el-table-column align="center" prop="Service" label="受理时间" show-overflow-tooltip/>
          <el-table-column align="center" prop="policyNumber" label="被保人姓名" show-overflow-tooltip/>
          <el-table-column align="center"  prop="secondNumber" label="说明" show-overflow-tooltip/>
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
    </el-card>

  </el-form>
</el-dialog>
  </div>
</template>
<script>
  import {demandListAndPublicPool,demandListAndPersonalPool} from '@/api/customService/demand'

  export default {
    name:'secondPhone',
    //接受父传值
    props: {
      workPoolData: [],
      value: {
        type: Boolean,
        default: false
      },
    },


    data() {
      return {
        open:"true",
        queryParams: {
          pageNum: 1,
          pageSize: 10


        },
        workPoolData:[],
        dialogVisable: false,
        coOrganizerForm: {
          attachmentType: "",
          attachmentTypes: "",
          attachmentFile: "",
          textarea: ""

        }
      }
    },
    mounted() {
      this.searchHandle()
    },
    methods: {
      searchHandle() {
        debugger;
        let query = {}
        console.log('query: ',query)
        demandListAndPublicPool(query).then(res => {
          console.log('------------: ',res)
          if (res != null && res.code === 200) {
            this.workPoolData = res.rows
            this.totalCount = res.total
            console.log('response',res.total)
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
        this.$emit('closeHistoricalProblem')
      }
    }
  }
</script>
