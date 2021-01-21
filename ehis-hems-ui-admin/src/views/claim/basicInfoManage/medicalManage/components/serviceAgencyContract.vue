<template>
<!--  <div class="app-container">
    <el-dialog
      :visible.sync="serviceDialogVisable"
      :append-to-body="true"
      :close-on-click-modal="true"
      show-close
      :modal="false"
      title="合约信息"
      width="80%">
    <el-card class="box-card" style="margin-top: 10px;">
      <div class="el-radio">
        <el-form ref="RadioForm" v-if="RadioFormShow" :model="RadioForm" style="margin-top:10px" label-width="130px">
        <el-form-item label="合约信息：" size="mini" class='form-label'>
          <div  >
            <el-radio-group v-model="radioV" @change="changeRadio" disabled>
            <el-radio class="radio" v-model="addType"  label="0" >供应商</el-radio>
            <el-radio class="radio" v-model="addType"  label="1" >服务机构</el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
        </el-form>
      </div>

      <el-form ref="providerForm" :model="providerForm"   style="padding-bottom: 30px;margin-top:20px" label-width="140px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="服务机构名称：" prop="providerCode"> &lt;!&ndash; providerInfoSelects &ndash;&gt;
              <el-select  :rules="proivderRules.providerCode"  :disabled="isShow" v-model="providerForm.providerCode" class="item-width" placeholder="请选择"
                         @change="typeServerChange">
                <el-option
                  v-for="dict in providerInfoSelects"
                  :key="dict.providerCode"
                  :label="dict.chname1+' - '+dict.enname1"
                  :value="dict.providerCode"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约编码：  "prop="contractNo">
              <el-input  v-model="providerForm.contractNo" class="item-width"  :disabled="provideConView" clearable size="mini"
                        placeholder="请输入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态：" prop="status">
              <el-select v-model="providerForm.status" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in statusSlects" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="23">
            <el-form-item label="合约名称：" prop="contractName">
              <el-input :disabled="isShow" v-model="providerForm.contractName"  clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约甲方：" prop="contractPartyA">
              <el-input :disabled="isShow" v-model="providerForm.contractPartyA" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约乙方：" prop="contractPartyB">
              <el-input :disabled="isShow" v-model="providerForm.contractPartyB" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约丙方方：" prop="connectedContract">
              <el-input :disabled="isShow" v-model="providerForm.connectedContract" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合约类型：" prop="contractType">
              <el-select v-model="providerForm.contractType" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in contractTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="关联合约：" prop="connectedContract">
              <el-select v-model="providerForm.connectedContract" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in contractLimitTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="押金金额：" prop="deposit">
              <el-input :disabled="isShow" v-model="providerForm.deposit" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="17">
            <el-form-item label="合约有效期：" prop="proeffectiveSDate">
              <el-date-picker
                v-model="providerForm.effectiveSDate"
                class="item-width" size="mini"
                value-format="yyyy-MM-dd"
                type="daterange"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>

                <el-checkbox-group v-model="providerForm.ads">
                  <el-checkbox label="01">自动续约</el-checkbox>
                </el-checkbox-group>

            </el-form-item>
          </el-col>

          <el-col :span="17">
            <el-form-item label="合约控费：" prop="contractControlFlagt">
              <el-checkbox-group v-model="providerForm.contractControlFlagt">
                <el-checkbox label="01">折扣</el-checkbox>
                <el-checkbox label="02">次均</el-checkbox>
              </el-checkbox-group>

            </el-form-item>
          </el-col>



          <el-col :span="16">
            <el-form-item label="诊疗费折扣：" prop="treatmentDiscount">
              <el-input :disabled="isShow" v-model="providerForm.treatmentDiscount" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="次均控费：" prop="averageCost">
              <el-input :disabled="isShow" v-model="providerForm.averageCost" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="床位费折扣：" prop="bedDiscount">
              <el-input :disabled="isShow" v-model="providerForm.bedDiscount" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="次均控费类型：" prop="type">
              <el-select v-model="providerForm.type" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in contractLimitTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="护理费折扣：" prop="allowance">
              <el-input :disabled="isShow" v-model="providerForm.allowance" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="就诊次数：" prop="advicenum">
              <el-input :disabled="isShow" v-model="providerForm.advicenum" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="医药费折扣：" prop="costs">
              <el-input :disabled="isShow" v-model="providerForm.costs" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="次均控费除外项目：" prop="averageCostExcept">
              <el-input :disabled="isShow" v-model="providerForm.averageCostExcept" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="折扣信息：" prop="discountinfo">
              <el-input :disabled="isShow" v-model="providerForm.discountinfo" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="特殊费折扣信息：" prop="specialDiscount">
              <el-input :disabled="isShow" v-model="providerForm.specialDiscount" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="折扣除外项目：" prop="project">
              <el-input :disabled="isShow" v-model="providerForm.project" class="item-width" clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="合约单位：" prop="cooperativeUnit">
              <el-select v-model="providerForm.cooperativeUnit" class="item-width" size="mini" placeholder="请选择">
                <el-option v-for="option in contractTypes" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue" />
              </el-select>
            </el-form-item>
          </el-col>

          &lt;!&ndash; 01=门诊，02=住院，03=体检，04=牙科，05=生育，06=眼科，07=疫苗 &ndash;&gt;
          <el-col :span="16">
            <el-form-item label="直结类型：" prop="straightT">
              <el-checkbox-group v-model="providerForm.straightT">
                <el-checkbox label="01">门诊</el-checkbox>
                <el-checkbox label="02">住院</el-checkbox>
                <el-checkbox label="03">体检</el-checkbox>
                <el-checkbox label="04">牙科</el-checkbox>
                <el-checkbox label="05">生育</el-checkbox>
                <el-checkbox label="06">眼科</el-checkbox>
                <el-checkbox label="07">疫苗</el-checkbox>
              </el-checkbox-group>

            </el-form-item>
          </el-col>

         &lt;!&ndash; <el-col :span="24">
            <el-form-item label="网络医院直结类型：" prop="networkHospitalType">
              <el-checkbox-group v-model="providerForm.networkHospitalType">
                <el-checkbox label="01">门诊</el-checkbox>
                <el-checkbox label="02">住院</el-checkbox>
                <el-checkbox label="03">体检</el-checkbox>
                <el-checkbox label="04">牙科</el-checkbox>
                <el-checkbox label="05">生育</el-checkbox>
                <el-checkbox label="06">眼科</el-checkbox>
                <el-checkbox label="07">疫苗</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>&ndash;&gt;

          <el-col :span="8">
            <el-form-item label="合约终止日期：" prop="endDate">
              <el-date-picker
                v-model="providerForm.endDate"
                class="item-width" size="mini"
                value-format="yyyy-MM-dd"
                type="date"
                range-separator="-"
                placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="16">
            <el-form-item label="合约终止原因：" prop="reason">
              <el-input :disabled="isShow" v-model="providerForm.reason"  clearable size="mini"
                        placeholder="请录入"/>
            </el-form-item>
          </el-col>

        <el-col :span="8">
          <el-form-item label="联系人：" prop="relp">
            <el-input :disabled="isShow" v-model="providerForm.relp" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="手机：" prop="phone">
            <el-input :disabled="isShow" v-model="providerForm.phone" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="盒脊编号：" prop="boxRidgeCode">
            <el-input :disabled="isShow" v-model="providerForm.boxRidgeCode" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="办公电话：" prop="tel">
            <el-input :disabled="isShow" v-model="providerForm.tel" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="电子邮件：" prop="email">
            <el-input :disabled="isShow" v-model="providerForm.email" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="最后维护人：" prop="exPer">
            <el-input :disabled="isShow" v-model="providerForm.exPer" class="item-width" clearable size="mini"
                      placeholder="请录入"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="最后维护时间：" prop="exEndate">
            <el-date-picker
              v-model="providerForm.exEndate"
              class="item-width" size="mini"
              value-format="yyyy-MM-dd"
              type="date"
              range-separator="-"
              placeholder="最后维护时间">
            </el-date-picker>
          </el-form-item>
        </el-col>

        </el-row>
        &lt;!&ndash;保存 关闭 start&ndash;&gt;
        <el-row >
          <el-col :span="6" :offset="20">
            <el-button
              type="primary"
              size="mini"
              @click="saveBaseInfo"
            >保存
            </el-button>
            <el-button
              type="primary"
              size="mini"
              @click="goBack"
            >关闭
            </el-button>
          </el-col>
        </el-row>
        &lt;!&ndash;保存 关闭 end&ndash;&gt;

      </el-form>

      <el-divider/>


      &lt;!&ndash;附件信息 START&ndash;&gt;
      <div>
        <div style="line-height: 50px; margin-bottom: 20px; border-bottom: 1px solid #e6ebf5;color: #303133;">
          <span>附件信息</span>
         &lt;!&ndash; <el-button  style="float: right; margin-top: 10px;" type="primary" size="mini"
           / :action="url + negotiationno" /action="https://jsonplaceholder.typicode.com/posts/"          @click="uploadFile">添加附件
          </el-button>&ndash;&gt;
          <el-button  style="float: right; margin-top: 10px;" type="primary" size="mini"
                      @click="diagShow">点击上传
          </el-button>
