<template>
  <div class="projectList_div">
    <!--Form查询条件 -->
    <el-card class="queryForm_card">
      <el-form ref="resetData" :model="queryParams" label-width="110px" >
        <el-row>
          <el-col :span="8">
            <el-form-item label="医生姓名" prop="docName">
              <el-input
                v-model="queryParams.docName"
                placeholder="请输入医生姓名"
                clearable
                size="small"
                @keyup.enter.native="initData"
              />
            </el-form-item>

          </el-col>
          <el-col :span="8">
            <el-form-item label="所属服务机构" prop="comment">
              <el-input
                v-model="queryParams.comment"
                placeholder="请录入所属服务机构/科室"
                clearable
                size="small"
                @keyup.enter.native="initData"
              />
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="所属地区：" prop="region">-->
<!--              <el-cascader :options="regions" v-model="region" class="item-width" placeholder="请选择"-->
<!--                           @change="handleChange"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
        </el-row>
        <el-row>
          <el-form-item class="queryForm_button">
            <el-button size="mini" type="success" icon="el-icon-search" @click="initData">查 询</el-button>
            <el-button size="mini" type="primary" icon="el-icon-refresh" @click="reset('resetData')">重 置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </el-card>


    <!-- 卡片、表格 -->
    <div class="projectList__card_div">
      <el-card class="projectList_card">
        <div slot="header">
          <span>医生列表信息</span>
          <span style="float: right;">
            <el-button icon="el-icon-plus" type="primary" size="mini" @click="addDoctor">新增</el-button>
            <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteList">删除</el-button>
          </span>
        </div>
        <el-table :data="doctorList"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  size="mini"
                  tooltip-effect="dark"
                  @selection-change="selectionChange"
                  class="projectList_table">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="医生编码" align="center" prop="docCode" show-overflow-tooltip/>
          <el-table-column label="医生姓名" align="center" prop="docName" show-overflow-tooltip/>
          <el-table-column label="联系电话" align="center" prop="docPhone" show-overflow-tooltip/>
          <el-table-column label="所属服务机构" align="center" prop="docDept" show-overflow-tooltip/>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                style="padding: 0px;"
                @click="editInfo(scope.row)"
              >编辑
              </el-button>
              <el-button
                size="mini"
                type="text"
                @click="deleteOne(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="totalNum>0"
          :total="totalNum"
          :page-sizes="pageSizes"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="changePage"
        />

      </el-card>
    </div>

  </div>

</template>

<script>
  import {getAddress} from '@/api/supplierManager/supplier';
  import {listDoc, delDocList, delDoc, nearDoc} from '@/api/provider/doctor'

  export default {
    name: "doctorList",
    data() {
      return {
        loading: true,
        regions: [], // 省市区下拉选项
        region: [], // 当前选中的省市区  '110000','110100','110105'
        ids: [],
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          docName: undefined,
          comment: undefined,
          province: undefined, // 省
          city: undefined, // 市
          district: undefined, // 区
        },
        doctorList: [],
        // 页码
        totalNum: 0,
        pageSizes: [5,10],
      }
    },
    created() {
      this.getListNear()
      // this.getAddressData()
    },
    methods: {
      // 查询近30天医生信息
      getListNear(){
        this.loading = true;
        nearDoc(this.queryParams).then(res => {
          this.doctorList = res.rows;
          this.totalNum = res.total;
          this.loading = false;
        })
      },
      // 查询医生信息列表
      getList(){
        this.loading = true;
        listDoc(this.queryParams).then(response => {
          this.doctorList = response.rows;
          this.totalNum = response.total;
          this.loading = false;
        })
      },
      // 页码改变
      changePage(){
        if ((this.queryParams.docName == undefined || this.queryParams.docName == undefined) && (this.queryParams.comment == undefined || this.queryParams.comment == "")){
          this.getListNear();
        } else {
          this.getList()
        }
      },
      // 地址下拉选
      getAddressData() {
        getAddress().then(response => {
          this.regions = response
        }).catch(error => {
          console.log(error)
        })
      },
      handleChange(value) {
        this.queryParams.province = value[0]
        this.queryParams.city = value[1]
        this.queryParams.district = value[2]
        console.log("所属区域：",this.queryParams)
      },
      // 查询按钮
      initData(){
        if (this.queryParams.docName == undefined && this.queryParams.comment == undefined){
          this.queryParams.pageNum = 1;
          this.getListNear()
        } else {
          this.queryParams.pageNum = 1;
          // 所属服务机构输入框的值分别赋给第一科室/第二科室
          this.queryParams.fiestDept = this.queryParams.comment;
          this.queryParams.secondDept = this.queryParams.comment;
          console.log("查询参数：",this.queryParams)
          this.getList();
        }
      },
      // 重置按钮
      reset(resetData) {
        this.$refs[resetData].resetFields()
        // this.initData()
      },
      // 新增按钮
      addDoctor(){
        this.$router.push({path: '/basicInfo/doctor/add/0'});
      },
      // 删除多个
      deleteList(){
        if(this.ids.length == 0){
          this.msgError("请先选择一条记录")
        } else {
          const docnos = this.ids;
          this.$confirm('请问是否删除所选条目?', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function () {
            return delDocList(docnos);
          }).then(() => {
            if (this.doctorList.length == 1 && this.queryParams.pageNum != 1) {
              this.queryParams.pageNum = this.queryParams.pageNum - 1;
            }
            this.changePage();
            this.msgSuccess("删除成功");
          }).catch(function () {
          });
        }
      },
      // 多选框选中数据
      selectionChange(selection){
        this.ids = selection.map(item => item.docCode);
        console.log("选中的数据：",this.ids);
      },
      // 编辑按钮
      editInfo(row){
        const docno = row.docCode;
        this.$router.push({path: '/basicInfo/doctor/add/'+docno});
      },
      // 删除单个
      deleteOne(row){
        const docno = row.docCode;
        this.$confirm('是否确认删除编号为"' + docno + '"的数据项?', "删除", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDoc(docno);
        }).then(() => {
          if(this.doctorList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.changePage();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      }
    }
  }
</script>

<style scoped>
  /*自定义样式*/
  .el-form-item ::v-deep label {
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
