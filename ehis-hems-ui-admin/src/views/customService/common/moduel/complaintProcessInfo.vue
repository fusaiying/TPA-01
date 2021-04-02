<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span style="color: blue">投诉质检处理</span>
    </div>
    <el-table :data="tableData.items"
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              size="small"
              highlight-current-row
              tooltip-effect="dark"
              :span-method="objectSpanMethod"
              style="width: 100%;">
      <el-table-column v-if="businessType=='03'" label="质检分类" show-overflow-tooltip align="center">
        <template slot-scope="scope">
          <el-input :disabled="routerParams.status=='show' || node=='mistake'" v-model.trim="scope.row.className" v-show="scope.row.show" size="mini"/>
          <span>{{scope.row.className }} </span>
        </template>
      </el-table-column>
      <el-table-column label="质检项目" show-overflow-tooltip align="center">
        <template slot-scope="scope">
          <el-input :disabled="routerParams.status=='show' || node=='mistake'" v-model.trim="scope.row.itemName" v-show="scope.row.show" size="mini"/>
          <span v-show="!scope.row.show">{{scope.row.itemName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否存在差错" show-overflow-tooltip align="center">
        <template slot-scope="scope">
          <el-select :disabled="routerParams.status=='show' || node=='mistake'" v-model="scope.row.itemValue" size="mini" placeholder="请选择"
                     @change="changeValue">
            <el-option v-for="option in valueOptions" :key="option.dictValue" :label="option.dictLabel"
                       :value="option.dictValue">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="质检说明" show-overflow-tooltip align="center">
        <template slot-scope="scope">
          <el-input :disabled="routerParams.status=='show' || node=='mistake'" v-model="scope.row.itemRemark" v-show="!scope.row.show" size="mini"/>
          <span v-show="scope.row.show">{{scope.row.itemRemark}}</span>
        </template>
      </el-table-column>
    </el-table>
    <el-form ref="ruleForm" :model="tableData" style="padding-bottom: 30px;" label-width="100px"
             :disabled="routerParams.status=='show'" :rules="rules">
      <el-row v-if="businessType=='03'"><!--判断01 03-->
        <el-form-item label="质检评分" prop="score">
          <el-input :disabled="node=='mistake'" v-model="tableData.score"/>
        </el-form-item>
      </el-row>
      <el-table :data="tableData.appeal" v-if="tableData.status!='04'">
        <el-table-column show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-input v-model="scope.row.appealName" v-show="scope.row.show" size="mini"/>
              <span v-show="!scope.row.show">{{scope.row.appealName}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-select :disabled="node=='pool'" v-model="scope.row.appealFlag" size="mini" placeholder="请选择"
                         @change="changeAppealFlag">
                <el-option v-for="option in appealFlagOptions" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item label="申诉理由：">
              <el-input :disabled="node=='pool'" v-model="scope.row.appealReason" size="mini"/>
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
  </el-card>
</template>

<script>
  import {getQualityHandleInfo, insetQualityHandleInfo} from '@/api/customService/spotCheck';

  let dictss = [
    {dictType: 'cs_whether_flag'},

  ]
  export default {
    name: "complaintProcessInfo",
    props: {
      routerParams: Object,
      businessType: String,
      node: String,
    },
    watch: {
      businessType: {
        handler(val) {
        },
        immediate: true
      }
    },
    data() {
      const checkNum = (rule, value, callback) => {
        const regx = /^(\d+|\d+\.\d{1,2})$/
        if (!value) {
          callback(new Error("质检评分必填"));
        } else {
          if (value < 0) {
            callback(new Error("质检评分应为0~100，请检查"));
          } else if (!regx.test(value)) {
            callback(new Error("质检评分最多保留两位小数，请检查"));
          } else if (value>100) {
            callback(new Error("质检评分应为0~100，请检查"));
          } else {
            callback();
          }
        }
      }
      return {
        rules: {
          score: [{validator: checkNum, required: true, trigger: 'blur'}], // 案件数
        },
        spanArr: [],//用于存放每一行记录的合并数
        dictList: [],
        complaintProcessList: [],
        valueOptions: [],
        appealFlagOptions: [],
        isshow: true,
        tableData: {
          inspectionId: '',//质检号
          score: '',//评分
          status: '',//01待确认02已确认03结案04无
          appeal: [{
            appealName: '是否申诉',
            appealFlag: '',//是否申诉
            appealReason: '',//申诉理由
          }],
          items: []
        },
      }
    },
    created() {
      this.params = JSON.parse(decodeURI(this.$route.query.data))
      //获取处理信息
      let query = {
        workOrderNo: this.params.workOrderNo,
        inspectionHandlerId: this.params.inspectionHandlerId,
        businessType: this.params.businessType,
        inspectionId: this.params.inspectionId,
      }
      //调用接口获取数据 changeValue
      getQualityHandleInfo(query).then(res => {
        if (res != null && res.code == '200') {
         /* this.tableData.appeal[0].appealFlag = res.data.appealFlag
          this.tableData.appeal[0].appealReason = res.data.appealReason*/
          this.tableData.inspectionId = res.data.inspectionId
          this.tableData.score = res.data.score
          this.tableData.items = res.data.items
          this.tableData.status = res.data.status
          this.changeValue()
          if (this.businessType == '03') {
            this.getSpanArr(this.tableData.items);
          }
        }
      }).catch(res => {
      });
      this.getSpanArr(this.tableData.items);
      this.selectAppealStatus();
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
      selectAppealStatus() {
        if (this.$emit('selectHandleStatus') !== null) {
          this.isshow = false;
        }
      },
      changeValue() {
        if (this.tableData.status == '04') {
          let flag = false
          this.tableData.items.forEach(item => {
            if (item.itemValue != '02') {
              flag = true
            }
          })
          if (flag) {
            this.$emit('changeShowStatus', '1')
          } else {
            this.$emit('changeShowStatus', '2')
          }

        }
      },
      getSpanArr(data) {
        // data就是我们从后台拿到的数据
        for (var i = 0; i < data.length; i++) {
          if (i === 0) {
            this.spanArr.push(1);
            this.pos = 0;
          } else {
            // 判断当前元素与上一个元素是否相同
            if (data[i].classType === data[i - 1].classType) {
              this.spanArr[this.pos] += 1;
              this.spanArr.push(0);
            } else {
              this.spanArr.push(1);
              this.pos = i;
            }
          }
        }
      },
      objectSpanMethod({row, column, rowIndex, columnIndex}) {
        if (this.params.businessType == '03') {
          if (columnIndex === 0) {
            const _row = this.spanArr[rowIndex];
            const _col = _row > 0 ? 1 : 0;
            console.log(`rowspan:${_row} colspan:${_col}`);
            return {
              // [0,0] 表示这一行不显示， [2,1]表示行的合并数
              rowspan: _row,
              colspan: _col
            };
          }
        }

      },
      changeAppealFlag(value) {
        if (this.params.businessType == '03' && this.params.node == 'mistake') {
          if (value == '01') {
            this.$emit('changeAppealFlag', '01')
          } else {
            this.$emit('changeAppealFlag', '02')
          }
        }
      },
      getPlaintAllData() {
        let data = {
          inspectionId: this.tableData.inspectionId,//质检号
          score: this.tableData.score,//评分
          appealFlag: this.tableData.appeal[0].appealFlag,//是否申诉
          appealReason: this.tableData.appeal[0].appealReason,//申诉理由
          items: this.tableData.items
        }
        return data
      },
      checkForm(){
        let data = true
        this.$refs.ruleForm.validate((valid) => {
          if (valid) {
            data=true
          }else {
            data=false
          }
        })
        return data
      }
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





