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
            <el-table-column key="3"  align="center" prop="checkResult" label="审核结论" min-width="120" :formatter="getCheckResult" show-overflow-tooltip/>
            <el-table-column key="4"  align="center" prop="checkAdvice" min-width="160" label="审核意见" show-overflow-tooltip/>
          </el-table>-->
<!--            <div class="el-steps el-steps&#45;&#45;vertical" style="margin-left: 100px">
              <div  class="el-step is-vertical is-flex" style="height: 70px">
                <div class="" style="width: 200px; margin-right: 20px">
                  {{caseInfo04.updateTime}}
                </div>

                <div :class="[caseInfo04Css ? 'el-step__head is-finish is-flex':'el-step__head is-flex is-process']" >
                  <div class="el-step__line" style="margin-right: 0px;">
                    <i class="el-step__line-inner" style="transition-delay: 0ms; border-width: 1px; height: 100%;"></i>
                  </div>

                  <div  v-if="!ca04Active"class="el-step__icon is-text">
                    <div class="el-step__icon-inner">产品定义</div>
                  </div>
                  <div v-if="ca04Active" class="el-step__icon is-text" style="background-color: #1c84c6">
                    <div class="el-step__icon-inner" style="color: whitesmoke">产品定义</div>
                  </div>


                </div>
                <div class="el-step__main">
                  <div class=""  style="width: 100%; margin-left: 30px">
                    <span>{{caseInfo04.historyFlag === 'Y' ? '已完成' :'' }} {{caseInfo04.historyFlag === 'N' ? '处理中' :'' }} </span>
                    <span style="margin-left: 70px">{{caseInfo04.historyFlag === 'Y' ?  "处理人："+caseInfo04.updateBy :'' }} </span>
                  </div>
                  <div class="el-step__title is-finish"  style="height: 30px">
                  </div>
                </div>
              </div>

              <div  class="el-step is-vertical" style="height: 70px">
                <div class="" style="width: 200px; margin-right: 20px">
                  {{caseInfo05.updateTime}}
                </div>

                <div :class="[caseInfo05Css ? 'el-step__head is-finish is-flex':'el-step__head is-flex is-process']" >
                  <div class="el-step__line" style="margin-right: 0px;">
                    <i class="el-step__line-inner" style="transition-delay: 150ms; border-width: 1px; height: 100%;"></i>
                  </div>
                  <div  v-if="!ca05Active" class="el-step__icon is-text">
                    <div class="el-step__icon-inner"> 产品审核</div>
                  </div>

                  <div v-if="ca05Active" class="el-step__icon is-text" style="background-color: #1c84c6">
                    <div class="el-step__icon-inner" style="color: whitesmoke">产品审核</div>
                  </div>
                </div>

                <div class="el-step__main">
                  <div class=""  style="width: 100%; margin-left: 30px">
                    <span>{{caseInfo05.historyFlag === 'Y' ? '已接收' :'' }} {{caseInfo05.historyFlag === 'N' ? '处理中' :'' }} </span>
                    <span style="margin-left: 70px">{{caseInfo05.historyFlag === 'Y' ?  "处理人："+caseInfo05.updateBy :'' }} </span>
                  </div>
                  <div class="el-step__title is-finish">
                  </div>
                  <div class="el-step__description is-finish" style="height: 30px">
                  </div>
                </div>
              </div>

              <div  class="el-step is-vertical" style="height: 70px">
                <div class="" style="width: 200px; margin-right: 20px">
                  {{caseInfo06.updateTime}}
                </div>
                <div :class="[caseInfo06Css ? 'el-step__head is-finish is-flex':'el-step__head is-flex is-process']" >
                  <div class="el-step__line" style="margin-right: 0px;">
                    <i class="el-step__line-inner" style="transition-delay: 300ms; border-width: 0px; height: 0%;"></i>
                  </div>

                  <div v-if="!ca06Active" class="el-step__icon is-text">
                    <div class="el-step__icon-inner">产品复核</div>
                  </div>
                  <div v-if="ca06Active" class="el-step__icon is-text" style="background-color: #1c84c6">
                    <div class="el-step__icon-inner" style="color: whitesmoke">产品复核</div>
                  </div>

                </div>
                <div class="el-step__main">
                  <div class=""  style="width: 100%; margin-left: 30px">
                    <span>{{caseInfo06.historyFlag === 'Y' ? '已完成' :'' }} {{caseInfo06.historyFlag === 'N' ? '处理中' :'' }} </span>
                    <span style="margin-left: 70px">{{caseInfo06.historyFlag === 'Y' ?  "处理人："+caseInfo06.updateBy :'' }} </span>
                  </div>
                  <div class="el-step__description is-finish"  style="height: 30px">
                  </div>
                </div>
              </div>



              <div  class="el-step is-vertical is-flex" style="height: 70px">
                <div class="" style="width: 200px; margin-right: 20px">
                  {{caseInfo99.updateTime}}
                </div>
                <div :class="[caseInfo99Css ? 'el-step__head is-finish is-flex':'el-step__head is-flex is-process']" >
                  <div class="el-step__line">
                    <i class="el-step__line-inner"></i>
                  </div>

                  <div v-if="!ca99Active" class="el-step__icon is-text">
                    <div class="el-step__icon-inner">结束</div>
                  </div>

                  <div v-if="ca99Active" class="el-step__icon is-text" style="background-color: #1c84c6">
                    <div class="el-step__icon-inner" style="color: whitesmoke">结束</div>
                  </div>

                </div>
                <div class="el-step__main">
                  <div class=""  style="width: 200px; margin-left: 20px">

                  </div>
                  <div class="el-step__description is-process"  style="height: 30px">
                  </div>
                </div>
              </div>
            </div>-->
          <el-steps align-center direction="vertical" :active="active" v-loading="loading" >
            <el-step :key="step.index" :title="step.bussinessStatusName" :description="step.updateTime + ' 处理人：' + step.updateBy" v-for="(step,index) in steps"/>
          </el-steps>




          <div style="text-align: right; margin-right: 10px; clear: both">
            <el-button size="mini" type="primary"  @click="goBack">关闭</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </el-card>


