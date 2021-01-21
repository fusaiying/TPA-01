<template>

    <el-card class="box-card" style="margin-top: 10px;">
      <div style="position: relative">
        <div slot="header" class="clearfix">
          <div style="width: 100%;cursor: pointer;">
            <span id="span1" :class="[isActiveSpan1?'span-tab is-active':'span-tab']" @click="activeFun">赔付结论</span>
            <span id="span2" :class="[isActiveSpan2?'span-tab is-active':'span-tab']" @click="activeFun">协谈</span>
            <span id="span3" :class="[isActiveSpan3?'span-tab is-active':'span-tab']" @click="activeFun">调查</span>
            <div style="float: right;">
              <el-button v-if="isButtonShow" type="primary" size="mini" >保存 </el-button>
              <el-button v-if="isButtonShow" type="primary" size="mini" >审核完毕</el-button>
              <el-button size="mini" type="primary">退回受理</el-button>
            </div>
          </div>
        </div>
        <el-divider/>

        <el-form v-if="isActiveSpan1" ref="conclusionForm" :model="conclusionForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" :rules="rules"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">账单金额：</span><span class="info_span">AAFFFFFFFFFFFFFFFFFFFFF</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">折扣金额：</span><span class="info_span">DD</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">赔付金额：</span><span class="info_span">FFFFFFF</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">账单金额：</span><span class="info_span_col">{{ conclusionInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">折扣金额：</span><span class="info_span_col">{{ conclusionInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">赔付金额：</span><span class="info_span_col">{{ conclusionInfo.contractName }}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">拒赔金额：</span><span class="info_span_col">{{ conclusionInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">追讨金额：</span><span class="info_span_col">{{ conclusionInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">本次支付差额：</span><span class="info_span_col">{{ conclusionInfo.amount}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">账单币种：</span><span class="info_span_col">{{ conclusionInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">汇率：</span><span class="info_span_col">{{ conclusionInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">外币给付金额：</span><span class="info_span_col">{{ conclusionInfo.contractName }}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">赔付结论：</span><span class="info_span_col">{{ conclusionInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">拒赔原因：</span><span class="info_span_col">{{ conclusionInfo.amount}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="客户备注：" prop="remark">
                <el-input  style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                          v-model="conclusionForm.customerRemark" class="item-width" clearable size="mini"
                          placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="核赔依据：" prop="remark">
                <el-input style="width:100%" col="2" type="textarea" row="4" maxlength="1000"
                          v-model="conclusionForm.fileRemark" class="item-width" clearable size="mini"
                          placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

          <!--保存 关闭 start-->
         <!-- <el-row >
            <el-col :span="6" :offset="20">

            </el-col>
          </el-row>-->
          <!--保存 关闭 end-->

        </el-form>

        <el-form v-if="isActiveSpan2" ref="discussionForm" :inline="true" :model="discussionForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" :rules="rules"
                 label-position="right" size="mini">
          <el-row>

            <el-col :span="20" :xs="24">
                <el-form-item label="协谈类型：" prop="fileType">
                  <el-select v-model="discussionForm.discussType" class="item-width" size="mini" placeholder="请选择">
                  </el-select>
                  <el-select v-model="discussionForm.idCard" class="item-width" size="mini" placeholder="请选择">
                  </el-select>
                </el-form-item>
            </el-col>

          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="转出意见：" prop="remark">
                <el-input style="min-width: 700px" col="2" type="textarea" row="4" maxlength="1000"
                          v-model="discussionForm.remark" clearable size="mini"
                          placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>

        <el-form v-if="isActiveSpan3" ref="surveyForm" :model="surveyForm"  style="padding-bottom: 30px;margin-top:20px;margin-left: 5%" label-width="130px" :rules="rules"
                 label-position="right" size="mini">
          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">调查任务号：</span><span class="info_span_col">{{ surveyInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">被保人：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">性别：</span><span class="info_span_col">{{ surveyInfo.contractName }}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">证件类型：</span><span class="info_span_col">{{ surveyInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">证件号码：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">出险日期：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <span class="info_span_col to_right">事故地点：</span><span class="info_span_col">{{ surveyInfo.contractNo }}</span>
            </el-col>
            <el-col :span="8">
              <span class="info_span_col to_right">事故经过：</span><span class="info_span_col">{{ surveyInfo.amount}}</span>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="提调类型：" prop="Type">
                <el-select v-model="surveyForm.Type" class="item-width" size="mini" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="提调原因：" prop="reason">
                <el-select v-model="surveyForm.reason" class="item-width" size="mini" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="调查机构：" prop="surProvider">
                <el-select v-model="surveyForm.surProvider" class="item-width" size="mini" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="提调机构：" prop="provider">
                <el-select v-model="surveyForm.provider" class="item-width" size="mini" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="保单号：" prop="policyNo">
                <el-select v-model="surveyForm.policyNo" class="item-width" size="mini" placeholder="请选择">
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-col :span="8">
            <el-form-item label="提调事项：  "prop="contractNo" key="contractNo2">
              <el-input maxlength="50" v-model="surveyForm.tdx" class="item-width"  clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>


        </el-form>
      </div>
    </el-card>

</template>

<script>

  export default {
    components: {
    },
    data() {
      return {
        isButtonShow:true,
        //赔付结论信息 start
        conclusionInfo:{

        },
        conclusionForm:{

        },
        //赔付结论信息 end

        //协谈信息 start
        discussionForm:{

        },
        //协谈信息 end

        //调查信息 start
        surveyInfo:{

        },
        surveyForm:{

        },

        //tab 切换
        loading:false,
        isActiveSpan1:true,
        isActiveSpan2:false,
        isActiveSpan3:false,

      }
    },
    created() {
    },
    mounted() {
    },
    watch: {
    },
    computed: {
    },
    methods: {
      activeFun(obj){

       // console.log(obj);
        let id = obj.id;

        switch(obj.toElement.id) {
          case 'span1':
            this.isButtonShow=true
            this.isActiveSpan1 = true;
            this.isActiveSpan2 = false;
            this.isActiveSpan3 = false;
            break;
          case 'span2':
            this.isButtonShow=false
            this.isActiveSpan1 = false;
            this.isActiveSpan2 = true;
            this.isActiveSpan3 = false;
            break;
          case 'span3':
            this.isButtonShow=false
            this.isActiveSpan1 = false;
            this.isActiveSpan2 = false;
            this.isActiveSpan3 = true;
            break;
          default :
        }


      },
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  /deep/.el-tabs__item.is-disabled {
    color: #303133;
    cursor:default
  }
  /deep/.el-tabs__item.is-active {
    color: #409EFF;
  }
  .span-tab {
    padding-right: 3%;
  }
  /deep/.span-tab:hover {
    color: #1890ff;
    cursor: pointer;
  }
  /deep/.span-tab {
    height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 40px;
    display: inline-block;
    list-style: none;
    font-size: 14px;
    font-weight: 500;
    color: #303133;
    position: relative;
  }
  /deep/.span-tab.is-active {
    color: #1890ff;
  }

  /deep/.span-tab1 {
    padding: 0 20px;
    height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 40px;
    display: inline-block;
    list-style: none;
    font-size: 14px;
    font-weight: 500;
    color: #303133;
    position: relative;
  }

  /deep/.el-divider--horizontal {
    display: block;
    height: 1px;
    width: 100%;
    margin: 0 0;
  }
  /deep/.info_span_col {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #606266;
    line-height: 40px;
    padding: 0 12px 0 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }
  /deep/.to_right {
    width: 130px;
    text-align: right;
  }
</style>