&lt;!&ndash;          <el-upload&ndash;&gt;
&lt;!&ndash;            ref="my-upload"&ndash;&gt;
&lt;!&ndash;            class="upload-demo"&ndash;&gt;
&lt;!&ndash;            action="https://jsonplaceholder.typicode.com/posts/"&ndash;&gt;
&lt;!&ndash;            accept=".jpg,.png"&ndash;&gt;
&lt;!&ndash;            :before-remove="beforeRemove"&ndash;&gt;
&lt;!&ndash;            :before-upload="beforeUpload"&ndash;&gt;
&lt;!&ndash;            :on-success="success"&ndash;&gt;
&lt;!&ndash;            list-type="text"&ndash;&gt;
&lt;!&ndash;            :on-error="handleError"&ndash;&gt;
&lt;!&ndash;            :show-file-list="false"&ndash;&gt;
&lt;!&ndash;            multiple&ndash;&gt;
&lt;!&ndash;            trigger&ndash;&gt;
&lt;!&ndash;            style="float: right;" size="mini" >&ndash;&gt;
&lt;!&ndash;            <el-button style="float: right;" size="mini" type="primary">点击上传</el-button>&ndash;&gt;
&lt;!&ndash;          </el-upload>&ndash;&gt;
&lt;!&ndash;          <el-image-viewer&ndash;&gt;
&lt;!&ndash;            v-if="showViewer"&ndash;&gt;
&lt;!&ndash;            disabled&ndash;&gt;
&lt;!&ndash;            :on-close.stop="closeViewer"&ndash;&gt;
&lt;!&ndash;            :url-list="file"/>&ndash;&gt;
        </div>
        <el-table
          :data="fileTableData"
          size="mini"
          tooltip-effect="dark"
          class="receive_table"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
        >
          <el-table-column prop="annexName" label="附件名称"  align="center" show-overflow-tooltip />
          <el-table-column prop="annexType" label="附件类型"  align="center" show-overflow-tooltip />
          &lt;!&ndash; <el-table-column prop="supplierName" label="供应商名称" align="center" show-overflow-tooltip />&ndash;&gt;
          <el-table-column prop="uploadMan" label="上传人"  align="center" show-overflow-tooltip />
          <el-table-column prop="uploadTime" label="上传时间" align="center" show-overflow-tooltip />
          <el-table-column prop="remak" label="备注" align="center" show-overflow-tooltip />
          <el-table-column label="操作" align="center" style="padding-top: 0px;">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="deleteFile(scope.row)">删除</el-button>
              <el-button size="mini" type="text" @click="downloadFile(scope.row)">下载</el-button>

            </template>
          </el-table-column>
        </el-table>
      </div>
      &lt;!&ndash;附件信息 END&ndash;&gt;

      &lt;!&ndash; 附件上传弹框 start &ndash;&gt;
      <el-dialog
        :visible.sync="dialogVisible"
        :dialog-visible="dialogVisible"
        :append-to-body="true"
        :before-close="handleClose"
        :close-on-click-modal="false"
        title="添加附件"
        width="40%"
        @open="openFun">
      &lt;!&ndash;  <el-card style="border: 0;box-shadow: none; margin-top: -20px;">&ndash;&gt;
         &lt;!&ndash; <span slot="footer" class="dialog-footer">
            <el-button   @click="dialogVisible = false">取 消</el-button>
          </span>&ndash;&gt;
          <el-form ref="fileForm" :model="fileForm" style="border:0" label-width="110px" label-position="right" size="mini">

            <el-row>
              <el-col :span="8">
                <el-form-item label="附件类型：" prop="fileType">
                  <el-select v-model="fileForm.fileType" class="item-width" size="mini" placeholder="请选择">
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="6">
                <el-form-item label="附件文件：" prop="fileName">
                  <el-input v-model="fileForm.fileName" disabled class="item-width" size="mini" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-upload
                  ref="my-upload"
                  class="upload-demo"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  accept=".jpg,.png"
                  :before-remove="beforeRemove"
                  :before-upload="beforeUpload"
                  :on-success="success"
                  list-type="text"
                  :on-error="handleError"
                  :show-file-list="false"
                  multiple
                  trigger
                  style="float: right;" size="mini" >
                  <el-button style="float: right;" size="mini" type="primary">上传文件</el-button>
                </el-upload>
                <el-image-viewer
                  v-if="showViewer"
                  disabled
                  :on-close.stop="closeViewer"
                  :url-list="file"/>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="备注：" prop="remark">
                  <el-input :disabled="isShow" style="width: 330px;" col="2" type="textarea" row="4" maxlength="1000"
                            v-model="fileForm.fileRemark" class="item-width" clearable size="mini"
                            placeholder=""/>
                </el-form-item>
              </el-col>
            </el-row>
              <el-row>
                &lt;!&ndash;<el-col :span="8">
                  <el-upload
                    ref="my-upload"
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    accept=".jpg,.png"
                    :before-remove="beforeRemove"
                    :before-upload="beforeUpload"
                    :on-success="success"
                    list-type="text"
                    :on-error="handleError"
                    :show-file-list="false"
                    multiple
                    trigger
                    style="float: right;" size="mini" >
                    <el-button style="float: right;" size="mini" type="primary">选择附件</el-button>
                  </el-upload>
                  <el-image-viewer
                    v-if="showViewer"
                    disabled
                    :on-close.stop="closeViewer"
                    :url-list="file"/>
                </el-col>&ndash;&gt;
              </el-row>

          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="saveFileInfo" type="primary">确认</el-button>
           <el-button   @click="dialogVisible = false">取 消</el-button>
          </span>
         &lt;!&ndash; <div style="float: right;padding: 10px">
            <el-button type="primary" style="width: 80px" size="mini" @click="closeDialog">返回</el-button>
          </div>&ndash;&gt;
       &lt;!&ndash; </el-card>&ndash;&gt;
      </el-dialog>
      &lt;!&ndash; 附件上传弹框 end &ndash;&gt;

    </el-card>
    </el-dialog>
  </div>-->
