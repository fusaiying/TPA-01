<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>产品福利规则</span>
        <span style="float: right;">
          <el-button type="primary" size="mini" @click="goBack">返回</el-button>
        </span>
      </div>
      <div style="margin-top: 0px">
        <span style="size: 2px">产品详情</span>
      </div>
      <el-divider/>
      <el-row v-loading="dutyLoading" style="margin: 0 40px;padding-bottom: 20px">
        <el-col :span="8">
          <span class="info_span to_right">产品编码：</span><span class="info_span">{{ insuranceInfo.riskCode }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品名称：</span><span class="info_span">{{ insuranceInfo.riskName }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品性质：</span><span
          class="info_span">{{ insuranceInfo.riskNature }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品类别：</span><span class="info_span">{{ insuranceInfo.riskType }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">产品分类：</span><span class="info_span">{{ insuranceInfo.riskClass }}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">等待期：</span><span class="info_span" v-if="insuranceInfo.waitingPeriod">{{ insuranceInfo.waitingPeriod +'天'}}</span>
        </el-col>
        <el-col :span="8">
          <span class="info_span to_right">出单公司：</span><span class="info_span">{{ insuranceInfo.company }}</span>
        </el-col>
      </el-row>
      <div class="tab-title">
        <span>计划信息</span>
      </div>
      <el-table
        v-loading="dutyLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="pagingData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        @expand-change="getMinData"
        style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-table :data="scope.row.minData"
                      :header-cell-style="{color:'black',background:'#f8f8ff'}"
                      highlight-current-row
                      size="small"
                      v-loading="minLoading"
                      tooltip-effect="dark"
                      style="width: 100%;">
              <el-table-column align="center" width="140" prop="dutyCode" label="责任编码" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyName" label="责任名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyDetailCode" label="责任明细编码" show-overflow-tooltip/>
              <el-table-column align="center" prop="dutyDetailName" label="责任明细名称" show-overflow-tooltip/>
              <el-table-column align="center" prop="applyReason" label="申请原因" show-overflow-tooltip/>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column align="center" width="140" prop="planCode" label="保障计划编码" show-overflow-tooltip/>
        <el-table-column align="center" prop="planName" label="保障计划名称" show-overflow-tooltip/>
        <el-table-column align="center" prop="ensureRegion" label="保障区域" show-overflow-tooltip/>
        <el-table-column align="center" prop="planDescription" label="计划描述" show-overflow-tooltip/>
      </el-table>
    </el-card>
    <el-card class="card" style="margin-top: 10px;">
      <div style="margin-top: 0px">
        <span style="size: 2px">福利规则</span>
      </div>
      <el-divider/>
      <el-table
        v-loading="ruleLoading"
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="ruleTableData"
        size="small"
        highlight-current-row
        tooltip-effect="dark"
        style="width: 100%;">
        <el-table-column align="center" width="140" label="福利对象" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{getPlan(scope.row,scope.$index)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="规则信息" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{getRuleInfo(scope.row,scope.$index)}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="规则条件" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{getConditional(scope.row,scope.$index,'1')}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="规则描述" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{scope.row.ruleDescription}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="createBy" label="涵盖区域" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{getConditional(scope.row,scope.$index,'2')}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="createBy" label="医院属性" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{getConditional(scope.row,scope.$index,'3')}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="createBy" label="特殊医院" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{getConditional(scope.row,scope.$index,'4')}}</span>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="ruleTotalCount>0"
        :total="ruleTotalCount"
        :page.sync="rulPageInfo.pageNum"
        :limit.sync="rulPageInfo.pageSize"
        @pagination="listRule"
      />
    </el-card>
    <el-card v-if="node === 'ruleReview'" class="box-card" style="margin-top: 10px;">
      <div style="margin-top: 0px">
        <span style="size: 2px">审核结论</span>
        <span style="float: right;">
       <el-button type="primary" size="mini"
                  @click="reviewHandle">审核完毕</el-button>
        </span>
      </div>
      <el-divider/>
      <el-form ref="reviewForm" :model="reviewForm" :rules="reviewRules" label-width="100px" class="demo-ruleForm" size="mini">
        <el-form-item

          label="审核结论"
          prop="conclusion">
          <el-select v-model="reviewForm.conclusion" placeholder="请选择审核结论">
            <el-option v-for="item in approvalconclusionOptions" :key="item.dictValue" :label="item.dictLabel"
                       :value="item.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item
          label="结论说明"
          prop="explanation">
          <span>
            <el-input v-model="reviewForm.explanation" maxlength="1000" class="small-input" show-word-limit
                      type="textarea"/>
          </span>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
  let that
  import {getProduct,listPlan,listDetail,listRule,submitReview} from '@/api/insuranceRules/ruleDefin'
  let dictss = [{dictType: 'waiting_period'}, {dictType: 'isShare'}, {dictType: 'sex'}, {dictType: 'ruleelement'},
    {dictType: 'special_cumulativemode'}, {dictType: 'intervalUnit'}, {dictType: 'unitofelement'},{dictType: 'approvalconclusion'},]
  export default {
    filters: {
      changeDate: function (value) {
        return moment(value).format('YYYY-MM-DD')
      },
    },
    data() {
      const checkExplanation = (rule, value, callback) => {
        if (this.reviewForm.conclusion==='02' && (this.reviewForm.explanation==null || this.reviewForm.explanation==='')){
          if (!value){
            callback(new Error("复核结论退回，结论说明必录！"));
          }else {
            callback()
          }
        }else {
          callback()
        }
      }
      return {
        minLoading: false,
        loading:false,
        insuranceInfo: {
          riskCode:undefined,
          riskStatus:undefined,
          riskName:undefined,
          riskNature:undefined,
          riskType:undefined,
          riskClass:undefined,
          companyName:undefined,
        },
        pagingData:[],
        ruleTableData: [],
        resTotalCount: 0,
        ruleTotalCount: 0,
        ruleLoading: false,
        rulPageInfo: {
          pageNum: 1,
          pageSize: 10
        },
        reviewForm: {
          conclusion:undefined,
          explanation:undefined,
        },
        reviewRules:{
          conclusion:{ required: true, message: '审核结论不能为空',trigger: 'change'},
          explanation:[{validator: checkExplanation, trigger: 'blur'}],
        },
        node: '',
        dutyLoading: false,
        approvalconclusionOptions:[],
        waiting_periodOptions: [],
        unitofelementOptions: [],
        isShareOptions: [],
        ruleelementOptions: [],
        special_cumulativemodeOptions: [],
        sexOptions: [],
        dictList: [],
        intervalUnitOptions: [],
        rulePagingDatasrulePagingDatas: []
      }
    },

    async mounted() {
      await this.getDictsList(dictss).then(response => {
        this.dictList = response.data
      })
      this.approvalconclusionOptions = this.dictList.find(item => {
        return item.dictType === 'approvalconclusion'
      }).dictDate
      this.waiting_periodOptions = this.dictList.find(item => {
        return item.dictType === 'waiting_period'
      }).dictDate
      this.isShareOptions = this.dictList.find(item => {
        return item.dictType === 'isShare'
      }).dictDate
      this.sexOptions = this.dictList.find(item => {
        return item.dictType === 'sex'
      }).dictDate
      this.ruleelementOptions = this.dictList.find(item => {
        return item.dictType === 'ruleelement'
      }).dictDate
      this.special_cumulativemodeOptions = this.dictList.find(item => {
        return item.dictType === 'special_cumulativemode'
      }).dictDate
      this.intervalUnitOptions = this.dictList.find(item => {
        return item.dictType === 'intervalUnit'
      }).dictDate
      this.unitofelementOptions = this.dictList.find(item => {
        return item.dictType === 'unitofelement'
      }).dictDate
      if (this.$route.query) {
        this.node = this.$route.query.node
        this.getProduct()
        this.listPlan()
        this.listRule()
      }
    },
    methods: {
      // 查询产品详情
      getProduct(){
        getProduct(this.$route.query.riskCode).then(res=>{
          if (res!=null && res.code===200){
            this.insuranceInfo=res.data
          }
        }).catch(res => {
        })
      },
      //查询计划信息
      listPlan(){
        let query ={
          riskCode:this.$route.query.riskCode
        }
        listPlan(query).then(res=>{
          if (res!=null && res.code===200){
            this.pagingData=res.rows
          }
        }).catch(res => {
        })
      },
      //查询福利规则
      listRule() {
        let querys = {
          pageNum: this.rulPageInfo.pageNum,
          pageSize: this.rulPageInfo.pageSize,
          riskCode: this.$route.query.riskCode
        }
        listRule(querys).then(res => {
          if (res != null && res.code === 200) {
            this.ruleTableData = res.rows
            this.ruleTotalCount = res.total
          }
        }).catch(res => {
        })
      },
      reviewHandle() {
        this.$refs.reviewForm.validate((valid) => {
          if (valid) {
            let data = {
              riskCode: this.insuranceInfo.riskCode,
              riskStatus: '',
              examineConclusion:this.reviewForm.conclusion,
              conclusionExplanation:this.reviewForm.explanation
            }
            if (this.reviewForm.conclusion==='01'){
              data.riskStatus='04'
            }else if (this.reviewForm.conclusion==='02'){
              data.riskStatus='02'
            }

            submitReview(data).then(res => {
              if (res!=null && res.code===200){
                this.$message({
                  message: '审核完毕！',
                  type: 'success',
                  center: true,
                  showClose: true
                })
                this.$router.go(-1)
              }
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '审核失败！'
              })
            })
          }
        })

        if (this.reviewForm.conclusion==null || this.reviewForm.conclusion===''){
          return this.$message.warning(
            "请选择审核结论！"
          )
        }
        if (this.reviewForm.conclusion==='02' && (this.reviewForm.explanation==='' || this.reviewForm.explanation==null)){
          return this.$message.warning(
            "请录入结论说明！"
          )
        }
      },
      goBack() {
        this.$router.go(-1)
      },
      getMinData(row, expandedRows) {
        this.minLoading = true
        //判断只有展开是做请求
        let data = {
          planCode: row.planCode
        }
        if (expandedRows.length > 0) {
          listDetail(data).then(res => {
            if (res != null && res.code === 200) {
              this.pagingData.forEach((temp, index) => {
                if (temp.planCode === row.planCode) {
                  this.pagingData[index].minData = res.rows
                }
              })
            }
          }).finally(() => {
            this.minLoading = false
          })
        }
      }, getPlan(row, index) {
        if (row.relationInfoList.length > 0) {
          let data = row.relationInfoList[0]
          let feeitem = ''
          for (let i = 0; i < row.relationInfoList.length; i++) {
            if (i === 0) {
              feeitem = row.relationInfoList[i].feeitemName
            } else {
              feeitem = feeitem + '|' + row.relationInfoList[i].feeitemName
            }
          }
          if ((feeitem==null || data.planName==='') && (data.dutyDetailName!==null && data.dutyDetailName!=='') ){
            return data.planName + '>' + data.dutyName + ':' + data.dutyDetailName
          }else if ((data.dutyDetailName==null || data.dutyDetailName==='') && (data.dutyName!==null && data.dutyName!=='')){
            return data.planName + '>' + data.dutyName
          }else if((data.dutyName==null || data.dutyName==='') && (data.planName!==null && data.planName!=='')) {
            return data.planName
          }else {
            return data.planName + '>' + data.dutyName + ':' + data.dutyDetailName + '>' + feeitem
          }

        }
      },
      getRuleInfo(row, index) {
        return this.selectDictLabel(this.ruleelementOptions, row.ruleElement)+'|' + row.elementValue+'|' + this.selectDictLabel(this.unitofelementOptions, row.elementUnit)
      },
      getConditional(row, index, status) {
        let data = row.constraintList
        let constraint = ''
        let area = ''
        let hospital = ''
        let hospitalProperty = ''
        let hospitalGrade = ''
        let departmentClass = ''
        let hospitalClass = ''
        let network = ''
        let isPay=''
        let specialHospitals=''
        data.forEach(item => {
          //规则条件
          if (item.constraintType === '10' || item.constraintType === '11' || item.constraintType === '12') {
            if (item.constraintType === '10') {
              constraint = item.value1 + '-' + item.value2 + this.selectDictLabel(this.intervalUnitOptions, item.constraintUnit)
            }
            if (item.constraintType === '11') {
              if (constraint !== '') {
                constraint = constraint + '|' + this.selectDictLabel(this.sexOptions, item.value1)
              } else {
                constraint = constraint + this.selectDictLabel(this.sexOptions, item.value1)
              }
            }
            if (item.constraintType === '12') {
              if (constraint !== '') {
                constraint = constraint + '|' + this.selectDictLabel(this.waiting_periodOptions, item.value1)
              } else {
                constraint = constraint + this.selectDictLabel(this.waiting_periodOptions, item.value1)
              }
            }
          }
          //涵盖区域
          if (item.constraintType === '00') {
            if (area === '') {
              area = item.value2
            } else {
              area = area + '|' + item.value2
            }
          }
          //医院属性
          if (item.constraintType === '31' || item.constraintType === '32' || item.constraintType === '33' || item.constraintType === '34' || item.constraintType === '35') {
            if (item.constraintType === '31') {
              if (hospitalProperty === '') {
                hospitalProperty = '医院属性：' + item.value2
              } else {
                hospitalProperty = hospitalProperty + ',' + item.value2
              }
            }
            if (item.constraintType === '32') {
              if (hospitalGrade === '') {
                hospitalGrade = '医院级别：' + item.value2
              } else {
                hospitalGrade = hospitalGrade + ',' + item.value2
              }
            }
            if (item.constraintType === '33') {
              if (departmentClass === '') {
                departmentClass = '部门类别：' + item.value2
              } else {
                departmentClass = departmentClass + ',' + item.value2
              }
            }
            if (item.constraintType === '34') {
              if (hospitalClass === '') {
                hospitalClass = '医院类别：' + item.value2
              } else {
                hospitalClass = hospitalClass + ',' + item.value2
              }
            }
            if (item.constraintType === '35') {
              if (network === '') {
                network = '网络内：' + item.value2
              } else {
                network = network + ',' + item.value2
              }
            }
            if (item.constraintType === '36') {
              if (isPay === '') {
                isPay = '是否直结：' + item.value2
              } else {
                isPay = isPay + ',' + item.value2
              }
            }
          }
          //特殊医院
          if (item.constraintType === '30'){
            if (specialHospitals!==''){
              specialHospitals = specialHospitals + '|' + item.value2
            }else {
              specialHospitals = item.value2
            }
          }
        })

        if (hospitalProperty !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + hospitalProperty
          } else {
            hospital = hospitalProperty
          }

        }
        if (hospitalGrade !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + hospitalGrade
          } else {
            hospital = hospitalGrade
          }

        }
        if (departmentClass !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + departmentClass
          } else {
            hospital = departmentClass
          }
        }
        if (hospitalClass !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + hospitalClass
          } else {
            hospital = hospitalClass
          }
        }
        if (network !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + network
          } else {
            hospital = network
          }
        }
        if (isPay !== '') {
          if (hospital !== '') {
            hospital = hospital + '|' + isPay
          } else {
            hospital = isPay
          }
        }


        if (status === '1') {
          return constraint
        } else if (status === '2') {
          return area
        } else if (status === '3') {
          return hospital
        } else if (status === '4') {
          return specialHospitals //特殊医院
        }
      },

    }
  }
</script>

<style scoped>
  .small-input {
    width: 80%;
  }

  .to_right {
    width: 100px;
    text-align: right;
  }
  .el-table ::v-deep .el-table__expanded-cell {
    padding: 10px;
  }
</style>
