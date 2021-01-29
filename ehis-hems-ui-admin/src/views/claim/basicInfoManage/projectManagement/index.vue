<template>
  <div class="projectList_div">
    <!--Form查询条件 -->

      <el-card class="queryForm_card">
        <el-form ref="resetData" :model="queryParams" label-width="110px" >
          <el-row>
            <el-col :span="8">
              <el-form-item label="类别：" prop="categoryCode">
                <el-select v-model="queryParams.categoryCode"
                           style="width: 220px;"
                           size="mini"
                           placeholder="请选择"
                           @change="changeCategory"
                           clearable>
                  <el-option v-for="option in projectCategoryOptions"
                             :key="option.dictValue"
                             :label="option.dictValue + ' - ' +option.dictLabel"
                             :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="种类：" prop="servertypeCode">
                <el-select v-model="queryParams.servertypeCode" style="width: 220px;" size="mini" placeholder="请选择" clearable>
                  <el-option v-for="option in serverTypeOptions"
                             :key="option.dictValue"
                             :label="option.dictValue + ' - ' +option.dictLabel"
                             :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="服务项目名称：" prop="serviceName">
                <el-input v-model="queryParams.serviceName" style="width: 220px;" size="mini" placeholder="请输入" @keyup.enter.native="initData" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="别名：" prop="alias">
                <el-input v-model="queryParams.alias" style="width: 220px;" size="mini" placeholder="请输入" @keyup.enter.native="initData" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="来源：" prop="orgin">
                <el-select v-model="queryParams.orgin" style="width: 220px;" size="mini" placeholder="请选择" clearable>
                  <el-option v-for="option in projectOrginOptions"
                             :key="option.dictValue"
                             :label="option.dictLabel"
                             :value="option.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item class="queryForm_button">
              <el-button size="mini" type="success" icon="el-icon-search" @click="initData" >查 询</el-button>
              <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('resetData')">重 置</el-button>
            </el-form-item>
          </el-row>
        </el-form>
      </el-card>


    <!-- 卡片、表格 -->
    <div class="projectList__card_div">
      <el-card class="projectList_card">
        <div slot="header">
          <span>服务项目列表({{ projectListNum }})</span>
          <span style="float: right;">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              style="float: right;margin-right: 10px;"
              @click="handleAdd"
            >新增
            </el-button>
          </span>
        </div>
        <el-table :data="projectList"
                  ref="multipleTable"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="mini"
                  tooltip-effect="dark"
                  class="projectList_table">
          <el-table-column label="类别" align="center" prop="categoryName" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.categoryCode}}+{{scope.row.categoryName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="种类" align="center" prop="servertypeName" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.servertypeCode}}+{{scope.row.servertypeName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="服务项目名称" align="center" prop="serviceName" width="220px" show-overflow-tooltip>
            <template slot-scope="scope">
              <span>{{scope.row.serviceCode}}+{{scope.row.serviceName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="别名" align="center" prop="alias" show-overflow-tooltip/>
          <el-table-column label="来源" align="center" prop="orgin" show-overflow-tooltip/>
          <el-table-column label="状态" align="center" prop="status" show-overflow-tooltip>
          </el-table-column>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                style="padding: 0px;"
                v-if="scope.row.status == '待发布'"
                @click="handleUpdate(scope.row)"
              >编辑
              </el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '待发布'"
                @click="handleFlow(scope.row)"
              >流程配置
              </el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '待发布'"
                @click="handleDelete(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="projectListNum>0"
          :total="projectListNum"
          :page-sizes="pageSizes"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />

      </el-card>
    </div>

  </div>

</template>

<script>
  import { getCategoryType, getServerType, listProjectInfo, delProjectInfo } from "@/api/provider/project";

  export default {
    name: "ProjectList",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 显示搜索条件
        showSearch: true,
        // 页码
        projectListNum: 0,
        pageSizes: [5,10],
        // 产品项目信息表格数据
        projectList: [],
        // 数据字典
        projectCategoryOptions: [],
        serverTypeOptions: [],
        projectOrginOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          categoryCode: undefined,
          servertypeCode: undefined,
          serviceName: undefined,
          alias: undefined,
          orgin: undefined
        },
      };
    },
    created() {
      this.getList();
      getCategoryType().then(response => {
        this.projectCategoryOptions = response.data;
      });
      this.getDicts("project_origin").then(response => {
        this.projectOrginOptions = response.data;
      });
    },
    methods: {
      /** 查询服务项目信息列表 */
      getList() {
        this.loading = true;
        listProjectInfo(this.queryParams).then(response => {
          this.projectList = response.rows;
          console.log(this.projectList)
          this.projectListNum = response.total;
          this.loading = false;
        });
      },
      // 类别改变触动方法
      changeCategory(){
        this.queryParams.servertypeCode = undefined;
        getServerType(this.queryParams.categoryCode).then(response => {
          this.serverTypeOptions = response.data;
        });
      },
      /** 搜索按钮操作 */
      initData() {
        var flag = true;
        this.$refs["resetData"].validate(valid => {
          console.log("阻断：",valid)
          if (this.queryParams.categoryCode == undefined && this.queryParams.servertypeCode == undefined && (this.queryParams.serviceName == undefined || this.queryParams.serviceName == ' ')
            && (this.queryParams.alias == undefined || this.queryParams.alias == ' ') && this.queryParams.orgin == undefined){
            this.msgError("请输入至少一个查询条件")
            flag = false;
            return flag;
          }
        })
        if (flag){
          this.queryParams.pageNum = 1;
          this.getList();
        }
      },
      /** 重置按钮操作 */
      reset(resetData) {
        this.$refs[resetData].resetFields()
        // this.initData()
      },
      /** 新增按钮 */
      handleAdd() {
        this.$router.push({path: '/basicInfo/projectManagement/add/0'});
      },
      /** 编辑按钮 */
      handleUpdate(row) {
        const projectcodes = row.serviceCode;
        const serialNo = row.serialNo;
        this.$router.push( {
        path: '/basicInfo/projectManagement/add/0',
          query: {
            serialNo: serialNo,
            servicecode: projectcodes,
          }
        });
      },
      /** 删除按钮 */
      handleDelete(row) {
        this.$confirm('请问是否删除服务项目名称为"' + row.serviceName + '"的条目?', "删除", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }).then(function () {
          return delProjectInfo(row.serviceCode);
        }).then(() => {
          if(this.projectList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 流程配置按钮  */
      handleFlow(row){
        const projectcode = row.serviceCode;
        this.$router.push('/basicInfo/projectManagement/workflow/' + projectcode);
      },

    }
  };
</script>

<style scoped>
  /*自定义样式*/
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .projectList_div{
    width: 100%;
    margin-top: 10px;
  }

  .queryForm_div {
    width:100%;
  }

  .queryForm_card{
    margin: 0px 10px;
  }

  .projectList__card_div {
    width: 100%;
    position: relative;
    margin-bottom: 33px;
  }

  .projectList_card {
    margin: 10px 10px;
  }

  .queryForm_button {
    text-align: right;
    margin: 0 10px 10px;
  }

  .pages {
    float: right;
    margin: 10px 20px 10px;
  }
</style>
