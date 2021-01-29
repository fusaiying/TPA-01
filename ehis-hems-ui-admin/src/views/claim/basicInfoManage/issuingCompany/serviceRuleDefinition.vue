<template>
  <div class="app-container">
    <el-card class="box-card">

      <div slot="header" class="clearfix">
        <span>服务费规则定义</span>
        <span style="float: right;">
          <el-button size="mini" type="success" @click="saveHandle">保存</el-button>
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <el-form ref="searchForm" :model="formSearch" style="padding-bottom: 30px;" :rules="rules" label-width="110px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="出单公司名称：" prop="companyname">
              <el-input v-model="formSearch.companyname" class="item-width" disabled clearable size="mini"
                        placeholder="平安寿险"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="riskcode">
              <el-select multiple v-model="formSearch.riskcode" class="item-width" placeholder="请选择" clearable @change="cleanOther"  >
                <el-option v-for="item in riskName" :label="item.riskName" :value="item.riskCode" :key="item.riskCode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="结算类型：" prop="accounttype">
              <el-select v-model="formSearch.accounttype" class="item-width" clearable @change="changeShow">
                <el-option v-for="item in accounttypeOptions" :label="item.dictLabel" :value="item.dictValue"
                           :key="item.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="服务费/人：" prop="servicefee" v-if="accounttypeShow">
              <el-input v-model="formSearch.servicefee" class="item-width" style="width: 125px" clearable size="mini"
                        placeholder="请录入" maxlength="6"/>
              <el-input disabled class="item-width" clearable style="width: 75px" placeholder="CNY"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="保费比例%：" prop="premrate" v-if="premrateShow">
              <el-input v-model="formSearch.premrate" class="item-width" clearable size="mini" placeholder="请录入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-divider/>
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>规则列表</span>
        </div>
        <el-table
          v-loading="loading"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          :data="tableData"
          element-loading-text="拼命加载中"
          size="small"
          highlight-current-row
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column label="规则编码" prop="ruleno" align="center"/>
          <el-table-column label="出单公司" prop="companyname" align="center"/>
          <el-table-column label="产品名称" prop="riskName" align="center"/>
          <el-table-column label="结算类型" prop="accounttype" align="center" :formatter="getAccounttypeOptions">

          </el-table-column>
          <el-table-column label="服务费/人" prop="servicefee" align="center"/>
          <el-table-column label="保费比例%" prop="premrate" align="center"/>
        </el-table>
        <!--分页组件-->
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="queryData.pageNum"
          :limit.sync="queryData.pageSize"
          @pagination="getData"
        />
        <!--<div>
          <el-pagination
            :total="totalCount"
            :current-page="pageInfo.page"
            :page-size="pageInfo.pageSize"
            :page-sizes="[10, 20, 30, 40]"
            style="margin-top: 8px; text-align: right;"
            layout="sizes, prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
        </div>-->
        <el-dialog
          :visible.sync="dialogVisible"
          :modal="modalValue"
          :close-on-click-modal="false"
          title="提示"
          width="30%">
          <span>{{'是否需要更改'+this.productName  + '险种结算规则' }}</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateConfirm">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-card>
  </div>

</template>

