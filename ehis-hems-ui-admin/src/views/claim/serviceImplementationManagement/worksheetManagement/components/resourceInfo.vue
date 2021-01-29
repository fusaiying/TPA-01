<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">资料信息</span>
          <span v-show="!activeNames.length"
                style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;">
          <el-button type="primary" size="mini" :loading="downloadFileRarLoading"
                     @click="downloadFileRar">打包下载</el-button>
        </span>
        <el-card shadow="never" style="margin-bottom: 10px;" >
          <el-tabs   v-model="activeName1" @tab-click="handleClick('1', activeName1)">
            <el-tab-pane label="分类" disabled></el-tab-pane>
            <el-tab-pane v-for="tab in originallist"
                         :label="`${tab.label}(${tab.total})`"
                         v-loading="loading1"
                         :name="tab.name" :key="tab.name">
<!--                            <el-row v-if="imageInfo.originallist.list.length && activeName1===tab.name"-->
<!--                                    :id="'image' + tab.name + '1'" ref="image1"-->
<!--                                    class="imgList_div"-->
<!--                                    @scroll.native="getScrollTop('1', page0, tab.total, tab.name)">-->
<!--                              <el-col v-for="(url, i) in imageInfo.originallist.list" :span="3" :key="i" class="block">-->
<!--                                <div  class="my-image-wrapper">-->
<!--                                  <el-image-->
<!--                                    :src="'data:image/jpeg;base64,'+ url.src"-->
<!--                                    style="width: 96%;height:160px"-->
<!--                                    fit="fill"-->
<!--                                    :preview-src-list="originallistPreviewList"-->
<!--                                    @click="getoriginallistPreviewList(i,url.pageid,imageInfo.originallist.list,'1')"/>-->
<!--                                  &lt;!&ndash;删除&ndash;&gt;-->
<!--                                  <el-button-->
<!--                                    type="text"-->
<!--                                    style="position: absolute;top: 2px;right: 0;padding: 0"-->
<!--                                    @click="delImageHandle(url)">-->
<!--                                    <i class="el-icon-circle-close" style="font-size:16px;color: red;opacity: .5;"></i>-->
<!--                                  </el-button>-->
<!--&lt;!&ndash;                                  <el-checkbox-->
<!--                                    :disabled="status==='show'?true:acceptFlag || disableFlag"-->
<!--                                    :label="url"-->
<!--                                    ref="imageCkeck"-->
<!--                                    style="position:absolute;bottom: -2px;right: -8px;"-->
<!--                                    text-color="#fff" fill="#fff">{{ }}</el-checkbox>&ndash;&gt;-->
<!--                                </div>-->
<!--                              </el-col>-->
<!--                              <el-col  :span="24" style="text-align: center">没有更多了</el-col>-->
<!--                            </el-row>-->
            </el-tab-pane>
          </el-tabs>
        </el-card>

      </el-collapse-item>
    </el-collapse>

  </el-card>
</template>
<script>

import {downloadData} from '@/utils'
import {dowloadFileRar, claimDelete, getImageInfo, dowloadFileOne} from '@/api/claim/handleDeal'

import {encrypt} from "@/utils/rsaEncrypt"
import {preView} from '@/api/claim/fileDeal'

//import images from '../../../claimsHandle/common/mixins/images'
import {toClient} from '@/utils/commenMethods.js'
import ElImageViewer from 'element-ui/packages/image/src/image-viewer'
import Bus from '../../../claimsHandle1/bus/bus'

