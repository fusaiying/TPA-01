<template>
  <el-card v-loading="loadTimeline" v-show="timelineInfo.length" class="box-card" style="margin: 10px 0;">
    <div>
      <div v-for="(item, i) in timelineInfo" :key="i">
        <el-row style="width:100%;font-size:12px;padding:5px 0">
          <i style="background-color: #00FF00"/>
          <i
            :style="{background:((i==timelineInfo.length-1&&item.closecase!=='17'&&item.closecase!=='18'&&item.closecase!=='19'&&item.closecase!=='20'&&item.closecase!=='21'&&item.closecase!=='23')?'#88741a':'#67C23A')}"
            class="icon-dot"/>
          <span v-if="(item.node).indexOf('C') !== -1"
                style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">{{getTasknodeOptions(item.node) }}-<span
            :style="item.conclution==='02' ? 'color:red;' : 'color:limegreen;'">{{getExamine_conclusion(item.conclution)}}</span></span>
          <span v-else style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">{{ item.node }}</span>
          <el-tooltip class="item" effect="dark" :content="item.explanation" placement="top-start">
            <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;max-width:400px"
                  v-if="item.explanation"><span style="color:#409eff;">说明-</span>{{ item.explanation }}</span>
          </el-tooltip>
          <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"
                v-if="item.operator"><span style="color:#409eff;">操作人-</span>{{ item.operator }}</span>
          <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"
                class="time-stamp" v-if="item.completedate"><span style="color:#409eff;">时间-</span>{{ item.completedate }}</span>
          <el-tooltip class="item" effect="dark" :content="item.remarks" placement="top-start">
            <span
              style="max-width:400px;margin-left:10px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap"
              v-if="item.remarks"><span style="color:#409eff;">备注-</span>{{item.remarks}}</span>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" :content="item.holdtype" placement="top-start">
            <span
              style="max-width:400px;margin-left:10px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"
              v-if="item.holdtype"><span style="color:#409eff;">子任务-</span>{{item.holdtype}}</span>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" :content="item.holdreason" placement="top-start">
            <span
              style="max-width:400px;margin-left:10px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"
              v-if="item.holdreason"><span style="color:#409eff;">悬挂-</span>{{item.holdreason}}</span>
          </el-tooltip>
        </el-row>
        <!-- <el-row v-else-if="i===timelineInfo.length-1&&status!=='show'" style="width:100%;font-size:12px;padding:5px 0">
          <i :style="{background:((i==timelineInfo.length-1&&item.closecase!=='17')?'#88741a':'#67C23A')}" class="icon-dot"/>
          <span style="display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" v-if="i!==timelineInfo.length-1">{{ dict.label.tasknode[item.node] }}-<span :style="item.conclution==='02' ? 'color:red;' : 'color:limegreen;'">{{ dict.label.examine_conclusion[item.conclution] }}</span></span>
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
          <el-tooltip class="item" effect="dark" :content="item.holdreason" placement="top-start">
            <span style="max-width:400px;margin-left:10px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" v-if="item.holdreason"><span style="color:#409eff;">悬挂-</span>{{item.holdreason}}</span>
          </el-tooltip>
        </el-row> -->
      </div>
    </div>
  </el-card>
</template>

<script>
  import {caseProcessLog} from '@/api/claim/handleDeal'

  export default {
    // status
    props: {
      dictList: {
        type: Array
      },
      claimno: {
        type: String,
        default: function () {
          return ''
        }
      },
      status: {
        type: String,
        default: function () {
          return 'handle'
        }
      }
    },
   // dicts: ['tasknode', 'examine_conclusion'],
    created() {
      this.caseSchedule()
    },
    data() {
      return {
        loadTimeline: false,
        timelineInfo: [],
        tasknodeOptions: [],
        examine_conclusionOptions: []
      }
    },
    mounted() {
      if (this.dictList!=null && this.dictList.length!=0) {
        this.tasknodeOptions = this.dictList.find(item => {
          return item.dictType == 'tasknode'
        }).dictDate
        this.examine_conclusionOptions = this.dictList.find(item => {
          return item.dictType == 'examine_conclusion'
        }).dictDate
      }
    },
    methods: {
      // 时间轴
      caseSchedule() {
        this.loadTimeline = true
        caseProcessLog(this.claimno).then(response => {
          if (response.status === '1') {
            this.timelineInfo = response.data
          } else {
            this.$message({message: '查询操作轨迹错误！', type: 'error'})
          }
        }).finally(() => {
          this.loadTimeline = false
        })
      },
      getTasknodeOptions(data) {
        return this.selectDictLabel(this.tasknodeOptions, data)
      },
      getExamine_conclusion(data) {
        return this.selectDictLabel(this.examine_conclusionOptions, data)
      }
    }
  }
</script>

<style scoped>

</style>
<style scoped>
  .el-tooltip__popper {
    max-width: 80%;
  }

  .flex-item {
    width: 100%;
  }
</style>
