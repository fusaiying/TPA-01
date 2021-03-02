<template>
  <el-dialog :title="title" :visible.sync="dialogVisable" width="600px" append-to-body>
    <el-form  label-width="80px" style="margin-top: 1px;">
      <el-card class="box-card" style="margin-top: 1px;">
        <div slot="header" class="clearfix">
          <!--：data赋值的地方，下面prop对应好就自己遍历赋值了-->
          <el-table
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="workPoolData"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            style=" width: 100%;"
          >
            <el-table-column align="center"  prop="callSerialNum" label="序号" show-overflow-tooltip/>
            <el-table-column align="center" width="140" prop="workOrderNo" label="工单号" show-overflow-tooltip/>
            <el-table-column prop="acceptTime" label="受理时间" align="center" show-overflow-tooltip>
              <template slot-scope="scope">
                <span>{{ scope.row.acceptTime | changeDate}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="insuredName" label="被保人姓名" show-overflow-tooltip/>
            <el-table-column align="center"  prop="remark" label="说明" show-overflow-tooltip/>
            <!--fixed="right"控制固定某一列-->
          </el-table>
<!--          <pagination-->
<!--            v-show="totalCount>0"-->
<!--            :total="totalCount"-->
<!--            :page.sync="queryParams.pageNum"-->
<!--            :limit.sync="queryParams.pageSize"-->
<!--            @pagination="searchHandle"-->
<!--          />-->
        </div>

      </el-card>

    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisable = false" type="primary">关 闭</el-button>
  </span>
  </el-dialog>
</template>
<script>
import {selectCallAgain} from '@/api/customService/demand'
import moment from "moment";

export default {
  filters: {
    changeDate: function (value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },
  name:'secondPhone',
  data() {
    return {
      dialogVisable:false,
      cs_service_item:[],
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      title:"再次来电工单提醒",
      totalCount:0,
      workPoolData:[],
      coOrganizerForm: {
        attachmentType: "",
        attachmentTypes: "",
        attachmentFile: "",
        textarea: ""

      }
    }
  },
  mounted() {
    // this.searchHandle()
  },
  methods: {

    open(){
      this.dialogVisable=true
    },

    // searchHandle() {
    //   selectCallAgain().then(res => {
    //     if (res != null && res.code === 200) {
    //       this.workPoolData = res.rows
    //       this.totalCount = res.total
    //       if (res.rows.length <= 0) {
    //         return this.$message.warning(
    //           "未查询到数据！"
    //         )
    //       }
    //     }
    //   }).catch(res => {
    //
    //   })
    // },
  }
}
</script>
