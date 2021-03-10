<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>服务处理</span>
    </div>
    <el-form ref="ruleForm" :model="form" style="padding-bottom: 30px;" label-width="100px"
             label-position="right" size="mini">
      <el-table>
        <el-table-column>
          <el-table-column align="center" width="140" prop="status" label="质检项目" show-overflow-tooltip/>
          <el-table-column align="center" prop="operateCode" label="是否存在差错" show-overflow-tooltip/>
          <el-table-column align="center" prop="makeBy" label="质检说明" show-overflow-tooltip/>
        </el-table-column>
        <el-row>
          <el-input placeholder="是否时效内响应客户:" />
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row>
          <el-input placeholder="是否符合短信结案规则:" />
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row>
          <el-input placeholder="是否电话通知客户:" />
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row>
          <el-input placeholder="是否满足客户诉求:" />
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row>
          <el-input placeholder="是否及时升级投诉:" />
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row>
          <el-input placeholder="是否是否规范记录:" />
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row>
          <el-input placeholder="是否有其他错误:"/>
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input />
        </el-row>
        <el-row></el-row>
        <el-row>
          <el-input placeholder="是否申诉:"/>
          <el-select v-model="form.status" class="item-width" placeholder="请选择">
            <el-option
              v-for="item in status"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
          <el-input label="申诉原因:"/>
        </el-row>
      </el-table>
      <el-row gutter="20" style="float: right" >
        <el-button @click="">结案</el-button>
        <el-button @click="">案件复核</el-button>
      </el-row>
    </el-form>
  </el-card>
</template>

<script>
  export default {
    name: "inspectionProcessInfo",
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





