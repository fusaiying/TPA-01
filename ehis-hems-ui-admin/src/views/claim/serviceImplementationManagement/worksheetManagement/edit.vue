<template>
  <div class="app-container">
  <!--客户信息-->
  <div>
    <client-info></client-info>
  </div>
    <!--服务信息-->
    <div >
      <service-info :orderCode="orderCode" ref="serviceInfo"></service-info>
    </div>

  <!--资料信息-->
  <div>
   <resource-info></resource-info>
  </div>


  <div
    style="line-height: 50px; margin-bottom: 20px; padding-right: 20px;  border-bottom: 1px solid #e6ebf5;color: #303133;float: right; ">
    <el-button size="mini" type="primary" @click="submitHandle" >审核完毕</el-button>
    <el-button size="mini" type="primary" @click="distribution" >分配</el-button>
    <el-button size="mini" type="primary" @click="sendBack" >退回</el-button>
    <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
  </div>
    <!--    分配-->
    <el-dialog
      :visible.sync="allotDialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeAllotDialogVisable"
      title="分配"
      width="500px"
    >
      <el-form ref="allotForm" :model="allotForm"  size="mini" label-width="150px"  :rules="allotFormRules">
        <el-row>
          <el-col :span="24">
            <el-form-item label="选择供应商：" prop="supplierName" >
              <el-select v-model="allotForm.supplierName" class="item-width" placeholder="请选择" clearable style="width: 200px">
                <el-option v-for="item in productTypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="saveAllot">确认</el-button>
        <el-button size="mini" @click="changeAllotDialogVisable">返回</el-button>
      </div>

    </el-dialog>
    <!--    退回工单-->
    <el-dialog
      :visible.sync="dialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      :before-close="changeDialogVisable"
      title="退回工单"
      width="600px">
      <el-form ref="remarkForm" :model="remarkForm"  size="mini" label-width="80px" :rules="remarkFormRules">
        <el-row>
          <el-col :span="8">
            <el-form-item prop="reason" style="position:relative;width: 500px" label="退回原因">
              <el-input type="textarea" :rows="7" v-model="remarkForm.reason"
                        class="item-widths" clearable maxlength='1000'
                        size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="saveRemark">确认</el-button>
        <el-button size="mini" @click="changeDialogVisable">返回</el-button>
      </div>

    </el-dialog>


  </div>

</template>

<script>
import {saveProductInfo,checkProductInfo} from '@/api/baseInfo/serviceProductManagement'
import clientInfo   from './components/clientInfo'
import serviceInfo   from './components/serviceInfo'
import resourceInfo   from './components/resourceInfo'


export default {
  components: {
    clientInfo,
    serviceInfo,
    resourceInfo

  },
  data() {
    return {
      orderCode: '',
      allotDialogVisable: false,
      dialogVisable: false,
      allotForm: {
        supplierName: ''
      },
      remarkForm: {
        reason: ''
      },
      allotFormRules:{supplierName: [{required:true,message:'供应商不能为空',trigger:'change'}]},
      remarkFormRules:{reason: [{required:true,message:'退回原因不能为空',trigger:'blur'}]},
    }
  },
   created() {
    this.init()

  },
  methods: {
    init(){
      if(this.$route.query.status=='edit'){
        this.orderCode=this.$route.query.orderCode
      }

    },







    //提交审核
    submitHandle(){

        let serviceInfoFlag=this.$refs['serviceInfo'].validateForm()
      if(serviceInfoFlag){
        //调用审核的接口
      }
      else{
        this.$message.warning('服务信息必录项未必录')
      }



    },
    //分配
    distribution(){

      this.allotDialogVisable=true
      //调用服务编码查询供应商的接口
      let query={

      }
      //供应商【】清空

    },
    changeAllotDialogVisable(){
      this.$refs.allotForm.resetFields()
      this.allotDialogVisable=false
    },

    saveAllot(){
      this.$refs.allotForm.validate(valid => {
        if (valid) {
          //let productData = this.tableData[this.index]
          //调用分配的接口
        }
      })

    },


    //退回
    sendBack(){
      this.dialogVisable=true
    },
    changeDialogVisable(){
      this.$refs.remarkForm.resetFields()
      this.dialogVisable=false
    },
    saveRemark(){
      this.$refs.remarkForm.validate(valid => {
        if(valid){
          let productData=this.tableData[this.index]
          //调用接口
          /*          insertMangerInfo(productData).then(res=>{
                      if (res !=null && res.code == '200') {
                        this.$message({
                          message: '操作成功！',
                          type: 'success',
                          center: true,
                          showClose: true
                        })
                        this.saveFlag=true
                      } else {
                        this.$message({
                          message: '操作失败!',
                          type: 'error',
                          center: true,
                          showClose: true
                        })
                        this.tableData[this.checkIndex].bussinessStatus='03'
                      }
                    })*/
        }
      })
    },


    //关闭
    goBack(){
      this.$router.go(-1);
    },


  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item /deep/ label {
  font-weight: normal;
}

/deep/ .el-table__header-wrapper .el-checkbox__input::after {
  content: '全选';
  position: absolute;
  font-weight: bolder;
  margin-left: 5px;
}
</style>

