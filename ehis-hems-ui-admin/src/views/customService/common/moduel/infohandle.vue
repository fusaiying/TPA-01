<template>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>服务处理</span>
      </div>
      <el-form>
        <el-row>
          <el-input label="业务处理情况：" type="text" v-model="infohandle.status"/>
        </el-row>
        <el-row>
          <el-input label="处理说明：" type="textarea" :row="2" v-model="infohandle.discription"/>
        </el-row>
      </el-form>
    </el-card>
</template>

<script>
export default {
  name: "infohandle",
  props: {
    infohandle: Object
  },
  data(){
    return {
      status:'',
      discription:'',
    }
  },
  created() {

  },
  methods: {

  }
}
</script>

<style scoped>
  .el-tooltip__popper {
    max-width: 400px;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>
