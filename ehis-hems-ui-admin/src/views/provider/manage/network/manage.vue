<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="125px">
      <!-- card1 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-menu mr5"></i>
            <span>网点列表</span>
          </div>
          <!-- 表格区域 -->
          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="网点编码"
              width="200">
              <template slot-scope="scope">{{ scope.row.websitecode }}</template>
            </el-table-column>
            <el-table-column
              label="网点名称"
              width="200" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.websitename }}</template>
            </el-table-column>
            <el-table-column
              label="联系人"
              width="200" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.linkperson }}</template>
            </el-table-column>

            <el-table-column
              label="联系电话"
              width="200">
              <template slot-scope="scope">{{ scope.row.phone }}</template>
            </el-table-column>

            <el-table-column
              label="联系邮箱"
              width="200">
              <template slot-scope="scope">{{ scope.row.email }}</template>
            </el-table-column>

            <el-table-column
              label="工作时间"
              show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.worktime }}</template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-card>
      </el-row>

      <!-- card2 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-chat-round mr5"></i>
            <span>网点信息</span>
          </div>
          <!-- 第一行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="网点编码">
                <el-input disabled placeholder="网点编码自动生成" v-model="form.websitecode" clearable/>
              </el-form-item>
            </el-col>

            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="所属机构名称">
                <el-input disabled v-model="form.chname" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="网点名称" prop="websitename">
                <el-input v-model="form.websitename" placeholder="请输入网点名称" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系人">
                <el-input v-model="form.linkperson" placeholder="请输入联系人" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系电话">
                <el-input v-model="form.phone" placeholder="请输入联系电话" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系邮箱">
                <el-input v-model="form.email" placeholder="请输入联系邮箱" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第三行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="工作时间">
                <el-input v-model="form.worktime" placeholder="请输入工作时间" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="州">
                <el-select v-model="form.continent"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择州">
                  <el-option
                    v-for="dict in continent"
                    :key="dict.placecode"
                    :label="dict.placecode + ' - ' + dict.placename"
                    :value="dict.placecode"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="国家">
                <el-select v-model="form.country"
                           clearable
                           filterable
                           style="width: 100%;"
                           placeholder="请选择国家">
                  <el-option
                    v-for="dict in coutryOptions"
                    :key="dict.placecode"
                    :label="dict.placecode + ' - ' + dict.placename"
                    :value="dict.placecode"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第四行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="所在地区">
                <el-cascader :options="areaSelectData"
                             style="width: 100%;"
                             clearable
                             filterable
                             @change="handleChange"
                             class="full-width"
                             v-model="form.selectedOptions" placeholder="请选择您所在的城市"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第五行 -->
          <el-row>
            <el-col :span="12" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="详细地址">
                <el-input type="textarea" v-model="form.address" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第六行 -->
          <el-row>
            <el-col :span="12" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="网点备注">
                <el-input type="textarea" v-model="form.remark" :rows="2"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>

      <!-- 按钮区域 -->
      <el-row style="text-align: right;">
        <el-col class="mt10 mb10">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="submitForm"
            v-hasPermi="['provider:provider:add']"
          >保存
          </el-button>

          <el-button icon="el-icon-refresh" size="mini" @click="reset">重置</el-button>

          <el-button icon="el-icon-delete" size="mini" @click="delRow" type="danger">删除选中</el-button>
          <el-button icon="el-icon-d-arrow-left" size="mini" @click="back">返回</el-button>
        </el-col>
      </el-row>

    </el-form>
  </div>
</template>