<script>
  import {listRule,getRule,riskList} from '@/api/baseInfo/issuingCompany.js'

  export default {
    data() {
      const checkServicefee = (rules, value, callback) => {

          const regx = /^(\d+|\d+\.\d{1,2})$/
          const regx1 = /[^\d+\d-\.]/g
          if (value < 0) {
            callback(new Error("只能输入数字且保留两位小数"));
          } else if (!regx.test(value)) {
            callback(new Error("只能输入数字且保留两位小数"));
          } else {
            callback();
          }


      }
      const checkPremrate = (rules, value, callback) => {

          const regx = /^(?:0|[1-9][0-9]?|100)$/
          if (!regx.test(value)) {
            callback(new Error("保费比例只能为0～100的正整数"));
          } else {
            callback();
          }

      }
      const checkRiskcode = (rules, value, callback) => {
        if (value == null || value == '') {
          callback(new Error("产品名称不能为空"));
        } else {
          callback();
        }
      }
      return {
        accounttypeOptions: [],
        riskName: [],

        //服务费/人 显示按钮
        accounttypeShow: false,
        //保费比例% 显示按钮
        premrateShow: false,


        rules: {
          companycode: [{required: true, message: '出单公司不能为空', trigger: 'blur'}],
          riskcode: [{validator: checkRiskcode, required: true,trigger: 'blur'}],
          accounttype: [{required: true, message: '结算类型不能为空', trigger: 'change'}],
          servicefee: [{validator: checkServicefee, trigger: 'blur'}],
          premrate: [{validator: checkPremrate, trigger: 'blur'}]
        },

        formSearch: {
          companyname: undefined,
          companycode : undefined,
          riskcode : [],
          accounttype : undefined,
          servicefee: undefined,
          premrate : undefined
        },
        //初始化时根据保存信息自动带出
        //
     /*   options:
          [{
            value: '01',
            label: '按保费比例'
          },
            {
              value: '02',
              label: '按人数'
            }
          ],*/
        copyData: {
                  companycode : undefined,
                  riskcode : undefined,
                  accounttype : undefined,
                  servicefee: undefined,
                    premrate : undefined},
        tableData: [],
        queryData:{
          pageNum: 1,
          pageSize: 10,
          companycode: undefined

        },
        totalCount: 0,
        loading: true,
        dialogVisible: false,
        modalValue: false,
        riskcode: [],
        productName: []

      }
    },
    created() {
      if (this.$route.query.companycode) {
        //根据companycode
        this.formSearch.companycode = this.$route.query.companycode;
        this.formSearch.companyname=this.$route.query.companyname
        //根据companycode 查询sys_claim_base_issuingcompany_rule的内容 给tableData赋值
        this.getData();
        let query={
          companycode: this.formSearch.companycode
        }
        riskList(query).then(res=>{
          if(res!=null && res.code=='200' && res.rows.length>0){
            this.riskName=res.rows
          }
        })
      }
      this.getDicts("accounttype").then(response => {
        this.accounttypeOptions = response.data;
      });
    },

    methods: {
      cleanOther(){
        this.formSearch.accounttype='';
          this.formSearch.servicefee='';
          this.formSearch.premrate='';

      },

      getAccounttypeOptions(data) {
        return this.selectDictLabel(this.accounttypeOptions, data.accounttype)
      },


      //服务费/人 保费比例%是否显示
      changeShow(data) {
        if (data == '01') {
          this.accounttypeShow = false;
          this.premrateShow = true;
          this.formSearch.servicefee='';
        }
        if (data == '02') {
          this.premrateShow = false;
          this.accounttypeShow = true;
          this.formSearch.premrate='';
        }
      },

      getData() {
        this.queryData.companycode=this.formSearch.companycode
        //查询的接口js
        listRule(this.queryData).then(response => {
          this.tableData = response.rows;
          this.totalCount = response.total;
          this.loading = false;
        }).catch(res => {
          this.loading = false
        })
      },

      saveHandle() {
        //得到产品名称的数组 遍历   与tableData数组中对象的riskcode有   判断它的accounttype是否相同   相同则保存   不同则不保存
        this.$refs.searchForm.validate((valid) => {
          if (valid) {
            let flag = true;
            this.riskcode.length=0;
            this.productName.length=0
            for (let i = 0; i < this.formSearch.riskcode.length; i++) {

              let temp = this.tableData.filter(item => {
                return item.riskcode == this.formSearch.riskcode[i]
              });

              if (temp != null && temp.length > 0) {
                if (temp[0].accounttype!=null) {
                  flag = false;
                  this.riskcode.push(temp[0].riskcode);
                  //
                 let name= this.riskName.find(item => {
                    return item.riskCode==temp[0].riskcode
                  })

                  this.productName.push(name.riskName)
                }
              }
            }

            if (flag) {
              this.copyData.riskcode=this.formSearch.riskcode.join(',');
              this.copyData.companycode=this.formSearch.companycode;
              this.copyData.accounttype=this.formSearch.accounttype;
              if(this.formSearch.accounttype == '01') {
                this.copyData.premrate = this.formSearch.premrate;
                this.copyData.servicefee = '';
              }
              else {
                this.copyData.servicefee = this.formSearch.servicefee;
                this.copyData.premrate = '';
              }
              //this.formSearch.riskcode1=this.formSearch.riskcode.join(',')
              //调接口
              getRule(this.copyData).then(res => {
                if (res!=null && res.code === 200) {
                  this.$message({
                    message: '保存成功！',
                    type: 'success',
                    center: true,
                    showClose: true
                  })
                  //根据companycode 查询sys_claim_base_issuingcompany_rule的内容 给tableData赋值
                  this.getData();
                } else {
                  this.$message({
                    message: '保存失败！',
                    type: 'error',
                    center: true,
                    showClose: true
                  })
                }
              })
            }
            else {
              this.dialogVisible = true
            }

          }
        })
      },
      updateConfirm() {
        this.dialogVisible = false;
        this.copyData.riskcode=this.formSearch.riskcode.join(',');
        this.copyData.companycode=this.formSearch.companycode;
        this.copyData.accounttype=this.formSearch.accounttype;
        if(this.formSearch.accounttype == '01') {
          this.copyData.premrate = this.formSearch.premrate;
          this.copyData.servicefee = '';
        }
        else {
          this.copyData.servicefee = this.formSearch.servicefee;
          this.copyData.premrate = '';
        }
        //调接口
        getRule(this.copyData).then(res => {
          if (res!=null && res.code === 200) {
            this.$message({
              message: '保存成功！',
              type: 'success',
              center: true,
              showClose: true
            })
            //根据companycode 查询sys_claim_base_issuingcompany_rule的内容 给tableData赋值
            this.getData();
          } else {
            this.$message({
              message: '保存失败！',
              type: 'error',
              center: true,
              showClose: true
            })
          }
        })
      },
      goBack() {
        this.$router.go(-1)
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

  /deep/ .el-select__tags {
    width: 100%;
    display: inline-block;
    max-width: 200px;
    overflow: hidden;
    white-space: nowrap;
  }
</style>
