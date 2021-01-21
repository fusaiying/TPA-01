<template>
  <el-card style="margin-top:10px">
    <el-collapse v-model="activeNames5">
      <el-collapse-item name="1">
        <template slot="title">
          <span style="font-size:16px;color:black">案件标签</span>
          <span v-show="!activeNames5.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 80px;">展开</span>
        </template>
        <el-form style="padding-bottom: 30px;" :rules="tableFormRules" ref="caselabelFrom" :model="caselabelFrom" size="mini">
          <el-row>
            <el-col :span="12">
              <el-table
                size="small"
                ref="table"
                highlight-current-row
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                tooltip-effect="dark"
                :data="caselabelInfo.data.filter((item,index) => index%2 == 0)"
                style="width: 100%;">
                <el-table-column align="center" prop="area" label="关键内容">
                  <template slot-scope="scope">
                    <el-form-item v-if="!scope.row.id" :rules="{required: true, message:'不能为空！'}" >
                      <el-select v-model="scope.row.keyWord" @change="changecase(scope.$index)"  class="item-width" placeholder="请选择">
                        <el-option v-for="(item,index) in keyWordArr" :label="item.value" :value="item.key" :key="item.index"
                          :disabled="caselabelInfo.data.find(f=>{return f.keyWord == item.key})!=null"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item v-else>
                      <span class="form-span">{{ (scope.row.keyWord == '01' ? '身故案件' : '病历修改痕迹') }}</span>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="hospitalname" label="是/否">
                  <template slot-scope="scope">
                    <el-form-item v-if="!scope.row.id" :rules="{required: true, message:'不能为空！'}">
                      <el-select v-model="scope.row.isKeyWord"  class="item-width" placeholder="请选择">
                        <el-option label="待定" value="01"/>
                        <el-option label="是" value="02"/>
                        <el-option label="否" value="03"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item v-else>
                      <span class="form-span">{{ (scope.row.isKeyWord == '01' ? '待定' : scope.row.isKeyWord == '02' ? '是' : '否' ) }}</span>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="diagnosistype" label="原因">
                  <template slot-scope="scope">
                    <el-form-item v-if="!scope.row.id" :rules="{required: true, message:'不能为空！'}">
                      <el-input v-model="scope.row.keyWordReason" type="textarea" autosize class="item-width" clearable maxlength="200" show-word-limit autocomplete="off"/>
                    </el-form-item>
                    <el-form-item v-else>
                      <el-tooltip class="item" effect="dark" :content="scope.row.keyWordReason" placement="top-start">
                        <span type="text" class="overEslap_class">{{ scope.row.keyWordReason }}</span>
                      </el-tooltip>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="140" align="center">
                  <template slot-scope="scope">
                    <el-button v-if="scope.row.id" :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index * 2, scope.row)">编辑</el-button>
                    <el-button v-if="!scope.row.id" :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="save(scope.$index * 2, scope.row)">保存</el-button>
                    <el-button :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="delCaseLabels(scope.row,scope.$index * 2)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-col>
            <el-col :span="12">
              <el-table
                size="small"
                ref="table"
                highlight-current-row
                :header-cell-style="{color:'black',background:'#f8f8ff'}"
                tooltip-effect="dark"
                :data="caselabelInfo.data.filter((item,index) => index%2 !== 0)"
                style="width: 100%;">
                <el-table-column align="center" prop="area" label="关键内容">
                  <template slot-scope="scope">
                    <el-form-item v-if="!scope.row.id" :rules="{required: true, message:'不能为空！'}">
                      <el-select v-model="scope.row.keyWord" @change="changecase(scope.$index)"  class="item-width" placeholder="请选择">
                        <el-option v-for="(item,index) in keyWordArr" :label="item.value" :value="item.key" :key="item.index"
                          :disabled="caselabelInfo.data.find(f=>{return f.keyWord == item.key})!=null"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item v-else>
                      <span class="form-span">{{ (scope.row.keyWord == '01' ? '身故案件' : '病历修改痕迹') }}</span>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="hospitalname" label="是/否">
                  <template slot-scope="scope">
                    <el-form-item v-if="!scope.row.id" :rules="{required: true, message:'不能为空！'}">
                      <el-select v-model="scope.row.isKeyWord"  class="item-width" placeholder="请选择">
                        <el-option label="待定" value="01"/>
                        <el-option label="是" value="02"/>
                        <el-option label="否" value="03"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item v-else>
                      <span class="form-span">{{ (scope.row.isKeyWord == '01' ? '待定' : scope.row.isKeyWord == '02' ? '是' : '否' ) }}</span>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column align="center" prop="diagnosistype" label="原因">
                  <template slot-scope="scope">
                    <el-form-item v-if="!scope.row.id" :rules="{required: true, message:'不能为空！'}">
                      <el-input v-model="scope.row.keyWordReason" type="textarea" autosize class="item-width" clearable maxlength="200" show-word-limit autocomplete="off"/>
                    </el-form-item>
                    <el-form-item v-else>
                      <el-tooltip class="item" effect="dark" :content="scope.row.keyWordReason" placement="top-start">
                        <span type="text" class="overEslap_class">{{ scope.row.keyWordReason }}</span>
                      </el-tooltip>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="140" align="center">
                  <template slot-scope="scope">
                    <el-button v-if="scope.row.id" :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index * 2 +1, scope.row)">编辑</el-button>
                    <el-button v-if="!scope.row.id" :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="save(scope.$index * 2 +1, scope.row)">保存</el-button>
                    <el-button :disabled="status==='show'" style="margin-bottom: 10px;" type="text" size="mini" @click="delCaseLabels(scope.row,scope.$index * 2 +1)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-col>
            <el-button :disabled="status==='show'" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel(0)"> + 添加</el-button>
          </el-row>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script>
