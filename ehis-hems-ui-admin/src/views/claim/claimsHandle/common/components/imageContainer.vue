<template>
  <div class="app-container" style="margin-bottom: 20px;height: calc(100% - 20px)">
    <!-- <div class="nav-bar"> -->
      <el-card class="top-card">
        <div style="line-height:40px">
          <el-button type="text" style="float:left;color:#409EFF;font-size: 14px;">赔案号： {{claimNo}}</el-button>
          <el-button type="text" style="float:left;color:#409EFF;font-size: 14px;margin-left:20px">姓名： {{name}}</el-button>
          <el-button type="text" style="float:left;color:#409EFF;font-size: 14px;margin-left:20px">证件号码： {{idNo}}</el-button>
          <el-tooltip v-if="isshowError" class="item" effect="dark" :content="errortypecontentslist" placement="top-start">
            <el-button  style="float: left;margin-left:20px;margin-bottom:15px;color:red" type="text" @click="confirmScan">{{this.selectDictLabel(this.scan_scanOptions, errortypecontent)}}</el-button>
          </el-tooltip>
          <el-button v-if="status =='handel'" style="float:right;margin-right:20px;margin-top:3px" :disabled="counts == null" @click="confirmSubmitScan"  type="primary" size="mini">提交</el-button>
          <el-button v-if="status =='handel'" style="float:right;margin-right:20px;margin-top:3px" @click="toClient"  type="primary" size="mini">扫描</el-button>
        </div>
      </el-card>
    <!-- </div> -->
    <el-card style="min-height:550px;max-height:100%;overflow-y: auto;margin-top:50px">
      <el-row style="overflow-y: auto;height: 100%;margin-top:20px">
        <el-col v-for="(url, i) in imgList" class="block">
          <el-image
          :src="url.src"
          style="width: 110px;height: 110px;margin: 0 10px"
          fit="fill"
          :preview-src-list="originallistPreviewList"
          @click="getoriginallistPreviewList(i,url.pageId,imgList,'1')"/>
        </el-col>
      </el-row>
    </el-card>
    <el-card style="height:220px;padding-bottom:10px;overflow-y: auto;font-size:12px;">
      <div slot="header" class="clearfix">
        <span style="font-size:16px;color:black">案件历史记录</span>
      </div>
      <div>
        <div v-for="(item, i) in loagData" :key="i">
          <el-row style="width:100%;font-size:12px;padding:10px 0">
            <span v-if="(item.node).indexOf('C') !== -1">{{this.selectDictLabel(this.tasknodeOptions, item.node)}}{{item.conclution?'-':''}}{{this.selectDictLabel(this.examine_conclusionOptions, item.conclution)}}</span>
            <span v-else>{{item.node}}</span>
            <span style="margin-left:10px"><span style="color:red">处理人</span>- {{item.operator}}</span>
            <span style="margin-left:10px"><span style="color:red">日期</span> - {{item.completedate}}</span>
            <el-tooltip class="item" effect="dark" :content="item.remarks" placement="top-start">
              <span style="max-width:200px;margin-left:10px"><span style="color:red">备注</span> - {{item.remarks}}</span>
            </el-tooltip>
          </el-row>
        </div>
      </div>
    </el-card>
    <el-dialog
      title="提示"
      :visible.sync="submitScanModal"
      width="30%"
     >
      <span>影像件共{{counts}}张，是否确认提交？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="submitScanModal = false">取 消</el-button>
        <el-button type="primary" @click="submitListCase">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="扫描提交"
      :visible.sync="scanVisable"
      width="500px"
      >
      <el-form ref="scanFrom" :model="scanInfo" :rules="scanRules" label-width="150px" label-position="right" size="mini">
        <el-form-item label="退回分类/类型：" prop="errortype">
          <span class="form-item-span">{{this.selectDictLabel(this.scan_scanOptions, errortypecontent) }}</span>
        </el-form-item>
        <el-form-item label="退回描述：" prop="errortype">
          <span class="form-item-span">{{this.errortypecontentslist}}</span>
        </el-form-item>
        <el-form-item label="是否认同回退意见：" prop="errortype">
          <el-radio-group v-model="radio" @change="changeList">
            <el-radio :label="3">是</el-radio>
            <el-radio :label="6">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="不认同原因：" prop="disagreereason" :rules="scanRules.disagreereason"  v-if="showNoReason">
          <el-input
            type="textarea"
            style="width:300px"
            :autosize="{ minRows: 3, maxRows: 6}"
            placeholder="请输入内容"
            v-model="scanInfo.disagreereason">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="scanVisable = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="confirmSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import {getImages} from '@/api/claim/input'
  import { preView } from '@/api/claim/fileDeal'
  import {decrypt,encrypt } from "@/utils/rsaEncrypt"
  import { preViewThumbnail, dowloadFileOne, serchScanLog, isReturn ,returnOpinion, scanSubmit } from '@/api/claim/handleDeal'
  import { downloadData } from '@/utils'
  const baseAddress = process.env.BASE_API
  /*import { getToken } from '@/utils/auth'
  import jwt from 'jsonwebtoken'*/
  import Cookies from 'js-cookie'
