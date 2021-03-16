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
              <el-input v-model.trim="scope.row.itemType" v-show="scope.row.show" size="mini" />
              <span v-show="!scope.row.show">{{scope.row.itemType}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="质检项目" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.itemKey'" >
              <el-input v-model.trim="scope.row.itemKey" v-show="scope.row.show" size="mini" />
              <span v-show="!scope.row.show">{{scope.row.itemKey}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="是否存在差错" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.value'" >
              <el-select v-model="scope.row.value" size="mini" placeholder="请选择">
                <el-option v-for="item in valueOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="质检说明" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.itemRemark'">
              <el-input v-model="scope.row.itemRemark" v-show="!scope.row.show" size="mini" />
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
      <el-table :data="complaintProcess.appeal" v-show="false">
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'appeal.' + scope.$index + '.appealName'" >
              <el-input v-model="scope.row.appealName" v-show="scope.row.show" size="mini" />
              <span v-show="!scope.row.show">{{scope.row.appealName}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'appeal.' + scope.$index + '.appealFlag'" >
              <el-select v-model="scope.row.appealFlag" size="mini" placeholder="请选择">
                <el-option v-for="item in appealFlagOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item label="申诉理由：" :prop="'appeal.' + scope.$index + '.appealReason'">
              <el-input v-model="scope.row.appealReason" size="mini" />
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
     complaintProcessList: [],
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
           itemType:'时效性',
           itemKey:'投诉件录入时效',
           value:'',
           itemRemark: '',
         },
         {
           itemType:'时效性',
           itemKey: '响应时间',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'时效性',
           itemKey: '根因改善闭环时效',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '受理渠道',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '投诉分类',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '监管计件',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '监管撤诉状态',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '投诉原因',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '客户反馈',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '投诉损失',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '投诉是否成立',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '投诉根因部门',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '根因改善',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '致诉根因',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '处理结果',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '附件完整性',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '征求处理意见',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '处理意见',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'准确性',
           itemKey: '行协调解或外部鉴定状态',
           value: '',
           itemRemark: '',
         },
         {
           itemType:'真实性',
           itemKey: '案件真实性',
           value: '',
           itemRemark: '',
         },
       ]
     }
   }
},
created() {
  this.params = JSON.parse(decodeURI(this.$route.query.data))
  //获取受理信息
  let query = {
    workOrderNo: this.params.workOrderNo,
    businessType: this.params.businessType,
  }
  if(this.complaintProcessList!==null){
    this.data=this.complaintProcess;
  }
  getHandleInfoList(query).then(res => {
    if(query.businessType === '01'){
      if (res !== null && res.code === 200) {
        this.inspectionList = res.rows;
        console.log(this.inspectionList,'+++++++++5555555');
      }
    }else if(query.businessType === '03'){
      if (res !== null && res.code === 200) {
        this.complaintProcessList = res.rows;
        console.log(this.allList.complaintProcess)
      }
    }

  }).catch( res => {
  });
},
async mounted() {
  await this.getDictsList(dictss).then(response => {
    this.dictList = response.data
  })
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





