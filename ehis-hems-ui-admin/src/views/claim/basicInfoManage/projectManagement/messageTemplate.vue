<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>消息模板</span>
      </div>
      <el-table :data="messageList"
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="mini"
                tooltip-effect="dark"
                class="projectList_table"
                @selection-change="">
        <el-table-column type="selection" align="center"/>
        <el-table-column label="模板名称" align="center" prop="name" show-overflow-tooltip/>
        <el-table-column label="模板类型" align="center" prop="type" show-overflow-tooltip/>
        <el-table-column label="发送对象" align="center" prop="object" show-overflow-tooltip/>
        <el-table-column label="发送内容" align="center" prop="content" show-overflow-tooltip/>
      </el-table>
      <el-row >
        <el-row :gutter="10" class="mt20" style="text-align: right;">
          <el-button
            size="mini"
            @click="close"
          >关闭</el-button>
        </el-row>
      </el-row>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "messageTemplate",
    data(){
      return{
        // 流程配置页面数据
        paramsData: {},
        messageList: [],
      }
    },
    created() {
      this.paramsData = this.$route.query;
      console.log("参数：",this.paramsData);
    },
    methods: {
      // 关闭按钮
      close(){
        this.$store.dispatch("tagsView/delView", this.$route);
        // this.$router.go(-1);
        this.$router.push({
          path: '/basic-info/projectManagement/workflow',
          query: {
            // 从消息模板跳转到流程配置页面
            flag: false,
            paramsData: this.paramsData
          }
        });
      }
    }
  }
</script>