import { addCaseLabels, delCaseLabels } from '@/api/claim/handleDeal'
export default {
  data () {
    return {
      activeNames5: ['1'],
      caselabelFrom: {},
      tableFormRules: {},
      keyWordArr: [
        {
          key: '01',
          value: '身故案件'
        },
        {
          key: '02',
          value: '病历修改痕迹'
        },
      ],
    }
  },
  props: {
    caselabelInfo: {
      type: Object,
      default: function() {
        return {}
      },
    },
    claimNo: String,
    status: String,
  },
  methods: {
    addHandel() {
      const field = {
        isDieCase: '',
        categorycode: '',
        reason:  '',
        isKeyWord: '03',
        id: ''
      }
      this.caselabelInfo.data.push(field)
      setTimeout(() => {
        this.$refs.table.setCurrentRow(field)
      }, 10)
    },
    editHandle(index,row) {
      this.$set(this.caselabelInfo.data[index],'id', '')
      this.caselabelInfo.data = [...this.caselabelInfo.data]
    },
    delCaseLabels(row,index) {
      if (row.caseLanelsNo == undefined) {
        this.caselabelInfo.data.splice(index,1)
        this.caselabelInfo.data = [...this.caselabelInfo.data]
      } else {
        let params = {}
        params.caseLanelsNo = row.caseLanelsNo
        delCaseLabels(params).then(res =>{
          if (res.status == '1') {
            this.$message.success('删除成功！')
            this.$emit('getPublicList')
          } else {
            this.$message.error('删除失败！')
          }
        })
      }
    },
    delEventHandle() {},
    changecase() {},
    save(index,row) {
      if (row.keyWordReason!== '' && row.keyWordReason!== null  && row.keyWordReason!== undefined) {
        row.keyWordReason  = row.keyWordReason + '  '
        row.keyWordReason = row.keyWordReason.replace(/\s+/g, "");
      }
      if (row.keyWord == undefined || row.isKeyWord == undefined || row.keyWordReason == '') {
        this.$message.warning('请填写完整的信息！')
        return false
      }
      let params = {}
      params.caseLanelsNo = row.caseLanelsNo ? row.caseLanelsNo : ''
      params.claimNo = this.claimNo
      params.keyWord = row.keyWord || ''
      params.isKeyWord = row.isKeyWord || ''
      params.keyWordReason = row.keyWordReason
      addCaseLabels(params).then(res =>{
        if (res.status == '1') {
          this.$message.success('保存成功！')
          this.$emit('getPublicList')
        } else {
          this.$message.error('查询案件标签错误！')
        }
      })
    }
  },
}
</script>
<style scoped>
.item-width{
  width: 120px;
}
.overEslap_class{
  color: #555;
  font-size: 12px;
  width:50%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.form-span {
  font-size: 12px;
  width:50%;
  display: inline-block;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
</style>