</template>

<script>
import {insertCheckInfo,selectCheckLog,selectLogList} from '@/api/productManage/serviceProductManagement'
import {listBalanceExamLog} from "@/api/claim/serviceBalance";
export default {
  props:{
    productCode: String,
    status: {
      type: String,
      default: ''
    }
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
      // 遮罩层
      loading: false,
      //当前激活
      active: 0,
      //步骤数组
      steps: [],

      pList: [{name: 1},{name: 2},{name: 3},{name: 4},],
      reviewForm: {
        checkResult: '',
        checkAdvice: ''
      },
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
    this.init();
    this.getDicts("product_review_result").then(response => {
      this.product_review_resultOptions = response.data;
    });

  },
  watch: {},

  methods: {
    init() {
      this.loading = true;
      this.active = 0;
      this.steps = [];
      let query = {
        productCode: this.productCode,
      }
      selectLogList(query).then(res => {
        if (res != null && res.code === 200) {
          this.steps = res.data;
          if (res.data.length > 0) {
            this.active = res.data.length - 1;
          }
          this.loading = false;
        }
      }).catch(res => {
        this.loading = false
      });

  },

    getCheckResult(row,index){
        return this.selectDictLabel(this.product_review_resultOptions, row.checkResult);
    },
    handleClick(){
     /* if(this.activeName=='02'){
        let query = {
          productCode: this.productCode,
        }
        //审核日志的接口

        selectCheckLog(query).then(res => {
          this.reviewLogTableData = res.data
        }).catch(res => {
        })
      }*/
    },

    goBack(){
      if(this.status=='review') {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({
          path: '/service-product/productReview/index'
        })
      }
      else if(this.status=='management'){
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({
          path: '/service-product/productManagement/index'
        })
      }
    },
    submitHandle(){
      let bussinessStatus= this.status=='management'? '04' : '02'
      let formData={
        productCode: this.productCode,
        checkResult: this.reviewForm.checkResult,
        checkAdvice:  this.reviewForm.checkAdvice,
        bussinessStatus: bussinessStatus
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
              if(bussinessStatus=='02') {
                this.$store.dispatch("tagsView/delView", this.$route);
                this.$router.push({
                  path: '/service-product/productReview/index',
                })
              }
            else if(bussinessStatus=='04'){
                this.$store.dispatch("tagsView/delView", this.$route);
                this.$router.push({
                  path: '/service-product/productManagement/index',
                })
              }
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
.active {
  padding-top: 49px
}







</style>
