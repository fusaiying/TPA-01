<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>质检处理信息</span>
    </div>
    <el-form ref="ruleForm" :model="inspection" style="padding-bottom: 30px;" label-width="100px">
      <el-table :data="inspection.items"  >
        <el-table-column  label="质检项目" show-overflow-tooltip align="center" disabled="true">
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
              <el-select v-model="scope.row.value" v-show="!scope.row.show"  size="mini" placeholder="请选择">
                <el-option v-for="item in valueOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                </el-option>
              </el-select>
              <span v-show="scope.row.show">{{scope.row.value}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="质检说明" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'items.' + scope.$index + '.itemRemark'">
              <el-input v-model="scope.row.itemRemark" v-show="!scope.row.show" clearable size="mini" />
              <span v-show="scope.row.show">{{scope.row.itemRemark}}</span>
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
      <el-table :data="inspection.appeal"  v-show="false" >
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'appeal.' + scope.$index + '.appealName'" >
              <el-input v-model="scope.row.appealName" v-show="scope.row.show"  size="mini" />
              <span v-show="!scope.row.show">{{scope.row.appealName}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'appeal.' + scope.$index + '.appealFlag'" >
              <el-select v-model="scope.row.appealFlag"   size="mini" placeholder="请选择">
                <el-option v-for="item in appealFlagOptions" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item label="申诉理由：" :prop="'appeal.' + scope.$index + '.appealReason'">
              <el-input v-model="scope.row.appealReason"   size="mini" />
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
  </el-card>
</template>

<script>
import {insertItem} from '@/api/customService/spotCheck'

let dictss = [
  {dictType: 'cs_whether_flag' },
]
export default {
  name: "inspectionProcessInfo",
  props: {

  },

data(){
      return {
        inspectionList: [],
        dictList: [],
        valueOptions: [],
        appealFlagOptions: [],
        inspection:{
          appeal:[
            {
              appealName: '是否申诉',
              appealFlag : '',
              appealReason: '',
            }
          ],
          items:[
            {
              itemKey:'是否时效内响应客户',
              value: '',
              itemRemark: '',
            },
            {
              itemKey:'是否符合短信结案规则',
              value: '',
              itemRemark:'',
            },
            {
              itemKey:'是否电话通知客户',
              value: '',
             itemRemark: '',
            },
            {
              itemKey:'是否满足客户诉求',
              value: '',
              itemRemark: '',
            },
            {
              itemKey:'是否及时升级投诉',
              value: '',
              itemRemark: '',
            },
            {
              itemKey:'是否规范记录',
              value: '',
              itemRemark: '',
            },
            {
              itemKey:'是否有其他错误',
              value: '',
              itemRemark: '',
            },
          ]
        },
      }
},

created() {
  this.params = JSON.parse(decodeURI(this.$route.query.data))
  //获取受理信息
  let query = {
    workOrderNo: this.params.workOrderNo,
    businessType: this.params.businessType,
  }
  if(this.inspectionList!==null){
    this.data=this.inspection;
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
        console.log(this.complaintProcess)
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





