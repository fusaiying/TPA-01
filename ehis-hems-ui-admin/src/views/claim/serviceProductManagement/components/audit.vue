<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div style="position: relative; ">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane :label="label.label01" name="01">
          <el-form ref="reviewForm" :model="reviewForm" style="padding-bottom: 30px;" label-width="150px" :rules="reviewFormRule"
                   size="mini" class="baseInfo_class">
            <el-row>
              <el-row :span="8">
                <el-form-item label="审核结果：" prop="checkResult">
                  <el-select v-model="reviewForm.checkResult" class="item-width"  clearable>
                    <el-option v-for="item in product_review_resultOptions" :label="item.dictLabel" :value="item.dictValue"
                               :key="item.dictValue"/>
                  </el-select>
                </el-form-item>
              </el-row>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item style="margin-right: 20px;" label="审核意见：" prop="checkAdvice">
                  <el-input
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="reviewForm.checkAdvice" maxlength="200">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <div style="text-align: right; margin-right: 10px;">
              <el-button
                size="mini"
                type="primary"
                @click="submitHandle"
              >提交
              </el-button>
              <el-button size="mini" type="primary" @click="goBack">关闭</el-button>
            </div>
          </el-form>
        </el-tab-pane>
        <el-tab-pane :label="label.label02" name="02">
          <!--          <el-table  :data="reviewLogTableData"
                               :header-cell-style="{color:'black',background:'#f8f8ff'}"
                               size="small" highlight-current-row style="width: 100%;">
                      <el-table-column key="1"  align="center" prop="updateBy" min-width="150" label="操作人" show-overflow-tooltip/>
                      <el-table-column  key="2"  align="center" min-width="100" prop="updateTime" label="时间" show-overflow-tooltip/>
                      <el-table-column key="3"  align="center" prop="checkResultName" label="审核结论" min-width="120" show-overflow-tooltip/>
                      <el-table-column key="4"  align="center" prop="checkAdvice" min-width="160" label="审核意见" show-overflow-tooltip/>
                    </el-table>-->
          <div style="text-align: right; margin-right: 10px;">
            <el-button size="mini" type="primary"  @click="goBack">关闭</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </el-card>


</template>

<script>
import {insertCheckInfo} from '@/api/productManage/serviceProductManagement'
export default {
  props:{
    productCode: String
  },

  data() {
    const checkReviewOpinion=(rules, value, callback) => {
      if (this.reviewForm.checkResult=='02') {
        if(!value) {
          callback(new Error('审核意见不能为空！'))
        } else {
          callback()
        }

      } else {
        callback()
      }
    }
    return {
      reviewForm: {},
      activeName: '01',
      label: {
        label01: '审核',
        label02: '日志'
      },
      reviewFormRule: {
        checkResult:  [{required: true, message: '不能为空！', trigger: 'change'}],
        checkAdvice: [{validator: checkReviewOpinion,trigger:'blur'}]
      },
      reviewLogTableData: [],
      product_review_resultOptions: []
    }
  },
  mounted() {
    this.getDicts("product_review_result").then(response => {
      this.product_review_resultOptions = response.data;
    });
  },
  watch: {},

  methods: {

    handleClick(){
      /*if(this.activeName=='02'){
        //历史合约的接口
        selectCheckInfo(this.providerCode).then(res => {
          this.reviewLogTableData = res.rows
        }).catch(res => {
        })
      }*/

    },

    goBack(){
      this.$router.push({
        path: '/service-product/productReview/index'

      })
    },
    submitHandle(){
      let formData={
        productCode: this.productCode,
        checkResult: this.reviewForm.checkResult,
        checkAdvice:  this.reviewForm.checkAdvice
      }
      this.$refs.reviewForm.validate((valid) => {
        if(valid) {
          insertCheckInfo(formData).then(res => {
            if (res.code == '200') {
              this.$message({
                message: '审核成功！',
                type: 'success',
                center: true,
                showClose: true
              })
            } else {
              this.$message({
                message: '审核失败!',
                type: 'error',
                center: true,
                showClose: true
              })
            }
          })
        }

      })


    }
  }
}
</script>

<style scoped>
.item-width {
  width: 220px;
}

</style>
