<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <el-form ref="queryParams" :model="queryParams" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>

          <el-col :span="8">
            <el-form-item label="产品编码：" prop="organcode">
              <el-input v-model="queryParams.organcode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态：" prop="hospitalname">
              <el-select v-model="queryParams.hospitalname" class="item-width" placeholder="请选择">
                <el-option key="01" label="门诊费用"
                           value="01"/>
                <el-option key="02" label="住院"
                           value="02"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="同步日期：" prop="submitdate">
              <el-date-picker
                v-model="queryParams.submitdate"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品名称：" prop="organcode">
              <el-input v-model="queryParams.organcode" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作人：" prop="claimtype">
              <el-select v-model="queryParams.claimtype" class="item-width" placeholder="请选择">
                <el-option key="01" label="张三"
                           value="01"/>
                <el-option key="02" label="李四"
                           value="02"/>
                <el-option key="03" label="admin"
                           value="03"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作日期：" prop="updateTime">
              <el-date-picker
                v-model="queryParams.updateTime"
                class="item-width"
                type="daterange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
              isinit='N',
              page=1,
              finishPage=1,
              searchHandle()
            "
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <!--<el-divider/>-->
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>工作池（{{ totalCount }}）</span>
        <el-divider/>
        <claimsTable :table-data="workPoolData"/>
        <pagination
          :total="totalCount"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'

  export default {
    components: {
      claimsTable
    },
    data() {
      return {
        caseNumber: false,//查询条件（报案号）是否显示
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        loading: true,
        workPoolData: [],
        isinit: 'Y',
        page: 1,
        totalCount: 0,
        finishPageSize: 10,
        pageSizes: [5, 10, 20, 30, 50, 100],
        changeSerchData: {},
      }
    },
    created() {
    },
    mounted() {


    },
    methods: {
      resetForm() {
        this.$refs.queryParams.resetFields()
      },
      searchHandle() {

      },
      send() {

      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }
</style>
