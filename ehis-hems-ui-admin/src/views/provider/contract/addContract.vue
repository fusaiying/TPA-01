<template>
  <div class="app-container">
    <!-- 合约基础信息 -->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <el-row>
            <i class="el-icon-folder mr5"></i>
            <span>合约基本信息</span>
          </el-row>
        </div>
        <el-form ref="form" :model="form" :rules="rules" label-width="125px">
          <!-- 第一行-->
          <el-row>
            <el-col :span="8">
              <el-form-item label="合约ID">
                <el-input v-model="form.contractno" disabled clearable placeholder="合约ID自动生成"/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约编码">
                <el-input v-model="form.contractversion" placeholder="请输入合约编码" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约名称" prop="contractname">
                <el-input v-model="form.contractname" clearable placeholder="请输入合约名称"/>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- 第二行 -->
          <el-row>
            <el-col :span="8">
              <el-form-item label="合约类型" prop="contracttype">
                <el-select v-model="form.contracttype" filterable placeholder="请选择合约类型" clearable style="width: 100%">
                  <el-option
                    v-for="dict in contracttype"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约对象编码" prop="servcomno">
                <el-select
                  filterable
                  v-model="form.servcomno"
                  placeholder="请选择合约对象编码"
                  clearable
                  @change="getContractObjectName"
                  style="width: 100%">
                  <el-option
                    v-for="dict in  contractObj"
                    :key="dict.servcomno"
                    :label="dict.servcomno + ' - ' +dict.chname"
                    :value="dict.servcomno"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约签订日期" prop="signdate">
                <el-date-picker clearable
                                v-model="form.signdate"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择合约签订日期"
                                style="width: 100%;">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- 第三行 -->
          <el-row>
            <el-col :span="8">
              <el-form-item label="提前协商时间" prop="contractadvance">
<!--                <el-input v-model="form.contractadvance" placeholder="请输入提前协商时间" clearable/>-->
                <el-input-number v-model="form.contractadvance" style="width: 100%;" :min="0" label="请输入提前协商时间"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合约期限类型" prop="contracttermtype">
                <el-select
                  v-model="form.contracttermtype"
                  placeholder="请选择合约期限类型"
                  clearable
                  @change="isshow"
                  style="width: 100%">
                  <el-option
                    v-for="dict in contracttermtype"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <!--                <el-form-item label="合约有效期" prop="cvalidate" v-show="show1">-->
              <!--                  <el-date-picker-->
              <!--                    v-model="form.cvalidate"-->
              <!--                    type="date"-->
              <!--                    value-format="yyyy-MM-dd"-->
              <!--                    style="width: 100%;"-->
              <!--                    placeholder="请选择合约有效期">-->
              <!--                  </el-date-picker>-->
              <!--                </el-form-item>-->
              <el-form-item label="合约有效期" prop="cvalidate">
                <el-date-picker
                  v-model="form.cvalidate"
                  type="daterange"
                  align="right"
                  unlink-panels
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd"
                  :picker-options="pickerOptions">
                </el-date-picker>
              </el-form-item>
            </el-col>

            <!--          <el-col :span="8">-->
            <!--            <el-form-item label="合约有效期" prop="expirydate" v-show="show2">-->
            <!--              <el-date-picker-->
            <!--                clearable-->
            <!--                v-model="form.expirydate"-->
            <!--                type="datetimerange"-->
            <!--                value-format="yyyy-MM-dd"-->
            <!--                style="width: 100%"-->
            <!--                start-placeholder="开始日期"-->
            <!--                end-placeholder="结束日期"-->
            <!--                range-separator="-"-->
            <!--                :default-time="['00:00:00', '00:00:00']"-->
            <!--              >-->
            <!--              </el-date-picker>-->
            <!--            </el-form-item>-->
            <!--          </el-col>-->
          </el-row>
          <!-- 第四行 -->
          <el-row>

            <el-col :span="8">
              <el-form-item label="合同分类">
                <el-select v-model="form.contractsort"
                           placeholder="请选择合同分类"
                           clearable
                           filterable
                           style="width: 100%">
                  <el-option
                    v-for="dict in contclassifyOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- 第五行 -->
          <el-row>
            <el-col :span="24">
              <el-form-item label="合约备注" prop="remark">
                <el-input
                  type="textarea"
                  v-model="form.remark"
                  clearable
                  placeholder="包括：权责、联系人、价格、约定服务流程等">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </el-row>

    <!--供应商服务项目卡单-->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-s-cooperation mr5"></i>
          <span>供应商服务项目</span>
        </div>
        <el-table :data="serviceInfo" highlight-current-row>
          <el-table-column type="index" width="50" align="center">
          </el-table-column>
          <el-table-column label="服务项目名称" align="center">
            <template slot-scope="scope">
              <el-select
                filterable
                v-model="scope.row.projectname"
                v-show="scope.row.show"
                placeholder="请选择服务项目名称"
                clearable>
                <el-option
                  v-for="dict in serviceProjectName"
                  :key="dict.projectcode"
                  :label="dict.projectcode + ' - ' +dict.projectname"
                  :value="dict.projectcode"
                ></el-option>
              </el-select>
