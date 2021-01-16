<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-location mr5"></i><span>服务项目</span>
        </div>

        <el-table :data="projectList" highlight-current-row @current-change="serviceProjectListCurrentChange">
          <el-table-column type="index" width="50" align="center"/>
          <el-table-column label="服务项目编码" align="center" prop="projectcode"/>
          <el-table-column label="服务项目名称" align="center" prop="projectname"/>
          <el-table-column label="服务项目分类" align="center" prop="projecttype"/>
        </el-table>
        <pagination
          v-show="projectTotal>0"
          :total="projectTotal"
          :page.sync="projectParams.pageNum"
          :limit.sync="projectParams.pageSize"
          :pageSizes="[5,10,15,20]"
          :autoScroll="false"
          @pagination="getProjectList"
        />
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-office-building mr5"></i><span>服务机构</span>
        </div>
        <el-table :data="serviceOrgList" highlight-current-row
                  @current-change="serviceOrgListCurrentChange">
          <el-table-column type="index" width="50" align="center">
          </el-table-column>
          <el-table-column label="机构编码" align="center" prop="servcomno"/>
          <el-table-column label="机构名称" align="center" prop="chname" />
          <el-table-column label="机构类型" align="center" prop="servcomtype"/>
          <el-table-column label="机构等级" align="center" prop="medicallevel"/>
        </el-table>
        <pagination
          v-show="orgTotal>0"
          :total="orgTotal"
          :page.sync="orgParams.pageNum"
          :limit.sync="orgParams.pageSize"
          :pageSizes="[5,10,15,20]"
          :autoScroll="false"
          @pagination="getServiceOrgListByProduct"
        />
      </el-card>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <i class="el-icon-share mr5"></i><span>服务网点</span>
        </div>

        <el-table :data="serviceNetWorkList" v-loading="netLoading" highlight-current-row @current-change="serviceNetWorkListCurrentChange">
          <el-table-column type="index" width="50" align="center"/>
          <el-table-column label="网点编码" align="center" prop="websitecode" />
          <el-table-column label="网点名称" align="center" prop="websitename" />
        </el-table>

        <pagination
          v-show="NetTotal>0"
          :total="NetTotal"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          :pageSizes="[5,10,15,20]"
          :autoScroll="false"
          @pagination="getServiceNetWorkListByServiceOrg"
        />

        <el-form>
          <el-form-item style="text-align: right;margin-top:20px;">
            <el-button
              type="primary"
              icon="el-icon-s-claim"
              size="mini"
              @click="serviceNetWorkSave"
              v-hasPermi="['system:definition:add']"
            >保存</el-button>
            <el-button
              icon="el-icon-close"
              size="mini"
              @click="close"
              v-hasPermi="['system:definition:remove']"
            >返 回</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-row>

  </div>
</template>

