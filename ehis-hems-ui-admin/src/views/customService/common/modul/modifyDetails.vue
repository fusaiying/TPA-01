<template>
  <div>
    <el-dialog title="修改说明" :visible.sync="dialogVisable" width="600px" append-to-body>
      <el-form label-width="100px">
        <!--        label-width设置了lanbl的宽度-->
        <el-row justify="center" align="center">
          <el-form-item label="修改原因：" prop="insuredName">
            <el-select v-model="ss.editReason" class="item-width" disabled size="mini">
              <el-option v-for="item in cs_eidt_reason" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="修改说明：" prop="insuredName">
            <el-input v-model="ss.editRemark" class="item-width" size="mini" readonly/>
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
              <el-table-column align="center" width="140" prop="itemKey" label="修改项" show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.itemKey">
                  <span>{{ selectDictLabel(cs_demandAcceptVo, scope.row.itemKey) }}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="nowValue" label="新值" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span>{{ showNowValue(scope.row) }}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="oldValue" label="旧值" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span>{{ showOldValue(scope.row) }}</span>
                </template>
              </el-table-column>

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
import moment from 'moment'

let dictss = [
  {dictType: 'cs_sex'},
  {dictType: 'cs_communication_language'},
  {dictType: 'cs_channel'},
  {dictType: 'cs_priority'},
  {dictType: 'cs_organization'},
  {dictType: 'cs_relation'},
  {dictType: 'cs_consultation_type'},
  {dictType: 'cs_whether_flag'},
  {dictType: 'cs_whether_flag'},
  {dictType: 'cs_whether_flag'},
  {dictType: 'cs_identity'},
  {dictType: 'cs_time_unit'},
]

export default {

  filters: {
    changeDate: function(value) {
      if (value !== null) {
        return moment(value).format('YYYY-MM-DD')
      }
    }
  },

  name: 'modify',
  data() {
    return {
      dictList: [],
      cs_eidt_reason: [],//修改原因
      totalCount: 0,
      workPoolData: [],
      cs_demandAcceptVo: [],
      ss: {
        editReason: "",
        editRemark: ""

      },
      workOrderNo: "",
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workOrderNo: "",
        subId: ""
      },
      dialogVisable: false,
    }
  },
  created() {
    this.getDicts("cs_eidt_reason").then(response => {
      this.cs_eidt_reason = response.data;
    });
    this.getDicts("cs_demandAcceptVo").then(response => {
      this.cs_demandAcceptVo = response.data;
    });
  },

  async mounted() {
    // 字典数据统一获取
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
  },

  methods: {
    //查询
    searchHandle() {
      let query = this.queryParams
      modifyDetailsSearch(query).then(res => {
        if (res != null && res.code === 200) {
          if (res.rows[0] != undefined) {
            this.ss = res.rows[0]
          }
          this.workPoolData = res.rows
          this.totalCount = res.total
          console.log('response', res.rows)
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
      this.dialogVisable = false

    },
    //打开窗口
    open() {
      console.log("调用到了子组件")
      this.dialogVisable = true;
      this.searchHandle();
    },
    showNowValue(row) {
      if (row.valueDictType != null && row.valueDictType != '' && row.valueDictType != undefined) {
        //本次疾病/症状起病时 需要特殊处理
        if(row.valueDictType == "cs_time_unit"){
          let tNowValueList = row.nowValue.splice("-");
          return tNowValueList[0] + this.selectDictLabel((this.dictList.find(item => {return item.dictType === row.valueDictType}).dictDate), tNowValueList[1]);
        }else{
          return this.selectDictLabel((this.dictList.find(item => {return item.dictType === row.valueDictType}).dictDate), row.nowValue);
        }
      }else{
        if(row.itemKey == "complaintTime"){
          return moment(row.nowValue).format('YYYY-MM-DD HH:mm:ss');
        }else{
          return row.nowValue;
        }
      }
    },

    showOldValue(row) {
      if (row.valueDictType != null && row.valueDictType != '' && row.valueDictType != undefined) {
        //本次疾病/症状起病时 需要特殊处理
        if(row.valueDictType == "cs_time_unit"){
          let tNowValueList = row.oldValue.splice("-");
          return tNowValueList[0] + this.selectDictLabel((this.dictList.find(item => {return item.dictType === row.valueDictType}).dictDate), tNowValueList[1]);
        }else {
          return this.selectDictLabel((this.dictList.find(item => {return item.dictType === row.valueDictType}).dictDate), row.oldValue);
        }
      }else{
        if(row.itemKey == "complaintTime"){
          return moment(row.oldValue).format('YYYY-MM-DD HH:mm:ss');
        }else {
          return row.oldValue;
        }
      }
    }
  }
}
</script>