export default {
 // inject: ['getImageInfo'],
 // mixins: [images],
  components: {
    // uploadModul,
    ElImageViewer
  },
  props: {
    //客户姓名
    claimno: String,
  },
  /* props: {
     status: String,
     node: String,
     imageLoad: Boolean,
     claimno: String,
     imageInfo: {
       type: Object,
       default: () => {
         return {}
       }
     },
     tabLabels:  {
       type: Object,
       default: () => {
         return {}
       }
     },
     acceptFlag: {
       type: Boolean,
       default: false
     },
     disableFlag: Boolean
   },*/
  data() {
    return {
      //父组件传来的图像数据

      imageInfo: {originallist: []},

//父组件传来的数据
        originallist: [{
          label: '全部',
          name: '9',
          total: 8
        },{
          label: '其他证明材料',
          name: '11',
          total: 1
        },
          {
            label: '住院单',
            name: '111',
            total: 7
          }],

      activeNames: ['1'],
      downloadFileRarLoading: false,
      loading1: false,
      activeName1: '00',
      activeName:'',
      page0: 1,
      originallistPreviewList: [''],
      tempList: [],
      originallistId: '',
      currentUser: '',
    }
  },

  watch: {
  },
  created() {
    //获取当前操作人
    this.currentUser = toClient()
    this.monitor()
    // this.clickClose()
  },
  mounted() {

  },
  updated() {

  },
  methods: {
    //打包下载
    downloadFileRar() {
      this.downloadFileRarLoading = true
      dowloadFileRar(this.claimno).then(res => {
        downloadData(res, this.claimno + '.rar')
        this.downloadFileRarLoading = false
      })
    },
    //标签页更换
    handleClick(catalog, subtype) {
      this.page0 = 1
      let sub = null
      if (subtype !== '00') {
        sub = subtype
      }
      this.loadtype = catalog
      this.getImageInfo(catalog, 1, sub, true)  //???
      // if (this.activeName !== subtype) {
      this.changeList(catalog)
      // }
      this.activeName = subtype
    },
    //请求接口 得到图像信息
    changeList(catalog) {
      this.page0 = 1
      this.load1 = false
      this.$emit('changeList', catalog)
    },

    getScrollTop(type, page, total, name) {
      const scrollTop = document.getElementById('image' + name + type).scrollTop
      const scrollHeight = document.getElementById('image' + name + type).scrollHeight
      const offsetHeight = document.getElementById('image' + name + type).offsetHeight
      if ((offsetHeight + scrollTop) - scrollHeight === 0) {
        this.load(type, page, total, name)
      }
    },
    load(type, page, total) {
      if (type === '1') {
        if (this.page0 < Math.ceil(total / 5)) {
          this.page0++
          this.type = type
          this.loadMore('1', this.page0, this.activeName1)
          this.load1 = false
        } else {
          this.load1 = true
        }
      }

    },
    loadMore(type, page, subType) {
      if (subType === '00') {
        subType = ''
      }
      this.getImageInfo(type, page, subType)
    },
    //文件预览
    getoriginallistPreviewList(index, id, data, type) {
      let list = []
      list = data
      this.tempList = data
      this.originallistPreviewList = []
      let params = {
        pageid: id
      }
      //文件预览
      preView(id).then(response => {
        this.originallistPreviewList = ['data:image/jpeg;base64,' + response.data]
        // this.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
        this.$nextTick(() => {
          this.appendDel(this.tempList, index, id)
        })
      })
      this.stopBodyScroll(true)
    },
    appendDel(data, index, pageid) {
      this.originallistId = index
      let _that = this
      // 第一次打开预览时生成
      this.$nextTick(function () {
        if (document.getElementsByClassName('has').length === 0) {
   /*       if (this.node === 'accept' || this.node === 'report') {
            if (this.status === 'handle' ? !this.acceptFlag && !this.disableFlag : false) {
              this.generateLabel('el-icon-delete has')
            }
          }*/
          this.generateLabel('el-icon-delete has')
          this.generateLabel('el-icon-arrow-left has')
          this.generateLabel('el-icon-arrow-right has')
          this.generateLabel('el-icon-download has')
        }
        // 删除
/*        if (this.node === 'accept' || this.node === 'report') {
          if (this.status === 'handle' ? !this.acceptFlag && !this.disableFlag : false) {
            let del = document.getElementsByClassName('el-icon-delete has')[0] //删除
            del.onclick = function () {
              let image = document.getElementsByClassName('el-image-viewer__img')[0]
              // 获取要删除的数据
              data.forEach(item => {
                if (item.pageid === pageid) {
                  _that.delImageHandle(item)
                }
              })
            }
          }
        } */
        // 删除
        let del = document.getElementsByClassName('el-icon-delete has')[0] //删除
        del.onclick = function () {
          let image = document.getElementsByClassName('el-image-viewer__img')[0]
          // 获取要删除的数据
          data.forEach(item => {
            if (item.pageid === pageid) {
              _that.delImageHandle(item)
            }
          })
        }

        var element = document.getElementsByClassName('el-image-viewer__btn el-image-viewer__close')[0]
        console.log(element, 898765444)
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
            _that.originallistId = _that.originallistId - 1
          }
          let id = data[_that.originallistId].pageid
          preView(id).then(response => {
            // _that.originallistPreviewList = []
            // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
            _that.originallistPreviewList = ['data:image/jpeg;base64,' + response.data]
            _that.originallistPreviewList = [..._that.originallistPreviewList]
          })
        }

        // 右
        let right = document.getElementsByClassName('el-icon-arrow-right has')[0]// 右
        right.onclick = function next() {
          if (_that.originallistId == data.length - 1) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId + 1
          }
          let id = data[_that.originallistId].pageid
          preView(id).then(response => {
            // _that.originallistPreviewList = []
            // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
            _that.originallistPreviewList = ['data:image/jpeg;base64,' + response.data]
            _that.originallistPreviewList = [..._that.originallistPreviewList]
          })
        }

        //下载
        let download = document.getElementsByClassName('el-icon-download has')[0]// 下载
        download.onclick = function () {
          let id = data[_that.originallistId].pageid
          if (_that.originallistId == 0) {
            _that.originallistId = _that.originallistId
          } else {
            _that.originallistId = _that.originallistId - 1
          }
          dowloadFileOne(id).then(response => {
            downloadData(response, data[_that.originallistId].name)
          })
        }
        // let close = document.getElementsByClassName('el-icon-circle-close')[1]
        // close.onclick = function() {
        //   _that.originallistPreviewList = []
        // }
      })
    },

    generateLabel(name) {
      let buttonBox = document.getElementsByClassName('el-image-viewer__actions__inner')[0]
      let newEle = document.createElement('i')
      newEle.className = name
      buttonBox.appendChild(newEle)
    },

    // 删除影像
    delImageHandle(data) {
      this.$confirm('请确认是否删除！', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (data.operator !== this.currentUser) {
          this.$message.warning('只允许删除当前操作人上传的影像！')
          return
        }
        const params = [data.pageid]
        claimDelete(params).then(res => {
          if (res.status === '1') {
            this.$message({type: 'success', message: '删除成功!'})
            this.$refs.imageCkeck.forEach(item => {
              item.model = false
            })
            this.page0 = 1
            this.load1 = false
            //
            this.$emit('imageUpdate')
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除!'
        });
      });
    },
    // 预览时禁止底部body滚动
    stopBodyScroll(isFixed) {
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





    // 关闭查看器
    closeViewer() {
      this.stopBodyScroll(false)
    },








    monitor() {
      var that = this;
      document.onkeydown = function (e) {
        let key = window.event.keyCode;
        // 监听键盘左键
        if (key == 37) {
          that.pre(that.tempList)
        } else if (key == 39) {  // 监听键盘右键
          that.next(that.tempList)
        }
      };
    },
    clickClose() {
      // class="el-image-viewer__btn el-image-viewer__close"

    },


    pre() {
      let _that = this
      if (_that.originallistId == 0) {
        _that.originallistId = _that.originallistId
      } else {
        _that.originallistId = _that.originallistId - 1
      }
      let id = _that.tempList[_that.originallistId].pageid
      preView(id).then(response => {
        _that.originallistPreviewList = []
        _that.originallistPreviewList.push('data:image/jpeg;base64,' + response.data)
        _that.originallistPreviewList = [..._that.originallistPreviewList]
      })
    },
    next() {
      let _that = this
      if (_that.originallistId == _that.tempList.length - 1) {
        _that.originallistId = _that.originallistId + 1
      } else {
        _that.originallistId = _that.originallistId + 1
      }
      let id = _that.tempList[_that.originallistId].pageid
      preView(id).then(response => {
        _that.originallistPreviewList = []
        _that.originallistPreviewList.push('data:image/jpeg;base64,' + response.data)
        _that.originallistPreviewList = [..._that.originallistPreviewList]
      })
    },
  }
}
</script>
<style>
.el-image-viewer__close {
  top: 60px;
  right: 30px;
  width: 60px;
  height: 60px;
  font-size: 60px;
  color: #000;
}
</style>
<style scoped>
.arrow {
  display: inline-block;
  position: absolute;
  top: 20px;
  width: 30px;
  height: 30px;
  display: none;
  /* display: block; */
  font-size: 18px;
  border-radius: 50%;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
  background: rgba(0, 0, 0, .1);
  z-index: 999999;
}

