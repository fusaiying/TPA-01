<template>
    <el-dialog :visible.sync="dialogVis" title="补材信息" width="90%" append-to-body
      :before-close="dialogClosed"
      :close-on-click-modal="false"
      @open="openHandle"
      v-bind="$attrs">
      <div style="margin-top: -30px;" class="tab-title">
        <span>补材任务列表</span>
<!--        <el-button size="mini" type="text" class="el-icon-minus" style="color: #1f2d3d" @click="toMinimize"></el-button>-->
      </div>
      <el-table
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        :data="fillingsData"
        size="small"
        highlight-current-row
        tooltip-effect="dark">
        <el-table-column align="center" label="序号" type="index" width="55"/>
        <el-table-column align="center" :formatter="formateMaterialtype" property="materialtype" label="材料类型"/>
        <el-table-column align="center" :formatter="formateMode" property="supplementarymode" label="推送方式"/>
        <el-table-column align="center" property="status" label="补材状态">
          <template slot-scope="scope">
            {{/*dict.label.status_supple[scope.row.status]*/ this.selectDictLabel(this.status_suppleOptions, scope.row.status)}}
          </template>
        </el-table-column>
        <el-table-column align="center" property="conclusion" label="补材结论">
          <template slot-scope="scope">
            {{/*dict.label.supplementaryconclusion[scope.row.conclusion]*/this.selectDictLabel(this.supplementaryconclusionOptions, scope.row.conclusion)}}
          </template>
        </el-table-column>
        <el-table-column align="center" property="explanation" label="备注"/>
        <el-table-column align="center" property="createdate" label="发起时间"/>
        <el-table-column align="center" property="operatedate" label="补充时间"/>
        <el-table-column align="center" property="operator" label="操作人"/>
      </el-table>
      <div>
        <el-card v-loading="loading1" shadow="never" style="margin-bottom: 10px;">
          <div slot="header" class="clearfix">
            <span>原始材料</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName1" @tab-click="handleClick('1', activeName1)">
            <el-tab-pane v-for="tab in tabLabels.originallist"
                         :label="`${tab.label}(${tab.total})`"
                         :name="tab.name" :key="tab.name">
              <!--              <p class="arrow arr-left" @click="pageChange('-','2',activeName2, tab.total)"><i class="el-icon-arrow-left"></i></p>-->
              <!--              <p class="arrow arr-right" @click="pageChange('+','2',activeName2, tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.originallist.list.length && activeName1===tab.name" :id="'image' + tab.name + '1'" class="imgList_div" @scroll.native="getScrollTop('1', page0, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.originallist.list" :span="4" :key="i" class="block">
                  <el-image
                    :src="'data:image/jpeg;base64,'+ url.src"
                    style="width: 96%;height: 160px"
                    :preview-src-list="originallistPreviewList"
                    @click="getoriginallistPreviewList(i,url.pageid,imageInfo.originallist.list,'1')"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col v-show="load1" :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <el-card v-loading="loading2" shadow="never" style="margin-bottom: 10px;" v-if="supplemenFlag">
          <div slot="header" class="clearfix">
            <span>补充材料</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName2" @tab-click="handleClick('2', activeName2)">
            <el-tab-pane v-for="tab in tabLabels.supplementlist"
              :label="`${tab.label}(${tab.total})`"
              :name="tab.name" :key="tab.name">
<!--              <p class="arrow arr-left" @click="pageChange('-','2',activeName2, tab.total)"><i class="el-icon-arrow-left"></i></p>-->
<!--              <p class="arrow arr-right" @click="pageChange('+','2',activeName2, tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.supplementlist.list.length && activeName2===tab.name" :id="'image' + tab.name + '2'" class="imgList_div" @scroll.native="getScrollTop('2', page1, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.supplementlist.list" :span="4" :key="i" class="block">
                  <el-image
                    :src="'data:image/jpeg;base64,'+ url.src"
                    style="width: 96%;height: 160px"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col v-show="load2" :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <el-card v-loading="loading3" shadow="never" style="margin-bottom: 10px;" v-if="surveyFlag">
          <div slot="header" class="clearfix">
            <span>调查材料</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName3" @tab-click="handleClick('3', activeName3)">
            <el-tab-pane v-for="tab in tabLabels.surveylist"
              :label="`${tab.label}(${tab.total})`"
              :name="tab.name" :key="tab.name">
