<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>质检处理信息</span>
    </div>
    <el-form ref="ruleForm" :model="inspection" style="padding-bottom: 30px;" label-width="100px">
      <el-table :data="inspection.items" >
            <el-table-column  label="质检项目" show-overflow-tooltip align="center">
              <template slot-scope="scope">
                <el-form-item :prop="'item.' + scope.$index + '.itemKey'" >
                  <el-input v-model.trim="scope.row.itemType" v-show="scope.row.show" clearable size="mini" />
                  <span v-show="!scope.row.show">{{scope.row.itemKey}}</span>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column  label="是否存在差错" show-overflow-tooltip align="center">
               <template slot-scope="scope">
                 <el-form-item :prop="'item.' + scope.$index + '.value'" >
                   <el-select v-model="scope.row.value"  clearable size="mini" placeholder="请选择">
                     <el-option v-for="item in status" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                       <span style="float: left">{{ item.dictValue }}</span>
                       <span style="float: right; color: #8492a6; font-size: 13px">{{ item.dictLabel }}</span>
                     </el-option>
                   </el-select>
                 </el-form-item>
               </template>
            </el-table-column>
            <el-table-column  label="质检说明" show-overflow-tooltip align="center">
              <template slot-scope="scope">
                <el-form-item :prop="'item.' + scope.$index + '.itemRemark'">
                  <el-input v-model="scope.row.itemRemark" v-show="!scope.row.show" clearable size="mini" />
                  <span v-show="!scope.row.show">{{scope.row.itemRemark}}</span>
                </el-form-item>
              </template>
            </el-table-column>
      </el-table>
      <el-table :data="inspection.name" >
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'name.' + scope.$index + '.name'" >
              <el-input v-model="scope.row.name" v-show="scope.row.show" clearable size="mini" />
              <span v-show="!scope.row.show">{{scope.row.name}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item :prop="'name.' + scope.$index + '.value'" >
              <el-select v-model="scope.row.value"  clearable size="mini" placeholder="请选择">
                <el-option v-for="item in status" :key="item.dictValue" :label="item.dictLabel" :value="item.dictValue">
                  <span style="float: left">{{ item.dictValue }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.dictLabel }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column   show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item label="申诉理由：" :prop="'name.' + scope.$index + '.appealReason'">
              <el-input v-model="scope.row.appealReason"   clearable size="mini" />
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
  </el-card>
</template>

<script>
import {insertItem} from '@/api/customService/spotCheck'
export default {
    name: "inspectionProcessInfo",
data(){
      return {
        inspection:{
          name :[
            {
              name : '是否申诉',
              value: '',
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





