<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span style="color: blue">投诉质检处理</span>
    </div>
    <el-form ref="ruleForm" :model="tableData" style="padding-bottom: 30px;" label-width="100px"
             :disabled="routerParams.status=='show'">
      <el-table :data="tableData.items" :header-cell-style="{color:'black',background:'#f8f8ff'}"
                highlight-current-row
                tooltip-effect="dark"
                :span-method="objectSpanMethod"
                lign="center">
        <el-table-column v-if="businessType=='03'" label="质检分类" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-input v-model.trim="scope.row.className" v-show="scope.row.show" size="mini"/>
              <span>{{scope.row.className }} </span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column label="质检项目" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-input v-model.trim="scope.row.itemName" v-show="scope.row.show" size="mini"/>
              <span v-show="!scope.row.show">{{scope.row.itemName}}</span>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column label="是否存在差错" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-select v-model="scope.row.itemValue" size="mini" placeholder="请选择" @change="changeValue">
                <el-option v-for="option in valueOptions" :key="option.dictValue" :label="option.dictLabel"
                           :value="option.dictValue">
                  <!-- <span style="float: left">{{ item.dictValue }}</span>-->
                  <!-- <span style="float: right; color: #8492a6; font-size: 13px">{{ item.dictLabel }}</span>-->
                </el-option>
              </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column label="质检说明" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-form-item>
              <el-input v-model="scope.row.itemRemark" v-show="!scope.row.show" size="mini"/>
              <span v-show="scope.row.show">{{scope.row.itemRemark}}</span>
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
      <el-row v-if="businessType=='03'"><!--判断01 03-->
        <el-form-item label="质检评分">
          <el-input v-model="tableData.score"/>
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
              <el-select v-model="scope.row.appealFlag" size="mini" placeholder="请选择" @change="changeAppealFlag">
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
              <el-input v-model="scope.row.appealReason" size="mini"/>
            </el-form-item>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
  </el-card>
</template>

<script>
  let dictss = [
    {dictType: 'cs_whether_flag'},

  ]
  export default {
    name: "complaintProcessInfo",
    props: {
      routerParams: Object,
      businessType: String,
    },
    watch: {
      businessType: {
        handler(val) {
        },
        immediate: true
      }
    },
    data() {
      return {
        spanArr: [],//用于存放每一行记录的合并数
        dictList: [],
        complaintProcessList: [],
        valueOptions: [],
        appealFlagOptions: [],
        isshow: true,
        tableData: {
          inspectionId: '',//质检号
          score: '',//评分
          status: '04',//01待确认02已确认03结案04无
          appeal: [{
            appealName: '是否申诉',
            appealFlag: '',//是否申诉
            appealReason: '',//申诉理由
          }],
          items: [ //质检项目
            {
              classType: '0101',//分类code
              className: '啊啊啊',//分类名
              itemType: '',//项目code
              itemName: '投诉件录入时效1',//项目名
              itemValue: '02',
              itemRemark: ''
            }, {
              classType: '0101',//分类code
              className: '啊啊啊',//分类名
              itemType: '',//项目code
              itemName: '投诉件录入时效2',//项目名
              itemValue: '02',
              itemRemark: ''
            }, {
              classType: '0202',//分类code
              className: '呵呵呵',//分类名
              itemType: '',//项目code
              itemName: '投诉件录入时效3',//项目名
              itemValue: '02',
              itemRemark: ''
            }, {
              classType: '0202',//分类code
              className: '呵呵呵',//分类名
              itemType: '',//项目code
              itemName: '投诉件录入时效4',//项目名
              itemValue: '02',
              itemRemark: ''
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
      //调用接口获取数据 changeValue
      /* getHandleInfoList(query).then(res => {
         if (res!=null && res.code=='200'){
           this.tableData.appeal[0].appealFlag=res.data.appealFlag
           this.tableData.appeal[0].appealReason=res.data.appealReason
           this.tableData.inspectionId=res.data.inspectionId
           this.tableData.score=res.data.score
           this.tableData.status=res.data.status
           this.changeValue()
           if (this.businessType=='03'){
             this.getSpanArr(this.tableData.items);
           }
         }
       }).catch( res => {
       });*/
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
      },
      changeAppealFlag(value) {
        if (this.params.businessType == '03' && this.params.node == 'mistake' ) {
          if (value == '01'){
            this.$emit('changeAppealFlag','01')
          }else {
            this.$emit('changeAppealFlag','02')
          }
        }
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





