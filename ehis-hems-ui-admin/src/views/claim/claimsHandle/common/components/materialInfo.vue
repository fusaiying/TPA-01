
<template>
  <div>
    <el-card class="box-card" style="margin-top: 10px;">
      <el-collapse v-model="activeNames">
        <el-collapse-item title="材料信息" name="1" style="position: relative;">
          <template slot="title">
            <span style="font-size:16px;color:black">材料信息</span>
            <span v-show="!activeNames.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
          </template>
          <span style="position: absolute;right: 40px;top: 0;">
            <el-button v-if="node==='review'||node==='complex'||node==='spotCheck'||node==='correct' " type="primary" size="mini" @click="fillingsHandle">补充物理件</el-button>
            <el-button :disabled="((status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false) || acceptFlag" type="primary" size="mini" @click="saveHandle">保 存</el-button>
          </span>
          <el-form :disabled="((status==='show'||node==='complex'||node==='spotCheck'||node==='correct' || node ==='physical')?true:false) || acceptFlag" ref="materialInfo" :rules="tableFormRules" :model="materialInfo" label-width="0" size="mini">
            <el-row :gutter="20">
              <!--              <el-table-->
              <!--                ref="table"-->
              <!--                :header-cell-style="{color:'black',background:'#f8f8f8'}"-->
              <!--                :data="materialInfo.data"-->
              <!--                size="small"-->
              <!--                tooltip-effect="dark"-->
              <!--                style="width: 100%;">-->
              <!--                <el-table-column align="center" label="材料类型" prop="materialtype">-->
              <!--                  <template slot-scope="scope">-->
              <!--                    <el-form-item v-if="scope.row.editFlag && scope.row.type" :prop="'data.' + scope.$index + '.materialtype'" :rules="tableFormRules.materialtype" style="margin: 9px 0">-->
              <!--                      <el-select v-model="scope.row.materialtype" placeholder="请选择" clearable-->
              <!--                                 @change="(val)=>getCount(val, scope.$index)">-->
              <!--                        <el-option v-for="item in dict.material_type" :label="item.label" :value="item.value" :key="item.value"-->
              <!--                                   :disabled="materialInfo.data.find(f=>{return f.materialtype == item.value})!=null"/>-->
              <!--                      </el-select>-->
              <!--                    </el-form-item>-->
              <!--                    <span v-else>{{ dict.label.material_type[scope.row.materialtype]}}</span>-->
              <!--                  </template>-->
              <!--                </el-table-column>-->
              <!--                <el-table-column align="center" label="提交时间" prop="receiveddate">-->
              <!--                  <template slot-scope="scope">-->
              <!--                    <el-form-item v-if="scope.row.editFlag">-->
              <!--                      <span style="font-size: 12px;">{{scope.row.receiveddate}}</span>-->
              <!--                    </el-form-item>-->
              <!--                    <span style="font-size: 12px;" v-else>{{scope.row.receiveddate}}</span>-->
              <!--                  </template>-->
              <!--                </el-table-column>-->
              <!--                <el-table-column align="center" label="原件数量" prop="originalnum">-->
              <!--                  &lt;!&ndash;                    <template slot-scope="scope">&ndash;&gt;-->
              <!--                  &lt;!&ndash;                      <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.originalnum'" :rules="tableFormRules.originalnum">&ndash;&gt;-->
              <!--                  &lt;!&ndash;                        <el-input-number :min="0" v-model="scope.row.originalnum" autocomplete="off" step-strictly clearable/>&ndash;&gt;-->
              <!--                  &lt;!&ndash;                      </el-form-item>&ndash;&gt;-->
              <!--                  &lt;!&ndash;                      <span v-else>{{scope.row.originalnum}}</span>&ndash;&gt;-->
              <!--                  &lt;!&ndash;                    </template>&ndash;&gt;-->
              <!--                </el-table-column>-->
              <!--                <el-table-column align="center" label="复印件数量" prop="copynum">-->
              <!--                  &lt;!&ndash;                    <template slot-scope="scope">&ndash;&gt;-->
              <!--                  &lt;!&ndash;                      <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.copynum'" :rules="tableFormRules.copynum">&ndash;&gt;-->
              <!--                  &lt;!&ndash;                        <el-input-number :min="0" v-model="scope.row.copynum" autocomplete="off" step-strictly clearable/>&ndash;&gt;-->
              <!--                  &lt;!&ndash;                      </el-form-item>&ndash;&gt;-->
              <!--                  &lt;!&ndash;                      <span v-else>{{scope.row.copynum}}</span>&ndash;&gt;-->
              <!--                  &lt;!&ndash;                    </template>&ndash;&gt;-->
              <!--                </el-table-column>-->
              <!--                <el-table-column min-width="300" align="center" label="材料备注" prop="remarks">-->
              <!--                  <template slot-scope="scope">-->
              <!--                    &lt;!&ndash;                      <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.remarks'">&ndash;&gt;-->
              <!--                    &lt;!&ndash;                        <el-input type="textarea" maxlength="200" show-word-limit :rows="2" style="width: 290px;" v-model="scope.row.remarks" placeholder="请输入内容"></el-input>&ndash;&gt;-->
              <!--                    &lt;!&ndash;                      </el-form-item>&ndash;&gt;-->
              <!--                    &lt;!&ndash;                      <span v-else>{{scope.row.remarks}}</span>&ndash;&gt;-->
              <!--                    <el-form-item :prop="'data.' + scope.$index + '.remarks'" style="margin-bottom: 0">-->
              <!--                      <el-input type="textarea" maxlength="200" show-word-limit :rows="2" style="width: 90%;" v-model="scope.row.remarks" placeholder="请输入内容"></el-input>-->
              <!--                    </el-form-item>-->
              <!--                  </template>-->
              <!--                </el-table-column>-->
              <!--                <el-table-column align="center" label="操作">-->
              <!--                  <template slot-scope="scope">-->
              <!--                    <el-form-item v-if="scope.row.editFlag && scope.row.type">-->
              <!--                      &lt;!&ndash; <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="saveHandle(scope.$index, scope.row)">保存</el-button> &ndash;&gt;-->
              <!--                      <el-button  style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row, 0)">删除</el-button>-->
              <!--                    </el-form-item>-->
              <!--                    <span v-else>-->
              <!--&lt;!&ndash;                        <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="editHandle(scope.$index, scope.row)">编辑</el-button>&ndash;&gt;-->
              <!--                        <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index, scope.row, 1)">删除</el-button>-->
              <!--                      </span>-->
              <!--                  </template>-->
              <!--                </el-table-column>-->
              <!--              </el-table>-->
              <el-col :span="12">
                <el-table
                  ref="table"
                  :header-cell-style="{color:'black',background:'#f8f8ff'}"
                  :data="materialInfo.data.filter((item, ind) => ind % 2 === 0)"
                  size="small"
                  tooltip-effect="dark"
                  class="material-table"
                  style="width: 100%;">
                  <el-table-column align="center" label="材料类型" prop="materialtype">
                    <template slot-scope="scope">
                      <el-form-item v-if="scope.row.editFlag && scope.row.type" :prop="'data.' + (scope.$index * 2) + '.materialtype'" :rules="tableFormRules.materialtype">
                        <el-select v-model="scope.row.materialtype" placeholder="请选择" clearable
                                   @change="(val)=>{getCount(val, scope.$index * 2)}">
                          <el-option v-for="item in materialType" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"
                                     :disabled="materialInfo.data.find(f=>{return f.materialtype == item.dictValue})!=null"/>
                        </el-select>
                      </el-form-item>
                      <span v-else>{{ selectDictLabel(material_typeOptions, scope.row.materialtype)}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="原件数量" prop="originalnum">
                    <template slot-scope="scope">
                      <el-form-item v-if="node==='accept'" :prop="'data.' + scope.$index * 2 + '.originalnum'" :rules="tableFormRules.originalnum">
                        <el-input-number :min="0" v-model="scope.row.originalnum" style="width: 100px" autocomplete="off" step-strictly clearable/>
                      </el-form-item>
                      <span v-else>{{scope.row.originalnum}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="复印件数量" prop="copynum">
                    <template slot-scope="scope">
                      <el-form-item v-if="node==='accept'" :prop="'data.' + scope.$index * 2 + '.copynum'" :rules="tableFormRules.copynum">
                        <el-input-number :min="0" v-model="scope.row.copynum" style="width: 100px" autocomplete="off" step-strictly clearable/>
                      </el-form-item>
                      <span v-else>{{scope.row.copynum}}</span>
                    </template>
                  </el-table-column>
                  <!--                  <el-table-column align="center" label="原件数量" style="padding: 0" prop="originalnum"></el-table-column>-->
                  <!--                  <el-table-column align="center" label="复印件数量" style="padding: 0" prop="copynum"></el-table-column>-->
                  <el-table-column min-width="150" align="center" label="材料备注" prop="remarks">-->
                    <template slot-scope="scope">
                      <!--                      <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.remarks'">-->
                      <!--                        <el-input type="textarea" maxlength="200" show-word-limit :rows="2" style="width: 290px;" v-model="scope.row.remarks" placeholder="请输入内容"></el-input>-->
                      <!--                      </el-form-item>-->
                      <!--                      <span v-else>{{scope.row.remarks}}</span>-->
                      <el-form-item v-if="status!=='show'&&node!=='complex'&&node!=='spotCheck'&&node!=='correct'" :prop="'data.' + scope.$index * 2 + '.remarks'" style="margin-bottom: 0">
                        <el-input type="textarea" maxlength="50" show-word-limit :rows="2" style="width: 90%;" v-model="scope.row.remarks" placeholder="请输入内容"></el-input>
                      </el-form-item>
                      <el-tooltip v-else class="item" effect="dark" :content="scope.row.remarks" placement="top-start">
                        <span class="show-little">{{scope.row.remarks}}</span>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="操作">
                    <template slot-scope="scope">
                      <el-form-item v-if="scope.row.editFlag && scope.row.type">
                        <el-button  style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index * 2, scope.row, 0)">删除</el-button>
                      </el-form-item>
                      <span v-else>
                        <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index * 2, scope.row, 1)">删除</el-button>
                      </span>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
              <el-col :span="12">
                <el-table
                  ref="table"
                  :header-cell-style="{color:'black',background:'#f8f8f8'}"
                  :data="materialInfo.data.filter((item, ind) => ind % 2 !== 0)"
                  size="small"
                  tooltip-effect="dark"
                  class="material-table"
                  style="width: 100%;">
                  <el-table-column align="center" label="材料类型" prop="materialtype">
                    <template slot-scope="scope">
                      <el-form-item v-if="scope.row.editFlag && scope.row.type" :prop="'data.' + (scope.$index * 2 + 1) + '.materialtype'" :rules="tableFormRules.materialtype">
                        <el-select v-model="scope.row.materialtype" placeholder="请选择" clearable
                                   @change="(val)=>getCount(val, scope.$index * 2 + 1)">
                          <el-option v-for="item in materialType" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"
                                     :disabled="materialInfo.data.find(f=>{return f.materialtype == item.dictValue})!=null"/>
                        </el-select>
                      </el-form-item>
                      <span v-else>{{selectDictLabel(material_typeOptions, scope.row.materialtype)}}</span>
                    </template>
                  </el-table-column>
                  <!--                  <el-table-column align="center" style="padding: 0" label="原件数量" prop="originalnum"></el-table-column>-->
                  <!--                  <el-table-column align="center" style="padding: 0" label="复印件数量" prop="copynum"></el-table-column>-->
                  <el-table-column align="center" label="原件数量" prop="originalnum">
                    <template slot-scope="scope">
                      <el-form-item v-if="node==='accept'" :prop="'data.' + (scope.$index * 2 + 1) + '.originalnum'" :rules="tableFormRules.originalnum">
                        <el-input-number :min="0" style="width: 100px" v-model="scope.row.originalnum" autocomplete="off" step-strictly clearable/>
                      </el-form-item>
                      <span v-else>{{scope.row.originalnum}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="复印件数量" prop="copynum">
                    <template slot-scope="scope">
                      <el-form-item v-if="node==='accept'" :prop="'data.' + (scope.$index * 2 + 1) + '.copynum'" :rules="tableFormRules.copynum">
                        <el-input-number :min="0" style="width: 100px" v-model="scope.row.copynum" autocomplete="off" step-strictly clearable/>
                      </el-form-item>
                      <span v-else>{{scope.row.copynum}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column min-width="150" align="center" label="材料备注" prop="remarks">-->
                    <template slot-scope="scope">
                      <!--                      <el-form-item v-if="scope.row.editFlag" :prop="'data.' + scope.$index + '.remarks'">-->
                      <!--                        <el-input type="textarea" maxlength="200" show-word-limit :rows="2" style="width: 290px;" v-model="scope.row.remarks" placeholder="请输入内容"></el-input>-->
                      <!--                      </el-form-item>-->
                      <!--                      <span v-else>{{scope.row.remarks}}</span>-->
                      <el-form-item v-if="status!=='show'&&node!=='complex'&&node!=='spotCheck'&&node!=='correct'" :prop="'data.' +( scope.$index * 2 + 1) + '.remarks'" style="margin-bottom: 0">
                        <el-input type="textarea" maxlength="50" show-word-limit :rows="2" style="width: 90%;" v-model="scope.row.remarks" placeholder="请输入内容"></el-input>
                      </el-form-item>
                      <el-tooltip v-else class="item" effect="dark" :content="scope.row.remarks" placement="top-start">
                        <span class="show-little">{{scope.row.remarks}}</span>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="操作">
                    <template slot-scope="scope">
                      <el-form-item v-if="scope.row.editFlag && scope.row.type">
                        <el-button  style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index * 2 + 1, scope.row, 0)">删除</el-button>
                      </el-form-item>
                      <span v-else>
                        <el-button style="margin-bottom: 10px;" type="text" size="mini" @click="delHandle(scope.$index * 2 + 1, scope.row, 1)">删除</el-button>
                      </span>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
              <el-button :disabled="((status==='show'||node==='complex'||node==='spotCheck'||node==='correct')?true:false)||!claimno" type="text" size="mini" style="text-align: center;width: 100%;border: 1px dashed #dfe6ec;margin: 10px 0 20px;" @click="addHandel"> + 添加</el-button>
              <!--              <el-col :span="24" style="padding: 0 10%; margin-bottom: 10px;display: flex;">-->
              <!--                <span style="display: inline-block;width: 100px">材料备注：</span>-->
              <!--                <el-input type="textarea" maxlength="200" show-word-limit :rows="2" style="width: 100%;" v-model="remarks" placeholder="请输入内容"></el-input>-->
              <!--              </el-col>-->
            </el-row>
          </el-form>
          <el-row>
            <el-col v-if="node!=='report' && node !== 'physical'" :span="4">
              <!-- <el-checkbox :disabled="acceptFlag" v-model="materialInfo.returnChecked" @change="materiaRefundList">是否退件</el-checkbox> -->
              <el-checkbox :disabled="acceptFlag" v-model="materialInfo.returnChecked">是否退件</el-checkbox>
            </el-col>
            <!-- <el-col v-if="node!=='report'&&node!=='accept'&&node!=='input'" :span="4">
              <el-checkbox v-model="materialInfo.FillingsChecked">是否补材</el-checkbox>
            </el-col> -->
          </el-row>
          <el-card v-show="materialInfo.FillingsChecked" class="box-card" style="margin-top: 10px;">
            <div slot="header" class="clearfix">
              <span>补材信息</span>
              <span style="float: right;">
                <el-button type="primary" size="mini">发起补材</el-button>
              </span>
            </div>
            <el-table
              :header-cell-style="{color:'black',background:'#f8f8ff'}"
              :data="FillingsaTable"
              size="small"
              highlight-current-row
              tooltip-effect="dark">
              <el-table-column type="selection" align="center"/>
              <el-table-column property="type" label="资料类型" align="center"/>
              <el-table-column property="policyNo" label="是否原件" align="center"/>
              <el-table-column property="policyNo" label="提交时间" align="center"/>
              <el-table-column property="policyNo" label="原件数量" align="center"/>
              <el-table-column property="policyNo" label="退件数量" align="center"/>
              <el-table-column label="操作" align="center">
                <template slot-scope="">
                  <el-button type="text" size="mini">编辑</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-form ref="FillingsForm" :rules="FillingsFormRules" :model="FillingsForm" style="margin-top: 40px;" label-width="120px" size="mini">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="补材渠道：" prop="qd">
                    <el-select v-model="FillingsForm.qd" class="item-width" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="补充文件类型：" prop="way">
                    <el-select v-model="FillingsForm.type" class="item-width" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="补材模板：" prop="phone">
                    <el-select v-model="FillingsForm.template" class="item-width" placeholder="请选择" clearable>
                      <el-option label="一" value="1"/>
                      <el-option label="二" value="2"/>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="补材说明：" prop="mark">
                    <el-input v-model="FillingsForm.mark" show-word-limit maxlength="50" autocomplete="off"/>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
          <fillsModul v-model="fillsDia" :email="email" :tab-labels="tabLabels" :status="status" :node="node" :image-info="imageInfo" :claimno="claimno" type="02" @changeList="changeList" @imageUpdate="imageUpdate"/>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <el-card v-show="materialInfo.returnChecked && node !== 'physical'" class="box-card" style="margin-top: 10px;">
      <el-collapse v-model="activeNames1">
        <el-collapse-item title="退件信息" name="1" style="position: relative;">
          <template slot="title">
            退件信息
            <span v-show="!activeNames1.length" style="font-size: 12px;color: #409EFF;position: absolute;right: 40px;">展开</span>
          </template>
          <span style="position: absolute;right: 40px;top: 0;">
            <el-button :disabled="(status==='show'?true:false)||node==='spotCheck'||node==='correct' || acceptFlag" type="primary" size="mini" @click="confirmHandle">保存</el-button>
          </span>
          <el-form :disabled="(status==='show'?true:false)||node==='spotCheck'||node==='correct' || acceptFlag" ref="returnForm" :rules="returnFormRules" :model="returnForm" label-width="120px" size="mini">
            <el-row>
              <el-col :span="8">
                <el-form-item label="收件人姓名：" prop="addressee">
                  <el-input v-model="returnForm.addressee" maxlength="30" show-word-limit class="item-width" autocomplete="off"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="退件方式：" prop="refundtype">
                  <el-select v-model="returnForm.refundtype" class="item-width" placeholder="请选择" clearable
                             @change="refundtypeChange">
                    <el-option v-for="item in refund_typeOptions" :label="item.dictLabel" :value="item.dictValue" :key="item.dictValue"/>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="手机：" prop="telephone">
                  <el-input v-model="returnForm.telephone" class="item-width" autocomplete="off"/>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="地址：" prop="address">
                  <el-input show-word-limit maxlength="200" v-model="returnForm.address" autocomplete="off"/>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="备注：" prop="remarks">
                  <el-input :rows="2" v-model="returnForm.remarks" type="textarea" show-word-limit maxlength="200" autocomplete="off"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-table
            v-show="returnForm.refundtype==='1'"
            :header-cell-style="{color:'black',background:'#f8f8ff'}"
            :data="tableData"
            ref="multipleTable"
            @selection-change="refundSelChange"
            size="small"
            highlight-current-row
            tooltip-effect="dark"
            v-loading="refundLoading">
            <el-table-column type="selection" align="center"/>
            <el-table-column property="materialtype" label="材料类型" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.materialtype?selectDictLabel(material_typeOptions, scope.row.materialtype):''}}</span>
              </template>
            </el-table-column>
            <el-table-column property="receiveddate" label="提交时间" align="center"/>
            <el-table-column property="originalnum" label="原件数量" align="center"/>
            <el-table-column property="refund" label="是否退件" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.refund?selectDictLabel(is_refundOptions, scope.row.refund):'否'}}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-collapse-item>
      </el-collapse>
    </el-card>
  </div>