/*.tabs-box:hover .arrow {
  display: block;
  transition: .5;
}*/

.arr-left {
  left: 5px;
}

.arr-right {
  right: 5px;
}

.item-width {
  width: 200px;
}

.el-collapse {
  border: none;
}

/*
.el-tabs /deep/ .el-tabs__item {
  width: 10.5%;
}*/

.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}

.fix-upload {
  position: absolute;
  right: 0;
}

.fix-upload /deep/ .el-upload--picture-card {
  width: 110px;
  height: 110px;
}

.el-upload /deep/ .el-icon-plus {
  position: relative;
  top: -10px;
}

.imgList_div {
  height: 180px;
  width: 100%;
  overflow: auto;
}

.block {
  border: 1px solid #f6f6f6;
  width: 16.4%;
  height: 180px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 8px 0;
}

.block .el-image {
  margin: 0 auto;
}

.my-image-wrapper {
  position: relative;
  display: flex;
  /* background: rgba(0,0,0,0.1); */
  width: 100%;
  height: 100%;
}

.time-wrapper {
  height: 16px;
  line-height: 16px;
  font-size: 12px;
  color: #909399;
  /* padding-top: 10px; */
}

/*.btn-bell-badge {
  width: 26px;
  height: 18px;
  margin-left 5px;
  line-height 18px
  border-radius: 15px;
  background: #f56c6c;
.num {
  display: block;
  width: 100%;
  text-align: center;
  font-size: 12px;
  color: #fff;
}*/


</style>
<style>
.el-image-viewer__close {
  top: 60px;
  right: 30px;
  width: 30px;
  height: 30px;
  font-size: 30px;
  color: #fff;
}

/* .el-image-viewer__next, .el-image-viewer__prev {
 top: 50%;
 -webkit-transform: translateY(-50%);
 transform: translateY(-50%);
 width: 44px;
 height: 44px;
 font-size: 24px;
 color: #fff;
 background-color: #606266;
 border-color: #fff;
} */
</style>
