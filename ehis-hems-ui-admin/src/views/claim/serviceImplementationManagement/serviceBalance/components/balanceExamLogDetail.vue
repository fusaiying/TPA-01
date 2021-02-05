<!-- 日志 -->
<template>
  <div style="height: 200px;overflow:auto;" id="balanceExamLogDetail" v-show="params.isShow">
    <el-steps align-center direction="vertical" :active="active" v-loading="loading">
      <el-step :key="step.index" :title="step.remark" :description="step.createTime + ' 处理人：' + step.createBy" v-for="(step,index) in steps"/>
    </el-steps>
  </div>
</template>

<script>

import {listBalanceExamLog} from "@/api/claim/serviceBalance";

export default {
  name: "balanceExamLogDetail",
  props: {
    params: {
      type: Object,
      required: true,
      default: function () {
        return {
          //是否展示
          isShow: false,
          //任务号
          taskNo: null
        }
      }
    },
    //字典数组
    dictList: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  watch: {
    balanceExamLog: {
      immediate: true,
      handler: function(newVal) {
        this.initData();
      }
    },
    dictList: function (newVal, oldVal) {
      this.initDictData();
    }
  },
  data() {
    return {
      // 遮罩层
      loading: false,
      //当前激活
      active: 0,
      //步骤数组
      steps: []
    };
  },
  created() {

  },
  async mounted() {

  },
  methods: {
    /** 初始化数据 */
    initData() {
      if (this.params.isShow) {
        this.loading = true;
        this.active = 0;
        this.steps = [];
        const params = {
          taskNo: this.params.taskNo
        };
        listBalanceExamLog(params).then(res => {
          if (res != null && res.code === 200) {
            this.steps = res.rows;
            if (res.rows.length > 0) {
              this.active = res.rows.length - 1;
            }
            this.loading = false;
          }
        }).catch(res => {
          this.loading = false
        });
      }
    },
    /** 初始化字典 */
    initDictData() {
      if (this.dictList != null && this.dictList.length != 0) {

      }
    }
  }
};
</script>

<style scoped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
/*自定义样式*/
.receive_table {
  width: 98%;
  padding: 0px;
  margin-left: 1%;
  margin-right: 1%;
}

</style>