</template>
<script>
  import fillsModul from '../../common/modul/fills'
  import { materiaRefundList, materiaRefundInfo, saveRefund, saveMateriaList, delMaterial, getMaterialCount} from '@/api/claim/handleDeal'
  import {validPhone} from '@/utils/validate'
  import { get } from '@/api/dictDetail'

  export default {
    //dicts: ['material_type', 'apply_sourcetype', 'refund_type', 'is_refund'],
    props: {
      dictList:Array,
      status: String,
      node: String,
      claimno: String,
      email: String,
      imageInfo: {
        type: Object,
        default: function() {
          return {}
        }
      },
      suspendFlag: String,
      tabLabels: {
        type: Object,
        default: () => {
          return {}
        }
      },
      materialInfo:{
        type: Object,
        default: function() {
          return {}
        }
      },
      acceptFlag: {
        type: Boolean,
        default: false
      }
    },
    // inject: ['getMateriaList'],
    components: {fillsModul},
    data() {
      let checkPhone = (rule, value, callback) => {
        let str = value.replace(/\s/g, "")
        if(!str){
          callback(new Error('不能为空'))
        } else {
          if(validPhone(str)){
            callback()
          } else if (/\d{3}-\d{8}|\d{4}-\d{7,8}/.test(str)) {
            callback()
          } else {
            callback(new Error('电话号不正确'))
          }
        }
      }
      return {
        activeNames: [],
        activeNames1: ['1'],
        showFlag: true,
        refundLoading: false,
        materialtype: [],
        selMaterialtype: null,
        searchForm: {},
        FillingsForm: {},
        tableFormRules: {
          materialtype: { required: true, message: '不能为空!', trigger: 'change' },
          originalnum: { required: true, message: '不能为空!', trigger: 'blur' },
          copynum: { required: true, message: '不能为空!', trigger: 'blur' }
        },
        returnForm: {},
        tableData: [],
        returnFormRules: {
          addressee: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          telephone: [{ validator: checkPhone, required: true, trigger: ['blur','change'] }],
          address: [{ required: true, message: '不能为空!', trigger: 'blur' }],
          refundtype: [{ required: true, message: '不能为空!', trigger: 'change' }]
        },
        FillingsFormRules: {},
        // typeList: [
        //   {label: '一', value: '1'},
        //   {label: '二', value: '2'},
        //   {label: '三', value: '3'},
        //   {label: '四', value: '4'},
        // ],
        // 发起补材数据
        fillsDia: false,
        FillingsaTable: [],
        hasSelectList: [],
        hasDisabledList:[],
        materialType: [],
        // remarks: ''
        material_typeOptions: [],
        apply_sourcetypeOptions: [],
        refund_typeOptions: [],
        is_refundOptions: [],
      }
    },
    created(){
      if (this.claimno) {
        this.materiaRefundList()
        this.getMaterialType()
      }
    },mounted() {
      if (this.dictList!=null && this.dictList.length!=0) {
        this.material_typeOptions = this.dictList.find(item => {
          return item.dictType == 'material_type'
        }).dictDate
        this.apply_sourcetypeOptions = this.dictList.find(item => {
          return item.dictType == 'apply_sourcetype'
        }).dictDate
        this.refund_typeOptions = this.dictList.find(item => {
          return item.dictType == 'refund_type'
        }).dictDate
        this.is_refundOptions = this.dictList.find(item => {
          return item.dictType == 'is_refund'
        }).dictDate
      }
    },
    methods: {
      // 其子组件触发其父组件的方法
      changeList(val) {
        this.$emit('changeList',val)
      },
      imageUpdate() {
        this.$emit('imageUpdate')
      },
      editHandle(index, row) {
        this.$set(this.materialInfo.data[index], 'editFlag', true)
      },
      getCount(materialtype, idx){
        this.materialInfo.data[idx] = {...this.materialInfo.data[idx]}
        if (materialtype === null || materialtype === undefined || materialtype === '') {
          return
        }
        let params = {
          materialtype,
          claimno: this.claimno
        }
        getMaterialCount(params).then(res => {
          if (res.status === '1') {
            this.$set(this.materialInfo.data[idx], 'originalnum', res.data.originals)
            this.$set(this.materialInfo.data[idx], 'copynum', res.data.copys)
          } else {
            this.$message({ message: res.data.errMsg, type: 'error' })
          }
        })
      },
      // 请求材料类型码表
      getMaterialType() {
        get('material_type').then(res => {
          this.materialType = res.data
        })
      },
      addHandel() {
        if (this.materialType.length === 0) {
          this.getMaterialType()
        }
        let reFlag = true
        if (this.materialType.length === 0) {
          let length = document.getElementsByClassName('el-message--warning').length
          if (length === 0) {
            this.$message.warning('材料信息正在加载，请稍后！')
          }
          return
        }
        this.materialType.forEach(item => {
          if (this.materialInfo.data.findIndex(val => val.materialtype === item.value) === -1) {
            reFlag = false
          }
        })
        if (reFlag) {
          let length = document.getElementsByClassName('el-message--warning').length
          if (length === 0) {
            this.$message.warning('已存在全部材料类型，无法继续添加！')
          }
          return
        }
        // 表单字段具体要根据后端定义进行修改
        const field = {
          editFlag: true,
          type: 'add',
          originalnum: 0,
          copynum: 0
        }
        this.materialInfo.data.push(field)
        setTimeout(() => { this.$refs.table.setCurrentRow(field) }, 10)
      },
      delHandle(index, row, type) {
        // this.$confirm(`是否确定删除?`, '提示', {
        //   confirmButtonText: '确定',
        //   cancelButtonText: '取消',
        //   type: 'warning'
        // }).then(() => {
        if(type===0){
          this.materialInfo.data.splice(index, 1)
        } else {
          let params = {
            materialtype: row.materialtype,
            claimno: this.claimno
          }
          delMaterial(params).then(res => {
            if (res.status === '1') {
              this.$message({ message: '操作成功！', type: 'success' })
              this.materialInfo.data.splice(index, 1)
              // this.$emit('getMateriaList')
              this.materiaRefundList()
              // this.getMateriaList(this.claimno)
            } else {
              this.$message({ message: '操作失败！', type: 'error' })
            }
          })
        }
        // }).catch(() => {
        //   this.$message({
        //     type: 'info',
        //     message: '已取消！'
        //   })
        // })
      },
      saveHandle() {
        this.$refs.materialInfo.validate(valid => {
          if (!valid) {
            return false
          } else {
            if(!this.materialInfo.data.length){
              return this.$message({ message: '材料信息需至少添加一条记录！', type: 'warning' })
            } else {
              let params = []
              this.materialInfo.data.map(item => {
                item.claimno = this.claimno
                //debugger
                if(item.editFlag) delete item.editFlag
                if (item.materialtype !== undefined) {
                  params.push(item)
                }
              })
              saveMateriaList(params).then(res => {
                if (res.status === '1') {
                  this.$message({ message: '操作成功！', type: 'success' })
                  this.materiaRefundList()
                  this.$emit('getMateriaList')
                } else {
                  this.$message({ message: '操作失败！', type: 'error' })
                }
              })
            }
          }
        })
      },
      refundSelChange(val){
        this.materialtype = val
      },
      materiaRefundList(){
        this.refundLoading = true
        this.tableData  = []
        // this.materialInfo.returnChecked = true
        materiaRefundList(this.claimno).then(res => {
          if (res.status === '1') {
            // this.tableData = []
            this.tableData = res.data
          } else {
            this.$message({ message: '查询出错！', type: 'error' })
          }
        }).finally(() => {
          this.refundLoading = false
        })
        this.hasSelectList = []
        this.hasDisabledList = []
        materiaRefundInfo(this.claimno).then(res => {
          if (res.status === '1') {
            if(res.data){
              this.returnForm = {...res.data}
              this.selMaterialtype = res.data.materialtype
              // this.$nextTick(() => {
              //   this.toggleSelection(res.data.materialtype)
              // })
              this.tableData.length?this.tableData.map((item) => {
                if (item.refund == 'Y') {
                  this.hasSelectList.push(item.refund)
                  this.hasDisabledList.push(item.materialtype)
                }
              }):[]
              this.$nextTick(()=>{
                this.tableData.forEach(row => {
                  if(this.hasSelectList.indexOf(row.refund) >= 0){
                    this.$refs.multipleTable.toggleRowSelection(row,true);
                  }
                })
              })
              let flag = false
              flag = this.tableData.length? this.tableData.some((item) => {
                return item.refund == 'Y'
              }):[]
              flag?this.$set(this.materialInfo, 'returnChecked', true)
                :this.$set(this.materialInfo, 'returnChecked', false)
              delete this.returnForm.claimno
              delete this.returnForm.materialtype
            }
          } else {
            this.$message({ message: '查询出错！', type: 'error' })
          }
        })
      },
      refundtypeChange(){
        // this.toggleSelection(this.selMaterialtype)
      },
      confirmHandle() {
        this.$refs.returnForm.validate((valid) => {
          if (valid) {
            let params = {
              claimno: this.claimno,
              ...this.returnForm
            }
            if(this.returnForm.refundtype==='1'){
              if(!this.materialtype.length){
                return this.$message({ message: '请勾选需要退件的材料类型！', type: 'warning' })
              } else {
                params.materialtype = []
                this.materialtype.map(item => {
                  params.materialtype.push(item.materialtype)
                })
                this.saveRefund(params)
              }
            } else {
              params.materialtype = []
              this.tableData.map(item => {
                params.materialtype.push(item.materialtype)
              })
              this.saveRefund(params)
            }
          } else {
            return false
          }
        })
      },
      saveRefund(params){
        saveRefund(params).then(res => {
          if (res.status === '1') {
            this.$message({ message: '操作成功！', type: 'success' })
            this.$emit('getMateriaList')
            this.materiaRefundList()
          } else {
            this.$message({ message: '操作失败！', type: 'error' })
          }
        })
      },
      toggleSelection(rows) {
        let idxs = []
        this.tableData.map((item, i) => {
          rows.map((row) => {
            if(item.materialtype === row){
              idxs.push(i)
            }
          })
        })
        if(idxs.length){
          /* eslint-disable */
          idxs.map(idx => {
            this.$refs.multipleTable.toggleRowSelection(this.tableData[idx])
          })
          /* eslint-disable */
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      fillingsHandle() {
        if (this.suspendFlag !== 'N') {
          this.$message.warning('请先进行解挂！')
          return
        }
        this.fillsDia = true
      }
    }
  }
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .el-tabs ::v-deep .el-tabs__item {
    width: 10%;
    font-weight: 400;
  }

  .el-collapse {
    border: none;
  }

  .el-collapse ::v-deep .el-collapse-item__header {
    padding-bottom: 10px;
    margin-bottom: 10px;
    color: #555;
    font-weight: 360;
    font-size: 14px;
  }
  .material-table ::v-deep .el-form-item--mini {
    margin-bottom: 0;
  }
  .show-little {
    display: inline-block;
    min-width: 80px;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
  }
</style>
