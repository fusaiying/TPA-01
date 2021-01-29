
<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1" style="position: relative;">
        <template slot="title">
          <span style="font-size:16px;color:black">影像文件</span>
          <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
        </template>
        <span style="position: absolute;right: 40px;top: 0;" >
           <el-button v-if="node==='accept' || node === 'report'" :disabled="status==='show'?true:false || acceptFlag || disableFlag" type="primary" size="mini" @click="uploadHandle">添加文件</el-button>
          <el-button v-if="node==='firstTrial'||node==='review'" :disabled="status==='show'?true:false || acceptFlag" type="primary" size="mini" @click="linkClass">影像分类</el-button>
          <el-button type="primary" size="mini" :loading="downloadFileRarLoading" @click="downloadFileRar">打包下载</el-button>
          <el-button v-if="node === 'accept' || node === 'report'" :disabled="status==='show'?true:false || acceptFlag || disableFlag" type="primary" size="mini" @click="batchDelHandle">批量删除</el-button>
        </span>
        <el-card shadow="never" style="margin-bottom: 10px;">
          <div slot="header" class="clearfix">
            <span>原始材料</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName1" @tab-click="handleClick('1', activeName1)">
            <el-tab-pane v-for="tab in tabLabels.originallist"
                         v-loading="loading1"
                         :label="`${tab.label}(${tab.total})`"
                         :name="tab.name" :key="tab.name">
              <!--              <p class="arrow arr-left" @click="pageChange('-','1',activeName1, tab.total)"><i class="el-icon-arrow-left"></i></p>-->
              <!--              <p class="arrow arr-right" @click="pageChange('+','1',activeName1, tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.originallist.list.length && activeName1===tab.name"
                      :id="'image' + tab.name + '1'" ref="image1"
                      class="imgList_div"
                      @scroll.native="getScrollTop('1', page0, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.originallist.list" :span="3" :key="i" class="block">
                  <div v-if="node === 'accept' || node === 'report'" class="my-image-wrapper">
                    <el-image
                      :src="'data:image/jpeg;base64,'+ url.src"
                      style="width: 96%;height:160px"
                      fit="fill"
                      :preview-src-list="originallistPreviewList"
                      @click="getoriginallistPreviewList(i,url.pageid,imageInfo.originallist.list,'1')"/>
                    <el-button
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      type="text"
                      style="position: absolute;top: 2px;right: 0;padding: 0"
                      @click="delImageHandle(url)">
                      <i class="el-icon-circle-close" style="font-size:16px;color: red;opacity: .5;"></i>
                    </el-button>
                    <el-checkbox
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      :label="url"
                      ref="imageCkeck"
                      style="position:absolute;bottom: -2px;right: -8px;"
                      text-color="#fff" fill="#fff">{{ }}</el-checkbox>
                  </div>
                  <el-image
                    v-else
                    :src="'data:image/jpeg;base64,'+ url.src"
                    :preview-src-list="originallistPreviewList"
                    @click="getoriginallistPreviewList(i,url.pageid,imageInfo.originallist.list,'1')"
                    style="width: 96%;height: 160px"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col  :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <!-- <el-card shadow="never" v-if="imageInfo.supplementlist.list" style="margin-bottom: 10px;"> -->
        <el-card shadow="never" style="margin-bottom: 10px;" v-if="supplemenFlag">
          <div slot="header" class="clearfix">
            <span>补充材料</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName2" @tab-click="handleClick('2', activeName2)">
            <el-tab-pane v-for="tab in tabLabels.supplementlist"
                         v-loading="loading2"
                         :label="`${tab.label}(${tab.total})`"
                         :name="tab.name" :key="tab.name">
              <!--              <p class="arrow arr-left" @click="pageChange('-','2',activeName2, tab.total)"><i class="el-icon-arrow-left"></i></p>-->
              <!--              <p class="arrow arr-right" @click="pageChange('+','2',activeName2, tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.supplementlist.list.length && activeName2===tab.name" :id="'image' + tab.name + '2'" class="imgList_div" @scroll.native="getScrollTop('2', page1, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.supplementlist.list" :span="3" :key="i" class="block">
                  <!--                  <el-image-->
                  <!--                    :src="'data:image/jpeg;base64,'+ url.src"-->
                  <!--                    :preview-src-list="imageInfo.supplementlist.previewList"-->
                  <!--                    style="width: 110px; height: 110px"-->
                  <!--                    fit="contain"/>-->
                  <div v-if="node === 'accept' || node === 'report'" class="my-image-wrapper">
                    <el-image
                      :src="'data:image/jpeg;base64,'+ url.src"
                      :preview-src-list="originallistPreviewList"
                      style="width: 96%;height: 160px"
                      fit="fill"
                      @click="getoriginallistPreviewList(i,url.pageid,imageInfo.supplementlist.list,'2')"
                    />
                    <!-- :preview-src-list="['data:image/jpeg;base64,'+ url.src]" -->
                    <el-button
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      type="text"
                      style="position: absolute;top: 2px;right: 0;padding: 0"
                      @click="delImageHandle(url)">
                      <i class="el-icon-circle-close" style="font-size:16px;color: red;opacity: .5;"></i>
                    </el-button>
                    <el-checkbox
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      :label="url"
                      ref="imageCkeck"
                      style="position:absolute;bottom: -2px;right: -8px;"
                      text-color="#fff" fill="#fff">{{ }}</el-checkbox>
                  </div>
                  <el-image
                    v-else
                    :src="'data:image/jpeg;base64,'+ url.src"
                    :preview-src-list="originallistPreviewList"
                    @click="getoriginallistPreviewList(i,url.pageid,imageInfo.supplementlist.list,'2')"
                    style="width: 96%;height: 160px"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <!-- <el-card shadow="never" v-if="imageInfo.surveylist.list" style="margin-bottom: 10px;"> -->
        <el-card shadow="never" style="margin-bottom: 10px;" v-if="surveyFlag">
          <div slot="header" class="clearfix">
            <span>调查材料</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName3" @tab-click="handleClick('3', activeName3)">
            <el-tab-pane v-for="tab in tabLabels.surveylist"
                         v-loading="loading3"
                         :label="`${tab.label}(${tab.total})`"
                         :name="tab.name" :key="tab.name">
              <!--              <p class="arrow arr-left" @click="pageChange('-','3',activeName3,  tab.total)"><i class="el-icon-arrow-left"></i></p>-->
              <!--              <p class="arrow arr-right" @click="pageChange('+','3',activeName3,  tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.surveylist.list.length && activeName3===tab.name" :id="'image' + tab.name + '3'" class="imgList_div"  @scroll.native="getScrollTop('3', page2, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.surveylist.list" :span="3" :key="i" class="block">
                  <!--                  <el-image-->
                  <!--                    :src="'data:image/jpeg;base64,'+ url.src"-->
                  <!--                    :preview-src-list="imageInfo.surveylist.previewList"-->
                  <!--                    style="width: 110px; height: 110px"-->
                  <!--                    fit="contain"/>-->
                  <div v-if="node === 'accept' || node === 'report'" class="my-image-wrapper">
                    <el-image
                      :src="'data:image/jpeg;base64,'+ url.src"
                      :preview-src-list="originallistPreviewList"
                      style="width: 96%;height: 160px"
                      fit="fill"
                      @click="getoriginallistPreviewList(i,url.pageid,imageInfo.surveylist.list,'3')"
                    />
                    <!-- :preview-src-list="['data:image/jpeg;base64,'+ url.src]" -->
                    <el-button
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      type="text"
                      style="position: absolute;top: 2px;right: 0;padding: 0"
                      @click="delImageHandle(url)">
                      <i class="el-icon-circle-close" style="font-size:16px;color: red;opacity: .5;"></i>
                    </el-button>
                    <el-checkbox
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      :label="url"
                      ref="imageCkeck"
                      style="position:absolute;bottom: -2px;right: -8px;"
                      text-color="#fff" fill="#fff">{{ }}</el-checkbox>
                  </div>
                  <el-image
                    v-else
                    :src="'data:image/jpeg;base64,'+ url.src"
                    :preview-src-list="originallistPreviewList"
                    @click="getoriginallistPreviewList(i,url.pageid,imageInfo.surveylist.list,'3')"
                    style="width: 96%;height: 160px"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col  :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <!-- <el-card shadow="never" v-if="imageInfo.supplementpartlist.list" style="margin-bottom: 10px;"> -->
        <el-card shadow="never" style="margin-bottom: 10px;" v-if="supplementpartFlag">
          <div slot="header" class="clearfix">
            <span>物理件补充</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName4" @tab-click="handleClick('4', activeName4)">
            <el-tab-pane v-for="tab in tabLabels.supplementpartlist"
                         v-loading="loading4"
                         :label="`${tab.label}(${tab.total})`"
                         :name="tab.name" :key="tab.name">
              <!--              <p class="arrow arr-left" @click="pageChange('-','4',activeName4,  tab.total)"><i class="el-icon-arrow-left"></i></p>-->
              <!--              <p class="arrow arr-right" @click="pageChange('+','4',activeName4,  tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.supplementpartlist.list.length && activeName4===tab.name" :id="'image' + tab.name + '4'" class="imgList_div"  @scroll.native="getScrollTop('4', page3, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.supplementpartlist.list" :span="3" :key="i" class="block">
                  <!--                  <el-image-->
                  <!--                    :src="'data:image/jpeg;base64,'+ url.src"-->
                  <!--                    style="width: 110px; height: 110px"-->
                  <!--                    :preview-src-list="imageInfo.supplementpartlist.previewList"-->
                  <!--                    fit="contain"/>-->
                  <div v-if="node === 'accept' || node === 'report'" class="my-image-wrapper">
                    <el-image
                      :src="'data:image/jpeg;base64,'+ url.src"
                      :preview-src-list="originallistPreviewList"
                      style="width: 96%;height: 160px"
                      @click="getoriginallistPreviewList(i,url.pageid,imageInfo.supplementpartlist.list,'4')"
                      fit="fill"/>
                    <!-- :preview-src-list="['data:image/jpeg;base64,'+ url.src]" -->
                    <el-button
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      type="text"
                      style="position: absolute;top: 2px;right: 0;padding: 0"
                      @click="delImageHandle(url)">
                      <i class="el-icon-circle-close" style="font-size:16px;color: red;opacity: .5;"></i>
                    </el-button>
                    <el-checkbox
                      :disabled="status==='show'?true:acceptFlag || disableFlag"
                      :label="url"
                      ref="imageCkeck"
                      style="position:absolute;bottom: -2px;right: -8px;"
                      text-color="#fff" fill="#fff">{{ }}</el-checkbox>
                  </div>
                  <el-image
                    v-else
                    :src="'data:image/jpeg;base64,'+ url.src"
                    :preview-src-list="originallistPreviewList"
                    style="width: 96%;height: 160px"
                    fit="fill"
                    @click="getoriginallistPreviewList(i,url.pageid,imageInfo.supplementpartlist.list,'4')"
                  />
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-collapse-item>
    </el-collapse>
    <upload-modul :claimno="claimno" v-model="uploadVis" @renovateImageInfo="renovateImageInfo"/>
  </el-card>
