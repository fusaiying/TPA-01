<template>
  <div class="app-container">
    <el-card class="box-card">
      <div>
        <div class="tab-title">
          <span>今天待处理量</span>
        </div>
        <div v-for="(item,index1) in authArr" :key="index1">
          <el-row>
            <el-col v-for="(item, i) in inputDate" :span="8" :key="i">
              <el-card :style="{'border-top-color':(item.icon==='err'?'#f00':item.icon==='warn'?'#e6a23c':'#67c23a')}" class="card_box">
                <h4 class="tag">{{ authArr[index1] }}</h4>
                <p class="desc">{{ item.label }}：{{ item.value }}件</p>
                <div :class="{'err-col':item.icon==='err', 'warn-col':item.icon==='warn', 'success-col':item.icon==='success'}" class="tip-icon" >
                  <i v-if="item.icon==='err'" class="el-icon-warning"/>
                  <i v-if="item.icon==='warn'" class="el-icon-alarm-clock"/>
                  <i v-if="item.icon==='success'" class="el-icon-circle-check"/>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>
      <div style="margin-top: 30px;">
        <div class="tab-title">
          <span>案件流转量</span>
          <el-date-picker
            v-model="value1"
            style="margin: 0 10px; width: 240px;"
            size="mini"
            type="daterange"
            value-format="yyyy-MM-DD"
            range-separator="~"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            @change="changeTime"/>
          <el-select v-model="value" placeholder="请选择" size="mini" @change="changeWork">
            <el-option
              v-for="(item,index) in workListArr"
              :key="index"
              :label="item.grouptype + &quot;-&quot; + item.groupnoname"
              :value="item.grouptype + '-' + item.groupnoname"/>
          </el-select>
          <span style="float: right; padding: 20px;">
            <el-button size="small" type="text" @click="linkDetail">详细</el-button>
          </span>
          <div id="lineChart" style="width:100%;height: 400px;"/>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
import echarts from 'echarts'
// import moment from 'moment'
import Cookies from 'js-cookie'
import { getWorkGroupList, lineChart, getPersonalMessage } from '@/api/personalWork/index.js'
export default {
  data() {
    return {
      value1: [],
      value: '',
      inputDate: [
        { label: '超时效', value: 5, icon: 'err' },
        { label: '即将超时效', value: 2, icon: 'warn' },
        { label: '时效内', value: 15, icon: 'success' }
      ],
      workListArr: [],
      xChartArr: [],
      yCharARR: [],
      chartArr: [],
      groupArr: [],
      authArr: []
    }
  },
  mounted() {
    // this.date()
    // this.getMessage()
    // this.getWorkList()
    // this.getLineData()
  },
  methods: {
    getLineData() {
      this.charts = echarts.init(document.getElementById('lineChart'))
      this.charts.setOption({
        legend: {
          data: ['tokyo', 'london']
        },
        grid: {
          top: '4%',
          left: '2%',
          right: '3%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: this.xChartArr
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: this.yCharARR,
          type: 'line'
        }],
        color: ['#3c3', '#f90', '#39f', '#fc0', '#96f']
      })
    },
    linkDetail(row, type) {
      this.$router.push({
        path: '/job-manage/detail',
        query: {
          applicationetime: this.value1[1] || '',
          applicationstime: this.value1[0] || '',
          groupname: this.value.split('-')[1] || '',
          grouptype: this.value.split('-')[0] || ''
        }
      })
    },
    date() {
      const strDate = new Date()
      const endDate = new Date() - (24 * 60 * 60 * 1000)
      this.value1 = [strDate, endDate]
    },
    changeTime(date) {
      // this.getChartData()
    },
    getWorkList() {
      getWorkGroupList().then(res => {
        this.workListArr = res.data || []
        this.groupArr = this.workListArr.filter((item) => {
          return item.username === Cookies.get('username')
        })
        this.value = this.groupArr[0].grouptype + '-' + this.groupArr[0].groupnoname
      })
    },
    getChartData() {
      this.chartArr = []
      this.xChartArr = []
      this.yCharARR = []
      const params = {
        applicationetime: this.value1[1],
        applicationstime: this.value1[0],
        groupname: this.value.split('-')[1] || '',
        grouptype: this.value.split('-')[0] || '',
        pageno: 1,
        pagesize: 10
      }
      lineChart(params).then(res => {
        this.chartArr = res.data.list || []
        this.chartArr.map(item => {
          this.xChartArr.push(item.applicationdate)
        })
        this.chartArr.map(item => {
          this.yCharARR.push(item.accepttotal)
        })
      }).then(() => {
        this.getLineData()
      })
    },
    changeWork(data) {
      this.getChartData()
    },
    getMessage() {
      getPersonalMessage().then(res => {
        const arr = res.data && res.data.operationgroupLimitationVOList && res.data.operationgroupLimitationVOList || []
        this.value = res.data.grouptype + '-' + res.data.groupnoname
        arr.filter((item) => {
          this.authArr.push(item.nodename)
        })
      }).then(() => {
        this.getChartData()
      })
    }
  }
}
</script>
<style scoped>
.card_box {
  position: relative;
  border-top: 5px solid;
  margin: 0 10px;
}
.tag {
  font-size: 18px;
  color: #555;
  margin-top: 0;
}
.tip-icon {
  position: absolute;
  bottom: 20px;
  right: 20px;
  font-size: 40px;
}
.err-col {
  color: #f00;
}
.warn-col {
  color: #e6a23c;
}
.success-col {
  color: #67c23a;
}
.desc {
  font-size: 14px;
  color: #555;
}
</style>
