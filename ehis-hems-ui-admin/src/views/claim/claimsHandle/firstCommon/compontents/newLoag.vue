<template>
  <el-card v-loading="loadTimeline" v-show="timelineInfo.length" class="box-card" style="margin: 10px 0;">
    <div>
      <div v-for="(item, i) in timelineInfo" :key="i">
        <el-row style="width:100%;font-size:12px;padding:5px 0">
          <i :style="{background:((i==timelineInfo.length-1&&item.closecase!=='17')?'#88741a':'#67C23A')}" class="icon-dot"/>
          <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" v-if="i!==timelineInfo.length-1">{{ /*dict.label.tasknode[item.node]*/ getExamine_conclusionOptions(item.node) }}-<span :style="item.conclution==='02' ? 'color:red;' : 'color:limegreen;'">{{ /*dict.label.examine_conclusion[item.conclution]*/ getExamine_conclusionOptions(item.conclution)}}</span></span>
          <!-- <span v-if="status==='show'">{{ item.node }}{{item.conclution?'-':''}}{{ dict.label.examine_conclusion[item.conclution] }}</span> -->
          <!-- <span v-else>{{ item.node }}</span> -->
          <el-tooltip class="item" effect="dark" :content="item.explanation" placement="top-start">
            <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;max-width:400px" v-if="item.explanation"><span style="color:#409eff;">说明-</span>{{ item.explanation }}</span>
          </el-tooltip>
          <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" v-if="item.operator"><span style="color:#409eff;">操作人-</span>{{ item.operator }}</span>
          <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" class="time-stamp" v-if="item.completedate"><span style="color:#409eff;">时间-</span>{{ item.completedate }}</span>
          <el-tooltip class="item" effect="dark" :content="item.remarks" placement="top-start">
            <span style="max-width:400px;margin-left:10px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap" v-if="item.remarks"><span style="color:#409eff;">备注-</span>{{item.remarks}}</span>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" :content="item.holdtype" placement="top-start">
            <span style="max-width:400px;margin-left:10px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" v-if="item.holdtype"><span style="color:#409eff;">子任务-</span>{{item.holdtype}}</span>
          </el-tooltip>
        </el-row>
      </div>
    </div>
  </el-card>
</template>
<script>
export default {
  //dicts:['tasknode','examine_conclusion'],
  props: {
    dictList:Array,
    timelineInfo: Array,
    status: String,
  },
  data () {
    return {
      loadTimeline: false,
      // timelineInfo: []
     /* 'tasknode','examine_conclusion'*/
      tasknodeOptions: [],
      examine_conclusionOptions: []
    }
  },
  mounted() {
    if (this.dictList != null && this.dictList.length != 0) {
      this.tasknodeOptions = this.dictList.find(item => {
        return item.dictType == 'tasknode'
      }).dictDate
      this.examine_conclusionOptions = this.dictList.find(item => {
        return item.dictType == 'examine_conclusion'
      }).dictDate
    }
  },
  methods: {
    getTasknodeOptions(data) {
      return this.selectDictLabel(this.tasknodeOptions, data)
    },
    getExamine_conclusionOptions(data) {
      this.selectDictLabel(this.examine_conclusionOptions,data)
    }
  }
}
</script>
<style scoped>
.flex-item {
  width: 100%;
}
</style>