/*  import Viewer from  'viewerjs'*/
  import 'viewerjs/dist/viewer.css'
  export default {
    name: "imageContainer",
    props: {
      imageInfo: {
        type: Array,
        default: () => {
          return []
        }
      }
    },
    dicts:['tasknode','scan_scan','examine_conclusion'],
    data () {
      return {
        url:[] ,
        imgList: [],
        imageInfos: [],
        images: [],
        claimNo: '',
        originallistPreviewList: [''],
        tempList: [],
        originallistId: '',
        top1: 0,
        name: '',
        idNo: '',
        scanVisable: false,
        scanInfo: {
          disagreereason: ''
        },
        scanRules: {
          disagreereason: [
            { trigger: ['blur', 'change'], required: true, message: '原因必填' }, // 实赔金额
          ]
        },
        radio: '',
        showNoReason: false,
        counts: null,
        loagData: [],
        isshowError: false,
        errortypecontent: '',
        errortypecontentslist: '',
        whetheridentify: '',
        errortype: '',
        rejectreason: '',
        opinionno: '',
        status: '',
        submitScanModal: false,
        tasknodeOptions: [],
        scan_scanOptions: [],
        examine_conclusionOptions: [],
      }
    },
    mounted() {
      this.getDicts("tasknode").then(response => {
        this.tasknodeOptions = response.data;
      });
      this.getDicts("scan_scan").then(response => {
        this.scan_scanOptions = response.data;
      });
      this.getDicts("examine_conclusion").then(response => {
        this.examine_conclusionOptions = response.data;
      });
      if (this.$route.query) {
        this.querys = JSON.parse(decodeURI(this.$route.query.data))
        this.claimNo = this.querys.claimno?decrypt(this.querys.claimno):''
        this.idNo = this.querys.idNo ? decrypt(this.querys.idNo) : ''
        this.name = this.querys.name ? this.querys.name : ''
        this.status = this.querys.status? this.querys.status: 'handel'
        this.counts = this.querys.num ? this.querys.num : null
        console.log(this.counts,89898989)
      }
      this.getAllImages();
      this.monitor()
      this.serchScanLog()
      this.isReturn()
      // this.clickClose()
    },
    methods: {
      dealImages(data) {
        this.showList = []
        for (let i = 0; i < data.length; i++) {
          preViewThumbnail(data[i].src).then(response => {
            if (response.status === '1') {
              const url = 'data:image/jpeg;base64,' + response.data
              this.imgList.push({ src: url, pageId: data[i].src, pageType: data[i].type, originals: data[i].originals,imageName: data[i].imageName })
              if (this.imgList.length === this.imageInfos.length) {
                // 页面显示图片
                let index = this.imgList.findIndex(item => item.pageType === '9' && !item.originals)
                // if (index !== -1) {
                //   this.changeImage(this.urls[index].src,this.urls[index].pageType,this.urls[index].originals, index) //src pageType
                //   this.imgIdx = index + 1
                // } else {
                //   this.imgIdx = 0
                // }
              }
            } else {
              this.$message({ message: '图片加载失败！', type: 'error' })
            }
          })
        }
      },
       // 扫描
      toClient() {
        const jwt = require('jsonwebtoken')
        const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
        const name = str.user_name
        window.open(`${process.env.BASE_API}/claim-easyscan5/EasyScanContainer?usercode=${name}&receivedmode=1`)
      },
      // 查询案件历史轨迹
      serchScanLog() {
        serchScanLog(this.claimNo).then(res =>{
          if (res.status == '1') {
            this.loagData = []
            this.loagData = res.data
          } else {
            this.$message.error('查询案件历史轨迹错误！')
          }
        })
      },
      isReturn() {
        isReturn(this.claimNo).then(res =>{
          // conclusion 01 不是退回案件 02 退回案件
          if (res.status =='1') {
            if (res.data.conclusion == '01') {
              this.isshowError = false
            } else {
              this.isshowError = true
              this.errortypecontent = res.data.errortype + res.data.rejectreason
              this.errortypecontentslist = res.data.explanation
              this.errortype = res.data.errortype
              this.rejectreason = res.data.rejectreason
              this.radio = res.data.whetheridentify == '01' ? 3 : 6
              this.whetheridentify = res.data.whetheridentify
              this.opinionno = res.data.opinionno
              if (this.radio == '3') {
                this.showNoReason = false
              } else {
                this.showNoReason = true
              }
              this.scanInfo.disagreereason = res.data.disagreereason
            }
          } else {
            this.$message.error('查询是否是退回案件！')
          }
        })
      },
      // 扫面流转
      confirmSubmitScan() {
        if (this.isshowError == false) {
          this.submitScanModal = true
        } else {
          this.$confirm('提交案件将流转，是否确认提交？', '确认信息', {
            distinguishCancelAndClose: true,
            closeOnClickModal: false,
            confirmButtonText: '确定',
            cancelButtonText: '取消'
          })
          .then(() => {
            this.submitListCase()
          })
          .catch(action => {
            this.$message({
              type: 'info',
              message: action === 'cancel'
                ? '已取消！'
                : '已取消！'
            })
          });
        }

      },
      submitListCase() {
        let params ={}
        params.claimno = this.claimNo
        params.opinionno  = this.opinionno
        params.whetheridentify = this.whetheridentify
        scanSubmit(params).then(res =>{
          if (res.status == '1') {
            this.submitScanModal = false
            this.$message.success('提交成功！')
            setTimeout(() =>{
              window.close()
            },2000)
          } else {
            this.$message({ message: res.data.errMsg, type: 'error' })
          }
        })
      },
      // 确认提交
      confirmSubmit() {
        this.$refs['scanFrom'].validate(valid => {
          console.log(valid,8989)
          if (valid) {
            let params = {}
            params.claimno = this.claimNo
            params.whetheridentify  = this.whetheridentify
            params.disagreereason = this.scanInfo.disagreereason
            params.tasknode = 'C009'
            params.opinionno  = this.opinionno? this.opinionno :''
            returnOpinion(params).then(res =>{
              if (res.status == '1') {
                this.opinionno = res.data.opinionno
                this.scanInfo.disagreereason = res.data.disagreereason
                this.radio = res.data.whetheridentify == '01' ? 3 : 6
                if (this.radio == '3') {
                  this.showNoReason = false
                } else {
                  this.showNoReason = true
                }
                this.scanVisable = false
                this.$message.success('提交成功！')
                this.serchScanLog()
              } else {
                this.$message.error('更新数据出错！')
                this.scanVisable = false
              }
            })
          } else {
            return false
          }
        })
      },
      changeList(value) {
        if (value == '3') {
          this.showNoReason = false
          this.whetheridentify = '01'
        } else {
          this.showNoReason = true
          this.whetheridentify = '02'
        }
      },
      confirmScan() {
        this.scanVisable = true
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
          this.originallistPreviewList = ['data:image/jpeg;base64,' + response.data]
          // this.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
          this.$nextTick(() => {
            this.appendDel(this.tempList, index)
          })
        })
        this.stopBodyScroll(true)
      },
      clickClose() {
        var element = document.getElementsByClassName('el-icon-circle-close')[2]
        let _this = this
        element.onclick = function next() {
          _this.stopBodyScroll(false)
        }
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

          // 左
          let left = document.getElementsByClassName('el-icon-arrow-left has')[0] // 左
          left.onclick = function pre() {
            if (_that.originallistId == 0) {
              _that.originallistId = _that.originallistId
            } else {
              _that.originallistId = _that.originallistId -1
            }
            let id = data[_that.originallistId].pageId
            preView(id).then(response => {
              // _that.originallistPreviewList = []
              // _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
              _that.originallistPreviewList = ['data:image/jpeg;base64,'+ response.data]
              _that.originallistPreviewList = [..._that.originallistPreviewList]
            })
          }
          var element = document.getElementsByClassName('el-image-viewer__btn el-image-viewer__close')[0]
          let _this = this
          element.onclick = function next() {
            _this.stopBodyScroll(false)
          }
          // 右
          let right = document.getElementsByClassName('el-icon-arrow-right has')[0]// 右
          right.onclick = function next() {
            if (_that.originallistId == data.length-1) {
              _that.originallistId = _that.originallistId
            } else {
              _that.originallistId = _that.originallistId + 1
            }
            let id = data[_that.originallistId].pageId
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
            let id = data[_that.originallistId].pageId
            if (_that.originallistId == 0) {
              _that.originallistId = _that.originallistId
            } else {
              _that.originallistId = _that.originallistId -1
            }
            dowloadFileOne(id).then(response => {
              downloadData(response,data[_that.originallistId].imageName)
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
        let id = _that.tempList[_that.originallistId].pageId
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
        let id = _that.tempList[_that.originallistId].pageId
        preView(id).then(response => {
          _that.originallistPreviewList = []
          _that.originallistPreviewList.push('data:image/jpeg;base64,'+ response.data)
          _that.originallistPreviewList = [..._that.originallistPreviewList]
        })
      },
      getAllImages(mode) {
        const requestData = {
          claimNo: this.claimNo,
          type: '',
          recievedMode: mode
        }
        getImages(requestData).then(response => {
          this.imageInfos = response.data
          this.dealImages(this.imageInfos)
        }).catch(error => {
          this.$message.error('查询影像文件异常')
        })
      },
    },
  }
</script>

<style scoped>
.trajectory {
  width: 180px;
  float: left;
}
.demonstration {
  height: 30px;
  line-height: 30px;
  font-size: 12px;
  color: #909399;
}
.top-card {
  position: fixed;
  top: 0;
  z-index: 999;
  width: 97.2%
}
.box-card ::v-deep.el-card__body {
  height: calc(100% - 55px)
}
.block {
  border: 1px solid #f6f6f6;
  width: 130px;
  height: 130px;
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
.overEslap_class{
  color: #555;
  font-size: 12px;
  width:95%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space:nowrap
}
</style>
<style>
  .el-tooltip__popper{
    max-width: 50%;
  }
  .el-autocomplete-suggestion li {
    width: 100%;
    white-space: pre-wrap;
    word-break: break-word;
    height: auto;
    line-height: 18px;
    padding: 0 5px;
  }
  .el-collapse-item__wrap {
    border: none !important;
  }
  .el-collapse {
    border: none;
  }
</style>