</template>

<script>
  import {
    getSupplierContractList ,
    addSupplierContract ,
    updateSupplierContract,
    getSupplierContractDetail,
    getContractServerList ,
    getFileList ,
    deleteContractServer ,
    addContractServer,
    getAllBaseSupplierInfo,
    getAllBaseProviderInfo

  } from '@/api/contractManage/contractManagement'
  export default {
   /* components: {
    },*/
    props: {
      providerCode: String,
      dialogVisable:Boolean
    },
    watch: {
      dialogVisable: function (){
        this.serviceDialogVisable=this.dialogVisable
      }
    },
    data() {
      const checkBatchtotal = (rule, value, callback) => {
        // const regx = /^(\d+|\d+\.\d{1,2})$/
        // if (!value) {
        //   callback(new Error("批次总金额必填"));
        // } else {
        //   if (value < 0) {
        //     callback(new Error("批次总名额不为正数，请检查"));
        //   } else if (!regx.test(value)) {
        //     callback(new Error("批次总名额最多保留两位小数，请检查"));
        //   } else {
        //     callback();
        //   }
        // }
      }
      const checkNum = (rule, value, callback) => {
        const regx = /^[1-9]{1}[\d]*$/
        if (!value) {
          callback(new Error("协商天数额必填"));
        } else {
          if (!regx.test(value)) {
            callback(new Error("协商天数不为正整数，请检查"));
          } else {
            callback();
          }
        }
      }
      return {
        serviceDialogVisable: false,
        provideConView : false,
        baseFomrmSub : false,
        pageOpe :'',
        queryContractNo :'',
        contractCategorys:[],
        contractLimitTypes: [],
        contractTypes:[],
        statusSlects:[],
        showViewer:false,
        isShow: false,
        effectiveSDate: '',
        pendingTotalNum: 0,
        fileTotalNum: 0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        radioV :'1',
        addType:'1',
        pendingPageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },
        fileTablePageInfo: {
          currentPage: 1,
          pageSize: 10,
          pageSizes:[5,10,20,30,50,100]
        },
        rules: {
          servcomNo: {trigger: ['change'], required: true, message: '供应商必填'},
          contractName: { required: true, message: '合约名称必填'},
          contractType: {trigger: [ 'change'], required: true, message: '合约类型必填'},
          contracttermType: {trigger: ['change'], required: true, message: '合约期限类型必填'},
          contractsort: {trigger: 'change', required: true, message: '合同分类必填'},
          contractadvance: [{validator: checkNum, required: true, trigger: 'blur'}],
          status: {trigger: 'change', required: true, message: '状态必填'},
          effectiveSDate: { required: true, message: '合约有效期'},

        },
        proivderRules : {
          providerCode: {trigger: ['change','blur'], required: true, message: '服务机构必填'},
        },
        serverInfoRules: {
           serviceCode: [{required: true, message: '服务项名称', trigger: 'blur'}],
           supplierCode: [{required: true, message: '供应商名称', trigger: 'blur'}],
           minPrice: [{required: true, message: '最小价格', trigger: 'blur'}],
           maxPrice: [{required: true, message: '最大价格', trigger: 'blur'}],
           limitnum: [{required: true, message: '日限次数', trigger: 'blur'}],
           settleType: [{required: true, message: '结算方式', trigger: 'blur'}],
           settleCurrency: [{required: true, message: '结算币种', trigger: 'blur'}],
        },
        recordRule: {
          servcomNo: {trigger: ['change'], required: true, message: '供应商必填'},
          contractName: { required: true, message: '合约名称必填'},
          contractType: {trigger: [ 'change'], required: true, message: '合约类型必填'},
          contracttermType: {trigger: [ 'change'], required: true, message: '合约期限类型必填'},
          contractsort: {trigger: 'change', required: true, message: '合同分类必填'},
          contractadvance: [{validator: checkNum, required: true, trigger: 'blur'}],
          status: {trigger: 'change', required: true, message: '状态必填'},
          effectiveSDate: { required: true, message: '合约有效期'},

        },
        dialogVisible: false,
        dateRange:'',
        afterTableTotal: 0,
        page: 1,
        total: 0,
        finishTotal: 0,
        pageSize: 10,
        finishPage: 1,
        finishPageSize: 10,
        pageSizes: [5, 10, 20, 30, 50, 100],
        activeName: '01',
        changeSerchData: {},
        suTableData: [],
        afterTable: [],
        tableData: [],
        modifyData:[],
        prevValue:{},
        pendingTableData: [],
        fileTableData:[],
        searchForm: {
          contractNo:'',
          servcomNo: '',
          contractName:'',
          contractType:'',
          contracttermType:'',
          cvaliDate:'',
          expiryDate:'',
          contractadvance:'',
          status:'',
          remark:'',
          contractsort:'',
          effectiveSDate:''
        },
        providerForm :{
          networkHospitalType: [],
          straightT:[],
          ads:'',
          contractControlFlagt:[],
          //straight:'',
          providerCode:'',
          contractNo:'',
          status:'',
          contractName:'',
          contractPartyA:'',
          contractPartyB:'',
          connectedContract:'',
          contractType:'',
          deposit:'',
          effectiveSDate:'',
          treatmentDiscount:'',
          averageCost:'',
          bedDiscount:'',
          type:'',
          allowance:'',
          advicenum:'',
          costs:'',
          averageCostExcept:'',
          discountinfo:'',
          specialDiscount:'',
          project:'',
          cooperativeUnit:'',
          endDate:'',
          reason:'',
          relp:'',
          phone:'',
          boxRidgeCode:'',
          tel:'',
          email:'',
          exPer:'',
          exEndate:'',
        },
        RadioForm:{

        },
        fileUpload:false,
        fileForm:{
          fileType :'',
          fileName:'',
          fileRemark:''
        },
        serverForm :{
          serverInfo:[]
        },
        formTab : true,
        RadioFormShow :true,
        supplierInfoSelects : '',
        providerInfoSelects : ''
      }
    },
    created() {
    },
     mounted() {
      this.getDicts("contract_limit_type").then(response => {
        this.contractLimitTypes = response.data;
      });
      this.getDicts("contract_type").then(response => {
        this.contractTypes = response.data;
      });
      this.getDicts("contract_category").then(response => {
        this.contractCategorys = response.data;
      });
      this.getDicts("status").then(response => {
        this.statusSlects = response.data;
      });

      //获取供应商下拉
      this.allBaseSupplierInfo();
      //获取服务机构下拉
      this.allBaseProviderInfo();

      //更新操作,隐藏切换form表单,回显数据
      if (this.$route.query.status == 'update') {
          this.RadioFormShow = false;
        if(this.$route.query.flag == '02') {
          this.formTab = false;
          this.provideConView = true;
        }
        this.baseFomrmSub = true;
        this.pageOpe = 'update';
        this.queryContractNo = this.$route.query.contractNo;

        //详情
        this.getDetail(this.queryContractNo);
        //供应商服务项目
        this.getData();
        //附件信息
       // this.getfileTableData();

      } else {
        this.pageOpe = 'add';
      }

    },

    methods: {
      allBaseProviderInfo(){
        const query ={
          nowPage:1,
          pageSize:10000,
          xadef:'select',
        };
        getAllBaseProviderInfo(query).then(response => {
          this.providerInfoSelects = response.rows;
          console.log(response)
        }).catch(error => {
          console.log(error);
        })
      },
      allBaseSupplierInfo (){
        const query ={
          status:'01'
        };
        getAllBaseSupplierInfo(query).then(response => {
          this.supplierInfoSelects = response.data;
        }).catch(error => {
          console.log(error);
        })
      },
      changeRadio(value){
        this.addType = value;
        if(value == '1') {
          this.$refs.searchForm.clearValidate();
          this.formTab = false;

         // this.$refs.providerForm.clearValidate();
        } else {
          this.$refs.providerForm.clearValidate();
          this.formTab = true;

        }
      },
      getContractTypeName(row,col){
        return this.selectDictLabel(this.contractTypes, row.contractType)
      },
      getContractLimitTypeName(row,col){
        return this.selectDictLabel(this.contractLimitTypes, row.contracttermType)
      },
      getValiDate(row,col){
        return row.cvaliDate + " / " + row.expiryDate;
      },
      getStatuTypeName(row,col){
        return this.selectDictLabel(this.statusSlects, row.status)
      },
      resetForm() {
        this.$refs.searchForm.resetFields()
      } ,
      handleClose() {
        this.$emit('handleClose');
        this.fileList = []
        this.$refs.fileForm.resetFields()
      },
      closeDialog(){
        this.open = false;
        this.dialogVisible = false;
      },
      goBack() {
        this.$router.go(-1);
      },
      openFun(){

      },
      diagShow(){
        this.open = true;
        this.dialogVisible = true;
      },
      //供应商改变
      typeChange() {
        this.getSupplierContractListByChangeType(1);
        // //参数集合
        // const query ={
        //   nowPage:1,
        //   pageSize:10000,
        //   servcomNo:this.searchForm.servcomNo
        // };
        // getSupplierContractList(query).then(response => {
        //   this.pendingTableData = response.rows
        // }).catch(error => {
        //
        // })
      },
      //服务机构改变
      typeServerChange() {
        this.getSupplierContractListByChangeType(2);
        // //参数集合
        // const query ={
        //   nowPage:1,
        //   pageSize:10000,
        //   providerCode:this.providerForm.providerCode
        // };
        // getSupplierContractList(query).then(response => {
        //   this.pendingTableData = response.rows
        // }).catch(error => {
        //
        // })
      },

      //历史合约信息 信息
      getSupplierContractListByChangeType(type) {
        let query = {};
        if(type == 1) {
            query = {
            nowPage:1,
            pageSize:10000,
            servcomNo:this.searchForm.servcomNo
          }
        } else {
          query ={
            nowPage:1,
            pageSize:10000,
            providerCode:this.providerForm.providerCode
          };
        }
        //查询数据
        getSupplierContractList(query).then(response => {
          this.pendingTableData = response.rows
        }).catch(error => {

        })
      },

      uploadFile(){
        console.log('uploadFile')
      },
      downloadFile (){
        console.log('downloadFile')
      },
      deleteFile(){
        console.log('deleteFile')
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
      },
      beforeUpload(file) {
        let url = URL.createObjectURL(file);
        console.log("before")
        //this.uploadData.push(url);
      },
      success(response, file, fileList) {
        // this.$refs.upload.clearFiles()
        // this.$notify.success(res.data.F_FileName + "文件上传成功");
        this.fileForm.fileName = file.name;
        this.fileUpload = true;
        console.log('success');
        console.log(response);
      },
      saveFileInfo(){
        if(this.fileUpload == false) {
          this.$message.error('请先上传文件！');
          return false;
        }
        console.log('saveFileInfo');
      },
      fileHandleCurrentChange(){

      },
      fileHandleSizeChange(){

      },
      handleError(file, fileList){
        this.$message.error('上传失败！')
      },
      saveBaseInfo() {

          //服务机构
          this.$refs.providerForm.validate((valid) => {
            if (valid) {
              let baseSupplierContract = {
                flag:'02',
                providerCode : this.providerForm.providerCode,
                contractNo : this.providerForm.contractNo,
                status : this.providerForm.status,
                contractName : this.providerForm.contractName,
                contractPartyA : this.providerForm.contractPartyA,
                contractPartyB : this.providerForm.contractPartyB,
                contractType : this.providerForm.contractType,
                connectedContract : this.providerForm.connectedContract,
                deposit : this.providerForm.deposit,
                effectiveSDate : this.providerForm.effectiveSDate,
                ads : this.providerForm.ads,
             //   contractControlFlag : this.providerForm.contractControlFlag,
                treatmentDiscount : this.providerForm.treatmentDiscount,
                averageCost : this.providerForm.averageCost,
                bedDiscount : this.providerForm.bedDiscount,
                type : this.providerForm.type,
                allowance : this.providerForm.allowance,
                advicenum : this.providerForm.costs,
                costs : this.providerForm.costs,
                averageCostExcept : this.providerForm.averageCostExcept,
                discountinfo : this.providerForm.discountinfo,
                specialDiscount : this.providerForm.specialDiscount,
                project : this.providerForm.project,
                cooperativeUnit : this.providerForm.cooperativeUnit,
                straight : this.providerForm.straight,
                endDate : this.providerForm.endDate,
                reason : this.providerForm.reason,
                relp : this.providerForm.relp,
                phone : this.providerForm.phone,
                boxRidgeCode : this.providerForm.boxRidgeCode,
                tel : this.providerForm.tel,
                email : this.providerForm.email,
                exPer : this.providerForm.exPer,
                exEndate : this.providerForm.exEndate,
              };

              if(this.pageOpe == 'add') {
                addSupplierContract(baseSupplierContract).then(res => {
                  this.provideConView = true;
                  this.queryContractNo = res.data.contractNo;
                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '保存成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    });
                  /*  this.$router.push({
                      path: '/basic-info/components/serviceAgencyContract',
                      query: {providerCode: this.providerCode
                      }
                    })*/


                  } else {
                    this.$message.error('保存失败！')
                  }
                })
                this.$emit('saveSuccess',this.providerForm);
              } else {
                updateSupplierContract(baseSupplierContract).then(res => {
                  if (res != null && res.code === 200) {
                    this.$message({
                      message: '更新成功！',
                      type: 'success',
                      center: true,
                      showClose: true
                    });
                  } else {
                    this.$message.error('更新失败！')
                  }
                })
              }

            } else {
              return false
            }
          })

        }


      },
      addLine() { //添加行数
        var newValue = {
          contractNo:this.queryContractNo,
          serviceCode: '',
          settleCurrency: '',
          minPrice: '',
          maxPrice: '',
          settleType: '',
          supplierCode: '',
          limitnum: '',
          editing:'add'
        };
        //添加新的行数
        this.serverForm.serverInfo.push(newValue);
      },
      getData(){
        //参数集合
        if(this.searchForm.contractNo == '') {
          this.searchForm.contractNo = this.queryContractNo;
        }
        const param = {
          nowPage:1,
          pageSize:1000,
          status:'1',
          contractNo:this.searchForm.contractNo,
          servcomNo:this.searchForm.servcomNo,
        };
        getContractServerList(param).then(response => {
          let _data = response.rows;
          let length = _data.length;
          for(let i = 0;i < length; i++){
            this.$set(_data[i], 'editing', false)
          }
          //赋值
          this.serverForm.serverInfo = _data;
        }).catch(error => {
          console.log(error);
        })
      },
      getfileTableData (){
        //参数集合
        const param = {
          nowPage:1,
          pageSize:1000,
          contractNo:this.searchForm.contractNo,
          servcomNo:this.searchForm.servcomNo,
        };
        getFileList(param).then(response => {
          //赋值
          this.fileTableData = response.rows;
        }).catch(error => {
          console.log(error);
        })
      },
      getDetail(contractNo){
        const param = {
          contractNo:contractNo,
        };
        getSupplierContractDetail(contractNo).then(response => {
          if('200' == response.code) {
            let detailData = response.data;
            if(this.formTab) {
              this.searchForm.contractNo = detailData.contractNo;
              this.searchForm.servcomNo= detailData.servcomNo;
              this.searchForm.contractName = detailData.contractName;
              this.searchForm.contractType = detailData.contractType;
              this.searchForm.contracttermType = detailData.contracttermType;
              this.searchForm.contractsort = detailData.contractsort;
              this.searchForm.contractadvance = detailData.contractadvance;
              this.searchForm.status = detailData.status;
              let effectiveArr = [];
              effectiveArr.push(detailData.cvaliDate);
              effectiveArr.push(detailData.expiryDate);
              this.searchForm.effectiveSDate = effectiveArr;
              this.searchForm.remark = detailData.remark;

              if(detailData.servcomNo != '') {
                this.getSupplierContractListByChangeType(1);
              }

            } else {

          //  let checkSlect =   detailData.providerCode;
              this.providerForm.providerCode = detailData.providerCode.toString() ;// this.providerInfoSelects.checkSlect;
             // alert(detailData.providerCode);

//  this.couponSelected = this.couponList[0].id;
              this.providerForm.contractNo = detailData.contractNo;
              this.providerForm.status = detailData.status;
              this.providerForm.contractName = detailData.contractName;
              this.providerForm.contractPartyA = detailData.contractPartyA;
              this.providerForm.contractPartyB = detailData.contractPartyB;
              this.providerForm.connectedContract = detailData.connectedContract;
              this.providerForm.contractType = detailData.contractType;
              this.providerForm.connectedContract = detailData.connectedContract;
              this.providerForm.deposit = detailData.deposit;

             // this.providerForm.effectiveSDate = detailData.effectiveSDate;
             // this.providerForm. ads = detailData.
            //  this.providerForm.contractControlFlagt = detailData.contractControlFlagt;
              this.providerForm.treatmentDiscount = detailData.treatmentDiscount;

              this.providerForm.averageCost = detailData.averageCost;
              this.providerForm.bedDiscount = detailData.bedDiscount;
              this.providerForm.type = detailData.type;
              this.providerForm.allowance = detailData.allowance;
              this.providerForm.advicenum = detailData.advicenum;
              this.providerForm.costs = detailData.costs;
              this.providerForm.averageCostExcept = detailData.averageCostExcept;
              this.providerForm.discountinfo = detailData.discountinfo;
              this.providerForm.specialDiscount = detailData.specialDiscount;
              this.providerForm.project = detailData.project;
              this.providerForm. cooperativeUnit = detailData.cooperativeUnit;
             // this.providerForm.straightT = detailData.straightT;

              this.providerForm.endDate = detailData.endDate;
              this.providerForm.reason = detailData.reason;

             // this.providerForm.relp
              this.providerForm.phone = detailData.phone;
            //  this.providerForm.boxRidgeCode = detailData.boxRidgeCode;
              this.providerForm.tel = detailData.tel;
              this.providerForm.email = detailData.email;
              //this.providerForm.exPer
              //this.providerForm.exEndate

              if(detailData.providerCode != '') {
                this.getSupplierContractListByChangeType(2);
              }
            }



          }
        }).catch(error => {
          console.log(error);
        })
      },
      handleDelete(index,row) {
        this.$confirm('确定删除?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
          deleteContractServer(row.contractNo).then(response => {
            if(response.code == '200') {
              this.$message({
                  type: 'success',
                  message: '删除成功!'
              });
              this.getData();
            } else {
              this.$message({
                  type: 'info',
                  message: '删除失败'
              });
            }

          }).catch(error => {
            console.log(error);
          })
        }).catch(() => {
        });
      },
      removeLine(index) { //删除行数
        this.serverForm.serverInfo.splice(index, 1)
      },
      save() {

        if(this.baseFomrmSub == false) {
            this.$message({
              message: '请先保存基本信息！',
              type: 'warning'
            });
            return false;
        }

        this.$refs.serverForm.validate((valid) => {
          if (valid) {

            let listDta = this.serverForm.serverInfo;

            // console.log(listDta);
            //
            // const param = {
            //   list: listDta
            // };
            addContractServer(listDta).then(response => {
              // console.log(response)
              if(response.code == '200') {
                this.$message({
                  type: 'success',
                  message: '保存成功!'
                });
                this.getData();
              } else {
                this.$message({
                  type: 'info',
                  message: '保存失败'
                });
              }
            }).catch(error => {
              console.log(error);
            })

          } else {

            return false
          }
        })

      },
      handleEdit(index,row){ //编辑
        row.editing = true;
        this.prevValue = JSON.parse(JSON.stringify(row));
      },
      handleCancle(index,row){ //取消
        row.editing = false;
        this.prevValue = JSON.parse(JSON.stringify(row));
      },

  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
  .long-input /deep/ .el-form-item__content {
    width: calc(100% - 150px);
  }

</style>