</template>
<script>
  import uploadModul from '../modul/upload'
  import { downloadData } from '@/utils'
  import {encrypt} from "@/utils/rsaEncrypt"
  import { preView } from '@/api/claim/fileDeal'
  import { dowloadFileRar, claimDelete,getImageInfo,dowloadFileOne} from '@/api/claim/handleDeal'
  import images from '../../common/mixins/images'
  import { toClient } from '@/utils/commenMethods.js'
  import ElImageViewer from 'element-ui/packages/image/src/image-viewer'
  import Bus from '../../bus/bus'

  export default {
    inject: ['getImageInfo'],
    mixins: [images],
    components: {
      uploadModul,
      ElImageViewer
    },
    props: {
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
    },
    data() {
      return {
        activeNames: ['1'],
        uploadVis: false,
        currentUser: '',
        loading1: false,
        loading2: false,
        loading3: false,
        loading4: false,
        load1: false,
        load2: false,
        load3: false,
        load4: false,
        showViewer: false,
        type: '',
        downloadFileRarLoading: false,
        supplemenFlag: false,
        surveyFlag: false,
        supplementpartFlag: false,
        originallistPreviewList: [''],
        pageno: 1,
        originallistId: '',
        tempList : [],
        top1: 0
        // temp: []
      }
    },
    // mounted(){
    // debugger
    // if(this.imageInfo.originallist.list && this.imageInfo.originallist.list.length){
    //   this.imageInfo.originallist.list.map(item => {
    //     this.temp.push('data:image/jpeg;base64,'+ item.src)
    //   })
    // }
    // },
    watch: {
      imageLoad: function(val) {
        this.$nextTick(function() {
          // if (this.type === '1') {
          //   this.loading1 = val
          // } else if (this.type === '2') {
          //   this.loading2 = val
          // } else if (this.type === '3') {
          //   this.loading3 = val
          // } else if (this.type === '4') {
          //   this.loading4 = val
          // }
        })
      }
    },
    created() {
      this.currentUser = toClient()
      this.monitor()
      // this.clickClose()
    },
    mounted() {
      //物理件上传触发
      Bus.$on('changePage3',() =>{
        this.page3 = 1
        this.load4 = false
      })
    },
    updated() {
      this.supplemenFlag = !!this.tabLabels.supplementlist[0].total
      this.surveyFlag = !!this.tabLabels.surveylist[0].total
      this.supplementpartFlag= !!this.tabLabels.supplementpartlist[0].total
    },
    methods: {
      uploadHandle() {
        this.uploadVis = true
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
          this.originallistPreviewList = ['data:image/jpeg;base64,'+ response.data]
          // this.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
          this.$nextTick(() =>{
            this.appendDel(this.tempList,index,id)
          })
        })
        this.stopBodyScroll(true)
      },
      // 关闭查看器
      closeViewer() {
        this.stopBodyScroll(false)
      },
      // 预览时禁止底部body滚动
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
      downloadFileRar() {
        this.downloadFileRarLoading = true
        dowloadFileRar(this.claimno).then(res => {
          downloadData(res, this.claimno + '.rar')
          this.downloadFileRarLoading = false
        })
      },
      initImages() {
        this.activeName1 = '00'
        this.activeName2 = '00'
        this.activeName3 = '00'
        this.activeName4 = '00'
      },
      linkClass() {
        // let data =  {
        //   node: 'classificat',
        //   claimNo: encrypt(this.claimno)
        // }
        const newpage = this.$router.resolve({
          name: 'ificatdetail',
          params:{},
          query:{
            node: 'classificat',
            showFlag: '01',
            claimNo: encrypt(this.claimno)
          }
        })
        window.open(newpage.href, '_blank');
        // this.$router.push({
        //   path: '/claims-handle/classificat',
        //   query: {
        //     node: 'classificat',
        //     claimNo: encrypt(this.claimno)
        //   }
        // })
      },
      renovateImageInfo(data) {
        this.page0 = 1
        this.page1 = 1
        this.page2 = 1
        this.page3 = 1
        this.$emit('imageUpdate',data)
        this.$emit('getFileCount')
      },
      // 删除影像
      delImageHandle(data) {
        this.$confirm('请确认是否删除！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (data.operator !==  this.currentUser) {
            this.$message.warning('只允许删除当前操作人上传的影像！')
            return
          }
          const params = [data.pageid]
          claimDelete(params).then(res => {
            if (res.status === '1') {
              this.$message({ type: 'success', message: '删除成功!' })
              this.$refs.imageCkeck.forEach(item => {
                item.model = false
              })
              this.page0 = 1
              this.page1 = 1
              this.page2 = 1
              this.page3 = 1
              this.load1 = false
              this.load2 = false
              this.load3 = false
              this.load4 = false
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
      batchDelHandle() {
        if (this.$refs.imageCkeck.findIndex(item => item.isChecked) === -1) {
          this.$message.warning('请先选择影像文件！')
          return
        }
        this.$confirm('请确认是否删除！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let returnFlag = false
          let params = []
          this.$refs.imageCkeck.forEach(item => {
            if (params.findIndex(val => val === item.label.pageid) === -1 && item.isChecked) {
              if (item.label.operator !== this.currentUser) {
                returnFlag = true
              }
              params.push(item.label.pageid)
            }
          })
          if (returnFlag) {
            this.$message.warning('只允许删除当前操作人上传的影像！')
            return
          }
          claimDelete(params).then(res => {
            if (res.status === '1') {
              this.$message({ type: 'success', message: '删除成功!' })
              this.$refs.imageCkeck.forEach(item => {
                item.model = false
              })
              this.page0 = 1
              this.page1 = 1
              this.page2 = 1
              this.page3 = 1
              this.load1 = false
              this.load2 = false
              this.load3 = false
              this.load4 = false
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
        } else if (type === '2') {
          if (this.page1 < Math.ceil(total / 5)) {
            this.page1++
            this.type = type
            this.loadMore('2', this.page1, this.activeName2)
            this.load2 = false
          } else {
            this.load2 = true
          }
        } else if (type === '3') {
          if (this.page2 < Math.ceil(total / 5)) {
            this.page2++
            this.type = type
            this.loadMore('3', this.page2, this.activeName3)
            this.load3 = false
          } else {
            this.load3 = true
          }
        } else if (type === '4') {
          if (this.page3 < Math.ceil(total / 5)) {
            this.page3++
            this.type = type
            this.loadMore('4', this.page3, this.activeName4)
            this.load4 = false
          } else {
            this.load4 = true
          }
        }
      },
      loadMore(type, page, subType) {
        if (subType === '00') {
          subType = ''
        }
        this.getImageInfo(type, page, subType)
      },
      getScrollTop(type, page, total, name) {
        const scrollTop = document.getElementById('image' + name + type).scrollTop
        const scrollHeight = document.getElementById('image' + name + type).scrollHeight
        const offsetHeight = document.getElementById('image' + name + type).offsetHeight
        if ((offsetHeight + scrollTop) - scrollHeight === 0) {
          this.load(type, page, total, name)
        }
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
      clickClose() {
        // class="el-image-viewer__btn el-image-viewer__close"

      },
      generateLabel(name) {
        let buttonBox = document.getElementsByClassName('el-image-viewer__actions__inner')[0]
        let newEle = document.createElement('i')
        newEle.className = name
        buttonBox.appendChild(newEle)
      },
      appendDel(data,index,pageid) {
        this.originallistId = index
        let _that = this
        // 第一次打开预览时生成
        this.$nextTick(function() {
          if (document.getElementsByClassName('has').length === 0) {
            if (this.node === 'accept' || this.node === 'report') {
              if (this.status === 'handle' ? !this.acceptFlag && !this.disableFlag : false) {
                this.generateLabel('el-icon-delete has')
              }
            }
            this.generateLabel('el-icon-arrow-left has')
            this.generateLabel('el-icon-arrow-right has')
            this.generateLabel('el-icon-download has')
          }
          // 删除
          if (this.node === 'accept' || this.node === 'report') {
            if (this.status === 'handle' ? !this.acceptFlag && !this.disableFlag : false) {
              let del = document.getElementsByClassName('el-icon-delete has')[0] //删除
              del.onclick =function() {
                let image = document.getElementsByClassName('el-image-viewer__img')[0]
                // 获取要删除的数据
                data.forEach(item => {
                  if (item.pageid === pageid) {
                    _that.delImageHandle(item)
                  }
                })
              }
            }
          }
          var element = document.getElementsByClassName('el-image-viewer__btn el-image-viewer__close')[0]
          console.log(element,898765444)
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
          // let close = document.getElementsByClassName('el-icon-circle-close')[1]
          // close.onclick = function() {
          //   _that.originallistPreviewList = []
          // }
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
    background: rgba(0,0,0,.1);
    z-index: 999999;
  }
  .tabs-box:hover .arrow {
    display: block;
    transition: .5;
  }
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
  .el-tabs /deep/ .el-tabs__item {
    width: 10.5%;
  }
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
    text-align:center;
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