<!--              <p class="arrow arr-left" @click="pageChange('-','3',activeName3,  tab.total)"><i class="el-icon-arrow-left"></i></p>-->
<!--              <p class="arrow arr-right" @click="pageChange('+','3',activeName3,  tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.surveylist.list.length && activeName3===tab.name" :id="'image' + tab.name + '3'" class="imgList_div" @scroll.native="getScrollTop('3', page2, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.surveylist.list" :span="4" :key="i" class="block">
                  <el-image
                    :src="'data:image/jpeg;base64,'+ url.src"
                    style="width: 96%;height: 160px"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col v-show="load3" :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <el-card v-loading="loading4" shadow="never" style="margin-bottom: 10px;" v-if="supplementpartFlag">
          <div slot="header" class="clearfix">
            <span>物理件补充</span>
          </div>
          <el-tabs class="tabs-box" :stretch="true" v-model="activeName4" @tab-click="handleClick('4', activeName4)">
            <el-tab-pane v-for="tab in tabLabels.supplementpartlist"
              :label="`${tab.label}(${tab.total})`"
              :name="tab.name" :key="tab.name">
<!--              <p class="arrow arr-left" @click="pageChange('-','4',activeName4,  tab.total)"><i class="el-icon-arrow-left"></i></p>-->
<!--              <p class="arrow arr-right" @click="pageChange('+','4',activeName4,  tab.total)"><i class="el-icon-arrow-right"></i></p>-->
              <el-row v-if="imageInfo.supplementpartlist.list.length && activeName4===tab.name" :id="'image' + tab.name + '4'" class="imgList_div" @scroll.native="getScrollTop('4', page3, tab.total, tab.name)">
                <el-col v-for="(url, i) in imageInfo.supplementpartlist.list" :span="4" :key="i" class="block">
                  <el-image
                    :src="'data:image/jpeg;base64,'+ url.src"
                    style="width: 96%;height: 160px"
                    fit="fill"/>
                  <div class="time-wrapper">
                    <span>{{url.operator ? url.operator : ''}}</span><span style="margin-left: 10px">{{url.maketime ? url.maketime : ''}}</span>
                  </div>
                </el-col>
                <el-col v-show="load4" :span="24" style="text-align: center">没有更多了</el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </div>
      <el-form  :disabled="(node!=='firstTrial'&&type==='01')|| status==='show'" ref="diaForm" style="margin-top: 20px;" :rules="tableFormRules" :model="diaForm" label-width="110px" size="mini">
        <el-form-item label="材料类型：" prop="materialtype">
          <el-checkbox-group v-model="diaForm.materialtype" @change="changeMaterialType">
            <el-checkbox v-for="item in material_typeOptions" :label="item.dictValue" :key="item.dictValue">{{item.dictLabel}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="推送方式：" prop="pushtype">
          <el-checkbox-group v-model="diaForm.pushtype" @change="changePushType">
            <el-checkbox v-for="item in pushtypeArr" :label="item.value" :disabled="item.disabled == true" :key="item.value">{{item.label}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="材料说明：" prop="description">
          <el-autocomplete
            v-model="diaForm.description"
            :rows="3"
            style="width: 98%;"
            :fetch-suggestions="querySearch"
            type="textarea"
            :maxlength="1000"
            show-word-limit
            placeholder="材料话术模版"
            @click.native="loadAll">
          </el-autocomplete>
        </el-form-item>
      </el-form>
      <div v-if="diaForm.pushtype.find((item) => {
          return item==='01'
        })">
        <div class="tab-title">
          <span>材料列表</span>
        </div>
        <el-form :disabled="(node!=='firstTrial'&&type==='01') || status==='show'" ref="diaTableForm" :rules="tableFormRules" :model="diaTableForm" label-width="0" size="mini">
          <el-table
            ref="multipleTable"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="diaTableForm.materiData"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            @selection-change="selectChange">
            <el-table-column align="center" type="selection" width="55"/>
            <el-table-column align="center" property="materiatype" label="材料类型">
              <template slot-scope="scope">
                <span>{{/*dict.label.material_type[scope.row.materiatype]*/this.selectDictLabel(this.material_typeOptions, scope.row.materiatype)}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" property="receiveddate" label="提交时间"/>
            <el-table-column align="center" property="originals" label="原件数量"/>
            <el-table-column align="center" property="copies" label="复印件数量"/>
            <el-table-column align="center" property="supplementarytype" label="处理方式">
              <template slot-scope="scope">
                <el-form-item :prop="'materiData.' + scope.$index + '.supplementarytype'"
                  :rules="{ required: true, message: '不能为空'}">
                  <el-select v-model="scope.row.supplementarytype" placeholder="请选择" clearable @change="supplementarytypeChange(scope.row)">
                    <el-option label="补充" value="01"/>
                    <el-option label="修改" value="02"/>
                  </el-select>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column align="center" width="400" property="description" label="材料说明">
              <template slot-scope="scope">
                <el-form-item :prop="'materiData.' + scope.$index + '.description'"
                  :rules="{ required: true, message: '不能为空'}">
                    <el-autocomplete
                      v-model="scope.row.description"
                      :rows="3"
                      style="width: 380px; font-size: 10px;"
                      :fetch-suggestions="querySearch"
                      type="textarea"
                      placeholder="请输入内容"
                      :maxlength="50"
                      show-word-limit
                      >
                    </el-autocomplete>
                </el-form-item>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" :disabled="(node!=='firstTrial'&&type==='01') || status==='show'" type="primary" @click="savefillings" :loading="loading">发起补材</el-button>
        <el-button size="mini" @click="dialogClosed">返 回</el-button>
      </div>
    </el-dialog>
</template>

<script>
import {pastSupplementaryList,getAntMateriaList, startStuff,getTempalte,getContents,dowloadFileOne} from '@/api/claim/handleDeal'
import images from '../../common/mixins/images'
import {decrypt} from "@/utils/rsaEncrypt"
import { preView } from '@/api/claim/fileDeal'
import { downloadData } from '@/utils'
import { get } from '@/api/dictDetail'

export default {
 // dicts: ['supplementmode', 'material_type', 'casestep', 'status_supple', 'supplementaryconclusion'],
  mixins: [images],
  inject: ['getImageInfo'],
  props: {
    dictList:Array,
    value: Boolean,
    type: String,
    node: String,
    status: String,
    claimno: String,
    fillsCount: String,
    email: String,
    imageLoad: Boolean
  },
  data() {
    return {
      activeNames: '1',
      diaForm: {
        pushtype: ['03'],
        materialtype: [],
        description: null
      },
      pushtypeArr: [],
      tableFormRules: {
        materialtype: { required: true, message: '不能为空!', trigger: 'change' },
        description: { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        pushtype:  { required: true, message: '不能为空!', trigger: ['blur', 'change'] },
        supplementarytype: { required: true, message: '不能为空!', trigger: 'change' }
      },
      diaTableForm: {
        materiData: []
      },
      originallistPreviewList: [''],
      tempList: [],
      originallistId: '',
      materiData: [],
      hasMateriData: [],
      fillingsData: [],
      expList: [],
      materiallist: [],
      querys: JSON.parse(decodeURI(this.$route.query.data)),
      supplementarytype: '',
      loading: false,
      load1: false,
      load2: false,
      load3: false,
      load4: false,
      loading1: false,
      loading2: false,
      loading3: false,
      loading4: false,
      loadtype: '',
      supplemenFlag: false,
      surveyFlag: false,
      supplementpartFlag: false,
      /*'supplementmode', 'material_type', 'casestep', 'status_supple', 'supplementaryconclusion'*/
      supplementmodeOptions: [],
      material_typeOptions: [],
      casestepOptions: [],
      status_suppleOptions: [],
      supplementaryconclusionOptions: []
    }
  },
  mounted() {
   if (this.dictList!=null && this.dictList.length!=0){
     this.supplementmodeOptions=this.dictList.find(item=>{
       return item.dictType=='supplementmode'
     }).dictDate
     this.material_typeOptions=this.dictList.find(item=>{
       return item.dictType=='material_type'
     }).dictDate
     this.casestepOptions=this.dictList.find(item=>{
       return item.dictType=='casestep'
     }).dictDate
     this.status_suppleOptions=this.dictList.find(item=>{
       return item.dictType=='status_supple'
     }).dictDate
     this.supplementaryconclusionOptions=this.dictList.find(item=>{
       return item.dictType=='supplementaryconclusion'
     }).dictDate
   }
  },
  computed: {
    dialogVis: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  watch: {
    imageLoad: function(val) {
      this.$nextTick(function() {
        if (this.loadtype === '1') {
          this.loading1 = val
        } else if (this.loadtype === '2') {
          this.loading2 = val
        } else if (this.loadtype === '3') {
          this.loading3 = val
        } else if (this.loadtype === '4') {
          this.loading4 = val
        }
      })
    },
  },
  created() {
    this.monitor()
  },
  methods: {
    selectChange(val, row){
      this.materiallist = val
      let arr = []
      val.map((item) =>{
        arr.push(item.materiatype)
        this.diaForm.materialtype = arr
      })
      if (val.length === 0) {
        this.diaForm.materialtype = arr
      }
    },
    formateMode(row, column) {
      let mode = ''
      if (row.supplementarymode) {
        row.supplementarymode.split(',').map((item, i) => {
          /*this.dict.label.supplementmode[item]*/this.selectDictLabel(this.supplementmodeOptions,item) ? mode += `｜${/*this.dict.label.supplementmode[item]*/this.selectDictLabel(this.supplementmodeOptions,item)}` : mode = ''
        })
      }
      return mode.slice(1)
    },
    formateMaterialtype(row, column) {
      let type = ''
      if (row.materialtype) {
        row.materialtype.split(',').map((item, i) => {
          /*this.dict.label.material_type[item] */this.selectDictLabel(this.material_typeOptions,item)? type += `｜${/*this.dict.label.material_type[item]*/this.selectDictLabel(this.material_typeOptions,item)}` : type = ''
        })
      }
      return type.slice(1)
    },
    // 判断勾选方式
    juideMetods() {
      if (parseInt(this.fillsCount)>=1) {
        this.diaForm.pushtype = ['04','03']
      } else {
        this.diaForm.pushtype = ['03']
      }
    },
    openHandle(){
      //todo 调整最小化按钮位置
      this.hasMateriData = []
      if(this.materiallist.length){
        this.$refs.multipleTable.clearSelection()
      }
      get('supplementmode').then(res => {
        this.pushtypeArr = res.content || []
        this.pushtypeArr.map((item,index) =>{
          if (item.value == '02' && (this.email == '' || this.email == null || this.email == undefined)) {
            this.pushtypeArr[index].disabled = true
            // item.disabled = true
          } else {
            this.pushtypeArr[index].disabled = false
          }
        })
        this.pushtypeArr = [...this.pushtypeArr]
      })
      // this.pushtypeArr =  this.dict.supplementmode || []
      // 材料类型和列表排序对应
      //
      this.supplemenFlag = this.tabLabels.supplementlist.length ? !!this.tabLabels.supplementlist[0].total : false
      this.surveyFlag = this.tabLabels.surveylist.length ? !!this.tabLabels.surveylist[0].total : false
      this.supplementpartFlag= this.tabLabels.supplementpartlist.length ? !!this.tabLabels.supplementpartlist[0].total : false
      // this.expList = this.loadAll()
      this.pastSupplementaryList()
      this.getAntMateriaList()
      this.juideMetods()
      // 打开时初始化影像
      this.initImages()
    },
    // 初始化影像
    initImages() {
      this.activeName1 = '00'
      this.activeName2 = '00'
      this.activeName3 = '00'
      this.activeName4 = '00'
      this.$emit('imageUpdate')
      this.$emit('getFileCount')
    },
    pastSupplementaryList() {
      let params = {
        relationno: decrypt(this.querys.claimno),
        type: this.type
      }
      pastSupplementaryList(params).then(res => {
        if (res.status === '1') {
          this.fillingsData = res.data
        } else {
          this.$message({ message: '查询补材任务列表出错！', type: 'error' })
        }
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
    createFilter(queryString) {
      return (expList) => {
        return (expList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      }
    },
    querySearch(queryString, cb) {
      let expList = this.expList
      let results = queryString ? expList.filter(this.createFilter(queryString)) : expList;
      cb(results ? results : [])
    },
    getAntMateriaList() {
      getAntMateriaList(decrypt(this.querys.claimno)).then(res => {
        if (res.status === '1') {
          // this.materiData = res.data
          // this.diaTableForm.materiData = res.data
          this.diaTableForm.materiData = []
          this.materiData = []
          this.material_typeOptions.forEach(val => {
            res.data.forEach(item => {
              if (val.value === item.materiatype) {
                this.diaTableForm.materiData.push(item)
                this.materiData.push(item)
              }
            })
          })
        } else {
          this.$message({ message: '查询材料列表出错！', type: 'error' })
        }
      })
    },
    supplementarytypeChange(row) {
      this.supplementarytype = row.supplementarytype
    },
    loadAll() {
      // let type = '02'
      // getTempalte(type).then(res =>{
      //   if (res.status == '1') {
      //     this.expList = res.data || []
      //   } else {
      //     this.$message.error('查询模版消息错误！')
      //   }
      // })
      this.expList = []
      const params = {
        templatetype: '02',
        supplementarymode: this.diaForm.pushtype ? this.diaForm.pushtype.sort().join(',') : '',
        // insurancecode: this.insurancecode ? this.insurancecode :'',
        // dutycode: this.dutycode ? this.dutycode : '',
        circulationvalue1: this.diaForm.pushtype.find((item) => { return item==='01' }) ? this.supplementarytype : '',
        circulationvalue2: this.diaForm.materialtype ? this.diaForm.materialtype.sort().join(',') : ''
      }
      getContents(params).then(res =>{
        if (res.status == '1') {
          res.data.forEach(item => {
            this.expList.push({ value: item })
          })
          // this.expList = res.data || []
        }
      })
    },
    dialogClosed() {
      this.$refs.diaForm.resetFields()
      if(this.$refs.diaTableForm){
        this.$refs.diaTableForm.resetFields()
      }
      this.$emit('input', false)
      // 关闭时初始化影像
      this.initImages()
    },
    savefillings() {
      this.$refs.diaForm.validate((valid) => {
        if (valid) {
          let params = {
            claimno: decrypt(this.querys.claimno),
            ...this.diaForm,
            materiallist: this.materiallist,
            type: this.type //01 影像件 02 物理件
          }
          let currentCase= this.casestepOptions.find((item) => {
            return item.label === this.querys.node
          })
          params.tasknode = currentCase.value
          if(this.diaForm.pushtype.indexOf('01')!==-1){
            if(!this.materiallist.length){
              this.$message({ message: '请至少选择一条数据！', type: 'warning' })
            } else {
              let checkIdx = []
              this.diaTableForm.materiData.map((item, i) => {
                this.materiallist.map(list => {
                  if(item.materiatype===list.materiatype){
                    checkIdx.push(i)
                  }
                })
              })
              let checkFlag = []
              checkIdx.map(item => {
                this.$refs.diaTableForm.validateField(['materiData.'+item+'.supplementarytype', 'materiData.'+item+'.description'],empty => {
                  if(empty){
                    checkFlag.push(0)
                  }
                })
              })
              if(!checkFlag.includes(0)) {
                this.startStuff(params)
              }
            }
          } else {
            this.startStuff(params)
          }
        } else {
          return false
        }
      })
    },
    startStuff(params){
      this.loading = true
      startStuff(params).then(res => {
        this.loading = false
        if (res.status === '1') {
          this.$message({ message: '操作成功！', type: 'success' })
          if(params.type==='01'){
            this.$router.go(-1)
            this.$emit('updateStatus','show')
            this.dialogClosed()
          } else {
            this.$refs.diaForm.resetFields()
            this.$refs.diaForm.clearValidate()
            if(this.materiallist.length){
              this.$refs.diaTableForm.resetFields()
              this.$refs.multipleTable.clearSelection()
            }
            this.pastSupplementaryList()
          }
        } else {
          this.$message({ message: res.data.errMsg, type: 'warning' })
        }
      })
    },
    changePushType(value) {
      let flag = value.some((item) =>{
        return item == '01'
      })
      if(flag) {
        this.$nextTick(()=>{
          this.diaTableForm.materiData.forEach(row => {
            if(this.hasMateriData.indexOf(row.materiatype) >= 0){
              this.$refs.multipleTable.toggleRowSelection(row,true);
            }
          })
        })
      }
    },
    changeMaterialType(value) {
      this.hasMateriData = []
      this.hasMateriData = value
      this.$nextTick(()=>{
       this.diaTableForm.materiData.forEach(row => {
          if(this.hasMateriData.indexOf(row.materiatype) >= 0){
            this.$refs.multipleTable.toggleRowSelection(row,true);
          } else {
            this.$refs.multipleTable.toggleRowSelection(row,false);
          }
        })
      })
    },
    load(type, page, total) {
      this.loadtype = type
      if (type === '1') {
        if (this.page0 < Math.ceil(total / 5)) {
          this.page0++
          this.loadMore('1', this.page0, this.activeName1)
          this.load1 = false
        } else {
          this.load1 = true
        }
      } else if (type === '2') {
        if (this.page1 < Math.ceil(total / 5)) {
          this.page1++
          this.loadtype = type
          this.loadMore('2', this.page1, this.activeName2)
          this.load2 = false
        } else {
          this.load2 = true
        }
      } else if (type === '3') {
        if (this.page2 < Math.ceil(total / 5)) {
          this.page2++
          this.loadtype = type
          this.loadMore('3', this.page2, this.activeName3)
          this.load3 = false
        } else {
          this.load3 = true
        }
      } else if (type === '4') {
        if (this.page3 < Math.ceil(total / 5)) {
          this.page3++
          this.loadtype = type
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
      // this.$nextTick(function() {
        // const scrollTop = document.getElementById('image' + name + type).scrollTop
        let scrollTop = window.event.target.scrollTop
        // const scrollHeight = document.getElementById('image' + name + type).scrollHeight
        let scrollHeight = window.event.target.scrollHeight
        // const offsetHeight = document.getElementById('image' + name + type).offsetHeight
        let offsetHeight = window.event.target.offsetHeight
        if ((offsetHeight + scrollTop) - scrollHeight === 0) {
          this.load(type, page, total, name)
        }
      // })

    },
    toMinimize() {
      this.$emit('toMinimize')
    }
  }
}
</script>
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
  .el-row /deep/ .el-col-8 {
    height: 40px;
  }
  .el-form-item /deep/ label {
    font-weight: normal;
  }
  .el-tabs /deep/ .el-tabs__item {
    width: 10.5%;
  }
  span {
    color: #555;
    line-height: 2.5;
  }

  .imgList_div {
    height: 180px;
    width: 100%;
    overflow: auto;
  }
  .block {
    border: 1px solid #f6f6f6;
    width: 20%;
    height: 190px;
    text-align:center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 10px 0;
  }
  .block .el-image {
    margin: 0 auto;
  }
  .time-wrapper {
    height: 16px;
    line-height: 16px;
    font-size: 12px;
    color: #909399;
    margin-bottom: 7px;
    /* padding-top: 10px; */
  }
</style>