<script>
  import { getProvider, listProviderNetworkByNo, deleteHmpComWebSiteByIds, updateWebSite, addWebSite} from "@/api/provider/provider";
  import {regionData} from "element-china-area-data";

  export default {
    name: "manage",
    data() {
      return {
        areaSelectData: regionData, // options绑定的数据就是引入的 provinceAndCityData
        // 总条数
        total: 5,
        // 模拟表格数据
        tableData: [],
        // 选中复选框数组
        ids: [],
        // 遮罩层
        loading: false,
        form: {
          websitecode: '',
          chname: '',
          websitename: '',
          linkperson: '',
          phone: '',
          email: '',
          continent: "Asia",
          country: 'CHN',
          address: '',
          selectedOptions: [],
          remark: '',
          worktime: '',
        },
        // 表单校验
        rules: {
          websitename: [
            {required: true, message: '网点名称必填', trigger: 'blur'},
          ],
        },
        // 机构类型字典 下拉
        medicaltypeOptions: [],
        // 签约状态字典 下拉
        contractStatus: [],
        // 机构属性字典 下拉
        comAttribute: [],
        // 医疗类型字典 下拉
        medicalType: [],
        // 医院类型字典 下拉
        hospitalDepartment: [],
        // 机构登记字典 下拉
        medicallevel: [],
        // 直付类型字典 下拉
        directPaymentType: [],
        // 直付方式字典 下拉
        paymentWay: [],
        // 医疗资质字典 下拉
        medicalQualification: [],
        // 是否昂贵字典 下拉
        isExpensive: [],
        // 州 下拉
        continent: [],
        // 国家
        coutryOptions: [],
        // 省份
        province: [],
        // 市
        city: [],
        // 区县
        district: [],

        // 页面一跳转 立即查询的查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          servcomno: null,
        },
        // 供应商机构编码
        servcomno: '',
      }
    },
    created() {
      // 获取机构编码
      const servcomno = this.$route.params && this.$route.params.servcomno;
      this.servcomno = servcomno;
      if (servcomno != undefined) {
        this.getProviderByServcomno(servcomno);
      }
      // 赋值
      this.queryParams.servcomno = servcomno;
      // 根据供应商编码查询服务网点配置对象
      this.getList();
      // 获取数据字典 州
      this.selectContinent().then(response => {
        this.continent = response.data;
      });
      // 获取对应州下的国家
      this.selectCountryByContinent(this.form.continent).then(response => {
        this.coutryOptions = response.data;
      });
    },
    methods: {
      // 获取
      getList() {
        this.loading = true;
        listProviderNetworkByNo(this.queryParams).then(response => {
          this.tableData = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 编辑格式化地址
      handleChange() {
        // console.log(this.form.selectedOptions[0]);
        // console.log(this.form.selectedOptions[1]);
        // console.log(this.form.selectedOptions[2]);
        // var self = this;
        // var provinceCode = self.selectedOptions[0];
        // var cityCode = self.selectedOptions[1];
        // // CodeToText属性是区域码，属性值是汉字 CodeToText['110000']输出北京市
        // self.infoForm.province = CodeToText[provinceCode];
        // self.infoForm.city = CodeToText[cityCode];
        // console.log("选择的省市：", self.infoForm.province, self.infoForm.city);
      },
      // 获取到省市数据，进行格式化
      // formatCity(){
      //   var self = this;
      //   console.log(self.infoForm.province,self.infoForm.city);
      //   // TextToCode属性是汉字，属性值是区域码 TextToCode['北京市'].code输出110000
      //   // 省份
      //   var provinceCode = TextToCode[self.infoForm.province].code;
      //   // 城市
      //   var cityCode = TextToCode[self.infoForm.province][self.infoForm.city].code;
      //   self.selectedOptions = [provinceCode, cityCode];
      // },

      /** 根据ID获取Provider */
      getProviderByServcomno(servcomno) {
        getProvider(servcomno).then(response => {
          // 根据供应商编码查询供应商名称
          this.form.chname = response.data.chname;
        });
      },
      /** 保存按钮 */
      submitForm() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            // 新增或修改都需要设置供应商编码
            this.form.servcomno = this.servcomno;
            // 如果机构编码不为空 那么就是修改
            if (this.form.websitecode != '') {
              // 修改操作
              updateWebSite(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                }
              });
              // 修改0.5秒后重新查询
              setTimeout(this.getList, 500);
            } else {
              addWebSite(this.form).then(response => {
                console.log(this.form);
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                }
              });
              // 修改新增0.5秒后重新查询
              setTimeout(this.getList, 500);
            }
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
        });
      },
      /** 重置按钮 */
      reset() {
        this.form.websitecode = '';
        this.form.websitename = '';
        this.form.linkperson = '';
        this.form.phone = '';
        this.form.email = '';
        this.form.worktime = '';
        this.form.selectedOptions = '';
        this.form.address = '';
        this.form.remark = '';
        this.form.continent = 'Asia';
        this.form.country = 'CHN';

        // 重新查
        this.getList();
        this.$message({
          message: '已重置',
          type: 'success'
        });
      },
      /** 删除按钮操作 */
      delRow(row) {
        const websitecode = row.websitecode || this.ids;
        if (websitecode == '') {
          this.$message.error('请先选中再进行删除');
          return;
        }
        // 显示样式
        var websiteStr = "<br/>"
        for(var i=0; i<websitecode.length; i++) {
          websiteStr += websitecode[i] + "<br/>";
        }
        this.$confirm('是否确认删除网点编码为"' + websiteStr + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
          dangerouslyUseHTMLString: true,
        }).then(function () {
          return deleteHmpComWebSiteByIds(websitecode);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      // 新增或修改页面点击返回之后进行路由跳转和重新查询
      back() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/provider/manage/network'});
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.websitecode);
        // 最后一个选中的赋值到表单
        if (this.ids.length != 0) {
          this.form.websitecode = selection[selection.length - 1].websitecode;
          this.form.websitename = selection[selection.length - 1].websitename;
          this.form.linkperson = selection[selection.length - 1].linkperson;
          this.form.phone = selection[selection.length - 1].phone;
          this.form.email = selection[selection.length - 1].email;
          this.form.worktime = selection[selection.length - 1].worktime;
          this.form.continent = selection[selection.length - 1].continent;
          this.form.country = selection[selection.length - 1].country;
          this.form.address = selection[selection.length - 1].address;
          this.form.remark = selection[selection.length - 1].remark;
          this.form.selectedOptions = [selection[selection.length - 1].province, selection[selection.length - 1].city, selection[selection.length - 1].district];
        } else {
          this.form.websitecode = '';
          this.form.websitename = '';
          this.form.linkperson = '';
          this.form.phone = '';
          this.form.email = '';
          this.form.worktime = '';
          this.form.selectedOptions = '';
          this.form.address = '';
          this.form.remark = '';
        }
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
    },
  }
</script>

<style scoped>

</style>
