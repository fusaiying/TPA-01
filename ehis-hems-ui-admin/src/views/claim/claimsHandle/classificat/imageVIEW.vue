<template>
  <div style="margin-bottom: 20px;height: calc(100% - 20px)">
    <el-card shadow="never" style="margin-bottom: 10px;">
      <div slot="header" class="clearfix">
        <span>影像查看</span>
      </div>
      <el-tabs class="tabs-box" :stretch="true" v-model="activeName1" @tab-click="handleClick(activeName1)">
        <el-tab-pane v-for="tab in temp"
          v-loading="serchloading"
          :label="`${tab.label}(${tab.total})`"
          :name="tab.name" :key="tab.name">
          <el-row style="overflow-y: auto;height: 100%">
            <el-col v-for="(url, i) in imageInfo.originallist.list" :span="3" :key="i" class="block">
              <el-image
                :src="'data:image/jpeg;base64,'+ url.src"
                style="width: 110px;height: 110px"
                fit="contain"
                :preview-src-list="originallistPreviewList"
                @click="getoriginallistPreviewList(i,url.pageid,imageInfo.originallist.list,'1')"/>
            </el-col>
          </el-row>
          <!-- <viewer :images="imageInfo.originallist.list2">
            <img v-for="(item,index) in imageInfo.originallist.list" @click="clickImage(activeName1)" :src="'data:image/jpeg;base64,'+item.src" :key="index" :title="item.title" :alt="item.title" style="border:1px solid #f6f6f6;margin-right: 10px;padding:5px" height="100" width="100">
          </viewer> -->
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>
<script>
import {getImageInfo,getAllFileCount,dowloadFileOne} from '@/api/claim/handleDeal'
import {decrypt,encrypt } from "@/utils/rsaEncrypt"
import { preView } from '@/api/claim/fileDeal'
import { downloadData } from '@/utils'
export default {
  data () {
    return {
      claimno: '',
      temp : [],
      loading1: false,
      originallist: {},
      imageInfo: {
        originallist: {
          list : [],
          previewList: [],
          list2: []
        }
      },
      activeName1: '00',
      originallistPreviewList: [''],
      originallistId: '',
      tempList: [],
      serchloading: false,
      top1: 0,
    }
  },
  created() {
     if (this.$route.query) {
      console.log(this.$route.query)
      this.claimno = this.$route.query.claimNo?decrypt(this.$route.query.claimNo):''
     }
     this.getAllFileCount()
     this.handleClick('00')
     this.monitor()
    //  this.clickClose()
  },
  methods : {
    getAllFileCount() {
      getAllFileCount(this.claimno).then(res =>{
        if (res.status == '1') {
          this.temp = res.data.originallist
        }
      })
    },
    handleClick(subtype) {
      this.serchloading = true
      let params = {
        claimno: this.claimno,
        type: '1',
        subtype:subtype == '00' ? null : subtype,
        isClass: 'N',
        pageno: 1,
        pagesize: 1000
      }
      getImageInfo(params).then(response => {
        if (response.status === '1') {
          if(params.type==='1'){
            this.imageInfo.originallist.list = []
            this.imageInfo.originallist.previewList = []
            if(response.data){
              response.data.list.map((item, i) => {
                this.imageInfo.originallist.list.push(item)
                this.imageInfo.originallist.previewList.push('data:image/jpeg;base64,'+ item.src)
              })
            }
          }
          this.serchloading = false
        } else {
          this.$message({ message: '查询影像文件错误！', type: 'error' })
          this.serchloading = false
        }
      }).finally(() => {
        this.serchloading = false
      })
    },
    monitor(){
      var that = this;
      document.onkeydown = function(e) {
        let key = window.event.keyCode;
        // 监听键盘左键
        if (key == 37) {
          that.pre(that.tempList)
        } else if(key == 39) {  // 监听键盘右键
          that.next(that.tempList)
        }
      };
    },
    generateLabel(name) {
      let buttonBox = document.getElementsByClassName('el-image-viewer__actions__inner')[0]
      let newEle = document.createElement('i')
      newEle.className = name
      buttonBox.appendChild(newEle)
    },
    appendDel(data,index) {
      this.originallistId = index
      let _that = this
      // 第一次打开预览时生成
      this.$nextTick(function() {
        if (document.getElementsByClassName('has').length === 0) {
          this.generateLabel('el-icon-arrow-left has')
          this.generateLabel('el-icon-arrow-right has')
          this.generateLabel('el-icon-download has')
        }
        var element = document.getElementsByClassName('el-image-viewer__btn el-image-viewer__close')[0]
        let _this = this
        element.onclick = function next() {
          _this.stopBodyScroll(false)
        }
        // 左
        let left = document.getElementsByClassName('el-icon-arrow-left has')[0] // 左
        left.onclick = function pre() {
          if (_that.originallistId == 0) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId -1
          }
          let id = data[_that.originallistId].pageid
          preView(id).then(response => {
            // _that.originallistPreviewList = []
            // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
            _that.originallistPreviewList = ['data:image/jpeg;base64,'+ response.data]
            _that.originallistPreviewList = [..._that.originallistPreviewList]
          })
        }

        // 右
        let right = document.getElementsByClassName('el-icon-arrow-right has')[0]// 右
        right.onclick = function next() {
          if (_that.originallistId == data.length-1) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId + 1
          }
          let id = data[_that.originallistId].pageid
          preView(id).then(response => {
            // _that.originallistPreviewList = []
            // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
            _that.originallistPreviewList = ['data:image/jpeg;base64,'+ response.data]
            _that.originallistPreviewList = [..._that.originallistPreviewList]
          })
        }

        //下载
        let download = document.getElementsByClassName('el-icon-download has')[0]// 下载
        download.onclick =function () {
          let id = data[_that.originallistId].pageid
          if (_that.originallistId == 0) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId -1
          }
          dowloadFileOne(id).then(response => {
            downloadData(response,data[_that.originallistId].name)
          })
        }
      })
    },
    pre () {
      let _that = this
      if (_that.originallistId == 0) {
        _that.originallistId = _that.originallistId
      } else {
        _that.originallistId = _that.originallistId -1
      }
      let id = _that.tempList[_that.originallistId].pageid
      preView(id).then(response => {
        _that.originallistPreviewList = []
        _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        _that.originallistPreviewList = [..._that.originallistPreviewList]
      })
    },
    next () {
      let _that = this
      if (_that.originallistId == _that.tempList.length-1) {
        _that.originallistId = _that.originallistId + 1
      } else {
        _that.originallistId = _that.originallistId + 1
      }
      let id = _that.tempList[_that.originallistId].pageid
      preView(id).then(response => {
        _that.originallistPreviewList = []
        _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        _that.originallistPreviewList = [..._that.originallistPreviewList]
      })
    },
    getoriginallistPreviewList(index,id,data,type) {
      let list = []
      list = data
      this.tempList = data
      this.originallistPreviewList = []
      let params = {
        pageid: id
      }
      preView(id).then(response => {
        this.originallistPreviewList = []
        this.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        this.$nextTick(() =>{
          this.appendDel(this.tempList,index)
        })
      })
      this.stopBodyScroll(true)
    },
    stopBodyScroll (isFixed) {
      var bodyEl = document.body
      if (isFixed) {
        this.top1 = window.scrollY
        bodyEl.style.position = 'fixed'
        bodyEl.style.top = -this.top1 + 'px'
        bodyEl.style.width = '100%'
      } else {
        bodyEl.style.position = ''
        bodyEl.style.top = ''
        window.scrollTo(0, this.top1) // 回到原先的top
        bodyEl.style.width = '100%'
      }
    },
  },
}
</script>
<style scoped>
.el-tabs ::v-deep .el-tabs__item {
  width: 10.5%;
}
.el-collapse ::v-deep .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
.block {
  border: 1px solid #f6f6f6;
  width: 110px;
  height: 110px;
  text-align:center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
}
.block .el-image {
  margin: 0 auto;
}
</style>
