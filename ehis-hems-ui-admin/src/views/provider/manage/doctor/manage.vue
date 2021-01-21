<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">

      <!-- card1 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-grid mr5"></i>
            <span>网点列表</span>
          </div>

          <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55" align="center">
            </el-table-column>

            <el-table-column
              label="医生编码"
              width="200">
              <template slot-scope="scope">{{ scope.row.netCode }}</template>
            </el-table-column>
            <el-table-column
              label="医生姓名"
              width="200" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netName }}</template>
            </el-table-column>
            <el-table-column
              label="联系人"
              width="200" show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netLinkMan }}</template>
            </el-table-column>

            <el-table-column
              label="联系电话"
              width="200">
              <template slot-scope="scope">{{ scope.row.netLinkPhone }}</template>
            </el-table-column>

            <el-table-column
              label="联系邮箱"
              width="200">
              <template slot-scope="scope">{{ scope.row.netLinkMail }}</template>
            </el-table-column>

            <el-table-column
              label="工作时间"
              show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.netWorkTime }}</template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="100>0"
            :total="total"
            :page-size="10"
          />
        </el-card>
      </el-row>
      <!-- card2 -->
      <el-row class="mt10 mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-s-custom mr5"></i>
            <span>医生信息</span>
          </div>

          <!-- 第一行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="医生编码" prop="servcomno">
                <el-input disabled v-model="form.doctorNo" clearable/>
              </el-form-item>
            </el-col>

            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="所属机构名称">
                <el-input disabled v-model="form.chname" clearable/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="所属网点名称" prop="netName">
                <el-input v-model="form.netName" disabled clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第二行 -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="所属科室名称">
                <el-input disabled v-model="form.roomNo" clearable/>
              </el-form-item>
            </el-col>

            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="医生姓名" prop="docName">
                <el-input clearable v-model="form.docName"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <!-- 医生性别 -->
              <el-form-item label="医生性别">
                <el-select
                  filterable
                  placeholder="请选择医生性别" v-model="form.sex">
                  <el-option
                    v-for="dict in sex"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' + dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>


          <!-- 第三行 -->
          <el-row>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系人" prop="concatName">
                <el-input v-model="form.concatName" placeholder="请输入联系人" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系电话" prop="concatPhone">
                <el-input v-model="form.concatPhone" placeholder="请输入联系电话" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="联系邮箱" prop="contactEmail">
                <el-input v-model="form.contactEmail" placeholder="请输入联系邮箱" clearable/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 第四行 -->
          <el-row>
            <el-col :span="12" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="备注">
                <el-input type="textarea" v-model="form.netRemark"></el-input>
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
  import {listProvider, getProvider, delProvider, addProvider, updateProvider} from "@/api/provider/provider";

  export default {
    name: "manage",
    data() {
      return {
        // 总条数
        total: 5,
        // 模拟表格数据
        tableData: [
          {
            netCode: 'H000001',
            netName: '张三',
            netLinkMan: '张三1',
            netLinkPhone: '15566936655',
            netLinkMail: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'H000002',
            netName: '张三',
            netLinkMan: '张三1',
            netLinkPhone: '15566936655',
            netLinkMail: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'H000003',
            netName: '王五',
            netLinkMan: '王五1',
            netLinkPhone: '15566936655',
            netLinkMail: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
          {
            netCode: 'H000004',
            netName: '赵六',
            netLinkMan: '赵六1',
            netLinkPhone: '15566936655',
            netLinkMail: 'HongKon19g@qq.com',
            netWorkTime: 45
          },
        ],
        rules: {
          docName: [
            {required: true, message: '医生姓名必填', trigger: 'blur'},
          ],
        },
        // 性别
        sex: [],
        // 选中复选框数组
        ids: [],
        // 遮罩层
        loading: false,
        form: {},
      }
    },
    created() {
      // 获取机构编码
      const servcomno = this.$route.params && this.$route.params.servcomno;
      if (servcomno != undefined) {
        this.getProviderByServcomno(servcomno);
      };

      // 获取数据字典 性别
      this.getDicts("sys_user_sex").then(response => {
        this.sex = response.data;
      });
    },
    methods: {
      /** 根据ID获取Provider */
      getProviderByServcomno(servcomno) {
        getProvider(servcomno).then(response => {
          // 往修改页面带数据
          this.form = response.data;
        });
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.netCode)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      // 提交
      submitForm() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            // 如果机构编码不为空 那么就是修改
            if (this.form.servcomno != null) {
              // updateProvider(this.form).then(response => {
              //   if (response.code === 200) {
              //     this.msgSuccess("修改成功");
              //   }
              // });
              this.msgSuccess("修改成功");
            } else {
              addProvider(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                }
              });
            }
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          };
        });
      },
      /**
       * 重置按钮
       */
      reset() {
        // 获取机构编码
        const servcomno = this.$route.params && this.$route.params.servcomno;
        // 只有在机构编码存在的情况下（也就是编辑的时候），重置需要重新查询一下
        if (servcomno != undefined) {
          this.getProviderByServcomno(servcomno);
        } else {
          // this.form = {servcomtype: '01', states: 'Asia'};
        };
        this.$message({
          message: '已重置',
          type: 'success'
        });

      },

      /** 删除按钮操作 */
      delRow(row) {
        const netCode = row.netCode || this.ids;
        if(netCode == '') {
          this.$message.error('请先选中再进行删除');
          return;
        }
        this.$confirm('是否确认删除医生编号为"' + netCode + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          // return delProvider(servcomnos);
        }).then(() => {
          // this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },

      // 返回按钮
      back() {
        // 关闭本标签页
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: '/provider/manage/doctor'});
      },

    },
  }
</script>

<style scoped>

</style>