<script>
  import {getServiceComNo, getServiceNetWorkList, getProvinceAll, getCityByProvince, getDistrictByCity, queryServiceOrgAndNetWork, getProductInfoById, saveServiceCity, queryServiceCity, productAudit, saveProductProject} from "@/api/product/info";
  import {listProjectInfo, getServiceOrgList} from "@/api/product/project";
  import { selectDictLabel } from "@/utils/sinoutils";
  import Editor from "../../../components/Editor/index";

  export default {
    name: "ProjectChange",
    components: {Editor},
    data(){
      return {
        // 遮罩层
        projectLoading: false,
        orgLoading: false,
        netLoading: false,
        // 总条数
        NetTotal: 0,
        projectTotal: 0,
        orgTotal: 0,
        // 获取的产品编码
        productno:"",
        //产品编码是否可以编辑
        disableFlag:false,
        //页面输入框是否变化标记
        modifyNum:0,
        // 用户表格数据
        projectList: [],
        serviceOrgList: [],
        serviceNetWorkList: [],
        // serviceCityList: [],
        //服务项目选中的单行
        serviceProjectOneRow: null,
        //服务项目选中的行
        serviceProjectSelectList: [],
        //服务机构选中的单行
        serviceOrgOneRow:null,
        //服务机构选中的行
        serviceOrgSelectList:[],
        //网点选中的行
        serviceNetWorkSelectList:[],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,

        // 表单参数
        auditform:{
          auditResults: undefined,
          auditOpinion: undefined
        },
        serviceOrg: {
          serivcecomtype: undefined,
          serivcecomno: undefined,
          servicecomcost: undefined,
          currency: undefined,
          payamount: undefined,
          settlementtype: undefined,
          serviceincomeamount: undefined,
          invaild: "Y",
          remark: undefined
        },
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 服务网点查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 5
        },
        // 服务项目查询参数
        projectParams: {
          pageNum: 1,
          pageSize: 5,
          projectcode: null,
          projectname: null,
          projecttype: null
        },
        // 服务机构查询参数
        orgParams: {
          pageNum: 1,
          pageSize: 5
        },
      };
  },
    created() {
      const productno = this.$route.params && this.$route.params.productno;
      this.productno = productno;
      // this.getProductInfoById(productno);
      this.getProjectList();
    },
    watch:{
      form:{
        handler(){
          this.modifyNum++;
        },
        deep:true
      }
    },
    methods: {
      /** 查询产品详情 */
      // getProductInfoById(productno){
      //   if(productno == "0"){
      //     this.getProjectList();
      //     return;
      //   }
      //   getProductInfoById(productno).then(response => {
      //     this.form = response.data;
      //     this.form.params = undefined;
      //     getProductClass(this.form).then(response => {
      //         this.productClassOptions = response.data;
      //       }
      //     );
      //     getProductSubClass(this.form).then(response => {
      //         this.productSubClassOptions = response.data;
      //       }
      //     );
      //     queryServiceOrgAndNetWork(this.form.productno).then(response => {
      //       if (response.code === 200) {
      //         //this.serviceOrgList = response.serviceComs;
      //         response.serviceComs.forEach(item => {
      //           item.params = undefined;
      //           item.show = false;
      //
      //           getServiceComNo(item).then(response => {
      //               item.serivcecomnoname = selectDictLabel(response.data,item.serivcecomno);
      //             }
      //           );
      //           getServiceComNo(item).then(response => {
      //               this.serivceComNoOptions = response.data;
      //             }
      //           );
      //         });
      //         this.serviceOrgList = response.serviceComs;
      //         this.serviceNetWorkList = response.serviceNetWorks;
      //         this.NetTotal = 1;
      //         this.netLoading = false;
      //         this.modifyNum = 0;
      //       }
      //     });
      //     queryServiceCity(this.form.productno).then(response => {
      //       if (response.code === 200) {
      //         //this.serviceOrgList = response.serviceComs;
      //         response.salComs.forEach(item => {
      //           item.params = undefined;
      //           item.show = false;
      //           getProvinceAll().then(response => {
      //               item.salprovincename = selectDictLabel(response.data,item.salprovince);
      //             }
      //           );
      //           getCityByProvince(item).then(response => {
      //               this.cityOptions = response.data;
      //             }
      //           );
      //           getCityByProvince(item).then(response => {
      //               item.salcityname = selectDictLabel(response.data,item.salcity);
      //             }
      //           );
      //           getDistrictByCity(item).then(response => {
      //               this.districtOptions = response.data;
      //             }
      //           );
      //           getDistrictByCity(item).then(response => {
      //               item.salareaname = selectDictLabel(response.data,item.salarea);
      //             }
      //           );
      //
      //         });
      //         //console.log(response.salComs);
      //         this.serviceCityList = response.salComs;
      //       }
      //     });
      //   });
      //   this.disableFlag = true;
      // },

      /** 查询服务项目列表 */
      getProjectList(){
        this.projectLoading = true;
        listProjectInfo(this.projectParams).then(response => {
          this.projectList = response.rows;
          this.projectTotal = response.total;
          this.projectLoading = false;
        })
      },

      /** 省份改变触发方法 */
      changeCity(row){
        row.salcity = undefined;
        row.salarea = undefined;
        getProvinceAll().then(response => {
            row.salprovincename = selectDictLabel(response.data,row.salprovince);
          }
        );
        getCityByProvince(row).then(response => {
            this.cityOptions = response.data;
          }
        );
      },
      /** 市改变触发方法 */
      changeDistrict(row){
        row.salarea = undefined;
        getCityByProvince(row).then(response => {
            row.salcityname = selectDictLabel(response.data,row.salcity);
          }
        );
        getDistrictByCity(row).then(response => {
            this.districtOptions = response.data;
          }
        );
      },
      /** 地区改变触发方法 */
      changeDistrictName(row){
        getDistrictByCity(row).then(response => {
            row.salareaname = selectDictLabel(response.data,row.salarea);
          }
        );
      },

      //服务项目选中数据
      serviceProjectListCurrentChange(val){
        console.log(val);
        val.params = undefined;
        this.serviceProjectSelectList.length = 0;
        this.serviceProjectSelectList.push(val);
        this.serviceProjectOneRow = val;
        this.orgLoading = true;
        this.serviceProjectOneRow.pageNum = this.projectParams.pageNum;
        this.serviceProjectOneRow.pageSize = this.projectParams.pageSize;
        getServiceOrgList(this.serviceProjectOneRow).then(response => {
          this.serviceOrgList = response.rows;
          this.orgTotal = response.total;
          this.orgLoading = false;
        })
      },

      // 服务机构分页方法
      getServiceOrgListByProduct(){
        let val = this.serviceProjectOneRow;
        this.orgLoading = true;
        val.pageNum = this.orgParams.pageNum;
        val.pageSize = this.orgParams.pageSize;
        getServiceOrgList(val).then(response => {
          this.serviceOrgList = response.rows;
          this.orgTotal = response.total;
          this.orgLoading = false;
        })
      },

      // 服务机构选中数据
      serviceOrgListCurrentChange(val) {
        val.params = undefined;
        this.serviceOrgSelectList.length = 0;
        this.serviceOrgSelectList.push(val);
        this.serviceOrgOneRow = val;
        this.netLoading = true;
        this.serviceOrgOneRow.pageNum = this.queryParams.pageNum;
        this.serviceOrgOneRow.pageSize = this.queryParams.pageSize;
        getServiceNetWorkList(this.serviceOrgOneRow).then(response => {
            this.serviceNetWorkList = response.rows;
            this.NetTotal = response.total;
            this.netLoading = false;
          }
        );
      },

      //获取网点数据
      getServiceNetWorkListByServiceOrg(){
        let val = this.serviceOrgOneRow;
        this.netLoading = true;
        val.pageNum = this.queryParams.pageNum;
        val.pageSize = this.queryParams.pageSize;
        getServiceNetWorkList(val).then(response => {
            this.serviceNetWorkList = response.rows;
            this.NetTotal = response.total;
            this.netLoading = false;
          }
        );
      },

      // 网点单选中数据
      serviceNetWorkListCurrentChange(val) {
        this.serviceNetWorkSelectList.length = 0;
        this.serviceNetWorkSelectList.push(val);
      },

      //ServiceOrgList添加一行
      addServiceOrgListOneRow(){
        if(this.serviceOrgList.length>0){
          this.msgError("只能添加一行数据");
        }else{
          let row = {
            serivcecomtype: null,
            serivcecomno: null,
            servicecomcost: null,
            currency: null,
            payamount: null,
            settlementtype: null,
            serviceincomeamount: null,
            invaild: "Y",
            remark: null,
            show:true
          };
          this.serviceOrgList.push(row);
        }
      },


      /** 服务网点save */
      serviceNetWorkSave(row) {
        if (this.projectList.length===0){
          this.msgError("请选择一个服务项目！");
          return false;
        }
        if(this.serviceOrgList.length===0 ){
          this.msgError("请至少录入一条供应商信息！");
          return false;
        }
        this.modifyNum = 0;
        console.log("校验结束");
        let product = {};
        product.productno = this.productno;
        saveProductProject(product,this.serviceProjectSelectList,this.serviceOrgSelectList,this.serviceNetWorkSelectList).then(response => {
          if (response.code === 200) {
            this.disableFlag = true;
            // this.form = response.data;
            // this.productno = this.form.productno;
            // this.form.params = undefined;
            this.modifyNum = 0;

            queryServiceOrgAndNetWork(this.productno).then(response => {
              if (response.code === 200) {
                // response.serviceComs.forEach(item => {
                //   item.params = undefined;
                //   item.show = false;
                //   getServiceComNo(item).then(response => {
                //       this.serivceComNoOptions = response.data;
                //     }
                //   );
                //   getServiceComNo(item).then(response => {
                //       item.serivcecomnoname = selectDictLabel(response.data,item.serivcecomno);
                //     }
                //   );
                // });
                this.serviceOrgList = response.serviceComs;
                this.serviceNetWorkList = response.serviceNetWorks;
                this.queryParams.pageNum = 1;
                this.NetTotal = 1;
                this.netLoading = false;
                this.modifyNum = 0;
              }
            });
            this.netLoading = false;
            this.msgSuccess("保存成功");
          }else{
            this.msgError(response.msg);
          }
        });
      },

      /** 服务地区save */
      // serviceCitySave(row) {
      //   if(undefined != this.serviceCityList[0]) {
      //     let province = this.serviceCityList[0].province;
      //     if (province == "") {
      //       this.msgError("请确认销售区域的省已经选择！");
      //       return false;
      //     }
      //   }
      //   if(this.form.productno == undefined){
      //     this.msgError("请先保存产品基本信息！");
      //     return false;
      //   }
      //   if(this.modifyNum > 0){
      //     this.msgError("产品信息有变化，请先保存产品基本信息！");
      //     return;
      //   }
      //   this.$refs["form"].validate(valid => {
      //     if (!valid) {
      //       return false;
      //     }
      //   });
      //   if(this.serviceOrgList.length == 0 ){
      //     this.msgError("请至少选择一条服务机构！");
      //     return false;
      //   }
      //   let te=this.serviceOrgList[0].serivcecomno;
      //   if(te=="" || te==undefined){
      //     this.msgError("请选择机构名称后保存！");
      //     return false;
      //   }
      //   saveServiceCity(this.form,this.serviceCityList).then(response => {
      //     if (response.code === 200) {
      //       queryServiceCity(this.form.productno).then(response => {
      //         if (response.code === 200) {
      //           //this.serviceOrgList = response.serviceComs;
      //           response.salComs.forEach(item => {
      //             item.params = undefined;
      //             item.show = false;
      //             getProvinceAll().then(response => {
      //                 item.salprovincename = selectDictLabel(response.data,item.salprovince);
      //               }
      //             );
      //             getCityByProvince(item).then(response => {
      //                 this.cityOptions = response.data;
      //               }
      //             );
      //             getCityByProvince(item).then(response => {
      //                 item.salcityname = selectDictLabel(response.data,item.salcity);
      //               }
      //             );
      //             getDistrictByCity(item).then(response => {
      //                 this.districtOptions = response.data;
      //               }
      //             );
      //             getDistrictByCity(item).then(response => {
      //                 item.salareaname = selectDictLabel(response.data,item.salarea);
      //               }
      //             );
      //
      //           });
      //           this.serviceCityList = response.salComs;
      //           this.modifyNum = 0;
      //         }
      //       });
      //       this.msgSuccess("保存成功");
      //     }else{
      //       this.msgError(response.msg);
      //     }
      //   });
      // },

      /** 关闭按钮操作 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        // this.$router.push({ path: "/product/definition/subreturn", query: { t: Date.now()}})
        this.$router.back();
      }
    }

  };
</script>
