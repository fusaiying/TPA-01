<template>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span style="color: blue">服务处理</span>
      </div>
      <el-form ref="infoForm" :model="infoForm" style="padding-bottom: 30px;" label-width="160px" :disabled="isDisabled || routerParams.status=='show'"
               label-position="right" size="mini" >
        <el-col :span="22">
          <el-form-item label="业务处理情况：" prop="businessProcess">
            <el-row>
              <el-input  type="text" v-model="infoForm.businessProcessType"/>
            </el-row>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="处理说明：" prop="remark">
            <el-row>
              <el-input type="textarea" :row="2" v-model="infoForm.remark"/>
            </el-row>
          </el-form-item>
        </el-col>
      </el-form>
    </el-card>
</template>cs_handle_state

<script>
    let dictss = [{dictType: 'cs_handle_state'},]
import {complainSearchServer} from '@/api/customService/complaint';
export default {
  name: "infohandle",
  props: {
    routerParams:Object,
    isDisabled: {
      type:Boolean,
      default:false
    },
    acceptInfo: {
      type: Object,
      default: function (){
        return {}
      }
    },
  },
  watch: {
    acceptInfo: function (newValue){
          /*      this.acceptData = newValue
                console.log("infohandleValue");
                console.info(newValue);
                this.infoForm.businessProcess= this.acceptData.businessProcess,
                this.infoForm.remark= this.acceptData.remark*/
      },
      routerParams:function(value){
          console.log(1111111111);

       },
  },

  data(){
    return {
      infoForm:{
        businessProcess:'',
        remark:'',
      },
        dictList:[],
        cs_handle_stateOption:[],
    }
  },
  created() {
      this.searchHandleServer()
  },
    async mounted() {
        await this.getDictsList(dictss).then(response => {
            this.dictList = response.data
        })
        this.cs_handle_stateOption = this.dictList.find(item => {
            return item.dictType === 'cs_handle_state'
        }).dictDate
    },
  methods: {

      //反显暂存信息
      searchHandleServer() {
          let insert = {
              workOrderNo:this.routerParams.workOrderNo,
              businessType:this.routerParams.businessType
          }

          console.log(insert);
          console.log(111111111);
          complainSearchServer(insert).then(res => {
              if (res != null && res.code === 200) {
                  console.log("预约页面server反显数据", res.data)

                  this.infoForm = res.data;
                  this.infoForm.businessProcessType=this.selectDictLabel(this.cs_handle_stateOption,this.infoForm.businessProcess)
                  console.log(this.submitForm, "85848541484848")
                  if (res.rows.length <= 0) {
                      return this.$message.warning(
                          "未查询到数据！"
                      )
                  }
              }
          }).catch(res => {

          })
      },

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
