<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>投诉质检处理</span>
    </div>
    <el-form  ref="ruleForm" :model="complaintProcess" style="padding-bottom: 30px;" label-width="100px">
      <el-table :data="complaintProcess.items" >
        <el-table-column  label="质检分类" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.itemType'" >
              <el-input v-model.trim="scope.row.itemType" v-show="scope.row.show" clearable size="mini" />
              <span v-show="!scope.row.show">{{scope.row.itemType}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="质检项目" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.itemKey'" >
              <el-input v-model.trim="scope.row.itemKey" v-show="scope.row.show" clearable size="mini" />
              <span v-show="!scope.row.show">{{scope.row.itemKey}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="是否存在差错" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.value'" >
              <el-select v-model="scope.row.value"  clearable size="mini" placeholder="请选择">
                <el-option v-for="item in valueOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                  <span style="float: left">{{ item.dictValue }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.dictLabel }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="质检说明" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.itemRemark'">
              <el-input v-model="scope.row.itemRemark" v-show="!scope.row.show" clearable size="mini" />
              <span v-show="!scope.row.show">{{scope.row.itemRemark}}</span>
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
      <el-row>
        <el-form-item label="质检评分">
          <el-input v-model="complaintProcess.score"/>
        </el-form-item>
      </el-row>
      <el-table :data="complaintProcess.appeal" >
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'appeal.' + scope.$index + '.appealName'" >
              <el-input v-model="scope.row.appealName" v-show="scope.row.show" clearable size="mini" />
              <span v-show="!scope.row.show">{{scope.row.appealName}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'appeal.' + scope.$index + '.appealFlag'" >
              <el-select v-model="scope.row.appealFlag"  clearable size="mini" placeholder="请选择">
                <el-option v-for="item in appealFlagOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item label="申诉理由：" :prop="'appeal.' + scope.$index + '.appealReason'">
              <el-input v-model="scope.row.appealReason"   clearable size="mini" />
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
  </el-card>
</template>

<script>
  let dictss = [
    {dictType: 'cs_whether_flag' },

  ]
export default {
    name: "complaintProcessInfo",
data(){
   return {
     dictList: [],
     valueOptions: [],
     appealFlagOptions: [],
     complaintProcess: {
       appeal:[
         {
           appealName : '是否申诉',
           appealFlag: '',
           appealReason: '',
         }
       ],
       score:'',
       items: [
         {
           itemType:'',
           itemKey:'投诉件录入时效',
           value:'',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '响应时间',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '根因改善闭环时效',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '受理渠道',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '投诉分类',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '监管计件',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '监管撤诉状态',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '投诉原因',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '客户反馈',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '投诉损失',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '投诉是否成立',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '投诉根因部门',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '根因改善',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '致诉根因',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '处理结果',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '附件完整性',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '征求处理意见',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '处理意见',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '行协调解或外部鉴定状态',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'',
           itemKey: '案件真实性',
           value: '',
           itemRemark: '',
         },
       ]
     }
   }
},
created() {

},
async mounted() {
  await this.getDictsList(dictss).then(response => {
    this.dictList = response.data
  })
  console.log(this.dictList,"85646568569");
  this.valueOptions = this.dictList.find(item => {
    return item.dictType === 'cs_whether_flag'
  }).dictDate
  this.appealFlagOptions = this.dictList.find(item => {
    return item.dictType === 'cs_whether_flag'
  }).dictDate
},
methods: {

}
}
</script>

<style scoped>
  .el-tooltip__popper {
    max-width: 400px;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>





