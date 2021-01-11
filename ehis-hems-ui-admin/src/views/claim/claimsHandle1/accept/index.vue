<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>查询条件</span>
      </div>
      <el-form ref="searchForm" :model="searchForm" style="padding-bottom: 30px;" label-width="100px"
               label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="赔案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报案号：" prop="claimno">
              <el-input v-model="searchForm.claimno" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="被保人姓名：" prop="name">
              <el-input v-model="searchForm.name" class="item-width" clearable size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="text-align: right; margin-right: 10px;">
          <el-button
            size="mini"
            type="success"
            icon="el-icon-search"
            @click="
            searchHandle()"
          >查询
          </el-button>
          <el-button size="mini" type="primary" @click="resetForm">重置</el-button>
        </div>
      </el-form>
      <el-divider/>
      <div>
        <span>个人池</span>
        <span style="float: right;">
            <el-button type="primary" size="mini" @click="listExport">清单导出</el-button>
        </span>
        <el-divider/>
      </div>
      <div style="position: relative; margin-top: 30px;">
        <el-tabs v-model="activeName">
          <el-tab-pane :label="`处理中(${total})`" name="01">
            <claimsTable :table-data="pendingTableData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`已处理(${finishTotal})`" name="02">
            <claimsTable @init-data="searchHandle" :table-data="completedTableData" :status="activeName"/>
          </el-tab-pane>
          <el-tab-pane :label="`悬挂中(${hangUpTotal})`" name="03">
            <claimsTable :tableData="hangUpTableData" :status="activeName"></claimsTable>
          </el-tab-pane>
        </el-tabs>
        <!--分页组件-->
        <pagination
          v-if="activeName==='01'"
          v-show="total>0"
          :total="total"
          :page.sync="backNum"
          :limit.sync="backSize"
          @pagination="searchHandle"
        />
        <pagination
          v-if="activeName==='02'"
          v-show="finishTotal>0"
          :total="finishTotal"
          :page.sync="dealNum"
          :limit.sync="dealSize"
          @pagination="searchHandle"
        />
        <pagination
          v-if="activeName==='03'"
          v-show="hangUpTotal>0"
          :total="hangUpTotal"
          :page.sync="hangUpNum"
          :limit.sync="hangUpSize"
          @pagination="searchHandle"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import claimsTable from './components/claimsTable'

  export default {
    name: 'Accecpt',
    components: {
      claimsTable
    },
    data() {
      return {
        total: 1,
        finishTotal: 2,
        hangUpTotal: 3,
        backNum: 1,
        backSize: 10,
        dealNum: 1,
        dealSize: 10,
        hangUpNum:1,
        hangUpSize:10,
        activeName: '01',
        completedTableData: [{
          claimno:'0001'
        }],
        pendingTableData: [{
          claimno:'0001'
        }],
        hangUpTableData: [{
          claimno:'0001'
        }],
        searchForm: {
          pageNum: 1,
          pageSize: 10,
          claimno: null,
          applicationsource: null,
          eventdate: null,
          name: null,
          idcardno: null,
          acceptDate: null
        },
      }
    },
    mounted() {

    },
    computed: {},
    methods: {
      resetForm() {
        this.$refs.searchForm.resetFields()
      },
      // 查询
      searchHandle() {
        this.searchForm.pageNum= this.activeName==='01'?this.backNum:this.activeName==='02'?this.dealNum:this.hangUpNum
        this.searchForm.pageSize= this.activeName==='01'?this.backSize:this.activeName==='02'?this.dealSize:this.hangUpSize
        if (this.activeName==='01'){//处理中

        }else if (this.activeName==='02'){//已处理

        }else {//悬挂中

        }

      },
      //清单导出
      listExport() {

      }
    }
  }
</script>

<style scoped>
  .item-width {
    width: 220px;
  }

  #print-iframe {
    position: absolute;
    width: 0px;
    height: 0px;
    left: -500px;
    top: -500px;
  }
</style>