<!--              <span v-show="!scope.row.show">{{scope.row.projectname}}</span>-->
            </template>
          </el-table-column>

          <el-table-column label="结算方式" align="center">
            <template slot-scope="scope">
              <el-select
                filterable
                v-model="scope.row.payway"
                v-show="scope.row.show"
                placeholder="请选择结算方式"
                clearable>
                <el-option
                  v-for="dict in supsettlementtype"
                  :key="dict.dictValue"
                  :label="dict.dictValue + ' - ' +dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
              <span v-show="!scope.row.show">{{getPayWayChnName(scope.row.payway)}}</span>
            </template>
          </el-table-column>

          <el-table-column label="合约价格" align="center">
            <template slot-scope="scope">
<!--              <el-input v-model="scope.row.contactprice"-->
<!--                        v-show="scope.row.show"-->
<!--                        placeholder="请输入合约价格"-->
<!--                        clearable/>-->
              <el-input-number v-model="scope.row.contactprice" :precision="2" :step="0.1" :min="0.0"></el-input-number>
              <span v-show="!scope.row.show">{{scope.row.contactprice}}</span>
            </template>
          </el-table-column>

          <el-table-column label="备注" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.remark"
                        v-show="scope.row.show"
                        placeholder="请输入备注信息"
                        clearable/>
              <span v-show="!scope.row.show">{{scope.row.remark}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"
                           fixed="right">
            <template slot-scope="scope">
<!--              <el-button-->
<!--                size="mini"-->
<!--                type="text"-->
<!--                icon="el-icon-s-claim"-->
<!--                v-show="scope.row.show"-->
<!--                @click="scope.row.show=false"-->
<!--                v-hasPermi="['system:information:remove']"-->
<!--              >保存-->
<!--              </el-button>-->
<!--              <el-button-->
<!--                size="mini"-->
<!--                type="text"-->
<!--                icon="el-icon-edit"-->
<!--                v-show="!scope.row.show"-->
<!--                @click="scope.row.show=true"-->
<!--                v-hasPermi="['system:information:remove']"-->
<!--              >编辑-->
<!--              </el-button>-->
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="deleteServiceProjListOneRow(scope.$index)"
                v-hasPermi="['system:information:remove']"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row :gutter="10" class="mb8">
          <el-col :span="24">
            <el-button
              style="width: 100%; margin-top: 3px;"
              icon="el-icon-plus"
              size="mini"
              @click="addServiceProjListOneRow"
              v-hasPermi="['system:definition:add']"
            >新增
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>

    <!--合同附件信息卡单-->
    <el-row class="mb10">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <el-row>
            <el-col :span="12">
              <i class="el-icon-folder mr5"></i>
              <span>合同附件信息</span>
            </el-col>
            <el-col :span="12" style="text-align: right">
              <el-button
                style=""
                type="success"
                icon="el-icon-download"
                size="mini"
                @click=""
                v-hasPermi="['system:definition:add']"
              >下载附件
              </el-button>
              <el-button
                style=""
                type="danger"
                icon="el-icon-delete"
                size="mini"
                @click=""
                v-hasPermi="['system:definition:add']"
              >删除附件
              </el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-button
                style=""
                type="primary"
                icon="el-icon-upload2"
                size="mini"
                @click=""
                v-hasPermi="['system:definition:add']"
              >附件上传
              </el-button>
              <el-tag type="info">支持扩展名：.rar .zip .doc .docx .pdf .jpg .png</el-tag>
            </el-col>
          </el-row>
        </div>
        <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column type="index" label="序号" align="center" show-overflow-tooltip width="50px"/>
          <el-table-column label="合约名称" align="center" prop="filename" show-overflow-tooltip/>
        </el-table>
        <el-form ref="fileform" :model="fileform" label-width="125px" class="mt10">
          <el-form-item label="附件备注">
            <el-input type="textarea" v-model="fileform.filedesc" :rows="2"></el-input>
          </el-form-item>
        </el-form>
        <el-row style="text-align: right;">
          <el-col class="mt10">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="submitForm"
              v-hasPermi="['provider:provider:add']"
            >保存
            </el-button>
            <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
  </div>
</template>

<script>
  import {
    getContract,
    addContract,
    updateContract,
    getContractObj,
    selectHmpServProjectOptions,
    getConPro,
  } from "@/api/provider/contract";
  import {selectDictLabel} from "@/utils/sinoutils";
  import {listFile, delFile, downloadFile} from "@/api/system/file";

  export default {
    name: "addContract",
    data() {
      return {
        // 表单参数
        form: {
          cvalidate: [],
          contractadvance: 0,
        },
        // 表单非空
        rules: {
          contractname: [
            {required: true, message: "合约名称不能为空", trigger: "blur"}
          ],
          contracttype: [
            {required: true, message: "合约类型不能为空", trigger: "change"}
          ],
          servcomno: [
            {required: true, message: "合约对象编码", trigger: "blur"}
          ],
          contracttermtype: [
            {required: true, message: '合约期限类型不能为空', trigger: 'change'}
          ],
          cvalidate: [
            {required: true, message: "合约生效日期不能为空", trigger: "change"}
          ],
          expirydate: [
            {required: true, message: "合约有效期不能为空", trigger: "change"}
          ],
        },
        // 合约类型 下拉
        contracttype: [],
        // 合约对象编码 下拉
        contractObj: [],
        // 合约期限类型 下拉
        contracttermtype: [],
        // 合同分类 下拉
        contclassifyOptions: [],
        // 服务项目 下拉
        serviceProjectName: [],
        // 结算方式下拉
        supsettlementtype: [],
        //截止日期的显示与隐藏
        show1: false,
        show2: false,
        // 服务项目数据
        serviceInfo: [],
        // 遮罩层
        loading: false,
        // 模拟表格数据
        tableData: [],
        // 文件表单
        fileform: {
          filedesc: '',
        },
        // 查询表单
        queryForm: {
          pageNum: 1,
          pageSize: 10,
        },

        // 合约编码
        contractno: '',

        //  日期插件 自带函数
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
      }
    },
    created() {
      // 获取合约编码
      const contractno = this.$route.params && this.$route.params.contractno;
      // 只有在机构编码存在的情况下（也就是编辑的时候），才会根据机构编码查询供应商
      if (contractno != undefined) {
        // 编辑的时候为合约编码赋值
        this.contractno = contractno;
        // 合约信息
        getContract(contractno).then(response => {
          // 往修改页面带数据
          this.form = response.data;
          // 解决浏览器控制台错误提示 如果不为空那么进行拼装，为空不做操作
          if (response.data.cvalidatebegin != null) {
            this.form.cvalidate = [response.data.cvalidatebegin, response.data.cvalidateend];
          }
        });
        // 服务项目信息
        getConPro(contractno).then(response => {
          if (response.code == 200) {
            if (response.hmpServConproList.length != 0) {
              // 循环遍历每一条服务项目 将show设置为false
              response.hmpServConproList.forEach(item => {
                item.show = true;
              });
              // 将返回的数据进行回显
              this.serviceInfo = response.hmpServConproList;
              var arr = response.hmpServConproList;
              for (var i = 0; i < arr.length; i++) {
                this.serviceInfo[i].projectname = arr[i].projectcode;
              }
            }
          }
        });
        // 查询合约附件信息
        this.getList();
      }
      /** 合约类型数据字典 */
      this.getDicts("contract_contracttype").then(response => {
        this.contracttype = response.data;
      });
      // 获取合约对象编码编码下拉列表
      getContractObj().then(response => {
        this.contractObj = response.data;
      });
      /** 合同分类数据字典 contract */
      this.getDicts("contract_contractsort").then(response => {
        this.contclassifyOptions = response.data;
      });
      /** 合约期限类型数据字典 */
      this.getDicts("contract_contracttermtype").then(response => {
        this.contracttermtype = response.data;
      });
      // 结算方式下拉框数据
      this.getDicts("contract_payway").then(response => {
        this.supsettlementtype = response.data;
      });
      // 服务项目名称 下拉
      selectHmpServProjectOptions().then(response => {
        this.serviceProjectName = response.data;
      });
    },
    methods: {
      // 获取附件列表
      getList() {
        this.loading = true;
        this.queryForm.filesource = this.contractno;
        listFile(this.queryForm).then(response => {
          this.tableData = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 调用封装方法 显示下拉框名称  注意：本方法只适用于配置在字典中的
      getPayWayChnName(payway) {
        return selectDictLabel(this.supsettlementtype, payway);
      },
      /** 合约对象名称 */
      getContractObjectName() {
        if (this.contractObj != null) {
          for (let i = 0; i < this.contractObj.length; i++) {
            if (this.contractObj[i].servcomno == this.form.servcomno) {
              this.form.contractobjectname = this.contractObj[i].chname;
            }
          }
        }
      },
      /** 是否显示合约截至日期选择栏 */
      isshow() {
        if (this.form.contracttermtype === "01") {
          this.show1 = false;
          this.show2 = true;
        } else if (this.form.contracttermtype === "02") {
          this.show2 = false;
          this.show1 = true;
        }
      },

      // 新增一行供应商服务项目
      addServiceProjListOneRow() {
        var serviceInfoLength = this.serviceInfo.length;
        if (serviceInfoLength == 0) {
          const row = {
            projectname: '',
            payway: '',
            contactprice: '',
            reamrk: '',
            show: true
          };
          this.serviceInfo.push(row);
        } else {
          if (this.serviceInfo[serviceInfoLength - 1].projectname != '') {
            const row = {
              projectname: '',
              payway: '',
              contactprice: '',
              remark: '',
              show: true
            };
            this.serviceInfo.push(row);
          } else {
            this.$message({
              showClose: true,
              message: '请确认服务项目已经选择！',
              type: 'error'
            });
          }
        }


      },
      //删除一行
      deleteServiceProjListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.serviceInfo.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

      /** 多选框选中数据 */
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.contractno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            // 日期校验判断
            if (this.form.signdate != undefined && new Date(this.form.signdate) < new Date(this.form.cvalidate[0])) {
              this.msgError("当前[合约签订日期]早于[合约有效日期]，请确认！");
              return false;
            }

            // 合约生效日期
            const cvalidate = this.form.cvalidate;
            if (cvalidate != null) {
              this.form.cvalidatebegin = cvalidate[0];
              this.form.cvalidateend = cvalidate[1];
            }
            this.form.cvalidate = new Date(cvalidate[0]);

            // 服务项目至少为一个
            if (this.serviceInfo.length == 0) {
              this.msgError("每个供应商至少提供一项服务项目，请确认！");
              return false;
            }

            // 判断是新增还是修改
            if (this.form.contractno != null) {
              // 如果最后一个服务项目没有选择
              if (this.serviceInfo.length != 0) {
                if (this.serviceInfo[this.serviceInfo.length - 1].projectname == '') {
                  this.$message({
                    showClose: true,
                    message: '请确认服务项目已经选择！',
                    type: 'error'
                  });
                  return false;
                }
              }
              // 设置服务项目编码
              for (var i = 0; i < this.serviceInfo.length; i++) {
                this.serviceInfo[i].projectcode = this.serviceInfo[i].projectname;
              }
              // 修改
              updateContract(this.form, this.serviceInfo).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                }
              });
            } else {
              // 如果最后一个服务项目没有选择
              if (this.serviceInfo.length != 0) {
                if (this.serviceInfo[this.serviceInfo.length - 1].projectname == '') {
                  this.$message({
                    showClose: true,
                    message: '请确认服务项目已经选择！',
                    type: 'error'
                  });
                  return false;
                }
              }
              // 设置服务项目编码
              for (var i = 0; i < this.serviceInfo.length; i++) {
                this.serviceInfo[i].projectcode = this.serviceInfo[i].projectname;
              }
              // 新增
              addContract(this.form, this.serviceInfo).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                }
              });
            }
          } else {
            // 页面跳转至 第一个不符合规范的地方
            setTimeout(() => {
              var isError = document.getElementsByClassName("is-error");
              isError[0].querySelector(['input', 'textarea']).focus();
            }, 100);
            this.$message({
              message: '请检查输入内容是否符合规范，再进行保存',
              type: 'warning'
            });
          }
        });
      },

      // 返回
      back() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/provider/contract'});
      },
    },
  }
</script>

<style scoped>

</style>
